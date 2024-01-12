import java.io.Serial;
import java.io.Serializable;

public class Snacks extends Item implements Serializable {
    @Serial
    private static final long serialVersionUID = -6037120274585647893L;
    private String name;
    private double price;
    public Snacks(String name, double price) {
        super(name, price);
    }
    @Override
    public Category getCategory() {
        return Category.SNACKS;
    }
}
