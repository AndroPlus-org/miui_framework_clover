// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;


// Referenced classes of package android.content:
//            UndoManager

public class UndoOwner
{

    UndoOwner(String s, UndoManager undomanager)
    {
        if(s == null)
            throw new NullPointerException("tag can't be null");
        if(undomanager == null)
        {
            throw new NullPointerException("manager can't be null");
        } else
        {
            mTag = s;
            mManager = undomanager;
            return;
        }
    }

    public Object getData()
    {
        return mData;
    }

    public String getTag()
    {
        return mTag;
    }

    public String toString()
    {
        return (new StringBuilder()).append("UndoOwner:[mTag=").append(mTag).append(" mManager=").append(mManager).append(" mData=").append(mData).append(" mData=").append(mData).append(" mOpCount=").append(mOpCount).append(" mStateSeq=").append(mStateSeq).append(" mSavedIdx=").append(mSavedIdx).append("]").toString();
    }

    Object mData;
    final UndoManager mManager;
    int mOpCount;
    int mSavedIdx;
    int mStateSeq;
    final String mTag;
}
