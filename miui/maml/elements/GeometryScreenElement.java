// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.elements;

import android.graphics.*;
import android.text.TextUtils;
import miui.maml.ScreenElementRoot;
import miui.maml.data.Expression;
import miui.maml.shader.ShadersElement;
import miui.maml.util.ColorParser;
import miui.maml.util.Utils;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.elements:
//            AnimatedScreenElement

public abstract class GeometryScreenElement extends AnimatedScreenElement
{
    protected static final class DrawMode extends Enum
    {

        public static DrawMode getStrokeAlign(String s)
        {
            if("inner".equalsIgnoreCase(s))
                return STROKE_INNER;
            if("center".equalsIgnoreCase(s))
                return STROKE_CENTER;
            else
                return STROKE_OUTER;
        }

        public static DrawMode valueOf(String s)
        {
            return (DrawMode)Enum.valueOf(miui/maml/elements/GeometryScreenElement$DrawMode, s);
        }

        public static DrawMode[] values()
        {
            return $VALUES;
        }

        private static final DrawMode $VALUES[];
        public static final DrawMode FILL;
        public static final DrawMode STROKE_CENTER;
        public static final DrawMode STROKE_INNER;
        public static final DrawMode STROKE_OUTER;

        static 
        {
            STROKE_CENTER = new DrawMode("STROKE_CENTER", 0);
            STROKE_OUTER = new DrawMode("STROKE_OUTER", 1);
            STROKE_INNER = new DrawMode("STROKE_INNER", 2);
            FILL = new DrawMode("FILL", 3);
            $VALUES = (new DrawMode[] {
                STROKE_CENTER, STROKE_OUTER, STROKE_INNER, FILL
            });
        }

        private DrawMode(String s, int i)
        {
            super(s, i);
        }
    }


    public GeometryScreenElement(Element element, ScreenElementRoot screenelementroot)
    {
        super(element, screenelementroot);
        mPaint = new Paint();
        mWeight = scale(1.0D);
        Object obj = getAttr(element, "strokeColor");
        miui.maml.data.Variables variables = getVariables();
        if(!TextUtils.isEmpty(((CharSequence) (obj))))
            mStrokeColorParser = new ColorParser(variables, ((String) (obj)));
        obj = getAttr(element, "fillColor");
        if(!TextUtils.isEmpty(((CharSequence) (obj))))
            mFillColorParser = new ColorParser(variables, ((String) (obj)));
        mWeightExp = Expression.build(variables, getAttr(element, "weight"));
        obj = getCap(getAttr(element, "cap"));
        mPaint.setStrokeCap(((android.graphics.Paint.Cap) (obj)));
        float af[] = resolveDashIntervals(element);
        if(af != null)
            mPaint.setPathEffect(new DashPathEffect(af, 0.0F));
        mStrokeAlign = DrawMode.getStrokeAlign(getAttr(element, "strokeAlign"));
        mXfermodeNumExp = Expression.build(variables, getAttr(element, "xfermodeNum"));
        if(mXfermodeNumExp == null)
        {
            android.graphics.PorterDuff.Mode mode = Utils.getPorterDuffMode(getAttr(element, "xfermode"));
            mPaint.setXfermode(new PorterDuffXfermode(mode));
        }
        mPaint.setAntiAlias(true);
        loadShadersElement(element, screenelementroot);
    }

    private final android.graphics.Paint.Cap getCap(String s)
    {
        android.graphics.Paint.Cap cap;
        cap = android.graphics.Paint.Cap.BUTT;
        if(TextUtils.isEmpty(s))
            return cap;
        if(!s.equalsIgnoreCase("round")) goto _L2; else goto _L1
_L1:
        cap = android.graphics.Paint.Cap.ROUND;
_L4:
        return cap;
_L2:
        if(s.equalsIgnoreCase("square"))
            cap = android.graphics.Paint.Cap.SQUARE;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void loadShadersElement(Element element, ScreenElementRoot screenelementroot)
    {
        Element element1 = Utils.getChild(element, "StrokeShaders");
        if(element1 != null)
            mStrokeShadersElement = new ShadersElement(element1, screenelementroot);
        element = Utils.getChild(element, "FillShaders");
        if(element != null)
            mFillShadersElement = new ShadersElement(element, screenelementroot);
    }

    private float[] resolveDashIntervals(Element element)
    {
        element = getAttr(element, "dash");
        if(TextUtils.isEmpty(element))
            return null;
        element = element.split(",");
        if(element.length >= 2 && element.length % 2 == 0)
        {
            float af[] = new float[element.length];
            int i = 0;
            while(i < element.length) 
            {
                try
                {
                    af[i] = Float.parseFloat(element[i]);
                }
                // Misplaced declaration of an exception variable
                catch(Element element)
                {
                    return null;
                }
                i++;
            }
            return af;
        } else
        {
            return null;
        }
    }

    protected void doRender(Canvas canvas)
    {
        if(mFillShadersElement != null || mFillColorParser != null)
        {
            mPaint.setStyle(android.graphics.Paint.Style.FILL);
            if(mFillShadersElement != null)
            {
                mPaint.setShader(mFillShadersElement.getShader());
                mPaint.setAlpha(mAlpha);
            } else
            {
                mPaint.setShader(null);
                mPaint.setColor(mFillColor);
                mPaint.setAlpha(Utils.mixAlpha(mPaint.getAlpha(), mAlpha));
            }
            onDraw(canvas, DrawMode.FILL);
        }
        if(mWeight > 0.0F && (mStrokeShadersElement != null || mStrokeColorParser != null))
        {
            mPaint.setStyle(android.graphics.Paint.Style.STROKE);
            mPaint.setStrokeWidth(mWeight);
            if(mStrokeShadersElement != null)
            {
                mPaint.setShader(mStrokeShadersElement.getShader());
                mPaint.setAlpha(mAlpha);
            } else
            {
                mPaint.setShader(null);
                mPaint.setColor(mStrokeColor);
                mPaint.setAlpha(Utils.mixAlpha(mPaint.getAlpha(), mAlpha));
            }
            onDraw(canvas, mStrokeAlign);
        }
    }

    protected void doTick(long l)
    {
        super.doTick(l);
        if(!isVisible())
            return;
        if(mStrokeColorParser != null)
            mStrokeColor = mStrokeColorParser.getColor();
        if(mFillColorParser != null)
            mFillColor = mFillColorParser.getColor();
        if(mStrokeShadersElement != null)
            mStrokeShadersElement.updateShader();
        if(mFillShadersElement != null)
            mFillShadersElement.updateShader();
        if(mWeightExp != null)
            mWeight = scale(mWeightExp.evaluate());
        if(mXfermodeNumExp != null)
        {
            android.graphics.PorterDuff.Mode mode = Utils.getPorterDuffMode((int)mXfermodeNumExp.evaluate());
            mPaint.setXfermode(new PorterDuffXfermode(mode));
        }
    }

    protected abstract void onDraw(Canvas canvas, DrawMode drawmode);

    private static final String LOG_TAG = "GeometryScreenElement";
    private int mFillColor;
    protected ColorParser mFillColorParser;
    protected ShadersElement mFillShadersElement;
    protected Paint mPaint;
    private final DrawMode mStrokeAlign;
    private int mStrokeColor;
    protected ColorParser mStrokeColorParser;
    protected ShadersElement mStrokeShadersElement;
    protected float mWeight;
    protected Expression mWeightExp;
    protected Expression mXfermodeNumExp;
}
