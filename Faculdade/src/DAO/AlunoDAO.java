package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Entidades.Aluno;
import Conexao.CHXHSQLDB;

public class AlunoDAO {
	private final String SQL_INSERIR_ALUNO = "INSERT INTO ALUNO (" + "NOME, MATRICULA, SEXO, IDPROFESSOR)"
			+ "VALUES (?, ?, ?, ?)";
	private final String SQL_ALTERAR_ALUNO = "UPDATE ALUNO SET NOME=?, MATRICULA=?, SEXO=?  WHERE ID=?;";
	private final String SQL_EXCLUIR_ALUNO = "DELETE FROM ALUNO WHERE ID=?";
	private final String SQL_SELECIONAR_ALUNO = "SELECT *FROM ALUNO";
	private final String SQL_PESQUISAR_ALUNO = "SELECT DISTINCT * FROM ALUNO WHERE NOME=?";

	private PreparedStatement pst = null;

	public boolean inserirAluno(Aluno umAluno) {
		boolean ret = false;
		Connection conn = CHXHSQLDB.conectar();
		try {
			pst = conn.prepareStatement(SQL_INSERIR_ALUNO);
			pst.setString(1, umAluno.getNome());
			pst.setString(2, umAluno.getMatricula());
			pst.setString(3, umAluno.getSexo());
			pst.setInt(4, umAluno.getProfessor().getID());
			ret = pst.execute();
			CHXHSQLDB.fecharCNX();
		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement " + e.toString());
		}
		return ret;
	}

	public ArrayList<Aluno> listarAluno() {
		ArrayList<Aluno> listaAluno = new ArrayList<Aluno>();
		Connection conn = CHXHSQLDB.conectar();
		Aluno umAluno;
		try {
			pst = conn.prepareStatement(SQL_SELECIONAR_ALUNO);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				umAluno = new Aluno();
				umAluno.setID(rs.getInt("ID"));
				umAluno.setNome(rs.getString("NOME"));
				umAluno.setMatricula(rs.getString("MATRICULA"));
				umAluno.setSexo(rs.getString("SEXO"));
				listaAluno.add(umAluno);
			}
			rs.close();
			pst.close();
			CHXHSQLDB.fecharCNX();
		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement " + e.toString());
		}
		return listaAluno;
	}

	public boolean alterarAluno(Aluno umAluno) {
		boolean ret = false;
		Connection conn = CHXHSQLDB.conectar();
		try {
			pst = conn.prepareStatement(SQL_ALTERAR_ALUNO);
			pst.setString(1, umAluno.getNome());
			pst.setString(2, umAluno.getMatricula());
			pst.setString(3, umAluno.getSexo());
			pst.setInt(4, umAluno.getID());
			ret = pst.execute();
			pst.close();
			CHXHSQLDB.fecharCNX();
		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement " + e.toString());
		}
		return ret;
	}

	public boolean excluirAluno(Aluno umAluno) {
		boolean ret = false;
		Connection conn = CHXHSQLDB.conectar();
		try {
			pst = conn.prepareStatement(SQL_EXCLUIR_ALUNO);
			pst.setInt(1, umAluno.getID());
			ret = pst.execute();
			pst.close();
			CHXHSQLDB.fecharCNX();
		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement " + e.toString());
		}
		return ret;
	}

	public Aluno pesquisar(String pesquisar) {
		Connection conn = CHXHSQLDB.conectar();
		Aluno umAluno = new Aluno();
		try {
			pst = conn.prepareStatement(SQL_PESQUISAR_ALUNO);
			pst.setString(1, pesquisar);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				umAluno.setID(rs.getInt("ID"));
				umAluno.setNome(rs.getString("NOME"));
				umAluno.setMatricula(rs.getString("MATRICULA"));
				umAluno.setSexo(rs.getString("SEXO"));
			}
			rs.close();
			pst.close();
			CHXHSQLDB.fecharCNX();
		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement " + e.toString());
		}
		return umAluno;
	}

}
