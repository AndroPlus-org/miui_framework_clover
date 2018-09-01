// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.elements;

import android.content.Intent;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import java.util.ArrayList;
import java.util.Iterator;
import miui.maml.*;
import miui.maml.animation.interpolater.InterpolatorHelper;
import miui.maml.data.*;
import miui.maml.util.*;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

// Referenced classes of package miui.maml.elements:
//            ElementGroup, ScreenElement, ITicker

public class AdvancedSlider extends ElementGroup
{
    private class CheckTouchResult
    {

        public EndPoint endPoint;
        public boolean reached;
        final AdvancedSlider this$0;

        private CheckTouchResult()
        {
            this$0 = AdvancedSlider.this;
            super();
        }

        CheckTouchResult(CheckTouchResult checktouchresult)
        {
            this();
        }
    }

    private class EndPoint extends SliderPoint
    {

        static ArrayList _2D_get0(EndPoint endpoint)
        {
            return endpoint.mPath;
        }

        private static int[] _2D_getmiui_2D_maml_2D_elements_2D_AdvancedSlider$StateSwitchesValues()
        {
            if(_2D_miui_2D_maml_2D_elements_2D_AdvancedSlider$StateSwitchesValues != null)
                return _2D_miui_2D_maml_2D_elements_2D_AdvancedSlider$StateSwitchesValues;
            int ai[] = new int[State.values().length];
            try
            {
                ai[State.Invalid.ordinal()] = 2;
            }
            catch(NoSuchFieldError nosuchfielderror3) { }
            try
            {
                ai[State.Normal.ordinal()] = 3;
            }
            catch(NoSuchFieldError nosuchfielderror2) { }
            try
            {
                ai[State.Pressed.ordinal()] = 4;
            }
            catch(NoSuchFieldError nosuchfielderror1) { }
            try
            {
                ai[State.Reached.ordinal()] = 1;
            }
            catch(NoSuchFieldError nosuchfielderror) { }
            _2D_miui_2D_maml_2D_elements_2D_AdvancedSlider$StateSwitchesValues = ai;
            return ai;
        }

        static miui.maml.util.Utils.Point _2D_wrap0(EndPoint endpoint, float f, float f1)
        {
            return endpoint.getNearestPoint(f, f1);
        }

        private miui.maml.util.Utils.Point getNearestPoint(float f, float f1)
        {
            if(mPath == null)
                return new miui.maml.util.Utils.Point(f - AdvancedSlider._2D_get2(AdvancedSlider.this), f1 - AdvancedSlider._2D_get3(AdvancedSlider.this));
            miui.maml.util.Utils.Point point = null;
            double d = 1.7976931348623157E+308D;
            for(int i = 1; i < mPath.size();)
            {
                float f2 = AdvancedSlider._2D_get2(AdvancedSlider.this);
                float f3 = AdvancedSlider._2D_get3(AdvancedSlider.this);
                Object obj = (Position)mPath.get(i - 1);
                Object obj1 = (Position)mPath.get(i);
                obj = new miui.maml.util.Utils.Point(((Position) (obj)).getX(), ((Position) (obj)).getY());
                miui.maml.util.Utils.Point point1 = new miui.maml.util.Utils.Point(((Position) (obj1)).getX(), ((Position) (obj1)).getY());
                obj1 = new miui.maml.util.Utils.Point(f - f2, f1 - f3);
                obj = Utils.pointProjectionOnSegment(((miui.maml.util.Utils.Point) (obj)), point1, ((miui.maml.util.Utils.Point) (obj1)), true);
                double d1 = Utils.Dist(((miui.maml.util.Utils.Point) (obj)), ((miui.maml.util.Utils.Point) (obj1)), false);
                double d2 = d;
                if(d1 < d)
                {
                    d2 = d1;
                    point = ((miui.maml.util.Utils.Point) (obj));
                }
                i++;
                d = d2;
            }

            return point;
        }

        private void load(Element element)
        {
            loadTask(element);
            loadPath(element);
        }

        private void loadPath(Element element)
        {
            Object obj = Utils.getChild(element, "Path");
            if(obj == null)
            {
                mPath = null;
                return;
            }
            mRawTolerance = getAttrAsInt(((Element) (obj)), "tolerance", 150);
            mPath = new ArrayList();
            element = getVariables();
            mPathX = Expression.build(element, ((Element) (obj)).getAttribute("x"));
            mPathY = Expression.build(element, ((Element) (obj)).getAttribute("y"));
            obj = ((Element) (obj)).getElementsByTagName("Position");
            for(int i = 0; i < ((NodeList) (obj)).getLength(); i++)
            {
                Element element1 = (Element)((NodeList) (obj)).item(i);
                mPath.add(new Position(element, element1, mPathX, mPathY));
            }

        }

        private void loadTask(Element element)
        {
            Element element1;
            Element element2;
            element1 = Utils.getChild(element, "Intent");
            element2 = Utils.getChild(element, "Command");
            element = Utils.getChild(element, "Trigger");
            if(element1 == null && element2 == null && element == null)
                return;
            mAction = new LaunchAction(null);
            if(element1 == null) goto _L2; else goto _L1
_L1:
            mAction.mIntentInfo = new IntentInfo(element1, getVariables());
_L4:
            return;
_L2:
            if(element2 != null)
            {
                mAction.mCommand = ActionCommand.create(element2, mRoot);
                if(mAction.mCommand == null)
                    Log.w("LockScreen_AdvancedSlider", (new StringBuilder()).append("invalid Command element: ").append(element2.toString()).toString());
            } else
            if(element != null)
                mAction.mTrigger = new CommandTrigger(element, mRoot);
            if(true) goto _L4; else goto _L3
_L3:
        }

        public void finish()
        {
            super.finish();
            if(mAction != null)
                mAction.finish();
        }

