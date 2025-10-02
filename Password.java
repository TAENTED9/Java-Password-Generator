public class Password {

    // Variable to store the password value
    private final String Value;

    // Constructor to initialize the password with a given value
    public Password(String value) {
        this.Value = value;
    }

    // Method to evaluate the strength of the password
    public String evaluateStrenght() {

        int upper = 0, lower = 0, digits = 0, symbols = 0;
        String specialChars = "!@#$%^&*_~? ";

        // Loop through each character in the password
        for (char c : Value.toCharArray()) {
            if (Character.isUpperCase(c)) upper++; // Count uppercase letters
            else if (Character.isLowerCase(c)) lower++; // Count lowercase letters
            else if (Character.isDigit(c)) digits++; // Count digits
            else if (specialChars.indexOf(c) != -1) symbols++; // Count special characters
        }

        // Check if the password is long enough (more than 8 characters)
        boolean longEnough = Value.length() > 8;

        // If the password meets all strong criteria
        if (upper >= 2 && lower >= 2 && digits >= 2 && symbols >= 2 && longEnough) {
            return "\nStrong password."; 
        }

        // If the password is fair but missing enough uppercase letters
        if (upper < 2 && lower >= 2 && digits >= 2 && symbols >= 2 && longEnough) {
            return "\nFair password. Try adding at least 1 more uppercase letter to make it strong.";
        }

        // If the password is weak, provide feedback on how to improve it
        StringBuilder feedback = new StringBuilder("\nWeak password, Please change for security purposes.\nPlease address the following:\n");
        if (upper < 2) feedback.append("-Add at least 2 uppercase letters\n"); // Suggest adding uppercase letters
        if (lower < 2) feedback.append("-Add at least 2 lowercase letters\n"); // Suggest adding lowercase letters
        if (digits < 2) feedback.append("-Add at least 2 digits\n"); // Suggest adding digits
        if (symbols < 2) feedback.append("-Add at least 2 symbols (e.g. !@#...)\n"); // Suggest adding symbols
        if (!longEnough) feedback.append("-Password must be longer than 8 characters\n"); // Suggest increasing length

        return feedback.toString(); // Return the feedback message
    }

    // Override the toString method to return the password value as a string
    @Override
    public String toString() {
        return Value; // Return the password value
    }
}
