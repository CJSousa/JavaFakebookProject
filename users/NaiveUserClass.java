package users;

/**
 * This class represents a naive user
 * @author Clara Sousa - 58403 / Bruna Arroja - 56751
 *
 */

public class NaiveUserClass extends UserClass implements User {

    /* Constants */
    private static final String KIND = "naive";

    /* Constructor */
    public NaiveUserClass(String id) {
        super(id);
        this.kind = NaiveUserClass.KIND;
    }

    /* Getters */

    public String getKind(){ return this.kind;}
}
