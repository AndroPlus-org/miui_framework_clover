// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.radio;

import android.os.*;

public interface ITunerCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ITunerCallback
    {

        public static ITunerCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.hardware.radio.ITunerCallback");
            if(iinterface != null && (iinterface instanceof ITunerCallback))
                return (ITunerCallback)iinterface;
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
                parcel1.writeString("android.hardware.radio.ITunerCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.hardware.radio.ITunerCallback");
                onError(parcel.readInt());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.hardware.radio.ITunerCallback");
                if(parcel.readInt() != 0)
                    parcel = (RadioManager.BandConfig)RadioManager.BandConfig.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onConfigurationChanged(parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.hardware.radio.ITunerCallback");
                if(parcel.readInt() != 0)
                    parcel = (RadioManager.ProgramInfo)RadioManager.ProgramInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onCurrentProgramInfoChanged(parcel);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.hardware.radio.ITunerCallback");
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                onTrafficAnnouncement(flag);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.hardware.radio.ITunerCallback");
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                onEmergencyAnnouncement(flag1);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.hardware.radio.ITunerCallback");
                boolean flag2;
                if(parcel.readInt() != 0)
                    flag2 = true;
                else
                    flag2 = false;
                onAntennaState(flag2);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.hardware.radio.ITunerCallback");
                boolean flag3;
                if(parcel.readInt() != 0)
                    flag3 = true;
                else
                    flag3 = false;
                onBackgroundScanAvailabilityChange(flag3);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.hardware.radio.ITunerCallback");
                onBackgroundScanComplete();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.hardware.radio.ITunerCallback");
                onProgramListChanged();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.hardware.radio.ITunerCallback";
        static final int TRANSACTION_onAntennaState = 6;
        static final int TRANSACTION_onBackgroundScanAvailabilityChange = 7;
        static final int TRANSACTION_onBackgroundScanComplete = 8;
        static final int TRANSACTION_onConfigurationChanged = 2;
        static final int TRANSACTION_onCurrentProgramInfoChanged = 3;
        static final int TRANSACTION_onEmergencyAnnouncement = 5;
        static final int TRANSACTION_onError = 1;
        static final int TRANSACTION_onProgramListChanged = 9;
        static final int TRANSACTION_onTrafficAnnouncement = 4;

        public Stub()
        {
            attachInterface(this, "android.hardware.radio.ITunerCallback");
        }
    }

    private static class Stub.Proxy
        implements ITunerCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.hardware.radio.ITunerCallback";
        }

        public void onAntennaState(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.radio.ITunerCallback");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onBackgroundScanAvailabilityChange(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.radio.ITunerCallback");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onBackgroundScanComplete()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.radio.ITunerCallback");
            mRemote.transact(8, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onConfigurationChanged(RadioManager.BandConfig bandconfig)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.radio.ITunerCallback");
            if(bandconfig == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            bandconfig.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            bandconfig;
            parcel.recycle();
            throw bandconfig;
        }

        public void onCurrentProgramInfoChanged(RadioManager.ProgramInfo programinfo)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.radio.ITunerCallback");
            if(programinfo == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            programinfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            programinfo;
            parcel.recycle();
            throw programinfo;
        }

        public void onEmergencyAnnouncement(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.radio.ITunerCallback");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onError(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.radio.ITunerCallback");
            parcel.writeInt(i);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onProgramListChanged()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.radio.ITunerCallback");
            mRemote.transact(9, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onTrafficAnnouncement(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.radio.ITunerCallback");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(4, parcel, null, 1);
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


    public abstract void onAntennaState(boolean flag)
        throws RemoteException;

    public abstract void onBackgroundScanAvailabilityChange(boolean flag)
        throws RemoteException;

    public abstract void onBackgroundScanComplete()
        throws RemoteException;

    public abstract void onConfigurationChanged(RadioManager.BandConfig bandconfig)
        throws RemoteException;

    public abstract void onCurrentProgramInfoChanged(RadioManager.ProgramInfo programinfo)
        throws RemoteException;

    public abstract void onEmergencyAnnouncement(boolean flag)
        throws RemoteException;

    public abstract void onError(int i)
        throws RemoteException;

    public abstract void onProgramListChanged()
        throws RemoteException;

    public abstract void onTrafficAnnouncement(boolean flag)
        throws RemoteException;
}
