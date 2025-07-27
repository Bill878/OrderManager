package controller.Supplier;

import au.edu.uts.ap.javafx.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;
// import model.Exceptions.*;
import javafx.beans.binding.Bindings;

import java.io.IOException;
// import java.util.logging.*;

public class SupplierCustomerController extends Controller<Supplier>{
    @FXML private TableView<Product> productsTv; 
    @FXML private Button orderBtn;
    @FXML private Label welcomeLbl;

    @FXML private void close() {
        stage.close();
    }

    @FXML private void handleOrder() throws IOException{
        Cart cart = new Cart(model, Organisation.getLoggedInUser());
        ViewLoader.showStage(cart, "/view/Cart/CartView.fxml", "Cart", new FixedStage("/image/cart_icon.png"));


    }

    @FXML private void initialize() {

        welcomeLbl.textProperty().bind(Bindings.createStringBinding(
            () -> String.format("Welcome to %s (Total Profit: %s)", model.getName(), model.getProfit()), 
            model.profitProperty()));

        productsTv.setItems(model.getProducts().getAllProducts());

    }
}
