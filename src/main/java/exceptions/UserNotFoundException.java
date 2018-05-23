package exceptions;


/**
 * Created by jon on 13/04/17.
 */
public class UserNotFoundException extends Exception{
    public UserNotFoundException(String message) {
        super(message);
    }

    static final long serialVersionUID = 01314123123l;
}