        public float getTransformedDist(miui.maml.util.Utils.Point point, float f, float f1)
        {
            if(mPath == null)
                return 1.701412E+038F;
            if(point == null)
                return 3.402823E+038F;
            float f2 = AdvancedSlider._2D_get2(AdvancedSlider.this);
            float f3 = AdvancedSlider._2D_get3(AdvancedSlider.this);
            f = (float)Utils.Dist(point, new miui.maml.util.Utils.Point(f - f2, f1 - f3), true);
            if(f < mTolerance)
                return f;
            else
                return 3.402823E+038F;
        }

        public void init()
        {
            super.init();
            if(mAction != null)
                mAction.init();
            mTolerance = scale(mRawTolerance);
        }

        protected void onStateChange(State state, State state1)
        {
            if(state == State.Invalid)
                return;
            _2D_getmiui_2D_maml_2D_elements_2D_AdvancedSlider$StateSwitchesValues()[state1.ordinal()];
            JVM INSTR tableswitch 1 1: default 36
        //                       1 43;
               goto _L1 _L2
_L1:
            super.onStateChange(state, state1);
            return;
_L2:
            mRoot.playSound(mReachedSound);
            if(true) goto _L1; else goto _L3
_L3:
        }

        public void pause()
        {
            super.pause();
            if(mAction != null)
                mAction.pause();
        }

        public void resume()
        {
            super.resume();
            if(mAction != null)
                mAction.resume();
        }

        private static final int _2D_miui_2D_maml_2D_elements_2D_AdvancedSlider$StateSwitchesValues[];
        public static final String TAG_NAME = "EndPoint";
        final int $SWITCH_TABLE$miui$maml$elements$AdvancedSlider$State[];
        public LaunchAction mAction;
        private ArrayList mPath;
        private Expression mPathX;
        private Expression mPathY;
        private int mRawTolerance;
        private float mTolerance;
        final AdvancedSlider this$0;

        public EndPoint(Element element, ScreenElementRoot screenelementroot)
        {
            this$0 = AdvancedSlider.this;
            super(element, screenelementroot, "EndPoint");
            mRawTolerance = 150;
            load(element);
        }
    }

    private class InterpolatorController extends ReboundAnimationController
    {

        protected long getDistance(long l)
        {
            if(l >= mReboundTime)
            {
                onStop();
                return (long)mTotalDistance;
            } else
            {
                float f = (float)l / (float)mReboundTime;
                return (long)(mTotalDistance * (double)mInterpolator.get(f));
            }
        }

        protected void onStart()
        {
            mReboundTime = (long)mReboundTimeExp.evaluate();
        }

        private InterpolatorHelper mInterpolator;
        private long mReboundTime;
        private Expression mReboundTimeExp;
        final AdvancedSlider this$0;

        public InterpolatorController(InterpolatorHelper interpolatorhelper, Expression expression)
        {
            this$0 = AdvancedSlider.this;
            super(null);
            mInterpolator = interpolatorhelper;
            mReboundTimeExp = expression;
        }
    }

    private class LaunchAction
    {

        private Intent performTask()
        {
            if(mIntentInfo == null)
                return null;
            if(!mConfigTaskLoaded)
            {
                Task task = mRoot.findTask(mIntentInfo.getId());
                if(task != null && TextUtils.isEmpty(task.action) ^ true)
                    mIntentInfo.set(task);
                mConfigTaskLoaded = true;
            }
            if(Utils.isProtectedIntent(mIntentInfo.getAction()))
            {
                return null;
            } else
            {
                Intent intent = new Intent();
                mIntentInfo.update(intent);
                intent.setFlags(0x34000000);
                return intent;
            }
        }

        public void finish()
        {
            if(mCommand != null)
                mCommand.finish();
            if(mTrigger != null)
                mTrigger.finish();
            mConfigTaskLoaded = false;
        }

        public void init()
        {
            if(mCommand != null)
                mCommand.init();
            if(mTrigger != null)
                mTrigger.init();
        }

        public void pause()
        {
            if(mCommand != null)
                mCommand.pause();
            if(mTrigger != null)
                mTrigger.pause();
        }

        public Intent perform()
        {
            if(mIntentInfo != null)
                return performTask();
            if(mCommand == null) goto _L2; else goto _L1
_L1:
            mCommand.perform();
_L4:
            return null;
_L2:
            if(mTrigger != null)
                mTrigger.perform();
            if(true) goto _L4; else goto _L3
_L3:
        }

        public void resume()
        {
            if(mCommand != null)
                mCommand.resume();
            if(mTrigger != null)
                mTrigger.resume();
        }

        public ActionCommand mCommand;
        public boolean mConfigTaskLoaded;
        public IntentInfo mIntentInfo;
        public CommandTrigger mTrigger;
        final AdvancedSlider this$0;

        private LaunchAction()
        {
            this$0 = AdvancedSlider.this;
            super();
        }

        LaunchAction(LaunchAction launchaction)
        {
            this();
        }
    }

    public static interface OnLaunchListener
    {

        public abstract boolean onLaunch(String s);
    }

    private class Position
    {

        public float getX()
        {
            double d = 0.0D;
            AdvancedSlider advancedslider = AdvancedSlider.this;
            double d1;
            if(mX == null)
                d1 = 0.0D;
            else
                d1 = mX.evaluate();
            if(mBaseX != null)
                d = mBaseX.evaluate();
            return advancedslider.scale(d1 + d);
        }

        public float getY()
        {
            double d = 0.0D;
            AdvancedSlider advancedslider = AdvancedSlider.this;
            double d1;
            if(mY == null)
                d1 = 0.0D;
            else
                d1 = mY.evaluate();
            if(mBaseY != null)
                d = mBaseY.evaluate();
            return advancedslider.scale(d1 + d);
        }

