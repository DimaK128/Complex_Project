import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Item {
    private String title;
    private int year;
    private double rating;

    public Item(String title, int year, double rating) {
        this.title = title;
        this.year = year;
        this.rating = rating;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public double getRating() {
        return rating;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return "Title: " + title + "\nYear: " + year + "\nRating: " + rating;
    }
}

class CollectionManager {
    private ArrayList<Item> items;

    public CollectionManager() {
        items = new ArrayList<>();
    }

    public void add(Item item) {
        items.add(item);
    }

    public void remove(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
        } else {
            System.out.println("Invalid index.");
        }
    }

    public void printOne(int index) {
        if (index >= 0 && index < items.size()) {
            System.out.println(items.get(index).getDescription());
        } else {
            System.out.println("Invalid index.");
        }
    }

    public void printAll() {
        for (Item item : items) {
            System.out.println(item.getDescription() + "\n");
        }
    }

    public void printList() {
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + " - " + items.get(i).getTitle());
        }
    }

    public void sort() {
        items.sort((item1, item2) -> Integer.compare(item1.getYear(), item2.getYear()));
    }

    public void search(String phrase) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getTitle().contains(phrase)) {
                System.out.println(i + " - " + items.get(i).getDescription());
            }
        }
    }

    public void searchByYear(int year) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getYear() == year) {
                System.out.println(i + " - " + items.get(i).getDescription());
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        CollectionManager collectionManager = new CollectionManager();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the hobby object database!");
        while (true) {
            System.out.println("Choose an option to proceed:");
            System.out.println("1 - Print item list");
            System.out.println("2 - Add a new item");
            System.out.println("3 - Remove an item");
            System.out.println("4 - Sort items by year");
            System.out.println("5 - Search items by title");
            System.out.println("6 - Search items by year");
            System.out.println("7 - Print detailed item list");
            System.out.println("0 - Exit the program");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    collectionManager.printList();
                    break;
                case 2:
                    System.out.print("Enter item title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter item year: ");
                    int year = scanner.nextInt();
                    System.out.print("Enter item rating: ");
                    double rating = scanner.nextDouble();
                    scanner.nextLine(); // Consume the newline character
                    Item newItem = new Item(title, year, rating);
                    collectionManager.add(newItem);
                    break;
                case 3:
                    System.out.print("Enter the index of the item to remove: ");
                    int indexToRemove = scanner.nextInt();
                    collectionManager.remove(indexToRemove);
                    break;
                case 4:
                    collectionManager.sort();
                    break;
                case 5:
                    System.out.print("Enter a search phrase: ");
                    String searchPhrase = scanner.nextLine();
                    collectionManager.search(searchPhrase);
                    break;
                case 6:
                    System.out.print("Enter a year to search for: ");
                    int searchYear = scanner.nextInt();
                    collectionManager.searchByYear(searchYear);
                    break;
                case 7:
                    collectionManager.printAll();
                    break;
                case 0:
                    System.out.println("Exiting the program.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
