// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.elements;

import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import miui.maml.ScreenElementRoot;
import miui.maml.util.Utils;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

// Referenced classes of package miui.maml.elements:
//            ScreenElement

public class FramerateController extends ScreenElement
{
    public static class ControlPoint
    {

        public int mFramerate;
        public long mTime;

        public ControlPoint(Element element)
        {
            mTime = Utils.getAttrAsLongThrows(element, "time");
            mFramerate = Utils.getAttrAsInt(element, "frameRate", -1);
        }
    }


    public FramerateController(Element element, ScreenElementRoot screenelementroot)
    {
        super(element, screenelementroot);
        mControlPoints = new ArrayList();
        mLock = new Object();
        mLoop = Boolean.parseBoolean(element.getAttribute("loop"));
        mTag = element.getAttribute("tag");
        screenelementroot = element.getAttribute("delay");
        if(!TextUtils.isEmpty(screenelementroot))
            try
            {
                mDelay = Long.parseLong(screenelementroot);
            }
            // Misplaced declaration of an exception variable
            catch(ScreenElementRoot screenelementroot)
            {
                Log.w("FramerateController", "invalid delay attribute");
            }
        screenelementroot = element.getElementsByTagName("ControlPoint");
        for(int i = 0; i < screenelementroot.getLength(); i++)
        {
            element = (Element)screenelementroot.item(i);
            mControlPoints.add(new ControlPoint(element));
        }

        mTimeRange = ((ControlPoint)mControlPoints.get(mControlPoints.size() - 1)).mTime;
        boolean flag;
        if(mLoop && mTimeRange != 0L)
            flag = true;
        else
            flag = false;
        mLoop = flag;
    }

    private void restart(long l)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        mStartTime = mDelay + l;
        mStopped = false;
        mLastUpdateTime = 0L;
        mNextUpdateInterval = 0L;
        requestUpdate();
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    protected void doRender(Canvas canvas)
    {
    }

    protected void playAnim(long l, long l1, long l2, boolean flag, 
            boolean flag1)
    {
        if(!isVisible())
        {
            return;
        } else
        {
            super.playAnim(l, l1, l2, flag, flag1);
            restart(l - l1);
            return;
        }
    }

    public void reset(long l)
    {
        super.reset(l);
        restart(l);
    }

    public void setAnim(String as[])
    {
        show(isTagEnable(as, mTag));
    }

    public long updateFramerate(long l)
    {
        updateVisibility();
        if(!isVisible())
            return 0x7fffffffffffffffL;
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag = mStopped;
        if(!flag)
            break MISSING_BLOCK_LABEL_39;
        obj;
        JVM INSTR monitorexit ;
        return 0x7fffffffffffffffL;
        long l1;
        if(mLastUpdateTime <= 0L)
            break MISSING_BLOCK_LABEL_98;
        l1 = l - mLastUpdateTime;
        if(l1 < 0L)
            break MISSING_BLOCK_LABEL_98;
        if(l1 >= mNextUpdateInterval)
            break MISSING_BLOCK_LABEL_98;
        mNextUpdateInterval = mNextUpdateInterval - l1;
        mLastUpdateTime = l;
        l = mNextUpdateInterval;
        obj;
        JVM INSTR monitorexit ;
        return l;
        long l2 = l - mStartTime;
        l1 = l2;
        if(l2 < 0L)
            l1 = 0L;
        if(mLoop)
            l1 %= mTimeRange + 1L;
        l2 = 0L;
        int i = mControlPoints.size() - 1;
_L2:
        if(i < 0)
            break; /* Loop/switch isn't completed */
        ControlPoint controlpoint;
        controlpoint = (ControlPoint)mControlPoints.get(i);
        if(l1 < controlpoint.mTime)
            break MISSING_BLOCK_LABEL_260;
        requestFramerate(controlpoint.mFramerate);
        if(!mLoop && i == mControlPoints.size() - 1)
            mStopped = true;
        mLastUpdateTime = l;
        if(mStopped)
            l = 0x7fffffffffffffffL;
        else
            l = l2 - l1;
        mNextUpdateInterval = l;
        l = mNextUpdateInterval;
        obj;
        JVM INSTR monitorexit ;
        return l;
        l2 = controlpoint.mTime;
        i--;
        if(true) goto _L2; else goto _L1
_L1:
        return 0x7fffffffffffffffL;
        Exception exception;
        exception;
        throw exception;
    }

    public static final String INNER_TAG = "ControlPoint";
    public static final String TAG_NAME = "FramerateController";
    private ArrayList mControlPoints;
    private long mDelay;
    private long mLastUpdateTime;
    private Object mLock;
    private boolean mLoop;
    private long mNextUpdateInterval;
    private long mStartTime;
    private boolean mStopped;
    private String mTag;
    private long mTimeRange;
}
