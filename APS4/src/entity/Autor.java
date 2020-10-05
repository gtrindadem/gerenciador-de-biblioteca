package entity;

public class Autor {
	int id;
	String sobrenome;
	String nome;
	
	public Autor(int id, String sobrenome, String nome) {
		this.id = id;
		this.sobrenome = sobrenome;
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
