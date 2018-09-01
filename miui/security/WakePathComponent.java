// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.security;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public class WakePathComponent
    implements Parcelable
{

    public WakePathComponent()
    {
        mIntentActions = new ArrayList();
    }

    public WakePathComponent(int i, String s, List list)
        throws Exception
    {
        mIntentActions = new ArrayList();
        mType = i;
        mClassname = s;
        mIntentActions.addAll(list);
    }

    private WakePathComponent(Parcel parcel)
    {
        mIntentActions = new ArrayList();
        mType = parcel.readInt();
        mClassname = parcel.readString();
        parcel.readStringList(mIntentActions);
    }

    WakePathComponent(Parcel parcel, WakePathComponent wakepathcomponent)
    {
        this(parcel);
    }

    public void addIntentAction(String s)
    {
        mIntentActions.add(s);
    }

    public int describeContents()
    {
        return 0;
    }

    public String getClassname()
    {
        return mClassname;
    }

    public List getIntentActions()
    {
        return mIntentActions;
    }

    public int getType()
    {
        return mType;
    }

    public void setClassname(String s)
    {
        mClassname = s;
    }

    public void setType(int i)
    {
        mType = i;
    }

    public String toString()
    {
        return (new StringBuilder()).append("WakePathComponent: mType=").append(mType).append(" mClassname=").append(mClassname).append(" mIntentActions=").append(mIntentActions.toString()).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mType);
        parcel.writeString(mClassname);
        parcel.writeStringList(mIntentActions);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public WakePathComponent createFromParcel(Parcel parcel)
        {
            return new WakePathComponent(parcel, null);
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

        public WakePathComponent[] newArray(int i)
        {
            return new WakePathComponent[i];
        }

    }
;
    public static final int WAKE_PATH_COMPONENT_ACTIVITY = 3;
    public static final int WAKE_PATH_COMPONENT_PROVIDER = 4;
    public static final int WAKE_PATH_COMPONENT_RECEIVER = 1;
    public static final int WAKE_PATH_COMPONENT_SERVICE = 2;
    private String mClassname;
    private List mIntentActions;
    private int mType;

}
