package lt.bit.pizzeriaproject;

public class Water extends MenuItem implements IDrink {
    private WaterType type;

    public WaterType getType() {
        return type;
    }

    public void setType(WaterType type) {
        this.type = type;
    }

    @Override
    public String getInfo() {
        return super.getInfo()+" "+type.name();
    }

    @Override
    public double getCost() {
        return getPrice();
    }

    @Override
    public boolean isAlcoholic() {
        return false;
    }

    public Water(String name, String info, double price) {
        super(name, info, price);
    }

    public Water(String name, String info, double price, WaterType type) {
        super(name, info, price);
        this.type = type;
    }

    @Override
    public String toString() {
        String type = this.type!=null?getType().name():"";
        return "Water "+ type+super.toString();
    }
}
enum WaterType{
    STILL,
    SPARKLING;
    }
