import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order implements Serializable {
    @Serial
    private static final long serialVersionUID = -7643843749170825805L;
    private static int orderNumberGenerator = 0;
    private int orderNumber;
    private Item item;
    private int quantity;
    private final static Map<Item, Integer> itemsWithQuantities = new HashMap<>();

    public Order(Item item, int quantity) {
        this.orderNumber = orderNumberGenerator++;
        itemsWithQuantities.put(item, quantity);
        this.item = item;
        this.quantity = quantity;
    }

    public int getOrderNumber() {
        return orderNumber;
    }
    public Item getItem() {
        return item;
    }


    @Override
    public String toString() {
        return "Name - " + item.getName() + "\nPrice - " + item.getPrice() + "\nCategory - " + item.getCategory() + "\nQuantity - " + quantity;


    }



}













