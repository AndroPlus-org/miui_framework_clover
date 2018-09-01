// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.elements;

import android.graphics.Canvas;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.ViewConfiguration;
import miui.maml.ScreenContext;
import miui.maml.ScreenElementRoot;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.elements:
//            ElementGroup, ScreenElement

public class ButtonScreenElement extends ElementGroup
{
    public static interface ButtonActionListener
    {

        public abstract boolean onButtonDoubleClick(String s);

        public abstract boolean onButtonDown(String s);

        public abstract boolean onButtonLongClick(String s);

        public abstract boolean onButtonUp(String s);
    }


    public ButtonScreenElement(Element element, ScreenElementRoot screenelementroot)
    {
        super(element, screenelementroot);
        load(element);
    }

    private void load(Element element)
    {
        if(element == null)
        {
            return;
        } else
        {
            mIsAlignChildren = Boolean.parseBoolean(getAttr(element, "alignChildren"));
            mListenerName = getAttr(element, "listener");
            mTouchable = true;
            return;
        }
    }

    private void showNormalElements()
    {
        if(mNormalElements != null)
            mNormalElements.show(true);
        if(mPressedElements != null)
            mPressedElements.show(false);
    }

    private void showPressedElements()
    {
        if(mPressedElements == null) goto _L2; else goto _L1
_L1:
        mPressedElements.show(true);
        if(mNormalElements != null)
            mNormalElements.show(false);
_L4:
        return;
_L2:
        if(mNormalElements != null)
            mNormalElements.show(true);
        if(true) goto _L4; else goto _L3
_L3:
    }

    protected void doRender(Canvas canvas)
    {
        canvas.save();
        if(!mIsAlignChildren)
            canvas.translate(-getLeft(), -getTop());
        super.doRender(canvas);
        canvas.restore();
    }

    protected float getParentLeft()
    {
        float f = 0.0F;
        float f1;
        if(mIsAlignChildren)
            f1 = getLeft();
        else
            f1 = 0.0F;
        if(mParent != null)
            f = mParent.getParentLeft();
        return f1 + f;
    }

    protected float getParentTop()
    {
        float f = 0.0F;
        float f1;
        if(mIsAlignChildren)
            f1 = getTop();
        else
            f1 = 0.0F;
        if(mParent != null)
            f = mParent.getParentTop();
        return f1 + f;
    }

    public void init()
    {
        super.init();
        if(mListener == null && TextUtils.isEmpty(mListenerName) ^ true)
        {
            ScreenElement screenelement = mRoot.findElement(mListenerName);
            try
            {
                mListener = (ButtonActionListener)screenelement;
            }
            catch(ClassCastException classcastexception)
            {
                Log.e("ButtonScreenElement", (new StringBuilder()).append("button listener designated by the name is not actually a listener: ").append(mListenerName).toString());
            }
        }
        showNormalElements();
    }

    protected void onActionCancel()
    {
        super.onActionCancel();
        resetState();
    }

    protected void onActionDown(float f, float f1)
    {
        super.onActionDown(f, f1);
        if(mListener != null)
            mListener.onButtonDown(mName);
        if(SystemClock.uptimeMillis() - mPreviousTapUpTime <= (long)ViewConfiguration.getDoubleTapTimeout())
        {
            float f2 = f - mPreviousTapPositionX;
            float f3 = f1 - mPreviousTapPositionY;
            int i = ViewConfiguration.get(getContext().mContext).getScaledDoubleTapSlop();
            if(f2 * f2 + f3 * f3 < (float)(i * i))
            {
                if(mListener != null)
                    mListener.onButtonDoubleClick(mName);
                performAction("double");
            }
        }
        mPreviousTapPositionX = f;
        mPreviousTapPositionY = f1;
        showPressedElements();
        if(mPressedElements != null)
            mPressedElements.reset();
    }

    protected void onActionUp()
    {
        super.onActionUp();
        if(mListener != null)
            mListener.onButtonUp(mName);
        mPreviousTapUpTime = SystemClock.uptimeMillis();
        resetState();
    }

    protected ScreenElement onCreateChild(Element element)
    {
        String s = element.getTagName();
        if(s.equalsIgnoreCase("Normal"))
        {
            element = new ElementGroup(element, mRoot);
            mNormalElements = element;
            return element;
        }
        if(s.equalsIgnoreCase("Pressed"))
        {
            element = new ElementGroup(element, mRoot);
            mPressedElements = element;
            return element;
        } else
        {
            return super.onCreateChild(element);
        }
    }

    protected void resetState()
    {
        showNormalElements();
        if(mNormalElements != null)
            mNormalElements.reset();
    }

    public void setListener(ButtonActionListener buttonactionlistener)
    {
        mListener = buttonactionlistener;
    }

    private static final String LOG_TAG = "ButtonScreenElement";
    public static final String TAG_NAME = "Button";
    private boolean mIsAlignChildren;
    private ButtonActionListener mListener;
    private String mListenerName;
    private ElementGroup mNormalElements;
    private ElementGroup mPressedElements;
    private float mPreviousTapPositionX;
    private float mPreviousTapPositionY;
    private long mPreviousTapUpTime;
}
