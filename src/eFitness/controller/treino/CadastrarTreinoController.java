package efitness.controller.treino;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import efitness.model.Aluno;
import efitness.model.Treino;
import efitness.negocio.AlunoNegocio;
import efitness.negocio.NegocioException;
import efitness.negocio.TreinoNegocio;
import java.time.ZoneId;
import java.util.Date;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;


public class CadastrarTreinoController implements Initializable {
    private TreinoNegocio treinoNegocio;
    private AlunoNegocio alunoNegocio = new AlunoNegocio();
    private List<Aluno> listaAlunos;
    private ObservableList<Aluno> observableListAlunos;
    private Aluno alunoSelecionado;
    private Treino treinoSelecionado;
    
    @FXML private ComboBox<Aluno> idAluno;
    @FXML private Button btnCancelar;
    @FXML private Button btnSalvar;
    @FXML private DatePicker datepicker;
    @FXML private TextField objetivo;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        treinoNegocio = new TreinoNegocio();
        listarAlunos();
    }
    
    @FXML
    public void listarAlunos(){
        listaAlunos = alunoNegocio.listar();
        observableListAlunos = FXCollections.observableArrayList();
        
        
        for(Aluno aluno : listaAlunos){
            Aluno a = new Aluno(aluno.getId(), aluno.getNome());
            observableListAlunos.add(a);
        }

        idAluno.setItems(observableListAlunos);
        
        idAluno.setConverter(new StringConverter<Aluno>() {
            @Override
            public String toString(Aluno object) {
                return object.getNome();
            }

            @Override
            public Aluno fromString(String string) {
                return idAluno.getItems()
                              .stream()
                              .filter(a -> a.getNome().equals(string)).findFirst().orElse(null);
            }
        });
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

    
     public void setTreinoSelecionada(Treino treinoSelecionado) {
        this.treinoSelecionado = treinoSelecionado;
        setAlunoSelecionado(treinoSelecionado.getAluno());
        datepicker.setValue(treinoSelecionado.getData().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        objetivo.setText(String.valueOf(treinoSelecionado.getObjetivo()));     
    }
    
    public void setAlunoSelecionado(Aluno alunoSelecionado) {
      this.alunoSelecionado = alunoSelecionado;
    }
    
    
    @FXML
    public void salvarDados() throws NegocioException{
        Stage stage = (Stage) btnSalvar.getScene().getWindow();
        Aluno alunoSelecionad = idAluno.getValue();
        Date dataInicio = Date.from(datepicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        String obj = objetivo.getText();
        
        if(confirmarAcao())
            treinoNegocio.salvar(new Treino(alunoSelecionad, dataInicio, obj));
        
        stage.close();
    }
    
}

