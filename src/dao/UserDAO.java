package dao;

import java.util.ArrayList;
import java.util.List;

import model.Listener;
import model.User;

public class UserDAO extends AbstractDAO<User> {
	private static UserDAO	instance;

	public static UserDAO getInstance() throws Exception {
		if ( UserDAO.instance == null ) {
			UserDAO.instance = new UserDAO();
		}
		return UserDAO.instance;
	}

	private final List<Listener>	listeners;

	private UserDAO() throws Exception {
		super(User.class);
		this.listeners = new ArrayList<Listener>();
	}

	public void addListener( final Listener l ) {
		this.listeners.add( l );
	}

	public User getByLogin( final String login ) throws Exception {
		return super.getByField("login", login).get(0);
	}

	@Override
	public void saveOrUpdate( final User user ) throws Exception {
		super.saveOrUpdate(user);
		this.dataChanged();
	}

	private void dataChanged() {
		for ( final Listener l : this.listeners )
			l.actionPerformed();
	}
}
