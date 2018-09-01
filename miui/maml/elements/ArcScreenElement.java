// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.elements;

import android.graphics.*;
import miui.maml.ScreenElementRoot;
import miui.maml.data.Expression;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.elements:
//            GeometryScreenElement

public class ArcScreenElement extends GeometryScreenElement
{

    public ArcScreenElement(Element element, ScreenElementRoot screenelementroot)
    {
        super(element, screenelementroot);
        mArcPath = new Path();
        mOvalRect = new RectF();
        screenelementroot = screenelementroot.getVariables();
        mAngleExp = Expression.build(screenelementroot, getAttr(element, "startAngle"));
        mSweepExp = Expression.build(screenelementroot, getAttr(element, "sweep"));
        mClose = Boolean.parseBoolean(getAttr(element, "close"));
        mAlign = ScreenElement.Align.CENTER;
        mAlignV = ScreenElement.AlignV.CENTER;
    }

    protected void doTick(long l)
    {
        super.doTick(l);
        if(!isVisible())
        {
            return;
        } else
        {
            mRoot.getVariables();
            mAngle = (float)mAngleExp.evaluate();
            mSweep = (float)mSweepExp.evaluate();
            return;
        }
    }

    protected void onDraw(Canvas canvas, GeometryScreenElement.DrawMode drawmode)
    {
        float f = getWidth();
        float f1 = getHeight();
        float f2 = 0.0F - f / 2.0F;
        float f3 = 0.0F - f1 / 2.0F;
        mArcPath.reset();
        mOvalRect.left = f2;
        mOvalRect.top = f3;
        mOvalRect.right = f2 + f;
        mOvalRect.bottom = f3 + f1;
        if(Math.abs(mSweep) >= 360F)
        {
            canvas.drawOval(mOvalRect, mPaint);
        } else
        {
            if(mClose)
                mArcPath.moveTo(mOvalRect.centerX(), mOvalRect.centerY());
            mArcPath.arcTo(mOvalRect, mAngle, mSweep, mClose ^ true);
            if(mClose)
                mArcPath.close();
            canvas.drawPath(mArcPath, mPaint);
        }
    }

    public static final String TAG_NAME = "Arc";
    private float mAngle;
    private Expression mAngleExp;
    private Path mArcPath;
    private boolean mClose;
    RectF mOvalRect;
    private float mSweep;
    private Expression mSweepExp;
}
