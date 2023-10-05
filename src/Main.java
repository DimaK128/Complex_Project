import java.util.Arrays;
import java.util.Scanner;

class Item {
    private String title;
    private int year;

    public Item(String title, int year) {
        this.title = title;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getDescription() {
        return "Title: " + title + "\nYear: " + year;
    }
}

class Collection {
    private int count;
    private Item[] items;

    public Collection() {
        this.count = 0;
        this.items = new Item[10]; // Initial capacity of 10, you can adjust it as needed
    }

    public void add(Item newItem) {
        if (count == items.length) {
            items = Arrays.copyOf(items, items.length * 2);
        }
        items[count] = newItem;
        count++;
    }

    public void remove(int index) {
        if (index >= 0 && index < count) {
            for (int i = index; i < count - 1; i++) {
                items[i] = items[i + 1];
            }
            count--;
        } else {
            System.out.println("Invalid index.");
        }
    }

    public void printOne(int index) {
        if (index >= 0 && index < count) {
            System.out.println(items[index].getDescription());
        } else {
            System.out.println("Invalid index.");
        }
    }

    public void printAll() {
        for (int i = 0; i < count; i++) {
            System.out.println("Item " + (i + 1) + ":");
            System.out.println(items[i].getDescription());
        }
    }

    public void printList() {
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + " - " + items[i].getTitle());
        }
    }

    public void sort() {
        // Bubble sort based on the numeric property (year) of the items
        for (int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count - i - 1; j++) {
                if (items[j].getYear() > items[j + 1].getYear()) {
                    Item temp = items[j];
                    items[j] = items[j + 1];
                    items[j + 1] = temp;
                }
            }
        }
    }

    public void search(String phrase) {
        for (int i = 0; i < count; i++) {
            if (items[i].getTitle().equalsIgnoreCase(phrase)) {
                System.out.println("Item " + (i + 1) + ":");
                System.out.println(items[i].getDescription());
            }
        }
    }

    public void searchByYear(int year) {
        for (int i = 0; i < count; i++) {
            if (items[i].getYear() == year) {
                System.out.println("Item " + (i + 1) + ":");
                System.out.println(items[i].getDescription());
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Collection collection = new Collection();

        System.out.println("Welcome to the hobby object database!");

        while (true) {
            System.out.println("Choose an option to proceed:");
            System.out.println("1 - Add new item");
            System.out.println("2 - Print all items");
            System.out.println("3 - Print detailed information about an item");
            System.out.println("4 - Remove an item");
            System.out.println("5 - Sort items by year");
            System.out.println("6 - Search items by title");
            System.out.println("7 - Search items by year");
            System.out.println("0 - Exit the program");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    inputItem(collection, scanner);
                    break;
                case 2:
                    collection.printAll();
                    break;
                case 3:
                    System.out.print("Enter the index of the item to print: ");
                    int indexToPrint = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    collection.printOne(indexToPrint - 1); // Adjust index to 0-based
                    break;
                case 4:
                    System.out.print("Enter the index of the item to remove: ");
                    int indexToRemove = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    collection.remove(indexToRemove - 1); // Adjust index to 0-based
                    break;
                case 5:
                    collection.sort();
                    System.out.println("Items sorted by year.");
                    break;
                case 6:
                    System.out.print("Enter the title to search: ");
                    String titleSearch = scanner.nextLine();
                    collection.search(titleSearch);
                    break;
                case 7:
                    System.out.print("Enter the year to search: ");
                    int yearSearch = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    collection.searchByYear(yearSearch);
                    break;
                case 0:
                    System.out.println("Exiting the program.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    public static void inputItem(Collection collection, Scanner scanner) {
        System.out.print("Enter item title: ");
        String title = scanner.nextLine();

        System.out.print("Enter item year: ");
        int year = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Item newItem = new Item(title, year);
        collection.add(newItem);
        System.out.println("Item added to the collection.");
    }
}
