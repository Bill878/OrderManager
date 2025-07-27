package controller.Cart;

import au.edu.uts.ap.javafx.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;
import model.Exceptions.*;

import java.io.IOException;
// import java.util.logging.*;

public class AddToCartController extends Controller<Order> {
    @FXML private Button addBtn;
    @FXML private TextField amountTf;
    @FXML private Label headerLbl;



    @FXML private void initialize() {
        headerLbl.setText("Adding " + model.getProduct().getName());
        
    }



    @FXML private void handleAdd() throws IOException{
        try {
            int amount = Integer.parseInt(amountTf.getText());
            model.setQuantity(amount);
            model.getCart().addOrder(model);
            stage.close();            
            
        } catch (InvalidQuantityException e) {
            ErrorModel errorModel = new ErrorModel(e, "Invalid stock entered");
            ViewLoader.showStage(errorModel, "/view/ErrorView.fxml", "Error", new FixedStage("/image/error_icon.png"));

        }        

    }


    
}
