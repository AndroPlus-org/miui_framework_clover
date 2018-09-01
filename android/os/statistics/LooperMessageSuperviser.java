// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os.statistics;

import android.app.ActivityThreadInjector;
import android.os.*;
import android.text.TextUtils;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package android.os.statistics:
//            PerfSupervisionSettings, MicroscopicEvent, JniParcel, OsUtils, 
//            PerfEvent, NativeBackTrace

public class LooperMessageSuperviser
{
    public static class SingleLooperMessage extends MicroscopicEvent
    {

        private void copyFrom(SingleLooperMessage singleloopermessage)
        {
            super.copyFrom(singleloopermessage);
            messageName = singleloopermessage.messageName;
            planUptimeMillis = singleloopermessage.planUptimeMillis;
            messageWhat = singleloopermessage.messageWhat;
            messageTargetClazz = singleloopermessage.messageTargetClazz;
            messageCallbackClazz = singleloopermessage.messageCallbackClazz;
        }

        private static boolean isActivityThreadMessage(SingleLooperMessage singleloopermessage)
        {
            boolean flag = false;
            if(activityThreadHandler == null)
                activityThreadHandler = ActivityThreadInjector.getHandler();
            boolean flag1 = flag;
            if(activityThreadHandler != null)
            {
                flag1 = flag;
                if(activityThreadHandler.getClass() == singleloopermessage.messageTargetClazz)
                {
                    flag1 = flag;
                    if(singleloopermessage.messageCallbackClazz == null)
                        flag1 = true;
                }
            }
            return flag1;
        }

        private static boolean isApplicationOperationMessage(SingleLooperMessage singleloopermessage)
        {
            boolean flag = false;
            if(Arrays.binarySearch(APPLICATION_OPERATION_MESSAGE_CODES, singleloopermessage.messageWhat) >= 0)
                flag = true;
            return flag;
        }

        private static boolean isFrameDisplayMessage(SingleLooperMessage singleloopermessage)
        {
            boolean flag;
            flag = false;
            if(singleloopermessage.messageTargetClazz == null || singleloopermessage.messageCallbackClazz == null)
                return false;
            if(isFrameDisplayMessageClassLoaded) goto _L2; else goto _L1
_L1:
            frameDisplayMessageCallbackClass = Class.forName("android.view.Choreographer$FrameDisplayEventReceiver");
            frameDisplayMessageTargetClass = Class.forName("android.view.Choreographer$FrameHandler");
_L4:
            isFrameDisplayMessageClassLoaded = true;
_L2:
            boolean flag1 = flag;
            if(singleloopermessage.messageCallbackClazz == frameDisplayMessageCallbackClass)
            {
                flag1 = flag;
                if(singleloopermessage.messageTargetClazz == frameDisplayMessageTargetClass)
                    flag1 = true;
            }
            return flag1;
            Exception exception;
            exception;
            exception.printStackTrace();
            if(true) goto _L4; else goto _L3
_L3:
            singleloopermessage;
            isFrameDisplayMessageClassLoaded = true;
            throw singleloopermessage;
        }

        private static boolean isWindowOperationMessage(SingleLooperMessage singleloopermessage)
        {
            boolean flag = false;
            if(Arrays.binarySearch(WINDOW_OPERATION_MESSAGE_CODES, singleloopermessage.messageWhat) >= 0)
                flag = true;
            return flag;
        }

