package efitness.controller.restricao;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import efitness.model.Restricao;
import efitness.negocio.RestricaoNegocio;

/**
 *
 * @author Pablo Schlusen
 */
public class ListarRestricoesController implements Initializable{
    private RestricaoNegocio restricaoNegocio;
    private Aluno alunoSelecionado;
    private List<Restricao> listaRestricoes;
    private Restricao restricaoSelecionada;
    private ObservableList<Restricao> observableListaRestricaos;
        
    @FXML private TableView<Restricao> tableViewRestricao;
    @FXML private TableColumn<Restricao, String> tableColumnId;
    @FXML private TableColumn<Restricao, Aluno> tableColumnAluno;
    @FXML private TableColumn<Restricao, String> tableColumnCid;
    @FXML private TableColumn<Restricao, String> tableColumnCausa;
    @FXML private TableColumn<Restricao, String> tableColumnDescricao;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        restricaoNegocio = new RestricaoNegocio();   
    }
    
    @FXML
    public void listarRestricoes() {   
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnAluno.setCellValueFactory(new PropertyValueFactory<>("id_aluno"));        
        tableColumnCid.setCellValueFactory(new PropertyValueFactory<>("cid"));
        tableColumnCausa.setCellValueFactory(new PropertyValueFactory<>("causa"));
        tableColumnDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
               
        listaRestricoes = restricaoNegocio.listarPorAluno(alunoSelecionado);
        observableListaRestricaos = FXCollections.observableArrayList(listaRestricoes);
        tableViewRestricao.setItems(observableListaRestricaos);        
    }
    
    @FXML
    public void cadastrar() throws IOException{           
      try {
        FXMLLoader loader = new FXMLLoader(efitness.Efitness.class.getResource("view/restricao/CadastrarRestricao.fxml"));
        Parent root = (Parent) loader.load();

        CadastrarRestricaoController controller = (CadastrarRestricaoController) loader.getController();
        controller.setAlunoSelecionado(alunoSelecionado);

        Stage dialogStage = new Stage();
        dialogStage.setScene(new Scene(root));
        dialogStage.initModality(Modality.APPLICATION_MODAL);                
        dialogStage.showAndWait();
        this.listarRestricoes();
      } catch (IOException ex) {
          Logger.getLogger(ListarRestricoesController.class.getName()).log(Level.SEVERE, null, ex);
      }
    }    
    
    @FXML
    public void editar(){
       //to do
    }
    
    @FXML
    public void deletar(ActionEvent event) throws IOException {
       //to do
    }
    
    public void setAlunoSelecionado(Aluno alunoSelecionado){
        this.alunoSelecionado = alunoSelecionado;        
    }

}
