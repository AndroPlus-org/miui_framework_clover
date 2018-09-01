// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.vr;

import android.content.ComponentName;
import android.os.*;

public interface IVrListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IVrListener
    {

        public static IVrListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.service.vr.IVrListener");
            if(iinterface != null && (iinterface instanceof IVrListener))
                return (IVrListener)iinterface;
            else
                return new Proxy(ibinder);
        }

        public IBinder asBinder()
        {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
            throws RemoteException
        {
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.service.vr.IVrListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.service.vr.IVrListener");
                break;
            }
            boolean flag;
            if(parcel.readInt() != 0)
                parcel1 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
            else
                parcel1 = null;
            if(parcel.readInt() != 0)
                flag = true;
            else
                flag = false;
            focusedActivityChanged(parcel1, flag, parcel.readInt());
            return true;
        }

        private static final String DESCRIPTOR = "android.service.vr.IVrListener";
        static final int TRANSACTION_focusedActivityChanged = 1;

        public Stub()
        {
            attachInterface(this, "android.service.vr.IVrListener");
        }
    }

    private static class Stub.Proxy
        implements IVrListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void focusedActivityChanged(ComponentName componentname, boolean flag, int i)
            throws RemoteException
        {
            int j;
            Parcel parcel;
            j = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.vr.IVrListener");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_70;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                j = 0;
            parcel.writeInt(j);
            parcel.writeInt(i);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel.recycle();
            throw componentname;
        }

        public String getInterfaceDescriptor()
        {
            return "android.service.vr.IVrListener";
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void focusedActivityChanged(ComponentName componentname, boolean flag, int i)
        throws RemoteException;
}
