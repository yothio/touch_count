package jp.ac.ecc.progclub.handson;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import jp.ac.ecc.progclub.handson.countdown.CountDownActivity;

public class MainActivity extends BaseActivity {

    // スタートするボタン
    Button startBtn;
    // ランキングボタン
    Button rankingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CountDownActivity.class);
                startActivity(intent);
                finish();
            }
        });

        rankingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RankingActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
}
