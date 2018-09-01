// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.display;

import android.graphics.Point;
import android.media.projection.IMediaProjection;
import android.os.*;
import android.view.DisplayInfo;
import android.view.Surface;

// Referenced classes of package android.hardware.display:
//            IVirtualDisplayCallback, WifiDisplayStatus, IDisplayManagerCallback

public interface IDisplayManager
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IDisplayManager
    {

        public static IDisplayManager asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.hardware.display.IDisplayManager");
            if(iinterface != null && (iinterface instanceof IDisplayManager))
                return (IDisplayManager)iinterface;
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
                parcel1.writeString("android.hardware.display.IDisplayManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.hardware.display.IDisplayManager");
                parcel = getDisplayInfo(parcel.readInt());
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

            case 2: // '\002'
                parcel.enforceInterface("android.hardware.display.IDisplayManager");
                parcel = getDisplayIds();
                parcel1.writeNoException();
                parcel1.writeIntArray(parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.hardware.display.IDisplayManager");
                registerCallback(IDisplayManagerCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.hardware.display.IDisplayManager");
                startWifiDisplayScan();
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.hardware.display.IDisplayManager");
                stopWifiDisplayScan();
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.hardware.display.IDisplayManager");
                connectWifiDisplay(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.hardware.display.IDisplayManager");
                disconnectWifiDisplay();
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.hardware.display.IDisplayManager");
                renameWifiDisplay(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.hardware.display.IDisplayManager");
                forgetWifiDisplay(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.hardware.display.IDisplayManager");
                pauseWifiDisplay();
                parcel1.writeNoException();
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.hardware.display.IDisplayManager");
                resumeWifiDisplay();
                parcel1.writeNoException();
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.hardware.display.IDisplayManager");
                parcel = getWifiDisplayStatus();
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
                parcel.enforceInterface("android.hardware.display.IDisplayManager");
                requestColorMode(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.hardware.display.IDisplayManager");
                IVirtualDisplayCallback ivirtualdisplaycallback = IVirtualDisplayCallback.Stub.asInterface(parcel.readStrongBinder());
                IMediaProjection imediaprojection = android.media.projection.IMediaProjection.Stub.asInterface(parcel.readStrongBinder());
                String s = parcel.readString();
                String s1 = parcel.readString();
                i = parcel.readInt();
                j = parcel.readInt();
                int k = parcel.readInt();
                Surface surface;
                if(parcel.readInt() != 0)
                    surface = (Surface)Surface.CREATOR.createFromParcel(parcel);
                else
                    surface = null;
                i = createVirtualDisplay(ivirtualdisplaycallback, imediaprojection, s, s1, i, j, k, surface, parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 15: // '\017'
                parcel.enforceInterface("android.hardware.display.IDisplayManager");
                resizeVirtualDisplay(IVirtualDisplayCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 16: // '\020'
                parcel.enforceInterface("android.hardware.display.IDisplayManager");
                IVirtualDisplayCallback ivirtualdisplaycallback1 = IVirtualDisplayCallback.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel = (Surface)Surface.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setVirtualDisplaySurface(ivirtualdisplaycallback1, parcel);
                parcel1.writeNoException();
                return true;

            case 17: // '\021'
                parcel.enforceInterface("android.hardware.display.IDisplayManager");
                releaseVirtualDisplay(IVirtualDisplayCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 18: // '\022'
                parcel.enforceInterface("android.hardware.display.IDisplayManager");
                parcel = getStableDisplaySize();
                parcel1.writeNoException();
                break;
            }
            if(parcel != null)
            {
                parcel1.writeInt(1);
                parcel.writeToParcel(parcel1, 1);
            } else
            {
                parcel1.writeInt(0);
            }
            return true;
        }

        private static final String DESCRIPTOR = "android.hardware.display.IDisplayManager";
        static final int TRANSACTION_connectWifiDisplay = 6;
        static final int TRANSACTION_createVirtualDisplay = 14;
        static final int TRANSACTION_disconnectWifiDisplay = 7;
        static final int TRANSACTION_forgetWifiDisplay = 9;
        static final int TRANSACTION_getDisplayIds = 2;
        static final int TRANSACTION_getDisplayInfo = 1;
        static final int TRANSACTION_getStableDisplaySize = 18;
        static final int TRANSACTION_getWifiDisplayStatus = 12;
        static final int TRANSACTION_pauseWifiDisplay = 10;
        static final int TRANSACTION_registerCallback = 3;
        static final int TRANSACTION_releaseVirtualDisplay = 17;
        static final int TRANSACTION_renameWifiDisplay = 8;
        static final int TRANSACTION_requestColorMode = 13;
        static final int TRANSACTION_resizeVirtualDisplay = 15;
        static final int TRANSACTION_resumeWifiDisplay = 11;
        static final int TRANSACTION_setVirtualDisplaySurface = 16;
        static final int TRANSACTION_startWifiDisplayScan = 4;
        static final int TRANSACTION_stopWifiDisplayScan = 5;

        public Stub()
        {
            attachInterface(this, "android.hardware.display.IDisplayManager");
        }
    }

    private static class Stub.Proxy
        implements IDisplayManager
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void connectWifiDisplay(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.display.IDisplayManager");
            parcel.writeString(s);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int createVirtualDisplay(IVirtualDisplayCallback ivirtualdisplaycallback, IMediaProjection imediaprojection, String s, String s1, int i, int j, int k, 
                Surface surface, int l, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.display.IDisplayManager");
            if(ivirtualdisplaycallback == null) goto _L2; else goto _L1
_L1:
            ivirtualdisplaycallback = ivirtualdisplaycallback.asBinder();
_L7:
            parcel.writeStrongBinder(ivirtualdisplaycallback);
            if(imediaprojection == null) goto _L4; else goto _L3
_L3:
            ivirtualdisplaycallback = imediaprojection.asBinder();
_L8:
            parcel.writeStrongBinder(ivirtualdisplaycallback);
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            if(surface == null) goto _L6; else goto _L5
_L5:
            parcel.writeInt(1);
            surface.writeToParcel(parcel, 0);
_L9:
            parcel.writeInt(l);
            parcel.writeString(s2);
            mRemote.transact(14, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
_L2:
            ivirtualdisplaycallback = null;
              goto _L7
_L4:
            ivirtualdisplaycallback = null;
              goto _L8
_L6:
            parcel.writeInt(0);
              goto _L9
            ivirtualdisplaycallback;
            parcel1.recycle();
            parcel.recycle();
            throw ivirtualdisplaycallback;
              goto _L7
        }

        public void disconnectWifiDisplay()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.display.IDisplayManager");
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

        public void forgetWifiDisplay(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.display.IDisplayManager");
            parcel.writeString(s);
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int[] getDisplayIds()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int ai[];
            parcel.writeInterfaceToken("android.hardware.display.IDisplayManager");
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            ai = parcel1.createIntArray();
            parcel1.recycle();
            parcel.recycle();
            return ai;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public DisplayInfo getDisplayInfo(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.display.IDisplayManager");
            parcel.writeInt(i);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            DisplayInfo displayinfo = (DisplayInfo)DisplayInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return displayinfo;
_L2:
            displayinfo = null;
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
            return "android.hardware.display.IDisplayManager";
        }

        public Point getStableDisplaySize()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.display.IDisplayManager");
            mRemote.transact(18, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Point point = (Point)Point.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return point;
_L2:
            point = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public WifiDisplayStatus getWifiDisplayStatus()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.display.IDisplayManager");
            mRemote.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            WifiDisplayStatus wifidisplaystatus = (WifiDisplayStatus)WifiDisplayStatus.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return wifidisplaystatus;
_L2:
            wifidisplaystatus = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void pauseWifiDisplay()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.display.IDisplayManager");
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

        public void registerCallback(IDisplayManagerCallback idisplaymanagercallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.display.IDisplayManager");
            if(idisplaymanagercallback == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = idisplaymanagercallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            idisplaymanagercallback;
            parcel1.recycle();
            parcel.recycle();
            throw idisplaymanagercallback;
        }

        public void releaseVirtualDisplay(IVirtualDisplayCallback ivirtualdisplaycallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.display.IDisplayManager");
            if(ivirtualdisplaycallback == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ivirtualdisplaycallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(17, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ivirtualdisplaycallback;
            parcel1.recycle();
            parcel.recycle();
            throw ivirtualdisplaycallback;
        }

        public void renameWifiDisplay(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.display.IDisplayManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void requestColorMode(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.display.IDisplayManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(13, parcel, parcel1, 0);
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

        public void resizeVirtualDisplay(IVirtualDisplayCallback ivirtualdisplaycallback, int i, int j, int k)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.display.IDisplayManager");
            if(ivirtualdisplaycallback == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = ivirtualdisplaycallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(15, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ivirtualdisplaycallback;
            parcel1.recycle();
            parcel.recycle();
            throw ivirtualdisplaycallback;
        }

        public void resumeWifiDisplay()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.display.IDisplayManager");
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

        public void setVirtualDisplaySurface(IVirtualDisplayCallback ivirtualdisplaycallback, Surface surface)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.display.IDisplayManager");
            if(ivirtualdisplaycallback == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = ivirtualdisplaycallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(surface == null)
                break MISSING_BLOCK_LABEL_86;
            parcel.writeInt(1);
            surface.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(16, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ivirtualdisplaycallback;
            parcel1.recycle();
            parcel.recycle();
            throw ivirtualdisplaycallback;
        }

        public void startWifiDisplayScan()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.display.IDisplayManager");
            mRemote.transact(4, parcel, parcel1, 0);
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

        public void stopWifiDisplayScan()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.display.IDisplayManager");
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

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void connectWifiDisplay(String s)
        throws RemoteException;

    public abstract int createVirtualDisplay(IVirtualDisplayCallback ivirtualdisplaycallback, IMediaProjection imediaprojection, String s, String s1, int i, int j, int k, 
            Surface surface, int l, String s2)
        throws RemoteException;

    public abstract void disconnectWifiDisplay()
        throws RemoteException;

    public abstract void forgetWifiDisplay(String s)
        throws RemoteException;

    public abstract int[] getDisplayIds()
        throws RemoteException;

    public abstract DisplayInfo getDisplayInfo(int i)
        throws RemoteException;

    public abstract Point getStableDisplaySize()
        throws RemoteException;

    public abstract WifiDisplayStatus getWifiDisplayStatus()
        throws RemoteException;

    public abstract void pauseWifiDisplay()
        throws RemoteException;

    public abstract void registerCallback(IDisplayManagerCallback idisplaymanagercallback)
        throws RemoteException;

    public abstract void releaseVirtualDisplay(IVirtualDisplayCallback ivirtualdisplaycallback)
        throws RemoteException;

    public abstract void renameWifiDisplay(String s, String s1)
        throws RemoteException;

    public abstract void requestColorMode(int i, int j)
        throws RemoteException;

    public abstract void resizeVirtualDisplay(IVirtualDisplayCallback ivirtualdisplaycallback, int i, int j, int k)
        throws RemoteException;

    public abstract void resumeWifiDisplay()
        throws RemoteException;

    public abstract void setVirtualDisplaySurface(IVirtualDisplayCallback ivirtualdisplaycallback, Surface surface)
        throws RemoteException;

    public abstract void startWifiDisplayScan()
        throws RemoteException;

    public abstract void stopWifiDisplayScan()
        throws RemoteException;
}
