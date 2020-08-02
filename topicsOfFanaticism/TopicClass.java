package topicsOfFanaticism;

/**
 * This class represents a topic of fanaticism
 * @author Clara Sousa - 58403 / Bruna Arroja - 56751
 *
 */

public class TopicClass implements Topic{

    /* Global Variables */
    private String hashtag, passion;

    /* Constructor */
    public TopicClass(String passion, String hashtag){
        this.passion = passion;
        this.hashtag = hashtag;
    }

    /* Getters */

    @Override
    public String getPassion() {
        return passion;
    }

    @Override
    public String getHashtag() {
        return hashtag;
    }

}
