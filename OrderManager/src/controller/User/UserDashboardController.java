package controller.User;

import au.edu.uts.ap.javafx.*;
// import controller.Supplier.SupplierListController;
// import javafx.collections.ObservableList;
import javafx.fxml.FXML;
// import javafx.fxml.FXMLLoader;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
import javafx.scene.control.*;
// import javafx.stage.Stage;
import model.*;
// import model.Exceptions.*;

import java.io.IOException;
// import java.util.logging.*;

public class UserDashboardController extends Controller<Organisation> {
    @FXML private Label welcomeLbl;
    @FXML private Button shopBtn;
    @FXML private Button orderHistoryBtn;


    @FXML public void close() { System.exit(0);}

    @FXML
    private void initialize() {
        if (Organisation.getLoggedInUser() instanceof Manager) {
            welcomeLbl.setText("Welcome to the Manager Dashboard " + Organisation.getLoggedInUser().getFirstName());
            shopBtn.setText("Manage");
        } else if (Organisation.getLoggedInUser() instanceof Customer) {
            welcomeLbl.setText("Welcome to the Customer Dashboard " + Organisation.getLoggedInUser().getFirstName());
            
        }
    }

    @FXML public void handleShop() throws IOException {
        User user = Organisation.getLoggedInUser();
            if (user instanceof Manager) {
            Suppliers suppliers = new Suppliers().seedData();
            ViewLoader.showStage(suppliers, "/view/Supplier/SupplierListView.fxml", "Supplier List", new FixedStage("/image/supplier_icon.png"));
            } else {
                Suppliers suppliers = new Suppliers().seedData();
                ViewLoader.showStage(suppliers, "/view/Supplier/SupplierListView.fxml", "Supplier List", new FixedStage("/image/supplier_icon.png"));
        
            }
    }

    @FXML public void handleOrderHistory() throws IOException {    
        ViewLoader.showStage(Organisation.getLoggedInUser(), "/view/User/OrderHistoryView.fxml", "Order History", new FixedStage("/image/user_icon.png"));

    }

}
