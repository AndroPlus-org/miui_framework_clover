// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.view;

import android.net.Uri;
import android.os.*;
import android.text.style.SuggestionSpan;
import android.view.inputmethod.*;
import com.android.internal.inputmethod.IInputContentUriToken;
import java.util.List;

// Referenced classes of package com.android.internal.view:
//            IInputMethodClient, IInputContext, InputBindResult

public interface IInputMethodManager
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IInputMethodManager
    {

        public static IInputMethodManager asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.view.IInputMethodManager");
            if(iinterface != null && (iinterface instanceof IInputMethodManager))
                return (IInputMethodManager)iinterface;
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
                parcel1.writeString("com.android.internal.view.IInputMethodManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.view.IInputMethodManager");
                parcel = getInputMethodList();
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.view.IInputMethodManager");
                parcel = getEnabledInputMethodList();
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.internal.view.IInputMethodManager");
                String s = parcel.readString();
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                parcel = getEnabledInputMethodSubtypeList(s, flag);
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.internal.view.IInputMethodManager");
                parcel = getLastInputMethodSubtype();
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.android.internal.view.IInputMethodManager");
                parcel = getShortcutInputMethodsAndSubtypes();
                parcel1.writeNoException();
                parcel1.writeList(parcel);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.android.internal.view.IInputMethodManager");
                addClient(IInputMethodClient.Stub.asInterface(parcel.readStrongBinder()), IInputContext.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.android.internal.view.IInputMethodManager");
                removeClient(IInputMethodClient.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.android.internal.view.IInputMethodManager");
                finishInput(IInputMethodClient.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.android.internal.view.IInputMethodManager");
                IInputMethodClient iinputmethodclient = IInputMethodClient.Stub.asInterface(parcel.readStrongBinder());
                i = parcel.readInt();
                boolean flag1;
                if(parcel.readInt() != 0)
                    parcel = (ResultReceiver)ResultReceiver.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag1 = showSoftInput(iinputmethodclient, i, parcel);
                parcel1.writeNoException();
                if(flag1)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("com.android.internal.view.IInputMethodManager");
                IInputMethodClient iinputmethodclient1 = IInputMethodClient.Stub.asInterface(parcel.readStrongBinder());
                i = parcel.readInt();
                boolean flag2;
                if(parcel.readInt() != 0)
                    parcel = (ResultReceiver)ResultReceiver.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag2 = hideSoftInput(iinputmethodclient1, i, parcel);
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("com.android.internal.view.IInputMethodManager");
                i = parcel.readInt();
                IInputMethodClient iinputmethodclient2 = IInputMethodClient.Stub.asInterface(parcel.readStrongBinder());
                IBinder ibinder3 = parcel.readStrongBinder();
                j = parcel.readInt();
                int k = parcel.readInt();
                int l = parcel.readInt();
                EditorInfo editorinfo;
                if(parcel.readInt() != 0)
                    editorinfo = (EditorInfo)EditorInfo.CREATOR.createFromParcel(parcel);
                else
                    editorinfo = null;
                parcel = startInputOrWindowGainedFocus(i, iinputmethodclient2, ibinder3, j, k, l, editorinfo, IInputContext.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 12: // '\f'
                parcel.enforceInterface("com.android.internal.view.IInputMethodManager");
                showInputMethodPickerFromClient(IInputMethodClient.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 13: // '\r'
                parcel.enforceInterface("com.android.internal.view.IInputMethodManager");
                showInputMethodAndSubtypeEnablerFromClient(IInputMethodClient.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 14: // '\016'
                parcel.enforceInterface("com.android.internal.view.IInputMethodManager");
                setInputMethod(parcel.readStrongBinder(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 15: // '\017'
                parcel.enforceInterface("com.android.internal.view.IInputMethodManager");
                IBinder ibinder = parcel.readStrongBinder();
                String s2 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (InputMethodSubtype)InputMethodSubtype.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setInputMethodAndSubtype(ibinder, s2, parcel);
                parcel1.writeNoException();
                return true;

            case 16: // '\020'
                parcel.enforceInterface("com.android.internal.view.IInputMethodManager");
                hideMySoftInput(parcel.readStrongBinder(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 17: // '\021'
                parcel.enforceInterface("com.android.internal.view.IInputMethodManager");
                showMySoftInput(parcel.readStrongBinder(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 18: // '\022'
                parcel.enforceInterface("com.android.internal.view.IInputMethodManager");
                updateStatusIcon(parcel.readStrongBinder(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 19: // '\023'
                parcel.enforceInterface("com.android.internal.view.IInputMethodManager");
                setImeWindowStatus(parcel.readStrongBinder(), parcel.readStrongBinder(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 20: // '\024'
                parcel.enforceInterface("com.android.internal.view.IInputMethodManager");
                registerSuggestionSpansForNotification((SuggestionSpan[])parcel.createTypedArray(SuggestionSpan.CREATOR));
                parcel1.writeNoException();
                return true;

            case 21: // '\025'
                parcel.enforceInterface("com.android.internal.view.IInputMethodManager");
                SuggestionSpan suggestionspan;
                boolean flag3;
                if(parcel.readInt() != 0)
                    suggestionspan = (SuggestionSpan)SuggestionSpan.CREATOR.createFromParcel(parcel);
                else
                    suggestionspan = null;
                flag3 = notifySuggestionPicked(suggestionspan, parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                if(flag3)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 22: // '\026'
                parcel.enforceInterface("com.android.internal.view.IInputMethodManager");
                parcel = getCurrentInputMethodSubtype();
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 23: // '\027'
                parcel.enforceInterface("com.android.internal.view.IInputMethodManager");
                boolean flag4;
                if(parcel.readInt() != 0)
                    parcel = (InputMethodSubtype)InputMethodSubtype.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag4 = setCurrentInputMethodSubtype(parcel);
                parcel1.writeNoException();
                if(flag4)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 24: // '\030'
                parcel.enforceInterface("com.android.internal.view.IInputMethodManager");
                boolean flag5 = switchToLastInputMethod(parcel.readStrongBinder());
                parcel1.writeNoException();
                if(flag5)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 25: // '\031'
                parcel.enforceInterface("com.android.internal.view.IInputMethodManager");
                IBinder ibinder1 = parcel.readStrongBinder();
                boolean flag6;
                if(parcel.readInt() != 0)
                    flag6 = true;
                else
                    flag6 = false;
                flag6 = switchToNextInputMethod(ibinder1, flag6);
                parcel1.writeNoException();
                if(flag6)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 26: // '\032'
                parcel.enforceInterface("com.android.internal.view.IInputMethodManager");
                boolean flag7 = shouldOfferSwitchingToNextInputMethod(parcel.readStrongBinder());
                parcel1.writeNoException();
                if(flag7)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 27: // '\033'
                parcel.enforceInterface("com.android.internal.view.IInputMethodManager");
                String s1 = parcel.readString();
                boolean flag8;
                if(parcel.readInt() != 0)
                    flag8 = true;
                else
                    flag8 = false;
                flag8 = setInputMethodEnabled(s1, flag8);
                parcel1.writeNoException();
                if(flag8)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 28: // '\034'
                parcel.enforceInterface("com.android.internal.view.IInputMethodManager");
                setAdditionalInputMethodSubtypes(parcel.readString(), (InputMethodSubtype[])parcel.createTypedArray(InputMethodSubtype.CREATOR));
                parcel1.writeNoException();
                return true;

            case 29: // '\035'
                parcel.enforceInterface("com.android.internal.view.IInputMethodManager");
                i = getInputMethodWindowVisibleHeight();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 30: // '\036'
                parcel.enforceInterface("com.android.internal.view.IInputMethodManager");
                clearLastInputMethodWindowForTransition(parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;

            case 31: // '\037'
                parcel.enforceInterface("com.android.internal.view.IInputMethodManager");
                IBinder ibinder4 = parcel.readStrongBinder();
                Uri uri;
                if(parcel.readInt() != 0)
                    uri = (Uri)Uri.CREATOR.createFromParcel(parcel);
                else
                    uri = null;
                parcel = createInputContentUriToken(ibinder4, uri, parcel.readString());
                parcel1.writeNoException();
                if(parcel != null)
                    parcel = parcel.asBinder();
                else
                    parcel = null;
                parcel1.writeStrongBinder(parcel);
                return true;

            case 32: // ' '
                parcel.enforceInterface("com.android.internal.view.IInputMethodManager");
                IBinder ibinder2 = parcel.readStrongBinder();
                boolean flag9;
                if(parcel.readInt() != 0)
                    flag9 = true;
                else
                    flag9 = false;
                reportFullscreenMode(ibinder2, flag9);
                parcel1.writeNoException();
                return true;

            case 33: // '!'
                parcel.enforceInterface("com.android.internal.view.IInputMethodManager");
                notifyUserAction(parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.internal.view.IInputMethodManager";
        static final int TRANSACTION_addClient = 6;
        static final int TRANSACTION_clearLastInputMethodWindowForTransition = 30;
        static final int TRANSACTION_createInputContentUriToken = 31;
        static final int TRANSACTION_finishInput = 8;
        static final int TRANSACTION_getCurrentInputMethodSubtype = 22;
        static final int TRANSACTION_getEnabledInputMethodList = 2;
        static final int TRANSACTION_getEnabledInputMethodSubtypeList = 3;
        static final int TRANSACTION_getInputMethodList = 1;
        static final int TRANSACTION_getInputMethodWindowVisibleHeight = 29;
        static final int TRANSACTION_getLastInputMethodSubtype = 4;
        static final int TRANSACTION_getShortcutInputMethodsAndSubtypes = 5;
        static final int TRANSACTION_hideMySoftInput = 16;
        static final int TRANSACTION_hideSoftInput = 10;
        static final int TRANSACTION_notifySuggestionPicked = 21;
        static final int TRANSACTION_notifyUserAction = 33;
        static final int TRANSACTION_registerSuggestionSpansForNotification = 20;
        static final int TRANSACTION_removeClient = 7;
        static final int TRANSACTION_reportFullscreenMode = 32;
        static final int TRANSACTION_setAdditionalInputMethodSubtypes = 28;
        static final int TRANSACTION_setCurrentInputMethodSubtype = 23;
        static final int TRANSACTION_setImeWindowStatus = 19;
        static final int TRANSACTION_setInputMethod = 14;
        static final int TRANSACTION_setInputMethodAndSubtype = 15;
        static final int TRANSACTION_setInputMethodEnabled = 27;
        static final int TRANSACTION_shouldOfferSwitchingToNextInputMethod = 26;
        static final int TRANSACTION_showInputMethodAndSubtypeEnablerFromClient = 13;
        static final int TRANSACTION_showInputMethodPickerFromClient = 12;
        static final int TRANSACTION_showMySoftInput = 17;
        static final int TRANSACTION_showSoftInput = 9;
        static final int TRANSACTION_startInputOrWindowGainedFocus = 11;
        static final int TRANSACTION_switchToLastInputMethod = 24;
        static final int TRANSACTION_switchToNextInputMethod = 25;
        static final int TRANSACTION_updateStatusIcon = 18;

        public Stub()
        {
            attachInterface(this, "com.android.internal.view.IInputMethodManager");
        }
    }

    private static class Stub.Proxy
        implements IInputMethodManager
    {

        public void addClient(IInputMethodClient iinputmethodclient, IInputContext iinputcontext, int i, int j)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethodManager");
            if(iinputmethodclient == null)
                break MISSING_BLOCK_LABEL_103;
            iinputmethodclient = iinputmethodclient.asBinder();
_L1:
            parcel.writeStrongBinder(iinputmethodclient);
            iinputmethodclient = obj;
            if(iinputcontext == null)
                break MISSING_BLOCK_LABEL_51;
            iinputmethodclient = iinputcontext.asBinder();
            parcel.writeStrongBinder(iinputmethodclient);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iinputmethodclient = null;
              goto _L1
            iinputmethodclient;
            parcel1.recycle();
            parcel.recycle();
            throw iinputmethodclient;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void clearLastInputMethodWindowForTransition(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethodManager");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(30, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public IInputContentUriToken createInputContentUriToken(IBinder ibinder, Uri uri, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethodManager");
            parcel.writeStrongBinder(ibinder);
            if(uri == null)
                break MISSING_BLOCK_LABEL_89;
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            mRemote.transact(31, parcel, parcel1, 0);
            parcel1.readException();
            ibinder = com.android.internal.inputmethod.IInputContentUriToken.Stub.asInterface(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return ibinder;
            parcel.writeInt(0);
              goto _L1
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void finishInput(IInputMethodClient iinputmethodclient)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethodManager");
            if(iinputmethodclient == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iinputmethodclient.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iinputmethodclient;
            parcel1.recycle();
            parcel.recycle();
            throw iinputmethodclient;
        }

        public InputMethodSubtype getCurrentInputMethodSubtype()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethodManager");
            mRemote.transact(22, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            InputMethodSubtype inputmethodsubtype = (InputMethodSubtype)InputMethodSubtype.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return inputmethodsubtype;
_L2:
            inputmethodsubtype = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public List getEnabledInputMethodList()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethodManager");
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createTypedArrayList(InputMethodInfo.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public List getEnabledInputMethodSubtypeList(String s, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethodManager");
            parcel.writeString(s);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.createTypedArrayList(InputMethodSubtype.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public List getInputMethodList()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethodManager");
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createTypedArrayList(InputMethodInfo.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getInputMethodWindowVisibleHeight()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethodManager");
            mRemote.transact(29, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.view.IInputMethodManager";
        }

        public InputMethodSubtype getLastInputMethodSubtype()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethodManager");
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            InputMethodSubtype inputmethodsubtype = (InputMethodSubtype)InputMethodSubtype.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return inputmethodsubtype;
_L2:
            inputmethodsubtype = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public List getShortcutInputMethodsAndSubtypes()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethodManager");
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.readArrayList(getClass().getClassLoader());
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void hideMySoftInput(IBinder ibinder, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethodManager");
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(16, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public boolean hideSoftInput(IInputMethodClient iinputmethodclient, int i, ResultReceiver resultreceiver)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethodManager");
            if(iinputmethodclient == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = iinputmethodclient.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            if(resultreceiver == null)
                break MISSING_BLOCK_LABEL_110;
            parcel.writeInt(1);
            resultreceiver.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            parcel.writeInt(0);
              goto _L1
            iinputmethodclient;
            parcel1.recycle();
            parcel.recycle();
            throw iinputmethodclient;
        }

        public boolean notifySuggestionPicked(SuggestionSpan suggestionspan, String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethodManager");
            if(suggestionspan == null)
                break MISSING_BLOCK_LABEL_94;
            parcel.writeInt(1);
            suggestionspan.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(21, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            parcel.writeInt(0);
              goto _L1
            suggestionspan;
            parcel1.recycle();
            parcel.recycle();
            throw suggestionspan;
        }

        public void notifyUserAction(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethodManager");
            parcel.writeInt(i);
            mRemote.transact(33, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void registerSuggestionSpansForNotification(SuggestionSpan asuggestionspan[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethodManager");
            parcel.writeTypedArray(asuggestionspan, 0);
            mRemote.transact(20, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            asuggestionspan;
            parcel1.recycle();
            parcel.recycle();
            throw asuggestionspan;
        }

        public void removeClient(IInputMethodClient iinputmethodclient)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethodManager");
            if(iinputmethodclient == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iinputmethodclient.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iinputmethodclient;
            parcel1.recycle();
            parcel.recycle();
            throw iinputmethodclient;
        }

        public void reportFullscreenMode(IBinder ibinder, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethodManager");
            parcel.writeStrongBinder(ibinder);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(32, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void setAdditionalInputMethodSubtypes(String s, InputMethodSubtype ainputmethodsubtype[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethodManager");
            parcel.writeString(s);
            parcel.writeTypedArray(ainputmethodsubtype, 0);
            mRemote.transact(28, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean setCurrentInputMethodSubtype(InputMethodSubtype inputmethodsubtype)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethodManager");
            if(inputmethodsubtype == null)
                break MISSING_BLOCK_LABEL_73;
            parcel.writeInt(1);
            inputmethodsubtype.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(23, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            parcel.writeInt(0);
              goto _L1
            inputmethodsubtype;
            parcel1.recycle();
            parcel.recycle();
            throw inputmethodsubtype;
        }

        public void setImeWindowStatus(IBinder ibinder, IBinder ibinder1, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethodManager");
            parcel.writeStrongBinder(ibinder);
            parcel.writeStrongBinder(ibinder1);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(19, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void setInputMethod(IBinder ibinder, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethodManager");
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            mRemote.transact(14, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void setInputMethodAndSubtype(IBinder ibinder, String s, InputMethodSubtype inputmethodsubtype)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethodManager");
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            if(inputmethodsubtype == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            inputmethodsubtype.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(15, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public boolean setInputMethodEnabled(String s, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethodManager");
            parcel.writeString(s);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(27, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean shouldOfferSwitchingToNextInputMethod(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethodManager");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(26, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void showInputMethodAndSubtypeEnablerFromClient(IInputMethodClient iinputmethodclient, String s)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethodManager");
            if(iinputmethodclient == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = iinputmethodclient.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            mRemote.transact(13, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iinputmethodclient;
            parcel1.recycle();
            parcel.recycle();
            throw iinputmethodclient;
        }

        public void showInputMethodPickerFromClient(IInputMethodClient iinputmethodclient, int i)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethodManager");
            if(iinputmethodclient == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = iinputmethodclient.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iinputmethodclient;
            parcel1.recycle();
            parcel.recycle();
            throw iinputmethodclient;
        }

        public void showMySoftInput(IBinder ibinder, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethodManager");
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(17, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public boolean showSoftInput(IInputMethodClient iinputmethodclient, int i, ResultReceiver resultreceiver)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethodManager");
            if(iinputmethodclient == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = iinputmethodclient.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            if(resultreceiver == null)
                break MISSING_BLOCK_LABEL_110;
            parcel.writeInt(1);
            resultreceiver.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            parcel.writeInt(0);
              goto _L1
            iinputmethodclient;
            parcel1.recycle();
            parcel.recycle();
            throw iinputmethodclient;
        }

        public InputBindResult startInputOrWindowGainedFocus(int i, IInputMethodClient iinputmethodclient, IBinder ibinder, int j, int k, int l, EditorInfo editorinfo, 
                IInputContext iinputcontext, int i1)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethodManager");
            parcel.writeInt(i);
            if(iinputmethodclient == null) goto _L2; else goto _L1
_L1:
            iinputmethodclient = iinputmethodclient.asBinder();
_L5:
            parcel.writeStrongBinder(iinputmethodclient);
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeInt(l);
            if(editorinfo == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            editorinfo.writeToParcel(parcel, 0);
_L6:
            iinputmethodclient = obj;
            if(iinputcontext == null)
                break MISSING_BLOCK_LABEL_105;
            iinputmethodclient = iinputcontext.asBinder();
            parcel.writeStrongBinder(iinputmethodclient);
            parcel.writeInt(i1);
            mRemote.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_201;
            iinputmethodclient = (InputBindResult)InputBindResult.CREATOR.createFromParcel(parcel1);
_L7:
            parcel1.recycle();
            parcel.recycle();
            return iinputmethodclient;
_L2:
            iinputmethodclient = null;
              goto _L5
_L4:
            parcel.writeInt(0);
              goto _L6
            iinputmethodclient;
            parcel1.recycle();
            parcel.recycle();
            throw iinputmethodclient;
            iinputmethodclient = null;
              goto _L7
        }

        public boolean switchToLastInputMethod(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethodManager");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(24, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public boolean switchToNextInputMethod(IBinder ibinder, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethodManager");
            parcel.writeStrongBinder(ibinder);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(25, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void updateStatusIcon(IBinder ibinder, String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethodManager");
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(18, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void addClient(IInputMethodClient iinputmethodclient, IInputContext iinputcontext, int i, int j)
        throws RemoteException;

    public abstract void clearLastInputMethodWindowForTransition(IBinder ibinder)
        throws RemoteException;

    public abstract IInputContentUriToken createInputContentUriToken(IBinder ibinder, Uri uri, String s)
        throws RemoteException;

    public abstract void finishInput(IInputMethodClient iinputmethodclient)
        throws RemoteException;

    public abstract InputMethodSubtype getCurrentInputMethodSubtype()
        throws RemoteException;

    public abstract List getEnabledInputMethodList()
        throws RemoteException;

    public abstract List getEnabledInputMethodSubtypeList(String s, boolean flag)
        throws RemoteException;

    public abstract List getInputMethodList()
        throws RemoteException;

    public abstract int getInputMethodWindowVisibleHeight()
        throws RemoteException;

    public abstract InputMethodSubtype getLastInputMethodSubtype()
        throws RemoteException;

    public abstract List getShortcutInputMethodsAndSubtypes()
        throws RemoteException;

    public abstract void hideMySoftInput(IBinder ibinder, int i)
        throws RemoteException;

    public abstract boolean hideSoftInput(IInputMethodClient iinputmethodclient, int i, ResultReceiver resultreceiver)
        throws RemoteException;

    public abstract boolean notifySuggestionPicked(SuggestionSpan suggestionspan, String s, int i)
        throws RemoteException;

    public abstract void notifyUserAction(int i)
        throws RemoteException;

    public abstract void registerSuggestionSpansForNotification(SuggestionSpan asuggestionspan[])
        throws RemoteException;

    public abstract void removeClient(IInputMethodClient iinputmethodclient)
        throws RemoteException;

    public abstract void reportFullscreenMode(IBinder ibinder, boolean flag)
        throws RemoteException;

    public abstract void setAdditionalInputMethodSubtypes(String s, InputMethodSubtype ainputmethodsubtype[])
        throws RemoteException;

    public abstract boolean setCurrentInputMethodSubtype(InputMethodSubtype inputmethodsubtype)
        throws RemoteException;

    public abstract void setImeWindowStatus(IBinder ibinder, IBinder ibinder1, int i, int j)
        throws RemoteException;

    public abstract void setInputMethod(IBinder ibinder, String s)
        throws RemoteException;

    public abstract void setInputMethodAndSubtype(IBinder ibinder, String s, InputMethodSubtype inputmethodsubtype)
        throws RemoteException;

    public abstract boolean setInputMethodEnabled(String s, boolean flag)
        throws RemoteException;

    public abstract boolean shouldOfferSwitchingToNextInputMethod(IBinder ibinder)
        throws RemoteException;

    public abstract void showInputMethodAndSubtypeEnablerFromClient(IInputMethodClient iinputmethodclient, String s)
        throws RemoteException;

    public abstract void showInputMethodPickerFromClient(IInputMethodClient iinputmethodclient, int i)
        throws RemoteException;

    public abstract void showMySoftInput(IBinder ibinder, int i)
        throws RemoteException;

    public abstract boolean showSoftInput(IInputMethodClient iinputmethodclient, int i, ResultReceiver resultreceiver)
        throws RemoteException;

    public abstract InputBindResult startInputOrWindowGainedFocus(int i, IInputMethodClient iinputmethodclient, IBinder ibinder, int j, int k, int l, EditorInfo editorinfo, 
            IInputContext iinputcontext, int i1)
        throws RemoteException;

    public abstract boolean switchToLastInputMethod(IBinder ibinder)
        throws RemoteException;

    public abstract boolean switchToNextInputMethod(IBinder ibinder, boolean flag)
        throws RemoteException;

    public abstract void updateStatusIcon(IBinder ibinder, String s, int i)
        throws RemoteException;
}
