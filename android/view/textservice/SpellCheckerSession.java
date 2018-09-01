// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.textservice;

import android.os.*;
import android.util.Log;
import com.android.internal.textservice.*;
import dalvik.system.CloseGuard;
import java.util.LinkedList;
import java.util.Queue;

// Referenced classes of package android.view.textservice:
//            TextInfo, SpellCheckerInfo, SentenceSuggestionsInfo, SuggestionsInfo

public class SpellCheckerSession
{
    private static final class InternalListener extends com.android.internal.textservice.ITextServicesSessionListener.Stub
    {

        public void onServiceConnected(ISpellCheckerSession ispellcheckersession)
        {
            mParentSpellCheckerSessionListenerImpl.onServiceConnected(ispellcheckersession);
        }

        private final SpellCheckerSessionListenerImpl mParentSpellCheckerSessionListenerImpl;

        public InternalListener(SpellCheckerSessionListenerImpl spellcheckersessionlistenerimpl)
        {
            mParentSpellCheckerSessionListenerImpl = spellcheckersessionlistenerimpl;
        }
    }

    public static interface SpellCheckerSessionListener
    {

        public abstract void onGetSentenceSuggestions(SentenceSuggestionsInfo asentencesuggestionsinfo[]);

        public abstract void onGetSuggestions(SuggestionsInfo asuggestionsinfo[]);
    }

    private static final class SpellCheckerSessionListenerImpl extends com.android.internal.textservice.ISpellCheckerSessionListener.Stub
    {

        static void _2D_wrap0(SpellCheckerSessionListenerImpl spellcheckersessionlistenerimpl, ISpellCheckerSession ispellcheckersession, SpellCheckerParams spellcheckerparams, boolean flag)
        {
            spellcheckersessionlistenerimpl.processTask(ispellcheckersession, spellcheckerparams, flag);
        }

        private void processCloseLocked()
        {
            mISpellCheckerSession = null;
            if(mThread != null)
                mThread.quit();
            mHandler = null;
            mPendingTasks.clear();
            mThread = null;
            mAsyncHandler = null;
            mState;
            JVM INSTR tableswitch 0 1: default 72
        //                       0 105
        //                       1 113;
               goto _L1 _L2 _L3
_L1:
            Log.e(SpellCheckerSession._2D_get0(), (new StringBuilder()).append("processCloseLocked is called unexpectedly. mState=").append(stateToString(mState)).toString());
_L5:
            return;
_L2:
            mState = 3;
            continue; /* Loop/switch isn't completed */
_L3:
            mState = 2;
            if(true) goto _L5; else goto _L4
_L4:
        }

        private void processOrEnqueueTask(SpellCheckerParams spellcheckerparams)
        {
            this;
            JVM INSTR monitorenter ;
            if(mState == 0 || mState == 1)
                break MISSING_BLOCK_LABEL_71;
            String s = SpellCheckerSession._2D_get0();
            StringBuilder stringbuilder = JVM INSTR new #82  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.e(s, stringbuilder.append("ignoring processOrEnqueueTask due to unexpected mState=").append(taskToString(spellcheckerparams.mWhat)).append(" scp.mWhat=").append(taskToString(spellcheckerparams.mWhat)).toString());
            this;
            JVM INSTR monitorexit ;
            return;
            if(mState != 0)
                break MISSING_BLOCK_LABEL_174;
            if(spellcheckerparams.mWhat != 3)
                break MISSING_BLOCK_LABEL_93;
            processCloseLocked();
            this;
            JVM INSTR monitorexit ;
            return;
            Object obj;
            SpellCheckerParams spellcheckerparams1;
            spellcheckerparams1 = null;
            obj = null;
            if(spellcheckerparams.mWhat != 1) goto _L2; else goto _L1
_L1:
            spellcheckerparams1 = ((SpellCheckerParams) (obj));
            if(mPendingTasks.isEmpty())
                break; /* Loop/switch isn't completed */
            spellcheckerparams1 = (SpellCheckerParams)mPendingTasks.poll();
            if(spellcheckerparams1.mWhat == 3)
                obj = spellcheckerparams1;
            if(true) goto _L1; else goto _L2
_L2:
            mPendingTasks.offer(spellcheckerparams);
            if(spellcheckerparams1 == null)
                break MISSING_BLOCK_LABEL_171;
            mPendingTasks.offer(spellcheckerparams1);
            this;
            JVM INSTR monitorexit ;
            return;
            obj = mISpellCheckerSession;
            this;
            JVM INSTR monitorexit ;
            processTask(((ISpellCheckerSession) (obj)), spellcheckerparams, false);
            return;
            spellcheckerparams;
            throw spellcheckerparams;
        }

