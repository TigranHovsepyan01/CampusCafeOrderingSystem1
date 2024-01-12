import java.io.Serial;
import java.io.Serializable;

public class Drinks extends Item implements Serializable{
    @Serial
    private static final long serialVersionUID = -3336132867081235341L;

    public Drinks(String name, double price) {
        super(name, price);
    }

    @Override
    public Category getCategory() {
        return Category.DRINKS;
    }
}
