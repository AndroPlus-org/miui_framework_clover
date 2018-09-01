// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.content.pm.ParceledListSlice;
import android.graphics.Rect;
import android.os.*;

// Referenced classes of package android.view:
//            IPinnedStackController

public interface IPinnedStackListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IPinnedStackListener
    {

        public static IPinnedStackListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.view.IPinnedStackListener");
            if(iinterface != null && (iinterface instanceof IPinnedStackListener))
                return (IPinnedStackListener)iinterface;
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
                parcel1.writeString("android.view.IPinnedStackListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.view.IPinnedStackListener");
                onListenerRegistered(IPinnedStackController.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.view.IPinnedStackListener");
                Rect rect;
                Rect rect1;
                boolean flag;
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
                    flag = true;
                else
                    flag = false;
                onMovementBoundsChanged(parcel1, rect, rect1, flag, parcel.readInt());
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.view.IPinnedStackListener");
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                onImeVisibilityChanged(flag1, parcel.readInt());
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.view.IPinnedStackListener");
                boolean flag2;
                if(parcel.readInt() != 0)
                    flag2 = true;
                else
                    flag2 = false;
                onMinimizedStateChanged(flag2);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.view.IPinnedStackListener");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            onActionsChanged(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.view.IPinnedStackListener";
        static final int TRANSACTION_onActionsChanged = 5;
        static final int TRANSACTION_onImeVisibilityChanged = 3;
        static final int TRANSACTION_onListenerRegistered = 1;
        static final int TRANSACTION_onMinimizedStateChanged = 4;
        static final int TRANSACTION_onMovementBoundsChanged = 2;

        public Stub()
        {
            attachInterface(this, "android.view.IPinnedStackListener");
        }
    }

    private static class Stub.Proxy
        implements IPinnedStackListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.view.IPinnedStackListener";
        }

        public void onActionsChanged(ParceledListSlice parceledlistslice)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IPinnedStackListener");
            if(parceledlistslice == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            parceledlistslice.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            parceledlistslice;
            parcel.recycle();
            throw parceledlistslice;
        }

        public void onImeVisibilityChanged(boolean flag, int i)
            throws RemoteException
        {
            int j;
            Parcel parcel;
            j = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IPinnedStackListener");
            if(!flag)
                j = 0;
            parcel.writeInt(j);
            parcel.writeInt(i);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onListenerRegistered(IPinnedStackController ipinnedstackcontroller)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IPinnedStackListener");
            if(ipinnedstackcontroller == null)
                break MISSING_BLOCK_LABEL_23;
            ibinder = ipinnedstackcontroller.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            ipinnedstackcontroller;
            parcel.recycle();
            throw ipinnedstackcontroller;
        }

        public void onMinimizedStateChanged(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IPinnedStackListener");
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

        public void onMovementBoundsChanged(Rect rect, Rect rect1, Rect rect2, boolean flag, int i)
            throws RemoteException
        {
            int j;
            Parcel parcel;
            j = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IPinnedStackListener");
            if(rect == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            rect.writeToParcel(parcel, 0);
_L5:
            if(rect1 == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            rect1.writeToParcel(parcel, 0);
_L6:
            if(rect2 == null)
                break MISSING_BLOCK_LABEL_132;
            parcel.writeInt(1);
            rect2.writeToParcel(parcel, 0);
_L7:
            if(!flag)
                j = 0;
            parcel.writeInt(j);
            parcel.writeInt(i);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L5
            rect;
            parcel.recycle();
            throw rect;
_L4:
            parcel.writeInt(0);
              goto _L6
            parcel.writeInt(0);
              goto _L7
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onActionsChanged(ParceledListSlice parceledlistslice)
        throws RemoteException;

    public abstract void onImeVisibilityChanged(boolean flag, int i)
        throws RemoteException;

    public abstract void onListenerRegistered(IPinnedStackController ipinnedstackcontroller)
        throws RemoteException;

    public abstract void onMinimizedStateChanged(boolean flag)
        throws RemoteException;

    public abstract void onMovementBoundsChanged(Rect rect, Rect rect1, Rect rect2, boolean flag, int i)
        throws RemoteException;
}
