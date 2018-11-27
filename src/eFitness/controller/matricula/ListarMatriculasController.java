package efitness.controller.matricula;

import efitness.model.Aluno;
import efitness.model.Avaliacao;
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
import efitness.model.Matricula;
import efitness.negocio.MatriculaNegocio;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.TableCell;

/**
 *
 * @author Patrick Moura
 */
public class ListarMatriculasController implements Initializable{
    private MatriculaNegocio matriculaNegocio;
    private Aluno alunoSelecionado;
    private List<Matricula> listaMatriculas;
    private Matricula matriculaSelecionada;
    private ObservableList<Matricula> observableListaMatriculas;
        
    @FXML private TableView<Matricula> tableViewMatricula;
    @FXML private TableColumn<Matricula, String> tableColumnId;
    @FXML private TableColumn<Matricula, LocalDate> tableColumnData;
    @FXML private TableColumn<Matricula, String> tableColumnValor;
    @FXML private TableColumn<Matricula, String> tableColumnPeriodicidade;
    @FXML private TableColumn<Matricula, LocalDate> tableColumnVencimento;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        matriculaNegocio = new MatriculaNegocio();    
        configuraColunas();
    }
    
    @FXML
    public void listarMatriculas() {   
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnData.setCellValueFactory(new PropertyValueFactory<>("data"));        
        tableColumnValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        tableColumnPeriodicidade.setCellValueFactory(new PropertyValueFactory<>("periodicidade"));
        tableColumnVencimento.setCellValueFactory(new PropertyValueFactory<>("vencimento"));
               
        listaMatriculas = matriculaNegocio.listarPorAluno(alunoSelecionado);
        observableListaMatriculas = FXCollections.observableArrayList(listaMatriculas);
        tableViewMatricula.setItems(observableListaMatriculas);        
    }
    
    @FXML
    public void cadastrar() throws IOException{           
      try {
        FXMLLoader loader = new FXMLLoader(efitness.Efitness.class.getResource("view/matricula/CadastrarMatricula.fxml"));
        Parent root = (Parent) loader.load();

        CadastrarMatriculaController controller = (CadastrarMatriculaController) loader.getController();
        controller.setAlunoSelecionado(alunoSelecionado);

        Stage dialogStage = new Stage();
        dialogStage.setScene(new Scene(root));
        dialogStage.initModality(Modality.APPLICATION_MODAL);                
        dialogStage.showAndWait();
        this.listarMatriculas();
      } catch (IOException ex) {
          Logger.getLogger(ListarMatriculasController.class.getName()).log(Level.SEVERE, null, ex);
      }
    }    
    
    @FXML
    public void editar(){
        matriculaSelecionada = tableViewMatricula.getSelectionModel().getSelectedItem();
        if (matriculaSelecionada != null) {
            try {
                FXMLLoader loader = new FXMLLoader(efitness.Efitness.class.getResource("view/matricula/CadastrarMatricula.fxml"));
                Parent root = (Parent) loader.load();
                
                CadastrarMatriculaController controller = (CadastrarMatriculaController) loader.getController();
                controller.setMatriculaSelecionada(matriculaSelecionada);
                
                Stage dialogStage = new Stage();
                dialogStage.setScene(new Scene(root));
                dialogStage.initModality(Modality.APPLICATION_MODAL);                
                dialogStage.showAndWait();
                this.listarMatriculas();
            } catch (IOException ex) {
                Logger.getLogger(ListarMatriculasController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @FXML
    public void deletar(ActionEvent event) throws IOException {
        matriculaSelecionada = tableViewMatricula.getSelectionModel().getSelectedItem();
        if (matriculaSelecionada != null) {
            Alert alert = new Alert(Alert.AlertType.WARNING,"Você deseja realmente excluir essa entrada?",ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                try {
                    matriculaNegocio.deletar(matriculaSelecionada);
                    this.listarMatriculas();
                } catch (Exception ex) {
                    Logger.getLogger(ListarMatriculasController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }        
    }
    
    private void configuraColunas() {
      configuraFormatacaoData();
      configuraFormatacaoVencimento();
    }
    
    private void configuraFormatacaoData() {
      tableColumnData.setCellFactory(column -> {
        TableCell<Matricula, LocalDate> cell = new TableCell<Matricula, LocalDate>() {             
           private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            @Override
            protected void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if(empty) {
                    setText(null);
                }
                else {
                    setText(formatter.format(item));
                }
            }
        };
        return cell;
      });
    }
    
    private void configuraFormatacaoVencimento() {
      tableColumnVencimento.setCellFactory(column -> {
        TableCell<Matricula, LocalDate> cell = new TableCell<Matricula, LocalDate>() {             
           private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            @Override
            protected void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if(empty) {
                    setText(null);
                }
                else {
                    setText(formatter.format(item));
                }
            }
        };
        return cell;
      });
    }
    
    public void setAlunoSelecionado(Aluno alunoSelecionado){
        this.alunoSelecionado = alunoSelecionado;        
    }

}
