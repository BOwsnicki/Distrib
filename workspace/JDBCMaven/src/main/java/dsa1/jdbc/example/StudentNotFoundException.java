package dsa1.jdbc.example;

/**
 *
 * @author Bernd OK
 */
public class StudentNotFoundException extends LibraryException {
    public StudentNotFoundException() { super(); }
    public StudentNotFoundException(String descr) { super(descr); }
}
