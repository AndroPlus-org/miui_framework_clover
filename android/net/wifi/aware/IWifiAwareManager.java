// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi.aware;

import android.os.*;

// Referenced classes of package android.net.wifi.aware:
//            IWifiAwareEventCallback, ConfigRequest, Characteristics, PublishConfig, 
//            IWifiAwareDiscoverySessionCallback, SubscribeConfig

public interface IWifiAwareManager
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IWifiAwareManager
    {

        public static IWifiAwareManager asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.net.wifi.aware.IWifiAwareManager");
            if(iinterface != null && (iinterface instanceof IWifiAwareManager))
                return (IWifiAwareManager)iinterface;
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
                parcel1.writeString("android.net.wifi.aware.IWifiAwareManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.net.wifi.aware.IWifiAwareManager");
                boolean flag = isUsageEnabled();
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.net.wifi.aware.IWifiAwareManager");
                parcel = getCharacteristics();
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.net.wifi.aware.IWifiAwareManager");
                IBinder ibinder = parcel.readStrongBinder();
                String s = parcel.readString();
                IWifiAwareEventCallback iwifiawareeventcallback = IWifiAwareEventCallback.Stub.asInterface(parcel.readStrongBinder());
                boolean flag1;
                ConfigRequest configrequest;
                if(parcel.readInt() != 0)
                    configrequest = (ConfigRequest)ConfigRequest.CREATOR.createFromParcel(parcel);
                else
                    configrequest = null;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                connect(ibinder, s, iwifiawareeventcallback, configrequest, flag1);
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.net.wifi.aware.IWifiAwareManager");
                disconnect(parcel.readInt(), parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.net.wifi.aware.IWifiAwareManager");
                i = parcel.readInt();
                PublishConfig publishconfig;
                if(parcel.readInt() != 0)
                    publishconfig = (PublishConfig)PublishConfig.CREATOR.createFromParcel(parcel);
                else
                    publishconfig = null;
                publish(i, publishconfig, IWifiAwareDiscoverySessionCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.net.wifi.aware.IWifiAwareManager");
                i = parcel.readInt();
                SubscribeConfig subscribeconfig;
                if(parcel.readInt() != 0)
                    subscribeconfig = (SubscribeConfig)SubscribeConfig.CREATOR.createFromParcel(parcel);
                else
                    subscribeconfig = null;
                subscribe(i, subscribeconfig, IWifiAwareDiscoverySessionCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.net.wifi.aware.IWifiAwareManager");
                i = parcel.readInt();
                j = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (PublishConfig)PublishConfig.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                updatePublish(i, j, parcel);
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.net.wifi.aware.IWifiAwareManager");
                j = parcel.readInt();
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (SubscribeConfig)SubscribeConfig.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                updateSubscribe(j, i, parcel);
                parcel1.writeNoException();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.net.wifi.aware.IWifiAwareManager");
                sendMessage(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.createByteArray(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.net.wifi.aware.IWifiAwareManager");
                terminateSession(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.net.wifi.aware.IWifiAwareManager");
                i = parcel.readInt();
                j = parcel.readInt();
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (android.net.wifi.RttManager.ParcelableRttParams)android.net.wifi.RttManager.ParcelableRttParams.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            i = startRanging(i, j, parcel);
            parcel1.writeNoException();
            parcel1.writeInt(i);
            return true;
        }

        private static final String DESCRIPTOR = "android.net.wifi.aware.IWifiAwareManager";
        static final int TRANSACTION_connect = 3;
        static final int TRANSACTION_disconnect = 4;
        static final int TRANSACTION_getCharacteristics = 2;
        static final int TRANSACTION_isUsageEnabled = 1;
        static final int TRANSACTION_publish = 5;
        static final int TRANSACTION_sendMessage = 9;
        static final int TRANSACTION_startRanging = 11;
        static final int TRANSACTION_subscribe = 6;
        static final int TRANSACTION_terminateSession = 10;
        static final int TRANSACTION_updatePublish = 7;
        static final int TRANSACTION_updateSubscribe = 8;

        public Stub()
        {
            attachInterface(this, "android.net.wifi.aware.IWifiAwareManager");
        }
    }

    private static class Stub.Proxy
        implements IWifiAwareManager
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void connect(IBinder ibinder, String s, IWifiAwareEventCallback iwifiawareeventcallback, ConfigRequest configrequest, boolean flag)
            throws RemoteException
        {
            int i;
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.aware.IWifiAwareManager");
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            ibinder = obj;
            if(iwifiawareeventcallback == null)
                break MISSING_BLOCK_LABEL_49;
            ibinder = iwifiawareeventcallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(configrequest == null)
                break MISSING_BLOCK_LABEL_118;
            parcel.writeInt(1);
            configrequest.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void disconnect(int i, IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.aware.IWifiAwareManager");
            parcel.writeInt(i);
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public Characteristics getCharacteristics()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.aware.IWifiAwareManager");
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Characteristics characteristics = (Characteristics)Characteristics.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return characteristics;
_L2:
            characteristics = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "android.net.wifi.aware.IWifiAwareManager";
        }

        public boolean isUsageEnabled()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.net.wifi.aware.IWifiAwareManager");
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void publish(int i, PublishConfig publishconfig, IWifiAwareDiscoverySessionCallback iwifiawarediscoverysessioncallback)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.aware.IWifiAwareManager");
            parcel.writeInt(i);
            if(publishconfig == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            publishconfig.writeToParcel(parcel, 0);
_L4:
            publishconfig = obj;
            if(iwifiawarediscoverysessioncallback == null)
                break MISSING_BLOCK_LABEL_57;
            publishconfig = iwifiawarediscoverysessioncallback.asBinder();
            parcel.writeStrongBinder(publishconfig);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
            if(true) goto _L4; else goto _L3
_L3:
            publishconfig;
            parcel1.recycle();
            parcel.recycle();
            throw publishconfig;
        }

        public void sendMessage(int i, int j, int k, byte abyte0[], int l, int i1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.aware.IWifiAwareManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeByteArray(abyte0);
            parcel.writeInt(l);
            parcel.writeInt(i1);
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            abyte0;
            parcel1.recycle();
            parcel.recycle();
            throw abyte0;
        }

        public int startRanging(int i, int j, android.net.wifi.RttManager.ParcelableRttParams parcelablerttparams)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.aware.IWifiAwareManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            if(parcelablerttparams == null)
                break MISSING_BLOCK_LABEL_86;
            parcel.writeInt(1);
            parcelablerttparams.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            parcelablerttparams;
            parcel1.recycle();
            parcel.recycle();
            throw parcelablerttparams;
        }

        public void subscribe(int i, SubscribeConfig subscribeconfig, IWifiAwareDiscoverySessionCallback iwifiawarediscoverysessioncallback)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.aware.IWifiAwareManager");
            parcel.writeInt(i);
            if(subscribeconfig == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            subscribeconfig.writeToParcel(parcel, 0);
_L4:
            subscribeconfig = obj;
            if(iwifiawarediscoverysessioncallback == null)
                break MISSING_BLOCK_LABEL_57;
            subscribeconfig = iwifiawarediscoverysessioncallback.asBinder();
            parcel.writeStrongBinder(subscribeconfig);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
            if(true) goto _L4; else goto _L3
_L3:
            subscribeconfig;
            parcel1.recycle();
            parcel.recycle();
            throw subscribeconfig;
        }

        public void terminateSession(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.aware.IWifiAwareManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void updatePublish(int i, int j, PublishConfig publishconfig)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.aware.IWifiAwareManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            if(publishconfig == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            publishconfig.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            publishconfig;
            parcel1.recycle();
            parcel.recycle();
            throw publishconfig;
        }

        public void updateSubscribe(int i, int j, SubscribeConfig subscribeconfig)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.aware.IWifiAwareManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            if(subscribeconfig == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            subscribeconfig.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            subscribeconfig;
            parcel1.recycle();
            parcel.recycle();
            throw subscribeconfig;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void connect(IBinder ibinder, String s, IWifiAwareEventCallback iwifiawareeventcallback, ConfigRequest configrequest, boolean flag)
        throws RemoteException;

    public abstract void disconnect(int i, IBinder ibinder)
        throws RemoteException;

    public abstract Characteristics getCharacteristics()
        throws RemoteException;

    public abstract boolean isUsageEnabled()
        throws RemoteException;

    public abstract void publish(int i, PublishConfig publishconfig, IWifiAwareDiscoverySessionCallback iwifiawarediscoverysessioncallback)
        throws RemoteException;

    public abstract void sendMessage(int i, int j, int k, byte abyte0[], int l, int i1)
        throws RemoteException;

    public abstract int startRanging(int i, int j, android.net.wifi.RttManager.ParcelableRttParams parcelablerttparams)
        throws RemoteException;

    public abstract void subscribe(int i, SubscribeConfig subscribeconfig, IWifiAwareDiscoverySessionCallback iwifiawarediscoverysessioncallback)
        throws RemoteException;

    public abstract void terminateSession(int i, int j)
        throws RemoteException;

    public abstract void updatePublish(int i, int j, PublishConfig publishconfig)
        throws RemoteException;

    public abstract void updateSubscribe(int i, int j, SubscribeConfig subscribeconfig)
        throws RemoteException;
}
