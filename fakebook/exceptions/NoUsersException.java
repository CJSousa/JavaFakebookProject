package fakebook.exceptions;


/**
 * This exceptions is thrown when there are no users in the system
 * @author Clara Sousa - 58403 / Bruna Arroja - 56751
 *
 */

public class NoUsersException extends Exception{

    /* Constant */
    private static final String NO_USERS_MESSAGE = "There are no users!";

    /* Constructor */
    public NoUsersException(){ super(NO_USERS_MESSAGE);}
}
