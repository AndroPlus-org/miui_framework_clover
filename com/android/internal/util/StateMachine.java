// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.util;

import android.os.*;
import android.text.TextUtils;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Stream;

// Referenced classes of package com.android.internal.util:
//            IState, State

public class StateMachine
{
    public static class LogRec
    {

        public IState getDestState()
        {
            return mDstState;
        }

        public String getInfo()
        {
            return mInfo;
        }

        public IState getOriginalState()
        {
            return mOrgState;
        }

        public IState getState()
        {
            return mState;
        }

        public long getTime()
        {
            return mTime;
        }

        public long getWhat()
        {
            return (long)mWhat;
        }

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("time=");
            Object obj = Calendar.getInstance();
            ((Calendar) (obj)).setTimeInMillis(mTime);
            stringbuilder.append(String.format("%tm-%td %tH:%tM:%tS.%tL", new Object[] {
                obj, obj, obj, obj, obj, obj
            }));
            stringbuilder.append(" processed=");
            if(mState == null)
                obj = "<null>";
            else
                obj = mState.getName();
            stringbuilder.append(((String) (obj)));
            stringbuilder.append(" org=");
            if(mOrgState == null)
                obj = "<null>";
            else
                obj = mOrgState.getName();
            stringbuilder.append(((String) (obj)));
            stringbuilder.append(" dest=");
            if(mDstState == null)
                obj = "<null>";
            else
                obj = mDstState.getName();
            stringbuilder.append(((String) (obj)));
            stringbuilder.append(" what=");
            if(mSm != null)
                obj = mSm.getWhatToString(mWhat);
            else
                obj = "";
            if(TextUtils.isEmpty(((CharSequence) (obj))))
            {
                stringbuilder.append(mWhat);
                stringbuilder.append("(0x");
                stringbuilder.append(Integer.toHexString(mWhat));
                stringbuilder.append(")");
            } else
            {
                stringbuilder.append(((String) (obj)));
            }
            if(!TextUtils.isEmpty(mInfo))
            {
                stringbuilder.append(" ");
                stringbuilder.append(mInfo);
            }
            return stringbuilder.toString();
        }

        public void update(StateMachine statemachine, Message message, String s, IState istate, IState istate1, IState istate2)
        {
            mSm = statemachine;
            mTime = System.currentTimeMillis();
            int i;
            if(message != null)
                i = message.what;
            else
                i = 0;
            mWhat = i;
            mInfo = s;
            mState = istate;
            mOrgState = istate1;
            mDstState = istate2;
        }

        private IState mDstState;
        private String mInfo;
        private IState mOrgState;
        private StateMachine mSm;
        private IState mState;
        private long mTime;
        private int mWhat;

