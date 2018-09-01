// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.inputmethodservice;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.*;
import android.util.Log;
import android.view.InputChannel;
import android.view.inputmethod.*;
import com.android.internal.os.HandlerCaller;
import com.android.internal.os.SomeArgs;
import com.android.internal.view.*;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

// Referenced classes of package android.inputmethodservice:
//            AbstractInputMethodService, IInputMethodSessionWrapper

class IInputMethodWrapper extends com.android.internal.view.IInputMethod.Stub
    implements com.android.internal.os.HandlerCaller.Callback
{
    static final class InputMethodSessionCallbackWrapper
        implements android.view.inputmethod.InputMethod.SessionCallback
    {

        public void sessionCreated(InputMethodSession inputmethodsession)
        {
            if(inputmethodsession == null)
                break MISSING_BLOCK_LABEL_32;
            IInputMethodSessionWrapper iinputmethodsessionwrapper = JVM INSTR new #33  <Class IInputMethodSessionWrapper>;
            iinputmethodsessionwrapper.IInputMethodSessionWrapper(mContext, inputmethodsession, mChannel);
            mCb.sessionCreated(iinputmethodsessionwrapper);
_L1:
            return;
            try
            {
                if(mChannel != null)
                    mChannel.dispose();
                mCb.sessionCreated(null);
            }
            // Misplaced declaration of an exception variable
            catch(InputMethodSession inputmethodsession) { }
              goto _L1
        }

        final IInputSessionCallback mCb;
        final InputChannel mChannel;
        final Context mContext;

        InputMethodSessionCallbackWrapper(Context context, InputChannel inputchannel, IInputSessionCallback iinputsessioncallback)
        {
            mContext = context;
            mChannel = inputchannel;
            mCb = iinputsessioncallback;
        }
    }

    static class Notifier
    {

        boolean notified;

        Notifier()
        {
        }
    }


    public IInputMethodWrapper(AbstractInputMethodService abstractinputmethodservice, InputMethod inputmethod)
    {
        mTarget = new WeakReference(abstractinputmethodservice);
        mContext = abstractinputmethodservice.getApplicationContext();
        mCaller = new HandlerCaller(mContext, null, this, true);
        mInputMethod = new WeakReference(inputmethod);
        mTargetSdkVersion = abstractinputmethodservice.getApplicationInfo().targetSdkVersion;
    }

    public void attachToken(IBinder ibinder)
    {
        mCaller.executeOrSendMessage(mCaller.obtainMessageO(10, ibinder));
    }

    public void bindInput(InputBinding inputbinding)
    {
        inputbinding = new InputBinding(new InputConnectionWrapper(mTarget, com.android.internal.view.IInputContext.Stub.asInterface(inputbinding.getConnectionToken()), 0), inputbinding);
        mCaller.executeOrSendMessage(mCaller.obtainMessageO(20, inputbinding));
    }

    public void changeInputMethodSubtype(InputMethodSubtype inputmethodsubtype)
    {
        mCaller.executeOrSendMessage(mCaller.obtainMessageO(80, inputmethodsubtype));
    }

    public void createSession(InputChannel inputchannel, IInputSessionCallback iinputsessioncallback)
    {
        mCaller.executeOrSendMessage(mCaller.obtainMessageOO(40, inputchannel, iinputsessioncallback));
    }

    protected void dump(FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        Object obj;
        obj = (AbstractInputMethodService)mTarget.get();
        if(obj == null)
            return;
        if(((AbstractInputMethodService) (obj)).checkCallingOrSelfPermission("android.permission.DUMP") != 0)
        {
            printwriter.println((new StringBuilder()).append("Permission Denial: can't dump InputMethodManager from from pid=").append(Binder.getCallingPid()).append(", uid=").append(Binder.getCallingUid()).toString());
            return;
        }
        obj = new CountDownLatch(1);
        mCaller.executeOrSendMessage(mCaller.obtainMessageOOOO(1, filedescriptor, printwriter, as, obj));
        if(!((CountDownLatch) (obj)).await(5L, TimeUnit.SECONDS))
            printwriter.println("Timeout waiting for dump");
_L1:
        return;
        filedescriptor;
        printwriter.println("Interrupted waiting for dump");
          goto _L1
    }

    public void executeMessage(Message message)
    {
        boolean flag;
        Object obj;
        flag = true;
        obj = (InputMethod)mInputMethod.get();
        if(obj == null && message.what != 1)
        {
            Log.w("InputMethodWrapper", (new StringBuilder()).append("Input method reference was null, ignoring message: ").append(message.what).toString());
            return;
        }
        message.what;
        JVM INSTR lookupswitch 11: default 156
    //                   1: 185
    //                   10: 296
    //                   20: 310
    //                   30: 324
    //                   32: 331
    //                   40: 440
    //                   45: 484
    //                   50: 515
    //                   60: 529
    //                   70: 547
    //                   80: 565;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12
_L1:
        Log.w("InputMethodWrapper", (new StringBuilder()).append("Unhandled message code: ").append(message.what).toString());
        return;
_L2:
        obj = (AbstractInputMethodService)mTarget.get();
        if(obj == null)
            return;
        message = (SomeArgs)message.obj;
        try
        {
            ((AbstractInputMethodService) (obj)).dump((FileDescriptor)((SomeArgs) (message)).arg1, (PrintWriter)((SomeArgs) (message)).arg2, (String[])((SomeArgs) (message)).arg3);
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            ((PrintWriter)((SomeArgs) (message)).arg2).println((new StringBuilder()).append("Exception: ").append(obj).toString());
        }
        obj = ((SomeArgs) (message)).arg4;
        obj;
        JVM INSTR monitorenter ;
        ((CountDownLatch)((SomeArgs) (message)).arg4).countDown();
        obj;
        JVM INSTR monitorexit ;
        message.recycle();
        return;
        message;
        throw message;
_L3:
        ((InputMethod) (obj)).attachToken((IBinder)message.obj);
        return;
_L4:
        ((InputMethod) (obj)).bindInput((InputBinding)message.obj);
        return;
_L5:
        ((InputMethod) (obj)).unbindInput();
        return;
_L6:
        SomeArgs someargs = (SomeArgs)message.obj;
        int i = message.arg1;
        IBinder ibinder;
        EditorInfo editorinfo;
        if(message.arg2 != 0)
            flag = true;
        else
            flag = false;
        ibinder = (IBinder)someargs.arg1;
        message = (IInputContext)someargs.arg2;
        editorinfo = (EditorInfo)someargs.arg3;
        if(message != null)
            message = new InputConnectionWrapper(mTarget, message, i);
        else
            message = null;
        editorinfo.makeCompatible(mTargetSdkVersion);
        ((InputMethod) (obj)).dispatchStartInputWithToken(message, editorinfo, flag, ibinder);
        someargs.recycle();
        return;
_L7:
        message = (SomeArgs)message.obj;
        ((InputMethod) (obj)).createSession(new InputMethodSessionCallbackWrapper(mContext, (InputChannel)((SomeArgs) (message)).arg1, (IInputSessionCallback)((SomeArgs) (message)).arg2));
        message.recycle();
        return;
_L8:
        InputMethodSession inputmethodsession = (InputMethodSession)message.obj;
        if(message.arg1 == 0)
            flag = false;
        ((InputMethod) (obj)).setSessionEnabled(inputmethodsession, flag);
        return;
_L9:
        ((InputMethod) (obj)).revokeSession((InputMethodSession)message.obj);
        return;
_L10:
        ((InputMethod) (obj)).showSoftInput(message.arg1, (ResultReceiver)message.obj);
        return;
_L11:
        ((InputMethod) (obj)).hideSoftInput(message.arg1, (ResultReceiver)message.obj);
        return;
_L12:
        ((InputMethod) (obj)).changeInputMethodSubtype((InputMethodSubtype)message.obj);
        return;
    }

    public InputMethod getInternalInputMethod()
    {
        return (InputMethod)mInputMethod.get();
    }

    public void hideSoftInput(int i, ResultReceiver resultreceiver)
    {
        mCaller.executeOrSendMessage(mCaller.obtainMessageIO(70, i, resultreceiver));
    }

    public void revokeSession(IInputMethodSession iinputmethodsession)
    {
        Object obj = ((IInputMethodSessionWrapper)iinputmethodsession).getInternalInputMethodSession();
        if(obj == null)
        {
            try
            {
                obj = JVM INSTR new #146 <Class StringBuilder>;
                ((StringBuilder) (obj)).StringBuilder();
                Log.w("InputMethodWrapper", ((StringBuilder) (obj)).append("Session is already finished: ").append(iinputmethodsession).toString());
                return;
            }
            catch(ClassCastException classcastexception)
            {
                Log.w("InputMethodWrapper", (new StringBuilder()).append("Incoming session not of correct type: ").append(iinputmethodsession).toString(), classcastexception);
            }
            break MISSING_BLOCK_LABEL_58;
        }
        mCaller.executeOrSendMessage(mCaller.obtainMessageO(50, obj));
    }

    public void setSessionEnabled(IInputMethodSession iinputmethodsession, boolean flag)
    {
        InputMethodSession inputmethodsession = ((IInputMethodSessionWrapper)iinputmethodsession).getInternalInputMethodSession();
        if(inputmethodsession == null)
        {
            HandlerCaller handlercaller;
            HandlerCaller handlercaller1;
            int i;
            try
            {
                StringBuilder stringbuilder = JVM INSTR new #146 <Class StringBuilder>;
                stringbuilder.StringBuilder();
                Log.w("InputMethodWrapper", stringbuilder.append("Session is already finished: ").append(iinputmethodsession).toString());
                return;
            }
            catch(ClassCastException classcastexception)
            {
                Log.w("InputMethodWrapper", (new StringBuilder()).append("Incoming session not of correct type: ").append(iinputmethodsession).toString(), classcastexception);
            }
            break MISSING_BLOCK_LABEL_78;
        }
        handlercaller = mCaller;
        handlercaller1 = mCaller;
        if(flag)
            i = 1;
        else
            i = 0;
        handlercaller.executeOrSendMessage(handlercaller1.obtainMessageIO(45, i, inputmethodsession));
    }

    public void showSoftInput(int i, ResultReceiver resultreceiver)
    {
        mCaller.executeOrSendMessage(mCaller.obtainMessageIO(60, i, resultreceiver));
    }

    public void startInput(IBinder ibinder, IInputContext iinputcontext, int i, EditorInfo editorinfo, boolean flag)
    {
        HandlerCaller handlercaller = mCaller;
        HandlerCaller handlercaller1 = mCaller;
        int j;
        if(flag)
            j = 1;
        else
            j = 0;
        handlercaller.executeOrSendMessage(handlercaller1.obtainMessageIIOOO(32, i, j, ibinder, iinputcontext, editorinfo));
    }

    public void unbindInput()
    {
        mCaller.executeOrSendMessage(mCaller.obtainMessage(30));
    }

    private static final int DO_ATTACH_TOKEN = 10;
    private static final int DO_CHANGE_INPUTMETHOD_SUBTYPE = 80;
    private static final int DO_CREATE_SESSION = 40;
    private static final int DO_DUMP = 1;
    private static final int DO_HIDE_SOFT_INPUT = 70;
    private static final int DO_REVOKE_SESSION = 50;
    private static final int DO_SET_INPUT_CONTEXT = 20;
    private static final int DO_SET_SESSION_ENABLED = 45;
    private static final int DO_SHOW_SOFT_INPUT = 60;
    private static final int DO_START_INPUT = 32;
    private static final int DO_UNSET_INPUT_CONTEXT = 30;
    private static final String TAG = "InputMethodWrapper";
    final HandlerCaller mCaller;
    final Context mContext;
    final WeakReference mInputMethod;
    final WeakReference mTarget;
    final int mTargetSdkVersion;
}
