// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims.internal.uce.options;

import android.os.*;
import com.android.ims.internal.uce.common.StatusCode;

// Referenced classes of package com.android.ims.internal.uce.options:
//            OptionsCmdStatus, OptionsCapInfo, OptionsSipResponse

public interface IOptionsListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IOptionsListener
    {

        public static IOptionsListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.ims.internal.uce.options.IOptionsListener");
            if(iinterface != null && (iinterface instanceof IOptionsListener))
                return (IOptionsListener)iinterface;
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
            String s1;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("com.android.ims.internal.uce.options.IOptionsListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.ims.internal.uce.options.IOptionsListener");
                getVersionCb(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.ims.internal.uce.options.IOptionsListener");
                if(parcel.readInt() != 0)
                    parcel = (StatusCode)StatusCode.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                serviceAvailable(parcel);
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.ims.internal.uce.options.IOptionsListener");
                if(parcel.readInt() != 0)
                    parcel = (StatusCode)StatusCode.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                serviceUnavailable(parcel);
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.ims.internal.uce.options.IOptionsListener");
                String s = parcel.readString();
                OptionsSipResponse optionssipresponse;
                if(parcel.readInt() != 0)
                    optionssipresponse = (OptionsSipResponse)OptionsSipResponse.CREATOR.createFromParcel(parcel);
                else
                    optionssipresponse = null;
                if(parcel.readInt() != 0)
                    parcel = (OptionsCapInfo)OptionsCapInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                sipResponseReceived(s, optionssipresponse, parcel);
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.android.ims.internal.uce.options.IOptionsListener");
                if(parcel.readInt() != 0)
                    parcel = (OptionsCmdStatus)OptionsCmdStatus.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                cmdStatus(parcel);
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.android.ims.internal.uce.options.IOptionsListener");
                s1 = parcel.readString();
                break;
            }
            OptionsCapInfo optionscapinfo;
            if(parcel.readInt() != 0)
                optionscapinfo = (OptionsCapInfo)OptionsCapInfo.CREATOR.createFromParcel(parcel);
            else
                optionscapinfo = null;
            incomingOptions(s1, optionscapinfo, parcel.readInt());
            parcel1.writeNoException();
            return true;
        }

        private static final String DESCRIPTOR = "com.android.ims.internal.uce.options.IOptionsListener";
        static final int TRANSACTION_cmdStatus = 5;
        static final int TRANSACTION_getVersionCb = 1;
        static final int TRANSACTION_incomingOptions = 6;
        static final int TRANSACTION_serviceAvailable = 2;
        static final int TRANSACTION_serviceUnavailable = 3;
        static final int TRANSACTION_sipResponseReceived = 4;

        public Stub()
        {
            attachInterface(this, "com.android.ims.internal.uce.options.IOptionsListener");
        }
    }

    private static class Stub.Proxy
        implements IOptionsListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void cmdStatus(OptionsCmdStatus optionscmdstatus)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.uce.options.IOptionsListener");
            if(optionscmdstatus == null)
                break MISSING_BLOCK_LABEL_56;
            parcel.writeInt(1);
            optionscmdstatus.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            optionscmdstatus;
            parcel1.recycle();
            parcel.recycle();
            throw optionscmdstatus;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.ims.internal.uce.options.IOptionsListener";
        }

        public void getVersionCb(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.uce.options.IOptionsListener");
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

        public void incomingOptions(String s, OptionsCapInfo optionscapinfo, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.uce.options.IOptionsListener");
            parcel.writeString(s);
            if(optionscapinfo == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            optionscapinfo.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void serviceAvailable(StatusCode statuscode)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.uce.options.IOptionsListener");
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

        public void serviceUnavailable(StatusCode statuscode)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.uce.options.IOptionsListener");
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

        public void sipResponseReceived(String s, OptionsSipResponse optionssipresponse, OptionsCapInfo optionscapinfo)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.uce.options.IOptionsListener");
            parcel.writeString(s);
            if(optionssipresponse == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            optionssipresponse.writeToParcel(parcel, 0);
_L3:
            if(optionscapinfo == null)
                break MISSING_BLOCK_LABEL_111;
            parcel.writeInt(1);
            optionscapinfo.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
            parcel.writeInt(0);
              goto _L4
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void cmdStatus(OptionsCmdStatus optionscmdstatus)
        throws RemoteException;

    public abstract void getVersionCb(String s)
        throws RemoteException;

    public abstract void incomingOptions(String s, OptionsCapInfo optionscapinfo, int i)
        throws RemoteException;

    public abstract void serviceAvailable(StatusCode statuscode)
        throws RemoteException;

    public abstract void serviceUnavailable(StatusCode statuscode)
        throws RemoteException;

    public abstract void sipResponseReceived(String s, OptionsSipResponse optionssipresponse, OptionsCapInfo optionscapinfo)
        throws RemoteException;
}
