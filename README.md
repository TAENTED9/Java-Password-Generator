# TAENTED Java Password Generator

Simple command-line password generator and strength checker written in Java. This small project provides an interactive menu to generate random passwords from configurable character pools, evaluate password strength, and save generated passwords to a text file.

## Features

- Generate random passwords from a selectable alphabet (uppercase, lowercase, numbers, symbols).
- Evaluate password strength and provide friendly feedback.
- Save generated password to a file (saved to a Desktop path by the current implementation).

## Requirements

- Java JDK 11 or newer (javac/java on PATH)
- (Optional) JUnit 5 for running the provided unit tests (`GeneratorTest.java`).

## Build & run (Windows PowerShell)

Open a PowerShell prompt in the project directory (`c:\Users\USER\Desktop\Java-Password-Generator`) and compile all sources:

```powershell
javac *.java
```

Run the program:

```powershell
java Main
```

When running the program you'll see a simple menu:

- Enter `1` to generate a password (you'll be prompted for character types and length).
- Enter `2` to check a password's strength.
- Enter `3` to display useful password tips.
- Enter `4` to quit.

Notes on interactive input:

- For Yes/No prompts, type `Yes` or `No` (case-insensitive).
- Password length must be a positive integer.

## Running tests (JUnit 5)

The repository includes `GeneratorTest.java` which uses JUnit 5. To run tests locally you can use the junit-platform-console-standalone jar. Download a compatible JUnit platform standalone jar (for example `junit-platform-console-standalone-1.9.3.jar`) and run:

```powershell
# Compile sources and tests (adjust path to the downloaded jar)
javac -cp .;C:\path\to\junit-platform-console-standalone-1.9.3.jar *.java

# Run tests by scanning the current classpath
java -jar C:\path\to\junit-platform-console-standalone-1.9.3.jar --class-path . --scan-class-path
```

Alternatively, import the project into an IDE (IntelliJ IDEA, Eclipse) and add JUnit 5 as a library/dependency and run the JUnit test run configuration.

## Project files

- `Main.java` - entry point, holds a shared Scanner and starts the `Generator` main loop.
- `Generator.java` - interactive menu, password generation, strength checking, and file saving logic.
- `Alphabet.java` - builds the character pool based on user preferences (uppercase, lowercase, numbers, symbols).
- `Password.java` - represents a password and evaluates its strength, returns human-friendly feedback.
- `GeneratorTest.java` - JUnit tests for `Alphabet`, `Password`, and `Generator` basic behavior.

## Notes, Known issues and suggested improvements

- Saving location: the current code builds a filename using `"\\Desktop\\Generated_password(...).txt"`. This creates a path relative to the drive root and may not match the active user's Desktop path. A more robust approach is to use:

```java
String desktop = System.getProperty("user.home") + "\\Desktop\\";
String filename = desktop + "Generated_password(" + timestamp + ").txt";
```

- Multiple Scanner instances: the code creates several `Scanner` objects reading `System.in` (and also uses a static `keyboard` Scanner). It's better to create a single Scanner and pass it around to avoid resource and stream conflicts.
- File IO error handling: consider encrypting the saved passwords or prompting before writing sensitive data to disk.
- Tests: the tests are light and assume certain behaviors. Add more unit tests (edge cases, invalid inputs) and consider using a build tool (Maven/Gradle) to manage JUnit and run tests reliably.
- Randomness: `Math.random()` is used for password generation. For stronger randomness, prefer `SecureRandom`.
- Improve UX: allow non-interactive invocation (CLI flags) to generate passwords from scripts, and add options to copy the generated password to clipboard.

## Contract

- Inputs: interactive text input from the console (Yes/No prompts, integer length, site name).
- Outputs: printed text to console, optional saved file on Desktop containing generated password and metadata.
- Error modes: invalid numeric input is retried; invalid Yes/No input is re-prompted; file save failures are reported to console.

## License

MIT License — feel free to use, modify, and share.

---
