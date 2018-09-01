// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims.internal.uce.options;

import android.os.*;
import com.android.ims.internal.uce.common.*;

// Referenced classes of package com.android.ims.internal.uce.options:
//            IOptionsListener, OptionsCapInfo

public interface IOptionsService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IOptionsService
    {

        public static IOptionsService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.ims.internal.uce.options.IOptionsService");
            if(iinterface != null && (iinterface instanceof IOptionsService))
                return (IOptionsService)iinterface;
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
            int k;
            String s;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("com.android.ims.internal.uce.options.IOptionsService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.ims.internal.uce.options.IOptionsService");
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
                parcel.enforceInterface("com.android.ims.internal.uce.options.IOptionsService");
                i = parcel.readInt();
                Object obj = IOptionsListener.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel = (UceLong)UceLong.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                obj = addListener(i, ((IOptionsListener) (obj)), parcel);
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
                parcel.enforceInterface("com.android.ims.internal.uce.options.IOptionsService");
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
                parcel.enforceInterface("com.android.ims.internal.uce.options.IOptionsService");
                i = parcel.readInt();
                CapInfo capinfo;
                if(parcel.readInt() != 0)
                    capinfo = (CapInfo)CapInfo.CREATOR.createFromParcel(parcel);
                else
                    capinfo = null;
                parcel = setMyInfo(i, capinfo, parcel.readInt());
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
                parcel.enforceInterface("com.android.ims.internal.uce.options.IOptionsService");
                parcel = getMyInfo(parcel.readInt(), parcel.readInt());
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
                parcel.enforceInterface("com.android.ims.internal.uce.options.IOptionsService");
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
                parcel.enforceInterface("com.android.ims.internal.uce.options.IOptionsService");
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
                parcel.enforceInterface("com.android.ims.internal.uce.options.IOptionsService");
                i = parcel.readInt();
                j = parcel.readInt();
                k = parcel.readInt();
                s = parcel.readString();
                break;
            }
            OptionsCapInfo optionscapinfo;
            boolean flag;
            if(parcel.readInt() != 0)
                optionscapinfo = (OptionsCapInfo)OptionsCapInfo.CREATOR.createFromParcel(parcel);
            else
                optionscapinfo = null;
            if(parcel.readInt() != 0)
                flag = true;
            else
                flag = false;
            parcel = responseIncomingOptions(i, j, k, s, optionscapinfo, flag);
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

        private static final String DESCRIPTOR = "com.android.ims.internal.uce.options.IOptionsService";
        static final int TRANSACTION_addListener = 2;
        static final int TRANSACTION_getContactCap = 6;
        static final int TRANSACTION_getContactListCap = 7;
        static final int TRANSACTION_getMyInfo = 5;
        static final int TRANSACTION_getVersion = 1;
        static final int TRANSACTION_removeListener = 3;
        static final int TRANSACTION_responseIncomingOptions = 8;
        static final int TRANSACTION_setMyInfo = 4;

        public Stub()
        {
            attachInterface(this, "com.android.ims.internal.uce.options.IOptionsService");
        }
    }

    private static class Stub.Proxy
        implements IOptionsService
    {

        public StatusCode addListener(int i, IOptionsListener ioptionslistener, UceLong ucelong)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.uce.options.IOptionsService");
            parcel.writeInt(i);
            if(ioptionslistener == null)
                break MISSING_BLOCK_LABEL_38;
            ibinder = ioptionslistener.asBinder();
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
            ioptionslistener = (StatusCode)StatusCode.CREATOR.createFromParcel(parcel1);
_L4:
            if(parcel1.readInt() != 0)
                ucelong.readFromParcel(parcel1);
            parcel1.recycle();
            parcel.recycle();
            return ioptionslistener;
_L2:
            parcel.writeInt(0);
              goto _L3
            ioptionslistener;
            parcel1.recycle();
            parcel.recycle();
            throw ioptionslistener;
            ioptionslistener = null;
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
            parcel.writeInterfaceToken("com.android.ims.internal.uce.options.IOptionsService");
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
            parcel.writeInterfaceToken("com.android.ims.internal.uce.options.IOptionsService");
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
            return "com.android.ims.internal.uce.options.IOptionsService";
        }

        public StatusCode getMyInfo(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.uce.options.IOptionsService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(5, parcel, parcel1, 0);
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

        public StatusCode getVersion(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.uce.options.IOptionsService");
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

        public StatusCode removeListener(int i, UceLong ucelong)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.uce.options.IOptionsService");
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

        public StatusCode responseIncomingOptions(int i, int j, int k, String s, OptionsCapInfo optionscapinfo, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.uce.options.IOptionsService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeString(s);
            if(optionscapinfo == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            optionscapinfo.writeToParcel(parcel, 0);
_L3:
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_165;
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

        public StatusCode setMyInfo(int i, CapInfo capinfo, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.uce.options.IOptionsService");
            parcel.writeInt(i);
            if(capinfo == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            capinfo.writeToParcel(parcel, 0);
_L3:
            parcel.writeInt(j);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_123;
            capinfo = (StatusCode)StatusCode.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return capinfo;
_L2:
            parcel.writeInt(0);
              goto _L3
            capinfo;
            parcel1.recycle();
            parcel.recycle();
            throw capinfo;
            capinfo = null;
              goto _L4
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract StatusCode addListener(int i, IOptionsListener ioptionslistener, UceLong ucelong)
        throws RemoteException;

    public abstract StatusCode getContactCap(int i, String s, int j)
        throws RemoteException;

    public abstract StatusCode getContactListCap(int i, String as[], int j)
        throws RemoteException;

    public abstract StatusCode getMyInfo(int i, int j)
        throws RemoteException;

    public abstract StatusCode getVersion(int i)
        throws RemoteException;

    public abstract StatusCode removeListener(int i, UceLong ucelong)
        throws RemoteException;

    public abstract StatusCode responseIncomingOptions(int i, int j, int k, String s, OptionsCapInfo optionscapinfo, boolean flag)
        throws RemoteException;

    public abstract StatusCode setMyInfo(int i, CapInfo capinfo, int j)
        throws RemoteException;
}
