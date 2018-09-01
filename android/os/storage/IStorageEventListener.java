// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os.storage;

import android.os.*;

// Referenced classes of package android.os.storage:
//            DiskInfo, VolumeRecord, VolumeInfo

public interface IStorageEventListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IStorageEventListener
    {

        public static IStorageEventListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.os.storage.IStorageEventListener");
            if(iinterface != null && (iinterface instanceof IStorageEventListener))
                return (IStorageEventListener)iinterface;
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
                parcel1.writeString("android.os.storage.IStorageEventListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.os.storage.IStorageEventListener");
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                onUsbMassStorageConnectionChanged(flag);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.os.storage.IStorageEventListener");
                onStorageStateChanged(parcel.readString(), parcel.readString(), parcel.readString());
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.os.storage.IStorageEventListener");
                if(parcel.readInt() != 0)
                    parcel1 = (VolumeInfo)VolumeInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                onVolumeStateChanged(parcel1, parcel.readInt(), parcel.readInt());
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.os.storage.IStorageEventListener");
                if(parcel.readInt() != 0)
                    parcel = (VolumeRecord)VolumeRecord.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onVolumeRecordChanged(parcel);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.os.storage.IStorageEventListener");
                onVolumeForgotten(parcel.readString());
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.os.storage.IStorageEventListener");
                if(parcel.readInt() != 0)
                    parcel1 = (DiskInfo)DiskInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                onDiskScanned(parcel1, parcel.readInt());
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.os.storage.IStorageEventListener");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (DiskInfo)DiskInfo.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            onDiskDestroyed(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.os.storage.IStorageEventListener";
        static final int TRANSACTION_onDiskDestroyed = 7;
        static final int TRANSACTION_onDiskScanned = 6;
        static final int TRANSACTION_onStorageStateChanged = 2;
        static final int TRANSACTION_onUsbMassStorageConnectionChanged = 1;
        static final int TRANSACTION_onVolumeForgotten = 5;
        static final int TRANSACTION_onVolumeRecordChanged = 4;
        static final int TRANSACTION_onVolumeStateChanged = 3;

        public Stub()
        {
            attachInterface(this, "android.os.storage.IStorageEventListener");
        }
    }

    private static class Stub.Proxy
        implements IStorageEventListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.os.storage.IStorageEventListener";
        }

        public void onDiskDestroyed(DiskInfo diskinfo)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageEventListener");
            if(diskinfo == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            diskinfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            diskinfo;
            parcel.recycle();
            throw diskinfo;
        }

        public void onDiskScanned(DiskInfo diskinfo, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageEventListener");
            if(diskinfo == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            diskinfo.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            diskinfo;
            parcel.recycle();
            throw diskinfo;
        }

        public void onStorageStateChanged(String s, String s1, String s2)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageEventListener");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onUsbMassStorageConnectionChanged(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageEventListener");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onVolumeForgotten(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageEventListener");
            parcel.writeString(s);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onVolumeRecordChanged(VolumeRecord volumerecord)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageEventListener");
            if(volumerecord == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            volumerecord.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            volumerecord;
            parcel.recycle();
            throw volumerecord;
        }

        public void onVolumeStateChanged(VolumeInfo volumeinfo, int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageEventListener");
            if(volumeinfo == null)
                break MISSING_BLOCK_LABEL_62;
            parcel.writeInt(1);
            volumeinfo.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            volumeinfo;
            parcel.recycle();
            throw volumeinfo;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onDiskDestroyed(DiskInfo diskinfo)
        throws RemoteException;

    public abstract void onDiskScanned(DiskInfo diskinfo, int i)
        throws RemoteException;

    public abstract void onStorageStateChanged(String s, String s1, String s2)
        throws RemoteException;

    public abstract void onUsbMassStorageConnectionChanged(boolean flag)
        throws RemoteException;

    public abstract void onVolumeForgotten(String s)
        throws RemoteException;

    public abstract void onVolumeRecordChanged(VolumeRecord volumerecord)
        throws RemoteException;

    public abstract void onVolumeStateChanged(VolumeInfo volumeinfo, int i, int j)
        throws RemoteException;
}
