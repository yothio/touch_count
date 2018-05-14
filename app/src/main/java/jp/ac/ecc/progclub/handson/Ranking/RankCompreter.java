package jp.ac.ecc.progclub.handson.Ranking;

import java.util.Comparator;

//ポイントが下か上かをきめる
public class RankCompreter implements Comparator<User> {
    @Override
    public int compare(User user, User t1) {
        return user.getTappoint() > t1.getTappoint() ? -1 : 1;
    }
}
