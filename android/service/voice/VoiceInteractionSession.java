// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.voice;

import android.app.Dialog;
import android.app.Instrumentation;
import android.app.assist.AssistContent;
import android.app.assist.AssistStructure;
import android.content.*;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.*;
import android.inputmethodservice.SoftInputWindow;
import android.os.*;
import android.util.*;
import android.view.*;
import android.widget.FrameLayout;
import com.android.internal.app.*;
import com.android.internal.os.HandlerCaller;
import com.android.internal.os.SomeArgs;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;

// Referenced classes of package android.service.voice:
//            IVoiceInteractionSession

public class VoiceInteractionSession
    implements android.view.KeyEvent.Callback, ComponentCallbacks2
{
    public static final class AbortVoiceRequest extends Request
    {

        void dump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
        {
            super.dump(s, filedescriptor, printwriter, as);
            printwriter.print(s);
            printwriter.print("mPrompt=");
            printwriter.println(mPrompt);
        }

        public CharSequence getMessage()
        {
            CharSequence charsequence = null;
            if(mPrompt != null)
                charsequence = mPrompt.getVoicePromptAt(0);
            return charsequence;
        }

        public android.app.VoiceInteractor.Prompt getVoicePrompt()
        {
            return mPrompt;
        }

        public void sendAbortResult(Bundle bundle)
        {
            finishRequest();
            mCallback.deliverAbortVoiceResult(mInterface, bundle);
_L2:
            return;
            bundle;
            if(true) goto _L2; else goto _L1
_L1:
        }

        final android.app.VoiceInteractor.Prompt mPrompt;

        AbortVoiceRequest(String s, int i, IVoiceInteractorCallback ivoiceinteractorcallback, VoiceInteractionSession voiceinteractionsession, android.app.VoiceInteractor.Prompt prompt, Bundle bundle)
        {
            super(s, i, ivoiceinteractorcallback, voiceinteractionsession, bundle);
            mPrompt = prompt;
        }
    }

    public static final class CommandRequest extends Request
    {

        void dump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
        {
            super.dump(s, filedescriptor, printwriter, as);
            printwriter.print(s);
            printwriter.print("mCommand=");
            printwriter.println(mCommand);
        }

        public String getCommand()
        {
            return mCommand;
        }

        void sendCommandResult(boolean flag, Bundle bundle)
        {
            if(!flag)
                break MISSING_BLOCK_LABEL_8;
            finishRequest();
            mCallback.deliverCommandResult(mInterface, flag, bundle);
_L2:
            return;
            bundle;
            if(true) goto _L2; else goto _L1
_L1:
        }

        public void sendIntermediateResult(Bundle bundle)
        {
            sendCommandResult(false, bundle);
        }

        public void sendResult(Bundle bundle)
        {
            sendCommandResult(true, bundle);
        }

        final String mCommand;

        CommandRequest(String s, int i, IVoiceInteractorCallback ivoiceinteractorcallback, VoiceInteractionSession voiceinteractionsession, String s1, Bundle bundle)
        {
            super(s, i, ivoiceinteractorcallback, voiceinteractionsession, bundle);
            mCommand = s1;
        }
    }

    public static final class CompleteVoiceRequest extends Request
    {

        void dump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
        {
            super.dump(s, filedescriptor, printwriter, as);
            printwriter.print(s);
            printwriter.print("mPrompt=");
            printwriter.println(mPrompt);
        }

        public CharSequence getMessage()
        {
            CharSequence charsequence = null;
            if(mPrompt != null)
                charsequence = mPrompt.getVoicePromptAt(0);
            return charsequence;
        }

        public android.app.VoiceInteractor.Prompt getVoicePrompt()
        {
            return mPrompt;
        }

        public void sendCompleteResult(Bundle bundle)
        {
            finishRequest();
            mCallback.deliverCompleteVoiceResult(mInterface, bundle);
_L2:
            return;
            bundle;
            if(true) goto _L2; else goto _L1
_L1:
        }

        final android.app.VoiceInteractor.Prompt mPrompt;

        CompleteVoiceRequest(String s, int i, IVoiceInteractorCallback ivoiceinteractorcallback, VoiceInteractionSession voiceinteractionsession, android.app.VoiceInteractor.Prompt prompt, Bundle bundle)
        {
            super(s, i, ivoiceinteractorcallback, voiceinteractionsession, bundle);
            mPrompt = prompt;
        }
    }

    public static final class ConfirmationRequest extends Request
    {

        void dump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
        {
            super.dump(s, filedescriptor, printwriter, as);
            printwriter.print(s);
            printwriter.print("mPrompt=");
            printwriter.println(mPrompt);
        }

        public CharSequence getPrompt()
        {
            CharSequence charsequence = null;
            if(mPrompt != null)
                charsequence = mPrompt.getVoicePromptAt(0);
            return charsequence;
        }

        public android.app.VoiceInteractor.Prompt getVoicePrompt()
        {
            return mPrompt;
        }

        public void sendConfirmationResult(boolean flag, Bundle bundle)
        {
            finishRequest();
            mCallback.deliverConfirmationResult(mInterface, flag, bundle);
_L2:
            return;
            bundle;
            if(true) goto _L2; else goto _L1
_L1:
        }

        final android.app.VoiceInteractor.Prompt mPrompt;

        ConfirmationRequest(String s, int i, IVoiceInteractorCallback ivoiceinteractorcallback, VoiceInteractionSession voiceinteractionsession, android.app.VoiceInteractor.Prompt prompt, Bundle bundle)
        {
            super(s, i, ivoiceinteractorcallback, voiceinteractionsession, bundle);
            mPrompt = prompt;
        }
    }

    public static final class Insets
    {

        public static final int TOUCHABLE_INSETS_CONTENT = 1;
        public static final int TOUCHABLE_INSETS_FRAME = 0;
        public static final int TOUCHABLE_INSETS_REGION = 3;
        public final Rect contentInsets = new Rect();
        public int touchableInsets;
        public final Region touchableRegion = new Region();

        public Insets()
        {
        }
    }

    class MyCallbacks
        implements com.android.internal.os.HandlerCaller.Callback, android.inputmethodservice.SoftInputWindow.Callback
    {

        public void executeMessage(Message message)
        {
            SomeArgs someargs = null;
            message.what;
            JVM INSTR lookupswitch 16: default 144
        //                       1: 155
        //                       2: 174
        //                       3: 193
        //                       4: 212
        //                       5: 231
        //                       6: 250
        //                       7: 285
        //                       100: 304
        //                       101: 327
        //                       102: 350
        //                       103: 362
        //                       104: 374
        //                       105: 473
        //                       106: 492
        //                       107: 530
        //                       108: 542;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17
_L1:
            message = someargs;
_L19:
            if(message != null)
                message.recycle();
            return;
_L2:
            onRequestConfirmation((ConfirmationRequest)message.obj);
            message = someargs;
            continue; /* Loop/switch isn't completed */
_L3:
            onRequestPickOption((PickOptionRequest)message.obj);
            message = someargs;
            continue; /* Loop/switch isn't completed */
_L4:
            onRequestCompleteVoice((CompleteVoiceRequest)message.obj);
            message = someargs;
            continue; /* Loop/switch isn't completed */
_L5:
            onRequestAbortVoice((AbortVoiceRequest)message.obj);
            message = someargs;
            continue; /* Loop/switch isn't completed */
_L6:
            onRequestCommand((CommandRequest)message.obj);
            message = someargs;
            continue; /* Loop/switch isn't completed */
_L7:
            message = (SomeArgs)message.obj;
            message.arg1 = onGetSupportedCommands((String[])((SomeArgs) (message)).arg1);
            message.complete();
            message = null;
            continue; /* Loop/switch isn't completed */
_L8:
            onCancelRequest((Request)message.obj);
            message = someargs;
            continue; /* Loop/switch isn't completed */
_L9:
            onTaskStarted((Intent)message.obj, message.arg1);
            message = someargs;
            continue; /* Loop/switch isn't completed */
_L10:
            onTaskFinished((Intent)message.obj, message.arg1);
            message = someargs;
            continue; /* Loop/switch isn't completed */
_L11:
            onCloseSystemDialogs();
            message = someargs;
            continue; /* Loop/switch isn't completed */
_L12:
            doDestroy();
            message = someargs;
            continue; /* Loop/switch isn't completed */
_L13:
            message = (SomeArgs)message.obj;
            if(((SomeArgs) (message)).argi5 == 0)
                doOnHandleAssist((Bundle)((SomeArgs) (message)).arg1, (AssistStructure)((SomeArgs) (message)).arg2, (Throwable)((SomeArgs) (message)).arg3, (AssistContent)((SomeArgs) (message)).arg4);
            else
                doOnHandleAssistSecondary((Bundle)((SomeArgs) (message)).arg1, (AssistStructure)((SomeArgs) (message)).arg2, (Throwable)((SomeArgs) (message)).arg3, (AssistContent)((SomeArgs) (message)).arg4, ((SomeArgs) (message)).argi5, ((SomeArgs) (message)).argi6);
            continue; /* Loop/switch isn't completed */
_L14:
            onHandleScreenshot((Bitmap)message.obj);
            message = someargs;
            continue; /* Loop/switch isn't completed */
_L15:
            someargs = (SomeArgs)message.obj;
            doShow((Bundle)someargs.arg1, message.arg1, (IVoiceInteractionSessionShowCallback)someargs.arg2);
            message = someargs;
            continue; /* Loop/switch isn't completed */
_L16:
            doHide();
            message = someargs;
            continue; /* Loop/switch isn't completed */
_L17:
            onLockscreenShown();
            message = someargs;
            if(true) goto _L19; else goto _L18
_L18:
        }

        public void onBackPressed()
        {
            VoiceInteractionSession.this.onBackPressed();
        }

        final VoiceInteractionSession this$0;

        MyCallbacks()
        {
            this$0 = VoiceInteractionSession.this;
            super();
        }
    }

    public static final class PickOptionRequest extends Request
    {

        void dump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
        {
            super.dump(s, filedescriptor, printwriter, as);
            printwriter.print(s);
            printwriter.print("mPrompt=");
            printwriter.println(mPrompt);
            if(mOptions != null)
            {
                printwriter.print(s);
                printwriter.println("Options:");
                for(int i = 0; i < mOptions.length; i++)
                {
                    filedescriptor = mOptions[i];
                    printwriter.print(s);
                    printwriter.print("  #");
                    printwriter.print(i);
                    printwriter.println(":");
                    printwriter.print(s);
                    printwriter.print("    mLabel=");
                    printwriter.println(filedescriptor.getLabel());
                    printwriter.print(s);
                    printwriter.print("    mIndex=");
                    printwriter.println(filedescriptor.getIndex());
                    if(filedescriptor.countSynonyms() > 0)
                    {
                        printwriter.print(s);
                        printwriter.println("    Synonyms:");
                        for(int j = 0; j < filedescriptor.countSynonyms(); j++)
                        {
                            printwriter.print(s);
                            printwriter.print("      #");
                            printwriter.print(j);
                            printwriter.print(": ");
                            printwriter.println(filedescriptor.getSynonymAt(j));
                        }

                    }
                    if(filedescriptor.getExtras() != null)
                    {
                        printwriter.print(s);
                        printwriter.print("    mExtras=");
                        printwriter.println(filedescriptor.getExtras());
                    }
                }

            }
        }

        public android.app.VoiceInteractor.PickOptionRequest.Option[] getOptions()
        {
            return mOptions;
        }

        public CharSequence getPrompt()
        {
            CharSequence charsequence = null;
            if(mPrompt != null)
                charsequence = mPrompt.getVoicePromptAt(0);
            return charsequence;
        }

        public android.app.VoiceInteractor.Prompt getVoicePrompt()
        {
            return mPrompt;
        }

        public void sendIntermediatePickOptionResult(android.app.VoiceInteractor.PickOptionRequest.Option aoption[], Bundle bundle)
        {
            sendPickOptionResult(false, aoption, bundle);
        }

        void sendPickOptionResult(boolean flag, android.app.VoiceInteractor.PickOptionRequest.Option aoption[], Bundle bundle)
        {
            if(!flag)
                break MISSING_BLOCK_LABEL_8;
            finishRequest();
            mCallback.deliverPickOptionResult(mInterface, flag, aoption, bundle);
_L2:
            return;
            aoption;
            if(true) goto _L2; else goto _L1
_L1:
        }

        public void sendPickOptionResult(android.app.VoiceInteractor.PickOptionRequest.Option aoption[], Bundle bundle)
        {
            sendPickOptionResult(true, aoption, bundle);
        }

        final android.app.VoiceInteractor.PickOptionRequest.Option mOptions[];
        final android.app.VoiceInteractor.Prompt mPrompt;

        PickOptionRequest(String s, int i, IVoiceInteractorCallback ivoiceinteractorcallback, VoiceInteractionSession voiceinteractionsession, android.app.VoiceInteractor.Prompt prompt, android.app.VoiceInteractor.PickOptionRequest.Option aoption[], Bundle bundle)
        {
            super(s, i, ivoiceinteractorcallback, voiceinteractionsession, bundle);
            mPrompt = prompt;
            mOptions = aoption;
        }
    }

    public static class Request
    {

        public void cancel()
        {
            finishRequest();
            mCallback.deliverCancel(mInterface);
_L2:
            return;
            RemoteException remoteexception;
            remoteexception;
            if(true) goto _L2; else goto _L1
_L1:
        }

        void dump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
        {
            printwriter.print(s);
            printwriter.print("mInterface=");
            printwriter.println(mInterface.asBinder());
            printwriter.print(s);
            printwriter.print("mCallingPackage=");
            printwriter.print(mCallingPackage);
            printwriter.print(" mCallingUid=");
            UserHandle.formatUid(printwriter, mCallingUid);
            printwriter.println();
            printwriter.print(s);
            printwriter.print("mCallback=");
            printwriter.println(mCallback.asBinder());
            if(mExtras != null)
            {
                printwriter.print(s);
                printwriter.print("mExtras=");
                printwriter.println(mExtras);
            }
        }

        void finishRequest()
        {
            Object obj = (VoiceInteractionSession)mSession.get();
            if(obj == null)
                throw new IllegalStateException("VoiceInteractionSession has been destroyed");
            obj = ((VoiceInteractionSession) (obj)).removeRequest(mInterface.asBinder());
            if(obj == null)
                throw new IllegalStateException((new StringBuilder()).append("Request not active: ").append(this).toString());
            if(obj != this)
                throw new IllegalStateException((new StringBuilder()).append("Current active request ").append(obj).append(" not same as calling request ").append(this).toString());
            else
                return;
        }

        public String getCallingPackage()
        {
            return mCallingPackage;
        }

        public int getCallingUid()
        {
            return mCallingUid;
        }

        public Bundle getExtras()
        {
            return mExtras;
        }

        public boolean isActive()
        {
            VoiceInteractionSession voiceinteractionsession = (VoiceInteractionSession)mSession.get();
            if(voiceinteractionsession == null)
                return false;
            else
                return voiceinteractionsession.isRequestActive(mInterface.asBinder());
        }

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder(128);
            DebugUtils.buildShortClassTag(this, stringbuilder);
            stringbuilder.append(" ");
            stringbuilder.append(mInterface.asBinder());
            stringbuilder.append(" pkg=");
            stringbuilder.append(mCallingPackage);
            stringbuilder.append(" uid=");
            UserHandle.formatUid(stringbuilder, mCallingUid);
            stringbuilder.append('}');
            return stringbuilder.toString();
        }

        final IVoiceInteractorCallback mCallback;
        final String mCallingPackage;
        final int mCallingUid;
        final Bundle mExtras;
        final IVoiceInteractorRequest mInterface = new _cls1();
        final WeakReference mSession;

        Request(String s, int i, IVoiceInteractorCallback ivoiceinteractorcallback, VoiceInteractionSession voiceinteractionsession, Bundle bundle)
        {
            mCallingPackage = s;
            mCallingUid = i;
            mCallback = ivoiceinteractorcallback;
            mSession = voiceinteractionsession.mWeakRef;
            mExtras = bundle;
        }
    }


    public VoiceInteractionSession(Context context)
    {
        this(context, new Handler());
    }

    public VoiceInteractionSession(Context context, Handler handler)
    {
        mDispatcherState = new android.view.KeyEvent.DispatcherState();
        mTheme = 0;
        mUiEnabled = true;
        mActiveRequests = new ArrayMap();
        mTmpInsets = new Insets();
        mWeakRef = new WeakReference(this);
        mInteractor = new com.android.internal.app.IVoiceInteractor.Stub() {

            public IVoiceInteractorRequest startAbortVoice(String s, IVoiceInteractorCallback ivoiceinteractorcallback, android.app.VoiceInteractor.Prompt prompt, Bundle bundle)
            {
                s = new AbortVoiceRequest(s, Binder.getCallingUid(), ivoiceinteractorcallback, VoiceInteractionSession.this, prompt, bundle);
                addRequest(s);
                mHandlerCaller.sendMessage(mHandlerCaller.obtainMessageO(4, s));
                return ((AbortVoiceRequest) (s)).mInterface;
            }

            public IVoiceInteractorRequest startCommand(String s, IVoiceInteractorCallback ivoiceinteractorcallback, String s1, Bundle bundle)
            {
                s = new CommandRequest(s, Binder.getCallingUid(), ivoiceinteractorcallback, VoiceInteractionSession.this, s1, bundle);
                addRequest(s);
                mHandlerCaller.sendMessage(mHandlerCaller.obtainMessageO(5, s));
                return ((CommandRequest) (s)).mInterface;
            }

            public IVoiceInteractorRequest startCompleteVoice(String s, IVoiceInteractorCallback ivoiceinteractorcallback, android.app.VoiceInteractor.Prompt prompt, Bundle bundle)
            {
                s = new CompleteVoiceRequest(s, Binder.getCallingUid(), ivoiceinteractorcallback, VoiceInteractionSession.this, prompt, bundle);
                addRequest(s);
                mHandlerCaller.sendMessage(mHandlerCaller.obtainMessageO(3, s));
                return ((CompleteVoiceRequest) (s)).mInterface;
            }

            public IVoiceInteractorRequest startConfirmation(String s, IVoiceInteractorCallback ivoiceinteractorcallback, android.app.VoiceInteractor.Prompt prompt, Bundle bundle)
            {
                s = new ConfirmationRequest(s, Binder.getCallingUid(), ivoiceinteractorcallback, VoiceInteractionSession.this, prompt, bundle);
                addRequest(s);
                mHandlerCaller.sendMessage(mHandlerCaller.obtainMessageO(1, s));
                return ((ConfirmationRequest) (s)).mInterface;
            }

            public IVoiceInteractorRequest startPickOption(String s, IVoiceInteractorCallback ivoiceinteractorcallback, android.app.VoiceInteractor.Prompt prompt, android.app.VoiceInteractor.PickOptionRequest.Option aoption[], Bundle bundle)
            {
                s = new PickOptionRequest(s, Binder.getCallingUid(), ivoiceinteractorcallback, VoiceInteractionSession.this, prompt, aoption, bundle);
                addRequest(s);
                mHandlerCaller.sendMessage(mHandlerCaller.obtainMessageO(2, s));
                return ((PickOptionRequest) (s)).mInterface;
            }

            public boolean[] supportsCommands(String s, String as[])
            {
                s = mHandlerCaller.obtainMessageIOO(6, 0, as, null);
                s = mHandlerCaller.sendMessageAndWait(s);
                if(s != null)
                {
                    as = (boolean[])((SomeArgs) (s)).arg1;
                    s.recycle();
                    return as;
                } else
                {
                    return new boolean[as.length];
                }
            }

            final VoiceInteractionSession this$0;

            
            {
                this$0 = VoiceInteractionSession.this;
                super();
            }
        }
;
        mSession = new IVoiceInteractionSession.Stub() {

            public void closeSystemDialogs()
            {
                mHandlerCaller.sendMessage(mHandlerCaller.obtainMessage(102));
            }

            public void destroy()
            {
                mHandlerCaller.sendMessage(mHandlerCaller.obtainMessage(103));
            }

            public void handleAssist(Bundle bundle, AssistStructure assiststructure, AssistContent assistcontent, int i, int j)
            {
                (i. new Thread(j) {

                    public void run()
                    {
                        AssistStructure assiststructure;
                        Object obj;
                        Throwable throwable;
                        assiststructure = null;
                        obj = null;
                        throwable = obj;
                        if(structure == null)
                            break MISSING_BLOCK_LABEL_22;
                        structure.ensureData();
                        throwable = obj;
_L2:
                        HandlerCaller handlercaller = mHandlerCaller;
                        HandlerCaller handlercaller1 = mHandlerCaller;
                        Bundle bundle = data;
                        if(throwable == null)
                            assiststructure = structure;
                        handlercaller.sendMessage(handlercaller1.obtainMessageOOOOII(104, bundle, assiststructure, throwable, content, index, count));
                        return;
                        throwable;
                        Log.w("VoiceInteractionSession", "Failure retrieving AssistStructure", throwable);
                        if(true) goto _L2; else goto _L1
_L1:
                    }

                    final _cls2 this$1;
                    final AssistContent val$content;
                    final int val$count;
                    final Bundle val$data;
                    final int val$index;
                    final AssistStructure val$structure;

            
            {
                this$1 = final__pcls2;
                structure = assiststructure;
                data = bundle;
                content = assistcontent;
                index = I.this;
                count = j;
                super(final_s);
            }
                }
).start();
            }

            public void handleScreenshot(Bitmap bitmap)
            {
                mHandlerCaller.sendMessage(mHandlerCaller.obtainMessageO(105, bitmap));
            }

            public void hide()
            {
                mHandlerCaller.removeMessages(106);
                mHandlerCaller.sendMessage(mHandlerCaller.obtainMessage(107));
            }

            public void onLockscreenShown()
            {
                mHandlerCaller.sendMessage(mHandlerCaller.obtainMessage(108));
            }

            public void show(Bundle bundle, int i, IVoiceInteractionSessionShowCallback ivoiceinteractionsessionshowcallback)
            {
                mHandlerCaller.sendMessage(mHandlerCaller.obtainMessageIOO(106, i, bundle, ivoiceinteractionsessionshowcallback));
            }

            public void taskFinished(Intent intent, int i)
            {
                mHandlerCaller.sendMessage(mHandlerCaller.obtainMessageIO(101, i, intent));
            }

            public void taskStarted(Intent intent, int i)
            {
                mHandlerCaller.sendMessage(mHandlerCaller.obtainMessageIO(100, i, intent));
            }

            final VoiceInteractionSession this$0;

            
            {
                this$0 = VoiceInteractionSession.this;
                super();
            }
        }
;
        mCallbacks = new MyCallbacks();
        mInsetsComputer = new android.view.ViewTreeObserver.OnComputeInternalInsetsListener() {

            public void onComputeInternalInsets(android.view.ViewTreeObserver.InternalInsetsInfo internalinsetsinfo)
            {
                onComputeInsets(mTmpInsets);
                internalinsetsinfo.contentInsets.set(mTmpInsets.contentInsets);
                internalinsetsinfo.visibleInsets.set(mTmpInsets.contentInsets);
                internalinsetsinfo.touchableRegion.set(mTmpInsets.touchableRegion);
                internalinsetsinfo.setTouchableInsets(mTmpInsets.touchableInsets);
            }

            final VoiceInteractionSession this$0;

            
            {
                this$0 = VoiceInteractionSession.this;
                super();
            }
        }
;
        mContext = context;
        mHandlerCaller = new HandlerCaller(context, handler.getLooper(), mCallbacks, true);
    }

    private void doOnCreate()
    {
        int i;
        if(mTheme != 0)
            i = mTheme;
        else
            i = 0x10303cb;
        mTheme = i;
    }

    void addRequest(Request request)
    {
        this;
        JVM INSTR monitorenter ;
        mActiveRequests.put(request.mInterface.asBinder(), request);
        this;
        JVM INSTR monitorexit ;
        return;
        request;
        throw request;
    }

    public void closeSystemDialogs()
    {
        if(mToken == null)
            throw new IllegalStateException("Can't call before onCreate()");
        mSystemService.closeSystemDialogs(mToken);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    void doCreate(IVoiceInteractionManagerService ivoiceinteractionmanagerservice, IBinder ibinder)
    {
        mSystemService = ivoiceinteractionmanagerservice;
        mToken = ibinder;
        onCreate();
    }

    void doDestroy()
    {
        onDestroy();
        if(mInitialized)
        {
            mRootView.getViewTreeObserver().removeOnComputeInternalInsetsListener(mInsetsComputer);
            if(mWindowAdded)
            {
                mWindow.dismiss();
                mWindowAdded = false;
            }
            mInitialized = false;
        }
    }

    void doHide()
    {
        if(mWindowVisible)
        {
            ensureWindowHidden();
            mWindowVisible = false;
            onHide();
        }
    }

    void doOnHandleAssist(Bundle bundle, AssistStructure assiststructure, Throwable throwable, AssistContent assistcontent)
    {
        if(throwable != null)
            onAssistStructureFailure(throwable);
        onHandleAssist(bundle, assiststructure, assistcontent);
    }

    void doOnHandleAssistSecondary(Bundle bundle, AssistStructure assiststructure, Throwable throwable, AssistContent assistcontent, int i, int j)
    {
        if(throwable != null)
            onAssistStructureFailure(throwable);
        onHandleAssistSecondary(bundle, assiststructure, assistcontent, i, j);
    }

    void doShow(Bundle bundle, int i, IVoiceInteractionSessionShowCallback ivoiceinteractionsessionshowcallback)
    {
        if(mInShowWindow)
        {
            Log.w("VoiceInteractionSession", "Re-entrance in to showWindow");
            return;
        }
        mInShowWindow = true;
        onPrepareShow(bundle, i);
        if(!mWindowVisible)
            ensureWindowAdded();
        onShow(bundle, i);
        if(!mWindowVisible)
        {
            mWindowVisible = true;
            if(mUiEnabled)
                mWindow.show();
        }
        if(ivoiceinteractionsessionshowcallback == null) goto _L2; else goto _L1
_L1:
        if(!mUiEnabled) goto _L4; else goto _L3
_L3:
        mRootView.invalidate();
        bundle = mRootView.getViewTreeObserver();
        android.view.ViewTreeObserver.OnPreDrawListener onpredrawlistener = JVM INSTR new #18  <Class VoiceInteractionSession$4>;
        onpredrawlistener.this. _cls4();
        bundle.addOnPreDrawListener(onpredrawlistener);
_L2:
        mWindowWasVisible = true;
        mInShowWindow = false;
        return;
_L4:
        ivoiceinteractionsessionshowcallback.onShown();
          goto _L2
        bundle;
        Log.w("VoiceInteractionSession", "Error calling onShown", bundle);
          goto _L2
        bundle;
        mWindowWasVisible = true;
        mInShowWindow = false;
        throw bundle;
    }

    public void dump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        printwriter.print(s);
        printwriter.print("mToken=");
        printwriter.println(mToken);
        printwriter.print(s);
        printwriter.print("mTheme=#");
        printwriter.println(Integer.toHexString(mTheme));
        printwriter.print(s);
        printwriter.print("mUiEnabled=");
        printwriter.println(mUiEnabled);
        printwriter.print(" mInitialized=");
        printwriter.println(mInitialized);
        printwriter.print(s);
        printwriter.print("mWindowAdded=");
        printwriter.print(mWindowAdded);
        printwriter.print(" mWindowVisible=");
        printwriter.println(mWindowVisible);
        printwriter.print(s);
        printwriter.print("mWindowWasVisible=");
        printwriter.print(mWindowWasVisible);
        printwriter.print(" mInShowWindow=");
        printwriter.println(mInShowWindow);
        if(mActiveRequests.size() > 0)
        {
            printwriter.print(s);
            printwriter.println("Active requests:");
            String s1 = (new StringBuilder()).append(s).append("    ").toString();
            for(int i = 0; i < mActiveRequests.size(); i++)
            {
                Request request = (Request)mActiveRequests.valueAt(i);
                printwriter.print(s);
                printwriter.print("  #");
                printwriter.print(i);
                printwriter.print(": ");
                printwriter.println(request);
                request.dump(s1, filedescriptor, printwriter, as);
            }

        }
    }

    void ensureWindowAdded()
    {
        if(mUiEnabled && mWindowAdded ^ true)
        {
            mWindowAdded = true;
            ensureWindowCreated();
            View view = onCreateContentView();
            if(view != null)
                setContentView(view);
        }
    }

    void ensureWindowCreated()
    {
        if(mInitialized)
            return;
        if(!mUiEnabled)
        {
            throw new IllegalStateException("setUiEnabled is false");
        } else
        {
            mInitialized = true;
            mInflater = (LayoutInflater)mContext.getSystemService("layout_inflater");
            mWindow = new SoftInputWindow(mContext, "VoiceInteractionSession", mTheme, mCallbacks, this, mDispatcherState, 2031, 80, true);
            mWindow.getWindow().addFlags(0x1010100);
            mThemeAttrs = mContext.obtainStyledAttributes(android.R.styleable.VoiceInteractionSession);
            mRootView = mInflater.inflate(0x109011d, null);
            mRootView.setSystemUiVisibility(1792);
            mWindow.setContentView(mRootView);
            mRootView.getViewTreeObserver().addOnComputeInternalInsetsListener(mInsetsComputer);
            mContentFrame = (FrameLayout)mRootView.findViewById(0x1020002);
            mWindow.getWindow().setLayout(-1, -1);
            mWindow.setToken(mToken);
            return;
        }
    }

    void ensureWindowHidden()
    {
        if(mWindow != null)
            mWindow.hide();
    }

    public void finish()
    {
        if(mToken == null)
            throw new IllegalStateException("Can't call before onCreate()");
        mSystemService.finish(mToken);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public Context getContext()
    {
        return mContext;
    }

    public int getDisabledShowContext()
    {
        int i;
        try
        {
            i = mSystemService.getDisabledShowContext();
        }
        catch(RemoteException remoteexception)
        {
            return 0;
        }
        return i;
    }

    public LayoutInflater getLayoutInflater()
    {
        ensureWindowCreated();
        return mInflater;
    }

    public int getUserDisabledShowContext()
    {
        int i;
        try
        {
            i = mSystemService.getUserDisabledShowContext();
        }
        catch(RemoteException remoteexception)
        {
            return 0;
        }
        return i;
    }

    public Dialog getWindow()
    {
        ensureWindowCreated();
        return mWindow;
    }

    public void hide()
    {
        if(mToken == null)
            throw new IllegalStateException("Can't call before onCreate()");
        mSystemService.hideSessionFromSession(mToken);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    boolean isRequestActive(IBinder ibinder)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = mActiveRequests.containsKey(ibinder);
        this;
        JVM INSTR monitorexit ;
        return flag;
        ibinder;
        throw ibinder;
    }

    public void onAssistStructureFailure(Throwable throwable)
    {
    }

    public void onBackPressed()
    {
        hide();
    }

    public void onCancelRequest(Request request)
    {
    }

    public void onCloseSystemDialogs()
    {
        hide();
    }

    public void onComputeInsets(Insets insets)
    {
        insets.contentInsets.left = 0;
        insets.contentInsets.bottom = 0;
        insets.contentInsets.right = 0;
        View view = getWindow().getWindow().getDecorView();
        insets.contentInsets.top = view.getHeight();
        insets.touchableInsets = 0;
        insets.touchableRegion.setEmpty();
    }

    public void onConfigurationChanged(Configuration configuration)
    {
    }

    public void onCreate()
    {
        doOnCreate();
    }

    public View onCreateContentView()
    {
        return null;
    }

    public void onDestroy()
    {
    }

    public boolean[] onGetSupportedCommands(String as[])
    {
        return new boolean[as.length];
    }

    public void onHandleAssist(Bundle bundle, AssistStructure assiststructure, AssistContent assistcontent)
    {
    }

    public void onHandleAssistSecondary(Bundle bundle, AssistStructure assiststructure, AssistContent assistcontent, int i, int j)
    {
    }

    public void onHandleScreenshot(Bitmap bitmap)
    {
    }

    public void onHide()
    {
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        return false;
    }

    public boolean onKeyLongPress(int i, KeyEvent keyevent)
    {
        return false;
    }

    public boolean onKeyMultiple(int i, int j, KeyEvent keyevent)
    {
        return false;
    }

    public boolean onKeyUp(int i, KeyEvent keyevent)
    {
        return false;
    }

    public void onLockscreenShown()
    {
        hide();
    }

    public void onLowMemory()
    {
    }

    public void onPrepareShow(Bundle bundle, int i)
    {
    }

    public void onRequestAbortVoice(AbortVoiceRequest abortvoicerequest)
    {
    }

    public void onRequestCommand(CommandRequest commandrequest)
    {
    }

    public void onRequestCompleteVoice(CompleteVoiceRequest completevoicerequest)
    {
    }

    public void onRequestConfirmation(ConfirmationRequest confirmationrequest)
    {
    }

    public void onRequestPickOption(PickOptionRequest pickoptionrequest)
    {
    }

    public void onShow(Bundle bundle, int i)
    {
    }

    public void onTaskFinished(Intent intent, int i)
    {
        hide();
    }

    public void onTaskStarted(Intent intent, int i)
    {
    }

    public void onTrimMemory(int i)
    {
    }

    Request removeRequest(IBinder ibinder)
    {
        this;
        JVM INSTR monitorenter ;
        ibinder = (Request)mActiveRequests.remove(ibinder);
        this;
        JVM INSTR monitorexit ;
        return ibinder;
        ibinder;
        throw ibinder;
    }

    public void setContentView(View view)
    {
        ensureWindowCreated();
        mContentFrame.removeAllViews();
        mContentFrame.addView(view, new android.widget.FrameLayout.LayoutParams(-1, -1));
        mContentFrame.requestApplyInsets();
    }

    public void setDisabledShowContext(int i)
    {
        mSystemService.setDisabledShowContext(i);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void setKeepAwake(boolean flag)
    {
        if(mToken == null)
            throw new IllegalStateException("Can't call before onCreate()");
        mSystemService.setKeepAwake(mToken, flag);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void setTheme(int i)
    {
        if(mWindow != null)
        {
            throw new IllegalStateException("Must be called before onCreate()");
        } else
        {
            mTheme = i;
            return;
        }
    }

    public void setUiEnabled(boolean flag)
    {
        if(mUiEnabled != flag)
        {
            mUiEnabled = flag;
            if(mWindowVisible)
                if(flag)
                {
                    ensureWindowAdded();
                    mWindow.show();
                } else
                {
                    ensureWindowHidden();
                }
        }
    }

    public void show(Bundle bundle, int i)
    {
        if(mToken == null)
            throw new IllegalStateException("Can't call before onCreate()");
        mSystemService.showSessionFromSession(mToken, bundle, i);
_L2:
        return;
        bundle;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void startAssistantActivity(Intent intent)
    {
        if(mToken == null)
            throw new IllegalStateException("Can't call before onCreate()");
        intent.migrateExtraStreamToClipData();
        intent.prepareToLeaveProcess(mContext);
        Instrumentation.checkStartActivityResult(mSystemService.startAssistantActivity(mToken, intent, intent.resolveType(mContext.getContentResolver())), intent);
_L2:
        return;
        intent;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void startVoiceActivity(Intent intent)
    {
        if(mToken == null)
            throw new IllegalStateException("Can't call before onCreate()");
        intent.migrateExtraStreamToClipData();
        intent.prepareToLeaveProcess(mContext);
        Instrumentation.checkStartActivityResult(mSystemService.startVoiceActivity(mToken, intent, intent.resolveType(mContext.getContentResolver())), intent);
_L2:
        return;
        intent;
        if(true) goto _L2; else goto _L1
_L1:
    }

    static final boolean DEBUG = false;
    public static final String KEY_CONTENT = "content";
    public static final String KEY_DATA = "data";
    public static final String KEY_RECEIVER_EXTRAS = "receiverExtras";
    public static final String KEY_STRUCTURE = "structure";
    static final int MSG_CANCEL = 7;
    static final int MSG_CLOSE_SYSTEM_DIALOGS = 102;
    static final int MSG_DESTROY = 103;
    static final int MSG_HANDLE_ASSIST = 104;
    static final int MSG_HANDLE_SCREENSHOT = 105;
    static final int MSG_HIDE = 107;
    static final int MSG_ON_LOCKSCREEN_SHOWN = 108;
    static final int MSG_SHOW = 106;
    static final int MSG_START_ABORT_VOICE = 4;
    static final int MSG_START_COMMAND = 5;
    static final int MSG_START_COMPLETE_VOICE = 3;
    static final int MSG_START_CONFIRMATION = 1;
    static final int MSG_START_PICK_OPTION = 2;
    static final int MSG_SUPPORTS_COMMANDS = 6;
    static final int MSG_TASK_FINISHED = 101;
    static final int MSG_TASK_STARTED = 100;
    public static final int SHOW_SOURCE_ACTIVITY = 16;
    public static final int SHOW_SOURCE_APPLICATION = 8;
    public static final int SHOW_SOURCE_ASSIST_GESTURE = 4;
    public static final int SHOW_WITH_ASSIST = 1;
    public static final int SHOW_WITH_SCREENSHOT = 2;
    static final String TAG = "VoiceInteractionSession";
    final ArrayMap mActiveRequests;
    final MyCallbacks mCallbacks;
    FrameLayout mContentFrame;
    final Context mContext;
    final android.view.KeyEvent.DispatcherState mDispatcherState;
    final HandlerCaller mHandlerCaller;
    boolean mInShowWindow;
    LayoutInflater mInflater;
    boolean mInitialized;
    final android.view.ViewTreeObserver.OnComputeInternalInsetsListener mInsetsComputer;
    final IVoiceInteractor mInteractor;
    View mRootView;
    final IVoiceInteractionSession mSession;
    IVoiceInteractionManagerService mSystemService;
    int mTheme;
    TypedArray mThemeAttrs;
    final Insets mTmpInsets;
    IBinder mToken;
    boolean mUiEnabled;
    final WeakReference mWeakRef;
    SoftInputWindow mWindow;
    boolean mWindowAdded;
    boolean mWindowVisible;
    boolean mWindowWasVisible;

    // Unreferenced inner class android/service/voice/VoiceInteractionSession$4

/* anonymous class */
    class _cls4
        implements android.view.ViewTreeObserver.OnPreDrawListener
    {

        public boolean onPreDraw()
        {
            mRootView.getViewTreeObserver().removeOnPreDrawListener(this);
            try
            {
                showCallback.onShown();
            }
            catch(RemoteException remoteexception)
            {
                Log.w("VoiceInteractionSession", "Error calling onShown", remoteexception);
            }
            return true;
        }

        final VoiceInteractionSession this$0;
        final IVoiceInteractionSessionShowCallback val$showCallback;

            
            {
                this$0 = VoiceInteractionSession.this;
                showCallback = ivoiceinteractionsessionshowcallback;
                super();
            }
    }


    // Unreferenced inner class android/service/voice/VoiceInteractionSession$Request$1

/* anonymous class */
    class Request._cls1 extends com.android.internal.app.IVoiceInteractorRequest.Stub
    {

        public void cancel()
            throws RemoteException
        {
            VoiceInteractionSession voiceinteractionsession = (VoiceInteractionSession)mSession.get();
            if(voiceinteractionsession != null)
                voiceinteractionsession.mHandlerCaller.sendMessage(voiceinteractionsession.mHandlerCaller.obtainMessageO(7, Request.this));
        }

        final Request this$1;

            
            {
                this$1 = Request.this;
                super();
            }
    }

}
