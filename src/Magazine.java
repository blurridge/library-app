public class Magazine extends ReadingMaterial {
    
    Magazine(String title, String author) {
        super(title, author);
        this.matType = "Magazine";
    }

    Magazine(String title, String author, boolean isBorrowed, String borrowerName, String borrowerType, String returnDate) {
        super(title, author, isBorrowed, borrowerName, borrowerType, returnDate);
        this.matType = "Magazine";
    }
}
