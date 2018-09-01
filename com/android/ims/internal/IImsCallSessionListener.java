// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims.internal;

import android.os.*;
import com.android.ims.*;

// Referenced classes of package com.android.ims.internal:
//            IImsCallSession

public interface IImsCallSessionListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IImsCallSessionListener
    {

        public static IImsCallSessionListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.ims.internal.IImsCallSessionListener");
            if(iinterface != null && (iinterface instanceof IImsCallSessionListener))
                return (IImsCallSessionListener)iinterface;
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
                parcel1.writeString("com.android.ims.internal.IImsCallSessionListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSessionListener");
                parcel1 = IImsCallSession.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel = (ImsStreamMediaProfile)ImsStreamMediaProfile.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                callSessionProgressing(parcel1, parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSessionListener");
                parcel1 = IImsCallSession.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel = (ImsCallProfile)ImsCallProfile.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                callSessionStarted(parcel1, parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSessionListener");
                parcel1 = IImsCallSession.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel = (ImsReasonInfo)ImsReasonInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                callSessionStartFailed(parcel1, parcel);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSessionListener");
                parcel1 = IImsCallSession.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel = (ImsReasonInfo)ImsReasonInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                callSessionTerminated(parcel1, parcel);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSessionListener");
                parcel1 = IImsCallSession.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel = (ImsCallProfile)ImsCallProfile.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                callSessionHeld(parcel1, parcel);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSessionListener");
                parcel1 = IImsCallSession.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel = (ImsReasonInfo)ImsReasonInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                callSessionHoldFailed(parcel1, parcel);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSessionListener");
                parcel1 = IImsCallSession.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel = (ImsCallProfile)ImsCallProfile.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                callSessionHoldReceived(parcel1, parcel);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSessionListener");
                parcel1 = IImsCallSession.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel = (ImsCallProfile)ImsCallProfile.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                callSessionResumed(parcel1, parcel);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSessionListener");
                parcel1 = IImsCallSession.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel = (ImsReasonInfo)ImsReasonInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                callSessionResumeFailed(parcel1, parcel);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSessionListener");
                parcel1 = IImsCallSession.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel = (ImsCallProfile)ImsCallProfile.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                callSessionResumeReceived(parcel1, parcel);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSessionListener");
                IImsCallSession iimscallsession = IImsCallSession.Stub.asInterface(parcel.readStrongBinder());
                parcel1 = IImsCallSession.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel = (ImsCallProfile)ImsCallProfile.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                callSessionMergeStarted(iimscallsession, parcel1, parcel);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSessionListener");
                callSessionMergeComplete(IImsCallSession.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 13: // '\r'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSessionListener");
                parcel1 = IImsCallSession.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel = (ImsReasonInfo)ImsReasonInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                callSessionMergeFailed(parcel1, parcel);
                return true;

            case 14: // '\016'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSessionListener");
                parcel1 = IImsCallSession.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel = (ImsCallProfile)ImsCallProfile.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                callSessionUpdated(parcel1, parcel);
                return true;

            case 15: // '\017'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSessionListener");
                parcel1 = IImsCallSession.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel = (ImsReasonInfo)ImsReasonInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                callSessionUpdateFailed(parcel1, parcel);
                return true;

            case 16: // '\020'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSessionListener");
                parcel1 = IImsCallSession.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel = (ImsCallProfile)ImsCallProfile.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                callSessionUpdateReceived(parcel1, parcel);
                return true;

            case 17: // '\021'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSessionListener");
                IImsCallSession iimscallsession1 = IImsCallSession.Stub.asInterface(parcel.readStrongBinder());
                parcel1 = IImsCallSession.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel = (ImsCallProfile)ImsCallProfile.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                callSessionConferenceExtended(iimscallsession1, parcel1, parcel);
                return true;

            case 18: // '\022'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSessionListener");
                parcel1 = IImsCallSession.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel = (ImsReasonInfo)ImsReasonInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                callSessionConferenceExtendFailed(parcel1, parcel);
                return true;

            case 19: // '\023'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSessionListener");
                parcel1 = IImsCallSession.Stub.asInterface(parcel.readStrongBinder());
                IImsCallSession iimscallsession2 = IImsCallSession.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel = (ImsCallProfile)ImsCallProfile.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                callSessionConferenceExtendReceived(parcel1, iimscallsession2, parcel);
                return true;

            case 20: // '\024'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSessionListener");
                callSessionInviteParticipantsRequestDelivered(IImsCallSession.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 21: // '\025'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSessionListener");
                parcel1 = IImsCallSession.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel = (ImsReasonInfo)ImsReasonInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                callSessionInviteParticipantsRequestFailed(parcel1, parcel);
                return true;

            case 22: // '\026'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSessionListener");
                callSessionRemoveParticipantsRequestDelivered(IImsCallSession.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 23: // '\027'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSessionListener");
                parcel1 = IImsCallSession.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel = (ImsReasonInfo)ImsReasonInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                callSessionRemoveParticipantsRequestFailed(parcel1, parcel);
                return true;

            case 24: // '\030'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSessionListener");
                parcel1 = IImsCallSession.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel = (ImsConferenceState)ImsConferenceState.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                callSessionConferenceStateUpdated(parcel1, parcel);
                return true;

            case 25: // '\031'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSessionListener");
                callSessionUssdMessageReceived(IImsCallSession.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readString());
                return true;

            case 26: // '\032'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSessionListener");
                parcel1 = IImsCallSession.Stub.asInterface(parcel.readStrongBinder());
                j = parcel.readInt();
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (ImsReasonInfo)ImsReasonInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                callSessionHandover(parcel1, j, i, parcel);
                return true;

            case 27: // '\033'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSessionListener");
                parcel1 = IImsCallSession.Stub.asInterface(parcel.readStrongBinder());
                i = parcel.readInt();
                j = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (ImsReasonInfo)ImsReasonInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                callSessionHandoverFailed(parcel1, i, j, parcel);
                return true;

            case 28: // '\034'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSessionListener");
                callSessionMayHandover(IImsCallSession.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                return true;

            case 29: // '\035'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSessionListener");
                callSessionTtyModeReceived(IImsCallSession.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                return true;

            case 30: // '\036'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSessionListener");
                parcel1 = IImsCallSession.Stub.asInterface(parcel.readStrongBinder());
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                callSessionMultipartyStateChanged(parcel1, flag);
                return true;

            case 31: // '\037'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSessionListener");
                parcel1 = IImsCallSession.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel = (ImsSuppServiceNotification)ImsSuppServiceNotification.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                callSessionSuppServiceReceived(parcel1, parcel);
                return true;

            case 32: // ' '
                parcel.enforceInterface("com.android.ims.internal.IImsCallSessionListener");
                parcel1 = IImsCallSession.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel = (ImsCallProfile)ImsCallProfile.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                callSessionRttModifyRequestReceived(parcel1, parcel);
                return true;

            case 33: // '!'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSessionListener");
                callSessionRttModifyResponseReceived(parcel.readInt());
                return true;

            case 34: // '"'
                parcel.enforceInterface("com.android.ims.internal.IImsCallSessionListener");
                callSessionRttMessageReceived(parcel.readString());
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.ims.internal.IImsCallSessionListener";
        static final int TRANSACTION_callSessionConferenceExtendFailed = 18;
        static final int TRANSACTION_callSessionConferenceExtendReceived = 19;
        static final int TRANSACTION_callSessionConferenceExtended = 17;
        static final int TRANSACTION_callSessionConferenceStateUpdated = 24;
        static final int TRANSACTION_callSessionHandover = 26;
        static final int TRANSACTION_callSessionHandoverFailed = 27;
        static final int TRANSACTION_callSessionHeld = 5;
        static final int TRANSACTION_callSessionHoldFailed = 6;
        static final int TRANSACTION_callSessionHoldReceived = 7;
        static final int TRANSACTION_callSessionInviteParticipantsRequestDelivered = 20;
        static final int TRANSACTION_callSessionInviteParticipantsRequestFailed = 21;
        static final int TRANSACTION_callSessionMayHandover = 28;
        static final int TRANSACTION_callSessionMergeComplete = 12;
        static final int TRANSACTION_callSessionMergeFailed = 13;
        static final int TRANSACTION_callSessionMergeStarted = 11;
        static final int TRANSACTION_callSessionMultipartyStateChanged = 30;
        static final int TRANSACTION_callSessionProgressing = 1;
        static final int TRANSACTION_callSessionRemoveParticipantsRequestDelivered = 22;
        static final int TRANSACTION_callSessionRemoveParticipantsRequestFailed = 23;
        static final int TRANSACTION_callSessionResumeFailed = 9;
        static final int TRANSACTION_callSessionResumeReceived = 10;
        static final int TRANSACTION_callSessionResumed = 8;
        static final int TRANSACTION_callSessionRttMessageReceived = 34;
        static final int TRANSACTION_callSessionRttModifyRequestReceived = 32;
        static final int TRANSACTION_callSessionRttModifyResponseReceived = 33;
        static final int TRANSACTION_callSessionStartFailed = 3;
        static final int TRANSACTION_callSessionStarted = 2;
        static final int TRANSACTION_callSessionSuppServiceReceived = 31;
        static final int TRANSACTION_callSessionTerminated = 4;
        static final int TRANSACTION_callSessionTtyModeReceived = 29;
        static final int TRANSACTION_callSessionUpdateFailed = 15;
        static final int TRANSACTION_callSessionUpdateReceived = 16;
        static final int TRANSACTION_callSessionUpdated = 14;
        static final int TRANSACTION_callSessionUssdMessageReceived = 25;

        public Stub()
        {
            attachInterface(this, "com.android.ims.internal.IImsCallSessionListener");
        }
    }

    private static class Stub.Proxy
        implements IImsCallSessionListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void callSessionConferenceExtendFailed(IImsCallSession iimscallsession, ImsReasonInfo imsreasoninfo)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSessionListener");
            if(iimscallsession == null)
                break MISSING_BLOCK_LABEL_25;
            ibinder = iimscallsession.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(imsreasoninfo == null)
                break MISSING_BLOCK_LABEL_70;
            parcel.writeInt(1);
            imsreasoninfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(18, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iimscallsession;
            parcel.recycle();
            throw iimscallsession;
        }

        public void callSessionConferenceExtendReceived(IImsCallSession iimscallsession, IImsCallSession iimscallsession1, ImsCallProfile imscallprofile)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSessionListener");
            if(iimscallsession == null) goto _L2; else goto _L1
_L1:
            iimscallsession = iimscallsession.asBinder();
_L5:
            parcel.writeStrongBinder(iimscallsession);
            iimscallsession = obj;
            if(iimscallsession1 == null)
                break MISSING_BLOCK_LABEL_46;
            iimscallsession = iimscallsession1.asBinder();
            parcel.writeStrongBinder(iimscallsession);
            if(imscallprofile == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            imscallprofile.writeToParcel(parcel, 0);
_L6:
            mRemote.transact(19, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            iimscallsession = null;
              goto _L5
_L4:
            parcel.writeInt(0);
              goto _L6
            iimscallsession;
            parcel.recycle();
            throw iimscallsession;
              goto _L5
        }

        public void callSessionConferenceExtended(IImsCallSession iimscallsession, IImsCallSession iimscallsession1, ImsCallProfile imscallprofile)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSessionListener");
            if(iimscallsession == null) goto _L2; else goto _L1
_L1:
            iimscallsession = iimscallsession.asBinder();
_L5:
            parcel.writeStrongBinder(iimscallsession);
            iimscallsession = obj;
            if(iimscallsession1 == null)
                break MISSING_BLOCK_LABEL_46;
            iimscallsession = iimscallsession1.asBinder();
            parcel.writeStrongBinder(iimscallsession);
            if(imscallprofile == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            imscallprofile.writeToParcel(parcel, 0);
_L6:
            mRemote.transact(17, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            iimscallsession = null;
              goto _L5
_L4:
            parcel.writeInt(0);
              goto _L6
            iimscallsession;
            parcel.recycle();
            throw iimscallsession;
              goto _L5
        }

        public void callSessionConferenceStateUpdated(IImsCallSession iimscallsession, ImsConferenceState imsconferencestate)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSessionListener");
            if(iimscallsession == null)
                break MISSING_BLOCK_LABEL_25;
            ibinder = iimscallsession.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(imsconferencestate == null)
                break MISSING_BLOCK_LABEL_70;
            parcel.writeInt(1);
            imsconferencestate.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(24, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iimscallsession;
            parcel.recycle();
            throw iimscallsession;
        }

        public void callSessionHandover(IImsCallSession iimscallsession, int i, int j, ImsReasonInfo imsreasoninfo)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSessionListener");
            if(iimscallsession == null)
                break MISSING_BLOCK_LABEL_27;
            ibinder = iimscallsession.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            parcel.writeInt(j);
            if(imsreasoninfo == null)
                break MISSING_BLOCK_LABEL_87;
            parcel.writeInt(1);
            imsreasoninfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(26, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iimscallsession;
            parcel.recycle();
            throw iimscallsession;
        }

        public void callSessionHandoverFailed(IImsCallSession iimscallsession, int i, int j, ImsReasonInfo imsreasoninfo)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSessionListener");
            if(iimscallsession == null)
                break MISSING_BLOCK_LABEL_27;
            ibinder = iimscallsession.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            parcel.writeInt(j);
            if(imsreasoninfo == null)
                break MISSING_BLOCK_LABEL_87;
            parcel.writeInt(1);
            imsreasoninfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(27, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iimscallsession;
            parcel.recycle();
            throw iimscallsession;
        }

        public void callSessionHeld(IImsCallSession iimscallsession, ImsCallProfile imscallprofile)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSessionListener");
            if(iimscallsession == null)
                break MISSING_BLOCK_LABEL_25;
            ibinder = iimscallsession.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(imscallprofile == null)
                break MISSING_BLOCK_LABEL_69;
            parcel.writeInt(1);
            imscallprofile.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iimscallsession;
            parcel.recycle();
            throw iimscallsession;
        }

        public void callSessionHoldFailed(IImsCallSession iimscallsession, ImsReasonInfo imsreasoninfo)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSessionListener");
            if(iimscallsession == null)
                break MISSING_BLOCK_LABEL_25;
            ibinder = iimscallsession.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(imsreasoninfo == null)
                break MISSING_BLOCK_LABEL_70;
            parcel.writeInt(1);
            imsreasoninfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iimscallsession;
            parcel.recycle();
            throw iimscallsession;
        }

        public void callSessionHoldReceived(IImsCallSession iimscallsession, ImsCallProfile imscallprofile)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSessionListener");
            if(iimscallsession == null)
                break MISSING_BLOCK_LABEL_25;
            ibinder = iimscallsession.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(imscallprofile == null)
                break MISSING_BLOCK_LABEL_70;
            parcel.writeInt(1);
            imscallprofile.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iimscallsession;
            parcel.recycle();
            throw iimscallsession;
        }

        public void callSessionInviteParticipantsRequestDelivered(IImsCallSession iimscallsession)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSessionListener");
            if(iimscallsession == null)
                break MISSING_BLOCK_LABEL_23;
            ibinder = iimscallsession.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(20, parcel, null, 1);
            parcel.recycle();
            return;
            iimscallsession;
            parcel.recycle();
            throw iimscallsession;
        }

        public void callSessionInviteParticipantsRequestFailed(IImsCallSession iimscallsession, ImsReasonInfo imsreasoninfo)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSessionListener");
            if(iimscallsession == null)
                break MISSING_BLOCK_LABEL_25;
            ibinder = iimscallsession.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(imsreasoninfo == null)
                break MISSING_BLOCK_LABEL_70;
            parcel.writeInt(1);
            imsreasoninfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(21, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iimscallsession;
            parcel.recycle();
            throw iimscallsession;
        }

        public void callSessionMayHandover(IImsCallSession iimscallsession, int i, int j)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSessionListener");
            if(iimscallsession == null)
                break MISSING_BLOCK_LABEL_27;
            ibinder = iimscallsession.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(28, parcel, null, 1);
            parcel.recycle();
            return;
            iimscallsession;
            parcel.recycle();
            throw iimscallsession;
        }

        public void callSessionMergeComplete(IImsCallSession iimscallsession)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSessionListener");
            if(iimscallsession == null)
                break MISSING_BLOCK_LABEL_23;
            ibinder = iimscallsession.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(12, parcel, null, 1);
            parcel.recycle();
            return;
            iimscallsession;
            parcel.recycle();
            throw iimscallsession;
        }

        public void callSessionMergeFailed(IImsCallSession iimscallsession, ImsReasonInfo imsreasoninfo)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSessionListener");
            if(iimscallsession == null)
                break MISSING_BLOCK_LABEL_25;
            ibinder = iimscallsession.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(imsreasoninfo == null)
                break MISSING_BLOCK_LABEL_70;
            parcel.writeInt(1);
            imsreasoninfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(13, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iimscallsession;
            parcel.recycle();
            throw iimscallsession;
        }

        public void callSessionMergeStarted(IImsCallSession iimscallsession, IImsCallSession iimscallsession1, ImsCallProfile imscallprofile)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSessionListener");
            if(iimscallsession == null) goto _L2; else goto _L1
_L1:
            iimscallsession = iimscallsession.asBinder();
_L5:
            parcel.writeStrongBinder(iimscallsession);
            iimscallsession = obj;
            if(iimscallsession1 == null)
                break MISSING_BLOCK_LABEL_46;
            iimscallsession = iimscallsession1.asBinder();
            parcel.writeStrongBinder(iimscallsession);
            if(imscallprofile == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            imscallprofile.writeToParcel(parcel, 0);
_L6:
            mRemote.transact(11, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            iimscallsession = null;
              goto _L5
_L4:
            parcel.writeInt(0);
              goto _L6
            iimscallsession;
            parcel.recycle();
            throw iimscallsession;
              goto _L5
        }

        public void callSessionMultipartyStateChanged(IImsCallSession iimscallsession, boolean flag)
            throws RemoteException
        {
            int i;
            IBinder ibinder;
            Parcel parcel;
            i = 1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSessionListener");
            if(iimscallsession == null)
                break MISSING_BLOCK_LABEL_29;
            ibinder = iimscallsession.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(30, parcel, null, 1);
            parcel.recycle();
            return;
            iimscallsession;
            parcel.recycle();
            throw iimscallsession;
        }

        public void callSessionProgressing(IImsCallSession iimscallsession, ImsStreamMediaProfile imsstreammediaprofile)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSessionListener");
            if(iimscallsession == null)
                break MISSING_BLOCK_LABEL_25;
            ibinder = iimscallsession.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(imsstreammediaprofile == null)
                break MISSING_BLOCK_LABEL_69;
            parcel.writeInt(1);
            imsstreammediaprofile.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iimscallsession;
            parcel.recycle();
            throw iimscallsession;
        }

        public void callSessionRemoveParticipantsRequestDelivered(IImsCallSession iimscallsession)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSessionListener");
            if(iimscallsession == null)
                break MISSING_BLOCK_LABEL_23;
            ibinder = iimscallsession.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(22, parcel, null, 1);
            parcel.recycle();
            return;
            iimscallsession;
            parcel.recycle();
            throw iimscallsession;
        }

        public void callSessionRemoveParticipantsRequestFailed(IImsCallSession iimscallsession, ImsReasonInfo imsreasoninfo)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSessionListener");
            if(iimscallsession == null)
                break MISSING_BLOCK_LABEL_25;
            ibinder = iimscallsession.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(imsreasoninfo == null)
                break MISSING_BLOCK_LABEL_70;
            parcel.writeInt(1);
            imsreasoninfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(23, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iimscallsession;
            parcel.recycle();
            throw iimscallsession;
        }

        public void callSessionResumeFailed(IImsCallSession iimscallsession, ImsReasonInfo imsreasoninfo)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSessionListener");
            if(iimscallsession == null)
                break MISSING_BLOCK_LABEL_25;
            ibinder = iimscallsession.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(imsreasoninfo == null)
                break MISSING_BLOCK_LABEL_70;
            parcel.writeInt(1);
            imsreasoninfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(9, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iimscallsession;
            parcel.recycle();
            throw iimscallsession;
        }

        public void callSessionResumeReceived(IImsCallSession iimscallsession, ImsCallProfile imscallprofile)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSessionListener");
            if(iimscallsession == null)
                break MISSING_BLOCK_LABEL_25;
            ibinder = iimscallsession.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(imscallprofile == null)
                break MISSING_BLOCK_LABEL_70;
            parcel.writeInt(1);
            imscallprofile.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(10, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iimscallsession;
            parcel.recycle();
            throw iimscallsession;
        }

        public void callSessionResumed(IImsCallSession iimscallsession, ImsCallProfile imscallprofile)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSessionListener");
            if(iimscallsession == null)
                break MISSING_BLOCK_LABEL_25;
            ibinder = iimscallsession.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(imscallprofile == null)
                break MISSING_BLOCK_LABEL_70;
            parcel.writeInt(1);
            imscallprofile.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(8, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iimscallsession;
            parcel.recycle();
            throw iimscallsession;
        }

        public void callSessionRttMessageReceived(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSessionListener");
            parcel.writeString(s);
            mRemote.transact(34, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void callSessionRttModifyRequestReceived(IImsCallSession iimscallsession, ImsCallProfile imscallprofile)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSessionListener");
            if(iimscallsession == null)
                break MISSING_BLOCK_LABEL_25;
            ibinder = iimscallsession.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(imscallprofile == null)
                break MISSING_BLOCK_LABEL_70;
            parcel.writeInt(1);
            imscallprofile.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(32, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iimscallsession;
            parcel.recycle();
            throw iimscallsession;
        }

        public void callSessionRttModifyResponseReceived(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSessionListener");
            parcel.writeInt(i);
            mRemote.transact(33, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void callSessionStartFailed(IImsCallSession iimscallsession, ImsReasonInfo imsreasoninfo)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSessionListener");
            if(iimscallsession == null)
                break MISSING_BLOCK_LABEL_25;
            ibinder = iimscallsession.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(imsreasoninfo == null)
                break MISSING_BLOCK_LABEL_69;
            parcel.writeInt(1);
            imsreasoninfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iimscallsession;
            parcel.recycle();
            throw iimscallsession;
        }

        public void callSessionStarted(IImsCallSession iimscallsession, ImsCallProfile imscallprofile)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSessionListener");
            if(iimscallsession == null)
                break MISSING_BLOCK_LABEL_25;
            ibinder = iimscallsession.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(imscallprofile == null)
                break MISSING_BLOCK_LABEL_69;
            parcel.writeInt(1);
            imscallprofile.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iimscallsession;
            parcel.recycle();
            throw iimscallsession;
        }

        public void callSessionSuppServiceReceived(IImsCallSession iimscallsession, ImsSuppServiceNotification imssuppservicenotification)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSessionListener");
            if(iimscallsession == null)
                break MISSING_BLOCK_LABEL_25;
            ibinder = iimscallsession.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(imssuppservicenotification == null)
                break MISSING_BLOCK_LABEL_70;
            parcel.writeInt(1);
            imssuppservicenotification.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(31, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iimscallsession;
            parcel.recycle();
            throw iimscallsession;
        }

        public void callSessionTerminated(IImsCallSession iimscallsession, ImsReasonInfo imsreasoninfo)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSessionListener");
            if(iimscallsession == null)
                break MISSING_BLOCK_LABEL_25;
            ibinder = iimscallsession.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(imsreasoninfo == null)
                break MISSING_BLOCK_LABEL_69;
            parcel.writeInt(1);
            imsreasoninfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iimscallsession;
            parcel.recycle();
            throw iimscallsession;
        }

        public void callSessionTtyModeReceived(IImsCallSession iimscallsession, int i)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSessionListener");
            if(iimscallsession == null)
                break MISSING_BLOCK_LABEL_25;
            ibinder = iimscallsession.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(29, parcel, null, 1);
            parcel.recycle();
            return;
            iimscallsession;
            parcel.recycle();
            throw iimscallsession;
        }

        public void callSessionUpdateFailed(IImsCallSession iimscallsession, ImsReasonInfo imsreasoninfo)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSessionListener");
            if(iimscallsession == null)
                break MISSING_BLOCK_LABEL_25;
            ibinder = iimscallsession.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(imsreasoninfo == null)
                break MISSING_BLOCK_LABEL_70;
            parcel.writeInt(1);
            imsreasoninfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(15, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iimscallsession;
            parcel.recycle();
            throw iimscallsession;
        }

        public void callSessionUpdateReceived(IImsCallSession iimscallsession, ImsCallProfile imscallprofile)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSessionListener");
            if(iimscallsession == null)
                break MISSING_BLOCK_LABEL_25;
            ibinder = iimscallsession.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(imscallprofile == null)
                break MISSING_BLOCK_LABEL_70;
            parcel.writeInt(1);
            imscallprofile.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(16, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iimscallsession;
            parcel.recycle();
            throw iimscallsession;
        }

        public void callSessionUpdated(IImsCallSession iimscallsession, ImsCallProfile imscallprofile)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSessionListener");
            if(iimscallsession == null)
                break MISSING_BLOCK_LABEL_25;
            ibinder = iimscallsession.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(imscallprofile == null)
                break MISSING_BLOCK_LABEL_70;
            parcel.writeInt(1);
            imscallprofile.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(14, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iimscallsession;
            parcel.recycle();
            throw iimscallsession;
        }

        public void callSessionUssdMessageReceived(IImsCallSession iimscallsession, int i, String s)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsCallSessionListener");
            if(iimscallsession == null)
                break MISSING_BLOCK_LABEL_27;
            ibinder = iimscallsession.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(25, parcel, null, 1);
            parcel.recycle();
            return;
            iimscallsession;
            parcel.recycle();
            throw iimscallsession;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.ims.internal.IImsCallSessionListener";
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void callSessionConferenceExtendFailed(IImsCallSession iimscallsession, ImsReasonInfo imsreasoninfo)
        throws RemoteException;

    public abstract void callSessionConferenceExtendReceived(IImsCallSession iimscallsession, IImsCallSession iimscallsession1, ImsCallProfile imscallprofile)
        throws RemoteException;

    public abstract void callSessionConferenceExtended(IImsCallSession iimscallsession, IImsCallSession iimscallsession1, ImsCallProfile imscallprofile)
        throws RemoteException;

    public abstract void callSessionConferenceStateUpdated(IImsCallSession iimscallsession, ImsConferenceState imsconferencestate)
        throws RemoteException;

    public abstract void callSessionHandover(IImsCallSession iimscallsession, int i, int j, ImsReasonInfo imsreasoninfo)
        throws RemoteException;

    public abstract void callSessionHandoverFailed(IImsCallSession iimscallsession, int i, int j, ImsReasonInfo imsreasoninfo)
        throws RemoteException;

    public abstract void callSessionHeld(IImsCallSession iimscallsession, ImsCallProfile imscallprofile)
        throws RemoteException;

    public abstract void callSessionHoldFailed(IImsCallSession iimscallsession, ImsReasonInfo imsreasoninfo)
        throws RemoteException;

    public abstract void callSessionHoldReceived(IImsCallSession iimscallsession, ImsCallProfile imscallprofile)
        throws RemoteException;

    public abstract void callSessionInviteParticipantsRequestDelivered(IImsCallSession iimscallsession)
        throws RemoteException;

    public abstract void callSessionInviteParticipantsRequestFailed(IImsCallSession iimscallsession, ImsReasonInfo imsreasoninfo)
        throws RemoteException;

    public abstract void callSessionMayHandover(IImsCallSession iimscallsession, int i, int j)
        throws RemoteException;

    public abstract void callSessionMergeComplete(IImsCallSession iimscallsession)
        throws RemoteException;

    public abstract void callSessionMergeFailed(IImsCallSession iimscallsession, ImsReasonInfo imsreasoninfo)
        throws RemoteException;

    public abstract void callSessionMergeStarted(IImsCallSession iimscallsession, IImsCallSession iimscallsession1, ImsCallProfile imscallprofile)
        throws RemoteException;

    public abstract void callSessionMultipartyStateChanged(IImsCallSession iimscallsession, boolean flag)
        throws RemoteException;

    public abstract void callSessionProgressing(IImsCallSession iimscallsession, ImsStreamMediaProfile imsstreammediaprofile)
        throws RemoteException;

    public abstract void callSessionRemoveParticipantsRequestDelivered(IImsCallSession iimscallsession)
        throws RemoteException;

    public abstract void callSessionRemoveParticipantsRequestFailed(IImsCallSession iimscallsession, ImsReasonInfo imsreasoninfo)
        throws RemoteException;

    public abstract void callSessionResumeFailed(IImsCallSession iimscallsession, ImsReasonInfo imsreasoninfo)
        throws RemoteException;

    public abstract void callSessionResumeReceived(IImsCallSession iimscallsession, ImsCallProfile imscallprofile)
        throws RemoteException;

    public abstract void callSessionResumed(IImsCallSession iimscallsession, ImsCallProfile imscallprofile)
        throws RemoteException;

    public abstract void callSessionRttMessageReceived(String s)
        throws RemoteException;

    public abstract void callSessionRttModifyRequestReceived(IImsCallSession iimscallsession, ImsCallProfile imscallprofile)
        throws RemoteException;

    public abstract void callSessionRttModifyResponseReceived(int i)
        throws RemoteException;

    public abstract void callSessionStartFailed(IImsCallSession iimscallsession, ImsReasonInfo imsreasoninfo)
        throws RemoteException;

    public abstract void callSessionStarted(IImsCallSession iimscallsession, ImsCallProfile imscallprofile)
        throws RemoteException;

    public abstract void callSessionSuppServiceReceived(IImsCallSession iimscallsession, ImsSuppServiceNotification imssuppservicenotification)
        throws RemoteException;

    public abstract void callSessionTerminated(IImsCallSession iimscallsession, ImsReasonInfo imsreasoninfo)
        throws RemoteException;

    public abstract void callSessionTtyModeReceived(IImsCallSession iimscallsession, int i)
        throws RemoteException;

    public abstract void callSessionUpdateFailed(IImsCallSession iimscallsession, ImsReasonInfo imsreasoninfo)
        throws RemoteException;

    public abstract void callSessionUpdateReceived(IImsCallSession iimscallsession, ImsCallProfile imscallprofile)
        throws RemoteException;

    public abstract void callSessionUpdated(IImsCallSession iimscallsession, ImsCallProfile imscallprofile)
        throws RemoteException;

    public abstract void callSessionUssdMessageReceived(IImsCallSession iimscallsession, int i, String s)
        throws RemoteException;
}
