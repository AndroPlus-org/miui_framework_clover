// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims.internal.uce.presence;

import android.os.*;
import com.android.ims.internal.uce.common.StatusCode;

// Referenced classes of package com.android.ims.internal.uce.presence:
//            PresTupleInfo, PresCmdStatus, PresRlmiInfo, PresResInfo, 
//            PresPublishTriggerType, PresSipResponse

public interface IPresenceListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IPresenceListener
    {

        public static IPresenceListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.ims.internal.uce.presence.IPresenceListener");
            if(iinterface != null && (iinterface instanceof IPresenceListener))
                return (IPresenceListener)iinterface;
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
                parcel1.writeString("com.android.ims.internal.uce.presence.IPresenceListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.ims.internal.uce.presence.IPresenceListener");
                getVersionCb(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.ims.internal.uce.presence.IPresenceListener");
                if(parcel.readInt() != 0)
                    parcel = (StatusCode)StatusCode.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                serviceAvailable(parcel);
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.ims.internal.uce.presence.IPresenceListener");
                if(parcel.readInt() != 0)
                    parcel = (StatusCode)StatusCode.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                serviceUnAvailable(parcel);
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.ims.internal.uce.presence.IPresenceListener");
                if(parcel.readInt() != 0)
                    parcel = (PresPublishTriggerType)PresPublishTriggerType.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                publishTriggering(parcel);
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.android.ims.internal.uce.presence.IPresenceListener");
                if(parcel.readInt() != 0)
                    parcel = (PresCmdStatus)PresCmdStatus.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                cmdStatus(parcel);
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.android.ims.internal.uce.presence.IPresenceListener");
                if(parcel.readInt() != 0)
                    parcel = (PresSipResponse)PresSipResponse.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                sipResponseReceived(parcel);
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.android.ims.internal.uce.presence.IPresenceListener");
                capInfoReceived(parcel.readString(), (PresTupleInfo[])parcel.createTypedArray(PresTupleInfo.CREATOR));
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.android.ims.internal.uce.presence.IPresenceListener");
                PresRlmiInfo presrlmiinfo;
                if(parcel.readInt() != 0)
                    presrlmiinfo = (PresRlmiInfo)PresRlmiInfo.CREATOR.createFromParcel(parcel);
                else
                    presrlmiinfo = null;
                listCapInfoReceived(presrlmiinfo, (PresResInfo[])parcel.createTypedArray(PresResInfo.CREATOR));
                parcel1.writeNoException();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.android.ims.internal.uce.presence.IPresenceListener");
                unpublishMessageSent();
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.ims.internal.uce.presence.IPresenceListener";
        static final int TRANSACTION_capInfoReceived = 7;
        static final int TRANSACTION_cmdStatus = 5;
        static final int TRANSACTION_getVersionCb = 1;
        static final int TRANSACTION_listCapInfoReceived = 8;
        static final int TRANSACTION_publishTriggering = 4;
        static final int TRANSACTION_serviceAvailable = 2;
        static final int TRANSACTION_serviceUnAvailable = 3;
        static final int TRANSACTION_sipResponseReceived = 6;
        static final int TRANSACTION_unpublishMessageSent = 9;

        public Stub()
        {
            attachInterface(this, "com.android.ims.internal.uce.presence.IPresenceListener");
        }
    }

    private static class Stub.Proxy
        implements IPresenceListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void capInfoReceived(String s, PresTupleInfo aprestupleinfo[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.uce.presence.IPresenceListener");
            parcel.writeString(s);
            parcel.writeTypedArray(aprestupleinfo, 0);
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void cmdStatus(PresCmdStatus prescmdstatus)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.uce.presence.IPresenceListener");
            if(prescmdstatus == null)
                break MISSING_BLOCK_LABEL_56;
            parcel.writeInt(1);
            prescmdstatus.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            prescmdstatus;
            parcel1.recycle();
            parcel.recycle();
            throw prescmdstatus;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.ims.internal.uce.presence.IPresenceListener";
        }

        public void getVersionCb(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.uce.presence.IPresenceListener");
            parcel.writeString(s);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void listCapInfoReceived(PresRlmiInfo presrlmiinfo, PresResInfo apresresinfo[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.uce.presence.IPresenceListener");
            if(presrlmiinfo == null)
                break MISSING_BLOCK_LABEL_67;
            parcel.writeInt(1);
            presrlmiinfo.writeToParcel(parcel, 0);
_L1:
            parcel.writeTypedArray(apresresinfo, 0);
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            presrlmiinfo;
            parcel1.recycle();
            parcel.recycle();
            throw presrlmiinfo;
        }

        public void publishTriggering(PresPublishTriggerType prespublishtriggertype)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.uce.presence.IPresenceListener");
            if(prespublishtriggertype == null)
                break MISSING_BLOCK_LABEL_56;
            parcel.writeInt(1);
            prespublishtriggertype.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            prespublishtriggertype;
            parcel1.recycle();
            parcel.recycle();
            throw prespublishtriggertype;
        }

        public void serviceAvailable(StatusCode statuscode)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.uce.presence.IPresenceListener");
            if(statuscode == null)
                break MISSING_BLOCK_LABEL_56;
            parcel.writeInt(1);
            statuscode.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            statuscode;
            parcel1.recycle();
            parcel.recycle();
            throw statuscode;
        }

        public void serviceUnAvailable(StatusCode statuscode)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.uce.presence.IPresenceListener");
            if(statuscode == null)
                break MISSING_BLOCK_LABEL_56;
            parcel.writeInt(1);
            statuscode.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            statuscode;
            parcel1.recycle();
            parcel.recycle();
            throw statuscode;
        }

        public void sipResponseReceived(PresSipResponse pressipresponse)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.uce.presence.IPresenceListener");
            if(pressipresponse == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            pressipresponse.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            pressipresponse;
            parcel1.recycle();
            parcel.recycle();
            throw pressipresponse;
        }

        public void unpublishMessageSent()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.uce.presence.IPresenceListener");
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

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void capInfoReceived(String s, PresTupleInfo aprestupleinfo[])
        throws RemoteException;

    public abstract void cmdStatus(PresCmdStatus prescmdstatus)
        throws RemoteException;

    public abstract void getVersionCb(String s)
        throws RemoteException;

    public abstract void listCapInfoReceived(PresRlmiInfo presrlmiinfo, PresResInfo apresresinfo[])
        throws RemoteException;

    public abstract void publishTriggering(PresPublishTriggerType prespublishtriggertype)
        throws RemoteException;

    public abstract void serviceAvailable(StatusCode statuscode)
        throws RemoteException;

    public abstract void serviceUnAvailable(StatusCode statuscode)
        throws RemoteException;

    public abstract void sipResponseReceived(PresSipResponse pressipresponse)
        throws RemoteException;

    public abstract void unpublishMessageSent()
        throws RemoteException;
}
