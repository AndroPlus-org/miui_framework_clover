// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims.internal;

import android.net.Uri;
import android.os.*;
import com.android.ims.ImsReasonInfo;

public interface IImsRegistrationListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IImsRegistrationListener
    {

        public static IImsRegistrationListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.ims.internal.IImsRegistrationListener");
            if(iinterface != null && (iinterface instanceof IImsRegistrationListener))
                return (IImsRegistrationListener)iinterface;
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
                parcel1.writeString("com.android.ims.internal.IImsRegistrationListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.ims.internal.IImsRegistrationListener");
                registrationConnected();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.ims.internal.IImsRegistrationListener");
                registrationProgressing();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.ims.internal.IImsRegistrationListener");
                registrationConnectedWithRadioTech(parcel.readInt());
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.ims.internal.IImsRegistrationListener");
                registrationProgressingWithRadioTech(parcel.readInt());
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.android.ims.internal.IImsRegistrationListener");
                if(parcel.readInt() != 0)
                    parcel = (ImsReasonInfo)ImsReasonInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                registrationDisconnected(parcel);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.android.ims.internal.IImsRegistrationListener");
                registrationResumed();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.android.ims.internal.IImsRegistrationListener");
                registrationSuspended();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.android.ims.internal.IImsRegistrationListener");
                registrationServiceCapabilityChanged(parcel.readInt(), parcel.readInt());
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.android.ims.internal.IImsRegistrationListener");
                registrationFeatureCapabilityChanged(parcel.readInt(), parcel.createIntArray(), parcel.createIntArray());
                return true;

            case 10: // '\n'
                parcel.enforceInterface("com.android.ims.internal.IImsRegistrationListener");
                voiceMessageCountUpdate(parcel.readInt());
                return true;

            case 11: // '\013'
                parcel.enforceInterface("com.android.ims.internal.IImsRegistrationListener");
                registrationAssociatedUriChanged((Uri[])parcel.createTypedArray(Uri.CREATOR));
                return true;

            case 12: // '\f'
                parcel.enforceInterface("com.android.ims.internal.IImsRegistrationListener");
                i = parcel.readInt();
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (ImsReasonInfo)ImsReasonInfo.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            registrationChangeFailed(i, parcel);
            return true;
        }

        private static final String DESCRIPTOR = "com.android.ims.internal.IImsRegistrationListener";
        static final int TRANSACTION_registrationAssociatedUriChanged = 11;
        static final int TRANSACTION_registrationChangeFailed = 12;
        static final int TRANSACTION_registrationConnected = 1;
        static final int TRANSACTION_registrationConnectedWithRadioTech = 3;
        static final int TRANSACTION_registrationDisconnected = 5;
        static final int TRANSACTION_registrationFeatureCapabilityChanged = 9;
        static final int TRANSACTION_registrationProgressing = 2;
        static final int TRANSACTION_registrationProgressingWithRadioTech = 4;
        static final int TRANSACTION_registrationResumed = 6;
        static final int TRANSACTION_registrationServiceCapabilityChanged = 8;
        static final int TRANSACTION_registrationSuspended = 7;
        static final int TRANSACTION_voiceMessageCountUpdate = 10;

        public Stub()
        {
            attachInterface(this, "com.android.ims.internal.IImsRegistrationListener");
        }
    }

    private static class Stub.Proxy
        implements IImsRegistrationListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.ims.internal.IImsRegistrationListener";
        }

        public void registrationAssociatedUriChanged(Uri auri[])
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsRegistrationListener");
            parcel.writeTypedArray(auri, 0);
            mRemote.transact(11, parcel, null, 1);
            parcel.recycle();
            return;
            auri;
            parcel.recycle();
            throw auri;
        }

        public void registrationChangeFailed(int i, ImsReasonInfo imsreasoninfo)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsRegistrationListener");
            parcel.writeInt(i);
            if(imsreasoninfo == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            imsreasoninfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(12, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            imsreasoninfo;
            parcel.recycle();
            throw imsreasoninfo;
        }

        public void registrationConnected()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsRegistrationListener");
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void registrationConnectedWithRadioTech(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsRegistrationListener");
            parcel.writeInt(i);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void registrationDisconnected(ImsReasonInfo imsreasoninfo)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsRegistrationListener");
            if(imsreasoninfo == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            imsreasoninfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            imsreasoninfo;
            parcel.recycle();
            throw imsreasoninfo;
        }

        public void registrationFeatureCapabilityChanged(int i, int ai[], int ai1[])
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsRegistrationListener");
            parcel.writeInt(i);
            parcel.writeIntArray(ai);
            parcel.writeIntArray(ai1);
            mRemote.transact(9, parcel, null, 1);
            parcel.recycle();
            return;
            ai;
            parcel.recycle();
            throw ai;
        }

        public void registrationProgressing()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsRegistrationListener");
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void registrationProgressingWithRadioTech(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsRegistrationListener");
            parcel.writeInt(i);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void registrationResumed()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsRegistrationListener");
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void registrationServiceCapabilityChanged(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsRegistrationListener");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(8, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void registrationSuspended()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsRegistrationListener");
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void voiceMessageCountUpdate(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsRegistrationListener");
            parcel.writeInt(i);
            mRemote.transact(10, parcel, null, 1);
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


    public abstract void registrationAssociatedUriChanged(Uri auri[])
        throws RemoteException;

    public abstract void registrationChangeFailed(int i, ImsReasonInfo imsreasoninfo)
        throws RemoteException;

    public abstract void registrationConnected()
        throws RemoteException;

    public abstract void registrationConnectedWithRadioTech(int i)
        throws RemoteException;

    public abstract void registrationDisconnected(ImsReasonInfo imsreasoninfo)
        throws RemoteException;

    public abstract void registrationFeatureCapabilityChanged(int i, int ai[], int ai1[])
        throws RemoteException;

    public abstract void registrationProgressing()
        throws RemoteException;

    public abstract void registrationProgressingWithRadioTech(int i)
        throws RemoteException;

    public abstract void registrationResumed()
        throws RemoteException;

    public abstract void registrationServiceCapabilityChanged(int i, int j)
        throws RemoteException;

    public abstract void registrationSuspended()
        throws RemoteException;

    public abstract void voiceMessageCountUpdate(int i)
        throws RemoteException;
}
