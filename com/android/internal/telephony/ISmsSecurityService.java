// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telephony;

import android.os.*;

// Referenced classes of package com.android.internal.telephony:
//            ISmsSecurityAgent, SmsAuthorizationRequest

public interface ISmsSecurityService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ISmsSecurityService
    {

        public static ISmsSecurityService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.telephony.ISmsSecurityService");
            if(iinterface != null && (iinterface instanceof ISmsSecurityService))
                return (ISmsSecurityService)iinterface;
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
            boolean flag = false;
            boolean flag1 = false;
            boolean flag2 = false;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("com.android.internal.telephony.ISmsSecurityService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.telephony.ISmsSecurityService");
                boolean flag3 = register(ISmsSecurityAgent.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                i = ((flag2) ? 1 : 0);
                if(flag3)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.telephony.ISmsSecurityService");
                boolean flag4 = unregister(ISmsSecurityAgent.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                i = ((flag) ? 1 : 0);
                if(flag4)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.internal.telephony.ISmsSecurityService");
                break;
            }
            boolean flag5;
            SmsAuthorizationRequest smsauthorizationrequest;
            if(parcel.readInt() != 0)
                smsauthorizationrequest = (SmsAuthorizationRequest)SmsAuthorizationRequest.CREATOR.createFromParcel(parcel);
            else
                smsauthorizationrequest = null;
            if(parcel.readInt() != 0)
                flag5 = true;
            else
                flag5 = false;
            flag5 = sendResponse(smsauthorizationrequest, flag5);
            parcel1.writeNoException();
            i = ((flag1) ? 1 : 0);
            if(flag5)
                i = 1;
            parcel1.writeInt(i);
            return true;
        }

        private static final String DESCRIPTOR = "com.android.internal.telephony.ISmsSecurityService";
        static final int TRANSACTION_register = 1;
        static final int TRANSACTION_sendResponse = 3;
        static final int TRANSACTION_unregister = 2;

        public Stub()
        {
            attachInterface(this, "com.android.internal.telephony.ISmsSecurityService");
        }
    }

    private static class Stub.Proxy
        implements ISmsSecurityService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.telephony.ISmsSecurityService";
        }

        public boolean register(ISmsSecurityAgent ismssecurityagent)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ISmsSecurityService");
            if(ismssecurityagent == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ismssecurityagent.asBinder();
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
            ismssecurityagent;
            parcel1.recycle();
            parcel.recycle();
            throw ismssecurityagent;
        }

        public boolean sendResponse(SmsAuthorizationRequest smsauthorizationrequest, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ISmsSecurityService");
            if(smsauthorizationrequest == null)
                break MISSING_BLOCK_LABEL_91;
            parcel.writeInt(1);
            smsauthorizationrequest.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            parcel.writeInt(0);
              goto _L1
            smsauthorizationrequest;
            parcel1.recycle();
            parcel.recycle();
            throw smsauthorizationrequest;
        }

        public boolean unregister(ISmsSecurityAgent ismssecurityagent)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ISmsSecurityService");
            if(ismssecurityagent == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ismssecurityagent.asBinder();
            int i;
            parcel.writeStrongBinder(ibinder);
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
            ismssecurityagent;
            parcel1.recycle();
            parcel.recycle();
            throw ismssecurityagent;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract boolean register(ISmsSecurityAgent ismssecurityagent)
        throws RemoteException;

    public abstract boolean sendResponse(SmsAuthorizationRequest smsauthorizationrequest, boolean flag)
        throws RemoteException;

    public abstract boolean unregister(ISmsSecurityAgent ismssecurityagent)
        throws RemoteException;
}
