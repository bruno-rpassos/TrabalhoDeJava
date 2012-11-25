package view.usuario;

import model.User;
import view.Form;
import exception.TypeNotFoundException;

@SuppressWarnings( "serial" )
public class FormUser extends Form<User> {

	public FormUser() throws TypeNotFoundException {
		super( User.class, "USUARIO" );
		super.setBounds( 100, 100, 450, 200 );
	}
}
