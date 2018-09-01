// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.accessibility.CaptioningManager;

abstract class ClosedCaptionWidget extends ViewGroup
    implements SubtitleTrack.RenderingWidget
{
    static interface ClosedCaptionLayout
    {

        public abstract void setCaptionStyle(android.view.accessibility.CaptioningManager.CaptionStyle captionstyle);

        public abstract void setFontScale(float f);
    }


    static android.view.accessibility.CaptioningManager.CaptionStyle _2D_get0()
    {
        return DEFAULT_CAPTION_STYLE;
    }

    public ClosedCaptionWidget(Context context)
    {
        this(context, null);
    }

    public ClosedCaptionWidget(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public ClosedCaptionWidget(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public ClosedCaptionWidget(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mCaptioningListener = new android.view.accessibility.CaptioningManager.CaptioningChangeListener() {

            public void onFontScaleChanged(float f)
            {
                mClosedCaptionLayout.setFontScale(f);
            }

            public void onUserStyleChanged(android.view.accessibility.CaptioningManager.CaptionStyle captionstyle)
            {
                mCaptionStyle = ClosedCaptionWidget._2D_get0().applyStyle(captionstyle);
                mClosedCaptionLayout.setCaptionStyle(mCaptionStyle);
            }

            final ClosedCaptionWidget this$0;

            
            {
                this$0 = ClosedCaptionWidget.this;
                super();
            }
        }
;
        setLayerType(1, null);
        mManager = (CaptioningManager)context.getSystemService("captioning");
        mCaptionStyle = DEFAULT_CAPTION_STYLE.applyStyle(mManager.getUserStyle());
        mClosedCaptionLayout = createCaptionLayout(context);
        mClosedCaptionLayout.setCaptionStyle(mCaptionStyle);
        mClosedCaptionLayout.setFontScale(mManager.getFontScale());
        addView((ViewGroup)mClosedCaptionLayout, -1, -1);
        requestLayout();
    }

    private void manageChangeListener()
    {
        boolean flag;
        if(isAttachedToWindow() && getVisibility() == 0)
            flag = true;
        else
            flag = false;
        if(mHasChangeListener != flag)
        {
            mHasChangeListener = flag;
            if(flag)
                mManager.addCaptioningChangeListener(mCaptioningListener);
            else
                mManager.removeCaptioningChangeListener(mCaptioningListener);
        }
    }

    public abstract ClosedCaptionLayout createCaptionLayout(Context context);

    public void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        manageChangeListener();
    }

    public void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        manageChangeListener();
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        ((ViewGroup)mClosedCaptionLayout).layout(i, j, k, l);
    }

    protected void onMeasure(int i, int j)
    {
        super.onMeasure(i, j);
        ((ViewGroup)mClosedCaptionLayout).measure(i, j);
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
        manageChangeListener();
    }

    private static final android.view.accessibility.CaptioningManager.CaptionStyle DEFAULT_CAPTION_STYLE;
    protected android.view.accessibility.CaptioningManager.CaptionStyle mCaptionStyle;
    private final android.view.accessibility.CaptioningManager.CaptioningChangeListener mCaptioningListener;
    protected ClosedCaptionLayout mClosedCaptionLayout;
    private boolean mHasChangeListener;
    protected SubtitleTrack.RenderingWidget.OnChangedListener mListener;
    private final CaptioningManager mManager;

    static 
    {
        DEFAULT_CAPTION_STYLE = android.view.accessibility.CaptioningManager.CaptionStyle.DEFAULT;
    }
}
