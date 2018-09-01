// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os.statistics;

import android.os.Parcel;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package android.os.statistics:
//            MacroscopicEvent, PerfEvent, JniParcel, NativeBackTrace

public class SingleJankRecord extends MacroscopicEvent
{

    public SingleJankRecord()
    {
        super(0x10002, "JankRecord");
        windowName = "";
        appCause = "";
        sysCause = "";
        conclusion = "";
    }

    private void copyFrom(SingleJankRecord singlejankrecord)
    {
        super.copyFrom(singlejankrecord);
        renderThreadTid = singlejankrecord.renderThreadTid;
        windowName = singlejankrecord.windowName;
        appCause = singlejankrecord.appCause;
        sysCause = singlejankrecord.sysCause;
        conclusion = singlejankrecord.conclusion;
        totalDuration = singlejankrecord.totalDuration;
        maxFrameDuration = singlejankrecord.maxFrameDuration;
        receivedUptimeMillis = singlejankrecord.receivedUptimeMillis;
        receivedCurrentTimeMillis = singlejankrecord.receivedCurrentTimeMillis;
        isCharging = singlejankrecord.isCharging;
        batteryLevel = singlejankrecord.batteryLevel;
        batteryTemperature = singlejankrecord.batteryTemperature;
    }

    public void copyFrom(PerfEvent perfevent)
    {
        copyFrom((SingleJankRecord)perfevent);
    }

    void fillIn(JniParcel jniparcel, Object obj, NativeBackTrace nativebacktrace)
    {
    }

    boolean isUserPerceptible()
    {
        return true;
    }

    boolean occursInCurrentProcess()
    {
        return false;
    }

    public void readFromJson(JSONObject jsonobject)
    {
        super.readFromJson(jsonobject);
        renderThreadTid = jsonobject.optInt("renderThreadTid", 0);
        windowName = jsonobject.optString("windowName", "");
        appCause = jsonobject.optString("appCause", "");
        sysCause = jsonobject.optString("sysCause", "");
        conclusion = jsonobject.optString("conclusion", "");
        totalDuration = jsonobject.optLong("totalDuration", 0L);
        maxFrameDuration = jsonobject.optLong("maxFrameDuration", 0L);
        receivedUptimeMillis = jsonobject.optLong("receivedUptime", 0L);
        receivedCurrentTimeMillis = jsonobject.optLong("receivedCurrentTime", 0L);
        isCharging = jsonobject.optBoolean("isCharging", false);
        batteryLevel = jsonobject.optInt("batteryLevel", 0);
        batteryTemperature = jsonobject.optInt("batteryTemperature", 0);
    }

    public void readFromParcel(Parcel parcel)
    {
        boolean flag = true;
        super.readFromParcel(parcel);
        renderThreadTid = parcel.readInt();
        windowName = parcel.readString();
        appCause = parcel.readString();
        sysCause = parcel.readString();
        conclusion = parcel.readString();
        totalDuration = parcel.readLong();
        maxFrameDuration = parcel.readLong();
        receivedUptimeMillis = parcel.readLong();
        receivedCurrentTimeMillis = parcel.readLong();
        if(parcel.readInt() != 1)
            flag = false;
        isCharging = flag;
        batteryLevel = parcel.readInt();
        batteryTemperature = parcel.readInt();
    }

    public void reset()
    {
        super.reset();
        renderThreadTid = 0;
        windowName = "";
        appCause = "";
        sysCause = "";
        conclusion = "";
        totalDuration = 0L;
        maxFrameDuration = 0L;
        receivedUptimeMillis = 0L;
        receivedCurrentTimeMillis = 0L;
        isCharging = false;
        batteryLevel = 0;
        batteryTemperature = 0;
    }

    public void writeToJson(JSONObject jsonobject)
    {
        super.writeToJson(jsonobject);
        jsonobject.put("renderThreadTid", renderThreadTid);
        jsonobject.put("windowName", windowName);
        jsonobject.put("appCause", appCause);
        jsonobject.put("sysCause", sysCause);
        jsonobject.put("conclusion", conclusion);
        jsonobject.put("totalDuration", totalDuration);
        jsonobject.put("maxFrameDuration", maxFrameDuration);
        jsonobject.put("receivedUptime", receivedUptimeMillis);
        jsonobject.put("receivedCurrentTime", receivedCurrentTimeMillis);
        jsonobject.put("isCharging", isCharging);
        jsonobject.put("batteryLevel", batteryLevel);
        jsonobject.put("batteryTemperature", batteryTemperature);
_L1:
        return;
        jsonobject;
        jsonobject.printStackTrace();
          goto _L1
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i);
        parcel.writeInt(renderThreadTid);
        parcel.writeString(windowName);
        parcel.writeString(appCause);
        parcel.writeString(sysCause);
        parcel.writeString(conclusion);
        parcel.writeLong(totalDuration);
        parcel.writeLong(maxFrameDuration);
        parcel.writeLong(receivedUptimeMillis);
        parcel.writeLong(receivedCurrentTimeMillis);
        if(isCharging)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeInt(batteryLevel);
        parcel.writeInt(batteryTemperature);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public SingleJankRecord createFromParcel(Parcel parcel)
        {
            SingleJankRecord singlejankrecord = new SingleJankRecord();
            singlejankrecord.readFromParcel(parcel);
            return singlejankrecord;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public SingleJankRecord[] newArray(int i)
        {
            return new SingleJankRecord[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String FIELD_APP_CAUSE = "appCause";
    private static final String FIELD_BATTERY_LEVEL = "batteryLevel";
    private static final String FIELD_BATTERY_TEMP = "batteryTemperature";
    private static final String FIELD_CHARGING = "isCharging";
    private static final String FIELD_CONCLUSION = "conclusion";
    private static final String FIELD_MAX_FRAME_DURATION = "maxFrameDuration";
    private static final String FIELD_RECEIVED_CURRENT_TIME = "receivedCurrentTime";
    private static final String FIELD_RECEIVED_UPTIME = "receivedUptime";
    private static final String FIELD_RENDER_THREAD_ID = "renderThreadTid";
    private static final String FIELD_SYS_CAUSE = "sysCause";
    private static final String FIELD_TOTAL_DURATION = "totalDuration";
    private static final String FIELD_WINDOW_NAME = "windowName";
    public String appCause;
    public int batteryLevel;
    public int batteryTemperature;
    public String conclusion;
    public boolean isCharging;
    public long maxFrameDuration;
    public long receivedCurrentTimeMillis;
    public long receivedUptimeMillis;
    public int renderThreadTid;
    public String sysCause;
    public long totalDuration;
    public String windowName;

}
