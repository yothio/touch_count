package jp.ac.ecc.progclub.handson;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import jp.ac.ecc.progclub.handson.Ranking.DataStore;
import jp.ac.ecc.progclub.handson.Ranking.User;

import static jp.ac.ecc.progclub.handson.countdown.CountDownActivity.COUNT_KEY;

public class ResultActivity extends BaseActivity {

    static final String save_resultName = "result";                   // 保存オブジェクト名
    static final String save_resultKey_name = "resultName";          // 入力した名前の保存キー名
    static final String save_resultKey_clickNum = "resultClickNum"; // 　クリック数の保存キー名

    TextView nameText;
    TextView countText;
    Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        nameText = findViewById(R.id.NameEditText);    // 名前入力用テキストボックス
        countText = findViewById(R.id.NumTextView);    // カウント回数を表示したラベル
        nextBtn = findViewById(R.id.DecButton);        // [決定]ボタン

        // 前画面でクリックした数を取得
        final int count = getIntent().getIntExtra(COUNT_KEY,0);

        // カウントテキストにセット
        countText.setText(String.valueOf(count));

        // データ保存してランキング画面へ
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameText.getText().toString();   // 名前
              
                User user = new User(name, count);
                DataStore.getInstance().addUser(user);

                // ランキング画面に移動
                Intent intent = new Intent(getApplicationContext(), RankingActivity.class);
                startActivity(intent);

                // 戻ってくると処理が重複するので、アクティビティを終了させておく
                // ただし、ランキング画面に移動→戻る でカウント画面から動けなくなるので対処が必要
                // 暫定的な処理なので、必要なくなったら削除しても可
                finish();
            }
        });

        // ここコピペ
        // 入力されたらボタンを有効化する
        nameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                //未入力はダメ。
                if (TextUtils.isEmpty(s.toString())) {
                    return;
                }
                // ボタンを有効化
                nextBtn.setEnabled(true);
            }
        });
    }
}
