// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.ComponentName;
import android.os.*;

public interface IInstrumentationWatcher
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IInstrumentationWatcher
    {

        public static IInstrumentationWatcher asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.app.IInstrumentationWatcher");
            if(iinterface != null && (iinterface instanceof IInstrumentationWatcher))
                return (IInstrumentationWatcher)iinterface;
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
                parcel1.writeString("android.app.IInstrumentationWatcher");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.app.IInstrumentationWatcher");
                ComponentName componentname;
                if(parcel.readInt() != 0)
                    componentname = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                instrumentationStatus(componentname, i, parcel);
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.app.IInstrumentationWatcher");
                break;
            }
            ComponentName componentname1;
            if(parcel.readInt() != 0)
                componentname1 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
            else
                componentname1 = null;
            i = parcel.readInt();
            if(parcel.readInt() != 0)
                parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            instrumentationFinished(componentname1, i, parcel);
            parcel1.writeNoException();
            return true;
        }

        private static final String DESCRIPTOR = "android.app.IInstrumentationWatcher";
        static final int TRANSACTION_instrumentationFinished = 2;
        static final int TRANSACTION_instrumentationStatus = 1;

        public Stub()
        {
            attachInterface(this, "android.app.IInstrumentationWatcher");
        }
    }

    private static class Stub.Proxy
        implements IInstrumentationWatcher
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.app.IInstrumentationWatcher";
        }

        public void instrumentationFinished(ComponentName componentname, int i, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IInstrumentationWatcher");
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L3:
            parcel.writeInt(i);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_111;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
            parcel.writeInt(0);
              goto _L4
        }

        public void instrumentationStatus(ComponentName componentname, int i, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IInstrumentationWatcher");
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L3:
            parcel.writeInt(i);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_111;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
            parcel.writeInt(0);
              goto _L4
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void instrumentationFinished(ComponentName componentname, int i, Bundle bundle)
        throws RemoteException;

    public abstract void instrumentationStatus(ComponentName componentname, int i, Bundle bundle)
        throws RemoteException;
}
