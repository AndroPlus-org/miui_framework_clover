// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.radio;

import android.os.*;
import java.util.List;

// Referenced classes of package android.hardware.radio:
//            ITunerCallback, ITuner

public interface IRadioService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IRadioService
    {

        public static IRadioService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.hardware.radio.IRadioService");
            if(iinterface != null && (iinterface instanceof IRadioService))
                return (IRadioService)iinterface;
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
            Object obj = null;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.hardware.radio.IRadioService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.hardware.radio.IRadioService");
                parcel = listModules();
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.hardware.radio.IRadioService");
                i = parcel.readInt();
                break;
            }
            Object obj1;
            boolean flag;
            if(parcel.readInt() != 0)
                obj1 = (RadioManager.BandConfig)RadioManager.BandConfig.CREATOR.createFromParcel(parcel);
            else
                obj1 = null;
            if(parcel.readInt() != 0)
                flag = true;
            else
                flag = false;
            obj1 = openTuner(i, ((RadioManager.BandConfig) (obj1)), flag, ITunerCallback.Stub.asInterface(parcel.readStrongBinder()));
            parcel1.writeNoException();
            parcel = obj;
            if(obj1 != null)
                parcel = ((ITuner) (obj1)).asBinder();
            parcel1.writeStrongBinder(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.hardware.radio.IRadioService";
        static final int TRANSACTION_listModules = 1;
        static final int TRANSACTION_openTuner = 2;

        public Stub()
        {
            attachInterface(this, "android.hardware.radio.IRadioService");
        }
    }

    private static class Stub.Proxy
        implements IRadioService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.hardware.radio.IRadioService";
        }

        public List listModules()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("android.hardware.radio.IRadioService");
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createTypedArrayList(RadioManager.ModuleProperties.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public ITuner openTuner(int i, RadioManager.BandConfig bandconfig, boolean flag, ITunerCallback itunercallback)
            throws RemoteException
        {
            boolean flag1;
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.radio.IRadioService");
            parcel.writeInt(i);
            if(bandconfig == null)
                break MISSING_BLOCK_LABEL_123;
            parcel.writeInt(1);
            bandconfig.writeToParcel(parcel, 0);
_L1:
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            bandconfig = obj;
            if(itunercallback == null)
                break MISSING_BLOCK_LABEL_75;
            bandconfig = itunercallback.asBinder();
            parcel.writeStrongBinder(bandconfig);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            bandconfig = ITuner.Stub.asInterface(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return bandconfig;
            parcel.writeInt(0);
              goto _L1
            bandconfig;
            parcel1.recycle();
            parcel.recycle();
            throw bandconfig;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract List listModules()
        throws RemoteException;

    public abstract ITuner openTuner(int i, RadioManager.BandConfig bandconfig, boolean flag, ITunerCallback itunercallback)
        throws RemoteException;
}
