// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.content.Context;
import android.util.AttributeSet;
import android.view.accessibility.CaptioningManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Vector;

// Referenced classes of package android.media:
//            TtmlCue

class TtmlRenderingWidget extends LinearLayout
    implements SubtitleTrack.RenderingWidget
{

    public TtmlRenderingWidget(Context context)
    {
        this(context, null);
    }

    public TtmlRenderingWidget(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public TtmlRenderingWidget(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public TtmlRenderingWidget(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        setLayerType(1, null);
        attributeset = (CaptioningManager)context.getSystemService("captioning");
        mTextView = new TextView(context);
        mTextView.setTextColor(attributeset.getUserStyle().foregroundColor);
        addView(mTextView, -1, -1);
        mTextView.setGravity(81);
    }

    public void onAttachedToWindow()
    {
        super.onAttachedToWindow();
    }

    public void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
    }

    public void setActiveCues(Vector vector)
    {
        int i = vector.size();
        String s = "";
        for(int j = 0; j < i; j++)
        {
            TtmlCue ttmlcue = (TtmlCue)vector.get(j);
            s = (new StringBuilder()).append(s).append(ttmlcue.mText).append("\n").toString();
        }

        mTextView.setText(s);
        if(mListener != null)
            mListener.onChanged(this);
    }

    public void setOnChangedListener(SubtitleTrack.RenderingWidget.OnChangedListener onchangedlistener)
    {
        mListener = onchangedlistener;
    }

    public void setSize(int i, int j)
    {
        measure(android.view.View.MeasureSpec.makeMeasureSpec(i, 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(j, 0x40000000));
        layout(0, 0, i, j);
    }

    public void setVisible(boolean flag)
    {
        if(flag)
            setVisibility(0);
        else
            setVisibility(8);
    }

    private SubtitleTrack.RenderingWidget.OnChangedListener mListener;
    private final TextView mTextView;
}
