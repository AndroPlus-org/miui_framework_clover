// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.process;

import android.content.ComponentName;
import android.os.*;

public interface IActivityChangeListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IActivityChangeListener
    {

        public static IActivityChangeListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("miui.process.IActivityChangeListener");
            if(iinterface != null && (iinterface instanceof IActivityChangeListener))
                return (IActivityChangeListener)iinterface;
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
                parcel1.writeString("miui.process.IActivityChangeListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("miui.process.IActivityChangeListener");
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
            onActivityChanged(parcel1, parcel);
            return true;
        }

        private static final String DESCRIPTOR = "miui.process.IActivityChangeListener";
        static final int TRANSACTION_onActivityChanged = 1;

        public Stub()
        {
            attachInterface(this, "miui.process.IActivityChangeListener");
        }
    }

    private static class Stub.Proxy
        implements IActivityChangeListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "miui.process.IActivityChangeListener";
        }

        public void onActivityChanged(ComponentName componentname, ComponentName componentname1)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("miui.process.IActivityChangeListener");
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


    public abstract void onActivityChanged(ComponentName componentname, ComponentName componentname1)
        throws RemoteException;
}
