package dao;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import model.Entity;
import util.BeanUtils;
import util.StringUtils;
import annotation.Table;
import connection.SingleConnection;
import exception.MissingAnnotationException;
import exception.SQLException;

public class AbstractDAO<T extends Entity> implements DAO<T> {
	private final SingleConnection	conn;
	private final Class<T>			entity;
	private final String			entityName;

	public AbstractDAO( final Class<T> clazz ) throws MissingAnnotationException, ClassNotFoundException, SQLException {
		this.entity = clazz;
		if ( !this.entity.isAnnotationPresent( annotation.Entity.class ) ) throw new MissingAnnotationException( "Entity" );

		if ( this.entity.isAnnotationPresent( annotation.Table.class ) ) {
			final Table t = this.entity.getAnnotation( annotation.Table.class );
			this.entityName = t.name();
		} else this.entityName = StringUtils.camelCaseToUnderscore( this.entity.getSimpleName().toLowerCase() );

		try {
			this.conn = SingleConnection.getInstance();
		} catch ( final java.sql.SQLException e ) {
			throw new SQLException( e.getMessage() );
		}
	}

	@Override
	public T getById( final Integer id ) throws SQLException {
		return this.getByField( "id", id ).get( 0 );
	}

	@Override
	public List<T> list() throws Exception {
		final String query = "SELECT * FROM " + this.entityName;
		final PreparedStatement ps = this.conn.prepareStatement( query );

		System.out.println( query );
		final ResultSet rs = ps.executeQuery();

		final List<T> results = this.getListOfBeansFromResultSet( rs );

		return results;
	}

	@Override
	public void saveOrUpdate( final T obj ) throws SQLException {
		try {
			final Map<String, Object> columNamesWithValues = obj.getAllColumnsWithValue();

			final String query = obj.isNew() ? this.getInsertQuery( columNamesWithValues ) : this.getUpdateQuery( obj, columNamesWithValues );

			final PreparedStatement ps = this.conn.prepareStatement( query, Statement.RETURN_GENERATED_KEYS );

			Integer parametroAtual = 1;
			for ( final Map.Entry<String, Object> entry : columNamesWithValues.entrySet() ) {
				ps.setObject( parametroAtual, entry.getValue() );
				parametroAtual++;
			}
			if ( !obj.isNew() ) ps.setObject( parametroAtual, obj.getId() );

			final int affectedRows = ps.executeUpdate();
			if ( affectedRows == 0 ) throw new SQLException( "Houve um erro ao gravar o registro" );

			final ResultSet generatedKeys = ps.getGeneratedKeys();
			if ( generatedKeys.next() ) obj.setId( generatedKeys.getInt( 1 ) );
			else throw new SQLException( "Houve um erro ao recuperar a chave o registro" );

			if ( obj.isNew() ) this.persistAssociations( obj );

			if ( ps != null ) ps.close();
			if ( generatedKeys != null ) generatedKeys.close();

			this.conn.commit();
		} catch ( final java.sql.SQLException e ) {
			throw new SQLException( e.getMessage() );
		} catch ( final IllegalArgumentException e ) {
			e.printStackTrace();
		} catch ( final IllegalAccessException e ) {
			e.printStackTrace();
		}
	}

	protected List<T> getByField( final String field, final Object value ) throws SQLException {
		List<T> results = null;
		try {
			final String query = "SELECT * FROM " + this.entityName + " WHERE " + StringUtils.camelCaseToUnderscore( field ) + " = (?)";
			final PreparedStatement ps = this.conn.prepareStatement( query );
			ps.setObject( 1, value );

			final ResultSet rs = ps.executeQuery();
			results = this.getListOfBeansFromResultSet( rs );

			if ( results.size() == 0 ) throw new SQLException( "Objeto não encontrado no banco" );
		} catch ( final java.sql.SQLException e1 ) {
			throw new SQLException( e1.getMessage() );
		}
		return results;
	}

	private Field[] getEntityDeclaredFields() {
		final List<Field> allFields = new ArrayList<Field>( Arrays.asList( this.entity.getDeclaredFields() ) );
		final List<Field> superclassFields = new ArrayList<Field>( Arrays.asList( this.entity.getSuperclass().getDeclaredFields() ) );
		allFields.addAll( superclassFields );
		return allFields.toArray( new Field[allFields.size()] );
	}

