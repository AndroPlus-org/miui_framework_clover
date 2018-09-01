// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.policy;

import android.os.*;

// Referenced classes of package com.android.internal.policy:
//            IKeyguardStateCallback, IKeyguardDismissCallback, IKeyguardDrawnCallback, IKeyguardExitCallback

public interface IKeyguardService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IKeyguardService
    {

        public static IKeyguardService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.policy.IKeyguardService");
            if(iinterface != null && (iinterface instanceof IKeyguardService))
                return (IKeyguardService)iinterface;
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
                parcel1.writeString("com.android.internal.policy.IKeyguardService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.policy.IKeyguardService");
                boolean flag;
                boolean flag4;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                if(parcel.readInt() != 0)
                    flag4 = true;
                else
                    flag4 = false;
                setOccluded(flag, flag4);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.policy.IKeyguardService");
                addStateMonitorCallback(IKeyguardStateCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.internal.policy.IKeyguardService");
                verifyUnlock(IKeyguardExitCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.internal.policy.IKeyguardService");
                dismiss(IKeyguardDismissCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.android.internal.policy.IKeyguardService");
                onDreamingStarted();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.android.internal.policy.IKeyguardService");
                onDreamingStopped();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.android.internal.policy.IKeyguardService");
                onStartedGoingToSleep(parcel.readInt());
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.android.internal.policy.IKeyguardService");
                i = parcel.readInt();
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                onFinishedGoingToSleep(i, flag1);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.android.internal.policy.IKeyguardService");
                onStartedWakingUp();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("com.android.internal.policy.IKeyguardService");
                onFinishedWakingUp();
                return true;

            case 11: // '\013'
                parcel.enforceInterface("com.android.internal.policy.IKeyguardService");
                onScreenTurningOn(IKeyguardDrawnCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 12: // '\f'
                parcel.enforceInterface("com.android.internal.policy.IKeyguardService");
                onScreenTurnedOn();
                return true;

            case 13: // '\r'
                parcel.enforceInterface("com.android.internal.policy.IKeyguardService");
                onScreenTurningOff();
                return true;

            case 14: // '\016'
                parcel.enforceInterface("com.android.internal.policy.IKeyguardService");
                onScreenTurnedOff();
                return true;

            case 15: // '\017'
                parcel.enforceInterface("com.android.internal.policy.IKeyguardService");
                boolean flag2;
                if(parcel.readInt() != 0)
                    flag2 = true;
                else
                    flag2 = false;
                setKeyguardEnabled(flag2);
                return true;

            case 16: // '\020'
                parcel.enforceInterface("com.android.internal.policy.IKeyguardService");
                onSystemReady();
                return true;

            case 17: // '\021'
                parcel.enforceInterface("com.android.internal.policy.IKeyguardService");
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                doKeyguardTimeout(parcel);
                return true;

            case 18: // '\022'
                parcel.enforceInterface("com.android.internal.policy.IKeyguardService");
                boolean flag3;
                if(parcel.readInt() != 0)
                    flag3 = true;
                else
                    flag3 = false;
                setSwitchingUser(flag3);
                return true;

            case 19: // '\023'
                parcel.enforceInterface("com.android.internal.policy.IKeyguardService");
                setCurrentUser(parcel.readInt());
                return true;

            case 20: // '\024'
                parcel.enforceInterface("com.android.internal.policy.IKeyguardService");
                onBootCompleted();
                return true;

            case 21: // '\025'
                parcel.enforceInterface("com.android.internal.policy.IKeyguardService");
                OnDoubleClickHome();
                return true;

            case 22: // '\026'
                parcel.enforceInterface("com.android.internal.policy.IKeyguardService");
                startKeyguardExitAnimation(parcel.readLong(), parcel.readLong());
                return true;

            case 23: // '\027'
                parcel.enforceInterface("com.android.internal.policy.IKeyguardService");
                onShortPowerPressedGoHome();
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.internal.policy.IKeyguardService";
        static final int TRANSACTION_OnDoubleClickHome = 21;
        static final int TRANSACTION_addStateMonitorCallback = 2;
        static final int TRANSACTION_dismiss = 4;
        static final int TRANSACTION_doKeyguardTimeout = 17;
        static final int TRANSACTION_onBootCompleted = 20;
        static final int TRANSACTION_onDreamingStarted = 5;
        static final int TRANSACTION_onDreamingStopped = 6;
        static final int TRANSACTION_onFinishedGoingToSleep = 8;
        static final int TRANSACTION_onFinishedWakingUp = 10;
        static final int TRANSACTION_onScreenTurnedOff = 14;
        static final int TRANSACTION_onScreenTurnedOn = 12;
        static final int TRANSACTION_onScreenTurningOff = 13;
        static final int TRANSACTION_onScreenTurningOn = 11;
        static final int TRANSACTION_onShortPowerPressedGoHome = 23;
        static final int TRANSACTION_onStartedGoingToSleep = 7;
        static final int TRANSACTION_onStartedWakingUp = 9;
        static final int TRANSACTION_onSystemReady = 16;
        static final int TRANSACTION_setCurrentUser = 19;
        static final int TRANSACTION_setKeyguardEnabled = 15;
        static final int TRANSACTION_setOccluded = 1;
        static final int TRANSACTION_setSwitchingUser = 18;
        static final int TRANSACTION_startKeyguardExitAnimation = 22;
        static final int TRANSACTION_verifyUnlock = 3;

        public Stub()
        {
            attachInterface(this, "com.android.internal.policy.IKeyguardService");
        }
    }

    private static class Stub.Proxy
        implements IKeyguardService
    {

        public void OnDoubleClickHome()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.policy.IKeyguardService");
            mRemote.transact(21, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void addStateMonitorCallback(IKeyguardStateCallback ikeyguardstatecallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.policy.IKeyguardService");
            if(ikeyguardstatecallback == null)
                break MISSING_BLOCK_LABEL_23;
            ibinder = ikeyguardstatecallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            ikeyguardstatecallback;
            parcel.recycle();
            throw ikeyguardstatecallback;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void dismiss(IKeyguardDismissCallback ikeyguarddismisscallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.policy.IKeyguardService");
            if(ikeyguarddismisscallback == null)
                break MISSING_BLOCK_LABEL_23;
            ibinder = ikeyguarddismisscallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            ikeyguarddismisscallback;
            parcel.recycle();
            throw ikeyguarddismisscallback;
        }

        public void doKeyguardTimeout(Bundle bundle)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.policy.IKeyguardService");
            if(bundle == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(17, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            bundle;
            parcel.recycle();
            throw bundle;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.policy.IKeyguardService";
        }

        public void onBootCompleted()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.policy.IKeyguardService");
            mRemote.transact(20, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onDreamingStarted()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.policy.IKeyguardService");
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onDreamingStopped()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.policy.IKeyguardService");
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onFinishedGoingToSleep(int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.policy.IKeyguardService");
            parcel.writeInt(i);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
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

        public void onFinishedWakingUp()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.policy.IKeyguardService");
            mRemote.transact(10, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onScreenTurnedOff()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.policy.IKeyguardService");
            mRemote.transact(14, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onScreenTurnedOn()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.policy.IKeyguardService");
            mRemote.transact(12, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onScreenTurningOff()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.policy.IKeyguardService");
            mRemote.transact(13, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onScreenTurningOn(IKeyguardDrawnCallback ikeyguarddrawncallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.policy.IKeyguardService");
            if(ikeyguarddrawncallback == null)
                break MISSING_BLOCK_LABEL_23;
            ibinder = ikeyguarddrawncallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(11, parcel, null, 1);
            parcel.recycle();
            return;
            ikeyguarddrawncallback;
            parcel.recycle();
            throw ikeyguarddrawncallback;
        }

        public void onShortPowerPressedGoHome()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.policy.IKeyguardService");
            mRemote.transact(23, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onStartedGoingToSleep(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.policy.IKeyguardService");
            parcel.writeInt(i);
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onStartedWakingUp()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.policy.IKeyguardService");
            mRemote.transact(9, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onSystemReady()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.policy.IKeyguardService");
            mRemote.transact(16, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void setCurrentUser(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.policy.IKeyguardService");
            parcel.writeInt(i);
            mRemote.transact(19, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void setKeyguardEnabled(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.policy.IKeyguardService");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(15, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void setOccluded(boolean flag, boolean flag1)
            throws RemoteException
        {
            boolean flag2;
            Parcel parcel;
            flag2 = true;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.policy.IKeyguardService");
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
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void setSwitchingUser(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.policy.IKeyguardService");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(18, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void startKeyguardExitAnimation(long l, long l1)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.policy.IKeyguardService");
            parcel.writeLong(l);
            parcel.writeLong(l1);
            mRemote.transact(22, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void verifyUnlock(IKeyguardExitCallback ikeyguardexitcallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.policy.IKeyguardService");
            if(ikeyguardexitcallback == null)
                break MISSING_BLOCK_LABEL_23;
            ibinder = ikeyguardexitcallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            ikeyguardexitcallback;
            parcel.recycle();
            throw ikeyguardexitcallback;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void OnDoubleClickHome()
        throws RemoteException;

    public abstract void addStateMonitorCallback(IKeyguardStateCallback ikeyguardstatecallback)
        throws RemoteException;

    public abstract void dismiss(IKeyguardDismissCallback ikeyguarddismisscallback)
        throws RemoteException;

    public abstract void doKeyguardTimeout(Bundle bundle)
        throws RemoteException;

    public abstract void onBootCompleted()
        throws RemoteException;

    public abstract void onDreamingStarted()
        throws RemoteException;

    public abstract void onDreamingStopped()
        throws RemoteException;

    public abstract void onFinishedGoingToSleep(int i, boolean flag)
        throws RemoteException;

    public abstract void onFinishedWakingUp()
        throws RemoteException;

    public abstract void onScreenTurnedOff()
        throws RemoteException;

    public abstract void onScreenTurnedOn()
        throws RemoteException;

    public abstract void onScreenTurningOff()
        throws RemoteException;

    public abstract void onScreenTurningOn(IKeyguardDrawnCallback ikeyguarddrawncallback)
        throws RemoteException;

    public abstract void onShortPowerPressedGoHome()
        throws RemoteException;

    public abstract void onStartedGoingToSleep(int i)
        throws RemoteException;

    public abstract void onStartedWakingUp()
        throws RemoteException;

    public abstract void onSystemReady()
        throws RemoteException;

    public abstract void setCurrentUser(int i)
        throws RemoteException;

    public abstract void setKeyguardEnabled(boolean flag)
        throws RemoteException;

    public abstract void setOccluded(boolean flag, boolean flag1)
        throws RemoteException;

    public abstract void setSwitchingUser(boolean flag)
        throws RemoteException;

    public abstract void startKeyguardExitAnimation(long l, long l1)
        throws RemoteException;

    public abstract void verifyUnlock(IKeyguardExitCallback ikeyguardexitcallback)
        throws RemoteException;
}
