import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.io.IOException;
import java.util.Scanner;

public class LibrarianApp {
    
    private static int arrSize = 100;
    private static Librarian[] librarians = new Librarian[arrSize];
    private static ReadingMaterial[] readingMats = new ReadingMaterial[arrSize];
    private static int libCount = 0;
    private static int bookCount = 0;
    private static int newsCount = 0;
    private static int magCount = 0;
    private static int journCount = 0;
    private static int matCount;
    private static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        loadValidLibrarians();
        loadReadingMats();
        boolean validUser = isLoggedIn();
        clearScreen();
        if(validUser) {
            boolean continueProgram = true;
            while(continueProgram) {
                updateMatCount();
                int choice = showMenu();
                switch(choice) {
                    case 1:
                        clearScreen();
                        addReadingMats();
                        clearScreen();
                        break;
                    case 2:
                        clearScreen();
                        removeReadingMats();
                        clearScreen();
                        break;
                    case 3:
                        clearScreen();
                        displayReadingMats();
                        promptEnterKey();
                        clearScreen();
                        break;
                    case 4:
                        continueProgram = false;
                        break;
                    default:
                        clearScreen();
                        System.out.println("Invalid choice! Please try again.\n\n");
                        break;
                }
            }
        }

        else {
            System.out.println("Invalid user! Closing the program...");
            System.exit(0);
        }
    }

    public static int showMenu() throws InputMismatchException {
        int choice = -1;
        System.out.println("[1]. Add Reading Materials");
        System.out.println("[2]. Remove Reading Materials");
        System.out.println("[3]. Display Reading Materials");
        System.out.println("[4]. Quit");
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

    public static int showMats() throws InputMismatchException {
        int choice = -1;
        System.out.println("[1]. Books");
        System.out.println("[2]. Magazines");
        System.out.println("[3]. Newspapers");
        System.out.println("[4]. Journals");
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

    public static boolean isLoggedIn() {
        String idNumber, password;
        System.out.print("Enter your ID Number: ");
        idNumber = input.nextLine();
        System.out.print("Enter your password: ");
        password = input.nextLine();
        for(int i = 0; i < libCount; i++) {
            String currId = librarians[i].getIdNumber();
            String currPassword = librarians[i].getPassword();
            if(idNumber.equals(currId) && password.equals(currPassword)){
                return true;
            }
        }
        return false;
    }

    public static void loadValidLibrarians() {
        File librarianFile = new File("../data/librarian.txt");
        Scanner fileReader;
        try {
            fileReader = new Scanner(librarianFile);
        }
        catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }
        while(fileReader.hasNext()) {
            String[] libDetails = fileReader.nextLine().split(";");
            Librarian currLib = new Librarian(libDetails[0], libDetails[1], libDetails[2], Long.parseLong(libDetails[3]));
            librarians[libCount++] = currLib;
        }
        fileReader.close();
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

    public static void addReadingMats() {
        updateMatCount();
        input.nextLine();
        System.out.print("Enter title of reading material: ");
        String title = input.nextLine();
        System.out.print("Enter author of " + title + ": ");
        String author = input.nextLine();
        int choice = showMats();
        switch(choice) {
            case 1:
                Book currBook = new Book(title, author);
                bookCount++;
                readingMats[matCount] = currBook;
                System.out.println("[SUCCESS] Added reading material!");
                promptEnterKey();
                break;
            case 2:
                Magazine currMag = new Magazine(title, author);
                magCount++;
                readingMats[matCount] = currMag;
                System.out.println("[SUCCESS] Added reading material!");
                promptEnterKey();
                break;
            case 3:
                Newspaper currNews = new Newspaper(title, author);
                newsCount++;
                readingMats[matCount] = currNews;
                System.out.println("[SUCCESS] Added reading material!");
                promptEnterKey();
                break;
            case 4:
                Journal currJourn = new Journal(title, author);
                journCount++;
                readingMats[matCount] = currJourn;
                System.out.println("[SUCCESS] Added reading material!");
                promptEnterKey();
                break;
            default:
                clearScreen();
                System.out.println("Invalid choice! Going back to menu.\n\n");
                promptEnterKey();
                break;
        }
        saveReadingMats();
    }

    public static void removeReadingMats() throws InputMismatchException {
        updateMatCount();
        displayReadingMats();
        if(matCount > 0) {
            int choice = -1;
            System.out.print("Enter index of material to be deleted: ");
            try {
                choice = input.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("[ERROR] Invalid input.");
                input.next();
                promptEnterKey();
                return;
            }
            if(choice > 0 && choice <= matCount) {
                choice--;
                String currType = readingMats[choice].getMatType();
                if(currType.equalsIgnoreCase("book")) {
                    bookCount--;
                }
                if(currType.equalsIgnoreCase("newspaper")) {
                    newsCount--;
                }
                if(currType.equalsIgnoreCase("journal")) {
                    journCount--;
                }
                if(currType.equalsIgnoreCase("magazine")) {
                    magCount--;
                }
                for(int i = choice; i < matCount - 1; i++){
                    readingMats[i] = readingMats[i + 1];
                    readingMats[i+1] = null;
                }
                System.out.println("[SUCCESS] Removed reading material!");
            }
        }
        saveReadingMats();
        promptEnterKey();
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

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    public static void promptEnterKey() {
        System.out.print("Press ENTER to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
     }

     public static void updateMatCount() {
        matCount = bookCount + newsCount + magCount + journCount;
     }
}