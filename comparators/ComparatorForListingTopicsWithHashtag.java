package comparators;
import java.util.Comparator;
import messages.*;

/**
 * This comparator compares users in 3 stages:
 * higher number of comments made,
 * alphabetic order of the author of the post
 * lower message id
 *
 * @author Clara Sousa - 58403 / Bruna Arroja - 56751
 *
 */

public class ComparatorForListingTopicsWithHashtag implements Comparator<Message>{

    /**
     * Compares two messages according to the number of comments they have
     * and in case they have the same number of comments, the method
     * to compare them according to their author is called upon
     * @param o1 - message to be compared with the second message
     * @param o2 - message to be compared with the second message
     * @return 1 if the first message fulfills, -1 if it is the second one who does and
     * 0 in case of a tie
     */
    @Override
    public int compare(Message o1, Message o2) {

        if (o1.getNumComments() < o2.getNumComments()) {
            return 1;
        } else if (o1.getNumComments() > o2.getNumComments()) {
            return -1;
        } else {
            return compareByAlphabeticOrderOfAuthor(o1, o2);
        }
    }

    /**
     * Compares two messages according to the alphabetic order of their author;
     * in case of the authors being exactly the same, a method to compare
     * @param o1 - message to be compared with the second message
     * @param o2 - message to be compared with the first message
     * @return 1 if the first author is alphabetically greater, -1 if otherwise,
     * 0 in case of a tie
     */
    public int compareByAlphabeticOrderOfAuthor(Message o1, Message o2){
        String user1 = o1.getAuthorOfMessage();
        String user2 = o2.getAuthorOfMessage();
        if (user1.compareTo(user2) > 0) {
            return 1;
        } else if (user1.compareTo(user2) < 0) {
            return -1;
        } else {
            return comparePostIdOfSameUser(o1, o2);
        }
    }

    /**
     * Compares two messages according to their id
     * @param o1 - message to be compared with the second message
     * @param o2 - message to be compared with the first message
     * @return 1 if the id of the second message is bigger than that of the
     * first message, -1 if it is the other one, or 0 in case they are the same
     */
    public int comparePostIdOfSameUser(Message o1, Message o2){
        if (o1.getPostId() < o2.getPostId()) {
            return 1;
        } else if (o1.getPostId() > o2.getPostId()) {
            return -1;
        } else {
            return 0;
        }
    }

}
