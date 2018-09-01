// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telephony;

import android.net.LinkProperties;
import android.net.NetworkCapabilities;
import android.os.*;
import android.telephony.*;
import java.util.List;
import miui.telephony.IMiuiTelephony;

// Referenced classes of package com.android.internal.telephony:
//            IOnSubscriptionsChangedListener, IPhoneStateListener

public interface ITelephonyRegistry
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ITelephonyRegistry
    {

        public static ITelephonyRegistry asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.telephony.ITelephonyRegistry");
            if(iinterface != null && (iinterface instanceof ITelephonyRegistry))
                return (ITelephonyRegistry)iinterface;
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
                parcel1.writeString("com.android.internal.telephony.ITelephonyRegistry");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.telephony.ITelephonyRegistry");
                addOnSubscriptionsChangedListener(parcel.readString(), IOnSubscriptionsChangedListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.telephony.ITelephonyRegistry");
                removeOnSubscriptionsChangedListener(parcel.readString(), IOnSubscriptionsChangedListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.internal.telephony.ITelephonyRegistry");
                String s = parcel.readString();
                IPhoneStateListener iphonestatelistener1 = IPhoneStateListener.Stub.asInterface(parcel.readStrongBinder());
                i = parcel.readInt();
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                listen(s, iphonestatelistener1, i, flag);
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.internal.telephony.ITelephonyRegistry");
                i = parcel.readInt();
                String s1 = parcel.readString();
                IPhoneStateListener iphonestatelistener = IPhoneStateListener.Stub.asInterface(parcel.readStrongBinder());
                j = parcel.readInt();
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                listenForSubscriber(i, s1, iphonestatelistener, j, flag1);
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.android.internal.telephony.ITelephonyRegistry");
                notifyCallState(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.android.internal.telephony.ITelephonyRegistry");
                notifyCallStateForPhoneId(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.android.internal.telephony.ITelephonyRegistry");
                j = parcel.readInt();
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (ServiceState)ServiceState.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                notifyServiceStateForPhoneId(j, i, parcel);
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.android.internal.telephony.ITelephonyRegistry");
                i = parcel.readInt();
                j = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (SignalStrength)SignalStrength.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                notifySignalStrengthForPhoneId(i, j, parcel);
                parcel1.writeNoException();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.android.internal.telephony.ITelephonyRegistry");
                i = parcel.readInt();
                j = parcel.readInt();
                boolean flag2;
                if(parcel.readInt() != 0)
                    flag2 = true;
                else
                    flag2 = false;
                notifyMessageWaitingChangedForPhoneId(i, j, flag2);
                parcel1.writeNoException();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("com.android.internal.telephony.ITelephonyRegistry");
                boolean flag3;
                if(parcel.readInt() != 0)
                    flag3 = true;
                else
                    flag3 = false;
                notifyCallForwardingChanged(flag3);
                parcel1.writeNoException();
                return true;

            case 11: // '\013'
                parcel.enforceInterface("com.android.internal.telephony.ITelephonyRegistry");
                i = parcel.readInt();
                boolean flag4;
                if(parcel.readInt() != 0)
                    flag4 = true;
                else
                    flag4 = false;
                notifyCallForwardingChangedForSubscriber(i, flag4);
                parcel1.writeNoException();
                return true;

            case 12: // '\f'
                parcel.enforceInterface("com.android.internal.telephony.ITelephonyRegistry");
                notifyDataActivity(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 13: // '\r'
                parcel.enforceInterface("com.android.internal.telephony.ITelephonyRegistry");
                notifyDataActivityForSubscriber(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 14: // '\016'
                parcel.enforceInterface("com.android.internal.telephony.ITelephonyRegistry");
                j = parcel.readInt();
                NetworkCapabilities networkcapabilities;
                LinkProperties linkproperties;
                boolean flag5;
                String s2;
                String s4;
                String s6;
                boolean flag8;
                if(parcel.readInt() != 0)
                    flag5 = true;
                else
                    flag5 = false;
                s2 = parcel.readString();
                s4 = parcel.readString();
                s6 = parcel.readString();
                if(parcel.readInt() != 0)
                    linkproperties = (LinkProperties)LinkProperties.CREATOR.createFromParcel(parcel);
                else
                    linkproperties = null;
                if(parcel.readInt() != 0)
                    networkcapabilities = (NetworkCapabilities)NetworkCapabilities.CREATOR.createFromParcel(parcel);
                else
                    networkcapabilities = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    flag8 = true;
                else
                    flag8 = false;
                notifyDataConnection(j, flag5, s2, s4, s6, linkproperties, networkcapabilities, i, flag8);
                parcel1.writeNoException();
                return true;

            case 15: // '\017'
                parcel.enforceInterface("com.android.internal.telephony.ITelephonyRegistry");
                int k = parcel.readInt();
                j = parcel.readInt();
                NetworkCapabilities networkcapabilities1;
                LinkProperties linkproperties1;
                boolean flag6;
                String s3;
                String s5;
                String s7;
                boolean flag9;
                if(parcel.readInt() != 0)
                    flag6 = true;
                else
                    flag6 = false;
                s7 = parcel.readString();
                s5 = parcel.readString();
                s3 = parcel.readString();
                if(parcel.readInt() != 0)
                    linkproperties1 = (LinkProperties)LinkProperties.CREATOR.createFromParcel(parcel);
                else
                    linkproperties1 = null;
                if(parcel.readInt() != 0)
                    networkcapabilities1 = (NetworkCapabilities)NetworkCapabilities.CREATOR.createFromParcel(parcel);
                else
                    networkcapabilities1 = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    flag9 = true;
                else
                    flag9 = false;
                notifyDataConnectionForSubscriber(k, j, flag6, s7, s5, s3, linkproperties1, networkcapabilities1, i, flag9);
                parcel1.writeNoException();
                return true;

            case 16: // '\020'
                parcel.enforceInterface("com.android.internal.telephony.ITelephonyRegistry");
                notifyDataConnectionFailed(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 17: // '\021'
                parcel.enforceInterface("com.android.internal.telephony.ITelephonyRegistry");
                notifyDataConnectionFailedForSubscriber(parcel.readInt(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 18: // '\022'
                parcel.enforceInterface("com.android.internal.telephony.ITelephonyRegistry");
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                notifyCellLocation(parcel);
                parcel1.writeNoException();
                return true;

            case 19: // '\023'
                parcel.enforceInterface("com.android.internal.telephony.ITelephonyRegistry");
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                notifyCellLocationForSubscriber(i, parcel);
                parcel1.writeNoException();
                return true;

            case 20: // '\024'
                parcel.enforceInterface("com.android.internal.telephony.ITelephonyRegistry");
                notifyOtaspChanged(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 21: // '\025'
                parcel.enforceInterface("com.android.internal.telephony.ITelephonyRegistry");
                notifyCellInfo(parcel.createTypedArrayList(CellInfo.CREATOR));
                parcel1.writeNoException();
                return true;

            case 22: // '\026'
                parcel.enforceInterface("com.android.internal.telephony.ITelephonyRegistry");
                notifyPreciseCallState(parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 23: // '\027'
                parcel.enforceInterface("com.android.internal.telephony.ITelephonyRegistry");
                notifyDisconnectCause(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 24: // '\030'
                parcel.enforceInterface("com.android.internal.telephony.ITelephonyRegistry");
                notifyPreciseDataConnectionFailed(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 25: // '\031'
                parcel.enforceInterface("com.android.internal.telephony.ITelephonyRegistry");
                notifyCellInfoForSubscriber(parcel.readInt(), parcel.createTypedArrayList(CellInfo.CREATOR));
                parcel1.writeNoException();
                return true;

            case 26: // '\032'
                parcel.enforceInterface("com.android.internal.telephony.ITelephonyRegistry");
                if(parcel.readInt() != 0)
                    parcel = (VoLteServiceState)VoLteServiceState.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                notifyVoLteServiceStateChanged(parcel);
                parcel1.writeNoException();
                return true;

            case 27: // '\033'
                parcel.enforceInterface("com.android.internal.telephony.ITelephonyRegistry");
                notifySimActivationStateChangedForPhoneId(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 28: // '\034'
                parcel.enforceInterface("com.android.internal.telephony.ITelephonyRegistry");
                notifyOemHookRawEventForSubscriber(parcel.readInt(), parcel.createByteArray());
                parcel1.writeNoException();
                return true;

            case 29: // '\035'
                parcel.enforceInterface("com.android.internal.telephony.ITelephonyRegistry");
                notifySubscriptionInfoChanged();
                parcel1.writeNoException();
                return true;

            case 30: // '\036'
                parcel.enforceInterface("com.android.internal.telephony.ITelephonyRegistry");
                boolean flag7;
                if(parcel.readInt() != 0)
                    flag7 = true;
                else
                    flag7 = false;
                notifyCarrierNetworkChange(flag7);
                parcel1.writeNoException();
                return true;

            case 31: // '\037'
                parcel.enforceInterface("com.android.internal.telephony.ITelephonyRegistry");
                setMiuiTelephony(miui.telephony.IMiuiTelephony.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 32: // ' '
                parcel.enforceInterface("com.android.internal.telephony.ITelephonyRegistry");
                parcel = getMiuiTelephony();
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

        private static final String DESCRIPTOR = "com.android.internal.telephony.ITelephonyRegistry";
        static final int TRANSACTION_addOnSubscriptionsChangedListener = 1;
        static final int TRANSACTION_getMiuiTelephony = 32;
        static final int TRANSACTION_listen = 3;
        static final int TRANSACTION_listenForSubscriber = 4;
        static final int TRANSACTION_notifyCallForwardingChanged = 10;
        static final int TRANSACTION_notifyCallForwardingChangedForSubscriber = 11;
        static final int TRANSACTION_notifyCallState = 5;
        static final int TRANSACTION_notifyCallStateForPhoneId = 6;
        static final int TRANSACTION_notifyCarrierNetworkChange = 30;
        static final int TRANSACTION_notifyCellInfo = 21;
        static final int TRANSACTION_notifyCellInfoForSubscriber = 25;
        static final int TRANSACTION_notifyCellLocation = 18;
        static final int TRANSACTION_notifyCellLocationForSubscriber = 19;
        static final int TRANSACTION_notifyDataActivity = 12;
        static final int TRANSACTION_notifyDataActivityForSubscriber = 13;
        static final int TRANSACTION_notifyDataConnection = 14;
        static final int TRANSACTION_notifyDataConnectionFailed = 16;
        static final int TRANSACTION_notifyDataConnectionFailedForSubscriber = 17;
        static final int TRANSACTION_notifyDataConnectionForSubscriber = 15;
        static final int TRANSACTION_notifyDisconnectCause = 23;
        static final int TRANSACTION_notifyMessageWaitingChangedForPhoneId = 9;
        static final int TRANSACTION_notifyOemHookRawEventForSubscriber = 28;
        static final int TRANSACTION_notifyOtaspChanged = 20;
        static final int TRANSACTION_notifyPreciseCallState = 22;
        static final int TRANSACTION_notifyPreciseDataConnectionFailed = 24;
        static final int TRANSACTION_notifyServiceStateForPhoneId = 7;
        static final int TRANSACTION_notifySignalStrengthForPhoneId = 8;
        static final int TRANSACTION_notifySimActivationStateChangedForPhoneId = 27;
        static final int TRANSACTION_notifySubscriptionInfoChanged = 29;
        static final int TRANSACTION_notifyVoLteServiceStateChanged = 26;
        static final int TRANSACTION_removeOnSubscriptionsChangedListener = 2;
        static final int TRANSACTION_setMiuiTelephony = 31;

        public Stub()
        {
            attachInterface(this, "com.android.internal.telephony.ITelephonyRegistry");
        }
    }

    private static class Stub.Proxy
        implements ITelephonyRegistry
    {

        public void addOnSubscriptionsChangedListener(String s, IOnSubscriptionsChangedListener ionsubscriptionschangedlistener)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephonyRegistry");
            parcel.writeString(s);
            s = obj;
            if(ionsubscriptionschangedlistener == null)
                break MISSING_BLOCK_LABEL_38;
            s = ionsubscriptionschangedlistener.asBinder();
            parcel.writeStrongBinder(s);
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

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.telephony.ITelephonyRegistry";
        }

        public IMiuiTelephony getMiuiTelephony()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            IMiuiTelephony imiuitelephony;
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephonyRegistry");
            mRemote.transact(32, parcel, parcel1, 0);
            parcel1.readException();
            imiuitelephony = miui.telephony.IMiuiTelephony.Stub.asInterface(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return imiuitelephony;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void listen(String s, IPhoneStateListener iphonestatelistener, int i, boolean flag)
            throws RemoteException
        {
            Object obj;
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephonyRegistry");
            parcel.writeString(s);
            s = obj;
            if(iphonestatelistener == null)
                break MISSING_BLOCK_LABEL_43;
            s = iphonestatelistener.asBinder();
            parcel.writeStrongBinder(s);
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void listenForSubscriber(int i, String s, IPhoneStateListener iphonestatelistener, int j, boolean flag)
            throws RemoteException
        {
            Object obj;
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephonyRegistry");
            parcel.writeInt(i);
            parcel.writeString(s);
            s = obj;
            if(iphonestatelistener == null)
                break MISSING_BLOCK_LABEL_49;
            s = iphonestatelistener.asBinder();
            parcel.writeStrongBinder(s);
            parcel.writeInt(j);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void notifyCallForwardingChanged(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephonyRegistry");
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

        public void notifyCallForwardingChangedForSubscriber(int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephonyRegistry");
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(11, parcel, parcel1, 0);
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

        public void notifyCallState(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephonyRegistry");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void notifyCallStateForPhoneId(int i, int j, int k, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephonyRegistry");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeString(s);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void notifyCarrierNetworkChange(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephonyRegistry");
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

        public void notifyCellInfo(List list)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephonyRegistry");
            parcel.writeTypedList(list);
            mRemote.transact(21, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            list;
            parcel1.recycle();
            parcel.recycle();
            throw list;
        }

        public void notifyCellInfoForSubscriber(int i, List list)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephonyRegistry");
            parcel.writeInt(i);
            parcel.writeTypedList(list);
            mRemote.transact(25, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            list;
            parcel1.recycle();
            parcel.recycle();
            throw list;
        }

        public void notifyCellLocation(Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephonyRegistry");
            if(bundle == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(18, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            bundle;
            parcel1.recycle();
            parcel.recycle();
            throw bundle;
        }

        public void notifyCellLocationForSubscriber(int i, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephonyRegistry");
            parcel.writeInt(i);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(19, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            bundle;
            parcel1.recycle();
            parcel.recycle();
            throw bundle;
        }

        public void notifyDataActivity(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephonyRegistry");
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

        public void notifyDataActivityForSubscriber(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephonyRegistry");
            parcel.writeInt(i);
            parcel.writeInt(j);
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

        public void notifyDataConnection(int i, boolean flag, String s, String s1, String s2, LinkProperties linkproperties, NetworkCapabilities networkcapabilities, 
                int j, boolean flag1)
            throws RemoteException
        {
            boolean flag2;
            Parcel parcel;
            Parcel parcel1;
            flag2 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephonyRegistry");
            parcel.writeInt(i);
            if(flag)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            if(linkproperties == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            linkproperties.writeToParcel(parcel, 0);
_L3:
            if(networkcapabilities == null)
                break MISSING_BLOCK_LABEL_177;
            parcel.writeInt(1);
            networkcapabilities.writeToParcel(parcel, 0);
_L4:
            parcel.writeInt(j);
            if(flag1)
                i = ((flag2) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(14, parcel, parcel1, 0);
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

        public void notifyDataConnectionFailed(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephonyRegistry");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(16, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void notifyDataConnectionFailedForSubscriber(int i, String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephonyRegistry");
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(17, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void notifyDataConnectionForSubscriber(int i, int j, boolean flag, String s, String s1, String s2, LinkProperties linkproperties, 
                NetworkCapabilities networkcapabilities, int k, boolean flag1)
            throws RemoteException
        {
            boolean flag2;
            Parcel parcel;
            Parcel parcel1;
            flag2 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephonyRegistry");
            parcel.writeInt(i);
            parcel.writeInt(j);
            if(flag)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            if(linkproperties == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            linkproperties.writeToParcel(parcel, 0);
_L3:
            if(networkcapabilities == null)
                break MISSING_BLOCK_LABEL_186;
            parcel.writeInt(1);
            networkcapabilities.writeToParcel(parcel, 0);
_L4:
            parcel.writeInt(k);
            if(flag1)
                i = ((flag2) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(15, parcel, parcel1, 0);
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

        public void notifyDisconnectCause(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephonyRegistry");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(23, parcel, parcel1, 0);
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

        public void notifyMessageWaitingChangedForPhoneId(int i, int j, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephonyRegistry");
            parcel.writeInt(i);
            parcel.writeInt(j);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
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

        public void notifyOemHookRawEventForSubscriber(int i, byte abyte0[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephonyRegistry");
            parcel.writeInt(i);
            parcel.writeByteArray(abyte0);
            mRemote.transact(28, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            abyte0;
            parcel1.recycle();
            parcel.recycle();
            throw abyte0;
        }

        public void notifyOtaspChanged(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephonyRegistry");
            parcel.writeInt(i);
            mRemote.transact(20, parcel, parcel1, 0);
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

        public void notifyPreciseCallState(int i, int j, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephonyRegistry");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(22, parcel, parcel1, 0);
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

        public void notifyPreciseDataConnectionFailed(String s, String s1, String s2, String s3)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephonyRegistry");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeString(s3);
            mRemote.transact(24, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void notifyServiceStateForPhoneId(int i, int j, ServiceState servicestate)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephonyRegistry");
            parcel.writeInt(i);
            parcel.writeInt(j);
            if(servicestate == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            servicestate.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            servicestate;
            parcel1.recycle();
            parcel.recycle();
            throw servicestate;
        }

        public void notifySignalStrengthForPhoneId(int i, int j, SignalStrength signalstrength)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephonyRegistry");
            parcel.writeInt(i);
            parcel.writeInt(j);
            if(signalstrength == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            signalstrength.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            signalstrength;
            parcel1.recycle();
            parcel.recycle();
            throw signalstrength;
        }

        public void notifySimActivationStateChangedForPhoneId(int i, int j, int k, int l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephonyRegistry");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeInt(l);
            mRemote.transact(27, parcel, parcel1, 0);
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

        public void notifySubscriptionInfoChanged()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephonyRegistry");
            mRemote.transact(29, parcel, parcel1, 0);
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

        public void notifyVoLteServiceStateChanged(VoLteServiceState volteservicestate)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephonyRegistry");
            if(volteservicestate == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            volteservicestate.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(26, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            volteservicestate;
            parcel1.recycle();
            parcel.recycle();
            throw volteservicestate;
        }

        public void removeOnSubscriptionsChangedListener(String s, IOnSubscriptionsChangedListener ionsubscriptionschangedlistener)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephonyRegistry");
            parcel.writeString(s);
            s = obj;
            if(ionsubscriptionschangedlistener == null)
                break MISSING_BLOCK_LABEL_38;
            s = ionsubscriptionschangedlistener.asBinder();
            parcel.writeStrongBinder(s);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setMiuiTelephony(IMiuiTelephony imiuitelephony)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephonyRegistry");
            if(imiuitelephony == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = imiuitelephony.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(31, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            imiuitelephony;
            parcel1.recycle();
            parcel.recycle();
            throw imiuitelephony;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void addOnSubscriptionsChangedListener(String s, IOnSubscriptionsChangedListener ionsubscriptionschangedlistener)
        throws RemoteException;

    public abstract IMiuiTelephony getMiuiTelephony()
        throws RemoteException;

    public abstract void listen(String s, IPhoneStateListener iphonestatelistener, int i, boolean flag)
        throws RemoteException;

    public abstract void listenForSubscriber(int i, String s, IPhoneStateListener iphonestatelistener, int j, boolean flag)
        throws RemoteException;

    public abstract void notifyCallForwardingChanged(boolean flag)
        throws RemoteException;

    public abstract void notifyCallForwardingChangedForSubscriber(int i, boolean flag)
        throws RemoteException;

    public abstract void notifyCallState(int i, String s)
        throws RemoteException;

    public abstract void notifyCallStateForPhoneId(int i, int j, int k, String s)
        throws RemoteException;

    public abstract void notifyCarrierNetworkChange(boolean flag)
        throws RemoteException;

    public abstract void notifyCellInfo(List list)
        throws RemoteException;

    public abstract void notifyCellInfoForSubscriber(int i, List list)
        throws RemoteException;

    public abstract void notifyCellLocation(Bundle bundle)
        throws RemoteException;

    public abstract void notifyCellLocationForSubscriber(int i, Bundle bundle)
        throws RemoteException;

    public abstract void notifyDataActivity(int i)
        throws RemoteException;

    public abstract void notifyDataActivityForSubscriber(int i, int j)
        throws RemoteException;

    public abstract void notifyDataConnection(int i, boolean flag, String s, String s1, String s2, LinkProperties linkproperties, NetworkCapabilities networkcapabilities, 
            int j, boolean flag1)
        throws RemoteException;

    public abstract void notifyDataConnectionFailed(String s, String s1)
        throws RemoteException;

    public abstract void notifyDataConnectionFailedForSubscriber(int i, String s, String s1)
        throws RemoteException;

    public abstract void notifyDataConnectionForSubscriber(int i, int j, boolean flag, String s, String s1, String s2, LinkProperties linkproperties, 
            NetworkCapabilities networkcapabilities, int k, boolean flag1)
        throws RemoteException;

    public abstract void notifyDisconnectCause(int i, int j)
        throws RemoteException;

    public abstract void notifyMessageWaitingChangedForPhoneId(int i, int j, boolean flag)
        throws RemoteException;

    public abstract void notifyOemHookRawEventForSubscriber(int i, byte abyte0[])
        throws RemoteException;

    public abstract void notifyOtaspChanged(int i)
        throws RemoteException;

    public abstract void notifyPreciseCallState(int i, int j, int k)
        throws RemoteException;

    public abstract void notifyPreciseDataConnectionFailed(String s, String s1, String s2, String s3)
        throws RemoteException;

    public abstract void notifyServiceStateForPhoneId(int i, int j, ServiceState servicestate)
        throws RemoteException;

    public abstract void notifySignalStrengthForPhoneId(int i, int j, SignalStrength signalstrength)
        throws RemoteException;

    public abstract void notifySimActivationStateChangedForPhoneId(int i, int j, int k, int l)
        throws RemoteException;

    public abstract void notifySubscriptionInfoChanged()
        throws RemoteException;

    public abstract void notifyVoLteServiceStateChanged(VoLteServiceState volteservicestate)
        throws RemoteException;

    public abstract void removeOnSubscriptionsChangedListener(String s, IOnSubscriptionsChangedListener ionsubscriptionschangedlistener)
        throws RemoteException;

    public abstract void setMiuiTelephony(IMiuiTelephony imiuitelephony)
        throws RemoteException;
}
