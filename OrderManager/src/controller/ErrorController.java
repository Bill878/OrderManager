package controller;

import au.edu.uts.ap.javafx.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
// import model.*;
import model.Exceptions.*;

// import java.io.IOException;
// import java.util.logging.*;
// import javafx.stage.Stage;


public class ErrorController extends Controller<ErrorModel> {
    @FXML private Label errorLbl;
    @FXML private Label descLbl;

    @FXML private void initialize() {
        errorLbl.setText(model.getException());
        descLbl.setText(model.getMessage());
    }

    @FXML public void close() {
            stage.close();
    }
}
