package users;
import messages.*;
import topicsOfFanaticism.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class represents a fanatic user
 *
 * @author Clara Sousa - 58403 / Bruna Arroja - 56751
 *
 */

public class FanaticUserClass extends UserClass implements FanaticUser {

    /* Constants */
    private static final String KIND = "fanatic";

    private static final String HONEST_MESSAGE = "honest";
    private static final String FAKE_MESSAGE = "fake";

    private static final String POSITIVE_STANCE = "positive";
    private static final String NEGATIVE_STANCE = "negative";

    private static final int DEFAULT_VALUE = 0;

    /* Global Variable */
    private List<Topic> topicsUserLoves, topicsUserHates;
    private List<Topic> totalTopics;

    /* Constructor */
    public FanaticUserClass(String id, List<Topic> topicsOfInterest) {
        super(id);
        this.kind = FanaticUserClass.KIND;
        this.topicsUserLoves = lovedTopics(topicsOfInterest);
        this.topicsUserHates = hatedTopics(topicsOfInterest);
        this.totalTopics = topicsOfInterest;

    }

    /* Methods needed for the constructor */

    /**
     *
     * @param topics
     * @return
     */
    private List<Topic> lovedTopics(List<Topic> topics) {
        List<Topic> lovedTopics = new ArrayList<Topic>();
        for (Topic topic : topics) {
            if (topic.getPassion().equalsIgnoreCase("loves")) {
                lovedTopics.add(topic);
            }
        }
        return lovedTopics;
    }

    /**
     *
     * @param topics
     * @return
     */
    private List<Topic> hatedTopics(List<Topic> topics) {
        List<Topic> hatedTopics = new ArrayList<Topic>();
        for (Topic topic : topics) {
            if (topic.getPassion().equalsIgnoreCase("hates")) {
                hatedTopics.add(topic);
            }
        }
        return hatedTopics;
    }

    /* Getters */

    @Override
    public String getKind() {
        return this.kind;
    }

    /* Checking */

    @Override
    public boolean inconsistentStances(String messageStance, List<String> hashtagList) {
        int i = DEFAULT_VALUE;
        int j = DEFAULT_VALUE;
        while (j < totalTopics.size()) {
            while (i < hashtagList.size()) {
                if ((topicsUserHates.get(j).getHashtag().equals(hashtagList.get(i))
                        && messageStance.equals(HONEST_MESSAGE))) {
                    return true;
                } else if (topicsUserLoves.get(j).getHashtag().equals(hashtagList.get(i))
                        && messageStance.equals(FAKE_MESSAGE)) {
                    return true;
                } else {
                    i++;
                }
            }
            j++;
        }
        return false;
    }

    @Override
    public boolean checkFanaticUserCanComment(Message post, String commentStance){
        Topic firstTopic = null;
        String messageStance = post.getTruthfulness();
        int i = 0;
        boolean foundCommonTopic = false;

        while(i<totalTopics.size()){
            Iterator<String> topicsInMessage = post.iterateHashtags();
            while(topicsInMessage.hasNext()) {
                 String hashtag = topicsInMessage.next();
                 if(totalTopics.get(i).getHashtag().equals(hashtag)){
                     firstTopic = totalTopics.get(i);
                     foundCommonTopic = true;
                     break;
                 }
            }
            if(foundCommonTopic){
                break;
            } else {
                i++;
            }
        }

        if(firstTopic == null){
            return false;
        } else {
            return circumstancesAllowComment(firstTopic, messageStance, commentStance);
        }
    }

    @Override
    public boolean userIsFanaticAbout(String hashtag){
        boolean found = false;
        Iterator<Topic> totalTopicsIt = totalTopics.iterator();
        while(totalTopicsIt.hasNext()){
            Topic topic = totalTopicsIt.next();
            if(topic.getHashtag().equals(hashtag)){
                found = true;
            }
        }
        return found;
    }

    /* Auxiliary Methods */

    /**
     * Checks whether the fanatic user can comment on a message or not
     * (for example, a fanatic user is not allowed to make a positive comment on a fake message
     * where there is a topic the user loves)
     * @param firstTopic - topic to check
     * @param messageStance - truthfulness of the message, "honest" or "fake"
     * @param commentStance
     * @return true if the user can comment on a message, false if otherwise
     */
    private boolean circumstancesAllowComment(Topic firstTopic, String messageStance, String commentStance){

        switch(messageStance){
            case HONEST_MESSAGE:
                if( (commentStance.equals(NEGATIVE_STANCE) && firstTopic.getPassion().equals("loves"))
                        || (commentStance.equals(POSITIVE_STANCE) && firstTopic.getPassion().equals("hates"))){
                    return false;
                } ;
                break;
            case FAKE_MESSAGE:
                if( (commentStance.equals(NEGATIVE_STANCE) && firstTopic.getPassion().equals("hates"))
                        || (commentStance.equals(POSITIVE_STANCE) && firstTopic.getPassion().equals("loves")) ){
                    return false;
                };
                break;
        }

        return true;
    }

}
