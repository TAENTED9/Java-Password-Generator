import java.util.Scanner; // Import the Scanner class to read user input

public class Main {

    // Create a Scanner object to read input from the keyboard
    public static final Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        // Use a try-with-resources block to automatically close the Scanner after use
        try (keyboard) {
            // Create a Generator object and pass the Scanner to it
            Generator generator = new Generator(keyboard);

            // Start the main loop of the Generator program
            generator.mainLoop();
        }
    }
}
