// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;


// Referenced classes of package android.os:
//            IInterface, RemoteException, CpuUsageInfo, Binder, 
//            IBinder, Parcel

public interface IHardwarePropertiesManager
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IHardwarePropertiesManager
    {

        public static IHardwarePropertiesManager asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.os.IHardwarePropertiesManager");
            if(iinterface != null && (iinterface instanceof IHardwarePropertiesManager))
                return (IHardwarePropertiesManager)iinterface;
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
                parcel1.writeString("android.os.IHardwarePropertiesManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.os.IHardwarePropertiesManager");
                parcel = getDeviceTemperatures(parcel.readString(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeFloatArray(parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.os.IHardwarePropertiesManager");
                parcel = getCpuUsages(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeTypedArray(parcel, 1);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.os.IHardwarePropertiesManager");
                parcel = getFanSpeeds(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeFloatArray(parcel);
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.os.IHardwarePropertiesManager";
        static final int TRANSACTION_getCpuUsages = 2;
        static final int TRANSACTION_getDeviceTemperatures = 1;
        static final int TRANSACTION_getFanSpeeds = 3;

        public Stub()
        {
            attachInterface(this, "android.os.IHardwarePropertiesManager");
        }
    }

    private static class Stub.Proxy
        implements IHardwarePropertiesManager
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public CpuUsageInfo[] getCpuUsages(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IHardwarePropertiesManager");
            parcel.writeString(s);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            s = (CpuUsageInfo[])parcel1.createTypedArray(CpuUsageInfo.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public float[] getDeviceTemperatures(String s, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IHardwarePropertiesManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.createFloatArray();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public float[] getFanSpeeds(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IHardwarePropertiesManager");
            parcel.writeString(s);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.createFloatArray();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public String getInterfaceDescriptor()
        {
            return "android.os.IHardwarePropertiesManager";
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract CpuUsageInfo[] getCpuUsages(String s)
        throws RemoteException;

    public abstract float[] getDeviceTemperatures(String s, int i, int j)
        throws RemoteException;

    public abstract float[] getFanSpeeds(String s)
        throws RemoteException;
}
