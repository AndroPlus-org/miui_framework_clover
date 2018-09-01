// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims.internal;

import android.os.*;
import com.android.ims.ImsCallProfile;
import com.android.ims.ImsStreamMediaProfile;

// Referenced classes of package com.android.ims.internal:
//            IImsVideoCallProvider, IImsCallSessionListener

public interface IImsCallSession
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IImsCallSession
    {

        public static IImsCallSession asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.ims.internal.IImsCallSession");
            if(iinterface != null && (iinterface instanceof IImsCallSession))
                return (IImsCallSession)iinterface;
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
                parcel1.writeString("com.android.ims.internal.IImsCallSession");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSession");
                close();
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSession");
                parcel = getCallId();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSession");
                parcel = getCallProfile();
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
                parcel.enforceInterface("com.android.ims.internal.IImsCallSession");
                parcel = getLocalCallProfile();
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
                parcel.enforceInterface("com.android.ims.internal.IImsCallSession");
                parcel = getRemoteCallProfile();
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
                parcel.enforceInterface("com.android.ims.internal.IImsCallSession");
                parcel = getProperty(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSession");
                i = getState();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSession");
                boolean flag = isInCall();
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSession");
                setListener(IImsCallSessionListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSession");
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                setMute(flag1);
                parcel1.writeNoException();
                return true;

            case 11: // '\013'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSession");
                String s = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (ImsCallProfile)ImsCallProfile.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                start(s, parcel);
                parcel1.writeNoException();
                return true;

            case 12: // '\f'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSession");
                String as[] = parcel.createStringArray();
                if(parcel.readInt() != 0)
                    parcel = (ImsCallProfile)ImsCallProfile.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                startConference(as, parcel);
                parcel1.writeNoException();
                return true;

            case 13: // '\r'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSession");
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (ImsStreamMediaProfile)ImsStreamMediaProfile.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                accept(i, parcel);
                parcel1.writeNoException();
                return true;

            case 14: // '\016'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSession");
                reject(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 15: // '\017'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSession");
                terminate(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 16: // '\020'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSession");
                if(parcel.readInt() != 0)
                    parcel = (ImsStreamMediaProfile)ImsStreamMediaProfile.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                hold(parcel);
                parcel1.writeNoException();
                return true;

            case 17: // '\021'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSession");
                if(parcel.readInt() != 0)
                    parcel = (ImsStreamMediaProfile)ImsStreamMediaProfile.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                resume(parcel);
                parcel1.writeNoException();
                return true;

            case 18: // '\022'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSession");
                merge();
                parcel1.writeNoException();
                return true;

            case 19: // '\023'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSession");
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (ImsStreamMediaProfile)ImsStreamMediaProfile.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                update(i, parcel);
                parcel1.writeNoException();
                return true;

            case 20: // '\024'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSession");
                extendToConference(parcel.createStringArray());
                parcel1.writeNoException();
                return true;

            case 21: // '\025'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSession");
                inviteParticipants(parcel.createStringArray());
                parcel1.writeNoException();
                return true;

            case 22: // '\026'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSession");
                removeParticipants(parcel.createStringArray());
                parcel1.writeNoException();
                return true;

            case 23: // '\027'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSession");
                char c = (char)parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (Message)Message.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                sendDtmf(c, parcel);
                parcel1.writeNoException();
                return true;

            case 24: // '\030'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSession");
                startDtmf((char)parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 25: // '\031'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSession");
                stopDtmf();
                parcel1.writeNoException();
                return true;

            case 26: // '\032'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSession");
                sendUssd(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 27: // '\033'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSession");
                parcel = getVideoCallProvider();
                parcel1.writeNoException();
                if(parcel != null)
                    parcel = parcel.asBinder();
                else
                    parcel = null;
                parcel1.writeStrongBinder(parcel);
                return true;

            case 28: // '\034'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSession");
                boolean flag2 = isMultiparty();
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 29: // '\035'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSession");
                if(parcel.readInt() != 0)
                    parcel = (ImsCallProfile)ImsCallProfile.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                sendRttModifyRequest(parcel);
                parcel1.writeNoException();
                return true;

            case 30: // '\036'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSession");
                boolean flag3;
                if(parcel.readInt() != 0)
                    flag3 = true;
                else
                    flag3 = false;
                sendRttModifyResponse(flag3);
                parcel1.writeNoException();
                return true;

            case 31: // '\037'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSession");
                sendRttMessage(parcel.readString());
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.ims.internal.IImsCallSession";
        static final int TRANSACTION_accept = 13;
        static final int TRANSACTION_close = 1;
        static final int TRANSACTION_extendToConference = 20;
        static final int TRANSACTION_getCallId = 2;
        static final int TRANSACTION_getCallProfile = 3;
        static final int TRANSACTION_getLocalCallProfile = 4;
        static final int TRANSACTION_getProperty = 6;
        static final int TRANSACTION_getRemoteCallProfile = 5;
        static final int TRANSACTION_getState = 7;
        static final int TRANSACTION_getVideoCallProvider = 27;
        static final int TRANSACTION_hold = 16;
        static final int TRANSACTION_inviteParticipants = 21;
        static final int TRANSACTION_isInCall = 8;
        static final int TRANSACTION_isMultiparty = 28;
        static final int TRANSACTION_merge = 18;
        static final int TRANSACTION_reject = 14;
        static final int TRANSACTION_removeParticipants = 22;
        static final int TRANSACTION_resume = 17;
        static final int TRANSACTION_sendDtmf = 23;
        static final int TRANSACTION_sendRttMessage = 31;
        static final int TRANSACTION_sendRttModifyRequest = 29;
        static final int TRANSACTION_sendRttModifyResponse = 30;
        static final int TRANSACTION_sendUssd = 26;
        static final int TRANSACTION_setListener = 9;
        static final int TRANSACTION_setMute = 10;
        static final int TRANSACTION_start = 11;
        static final int TRANSACTION_startConference = 12;
        static final int TRANSACTION_startDtmf = 24;
        static final int TRANSACTION_stopDtmf = 25;
        static final int TRANSACTION_terminate = 15;
        static final int TRANSACTION_update = 19;

        public Stub()
        {
            attachInterface(this, "com.android.ims.internal.IImsCallSession");
        }
    }

    private static class Stub.Proxy
        implements IImsCallSession
    {

        public void accept(int i, ImsStreamMediaProfile imsstreammediaprofile)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSession");
            parcel.writeInt(i);
            if(imsstreammediaprofile == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            imsstreammediaprofile.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(13, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            imsstreammediaprofile;
            parcel1.recycle();
            parcel.recycle();
            throw imsstreammediaprofile;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void close()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSession");
            mRemote.transact(1, parcel, parcel1, 0);
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

        public void extendToConference(String as[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSession");
            parcel.writeStringArray(as);
            mRemote.transact(20, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            as;
            parcel1.recycle();
            parcel.recycle();
            throw as;
        }

        public String getCallId()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSession");
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public ImsCallProfile getCallProfile()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSession");
            mRemote.transact(3, parcel, parcel1, 0);
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

        public String getInterfaceDescriptor()
        {
            return "com.android.ims.internal.IImsCallSession";
        }

        public ImsCallProfile getLocalCallProfile()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSession");
            mRemote.transact(4, parcel, parcel1, 0);
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

        public String getProperty(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSession");
            parcel.writeString(s);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public ImsCallProfile getRemoteCallProfile()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSession");
            mRemote.transact(5, parcel, parcel1, 0);
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

        public int getState()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSession");
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

        public IImsVideoCallProvider getVideoCallProvider()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            IImsVideoCallProvider iimsvideocallprovider;
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSession");
            mRemote.transact(27, parcel, parcel1, 0);
            parcel1.readException();
            iimsvideocallprovider = IImsVideoCallProvider.Stub.asInterface(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return iimsvideocallprovider;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void hold(ImsStreamMediaProfile imsstreammediaprofile)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSession");
            if(imsstreammediaprofile == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            imsstreammediaprofile.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(16, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            imsstreammediaprofile;
            parcel1.recycle();
            parcel.recycle();
            throw imsstreammediaprofile;
        }

        public void inviteParticipants(String as[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSession");
            parcel.writeStringArray(as);
            mRemote.transact(21, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            as;
            parcel1.recycle();
            parcel.recycle();
            throw as;
        }

        public boolean isInCall()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSession");
            mRemote.transact(8, parcel, parcel1, 0);
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

        public boolean isMultiparty()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSession");
            mRemote.transact(28, parcel, parcel1, 0);
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

        public void merge()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSession");
            mRemote.transact(18, parcel, parcel1, 0);
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

        public void reject(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSession");
            parcel.writeInt(i);
            mRemote.transact(14, parcel, parcel1, 0);
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

        public void removeParticipants(String as[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSession");
            parcel.writeStringArray(as);
            mRemote.transact(22, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            as;
            parcel1.recycle();
            parcel.recycle();
            throw as;
        }

        public void resume(ImsStreamMediaProfile imsstreammediaprofile)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSession");
            if(imsstreammediaprofile == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            imsstreammediaprofile.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(17, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            imsstreammediaprofile;
            parcel1.recycle();
            parcel.recycle();
            throw imsstreammediaprofile;
        }

        public void sendDtmf(char c, Message message)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSession");
            parcel.writeInt(c);
            if(message == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            message.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(23, parcel, parcel1, 0);
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

        public void sendRttMessage(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSession");
            parcel.writeString(s);
            mRemote.transact(31, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void sendRttModifyRequest(ImsCallProfile imscallprofile)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSession");
            if(imscallprofile == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            imscallprofile.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(29, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            imscallprofile;
            parcel1.recycle();
            parcel.recycle();
            throw imscallprofile;
        }

        public void sendRttModifyResponse(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSession");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(30, parcel, parcel1, 0);
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

        public void sendUssd(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSession");
            parcel.writeString(s);
            mRemote.transact(26, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setListener(IImsCallSessionListener iimscallsessionlistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSession");
            if(iimscallsessionlistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iimscallsessionlistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iimscallsessionlistener;
            parcel1.recycle();
            parcel.recycle();
            throw iimscallsessionlistener;
        }

        public void setMute(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSession");
            if(flag)
                i = 1;
            parcel.writeInt(i);
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

        public void start(String s, ImsCallProfile imscallprofile)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSession");
            parcel.writeString(s);
            if(imscallprofile == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            imscallprofile.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(11, parcel, parcel1, 0);
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

        public void startConference(String as[], ImsCallProfile imscallprofile)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSession");
            parcel.writeStringArray(as);
            if(imscallprofile == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            imscallprofile.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            as;
            parcel1.recycle();
            parcel.recycle();
            throw as;
        }

        public void startDtmf(char c)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSession");
            parcel.writeInt(c);
            mRemote.transact(24, parcel, parcel1, 0);
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

        public void stopDtmf()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSession");
            mRemote.transact(25, parcel, parcel1, 0);
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

        public void terminate(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSession");
            parcel.writeInt(i);
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

        public void update(int i, ImsStreamMediaProfile imsstreammediaprofile)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSession");
            parcel.writeInt(i);
            if(imsstreammediaprofile == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            imsstreammediaprofile.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(19, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            imsstreammediaprofile;
            parcel1.recycle();
            parcel.recycle();
            throw imsstreammediaprofile;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void accept(int i, ImsStreamMediaProfile imsstreammediaprofile)
        throws RemoteException;

    public abstract void close()
        throws RemoteException;

    public abstract void extendToConference(String as[])
        throws RemoteException;

    public abstract String getCallId()
        throws RemoteException;

    public abstract ImsCallProfile getCallProfile()
        throws RemoteException;

    public abstract ImsCallProfile getLocalCallProfile()
        throws RemoteException;

    public abstract String getProperty(String s)
        throws RemoteException;

    public abstract ImsCallProfile getRemoteCallProfile()
        throws RemoteException;

    public abstract int getState()
        throws RemoteException;

    public abstract IImsVideoCallProvider getVideoCallProvider()
        throws RemoteException;

    public abstract void hold(ImsStreamMediaProfile imsstreammediaprofile)
        throws RemoteException;

    public abstract void inviteParticipants(String as[])
        throws RemoteException;

    public abstract boolean isInCall()
        throws RemoteException;

    public abstract boolean isMultiparty()
        throws RemoteException;

    public abstract void merge()
        throws RemoteException;

    public abstract void reject(int i)
        throws RemoteException;

    public abstract void removeParticipants(String as[])
        throws RemoteException;

    public abstract void resume(ImsStreamMediaProfile imsstreammediaprofile)
        throws RemoteException;

    public abstract void sendDtmf(char c, Message message)
        throws RemoteException;

    public abstract void sendRttMessage(String s)
        throws RemoteException;

    public abstract void sendRttModifyRequest(ImsCallProfile imscallprofile)
        throws RemoteException;

    public abstract void sendRttModifyResponse(boolean flag)
        throws RemoteException;

    public abstract void sendUssd(String s)
        throws RemoteException;

    public abstract void setListener(IImsCallSessionListener iimscallsessionlistener)
        throws RemoteException;

    public abstract void setMute(boolean flag)
        throws RemoteException;

    public abstract void start(String s, ImsCallProfile imscallprofile)
        throws RemoteException;

    public abstract void startConference(String as[], ImsCallProfile imscallprofile)
        throws RemoteException;

    public abstract void startDtmf(char c)
        throws RemoteException;

    public abstract void stopDtmf()
        throws RemoteException;

    public abstract void terminate(int i)
        throws RemoteException;

    public abstract void update(int i, ImsStreamMediaProfile imsstreammediaprofile)
        throws RemoteException;
}
