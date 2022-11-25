public class Teacher extends User {
    
    Teacher(String name, String idNumber, String password, long mobileNumber) {
        super(name, idNumber, password, mobileNumber);
    }

    public String toString() {
        String userString = "Type: TEACHER\nUser name: " + this.name + "\nID number: " + this.idNumber + "\nMobile Number: " + this.mobileNumber;
        return userString;
    }
}
