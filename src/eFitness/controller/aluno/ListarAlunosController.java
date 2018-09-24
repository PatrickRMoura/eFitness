package eFitness.controller.aluno;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import eFitness.model.Aluno;
import eFitness.negocio.ClienteNegocio;
import eFitness.negocio.NegocioException;
import eFitness.negocio.AlunoNegocio;

/**
 *
 * @author Patrick Moura
 */
public class ListarAlunosController implements Initializable{
    private AlunoNegocio alunoNegocio;
    private ClienteNegocio clienteNegocio;
    private List<Aluno> listaAlunos;
    private Aluno alunoSelecionado;
    private ObservableList<Aluno> observableListaAlunos;
    
    @FXML private VBox painelAluno;
    @FXML private TableView<Aluno> tableViewAluno;
    //@FXML private TableColumn<Aluno, String> tableColumnCliente;
    @FXML private TableColumn<Aluno, Integer> tableColumnCliente = new TableColumn<>("id_cliente");
    @FXML private TableColumn<Aluno, String> tableColumnNome;
    @FXML private TableColumn<Aluno, String> tableColumnTipoAluno;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        alunoNegocio = new AlunoNegocio();
        listarAlunos();
    }
    
    @FXML
    public void listarAlunos() {
        String teste = new PropertyValueFactory<Aluno, Integer>("id").toString();
        System.out.println(teste);
        tableColumnCliente.setCellValueFactory(new PropertyValueFactory<Aluno, Integer>("id"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnTipoAluno.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        
        listaAlunos = alunoNegocio.listar();
        
        observableListaAlunos = FXCollections.observableArrayList(listaAlunos);
        tableViewAluno.setItems(observableListaAlunos);
    }
    
    @FXML
    public void cadastrarAluno() throws IOException{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(eFitness.eFitness.class.getResource("view/aluno/CadastrarAluno.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(painelAluno.getScene().getWindow());
        stage.showAndWait();
    }
    
    @FXML
    public void editarDados(){
        alunoSelecionado = tableViewAluno.getSelectionModel().getSelectedItem();
        
        if (alunoSelecionado != null) {
            try {
                FXMLLoader loader = new FXMLLoader(eFitness.eFitness.class.getResource("view/aluno/CadastrarAluno.fxml"));
                Parent root = (Parent) loader.load();
                
                CadastrarAlunoController controller = (CadastrarAlunoController) loader.getController();
                controller.setAlunoSelecionado(alunoSelecionado);
                
                Stage dialogStage = new Stage();
                dialogStage.setScene(new Scene(root));
                dialogStage.initModality(Modality.APPLICATION_MODAL);
                dialogStage.initOwner(painelAluno.getScene().getWindow());
                dialogStage.showAndWait();
                this.listarAlunos();
            } catch (IOException ex) {
                Logger.getLogger(ListarAlunosController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @FXML
    public void deletarAluno(ActionEvent event) throws IOException {
        alunoSelecionado = tableViewAluno.getSelectionModel().getSelectedItem();
        
        if (alunoSelecionado != null) {
            Alert alert = new Alert(Alert.AlertType.WARNING,"Você deseja realmente excluir essa entrada?",ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                try {
                    alunoNegocio.deletar(alunoSelecionado);
                    this.listarAlunos();
                } catch (NegocioException ex) {
                    Logger.getLogger(ListarAlunosController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
