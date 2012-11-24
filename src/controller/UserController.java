package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;

import model.User;
import repository.UserRepository;
import view.vo.UserVO;
import dao.UserDAO;
import exception.PassNotFoundException;
import exception.UserNotFoundException;
import factory.UserFactory;

public class UserController {

	public static void create( final UserVO vo ) {
		final User user = UserFactory.getUserByVO( vo );
		try {
			UserDAO.getInstance().saveOrUpdate( user );
		} catch ( final Exception e ) {
			e.printStackTrace();
		}
	}

	public static List<UserVO> getAllUserVO() {
		final List<User> users = UserDAO.getInstance().list();
		final List<UserVO> vo = new ArrayList<UserVO>();

		for ( final User user : users ) {
			vo.add( UserFactory.beanToVO( user ) );
		}

		return vo;
	}

	public static void newResource() {
		try {
			final JDialog view = new view.usuario.NewUser();
			view.setVisible( true );
		} catch ( final Exception e ) {
			e.printStackTrace();
		}
	}

	public static void registerNewUser( final User u ) {
		UserRepository.getInstance().add( u );
	}

	public static void update( final UserVO vo ) {
		final User user = UserFactory.getUserByVO( vo );
		try {
			UserDAO.getInstance().saveOrUpdate( user );
		} catch ( final Exception e ) {
			e.printStackTrace();
		}
	}

	public static void validarLogin( final String user, final String pass ) throws PassNotFoundException, UserNotFoundException {
		System.out.println( "looking for : " + user );
		System.out.println( "   with pass : " + pass );

		final User u = UserRepository.getInstance().getUser( user );
		System.out.println( "user found > " + u.getNome() );
		System.out.println( "   pass > " + u.getSenha() );

		System.out.println( "   user.pass equals pass ? " + u.getSenha().equals( pass ) );

		if ( !u.getSenha().equals( pass ) ) throw new PassNotFoundException();
	}

}
