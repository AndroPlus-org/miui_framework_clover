// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONException;
import org.json.JSONObject;

public class WhetstoneAction
    implements Parcelable
{

    private WhetstoneAction(Parcel parcel)
    {
        DT = parcel.readString();
        content = convertStringToJson(parcel.readString());
    }

    WhetstoneAction(Parcel parcel, WhetstoneAction whetstoneaction)
    {
        this(parcel);
    }

    public WhetstoneAction(String s, JSONObject jsonobject)
    {
        DT = s;
        content = jsonobject;
    }

    private JSONObject convertStringToJson(String s)
    {
        Object obj = null;
        JSONObject jsonobject;
        jsonobject = JVM INSTR new #47  <Class JSONObject>;
        jsonobject.JSONObject(s);
        s = jsonobject;
_L2:
        return s;
        s;
        s.printStackTrace();
        s = obj;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public int describeContents()
    {
        return 0;
    }

    public JSONObject getContent()
    {
        return content;
    }

    public String getKey()
    {
        return DT;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(DT);
        parcel.writeString(content.toString());
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public WhetstoneAction createFromParcel(Parcel parcel)
        {
            return new WhetstoneAction(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public WhetstoneAction[] newArray(int i)
        {
            return new WhetstoneAction[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private String DT;
    private JSONObject content;

}
