// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.elements;

import android.graphics.*;
import android.text.TextUtils;
import android.view.MotionEvent;
import miui.maml.ScreenElementRoot;
import miui.maml.animation.*;
import miui.maml.data.*;
import miui.maml.util.Utils;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.elements:
//            ScreenElement, LayerScreenElement, ElementGroup

public abstract class AnimatedScreenElement extends ScreenElement
{

    public AnimatedScreenElement(Element element, ScreenElementRoot screenelementroot)
    {
        super(element, screenelementroot);
        mMatrix = new Matrix();
        mPaint = new Paint();
        mHoverPaint = new Paint();
        load(element);
        if(mHasContentDescription)
            mRoot.addAccessibleElements(this);
        mPaint.setStyle(android.graphics.Paint.Style.STROKE);
        mPaint.setStrokeWidth(1.0F);
        mPaint.setColor(0xffb3f90a);
        mHoverPaint.setStyle(android.graphics.Paint.Style.STROKE);
        mHoverPaint.setStrokeWidth(1.0F);
        mHoverPaint.setColor(0x80ffff00);
    }

    private Expression createExp(Variables variables, Element element, String s, String s1)
    {
        Expression expression = Expression.build(variables, getAttr(element, s));
        s = expression;
        if(expression == null)
        {
            s = expression;
            if(TextUtils.isEmpty(s1) ^ true)
                s = Expression.build(variables, getAttr(element, s1));
        }
        return s;
    }

    private void handleCancel()
    {
        if(!mTouchable)
            return;
        if(mPressed)
        {
            mPressed = false;
            performAction("cancel");
            onActionCancel();
        }
    }

    private void load(Element element)
    {
        boolean flag;
        Variables variables;
        flag = true;
        if(element == null)
            return;
        variables = getVariables();
        mBaseXExpression = createExp(variables, element, "x", "left");
        mBaseYExpression = createExp(variables, element, "y", "top");
        mWidthExpression = createExp(variables, element, "w", "width");
        mHeightExpression = createExp(variables, element, "h", "height");
        mRotationExpression = createExp(variables, element, "angle", "rotation");
        mPivotXExpression = createExp(variables, element, "centerX", "pivotX");
        mPivotYExpression = createExp(variables, element, "centerY", "pivotY");
        mAlphaExpression = createExp(variables, element, "alpha", null);
        mScaleExpression = createExp(variables, element, "scale", null);
        mScaleXExpression = createExp(variables, element, "scaleX", null);
        mScaleYExpression = createExp(variables, element, "scaleY", null);
        mRotationX = createExp(variables, element, "angleX", "rotationX");
        mRotationY = createExp(variables, element, "angleY", "rotationY");
        mRotationZ = createExp(variables, element, "angleZ", "rotationZ");
        mPivotZ = createExp(variables, element, "centerZ", "pivotZ");
        if(mHasName)
        {
            mActualXVar = new IndexedVariable((new StringBuilder()).append(mName).append(".").append("actual_x").toString(), variables, true);
            mActualYVar = new IndexedVariable((new StringBuilder()).append(mName).append(".").append("actual_y").toString(), variables, true);
        }
        break MISSING_BLOCK_LABEL_306;
        boolean flag1;
        if(mRotationX != null || mRotationY != null || mRotationZ != null)
            mCamera = new Camera();
        mTouchable = Boolean.parseBoolean(getAttr(element, "touchable"));
        mInterceptTouch = Boolean.parseBoolean(getAttr(element, "interceptTouch"));
        mIsHaptic = Boolean.parseBoolean(getAttr(element, "haptic"));
        mContentDescription = getAttr(element, "contentDescription");
        mContentDescriptionExp = Expression.build(variables, getAttr(element, "contentDescriptionExp"));
        flag1 = flag;
        if(TextUtils.isEmpty(mContentDescription))
            if(mContentDescriptionExp != null)
                flag1 = flag;
            else
                flag1 = false;
        mHasContentDescription = flag1;
        mMarginLeft = Utils.getAttrAsFloat(element, "marginLeft", 0.0F);
        mMarginRight = Utils.getAttrAsFloat(element, "marginRight", 0.0F);
        mMarginTop = Utils.getAttrAsFloat(element, "marginTop", 0.0F);
        mMarginBottom = Utils.getAttrAsFloat(element, "marginBottom", 0.0F);
        return;
    }

