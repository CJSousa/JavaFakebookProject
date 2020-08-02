package fakebook.exceptions;

/**
 * This exceptions is thrown when the comment stance is invalid for the user id and the post in question
 * @author Clara Sousa - 58403 / Bruna Arroja - 56751
 *
 */


public class InvalidCommentStanceException extends Exception{

    /* Constant */
    private static final String INVALID_COMMENT_STANCE_MESSAGE = "Invalid comment stance!";

    /* Constructor */
    public InvalidCommentStanceException(){super(INVALID_COMMENT_STANCE_MESSAGE);}
}
