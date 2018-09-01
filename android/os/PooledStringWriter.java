// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import java.util.HashMap;

// Referenced classes of package android.os:
//            Parcel

public class PooledStringWriter
{

    public PooledStringWriter(Parcel parcel)
    {
        mOut = parcel;
        mStart = parcel.dataPosition();
        parcel.writeInt(0);
    }

    public void finish()
    {
        int i = mOut.dataPosition();
        mOut.setDataPosition(mStart);
        mOut.writeInt(mNext);
        mOut.setDataPosition(i);
    }

    public int getStringCount()
    {
        return mPool.size();
    }

    public void writeString(String s)
    {
        Integer integer = (Integer)mPool.get(s);
        if(integer != null)
        {
            mOut.writeInt(integer.intValue());
        } else
        {
            mPool.put(s, Integer.valueOf(mNext));
            mOut.writeInt(-(mNext + 1));
            mOut.writeString(s);
            mNext = mNext + 1;
        }
    }

    private int mNext;
    private final Parcel mOut;
    private final HashMap mPool = new HashMap();
    private int mStart;
}
