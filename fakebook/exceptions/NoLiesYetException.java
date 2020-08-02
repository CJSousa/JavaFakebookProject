package fakebook.exceptions;

/**
 * This exceptions is thrown if no lies have yet been told
 * @author Clara Sousa - 58403 / Bruna Arroja - 56751
 *
 */

public class NoLiesYetException extends Exception{

    /* Constant */
    private static final String NO_LIES_MESSAGE =
            "Social distancing has reached fakebook. Post a lie and become the king of liars.";

    /* Constructor */
    public NoLiesYetException(){ super(NO_LIES_MESSAGE);}
}