        private void processTask(ISpellCheckerSession ispellcheckersession, SpellCheckerParams spellcheckerparams, boolean flag)
        {
            if(!flag && mAsyncHandler != null) goto _L2; else goto _L1
_L1:
            spellcheckerparams.mWhat;
            JVM INSTR tableswitch 1 4: default 44
        //                       1 61
        //                       2 100
        //                       3 198
        //                       4 151;
               goto _L3 _L4 _L5 _L6 _L7
_L3:
            if(spellcheckerparams.mWhat != 3) goto _L9; else goto _L8
_L8:
            this;
            JVM INSTR monitorenter ;
            processCloseLocked();
            this;
            JVM INSTR monitorexit ;
_L9:
            return;
_L4:
            try
            {
                ispellcheckersession.onCancel();
            }
            // Misplaced declaration of an exception variable
            catch(ISpellCheckerSession ispellcheckersession)
            {
                Log.e(SpellCheckerSession._2D_get0(), (new StringBuilder()).append("Failed to cancel ").append(ispellcheckersession).toString());
            }
            continue; /* Loop/switch isn't completed */
_L5:
            try
            {
                ispellcheckersession.onGetSuggestionsMultiple(spellcheckerparams.mTextInfos, spellcheckerparams.mSuggestionsLimit, spellcheckerparams.mSequentialWords);
            }
            // Misplaced declaration of an exception variable
            catch(ISpellCheckerSession ispellcheckersession)
            {
                Log.e(SpellCheckerSession._2D_get0(), (new StringBuilder()).append("Failed to get suggestions ").append(ispellcheckersession).toString());
            }
            continue; /* Loop/switch isn't completed */
_L7:
            try
            {
                ispellcheckersession.onGetSentenceSuggestionsMultiple(spellcheckerparams.mTextInfos, spellcheckerparams.mSuggestionsLimit);
            }
            // Misplaced declaration of an exception variable
            catch(ISpellCheckerSession ispellcheckersession)
            {
                Log.e(SpellCheckerSession._2D_get0(), (new StringBuilder()).append("Failed to get suggestions ").append(ispellcheckersession).toString());
            }
            continue; /* Loop/switch isn't completed */
_L6:
            try
            {
                ispellcheckersession.onClose();
            }
            // Misplaced declaration of an exception variable
            catch(ISpellCheckerSession ispellcheckersession)
            {
                Log.e(SpellCheckerSession._2D_get0(), (new StringBuilder()).append("Failed to close ").append(ispellcheckersession).toString());
            }
            continue; /* Loop/switch isn't completed */
_L2:
            spellcheckerparams.mSession = ispellcheckersession;
            mAsyncHandler.sendMessage(Message.obtain(mAsyncHandler, 1, spellcheckerparams));
            if(true) goto _L3; else goto _L10
_L10:
            ispellcheckersession;
            throw ispellcheckersession;
        }

