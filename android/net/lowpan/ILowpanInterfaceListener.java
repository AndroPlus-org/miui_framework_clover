// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.lowpan;

import android.net.IpPrefix;
import android.os.*;

// Referenced classes of package android.net.lowpan:
//            LowpanIdentity

public interface ILowpanInterfaceListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ILowpanInterfaceListener
    {

        public static ILowpanInterfaceListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.net.lowpan.ILowpanInterfaceListener");
            if(iinterface != null && (iinterface instanceof ILowpanInterfaceListener))
                return (ILowpanInterfaceListener)iinterface;
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
                parcel1.writeString("android.net.lowpan.ILowpanInterfaceListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.net.lowpan.ILowpanInterfaceListener");
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                onEnabledChanged(flag);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.net.lowpan.ILowpanInterfaceListener");
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                onConnectedChanged(flag1);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.net.lowpan.ILowpanInterfaceListener");
                boolean flag2;
                if(parcel.readInt() != 0)
                    flag2 = true;
                else
                    flag2 = false;
                onUpChanged(flag2);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.net.lowpan.ILowpanInterfaceListener");
                onRoleChanged(parcel.readString());
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.net.lowpan.ILowpanInterfaceListener");
                onStateChanged(parcel.readString());
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.net.lowpan.ILowpanInterfaceListener");
                if(parcel.readInt() != 0)
                    parcel = (LowpanIdentity)LowpanIdentity.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onLowpanIdentityChanged(parcel);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.net.lowpan.ILowpanInterfaceListener");
                if(parcel.readInt() != 0)
                    parcel = (IpPrefix)IpPrefix.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onLinkNetworkAdded(parcel);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.net.lowpan.ILowpanInterfaceListener");
                if(parcel.readInt() != 0)
                    parcel = (IpPrefix)IpPrefix.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onLinkNetworkRemoved(parcel);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.net.lowpan.ILowpanInterfaceListener");
                onLinkAddressAdded(parcel.readString());
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.net.lowpan.ILowpanInterfaceListener");
                onLinkAddressRemoved(parcel.readString());
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.net.lowpan.ILowpanInterfaceListener");
                onReceiveFromCommissioner(parcel.createByteArray());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.net.lowpan.ILowpanInterfaceListener";
        static final int TRANSACTION_onConnectedChanged = 2;
        static final int TRANSACTION_onEnabledChanged = 1;
        static final int TRANSACTION_onLinkAddressAdded = 9;
        static final int TRANSACTION_onLinkAddressRemoved = 10;
        static final int TRANSACTION_onLinkNetworkAdded = 7;
        static final int TRANSACTION_onLinkNetworkRemoved = 8;
        static final int TRANSACTION_onLowpanIdentityChanged = 6;
        static final int TRANSACTION_onReceiveFromCommissioner = 11;
        static final int TRANSACTION_onRoleChanged = 4;
        static final int TRANSACTION_onStateChanged = 5;
        static final int TRANSACTION_onUpChanged = 3;

        public Stub()
        {
            attachInterface(this, "android.net.lowpan.ILowpanInterfaceListener");
        }
    }

    private static class Stub.Proxy
        implements ILowpanInterfaceListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.net.lowpan.ILowpanInterfaceListener";
        }

        public void onConnectedChanged(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterfaceListener");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onEnabledChanged(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterfaceListener");
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

        public void onLinkAddressAdded(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterfaceListener");
            parcel.writeString(s);
            mRemote.transact(9, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onLinkAddressRemoved(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterfaceListener");
            parcel.writeString(s);
            mRemote.transact(10, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onLinkNetworkAdded(IpPrefix ipprefix)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterfaceListener");
            if(ipprefix == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            ipprefix.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ipprefix;
            parcel.recycle();
            throw ipprefix;
        }

        public void onLinkNetworkRemoved(IpPrefix ipprefix)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterfaceListener");
            if(ipprefix == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            ipprefix.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(8, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ipprefix;
            parcel.recycle();
            throw ipprefix;
        }

        public void onLowpanIdentityChanged(LowpanIdentity lowpanidentity)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterfaceListener");
            if(lowpanidentity == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            lowpanidentity.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            lowpanidentity;
            parcel.recycle();
            throw lowpanidentity;
        }

        public void onReceiveFromCommissioner(byte abyte0[])
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterfaceListener");
            parcel.writeByteArray(abyte0);
            mRemote.transact(11, parcel, null, 1);
            parcel.recycle();
            return;
            abyte0;
            parcel.recycle();
            throw abyte0;
        }

        public void onRoleChanged(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterfaceListener");
            parcel.writeString(s);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onStateChanged(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterfaceListener");
            parcel.writeString(s);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onUpChanged(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterfaceListener");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onConnectedChanged(boolean flag)
        throws RemoteException;

    public abstract void onEnabledChanged(boolean flag)
        throws RemoteException;

    public abstract void onLinkAddressAdded(String s)
        throws RemoteException;

    public abstract void onLinkAddressRemoved(String s)
        throws RemoteException;

    public abstract void onLinkNetworkAdded(IpPrefix ipprefix)
        throws RemoteException;

    public abstract void onLinkNetworkRemoved(IpPrefix ipprefix)
        throws RemoteException;

    public abstract void onLowpanIdentityChanged(LowpanIdentity lowpanidentity)
        throws RemoteException;

    public abstract void onReceiveFromCommissioner(byte abyte0[])
        throws RemoteException;

    public abstract void onRoleChanged(String s)
        throws RemoteException;

    public abstract void onStateChanged(String s)
        throws RemoteException;

    public abstract void onUpChanged(boolean flag)
        throws RemoteException;
}
