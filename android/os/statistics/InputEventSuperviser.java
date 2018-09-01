// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os.statistics;

import android.os.Parcel;
import android.view.*;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package android.os.statistics:
//            PerfSupervisionSettings, MicroscopicEvent, JniParcel, OsUtils, 
//            PerfEvent, NativeBackTrace

public class InputEventSuperviser
{
    public static class SingleInputEvent extends MicroscopicEvent
    {

        private static String compactChannel(String s)
        {
label0:
            {
                if(s.equals("uninitialized"))
                    return "uninitialized";
                try
                {
                    String as[] = s.split(" ");
                    if(as.length < 2)
                        break label0;
                    s = as[as.length - 2];
                }
                // Misplaced declaration of an exception variable
                catch(String s)
                {
                    return "null";
                }
                return s;
            }
            return s;
        }

        private void copyFrom(SingleInputEvent singleinputevent)
        {
            super.copyFrom(singleinputevent);
            inputEventStage = singleinputevent.inputEventStage;
            inputEventSequenceNumber = singleinputevent.inputEventSequenceNumber;
            inputEventTime = singleinputevent.inputEventTime;
            inputEventDescription = singleinputevent.inputEventDescription;
            inputChannelName = singleinputevent.inputChannelName;
            targetReceiverName = singleinputevent.targetReceiverName;
            inputEventType = singleinputevent.inputEventType;
            inputEventAction = singleinputevent.inputEventAction;
            inputEventCode = singleinputevent.inputEventCode;
            targetReceiver = singleinputevent.targetReceiver;
            inputChannel = singleinputevent.inputChannel;
        }

        public void copyFrom(PerfEvent perfevent)
        {
            copyFrom((SingleInputEvent)perfevent);
        }

        void fillIn(JniParcel jniparcel, Object obj, NativeBackTrace nativebacktrace)
        {
            threadId = (int)jniparcel.readLong();
            threadName = jniparcel.readString();
            beginUptimeMillis = jniparcel.readLong();
            endUptimeMillis = jniparcel.readLong();
            schedPolicy = OsUtils.decodeThreadSchedulePolicy((int)jniparcel.readLong());
            schedPriority = (int)jniparcel.readLong();
            schedGroup = (int)jniparcel.readLong();
            runningTimeMs = jniparcel.readLong();
            runnableTimeMs = jniparcel.readLong();
            sleepingTimeMs = jniparcel.readLong();
            endRealTimeMs = jniparcel.readLong();
            inputEventStage = (int)jniparcel.readLong();
            inputEventSequenceNumber = (int)jniparcel.readLong();
            inputEventTime = jniparcel.readLong();
            inputEventType = (int)jniparcel.readLong();
            inputEventAction = (int)jniparcel.readLong();
            inputEventCode = (int)jniparcel.readLong();
            targetReceiver = (InputEventReceiver)jniparcel.readObject();
            inputChannel = (InputChannel)jniparcel.readObject();
        }

        public boolean hasMultiplePeerBlockingEvents()
        {
            return false;
        }

        public boolean hasPeerBlockingEvent()
        {
            return false;
        }

        public boolean isBlockedBy(MicroscopicEvent microscopicevent)
        {
            return false;
        }

        public boolean isBlockedBySameProcess()
        {
            return false;
        }

        public boolean isBlockingMultiplePeer()
        {
            return false;
        }

        public boolean isBlockingSameProcess()
        {
            return false;
        }

        public boolean isPeerBlockingEvent()
        {
            return false;
        }

        public boolean isRootEvent()
        {
            return true;
        }

        boolean isUserPerceptible()
        {
            return true;
        }

        public void readFromJson(JSONObject jsonobject)
        {
            super.readFromJson(jsonobject);
            inputEventStage = jsonobject.optInt("inputEventStage", 0);
            inputEventSequenceNumber = jsonobject.optInt("inputEventSequenceNumber", 0);
            inputEventTime = jsonobject.optLong("inputEventTime", 0L);
            inputEventDescription = jsonobject.optString("inputEventDescription", "");
            inputChannelName = jsonobject.optString("inputChannel", "");
            targetReceiverName = jsonobject.optString("targetReceiver", "");
            inputEventType = -1;
            inputEventAction = 0;
            inputEventCode = 0;
            targetReceiver = null;
            inputChannel = null;
        }

        public void readFromParcel(Parcel parcel)
        {
            super.readFromParcel(parcel);
            inputEventStage = parcel.readInt();
            inputEventSequenceNumber = parcel.readInt();
            inputEventTime = parcel.readLong();
            inputEventDescription = parcel.readString();
            inputChannelName = parcel.readString();
            targetReceiverName = parcel.readString();
            inputEventType = -1;
            inputEventAction = 0;
            inputEventCode = 0;
            targetReceiver = null;
            inputChannel = null;
        }

        public void reset()
        {
            super.reset();
            inputEventStage = 0;
            inputEventSequenceNumber = 0;
            inputEventTime = 0L;
            inputEventDescription = "";
            inputChannelName = "";
            targetReceiverName = "";
            inputEventType = -1;
            inputEventAction = 0;
            inputEventCode = 0;
            targetReceiver = null;
            inputChannel = null;
        }

