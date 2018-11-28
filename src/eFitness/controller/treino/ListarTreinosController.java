package efitness.controller.treino;

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
import javafx.stage.Modality;
import javafx.stage.Stage;
import efitness.model.Treino;
import efitness.negocio.TreinoNegocio;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.TableCell;

/**
 *
 * @author Pablo Schlusen
 */
public class ListarTreinosController implements Initializable{
    private TreinoNegocio treinoNegocio;
    private Aluno alunoSelecionado;
    private List<Treino> listaTreinos;
    private Treino treinoSelecionado;
    private ObservableList<Treino> observableListaTreinos;
        
    @FXML private TableView<Treino> tableViewTreino;
    @FXML private TableColumn<Treino, String> tableColumnId;
    @FXML private TableColumn<Treino, java.sql.Date> tableColumnData;
    @FXML private TableColumn<Treino, String> tableColumnObjetivo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        treinoNegocio = new TreinoNegocio();    
        configuraColunas();
    }
    
    @FXML
    public void listarTreinos() {   
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnData.setCellValueFactory(new PropertyValueFactory<>("data"));        
        tableColumnObjetivo.setCellValueFactory(new PropertyValueFactory<>("objetivo"));
               
        listaTreinos = treinoNegocio.listarPorAluno(alunoSelecionado);
        observableListaTreinos = FXCollections.observableArrayList(listaTreinos);
        tableViewTreino.setItems(observableListaTreinos);        
    }
    
    @FXML
    public void cadastrar() throws IOException{           
      try {
        FXMLLoader loader = new FXMLLoader(efitness.Efitness.class.getResource("view/matricula/CadastrarTreino.fxml"));
        Parent root = (Parent) loader.load();

        CadastrarTreinoController controller = (CadastrarTreinoController) loader.getController();
        controller.setAlunoSelecionado(alunoSelecionado);

        Stage dialogStage = new Stage();
        dialogStage.setScene(new Scene(root));
        dialogStage.initModality(Modality.APPLICATION_MODAL);                
        dialogStage.showAndWait();
        this.listarTreinos();
      } catch (IOException ex) {
          Logger.getLogger(ListarTreinosController.class.getName()).log(Level.SEVERE, null, ex);
      }
    }    
    
    @FXML
    public void editar(){
        treinoSelecionado = tableViewTreino.getSelectionModel().getSelectedItem();
        if (treinoSelecionado != null) {
            try {
                FXMLLoader loader = new FXMLLoader(efitness.Efitness.class.getResource("view/matricula/CadastrarTreino.fxml"));
                Parent root = (Parent) loader.load();
                
                CadastrarTreinoController controller = (CadastrarTreinoController) loader.getController();
                controller.setTreinoSelecionada(treinoSelecionado);
                
                Stage dialogStage = new Stage();
                dialogStage.setScene(new Scene(root));
                dialogStage.initModality(Modality.APPLICATION_MODAL);                
                dialogStage.showAndWait();
                this.listarTreinos();
            } catch (IOException ex) {
                Logger.getLogger(ListarTreinosController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @FXML
    public void deletar(ActionEvent event) throws IOException {
        treinoSelecionado = tableViewTreino.getSelectionModel().getSelectedItem();
        if (treinoSelecionado != null) {
            Alert alert = new Alert(Alert.AlertType.WARNING,"Você deseja realmente excluir essa entrada?",ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                try {
                    treinoNegocio.deletar(treinoSelecionado);
                    this.listarTreinos();
                } catch (Exception ex) {
                    Logger.getLogger(ListarTreinosController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }        
    }
    
    private void configuraColunas() {
      configuraFormatacaoData();
    }
    
    private void configuraFormatacaoData() {
      tableColumnData.setCellFactory(column -> {
        TableCell<Treino, java.sql.Date> cell = new TableCell<Treino, java.sql.Date>() {           
           
            
            @Override
            protected void updateItem(java.sql.Date item, boolean empty) {
                super.updateItem(item, empty);
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); 
                if(empty) {
                    setText(null);
                }
                else {
                    setText(df.format(item));
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
