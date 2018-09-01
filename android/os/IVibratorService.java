// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;


// Referenced classes of package android.os:
//            IInterface, RemoteException, IBinder, VibrationEffect, 
//            Binder, Parcel

public interface IVibratorService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IVibratorService
    {

        public static IVibratorService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.os.IVibratorService");
            if(iinterface != null && (iinterface instanceof IVibratorService))
                return (IVibratorService)iinterface;
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
                parcel1.writeString("android.os.IVibratorService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.os.IVibratorService");
                boolean flag2 = hasVibrator();
                parcel1.writeNoException();
                i = ((flag1) ? 1 : 0);
                if(flag2)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.os.IVibratorService");
                boolean flag3 = hasAmplitudeControl();
                parcel1.writeNoException();
                i = ((flag) ? 1 : 0);
                if(flag3)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.os.IVibratorService");
                i = parcel.readInt();
                String s = parcel.readString();
                VibrationEffect vibrationeffect;
                if(parcel.readInt() != 0)
                    vibrationeffect = (VibrationEffect)VibrationEffect.CREATOR.createFromParcel(parcel);
                else
                    vibrationeffect = null;
                vibrate(i, s, vibrationeffect, parcel.readInt(), parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.os.IVibratorService");
                cancelVibrate(parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.os.IVibratorService";
        static final int TRANSACTION_cancelVibrate = 4;
        static final int TRANSACTION_hasAmplitudeControl = 2;
        static final int TRANSACTION_hasVibrator = 1;
        static final int TRANSACTION_vibrate = 3;

        public Stub()
        {
            attachInterface(this, "android.os.IVibratorService");
        }
    }

    private static class Stub.Proxy
        implements IVibratorService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void cancelVibrate(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IVibratorService");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public String getInterfaceDescriptor()
        {
            return "android.os.IVibratorService";
        }

        public boolean hasAmplitudeControl()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.os.IVibratorService");
            mRemote.transact(2, parcel, parcel1, 0);
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

        public boolean hasVibrator()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.os.IVibratorService");
            mRemote.transact(1, parcel, parcel1, 0);
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

        public void vibrate(int i, String s, VibrationEffect vibrationeffect, int j, IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IVibratorService");
            parcel.writeInt(i);
            parcel.writeString(s);
            if(vibrationeffect == null)
                break MISSING_BLOCK_LABEL_92;
            parcel.writeInt(1);
            vibrationeffect.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(j);
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
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


    public abstract void cancelVibrate(IBinder ibinder)
        throws RemoteException;

    public abstract boolean hasAmplitudeControl()
        throws RemoteException;

    public abstract boolean hasVibrator()
        throws RemoteException;

    public abstract void vibrate(int i, String s, VibrationEffect vibrationeffect, int j, IBinder ibinder)
        throws RemoteException;
}
