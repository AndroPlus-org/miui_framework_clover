// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims;

import android.os.*;
import android.telecom.Log;
import java.util.*;

public class ImsConferenceState
    implements Parcelable
{

    public ImsConferenceState()
    {
        mParticipants = new LinkedHashMap();
    }

    public ImsConferenceState(Parcel parcel)
    {
        mParticipants = new LinkedHashMap();
        readFromParcel(parcel);
    }

    public static int getConnectionStateForStatus(String s)
    {
        if(s.equals("pending"))
            return 0;
        if(s.equals("dialing-in"))
            return 2;
        if(s.equals("alerting") || s.equals("dialing-out"))
            return 3;
        if(s.equals("on-hold") || s.equals("sendonly"))
            return 5;
        if(s.equals("connected") || s.equals("muted-via-focus") || s.equals("disconnecting") || s.equals("sendrecv"))
            return 4;
        return !s.equals("disconnected") ? 4 : 6;
    }

    private void readFromParcel(Parcel parcel)
    {
        int i = parcel.readInt();
        for(int j = 0; j < i; j++)
        {
            String s = parcel.readString();
            Bundle bundle = (Bundle)parcel.readParcelable(null);
            mParticipants.put(s, bundle);
        }

    }

    public int describeContents()
    {
        return 0;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("[");
        stringbuilder.append(com/android/ims/ImsConferenceState.getSimpleName());
        stringbuilder.append(" ");
        if(mParticipants.size() > 0)
        {
            Object obj = mParticipants.entrySet();
            if(obj != null)
            {
                obj = ((Set) (obj)).iterator();
                stringbuilder.append("<");
                while(((Iterator) (obj)).hasNext()) 
                {
                    Object obj1 = (java.util.Map.Entry)((Iterator) (obj)).next();
                    stringbuilder.append(Log.pii(((java.util.Map.Entry) (obj1)).getKey()));
                    stringbuilder.append(": ");
                    Bundle bundle = (Bundle)((java.util.Map.Entry) (obj1)).getValue();
                    obj1 = bundle.keySet().iterator();
                    while(((Iterator) (obj1)).hasNext()) 
                    {
                        String s = (String)((Iterator) (obj1)).next();
                        stringbuilder.append(s);
                        stringbuilder.append("=");
                        if("endpoint".equals(s) || "user".equals(s))
                            stringbuilder.append(Log.pii(bundle.get(s)));
                        else
                            stringbuilder.append(bundle.get(s));
                        stringbuilder.append(", ");
                    }
                }
                stringbuilder.append(">");
            }
        }
        stringbuilder.append("]");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mParticipants.size());
        if(mParticipants.size() > 0)
        {
            Object obj = mParticipants.entrySet();
            if(obj != null)
            {
                for(Iterator iterator = ((Set) (obj)).iterator(); iterator.hasNext(); parcel.writeParcelable((Parcelable)((java.util.Map.Entry) (obj)).getValue(), 0))
                {
                    obj = (java.util.Map.Entry)iterator.next();
                    parcel.writeString((String)((java.util.Map.Entry) (obj)).getKey());
                }

            }
        }
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ImsConferenceState createFromParcel(Parcel parcel)
        {
            return new ImsConferenceState(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ImsConferenceState[] newArray(int i)
        {
            return new ImsConferenceState[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final String DISPLAY_TEXT = "display-text";
    public static final String ENDPOINT = "endpoint";
    public static final String SIP_STATUS_CODE = "sipstatuscode";
    public static final String STATUS = "status";
    public static final String STATUS_ALERTING = "alerting";
    public static final String STATUS_CONNECTED = "connected";
    public static final String STATUS_CONNECT_FAIL = "connect-fail";
    public static final String STATUS_DIALING_IN = "dialing-in";
    public static final String STATUS_DIALING_OUT = "dialing-out";
    public static final String STATUS_DISCONNECTED = "disconnected";
    public static final String STATUS_DISCONNECTING = "disconnecting";
    public static final String STATUS_MUTED_VIA_FOCUS = "muted-via-focus";
    public static final String STATUS_ON_HOLD = "on-hold";
    public static final String STATUS_PENDING = "pending";
    public static final String STATUS_SEND_ONLY = "sendonly";
    public static final String STATUS_SEND_RECV = "sendrecv";
    public static final String USER = "user";
    public HashMap mParticipants;

}
