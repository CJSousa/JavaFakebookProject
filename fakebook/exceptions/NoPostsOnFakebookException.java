package fakebook.exceptions;

/**
 * This exceptions is thrown when fakebook has no posts
 * @author Clara Sousa - 58403 / Bruna Arroja - 56751
 *
 */
public class NoPostsOnFakebookException extends Exception{

    /* Constant */
    private static final String NO_POSTS_MESSAGE = "Social distancing has reached fakebook. " +
            "Post something to become the king of posters.";

    /* Constructor */
    public NoPostsOnFakebookException(){super(NO_POSTS_MESSAGE);}
}