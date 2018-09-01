// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.content.ComponentName;
import android.content.IntentSender;
import android.graphics.Rect;
import android.os.*;
import java.util.List;

// Referenced classes of package android.content.pm:
//            IOnAppsChangedListener, ApplicationInfo, ParceledListSlice, ActivityInfo

public interface ILauncherApps
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ILauncherApps
    {

        public static ILauncherApps asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.content.pm.ILauncherApps");
            if(iinterface != null && (iinterface instanceof ILauncherApps))
                return (ILauncherApps)iinterface;
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
            String s8;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.content.pm.ILauncherApps");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.content.pm.ILauncherApps");
                addOnAppsChangedListener(parcel.readString(), IOnAppsChangedListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.content.pm.ILauncherApps");
                removeOnAppsChangedListener(IOnAppsChangedListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.content.pm.ILauncherApps");
                String s = parcel.readString();
                String s9 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (UserHandle)UserHandle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getLauncherActivities(s, s9, parcel);
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
                parcel.enforceInterface("android.content.pm.ILauncherApps");
                String s1 = parcel.readString();
                ComponentName componentname;
                if(parcel.readInt() != 0)
                    componentname = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname = null;
                if(parcel.readInt() != 0)
                    parcel = (UserHandle)UserHandle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = resolveActivity(s1, componentname, parcel);
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

            case 5: // '\005'
                parcel.enforceInterface("android.content.pm.ILauncherApps");
                String s14 = parcel.readString();
                Rect rect;
                ComponentName componentname1;
                Bundle bundle1;
                if(parcel.readInt() != 0)
                    componentname1 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname1 = null;
                if(parcel.readInt() != 0)
                    rect = (Rect)Rect.CREATOR.createFromParcel(parcel);
                else
                    rect = null;
                if(parcel.readInt() != 0)
                    bundle1 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle1 = null;
                if(parcel.readInt() != 0)
                    parcel = (UserHandle)UserHandle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                startActivityAsUser(s14, componentname1, rect, bundle1, parcel);
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.content.pm.ILauncherApps");
                String s15 = parcel.readString();
                Rect rect1;
                ComponentName componentname2;
                Bundle bundle2;
                if(parcel.readInt() != 0)
                    componentname2 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname2 = null;
                if(parcel.readInt() != 0)
                    rect1 = (Rect)Rect.CREATOR.createFromParcel(parcel);
                else
                    rect1 = null;
                if(parcel.readInt() != 0)
                    bundle2 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle2 = null;
                if(parcel.readInt() != 0)
                    parcel = (UserHandle)UserHandle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                showAppDetailsAsUser(s15, componentname2, rect1, bundle2, parcel);
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.content.pm.ILauncherApps");
                String s10 = parcel.readString();
                String s2 = parcel.readString();
                boolean flag;
                if(parcel.readInt() != 0)
                    parcel = (UserHandle)UserHandle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag = isPackageEnabled(s10, s2, parcel);
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.content.pm.ILauncherApps");
                String s3 = parcel.readString();
                ComponentName componentname3;
                boolean flag1;
                if(parcel.readInt() != 0)
                    componentname3 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname3 = null;
                if(parcel.readInt() != 0)
                    parcel = (UserHandle)UserHandle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag1 = isActivityEnabled(s3, componentname3, parcel);
                parcel1.writeNoException();
                if(flag1)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.content.pm.ILauncherApps");
                String s4 = parcel.readString();
                String s11 = parcel.readString();
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (UserHandle)UserHandle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getApplicationInfo(s4, s11, i, parcel);
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

            case 10: // '\n'
                parcel.enforceInterface("android.content.pm.ILauncherApps");
                String s16 = parcel.readString();
                long l = parcel.readLong();
                String s5 = parcel.readString();
                java.util.ArrayList arraylist = parcel.readArrayList(getClass().getClassLoader());
                ComponentName componentname4;
                if(parcel.readInt() != 0)
                    componentname4 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname4 = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (UserHandle)UserHandle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getShortcuts(s16, l, s5, arraylist, componentname4, i, parcel);
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
                parcel.enforceInterface("android.content.pm.ILauncherApps");
                String s12 = parcel.readString();
                String s6 = parcel.readString();
                java.util.ArrayList arraylist1 = parcel.createStringArrayList();
                if(parcel.readInt() != 0)
                    parcel = (UserHandle)UserHandle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                pinShortcuts(s12, s6, arraylist1, parcel);
                parcel1.writeNoException();
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.content.pm.ILauncherApps");
                String s19 = parcel.readString();
                String s18 = parcel.readString();
                String s17 = parcel.readString();
                Bundle bundle;
                Rect rect2;
                boolean flag2;
                if(parcel.readInt() != 0)
                    rect2 = (Rect)Rect.CREATOR.createFromParcel(parcel);
                else
                    rect2 = null;
                if(parcel.readInt() != 0)
                    bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle = null;
                flag2 = startShortcut(s19, s18, s17, rect2, bundle, parcel.readInt());
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.content.pm.ILauncherApps");
                i = getShortcutIconResId(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.content.pm.ILauncherApps");
                parcel = getShortcutIconFd(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt());
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

            case 15: // '\017'
                parcel.enforceInterface("android.content.pm.ILauncherApps");
                boolean flag3 = hasShortcutHostPermission(parcel.readString());
                parcel1.writeNoException();
                if(flag3)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 16: // '\020'
                parcel.enforceInterface("android.content.pm.ILauncherApps");
                String s7 = parcel.readString();
                String s13 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (UserHandle)UserHandle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getShortcutConfigActivities(s7, s13, parcel);
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

            case 17: // '\021'
                parcel.enforceInterface("android.content.pm.ILauncherApps");
                s8 = parcel.readString();
                break;
            }
            ComponentName componentname5;
            if(parcel.readInt() != 0)
                componentname5 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
            else
                componentname5 = null;
            if(parcel.readInt() != 0)
                parcel = (UserHandle)UserHandle.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            parcel = getShortcutConfigActivityIntent(s8, componentname5, parcel);
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
        }

        private static final String DESCRIPTOR = "android.content.pm.ILauncherApps";
        static final int TRANSACTION_addOnAppsChangedListener = 1;
        static final int TRANSACTION_getApplicationInfo = 9;
        static final int TRANSACTION_getLauncherActivities = 3;
        static final int TRANSACTION_getShortcutConfigActivities = 16;
        static final int TRANSACTION_getShortcutConfigActivityIntent = 17;
        static final int TRANSACTION_getShortcutIconFd = 14;
        static final int TRANSACTION_getShortcutIconResId = 13;
        static final int TRANSACTION_getShortcuts = 10;
        static final int TRANSACTION_hasShortcutHostPermission = 15;
        static final int TRANSACTION_isActivityEnabled = 8;
        static final int TRANSACTION_isPackageEnabled = 7;
        static final int TRANSACTION_pinShortcuts = 11;
        static final int TRANSACTION_removeOnAppsChangedListener = 2;
        static final int TRANSACTION_resolveActivity = 4;
        static final int TRANSACTION_showAppDetailsAsUser = 6;
        static final int TRANSACTION_startActivityAsUser = 5;
        static final int TRANSACTION_startShortcut = 12;

        public Stub()
        {
            attachInterface(this, "android.content.pm.ILauncherApps");
        }
    }

    private static class Stub.Proxy
        implements ILauncherApps
    {

        public void addOnAppsChangedListener(String s, IOnAppsChangedListener ionappschangedlistener)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.ILauncherApps");
            parcel.writeString(s);
            s = obj;
            if(ionappschangedlistener == null)
                break MISSING_BLOCK_LABEL_38;
            s = ionappschangedlistener.asBinder();
            parcel.writeStrongBinder(s);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public ApplicationInfo getApplicationInfo(String s, String s1, int i, UserHandle userhandle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.ILauncherApps");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(i);
            if(userhandle == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            userhandle.writeToParcel(parcel, 0);
_L3:
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_132;
            s = (ApplicationInfo)ApplicationInfo.CREATOR.createFromParcel(parcel1);
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

        public String getInterfaceDescriptor()
        {
            return "android.content.pm.ILauncherApps";
        }

        public ParceledListSlice getLauncherActivities(String s, String s1, UserHandle userhandle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.ILauncherApps");
            parcel.writeString(s);
            parcel.writeString(s1);
            if(userhandle == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            userhandle.writeToParcel(parcel, 0);
_L3:
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_123;
            s = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel1);
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

        public ParceledListSlice getShortcutConfigActivities(String s, String s1, UserHandle userhandle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.ILauncherApps");
            parcel.writeString(s);
            parcel.writeString(s1);
            if(userhandle == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            userhandle.writeToParcel(parcel, 0);
_L3:
            mRemote.transact(16, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_124;
            s = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel1);
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

        public IntentSender getShortcutConfigActivityIntent(String s, ComponentName componentname, UserHandle userhandle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.ILauncherApps");
            parcel.writeString(s);
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L5:
            if(userhandle == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            userhandle.writeToParcel(parcel, 0);
_L6:
            mRemote.transact(17, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_144;
            s = (IntentSender)IntentSender.CREATOR.createFromParcel(parcel1);
_L7:
            parcel1.recycle();
            parcel.recycle();
            return s;
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
            s = null;
              goto _L7
        }

        public ParcelFileDescriptor getShortcutIconFd(String s, String s1, String s2, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.ILauncherApps");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeInt(i);
            mRemote.transact(14, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel1);
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

        public int getShortcutIconResId(String s, String s1, String s2, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.ILauncherApps");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
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

        public ParceledListSlice getShortcuts(String s, long l, String s1, List list, ComponentName componentname, int i, 
                UserHandle userhandle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.ILauncherApps");
            parcel.writeString(s);
            parcel.writeLong(l);
            parcel.writeString(s1);
            parcel.writeList(list);
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L5:
            parcel.writeInt(i);
            if(userhandle == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            userhandle.writeToParcel(parcel, 0);
_L6:
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_175;
            s = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel1);
_L7:
            parcel1.recycle();
            parcel.recycle();
            return s;
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
            s = null;
              goto _L7
        }

        public boolean hasShortcutHostPermission(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.content.pm.ILauncherApps");
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

        public boolean isActivityEnabled(String s, ComponentName componentname, UserHandle userhandle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.ILauncherApps");
            parcel.writeString(s);
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L3:
            if(userhandle == null)
                break MISSING_BLOCK_LABEL_129;
            parcel.writeInt(1);
            userhandle.writeToParcel(parcel, 0);
_L4:
            int i;
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

        public boolean isPackageEnabled(String s, String s1, UserHandle userhandle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.ILauncherApps");
            parcel.writeString(s);
            parcel.writeString(s1);
            if(userhandle == null)
                break MISSING_BLOCK_LABEL_96;
            parcel.writeInt(1);
            userhandle.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(7, parcel, parcel1, 0);
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

        public void pinShortcuts(String s, String s1, List list, UserHandle userhandle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.ILauncherApps");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeStringList(list);
            if(userhandle == null)
                break MISSING_BLOCK_LABEL_87;
            parcel.writeInt(1);
            userhandle.writeToParcel(parcel, 0);
_L1:
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

        public void removeOnAppsChangedListener(IOnAppsChangedListener ionappschangedlistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.ILauncherApps");
            if(ionappschangedlistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ionappschangedlistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ionappschangedlistener;
            parcel1.recycle();
            parcel.recycle();
            throw ionappschangedlistener;
        }

        public ActivityInfo resolveActivity(String s, ComponentName componentname, UserHandle userhandle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.ILauncherApps");
            parcel.writeString(s);
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L5:
            if(userhandle == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            userhandle.writeToParcel(parcel, 0);
_L6:
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_143;
            s = (ActivityInfo)ActivityInfo.CREATOR.createFromParcel(parcel1);
_L7:
            parcel1.recycle();
            parcel.recycle();
            return s;
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
            s = null;
              goto _L7
        }

        public void showAppDetailsAsUser(String s, ComponentName componentname, Rect rect, Bundle bundle, UserHandle userhandle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.ILauncherApps");
            parcel.writeString(s);
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L7:
            if(rect == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            rect.writeToParcel(parcel, 0);
_L8:
            if(bundle == null) goto _L6; else goto _L5
_L5:
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L9:
            if(userhandle == null)
                break MISSING_BLOCK_LABEL_168;
            parcel.writeInt(1);
            userhandle.writeToParcel(parcel, 0);
_L10:
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L7
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
_L4:
            parcel.writeInt(0);
              goto _L8
_L6:
            parcel.writeInt(0);
              goto _L9
            parcel.writeInt(0);
              goto _L10
        }

        public void startActivityAsUser(String s, ComponentName componentname, Rect rect, Bundle bundle, UserHandle userhandle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.ILauncherApps");
            parcel.writeString(s);
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L7:
            if(rect == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            rect.writeToParcel(parcel, 0);
_L8:
            if(bundle == null) goto _L6; else goto _L5
_L5:
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L9:
            if(userhandle == null)
                break MISSING_BLOCK_LABEL_167;
            parcel.writeInt(1);
            userhandle.writeToParcel(parcel, 0);
_L10:
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L7
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
_L4:
            parcel.writeInt(0);
              goto _L8
_L6:
            parcel.writeInt(0);
              goto _L9
            parcel.writeInt(0);
              goto _L10
        }

        public boolean startShortcut(String s, String s1, String s2, Rect rect, Bundle bundle, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.ILauncherApps");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            if(rect == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            rect.writeToParcel(parcel, 0);
_L3:
            if(bundle == null)
                break MISSING_BLOCK_LABEL_152;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
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

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void addOnAppsChangedListener(String s, IOnAppsChangedListener ionappschangedlistener)
        throws RemoteException;

    public abstract ApplicationInfo getApplicationInfo(String s, String s1, int i, UserHandle userhandle)
        throws RemoteException;

    public abstract ParceledListSlice getLauncherActivities(String s, String s1, UserHandle userhandle)
        throws RemoteException;

    public abstract ParceledListSlice getShortcutConfigActivities(String s, String s1, UserHandle userhandle)
        throws RemoteException;

    public abstract IntentSender getShortcutConfigActivityIntent(String s, ComponentName componentname, UserHandle userhandle)
        throws RemoteException;

    public abstract ParcelFileDescriptor getShortcutIconFd(String s, String s1, String s2, int i)
        throws RemoteException;

    public abstract int getShortcutIconResId(String s, String s1, String s2, int i)
        throws RemoteException;

    public abstract ParceledListSlice getShortcuts(String s, long l, String s1, List list, ComponentName componentname, int i, 
            UserHandle userhandle)
        throws RemoteException;

    public abstract boolean hasShortcutHostPermission(String s)
        throws RemoteException;

    public abstract boolean isActivityEnabled(String s, ComponentName componentname, UserHandle userhandle)
        throws RemoteException;

    public abstract boolean isPackageEnabled(String s, String s1, UserHandle userhandle)
        throws RemoteException;

    public abstract void pinShortcuts(String s, String s1, List list, UserHandle userhandle)
        throws RemoteException;

    public abstract void removeOnAppsChangedListener(IOnAppsChangedListener ionappschangedlistener)
        throws RemoteException;

    public abstract ActivityInfo resolveActivity(String s, ComponentName componentname, UserHandle userhandle)
        throws RemoteException;

    public abstract void showAppDetailsAsUser(String s, ComponentName componentname, Rect rect, Bundle bundle, UserHandle userhandle)
        throws RemoteException;

    public abstract void startActivityAsUser(String s, ComponentName componentname, Rect rect, Bundle bundle, UserHandle userhandle)
        throws RemoteException;

    public abstract boolean startShortcut(String s, String s1, String s2, Rect rect, Bundle bundle, int i)
        throws RemoteException;
}
