package fakebook;
import fakebook.exceptions.*;
import messages.Message;
import fakebook.exceptions.NoCommentsException;
import topicsOfFanaticism.Topic;
import users.*;;
import users.exceptions.*;
import comments.*;
import java.util.Iterator;
import java.util.List;

/**
 * This interface represents the top class of the program
 * @author Clara Sousa - 58403 / Bruna Arroja - 56751
 *
 */

public interface Fakebook {

    /* Getting Objects */

    /**
     * Returns the user object correspondent to a certain user id
     * @param userID - id of the user we wish to get
     * @return user correspondent to the object
     * @Pre: userID!=null
     */
    public User getUser(String userID);

    /**
     * Returns the post object correspondent to a certain post id
     * @param userID - id of the author of the post
     * @param postID - id of the post
     * @return post orrespondent to the object
     * @Pre: userExists(userID) && getUser(userID) has the message with the message id
     */
    public Message getPost(String userID, int postID) throws UserHasNoPostException;

    /* Checking */

    /**
     * Checks if a user is of kind fanatic
     * @param kind - kind of user
     * @return true if the user is fanatic, false if otherwise
     * @Pre: kind!=null
     */
    public boolean userIsFanatic(String kind);


    /**
     * Checks whether a kind is valid or not
     * @param kind - kind of user to be checked
     * @return true if the kind is valid, false if it is invalid
     * @Pre: kind!=null
     */
    public boolean checkKindIsKnown(String kind);

    /**
     * Checks whether a user exists
     * @param id - id of the user
     * @return true if the user exists in fakebook, false if otherwise
     * @Pre: id!=null
     */
    public boolean userExists(String id);

    /**
     * Checks whether a user has no posts
     * @param id - id of the user whose posts' existence we wish to check
     * @return true if the user has made no posts, false if he/she has
     * @Pre: userExists(id)
     */
    public boolean noPosts(String id);

    /**
     * Returns the user who has lied the most
     * @return user who has lied the most
     * @throws NoLiesYetException - thrown if no lies have yet been told
     */
    public User getShamelessUser() throws NoLiesYetException;


    /* Commands */

    /**
     * Adding a user to the system, taking the user's kind into account
     * @param id   - user's id
     * @param kind - kind of user (self-centered, naive, liar or fanatic)
     * @Pre: id!=null && kind!=null
     * @throws UnknownUserKindException thrown if the user kind inserted in the system is not valid
     * @throws UserAlreadyExistsException thrown if the id inserted in the system already belongs to a user
     * @Pre: userExists(id) && kind!=null
     */
    public void addUser(String id, String kind) throws UnknownUserKindException, UserAlreadyExistsException;

    /**
     * Adds the topic a user is interested in to the group (array list) of the user's interests
     * @param userID - id of the user
     * @param topicsOfInterest list of topics the user is interested in
     * @throws RepeatedTopicException - thrown when a topic was already inserted in the list of interests
     * @Pre: userExists(userID)
     */
    public void addFanaticUser(String userID, List<Topic> topicsOfInterest) throws RepeatedTopicException;

    /**
     * Allows a fanatic user to create a message
     * @param user - user
     * @param numTopics - number of topics in the message to be created
     * @param truthfulness - whether the message is honest or fake
     * @param topicsList - list of topics in the message to be created
     * @param messageContent - content of the message
     * @throws InadequateStanceException - thrown if there is an inconsistency between
     * the user's stance and that of the message
     * @Pre: userExists(user.getId()) && messageContent != null
     */
    public void addPostToFanaticUser(User user, int numTopics, String truthfulness, List<String> topicsList,
                                     String messageContent) throws InadequateStanceException;

    /**
     * Adds a friend to the system
     * @param id1 - name of one of the users
     * @param id2 - name of another user
     * @Pre: id1!=null && id2!=null
     * @throws UserDoesntExistException - thrown when an inserted string does
     * not correspond to any user in the program
     * @throws SameUserException - thrown when there is an attempt to establish
     * a friendship with a user with himself/ herself
     * @throws UserAlreadyHasFriendException - thrown when a friendship between
     * the two provided users already exists
     * @Pre: userExists(id1) && userExists(id2)
     */
    public void addFriend(String id1, String id2) throws UserDoesntExistException,
            SameUserException, UserAlreadyHasFriendException, UserDoesntExistException;

    /**
     * Posts a message
     * @param id - id of the author of the message
     * @param numTopics - number of topics in the message
     * @param truthfulness - whether the message is an honest or a fake one
     * @param topicsList - list of topics
     * @param messageContent - content of the message in the post
     * @throws UserDoesntExistException - thrown when an inserted string does
     * not correspond to any user in the program
     * @throws InvalidHashtagListException - thrown if the number of hashtags is
     * less than zero or if there are repeated hashtags
     * @throws InadequateStanceException - thrown if there is an inconsistency between
     * the user's stance and that of the post
     * @Pre: userExists(id) && messageContent != null
     */
    public void post(String id, int numTopics, List<String> topicsList, String truthfulness, String messageContent)
            throws UserDoesntExistException, InvalidHashtagListException, InadequateStanceException, UserDoesntExistException;