        public static final String TAG_NAME = "Position";
        private Expression mBaseX;
        private Expression mBaseY;
        private Expression mX;
        private Expression mY;
        final AdvancedSlider this$0;

        public Position(Variables variables, Element element, Expression expression, Expression expression1)
        {
            this$0 = AdvancedSlider.this;
            super();
            mBaseX = expression;
            mBaseY = expression1;
            mX = Expression.build(variables, getAttr(element, "x"));
            mY = Expression.build(variables, getAttr(element, "y"));
        }
    }

    private abstract class ReboundAnimationController
        implements ITicker
    {

        private miui.maml.util.Utils.Point getPoint(float f, float f1, float f2, float f3, long l)
        {
            miui.maml.util.Utils.Point point = new miui.maml.util.Utils.Point(f, f1);
            miui.maml.util.Utils.Point point1 = new miui.maml.util.Utils.Point(f2, f3);
            double d = Utils.Dist(point, point1, true);
            if((double)l >= d)
            {
                return null;
            } else
            {
                double d1 = (d - (double)l) / d;
                d = point1.x;
                double d2 = point.x;
                double d3 = point1.y;
                double d4 = point.y;
                return new miui.maml.util.Utils.Point(point.x + (d - d2) * d1, point.y + (d3 - d4) * d1);
            }
        }

        protected abstract long getDistance(long l);

        public void init()
        {
            mStartTime = -1L;
        }

        public boolean isRunning()
        {
            boolean flag;
            if(mStartTime >= 0L)
                flag = true;
            else
                flag = false;
            return flag;
        }

        protected void onMove(float f, float f1)
        {
            AdvancedSlider._2D_wrap1(AdvancedSlider.this, f, f1);
        }

        protected void onStart()
        {
        }

        protected void onStop()
        {
            mStartTime = -1L;
            AdvancedSlider._2D_wrap0(AdvancedSlider.this);
        }

        public void start(EndPoint endpoint)
        {
            miui.maml.util.Utils.Point point;
            int i;
            mStartTime = 0L;
            mEndPoint = endpoint;
            mStartX = AdvancedSlider._2D_get0(AdvancedSlider.this).getOffsetX() + AdvancedSlider._2D_get0(AdvancedSlider.this).getAnchorX();
            mStartY = AdvancedSlider._2D_get0(AdvancedSlider.this).getOffsetY() + AdvancedSlider._2D_get0(AdvancedSlider.this).getAnchorY();
            mBounceStartPointIndex = -1;
            mTotalDistance = 0.0D;
            point = new miui.maml.util.Utils.Point(mStartX, mStartY);
            if(endpoint == null || EndPoint._2D_get0(endpoint) == null)
                break MISSING_BLOCK_LABEL_261;
            i = 1;
_L5:
            if(i >= EndPoint._2D_get0(endpoint).size()) goto _L2; else goto _L1
_L1:
            Object obj;
            Object obj1;
            miui.maml.util.Utils.Point point1;
            obj = (Position)EndPoint._2D_get0(endpoint).get(i - 1);
            obj1 = (Position)EndPoint._2D_get0(endpoint).get(i);
            obj = new miui.maml.util.Utils.Point(((Position) (obj)).getX(), ((Position) (obj)).getY());
            point1 = new miui.maml.util.Utils.Point(((Position) (obj1)).getX(), ((Position) (obj1)).getY());
            obj1 = Utils.pointProjectionOnSegment(((miui.maml.util.Utils.Point) (obj)), point1, point, false);
            if(obj1 == null) goto _L4; else goto _L3
_L3:
            mBounceStartPointIndex = i - 1;
            mTotalDistance = mTotalDistance + Utils.Dist(((miui.maml.util.Utils.Point) (obj)), ((miui.maml.util.Utils.Point) (obj1)), true);
_L2:
            if(mTotalDistance < 3D)
            {
                onStop();
                return;
            } else
            {
                onStart();
                requestUpdate();
                return;
            }
_L4:
            mTotalDistance = mTotalDistance + Utils.Dist(((miui.maml.util.Utils.Point) (obj)), point1, true);
            i++;
              goto _L5
            mTotalDistance = Utils.Dist(new miui.maml.util.Utils.Point(AdvancedSlider._2D_get0(AdvancedSlider.this).getAnchorX(), AdvancedSlider._2D_get0(AdvancedSlider.this).getAnchorY()), point, true);
              goto _L2
        }

        public void stopRunning()
        {
            mStartTime = -1L;
        }

        public void tick(long l)
        {
            if(mStartTime < 0L)
                return;
            if(mStartTime != 0L) goto _L2; else goto _L1
_L1:
            mStartTime = l;
            mPreDistance = 0L;
_L4:
            requestUpdate();
            return;
_L2:
            long l1;
            l1 = getDistance(l - mStartTime);
            if(mStartTime < 0L)
                return;
            if(mEndPoint != null && EndPoint._2D_get0(mEndPoint) != null)
                break; /* Loop/switch isn't completed */
            miui.maml.util.Utils.Point point = getPoint(AdvancedSlider._2D_get0(AdvancedSlider.this).getAnchorX(), AdvancedSlider._2D_get0(AdvancedSlider.this).getAnchorY(), mStartX, mStartY, l1);
            if(point == null)
                onStop();
            else
                onMove((float)point.x, (float)point.y);
_L5:
            mPreDistance = l1;
            if(true) goto _L4; else goto _L3
_L3:
            float f;
            float f1;
            int i;
            f = AdvancedSlider._2D_get0(AdvancedSlider.this).getOffsetX() + AdvancedSlider._2D_get0(AdvancedSlider.this).getAnchorX();
            f1 = AdvancedSlider._2D_get0(AdvancedSlider.this).getOffsetY() + AdvancedSlider._2D_get0(AdvancedSlider.this).getAnchorY();
            l = l1 - mPreDistance;
            i = mBounceStartPointIndex;
_L6:
            Position position;
            miui.maml.util.Utils.Point point1;
            if(i >= 0)
            {
label0:
                {
                    position = (Position)EndPoint._2D_get0(mEndPoint).get(i);
                    point1 = getPoint(position.getX(), position.getY(), f, f1, l);
                    if(point1 != null)
                        break MISSING_BLOCK_LABEL_334;
                    if(i != 0)
                        break label0;
                    onStop();
                }
            }
              goto _L5
            point1 = new miui.maml.util.Utils.Point(position.getX(), position.getY());
            miui.maml.util.Utils.Point point2 = new miui.maml.util.Utils.Point(f, f1);
            l = (long)((double)l - Utils.Dist(point1, point2, true));
            f = position.getX();
            f1 = position.getY();
            i--;
              goto _L6
            mBounceStartPointIndex = i;
            onMove((float)point1.x, (float)point1.y);
              goto _L5
        }

        private int mBounceStartPointIndex;
        private EndPoint mEndPoint;
        private long mPreDistance;
        protected long mStartTime;
        private float mStartX;
        private float mStartY;
        protected double mTotalDistance;
        final AdvancedSlider this$0;

        private ReboundAnimationController()
        {
            this$0 = AdvancedSlider.this;
            super();
            mStartTime = -1L;
        }

        ReboundAnimationController(ReboundAnimationController reboundanimationcontroller)
        {
            this();
        }
    }

