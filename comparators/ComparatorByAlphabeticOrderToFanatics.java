package comparators;

import users.*;

import java.util.Comparator;

/**
 * This comparator compares fanatic users alphabetically
 *
 * @author Clara Sousa - 58403 / Bruna Arroja - 56751
 *
 */

public class ComparatorByAlphabeticOrderToFanatics implements Comparator<FanaticUser> {

    /**
     * Compares two fanatic users according to the alphabetic order of their id
     * @param o1 - fanatic user to be compared with the second user
     * @param o2 - fanatic user to be compared with the first user
     * @return 1 if the id of the first user is alphabetically greater,
     * -1 if it is the second user who has an id alphabetically greater,
     * 0 if there is a tie
     */
    @Override
    public int compare(FanaticUser o1, FanaticUser o2) {
        if (o1.getId().compareTo(o2.getId()) > 0) {
            return 1;
        } else if (o1.getId().compareTo(o2.getId()) < 0) {
            return -1;
        } else {
            return 0;
        }
    }
}