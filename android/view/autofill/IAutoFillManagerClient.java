// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.autofill;

import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Rect;
import android.os.*;
import java.util.List;

// Referenced classes of package android.view.autofill:
//            AutofillId, IAutofillWindowPresenter, AutofillValue

public interface IAutoFillManagerClient
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IAutoFillManagerClient
    {

        public static IAutoFillManagerClient asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.view.autofill.IAutoFillManagerClient");
            if(iinterface != null && (iinterface instanceof IAutoFillManagerClient))
                return (IAutoFillManagerClient)iinterface;
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
                parcel1.writeString("android.view.autofill.IAutoFillManagerClient");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.view.autofill.IAutoFillManagerClient");
                boolean flag;
                boolean flag4;
                boolean flag5;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                if(parcel.readInt() != 0)
                    flag4 = true;
                else
                    flag4 = false;
                if(parcel.readInt() != 0)
                    flag5 = true;
                else
                    flag5 = false;
                setState(flag, flag4, flag5);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.view.autofill.IAutoFillManagerClient");
                autofill(parcel.readInt(), parcel.createTypedArrayList(AutofillId.CREATOR), parcel.createTypedArrayList(AutofillValue.CREATOR));
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.view.autofill.IAutoFillManagerClient");
                j = parcel.readInt();
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel1 = (IntentSender)IntentSender.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    parcel = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                authenticate(j, i, parcel1, parcel);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.view.autofill.IAutoFillManagerClient");
                i = parcel.readInt();
                parcel1 = (AutofillId[])parcel.createTypedArray(AutofillId.CREATOR);
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                setTrackedViews(i, parcel1, flag1, (AutofillId[])parcel.createTypedArray(AutofillId.CREATOR));
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.view.autofill.IAutoFillManagerClient");
                j = parcel.readInt();
                int k;
                Rect rect;
                if(parcel.readInt() != 0)
                    parcel1 = (AutofillId)AutofillId.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                k = parcel.readInt();
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    rect = (Rect)Rect.CREATOR.createFromParcel(parcel);
                else
                    rect = null;
                requestShowFillUi(j, parcel1, k, i, rect, IAutofillWindowPresenter.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.view.autofill.IAutoFillManagerClient");
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (AutofillId)AutofillId.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                requestHideFillUi(i, parcel);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.view.autofill.IAutoFillManagerClient");
                i = parcel.readInt();
                boolean flag2;
                if(parcel.readInt() != 0)
                    parcel1 = (AutofillId)AutofillId.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    flag2 = true;
                else
                    flag2 = false;
                notifyNoFillUi(i, parcel1, flag2);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.view.autofill.IAutoFillManagerClient");
                if(parcel.readInt() != 0)
                    parcel1 = (IntentSender)IntentSender.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    parcel = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                startIntentSender(parcel1, parcel);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.view.autofill.IAutoFillManagerClient");
                i = parcel.readInt();
                boolean flag3;
                if(parcel.readInt() != 0)
                    flag3 = true;
                else
                    flag3 = false;
                setSaveUiState(i, flag3);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.view.autofill.IAutoFillManagerClient");
                setSessionFinished(parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.view.autofill.IAutoFillManagerClient";
        static final int TRANSACTION_authenticate = 3;
        static final int TRANSACTION_autofill = 2;
        static final int TRANSACTION_notifyNoFillUi = 7;
        static final int TRANSACTION_requestHideFillUi = 6;
        static final int TRANSACTION_requestShowFillUi = 5;
        static final int TRANSACTION_setSaveUiState = 9;
        static final int TRANSACTION_setSessionFinished = 10;
        static final int TRANSACTION_setState = 1;
        static final int TRANSACTION_setTrackedViews = 4;
        static final int TRANSACTION_startIntentSender = 8;

        public Stub()
        {
            attachInterface(this, "android.view.autofill.IAutoFillManagerClient");
        }
    }

    private static class Stub.Proxy
        implements IAutoFillManagerClient
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void authenticate(int i, int j, IntentSender intentsender, Intent intent)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.autofill.IAutoFillManagerClient");
            parcel.writeInt(i);
            parcel.writeInt(j);
            if(intentsender == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            intentsender.writeToParcel(parcel, 0);
_L3:
            if(intent == null)
                break MISSING_BLOCK_LABEL_98;
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            intentsender;
            parcel.recycle();
            throw intentsender;
            parcel.writeInt(0);
              goto _L4
        }

        public void autofill(int i, List list, List list1)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.autofill.IAutoFillManagerClient");
            parcel.writeInt(i);
            parcel.writeTypedList(list);
            parcel.writeTypedList(list1);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            list;
            parcel.recycle();
            throw list;
        }

        public String getInterfaceDescriptor()
        {
            return "android.view.autofill.IAutoFillManagerClient";
        }

        public void notifyNoFillUi(int i, AutofillId autofillid, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.autofill.IAutoFillManagerClient");
            parcel.writeInt(i);
            if(autofillid == null)
                break MISSING_BLOCK_LABEL_73;
            parcel.writeInt(1);
            autofillid.writeToParcel(parcel, 0);
_L1:
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            autofillid;
            parcel.recycle();
            throw autofillid;
        }

        public void requestHideFillUi(int i, AutofillId autofillid)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.autofill.IAutoFillManagerClient");
            parcel.writeInt(i);
            if(autofillid == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            autofillid.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            autofillid;
            parcel.recycle();
            throw autofillid;
        }

        public void requestShowFillUi(int i, AutofillId autofillid, int j, int k, Rect rect, IAutofillWindowPresenter iautofillwindowpresenter)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.autofill.IAutoFillManagerClient");
            parcel.writeInt(i);
            if(autofillid == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            autofillid.writeToParcel(parcel, 0);
_L3:
            parcel.writeInt(j);
            parcel.writeInt(k);
            if(rect == null)
                break MISSING_BLOCK_LABEL_130;
            parcel.writeInt(1);
            rect.writeToParcel(parcel, 0);
_L4:
            autofillid = obj;
            if(iautofillwindowpresenter == null)
                break MISSING_BLOCK_LABEL_86;
            autofillid = iautofillwindowpresenter.asBinder();
            parcel.writeStrongBinder(autofillid);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            autofillid;
            parcel.recycle();
            throw autofillid;
            parcel.writeInt(0);
              goto _L4
        }

        public void setSaveUiState(int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.autofill.IAutoFillManagerClient");
            parcel.writeInt(i);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(9, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void setSessionFinished(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.autofill.IAutoFillManagerClient");
            parcel.writeInt(i);
            mRemote.transact(10, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void setState(boolean flag, boolean flag1, boolean flag2)
            throws RemoteException
        {
            boolean flag3;
            Parcel parcel;
            flag3 = true;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.autofill.IAutoFillManagerClient");
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(flag1)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(flag2)
                i = ((flag3) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void setTrackedViews(int i, AutofillId aautofillid[], boolean flag, AutofillId aautofillid1[])
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.autofill.IAutoFillManagerClient");
            parcel.writeInt(i);
            parcel.writeTypedArray(aautofillid, 0);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeTypedArray(aautofillid1, 0);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            aautofillid;
            parcel.recycle();
            throw aautofillid;
        }

        public void startIntentSender(IntentSender intentsender, Intent intent)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.autofill.IAutoFillManagerClient");
            if(intentsender == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            intentsender.writeToParcel(parcel, 0);
_L3:
            if(intent == null)
                break MISSING_BLOCK_LABEL_75;
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(8, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            intentsender;
            parcel.recycle();
            throw intentsender;
            parcel.writeInt(0);
              goto _L4
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void authenticate(int i, int j, IntentSender intentsender, Intent intent)
        throws RemoteException;

    public abstract void autofill(int i, List list, List list1)
        throws RemoteException;

    public abstract void notifyNoFillUi(int i, AutofillId autofillid, boolean flag)
        throws RemoteException;

    public abstract void requestHideFillUi(int i, AutofillId autofillid)
        throws RemoteException;

    public abstract void requestShowFillUi(int i, AutofillId autofillid, int j, int k, Rect rect, IAutofillWindowPresenter iautofillwindowpresenter)
        throws RemoteException;

    public abstract void setSaveUiState(int i, boolean flag)
        throws RemoteException;

    public abstract void setSessionFinished(int i)
        throws RemoteException;

    public abstract void setState(boolean flag, boolean flag1, boolean flag2)
        throws RemoteException;

    public abstract void setTrackedViews(int i, AutofillId aautofillid[], boolean flag, AutofillId aautofillid1[])
        throws RemoteException;

    public abstract void startIntentSender(IntentSender intentsender, Intent intent)
        throws RemoteException;
}
