// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telecom;

import android.os.*;
import android.telecom.CallAudioState;
import android.telecom.ParcelableCall;

// Referenced classes of package com.android.internal.telecom:
//            IInCallAdapter

public interface IInCallService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IInCallService
    {

        public static IInCallService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.telecom.IInCallService");
            if(iinterface != null && (iinterface instanceof IInCallService))
                return (IInCallService)iinterface;
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
                parcel1.writeString("com.android.internal.telecom.IInCallService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.telecom.IInCallService");
                setInCallAdapter(IInCallAdapter.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.telecom.IInCallService");
                if(parcel.readInt() != 0)
                    parcel = (ParcelableCall)ParcelableCall.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                addCall(parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.internal.telecom.IInCallService");
                if(parcel.readInt() != 0)
                    parcel = (ParcelableCall)ParcelableCall.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                updateCall(parcel);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.internal.telecom.IInCallService");
                setPostDial(parcel.readString(), parcel.readString());
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.android.internal.telecom.IInCallService");
                setPostDialWait(parcel.readString(), parcel.readString());
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.android.internal.telecom.IInCallService");
                if(parcel.readInt() != 0)
                    parcel = (CallAudioState)CallAudioState.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onCallAudioStateChanged(parcel);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.android.internal.telecom.IInCallService");
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                bringToForeground(flag);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.android.internal.telecom.IInCallService");
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                onCanAddCallChanged(flag1);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.android.internal.telecom.IInCallService");
                silenceRinger();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("com.android.internal.telecom.IInCallService");
                String s = parcel.readString();
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onConnectionEvent(s, parcel1, parcel);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("com.android.internal.telecom.IInCallService");
                onRttUpgradeRequest(parcel.readString(), parcel.readInt());
                return true;

            case 12: // '\f'
                parcel.enforceInterface("com.android.internal.telecom.IInCallService");
                onRttInitiationFailure(parcel.readString(), parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.internal.telecom.IInCallService";
        static final int TRANSACTION_addCall = 2;
        static final int TRANSACTION_bringToForeground = 7;
        static final int TRANSACTION_onCallAudioStateChanged = 6;
        static final int TRANSACTION_onCanAddCallChanged = 8;
        static final int TRANSACTION_onConnectionEvent = 10;
        static final int TRANSACTION_onRttInitiationFailure = 12;
        static final int TRANSACTION_onRttUpgradeRequest = 11;
        static final int TRANSACTION_setInCallAdapter = 1;
        static final int TRANSACTION_setPostDial = 4;
        static final int TRANSACTION_setPostDialWait = 5;
        static final int TRANSACTION_silenceRinger = 9;
        static final int TRANSACTION_updateCall = 3;

        public Stub()
        {
            attachInterface(this, "com.android.internal.telecom.IInCallService");
        }
    }

    private static class Stub.Proxy
        implements IInCallService
    {

        public void addCall(ParcelableCall parcelablecall)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IInCallService");
            if(parcelablecall == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            parcelablecall.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            parcelablecall;
            parcel.recycle();
            throw parcelablecall;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void bringToForeground(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IInCallService");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.telecom.IInCallService";
        }

        public void onCallAudioStateChanged(CallAudioState callaudiostate)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IInCallService");
            if(callaudiostate == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            callaudiostate.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            callaudiostate;
            parcel.recycle();
            throw callaudiostate;
        }

        public void onCanAddCallChanged(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IInCallService");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(8, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onConnectionEvent(String s, String s1, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IInCallService");
            parcel.writeString(s);
            parcel.writeString(s1);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_63;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
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

        public void onRttInitiationFailure(String s, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IInCallService");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(12, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onRttUpgradeRequest(String s, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IInCallService");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(11, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void setInCallAdapter(IInCallAdapter iincalladapter)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IInCallService");
            if(iincalladapter == null)
                break MISSING_BLOCK_LABEL_23;
            ibinder = iincalladapter.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            iincalladapter;
            parcel.recycle();
            throw iincalladapter;
        }

        public void setPostDial(String s, String s1)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IInCallService");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void setPostDialWait(String s, String s1)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IInCallService");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void silenceRinger()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IInCallService");
            mRemote.transact(9, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void updateCall(ParcelableCall parcelablecall)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.IInCallService");
            if(parcelablecall == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            parcelablecall.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            parcelablecall;
            parcel.recycle();
            throw parcelablecall;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void addCall(ParcelableCall parcelablecall)
        throws RemoteException;

    public abstract void bringToForeground(boolean flag)
        throws RemoteException;

    public abstract void onCallAudioStateChanged(CallAudioState callaudiostate)
        throws RemoteException;

    public abstract void onCanAddCallChanged(boolean flag)
        throws RemoteException;

    public abstract void onConnectionEvent(String s, String s1, Bundle bundle)
        throws RemoteException;

    public abstract void onRttInitiationFailure(String s, int i)
        throws RemoteException;

    public abstract void onRttUpgradeRequest(String s, int i)
        throws RemoteException;

    public abstract void setInCallAdapter(IInCallAdapter iincalladapter)
        throws RemoteException;

    public abstract void setPostDial(String s, String s1)
        throws RemoteException;

    public abstract void setPostDialWait(String s, String s1)
        throws RemoteException;

    public abstract void silenceRinger()
        throws RemoteException;

    public abstract void updateCall(ParcelableCall parcelablecall)
        throws RemoteException;
}
