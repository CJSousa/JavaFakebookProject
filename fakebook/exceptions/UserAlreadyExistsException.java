package fakebook.exceptions;

/**
 * This exceptions is thrown when a method calls a user who already exists in the program
 * @author Clara Sousa - 58403 / Bruna Arroja - 56751
 *
 */

public class UserAlreadyExistsException extends Exception{

    /* Constant */
    private static final String USER_EXISTS_MESSAGE = " already exists!";

    /* Constructor */
    public UserAlreadyExistsException(String id){ super(id+USER_EXISTS_MESSAGE);}
}
