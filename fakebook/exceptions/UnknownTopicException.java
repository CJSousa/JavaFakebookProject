package fakebook.exceptions;

public class UnknownTopicException extends Exception {

    /**
     * This exceptions is thrown when no fanatic users are fanatic about such topic
     * @author Clara Sousa - 58403 / Bruna Arroja - 56751
     *
     */

    /* Constants */

    private final static String UNKNOWN = "Oh please, who would be a fanatic of ";
    private final static String EXCLAMATION = "?";

    /* Constructor */

    public UnknownTopicException(String topic) { super(UNKNOWN + topic + EXCLAMATION); }
}