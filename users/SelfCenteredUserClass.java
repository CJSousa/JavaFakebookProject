package users;

/**
 * This class represents a self-centred user
 * @author Clara Sousa - 58403 / Bruna Arroja - 56751
 *
 */

public class SelfCenteredUserClass extends UserClass implements User{

    /* Constants */
    private static final String KIND = "selfcentered";

    public SelfCenteredUserClass(String id) {
        super(id);
        this.kind = SelfCenteredUserClass.KIND;
    }

    /* Getters */

    public String getKind(){ return this.kind;}
}
