package users;

import messages.Message;
import topicsOfFanaticism.Topic;

import java.util.List;

public interface FanaticUser {

    /* Getters */

    /**
     * Returns the id of the user
     * @return id
     */
    public String getId();

    /* Checking */

    /**
     * Checks whether there is an inconsistency between the truthfulness of a message the user is
     * to post and how the user feels about the topics in the post
     * @param messageStance - truthfulness of the message in the post (honest or fake)
     * @param hashtagList   - list of topics about which the user wishes to post
     * @return true if the user is trying to post an honest message about topic he/she hates OR
     * a fake message about topics he/she loves, false if otherwise
     */
    public boolean inconsistentStances(String messageStance, List<String> hashtagList);


    /**
     * Checks whether a fanatic user can comment on a post taking into consideration
     * the stance of the comment (positive or negative), as well as the priority the
     * user has regarding the topics in the comment
     * @param post - the post where we wish to check if the user can comment on
     * @param commentStance - whether a comment is positive or negative
     * @return true if the user can comment on the post
     */
    public boolean checkFanaticUserCanComment(Message post, String commentStance);

    /**
     * Checks whether the fanatic user is fanatic about a certain topic
     * @param hashtag - name of the topic
     * @return true if the user is fanatic about this topic, false if otherwise
     */
    public boolean userIsFanaticAbout(String hashtag);


}