        void resolveLazyInfo()
        {
            if(lazyInfoResolved)
                return;
            super.resolveLazyInfo();
            StringBuilder stringbuilder = new StringBuilder();
            if(inputEventType == 0)
            {
                stringbuilder.append("KeyEvent { action=").append(KeyEvent.actionToString(inputEventAction));
                stringbuilder.append(", keyCode=").append(KeyEvent.keyCodeToString(inputEventCode));
                stringbuilder.append(" }");
            } else
            {
                stringbuilder.append("MotionEvent { action=").append(MotionEvent.actionToString(inputEventAction));
                stringbuilder.append(" }");
            }
            inputEventDescription = stringbuilder.toString();
            if(targetReceiver != null)
                targetReceiverName = targetReceiver.getClass().getName();
            else
                targetReceiverName = "";
            if(inputChannel != null)
                inputChannelName = compactChannel(inputChannel.getName());
            else
                inputChannelName = "";
            inputEventType = -1;
            inputEventAction = 0;
            inputEventCode = 0;
            targetReceiver = null;
            inputChannel = null;
        }

        public void writeToJson(JSONObject jsonobject)
        {
            super.writeToJson(jsonobject);
            jsonobject.put("inputEventStage", inputEventStage);
            jsonobject.put("inputEventSequenceNumber", inputEventSequenceNumber);
            jsonobject.put("inputEventTime", inputEventTime);
            jsonobject.put("inputEventDescription", inputEventDescription);
            jsonobject.put("inputChannel", inputChannelName);
            jsonobject.put("targetReceiver", targetReceiverName);
_L2:
            return;
            jsonobject;
            if(true) goto _L2; else goto _L1
_L1:
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeInt(inputEventStage);
            parcel.writeInt(inputEventSequenceNumber);
            parcel.writeLong(inputEventTime);
            parcel.writeString(inputEventDescription);
            parcel.writeString(inputChannelName);
            parcel.writeString(targetReceiverName);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public SingleInputEvent createFromParcel(Parcel parcel)
            {
                SingleInputEvent singleinputevent = new SingleInputEvent();
                singleinputevent.readFromParcel(parcel);
                return singleinputevent;
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public SingleInputEvent[] newArray(int i)
            {
                return new SingleInputEvent[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private static final String FIELD_INPUT_CHANNEL = "inputChannel";
        private static final String FIELD_INPUT_EVENT_DESCRIPTION = "inputEventDescription";
        private static final String FIELD_INPUT_EVENT_SEQUENCE_NUMBER = "inputEventSequenceNumber";
        private static final String FIELD_INPUT_EVENT_STAGE = "inputEventStage";
        private static final String FIELD_INPUT_EVENT_TIME = "inputEventTime";
        private static final String FIELD_TARGET_RECEIVER = "targetReceiver";
        private InputChannel inputChannel;
        public String inputChannelName;
        private int inputEventAction;
        private int inputEventCode;
        public String inputEventDescription;
        public int inputEventSequenceNumber;
        public int inputEventStage;
        public long inputEventTime;
        private int inputEventType;
        private InputEventReceiver targetReceiver;
        public String targetReceiverName;


        public SingleInputEvent()
        {
            super(9, "InputEvent");
            inputEventDescription = "";
            targetReceiverName = "";
            inputChannelName = "";
            eventFlags = 1;
        }
    }


    public InputEventSuperviser()
    {
    }

    public static void beginInputEvent(InputEvent inputevent)
    {
        beginInputEvent(inputevent, 0);
    }

    public static void beginInputEvent(InputEvent inputevent, int i)
    {
        if(!PerfSupervisionSettings.isSupervisionOn())
            return;
        if(!(inputevent instanceof KeyEvent) && (inputevent instanceof MotionEvent) ^ true)
        {
            return;
        } else
        {
            nativeBeginInputEvent(i, inputevent.getSequenceNumber());
            return;
        }
    }

    public static void endInputEvent(InputEvent inputevent, int i, InputEventReceiver inputeventreceiver, InputChannel inputchannel)
    {
        if(!PerfSupervisionSettings.isSupervisionOn())
            return;
        if(!(inputevent instanceof KeyEvent) && (inputevent instanceof MotionEvent) ^ true)
            return;
        int j = inputevent.getSequenceNumber();
        long l = inputevent.getEventTime();
        int k;
        int i1;
        int j1;
        if(inputevent instanceof KeyEvent)
        {
            k = 0;
            i1 = ((KeyEvent)inputevent).getAction();
            j1 = ((KeyEvent)inputevent).getKeyCode();
        } else
        {
            k = 1;
            i1 = ((MotionEvent)inputevent).getAction();
            j1 = 0;
        }
        nativeEndInputEvent(i, j, l, k, i1, j1, inputeventreceiver, inputchannel);
    }

    public static void endInputEvent(InputEvent inputevent, InputEventReceiver inputeventreceiver, InputChannel inputchannel)
    {
        endInputEvent(inputevent, 0, inputeventreceiver, inputchannel);
    }

    private static native void nativeBeginInputEvent(int i, int j);

    private static native void nativeEndInputEvent(int i, int j, long l, int k, int i1, int j1, InputEventReceiver inputeventreceiver, 
            InputChannel inputchannel);

    private static final boolean DEBUGGING = false;
    public static final int INPUT_EVENT_STAGE_DELIVERY = 1;
    public static final int INPUT_EVENT_STAGE_DISPATCH = 0;
    public static final int INPUT_EVENT_TYPE_KEY = 0;
    public static final int INPUT_EVENT_TYPE_MOTION = 1;
    public static final int INPUT_EVENT_TYPE_UNKNOWN = -1;
    private static final String TAG = "InputEventSuperviser";
}
