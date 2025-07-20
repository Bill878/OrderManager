package controller.User;

import au.edu.uts.ap.javafx.*;
// import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;
// import model.Exceptions.*;
// import java.util.logging.*;

public class OrderHistoryController extends Controller<User>{
    @FXML private ListView<Cart> ordersLv;

    @FXML private void close() { stage.close();} 

    @FXML private void initialize() {
        ordersLv.setItems(model.getPurchaseHistory());
    }

}
