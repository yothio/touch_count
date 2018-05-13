package jp.ac.ecc.progclub.handson;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import jp.ac.ecc.progclub.handson.countdown.CountDownTimer;
import jp.ac.ecc.progclub.handson.countdown.TimerHandler;

public class CountDownActivity extends BaseActivity {

    private int count = 0;

    private ProgressBar progressBar;
    private TextView textView;
    private TextView tapCountText;
    private TextView timerCountText;
    private TouchFrameLayout frameLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);

        progressBar = findViewById(R.id.progress_bar);
        textView = findViewById(R.id.progress_text);
        tapCountText = findViewById(R.id.tap_count_textview);
        timerCountText = findViewById(R.id.timer_textview);
        frameLayout = findViewById(R.id.tap_area_frame);
        frameLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                count++;
                tapCountText.setText(String.valueOf(count));
                tapCountText.setTextSize(count);
                return false;
            }
        });

        // 制限時間・タップ数を表示・タッチ領域の非表示
        frameLayout.setVisibility(View.INVISIBLE);
        tapCountText.setVisibility(View.INVISIBLE);
        timerCountText.setVisibility(View.INVISIBLE);

        // ゲーム開始のカウントダウンアニメーションのviewを渡す
        CountDownProgressBar.Builder builder = new CountDownProgressBar.Builder(progressBar,textView);


        builder.afterProgressDone(new CountDownProgressBar.Callback() {
            @Override
            public void callback() {
                // カウントダウン後の処理
            }
        }, 2).after(new CountDownProgressBar.Callback() {
            @Override
            public void callback() {
                // 上のコールバックの後に行う処理

                // プログレスバーとそのタイマーを非表示
                progressBar.setVisibility(View.INVISIBLE);
                textView.setVisibility(View.INVISIBLE);

                // 制限時間・タップ数を表示・タッチ領域の表示
                tapCountText.setVisibility(View.VISIBLE);
                timerCountText.setVisibility(View.VISIBLE);
                frameLayout.setVisibility(View.VISIBLE);

                // 制限時間用のタイマーの設定
                TimerHandler handler = new TimerHandler(timerCountText);
                CountDownTimer countDownTimer = new CountDownTimer(handler, 10000L, 16L, new CountDownTimer.Callback() {
                    @Override
                    public void callback() {
                        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                        startActivity(intent);
                    }
                });
                countDownTimer.start();

            }
        },1000).build();
    }
}
