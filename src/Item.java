import java.io.Serial;
import java.io.Serializable;

public abstract class Item implements Serializable {
    @Serial
    private static final long serialVersionUID = -2458345455014884025L;
    public static int idGeneration = 1;
    public int id = idGeneration++;
    private String name;
    private double price;


    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
    public abstract Category getCategory();

    public double fee() {
        return price * 0.1;
    }
    public double tax() {
        return price * 0.2;
    }
    public double tip() {
        return fee();
    }



}
