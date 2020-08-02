package messages;

import comments.*;
import users.User;

import java.util.*;

/**
 * This class represents a message to be posted
 *
 * @author Clara Sousa - 58403 / Bruna Arroja - 56751
 *
 */

public abstract class MessageClass implements Message {

    /* Constants */
    private static final String POSITIVE_STANCE = "positive";
    private static final String NEGATIVE_STANCE = "negative";

    /* Global Variables */
    private String authorOfMessage, messageContent;
    private int postId;
    protected String truthfulness;
    private List<String> hashtags;
    private List<Comment> comments;
    private List<User> usersWithAccessToMessage;

    /* Constructor */
    public MessageClass(String authorOfMessage, int postId, List<String> hashtags,
                        String messageContent) {
        this.authorOfMessage = authorOfMessage;
        this.postId = postId;
        this.hashtags = hashtags;
        this.messageContent = messageContent;
        this.comments = new ArrayList<Comment>();
        this.usersWithAccessToMessage = new ArrayList<User>();
    }

    /* Getters */

    @Override
    public String getAuthorOfMessage() {
        return authorOfMessage;
    }

    @Override
    public int getPostId() {
        return this.postId;
    }

    @Override
    public abstract String getTruthfulness();

    @Override
    public String getPostContent() {
        return this.messageContent;
    }

    @Override
    public int getNumComments() {
        return comments.size();
    }

    /* Checking */

    @Override
    public boolean postCanBeAccessedBy(User user) {
        return usersWithAccessToMessage.contains(user);
    }

    @Override
    public boolean noComments() { return comments.isEmpty(); }

    @Override
    public boolean messageHasHashtag(String hashtag){
        return hashtags.contains(hashtag);
    }

    /* Others */

    @Override
    public Comment addComment(String userWhoComments, String stance, String content) {
        Comment comment = null;
        switch (stance) {
            case POSITIVE_STANCE:
                comment = new PositiveCommentClass(this, userWhoComments, content);
                comments.add(comment);
                break;
            case NEGATIVE_STANCE:
                comment = new NegativeCommentClass(this, userWhoComments, content);
                comments.add(comment);
                break;
        }
        return comment;
    }

    @Override
    public void addUsersWithAccess(User user) {
        usersWithAccessToMessage.add(user);
    }


    /* Iterators */

    @Override
    public Iterator<String> iterateHashtags() {
        return hashtags.iterator();
    }

    @Override
    public Iterator<Comment> iterateComments() {
        return comments.iterator();
    }

}