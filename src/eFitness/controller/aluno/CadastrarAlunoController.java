package efitness.controller.aluno;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import efitness.model.Aluno;
import efitness.negocio.AlunoNegocio;
import efitness.negocio.NegocioException;

/**
 *
 * @author Patrick Moura
 */
public class CadastrarAlunoController implements Initializable {
    private AlunoNegocio alunoNegocio;
    private Aluno alunoSelecionado;
    
    @FXML private TextField textRG;
    @FXML private TextField textNome;
    @FXML private TextField textTelefone;
    @FXML private Button btnCancelar;
    @FXML private Button btnSalvar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        alunoNegocio = new AlunoNegocio();
    }
    
    @FXML
    public void salvarDados() {
        Stage stage = (Stage) btnSalvar.getScene().getWindow();
        
        if (alunoSelecionado == null) {
            if (confirmarAcao()) {
                try {
                    alunoNegocio.salvar(new Aluno(textRG.getText(),textNome.getText(),textTelefone.getText()));
                } catch (NegocioException ex) {
                }
            }
            
        } else {
            if (confirmarAcao()) {
                try {
                    alunoSelecionado.setNome(textNome.getText());
                    alunoSelecionado.setTelefone(textTelefone.getText());
                    alunoNegocio.atualizar(alunoSelecionado);
                } catch (NegocioException ex) {
                }   
            }
        }
        stage.close();
    }
    
    @FXML
    public void cancelarDados(){
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
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
    
    public void setAlunoSelecionado(Aluno alunoSelecionado){
        this.alunoSelecionado = alunoSelecionado;
        textNome.setText(alunoSelecionado.getNome());
        textRG.setText(alunoSelecionado.getRg());
        textTelefone.setText(alunoSelecionado.getTelefone());
        textRG.setDisable(true);
    }
    
    public Aluno getAlunoSelecionado(){
        return alunoSelecionado;
    }
}
