package User.Exception;

public class DuplicateUserException extends RuntimeException{

    public DuplicateUserException(String msg){
        super(msg);
    }

}
