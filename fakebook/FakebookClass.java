package fakebook;

import comparators.*;
import fakebook.exceptions.*;
import fakebook.exceptions.NoCommentsException;
import topicsOfFanaticism.*;
import users.exceptions.*;
import usersEnum.*;
import users.*;

import java.util.*;
import messages.*;
import comments.*;

/**
 * This is the top class of the program
 *
 * @author Clara Sousa - 58403 / Bruna Arroja - 56751
 *
 */

public class FakebookClass implements Fakebook {

    /* Constants */
    private static final String SELF_CENTERED = "selfcentered";
    private static final String NAIVE = "naive";
    private static final String LIAR = "liar";
    private static final String FANATIC = "fanatic";

    private static final String HONEST_MESSAGE = "honest";
    private static final String FAKE_MESSAGE = "fake";

    private static final String POSITIVE_COMMENT = "positive";
    private static final String NEGATIVE_COMMENT = "negative";

    private static final int DEFAULT_VALUE = 0;

    /* Global Variables */
    private Map<String, User> users;

    /* Constructor */
    public FakebookClass() {
        this.users = new TreeMap<String, User>();
    }

    /* Getting objects */

    @Override
    public User getUser(String userID) {
        return this.users.get(userID);
    }

    @Override
    public Message getPost(String userID, int postID) throws UserHasNoPostException {
        User user = getUser(userID);
        return user.getMessage(postID);
    }

    /* Checking */

    @Override
    public boolean userIsFanatic(String kind) {
        return kind.equals(FANATIC);
    }

