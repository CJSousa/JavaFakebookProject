package comments;
import java.util.ArrayList;
import messages.*;
import java.util.List;

/**
 * This class represents a comment
 * @author Clara Sousa - 58403 / Bruna Arroja - 56751
 *
 */

public abstract class CommentClass implements Comment{

    /* Constants */

    /* Global Variables */
    private Message post;
    private String userWhoCommentsId, message;
    protected String stance;
    private List<String> hashtags;

    /* Constructor */
    public CommentClass(Message post, String userWhoCommentsId, String content){
        this.post = post;
        this.userWhoCommentsId = userWhoCommentsId;
        this.message = content;
        this.hashtags = new ArrayList<String>();
    }

    /* Getters */

    @Override
    public String getCommenter(){return this.userWhoCommentsId; }

    @Override
    public String getContent() {return this.message; }

    @Override
    public abstract String getStance();

    @Override
    public Message getMessage(){return this.post;}


}
