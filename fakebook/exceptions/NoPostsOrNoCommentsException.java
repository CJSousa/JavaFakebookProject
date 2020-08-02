package fakebook.exceptions;

/**
 * This exceptions is thrown when fakebook has no posts or comments yet
 * @author Clara Sousa - 58403 / Bruna Arroja - 56751
 *
 */
public class NoPostsOrNoCommentsException extends Exception{

    /* Constant */
    private static final String NO_POSTS_OR_NO_COMMENTS_MESSAGE = "Social distancing has reached fakebook. Please post something.";

    /* Constructor */
    public NoPostsOrNoCommentsException(){super(NO_POSTS_OR_NO_COMMENTS_MESSAGE);}
}