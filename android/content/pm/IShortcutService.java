// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.content.Intent;
import android.content.IntentSender;
import android.os.*;
import android.text.TextUtils;
import java.util.List;

// Referenced classes of package android.content.pm:
//            ParceledListSlice, ShortcutInfo

public interface IShortcutService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IShortcutService
    {

        public static IShortcutService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.content.pm.IShortcutService");
            if(iinterface != null && (iinterface instanceof IShortcutService))
                return (IShortcutService)iinterface;
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
            boolean flag4;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.content.pm.IShortcutService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.content.pm.IShortcutService");
                String s = parcel.readString();
                ParceledListSlice parceledlistslice;
                boolean flag;
                if(parcel.readInt() != 0)
                    parceledlistslice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel);
                else
                    parceledlistslice = null;
                flag = setDynamicShortcuts(s, parceledlistslice, parcel.readInt());
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.content.pm.IShortcutService");
                parcel = getDynamicShortcuts(parcel.readString(), parcel.readInt());
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
                parcel.enforceInterface("android.content.pm.IShortcutService");
                parcel = getManifestShortcuts(parcel.readString(), parcel.readInt());
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
                parcel.enforceInterface("android.content.pm.IShortcutService");
                String s1 = parcel.readString();
                ParceledListSlice parceledlistslice1;
                boolean flag1;
                if(parcel.readInt() != 0)
                    parceledlistslice1 = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel);
                else
                    parceledlistslice1 = null;
                flag1 = addDynamicShortcuts(s1, parceledlistslice1, parcel.readInt());
                parcel1.writeNoException();
                if(flag1)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.content.pm.IShortcutService");
                removeDynamicShortcuts(parcel.readString(), parcel.readArrayList(getClass().getClassLoader()), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.content.pm.IShortcutService");
                removeAllDynamicShortcuts(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.content.pm.IShortcutService");
                parcel = getPinnedShortcuts(parcel.readString(), parcel.readInt());
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

            case 8: // '\b'
                parcel.enforceInterface("android.content.pm.IShortcutService");
                String s2 = parcel.readString();
                ParceledListSlice parceledlistslice2;
                boolean flag2;
                if(parcel.readInt() != 0)
                    parceledlistslice2 = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel);
                else
                    parceledlistslice2 = null;
                flag2 = updateShortcuts(s2, parceledlistslice2, parcel.readInt());
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.content.pm.IShortcutService");
                String s5 = parcel.readString();
                IntentSender intentsender;
                ShortcutInfo shortcutinfo;
                boolean flag3;
                if(parcel.readInt() != 0)
                    shortcutinfo = (ShortcutInfo)ShortcutInfo.CREATOR.createFromParcel(parcel);
                else
                    shortcutinfo = null;
                if(parcel.readInt() != 0)
                    intentsender = (IntentSender)IntentSender.CREATOR.createFromParcel(parcel);
                else
                    intentsender = null;
                flag3 = requestPinShortcut(s5, shortcutinfo, intentsender, parcel.readInt());
                parcel1.writeNoException();
                if(flag3)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.content.pm.IShortcutService");
                String s3 = parcel.readString();
                ShortcutInfo shortcutinfo1;
                if(parcel.readInt() != 0)
                    shortcutinfo1 = (ShortcutInfo)ShortcutInfo.CREATOR.createFromParcel(parcel);
                else
                    shortcutinfo1 = null;
                parcel = createShortcutResultIntent(s3, shortcutinfo1, parcel.readInt());
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

            case 11: // '\013'
                parcel.enforceInterface("android.content.pm.IShortcutService");
                String s4 = parcel.readString();
                java.util.ArrayList arraylist = parcel.readArrayList(getClass().getClassLoader());
                CharSequence charsequence;
                if(parcel.readInt() != 0)
                    charsequence = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
                else
                    charsequence = null;
                disableShortcuts(s4, arraylist, charsequence, parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.content.pm.IShortcutService");
                enableShortcuts(parcel.readString(), parcel.readArrayList(getClass().getClassLoader()), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.content.pm.IShortcutService");
                i = getMaxShortcutCountPerActivity(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.content.pm.IShortcutService");
                i = getRemainingCallCount(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 15: // '\017'
                parcel.enforceInterface("android.content.pm.IShortcutService");
                long l = getRateLimitResetTime(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeLong(l);
                return true;

            case 16: // '\020'
                parcel.enforceInterface("android.content.pm.IShortcutService");
                i = getIconMaxDimensions(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 17: // '\021'
                parcel.enforceInterface("android.content.pm.IShortcutService");
                reportShortcutUsed(parcel.readString(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 18: // '\022'
                parcel.enforceInterface("android.content.pm.IShortcutService");
                resetThrottling();
                parcel1.writeNoException();
                return true;

            case 19: // '\023'
                parcel.enforceInterface("android.content.pm.IShortcutService");
                onApplicationActive(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 20: // '\024'
                parcel.enforceInterface("android.content.pm.IShortcutService");
                parcel = getBackupPayload(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeByteArray(parcel);
                return true;

            case 21: // '\025'
                parcel.enforceInterface("android.content.pm.IShortcutService");
                applyRestore(parcel.createByteArray(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 22: // '\026'
                parcel.enforceInterface("android.content.pm.IShortcutService");
                flag4 = isRequestPinItemSupported(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                break;
            }
            if(flag4)
                i = 1;
            else
                i = 0;
            parcel1.writeInt(i);
            return true;
        }

        private static final String DESCRIPTOR = "android.content.pm.IShortcutService";
        static final int TRANSACTION_addDynamicShortcuts = 4;
        static final int TRANSACTION_applyRestore = 21;
        static final int TRANSACTION_createShortcutResultIntent = 10;
        static final int TRANSACTION_disableShortcuts = 11;
        static final int TRANSACTION_enableShortcuts = 12;
        static final int TRANSACTION_getBackupPayload = 20;
        static final int TRANSACTION_getDynamicShortcuts = 2;
        static final int TRANSACTION_getIconMaxDimensions = 16;
        static final int TRANSACTION_getManifestShortcuts = 3;
        static final int TRANSACTION_getMaxShortcutCountPerActivity = 13;
        static final int TRANSACTION_getPinnedShortcuts = 7;
        static final int TRANSACTION_getRateLimitResetTime = 15;
        static final int TRANSACTION_getRemainingCallCount = 14;
        static final int TRANSACTION_isRequestPinItemSupported = 22;
        static final int TRANSACTION_onApplicationActive = 19;
        static final int TRANSACTION_removeAllDynamicShortcuts = 6;
        static final int TRANSACTION_removeDynamicShortcuts = 5;
        static final int TRANSACTION_reportShortcutUsed = 17;
        static final int TRANSACTION_requestPinShortcut = 9;
        static final int TRANSACTION_resetThrottling = 18;
        static final int TRANSACTION_setDynamicShortcuts = 1;
        static final int TRANSACTION_updateShortcuts = 8;

        public Stub()
        {
            attachInterface(this, "android.content.pm.IShortcutService");
        }
    }

    private static class Stub.Proxy
        implements IShortcutService
    {

        public boolean addDynamicShortcuts(String s, ParceledListSlice parceledlistslice, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IShortcutService");
            parcel.writeString(s);
            if(parceledlistslice == null)
                break MISSING_BLOCK_LABEL_93;
            parcel.writeInt(1);
            parceledlistslice.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
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
            parcel.writeInt(0);
              goto _L1
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void applyRestore(byte abyte0[], int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IShortcutService");
            parcel.writeByteArray(abyte0);
            parcel.writeInt(i);
            mRemote.transact(21, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            abyte0;
            parcel1.recycle();
            parcel.recycle();
            throw abyte0;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public Intent createShortcutResultIntent(String s, ShortcutInfo shortcutinfo, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IShortcutService");
            parcel.writeString(s);
            if(shortcutinfo == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            shortcutinfo.writeToParcel(parcel, 0);
_L3:
            parcel.writeInt(i);
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_124;
            s = (Intent)Intent.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            parcel.writeInt(0);
              goto _L3
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
            s = null;
              goto _L4
        }

        public void disableShortcuts(String s, List list, CharSequence charsequence, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IShortcutService");
            parcel.writeString(s);
            parcel.writeList(list);
            if(charsequence == null)
                break MISSING_BLOCK_LABEL_93;
            parcel.writeInt(1);
            TextUtils.writeToParcel(charsequence, parcel, 0);
_L1:
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(11, parcel, parcel1, 0);
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

        public void enableShortcuts(String s, List list, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IShortcutService");
            parcel.writeString(s);
            parcel.writeList(list);
            parcel.writeInt(i);
            mRemote.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public byte[] getBackupPayload(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            byte abyte0[];
            parcel.writeInterfaceToken("android.content.pm.IShortcutService");
            parcel.writeInt(i);
            mRemote.transact(20, parcel, parcel1, 0);
            parcel1.readException();
            abyte0 = parcel1.createByteArray();
            parcel1.recycle();
            parcel.recycle();
            return abyte0;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public ParceledListSlice getDynamicShortcuts(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IShortcutService");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel1);
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

        public int getIconMaxDimensions(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IShortcutService");
            parcel.writeString(s);
            parcel.writeInt(i);
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

        public String getInterfaceDescriptor()
        {
            return "android.content.pm.IShortcutService";
        }

        public ParceledListSlice getManifestShortcuts(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IShortcutService");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel1);
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

        public int getMaxShortcutCountPerActivity(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IShortcutService");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(13, parcel, parcel1, 0);
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

        public ParceledListSlice getPinnedShortcuts(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IShortcutService");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel1);
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

        public long getRateLimitResetTime(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            long l;
            parcel.writeInterfaceToken("android.content.pm.IShortcutService");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(15, parcel, parcel1, 0);
            parcel1.readException();
            l = parcel1.readLong();
            parcel1.recycle();
            parcel.recycle();
            return l;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int getRemainingCallCount(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IShortcutService");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(14, parcel, parcel1, 0);
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

        public boolean isRequestPinItemSupported(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IShortcutService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(22, parcel, parcel1, 0);
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

        public void onApplicationActive(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IShortcutService");
            parcel.writeString(s);
            parcel.writeInt(i);
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

        public void removeAllDynamicShortcuts(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IShortcutService");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void removeDynamicShortcuts(String s, List list, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IShortcutService");
            parcel.writeString(s);
            parcel.writeList(list);
            parcel.writeInt(i);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void reportShortcutUsed(String s, String s1, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IShortcutService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(i);
            mRemote.transact(17, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean requestPinShortcut(String s, ShortcutInfo shortcutinfo, IntentSender intentsender, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IShortcutService");
            parcel.writeString(s);
            if(shortcutinfo == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            shortcutinfo.writeToParcel(parcel, 0);
_L3:
            if(intentsender == null)
                break MISSING_BLOCK_LABEL_136;
            parcel.writeInt(1);
            intentsender.writeToParcel(parcel, 0);
_L4:
            parcel.writeInt(i);
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

        public void resetThrottling()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IShortcutService");
            mRemote.transact(18, parcel, parcel1, 0);
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

        public boolean setDynamicShortcuts(String s, ParceledListSlice parceledlistslice, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IShortcutService");
            parcel.writeString(s);
            if(parceledlistslice == null)
                break MISSING_BLOCK_LABEL_93;
            parcel.writeInt(1);
            parceledlistslice.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(1, parcel, parcel1, 0);
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
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean updateShortcuts(String s, ParceledListSlice parceledlistslice, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IShortcutService");
            parcel.writeString(s);
            if(parceledlistslice == null)
                break MISSING_BLOCK_LABEL_94;
            parcel.writeInt(1);
            parceledlistslice.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(8, parcel, parcel1, 0);
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


    public abstract boolean addDynamicShortcuts(String s, ParceledListSlice parceledlistslice, int i)
        throws RemoteException;

    public abstract void applyRestore(byte abyte0[], int i)
        throws RemoteException;

    public abstract Intent createShortcutResultIntent(String s, ShortcutInfo shortcutinfo, int i)
        throws RemoteException;

    public abstract void disableShortcuts(String s, List list, CharSequence charsequence, int i, int j)
        throws RemoteException;

    public abstract void enableShortcuts(String s, List list, int i)
        throws RemoteException;

    public abstract byte[] getBackupPayload(int i)
        throws RemoteException;

    public abstract ParceledListSlice getDynamicShortcuts(String s, int i)
        throws RemoteException;

    public abstract int getIconMaxDimensions(String s, int i)
        throws RemoteException;

    public abstract ParceledListSlice getManifestShortcuts(String s, int i)
        throws RemoteException;

    public abstract int getMaxShortcutCountPerActivity(String s, int i)
        throws RemoteException;

    public abstract ParceledListSlice getPinnedShortcuts(String s, int i)
        throws RemoteException;

    public abstract long getRateLimitResetTime(String s, int i)
        throws RemoteException;

    public abstract int getRemainingCallCount(String s, int i)
        throws RemoteException;

    public abstract boolean isRequestPinItemSupported(int i, int j)
        throws RemoteException;

    public abstract void onApplicationActive(String s, int i)
        throws RemoteException;

    public abstract void removeAllDynamicShortcuts(String s, int i)
        throws RemoteException;

    public abstract void removeDynamicShortcuts(String s, List list, int i)
        throws RemoteException;

    public abstract void reportShortcutUsed(String s, String s1, int i)
        throws RemoteException;

    public abstract boolean requestPinShortcut(String s, ShortcutInfo shortcutinfo, IntentSender intentsender, int i)
        throws RemoteException;

    public abstract void resetThrottling()
        throws RemoteException;

    public abstract boolean setDynamicShortcuts(String s, ParceledListSlice parceledlistslice, int i)
        throws RemoteException;

    public abstract boolean updateShortcuts(String s, ParceledListSlice parceledlistslice, int i)
        throws RemoteException;
}
