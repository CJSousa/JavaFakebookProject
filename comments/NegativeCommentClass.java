package comments;
import messages.*;

/**
 * This class represents a negative comment
 * @author Clara Sousa - 58403 / Bruna Arroja - 56751
 *
 */

public class NegativeCommentClass extends CommentClass implements Comment {

    /* Constants */
    private static final String STANCE = "negative";

    /* Constructor */
    public NegativeCommentClass(Message post, String userWhoCommentsId, String content) {
        super(post, userWhoCommentsId, content);
        this.stance = STANCE;
    }

    /* Getters */

    @Override
    public String getStance() {
        return STANCE;
    }

}
