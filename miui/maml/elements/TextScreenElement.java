// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.elements;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.text.*;
import android.util.Log;
import miui.maml.ScreenContext;
import miui.maml.ScreenElementRoot;
import miui.maml.data.Expression;
import miui.maml.data.IndexedVariable;
import miui.maml.util.*;
import miui.util.TypefaceUtils;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.elements:
//            AnimatedScreenElement

public class TextScreenElement extends AnimatedScreenElement
{

    private static int[] _2D_getmiui_2D_maml_2D_elements_2D_ScreenElement$AlignSwitchesValues()
    {
        if(_2D_miui_2D_maml_2D_elements_2D_ScreenElement$AlignSwitchesValues != null)
            return _2D_miui_2D_maml_2D_elements_2D_ScreenElement$AlignSwitchesValues;
        int ai[] = new int[ScreenElement.Align.values().length];
        try
        {
            ai[ScreenElement.Align.CENTER.ordinal()] = 1;
        }
        catch(NoSuchFieldError nosuchfielderror2) { }
        try
        {
            ai[ScreenElement.Align.LEFT.ordinal()] = 2;
        }
        catch(NoSuchFieldError nosuchfielderror1) { }
        try
        {
            ai[ScreenElement.Align.RIGHT.ordinal()] = 3;
        }
        catch(NoSuchFieldError nosuchfielderror) { }
        _2D_miui_2D_maml_2D_elements_2D_ScreenElement$AlignSwitchesValues = ai;
        return ai;
    }

    public TextScreenElement(Element element, ScreenElementRoot screenelementroot)
    {
        super(element, screenelementroot);
        mPaint = new TextPaint();
        mMarqueePos = 3.402823E+038F;
        mTextSize = scale(18D);
        load(element);
    }

    private android.text.Layout.Alignment getAlignment()
    {
        android.text.Layout.Alignment alignment = android.text.Layout.Alignment.ALIGN_NORMAL;
        _2D_getmiui_2D_maml_2D_elements_2D_ScreenElement$AlignSwitchesValues()[mAlign.ordinal()];
        JVM INSTR tableswitch 1 3: default 40
    //                   1 49
    //                   2 42
    //                   3 56;
           goto _L1 _L2 _L3 _L4
_L1:
        return alignment;
_L3:
        alignment = android.text.Layout.Alignment.ALIGN_LEFT;
        continue; /* Loop/switch isn't completed */
_L2:
        alignment = android.text.Layout.Alignment.ALIGN_CENTER;
        continue; /* Loop/switch isn't completed */
_L4:
        alignment = android.text.Layout.Alignment.ALIGN_RIGHT;
        if(true) goto _L1; else goto _L5
_L5:
    }

    private void load(Element element)
    {
        miui.maml.data.Variables variables;
        Object obj;
        Object obj1;
        if(element == null)
            return;
        variables = getVariables();
        if(mHasName)
        {
            mTextWidthVar = new IndexedVariable((new StringBuilder()).append(mName).append(".").append("text_width").toString(), variables, true);
            mTextHeightVar = new IndexedVariable((new StringBuilder()).append(mName).append(".").append("text_height").toString(), variables, true);
        }
        mFormatter = TextFormatter.fromElement(variables, element, mStyle);
        mColorParser = ColorParser.fromElement(variables, element, mStyle);
        mSizeExpression = Expression.build(variables, getAttr(element, "size"));
        mMarqueeSpeed = getAttrAsInt(element, "marqueeSpeed", 0);
        mSpacingMult = getAttrAsFloat(element, "spacingMult", 1.0F);
        mSpacingAdd = getAttrAsFloat(element, "spacingAdd", 0.0F);
        mMarqueeGap = getAttrAsInt(element, "marqueeGap", 2);
        mMultiLine = Boolean.parseBoolean(getAttr(element, "multiLine"));
        mFontScaleEnabled = Boolean.parseBoolean(getAttr(element, "enableFontScale"));
        obj = getAttr(element, "fontFamily");
        obj1 = getAttr(element, "fontPath");
        if(TextUtils.isEmpty(((CharSequence) (obj)))) goto _L2; else goto _L1
_L1:
        int i = parseFontStyle(getAttr(element, "fontStyle"));
        mPaint.setTypeface(Typeface.create(((String) (obj)), i));
_L7:
        mPaint.setColor(getColor());
        mPaint.setTextSize(scale(18D));
        mPaint.setAntiAlias(true);
        mShadowRadius = getAttrAsFloat(element, "shadowRadius", 0.0F);
        mShadowDx = getAttrAsFloat(element, "shadowDx", 0.0F);
        mShadowDy = getAttrAsFloat(element, "shadowDy", 0.0F);
        mShadowColorParser = ColorParser.fromElement(variables, element, "shadowColor", mStyle);
        mPaint.setShadowLayer(mShadowRadius, mShadowDx, mShadowDy, getShadowColor());
        return;
_L2:
        if(TextUtils.isEmpty(((CharSequence) (obj1)))) goto _L4; else goto _L3
_L3:
        obj = null;
        obj1 = Typeface.createFromAsset(getContext().mContext.getAssets(), ((String) (obj1)));
        obj = obj1;
_L5:
        if(obj != null)
            mPaint.setTypeface(((Typeface) (obj)));
        continue; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        Log.e("TextScreenElement", (new StringBuilder()).append("create typeface from asset fail :").append(exception).toString());
        if(true) goto _L5; else goto _L4
_L4:
        boolean flag = Boolean.parseBoolean(getAttr(element, "bold"));
        mPaint.setFakeBoldText(flag);
        mPaint.setTypeface(TypefaceUtils.replaceTypeface(getContext().mContext, mPaint.getTypeface()));
        if(true) goto _L7; else goto _L6
_L6:
    }

