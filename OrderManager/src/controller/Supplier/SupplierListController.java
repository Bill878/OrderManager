package controller.Supplier;

import au.edu.uts.ap.javafx.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;
import java.io.IOException;


public class SupplierListController extends Controller<Suppliers> {
    @FXML private ListView<Supplier> suppliersLv;
    @FXML private Button shopBtn;

    


    @FXML private void close() {stage.close();}
    @FXML private void handleShop() throws IOException {
        Supplier supplier = getSelectedSupplier();
        stage.close();
        if (Organisation.getLoggedInUser() instanceof Customer) {
            ViewLoader.showStage(supplier, "/view/Supplier/SupplierCustomerView.fxml", "Supplier: " + supplier.getRegion(), new FixedStage("/image/supplier_icon.png"));    
        } else {
            ViewLoader.showStage(supplier, "/view/Supplier/SupplierManagerView.fxml", "Supplier: " + supplier.getRegion(), new FixedStage("/image/supplier_icon.png"));    

        }


    }

    @FXML
    private void initialize() {
        User user = Organisation.getLoggedInUser();

        if (user instanceof Manager) {
            Manager manager = (Manager) user;  // Cast the user to Manager
            suppliersLv.setItems(manager.getSuppliers().getSuppliers());
        } else {
            suppliersLv.setItems(model.getSuppliers());
        }
        suppliersLv.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> shopBtn.setDisable(newValue == null));

    }

    private Supplier getSelectedSupplier() {
        return suppliersLv.getSelectionModel().getSelectedItem();
    }
    


}
