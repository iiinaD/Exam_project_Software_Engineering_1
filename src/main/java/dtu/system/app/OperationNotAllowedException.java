package dtu.system.app;

// Danny

public class OperationNotAllowedException extends Exception {
     public OperationNotAllowedException(String errorMessage) {
          super(errorMessage);
     }
}
