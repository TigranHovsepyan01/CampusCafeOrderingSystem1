import java.io.Serial;
import java.io.Serializable;

public class Meals extends Item  implements Serializable {
    @Serial
    private static final long serialVersionUID = -3820554313038195248L;
    public Meals(String name, double price) {
        super(name, price);
    }
    @Override
    public Category getCategory() {
        return Category.MEALS;
    }
}
