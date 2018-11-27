package efitness.controller.avaliacao;

import efitness.model.Aluno;
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
import efitness.model.Avaliacao;
import efitness.negocio.AvaliacaoNegocio;
import efitness.negocio.NegocioException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javafx.scene.control.TableCell;

/**
 *
 * @author gustavo.fonseca
 */
public class ListarAvaliacoesController implements Initializable{
    private AvaliacaoNegocio avaliacaoNegocio;    
    private Avaliacao avaliacaoSelecionada;
    private Aluno alunoSelecionado;
    private List<Avaliacao> listaAvaliacoes;
    private ObservableList<Avaliacao> observableListaAvaliacoes;
        
    @FXML private TableView<Avaliacao> tableViewAvaliacao;
    @FXML private TableColumn<Avaliacao, String> tableColumnId;
    @FXML private TableColumn<Avaliacao, LocalDate> tableColumnData;    
    @FXML private TableColumn<Avaliacao, String> tableColumnMassaCorporal;    
    @FXML private TableColumn<Avaliacao, Object> tableColumnFrequenciaCardiaca;    
    @FXML private TableColumn<Avaliacao, Object> tableColumnPressaoArterial; 

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        avaliacaoNegocio = new AvaliacaoNegocio();
        configuraFormatacaoColunas();
    }
    
    @FXML
    public void listarAvaliacoes() {   
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnData.setCellValueFactory(new PropertyValueFactory<>("data"));
        tableColumnMassaCorporal.setCellValueFactory(new PropertyValueFactory<>("massaCorporal"));
        tableColumnFrequenciaCardiaca.setCellValueFactory(new PropertyValueFactory<>("frequenciaCardiaca"));
        tableColumnPressaoArterial.setCellValueFactory(new PropertyValueFactory<>("pressaoArterial"));
                
        listaAvaliacoes = avaliacaoNegocio.listarPorAluno(alunoSelecionado);
        observableListaAvaliacoes = FXCollections.observableArrayList(listaAvaliacoes);
        tableViewAvaliacao.setItems(observableListaAvaliacoes);                                             
    }
    
    @FXML
    public void cadastrarAvaliacao() throws IOException{
      
      if (alunoSelecionado != null) {
        FXMLLoader loader = new FXMLLoader(efitness.Efitness.class.getResource("view/avaliacao/CadastrarAvaliacao.fxml"));
        Parent root = (Parent) loader.load();

        CadastrarAvaliacaoController controller = (CadastrarAvaliacaoController) loader.getController();
        controller.setAlunoSelecionado(alunoSelecionado);

        Stage dialogStage = new Stage();
        dialogStage.setScene(new Scene(root));
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.showAndWait();
        this.listarAvaliacoes();
       }      
        
    }
    
    @FXML
    public void editarDados(){
      avaliacaoSelecionada = tableViewAvaliacao.getSelectionModel().getSelectedItem();        
      if (avaliacaoSelecionada != null) {
        try {
            FXMLLoader loader = new FXMLLoader(efitness.Efitness.class.getResource("view/avaliacao/CadastrarAvaliacao.fxml"));
            Parent root = (Parent) loader.load();

            CadastrarAvaliacaoController controller = (CadastrarAvaliacaoController) loader.getController();
            controller.setAvaliacaoSelecionada(avaliacaoSelecionada);

            Stage dialogStage = new Stage();
            dialogStage.setScene(new Scene(root));
            dialogStage.initModality(Modality.APPLICATION_MODAL);              
            dialogStage.showAndWait();
            this.listarAvaliacoes();
        } catch (IOException ex) {
            Logger.getLogger(ListarAvaliacoesController.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
    
    @FXML
    public void deletarAvaliacao(ActionEvent event) throws IOException {
        avaliacaoSelecionada = tableViewAvaliacao.getSelectionModel().getSelectedItem();
        if (avaliacaoSelecionada != null) {
            Alert alert = new Alert(Alert.AlertType.WARNING,"Você deseja realmente excluir essa entrada?",ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                try {
                    avaliacaoNegocio.deletar(avaliacaoSelecionada);
                    this.listarAvaliacoes();
                } catch (Exception ex) {
                    Logger.getLogger(ListarAvaliacoesController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    private void configuraFormatacaoColunas() {
      configuraFormatacaoData();
    }
    
    private void configuraFormatacaoData() {
      tableColumnData.setCellFactory(column -> {
        TableCell<Avaliacao, LocalDate> cell = new TableCell<Avaliacao, LocalDate>() {             
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