    protected void doRenderWithTranslation(Canvas canvas)
    {
        int i;
        float f;
        float f4;
        float f8;
        i = canvas.save();
        mMatrix.reset();
        if(mCamera != null)
        {
            mCamera.save();
            f = getRotationX();
            f4 = getRotationY();
            f8 = getRotationZ();
            break MISSING_BLOCK_LABEL_43;
        }
_L1:
        float f1 = getRotation();
        if(f1 != 0.0F)
            mMatrix.preRotate(f1);
        f1 = getScaleX();
        f8 = getScaleY();
        if(f1 != 1.0F || f8 != 1.0F)
            mMatrix.preScale(f1, f8);
        f8 = getX();
        f1 = getY();
        float f5 = getPivotX() - (f8 - getLeft());
        float f9 = getPivotY() - (f1 - getTop());
        mMatrix.preTranslate(-f5, -f9);
        mMatrix.postTranslate(f5 + f8, f9 + f1);
        canvas.concat(mMatrix);
        doRender(canvas);
        if(mRoot.mShowDebugLayout)
        {
            float f6 = getWidth();
            f8 = getHeight();
            if(f6 > 0.0F && f8 > 0.0F)
            {
                float f10 = getLeft(0.0F, f6);
                float f2 = getTop(0.0F, f8);
                canvas.drawRect(f10, f2, f10 + f6, f2 + f8, mPaint);
            }
        }
        if(mRoot.getHoverElement() == this)
        {
            float f3 = getWidth();
            float f11 = getHeight();
            if(f3 > 0.0F && f11 > 0.0F)
            {
                float f7 = getLeft(0.0F, f3);
                f8 = getTop(0.0F, f11);
                canvas.drawRect(f7, f8, f7 + f3, f8 + f11, mHoverPaint);
            }
        }
        canvas.restoreToCount(i);
        return;
        if(f != 0.0F || f4 != 0.0F || f8 != 0.0F)
        {
            mCamera.rotate(f, f4, f8);
            if(mPivotZ != null)
                mCamera.translate(0.0F, 0.0F, (float)mPivotZ.evaluate());
            mCamera.getMatrix(mMatrix);
            mCamera.restore();
        }
          goto _L1
    }

    protected void doTick(long l)
    {
        int i = 0;
        super.doTick(l);
        if(mHasName)
        {
            mActualXVar.set(descale(getX()));
            mActualYVar.set(descale(getY()));
        }
        mAlpha = evaluateAlpha();
        if(mAlpha >= 0)
            i = mAlpha;
        mAlpha = i;
    }

    protected int evaluateAlpha()
    {
        int i;
        int j;
        if(mAlphaExpression != null)
            i = (int)mAlphaExpression.evaluate();
        else
            i = 255;
        if(mAlphas != null)
            j = mAlphas.getAlpha();
        else
            j = 255;
        j = Utils.mixAlpha(i, j);
        i = j;
        if(mParent != null)
        {
            i = j;
            if(!(mParent instanceof LayerScreenElement))
                if((mParent instanceof ElementGroup) && mParent.isLayered())
                    i = j;
                else
                    i = Utils.mixAlpha(j, mParent.getAlpha());
        }
        return i;
    }

    public float getAbsoluteLeft()
    {
        float f = getLeft();
        float f1;
        if(mParent == null)
            f1 = 0.0F;
        else
            f1 = mParent.getParentLeft();
        return f1 + f;
    }

    public float getAbsoluteTop()
    {
        float f = getTop();
        float f1;
        if(mParent == null)
            f1 = 0.0F;
        else
            f1 = mParent.getParentTop();
        return f1 + f;
    }

    public int getAlpha()
    {
        return mAlpha;
    }

    public String getContentDescription()
    {
        if(mContentDescriptionExp != null)
            return mContentDescriptionExp.evaluateStr();
        else
            return mContentDescription;
    }

    public float getHeight()
    {
        return scale(getHeightRaw());
    }

    public float getHeightRaw()
    {
        if(mSizes != null)
            return (float)mSizes.getHeight();
        double d;
        if(mHeightExpression != null)
            d = mHeightExpression.evaluate();
        else
            d = -1D;
        return (float)d;
    }

    protected float getLeft()
    {
        return getLeft(getX(), getWidth());
    }

    public final float getMarginBottom()
    {
        return scale(mMarginBottom);
    }

