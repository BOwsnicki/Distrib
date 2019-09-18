package dsa1.jdbc.example;

/**
 *
 * @author Bernd OK
 The main idea of this class is to serve as a superclass for all exceptions we created.
 It simply makes it easier to catch - we're catching LibraryExceptions, all subclass instances
 will then be caught and we just print out the (very descriptive) message... Clever, clever!
 */
public class LibraryException extends Exception {
    public LibraryException() { super(); }
    public LibraryException(String descr) { super(descr); }
}
