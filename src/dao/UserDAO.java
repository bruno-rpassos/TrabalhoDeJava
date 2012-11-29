package dao;

import java.util.ArrayList;
import java.util.List;

import model.Listener;
import model.User;
import exception.MissingAnnotationException;
import exception.SQLException;

public class UserDAO extends AbstractDAO<User> {
	private static UserDAO	instance;

	public static UserDAO getInstance() throws SQLException {
		if ( UserDAO.instance == null ) try {
			UserDAO.instance = new UserDAO();
		} catch ( final MissingAnnotationException e ) {
			e.printStackTrace();
		} catch ( final ClassNotFoundException e ) {
			e.printStackTrace();
		}
		return UserDAO.instance;
	}

	private final List<Listener>	listeners;

	private UserDAO() throws MissingAnnotationException, ClassNotFoundException, SQLException {
		super( User.class );
		this.listeners = new ArrayList<Listener>();
	}

	public void addListener( final Listener l ) {
		this.listeners.add( l );
	}

	public User getByLogin( final String login ) throws SQLException {
		return super.getByField( "nome", login ).get( 0 );
	}

	@Override
	public void saveOrUpdate( final User user ) throws SQLException {
		super.saveOrUpdate( user );
		this.dataChanged();
	}

	private void dataChanged() {
		for ( final Listener l : this.listeners )
			l.actionPerformed();
	}
}
