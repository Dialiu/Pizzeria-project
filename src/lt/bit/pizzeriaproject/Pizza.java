package lt.bit.pizzeriaproject;

import java.util.HashMap;
import java.util.Map;

public class Pizza extends MenuItem implements IFood{

    private final int paruosimoLaikas = 10;
    private Sauce sauce;

    public Sauce getSauce() {
        return sauce;
    }

    public void setSauce(Sauce sauce) {
        this.sauce = sauce;
    }

    public Pizza(String name, String info, double price) {
        super(name, info, price);
    }
    public Pizza(String name, String info, double price, Sauce sauce) {
        super(name, info, price);
        this.sauce = sauce;
    }

    @Override
    public HashMap<String, String> getFoodFields() {
        HashMap<String, String> fields = new HashMap<>();
        fields.put("name",this.getName());
        fields.put("info",this.getInfo());
        fields.put("price", ""+getPrice());
        if(this.sauce!=null)
        fields.put("sauce", sauce.name());
        return fields;
    }

    @Override
    public int getParuosimoLaikas() {
        return paruosimoLaikas;
    }

    @Override
    public String toString() {
        String sauce = this.sauce!=null?getSauce().name():"pasirinktina";
        String ret = "Pizza"+super.toString()+" Sauce:"+sauce;
        return ret;
    }
}
