// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.graphics.Rect;
import android.os.*;
import android.util.MergedConfiguration;
import com.android.internal.os.IResultReceiver;

// Referenced classes of package android.view:
//            DragEvent

public interface IWindow
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IWindow
    {

        public static IWindow asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.view.IWindow");
            if(iinterface != null && (iinterface instanceof IWindow))
                return (IWindow)iinterface;
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
                parcel1.writeString("android.view.IWindow");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.view.IWindow");
                parcel1 = parcel.readString();
                String s = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                executeCommand(parcel1, s, parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.view.IWindow");
                Rect rect;
                Rect rect1;
                Rect rect2;
                Rect rect3;
                Rect rect4;
                boolean flag;
                MergedConfiguration mergedconfiguration;
                Rect rect5;
                boolean flag6;
                boolean flag8;
                if(parcel.readInt() != 0)
                    parcel1 = (Rect)Rect.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    rect = (Rect)Rect.CREATOR.createFromParcel(parcel);
                else
                    rect = null;
                if(parcel.readInt() != 0)
                    rect1 = (Rect)Rect.CREATOR.createFromParcel(parcel);
                else
                    rect1 = null;
                if(parcel.readInt() != 0)
                    rect2 = (Rect)Rect.CREATOR.createFromParcel(parcel);
                else
                    rect2 = null;
                if(parcel.readInt() != 0)
                    rect3 = (Rect)Rect.CREATOR.createFromParcel(parcel);
                else
                    rect3 = null;
                if(parcel.readInt() != 0)
                    rect4 = (Rect)Rect.CREATOR.createFromParcel(parcel);
                else
                    rect4 = null;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                if(parcel.readInt() != 0)
                    mergedconfiguration = (MergedConfiguration)MergedConfiguration.CREATOR.createFromParcel(parcel);
                else
                    mergedconfiguration = null;
                if(parcel.readInt() != 0)
                    rect5 = (Rect)Rect.CREATOR.createFromParcel(parcel);
                else
                    rect5 = null;
                if(parcel.readInt() != 0)
                    flag6 = true;
                else
                    flag6 = false;
                if(parcel.readInt() != 0)
                    flag8 = true;
                else
                    flag8 = false;
                resized(parcel1, rect, rect1, rect2, rect3, rect4, flag, mergedconfiguration, rect5, flag6, flag8, parcel.readInt());
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.view.IWindow");
                moved(parcel.readInt(), parcel.readInt());
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.view.IWindow");
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                dispatchAppVisibility(flag1);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.view.IWindow");
                dispatchGetNewSurface();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.view.IWindow");
                boolean flag2;
                boolean flag7;
                if(parcel.readInt() != 0)
                    flag2 = true;
                else
                    flag2 = false;
                if(parcel.readInt() != 0)
                    flag7 = true;
                else
                    flag7 = false;
                windowFocusChanged(flag2, flag7);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.view.IWindow");
                closeSystemDialogs(parcel.readString());
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.view.IWindow");
                float f = parcel.readFloat();
                float f1 = parcel.readFloat();
                float f2 = parcel.readFloat();
                float f3 = parcel.readFloat();
                boolean flag3;
                if(parcel.readInt() != 0)
                    flag3 = true;
                else
                    flag3 = false;
                dispatchWallpaperOffsets(f, f1, f2, f3, flag3);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.view.IWindow");
                String s1 = parcel.readString();
                i = parcel.readInt();
                j = parcel.readInt();
                int k = parcel.readInt();
                boolean flag4;
                if(parcel.readInt() != 0)
                    parcel1 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    flag4 = true;
                else
                    flag4 = false;
                dispatchWallpaperCommand(s1, i, j, k, parcel1, flag4);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.view.IWindow");
                if(parcel.readInt() != 0)
                    parcel = (DragEvent)DragEvent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                dispatchDragEvent(parcel);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.view.IWindow");
                updatePointerIcon(parcel.readFloat(), parcel.readFloat());
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.view.IWindow");
                dispatchSystemUiVisibilityChanged(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.view.IWindow");
                dispatchWindowShown();
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.view.IWindow");
                requestAppKeyboardShortcuts(com.android.internal.os.IResultReceiver.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                return true;

            case 15: // '\017'
                parcel.enforceInterface("android.view.IWindow");
                break;
            }
            boolean flag5;
            if(parcel.readInt() != 0)
                flag5 = true;
            else
                flag5 = false;
            dispatchPointerCaptureChanged(flag5);
            return true;
        }

        private static final String DESCRIPTOR = "android.view.IWindow";
        static final int TRANSACTION_closeSystemDialogs = 7;
        static final int TRANSACTION_dispatchAppVisibility = 4;
        static final int TRANSACTION_dispatchDragEvent = 10;
        static final int TRANSACTION_dispatchGetNewSurface = 5;
        static final int TRANSACTION_dispatchPointerCaptureChanged = 15;
        static final int TRANSACTION_dispatchSystemUiVisibilityChanged = 12;
        static final int TRANSACTION_dispatchWallpaperCommand = 9;
        static final int TRANSACTION_dispatchWallpaperOffsets = 8;
        static final int TRANSACTION_dispatchWindowShown = 13;
        static final int TRANSACTION_executeCommand = 1;
        static final int TRANSACTION_moved = 3;
        static final int TRANSACTION_requestAppKeyboardShortcuts = 14;
        static final int TRANSACTION_resized = 2;
        static final int TRANSACTION_updatePointerIcon = 11;
        static final int TRANSACTION_windowFocusChanged = 6;

        public Stub()
        {
            attachInterface(this, "android.view.IWindow");
        }
    }

    private static class Stub.Proxy
        implements IWindow
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void closeSystemDialogs(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindow");
            parcel.writeString(s);
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void dispatchAppVisibility(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindow");
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

        public void dispatchDragEvent(DragEvent dragevent)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindow");
            if(dragevent == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            dragevent.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(10, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            dragevent;
            parcel.recycle();
            throw dragevent;
        }

        public void dispatchGetNewSurface()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindow");
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void dispatchPointerCaptureChanged(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindow");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(15, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void dispatchSystemUiVisibilityChanged(int i, int j, int k, int l)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindow");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeInt(l);
            mRemote.transact(12, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void dispatchWallpaperCommand(String s, int i, int j, int k, Bundle bundle, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindow");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_95;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(9, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void dispatchWallpaperOffsets(float f, float f1, float f2, float f3, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindow");
            parcel.writeFloat(f);
            parcel.writeFloat(f1);
            parcel.writeFloat(f2);
            parcel.writeFloat(f3);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(8, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void dispatchWindowShown()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindow");
            mRemote.transact(13, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void executeCommand(String s, String s1, ParcelFileDescriptor parcelfiledescriptor)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindow");
            parcel.writeString(s);
            parcel.writeString(s1);
            if(parcelfiledescriptor == null)
                break MISSING_BLOCK_LABEL_62;
            parcel.writeInt(1);
            parcelfiledescriptor.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public String getInterfaceDescriptor()
        {
            return "android.view.IWindow";
        }

        public void moved(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindow");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void requestAppKeyboardShortcuts(IResultReceiver iresultreceiver, int i)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindow");
            if(iresultreceiver == null)
                break MISSING_BLOCK_LABEL_25;
            ibinder = iresultreceiver.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(14, parcel, null, 1);
            parcel.recycle();
            return;
            iresultreceiver;
            parcel.recycle();
            throw iresultreceiver;
        }

        public void resized(Rect rect, Rect rect1, Rect rect2, Rect rect3, Rect rect4, Rect rect5, boolean flag, 
                MergedConfiguration mergedconfiguration, Rect rect6, boolean flag1, boolean flag2, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindow");
            if(rect == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            rect.writeToParcel(parcel, 0);
_L15:
            if(rect1 == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            rect1.writeToParcel(parcel, 0);
_L16:
            if(rect2 == null) goto _L6; else goto _L5
_L5:
            parcel.writeInt(1);
            rect2.writeToParcel(parcel, 0);
_L17:
            if(rect3 == null) goto _L8; else goto _L7
_L7:
            parcel.writeInt(1);
            rect3.writeToParcel(parcel, 0);
_L18:
            if(rect4 == null) goto _L10; else goto _L9
_L9:
            parcel.writeInt(1);
            rect4.writeToParcel(parcel, 0);
_L19:
            if(rect5 == null) goto _L12; else goto _L11
_L11:
            parcel.writeInt(1);
            rect5.writeToParcel(parcel, 0);
_L20:
            int j;
            if(flag)
                j = 1;
            else
                j = 0;
            parcel.writeInt(j);
            if(mergedconfiguration == null) goto _L14; else goto _L13
_L13:
            parcel.writeInt(1);
            mergedconfiguration.writeToParcel(parcel, 0);
_L21:
            if(rect6 == null)
                break MISSING_BLOCK_LABEL_308;
            parcel.writeInt(1);
            rect6.writeToParcel(parcel, 0);
_L22:
            if(flag1)
                j = 1;
            else
                j = 0;
            parcel.writeInt(j);
            if(flag2)
                j = 1;
            else
                j = 0;
            parcel.writeInt(j);
            parcel.writeInt(i);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L15
            rect;
            parcel.recycle();
            throw rect;
_L4:
            parcel.writeInt(0);
              goto _L16
_L6:
            parcel.writeInt(0);
              goto _L17
_L8:
            parcel.writeInt(0);
              goto _L18
_L10:
            parcel.writeInt(0);
              goto _L19
_L12:
            parcel.writeInt(0);
              goto _L20
_L14:
            parcel.writeInt(0);
              goto _L21
            parcel.writeInt(0);
              goto _L22
        }

        public void updatePointerIcon(float f, float f1)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindow");
            parcel.writeFloat(f);
            parcel.writeFloat(f1);
            mRemote.transact(11, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void windowFocusChanged(boolean flag, boolean flag1)
            throws RemoteException
        {
            boolean flag2;
            Parcel parcel;
            flag2 = true;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindow");
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
            mRemote.transact(6, parcel, null, 1);
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


    public abstract void closeSystemDialogs(String s)
        throws RemoteException;

    public abstract void dispatchAppVisibility(boolean flag)
        throws RemoteException;

    public abstract void dispatchDragEvent(DragEvent dragevent)
        throws RemoteException;

    public abstract void dispatchGetNewSurface()
        throws RemoteException;

    public abstract void dispatchPointerCaptureChanged(boolean flag)
        throws RemoteException;

    public abstract void dispatchSystemUiVisibilityChanged(int i, int j, int k, int l)
        throws RemoteException;

    public abstract void dispatchWallpaperCommand(String s, int i, int j, int k, Bundle bundle, boolean flag)
        throws RemoteException;

    public abstract void dispatchWallpaperOffsets(float f, float f1, float f2, float f3, boolean flag)
        throws RemoteException;

    public abstract void dispatchWindowShown()
        throws RemoteException;

    public abstract void executeCommand(String s, String s1, ParcelFileDescriptor parcelfiledescriptor)
        throws RemoteException;

    public abstract void moved(int i, int j)
        throws RemoteException;

    public abstract void requestAppKeyboardShortcuts(IResultReceiver iresultreceiver, int i)
        throws RemoteException;

    public abstract void resized(Rect rect, Rect rect1, Rect rect2, Rect rect3, Rect rect4, Rect rect5, boolean flag, 
            MergedConfiguration mergedconfiguration, Rect rect6, boolean flag1, boolean flag2, int i)
        throws RemoteException;

    public abstract void updatePointerIcon(float f, float f1)
        throws RemoteException;

    public abstract void windowFocusChanged(boolean flag, boolean flag1)
        throws RemoteException;
}
