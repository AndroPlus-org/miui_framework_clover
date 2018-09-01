// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.*;

// Referenced classes of package android.net:
//            LinkAddress, RouteInfo

public interface INetworkManagementEventObserver
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements INetworkManagementEventObserver
    {

        public static INetworkManagementEventObserver asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.net.INetworkManagementEventObserver");
            if(iinterface != null && (iinterface instanceof INetworkManagementEventObserver))
                return (INetworkManagementEventObserver)iinterface;
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
                parcel1.writeString("android.net.INetworkManagementEventObserver");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.net.INetworkManagementEventObserver");
                parcel1 = parcel.readString();
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                interfaceStatusChanged(parcel1, flag);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.net.INetworkManagementEventObserver");
                parcel1 = parcel.readString();
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                interfaceLinkStateChanged(parcel1, flag1);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.net.INetworkManagementEventObserver");
                interfaceAdded(parcel.readString());
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.net.INetworkManagementEventObserver");
                interfaceRemoved(parcel.readString());
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.net.INetworkManagementEventObserver");
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (LinkAddress)LinkAddress.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                addressUpdated(parcel1, parcel);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.net.INetworkManagementEventObserver");
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (LinkAddress)LinkAddress.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                addressRemoved(parcel1, parcel);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.net.INetworkManagementEventObserver");
                limitReached(parcel.readString(), parcel.readString());
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.net.INetworkManagementEventObserver");
                parcel1 = parcel.readString();
                boolean flag2;
                if(parcel.readInt() != 0)
                    flag2 = true;
                else
                    flag2 = false;
                interfaceClassDataActivityChanged(parcel1, flag2, parcel.readLong());
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.net.INetworkManagementEventObserver");
                interfaceDnsServerInfo(parcel.readString(), parcel.readLong(), parcel.createStringArray());
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.net.INetworkManagementEventObserver");
                if(parcel.readInt() != 0)
                    parcel = (RouteInfo)RouteInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                routeUpdated(parcel);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.net.INetworkManagementEventObserver");
                if(parcel.readInt() != 0)
                    parcel = (RouteInfo)RouteInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                routeRemoved(parcel);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.net.INetworkManagementEventObserver");
                interfaceConfigurationLost();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.net.INetworkManagementEventObserver";
        static final int TRANSACTION_addressRemoved = 6;
        static final int TRANSACTION_addressUpdated = 5;
        static final int TRANSACTION_interfaceAdded = 3;
        static final int TRANSACTION_interfaceClassDataActivityChanged = 8;
        static final int TRANSACTION_interfaceConfigurationLost = 12;
        static final int TRANSACTION_interfaceDnsServerInfo = 9;
        static final int TRANSACTION_interfaceLinkStateChanged = 2;
        static final int TRANSACTION_interfaceRemoved = 4;
        static final int TRANSACTION_interfaceStatusChanged = 1;
        static final int TRANSACTION_limitReached = 7;
        static final int TRANSACTION_routeRemoved = 11;
        static final int TRANSACTION_routeUpdated = 10;

        public Stub()
        {
            attachInterface(this, "android.net.INetworkManagementEventObserver");
        }
    }

    private static class Stub.Proxy
        implements INetworkManagementEventObserver
    {

        public void addressRemoved(String s, LinkAddress linkaddress)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkManagementEventObserver");
            parcel.writeString(s);
            if(linkaddress == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            linkaddress.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void addressUpdated(String s, LinkAddress linkaddress)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkManagementEventObserver");
            parcel.writeString(s);
            if(linkaddress == null)
                break MISSING_BLOCK_LABEL_49;
            parcel.writeInt(1);
            linkaddress.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.net.INetworkManagementEventObserver";
        }

        public void interfaceAdded(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkManagementEventObserver");
            parcel.writeString(s);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void interfaceClassDataActivityChanged(String s, boolean flag, long l)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkManagementEventObserver");
            parcel.writeString(s);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            parcel.writeLong(l);
            mRemote.transact(8, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void interfaceConfigurationLost()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkManagementEventObserver");
            mRemote.transact(12, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void interfaceDnsServerInfo(String s, long l, String as[])
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkManagementEventObserver");
            parcel.writeString(s);
            parcel.writeLong(l);
            parcel.writeStringArray(as);
            mRemote.transact(9, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void interfaceLinkStateChanged(String s, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkManagementEventObserver");
            parcel.writeString(s);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void interfaceRemoved(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkManagementEventObserver");
            parcel.writeString(s);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void interfaceStatusChanged(String s, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkManagementEventObserver");
            parcel.writeString(s);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void limitReached(String s, String s1)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkManagementEventObserver");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void routeRemoved(RouteInfo routeinfo)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkManagementEventObserver");
            if(routeinfo == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            routeinfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(11, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            routeinfo;
            parcel.recycle();
            throw routeinfo;
        }

        public void routeUpdated(RouteInfo routeinfo)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkManagementEventObserver");
            if(routeinfo == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            routeinfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(10, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            routeinfo;
            parcel.recycle();
            throw routeinfo;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void addressRemoved(String s, LinkAddress linkaddress)
        throws RemoteException;

    public abstract void addressUpdated(String s, LinkAddress linkaddress)
        throws RemoteException;

    public abstract void interfaceAdded(String s)
        throws RemoteException;

    public abstract void interfaceClassDataActivityChanged(String s, boolean flag, long l)
        throws RemoteException;

    public abstract void interfaceConfigurationLost()
        throws RemoteException;

    public abstract void interfaceDnsServerInfo(String s, long l, String as[])
        throws RemoteException;

    public abstract void interfaceLinkStateChanged(String s, boolean flag)
        throws RemoteException;

    public abstract void interfaceRemoved(String s)
        throws RemoteException;

    public abstract void interfaceStatusChanged(String s, boolean flag)
        throws RemoteException;

    public abstract void limitReached(String s, String s1)
        throws RemoteException;

    public abstract void routeRemoved(RouteInfo routeinfo)
        throws RemoteException;

    public abstract void routeUpdated(RouteInfo routeinfo)
        throws RemoteException;
}