        private static String stateToString(int i)
        {
            switch(i)
            {
            default:
                return (new StringBuilder()).append("Unexpected state=").append(i).toString();

            case 0: // '\0'
                return "STATE_WAIT_CONNECTION";

            case 1: // '\001'
                return "STATE_CONNECTED";

            case 2: // '\002'
                return "STATE_CLOSED_AFTER_CONNECTION";

            case 3: // '\003'
                return "STATE_CLOSED_BEFORE_CONNECTION";
            }
        }

        private static String taskToString(int i)
        {
            switch(i)
            {
            default:
                return (new StringBuilder()).append("Unexpected task=").append(i).toString();

            case 1: // '\001'
                return "TASK_CANCEL";

            case 2: // '\002'
                return "TASK_GET_SUGGESTIONS_MULTIPLE";

            case 3: // '\003'
                return "TASK_CLOSE";

            case 4: // '\004'
                return "TASK_GET_SUGGESTIONS_MULTIPLE_FOR_SENTENCE";
            }
        }

        public void cancel()
        {
            processOrEnqueueTask(new SpellCheckerParams(1, null, 0, false));
        }

        public void close()
        {
            processOrEnqueueTask(new SpellCheckerParams(3, null, 0, false));
        }

        public void getSentenceSuggestionsMultiple(TextInfo atextinfo[], int i)
        {
            processOrEnqueueTask(new SpellCheckerParams(4, atextinfo, i, false));
        }

        public void getSuggestionsMultiple(TextInfo atextinfo[], int i, boolean flag)
        {
            processOrEnqueueTask(new SpellCheckerParams(2, atextinfo, i, flag));
        }

        public boolean isDisconnected()
        {
            boolean flag = true;
            this;
            JVM INSTR monitorenter ;
            int i = mState;
            if(i == 1)
                flag = false;
            this;
            JVM INSTR monitorexit ;
            return flag;
            Exception exception;
            exception;
            throw exception;
        }

        public void onGetSentenceSuggestions(SentenceSuggestionsInfo asentencesuggestionsinfo[])
        {
            this;
            JVM INSTR monitorenter ;
            if(mHandler != null)
                mHandler.sendMessage(Message.obtain(mHandler, 2, asentencesuggestionsinfo));
            this;
            JVM INSTR monitorexit ;
            return;
            asentencesuggestionsinfo;
            throw asentencesuggestionsinfo;
        }

        public void onGetSuggestions(SuggestionsInfo asuggestionsinfo[])
        {
            this;
            JVM INSTR monitorenter ;
            if(mHandler != null)
                mHandler.sendMessage(Message.obtain(mHandler, 1, asuggestionsinfo));
            this;
            JVM INSTR monitorexit ;
            return;
            asuggestionsinfo;
            throw asuggestionsinfo;
        }

        public void onServiceConnected(ISpellCheckerSession ispellcheckersession)
        {
            this;
            JVM INSTR monitorenter ;
            mState;
            JVM INSTR tableswitch 0 3: default 36
        //                       0 78
        //                       1 36
        //                       2 36
        //                       3 75;
               goto _L1 _L2 _L1 _L1 _L3
_L1:
            ispellcheckersession = SpellCheckerSession._2D_get0();
            StringBuilder stringbuilder = JVM INSTR new #82  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.e(ispellcheckersession, stringbuilder.append("ignoring onServiceConnected due to unexpected mState=").append(stateToString(mState)).toString());
            this;
            JVM INSTR monitorexit ;
            return;
_L3:
            this;
            JVM INSTR monitorexit ;
            return;
_L2:
            if(ispellcheckersession != null) goto _L5; else goto _L4
_L4:
            Log.e(SpellCheckerSession._2D_get0(), "ignoring onServiceConnected due to session=null");
            this;
            JVM INSTR monitorexit ;
            return;
_L5:
            mISpellCheckerSession = ispellcheckersession;
            if((ispellcheckersession.asBinder() instanceof Binder) && mThread == null)
            {
                Object obj = JVM INSTR new #65  <Class HandlerThread>;
                ((HandlerThread) (obj)).HandlerThread("SpellCheckerSession", 10);
                mThread = ((HandlerThread) (obj));
                mThread.start();
                obj = JVM INSTR new #9   <Class SpellCheckerSession$SpellCheckerSessionListenerImpl$1>;
                ((_cls1) (obj)).this. _cls1(mThread.getLooper());
                mAsyncHandler = ((Handler) (obj));
            }
            mState = 1;
            for(; !mPendingTasks.isEmpty(); processTask(ispellcheckersession, (SpellCheckerParams)mPendingTasks.poll(), false));
              goto _L6
            ispellcheckersession;
            throw ispellcheckersession;
_L6:
            this;
            JVM INSTR monitorexit ;
        }

