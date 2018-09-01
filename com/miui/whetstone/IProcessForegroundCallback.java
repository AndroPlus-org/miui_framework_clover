// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone;

import android.content.ComponentName;
import android.os.*;

public interface IProcessForegroundCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IProcessForegroundCallback
    {

        public static IProcessForegroundCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.miui.whetstone.IProcessForegroundCallback");
            if(iinterface != null && (iinterface instanceof IProcessForegroundCallback))
                return (IProcessForegroundCallback)iinterface;
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
                parcel1.writeString("com.miui.whetstone.IProcessForegroundCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.miui.whetstone.IProcessForegroundCallback");
                break;
            }
            if(parcel.readInt() != 0)
                parcel1 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
            else
                parcel1 = null;
            if(parcel.readInt() != 0)
                parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            onTargetProcessForeground(parcel1, parcel);
            return true;
        }

        private static final String DESCRIPTOR = "com.miui.whetstone.IProcessForegroundCallback";
        static final int TRANSACTION_onTargetProcessForeground = 1;

        public Stub()
        {
            attachInterface(this, "com.miui.whetstone.IProcessForegroundCallback");
        }
    }

    private static class Stub.Proxy
        implements IProcessForegroundCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.miui.whetstone.IProcessForegroundCallback";
        }

        public void onTargetProcessForeground(ComponentName componentname, ComponentName componentname1)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.IProcessForegroundCallback");
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L3:
            if(componentname1 == null)
                break MISSING_BLOCK_LABEL_74;
            parcel.writeInt(1);
            componentname1.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            componentname;
            parcel.recycle();
            throw componentname;
            parcel.writeInt(0);
              goto _L4
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onTargetProcessForeground(ComponentName componentname, ComponentName componentname1)
        throws RemoteException;
}
