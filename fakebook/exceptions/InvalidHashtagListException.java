package fakebook.exceptions;

/**
 * This exceptions is thrown if the number of hashtags is less than zero or if there are repeated hashtags
 * @author Clara Sousa - 58403 / Bruna Arroja - 56751
 *
 */

public class InvalidHashtagListException extends Exception{

    /* Constant */
    private static final String INVALID_LIST_MESSAGE = "Invalid hashtags list!";

    /* Constructor */
    public InvalidHashtagListException(){ super(INVALID_LIST_MESSAGE);}
}
