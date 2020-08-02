package comments;

import messages.Message;

/**
 * This interface represents a comment
 * @author Clara Sousa - 58403 / Bruna Arroja - 56751
 *
 */

public interface Comment {


    /* Getters */

    /**
     * Returns the user who made the comment
     * @return user who made the comment
     */
    public String getCommenter();

    /**
     * Returns the message of a certain comment
     * @return message
     */
    public String getContent();


    /**
     * Returns the stance of the certain comment
     * @return stance
     */
    public abstract String getStance();

    /**
     * Returns the message where the comment is
     * @return post
     */
    public Message getMessage();

}
