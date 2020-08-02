package users;

/**
 * This class represents a liar user
 * @author Clara Sousa - 58403 / Bruna Arroja - 56751
 *
 */

public class LiarUserClass extends UserClass implements User{

    /* Constants */
    private static final String KIND = "liar";

    /* Constructor */
    public LiarUserClass(String id) {
        super(id);
        this.kind = LiarUserClass.KIND;
    }

    /* Getters */

    public String getKind(){ return this.kind;}



}
