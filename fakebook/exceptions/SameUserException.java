package fakebook.exceptions;

/**
 * This exceptions is thrown when two ids are the same
 * @author Clara Sousa - 58403 / Bruna Arroja - 56751
 *
 */

public class SameUserException extends Exception{

    /* Constant */
    private static final String SAME_USER_MESSAGE = " cannot be the same as ";
    private static final String EXCLAMATION = "!";

    /* Constructor */
    public SameUserException(String id){ super(id+SAME_USER_MESSAGE+id+EXCLAMATION);}
}
