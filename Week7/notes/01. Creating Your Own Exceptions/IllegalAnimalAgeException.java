// Defines a custom exception: IllegalAnimalAgeException

//class IllegalAnimalAgeException extends java.lang.RuntimeException { // unchecked exception
class IllegalAnimalAgeException extends java.lang.Exception {          // checked exception

    // zero-arg constructor
    public IllegalAnimalAgeException() {
        super();
    }

    // one-arg constructor
    public IllegalAnimalAgeException(String s) {
        super(s);
    }
}
