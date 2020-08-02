package comments;

import messages.Message;

/**
 * This class represents a positive comment
 * @author Clara Sousa - 58403 / Bruna Arroja - 56751
 *
 */

public class PositiveCommentClass extends CommentClass implements Comment {

    /* Constants */
    private static final String STANCE = "positive";

    /* Constructor */
    public PositiveCommentClass(Message post, String userWhoCommentsId, String content) {
        super(post, userWhoCommentsId, content);
        this.stance = STANCE;
    }

    /* Getters */

    @Override
    public String getStance() {
        return STANCE;
    }

}
