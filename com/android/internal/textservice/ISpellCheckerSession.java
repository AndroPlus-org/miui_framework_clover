// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.textservice;

import android.os.*;
import android.view.textservice.TextInfo;

public interface ISpellCheckerSession
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ISpellCheckerSession
    {

        public static ISpellCheckerSession asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.textservice.ISpellCheckerSession");
            if(iinterface != null && (iinterface instanceof ISpellCheckerSession))
                return (ISpellCheckerSession)iinterface;
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
                parcel1.writeString("com.android.internal.textservice.ISpellCheckerSession");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.textservice.ISpellCheckerSession");
                parcel1 = (TextInfo[])parcel.createTypedArray(TextInfo.CREATOR);
                i = parcel.readInt();
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                onGetSuggestionsMultiple(parcel1, i, flag);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.textservice.ISpellCheckerSession");
                onGetSentenceSuggestionsMultiple((TextInfo[])parcel.createTypedArray(TextInfo.CREATOR), parcel.readInt());
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.internal.textservice.ISpellCheckerSession");
                onCancel();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.internal.textservice.ISpellCheckerSession");
                onClose();
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.internal.textservice.ISpellCheckerSession";
        static final int TRANSACTION_onCancel = 3;
        static final int TRANSACTION_onClose = 4;
        static final int TRANSACTION_onGetSentenceSuggestionsMultiple = 2;
        static final int TRANSACTION_onGetSuggestionsMultiple = 1;

        public Stub()
        {
            attachInterface(this, "com.android.internal.textservice.ISpellCheckerSession");
        }
    }

    private static class Stub.Proxy
        implements ISpellCheckerSession
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.textservice.ISpellCheckerSession";
        }

        public void onCancel()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.textservice.ISpellCheckerSession");
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onClose()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.textservice.ISpellCheckerSession");
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onGetSentenceSuggestionsMultiple(TextInfo atextinfo[], int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.textservice.ISpellCheckerSession");
            parcel.writeTypedArray(atextinfo, 0);
            parcel.writeInt(i);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            atextinfo;
            parcel.recycle();
            throw atextinfo;
        }

        public void onGetSuggestionsMultiple(TextInfo atextinfo[], int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.textservice.ISpellCheckerSession");
            parcel.writeTypedArray(atextinfo, 0);
            parcel.writeInt(i);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            atextinfo;
            parcel.recycle();
            throw atextinfo;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onCancel()
        throws RemoteException;

    public abstract void onClose()
        throws RemoteException;

    public abstract void onGetSentenceSuggestionsMultiple(TextInfo atextinfo[], int i)
        throws RemoteException;

    public abstract void onGetSuggestionsMultiple(TextInfo atextinfo[], int i, boolean flag)
        throws RemoteException;
}
