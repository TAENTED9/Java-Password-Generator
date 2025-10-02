public class Alphabet {

    // Constant for uppercase letters
    public static final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    // Constant for lowercase letters
    public static final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";

    // Constant for numbers
    public static final String NUMBERS = "1234567890";

    // Constant for special characters
    public static final String SYMBOLS = "!@#$%^&*_~?";

    // StringBuilder to hold the selected characters for the password
    private final StringBuilder pool;

    // Constructor to create an alphabet based on user preferences
    public Alphabet(boolean uppercaseIncluded, boolean lowercaseIncluded, boolean numbersIncluded, boolean specialCharactersIncluded) {
        
        pool = new StringBuilder(); // Initialize the pool to store selected characters

        // Add uppercase letters to the pool if the user wants them
        if (uppercaseIncluded) pool.append(UPPERCASE_LETTERS);

        // Add lowercase letters to the pool if the user wants them
        if (lowercaseIncluded) pool.append(LOWERCASE_LETTERS);

        // Add numbers to the pool if the user wants them
        if (numbersIncluded) pool.append(NUMBERS);

        // Add special characters to the pool if the user wants them
        if (specialCharactersIncluded) pool.append(SYMBOLS);
    }

    // Method to return the selected alphabet as a string
    public String getAlphabet() {
        return pool.toString(); // Convert the StringBuilder to a string and return it
    }
}
