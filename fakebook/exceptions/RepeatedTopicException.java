package fakebook.exceptions;

/**
 * This exceptions is thrown when a topic was already inserted in the list of interests
 * @author Clara Sousa - 58403 / Bruna Arroja - 56751
 *
 */

public class RepeatedTopicException extends Exception{

    /* Constant */
    private static final String INVALID_FANATICISM_LIST_MESSAGE = "Invalid fanaticism list!";

    /* Constructor */
    public RepeatedTopicException(){ super(INVALID_FANATICISM_LIST_MESSAGE);}
}