    private class SliderPoint extends ElementGroup
    {

        private static int[] _2D_getmiui_2D_maml_2D_elements_2D_AdvancedSlider$StateSwitchesValues()
        {
            if(_2D_miui_2D_maml_2D_elements_2D_AdvancedSlider$StateSwitchesValues != null)
                return _2D_miui_2D_maml_2D_elements_2D_AdvancedSlider$StateSwitchesValues;
            int ai[] = new int[State.values().length];
            try
            {
                ai[State.Invalid.ordinal()] = 4;
            }
            catch(NoSuchFieldError nosuchfielderror3) { }
            try
            {
                ai[State.Normal.ordinal()] = 1;
            }
            catch(NoSuchFieldError nosuchfielderror2) { }
            try
            {
                ai[State.Pressed.ordinal()] = 2;
            }
            catch(NoSuchFieldError nosuchfielderror1) { }
            try
            {
                ai[State.Reached.ordinal()] = 3;
            }
            catch(NoSuchFieldError nosuchfielderror) { }
            _2D_miui_2D_maml_2D_elements_2D_AdvancedSlider$StateSwitchesValues = ai;
            return ai;
        }

        private void load(Element element, String s)
        {
            mName = getAttr(element, "name");
            mNormalSound = getAttr(element, "normalSound");
            mPressedSound = getAttr(element, "pressedSound");
            mReachedSound = getAttr(element, "reachedSound");
            mNormalStateTrigger = loadTrigger(element, "NormalState");
            mPressedStateTrigger = loadTrigger(element, "PressedState");
            mReachedStateTrigger = loadTrigger(element, "ReachedState");
            if(!TextUtils.isEmpty(mName))
                mPointStateVar = new IndexedVariable((new StringBuilder()).append(mName).append(".").append("state").toString(), getVariables(), true);
            mIsAlignChildren = Boolean.parseBoolean(getAttr(element, "alignChildren"));
        }