	private String getInsertQuery( final Map<String, Object> columNamesWithValues ) {
		final StringBuilder query = new StringBuilder();
		query.append( "INSERT INTO " + this.entityName + " " );

		final StringBuilder insertColumns = new StringBuilder();
		final StringBuilder insertValues = new StringBuilder();

		for ( final Map.Entry<String, Object> entry : columNamesWithValues.entrySet() ) {
			insertColumns.append( ", " + entry.getKey() + " " );
			insertValues.append( ", ? " );
		}

		insertColumns.setCharAt( 0, '(' );
		insertValues.setCharAt( 0, '(' );

		insertColumns.append( " ) " );
		insertValues.append( " ) " );

		query.append( insertColumns + " VALUES " + insertValues );
		return query.toString();
	}

	@SuppressWarnings( { "unchecked", "rawtypes" } )
	private List<T> getListOfBeansFromResultSet( final ResultSet rs ) throws SQLException {
		final List<T> list = new ArrayList<T>();
		try {
			final ResultSetMetaData rsmd = rs.getMetaData();

			if ( rs != null ) while ( rs.next() ) {
				T bean = null;
				bean = this.entity.newInstance();
				for ( int _iterator = 0; _iterator < rsmd.getColumnCount(); _iterator++ ) {
					final String columnName = StringUtils.underscoreToCamelCase( rsmd.getColumnName( _iterator + 1 ) );
					Object columnValue = rs.getObject( _iterator + 1 );

					for ( final Field field : this.getEntityDeclaredFields() ) {
						String fieldNameInDB = field.getName();

						if ( field.isAnnotationPresent( annotation.BelongsTo.class ) ) fieldNameInDB = fieldNameInDB + "Id";

						if ( fieldNameInDB.equalsIgnoreCase( columnName ) && columnValue != null && !field.isAnnotationPresent( annotation.Transient.class ) ) {

							if ( field.isAnnotationPresent( annotation.BelongsTo.class ) ) {
								AbstractDAO absDAO = null;
								absDAO = new AbstractDAO( field.getType() );

								final List relation = absDAO.getByField( "id", new Integer( columnValue.toString() ) );
								columnValue = relation.size() >= 1 ? relation.get( 0 ) : null;
							}

							BeanUtils.setProperty( bean, field.getName(), columnValue );
							break;
						}
					}
				}
				list.add( bean );
			}
		} catch ( final java.sql.SQLException e1 ) {
			throw new SQLException( e1.getMessage() );
		} catch ( final InstantiationException e ) {
			e.printStackTrace();
		} catch ( final IllegalAccessException e ) {
			e.printStackTrace();
		} catch ( final MissingAnnotationException e ) {
			e.printStackTrace();
		} catch ( final ClassNotFoundException e ) {
			e.printStackTrace();
		}
		return list;
	}

	private String getUpdateQuery( final T obj, final Map<String, Object> columNamesWithValues ) {
		final StringBuilder query = new StringBuilder();
		query.append( "UPDATE " + this.entityName + " SET " );

		final StringBuilder insertColumns = new StringBuilder();

		for ( final Map.Entry<String, Object> entry : columNamesWithValues.entrySet() )
			insertColumns.append( ", " + entry.getKey() + " = ? " );

		insertColumns.setCharAt( 0, ' ' );
		insertColumns.append( " WHERE id = ? " );

		query.append( insertColumns );
		return query.toString();
	}

	private void persistAssociations( final T obj ) throws SQLException, IllegalArgumentException, IllegalAccessException {
		try {
			PreparedStatement associationPs = null;

			for ( final Map.Entry<String, Collection<Entity>> entry : obj.getAllManyToManyFieldsWithValue().entrySet() )
				for ( final Entity e : entry.getValue() ) {
					final StringBuilder associateQuery = new StringBuilder( "INSERT INTO " + entry.getKey() + " ( " + this.entityName + "_id, "
									+ e.getClass().getSimpleName().toLowerCase() + "_id ) values ( " + obj.getId() + ", " + e.getId() + " )" );
					associationPs = this.conn.prepareStatement( associateQuery.toString() );

					final int affectedAssociationRows = associationPs.executeUpdate();
					if ( affectedAssociationRows == 0 ) throw new SQLException( "Houve um erro ao gravar as associa��es" );
				}

			if ( associationPs != null ) associationPs.close();
		} catch ( final java.sql.SQLException e1 ) {
			throw new SQLException( e1.getMessage() );
		}
	}

}
