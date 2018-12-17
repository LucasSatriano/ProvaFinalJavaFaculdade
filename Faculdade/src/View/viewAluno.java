package View;

import Entidades.Aluno;

public class viewAluno {

	public viewAluno() {

	}

	public Aluno inserir(String nome, String matricula, String sexo) {
		Aluno Aluno = new Aluno();

		Aluno.setNome(nome);

		Aluno.setMatricula(matricula);

		Aluno.setSexo(sexo);

		return Aluno;
	}

}
