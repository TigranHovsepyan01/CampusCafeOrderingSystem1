import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Waiter implements Serializable{
    @Serial
    private static final long serialVersionUID = -5248986560078765610L;
    private final MenuManager menuManager;
    private static List<Order> orders = new ArrayList<>();
    ;

    public Waiter(MenuManager menuManager) {
        this.menuManager = menuManager;
    }

    public void startProgram() {
        String message = """
                1. Add Item Menu
                2. Remove Item from Menu 
                3. Update Menu Item
                4. Display Menu
                5. Add Order
                6. View Orders
                7. Load State from File
                8. Exit
                """;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(message);
            String operation = scanner.nextLine();

            switch (operation) {
                case "1" -> menuManager.addMenuItem();
                case "2" -> menuManager.removeItem();
                case "3" -> menuManager.updateMenuItem();
                case "4" -> menuManager.displayMenu();
                case "5" -> addOrder(orders);
                case "6" -> viewOrders(orders);
                case "7" -> loadData();
                case "8" -> exitProgram();
                default -> System.out.println("Invalid operation. Please enter a valid option.");
            }
        }
    }

    public double getBill(Order order) {
        Item item = order.getItem();
        double totalBill = item.fee() + item.tax() + item.tip();
        return totalBill;
    }

    private void viewOrders(List<Order> orders) {
        if (orders.isEmpty()) {
            System.out.println("No Orders available: ");
            return;
        }

        System.out.println("Current orders: ");
        for (Order order : orders) {
            System.out.println(order.toString());
            System.out.println("Order Bill - " + getBill(order));

        }
        System.out.println();

    }


    @SuppressWarnings("all")
    public void loadData() {
        File file = new File("myFile");
        if (file.isFile() && file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                orders = (List<Order>) ois.readObject();
                viewOrders(orders);
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error loading data from file: " + e.getMessage());
            }
        } else {
            System.out.println("Your file doesn't exist: ");
        }

    }


    public void saveData(Order order) {
//        orders.add(order);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("myFile"))) {
            oos.writeObject(orders);
            oos.flush();
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data to file: " + e.getMessage());
        }
    }

    public void addOrder(List<Order> orders) {
        Scanner scanner = new Scanner(System.in);


        while (true) {
            menuManager.displayMenu();

            System.out.print("Enter the name of the item to add to the order (type 'done' to finish): ");
            String itemName = scanner.nextLine().trim();

            if (itemName.equalsIgnoreCase("done")) {
                break;
            }

            Item menuItem = menuManager.getMenuItem(itemName);


            if (menuItem != null) {
                int quantity = getValidQuantity(scanner);

                if (quantity > 0) {
                    Order order = new Order(menuItem, quantity);
                    System.out.println("Creating a new order. Order Number: " + order.getOrderNumber());
                    orders.add(order);
                    saveData(order);
                    System.out.println("Order added successfully:  ");


                } else {
                    System.out.println("Quantity must be greater than 0. Please enter a valid quantity.");
                }
            } else {
                System.out.println("Item not found in the menu. Please enter a valid item.");
            }
        }

    }

    private int getValidQuantity(Scanner scanner) {
        int quantity;
        while (true) {
            System.out.print("Enter the quantity: ");
            try {
                quantity = Integer.parseInt(scanner.nextLine());
                if (quantity > 0) {
                    break;
                } else {
                    System.out.println("Quantity must be greater than 0. Please enter a valid quantity.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid quantity.");
            }
        }
        return quantity;
    }

    private void exitProgram() {
        System.out.println("Good Bye!.");
        System.exit(0);
    }
}