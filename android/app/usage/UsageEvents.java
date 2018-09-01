// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.usage;

import android.content.res.Configuration;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.List;

public final class UsageEvents
    implements Parcelable
{
    public static final class Event
    {

        public String getClassName()
        {
            return mClass;
        }

        public Configuration getConfiguration()
        {
            return mConfiguration;
        }

        public int getEventType()
        {
            return mEventType;
        }

        public Event getObfuscatedIfInstantApp()
        {
            if((mFlags & 1) == 0)
            {
                return this;
            } else
            {
                Event event = new Event(this);
                event.mPackage = "android.instant_app";
                event.mClass = "android.instant_class";
                return event;
            }
        }

        public String getPackageName()
        {
            return mPackage;
        }

        public String getShortcutId()
        {
            return mShortcutId;
        }

        public long getTimeStamp()
        {
            return mTimeStamp;
        }

        public static final int CHOOSER_ACTION = 9;
        public static final int CONFIGURATION_CHANGE = 5;
        public static final int CONTINUE_PREVIOUS_DAY = 4;
        public static final int END_OF_DAY = 3;
        public static final int FLAG_IS_PACKAGE_INSTANT_APP = 1;
        public static final int MOVE_TO_BACKGROUND = 2;
        public static final int MOVE_TO_FOREGROUND = 1;
        public static final int NONE = 0;
        public static final int SHORTCUT_INVOCATION = 8;
        public static final int SYSTEM_INTERACTION = 6;
        public static final int USER_INTERACTION = 7;
        public String mAction;
        public String mClass;
        public Configuration mConfiguration;
        public String mContentAnnotations[];
        public String mContentType;
        public int mEventType;
        public int mFlags;
        public String mPackage;
        public String mShortcutId;
        public long mTimeStamp;

        public Event()
        {
        }

        public Event(Event event)
        {
            mPackage = event.mPackage;
            mClass = event.mClass;
            mTimeStamp = event.mTimeStamp;
            mEventType = event.mEventType;
            mConfiguration = event.mConfiguration;
            mShortcutId = event.mShortcutId;
            mAction = event.mAction;
            mContentType = event.mContentType;
            mContentAnnotations = event.mContentAnnotations;
            mFlags = event.mFlags;
        }
    }


    UsageEvents()
    {
        mEventsToWrite = null;
        mParcel = null;
        mIndex = 0;
        mEventCount = 0;
    }

    public UsageEvents(Parcel parcel)
    {
        mEventsToWrite = null;
        mParcel = null;
        mIndex = 0;
        mEventCount = parcel.readInt();
        mIndex = parcel.readInt();
        if(mEventCount > 0)
        {
            mStringPool = parcel.createStringArray();
            int i = parcel.readInt();
            int j = parcel.readInt();
            mParcel = Parcel.obtain();
            mParcel.setDataPosition(0);
            mParcel.appendFrom(parcel, parcel.dataPosition(), i);
            mParcel.setDataSize(mParcel.dataPosition());
            mParcel.setDataPosition(j);
        }
    }

    public UsageEvents(List list, String as[])
    {
        mEventsToWrite = null;
        mParcel = null;
        mIndex = 0;
        mStringPool = as;
        mEventCount = list.size();
        mEventsToWrite = list;
    }

    private int findStringIndex(String s)
    {
        int i = Arrays.binarySearch(mStringPool, s);
        if(i < 0)
            throw new IllegalStateException((new StringBuilder()).append("String '").append(s).append("' is not in the string pool").toString());
        else
            return i;
    }

    private void readEventFromParcel(Parcel parcel, Event event)
    {
        int i = parcel.readInt();
        if(i >= 0)
            event.mPackage = mStringPool[i];
        else
            event.mPackage = null;
        i = parcel.readInt();
        if(i >= 0)
            event.mClass = mStringPool[i];
        else
            event.mClass = null;
        event.mEventType = parcel.readInt();
        event.mTimeStamp = parcel.readLong();
        event.mConfiguration = null;
        event.mShortcutId = null;
        event.mAction = null;
        event.mContentType = null;
        event.mContentAnnotations = null;
        event.mEventType;
        JVM INSTR tableswitch 5 9: default 116
    //                   5 133
    //                   6 116
    //                   7 116
    //                   8 152
    //                   9 163;
           goto _L1 _L2 _L1 _L1 _L3 _L4
_L1:
        return;
_L2:
        event.mConfiguration = (Configuration)Configuration.CREATOR.createFromParcel(parcel);
        continue; /* Loop/switch isn't completed */
_L3:
        event.mShortcutId = parcel.readString();
        continue; /* Loop/switch isn't completed */
_L4:
        event.mAction = parcel.readString();
        event.mContentType = parcel.readString();
        event.mContentAnnotations = parcel.createStringArray();
        if(true) goto _L1; else goto _L5
_L5:
    }

    private void writeEventToParcel(Event event, Parcel parcel, int i)
    {
        int j;
        int k;
        if(event.mPackage != null)
            j = findStringIndex(event.mPackage);
        else
            j = -1;
        if(event.mClass != null)
            k = findStringIndex(event.mClass);
        else
            k = -1;
        parcel.writeInt(j);
        parcel.writeInt(k);
        parcel.writeInt(event.mEventType);
        parcel.writeLong(event.mTimeStamp);
        event.mEventType;
        JVM INSTR tableswitch 5 9: default 100
    //                   5 113
    //                   6 100
    //                   7 100
    //                   8 125
    //                   9 136;
           goto _L1 _L2 _L1 _L1 _L3 _L4
_L1:
        return;
_L2:
        event.mConfiguration.writeToParcel(parcel, i);
        continue; /* Loop/switch isn't completed */
_L3:
        parcel.writeString(event.mShortcutId);
        continue; /* Loop/switch isn't completed */
_L4:
        parcel.writeString(event.mAction);
        parcel.writeString(event.mContentType);
        parcel.writeStringArray(event.mContentAnnotations);
        if(true) goto _L1; else goto _L5
_L5:
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean getNextEvent(Event event)
    {
        if(mIndex >= mEventCount)
            return false;
        readEventFromParcel(mParcel, event);
        mIndex = mIndex + 1;
        if(mIndex >= mEventCount)
        {
            mParcel.recycle();
            mParcel = null;
        }
        return true;
    }

    public boolean hasNextEvent()
    {
        boolean flag;
        if(mIndex < mEventCount)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void resetToStart()
    {
        mIndex = 0;
        if(mParcel != null)
            mParcel.setDataPosition(0);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mEventCount);
        parcel.writeInt(mIndex);
        if(mEventCount <= 0) goto _L2; else goto _L1
_L1:
        parcel.writeStringArray(mStringPool);
        if(mEventsToWrite == null) goto _L4; else goto _L3
_L3:
        Parcel parcel1 = Parcel.obtain();
        parcel1.setDataPosition(0);
        int j = 0;
_L6:
        if(j >= mEventCount)
            break; /* Loop/switch isn't completed */
        writeEventToParcel((Event)mEventsToWrite.get(j), parcel1, i);
        j++;
        if(true) goto _L6; else goto _L5
_L5:
        i = parcel1.dataPosition();
        parcel.writeInt(i);
        parcel.writeInt(0);
        parcel.appendFrom(parcel1, 0, i);
        parcel1.recycle();
_L2:
        return;
        parcel;
        parcel1.recycle();
        throw parcel;
_L4:
        if(mParcel != null)
        {
            parcel.writeInt(mParcel.dataSize());
            parcel.writeInt(mParcel.dataPosition());
            parcel.appendFrom(mParcel, 0, mParcel.dataSize());
        } else
        {
            throw new IllegalStateException("Either mParcel or mEventsToWrite must not be null");
        }
        if(true) goto _L2; else goto _L7
_L7:
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public UsageEvents createFromParcel(Parcel parcel)
        {
            return new UsageEvents(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public UsageEvents[] newArray(int i)
        {
            return new UsageEvents[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final String INSTANT_APP_CLASS_NAME = "android.instant_class";
    public static final String INSTANT_APP_PACKAGE_NAME = "android.instant_app";
    private final int mEventCount;
    private List mEventsToWrite;
    private int mIndex;
    private Parcel mParcel;
    private String mStringPool[];

}
