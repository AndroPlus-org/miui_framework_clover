// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.os.*;

public interface IPlayer
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IPlayer
    {

        public static IPlayer asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.media.IPlayer");
            if(iinterface != null && (iinterface instanceof IPlayer))
                return (IPlayer)iinterface;
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
                parcel1.writeString("android.media.IPlayer");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.media.IPlayer");
                start();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.media.IPlayer");
                pause();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.media.IPlayer");
                stop();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.media.IPlayer");
                setVolume(parcel.readFloat());
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.media.IPlayer");
                setPan(parcel.readFloat());
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.media.IPlayer");
                setStartDelayMs(parcel.readInt());
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.media.IPlayer");
                break;
            }
            if(parcel.readInt() != 0)
                parcel1 = (VolumeShaper.Configuration)VolumeShaper.Configuration.CREATOR.createFromParcel(parcel);
            else
                parcel1 = null;
            if(parcel.readInt() != 0)
                parcel = (VolumeShaper.Operation)VolumeShaper.Operation.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            applyVolumeShaper(parcel1, parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.media.IPlayer";
        static final int TRANSACTION_applyVolumeShaper = 7;
        static final int TRANSACTION_pause = 2;
        static final int TRANSACTION_setPan = 5;
        static final int TRANSACTION_setStartDelayMs = 6;
        static final int TRANSACTION_setVolume = 4;
        static final int TRANSACTION_start = 1;
        static final int TRANSACTION_stop = 3;

        public Stub()
        {
            attachInterface(this, "android.media.IPlayer");
        }
    }

    private static class Stub.Proxy
        implements IPlayer
    {

        public void applyVolumeShaper(VolumeShaper.Configuration configuration, VolumeShaper.Operation operation)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IPlayer");
            if(configuration == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            configuration.writeToParcel(parcel, 0);
_L3:
            if(operation == null)
                break MISSING_BLOCK_LABEL_75;
            parcel.writeInt(1);
            operation.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            configuration;
            parcel.recycle();
            throw configuration;
            parcel.writeInt(0);
              goto _L4
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.media.IPlayer";
        }

        public void pause()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IPlayer");
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void setPan(float f)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IPlayer");
            parcel.writeFloat(f);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void setStartDelayMs(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IPlayer");
            parcel.writeInt(i);
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void setVolume(float f)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IPlayer");
            parcel.writeFloat(f);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void start()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IPlayer");
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void stop()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IPlayer");
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void applyVolumeShaper(VolumeShaper.Configuration configuration, VolumeShaper.Operation operation)
        throws RemoteException;

    public abstract void pause()
        throws RemoteException;

    public abstract void setPan(float f)
        throws RemoteException;

    public abstract void setStartDelayMs(int i)
        throws RemoteException;

    public abstract void setVolume(float f)
        throws RemoteException;

    public abstract void start()
        throws RemoteException;

    public abstract void stop()
        throws RemoteException;
}
