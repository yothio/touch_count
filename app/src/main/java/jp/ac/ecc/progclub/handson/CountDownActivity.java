package jp.ac.ecc.progclub.handson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class CountDownActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);

        ProgressBar progressBar = findViewById(R.id.progress_bar);
        TextView textView = findViewById(R.id.progress_text);

        CountDownProgressBar.Builder builder = new CountDownProgressBar.Builder(progressBar,textView);

        builder.afterProgressDone(new CountDownProgressBar.Callback() {
            @Override
            public void callback() {

            }
        }, 3000).after(new CountDownProgressBar.Callback() {
            @Override
            public void callback() {
            }
        },3000).build();
    }
}
