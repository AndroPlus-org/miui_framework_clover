// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims.internal.uce.presence;

import android.os.*;
import com.android.ims.internal.uce.common.StatusCode;
import com.android.ims.internal.uce.common.UceLong;

// Referenced classes of package com.android.ims.internal.uce.presence:
//            IPresenceListener, PresCapInfo, PresServiceInfo

public interface IPresenceService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IPresenceService
    {

        public static IPresenceService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.ims.internal.uce.presence.IPresenceService");
            if(iinterface != null && (iinterface instanceof IPresenceService))
                return (IPresenceService)iinterface;
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
            String s;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("com.android.ims.internal.uce.presence.IPresenceService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.ims.internal.uce.presence.IPresenceService");
                parcel = getVersion(parcel.readInt());
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

            case 2: // '\002'
                parcel.enforceInterface("com.android.ims.internal.uce.presence.IPresenceService");
                i = parcel.readInt();
                Object obj = IPresenceListener.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel = (UceLong)UceLong.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                obj = addListener(i, ((IPresenceListener) (obj)), parcel);
                parcel1.writeNoException();
                if(obj != null)
                {
                    parcel1.writeInt(1);
                    ((StatusCode) (obj)).writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
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
                parcel.enforceInterface("com.android.ims.internal.uce.presence.IPresenceService");
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (UceLong)UceLong.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = removeListener(i, parcel);
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

            case 4: // '\004'
                parcel.enforceInterface("com.android.ims.internal.uce.presence.IPresenceService");
                parcel = reenableService(parcel.readInt(), parcel.readInt());
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

            case 5: // '\005'
                parcel.enforceInterface("com.android.ims.internal.uce.presence.IPresenceService");
                i = parcel.readInt();
                PresCapInfo prescapinfo;
                if(parcel.readInt() != 0)
                    prescapinfo = (PresCapInfo)PresCapInfo.CREATOR.createFromParcel(parcel);
                else
                    prescapinfo = null;
                parcel = publishMyCap(i, prescapinfo, parcel.readInt());
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

            case 6: // '\006'
                parcel.enforceInterface("com.android.ims.internal.uce.presence.IPresenceService");
                parcel = getContactCap(parcel.readInt(), parcel.readString(), parcel.readInt());
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

            case 7: // '\007'
                parcel.enforceInterface("com.android.ims.internal.uce.presence.IPresenceService");
                parcel = getContactListCap(parcel.readInt(), parcel.createStringArray(), parcel.readInt());
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

            case 8: // '\b'
                parcel.enforceInterface("com.android.ims.internal.uce.presence.IPresenceService");
                i = parcel.readInt();
                s = parcel.readString();
                break;
            }
            PresServiceInfo presserviceinfo;
            if(parcel.readInt() != 0)
                presserviceinfo = (PresServiceInfo)PresServiceInfo.CREATOR.createFromParcel(parcel);
            else
                presserviceinfo = null;
            parcel = setNewFeatureTag(i, s, presserviceinfo, parcel.readInt());
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
        }

        private static final String DESCRIPTOR = "com.android.ims.internal.uce.presence.IPresenceService";
        static final int TRANSACTION_addListener = 2;
        static final int TRANSACTION_getContactCap = 6;
        static final int TRANSACTION_getContactListCap = 7;
        static final int TRANSACTION_getVersion = 1;
        static final int TRANSACTION_publishMyCap = 5;
        static final int TRANSACTION_reenableService = 4;
        static final int TRANSACTION_removeListener = 3;
        static final int TRANSACTION_setNewFeatureTag = 8;

        public Stub()
        {
            attachInterface(this, "com.android.ims.internal.uce.presence.IPresenceService");
        }
    }

    private static class Stub.Proxy
        implements IPresenceService
    {

        public StatusCode addListener(int i, IPresenceListener ipresencelistener, UceLong ucelong)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.uce.presence.IPresenceService");
            parcel.writeInt(i);
            if(ipresencelistener == null)
                break MISSING_BLOCK_LABEL_38;
            ibinder = ipresencelistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(ucelong == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            ucelong.writeToParcel(parcel, 0);
_L3:
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_153;
            ipresencelistener = (StatusCode)StatusCode.CREATOR.createFromParcel(parcel1);
_L4:
            if(parcel1.readInt() != 0)
                ucelong.readFromParcel(parcel1);
            parcel1.recycle();
            parcel.recycle();
            return ipresencelistener;
_L2:
            parcel.writeInt(0);
              goto _L3
            ipresencelistener;
            parcel1.recycle();
            parcel.recycle();
            throw ipresencelistener;
            ipresencelistener = null;
              goto _L4
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public StatusCode getContactCap(int i, String s, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.uce.presence.IPresenceService");
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeInt(j);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (StatusCode)StatusCode.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            s = null;
            if(true) goto _L4; else goto _L3
_L3:
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public StatusCode getContactListCap(int i, String as[], int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.uce.presence.IPresenceService");
            parcel.writeInt(i);
            parcel.writeStringArray(as);
            parcel.writeInt(j);
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            as = (StatusCode)StatusCode.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return as;
_L2:
            as = null;
            if(true) goto _L4; else goto _L3
_L3:
            as;
            parcel1.recycle();
            parcel.recycle();
            throw as;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.ims.internal.uce.presence.IPresenceService";
        }

        public StatusCode getVersion(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.uce.presence.IPresenceService");
            parcel.writeInt(i);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            StatusCode statuscode = (StatusCode)StatusCode.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return statuscode;
_L2:
            statuscode = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public StatusCode publishMyCap(int i, PresCapInfo prescapinfo, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.uce.presence.IPresenceService");
            parcel.writeInt(i);
            if(prescapinfo == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            prescapinfo.writeToParcel(parcel, 0);
_L3:
            parcel.writeInt(j);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_123;
            prescapinfo = (StatusCode)StatusCode.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return prescapinfo;
_L2:
            parcel.writeInt(0);
              goto _L3
            prescapinfo;
            parcel1.recycle();
            parcel.recycle();
            throw prescapinfo;
            prescapinfo = null;
              goto _L4
        }

        public StatusCode reenableService(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.uce.presence.IPresenceService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            StatusCode statuscode = (StatusCode)StatusCode.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return statuscode;
_L2:
            statuscode = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public StatusCode removeListener(int i, UceLong ucelong)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.uce.presence.IPresenceService");
            parcel.writeInt(i);
            if(ucelong == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            ucelong.writeToParcel(parcel, 0);
_L3:
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_108;
            ucelong = (StatusCode)StatusCode.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return ucelong;
_L2:
            parcel.writeInt(0);
              goto _L3
            ucelong;
            parcel1.recycle();
            parcel.recycle();
            throw ucelong;
            ucelong = null;
              goto _L4
        }

        public StatusCode setNewFeatureTag(int i, String s, PresServiceInfo presserviceinfo, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.uce.presence.IPresenceService");
            parcel.writeInt(i);
            parcel.writeString(s);
            if(presserviceinfo == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            presserviceinfo.writeToParcel(parcel, 0);
_L3:
            parcel.writeInt(j);
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_131;
            s = (StatusCode)StatusCode.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            parcel.writeInt(0);
              goto _L3
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
            s = null;
              goto _L4
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract StatusCode addListener(int i, IPresenceListener ipresencelistener, UceLong ucelong)
        throws RemoteException;

    public abstract StatusCode getContactCap(int i, String s, int j)
        throws RemoteException;

    public abstract StatusCode getContactListCap(int i, String as[], int j)
        throws RemoteException;

    public abstract StatusCode getVersion(int i)
        throws RemoteException;

    public abstract StatusCode publishMyCap(int i, PresCapInfo prescapinfo, int j)
        throws RemoteException;

    public abstract StatusCode reenableService(int i, int j)
        throws RemoteException;

    public abstract StatusCode removeListener(int i, UceLong ucelong)
        throws RemoteException;

    public abstract StatusCode setNewFeatureTag(int i, String s, PresServiceInfo presserviceinfo, int j)
        throws RemoteException;
}
