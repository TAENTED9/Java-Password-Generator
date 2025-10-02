import java.io.FileWriter; // Import the Scanner class for user input
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Generator {
    Alphabet alphabet; // Holds the alphabet configuration for password generation
    public static Scanner keyboard; // Scanner object for reading user input

    // Constructor to initialize the Scanner object
    public Generator(Scanner scanner) {
        keyboard = scanner;
    }

    // Constructor to initialize the Alphabet object with user preferences
    public Generator(boolean IncludeUpper, boolean IncludeLower, boolean IncludeNum, boolean IncludeSym) {
        alphabet = new Alphabet(IncludeUpper, IncludeLower, IncludeNum, IncludeSym);
    }

    // Main loop for the program's menu
    public void mainLoop() {
        System.out.println("\nHey There, Welcome to TAENTED Password Generator :)"); // Welcome message
        printMenu(); // Show the menu options

        String userOption = "-1"; // Variable to store the user's menu choice

        // Loop until the user chooses to quit (option "4")
        while (!userOption.equals("4")) {
            userOption = keyboard.next(); // Read the user's choice

            switch (userOption) {
                case "1" -> { // Option 1: Generate a password
                    requestPassword();
                    printMenu(); // Show the menu again
                }
                case "2" -> { // Option 2: Check password strength
                    checkPassword();
                    printMenu(); // Show the menu again
                }
                case "3" -> { // Option 3: Show useful password tips
                    printUsefulInfo();
                    printMenu(); // Show the menu again
                }
                case "4" -> printQuitMessage(); // Option 4: Quit the program
                default -> { // Handle invalid menu choices
                    System.out.println();
                    System.out.println("Kindly select one of the available commands");
                    printMenu(); // Show the menu again
                }
            }
        }
    }

    // Method to generate a password of a given length
    private Password GeneratePassword(int length) {
        final StringBuilder pass = new StringBuilder(""); // StringBuilder to build the password

        final int alphabetLength = alphabet.getAlphabet().length(); // Get the length of the alphabet

        int max = alphabetLength - 1; // Maximum index for the alphabet
        int min = 0; // Minimum index for the alphabet
        int range = max - min + 1; // Range of indices

        // Loop to generate each character of the password
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * range) + min; // Random index in the alphabet
            pass.append(alphabet.getAlphabet().charAt(index)); // Add the character to the password
        }

        return new Password(pass.toString()); // Return the generated password
    }

    // Method to print useful password tips
    private void printUsefulInfo() {
        System.out.println();
        System.out.println("Password Tips:");
        System.out.println("- Use at least 8 characters (more is better).");
        System.out.println("- Mix lowercase, uppercase, numbers, and symbols if allowed.");
        System.out.println("- Create passwords randomly when possible.");
        System.out.println("- Don’t reuse the same password on different accounts or systems.");
        System.out.println("- Avoid repeats, patterns (like '1234' or 'abcd'), dictionary words,");
        System.out.println("  names, birthdays, or other personal info.");
        System.out.println("- Don’t use info that coworkers, friends, or family might guess about you.");
        System.out.println("- Never make a password from only weak parts like words, names, or numbers.");
    }


    // Method to request user preferences and generate a password
    private void requestPassword() {
        Scanner input = new Scanner(System.in);
        boolean IncludeUpper = false; // Include uppercase letters
        boolean IncludeLower = false; // Include lowercase letters
        boolean IncludeNum = false; // Include numbers
        boolean IncludeSym = false; // Include symbols

        boolean correctParams = false; // Flag to check if valid preferences are provided

        System.out.println();
        System.out.println("\nAnswer the following questions by Yes or No \n");

        // Loop until the user provides valid preferences
        while (!correctParams) {
            IncludeLower = getUserPreference("Do you want Lowercase letters \"abcd...\" to be used?");
            IncludeUpper = getUserPreference("Do you want Uppercase letters \"ABCD...\" to be used?");
            IncludeNum = getUserPreference("Do you want Numbers \"1234...\" to be used?");
            IncludeSym = getUserPreference("Do you want Symbols \"!@#$...\" to be used?");

            // Check if no character pool is selected
            if (!IncludeUpper && !IncludeLower && !IncludeNum && !IncludeSym) {
                System.out.println("You have selected no characters to generate your password. \nAt least one of your answers should be Yes.");
            } 
            else {
                correctParams = true; // Valid preferences provided
            }
        }

        int length = 0; // Variable to store the password length
        System.out.println("Great! Now enter the length of the password:");
        while (length <= 0) { // Loop until a valid length is provided
            try {
                length = Integer.parseInt(input.nextLine()); // Read and parse the length
                if (length <= 0) {
                    System.out.println("Password length must be a positive number. Try again.");
                }
            } catch (NumberFormatException e) { // Handle invalid input
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        // Create a new Generator object with the user's preferences
        final Generator generator = new Generator(IncludeUpper, IncludeLower, IncludeNum, IncludeSym);
        final Password password = generator.GeneratePassword(length); // Generate the password

        System.err.println("Your generated password -> " + password); // Print the generated password

        
        System.out.println("\nWrite down the name of Site for future reference: ");
        String SiteInput = input.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH-mm a dd-MMMM-yyyy");
        String timestamp = LocalDateTime.now().format(formatter);
        String filename = "\\Desktop\\Generated_password(" + timestamp + ").txt";

        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write("\nName of Site: " + SiteInput + "\nPassword Genrated: " + password + "\nDate & Time Generated: " + timestamp);
            System.out.println("Generated Password saved successfully to Desktop!");
        } catch (IOException e) {
            System.out.println("Failed to save password: " + e.getMessage());
        }

    }

    // Method to get user preference (Yes or No) for a specific question
    private boolean getUserPreference(String message) {
        Scanner input = new Scanner(System.in);
        while (true) { // Loop until valid input is provided
            System.out.println(message + " (Type 'Yes' or 'No')");
            String x = input.nextLine().trim().toLowerCase(); // Read input and trim whitespace
            
            switch (x) {
                case "yes":
                    return true; // Return true for "Yes"
                case "no":
                    return false; // Return false for "No"
                default:
                    System.out.println("Invalid input. Please answer with 'Yes' or 'No'."); // Handle invalid input
            } 
        }
    }

    // Method to check the strength of a password
    private void checkPassword() {
        Scanner input = new Scanner(System.in);
        System.out.print("\nEnter your password:"); // Prompt the user to enter a password
        String x = input.nextLine(); // Read the password

        final Password p = new Password(x); // Create a Password object

        System.out.println(p.evaluateStrenght()); // Print the password strength score
    }

    // Method to print the menu options
    private void printMenu() {
        System.out.println();
        System.out.println("Enter 1 - Password Generator");
        System.out.println("Enter 2 - Password Strength Check");
        System.out.println("Enter 3 - Useful Information");
        System.out.println("Enter 4 - Quit");
        System.out.print("Choice: "); // Prompt the user to make a choice
    }

    // Method to print a quit message
    private void printQuitMessage() {
        System.out.println("\nClosing program....\nProgram Closed.");
    }
}
