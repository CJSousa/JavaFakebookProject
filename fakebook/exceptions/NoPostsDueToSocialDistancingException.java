package fakebook.exceptions;

/**
 * This exceptions is thrown when there are no posts on fakebook
 * @author Clara Sousa - 58403 / Bruna Arroja - 56751
 *
 */

public class NoPostsDueToSocialDistancingException extends Exception {

    /* Constant */
    private static final String NO_POSTS_ON_BOOK =
            "Social distancing has reached fakebook. Post something and then comment your own post to become the king of responsiveness.";

    /* Constructor */
    public NoPostsDueToSocialDistancingException(){super(NO_POSTS_ON_BOOK);}
}
