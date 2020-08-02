package users.exceptions;

/**
 * This exceptions is thrown when a user does not have a given post
 * @author Clara Sousa - 58403 / Bruna Arroja - 56751
 *
 */

public class UserHasNoPostException extends Exception{

    /* Constant */
    private static final String USER_NO_POST_MESSAGE = " has no post ";
    private static final String EXCLAMATION = "!";

    /* Constructor */
    public UserHasNoPostException(String userId, int postId){
        super(userId+USER_NO_POST_MESSAGE+postId+EXCLAMATION);
    }
}
