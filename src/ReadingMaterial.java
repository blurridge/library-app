public class ReadingMaterial {
    
    protected String title;
    protected String author;
    protected boolean isBorrowed;
    protected String borrowerName;
    protected String borrowerType;
    protected String returnDate;
    protected String matType;

    ReadingMaterial() {
        title = "";
        author = "";
        isBorrowed = false;
    }

    ReadingMaterial(String title, String author) {
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
        this.borrowerName = "N/A";
        this.borrowerType = "N/A";
        this.returnDate = "N/A";
        this.matType = "";
    }

    ReadingMaterial(String title, String author, boolean isBorrowed, String borrowerName, String borrowerType, String returnDate) {
        this.title = title;
        this.author = author;
        this.isBorrowed = isBorrowed;
        this.borrowerName = borrowerName;
        this.borrowerType = borrowerType;
        this.returnDate = returnDate;
        this.matType = "";
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public boolean getBorrowedStatus() {
        return this.isBorrowed;
    }

    public String getBorrowerName() {
        return this.borrowerName;
    }

    public String getBorrowerType() {
        return this.borrowerType;
    }

    public String getReturnDate() {
        return this.returnDate;
    }

    public String getMatType() {
        return this.matType;
    }

    public String getSaveString() {
        String saveString = this.matType + ";" + this.title + ";" + this.author + ";" + String.valueOf(this.isBorrowed) + ";" + this.borrowerName + ";"
                            + this.borrowerType + ";" + this.returnDate;
        return saveString;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setBorrowStatus(boolean isBorrowed, String borrowerName, String borrowerType, String returnDate) {
        this.isBorrowed = isBorrowed;
        this.borrowerName = borrowerName;
        this.borrowerType = borrowerType;
        this.returnDate = returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String toString() {
        String userString = "Type: " + this.matType + "\nTitle: " + this.title + "\nAuthor: " + this.author + "\nBorrow Status: " 
                        + this.isBorrowed + "\nBorrower: " + this.borrowerName + " (" + this.borrowerType + ")" + "\nReturn Date: " 
                        + this.returnDate;
        return userString;
    }
}
