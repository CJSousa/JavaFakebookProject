package users;

import users.exceptions.*;
import messages.Message;
import comments.*;
import java.util.Iterator;
import java.util.List;


/**
 * This interface represents a user
 * @author Clara Sousa - 58403 / Bruna Arroja - 56751
 *
 */

public interface User {

    /* Getters */

    /**
     * Returns the id of the user
     * @return id
     */
    public String getId();

    /**
     * Returns the kind of a user
     * @return kind
     */
    public abstract String getKind();

    /**
     * Returns the post a user has, based on the post id
     * @pre: posts.contains(postId) -- The exception is thrown
     * @param postId - id of the post we wish to return
     * @return message
     * @throws UserHasNoPostException - thrown when a user does not have a given post
     */
    public Message getMessage(int postId) throws UserHasNoPostException;

    /**
     * Returns the number of friends (contacts) the user has
     * @return number of contact
     */
    public int getNumFriends();

    /**
     * Returns the number of comments the user has made
     * @return number of comments
     */
    public int getNumComments();

    /**
     * Returns the number of messages the user has made
     * @return number of messages made
     */
    public int getNumPosts();

    /**
     * Returns the number of messages to which the user can access to
     * @return number of messages the user has access to
     */
    public int getNumPostsUserHasAccessTo();

    /**
     * Returns the number of lies the user has made
     * @return number of lies
     */
    public int getNumLies();

    /**
     * Returns the sum of all the posts the user has made with the comments made
     * @return sum of posts and comments made by the user
     */
    public int getTotalPostsAndComments();

    /* Checking */

    /**
     * Checks whether the user already has a certain contact
     * @param user - user whose friendship is to be checked
     * @return true if the user has this friend, false if otherwise
     */
    public boolean hasContact(User user);

    /**
     * Checks whether the user has not yet made any posts
     * @return if the user has not yet made any posts
     */
    public boolean noPosts();

    /**
     * Checks whether a user has access to a certain message
     * @param user - user whose permission for access is to be checked
     * @param postId - id of the message
     * @return true if the user has access to the message, false if otherwise
     */
    public boolean contactHasAccessToPost(User user, int postId);


    /* Others */

    /**
     * Adds a friend to the user's contact list, taking the alphabetic order into consideration
     * @param user - user to be added
     */
    public void addContact(User user);

    /**
     * Creates a new message, by adding the author of the message and his/her contacts
     * to the group of users with access to it
     * Also increases the number of lies if the message to be created is fake
     * @param truthfulness - truthfulness of the message (honest or fake)
     * @param hashtagList - list of topics (in hashtags) in the new message
     * @param messageContent - content of the new message
     */
    public void createNewMessage(String truthfulness,  List<String> hashtagList, String messageContent);

    /**
     * Creates a new comment in a certain message
     * Calls for a method to check if the number of lies is to be increased
     * @param userWhoComments - user who is writing the comment
     * @param postId - id of the message where the comment is being written
     * @param stance - stance of the comment (negative or positive)
     * @param content - content of the new comment
     * @throws UserHasNoPostException - thrown when a user does not have a given post
     */
    public void createCommentInPost(User userWhoComments, int postId, String stance, String content)
            throws UserHasNoPostException, UserHasNoAccessToPostException;


    /* Listing */

    /**
     * Returns the iterator with the contacts of the user, in alphabetic order
     * @return iterator of contacts
     * @throws NoContactsException - thrown when a user has no friends
     */
    public Iterator<User> iterateContacts() throws NoContactsException;

    /**
     * Returns the iterator with the posts made by the user, in the order they were performed
     * @return iterator of posts
     */
    public Iterator<Message> iteratePosts();

    /**
     * Returns the iterator of all comments in messages with a certain topic
     * @param hashtag - hashtag in the given topic
     * @return  iterator of all comments in messages with a certain topic
     * @throws NoCommentsByUserException - thrown when a user didnt make any comments
     */
    public Iterator<Comment> userPostsWithHashtag(String hashtag) throws NoCommentsByUserException;

    /* Auxiliary Methods */

    /**
     * Adds a comment to the list of comments a user has made
     * @param comment - comment to be added
     */
    public void addComment(Comment comment);

    /**
     * Checks whether a user has made no comments yet
     * @return true if the user has made no comments yet
     */
    public boolean noComments();

    /**
     * Increments the number of lies
     */
    public void addLie();

}
