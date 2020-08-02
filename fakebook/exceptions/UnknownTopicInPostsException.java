package fakebook.exceptions;

/**
 * This exceptions is thrown when there are no posts about a topic
 * @author Clara Sousa - 58403 / Bruna Arroja - 56751
 *
 */

public class UnknownTopicInPostsException extends Exception {

    /* Constants */
    private static final String NO_TOPIC_IN_POSTS_MESSAGE = "Oh please, who would write about ";
    private static final String QUESTION_MARK = "?";

    /* Constructor */
    public UnknownTopicInPostsException(String hashtag){
        super(NO_TOPIC_IN_POSTS_MESSAGE+hashtag+QUESTION_MARK);
    }
}
