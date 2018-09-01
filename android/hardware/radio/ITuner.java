// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.radio;

import android.graphics.Bitmap;
import android.os.*;
import java.util.List;
import java.util.Map;

// Referenced classes of package android.hardware.radio:
//            ProgramSelector

public interface ITuner
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ITuner
    {

        public static ITuner asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.hardware.radio.ITuner");
            if(iinterface != null && (iinterface instanceof ITuner))
                return (ITuner)iinterface;
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
            boolean flag8;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.hardware.radio.ITuner");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.hardware.radio.ITuner");
                close();
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.hardware.radio.ITuner");
                boolean flag = isClosed();
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.hardware.radio.ITuner");
                if(parcel.readInt() != 0)
                    parcel = (RadioManager.BandConfig)RadioManager.BandConfig.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setConfiguration(parcel);
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.hardware.radio.ITuner");
                parcel = getConfiguration();
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

            case 5: // '\005'
                parcel.enforceInterface("android.hardware.radio.ITuner");
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                setMuted(flag1);
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.hardware.radio.ITuner");
                boolean flag2 = isMuted();
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.hardware.radio.ITuner");
                boolean flag3;
                boolean flag9;
                if(parcel.readInt() != 0)
                    flag3 = true;
                else
                    flag3 = false;
                if(parcel.readInt() != 0)
                    flag9 = true;
                else
                    flag9 = false;
                step(flag3, flag9);
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.hardware.radio.ITuner");
                boolean flag4;
                boolean flag10;
                if(parcel.readInt() != 0)
                    flag4 = true;
                else
                    flag4 = false;
                if(parcel.readInt() != 0)
                    flag10 = true;
                else
                    flag10 = false;
                scan(flag4, flag10);
                parcel1.writeNoException();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.hardware.radio.ITuner");
                if(parcel.readInt() != 0)
                    parcel = (ProgramSelector)ProgramSelector.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                tune(parcel);
                parcel1.writeNoException();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.hardware.radio.ITuner");
                cancel();
                parcel1.writeNoException();
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.hardware.radio.ITuner");
                cancelAnnouncement();
                parcel1.writeNoException();
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.hardware.radio.ITuner");
                parcel = getProgramInformation();
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

            case 13: // '\r'
                parcel.enforceInterface("android.hardware.radio.ITuner");
                parcel = getImage(parcel.readInt());
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

            case 14: // '\016'
                parcel.enforceInterface("android.hardware.radio.ITuner");
                boolean flag5 = startBackgroundScan();
                parcel1.writeNoException();
                if(flag5)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 15: // '\017'
                parcel.enforceInterface("android.hardware.radio.ITuner");
                parcel = getProgramList(parcel.readHashMap(getClass().getClassLoader()));
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 16: // '\020'
                parcel.enforceInterface("android.hardware.radio.ITuner");
                boolean flag6 = isAnalogForced();
                parcel1.writeNoException();
                if(flag6)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 17: // '\021'
                parcel.enforceInterface("android.hardware.radio.ITuner");
                boolean flag7;
                if(parcel.readInt() != 0)
                    flag7 = true;
                else
                    flag7 = false;
                setAnalogForced(flag7);
                parcel1.writeNoException();
                return true;

            case 18: // '\022'
                parcel.enforceInterface("android.hardware.radio.ITuner");
                flag8 = isAntennaConnected();
                parcel1.writeNoException();
                break;
            }
            if(flag8)
                i = 1;
            else
                i = 0;
            parcel1.writeInt(i);
            return true;
        }

        private static final String DESCRIPTOR = "android.hardware.radio.ITuner";
        static final int TRANSACTION_cancel = 10;
        static final int TRANSACTION_cancelAnnouncement = 11;
        static final int TRANSACTION_close = 1;
        static final int TRANSACTION_getConfiguration = 4;
        static final int TRANSACTION_getImage = 13;
        static final int TRANSACTION_getProgramInformation = 12;
        static final int TRANSACTION_getProgramList = 15;
        static final int TRANSACTION_isAnalogForced = 16;
        static final int TRANSACTION_isAntennaConnected = 18;
        static final int TRANSACTION_isClosed = 2;
        static final int TRANSACTION_isMuted = 6;
        static final int TRANSACTION_scan = 8;
        static final int TRANSACTION_setAnalogForced = 17;
        static final int TRANSACTION_setConfiguration = 3;
        static final int TRANSACTION_setMuted = 5;
        static final int TRANSACTION_startBackgroundScan = 14;
        static final int TRANSACTION_step = 7;
        static final int TRANSACTION_tune = 9;

        public Stub()
        {
            attachInterface(this, "android.hardware.radio.ITuner");
        }
    }

    private static class Stub.Proxy
        implements ITuner
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void cancel()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.radio.ITuner");
            mRemote.transact(10, parcel, parcel1, 0);
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

        public void cancelAnnouncement()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.radio.ITuner");
            mRemote.transact(11, parcel, parcel1, 0);
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

        public void close()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.radio.ITuner");
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

        public RadioManager.BandConfig getConfiguration()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.radio.ITuner");
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            RadioManager.BandConfig bandconfig = (RadioManager.BandConfig)RadioManager.BandConfig.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return bandconfig;
_L2:
            bandconfig = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public Bitmap getImage(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.radio.ITuner");
            parcel.writeInt(i);
            mRemote.transact(13, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Bitmap bitmap = (Bitmap)Bitmap.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return bitmap;
_L2:
            bitmap = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "android.hardware.radio.ITuner";
        }

        public RadioManager.ProgramInfo getProgramInformation()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.radio.ITuner");
            mRemote.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            RadioManager.ProgramInfo programinfo = (RadioManager.ProgramInfo)RadioManager.ProgramInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return programinfo;
_L2:
            programinfo = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public List getProgramList(Map map)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.radio.ITuner");
            parcel.writeMap(map);
            mRemote.transact(15, parcel, parcel1, 0);
            parcel1.readException();
            map = parcel1.createTypedArrayList(RadioManager.ProgramInfo.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return map;
            map;
            parcel1.recycle();
            parcel.recycle();
            throw map;
        }

        public boolean isAnalogForced()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.hardware.radio.ITuner");
            mRemote.transact(16, parcel, parcel1, 0);
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

        public boolean isAntennaConnected()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.hardware.radio.ITuner");
            mRemote.transact(18, parcel, parcel1, 0);
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

        public boolean isClosed()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.hardware.radio.ITuner");
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

        public boolean isMuted()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.hardware.radio.ITuner");
            mRemote.transact(6, parcel, parcel1, 0);
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

        public void scan(boolean flag, boolean flag1)
            throws RemoteException
        {
            boolean flag2;
            Parcel parcel;
            Parcel parcel1;
            flag2 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.radio.ITuner");
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(flag1)
                i = ((flag2) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(8, parcel, parcel1, 0);
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

        public void setAnalogForced(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.radio.ITuner");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(17, parcel, parcel1, 0);
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

        public void setConfiguration(RadioManager.BandConfig bandconfig)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.radio.ITuner");
            if(bandconfig == null)
                break MISSING_BLOCK_LABEL_56;
            parcel.writeInt(1);
            bandconfig.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            bandconfig;
            parcel1.recycle();
            parcel.recycle();
            throw bandconfig;
        }

        public void setMuted(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.radio.ITuner");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(5, parcel, parcel1, 0);
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

        public boolean startBackgroundScan()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.hardware.radio.ITuner");
            mRemote.transact(14, parcel, parcel1, 0);
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

        public void step(boolean flag, boolean flag1)
            throws RemoteException
        {
            boolean flag2;
            Parcel parcel;
            Parcel parcel1;
            flag2 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.radio.ITuner");
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(flag1)
                i = ((flag2) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(7, parcel, parcel1, 0);
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

        public void tune(ProgramSelector programselector)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.radio.ITuner");
            if(programselector == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            programselector.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            programselector;
            parcel1.recycle();
            parcel.recycle();
            throw programselector;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void cancel()
        throws RemoteException;

    public abstract void cancelAnnouncement()
        throws RemoteException;

    public abstract void close()
        throws RemoteException;

    public abstract RadioManager.BandConfig getConfiguration()
        throws RemoteException;

    public abstract Bitmap getImage(int i)
        throws RemoteException;

    public abstract RadioManager.ProgramInfo getProgramInformation()
        throws RemoteException;

    public abstract List getProgramList(Map map)
        throws RemoteException;

    public abstract boolean isAnalogForced()
        throws RemoteException;

    public abstract boolean isAntennaConnected()
        throws RemoteException;

    public abstract boolean isClosed()
        throws RemoteException;

    public abstract boolean isMuted()
        throws RemoteException;

    public abstract void scan(boolean flag, boolean flag1)
        throws RemoteException;

    public abstract void setAnalogForced(boolean flag)
        throws RemoteException;

    public abstract void setConfiguration(RadioManager.BandConfig bandconfig)
        throws RemoteException;

    public abstract void setMuted(boolean flag)
        throws RemoteException;

    public abstract boolean startBackgroundScan()
        throws RemoteException;

    public abstract void step(boolean flag, boolean flag1)
        throws RemoteException;

    public abstract void tune(ProgramSelector programselector)
        throws RemoteException;
}
