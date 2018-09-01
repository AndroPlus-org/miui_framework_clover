// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.method;

import android.text.Spannable;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.TextView;

public interface MovementMethod
{

    public abstract boolean canSelectArbitrarily();

    public abstract void initialize(TextView textview, Spannable spannable);

    public abstract boolean onGenericMotionEvent(TextView textview, Spannable spannable, MotionEvent motionevent);

    public abstract boolean onKeyDown(TextView textview, Spannable spannable, int i, KeyEvent keyevent);

    public abstract boolean onKeyOther(TextView textview, Spannable spannable, KeyEvent keyevent);

    public abstract boolean onKeyUp(TextView textview, Spannable spannable, int i, KeyEvent keyevent);

    public abstract void onTakeFocus(TextView textview, Spannable spannable, int i);

    public abstract boolean onTouchEvent(TextView textview, Spannable spannable, MotionEvent motionevent);

    public abstract boolean onTrackballEvent(TextView textview, Spannable spannable, MotionEvent motionevent);
}
