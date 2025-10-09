package Student_Management_System;
//import exceptions.*;
public class CustomException {

    // Student not found
    public static class StudentNotFoundException extends Exception {
        public StudentNotFoundException(String message) {
            super(message);
        }
    }

    // Class not found
    public static class ClassNotFoundException extends Exception {
        public ClassNotFoundException(String message) {
            super(message);
        }
    }

    // Address not found
    public static class AddressNotFoundException extends Exception {
        public AddressNotFoundException(String message) {
            super(message);
        }
    }
    public static class InvalidAgeException extends Exception {
        public InvalidAgeException(String message) {
            super(message);
        }
    }
    public static class InvalidMarksException extends Exception {
        public InvalidMarksException(String message) {
            super(message);
        }
    }

    // Invalid data exception
    public static class InvalidDataException extends Exception {
        public InvalidDataException(String message) {
            super(message);
        }
    }
}