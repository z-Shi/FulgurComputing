package ui;

import engine.Engine;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.Product;
import utils.Helper;

public class ProductController extends Controller {

    @FXML private TextField productName;
    @FXML private TextField productDescription;
    @FXML private TextField productPrice;
    @FXML private TextField productStockLevel;

    private Engine engine = Engine.getInstance();
    private Product currentProduct;
    private Helper helper = new Helper();

    public void loadProduct(int productId) {
        currentProduct = engine.getProductById(productId);

        productName.setText(currentProduct.getProductName());
        productDescription.setText(currentProduct.getDescription());
        productPrice.setText("£" + currentProduct.getPrice());
        productStockLevel.setText(String.valueOf(currentProduct.getStockLevel()));
    }

    public void orderProduct() {
        if (currentProduct.getStockLevel() == 0) {
            helper.showErrorPopup("Not Enough Stock", "You cannot order a product when the current stock level is zero!");
        } else {
            engine.addProductToBasket(currentProduct);
            if (engine.checkStockLevel(currentProduct)){
                reOrderMessage();
            }
            loadProduct(currentProduct.getProductID());
        }
    }

    public void reOrderMessage(){
        helper.showErrorPopup("Stock Reordered","Stock fell beneath minimum, more stock ordered!");
    }

}
