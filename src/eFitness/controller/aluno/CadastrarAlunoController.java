package eFitness.controller.aluno;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import eFitness.model.Personal;
import eFitness.model.Aluno;
import eFitness.negocio.ClienteNegocio;
import eFitness.negocio.NegocioException;
import eFitness.negocio.AlunoNegocio;

/**
 *
 * @author Patrick Moura
 */
public class CadastrarAlunoController implements Initializable {
    private AlunoNegocio alunoNegocio;
    private Aluno alunoSelecionado;
    
    private List<Personal> listaClientes;
    private ClienteNegocio clienteNegocio;
    private ObservableList<Personal> observableListaPacientes;
    private Personal clienteSelecionado;
    
    @FXML private Button btnSalvar;
    @FXML private TextField textCliente;
    @FXML private TextField textNomeAluno;
    @FXML private TextField textTipoAluno;
    @FXML private TextField textPesquisarCliente;
    @FXML private TableView<Personal> tableViewCliente;
    @FXML private TableColumn<Personal, String> tableColumnNome;
    @FXML private TableColumn<Personal, String> tableColumnTelefone;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        alunoNegocio = new AlunoNegocio();
        clienteNegocio = new ClienteNegocio();
    }
    
    @FXML
    public void salvarDados(){
        Stage stage = (Stage) btnSalvar.getScene().getWindow();
        
        if (alunoSelecionado == null) {
            if (textNomeAluno.getText().length() > 0 && textCliente.getText().length() > 0 && textTipoAluno.getText().length() > 0) {
                System.out.println(textNomeAluno.getText().length());
                String tipoOpcao = "cadastrar";
                if (confirmarAcao(tipoOpcao)) {
                    try {
                        alunoNegocio.salvar(new Aluno(textNomeAluno.getText(), textTipoAluno.getText(), clienteSelecionado));
                        stage.close();
                    } catch (Exception e) {
                        System.out.println("Errou");
                    }
                }       
            }
        } else {
            String tipoOpcao = "editar";
            if (confirmarAcao(tipoOpcao)) {
                try {
                    alunoSelecionado.setNome(textNomeAluno.getText());
                    alunoSelecionado.setTipo(textTipoAluno.getText());
                    alunoNegocio.atualizar(alunoSelecionado);
                    stage.close();
                } catch (NegocioException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    @FXML
    public void getPesquisarNomeCliente(){
        try {
            tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
            tableColumnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
            
            listaClientes = clienteNegocio.listarPorNome(textPesquisarCliente.getText());
            
            observableListaPacientes = FXCollections.observableArrayList(listaClientes);
            tableViewCliente.setItems(observableListaPacientes);
        } catch (NegocioException ex) {
            Logger.getLogger(CadastrarAlunoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Personal getCliente(){
        clienteSelecionado = tableViewCliente.getSelectionModel().getSelectedItem();
        if (clienteSelecionado != null && alunoSelecionado == null) {
            textCliente.setText(clienteSelecionado.getNome());
            return clienteSelecionado;
        }
        return null;
    }
    
    public boolean confirmarAcao(String tipo){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Você deseja realmente "+ tipo +" essa entrada?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        
        if (alert.getResult() == ButtonType.YES) {
            return true;
        }
        return false;
    }

    public void setAlunoSelecionado(Aluno alunoSelecionado) {
        this.alunoSelecionado = alunoSelecionado;
        textCliente.setText("1");
        textNomeAluno.setText(alunoSelecionado.getNome());
        textTipoAluno.setText(alunoSelecionado.getTipo());
        textCliente.setDisable(true);
    }
}
