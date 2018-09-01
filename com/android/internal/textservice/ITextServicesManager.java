// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.textservice;

import android.os.*;
import android.view.textservice.SpellCheckerInfo;
import android.view.textservice.SpellCheckerSubtype;

// Referenced classes of package com.android.internal.textservice:
//            ISpellCheckerSessionListener, ITextServicesSessionListener

public interface ITextServicesManager
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ITextServicesManager
    {

        public static ITextServicesManager asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.textservice.ITextServicesManager");
            if(iinterface != null && (iinterface instanceof ITextServicesManager))
                return (ITextServicesManager)iinterface;
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
                parcel1.writeString("com.android.internal.textservice.ITextServicesManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.textservice.ITextServicesManager");
                parcel = getCurrentSpellChecker(parcel.readString());
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

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.textservice.ITextServicesManager");
                String s = parcel.readString();
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                parcel = getCurrentSpellCheckerSubtype(s, flag);
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

            case 3: // '\003'
                parcel.enforceInterface("com.android.internal.textservice.ITextServicesManager");
                parcel1 = parcel.readString();
                String s1 = parcel.readString();
                ITextServicesSessionListener itextservicessessionlistener = ITextServicesSessionListener.Stub.asInterface(parcel.readStrongBinder());
                ISpellCheckerSessionListener ispellcheckersessionlistener = ISpellCheckerSessionListener.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                getSpellCheckerService(parcel1, s1, itextservicessessionlistener, ispellcheckersessionlistener, parcel);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.internal.textservice.ITextServicesManager");
                finishSpellCheckerService(ISpellCheckerSessionListener.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.android.internal.textservice.ITextServicesManager");
                boolean flag1 = isSpellCheckerEnabled();
                parcel1.writeNoException();
                if(flag1)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.android.internal.textservice.ITextServicesManager");
                parcel = getEnabledSpellCheckers();
                parcel1.writeNoException();
                parcel1.writeTypedArray(parcel, 1);
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.internal.textservice.ITextServicesManager";
        static final int TRANSACTION_finishSpellCheckerService = 4;
        static final int TRANSACTION_getCurrentSpellChecker = 1;
        static final int TRANSACTION_getCurrentSpellCheckerSubtype = 2;
        static final int TRANSACTION_getEnabledSpellCheckers = 6;
        static final int TRANSACTION_getSpellCheckerService = 3;
        static final int TRANSACTION_isSpellCheckerEnabled = 5;

        public Stub()
        {
            attachInterface(this, "com.android.internal.textservice.ITextServicesManager");
        }
    }

    private static class Stub.Proxy
        implements ITextServicesManager
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void finishSpellCheckerService(ISpellCheckerSessionListener ispellcheckersessionlistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.textservice.ITextServicesManager");
            if(ispellcheckersessionlistener == null)
                break MISSING_BLOCK_LABEL_23;
            ibinder = ispellcheckersessionlistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            ispellcheckersessionlistener;
            parcel.recycle();
            throw ispellcheckersessionlistener;
        }

        public SpellCheckerInfo getCurrentSpellChecker(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.textservice.ITextServicesManager");
            parcel.writeString(s);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (SpellCheckerInfo)SpellCheckerInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            s = null;
            if(true) goto _L4; else goto _L3
_L3:
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public SpellCheckerSubtype getCurrentSpellCheckerSubtype(String s, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.textservice.ITextServicesManager");
            parcel.writeString(s);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (SpellCheckerSubtype)SpellCheckerSubtype.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            s = null;
            if(true) goto _L4; else goto _L3
_L3:
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public SpellCheckerInfo[] getEnabledSpellCheckers()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            SpellCheckerInfo aspellcheckerinfo[];
            parcel.writeInterfaceToken("com.android.internal.textservice.ITextServicesManager");
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            aspellcheckerinfo = (SpellCheckerInfo[])parcel1.createTypedArray(SpellCheckerInfo.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return aspellcheckerinfo;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.textservice.ITextServicesManager";
        }

        public void getSpellCheckerService(String s, String s1, ITextServicesSessionListener itextservicessessionlistener, ISpellCheckerSessionListener ispellcheckersessionlistener, Bundle bundle)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.textservice.ITextServicesManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            if(itextservicessessionlistener == null) goto _L2; else goto _L1
_L1:
            s = itextservicessessionlistener.asBinder();
_L5:
            parcel.writeStrongBinder(s);
            s = obj;
            if(ispellcheckersessionlistener == null)
                break MISSING_BLOCK_LABEL_60;
            s = ispellcheckersessionlistener.asBinder();
            parcel.writeStrongBinder(s);
            if(bundle == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L6:
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            s = null;
              goto _L5
_L4:
            parcel.writeInt(0);
              goto _L6
            s;
            parcel.recycle();
            throw s;
              goto _L5
        }

        public boolean isSpellCheckerEnabled()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.textservice.ITextServicesManager");
            mRemote.transact(5, parcel, parcel1, 0);
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

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void finishSpellCheckerService(ISpellCheckerSessionListener ispellcheckersessionlistener)
        throws RemoteException;

    public abstract SpellCheckerInfo getCurrentSpellChecker(String s)
        throws RemoteException;

    public abstract SpellCheckerSubtype getCurrentSpellCheckerSubtype(String s, boolean flag)
        throws RemoteException;

    public abstract SpellCheckerInfo[] getEnabledSpellCheckers()
        throws RemoteException;

    public abstract void getSpellCheckerService(String s, String s1, ITextServicesSessionListener itextservicessessionlistener, ISpellCheckerSessionListener ispellcheckersessionlistener, Bundle bundle)
        throws RemoteException;

    public abstract boolean isSpellCheckerEnabled()
        throws RemoteException;
}
