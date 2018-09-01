// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.view;

import android.graphics.Rect;
import android.os.*;
import android.view.inputmethod.*;

public interface IInputMethodSession
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IInputMethodSession
    {

        public static IInputMethodSession asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.view.IInputMethodSession");
            if(iinterface != null && (iinterface instanceof IInputMethodSession))
                return (IInputMethodSession)iinterface;
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
                parcel1.writeString("com.android.internal.view.IInputMethodSession");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.view.IInputMethodSession");
                finishInput();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.view.IInputMethodSession");
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (ExtractedText)ExtractedText.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                updateExtractedText(i, parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.internal.view.IInputMethodSession");
                updateSelection(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.internal.view.IInputMethodSession");
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                viewClicked(flag);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.android.internal.view.IInputMethodSession");
                if(parcel.readInt() != 0)
                    parcel = (Rect)Rect.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                updateCursor(parcel);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.android.internal.view.IInputMethodSession");
                displayCompletions((CompletionInfo[])parcel.createTypedArray(CompletionInfo.CREATOR));
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.android.internal.view.IInputMethodSession");
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                appPrivateCommand(parcel1, parcel);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.android.internal.view.IInputMethodSession");
                toggleSoftInput(parcel.readInt(), parcel.readInt());
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.android.internal.view.IInputMethodSession");
                finishSession();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("com.android.internal.view.IInputMethodSession");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (CursorAnchorInfo)CursorAnchorInfo.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            updateCursorAnchorInfo(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "com.android.internal.view.IInputMethodSession";
        static final int TRANSACTION_appPrivateCommand = 7;
        static final int TRANSACTION_displayCompletions = 6;
        static final int TRANSACTION_finishInput = 1;
        static final int TRANSACTION_finishSession = 9;
        static final int TRANSACTION_toggleSoftInput = 8;
        static final int TRANSACTION_updateCursor = 5;
        static final int TRANSACTION_updateCursorAnchorInfo = 10;
        static final int TRANSACTION_updateExtractedText = 2;
        static final int TRANSACTION_updateSelection = 3;
        static final int TRANSACTION_viewClicked = 4;

        public Stub()
        {
            attachInterface(this, "com.android.internal.view.IInputMethodSession");
        }
    }

    private static class Stub.Proxy
        implements IInputMethodSession
    {

        public void appPrivateCommand(String s, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethodSession");
            parcel.writeString(s);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void displayCompletions(CompletionInfo acompletioninfo[])
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethodSession");
            parcel.writeTypedArray(acompletioninfo, 0);
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            acompletioninfo;
            parcel.recycle();
            throw acompletioninfo;
        }

        public void finishInput()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethodSession");
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void finishSession()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethodSession");
            mRemote.transact(9, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.view.IInputMethodSession";
        }

        public void toggleSoftInput(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethodSession");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(8, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void updateCursor(Rect rect)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethodSession");
            if(rect == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            rect.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            rect;
            parcel.recycle();
            throw rect;
        }

        public void updateCursorAnchorInfo(CursorAnchorInfo cursoranchorinfo)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethodSession");
            if(cursoranchorinfo == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            cursoranchorinfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(10, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            cursoranchorinfo;
            parcel.recycle();
            throw cursoranchorinfo;
        }

        public void updateExtractedText(int i, ExtractedText extractedtext)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethodSession");
            parcel.writeInt(i);
            if(extractedtext == null)
                break MISSING_BLOCK_LABEL_49;
            parcel.writeInt(1);
            extractedtext.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            extractedtext;
            parcel.recycle();
            throw extractedtext;
        }

        public void updateSelection(int i, int j, int k, int l, int i1, int j1)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethodSession");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeInt(l);
            parcel.writeInt(i1);
            parcel.writeInt(j1);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void viewClicked(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethodSession");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(4, parcel, null, 1);
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


    public abstract void appPrivateCommand(String s, Bundle bundle)
        throws RemoteException;

    public abstract void displayCompletions(CompletionInfo acompletioninfo[])
        throws RemoteException;

    public abstract void finishInput()
        throws RemoteException;

    public abstract void finishSession()
        throws RemoteException;

    public abstract void toggleSoftInput(int i, int j)
        throws RemoteException;

    public abstract void updateCursor(Rect rect)
        throws RemoteException;

    public abstract void updateCursorAnchorInfo(CursorAnchorInfo cursoranchorinfo)
        throws RemoteException;

    public abstract void updateExtractedText(int i, ExtractedText extractedtext)
        throws RemoteException;

    public abstract void updateSelection(int i, int j, int k, int l, int i1, int j1)
        throws RemoteException;

    public abstract void viewClicked(boolean flag)
        throws RemoteException;
}
