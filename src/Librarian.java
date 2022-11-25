public class Librarian extends User {
    
    Librarian(String name, String idNumber, String password, long mobileNumber) {
        super(name, idNumber, password, mobileNumber);
    }

    public String toString() {
        String userString = "Type: LIBRARIAN\nUser name: " + this.name + "\nID number: " + this.idNumber + "\nMobile Number: " + this.mobileNumber + "\nPassword: " + this.password;
        return userString;
    }
}
