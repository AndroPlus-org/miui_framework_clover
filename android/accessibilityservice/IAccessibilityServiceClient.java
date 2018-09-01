// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.accessibilityservice;

import android.graphics.Region;
import android.os.*;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;

// Referenced classes of package android.accessibilityservice:
//            IAccessibilityServiceConnection

public interface IAccessibilityServiceClient
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IAccessibilityServiceClient
    {

        public static IAccessibilityServiceClient asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.accessibilityservice.IAccessibilityServiceClient");
            if(iinterface != null && (iinterface instanceof IAccessibilityServiceClient))
                return (IAccessibilityServiceClient)iinterface;
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
                parcel1.writeString("android.accessibilityservice.IAccessibilityServiceClient");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.accessibilityservice.IAccessibilityServiceClient");
                init(IAccessibilityServiceConnection.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readStrongBinder());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.accessibilityservice.IAccessibilityServiceClient");
                boolean flag;
                if(parcel.readInt() != 0)
                    parcel1 = (AccessibilityEvent)AccessibilityEvent.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                onAccessibilityEvent(parcel1, flag);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.accessibilityservice.IAccessibilityServiceClient");
                onInterrupt();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.accessibilityservice.IAccessibilityServiceClient");
                onGesture(parcel.readInt());
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.accessibilityservice.IAccessibilityServiceClient");
                clearAccessibilityCache();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.accessibilityservice.IAccessibilityServiceClient");
                if(parcel.readInt() != 0)
                    parcel1 = (KeyEvent)KeyEvent.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                onKeyEvent(parcel1, parcel.readInt());
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.accessibilityservice.IAccessibilityServiceClient");
                if(parcel.readInt() != 0)
                    parcel1 = (Region)Region.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                onMagnificationChanged(parcel1, parcel.readFloat(), parcel.readFloat(), parcel.readFloat());
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.accessibilityservice.IAccessibilityServiceClient");
                onSoftKeyboardShowModeChanged(parcel.readInt());
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.accessibilityservice.IAccessibilityServiceClient");
                i = parcel.readInt();
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                onPerformGestureResult(i, flag1);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.accessibilityservice.IAccessibilityServiceClient");
                boolean flag2;
                if(parcel.readInt() != 0)
                    flag2 = true;
                else
                    flag2 = false;
                onFingerprintCapturingGesturesChanged(flag2);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.accessibilityservice.IAccessibilityServiceClient");
                onFingerprintGesture(parcel.readInt());
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.accessibilityservice.IAccessibilityServiceClient");
                onAccessibilityButtonClicked();
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.accessibilityservice.IAccessibilityServiceClient");
                break;
            }
            boolean flag3;
            if(parcel.readInt() != 0)
                flag3 = true;
            else
                flag3 = false;
            onAccessibilityButtonAvailabilityChanged(flag3);
            return true;
        }

        private static final String DESCRIPTOR = "android.accessibilityservice.IAccessibilityServiceClient";
        static final int TRANSACTION_clearAccessibilityCache = 5;
        static final int TRANSACTION_init = 1;
        static final int TRANSACTION_onAccessibilityButtonAvailabilityChanged = 13;
        static final int TRANSACTION_onAccessibilityButtonClicked = 12;
        static final int TRANSACTION_onAccessibilityEvent = 2;
        static final int TRANSACTION_onFingerprintCapturingGesturesChanged = 10;
        static final int TRANSACTION_onFingerprintGesture = 11;
        static final int TRANSACTION_onGesture = 4;
        static final int TRANSACTION_onInterrupt = 3;
        static final int TRANSACTION_onKeyEvent = 6;
        static final int TRANSACTION_onMagnificationChanged = 7;
        static final int TRANSACTION_onPerformGestureResult = 9;
        static final int TRANSACTION_onSoftKeyboardShowModeChanged = 8;

        public Stub()
        {
            attachInterface(this, "android.accessibilityservice.IAccessibilityServiceClient");
        }
    }

    private static class Stub.Proxy
        implements IAccessibilityServiceClient
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void clearAccessibilityCache()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "android.accessibilityservice.IAccessibilityServiceClient";
        }

        public void init(IAccessibilityServiceConnection iaccessibilityserviceconnection, int i, IBinder ibinder)
            throws RemoteException
        {
            IBinder ibinder1;
            Parcel parcel;
            ibinder1 = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
            if(iaccessibilityserviceconnection == null)
                break MISSING_BLOCK_LABEL_27;
            ibinder1 = iaccessibilityserviceconnection.asBinder();
            parcel.writeStrongBinder(ibinder1);
            parcel.writeInt(i);
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            iaccessibilityserviceconnection;
            parcel.recycle();
            throw iaccessibilityserviceconnection;
        }

        public void onAccessibilityButtonAvailabilityChanged(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(13, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onAccessibilityButtonClicked()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
            mRemote.transact(12, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onAccessibilityEvent(AccessibilityEvent accessibilityevent, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
            if(accessibilityevent == null)
                break MISSING_BLOCK_LABEL_62;
            parcel.writeInt(1);
            accessibilityevent.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            accessibilityevent;
            parcel.recycle();
            throw accessibilityevent;
        }

        public void onFingerprintCapturingGesturesChanged(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(10, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onFingerprintGesture(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
            parcel.writeInt(i);
            mRemote.transact(11, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onGesture(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
            parcel.writeInt(i);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onInterrupt()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onKeyEvent(KeyEvent keyevent, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
            if(keyevent == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            keyevent.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            keyevent;
            parcel.recycle();
            throw keyevent;
        }

        public void onMagnificationChanged(Region region, float f, float f1, float f2)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
            if(region == null)
                break MISSING_BLOCK_LABEL_70;
            parcel.writeInt(1);
            region.writeToParcel(parcel, 0);
_L1:
            parcel.writeFloat(f);
            parcel.writeFloat(f1);
            parcel.writeFloat(f2);
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            region;
            parcel.recycle();
            throw region;
        }

        public void onPerformGestureResult(int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
            parcel.writeInt(i);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(9, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onSoftKeyboardShowModeChanged(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
            parcel.writeInt(i);
            mRemote.transact(8, parcel, null, 1);
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


    public abstract void clearAccessibilityCache()
        throws RemoteException;

    public abstract void init(IAccessibilityServiceConnection iaccessibilityserviceconnection, int i, IBinder ibinder)
        throws RemoteException;

    public abstract void onAccessibilityButtonAvailabilityChanged(boolean flag)
        throws RemoteException;

    public abstract void onAccessibilityButtonClicked()
        throws RemoteException;

    public abstract void onAccessibilityEvent(AccessibilityEvent accessibilityevent, boolean flag)
        throws RemoteException;

    public abstract void onFingerprintCapturingGesturesChanged(boolean flag)
        throws RemoteException;

    public abstract void onFingerprintGesture(int i)
        throws RemoteException;

    public abstract void onGesture(int i)
        throws RemoteException;

    public abstract void onInterrupt()
        throws RemoteException;

    public abstract void onKeyEvent(KeyEvent keyevent, int i)
        throws RemoteException;

    public abstract void onMagnificationChanged(Region region, float f, float f1, float f2)
        throws RemoteException;

    public abstract void onPerformGestureResult(int i, boolean flag)
        throws RemoteException;

    public abstract void onSoftKeyboardShowModeChanged(int i)
        throws RemoteException;
}