        public void copyFrom(PerfEvent perfevent)
        {
            copyFrom((SingleLooperMessage)perfevent);
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
            messageWhat = (int)jniparcel.readLong();
            planUptimeMillis = jniparcel.readLong();
            messageName = jniparcel.readString();
            messageTargetClazz = (Class)jniparcel.readObject();
            messageCallbackClazz = (Class)jniparcel.readObject();
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

        boolean isConcerned()
        {
            boolean flag = isActivityThreadMessage(this);
            boolean flag1 = false;
            boolean flag2 = false;
            if(flag)
            {
                flag1 = isWindowOperationMessage(this);
                flag2 = isApplicationOperationMessage(this);
            }
            flag = isFrameDisplayMessage(this);
            long l = endUptimeMillis - beginUptimeMillis;
            long l1 = 0x7fffffffffffffffL;
            if((flag1 ^ true) & (flag2 ^ true) & (flag ^ true))
                l1 = l;
            else
            if(flag)
                l1 = PerfSupervisionSettings.sPerfSupervisionSoftThreshold;
            else
            if(flag1)
                l1 = 300L;
            else
            if(flag2)
                l1 = 600L;
            if(l >= l1)
                flag1 = true;
            else
                flag1 = false;
            return flag1;
        }

        public boolean isPeerBlockingEvent()
        {
            return false;
        }

        public boolean isRootEvent()
        {
            return false;
        }

        public void readFromJson(JSONObject jsonobject)
        {
            super.readFromJson(jsonobject);
            messageName = jsonobject.optString("messageName", "");
            planUptimeMillis = jsonobject.optInt("planTime", 0);
            messageWhat = 0;
            messageTargetClazz = null;
            messageCallbackClazz = null;
        }

        public void readFromParcel(Parcel parcel)
        {
            super.readFromParcel(parcel);
            messageName = parcel.readString();
            if(messageName == null)
                messageName = "";
            planUptimeMillis = parcel.readLong();
            messageWhat = 0;
            messageTargetClazz = null;
            messageCallbackClazz = null;
        }

        public void reset()
        {
            super.reset();
            messageName = "";
            planUptimeMillis = 0L;
            messageWhat = 0;
            messageTargetClazz = null;
            messageCallbackClazz = null;
        }

        void resolveLazyInfo()
        {
            if(lazyInfoResolved)
                return;
            super.resolveLazyInfo();
            if(TextUtils.isEmpty(messageName))
            {
                StringBuilder stringbuilder = new StringBuilder();
                stringbuilder.append("{ ");
                if(messageCallbackClazz != null)
                {
                    stringbuilder.append("callback=");
                    stringbuilder.append(messageCallbackClazz.getName());
                } else
                {
                    stringbuilder.append("what=");
                    stringbuilder.append(messageWhat);
                }
                if(messageTargetClazz != null)
                {
                    stringbuilder.append(" target=");
                    stringbuilder.append(messageTargetClazz.getName());
                }
                stringbuilder.append("}");
                messageName = stringbuilder.toString();
            }
            messageWhat = 0;
            messageTargetClazz = null;
            messageCallbackClazz = null;
        }

        public void writeToJson(JSONObject jsonobject)
        {
            super.writeToJson(jsonobject);
            jsonobject.put("messageName", messageName);
            jsonobject.put("planTime", planUptimeMillis);
_L1:
            return;
            jsonobject;
            jsonobject.printStackTrace();
              goto _L1
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeString(messageName);
            parcel.writeLong(planUptimeMillis);
        }

        private static final int ACTIVITY_CONFIGURATION_CHANGED = 125;
        private static final int APPLICATION_OPERATION_MESSAGE_CODES[] = {
            110
        };
        private static final int BIND_APPLICATION = 110;
        private static final int CONFIGURATION_CHANGED = 118;
        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public SingleLooperMessage createFromParcel(Parcel parcel)
            {
                SingleLooperMessage singleloopermessage = new SingleLooperMessage();
                singleloopermessage.readFromParcel(parcel);
                return singleloopermessage;
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public SingleLooperMessage[] newArray(int i)
            {
                return new SingleLooperMessage[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private static final String FIELD_MESSAGE_NAME = "messageName";
        private static final String FIELD_PLAN_UPTIME = "planTime";
        private static final int HIDE_WINDOW = 106;
        private static final int LAUNCH_ACTIVITY = 100;
        private static final int MULTI_WINDOW_MODE_CHANGED = 152;
        private static final int NEW_INTENT = 112;
        private static final int PAUSE_ACTIVITY = 101;
        private static final int PAUSE_ACTIVITY_FINISHING = 102;
        private static final int PICTURE_IN_PICTURE_MODE_CHANGED = 153;
        private static final int RELAUNCH_ACTIVITY = 126;
        private static final int RESUME_ACTIVITY = 107;
        private static final int SEND_RESULT = 108;
        private static final int SHOW_WINDOW = 105;
        private static final int STOP_ACTIVITY_HIDE = 104;
        private static final int STOP_ACTIVITY_SHOW = 103;
        private static final int WINDOW_OPERATION_MESSAGE_CODES[] = {
            100, 101, 102, 103, 104, 105, 106, 107, 108, 112, 
            118, 125, 126, 152, 153
        };
        private static Handler activityThreadHandler;
        private static Class frameDisplayMessageCallbackClass;
        private static Class frameDisplayMessageTargetClass;
        private static boolean isFrameDisplayMessageClassLoaded;
        private Class messageCallbackClazz;
        public String messageName;
        private Class messageTargetClazz;
        private int messageWhat;
        public long planUptimeMillis;


        public SingleLooperMessage()
        {
            super(8, "LooperMessage");
        }
    }


    public LooperMessageSuperviser()
    {
    }

    public static void beginLooperMessage(ILooperMonitorable iloopermonitorable, Message message)
    {
        if(!PerfSupervisionSettings.isSupervisionOn())
            return;
        if(!iloopermonitorable.isMonitorLooper())
        {
            return;
        } else
        {
            nativeBeginLooperMessage();
            return;
        }
    }

    public static void endLooperMessage(ILooperMonitorable iloopermonitorable, Message message, long l)
    {
        Object obj = null;
        if(!PerfSupervisionSettings.isSupervisionOn())
            return;
        if(!iloopermonitorable.isMonitorLooper())
            return;
        iloopermonitorable = message.getTarget();
        Runnable runnable = message.getCallback();
        int i;
        if(iloopermonitorable == null)
            iloopermonitorable = null;
        else
            iloopermonitorable = iloopermonitorable.getClass();
        i = message.what;
        if(runnable == null)
            message = obj;
        else
            message = runnable.getClass();
        nativeEndLooperMessage(iloopermonitorable, i, message, l);
    }

    private static native void nativeBeginLooperMessage();

    private static native void nativeEndLooperMessage(Class class1, int i, Class class2, long l);

    private static final long APPLICATION_OPERATION_MESSAGE_THRESHOLD_MS = 600L;
    private static final boolean DEBUGGING = false;
    private static final long LOW_PRIORITY_ACTIVITY_MESSAGE_THRESHOLD_MS = 300L;
    private static final String TAG = "LooperMessageSuperviser";
    private static final long WINDOW_OPERATION_MESSAGE_THRESHOLD_MS = 300L;
}
