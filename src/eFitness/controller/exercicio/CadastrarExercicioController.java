package eFitness.controller.exercicio;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import eFitness.model.Exercicio;
import eFitness.negocio.NegocioException;
import eFitness.negocio.ExercicioNegocio;

/**
 *
 * @author gustavo.fonseca
 */
public class CadastrarExercicioController implements Initializable {
    private ExercicioNegocio exercicioNegocio;
    private Exercicio exercicioSelecionado;
       
    @FXML private Button btnSalvar;
    @FXML private Button btnCancelar;
    @FXML private TextField textNomeExercicio;            
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exercicioNegocio = new ExercicioNegocio();        
    }
    
    @FXML
    public void salvarDados(){
        Stage stage = (Stage) btnSalvar.getScene().getWindow();
        
        if (exercicioSelecionado == null) {
            if (!textNomeExercicio.getText().isEmpty()) {
                String tipoOpcao = "cadastrar";
                if (confirmarAcao(tipoOpcao)) {
                    try {
                        exercicioNegocio.salvar(new Exercicio(textNomeExercicio.getText()));
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
                    exercicioSelecionado.setNome(textNomeExercicio.getText());
                    exercicioNegocio.atualizar(exercicioSelecionado);
                    stage.close();
                } catch (NegocioException e) {
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

    public void setExercicioSelecionado(Exercicio exercicioSelecionado) {
        this.exercicioSelecionado = exercicioSelecionado;        
        textNomeExercicio.setText(exercicioSelecionado.getNome());        
    }
}
