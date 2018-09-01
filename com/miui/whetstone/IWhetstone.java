// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.os.*;
import java.util.List;

// Referenced classes of package com.miui.whetstone:
//            CloudControlInfo

public interface IWhetstone
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IWhetstone
    {

        public static IWhetstone asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.miui.whetstone.IWhetstone");
            if(iinterface != null && (iinterface instanceof IWhetstone))
                return (IWhetstone)iinterface;
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
                parcel1.writeString("com.miui.whetstone.IWhetstone");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.miui.whetstone.IWhetstone");
                ComponentName componentname;
                if(parcel.readInt() != 0)
                    componentname = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname = null;
                if(parcel.readInt() != 0)
                    parcel = (CloudControlInfo)CloudControlInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = registerMiuiWhetstoneCloudSync(componentname, parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.miui.whetstone.IWhetstone");
                ComponentName componentname1;
                if(parcel.readInt() != 0)
                    componentname1 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname1 = null;
                i = registerMiuiWhetstoneCloudSyncList(componentname1, parcel.createTypedArrayList(CloudControlInfo.CREATOR));
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.miui.whetstone.IWhetstone");
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = unregisterMiuiWhetstoneCloudSync(parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.miui.whetstone.IWhetstone");
                log(parcel.readInt(), parcel.createByteArray());
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.miui.whetstone.IWhetstone");
                i = parcel.readInt();
                break;
            }
            boolean flag;
            if(parcel.readInt() != 0)
                parcel1 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
            else
                parcel1 = null;
            if(parcel.readInt() != 0)
                flag = true;
            else
                flag = false;
            recordRTCWakeupInfo(i, parcel1, flag);
            return true;
        }

        private static final String DESCRIPTOR = "com.miui.whetstone.IWhetstone";
        static final int TRANSACTION_log = 4;
        static final int TRANSACTION_recordRTCWakeupInfo = 5;
        static final int TRANSACTION_registerMiuiWhetstoneCloudSync = 1;
        static final int TRANSACTION_registerMiuiWhetstoneCloudSyncList = 2;
        static final int TRANSACTION_unregisterMiuiWhetstoneCloudSync = 3;

        public Stub()
        {
            attachInterface(this, "com.miui.whetstone.IWhetstone");
        }
    }

    private static class Stub.Proxy
        implements IWhetstone
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.miui.whetstone.IWhetstone";
        }

        public void log(int i, byte abyte0[])
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.IWhetstone");
            parcel.writeInt(i);
            parcel.writeByteArray(abyte0);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            abyte0;
            parcel.recycle();
            throw abyte0;
        }

        public void recordRTCWakeupInfo(int i, PendingIntent pendingintent, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.IWhetstone");
            parcel.writeInt(i);
            if(pendingintent == null)
                break MISSING_BLOCK_LABEL_72;
            parcel.writeInt(1);
            pendingintent.writeToParcel(parcel, 0);
_L1:
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            pendingintent;
            parcel.recycle();
            throw pendingintent;
        }

        public int registerMiuiWhetstoneCloudSync(ComponentName componentname, CloudControlInfo cloudcontrolinfo)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.IWhetstone");
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L3:
            if(cloudcontrolinfo == null)
                break MISSING_BLOCK_LABEL_104;
            parcel.writeInt(1);
            cloudcontrolinfo.writeToParcel(parcel, 0);
_L4:
            int i;
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
_L2:
            parcel.writeInt(0);
              goto _L3
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
            parcel.writeInt(0);
              goto _L4
        }

        public int registerMiuiWhetstoneCloudSyncList(ComponentName componentname, List list)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.IWhetstone");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_74;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            int i;
            parcel.writeTypedList(list);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public int unregisterMiuiWhetstoneCloudSync(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.IWhetstone");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_64;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void log(int i, byte abyte0[])
        throws RemoteException;

    public abstract void recordRTCWakeupInfo(int i, PendingIntent pendingintent, boolean flag)
        throws RemoteException;

    public abstract int registerMiuiWhetstoneCloudSync(ComponentName componentname, CloudControlInfo cloudcontrolinfo)
        throws RemoteException;

    public abstract int registerMiuiWhetstoneCloudSyncList(ComponentName componentname, List list)
        throws RemoteException;

    public abstract int unregisterMiuiWhetstoneCloudSync(ComponentName componentname)
        throws RemoteException;
}