        LogRec(StateMachine statemachine, Message message, String s, IState istate, IState istate1, IState istate2)
        {
            update(statemachine, message, s, istate, istate1, istate2);
        }
    }

    private static class LogRecords
    {

        static Vector _2D_get0(LogRecords logrecords)
        {
            return logrecords.mLogRecVector;
        }

        static int _2D_get1(LogRecords logrecords)
        {
            return logrecords.mMaxSize;
        }

        void add(StateMachine statemachine, Message message, String s, IState istate, IState istate1, IState istate2)
        {
            this;
            JVM INSTR monitorenter ;
            mCount = mCount + 1;
            if(mLogRecVector.size() >= mMaxSize)
                break MISSING_BLOCK_LABEL_62;
            Vector vector = mLogRecVector;
            LogRec logrec = JVM INSTR new #50  <Class StateMachine$LogRec>;
            logrec.LogRec(statemachine, message, s, istate, istate1, istate2);
            vector.add(logrec);
_L1:
            this;
            JVM INSTR monitorexit ;
            return;
            LogRec logrec1 = (LogRec)mLogRecVector.get(mOldestIndex);
            mOldestIndex = mOldestIndex + 1;
            if(mOldestIndex >= mMaxSize)
                mOldestIndex = 0;
            logrec1.update(statemachine, message, s, istate, istate1, istate2);
              goto _L1
            statemachine;
            throw statemachine;
        }

        void cleanup()
        {
            this;
            JVM INSTR monitorenter ;
            mLogRecVector.clear();
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        int count()
        {
            this;
            JVM INSTR monitorenter ;
            int i = mCount;
            this;
            JVM INSTR monitorexit ;
            return i;
            Exception exception;
            exception;
            throw exception;
        }

        LogRec get(int i)
        {
            this;
            JVM INSTR monitorenter ;
            int j = mOldestIndex + i;
            i = j;
            if(j >= mMaxSize)
                i = j - mMaxSize;
            j = size();
            if(i < j)
                break MISSING_BLOCK_LABEL_40;
            this;
            JVM INSTR monitorexit ;
            return null;
            LogRec logrec = (LogRec)mLogRecVector.get(i);
            this;
            JVM INSTR monitorexit ;
            return logrec;
            Exception exception;
            exception;
            throw exception;
        }

        boolean logOnlyTransitions()
        {
            this;
            JVM INSTR monitorenter ;
            boolean flag = mLogOnlyTransitions;
            this;
            JVM INSTR monitorexit ;
            return flag;
            Exception exception;
            exception;
            throw exception;
        }

        void setLogOnlyTransitions(boolean flag)
        {
            this;
            JVM INSTR monitorenter ;
            mLogOnlyTransitions = flag;
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        void setSize(int i)
        {
            this;
            JVM INSTR monitorenter ;
            mMaxSize = i;
            mOldestIndex = 0;
            mCount = 0;
            mLogRecVector.clear();
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        int size()
        {
            this;
            JVM INSTR monitorenter ;
            int i = mLogRecVector.size();
            this;
            JVM INSTR monitorexit ;
            return i;
            Exception exception;
            exception;
            throw exception;
        }

        private static final int DEFAULT_SIZE = 20;
        private int mCount;
        private boolean mLogOnlyTransitions;
        private Vector mLogRecVector;
        private int mMaxSize;
        private int mOldestIndex;

        private LogRecords()
        {
            mLogRecVector = new Vector();
            mMaxSize = 20;
            mOldestIndex = 0;
            mCount = 0;
            mLogOnlyTransitions = false;
        }

        LogRecords(LogRecords logrecords)
        {
            this();
        }
    }

    private static class SmHandler extends Handler
    {

        static boolean _2D_get0(SmHandler smhandler)
        {
            return smhandler.mDbg;
        }

        static ArrayList _2D_get1(SmHandler smhandler)
        {
            return smhandler.mDeferredMessages;
        }

        static State _2D_get2(SmHandler smhandler)
        {
            return smhandler.mDestState;
        }

        static HaltingState _2D_get3(SmHandler smhandler)
        {
            return smhandler.mHaltingState;
        }

        static LogRecords _2D_get4(SmHandler smhandler)
        {
            return smhandler.mLogRecords;
        }

        static StateMachine _2D_get5(SmHandler smhandler)
        {
            return smhandler.mSm;
        }

        static StateInfo[] _2D_get6(SmHandler smhandler)
        {
            return smhandler.mStateStack;
        }

        static int _2D_get7(SmHandler smhandler)
        {
            return smhandler.mStateStackTopIndex;
        }

        static Message _2D_wrap0(SmHandler smhandler)
        {
            return smhandler.getCurrentMessage();
        }

        static boolean _2D_wrap1(SmHandler smhandler)
        {
            return smhandler.isDbg();
        }

        static void _2D_wrap10(SmHandler smhandler, boolean flag)
        {
            smhandler.setDbg(flag);
        }

        static void _2D_wrap11(SmHandler smhandler, State state)
        {
            smhandler.setInitialState(state);
        }

        static void _2D_wrap12(SmHandler smhandler, IState istate)
        {
            smhandler.transitionTo(istate);
        }

        static boolean _2D_wrap2(SmHandler smhandler, Message message)
        {
            return smhandler.isQuit(message);
        }

        static IState _2D_wrap3(SmHandler smhandler)
        {
            return smhandler.getCurrentState();
        }

        static StateInfo _2D_wrap4(SmHandler smhandler, State state, State state1)
        {
            return smhandler.addState(state, state1);
        }

        static void _2D_wrap5(SmHandler smhandler)
        {
            smhandler.completeConstruction();
        }

        static void _2D_wrap6(SmHandler smhandler, Message message)
        {
            smhandler.deferMessage(message);
        }

        static void _2D_wrap7(SmHandler smhandler)
        {
            smhandler.quitNow();
        }

        static void _2D_wrap8(SmHandler smhandler)
        {
            smhandler.quit();
        }

        static void _2D_wrap9(SmHandler smhandler, State state)
        {
            smhandler.removeState(state);
        }

        private final StateInfo addState(State state, State state1)
        {
            Object obj1;
            if(mDbg)
            {
                Object obj = mSm;
                StringBuilder stringbuilder = (new StringBuilder()).append("addStateInternal: E state=").append(state.getName()).append(",parent=");
                if(state1 == null)
                    obj1 = "";
                else
                    obj1 = state1.getName();
                ((StateMachine) (obj)).log(stringbuilder.append(((String) (obj1))).toString());
            }
            obj1 = null;
            if(state1 != null)
            {
                obj = (StateInfo)mStateInfo.get(state1);
                obj1 = obj;
                if(obj == null)
                    obj1 = addState(state1, null);
            }
            obj = (StateInfo)mStateInfo.get(state);
            state1 = ((State) (obj));
            if(obj == null)
            {
                state1 = new StateInfo(null);
                mStateInfo.put(state, state1);
            }
            if(((StateInfo) (state1)).parentStateInfo != null && ((StateInfo) (state1)).parentStateInfo != obj1)
                throw new RuntimeException("state already added");
            state1.state = state;
            state1.parentStateInfo = ((StateInfo) (obj1));
            state1.active = false;
            if(mDbg)
                mSm.log((new StringBuilder()).append("addStateInternal: X stateInfo: ").append(state1).toString());
            return state1;
        }

        private final void cleanupAfterQuitting()
        {
            if(StateMachine._2D_get1(mSm) != null)
            {
                getLooper().quit();
                StateMachine._2D_set1(mSm, null);
            }
            StateMachine._2D_set0(mSm, null);
            mSm = null;
            mMsg = null;
            mLogRecords.cleanup();
            mStateStack = null;
            mTempStateStack = null;
            mStateInfo.clear();
            mInitialState = null;
            mDestState = null;
            mDeferredMessages.clear();
            mHasQuit = true;
        }

        private final void completeConstruction()
        {
            if(mDbg)
                mSm.log("completeConstruction: E");
            int i = 0;
            Iterator iterator = mStateInfo.values().iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                StateInfo stateinfo = (StateInfo)iterator.next();
                int j;
                for(j = 0; stateinfo != null; j++)
                    stateinfo = stateinfo.parentStateInfo;

                if(i < j)
                    i = j;
            } while(true);
            if(mDbg)
                mSm.log((new StringBuilder()).append("completeConstruction: maxDepth=").append(i).toString());
            mStateStack = new StateInfo[i];
            mTempStateStack = new StateInfo[i];
            setupInitialStateStack();
            sendMessageAtFrontOfQueue(obtainMessage(-2, mSmHandlerObj));
            if(mDbg)
                mSm.log("completeConstruction: X");
        }

        private final void deferMessage(Message message)
        {
            if(mDbg)
                mSm.log((new StringBuilder()).append("deferMessage: msg=").append(message.what).toString());
            Message message1 = obtainMessage();
            message1.copyFrom(message);
            mDeferredMessages.add(message1);
        }

        private final Message getCurrentMessage()
        {
            return mMsg;
        }

        private final IState getCurrentState()
        {
            return mStateStack[mStateStackTopIndex].state;
        }

        private final void invokeEnterMethods(int i)
        {
            for(int j = i; j <= mStateStackTopIndex; j++)
            {
                if(i == mStateStackTopIndex)
                    mTransitionInProgress = false;
                if(mDbg)
                    mSm.log((new StringBuilder()).append("invokeEnterMethods: ").append(mStateStack[j].state.getName()).toString());
                mStateStack[j].state.enter();
                mStateStack[j].active = true;
            }

            mTransitionInProgress = false;
        }

        private final void invokeExitMethods(StateInfo stateinfo)
        {
            for(; mStateStackTopIndex >= 0 && mStateStack[mStateStackTopIndex] != stateinfo; mStateStackTopIndex = mStateStackTopIndex - 1)
            {
                State state = mStateStack[mStateStackTopIndex].state;
                if(mDbg)
                    mSm.log((new StringBuilder()).append("invokeExitMethods: ").append(state.getName()).toString());
                state.exit();
                mStateStack[mStateStackTopIndex].active = false;
            }

        }

        private final boolean isDbg()
        {
            return mDbg;
        }

        private final boolean isQuit(Message message)
        {
            boolean flag = false;
            boolean flag1 = flag;
            if(message.what == -1)
            {
                flag1 = flag;
                if(message.obj == mSmHandlerObj)
                    flag1 = true;
            }
            return flag1;
        }

        static boolean lambda$_2D_com_android_internal_util_StateMachine$SmHandler_43582(StateInfo stateinfo, StateInfo stateinfo1)
        {
            boolean flag;
            if(stateinfo1.parentStateInfo == stateinfo)
                flag = true;
            else
                flag = false;
            return flag;
        }

        private final void moveDeferredMessageAtFrontOfQueue()
        {
            for(int i = mDeferredMessages.size() - 1; i >= 0; i--)
            {
                Message message = (Message)mDeferredMessages.get(i);
                if(mDbg)
                    mSm.log((new StringBuilder()).append("moveDeferredMessageAtFrontOfQueue; what=").append(message.what).toString());
                sendMessageAtFrontOfQueue(message);
            }

            mDeferredMessages.clear();
        }

        private final int moveTempStateStackToStateStack()
        {
            int i = mStateStackTopIndex + 1;
            int j = mTempStateStackCount - 1;
            int k = i;
            for(; j >= 0; j--)
            {
                if(mDbg)
                    mSm.log((new StringBuilder()).append("moveTempStackToStateStack: i=").append(j).append(",j=").append(k).toString());
                mStateStack[k] = mTempStateStack[j];
                k++;
            }

            mStateStackTopIndex = k - 1;
            if(mDbg)
                mSm.log((new StringBuilder()).append("moveTempStackToStateStack: X mStateStackTop=").append(mStateStackTopIndex).append(",startingIndex=").append(i).append(",Top=").append(mStateStack[mStateStackTopIndex].state.getName()).toString());
            return i;
        }

        private void performTransitions(State state, Message message)
        {
            State state1 = mStateStack[mStateStackTopIndex].state;
            boolean flag;
            if(mSm.recordLogRec(mMsg) && message.obj != mSmHandlerObj)
                flag = true;
            else
                flag = false;
            if(mLogRecords.logOnlyTransitions())
            {
                if(mDestState != null)
                    mLogRecords.add(mSm, mMsg, mSm.getLogRecString(mMsg), state, state1, mDestState);
            } else
            if(flag)
                mLogRecords.add(mSm, mMsg, mSm.getLogRecString(mMsg), state, state1, mDestState);
            state = mDestState;
            message = state;
            if(state != null)
            {
                do
                {
                    if(mDbg)
                        mSm.log("handleMessage: new destination call exit/enter");
                    message = setupTempStateStackWithStatesToEnter(state);
                    mTransitionInProgress = true;
                    invokeExitMethods(message);
                    invokeEnterMethods(moveTempStateStackToStateStack());
                    moveDeferredMessageAtFrontOfQueue();
                    if(state == mDestState)
                        break;
                    state = mDestState;
                } while(true);
                mDestState = null;
                message = state;
            }
            if(message == null) goto _L2; else goto _L1
_L1:
            if(message != mQuittingState) goto _L4; else goto _L3
_L3:
            mSm.onQuitting();
            cleanupAfterQuitting();
_L2:
            return;
_L4:
            if(message == mHaltingState)
                mSm.onHalting();
            if(true) goto _L2; else goto _L5
_L5:
        }

        private final State processMsg(Message message)
        {
            Object obj;
            StateInfo stateinfo;
            StateInfo stateinfo1;
            obj = null;
            stateinfo = mStateStack[mStateStackTopIndex];
            if(mDbg)
                mSm.log((new StringBuilder()).append("processMsg: ").append(stateinfo.state.getName()).toString());
            stateinfo1 = stateinfo;
            if(!isQuit(message)) goto _L2; else goto _L1
_L1:
            transitionTo(mQuittingState);
_L6:
            message = obj;
            if(stateinfo != null)
                message = stateinfo.state;
            return message;
_L4:
            stateinfo1 = stateinfo;
            if(mDbg)
            {
                mSm.log((new StringBuilder()).append("processMsg: ").append(stateinfo.state.getName()).toString());
                stateinfo1 = stateinfo;
            }
_L2:
            stateinfo = stateinfo1;
            if(stateinfo1.state.processMessage(message))
                continue; /* Loop/switch isn't completed */
            stateinfo = stateinfo1.parentStateInfo;
            if(stateinfo != null) goto _L4; else goto _L3
_L3:
            mSm.unhandledMessage(message);
            if(true) goto _L6; else goto _L5
_L5:
        }

        private final void quit()
        {
            if(mDbg)
                mSm.log("quit:");
            sendMessage(obtainMessage(-1, mSmHandlerObj));
        }

        private final void quitNow()
        {
            if(mDbg)
                mSm.log("quitNow:");
            sendMessageAtFrontOfQueue(obtainMessage(-1, mSmHandlerObj));
        }

        private void removeState(State state)
        {
            StateInfo stateinfo = (StateInfo)mStateInfo.get(state);
            if(stateinfo == null || stateinfo.active)
                return;
            if(mStateInfo.values().stream().filter(new _.Lambda.yemvYsjJALMa5EcxPVEO8dzsQUY(stateinfo)).findAny().isPresent())
            {
                return;
            } else
            {
                mStateInfo.remove(state);
                return;
            }
        }

        private final void setDbg(boolean flag)
        {
            mDbg = flag;
        }

        private final void setInitialState(State state)
        {
            if(mDbg)
                mSm.log((new StringBuilder()).append("setInitialState: initialState=").append(state.getName()).toString());
            mInitialState = state;
        }

        private final void setupInitialStateStack()
        {
            if(mDbg)
                mSm.log((new StringBuilder()).append("setupInitialStateStack: E mInitialState=").append(mInitialState.getName()).toString());
            StateInfo stateinfo = (StateInfo)mStateInfo.get(mInitialState);
            for(mTempStateStackCount = 0; stateinfo != null; mTempStateStackCount = mTempStateStackCount + 1)
            {
                mTempStateStack[mTempStateStackCount] = stateinfo;
                stateinfo = stateinfo.parentStateInfo;
            }

            mStateStackTopIndex = -1;
            moveTempStateStackToStateStack();
        }

        private final StateInfo setupTempStateStackWithStatesToEnter(State state)
        {
            mTempStateStackCount = 0;
            state = (StateInfo)mStateInfo.get(state);
            StateInfo stateinfo;
            do
            {
                StateInfo astateinfo[] = mTempStateStack;
                int i = mTempStateStackCount;
                mTempStateStackCount = i + 1;
                astateinfo[i] = state;
                stateinfo = ((StateInfo) (state)).parentStateInfo;
                if(stateinfo == null)
                    break;
                state = stateinfo;
            } while(stateinfo.active ^ true);
            if(mDbg)
                mSm.log((new StringBuilder()).append("setupTempStateStackWithStatesToEnter: X mTempStateStackCount=").append(mTempStateStackCount).append(",curStateInfo: ").append(stateinfo).toString());
            return stateinfo;
        }

        private final void transitionTo(IState istate)
        {
            if(mTransitionInProgress)
                Log.wtf(StateMachine._2D_get0(mSm), (new StringBuilder()).append("transitionTo called while transition already in progress to ").append(mDestState).append(", new target state=").append(istate).toString());
            mDestState = (State)istate;
            if(mDbg)
                mSm.log((new StringBuilder()).append("transitionTo: destState=").append(mDestState.getName()).toString());
        }

        public final void handleMessage(Message message)
        {
            if(!mHasQuit)
            {
                if(mSm != null && message.what != -2 && message.what != -1)
                    mSm.onPreHandleMessage(message);
                if(mDbg)
                    mSm.log((new StringBuilder()).append("handleMessage: E msg.what=").append(message.what).toString());
                mMsg = message;
                State state = null;
                if(mIsConstructionCompleted)
                    state = processMsg(message);
                else
                if(!mIsConstructionCompleted && mMsg.what == -2 && mMsg.obj == mSmHandlerObj)
                {
                    mIsConstructionCompleted = true;
                    invokeEnterMethods(0);
                } else
                {
                    throw new RuntimeException((new StringBuilder()).append("StateMachine.handleMessage: The start method not called, received msg: ").append(message).toString());
                }
                performTransitions(state, message);
                if(mDbg && mSm != null)
                    mSm.log("handleMessage: X");
                if(mSm != null && message.what != -2 && message.what != -1)
                    mSm.onPostHandleMessage(message);
            }
        }

        private static final Object mSmHandlerObj = new Object();
        private boolean mDbg;
        private ArrayList mDeferredMessages;
        private State mDestState;
        private HaltingState mHaltingState;
        private boolean mHasQuit;
        private State mInitialState;
        private boolean mIsConstructionCompleted;
        private LogRecords mLogRecords;
        private Message mMsg;
        private QuittingState mQuittingState;
        private StateMachine mSm;
        private HashMap mStateInfo;
        private StateInfo mStateStack[];
        private int mStateStackTopIndex;
        private StateInfo mTempStateStack[];
        private int mTempStateStackCount;
        private boolean mTransitionInProgress;


        private SmHandler(Looper looper, StateMachine statemachine)
        {
            super(looper);
            mHasQuit = false;
            mDbg = false;
            mLogRecords = new LogRecords(null);
            mStateStackTopIndex = -1;
            mHaltingState = new HaltingState(null);
            mQuittingState = new QuittingState(null);
            mStateInfo = new HashMap();
            mTransitionInProgress = false;
            mDeferredMessages = new ArrayList();
            mSm = statemachine;
            addState(mHaltingState, null);
            addState(mQuittingState, null);
        }

        SmHandler(Looper looper, StateMachine statemachine, SmHandler smhandler)
        {
            this(looper, statemachine);
        }
    }

    private class SmHandler.HaltingState extends State
    {

        public boolean processMessage(Message message)
        {
            SmHandler._2D_get5(SmHandler.this).haltedProcessMessage(message);
            return true;
        }

        final SmHandler this$1;

        private SmHandler.HaltingState()
        {
            this$1 = SmHandler.this;
            super();
        }

        SmHandler.HaltingState(SmHandler.HaltingState haltingstate)
        {
            this();
        }
    }

    private class SmHandler.QuittingState extends State
    {

        public boolean processMessage(Message message)
        {
            return false;
        }

        final SmHandler this$1;

        private SmHandler.QuittingState()
        {
            this$1 = SmHandler.this;
            super();
        }

        SmHandler.QuittingState(SmHandler.QuittingState quittingstate)
        {
            this();
        }
    }

    private class SmHandler.StateInfo
    {

        public String toString()
        {
            StringBuilder stringbuilder = (new StringBuilder()).append("state=").append(state.getName()).append(",active=").append(active).append(",parent=");
            String s;
            if(parentStateInfo == null)
                s = "null";
            else
                s = parentStateInfo.state.getName();
            return stringbuilder.append(s).toString();
        }

        boolean active;
        SmHandler.StateInfo parentStateInfo;
        State state;
        final SmHandler this$1;

        private SmHandler.StateInfo()
        {
            this$1 = SmHandler.this;
            super();
        }

        SmHandler.StateInfo(SmHandler.StateInfo stateinfo)
        {
            this();
        }
    }


    static String _2D_get0(StateMachine statemachine)
    {
        return statemachine.mName;
    }

    static HandlerThread _2D_get1(StateMachine statemachine)
    {
        return statemachine.mSmThread;
    }

    static SmHandler _2D_set0(StateMachine statemachine, SmHandler smhandler)
    {
        statemachine.mSmHandler = smhandler;
        return smhandler;
    }

    static HandlerThread _2D_set1(StateMachine statemachine, HandlerThread handlerthread)
    {
        statemachine.mSmThread = handlerthread;
        return handlerthread;
    }

    protected StateMachine(String s)
    {
        mSmThread = new HandlerThread(s);
        mSmThread.start();
        initStateMachine(s, mSmThread.getLooper());
    }

    protected StateMachine(String s, Handler handler)
    {
        initStateMachine(s, handler.getLooper());
    }

    protected StateMachine(String s, Looper looper)
    {
        initStateMachine(s, looper);
    }

    private void initStateMachine(String s, Looper looper)
    {
        mName = s;
        mSmHandler = new SmHandler(looper, this, null);
    }

    public void addLogRec(String s)
    {
        SmHandler smhandler = mSmHandler;
        if(smhandler == null)
        {
            return;
        } else
        {
            SmHandler._2D_get4(smhandler).add(this, SmHandler._2D_wrap0(smhandler), s, SmHandler._2D_wrap3(smhandler), SmHandler._2D_get6(smhandler)[SmHandler._2D_get7(smhandler)].state, SmHandler._2D_get2(smhandler));
            return;
        }
    }

    public final void addState(State state)
    {
        SmHandler._2D_wrap4(mSmHandler, state, null);
    }

    public final void addState(State state, State state1)
    {
        SmHandler._2D_wrap4(mSmHandler, state, state1);
    }

    public final Collection copyLogRecs()
    {
        Vector vector = new Vector();
        Object obj = mSmHandler;
        if(obj != null)
            for(obj = LogRecords._2D_get0(SmHandler._2D_get4(((SmHandler) (obj)))).iterator(); ((Iterator) (obj)).hasNext(); vector.add((LogRec)((Iterator) (obj)).next()));
        return vector;
    }

    public final void deferMessage(Message message)
    {
        SmHandler._2D_wrap6(mSmHandler, message);
    }

    public void dump(FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        printwriter.println((new StringBuilder()).append(getName()).append(":").toString());
        printwriter.println((new StringBuilder()).append(" total records=").append(getLogRecCount()).toString());
        for(int i = 0; i < getLogRecSize(); i++)
        {
            printwriter.println((new StringBuilder()).append(" rec[").append(i).append("]: ").append(getLogRec(i).toString()).toString());
            printwriter.flush();
        }

        printwriter.println((new StringBuilder()).append("curState=").append(getCurrentState().getName()).toString());
    }

    public final Message getCurrentMessage()
    {
        SmHandler smhandler = mSmHandler;
        if(smhandler == null)
            return null;
        else
            return SmHandler._2D_wrap0(smhandler);
    }

    public final IState getCurrentState()
    {
        SmHandler smhandler = mSmHandler;
        if(smhandler == null)
            return null;
        else
            return SmHandler._2D_wrap3(smhandler);
    }

    public final Handler getHandler()
    {
        return mSmHandler;
    }

    public final LogRec getLogRec(int i)
    {
        SmHandler smhandler = mSmHandler;
        if(smhandler == null)
            return null;
        else
            return SmHandler._2D_get4(smhandler).get(i);
    }

    public final int getLogRecCount()
    {
        SmHandler smhandler = mSmHandler;
        if(smhandler == null)
            return 0;
        else
            return SmHandler._2D_get4(smhandler).count();
    }

    public final int getLogRecMaxSize()
    {
        SmHandler smhandler = mSmHandler;
        if(smhandler == null)
            return 0;
        else
            return LogRecords._2D_get1(SmHandler._2D_get4(smhandler));
    }

    public final int getLogRecSize()
    {
        SmHandler smhandler = mSmHandler;
        if(smhandler == null)
            return 0;
        else
            return SmHandler._2D_get4(smhandler).size();
    }

    protected String getLogRecString(Message message)
    {
        return "";
    }

    public final String getName()
    {
        return mName;
    }

    protected String getWhatToString(int i)
    {
        return null;
    }

    protected void haltedProcessMessage(Message message)
    {
    }

    protected final boolean hasDeferredMessages(int i)
    {
        Object obj = mSmHandler;
        if(obj == null)
            return false;
        for(obj = SmHandler._2D_get1(((SmHandler) (obj))).iterator(); ((Iterator) (obj)).hasNext();)
            if(((Message)((Iterator) (obj)).next()).what == i)
                return true;

        return false;
    }

    protected final boolean hasDeferredMessages(int i, Object obj)
    {
        Object obj1 = mSmHandler;
        if(obj1 == null)
            return false;
        for(obj1 = SmHandler._2D_get1(((SmHandler) (obj1))).iterator(); ((Iterator) (obj1)).hasNext();)
        {
            Message message = (Message)((Iterator) (obj1)).next();
            if(message.what == i && message.obj == obj)
                return true;
        }

        return false;
    }

    protected final boolean hasMessages(int i)
    {
        SmHandler smhandler = mSmHandler;
        if(smhandler == null)
            return false;
        else
            return smhandler.hasMessages(i);
    }

    public boolean isDbg()
    {
        SmHandler smhandler = mSmHandler;
        if(smhandler == null)
            return false;
        else
            return SmHandler._2D_wrap1(smhandler);
    }

    protected final boolean isQuit(Message message)
    {
        SmHandler smhandler = mSmHandler;
        if(smhandler == null)
        {
            boolean flag;
            if(message.what == -1)
                flag = true;
            else
                flag = false;
            return flag;
        } else
        {
            return SmHandler._2D_wrap2(smhandler, message);
        }
    }

    protected void log(String s)
    {
        Log.d(mName, s);
    }

    protected void logAndAddLogRec(String s)
    {
        addLogRec(s);
        log(s);
    }

    protected void logd(String s)
    {
        Log.d(mName, s);
    }

    protected void loge(String s)
    {
        Log.e(mName, s);
    }

    protected void loge(String s, Throwable throwable)
    {
        Log.e(mName, s, throwable);
    }

    protected void logi(String s)
    {
        Log.i(mName, s);
    }

    protected void logv(String s)
    {
        Log.v(mName, s);
    }

    protected void logw(String s)
    {
        Log.w(mName, s);
    }

    public final Message obtainMessage()
    {
        return Message.obtain(mSmHandler);
    }

    public final Message obtainMessage(int i)
    {
        return Message.obtain(mSmHandler, i);
    }

    public final Message obtainMessage(int i, int j)
    {
        return Message.obtain(mSmHandler, i, j, 0);
    }

    public final Message obtainMessage(int i, int j, int k)
    {
        return Message.obtain(mSmHandler, i, j, k);
    }

    public final Message obtainMessage(int i, int j, int k, Object obj)
    {
        return Message.obtain(mSmHandler, i, j, k, obj);
    }

    public final Message obtainMessage(int i, Object obj)
    {
        return Message.obtain(mSmHandler, i, obj);
    }

    protected void onHalting()
    {
    }

    protected void onPostHandleMessage(Message message)
    {
    }

    protected void onPreHandleMessage(Message message)
    {
    }

    protected void onQuitting()
    {
    }

    public final void quit()
    {
        SmHandler smhandler = mSmHandler;
        if(smhandler == null)
        {
            return;
        } else
        {
            SmHandler._2D_wrap8(smhandler);
            return;
        }
    }

    public final void quitNow()
    {
        SmHandler smhandler = mSmHandler;
        if(smhandler == null)
        {
            return;
        } else
        {
            SmHandler._2D_wrap7(smhandler);
            return;
        }
    }

    protected boolean recordLogRec(Message message)
    {
        return true;
    }

    protected final void removeDeferredMessages(int i)
    {
        Object obj = mSmHandler;
        if(obj == null)
            return;
        obj = SmHandler._2D_get1(((SmHandler) (obj))).iterator();
        do
        {
            if(!((Iterator) (obj)).hasNext())
                break;
            if(((Message)((Iterator) (obj)).next()).what == i)
                ((Iterator) (obj)).remove();
        } while(true);
    }

    protected final void removeMessages(int i)
    {
        SmHandler smhandler = mSmHandler;
        if(smhandler == null)
        {
            return;
        } else
        {
            smhandler.removeMessages(i);
            return;
        }
    }

    public final void removeState(State state)
    {
        SmHandler._2D_wrap9(mSmHandler, state);
    }

    public void sendMessage(int i)
    {
        SmHandler smhandler = mSmHandler;
        if(smhandler == null)
        {
            return;
        } else
        {
            smhandler.sendMessage(obtainMessage(i));
            return;
        }
    }

    public void sendMessage(int i, int j)
    {
        SmHandler smhandler = mSmHandler;
        if(smhandler == null)
        {
            return;
        } else
        {
            smhandler.sendMessage(obtainMessage(i, j));
            return;
        }
    }

    public void sendMessage(int i, int j, int k)
    {
        SmHandler smhandler = mSmHandler;
        if(smhandler == null)
        {
            return;
        } else
        {
            smhandler.sendMessage(obtainMessage(i, j, k));
            return;
        }
    }

    public void sendMessage(int i, int j, int k, Object obj)
    {
        SmHandler smhandler = mSmHandler;
        if(smhandler == null)
        {
            return;
        } else
        {
            smhandler.sendMessage(obtainMessage(i, j, k, obj));
            return;
        }
    }

    public void sendMessage(int i, Object obj)
    {
        SmHandler smhandler = mSmHandler;
        if(smhandler == null)
        {
            return;
        } else
        {
            smhandler.sendMessage(obtainMessage(i, obj));
            return;
        }
    }

    public void sendMessage(Message message)
    {
        SmHandler smhandler = mSmHandler;
        if(smhandler == null)
        {
            return;
        } else
        {
            smhandler.sendMessage(message);
            return;
        }
    }

    protected final void sendMessageAtFrontOfQueue(int i)
    {
        SmHandler smhandler = mSmHandler;
        if(smhandler == null)
        {
            return;
        } else
        {
            smhandler.sendMessageAtFrontOfQueue(obtainMessage(i));
            return;
        }
    }

    protected final void sendMessageAtFrontOfQueue(int i, int j)
    {
        SmHandler smhandler = mSmHandler;
        if(smhandler == null)
        {
            return;
        } else
        {
            smhandler.sendMessageAtFrontOfQueue(obtainMessage(i, j));
            return;
        }
    }

    protected final void sendMessageAtFrontOfQueue(int i, int j, int k)
    {
        SmHandler smhandler = mSmHandler;
        if(smhandler == null)
        {
            return;
        } else
        {
            smhandler.sendMessageAtFrontOfQueue(obtainMessage(i, j, k));
            return;
        }
    }

    protected final void sendMessageAtFrontOfQueue(int i, int j, int k, Object obj)
    {
        SmHandler smhandler = mSmHandler;
        if(smhandler == null)
        {
            return;
        } else
        {
            smhandler.sendMessageAtFrontOfQueue(obtainMessage(i, j, k, obj));
            return;
        }
    }

    protected final void sendMessageAtFrontOfQueue(int i, Object obj)
    {
        SmHandler smhandler = mSmHandler;
        if(smhandler == null)
        {
            return;
        } else
        {
            smhandler.sendMessageAtFrontOfQueue(obtainMessage(i, obj));
            return;
        }
    }

    protected final void sendMessageAtFrontOfQueue(Message message)
    {
        SmHandler smhandler = mSmHandler;
        if(smhandler == null)
        {
            return;
        } else
        {
            smhandler.sendMessageAtFrontOfQueue(message);
            return;
        }
    }

    public void sendMessageDelayed(int i, int j, int k, long l)
    {
        SmHandler smhandler = mSmHandler;
        if(smhandler == null)
        {
            return;
        } else
        {
            smhandler.sendMessageDelayed(obtainMessage(i, j, k), l);
            return;
        }
    }

    public void sendMessageDelayed(int i, int j, int k, Object obj, long l)
    {
        SmHandler smhandler = mSmHandler;
        if(smhandler == null)
        {
            return;
        } else
        {
            smhandler.sendMessageDelayed(obtainMessage(i, j, k, obj), l);
            return;
        }
    }

    public void sendMessageDelayed(int i, int j, long l)
    {
        SmHandler smhandler = mSmHandler;
        if(smhandler == null)
        {
            return;
        } else
        {
            smhandler.sendMessageDelayed(obtainMessage(i, j), l);
            return;
        }
    }

    public void sendMessageDelayed(int i, long l)
    {
        SmHandler smhandler = mSmHandler;
        if(smhandler == null)
        {
            return;
        } else
        {
            smhandler.sendMessageDelayed(obtainMessage(i), l);
            return;
        }
    }

    public void sendMessageDelayed(int i, Object obj, long l)
    {
        SmHandler smhandler = mSmHandler;
        if(smhandler == null)
        {
            return;
        } else
        {
            smhandler.sendMessageDelayed(obtainMessage(i, obj), l);
            return;
        }
    }

    public void sendMessageDelayed(Message message, long l)
    {
        SmHandler smhandler = mSmHandler;
        if(smhandler == null)
        {
            return;
        } else
        {
            smhandler.sendMessageDelayed(message, l);
            return;
        }
    }

    public void setDbg(boolean flag)
    {
        SmHandler smhandler = mSmHandler;
        if(smhandler == null)
        {
            return;
        } else
        {
            SmHandler._2D_wrap10(smhandler, flag);
            return;
        }
    }

    public final void setInitialState(State state)
    {
        SmHandler._2D_wrap11(mSmHandler, state);
    }

    public final void setLogOnlyTransitions(boolean flag)
    {
        SmHandler._2D_get4(mSmHandler).setLogOnlyTransitions(flag);
    }

    public final void setLogRecSize(int i)
    {
        SmHandler._2D_get4(mSmHandler).setSize(i);
    }

    public void start()
    {
        SmHandler smhandler = mSmHandler;
        if(smhandler == null)
        {
            return;
        } else
        {
            SmHandler._2D_wrap5(smhandler);
            return;
        }
    }

    public String toString()
    {
        String s;
        String s1;
        s = "(null)";
        s1 = "(null)";
        Object obj = mName.toString();
        s = ((String) (obj));
        String s2 = SmHandler._2D_wrap3(mSmHandler).getName().toString();
        s = s2;
_L2:
        return (new StringBuilder()).append("name=").append(((String) (obj))).append(" state=").append(s).toString();
        NullPointerException nullpointerexception;
        nullpointerexception;
        nullpointerexception = s;
        s = s1;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public final void transitionTo(IState istate)
    {
        SmHandler._2D_wrap12(mSmHandler, istate);
    }

    public final void transitionToHaltingState()
    {
        SmHandler._2D_wrap12(mSmHandler, SmHandler._2D_get3(mSmHandler));
    }

    protected void unhandledMessage(Message message)
    {
        if(SmHandler._2D_get0(mSmHandler))
            loge((new StringBuilder()).append(" - unhandledMessage: msg.what=").append(message.what).toString());
    }

    public static final boolean HANDLED = true;
    public static final boolean NOT_HANDLED = false;
    private static final int SM_INIT_CMD = -2;
    private static final int SM_QUIT_CMD = -1;
    private String mName;
    private SmHandler mSmHandler;
    private HandlerThread mSmThread;
}
