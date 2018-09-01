// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.autofill;

import android.os.*;
import com.android.internal.util.Preconditions;
import java.util.ArrayList;
import java.util.List;

public final class FillEventHistory
    implements Parcelable
{
    public static final class Event
    {

        public String getDatasetId()
        {
            return mDatasetId;
        }

        public int getType()
        {
            return mEventType;
        }

        public static final int TYPE_AUTHENTICATION_SELECTED = 2;
        public static final int TYPE_DATASET_AUTHENTICATION_SELECTED = 1;
        public static final int TYPE_DATASET_SELECTED = 0;
        public static final int TYPE_SAVE_SHOWN = 3;
        private final String mDatasetId;
        private final int mEventType;

        public Event(int i, String s)
        {
            mEventType = Preconditions.checkArgumentInRange(i, 0, 3, "eventType");
            mDatasetId = s;
        }
    }


    public FillEventHistory(int i, int j, Bundle bundle)
    {
        mClientState = bundle;
        mServiceUid = i;
        mSessionId = j;
    }

    public void addEvent(Event event)
    {
        if(mEvents == null)
            mEvents = new ArrayList(1);
        mEvents.add(event);
    }

    public int describeContents()
    {
        return 0;
    }

    public Bundle getClientState()
    {
        return mClientState;
    }

    public List getEvents()
    {
        return mEvents;
    }

    public int getServiceUid()
    {
        return mServiceUid;
    }

    public int getSessionId()
    {
        return mSessionId;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeBundle(mClientState);
        if(mEvents == null)
        {
            parcel.writeInt(0);
        } else
        {
            parcel.writeInt(mEvents.size());
            int j = mEvents.size();
            i = 0;
            while(i < j) 
            {
                Event event = (Event)mEvents.get(i);
                parcel.writeInt(event.getType());
                parcel.writeString(event.getDatasetId());
                i++;
            }
        }
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public FillEventHistory createFromParcel(Parcel parcel)
        {
            FillEventHistory filleventhistory = new FillEventHistory(0, 0, parcel.readBundle());
            int i = parcel.readInt();
            for(int j = 0; j < i; j++)
                filleventhistory.addEvent(new Event(parcel.readInt(), parcel.readString()));

            return filleventhistory;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public FillEventHistory[] newArray(int i)
        {
            return new FillEventHistory[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final Bundle mClientState;
    List mEvents;
    private final int mServiceUid;
    private final int mSessionId;

}
