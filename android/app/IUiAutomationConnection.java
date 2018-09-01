// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.accessibilityservice.IAccessibilityServiceClient;
import android.graphics.Bitmap;
import android.os.*;
import android.view.*;

public interface IUiAutomationConnection
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IUiAutomationConnection
    {

        public static IUiAutomationConnection asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.app.IUiAutomationConnection");
            if(iinterface != null && (iinterface instanceof IUiAutomationConnection))
                return (IUiAutomationConnection)iinterface;
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
                parcel1.writeString("android.app.IUiAutomationConnection");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.app.IUiAutomationConnection");
                connect(android.accessibilityservice.IAccessibilityServiceClient.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.app.IUiAutomationConnection");
                disconnect();
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.app.IUiAutomationConnection");
                InputEvent inputevent;
                boolean flag;
                if(parcel.readInt() != 0)
                    inputevent = (InputEvent)InputEvent.CREATOR.createFromParcel(parcel);
                else
                    inputevent = null;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                flag = injectInputEvent(inputevent, flag);
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.app.IUiAutomationConnection");
                boolean flag1 = setRotation(parcel.readInt());
                parcel1.writeNoException();
                if(flag1)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.app.IUiAutomationConnection");
                parcel = takeScreenshot(parcel.readInt(), parcel.readInt());
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

            case 6: // '\006'
                parcel.enforceInterface("android.app.IUiAutomationConnection");
                boolean flag2 = clearWindowContentFrameStats(parcel.readInt());
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.app.IUiAutomationConnection");
                parcel = getWindowContentFrameStats(parcel.readInt());
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

            case 8: // '\b'
                parcel.enforceInterface("android.app.IUiAutomationConnection");
                clearWindowAnimationFrameStats();
                parcel1.writeNoException();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.app.IUiAutomationConnection");
                parcel = getWindowAnimationFrameStats();
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

            case 10: // '\n'
                parcel.enforceInterface("android.app.IUiAutomationConnection");
                String s = parcel.readString();
                ParcelFileDescriptor parcelfiledescriptor;
                if(parcel.readInt() != 0)
                    parcelfiledescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                else
                    parcelfiledescriptor = null;
                if(parcel.readInt() != 0)
                    parcel = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                executeShellCommand(s, parcelfiledescriptor, parcel);
                parcel1.writeNoException();
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.app.IUiAutomationConnection");
                grantRuntimePermission(parcel.readString(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.app.IUiAutomationConnection");
                revokeRuntimePermission(parcel.readString(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.app.IUiAutomationConnection");
                shutdown();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.app.IUiAutomationConnection";
        static final int TRANSACTION_clearWindowAnimationFrameStats = 8;
        static final int TRANSACTION_clearWindowContentFrameStats = 6;
        static final int TRANSACTION_connect = 1;
        static final int TRANSACTION_disconnect = 2;
        static final int TRANSACTION_executeShellCommand = 10;
        static final int TRANSACTION_getWindowAnimationFrameStats = 9;
        static final int TRANSACTION_getWindowContentFrameStats = 7;
        static final int TRANSACTION_grantRuntimePermission = 11;
        static final int TRANSACTION_injectInputEvent = 3;
        static final int TRANSACTION_revokeRuntimePermission = 12;
        static final int TRANSACTION_setRotation = 4;
        static final int TRANSACTION_shutdown = 13;
        static final int TRANSACTION_takeScreenshot = 5;

        public Stub()
        {
            attachInterface(this, "android.app.IUiAutomationConnection");
        }
    }

    private static class Stub.Proxy
        implements IUiAutomationConnection
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void clearWindowAnimationFrameStats()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IUiAutomationConnection");
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

        public boolean clearWindowContentFrameStats(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IUiAutomationConnection");
            parcel.writeInt(i);
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

        public void connect(IAccessibilityServiceClient iaccessibilityserviceclient, int i)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IUiAutomationConnection");
            if(iaccessibilityserviceclient == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = iaccessibilityserviceclient.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iaccessibilityserviceclient;
            parcel1.recycle();
            parcel.recycle();
            throw iaccessibilityserviceclient;
        }

        public void disconnect()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IUiAutomationConnection");
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

        public void executeShellCommand(String s, ParcelFileDescriptor parcelfiledescriptor, ParcelFileDescriptor parcelfiledescriptor1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IUiAutomationConnection");
            parcel.writeString(s);
            if(parcelfiledescriptor == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            parcelfiledescriptor.writeToParcel(parcel, 0);
_L3:
            if(parcelfiledescriptor1 == null)
                break MISSING_BLOCK_LABEL_112;
            parcel.writeInt(1);
            parcelfiledescriptor1.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
            parcel.writeInt(0);
              goto _L4
        }

        public String getInterfaceDescriptor()
        {
            return "android.app.IUiAutomationConnection";
        }

        public WindowAnimationFrameStats getWindowAnimationFrameStats()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IUiAutomationConnection");
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            WindowAnimationFrameStats windowanimationframestats = (WindowAnimationFrameStats)WindowAnimationFrameStats.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return windowanimationframestats;
_L2:
            windowanimationframestats = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public WindowContentFrameStats getWindowContentFrameStats(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IUiAutomationConnection");
            parcel.writeInt(i);
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            WindowContentFrameStats windowcontentframestats = (WindowContentFrameStats)WindowContentFrameStats.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return windowcontentframestats;
_L2:
            windowcontentframestats = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void grantRuntimePermission(String s, String s1, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IUiAutomationConnection");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(i);
            mRemote.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean injectInputEvent(InputEvent inputevent, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IUiAutomationConnection");
            if(inputevent == null)
                break MISSING_BLOCK_LABEL_91;
            parcel.writeInt(1);
            inputevent.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            parcel.writeInt(0);
              goto _L1
            inputevent;
            parcel1.recycle();
            parcel.recycle();
            throw inputevent;
        }

        public void revokeRuntimePermission(String s, String s1, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IUiAutomationConnection");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(i);
            mRemote.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean setRotation(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IUiAutomationConnection");
            parcel.writeInt(i);
            mRemote.transact(4, parcel, parcel1, 0);
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

        public void shutdown()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IUiAutomationConnection");
            mRemote.transact(13, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public Bitmap takeScreenshot(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IUiAutomationConnection");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(5, parcel, parcel1, 0);
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

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void clearWindowAnimationFrameStats()
        throws RemoteException;

    public abstract boolean clearWindowContentFrameStats(int i)
        throws RemoteException;

    public abstract void connect(IAccessibilityServiceClient iaccessibilityserviceclient, int i)
        throws RemoteException;

    public abstract void disconnect()
        throws RemoteException;

    public abstract void executeShellCommand(String s, ParcelFileDescriptor parcelfiledescriptor, ParcelFileDescriptor parcelfiledescriptor1)
        throws RemoteException;

    public abstract WindowAnimationFrameStats getWindowAnimationFrameStats()
        throws RemoteException;

    public abstract WindowContentFrameStats getWindowContentFrameStats(int i)
        throws RemoteException;

    public abstract void grantRuntimePermission(String s, String s1, int i)
        throws RemoteException;

    public abstract boolean injectInputEvent(InputEvent inputevent, boolean flag)
        throws RemoteException;

    public abstract void revokeRuntimePermission(String s, String s1, int i)
        throws RemoteException;

    public abstract boolean setRotation(int i)
        throws RemoteException;

    public abstract void shutdown()
        throws RemoteException;

    public abstract Bitmap takeScreenshot(int i, int j)
        throws RemoteException;
}
