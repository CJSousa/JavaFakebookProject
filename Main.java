import java.util.*;

import exceptionsForMainClass.*;
import fakebook.*;
import fakebook.exceptions.*;
import fakebook.exceptions.NoCommentsException;
import users.*;
import users.exceptions.*;
import messages.*;
import topicsOfFanaticism.*;
import comments.*;

/**
 * This class executes the Facebook program
 *
 * @author Clara Sousa - 58403 / Bruna Arroja - 56751
 *
 */
public class Main {

    /* Commands */
    private static final String HELP = "help";
    private static final String LIST_USERS = "users";
    private static final String REGISTER = "register";
    private static final String ADD_FRIEND = "addfriend";
    private static final String LIST_FRIENDS = "friends";
    private static final String POST = "post";
    private static final String LIST_POSTS = "userposts";
    private static final String COMMENT = "comment";
    private static final String READ_POST = "readpost";
    private static final String COMMENTS_BY_USER = "commentsbyuser";
    private static final String TOPIC_FANATICS = "topicfanatics";
    private static final String TOPIC_POSTS = "topicposts";
    private static final String POPULAR_POST = "popularpost";
    private static final String TOP_POSTER = "topposter";
    private static final String RESPONSIVE = "responsive";
    private static final String SHAMELESS = "shameless";
    private static final String EXIT = "exit";

    /* Messages */
    private static final String UNKNOWN = "Unknown command. Type help to see available commands.";
    private static final String IS_FRIEND = " is friend of ";
    private static final String REGISTERED = " registered.";
    private static final String ADDED_COMMENT = "Comment added!";
    private static final String POSTS = " posts:";
    private static final String GOODBYE = "Bye!";

    /* Help menu */
    private static final String COMMAND1 = "register - registers a new user";
    private static final String COMMAND2 = "users - lists all users";
    private static final String COMMAND3 = "addfriend - adds a new friend";
    private static final String COMMAND4 = "friends - lists the user friends";
    private static final String COMMAND5 = "post - posts a new message";
    private static final String COMMAND6 = "userposts - lists all posts by a user";
    private static final String COMMAND7 = "comment - user comments on a post";
    private static final String COMMAND8 = "readpost - prints detailed info on a post";
    private static final String COMMAND9 = "commentsbyuser - shows all the comments by a user on a given post";
    private static final String COMMAND10 = "topicfanatics - shows a list of fanatic users on a topic";
    private static final String COMMAND11 = "topicposts - shows a list of posts on a given topic";
    private static final String COMMAND12 = "popularpost - shows the most commented post";
    private static final String COMMAND13 = "topposter - shows the user with more posts";
    private static final String COMMAND14 = "responsive - shows the user with a higher percentage of commented posts";
    private static final String COMMAND15 = "shameless - shows the top liars";
    private static final String COMMAND16 = "help - shows the available commands";
    private static final String COMMAND17 = "exit - terminates the execution of the program";

    /* Constants */
    private static final int DEFAULT_VALUE = 0;
    private static final int MINIMAL_NUMBER_FOR_LISTING = 1;
    private static final String SPACE = " ";
    private static final String OPEN_PARENTHESES = "[";
    private static final String CLOSE_PARENTHESES = "]";
    private static final String FULL_STOP = ".";
    private static final String COMA = ",";
    private static final String TWO_DOTS = ":";


    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        Fakebook book = new FakebookClass();
        String command = "";

        do {
            command = readCommand(in);
            executeCommand(in, book, command);
        } while (!command.equals(EXIT));

