package joinusforthea.choreproject.choremanager11;

import android.widget.EditText;

/**
 * Created by Digo9 on 2017-12-03.
 */

public class CupboardFridgeItems {

    private String _id;
    private String _foodName;

    public CupboardFridgeItems() {
    }

    public CupboardFridgeItems(String id, String foodName) {
        _id = id;
        _foodName = foodName;

    }

    public CupboardFridgeItems(String productname){
        _foodName = productname;
    }

    public void setId(String id) {
        _id = id;
    }
    public String getId() {
        return _id;
    }
    public void setfoodName(String foodName) {
        _foodName = foodName;
    }
    public String getfoodName() {
        return _foodName;
    }

}