    public final float getMarginLeft()
    {
        return scale(mMarginLeft);
    }

    public final float getMarginRight()
    {
        return scale(mMarginRight);
    }

    public final float getMarginTop()
    {
        return scale(mMarginTop);
    }

    protected Matrix getMatrix()
    {
        return mMatrix;
    }

    protected float getMaxHeight()
    {
        if(mSizes != null)
            return scale(mSizes.getMaxHeight());
        double d;
        if(mHeightExpression != null)
            d = mHeightExpression.evaluate();
        else
            d = -1D;
        return scale(d);
    }

    protected float getMaxWidth()
    {
        if(mSizes != null)
            return scale(mSizes.getMaxWidth());
        double d;
        if(mWidthExpression != null)
            d = mWidthExpression.evaluate();
        else
            d = -1D;
        return scale(d);
    }

    protected float getPivotX()
    {
        return scale(getPivotXRaw());
    }

    protected float getPivotXRaw()
    {
        double d;
        if(mPivotXExpression != null)
            d = mPivotXExpression.evaluate();
        else
            d = 0.0D;
        return (float)d;
    }

    protected float getPivotY()
    {
        return scale(getPivotYRaw());
    }

    protected float getPivotYRaw()
    {
        double d;
        if(mPivotYExpression != null)
            d = mPivotYExpression.evaluate();
        else
            d = 0.0D;
        return (float)d;
    }

    protected float getRotation()
    {
        double d;
        if(mRotationExpression != null)
            d = mRotationExpression.evaluate();
        else
            d = 0.0D;
        if(mRotations != null)
            return (float)d + mRotations.getAngle();
        else
            return (float)d;
    }

    protected float getRotationX()
    {
        double d;
        if(mRotationX != null)
            d = mRotationX.evaluate();
        else
            d = 0.0D;
        return (float)d;
    }

    protected float getRotationY()
    {
        double d;
        if(mRotationY != null)
            d = mRotationY.evaluate();
        else
            d = 0.0D;
        return (float)d;
    }

    protected float getRotationZ()
    {
        double d;
        if(mRotationZ != null)
            d = mRotationZ.evaluate();
        else
            d = 0.0D;
        return (float)d;
    }

    protected float getScaleX()
    {
        double d;
        if(mScaleExpression != null)
            d = mScaleExpression.evaluate();
        else
        if(mScaleXExpression != null)
            d = mScaleXExpression.evaluate();
        else
            d = 1.0D;
        if(mScales != null)
            return (float)(mScales.getScaleX() * d);
        else
            return (float)d;
    }

    protected float getScaleY()
    {
        double d;
        if(mScaleExpression != null)
            d = mScaleExpression.evaluate();
        else
        if(mScaleYExpression != null)
            d = mScaleYExpression.evaluate();
        else
            d = 1.0D;
        if(mScales != null)
            return (float)(mScales.getScaleY() * d);
        else
            return (float)d;
    }

    protected float getTop()
    {
        return getTop(getY(), getHeight());
    }

    public float getWidth()
    {
        return scale(getWidthRaw());
    }

    public float getWidthRaw()
    {
        if(mSizes != null)
            return (float)mSizes.getWidth();
        double d;
        if(mWidthExpression != null)
            d = mWidthExpression.evaluate();
        else
            d = -1D;
        return (float)d;
    }

    protected float getX()
    {
        return scale(getXRaw());
    }

    protected float getXRaw()
    {
        double d;
        double d1;
        if(mBaseXExpression != null)
            d = mBaseXExpression.evaluate();
        else
            d = 0.0D;
        d1 = d;
        if(mPositions != null)
            d1 = d + mPositions.getX();
        return (float)d1;
    }

    protected float getY()
    {
        return scale(getYRaw());
    }

    protected float getYRaw()
    {
        double d;
        double d1;
        if(mBaseYExpression != null)
            d = mBaseYExpression.evaluate();
        else
            d = 0.0D;
        d1 = d;
        if(mPositions != null)
            d1 = d + mPositions.getY();
        return (float)d1;
    }

    public void init()
    {
        super.init();
    }

    protected void onActionCancel()
    {
    }

    protected void onActionDown(float f, float f1)
    {
        mRoot.onUIInteractive(this, "down");
    }

    protected void onActionMove(float f, float f1)
    {
        mRoot.onUIInteractive(this, "move");
    }

