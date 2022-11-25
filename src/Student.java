public class Student extends User {

    Student(String name, String idNumber, String password, long mobileNumber) {
        super(name, idNumber, password, mobileNumber);
    }

    public String toString() {
        String userString = "Type: STUDENT\nUser name: " + this.name + "\nID number: " + this.idNumber + "\nMobile Number: " + this.mobileNumber;
        return userString;
    }
}
