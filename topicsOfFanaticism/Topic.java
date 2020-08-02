package topicsOfFanaticism;

/**
 * This class represents a topic of fanaticism
 * @author Clara Sousa - 58403 / Bruna Arroja - 56751
 *
 */

public interface Topic {

    /* Getters */

    /**
     * Returns the passion of a user concerning a certain topic
     * @return "loves" or "hates"
     */
    public String getPassion();

    /**
     * Returns the subject the topic is about
     * @return subject the topic is about
     */
    public String getHashtag();
}
