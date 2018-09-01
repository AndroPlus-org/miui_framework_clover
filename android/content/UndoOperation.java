// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package android.content:
//            UndoOwner

public abstract class UndoOperation
    implements Parcelable
{

    public UndoOperation(UndoOwner undoowner)
    {
        mOwner = undoowner;
    }

    protected UndoOperation(Parcel parcel, ClassLoader classloader)
    {
    }

    public boolean allowMerge()
    {
        return true;
    }

    public abstract void commit();

    public int describeContents()
    {
        return 0;
    }

    public UndoOwner getOwner()
    {
        return mOwner;
    }

    public Object getOwnerData()
    {
        return mOwner.getData();
    }

    public boolean hasData()
    {
        return true;
    }

    public boolean matchOwner(UndoOwner undoowner)
    {
        boolean flag;
        if(undoowner == getOwner())
            flag = true;
        else
            flag = false;
        return flag;
    }

    public abstract void redo();

    public abstract void undo();

    UndoOwner mOwner;
}
