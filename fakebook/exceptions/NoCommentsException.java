package fakebook.exceptions;

public class NoCommentsException extends Exception{

    /**
     * This exceptions is thrown when a post doesnt have any comments
     * @author Clara Sousa - 58403 / Bruna Arroja - 56751
     *
     */

    /* Constants */
    private static final String NO_COMMENTS_MESSAGE = "No comments!";

    /* Constructor */
    public NoCommentsException(){super(NO_COMMENTS_MESSAGE);}

}
