package users.exceptions;

/**
 * This exceptions is thrown when a user has no friends
 * @author Clara Sousa - 58403 / Bruna Arroja - 56751
 *
 */

public class NoContactsException extends Exception{

    /* Constants */
    private static final String NO_CONTACTS_MESSAGE = " has no friends!";

    /* Constructor */
    public NoContactsException(String id) { super(id+NO_CONTACTS_MESSAGE);}
}
