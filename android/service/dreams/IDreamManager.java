// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.dreams;

import android.content.ComponentName;
import android.os.*;

public interface IDreamManager
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IDreamManager
    {

        public static IDreamManager asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.service.dreams.IDreamManager");
            if(iinterface != null && (iinterface instanceof IDreamManager))
                return (IDreamManager)iinterface;
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
                parcel1.writeString("android.service.dreams.IDreamManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.service.dreams.IDreamManager");
                dream();
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.service.dreams.IDreamManager");
                awaken();
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.service.dreams.IDreamManager");
                setDreamComponents((ComponentName[])parcel.createTypedArray(ComponentName.CREATOR));
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.service.dreams.IDreamManager");
                parcel = getDreamComponents();
                parcel1.writeNoException();
                parcel1.writeTypedArray(parcel, 1);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.service.dreams.IDreamManager");
                parcel = getDefaultDreamComponent();
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.service.dreams.IDreamManager");
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                testDream(parcel);
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.service.dreams.IDreamManager");
                boolean flag = isDreaming();
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.service.dreams.IDreamManager");
                IBinder ibinder = parcel.readStrongBinder();
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                finishSelf(ibinder, flag1);
                parcel1.writeNoException();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.service.dreams.IDreamManager");
                startDozing(parcel.readStrongBinder(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.service.dreams.IDreamManager");
                stopDozing(parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.service.dreams.IDreamManager";
        static final int TRANSACTION_awaken = 2;
        static final int TRANSACTION_dream = 1;
        static final int TRANSACTION_finishSelf = 8;
        static final int TRANSACTION_getDefaultDreamComponent = 5;
        static final int TRANSACTION_getDreamComponents = 4;
        static final int TRANSACTION_isDreaming = 7;
        static final int TRANSACTION_setDreamComponents = 3;
        static final int TRANSACTION_startDozing = 9;
        static final int TRANSACTION_stopDozing = 10;
        static final int TRANSACTION_testDream = 6;

        public Stub()
        {
            attachInterface(this, "android.service.dreams.IDreamManager");
        }
    }

    private static class Stub.Proxy
        implements IDreamManager
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void awaken()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.dreams.IDreamManager");
            mRemote.transact(2, parcel, parcel1, 0);
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

        public void dream()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.dreams.IDreamManager");
            mRemote.transact(1, parcel, parcel1, 0);
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

        public void finishSelf(IBinder ibinder, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.dreams.IDreamManager");
            parcel.writeStrongBinder(ibinder);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public ComponentName getDefaultDreamComponent()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.dreams.IDreamManager");
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            ComponentName componentname = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return componentname;
_L2:
            componentname = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public ComponentName[] getDreamComponents()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            ComponentName acomponentname[];
            parcel.writeInterfaceToken("android.service.dreams.IDreamManager");
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            acomponentname = (ComponentName[])parcel1.createTypedArray(ComponentName.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return acomponentname;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "android.service.dreams.IDreamManager";
        }

        public boolean isDreaming()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.service.dreams.IDreamManager");
            mRemote.transact(7, parcel, parcel1, 0);
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

        public void setDreamComponents(ComponentName acomponentname[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.dreams.IDreamManager");
            parcel.writeTypedArray(acomponentname, 0);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            acomponentname;
            parcel1.recycle();
            parcel.recycle();
            throw acomponentname;
        }

        public void startDozing(IBinder ibinder, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.dreams.IDreamManager");
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void stopDozing(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.dreams.IDreamManager");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void testDream(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.dreams.IDreamManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void awaken()
        throws RemoteException;

    public abstract void dream()
        throws RemoteException;

    public abstract void finishSelf(IBinder ibinder, boolean flag)
        throws RemoteException;

    public abstract ComponentName getDefaultDreamComponent()
        throws RemoteException;

    public abstract ComponentName[] getDreamComponents()
        throws RemoteException;

    public abstract boolean isDreaming()
        throws RemoteException;

    public abstract void setDreamComponents(ComponentName acomponentname[])
        throws RemoteException;

    public abstract void startDozing(IBinder ibinder, int i, int j)
        throws RemoteException;

    public abstract void stopDozing(IBinder ibinder)
        throws RemoteException;

    public abstract void testDream(ComponentName componentname)
        throws RemoteException;
}
