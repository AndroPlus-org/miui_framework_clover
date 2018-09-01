// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.vr;

import android.app.Vr2dDisplayProperties;
import android.os.*;

// Referenced classes of package android.service.vr:
//            IVrStateCallbacks, IPersistentVrStateCallbacks

public interface IVrManager
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IVrManager
    {

        public static IVrManager asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.service.vr.IVrManager");
            if(iinterface != null && (iinterface instanceof IVrManager))
                return (IVrManager)iinterface;
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
            boolean flag = false;
            boolean flag1 = false;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.service.vr.IVrManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.service.vr.IVrManager");
                registerListener(IVrStateCallbacks.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.service.vr.IVrManager");
                unregisterListener(IVrStateCallbacks.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.service.vr.IVrManager");
                registerPersistentVrStateListener(IPersistentVrStateCallbacks.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.service.vr.IVrManager");
                unregisterPersistentVrStateListener(IPersistentVrStateCallbacks.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.service.vr.IVrManager");
                boolean flag2 = getVrModeState();
                parcel1.writeNoException();
                i = ((flag1) ? 1 : 0);
                if(flag2)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.service.vr.IVrManager");
                boolean flag3 = getPersistentVrModeEnabled();
                parcel1.writeNoException();
                i = ((flag) ? 1 : 0);
                if(flag3)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.service.vr.IVrManager");
                boolean flag4;
                if(parcel.readInt() != 0)
                    flag4 = true;
                else
                    flag4 = false;
                setPersistentVrModeEnabled(flag4);
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.service.vr.IVrManager");
                if(parcel.readInt() != 0)
                    parcel = (Vr2dDisplayProperties)Vr2dDisplayProperties.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setVr2dDisplayProperties(parcel);
                parcel1.writeNoException();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.service.vr.IVrManager");
                i = getVr2dDisplayId();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.service.vr.IVrManager");
                setAndBindCompositor(parcel.readString());
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.service.vr.IVrManager";
        static final int TRANSACTION_getPersistentVrModeEnabled = 6;
        static final int TRANSACTION_getVr2dDisplayId = 9;
        static final int TRANSACTION_getVrModeState = 5;
        static final int TRANSACTION_registerListener = 1;
        static final int TRANSACTION_registerPersistentVrStateListener = 3;
        static final int TRANSACTION_setAndBindCompositor = 10;
        static final int TRANSACTION_setPersistentVrModeEnabled = 7;
        static final int TRANSACTION_setVr2dDisplayProperties = 8;
        static final int TRANSACTION_unregisterListener = 2;
        static final int TRANSACTION_unregisterPersistentVrStateListener = 4;

        public Stub()
        {
            attachInterface(this, "android.service.vr.IVrManager");
        }
    }

    private static class Stub.Proxy
        implements IVrManager
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.service.vr.IVrManager";
        }

        public boolean getPersistentVrModeEnabled()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.service.vr.IVrManager");
            mRemote.transact(6, parcel, parcel1, 0);
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

        public int getVr2dDisplayId()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.service.vr.IVrManager");
            mRemote.transact(9, parcel, parcel1, 0);
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

        public boolean getVrModeState()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.service.vr.IVrManager");
            mRemote.transact(5, parcel, parcel1, 0);
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

        public void registerListener(IVrStateCallbacks ivrstatecallbacks)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.vr.IVrManager");
            if(ivrstatecallbacks == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ivrstatecallbacks.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ivrstatecallbacks;
            parcel1.recycle();
            parcel.recycle();
            throw ivrstatecallbacks;
        }

        public void registerPersistentVrStateListener(IPersistentVrStateCallbacks ipersistentvrstatecallbacks)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.vr.IVrManager");
            if(ipersistentvrstatecallbacks == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ipersistentvrstatecallbacks.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ipersistentvrstatecallbacks;
            parcel1.recycle();
            parcel.recycle();
            throw ipersistentvrstatecallbacks;
        }

        public void setAndBindCompositor(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.vr.IVrManager");
            parcel.writeString(s);
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setPersistentVrModeEnabled(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.vr.IVrManager");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(7, parcel, parcel1, 0);
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

        public void setVr2dDisplayProperties(Vr2dDisplayProperties vr2ddisplayproperties)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.vr.IVrManager");
            if(vr2ddisplayproperties == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            vr2ddisplayproperties.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            vr2ddisplayproperties;
            parcel1.recycle();
            parcel.recycle();
            throw vr2ddisplayproperties;
        }

        public void unregisterListener(IVrStateCallbacks ivrstatecallbacks)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.vr.IVrManager");
            if(ivrstatecallbacks == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ivrstatecallbacks.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ivrstatecallbacks;
            parcel1.recycle();
            parcel.recycle();
            throw ivrstatecallbacks;
        }

        public void unregisterPersistentVrStateListener(IPersistentVrStateCallbacks ipersistentvrstatecallbacks)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.vr.IVrManager");
            if(ipersistentvrstatecallbacks == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ipersistentvrstatecallbacks.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ipersistentvrstatecallbacks;
            parcel1.recycle();
            parcel.recycle();
            throw ipersistentvrstatecallbacks;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract boolean getPersistentVrModeEnabled()
        throws RemoteException;

    public abstract int getVr2dDisplayId()
        throws RemoteException;

    public abstract boolean getVrModeState()
        throws RemoteException;

    public abstract void registerListener(IVrStateCallbacks ivrstatecallbacks)
        throws RemoteException;

    public abstract void registerPersistentVrStateListener(IPersistentVrStateCallbacks ipersistentvrstatecallbacks)
        throws RemoteException;

    public abstract void setAndBindCompositor(String s)
        throws RemoteException;

    public abstract void setPersistentVrModeEnabled(boolean flag)
        throws RemoteException;

    public abstract void setVr2dDisplayProperties(Vr2dDisplayProperties vr2ddisplayproperties)
        throws RemoteException;

    public abstract void unregisterListener(IVrStateCallbacks ivrstatecallbacks)
        throws RemoteException;

    public abstract void unregisterPersistentVrStateListener(IPersistentVrStateCallbacks ipersistentvrstatecallbacks)
        throws RemoteException;
}
