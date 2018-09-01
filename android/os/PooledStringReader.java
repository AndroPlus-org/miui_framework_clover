// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;


// Referenced classes of package android.os:
//            Parcel

public class PooledStringReader
{

    public PooledStringReader(Parcel parcel)
    {
        mIn = parcel;
        mPool = new String[parcel.readInt()];
    }

    public int getStringCount()
    {
        return mPool.length;
    }

    public String readString()
    {
        int i = mIn.readInt();
        if(i >= 0)
        {
            return mPool[i];
        } else
        {
            i = -i;
            String s = mIn.readString();
            mPool[i - 1] = s;
            return s;
        }
    }

    private final Parcel mIn;
    private final String mPool[];
}
