// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.view;

import android.os.*;
import android.view.InputChannel;
import android.view.inputmethod.*;

// Referenced classes of package com.android.internal.view:
//            IInputSessionCallback, IInputMethodSession, IInputContext

public interface IInputMethod
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IInputMethod
    {

        public static IInputMethod asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.view.IInputMethod");
            if(iinterface != null && (iinterface instanceof IInputMethod))
                return (IInputMethod)iinterface;
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
                parcel1.writeString("com.android.internal.view.IInputMethod");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.view.IInputMethod");
                attachToken(parcel.readStrongBinder());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.view.IInputMethod");
                if(parcel.readInt() != 0)
                    parcel = (InputBinding)InputBinding.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                bindInput(parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.internal.view.IInputMethod");
                unbindInput();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.internal.view.IInputMethod");
                IBinder ibinder = parcel.readStrongBinder();
                IInputContext iinputcontext = IInputContext.Stub.asInterface(parcel.readStrongBinder());
                i = parcel.readInt();
                boolean flag;
                if(parcel.readInt() != 0)
                    parcel1 = (EditorInfo)EditorInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                startInput(ibinder, iinputcontext, i, parcel1, flag);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.android.internal.view.IInputMethod");
                if(parcel.readInt() != 0)
                    parcel1 = (InputChannel)InputChannel.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                createSession(parcel1, IInputSessionCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.android.internal.view.IInputMethod");
                parcel1 = IInputMethodSession.Stub.asInterface(parcel.readStrongBinder());
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                setSessionEnabled(parcel1, flag1);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.android.internal.view.IInputMethod");
                revokeSession(IInputMethodSession.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.android.internal.view.IInputMethod");
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (ResultReceiver)ResultReceiver.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                showSoftInput(i, parcel);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.android.internal.view.IInputMethod");
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (ResultReceiver)ResultReceiver.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                hideSoftInput(i, parcel);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("com.android.internal.view.IInputMethod");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (InputMethodSubtype)InputMethodSubtype.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            changeInputMethodSubtype(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "com.android.internal.view.IInputMethod";
        static final int TRANSACTION_attachToken = 1;
        static final int TRANSACTION_bindInput = 2;
        static final int TRANSACTION_changeInputMethodSubtype = 10;
        static final int TRANSACTION_createSession = 5;
        static final int TRANSACTION_hideSoftInput = 9;
        static final int TRANSACTION_revokeSession = 7;
        static final int TRANSACTION_setSessionEnabled = 6;
        static final int TRANSACTION_showSoftInput = 8;
        static final int TRANSACTION_startInput = 4;
        static final int TRANSACTION_unbindInput = 3;

        public Stub()
        {
            attachInterface(this, "com.android.internal.view.IInputMethod");
        }
    }

    private static class Stub.Proxy
        implements IInputMethod
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void attachToken(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethod");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        public void bindInput(InputBinding inputbinding)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethod");
            if(inputbinding == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            inputbinding.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            inputbinding;
            parcel.recycle();
            throw inputbinding;
        }

        public void changeInputMethodSubtype(InputMethodSubtype inputmethodsubtype)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethod");
            if(inputmethodsubtype == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            inputmethodsubtype.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(10, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            inputmethodsubtype;
            parcel.recycle();
            throw inputmethodsubtype;
        }

        public void createSession(InputChannel inputchannel, IInputSessionCallback iinputsessioncallback)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethod");
            if(inputchannel == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            inputchannel.writeToParcel(parcel, 0);
_L4:
            inputchannel = obj;
            if(iinputsessioncallback == null)
                break MISSING_BLOCK_LABEL_44;
            inputchannel = iinputsessioncallback.asBinder();
            parcel.writeStrongBinder(inputchannel);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
            if(true) goto _L4; else goto _L3
_L3:
            inputchannel;
            parcel.recycle();
            throw inputchannel;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.view.IInputMethod";
        }

        public void hideSoftInput(int i, ResultReceiver resultreceiver)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethod");
            parcel.writeInt(i);
            if(resultreceiver == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            resultreceiver.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(9, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            resultreceiver;
            parcel.recycle();
            throw resultreceiver;
        }

        public void revokeSession(IInputMethodSession iinputmethodsession)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethod");
            if(iinputmethodsession == null)
                break MISSING_BLOCK_LABEL_23;
            ibinder = iinputmethodsession.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            iinputmethodsession;
            parcel.recycle();
            throw iinputmethodsession;
        }

        public void setSessionEnabled(IInputMethodSession iinputmethodsession, boolean flag)
            throws RemoteException
        {
            int i;
            IBinder ibinder;
            Parcel parcel;
            i = 1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethod");
            if(iinputmethodsession == null)
                break MISSING_BLOCK_LABEL_29;
            ibinder = iinputmethodsession.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            iinputmethodsession;
            parcel.recycle();
            throw iinputmethodsession;
        }

        public void showSoftInput(int i, ResultReceiver resultreceiver)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethod");
            parcel.writeInt(i);
            if(resultreceiver == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            resultreceiver.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(8, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            resultreceiver;
            parcel.recycle();
            throw resultreceiver;
        }

        public void startInput(IBinder ibinder, IInputContext iinputcontext, int i, EditorInfo editorinfo, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Object obj;
            Parcel parcel;
            flag1 = true;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethod");
            parcel.writeStrongBinder(ibinder);
            ibinder = obj;
            if(iinputcontext == null)
                break MISSING_BLOCK_LABEL_38;
            ibinder = iinputcontext.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            if(editorinfo == null)
                break MISSING_BLOCK_LABEL_104;
            parcel.writeInt(1);
            editorinfo.writeToParcel(parcel, 0);
_L1:
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        public void unbindInput()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethod");
            mRemote.transact(3, parcel, null, 1);
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


    public abstract void attachToken(IBinder ibinder)
        throws RemoteException;

    public abstract void bindInput(InputBinding inputbinding)
        throws RemoteException;

    public abstract void changeInputMethodSubtype(InputMethodSubtype inputmethodsubtype)
        throws RemoteException;

    public abstract void createSession(InputChannel inputchannel, IInputSessionCallback iinputsessioncallback)
        throws RemoteException;

    public abstract void hideSoftInput(int i, ResultReceiver resultreceiver)
        throws RemoteException;

    public abstract void revokeSession(IInputMethodSession iinputmethodsession)
        throws RemoteException;

    public abstract void setSessionEnabled(IInputMethodSession iinputmethodsession, boolean flag)
        throws RemoteException;

    public abstract void showSoftInput(int i, ResultReceiver resultreceiver)
        throws RemoteException;

    public abstract void startInput(IBinder ibinder, IInputContext iinputcontext, int i, EditorInfo editorinfo, boolean flag)
        throws RemoteException;

    public abstract void unbindInput()
        throws RemoteException;
}
