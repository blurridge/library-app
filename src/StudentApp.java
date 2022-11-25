import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.io.IOException;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;

public class StudentApp {

    private static int arrSize = 100;
    private static ReadingMaterial[] readingMats = new ReadingMaterial[arrSize];
    private static Scanner input = new Scanner(System.in);
    private static int bookCount = 0;
    private static int newsCount = 0;
    private static int magCount = 0;
    private static int journCount = 0;
    private static int matCount;
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
    private static String maxReturnDate = dtf.format(LocalDateTime.now().plusDays(7));
    public static void main(String[] args) {
        loadReadingMats();
        boolean continueProgram = true;
        while(continueProgram) {
            int choice = showMenu();
            switch(choice) {
                case 1:
                    clearScreen();
                    borrowMat();
                    clearScreen();
                    break;
                case 2:
                    clearScreen();
                    returnMat();
                    clearScreen();
                    break;
                case 3:
                    continueProgram = false;
                    break;
                default:
                    clearScreen();
                    System.out.println("Invalid choice! Please try again.\n\n");
                    break;
            }
        }
    }

    public static int showMenu() throws InputMismatchException {
        int choice = -1;
        System.out.println("[1]. Borrow material");
        System.out.println("[2]. Return material");
        System.out.println("[3]. Quit");
        System.out.print("Enter your choice: ");
        try {
            choice = input.nextInt();
        }
        catch (InputMismatchException e) {
            input.next();
        }
        System.out.println("\n\n");
        return choice;
    }

    public static void saveReadingMats() {
        updateMatCount();
        File readingMatFile = new File("../data/readingmats.txt");
        try{
            FileWriter fileWriter = new FileWriter(readingMatFile);
            for(int i = 0; i < matCount; i++) {
                fileWriter.write(readingMats[i].getSaveString() + "\n");
            }
            fileWriter.flush();
            fileWriter.close();
        }
        catch (IOException e) {
            System.out.println("[ERROR] Saving failed.");
        }
    }

    public static void loadReadingMats() {
        File readingMatFile = new File("../data/readingmats.txt");
        Scanner fileReader;
        try {
            fileReader = new Scanner(readingMatFile);
        }
        catch(FileNotFoundException e) {
            System.out.println("[ERROR] Default reading material file not found. Creating...");
            return;
        }
        while(fileReader.hasNext()) {
            updateMatCount();
            String[] matDetails = fileReader.nextLine().split(";");
            String type = matDetails[0], title = matDetails[1], author = matDetails[2], borrowerName = matDetails[4];
            String borrowerType = matDetails[5], returnDate = matDetails[6];
            boolean borrowStatus = Boolean.parseBoolean(matDetails[3]);
            ReadingMaterial curr = null;
            if(type.equalsIgnoreCase("book")) {
                curr = new Book(title, author, borrowStatus, borrowerName, borrowerType, returnDate);
                bookCount++;
            }
            else if(type.equalsIgnoreCase("newspaper")) {
                curr = new Newspaper(title, author, borrowStatus, borrowerName, borrowerType, returnDate);
                newsCount++;
            }
            else if(type.equalsIgnoreCase("magazine")) {
                curr = new Magazine(title, author, borrowStatus, borrowerName, borrowerType, returnDate);
                magCount++;
            }
            else if(type.equalsIgnoreCase("journal")) {
                curr = new Journal(title, author, borrowStatus, borrowerName, borrowerType, returnDate);
                journCount++;
            }
            readingMats[matCount] = curr;
        }
        fileReader.close();
    }

    public static void displayReadingMats() {
        updateMatCount();
        if(matCount > 0) {
            for(int i = 0; i < matCount; i++) {
                System.out.print("[" + (i+1) + "] ");
                System.out.println(readingMats[i].toString());
                System.out.println();
            }
        }
        else {
            System.out.println("[ERROR] There are no reading materials available.");
        }
    }

    public static void borrowMat() throws InputMismatchException {
        displayReadingMats();
        int choice = -1;
        System.out.println("Enter index of material to be borrowed: ");
        try {
            choice = input.nextInt();
        }
        catch (InputMismatchException e) {
            input.next();
        }
        if(choice > 0 && choice <= matCount) {
            choice--;
            if(!readingMats[choice].getBorrowedStatus()) {
                input.nextLine();
                System.out.print("Enter name of student: ");
                String name = input.nextLine();
                readingMats[choice].setBorrowStatus(true, name, "Student", maxReturnDate);
                System.out.println("[SUCCESS] Material borrowed successfully!");
                promptEnterKey();
            }
            else {
                System.out.println("[ERROR] Material currently borrowed.");
                promptEnterKey();
            }
            saveReadingMats();
        }
        else {
            System.out.println("[ERROR] Invalid choice.");
            promptEnterKey();
        }
    }

    public static void returnMat() throws InputMismatchException {
        displayReadingMats();
        int choice = -1;
        System.out.println("Enter index of material to be returned: ");
        try {
            choice = input.nextInt();
        }
        catch (InputMismatchException e) {
            input.next();
        }
        if(choice > 0 && choice <= matCount) {
            choice--;
            if(readingMats[choice].getBorrowedStatus()) {
                readingMats[choice].setBorrowStatus(false, "N/A", "N/A", "N/A");
                System.out.println("[SUCCESS] Material borrowed returned!");
                promptEnterKey();
            }
            else {
                System.out.println("[ERROR] Material currently unborrowed");
                promptEnterKey();
            }
            saveReadingMats();
        }
        else {
            System.out.println("[ERROR] Invalid choice.");
            promptEnterKey();
        }
    }

    public static void updateMatCount() {
        matCount = bookCount + newsCount + magCount + journCount;
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    public static void promptEnterKey() {
        System.out.print("Press ENTER to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
