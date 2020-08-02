package users.exceptions;

/**
 * This exceptions is thrown when a user has no access to a post, since it was not
 * friends with the author of the post when the post was published
 * @author Clara Sousa - 58403 / Bruna Arroja - 56751
 *
 */

public class UserHasNoAccessToPostException extends Exception {

    /* Constants */
    private static final String NO_ACCESS_MESSAGE = " has no access to post ";
    private static final String BY = " by ";
    private static final String EXCLAMATION_MARK = "!";

    public UserHasNoAccessToPostException(String userId, int postId, String authorId){
        super(userId+NO_ACCESS_MESSAGE+postId+BY+authorId+EXCLAMATION_MARK);
    }

}
