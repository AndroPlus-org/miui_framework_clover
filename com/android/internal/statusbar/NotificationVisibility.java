// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.statusbar;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayDeque;

public class NotificationVisibility
    implements Parcelable
{

    static NotificationVisibility _2D_wrap0(Parcel parcel)
    {
        return obtain(parcel);
    }

    private NotificationVisibility()
    {
        visible = true;
        int i = sNexrId;
        sNexrId = i + 1;
        id = i;
    }

    private NotificationVisibility(String s, int i, boolean flag)
    {
        this();
        key = s;
        rank = i;
        visible = flag;
    }

    private static NotificationVisibility obtain()
    {
        ArrayDeque arraydeque = sPool;
        arraydeque;
        JVM INSTR monitorenter ;
        NotificationVisibility notificationvisibility;
        if(sPool.isEmpty())
            break MISSING_BLOCK_LABEL_29;
        notificationvisibility = (NotificationVisibility)sPool.poll();
        return notificationvisibility;
        arraydeque;
        JVM INSTR monitorexit ;
        return new NotificationVisibility();
        Exception exception;
        exception;
        throw exception;
    }

    private static NotificationVisibility obtain(Parcel parcel)
    {
        NotificationVisibility notificationvisibility = obtain();
        notificationvisibility.readFromParcel(parcel);
        return notificationvisibility;
    }

    public static NotificationVisibility obtain(String s, int i, boolean flag)
    {
        NotificationVisibility notificationvisibility = obtain();
        notificationvisibility.key = s;
        notificationvisibility.rank = i;
        notificationvisibility.visible = flag;
        return notificationvisibility;
    }

    private void readFromParcel(Parcel parcel)
    {
        boolean flag = false;
        key = parcel.readString();
        rank = parcel.readInt();
        if(parcel.readInt() != 0)
            flag = true;
        visible = flag;
    }

    public NotificationVisibility clone()
    {
        return obtain(key, rank, visible);
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if(obj instanceof NotificationVisibility)
        {
            obj = (NotificationVisibility)obj;
            boolean flag;
            if(key == null && ((NotificationVisibility) (obj)).key == null)
                flag = true;
            else
                flag = key.equals(((NotificationVisibility) (obj)).key);
            return flag;
        } else
        {
            return false;
        }
    }

    public int hashCode()
    {
        int i;
        if(key == null)
            i = 0;
        else
            i = key.hashCode();
        return i;
    }

    public void recycle()
    {
        if(key == null)
            return;
        key = null;
        if(sPool.size() >= 25) goto _L2; else goto _L1
_L1:
        ArrayDeque arraydeque = sPool;
        arraydeque;
        JVM INSTR monitorenter ;
        sPool.offer(this);
        arraydeque;
        JVM INSTR monitorexit ;
_L2:
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public String toString()
    {
        StringBuilder stringbuilder = (new StringBuilder()).append("NotificationVisibility(id=").append(id).append("key=").append(key).append(" rank=").append(rank);
        String s;
        if(visible)
            s = " visible";
        else
            s = "";
        return stringbuilder.append(s).append(" )").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(key);
        parcel.writeInt(rank);
        if(visible)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public NotificationVisibility createFromParcel(Parcel parcel)
        {
            return NotificationVisibility._2D_wrap0(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public NotificationVisibility[] newArray(int i)
        {
            return new NotificationVisibility[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final int MAX_POOL_SIZE = 25;
    private static final String TAG = "NoViz";
    private static int sNexrId = 0;
    private static ArrayDeque sPool = new ArrayDeque(25);
    int id;
    public String key;
    public int rank;
    public boolean visible;

}
