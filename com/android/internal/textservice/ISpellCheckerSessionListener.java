// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.textservice;

import android.os.*;
import android.view.textservice.SentenceSuggestionsInfo;
import android.view.textservice.SuggestionsInfo;

public interface ISpellCheckerSessionListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ISpellCheckerSessionListener
    {

        public static ISpellCheckerSessionListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.textservice.ISpellCheckerSessionListener");
            if(iinterface != null && (iinterface instanceof ISpellCheckerSessionListener))
                return (ISpellCheckerSessionListener)iinterface;
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
                parcel1.writeString("com.android.internal.textservice.ISpellCheckerSessionListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.textservice.ISpellCheckerSessionListener");
                onGetSuggestions((SuggestionsInfo[])parcel.createTypedArray(SuggestionsInfo.CREATOR));
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.textservice.ISpellCheckerSessionListener");
                onGetSentenceSuggestions((SentenceSuggestionsInfo[])parcel.createTypedArray(SentenceSuggestionsInfo.CREATOR));
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.internal.textservice.ISpellCheckerSessionListener";
        static final int TRANSACTION_onGetSentenceSuggestions = 2;
        static final int TRANSACTION_onGetSuggestions = 1;

        public Stub()
        {
            attachInterface(this, "com.android.internal.textservice.ISpellCheckerSessionListener");
        }
    }

    private static class Stub.Proxy
        implements ISpellCheckerSessionListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.textservice.ISpellCheckerSessionListener";
        }

        public void onGetSentenceSuggestions(SentenceSuggestionsInfo asentencesuggestionsinfo[])
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.textservice.ISpellCheckerSessionListener");
            parcel.writeTypedArray(asentencesuggestionsinfo, 0);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            asentencesuggestionsinfo;
            parcel.recycle();
            throw asentencesuggestionsinfo;
        }

        public void onGetSuggestions(SuggestionsInfo asuggestionsinfo[])
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.textservice.ISpellCheckerSessionListener");
            parcel.writeTypedArray(asuggestionsinfo, 0);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            asuggestionsinfo;
            parcel.recycle();
            throw asuggestionsinfo;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onGetSentenceSuggestions(SentenceSuggestionsInfo asentencesuggestionsinfo[])
        throws RemoteException;

    public abstract void onGetSuggestions(SuggestionsInfo asuggestionsinfo[])
        throws RemoteException;
}
