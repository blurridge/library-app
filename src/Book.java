public class Book extends ReadingMaterial {
    
    Book(String title, String author) {
        super(title, author);
        this.matType = "Book";
    }

    Book(String title, String author, boolean isBorrowed, String borrowerName, String borrowerType, String returnDate) {
        super(title, author, isBorrowed, borrowerName, borrowerType, returnDate);
        this.matType = "Book";
    }
}
