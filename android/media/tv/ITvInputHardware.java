// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.tv;

import android.os.*;
import android.view.KeyEvent;
import android.view.Surface;

// Referenced classes of package android.media.tv:
//            TvStreamConfig

public interface ITvInputHardware
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ITvInputHardware
    {

        public static ITvInputHardware asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.media.tv.ITvInputHardware");
            if(iinterface != null && (iinterface instanceof ITvInputHardware))
                return (ITvInputHardware)iinterface;
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
                parcel1.writeString("android.media.tv.ITvInputHardware");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.media.tv.ITvInputHardware");
                Surface surface;
                boolean flag;
                if(parcel.readInt() != 0)
                    surface = (Surface)Surface.CREATOR.createFromParcel(parcel);
                else
                    surface = null;
                if(parcel.readInt() != 0)
                    parcel = (TvStreamConfig)TvStreamConfig.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag = setSurface(surface, parcel);
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.media.tv.ITvInputHardware");
                setStreamVolume(parcel.readFloat());
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.media.tv.ITvInputHardware");
                boolean flag1;
                if(parcel.readInt() != 0)
                    parcel = (KeyEvent)KeyEvent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag1 = dispatchKeyEventToHdmi(parcel);
                parcel1.writeNoException();
                if(flag1)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.media.tv.ITvInputHardware");
                overrideAudioSink(parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.media.tv.ITvInputHardware";
        static final int TRANSACTION_dispatchKeyEventToHdmi = 3;
        static final int TRANSACTION_overrideAudioSink = 4;
        static final int TRANSACTION_setStreamVolume = 2;
        static final int TRANSACTION_setSurface = 1;

        public Stub()
        {
            attachInterface(this, "android.media.tv.ITvInputHardware");
        }
    }

    private static class Stub.Proxy
        implements ITvInputHardware
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public boolean dispatchKeyEventToHdmi(KeyEvent keyevent)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputHardware");
            if(keyevent == null)
                break MISSING_BLOCK_LABEL_72;
            parcel.writeInt(1);
            keyevent.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(3, parcel, parcel1, 0);
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
            parcel.writeInt(0);
              goto _L1
            keyevent;
            parcel1.recycle();
            parcel.recycle();
            throw keyevent;
        }

        public String getInterfaceDescriptor()
        {
            return "android.media.tv.ITvInputHardware";
        }

        public void overrideAudioSink(int i, String s, int j, int k, int l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputHardware");
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeInt(l);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setStreamVolume(float f)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputHardware");
            parcel.writeFloat(f);
            mRemote.transact(2, parcel, parcel1, 0);
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

        public boolean setSurface(Surface surface, TvStreamConfig tvstreamconfig)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputHardware");
            if(surface == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            surface.writeToParcel(parcel, 0);
_L3:
            if(tvstreamconfig == null)
                break MISSING_BLOCK_LABEL_112;
            parcel.writeInt(1);
            tvstreamconfig.writeToParcel(parcel, 0);
_L4:
            int i;
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
_L2:
            parcel.writeInt(0);
              goto _L3
            surface;
            parcel1.recycle();
            parcel.recycle();
            throw surface;
            parcel.writeInt(0);
              goto _L4
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract boolean dispatchKeyEventToHdmi(KeyEvent keyevent)
        throws RemoteException;

    public abstract void overrideAudioSink(int i, String s, int j, int k, int l)
        throws RemoteException;

    public abstract void setStreamVolume(float f)
        throws RemoteException;

    public abstract boolean setSurface(Surface surface, TvStreamConfig tvstreamconfig)
        throws RemoteException;
}
