// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.content.*;
import android.graphics.Bitmap;
import android.os.*;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.content.pm:
//            IOnPermissionsChangeListener, PermissionInfo, IPackageDataObserver, IPackageDeleteObserver, 
//            VersionedPackage, IPackageDeleteObserver2, ResolveInfo, ActivityInfo, 
//            ParceledListSlice, ApplicationInfo, ChangedPackages, InstrumentationInfo, 
//            KeySet, PackageInfo, IPackageInstaller, IPackageStatsObserver, 
//            PermissionGroupInfo, ProviderInfo, ServiceInfo, VerifierDeviceIdentity, 
//            IPackageInstallObserver2, PackageCleanItem, IDexModuleRegisterCallback, IPackageMoveObserver

public interface IPackageManager
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IPackageManager
    {

        public static IPackageManager asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.content.pm.IPackageManager");
            if(iinterface != null && (iinterface instanceof IPackageManager))
                return (IPackageManager)iinterface;
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
                parcel1.writeString("android.content.pm.IPackageManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                checkPackageStartable(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                boolean flag = isPackageAvailable(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                parcel = getPackageInfo(parcel.readString(), parcel.readInt(), parcel.readInt());
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
                parcel.enforceInterface("android.content.pm.IPackageManager");
                VersionedPackage versionedpackage;
                if(parcel.readInt() != 0)
                    versionedpackage = (VersionedPackage)VersionedPackage.CREATOR.createFromParcel(parcel);
                else
                    versionedpackage = null;
                parcel = getPackageInfoVersioned(versionedpackage, parcel.readInt(), parcel.readInt());
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
                parcel.enforceInterface("android.content.pm.IPackageManager");
                i = getPackageUid(parcel.readString(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                parcel = getPackageGids(parcel.readString(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeIntArray(parcel);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                parcel = currentToCanonicalPackageNames(parcel.createStringArray());
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                parcel = canonicalToCurrentPackageNames(parcel.createStringArray());
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                parcel = getPermissionInfo(parcel.readString(), parcel.readString(), parcel.readInt());
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
                parcel.enforceInterface("android.content.pm.IPackageManager");
                parcel = queryPermissionsByGroup(parcel.readString(), parcel.readInt());
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
                parcel.enforceInterface("android.content.pm.IPackageManager");
                parcel = getPermissionGroupInfo(parcel.readString(), parcel.readInt());
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

            case 12: // '\f'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                parcel = getAllPermissionGroups(parcel.readInt());
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

            case 13: // '\r'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                parcel = getApplicationInfo(parcel.readString(), parcel.readInt(), parcel.readInt());
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

            case 14: // '\016'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                ComponentName componentname;
                if(parcel.readInt() != 0)
                    componentname = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname = null;
                parcel = getActivityInfo(componentname, parcel.readInt(), parcel.readInt());
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
                parcel.enforceInterface("android.content.pm.IPackageManager");
                boolean flag1;
                ComponentName componentname1;
                Intent intent10;
                if(parcel.readInt() != 0)
                    componentname1 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname1 = null;
                if(parcel.readInt() != 0)
                    intent10 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    intent10 = null;
                flag1 = activitySupportsIntent(componentname1, intent10, parcel.readString());
                parcel1.writeNoException();
                if(flag1)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 16: // '\020'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                ComponentName componentname2;
                if(parcel.readInt() != 0)
                    componentname2 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname2 = null;
                parcel = getReceiverInfo(componentname2, parcel.readInt(), parcel.readInt());
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
                parcel.enforceInterface("android.content.pm.IPackageManager");
                ComponentName componentname3;
                if(parcel.readInt() != 0)
                    componentname3 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname3 = null;
                parcel = getServiceInfo(componentname3, parcel.readInt(), parcel.readInt());
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

            case 18: // '\022'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                ComponentName componentname4;
                if(parcel.readInt() != 0)
                    componentname4 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname4 = null;
                parcel = getProviderInfo(componentname4, parcel.readInt(), parcel.readInt());
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

            case 19: // '\023'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                i = checkPermission(parcel.readString(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 20: // '\024'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                i = checkUidPermission(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 21: // '\025'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                boolean flag2;
                if(parcel.readInt() != 0)
                    parcel = (PermissionInfo)PermissionInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag2 = addPermission(parcel);
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 22: // '\026'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                removePermission(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 23: // '\027'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                grantRuntimePermission(parcel.readString(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 24: // '\030'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                revokeRuntimePermission(parcel.readString(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 25: // '\031'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                resetRuntimePermissions();
                parcel1.writeNoException();
                return true;

            case 26: // '\032'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                i = getPermissionFlags(parcel.readString(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 27: // '\033'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                updatePermissionFlags(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 28: // '\034'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                updatePermissionFlagsForAllApps(parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 29: // '\035'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                boolean flag3 = shouldShowRequestPermissionRationale(parcel.readString(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                if(flag3)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 30: // '\036'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                boolean flag4 = isProtectedBroadcast(parcel.readString());
                parcel1.writeNoException();
                if(flag4)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 31: // '\037'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                i = checkSignatures(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 32: // ' '
                parcel.enforceInterface("android.content.pm.IPackageManager");
                i = checkUidSignatures(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 33: // '!'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                parcel = getAllPackages();
                parcel1.writeNoException();
                parcel1.writeStringList(parcel);
                return true;

            case 34: // '"'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                parcel = getPackagesForUid(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 35: // '#'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                parcel = getNameForUid(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 36: // '$'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                parcel = getNamesForUids(parcel.createIntArray());
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 37: // '%'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                i = getUidForSharedUser(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 38: // '&'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                i = getFlagsForUid(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 39: // '\''
                parcel.enforceInterface("android.content.pm.IPackageManager");
                i = getPrivateFlagsForUid(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 40: // '('
                parcel.enforceInterface("android.content.pm.IPackageManager");
                boolean flag5 = isUidPrivileged(parcel.readInt());
                parcel1.writeNoException();
                if(flag5)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 41: // ')'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                parcel = getAppOpPermissionPackages(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 42: // '*'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                Intent intent;
                if(parcel.readInt() != 0)
                    intent = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    intent = null;
                parcel = resolveIntent(intent, parcel.readString(), parcel.readInt(), parcel.readInt());
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

            case 43: // '+'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                Intent intent1;
                if(parcel.readInt() != 0)
                    intent1 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    intent1 = null;
                parcel = findPersistentPreferredActivity(intent1, parcel.readInt());
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

            case 44: // ','
                parcel.enforceInterface("android.content.pm.IPackageManager");
                boolean flag6;
                Intent intent2;
                if(parcel.readInt() != 0)
                    intent2 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    intent2 = null;
                flag6 = canForwardTo(intent2, parcel.readString(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                if(flag6)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 45: // '-'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                Intent intent3;
                if(parcel.readInt() != 0)
                    intent3 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    intent3 = null;
                parcel = queryIntentActivities(intent3, parcel.readString(), parcel.readInt(), parcel.readInt());
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

            case 46: // '.'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                ComponentName componentname5;
                Intent intent11;
                Intent aintent[];
                String as1[];
                if(parcel.readInt() != 0)
                    componentname5 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname5 = null;
                aintent = (Intent[])parcel.createTypedArray(Intent.CREATOR);
                as1 = parcel.createStringArray();
                if(parcel.readInt() != 0)
                    intent11 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    intent11 = null;
                parcel = queryIntentActivityOptions(componentname5, aintent, as1, intent11, parcel.readString(), parcel.readInt(), parcel.readInt());
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

            case 47: // '/'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                Intent intent4;
                if(parcel.readInt() != 0)
                    intent4 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    intent4 = null;
                parcel = queryIntentReceivers(intent4, parcel.readString(), parcel.readInt(), parcel.readInt());
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

            case 48: // '0'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                Intent intent5;
                if(parcel.readInt() != 0)
                    intent5 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    intent5 = null;
                parcel = resolveService(intent5, parcel.readString(), parcel.readInt(), parcel.readInt());
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

            case 49: // '1'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                Intent intent6;
                if(parcel.readInt() != 0)
                    intent6 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    intent6 = null;
                parcel = queryIntentServices(intent6, parcel.readString(), parcel.readInt(), parcel.readInt());
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

            case 50: // '2'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                Intent intent7;
                if(parcel.readInt() != 0)
                    intent7 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    intent7 = null;
                parcel = queryIntentContentProviders(intent7, parcel.readString(), parcel.readInt(), parcel.readInt());
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

            case 51: // '3'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                parcel = getInstalledPackages(parcel.readInt(), parcel.readInt());
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

            case 52: // '4'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                parcel = getPackagesHoldingPermissions(parcel.createStringArray(), parcel.readInt(), parcel.readInt());
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

            case 53: // '5'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                parcel = getInstalledApplications(parcel.readInt(), parcel.readInt());
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

            case 54: // '6'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                parcel = getPersistentApplications(parcel.readInt());
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

            case 55: // '7'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                parcel = resolveContentProvider(parcel.readString(), parcel.readInt(), parcel.readInt());
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

            case 56: // '8'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                ArrayList arraylist = parcel.createStringArrayList();
                parcel = parcel.createTypedArrayList(ProviderInfo.CREATOR);
                querySyncProviders(arraylist, parcel);
                parcel1.writeNoException();
                parcel1.writeStringList(arraylist);
                parcel1.writeTypedList(parcel);
                return true;

            case 57: // '9'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                parcel = queryContentProviders(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readString());
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

            case 58: // ':'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                ComponentName componentname6;
                if(parcel.readInt() != 0)
                    componentname6 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname6 = null;
                parcel = getInstrumentationInfo(componentname6, parcel.readInt());
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

            case 59: // ';'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                parcel = queryInstrumentation(parcel.readString(), parcel.readInt());
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

            case 60: // '<'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                installPackageAsUser(parcel.readString(), IPackageInstallObserver2.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 61: // '='
                parcel.enforceInterface("android.content.pm.IPackageManager");
                i = parcel.readInt();
                boolean flag7;
                if(parcel.readInt() != 0)
                    flag7 = true;
                else
                    flag7 = false;
                finishPackageInstall(i, flag7);
                parcel1.writeNoException();
                return true;

            case 62: // '>'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                setInstallerPackageName(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 63: // '?'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                setApplicationCategoryHint(parcel.readString(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 64: // '@'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                deletePackageAsUser(parcel.readString(), parcel.readInt(), IPackageDeleteObserver.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 65: // 'A'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                VersionedPackage versionedpackage1;
                if(parcel.readInt() != 0)
                    versionedpackage1 = (VersionedPackage)VersionedPackage.CREATOR.createFromParcel(parcel);
                else
                    versionedpackage1 = null;
                deletePackageVersioned(versionedpackage1, IPackageDeleteObserver2.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 66: // 'B'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                parcel = getInstallerPackageName(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 67: // 'C'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                resetApplicationPreferences(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 68: // 'D'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                Intent intent8;
                if(parcel.readInt() != 0)
                    intent8 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    intent8 = null;
                parcel = getLastChosenActivity(intent8, parcel.readString(), parcel.readInt());
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

            case 69: // 'E'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                Intent intent9;
                IntentFilter intentfilter4;
                String s14;
                if(parcel.readInt() != 0)
                    intent9 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    intent9 = null;
                s14 = parcel.readString();
                j = parcel.readInt();
                if(parcel.readInt() != 0)
                    intentfilter4 = (IntentFilter)IntentFilter.CREATOR.createFromParcel(parcel);
                else
                    intentfilter4 = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setLastChosenActivity(intent9, s14, j, intentfilter4, i, parcel);
                parcel1.writeNoException();
                return true;

            case 70: // 'F'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                IntentFilter intentfilter;
                ComponentName componentname11;
                ComponentName acomponentname[];
                if(parcel.readInt() != 0)
                    intentfilter = (IntentFilter)IntentFilter.CREATOR.createFromParcel(parcel);
                else
                    intentfilter = null;
                i = parcel.readInt();
                acomponentname = (ComponentName[])parcel.createTypedArray(ComponentName.CREATOR);
                if(parcel.readInt() != 0)
                    componentname11 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname11 = null;
                addPreferredActivity(intentfilter, i, acomponentname, componentname11, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 71: // 'G'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                IntentFilter intentfilter1;
                ComponentName componentname12;
                ComponentName acomponentname1[];
                if(parcel.readInt() != 0)
                    intentfilter1 = (IntentFilter)IntentFilter.CREATOR.createFromParcel(parcel);
                else
                    intentfilter1 = null;
                i = parcel.readInt();
                acomponentname1 = (ComponentName[])parcel.createTypedArray(ComponentName.CREATOR);
                if(parcel.readInt() != 0)
                    componentname12 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname12 = null;
                replacePreferredActivity(intentfilter1, i, acomponentname1, componentname12, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 72: // 'H'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                clearPackagePreferredActivities(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 73: // 'I'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                ArrayList arraylist2 = new ArrayList();
                ArrayList arraylist1 = new ArrayList();
                i = getPreferredActivities(arraylist2, arraylist1, parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                parcel1.writeTypedList(arraylist2);
                parcel1.writeTypedList(arraylist1);
                return true;

            case 74: // 'J'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                IntentFilter intentfilter2;
                ComponentName componentname13;
                if(parcel.readInt() != 0)
                    intentfilter2 = (IntentFilter)IntentFilter.CREATOR.createFromParcel(parcel);
                else
                    intentfilter2 = null;
                if(parcel.readInt() != 0)
                    componentname13 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname13 = null;
                addPersistentPreferredActivity(intentfilter2, componentname13, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 75: // 'K'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                clearPackagePersistentPreferredActivities(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 76: // 'L'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                IntentFilter intentfilter3;
                if(parcel.readInt() != 0)
                    intentfilter3 = (IntentFilter)IntentFilter.CREATOR.createFromParcel(parcel);
                else
                    intentfilter3 = null;
                addCrossProfileIntentFilter(intentfilter3, parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 77: // 'M'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                clearCrossProfileIntentFilters(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 78: // 'N'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                String as[] = parcel.createStringArray();
                boolean flag8;
                if(parcel.readInt() != 0)
                    flag8 = true;
                else
                    flag8 = false;
                parcel = setPackagesSuspendedAsUser(as, flag8, parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 79: // 'O'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                boolean flag9 = isPackageSuspendedForUser(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                if(flag9)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 80: // 'P'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                parcel = getPreferredActivityBackup(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeByteArray(parcel);
                return true;

            case 81: // 'Q'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                restorePreferredActivities(parcel.createByteArray(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 82: // 'R'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                parcel = getDefaultAppsBackup(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeByteArray(parcel);
                return true;

            case 83: // 'S'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                restoreDefaultApps(parcel.createByteArray(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 84: // 'T'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                parcel = getIntentFilterVerificationBackup(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeByteArray(parcel);
                return true;

            case 85: // 'U'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                restoreIntentFilterVerification(parcel.createByteArray(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 86: // 'V'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                parcel = getPermissionGrantBackup(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeByteArray(parcel);
                return true;

            case 87: // 'W'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                restorePermissionGrants(parcel.createByteArray(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 88: // 'X'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                parcel = new ArrayList();
                ComponentName componentname7 = getHomeActivities(parcel);
                parcel1.writeNoException();
                if(componentname7 != null)
                {
                    parcel1.writeInt(1);
                    componentname7.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                parcel1.writeTypedList(parcel);
                return true;

            case 89: // 'Y'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                ComponentName componentname8;
                if(parcel.readInt() != 0)
                    componentname8 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname8 = null;
                setHomeActivity(componentname8, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 90: // 'Z'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                ComponentName componentname9;
                if(parcel.readInt() != 0)
                    componentname9 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname9 = null;
                setComponentEnabledSetting(componentname9, parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 91: // '['
                parcel.enforceInterface("android.content.pm.IPackageManager");
                ComponentName componentname10;
                if(parcel.readInt() != 0)
                    componentname10 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname10 = null;
                i = getComponentEnabledSetting(componentname10, parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 92: // '\\'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                setApplicationEnabledSetting(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 93: // ']'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                i = getApplicationEnabledSetting(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 94: // '^'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                logAppProcessStartIfNeeded(parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 95: // '_'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                flushPackageRestrictionsAsUser(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 96: // '`'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                String s = parcel.readString();
                boolean flag10;
                if(parcel.readInt() != 0)
                    flag10 = true;
                else
                    flag10 = false;
                setPackageStoppedState(s, flag10, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 97: // 'a'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                freeStorageAndNotify(parcel.readString(), parcel.readLong(), parcel.readInt(), IPackageDataObserver.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 98: // 'b'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                String s1 = parcel.readString();
                long l = parcel.readLong();
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (IntentSender)IntentSender.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                freeStorage(s1, l, i, parcel);
                parcel1.writeNoException();
                return true;

            case 99: // 'c'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                deleteApplicationCacheFiles(parcel.readString(), IPackageDataObserver.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 100: // 'd'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                deleteApplicationCacheFilesAsUser(parcel.readString(), parcel.readInt(), IPackageDataObserver.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 101: // 'e'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                clearApplicationUserData(parcel.readString(), IPackageDataObserver.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 102: // 'f'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                clearApplicationProfileData(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 103: // 'g'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                getPackageSizeInfo(parcel.readString(), parcel.readInt(), IPackageStatsObserver.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 104: // 'h'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                parcel = getSystemSharedLibraryNames();
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 105: // 'i'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                parcel = getSystemAvailableFeatures();
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

            case 106: // 'j'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                boolean flag11 = hasSystemFeature(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                if(flag11)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 107: // 'k'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                enterSafeMode();
                parcel1.writeNoException();
                return true;

            case 108: // 'l'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                boolean flag12 = isSafeMode();
                parcel1.writeNoException();
                if(flag12)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 109: // 'm'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                systemReady();
                parcel1.writeNoException();
                return true;

            case 110: // 'n'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                boolean flag13 = hasSystemUidErrors();
                parcel1.writeNoException();
                if(flag13)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 111: // 'o'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                performFstrimIfNeeded();
                parcel1.writeNoException();
                return true;

            case 112: // 'p'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                updatePackagesIfNeeded();
                parcel1.writeNoException();
                return true;

            case 113: // 'q'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                notifyPackageUse(parcel.readString(), parcel.readInt());
                return true;

            case 114: // 'r'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                notifyDexLoad(parcel.readString(), parcel.createStringArrayList(), parcel.createStringArrayList(), parcel.readString());
                return true;

            case 115: // 's'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                parcel1 = parcel.readString();
                String s2 = parcel.readString();
                boolean flag14;
                if(parcel.readInt() != 0)
                    flag14 = true;
                else
                    flag14 = false;
                registerDexModule(parcel1, s2, flag14, IDexModuleRegisterCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 116: // 't'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                String s12 = parcel.readString();
                boolean flag15;
                String s3;
                boolean flag42;
                boolean flag44;
                if(parcel.readInt() != 0)
                    flag15 = true;
                else
                    flag15 = false;
                s3 = parcel.readString();
                if(parcel.readInt() != 0)
                    flag42 = true;
                else
                    flag42 = false;
                if(parcel.readInt() != 0)
                    flag44 = true;
                else
                    flag44 = false;
                flag15 = performDexOptMode(s12, flag15, s3, flag42, flag44, parcel.readString());
                parcel1.writeNoException();
                if(flag15)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 117: // 'u'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                String s4 = parcel.readString();
                String s13 = parcel.readString();
                boolean flag16;
                if(parcel.readInt() != 0)
                    flag16 = true;
                else
                    flag16 = false;
                flag16 = performDexOptSecondary(s4, s13, flag16);
                parcel1.writeNoException();
                if(flag16)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 118: // 'v'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                dumpProfiles(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 119: // 'w'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                forceDexOpt(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 120: // 'x'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                boolean flag17 = runBackgroundDexoptJob();
                parcel1.writeNoException();
                if(flag17)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 121: // 'y'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                reconcileSecondaryDexFiles(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 122: // 'z'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                boolean flag18;
                boolean flag43;
                if(parcel.readInt() != 0)
                    flag18 = true;
                else
                    flag18 = false;
                if(parcel.readInt() != 0)
                    flag43 = true;
                else
                    flag43 = false;
                updateExternalMediaStatus(flag18, flag43);
                parcel1.writeNoException();
                return true;

            case 123: // '{'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                if(parcel.readInt() != 0)
                    parcel = (PackageCleanItem)PackageCleanItem.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = nextPackageToClean(parcel);
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

            case 124: // '|'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                i = getMoveStatus(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 125: // '}'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                registerMoveCallback(IPackageMoveObserver.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 126: // '~'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                unregisterMoveCallback(IPackageMoveObserver.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 127: // '\177'
                parcel.enforceInterface("android.content.pm.IPackageManager");
                i = movePackage(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 128: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                i = movePrimaryStorage(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 129: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                boolean flag19;
                if(parcel.readInt() != 0)
                    parcel = (PermissionInfo)PermissionInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag19 = addPermissionAsync(parcel);
                parcel1.writeNoException();
                if(flag19)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 130: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                boolean flag20 = setInstallLocation(parcel.readInt());
                parcel1.writeNoException();
                if(flag20)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 131: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                i = getInstallLocation();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 132: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                i = installExistingPackageAsUser(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 133: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                verifyPendingInstall(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 134: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                extendVerificationTimeout(parcel.readInt(), parcel.readInt(), parcel.readLong());
                parcel1.writeNoException();
                return true;

            case 135: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                verifyIntentFilter(parcel.readInt(), parcel.readInt(), parcel.createStringArrayList());
                parcel1.writeNoException();
                return true;

            case 136: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                i = getIntentVerificationStatus(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 137: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                boolean flag21 = updateIntentVerificationStatus(parcel.readString(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                if(flag21)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 138: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                parcel = getIntentFilterVerifications(parcel.readString());
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

            case 139: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                parcel = getAllIntentFilters(parcel.readString());
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

            case 140: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                boolean flag22 = setDefaultBrowserPackageName(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                if(flag22)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 141: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                parcel = getDefaultBrowserPackageName(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 142: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                parcel = getVerifierDeviceIdentity();
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

            case 143: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                boolean flag23 = isFirstBoot();
                parcel1.writeNoException();
                if(flag23)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 144: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                boolean flag24 = isOnlyCoreApps();
                parcel1.writeNoException();
                if(flag24)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 145: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                boolean flag25 = isUpgrade();
                parcel1.writeNoException();
                if(flag25)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 146: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                String s5 = parcel.readString();
                boolean flag26;
                if(parcel.readInt() != 0)
                    flag26 = true;
                else
                    flag26 = false;
                setPermissionEnforced(s5, flag26);
                parcel1.writeNoException();
                return true;

            case 147: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                boolean flag27 = isPermissionEnforced(parcel.readString());
                parcel1.writeNoException();
                if(flag27)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 148: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                boolean flag28 = isStorageLow();
                parcel1.writeNoException();
                if(flag28)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 149: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                String s6 = parcel.readString();
                boolean flag29;
                if(parcel.readInt() != 0)
                    flag29 = true;
                else
                    flag29 = false;
                flag29 = setApplicationHiddenSettingAsUser(s6, flag29, parcel.readInt());
                parcel1.writeNoException();
                if(flag29)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 150: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                boolean flag30 = getApplicationHiddenSettingAsUser(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                if(flag30)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 151: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                parcel = getPackageInstaller();
                parcel1.writeNoException();
                if(parcel != null)
                    parcel = parcel.asBinder();
                else
                    parcel = null;
                parcel1.writeStrongBinder(parcel);
                return true;

            case 152: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                String s7 = parcel.readString();
                boolean flag31;
                if(parcel.readInt() != 0)
                    flag31 = true;
                else
                    flag31 = false;
                flag31 = setBlockUninstallForUser(s7, flag31, parcel.readInt());
                parcel1.writeNoException();
                if(flag31)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 153: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                boolean flag32 = getBlockUninstallForUser(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                if(flag32)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 154: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                parcel = getKeySetByAlias(parcel.readString(), parcel.readString());
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

            case 155: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                parcel = getSigningKeySet(parcel.readString());
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

            case 156: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                String s8 = parcel.readString();
                boolean flag33;
                if(parcel.readInt() != 0)
                    parcel = (KeySet)KeySet.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag33 = isPackageSignedByKeySet(s8, parcel);
                parcel1.writeNoException();
                if(flag33)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 157: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                String s9 = parcel.readString();
                boolean flag34;
                if(parcel.readInt() != 0)
                    parcel = (KeySet)KeySet.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag34 = isPackageSignedByKeySetExactly(s9, parcel);
                parcel1.writeNoException();
                if(flag34)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 158: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                addOnPermissionsChangeListener(IOnPermissionsChangeListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 159: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                removeOnPermissionsChangeListener(IOnPermissionsChangeListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 160: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                grantDefaultPermissionsToEnabledCarrierApps(parcel.createStringArray(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 161: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                grantDefaultPermissionsToEnabledImsServices(parcel.createStringArray(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 162: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                boolean flag35 = isPermissionRevokedByPolicy(parcel.readString(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                if(flag35)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 163: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                parcel = getPermissionControllerPackageName();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 164: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                parcel = getInstantApps(parcel.readInt());
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

            case 165: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                parcel = getInstantAppCookie(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeByteArray(parcel);
                return true;

            case 166: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                boolean flag36 = setInstantAppCookie(parcel.readString(), parcel.createByteArray(), parcel.readInt());
                parcel1.writeNoException();
                if(flag36)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 167: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                parcel = getInstantAppIcon(parcel.readString(), parcel.readInt());
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

            case 168: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                boolean flag37 = isInstantApp(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                if(flag37)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 169: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                String s10 = parcel.readString();
                boolean flag38;
                if(parcel.readInt() != 0)
                    flag38 = true;
                else
                    flag38 = false;
                flag38 = setRequiredForSystemUser(s10, flag38);
                parcel1.writeNoException();
                if(flag38)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 170: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                String s11 = parcel.readString();
                boolean flag39;
                if(parcel.readInt() != 0)
                    flag39 = true;
                else
                    flag39 = false;
                setUpdateAvailable(s11, flag39);
                parcel1.writeNoException();
                return true;

            case 171: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                parcel = getServicesSystemSharedLibraryPackageName();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 172: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                parcel = getSharedSystemSharedLibraryPackageName();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 173: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                parcel = getChangedPackages(parcel.readInt(), parcel.readInt());
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

            case 174: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                boolean flag40 = isPackageDeviceAdminOnAnyUser(parcel.readString());
                parcel1.writeNoException();
                if(flag40)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 175: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                parcel = getPreviousCodePaths(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeStringList(parcel);
                return true;

            case 176: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                i = getInstallReason(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 177: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                parcel = getSharedLibraries(parcel.readString(), parcel.readInt(), parcel.readInt());
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

            case 178: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                boolean flag41 = canRequestPackageInstalls(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                if(flag41)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 179: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                deletePreloadsFileCache();
                parcel1.writeNoException();
                return true;

            case 180: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                parcel = getInstantAppResolverComponent();
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

            case 181: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                parcel = getInstantAppResolverSettingsComponent();
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

            case 182: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                parcel = getInstantAppInstallerComponent();
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

            case 183: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                parcel = getInstantAppAndroidId(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 184: 
                parcel.enforceInterface("android.content.pm.IPackageManager");
                revokeRuntimePermissionNotKill(parcel.readString(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.content.pm.IPackageManager";
        static final int TRANSACTION_activitySupportsIntent = 15;
        static final int TRANSACTION_addCrossProfileIntentFilter = 76;
        static final int TRANSACTION_addOnPermissionsChangeListener = 158;
        static final int TRANSACTION_addPermission = 21;
        static final int TRANSACTION_addPermissionAsync = 129;
        static final int TRANSACTION_addPersistentPreferredActivity = 74;
        static final int TRANSACTION_addPreferredActivity = 70;
        static final int TRANSACTION_canForwardTo = 44;
        static final int TRANSACTION_canRequestPackageInstalls = 178;
        static final int TRANSACTION_canonicalToCurrentPackageNames = 8;
        static final int TRANSACTION_checkPackageStartable = 1;
        static final int TRANSACTION_checkPermission = 19;
        static final int TRANSACTION_checkSignatures = 31;
        static final int TRANSACTION_checkUidPermission = 20;
        static final int TRANSACTION_checkUidSignatures = 32;
        static final int TRANSACTION_clearApplicationProfileData = 102;
        static final int TRANSACTION_clearApplicationUserData = 101;
        static final int TRANSACTION_clearCrossProfileIntentFilters = 77;
        static final int TRANSACTION_clearPackagePersistentPreferredActivities = 75;
        static final int TRANSACTION_clearPackagePreferredActivities = 72;
        static final int TRANSACTION_currentToCanonicalPackageNames = 7;
        static final int TRANSACTION_deleteApplicationCacheFiles = 99;
        static final int TRANSACTION_deleteApplicationCacheFilesAsUser = 100;
        static final int TRANSACTION_deletePackageAsUser = 64;
        static final int TRANSACTION_deletePackageVersioned = 65;
        static final int TRANSACTION_deletePreloadsFileCache = 179;
        static final int TRANSACTION_dumpProfiles = 118;
        static final int TRANSACTION_enterSafeMode = 107;
        static final int TRANSACTION_extendVerificationTimeout = 134;
        static final int TRANSACTION_findPersistentPreferredActivity = 43;
        static final int TRANSACTION_finishPackageInstall = 61;
        static final int TRANSACTION_flushPackageRestrictionsAsUser = 95;
        static final int TRANSACTION_forceDexOpt = 119;
        static final int TRANSACTION_freeStorage = 98;
        static final int TRANSACTION_freeStorageAndNotify = 97;
        static final int TRANSACTION_getActivityInfo = 14;
        static final int TRANSACTION_getAllIntentFilters = 139;
        static final int TRANSACTION_getAllPackages = 33;
        static final int TRANSACTION_getAllPermissionGroups = 12;
        static final int TRANSACTION_getAppOpPermissionPackages = 41;
        static final int TRANSACTION_getApplicationEnabledSetting = 93;
        static final int TRANSACTION_getApplicationHiddenSettingAsUser = 150;
        static final int TRANSACTION_getApplicationInfo = 13;
        static final int TRANSACTION_getBlockUninstallForUser = 153;
        static final int TRANSACTION_getChangedPackages = 173;
        static final int TRANSACTION_getComponentEnabledSetting = 91;
        static final int TRANSACTION_getDefaultAppsBackup = 82;
        static final int TRANSACTION_getDefaultBrowserPackageName = 141;
        static final int TRANSACTION_getFlagsForUid = 38;
        static final int TRANSACTION_getHomeActivities = 88;
        static final int TRANSACTION_getInstallLocation = 131;
        static final int TRANSACTION_getInstallReason = 176;
        static final int TRANSACTION_getInstalledApplications = 53;
        static final int TRANSACTION_getInstalledPackages = 51;
        static final int TRANSACTION_getInstallerPackageName = 66;
        static final int TRANSACTION_getInstantAppAndroidId = 183;
        static final int TRANSACTION_getInstantAppCookie = 165;
        static final int TRANSACTION_getInstantAppIcon = 167;
        static final int TRANSACTION_getInstantAppInstallerComponent = 182;
        static final int TRANSACTION_getInstantAppResolverComponent = 180;
        static final int TRANSACTION_getInstantAppResolverSettingsComponent = 181;
        static final int TRANSACTION_getInstantApps = 164;
        static final int TRANSACTION_getInstrumentationInfo = 58;
        static final int TRANSACTION_getIntentFilterVerificationBackup = 84;
        static final int TRANSACTION_getIntentFilterVerifications = 138;
        static final int TRANSACTION_getIntentVerificationStatus = 136;
        static final int TRANSACTION_getKeySetByAlias = 154;
        static final int TRANSACTION_getLastChosenActivity = 68;
        static final int TRANSACTION_getMoveStatus = 124;
        static final int TRANSACTION_getNameForUid = 35;
        static final int TRANSACTION_getNamesForUids = 36;
        static final int TRANSACTION_getPackageGids = 6;
        static final int TRANSACTION_getPackageInfo = 3;
        static final int TRANSACTION_getPackageInfoVersioned = 4;
        static final int TRANSACTION_getPackageInstaller = 151;
        static final int TRANSACTION_getPackageSizeInfo = 103;
        static final int TRANSACTION_getPackageUid = 5;
        static final int TRANSACTION_getPackagesForUid = 34;
        static final int TRANSACTION_getPackagesHoldingPermissions = 52;
        static final int TRANSACTION_getPermissionControllerPackageName = 163;
        static final int TRANSACTION_getPermissionFlags = 26;
        static final int TRANSACTION_getPermissionGrantBackup = 86;
        static final int TRANSACTION_getPermissionGroupInfo = 11;
        static final int TRANSACTION_getPermissionInfo = 9;
        static final int TRANSACTION_getPersistentApplications = 54;
        static final int TRANSACTION_getPreferredActivities = 73;
        static final int TRANSACTION_getPreferredActivityBackup = 80;
        static final int TRANSACTION_getPreviousCodePaths = 175;
        static final int TRANSACTION_getPrivateFlagsForUid = 39;
        static final int TRANSACTION_getProviderInfo = 18;
        static final int TRANSACTION_getReceiverInfo = 16;
        static final int TRANSACTION_getServiceInfo = 17;
        static final int TRANSACTION_getServicesSystemSharedLibraryPackageName = 171;
        static final int TRANSACTION_getSharedLibraries = 177;
        static final int TRANSACTION_getSharedSystemSharedLibraryPackageName = 172;
        static final int TRANSACTION_getSigningKeySet = 155;
        static final int TRANSACTION_getSystemAvailableFeatures = 105;
        static final int TRANSACTION_getSystemSharedLibraryNames = 104;
        static final int TRANSACTION_getUidForSharedUser = 37;
        static final int TRANSACTION_getVerifierDeviceIdentity = 142;
        static final int TRANSACTION_grantDefaultPermissionsToEnabledCarrierApps = 160;
        static final int TRANSACTION_grantDefaultPermissionsToEnabledImsServices = 161;
        static final int TRANSACTION_grantRuntimePermission = 23;
        static final int TRANSACTION_hasSystemFeature = 106;
        static final int TRANSACTION_hasSystemUidErrors = 110;
        static final int TRANSACTION_installExistingPackageAsUser = 132;
        static final int TRANSACTION_installPackageAsUser = 60;
        static final int TRANSACTION_isFirstBoot = 143;
        static final int TRANSACTION_isInstantApp = 168;
        static final int TRANSACTION_isOnlyCoreApps = 144;
        static final int TRANSACTION_isPackageAvailable = 2;
        static final int TRANSACTION_isPackageDeviceAdminOnAnyUser = 174;
        static final int TRANSACTION_isPackageSignedByKeySet = 156;
        static final int TRANSACTION_isPackageSignedByKeySetExactly = 157;
        static final int TRANSACTION_isPackageSuspendedForUser = 79;
        static final int TRANSACTION_isPermissionEnforced = 147;
        static final int TRANSACTION_isPermissionRevokedByPolicy = 162;
        static final int TRANSACTION_isProtectedBroadcast = 30;
        static final int TRANSACTION_isSafeMode = 108;
        static final int TRANSACTION_isStorageLow = 148;
        static final int TRANSACTION_isUidPrivileged = 40;
        static final int TRANSACTION_isUpgrade = 145;
        static final int TRANSACTION_logAppProcessStartIfNeeded = 94;
        static final int TRANSACTION_movePackage = 127;
        static final int TRANSACTION_movePrimaryStorage = 128;
        static final int TRANSACTION_nextPackageToClean = 123;
        static final int TRANSACTION_notifyDexLoad = 114;
        static final int TRANSACTION_notifyPackageUse = 113;
        static final int TRANSACTION_performDexOptMode = 116;
        static final int TRANSACTION_performDexOptSecondary = 117;
        static final int TRANSACTION_performFstrimIfNeeded = 111;
        static final int TRANSACTION_queryContentProviders = 57;
        static final int TRANSACTION_queryInstrumentation = 59;
        static final int TRANSACTION_queryIntentActivities = 45;
        static final int TRANSACTION_queryIntentActivityOptions = 46;
        static final int TRANSACTION_queryIntentContentProviders = 50;
        static final int TRANSACTION_queryIntentReceivers = 47;
        static final int TRANSACTION_queryIntentServices = 49;
        static final int TRANSACTION_queryPermissionsByGroup = 10;
        static final int TRANSACTION_querySyncProviders = 56;
        static final int TRANSACTION_reconcileSecondaryDexFiles = 121;
        static final int TRANSACTION_registerDexModule = 115;
        static final int TRANSACTION_registerMoveCallback = 125;
        static final int TRANSACTION_removeOnPermissionsChangeListener = 159;
        static final int TRANSACTION_removePermission = 22;
        static final int TRANSACTION_replacePreferredActivity = 71;
        static final int TRANSACTION_resetApplicationPreferences = 67;
        static final int TRANSACTION_resetRuntimePermissions = 25;
        static final int TRANSACTION_resolveContentProvider = 55;
        static final int TRANSACTION_resolveIntent = 42;
        static final int TRANSACTION_resolveService = 48;
        static final int TRANSACTION_restoreDefaultApps = 83;
        static final int TRANSACTION_restoreIntentFilterVerification = 85;
        static final int TRANSACTION_restorePermissionGrants = 87;
        static final int TRANSACTION_restorePreferredActivities = 81;
        static final int TRANSACTION_revokeRuntimePermission = 24;
        static final int TRANSACTION_revokeRuntimePermissionNotKill = 184;
        static final int TRANSACTION_runBackgroundDexoptJob = 120;
        static final int TRANSACTION_setApplicationCategoryHint = 63;
        static final int TRANSACTION_setApplicationEnabledSetting = 92;
        static final int TRANSACTION_setApplicationHiddenSettingAsUser = 149;
        static final int TRANSACTION_setBlockUninstallForUser = 152;
        static final int TRANSACTION_setComponentEnabledSetting = 90;
        static final int TRANSACTION_setDefaultBrowserPackageName = 140;
        static final int TRANSACTION_setHomeActivity = 89;
        static final int TRANSACTION_setInstallLocation = 130;
        static final int TRANSACTION_setInstallerPackageName = 62;
        static final int TRANSACTION_setInstantAppCookie = 166;
        static final int TRANSACTION_setLastChosenActivity = 69;
        static final int TRANSACTION_setPackageStoppedState = 96;
        static final int TRANSACTION_setPackagesSuspendedAsUser = 78;
        static final int TRANSACTION_setPermissionEnforced = 146;
        static final int TRANSACTION_setRequiredForSystemUser = 169;
        static final int TRANSACTION_setUpdateAvailable = 170;
        static final int TRANSACTION_shouldShowRequestPermissionRationale = 29;
        static final int TRANSACTION_systemReady = 109;
        static final int TRANSACTION_unregisterMoveCallback = 126;
        static final int TRANSACTION_updateExternalMediaStatus = 122;
        static final int TRANSACTION_updateIntentVerificationStatus = 137;
        static final int TRANSACTION_updatePackagesIfNeeded = 112;
        static final int TRANSACTION_updatePermissionFlags = 27;
        static final int TRANSACTION_updatePermissionFlagsForAllApps = 28;
        static final int TRANSACTION_verifyIntentFilter = 135;
        static final int TRANSACTION_verifyPendingInstall = 133;

        public Stub()
        {
            attachInterface(this, "android.content.pm.IPackageManager");
        }
    }

    private static class Stub.Proxy
        implements IPackageManager
    {

        public boolean activitySupportsIntent(ComponentName componentname, Intent intent, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L3:
            if(intent == null)
                break MISSING_BLOCK_LABEL_129;
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L4:
            int i;
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
_L2:
            parcel.writeInt(0);
              goto _L3
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
            parcel.writeInt(0);
              goto _L4
        }

        public void addCrossProfileIntentFilter(IntentFilter intentfilter, String s, int i, int j, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            if(intentfilter == null)
                break MISSING_BLOCK_LABEL_93;
            parcel.writeInt(1);
            intentfilter.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(76, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            intentfilter;
            parcel1.recycle();
            parcel.recycle();
            throw intentfilter;
        }

        public void addOnPermissionsChangeListener(IOnPermissionsChangeListener ionpermissionschangelistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            if(ionpermissionschangelistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ionpermissionschangelistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(158, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ionpermissionschangelistener;
            parcel1.recycle();
            parcel.recycle();
            throw ionpermissionschangelistener;
        }

        public boolean addPermission(PermissionInfo permissioninfo)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            if(permissioninfo == null)
                break MISSING_BLOCK_LABEL_73;
            parcel.writeInt(1);
            permissioninfo.writeToParcel(parcel, 0);
_L1:
            int i;
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
            parcel.writeInt(0);
              goto _L1
            permissioninfo;
            parcel1.recycle();
            parcel.recycle();
            throw permissioninfo;
        }

        public boolean addPermissionAsync(PermissionInfo permissioninfo)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            if(permissioninfo == null)
                break MISSING_BLOCK_LABEL_74;
            parcel.writeInt(1);
            permissioninfo.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(129, parcel, parcel1, 0);
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
            permissioninfo;
            parcel1.recycle();
            parcel.recycle();
            throw permissioninfo;
        }

        public void addPersistentPreferredActivity(IntentFilter intentfilter, ComponentName componentname, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            if(intentfilter == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            intentfilter.writeToParcel(parcel, 0);
_L3:
            if(componentname == null)
                break MISSING_BLOCK_LABEL_112;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L4:
            parcel.writeInt(i);
            mRemote.transact(74, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            intentfilter;
            parcel1.recycle();
            parcel.recycle();
            throw intentfilter;
            parcel.writeInt(0);
              goto _L4
        }

        public void addPreferredActivity(IntentFilter intentfilter, int i, ComponentName acomponentname[], ComponentName componentname, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            if(intentfilter == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            intentfilter.writeToParcel(parcel, 0);
_L3:
            parcel.writeInt(i);
            parcel.writeTypedArray(acomponentname, 0);
            if(componentname == null)
                break MISSING_BLOCK_LABEL_128;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L4:
            parcel.writeInt(j);
            mRemote.transact(70, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            intentfilter;
            parcel1.recycle();
            parcel.recycle();
            throw intentfilter;
            parcel.writeInt(0);
              goto _L4
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public boolean canForwardTo(Intent intent, String s, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            if(intent == null)
                break MISSING_BLOCK_LABEL_101;
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(44, parcel, parcel1, 0);
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
            intent;
            parcel1.recycle();
            parcel.recycle();
            throw intent;
        }

        public boolean canRequestPackageInstalls(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(178, parcel, parcel1, 0);
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

        public String[] canonicalToCurrentPackageNames(String as[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeStringArray(as);
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            as = parcel1.createStringArray();
            parcel1.recycle();
            parcel.recycle();
            return as;
            as;
            parcel1.recycle();
            parcel.recycle();
            throw as;
        }

        public void checkPackageStartable(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeInt(i);
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

        public int checkPermission(String s, String s1, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(i);
            mRemote.transact(19, parcel, parcel1, 0);
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

        public int checkSignatures(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(31, parcel, parcel1, 0);
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

        public int checkUidPermission(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(20, parcel, parcel1, 0);
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

        public int checkUidSignatures(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(32, parcel, parcel1, 0);
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

        public void clearApplicationProfileData(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            mRemote.transact(102, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void clearApplicationUserData(String s, IPackageDataObserver ipackagedataobserver, int i)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            s = obj;
            if(ipackagedataobserver == null)
                break MISSING_BLOCK_LABEL_40;
            s = ipackagedataobserver.asBinder();
            parcel.writeStrongBinder(s);
            parcel.writeInt(i);
            mRemote.transact(101, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void clearCrossProfileIntentFilters(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(77, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void clearPackagePersistentPreferredActivities(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(75, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void clearPackagePreferredActivities(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            mRemote.transact(72, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public String[] currentToCanonicalPackageNames(String as[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeStringArray(as);
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            as = parcel1.createStringArray();
            parcel1.recycle();
            parcel.recycle();
            return as;
            as;
            parcel1.recycle();
            parcel.recycle();
            throw as;
        }

        public void deleteApplicationCacheFiles(String s, IPackageDataObserver ipackagedataobserver)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            s = obj;
            if(ipackagedataobserver == null)
                break MISSING_BLOCK_LABEL_38;
            s = ipackagedataobserver.asBinder();
            parcel.writeStrongBinder(s);
            mRemote.transact(99, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void deleteApplicationCacheFilesAsUser(String s, int i, IPackageDataObserver ipackagedataobserver)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            s = obj;
            if(ipackagedataobserver == null)
                break MISSING_BLOCK_LABEL_46;
            s = ipackagedataobserver.asBinder();
            parcel.writeStrongBinder(s);
            mRemote.transact(100, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void deletePackageAsUser(String s, int i, IPackageDeleteObserver ipackagedeleteobserver, int j, int k)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            s = obj;
            if(ipackagedeleteobserver == null)
                break MISSING_BLOCK_LABEL_46;
            s = ipackagedeleteobserver.asBinder();
            parcel.writeStrongBinder(s);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(64, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void deletePackageVersioned(VersionedPackage versionedpackage, IPackageDeleteObserver2 ipackagedeleteobserver2, int i, int j)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            if(versionedpackage == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            versionedpackage.writeToParcel(parcel, 0);
_L4:
            versionedpackage = obj;
            if(ipackagedeleteobserver2 == null)
                break MISSING_BLOCK_LABEL_51;
            versionedpackage = ipackagedeleteobserver2.asBinder();
            parcel.writeStrongBinder(versionedpackage);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(65, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
            if(true) goto _L4; else goto _L3
_L3:
            versionedpackage;
            parcel1.recycle();
            parcel.recycle();
            throw versionedpackage;
        }

        public void deletePreloadsFileCache()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            mRemote.transact(179, parcel, parcel1, 0);
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

        public void dumpProfiles(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            mRemote.transact(118, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void enterSafeMode()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            mRemote.transact(107, parcel, parcel1, 0);
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

        public void extendVerificationTimeout(int i, int j, long l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeLong(l);
            mRemote.transact(134, parcel, parcel1, 0);
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

        public ResolveInfo findPersistentPreferredActivity(Intent intent, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            if(intent == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L3:
            parcel.writeInt(i);
            mRemote.transact(43, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_109;
            intent = (ResolveInfo)ResolveInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return intent;
_L2:
            parcel.writeInt(0);
              goto _L3
            intent;
            parcel1.recycle();
            parcel.recycle();
            throw intent;
            intent = null;
              goto _L4
        }

        public void finishPackageInstall(int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(61, parcel, parcel1, 0);
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

        public void flushPackageRestrictionsAsUser(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeInt(i);
            mRemote.transact(95, parcel, parcel1, 0);
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

        public void forceDexOpt(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            mRemote.transact(119, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void freeStorage(String s, long l, int i, IntentSender intentsender)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeLong(l);
            parcel.writeInt(i);
            if(intentsender == null)
                break MISSING_BLOCK_LABEL_88;
            parcel.writeInt(1);
            intentsender.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(98, parcel, parcel1, 0);
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

        public void freeStorageAndNotify(String s, long l, int i, IPackageDataObserver ipackagedataobserver)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeLong(l);
            parcel.writeInt(i);
            s = obj;
            if(ipackagedataobserver == null)
                break MISSING_BLOCK_LABEL_55;
            s = ipackagedataobserver.asBinder();
            parcel.writeStrongBinder(s);
            mRemote.transact(97, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public ActivityInfo getActivityInfo(ComponentName componentname, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L3:
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(14, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_124;
            componentname = (ActivityInfo)ActivityInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return componentname;
_L2:
            parcel.writeInt(0);
              goto _L3
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
            componentname = null;
              goto _L4
        }

        public ParceledListSlice getAllIntentFilters(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            mRemote.transact(139, parcel, parcel1, 0);
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

        public List getAllPackages()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            ArrayList arraylist;
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            mRemote.transact(33, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createStringArrayList();
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public ParceledListSlice getAllPermissionGroups(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeInt(i);
            mRemote.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            ParceledListSlice parceledlistslice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return parceledlistslice;
_L2:
            parceledlistslice = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String[] getAppOpPermissionPackages(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            mRemote.transact(41, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.createStringArray();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int getApplicationEnabledSetting(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(93, parcel, parcel1, 0);
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

        public boolean getApplicationHiddenSettingAsUser(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(150, parcel, parcel1, 0);
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

        public ApplicationInfo getApplicationInfo(String s, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(13, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (ApplicationInfo)ApplicationInfo.CREATOR.createFromParcel(parcel1);
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

        public boolean getBlockUninstallForUser(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(153, parcel, parcel1, 0);
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

        public ChangedPackages getChangedPackages(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(173, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            ChangedPackages changedpackages = (ChangedPackages)ChangedPackages.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return changedpackages;
_L2:
            changedpackages = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getComponentEnabledSetting(ComponentName componentname, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_73;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(91, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public byte[] getDefaultAppsBackup(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            byte abyte0[];
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeInt(i);
            mRemote.transact(82, parcel, parcel1, 0);
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

        public String getDefaultBrowserPackageName(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeInt(i);
            mRemote.transact(141, parcel, parcel1, 0);
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

        public int getFlagsForUid(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeInt(i);
            mRemote.transact(38, parcel, parcel1, 0);
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

        public ComponentName getHomeActivities(List list)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            ComponentName componentname;
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            mRemote.transact(88, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_73;
            componentname = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel1);
_L1:
            parcel1.readTypedList(list, ResolveInfo.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return componentname;
            componentname = null;
              goto _L1
            list;
            parcel1.recycle();
            parcel.recycle();
            throw list;
        }

        public int getInstallLocation()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            mRemote.transact(131, parcel, parcel1, 0);
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

        public int getInstallReason(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(176, parcel, parcel1, 0);
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

        public ParceledListSlice getInstalledApplications(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(53, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            ParceledListSlice parceledlistslice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return parceledlistslice;
_L2:
            parceledlistslice = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public ParceledListSlice getInstalledPackages(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(51, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            ParceledListSlice parceledlistslice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return parceledlistslice;
_L2:
            parceledlistslice = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getInstallerPackageName(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            mRemote.transact(66, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public String getInstantAppAndroidId(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(183, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public byte[] getInstantAppCookie(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(165, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.createByteArray();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public Bitmap getInstantAppIcon(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(167, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (Bitmap)Bitmap.CREATOR.createFromParcel(parcel1);
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

        public ComponentName getInstantAppInstallerComponent()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            mRemote.transact(182, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            ComponentName componentname = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return componentname;
_L2:
            componentname = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public ComponentName getInstantAppResolverComponent()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            mRemote.transact(180, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            ComponentName componentname = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return componentname;
_L2:
            componentname = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public ComponentName getInstantAppResolverSettingsComponent()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            mRemote.transact(181, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            ComponentName componentname = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return componentname;
_L2:
            componentname = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public ParceledListSlice getInstantApps(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeInt(i);
            mRemote.transact(164, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            ParceledListSlice parceledlistslice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return parceledlistslice;
_L2:
            parceledlistslice = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public InstrumentationInfo getInstrumentationInfo(ComponentName componentname, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L3:
            parcel.writeInt(i);
            mRemote.transact(58, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_109;
            componentname = (InstrumentationInfo)InstrumentationInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return componentname;
_L2:
            parcel.writeInt(0);
              goto _L3
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
            componentname = null;
              goto _L4
        }

        public byte[] getIntentFilterVerificationBackup(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            byte abyte0[];
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeInt(i);
            mRemote.transact(84, parcel, parcel1, 0);
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

        public ParceledListSlice getIntentFilterVerifications(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            mRemote.transact(138, parcel, parcel1, 0);
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

        public int getIntentVerificationStatus(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(136, parcel, parcel1, 0);
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
            return "android.content.pm.IPackageManager";
        }

        public KeySet getKeySetByAlias(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(154, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (KeySet)KeySet.CREATOR.createFromParcel(parcel1);
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

        public ResolveInfo getLastChosenActivity(Intent intent, String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            if(intent == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L3:
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(68, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_124;
            intent = (ResolveInfo)ResolveInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return intent;
_L2:
            parcel.writeInt(0);
              goto _L3
            intent;
            parcel1.recycle();
            parcel.recycle();
            throw intent;
            intent = null;
              goto _L4
        }

        public int getMoveStatus(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeInt(i);
            mRemote.transact(124, parcel, parcel1, 0);
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

        public String getNameForUid(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeInt(i);
            mRemote.transact(35, parcel, parcel1, 0);
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

        public String[] getNamesForUids(int ai[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeIntArray(ai);
            mRemote.transact(36, parcel, parcel1, 0);
            parcel1.readException();
            ai = parcel1.createStringArray();
            parcel1.recycle();
            parcel.recycle();
            return ai;
            ai;
            parcel1.recycle();
            parcel.recycle();
            throw ai;
        }

        public int[] getPackageGids(String s, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.createIntArray();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public PackageInfo getPackageInfo(String s, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (PackageInfo)PackageInfo.CREATOR.createFromParcel(parcel1);
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

        public PackageInfo getPackageInfoVersioned(VersionedPackage versionedpackage, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            if(versionedpackage == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            versionedpackage.writeToParcel(parcel, 0);
_L3:
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_123;
            versionedpackage = (PackageInfo)PackageInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return versionedpackage;
_L2:
            parcel.writeInt(0);
              goto _L3
            versionedpackage;
            parcel1.recycle();
            parcel.recycle();
            throw versionedpackage;
            versionedpackage = null;
              goto _L4
        }

        public IPackageInstaller getPackageInstaller()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            IPackageInstaller ipackageinstaller;
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            mRemote.transact(151, parcel, parcel1, 0);
            parcel1.readException();
            ipackageinstaller = IPackageInstaller.Stub.asInterface(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return ipackageinstaller;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void getPackageSizeInfo(String s, int i, IPackageStatsObserver ipackagestatsobserver)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            s = obj;
            if(ipackagestatsobserver == null)
                break MISSING_BLOCK_LABEL_46;
            s = ipackagestatsobserver.asBinder();
            parcel.writeStrongBinder(s);
            mRemote.transact(103, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int getPackageUid(String s, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(5, parcel, parcel1, 0);
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

        public String[] getPackagesForUid(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String as[];
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeInt(i);
            mRemote.transact(34, parcel, parcel1, 0);
            parcel1.readException();
            as = parcel1.createStringArray();
            parcel1.recycle();
            parcel.recycle();
            return as;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public ParceledListSlice getPackagesHoldingPermissions(String as[], int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeStringArray(as);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(52, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            as = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return as;
_L2:
            as = null;
            if(true) goto _L4; else goto _L3
_L3:
            as;
            parcel1.recycle();
            parcel.recycle();
            throw as;
        }

        public String getPermissionControllerPackageName()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            mRemote.transact(163, parcel, parcel1, 0);
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

        public int getPermissionFlags(String s, String s1, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(i);
            mRemote.transact(26, parcel, parcel1, 0);
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

        public byte[] getPermissionGrantBackup(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            byte abyte0[];
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeInt(i);
            mRemote.transact(86, parcel, parcel1, 0);
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

        public PermissionGroupInfo getPermissionGroupInfo(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (PermissionGroupInfo)PermissionGroupInfo.CREATOR.createFromParcel(parcel1);
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

        public PermissionInfo getPermissionInfo(String s, String s1, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(i);
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (PermissionInfo)PermissionInfo.CREATOR.createFromParcel(parcel1);
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

        public ParceledListSlice getPersistentApplications(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeInt(i);
            mRemote.transact(54, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            ParceledListSlice parceledlistslice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return parceledlistslice;
_L2:
            parceledlistslice = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getPreferredActivities(List list, List list1, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            mRemote.transact(73, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.readTypedList(list, IntentFilter.CREATOR);
            parcel1.readTypedList(list1, ComponentName.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return i;
            list;
            parcel1.recycle();
            parcel.recycle();
            throw list;
        }

        public byte[] getPreferredActivityBackup(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            byte abyte0[];
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeInt(i);
            mRemote.transact(80, parcel, parcel1, 0);
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

        public List getPreviousCodePaths(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            mRemote.transact(175, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.createStringArrayList();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int getPrivateFlagsForUid(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeInt(i);
            mRemote.transact(39, parcel, parcel1, 0);
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

        public ProviderInfo getProviderInfo(ComponentName componentname, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L3:
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(18, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_124;
            componentname = (ProviderInfo)ProviderInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return componentname;
_L2:
            parcel.writeInt(0);
              goto _L3
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
            componentname = null;
              goto _L4
        }

        public ActivityInfo getReceiverInfo(ComponentName componentname, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L3:
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(16, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_124;
            componentname = (ActivityInfo)ActivityInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return componentname;
_L2:
            parcel.writeInt(0);
              goto _L3
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
            componentname = null;
              goto _L4
        }

        public ServiceInfo getServiceInfo(ComponentName componentname, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L3:
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(17, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_124;
            componentname = (ServiceInfo)ServiceInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return componentname;
_L2:
            parcel.writeInt(0);
              goto _L3
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
            componentname = null;
              goto _L4
        }

        public String getServicesSystemSharedLibraryPackageName()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            mRemote.transact(171, parcel, parcel1, 0);
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

        public ParceledListSlice getSharedLibraries(String s, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(177, parcel, parcel1, 0);
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

        public String getSharedSystemSharedLibraryPackageName()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            mRemote.transact(172, parcel, parcel1, 0);
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

        public KeySet getSigningKeySet(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            mRemote.transact(155, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (KeySet)KeySet.CREATOR.createFromParcel(parcel1);
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

        public ParceledListSlice getSystemAvailableFeatures()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            mRemote.transact(105, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            ParceledListSlice parceledlistslice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return parceledlistslice;
_L2:
            parceledlistslice = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String[] getSystemSharedLibraryNames()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String as[];
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            mRemote.transact(104, parcel, parcel1, 0);
            parcel1.readException();
            as = parcel1.createStringArray();
            parcel1.recycle();
            parcel.recycle();
            return as;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getUidForSharedUser(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            mRemote.transact(37, parcel, parcel1, 0);
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

        public VerifierDeviceIdentity getVerifierDeviceIdentity()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            mRemote.transact(142, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            VerifierDeviceIdentity verifierdeviceidentity = (VerifierDeviceIdentity)VerifierDeviceIdentity.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return verifierdeviceidentity;
_L2:
            verifierdeviceidentity = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void grantDefaultPermissionsToEnabledCarrierApps(String as[], int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeStringArray(as);
            parcel.writeInt(i);
            mRemote.transact(160, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            as;
            parcel1.recycle();
            parcel.recycle();
            throw as;
        }

        public void grantDefaultPermissionsToEnabledImsServices(String as[], int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeStringArray(as);
            parcel.writeInt(i);
            mRemote.transact(161, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            as;
            parcel1.recycle();
            parcel.recycle();
            throw as;
        }

        public void grantRuntimePermission(String s, String s1, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(i);
            mRemote.transact(23, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean hasSystemFeature(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(106, parcel, parcel1, 0);
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

        public boolean hasSystemUidErrors()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            mRemote.transact(110, parcel, parcel1, 0);
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

        public int installExistingPackageAsUser(String s, int i, int j, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(132, parcel, parcel1, 0);
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

        public void installPackageAsUser(String s, IPackageInstallObserver2 ipackageinstallobserver2, int i, String s1, int j)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            s = obj;
            if(ipackageinstallobserver2 == null)
                break MISSING_BLOCK_LABEL_40;
            s = ipackageinstallobserver2.asBinder();
            parcel.writeStrongBinder(s);
            parcel.writeInt(i);
            parcel.writeString(s1);
            parcel.writeInt(j);
            mRemote.transact(60, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean isFirstBoot()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            mRemote.transact(143, parcel, parcel1, 0);
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

        public boolean isInstantApp(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(168, parcel, parcel1, 0);
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

        public boolean isOnlyCoreApps()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            mRemote.transact(144, parcel, parcel1, 0);
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

        public boolean isPackageAvailable(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeInt(i);
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

        public boolean isPackageDeviceAdminOnAnyUser(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            mRemote.transact(174, parcel, parcel1, 0);
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

        public boolean isPackageSignedByKeySet(String s, KeySet keyset)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            if(keyset == null)
                break MISSING_BLOCK_LABEL_84;
            parcel.writeInt(1);
            keyset.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(156, parcel, parcel1, 0);
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

        public boolean isPackageSignedByKeySetExactly(String s, KeySet keyset)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            if(keyset == null)
                break MISSING_BLOCK_LABEL_84;
            parcel.writeInt(1);
            keyset.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(157, parcel, parcel1, 0);
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

        public boolean isPackageSuspendedForUser(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(79, parcel, parcel1, 0);
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

        public boolean isPermissionEnforced(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            mRemote.transact(147, parcel, parcel1, 0);
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

        public boolean isPermissionRevokedByPolicy(String s, String s1, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(i);
            mRemote.transact(162, parcel, parcel1, 0);
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

        public boolean isProtectedBroadcast(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            mRemote.transact(30, parcel, parcel1, 0);
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

        public boolean isSafeMode()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            mRemote.transact(108, parcel, parcel1, 0);
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

        public boolean isStorageLow()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            mRemote.transact(148, parcel, parcel1, 0);
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

        public boolean isUidPrivileged(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeInt(i);
            mRemote.transact(40, parcel, parcel1, 0);
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

        public boolean isUpgrade()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            mRemote.transact(145, parcel, parcel1, 0);
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

        public void logAppProcessStartIfNeeded(String s, int i, String s1, String s2, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeInt(j);
            mRemote.transact(94, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int movePackage(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(127, parcel, parcel1, 0);
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

        public int movePrimaryStorage(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            mRemote.transact(128, parcel, parcel1, 0);
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

        public PackageCleanItem nextPackageToClean(PackageCleanItem packagecleanitem)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            if(packagecleanitem == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            packagecleanitem.writeToParcel(parcel, 0);
_L3:
            mRemote.transact(123, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_97;
            packagecleanitem = (PackageCleanItem)PackageCleanItem.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return packagecleanitem;
_L2:
            parcel.writeInt(0);
              goto _L3
            packagecleanitem;
            parcel1.recycle();
            parcel.recycle();
            throw packagecleanitem;
            packagecleanitem = null;
              goto _L4
        }

        public void notifyDexLoad(String s, List list, List list1, String s1)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeStringList(list);
            parcel.writeStringList(list1);
            parcel.writeString(s1);
            mRemote.transact(114, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void notifyPackageUse(String s, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(113, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public boolean performDexOptMode(String s, boolean flag, String s1, boolean flag1, boolean flag2, String s2)
            throws RemoteException
        {
            boolean flag3;
            Parcel parcel;
            Parcel parcel1;
            flag3 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeString(s1);
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
            parcel.writeString(s2);
            mRemote.transact(116, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
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

        public boolean performDexOptSecondary(String s, String s1, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(117, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
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

        public void performFstrimIfNeeded()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            mRemote.transact(111, parcel, parcel1, 0);
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

        public ParceledListSlice queryContentProviders(String s, int i, int j, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeString(s1);
            mRemote.transact(57, parcel, parcel1, 0);
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

        public ParceledListSlice queryInstrumentation(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(59, parcel, parcel1, 0);
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

        public ParceledListSlice queryIntentActivities(Intent intent, String s, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            if(intent == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L3:
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(45, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_131;
            intent = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return intent;
_L2:
            parcel.writeInt(0);
              goto _L3
            intent;
            parcel1.recycle();
            parcel.recycle();
            throw intent;
            intent = null;
              goto _L4
        }

        public ParceledListSlice queryIntentActivityOptions(ComponentName componentname, Intent aintent[], String as[], Intent intent, String s, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L5:
            parcel.writeTypedArray(aintent, 0);
            parcel.writeStringArray(as);
            if(intent == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L6:
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(46, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_174;
            componentname = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel1);
_L7:
            parcel1.recycle();
            parcel.recycle();
            return componentname;
_L2:
            parcel.writeInt(0);
              goto _L5
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
_L4:
            parcel.writeInt(0);
              goto _L6
            componentname = null;
              goto _L7
        }

        public ParceledListSlice queryIntentContentProviders(Intent intent, String s, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            if(intent == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L3:
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(50, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_131;
            intent = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return intent;
_L2:
            parcel.writeInt(0);
              goto _L3
            intent;
            parcel1.recycle();
            parcel.recycle();
            throw intent;
            intent = null;
              goto _L4
        }

        public ParceledListSlice queryIntentReceivers(Intent intent, String s, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            if(intent == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L3:
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(47, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_131;
            intent = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return intent;
_L2:
            parcel.writeInt(0);
              goto _L3
            intent;
            parcel1.recycle();
            parcel.recycle();
            throw intent;
            intent = null;
              goto _L4
        }

        public ParceledListSlice queryIntentServices(Intent intent, String s, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            if(intent == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L3:
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(49, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_131;
            intent = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return intent;
_L2:
            parcel.writeInt(0);
              goto _L3
            intent;
            parcel1.recycle();
            parcel.recycle();
            throw intent;
            intent = null;
              goto _L4
        }

        public ParceledListSlice queryPermissionsByGroup(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(10, parcel, parcel1, 0);
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

        public void querySyncProviders(List list, List list1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeStringList(list);
            parcel.writeTypedList(list1);
            mRemote.transact(56, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.readStringList(list);
            parcel1.readTypedList(list1, ProviderInfo.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return;
            list;
            parcel1.recycle();
            parcel.recycle();
            throw list;
        }

        public void reconcileSecondaryDexFiles(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            mRemote.transact(121, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void registerDexModule(String s, String s1, boolean flag, IDexModuleRegisterCallback idexmoduleregistercallback)
            throws RemoteException
        {
            int i;
            Object obj;
            Parcel parcel;
            i = 1;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            s = obj;
            if(idexmoduleregistercallback == null)
                break MISSING_BLOCK_LABEL_57;
            s = idexmoduleregistercallback.asBinder();
            parcel.writeStrongBinder(s);
            mRemote.transact(115, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void registerMoveCallback(IPackageMoveObserver ipackagemoveobserver)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            if(ipackagemoveobserver == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ipackagemoveobserver.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(125, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ipackagemoveobserver;
            parcel1.recycle();
            parcel.recycle();
            throw ipackagemoveobserver;
        }

        public void removeOnPermissionsChangeListener(IOnPermissionsChangeListener ionpermissionschangelistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            if(ionpermissionschangelistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ionpermissionschangelistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(159, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ionpermissionschangelistener;
            parcel1.recycle();
            parcel.recycle();
            throw ionpermissionschangelistener;
        }

        public void removePermission(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
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

        public void replacePreferredActivity(IntentFilter intentfilter, int i, ComponentName acomponentname[], ComponentName componentname, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            if(intentfilter == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            intentfilter.writeToParcel(parcel, 0);
_L3:
            parcel.writeInt(i);
            parcel.writeTypedArray(acomponentname, 0);
            if(componentname == null)
                break MISSING_BLOCK_LABEL_128;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L4:
            parcel.writeInt(j);
            mRemote.transact(71, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            intentfilter;
            parcel1.recycle();
            parcel.recycle();
            throw intentfilter;
            parcel.writeInt(0);
              goto _L4
        }

        public void resetApplicationPreferences(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeInt(i);
            mRemote.transact(67, parcel, parcel1, 0);
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

        public void resetRuntimePermissions()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            mRemote.transact(25, parcel, parcel1, 0);
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

        public ProviderInfo resolveContentProvider(String s, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(55, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (ProviderInfo)ProviderInfo.CREATOR.createFromParcel(parcel1);
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

        public ResolveInfo resolveIntent(Intent intent, String s, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            if(intent == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L3:
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(42, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_131;
            intent = (ResolveInfo)ResolveInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return intent;
_L2:
            parcel.writeInt(0);
              goto _L3
            intent;
            parcel1.recycle();
            parcel.recycle();
            throw intent;
            intent = null;
              goto _L4
        }

        public ResolveInfo resolveService(Intent intent, String s, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            if(intent == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L3:
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(48, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_131;
            intent = (ResolveInfo)ResolveInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return intent;
_L2:
            parcel.writeInt(0);
              goto _L3
            intent;
            parcel1.recycle();
            parcel.recycle();
            throw intent;
            intent = null;
              goto _L4
        }

        public void restoreDefaultApps(byte abyte0[], int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeByteArray(abyte0);
            parcel.writeInt(i);
            mRemote.transact(83, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            abyte0;
            parcel1.recycle();
            parcel.recycle();
            throw abyte0;
        }

        public void restoreIntentFilterVerification(byte abyte0[], int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeByteArray(abyte0);
            parcel.writeInt(i);
            mRemote.transact(85, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            abyte0;
            parcel1.recycle();
            parcel.recycle();
            throw abyte0;
        }

        public void restorePermissionGrants(byte abyte0[], int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeByteArray(abyte0);
            parcel.writeInt(i);
            mRemote.transact(87, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            abyte0;
            parcel1.recycle();
            parcel.recycle();
            throw abyte0;
        }

        public void restorePreferredActivities(byte abyte0[], int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeByteArray(abyte0);
            parcel.writeInt(i);
            mRemote.transact(81, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            abyte0;
            parcel1.recycle();
            parcel.recycle();
            throw abyte0;
        }

        public void revokeRuntimePermission(String s, String s1, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeString(s1);
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

        public void revokeRuntimePermissionNotKill(String s, String s1, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(i);
            mRemote.transact(184, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean runBackgroundDexoptJob()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            mRemote.transact(120, parcel, parcel1, 0);
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

        public void setApplicationCategoryHint(String s, int i, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeString(s1);
            mRemote.transact(63, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setApplicationEnabledSetting(String s, int i, int j, int k, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeString(s1);
            mRemote.transact(92, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean setApplicationHiddenSettingAsUser(String s, boolean flag, int i)
            throws RemoteException
        {
            int j;
            Parcel parcel;
            Parcel parcel1;
            j = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            if(flag)
                j = 1;
            parcel.writeInt(j);
            parcel.writeInt(i);
            mRemote.transact(149, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
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

        public boolean setBlockUninstallForUser(String s, boolean flag, int i)
            throws RemoteException
        {
            int j;
            Parcel parcel;
            Parcel parcel1;
            j = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            if(flag)
                j = 1;
            parcel.writeInt(j);
            parcel.writeInt(i);
            mRemote.transact(152, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
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

        public void setComponentEnabledSetting(ComponentName componentname, int i, int j, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_86;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(90, parcel, parcel1, 0);
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

        public boolean setDefaultBrowserPackageName(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(140, parcel, parcel1, 0);
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

        public void setHomeActivity(ComponentName componentname, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(89, parcel, parcel1, 0);
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

        public boolean setInstallLocation(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeInt(i);
            mRemote.transact(130, parcel, parcel1, 0);
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

        public void setInstallerPackageName(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(62, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean setInstantAppCookie(String s, byte abyte0[], int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeByteArray(abyte0);
            parcel.writeInt(i);
            mRemote.transact(166, parcel, parcel1, 0);
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

        public void setLastChosenActivity(Intent intent, String s, int i, IntentFilter intentfilter, int j, ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            if(intent == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L5:
            parcel.writeString(s);
            parcel.writeInt(i);
            if(intentfilter == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            intentfilter.writeToParcel(parcel, 0);
_L6:
            parcel.writeInt(j);
            if(componentname == null)
                break MISSING_BLOCK_LABEL_155;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L7:
            mRemote.transact(69, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L5
            intent;
            parcel1.recycle();
            parcel.recycle();
            throw intent;
_L4:
            parcel.writeInt(0);
              goto _L6
            parcel.writeInt(0);
              goto _L7
        }

        public void setPackageStoppedState(String s, boolean flag, int i)
            throws RemoteException
        {
            int j;
            Parcel parcel;
            Parcel parcel1;
            j = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            if(flag)
                j = 1;
            parcel.writeInt(j);
            parcel.writeInt(i);
            mRemote.transact(96, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public String[] setPackagesSuspendedAsUser(String as[], boolean flag, int i)
            throws RemoteException
        {
            int j;
            Parcel parcel;
            Parcel parcel1;
            j = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeStringArray(as);
            if(flag)
                j = 1;
            parcel.writeInt(j);
            parcel.writeInt(i);
            mRemote.transact(78, parcel, parcel1, 0);
            parcel1.readException();
            as = parcel1.createStringArray();
            parcel1.recycle();
            parcel.recycle();
            return as;
            as;
            parcel1.recycle();
            parcel.recycle();
            throw as;
        }

        public void setPermissionEnforced(String s, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(146, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean setRequiredForSystemUser(String s, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(169, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
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

        public void setUpdateAvailable(String s, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(170, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean shouldShowRequestPermissionRationale(String s, String s1, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(i);
            mRemote.transact(29, parcel, parcel1, 0);
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

        public void systemReady()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            mRemote.transact(109, parcel, parcel1, 0);
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

        public void unregisterMoveCallback(IPackageMoveObserver ipackagemoveobserver)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            if(ipackagemoveobserver == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ipackagemoveobserver.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(126, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ipackagemoveobserver;
            parcel1.recycle();
            parcel.recycle();
            throw ipackagemoveobserver;
        }

        public void updateExternalMediaStatus(boolean flag, boolean flag1)
            throws RemoteException
        {
            boolean flag2;
            Parcel parcel;
            Parcel parcel1;
            flag2 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
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
            mRemote.transact(122, parcel, parcel1, 0);
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

        public boolean updateIntentVerificationStatus(String s, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(137, parcel, parcel1, 0);
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

        public void updatePackagesIfNeeded()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            mRemote.transact(112, parcel, parcel1, 0);
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

        public void updatePermissionFlags(String s, String s1, int i, int j, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(27, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void updatePermissionFlagsForAllApps(int i, int j, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
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

        public void verifyIntentFilter(int i, int j, List list)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeStringList(list);
            mRemote.transact(135, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            list;
            parcel1.recycle();
            parcel.recycle();
            throw list;
        }

        public void verifyPendingInstall(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(133, parcel, parcel1, 0);
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


    public abstract boolean activitySupportsIntent(ComponentName componentname, Intent intent, String s)
        throws RemoteException;

    public abstract void addCrossProfileIntentFilter(IntentFilter intentfilter, String s, int i, int j, int k)
        throws RemoteException;

    public abstract void addOnPermissionsChangeListener(IOnPermissionsChangeListener ionpermissionschangelistener)
        throws RemoteException;

    public abstract boolean addPermission(PermissionInfo permissioninfo)
        throws RemoteException;

    public abstract boolean addPermissionAsync(PermissionInfo permissioninfo)
        throws RemoteException;

    public abstract void addPersistentPreferredActivity(IntentFilter intentfilter, ComponentName componentname, int i)
        throws RemoteException;

    public abstract void addPreferredActivity(IntentFilter intentfilter, int i, ComponentName acomponentname[], ComponentName componentname, int j)
        throws RemoteException;

    public abstract boolean canForwardTo(Intent intent, String s, int i, int j)
        throws RemoteException;

    public abstract boolean canRequestPackageInstalls(String s, int i)
        throws RemoteException;

    public abstract String[] canonicalToCurrentPackageNames(String as[])
        throws RemoteException;

    public abstract void checkPackageStartable(String s, int i)
        throws RemoteException;

    public abstract int checkPermission(String s, String s1, int i)
        throws RemoteException;

    public abstract int checkSignatures(String s, String s1)
        throws RemoteException;

    public abstract int checkUidPermission(String s, int i)
        throws RemoteException;

    public abstract int checkUidSignatures(int i, int j)
        throws RemoteException;

    public abstract void clearApplicationProfileData(String s)
        throws RemoteException;

    public abstract void clearApplicationUserData(String s, IPackageDataObserver ipackagedataobserver, int i)
        throws RemoteException;

    public abstract void clearCrossProfileIntentFilters(int i, String s)
        throws RemoteException;

    public abstract void clearPackagePersistentPreferredActivities(String s, int i)
        throws RemoteException;

    public abstract void clearPackagePreferredActivities(String s)
        throws RemoteException;

    public abstract String[] currentToCanonicalPackageNames(String as[])
        throws RemoteException;

    public abstract void deleteApplicationCacheFiles(String s, IPackageDataObserver ipackagedataobserver)
        throws RemoteException;

    public abstract void deleteApplicationCacheFilesAsUser(String s, int i, IPackageDataObserver ipackagedataobserver)
        throws RemoteException;

    public abstract void deletePackageAsUser(String s, int i, IPackageDeleteObserver ipackagedeleteobserver, int j, int k)
        throws RemoteException;

    public abstract void deletePackageVersioned(VersionedPackage versionedpackage, IPackageDeleteObserver2 ipackagedeleteobserver2, int i, int j)
        throws RemoteException;

    public abstract void deletePreloadsFileCache()
        throws RemoteException;

    public abstract void dumpProfiles(String s)
        throws RemoteException;

    public abstract void enterSafeMode()
        throws RemoteException;

    public abstract void extendVerificationTimeout(int i, int j, long l)
        throws RemoteException;

    public abstract ResolveInfo findPersistentPreferredActivity(Intent intent, int i)
        throws RemoteException;

    public abstract void finishPackageInstall(int i, boolean flag)
        throws RemoteException;

    public abstract void flushPackageRestrictionsAsUser(int i)
        throws RemoteException;

    public abstract void forceDexOpt(String s)
        throws RemoteException;

    public abstract void freeStorage(String s, long l, int i, IntentSender intentsender)
        throws RemoteException;

    public abstract void freeStorageAndNotify(String s, long l, int i, IPackageDataObserver ipackagedataobserver)
        throws RemoteException;

    public abstract ActivityInfo getActivityInfo(ComponentName componentname, int i, int j)
        throws RemoteException;

    public abstract ParceledListSlice getAllIntentFilters(String s)
        throws RemoteException;

    public abstract List getAllPackages()
        throws RemoteException;

    public abstract ParceledListSlice getAllPermissionGroups(int i)
        throws RemoteException;

    public abstract String[] getAppOpPermissionPackages(String s)
        throws RemoteException;

    public abstract int getApplicationEnabledSetting(String s, int i)
        throws RemoteException;

    public abstract boolean getApplicationHiddenSettingAsUser(String s, int i)
        throws RemoteException;

    public abstract ApplicationInfo getApplicationInfo(String s, int i, int j)
        throws RemoteException;

    public abstract boolean getBlockUninstallForUser(String s, int i)
        throws RemoteException;

    public abstract ChangedPackages getChangedPackages(int i, int j)
        throws RemoteException;

    public abstract int getComponentEnabledSetting(ComponentName componentname, int i)
        throws RemoteException;

    public abstract byte[] getDefaultAppsBackup(int i)
        throws RemoteException;

    public abstract String getDefaultBrowserPackageName(int i)
        throws RemoteException;

    public abstract int getFlagsForUid(int i)
        throws RemoteException;

    public abstract ComponentName getHomeActivities(List list)
        throws RemoteException;

    public abstract int getInstallLocation()
        throws RemoteException;

    public abstract int getInstallReason(String s, int i)
        throws RemoteException;

    public abstract ParceledListSlice getInstalledApplications(int i, int j)
        throws RemoteException;

    public abstract ParceledListSlice getInstalledPackages(int i, int j)
        throws RemoteException;

    public abstract String getInstallerPackageName(String s)
        throws RemoteException;

    public abstract String getInstantAppAndroidId(String s, int i)
        throws RemoteException;

    public abstract byte[] getInstantAppCookie(String s, int i)
        throws RemoteException;

    public abstract Bitmap getInstantAppIcon(String s, int i)
        throws RemoteException;

    public abstract ComponentName getInstantAppInstallerComponent()
        throws RemoteException;

    public abstract ComponentName getInstantAppResolverComponent()
        throws RemoteException;

    public abstract ComponentName getInstantAppResolverSettingsComponent()
        throws RemoteException;

    public abstract ParceledListSlice getInstantApps(int i)
        throws RemoteException;

    public abstract InstrumentationInfo getInstrumentationInfo(ComponentName componentname, int i)
        throws RemoteException;

    public abstract byte[] getIntentFilterVerificationBackup(int i)
        throws RemoteException;

    public abstract ParceledListSlice getIntentFilterVerifications(String s)
        throws RemoteException;

    public abstract int getIntentVerificationStatus(String s, int i)
        throws RemoteException;

    public abstract KeySet getKeySetByAlias(String s, String s1)
        throws RemoteException;

    public abstract ResolveInfo getLastChosenActivity(Intent intent, String s, int i)
        throws RemoteException;

    public abstract int getMoveStatus(int i)
        throws RemoteException;

    public abstract String getNameForUid(int i)
        throws RemoteException;

    public abstract String[] getNamesForUids(int ai[])
        throws RemoteException;

    public abstract int[] getPackageGids(String s, int i, int j)
        throws RemoteException;

    public abstract PackageInfo getPackageInfo(String s, int i, int j)
        throws RemoteException;

    public abstract PackageInfo getPackageInfoVersioned(VersionedPackage versionedpackage, int i, int j)
        throws RemoteException;

    public abstract IPackageInstaller getPackageInstaller()
        throws RemoteException;

    public abstract void getPackageSizeInfo(String s, int i, IPackageStatsObserver ipackagestatsobserver)
        throws RemoteException;

    public abstract int getPackageUid(String s, int i, int j)
        throws RemoteException;

    public abstract String[] getPackagesForUid(int i)
        throws RemoteException;

    public abstract ParceledListSlice getPackagesHoldingPermissions(String as[], int i, int j)
        throws RemoteException;

    public abstract String getPermissionControllerPackageName()
        throws RemoteException;

    public abstract int getPermissionFlags(String s, String s1, int i)
        throws RemoteException;

    public abstract byte[] getPermissionGrantBackup(int i)
        throws RemoteException;

    public abstract PermissionGroupInfo getPermissionGroupInfo(String s, int i)
        throws RemoteException;

    public abstract PermissionInfo getPermissionInfo(String s, String s1, int i)
        throws RemoteException;

    public abstract ParceledListSlice getPersistentApplications(int i)
        throws RemoteException;

    public abstract int getPreferredActivities(List list, List list1, String s)
        throws RemoteException;

    public abstract byte[] getPreferredActivityBackup(int i)
        throws RemoteException;

    public abstract List getPreviousCodePaths(String s)
        throws RemoteException;

    public abstract int getPrivateFlagsForUid(int i)
        throws RemoteException;

    public abstract ProviderInfo getProviderInfo(ComponentName componentname, int i, int j)
        throws RemoteException;

    public abstract ActivityInfo getReceiverInfo(ComponentName componentname, int i, int j)
        throws RemoteException;

    public abstract ServiceInfo getServiceInfo(ComponentName componentname, int i, int j)
        throws RemoteException;

    public abstract String getServicesSystemSharedLibraryPackageName()
        throws RemoteException;

    public abstract ParceledListSlice getSharedLibraries(String s, int i, int j)
        throws RemoteException;

    public abstract String getSharedSystemSharedLibraryPackageName()
        throws RemoteException;

    public abstract KeySet getSigningKeySet(String s)
        throws RemoteException;

    public abstract ParceledListSlice getSystemAvailableFeatures()
        throws RemoteException;

    public abstract String[] getSystemSharedLibraryNames()
        throws RemoteException;

    public abstract int getUidForSharedUser(String s)
        throws RemoteException;

    public abstract VerifierDeviceIdentity getVerifierDeviceIdentity()
        throws RemoteException;

    public abstract void grantDefaultPermissionsToEnabledCarrierApps(String as[], int i)
        throws RemoteException;

    public abstract void grantDefaultPermissionsToEnabledImsServices(String as[], int i)
        throws RemoteException;

    public abstract void grantRuntimePermission(String s, String s1, int i)
        throws RemoteException;

    public abstract boolean hasSystemFeature(String s, int i)
        throws RemoteException;

    public abstract boolean hasSystemUidErrors()
        throws RemoteException;

    public abstract int installExistingPackageAsUser(String s, int i, int j, int k)
        throws RemoteException;

    public abstract void installPackageAsUser(String s, IPackageInstallObserver2 ipackageinstallobserver2, int i, String s1, int j)
        throws RemoteException;

    public abstract boolean isFirstBoot()
        throws RemoteException;

    public abstract boolean isInstantApp(String s, int i)
        throws RemoteException;

    public abstract boolean isOnlyCoreApps()
        throws RemoteException;

    public abstract boolean isPackageAvailable(String s, int i)
        throws RemoteException;

    public abstract boolean isPackageDeviceAdminOnAnyUser(String s)
        throws RemoteException;

    public abstract boolean isPackageSignedByKeySet(String s, KeySet keyset)
        throws RemoteException;

    public abstract boolean isPackageSignedByKeySetExactly(String s, KeySet keyset)
        throws RemoteException;

    public abstract boolean isPackageSuspendedForUser(String s, int i)
        throws RemoteException;

    public abstract boolean isPermissionEnforced(String s)
        throws RemoteException;

    public abstract boolean isPermissionRevokedByPolicy(String s, String s1, int i)
        throws RemoteException;

    public abstract boolean isProtectedBroadcast(String s)
        throws RemoteException;

    public abstract boolean isSafeMode()
        throws RemoteException;

    public abstract boolean isStorageLow()
        throws RemoteException;

    public abstract boolean isUidPrivileged(int i)
        throws RemoteException;

    public abstract boolean isUpgrade()
        throws RemoteException;

    public abstract void logAppProcessStartIfNeeded(String s, int i, String s1, String s2, int j)
        throws RemoteException;

    public abstract int movePackage(String s, String s1)
        throws RemoteException;

    public abstract int movePrimaryStorage(String s)
        throws RemoteException;

    public abstract PackageCleanItem nextPackageToClean(PackageCleanItem packagecleanitem)
        throws RemoteException;

    public abstract void notifyDexLoad(String s, List list, List list1, String s1)
        throws RemoteException;

    public abstract void notifyPackageUse(String s, int i)
        throws RemoteException;

    public abstract boolean performDexOptMode(String s, boolean flag, String s1, boolean flag1, boolean flag2, String s2)
        throws RemoteException;

    public abstract boolean performDexOptSecondary(String s, String s1, boolean flag)
        throws RemoteException;

    public abstract void performFstrimIfNeeded()
        throws RemoteException;

    public abstract ParceledListSlice queryContentProviders(String s, int i, int j, String s1)
        throws RemoteException;

    public abstract ParceledListSlice queryInstrumentation(String s, int i)
        throws RemoteException;

    public abstract ParceledListSlice queryIntentActivities(Intent intent, String s, int i, int j)
        throws RemoteException;

    public abstract ParceledListSlice queryIntentActivityOptions(ComponentName componentname, Intent aintent[], String as[], Intent intent, String s, int i, int j)
        throws RemoteException;

    public abstract ParceledListSlice queryIntentContentProviders(Intent intent, String s, int i, int j)
        throws RemoteException;

    public abstract ParceledListSlice queryIntentReceivers(Intent intent, String s, int i, int j)
        throws RemoteException;

    public abstract ParceledListSlice queryIntentServices(Intent intent, String s, int i, int j)
        throws RemoteException;

    public abstract ParceledListSlice queryPermissionsByGroup(String s, int i)
        throws RemoteException;

    public abstract void querySyncProviders(List list, List list1)
        throws RemoteException;

    public abstract void reconcileSecondaryDexFiles(String s)
        throws RemoteException;

    public abstract void registerDexModule(String s, String s1, boolean flag, IDexModuleRegisterCallback idexmoduleregistercallback)
        throws RemoteException;

    public abstract void registerMoveCallback(IPackageMoveObserver ipackagemoveobserver)
        throws RemoteException;

    public abstract void removeOnPermissionsChangeListener(IOnPermissionsChangeListener ionpermissionschangelistener)
        throws RemoteException;

    public abstract void removePermission(String s)
        throws RemoteException;

    public abstract void replacePreferredActivity(IntentFilter intentfilter, int i, ComponentName acomponentname[], ComponentName componentname, int j)
        throws RemoteException;

    public abstract void resetApplicationPreferences(int i)
        throws RemoteException;

    public abstract void resetRuntimePermissions()
        throws RemoteException;

    public abstract ProviderInfo resolveContentProvider(String s, int i, int j)
        throws RemoteException;

    public abstract ResolveInfo resolveIntent(Intent intent, String s, int i, int j)
        throws RemoteException;

    public abstract ResolveInfo resolveService(Intent intent, String s, int i, int j)
        throws RemoteException;

    public abstract void restoreDefaultApps(byte abyte0[], int i)
        throws RemoteException;

    public abstract void restoreIntentFilterVerification(byte abyte0[], int i)
        throws RemoteException;

    public abstract void restorePermissionGrants(byte abyte0[], int i)
        throws RemoteException;

    public abstract void restorePreferredActivities(byte abyte0[], int i)
        throws RemoteException;

    public abstract void revokeRuntimePermission(String s, String s1, int i)
        throws RemoteException;

    public abstract void revokeRuntimePermissionNotKill(String s, String s1, int i)
        throws RemoteException;

    public abstract boolean runBackgroundDexoptJob()
        throws RemoteException;

    public abstract void setApplicationCategoryHint(String s, int i, String s1)
        throws RemoteException;

    public abstract void setApplicationEnabledSetting(String s, int i, int j, int k, String s1)
        throws RemoteException;

    public abstract boolean setApplicationHiddenSettingAsUser(String s, boolean flag, int i)
        throws RemoteException;

    public abstract boolean setBlockUninstallForUser(String s, boolean flag, int i)
        throws RemoteException;

    public abstract void setComponentEnabledSetting(ComponentName componentname, int i, int j, int k)
        throws RemoteException;

    public abstract boolean setDefaultBrowserPackageName(String s, int i)
        throws RemoteException;

    public abstract void setHomeActivity(ComponentName componentname, int i)
        throws RemoteException;

    public abstract boolean setInstallLocation(int i)
        throws RemoteException;

    public abstract void setInstallerPackageName(String s, String s1)
        throws RemoteException;

    public abstract boolean setInstantAppCookie(String s, byte abyte0[], int i)
        throws RemoteException;

    public abstract void setLastChosenActivity(Intent intent, String s, int i, IntentFilter intentfilter, int j, ComponentName componentname)
        throws RemoteException;

    public abstract void setPackageStoppedState(String s, boolean flag, int i)
        throws RemoteException;

    public abstract String[] setPackagesSuspendedAsUser(String as[], boolean flag, int i)
        throws RemoteException;

    public abstract void setPermissionEnforced(String s, boolean flag)
        throws RemoteException;

    public abstract boolean setRequiredForSystemUser(String s, boolean flag)
        throws RemoteException;

    public abstract void setUpdateAvailable(String s, boolean flag)
        throws RemoteException;

    public abstract boolean shouldShowRequestPermissionRationale(String s, String s1, int i)
        throws RemoteException;

    public abstract void systemReady()
        throws RemoteException;

    public abstract void unregisterMoveCallback(IPackageMoveObserver ipackagemoveobserver)
        throws RemoteException;

    public abstract void updateExternalMediaStatus(boolean flag, boolean flag1)
        throws RemoteException;

    public abstract boolean updateIntentVerificationStatus(String s, int i, int j)
        throws RemoteException;

    public abstract void updatePackagesIfNeeded()
        throws RemoteException;

    public abstract void updatePermissionFlags(String s, String s1, int i, int j, int k)
        throws RemoteException;

    public abstract void updatePermissionFlagsForAllApps(int i, int j, int k)
        throws RemoteException;

    public abstract void verifyIntentFilter(int i, int j, List list)
        throws RemoteException;

    public abstract void verifyPendingInstall(int i, int j)
        throws RemoteException;
}
