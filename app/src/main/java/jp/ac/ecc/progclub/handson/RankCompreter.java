package jp.ac.ecc.progclub.handson;

import java.util.Comparator;

public class RankCompreter implements Comparator<User> {
    @Override
    public int compare(User user, User t1) {
        return user.tappoint < t1.tappoint ? -1 : 1;
    }
}
