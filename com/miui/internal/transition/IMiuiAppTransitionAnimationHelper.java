// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.internal.transition;

import android.os.*;

// Referenced classes of package com.miui.internal.transition:
//            MiuiAppTransitionAnimationSpec

public interface IMiuiAppTransitionAnimationHelper
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IMiuiAppTransitionAnimationHelper
    {

        public static IMiuiAppTransitionAnimationHelper asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.miui.internal.transition.IMiuiAppTransitionAnimationHelper");
            if(iinterface != null && (iinterface instanceof IMiuiAppTransitionAnimationHelper))
                return (IMiuiAppTransitionAnimationHelper)iinterface;
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
                parcel1.writeString("com.miui.internal.transition.IMiuiAppTransitionAnimationHelper");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.miui.internal.transition.IMiuiAppTransitionAnimationHelper");
                parcel = getSpec(parcel.readString(), parcel.readInt());
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
                parcel.enforceInterface("com.miui.internal.transition.IMiuiAppTransitionAnimationHelper");
                notifyMiuiAnimationStart();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.miui.internal.transition.IMiuiAppTransitionAnimationHelper");
                notifyMiuiAnimationEnd();
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.miui.internal.transition.IMiuiAppTransitionAnimationHelper";
        static final int TRANSACTION_getSpec = 1;
        static final int TRANSACTION_notifyMiuiAnimationEnd = 3;
        static final int TRANSACTION_notifyMiuiAnimationStart = 2;

        public Stub()
        {
            attachInterface(this, "com.miui.internal.transition.IMiuiAppTransitionAnimationHelper");
        }
    }

    private static class Stub.Proxy
        implements IMiuiAppTransitionAnimationHelper
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.miui.internal.transition.IMiuiAppTransitionAnimationHelper";
        }

        public MiuiAppTransitionAnimationSpec getSpec(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.internal.transition.IMiuiAppTransitionAnimationHelper");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (MiuiAppTransitionAnimationSpec)MiuiAppTransitionAnimationSpec.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            s = null;
            if(true) goto _L4; else goto _L3
_L3:
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void notifyMiuiAnimationEnd()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.internal.transition.IMiuiAppTransitionAnimationHelper");
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void notifyMiuiAnimationStart()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.internal.transition.IMiuiAppTransitionAnimationHelper");
            mRemote.transact(2, parcel, null, 1);
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


    public abstract MiuiAppTransitionAnimationSpec getSpec(String s, int i)
        throws RemoteException;

    public abstract void notifyMiuiAnimationEnd()
        throws RemoteException;

    public abstract void notifyMiuiAnimationStart()
        throws RemoteException;
}
