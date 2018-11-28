package efitness.controller.aluno;

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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import efitness.model.Aluno;
import efitness.negocio.AlunoNegocio;
import efitness.negocio.NegocioException;
import efitness.controller.avaliacao.ListarAvaliacoesController;
import efitness.controller.matricula.ListarMatriculasController;
import efitness.controller.restricao.ListarRestricoesController;
import efitness.controller.treino.ListarTreinosController;
/**
 *
 * @author Patrick Moura
 */
public class ListarAlunosController implements Initializable {
    private AlunoNegocio alunoNegocio;
    private List<Aluno> listaAlunos;
    private ObservableList<Aluno> observableListaPacientes;
    
    @FXML private TableView<Aluno> tableViewAluno;
    @FXML private TableColumn<Aluno, String> tableColumnRG;
    @FXML private TableColumn<Aluno, String> tableColumnNome;
    @FXML private TableColumn<Aluno, String> tableColumnTelefone;
    @FXML private TextField textProcurarNome;
    @FXML private Button btnCancelar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        alunoNegocio = new AlunoNegocio();
        listarAlunos();
    }
    
    @FXML
    public void listarAlunos(){
        tableColumnRG.setCellValueFactory(new PropertyValueFactory<>("rg"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        
        listaAlunos = alunoNegocio.listar();
        
        observableListaPacientes = FXCollections.observableArrayList(listaAlunos);
        tableViewAluno.setItems(observableListaPacientes);
    }
    
    @FXML
    public void cadastrarAluno() throws IOException{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(efitness.Efitness.class.getResource("view/aluno/CadastrarAluno.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        this.listarAlunos();
    }
    
    @FXML
    public void editarAluno(ActionEvent event) throws IOException{
        Aluno aluno = tableViewAluno.getSelectionModel().getSelectedItem();
        
        if (aluno != null) {
            FXMLLoader loader = new FXMLLoader(efitness.Efitness.class.getResource("view/aluno/CadastrarAluno.fxml"));
            Parent root = (Parent) loader.load();
            
            CadastrarAlunoController controller = (CadastrarAlunoController) loader.getController();
            controller.setAlunoSelecionado(aluno);
            
            Stage dialogStage = new Stage();
            dialogStage.setScene(new Scene(root));
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.showAndWait();
            this.listarAlunos();
        }
    }
    
    @FXML
    public void deletarAluno(ActionEvent event) throws IOException {
        Aluno aluno = tableViewAluno.getSelectionModel().getSelectedItem();
        
        if (aluno != null) {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                                    "Você deseja realmente excluir essa entrada?",
                                    ButtonType.YES, ButtonType.NO
                                    );
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                try {
                    alunoNegocio.deletar(aluno);
                    this.listarAlunos();
                } catch (NegocioException ex) {
                    Logger.getLogger(ListarAlunosController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @FXML
    public void pesquisarNome(ActionEvent event) throws IOException {
        try {
            tableColumnRG.setCellValueFactory(new PropertyValueFactory<>("rg"));
            tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
            tableColumnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        
            listaAlunos = alunoNegocio.listarPorNome(textProcurarNome.getText());
            
            observableListaPacientes = FXCollections.observableArrayList(listaAlunos);
            tableViewAluno.setItems(observableListaPacientes);
        } catch (NegocioException ex) {
            Logger.getLogger(ListarAlunosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void abrirAvaliacoes() throws IOException{
      
      Aluno aluno = tableViewAluno.getSelectionModel().getSelectedItem();
            
      if (aluno != null) {
          FXMLLoader loader = new FXMLLoader(efitness.Efitness.class.getResource("view/avaliacao/ListarAvaliacoes.fxml"));                              
          Parent root = (Parent) loader.load();
          
          ListarAvaliacoesController controller = (ListarAvaliacoesController) loader.getController();
          controller.setAlunoSelecionado(aluno);
          controller.listarAvaliacoes();
          
          Stage dialogStage = new Stage();
          dialogStage.setScene(new Scene(root));
          dialogStage.initModality(Modality.APPLICATION_MODAL);
          dialogStage.showAndWait();          
      }                  
    }
    
    public void abrirMatriculas() throws IOException{
      
      Aluno aluno = tableViewAluno.getSelectionModel().getSelectedItem();
            
      if (aluno != null) {
          FXMLLoader loader = new FXMLLoader(efitness.Efitness.class.getResource("view/matricula/ListarMatriculas.fxml"));                              
          Parent root = (Parent) loader.load();
          
          ListarMatriculasController controller = (ListarMatriculasController) loader.getController();
          controller.setAlunoSelecionado(aluno);
          controller.listarMatriculas();
          
          Stage dialogStage = new Stage();
          dialogStage.setScene(new Scene(root));
          dialogStage.initModality(Modality.APPLICATION_MODAL);
          dialogStage.showAndWait();          
      }                  
    }
    
    public void abrirTreinos() throws IOException{
      
      Aluno aluno = tableViewAluno.getSelectionModel().getSelectedItem();
            
      if (aluno != null) {
          FXMLLoader loader = new FXMLLoader(efitness.Efitness.class.getResource("view/Treino/ListarTreinos.fxml"));                              
          Parent root = (Parent) loader.load();
          
          ListarTreinosController controller = (ListarTreinosController) loader.getController();
          controller.setAlunoSelecionado(aluno);
          controller.listarTreinos();
          
          Stage dialogStage = new Stage();
          dialogStage.setScene(new Scene(root));
          dialogStage.initModality(Modality.APPLICATION_MODAL);
          dialogStage.showAndWait();          
      }                  
    }
    
    public void abrirRestricoes() throws IOException{
      
      Aluno aluno = tableViewAluno.getSelectionModel().getSelectedItem();
            
      if (aluno != null) {
          FXMLLoader loader = new FXMLLoader(efitness.Efitness.class.getResource("view/restricao/ListarRestricoes.fxml"));                              
          Parent root = (Parent) loader.load();
          
          ListarRestricoesController controller = (ListarRestricoesController) loader.getController();
          controller.setAlunoSelecionado(aluno);
          controller.listarRestricoes();
          
          Stage dialogStage = new Stage();
          dialogStage.setScene(new Scene(root));
          dialogStage.initModality(Modality.APPLICATION_MODAL);
          dialogStage.showAndWait();          
      }                  
    }
    
    @FXML
    public void cancelarDados(){
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
}
