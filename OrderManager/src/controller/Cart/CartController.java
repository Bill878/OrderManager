package controller.Cart;

import au.edu.uts.ap.javafx.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;
// import model.Exceptions.*;
import java.io.IOException;
// import java.util.logging.*;

public class CartController extends Controller<Cart>{
    @FXML private Button addBtn;
    @FXML private Button viewBtn;
    @FXML private Button checkoutBtn;
    @FXML private TableView<Product> productsTv;
    @FXML private Label headerLbl;

    @FXML private void initialize() {
        ObservableList<Product> catalogue = model.getCatalogue();
        productsTv.setItems(catalogue);

        headerLbl.setText("Ordering from " + model.getSupplier().getName());
        productsTv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        productsTv.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                addBtn.setDisable(productsTv.getSelectionModel().getSelectedItems().isEmpty());
            }
        );
    }

    @FXML private void close() {stage.close();}

    @FXML private void handleAdd() throws IOException {
        ObservableList<Product> products = FXCollections.observableArrayList(productsTv.getSelectionModel().getSelectedItems());
        for (Product product : products) {
            Order order = new Order(product, 1, model);
            ViewLoader.showStage(order, "/view/Cart/AddToCartView.fxml", "Adding to cart", new FixedStage("/image/cart_icon.png"));
            model.getCatalogue().remove(product);
        }
        productsTv.getSelectionModel().clearSelection();
    }

    @FXML private void handleView() throws IOException{
        Cart cart = model;
        ViewLoader.showStage(cart, "/view/Cart/ViewCartView.fxml", "View Cart", new FixedStage("/image/cart_icon.png"));
    }

    @FXML private void handleCheckout() {
        model.getSupplier().processCart(model);
        Organisation.getLoggedInUser().addPurchase(model);
        stage.close();
    }
}
