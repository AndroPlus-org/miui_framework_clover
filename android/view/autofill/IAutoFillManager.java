// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.autofill;

import android.content.ComponentName;
import android.graphics.Rect;
import android.os.*;
import android.service.autofill.FillEventHistory;

// Referenced classes of package android.view.autofill:
//            IAutoFillManagerClient, AutofillId, AutofillValue

public interface IAutoFillManager
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IAutoFillManager
    {

        public static IAutoFillManager asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.view.autofill.IAutoFillManager");
            if(iinterface != null && (iinterface instanceof IAutoFillManager))
                return (IAutoFillManager)iinterface;
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
                parcel1.writeString("android.view.autofill.IAutoFillManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.view.autofill.IAutoFillManager");
                i = addClient(IAutoFillManagerClient.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.view.autofill.IAutoFillManager");
                IBinder ibinder = parcel.readStrongBinder();
                IBinder ibinder1 = parcel.readStrongBinder();
                AutofillId autofillid;
                Rect rect;
                AutofillValue autofillvalue;
                boolean flag;
                if(parcel.readInt() != 0)
                    autofillid = (AutofillId)AutofillId.CREATOR.createFromParcel(parcel);
                else
                    autofillid = null;
                if(parcel.readInt() != 0)
                    rect = (Rect)Rect.CREATOR.createFromParcel(parcel);
                else
                    rect = null;
                if(parcel.readInt() != 0)
                    autofillvalue = (AutofillValue)AutofillValue.CREATOR.createFromParcel(parcel);
                else
                    autofillvalue = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                j = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = startSession(ibinder, ibinder1, autofillid, rect, autofillvalue, i, flag, j, parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.view.autofill.IAutoFillManager");
                parcel = getFillEventHistory();
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

            case 4: // '\004'
                parcel.enforceInterface("android.view.autofill.IAutoFillManager");
                boolean flag1 = restoreSession(parcel.readInt(), parcel.readStrongBinder(), parcel.readStrongBinder());
                parcel1.writeNoException();
                if(flag1)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.view.autofill.IAutoFillManager");
                i = parcel.readInt();
                AutofillId autofillid1;
                Rect rect1;
                AutofillValue autofillvalue1;
                if(parcel.readInt() != 0)
                    autofillid1 = (AutofillId)AutofillId.CREATOR.createFromParcel(parcel);
                else
                    autofillid1 = null;
                if(parcel.readInt() != 0)
                    rect1 = (Rect)Rect.CREATOR.createFromParcel(parcel);
                else
                    rect1 = null;
                if(parcel.readInt() != 0)
                    autofillvalue1 = (AutofillValue)AutofillValue.CREATOR.createFromParcel(parcel);
                else
                    autofillvalue1 = null;
                updateSession(i, autofillid1, rect1, autofillvalue1, parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.view.autofill.IAutoFillManager");
                IBinder ibinder2 = parcel.readStrongBinder();
                IBinder ibinder3 = parcel.readStrongBinder();
                ComponentName componentname;
                AutofillId autofillid2;
                Rect rect2;
                AutofillValue autofillvalue2;
                boolean flag2;
                if(parcel.readInt() != 0)
                    autofillid2 = (AutofillId)AutofillId.CREATOR.createFromParcel(parcel);
                else
                    autofillid2 = null;
                if(parcel.readInt() != 0)
                    rect2 = (Rect)Rect.CREATOR.createFromParcel(parcel);
                else
                    rect2 = null;
                if(parcel.readInt() != 0)
                    autofillvalue2 = (AutofillValue)AutofillValue.CREATOR.createFromParcel(parcel);
                else
                    autofillvalue2 = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    flag2 = true;
                else
                    flag2 = false;
                j = parcel.readInt();
                if(parcel.readInt() != 0)
                    componentname = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname = null;
                i = updateOrRestartSession(ibinder2, ibinder3, autofillid2, rect2, autofillvalue2, i, flag2, j, componentname, parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.view.autofill.IAutoFillManager");
                finishSession(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.view.autofill.IAutoFillManager");
                cancelSession(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.view.autofill.IAutoFillManager");
                Bundle bundle;
                if(parcel.readInt() != 0)
                    bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle = null;
                setAuthenticationResult(bundle, parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.view.autofill.IAutoFillManager");
                j = parcel.readInt();
                i = parcel.readInt();
                boolean flag3;
                if(parcel.readInt() != 0)
                    flag3 = true;
                else
                    flag3 = false;
                setHasCallback(j, i, flag3);
                parcel1.writeNoException();
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.view.autofill.IAutoFillManager");
                disableOwnedAutofillServices(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.view.autofill.IAutoFillManager");
                boolean flag4 = isServiceSupported(parcel.readInt());
                parcel1.writeNoException();
                if(flag4)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.view.autofill.IAutoFillManager");
                boolean flag5 = isServiceEnabled(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                if(flag5)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.view.autofill.IAutoFillManager");
                onPendingSaveUi(parcel.readInt(), parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.view.autofill.IAutoFillManager";
        static final int TRANSACTION_addClient = 1;
        static final int TRANSACTION_cancelSession = 8;
        static final int TRANSACTION_disableOwnedAutofillServices = 11;
        static final int TRANSACTION_finishSession = 7;
        static final int TRANSACTION_getFillEventHistory = 3;
        static final int TRANSACTION_isServiceEnabled = 13;
        static final int TRANSACTION_isServiceSupported = 12;
        static final int TRANSACTION_onPendingSaveUi = 14;
        static final int TRANSACTION_restoreSession = 4;
        static final int TRANSACTION_setAuthenticationResult = 9;
        static final int TRANSACTION_setHasCallback = 10;
        static final int TRANSACTION_startSession = 2;
        static final int TRANSACTION_updateOrRestartSession = 6;
        static final int TRANSACTION_updateSession = 5;

        public Stub()
        {
            attachInterface(this, "android.view.autofill.IAutoFillManager");
        }
    }

    private static class Stub.Proxy
        implements IAutoFillManager
    {

        public int addClient(IAutoFillManagerClient iautofillmanagerclient, int i)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.autofill.IAutoFillManager");
            if(iautofillmanagerclient == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = iautofillmanagerclient.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            iautofillmanagerclient;
            parcel1.recycle();
            parcel.recycle();
            throw iautofillmanagerclient;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void cancelSession(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.autofill.IAutoFillManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void disableOwnedAutofillServices(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.autofill.IAutoFillManager");
            parcel.writeInt(i);
            mRemote.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void finishSession(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.autofill.IAutoFillManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public FillEventHistory getFillEventHistory()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.autofill.IAutoFillManager");
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            FillEventHistory filleventhistory = (FillEventHistory)FillEventHistory.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return filleventhistory;
_L2:
            filleventhistory = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "android.view.autofill.IAutoFillManager";
        }

        public boolean isServiceEnabled(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.autofill.IAutoFillManager");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(13, parcel, parcel1, 0);
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
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean isServiceSupported(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.autofill.IAutoFillManager");
            parcel.writeInt(i);
            mRemote.transact(12, parcel, parcel1, 0);
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void onPendingSaveUi(int i, IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.autofill.IAutoFillManager");
            parcel.writeInt(i);
            parcel.writeStrongBinder(ibinder);
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

        public boolean restoreSession(int i, IBinder ibinder, IBinder ibinder1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.autofill.IAutoFillManager");
            parcel.writeInt(i);
            parcel.writeStrongBinder(ibinder);
            parcel.writeStrongBinder(ibinder1);
            mRemote.transact(4, parcel, parcel1, 0);
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

        public void setAuthenticationResult(Bundle bundle, int i, int j, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.autofill.IAutoFillManager");
            if(bundle == null)
                break MISSING_BLOCK_LABEL_86;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            bundle;
            parcel1.recycle();
            parcel.recycle();
            throw bundle;
        }

        public void setHasCallback(int i, int j, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.autofill.IAutoFillManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int startSession(IBinder ibinder, IBinder ibinder1, AutofillId autofillid, Rect rect, AutofillValue autofillvalue, int i, boolean flag, 
                int j, ComponentName componentname)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.autofill.IAutoFillManager");
            parcel.writeStrongBinder(ibinder);
            parcel.writeStrongBinder(ibinder1);
            if(autofillid == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            autofillid.writeToParcel(parcel, 0);
_L7:
            if(rect == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            rect.writeToParcel(parcel, 0);
_L8:
            if(autofillvalue == null) goto _L6; else goto _L5
_L5:
            parcel.writeInt(1);
            autofillvalue.writeToParcel(parcel, 0);
_L9:
            parcel.writeInt(i);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeInt(j);
            if(componentname == null)
                break MISSING_BLOCK_LABEL_223;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L10:
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
_L2:
            parcel.writeInt(0);
              goto _L7
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
_L4:
            parcel.writeInt(0);
              goto _L8
_L6:
            parcel.writeInt(0);
              goto _L9
            parcel.writeInt(0);
              goto _L10
        }

        public int updateOrRestartSession(IBinder ibinder, IBinder ibinder1, AutofillId autofillid, Rect rect, AutofillValue autofillvalue, int i, boolean flag, 
                int j, ComponentName componentname, int k, int l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.autofill.IAutoFillManager");
            parcel.writeStrongBinder(ibinder);
            parcel.writeStrongBinder(ibinder1);
            if(autofillid == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            autofillid.writeToParcel(parcel, 0);
_L7:
            if(rect == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            rect.writeToParcel(parcel, 0);
_L8:
            if(autofillvalue == null) goto _L6; else goto _L5
_L5:
            parcel.writeInt(1);
            autofillvalue.writeToParcel(parcel, 0);
_L9:
            parcel.writeInt(i);
            if(flag)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeInt(j);
            if(componentname == null)
                break MISSING_BLOCK_LABEL_234;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L10:
            parcel.writeInt(k);
            parcel.writeInt(l);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
_L2:
            parcel.writeInt(0);
              goto _L7
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
_L4:
            parcel.writeInt(0);
              goto _L8
_L6:
            parcel.writeInt(0);
              goto _L9
            parcel.writeInt(0);
              goto _L10
        }

        public void updateSession(int i, AutofillId autofillid, Rect rect, AutofillValue autofillvalue, int j, int k, int l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.autofill.IAutoFillManager");
            parcel.writeInt(i);
            if(autofillid == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            autofillid.writeToParcel(parcel, 0);
_L5:
            if(rect == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            rect.writeToParcel(parcel, 0);
_L6:
            if(autofillvalue == null)
                break MISSING_BLOCK_LABEL_160;
            parcel.writeInt(1);
            autofillvalue.writeToParcel(parcel, 0);
_L7:
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeInt(l);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L5
            autofillid;
            parcel1.recycle();
            parcel.recycle();
            throw autofillid;
_L4:
            parcel.writeInt(0);
              goto _L6
            parcel.writeInt(0);
              goto _L7
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract int addClient(IAutoFillManagerClient iautofillmanagerclient, int i)
        throws RemoteException;

    public abstract void cancelSession(int i, int j)
        throws RemoteException;

    public abstract void disableOwnedAutofillServices(int i)
        throws RemoteException;

    public abstract void finishSession(int i, int j)
        throws RemoteException;

    public abstract FillEventHistory getFillEventHistory()
        throws RemoteException;

    public abstract boolean isServiceEnabled(int i, String s)
        throws RemoteException;

    public abstract boolean isServiceSupported(int i)
        throws RemoteException;

    public abstract void onPendingSaveUi(int i, IBinder ibinder)
        throws RemoteException;

    public abstract boolean restoreSession(int i, IBinder ibinder, IBinder ibinder1)
        throws RemoteException;

    public abstract void setAuthenticationResult(Bundle bundle, int i, int j, int k)
        throws RemoteException;

    public abstract void setHasCallback(int i, int j, boolean flag)
        throws RemoteException;

    public abstract int startSession(IBinder ibinder, IBinder ibinder1, AutofillId autofillid, Rect rect, AutofillValue autofillvalue, int i, boolean flag, 
            int j, ComponentName componentname)
        throws RemoteException;

    public abstract int updateOrRestartSession(IBinder ibinder, IBinder ibinder1, AutofillId autofillid, Rect rect, AutofillValue autofillvalue, int i, boolean flag, 
            int j, ComponentName componentname, int k, int l)
        throws RemoteException;

    public abstract void updateSession(int i, AutofillId autofillid, Rect rect, AutofillValue autofillvalue, int j, int k, int l)
        throws RemoteException;
}
