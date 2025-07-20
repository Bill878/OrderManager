package controller.Supplier;

import au.edu.uts.ap.javafx.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;

// import model.Exceptions.*;
// import java.util.logging.*;

public class ManageProductsController extends Controller<Products> {
    @FXML private Button removeBtn;
    @FXML private Button delistBtn;
    @FXML private ListView<Product> productsLv;
    



    @FXML private void initialize() {
        productsLv.setItems(model.getAllProducts());
        productsLv.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> delistBtn.setDisable(!newValue.isAvailable()));
    }

    @FXML private void close() {stage.close();}

    @FXML private void handleRemove() {
        Product selectedItem = productsLv.getSelectionModel().getSelectedItem();
        model.getAllProducts().remove(selectedItem);
    }

    @FXML private void handleDelist() {
        Product selectedItem = productsLv.getSelectionModel().getSelectedItem();
        selectedItem.delist();
        delistBtn.setDisable(true);
    }


}
