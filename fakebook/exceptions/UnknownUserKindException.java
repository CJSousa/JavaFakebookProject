package fakebook.exceptions;

/**
 * This exceptions is thrown when a method calls a user kind which does not exist in the program
 * @author Clara Sousa - 58403 / Bruna Arroja - 56751
 *
 */

public class UnknownUserKindException extends Exception{

    /* Constant */
    private static final String UNKNOWN_USER_KIND_EXCEPTION = " is an invalid user kind!";

    /* Constructor */
    public UnknownUserKindException(String kind){
        super(kind+UNKNOWN_USER_KIND_EXCEPTION);
    }
}