    @Override
    public boolean checkKindIsKnown(String kind) {
        for (UsersKind userKind : UsersKind.values()) {
            if (userKind.getKindOfUser().equalsIgnoreCase(kind)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean userExists(String id) {
        return users.containsKey(id);
    }

    @Override
    public boolean noPosts(String id) {
        return getUser(id).noPosts();
    }

    /* Commands */

    @Override
    public void addUser(String id, String kind) throws UnknownUserKindException, UserAlreadyExistsException {
        if (!checkKindIsKnown(kind)) {
            throw new UnknownUserKindException(kind);
        } else if (users.containsKey(id)) {
            throw new UserAlreadyExistsException(id);
        } else {
            switch (kind) {
                case SELF_CENTERED:
                    this.users.put(id, new SelfCenteredUserClass(id));
                    break;
                case NAIVE:
                    this.users.put(id, new NaiveUserClass(id));
                    break;
                case LIAR:
                    this.users.put(id, new LiarUserClass(id));
                    break;
            }
        }
    }

    @Override
    public void addFanaticUser(String userID, List<Topic> topicsOfInterest) {
        users.put(userID, new FanaticUserClass(userID, topicsOfInterest));
    }

    @Override
    public void addFriend(String id1, String id2)
            throws UserDoesntExistException, SameUserException, UserAlreadyHasFriendException {
        if (!users.containsKey(id1)) {
            throw new UserDoesntExistException(id1);
        } else if (!users.containsKey(id2)) {
            throw new UserDoesntExistException(id2);
        } else if (id1.equals(id2)) {
            throw new SameUserException(id1);
        } else {
            User user1 = this.getUser(id1);
            User user2 = this.getUser(id2);

            if (user1.hasContact(user2)) {
                throw new UserAlreadyHasFriendException(id1, id2);
            } else {
                this.getUser(id1).addContact(user2);
                this.getUser(id2).addContact(user1);
            }
        }
    }

    @Override
    public void post(String id, int numTopics, List<String> topicsList, String truthfulness, String messageContent)
            throws UserDoesntExistException, InvalidHashtagListException, InadequateStanceException {
        if (!users.containsKey(id)) {
            throw new UserDoesntExistException(id);
        } else if (numTopics < DEFAULT_VALUE) {
            throw new InvalidHashtagListException();
        } else if (getUser(id).getKind().equals(LIAR) && truthfulness.equals(HONEST_MESSAGE)) {
            throw new InadequateStanceException();
        } else {
            User user = getUser(id);
            if (userIsFanatic(user.getKind())) {
                addPostToFanaticUser(user, numTopics, truthfulness, topicsList, messageContent);
            } else {
                user.createNewMessage(truthfulness, topicsList, messageContent);
            }
        }
    }

    @Override
    public void addPostToFanaticUser(User user, int numTopics, String truthfulness, List<String> topicsList,
                                     String messageContent) throws InadequateStanceException {
        FanaticUser fanaticUser = (FanaticUser) user;
        if (fanaticUser.inconsistentStances(truthfulness, topicsList)) {
            throw new InadequateStanceException();
        } else {
            user.createNewMessage(truthfulness, topicsList, messageContent);
        }
    }

    @Override
    public void comment(String commenterId, String authorId, int postId, String stance, String comment)
            throws UserDoesntExistException, UserHasNoPostException, CannotCommentException,
            UserHasNoAccessToPostException, InvalidCommentStanceException {
        if (!users.containsKey(commenterId)) {
            throw new UserDoesntExistException(commenterId);
        } else if (!users.containsKey(authorId)) {
            throw new UserDoesntExistException(authorId);
        } else {
            commentByKind(getUser(commenterId), authorId, postId, stance, comment);
        }
    }

    @Override
    public Iterator<Comment> commentsMadeByUserIterator(String id, String hashtag)
            throws UserDoesntExistException, NoCommentsByUserException {
        if (!users.containsKey(id)) {
            throw new UserDoesntExistException(id);
        } else {
            User user = this.getUser(id);
            Iterator<Comment> commentsOnHashtag = user.userPostsWithHashtag(hashtag);
            return commentsOnHashtag;
        }
    }

    @Override
    public Iterator<FanaticUser> fanaticsWithThisTopicIterator(String hashtag) throws UnknownTopicException {

        List<FanaticUser> fanaticUsersList = new LinkedList<FanaticUser>();
        Iterator<User> usersIt = getUsersIterator();

        while (usersIt.hasNext()) {
            User user = usersIt.next();
            if (user.getKind().equals(FANATIC)) {
                FanaticUser fanaticUser = (FanaticUser) user;
                if (fanaticUser.userIsFanaticAbout(hashtag)) {
                    fanaticUsersList.add(fanaticUser);
                }
            }
        }

        if (fanaticUsersList.size() == DEFAULT_VALUE) {
            throw new UnknownTopicException(hashtag);
        } else {
            Collections.sort(fanaticUsersList, new ComparatorByAlphabeticOrderToFanatics());
            return fanaticUsersList.iterator();
        }
    }

    @Override
    public Iterator<Message> topicPosts(String hashtag) throws UnknownTopicInPostsException {
        Iterator<User> usersIterator = getUsersIterator();
        List<Message> messagesWithHashtag = new ArrayList<Message>();

        while (usersIterator.hasNext()) {
            User user = usersIterator.next();
            Iterator<Message> messagesOfUser = user.iteratePosts();

            while (messagesOfUser.hasNext()) {
                Message msg = messagesOfUser.next();
                if (msg.messageHasHashtag(hashtag)) {
                    messagesWithHashtag.add(msg);
                }
            }
        }

        if (messagesWithHashtag.size() == DEFAULT_VALUE) {
            throw new UnknownTopicInPostsException(hashtag);
        } else {
            Collections.sort(messagesWithHashtag, new ComparatorForListingTopicsWithHashtag());
            return messagesWithHashtag.iterator();
        }

    }

    @Override
    public Message popularPost() throws NoPostsOrNoCommentsException {
        int maxNumberOfPosts = DEFAULT_VALUE;
        int numberOfUsersWithPosts = DEFAULT_VALUE;
        int numberOfPostsWithComments = DEFAULT_VALUE;
        Message messageWithMostComments = null;
        Iterator<User> usersIterator = getUsersIterator();

        while (usersIterator.hasNext()) {

            User user = usersIterator.next();
            if (!user.noPosts()) {
                numberOfUsersWithPosts++;

                Iterator<Message> messageIterator = user.iteratePosts();

                while (messageIterator.hasNext()) {
                    Message msg = messageIterator.next();

                    if (!msg.noComments()) {
                        numberOfPostsWithComments++;
                        int numPosts = msg.getNumComments();
                        if (numPosts > maxNumberOfPosts) {
                            messageWithMostComments = msg;
                            maxNumberOfPosts = numPosts;
                        } else if (numPosts == maxNumberOfPosts && msg.getAuthorOfMessage()
                                .compareTo(messageWithMostComments.getAuthorOfMessage()) < 0) {
                            messageWithMostComments = msg;
                            maxNumberOfPosts = numPosts;
                        } else if (numPosts == maxNumberOfPosts
                                && msg.getAuthorOfMessage().compareTo(messageWithMostComments.getAuthorOfMessage()) == 0
                                && msg.getPostId() > messageWithMostComments.getPostId()) {
                            messageWithMostComments = msg;
                            maxNumberOfPosts = numPosts;
                        }
                    }
                }
            }

        }
        if (numberOfPostsWithComments == DEFAULT_VALUE || numberOfUsersWithPosts == DEFAULT_VALUE) {
            throw new NoPostsOrNoCommentsException();
        }
        return messageWithMostComments;

    }

    @Override
    public User topPoster() throws NoPostsOnFakebookException {
        int maxNumberOfPosts = DEFAULT_VALUE;
        User topUser = null;
        int posts = DEFAULT_VALUE;
        Iterator<User> usersIterator = getUsersIterator();

        while (usersIterator.hasNext()) {
            User user = usersIterator.next();
            if (!user.noPosts()) {
                posts++;
                if (user.getNumPosts() > maxNumberOfPosts) {
                    topUser = user;
                    maxNumberOfPosts = user.getNumPosts();
                } else if (user.getNumPosts() == maxNumberOfPosts && user.getNumComments() > topUser.getNumComments()) {
                    topUser = user;
                    maxNumberOfPosts = user.getNumPosts();
                } else if (user.getNumPosts() == maxNumberOfPosts && user.getNumComments() == topUser.getNumComments()
                        && user.getId().compareTo(topUser.getId()) < 0) {
                    topUser = user;
                    maxNumberOfPosts = user.getNumPosts();
                }
            }
        }
        if (posts == 0) {
            throw new NoPostsOnFakebookException();
        } else {
            return topUser;
        }
    }

    @Override
    public User getMostResponsiveUser() throws NoPostsDueToSocialDistancingException {
        User mostResponsiveUser = null;
        float higherPercentage = DEFAULT_VALUE;
        int usersWithComments = DEFAULT_VALUE;
        Iterator<User> usersIterator = getUsersIterator();

        if(numPostsInSystem() == 0) {
            throw new NoPostsDueToSocialDistancingException();
        }

        while (usersIterator.hasNext()) {
            User user = usersIterator.next();
            if (!user.noComments()) {
                usersWithComments++;
                if (mostResponsiveUser == null) {
                    mostResponsiveUser = user;
                }

                if (getPercentageOfCommentsInTotalPosts(user.getId()) > higherPercentage) {
                    mostResponsiveUser = user;
                    higherPercentage = getPercentageOfCommentsInTotalPosts(user.getId());
                } else if (getPercentageOfCommentsInTotalPosts(user.getId()) == higherPercentage
                        && user.getId().compareTo(mostResponsiveUser.getId()) < 0) {
                    mostResponsiveUser = user;
                    higherPercentage = getPercentageOfCommentsInTotalPosts(user.getId());
                }
            }
        }

        if (numPostsInSystem() == DEFAULT_VALUE || usersWithComments == DEFAULT_VALUE) {
            throw new NoPostsDueToSocialDistancingException();
        }

        return mostResponsiveUser;
    }

    @Override
    public User getShamelessUser() throws NoLiesYetException {
        int numLies = DEFAULT_VALUE;
        User shameless = null;
        Iterator<User> usersIterator = getUsersIterator();

        while (usersIterator.hasNext()) {
            User liar = usersIterator.next();

            if (shameless == null) {
                shameless = liar;
            }
            if (liar.getNumLies() > numLies) {
                numLies = liar.getNumLies();
                shameless = liar;
            } else if (liar.getNumLies() == numLies
                    && liar.getTotalPostsAndComments() < shameless.getTotalPostsAndComments()) {
                numLies = liar.getNumLies();
                shameless = liar;
            } else if (liar.getNumLies() == numLies
                    && liar.getTotalPostsAndComments() == shameless.getTotalPostsAndComments()
                    && liar.getId().compareTo(shameless.getId()) < 0) {
                numLies = liar.getNumLies();
                shameless = liar;
            }

        }
        if (numLies == DEFAULT_VALUE) {
            throw new NoLiesYetException();
        } else {
            return shameless;
        }

    }

    /* Listing Methods */

    @Override
    public Iterator<User> usersIterator() throws NoUsersException {
        if (users.isEmpty()) {
            throw new NoUsersException();
        } else {
            List<User> usersList = new LinkedList<User>(users.values());
            Collections.sort(usersList, new ComparatorByAlphabeticOrder());
            return usersList.iterator();
        }
    }

    @Override
    public Iterator<User> friendsIterator(String id) throws UserDoesntExistException, NoContactsException {
        if (!users.containsKey(id)) {
            throw new UserDoesntExistException(id);
        } else {
            User user = this.getUser(id);
            return user.iterateContacts();
        }
    }

    @Override
    public Iterator<Message> postsIterator(String id) throws UserDoesntExistException {
        if (!users.containsKey(id)) {
            throw new UserDoesntExistException(id);
        } else {
            User user = this.getUser(id);
            return user.iteratePosts();
        }
    }

    @Override
    public Iterator<Comment> commentsIterator(Message post) throws NoCommentsException {
        if (post.noComments()) {
            throw new NoCommentsException();
        } else {
            return post.iterateComments();
        }
    }

    /* Auxiliary Methods */

    /* Auxiliary Methods for Comment Command */

    @Override
    public void commentByKind(User commenter, String authorId, int postId, String stance, String comment)
            throws UserHasNoPostException, CannotCommentException, UserHasNoAccessToPostException,
            InvalidCommentStanceException {

        String commenterKind = commenter.getKind();

        switch (commenterKind) {
            case SELF_CENTERED:
                selfCenteredComments(commenter, authorId, postId, stance, comment);
                break;
            case NAIVE:
                naiveComments(commenter, authorId, postId, stance, comment);
                break;
            case LIAR:
                liarComments(commenter, authorId, postId, stance, comment);
                break;
            case FANATIC:
                fanaticComments(commenter, authorId, postId, stance, comment);
                break;
        }
    }

    @Override
    public void selfCenteredComments(User commenter, String authorId, int postId, String stance, String comment)
            throws CannotCommentException, UserHasNoPostException, UserHasNoAccessToPostException {
        if (!commenter.getId().equals(authorId) || !stance.equals(POSITIVE_COMMENT)) {
            throw new CannotCommentException(commenter.getId());
        } else {
            getUser(authorId).createCommentInPost(commenter, postId, stance, comment);
        }
    }

    @Override
    public void naiveComments(User commenter, String authorId, int postId, String stance, String comment)
            throws CannotCommentException, UserHasNoPostException, UserHasNoAccessToPostException {
        if (stance.equals(NEGATIVE_COMMENT)) {
            throw new CannotCommentException(commenter.getId());
        } else {
            getUser(authorId).createCommentInPost(commenter, postId, stance, comment);
        }
    }

    @Override
    public void liarComments(User commenter, String authorId, int postId, String stance, String comment)
            throws InvalidCommentStanceException, UserHasNoPostException, UserHasNoAccessToPostException {

        String messageTruthfulness = getUser(authorId).getMessage(postId).getTruthfulness();

        if (messageTruthfulness.equals(HONEST_MESSAGE) && stance.equals(POSITIVE_COMMENT)) {
            throw new InvalidCommentStanceException();
        } else if (messageTruthfulness.equals(FAKE_MESSAGE) && stance.equals(NEGATIVE_COMMENT)) {
            throw new InvalidCommentStanceException();
        } else {
            getUser(authorId).createCommentInPost(commenter, postId, stance, comment);
        }
    }

    @Override
    public void fanaticComments(User commenter, String authorId, int postId, String stance, String comment)
            throws UserHasNoPostException, UserHasNoAccessToPostException, InvalidCommentStanceException {
        FanaticUser fanaticUser = (FanaticUser) commenter;
        Message post = getUser(authorId).getMessage(postId);
        if (!fanaticUser.checkFanaticUserCanComment(post, stance)) {
            throw new InvalidCommentStanceException();
        } else {
            getUser(authorId).createCommentInPost(commenter, postId, stance, comment);
        }
    }


    /* Auxiliary Method to Responsive Command */

    /**
     * Returns, in percentage, the number of the total comments made by a user
     * over the number of posts the user has access to
     * @param userID - id of the user
     * @return percentage of comments in total posts
     * @Pre: userExists(userID)
     */
    private float getPercentageOfCommentsInTotalPosts(String userID) {
        int numPostsUserHasAccess = getUser(userID).getNumPostsUserHasAccessTo();
        int numCommentsMade = DEFAULT_VALUE;
        float percentage;

        Iterator<User> userIt = users.values().iterator();

        while (userIt.hasNext()) {
            User user = userIt.next();
            Iterator<Message> postIt = user.iteratePosts();
            while (postIt.hasNext()) {
                Message post = postIt.next();
                Iterator<Comment> commentIt = post.iterateComments();
                while (commentIt.hasNext()) {
                    Comment comment = commentIt.next();
                    if (comment.getCommenter().equals(userID)) {
                        numCommentsMade++;
                        break;
                    }
                }
            }
        }

        percentage = ((float)numCommentsMade / (float) numPostsUserHasAccess) * 100;

        return percentage;
    }

    /* Auxiliary method for using the users iterator */

    /**
     * Returns the iterator with all the users on fakebook
     * @return iterator with all users
     */
    private Iterator<User> getUsersIterator(){
        List<User> usersList = new LinkedList<User>(users.values());
        Iterator<User> usersIterator = usersList.iterator();
        return usersIterator;
    }

    /* Other auxiliary methods */

    /**
     * Returns the number of posts on fakebook
     * @return total number of posts by all users
     */
    private int numPostsInSystem() {
        int i = DEFAULT_VALUE;
        Iterator<User> userIt = users.values().iterator();

        while (userIt.hasNext()) {
            User user = userIt.next();
            i += user.getNumPosts();
        }

        return i;
    }
}
