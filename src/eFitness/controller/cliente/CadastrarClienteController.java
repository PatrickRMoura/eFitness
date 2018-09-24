package eFitness.controller.cliente;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import eFitness.model.Personal;
import eFitness.negocio.ClienteNegocio;
import eFitness.negocio.NegocioException;

/**
 *
 * @author Patrick Moura
 */
public class CadastrarClienteController implements Initializable {
    private ClienteNegocio clienteNegocio;
    private Personal clienteSelecionado;
    
    @FXML private TextField textRG;
    @FXML private TextField textNome;
    @FXML private TextField textTelefone;
    @FXML private Button btnCancelar;
    @FXML private Button btnSalvar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clienteNegocio = new ClienteNegocio();
    }
    
    @FXML
    public void salvarDados() {
        Stage stage = (Stage) btnSalvar.getScene().getWindow();
        
        if (clienteSelecionado == null) {
            if (confirmarAcao()) {
                try {
                    clienteNegocio.salvar(new Personal(textRG.getText(),textNome.getText(),textTelefone.getText()));
                } catch (NegocioException ex) {
                }
            }
            
        } else {
            if (confirmarAcao()) {
                try {
                    clienteSelecionado.setNome(textNome.getText());
                    clienteSelecionado.setTelefone(textTelefone.getText());
                    clienteNegocio.atualizar(clienteSelecionado);
                } catch (NegocioException ex) {
                }   
            }
        }
        stage.close();
    }
    
    @FXML
    public void cancelarDados(){
        Stage stage = (Stage) btnSalvar.getScene().getWindow();
        stage.close();
    }
    
    public boolean confirmarAcao(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Você deseja realmente alterar essa entrada?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        
        if (alert.getResult() == ButtonType.YES) {
            return true;
        }
        return false;
    }
    
    public void setClienteSelecionado(Personal clienteSelecionado){
        this.clienteSelecionado = clienteSelecionado;
        textNome.setText(clienteSelecionado.getNome());
        textRG.setText(clienteSelecionado.getRg());
        textTelefone.setText(clienteSelecionado.getTelefone());
        textRG.setDisable(true);
    }
    
    public Personal getClienteSelecionado(){
        return clienteSelecionado;
    }
}
