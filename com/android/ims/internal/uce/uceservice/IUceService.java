// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims.internal.uce.uceservice;

import android.os.*;
import com.android.ims.internal.uce.common.UceLong;
import com.android.ims.internal.uce.options.IOptionsListener;
import com.android.ims.internal.uce.options.IOptionsService;
import com.android.ims.internal.uce.presence.IPresenceListener;
import com.android.ims.internal.uce.presence.IPresenceService;

// Referenced classes of package com.android.ims.internal.uce.uceservice:
//            IUceListener

public interface IUceService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IUceService
    {

        public static IUceService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.ims.internal.uce.uceservice.IUceService");
            if(iinterface != null && (iinterface instanceof IUceService))
                return (IUceService)iinterface;
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
                parcel1.writeString("com.android.ims.internal.uce.uceservice.IUceService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.ims.internal.uce.uceservice.IUceService");
                boolean flag = startService(IUceListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.ims.internal.uce.uceservice.IUceService");
                boolean flag1 = stopService();
                parcel1.writeNoException();
                if(flag1)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.ims.internal.uce.uceservice.IUceService");
                boolean flag2 = isServiceStarted();
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.ims.internal.uce.uceservice.IUceService");
                IOptionsListener ioptionslistener = com.android.ims.internal.uce.options.IOptionsListener.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel = (UceLong)UceLong.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = createOptionsService(ioptionslistener, parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.android.ims.internal.uce.uceservice.IUceService");
                IOptionsListener ioptionslistener1 = com.android.ims.internal.uce.options.IOptionsListener.Stub.asInterface(parcel.readStrongBinder());
                UceLong ucelong;
                if(parcel.readInt() != 0)
                    ucelong = (UceLong)UceLong.CREATOR.createFromParcel(parcel);
                else
                    ucelong = null;
                i = createOptionsServiceForSubscription(ioptionslistener1, ucelong, parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                if(ucelong != null)
                {
                    parcel1.writeInt(1);
                    ucelong.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.android.ims.internal.uce.uceservice.IUceService");
                destroyOptionsService(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.android.ims.internal.uce.uceservice.IUceService");
                IPresenceListener ipresencelistener = com.android.ims.internal.uce.presence.IPresenceListener.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel = (UceLong)UceLong.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = createPresenceService(ipresencelistener, parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.android.ims.internal.uce.uceservice.IUceService");
                IPresenceListener ipresencelistener1 = com.android.ims.internal.uce.presence.IPresenceListener.Stub.asInterface(parcel.readStrongBinder());
                UceLong ucelong1;
                if(parcel.readInt() != 0)
                    ucelong1 = (UceLong)UceLong.CREATOR.createFromParcel(parcel);
                else
                    ucelong1 = null;
                i = createPresenceServiceForSubscription(ipresencelistener1, ucelong1, parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                if(ucelong1 != null)
                {
                    parcel1.writeInt(1);
                    ucelong1.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.android.ims.internal.uce.uceservice.IUceService");
                destroyPresenceService(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("com.android.ims.internal.uce.uceservice.IUceService");
                boolean flag3 = getServiceStatus();
                parcel1.writeNoException();
                if(flag3)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("com.android.ims.internal.uce.uceservice.IUceService");
                parcel = getPresenceService();
                parcel1.writeNoException();
                if(parcel != null)
                    parcel = parcel.asBinder();
                else
                    parcel = null;
                parcel1.writeStrongBinder(parcel);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("com.android.ims.internal.uce.uceservice.IUceService");
                parcel = getPresenceServiceForSubscription(parcel.readString());
                parcel1.writeNoException();
                if(parcel != null)
                    parcel = parcel.asBinder();
                else
                    parcel = null;
                parcel1.writeStrongBinder(parcel);
                return true;

            case 13: // '\r'
                parcel.enforceInterface("com.android.ims.internal.uce.uceservice.IUceService");
                parcel = getOptionsService();
                parcel1.writeNoException();
                if(parcel != null)
                    parcel = parcel.asBinder();
                else
                    parcel = null;
                parcel1.writeStrongBinder(parcel);
                return true;

            case 14: // '\016'
                parcel.enforceInterface("com.android.ims.internal.uce.uceservice.IUceService");
                parcel = getOptionsServiceForSubscription(parcel.readString());
                parcel1.writeNoException();
                break;
            }
            if(parcel != null)
                parcel = parcel.asBinder();
            else
                parcel = null;
            parcel1.writeStrongBinder(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "com.android.ims.internal.uce.uceservice.IUceService";
        static final int TRANSACTION_createOptionsService = 4;
        static final int TRANSACTION_createOptionsServiceForSubscription = 5;
        static final int TRANSACTION_createPresenceService = 7;
        static final int TRANSACTION_createPresenceServiceForSubscription = 8;
        static final int TRANSACTION_destroyOptionsService = 6;
        static final int TRANSACTION_destroyPresenceService = 9;
        static final int TRANSACTION_getOptionsService = 13;
        static final int TRANSACTION_getOptionsServiceForSubscription = 14;
        static final int TRANSACTION_getPresenceService = 11;
        static final int TRANSACTION_getPresenceServiceForSubscription = 12;
        static final int TRANSACTION_getServiceStatus = 10;
        static final int TRANSACTION_isServiceStarted = 3;
        static final int TRANSACTION_startService = 1;
        static final int TRANSACTION_stopService = 2;

        public Stub()
        {
            attachInterface(this, "com.android.ims.internal.uce.uceservice.IUceService");
        }
    }

    private static class Stub.Proxy
        implements IUceService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public int createOptionsService(IOptionsListener ioptionslistener, UceLong ucelong)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.uce.uceservice.IUceService");
            if(ioptionslistener == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = ioptionslistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(ucelong == null)
                break MISSING_BLOCK_LABEL_108;
            parcel.writeInt(1);
            ucelong.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            if(parcel1.readInt() != 0)
                ucelong.readFromParcel(parcel1);
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            ioptionslistener;
            parcel1.recycle();
            parcel.recycle();
            throw ioptionslistener;
        }

        public int createOptionsServiceForSubscription(IOptionsListener ioptionslistener, UceLong ucelong, String s)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.uce.uceservice.IUceService");
            if(ioptionslistener == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = ioptionslistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(ucelong == null)
                break MISSING_BLOCK_LABEL_117;
            parcel.writeInt(1);
            ucelong.writeToParcel(parcel, 0);
_L1:
            int i;
            parcel.writeString(s);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            if(parcel1.readInt() != 0)
                ucelong.readFromParcel(parcel1);
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            ioptionslistener;
            parcel1.recycle();
            parcel.recycle();
            throw ioptionslistener;
        }

        public int createPresenceService(IPresenceListener ipresencelistener, UceLong ucelong)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.uce.uceservice.IUceService");
            if(ipresencelistener == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = ipresencelistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(ucelong == null)
                break MISSING_BLOCK_LABEL_109;
            parcel.writeInt(1);
            ucelong.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            if(parcel1.readInt() != 0)
                ucelong.readFromParcel(parcel1);
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            ipresencelistener;
            parcel1.recycle();
            parcel.recycle();
            throw ipresencelistener;
        }

        public int createPresenceServiceForSubscription(IPresenceListener ipresencelistener, UceLong ucelong, String s)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.uce.uceservice.IUceService");
            if(ipresencelistener == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = ipresencelistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(ucelong == null)
                break MISSING_BLOCK_LABEL_118;
            parcel.writeInt(1);
            ucelong.writeToParcel(parcel, 0);
_L1:
            int i;
            parcel.writeString(s);
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            if(parcel1.readInt() != 0)
                ucelong.readFromParcel(parcel1);
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            ipresencelistener;
            parcel1.recycle();
            parcel.recycle();
            throw ipresencelistener;
        }

        public void destroyOptionsService(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.uce.uceservice.IUceService");
            parcel.writeInt(i);
            mRemote.transact(6, parcel, parcel1, 0);
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

        public void destroyPresenceService(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.uce.uceservice.IUceService");
            parcel.writeInt(i);
            mRemote.transact(9, parcel, parcel1, 0);
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

        public String getInterfaceDescriptor()
        {
            return "com.android.ims.internal.uce.uceservice.IUceService";
        }

        public IOptionsService getOptionsService()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            IOptionsService ioptionsservice;
            parcel.writeInterfaceToken("com.android.ims.internal.uce.uceservice.IUceService");
            mRemote.transact(13, parcel, parcel1, 0);
            parcel1.readException();
            ioptionsservice = com.android.ims.internal.uce.options.IOptionsService.Stub.asInterface(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return ioptionsservice;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public IOptionsService getOptionsServiceForSubscription(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.uce.uceservice.IUceService");
            parcel.writeString(s);
            mRemote.transact(14, parcel, parcel1, 0);
            parcel1.readException();
            s = com.android.ims.internal.uce.options.IOptionsService.Stub.asInterface(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public IPresenceService getPresenceService()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            IPresenceService ipresenceservice;
            parcel.writeInterfaceToken("com.android.ims.internal.uce.uceservice.IUceService");
            mRemote.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            ipresenceservice = com.android.ims.internal.uce.presence.IPresenceService.Stub.asInterface(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return ipresenceservice;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public IPresenceService getPresenceServiceForSubscription(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.uce.uceservice.IUceService");
            parcel.writeString(s);
            mRemote.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            s = com.android.ims.internal.uce.presence.IPresenceService.Stub.asInterface(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean getServiceStatus()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.ims.internal.uce.uceservice.IUceService");
            mRemote.transact(10, parcel, parcel1, 0);
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

        public boolean isServiceStarted()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.ims.internal.uce.uceservice.IUceService");
            mRemote.transact(3, parcel, parcel1, 0);
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

        public boolean startService(IUceListener iucelistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.uce.uceservice.IUceService");
            if(iucelistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iucelistener.asBinder();
            int i;
            parcel.writeStrongBinder(ibinder);
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
            iucelistener;
            parcel1.recycle();
            parcel.recycle();
            throw iucelistener;
        }

        public boolean stopService()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.ims.internal.uce.uceservice.IUceService");
            mRemote.transact(2, parcel, parcel1, 0);
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

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract int createOptionsService(IOptionsListener ioptionslistener, UceLong ucelong)
        throws RemoteException;

    public abstract int createOptionsServiceForSubscription(IOptionsListener ioptionslistener, UceLong ucelong, String s)
        throws RemoteException;

    public abstract int createPresenceService(IPresenceListener ipresencelistener, UceLong ucelong)
        throws RemoteException;

    public abstract int createPresenceServiceForSubscription(IPresenceListener ipresencelistener, UceLong ucelong, String s)
        throws RemoteException;

    public abstract void destroyOptionsService(int i)
        throws RemoteException;

    public abstract void destroyPresenceService(int i)
        throws RemoteException;

    public abstract IOptionsService getOptionsService()
        throws RemoteException;

    public abstract IOptionsService getOptionsServiceForSubscription(String s)
        throws RemoteException;

    public abstract IPresenceService getPresenceService()
        throws RemoteException;

    public abstract IPresenceService getPresenceServiceForSubscription(String s)
        throws RemoteException;

    public abstract boolean getServiceStatus()
        throws RemoteException;

    public abstract boolean isServiceStarted()
        throws RemoteException;

    public abstract boolean startService(IUceListener iucelistener)
        throws RemoteException;

    public abstract boolean stopService()
        throws RemoteException;
}
