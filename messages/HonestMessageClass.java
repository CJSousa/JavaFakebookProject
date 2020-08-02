package messages;

import java.util.List;

/**
 * This class represents an honest message to be posted
 * @author Clara Sousa - 58403 / Bruna Arroja - 56751
 *
 */

public class HonestMessageClass extends MessageClass implements Message {

    /* Constructor */
    private static final String HONEST = "honest";

    /* Constructor */
    public HonestMessageClass(String authorOfMessage, int postId,
                              List<String> hashtags, String messageContent) {
        super(authorOfMessage, postId, hashtags, messageContent);
        this.truthfulness = HONEST;
    }

    /* Getters */

    public String getTruthfulness(){ return this.truthfulness;}

}
