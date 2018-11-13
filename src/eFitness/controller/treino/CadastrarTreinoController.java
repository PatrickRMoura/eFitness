package efitness.controller.treino;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import efitness.model.Aluno;
import efitness.negocio.AlunoNegocio;
import javafx.scene.control.ComboBox;


public class CadastrarTreinoController implements Initializable {
    private AlunoNegocio alunoNegocio;
    private List<Aluno> listaAlunos;
    private Aluno alunoSelecionado;
    private ObservableList<Aluno> observableListaPacientes;
    
    @FXML private ComboBox<Aluno> idAluno;
    @FXML private Button btnCancelar;
    @FXML private Button btnSalvar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        alunoNegocio = new AlunoNegocio();
        listarAlunos();
    }
    
    @FXML
    public void listarAlunos(){
        
        listaAlunos = alunoNegocio.listar();
        
        observableListaPacientes = FXCollections.observableArrayList(listaAlunos);
        idAluno.setItems(observableListaPacientes);
    }
    
    public boolean confirmarAcao(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Você deseja realmente alterar essa entrada?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        
        if (alert.getResult() == ButtonType.YES) {
            return true;
        }
        return false;
    }
    
    @FXML
    public void cancelarDados(){
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

@FXML
    public void salvarDados(){
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
    
}

