// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.accessibility.CaptioningManager;
import android.widget.LinearLayout;
import com.android.internal.widget.SubtitleView;
import java.util.ArrayList;
import java.util.Vector;

// Referenced classes of package android.media:
//            TextTrackCue, TextTrackRegion, TextTrackCueSpan

class WebVttRenderingWidget extends ViewGroup
    implements SubtitleTrack.RenderingWidget
{
    private static class CueLayout extends LinearLayout
    {

        static int _2D_get0(CueLayout cuelayout)
        {
            return cuelayout.mOrder;
        }

        public TextTrackCue getCue()
        {
            return mCue;
        }

        public boolean isActive()
        {
            return mActive;
        }

        public void measureForParent(int i, int j)
        {
            TextTrackCue texttrackcue;
            int k;
            texttrackcue = mCue;
            k = android.view.View.MeasureSpec.getSize(i);
            j = android.view.View.MeasureSpec.getSize(j);
            WebVttRenderingWidget._2D_wrap0(getLayoutDirection(), texttrackcue.mAlignment);
            JVM INSTR tableswitch 200 204: default 60
        //                       200 111
        //                       201 60
        //                       202 60
        //                       203 92
        //                       204 103;
               goto _L1 _L2 _L1 _L1 _L3 _L4
_L1:
            i = 0;
_L6:
            measure(android.view.View.MeasureSpec.makeMeasureSpec((Math.min(texttrackcue.mSize, i) * k) / 100, 0x80000000), android.view.View.MeasureSpec.makeMeasureSpec(j, 0x80000000));
            return;
_L3:
            i = 100 - texttrackcue.mTextPosition;
            continue; /* Loop/switch isn't completed */
_L4:
            i = texttrackcue.mTextPosition;
            continue; /* Loop/switch isn't completed */
_L2:
            if(texttrackcue.mTextPosition <= 50)
                i = texttrackcue.mTextPosition * 2;
            else
                i = (100 - texttrackcue.mTextPosition) * 2;
            if(true) goto _L6; else goto _L5
_L5:
        }

        protected void onMeasure(int i, int j)
        {
            super.onMeasure(i, j);
        }

        public void prepForPrune()
        {
            mActive = false;
        }

        public void setCaptionStyle(android.view.accessibility.CaptioningManager.CaptionStyle captionstyle, float f)
        {
            mCaptionStyle = captionstyle;
            mFontSize = f;
            int i = getChildCount();
            for(int j = 0; j < i; j++)
            {
                android.view.View view = getChildAt(j);
                if(view instanceof SpanLayout)
                    ((SpanLayout)view).setCaptionStyle(captionstyle, f);
            }

        }

        public void setOrder(int i)
        {
            mOrder = i;
        }

        public void update()
        {
            mActive = true;
            removeAllViews();
            WebVttRenderingWidget._2D_wrap0(getLayoutDirection(), mCue.mAlignment);
            JVM INSTR tableswitch 203 204: default 44
        //                       203 129
        //                       204 136;
               goto _L1 _L2 _L3
_L1:
            android.text.Layout.Alignment alignment = android.text.Layout.Alignment.ALIGN_CENTER;
_L5:
            android.view.accessibility.CaptioningManager.CaptionStyle captionstyle = mCaptionStyle;
            float f = mFontSize;
            TextTrackCueSpan atexttrackcuespan[][] = mCue.mLines;
            int i = atexttrackcuespan.length;
            for(int j = 0; j < i; j++)
            {
                SpanLayout spanlayout = new SpanLayout(getContext(), atexttrackcuespan[j]);
                spanlayout.setAlignment(alignment);
                spanlayout.setCaptionStyle(captionstyle, f);
                addView(spanlayout, -2, -2);
            }

            break; /* Loop/switch isn't completed */
_L2:
            alignment = android.text.Layout.Alignment.ALIGN_LEFT;
            continue; /* Loop/switch isn't completed */
_L3:
            alignment = android.text.Layout.Alignment.ALIGN_RIGHT;
            if(true) goto _L5; else goto _L4
_L4:
        }

        private boolean mActive;
        private android.view.accessibility.CaptioningManager.CaptionStyle mCaptionStyle;
        public final TextTrackCue mCue;
        private float mFontSize;
        private int mOrder;

        public CueLayout(Context context, TextTrackCue texttrackcue, android.view.accessibility.CaptioningManager.CaptionStyle captionstyle, float f)
        {
            boolean flag;
            boolean flag1;
            flag = true;
            super(context);
            mCue = texttrackcue;
            mCaptionStyle = captionstyle;
            mFontSize = f;
            int j;
            if(texttrackcue.mWritingDirection == 100)
                flag1 = true;
            else
                flag1 = false;
            if(flag1)
                j = 1;
            else
                j = 0;
            setOrientation(j);
            texttrackcue.mAlignment;
            JVM INSTR tableswitch 200 204: default 88
        //                       200 122
        //                       201 155
        //                       202 105
        //                       203 114
        //                       204 147;
               goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
            update();
            return;
_L4:
            setGravity(0x800005);
            continue; /* Loop/switch isn't completed */
_L5:
            setGravity(3);
            continue; /* Loop/switch isn't completed */
_L2:
            int i;
            if(flag1)
                i = ((flag) ? 1 : 0);
            else
                i = 16;
            setGravity(i);
            continue; /* Loop/switch isn't completed */
_L6:
            setGravity(5);
            continue; /* Loop/switch isn't completed */
_L3:
            setGravity(0x800003);
            if(true) goto _L1; else goto _L7
_L7:
        }
    }

    private static class RegionLayout extends LinearLayout
    {

        public TextTrackRegion getRegion()
        {
            return mRegion;
        }

        public void measureForParent(int i, int j)
        {
            TextTrackRegion texttrackregion = mRegion;
            i = android.view.View.MeasureSpec.getSize(i);
            j = android.view.View.MeasureSpec.getSize(j);
            measure(android.view.View.MeasureSpec.makeMeasureSpec(((int)texttrackregion.mWidth * i) / 100, 0x80000000), android.view.View.MeasureSpec.makeMeasureSpec(j, 0x80000000));
        }

        public void prepForPrune()
        {
            int i = mRegionCueBoxes.size();
            for(int j = 0; j < i; j++)
                ((CueLayout)mRegionCueBoxes.get(j)).prepForPrune();

        }

        public boolean prune()
        {
            int i = mRegionCueBoxes.size();
            int k;
            for(int j = 0; j < i; i = k)
            {
                CueLayout cuelayout = (CueLayout)mRegionCueBoxes.get(j);
                k = i;
                int l = j;
                if(!cuelayout.isActive())
                {
                    mRegionCueBoxes.remove(j);
                    removeView(cuelayout);
                    k = i - 1;
                    l = j - 1;
                }
                j = l + 1;
            }

            return mRegionCueBoxes.isEmpty();
        }

        public void put(TextTrackCue texttrackcue)
        {
            int i = mRegionCueBoxes.size();
            for(int j = 0; j < i; j++)
            {
                CueLayout cuelayout = (CueLayout)mRegionCueBoxes.get(j);
                if(cuelayout.getCue() == texttrackcue)
                {
                    cuelayout.update();
                    return;
                }
            }

            texttrackcue = new CueLayout(getContext(), texttrackcue, mCaptionStyle, mFontSize);
            mRegionCueBoxes.add(texttrackcue);
            addView(texttrackcue, -2, -2);
            if(getChildCount() > mRegion.mLines)
                removeViewAt(0);
        }

        public void setCaptionStyle(android.view.accessibility.CaptioningManager.CaptionStyle captionstyle, float f)
        {
            mCaptionStyle = captionstyle;
            mFontSize = f;
            int i = mRegionCueBoxes.size();
            for(int j = 0; j < i; j++)
                ((CueLayout)mRegionCueBoxes.get(j)).setCaptionStyle(captionstyle, f);

            setBackgroundColor(captionstyle.windowColor);
        }

        private android.view.accessibility.CaptioningManager.CaptionStyle mCaptionStyle;
        private float mFontSize;
        private final TextTrackRegion mRegion;
        private final ArrayList mRegionCueBoxes = new ArrayList();

        public RegionLayout(Context context, TextTrackRegion texttrackregion, android.view.accessibility.CaptioningManager.CaptionStyle captionstyle, float f)
        {
            super(context);
            mRegion = texttrackregion;
            mCaptionStyle = captionstyle;
            mFontSize = f;
            setOrientation(1);
            setBackgroundColor(captionstyle.windowColor);
        }
    }

    private static class SpanLayout extends SubtitleView
    {

        public void setCaptionStyle(android.view.accessibility.CaptioningManager.CaptionStyle captionstyle, float f)
        {
            setBackgroundColor(captionstyle.backgroundColor);
            setForegroundColor(captionstyle.foregroundColor);
            setEdgeColor(captionstyle.edgeColor);
            setEdgeType(captionstyle.edgeType);
            setTypeface(captionstyle.getTypeface());
            setTextSize(f);
        }

        public void update()
        {
            SpannableStringBuilder spannablestringbuilder = mBuilder;
            TextTrackCueSpan atexttrackcuespan[] = mSpans;
            spannablestringbuilder.clear();
            spannablestringbuilder.clearSpans();
            int i = atexttrackcuespan.length;
            for(int j = 0; j < i; j++)
                if(atexttrackcuespan[j].mEnabled)
                    spannablestringbuilder.append(atexttrackcuespan[j].mText);

            setText(spannablestringbuilder);
        }

        private final SpannableStringBuilder mBuilder = new SpannableStringBuilder();
        private final TextTrackCueSpan mSpans[];

        public SpanLayout(Context context, TextTrackCueSpan atexttrackcuespan[])
        {
            super(context);
            mSpans = atexttrackcuespan;
            update();
        }
    }


    static android.view.accessibility.CaptioningManager.CaptionStyle _2D_get0(WebVttRenderingWidget webvttrenderingwidget)
    {
        return webvttrenderingwidget.mCaptionStyle;
    }

    static float _2D_get1(WebVttRenderingWidget webvttrenderingwidget)
    {
        return webvttrenderingwidget.mFontSize;
    }

    static int _2D_wrap0(int i, int j)
    {
        return resolveCueAlignment(i, j);
    }

    static void _2D_wrap1(WebVttRenderingWidget webvttrenderingwidget, android.view.accessibility.CaptioningManager.CaptionStyle captionstyle, float f)
    {
        webvttrenderingwidget.setCaptionStyle(captionstyle, f);
    }

    public WebVttRenderingWidget(Context context)
    {
        this(context, null);
    }

    public WebVttRenderingWidget(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public WebVttRenderingWidget(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public WebVttRenderingWidget(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mRegionBoxes = new ArrayMap();
        mCueBoxes = new ArrayMap();
        mCaptioningListener = new android.view.accessibility.CaptioningManager.CaptioningChangeListener() {

            public void onFontScaleChanged(float f)
            {
                float f1 = getHeight();
                WebVttRenderingWidget._2D_wrap1(WebVttRenderingWidget.this, WebVttRenderingWidget._2D_get0(WebVttRenderingWidget.this), f1 * f * 0.0533F);
            }

            public void onUserStyleChanged(android.view.accessibility.CaptioningManager.CaptionStyle captionstyle)
            {
                WebVttRenderingWidget._2D_wrap1(WebVttRenderingWidget.this, captionstyle, WebVttRenderingWidget._2D_get1(WebVttRenderingWidget.this));
            }

            final WebVttRenderingWidget this$0;

            
            {
                this$0 = WebVttRenderingWidget.this;
                super();
            }
        }
;
        setLayerType(1, null);
        mManager = (CaptioningManager)context.getSystemService("captioning");
        mCaptionStyle = mManager.getUserStyle();
        mFontSize = mManager.getFontScale() * (float)getHeight() * 0.0533F;
    }

    private int calculateLinePosition(CueLayout cuelayout)
    {
        TextTrackCue texttrackcue = cuelayout.getCue();
        Integer integer = texttrackcue.mLinePosition;
        boolean flag = texttrackcue.mSnapToLines;
        boolean flag1;
        if(integer == null)
            flag1 = true;
        else
            flag1 = false;
        if(!flag && flag1 ^ true && (integer.intValue() < 0 || integer.intValue() > 100))
            return 100;
        if(!flag1)
            return integer.intValue();
        if(!flag)
            return 100;
        else
            return -(CueLayout._2D_get0(cuelayout) + 1);
    }

    private void layoutCue(int i, int j, CueLayout cuelayout)
    {
        TextTrackCue texttrackcue;
        int k;
        int l;
        boolean flag;
        int j1;
        texttrackcue = cuelayout.getCue();
        k = getLayoutDirection();
        l = resolveCueAlignment(k, texttrackcue.mAlignment);
        flag = texttrackcue.mSnapToLines;
        j1 = (cuelayout.getMeasuredWidth() * 100) / i;
        l;
        JVM INSTR tableswitch 203 204: default 68
    //                   203 293
    //                   204 303;
           goto _L1 _L2 _L3
_L3:
        break MISSING_BLOCK_LABEL_303;
_L1:
        int k1 = texttrackcue.mTextPosition - j1 / 2;
_L4:
        int i1 = k1;
        if(k == 1)
            i1 = 100 - k1;
        k = j1;
        int l1 = i1;
        if(flag)
        {
            k = (getPaddingLeft() * 100) / i;
            int i2 = (getPaddingRight() * 100) / i;
            int j2 = j1;
            k1 = i1;
            if(i1 < k)
            {
                j2 = j1;
                k1 = i1;
                if(i1 + j1 > k)
                {
                    k1 = i1 + k;
                    j2 = j1 - k;
                }
            }
            float f = 100 - i2;
            k = j2;
            l1 = k1;
            if((float)k1 < f)
            {
                k = j2;
                l1 = k1;
                if((float)(k1 + j2) > f)
                {
                    k = j2 - i2;
                    l1 = k1;
                }
            }
        }
        i1 = (l1 * i) / 100;
        k = (k * i) / 100;
        i = calculateLinePosition(cuelayout);
        k1 = cuelayout.getMeasuredHeight();
        if(i < 0)
            i = j + i * k1;
        else
            i = ((j - k1) * i) / 100;
        cuelayout.layout(i1, i, i1 + k, i + k1);
        return;
_L2:
        k1 = texttrackcue.mTextPosition;
          goto _L4
        k1 = texttrackcue.mTextPosition - j1;
          goto _L4
    }

    private void layoutRegion(int i, int j, RegionLayout regionlayout)
    {
        TextTrackRegion texttrackregion = regionlayout.getRegion();
        int k = regionlayout.getMeasuredHeight();
        int l = regionlayout.getMeasuredWidth();
        float f = texttrackregion.mViewportAnchorPointX;
        float f1 = texttrackregion.mViewportAnchorPointY;
        i = (int)(((float)(i - l) * f) / 100F);
        j = (int)(((float)(j - k) * f1) / 100F);
        regionlayout.layout(i, j, i + l, j + k);
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
            {
                mManager.addCaptioningChangeListener(mCaptioningListener);
                setCaptionStyle(mManager.getUserStyle(), mManager.getFontScale() * (float)getHeight() * 0.0533F);
            } else
            {
                mManager.removeCaptioningChangeListener(mCaptioningListener);
            }
        }
    }

    private void prepForPrune()
    {
        int i = mRegionBoxes.size();
        for(int j = 0; j < i; j++)
            ((RegionLayout)mRegionBoxes.valueAt(j)).prepForPrune();

        i = mCueBoxes.size();
        for(int k = 0; k < i; k++)
            ((CueLayout)mCueBoxes.valueAt(k)).prepForPrune();

    }

    private void prune()
    {
        int i = mRegionBoxes.size();
        int k1;
        for(int k = 0; k < i; i = k1)
        {
            RegionLayout regionlayout = (RegionLayout)mRegionBoxes.valueAt(k);
            int i1 = k;
            k1 = i;
            if(regionlayout.prune())
            {
                removeView(regionlayout);
                mRegionBoxes.removeAt(k);
                k1 = i - 1;
                i1 = k - 1;
            }
            k = i1 + 1;
        }

        int l1 = mCueBoxes.size();
        int j;
        for(int l = 0; l < l1; l1 = j)
        {
            CueLayout cuelayout = (CueLayout)mCueBoxes.valueAt(l);
            j = l1;
            int j1 = l;
            if(!cuelayout.isActive())
            {
                removeView(cuelayout);
                mCueBoxes.removeAt(l);
                j = l1 - 1;
                j1 = l - 1;
            }
            l = j1 + 1;
        }

    }

    private static int resolveCueAlignment(int i, int j)
    {
        char c = '\314';
        char c1 = '\313';
        switch(j)
        {
        default:
            return j;

        case 201: 
            if(i == 0)
                i = c1;
            else
                i = 204;
            return i;

        case 202: 
            break;
        }
        if(i == 0)
            i = c;
        else
            i = 203;
        return i;
    }

    private void setCaptionStyle(android.view.accessibility.CaptioningManager.CaptionStyle captionstyle, float f)
    {
        captionstyle = DEFAULT_CAPTION_STYLE.applyStyle(captionstyle);
        mCaptionStyle = captionstyle;
        mFontSize = f;
        int i = mCueBoxes.size();
        for(int j = 0; j < i; j++)
            ((CueLayout)mCueBoxes.valueAt(j)).setCaptionStyle(captionstyle, f);

        i = mRegionBoxes.size();
        for(int k = 0; k < i; k++)
            ((RegionLayout)mRegionBoxes.valueAt(k)).setCaptionStyle(captionstyle, f);

    }

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
        k -= i;
        j = l - j;
        setCaptionStyle(mCaptionStyle, mManager.getFontScale() * 0.0533F * (float)j);
        l = mRegionBoxes.size();
        for(i = 0; i < l; i++)
            layoutRegion(k, j, (RegionLayout)mRegionBoxes.valueAt(i));

        l = mCueBoxes.size();
        for(i = 0; i < l; i++)
            layoutCue(k, j, (CueLayout)mCueBoxes.valueAt(i));

    }

    protected void onMeasure(int i, int j)
    {
        super.onMeasure(i, j);
        int k = mRegionBoxes.size();
        for(int l = 0; l < k; l++)
            ((RegionLayout)mRegionBoxes.valueAt(l)).measureForParent(i, j);

        k = mCueBoxes.size();
        for(int i1 = 0; i1 < k; i1++)
            ((CueLayout)mCueBoxes.valueAt(i1)).measureForParent(i, j);

    }

    public void setActiveCues(Vector vector)
    {
        Context context = getContext();
        android.view.accessibility.CaptioningManager.CaptionStyle captionstyle = mCaptionStyle;
        float f = mFontSize;
        prepForPrune();
        int i = vector.size();
        int j = 0;
        while(j < i) 
        {
            TextTrackCue texttrackcue = (TextTrackCue)vector.get(j);
            TextTrackRegion texttrackregion = texttrackcue.mRegion;
            if(texttrackregion != null)
            {
                RegionLayout regionlayout = (RegionLayout)mRegionBoxes.get(texttrackregion);
                RegionLayout regionlayout1 = regionlayout;
                if(regionlayout == null)
                {
                    regionlayout1 = new RegionLayout(context, texttrackregion, captionstyle, f);
                    mRegionBoxes.put(texttrackregion, regionlayout1);
                    addView(regionlayout1, -2, -2);
                }
                regionlayout1.put(texttrackcue);
            } else
            {
                CueLayout cuelayout = (CueLayout)mCueBoxes.get(texttrackcue);
                CueLayout cuelayout1 = cuelayout;
                if(cuelayout == null)
                {
                    cuelayout1 = new CueLayout(context, texttrackcue, captionstyle, f);
                    mCueBoxes.put(texttrackcue, cuelayout1);
                    addView(cuelayout1, -2, -2);
                }
                cuelayout1.update();
                cuelayout1.setOrder(j);
            }
            j++;
        }
        prune();
        setSize(getWidth(), getHeight());
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
        manageChangeListener();
    }

    private static final boolean DEBUG = false;
    private static final int DEBUG_CUE_BACKGROUND = 0x80ff0000;
    private static final int DEBUG_REGION_BACKGROUND = 0x800000ff;
    private static final android.view.accessibility.CaptioningManager.CaptionStyle DEFAULT_CAPTION_STYLE;
    private static final float LINE_HEIGHT_RATIO = 0.0533F;
    private android.view.accessibility.CaptioningManager.CaptionStyle mCaptionStyle;
    private final android.view.accessibility.CaptioningManager.CaptioningChangeListener mCaptioningListener;
    private final ArrayMap mCueBoxes;
    private float mFontSize;
    private boolean mHasChangeListener;
    private SubtitleTrack.RenderingWidget.OnChangedListener mListener;
    private final CaptioningManager mManager;
    private final ArrayMap mRegionBoxes;

    static 
    {
        DEFAULT_CAPTION_STYLE = android.view.accessibility.CaptioningManager.CaptionStyle.DEFAULT;
    }
}
