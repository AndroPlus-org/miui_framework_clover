// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telephony;

import android.app.PendingIntent;
import android.net.Uri;
import android.os.*;
import java.util.List;

// Referenced classes of package com.android.internal.telephony:
//            SmsRawData

public interface ISms
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ISms
    {

        public static ISms asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.telephony.ISms");
            if(iinterface != null && (iinterface instanceof ISms))
                return (ISms)iinterface;
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
                parcel1.writeString("com.android.internal.telephony.ISms");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.telephony.ISms");
                parcel = getAllMessagesFromIccEfForSubscriber(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.telephony.ISms");
                boolean flag = updateMessageOnIccEfForSubscriber(parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.createByteArray());
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.internal.telephony.ISms");
                boolean flag1 = copyMessageToIccEfForSubscriber(parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.createByteArray(), parcel.createByteArray());
                parcel1.writeNoException();
                if(flag1)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.internal.telephony.ISms");
                j = parcel.readInt();
                String s = parcel.readString();
                String s5 = parcel.readString();
                String s11 = parcel.readString();
                i = parcel.readInt();
                byte abyte2[] = parcel.createByteArray();
                PendingIntent pendingintent4;
                if(parcel.readInt() != 0)
                    pendingintent4 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                else
                    pendingintent4 = null;
                if(parcel.readInt() != 0)
                    parcel = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                sendDataForSubscriber(j, s, s5, s11, i, abyte2, pendingintent4, parcel);
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.android.internal.telephony.ISms");
                j = parcel.readInt();
                String s6 = parcel.readString();
                String s17 = parcel.readString();
                String s1 = parcel.readString();
                i = parcel.readInt();
                byte abyte1[] = parcel.createByteArray();
                PendingIntent pendingintent5;
                if(parcel.readInt() != 0)
                    pendingintent5 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                else
                    pendingintent5 = null;
                if(parcel.readInt() != 0)
                    parcel = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                sendDataForSubscriberWithSelfPermissions(j, s6, s17, s1, i, abyte1, pendingintent5, parcel);
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.android.internal.telephony.ISms");
                i = parcel.readInt();
                String s25 = parcel.readString();
                String s7 = parcel.readString();
                String s12 = parcel.readString();
                String s18 = parcel.readString();
                boolean flag2;
                PendingIntent pendingintent;
                PendingIntent pendingintent6;
                if(parcel.readInt() != 0)
                    pendingintent6 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                else
                    pendingintent6 = null;
                if(parcel.readInt() != 0)
                    pendingintent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                else
                    pendingintent = null;
                if(parcel.readInt() != 0)
                    flag2 = true;
                else
                    flag2 = false;
                sendTextForSubscriber(i, s25, s7, s12, s18, pendingintent6, pendingintent, flag2);
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.android.internal.telephony.ISms");
                i = parcel.readInt();
                String s26 = parcel.readString();
                String s13 = parcel.readString();
                String s8 = parcel.readString();
                String s19 = parcel.readString();
                boolean flag3;
                PendingIntent pendingintent1;
                PendingIntent pendingintent7;
                if(parcel.readInt() != 0)
                    pendingintent7 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                else
                    pendingintent7 = null;
                if(parcel.readInt() != 0)
                    pendingintent1 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                else
                    pendingintent1 = null;
                if(parcel.readInt() != 0)
                    flag3 = true;
                else
                    flag3 = false;
                sendTextForSubscriberWithSelfPermissions(i, s26, s13, s8, s19, pendingintent7, pendingintent1, flag3);
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.android.internal.telephony.ISms");
                i = parcel.readInt();
                String s14 = parcel.readString();
                String s27 = parcel.readString();
                String s20 = parcel.readString();
                String s9 = parcel.readString();
                boolean flag4;
                PendingIntent pendingintent2;
                PendingIntent pendingintent8;
                boolean flag14;
                if(parcel.readInt() != 0)
                    pendingintent8 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                else
                    pendingintent8 = null;
                if(parcel.readInt() != 0)
                    pendingintent2 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                else
                    pendingintent2 = null;
                if(parcel.readInt() != 0)
                    flag4 = true;
                else
                    flag4 = false;
                j = parcel.readInt();
                if(parcel.readInt() != 0)
                    flag14 = true;
                else
                    flag14 = false;
                sendTextForSubscriberWithOptions(i, s14, s27, s20, s9, pendingintent8, pendingintent2, flag4, j, flag14, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.android.internal.telephony.ISms");
                i = parcel.readInt();
                byte abyte0[] = parcel.createByteArray();
                String s22 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                injectSmsPduForSubscriber(i, abyte0, s22, parcel);
                parcel1.writeNoException();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("com.android.internal.telephony.ISms");
                i = parcel.readInt();
                String s28 = parcel.readString();
                String s2 = parcel.readString();
                String s15 = parcel.readString();
                java.util.ArrayList arraylist = parcel.createStringArrayList();
                java.util.ArrayList arraylist3 = parcel.createTypedArrayList(PendingIntent.CREATOR);
                java.util.ArrayList arraylist4 = parcel.createTypedArrayList(PendingIntent.CREATOR);
                boolean flag5;
                if(parcel.readInt() != 0)
                    flag5 = true;
                else
                    flag5 = false;
                sendMultipartTextForSubscriber(i, s28, s2, s15, arraylist, arraylist3, arraylist4, flag5);
                parcel1.writeNoException();
                return true;

            case 11: // '\013'
                parcel.enforceInterface("com.android.internal.telephony.ISms");
                i = parcel.readInt();
                String s21 = parcel.readString();
                String s3 = parcel.readString();
                String s23 = parcel.readString();
                java.util.ArrayList arraylist5 = parcel.createStringArrayList();
                java.util.ArrayList arraylist1 = parcel.createTypedArrayList(PendingIntent.CREATOR);
                java.util.ArrayList arraylist2 = parcel.createTypedArrayList(PendingIntent.CREATOR);
                boolean flag6;
                boolean flag15;
                if(parcel.readInt() != 0)
                    flag6 = true;
                else
                    flag6 = false;
                j = parcel.readInt();
                if(parcel.readInt() != 0)
                    flag15 = true;
                else
                    flag15 = false;
                sendMultipartTextForSubscriberWithOptions(i, s21, s3, s23, arraylist5, arraylist1, arraylist2, flag6, j, flag15, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 12: // '\f'
                parcel.enforceInterface("com.android.internal.telephony.ISms");
                boolean flag7 = enableCellBroadcastForSubscriber(parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                if(flag7)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 13: // '\r'
                parcel.enforceInterface("com.android.internal.telephony.ISms");
                boolean flag8 = disableCellBroadcastForSubscriber(parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                if(flag8)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 14: // '\016'
                parcel.enforceInterface("com.android.internal.telephony.ISms");
                boolean flag9 = enableCellBroadcastRangeForSubscriber(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                if(flag9)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 15: // '\017'
                parcel.enforceInterface("com.android.internal.telephony.ISms");
                boolean flag10 = disableCellBroadcastRangeForSubscriber(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                if(flag10)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 16: // '\020'
                parcel.enforceInterface("com.android.internal.telephony.ISms");
                i = getPremiumSmsPermission(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 17: // '\021'
                parcel.enforceInterface("com.android.internal.telephony.ISms");
                i = getPremiumSmsPermissionForSubscriber(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 18: // '\022'
                parcel.enforceInterface("com.android.internal.telephony.ISms");
                setPremiumSmsPermission(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 19: // '\023'
                parcel.enforceInterface("com.android.internal.telephony.ISms");
                setPremiumSmsPermissionForSubscriber(parcel.readInt(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 20: // '\024'
                parcel.enforceInterface("com.android.internal.telephony.ISms");
                boolean flag11 = isImsSmsSupportedForSubscriber(parcel.readInt());
                parcel1.writeNoException();
                if(flag11)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 21: // '\025'
                parcel.enforceInterface("com.android.internal.telephony.ISms");
                boolean flag12 = isSmsSimPickActivityNeeded(parcel.readInt());
                parcel1.writeNoException();
                if(flag12)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 22: // '\026'
                parcel.enforceInterface("com.android.internal.telephony.ISms");
                i = getPreferredSmsSubscription();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 23: // '\027'
                parcel.enforceInterface("com.android.internal.telephony.ISms");
                parcel = getImsSmsFormatForSubscriber(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 24: // '\030'
                parcel.enforceInterface("com.android.internal.telephony.ISms");
                boolean flag13 = isSMSPromptEnabled();
                parcel1.writeNoException();
                if(flag13)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 25: // '\031'
                parcel.enforceInterface("com.android.internal.telephony.ISms");
                i = parcel.readInt();
                String s16 = parcel.readString();
                PendingIntent pendingintent3;
                String s10;
                Uri uri;
                if(parcel.readInt() != 0)
                    uri = (Uri)Uri.CREATOR.createFromParcel(parcel);
                else
                    uri = null;
                s10 = parcel.readString();
                if(parcel.readInt() != 0)
                    pendingintent3 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                else
                    pendingintent3 = null;
                if(parcel.readInt() != 0)
                    parcel = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                sendStoredText(i, s16, uri, s10, pendingintent3, parcel);
                parcel1.writeNoException();
                return true;

            case 26: // '\032'
                parcel.enforceInterface("com.android.internal.telephony.ISms");
                i = parcel.readInt();
                String s4 = parcel.readString();
                Uri uri1;
                if(parcel.readInt() != 0)
                    uri1 = (Uri)Uri.CREATOR.createFromParcel(parcel);
                else
                    uri1 = null;
                sendStoredMultipartText(i, s4, uri1, parcel.readString(), parcel.createTypedArrayList(PendingIntent.CREATOR), parcel.createTypedArrayList(PendingIntent.CREATOR));
                parcel1.writeNoException();
                return true;

            case 27: // '\033'
                parcel.enforceInterface("com.android.internal.telephony.ISms");
                i = parcel.readInt();
                String s24 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = createAppSpecificSmsToken(i, s24, parcel);
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 28: // '\034'
                parcel.enforceInterface("com.android.internal.telephony.ISms");
                i = getSmsCapacityOnIccForSubscriber(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.internal.telephony.ISms";
        static final int TRANSACTION_copyMessageToIccEfForSubscriber = 3;
        static final int TRANSACTION_createAppSpecificSmsToken = 27;
        static final int TRANSACTION_disableCellBroadcastForSubscriber = 13;
        static final int TRANSACTION_disableCellBroadcastRangeForSubscriber = 15;
        static final int TRANSACTION_enableCellBroadcastForSubscriber = 12;
        static final int TRANSACTION_enableCellBroadcastRangeForSubscriber = 14;
        static final int TRANSACTION_getAllMessagesFromIccEfForSubscriber = 1;
        static final int TRANSACTION_getImsSmsFormatForSubscriber = 23;
        static final int TRANSACTION_getPreferredSmsSubscription = 22;
        static final int TRANSACTION_getPremiumSmsPermission = 16;
        static final int TRANSACTION_getPremiumSmsPermissionForSubscriber = 17;
        static final int TRANSACTION_getSmsCapacityOnIccForSubscriber = 28;
        static final int TRANSACTION_injectSmsPduForSubscriber = 9;
        static final int TRANSACTION_isImsSmsSupportedForSubscriber = 20;
        static final int TRANSACTION_isSMSPromptEnabled = 24;
        static final int TRANSACTION_isSmsSimPickActivityNeeded = 21;
        static final int TRANSACTION_sendDataForSubscriber = 4;
        static final int TRANSACTION_sendDataForSubscriberWithSelfPermissions = 5;
        static final int TRANSACTION_sendMultipartTextForSubscriber = 10;
        static final int TRANSACTION_sendMultipartTextForSubscriberWithOptions = 11;
        static final int TRANSACTION_sendStoredMultipartText = 26;
        static final int TRANSACTION_sendStoredText = 25;
        static final int TRANSACTION_sendTextForSubscriber = 6;
        static final int TRANSACTION_sendTextForSubscriberWithOptions = 8;
        static final int TRANSACTION_sendTextForSubscriberWithSelfPermissions = 7;
        static final int TRANSACTION_setPremiumSmsPermission = 18;
        static final int TRANSACTION_setPremiumSmsPermissionForSubscriber = 19;
        static final int TRANSACTION_updateMessageOnIccEfForSubscriber = 2;

        public Stub()
        {
            attachInterface(this, "com.android.internal.telephony.ISms");
        }
    }

    private static class Stub.Proxy
        implements ISms
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public boolean copyMessageToIccEfForSubscriber(int i, String s, int j, byte abyte0[], byte abyte1[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ISms");
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeInt(j);
            parcel.writeByteArray(abyte0);
            parcel.writeByteArray(abyte1);
            mRemote.transact(3, parcel, parcel1, 0);
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

        public String createAppSpecificSmsToken(int i, String s, PendingIntent pendingintent)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ISms");
            parcel.writeInt(i);
            parcel.writeString(s);
            if(pendingintent == null)
                break MISSING_BLOCK_LABEL_86;
            parcel.writeInt(1);
            pendingintent.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(27, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean disableCellBroadcastForSubscriber(int i, int j, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ISms");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean disableCellBroadcastRangeForSubscriber(int i, int j, int k, int l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ISms");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeInt(l);
            mRemote.transact(15, parcel, parcel1, 0);
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

        public boolean enableCellBroadcastForSubscriber(int i, int j, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ISms");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
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

        public boolean enableCellBroadcastRangeForSubscriber(int i, int j, int k, int l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ISms");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeInt(l);
            mRemote.transact(14, parcel, parcel1, 0);
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

        public List getAllMessagesFromIccEfForSubscriber(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ISms");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.createTypedArrayList(SmsRawData.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public String getImsSmsFormatForSubscriber(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.android.internal.telephony.ISms");
            parcel.writeInt(i);
            mRemote.transact(23, parcel, parcel1, 0);
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

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.telephony.ISms";
        }

        public int getPreferredSmsSubscription()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telephony.ISms");
            mRemote.transact(22, parcel, parcel1, 0);
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

        public int getPremiumSmsPermission(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telephony.ISms");
            parcel.writeString(s);
            mRemote.transact(16, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int getPremiumSmsPermissionForSubscriber(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ISms");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(17, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int getSmsCapacityOnIccForSubscriber(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ISms");
            parcel.writeInt(i);
            mRemote.transact(28, parcel, parcel1, 0);
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

        public void injectSmsPduForSubscriber(int i, byte abyte0[], String s, PendingIntent pendingintent)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ISms");
            parcel.writeInt(i);
            parcel.writeByteArray(abyte0);
            parcel.writeString(s);
            if(pendingintent == null)
                break MISSING_BLOCK_LABEL_87;
            parcel.writeInt(1);
            pendingintent.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            abyte0;
            parcel1.recycle();
            parcel.recycle();
            throw abyte0;
        }

        public boolean isImsSmsSupportedForSubscriber(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ISms");
            parcel.writeInt(i);
            mRemote.transact(20, parcel, parcel1, 0);
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

        public boolean isSMSPromptEnabled()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telephony.ISms");
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isSmsSimPickActivityNeeded(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ISms");
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void sendDataForSubscriber(int i, String s, String s1, String s2, int j, byte abyte0[], PendingIntent pendingintent, 
                PendingIntent pendingintent1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ISms");
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeInt(j);
            parcel.writeByteArray(abyte0);
            if(pendingintent == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            pendingintent.writeToParcel(parcel, 0);
_L3:
            if(pendingintent1 == null)
                break MISSING_BLOCK_LABEL_148;
            parcel.writeInt(1);
            pendingintent1.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
            parcel.writeInt(0);
              goto _L4
        }

        public void sendDataForSubscriberWithSelfPermissions(int i, String s, String s1, String s2, int j, byte abyte0[], PendingIntent pendingintent, 
                PendingIntent pendingintent1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ISms");
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeInt(j);
            parcel.writeByteArray(abyte0);
            if(pendingintent == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            pendingintent.writeToParcel(parcel, 0);
_L3:
            if(pendingintent1 == null)
                break MISSING_BLOCK_LABEL_148;
            parcel.writeInt(1);
            pendingintent1.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
            parcel.writeInt(0);
              goto _L4
        }

        public void sendMultipartTextForSubscriber(int i, String s, String s1, String s2, List list, List list1, List list2, 
                boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ISms");
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeStringList(list);
            parcel.writeTypedList(list1);
            parcel.writeTypedList(list2);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void sendMultipartTextForSubscriberWithOptions(int i, String s, String s1, String s2, List list, List list1, List list2, 
                boolean flag, int j, boolean flag1, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ISms");
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeStringList(list);
            parcel.writeTypedList(list1);
            parcel.writeTypedList(list2);
            if(flag)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeInt(j);
            if(flag1)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeInt(k);
            mRemote.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void sendStoredMultipartText(int i, String s, Uri uri, String s1, List list, List list1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ISms");
            parcel.writeInt(i);
            parcel.writeString(s);
            if(uri == null)
                break MISSING_BLOCK_LABEL_100;
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s1);
            parcel.writeTypedList(list);
            parcel.writeTypedList(list1);
            mRemote.transact(26, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void sendStoredText(int i, String s, Uri uri, String s1, PendingIntent pendingintent, PendingIntent pendingintent1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ISms");
            parcel.writeInt(i);
            parcel.writeString(s);
            if(uri == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L5:
            parcel.writeString(s1);
            if(pendingintent == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            pendingintent.writeToParcel(parcel, 0);
_L6:
            if(pendingintent1 == null)
                break MISSING_BLOCK_LABEL_155;
            parcel.writeInt(1);
            pendingintent1.writeToParcel(parcel, 0);
_L7:
            mRemote.transact(25, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L5
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
_L4:
            parcel.writeInt(0);
              goto _L6
            parcel.writeInt(0);
              goto _L7
        }

        public void sendTextForSubscriber(int i, String s, String s1, String s2, String s3, PendingIntent pendingintent, PendingIntent pendingintent1, 
                boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ISms");
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeString(s3);
            if(pendingintent == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            pendingintent.writeToParcel(parcel, 0);
_L3:
            if(pendingintent1 == null)
                break MISSING_BLOCK_LABEL_159;
            parcel.writeInt(1);
            pendingintent1.writeToParcel(parcel, 0);
_L4:
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
            parcel.writeInt(0);
              goto _L4
        }

        public void sendTextForSubscriberWithOptions(int i, String s, String s1, String s2, String s3, PendingIntent pendingintent, PendingIntent pendingintent1, 
                boolean flag, int j, boolean flag1, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ISms");
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeString(s3);
            if(pendingintent == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            pendingintent.writeToParcel(parcel, 0);
_L3:
            if(pendingintent1 == null)
                break MISSING_BLOCK_LABEL_182;
            parcel.writeInt(1);
            pendingintent1.writeToParcel(parcel, 0);
_L4:
            if(flag)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeInt(j);
            if(flag1)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeInt(k);
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
            parcel.writeInt(0);
              goto _L4
        }

        public void sendTextForSubscriberWithSelfPermissions(int i, String s, String s1, String s2, String s3, PendingIntent pendingintent, PendingIntent pendingintent1, 
                boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ISms");
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeString(s3);
            if(pendingintent == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            pendingintent.writeToParcel(parcel, 0);
_L3:
            if(pendingintent1 == null)
                break MISSING_BLOCK_LABEL_159;
            parcel.writeInt(1);
            pendingintent1.writeToParcel(parcel, 0);
_L4:
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
            parcel.writeInt(0);
              goto _L4
        }

        public void setPremiumSmsPermission(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ISms");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(18, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setPremiumSmsPermissionForSubscriber(int i, String s, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ISms");
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeInt(j);
            mRemote.transact(19, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean updateMessageOnIccEfForSubscriber(int i, String s, int j, int k, byte abyte0[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ISms");
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeByteArray(abyte0);
            mRemote.transact(2, parcel, parcel1, 0);
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

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract boolean copyMessageToIccEfForSubscriber(int i, String s, int j, byte abyte0[], byte abyte1[])
        throws RemoteException;

    public abstract String createAppSpecificSmsToken(int i, String s, PendingIntent pendingintent)
        throws RemoteException;

    public abstract boolean disableCellBroadcastForSubscriber(int i, int j, int k)
        throws RemoteException;

    public abstract boolean disableCellBroadcastRangeForSubscriber(int i, int j, int k, int l)
        throws RemoteException;

    public abstract boolean enableCellBroadcastForSubscriber(int i, int j, int k)
        throws RemoteException;

    public abstract boolean enableCellBroadcastRangeForSubscriber(int i, int j, int k, int l)
        throws RemoteException;

    public abstract List getAllMessagesFromIccEfForSubscriber(int i, String s)
        throws RemoteException;

    public abstract String getImsSmsFormatForSubscriber(int i)
        throws RemoteException;

    public abstract int getPreferredSmsSubscription()
        throws RemoteException;

    public abstract int getPremiumSmsPermission(String s)
        throws RemoteException;

    public abstract int getPremiumSmsPermissionForSubscriber(int i, String s)
        throws RemoteException;

    public abstract int getSmsCapacityOnIccForSubscriber(int i)
        throws RemoteException;

    public abstract void injectSmsPduForSubscriber(int i, byte abyte0[], String s, PendingIntent pendingintent)
        throws RemoteException;

    public abstract boolean isImsSmsSupportedForSubscriber(int i)
        throws RemoteException;

    public abstract boolean isSMSPromptEnabled()
        throws RemoteException;

    public abstract boolean isSmsSimPickActivityNeeded(int i)
        throws RemoteException;

    public abstract void sendDataForSubscriber(int i, String s, String s1, String s2, int j, byte abyte0[], PendingIntent pendingintent, 
            PendingIntent pendingintent1)
        throws RemoteException;

    public abstract void sendDataForSubscriberWithSelfPermissions(int i, String s, String s1, String s2, int j, byte abyte0[], PendingIntent pendingintent, 
            PendingIntent pendingintent1)
        throws RemoteException;

    public abstract void sendMultipartTextForSubscriber(int i, String s, String s1, String s2, List list, List list1, List list2, 
            boolean flag)
        throws RemoteException;

    public abstract void sendMultipartTextForSubscriberWithOptions(int i, String s, String s1, String s2, List list, List list1, List list2, 
            boolean flag, int j, boolean flag1, int k)
        throws RemoteException;

    public abstract void sendStoredMultipartText(int i, String s, Uri uri, String s1, List list, List list1)
        throws RemoteException;

    public abstract void sendStoredText(int i, String s, Uri uri, String s1, PendingIntent pendingintent, PendingIntent pendingintent1)
        throws RemoteException;

    public abstract void sendTextForSubscriber(int i, String s, String s1, String s2, String s3, PendingIntent pendingintent, PendingIntent pendingintent1, 
            boolean flag)
        throws RemoteException;

    public abstract void sendTextForSubscriberWithOptions(int i, String s, String s1, String s2, String s3, PendingIntent pendingintent, PendingIntent pendingintent1, 
            boolean flag, int j, boolean flag1, int k)
        throws RemoteException;

    public abstract void sendTextForSubscriberWithSelfPermissions(int i, String s, String s1, String s2, String s3, PendingIntent pendingintent, PendingIntent pendingintent1, 
            boolean flag)
        throws RemoteException;

    public abstract void setPremiumSmsPermission(String s, int i)
        throws RemoteException;

    public abstract void setPremiumSmsPermissionForSubscriber(int i, String s, int j)
        throws RemoteException;

    public abstract boolean updateMessageOnIccEfForSubscriber(int i, String s, int j, int k, byte abyte0[])
        throws RemoteException;
}
