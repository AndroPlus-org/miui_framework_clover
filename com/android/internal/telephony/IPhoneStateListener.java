// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telephony;

import android.os.*;
import android.telephony.*;
import java.util.List;

public interface IPhoneStateListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IPhoneStateListener
    {

        public static IPhoneStateListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.telephony.IPhoneStateListener");
            if(iinterface != null && (iinterface instanceof IPhoneStateListener))
                return (IPhoneStateListener)iinterface;
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
                parcel1.writeString("com.android.internal.telephony.IPhoneStateListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.telephony.IPhoneStateListener");
                if(parcel.readInt() != 0)
                    parcel = (ServiceState)ServiceState.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onServiceStateChanged(parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.telephony.IPhoneStateListener");
                onSignalStrengthChanged(parcel.readInt());
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.internal.telephony.IPhoneStateListener");
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                onMessageWaitingIndicatorChanged(flag);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.internal.telephony.IPhoneStateListener");
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                onCallForwardingIndicatorChanged(flag1);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.android.internal.telephony.IPhoneStateListener");
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onCellLocationChanged(parcel);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.android.internal.telephony.IPhoneStateListener");
                onCallStateChanged(parcel.readInt(), parcel.readString());
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.android.internal.telephony.IPhoneStateListener");
                onDataConnectionStateChanged(parcel.readInt(), parcel.readInt());
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.android.internal.telephony.IPhoneStateListener");
                onDataActivity(parcel.readInt());
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.android.internal.telephony.IPhoneStateListener");
                if(parcel.readInt() != 0)
                    parcel = (SignalStrength)SignalStrength.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onSignalStrengthsChanged(parcel);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("com.android.internal.telephony.IPhoneStateListener");
                onOtaspChanged(parcel.readInt());
                return true;

            case 11: // '\013'
                parcel.enforceInterface("com.android.internal.telephony.IPhoneStateListener");
                onCellInfoChanged(parcel.createTypedArrayList(CellInfo.CREATOR));
                return true;

            case 12: // '\f'
                parcel.enforceInterface("com.android.internal.telephony.IPhoneStateListener");
                if(parcel.readInt() != 0)
                    parcel = (PreciseCallState)PreciseCallState.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onPreciseCallStateChanged(parcel);
                return true;

            case 13: // '\r'
                parcel.enforceInterface("com.android.internal.telephony.IPhoneStateListener");
                if(parcel.readInt() != 0)
                    parcel = (PreciseDataConnectionState)PreciseDataConnectionState.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onPreciseDataConnectionStateChanged(parcel);
                return true;

            case 14: // '\016'
                parcel.enforceInterface("com.android.internal.telephony.IPhoneStateListener");
                if(parcel.readInt() != 0)
                    parcel = (DataConnectionRealTimeInfo)DataConnectionRealTimeInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onDataConnectionRealTimeInfoChanged(parcel);
                return true;

            case 15: // '\017'
                parcel.enforceInterface("com.android.internal.telephony.IPhoneStateListener");
                if(parcel.readInt() != 0)
                    parcel = (VoLteServiceState)VoLteServiceState.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onVoLteServiceStateChanged(parcel);
                return true;

            case 16: // '\020'
                parcel.enforceInterface("com.android.internal.telephony.IPhoneStateListener");
                onVoiceActivationStateChanged(parcel.readInt());
                return true;

            case 17: // '\021'
                parcel.enforceInterface("com.android.internal.telephony.IPhoneStateListener");
                onDataActivationStateChanged(parcel.readInt());
                return true;

            case 18: // '\022'
                parcel.enforceInterface("com.android.internal.telephony.IPhoneStateListener");
                onOemHookRawEvent(parcel.createByteArray());
                return true;

            case 19: // '\023'
                parcel.enforceInterface("com.android.internal.telephony.IPhoneStateListener");
                break;
            }
            boolean flag2;
            if(parcel.readInt() != 0)
                flag2 = true;
            else
                flag2 = false;
            onCarrierNetworkChange(flag2);
            return true;
        }

        private static final String DESCRIPTOR = "com.android.internal.telephony.IPhoneStateListener";
        static final int TRANSACTION_onCallForwardingIndicatorChanged = 4;
        static final int TRANSACTION_onCallStateChanged = 6;
        static final int TRANSACTION_onCarrierNetworkChange = 19;
        static final int TRANSACTION_onCellInfoChanged = 11;
        static final int TRANSACTION_onCellLocationChanged = 5;
        static final int TRANSACTION_onDataActivationStateChanged = 17;
        static final int TRANSACTION_onDataActivity = 8;
        static final int TRANSACTION_onDataConnectionRealTimeInfoChanged = 14;
        static final int TRANSACTION_onDataConnectionStateChanged = 7;
        static final int TRANSACTION_onMessageWaitingIndicatorChanged = 3;
        static final int TRANSACTION_onOemHookRawEvent = 18;
        static final int TRANSACTION_onOtaspChanged = 10;
        static final int TRANSACTION_onPreciseCallStateChanged = 12;
        static final int TRANSACTION_onPreciseDataConnectionStateChanged = 13;
        static final int TRANSACTION_onServiceStateChanged = 1;
        static final int TRANSACTION_onSignalStrengthChanged = 2;
        static final int TRANSACTION_onSignalStrengthsChanged = 9;
        static final int TRANSACTION_onVoLteServiceStateChanged = 15;
        static final int TRANSACTION_onVoiceActivationStateChanged = 16;

        public Stub()
        {
            attachInterface(this, "com.android.internal.telephony.IPhoneStateListener");
        }
    }

    private static class Stub.Proxy
        implements IPhoneStateListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.telephony.IPhoneStateListener";
        }

        public void onCallForwardingIndicatorChanged(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneStateListener");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onCallStateChanged(int i, String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneStateListener");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onCarrierNetworkChange(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneStateListener");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(19, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onCellInfoChanged(List list)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneStateListener");
            parcel.writeTypedList(list);
            mRemote.transact(11, parcel, null, 1);
            parcel.recycle();
            return;
            list;
            parcel.recycle();
            throw list;
        }

        public void onCellLocationChanged(Bundle bundle)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneStateListener");
            if(bundle == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            bundle;
            parcel.recycle();
            throw bundle;
        }

        public void onDataActivationStateChanged(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneStateListener");
            parcel.writeInt(i);
            mRemote.transact(17, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onDataActivity(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneStateListener");
            parcel.writeInt(i);
            mRemote.transact(8, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onDataConnectionRealTimeInfoChanged(DataConnectionRealTimeInfo dataconnectionrealtimeinfo)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneStateListener");
            if(dataconnectionrealtimeinfo == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            dataconnectionrealtimeinfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(14, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            dataconnectionrealtimeinfo;
            parcel.recycle();
            throw dataconnectionrealtimeinfo;
        }

        public void onDataConnectionStateChanged(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneStateListener");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onMessageWaitingIndicatorChanged(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneStateListener");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onOemHookRawEvent(byte abyte0[])
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneStateListener");
            parcel.writeByteArray(abyte0);
            mRemote.transact(18, parcel, null, 1);
            parcel.recycle();
            return;
            abyte0;
            parcel.recycle();
            throw abyte0;
        }

        public void onOtaspChanged(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneStateListener");
            parcel.writeInt(i);
            mRemote.transact(10, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onPreciseCallStateChanged(PreciseCallState precisecallstate)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneStateListener");
            if(precisecallstate == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            precisecallstate.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(12, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            precisecallstate;
            parcel.recycle();
            throw precisecallstate;
        }

        public void onPreciseDataConnectionStateChanged(PreciseDataConnectionState precisedataconnectionstate)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneStateListener");
            if(precisedataconnectionstate == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            precisedataconnectionstate.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(13, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            precisedataconnectionstate;
            parcel.recycle();
            throw precisedataconnectionstate;
        }

        public void onServiceStateChanged(ServiceState servicestate)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneStateListener");
            if(servicestate == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            servicestate.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            servicestate;
            parcel.recycle();
            throw servicestate;
        }

        public void onSignalStrengthChanged(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneStateListener");
            parcel.writeInt(i);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onSignalStrengthsChanged(SignalStrength signalstrength)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneStateListener");
            if(signalstrength == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            signalstrength.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(9, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            signalstrength;
            parcel.recycle();
            throw signalstrength;
        }

        public void onVoLteServiceStateChanged(VoLteServiceState volteservicestate)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneStateListener");
            if(volteservicestate == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            volteservicestate.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(15, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            volteservicestate;
            parcel.recycle();
            throw volteservicestate;
        }

        public void onVoiceActivationStateChanged(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneStateListener");
            parcel.writeInt(i);
            mRemote.transact(16, parcel, null, 1);
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


    public abstract void onCallForwardingIndicatorChanged(boolean flag)
        throws RemoteException;

    public abstract void onCallStateChanged(int i, String s)
        throws RemoteException;

    public abstract void onCarrierNetworkChange(boolean flag)
        throws RemoteException;

    public abstract void onCellInfoChanged(List list)
        throws RemoteException;

    public abstract void onCellLocationChanged(Bundle bundle)
        throws RemoteException;

    public abstract void onDataActivationStateChanged(int i)
        throws RemoteException;

    public abstract void onDataActivity(int i)
        throws RemoteException;

    public abstract void onDataConnectionRealTimeInfoChanged(DataConnectionRealTimeInfo dataconnectionrealtimeinfo)
        throws RemoteException;

    public abstract void onDataConnectionStateChanged(int i, int j)
        throws RemoteException;

    public abstract void onMessageWaitingIndicatorChanged(boolean flag)
        throws RemoteException;

    public abstract void onOemHookRawEvent(byte abyte0[])
        throws RemoteException;

    public abstract void onOtaspChanged(int i)
        throws RemoteException;

    public abstract void onPreciseCallStateChanged(PreciseCallState precisecallstate)
        throws RemoteException;

    public abstract void onPreciseDataConnectionStateChanged(PreciseDataConnectionState precisedataconnectionstate)
        throws RemoteException;

    public abstract void onServiceStateChanged(ServiceState servicestate)
        throws RemoteException;

    public abstract void onSignalStrengthChanged(int i)
        throws RemoteException;

    public abstract void onSignalStrengthsChanged(SignalStrength signalstrength)
        throws RemoteException;

    public abstract void onVoLteServiceStateChanged(VoLteServiceState volteservicestate)
        throws RemoteException;

    public abstract void onVoiceActivationStateChanged(int i)
        throws RemoteException;
}