        private CommandTrigger loadTrigger(Element element, String s)
        {
            element = Utils.getChild(element, s);
            if(element != null)
                return CommandTrigger.fromParentElement(element, mRoot);
            else
                return null;
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

        public State getState()
        {
            return mState;
        }

        public void init()
        {
            super.init();
            if(mNormalStateElements != null)
                mNormalStateElements.show(true);
            if(mPressedStateElements != null)
                mPressedStateElements.show(false);
            if(mReachedStateElements != null)
                mReachedStateElements.show(false);
            setState(State.Normal);
            if(mNormalStateTrigger != null)
                mNormalStateTrigger.init();
            if(mPressedStateTrigger != null)
                mPressedStateTrigger.init();
            if(mReachedStateTrigger != null)
                mReachedStateTrigger.init();
        }

        protected ScreenElement onCreateChild(Element element)
        {
            String s = element.getTagName();
            if(s.equalsIgnoreCase("NormalState"))
            {
                element = new ElementGroup(element, mRoot);
                mNormalStateElements = element;
                return element;
            }
            if(s.equalsIgnoreCase("PressedState"))
            {
                element = new ElementGroup(element, mRoot);
                mPressedStateElements = element;
                return element;
            }
            if(s.equalsIgnoreCase("ReachedState"))
            {
                element = new ElementGroup(element, mRoot);
                mReachedStateElements = element;
                return element;
            } else
            {
                return super.onCreateChild(element);
            }
        }

        protected void onStateChange(State state, State state1)
        {
            _2D_getmiui_2D_maml_2D_elements_2D_AdvancedSlider$StateSwitchesValues()[state1.ordinal()];
            JVM INSTR tableswitch 1 3: default 36
        //                       1 37
        //                       2 60
        //                       3 138;
               goto _L1 _L2 _L3 _L4
_L1:
            break; /* Loop/switch isn't completed */
_L6:
            return;
_L2:
            if(mNormalStateTrigger != null)
                mNormalStateTrigger.perform();
            performAction("normal");
            break; /* Loop/switch isn't completed */
_L3:
            if(mPressedStateTrigger != null)
                mPressedStateTrigger.perform();
            performAction("pressed");
            switch(_2D_getmiui_2D_maml_2D_elements_2D_AdvancedSlider$StateSwitchesValues()[state.ordinal()])
            {
            case 1: // '\001'
                performAction("pressed_normal");
                break;

            case 3: // '\003'
                performAction("pressed_reached");
                break;
            }
            if(false)
                ;
            continue; /* Loop/switch isn't completed */
_L4:
            if(mReachedStateTrigger != null)
                mReachedStateTrigger.perform();
            performAction("reached");
            if(true) goto _L6; else goto _L5
_L5:
        }

        public void setState(State state)
        {
            State state1;
            Object obj;
            boolean flag;
            int i;
            if(mState == state)
                return;
            state1 = mState;
            mState = state;
            obj = null;
            flag = false;
            i = 0;
            _2D_getmiui_2D_maml_2D_elements_2D_AdvancedSlider$StateSwitchesValues()[state.ordinal()];
            JVM INSTR tableswitch 1 3: default 60
        //                       1 139
        //                       2 163
        //                       3 213;
               goto _L1 _L2 _L3 _L4
_L1:
            state = obj;
_L8:
            if(mCurrentStateElements != state)
            {
                if(mCurrentStateElements != null)
                    mCurrentStateElements.show(false);
                if(state != null)
                    state.show(true);
                mCurrentStateElements = state;
            }
            if(state != null && flag)
                state.reset();
            if(mPointStateVar != null)
                mPointStateVar.set(i);
            onStateChange(state1, mState);
            return;
_L2:
            state = mNormalStateElements;
            if(mPressedStateElements != null)
                flag = true;
            else
                flag = false;
              goto _L5
_L3:
            if(mPressedStateElements != null)
                state = mPressedStateElements;
            else
                state = mNormalStateElements;
            if(mPressedStateElements != null)
                flag = AdvancedSlider._2D_get1(AdvancedSlider.this) ^ true;
            else
                flag = false;
            i = 1;
              goto _L5
_L4:
            if(mReachedStateElements == null) goto _L7; else goto _L6
_L6:
            state = mReachedStateElements;
_L9:
            if(mReachedStateElements != null)
                flag = true;
            else
                flag = false;
            i = 2;
_L5:
            if(true) goto _L8; else goto _L7
_L7:
            if(mPressedStateElements != null)
                state = mPressedStateElements;
            else
                state = mNormalStateElements;
              goto _L9
        }

        private static final int _2D_miui_2D_maml_2D_elements_2D_AdvancedSlider$StateSwitchesValues[];
        final int $SWITCH_TABLE$miui$maml$elements$AdvancedSlider$State[];
        private ScreenElement mCurrentStateElements;
        protected boolean mIsAlignChildren;
        protected String mName;
        protected String mNormalSound;
        protected ElementGroup mNormalStateElements;
        private CommandTrigger mNormalStateTrigger;
        private IndexedVariable mPointStateVar;
        protected String mPressedSound;
        protected ElementGroup mPressedStateElements;
        private CommandTrigger mPressedStateTrigger;
        protected String mReachedSound;
        private ElementGroup mReachedStateElements;
        private CommandTrigger mReachedStateTrigger;
        private State mState;
        final AdvancedSlider this$0;

        public SliderPoint(Element element, ScreenElementRoot screenelementroot, String s)
        {
            this$0 = AdvancedSlider.this;
            super(element, screenelementroot);
            mState = State.Invalid;
            load(element, s);
        }
    }

    private class SpeedAccController extends ReboundAnimationController
        implements ITicker
    {

        protected long getDistance(long l)
        {
            long l1 = ((long)mBounceInitSpeed * l) / 1000L + ((long)mBounceAccelation * l * l) / 0x1e8480L;
            if((long)mBounceInitSpeed + ((long)mBounceAccelation * l) / 1000L <= 0L)
            {
                onStop();
                if(mBounceProgress != null)
                    mBounceProgress.set(1.0D);
            }
            if(mTotalDistance > 0.0D)
            {
                double d = (double)l1 / mTotalDistance;
                if(mBounceProgress != null)
                {
                    IndexedVariable indexedvariable = mBounceProgress;
                    double d1 = d;
                    if(d > 1.0D)
                        d1 = 1.0D;
                    indexedvariable.set(d1);
                }
            }
            return l1;
        }

        public void init()
        {
            super.init();
            if(mBounceProgress != null)
                mBounceProgress.set(1.0D);
        }

        protected void onStart()
        {
            if(mBounceInitSpeedExp != null)
                mBounceInitSpeed = (int)evaluate(mBounceInitSpeedExp);
            if(mBounceAccelationExp != null)
                mBounceAccelation = (int)evaluate(mBounceAccelationExp);
            if(mBounceProgress != null)
                mBounceProgress.set(0.0D);
        }

        public void start(EndPoint endpoint)
        {
            if(mBounceInitSpeedExp == null)
                onStop();
            else
                super.start(endpoint);
        }

        private int mBounceAccelation;
        private Expression mBounceAccelationExp;
        private int mBounceInitSpeed;
        private Expression mBounceInitSpeedExp;
        private IndexedVariable mBounceProgress;
        final AdvancedSlider this$0;

        public SpeedAccController(Element element)
        {
            this$0 = AdvancedSlider.this;
            super(null);
            mBounceInitSpeedExp = Expression.build(getVariables(), getAttr(element, "bounceInitSpeed"));
            mBounceAccelationExp = Expression.build(getVariables(), getAttr(element, "bounceAcceleration"));
            if(mHasName)
                mBounceProgress = new IndexedVariable((new StringBuilder()).append(mName).append(".").append("bounce_progress").toString(), getVariables(), true);
        }
    }

    private class StartPoint extends SliderPoint
    {

