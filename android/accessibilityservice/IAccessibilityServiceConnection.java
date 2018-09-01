// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.accessibilityservice;

import android.content.pm.ParceledListSlice;
import android.graphics.Region;
import android.os.*;
import android.view.accessibility.AccessibilityWindowInfo;
import android.view.accessibility.IAccessibilityInteractionConnectionCallback;
import java.util.List;

// Referenced classes of package android.accessibilityservice:
//            AccessibilityServiceInfo

public interface IAccessibilityServiceConnection
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IAccessibilityServiceConnection
    {

        public static IAccessibilityServiceConnection asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.accessibilityservice.IAccessibilityServiceConnection");
            if(iinterface != null && (iinterface instanceof IAccessibilityServiceConnection))
                return (IAccessibilityServiceConnection)iinterface;
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
            boolean flag14;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.accessibilityservice.IAccessibilityServiceConnection");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
                if(parcel.readInt() != 0)
                    parcel = (AccessibilityServiceInfo)AccessibilityServiceInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setServiceInfo(parcel);
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
                j = parcel.readInt();
                long l = parcel.readLong();
                int k = parcel.readInt();
                IAccessibilityInteractionConnectionCallback iaccessibilityinteractionconnectioncallback = android.view.accessibility.IAccessibilityInteractionConnectionCallback.Stub.asInterface(parcel.readStrongBinder());
                i = parcel.readInt();
                long l2 = parcel.readLong();
                boolean flag;
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag = findAccessibilityNodeInfoByAccessibilityId(j, l, k, iaccessibilityinteractionconnectioncallback, i, l2, parcel);
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
                boolean flag1 = findAccessibilityNodeInfosByText(parcel.readInt(), parcel.readLong(), parcel.readString(), parcel.readInt(), android.view.accessibility.IAccessibilityInteractionConnectionCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readLong());
                parcel1.writeNoException();
                if(flag1)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
                boolean flag2 = findAccessibilityNodeInfosByViewId(parcel.readInt(), parcel.readLong(), parcel.readString(), parcel.readInt(), android.view.accessibility.IAccessibilityInteractionConnectionCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readLong());
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
                boolean flag3 = findFocus(parcel.readInt(), parcel.readLong(), parcel.readInt(), parcel.readInt(), android.view.accessibility.IAccessibilityInteractionConnectionCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readLong());
                parcel1.writeNoException();
                if(flag3)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
                boolean flag4 = focusSearch(parcel.readInt(), parcel.readLong(), parcel.readInt(), parcel.readInt(), android.view.accessibility.IAccessibilityInteractionConnectionCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readLong());
                parcel1.writeNoException();
                if(flag4)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
                j = parcel.readInt();
                long l1 = parcel.readLong();
                i = parcel.readInt();
                Bundle bundle;
                boolean flag5;
                if(parcel.readInt() != 0)
                    bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle = null;
                flag5 = performAccessibilityAction(j, l1, i, bundle, parcel.readInt(), android.view.accessibility.IAccessibilityInteractionConnectionCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readLong());
                parcel1.writeNoException();
                if(flag5)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
                parcel = getWindow(parcel.readInt());
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

            case 9: // '\t'
                parcel.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
                parcel = getWindows();
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
                parcel = getServiceInfo();
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

            case 11: // '\013'
                parcel.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
                boolean flag6 = performGlobalAction(parcel.readInt());
                parcel1.writeNoException();
                if(flag6)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
                disableSelf();
                parcel1.writeNoException();
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
                boolean flag7;
                if(parcel.readInt() != 0)
                    flag7 = true;
                else
                    flag7 = false;
                setOnKeyEventResult(flag7, parcel.readInt());
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
                float f = getMagnificationScale();
                parcel1.writeNoException();
                parcel1.writeFloat(f);
                return true;

            case 15: // '\017'
                parcel.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
                float f1 = getMagnificationCenterX();
                parcel1.writeNoException();
                parcel1.writeFloat(f1);
                return true;

            case 16: // '\020'
                parcel.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
                float f2 = getMagnificationCenterY();
                parcel1.writeNoException();
                parcel1.writeFloat(f2);
                return true;

            case 17: // '\021'
                parcel.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
                parcel = getMagnificationRegion();
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

            case 18: // '\022'
                parcel.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
                boolean flag8;
                if(parcel.readInt() != 0)
                    flag8 = true;
                else
                    flag8 = false;
                flag8 = resetMagnification(flag8);
                parcel1.writeNoException();
                if(flag8)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 19: // '\023'
                parcel.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
                float f4 = parcel.readFloat();
                float f5 = parcel.readFloat();
                float f3 = parcel.readFloat();
                boolean flag9;
                if(parcel.readInt() != 0)
                    flag9 = true;
                else
                    flag9 = false;
                flag9 = setMagnificationScaleAndCenter(f4, f5, f3, flag9);
                parcel1.writeNoException();
                if(flag9)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 20: // '\024'
                parcel.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
                boolean flag10;
                if(parcel.readInt() != 0)
                    flag10 = true;
                else
                    flag10 = false;
                setMagnificationCallbackEnabled(flag10);
                parcel1.writeNoException();
                return true;

            case 21: // '\025'
                parcel.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
                boolean flag11 = setSoftKeyboardShowMode(parcel.readInt());
                parcel1.writeNoException();
                if(flag11)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 22: // '\026'
                parcel.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
                boolean flag12;
                if(parcel.readInt() != 0)
                    flag12 = true;
                else
                    flag12 = false;
                setSoftKeyboardCallbackEnabled(flag12);
                parcel1.writeNoException();
                return true;

            case 23: // '\027'
                parcel.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
                boolean flag13 = isAccessibilityButtonAvailable();
                parcel1.writeNoException();
                if(flag13)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 24: // '\030'
                parcel.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                sendGesture(i, parcel);
                parcel1.writeNoException();
                return true;

            case 25: // '\031'
                parcel.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
                flag14 = isFingerprintGestureDetectionAvailable();
                parcel1.writeNoException();
                break;
            }
            if(flag14)
                i = 1;
            else
                i = 0;
            parcel1.writeInt(i);
            return true;
        }

        private static final String DESCRIPTOR = "android.accessibilityservice.IAccessibilityServiceConnection";
        static final int TRANSACTION_disableSelf = 12;
        static final int TRANSACTION_findAccessibilityNodeInfoByAccessibilityId = 2;
        static final int TRANSACTION_findAccessibilityNodeInfosByText = 3;
        static final int TRANSACTION_findAccessibilityNodeInfosByViewId = 4;
        static final int TRANSACTION_findFocus = 5;
        static final int TRANSACTION_focusSearch = 6;
        static final int TRANSACTION_getMagnificationCenterX = 15;
        static final int TRANSACTION_getMagnificationCenterY = 16;
        static final int TRANSACTION_getMagnificationRegion = 17;
        static final int TRANSACTION_getMagnificationScale = 14;
        static final int TRANSACTION_getServiceInfo = 10;
        static final int TRANSACTION_getWindow = 8;
        static final int TRANSACTION_getWindows = 9;
        static final int TRANSACTION_isAccessibilityButtonAvailable = 23;
        static final int TRANSACTION_isFingerprintGestureDetectionAvailable = 25;
        static final int TRANSACTION_performAccessibilityAction = 7;
        static final int TRANSACTION_performGlobalAction = 11;
        static final int TRANSACTION_resetMagnification = 18;
        static final int TRANSACTION_sendGesture = 24;
        static final int TRANSACTION_setMagnificationCallbackEnabled = 20;
        static final int TRANSACTION_setMagnificationScaleAndCenter = 19;
        static final int TRANSACTION_setOnKeyEventResult = 13;
        static final int TRANSACTION_setServiceInfo = 1;
        static final int TRANSACTION_setSoftKeyboardCallbackEnabled = 22;
        static final int TRANSACTION_setSoftKeyboardShowMode = 21;

        public Stub()
        {
            attachInterface(this, "android.accessibilityservice.IAccessibilityServiceConnection");
        }
    }

    private static class Stub.Proxy
        implements IAccessibilityServiceConnection
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void disableSelf()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
            mRemote.transact(12, parcel, parcel1, 0);
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

        public boolean findAccessibilityNodeInfoByAccessibilityId(int i, long l, int j, IAccessibilityInteractionConnectionCallback iaccessibilityinteractionconnectioncallback, int k, long l1, Bundle bundle)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
            parcel.writeInt(i);
            parcel.writeLong(l);
            parcel.writeInt(j);
            if(iaccessibilityinteractionconnectioncallback == null)
                break MISSING_BLOCK_LABEL_53;
            ibinder = iaccessibilityinteractionconnectioncallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(k);
            parcel.writeLong(l1);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_140;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
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
            parcel.writeInt(0);
              goto _L1
            iaccessibilityinteractionconnectioncallback;
            parcel1.recycle();
            parcel.recycle();
            throw iaccessibilityinteractionconnectioncallback;
        }

        public boolean findAccessibilityNodeInfosByText(int i, long l, String s, int j, IAccessibilityInteractionConnectionCallback iaccessibilityinteractionconnectioncallback, long l1)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
            parcel.writeInt(i);
            parcel.writeLong(l);
            parcel.writeString(s);
            parcel.writeInt(j);
            s = obj;
            if(iaccessibilityinteractionconnectioncallback == null)
                break MISSING_BLOCK_LABEL_64;
            s = iaccessibilityinteractionconnectioncallback.asBinder();
            parcel.writeStrongBinder(s);
            parcel.writeLong(l1);
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
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean findAccessibilityNodeInfosByViewId(int i, long l, String s, int j, IAccessibilityInteractionConnectionCallback iaccessibilityinteractionconnectioncallback, long l1)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
            parcel.writeInt(i);
            parcel.writeLong(l);
            parcel.writeString(s);
            parcel.writeInt(j);
            s = obj;
            if(iaccessibilityinteractionconnectioncallback == null)
                break MISSING_BLOCK_LABEL_64;
            s = iaccessibilityinteractionconnectioncallback.asBinder();
            parcel.writeStrongBinder(s);
            parcel.writeLong(l1);
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
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean findFocus(int i, long l, int j, int k, IAccessibilityInteractionConnectionCallback iaccessibilityinteractionconnectioncallback, long l1)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
            parcel.writeInt(i);
            parcel.writeLong(l);
            parcel.writeInt(j);
            parcel.writeInt(k);
            if(iaccessibilityinteractionconnectioncallback == null)
                break MISSING_BLOCK_LABEL_60;
            ibinder = iaccessibilityinteractionconnectioncallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeLong(l1);
            mRemote.transact(5, parcel, parcel1, 0);
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
            iaccessibilityinteractionconnectioncallback;
            parcel1.recycle();
            parcel.recycle();
            throw iaccessibilityinteractionconnectioncallback;
        }

        public boolean focusSearch(int i, long l, int j, int k, IAccessibilityInteractionConnectionCallback iaccessibilityinteractionconnectioncallback, long l1)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
            parcel.writeInt(i);
            parcel.writeLong(l);
            parcel.writeInt(j);
            parcel.writeInt(k);
            if(iaccessibilityinteractionconnectioncallback == null)
                break MISSING_BLOCK_LABEL_60;
            ibinder = iaccessibilityinteractionconnectioncallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeLong(l1);
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
            iaccessibilityinteractionconnectioncallback;
            parcel1.recycle();
            parcel.recycle();
            throw iaccessibilityinteractionconnectioncallback;
        }

        public String getInterfaceDescriptor()
        {
            return "android.accessibilityservice.IAccessibilityServiceConnection";
        }

        public float getMagnificationCenterX()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            float f;
            parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
            mRemote.transact(15, parcel, parcel1, 0);
            parcel1.readException();
            f = parcel1.readFloat();
            parcel1.recycle();
            parcel.recycle();
            return f;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public float getMagnificationCenterY()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            float f;
            parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
            mRemote.transact(16, parcel, parcel1, 0);
            parcel1.readException();
            f = parcel1.readFloat();
            parcel1.recycle();
            parcel.recycle();
            return f;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public Region getMagnificationRegion()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
            mRemote.transact(17, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Region region = (Region)Region.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return region;
_L2:
            region = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public float getMagnificationScale()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            float f;
            parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
            mRemote.transact(14, parcel, parcel1, 0);
            parcel1.readException();
            f = parcel1.readFloat();
            parcel1.recycle();
            parcel.recycle();
            return f;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public AccessibilityServiceInfo getServiceInfo()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            AccessibilityServiceInfo accessibilityserviceinfo = (AccessibilityServiceInfo)AccessibilityServiceInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return accessibilityserviceinfo;
_L2:
            accessibilityserviceinfo = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public AccessibilityWindowInfo getWindow(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
            parcel.writeInt(i);
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            AccessibilityWindowInfo accessibilitywindowinfo = (AccessibilityWindowInfo)AccessibilityWindowInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return accessibilitywindowinfo;
_L2:
            accessibilitywindowinfo = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public List getWindows()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createTypedArrayList(AccessibilityWindowInfo.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isAccessibilityButtonAvailable()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
            mRemote.transact(23, parcel, parcel1, 0);
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

        public boolean isFingerprintGestureDetectionAvailable()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
            mRemote.transact(25, parcel, parcel1, 0);
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

        public boolean performAccessibilityAction(int i, long l, int j, Bundle bundle, int k, IAccessibilityInteractionConnectionCallback iaccessibilityinteractionconnectioncallback, 
                long l1)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
            parcel.writeInt(i);
            parcel.writeLong(l);
            parcel.writeInt(j);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_145;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(k);
            bundle = obj;
            if(iaccessibilityinteractionconnectioncallback == null)
                break MISSING_BLOCK_LABEL_83;
            bundle = iaccessibilityinteractionconnectioncallback.asBinder();
            parcel.writeStrongBinder(bundle);
            parcel.writeLong(l1);
            mRemote.transact(7, parcel, parcel1, 0);
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
            bundle;
            parcel1.recycle();
            parcel.recycle();
            throw bundle;
        }

        public boolean performGlobalAction(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
            parcel.writeInt(i);
            mRemote.transact(11, parcel, parcel1, 0);
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

        public boolean resetMagnification(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(18, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
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

        public void sendGesture(int i, ParceledListSlice parceledlistslice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
            parcel.writeInt(i);
            if(parceledlistslice == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            parceledlistslice.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(24, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            parceledlistslice;
            parcel1.recycle();
            parcel.recycle();
            throw parceledlistslice;
        }

        public void setMagnificationCallbackEnabled(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(20, parcel, parcel1, 0);
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

        public boolean setMagnificationScaleAndCenter(float f, float f1, float f2, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
            parcel.writeFloat(f);
            parcel.writeFloat(f1);
            parcel.writeFloat(f2);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(19, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
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

        public void setOnKeyEventResult(boolean flag, int i)
            throws RemoteException
        {
            int j;
            Parcel parcel;
            j = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
            if(!flag)
                j = 0;
            parcel.writeInt(j);
            parcel.writeInt(i);
            mRemote.transact(13, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void setServiceInfo(AccessibilityServiceInfo accessibilityserviceinfo)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
            if(accessibilityserviceinfo == null)
                break MISSING_BLOCK_LABEL_56;
            parcel.writeInt(1);
            accessibilityserviceinfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            accessibilityserviceinfo;
            parcel1.recycle();
            parcel.recycle();
            throw accessibilityserviceinfo;
        }

        public void setSoftKeyboardCallbackEnabled(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(22, parcel, parcel1, 0);
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

        public boolean setSoftKeyboardShowMode(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
            parcel.writeInt(i);
            mRemote.transact(21, parcel, parcel1, 0);
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

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void disableSelf()
        throws RemoteException;

    public abstract boolean findAccessibilityNodeInfoByAccessibilityId(int i, long l, int j, IAccessibilityInteractionConnectionCallback iaccessibilityinteractionconnectioncallback, int k, long l1, Bundle bundle)
        throws RemoteException;

    public abstract boolean findAccessibilityNodeInfosByText(int i, long l, String s, int j, IAccessibilityInteractionConnectionCallback iaccessibilityinteractionconnectioncallback, long l1)
        throws RemoteException;

    public abstract boolean findAccessibilityNodeInfosByViewId(int i, long l, String s, int j, IAccessibilityInteractionConnectionCallback iaccessibilityinteractionconnectioncallback, long l1)
        throws RemoteException;

    public abstract boolean findFocus(int i, long l, int j, int k, IAccessibilityInteractionConnectionCallback iaccessibilityinteractionconnectioncallback, long l1)
        throws RemoteException;

    public abstract boolean focusSearch(int i, long l, int j, int k, IAccessibilityInteractionConnectionCallback iaccessibilityinteractionconnectioncallback, long l1)
        throws RemoteException;

    public abstract float getMagnificationCenterX()
        throws RemoteException;

    public abstract float getMagnificationCenterY()
        throws RemoteException;

    public abstract Region getMagnificationRegion()
        throws RemoteException;

    public abstract float getMagnificationScale()
        throws RemoteException;

    public abstract AccessibilityServiceInfo getServiceInfo()
        throws RemoteException;

    public abstract AccessibilityWindowInfo getWindow(int i)
        throws RemoteException;

    public abstract List getWindows()
        throws RemoteException;

    public abstract boolean isAccessibilityButtonAvailable()
        throws RemoteException;

    public abstract boolean isFingerprintGestureDetectionAvailable()
        throws RemoteException;

    public abstract boolean performAccessibilityAction(int i, long l, int j, Bundle bundle, int k, IAccessibilityInteractionConnectionCallback iaccessibilityinteractionconnectioncallback, 
            long l1)
        throws RemoteException;

    public abstract boolean performGlobalAction(int i)
        throws RemoteException;

    public abstract boolean resetMagnification(boolean flag)
        throws RemoteException;

    public abstract void sendGesture(int i, ParceledListSlice parceledlistslice)
        throws RemoteException;

    public abstract void setMagnificationCallbackEnabled(boolean flag)
        throws RemoteException;

    public abstract boolean setMagnificationScaleAndCenter(float f, float f1, float f2, boolean flag)
        throws RemoteException;

    public abstract void setOnKeyEventResult(boolean flag, int i)
        throws RemoteException;

    public abstract void setServiceInfo(AccessibilityServiceInfo accessibilityserviceinfo)
        throws RemoteException;

    public abstract void setSoftKeyboardCallbackEnabled(boolean flag)
        throws RemoteException;

    public abstract boolean setSoftKeyboardShowMode(int i)
        throws RemoteException;
}
