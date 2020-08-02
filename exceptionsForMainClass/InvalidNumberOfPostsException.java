package exceptionsForMainClass;

/**
 * This exceptions is thrown when the maximum number of posts to be listed is invalid (smaller then 1)
 * @author Clara Sousa - 58403 / Bruna Arroja - 56751
 *
 */

public class InvalidNumberOfPostsException extends Exception {

    /* Constant */
    private static final String INVALID_NUMBER_EXCEPTION = "Invalid number of posts to present!";

    /* Constructor */
    public InvalidNumberOfPostsException(){super(INVALID_NUMBER_EXCEPTION);}
}
