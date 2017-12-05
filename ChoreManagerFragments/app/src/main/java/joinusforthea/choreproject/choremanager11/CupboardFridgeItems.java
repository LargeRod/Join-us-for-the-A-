package joinusforthea.choreproject.choremanager11;

import android.widget.EditText;

/**
 * Created by Digo9 on 2017-12-03.
 */

public class CupboardFridgeItems {

    private String _id;
    private String _productname;
    private String _price;

    public CupboardFridgeItems() {
    }

//    public CupboardFridgeItems(String productname, String price) {
//        _productname = productname;
//        _price = price;
//    }

    public CupboardFridgeItems(String productname){
        _productname = productname;
    }

    public void setId(String id) {
        _id = id;
    }
    public String getId() {
        return _id;
    }
    public void setProductName(String productname) {
        _productname = productname;
    }
    public String getProductName() {
        return _productname;
    }
    public void setPrice(String price) {
        _price = price;
    }
    public String getPrice() {
        return _price;
    }
}
