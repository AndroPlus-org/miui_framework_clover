// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.elements;

import android.graphics.*;
import miui.maml.ScreenElementRoot;
import miui.maml.data.Expression;
import miui.maml.util.ColorParser;
import miui.maml.util.Utils;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.elements:
//            AnimatedScreenElement

public class PaintScreenElement extends AnimatedScreenElement
{

    public PaintScreenElement(Element element, ScreenElementRoot screenelementroot)
    {
        super(element, screenelementroot);
        load(element, screenelementroot);
        mPath = new Path();
        DEFAULT_WEIGHT = scale(DEFAULT_WEIGHT);
        mWeight = DEFAULT_WEIGHT;
        mPaint = new Paint();
        mPaint.setXfermode(mXfermode);
        mPaint.setAntiAlias(true);
        mCachedPaint = new Paint();
        mCachedPaint.setStyle(android.graphics.Paint.Style.STROKE);
        mCachedPaint.setStrokeWidth(DEFAULT_WEIGHT);
        mCachedPaint.setStrokeCap(android.graphics.Paint.Cap.ROUND);
        mCachedPaint.setStrokeJoin(android.graphics.Paint.Join.ROUND);
        mCachedPaint.setAntiAlias(true);
        mTouchable = true;
    }

    private void load(Element element, ScreenElementRoot screenelementroot)
    {
        if(element == null)
        {
            return;
        } else
        {
            screenelementroot = getVariables();
            mWeightExp = Expression.build(screenelementroot, element.getAttribute("weight"));
            mColorParser = ColorParser.fromElement(screenelementroot, element);
            mXfermode = new PorterDuffXfermode(Utils.getPorterDuffMode(element.getAttribute("xfermode")));
            return;
        }
    }

    protected void doRender(Canvas canvas)
    {
        float f = getWidth();
        float f1 = getHeight();
        f = getLeft(0.0F, f);
        float f2 = getTop(0.0F, f1);
        float f3 = getAbsoluteLeft();
        f1 = getAbsoluteTop();
        if(mPendingMouseUp)
        {
            mCachedCanvas.save();
            mCachedCanvas.translate(-f3, -f1);
            mCachedCanvas.drawPath(mPath, mCachedPaint);
            mCachedCanvas.restore();
            mPath.reset();
            mPendingMouseUp = false;
        }
        canvas.drawBitmap(mCachedBitmap, f, f2, mPaint);
        if(mPressed && mWeight > 0.0F && mAlpha > 0)
        {
            mCachedPaint.setStrokeWidth(mWeight);
            mCachedPaint.setColor(mColor);
            mCachedPaint.setAlpha(Utils.mixAlpha(mCachedPaint.getAlpha(), mAlpha));
            canvas.save();
            canvas.translate(-f3 + f, -f1 + f2);
            Xfermode xfermode = mCachedPaint.getXfermode();
            mCachedPaint.setXfermode(mXfermode);
            canvas.drawPath(mPath, mCachedPaint);
            mCachedPaint.setXfermode(xfermode);
            canvas.restore();
        }
    }

    protected void doTick(long l)
    {
        super.doTick(l);
        if(!isVisible())
            return;
        if(mWeightExp != null)
            mWeight = scale(mWeightExp.evaluate());
        mColor = mColorParser.getColor();
    }

    public void finish()
    {
        super.finish();
        mCachedBitmap.recycle();
        mCachedBitmap = null;
        mCachedCanvas = null;
    }

    public void init()
    {
        super.init();
        float f = getWidth();
        float f1 = f;
        if(f < 0.0F)
            f1 = scale(Utils.getVariableNumber("screen_width", getVariables()));
        float f2 = getHeight();
        f = f2;
        if(f2 < 0.0F)
            f = scale(Utils.getVariableNumber("screen_height", getVariables()));
        mCachedBitmap = Bitmap.createBitmap((int)Math.ceil(f1), (int)Math.ceil(f), android.graphics.Bitmap.Config.ARGB_8888);
        mCachedBitmap.setDensity(mRoot.getTargetDensity());
        mCachedCanvas = new Canvas(mCachedBitmap);
    }

    protected void onActionCancel()
    {
        mPendingMouseUp = true;
    }

    protected void onActionDown(float f, float f1)
    {
        super.onActionDown(f, f1);
        mPath.reset();
        mPath.moveTo(f, f1);
    }

    protected void onActionMove(float f, float f1)
    {
        super.onActionMove(f, f1);
        mPath.lineTo(f, f1);
    }

    protected void onActionUp()
    {
        super.onActionUp();
        mPendingMouseUp = true;
    }

    public void reset(long l)
    {
        super.reset(l);
        mCachedCanvas.drawColor(0, android.graphics.PorterDuff.Mode.CLEAR);
        mPressed = false;
    }

    private static float DEFAULT_WEIGHT = 0F;
    public static final String TAG_NAME = "Paint";
    private Bitmap mCachedBitmap;
    private Canvas mCachedCanvas;
    private Paint mCachedPaint;
    private int mColor;
    private ColorParser mColorParser;
    private Paint mPaint;
    private Path mPath;
    private boolean mPendingMouseUp;
    private float mWeight;
    private Expression mWeightExp;
    private Xfermode mXfermode;

    static 
    {
        DEFAULT_WEIGHT = 1.0F;
    }
}
