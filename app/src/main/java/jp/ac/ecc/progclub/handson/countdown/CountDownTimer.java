package jp.ac.ecc.progclub.handson.countdown;

import android.app.Notification;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class CountDownTimer extends Thread {
    long millis = 0L;   //カウントダウンする時間ms
    long handlerInterval = 0L;  //カウントダウン間隔ms
    Handler handler;    //紐付けされた送信するハンドラ
    private Callback callback;

    /**
     * カウントダウンタイマーをインスタンス化します
     * @param millis    カウントする時間（ミリ秒）
     * @param handlerInterval   カウントダウン間隔（ミリ秒）
     * @param callback  タイマー終了後に行うcallback
     */
    public CountDownTimer(Handler handler, long millis, long handlerInterval, Callback callback) {
        super();
        this.callback = callback;
        this.handler = handler;
        setTimer(millis, handlerInterval);
    }


    /**
     * タイマーをセットします。
     * @param millis    カウントする時間（ミリ秒）
     * @param handlerInterval   カウントダウン間隔（ミリ秒）
     */
    public void setTimer(long millis, long handlerInterval){
        this.millis = millis;
        this.handlerInterval = handlerInterval;
    }

    /**
     * 極上のカウントダウンを行うスレッド
     */
    @Override
    public void run() {
        if(millis <= 0) return; //計測時間がおかしかったら、最速終了。そうだね、スレッド作成の時間的コストのほうが大きいね。

        //至上の精度でカウント回すマン
        //高速軽量設計にしておいた。
        long remain;                                                //残り時間
        long finishTime = System.nanoTime() + millis * 1000 * 1000; //終了予定時刻をナノ秒で（•̀.̫•́✧ｷﾗﾝ
        Message msg;                                                //メッセージオブジェクト
        Bundle data;                                                //データを受け渡しするオブジェクト

        //ハンドラの更新間隔が0だとか異常値な時は、その心意気を買って1msに自動設定してやる
        if(handlerInterval <= 0) handlerInterval = 1;

        remain = finishTime - System.nanoTime();                    //do-whileでも最初の初期化はしちゃうのよ

        //残り時間が0になるまで続ける
        do{
            //最初のスタートな時刻も律儀に表示
            data = new Bundle();
            data.putLong("Remain", remain);
            msg = Message.obtain();
            msg.setData(data);
            handler.sendMessage(msg);

            try {
                //ハンドラの更新間隔よりも残り時間が短くなったら、ナノ秒計測モード発動。なんて効率的。
                //ただしsleep関数は999999nsまでしか対応しないので、残り時間がそれ以上あったら、従来の低精度モードになる。
                if(handlerInterval < remain / 1000 / 1000 || remain > 999999)
                    sleep(handlerInterval);
                else
                    sleep(0, (int)remain);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            remain = finishTime - System.nanoTime();
        }while (remain > 0);

        //最後の更新情報を送信
        data = new Bundle();
        data.putLong("Remain", remain);
        msg = Message.obtain();
        msg.setData(data);
        handler.sendMessage(msg);

        callback.callback();
    }

    // Timer更新情報送信後用のCallback
    public interface Callback{
        void callback();
    }
}
