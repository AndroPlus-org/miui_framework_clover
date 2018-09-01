// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os.statistics;

import android.os.Parcel;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package android.os.statistics:
//            PerfEvent

public abstract class MacroscopicEvent extends PerfEvent
{

    public MacroscopicEvent(int i, String s)
    {
        super(i, s);
    }

    private void copyFrom(MacroscopicEvent macroscopicevent)
    {
        super.copyFrom(macroscopicevent);
        occurUptimeMillis = macroscopicevent.occurUptimeMillis;
    }

    public void copyFrom(PerfEvent perfevent)
    {
        copyFrom((MacroscopicEvent)perfevent);
    }

    public long getBeginUptimeMillis()
    {
        return occurUptimeMillis;
    }

    public long getEndUptimeMillis()
    {
        return occurUptimeMillis;
    }

    public void readFromJson(JSONObject jsonobject)
    {
        super.readFromJson(jsonobject);
        occurUptimeMillis = jsonobject.optLong("occurTime", 0L);
    }

    public void readFromParcel(Parcel parcel)
    {
        super.readFromParcel(parcel);
        occurUptimeMillis = parcel.readLong();
    }

    public void reset()
    {
        super.reset();
        occurUptimeMillis = 0L;
    }

    public void writeToJson(JSONObject jsonobject)
    {
        super.writeToJson(jsonobject);
        jsonobject.put("occurTime", occurUptimeMillis);
_L1:
        return;
        jsonobject;
        jsonobject.printStackTrace();
          goto _L1
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i);
        parcel.writeLong(occurUptimeMillis);
    }

    public static final int MACRO_EVENT_TYPE_COUNT = 5;
    public static final int MACRO_EVENT_TYPE_START = 0x10000;
    public static final int TYPE_BINDER_STARVATION = 0x10004;
    public static final int TYPE_DEX2OAT = 0x10003;
    public static final int TYPE_PLACE_HOLDER_1 = 0x10001;
    public static final int TYPE_SINGLE_EVENT_LOG_ITEM = 0x10000;
    public static final int TYPE_SINGLE_JANK_RECORD = 0x10002;
    public long occurUptimeMillis;
}