        private static final int STATE_CLOSED_AFTER_CONNECTION = 2;
        private static final int STATE_CLOSED_BEFORE_CONNECTION = 3;
        private static final int STATE_CONNECTED = 1;
        private static final int STATE_WAIT_CONNECTION = 0;
        private static final int TASK_CANCEL = 1;
        private static final int TASK_CLOSE = 3;
        private static final int TASK_GET_SUGGESTIONS_MULTIPLE = 2;
        private static final int TASK_GET_SUGGESTIONS_MULTIPLE_FOR_SENTENCE = 4;
        private Handler mAsyncHandler;
        private Handler mHandler;
        private ISpellCheckerSession mISpellCheckerSession;
        private final Queue mPendingTasks = new LinkedList();
        private int mState;
        private HandlerThread mThread;

        public SpellCheckerSessionListenerImpl(Handler handler)
        {
            mState = 0;
            mHandler = handler;
        }
    }

    private static class SpellCheckerSessionListenerImpl.SpellCheckerParams
    {

        public final boolean mSequentialWords;
        public ISpellCheckerSession mSession;
        public final int mSuggestionsLimit;
        public final TextInfo mTextInfos[];
        public final int mWhat;

        public SpellCheckerSessionListenerImpl.SpellCheckerParams(int i, TextInfo atextinfo[], int j, boolean flag)
        {
            mWhat = i;
            mTextInfos = atextinfo;
            mSuggestionsLimit = j;
            mSequentialWords = flag;
        }
    }


    static String _2D_get0()
    {
        return TAG;
    }

    static void _2D_wrap0(SpellCheckerSession spellcheckersession, SentenceSuggestionsInfo asentencesuggestionsinfo[])
    {
        spellcheckersession.handleOnGetSentenceSuggestionsMultiple(asentencesuggestionsinfo);
    }

    static void _2D_wrap1(SpellCheckerSession spellcheckersession, SuggestionsInfo asuggestionsinfo[])
    {
        spellcheckersession.handleOnGetSuggestionsMultiple(asuggestionsinfo);
    }

    public SpellCheckerSession(SpellCheckerInfo spellcheckerinfo, ITextServicesManager itextservicesmanager, SpellCheckerSessionListener spellcheckersessionlistener)
    {
        for(mHandler = new Handler() {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 2: default 28
        //                       1 29
        //                       2 46;
               goto _L1 _L2 _L3
_L1:
            return;
_L2:
            SpellCheckerSession._2D_wrap1(SpellCheckerSession.this, (SuggestionsInfo[])message.obj);
            continue; /* Loop/switch isn't completed */
_L3:
            SpellCheckerSession._2D_wrap0(SpellCheckerSession.this, (SentenceSuggestionsInfo[])message.obj);
            if(true) goto _L1; else goto _L4
_L4:
        }

        final SpellCheckerSession this$0;

            
            {
                this$0 = SpellCheckerSession.this;
                super();
            }
    }
; spellcheckerinfo == null || spellcheckersessionlistener == null || itextservicesmanager == null;)
            throw new NullPointerException();

