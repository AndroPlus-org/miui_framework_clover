// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.statusbar;

import android.content.ComponentName;
import android.graphics.Rect;
import android.os.*;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.android.internal.statusbar:
//            NotificationVisibility, IStatusBar, StatusBarIcon

public interface IStatusBarService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IStatusBarService
    {

        public static IStatusBarService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.statusbar.IStatusBarService");
            if(iinterface != null && (iinterface instanceof IStatusBarService))
                return (IStatusBarService)iinterface;
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
                parcel1.writeString("com.android.internal.statusbar.IStatusBarService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBarService");
                i = parcel.readInt();
                IBinder ibinder = parcel.readStrongBinder();
                String s2 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setStatus(i, ibinder, s2, parcel);
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBarService");
                expandNotificationsPanel();
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBarService");
                collapsePanels();
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBarService");
                togglePanel();
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBarService");
                disable(parcel.readInt(), parcel.readStrongBinder(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBarService");
                disableForUser(parcel.readInt(), parcel.readStrongBinder(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBarService");
                disable2(parcel.readInt(), parcel.readStrongBinder(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBarService");
                disable2ForUser(parcel.readInt(), parcel.readStrongBinder(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBarService");
                setIcon(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBarService");
                String s = parcel.readString();
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                setIconVisibility(s, flag);
                parcel1.writeNoException();
                return true;

            case 11: // '\013'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBarService");
                removeIcon(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 12: // '\f'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBarService");
                IBinder ibinder1 = parcel.readStrongBinder();
                i = parcel.readInt();
                j = parcel.readInt();
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                setImeWindowStatus(ibinder1, i, j, flag1);
                parcel1.writeNoException();
                return true;

            case 13: // '\r'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBarService");
                expandSettingsPanel(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 14: // '\016'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBarService");
                IStatusBar istatusbar = IStatusBar.Stub.asInterface(parcel.readStrongBinder());
                ArrayList arraylist = new ArrayList();
                ArrayList arraylist1 = new ArrayList();
                i = parcel.readInt();
                ArrayList arraylist2;
                Rect rect;
                Rect rect1;
                if(i < 0)
                    parcel = null;
                else
                    parcel = new int[i];
                arraylist2 = new ArrayList();
                rect = new Rect();
                rect1 = new Rect();
                registerStatusBar(istatusbar, arraylist, arraylist1, parcel, arraylist2, rect, rect1);
                parcel1.writeNoException();
                parcel1.writeStringList(arraylist);
                parcel1.writeTypedList(arraylist1);
                parcel1.writeIntArray(parcel);
                parcel1.writeBinderList(arraylist2);
                if(rect != null)
                {
                    parcel1.writeInt(1);
                    rect.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                if(rect1 != null)
                {
                    parcel1.writeInt(1);
                    rect1.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 15: // '\017'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBarService");
                boolean flag2;
                if(parcel.readInt() != 0)
                    flag2 = true;
                else
                    flag2 = false;
                onPanelRevealed(flag2, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 16: // '\020'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBarService");
                onPanelHidden();
                parcel1.writeNoException();
                return true;

            case 17: // '\021'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBarService");
                clearNotificationEffects();
                parcel1.writeNoException();
                return true;

            case 18: // '\022'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBarService");
                onNotificationClick(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 19: // '\023'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBarService");
                onNotificationActionClick(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 20: // '\024'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBarService");
                onNotificationError(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 21: // '\025'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBarService");
                onClearAllNotifications(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 22: // '\026'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBarService");
                onNotificationClear(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 23: // '\027'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBarService");
                onNotificationVisibilityChanged((NotificationVisibility[])parcel.createTypedArray(NotificationVisibility.CREATOR), (NotificationVisibility[])parcel.createTypedArray(NotificationVisibility.CREATOR));
                parcel1.writeNoException();
                return true;

            case 24: // '\030'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBarService");
                String s1 = parcel.readString();
                boolean flag3;
                boolean flag5;
                if(parcel.readInt() != 0)
                    flag3 = true;
                else
                    flag3 = false;
                if(parcel.readInt() != 0)
                    flag5 = true;
                else
                    flag5 = false;
                onNotificationExpansionChanged(s1, flag3, flag5);
                parcel1.writeNoException();
                return true;

            case 25: // '\031'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBarService");
                setSystemUiVisibility(parcel.readInt(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 26: // '\032'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBarService");
                onGlobalActionsShown();
                parcel1.writeNoException();
                return true;

            case 27: // '\033'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBarService");
                onGlobalActionsHidden();
                parcel1.writeNoException();
                return true;

            case 28: // '\034'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBarService");
                shutdown();
                parcel1.writeNoException();
                return true;

            case 29: // '\035'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBarService");
                boolean flag4;
                if(parcel.readInt() != 0)
                    flag4 = true;
                else
                    flag4 = false;
                reboot(flag4);
                parcel1.writeNoException();
                return true;

            case 30: // '\036'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBarService");
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                addTile(parcel);
                parcel1.writeNoException();
                return true;

            case 31: // '\037'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBarService");
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                remTile(parcel);
                parcel1.writeNoException();
                return true;

            case 32: // ' '
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBarService");
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                clickTile(parcel);
                parcel1.writeNoException();
                return true;

            case 33: // '!'
                parcel.enforceInterface("com.android.internal.statusbar.IStatusBarService");
                handleSystemKey(parcel.readInt());
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.internal.statusbar.IStatusBarService";
        static final int TRANSACTION_addTile = 30;
        static final int TRANSACTION_clearNotificationEffects = 17;
        static final int TRANSACTION_clickTile = 32;
        static final int TRANSACTION_collapsePanels = 3;
        static final int TRANSACTION_disable = 5;
        static final int TRANSACTION_disable2 = 7;
        static final int TRANSACTION_disable2ForUser = 8;
        static final int TRANSACTION_disableForUser = 6;
        static final int TRANSACTION_expandNotificationsPanel = 2;
        static final int TRANSACTION_expandSettingsPanel = 13;
        static final int TRANSACTION_handleSystemKey = 33;
        static final int TRANSACTION_onClearAllNotifications = 21;
        static final int TRANSACTION_onGlobalActionsHidden = 27;
        static final int TRANSACTION_onGlobalActionsShown = 26;
        static final int TRANSACTION_onNotificationActionClick = 19;
        static final int TRANSACTION_onNotificationClear = 22;
        static final int TRANSACTION_onNotificationClick = 18;
        static final int TRANSACTION_onNotificationError = 20;
        static final int TRANSACTION_onNotificationExpansionChanged = 24;
        static final int TRANSACTION_onNotificationVisibilityChanged = 23;
        static final int TRANSACTION_onPanelHidden = 16;
        static final int TRANSACTION_onPanelRevealed = 15;
        static final int TRANSACTION_reboot = 29;
        static final int TRANSACTION_registerStatusBar = 14;
        static final int TRANSACTION_remTile = 31;
        static final int TRANSACTION_removeIcon = 11;
        static final int TRANSACTION_setIcon = 9;
        static final int TRANSACTION_setIconVisibility = 10;
        static final int TRANSACTION_setImeWindowStatus = 12;
        static final int TRANSACTION_setStatus = 1;
        static final int TRANSACTION_setSystemUiVisibility = 25;
        static final int TRANSACTION_shutdown = 28;
        static final int TRANSACTION_togglePanel = 4;

        public Stub()
        {
            attachInterface(this, "com.android.internal.statusbar.IStatusBarService");
        }
    }

    private static class Stub.Proxy
        implements IStatusBarService
    {

        public void addTile(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBarService");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(30, parcel, parcel1, 0);
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

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void clearNotificationEffects()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBarService");
            mRemote.transact(17, parcel, parcel1, 0);
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

        public void clickTile(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBarService");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(32, parcel, parcel1, 0);
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

        public void collapsePanels()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBarService");
            mRemote.transact(3, parcel, parcel1, 0);
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

        public void disable(int i, IBinder ibinder, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBarService");
            parcel.writeInt(i);
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void disable2(int i, IBinder ibinder, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBarService");
            parcel.writeInt(i);
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void disable2ForUser(int i, IBinder ibinder, String s, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBarService");
            parcel.writeInt(i);
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeInt(j);
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void disableForUser(int i, IBinder ibinder, String s, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBarService");
            parcel.writeInt(i);
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeInt(j);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void expandNotificationsPanel()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBarService");
            mRemote.transact(2, parcel, parcel1, 0);
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

        public void expandSettingsPanel(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBarService");
            parcel.writeString(s);
            mRemote.transact(13, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.statusbar.IStatusBarService";
        }

        public void handleSystemKey(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBarService");
            parcel.writeInt(i);
            mRemote.transact(33, parcel, parcel1, 0);
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

        public void onClearAllNotifications(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBarService");
            parcel.writeInt(i);
            mRemote.transact(21, parcel, parcel1, 0);
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

        public void onGlobalActionsHidden()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBarService");
            mRemote.transact(27, parcel, parcel1, 0);
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

        public void onGlobalActionsShown()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBarService");
            mRemote.transact(26, parcel, parcel1, 0);
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

        public void onNotificationActionClick(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBarService");
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

        public void onNotificationClear(String s, String s1, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBarService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(22, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void onNotificationClick(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBarService");
            parcel.writeString(s);
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

        public void onNotificationError(String s, String s1, int i, int j, int k, String s2, int l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBarService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeString(s2);
            parcel.writeInt(l);
            mRemote.transact(20, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void onNotificationExpansionChanged(String s, boolean flag, boolean flag1)
            throws RemoteException
        {
            boolean flag2;
            Parcel parcel;
            Parcel parcel1;
            flag2 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBarService");
            parcel.writeString(s);
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
            mRemote.transact(24, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void onNotificationVisibilityChanged(NotificationVisibility anotificationvisibility[], NotificationVisibility anotificationvisibility1[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBarService");
            parcel.writeTypedArray(anotificationvisibility, 0);
            parcel.writeTypedArray(anotificationvisibility1, 0);
            mRemote.transact(23, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            anotificationvisibility;
            parcel1.recycle();
            parcel.recycle();
            throw anotificationvisibility;
        }

        public void onPanelHidden()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBarService");
            mRemote.transact(16, parcel, parcel1, 0);
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

        public void onPanelRevealed(boolean flag, int i)
            throws RemoteException
        {
            int j;
            Parcel parcel;
            Parcel parcel1;
            j = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBarService");
            if(flag)
                j = 1;
            parcel.writeInt(j);
            parcel.writeInt(i);
            mRemote.transact(15, parcel, parcel1, 0);
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

        public void reboot(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBarService");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(29, parcel, parcel1, 0);
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

        public void registerStatusBar(IStatusBar istatusbar, List list, List list1, int ai[], List list2, Rect rect, Rect rect1)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBarService");
            if(istatusbar == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = istatusbar.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(ai != null)
                break MISSING_BLOCK_LABEL_142;
            parcel.writeInt(-1);
_L1:
            mRemote.transact(14, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.readStringList(list);
            parcel1.readTypedList(list1, StatusBarIcon.CREATOR);
            parcel1.readIntArray(ai);
            parcel1.readBinderList(list2);
            if(parcel1.readInt() != 0)
                rect.readFromParcel(parcel1);
            if(parcel1.readInt() != 0)
                rect1.readFromParcel(parcel1);
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(ai.length);
              goto _L1
            istatusbar;
            parcel1.recycle();
            parcel.recycle();
            throw istatusbar;
        }

        public void remTile(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBarService");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(31, parcel, parcel1, 0);
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

        public void removeIcon(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBarService");
            parcel.writeString(s);
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

        public void setIcon(String s, String s1, int i, int j, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBarService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeString(s2);
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

        public void setIconVisibility(String s, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBarService");
            parcel.writeString(s);
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

        public void setImeWindowStatus(IBinder ibinder, int i, int j, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBarService");
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            parcel.writeInt(j);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void setStatus(int i, IBinder ibinder, String s, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBarService");
            parcel.writeInt(i);
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_86;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, parcel1, 0);
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

        public void setSystemUiVisibility(int i, int j, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBarService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeString(s);
            mRemote.transact(25, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void shutdown()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBarService");
            mRemote.transact(28, parcel, parcel1, 0);
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

        public void togglePanel()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.statusbar.IStatusBarService");
            mRemote.transact(4, parcel, parcel1, 0);
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

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void addTile(ComponentName componentname)
        throws RemoteException;

    public abstract void clearNotificationEffects()
        throws RemoteException;

    public abstract void clickTile(ComponentName componentname)
        throws RemoteException;

    public abstract void collapsePanels()
        throws RemoteException;

    public abstract void disable(int i, IBinder ibinder, String s)
        throws RemoteException;

    public abstract void disable2(int i, IBinder ibinder, String s)
        throws RemoteException;

    public abstract void disable2ForUser(int i, IBinder ibinder, String s, int j)
        throws RemoteException;

    public abstract void disableForUser(int i, IBinder ibinder, String s, int j)
        throws RemoteException;

    public abstract void expandNotificationsPanel()
        throws RemoteException;

    public abstract void expandSettingsPanel(String s)
        throws RemoteException;

    public abstract void handleSystemKey(int i)
        throws RemoteException;

    public abstract void onClearAllNotifications(int i)
        throws RemoteException;

    public abstract void onGlobalActionsHidden()
        throws RemoteException;

    public abstract void onGlobalActionsShown()
        throws RemoteException;

    public abstract void onNotificationActionClick(String s, int i)
        throws RemoteException;

    public abstract void onNotificationClear(String s, String s1, int i, int j)
        throws RemoteException;

    public abstract void onNotificationClick(String s)
        throws RemoteException;

    public abstract void onNotificationError(String s, String s1, int i, int j, int k, String s2, int l)
        throws RemoteException;

    public abstract void onNotificationExpansionChanged(String s, boolean flag, boolean flag1)
        throws RemoteException;

    public abstract void onNotificationVisibilityChanged(NotificationVisibility anotificationvisibility[], NotificationVisibility anotificationvisibility1[])
        throws RemoteException;

    public abstract void onPanelHidden()
        throws RemoteException;

    public abstract void onPanelRevealed(boolean flag, int i)
        throws RemoteException;

    public abstract void reboot(boolean flag)
        throws RemoteException;

    public abstract void registerStatusBar(IStatusBar istatusbar, List list, List list1, int ai[], List list2, Rect rect, Rect rect1)
        throws RemoteException;

    public abstract void remTile(ComponentName componentname)
        throws RemoteException;

    public abstract void removeIcon(String s)
        throws RemoteException;

    public abstract void setIcon(String s, String s1, int i, int j, String s2)
        throws RemoteException;

    public abstract void setIconVisibility(String s, boolean flag)
        throws RemoteException;

    public abstract void setImeWindowStatus(IBinder ibinder, int i, int j, boolean flag)
        throws RemoteException;

    public abstract void setStatus(int i, IBinder ibinder, String s, Bundle bundle)
        throws RemoteException;

    public abstract void setSystemUiVisibility(int i, int j, String s)
        throws RemoteException;

    public abstract void shutdown()
        throws RemoteException;

    public abstract void togglePanel()
        throws RemoteException;
}