        private static int[] _2D_getmiui_2D_maml_2D_elements_2D_AdvancedSlider$StateSwitchesValues()
        {
            if(_2D_miui_2D_maml_2D_elements_2D_AdvancedSlider$StateSwitchesValues != null)
                return _2D_miui_2D_maml_2D_elements_2D_AdvancedSlider$StateSwitchesValues;
            int ai[] = new int[State.values().length];
            try
            {
                ai[State.Invalid.ordinal()] = 3;
            }
            catch(NoSuchFieldError nosuchfielderror3) { }
            try
            {
                ai[State.Normal.ordinal()] = 1;
            }
            catch(NoSuchFieldError nosuchfielderror2) { }
            try
            {
                ai[State.Pressed.ordinal()] = 2;
            }
            catch(NoSuchFieldError nosuchfielderror1) { }
            try
            {
                ai[State.Reached.ordinal()] = 4;
            }
            catch(NoSuchFieldError nosuchfielderror) { }
            _2D_miui_2D_maml_2D_elements_2D_AdvancedSlider$StateSwitchesValues = ai;
            return ai;
        }

        public void doRender(Canvas canvas)
        {
            int i = canvas.save();
            canvas.translate(mOffsetX, mOffsetY);
            super.doRender(canvas);
            canvas.restoreToCount(i);
        }

        protected void doTick(long l)
        {
            super.doTick(l);
            if(mReboundController != null)
                mReboundController.tick(l);
        }

        public float getAnchorX()
        {
            return getLeft() + mAnchorX;
        }

        public float getAnchorY()
        {
            return getTop() + mAnchorY;
        }

        public float getOffsetX()
        {
            return mOffsetX;
        }

        public float getOffsetY()
        {
            return mOffsetY;
        }

        public void init()
        {
            super.init();
            if(mReboundController != null)
                mReboundController.init();
        }

        public void moveTo(float f, float f1)
        {
            mOffsetX = f;
            mOffsetY = f1;
        }

        protected void onStateChange(State state, State state1)
        {
            if(state == State.Invalid)
                return;
            _2D_getmiui_2D_maml_2D_elements_2D_AdvancedSlider$StateSwitchesValues()[state1.ordinal()];
            JVM INSTR tableswitch 1 2: default 40
        //                       1 47
        //                       2 62;
               goto _L1 _L2 _L3
_L1:
            super.onStateChange(state, state1);
            return;
_L2:
            mRoot.playSound(mNormalSound);
            continue; /* Loop/switch isn't completed */
_L3:
            if(!mPressed)
                mRoot.playSound(mPressedSound);
            if(true) goto _L1; else goto _L4
_L4:
        }

        private static final int _2D_miui_2D_maml_2D_elements_2D_AdvancedSlider$StateSwitchesValues[];
        public static final String TAG_NAME = "StartPoint";
        final int $SWITCH_TABLE$miui$maml$elements$AdvancedSlider$State[];
        private float mAnchorX;
        private float mAnchorY;
        protected float mOffsetX;
        protected float mOffsetY;
        public InterpolatorController mReboundController;
        final AdvancedSlider this$0;

        public StartPoint(Element element, ScreenElementRoot screenelementroot)
        {
            this$0 = AdvancedSlider.this;
            super(element, screenelementroot, "StartPoint");
            mAnchorX = Utils.getAttrAsFloat(element, "anchorX", 0.0F);
            mAnchorY = Utils.getAttrAsFloat(element, "anchorY", 0.0F);
            screenelementroot = InterpolatorHelper.create(getVariables(), element);
            element = Expression.build(getVariables(), element.getAttribute("easeTime"));
            if(screenelementroot != null && element != null)
                mReboundController = new InterpolatorController(screenelementroot, element);
        }
    }

    private static final class State extends Enum
    {

        public static State valueOf(String s)
        {
            return (State)Enum.valueOf(miui/maml/elements/AdvancedSlider$State, s);
        }

        public static State[] values()
        {
            return $VALUES;
        }

        private static final State $VALUES[];
        public static final State Invalid;
        public static final State Normal;
        public static final State Pressed;
        public static final State Reached;

        static 
        {
            Normal = new State("Normal", 0);
            Pressed = new State("Pressed", 1);
            Reached = new State("Reached", 2);
            Invalid = new State("Invalid", 3);
            $VALUES = (new State[] {
                Normal, Pressed, Reached, Invalid
            });
        }

        private State(String s, int i)
        {
            super(s, i);
        }
    }


    static StartPoint _2D_get0(AdvancedSlider advancedslider)
    {
        return advancedslider.mStartPoint;
    }

    static boolean _2D_get1(AdvancedSlider advancedslider)
    {
        return advancedslider.mStartPointPressed;
    }

    static float _2D_get2(AdvancedSlider advancedslider)
    {
        return advancedslider.mTouchOffsetX;
    }

    static float _2D_get3(AdvancedSlider advancedslider)
    {
        return advancedslider.mTouchOffsetY;
    }

    static void _2D_wrap0(AdvancedSlider advancedslider)
    {
        advancedslider.cancelMoving();
    }

    static void _2D_wrap1(AdvancedSlider advancedslider, float f, float f1)
    {
        advancedslider.moveStartPoint(f, f1);
    }

    public AdvancedSlider(Element element, ScreenElementRoot screenelementroot)
    {
        super(element, screenelementroot);
        load(element);
    }

    private void cancelMoving()
    {
        resetInner();
        onCancel();
    }

    private boolean checkEndPoint(miui.maml.util.Utils.Point point, EndPoint endpoint)
    {
        boolean flag = false;
        if(endpoint.touched((float)point.x, (float)point.y, false))
        {
            if(endpoint.getState() != State.Reached)
            {
                endpoint.setState(State.Reached);
                point = mEndPoints.iterator();
                do
                {
                    if(!point.hasNext())
                        break;
                    EndPoint endpoint1 = (EndPoint)point.next();
                    if(endpoint1 != endpoint)
                        endpoint1.setState(State.Pressed);
                } while(true);
                onReach(endpoint.mName);
            }
            flag = true;
        } else
        {
            endpoint.setState(State.Pressed);
        }
        return flag;
    }

