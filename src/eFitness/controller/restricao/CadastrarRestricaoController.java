package efitness.controller.restricao;

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
import efitness.model.Restricao;
import efitness.negocio.AlunoNegocio;
import efitness.negocio.NegocioException;
import efitness.negocio.RestricaoNegocio;
import java.time.ZoneId;
import java.util.Date;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;


public class CadastrarRestricaoController implements Initializable {
    private RestricaoNegocio restricaoNegocio;
    private AlunoNegocio alunoNegocio = new AlunoNegocio();
    private List<Aluno> listaAlunos;
    private ObservableList<Aluno> observableListAlunos;
    private Aluno alunoSelecionado;
    
    @FXML private ComboBox<Aluno> idAluno;
    @FXML private TextField cid;
    @FXML private TextField causa;
    @FXML private TextField descricao;
    @FXML private Button btnCancelar;
    @FXML private Button btnSalvar;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        restricaoNegocio = new RestricaoNegocio();
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

    @FXML
    public void salvarDados() throws NegocioException{
        Stage stage = (Stage) btnSalvar.getScene().getWindow();
        Aluno alunoSelecionado = idAluno.getValue();
        String _cid = cid.getText();
        String _causa = causa.getText();
        String _descricao = descricao.getText();
        
        if(confirmarAcao())
            restricaoNegocio.salvar(new Restricao(alunoSelecionado, _cid, _causa, _descricao));
        
        stage.close();
    }
    
     public void setAlunoSelecionado(Aluno alunoSelecionado){
        this.alunoSelecionado = alunoSelecionado;        
    }
}

