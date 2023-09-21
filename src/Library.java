import java.util.HashMap;
import java.util.Map;

public class Library {
    private Map<String, Integer> booksInLibrary; // Map to store books and their quantities

    public Library() {
        booksInLibrary = new HashMap<>();
    }

    // Method to add or update a book in the library
    public void addBook(String title, String author, int quantity) {
        if (quantity <= 0) {
            System.out.println("Quantity should be greater than 0.");
            return;
        }

        String bookKey = title + " by " + author;
        if (booksInLibrary.containsKey(bookKey)) {
            // Book already exists, update quantity
            int currentQuantity = booksInLibrary.get(bookKey);
            booksInLibrary.put(bookKey, currentQuantity + quantity);
            System.out.println(quantity + " copies of '" + bookKey + "' added to the library.");
        } else {
            // Book is not in the library, add it
            booksInLibrary.put(bookKey, quantity);
            System.out.println("'" + bookKey + "' added to the library with " + quantity + " copies.");
        }
    }

    // Method to borrow books from the library
    public void borrowBook(String title, int quantity) {
        String bookKey = findBook(title);

        if (bookKey == null) {
            System.out.println("'" + title + "' is not readily available in the library.");
            return;
        }

        int availableQuantity = booksInLibrary.get(bookKey);

        if (quantity <= 0) {
            System.out.println("Please specify a valid quantity to borrow.");
        } else if (quantity > availableQuantity) {
            System.out.println("Sorry, only " + availableQuantity + " copies of '" + bookKey + "' are available.");
        } else {
            booksInLibrary.put(bookKey, availableQuantity - quantity);
            System.out.println(quantity + " copies of '" + bookKey + "' borrowed successfully.");
        }
    }

    // Method to return books to the library
    public void returnBook(String title, int quantity) {
        String bookKey = findBook(title);

        if (bookKey == null) {
            System.out.println("'" + title + "' is not a valid book to return.");
            return;
        }

        int currentQuantity = booksInLibrary.get(bookKey);

        if (quantity <= 0) {
            System.out.println("Please specify a valid quantity to return.");
        } else {
            booksInLibrary.put(bookKey, currentQuantity + quantity);
            System.out.println(quantity + " copies of '" + bookKey + "' returned successfully.");
        }
    }

    // Helper method to find a book by title
    private String findBook(String title) {
        for (String book : booksInLibrary.keySet()) {
            if (book.startsWith(title)) {
                return book;
            }
        }
        return null;
    }

    // Method to display the available books in the library
    public void displayBooks() {
        System.out.println("Books available in the library:");
        for (Map.Entry<String, Integer> entry : booksInLibrary.entrySet()) {
            System.out.println(entry.getKey() + " - Quantity: " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        Library library = new Library();

        // Adding books to the library
        library.addBook("Harry Potter and the Sorcerer's Stone", "J.K. Rowling", 5);
        library.addBook("The Hobbit", "J.R.R. Tolkien", 3);
        library.addBook("To Kill a Mockingbird", "Harper Lee", 2);

        // Display available books
        library.displayBooks();

        // Borrowing and returning books
        library.borrowBook("Harry Potter and the Sorcerer's Stone", 2);
        library.returnBook("The Hobbit", 1);

        // Display available books again
        library.displayBooks();
    }
}
