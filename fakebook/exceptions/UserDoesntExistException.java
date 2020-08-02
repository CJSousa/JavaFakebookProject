package fakebook.exceptions;

import fakebook.FakebookClass;

/**
 * This exceptions is thrown when a certain user id does not exist
 * @author Clara Sousa - 58403 / Bruna Arroja - 56751
 *
 */

public class UserDoesntExistException extends Exception{

    /* Constant */
    private static final String USER_NO_EXISTS_MESSAGE = " does not exist!";

    /* Constructor */
    public UserDoesntExistException(String id){ super(id+USER_NO_EXISTS_MESSAGE);}
}
