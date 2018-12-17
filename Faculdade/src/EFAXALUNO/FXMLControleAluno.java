package EFAXALUNO;

import Entidades.Aluno;
import Entidades.Professor;
import View.viewAluno;
import View.viewProfessor;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import com.sun.xml.internal.bind.v2.model.core.ID;

import DAO.AlunoDAO;
import DAO.ProfessorDAO;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLControleAluno implements Initializable {

	private viewAluno viewAluno = new viewAluno();
	private AlunoDAO AlunoDAO = new AlunoDAO();
	private ArrayList<Aluno> listaAluno = new ArrayList<Aluno>();
	private Aluno AlunoEscolhido;
	private Professor ProfessorEscolhido;
	private ProfessorDAO ProfessorBanco = new ProfessorDAO();
	private viewProfessor viewProfessor = new viewProfessor();
	private ArrayList<Aluno> listarAluno = new ArrayList<Aluno>();
	private ArrayList<Professor> listaProfessor = new ArrayList<Professor>();

	@FXML
    private Button BTNCADASTRAR;

    @FXML
    private Button BTNEDITAR;

    @FXML
    private Button BTNEXCLUIR;

    @FXML
    private Button BTNPESQUISAR;

    @FXML
    private Button BTNSAIR;

    @FXML
    private TableView<Aluno> TABLE;

    @FXML
    private TableColumn<Aluno, String> TABLEID;

    @FXML
    private TableColumn<Aluno, String> TABLENOME;

    @FXML
    private TableColumn<Aluno, String> TABLEMATRICULA;

    @FXML
    private TableColumn<Aluno, String> TABLESEXO;

    @FXML
    private TextField TXTNOME;

    @FXML
    private TextField TXTMATRICULA;

    @FXML
    private TextField TXTSEXO;

    @FXML
    private TextField TXTPESQUISAR;

    @FXML
    private Button BTNATUALIZAR;

    @FXML
    private TextField TXTNOMEPROFESSOR;

    @FXML
    private TextField TXTCPFPROFESSOR;

    @FXML
    private TableView<Professor> TABLE2;

    @FXML
    private TableColumn<Professor, String> TABLENOMEPROFESSOR;

    @FXML
    private TableColumn<Professor, String> TABLECPFPROFESSOR;

	public void initialize(URL location, ResourceBundle resources) {
		TABLEID.setCellValueFactory(new PropertyValueFactory<>("ID"));
		TABLENOME.setCellValueFactory(new PropertyValueFactory<>("Nome"));
		TABLEMATRICULA.setCellValueFactory(new PropertyValueFactory<>("Matricula"));
		TABLESEXO.setCellValueFactory(new PropertyValueFactory<>("Sexo"));

		refreshTable();

		TABLE.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Aluno>() {

			@Override
			public void changed(ObservableValue<? extends Aluno> observable, Aluno oldValue, Aluno newValue) {
				AlunoEscolhido = newValue;
			}
		});

		TABLENOMEPROFESSOR.setCellValueFactory(new PropertyValueFactory<>("Nome Professor"));
		TABLECPFPROFESSOR.setCellValueFactory(new PropertyValueFactory<>("CPF Professor"));

		refreshTableProfessor();

		TABLE2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Professor>() {

			@Override
			public void changed(ObservableValue<? extends Professor> observable, Professor oldValue,
					Professor newValue) {
				ProfessorEscolhido = newValue;
			}
		});
	}

	@FXML
	private void cadastrar(ActionEvent event) {

		Aluno Aluno = viewAluno.inserir(TXTNOME.getText(), TXTMATRICULA.getText(), TXTSEXO.getText());
		Professor Professor = viewProfessor.inserir(TXTNOMEPROFESSOR.getText(), TXTCPFPROFESSOR.getText());
		ProfessorBanco.inserirProfessor(Professor);
		Aluno.setProfessor(Professor);
		AlunoDAO.inserirAluno(Aluno);

		refreshTable();
		refreshTableProfessor();

	}

	@FXML
	private void refreshTable() {
		listaAluno = new AlunoDAO().listarAluno();
		ObservableList<Aluno> observableList = FXCollections.observableArrayList(listaAluno);
		TABLE.setItems(observableList);
		TABLE.refresh();
	}

	@FXML
	private void refreshTableProfessor() {
		listaProfessor = new ProfessorDAO().listarProfessor();
		ObservableList<Professor> observableList2 = FXCollections.observableArrayList(listaProfessor);
		TABLE2.setItems(observableList2);
		TABLE2.refresh();
	}

	@FXML
	private void deletar() {
		AlunoDAO.excluirAluno(AlunoEscolhido);
		refreshTable();
		refreshTableProfessor();
	}

	@FXML
	private void editarAluno() {
		TXTNOME.setText(AlunoEscolhido.getNome());
		TXTMATRICULA.setText(AlunoEscolhido.getMatricula());
		TXTSEXO.setText(AlunoEscolhido.getSexo());

		AlunoDAO.alterarAluno(AlunoEscolhido);
		refreshTable();
		refreshTableProfessor();
	}

	@FXML
	private void editar() {
		AlunoEscolhido.setNome(TXTNOME.getText());
		AlunoEscolhido.setMatricula(TXTMATRICULA.getText());
		AlunoEscolhido.setSexo(TXTSEXO.getText());

		AlunoDAO.alterarAluno(AlunoEscolhido);
		refreshTable();
		refreshTableProfessor();
	}

	@FXML
	private void sair(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Sair");
		String s = "Professor, não saia, por favor!!!";
		alert.setContentText(s);

		Optional<ButtonType> result = alert.showAndWait();

		if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
			System.exit(0);

		}
	}

	@FXML
	private void pesquisar() {

		AlunoDAO AlunoPesquisar = new AlunoDAO();

		Aluno pesquisar = new Aluno();

		pesquisar = AlunoPesquisar.pesquisar(TXTPESQUISAR.getText());

		listarAluno.add(pesquisar);

		ObservableList<Aluno> observableList = FXCollections.observableArrayList(pesquisar);

		TABLE.setItems(observableList);
		
		ProfessorDAO ProfessorPesquisar2 = new ProfessorDAO();

		Professor pesquisar2 = new Professor();

		pesquisar2 = ProfessorPesquisar2.pesquisar2(TXTPESQUISAR.getText());

		listaProfessor.add(pesquisar2);

		ObservableList<Professor> observableList2 = FXCollections.observableArrayList(pesquisar2);

		TABLE2.setItems(observableList2);
		
	}
}
