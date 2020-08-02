package fakebook.exceptions;

public class UserAlreadyHasFriendException extends Exception{

    /* Constant */
    private static final String ALREADY_HAS_FRIEND_MESSAGE = " must really admire ";
    private static final String EXCLAMATION = "!";

    /* Constructor */
    public UserAlreadyHasFriendException(String id1, String id2){

        super(id1 + ALREADY_HAS_FRIEND_MESSAGE + id2+EXCLAMATION);
    }
}
