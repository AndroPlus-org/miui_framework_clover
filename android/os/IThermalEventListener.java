// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;


// Referenced classes of package android.os:
//            IInterface, RemoteException, Temperature, Binder, 
//            IBinder, Parcel

public interface IThermalEventListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IThermalEventListener
    {

        public static IThermalEventListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.os.IThermalEventListener");
            if(iinterface != null && (iinterface instanceof IThermalEventListener))
                return (IThermalEventListener)iinterface;
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
                parcel1.writeString("android.os.IThermalEventListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.os.IThermalEventListener");
                break;
            }
            boolean flag;
            if(parcel.readInt() != 0)
                flag = true;
            else
                flag = false;
            if(parcel.readInt() != 0)
                parcel = (Temperature)Temperature.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            notifyThrottling(flag, parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.os.IThermalEventListener";
        static final int TRANSACTION_notifyThrottling = 1;

        public Stub()
        {
            attachInterface(this, "android.os.IThermalEventListener");
        }
    }

    private static class Stub.Proxy
        implements IThermalEventListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.os.IThermalEventListener";
        }

        public void notifyThrottling(boolean flag, Temperature temperature)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IThermalEventListener");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            if(temperature == null)
                break MISSING_BLOCK_LABEL_67;
            parcel.writeInt(1);
            temperature.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            temperature;
            parcel.recycle();
            throw temperature;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void notifyThrottling(boolean flag, Temperature temperature)
        throws RemoteException;
}
