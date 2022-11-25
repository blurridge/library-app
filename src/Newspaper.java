public class Newspaper extends ReadingMaterial {
    
    Newspaper(String title, String author) {
        super(title, author);
        this.matType = "Newspaper";
    }

    Newspaper(String title, String author, boolean isBorrowed, String borrowerName, String borrowerType, String returnDate) {
        super(title, author, isBorrowed, borrowerName, borrowerType, returnDate);
        this.matType = "Newspaper";
    }
}
