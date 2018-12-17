package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Entidades.Aluno;
import Entidades.Professor;
import Conexao.CHXHSQLDB;

public class ProfessorDAO {
	private final String SQL_INSERIR_PROFESSOR = "INSERT INTO PROFESSOR (" + "NOME, CPF)" + "VALUES (?, ?)";
	private final String SQL_ALTERAR_PROFESSOR = "UPDATE PROFESSOR SET NOME=?, CEP=? WHERE ID=?;";
	private final String SQL_EXCLUIR_PROFESSOR = "DELETE FROM PROFESSOR WHERE ID=?";
	private final String SQL_SELECIONAR_PROFESSOR = "SELECT * FROM PROFESSOR";
	private final String SQL_PESQUISAR_PROFESSOR = "SELECT DISTINCT * FROM PROFESSOR WHERE PROFESSOR=?";

	private PreparedStatement pst = null;

	public boolean inserirProfessor(Professor umProfessor) {
		boolean ret = false;
		Connection conn = CHXHSQLDB.conectar();
		try {
			pst = conn.prepareStatement(SQL_INSERIR_PROFESSOR);
			pst.setString(1, umProfessor.getNome());
			pst.setString(2, umProfessor.getCPF());
			ret = pst.execute();
			CHXHSQLDB.fecharCNX();
		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement " + e.toString());
		}
		return ret;
	}

	public ArrayList<Professor> listarProfessor() {
		ArrayList<Professor> listaProfessor = new ArrayList<Professor>();
		Connection conn = CHXHSQLDB.conectar();
		Professor umProfessor;
		try {
			pst = conn.prepareStatement(SQL_SELECIONAR_PROFESSOR);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				umProfessor = new Professor();
				umProfessor.setID(rs.getInt("ID"));
				umProfessor.setNome(rs.getString("NOME"));
				umProfessor.setCPF(rs.getString("CPF"));
				listaProfessor.add(umProfessor);
			}
			rs.close();
			pst.close();
			CHXHSQLDB.fecharCNX();

		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement " + e.toString());
		}
		return listaProfessor;
	}

	public boolean alterarProfessor(Professor umProfessor) {
		boolean ret = false;
		Connection conn = CHXHSQLDB.conectar();
		try {
			pst = conn.prepareStatement(SQL_ALTERAR_PROFESSOR);
			pst.setString(1, umProfessor.getNome());
			pst.setString(2, umProfessor.getCPF());
			pst.setInt(6, umProfessor.getID());
			ret = pst.execute();
			pst.close();
			CHXHSQLDB.fecharCNX();
		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement " + e.toString());
		}
		return ret;
	}

	public boolean excluirProfessor(Professor umProfessor) {
		boolean ret = false;
		Connection conn = CHXHSQLDB.conectar();
		try {
			pst = conn.prepareStatement(SQL_EXCLUIR_PROFESSOR);
			pst.setInt(1, umProfessor.getID());
			ret = pst.execute();
			pst.close();
			CHXHSQLDB.fecharCNX();
		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement " + e.toString());
		}
		return ret;
	}

	public Professor pesquisar2(String pesquisar2) {
		Connection conn = CHXHSQLDB.conectar();
		Professor umProfessor = new Professor();
		try {
			pst = conn.prepareStatement(SQL_PESQUISAR_PROFESSOR);
			pst.setString(1, pesquisar2);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				umProfessor.setID(rs.getInt("ID"));
				umProfessor.setNome(rs.getString("NOME"));
				umProfessor.setCPF(rs.getString("CPF"));
			}
			rs.close();
			pst.close();
			CHXHSQLDB.fecharCNX();
		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement " + e.toString());
		}
		return umProfessor;
	}

}