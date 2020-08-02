package fakebook.exceptions;

/**
 * This exceptions is thrown when a user has not yet made any posts
 * @author Clara Sousa - 58403 / Bruna Arroja - 56751
 *
 */
public class NoPostsException extends Exception{

    /* Constant */
    private static final String NO_POSTS_MESSAGE = " has no posts!";

    /* Constructor */
    public NoPostsException(String id){super(id+NO_POSTS_MESSAGE);}
}
