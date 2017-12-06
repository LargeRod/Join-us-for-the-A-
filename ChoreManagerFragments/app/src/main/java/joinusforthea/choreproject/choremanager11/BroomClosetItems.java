package joinusforthea.choreproject.choremanager11;

/**
 * Created by Digo9 on 2017-12-04.
 */

public class BroomClosetItems {

    private String _id;
    private String _productname;
    private String _price;

    public BroomClosetItems(){

    }
    public BroomClosetItems(String id, String productname){
        _id = id;
        _productname = productname;
    }
    public BroomClosetItems(String productname){
        _productname = productname;
    }

    public void setId(String id) {
        _id = id;
    }
    public String getId() {
        return _id;
    }
    public void setItemName(String productname) {
        _productname = productname;
    }
    public String getItemName() {
        return _productname;
    }
    public void setPrice(String price) {
        _price = price;
    }
    public String getPrice() {
        return _price;
    }
}

