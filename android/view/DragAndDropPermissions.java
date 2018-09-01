// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.os.*;
import com.android.internal.view.IDragAndDropPermissions;

// Referenced classes of package android.view:
//            DragEvent

public final class DragAndDropPermissions
    implements Parcelable
{

    private DragAndDropPermissions(Parcel parcel)
    {
        mDragAndDropPermissions = com.android.internal.view.IDragAndDropPermissions.Stub.asInterface(parcel.readStrongBinder());
        mTransientToken = parcel.readStrongBinder();
    }

    DragAndDropPermissions(Parcel parcel, DragAndDropPermissions draganddroppermissions)
    {
        this(parcel);
    }

    private DragAndDropPermissions(IDragAndDropPermissions idraganddroppermissions)
    {
        mDragAndDropPermissions = idraganddroppermissions;
    }

    public static DragAndDropPermissions obtain(DragEvent dragevent)
    {
        if(dragevent.getDragAndDropPermissions() == null)
            return null;
        else
            return new DragAndDropPermissions(dragevent.getDragAndDropPermissions());
    }

    public int describeContents()
    {
        return 0;
    }

    public void release()
    {
        mDragAndDropPermissions.release();
        mTransientToken = null;
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public boolean take(IBinder ibinder)
    {
        try
        {
            mDragAndDropPermissions.take(ibinder);
        }
        // Misplaced declaration of an exception variable
        catch(IBinder ibinder)
        {
            return false;
        }
        return true;
    }

    public boolean takeTransient()
    {
        try
        {
            Binder binder = JVM INSTR new #73  <Class Binder>;
            binder.Binder();
            mTransientToken = binder;
            mDragAndDropPermissions.takeTransient(mTransientToken);
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        return true;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeStrongInterface(mDragAndDropPermissions);
        parcel.writeStrongBinder(mTransientToken);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public DragAndDropPermissions createFromParcel(Parcel parcel)
        {
            return new DragAndDropPermissions(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public DragAndDropPermissions[] newArray(int i)
        {
            return new DragAndDropPermissions[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final IDragAndDropPermissions mDragAndDropPermissions;
    private IBinder mTransientToken;

}
