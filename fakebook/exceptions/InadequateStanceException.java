package fakebook.exceptions;

/**
 * This exceptions is thrown if there is an inconsistency between the user's stance and that of the message
 * @author Clara Sousa - 58403 / Bruna Arroja - 56751
 *
 */

public class InadequateStanceException extends Exception {

    /* Constant */
    private static final String INADEQUATE_STANCE_EXCEPTION = "Inadequate stance!";

    /* Constructor */
    public InadequateStanceException(){super(INADEQUATE_STANCE_EXCEPTION);}
}