    private static int parseFontStyle(String s)
    {
        if(TextUtils.isEmpty(s) || "normal".equals(s))
            return 0;
        if("bold".equals(s))
            return 1;
        if("italic".equals(s))
            return 2;
        return !"bold_italic".equals(s) ? 0 : 3;
    }

    private void updateTextSize()
    {
        if(mSizeExpression != null)
        {
            mTextSize = scale(evaluate(mSizeExpression));
            if(mFontScaleEnabled)
                mTextSize = mTextSize * mRoot.getFontScale();
            mPaint.setTextSize(mTextSize);
        }
    }

    private void updateTextWidth()
    {
        mTextWidth = 0.0F;
        if(!TextUtils.isEmpty(mText))
            if(mMultiLine)
            {
                String as[] = mText.split("\n");
                for(int i = 0; i < as.length; i++)
                {
                    float f = mPaint.measureText(as[i]);
                    if(f > mTextWidth)
                        mTextWidth = f;
                }

            } else
            {
                mTextWidth = mPaint.measureText(mText);
            }
        if(mHasName)
            mTextWidthVar.set(descale(mTextWidth));
    }

    protected void doRender(Canvas canvas)
    {
label0:
        {
            if(TextUtils.isEmpty(mText))
                return;
            mPaint.setColor(getColor());
            mPaint.setAlpha(Utils.mixAlpha(mPaint.getAlpha(), getAlpha()));
            mPaint.setShadowLayer(mShadowRadius, mShadowDx, mShadowDy, getShadowColor());
            float f = getWidth();
            int i;
            float f1;
            float f2;
            float f3;
            float f4;
            float f5;
            float f6;
            float f7;
            float f8;
            int j;
            int k;
            if(f >= 0.0F)
                i = 1;
            else
                i = 0;
            if(f >= 0.0F)
            {
                f1 = f;
                if(f <= mTextWidth)
                    break label0;
            }
            f1 = mTextWidth;
        }
        f = getHeight();
        f2 = mPaint.getTextSize();
        f3 = f;
        if(f < 0.0F)
            f3 = mTextHeight;
        f4 = getLeft(0.0F, f1);
        f5 = getTop(0.0F, f3);
        canvas.save();
        f6 = 0.0F;
        f = 0.0F;
        f7 = 0.0F;
        f8 = 0.0F;
        if(mShadowRadius != 0.0F)
        {
            f6 = Math.min(0.0F, mShadowDx - mShadowRadius);
            f = Math.max(0.0F, mShadowDx + mShadowRadius);
            f7 = Math.min(0.0F, mShadowDy - mShadowRadius);
            f8 = Math.max(0.0F, mShadowDy + mShadowRadius);
        }
        canvas.translate(f4, f5);
        if(i != 0)
            f6 = 0.0F;
        if(i != 0)
            f = 0.0F;
        canvas.clipRect(f6, f7, f1 + f, f3 + f8);
        if(mTextLayout != null)
            if(mTextLayout.getLineCount() == 1 && mShouldMarquee)
            {
                j = mTextLayout.getLineStart(0);
                k = mTextLayout.getLineEnd(0);
                i = mTextLayout.getLineTop(0);
                f6 = mTextLayout.getLineLeft(0);
                canvas.drawText(mText, j, k, f6 + mMarqueePos, f2 + (float)i, mPaint);
                if(mMarqueePos != 0.0F)
                {
                    f = mMarqueePos + mTextWidth + mTextSize * (float)mMarqueeGap;
                    if(f < f1)
                        canvas.drawText(mText, f6 + f, (float)i + f2, mPaint);
                }
            } else
            {
                mTextLayout.draw(canvas);
            }
        canvas.restore();
    }