    private CheckTouchResult checkTouch(float f, float f1)
    {
        float f2;
        Object obj;
        CheckTouchResult checktouchresult;
        boolean flag;
        f2 = 3.402823E+038F;
        obj = null;
        checktouchresult = new CheckTouchResult(null);
        Iterator iterator = mEndPoints.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            EndPoint endpoint = (EndPoint)iterator.next();
            miui.maml.util.Utils.Point point = EndPoint._2D_wrap0(endpoint, f, f1);
            float f3 = endpoint.getTransformedDist(point, f, f1);
            if(f3 < f2)
            {
                f2 = f3;
                obj = point;
                checktouchresult.endPoint = endpoint;
            }
        } while(true);
        flag = false;
        if(f2 >= 3.402823E+038F)
            break MISSING_BLOCK_LABEL_276;
        moveStartPoint((float)((miui.maml.util.Utils.Point) (obj)).x, (float)((miui.maml.util.Utils.Point) (obj)).y);
        if(f2 >= 1.701412E+038F) goto _L2; else goto _L1
_L1:
        boolean flag1 = checkEndPoint(((miui.maml.util.Utils.Point) (obj)), checktouchresult.endPoint);
_L4:
        StartPoint startpoint = mStartPoint;
        if(flag1)
            obj = State.Reached;
        else
            obj = State.Pressed;
        startpoint.setState(((State) (obj)));
        if(mHasName)
        {
            obj = mStateVar;
            Iterator iterator1;
            EndPoint endpoint1;
            int i;
            if(flag1)
                i = 2;
            else
                i = 1;
            ((IndexedVariable) (obj)).set(i);
        }
        checktouchresult.reached = flag1;
        return checktouchresult;
_L2:
        iterator1 = mEndPoints.iterator();
_L6:
        flag1 = flag;
        if(!iterator1.hasNext()) goto _L4; else goto _L3
_L3:
        endpoint1 = (EndPoint)iterator1.next();
        if(EndPoint._2D_get0(endpoint1) != null) goto _L6; else goto _L5
_L5:
        flag1 = checkEndPoint(((miui.maml.util.Utils.Point) (obj)), endpoint1);
        flag = flag1;
        if(!flag1) goto _L6; else goto _L7
_L7:
        checktouchresult.endPoint = endpoint1;
          goto _L4
        Log.i("LockScreen_AdvancedSlider", "unlock touch canceled due to exceeding tollerance");
        mStartPoint.performAction("cancel");
        return null;
    }

    private boolean doLaunch(EndPoint endpoint)
    {
        mStartPoint.performAction("launch");
        endpoint.performAction("launch");
        Intent intent = null;
        if(endpoint.mAction != null)
            intent = endpoint.mAction.perform();
        return onLaunch(endpoint.mName, intent);
    }

    private void load(Element element)
    {
        if(element == null)
            return;
        if(mHasName)
        {
            mStateVar = new IndexedVariable((new StringBuilder()).append(mName).append(".").append("state").toString(), getVariables(), true);
            mMoveXVar = new IndexedVariable((new StringBuilder()).append(mName).append(".").append("move_x").toString(), getVariables(), true);
            mMoveYVar = new IndexedVariable((new StringBuilder()).append(mName).append(".").append("move_y").toString(), getVariables(), true);
            mMoveDistVar = new IndexedVariable((new StringBuilder()).append(mName).append(".").append("move_dist").toString(), getVariables(), true);
        }
        if(mStartPoint != null && mStartPoint.mReboundController != null)
        {
            mReboundAnimationController = mStartPoint.mReboundController;
        } else
        {
            mReboundAnimationController = new SpeedAccController(element);
            mRoot.addPreTicker(mReboundAnimationController);
        }
        mIsHaptic = Boolean.parseBoolean(getAttr(element, "haptic"));
        mIsKeepStatusAfterLaunch = Boolean.parseBoolean(getAttr(element, "keepStatusAfterLaunch"));
    }

    private void moveStartPoint(float f, float f1)
    {
        f -= mStartPoint.getAnchorX();
        f1 -= mStartPoint.getAnchorY();
        mStartPoint.moveTo(f, f1);
        if(mHasName)
        {
            double d = descale(f);
            double d1 = descale(f1);
            double d2 = Math.sqrt(d * d + d1 * d1);
            mMoveXVar.set(d);
            mMoveYVar.set(d1);
            mMoveDistVar.set(d2);
        }
    }

    public void finish()
    {
        super.finish();
        resetInner();
    }

    public void init()
    {
        super.init();
        mReboundAnimationController.init();
        resetInner();
    }

    protected void onCancel()
    {
    }

    protected ScreenElement onCreateChild(Element element)
    {
        String s = element.getTagName();
        if(s.equalsIgnoreCase("StartPoint"))
        {
            element = new StartPoint(element, mRoot);
            mStartPoint = element;
            return element;
        }
        if(s.equalsIgnoreCase("EndPoint"))
        {
            element = new EndPoint(element, mRoot);
            if(mEndPoints == null)
                mEndPoints = new ArrayList();
            mEndPoints.add(element);
            return element;
        } else
        {
            return super.onCreateChild(element);
        }
    }

    protected boolean onLaunch(String s, Intent intent)
    {
        if(mOnLaunchListener != null)
            return mOnLaunchListener.onLaunch(s);
        else
            return mIsKeepStatusAfterLaunch;
    }

    protected void onMove(float f, float f1)
    {
    }

    protected void onReach(String s)
    {
        if(mIsHaptic)
            mRoot.haptic(0);
    }

    protected void onRelease()
    {
        if(mIsHaptic)
            mRoot.haptic(1);
    }

