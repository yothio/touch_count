package jp.ac.ecc.progclub.handson.countdown;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * イベントハンドラ。残り時間を表示するだけ
 */
public class TimerHandler extends Handler {
    //単位定義。
    //高速軽量処理のために数字は配列の要素番号としても使ってるヨ
    public static final int TIME_NS = 0;    //ナノ秒
    public static final int TIME_US = 1;    //マイクロ秒
    public static final int TIME_MS = 2;    //ミリ秒
    public static final int TIME_SEC = 3;   //秒

    private TextView showTextView;  //残り時間を表示するテキストビュー
    private ProgressBar progressBar;//プログレスバー
    private long max = 0;           //今回表示した時間の中で最大値

    /**
     * 関連付けられたスレッドからメッセージを受け取ったら動くメソッド
     * @param msg
     */
    @Override
    public void handleMessage(Message msg) {
        long remain;
        remain = msg.getData().getLong("Remain");

        //どうしても非同期だから誤差でちゃうのはハードウェアの仕様。
        //誤差を人為的に隠蔽することを強いる一行は人類の英知の敗北
        //負数は強制的に0になる
        remain = (remain < 0) ? 0 : remain;

        //ナノ秒単位での合計時間を保持する変数
        long remainNs = remain;

        //最大値が更新されれば最大値
        //プログレスバー用
        max = (remain > max) ? remain : max;

        //秒ミリ秒形式に変換
        int[] time = new int[4];
        time[TIME_NS] = (int)(remain % 1000);
        remain /= 1000;
        time[TIME_US] = (int)(remain % 1000);
        remain /= 1000;
        time[TIME_MS] = (int)(remain % 1000);
        remain /= 1000;
        time[TIME_SEC] = (int)remain;

        //フルスペックで表示するぞおらー（必要に応じてここを書き換えるのです↓）
        if (showTextView != null) {
            showTextView.setText(String.format("%d.%03d", time[TIME_SEC], time[TIME_MS]));
        }

        if (progressBar != null) {
            progressBar.setProgress((int)(remainNs / (double) max * 10000));
        }
    }

    /**
     * ビューを初期化する
     * @param views
     */
    private void init(View[] views){
        for (int i = 0; i < views.length; i++) {
            if(views[i] instanceof TextView)
                showTextView = (TextView) views[i];

            if(views[i] instanceof ProgressBar) {
                progressBar = (ProgressBar) views[i];
                progressBar.setMax(10000);
            }
        }
    }

    //---- 以下、コンストラクタの群れ ----
    public TimerHandler(Callback callback, View[] views) {
        super(callback);
        init(views);
    }

    public TimerHandler(Looper looper, View[] views) {
        super(looper);
        init(views);
    }

    public TimerHandler(Looper looper, Callback callback, View[] views) {
        super(looper, callback);
        init(views);
    }

    public TimerHandler(View[] views) {
        super();
        init(views);
    }
    //---- 群れここまで ----
}
