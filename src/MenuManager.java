import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class MenuManager implements Serializable {
    @Serial
    private static final long serialVersionUID = 7791753913464537076L;
    private Map<String, Item> menu;

    public MenuManager() {
        this.menu = new TreeMap<>();
    }

    public void addMenuItem() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Item Name - ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Item name cannot be empty.");
            return;
        }


        double price = 0;
        boolean validPrice = false;
        while (!validPrice) {
            System.out.print("Enter Item Price - ");
            try {
                price = Double.parseDouble(scanner.nextLine());
                validPrice = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid price format. Please enter a valid number.");
            }
        }


        Category category = null;
        boolean validCategory = false;
        while (!validCategory) {
            System.out.print("Enter Item Category (DRINKS, SNACKS, MEALS) - ");
            String categoryInput = scanner.nextLine().toUpperCase();
            try {
                category = Category.valueOf(categoryInput);
                validCategory = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid category. Please enter DRINKS, SNACKS, or MEALS.");
            }
        }


        Item newItem = createItem(name, price, category);
        menu.put(name, newItem);

        System.out.println("Item added successfully.");
    }

    private Item createItem(String name, double price, Category category) {

        switch (category) {
            case DRINKS:
                return new Drinks(name, price);
            case SNACKS:
                return new Snacks(name, price);
            case MEALS:
                return new Meals(name, price);
            default:
                throw new IllegalArgumentException("Unsupported category: " + category);
        }
    }

    public void displayMenu() {
        System.out.println("Menu: ");
        for (Map.Entry<String, Item> entry : menu.entrySet()) {
            Item item = entry.getValue();
            System.out.println("Name: " + item.getName());
            System.out.println("Price: " + item.getPrice());
            System.out.println("Category: " + item.getCategory());
            System.out.println("------------");
        }
    }

    public void removeItem() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the name of the item to remove: ");
        String itemName = scanner.nextLine();

        if (menu.containsKey(itemName)) {
            Item itemToRemove = menu.get(itemName);
            System.out.println("Item to be removed:");
            System.out.println("Name: " + itemToRemove.getClass().getName());
            System.out.println("Price: " + itemToRemove.getClass().getName());
            System.out.println("Category: " + itemToRemove.getCategory());


            System.out.print("Do you want to remove this item? (yes/no): ");
            String confirmation = scanner.nextLine().toLowerCase();

            if (confirmation.equals("yes")) {
                menu.remove(itemName);
                System.out.println("Item removed successfully.");
            } else {
                System.out.println("Item removal canceled.");
            }
        } else {
            System.out.println("Item not found in the menu.");
        }
    }

    public Item getMenuItem(String itemName) {
        for (Item item : menu.values()) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    public void updateMenuItem() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the item to update: ");
        String itemName = scanner.nextLine();

        if (menu.containsKey(itemName)) {
            Item currentItem = menu.get(itemName);

            System.out.println("Current Details for " + itemName + ":");
            System.out.println("Price: " + currentItem.getPrice());

            System.out.print("Enter new price: ");
            double newPrice = scanner.nextDouble();

            switch (currentItem.getCategory()) {
                case MEALS -> menu.replace(itemName, new Meals(itemName, newPrice));
                case DRINKS -> menu.replace(itemName, new Drinks(itemName, newPrice));
                case SNACKS -> menu.replace(itemName, new Snacks(itemName, newPrice));
            }
            System.out.println("Menu item updated successfully.");
        } else {
            System.out.println("Item not found in the menu.");
        }
    }


}

