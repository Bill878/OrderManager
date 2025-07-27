package controller;

import au.edu.uts.ap.javafx.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
// import javafx.stage.Stage;
import model.*;
import model.Exceptions.*;

import java.io.IOException;
// import java.util.logging.*;

public class LoginController extends Controller<Organisation>{
    @FXML private Button loginBtn;
    @FXML private TextField usernameTf;
    @FXML private PasswordField passwordPf;


    @FXML
    private void close() {
        stage.close();    
    }



    @FXML
    public void handleLogin() throws IOException {
        String username = usernameTf.getText();
        String password = passwordPf.getText();
        try {
            User loggedInUser = model.getUsers().validateUser(username, password);
            Organisation.setLoggedInUser(loggedInUser);
            stage.close();
            if (loggedInUser instanceof Customer) {
                ViewLoader.showStage(loggedInUser, "/view/User/UserDashboardView.fxml", 
                "Customer Dashboard", new FixedStage("/image/user_icon.png"));
            } else {
                ViewLoader.showStage(loggedInUser, "/view/User/UserDashboardView.fxml", 
                "Manager Dashboard", new FixedStage("/image/user_icon.png"));

            }
        } catch (NoSuchUserException e) {
            ErrorModel errorModel = new ErrorModel(e, "Invalid credentials");
            ViewLoader.showStage(errorModel, "/view/ErrorView.fxml", "Error", 
                                 new FixedStage("/image/error_icon.png"));
        }
    }
        
    @FXML
    private void initialize() {
            usernameTf.textProperty().addListener((observable, oldValue, newValue) -> {
                checkFields();
            });

            passwordPf.textProperty().addListener((observable, oldValue, newValue) -> {
                checkFields();
            });
    }
    
    @FXML private void checkFields() {
        boolean isTfEmpty = usernameTf.getText().isEmpty();
        boolean isPfEmpty = passwordPf.getText().isEmpty();
        loginBtn.setDisable((isTfEmpty || isPfEmpty));
    }
}
