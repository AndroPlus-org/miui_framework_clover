// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;


// Referenced classes of package android.os:
//            IInterface, RemoteException, BatteryProperties, Binder, 
//            IBinder, Parcel

public interface IBatteryPropertiesListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IBatteryPropertiesListener
    {

        public static IBatteryPropertiesListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.os.IBatteryPropertiesListener");
            if(iinterface != null && (iinterface instanceof IBatteryPropertiesListener))
                return (IBatteryPropertiesListener)iinterface;
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
                parcel1.writeString("android.os.IBatteryPropertiesListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.os.IBatteryPropertiesListener");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (BatteryProperties)BatteryProperties.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            batteryPropertiesChanged(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.os.IBatteryPropertiesListener";
        static final int TRANSACTION_batteryPropertiesChanged = 1;

        public Stub()
        {
            attachInterface(this, "android.os.IBatteryPropertiesListener");
        }
    }

    private static class Stub.Proxy
        implements IBatteryPropertiesListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void batteryPropertiesChanged(BatteryProperties batteryproperties)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IBatteryPropertiesListener");
            if(batteryproperties == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            batteryproperties.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            batteryproperties;
            parcel.recycle();
            throw batteryproperties;
        }

        public String getInterfaceDescriptor()
        {
            return "android.os.IBatteryPropertiesListener";
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void batteryPropertiesChanged(BatteryProperties batteryproperties)
        throws RemoteException;
}