    /**
     * Adds a comment to a certain message
     * @param commenterId - id of the user who writes the comment
     * @param authorId - id of the author of the message where the comment is to be made
     * @param postId - id of the message
     * @param stance - whether the comment is positive or negative
     * @param comment - content of the comment
     * @throws UserDoesntExistException - thrown when an inserted string does
     * not correspond to any user in the program
     * @throws UserHasNoPostException - thrown when a user has no access to a post, since it was not
     * @throws CannotCommentException - thrown when a user is not allowed to comment on a post
     * @throws UserHasNoAccessToPostException - thrown when a user has no access to a post, since it was not
     * friends with the author of the post when the post was published
     * @throws InvalidCommentStanceException - thrown when the comment stance is invalid for
     * the user id and the post in question
     * @Pre: comment != null
     */
    public void comment (String commenterId, String authorId, int postId, String stance, String comment)
            throws UserHasNoPostException, CannotCommentException, UserDoesntExistException, UserHasNoAccessToPostException, InvalidCommentStanceException;


    /**
     * Returns an iterator of all the comments a user has written about a certain topic
     * @param id - id of the user
     * @param hashtag - name of the topic
     * @return iterator of all the comments a user has written about a certain topic
     * @throws UserDoesntExistException - thrown when a certain user id does not exist
     * @throws NoCommentsByUserException - thrown when a user didnt make any comments
     * @Pre: hashtag != null
     */
    public Iterator<Comment> commentsMadeByUserIterator(String id, String hashtag)
            throws UserDoesntExistException, NoCommentsByUserException;

    /**
     * Returns the fanatic users who are fanatic about a certain topic
     * @param hashtag - hashtag correspondent to the topic
     * @return fanatic users who are fanatic about this topic
     * @throws UnknownTopicException - when no fanatic users are fanatic about such topic
     * @Pre: hashtag != null
     */
    public Iterator<FanaticUser> fanaticsWithThisTopicIterator(String hashtag) throws UnknownTopicException;

    /**
     * Returns a list with the posts on a certain topic
     * @param hashtag - name of the topic
     * @return list with the posts on a certain topic
     * @throws UnknownTopicException - thrown when no fanatic users are fanatic about such topic
     * @Pre: hashtag != null
     */
    public Iterator<Message> topicPosts(String hashtag) throws UnknownTopicInPostsException;

    /**
     * Returns the most commented post
     * @return the most commented post
     * @throws NoPostsOrNoCommentsException - thrown when fakebook has no posts or comments yet
     */
    public Message popularPost() throws NoPostsOrNoCommentsException;

    /**
     * Returns the user who made more messages
     * @return user who made more messages
     * @throws NoPostsOnFakebookException - thrown when fakebook has no posts
     */
    public User topPoster() throws NoPostsOnFakebookException;

    /**
     * Returns the user with the higher percentage of commented posts
     * @return user with the higher percentage of commented posts
     * @throws NoPostsDueToSocialDistancingException - thrown when there are no posts on fakebook
     */
    public User getMostResponsiveUser() throws NoPostsDueToSocialDistancingException;

    /* Listing Methods */

    /**
     * Returns the users in the system, in an alphabetic order
     * @return iterator with the users in the system
     * @throws NoUsersException thrown if there are yet no users in the system
     */
    public Iterator<User> usersIterator() throws NoUsersException;

    /**
     * Lists the contacts (friends) of a certain user
     * @param id - id of the user whose contacts we wish to list
     * @Pre: id != null
     * @return iterator with the contacts of the user
     * @throws UserDoesntExistException - thrown when an inserted string does
     * not correspond to any user in the program
     * @throws NoContactsException - thrown when the user has not yet made any friends
     *  @Pre: id!=null
     */
    public Iterator<User> friendsIterator(String id) throws UserDoesntExistException, NoContactsException, UserDoesntExistException;

    /**
     * Lists the posts of a user
     * @param id - id of the user
     * @return iterator with the posts made by the user
     * @throws UserDoesntExistException - thrown when an inserted string does
     * not correspond to any user in the program
     * @throws NoPostsException - thrown when a user has not yet made any posts
     * @Pre: id != null
     */
    public Iterator<Message> postsIterator(String id) throws UserDoesntExistException, NoPostsException, UserDoesntExistException;

    /**
     * Lists the comments of a post
     * @param post - message whose comments are to be listed
     * @return iterator with the comments of a post
     * @throws NoCommentsException - thrown when a post doesnt have any comments
     * @Pre: post != null
     */
    public Iterator<Comment> commentsIterator ( Message post) throws  NoCommentsException;

