// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os.statistics;

import org.json.JSONObject;

// Referenced classes of package android.os.statistics:
//            SingleJniMethod, LooperOnce, SingleJankRecord, Dex2oatEvent, 
//            PerfEvent, JniParcel, NativeBackTrace

public class PerfEventFactory
{

    public PerfEventFactory()
    {
    }

    public static PerfEvent createPerfEvent(int i)
    {
        Object obj1;
        Object obj = null;
        if(i >= 0x10000)
            i -= 65524;
        obj1 = obj;
        i;
        JVM INSTR tableswitch 0 16: default 92
    //                   0 104
    //                   1 115
    //                   2 126
    //                   3 137
    //                   4 148
    //                   5 159
    //                   6 170
    //                   7 181
    //                   8 192
    //                   9 203
    //                   10 214
    //                   11 225
    //                   12 236
    //                   13 94
    //                   14 247
    //                   15 258
    //                   16 269;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18
_L15:
        break; /* Loop/switch isn't completed */
_L1:
        obj1 = obj;
_L20:
        return ((PerfEvent) (obj1));
_L2:
        obj1 = new MonitorSuperviser.SingleLockHold();
        continue; /* Loop/switch isn't completed */
_L3:
        obj1 = new MonitorSuperviser.SingleLockWait();
        continue; /* Loop/switch isn't completed */
_L4:
        obj1 = new MonitorSuperviser.SingleConditionAwaken();
        continue; /* Loop/switch isn't completed */
_L5:
        obj1 = new MonitorSuperviser.SingleConditionWait();
        continue; /* Loop/switch isn't completed */
_L6:
        obj1 = new BinderSuperviser.SingleBinderCall();
        continue; /* Loop/switch isn't completed */
_L7:
        obj1 = new BinderSuperviser.SingleBinderExecution();
        continue; /* Loop/switch isn't completed */
_L8:
        obj1 = new PerfTracer.SingleTracePoint();
        continue; /* Loop/switch isn't completed */
_L9:
        obj1 = new SystemTraceSuperviser.SingleSystemTraceEvent();
        continue; /* Loop/switch isn't completed */
_L10:
        obj1 = new LooperMessageSuperviser.SingleLooperMessage();
        continue; /* Loop/switch isn't completed */
_L11:
        obj1 = new InputEventSuperviser.SingleInputEvent();
        continue; /* Loop/switch isn't completed */
_L12:
        obj1 = new SingleJniMethod();
        continue; /* Loop/switch isn't completed */
_L13:
        obj1 = new LooperOnce();
        continue; /* Loop/switch isn't completed */
_L14:
        obj1 = new EventLogSuperviser.SingleEventLogItem();
        continue; /* Loop/switch isn't completed */
_L16:
        obj1 = new SingleJankRecord();
        continue; /* Loop/switch isn't completed */
_L17:
        obj1 = new Dex2oatEvent();
        continue; /* Loop/switch isn't completed */
_L18:
        obj1 = new BinderSuperviser.BinderStarvation();
        if(true) goto _L20; else goto _L19
_L19:
    }

    public static PerfEvent createPerfEvent(int i, JniParcel jniparcel, Object obj, NativeBackTrace nativebacktrace)
    {
        PerfEvent perfevent = createPerfEvent(i);
        if(perfevent != null)
            perfevent.fillIn(jniparcel, obj, nativebacktrace);
        return perfevent;
    }

    public static PerfEvent createPerfEvent(JSONObject jsonobject)
    {
        int i = PerfEvent.getPerfEventType(jsonobject);
        if(i == -1)
            return null;
        BinderSuperviser.BinderStarvation binderstarvation;
        switch(i)
        {
        default:
            switch(i)
            {
            case 65537: 
            default:
                throw new IllegalArgumentException();

            case 65536: 
                EventLogSuperviser.SingleEventLogItem singleeventlogitem = new EventLogSuperviser.SingleEventLogItem();
                singleeventlogitem.readFromJson(jsonobject);
                return singleeventlogitem;

            case 65538: 
                SingleJankRecord singlejankrecord = new SingleJankRecord();
                singlejankrecord.readFromJson(jsonobject);
                return singlejankrecord;

            case 65539: 
                Dex2oatEvent dex2oatevent = new Dex2oatEvent();
                dex2oatevent.readFromJson(jsonobject);
                return dex2oatevent;

            case 65540: 
                binderstarvation = new BinderSuperviser.BinderStarvation();
                break;
            }
            break;

        case 0: // '\0'
            MonitorSuperviser.SingleLockHold singlelockhold = new MonitorSuperviser.SingleLockHold();
            singlelockhold.readFromJson(jsonobject);
            return singlelockhold;

        case 1: // '\001'
            MonitorSuperviser.SingleLockWait singlelockwait = new MonitorSuperviser.SingleLockWait();
            singlelockwait.readFromJson(jsonobject);
            return singlelockwait;

        case 2: // '\002'
            MonitorSuperviser.SingleConditionAwaken singleconditionawaken = new MonitorSuperviser.SingleConditionAwaken();
            singleconditionawaken.readFromJson(jsonobject);
            return singleconditionawaken;

        case 3: // '\003'
            MonitorSuperviser.SingleConditionWait singleconditionwait = new MonitorSuperviser.SingleConditionWait();
            singleconditionwait.readFromJson(jsonobject);
            return singleconditionwait;

        case 4: // '\004'
            BinderSuperviser.SingleBinderCall singlebindercall = new BinderSuperviser.SingleBinderCall();
            singlebindercall.readFromJson(jsonobject);
            return singlebindercall;

        case 5: // '\005'
            BinderSuperviser.SingleBinderExecution singlebinderexecution = new BinderSuperviser.SingleBinderExecution();
            singlebinderexecution.readFromJson(jsonobject);
            return singlebinderexecution;

        case 6: // '\006'
            PerfTracer.SingleTracePoint singletracepoint = new PerfTracer.SingleTracePoint();
            singletracepoint.readFromJson(jsonobject);
            return singletracepoint;

        case 7: // '\007'
            SystemTraceSuperviser.SingleSystemTraceEvent singlesystemtraceevent = new SystemTraceSuperviser.SingleSystemTraceEvent();
            singlesystemtraceevent.readFromJson(jsonobject);
            return singlesystemtraceevent;

        case 8: // '\b'
            LooperMessageSuperviser.SingleLooperMessage singleloopermessage = new LooperMessageSuperviser.SingleLooperMessage();
            singleloopermessage.readFromJson(jsonobject);
            return singleloopermessage;

        case 9: // '\t'
            InputEventSuperviser.SingleInputEvent singleinputevent = new InputEventSuperviser.SingleInputEvent();
            singleinputevent.readFromJson(jsonobject);
            return singleinputevent;

        case 10: // '\n'
            SingleJniMethod singlejnimethod = new SingleJniMethod();
            singlejnimethod.readFromJson(jsonobject);
            return singlejnimethod;

        case 11: // '\013'
            LooperOnce looperonce = new LooperOnce();
            looperonce.readFromJson(jsonobject);
            return looperonce;
        }
        binderstarvation.readFromJson(jsonobject);
        return binderstarvation;
    }

    private static final int MACRO_EVENT_TYPE_INDEX_OFFSET = -65524;
}
