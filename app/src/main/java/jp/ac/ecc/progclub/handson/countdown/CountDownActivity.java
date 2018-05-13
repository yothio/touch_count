package jp.ac.ecc.progclub.handson.countdown;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import jp.ac.ecc.progclub.handson.BaseActivity;
import jp.ac.ecc.progclub.handson.R;
import jp.ac.ecc.progclub.handson.ResultActivity;
import jp.ac.ecc.progclub.handson.TouchFrameLayout;

public class CountDownActivity extends BaseActivity {

    // tap回数
    private int count = 0;

    // 開始前のアニメーションタイマ
    private TextView textView;
    // tap回数の表示領域
    private TextView tapCountText;
    private TextView timerCountText;
    // tap領域
    private TouchFrameLayout frameLayout;
    private ProgressBar progressBar;

    public static final String COUNT_KEY = "count_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);

        // レイアウトファイルとの結びつけ
        progressBar = findViewById(R.id.progress_bar);
        textView = findViewById(R.id.progress_text);
        tapCountText = findViewById(R.id.tap_count_textview);
        timerCountText = findViewById(R.id.timer_textview);
        frameLayout = findViewById(R.id.tap_area_frame);

        // tap時の処理を設定
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

                        // 画面遷移処理
                        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                        // タップ回数を渡す
                        intent.putExtra(COUNT_KEY,count);
                        startActivity(intent);
                    }
                });
                countDownTimer.start();

            }
        },1000).build();
    }
}
