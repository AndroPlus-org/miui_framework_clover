// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims.internal;

import android.app.PendingIntent;
import android.os.*;
import com.android.ims.ImsCallProfile;

// Referenced classes of package com.android.ims.internal:
//            IImsRegistrationListener, IImsCallSessionListener, IImsCallSession, IImsFeatureStatusCallback, 
//            IImsConfig, IImsEcbm, IImsMultiEndpoint, IImsUt

public interface IImsServiceController
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IImsServiceController
    {

        public static IImsServiceController asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.ims.internal.IImsServiceController");
            if(iinterface != null && (iinterface instanceof IImsServiceController))
                return (IImsServiceController)iinterface;
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
                parcel1.writeString("com.android.ims.internal.IImsServiceController");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.ims.internal.IImsServiceController");
                createImsFeature(parcel.readInt(), parcel.readInt(), IImsFeatureStatusCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.ims.internal.IImsServiceController");
                removeImsFeature(parcel.readInt(), parcel.readInt(), IImsFeatureStatusCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.ims.internal.IImsServiceController");
                i = parcel.readInt();
                j = parcel.readInt();
                PendingIntent pendingintent;
                if(parcel.readInt() != 0)
                    pendingintent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                else
                    pendingintent = null;
                i = startSession(i, j, pendingintent, IImsRegistrationListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.ims.internal.IImsServiceController");
                endSession(parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.android.ims.internal.IImsServiceController");
                boolean flag = isConnected(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.android.ims.internal.IImsServiceController");
                boolean flag1 = isOpened(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                if(flag1)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.android.ims.internal.IImsServiceController");
                i = getFeatureStatus(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.android.ims.internal.IImsServiceController");
                addRegistrationListener(parcel.readInt(), parcel.readInt(), IImsRegistrationListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.android.ims.internal.IImsServiceController");
                removeRegistrationListener(parcel.readInt(), parcel.readInt(), IImsRegistrationListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("com.android.ims.internal.IImsServiceController");
                parcel = createCallProfile(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
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

            case 11: // '\013'
                parcel.enforceInterface("com.android.ims.internal.IImsServiceController");
                j = parcel.readInt();
                int k = parcel.readInt();
                i = parcel.readInt();
                ImsCallProfile imscallprofile;
                if(parcel.readInt() != 0)
                    imscallprofile = (ImsCallProfile)ImsCallProfile.CREATOR.createFromParcel(parcel);
                else
                    imscallprofile = null;
                parcel = createCallSession(j, k, i, imscallprofile, IImsCallSessionListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                if(parcel != null)
                    parcel = parcel.asBinder();
                else
                    parcel = null;
                parcel1.writeStrongBinder(parcel);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("com.android.ims.internal.IImsServiceController");
                parcel = getPendingCallSession(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                if(parcel != null)
                    parcel = parcel.asBinder();
                else
                    parcel = null;
                parcel1.writeStrongBinder(parcel);
                return true;

            case 13: // '\r'
                parcel.enforceInterface("com.android.ims.internal.IImsServiceController");
                parcel = getUtInterface(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                if(parcel != null)
                    parcel = parcel.asBinder();
                else
                    parcel = null;
                parcel1.writeStrongBinder(parcel);
                return true;

            case 14: // '\016'
                parcel.enforceInterface("com.android.ims.internal.IImsServiceController");
                parcel = getConfigInterface(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                if(parcel != null)
                    parcel = parcel.asBinder();
                else
                    parcel = null;
                parcel1.writeStrongBinder(parcel);
                return true;

            case 15: // '\017'
                parcel.enforceInterface("com.android.ims.internal.IImsServiceController");
                turnOnIms(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 16: // '\020'
                parcel.enforceInterface("com.android.ims.internal.IImsServiceController");
                turnOffIms(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 17: // '\021'
                parcel.enforceInterface("com.android.ims.internal.IImsServiceController");
                parcel = getEcbmInterface(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                if(parcel != null)
                    parcel = parcel.asBinder();
                else
                    parcel = null;
                parcel1.writeStrongBinder(parcel);
                return true;

            case 18: // '\022'
                parcel.enforceInterface("com.android.ims.internal.IImsServiceController");
                int l = parcel.readInt();
                j = parcel.readInt();
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (Message)Message.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setUiTTYMode(l, j, i, parcel);
                parcel1.writeNoException();
                return true;

            case 19: // '\023'
                parcel.enforceInterface("com.android.ims.internal.IImsServiceController");
                parcel = getMultiEndpointInterface(parcel.readInt(), parcel.readInt());
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

        private static final String DESCRIPTOR = "com.android.ims.internal.IImsServiceController";
        static final int TRANSACTION_addRegistrationListener = 8;
        static final int TRANSACTION_createCallProfile = 10;
        static final int TRANSACTION_createCallSession = 11;
        static final int TRANSACTION_createImsFeature = 1;
        static final int TRANSACTION_endSession = 4;
        static final int TRANSACTION_getConfigInterface = 14;
        static final int TRANSACTION_getEcbmInterface = 17;
        static final int TRANSACTION_getFeatureStatus = 7;
        static final int TRANSACTION_getMultiEndpointInterface = 19;
        static final int TRANSACTION_getPendingCallSession = 12;
        static final int TRANSACTION_getUtInterface = 13;
        static final int TRANSACTION_isConnected = 5;
        static final int TRANSACTION_isOpened = 6;
        static final int TRANSACTION_removeImsFeature = 2;
        static final int TRANSACTION_removeRegistrationListener = 9;
        static final int TRANSACTION_setUiTTYMode = 18;
        static final int TRANSACTION_startSession = 3;
        static final int TRANSACTION_turnOffIms = 16;
        static final int TRANSACTION_turnOnIms = 15;

        public Stub()
        {
            attachInterface(this, "com.android.ims.internal.IImsServiceController");
        }
    }

    private static class Stub.Proxy
        implements IImsServiceController
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
            parcel.writeInterfaceToken("com.android.ims.internal.IImsServiceController");
            parcel.writeInt(i);
            parcel.writeInt(j);
            if(iimsregistrationlistener == null)
                break MISSING_BLOCK_LABEL_44;
            ibinder = iimsregistrationlistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(8, parcel, parcel1, 0);
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

        public ImsCallProfile createCallProfile(int i, int j, int k, int l, int i1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsServiceController");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeInt(l);
            parcel.writeInt(i1);
            mRemote.transact(10, parcel, parcel1, 0);
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

        public IImsCallSession createCallSession(int i, int j, int k, ImsCallProfile imscallprofile, IImsCallSessionListener iimscallsessionlistener)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsServiceController");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            if(imscallprofile == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            imscallprofile.writeToParcel(parcel, 0);
_L4:
            imscallprofile = obj;
            if(iimscallsessionlistener == null)
                break MISSING_BLOCK_LABEL_75;
            imscallprofile = iimscallsessionlistener.asBinder();
            parcel.writeStrongBinder(imscallprofile);
            mRemote.transact(11, parcel, parcel1, 0);
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

        public void createImsFeature(int i, int j, IImsFeatureStatusCallback iimsfeaturestatuscallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsServiceController");
            parcel.writeInt(i);
            parcel.writeInt(j);
            if(iimsfeaturestatuscallback == null)
                break MISSING_BLOCK_LABEL_44;
            ibinder = iimsfeaturestatuscallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iimsfeaturestatuscallback;
            parcel1.recycle();
            parcel.recycle();
            throw iimsfeaturestatuscallback;
        }

        public void endSession(int i, int j, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsServiceController");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(4, parcel, parcel1, 0);
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

        public IImsConfig getConfigInterface(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            IImsConfig iimsconfig;
            parcel.writeInterfaceToken("com.android.ims.internal.IImsServiceController");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(14, parcel, parcel1, 0);
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

        public IImsEcbm getEcbmInterface(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            IImsEcbm iimsecbm;
            parcel.writeInterfaceToken("com.android.ims.internal.IImsServiceController");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(17, parcel, parcel1, 0);
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

        public int getFeatureStatus(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsServiceController");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.ims.internal.IImsServiceController";
        }

        public IImsMultiEndpoint getMultiEndpointInterface(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            IImsMultiEndpoint iimsmultiendpoint;
            parcel.writeInterfaceToken("com.android.ims.internal.IImsServiceController");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(19, parcel, parcel1, 0);
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

        public IImsCallSession getPendingCallSession(int i, int j, int k, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsServiceController");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeString(s);
            mRemote.transact(12, parcel, parcel1, 0);
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

        public IImsUt getUtInterface(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            IImsUt iimsut;
            parcel.writeInterfaceToken("com.android.ims.internal.IImsServiceController");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(13, parcel, parcel1, 0);
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

        public boolean isConnected(int i, int j, int k, int l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsServiceController");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeInt(l);
            mRemote.transact(5, parcel, parcel1, 0);
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

        public boolean isOpened(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsServiceController");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(6, parcel, parcel1, 0);
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

        public void removeImsFeature(int i, int j, IImsFeatureStatusCallback iimsfeaturestatuscallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsServiceController");
            parcel.writeInt(i);
            parcel.writeInt(j);
            if(iimsfeaturestatuscallback == null)
                break MISSING_BLOCK_LABEL_44;
            ibinder = iimsfeaturestatuscallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iimsfeaturestatuscallback;
            parcel1.recycle();
            parcel.recycle();
            throw iimsfeaturestatuscallback;
        }

        public void removeRegistrationListener(int i, int j, IImsRegistrationListener iimsregistrationlistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsServiceController");
            parcel.writeInt(i);
            parcel.writeInt(j);
            if(iimsregistrationlistener == null)
                break MISSING_BLOCK_LABEL_44;
            ibinder = iimsregistrationlistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iimsregistrationlistener;
            parcel1.recycle();
            parcel.recycle();
            throw iimsregistrationlistener;
        }

        public void setUiTTYMode(int i, int j, int k, Message message)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsServiceController");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            if(message == null)
                break MISSING_BLOCK_LABEL_87;
            parcel.writeInt(1);
            message.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(18, parcel, parcel1, 0);
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

        public int startSession(int i, int j, PendingIntent pendingintent, IImsRegistrationListener iimsregistrationlistener)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsServiceController");
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
            mRemote.transact(3, parcel, parcel1, 0);
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

        public void turnOffIms(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsServiceController");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(16, parcel, parcel1, 0);
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

        public void turnOnIms(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsServiceController");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(15, parcel, parcel1, 0);
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

    public abstract ImsCallProfile createCallProfile(int i, int j, int k, int l, int i1)
        throws RemoteException;

    public abstract IImsCallSession createCallSession(int i, int j, int k, ImsCallProfile imscallprofile, IImsCallSessionListener iimscallsessionlistener)
        throws RemoteException;

    public abstract void createImsFeature(int i, int j, IImsFeatureStatusCallback iimsfeaturestatuscallback)
        throws RemoteException;

    public abstract void endSession(int i, int j, int k)
        throws RemoteException;

    public abstract IImsConfig getConfigInterface(int i, int j)
        throws RemoteException;

    public abstract IImsEcbm getEcbmInterface(int i, int j)
        throws RemoteException;

    public abstract int getFeatureStatus(int i, int j)
        throws RemoteException;

    public abstract IImsMultiEndpoint getMultiEndpointInterface(int i, int j)
        throws RemoteException;

    public abstract IImsCallSession getPendingCallSession(int i, int j, int k, String s)
        throws RemoteException;

    public abstract IImsUt getUtInterface(int i, int j)
        throws RemoteException;

    public abstract boolean isConnected(int i, int j, int k, int l)
        throws RemoteException;

    public abstract boolean isOpened(int i, int j)
        throws RemoteException;

    public abstract void removeImsFeature(int i, int j, IImsFeatureStatusCallback iimsfeaturestatuscallback)
        throws RemoteException;

    public abstract void removeRegistrationListener(int i, int j, IImsRegistrationListener iimsregistrationlistener)
        throws RemoteException;

    public abstract void setUiTTYMode(int i, int j, int k, Message message)
        throws RemoteException;

    public abstract int startSession(int i, int j, PendingIntent pendingintent, IImsRegistrationListener iimsregistrationlistener)
        throws RemoteException;

    public abstract void turnOffIms(int i, int j)
        throws RemoteException;

    public abstract void turnOnIms(int i, int j)
        throws RemoteException;
}