    /* Auxiliary Methods */

    /* Auxiliary Methods for Comment Command */

    /**
     * Adds a comment to a certain message, taking the kind of user into account
     * @param commenter -  user who writes the comment
     * @param authorId - id of the author of the message where the comment is to be made
     * @param postId - id of the message
     * @param stance - whether the comment is positive or negative
     * @param comment - content of the comment
     * @throws UserDoesntExistException - thrown when an inserted string does
     * not correspond to any user in the program
     * @throws UserHasNoPostException - thrown when a user has no access to a post, since it was not
     * @throws CannotCommentException - thrown when a user is not allowed to comment on a post
     * @throws UserHasNoAccessToPostException - thrown when a user has no access to a post, since it was not
     * friends with the author of the post when the post was published
     * @throws InvalidCommentStanceException - thrown when the comment stance is invalid for
     * the user id and the post in question
     * @Pre: comment != null
     */
    public void commentByKind(User commenter, String authorId, int postId, String stance, String comment)
            throws UserHasNoPostException, CannotCommentException, UserHasNoAccessToPostException, InvalidCommentStanceException;

    /**
     * Adds a comment to a message if the user is self-centered
     * @param commenter -  user who writes the comment
     * @param authorId - id of the author of the message where the comment is to be made
     * @param postId - id of the message
     * @param stance - whether the comment is positive or negative
     * @param comment - content of the comment
     * @throws UserHasNoPostException - thrown when a user has no access to a post, since it was not
     * @throws CannotCommentException - thrown when a user is not allowed to comment on a post
     * @throws UserHasNoAccessToPostException - thrown when a user has no access to a post, since it was not
     * friends with the author of the post when the post was published
     * @throws InvalidCommentStanceException - thrown when the comment stance is invalid for
     * the user id and the post in question
     * @Pre: comment != null
     */
    public void selfCenteredComments(User commenter, String authorId, int postId, String stance, String comment)
            throws CannotCommentException, UserHasNoPostException, UserHasNoAccessToPostException, InvalidCommentStanceException;

    /**
     * Adds a comment to a message if the user naive
     * @param commenter -  user who writes the comment
     * @param authorId - id of the author of the message where the comment is to be made
     * @param postId - id of the message
     * @param stance - whether the comment is positive or negative
     * @param comment - content of the comment
     * @throws UserHasNoPostException - thrown when a user has no access to a post, since it was not
     * @throws CannotCommentException - thrown when a user is not allowed to comment on a post
     * @throws UserHasNoAccessToPostException - thrown when a user has no access to a post, since it was not
     * friends with the author of the post when the post was published
     * @throws InvalidCommentStanceException - thrown when the comment stance is invalid for
     * the user id and the post in question
     * @Pre: comment != null
     */
    public void naiveComments(User commenter, String authorId, int postId, String stance, String comment)
            throws CannotCommentException, UserHasNoPostException, UserHasNoAccessToPostException, InvalidCommentStanceException;

    /**
     * Adds a comment to a message if the user is a liar
     * @param commenter -  user who writes the comment
     * @param authorId - id of the author of the message where the comment is to be made
     * @param postId - id of the message
     * @param stance - whether the comment is positive or negative
     * @param comment - content of the comment
     * @throws UserHasNoPostException - thrown when a user has no access to a post, since it was not
     * @throws CannotCommentException - thrown when a user is not allowed to comment on a post
     * @throws UserHasNoAccessToPostException - thrown when a user has no access to a post, since it was not
     * friends with the author of the post when the post was published
     * @throws InvalidCommentStanceException - thrown when the comment stance is invalid for
     * the user id and the post in question
     * @Pre: comment != null
     */
    public void liarComments(User commenter, String authorId,int postId, String stance, String comment)
            throws CannotCommentException, UserHasNoPostException, UserHasNoAccessToPostException, InvalidCommentStanceException;

    /**
     * Adds a comment to a message if the user is fanatic
     * @param commenter -  user who writes the comment
     * @param authorId - id of the author of the message where the comment is to be made
     * @param postId - id of the message
     * @param stance - whether the comment is positive or negative
     * @param comment - content of the comment
     * @throws UserHasNoPostException - thrown when a user has no access to a post, since it was not
     * @throws CannotCommentException - thrown when a user is not allowed to comment on a post
     * @throws UserHasNoAccessToPostException - thrown when a user has no access to a post, since it was not
     * friends with the author of the post when the post was published
     * @throws InvalidCommentStanceException - thrown when the comment stance is invalid for
     * the user id and the post in question
     * @Pre: comment != null
     */
    public void fanaticComments(User commenter, String authorId,int postId, String stance, String comment)
            throws CannotCommentException, UserHasNoPostException, UserHasNoAccessToPostException, InvalidCommentStanceException;


}
