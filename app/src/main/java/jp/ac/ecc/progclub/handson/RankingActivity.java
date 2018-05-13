package jp.ac.ecc.progclub.handson;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class RankingActivity extends AppCompatActivity {

    ListView rankinglist;
    Button rankbutton;
    ImageView rankimage;

    SharedPreferences sp;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranking);

        rankbutton = (Button) findViewById(R.id.rankbutton);
        rankinglist = (ListView) findViewById(R.id.ranklist);
        rankimage = (ImageView) findViewById(R.id.rankimage);

    }


    private ArrayList getRanking() {

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