    protected void doTick(long l)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag;
        super.doTick(l);
        flag = isVisible();
        if(flag)
            break MISSING_BLOCK_LABEL_25;
        obj;
        JVM INSTR monitorexit ;
        return;
        Object obj1;
        mShouldMarquee = false;
        obj1 = mText;
        mText = getText();
        if(!TextUtils.isEmpty(mText))
            break MISSING_BLOCK_LABEL_66;
        mTextLayout = null;
        updateTextWidth();
        obj;
        JVM INSTR monitorexit ;
        return;
        float f;
        f = mTextSize;
        updateTextSize();
        int i;
        boolean flag1;
        if(!TextUtils.equals(((CharSequence) (obj1)), mText) || f != mTextSize)
            i = 1;
        else
            i = 0;
        if(i == 0)
            break MISSING_BLOCK_LABEL_110;
        updateTextWidth();
        f = getWidth();
        if(!mMultiLine && mMarqueeSpeed > 0 && mTextWidth > f)
            flag1 = true;
        else
            flag1 = false;
        if(!mMultiLine || f > mTextWidth)
            f = mTextWidth;
        if(mTextLayout == null || i != 0)
            break MISSING_BLOCK_LABEL_188;
        if(mLayoutWidth == f)
            break MISSING_BLOCK_LABEL_288;
        mLayoutWidth = f;
        obj1 = JVM INSTR new #474 <Class StaticLayout>;
        ((StaticLayout) (obj1)).StaticLayout(mText, mPaint, (int)Math.ceil(mLayoutWidth), getAlignment(), mSpacingMult, mSpacingAdd, false);
        mTextLayout = ((StaticLayout) (obj1));
        mTextHeight = mTextLayout.getLineTop(mTextLayout.getLineCount());
        if(mHasName)
            mTextHeightVar.set(descale(mTextHeight));
        mMarqueePos = 3.402823E+038F;
        if(!flag1) goto _L2; else goto _L1
_L1:
        if(mMarqueePos != 3.402823E+038F)
            break MISSING_BLOCK_LABEL_356;
        mMarqueePos = 50F;
_L3:
        mPreviousTime = l;
        mShouldMarquee = true;
_L2:
        obj;
        JVM INSTR monitorexit ;
        Exception exception;
        if(mShouldMarquee)
            i = 45;
        else
            i = 0;
        requestFramerate(i);
        return;
        mMarqueePos = mMarqueePos - (float)((long)mMarqueeSpeed * (l - mPreviousTime)) / 1000F;
        if(mMarqueePos < -mTextWidth)
            mMarqueePos = mMarqueePos + (mTextWidth + mTextSize * (float)mMarqueeGap);
          goto _L3
        exception;
        throw exception;
    }

    public void finish()
    {
        super.finish();
        mText = null;
        mSetText = null;
        mMarqueePos = 3.402823E+038F;
    }

    protected int getColor()
    {
        return mColorParser.getColor();
    }

    protected String getFormat()
    {
        return mFormatter.getFormat();
    }

    public float getHeight()
    {
        float f = super.getHeight();
        float f1 = f;
        if(f <= 0.0F)
            f1 = mTextHeight;
        return f1;
    }

    protected int getShadowColor()
    {
        return mShadowColorParser.getColor();
    }

    protected String getText()
    {
        if(mSetText != null)
            return mSetText;
        String s = mFormatter.getText();
        String s1 = s;
        if(s != null)
        {
            s = s.replace("\\n", "\n");
            s1 = s;
            if(!mMultiLine)
                s1 = s.replace("\n", " ");
        }
        return s1;
    }

    public float getWidth()
    {
        float f = super.getWidth();
        float f1 = f;
        if(f <= 0.0F)
            f1 = mTextWidth;
        return f1;
    }

    public void init()
    {
        super.init();
    }

    protected void onVisibilityChange(boolean flag)
    {
        super.onVisibilityChange(flag);
        int i;
        if(mShouldMarquee && flag)
            i = 45;
        else
            i = 0;
        requestFramerate(i);
    }

    public transient void setParams(Object aobj[])
    {
        mFormatter.setParams(aobj);
    }

    public void setText(String s)
    {
        mSetText = s;
    }

    private static final int _2D_miui_2D_maml_2D_elements_2D_ScreenElement$AlignSwitchesValues[];
    private static final String CRLF = "\n";
    private static final int DEFAULT_SIZE = 18;
    private static final String LOG_TAG = "TextScreenElement";
    private static final int MARQUEE_FRAMERATE = 45;
    private static final int PADDING = 50;
    private static final String RAW_CRLF = "\\n";
    public static final String TAG_NAME = "Text";
    public static final String TEXT_HEIGHT = "text_height";
    public static final String TEXT_WIDTH = "text_width";
    private static final Object mLock = new Object();
    private ColorParser mColorParser;
    private boolean mFontScaleEnabled;
    protected TextFormatter mFormatter;
    private float mLayoutWidth;
    private int mMarqueeGap;
    private float mMarqueePos;
    private int mMarqueeSpeed;
    private boolean mMultiLine;
    private TextPaint mPaint;
    private long mPreviousTime;
    private String mSetText;
    private ColorParser mShadowColorParser;
    private float mShadowDx;
    private float mShadowDy;
    private float mShadowRadius;
    private boolean mShouldMarquee;
    private Expression mSizeExpression;
    private float mSpacingAdd;
    private float mSpacingMult;
    private String mText;
    private float mTextHeight;
    private IndexedVariable mTextHeightVar;
    private StaticLayout mTextLayout;
    private float mTextSize;
    private float mTextWidth;
    private IndexedVariable mTextWidthVar;

}
