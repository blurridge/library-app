public class User {
    
    protected String name;
    protected String idNumber;
    protected String password;
    protected long mobileNumber;

    User() {
        name = "";
        idNumber = "";
        password = "";
    }

    User(String name, String idNumber, String password, long mobileNumber) {
        this.name = name;
        this.idNumber = idNumber;
        this.password = password;
        this.mobileNumber = mobileNumber;
    }

    public String getName() {
        return this.name;
    }

    public String getIdNumber() {
        return this.idNumber;
    }

    public long getMobileNumber() {
        return this.mobileNumber;
    }

    public String getPassword() {
        return this.password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        String userString = "\nUser name: " + this.name + "\nID number: " + this.idNumber + "\nMobile Number: " + this.mobileNumber;
        return userString;
    }
}
