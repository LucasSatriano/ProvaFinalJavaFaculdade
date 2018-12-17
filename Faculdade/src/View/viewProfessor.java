package View;

import Entidades.Professor;

public class viewProfessor {

	private int controleID = 0;

	public Professor inserir(String nome, String CPF) {
		Professor Professor = new Professor();

		Professor.setNome(nome);

		Professor.setCPF(CPF);

		Professor.setID(controleID);
		controleID++;

		return Professor;
	}

}