    protected void onActionUp()
    {
        mRoot.onUIInteractive(this, "up");
    }

    protected BaseAnimation onCreateAnimation(String s, Element element)
    {
        if("AlphaAnimation".equals(s))
        {
            s = new AlphaAnimation(element, this);
            mAlphas = s;
            return s;
        }
        if("PositionAnimation".equals(s))
        {
            s = new PositionAnimation(element, this);
            mPositions = s;
            return s;
        }
        if("RotationAnimation".equals(s))
        {
            s = new RotationAnimation(element, this);
            mRotations = s;
            return s;
        }
        if("SizeAnimation".equals(s))
        {
            s = new SizeAnimation(element, this);
            mSizes = s;
            return s;
        }
        if("ScaleAnimation".equals(s))
        {
            s = new ScaleAnimation(element, this);
            mScales = s;
            return s;
        } else
        {
            return super.onCreateAnimation(s, element);
        }
    }

    public boolean onHover(MotionEvent motionevent)
    {
        boolean flag;
        String s;
        float f;
        float f1;
        boolean flag1;
        boolean flag2;
        flag = false;
        if(!isVisible() || mHasContentDescription ^ true)
            return false;
        s = getContentDescription();
        f = motionevent.getX();
        f1 = motionevent.getY();
        flag1 = super.onHover(motionevent);
        flag2 = flag1;
        motionevent.getActionMasked();
        JVM INSTR tableswitch 7 10: default 84
    //                   7 139
    //                   8 88
    //                   9 109
    //                   10 88;
           goto _L1 _L2 _L3 _L4 _L3
_L1:
        flag2 = flag1;
_L3:
        if(flag2)
            requestUpdate();
        if(flag2)
            flag = mInterceptTouch;
        return flag;
_L4:
        flag2 = flag1;
        if(touched(f, f1))
        {
            mRoot.onHoverChange(this, s);
            flag2 = true;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        flag2 = flag1;
        if(touched(f, f1))
        {
            if(mRoot.getHoverElement() != this)
                mRoot.onHoverChange(this, s);
            flag2 = true;
        }
        if(true) goto _L3; else goto _L5
_L5:
    }

    protected void onSetAnimBefore()
    {
        mAlphas = null;
        mPositions = null;
        mRotations = null;
        mSizes = null;
        mScales = null;
    }

    protected void onSetAnimEnable(BaseAnimation baseanimation)
    {
        if(!(baseanimation instanceof AlphaAnimation)) goto _L2; else goto _L1
_L1:
        mAlphas = (AlphaAnimation)baseanimation;
_L4:
        return;
_L2:
        if(baseanimation instanceof PositionAnimation)
            mPositions = (PositionAnimation)baseanimation;
        else
        if(baseanimation instanceof RotationAnimation)
            mRotations = (RotationAnimation)baseanimation;
        else
        if(baseanimation instanceof SizeAnimation)
            mSizes = (SizeAnimation)baseanimation;
        else
        if(baseanimation instanceof ScaleAnimation)
            mScales = (ScaleAnimation)baseanimation;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean onTouch(MotionEvent motionevent)
    {
        boolean flag;
        float f;
        float f1;
        boolean flag1;
        flag = false;
        if(!isVisible() || mTouchable ^ true)
            return false;
        f = motionevent.getX();
        f1 = motionevent.getY();
        flag1 = super.onTouch(motionevent);
        motionevent.getActionMasked();
        JVM INSTR tableswitch 0 3: default 72
    //                   0 93
    //                   1 176
    //                   2 143
    //                   3 243;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        if(flag1)
            requestUpdate();
        if(flag1)
            flag = mInterceptTouch;
        return flag;
_L2:
        if(touched(f, f1))
        {
            mPressed = true;
            if(mIsHaptic)
                mRoot.haptic(1);
            performAction("down");
            onActionDown(f, f1);
            flag1 = true;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if(mPressed)
        {
            flag1 = touched(f, f1);
            performAction("move");
            onActionMove(f, f1);
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if(mPressed)
        {
            mPressed = false;
            if(touched(f, f1))
            {
                if(mIsHaptic)
                    mRoot.haptic(1);
                performAction("up");
                onActionUp();
            } else
            {
                performAction("cancel");
                onActionCancel();
            }
            flag1 = true;
        }
        continue; /* Loop/switch isn't completed */
_L5:
        handleCancel();
        if(true) goto _L1; else goto _L6
_L6:
    }

    protected void onVisibilityChange(boolean flag)
    {
        super.onVisibilityChange(flag);
        if(!flag)
            handleCancel();
    }

    public void pause()
    {
        super.pause();
        handleCancel();
    }

    public void render(Canvas canvas)
    {
        updateVisibility();
        if(!isVisible())
        {
            return;
        } else
        {
            doRenderWithTranslation(canvas);
            return;
        }
    }

    public void setH(double d)
    {
        if(mHeightExpression != null && (mHeightExpression instanceof miui.maml.data.Expression.NumberExpression))
            ((miui.maml.data.Expression.NumberExpression)mHeightExpression).setValue(descale(d));
        else
            mHeightExpression = new miui.maml.data.Expression.NumberExpression(descale(d));
    }

    public void setW(double d)
    {
        if(mWidthExpression != null && (mWidthExpression instanceof miui.maml.data.Expression.NumberExpression))
            ((miui.maml.data.Expression.NumberExpression)mWidthExpression).setValue(descale(d));
        else
            mWidthExpression = new miui.maml.data.Expression.NumberExpression(descale(d));
    }

    public void setX(double d)
    {
        if(mBaseXExpression != null && (mBaseXExpression instanceof miui.maml.data.Expression.NumberExpression))
            ((miui.maml.data.Expression.NumberExpression)mBaseXExpression).setValue(descale(d));
        else
            mBaseXExpression = new miui.maml.data.Expression.NumberExpression(descale(d));
    }

    public void setY(double d)
    {
        if(mBaseYExpression != null && (mBaseYExpression instanceof miui.maml.data.Expression.NumberExpression))
            ((miui.maml.data.Expression.NumberExpression)mBaseYExpression).setValue(descale(d));
        else
            mBaseYExpression = new miui.maml.data.Expression.NumberExpression(descale(d));
    }

    public boolean touched(float f, float f1)
    {
        return touched(f, f1, true);
    }

    public boolean touched(float f, float f1, boolean flag)
    {
        boolean flag1 = false;
        float f2 = f;
        float f3 = f1;
        if(flag)
        {
            float f4;
            float f5;
            if(mParent == null)
                f3 = 0.0F;
            else
                f3 = mParent.getParentLeft();
            if(mParent == null)
                f2 = 0.0F;
            else
                f2 = mParent.getParentTop();
            f -= f3;
            f3 = f1 - f2;
            f2 = f;
        }
        f4 = getLeft();
        f = getTop();
        f5 = getWidth();
        f1 = getHeight();
        flag = flag1;
        if(f2 >= f4)
        {
            flag = flag1;
            if(f2 <= f4 + f5)
            {
                flag = flag1;
                if(f3 >= f)
                {
                    flag = flag1;
                    if(f3 <= f + f1)
                        flag = true;
                }
            }
        }
        return flag;
    }

    private static final int sHoverPaintColor = 0x80ffff00;
    private static final int sPaintColor = 0xffb3f90a;
    private IndexedVariable mActualXVar;
    private IndexedVariable mActualYVar;
    protected int mAlpha;
    private Expression mAlphaExpression;
    private AlphaAnimation mAlphas;
    private Expression mBaseXExpression;
    private Expression mBaseYExpression;
    private Camera mCamera;
    protected String mContentDescription;
    protected Expression mContentDescriptionExp;
    protected boolean mHasContentDescription;
    private Expression mHeightExpression;
    private Paint mHoverPaint;
    protected boolean mInterceptTouch;
    private boolean mIsHaptic;
    private float mMarginBottom;
    private float mMarginLeft;
    private float mMarginRight;
    private float mMarginTop;
    private Matrix mMatrix;
    private Paint mPaint;
    private Expression mPivotXExpression;
    private Expression mPivotYExpression;
    private Expression mPivotZ;
    private PositionAnimation mPositions;
    protected boolean mPressed;
    private Expression mRotationExpression;
    private Expression mRotationX;
    private Expression mRotationY;
    private Expression mRotationZ;
    private RotationAnimation mRotations;
    private Expression mScaleExpression;
    private Expression mScaleXExpression;
    private Expression mScaleYExpression;
    private ScaleAnimation mScales;
    private SizeAnimation mSizes;
    protected boolean mTouchable;
    private Expression mWidthExpression;
}
