// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityNodeInfo;

// Referenced classes of package android.widget:
//            AbsSeekBar

public class SeekBar extends AbsSeekBar
{
    public static interface OnSeekBarChangeListener
    {

        public abstract void onProgressChanged(SeekBar seekbar, int i, boolean flag);

        public abstract void onStartTrackingTouch(SeekBar seekbar);

        public abstract void onStopTrackingTouch(SeekBar seekbar);
    }


    public SeekBar(Context context)
    {
        this(context, null);
    }

    public SeekBar(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x101007b);
    }

    public SeekBar(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public SeekBar(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/SeekBar.getName();
    }

    public void onInitializeAccessibilityNodeInfoInternal(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        super.onInitializeAccessibilityNodeInfoInternal(accessibilitynodeinfo);
        if(canUserSetProgress())
            accessibilitynodeinfo.addAction(android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS);
    }

    void onProgressRefresh(float f, boolean flag, int i)
    {
        super.onProgressRefresh(f, flag, i);
        if(mOnSeekBarChangeListener != null)
            mOnSeekBarChangeListener.onProgressChanged(this, i, flag);
    }

    void onStartTrackingTouch()
    {
        super.onStartTrackingTouch();
        if(mOnSeekBarChangeListener != null)
            mOnSeekBarChangeListener.onStartTrackingTouch(this);
    }

    void onStopTrackingTouch()
    {
        super.onStopTrackingTouch();
        if(mOnSeekBarChangeListener != null)
            mOnSeekBarChangeListener.onStopTrackingTouch(this);
    }

    public void setOnSeekBarChangeListener(OnSeekBarChangeListener onseekbarchangelistener)
    {
        mOnSeekBarChangeListener = onseekbarchangelistener;
    }

    private OnSeekBarChangeListener mOnSeekBarChangeListener;
}
