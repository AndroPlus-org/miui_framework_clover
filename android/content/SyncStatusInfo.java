// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package android.content:
//            ContentResolver

public class SyncStatusInfo
    implements Parcelable
{

    public SyncStatusInfo(int i)
    {
        mLastEventTimes = new ArrayList();
        mLastEvents = new ArrayList();
        authorityId = i;
    }

    public SyncStatusInfo(SyncStatusInfo syncstatusinfo)
    {
        mLastEventTimes = new ArrayList();
        mLastEvents = new ArrayList();
        authorityId = syncstatusinfo.authorityId;
        totalElapsedTime = syncstatusinfo.totalElapsedTime;
        numSyncs = syncstatusinfo.numSyncs;
        numSourcePoll = syncstatusinfo.numSourcePoll;
        numSourceServer = syncstatusinfo.numSourceServer;
        numSourceLocal = syncstatusinfo.numSourceLocal;
        numSourceUser = syncstatusinfo.numSourceUser;
        numSourcePeriodic = syncstatusinfo.numSourcePeriodic;
        lastSuccessTime = syncstatusinfo.lastSuccessTime;
        lastSuccessSource = syncstatusinfo.lastSuccessSource;
        lastFailureTime = syncstatusinfo.lastFailureTime;
        lastFailureSource = syncstatusinfo.lastFailureSource;
        lastFailureMesg = syncstatusinfo.lastFailureMesg;
        initialFailureTime = syncstatusinfo.initialFailureTime;
        pending = syncstatusinfo.pending;
        initialize = syncstatusinfo.initialize;
        if(syncstatusinfo.periodicSyncTimes != null)
            periodicSyncTimes = new ArrayList(syncstatusinfo.periodicSyncTimes);
        mLastEventTimes.addAll(syncstatusinfo.mLastEventTimes);
        mLastEvents.addAll(syncstatusinfo.mLastEvents);
    }

    public SyncStatusInfo(Parcel parcel)
    {
        mLastEventTimes = new ArrayList();
        mLastEvents = new ArrayList();
        int i = parcel.readInt();
        if(i != 4 && i != 1)
            Log.w("SyncStatusInfo", (new StringBuilder()).append("Unknown version: ").append(i).toString());
        authorityId = parcel.readInt();
        totalElapsedTime = parcel.readLong();
        numSyncs = parcel.readInt();
        numSourcePoll = parcel.readInt();
        numSourceServer = parcel.readInt();
        numSourceLocal = parcel.readInt();
        numSourceUser = parcel.readInt();
        lastSuccessTime = parcel.readLong();
        lastSuccessSource = parcel.readInt();
        lastFailureTime = parcel.readLong();
        lastFailureSource = parcel.readInt();
        lastFailureMesg = parcel.readString();
        initialFailureTime = parcel.readLong();
        boolean flag;
        if(parcel.readInt() != 0)
            flag = true;
        else
            flag = false;
        pending = flag;
        if(parcel.readInt() != 0)
            flag = true;
        else
            flag = false;
        initialize = flag;
        if(i == 1)
        {
            periodicSyncTimes = null;
        } else
        {
            int j = parcel.readInt();
            if(j < 0)
            {
                periodicSyncTimes = null;
            } else
            {
                periodicSyncTimes = new ArrayList();
                int l = 0;
                while(l < j) 
                {
                    periodicSyncTimes.add(Long.valueOf(parcel.readLong()));
                    l++;
                }
            }
            if(i >= 3)
            {
                mLastEventTimes.clear();
                mLastEvents.clear();
                j = parcel.readInt();
                int k = 0;
                while(k < j) 
                {
                    mLastEventTimes.add(Long.valueOf(parcel.readLong()));
                    mLastEvents.add(parcel.readString());
                    k++;
                }
            }
        }
        if(i < 4)
        {
            numSourcePeriodic = numSyncs - numSourceLocal - numSourcePoll - numSourceServer - numSourceUser;
            if(numSourcePeriodic < 0)
                numSourcePeriodic = 0;
        } else
        {
            numSourcePeriodic = parcel.readInt();
        }
    }

    private void ensurePeriodicSyncTimeSize(int i)
    {
        if(periodicSyncTimes == null)
            periodicSyncTimes = new ArrayList(0);
        int j = i + 1;
        if(periodicSyncTimes.size() < j)
            for(i = periodicSyncTimes.size(); i < j; i++)
                periodicSyncTimes.add(Long.valueOf(0L));

    }

    public void addEvent(String s)
    {
        if(mLastEventTimes.size() >= 10)
        {
            mLastEventTimes.remove(9);
            mLastEvents.remove(9);
        }
        mLastEventTimes.add(0, Long.valueOf(System.currentTimeMillis()));
        mLastEvents.add(0, s);
    }

    public int describeContents()
    {
        return 0;
    }

    public String getEvent(int i)
    {
        return (String)mLastEvents.get(i);
    }

    public int getEventCount()
    {
        return mLastEventTimes.size();
    }

    public long getEventTime(int i)
    {
        return ((Long)mLastEventTimes.get(i)).longValue();
    }

    public int getLastFailureMesgAsInt(int i)
    {
        int j = ContentResolver.syncErrorStringToInt(lastFailureMesg);
        if(j > 0)
        {
            return j;
        } else
        {
            Log.d("Sync", (new StringBuilder()).append("Unknown lastFailureMesg:").append(lastFailureMesg).toString());
            return i;
        }
    }

    public long getPeriodicSyncTime(int i)
    {
        if(periodicSyncTimes != null && i < periodicSyncTimes.size())
            return ((Long)periodicSyncTimes.get(i)).longValue();
        else
            return 0L;
    }

    public void removePeriodicSyncTime(int i)
    {
        if(periodicSyncTimes != null && i < periodicSyncTimes.size())
            periodicSyncTimes.remove(i);
    }

    public void setPeriodicSyncTime(int i, long l)
    {
        ensurePeriodicSyncTimeSize(i);
        periodicSyncTimes.set(i, Long.valueOf(l));
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeInt(4);
        parcel.writeInt(authorityId);
        parcel.writeLong(totalElapsedTime);
        parcel.writeInt(numSyncs);
        parcel.writeInt(numSourcePoll);
        parcel.writeInt(numSourceServer);
        parcel.writeInt(numSourceLocal);
        parcel.writeInt(numSourceUser);
        parcel.writeLong(lastSuccessTime);
        parcel.writeInt(lastSuccessSource);
        parcel.writeLong(lastFailureTime);
        parcel.writeInt(lastFailureSource);
        parcel.writeString(lastFailureMesg);
        parcel.writeLong(initialFailureTime);
        if(pending)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(initialize)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeInt(i);
        if(periodicSyncTimes != null)
        {
            parcel.writeInt(periodicSyncTimes.size());
            for(Iterator iterator = periodicSyncTimes.iterator(); iterator.hasNext(); parcel.writeLong(((Long)iterator.next()).longValue()));
        } else
        {
            parcel.writeInt(-1);
        }
        parcel.writeInt(mLastEventTimes.size());
        for(i = 0; i < mLastEventTimes.size(); i++)
        {
            parcel.writeLong(((Long)mLastEventTimes.get(i)).longValue());
            parcel.writeString((String)mLastEvents.get(i));
        }

        parcel.writeInt(numSourcePeriodic);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public SyncStatusInfo createFromParcel(Parcel parcel)
        {
            return new SyncStatusInfo(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public SyncStatusInfo[] newArray(int i)
        {
            return new SyncStatusInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final int MAX_EVENT_COUNT = 10;
    private static final String TAG = "Sync";
    static final int VERSION = 4;
    public final int authorityId;
    public long initialFailureTime;
    public boolean initialize;
    public String lastFailureMesg;
    public int lastFailureSource;
    public long lastFailureTime;
    public int lastSuccessSource;
    public long lastSuccessTime;
    private final ArrayList mLastEventTimes;
    private final ArrayList mLastEvents;
    public int numSourceLocal;
    public int numSourcePeriodic;
    public int numSourcePoll;
    public int numSourceServer;
    public int numSourceUser;
    public int numSyncs;
    public boolean pending;
    private ArrayList periodicSyncTimes;
    public long totalElapsedTime;

}
