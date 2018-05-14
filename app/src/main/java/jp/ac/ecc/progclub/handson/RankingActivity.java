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


        ArrayList al = getRanking();

        setRankinglist(al, rankingList);

        rankButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setRankinglist(ArrayList list, ListView listView) {

        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.ranklist, list);
        //ArrayAdapter arrayAdapter = new ArrayAdapter<>(this, R.layout.ranklist, new String[]{"name", "point"}, new int[]{R.id.name, R.id.point});
      //  ArrayAdapter<User> arrayAdapter =  new ArrayAdapter<User>(this, R.layout.ranklist, list);
        ListAdapter arrayAdapter = new jp.ac.ecc.progclub.handson.ListAdapter(this,list);
        listView.setAdapter(arrayAdapter);
    }


    private ArrayList getRanking() {

        ArrayList<User> result = new ArrayList<User>();
/*        sp = getSharedPreferences("Ranking", MODE_PRIVATE);
        Map<String, ?> rankmap = sp.getAll();
        User user;


        for (Map.Entry<String, ?> entry : rankmap.entrySet()) {

            String key = entry.getKey();
            Object obj = entry.getValue();

            int point = Integer.parseInt(obj.toString());
            user = new User(key, point);

            result.add(user);

        }*/
        User user = new User("10", 10);
        result.add(user);
        user = new User("11", 999);
        result.add(user);
        result.add(new User("fawfa",0));
        result.add(new User("fawfa",0));
        result.add(new User("fawfa",650));
        result.add(new User("fawfa",0));
        result.add(new User("fawfa",596130));
        result.add(new User("fawfa",0));
        result.add(new User("fawfa",0));
        result.add(new User("fawfa",0));
        result.add(new User("fawfa",0));
        result.add(new User("fawfa",0));
        result.add(new User("fawfa",0));
        result.add(new User("fawfa",0));
        result.add(new User("fawfa",0));

        result.add(new User("fawfa",0));
        result.add(new User("fawfa",0));
        result.add(new User("fawfa",0));
        result.add(new User("fawfa",0));
        result.add(new User("fawfa",0));
        result.add(new User("fawfa",0));



        Collections.sort(result, new RankCompreter());


        return result;
    }


}
