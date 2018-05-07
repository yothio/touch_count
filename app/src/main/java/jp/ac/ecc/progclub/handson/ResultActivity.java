package jp.ac.ecc.progclub.handson;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        final TextView nameTV = findViewById(R.id.NameEditText);   // クリックした回数
        final TextView countTV = findViewById(R.id.NumTextView);  // カウント回数を表示したラベル
        final Button button = findViewById(R.id.DecButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickNum = Integer.parseInt(countTV.getText().toString());  // クリック数
                String name = nameTV.getText().toString();   // 名前

                SharedPreferences sharedPreferences =
                        getSharedPreferences("result", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("resultName", name);
                editor.putInt("resultClickNum", clickNum);
                editor.apply();
            }
        });
    }
}
