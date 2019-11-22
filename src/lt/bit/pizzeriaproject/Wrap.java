package lt.bit.pizzeriaproject;

import java.util.HashMap;
import java.util.Map;

public class Wrap extends MenuItem implements IFood {

    Sauce sauce;

    public Sauce getSauce() {
        return sauce;
    }

    public void setSauce(Sauce sauce) {
        this.sauce = sauce;
    }

    public Wrap(String name, String info, double price) {
        super(name, info, price);
    }

    public Wrap(String name, String info, double price, Sauce sauce) {
        super(name, info, price);
        this.sauce = sauce;
    }

    @Override
    public HashMap<String, String> getFoodFields() {
        HashMap<String, String> fields = new HashMap<>();
        fields.put("name",this.getName());
        fields.put("info",this.getInfo());
        fields.put("price", ""+getPrice());
        if(sauce!=null) fields.put("sauce", sauce.name());
        return fields;
    }

    @Override
    public int getParuosimoLaikas() {
        return 8;
    }

    @Override
    public String toString() {
        String ret = "Wrap{";
        HashMap<String, String> foodFields = getFoodFields();
        for(Map.Entry<String, String> item:foodFields.entrySet()){
            ret+=item.getKey()+":"+item.getValue()+",";
        }
        ret += "paruosimoLaikas=" + getParuosimoLaikas() +
                ", sauce=" + sauce +
                '}';
        return ret;
    }
}
