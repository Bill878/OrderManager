package controller.Cart;

import au.edu.uts.ap.javafx.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;
// import model.Exceptions.*;
// import java.util.logging.*;

public class ViewCartController extends Controller<Cart>{
    @FXML private Button removeBtn;
    @FXML private TableView<Order> cartTv;


    @FXML private void initialize() {
        cartTv.setItems(model.getOrders());
        cartTv.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> removeBtn.setDisable(newValue == null));
    }

    @FXML private void close() {
        stage.close();
    }

    @FXML 
    private void handleRemove() {
        Order selectedOrder = cartTv.getSelectionModel().getSelectedItem();
            model.removeOrder(selectedOrder);
            model.getCatalogue().add(selectedOrder.getProduct());
    }
    



}