    protected void onStart()
    {
        if(mIsHaptic)
            mRoot.haptic(1);
    }

    public boolean onTouch(MotionEvent motionevent)
    {
        boolean flag;
        float f;
        float f1;
        boolean flag1;
        flag = true;
        if(!isVisible())
            return false;
        f = motionevent.getX();
        f1 = motionevent.getY();
        flag1 = false;
        f -= getAbsoluteLeft();
        f1 -= getAbsoluteTop();
        motionevent.getActionMasked();
        JVM INSTR tableswitch 0 3: default 76
    //                   0 101
    //                   1 349
    //                   2 283
    //                   3 484;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        break; /* Loop/switch isn't completed */
_L5:
        break MISSING_BLOCK_LABEL_484;
_L6:
        boolean flag2 = flag;
        Object obj;
        boolean flag3;
        if(!super.onTouch(motionevent))
            if(flag1)
                flag2 = mInterceptTouch;
            else
                flag2 = false;
        return flag2;
_L2:
        if(mStartPoint.touched(f, f1, false))
        {
            mMoving = true;
            mTouchOffsetX = f - mStartPoint.getAnchorX();
            mTouchOffsetY = f1 - mStartPoint.getAnchorY();
            if(mReboundAnimationController.isRunning())
            {
                mReboundAnimationController.stopRunning();
                mTouchOffsetX = mTouchOffsetX - mStartPoint.getOffsetX();
                mTouchOffsetY = mTouchOffsetY - mStartPoint.getOffsetY();
            }
            mStartPoint.setState(State.Pressed);
            for(obj = mEndPoints.iterator(); ((Iterator) (obj)).hasNext(); ((EndPoint)((Iterator) (obj)).next()).setState(State.Pressed));
            mStartPointPressed = true;
            if(mHasName)
                mStateVar.set(1.0D);
            mReboundAnimationController.init();
            onStart();
            flag1 = true;
        }
          goto _L6
_L4:
        if(mMoving)
        {
            obj = checkTouch(f, f1);
            if(obj != null)
            {
                mCurrentEndPoint = ((CheckTouchResult) (obj)).endPoint;
                onMove(f, f1);
            } else
            {
                mReboundAnimationController.start(mCurrentEndPoint);
                mMoving = false;
                onRelease();
            }
            flag1 = true;
        }
          goto _L6
_L3:
        flag2 = false;
        flag3 = false;
        if(mMoving)
        {
            Log.i("LockScreen_AdvancedSlider", "unlock touch up");
            obj = checkTouch(f, f1);
            if(obj != null)
            {
                if(((CheckTouchResult) (obj)).reached)
                {
                    flag2 = doLaunch(((CheckTouchResult) (obj)).endPoint);
                } else
                {
                    mStartPoint.performAction("release");
                    flag2 = flag3;
                    if(((CheckTouchResult) (obj)).endPoint != null)
                    {
                        ((CheckTouchResult) (obj)).endPoint.performAction("release");
                        flag2 = flag3;
                    }
                }
                mCurrentEndPoint = ((CheckTouchResult) (obj)).endPoint;
            }
            mMoving = false;
            if(!flag2)
                mReboundAnimationController.start(mCurrentEndPoint);
            onRelease();
            flag1 = true;
        }
          goto _L6
        if(mMoving)
        {
            mReboundAnimationController.start(null);
            mCurrentEndPoint = null;
            mMoving = false;
            onRelease();
            mStartPoint.performAction("cancel");
            flag1 = true;
        }
          goto _L6
    }

    public void pause()
    {
        super.pause();
        resetInner();
    }

    public void reset(long l)
    {
        super.reset(l);
        resetInner();
    }

    protected void resetInner()
    {
        if(mStartPointPressed)
        {
            mStartPointPressed = false;
            mStartPoint.moveTo(0.0F, 0.0F);
            mStartPoint.setState(State.Normal);
            for(Iterator iterator = mEndPoints.iterator(); iterator.hasNext(); ((EndPoint)iterator.next()).setState(State.Normal));
            if(mHasName)
            {
                mMoveXVar.set(0.0D);
                mMoveYVar.set(0.0D);
                mMoveDistVar.set(0.0D);
                mStateVar.set(0.0D);
            }
            mMoving = false;
            requestUpdate();
        }
    }

    public void setOnLaunchListener(OnLaunchListener onlaunchlistener)
    {
        mOnLaunchListener = onlaunchlistener;
    }

    private static final boolean DEBUG = false;
    private static final int DEFAULT_DRAG_TOLERANCE = 150;
    private static final float FREE_ENDPOINT_DIST = 1.701412E+038F;
    private static final String LOG_TAG = "LockScreen_AdvancedSlider";
    public static final String MOVE_DIST = "move_dist";
    public static final String MOVE_X = "move_x";
    public static final String MOVE_Y = "move_y";
    private static final float NONE_ENDPOINT_DIST = 3.402823E+038F;
    public static final int SLIDER_STATE_NORMAL = 0;
    public static final int SLIDER_STATE_PRESSED = 1;
    public static final int SLIDER_STATE_REACHED = 2;
    public static final String STATE = "state";
    public static final String TAG_NAME = "Slider";
    private EndPoint mCurrentEndPoint;
    private ArrayList mEndPoints;
    protected boolean mIsHaptic;
    private boolean mIsKeepStatusAfterLaunch;
    private IndexedVariable mMoveDistVar;
    private IndexedVariable mMoveXVar;
    private IndexedVariable mMoveYVar;
    private boolean mMoving;
    private OnLaunchListener mOnLaunchListener;
    private ReboundAnimationController mReboundAnimationController;
    private StartPoint mStartPoint;
    private boolean mStartPointPressed;
    private IndexedVariable mStateVar;
    private float mTouchOffsetX;
    private float mTouchOffsetY;
}
