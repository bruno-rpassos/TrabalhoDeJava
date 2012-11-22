package view.vo;

public class UserVO {
	public static final int ID = 0;
	public static final int NOME = 1;
	
	private String	nome;
	private Integer id;

	public UserVO(String nome) {
		this.nome = nome;
	}

	public UserVO() {}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
