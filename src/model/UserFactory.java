package model;

import view.vo.UserVO;

public class UserFactory {

	public static UserVO beanToVO(User u) {
		UserVO vo = new UserVO();
		
		vo.setId(u.getId());
		vo.setNome(u.getNome());
		
		return vo;
	}

}
