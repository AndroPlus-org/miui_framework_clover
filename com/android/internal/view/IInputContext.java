// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.view;

import android.os.*;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.inputmethod.*;

// Referenced classes of package com.android.internal.view:
//            IInputContextCallback

public interface IInputContext
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IInputContext
    {

        public static IInputContext asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.view.IInputContext");
            if(iinterface != null && (iinterface instanceof IInputContext))
                return (IInputContext)iinterface;
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
                parcel1.writeString("com.android.internal.view.IInputContext");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.view.IInputContext");
                getTextBeforeCursor(parcel.readInt(), parcel.readInt(), parcel.readInt(), IInputContextCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.view.IInputContext");
                getTextAfterCursor(parcel.readInt(), parcel.readInt(), parcel.readInt(), IInputContextCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.internal.view.IInputContext");
                getCursorCapsMode(parcel.readInt(), parcel.readInt(), IInputContextCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.internal.view.IInputContext");
                if(parcel.readInt() != 0)
                    parcel1 = (ExtractedTextRequest)ExtractedTextRequest.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                getExtractedText(parcel1, parcel.readInt(), parcel.readInt(), IInputContextCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.android.internal.view.IInputContext");
                deleteSurroundingText(parcel.readInt(), parcel.readInt());
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.android.internal.view.IInputContext");
                deleteSurroundingTextInCodePoints(parcel.readInt(), parcel.readInt());
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.android.internal.view.IInputContext");
                if(parcel.readInt() != 0)
                    parcel1 = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                setComposingText(parcel1, parcel.readInt());
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.android.internal.view.IInputContext");
                finishComposingText();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.android.internal.view.IInputContext");
                if(parcel.readInt() != 0)
                    parcel1 = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                commitText(parcel1, parcel.readInt());
                return true;

            case 10: // '\n'
                parcel.enforceInterface("com.android.internal.view.IInputContext");
                if(parcel.readInt() != 0)
                    parcel = (CompletionInfo)CompletionInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                commitCompletion(parcel);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("com.android.internal.view.IInputContext");
                if(parcel.readInt() != 0)
                    parcel = (CorrectionInfo)CorrectionInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                commitCorrection(parcel);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("com.android.internal.view.IInputContext");
                setSelection(parcel.readInt(), parcel.readInt());
                return true;

            case 13: // '\r'
                parcel.enforceInterface("com.android.internal.view.IInputContext");
                performEditorAction(parcel.readInt());
                return true;

            case 14: // '\016'
                parcel.enforceInterface("com.android.internal.view.IInputContext");
                performContextMenuAction(parcel.readInt());
                return true;

            case 15: // '\017'
                parcel.enforceInterface("com.android.internal.view.IInputContext");
                beginBatchEdit();
                return true;

            case 16: // '\020'
                parcel.enforceInterface("com.android.internal.view.IInputContext");
                endBatchEdit();
                return true;

            case 17: // '\021'
                parcel.enforceInterface("com.android.internal.view.IInputContext");
                if(parcel.readInt() != 0)
                    parcel = (KeyEvent)KeyEvent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                sendKeyEvent(parcel);
                return true;

            case 18: // '\022'
                parcel.enforceInterface("com.android.internal.view.IInputContext");
                clearMetaKeyStates(parcel.readInt());
                return true;

            case 19: // '\023'
                parcel.enforceInterface("com.android.internal.view.IInputContext");
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                performPrivateCommand(parcel1, parcel);
                return true;

            case 20: // '\024'
                parcel.enforceInterface("com.android.internal.view.IInputContext");
                setComposingRegion(parcel.readInt(), parcel.readInt());
                return true;

            case 21: // '\025'
                parcel.enforceInterface("com.android.internal.view.IInputContext");
                getSelectedText(parcel.readInt(), parcel.readInt(), IInputContextCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 22: // '\026'
                parcel.enforceInterface("com.android.internal.view.IInputContext");
                requestUpdateCursorAnchorInfo(parcel.readInt(), parcel.readInt(), IInputContextCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 23: // '\027'
                parcel.enforceInterface("com.android.internal.view.IInputContext");
                break;
            }
            Bundle bundle;
            if(parcel.readInt() != 0)
                parcel1 = (InputContentInfo)InputContentInfo.CREATOR.createFromParcel(parcel);
            else
                parcel1 = null;
            i = parcel.readInt();
            if(parcel.readInt() != 0)
                bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
            else
                bundle = null;
            commitContent(parcel1, i, bundle, parcel.readInt(), IInputContextCallback.Stub.asInterface(parcel.readStrongBinder()));
            return true;
        }

        private static final String DESCRIPTOR = "com.android.internal.view.IInputContext";
        static final int TRANSACTION_beginBatchEdit = 15;
        static final int TRANSACTION_clearMetaKeyStates = 18;
        static final int TRANSACTION_commitCompletion = 10;
        static final int TRANSACTION_commitContent = 23;
        static final int TRANSACTION_commitCorrection = 11;
        static final int TRANSACTION_commitText = 9;
        static final int TRANSACTION_deleteSurroundingText = 5;
        static final int TRANSACTION_deleteSurroundingTextInCodePoints = 6;
        static final int TRANSACTION_endBatchEdit = 16;
        static final int TRANSACTION_finishComposingText = 8;
        static final int TRANSACTION_getCursorCapsMode = 3;
        static final int TRANSACTION_getExtractedText = 4;
        static final int TRANSACTION_getSelectedText = 21;
        static final int TRANSACTION_getTextAfterCursor = 2;
        static final int TRANSACTION_getTextBeforeCursor = 1;
        static final int TRANSACTION_performContextMenuAction = 14;
        static final int TRANSACTION_performEditorAction = 13;
        static final int TRANSACTION_performPrivateCommand = 19;
        static final int TRANSACTION_requestUpdateCursorAnchorInfo = 22;
        static final int TRANSACTION_sendKeyEvent = 17;
        static final int TRANSACTION_setComposingRegion = 20;
        static final int TRANSACTION_setComposingText = 7;
        static final int TRANSACTION_setSelection = 12;

        public Stub()
        {
            attachInterface(this, "com.android.internal.view.IInputContext");
        }
    }

    private static class Stub.Proxy
        implements IInputContext
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void beginBatchEdit()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputContext");
            mRemote.transact(15, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void clearMetaKeyStates(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputContext");
            parcel.writeInt(i);
            mRemote.transact(18, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void commitCompletion(CompletionInfo completioninfo)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputContext");
            if(completioninfo == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            completioninfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(10, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            completioninfo;
            parcel.recycle();
            throw completioninfo;
        }

        public void commitContent(InputContentInfo inputcontentinfo, int i, Bundle bundle, int j, IInputContextCallback iinputcontextcallback)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputContext");
            if(inputcontentinfo == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            inputcontentinfo.writeToParcel(parcel, 0);
_L3:
            parcel.writeInt(i);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_123;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            parcel.writeInt(j);
            inputcontentinfo = obj;
            if(iinputcontextcallback == null)
                break MISSING_BLOCK_LABEL_78;
            inputcontentinfo = iinputcontextcallback.asBinder();
            parcel.writeStrongBinder(inputcontentinfo);
            mRemote.transact(23, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            inputcontentinfo;
            parcel.recycle();
            throw inputcontentinfo;
            parcel.writeInt(0);
              goto _L4
        }

        public void commitCorrection(CorrectionInfo correctioninfo)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputContext");
            if(correctioninfo == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            correctioninfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(11, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            correctioninfo;
            parcel.recycle();
            throw correctioninfo;
        }

        public void commitText(CharSequence charsequence, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputContext");
            if(charsequence == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            TextUtils.writeToParcel(charsequence, parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(9, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            charsequence;
            parcel.recycle();
            throw charsequence;
        }

        public void deleteSurroundingText(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputContext");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void deleteSurroundingTextInCodePoints(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputContext");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void endBatchEdit()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputContext");
            mRemote.transact(16, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void finishComposingText()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputContext");
            mRemote.transact(8, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void getCursorCapsMode(int i, int j, IInputContextCallback iinputcontextcallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputContext");
            parcel.writeInt(i);
            parcel.writeInt(j);
            if(iinputcontextcallback == null)
                break MISSING_BLOCK_LABEL_39;
            ibinder = iinputcontextcallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            iinputcontextcallback;
            parcel.recycle();
            throw iinputcontextcallback;
        }

        public void getExtractedText(ExtractedTextRequest extractedtextrequest, int i, int j, IInputContextCallback iinputcontextcallback)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputContext");
            if(extractedtextrequest == null)
                break MISSING_BLOCK_LABEL_87;
            parcel.writeInt(1);
            extractedtextrequest.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            parcel.writeInt(j);
            extractedtextrequest = obj;
            if(iinputcontextcallback == null)
                break MISSING_BLOCK_LABEL_60;
            extractedtextrequest = iinputcontextcallback.asBinder();
            parcel.writeStrongBinder(extractedtextrequest);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            extractedtextrequest;
            parcel.recycle();
            throw extractedtextrequest;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.view.IInputContext";
        }

        public void getSelectedText(int i, int j, IInputContextCallback iinputcontextcallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputContext");
            parcel.writeInt(i);
            parcel.writeInt(j);
            if(iinputcontextcallback == null)
                break MISSING_BLOCK_LABEL_39;
            ibinder = iinputcontextcallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(21, parcel, null, 1);
            parcel.recycle();
            return;
            iinputcontextcallback;
            parcel.recycle();
            throw iinputcontextcallback;
        }

        public void getTextAfterCursor(int i, int j, int k, IInputContextCallback iinputcontextcallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputContext");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            if(iinputcontextcallback == null)
                break MISSING_BLOCK_LABEL_47;
            ibinder = iinputcontextcallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            iinputcontextcallback;
            parcel.recycle();
            throw iinputcontextcallback;
        }

        public void getTextBeforeCursor(int i, int j, int k, IInputContextCallback iinputcontextcallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputContext");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            if(iinputcontextcallback == null)
                break MISSING_BLOCK_LABEL_47;
            ibinder = iinputcontextcallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            iinputcontextcallback;
            parcel.recycle();
            throw iinputcontextcallback;
        }

        public void performContextMenuAction(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputContext");
            parcel.writeInt(i);
            mRemote.transact(14, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void performEditorAction(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputContext");
            parcel.writeInt(i);
            mRemote.transact(13, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void performPrivateCommand(String s, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputContext");
            parcel.writeString(s);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(19, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void requestUpdateCursorAnchorInfo(int i, int j, IInputContextCallback iinputcontextcallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputContext");
            parcel.writeInt(i);
            parcel.writeInt(j);
            if(iinputcontextcallback == null)
                break MISSING_BLOCK_LABEL_39;
            ibinder = iinputcontextcallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(22, parcel, null, 1);
            parcel.recycle();
            return;
            iinputcontextcallback;
            parcel.recycle();
            throw iinputcontextcallback;
        }

        public void sendKeyEvent(KeyEvent keyevent)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputContext");
            if(keyevent == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            keyevent.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(17, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            keyevent;
            parcel.recycle();
            throw keyevent;
        }

        public void setComposingRegion(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputContext");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(20, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void setComposingText(CharSequence charsequence, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputContext");
            if(charsequence == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            TextUtils.writeToParcel(charsequence, parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            charsequence;
            parcel.recycle();
            throw charsequence;
        }

        public void setSelection(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputContext");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(12, parcel, null, 1);
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


    public abstract void beginBatchEdit()
        throws RemoteException;

    public abstract void clearMetaKeyStates(int i)
        throws RemoteException;

    public abstract void commitCompletion(CompletionInfo completioninfo)
        throws RemoteException;

    public abstract void commitContent(InputContentInfo inputcontentinfo, int i, Bundle bundle, int j, IInputContextCallback iinputcontextcallback)
        throws RemoteException;

    public abstract void commitCorrection(CorrectionInfo correctioninfo)
        throws RemoteException;

    public abstract void commitText(CharSequence charsequence, int i)
        throws RemoteException;

    public abstract void deleteSurroundingText(int i, int j)
        throws RemoteException;

    public abstract void deleteSurroundingTextInCodePoints(int i, int j)
        throws RemoteException;

    public abstract void endBatchEdit()
        throws RemoteException;

    public abstract void finishComposingText()
        throws RemoteException;

    public abstract void getCursorCapsMode(int i, int j, IInputContextCallback iinputcontextcallback)
        throws RemoteException;

    public abstract void getExtractedText(ExtractedTextRequest extractedtextrequest, int i, int j, IInputContextCallback iinputcontextcallback)
        throws RemoteException;

    public abstract void getSelectedText(int i, int j, IInputContextCallback iinputcontextcallback)
        throws RemoteException;

    public abstract void getTextAfterCursor(int i, int j, int k, IInputContextCallback iinputcontextcallback)
        throws RemoteException;

    public abstract void getTextBeforeCursor(int i, int j, int k, IInputContextCallback iinputcontextcallback)
        throws RemoteException;

    public abstract void performContextMenuAction(int i)
        throws RemoteException;

    public abstract void performEditorAction(int i)
        throws RemoteException;

    public abstract void performPrivateCommand(String s, Bundle bundle)
        throws RemoteException;

    public abstract void requestUpdateCursorAnchorInfo(int i, int j, IInputContextCallback iinputcontextcallback)
        throws RemoteException;

    public abstract void sendKeyEvent(KeyEvent keyevent)
        throws RemoteException;

    public abstract void setComposingRegion(int i, int j)
        throws RemoteException;

    public abstract void setComposingText(CharSequence charsequence, int i)
        throws RemoteException;

    public abstract void setSelection(int i, int j)
        throws RemoteException;
}
