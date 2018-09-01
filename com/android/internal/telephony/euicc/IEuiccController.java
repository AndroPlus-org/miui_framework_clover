// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telephony.euicc;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.*;
import android.telephony.euicc.DownloadableSubscription;
import android.telephony.euicc.EuiccInfo;

public interface IEuiccController
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IEuiccController
    {

        public static IEuiccController asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.telephony.euicc.IEuiccController");
            if(iinterface != null && (iinterface instanceof IEuiccController))
                return (IEuiccController)iinterface;
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
                parcel1.writeString("com.android.internal.telephony.euicc.IEuiccController");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.telephony.euicc.IEuiccController");
                if(parcel.readInt() != 0)
                    parcel1 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                continueOperation(parcel1, parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.telephony.euicc.IEuiccController");
                String s;
                if(parcel.readInt() != 0)
                    parcel1 = (DownloadableSubscription)DownloadableSubscription.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                s = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                getDownloadableSubscriptionMetadata(parcel1, s, parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.internal.telephony.euicc.IEuiccController");
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                getDefaultDownloadableSubscriptionList(parcel1, parcel);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.internal.telephony.euicc.IEuiccController");
                parcel = getEid();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.android.internal.telephony.euicc.IEuiccController");
                String s1;
                boolean flag;
                if(parcel.readInt() != 0)
                    parcel1 = (DownloadableSubscription)DownloadableSubscription.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                s1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                downloadSubscription(parcel1, flag, s1, parcel);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.android.internal.telephony.euicc.IEuiccController");
                parcel = getEuiccInfo();
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

            case 7: // '\007'
                parcel.enforceInterface("com.android.internal.telephony.euicc.IEuiccController");
                i = parcel.readInt();
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                deleteSubscription(i, parcel1, parcel);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.android.internal.telephony.euicc.IEuiccController");
                i = parcel.readInt();
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                switchToSubscription(i, parcel1, parcel);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.android.internal.telephony.euicc.IEuiccController");
                i = parcel.readInt();
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                updateSubscriptionNickname(i, parcel1, parcel);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("com.android.internal.telephony.euicc.IEuiccController");
                if(parcel.readInt() != 0)
                    parcel = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                eraseSubscriptions(parcel);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("com.android.internal.telephony.euicc.IEuiccController");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            retainSubscriptionsForFactoryReset(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "com.android.internal.telephony.euicc.IEuiccController";
        static final int TRANSACTION_continueOperation = 1;
        static final int TRANSACTION_deleteSubscription = 7;
        static final int TRANSACTION_downloadSubscription = 5;
        static final int TRANSACTION_eraseSubscriptions = 10;
        static final int TRANSACTION_getDefaultDownloadableSubscriptionList = 3;
        static final int TRANSACTION_getDownloadableSubscriptionMetadata = 2;
        static final int TRANSACTION_getEid = 4;
        static final int TRANSACTION_getEuiccInfo = 6;
        static final int TRANSACTION_retainSubscriptionsForFactoryReset = 11;
        static final int TRANSACTION_switchToSubscription = 8;
        static final int TRANSACTION_updateSubscriptionNickname = 9;

        public Stub()
        {
            attachInterface(this, "com.android.internal.telephony.euicc.IEuiccController");
        }
    }

    private static class Stub.Proxy
        implements IEuiccController
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void continueOperation(Intent intent, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.euicc.IEuiccController");
            if(intent == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L3:
            if(bundle == null)
                break MISSING_BLOCK_LABEL_74;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            intent;
            parcel.recycle();
            throw intent;
            parcel.writeInt(0);
              goto _L4
        }

        public void deleteSubscription(int i, String s, PendingIntent pendingintent)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.euicc.IEuiccController");
            parcel.writeInt(i);
            parcel.writeString(s);
            if(pendingintent == null)
                break MISSING_BLOCK_LABEL_63;
            parcel.writeInt(1);
            pendingintent.writeToParcel(parcel, 0);
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

        public void downloadSubscription(DownloadableSubscription downloadablesubscription, boolean flag, String s, PendingIntent pendingintent)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.euicc.IEuiccController");
            if(downloadablesubscription == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            downloadablesubscription.writeToParcel(parcel, 0);
_L3:
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            parcel.writeString(s);
            if(pendingintent == null)
                break MISSING_BLOCK_LABEL_112;
            parcel.writeInt(1);
            pendingintent.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            downloadablesubscription;
            parcel.recycle();
            throw downloadablesubscription;
            parcel.writeInt(0);
              goto _L4
        }

        public void eraseSubscriptions(PendingIntent pendingintent)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.euicc.IEuiccController");
            if(pendingintent == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            pendingintent.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(10, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            pendingintent;
            parcel.recycle();
            throw pendingintent;
        }

        public void getDefaultDownloadableSubscriptionList(String s, PendingIntent pendingintent)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.euicc.IEuiccController");
            parcel.writeString(s);
            if(pendingintent == null)
                break MISSING_BLOCK_LABEL_49;
            parcel.writeInt(1);
            pendingintent.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void getDownloadableSubscriptionMetadata(DownloadableSubscription downloadablesubscription, String s, PendingIntent pendingintent)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.euicc.IEuiccController");
            if(downloadablesubscription == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            downloadablesubscription.writeToParcel(parcel, 0);
_L3:
            parcel.writeString(s);
            if(pendingintent == null)
                break MISSING_BLOCK_LABEL_90;
            parcel.writeInt(1);
            pendingintent.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            downloadablesubscription;
            parcel.recycle();
            throw downloadablesubscription;
            parcel.writeInt(0);
              goto _L4
        }

        public String getEid()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.android.internal.telephony.euicc.IEuiccController");
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public EuiccInfo getEuiccInfo()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.euicc.IEuiccController");
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            EuiccInfo euiccinfo = (EuiccInfo)EuiccInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return euiccinfo;
_L2:
            euiccinfo = null;
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
            return "com.android.internal.telephony.euicc.IEuiccController";
        }

        public void retainSubscriptionsForFactoryReset(PendingIntent pendingintent)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.euicc.IEuiccController");
            if(pendingintent == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            pendingintent.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(11, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            pendingintent;
            parcel.recycle();
            throw pendingintent;
        }

        public void switchToSubscription(int i, String s, PendingIntent pendingintent)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.euicc.IEuiccController");
            parcel.writeInt(i);
            parcel.writeString(s);
            if(pendingintent == null)
                break MISSING_BLOCK_LABEL_63;
            parcel.writeInt(1);
            pendingintent.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(8, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void updateSubscriptionNickname(int i, String s, PendingIntent pendingintent)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.euicc.IEuiccController");
            parcel.writeInt(i);
            parcel.writeString(s);
            if(pendingintent == null)
                break MISSING_BLOCK_LABEL_63;
            parcel.writeInt(1);
            pendingintent.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(9, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void continueOperation(Intent intent, Bundle bundle)
        throws RemoteException;

    public abstract void deleteSubscription(int i, String s, PendingIntent pendingintent)
        throws RemoteException;

    public abstract void downloadSubscription(DownloadableSubscription downloadablesubscription, boolean flag, String s, PendingIntent pendingintent)
        throws RemoteException;

    public abstract void eraseSubscriptions(PendingIntent pendingintent)
        throws RemoteException;

    public abstract void getDefaultDownloadableSubscriptionList(String s, PendingIntent pendingintent)
        throws RemoteException;

    public abstract void getDownloadableSubscriptionMetadata(DownloadableSubscription downloadablesubscription, String s, PendingIntent pendingintent)
        throws RemoteException;

    public abstract String getEid()
        throws RemoteException;

    public abstract EuiccInfo getEuiccInfo()
        throws RemoteException;

    public abstract void retainSubscriptionsForFactoryReset(PendingIntent pendingintent)
        throws RemoteException;

    public abstract void switchToSubscription(int i, String s, PendingIntent pendingintent)
        throws RemoteException;

    public abstract void updateSubscriptionNickname(int i, String s, PendingIntent pendingintent)
        throws RemoteException;
}
