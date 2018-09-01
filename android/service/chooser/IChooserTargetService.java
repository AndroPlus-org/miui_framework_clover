// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.chooser;

import android.content.ComponentName;
import android.content.IntentFilter;
import android.os.*;

// Referenced classes of package android.service.chooser:
//            IChooserTargetResult

public interface IChooserTargetService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IChooserTargetService
    {

        public static IChooserTargetService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.service.chooser.IChooserTargetService");
            if(iinterface != null && (iinterface instanceof IChooserTargetService))
                return (IChooserTargetService)iinterface;
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
                parcel1.writeString("android.service.chooser.IChooserTargetService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.service.chooser.IChooserTargetService");
                break;
            }
            IntentFilter intentfilter;
            if(parcel.readInt() != 0)
                parcel1 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
            else
                parcel1 = null;
            if(parcel.readInt() != 0)
                intentfilter = (IntentFilter)IntentFilter.CREATOR.createFromParcel(parcel);
            else
                intentfilter = null;
            getChooserTargets(parcel1, intentfilter, IChooserTargetResult.Stub.asInterface(parcel.readStrongBinder()));
            return true;
        }

        private static final String DESCRIPTOR = "android.service.chooser.IChooserTargetService";
        static final int TRANSACTION_getChooserTargets = 1;

        public Stub()
        {
            attachInterface(this, "android.service.chooser.IChooserTargetService");
        }
    }

    private static class Stub.Proxy
        implements IChooserTargetService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void getChooserTargets(ComponentName componentname, IntentFilter intentfilter, IChooserTargetResult ichoosertargetresult)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.chooser.IChooserTargetService");
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L3:
            if(intentfilter == null)
                break MISSING_BLOCK_LABEL_107;
            parcel.writeInt(1);
            intentfilter.writeToParcel(parcel, 0);
_L4:
            componentname = obj;
            if(ichoosertargetresult == null)
                break MISSING_BLOCK_LABEL_63;
            componentname = ichoosertargetresult.asBinder();
            parcel.writeStrongBinder(componentname);
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

        public String getInterfaceDescriptor()
        {
            return "android.service.chooser.IChooserTargetService";
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void getChooserTargets(ComponentName componentname, IntentFilter intentfilter, IChooserTargetResult ichoosertargetresult)
        throws RemoteException;
}
