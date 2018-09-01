// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.content.ComponentName;
import android.os.*;

public interface IBluetoothProfileServiceConnection
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IBluetoothProfileServiceConnection
    {

        public static IBluetoothProfileServiceConnection asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.bluetooth.IBluetoothProfileServiceConnection");
            if(iinterface != null && (iinterface instanceof IBluetoothProfileServiceConnection))
                return (IBluetoothProfileServiceConnection)iinterface;
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
                parcel1.writeString("android.bluetooth.IBluetoothProfileServiceConnection");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.bluetooth.IBluetoothProfileServiceConnection");
                if(parcel.readInt() != 0)
                    parcel1 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                onServiceConnected(parcel1, parcel.readStrongBinder());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.bluetooth.IBluetoothProfileServiceConnection");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            onServiceDisconnected(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.bluetooth.IBluetoothProfileServiceConnection";
        static final int TRANSACTION_onServiceConnected = 1;
        static final int TRANSACTION_onServiceDisconnected = 2;

        public Stub()
        {
            attachInterface(this, "android.bluetooth.IBluetoothProfileServiceConnection");
        }
    }

    private static class Stub.Proxy
        implements IBluetoothProfileServiceConnection
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.bluetooth.IBluetoothProfileServiceConnection";
        }

        public void onServiceConnected(ComponentName componentname, IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothProfileServiceConnection");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_49;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel.recycle();
            throw componentname;
        }

        public void onServiceDisconnected(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothProfileServiceConnection");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel.recycle();
            throw componentname;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onServiceConnected(ComponentName componentname, IBinder ibinder)
        throws RemoteException;

    public abstract void onServiceDisconnected(ComponentName componentname)
        throws RemoteException;
}
