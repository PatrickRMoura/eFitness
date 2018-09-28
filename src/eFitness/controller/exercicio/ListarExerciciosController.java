package eFitness.controller.exercicio;

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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import eFitness.model.Exercicio;
import eFitness.negocio.NegocioException;
import eFitness.negocio.ExercicioNegocio;

/**
 *
 * @author Patrick Moura
 */
public class ListarExerciciosController implements Initializable{
    private ExercicioNegocio exercicioNegocio;
    private List<Exercicio> listaExercicios;
    private Exercicio exercicioSelecionado;
    private ObservableList<Exercicio> observableListaExercicios;
    
    @FXML private VBox painelExercicio;
    @FXML private TableView<Exercicio> tableViewExercicio;
    @FXML private TableColumn<Exercicio, String> tableColumnId;
    @FXML private TableColumn<Exercicio, String> tableColumnNome;    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exercicioNegocio = new ExercicioNegocio();
        listarExercicios();
    }
    
    @FXML
    public void listarExercicios() {                        
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        listaExercicios = exercicioNegocio.listar();        
        observableListaExercicios = FXCollections.observableArrayList(listaExercicios);
        tableViewExercicio.setItems(observableListaExercicios);
    }
    
    @FXML
    public void cadastrarExercicio() throws IOException{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(eFitness.eFitness.class.getResource("view/exercicio/CadastrarExercicio.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(painelExercicio.getScene().getWindow());
        stage.showAndWait();
        this.listarExercicios();
    }
    
    @FXML
    public void editarDados(){
        exercicioSelecionado = tableViewExercicio.getSelectionModel().getSelectedItem();        
        if (exercicioSelecionado != null) {
            try {
                FXMLLoader loader = new FXMLLoader(eFitness.eFitness.class.getResource("view/exercicio/CadastrarExercicio.fxml"));
                Parent root = (Parent) loader.load();
                
                CadastrarExercicioController controller = (CadastrarExercicioController) loader.getController();
                controller.setExercicioSelecionado(exercicioSelecionado);
                
                Stage dialogStage = new Stage();
                dialogStage.setScene(new Scene(root));
                dialogStage.initModality(Modality.APPLICATION_MODAL);
                dialogStage.initOwner(painelExercicio.getScene().getWindow());
                dialogStage.showAndWait();
                this.listarExercicios();
            } catch (IOException ex) {
                Logger.getLogger(ListarExerciciosController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @FXML
    public void deletarExercicio(ActionEvent event) throws IOException {
        exercicioSelecionado = tableViewExercicio.getSelectionModel().getSelectedItem();
        if (exercicioSelecionado != null) {
            Alert alert = new Alert(Alert.AlertType.WARNING,"Você deseja realmente excluir essa entrada?",ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                try {
                    exercicioNegocio.deletar(exercicioSelecionado);
                    this.listarExercicios();
                } catch (NegocioException ex) {
                    Logger.getLogger(ListarExerciciosController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
