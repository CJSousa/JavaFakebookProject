package messages;

import java.util.List;

/**
 * This class represents a fake message to be posted
 * @author Clara Sousa - 58403 / Bruna Arroja - 56751
 *
 */

public class FakeMessageClass extends MessageClass implements Message {

    /* Constants */
    private static final String FAKE = "fake";

    /* Constructor */
    public FakeMessageClass(String authorOfMessage, int postId,
                    List<String>hashtags, String messageContent) {
        super(authorOfMessage, postId, hashtags, messageContent);
        this.truthfulness = FAKE;
    }

    /* Getters */

    public String getTruthfulness(){ return this.truthfulness;}
}
