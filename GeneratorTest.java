import static org.junit.jupiter.api.Assertions.*; // Import JUnit assertions for testing

import org.junit.jupiter.api.Test; // Import JUnit's @Test annotation for writing test cases

// Test class for the Generator class
class GeneratorTest {
    
    // Create a Password object with the value "Secret"
    private final Password password = new Password("Secret");
    
    // Create an Alphabet object with only uppercase letters included
    private final Alphabet firstAlphabet = new Alphabet(true, false, false, false);
    
    // Create an Alphabet object with lowercase letters, numbers, and symbols included
    private final Alphabet secondAlphabet = new Alphabet(false, true, true, true);
    
    // Create a Generator object with only uppercase letters included
    private final Generator generator = new Generator(true, false, false, false);
    

    // Test case 1: Check if the Password object's string value is "Secret"
    @Test
    void test1() {
        assertEquals("Secret", password.toString()); // Verify that the password's string matches "Secret"
    }

    // Test case 2: Check if the firstAlphabet contains only uppercase letters
    @Test
    void test2() {
        assertEquals(firstAlphabet.getAlphabet(), Alphabet.UPPERCASE_LETTERS); // Verify that the alphabet matches uppercase letters
    }

    // Test case 3: Check if the secondAlphabet contains lowercase letters, numbers, and symbols
    @Test
    void test3() {
        assertEquals(secondAlphabet.getAlphabet(), Alphabet.LOWERCASE_LETTERS + Alphabet.NUMBERS + Alphabet.SYMBOLS); // Verify the combined alphabet
    }
    
    // Test case 4: Check if the generator's alphabet contains only uppercase letters
    @Test
    void test4() {
        assertEquals(generator.alphabet.getAlphabet(), Alphabet.UPPERCASE_LETTERS); // Verify that the generator's alphabet matches uppercase letters
    }
    
    // Test case 5: Check if the length of the generator's alphabet is 26 (uppercase letters only)
    @Test
    void test5() {
        assertEquals(generator.alphabet.getAlphabet().length(), 26); // Verify that the length of the alphabet is 26
    }
}
