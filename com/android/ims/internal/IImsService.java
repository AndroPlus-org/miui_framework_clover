// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims.internal;

import android.app.PendingIntent;
import android.os.*;
import com.android.ims.ImsCallProfile;

// Referenced classes of package com.android.ims.internal:
//            IImsRegistrationListener, IImsCallSessionListener, IImsCallSession, IImsConfig, 
//            IImsEcbm, IImsMultiEndpoint, IImsUt

public interface IImsService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IImsService
    {

        public static IImsService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.ims.internal.IImsService");
            if(iinterface != null && (iinterface instanceof IImsService))
                return (IImsService)iinterface;
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
                parcel1.writeString("com.android.ims.internal.IImsService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.ims.internal.IImsService");
                j = parcel.readInt();
                i = parcel.readInt();
                PendingIntent pendingintent;
                if(parcel.readInt() != 0)
                    pendingintent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                else
                    pendingintent = null;
                i = open(j, i, pendingintent, IImsRegistrationListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.ims.internal.IImsService");
                close(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.ims.internal.IImsService");
                boolean flag = isConnected(parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.ims.internal.IImsService");
                boolean flag1 = isOpened(parcel.readInt());
                parcel1.writeNoException();
                if(flag1)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.android.ims.internal.IImsService");
                setRegistrationListener(parcel.readInt(), IImsRegistrationListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.android.ims.internal.IImsService");
                addRegistrationListener(parcel.readInt(), parcel.readInt(), IImsRegistrationListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.android.ims.internal.IImsService");
                parcel = createCallProfile(parcel.readInt(), parcel.readInt(), parcel.readInt());
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
                parcel.enforceInterface("com.android.ims.internal.IImsService");
                i = parcel.readInt();
                ImsCallProfile imscallprofile;
                if(parcel.readInt() != 0)
                    imscallprofile = (ImsCallProfile)ImsCallProfile.CREATOR.createFromParcel(parcel);
                else
                    imscallprofile = null;
                parcel = createCallSession(i, imscallprofile, IImsCallSessionListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                if(parcel != null)
                    parcel = parcel.asBinder();
                else
                    parcel = null;
                parcel1.writeStrongBinder(parcel);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.android.ims.internal.IImsService");
                parcel = getPendingCallSession(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                if(parcel != null)
                    parcel = parcel.asBinder();
                else
                    parcel = null;
                parcel1.writeStrongBinder(parcel);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("com.android.ims.internal.IImsService");
                parcel = getUtInterface(parcel.readInt());
                parcel1.writeNoException();
                if(parcel != null)
                    parcel = parcel.asBinder();
                else
                    parcel = null;
                parcel1.writeStrongBinder(parcel);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("com.android.ims.internal.IImsService");
                parcel = getConfigInterface(parcel.readInt());
                parcel1.writeNoException();
                if(parcel != null)
                    parcel = parcel.asBinder();
                else
                    parcel = null;
                parcel1.writeStrongBinder(parcel);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("com.android.ims.internal.IImsService");
                turnOnIms(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 13: // '\r'
                parcel.enforceInterface("com.android.ims.internal.IImsService");
                turnOffIms(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 14: // '\016'
                parcel.enforceInterface("com.android.ims.internal.IImsService");
                parcel = getEcbmInterface(parcel.readInt());
                parcel1.writeNoException();
                if(parcel != null)
                    parcel = parcel.asBinder();
                else
                    parcel = null;
                parcel1.writeStrongBinder(parcel);
                return true;

            case 15: // '\017'
                parcel.enforceInterface("com.android.ims.internal.IImsService");
                j = parcel.readInt();
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (Message)Message.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setUiTTYMode(j, i, parcel);
                parcel1.writeNoException();
                return true;

            case 16: // '\020'
                parcel.enforceInterface("com.android.ims.internal.IImsService");
                parcel = getMultiEndpointInterface(parcel.readInt());
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

        private static final String DESCRIPTOR = "com.android.ims.internal.IImsService";
        static final int TRANSACTION_addRegistrationListener = 6;
        static final int TRANSACTION_close = 2;
        static final int TRANSACTION_createCallProfile = 7;
        static final int TRANSACTION_createCallSession = 8;
        static final int TRANSACTION_getConfigInterface = 11;
        static final int TRANSACTION_getEcbmInterface = 14;
        static final int TRANSACTION_getMultiEndpointInterface = 16;
        static final int TRANSACTION_getPendingCallSession = 9;
        static final int TRANSACTION_getUtInterface = 10;
        static final int TRANSACTION_isConnected = 3;
        static final int TRANSACTION_isOpened = 4;
        static final int TRANSACTION_open = 1;
        static final int TRANSACTION_setRegistrationListener = 5;
        static final int TRANSACTION_setUiTTYMode = 15;
        static final int TRANSACTION_turnOffIms = 13;
        static final int TRANSACTION_turnOnIms = 12;

        public Stub()
        {
            attachInterface(this, "com.android.ims.internal.IImsService");
        }
    }

    private static class Stub.Proxy
        implements IImsService
    {

        public void addRegistrationListener(int i, int j, IImsRegistrationListener iimsregistrationlistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            if(iimsregistrationlistener == null)
                break MISSING_BLOCK_LABEL_44;
            ibinder = iimsregistrationlistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iimsregistrationlistener;
            parcel1.recycle();
            parcel.recycle();
            throw iimsregistrationlistener;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void close(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsService");
            parcel.writeInt(i);
            mRemote.transact(2, parcel, parcel1, 0);
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

        public ImsCallProfile createCallProfile(int i, int j, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            ImsCallProfile imscallprofile = (ImsCallProfile)ImsCallProfile.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return imscallprofile;
_L2:
            imscallprofile = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public IImsCallSession createCallSession(int i, ImsCallProfile imscallprofile, IImsCallSessionListener iimscallsessionlistener)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsService");
            parcel.writeInt(i);
            if(imscallprofile == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            imscallprofile.writeToParcel(parcel, 0);
_L4:
            imscallprofile = obj;
            if(iimscallsessionlistener == null)
                break MISSING_BLOCK_LABEL_57;
            imscallprofile = iimscallsessionlistener.asBinder();
            parcel.writeStrongBinder(imscallprofile);
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            imscallprofile = IImsCallSession.Stub.asInterface(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return imscallprofile;
_L2:
            parcel.writeInt(0);
            if(true) goto _L4; else goto _L3
_L3:
            imscallprofile;
            parcel1.recycle();
            parcel.recycle();
            throw imscallprofile;
        }

        public IImsConfig getConfigInterface(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            IImsConfig iimsconfig;
            parcel.writeInterfaceToken("com.android.ims.internal.IImsService");
            parcel.writeInt(i);
            mRemote.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            iimsconfig = IImsConfig.Stub.asInterface(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return iimsconfig;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public IImsEcbm getEcbmInterface(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            IImsEcbm iimsecbm;
            parcel.writeInterfaceToken("com.android.ims.internal.IImsService");
            parcel.writeInt(i);
            mRemote.transact(14, parcel, parcel1, 0);
            parcel1.readException();
            iimsecbm = IImsEcbm.Stub.asInterface(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return iimsecbm;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.ims.internal.IImsService";
        }

        public IImsMultiEndpoint getMultiEndpointInterface(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            IImsMultiEndpoint iimsmultiendpoint;
            parcel.writeInterfaceToken("com.android.ims.internal.IImsService");
            parcel.writeInt(i);
            mRemote.transact(16, parcel, parcel1, 0);
            parcel1.readException();
            iimsmultiendpoint = IImsMultiEndpoint.Stub.asInterface(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return iimsmultiendpoint;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public IImsCallSession getPendingCallSession(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsService");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            s = IImsCallSession.Stub.asInterface(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public IImsUt getUtInterface(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            IImsUt iimsut;
            parcel.writeInterfaceToken("com.android.ims.internal.IImsService");
            parcel.writeInt(i);
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            iimsut = IImsUt.Stub.asInterface(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return iimsut;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isConnected(int i, int j, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
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

        public boolean isOpened(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsService");
            parcel.writeInt(i);
            mRemote.transact(4, parcel, parcel1, 0);
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

        public int open(int i, int j, PendingIntent pendingintent, IImsRegistrationListener iimsregistrationlistener)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            if(pendingintent == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            pendingintent.writeToParcel(parcel, 0);
_L4:
            pendingintent = obj;
            if(iimsregistrationlistener == null)
                break MISSING_BLOCK_LABEL_65;
            pendingintent = iimsregistrationlistener.asBinder();
            parcel.writeStrongBinder(pendingintent);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
_L2:
            parcel.writeInt(0);
            if(true) goto _L4; else goto _L3
_L3:
            pendingintent;
            parcel1.recycle();
            parcel.recycle();
            throw pendingintent;
        }

        public void setRegistrationListener(int i, IImsRegistrationListener iimsregistrationlistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsService");
            parcel.writeInt(i);
            if(iimsregistrationlistener == null)
                break MISSING_BLOCK_LABEL_36;
            ibinder = iimsregistrationlistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iimsregistrationlistener;
            parcel1.recycle();
            parcel.recycle();
            throw iimsregistrationlistener;
        }

        public void setUiTTYMode(int i, int j, Message message)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            if(message == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            message.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(15, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            message;
            parcel1.recycle();
            parcel.recycle();
            throw message;
        }

        public void turnOffIms(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsService");
            parcel.writeInt(i);
            mRemote.transact(13, parcel, parcel1, 0);
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

        public void turnOnIms(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsService");
            parcel.writeInt(i);
            mRemote.transact(12, parcel, parcel1, 0);
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


    public abstract void addRegistrationListener(int i, int j, IImsRegistrationListener iimsregistrationlistener)
        throws RemoteException;

    public abstract void close(int i)
        throws RemoteException;

    public abstract ImsCallProfile createCallProfile(int i, int j, int k)
        throws RemoteException;

    public abstract IImsCallSession createCallSession(int i, ImsCallProfile imscallprofile, IImsCallSessionListener iimscallsessionlistener)
        throws RemoteException;

    public abstract IImsConfig getConfigInterface(int i)
        throws RemoteException;

    public abstract IImsEcbm getEcbmInterface(int i)
        throws RemoteException;

    public abstract IImsMultiEndpoint getMultiEndpointInterface(int i)
        throws RemoteException;

    public abstract IImsCallSession getPendingCallSession(int i, String s)
        throws RemoteException;

    public abstract IImsUt getUtInterface(int i)
        throws RemoteException;

    public abstract boolean isConnected(int i, int j, int k)
        throws RemoteException;

    public abstract boolean isOpened(int i)
        throws RemoteException;

    public abstract int open(int i, int j, PendingIntent pendingintent, IImsRegistrationListener iimsregistrationlistener)
        throws RemoteException;

    public abstract void setRegistrationListener(int i, IImsRegistrationListener iimsregistrationlistener)
        throws RemoteException;

    public abstract void setUiTTYMode(int i, int j, Message message)
        throws RemoteException;

    public abstract void turnOffIms(int i)
        throws RemoteException;

    public abstract void turnOnIms(int i)
        throws RemoteException;
}
