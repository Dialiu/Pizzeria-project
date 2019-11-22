package lt.bit.pizzeriaproject;

import java.util.HashMap;

public interface IFood {
    public HashMap<String, String> getFoodFields();
    public int getParuosimoLaikas();

    public void setSauce(Sauce sauce);
}
