// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.textservice;

import android.os.*;

// Referenced classes of package com.android.internal.textservice:
//            ISpellCheckerSession

public interface ITextServicesSessionListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ITextServicesSessionListener
    {

        public static ITextServicesSessionListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.textservice.ITextServicesSessionListener");
            if(iinterface != null && (iinterface instanceof ITextServicesSessionListener))
                return (ITextServicesSessionListener)iinterface;
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
                parcel1.writeString("com.android.internal.textservice.ITextServicesSessionListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.textservice.ITextServicesSessionListener");
                onServiceConnected(ISpellCheckerSession.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.internal.textservice.ITextServicesSessionListener";
        static final int TRANSACTION_onServiceConnected = 1;

        public Stub()
        {
            attachInterface(this, "com.android.internal.textservice.ITextServicesSessionListener");
        }
    }

    private static class Stub.Proxy
        implements ITextServicesSessionListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.textservice.ITextServicesSessionListener";
        }

        public void onServiceConnected(ISpellCheckerSession ispellcheckersession)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.textservice.ITextServicesSessionListener");
            if(ispellcheckersession == null)
                break MISSING_BLOCK_LABEL_23;
            ibinder = ispellcheckersession.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            ispellcheckersession;
            parcel.recycle();
            throw ispellcheckersession;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onServiceConnected(ISpellCheckerSession ispellcheckersession)
        throws RemoteException;
}
