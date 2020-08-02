package messages;

import comments.Comment;
import users.User;

import java.util.Iterator;
import java.util.List;

/**
 * This interface represents a message to be posted
 * @author Clara Sousa - 58403 / Bruna Arroja - 56751
 *
 */

public interface Message {

    /* Getters */

    /**
     *Returns the author id of the message
     * @return authorOfMessage
     */
    public String getAuthorOfMessage();

    /**
     * Returns the id of the post
     * @return postId
     */
    public int getPostId();

    /**
     * Returns the truthfulness of the message (honest or fake)
     * @return truthfulness of the message
     */
    public abstract String getTruthfulness();

    /**
     * Returns the message content in the post
     * @return messageContent
     */
    public String getPostContent();

    /**
     * Returns the number of comments in the post
     * @return number of comments
     */
    public int getNumComments();

    /* Checking */

    /**
     * Checks if the post can be accessed by a certain user
     * @param user
     * @return true if the post can be accessed by the user, false if otherwise
     */
    public boolean postCanBeAccessedBy(User user);


    /**
     * Checks if a post doesnt have comments
     * @return true if the post doenst have comments
     */
    public boolean noComments();

    /**
     * Checks if the message has a certain hashtag
     * @param hashtag
     * @return true if it does, false if it doesnt
     */
    public boolean messageHasHashtag(String hashtag);


    /* Others */

    /**
     * Adds a comment to the list of comments on the post, based on it being a positive or a negative comment
     * @param userWhoComments - id of the user who makes the comment
     * @param stance - positive or negative
     * @param content - content of the comment
     * @return
     */
    public Comment addComment(String userWhoComments, String stance, String content);

    /**
     * Adds a user to the list of users with access to the post
     * @param user - user to be added
     */
    public void addUsersWithAccess(User user);


    /* Iterators */

    /**
     * Returns an iterator with the hashtags (topics) in the message
     * @return iterator with topics
     */
    public Iterator<String> iterateHashtags();

    /**
     * Returns an iterator with the comments in the message
     * @return iterator with comments
     */
    public Iterator<Comment> iterateComments();

}
