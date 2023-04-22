package dtu.system.app;

public class OperationNotAllowedException extends Exception {
     public OperationNotAllowedException(String errorMessage) {
          // Danny
          super(errorMessage);
     }
}
