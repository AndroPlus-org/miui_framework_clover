// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package android.content:
//            SyncStats

public final class SyncResult
    implements Parcelable
{

    public SyncResult()
    {
        this(false);
    }

    private SyncResult(Parcel parcel)
    {
        boolean flag = true;
        super();
        boolean flag1;
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        syncAlreadyInProgress = flag1;
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        tooManyDeletions = flag1;
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        tooManyRetries = flag1;
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        databaseError = flag1;
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        fullSyncRequested = flag1;
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        partialSyncUnavailable = flag1;
        if(parcel.readInt() != 0)
            flag1 = flag;
        else
            flag1 = false;
        moreRecordsToGet = flag1;
        delayUntil = parcel.readLong();
        stats = new SyncStats(parcel);
    }

    SyncResult(Parcel parcel, SyncResult syncresult)
    {
        this(parcel);
    }

    private SyncResult(boolean flag)
    {
        syncAlreadyInProgress = flag;
        tooManyDeletions = false;
        tooManyRetries = false;
        fullSyncRequested = false;
        partialSyncUnavailable = false;
        moreRecordsToGet = false;
        delayUntil = 0L;
        stats = new SyncStats();
    }

    public void clear()
    {
        if(syncAlreadyInProgress)
        {
            throw new UnsupportedOperationException("you are not allowed to clear the ALREADY_IN_PROGRESS SyncStats");
        } else
        {
            tooManyDeletions = false;
            tooManyRetries = false;
            databaseError = false;
            fullSyncRequested = false;
            partialSyncUnavailable = false;
            moreRecordsToGet = false;
            delayUntil = 0L;
            stats.clear();
            return;
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean hasError()
    {
        boolean flag;
        if(!hasSoftError())
            flag = hasHardError();
        else
            flag = true;
        return flag;
    }

    public boolean hasHardError()
    {
        boolean flag;
        if(stats.numParseExceptions > 0L || stats.numConflictDetectedExceptions > 0L || stats.numAuthExceptions > 0L || tooManyDeletions || tooManyRetries)
            flag = true;
        else
            flag = databaseError;
        return flag;
    }

    public boolean hasSoftError()
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(!syncAlreadyInProgress)
            if(stats.numIoExceptions > 0L)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public boolean madeSomeProgress()
    {
        boolean flag = true;
        if(stats.numDeletes <= 0L) goto _L2; else goto _L1
_L1:
        boolean flag1 = flag;
        if(tooManyDeletions ^ true) goto _L3; else goto _L2
_L2:
        if(stats.numInserts <= 0L) goto _L5; else goto _L4
_L4:
        flag1 = flag;
_L3:
        return flag1;
_L5:
        flag1 = flag;
        if(stats.numUpdates <= 0L)
            flag1 = false;
        if(true) goto _L3; else goto _L6
_L6:
    }

    public String toDebugString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        if(fullSyncRequested)
            stringbuffer.append("f1");
        if(partialSyncUnavailable)
            stringbuffer.append("r1");
        if(hasHardError())
            stringbuffer.append("X1");
        if(stats.numParseExceptions > 0L)
            stringbuffer.append("e").append(stats.numParseExceptions);
        if(stats.numConflictDetectedExceptions > 0L)
            stringbuffer.append("c").append(stats.numConflictDetectedExceptions);
        if(stats.numAuthExceptions > 0L)
            stringbuffer.append("a").append(stats.numAuthExceptions);
        if(tooManyDeletions)
            stringbuffer.append("D1");
        if(tooManyRetries)
            stringbuffer.append("R1");
        if(databaseError)
            stringbuffer.append("b1");
        if(hasSoftError())
            stringbuffer.append("x1");
        if(syncAlreadyInProgress)
            stringbuffer.append("l1");
        if(stats.numIoExceptions > 0L)
            stringbuffer.append("I").append(stats.numIoExceptions);
        return stringbuffer.toString();
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("SyncResult:");
        if(syncAlreadyInProgress)
            stringbuilder.append(" syncAlreadyInProgress: ").append(syncAlreadyInProgress);
        if(tooManyDeletions)
            stringbuilder.append(" tooManyDeletions: ").append(tooManyDeletions);
        if(tooManyRetries)
            stringbuilder.append(" tooManyRetries: ").append(tooManyRetries);
        if(databaseError)
            stringbuilder.append(" databaseError: ").append(databaseError);
        if(fullSyncRequested)
            stringbuilder.append(" fullSyncRequested: ").append(fullSyncRequested);
        if(partialSyncUnavailable)
            stringbuilder.append(" partialSyncUnavailable: ").append(partialSyncUnavailable);
        if(moreRecordsToGet)
            stringbuilder.append(" moreRecordsToGet: ").append(moreRecordsToGet);
        if(delayUntil > 0L)
            stringbuilder.append(" delayUntil: ").append(delayUntil);
        stringbuilder.append(stats);
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        int j;
        if(syncAlreadyInProgress)
            j = 1;
        else
            j = 0;
        parcel.writeInt(j);
        if(tooManyDeletions)
            j = 1;
        else
            j = 0;
        parcel.writeInt(j);
        if(tooManyRetries)
            j = 1;
        else
            j = 0;
        parcel.writeInt(j);
        if(databaseError)
            j = 1;
        else
            j = 0;
        parcel.writeInt(j);
        if(fullSyncRequested)
            j = 1;
        else
            j = 0;
        parcel.writeInt(j);
        if(partialSyncUnavailable)
            j = 1;
        else
            j = 0;
        parcel.writeInt(j);
        if(moreRecordsToGet)
            j = ((flag) ? 1 : 0);
        else
            j = 0;
        parcel.writeInt(j);
        parcel.writeLong(delayUntil);
        stats.writeToParcel(parcel, i);
    }

    public static final SyncResult ALREADY_IN_PROGRESS = new SyncResult(true);
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public SyncResult createFromParcel(Parcel parcel)
        {
            return new SyncResult(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public SyncResult[] newArray(int i)
        {
            return new SyncResult[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public boolean databaseError;
    public long delayUntil;
    public boolean fullSyncRequested;
    public boolean moreRecordsToGet;
    public boolean partialSyncUnavailable;
    public final SyncStats stats;
    public final boolean syncAlreadyInProgress;
    public boolean tooManyDeletions;
    public boolean tooManyRetries;

}
