package usersEnum;

/**
 * This class represents an enumerator for the users, according to their kind
 * @author Clara Sousa - 58403 / Bruna Arroja - 56751
 *
 */

public enum UsersKind {

    /* Constants */
    SELF_CENTERED("selfcentered"),
    NAIVE("naive"),
    LIAR("liar"),
    FANATIC("fanatic");

    /* Global Variables*/

    private final String kindOfUser;

    /* Constructor */

    private UsersKind(String kind) {
        this.kindOfUser = kind;
    }

    /**
     * Returns the kind of user (self-centered, naive, liar or fanatic)
     * @return representation - representation of the security level
     */
    public String getKindOfUser() {
        return kindOfUser;
    }

}