        in.close();

    }

    /**
     * Reads the command which has been typed in the console
     *
     * @param in - scanner
     * @return command - command to be executed
     */

    private static String readCommand(Scanner in) {
        String command = in.next().toLowerCase();
        return command;
    }

    /**
     * Executes the commands which are typed in the console
     *
     * @param in      - scanner
     * @param book    - top class
     * @param command - command/option selected in the console
     */

    private static void executeCommand(Scanner in, Fakebook book, String command) {
        switch (command) {
            case HELP:
                help();
                break;
            case REGISTER:
                register(in, book);
                break;
            case LIST_USERS:
                listUsers(book);
                break;
            case ADD_FRIEND:
                addFriend(in, book);
                break;
            case LIST_FRIENDS:
                listFriends(in, book);
                break;
            case POST:
                post(in, book);
                break;
            case LIST_POSTS:
                listPosts(in, book);
                break;
            case COMMENT:
                comment(in, book);
                break;
            case READ_POST:
                readPost(in, book);
                break;
            case COMMENTS_BY_USER:
                commentsByUser(in, book);
                break;
            case TOPIC_FANATICS:
                showTopicFanatics(in, book);
                break;
            case TOPIC_POSTS:
                topicPosts(in, book);
                break;
            case POPULAR_POST:
                popularPost(book);
                break;
            case TOP_POSTER:
                topPoster(book);
                break;
            case RESPONSIVE:
                responsive(book);
                break;
            case SHAMELESS:
                shameless(book);
                break;
            case EXIT:
                exit();
                break;
            default:
                in.nextLine();
                System.out.println(UNKNOWN);
        }
    }

    /* Commands */

    /**
     * Returns the commands available in the application
     */
    private static void help() {

        System.out.println(COMMAND1);
        System.out.println(COMMAND2);
        System.out.println(COMMAND3);
        System.out.println(COMMAND4);
        System.out.println(COMMAND5);
        System.out.println(COMMAND6);
        System.out.println(COMMAND7);
        System.out.println(COMMAND8);
        System.out.println(COMMAND9);
        System.out.println(COMMAND10);
        System.out.println(COMMAND11);
        System.out.println(COMMAND12);
        System.out.println(COMMAND13);
        System.out.println(COMMAND14);
        System.out.println(COMMAND15);
        System.out.println(COMMAND16);
        System.out.println(COMMAND17);
    }

    /* Registering a user */

    /**
     * Registers a new user on Fakebook
     *
     * @param in   - scanner
     * @param book - top class
     */
    private static final void register(Scanner in, Fakebook book) {
        try {
            tryToRegister(in, book);
        } catch (UnknownUserKindException e1) {
            System.out.println(e1.getMessage());
        } catch (UserAlreadyExistsException e2) {
            System.out.println(e2.getMessage());
        } catch (RepeatedTopicException e3) {
            System.out.println(e3.getMessage());
        }
    }

    /**
     * Tries to register a new user on the program
     *
     * @param in   - scanner
     * @param book - top class
     * @throws UnknownUserKindException - thrown if the user kind read in the
     * scanner is not valid
     * @throws UserAlreadyExistsException - thrown if a user already exists in the
     * program
     * @throws RepeatedTopicException - thrown if a topic was already inserted
     * in the list of interests
     */
    private static final void tryToRegister(Scanner in, Fakebook book)
            throws UnknownUserKindException, UserAlreadyExistsException, RepeatedTopicException {
        String kind = in.next();
        in.skip(SPACE);
        String id = in.nextLine();

        if (book.userIsFanatic(kind)) {
            int numTopics = in.nextInt();
            List<Topic> topicList = new ArrayList<Topic>(numTopics);
            int i = DEFAULT_VALUE;
            while (i < numTopics) {
                String passion = in.next();
                String hashtag = in.next();
                Topic newTopic = new TopicClass(passion, hashtag);
                if (topicList.isEmpty()) {
                    topicList.add(newTopic);
                } else{
                    for (int j = DEFAULT_VALUE; j < topicList.size(); j++) {
                        if (!topicList.get(j).getHashtag().equals(hashtag)) {
                            continue;
                        } else {
                            in.nextLine();
                            throw new RepeatedTopicException();
                        }
                    }
                    topicList.add(newTopic);
                }
                i++;
            }
            in.nextLine();
            book.addFanaticUser(id, topicList);
        } else {
            book.addUser(id, kind);
        }
        System.out.println(id + REGISTERED);
    }

    /* Listing users */

    /**
     * Lists all the users in the system
     *
     * @param book - top class
     */
    private static final void listUsers(Fakebook book) {
        try {
            tryToListUsers(book);
        } catch (NoUsersException e1) {
            System.out.println(e1.getMessage());
        }
    }

    /**
     * Tries to list all the users in the system
     *
     * @param book - top class
     * @throws NoUsersException thrown if there are yet no users in the system
     */
    private static final void tryToListUsers(Fakebook book) throws NoUsersException {
        Iterator<User> it = book.usersIterator();
        while (it.hasNext()) {
            User user = it.next();
            System.out.println(user.getId() + SPACE + OPEN_PARENTHESES + user.getKind() +
                    CLOSE_PARENTHESES + SPACE + user.getNumFriends() + SPACE
                    + user.getNumPosts() + SPACE + user.getNumComments());
        }
    }

    /* Add friendship */

    /**
     * Adds a friendship to the program
     *
     * @param in   - scanner
     * @param book - top class
     */
    private static void addFriend(Scanner in, Fakebook book) {
        try {
            tryToAddFiend(in, book);
        } catch (UserDoesntExistException e1) {
            System.out.println(e1.getMessage());
        } catch (SameUserException e2) {
            System.out.println(e2.getMessage());
        } catch (UserAlreadyHasFriendException e3) {
            System.out.println(e3.getMessage());
        }
    }

    /**
     * Tries to add a friendship to the program
     * @param in  - scanner
     * @param book - top class
     * @throws UserDoesntExistException - thrown when a certain user id does not exist
     * @throws SameUserException - thrown when there is an attempt to
     *  establish a friendship with a user with himself/ herself
     * @throws UserAlreadyHasFriendException - thrown when a friendship between the
     *  two provided users already exists
     */
    private static void tryToAddFiend(Scanner in, Fakebook book)
            throws UserDoesntExistException, SameUserException, UserAlreadyHasFriendException {
        in.skip(SPACE);
        String id1 = in.nextLine();
        String id2 = in.nextLine();

        book.addFriend(id1, id2);
        System.out.println(id1 + IS_FRIEND + id2 + FULL_STOP);
    }

    /* Listing the friends of a user */

    /**
     * Lists the friends of a certain user
     *
     * @param in   - scanner
     * @param book - top class
     */
    private static void listFriends(Scanner in, Fakebook book) {
        try {
            tryToListFriends(in, book);
        } catch (UserDoesntExistException e1) {
            System.out.println(e1.getMessage());
        } catch (NoContactsException e2) {
            System.out.println(e2.getMessage());
        }
    }

    /**
     * Tries to list the friends of a certain user
     *
     * @param in   - scanner
     * @param book - top class
     * @throws UserDoesntExistException - thrown when a certain user id does not exist
     * @throws NoContactsException - thrown when the user has not yet made any friends
     */
    private static void tryToListFriends(Scanner in, Fakebook book)
            throws UserDoesntExistException, NoContactsException {
        in.skip(SPACE);
        String id = in.nextLine();
        Iterator<User> it = book.friendsIterator(id);
        while (it.hasNext()) {
            User user = it.next();
            if (!it.hasNext()) {
                System.out.println(user.getId() + FULL_STOP);
            } else {
                System.out.print(user.getId() + COMA + SPACE);
            }
        }
    }

    /* Writing a message (posting a message) */

    /**
     * Adds a new post on the system
     *
     * @param in - scanner
     * @param book - top class
     */
    public static final void post(Scanner in, Fakebook book) {
        try {
            tryToPost(in, book);
        } catch (UserDoesntExistException e1) {
            System.out.println(e1.getMessage());
        } catch (InvalidHashtagListException e2) {
            System.out.println(e2.getMessage());
        } catch (InadequateStanceException e3) {
            System.out.println(e3.getMessage());
        }
    }

    /**
     * Tries to add a new post on the system
     *
     * @param in - scanner
     * @param book - top class
     * @throws UserDoesntExistException - thrown when a certain user id does not exist
     * @throws InvalidHashtagListException - thrown if the number of hashtags is less
     * 	than zero or if there are repeated hashtags
     * @throws InadequateStanceException - thrown if there is an inconsistency between
     * the user's stance and that of the post
     */
    public static final void tryToPost(Scanner in, Fakebook book)
            throws UserDoesntExistException, InvalidHashtagListException, InadequateStanceException {
        in.skip(SPACE);
        String id = in.nextLine();
        int numHashtags = in.nextInt();
        int i = DEFAULT_VALUE;
        boolean canThrowException = false;
        List<String> temporaryTopicsList = new ArrayList<String>();
        while(i < numHashtags){
            String hashtag = in.next();
            if (temporaryTopicsList.contains(hashtag)) {
                canThrowException = true;
            } else {
                temporaryTopicsList.add(hashtag);
            }
            i++;
        }
        if (numHashtags != temporaryTopicsList.size()) {
            canThrowException = true;
        }
        in.nextLine();
        String truthfulness = in.next();
        in.skip(SPACE);
        String messageContent = in.nextLine();
        if(canThrowException){
            throw new InvalidHashtagListException();
        } else {
            book.post(id, numHashtags, temporaryTopicsList, truthfulness, messageContent);
            System.out.println(id + " sent a " + truthfulness + " post to " + book.getUser(id).getNumFriends()
                    + " friends. Post id = " + book.getUser(id).getNumPosts() + FULL_STOP);
        }
    }


    /* Listing messages a user's messages */

    /**
     * Lists the posts of a certain user
     *
     * @param in - scanner
     * @param book - top class
     */
    private static final void listPosts(Scanner in, Fakebook book) {
        try {
            tryToListPosts(in, book);
        } catch (UserDoesntExistException e1) {
            System.out.println(e1.getMessage());
        } catch (NoPostsException e2) {
            System.out.println(e2.getMessage());
        }
    }

    /**
     * Tries to list the posts of a certain user
     *
     * @param in - scanner
     * @param book - top class
     * @throws UserDoesntExistException - thrown when a certain user id does not exist
     * @throws NoPostsException - thrown when a user has not yet made any posts
     */
    private static final void tryToListPosts(Scanner in, Fakebook book)
            throws UserDoesntExistException, NoPostsException {
        in.skip(SPACE);
        String id = in.nextLine();
        if(!book.userExists(id)){
            throw new UserDoesntExistException(id);
        } else if(book.noPosts(id)) {
            throw new NoPostsException(id);
        } else {
            System.out.println(id + POSTS);
            Iterator<Message> postsIt = book.getUser(id).iteratePosts();
            while (postsIt.hasNext()) {
                Message message = postsIt.next();
                System.out.println(message.getPostId() + FULL_STOP + SPACE + OPEN_PARENTHESES
                        + message.getTruthfulness() + CLOSE_PARENTHESES + SPACE + message.getPostContent() +
                        SPACE + OPEN_PARENTHESES + message.getNumComments() +
                        SPACE + "comments" + CLOSE_PARENTHESES);
            }
        }
    }

    /* Writing a comment on a message */

    /**
     * Adds a comment to the system
     *
     * @param in - scanner
     * @param book - top class
     */
    private static final void comment(Scanner in, Fakebook book) {
        try {
            tryToComment(in, book);
        } catch (UserDoesntExistException e1) {
            System.out.println(e1.getMessage());
        } catch (UserHasNoPostException e2) {
            System.out.println(e2.getMessage());
        } catch (UserHasNoAccessToPostException e3) {
            System.out.println(e3.getMessage());
        } catch (CannotCommentException e4) {
            System.out.println(e4.getMessage());
        } catch (InvalidCommentStanceException e5) {
            System.out.println(e5.getMessage());
        }
    }

    /**
     * Tries to add a comment on the system
     *
     * @param in - scanner
     * @param book - top class
     * @throws UserHasNoAccessToPostException - thrown when a user has no access to a post, since it was not
     * 	friends with the author of the post when the post was published
     * @throws CannotCommentException - thrown when a user is not allowed to comment on a post
     * @throws UserDoesntExistException - thrown when a certain user id does not exist
     * @throws UserHasNoPostException - thrown when a user does not have a given post
     * @throws InvalidCommentStanceException - thrown when the comment stance is invalid for
     * the user id and the post in question
     */
    private static final void tryToComment(Scanner in, Fakebook book) throws UserHasNoAccessToPostException,
            CannotCommentException, UserDoesntExistException, UserHasNoPostException, InvalidCommentStanceException {
        in.skip(SPACE);
        String commentedUserId = in.nextLine();
        String authorId = in.nextLine();
        int postId = in.nextInt();
        String stance = in.next();
        in.skip(SPACE);
        String comment = in.nextLine();

        book.comment(commentedUserId, authorId, postId, stance, comment);
        System.out.println(ADDED_COMMENT);

    }

    /* Reading a message (post) */

    /**
     * Prints detailed information on a post
     *
     * @param in - scanner
     * @param book - top class
     */
    private static final void readPost(Scanner in, Fakebook book) {
        try {
            tryToReadPost(in, book);
        } catch (UserDoesntExistException e1) {
            System.out.println(e1.getMessage());
        } catch (UserHasNoPostException e2) {
            System.out.println(e2.getMessage());
        } catch (NoCommentsException e3) {
            System.out.println(e3.getMessage());
        }
    }

    /**
     * Tries to print detailed information on a post
     *
     * @param in - scanner
     * @param book - top class
     * @throws UserDoesntExistException - thrown when a certain user id does not exist
     * @throws UserHasNoPostException - thrown when a user does not have a given post
     * @throws NoCommentsException - thrown when a post doesnt have any comments
     */
    private static final void tryToReadPost(Scanner in, Fakebook book)
            throws UserDoesntExistException, UserHasNoPostException, NoCommentsException {
        in.skip(SPACE);
        String authorId = in.nextLine();

        int postId = in.nextInt();
        in.nextLine();

        if(!book.userExists(authorId)){
            throw new UserDoesntExistException(authorId);
        } else if(book.noPosts(authorId)){
            throw new UserHasNoPostException(authorId, postId);
        } else {
            Message post = book.getPost(authorId, postId);

            System.out.println( OPEN_PARENTHESES+ authorId + SPACE + post.getTruthfulness() +
                    CLOSE_PARENTHESES + SPACE + post.getPostContent());

            if(post.noComments()){
                throw new NoCommentsException();
            } else {
                Iterator<Comment> it = book.commentsIterator(post);
                while(it.hasNext()) {
                    Comment comment = it.next();
                    System.out.println(OPEN_PARENTHESES + comment.getCommenter() +
                            SPACE + comment.getStance() +
                            CLOSE_PARENTHESES + SPACE  + comment.getContent());
                }
            }
        }
    }


    /* Listing all comments a user wrote*/


    /**
     * Shows all the comments by a user on a particular topic
     *
     * @param in - scanner
     * @param book - top class
     */
    private static final void commentsByUser(Scanner in, Fakebook book) {
        try {
            tryToCommentsByUser(in, book);
        } catch (UserDoesntExistException e1) {
            System.out.println(e1.getMessage());
        } catch (NoCommentsByUserException e2) {
            System.out.println(e2.getMessage());
        }

    }

    /**
     * Tries to show all the comments by a user on a particular topic
     *
     * @param in - scanner
     * @param book - top class
     * @throws UserDoesntExistException - thrown when a certain user id does not exist
     * @throws NoCommentsByUserException - thrown when a user didnt make any comments
     */
    private static final void tryToCommentsByUser(Scanner in, Fakebook book)
            throws UserDoesntExistException, NoCommentsByUserException {
        in.skip(SPACE);
        String id = in.nextLine();
        String topicId = in.nextLine();

        Iterator<Comment> it = book.commentsMadeByUserIterator(id, topicId);
        while (it.hasNext()) {
            Comment comment = it.next();
            System.out.println(OPEN_PARENTHESES + comment.getMessage().getAuthorOfMessage() + SPACE +
                    comment.getMessage().getTruthfulness() + SPACE + comment.getMessage().getPostId()
                    + SPACE + comment.getStance() + CLOSE_PARENTHESES + SPACE + comment.getContent());
        }

    }

    /* Listing users fanatic about a certain topic */

    /**
     * Shows a list of fanatic users on a given topic
     *
     * @param in - scanner
     * @param book - top class
     */
    private static final void showTopicFanatics(Scanner in, Fakebook book){
        try{
            tryToShowTopicFanatics(in, book);
        } catch(UnknownTopicException e1){
            System.out.println(e1.getMessage());
        }
    }

    /**
     * Tries to show a list of fanatic users on a given topic
     * @param in - scanner
     * @param book - top class
     * @throws UnknownTopicException - thrown when no fanatic users are fanatic about such topic
     */
    private static final void tryToShowTopicFanatics(Scanner in, Fakebook book) throws UnknownTopicException {
        in.skip(SPACE);
        String hashtag = in.nextLine();

        Iterator<FanaticUser> it = book.fanaticsWithThisTopicIterator(hashtag);
        while (it.hasNext()) {
            FanaticUser fanatic = it.next();
            if (!it.hasNext()) {
                System.out.println(fanatic.getId() + FULL_STOP);
            } else {
                System.out.print(fanatic.getId() + COMA + SPACE);
            }
        }
    }

    /* Listing posts about a certain topic */

    /**
     * Shows a list of posts on a given topic
     *
     * @param in - scanner
     * @param book - top class
     */
    private static final void topicPosts(Scanner in, Fakebook book){
        try{
            tryToTopicPosts(in, book);
        }catch(InvalidNumberOfPostsException e1){
            System.out.println(e1.getMessage());
        } catch(UnknownTopicInPostsException e2){
            System.out.println(e2.getMessage());
        }
    }
    /**
     *  Tries to show a list of posts on a given topic
     *
     * @param in - scanner
     * @param book - top class
     * @throws InvalidNumberOfPostsException - thrown when the maximum number of posts to be listed is invalid (smaller then 1)
     * @throws UnknownTopicInPostsException - thrown when there are no posts about a topic
     */
    private static final void tryToTopicPosts(Scanner in, Fakebook book)
            throws InvalidNumberOfPostsException, UnknownTopicInPostsException{
        in.skip(SPACE);
        String hashtag = in.next();
        int maxNumb = in.nextInt();
        in.nextLine();

        if(maxNumb < MINIMAL_NUMBER_FOR_LISTING){
            throw new InvalidNumberOfPostsException();
        } else {
            int i = DEFAULT_VALUE;
            Iterator<Message> it = book.topicPosts(hashtag);

            while(it.hasNext() && i<maxNumb){
                Message msg = it.next();
                System.out.println(msg.getAuthorOfMessage() + SPACE + msg.getPostId() + SPACE
                        + msg.getNumComments() + TWO_DOTS + SPACE + msg.getPostContent());
                i++;
            }
        }
    }


    /* Presenting the most commented post*/

    /**
     * Shows the most commented post
     *
     * @param book - top class
     */
    private static final void popularPost(Fakebook book) {
        try {
            tryToShowPopularPost(book);
        } catch (NoPostsOrNoCommentsException e1) {
            System.out.println(e1.getMessage());
        }
    }

    /**
     * Tries to show the most commented post
     *
     * @param book - top class
     * @throws NoPostsOrNoCommentsException - thrown when fakebook has no posts or comments yet
     */
    private static final void tryToShowPopularPost(Fakebook book) throws NoPostsOrNoCommentsException{
        Message msg = book.popularPost();
        System.out.println(msg.getAuthorOfMessage() + SPACE +
                msg.getPostId() + SPACE + msg.getNumComments() + TWO_DOTS + SPACE + msg.getPostContent());
    }


    /* Presenting the user who has written more posts */

    /**
     * Shows the user with the higher number of posts
     *
     * @param book - top class
     */
    private static final void topPoster(Fakebook book) {
        try {
            tryToShowTopPoster(book);
        } catch (NoPostsOnFakebookException e1) {
            System.out.println(e1.getMessage());
        }
    }

    /**
     * Tries to show the user with the higher number of posts
     *
     * @param book - top class
     * @throws NoPostsOnFakebookException - thrown when fakebook has no posts
     */
    private static void tryToShowTopPoster(Fakebook book) throws NoPostsOnFakebookException {
        User user = book.topPoster();
        System.out.println(user.getId() + SPACE + user.getNumPosts() +
                SPACE + user.getNumComments() + FULL_STOP);
    }


    /* Presenting the user with higher percentage of commented posts */

    /**
     * Shows the id of the user with the higher percentage of commented posts
     *
     * @param book - top class
     */
    private static void responsive(Fakebook book){
        try{
            tryToGetMostResponsiveUser(book);
        } catch(NoPostsDueToSocialDistancingException e1){
            System.out.println(e1.getMessage());
        }
    }

    /**
     * Tries to show the id of the user with the higher percentage of commented posts
     *
     * @param book - top class
     * @throws NoPostsDueToSocialDistancingException - thrown when there are no posts on fakebook
     */
    private static void tryToGetMostResponsiveUser(Fakebook book) throws NoPostsDueToSocialDistancingException{
        User user = book.getMostResponsiveUser();
        System.out.println(user.getId() + SPACE + user.getNumComments() +
                SPACE + user.getNumPostsUserHasAccessTo() + FULL_STOP);
    }


    /* Presenting the user who has lied the most*/

    /**
     * Shows the id of the top liar
     *
     * @param book - top class
     */
    private static void shameless(Fakebook book){
        try{
            tryToFindShameless(book);
        } catch(NoLiesYetException e1){
            System.out.println(e1.getMessage());
        }
    }

    /**
     * Tries to show the id of the top liar
     *
     * @param book - top class
     * @throws NoLiesYetException - thrown if no lies have yet been told
     */
    private static void tryToFindShameless(Fakebook book) throws NoLiesYetException {
        User liar = book.getShamelessUser();
        System.out.println(liar.getId() + SPACE + liar.getNumLies() + FULL_STOP);
    }

    /*
     * Exits the system
     */
    private static void exit() {
        System.out.println(GOODBYE);
    }

}
