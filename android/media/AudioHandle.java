// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;


class AudioHandle
{

    AudioHandle(int i)
    {
        mId = i;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj == null || (obj instanceof AudioHandle) ^ true)
            return false;
        obj = (AudioHandle)obj;
        if(mId == ((AudioHandle) (obj)).id())
            flag = true;
        return flag;
    }

    public int hashCode()
    {
        return mId;
    }

    int id()
    {
        return mId;
    }

    public String toString()
    {
        return Integer.toString(mId);
    }

    private final int mId;
}
