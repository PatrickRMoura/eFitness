package efitness.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Patrick Moura
 */
public class MenuPrincipalController implements Initializable {
    
    @FXML private AnchorPane painelMenuPrincipal;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    public void cadastrarAluno(ActionEvent event) throws IOException {
        Parent root;
        Stage stage = new Stage();
        root = FXMLLoader.load(efitness.Efitness.class.getResource("view/aluno/CadastrarAluno.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(painelMenuPrincipal.getScene().getWindow());
        stage.showAndWait();
    }
    
    @FXML
    public void listarAlunos(ActionEvent event) throws IOException{
        Parent root;
        Stage stage = new Stage();
        root = FXMLLoader.load(efitness.Efitness.class.getResource("view/aluno/ListarAlunos.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(painelMenuPrincipal.getScene().getWindow());
        stage.showAndWait();
    }
    
    @FXML
    public void cadastrarExercicio(ActionEvent event) throws IOException {
        Parent root;
        Stage stage = new Stage();
        root = FXMLLoader.load(efitness.Efitness.class.getResource("view/exercicio/CadastrarExercicio.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(painelMenuPrincipal.getScene().getWindow());
        stage.showAndWait();
    }
    
    @FXML
    public void listarExercicios(ActionEvent event) throws IOException {
        Parent root;
        Stage stage = new Stage();
        root = FXMLLoader.load(efitness.Efitness.class.getResource("view/exercicio/ListarExercicios.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(painelMenuPrincipal.getScene().getWindow());
        stage.showAndWait();
    }
    
       @FXML
    public void Sair(ActionEvent event) throws IOException {
        System.exit(0);
    }
}
