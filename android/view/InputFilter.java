// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.os.*;

// Referenced classes of package android.view:
//            InputEventConsistencyVerifier, IInputFilterHost, InputEvent

public abstract class InputFilter extends IInputFilter.Stub
{
    private final class H extends Handler
    {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 3: default 32
        //                       1 33
        //                       2 98
        //                       3 129;
               goto _L1 _L2 _L3 _L4
_L1:
            return;
_L2:
            InputFilter._2D_set0(InputFilter.this, (IInputFilterHost)message.obj);
            if(InputFilter._2D_get0(InputFilter.this) != null)
                InputFilter._2D_get0(InputFilter.this).reset();
            if(InputFilter._2D_get1(InputFilter.this) != null)
                InputFilter._2D_get1(InputFilter.this).reset();
            onInstalled();
              goto _L1
_L3:
            onUninstalled();
            InputFilter._2D_set0(InputFilter.this, null);
              goto _L1
            message;
            InputFilter._2D_set0(InputFilter.this, null);
            throw message;
_L4:
            InputEvent inputevent = (InputEvent)message.obj;
            if(InputFilter._2D_get0(InputFilter.this) != null)
                InputFilter._2D_get0(InputFilter.this).onInputEvent(inputevent, 0);
            onInputEvent(inputevent, message.arg1);
            inputevent.recycle();
              goto _L1
            message;
            inputevent.recycle();
            throw message;
        }

        final InputFilter this$0;

        public H(Looper looper)
        {
            this$0 = InputFilter.this;
            super(looper);
        }
    }


    static InputEventConsistencyVerifier _2D_get0(InputFilter inputfilter)
    {
        return inputfilter.mInboundInputEventConsistencyVerifier;
    }

    static InputEventConsistencyVerifier _2D_get1(InputFilter inputfilter)
    {
        return inputfilter.mOutboundInputEventConsistencyVerifier;
    }

    static IInputFilterHost _2D_set0(InputFilter inputfilter, IInputFilterHost iinputfilterhost)
    {
        inputfilter.mHost = iinputfilterhost;
        return iinputfilterhost;
    }

    public InputFilter(Looper looper)
    {
        Object obj = null;
        super();
        InputEventConsistencyVerifier inputeventconsistencyverifier;
        if(InputEventConsistencyVerifier.isInstrumentationEnabled())
            inputeventconsistencyverifier = new InputEventConsistencyVerifier(this, 1, "InputFilter#InboundInputEventConsistencyVerifier");
        else
            inputeventconsistencyverifier = null;
        mInboundInputEventConsistencyVerifier = inputeventconsistencyverifier;
        inputeventconsistencyverifier = obj;
        if(InputEventConsistencyVerifier.isInstrumentationEnabled())
            inputeventconsistencyverifier = new InputEventConsistencyVerifier(this, 1, "InputFilter#OutboundInputEventConsistencyVerifier");
        mOutboundInputEventConsistencyVerifier = inputeventconsistencyverifier;
        mH = new H(looper);
    }

    public final void filterInputEvent(InputEvent inputevent, int i)
    {
        mH.obtainMessage(3, i, 0, inputevent).sendToTarget();
    }

    public final void install(IInputFilterHost iinputfilterhost)
    {
        mH.obtainMessage(1, iinputfilterhost).sendToTarget();
    }

    public void onInputEvent(InputEvent inputevent, int i)
    {
        sendInputEvent(inputevent, i);
    }

    public void onInstalled()
    {
    }

    public void onUninstalled()
    {
    }

    public void sendInputEvent(InputEvent inputevent, int i)
    {
        if(inputevent == null)
            throw new IllegalArgumentException("event must not be null");
        if(mHost == null)
            throw new IllegalStateException("Cannot send input event because the input filter is not installed.");
        if(mOutboundInputEventConsistencyVerifier != null)
            mOutboundInputEventConsistencyVerifier.onInputEvent(inputevent, 0);
        mHost.sendInputEvent(inputevent, i);
_L2:
        return;
        inputevent;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public final void uninstall()
    {
        mH.obtainMessage(2).sendToTarget();
    }

    private static final int MSG_INPUT_EVENT = 3;
    private static final int MSG_INSTALL = 1;
    private static final int MSG_UNINSTALL = 2;
    private final H mH;
    private IInputFilterHost mHost;
    private final InputEventConsistencyVerifier mInboundInputEventConsistencyVerifier;
    private final InputEventConsistencyVerifier mOutboundInputEventConsistencyVerifier;
}
