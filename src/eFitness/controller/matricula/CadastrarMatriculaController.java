package efitness.controller.matricula;

import efitness.model.Aluno;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import efitness.model.Matricula;
import efitness.negocio.MatriculaNegocio;
import java.math.BigDecimal;
import javafx.scene.control.DatePicker;

/**
 *
 * @author gustavo.fonseca
 */
public class CadastrarMatriculaController implements Initializable {
    private MatriculaNegocio matriculaNegocio;
    private Matricula matriculaSelecionada;
    private Aluno alunoSelecionado;
       
    @FXML private Button btnSalvar;
    @FXML private Button btnCancelar;
    @FXML private DatePicker datePickerData;
    @FXML private TextField textAluno;
    @FXML private TextField textValor;
    @FXML private TextField textPeriodicidade;
    @FXML private DatePicker datePickerVencimento;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        matriculaNegocio = new MatriculaNegocio();        
    }
    
    @FXML
    public void salvar(){
      Stage stage = (Stage) btnSalvar.getScene().getWindow();

      if (matriculaSelecionada == null) {
        String tipoOpcao = "cadastrar";
        if (confirmarAcao(tipoOpcao)) {
            try {                        
                matriculaNegocio.salvar(new Matricula(
                  alunoSelecionado,
                  datePickerData.getValue(),
                  BigDecimal.valueOf(Double.parseDouble(textValor.getText())),
                  Integer.parseInt(textPeriodicidade.getText()),
                  datePickerVencimento.getValue()
                ));
                stage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
      } else {
        String tipoOpcao = "editar";
        if (confirmarAcao(tipoOpcao)) {
            try {
                matriculaSelecionada.setData(datePickerData.getValue());
                matriculaSelecionada.setValor(BigDecimal.valueOf(Double.parseDouble(textValor.getText())));
                matriculaSelecionada.setPeriodicidade(Integer.parseInt(textPeriodicidade.getText()));
                matriculaSelecionada.setVencimento(datePickerVencimento.getValue());
                matriculaNegocio.atualizar(matriculaSelecionada);
                stage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
      }
    }
    
    @FXML
    public void cancelar() {
      Stage stage = (Stage) btnCancelar.getScene().getWindow();    
      stage.close();
    }
    
    public boolean confirmarAcao(String tipo){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Você deseja realmente "+ tipo +" essa entrada?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        
        if (alert.getResult() == ButtonType.YES) {
            return true;
        }
        return false;
    }

    public void setMatriculaSelecionada(Matricula matriculaSelecionada) {
        this.matriculaSelecionada = matriculaSelecionada;
        setAlunoSelecionado(matriculaSelecionada.getAluno());
        datePickerData.setValue(matriculaSelecionada.getData());
        textValor.setText(String.valueOf(matriculaSelecionada.getValor()));
        textPeriodicidade.setText(String.valueOf(matriculaSelecionada.getPeriodicidade()));
        datePickerVencimento.setValue(matriculaSelecionada.getVencimento());        
    }
    
    public void setAlunoSelecionado(Aluno alunoSelecionado) {
      this.alunoSelecionado = alunoSelecionado;
      textAluno.setText(alunoSelecionado.getNome());
    }
}
