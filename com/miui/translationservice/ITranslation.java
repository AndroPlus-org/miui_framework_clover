// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.translationservice;

import android.os.*;

// Referenced classes of package com.miui.translationservice:
//            ITranslationRemoteCallback

public interface ITranslation
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ITranslation
    {

        public static ITranslation asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.miui.translationservice.ITranslation");
            if(iinterface != null && (iinterface instanceof ITranslation))
                return (ITranslation)iinterface;
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
                parcel1.writeString("com.miui.translationservice.ITranslation");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.miui.translationservice.ITranslation");
                translate(parcel.readString(), parcel.readString(), parcel.readString(), ITranslationRemoteCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.miui.translationservice.ITranslation");
                break;
            }
            boolean flag;
            if(parcel.readInt() != 0)
                flag = true;
            else
                flag = false;
            translateByEngine(flag, parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString(), ITranslationRemoteCallback.Stub.asInterface(parcel.readStrongBinder()));
            return true;
        }

        private static final String DESCRIPTOR = "com.miui.translationservice.ITranslation";
        static final int TRANSACTION_translate = 1;
        static final int TRANSACTION_translateByEngine = 2;

        public Stub()
        {
            attachInterface(this, "com.miui.translationservice.ITranslation");
        }
    }

    private static class Stub.Proxy
        implements ITranslation
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.miui.translationservice.ITranslation";
        }

        public void translate(String s, String s1, String s2, ITranslationRemoteCallback itranslationremotecallback)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.translationservice.ITranslation");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            s = obj;
            if(itranslationremotecallback == null)
                break MISSING_BLOCK_LABEL_49;
            s = itranslationremotecallback.asBinder();
            parcel.writeStrongBinder(s);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void translateByEngine(boolean flag, int i, String s, int j, String s1, String s2, String s3, 
                ITranslationRemoteCallback itranslationremotecallback)
            throws RemoteException
        {
            int k;
            Object obj;
            Parcel parcel;
            k = 1;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.translationservice.ITranslation");
            if(!flag)
                k = 0;
            parcel.writeInt(k);
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeInt(j);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeString(s3);
            s = obj;
            if(itranslationremotecallback == null)
                break MISSING_BLOCK_LABEL_85;
            s = itranslationremotecallback.asBinder();
            parcel.writeStrongBinder(s);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void translate(String s, String s1, String s2, ITranslationRemoteCallback itranslationremotecallback)
        throws RemoteException;

    public abstract void translateByEngine(boolean flag, int i, String s, int j, String s1, String s2, String s3, 
            ITranslationRemoteCallback itranslationremotecallback)
        throws RemoteException;
}
