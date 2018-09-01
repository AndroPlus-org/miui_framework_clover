// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telecom;

import android.os.*;
import android.telecom.*;

// Referenced classes of package com.android.internal.telecom:
//            IConnectionServiceAdapter

public interface IConnectionService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IConnectionService
    {

        public static IConnectionService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.telecom.IConnectionService");
            if(iinterface != null && (iinterface instanceof IConnectionService))
                return (IConnectionService)iinterface;
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
                parcel1.writeString("com.android.internal.telecom.IConnectionService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionService");
                parcel1 = IConnectionServiceAdapter.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                addConnectionServiceAdapter(parcel1, parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionService");
                parcel1 = IConnectionServiceAdapter.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                removeConnectionServiceAdapter(parcel1, parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionService");
                String s;
                ConnectionRequest connectionrequest;
                boolean flag;
                boolean flag3;
                if(parcel.readInt() != 0)
                    parcel1 = (PhoneAccountHandle)PhoneAccountHandle.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                s = parcel.readString();
                if(parcel.readInt() != 0)
                    connectionrequest = (ConnectionRequest)ConnectionRequest.CREATOR.createFromParcel(parcel);
                else
                    connectionrequest = null;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                if(parcel.readInt() != 0)
                    flag3 = true;
                else
                    flag3 = false;
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                createConnection(parcel1, s, connectionrequest, flag, flag3, parcel);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionService");
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                createConnectionComplete(parcel1, parcel);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionService");
                String s1;
                ConnectionRequest connectionrequest1;
                boolean flag1;
                if(parcel.readInt() != 0)
                    parcel1 = (PhoneAccountHandle)PhoneAccountHandle.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                s1 = parcel.readString();
                if(parcel.readInt() != 0)
                    connectionrequest1 = (ConnectionRequest)ConnectionRequest.CREATOR.createFromParcel(parcel);
                else
                    connectionrequest1 = null;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                createConnectionFailed(parcel1, s1, connectionrequest1, flag1, parcel);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionService");
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                abort(parcel1, parcel);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionService");
                parcel1 = parcel.readString();
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                answerVideo(parcel1, i, parcel);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionService");
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                answer(parcel1, parcel);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionService");
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                reject(parcel1, parcel);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionService");
                parcel1 = parcel.readString();
                String s5 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                rejectWithMessage(parcel1, s5, parcel);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionService");
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                disconnect(parcel1, parcel);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionService");
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                silence(parcel1, parcel);
                return true;

            case 13: // '\r'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionService");
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                hold(parcel1, parcel);
                return true;

            case 14: // '\016'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionService");
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                unhold(parcel1, parcel);
                return true;

            case 15: // '\017'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionService");
                String s6 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel1 = (CallAudioState)CallAudioState.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onCallAudioStateChanged(s6, parcel1, parcel);
                return true;

            case 16: // '\020'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionService");
                parcel1 = parcel.readString();
                char c = (char)parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                playDtmfTone(parcel1, c, parcel);
                return true;

            case 17: // '\021'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionService");
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                stopDtmfTone(parcel1, parcel);
                return true;

            case 18: // '\022'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionService");
                parcel1 = parcel.readString();
                String s7 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                conference(parcel1, s7, parcel);
                return true;

            case 19: // '\023'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionService");
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                splitFromConference(parcel1, parcel);
                return true;

            case 20: // '\024'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionService");
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                mergeConference(parcel1, parcel);
                return true;

            case 21: // '\025'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionService");
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                swapConference(parcel1, parcel);
                return true;

            case 22: // '\026'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionService");
                parcel1 = parcel.readString();
                boolean flag2;
                if(parcel.readInt() != 0)
                    flag2 = true;
                else
                    flag2 = false;
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onPostDialContinue(parcel1, flag2, parcel);
                return true;

            case 23: // '\027'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionService");
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                pullExternalCall(parcel1, parcel);
                return true;

            case 24: // '\030'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionService");
                String s8 = parcel.readString();
                String s2 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel1 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                sendCallEvent(s8, s2, parcel1, parcel);
                return true;

            case 25: // '\031'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionService");
                String s9 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel1 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onExtrasChanged(s9, parcel1, parcel);
                return true;

            case 26: // '\032'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionService");
                String s3 = parcel.readString();
                ParcelFileDescriptor parcelfiledescriptor;
                if(parcel.readInt() != 0)
                    parcel1 = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    parcelfiledescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                else
                    parcelfiledescriptor = null;
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                startRtt(s3, parcel1, parcelfiledescriptor, parcel);
                return true;

            case 27: // '\033'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionService");
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                stopRtt(parcel1, parcel);
                return true;

            case 28: // '\034'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionService");
                String s4 = parcel.readString();
                ParcelFileDescriptor parcelfiledescriptor1;
                if(parcel.readInt() != 0)
                    parcel1 = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    parcelfiledescriptor1 = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                else
                    parcelfiledescriptor1 = null;
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                respondToRttUpgradeRequest(s4, parcel1, parcelfiledescriptor1, parcel);
                return true;

            case 29: // '\035'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionService");
                addParticipantWithConference(parcel.readString(), parcel.readString());
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.internal.telecom.IConnectionService";
        static final int TRANSACTION_abort = 6;
        static final int TRANSACTION_addConnectionServiceAdapter = 1;
        static final int TRANSACTION_addParticipantWithConference = 29;
        static final int TRANSACTION_answer = 8;
        static final int TRANSACTION_answerVideo = 7;
        static final int TRANSACTION_conference = 18;
        static final int TRANSACTION_createConnection = 3;
        static final int TRANSACTION_createConnectionComplete = 4;
        static final int TRANSACTION_createConnectionFailed = 5;
        static final int TRANSACTION_disconnect = 11;
        static final int TRANSACTION_hold = 13;
        static final int TRANSACTION_mergeConference = 20;
        static final int TRANSACTION_onCallAudioStateChanged = 15;
        static final int TRANSACTION_onExtrasChanged = 25;
        static final int TRANSACTION_onPostDialContinue = 22;
        static final int TRANSACTION_playDtmfTone = 16;
        static final int TRANSACTION_pullExternalCall = 23;
        static final int TRANSACTION_reject = 9;
        static final int TRANSACTION_rejectWithMessage = 10;
        static final int TRANSACTION_removeConnectionServiceAdapter = 2;
        static final int TRANSACTION_respondToRttUpgradeRequest = 28;
        static final int TRANSACTION_sendCallEvent = 24;
        static final int TRANSACTION_silence = 12;
        static final int TRANSACTION_splitFromConference = 19;
        static final int TRANSACTION_startRtt = 26;
        static final int TRANSACTION_stopDtmfTone = 17;
        static final int TRANSACTION_stopRtt = 27;
        static final int TRANSACTION_swapConference = 21;
        static final int TRANSACTION_unhold = 14;

        public Stub()
        {
            attachInterface(this, "com.android.internal.telecom.IConnectionService");
        }
    }

    private static class Stub.Proxy
        implements IConnectionService
    {

        public void abort(String s, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionService");
            parcel.writeString(s);
            if(info == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            info.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void addConnectionServiceAdapter(IConnectionServiceAdapter iconnectionserviceadapter, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionService");
            if(iconnectionserviceadapter == null)
                break MISSING_BLOCK_LABEL_25;
            ibinder = iconnectionserviceadapter.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(info == null)
                break MISSING_BLOCK_LABEL_69;
            parcel.writeInt(1);
            info.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iconnectionserviceadapter;
            parcel.recycle();
            throw iconnectionserviceadapter;
        }

        public void addParticipantWithConference(String s, String s1)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionService");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(29, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void answer(String s, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionService");
            parcel.writeString(s);
            if(info == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            info.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(8, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void answerVideo(String s, int i, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionService");
            parcel.writeString(s);
            parcel.writeInt(i);
            if(info == null)
                break MISSING_BLOCK_LABEL_63;
            parcel.writeInt(1);
            info.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void conference(String s, String s1, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionService");
            parcel.writeString(s);
            parcel.writeString(s1);
            if(info == null)
                break MISSING_BLOCK_LABEL_63;
            parcel.writeInt(1);
            info.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(18, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void createConnection(PhoneAccountHandle phoneaccounthandle, String s, ConnectionRequest connectionrequest, boolean flag, boolean flag1, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            boolean flag2;
            Parcel parcel;
            flag2 = true;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionService");
            if(phoneaccounthandle == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            phoneaccounthandle.writeToParcel(parcel, 0);
_L5:
            parcel.writeString(s);
            if(connectionrequest == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            connectionrequest.writeToParcel(parcel, 0);
_L6:
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(flag1)
                i = ((flag2) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            if(info == null)
                break MISSING_BLOCK_LABEL_164;
            parcel.writeInt(1);
            info.writeToParcel(parcel, 0);
_L7:
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L5
            phoneaccounthandle;
            parcel.recycle();
            throw phoneaccounthandle;
_L4:
            parcel.writeInt(0);
              goto _L6
            parcel.writeInt(0);
              goto _L7
        }

        public void createConnectionComplete(String s, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionService");
            parcel.writeString(s);
            if(info == null)
                break MISSING_BLOCK_LABEL_49;
            parcel.writeInt(1);
            info.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void createConnectionFailed(PhoneAccountHandle phoneaccounthandle, String s, ConnectionRequest connectionrequest, boolean flag, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionService");
            if(phoneaccounthandle == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            phoneaccounthandle.writeToParcel(parcel, 0);
_L5:
            parcel.writeString(s);
            if(connectionrequest == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            connectionrequest.writeToParcel(parcel, 0);
_L6:
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            if(info == null)
                break MISSING_BLOCK_LABEL_139;
            parcel.writeInt(1);
            info.writeToParcel(parcel, 0);
_L7:
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L5
            phoneaccounthandle;
            parcel.recycle();
            throw phoneaccounthandle;
_L4:
            parcel.writeInt(0);
              goto _L6
            parcel.writeInt(0);
              goto _L7
        }

        public void disconnect(String s, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionService");
            parcel.writeString(s);
            if(info == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            info.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(11, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.telecom.IConnectionService";
        }

        public void hold(String s, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionService");
            parcel.writeString(s);
            if(info == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            info.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(13, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void mergeConference(String s, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionService");
            parcel.writeString(s);
            if(info == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            info.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(20, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void onCallAudioStateChanged(String s, CallAudioState callaudiostate, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionService");
            parcel.writeString(s);
            if(callaudiostate == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            callaudiostate.writeToParcel(parcel, 0);
_L3:
            if(info == null)
                break MISSING_BLOCK_LABEL_91;
            parcel.writeInt(1);
            info.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(15, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            s;
            parcel.recycle();
            throw s;
            parcel.writeInt(0);
              goto _L4
        }

        public void onExtrasChanged(String s, Bundle bundle, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionService");
            parcel.writeString(s);
            if(bundle == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L3:
            if(info == null)
                break MISSING_BLOCK_LABEL_91;
            parcel.writeInt(1);
            info.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(25, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            s;
            parcel.recycle();
            throw s;
            parcel.writeInt(0);
              goto _L4
        }

        public void onPostDialContinue(String s, boolean flag, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionService");
            parcel.writeString(s);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            if(info == null)
                break MISSING_BLOCK_LABEL_77;
            parcel.writeInt(1);
            info.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(22, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void playDtmfTone(String s, char c, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionService");
            parcel.writeString(s);
            parcel.writeInt(c);
            if(info == null)
                break MISSING_BLOCK_LABEL_63;
            parcel.writeInt(1);
            info.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(16, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void pullExternalCall(String s, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionService");
            parcel.writeString(s);
            if(info == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            info.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(23, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void reject(String s, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionService");
            parcel.writeString(s);
            if(info == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            info.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(9, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void rejectWithMessage(String s, String s1, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionService");
            parcel.writeString(s);
            parcel.writeString(s1);
            if(info == null)
                break MISSING_BLOCK_LABEL_63;
            parcel.writeInt(1);
            info.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(10, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void removeConnectionServiceAdapter(IConnectionServiceAdapter iconnectionserviceadapter, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionService");
            if(iconnectionserviceadapter == null)
                break MISSING_BLOCK_LABEL_25;
            ibinder = iconnectionserviceadapter.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(info == null)
                break MISSING_BLOCK_LABEL_69;
            parcel.writeInt(1);
            info.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iconnectionserviceadapter;
            parcel.recycle();
            throw iconnectionserviceadapter;
        }

        public void respondToRttUpgradeRequest(String s, ParcelFileDescriptor parcelfiledescriptor, ParcelFileDescriptor parcelfiledescriptor1, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionService");
            parcel.writeString(s);
            if(parcelfiledescriptor == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            parcelfiledescriptor.writeToParcel(parcel, 0);
_L5:
            if(parcelfiledescriptor1 == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            parcelfiledescriptor1.writeToParcel(parcel, 0);
_L6:
            if(info == null)
                break MISSING_BLOCK_LABEL_119;
            parcel.writeInt(1);
            info.writeToParcel(parcel, 0);
_L7:
            mRemote.transact(28, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L5
            s;
            parcel.recycle();
            throw s;
_L4:
            parcel.writeInt(0);
              goto _L6
            parcel.writeInt(0);
              goto _L7
        }

        public void sendCallEvent(String s, String s1, Bundle bundle, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionService");
            parcel.writeString(s);
            parcel.writeString(s1);
            if(bundle == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L3:
            if(info == null)
                break MISSING_BLOCK_LABEL_99;
            parcel.writeInt(1);
            info.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(24, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            s;
            parcel.recycle();
            throw s;
            parcel.writeInt(0);
              goto _L4
        }

        public void silence(String s, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionService");
            parcel.writeString(s);
            if(info == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            info.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(12, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void splitFromConference(String s, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionService");
            parcel.writeString(s);
            if(info == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            info.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(19, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void startRtt(String s, ParcelFileDescriptor parcelfiledescriptor, ParcelFileDescriptor parcelfiledescriptor1, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionService");
            parcel.writeString(s);
            if(parcelfiledescriptor == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            parcelfiledescriptor.writeToParcel(parcel, 0);
_L5:
            if(parcelfiledescriptor1 == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            parcelfiledescriptor1.writeToParcel(parcel, 0);
_L6:
            if(info == null)
                break MISSING_BLOCK_LABEL_119;
            parcel.writeInt(1);
            info.writeToParcel(parcel, 0);
_L7:
            mRemote.transact(26, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L5
            s;
            parcel.recycle();
            throw s;
_L4:
            parcel.writeInt(0);
              goto _L6
            parcel.writeInt(0);
              goto _L7
        }

        public void stopDtmfTone(String s, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionService");
            parcel.writeString(s);
            if(info == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            info.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(17, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void stopRtt(String s, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionService");
            parcel.writeString(s);
            if(info == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            info.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(27, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void swapConference(String s, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionService");
            parcel.writeString(s);
            if(info == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            info.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(21, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void unhold(String s, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionService");
            parcel.writeString(s);
            if(info == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            info.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(14, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void abort(String s, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void addConnectionServiceAdapter(IConnectionServiceAdapter iconnectionserviceadapter, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void addParticipantWithConference(String s, String s1)
        throws RemoteException;

    public abstract void answer(String s, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void answerVideo(String s, int i, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void conference(String s, String s1, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void createConnection(PhoneAccountHandle phoneaccounthandle, String s, ConnectionRequest connectionrequest, boolean flag, boolean flag1, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void createConnectionComplete(String s, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void createConnectionFailed(PhoneAccountHandle phoneaccounthandle, String s, ConnectionRequest connectionrequest, boolean flag, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void disconnect(String s, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void hold(String s, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void mergeConference(String s, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void onCallAudioStateChanged(String s, CallAudioState callaudiostate, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void onExtrasChanged(String s, Bundle bundle, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void onPostDialContinue(String s, boolean flag, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void playDtmfTone(String s, char c, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void pullExternalCall(String s, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void reject(String s, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void rejectWithMessage(String s, String s1, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void removeConnectionServiceAdapter(IConnectionServiceAdapter iconnectionserviceadapter, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void respondToRttUpgradeRequest(String s, ParcelFileDescriptor parcelfiledescriptor, ParcelFileDescriptor parcelfiledescriptor1, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void sendCallEvent(String s, String s1, Bundle bundle, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void silence(String s, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void splitFromConference(String s, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void startRtt(String s, ParcelFileDescriptor parcelfiledescriptor, ParcelFileDescriptor parcelfiledescriptor1, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void stopDtmfTone(String s, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void stopRtt(String s, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void swapConference(String s, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void unhold(String s, android.telecom.Logging.Session.Info info)
        throws RemoteException;
}
