package dtu.system.app;

public class WorkerAlreadyExistsException extends Exception
{
     public WorkerAlreadyExistsException(String errorMessage)
     {
          super(errorMessage);
     }
}
