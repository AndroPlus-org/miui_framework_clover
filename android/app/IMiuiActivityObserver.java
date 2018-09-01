// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.Intent;
import android.os.*;

public interface IMiuiActivityObserver
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IMiuiActivityObserver
    {

        public static IMiuiActivityObserver asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.app.IMiuiActivityObserver");
            if(iinterface != null && (iinterface instanceof IMiuiActivityObserver))
                return (IMiuiActivityObserver)iinterface;
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
                parcel1.writeString("android.app.IMiuiActivityObserver");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.app.IMiuiActivityObserver");
                if(parcel.readInt() != 0)
                    parcel = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                activityIdle(parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.app.IMiuiActivityObserver");
                if(parcel.readInt() != 0)
                    parcel = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                activityResumed(parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.app.IMiuiActivityObserver");
                if(parcel.readInt() != 0)
                    parcel = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                activityPaused(parcel);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.app.IMiuiActivityObserver");
                if(parcel.readInt() != 0)
                    parcel = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                activityStopped(parcel);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.app.IMiuiActivityObserver");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (Intent)Intent.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            activityDestroyed(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.app.IMiuiActivityObserver";
        static final int TRANSACTION_activityDestroyed = 5;
        static final int TRANSACTION_activityIdle = 1;
        static final int TRANSACTION_activityPaused = 3;
        static final int TRANSACTION_activityResumed = 2;
        static final int TRANSACTION_activityStopped = 4;

        public Stub()
        {
            attachInterface(this, "android.app.IMiuiActivityObserver");
        }
    }

    private static class Stub.Proxy
        implements IMiuiActivityObserver
    {

        public void activityDestroyed(Intent intent)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IMiuiActivityObserver");
            if(intent == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            intent;
            parcel.recycle();
            throw intent;
        }

        public void activityIdle(Intent intent)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IMiuiActivityObserver");
            if(intent == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            intent;
            parcel.recycle();
            throw intent;
        }

        public void activityPaused(Intent intent)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IMiuiActivityObserver");
            if(intent == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            intent;
            parcel.recycle();
            throw intent;
        }

        public void activityResumed(Intent intent)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IMiuiActivityObserver");
            if(intent == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            intent;
            parcel.recycle();
            throw intent;
        }

        public void activityStopped(Intent intent)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IMiuiActivityObserver");
            if(intent == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            intent;
            parcel.recycle();
            throw intent;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.app.IMiuiActivityObserver";
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void activityDestroyed(Intent intent)
        throws RemoteException;

    public abstract void activityIdle(Intent intent)
        throws RemoteException;

    public abstract void activityPaused(Intent intent)
        throws RemoteException;

    public abstract void activityResumed(Intent intent)
        throws RemoteException;

    public abstract void activityStopped(Intent intent)
        throws RemoteException;
}
