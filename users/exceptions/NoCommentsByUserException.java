package users.exceptions;

/**
 * This exceptions is thrown when a user didnt make any comments
 * @author Clara Sousa - 58403 / Bruna Arroja - 56751
 *
 */
public class NoCommentsByUserException extends Exception {

	/*Constants*/

    private static final String NO_COMMENTS = "No comments!";


	/* Constructor*/

    public NoCommentsByUserException( ){ super(NO_COMMENTS); }

}