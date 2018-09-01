// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.content.pm;

import android.content.pm.IPackageInstallObserver;
import android.os.*;

public interface IPreloadedAppManager
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IPreloadedAppManager
    {

        public static IPreloadedAppManager asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("miui.content.pm.IPreloadedAppManager");
            if(iinterface != null && (iinterface instanceof IPreloadedAppManager))
                return (IPreloadedAppManager)iinterface;
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
                parcel1.writeString("miui.content.pm.IPreloadedAppManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("miui.content.pm.IPreloadedAppManager");
                reinstallPreloadedApp(parcel.readString(), android.content.pm.IPackageInstallObserver.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "miui.content.pm.IPreloadedAppManager";
        static final int TRANSACTION_reinstallPreloadedApp = 1;

        public Stub()
        {
            attachInterface(this, "miui.content.pm.IPreloadedAppManager");
        }
    }

    private static class Stub.Proxy
        implements IPreloadedAppManager
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "miui.content.pm.IPreloadedAppManager";
        }

        public void reinstallPreloadedApp(String s, IPackageInstallObserver ipackageinstallobserver, int i)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.content.pm.IPreloadedAppManager");
            parcel.writeString(s);
            s = obj;
            if(ipackageinstallobserver == null)
                break MISSING_BLOCK_LABEL_40;
            s = ipackageinstallobserver.asBinder();
            parcel.writeStrongBinder(s);
            parcel.writeInt(i);
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

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void reinstallPreloadedApp(String s, IPackageInstallObserver ipackageinstallobserver, int i)
        throws RemoteException;
}
