// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.database;

import android.os.*;

// Referenced classes of package android.database:
//            IBulkCursor, BulkCursorProxy, CursorWindow, DatabaseUtils

public abstract class BulkCursorNative extends Binder
    implements IBulkCursor
{

    public BulkCursorNative()
    {
        attachInterface(this, "android.content.IBulkCursor");
    }

    public static IBulkCursor asInterface(IBinder ibinder)
    {
        if(ibinder == null)
            return null;
        IBulkCursor ibulkcursor = (IBulkCursor)ibinder.queryLocalInterface("android.content.IBulkCursor");
        if(ibulkcursor != null)
            return ibulkcursor;
        else
            return new BulkCursorProxy(ibinder);
    }

    public IBinder asBinder()
    {
        return this;
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
        throws RemoteException
    {
        i;
        JVM INSTR tableswitch 1 7: default 44
    //                   1 54
    //                   2 106
    //                   3 138
    //                   4 175
    //                   5 195
    //                   6 217
    //                   7 122;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
        return super.onTransact(i, parcel, parcel1, j);
_L2:
        try
        {
            parcel.enforceInterface("android.content.IBulkCursor");
            parcel = getWindow(parcel.readInt());
            parcel1.writeNoException();
        }
        // Misplaced declaration of an exception variable
        catch(Parcel parcel)
        {
            DatabaseUtils.writeExceptionToParcel(parcel1, parcel);
            return true;
        }
        if(parcel != null) goto _L10; else goto _L9
_L9:
        parcel1.writeInt(0);
_L12:
        return true;
_L10:
        parcel1.writeInt(1);
        parcel.writeToParcel(parcel1, 1);
        if(true) goto _L12; else goto _L11
_L11:
_L3:
        parcel.enforceInterface("android.content.IBulkCursor");
        deactivate();
        parcel1.writeNoException();
        return true;
_L8:
        parcel.enforceInterface("android.content.IBulkCursor");
        close();
        parcel1.writeNoException();
        return true;
_L4:
        parcel.enforceInterface("android.content.IBulkCursor");
        i = requery(IContentObserver.Stub.asInterface(parcel.readStrongBinder()));
        parcel1.writeNoException();
        parcel1.writeInt(i);
        parcel1.writeBundle(getExtras());
        return true;
_L5:
        parcel.enforceInterface("android.content.IBulkCursor");
        onMove(parcel.readInt());
        parcel1.writeNoException();
        return true;
_L6:
        parcel.enforceInterface("android.content.IBulkCursor");
        parcel = getExtras();
        parcel1.writeNoException();
        parcel1.writeBundle(parcel);
        return true;
_L7:
        parcel.enforceInterface("android.content.IBulkCursor");
        parcel = respond(parcel.readBundle());
        parcel1.writeNoException();
        parcel1.writeBundle(parcel);
        return true;
    }
}
