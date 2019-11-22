package lt.bit.pizzeriaproject;

public class Beer extends MenuItem implements IDrink {

    public Beer(String name, String info, double price) {
        super(name, info, price);
    }

    @Override
    public double getCost() {
        return getPrice();
    }


    @Override
    public boolean isAlcoholic() {
        return true;
    }

    @Override
    public String toString() {
        return "Beer"+super.toString();
    }
}
