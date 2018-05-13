package jp.ac.ecc.progclub.handson;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class RankingActivity extends AppCompatActivity {

    ListView rankinglist;
    Button rankButton;
    ImageView rankImage;
    SharedPreferences sp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        rankButton = findViewById(R.id.rank_button);
        rankinglist = findViewById(R.id.rank_listview);
        rankImage = findViewById(R.id.rank_imageview);
        List<User> list = new ArrayList<>();
        list.add(new User("test",111));
        list.add(new User("test1",111));
        list.add(new User("test2",111));


        ArrayAdapter<User> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        rankinglist.setAdapter(adapter);

    }


    private ArrayList<User> getRanking() {

        ArrayList<User> result = new ArrayList<User>();
        sp = getSharedPreferences("Ranking", MODE_PRIVATE);
        Map<String, ?> rankmap = sp.getAll();
        User user;


        for (Map.Entry<String, ?> entry : rankmap.entrySet()) {

            String key = entry.getKey();
            Object obj = entry.getValue();

            int point = Integer.parseInt(obj.toString());
            user = new User(key, point);

            result.add(user);

        }

        Collections.sort(result,new RankComparator());

        return result;
    }


}
