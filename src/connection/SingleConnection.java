package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class SingleConnection {
	private static SingleConnection	instance;

	public static SingleConnection getInstance() throws SQLException, ClassNotFoundException {
		if ( SingleConnection.instance == null ) SingleConnection.instance = new SingleConnection();
		return SingleConnection.instance;
	}

	private final Connection	conn;

	private SingleConnection() throws SQLException, ClassNotFoundException {
		Class.forName( "org.postgresql.Driver" );
		this.conn = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/tiagocouto", "tiagocouto", "ingrid9" );
		this.conn.setAutoCommit( false );
	}

	public void commit() throws SQLException {
		this.conn.commit();
	}

	public Statement createStatement() throws SQLException {
		return this.conn.createStatement();
	}

	public PreparedStatement prepareStatement( final String sql ) throws SQLException {
		return this.conn.prepareStatement( sql );
	}

	public PreparedStatement prepareStatement( final String sql, final int returnGeneratedKeys ) throws SQLException {
		return this.conn.prepareStatement( sql, returnGeneratedKeys );
	}

	public void rollback() throws SQLException {
		this.conn.rollback();
	}
}
