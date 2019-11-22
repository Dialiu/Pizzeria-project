package lt.bit.pizzeriaproject;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public abstract class MenuItem {
    private String name;
    private String info;
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public MenuItem(String name, String info, double price) {
        this.name = name;
        this.info = info;
        this.price = price;
    }

    @Override
    public String toString() {
        return /*":"+name+" "+
                "info:"+info+
                "; price:"+price+"EUR"+
                "";*/
                ":{" +
                        "name:" + name +
                        ",info:" + info +
                        ",price:" + price +
                        "}";
    }

    public static MenuItem GetOrderCopy(MenuItem item) {
        try {
            Class cls = item.getClass();

            Constructor construct = cls.getDeclaredConstructor(String.class, String.class, double.class);

            Field[] fields = cls.getDeclaredFields();

            MenuItem ret = (MenuItem) construct.newInstance(item.name, item.info, item.price);

            for (Field f : fields) {
                f.setAccessible(true);
                f.set(ret, f.get(item));
            }

            return ret;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}

