// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.autofill;

import android.graphics.Rect;
import android.os.*;

public interface IAutofillWindowPresenter
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IAutofillWindowPresenter
    {

        public static IAutofillWindowPresenter asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.view.autofill.IAutofillWindowPresenter");
            if(iinterface != null && (iinterface instanceof IAutofillWindowPresenter))
                return (IAutofillWindowPresenter)iinterface;
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
                parcel1.writeString("android.view.autofill.IAutofillWindowPresenter");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.view.autofill.IAutofillWindowPresenter");
                Rect rect;
                boolean flag;
                if(parcel.readInt() != 0)
                    parcel1 = (android.view.WindowManager.LayoutParams)android.view.WindowManager.LayoutParams.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    rect = (Rect)Rect.CREATOR.createFromParcel(parcel);
                else
                    rect = null;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                show(parcel1, rect, flag, parcel.readInt());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.view.autofill.IAutofillWindowPresenter");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (Rect)Rect.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            hide(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.view.autofill.IAutofillWindowPresenter";
        static final int TRANSACTION_hide = 2;
        static final int TRANSACTION_show = 1;

        public Stub()
        {
            attachInterface(this, "android.view.autofill.IAutofillWindowPresenter");
        }
    }

    private static class Stub.Proxy
        implements IAutofillWindowPresenter
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.view.autofill.IAutofillWindowPresenter";
        }

        public void hide(Rect rect)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.autofill.IAutofillWindowPresenter");
            if(rect == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            rect.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            rect;
            parcel.recycle();
            throw rect;
        }

        public void show(android.view.WindowManager.LayoutParams layoutparams, Rect rect, boolean flag, int i)
            throws RemoteException
        {
            int j;
            Parcel parcel;
            j = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.autofill.IAutofillWindowPresenter");
            if(layoutparams == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            layoutparams.writeToParcel(parcel, 0);
_L3:
            if(rect == null)
                break MISSING_BLOCK_LABEL_105;
            parcel.writeInt(1);
            rect.writeToParcel(parcel, 0);
_L4:
            if(!flag)
                j = 0;
            parcel.writeInt(j);
            parcel.writeInt(i);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            layoutparams;
            parcel.recycle();
            throw layoutparams;
            parcel.writeInt(0);
              goto _L4
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void hide(Rect rect)
        throws RemoteException;

    public abstract void show(android.view.WindowManager.LayoutParams layoutparams, Rect rect, boolean flag, int i)
        throws RemoteException;
}
