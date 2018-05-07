package jp.ac.ecc.progclub.handson;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by yocchi on 2018/05/07.
 */

public class CountDownProgressBar extends AsyncTask<Integer, Integer, Integer> {

    ProgressBar progressBar;
    TextView countText;
    Callback callback;
    private final long duration;

    public interface Callback {
        void callback();
    }

    private CountDownProgressBar(ProgressBar progressBar, TextView countText, Callback callback, long duration) {
        super();
        this.progressBar = progressBar;
        this.countText = countText;
        this.callback = callback;
        this.duration = duration;
    }

    @Override
    protected Integer doInBackground(Integer... params) {
        Log.d("ProgressAsyncTask", "doInBackGround");
        for (int count = 0; count <= duration; count++) {
            try {
                Thread.sleep(1000);
                publishProgress(count);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }


    //前準備
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d("ProgressAsyncTask", "onPreExe");
        final Interpolator interpolator = progressBar.getInterpolator();
        countText.setText("3");
        progressBar.setInterpolator(interpolator);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setEnabled(true);

    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        Log.d("ProgressAsyncTask", "onPostExe");
        progressBar.setInterpolator(new LinearInterpolator());
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setProgress(0);
        progressBar.setEnabled(false);

        if(callback != null)
            callback.callback();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.d("ProgressAsyncTask", "update now...");
        progressBar.setProgress(values[0]);
        countText.setText(String.valueOf(2 - values[0]));
    }

    public static class Builder{
        private ProgressBar progressBar;
        private TextView countText;
        private Callback callback;
        private long duration;

        public Builder(ProgressBar progressBar, TextView countText) {
            super();
            this.progressBar = progressBar;
            this.countText = countText;
        }

        public Builder afterProgressDone(Callback callback1, long duration){
            callback = callback1;
            this.duration = duration;

            return this;
        }

        public Builder after(@NonNull Callback callback1, final long delay){
            if (callback == null)
                throw new IllegalArgumentException("afterProgressDone must be called first!");

            final Callback previousCallback = callback;
            callback = new Callback() {
                @Override
                public void callback() {
                    previousCallback.callback();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                        }
                    }, delay);
                }
            };
            return this;
        }

        public void build(){
            new CountDownProgressBar(progressBar, countText, callback, duration).execute();
        }
    }
}