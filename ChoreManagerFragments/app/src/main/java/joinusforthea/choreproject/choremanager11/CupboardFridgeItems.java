package joinusforthea.choreproject.choremanager11;

/**
 * Created by Digo9 on 2017-12-03.
 */

public class CupboardFridgeItems {

    private String _id;
    private String _foodName;

    public CupboardFridgeItems() {
    }

    public CupboardFridgeItems(String id, String foodName) {
        setId(id);
        setfoodName(foodName);

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
