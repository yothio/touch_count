
/*
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.a2150254.countdowntimer.countdown.CountDownTimer;
import com.example.a2150254.countdowntimer.countdown.TimerHandler;

public class MainActivity extends AppCompatActivity {

    TimerHandler handler;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        //とりま10秒を0.016秒ずつ減らす
        //
        handler = new TimerHandler(textView);
        CountDownTimer countDownTimer = new CountDownTimer(handler ,10000L,16L);
        countDownTimer.start();
    }
}
*/