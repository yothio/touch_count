package jp.ac.ecc.progclub.handson;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import jp.ac.ecc.progclub.handson.Ranking.RankCompreter;
import jp.ac.ecc.progclub.handson.Ranking.User;

public class RankingActivity extends AppCompatActivity {

    ListView rankingList;
    Button rankButton;
    ImageView rankImage;

    SharedPreferences sp;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        rankButton = (Button) findViewById(R.id.rankbutton);
        rankingList = (ListView) findViewById(R.id.ranklist);
        rankImage = (ImageView) findViewById(R.id.rankimage);


        ArrayList al = getRanking();//ランキング取得

        setRankinglist(al, rankingList);//ランキング表示

        //ホームへ
        rankButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    //リストにランキング表示
    private void setRankinglist(ArrayList list, ListView listView) {

        ListAdapter arrayAdapter = new jp.ac.ecc.progclub.handson.Ranking.ListAdapter(this, list);
        listView.setAdapter(arrayAdapter);
    }

    //ソートされたランキング取得
    private ArrayList getRanking() {

        ArrayList<User> result = new ArrayList<User>();
        sp = getSharedPreferences("result", MODE_PRIVATE);
        Map<String, ?> rankmap = sp.getAll();
        User user;


        for (Map.Entry<String, ?> entry : rankmap.entrySet()) {

            String key = entry.getKey();
            Object obj = entry.getValue();

            int point = Integer.parseInt(obj.toString());
            user = new User(key, point);

            result.add(user);

        }
//ランキングソート
        Collections.sort(result, new RankCompreter());


        return result;
    }


}
