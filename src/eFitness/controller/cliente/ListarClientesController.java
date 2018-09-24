package eFitness.controller.cliente;

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
import eFitness.model.Personal;
import eFitness.negocio.ClienteNegocio;
import eFitness.negocio.NegocioException;

/**
 *
 * @author Patrick Moura
 */
public class ListarClientesController implements Initializable {
    private ClienteNegocio clienteNegocio;
    private List<Personal> listaClientes;
    private Personal clienteSelecionado;
    private ObservableList<Personal> observableListaPacientes;
    
    @FXML private TableView<Personal> tableViewCliente;
    @FXML private TableColumn<Personal, String> tableColumnRG;
    @FXML private TableColumn<Personal, String> tableColumnNome;
    @FXML private TableColumn<Personal, String> tableColumnTelefone;
    @FXML private TextField textProcurarNome;
    @FXML private Button btnCancelar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clienteNegocio = new ClienteNegocio();
        listarClientes();
    }
    
    @FXML
    public void listarClientes(){
        tableColumnRG.setCellValueFactory(new PropertyValueFactory<>("rg"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        
        listaClientes = clienteNegocio.listar();
        
        observableListaPacientes = FXCollections.observableArrayList(listaClientes);
        tableViewCliente.setItems(observableListaPacientes);
    }
    
    @FXML
    public void cadastrarCliente() throws IOException{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(eFitness.eFitness.class.getResource("view/cliente/CadastrarCliente.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
    
    @FXML
    public void editarCliente(ActionEvent event) throws IOException{
        Personal clienteSelecionado = tableViewCliente.getSelectionModel().getSelectedItem();
        
        if (clienteSelecionado != null) {
            FXMLLoader loader = new FXMLLoader(eFitness.eFitness.class.getResource("view/cliente/CadastrarCliente.fxml"));
            Parent root = (Parent) loader.load();
            
            CadastrarClienteController controller = (CadastrarClienteController) loader.getController();
            controller.setClienteSelecionado(clienteSelecionado);
            
            Stage dialogStage = new Stage();
            dialogStage.setScene(new Scene(root));
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.showAndWait();
            this.listarClientes();
        }
    }
    
    @FXML
    public void deletarCliente(ActionEvent event) throws IOException {
        Personal clienteSelecionado = tableViewCliente.getSelectionModel().getSelectedItem();
        
        if (clienteSelecionado != null) {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                                    "Você deseja realmente excluir essa entrada?",
                                    ButtonType.YES, ButtonType.NO
                                    );
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                try {
                    clienteNegocio.deletar(clienteSelecionado);
                    this.listarClientes();
                } catch (NegocioException ex) {
                    Logger.getLogger(ListarClientesController.class.getName()).log(Level.SEVERE, null, ex);
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
        
            listaClientes = clienteNegocio.listarPorNome(textProcurarNome.getText());
            
            observableListaPacientes = FXCollections.observableArrayList(listaClientes);
            tableViewCliente.setItems(observableListaPacientes);
        } catch (NegocioException ex) {
            Logger.getLogger(ListarClientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void cancelarDados(){
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
}
