// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.view;

import android.os.*;
import android.text.TextUtils;
import android.view.inputmethod.ExtractedText;

public interface IInputContextCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IInputContextCallback
    {

        public static IInputContextCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.view.IInputContextCallback");
            if(iinterface != null && (iinterface instanceof IInputContextCallback))
                return (IInputContextCallback)iinterface;
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
                parcel1.writeString("com.android.internal.view.IInputContextCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.view.IInputContextCallback");
                if(parcel.readInt() != 0)
                    parcel1 = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                setTextBeforeCursor(parcel1, parcel.readInt());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.view.IInputContextCallback");
                if(parcel.readInt() != 0)
                    parcel1 = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                setTextAfterCursor(parcel1, parcel.readInt());
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.internal.view.IInputContextCallback");
                setCursorCapsMode(parcel.readInt(), parcel.readInt());
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.internal.view.IInputContextCallback");
                if(parcel.readInt() != 0)
                    parcel1 = (ExtractedText)ExtractedText.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                setExtractedText(parcel1, parcel.readInt());
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.android.internal.view.IInputContextCallback");
                if(parcel.readInt() != 0)
                    parcel1 = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                setSelectedText(parcel1, parcel.readInt());
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.android.internal.view.IInputContextCallback");
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                setRequestUpdateCursorAnchorInfoResult(flag, parcel.readInt());
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.android.internal.view.IInputContextCallback");
                break;
            }
            boolean flag1;
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            setCommitContentResult(flag1, parcel.readInt());
            return true;
        }

        private static final String DESCRIPTOR = "com.android.internal.view.IInputContextCallback";
        static final int TRANSACTION_setCommitContentResult = 7;
        static final int TRANSACTION_setCursorCapsMode = 3;
        static final int TRANSACTION_setExtractedText = 4;
        static final int TRANSACTION_setRequestUpdateCursorAnchorInfoResult = 6;
        static final int TRANSACTION_setSelectedText = 5;
        static final int TRANSACTION_setTextAfterCursor = 2;
        static final int TRANSACTION_setTextBeforeCursor = 1;

        public Stub()
        {
            attachInterface(this, "com.android.internal.view.IInputContextCallback");
        }
    }

    private static class Stub.Proxy
        implements IInputContextCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.view.IInputContextCallback";
        }

        public void setCommitContentResult(boolean flag, int i)
            throws RemoteException
        {
            int j;
            Parcel parcel;
            j = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputContextCallback");
            if(!flag)
                j = 0;
            parcel.writeInt(j);
            parcel.writeInt(i);
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void setCursorCapsMode(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputContextCallback");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void setExtractedText(ExtractedText extractedtext, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputContextCallback");
            if(extractedtext == null)
                break MISSING_BLOCK_LABEL_49;
            parcel.writeInt(1);
            extractedtext.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            extractedtext;
            parcel.recycle();
            throw extractedtext;
        }

        public void setRequestUpdateCursorAnchorInfoResult(boolean flag, int i)
            throws RemoteException
        {
            int j;
            Parcel parcel;
            j = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputContextCallback");
            if(!flag)
                j = 0;
            parcel.writeInt(j);
            parcel.writeInt(i);
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void setSelectedText(CharSequence charsequence, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputContextCallback");
            if(charsequence == null)
                break MISSING_BLOCK_LABEL_49;
            parcel.writeInt(1);
            TextUtils.writeToParcel(charsequence, parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            charsequence;
            parcel.recycle();
            throw charsequence;
        }

        public void setTextAfterCursor(CharSequence charsequence, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputContextCallback");
            if(charsequence == null)
                break MISSING_BLOCK_LABEL_49;
            parcel.writeInt(1);
            TextUtils.writeToParcel(charsequence, parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            charsequence;
            parcel.recycle();
            throw charsequence;
        }

        public void setTextBeforeCursor(CharSequence charsequence, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputContextCallback");
            if(charsequence == null)
                break MISSING_BLOCK_LABEL_49;
            parcel.writeInt(1);
            TextUtils.writeToParcel(charsequence, parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            charsequence;
            parcel.recycle();
            throw charsequence;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void setCommitContentResult(boolean flag, int i)
        throws RemoteException;

    public abstract void setCursorCapsMode(int i, int j)
        throws RemoteException;

    public abstract void setExtractedText(ExtractedText extractedtext, int i)
        throws RemoteException;

    public abstract void setRequestUpdateCursorAnchorInfoResult(boolean flag, int i)
        throws RemoteException;

    public abstract void setSelectedText(CharSequence charsequence, int i)
        throws RemoteException;

    public abstract void setTextAfterCursor(CharSequence charsequence, int i)
        throws RemoteException;

    public abstract void setTextBeforeCursor(CharSequence charsequence, int i)
        throws RemoteException;
}
