// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telecom;

import android.net.Uri;
import android.os.*;
import android.telecom.*;
import java.util.List;

// Referenced classes of package com.android.internal.telecom:
//            RemoteServiceCallback, IVideoProvider

public interface IConnectionServiceAdapter
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IConnectionServiceAdapter
    {

        public static IConnectionServiceAdapter asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.telecom.IConnectionServiceAdapter");
            if(iinterface != null && (iinterface instanceof IConnectionServiceAdapter))
                return (IConnectionServiceAdapter)iinterface;
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
                parcel1.writeString("com.android.internal.telecom.IConnectionServiceAdapter");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionServiceAdapter");
                String s = parcel.readString();
                ParcelableConnection parcelableconnection;
                if(parcel.readInt() != 0)
                    parcel1 = (ConnectionRequest)ConnectionRequest.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    parcelableconnection = (ParcelableConnection)ParcelableConnection.CREATOR.createFromParcel(parcel);
                else
                    parcelableconnection = null;
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                handleCreateConnectionComplete(s, parcel1, parcelableconnection, parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionServiceAdapter");
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setActive(parcel1, parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionServiceAdapter");
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setRinging(parcel1, parcel);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionServiceAdapter");
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setDialing(parcel1, parcel);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionServiceAdapter");
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setPulling(parcel1, parcel);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionServiceAdapter");
                String s2 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel1 = (DisconnectCause)DisconnectCause.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setDisconnected(s2, parcel1, parcel);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionServiceAdapter");
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setOnHold(parcel1, parcel);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionServiceAdapter");
                parcel1 = parcel.readString();
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setRingbackRequested(parcel1, flag, parcel);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionServiceAdapter");
                parcel1 = parcel.readString();
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setConnectionCapabilities(parcel1, i, parcel);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionServiceAdapter");
                parcel1 = parcel.readString();
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setConnectionProperties(parcel1, i, parcel);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionServiceAdapter");
                String s3 = parcel.readString();
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setIsConferenced(s3, parcel1, parcel);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionServiceAdapter");
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setConferenceMergeFailed(parcel1, parcel);
                return true;

            case 13: // '\r'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionServiceAdapter");
                String s4 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel1 = (ParcelableConference)ParcelableConference.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                addConferenceCall(s4, parcel1, parcel);
                return true;

            case 14: // '\016'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionServiceAdapter");
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                removeCall(parcel1, parcel);
                return true;

            case 15: // '\017'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionServiceAdapter");
                String s5 = parcel.readString();
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onPostDialWait(s5, parcel1, parcel);
                return true;

            case 16: // '\020'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionServiceAdapter");
                parcel1 = parcel.readString();
                char c = (char)parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onPostDialChar(parcel1, c, parcel);
                return true;

            case 17: // '\021'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionServiceAdapter");
                parcel1 = RemoteServiceCallback.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                queryRemoteConnectionServices(parcel1, parcel);
                return true;

            case 18: // '\022'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionServiceAdapter");
                String s6 = parcel.readString();
                parcel1 = IVideoProvider.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setVideoProvider(s6, parcel1, parcel);
                return true;

            case 19: // '\023'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionServiceAdapter");
                parcel1 = parcel.readString();
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setVideoState(parcel1, i, parcel);
                return true;

            case 20: // '\024'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionServiceAdapter");
                parcel1 = parcel.readString();
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setIsVoipAudioMode(parcel1, flag1, parcel);
                return true;

            case 21: // '\025'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionServiceAdapter");
                String s7 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel1 = (StatusHints)StatusHints.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setStatusHints(s7, parcel1, parcel);
                return true;

            case 22: // '\026'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionServiceAdapter");
                String s8 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel1 = (Uri)Uri.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setAddress(s8, parcel1, i, parcel);
                return true;

            case 23: // '\027'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionServiceAdapter");
                parcel1 = parcel.readString();
                String s9 = parcel.readString();
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setCallerDisplayName(parcel1, s9, i, parcel);
                return true;

            case 24: // '\030'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionServiceAdapter");
                String s10 = parcel.readString();
                parcel1 = parcel.createStringArrayList();
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setConferenceableConnections(s10, parcel1, parcel);
                return true;

            case 25: // '\031'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionServiceAdapter");
                String s11 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel1 = (ParcelableConnection)ParcelableConnection.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                addExistingConnection(s11, parcel1, parcel);
                return true;

            case 26: // '\032'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionServiceAdapter");
                String s12 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel1 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                putExtras(s12, parcel1, parcel);
                return true;

            case 27: // '\033'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionServiceAdapter");
                String s13 = parcel.readString();
                parcel1 = parcel.createStringArrayList();
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                removeExtras(s13, parcel1, parcel);
                return true;

            case 28: // '\034'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionServiceAdapter");
                parcel1 = parcel.readString();
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setAudioRoute(parcel1, i, parcel);
                return true;

            case 29: // '\035'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionServiceAdapter");
                String s1 = parcel.readString();
                String s14 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel1 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onConnectionEvent(s1, s14, parcel1, parcel);
                return true;

            case 30: // '\036'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionServiceAdapter");
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onRttInitiationSuccess(parcel1, parcel);
                return true;

            case 31: // '\037'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionServiceAdapter");
                parcel1 = parcel.readString();
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onRttInitiationFailure(parcel1, i, parcel);
                return true;

            case 32: // ' '
                parcel.enforceInterface("com.android.internal.telecom.IConnectionServiceAdapter");
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onRttSessionRemotelyTerminated(parcel1, parcel);
                return true;

            case 33: // '!'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionServiceAdapter");
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onRemoteRttRequest(parcel1, parcel);
                return true;

            case 34: // '"'
                parcel.enforceInterface("com.android.internal.telecom.IConnectionServiceAdapter");
                parcel1 = parcel.readString();
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (android.telecom.Logging.Session.Info)android.telecom.Logging.Session.Info.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            resetCdmaConnectionTime(parcel1, parcel);
            return true;
        }

        private static final String DESCRIPTOR = "com.android.internal.telecom.IConnectionServiceAdapter";
        static final int TRANSACTION_addConferenceCall = 13;
        static final int TRANSACTION_addExistingConnection = 25;
        static final int TRANSACTION_handleCreateConnectionComplete = 1;
        static final int TRANSACTION_onConnectionEvent = 29;
        static final int TRANSACTION_onPostDialChar = 16;
        static final int TRANSACTION_onPostDialWait = 15;
        static final int TRANSACTION_onRemoteRttRequest = 33;
        static final int TRANSACTION_onRttInitiationFailure = 31;
        static final int TRANSACTION_onRttInitiationSuccess = 30;
        static final int TRANSACTION_onRttSessionRemotelyTerminated = 32;
        static final int TRANSACTION_putExtras = 26;
        static final int TRANSACTION_queryRemoteConnectionServices = 17;
        static final int TRANSACTION_removeCall = 14;
        static final int TRANSACTION_removeExtras = 27;
        static final int TRANSACTION_resetCdmaConnectionTime = 34;
        static final int TRANSACTION_setActive = 2;
        static final int TRANSACTION_setAddress = 22;
        static final int TRANSACTION_setAudioRoute = 28;
        static final int TRANSACTION_setCallerDisplayName = 23;
        static final int TRANSACTION_setConferenceMergeFailed = 12;
        static final int TRANSACTION_setConferenceableConnections = 24;
        static final int TRANSACTION_setConnectionCapabilities = 9;
        static final int TRANSACTION_setConnectionProperties = 10;
        static final int TRANSACTION_setDialing = 4;
        static final int TRANSACTION_setDisconnected = 6;
        static final int TRANSACTION_setIsConferenced = 11;
        static final int TRANSACTION_setIsVoipAudioMode = 20;
        static final int TRANSACTION_setOnHold = 7;
        static final int TRANSACTION_setPulling = 5;
        static final int TRANSACTION_setRingbackRequested = 8;
        static final int TRANSACTION_setRinging = 3;
        static final int TRANSACTION_setStatusHints = 21;
        static final int TRANSACTION_setVideoProvider = 18;
        static final int TRANSACTION_setVideoState = 19;

        public Stub()
        {
            attachInterface(this, "com.android.internal.telecom.IConnectionServiceAdapter");
        }
    }

    private static class Stub.Proxy
        implements IConnectionServiceAdapter
    {

        public void addConferenceCall(String s, ParcelableConference parcelableconference, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionServiceAdapter");
            parcel.writeString(s);
            if(parcelableconference == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            parcelableconference.writeToParcel(parcel, 0);
_L3:
            if(info == null)
                break MISSING_BLOCK_LABEL_91;
            parcel.writeInt(1);
            info.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(13, parcel, null, 1);
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

        public void addExistingConnection(String s, ParcelableConnection parcelableconnection, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionServiceAdapter");
            parcel.writeString(s);
            if(parcelableconnection == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            parcelableconnection.writeToParcel(parcel, 0);
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

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.telecom.IConnectionServiceAdapter";
        }

        public void handleCreateConnectionComplete(String s, ConnectionRequest connectionrequest, ParcelableConnection parcelableconnection, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionServiceAdapter");
            parcel.writeString(s);
            if(connectionrequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            connectionrequest.writeToParcel(parcel, 0);
_L5:
            if(parcelableconnection == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            parcelableconnection.writeToParcel(parcel, 0);
_L6:
            if(info == null)
                break MISSING_BLOCK_LABEL_118;
            parcel.writeInt(1);
            info.writeToParcel(parcel, 0);
_L7:
            mRemote.transact(1, parcel, null, 1);
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

        public void onConnectionEvent(String s, String s1, Bundle bundle, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionServiceAdapter");
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
            mRemote.transact(29, parcel, null, 1);
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

        public void onPostDialChar(String s, char c, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionServiceAdapter");
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

        public void onPostDialWait(String s, String s1, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionServiceAdapter");
            parcel.writeString(s);
            parcel.writeString(s1);
            if(info == null)
                break MISSING_BLOCK_LABEL_63;
            parcel.writeInt(1);
            info.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(15, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void onRemoteRttRequest(String s, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionServiceAdapter");
            parcel.writeString(s);
            if(info == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            info.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(33, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void onRttInitiationFailure(String s, int i, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionServiceAdapter");
            parcel.writeString(s);
            parcel.writeInt(i);
            if(info == null)
                break MISSING_BLOCK_LABEL_63;
            parcel.writeInt(1);
            info.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(31, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void onRttInitiationSuccess(String s, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionServiceAdapter");
            parcel.writeString(s);
            if(info == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            info.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(30, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void onRttSessionRemotelyTerminated(String s, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionServiceAdapter");
            parcel.writeString(s);
            if(info == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            info.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(32, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void putExtras(String s, Bundle bundle, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionServiceAdapter");
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
            mRemote.transact(26, parcel, null, 1);
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

        public void queryRemoteConnectionServices(RemoteServiceCallback remoteservicecallback, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionServiceAdapter");
            if(remoteservicecallback == null)
                break MISSING_BLOCK_LABEL_25;
            ibinder = remoteservicecallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(info == null)
                break MISSING_BLOCK_LABEL_70;
            parcel.writeInt(1);
            info.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(17, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            remoteservicecallback;
            parcel.recycle();
            throw remoteservicecallback;
        }

        public void removeCall(String s, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionServiceAdapter");
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

        public void removeExtras(String s, List list, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionServiceAdapter");
            parcel.writeString(s);
            parcel.writeStringList(list);
            if(info == null)
                break MISSING_BLOCK_LABEL_63;
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

        public void resetCdmaConnectionTime(String s, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionServiceAdapter");
            parcel.writeString(s);
            if(info == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            info.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(34, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void setActive(String s, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionServiceAdapter");
            parcel.writeString(s);
            if(info == null)
                break MISSING_BLOCK_LABEL_49;
            parcel.writeInt(1);
            info.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void setAddress(String s, Uri uri, int i, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionServiceAdapter");
            parcel.writeString(s);
            if(uri == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L3:
            parcel.writeInt(i);
            if(info == null)
                break MISSING_BLOCK_LABEL_99;
            parcel.writeInt(1);
            info.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(22, parcel, null, 1);
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

        public void setAudioRoute(String s, int i, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionServiceAdapter");
            parcel.writeString(s);
            parcel.writeInt(i);
            if(info == null)
                break MISSING_BLOCK_LABEL_63;
            parcel.writeInt(1);
            info.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(28, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void setCallerDisplayName(String s, String s1, int i, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionServiceAdapter");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(i);
            if(info == null)
                break MISSING_BLOCK_LABEL_71;
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

        public void setConferenceMergeFailed(String s, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionServiceAdapter");
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

        public void setConferenceableConnections(String s, List list, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionServiceAdapter");
            parcel.writeString(s);
            parcel.writeStringList(list);
            if(info == null)
                break MISSING_BLOCK_LABEL_63;
            parcel.writeInt(1);
            info.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(24, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void setConnectionCapabilities(String s, int i, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionServiceAdapter");
            parcel.writeString(s);
            parcel.writeInt(i);
            if(info == null)
                break MISSING_BLOCK_LABEL_63;
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

        public void setConnectionProperties(String s, int i, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionServiceAdapter");
            parcel.writeString(s);
            parcel.writeInt(i);
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

        public void setDialing(String s, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionServiceAdapter");
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

        public void setDisconnected(String s, DisconnectCause disconnectcause, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionServiceAdapter");
            parcel.writeString(s);
            if(disconnectcause == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            disconnectcause.writeToParcel(parcel, 0);
_L3:
            if(info == null)
                break MISSING_BLOCK_LABEL_91;
            parcel.writeInt(1);
            info.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(6, parcel, null, 1);
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

        public void setIsConferenced(String s, String s1, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionServiceAdapter");
            parcel.writeString(s);
            parcel.writeString(s1);
            if(info == null)
                break MISSING_BLOCK_LABEL_63;
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

        public void setIsVoipAudioMode(String s, boolean flag, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionServiceAdapter");
            parcel.writeString(s);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            if(info == null)
                break MISSING_BLOCK_LABEL_77;
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

        public void setOnHold(String s, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionServiceAdapter");
            parcel.writeString(s);
            if(info == null)
                break MISSING_BLOCK_LABEL_50;
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

        public void setPulling(String s, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionServiceAdapter");
            parcel.writeString(s);
            if(info == null)
                break MISSING_BLOCK_LABEL_49;
            parcel.writeInt(1);
            info.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void setRingbackRequested(String s, boolean flag, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionServiceAdapter");
            parcel.writeString(s);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            if(info == null)
                break MISSING_BLOCK_LABEL_77;
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

        public void setRinging(String s, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionServiceAdapter");
            parcel.writeString(s);
            if(info == null)
                break MISSING_BLOCK_LABEL_49;
            parcel.writeInt(1);
            info.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void setStatusHints(String s, StatusHints statushints, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionServiceAdapter");
            parcel.writeString(s);
            if(statushints == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            statushints.writeToParcel(parcel, 0);
_L3:
            if(info == null)
                break MISSING_BLOCK_LABEL_91;
            parcel.writeInt(1);
            info.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(21, parcel, null, 1);
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

        public void setVideoProvider(String s, IVideoProvider ivideoprovider, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionServiceAdapter");
            parcel.writeString(s);
            s = obj;
            if(ivideoprovider == null)
                break MISSING_BLOCK_LABEL_35;
            s = ivideoprovider.asBinder();
            parcel.writeStrongBinder(s);
            if(info == null)
                break MISSING_BLOCK_LABEL_80;
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

        public void setVideoState(String s, int i, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IConnectionServiceAdapter");
            parcel.writeString(s);
            parcel.writeInt(i);
            if(info == null)
                break MISSING_BLOCK_LABEL_63;
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

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void addConferenceCall(String s, ParcelableConference parcelableconference, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void addExistingConnection(String s, ParcelableConnection parcelableconnection, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void handleCreateConnectionComplete(String s, ConnectionRequest connectionrequest, ParcelableConnection parcelableconnection, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void onConnectionEvent(String s, String s1, Bundle bundle, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void onPostDialChar(String s, char c, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void onPostDialWait(String s, String s1, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void onRemoteRttRequest(String s, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void onRttInitiationFailure(String s, int i, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void onRttInitiationSuccess(String s, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void onRttSessionRemotelyTerminated(String s, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void putExtras(String s, Bundle bundle, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void queryRemoteConnectionServices(RemoteServiceCallback remoteservicecallback, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void removeCall(String s, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void removeExtras(String s, List list, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void resetCdmaConnectionTime(String s, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void setActive(String s, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void setAddress(String s, Uri uri, int i, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void setAudioRoute(String s, int i, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void setCallerDisplayName(String s, String s1, int i, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void setConferenceMergeFailed(String s, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void setConferenceableConnections(String s, List list, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void setConnectionCapabilities(String s, int i, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void setConnectionProperties(String s, int i, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void setDialing(String s, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void setDisconnected(String s, DisconnectCause disconnectcause, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void setIsConferenced(String s, String s1, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void setIsVoipAudioMode(String s, boolean flag, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void setOnHold(String s, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void setPulling(String s, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void setRingbackRequested(String s, boolean flag, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void setRinging(String s, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void setStatusHints(String s, StatusHints statushints, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void setVideoProvider(String s, IVideoProvider ivideoprovider, android.telecom.Logging.Session.Info info)
        throws RemoteException;

    public abstract void setVideoState(String s, int i, android.telecom.Logging.Session.Info info)
        throws RemoteException;
}
