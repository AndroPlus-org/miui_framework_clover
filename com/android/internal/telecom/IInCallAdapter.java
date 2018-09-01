// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telecom;

import android.os.*;
import android.telecom.PhoneAccountHandle;
import java.util.List;

public interface IInCallAdapter
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IInCallAdapter
    {

        public static IInCallAdapter asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.telecom.IInCallAdapter");
            if(iinterface != null && (iinterface instanceof IInCallAdapter))
                return (IInCallAdapter)iinterface;
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
                parcel1.writeString("com.android.internal.telecom.IInCallAdapter");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.telecom.IInCallAdapter");
                answerCall(parcel.readString(), parcel.readInt());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.telecom.IInCallAdapter");
                parcel1 = parcel.readString();
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                rejectCall(parcel1, flag, parcel.readString());
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.internal.telecom.IInCallAdapter");
                disconnectCall(parcel.readString());
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.internal.telecom.IInCallAdapter");
                holdCall(parcel.readString());
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.android.internal.telecom.IInCallAdapter");
                unholdCall(parcel.readString());
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.android.internal.telecom.IInCallAdapter");
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                mute(flag1);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.android.internal.telecom.IInCallAdapter");
                setAudioRoute(parcel.readInt());
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.android.internal.telecom.IInCallAdapter");
                playDtmfTone(parcel.readString(), (char)parcel.readInt());
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.android.internal.telecom.IInCallAdapter");
                stopDtmfTone(parcel.readString());
                return true;

            case 10: // '\n'
                parcel.enforceInterface("com.android.internal.telecom.IInCallAdapter");
                parcel1 = parcel.readString();
                boolean flag2;
                if(parcel.readInt() != 0)
                    flag2 = true;
                else
                    flag2 = false;
                postDialContinue(parcel1, flag2);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("com.android.internal.telecom.IInCallAdapter");
                String s = parcel.readString();
                boolean flag3;
                if(parcel.readInt() != 0)
                    parcel1 = (PhoneAccountHandle)PhoneAccountHandle.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    flag3 = true;
                else
                    flag3 = false;
                phoneAccountSelected(s, parcel1, flag3);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("com.android.internal.telecom.IInCallAdapter");
                conference(parcel.readString(), parcel.readString());
                return true;

            case 13: // '\r'
                parcel.enforceInterface("com.android.internal.telecom.IInCallAdapter");
                splitFromConference(parcel.readString());
                return true;

            case 14: // '\016'
                parcel.enforceInterface("com.android.internal.telecom.IInCallAdapter");
                mergeConference(parcel.readString());
                return true;

            case 15: // '\017'
                parcel.enforceInterface("com.android.internal.telecom.IInCallAdapter");
                swapConference(parcel.readString());
                return true;

            case 16: // '\020'
                parcel.enforceInterface("com.android.internal.telecom.IInCallAdapter");
                turnOnProximitySensor();
                return true;

            case 17: // '\021'
                parcel.enforceInterface("com.android.internal.telecom.IInCallAdapter");
                boolean flag4;
                if(parcel.readInt() != 0)
                    flag4 = true;
                else
                    flag4 = false;
                turnOffProximitySensor(flag4);
                return true;

            case 18: // '\022'
                parcel.enforceInterface("com.android.internal.telecom.IInCallAdapter");
                pullExternalCall(parcel.readString());
                return true;

            case 19: // '\023'
                parcel.enforceInterface("com.android.internal.telecom.IInCallAdapter");
                parcel1 = parcel.readString();
                String s1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                sendCallEvent(parcel1, s1, parcel);
                return true;

            case 20: // '\024'
                parcel.enforceInterface("com.android.internal.telecom.IInCallAdapter");
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                putExtras(parcel1, parcel);
                return true;

            case 21: // '\025'
                parcel.enforceInterface("com.android.internal.telecom.IInCallAdapter");
                removeExtras(parcel.readString(), parcel.createStringArrayList());
                return true;

            case 22: // '\026'
                parcel.enforceInterface("com.android.internal.telecom.IInCallAdapter");
                sendRttRequest(parcel.readString());
                return true;

            case 23: // '\027'
                parcel.enforceInterface("com.android.internal.telecom.IInCallAdapter");
                parcel1 = parcel.readString();
                i = parcel.readInt();
                boolean flag5;
                if(parcel.readInt() != 0)
                    flag5 = true;
                else
                    flag5 = false;
                respondToRttRequest(parcel1, i, flag5);
                return true;

            case 24: // '\030'
                parcel.enforceInterface("com.android.internal.telecom.IInCallAdapter");
                stopRtt(parcel.readString());
                return true;

            case 25: // '\031'
                parcel.enforceInterface("com.android.internal.telecom.IInCallAdapter");
                setRttMode(parcel.readString(), parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.internal.telecom.IInCallAdapter";
        static final int TRANSACTION_answerCall = 1;
        static final int TRANSACTION_conference = 12;
        static final int TRANSACTION_disconnectCall = 3;
        static final int TRANSACTION_holdCall = 4;
        static final int TRANSACTION_mergeConference = 14;
        static final int TRANSACTION_mute = 6;
        static final int TRANSACTION_phoneAccountSelected = 11;
        static final int TRANSACTION_playDtmfTone = 8;
        static final int TRANSACTION_postDialContinue = 10;
        static final int TRANSACTION_pullExternalCall = 18;
        static final int TRANSACTION_putExtras = 20;
        static final int TRANSACTION_rejectCall = 2;
        static final int TRANSACTION_removeExtras = 21;
        static final int TRANSACTION_respondToRttRequest = 23;
        static final int TRANSACTION_sendCallEvent = 19;
        static final int TRANSACTION_sendRttRequest = 22;
        static final int TRANSACTION_setAudioRoute = 7;
        static final int TRANSACTION_setRttMode = 25;
        static final int TRANSACTION_splitFromConference = 13;
        static final int TRANSACTION_stopDtmfTone = 9;
        static final int TRANSACTION_stopRtt = 24;
        static final int TRANSACTION_swapConference = 15;
        static final int TRANSACTION_turnOffProximitySensor = 17;
        static final int TRANSACTION_turnOnProximitySensor = 16;
        static final int TRANSACTION_unholdCall = 5;

        public Stub()
        {
            attachInterface(this, "com.android.internal.telecom.IInCallAdapter");
        }
    }

    private static class Stub.Proxy
        implements IInCallAdapter
    {

        public void answerCall(String s, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IInCallAdapter");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void conference(String s, String s1)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IInCallAdapter");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(12, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void disconnectCall(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IInCallAdapter");
            parcel.writeString(s);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.telecom.IInCallAdapter";
        }

        public void holdCall(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IInCallAdapter");
            parcel.writeString(s);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void mergeConference(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IInCallAdapter");
            parcel.writeString(s);
            mRemote.transact(14, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void mute(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IInCallAdapter");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void phoneAccountSelected(String s, PhoneAccountHandle phoneaccounthandle, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IInCallAdapter");
            parcel.writeString(s);
            if(phoneaccounthandle == null)
                break MISSING_BLOCK_LABEL_71;
            parcel.writeInt(1);
            phoneaccounthandle.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(11, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void playDtmfTone(String s, char c)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IInCallAdapter");
            parcel.writeString(s);
            parcel.writeInt(c);
            mRemote.transact(8, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void postDialContinue(String s, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IInCallAdapter");
            parcel.writeString(s);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(10, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void pullExternalCall(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IInCallAdapter");
            parcel.writeString(s);
            mRemote.transact(18, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void putExtras(String s, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IInCallAdapter");
            parcel.writeString(s);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
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

        public void rejectCall(String s, boolean flag, String s1)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IInCallAdapter");
            parcel.writeString(s);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            parcel.writeString(s1);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void removeExtras(String s, List list)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IInCallAdapter");
            parcel.writeString(s);
            parcel.writeStringList(list);
            mRemote.transact(21, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void respondToRttRequest(String s, int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IInCallAdapter");
            parcel.writeString(s);
            parcel.writeInt(i);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(23, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void sendCallEvent(String s, String s1, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IInCallAdapter");
            parcel.writeString(s);
            parcel.writeString(s1);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_63;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
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

        public void sendRttRequest(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IInCallAdapter");
            parcel.writeString(s);
            mRemote.transact(22, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void setAudioRoute(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IInCallAdapter");
            parcel.writeInt(i);
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void setRttMode(String s, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IInCallAdapter");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(25, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void splitFromConference(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IInCallAdapter");
            parcel.writeString(s);
            mRemote.transact(13, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void stopDtmfTone(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IInCallAdapter");
            parcel.writeString(s);
            mRemote.transact(9, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void stopRtt(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IInCallAdapter");
            parcel.writeString(s);
            mRemote.transact(24, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void swapConference(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IInCallAdapter");
            parcel.writeString(s);
            mRemote.transact(15, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void turnOffProximitySensor(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IInCallAdapter");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(17, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void turnOnProximitySensor()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IInCallAdapter");
            mRemote.transact(16, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void unholdCall(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IInCallAdapter");
            parcel.writeString(s);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
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


    public abstract void answerCall(String s, int i)
        throws RemoteException;

    public abstract void conference(String s, String s1)
        throws RemoteException;

    public abstract void disconnectCall(String s)
        throws RemoteException;

    public abstract void holdCall(String s)
        throws RemoteException;

    public abstract void mergeConference(String s)
        throws RemoteException;

    public abstract void mute(boolean flag)
        throws RemoteException;

    public abstract void phoneAccountSelected(String s, PhoneAccountHandle phoneaccounthandle, boolean flag)
        throws RemoteException;

    public abstract void playDtmfTone(String s, char c)
        throws RemoteException;

    public abstract void postDialContinue(String s, boolean flag)
        throws RemoteException;

    public abstract void pullExternalCall(String s)
        throws RemoteException;

    public abstract void putExtras(String s, Bundle bundle)
        throws RemoteException;

    public abstract void rejectCall(String s, boolean flag, String s1)
        throws RemoteException;

    public abstract void removeExtras(String s, List list)
        throws RemoteException;

    public abstract void respondToRttRequest(String s, int i, boolean flag)
        throws RemoteException;

    public abstract void sendCallEvent(String s, String s1, Bundle bundle)
        throws RemoteException;

    public abstract void sendRttRequest(String s)
        throws RemoteException;

    public abstract void setAudioRoute(int i)
        throws RemoteException;

    public abstract void setRttMode(String s, int i)
        throws RemoteException;

    public abstract void splitFromConference(String s)
        throws RemoteException;

    public abstract void stopDtmfTone(String s)
        throws RemoteException;

    public abstract void stopRtt(String s)
        throws RemoteException;

    public abstract void swapConference(String s)
        throws RemoteException;

    public abstract void turnOffProximitySensor(boolean flag)
        throws RemoteException;

    public abstract void turnOnProximitySensor()
        throws RemoteException;

    public abstract void unholdCall(String s)
        throws RemoteException;
}
