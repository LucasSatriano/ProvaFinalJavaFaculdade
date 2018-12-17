package Entidades;

public class Professor {

	private int ID;
	private String nome;
	private String CPF;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		this.ID = iD;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String CPF) {
		this.CPF = CPF;
	}

	public Professor() {

		// struct - recebe informações do usuário no constructor
		// identalização - a criação do aluno será verdadeira com as informações
		// recebidas
		// serealização - chama o constructor vazio
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CPF == null) ? 0 : CPF.hashCode());
		result = prime * result + ID;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Professor other = (Professor) obj;
		if (CPF == null) {
			if (other.CPF != null)
				return false;
		} else if (!CPF.equals(other.CPF))
			return false;
		if (ID != other.ID)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Professor [ID=" + ID + ", nome=" + nome + ", CPF=" + CPF + "]";
	}

	public Professor(int iD, String nome, String CPF) {
		super();
		this.ID = iD;
		this.nome = nome;
		this.CPF = CPF;
	}

}
