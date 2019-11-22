package lt.bit.pizzeriaproject;

import java.util.HashMap;
import java.util.Map;

public class Tortilla extends MenuItem implements IFood {
    private final int paruosimoLaikas = 12;
    Sauce sauce;

    public Tortilla(String name, String info, double price) {
        super(name, info, price);
    }

    public Tortilla(String name, String info, double price, Sauce sauce) {
        super(name, info, price);
        this.sauce = sauce;
    }

    @Override
    public HashMap<String, String> getFoodFields() {
        HashMap<String, String> fields = new HashMap<>();
        fields.put("name",this.getName());
        fields.put("info",this.getInfo());
        fields.put("price", ""+getPrice());
        fields.put("sauce", sauce.name());
        return fields;
    }

    @Override
    public int getParuosimoLaikas() {
        return this.paruosimoLaikas;
    }

    @Override
    public void setSauce(Sauce sauce) {
        this.sauce = sauce;
    }
    @Override
    public String toString() {
        return "Tortilia"+super.toString()+"Sauce:"+(sauce!=null?sauce.name():"pasirinktina");
    }
}
