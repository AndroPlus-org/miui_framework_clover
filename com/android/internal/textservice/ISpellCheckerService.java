// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.textservice;

import android.os.*;

// Referenced classes of package com.android.internal.textservice:
//            ISpellCheckerSessionListener, ISpellCheckerServiceCallback

public interface ISpellCheckerService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ISpellCheckerService
    {

        public static ISpellCheckerService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.textservice.ISpellCheckerService");
            if(iinterface != null && (iinterface instanceof ISpellCheckerService))
                return (ISpellCheckerService)iinterface;
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
            String s;
            ISpellCheckerSessionListener ispellcheckersessionlistener;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("com.android.internal.textservice.ISpellCheckerService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.textservice.ISpellCheckerService");
                s = parcel.readString();
                ispellcheckersessionlistener = ISpellCheckerSessionListener.Stub.asInterface(parcel.readStrongBinder());
                break;
            }
            if(parcel.readInt() != 0)
                parcel1 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
            else
                parcel1 = null;
            getISpellCheckerSession(s, ispellcheckersessionlistener, parcel1, ISpellCheckerServiceCallback.Stub.asInterface(parcel.readStrongBinder()));
            return true;
        }

        private static final String DESCRIPTOR = "com.android.internal.textservice.ISpellCheckerService";
        static final int TRANSACTION_getISpellCheckerSession = 1;

        public Stub()
        {
            attachInterface(this, "com.android.internal.textservice.ISpellCheckerService");
        }
    }

    private static class Stub.Proxy
        implements ISpellCheckerService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void getISpellCheckerSession(String s, ISpellCheckerSessionListener ispellcheckersessionlistener, Bundle bundle, ISpellCheckerServiceCallback ispellcheckerservicecallback)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.textservice.ISpellCheckerService");
            parcel.writeString(s);
            if(ispellcheckersessionlistener == null) goto _L2; else goto _L1
_L1:
            s = ispellcheckersessionlistener.asBinder();
_L5:
            parcel.writeStrongBinder(s);
            if(bundle == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L6:
            s = obj;
            if(ispellcheckerservicecallback == null)
                break MISSING_BLOCK_LABEL_71;
            s = ispellcheckerservicecallback.asBinder();
            parcel.writeStrongBinder(s);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            s = null;
              goto _L5
_L4:
            parcel.writeInt(0);
              goto _L6
            s;
            parcel.recycle();
            throw s;
              goto _L5
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.textservice.ISpellCheckerService";
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void getISpellCheckerSession(String s, ISpellCheckerSessionListener ispellcheckersessionlistener, Bundle bundle, ISpellCheckerServiceCallback ispellcheckerservicecallback)
        throws RemoteException;
}