        mSpellCheckerInfo = spellcheckerinfo;
        mSpellCheckerSessionListenerImpl = new SpellCheckerSessionListenerImpl(mHandler);
        mInternalListener = new InternalListener(mSpellCheckerSessionListenerImpl);
        mTextServicesManager = itextservicesmanager;
        mSpellCheckerSessionListener = spellcheckersessionlistener;
        mGuard.open("finishSession");
    }

    private void handleOnGetSentenceSuggestionsMultiple(SentenceSuggestionsInfo asentencesuggestionsinfo[])
    {
        mSpellCheckerSessionListener.onGetSentenceSuggestions(asentencesuggestionsinfo);
    }

    private void handleOnGetSuggestionsMultiple(SuggestionsInfo asuggestionsinfo[])
    {
        mSpellCheckerSessionListener.onGetSuggestions(asuggestionsinfo);
    }

    public void cancel()
    {
        mSpellCheckerSessionListenerImpl.cancel();
    }

    public void close()
    {
        mGuard.close();
        mSpellCheckerSessionListenerImpl.close();
        mTextServicesManager.finishSpellCheckerService(mSpellCheckerSessionListenerImpl);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    protected void finalize()
        throws Throwable
    {
        if(mGuard != null)
        {
            mGuard.warnIfOpen();
            close();
        }
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public void getSentenceSuggestions(TextInfo atextinfo[], int i)
    {
        mSpellCheckerSessionListenerImpl.getSentenceSuggestionsMultiple(atextinfo, i);
    }

    public SpellCheckerInfo getSpellChecker()
    {
        return mSpellCheckerInfo;
    }

    public ISpellCheckerSessionListener getSpellCheckerSessionListener()
    {
        return mSpellCheckerSessionListenerImpl;
    }

    public void getSuggestions(TextInfo textinfo, int i)
    {
        getSuggestions(new TextInfo[] {
            textinfo
        }, i, false);
    }

    public void getSuggestions(TextInfo atextinfo[], int i, boolean flag)
    {
        mSpellCheckerSessionListenerImpl.getSuggestionsMultiple(atextinfo, i, flag);
    }

    public ITextServicesSessionListener getTextServicesSessionListener()
    {
        return mInternalListener;
    }

    public boolean isSessionDisconnected()
    {
        return mSpellCheckerSessionListenerImpl.isDisconnected();
    }

    private static final boolean DBG = false;
    private static final int MSG_ON_GET_SUGGESTION_MULTIPLE = 1;
    private static final int MSG_ON_GET_SUGGESTION_MULTIPLE_FOR_SENTENCE = 2;
    public static final String SERVICE_META_DATA = "android.view.textservice.scs";
    private static final String TAG = android/view/textservice/SpellCheckerSession.getSimpleName();
    private final CloseGuard mGuard = CloseGuard.get();
    private final Handler mHandler;
    private final InternalListener mInternalListener;
    private final SpellCheckerInfo mSpellCheckerInfo;
    private final SpellCheckerSessionListener mSpellCheckerSessionListener;
    private final SpellCheckerSessionListenerImpl mSpellCheckerSessionListenerImpl;
    private final ITextServicesManager mTextServicesManager;


    // Unreferenced inner class android/view/textservice/SpellCheckerSession$SpellCheckerSessionListenerImpl$1

/* anonymous class */
    class SpellCheckerSessionListenerImpl._cls1 extends Handler
    {

        public void handleMessage(Message message)
        {
            message = (SpellCheckerSessionListenerImpl.SpellCheckerParams)message.obj;
            SpellCheckerSessionListenerImpl._2D_wrap0(SpellCheckerSessionListenerImpl.this, ((SpellCheckerSessionListenerImpl.SpellCheckerParams) (message)).mSession, message, true);
        }

        final SpellCheckerSessionListenerImpl this$1;

            
            {
                this$1 = SpellCheckerSessionListenerImpl.this;
                super(looper);
            }
    }

}
