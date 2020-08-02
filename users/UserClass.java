package users;
import users.exceptions.*;
import messages.*;
import comparators.*;
import comments.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.*;

/**
 * This class represents a user
 * @author Clara Sousa - 58403 / Bruna Arroja - 56751
 *
 */

public abstract class UserClass implements User{

    /* Constants */
    private static final int DEFAULT_VALUE = 0;
    private static final String HONEST = "honest";
    private static final String FAKE = "fake";
    private static final String POSITIVE_COMMENT = "positive";
    private static final String NEGATIVE_COMMENT = "negative";

    /* Global Variables */
    private String id;
    protected String kind;
    private List<User> contacts;
    private int postId, numLies;
    private Map<Integer,Message> posts;
    private List<Comment> commentsMade;


    /* Constructor */
    public UserClass(String id)  {
        this.id = id;
        this.postId = DEFAULT_VALUE;
        this.numLies = DEFAULT_VALUE;
        this.contacts = new ArrayList<User>();
        this.posts = new TreeMap<Integer, Message>();
        this.commentsMade = new ArrayList<Comment>();
    }

    /* Getters */

    @Override
    public String getId(){
        return this.id;
    }

    @Override
    public abstract String getKind();

    @Override
    public Message getMessage(int postId) throws UserHasNoPostException{
        if(!posts.containsKey(postId)){
            throw new UserHasNoPostException(id, postId);
        } else {
            return posts.get(postId);
        }
    }

    @Override
    public int getNumFriends() {
        return contacts.size();
    }

    @Override
    public int getNumComments() {
        return commentsMade.size();
    }

    @Override
    public int getNumPosts() { return posts.size(); }

    @Override
    public int getNumPostsUserHasAccessTo() {
        int numPosts = posts.size();

        Iterator<User> friends = contacts.iterator();

        while (friends.hasNext()) {
            User friend = friends.next();
            Iterator<Message> postIt = friend.iteratePosts();
            while(postIt.hasNext()) {
                Message post = postIt.next();
                if (post.postCanBeAccessedBy(this)) {
                    numPosts++;
                }
            }
        }

        return numPosts;
    }

    @Override
    public int getNumLies() {return this.numLies;}

    @Override
    public int getTotalPostsAndComments(){
        int number = DEFAULT_VALUE;
        number += posts.size();
        number += commentsMade.size();

        return number;
    }

    /* Checking */

    @Override
    public boolean hasContact(User user){
        return contacts.contains(user);
    }

    @Override
    public boolean noPosts(){
        return posts.isEmpty();
    }

    public boolean noComments() {
        return commentsMade.isEmpty();
    }

    @Override
    public boolean contactHasAccessToPost(User user, int postId){
        return posts.get(postId).postCanBeAccessedBy(user);
    }


    /* Others */

    @Override
    public void addContact(User user) {
        int i = DEFAULT_VALUE;
        if(contacts.isEmpty()){
            contacts.add(user);
        } else {
            boolean added = false;
            while(i<contacts.size() && !added){
                if(user.getId().compareTo(contacts.get(i).getId())>0){
                    contacts.add(i, user);
                    added = true;
                    break;
                } else{
                    i++;
                }
            }
            if (!added) {
                contacts.add(user);
            }
        }
    }

    @Override
    public void createNewMessage(String truthfulness, List<String> hashtags, String messageContent){
        this.newPostId();
        switch(truthfulness){
            case HONEST:
                posts.put(postId, new HonestMessageClass(this.id, this.postId, hashtags, messageContent));
                posts.get(postId).addUsersWithAccess(this);
                addAccessToContacts(postId);
                break;
            case FAKE:
                posts.put(postId, new FakeMessageClass(this.id, this.postId,
                        hashtags, messageContent));
                posts.get(postId).addUsersWithAccess(this);
                addAccessToContacts(postId);
                addLie();
                break;
        }
    }

    @Override
    public void createCommentInPost(User userWhoComments, int postId, String stance, String content)
            throws UserHasNoPostException, UserHasNoAccessToPostException {

        if (!posts.containsKey(postId)) {
            throw new UserHasNoPostException(userWhoComments.getId(), postId);
        } else if(!contactHasAccessToPost(userWhoComments, postId)){
            throw new UserHasNoAccessToPostException(userWhoComments.getId(), postId, this.id);
        } else {
            Comment comment = posts.get(postId).addComment(userWhoComments.getId(), stance, content);
            userWhoComments.addComment(comment);
            addLieIfNecessary(userWhoComments, postId, stance);
        }
    }

    /* Listing */

    @Override
    public Iterator<User> iterateContacts() throws NoContactsException{
        if(contacts.isEmpty()){
            throw new NoContactsException(id);
        }
        Collections.sort(contacts, new ComparatorByAlphabeticOrder());
        return contacts.iterator();
    }

    @Override
    public Iterator<Message> iteratePosts(){
        return posts.values().iterator();
    }

    @Override
    public Iterator<Comment> userPostsWithHashtag(String hashtag) throws NoCommentsByUserException {
        List<Comment> commentsWithHashtag = new ArrayList<Comment>();

        for(Comment comment : commentsMade){
            if(comment.getMessage().messageHasHashtag(hashtag)){
                commentsWithHashtag.add(comment);
            }
        }

        if(commentsWithHashtag.size() == DEFAULT_VALUE){
            throw new NoCommentsByUserException();
        } else {
            return commentsWithHashtag.iterator();
        }

    }

    /* Auxiliary Methods */

    /**
     * Increments the number of posts
     */
    private void newPostId(){
        postId++;
    }

    /**
     * When a message is created, this method is called so as to place all the current friends
     * of the user (and the user himself/herself) as users who can access the message
     * @param postId - id of the message
     */
    private void addAccessToContacts(int postId){
        for ( User contact : contacts) {
            posts.get(postId).addUsersWithAccess(contact);
        }
    }

    @Override
    public void addComment(Comment comment){
        commentsMade.add(comment);
    }

    @Override
    public void addLie(){ this.numLies++;};

    /**
     * Checks if, in making a comment, the user is telling a lie
     * @param userWhoComments - id of the user who writes the comment
     * @param postId - id of the message where the post is to be written
     * @param stance - whether the comment is negative or positive
     */
    private void addLieIfNecessary(User userWhoComments, int postId, String stance){
        Message msg = posts.get(postId);
        String truthfulness = msg.getTruthfulness();
        switch (truthfulness){
            case HONEST:
                if(stance.equals(NEGATIVE_COMMENT)){
                    userWhoComments.addLie();
                }
            break;
            case FAKE:
                if(stance.equals(POSITIVE_COMMENT)){
                    userWhoComments.addLie();
                }
        }
    }
}
