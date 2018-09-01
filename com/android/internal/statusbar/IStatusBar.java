// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.statusbar;

import android.content.ComponentName;
import android.graphics.Rect;
import android.os.*;

// Referenced classes of package com.android.internal.statusbar:
//            StatusBarIcon

public interface IStatusBar
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IStatusBar
    {

        public static IStatusBar asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.statusbar.IStatusBar");
            if(iinterface != null && (iinterface instanceof IStatusBar))
                return (IStatusBar)iinterface;
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
                parcel1.writeString("com.android.internal.statusbar.IStatusBar");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBar");
                i = parcel.readInt();
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setStatus(i, parcel1, parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBar");
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (StatusBarIcon)StatusBarIcon.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setIcon(parcel1, parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBar");
                removeIcon(parcel.readString());
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBar");
                disable(parcel.readInt(), parcel.readInt());
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBar");
                animateExpandNotificationsPanel();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBar");
                animateExpandSettingsPanel(parcel.readString());
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBar");
                animateCollapsePanels();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBar");
                togglePanel();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBar");
                j = parcel.readInt();
                int k = parcel.readInt();
                i = parcel.readInt();
                int l = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel1 = (Rect)Rect.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    parcel = (Rect)Rect.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setSystemUiVisibility(j, k, i, l, parcel1, parcel);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBar");
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                topAppWindowChanged(flag);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBar");
                parcel1 = parcel.readStrongBinder();
                i = parcel.readInt();
                j = parcel.readInt();
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                setImeWindowStatus(parcel1, i, j, flag1);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBar");
                setWindowState(parcel.readInt(), parcel.readInt());
                return true;

            case 13: // '\r'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBar");
                boolean flag2;
                boolean flag6;
                if(parcel.readInt() != 0)
                    flag2 = true;
                else
                    flag2 = false;
                if(parcel.readInt() != 0)
                    flag6 = true;
                else
                    flag6 = false;
                showRecentApps(flag2, flag6);
                return true;

            case 14: // '\016'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBar");
                boolean flag3;
                boolean flag7;
                if(parcel.readInt() != 0)
                    flag3 = true;
                else
                    flag3 = false;
                if(parcel.readInt() != 0)
                    flag7 = true;
                else
                    flag7 = false;
                hideRecentApps(flag3, flag7);
                return true;

            case 15: // '\017'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBar");
                toggleRecentApps();
                return true;

            case 16: // '\020'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBar");
                toggleSplitScreen();
                return true;

            case 17: // '\021'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBar");
                preloadRecentApps();
                return true;

            case 18: // '\022'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBar");
                cancelPreloadRecentApps();
                return true;

            case 19: // '\023'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBar");
                showScreenPinningRequest(parcel.readInt());
                return true;

            case 20: // '\024'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBar");
                dismissKeyboardShortcutsMenu();
                return true;

            case 21: // '\025'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBar");
                toggleKeyboardShortcutsMenu(parcel.readInt());
                return true;

            case 22: // '\026'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBar");
                appTransitionPending();
                return true;

            case 23: // '\027'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBar");
                appTransitionCancelled();
                return true;

            case 24: // '\030'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBar");
                appTransitionStarting(parcel.readLong(), parcel.readLong());
                return true;

            case 25: // '\031'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBar");
                appTransitionFinished();
                return true;

            case 26: // '\032'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBar");
                showAssistDisclosure();
                return true;

            case 27: // '\033'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBar");
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                startAssist(parcel);
                return true;

            case 28: // '\034'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBar");
                onCameraLaunchGestureDetected(parcel.readInt());
                return true;

            case 29: // '\035'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBar");
                showPictureInPictureMenu();
                return true;

            case 30: // '\036'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBar");
                showGlobalActionsMenu();
                return true;

            case 31: // '\037'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBar");
                boolean flag4;
                if(parcel.readInt() != 0)
                    flag4 = true;
                else
                    flag4 = false;
                setTopAppHidesStatusBar(flag4);
                return true;

            case 32: // ' '
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBar");
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                addQsTile(parcel);
                return true;

            case 33: // '!'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBar");
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                remQsTile(parcel);
                return true;

            case 34: // '"'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBar");
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                clickQsTile(parcel);
                return true;

            case 35: // '#'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBar");
                handleSystemKey(parcel.readInt());
                return true;

            case 36: // '$'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBar");
                break;
            }
            boolean flag5;
            if(parcel.readInt() != 0)
                flag5 = true;
            else
                flag5 = false;
            showShutdownUi(flag5, parcel.readString());
            return true;
        }

        private static final String DESCRIPTOR = "com.android.internal.statusbar.IStatusBar";
        static final int TRANSACTION_addQsTile = 32;
        static final int TRANSACTION_animateCollapsePanels = 7;
        static final int TRANSACTION_animateExpandNotificationsPanel = 5;
        static final int TRANSACTION_animateExpandSettingsPanel = 6;
        static final int TRANSACTION_appTransitionCancelled = 23;
        static final int TRANSACTION_appTransitionFinished = 25;
        static final int TRANSACTION_appTransitionPending = 22;
        static final int TRANSACTION_appTransitionStarting = 24;
        static final int TRANSACTION_cancelPreloadRecentApps = 18;
        static final int TRANSACTION_clickQsTile = 34;
        static final int TRANSACTION_disable = 4;
        static final int TRANSACTION_dismissKeyboardShortcutsMenu = 20;
        static final int TRANSACTION_handleSystemKey = 35;
        static final int TRANSACTION_hideRecentApps = 14;
        static final int TRANSACTION_onCameraLaunchGestureDetected = 28;
        static final int TRANSACTION_preloadRecentApps = 17;
        static final int TRANSACTION_remQsTile = 33;
        static final int TRANSACTION_removeIcon = 3;
        static final int TRANSACTION_setIcon = 2;
        static final int TRANSACTION_setImeWindowStatus = 11;
        static final int TRANSACTION_setStatus = 1;
        static final int TRANSACTION_setSystemUiVisibility = 9;
        static final int TRANSACTION_setTopAppHidesStatusBar = 31;
        static final int TRANSACTION_setWindowState = 12;
        static final int TRANSACTION_showAssistDisclosure = 26;
        static final int TRANSACTION_showGlobalActionsMenu = 30;
        static final int TRANSACTION_showPictureInPictureMenu = 29;
        static final int TRANSACTION_showRecentApps = 13;
        static final int TRANSACTION_showScreenPinningRequest = 19;
        static final int TRANSACTION_showShutdownUi = 36;
        static final int TRANSACTION_startAssist = 27;
        static final int TRANSACTION_toggleKeyboardShortcutsMenu = 21;
        static final int TRANSACTION_togglePanel = 8;
        static final int TRANSACTION_toggleRecentApps = 15;
        static final int TRANSACTION_toggleSplitScreen = 16;
        static final int TRANSACTION_topAppWindowChanged = 10;

        public Stub()
        {
            attachInterface(this, "com.android.internal.statusbar.IStatusBar");
        }
    }

    private static class Stub.Proxy
        implements IStatusBar
    {

        public void addQsTile(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBar");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(32, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel.recycle();
            throw componentname;
        }

        public void animateCollapsePanels()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBar");
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void animateExpandNotificationsPanel()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBar");
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void animateExpandSettingsPanel(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBar");
            parcel.writeString(s);
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void appTransitionCancelled()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBar");
            mRemote.transact(23, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void appTransitionFinished()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBar");
            mRemote.transact(25, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void appTransitionPending()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBar");
            mRemote.transact(22, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void appTransitionStarting(long l, long l1)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBar");
            parcel.writeLong(l);
            parcel.writeLong(l1);
            mRemote.transact(24, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void cancelPreloadRecentApps()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBar");
            mRemote.transact(18, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void clickQsTile(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBar");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(34, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel.recycle();
            throw componentname;
        }

        public void disable(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBar");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void dismissKeyboardShortcutsMenu()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBar");
            mRemote.transact(20, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.statusbar.IStatusBar";
        }

        public void handleSystemKey(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBar");
            parcel.writeInt(i);
            mRemote.transact(35, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void hideRecentApps(boolean flag, boolean flag1)
            throws RemoteException
        {
            boolean flag2;
            Parcel parcel;
            flag2 = true;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBar");
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(flag1)
                i = ((flag2) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(14, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onCameraLaunchGestureDetected(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBar");
            parcel.writeInt(i);
            mRemote.transact(28, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void preloadRecentApps()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBar");
            mRemote.transact(17, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void remQsTile(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBar");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(33, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel.recycle();
            throw componentname;
        }

        public void removeIcon(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBar");
            parcel.writeString(s);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void setIcon(String s, StatusBarIcon statusbaricon)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBar");
            parcel.writeString(s);
            if(statusbaricon == null)
                break MISSING_BLOCK_LABEL_49;
            parcel.writeInt(1);
            statusbaricon.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void setImeWindowStatus(IBinder ibinder, int i, int j, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBar");
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            parcel.writeInt(j);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(11, parcel, null, 1);
            parcel.recycle();
            return;
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        public void setStatus(int i, String s, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBar");
            parcel.writeInt(i);
            parcel.writeString(s);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_62;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void setSystemUiVisibility(int i, int j, int k, int l, Rect rect, Rect rect1)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBar");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeInt(l);
            if(rect == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            rect.writeToParcel(parcel, 0);
_L3:
            if(rect1 == null)
                break MISSING_BLOCK_LABEL_116;
            parcel.writeInt(1);
            rect1.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(9, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            rect;
            parcel.recycle();
            throw rect;
            parcel.writeInt(0);
              goto _L4
        }

        public void setTopAppHidesStatusBar(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBar");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(31, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void setWindowState(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBar");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(12, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void showAssistDisclosure()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBar");
            mRemote.transact(26, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void showGlobalActionsMenu()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBar");
            mRemote.transact(30, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void showPictureInPictureMenu()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBar");
            mRemote.transact(29, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void showRecentApps(boolean flag, boolean flag1)
            throws RemoteException
        {
            boolean flag2;
            Parcel parcel;
            flag2 = true;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBar");
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(flag1)
                i = ((flag2) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(13, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void showScreenPinningRequest(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBar");
            parcel.writeInt(i);
            mRemote.transact(19, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void showShutdownUi(boolean flag, String s)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBar");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(36, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void startAssist(Bundle bundle)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBar");
            if(bundle == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(27, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            bundle;
            parcel.recycle();
            throw bundle;
        }

        public void toggleKeyboardShortcutsMenu(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBar");
            parcel.writeInt(i);
            mRemote.transact(21, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void togglePanel()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBar");
            mRemote.transact(8, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void toggleRecentApps()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBar");
            mRemote.transact(15, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void toggleSplitScreen()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBar");
            mRemote.transact(16, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void topAppWindowChanged(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBar");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(10, parcel, null, 1);
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


    public abstract void addQsTile(ComponentName componentname)
        throws RemoteException;

    public abstract void animateCollapsePanels()
        throws RemoteException;

    public abstract void animateExpandNotificationsPanel()
        throws RemoteException;

    public abstract void animateExpandSettingsPanel(String s)
        throws RemoteException;

    public abstract void appTransitionCancelled()
        throws RemoteException;

    public abstract void appTransitionFinished()
        throws RemoteException;

    public abstract void appTransitionPending()
        throws RemoteException;

    public abstract void appTransitionStarting(long l, long l1)
        throws RemoteException;

    public abstract void cancelPreloadRecentApps()
        throws RemoteException;

    public abstract void clickQsTile(ComponentName componentname)
        throws RemoteException;

    public abstract void disable(int i, int j)
        throws RemoteException;

    public abstract void dismissKeyboardShortcutsMenu()
        throws RemoteException;

    public abstract void handleSystemKey(int i)
        throws RemoteException;

    public abstract void hideRecentApps(boolean flag, boolean flag1)
        throws RemoteException;

    public abstract void onCameraLaunchGestureDetected(int i)
        throws RemoteException;

    public abstract void preloadRecentApps()
        throws RemoteException;

    public abstract void remQsTile(ComponentName componentname)
        throws RemoteException;

    public abstract void removeIcon(String s)
        throws RemoteException;

    public abstract void setIcon(String s, StatusBarIcon statusbaricon)
        throws RemoteException;

    public abstract void setImeWindowStatus(IBinder ibinder, int i, int j, boolean flag)
        throws RemoteException;

    public abstract void setStatus(int i, String s, Bundle bundle)
        throws RemoteException;

    public abstract void setSystemUiVisibility(int i, int j, int k, int l, Rect rect, Rect rect1)
        throws RemoteException;

    public abstract void setTopAppHidesStatusBar(boolean flag)
        throws RemoteException;

    public abstract void setWindowState(int i, int j)
        throws RemoteException;

    public abstract void showAssistDisclosure()
        throws RemoteException;

    public abstract void showGlobalActionsMenu()
        throws RemoteException;

    public abstract void showPictureInPictureMenu()
        throws RemoteException;

    public abstract void showRecentApps(boolean flag, boolean flag1)
        throws RemoteException;

    public abstract void showScreenPinningRequest(int i)
        throws RemoteException;

    public abstract void showShutdownUi(boolean flag, String s)
        throws RemoteException;

    public abstract void startAssist(Bundle bundle)
        throws RemoteException;

    public abstract void toggleKeyboardShortcutsMenu(int i)
        throws RemoteException;

    public abstract void togglePanel()
        throws RemoteException;

    public abstract void toggleRecentApps()
        throws RemoteException;

    public abstract void toggleSplitScreen()
        throws RemoteException;

    public abstract void topAppWindowChanged(boolean flag)
        throws RemoteException;
}
