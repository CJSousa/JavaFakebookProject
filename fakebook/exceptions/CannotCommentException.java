package fakebook.exceptions;

/**
 * This exceptions is thrown when a user is not allowed to comment on a post
 * @author Clara Sousa - 58403 / Bruna Arroja - 56751
 *
 */

public class CannotCommentException extends Exception {

    /* Constant */
    private static final String CANNOT_COMMENT_MESSAGE = " cannot comment on this post!";

    /* Constructor */
    public CannotCommentException(String id){ super(id+CANNOT_COMMENT_MESSAGE);}

}
