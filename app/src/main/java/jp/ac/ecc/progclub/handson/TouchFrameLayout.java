package jp.ac.ecc.progclub.handson;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by yocchi on 2018/05/14.
 */

public class TouchFrameLayout extends FrameLayout {
    public TouchFrameLayout(@NonNull Context context) {
        super(context);
    }

    public TouchFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TouchFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    // frameLayoutにタッチイベントを付ける際に警告を消すために下記メソッドをオーバーライド
    @Override
    public boolean performClick() {
        super.performClick();
        return true;
    }
}
