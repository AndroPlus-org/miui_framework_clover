// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.ComponentName;
import android.graphics.Rect;
import android.os.*;

// Referenced classes of package android.app:
//            IWallpaperManagerCallback, WallpaperColors, WallpaperInfo

public interface IWallpaperManager
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IWallpaperManager
    {

        public static IWallpaperManager asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.app.IWallpaperManager");
            if(iinterface != null && (iinterface instanceof IWallpaperManager))
                return (IWallpaperManager)iinterface;
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
                parcel1.writeString("android.app.IWallpaperManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.app.IWallpaperManager");
                String s = parcel.readString();
                String s1 = parcel.readString();
                Rect rect;
                boolean flag;
                Bundle bundle1;
                if(parcel.readInt() != 0)
                    rect = (Rect)Rect.CREATOR.createFromParcel(parcel);
                else
                    rect = null;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                bundle1 = new Bundle();
                parcel = setWallpaper(s, s1, rect, flag, bundle1, parcel.readInt(), IWallpaperManagerCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                if(bundle1 != null)
                {
                    parcel1.writeInt(1);
                    bundle1.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.app.IWallpaperManager");
                ComponentName componentname;
                if(parcel.readInt() != 0)
                    componentname = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname = null;
                setWallpaperComponentChecked(componentname, parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.app.IWallpaperManager");
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setWallpaperComponent(parcel);
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.app.IWallpaperManager");
                String s2 = parcel.readString();
                IWallpaperManagerCallback iwallpapermanagercallback = IWallpaperManagerCallback.Stub.asInterface(parcel.readStrongBinder());
                i = parcel.readInt();
                Bundle bundle = new Bundle();
                parcel = getWallpaper(s2, iwallpapermanagercallback, i, bundle, parcel.readInt());
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                if(bundle != null)
                {
                    parcel1.writeInt(1);
                    bundle.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.app.IWallpaperManager");
                i = getWallpaperIdForUser(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.app.IWallpaperManager");
                parcel = getWallpaperInfo(parcel.readInt());
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
                parcel.enforceInterface("android.app.IWallpaperManager");
                clearWallpaper(parcel.readString(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.app.IWallpaperManager");
                boolean flag1 = hasNamedWallpaper(parcel.readString());
                parcel1.writeNoException();
                if(flag1)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.app.IWallpaperManager");
                setDimensionHints(parcel.readInt(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.app.IWallpaperManager");
                i = getWidthHint();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.app.IWallpaperManager");
                i = getHeightHint();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.app.IWallpaperManager");
                Rect rect1;
                if(parcel.readInt() != 0)
                    rect1 = (Rect)Rect.CREATOR.createFromParcel(parcel);
                else
                    rect1 = null;
                setDisplayPadding(rect1, parcel.readString());
                parcel1.writeNoException();
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.app.IWallpaperManager");
                parcel = getName();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.app.IWallpaperManager");
                settingsRestored();
                parcel1.writeNoException();
                return true;

            case 15: // '\017'
                parcel.enforceInterface("android.app.IWallpaperManager");
                boolean flag2 = isWallpaperSupported(parcel.readString());
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 16: // '\020'
                parcel.enforceInterface("android.app.IWallpaperManager");
                boolean flag3 = isSetWallpaperAllowed(parcel.readString());
                parcel1.writeNoException();
                if(flag3)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 17: // '\021'
                parcel.enforceInterface("android.app.IWallpaperManager");
                boolean flag4 = isWallpaperBackupEligible(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                if(flag4)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 18: // '\022'
                parcel.enforceInterface("android.app.IWallpaperManager");
                boolean flag5 = setLockWallpaperCallback(IWallpaperManagerCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                if(flag5)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 19: // '\023'
                parcel.enforceInterface("android.app.IWallpaperManager");
                parcel = getWallpaperColors(parcel.readInt(), parcel.readInt());
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

            case 20: // '\024'
                parcel.enforceInterface("android.app.IWallpaperManager");
                registerWallpaperColorsCallback(IWallpaperManagerCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 21: // '\025'
                parcel.enforceInterface("android.app.IWallpaperManager");
                unregisterWallpaperColorsCallback(IWallpaperManagerCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.app.IWallpaperManager";
        static final int TRANSACTION_clearWallpaper = 7;
        static final int TRANSACTION_getHeightHint = 11;
        static final int TRANSACTION_getName = 13;
        static final int TRANSACTION_getWallpaper = 4;
        static final int TRANSACTION_getWallpaperColors = 19;
        static final int TRANSACTION_getWallpaperIdForUser = 5;
        static final int TRANSACTION_getWallpaperInfo = 6;
        static final int TRANSACTION_getWidthHint = 10;
        static final int TRANSACTION_hasNamedWallpaper = 8;
        static final int TRANSACTION_isSetWallpaperAllowed = 16;
        static final int TRANSACTION_isWallpaperBackupEligible = 17;
        static final int TRANSACTION_isWallpaperSupported = 15;
        static final int TRANSACTION_registerWallpaperColorsCallback = 20;
        static final int TRANSACTION_setDimensionHints = 9;
        static final int TRANSACTION_setDisplayPadding = 12;
        static final int TRANSACTION_setLockWallpaperCallback = 18;
        static final int TRANSACTION_setWallpaper = 1;
        static final int TRANSACTION_setWallpaperComponent = 3;
        static final int TRANSACTION_setWallpaperComponentChecked = 2;
        static final int TRANSACTION_settingsRestored = 14;
        static final int TRANSACTION_unregisterWallpaperColorsCallback = 21;

        public Stub()
        {
            attachInterface(this, "android.app.IWallpaperManager");
        }
    }

    private static class Stub.Proxy
        implements IWallpaperManager
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void clearWallpaper(String s, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IWallpaperManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int getHeightHint()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.IWallpaperManager");
            mRemote.transact(11, parcel, parcel1, 0);
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
            return "android.app.IWallpaperManager";
        }

        public String getName()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("android.app.IWallpaperManager");
            mRemote.transact(13, parcel, parcel1, 0);
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

        public ParcelFileDescriptor getWallpaper(String s, IWallpaperManagerCallback iwallpapermanagercallback, int i, Bundle bundle, int j)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IWallpaperManager");
            parcel.writeString(s);
            s = obj;
            if(iwallpapermanagercallback == null)
                break MISSING_BLOCK_LABEL_40;
            s = iwallpapermanagercallback.asBinder();
            parcel.writeStrongBinder(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_129;
            s = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel1);
_L1:
            if(parcel1.readInt() != 0)
                bundle.readFromParcel(parcel1);
            parcel1.recycle();
            parcel.recycle();
            return s;
            s = null;
              goto _L1
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public WallpaperColors getWallpaperColors(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IWallpaperManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(19, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            WallpaperColors wallpapercolors = (WallpaperColors)WallpaperColors.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return wallpapercolors;
_L2:
            wallpapercolors = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getWallpaperIdForUser(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IWallpaperManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(5, parcel, parcel1, 0);
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

        public WallpaperInfo getWallpaperInfo(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IWallpaperManager");
            parcel.writeInt(i);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            WallpaperInfo wallpaperinfo = (WallpaperInfo)WallpaperInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return wallpaperinfo;
_L2:
            wallpaperinfo = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getWidthHint()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.IWallpaperManager");
            mRemote.transact(10, parcel, parcel1, 0);
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

        public boolean hasNamedWallpaper(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.IWallpaperManager");
            parcel.writeString(s);
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
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean isSetWallpaperAllowed(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.IWallpaperManager");
            parcel.writeString(s);
            mRemote.transact(16, parcel, parcel1, 0);
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

        public boolean isWallpaperBackupEligible(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IWallpaperManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(17, parcel, parcel1, 0);
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

        public boolean isWallpaperSupported(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.IWallpaperManager");
            parcel.writeString(s);
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
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void registerWallpaperColorsCallback(IWallpaperManagerCallback iwallpapermanagercallback, int i)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IWallpaperManager");
            if(iwallpapermanagercallback == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = iwallpapermanagercallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(20, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iwallpapermanagercallback;
            parcel1.recycle();
            parcel.recycle();
            throw iwallpapermanagercallback;
        }

        public void setDimensionHints(int i, int j, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IWallpaperManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeString(s);
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setDisplayPadding(Rect rect, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IWallpaperManager");
            if(rect == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            rect.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            mRemote.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            rect;
            parcel1.recycle();
            parcel.recycle();
            throw rect;
        }

        public boolean setLockWallpaperCallback(IWallpaperManagerCallback iwallpapermanagercallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IWallpaperManager");
            if(iwallpapermanagercallback == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iwallpapermanagercallback.asBinder();
            int i;
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(18, parcel, parcel1, 0);
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
            iwallpapermanagercallback;
            parcel1.recycle();
            parcel.recycle();
            throw iwallpapermanagercallback;
        }

        public ParcelFileDescriptor setWallpaper(String s, String s1, Rect rect, boolean flag, Bundle bundle, int i, IWallpaperManagerCallback iwallpapermanagercallback, 
                int j)
            throws RemoteException
        {
            Object obj;
            int k;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            k = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IWallpaperManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            if(rect == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            rect.writeToParcel(parcel, 0);
_L3:
            if(!flag)
                k = 0;
            parcel.writeInt(k);
            parcel.writeInt(i);
            s = obj;
            if(iwallpapermanagercallback == null)
                break MISSING_BLOCK_LABEL_87;
            s = iwallpapermanagercallback.asBinder();
            parcel.writeStrongBinder(s);
            parcel.writeInt(j);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_198;
            s = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel1);
_L4:
            if(parcel1.readInt() != 0)
                bundle.readFromParcel(parcel1);
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

        public void setWallpaperComponent(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IWallpaperManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_56;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void setWallpaperComponentChecked(ComponentName componentname, String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IWallpaperManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_78;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void settingsRestored()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IWallpaperManager");
            mRemote.transact(14, parcel, parcel1, 0);
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

        public void unregisterWallpaperColorsCallback(IWallpaperManagerCallback iwallpapermanagercallback, int i)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IWallpaperManager");
            if(iwallpapermanagercallback == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = iwallpapermanagercallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(21, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iwallpapermanagercallback;
            parcel1.recycle();
            parcel.recycle();
            throw iwallpapermanagercallback;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void clearWallpaper(String s, int i, int j)
        throws RemoteException;

    public abstract int getHeightHint()
        throws RemoteException;

    public abstract String getName()
        throws RemoteException;

    public abstract ParcelFileDescriptor getWallpaper(String s, IWallpaperManagerCallback iwallpapermanagercallback, int i, Bundle bundle, int j)
        throws RemoteException;

    public abstract WallpaperColors getWallpaperColors(int i, int j)
        throws RemoteException;

    public abstract int getWallpaperIdForUser(int i, int j)
        throws RemoteException;

    public abstract WallpaperInfo getWallpaperInfo(int i)
        throws RemoteException;

    public abstract int getWidthHint()
        throws RemoteException;

    public abstract boolean hasNamedWallpaper(String s)
        throws RemoteException;

    public abstract boolean isSetWallpaperAllowed(String s)
        throws RemoteException;

    public abstract boolean isWallpaperBackupEligible(int i, int j)
        throws RemoteException;

    public abstract boolean isWallpaperSupported(String s)
        throws RemoteException;

    public abstract void registerWallpaperColorsCallback(IWallpaperManagerCallback iwallpapermanagercallback, int i)
        throws RemoteException;

    public abstract void setDimensionHints(int i, int j, String s)
        throws RemoteException;

    public abstract void setDisplayPadding(Rect rect, String s)
        throws RemoteException;

    public abstract boolean setLockWallpaperCallback(IWallpaperManagerCallback iwallpapermanagercallback)
        throws RemoteException;

    public abstract ParcelFileDescriptor setWallpaper(String s, String s1, Rect rect, boolean flag, Bundle bundle, int i, IWallpaperManagerCallback iwallpapermanagercallback, 
            int j)
        throws RemoteException;

    public abstract void setWallpaperComponent(ComponentName componentname)
        throws RemoteException;

    public abstract void setWallpaperComponentChecked(ComponentName componentname, String s, int i)
        throws RemoteException;

    public abstract void settingsRestored()
        throws RemoteException;

    public abstract void unregisterWallpaperColorsCallback(IWallpaperManagerCallback iwallpapermanagercallback, int i)
        throws RemoteException;
}
