package lt.bit.pizzeriaproject;

public class SoftDrink extends MenuItem implements IDrink {

    public SoftDrink(String name, String info, double price) {
        super(name, info, price);
    }

    @Override
    public String getInfo() {
        return super.getInfo();
    }

    @Override
    public double getCost() {
        return getPrice();
    }

    @Override
    public boolean isAlcoholic() {
        return false;
    }

    @Override
    public String toString() {
        return "SoftDrink"+super.toString();
    }
}
