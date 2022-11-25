public class Journal extends ReadingMaterial {
    
    Journal(String title, String author) {
        super(title, author);
        this.matType = "Journal";
    }

    Journal(String title, String author, boolean isBorrowed, String borrowerName, String borrowerType, String returnDate) {
        super(title, author, isBorrowed, borrowerName, borrowerType, returnDate);
        this.matType = "Journal";
    }
}
