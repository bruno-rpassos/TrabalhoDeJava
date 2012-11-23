package view.usuario;

import view.Form;
import view.vo.UserVO;

@SuppressWarnings("serial")
public class FormUser extends Form<UserVO> {

	public FormUser() throws Exception {
		super(UserVO.class, "USUARIO");
	}
}
