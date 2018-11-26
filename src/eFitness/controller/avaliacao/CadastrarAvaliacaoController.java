package efitness.controller.avaliacao;

import efitness.controller.avaliacao.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import efitness.model.Aluno;
import efitness.model.Avaliacao;
import efitness.model.Exercicio;
import efitness.negocio.AvaliacaoNegocio;
import efitness.negocio.AlunoNegocio;
import efitness.negocio.NegocioException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;


public class CadastrarAvaliacaoController implements Initializable {
    private AvaliacaoNegocio avaliacaoNegocio = new AvaliacaoNegocio();
    private AlunoNegocio alunoNegocio = new AlunoNegocio();
    private Aluno alunoSelecionado;
    private Avaliacao avaliacaoSelecionada;
        
    @FXML private Button btnCancelar;
    @FXML private DatePicker datePickerData;
    @FXML private TextField textAluno;
    @FXML private TextField textMassaCorporal;
    @FXML private TextField textFrequenciaCardiaca;
    @FXML private TextField textPressaoArterial;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
      
    }
    
    public boolean confirmarAcao(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Você deseja realmente alterar essa entrada?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        
        if (alert.getResult() == ButtonType.YES) {
            return true;
        }
        return false;
    }
    
    @FXML
    public void cancelarDados(){
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void salvarDados() throws NegocioException{
        Stage stage = (Stage) btnCancelar.getScene().getWindow();        
        
        Date data = Date.from(datePickerData.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        
        
        if (avaliacaoSelecionada == null) {            
          String tipoOpcao = "cadastrar";
          if (confirmarAcao(tipoOpcao)) {
            try {
              avaliacaoNegocio.salvar(new Avaliacao(alunoSelecionado, 
                data,
                Double.parseDouble(textMassaCorporal.getText()),
                Double.parseDouble(textFrequenciaCardiaca.getText()),
                Double.parseDouble(textPressaoArterial.getText())
              )); 
              
            } catch (Exception e) {
                System.out.println("Errou");
            }
          }            
        } 
        else {
          String tipoOpcao = "editar";
          if (confirmarAcao(tipoOpcao)) {
            try {
              avaliacaoSelecionada.setData(data);
              avaliacaoSelecionada.setMassaCorporal(Double.parseDouble(textMassaCorporal.getText()));
              avaliacaoSelecionada.setFrequenciaCardiaca(Double.parseDouble(textFrequenciaCardiaca.getText()));
              avaliacaoSelecionada.setPressaoArterial(Double.parseDouble(textPressaoArterial.getText()));
              avaliacaoNegocio.atualizar(avaliacaoSelecionada);              
            } catch (Exception e) {
                e.printStackTrace();
            }
          }
        }
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
    
    public void setAlunoSelecionado(Aluno alunoSelecionado) {
      this.alunoSelecionado = alunoSelecionado;
      this.textAluno.setText(this.alunoSelecionado.getNome());
    }
    
    public void setAvaliacaoSelecionada(Avaliacao avaliacaoSelecionada) {
      this.avaliacaoSelecionada = avaliacaoSelecionada;
      setAlunoSelecionado(avaliacaoSelecionada.getAluno());
      textAluno.setDisable(true);      
      textFrequenciaCardiaca.setText(String.valueOf(avaliacaoSelecionada.getFrequenciaCardiaca()));
      textMassaCorporal.setText(String.valueOf(avaliacaoSelecionada.getMassaCorporal()));
      textPressaoArterial.setText(String.valueOf(avaliacaoSelecionada.getPressaoArterial()));
    }
    
}

