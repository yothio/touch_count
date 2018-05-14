package jp.ac.ecc.progclub.handson.Ranking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yocchi on 2018/05/14.
 */

public class DataStore {

    private DataStore(){
        list = new ArrayList<>();
    }

    private static DataStore dataStore = new DataStore();
    public static DataStore getInstance() {
        return dataStore;
    }

    private List<User> list;
    public void addUser(User user){
        list.add(user);
    }

    public List<User> getUserList(){
        return list;
    }
}
