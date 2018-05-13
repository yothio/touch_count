package jp.ac.ecc.progclub.handson;

import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

/**
 * Created by yocchi on 2018/05/14.
 */

public abstract class BaseActivity extends AppCompatActivity {
//    今回のアプリは戻るボタンを反応しないように設定
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction()==KeyEvent.ACTION_DOWN) {
            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_BACK:
                    // ダイアログ表示など特定の処理を行いたい場合はここに記述
                    // 親クラスのdispatchKeyEvent()を呼び出さずにtrueを返す
                    return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }
}
