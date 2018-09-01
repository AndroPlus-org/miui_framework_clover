// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.IntentFilter;
import android.content.pm.split.DefaultSplitAssetLoader;
import android.content.pm.split.SplitAssetDependencyLoader;
import android.content.pm.split.SplitAssetLoader;
import android.content.res.*;
import android.os.*;
import android.os.storage.StorageManager;
import android.system.*;
import android.text.TextUtils;
import android.util.*;
import android.util.apk.ApkSignatureSchemeV2Verifier;
import android.util.jar.StrictJarFile;
import com.android.internal.os.ClassLoaderFactory;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.XmlUtils;
import java.io.*;
import java.lang.reflect.Constructor;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.zip.ZipEntry;
import libcore.io.IoUtils;
import libcore.util.EmptyArray;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.content.pm:
//            ActivityInfo, PackageUserState, ApplicationInfo, Signature, 
//            InstrumentationInfo, PackageInfo, ConfigurationInfo, FeatureInfo, 
//            FeatureGroupInfo, ServiceInfo, ProviderInfo, PermissionInfo, 
//            PermissionGroupInfo, PackageBackwardCompatibility, PackageItemInfo, PathPermission, 
//            ComponentInfo, VerifierInfo, FallbackCategoryProvider, SELinuxUtil, 
//            PackageManager

public class PackageParser
{
    class _cls1VerificationData
    {

        public Exception exception;
        public int exceptionFlag;
        public int index;
        public Object objWaitAll;
        public boolean shutDown;
        public boolean wait;

        _cls1VerificationData()
        {
        }
    }

    public static final class Activity extends Component
        implements Parcelable
    {

        static boolean _2D_wrap0(Activity activity)
        {
            return activity.hasMaxAspectRatio();
        }

        static void _2D_wrap1(Activity activity, float f)
        {
            activity.setMaxAspectRatio(f);
        }

        private boolean hasMaxAspectRatio()
        {
            return mHasMaxAspectRatio;
        }

        private void setMaxAspectRatio(float f)
        {
            if(info.resizeMode == 2 || info.resizeMode == 1)
                return;
            if(f < 1.0F && f != 0.0F)
            {
                return;
            } else
            {
                info.maxAspectRatio = f;
                mHasMaxAspectRatio = true;
                return;
            }
        }

        public int describeContents()
        {
            return 0;
        }

        public void setPackageName(String s)
        {
            super.setPackageName(s);
            info.packageName = s;
        }

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder(128);
            stringbuilder.append("Activity{");
            stringbuilder.append(Integer.toHexString(System.identityHashCode(this)));
            stringbuilder.append(' ');
            appendComponentShortName(stringbuilder);
            stringbuilder.append('}');
            return stringbuilder.toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeParcelable(info, i | 2);
            parcel.writeBoolean(mHasMaxAspectRatio);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public Activity createFromParcel(Parcel parcel)
            {
                return new Activity(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public Activity[] newArray(int i)
            {
                return new Activity[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public final ActivityInfo info;
        private boolean mHasMaxAspectRatio;


        public Activity(ParseComponentArgs parsecomponentargs, ActivityInfo activityinfo)
        {
            super(parsecomponentargs, activityinfo);
            info = activityinfo;
            info.applicationInfo = parsecomponentargs.owner.applicationInfo;
        }

        private Activity(Parcel parcel)
        {
            super(parcel);
            info = (ActivityInfo)parcel.readParcelable(java/lang/Object.getClassLoader());
            mHasMaxAspectRatio = parcel.readBoolean();
            for(parcel = intents.iterator(); parcel.hasNext();)
                ((ActivityIntentInfo)parcel.next()).activity = this;

            if(info.permission != null)
                info.permission = info.permission.intern();
        }

        Activity(Parcel parcel, Activity activity)
        {
            this(parcel);
        }
    }

    public static final class ActivityIntentInfo extends IntentInfo
    {

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder(128);
            stringbuilder.append("ActivityIntentInfo{");
            stringbuilder.append(Integer.toHexString(System.identityHashCode(this)));
            stringbuilder.append(' ');
            activity.appendComponentShortName(stringbuilder);
            stringbuilder.append('}');
            return stringbuilder.toString();
        }

        public Activity activity;

        public ActivityIntentInfo(Activity activity1)
        {
            activity = activity1;
        }

        public ActivityIntentInfo(Parcel parcel)
        {
            super(parcel);
        }
    }

    public static class ApkLite
    {

        public final Certificate certificates[][];
        public final String codePath;
        public final String configForSplit;
        public final boolean coreApp;
        public final boolean debuggable;
        public final boolean extractNativeLibs;
        public final int installLocation;
        public boolean isFeatureSplit;
        public final boolean isolatedSplits;
        public final boolean multiArch;
        public final String packageName;
        public final int revisionCode;
        public final Signature signatures[];
        public final String splitName;
        public final boolean use32bitAbi;
        public final String usesSplitName;
        public final VerifierInfo verifiers[];
        public final int versionCode;

        public ApkLite(String s, String s1, String s2, boolean flag, String s3, String s4, int i, 
                int j, int k, List list, Signature asignature[], Certificate acertificate[][], boolean flag1, boolean flag2, 
                boolean flag3, boolean flag4, boolean flag5, boolean flag6)
        {
            codePath = s;
            packageName = s1;
            splitName = s2;
            isFeatureSplit = flag;
            configForSplit = s3;
            usesSplitName = s4;
            versionCode = i;
            revisionCode = j;
            installLocation = k;
            verifiers = (VerifierInfo[])list.toArray(new VerifierInfo[list.size()]);
            signatures = asignature;
            certificates = acertificate;
            coreApp = flag1;
            debuggable = flag2;
            multiArch = flag3;
            use32bitAbi = flag4;
            extractNativeLibs = flag5;
            isolatedSplits = flag6;
        }
    }

    private static class CachedComponentArgs
    {

        ParseComponentArgs mActivityAliasArgs;
        ParseComponentArgs mActivityArgs;
        ParseComponentArgs mProviderArgs;
        ParseComponentArgs mServiceArgs;

        private CachedComponentArgs()
        {
        }

        CachedComponentArgs(CachedComponentArgs cachedcomponentargs)
        {
            this();
        }
    }

    public static interface Callback
    {

        public abstract String[] getOverlayApks(String s);

        public abstract String[] getOverlayPaths(String s, String s1);

        public abstract boolean hasFeature(String s);
    }

    public static final class CallbackImpl
        implements Callback
    {

        public String[] getOverlayApks(String s)
        {
            return null;
        }

        public String[] getOverlayPaths(String s, String s1)
        {
            return null;
        }

        public boolean hasFeature(String s)
        {
            return mPm.hasSystemFeature(s);
        }

        private final PackageManager mPm;

        public CallbackImpl(PackageManager packagemanager)
        {
            mPm = packagemanager;
        }
    }

    public static abstract class Component
    {

        private static ArrayList createIntentsList(Parcel parcel)
        {
            ArrayList arraylist;
            int i = parcel.readInt();
            if(i == -1)
                return null;
            if(i == 0)
                return new ArrayList(0);
            String s = parcel.readString();
            Constructor constructor;
            int j;
            try
            {
                constructor = Class.forName(s).getConstructor(new Class[] {
                    android/os/Parcel
                });
                arraylist = JVM INSTR new #121 <Class ArrayList>;
                arraylist.ArrayList(i);
            }
            // Misplaced declaration of an exception variable
            catch(Parcel parcel)
            {
                throw new AssertionError((new StringBuilder()).append("Unable to construct intent list for: ").append(s).toString());
            }
            j = 0;
            if(j >= i)
                break; /* Loop/switch isn't completed */
            arraylist.add((IntentInfo)constructor.newInstance(new Object[] {
                parcel
            }));
            j++;
            if(true) goto _L2; else goto _L1
_L2:
            break MISSING_BLOCK_LABEL_61;
_L1:
            return arraylist;
        }

        private static void writeIntentsList(ArrayList arraylist, Parcel parcel, int i)
        {
            if(arraylist == null)
            {
                parcel.writeInt(-1);
                return;
            }
            int j = arraylist.size();
            parcel.writeInt(j);
            if(j > 0)
            {
                parcel.writeString(((IntentInfo)arraylist.get(0)).getClass().getName());
                for(int k = 0; k < j; k++)
                    ((IntentInfo)arraylist.get(k)).writeIntentInfoToParcel(parcel, i);

            }
        }

        public void appendComponentShortName(StringBuilder stringbuilder)
        {
            ComponentName.appendShortString(stringbuilder, owner.applicationInfo.packageName, className);
        }

        public ComponentName getComponentName()
        {
            if(componentName != null)
                return componentName;
            if(className != null)
                componentName = new ComponentName(owner.applicationInfo.packageName, className);
            return componentName;
        }

        public void printComponentShortName(PrintWriter printwriter)
        {
            ComponentName.printShortString(printwriter, owner.applicationInfo.packageName, className);
        }

        public void setPackageName(String s)
        {
            componentName = null;
            componentShortName = null;
        }

        protected void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeString(className);
            parcel.writeBundle(metaData);
            writeIntentsList(intents, parcel, i);
        }

        public final String className;
        ComponentName componentName;
        String componentShortName;
        public final ArrayList intents;
        public Bundle metaData;
        public Package owner;

        public Component(Component component)
        {
            owner = component.owner;
            intents = component.intents;
            className = component.className;
            componentName = component.componentName;
            componentShortName = component.componentShortName;
        }

        public Component(Package package1)
        {
            owner = package1;
            intents = null;
            className = null;
        }

        public Component(ParseComponentArgs parsecomponentargs, ComponentInfo componentinfo)
        {
            this(((ParsePackageItemArgs) (parsecomponentargs)), ((PackageItemInfo) (componentinfo)));
            if(parsecomponentargs.outError[0] != null)
                return;
            if(parsecomponentargs.processRes != 0)
            {
                String s;
                if(owner.applicationInfo.targetSdkVersion >= 8)
                    s = parsecomponentargs.sa.getNonConfigurationString(parsecomponentargs.processRes, 1024);
                else
                    s = parsecomponentargs.sa.getNonResourceString(parsecomponentargs.processRes);
                componentinfo.processName = PackageParser._2D_wrap2(owner.applicationInfo.packageName, owner.applicationInfo.processName, s, parsecomponentargs.flags, parsecomponentargs.sepProcesses, parsecomponentargs.outError);
            }
            if(parsecomponentargs.descriptionRes != 0)
                componentinfo.descriptionRes = parsecomponentargs.sa.getResourceId(parsecomponentargs.descriptionRes, 0);
            componentinfo.enabled = parsecomponentargs.sa.getBoolean(parsecomponentargs.enabledRes, true);
        }

        public Component(ParsePackageItemArgs parsepackageitemargs, PackageItemInfo packageiteminfo)
        {
            owner = parsepackageitemargs.owner;
            intents = new ArrayList(0);
            if(PackageParser._2D_wrap1(parsepackageitemargs.owner, packageiteminfo, parsepackageitemargs.outError, parsepackageitemargs.tag, parsepackageitemargs.sa, true, parsepackageitemargs.nameRes, parsepackageitemargs.labelRes, parsepackageitemargs.iconRes, parsepackageitemargs.roundIconRes, parsepackageitemargs.logoRes, parsepackageitemargs.bannerRes))
                className = packageiteminfo.name;
            else
                className = null;
        }

        protected Component(Parcel parcel)
        {
            className = parcel.readString();
            metaData = parcel.readBundle();
            intents = createIntentsList(parcel);
            owner = null;
        }
    }

    public static final class Instrumentation extends Component
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public void setPackageName(String s)
        {
            super.setPackageName(s);
            info.packageName = s;
        }

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder(128);
            stringbuilder.append("Instrumentation{");
            stringbuilder.append(Integer.toHexString(System.identityHashCode(this)));
            stringbuilder.append(' ');
            appendComponentShortName(stringbuilder);
            stringbuilder.append('}');
            return stringbuilder.toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeParcelable(info, i);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public Instrumentation createFromParcel(Parcel parcel)
            {
                return new Instrumentation(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public Instrumentation[] newArray(int i)
            {
                return new Instrumentation[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public final InstrumentationInfo info;


        public Instrumentation(ParsePackageItemArgs parsepackageitemargs, InstrumentationInfo instrumentationinfo)
        {
            super(parsepackageitemargs, instrumentationinfo);
            info = instrumentationinfo;
        }

        private Instrumentation(Parcel parcel)
        {
            super(parcel);
            info = (InstrumentationInfo)parcel.readParcelable(java/lang/Object.getClassLoader());
            if(info.targetPackage != null)
                info.targetPackage = info.targetPackage.intern();
            if(info.targetProcesses != null)
                info.targetProcesses = info.targetProcesses.intern();
        }

        Instrumentation(Parcel parcel, Instrumentation instrumentation)
        {
            this(parcel);
        }
    }

    public static abstract class IntentInfo extends IntentFilter
    {

        public void writeIntentInfoToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            if(hasDefault)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeInt(labelRes);
            parcel.writeCharSequence(nonLocalizedLabel);
            parcel.writeInt(icon);
            parcel.writeInt(logo);
            parcel.writeInt(banner);
            parcel.writeInt(preferred);
        }

        public int banner;
        public boolean hasDefault;
        public int icon;
        public int labelRes;
        public int logo;
        public CharSequence nonLocalizedLabel;
        public int preferred;

        protected IntentInfo()
        {
        }

        protected IntentInfo(Parcel parcel)
        {
            boolean flag = true;
            super(parcel);
            if(parcel.readInt() != 1)
                flag = false;
            hasDefault = flag;
            labelRes = parcel.readInt();
            nonLocalizedLabel = parcel.readCharSequence();
            icon = parcel.readInt();
            logo = parcel.readInt();
            banner = parcel.readInt();
            preferred = parcel.readInt();
        }
    }

    public static class NewPermissionInfo
    {

        public final int fileVersion;
        public final String name;
        public final int sdkVersion;

        public NewPermissionInfo(String s, int i, int j)
        {
            name = s;
            sdkVersion = i;
            fileVersion = j;
        }
    }

    public static final class Package
        implements Parcelable
    {

        private void fixupOwner(List list)
        {
            if(list != null)
            {
                list = list.iterator();
                do
                {
                    if(!list.hasNext())
                        break;
                    Component component = (Component)list.next();
                    component.owner = this;
                    if(component instanceof Activity)
                        ((Activity)component).info.applicationInfo = applicationInfo;
                    else
                    if(component instanceof Service)
                        ((Service)component).info.applicationInfo = applicationInfo;
                    else
                    if(component instanceof Provider)
                        ((Provider)component).info.applicationInfo = applicationInfo;
                } while(true);
            }
        }

        private static void internStringArrayList(List list)
        {
            if(list != null)
            {
                int i = list.size();
                for(int j = 0; j < i; j++)
                    list.set(j, ((String)list.get(j)).intern());

            }
        }

        private static ArrayMap readKeySetMapping(Parcel parcel)
        {
            int i = parcel.readInt();
            if(i == -1)
                return null;
            ArrayMap arraymap = new ArrayMap();
            int j = 0;
            while(j < i) 
            {
                String s = parcel.readString();
                int k = parcel.readInt();
                if(k == -1)
                {
                    arraymap.put(s, null);
                } else
                {
                    ArraySet arrayset = new ArraySet(k);
                    for(int l = 0; l < k; l++)
                        arrayset.add((PublicKey)parcel.readSerializable());

                    arraymap.put(s, arrayset);
                }
                j++;
            }
            return arraymap;
        }

        private static void writeKeySetMapping(Parcel parcel, ArrayMap arraymap)
        {
            if(arraymap == null)
            {
                parcel.writeInt(-1);
                return;
            }
            int i = arraymap.size();
            parcel.writeInt(i);
            int j = 0;
            while(j < i) 
            {
                parcel.writeString((String)arraymap.keyAt(j));
                ArraySet arrayset = (ArraySet)arraymap.valueAt(j);
                if(arrayset == null)
                {
                    parcel.writeInt(-1);
                } else
                {
                    int k = arrayset.size();
                    parcel.writeInt(k);
                    int l = 0;
                    while(l < k) 
                    {
                        parcel.writeSerializable((Serializable)arrayset.valueAt(l));
                        l++;
                    }
                }
                j++;
            }
        }

        public boolean canHaveOatDir()
        {
            boolean flag;
            if((!isSystemApp() || isUpdatedSystemApp()) && isForwardLocked() ^ true)
                flag = applicationInfo.isExternalAsec() ^ true;
            else
                flag = false;
            return flag;
        }

        public int describeContents()
        {
            return 0;
        }

        public List getAllCodePaths()
        {
            ArrayList arraylist = new ArrayList();
            arraylist.add(baseCodePath);
            if(!ArrayUtils.isEmpty(splitCodePaths))
                Collections.addAll(arraylist, splitCodePaths);
            return arraylist;
        }

        public List getAllCodePathsExcludingResourceOnly()
        {
            ArrayList arraylist = new ArrayList();
            if((applicationInfo.flags & 4) != 0)
                arraylist.add(baseCodePath);
            if(!ArrayUtils.isEmpty(splitCodePaths))
            {
                for(int i = 0; i < splitCodePaths.length; i++)
                    if((splitFlags[i] & 4) != 0)
                        arraylist.add(splitCodePaths[i]);

            }
            return arraylist;
        }

        public List getChildPackageNames()
        {
            if(childPackages == null)
                return null;
            int i = childPackages.size();
            ArrayList arraylist = new ArrayList(i);
            for(int j = 0; j < i; j++)
                arraylist.add(((Package)childPackages.get(j)).packageName);

            return arraylist;
        }

        public long getLatestForegroundPackageUseTimeInMills()
        {
            int i = 0;
            int ai[] = new int[2];
            ai[0] = 0;
            ai[1] = 2;
            long l = 0L;
            for(int j = ai.length; i < j; i++)
            {
                int k = ai[i];
                l = Math.max(l, mLastPackageUsageTimeInMills[k]);
            }

            return l;
        }

        public long getLatestPackageUseTimeInMills()
        {
            long l = 0L;
            long al[] = mLastPackageUsageTimeInMills;
            int i = 0;
            for(int j = al.length; i < j; i++)
                l = Math.max(l, al[i]);

            return l;
        }

        public boolean hasChildPackage(String s)
        {
            int i;
            int j;
            if(childPackages != null)
                i = childPackages.size();
            else
                i = 0;
            for(j = 0; j < i; j++)
                if(((Package)childPackages.get(j)).packageName.equals(s))
                    return true;

            return false;
        }

        public boolean hasComponentClassName(String s)
        {
            for(int i = activities.size() - 1; i >= 0; i--)
                if(s.equals(((Activity)activities.get(i)).className))
                    return true;

            for(int j = receivers.size() - 1; j >= 0; j--)
                if(s.equals(((Activity)receivers.get(j)).className))
                    return true;

            for(int k = providers.size() - 1; k >= 0; k--)
                if(s.equals(((Provider)providers.get(k)).className))
                    return true;

            for(int l = services.size() - 1; l >= 0; l--)
                if(s.equals(((Service)services.get(l)).className))
                    return true;

            for(int i1 = instrumentation.size() - 1; i1 >= 0; i1--)
                if(s.equals(((Instrumentation)instrumentation.get(i1)).className))
                    return true;

            return false;
        }

        public boolean isForwardLocked()
        {
            return applicationInfo.isForwardLocked();
        }

        public boolean isLibrary()
        {
            boolean flag;
            if(staticSharedLibName == null)
                flag = ArrayUtils.isEmpty(libraryNames) ^ true;
            else
                flag = true;
            return flag;
        }

        public boolean isMatch(int i)
        {
            if((0x100000 & i) != 0)
                return isSystemApp();
            else
                return true;
        }

        public boolean isPrivilegedApp()
        {
            return applicationInfo.isPrivilegedApp();
        }

        public boolean isSystemApp()
        {
            return applicationInfo.isSystemApp();
        }

        public boolean isUpdatedSystemApp()
        {
            return applicationInfo.isUpdatedSystemApp();
        }

        public void setApplicationInfoBaseCodePath(String s)
        {
            applicationInfo.setBaseCodePath(s);
            if(childPackages != null)
            {
                int i = childPackages.size();
                for(int j = 0; j < i; j++)
                    ((Package)childPackages.get(j)).applicationInfo.setBaseCodePath(s);

            }
        }

        public void setApplicationInfoBaseResourcePath(String s)
        {
            applicationInfo.setBaseResourcePath(s);
            if(childPackages != null)
            {
                int i = childPackages.size();
                for(int j = 0; j < i; j++)
                    ((Package)childPackages.get(j)).applicationInfo.setBaseResourcePath(s);

            }
        }

        public void setApplicationInfoCodePath(String s)
        {
            applicationInfo.setCodePath(s);
            if(childPackages != null)
            {
                int i = childPackages.size();
                for(int j = 0; j < i; j++)
                    ((Package)childPackages.get(j)).applicationInfo.setCodePath(s);

            }
        }

        public void setApplicationInfoFlags(int i, int j)
        {
            applicationInfo.flags = applicationInfo.flags & i | i & j;
            if(childPackages != null)
            {
                int k = childPackages.size();
                for(int l = 0; l < k; l++)
                    ((Package)childPackages.get(l)).applicationInfo.flags = applicationInfo.flags & i | i & j;

            }
        }

        public void setApplicationInfoResourcePath(String s)
        {
            applicationInfo.setResourcePath(s);
            if(childPackages != null)
            {
                int i = childPackages.size();
                for(int j = 0; j < i; j++)
                    ((Package)childPackages.get(j)).applicationInfo.setResourcePath(s);

            }
        }

        public void setApplicationInfoSplitCodePaths(String as[])
        {
            applicationInfo.setSplitCodePaths(as);
        }

        public void setApplicationInfoSplitResourcePaths(String as[])
        {
            applicationInfo.setSplitResourcePaths(as);
        }

        public void setApplicationVolumeUuid(String s)
        {
            java.util.UUID uuid = StorageManager.convert(s);
            applicationInfo.volumeUuid = s;
            applicationInfo.storageUuid = uuid;
            if(childPackages != null)
            {
                int i = childPackages.size();
                for(int j = 0; j < i; j++)
                {
                    ((Package)childPackages.get(j)).applicationInfo.volumeUuid = s;
                    ((Package)childPackages.get(j)).applicationInfo.storageUuid = uuid;
                }

            }
        }

        public void setBaseCodePath(String s)
        {
            baseCodePath = s;
            if(childPackages != null)
            {
                int i = childPackages.size();
                for(int j = 0; j < i; j++)
                    ((Package)childPackages.get(j)).baseCodePath = s;

            }
        }

        public void setCodePath(String s)
        {
            codePath = s;
            if(childPackages != null)
            {
                int i = childPackages.size();
                for(int j = 0; j < i; j++)
                    ((Package)childPackages.get(j)).codePath = s;

            }
        }

        public void setPackageName(String s)
        {
            packageName = s;
            applicationInfo.packageName = s;
            for(int i = permissions.size() - 1; i >= 0; i--)
                ((Permission)permissions.get(i)).setPackageName(s);

            for(int j = permissionGroups.size() - 1; j >= 0; j--)
                ((PermissionGroup)permissionGroups.get(j)).setPackageName(s);

            for(int k = activities.size() - 1; k >= 0; k--)
                ((Activity)activities.get(k)).setPackageName(s);

            for(int l = receivers.size() - 1; l >= 0; l--)
                ((Activity)receivers.get(l)).setPackageName(s);

            for(int i1 = providers.size() - 1; i1 >= 0; i1--)
                ((Provider)providers.get(i1)).setPackageName(s);

            for(int j1 = services.size() - 1; j1 >= 0; j1--)
                ((Service)services.get(j1)).setPackageName(s);

            for(int k1 = instrumentation.size() - 1; k1 >= 0; k1--)
                ((Instrumentation)instrumentation.get(k1)).setPackageName(s);

        }

        public void setSignatures(Signature asignature[])
        {
            mSignatures = asignature;
            if(childPackages != null)
            {
                int i = childPackages.size();
                for(int j = 0; j < i; j++)
                    ((Package)childPackages.get(j)).mSignatures = asignature;

            }
        }

        public void setSplitCodePaths(String as[])
        {
            splitCodePaths = as;
        }

        public void setUse32bitAbi(boolean flag)
        {
            use32bitAbi = flag;
            if(childPackages != null)
            {
                int i = childPackages.size();
                for(int j = 0; j < i; j++)
                    ((Package)childPackages.get(j)).use32bitAbi = flag;

            }
        }

        public void setVolumeUuid(String s)
        {
            volumeUuid = s;
            if(childPackages != null)
            {
                int i = childPackages.size();
                for(int j = 0; j < i; j++)
                    ((Package)childPackages.get(j)).volumeUuid = s;

            }
        }

        public String toString()
        {
            return (new StringBuilder()).append("Package{").append(Integer.toHexString(System.identityHashCode(this))).append(" ").append(packageName).append("}").toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            boolean flag = true;
            parcel.writeString(packageName);
            parcel.writeString(manifestPackageName);
            parcel.writeStringArray(splitNames);
            parcel.writeString(volumeUuid);
            parcel.writeString(codePath);
            parcel.writeString(baseCodePath);
            parcel.writeStringArray(splitCodePaths);
            parcel.writeInt(baseRevisionCode);
            parcel.writeIntArray(splitRevisionCodes);
            parcel.writeIntArray(splitFlags);
            parcel.writeIntArray(splitPrivateFlags);
            int j;
            if(baseHardwareAccelerated)
                j = 1;
            else
                j = 0;
            parcel.writeInt(j);
            parcel.writeParcelable(applicationInfo, i);
            parcel.writeParcelableList(permissions, i);
            parcel.writeParcelableList(permissionGroups, i);
            parcel.writeParcelableList(activities, i);
            parcel.writeParcelableList(receivers, i);
            parcel.writeParcelableList(providers, i);
            parcel.writeParcelableList(services, i);
            parcel.writeParcelableList(instrumentation, i);
            parcel.writeStringList(requestedPermissions);
            parcel.writeStringList(protectedBroadcasts);
            parcel.writeParcelable(parentPackage, i);
            parcel.writeParcelableList(childPackages, i);
            parcel.writeString(staticSharedLibName);
            parcel.writeInt(staticSharedLibVersion);
            parcel.writeStringList(libraryNames);
            parcel.writeStringList(usesLibraries);
            parcel.writeStringList(usesOptionalLibraries);
            parcel.writeStringArray(usesLibraryFiles);
            if(ArrayUtils.isEmpty(usesStaticLibraries))
            {
                parcel.writeInt(-1);
            } else
            {
                parcel.writeInt(usesStaticLibraries.size());
                parcel.writeStringList(usesStaticLibraries);
                parcel.writeIntArray(usesStaticLibrariesVersions);
                String as[][] = usesStaticLibrariesCertDigests;
                int l = as.length;
                int k = 0;
                while(k < l) 
                {
                    parcel.writeStringArray(as[k]);
                    k++;
                }
            }
            parcel.writeParcelableList(preferredActivityFilters, i);
            parcel.writeStringList(mOriginalPackages);
            parcel.writeString(mRealPackage);
            parcel.writeStringList(mAdoptPermissions);
            parcel.writeBundle(mAppMetaData);
            parcel.writeInt(mVersionCode);
            parcel.writeString(mVersionName);
            parcel.writeString(mSharedUserId);
            parcel.writeInt(mSharedUserLabel);
            parcel.writeParcelableArray(mSignatures, i);
            parcel.writeSerializable(mCertificates);
            parcel.writeInt(mPreferredOrder);
            parcel.writeParcelableList(configPreferences, i);
            parcel.writeParcelableList(reqFeatures, i);
            parcel.writeParcelableList(featureGroups, i);
            parcel.writeInt(installLocation);
            if(coreApp)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(mRequiredForAllUsers)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeString(mRestrictedAccountType);
            parcel.writeString(mRequiredAccountType);
            parcel.writeString(mOverlayTarget);
            parcel.writeInt(mOverlayPriority);
            if(mIsStaticOverlay)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(mTrustedOverlay)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeArraySet(mSigningKeys);
            parcel.writeArraySet(mUpgradeKeySets);
            writeKeySetMapping(parcel, mKeySetMapping);
            parcel.writeString(cpuAbiOverride);
            if(use32bitAbi)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeByteArray(restrictUpdateHash);
            if(visibleToInstantApps)
                i = ((flag) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public Package createFromParcel(Parcel parcel)
            {
                return new Package(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public Package[] newArray(int i)
            {
                return new Package[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public final ArrayList activities;
        public ApplicationInfo applicationInfo;
        public String baseCodePath;
        public boolean baseHardwareAccelerated;
        public int baseRevisionCode;
        public ArrayList childPackages;
        public String codePath;
        public ArrayList configPreferences;
        public boolean coreApp;
        public String cpuAbiOverride;
        public ArrayList featureGroups;
        public int installLocation;
        public final ArrayList instrumentation;
        public boolean isStub;
        public ArrayList libraryNames;
        public ArrayList mAdoptPermissions;
        public Bundle mAppMetaData;
        public Certificate mCertificates[][];
        public Object mExtras;
        public boolean mIsStaticOverlay;
        public ArrayMap mKeySetMapping;
        public long mLastPackageUsageTimeInMills[];
        public ArrayList mOriginalPackages;
        public int mOverlayPriority;
        public String mOverlayTarget;
        public int mPreferredOrder;
        public String mRealPackage;
        public String mRequiredAccountType;
        public boolean mRequiredForAllUsers;
        public String mRestrictedAccountType;
        public String mSharedUserId;
        public int mSharedUserLabel;
        public Signature mSignatures[];
        public ArraySet mSigningKeys;
        public boolean mTrustedOverlay;
        public ArraySet mUpgradeKeySets;
        public int mVersionCode;
        public String mVersionName;
        public String manifestPackageName;
        public String packageName;
        public Package parentPackage;
        public final ArrayList permissionGroups;
        public final ArrayList permissions;
        public ArrayList preferredActivityFilters;
        public ArrayList protectedBroadcasts;
        public final ArrayList providers;
        public final ArrayList receivers;
        public ArrayList reqFeatures;
        public final ArrayList requestedPermissions;
        public byte restrictUpdateHash[];
        public final ArrayList services;
        public String splitCodePaths[];
        public int splitFlags[];
        public String splitNames[];
        public int splitPrivateFlags[];
        public int splitRevisionCodes[];
        public String staticSharedLibName;
        public int staticSharedLibVersion;
        public boolean use32bitAbi;
        public ArrayList usesLibraries;
        public String usesLibraryFiles[];
        public ArrayList usesOptionalLibraries;
        public ArrayList usesStaticLibraries;
        public String usesStaticLibrariesCertDigests[][];
        public int usesStaticLibrariesVersions[];
        public boolean visibleToInstantApps;
        public String volumeUuid;


        public Package(Parcel parcel)
        {
            boolean flag = true;
            super();
            applicationInfo = new ApplicationInfo();
            permissions = new ArrayList(0);
            permissionGroups = new ArrayList(0);
            activities = new ArrayList(0);
            receivers = new ArrayList(0);
            providers = new ArrayList(0);
            services = new ArrayList(0);
            instrumentation = new ArrayList(0);
            requestedPermissions = new ArrayList();
            staticSharedLibName = null;
            staticSharedLibVersion = 0;
            libraryNames = null;
            usesLibraries = null;
            usesStaticLibraries = null;
            usesStaticLibrariesVersions = null;
            usesStaticLibrariesCertDigests = null;
            usesOptionalLibraries = null;
            usesLibraryFiles = null;
            preferredActivityFilters = null;
            mOriginalPackages = null;
            mRealPackage = null;
            mAdoptPermissions = null;
            mAppMetaData = null;
            mPreferredOrder = 0;
            mLastPackageUsageTimeInMills = new long[8];
            configPreferences = null;
            reqFeatures = null;
            featureGroups = null;
            ClassLoader classloader = java/lang/Object.getClassLoader();
            packageName = parcel.readString().intern();
            manifestPackageName = parcel.readString();
            splitNames = parcel.readStringArray();
            volumeUuid = parcel.readString();
            codePath = parcel.readString();
            baseCodePath = parcel.readString();
            splitCodePaths = parcel.readStringArray();
            baseRevisionCode = parcel.readInt();
            splitRevisionCodes = parcel.createIntArray();
            splitFlags = parcel.createIntArray();
            splitPrivateFlags = parcel.createIntArray();
            boolean flag1;
            int i;
            if(parcel.readInt() == 1)
                flag1 = true;
            else
                flag1 = false;
            baseHardwareAccelerated = flag1;
            applicationInfo = (ApplicationInfo)parcel.readParcelable(classloader);
            if(applicationInfo.permission != null)
                applicationInfo.permission = applicationInfo.permission.intern();
            parcel.readParcelableList(permissions, classloader);
            fixupOwner(permissions);
            parcel.readParcelableList(permissionGroups, classloader);
            fixupOwner(permissionGroups);
            parcel.readParcelableList(activities, classloader);
            fixupOwner(activities);
            parcel.readParcelableList(receivers, classloader);
            fixupOwner(receivers);
            parcel.readParcelableList(providers, classloader);
            fixupOwner(providers);
            parcel.readParcelableList(services, classloader);
            fixupOwner(services);
            parcel.readParcelableList(instrumentation, classloader);
            fixupOwner(instrumentation);
            parcel.readStringList(requestedPermissions);
            internStringArrayList(requestedPermissions);
            protectedBroadcasts = parcel.createStringArrayList();
            internStringArrayList(protectedBroadcasts);
            parentPackage = (Package)parcel.readParcelable(classloader);
            childPackages = new ArrayList();
            parcel.readParcelableList(childPackages, classloader);
            if(childPackages.size() == 0)
                childPackages = null;
            staticSharedLibName = parcel.readString();
            if(staticSharedLibName != null)
                staticSharedLibName = staticSharedLibName.intern();
            staticSharedLibVersion = parcel.readInt();
            libraryNames = parcel.createStringArrayList();
            internStringArrayList(libraryNames);
            usesLibraries = parcel.createStringArrayList();
            internStringArrayList(usesLibraries);
            usesOptionalLibraries = parcel.createStringArrayList();
            internStringArrayList(usesOptionalLibraries);
            usesLibraryFiles = parcel.readStringArray();
            i = parcel.readInt();
            if(i > 0)
            {
                usesStaticLibraries = new ArrayList(i);
                parcel.readStringList(usesStaticLibraries);
                internStringArrayList(usesStaticLibraries);
                usesStaticLibrariesVersions = new int[i];
                parcel.readIntArray(usesStaticLibrariesVersions);
                usesStaticLibrariesCertDigests = new String[i][];
                for(int j = 0; j < i; j++)
                    usesStaticLibrariesCertDigests[j] = parcel.createStringArray();

            }
            preferredActivityFilters = new ArrayList();
            parcel.readParcelableList(preferredActivityFilters, classloader);
            if(preferredActivityFilters.size() == 0)
                preferredActivityFilters = null;
            mOriginalPackages = parcel.createStringArrayList();
            mRealPackage = parcel.readString();
            mAdoptPermissions = parcel.createStringArrayList();
            mAppMetaData = parcel.readBundle();
            mVersionCode = parcel.readInt();
            mVersionName = parcel.readString();
            if(mVersionName != null)
                mVersionName = mVersionName.intern();
            mSharedUserId = parcel.readString();
            if(mSharedUserId != null)
                mSharedUserId = mSharedUserId.intern();
            mSharedUserLabel = parcel.readInt();
            mSignatures = (Signature[])parcel.readParcelableArray(classloader, android/content/pm/Signature);
            mCertificates = (Certificate[][])parcel.readSerializable();
            mPreferredOrder = parcel.readInt();
            configPreferences = new ArrayList();
            parcel.readParcelableList(configPreferences, classloader);
            if(configPreferences.size() == 0)
                configPreferences = null;
            reqFeatures = new ArrayList();
            parcel.readParcelableList(reqFeatures, classloader);
            if(reqFeatures.size() == 0)
                reqFeatures = null;
            featureGroups = new ArrayList();
            parcel.readParcelableList(featureGroups, classloader);
            if(featureGroups.size() == 0)
                featureGroups = null;
            installLocation = parcel.readInt();
            if(parcel.readInt() == 1)
                flag1 = true;
            else
                flag1 = false;
            coreApp = flag1;
            if(parcel.readInt() == 1)
                flag1 = true;
            else
                flag1 = false;
            mRequiredForAllUsers = flag1;
            mRestrictedAccountType = parcel.readString();
            mRequiredAccountType = parcel.readString();
            mOverlayTarget = parcel.readString();
            mOverlayPriority = parcel.readInt();
            if(parcel.readInt() == 1)
                flag1 = true;
            else
                flag1 = false;
            mIsStaticOverlay = flag1;
            if(parcel.readInt() == 1)
                flag1 = true;
            else
                flag1 = false;
            mTrustedOverlay = flag1;
            mSigningKeys = parcel.readArraySet(classloader);
            mUpgradeKeySets = parcel.readArraySet(classloader);
            mKeySetMapping = readKeySetMapping(parcel);
            cpuAbiOverride = parcel.readString();
            if(parcel.readInt() == 1)
                flag1 = true;
            else
                flag1 = false;
            use32bitAbi = flag1;
            restrictUpdateHash = parcel.createByteArray();
            if(parcel.readInt() == 1)
                flag1 = flag;
            else
                flag1 = false;
            visibleToInstantApps = flag1;
        }

        public Package(String s)
        {
            applicationInfo = new ApplicationInfo();
            permissions = new ArrayList(0);
            permissionGroups = new ArrayList(0);
            activities = new ArrayList(0);
            receivers = new ArrayList(0);
            providers = new ArrayList(0);
            services = new ArrayList(0);
            instrumentation = new ArrayList(0);
            requestedPermissions = new ArrayList();
            staticSharedLibName = null;
            staticSharedLibVersion = 0;
            libraryNames = null;
            usesLibraries = null;
            usesStaticLibraries = null;
            usesStaticLibrariesVersions = null;
            usesStaticLibrariesCertDigests = null;
            usesOptionalLibraries = null;
            usesLibraryFiles = null;
            preferredActivityFilters = null;
            mOriginalPackages = null;
            mRealPackage = null;
            mAdoptPermissions = null;
            mAppMetaData = null;
            mPreferredOrder = 0;
            mLastPackageUsageTimeInMills = new long[8];
            configPreferences = null;
            reqFeatures = null;
            featureGroups = null;
            packageName = s;
            manifestPackageName = s;
            applicationInfo.packageName = s;
            applicationInfo.uid = -1;
        }
    }

    public static class PackageLite
    {

        public List getAllCodePaths()
        {
            ArrayList arraylist = new ArrayList();
            arraylist.add(baseCodePath);
            if(!ArrayUtils.isEmpty(splitCodePaths))
                Collections.addAll(arraylist, splitCodePaths);
            return arraylist;
        }

        public final String baseCodePath;
        public final int baseRevisionCode;
        public final String codePath;
        public final String configForSplit[];
        public final boolean coreApp;
        public final boolean debuggable;
        public final boolean extractNativeLibs;
        public final int installLocation;
        public final boolean isFeatureSplits[];
        public final boolean isolatedSplits;
        public final boolean multiArch;
        public final String packageName;
        public final String splitCodePaths[];
        public final String splitNames[];
        public final int splitRevisionCodes[];
        public final boolean use32bitAbi;
        public final String usesSplitNames[];
        public final VerifierInfo verifiers[];
        public final int versionCode;

        public PackageLite(String s, ApkLite apklite, String as[], boolean aflag[], String as1[], String as2[], String as3[], 
                int ai[])
        {
            packageName = apklite.packageName;
            versionCode = apklite.versionCode;
            installLocation = apklite.installLocation;
            verifiers = apklite.verifiers;
            splitNames = as;
            isFeatureSplits = aflag;
            usesSplitNames = as1;
            configForSplit = as2;
            codePath = s;
            baseCodePath = apklite.codePath;
            splitCodePaths = as3;
            baseRevisionCode = apklite.revisionCode;
            splitRevisionCodes = ai;
            coreApp = apklite.coreApp;
            debuggable = apklite.debuggable;
            multiArch = apklite.multiArch;
            use32bitAbi = apklite.use32bitAbi;
            extractNativeLibs = apklite.extractNativeLibs;
            isolatedSplits = apklite.isolatedSplits;
        }
    }

    public static class PackageParserException extends Exception
    {

        public final int error;

        public PackageParserException(int i, String s)
        {
            super(s);
            error = i;
        }

        public PackageParserException(int i, String s, Throwable throwable)
        {
            super(s, throwable);
            error = i;
        }
    }

    public static class ParseComponentArgs extends ParsePackageItemArgs
    {

        final int descriptionRes;
        final int enabledRes;
        int flags;
        final int processRes;
        final String sepProcesses[];

        public ParseComponentArgs(Package package1, String as[], int i, int j, int k, int l, int i1, 
                int j1, String as1[], int k1, int l1, int i2)
        {
            super(package1, as, i, j, k, l, i1, j1);
            sepProcesses = as1;
            processRes = k1;
            descriptionRes = l1;
            enabledRes = i2;
        }
    }

    static class ParsePackageItemArgs
    {

        final int bannerRes;
        final int iconRes;
        final int labelRes;
        final int logoRes;
        final int nameRes;
        final String outError[];
        final Package owner;
        final int roundIconRes;
        TypedArray sa;
        String tag;

        ParsePackageItemArgs(Package package1, String as[], int i, int j, int k, int l, int i1, 
                int j1)
        {
            owner = package1;
            outError = as;
            nameRes = i;
            labelRes = j;
            iconRes = k;
            logoRes = i1;
            bannerRes = j1;
            roundIconRes = l;
        }
    }

    public static final class Permission extends Component
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public void setPackageName(String s)
        {
            super.setPackageName(s);
            info.packageName = s;
        }

        public String toString()
        {
            return (new StringBuilder()).append("Permission{").append(Integer.toHexString(System.identityHashCode(this))).append(" ").append(info.name).append("}").toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeParcelable(info, i);
            int j;
            if(tree)
                j = 1;
            else
                j = 0;
            parcel.writeInt(j);
            parcel.writeParcelable(group, i);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public Permission createFromParcel(Parcel parcel)
            {
                return new Permission(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public Permission[] newArray(int i)
            {
                return new Permission[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public PermissionGroup group;
        public final PermissionInfo info;
        public boolean tree;


        public Permission(Package package1)
        {
            super(package1);
            info = new PermissionInfo();
        }

        public Permission(Package package1, PermissionInfo permissioninfo)
        {
            super(package1);
            info = permissioninfo;
        }

        private Permission(Parcel parcel)
        {
            super(parcel);
            ClassLoader classloader = java/lang/Object.getClassLoader();
            info = (PermissionInfo)parcel.readParcelable(classloader);
            if(info.group != null)
                info.group = info.group.intern();
            boolean flag;
            if(parcel.readInt() == 1)
                flag = true;
            else
                flag = false;
            tree = flag;
            group = (PermissionGroup)parcel.readParcelable(classloader);
        }

        Permission(Parcel parcel, Permission permission)
        {
            this(parcel);
        }
    }

    public static final class PermissionGroup extends Component
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public void setPackageName(String s)
        {
            super.setPackageName(s);
            info.packageName = s;
        }

        public String toString()
        {
            return (new StringBuilder()).append("PermissionGroup{").append(Integer.toHexString(System.identityHashCode(this))).append(" ").append(info.name).append("}").toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeParcelable(info, i);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public PermissionGroup createFromParcel(Parcel parcel)
            {
                return new PermissionGroup(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public PermissionGroup[] newArray(int i)
            {
                return new PermissionGroup[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public final PermissionGroupInfo info;


        public PermissionGroup(Package package1)
        {
            super(package1);
            info = new PermissionGroupInfo();
        }

        public PermissionGroup(Package package1, PermissionGroupInfo permissiongroupinfo)
        {
            super(package1);
            info = permissiongroupinfo;
        }

        private PermissionGroup(Parcel parcel)
        {
            super(parcel);
            info = (PermissionGroupInfo)parcel.readParcelable(java/lang/Object.getClassLoader());
        }

        PermissionGroup(Parcel parcel, PermissionGroup permissiongroup)
        {
            this(parcel);
        }
    }

    public static final class Provider extends Component
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public void setPackageName(String s)
        {
            super.setPackageName(s);
            info.packageName = s;
        }

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder(128);
            stringbuilder.append("Provider{");
            stringbuilder.append(Integer.toHexString(System.identityHashCode(this)));
            stringbuilder.append(' ');
            appendComponentShortName(stringbuilder);
            stringbuilder.append('}');
            return stringbuilder.toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeParcelable(info, i | 2);
            if(syncable)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public Provider createFromParcel(Parcel parcel)
            {
                return new Provider(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public Provider[] newArray(int i)
            {
                return new Provider[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public final ProviderInfo info;
        public boolean syncable;


        public Provider(ParseComponentArgs parsecomponentargs, ProviderInfo providerinfo)
        {
            super(parsecomponentargs, providerinfo);
            info = providerinfo;
            info.applicationInfo = parsecomponentargs.owner.applicationInfo;
            syncable = false;
        }

        public Provider(Provider provider)
        {
            super(provider);
            info = provider.info;
            syncable = provider.syncable;
        }

        private Provider(Parcel parcel)
        {
            super(parcel);
            info = (ProviderInfo)parcel.readParcelable(java/lang/Object.getClassLoader());
            boolean flag;
            if(parcel.readInt() == 1)
                flag = true;
            else
                flag = false;
            syncable = flag;
            for(parcel = intents.iterator(); parcel.hasNext();)
                ((ProviderIntentInfo)parcel.next()).provider = this;

            if(info.readPermission != null)
                info.readPermission = info.readPermission.intern();
            if(info.writePermission != null)
                info.writePermission = info.writePermission.intern();
            if(info.authority != null)
                info.authority = info.authority.intern();
        }

        Provider(Parcel parcel, Provider provider)
        {
            this(parcel);
        }
    }

    public static final class ProviderIntentInfo extends IntentInfo
    {

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder(128);
            stringbuilder.append("ProviderIntentInfo{");
            stringbuilder.append(Integer.toHexString(System.identityHashCode(this)));
            stringbuilder.append(' ');
            provider.appendComponentShortName(stringbuilder);
            stringbuilder.append('}');
            return stringbuilder.toString();
        }

        public Provider provider;

        public ProviderIntentInfo(Provider provider1)
        {
            provider = provider1;
        }

        public ProviderIntentInfo(Parcel parcel)
        {
            super(parcel);
        }
    }

    public static final class Service extends Component
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public void setPackageName(String s)
        {
            super.setPackageName(s);
            info.packageName = s;
        }

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder(128);
            stringbuilder.append("Service{");
            stringbuilder.append(Integer.toHexString(System.identityHashCode(this)));
            stringbuilder.append(' ');
            appendComponentShortName(stringbuilder);
            stringbuilder.append('}');
            return stringbuilder.toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeParcelable(info, i | 2);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public Service createFromParcel(Parcel parcel)
            {
                return new Service(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public Service[] newArray(int i)
            {
                return new Service[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public final ServiceInfo info;


        public Service(ParseComponentArgs parsecomponentargs, ServiceInfo serviceinfo)
        {
            super(parsecomponentargs, serviceinfo);
            info = serviceinfo;
            info.applicationInfo = parsecomponentargs.owner.applicationInfo;
        }

        private Service(Parcel parcel)
        {
            super(parcel);
            info = (ServiceInfo)parcel.readParcelable(java/lang/Object.getClassLoader());
            for(parcel = intents.iterator(); parcel.hasNext();)
                ((ServiceIntentInfo)parcel.next()).service = this;

            if(info.permission != null)
                info.permission = info.permission.intern();
        }

        Service(Parcel parcel, Service service)
        {
            this(parcel);
        }
    }

    public static final class ServiceIntentInfo extends IntentInfo
    {

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder(128);
            stringbuilder.append("ServiceIntentInfo{");
            stringbuilder.append(Integer.toHexString(System.identityHashCode(this)));
            stringbuilder.append(' ');
            service.appendComponentShortName(stringbuilder);
            stringbuilder.append('}');
            return stringbuilder.toString();
        }

        public Service service;

        public ServiceIntentInfo(Service service1)
        {
            service = service1;
        }

        public ServiceIntentInfo(Parcel parcel)
        {
            super(parcel);
        }
    }

    private static class SplitNameComparator
        implements Comparator
    {

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((String)obj, (String)obj1);
        }

        public int compare(String s, String s1)
        {
            if(s == null)
                return -1;
            if(s1 == null)
                return 1;
            else
                return s.compareTo(s1);
        }

        private SplitNameComparator()
        {
        }

        SplitNameComparator(SplitNameComparator splitnamecomparator)
        {
            this();
        }
    }

    public static class SplitPermissionInfo
    {

        public final String newPerms[];
        public final String rootPerm;
        public final int targetSdk;

        public SplitPermissionInfo(String s, String as[], int i)
        {
            rootPerm = s;
            newPerms = as;
            targetSdk = i;
        }
    }


    static int _2D_get0()
    {
        return NUMBER_OF_CORES;
    }

    static Signature[] _2D_wrap0(Certificate acertificate[][])
    {
        return convertToSignatures(acertificate);
    }

    static boolean _2D_wrap1(Package package1, PackageItemInfo packageiteminfo, String as[], String s, TypedArray typedarray, boolean flag, int i, int j, 
            int k, int l, int i1, int j1)
    {
        return parsePackageItemInfo(package1, packageiteminfo, as, s, typedarray, flag, i, j, k, l, i1, j1);
    }

    static String _2D_wrap2(String s, String s1, CharSequence charsequence, int i, String as[], String as1[])
    {
        return buildProcessName(s, s1, charsequence, i, as, as1);
    }

    static Certificate[][] _2D_wrap3(StrictJarFile strictjarfile, ZipEntry zipentry)
    {
        return loadCertificates(strictjarfile, zipentry);
    }

    public PackageParser()
    {
        mParseError = 1;
        mMetrics = new DisplayMetrics();
        mMetrics.setToDefaults();
    }

    private void adjustPackageToBeUnresizeableAndUnpipable(Package package1)
    {
        for(package1 = package1.activities.iterator(); package1.hasNext();)
        {
            Object obj = (Activity)package1.next();
            ((Activity) (obj)).info.resizeMode = 0;
            obj = ((Activity) (obj)).info;
            obj.flags = ((ActivityInfo) (obj)).flags & 0xffbfffff;
        }

    }

    private static String buildClassName(String s, CharSequence charsequence, String as[])
    {
        if(charsequence == null || charsequence.length() <= 0)
        {
            as[0] = (new StringBuilder()).append("Empty class name in package ").append(s).toString();
            return null;
        }
        charsequence = charsequence.toString();
        if(charsequence.charAt(0) == '.')
            return (new StringBuilder()).append(s).append(charsequence).toString();
        if(charsequence.indexOf('.') < 0)
        {
            s = new StringBuilder(s);
            s.append('.');
            s.append(charsequence);
            return s.toString();
        } else
        {
            return charsequence;
        }
    }

    private static String buildCompoundName(String s, CharSequence charsequence, String s1, String as[])
    {
        charsequence = charsequence.toString();
        char c = charsequence.charAt(0);
        if(s != null && c == ':')
        {
            if(charsequence.length() < 2)
            {
                as[0] = (new StringBuilder()).append("Bad ").append(s1).append(" name ").append(charsequence).append(" in package ").append(s).append(": must be at least two characters").toString();
                return null;
            }
            String s2 = validateName(charsequence.substring(1), false, false);
            if(s2 != null)
            {
                as[0] = (new StringBuilder()).append("Invalid ").append(s1).append(" name ").append(charsequence).append(" in package ").append(s).append(": ").append(s2).toString();
                return null;
            } else
            {
                return (new StringBuilder()).append(s).append(charsequence).toString();
            }
        }
        String s3 = validateName(charsequence, true, false);
        if(s3 != null && "system".equals(charsequence) ^ true)
        {
            as[0] = (new StringBuilder()).append("Invalid ").append(s1).append(" name ").append(charsequence).append(" in package ").append(s).append(": ").append(s3).toString();
            return null;
        } else
        {
            return charsequence;
        }
    }

    private static String buildProcessName(String s, String s1, CharSequence charsequence, int i, String as[], String as1[])
    {
        if((i & 8) != 0 && "system".equals(charsequence) ^ true)
        {
            if(s1 != null)
                s = s1;
            return s;
        }
        if(as != null)
            for(i = as.length - 1; i >= 0; i--)
            {
                String s2 = as[i];
                if(s2.equals(s) || s2.equals(s1) || s2.equals(charsequence))
                    return s;
            }

        if(charsequence == null || charsequence.length() <= 0)
            return s1;
        else
            return TextUtils.safeIntern(buildCompoundName(s, charsequence, "process", as1));
    }

    private static String buildTaskAffinityName(String s, String s1, CharSequence charsequence, String as[])
    {
        if(charsequence == null)
            return s1;
        if(charsequence.length() <= 0)
            return null;
        else
            return buildCompoundName(s, charsequence, "taskAffinity", as);
    }

    private void cacheResult(File file, int i, Package package1)
    {
        Object obj;
        Object obj1;
        obj = null;
        obj1 = null;
        if(mCacheDir == null)
            return;
        File file1;
        byte abyte0[];
        file = getCacheKey(file, i);
        file1 = JVM INSTR new #547 <Class File>;
        file1.File(mCacheDir, file);
        if(file1.exists() && !file1.delete())
        {
            file = JVM INSTR new #465 <Class StringBuilder>;
            file.StringBuilder();
            Slog.e("PackageParser", file.append("Unable to delete cache file: ").append(file1).toString());
        }
        abyte0 = toCacheEntry(package1);
        Object obj3;
        if(abyte0 == null)
            return;
        obj3 = null;
        package1 = null;
        file = JVM INSTR new #573 <Class FileOutputStream>;
        file.FileOutputStream(file1);
        file.write(abyte0);
        package1 = ((Package) (obj1));
        if(file == null)
            break MISSING_BLOCK_LABEL_129;
        file.close();
        package1 = ((Package) (obj1));
_L3:
        if(package1 == null) goto _L2; else goto _L1
_L1:
        throw package1;
        file;
_L4:
        Slog.w("PackageParser", "Error writing cache entry.", file);
        file1.delete();
_L2:
        return;
        package1;
          goto _L3
        file;
_L8:
        throw file;
        obj;
        obj1 = file;
        file = ((File) (obj));
_L7:
        obj = obj1;
        if(package1 == null)
            break MISSING_BLOCK_LABEL_184;
        package1.close();
        obj = obj1;
_L5:
        if(obj == null)
            break MISSING_BLOCK_LABEL_245;
        throw obj;
        file;
          goto _L4
        package1;
label0:
        {
            if(obj1 != null)
                break label0;
            obj = package1;
        }
          goto _L5
        obj = obj1;
        if(obj1 == package1) goto _L5; else goto _L6
_L6:
        ((Throwable) (obj1)).addSuppressed(package1);
        obj = obj1;
          goto _L5
        file;
        Slog.w("PackageParser", "Error saving package cache.", file);
          goto _L2
        throw file;
        file;
        package1 = obj3;
        obj1 = obj;
          goto _L7
        Object obj2;
        obj2;
        package1 = file;
        file = ((File) (obj2));
        obj2 = obj;
          goto _L7
        obj2;
        package1 = file;
        file = ((File) (obj2));
          goto _L8
    }

    private boolean checkOverlayRequiredSystemProperty(String s, String s1)
    {
        boolean flag = false;
        if(TextUtils.isEmpty(s) || TextUtils.isEmpty(s1))
            if(!TextUtils.isEmpty(s) || TextUtils.isEmpty(s1) ^ true)
            {
                Slog.w("PackageParser", (new StringBuilder()).append("Disabling overlay - incomplete property :'").append(s).append("=").append(s1).append("' - require both requiredSystemPropertyName").append(" AND requiredSystemPropertyValue to be specified.").toString());
                return false;
            } else
            {
                return true;
            }
        s = SystemProperties.get(s);
        if(s != null)
            flag = s.equals(s1);
        return flag;
    }

    private static boolean checkUseInstalledOrHidden(int i, PackageUserState packageuserstate, ApplicationInfo applicationinfo)
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(!packageuserstate.isAvailable(i))
            if(applicationinfo != null && applicationinfo.isSystemApp())
            {
                if((0x402000 & i) != 0)
                    flag1 = flag;
                else
                    flag1 = false;
            } else
            {
                flag1 = false;
            }
        return flag1;
    }

    public static void closeQuietly(StrictJarFile strictjarfile)
    {
        if(strictjarfile == null)
            break MISSING_BLOCK_LABEL_8;
        strictjarfile.close();
_L2:
        return;
        strictjarfile;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static void collectCertificates(Package package1, int i)
        throws PackageParserException
    {
        collectCertificatesInternal(package1, i);
        int j;
        if(package1.childPackages != null)
            i = package1.childPackages.size();
        else
            i = 0;
        for(j = 0; j < i; j++)
        {
            Package package2 = (Package)package1.childPackages.get(j);
            package2.mCertificates = package1.mCertificates;
            package2.mSignatures = package1.mSignatures;
            package2.mSigningKeys = package1.mSigningKeys;
        }

    }

    private static void collectCertificates(Package package1, File file, int i)
        throws PackageParserException
    {
        String s;
        boolean flag;
        Object obj;
        Signature asignature[];
        s = file.getAbsolutePath();
        flag = false;
        obj = null;
        asignature = null;
        file = ((File) (obj));
        Trace.traceBegin(0x40000L, "verifyV2");
        file = ((File) (obj));
        obj = ApkSignatureSchemeV2Verifier.verify(s);
        file = ((File) (obj));
        Signature asignature1[] = convertToSignatures(((Certificate [][]) (obj)));
        asignature = asignature1;
        flag = true;
        Trace.traceEnd(0x40000L);
        file = ((File) (obj));
_L1:
        android.util.apk.ApkSignatureSchemeV2Verifier.SignatureNotFoundException signaturenotfoundexception;
        if(flag)
            if(package1.mCertificates == null)
            {
                package1.mCertificates = file;
                package1.mSignatures = asignature;
                package1.mSigningKeys = new ArraySet(file.length);
                for(int k = 0; k < file.length; k++)
                {
                    obj = file[k][0];
                    package1.mSigningKeys.add(((Certificate) (obj)).getPublicKey());
                }

            } else
            if(!Signature.areExactMatch(package1.mSignatures, asignature))
                throw new PackageParserException(-104, (new StringBuilder()).append(s).append(" has mismatched certificates").toString());
        break MISSING_BLOCK_LABEL_329;
        file;
        package1 = JVM INSTR new #53  <Class PackageParser$PackageParserException>;
        obj = JVM INSTR new #465 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        package1.PackageParserException(-103, ((StringBuilder) (obj)).append("Failed to collect certificates from ").append(s).append(" using APK Signature Scheme v2").toString(), file);
        throw package1;
        package1;
        Trace.traceEnd(0x40000L);
        throw package1;
        signaturenotfoundexception;
        if((i & 0x800) == 0)
            break MISSING_BLOCK_LABEL_234;
        package1 = JVM INSTR new #53  <Class PackageParser$PackageParserException>;
        file = JVM INSTR new #465 <Class StringBuilder>;
        file.StringBuilder();
        package1.PackageParserException(-103, file.append("No APK Signature Scheme v2 signature in ephemeral package ").append(s).toString(), signaturenotfoundexception);
        throw package1;
        if(package1.applicationInfo.isStaticSharedLibrary())
        {
            package1 = JVM INSTR new #53  <Class PackageParser$PackageParserException>;
            file = JVM INSTR new #465 <Class StringBuilder>;
            file.StringBuilder();
            package1.PackageParserException(-103, file.append("Static shared libs must use v2 signature scheme ").append(s).toString());
            throw package1;
        }
        Trace.traceEnd(0x40000L);
          goto _L1
        int l;
        BoostFramework boostframework;
        int i1;
        if(flag)
            l = 1;
        else
            l = NUMBER_OF_CORES;
        signaturenotfoundexception = new StrictJarFile[l];
        file = new ArrayMap();
        Trace.traceBegin(0x40000L, "strictJarFileCtor");
        if(sPerfBoost == null)
        {
            boostframework = JVM INSTR new #740 <Class BoostFramework>;
            boostframework.BoostFramework();
            sPerfBoost = boostframework;
        }
        if(sPerfBoost == null || !(sIsPerfLockAcquired ^ true) || !(flag ^ true))
            break MISSING_BLOCK_LABEL_431;
        sPerfBoost.perfHint(4232, null, 0x7fffffff, -1);
        Log.d("PackageParser", "Perflock acquired for PackageInstall ");
        sIsPerfLockAcquired = true;
        boolean flag1;
        if((i & 0x40) == 0)
            flag1 = true;
        else
            flag1 = false;
        i1 = 0;
        if(i1 >= l)
            break; /* Loop/switch isn't completed */
        signaturenotfoundexception[i1] = new StrictJarFile(s, flag ^ true, flag1);
        i1++;
        if(true) goto _L3; else goto _L2
_L3:
        break MISSING_BLOCK_LABEL_444;
_L2:
        Object obj1;
        Trace.traceEnd(0x40000L);
        obj1 = signaturenotfoundexception[0].findEntry("AndroidManifest.xml");
        if(obj1 != null)
            break MISSING_BLOCK_LABEL_654;
        package1 = JVM INSTR new #53  <Class PackageParser$PackageParserException>;
        obj1 = JVM INSTR new #465 <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        package1.PackageParserException(-101, ((StringBuilder) (obj1)).append("Package ").append(s).append(" has no manifest").toString());
        throw package1;
        Object obj2;
        obj2;
        package1 = JVM INSTR new #53  <Class PackageParser$PackageParserException>;
        obj1 = JVM INSTR new #465 <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        package1.PackageParserException(-103, ((StringBuilder) (obj1)).append("Failed to collect certificates from ").append(s).toString(), ((Throwable) (obj2)));
        throw package1;
        package1;
        if(sIsPerfLockAcquired && sPerfBoost != null)
        {
            sPerfBoost.perfLockRelease();
            sIsPerfLockAcquired = false;
            Log.d("PackageParser", "Perflock released for PackageInstall ");
        }
        file.clear();
        for(i = 0; i < l; i++)
            closeQuietly(signaturenotfoundexception[i]);

        break MISSING_BLOCK_LABEL_1226;
        if(flag)
        {
            if(sIsPerfLockAcquired && sPerfBoost != null)
            {
                sPerfBoost.perfLockRelease();
                sIsPerfLockAcquired = false;
                Log.d("PackageParser", "Perflock released for PackageInstall ");
            }
            file.clear();
            for(i = 0; i < l; i++)
                closeQuietly(signaturenotfoundexception[i]);

            return;
        }
        Object obj4;
        Trace.traceBegin(0x40000L, "verifyV1");
        obj4 = JVM INSTR new #645 <Class ArrayList>;
        ((ArrayList) (obj4)).ArrayList();
        ((List) (obj4)).add(obj1);
        if((i & 0x40) != 0)
            break MISSING_BLOCK_LABEL_833;
        obj1 = signaturenotfoundexception[0].iterator();
        do
        {
            if(!((Iterator) (obj1)).hasNext())
                break;
            ZipEntry zipentry = (ZipEntry)((Iterator) (obj1)).next();
            if(!zipentry.isDirectory())
            {
                String s1 = zipentry.getName();
                if(!s1.startsWith("META-INF/") && !s1.equals("AndroidManifest.xml"))
                    ((List) (obj4)).add(zipentry);
            }
        } while(true);
        Object obj3;
        Object obj5;
        obj1 = JVM INSTR new #8   <Class PackageParser$1VerificationData>;
        ((_cls1VerificationData) (obj1))._cls1VerificationData();
        obj3 = JVM INSTR new #4   <Class Object>;
        obj3.Object();
        obj1.objWaitAll = obj3;
        obj3 = JVM INSTR new #802 <Class ThreadPoolExecutor>;
        i = NUMBER_OF_CORES;
        int j = NUMBER_OF_CORES;
        obj5 = TimeUnit.SECONDS;
        LinkedBlockingQueue linkedblockingqueue = JVM INSTR new #810 <Class LinkedBlockingQueue>;
        linkedblockingqueue.LinkedBlockingQueue();
        ((ThreadPoolExecutor) (obj3)).ThreadPoolExecutor(i, j, 1L, ((TimeUnit) (obj5)), linkedblockingqueue);
        obj5 = ((Iterable) (obj4)).iterator();
_L4:
        Runnable runnable;
        if(!((Iterator) (obj5)).hasNext())
            break MISSING_BLOCK_LABEL_989;
        obj4 = (ZipEntry)((Iterator) (obj5)).next();
        runnable = JVM INSTR new #6   <Class PackageParser$1>;
        runnable._cls1(((_cls1VerificationData) (obj1)), file, signaturenotfoundexception, ((ZipEntry) (obj4)), s, package1);
        obj4 = ((_cls1VerificationData) (obj1)).objWaitAll;
        obj4;
        JVM INSTR monitorenter ;
        if(((_cls1VerificationData) (obj1)).exceptionFlag == 0)
            ((ThreadPoolExecutor) (obj3)).execute(runnable);
        obj4;
        JVM INSTR monitorexit ;
          goto _L4
        package1;
        obj4;
        JVM INSTR monitorexit ;
        throw package1;
        obj1.wait = true;
        ((ThreadPoolExecutor) (obj3)).shutdown();
_L5:
        boolean flag2 = ((_cls1VerificationData) (obj1)).wait;
        if(!flag2)
            break MISSING_BLOCK_LABEL_1108;
        if(((_cls1VerificationData) (obj1)).exceptionFlag != 0 && ((_cls1VerificationData) (obj1)).shutDown ^ true)
        {
            package1 = JVM INSTR new #465 <Class StringBuilder>;
            package1.StringBuilder();
            Slog.w("PackageParser", package1.append("verifyV1 Exception ").append(((_cls1VerificationData) (obj1)).exceptionFlag).toString());
            ((ThreadPoolExecutor) (obj3)).shutdownNow();
            obj1.shutDown = true;
        }
        obj1.wait = ((ThreadPoolExecutor) (obj3)).awaitTermination(50L, TimeUnit.MILLISECONDS) ^ true;
          goto _L5
        package1;
        Slog.w("PackageParser", "VerifyV1 interrupted while awaiting all threads done...");
          goto _L5
        Trace.traceEnd(0x40000L);
        if(((_cls1VerificationData) (obj1)).exceptionFlag != 0)
        {
            package1 = JVM INSTR new #53  <Class PackageParser$PackageParserException>;
            i = ((_cls1VerificationData) (obj1)).exceptionFlag;
            StringBuilder stringbuilder = JVM INSTR new #465 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            package1.PackageParserException(i, stringbuilder.append("Failed to collect certificates from ").append(s).toString(), ((_cls1VerificationData) (obj1)).exception);
            throw package1;
        }
        if(sIsPerfLockAcquired && sPerfBoost != null)
        {
            sPerfBoost.perfLockRelease();
            sIsPerfLockAcquired = false;
            Log.d("PackageParser", "Perflock released for PackageInstall ");
        }
        file.clear();
        for(i = 0; i < l; i++)
            closeQuietly(signaturenotfoundexception[i]);

        break MISSING_BLOCK_LABEL_1228;
        throw package1;
    }

    private static void collectCertificatesInternal(Package package1, int i)
        throws PackageParserException
    {
        package1.mCertificates = null;
        package1.mSignatures = null;
        package1.mSigningKeys = null;
        Trace.traceBegin(0x40000L, "collectCertificates");
        File file = JVM INSTR new #547 <Class File>;
        file.File(package1.baseCodePath);
        collectCertificates(package1, file, i);
        if(ArrayUtils.isEmpty(package1.splitCodePaths)) goto _L2; else goto _L1
_L1:
        int j = 0;
_L3:
        if(j >= package1.splitCodePaths.length)
            break; /* Loop/switch isn't completed */
        File file1 = JVM INSTR new #547 <Class File>;
        file1.File(package1.splitCodePaths[j]);
        collectCertificates(package1, file1, i);
        j++;
        if(true) goto _L3; else goto _L2
_L2:
        Trace.traceEnd(0x40000L);
        return;
        package1;
        Trace.traceEnd(0x40000L);
        throw package1;
    }

    public static int computeMinSdkVersion(int i, String s, int j, String as[], String as1[])
    {
        if(s == null)
            if(i <= j)
            {
                return i;
            } else
            {
                as1[0] = (new StringBuilder()).append("Requires newer sdk version #").append(i).append(" (current version is #").append(j).append(")").toString();
                return -1;
            }
        if(ArrayUtils.contains(as, s))
            return 10000;
        if(as.length > 0)
            as1[0] = (new StringBuilder()).append("Requires development platform ").append(s).append(" (current platform is any of ").append(Arrays.toString(as)).append(")").toString();
        else
            as1[0] = (new StringBuilder()).append("Requires development platform ").append(s).append(" but this is a release platform.").toString();
        return -1;
    }

    public static int computeTargetSdkVersion(int i, String s, int j, String as[], String as1[])
    {
        if(s == null)
            return i;
        if(ArrayUtils.contains(as, s))
            return 10000;
        if(as.length > 0)
            as1[0] = (new StringBuilder()).append("Requires development platform ").append(s).append(" (current platform is any of ").append(Arrays.toString(as)).append(")").toString();
        else
            as1[0] = (new StringBuilder()).append("Requires development platform ").append(s).append(" but this is a release platform.").toString();
        return -1;
    }

    private static Signature[] convertToSignatures(Certificate acertificate[][])
        throws CertificateEncodingException
    {
        Signature asignature[] = new Signature[acertificate.length];
        for(int i = 0; i < acertificate.length; i++)
            asignature[i] = new Signature(acertificate[i]);

        return asignature;
    }

    private static boolean copyNeeded(int i, Package package1, PackageUserState packageuserstate, Bundle bundle, int j)
    {
        if(j != 0)
            return true;
        if(packageuserstate.enabled != 0)
        {
            boolean flag;
            if(packageuserstate.enabled == 1)
                flag = true;
            else
                flag = false;
            if(package1.applicationInfo.enabled != flag)
                return true;
        }
        boolean flag1;
        if((package1.applicationInfo.flags & 0x40000000) != 0)
            flag1 = true;
        else
            flag1 = false;
        if(packageuserstate.suspended != flag1)
            return true;
        if(!packageuserstate.installed || packageuserstate.hidden)
            return true;
        if(packageuserstate.stopped)
            return true;
        if(packageuserstate.instantApp != package1.applicationInfo.isInstantApp())
            return true;
        if((i & 0x80) != 0 && (bundle != null || package1.mAppMetaData != null))
            return true;
        if((i & 0x400) != 0 && package1.usesLibraryFiles != null)
            return true;
        return package1.staticSharedLibName != null;
    }

    public static Package fromCacheEntryStatic(byte abyte0[])
    {
        Parcel parcel = Parcel.obtain();
        parcel.unmarshall(abyte0, 0, abyte0.length);
        parcel.setDataPosition(0);
        (new PackageParserCacheHelper.ReadHelper(parcel)).startAndInstall();
        abyte0 = new Package(parcel);
        parcel.recycle();
        sCachedPackageReadCount.incrementAndGet();
        return abyte0;
    }

    public static final ActivityInfo generateActivityInfo(ActivityInfo activityinfo, int i, PackageUserState packageuserstate, int j)
    {
        if(activityinfo == null)
            return null;
        if(!checkUseInstalledOrHidden(i, packageuserstate, activityinfo.applicationInfo))
        {
            return null;
        } else
        {
            activityinfo = new ActivityInfo(activityinfo);
            activityinfo.applicationInfo = generateApplicationInfo(activityinfo.applicationInfo, i, packageuserstate, j);
            return activityinfo;
        }
    }

    public static final ActivityInfo generateActivityInfo(Activity activity, int i, PackageUserState packageuserstate, int j)
    {
        if(activity == null)
            return null;
        if(!checkUseInstalledOrHidden(i, packageuserstate, activity.owner.applicationInfo))
            return null;
        if(!copyNeeded(i, activity.owner, packageuserstate, activity.metaData, j))
        {
            updateApplicationInfo(activity.info.applicationInfo, i, packageuserstate);
            return activity.info;
        } else
        {
            ActivityInfo activityinfo = new ActivityInfo(activity.info);
            activityinfo.metaData = activity.metaData;
            activityinfo.applicationInfo = generateApplicationInfo(activity.owner, i, packageuserstate, j);
            return activityinfo;
        }
    }

    public static ApplicationInfo generateApplicationInfo(ApplicationInfo applicationinfo, int i, PackageUserState packageuserstate, int j)
    {
        if(applicationinfo == null)
            return null;
        if(!checkUseInstalledOrHidden(i, packageuserstate, applicationinfo))
            return null;
        applicationinfo = new ApplicationInfo(applicationinfo);
        applicationinfo.initForUser(j);
        if(packageuserstate.stopped)
            applicationinfo.flags = applicationinfo.flags | 0x200000;
        else
            applicationinfo.flags = applicationinfo.flags & 0xffdfffff;
        updateApplicationInfo(applicationinfo, i, packageuserstate);
        return applicationinfo;
    }

    public static ApplicationInfo generateApplicationInfo(Package package1, int i, PackageUserState packageuserstate)
    {
        return generateApplicationInfo(package1, i, packageuserstate, UserHandle.getCallingUserId());
    }

    public static ApplicationInfo generateApplicationInfo(Package package1, int i, PackageUserState packageuserstate, int j)
    {
        if(package1 == null)
            return null;
        if(!checkUseInstalledOrHidden(i, packageuserstate, package1.applicationInfo) || package1.isMatch(i) ^ true)
            return null;
        if(!copyNeeded(i, package1, packageuserstate, null, j) && ((0x8000 & i) == 0 || packageuserstate.enabled != 4))
        {
            updateApplicationInfo(package1.applicationInfo, i, packageuserstate);
            return package1.applicationInfo;
        }
        ApplicationInfo applicationinfo = new ApplicationInfo(package1.applicationInfo);
        applicationinfo.initForUser(j);
        if((i & 0x80) != 0)
            applicationinfo.metaData = package1.mAppMetaData;
        if((i & 0x400) != 0)
            applicationinfo.sharedLibraryFiles = package1.usesLibraryFiles;
        if(packageuserstate.stopped)
            applicationinfo.flags = applicationinfo.flags | 0x200000;
        else
            applicationinfo.flags = applicationinfo.flags & 0xffdfffff;
        updateApplicationInfo(applicationinfo, i, packageuserstate);
        return applicationinfo;
    }

    public static final InstrumentationInfo generateInstrumentationInfo(Instrumentation instrumentation, int i)
    {
        if(instrumentation == null)
            return null;
        if((i & 0x80) == 0)
        {
            return instrumentation.info;
        } else
        {
            InstrumentationInfo instrumentationinfo = new InstrumentationInfo(instrumentation.info);
            instrumentationinfo.metaData = instrumentation.metaData;
            return instrumentationinfo;
        }
    }

    public static PackageInfo generatePackageInfo(Package package1, int ai[], int i, long l, long l1, Set set, 
            PackageUserState packageuserstate)
    {
        return generatePackageInfo(package1, ai, i, l, l1, set, packageuserstate, UserHandle.getCallingUserId());
    }

    public static PackageInfo generatePackageInfo(Package package1, int ai[], int i, long l, long l1, Set set, 
            PackageUserState packageuserstate, int j)
    {
        if(!checkUseInstalledOrHidden(i, packageuserstate, package1.applicationInfo) || package1.isMatch(i) ^ true)
            return null;
        PackageInfo packageinfo = new PackageInfo();
        packageinfo.packageName = package1.packageName;
        packageinfo.splitNames = package1.splitNames;
        packageinfo.versionCode = package1.mVersionCode;
        packageinfo.baseRevisionCode = package1.baseRevisionCode;
        packageinfo.splitRevisionCodes = package1.splitRevisionCodes;
        packageinfo.versionName = package1.mVersionName;
        packageinfo.sharedUserId = package1.mSharedUserId;
        packageinfo.sharedUserLabel = package1.mSharedUserLabel;
        packageinfo.applicationInfo = generateApplicationInfo(package1, i, packageuserstate, j);
        packageinfo.installLocation = package1.installLocation;
        packageinfo.isStub = package1.isStub;
        packageinfo.coreApp = package1.coreApp;
        if((packageinfo.applicationInfo.flags & 1) != 0 || (packageinfo.applicationInfo.flags & 0x80) != 0)
            packageinfo.requiredForAllUsers = package1.mRequiredForAllUsers;
        packageinfo.restrictedAccountType = package1.mRestrictedAccountType;
        packageinfo.requiredAccountType = package1.mRequiredAccountType;
        packageinfo.overlayTarget = package1.mOverlayTarget;
        packageinfo.overlayPriority = package1.mOverlayPriority;
        if(package1.mIsStaticOverlay)
            packageinfo.overlayFlags = packageinfo.overlayFlags | 2;
        if(package1.mTrustedOverlay)
            packageinfo.overlayFlags = packageinfo.overlayFlags | 4;
        packageinfo.firstInstallTime = l;
        packageinfo.lastUpdateTime = l1;
        if((i & 0x100) != 0)
            packageinfo.gids = ai;
        int k;
        if((i & 0x4000) != 0)
        {
            int k2;
            int k3;
            int k4;
            if(package1.configPreferences != null)
                k = package1.configPreferences.size();
            else
                k = 0;
            if(k > 0)
            {
                packageinfo.configPreferences = new ConfigurationInfo[k];
                package1.configPreferences.toArray(packageinfo.configPreferences);
            }
            if(package1.reqFeatures != null)
                k = package1.reqFeatures.size();
            else
                k = 0;
            if(k > 0)
            {
                packageinfo.reqFeatures = new FeatureInfo[k];
                package1.reqFeatures.toArray(packageinfo.reqFeatures);
            }
            if(package1.featureGroups != null)
                k = package1.featureGroups.size();
            else
                k = 0;
            if(k > 0)
            {
                packageinfo.featureGroups = new FeatureGroupInfo[k];
                package1.featureGroups.toArray(packageinfo.featureGroups);
            }
        }
        if((i & 1) != 0)
        {
            k2 = package1.activities.size();
            if(k2 > 0)
            {
                ActivityInfo aactivityinfo[] = new ActivityInfo[k2];
                k3 = 0;
                k = 0;
                for(; k3 < k2; k3++)
                {
                    ai = (Activity)package1.activities.get(k3);
                    if(packageuserstate.isMatch(((Activity) (ai)).info, i))
                    {
                        k4 = k + 1;
                        aactivityinfo[k] = generateActivityInfo(ai, i, packageuserstate, j);
                        k = k4;
                    }
                }

                packageinfo.activities = (ActivityInfo[])ArrayUtils.trimToSize(aactivityinfo, k);
            }
        }
        if((i & 2) != 0)
        {
            int l2 = package1.receivers.size();
            if(l2 > 0)
            {
                ActivityInfo aactivityinfo1[] = new ActivityInfo[l2];
                int l3 = 0;
                int i1 = 0;
                for(; l3 < l2; l3++)
                {
                    ai = (Activity)package1.receivers.get(l3);
                    if(packageuserstate.isMatch(((Activity) (ai)).info, i))
                    {
                        int l4 = i1 + 1;
                        aactivityinfo1[i1] = generateActivityInfo(ai, i, packageuserstate, j);
                        i1 = l4;
                    }
                }

                packageinfo.receivers = (ActivityInfo[])ArrayUtils.trimToSize(aactivityinfo1, i1);
            }
        }
        if((i & 4) != 0)
        {
            int i3 = package1.services.size();
            if(i3 > 0)
            {
                ServiceInfo aserviceinfo[] = new ServiceInfo[i3];
                int i4 = 0;
                int j1 = 0;
                for(; i4 < i3; i4++)
                {
                    ai = (Service)package1.services.get(i4);
                    if(packageuserstate.isMatch(((Service) (ai)).info, i))
                    {
                        int i5 = j1 + 1;
                        aserviceinfo[j1] = generateServiceInfo(ai, i, packageuserstate, j);
                        j1 = i5;
                    }
                }

                packageinfo.services = (ServiceInfo[])ArrayUtils.trimToSize(aserviceinfo, j1);
            }
        }
        if((i & 8) != 0)
        {
            int j3 = package1.providers.size();
            if(j3 > 0)
            {
                ProviderInfo aproviderinfo[] = new ProviderInfo[j3];
                int j4 = 0;
                int k1 = 0;
                for(; j4 < j3; j4++)
                {
                    ai = (Provider)package1.providers.get(j4);
                    if(packageuserstate.isMatch(((Provider) (ai)).info, i))
                    {
                        int j5 = k1 + 1;
                        aproviderinfo[k1] = generateProviderInfo(ai, i, packageuserstate, j);
                        k1 = j5;
                    }
                }

                packageinfo.providers = (ProviderInfo[])ArrayUtils.trimToSize(aproviderinfo, k1);
            }
        }
        if((i & 0x10) != 0)
        {
            int i2 = package1.instrumentation.size();
            if(i2 > 0)
            {
                packageinfo.instrumentation = new InstrumentationInfo[i2];
                for(j = 0; j < i2; j++)
                    packageinfo.instrumentation[j] = generateInstrumentationInfo((Instrumentation)package1.instrumentation.get(j), i);

            }
        }
        if((i & 0x1000) != 0)
        {
            int j2 = package1.permissions.size();
            if(j2 > 0)
            {
                packageinfo.permissions = new PermissionInfo[j2];
                for(j = 0; j < j2; j++)
                    packageinfo.permissions[j] = generatePermissionInfo((Permission)package1.permissions.get(j), i);

            }
            j2 = package1.requestedPermissions.size();
            if(j2 > 0)
            {
                packageinfo.requestedPermissions = new String[j2];
                packageinfo.requestedPermissionsFlags = new int[j2];
                for(j = 0; j < j2; j++)
                {
                    packageuserstate = (String)package1.requestedPermissions.get(j);
                    packageinfo.requestedPermissions[j] = packageuserstate;
                    ai = packageinfo.requestedPermissionsFlags;
                    ai[j] = ai[j] | 1;
                    if(set != null && set.contains(packageuserstate))
                    {
                        ai = packageinfo.requestedPermissionsFlags;
                        ai[j] = ai[j] | 2;
                    }
                }

            }
        }
        if((i & 0x40) != 0)
        {
            if(package1.mSignatures != null)
                i = package1.mSignatures.length;
            else
                i = 0;
            if(i > 0)
            {
                packageinfo.signatures = new Signature[i];
                System.arraycopy(package1.mSignatures, 0, packageinfo.signatures, 0, i);
            }
        }
        return packageinfo;
    }

    public static final PermissionGroupInfo generatePermissionGroupInfo(PermissionGroup permissiongroup, int i)
    {
        if(permissiongroup == null)
            return null;
        if((i & 0x80) == 0)
        {
            return permissiongroup.info;
        } else
        {
            PermissionGroupInfo permissiongroupinfo = new PermissionGroupInfo(permissiongroup.info);
            permissiongroupinfo.metaData = permissiongroup.metaData;
            return permissiongroupinfo;
        }
    }

    public static final PermissionInfo generatePermissionInfo(Permission permission, int i)
    {
        if(permission == null)
            return null;
        if((i & 0x80) == 0)
        {
            return permission.info;
        } else
        {
            PermissionInfo permissioninfo = new PermissionInfo(permission.info);
            permissioninfo.metaData = permission.metaData;
            return permissioninfo;
        }
    }

    public static final ProviderInfo generateProviderInfo(Provider provider, int i, PackageUserState packageuserstate, int j)
    {
        if(provider == null)
            return null;
        if(!checkUseInstalledOrHidden(i, packageuserstate, provider.owner.applicationInfo))
            return null;
        if(!copyNeeded(i, provider.owner, packageuserstate, provider.metaData, j) && ((i & 0x800) != 0 || provider.info.uriPermissionPatterns == null))
        {
            updateApplicationInfo(provider.info.applicationInfo, i, packageuserstate);
            return provider.info;
        }
        ProviderInfo providerinfo = new ProviderInfo(provider.info);
        providerinfo.metaData = provider.metaData;
        if((i & 0x800) == 0)
            providerinfo.uriPermissionPatterns = null;
        providerinfo.applicationInfo = generateApplicationInfo(provider.owner, i, packageuserstate, j);
        return providerinfo;
    }

    public static final ServiceInfo generateServiceInfo(Service service, int i, PackageUserState packageuserstate, int j)
    {
        if(service == null)
            return null;
        if(!checkUseInstalledOrHidden(i, packageuserstate, service.owner.applicationInfo))
            return null;
        if(!copyNeeded(i, service.owner, packageuserstate, service.metaData, j))
        {
            updateApplicationInfo(service.info.applicationInfo, i, packageuserstate);
            return service.info;
        } else
        {
            ServiceInfo serviceinfo = new ServiceInfo(service.info);
            serviceinfo.metaData = service.metaData;
            serviceinfo.applicationInfo = generateApplicationInfo(service.owner, i, packageuserstate, j);
            return serviceinfo;
        }
    }

    public static int getActivityConfigChanges(int i, int j)
    {
        return j & 3 | i;
    }

    public static int getApkSigningVersion(Package package1)
    {
        boolean flag;
        try
        {
            flag = ApkSignatureSchemeV2Verifier.hasSignature(package1.baseCodePath);
        }
        // Misplaced declaration of an exception variable
        catch(Package package1)
        {
            return 0;
        }
        return !flag ? 1 : 2;
    }

    private String getCacheKey(File file, int i)
    {
        file = new StringBuilder(file.getName());
        file.append('-');
        file.append(i);
        return file.toString();
    }

    private Package getCachedResult(File file, int i)
    {
        boolean flag;
        Object obj;
        flag = false;
        if(mCacheDir == null)
            return null;
        obj = getCacheKey(file, i);
        obj = new File(mCacheDir, ((String) (obj)));
        if(!isCacheUpToDate(file, ((File) (obj))))
            return null;
        Package package1;
        String as[];
        package1 = fromCacheEntry(IoUtils.readFileAsByteArray(((File) (obj)).getAbsolutePath()));
        if(mCallback == null)
            break MISSING_BLOCK_LABEL_143;
        as = mCallback.getOverlayApks(package1.packageName);
        if(as == null)
            break MISSING_BLOCK_LABEL_143;
        int j;
        if(as.length <= 0)
            break MISSING_BLOCK_LABEL_143;
        j = as.length;
        i = ((flag) ? 1 : 0);
        do
        {
            if(i >= j)
                break;
            String s = as[i];
            boolean flag1;
            try
            {
                file = JVM INSTR new #547 <Class File>;
                file.File(s);
                flag1 = isCacheUpToDate(file, ((File) (obj)));
            }
            // Misplaced declaration of an exception variable
            catch(File file)
            {
                Slog.w("PackageParser", "Error reading package cache: ", file);
                ((File) (obj)).delete();
                return null;
            }
            if(!flag1)
                return null;
            i++;
        } while(true);
        return package1;
    }

    private static boolean hasDomainURLs(Package package1)
    {
        if(package1 == null || package1.activities == null)
            return false;
        package1 = package1.activities;
        int i = package1.size();
        int j = 0;
        while(j < i) 
        {
            ArrayList arraylist = ((Activity)package1.get(j)).intents;
            if(arraylist != null)
            {
                int k = arraylist.size();
                int l = 0;
                while(l < k) 
                {
                    ActivityIntentInfo activityintentinfo = (ActivityIntentInfo)arraylist.get(l);
                    if(!activityintentinfo.hasAction("android.intent.action.VIEW") || !activityintentinfo.hasAction("android.intent.action.VIEW") || !activityintentinfo.hasDataScheme("http") && !activityintentinfo.hasDataScheme("https"))
                        l++;
                    else
                        return true;
                }
            }
            j++;
        }
        return false;
    }

    public static final boolean isApkFile(File file)
    {
        return isApkPath(file.getName());
    }

    public static boolean isApkPath(String s)
    {
        return s.endsWith(".apk");
    }

    public static boolean isAvailable(PackageUserState packageuserstate)
    {
        return checkUseInstalledOrHidden(0, packageuserstate, null);
    }

    private static boolean isCacheUpToDate(File file, File file1)
    {
        boolean flag = false;
        long l;
        long l1;
        try
        {
            file = Os.stat(file.getAbsolutePath());
            file1 = Os.stat(file1.getAbsolutePath());
            l = ((StructStat) (file)).st_mtime;
            l1 = ((StructStat) (file1)).st_mtime;
        }
        // Misplaced declaration of an exception variable
        catch(File file)
        {
            if(((ErrnoException) (file)).errno != OsConstants.ENOENT)
                Slog.w("Error while stating package cache : ", file);
            return false;
        }
        if(l < l1)
            flag = true;
        return flag;
    }

    private boolean isImplicitlyExposedIntent(IntentInfo intentinfo)
    {
        boolean flag;
        if(!intentinfo.hasCategory("android.intent.category.BROWSABLE") && !intentinfo.hasAction("android.intent.action.SEND") && !intentinfo.hasAction("android.intent.action.SENDTO"))
            flag = intentinfo.hasAction("android.intent.action.SEND_MULTIPLE");
        else
            flag = true;
        return flag;
    }

    private static int loadApkIntoAssetManager(AssetManager assetmanager, String s, int i)
        throws PackageParserException
    {
        if((i & 4) != 0 && isApkPath(s) ^ true)
            throw new PackageParserException(-100, (new StringBuilder()).append("Invalid package file: ").append(s).toString());
        i = assetmanager.addAssetPath(s);
        if(i == 0)
            throw new PackageParserException(-101, (new StringBuilder()).append("Failed adding asset path: ").append(s).toString());
        else
            return i;
    }

    private static Certificate[][] loadCertificates(StrictJarFile strictjarfile, ZipEntry zipentry)
        throws PackageParserException
    {
        InputStream inputstream;
        InputStream inputstream1;
        inputstream = null;
        inputstream1 = null;
        InputStream inputstream2 = strictjarfile.getInputStream(zipentry);
        inputstream1 = inputstream2;
        inputstream = inputstream2;
        readFullyIgnoringContents(inputstream2);
        inputstream1 = inputstream2;
        inputstream = inputstream2;
        Certificate acertificate[][] = strictjarfile.getCertificateChains(zipentry);
        IoUtils.closeQuietly(inputstream2);
        return acertificate;
        Object obj;
        obj;
        inputstream = inputstream1;
        PackageParserException packageparserexception = JVM INSTR new #53  <Class PackageParser$PackageParserException>;
        inputstream = inputstream1;
        StringBuilder stringbuilder = JVM INSTR new #465 <Class StringBuilder>;
        inputstream = inputstream1;
        stringbuilder.StringBuilder();
        inputstream = inputstream1;
        packageparserexception.PackageParserException(-102, stringbuilder.append("Failed reading ").append(zipentry.getName()).append(" in ").append(strictjarfile).toString(), ((Throwable) (obj)));
        inputstream = inputstream1;
        throw packageparserexception;
        strictjarfile;
        IoUtils.closeQuietly(inputstream);
        throw strictjarfile;
    }

    private static AssetManager newConfiguredAssetManager()
    {
        AssetManager assetmanager = new AssetManager();
        assetmanager.setConfiguration(0, 0, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, android.os.Build.VERSION.RESOURCES_SDK_INT);
        return assetmanager;
    }

    private Activity parseActivity(Package package1, Resources resources, XmlResourceParser xmlresourceparser, int i, String as[], CachedComponentArgs cachedcomponentargs, boolean flag, 
            boolean flag1)
        throws XmlPullParserException, IOException
    {
        TypedArray typedarray = resources.obtainAttributes(xmlresourceparser, com.android.internal.R.styleable.AndroidManifestActivity);
        if(cachedcomponentargs.mActivityArgs == null)
            cachedcomponentargs.mActivityArgs = new ParseComponentArgs(package1, as, 3, 1, 2, 44, 23, 30, mSeparateProcesses, 7, 17, 5);
        Object obj = cachedcomponentargs.mActivityArgs;
        Object obj1;
        if(flag)
            obj1 = "<receiver>";
        else
            obj1 = "<activity>";
        obj.tag = ((String) (obj1));
        cachedcomponentargs.mActivityArgs.sa = typedarray;
        cachedcomponentargs.mActivityArgs.flags = i;
        obj1 = new Activity(cachedcomponentargs.mActivityArgs, new ActivityInfo());
        if(as[0] != null)
        {
            typedarray.recycle();
            return null;
        }
        boolean flag2 = typedarray.hasValue(6);
        if(flag2)
            ((Activity) (obj1)).info.exported = typedarray.getBoolean(6, false);
        ((Activity) (obj1)).info.theme = typedarray.getResourceId(0, 0);
        ((Activity) (obj1)).info.uiOptions = typedarray.getInt(26, ((Activity) (obj1)).info.applicationInfo.uiOptions);
        obj = typedarray.getNonConfigurationString(27, 1024);
        boolean flag3;
        if(obj != null)
        {
            cachedcomponentargs = buildClassName(((Activity) (obj1)).info.packageName, ((CharSequence) (obj)), as);
            if(as[0] == null)
            {
                ((Activity) (obj1)).info.parentActivityName = cachedcomponentargs;
            } else
            {
                Log.e("PackageParser", (new StringBuilder()).append("Activity ").append(((Activity) (obj1)).info.name).append(" specified invalid parentActivityName ").append(((String) (obj))).toString());
                as[0] = null;
            }
        }
        cachedcomponentargs = typedarray.getNonConfigurationString(4, 0);
        if(cachedcomponentargs == null)
        {
            ((Activity) (obj1)).info.permission = package1.applicationInfo.permission;
        } else
        {
            ActivityInfo activityinfo = ((Activity) (obj1)).info;
            if(cachedcomponentargs.length() > 0)
                cachedcomponentargs = cachedcomponentargs.toString().intern();
            else
                cachedcomponentargs = null;
            activityinfo.permission = cachedcomponentargs;
        }
        cachedcomponentargs = typedarray.getNonConfigurationString(8, 1024);
        ((Activity) (obj1)).info.taskAffinity = buildTaskAffinityName(package1.applicationInfo.packageName, package1.applicationInfo.taskAffinity, cachedcomponentargs, as);
        cachedcomponentargs = typedarray.getNonConfigurationString(48, 0);
        if(!TextUtils.isEmpty(cachedcomponentargs))
            if(validateName(cachedcomponentargs, false, false) != null)
                Slog.w("PackageParser", (new StringBuilder()).append("Activity ").append(((Activity) (obj1)).info.name).append(" specified invalid splitName ").append(cachedcomponentargs).append(" at ").append(mArchiveSourcePath).toString());
            else
                ((Activity) (obj1)).info.splitName = cachedcomponentargs;
        ((Activity) (obj1)).info.flags = 0;
        if(typedarray.getBoolean(9, false))
        {
            cachedcomponentargs = ((Activity) (obj1)).info;
            cachedcomponentargs.flags = ((ActivityInfo) (cachedcomponentargs)).flags | 1;
        }
        if(typedarray.getBoolean(10, false))
        {
            cachedcomponentargs = ((Activity) (obj1)).info;
            cachedcomponentargs.flags = ((ActivityInfo) (cachedcomponentargs)).flags | 2;
        }
        if(typedarray.getBoolean(11, false))
        {
            cachedcomponentargs = ((Activity) (obj1)).info;
            cachedcomponentargs.flags = ((ActivityInfo) (cachedcomponentargs)).flags | 4;
        }
        if(typedarray.getBoolean(21, false))
        {
            cachedcomponentargs = ((Activity) (obj1)).info;
            cachedcomponentargs.flags = ((ActivityInfo) (cachedcomponentargs)).flags | 0x80;
        }
        if(typedarray.getBoolean(18, false))
        {
            cachedcomponentargs = ((Activity) (obj1)).info;
            cachedcomponentargs.flags = ((ActivityInfo) (cachedcomponentargs)).flags | 8;
        }
        if(typedarray.getBoolean(12, false))
        {
            cachedcomponentargs = ((Activity) (obj1)).info;
            cachedcomponentargs.flags = ((ActivityInfo) (cachedcomponentargs)).flags | 0x10;
        }
        if(typedarray.getBoolean(13, false))
        {
            cachedcomponentargs = ((Activity) (obj1)).info;
            cachedcomponentargs.flags = ((ActivityInfo) (cachedcomponentargs)).flags | 0x20;
        }
        if((package1.applicationInfo.flags & 0x20) != 0)
            flag3 = true;
        else
            flag3 = false;
        if(typedarray.getBoolean(19, flag3))
        {
            cachedcomponentargs = ((Activity) (obj1)).info;
            cachedcomponentargs.flags = ((ActivityInfo) (cachedcomponentargs)).flags | 0x40;
        }
        if(typedarray.getBoolean(22, false))
        {
            cachedcomponentargs = ((Activity) (obj1)).info;
            cachedcomponentargs.flags = ((ActivityInfo) (cachedcomponentargs)).flags | 0x100;
        }
        if(typedarray.getBoolean(29, false) || typedarray.getBoolean(39, false))
        {
            cachedcomponentargs = ((Activity) (obj1)).info;
            cachedcomponentargs.flags = ((ActivityInfo) (cachedcomponentargs)).flags | 0x400;
        }
        if(typedarray.getBoolean(24, false))
        {
            cachedcomponentargs = ((Activity) (obj1)).info;
            cachedcomponentargs.flags = ((ActivityInfo) (cachedcomponentargs)).flags | 0x800;
        }
        if(typedarray.getBoolean(54, false))
        {
            cachedcomponentargs = ((Activity) (obj1)).info;
            cachedcomponentargs.flags = ((ActivityInfo) (cachedcomponentargs)).flags | 0x20000000;
        }
        if(!flag)
        {
            if(typedarray.getBoolean(25, flag1))
            {
                cachedcomponentargs = ((Activity) (obj1)).info;
                cachedcomponentargs.flags = ((ActivityInfo) (cachedcomponentargs)).flags | 0x200;
            }
            ((Activity) (obj1)).info.launchMode = typedarray.getInt(14, 0);
            ((Activity) (obj1)).info.documentLaunchMode = typedarray.getInt(33, 0);
            ((Activity) (obj1)).info.maxRecents = typedarray.getInt(34, ActivityManager.getDefaultAppRecentsLimitStatic());
            ((Activity) (obj1)).info.configChanges = getActivityConfigChanges(typedarray.getInt(16, 0), typedarray.getInt(47, 0));
            ((Activity) (obj1)).info.softInputMode = typedarray.getInt(20, 0);
            ((Activity) (obj1)).info.persistableMode = typedarray.getInteger(32, 0);
            if(typedarray.getBoolean(31, false))
            {
                cachedcomponentargs = ((Activity) (obj1)).info;
                cachedcomponentargs.flags = ((ActivityInfo) (cachedcomponentargs)).flags | 0x80000000;
            }
            if(typedarray.getBoolean(35, false))
            {
                cachedcomponentargs = ((Activity) (obj1)).info;
                cachedcomponentargs.flags = ((ActivityInfo) (cachedcomponentargs)).flags | 0x2000;
            }
            if(typedarray.getBoolean(36, false))
            {
                cachedcomponentargs = ((Activity) (obj1)).info;
                cachedcomponentargs.flags = ((ActivityInfo) (cachedcomponentargs)).flags | 0x1000;
            }
            if(typedarray.getBoolean(37, false))
            {
                cachedcomponentargs = ((Activity) (obj1)).info;
                cachedcomponentargs.flags = ((ActivityInfo) (cachedcomponentargs)).flags | 0x4000;
            }
            ((Activity) (obj1)).info.screenOrientation = typedarray.getInt(15, -1);
            setActivityResizeMode(((Activity) (obj1)).info, typedarray, package1);
            if(typedarray.getBoolean(41, false))
            {
                cachedcomponentargs = ((Activity) (obj1)).info;
                cachedcomponentargs.flags = ((ActivityInfo) (cachedcomponentargs)).flags | 0x400000;
            }
            if(typedarray.getBoolean(53, false))
            {
                cachedcomponentargs = ((Activity) (obj1)).info;
                cachedcomponentargs.flags = ((ActivityInfo) (cachedcomponentargs)).flags | 0x40000;
            }
            if(typedarray.hasValue(50) && typedarray.getType(50) == 4)
                Activity._2D_wrap1(((Activity) (obj1)), typedarray.getFloat(50, 0.0F));
            ((Activity) (obj1)).info.lockTaskLaunchMode = typedarray.getInt(38, 0);
            cachedcomponentargs = ((Activity) (obj1)).info;
            flag1 = typedarray.getBoolean(42, false);
            ((Activity) (obj1)).info.directBootAware = flag1;
            cachedcomponentargs.encryptionAware = flag1;
            ((Activity) (obj1)).info.requestedVrComponent = typedarray.getString(43);
            ((Activity) (obj1)).info.rotationAnimation = typedarray.getInt(46, -1);
            ((Activity) (obj1)).info.colorMode = typedarray.getInt(49, 0);
            if(typedarray.getBoolean(51, false))
            {
                cachedcomponentargs = ((Activity) (obj1)).info;
                cachedcomponentargs.flags = ((ActivityInfo) (cachedcomponentargs)).flags | 0x800000;
            }
            flag1 = flag2;
            if(typedarray.getBoolean(52, false))
            {
                cachedcomponentargs = ((Activity) (obj1)).info;
                cachedcomponentargs.flags = ((ActivityInfo) (cachedcomponentargs)).flags | 0x1000000;
                flag1 = flag2;
            }
        } else
        {
            ((Activity) (obj1)).info.launchMode = 0;
            ((Activity) (obj1)).info.configChanges = 0;
            flag1 = flag2;
            if(typedarray.getBoolean(28, false))
            {
                cachedcomponentargs = ((Activity) (obj1)).info;
                cachedcomponentargs.flags = ((ActivityInfo) (cachedcomponentargs)).flags | 0x40000000;
                flag1 = flag2;
                if(((Activity) (obj1)).info.exported)
                {
                    flag1 = flag2;
                    if((i & 0x80) == 0)
                    {
                        Slog.w("PackageParser", (new StringBuilder()).append("Activity exported request ignored due to singleUser: ").append(((Activity) (obj1)).className).append(" at ").append(mArchiveSourcePath).append(" ").append(xmlresourceparser.getPositionDescription()).toString());
                        ((Activity) (obj1)).info.exported = false;
                        flag1 = true;
                    }
                }
            }
            cachedcomponentargs = ((Activity) (obj1)).info;
            flag2 = typedarray.getBoolean(42, false);
            ((Activity) (obj1)).info.directBootAware = flag2;
            cachedcomponentargs.encryptionAware = flag2;
        }
        if(((Activity) (obj1)).info.directBootAware)
        {
            cachedcomponentargs = package1.applicationInfo;
            cachedcomponentargs.privateFlags = ((ApplicationInfo) (cachedcomponentargs)).privateFlags | 0x100;
        }
        flag2 = typedarray.getBoolean(45, false);
        if(flag2)
        {
            cachedcomponentargs = ((Activity) (obj1)).info;
            cachedcomponentargs.flags = ((ActivityInfo) (cachedcomponentargs)).flags | 0x100000;
            package1.visibleToInstantApps = true;
        }
        typedarray.recycle();
        if(flag && (package1.applicationInfo.privateFlags & 2) != 0 && ((Activity) (obj1)).info.processName == package1.packageName)
            as[0] = "Heavy-weight applications can not have receivers in main process";
        if(as[0] != null)
            return null;
        int j = xmlresourceparser.getDepth();
label0:
        do
        {
            i = xmlresourceparser.next();
            if(i == 1 || i == 3 && xmlresourceparser.getDepth() <= j)
                break;
            if(i == 3 || i == 4)
                continue;
            if(xmlresourceparser.getName().equals("intent-filter"))
            {
                ActivityIntentInfo activityintentinfo = new ActivityIntentInfo(((Activity) (obj1)));
                if(!parseIntent(resources, xmlresourceparser, true, true, activityintentinfo, as))
                    return null;
                if(activityintentinfo.countActions() == 0)
                    Slog.w("PackageParser", (new StringBuilder()).append("No actions in intent filter at ").append(mArchiveSourcePath).append(" ").append(xmlresourceparser.getPositionDescription()).toString());
                else
                    ((Activity) (obj1)).intents.add(activityintentinfo);
                if(flag2)
                    i = 1;
                else
                if(!flag && isImplicitlyExposedIntent(activityintentinfo))
                    i = 2;
                else
                    i = 0;
                activityintentinfo.setVisibilityToInstantApp(i);
                if(activityintentinfo.isVisibleToInstantApp())
                {
                    cachedcomponentargs = ((Activity) (obj1)).info;
                    cachedcomponentargs.flags = ((ActivityInfo) (cachedcomponentargs)).flags | 0x100000;
                }
                if(activityintentinfo.isImplicitlyVisibleToInstantApp())
                {
                    cachedcomponentargs = ((Activity) (obj1)).info;
                    cachedcomponentargs.flags = ((ActivityInfo) (cachedcomponentargs)).flags | 0x200000;
                }
                continue;
            }
            if(!flag && xmlresourceparser.getName().equals("preferred"))
            {
                ActivityIntentInfo activityintentinfo1 = new ActivityIntentInfo(((Activity) (obj1)));
                if(!parseIntent(resources, xmlresourceparser, false, false, activityintentinfo1, as))
                    return null;
                if(activityintentinfo1.countActions() == 0)
                {
                    Slog.w("PackageParser", (new StringBuilder()).append("No actions in preferred at ").append(mArchiveSourcePath).append(" ").append(xmlresourceparser.getPositionDescription()).toString());
                } else
                {
                    if(package1.preferredActivityFilters == null)
                        package1.preferredActivityFilters = new ArrayList();
                    package1.preferredActivityFilters.add(activityintentinfo1);
                }
                if(flag2)
                    i = 1;
                else
                if(!flag && isImplicitlyExposedIntent(activityintentinfo1))
                    i = 2;
                else
                    i = 0;
                activityintentinfo1.setVisibilityToInstantApp(i);
                if(activityintentinfo1.isVisibleToInstantApp())
                {
                    cachedcomponentargs = ((Activity) (obj1)).info;
                    cachedcomponentargs.flags = ((ActivityInfo) (cachedcomponentargs)).flags | 0x100000;
                }
                if(activityintentinfo1.isImplicitlyVisibleToInstantApp())
                {
                    cachedcomponentargs = ((Activity) (obj1)).info;
                    cachedcomponentargs.flags = ((ActivityInfo) (cachedcomponentargs)).flags | 0x200000;
                }
                continue;
            }
            if(xmlresourceparser.getName().equals("meta-data"))
            {
                cachedcomponentargs = parseMetaData(resources, xmlresourceparser, ((Activity) (obj1)).metaData, as);
                obj1.metaData = cachedcomponentargs;
                if(cachedcomponentargs == null)
                    return null;
                if(flag2 || !((Activity) (obj1)).metaData.getBoolean("instantapps.clients.allowed"))
                    continue;
                boolean flag4 = true;
                cachedcomponentargs = ((Activity) (obj1)).info;
                cachedcomponentargs.flags = ((ActivityInfo) (cachedcomponentargs)).flags | 0x100000;
                cachedcomponentargs = ((Activity) (obj1)).info;
                cachedcomponentargs.flags = ((ActivityInfo) (cachedcomponentargs)).flags & 0xffdfffff;
                package1.visibleToInstantApps = true;
                for(i = ((Activity) (obj1)).intents.size() - 1; i >= 0; i--)
                    ((ActivityIntentInfo)((Activity) (obj1)).intents.get(i)).setVisibilityToInstantApp(1);

                flag2 = flag4;
                if(package1.preferredActivityFilters == null)
                    continue;
                i = package1.preferredActivityFilters.size() - 1;
                do
                {
                    flag2 = flag4;
                    if(i < 0)
                        continue label0;
                    ((ActivityIntentInfo)package1.preferredActivityFilters.get(i)).setVisibilityToInstantApp(1);
                    i--;
                } while(true);
            }
            if(!flag && xmlresourceparser.getName().equals("layout"))
            {
                parseLayout(resources, xmlresourceparser, ((Activity) (obj1)));
            } else
            {
                Slog.w("PackageParser", (new StringBuilder()).append("Problem in package ").append(mArchiveSourcePath).append(":").toString());
                if(flag)
                    Slog.w("PackageParser", (new StringBuilder()).append("Unknown element under <receiver>: ").append(xmlresourceparser.getName()).append(" at ").append(mArchiveSourcePath).append(" ").append(xmlresourceparser.getPositionDescription()).toString());
                else
                    Slog.w("PackageParser", (new StringBuilder()).append("Unknown element under <activity>: ").append(xmlresourceparser.getName()).append(" at ").append(mArchiveSourcePath).append(" ").append(xmlresourceparser.getPositionDescription()).toString());
                XmlUtils.skipCurrentTag(xmlresourceparser);
            }
        } while(true);
        if(!flag1)
        {
            package1 = ((Activity) (obj1)).info;
            if(((Activity) (obj1)).intents.size() > 0)
                flag = true;
            else
                flag = false;
            package1.exported = flag;
        }
        return ((Activity) (obj1));
    }

    private Activity parseActivityAlias(Package package1, Resources resources, XmlResourceParser xmlresourceparser, int i, String as[], CachedComponentArgs cachedcomponentargs)
        throws XmlPullParserException, IOException
    {
        TypedArray typedarray = resources.obtainAttributes(xmlresourceparser, com.android.internal.R.styleable.AndroidManifestActivityAlias);
        Object obj = typedarray.getNonConfigurationString(7, 1024);
        if(obj == null)
        {
            as[0] = "<activity-alias> does not specify android:targetActivity";
            typedarray.recycle();
            return null;
        }
        String s = buildClassName(package1.applicationInfo.packageName, ((CharSequence) (obj)), as);
        if(s == null)
        {
            typedarray.recycle();
            return null;
        }
        if(cachedcomponentargs.mActivityAliasArgs == null)
        {
            cachedcomponentargs.mActivityAliasArgs = new ParseComponentArgs(package1, as, 2, 0, 1, 11, 8, 10, mSeparateProcesses, 0, 6, 4);
            cachedcomponentargs.mActivityAliasArgs.tag = "<activity-alias>";
        }
        cachedcomponentargs.mActivityAliasArgs.sa = typedarray;
        cachedcomponentargs.mActivityAliasArgs.flags = i;
        Object obj2 = null;
        int j = package1.activities.size();
        i = 0;
label0:
        do
        {
label1:
            {
                obj = obj2;
                if(i < j)
                {
                    obj = (Activity)package1.activities.get(i);
                    if(!s.equals(((Activity) (obj)).info.name))
                        break label1;
                }
                if(obj == null)
                {
                    as[0] = (new StringBuilder()).append("<activity-alias> target activity ").append(s).append(" not found in manifest").toString();
                    typedarray.recycle();
                    return null;
                }
                break label0;
            }
            i++;
        } while(true);
        package1 = new ActivityInfo();
        package1.targetActivity = s;
        package1.configChanges = ((Activity) (obj)).info.configChanges;
        package1.flags = ((Activity) (obj)).info.flags;
        package1.icon = ((Activity) (obj)).info.icon;
        package1.logo = ((Activity) (obj)).info.logo;
        package1.banner = ((Activity) (obj)).info.banner;
        package1.labelRes = ((Activity) (obj)).info.labelRes;
        package1.nonLocalizedLabel = ((Activity) (obj)).info.nonLocalizedLabel;
        package1.launchMode = ((Activity) (obj)).info.launchMode;
        package1.lockTaskLaunchMode = ((Activity) (obj)).info.lockTaskLaunchMode;
        package1.processName = ((Activity) (obj)).info.processName;
        if(((ActivityInfo) (package1)).descriptionRes == 0)
            package1.descriptionRes = ((Activity) (obj)).info.descriptionRes;
        package1.screenOrientation = ((Activity) (obj)).info.screenOrientation;
        package1.taskAffinity = ((Activity) (obj)).info.taskAffinity;
        package1.theme = ((Activity) (obj)).info.theme;
        package1.softInputMode = ((Activity) (obj)).info.softInputMode;
        package1.uiOptions = ((Activity) (obj)).info.uiOptions;
        package1.parentActivityName = ((Activity) (obj)).info.parentActivityName;
        package1.maxRecents = ((Activity) (obj)).info.maxRecents;
        package1.windowLayout = ((Activity) (obj)).info.windowLayout;
        package1.resizeMode = ((Activity) (obj)).info.resizeMode;
        package1.maxAspectRatio = ((Activity) (obj)).info.maxAspectRatio;
        boolean flag1 = ((Activity) (obj)).info.directBootAware;
        package1.directBootAware = flag1;
        package1.encryptionAware = flag1;
        cachedcomponentargs = new Activity(cachedcomponentargs.mActivityAliasArgs, package1);
        if(as[0] != null)
        {
            typedarray.recycle();
            return null;
        }
        flag1 = typedarray.hasValue(5);
        if(flag1)
            ((Activity) (cachedcomponentargs)).info.exported = typedarray.getBoolean(5, false);
        package1 = typedarray.getNonConfigurationString(3, 0);
        boolean flag;
        if(package1 != null)
        {
            Object obj1 = ((Activity) (cachedcomponentargs)).info;
            if(package1.length() > 0)
                package1 = package1.toString().intern();
            else
                package1 = null;
            obj1.permission = package1;
        }
        package1 = typedarray.getNonConfigurationString(9, 1024);
        if(package1 != null)
        {
            obj1 = buildClassName(((Activity) (cachedcomponentargs)).info.packageName, package1, as);
            if(as[0] == null)
            {
                ((Activity) (cachedcomponentargs)).info.parentActivityName = ((String) (obj1));
            } else
            {
                Log.e("PackageParser", (new StringBuilder()).append("Activity alias ").append(((Activity) (cachedcomponentargs)).info.name).append(" specified invalid parentActivityName ").append(package1).toString());
                as[0] = null;
            }
        }
        if((((Activity) (cachedcomponentargs)).info.flags & 0x100000) != 0)
            flag = true;
        else
            flag = false;
        typedarray.recycle();
        if(as[0] != null)
            return null;
        int k = xmlresourceparser.getDepth();
        do
        {
            i = xmlresourceparser.next();
            if(i == 1 || i == 3 && xmlresourceparser.getDepth() <= k)
                break;
            if(i != 3 && i != 4)
                if(xmlresourceparser.getName().equals("intent-filter"))
                {
                    package1 = new ActivityIntentInfo(cachedcomponentargs);
                    if(!parseIntent(resources, xmlresourceparser, true, true, package1, as))
                        return null;
                    if(package1.countActions() == 0)
                        Slog.w("PackageParser", (new StringBuilder()).append("No actions in intent filter at ").append(mArchiveSourcePath).append(" ").append(xmlresourceparser.getPositionDescription()).toString());
                    else
                        ((Activity) (cachedcomponentargs)).intents.add(package1);
                    if(flag)
                        i = 1;
                    else
                    if(isImplicitlyExposedIntent(package1))
                        i = 2;
                    else
                        i = 0;
                    package1.setVisibilityToInstantApp(i);
                    if(package1.isVisibleToInstantApp())
                    {
                        ActivityInfo activityinfo = ((Activity) (cachedcomponentargs)).info;
                        activityinfo.flags = activityinfo.flags | 0x100000;
                    }
                    if(package1.isImplicitlyVisibleToInstantApp())
                    {
                        package1 = ((Activity) (cachedcomponentargs)).info;
                        package1.flags = ((ActivityInfo) (package1)).flags | 0x200000;
                    }
                } else
                if(xmlresourceparser.getName().equals("meta-data"))
                {
                    package1 = parseMetaData(resources, xmlresourceparser, ((Activity) (cachedcomponentargs)).metaData, as);
                    cachedcomponentargs.metaData = package1;
                    if(package1 == null)
                        return null;
                } else
                {
                    Slog.w("PackageParser", (new StringBuilder()).append("Unknown element under <activity-alias>: ").append(xmlresourceparser.getName()).append(" at ").append(mArchiveSourcePath).append(" ").append(xmlresourceparser.getPositionDescription()).toString());
                    XmlUtils.skipCurrentTag(xmlresourceparser);
                }
        } while(true);
        if(!flag1)
        {
            package1 = ((Activity) (cachedcomponentargs)).info;
            boolean flag2;
            if(((Activity) (cachedcomponentargs)).intents.size() > 0)
                flag2 = true;
            else
                flag2 = false;
            package1.exported = flag2;
        }
        return cachedcomponentargs;
    }

    private String[] parseAdditionalCertificates(Resources resources, XmlResourceParser xmlresourceparser, String as[])
        throws XmlPullParserException, IOException
    {
        String as1[] = EmptyArray.STRING;
        int i = xmlresourceparser.getDepth();
        do
        {
            int j = xmlresourceparser.next();
            if(j == 1 || j == 3 && xmlresourceparser.getDepth() <= i)
                break;
            if(j != 3 && j != 4)
                if(xmlresourceparser.getName().equals("additional-certificate"))
                {
                    TypedArray typedarray = resources.obtainAttributes(xmlresourceparser, com.android.internal.R.styleable.AndroidManifestAdditionalCertificate);
                    String s = typedarray.getNonResourceString(0);
                    typedarray.recycle();
                    if(TextUtils.isEmpty(s))
                    {
                        as[0] = (new StringBuilder()).append("Bad additional-certificate declaration with empty certDigest:").append(s).toString();
                        mParseError = -108;
                        XmlUtils.skipCurrentTag(xmlresourceparser);
                        typedarray.recycle();
                        return null;
                    }
                    as1 = (String[])ArrayUtils.appendElement(java/lang/String, as1, s.replace(":", "").toLowerCase());
                } else
                {
                    XmlUtils.skipCurrentTag(xmlresourceparser);
                }
        } while(true);
        return as1;
    }

    private boolean parseAllMetaData(Resources resources, XmlResourceParser xmlresourceparser, String s, Component component, String as[])
        throws XmlPullParserException, IOException
    {
        int i = xmlresourceparser.getDepth();
        do
        {
            int j = xmlresourceparser.next();
            if(j == 1 || j == 3 && xmlresourceparser.getDepth() <= i)
                break;
            if(j != 3 && j != 4)
                if(xmlresourceparser.getName().equals("meta-data"))
                {
                    Bundle bundle = parseMetaData(resources, xmlresourceparser, component.metaData, as);
                    component.metaData = bundle;
                    if(bundle == null)
                        return false;
                } else
                {
                    Slog.w("PackageParser", (new StringBuilder()).append("Unknown element under ").append(s).append(": ").append(xmlresourceparser.getName()).append(" at ").append(mArchiveSourcePath).append(" ").append(xmlresourceparser.getPositionDescription()).toString());
                    XmlUtils.skipCurrentTag(xmlresourceparser);
                }
        } while(true);
        return true;
    }

    public static ApkLite parseApkLite(File file, int i)
        throws PackageParserException
    {
        String s;
        Object obj;
        Object obj1;
        Package package1;
        Object obj2;
        Object obj3;
        Object obj4;
        s = file.getAbsolutePath();
        obj = null;
        obj1 = null;
        package1 = null;
        obj2 = null;
        obj3 = obj2;
        obj4 = package1;
        Object obj5 = newConfiguredAssetManager();
        obj1 = obj5;
        obj3 = obj2;
        obj = obj5;
        obj4 = package1;
        int j = ((AssetManager) (obj5)).addAssetPath(s);
        if(j != 0)
            break MISSING_BLOCK_LABEL_311;
        obj1 = obj5;
        obj3 = obj2;
        obj = obj5;
        obj4 = package1;
        file = JVM INSTR new #53  <Class PackageParser$PackageParserException>;
        obj1 = obj5;
        obj3 = obj2;
        obj = obj5;
        obj4 = package1;
        Object obj6 = JVM INSTR new #465 <Class StringBuilder>;
        obj1 = obj5;
        obj3 = obj2;
        obj = obj5;
        obj4 = package1;
        ((StringBuilder) (obj6)).StringBuilder();
        obj1 = obj5;
        obj3 = obj2;
        obj = obj5;
        obj4 = package1;
        file.PackageParserException(-100, ((StringBuilder) (obj6)).append("Failed to parse ").append(s).toString());
        obj1 = obj5;
        obj3 = obj2;
        obj = obj5;
        obj4 = package1;
        try
        {
            throw file;
        }
        // Misplaced declaration of an exception variable
        catch(File file)
        {
            obj = obj1;
        }
        obj4 = obj3;
        obj5 = JVM INSTR new #465 <Class StringBuilder>;
        obj = obj1;
        obj4 = obj3;
        ((StringBuilder) (obj5)).StringBuilder();
        obj = obj1;
        obj4 = obj3;
        Slog.w("PackageParser", ((StringBuilder) (obj5)).append("Failed to parse ").append(s).toString(), file);
        obj = obj1;
        obj4 = obj3;
        obj5 = JVM INSTR new #53  <Class PackageParser$PackageParserException>;
        obj = obj1;
        obj4 = obj3;
        obj2 = JVM INSTR new #465 <Class StringBuilder>;
        obj = obj1;
        obj4 = obj3;
        ((StringBuilder) (obj2)).StringBuilder();
        obj = obj1;
        obj4 = obj3;
        ((PackageParserException) (obj5)).PackageParserException(-102, ((StringBuilder) (obj2)).append("Failed to parse ").append(s).toString(), file);
        obj = obj1;
        obj4 = obj3;
        throw obj5;
        file;
        IoUtils.closeQuietly(((AutoCloseable) (obj4)));
        IoUtils.closeQuietly(((AutoCloseable) (obj)));
        throw file;
        obj1 = obj5;
        obj3 = obj2;
        obj = obj5;
        obj4 = package1;
        obj6 = JVM INSTR new #415 <Class DisplayMetrics>;
        obj1 = obj5;
        obj3 = obj2;
        obj = obj5;
        obj4 = package1;
        ((DisplayMetrics) (obj6)).DisplayMetrics();
        obj1 = obj5;
        obj3 = obj2;
        obj = obj5;
        obj4 = package1;
        ((DisplayMetrics) (obj6)).setToDefaults();
        obj1 = obj5;
        obj3 = obj2;
        obj = obj5;
        obj4 = package1;
        obj2 = ((AssetManager) (obj5)).openXmlResourceParser(j, "AndroidManifest.xml");
        if((i & 0x100) == 0) goto _L2; else goto _L1
_L1:
        obj1 = obj5;
        obj3 = obj2;
        obj = obj5;
        obj4 = obj2;
        package1 = JVM INSTR new #45  <Class PackageParser$Package>;
        obj1 = obj5;
        obj3 = obj2;
        obj = obj5;
        obj4 = obj2;
        package1.Package((String)null);
        obj1 = obj5;
        obj3 = obj2;
        obj = obj5;
        obj4 = obj2;
        Trace.traceBegin(0x40000L, "collectCertificates");
        collectCertificates(package1, file, i);
        obj1 = obj5;
        obj3 = obj2;
        obj = obj5;
        obj4 = obj2;
        Trace.traceEnd(0x40000L);
        obj1 = obj5;
        obj3 = obj2;
        obj = obj5;
        obj4 = obj2;
        file = package1.mSignatures;
        obj1 = obj5;
        obj3 = obj2;
        obj = obj5;
        obj4 = obj2;
        Certificate acertificate[][] = package1.mCertificates;
_L4:
        obj1 = obj5;
        obj3 = obj2;
        obj = obj5;
        obj4 = obj2;
        file = parseApkLite(s, ((XmlPullParser) (obj2)), ((AttributeSet) (obj2)), ((Signature []) (file)), acertificate);
        IoUtils.closeQuietly(((AutoCloseable) (obj2)));
        IoUtils.closeQuietly(((AutoCloseable) (obj5)));
        return file;
        file;
        obj1 = obj5;
        obj3 = obj2;
        obj = obj5;
        obj4 = obj2;
        Trace.traceEnd(0x40000L);
        obj1 = obj5;
        obj3 = obj2;
        obj = obj5;
        obj4 = obj2;
        throw file;
_L2:
        file = null;
        acertificate = null;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static ApkLite parseApkLite(String s, XmlPullParser xmlpullparser, AttributeSet attributeset, Signature asignature[], Certificate acertificate[][])
        throws IOException, XmlPullParserException, PackageParserException
    {
        Pair pair = parsePackageSplitNames(xmlpullparser, attributeset);
        int i = -1;
        int j = 0;
        int k = 0;
        boolean flag = false;
        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = false;
        boolean flag4 = true;
        boolean flag5 = false;
        boolean flag6 = false;
        String s1 = null;
        Object obj = null;
        int l = 0;
        while(l < attributeset.getAttributeCount()) 
        {
            String s4 = attributeset.getAttributeName(l);
            int k1;
            boolean flag7;
            boolean flag9;
            int l1;
            int i2;
            String s5;
            if(s4.equals("installLocation"))
            {
                k1 = attributeset.getAttributeIntValue(l, -1);
                flag7 = flag5;
                flag9 = flag;
                l1 = k;
                i2 = j;
                s5 = s1;
            } else
            if(s4.equals("versionCode"))
            {
                i2 = attributeset.getAttributeIntValue(l, 0);
                s5 = s1;
                l1 = k;
                k1 = i;
                flag9 = flag;
                flag7 = flag5;
            } else
            if(s4.equals("revisionCode"))
            {
                l1 = attributeset.getAttributeIntValue(l, 0);
                s5 = s1;
                i2 = j;
                k1 = i;
                flag9 = flag;
                flag7 = flag5;
            } else
            if(s4.equals("coreApp"))
            {
                flag9 = attributeset.getAttributeBooleanValue(l, false);
                s5 = s1;
                i2 = j;
                l1 = k;
                k1 = i;
                flag7 = flag5;
            } else
            if(s4.equals("isolatedSplits"))
            {
                flag7 = attributeset.getAttributeBooleanValue(l, false);
                s5 = s1;
                i2 = j;
                l1 = k;
                k1 = i;
                flag9 = flag;
            } else
            if(s4.equals("configForSplit"))
            {
                s5 = attributeset.getAttributeValue(l);
                i2 = j;
                l1 = k;
                k1 = i;
                flag9 = flag;
                flag7 = flag5;
            } else
            {
                s5 = s1;
                i2 = j;
                l1 = k;
                k1 = i;
                flag9 = flag;
                flag7 = flag5;
                if(s4.equals("isFeatureSplit"))
                {
                    flag6 = attributeset.getAttributeBooleanValue(l, false);
                    s5 = s1;
                    i2 = j;
                    l1 = k;
                    k1 = i;
                    flag9 = flag;
                    flag7 = flag5;
                }
            }
            l++;
            s1 = s5;
            j = i2;
            k = l1;
            i = k1;
            flag = flag9;
            flag5 = flag7;
        }
        int j2 = xmlpullparser.getDepth() + 1;
        ArrayList arraylist = new ArrayList();
        String s6 = obj;
label0:
        do
        {
            int i1 = xmlpullparser.next();
            if(i1 == 1 || i1 == 3 && xmlpullparser.getDepth() < j2)
                break;
            if(i1 == 3 || i1 == 4 || xmlpullparser.getDepth() != j2)
                continue;
            if("package-verifier".equals(xmlpullparser.getName()))
            {
                VerifierInfo verifierinfo = parseVerifier(attributeset);
                if(verifierinfo != null)
                    arraylist.add(verifierinfo);
                continue;
            }
            if("application".equals(xmlpullparser.getName()))
            {
                int j1 = 0;
                boolean flag10 = flag4;
                boolean flag8 = flag3;
                boolean flag11 = flag2;
                boolean flag12 = flag1;
                do
                {
                    flag1 = flag12;
                    flag2 = flag11;
                    flag3 = flag8;
                    flag4 = flag10;
                    if(j1 >= attributeset.getAttributeCount())
                        continue label0;
                    String s2 = attributeset.getAttributeName(j1);
                    if("debuggable".equals(s2))
                        flag12 = attributeset.getAttributeBooleanValue(j1, false);
                    if("multiArch".equals(s2))
                        flag11 = attributeset.getAttributeBooleanValue(j1, false);
                    if("use32bitAbi".equals(s2))
                        flag8 = attributeset.getAttributeBooleanValue(j1, false);
                    if("extractNativeLibs".equals(s2))
                        flag10 = attributeset.getAttributeBooleanValue(j1, true);
                    j1++;
                } while(true);
            }
            if("uses-split".equals(xmlpullparser.getName()))
                if(s6 != null)
                {
                    Slog.w("PackageParser", "Only one <uses-split> permitted. Ignoring others.");
                } else
                {
                    String s3 = attributeset.getAttributeValue("http://schemas.android.com/apk/res/android", "name");
                    s6 = s3;
                    if(s3 == null)
                        throw new PackageParserException(-108, "<uses-split> tag requires 'android:name' attribute");
                }
        } while(true);
        return new ApkLite(s, (String)pair.first, (String)pair.second, flag6, s1, s6, j, k, i, arraylist, asignature, acertificate, flag, flag1, flag2, flag3, flag4, flag5);
    }

    private Package parseBaseApk(File file, AssetManager assetmanager, int i)
        throws PackageParserException
    {
        String s;
        Object obj;
        int k;
        Object obj1;
        Object obj2;
        AssetManager assetmanager1;
        String as[];
        Object obj3;
        s = file.getAbsolutePath();
        obj = null;
        if(s.startsWith("/mnt/expand/"))
        {
            int j = s.indexOf('/', "/mnt/expand/".length());
            obj = s.substring("/mnt/expand/".length(), j);
        }
        mParseError = 1;
        mArchiveSourcePath = file.getAbsolutePath();
        k = loadApkIntoAssetManager(assetmanager, s, i);
        file = null;
        obj1 = null;
        obj2 = null;
        assetmanager1 = null;
        as = null;
        obj3 = null;
        MiuiResources miuiresources;
        miuiresources = JVM INSTR new #1888 <Class MiuiResources>;
        miuiresources.MiuiResources(assetmanager, mMetrics, null);
        obj2 = obj3;
        file = ((File) (obj1));
        assetmanager1 = as;
        assetmanager = assetmanager.openXmlResourceParser(k, "AndroidManifest.xml");
        obj2 = assetmanager;
        file = assetmanager;
        assetmanager1 = assetmanager;
        as = new String[1];
        obj2 = assetmanager;
        file = assetmanager;
        assetmanager1 = assetmanager;
        obj1 = parseBaseApk(s, ((Resources) (miuiresources)), ((XmlResourceParser) (assetmanager)), i, as);
        if(obj1 != null) goto _L2; else goto _L1
_L1:
        obj2 = assetmanager;
        file = assetmanager;
        assetmanager1 = assetmanager;
        obj = JVM INSTR new #53  <Class PackageParser$PackageParserException>;
        obj2 = assetmanager;
        file = assetmanager;
        assetmanager1 = assetmanager;
        i = mParseError;
        obj2 = assetmanager;
        file = assetmanager;
        assetmanager1 = assetmanager;
        obj1 = JVM INSTR new #465 <Class StringBuilder>;
        obj2 = assetmanager;
        file = assetmanager;
        assetmanager1 = assetmanager;
        ((StringBuilder) (obj1)).StringBuilder();
        obj2 = assetmanager;
        file = assetmanager;
        assetmanager1 = assetmanager;
        ((PackageParserException) (obj)).PackageParserException(i, ((StringBuilder) (obj1)).append(s).append(" (at ").append(assetmanager.getPositionDescription()).append("): ").append(as[0]).toString());
        obj2 = assetmanager;
        file = assetmanager;
        assetmanager1 = assetmanager;
        throw obj;
        assetmanager;
        file = ((File) (obj2));
_L4:
        throw assetmanager;
        obj2;
        assetmanager = file;
        file = ((File) (obj2));
_L3:
        IoUtils.closeQuietly(assetmanager);
        throw file;
_L2:
        obj2 = assetmanager;
        file = assetmanager;
        assetmanager1 = assetmanager;
        ((Package) (obj1)).setVolumeUuid(((String) (obj)));
        obj2 = assetmanager;
        file = assetmanager;
        assetmanager1 = assetmanager;
        ((Package) (obj1)).setApplicationVolumeUuid(((String) (obj)));
        obj2 = assetmanager;
        file = assetmanager;
        assetmanager1 = assetmanager;
        ((Package) (obj1)).setBaseCodePath(s);
        obj2 = assetmanager;
        file = assetmanager;
        assetmanager1 = assetmanager;
        ((Package) (obj1)).setSignatures(null);
        IoUtils.closeQuietly(assetmanager);
        return ((Package) (obj1));
        assetmanager;
_L5:
        file = assetmanager1;
        obj2 = JVM INSTR new #53  <Class PackageParser$PackageParserException>;
        file = assetmanager1;
        obj = JVM INSTR new #465 <Class StringBuilder>;
        file = assetmanager1;
        ((StringBuilder) (obj)).StringBuilder();
        file = assetmanager1;
        ((PackageParserException) (obj2)).PackageParserException(-102, ((StringBuilder) (obj)).append("Failed to read manifest from ").append(s).toString(), assetmanager);
        file = assetmanager1;
        throw obj2;
        file;
        assetmanager = ((AssetManager) (obj2));
          goto _L3
        assetmanager;
          goto _L4
        assetmanager;
          goto _L5
    }

    private Package parseBaseApk(String s, Resources resources, XmlResourceParser xmlresourceparser, int i, String as[])
        throws XmlPullParserException, IOException
    {
        Object obj1;
label0:
        {
            try
            {
                Object obj = parsePackageSplitNames(xmlresourceparser, xmlresourceparser);
                obj1 = (String)((Pair) (obj)).first;
                obj = (String)((Pair) (obj)).second;
                if(TextUtils.isEmpty(((CharSequence) (obj))))
                    break label0;
                s = JVM INSTR new #465 <Class StringBuilder>;
                s.StringBuilder();
                as[0] = s.append("Expected base APK, but found split ").append(((String) (obj))).toString();
                mParseError = -106;
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                mParseError = -106;
                return null;
            }
            return null;
        }
        if(mCallback != null)
        {
            String as1[] = mCallback.getOverlayPaths(((String) (obj1)), s);
            if(as1 != null && as1.length > 0)
            {
                int j = 0;
                for(int l = as1.length; j < l; j++)
                {
                    s = as1[j];
                    resources.getAssets().addOverlayPath(s);
                }

            }
        }
        s = new Package(((String) (obj1)));
        obj1 = resources.obtainAttributes(xmlresourceparser, com.android.internal.R.styleable.AndroidManifest);
        int k = ((TypedArray) (obj1)).getInteger(1, 0);
        ((Package) (s)).applicationInfo.versionCode = k;
        s.mVersionCode = k;
        s.baseRevisionCode = ((TypedArray) (obj1)).getInteger(5, 0);
        s.mVersionName = ((TypedArray) (obj1)).getNonConfigurationString(2, 0);
        if(((Package) (s)).mVersionName != null)
            s.mVersionName = ((Package) (s)).mVersionName.intern();
        s.coreApp = xmlresourceparser.getAttributeBooleanValue(null, "coreApp", false);
        ((TypedArray) (obj1)).recycle();
        return parseBaseApkCommon(s, null, resources, xmlresourceparser, i, as);
    }

    private boolean parseBaseApkChild(Package package1, Resources resources, XmlResourceParser xmlresourceparser, int i, String as[])
        throws XmlPullParserException, IOException
    {
        if(package1.childPackages != null && package1.childPackages.size() + 2 > 5)
        {
            as[0] = "Maximum number of packages per APK is: 5";
            mParseError = -108;
            return false;
        }
        Object obj = xmlresourceparser.getAttributeValue(null, "package");
        if(validateName(((String) (obj)), true, false) != null)
        {
            mParseError = -106;
            return false;
        }
        if(((String) (obj)).equals(package1.packageName))
        {
            package1 = (new StringBuilder()).append("Child package name cannot be equal to parent package name: ").append(package1.packageName).toString();
            Slog.w("PackageParser", package1);
            as[0] = package1;
            mParseError = -108;
            return false;
        }
        if(package1.hasChildPackage(((String) (obj))))
        {
            package1 = (new StringBuilder()).append("Duplicate child package:").append(((String) (obj))).toString();
            Slog.w("PackageParser", package1);
            as[0] = package1;
            mParseError = -108;
            return false;
        }
        obj = new Package(((String) (obj)));
        obj.mVersionCode = package1.mVersionCode;
        obj.baseRevisionCode = package1.baseRevisionCode;
        obj.mVersionName = package1.mVersionName;
        ((Package) (obj)).applicationInfo.targetSdkVersion = package1.applicationInfo.targetSdkVersion;
        ((Package) (obj)).applicationInfo.minSdkVersion = package1.applicationInfo.minSdkVersion;
        resources = parseBaseApkCommon(((Package) (obj)), CHILD_PACKAGE_TAGS, resources, xmlresourceparser, i, as);
        if(resources == null)
            return false;
        if(package1.childPackages == null)
            package1.childPackages = new ArrayList();
        package1.childPackages.add(resources);
        resources.parentPackage = package1;
        return true;
    }

    private Package parseBaseApkCommon(Package package1, Set set, Resources resources, XmlResourceParser xmlresourceparser, int i, String as[])
        throws XmlPullParserException, IOException
    {
        mParseInstrumentationArgs = null;
        boolean flag = false;
        TypedArray typedarray = resources.obtainAttributes(xmlresourceparser, com.android.internal.R.styleable.AndroidManifest);
        String s1 = typedarray.getNonConfigurationString(0, 0);
        if(s1 != null && s1.length() > 0)
        {
            if((i & 0x800) != 0)
            {
                as[0] = "sharedUserId not allowed in ephemeral application";
                mParseError = -107;
                return null;
            }
            String s3 = validateName(s1, true, false);
            if(s3 != null && "android".equals(package1.packageName) ^ true)
            {
                as[0] = (new StringBuilder()).append("<manifest> specifies bad sharedUserId name \"").append(s1).append("\": ").append(s3).toString();
                mParseError = -107;
                return null;
            }
            package1.mSharedUserId = s1.intern();
            package1.mSharedUserLabel = typedarray.getResourceId(3, 0);
        }
        package1.installLocation = typedarray.getInteger(4, -1);
        package1.applicationInfo.installLocation = package1.installLocation;
        int k = typedarray.getInteger(7, 1);
        package1.applicationInfo.targetSandboxVersion = k;
        if((i & 0x10) != 0)
        {
            ApplicationInfo applicationinfo1 = package1.applicationInfo;
            applicationinfo1.privateFlags = applicationinfo1.privateFlags | 4;
        }
        if((i & 0x20) != 0)
        {
            ApplicationInfo applicationinfo2 = package1.applicationInfo;
            applicationinfo2.flags = applicationinfo2.flags | 0x40000;
        }
        if(typedarray.getBoolean(6, false))
        {
            ApplicationInfo applicationinfo = package1.applicationInfo;
            applicationinfo.privateFlags = applicationinfo.privateFlags | 0x8000;
        }
        int i1 = 1;
        int j1 = 1;
        int k1 = 1;
        int l1 = 1;
        int i2 = 1;
        int j2 = 1;
        int k2 = xmlresourceparser.getDepth();
        do
        {
            k = xmlresourceparser.next();
            if(k == 1 || k == 3 && xmlresourceparser.getDepth() <= k2)
                break;
            if(k != 3 && k != 4)
            {
                Object obj = xmlresourceparser.getName();
                if(set != null && set.contains(obj) ^ true)
                {
                    Slog.w("PackageParser", (new StringBuilder()).append("Skipping unsupported element under <manifest>: ").append(((String) (obj))).append(" at ").append(mArchiveSourcePath).append(" ").append(xmlresourceparser.getPositionDescription()).toString());
                    XmlUtils.skipCurrentTag(xmlresourceparser);
                } else
                if(((String) (obj)).equals("application"))
                {
                    if(flag)
                    {
                        Slog.w("PackageParser", "<manifest> has more than one <application>");
                        XmlUtils.skipCurrentTag(xmlresourceparser);
                    } else
                    {
                        flag = true;
                        if(!parseBaseApplication(package1, resources, xmlresourceparser, i, as))
                            return null;
                    }
                } else
                if(((String) (obj)).equals("overlay"))
                {
                    TypedArray typedarray1 = resources.obtainAttributes(xmlresourceparser, com.android.internal.R.styleable.AndroidManifestResourceOverlay);
                    package1.mOverlayTarget = typedarray1.getString(1);
                    package1.mOverlayPriority = typedarray1.getInt(0, 0);
                    package1.mIsStaticOverlay = typedarray1.getBoolean(2, false);
                    String s2 = typedarray1.getString(3);
                    obj = typedarray1.getString(4);
                    typedarray1.recycle();
                    if(package1.mOverlayTarget == null)
                    {
                        as[0] = "<overlay> does not specify a target package";
                        mParseError = -108;
                        return null;
                    }
                    if(package1.mOverlayPriority < 0 || package1.mOverlayPriority > 9999)
                    {
                        as[0] = "<overlay> priority must be between 0 and 9999";
                        mParseError = -108;
                        return null;
                    }
                    if(!checkOverlayRequiredSystemProperty(s2, ((String) (obj))))
                    {
                        Slog.i("PackageParser", (new StringBuilder()).append("Skipping target and overlay pair ").append(package1.mOverlayTarget).append(" and ").append(package1.baseCodePath).append(": overlay ignored due to required system property: ").append(s2).append(" with value: ").append(((String) (obj))).toString());
                        return null;
                    }
                    XmlUtils.skipCurrentTag(xmlresourceparser);
                } else
                if(((String) (obj)).equals("key-sets"))
                {
                    if(!parseKeySets(package1, resources, xmlresourceparser, as))
                        return null;
                } else
                if(((String) (obj)).equals("permission-group"))
                {
                    if(!parsePermissionGroup(package1, i, resources, xmlresourceparser, as))
                        return null;
                } else
                if(((String) (obj)).equals("permission"))
                {
                    if(!parsePermission(package1, resources, xmlresourceparser, as))
                        return null;
                } else
                if(((String) (obj)).equals("permission-tree"))
                {
                    if(!parsePermissionTree(package1, resources, xmlresourceparser, as))
                        return null;
                } else
                if(((String) (obj)).equals("uses-permission"))
                {
                    if(!parseUsesPermission(package1, resources, xmlresourceparser))
                        return null;
                } else
                if(((String) (obj)).equals("uses-permission-sdk-m") || ((String) (obj)).equals("uses-permission-sdk-23"))
                {
                    if(!parseUsesPermission(package1, resources, xmlresourceparser))
                        return null;
                } else
                if(((String) (obj)).equals("uses-configuration"))
                {
                    obj = new ConfigurationInfo();
                    TypedArray typedarray2 = resources.obtainAttributes(xmlresourceparser, com.android.internal.R.styleable.AndroidManifestUsesConfiguration);
                    obj.reqTouchScreen = typedarray2.getInt(0, 0);
                    obj.reqKeyboardType = typedarray2.getInt(1, 0);
                    if(typedarray2.getBoolean(2, false))
                        obj.reqInputFeatures = ((ConfigurationInfo) (obj)).reqInputFeatures | 1;
                    obj.reqNavigation = typedarray2.getInt(3, 0);
                    if(typedarray2.getBoolean(4, false))
                        obj.reqInputFeatures = ((ConfigurationInfo) (obj)).reqInputFeatures | 2;
                    typedarray2.recycle();
                    package1.configPreferences = ArrayUtils.add(package1.configPreferences, obj);
                    XmlUtils.skipCurrentTag(xmlresourceparser);
                } else
                if(((String) (obj)).equals("uses-feature"))
                {
                    FeatureInfo featureinfo = parseUsesFeature(resources, xmlresourceparser);
                    package1.reqFeatures = ArrayUtils.add(package1.reqFeatures, featureinfo);
                    if(featureinfo.name == null)
                    {
                        obj = new ConfigurationInfo();
                        obj.reqGlEsVersion = featureinfo.reqGlEsVersion;
                        package1.configPreferences = ArrayUtils.add(package1.configPreferences, obj);
                    }
                    XmlUtils.skipCurrentTag(xmlresourceparser);
                } else
                if(((String) (obj)).equals("feature-group"))
                {
                    FeatureGroupInfo featuregroupinfo = new FeatureGroupInfo();
                    obj = null;
                    k = xmlresourceparser.getDepth();
                    do
                    {
                        int l2 = xmlresourceparser.next();
                        if(l2 == 1 || l2 == 3 && xmlresourceparser.getDepth() <= k)
                            break;
                        if(l2 != 3 && l2 != 4)
                        {
                            Object obj1 = xmlresourceparser.getName();
                            if(((String) (obj1)).equals("uses-feature"))
                            {
                                obj1 = parseUsesFeature(resources, xmlresourceparser);
                                obj1.flags = ((FeatureInfo) (obj1)).flags | 1;
                                obj = ArrayUtils.add(((ArrayList) (obj)), obj1);
                            } else
                            {
                                Slog.w("PackageParser", (new StringBuilder()).append("Unknown element under <feature-group>: ").append(((String) (obj1))).append(" at ").append(mArchiveSourcePath).append(" ").append(xmlresourceparser.getPositionDescription()).toString());
                            }
                            XmlUtils.skipCurrentTag(xmlresourceparser);
                        }
                    } while(true);
                    if(obj != null)
                    {
                        featuregroupinfo.features = new FeatureInfo[((ArrayList) (obj)).size()];
                        featuregroupinfo.features = (FeatureInfo[])((ArrayList) (obj)).toArray(featuregroupinfo.features);
                    }
                    package1.featureGroups = ArrayUtils.add(package1.featureGroups, featuregroupinfo);
                } else
                if(((String) (obj)).equals("uses-sdk"))
                {
                    if(SDK_VERSION > 0)
                    {
                        TypedArray typedarray4 = resources.obtainAttributes(xmlresourceparser, com.android.internal.R.styleable.AndroidManifestUsesSdk);
                        int k3 = 1;
                        Object obj4 = null;
                        boolean flag1 = false;
                        Object obj2 = null;
                        TypedValue typedvalue = typedarray4.peekValue(0);
                        Object obj3 = obj4;
                        int i3 = k3;
                        obj = obj2;
                        k = ((flag1) ? 1 : 0);
                        if(typedvalue != null)
                            if(typedvalue.type == 3 && typedvalue.string != null)
                            {
                                obj3 = typedvalue.string.toString();
                                obj = obj3;
                                k = ((flag1) ? 1 : 0);
                                i3 = k3;
                            } else
                            {
                                i3 = typedvalue.data;
                                k = i3;
                                obj3 = obj4;
                                obj = obj2;
                            }
                        typedvalue = typedarray4.peekValue(1);
                        obj4 = obj3;
                        obj2 = obj;
                        k3 = k;
                        if(typedvalue != null)
                            if(typedvalue.type == 3 && typedvalue.string != null)
                            {
                                obj = typedvalue.string.toString();
                                obj4 = obj3;
                                obj2 = obj;
                                k3 = k;
                                if(obj3 == null)
                                {
                                    obj4 = obj;
                                    k3 = k;
                                    obj2 = obj;
                                }
                            } else
                            {
                                k3 = typedvalue.data;
                                obj4 = obj3;
                                obj2 = obj;
                            }
                        typedarray4.recycle();
                        k = computeMinSdkVersion(i3, ((String) (obj4)), SDK_VERSION, SDK_CODENAMES, as);
                        if(k < 0)
                        {
                            mParseError = -12;
                            return null;
                        }
                        i3 = computeTargetSdkVersion(k3, ((String) (obj2)), SDK_VERSION, SDK_CODENAMES, as);
                        if(i3 < 0)
                        {
                            mParseError = -12;
                            return null;
                        }
                        package1.applicationInfo.minSdkVersion = k;
                        package1.applicationInfo.targetSdkVersion = i3;
                    }
                    XmlUtils.skipCurrentTag(xmlresourceparser);
                } else
                if(((String) (obj)).equals("supports-screens"))
                {
                    obj = resources.obtainAttributes(xmlresourceparser, com.android.internal.R.styleable.AndroidManifestSupportsScreens);
                    package1.applicationInfo.requiresSmallestWidthDp = ((TypedArray) (obj)).getInteger(6, 0);
                    package1.applicationInfo.compatibleWidthLimitDp = ((TypedArray) (obj)).getInteger(7, 0);
                    package1.applicationInfo.largestWidthLimitDp = ((TypedArray) (obj)).getInteger(8, 0);
                    i1 = ((TypedArray) (obj)).getInteger(1, i1);
                    j1 = ((TypedArray) (obj)).getInteger(2, j1);
                    k1 = ((TypedArray) (obj)).getInteger(3, k1);
                    l1 = ((TypedArray) (obj)).getInteger(5, l1);
                    i2 = ((TypedArray) (obj)).getInteger(4, i2);
                    j2 = ((TypedArray) (obj)).getInteger(0, j2);
                    ((TypedArray) (obj)).recycle();
                    XmlUtils.skipCurrentTag(xmlresourceparser);
                } else
                if(((String) (obj)).equals("protected-broadcast"))
                {
                    obj = resources.obtainAttributes(xmlresourceparser, com.android.internal.R.styleable.AndroidManifestProtectedBroadcast);
                    String s4 = ((TypedArray) (obj)).getNonResourceString(0);
                    ((TypedArray) (obj)).recycle();
                    if(s4 != null && (i & 1) != 0)
                    {
                        if(package1.protectedBroadcasts == null)
                            package1.protectedBroadcasts = new ArrayList();
                        if(!package1.protectedBroadcasts.contains(s4))
                            package1.protectedBroadcasts.add(s4.intern());
                    }
                    XmlUtils.skipCurrentTag(xmlresourceparser);
                } else
                if(((String) (obj)).equals("instrumentation"))
                {
                    if(parseInstrumentation(package1, resources, xmlresourceparser, as) == null)
                        return null;
                } else
                if(((String) (obj)).equals("original-package"))
                {
                    obj = resources.obtainAttributes(xmlresourceparser, com.android.internal.R.styleable.AndroidManifestOriginalPackage);
                    String s5 = ((TypedArray) (obj)).getNonConfigurationString(0, 0);
                    if(!package1.packageName.equals(s5))
                    {
                        if(package1.mOriginalPackages == null)
                        {
                            package1.mOriginalPackages = new ArrayList();
                            package1.mRealPackage = package1.packageName;
                        }
                        package1.mOriginalPackages.add(s5);
                    }
                    ((TypedArray) (obj)).recycle();
                    XmlUtils.skipCurrentTag(xmlresourceparser);
                } else
                if(((String) (obj)).equals("adopt-permissions"))
                {
                    obj = resources.obtainAttributes(xmlresourceparser, com.android.internal.R.styleable.AndroidManifestOriginalPackage);
                    String s6 = ((TypedArray) (obj)).getNonConfigurationString(0, 0);
                    ((TypedArray) (obj)).recycle();
                    if(s6 != null)
                    {
                        if(package1.mAdoptPermissions == null)
                            package1.mAdoptPermissions = new ArrayList();
                        package1.mAdoptPermissions.add(s6);
                    }
                    XmlUtils.skipCurrentTag(xmlresourceparser);
                } else
                if(((String) (obj)).equals("uses-gl-texture"))
                    XmlUtils.skipCurrentTag(xmlresourceparser);
                else
                if(((String) (obj)).equals("compatible-screens"))
                    XmlUtils.skipCurrentTag(xmlresourceparser);
                else
                if(((String) (obj)).equals("supports-input"))
                    XmlUtils.skipCurrentTag(xmlresourceparser);
                else
                if(((String) (obj)).equals("eat-comment"))
                    XmlUtils.skipCurrentTag(xmlresourceparser);
                else
                if(((String) (obj)).equals("package"))
                {
                    if(!MULTI_PACKAGE_APK_ENABLED)
                        XmlUtils.skipCurrentTag(xmlresourceparser);
                    else
                    if(!parseBaseApkChild(package1, resources, xmlresourceparser, i, as))
                        return null;
                } else
                if(((String) (obj)).equals("restrict-update"))
                {
                    if((i & 0x40) != 0)
                    {
                        TypedArray typedarray3 = resources.obtainAttributes(xmlresourceparser, com.android.internal.R.styleable.AndroidManifestRestrictUpdate);
                        String s = typedarray3.getNonConfigurationString(0, 0);
                        typedarray3.recycle();
                        package1.restrictUpdateHash = null;
                        if(s != null)
                        {
                            int j3 = s.length();
                            byte abyte0[] = new byte[j3 / 2];
                            for(k = 0; k < j3; k += 2)
                                abyte0[k / 2] = (byte)((Character.digit(s.charAt(k), 16) << 4) + Character.digit(s.charAt(k + 1), 16));

                            package1.restrictUpdateHash = abyte0;
                        }
                    }
                    XmlUtils.skipCurrentTag(xmlresourceparser);
                } else
                {
                    Slog.w("PackageParser", (new StringBuilder()).append("Unknown element under <manifest>: ").append(xmlresourceparser.getName()).append(" at ").append(mArchiveSourcePath).append(" ").append(xmlresourceparser.getPositionDescription()).toString());
                    XmlUtils.skipCurrentTag(xmlresourceparser);
                }
            }
        } while(true);
        if(!flag && package1.instrumentation.size() == 0)
        {
            as[0] = "<manifest> does not contain an <application> or <instrumentation>";
            mParseError = -109;
        }
        k = NEW_PERMISSIONS.length;
        set = null;
        i = 0;
        do
        {
label0:
            {
label1:
                {
                    if(i < k)
                    {
                        xmlresourceparser = NEW_PERMISSIONS[i];
                        if(package1.applicationInfo.targetSdkVersion < ((NewPermissionInfo) (xmlresourceparser)).sdkVersion)
                            break label1;
                    }
                    if(set != null)
                        Slog.i("PackageParser", set.toString());
                    int j = SPLIT_PERMISSIONS.length;
                    i = 0;
                    while(i < j) 
                    {
                        resources = SPLIT_PERMISSIONS[i];
                        if(package1.applicationInfo.targetSdkVersion < ((SplitPermissionInfo) (resources)).targetSdk && !(package1.requestedPermissions.contains(((SplitPermissionInfo) (resources)).rootPerm) ^ true))
                        {
                            int l = 0;
                            while(l < ((SplitPermissionInfo) (resources)).newPerms.length) 
                            {
                                set = ((SplitPermissionInfo) (resources)).newPerms[l];
                                if(!package1.requestedPermissions.contains(set))
                                    package1.requestedPermissions.add(set);
                                l++;
                            }
                        }
                        i++;
                    }
                    break label0;
                }
                resources = set;
                if(!package1.requestedPermissions.contains(((NewPermissionInfo) (xmlresourceparser)).name))
                {
                    if(set == null)
                    {
                        set = new StringBuilder(128);
                        set.append(package1.packageName);
                        set.append(": compat added ");
                    } else
                    {
                        set.append(' ');
                    }
                    set.append(((NewPermissionInfo) (xmlresourceparser)).name);
                    package1.requestedPermissions.add(((NewPermissionInfo) (xmlresourceparser)).name);
                    resources = set;
                }
                i++;
                set = resources;
                continue;
            }
            if(i1 < 0 || i1 > 0 && package1.applicationInfo.targetSdkVersion >= 4)
            {
                set = package1.applicationInfo;
                set.flags = ((ApplicationInfo) (set)).flags | 0x200;
            }
            if(j1 != 0)
            {
                set = package1.applicationInfo;
                set.flags = ((ApplicationInfo) (set)).flags | 0x400;
            }
            if(k1 < 0 || k1 > 0 && package1.applicationInfo.targetSdkVersion >= 4)
            {
                set = package1.applicationInfo;
                set.flags = ((ApplicationInfo) (set)).flags | 0x800;
            }
            if(l1 < 0 || l1 > 0 && package1.applicationInfo.targetSdkVersion >= 9)
            {
                set = package1.applicationInfo;
                set.flags = ((ApplicationInfo) (set)).flags | 0x80000;
            }
            if(i2 < 0 || i2 > 0 && package1.applicationInfo.targetSdkVersion >= 4)
            {
                set = package1.applicationInfo;
                set.flags = ((ApplicationInfo) (set)).flags | 0x1000;
            }
            if(j2 < 0 || j2 > 0 && package1.applicationInfo.targetSdkVersion >= 4)
            {
                set = package1.applicationInfo;
                set.flags = ((ApplicationInfo) (set)).flags | 0x2000;
            }
            if(package1.applicationInfo.usesCompatibilityMode())
                adjustPackageToBeUnresizeableAndUnpipable(package1);
            return package1;
        } while(true);
    }

    private boolean parseBaseApplication(Package package1, Resources resources, XmlResourceParser xmlresourceparser, int i, String as[])
        throws XmlPullParserException, IOException
    {
        ApplicationInfo applicationinfo = package1.applicationInfo;
        String s = package1.applicationInfo.packageName;
        TypedArray typedarray = resources.obtainAttributes(xmlresourceparser, com.android.internal.R.styleable.AndroidManifestApplication);
        if(!parsePackageItemInfo(package1, applicationinfo, as, "<application>", typedarray, false, 3, 1, 2, 42, 22, 30))
        {
            typedarray.recycle();
            mParseError = -108;
            return false;
        }
        if(applicationinfo.name != null)
            applicationinfo.className = applicationinfo.name;
        Object obj1 = typedarray.getNonConfigurationString(4, 1024);
        if(obj1 != null)
            applicationinfo.manageSpaceActivityName = buildClassName(s, ((CharSequence) (obj1)), as);
        boolean flag;
        if(typedarray.getBoolean(17, true))
        {
            applicationinfo.flags = applicationinfo.flags | 0x8000;
            obj1 = typedarray.getNonConfigurationString(16, 1024);
            if(obj1 != null)
            {
                applicationinfo.backupAgentName = buildClassName(s, ((CharSequence) (obj1)), as);
                if(typedarray.getBoolean(18, true))
                    applicationinfo.flags = applicationinfo.flags | 0x10000;
                if(typedarray.getBoolean(21, false))
                    applicationinfo.flags = applicationinfo.flags | 0x20000;
                if(typedarray.getBoolean(32, false))
                    applicationinfo.flags = applicationinfo.flags | 0x4000000;
                if(typedarray.getBoolean(40, false))
                    applicationinfo.privateFlags = applicationinfo.privateFlags | 0x2000;
            }
            obj1 = typedarray.peekValue(35);
            if(obj1 != null)
            {
                int j = ((TypedValue) (obj1)).resourceId;
                applicationinfo.fullBackupContent = j;
                if(j == 0)
                {
                    int k;
                    if(((TypedValue) (obj1)).data == 0)
                        k = -1;
                    else
                        k = 0;
                    applicationinfo.fullBackupContent = k;
                }
            }
        }
        applicationinfo.theme = typedarray.getResourceId(0, 0);
        applicationinfo.descriptionRes = typedarray.getResourceId(13, 0);
        if((i & 1) != 0 && typedarray.getBoolean(8, false))
        {
            obj1 = typedarray.getNonResourceString(45);
            if(obj1 == null || mCallback.hasFeature(((String) (obj1))))
                applicationinfo.flags = applicationinfo.flags | 8;
        }
        if(typedarray.getBoolean(27, false))
            package1.mRequiredForAllUsers = true;
        obj1 = typedarray.getString(28);
        if(obj1 != null && ((String) (obj1)).length() > 0)
            package1.mRestrictedAccountType = ((String) (obj1));
        obj1 = typedarray.getString(29);
        if(obj1 != null && ((String) (obj1)).length() > 0)
            package1.mRequiredAccountType = ((String) (obj1));
        if(typedarray.getBoolean(10, false))
            applicationinfo.flags = applicationinfo.flags | 2;
        if(typedarray.getBoolean(20, false))
            applicationinfo.flags = applicationinfo.flags | 0x4000;
        if(package1.applicationInfo.targetSdkVersion >= 14)
            flag = true;
        else
            flag = false;
        package1.baseHardwareAccelerated = typedarray.getBoolean(23, flag);
        if(package1.baseHardwareAccelerated)
            applicationinfo.flags = applicationinfo.flags | 0x20000000;
        if(typedarray.getBoolean(7, true))
            applicationinfo.flags = applicationinfo.flags | 4;
        if(typedarray.getBoolean(14, false))
            applicationinfo.flags = applicationinfo.flags | 0x20;
        if(typedarray.getBoolean(5, true))
            applicationinfo.flags = applicationinfo.flags | 0x40;
        if(package1.parentPackage == null && typedarray.getBoolean(15, false))
            applicationinfo.flags = applicationinfo.flags | 0x100;
        if(typedarray.getBoolean(24, false))
            applicationinfo.flags = applicationinfo.flags | 0x100000;
        if(typedarray.getBoolean(36, true))
            applicationinfo.flags = applicationinfo.flags | 0x8000000;
        if(typedarray.getBoolean(26, false))
            applicationinfo.flags = applicationinfo.flags | 0x400000;
        if(typedarray.getBoolean(33, false))
            applicationinfo.flags = applicationinfo.flags | 0x80000000;
        if(typedarray.getBoolean(34, true))
            applicationinfo.flags = applicationinfo.flags | 0x10000000;
        if(typedarray.getBoolean(38, false))
            applicationinfo.privateFlags = applicationinfo.privateFlags | 0x20;
        if(typedarray.getBoolean(39, false))
            applicationinfo.privateFlags = applicationinfo.privateFlags | 0x40;
        if(typedarray.hasValueOrEmpty(37))
        {
            if(typedarray.getBoolean(37, true))
                applicationinfo.privateFlags = applicationinfo.privateFlags | 0x400;
            else
                applicationinfo.privateFlags = applicationinfo.privateFlags | 0x800;
        } else
        if(package1.applicationInfo.targetSdkVersion >= 24)
            applicationinfo.privateFlags = applicationinfo.privateFlags | 0x1000;
        applicationinfo.maxAspectRatio = typedarray.getFloat(44, 0.0F);
        applicationinfo.networkSecurityConfigRes = typedarray.getResourceId(41, 0);
        applicationinfo.category = typedarray.getInt(43, -1);
        obj1 = typedarray.getNonConfigurationString(6, 0);
        if(obj1 != null && ((String) (obj1)).length() > 0)
            obj1 = ((String) (obj1)).intern();
        else
            obj1 = null;
        applicationinfo.permission = ((String) (obj1));
        if(package1.applicationInfo.targetSdkVersion >= 8)
            obj1 = typedarray.getNonConfigurationString(12, 1024);
        else
            obj1 = typedarray.getNonResourceString(12);
        applicationinfo.taskAffinity = buildTaskAffinityName(applicationinfo.packageName, applicationinfo.packageName, ((CharSequence) (obj1)), as);
        if(as[0] == null)
        {
            if(package1.applicationInfo.targetSdkVersion >= 8)
                obj1 = typedarray.getNonConfigurationString(11, 1024);
            else
                obj1 = typedarray.getNonResourceString(11);
            applicationinfo.processName = buildProcessName(applicationinfo.packageName, null, ((CharSequence) (obj1)), i, mSeparateProcesses, as);
            applicationinfo.enabled = typedarray.getBoolean(9, true);
            if(typedarray.getBoolean(31, false))
                applicationinfo.flags = applicationinfo.flags | 0x2000000;
        }
        applicationinfo.uiOptions = typedarray.getInt(25, 0);
        applicationinfo.classLoaderName = typedarray.getString(46);
        if(applicationinfo.classLoaderName != null && ClassLoaderFactory.isValidClassLoaderName(applicationinfo.classLoaderName) ^ true)
            as[0] = (new StringBuilder()).append("Invalid class loader name: ").append(applicationinfo.classLoaderName).toString();
        typedarray.recycle();
        if(as[0] != null)
        {
            mParseError = -108;
            return false;
        }
        int l = xmlresourceparser.getDepth();
        obj1 = new CachedComponentArgs(null);
        do
        {
            int i1 = xmlresourceparser.next();
            if(i1 == 1 || i1 == 3 && xmlresourceparser.getDepth() <= l)
                break;
            if(i1 != 3 && i1 != 4)
            {
                Object obj = xmlresourceparser.getName();
                if(((String) (obj)).equals("activity"))
                {
                    obj = parseActivity(package1, resources, xmlresourceparser, i, as, ((CachedComponentArgs) (obj1)), false, package1.baseHardwareAccelerated);
                    if(obj == null)
                    {
                        mParseError = -108;
                        return false;
                    }
                    package1.activities.add(obj);
                } else
                if(((String) (obj)).equals("receiver"))
                {
                    obj = parseActivity(package1, resources, xmlresourceparser, i, as, ((CachedComponentArgs) (obj1)), true, false);
                    if(obj == null)
                    {
                        mParseError = -108;
                        return false;
                    }
                    package1.receivers.add(obj);
                } else
                if(((String) (obj)).equals("service"))
                {
                    obj = parseService(package1, resources, xmlresourceparser, i, as, ((CachedComponentArgs) (obj1)));
                    if(obj == null)
                    {
                        mParseError = -108;
                        return false;
                    }
                    package1.services.add(obj);
                } else
                if(((String) (obj)).equals("provider"))
                {
                    obj = parseProvider(package1, resources, xmlresourceparser, i, as, ((CachedComponentArgs) (obj1)));
                    if(obj == null)
                    {
                        mParseError = -108;
                        return false;
                    }
                    package1.providers.add(obj);
                } else
                if(((String) (obj)).equals("activity-alias"))
                {
                    obj = parseActivityAlias(package1, resources, xmlresourceparser, i, as, ((CachedComponentArgs) (obj1)));
                    if(obj == null)
                    {
                        mParseError = -108;
                        return false;
                    }
                    package1.activities.add(obj);
                } else
                if(xmlresourceparser.getName().equals("meta-data"))
                {
                    obj = parseMetaData(resources, xmlresourceparser, package1.mAppMetaData, as);
                    package1.mAppMetaData = ((Bundle) (obj));
                    if(obj == null)
                    {
                        mParseError = -108;
                        return false;
                    }
                } else
                if(((String) (obj)).equals("static-library"))
                {
                    obj = resources.obtainAttributes(xmlresourceparser, com.android.internal.R.styleable.AndroidManifestStaticLibrary);
                    String s1 = ((TypedArray) (obj)).getNonResourceString(0);
                    int j1 = ((TypedArray) (obj)).getInt(1, -1);
                    ((TypedArray) (obj)).recycle();
                    if(s1 == null || j1 < 0)
                    {
                        as[0] = (new StringBuilder()).append("Bad static-library declaration name: ").append(s1).append(" version: ").append(j1).toString();
                        mParseError = -108;
                        XmlUtils.skipCurrentTag(xmlresourceparser);
                        return false;
                    }
                    if(package1.mSharedUserId != null)
                    {
                        as[0] = "sharedUserId not allowed in static shared library";
                        mParseError = -107;
                        XmlUtils.skipCurrentTag(xmlresourceparser);
                        return false;
                    }
                    if(package1.staticSharedLibName != null)
                    {
                        as[0] = (new StringBuilder()).append("Multiple static-shared libs for package ").append(s).toString();
                        mParseError = -108;
                        XmlUtils.skipCurrentTag(xmlresourceparser);
                        return false;
                    }
                    package1.staticSharedLibName = s1.intern();
                    package1.staticSharedLibVersion = j1;
                    applicationinfo.privateFlags = applicationinfo.privateFlags | 0x4000;
                    XmlUtils.skipCurrentTag(xmlresourceparser);
                } else
                if(((String) (obj)).equals("library"))
                {
                    TypedArray typedarray1 = resources.obtainAttributes(xmlresourceparser, com.android.internal.R.styleable.AndroidManifestLibrary);
                    obj = typedarray1.getNonResourceString(0);
                    typedarray1.recycle();
                    if(obj != null)
                    {
                        obj = ((String) (obj)).intern();
                        if(!ArrayUtils.contains(package1.libraryNames, obj))
                            package1.libraryNames = ArrayUtils.add(package1.libraryNames, obj);
                    }
                    XmlUtils.skipCurrentTag(xmlresourceparser);
                } else
                if(((String) (obj)).equals("uses-static-library"))
                {
                    if(!parseUsesStaticLibrary(package1, resources, xmlresourceparser, as))
                        return false;
                } else
                if(((String) (obj)).equals("uses-library"))
                {
                    obj = resources.obtainAttributes(xmlresourceparser, com.android.internal.R.styleable.AndroidManifestUsesLibrary);
                    String s2 = ((TypedArray) (obj)).getNonResourceString(0);
                    boolean flag1 = ((TypedArray) (obj)).getBoolean(1, true);
                    ((TypedArray) (obj)).recycle();
                    if(s2 != null)
                    {
                        obj = s2.intern();
                        if(flag1)
                            package1.usesLibraries = ArrayUtils.add(package1.usesLibraries, obj);
                        else
                            package1.usesOptionalLibraries = ArrayUtils.add(package1.usesOptionalLibraries, obj);
                    }
                    XmlUtils.skipCurrentTag(xmlresourceparser);
                } else
                if(((String) (obj)).equals("uses-package"))
                {
                    XmlUtils.skipCurrentTag(xmlresourceparser);
                } else
                {
                    Slog.w("PackageParser", (new StringBuilder()).append("Unknown element under <application>: ").append(((String) (obj))).append(" at ").append(mArchiveSourcePath).append(" ").append(xmlresourceparser.getPositionDescription()).toString());
                    XmlUtils.skipCurrentTag(xmlresourceparser);
                }
            }
        } while(true);
        setMaxAspectRatio(package1);
        PackageBackwardCompatibility.modifySharedLibraries(package1);
        if(hasDomainURLs(package1))
        {
            package1 = package1.applicationInfo;
            package1.privateFlags = ((ApplicationInfo) (package1)).privateFlags | 0x10;
        } else
        {
            package1 = package1.applicationInfo;
            package1.privateFlags = ((ApplicationInfo) (package1)).privateFlags & 0xffffffef;
        }
        return true;
    }

    private Package parseClusterPackage(File file, int i)
        throws PackageParserException
    {
        PackageLite packagelite;
        Object obj;
        Object obj1;
        Object obj2;
        packagelite = parseClusterPackageLite(file, 0);
        if(mOnlyCoreApps && packagelite.coreApp ^ true)
            throw new PackageParserException(-108, (new StringBuilder()).append("Not a coreApp: ").append(file).toString());
        obj = null;
        File file1;
        if(packagelite.isolatedSplits && ArrayUtils.isEmpty(packagelite.splitNames) ^ true)
            try
            {
                obj = SplitAssetDependencyLoader.createDependenciesFromPackage(packagelite);
                obj1 = new SplitAssetDependencyLoader(packagelite, ((android.util.SparseArray) (obj)), i);
            }
            // Misplaced declaration of an exception variable
            catch(File file)
            {
                throw new PackageParserException(-101, file.getMessage());
            }
        else
            obj1 = new DefaultSplitAssetLoader(packagelite, i);
        obj2 = ((SplitAssetLoader) (obj1)).getBaseAssetManager();
        file1 = JVM INSTR new #547 <Class File>;
        file1.File(packagelite.baseCodePath);
        obj2 = parseBaseApk(file1, ((AssetManager) (obj2)), i);
        if(obj2 != null)
            break MISSING_BLOCK_LABEL_207;
        obj = JVM INSTR new #53  <Class PackageParser$PackageParserException>;
        file = JVM INSTR new #465 <Class StringBuilder>;
        file.StringBuilder();
        ((PackageParserException) (obj)).PackageParserException(-100, file.append("Failed to parse base APK: ").append(file1).toString());
        throw obj;
        file;
        IoUtils.closeQuietly(((AutoCloseable) (obj1)));
        throw file;
        if(ArrayUtils.isEmpty(packagelite.splitNames)) goto _L2; else goto _L1
_L1:
        int j;
        j = packagelite.splitNames.length;
        obj2.splitNames = packagelite.splitNames;
        obj2.splitCodePaths = packagelite.splitCodePaths;
        obj2.splitRevisionCodes = packagelite.splitRevisionCodes;
        obj2.splitFlags = new int[j];
        obj2.splitPrivateFlags = new int[j];
        ((Package) (obj2)).applicationInfo.splitNames = ((Package) (obj2)).splitNames;
        ((Package) (obj2)).applicationInfo.splitDependencies = ((android.util.SparseArray) (obj));
        ((Package) (obj2)).applicationInfo.splitClassLoaderNames = new String[j];
        int k = 0;
_L3:
        if(k >= j)
            break; /* Loop/switch isn't completed */
        parseSplitApk(((Package) (obj2)), k, ((SplitAssetLoader) (obj1)).getSplitAssetManager(k), i);
        k++;
        if(true) goto _L3; else goto _L2
_L2:
        ((Package) (obj2)).setCodePath(file.getAbsolutePath());
        ((Package) (obj2)).setUse32bitAbi(packagelite.use32bitAbi);
        IoUtils.closeQuietly(((AutoCloseable) (obj1)));
        return ((Package) (obj2));
    }

    static PackageLite parseClusterPackageLite(File file, int i)
        throws PackageParserException
    {
        File afile[];
        String s;
        int j;
        ArrayMap arraymap;
        int l;
        int i1;
        afile = file.listFiles();
        if(ArrayUtils.isEmpty(afile))
            throw new PackageParserException(-100, "No packages found in split");
        s = null;
        j = 0;
        Trace.traceBegin(0x40000L, "parseApkLite");
        arraymap = new ArrayMap();
        l = 0;
        i1 = afile.length;
_L7:
        if(l >= i1) goto _L2; else goto _L1
_L1:
        File file1;
        String s1;
        int j1;
        ApkLite apklite;
        file1 = afile[l];
        s1 = s;
        j1 = j;
        if(!isApkFile(file1))
            break MISSING_BLOCK_LABEL_308;
        apklite = parseApkLite(file1, i);
        if(s != null) goto _L4; else goto _L3
_L3:
        s = apklite.packageName;
        j1 = apklite.versionCode;
_L6:
        s1 = s;
        if(arraymap.put(apklite.splitName, apklite) != null)
            throw new PackageParserException(-101, (new StringBuilder()).append("Split name ").append(apklite.splitName).append(" defined more than once; most recent was ").append(file1).toString());
        break MISSING_BLOCK_LABEL_308;
_L4:
        if(!s.equals(apklite.packageName))
            throw new PackageParserException(-101, (new StringBuilder()).append("Inconsistent package ").append(apklite.packageName).append(" in ").append(file1).append("; expected ").append(s).toString());
        j1 = j;
        if(j == apklite.versionCode) goto _L6; else goto _L5
_L5:
        throw new PackageParserException(-101, (new StringBuilder()).append("Inconsistent version ").append(apklite.versionCode).append(" in ").append(file1).append("; expected ").append(j).toString());
        l++;
        s = s1;
        j = j1;
          goto _L7
_L2:
        Trace.traceEnd(0x40000L);
        ApkLite apklite1 = (ApkLite)arraymap.remove(null);
        if(apklite1 == null)
            throw new PackageParserException(-101, (new StringBuilder()).append("Missing base APK in ").append(file).toString());
        int k = arraymap.size();
        String as1[] = null;
        boolean aflag[] = null;
        String as[] = null;
        String as3[] = null;
        String as2[] = null;
        int ai[] = null;
        if(k > 0)
        {
            as1 = new String[k];
            boolean aflag1[] = new boolean[k];
            String as4[] = new String[k];
            String as5[] = new String[k];
            String as6[] = new String[k];
            int ai1[] = new int[k];
            String as7[] = (String[])arraymap.keySet().toArray(as1);
            Arrays.sort(as7, sSplitNameComparator);
            i = 0;
            do
            {
                as1 = as7;
                aflag = aflag1;
                as = as4;
                as3 = as5;
                as2 = as6;
                ai = ai1;
                if(i >= k)
                    break;
                as1 = (ApkLite)arraymap.get(as7[i]);
                as4[i] = ((ApkLite) (as1)).usesSplitName;
                aflag1[i] = ((ApkLite) (as1)).isFeatureSplit;
                as5[i] = ((ApkLite) (as1)).configForSplit;
                as6[i] = ((ApkLite) (as1)).codePath;
                ai1[i] = ((ApkLite) (as1)).revisionCode;
                i++;
            } while(true);
        }
        return new PackageLite(file.getAbsolutePath(), apklite1, as1, aflag, as, as3, as2, ai);
    }

    private Instrumentation parseInstrumentation(Package package1, Resources resources, XmlResourceParser xmlresourceparser, String as[])
        throws XmlPullParserException, IOException
    {
        TypedArray typedarray = resources.obtainAttributes(xmlresourceparser, com.android.internal.R.styleable.AndroidManifestInstrumentation);
        if(mParseInstrumentationArgs == null)
        {
            mParseInstrumentationArgs = new ParsePackageItemArgs(package1, as, 2, 0, 1, 8, 6, 7);
            mParseInstrumentationArgs.tag = "<instrumentation>";
        }
        mParseInstrumentationArgs.sa = typedarray;
        Instrumentation instrumentation = new Instrumentation(mParseInstrumentationArgs, new InstrumentationInfo());
        if(as[0] != null)
        {
            typedarray.recycle();
            mParseError = -108;
            return null;
        }
        String s = typedarray.getNonResourceString(3);
        InstrumentationInfo instrumentationinfo = instrumentation.info;
        if(s != null)
            s = s.intern();
        else
            s = null;
        instrumentationinfo.targetPackage = s;
        s = typedarray.getNonResourceString(9);
        instrumentationinfo = instrumentation.info;
        if(s != null)
            s = s.intern();
        else
            s = null;
        instrumentationinfo.targetProcesses = s;
        instrumentation.info.handleProfiling = typedarray.getBoolean(4, false);
        instrumentation.info.functionalTest = typedarray.getBoolean(5, false);
        typedarray.recycle();
        if(instrumentation.info.targetPackage == null)
        {
            as[0] = "<instrumentation> does not specify targetPackage";
            mParseError = -108;
            return null;
        }
        if(!parseAllMetaData(resources, xmlresourceparser, "<instrumentation>", instrumentation, as))
        {
            mParseError = -108;
            return null;
        } else
        {
            package1.instrumentation.add(instrumentation);
            return instrumentation;
        }
    }

    private boolean parseIntent(Resources resources, XmlResourceParser xmlresourceparser, boolean flag, boolean flag1, IntentInfo intentinfo, String as[])
        throws XmlPullParserException, IOException
    {
        Object obj = resources.obtainAttributes(xmlresourceparser, com.android.internal.R.styleable.AndroidManifestIntentFilter);
        intentinfo.setPriority(((TypedArray) (obj)).getInt(2, 0));
        TypedValue typedvalue = ((TypedArray) (obj)).peekValue(0);
        if(typedvalue != null)
        {
            int i = typedvalue.resourceId;
            intentinfo.labelRes = i;
            if(i == 0)
                intentinfo.nonLocalizedLabel = typedvalue.coerceToString();
        }
        int j;
        int k;
        if(Resources.getSystem().getBoolean(0x11200c4))
            j = ((TypedArray) (obj)).getResourceId(6, 0);
        else
            j = 0;
        if(j != 0)
            intentinfo.icon = j;
        else
            intentinfo.icon = ((TypedArray) (obj)).getResourceId(1, 0);
        intentinfo.logo = ((TypedArray) (obj)).getResourceId(3, 0);
        intentinfo.banner = ((TypedArray) (obj)).getResourceId(4, 0);
        if(flag1)
            intentinfo.setAutoVerify(((TypedArray) (obj)).getBoolean(5, false));
        ((TypedArray) (obj)).recycle();
        k = xmlresourceparser.getDepth();
        do
        {
            j = xmlresourceparser.next();
            if(j == 1 || j == 3 && xmlresourceparser.getDepth() <= k)
                break;
            if(j != 3 && j != 4)
            {
                obj = xmlresourceparser.getName();
                if(((String) (obj)).equals("action"))
                {
                    obj = xmlresourceparser.getAttributeValue("http://schemas.android.com/apk/res/android", "name");
                    if(obj == null || obj == "")
                    {
                        as[0] = "No value supplied for <android:name>";
                        return false;
                    }
                    XmlUtils.skipCurrentTag(xmlresourceparser);
                    intentinfo.addAction(((String) (obj)));
                } else
                if(((String) (obj)).equals("category"))
                {
                    obj = xmlresourceparser.getAttributeValue("http://schemas.android.com/apk/res/android", "name");
                    if(obj == null || obj == "")
                    {
                        as[0] = "No value supplied for <android:name>";
                        return false;
                    }
                    XmlUtils.skipCurrentTag(xmlresourceparser);
                    intentinfo.addCategory(((String) (obj)));
                } else
                if(((String) (obj)).equals("data"))
                {
                    TypedArray typedarray = resources.obtainAttributes(xmlresourceparser, com.android.internal.R.styleable.AndroidManifestData);
                    String s = typedarray.getNonConfigurationString(0, 0);
                    if(s != null)
                        try
                        {
                            intentinfo.addDataType(s);
                        }
                        // Misplaced declaration of an exception variable
                        catch(Resources resources)
                        {
                            as[0] = resources.toString();
                            typedarray.recycle();
                            return false;
                        }
                    s = typedarray.getNonConfigurationString(1, 0);
                    if(s != null)
                        intentinfo.addDataScheme(s);
                    s = typedarray.getNonConfigurationString(7, 0);
                    if(s != null)
                        intentinfo.addDataSchemeSpecificPart(s, 0);
                    s = typedarray.getNonConfigurationString(8, 0);
                    if(s != null)
                        intentinfo.addDataSchemeSpecificPart(s, 1);
                    s = typedarray.getNonConfigurationString(9, 0);
                    if(s != null)
                    {
                        if(!flag)
                        {
                            as[0] = "sspPattern not allowed here; ssp must be literal";
                            return false;
                        }
                        intentinfo.addDataSchemeSpecificPart(s, 2);
                    }
                    String s1 = typedarray.getNonConfigurationString(2, 0);
                    s = typedarray.getNonConfigurationString(3, 0);
                    if(s1 != null)
                        intentinfo.addDataAuthority(s1, s);
                    s = typedarray.getNonConfigurationString(4, 0);
                    if(s != null)
                        intentinfo.addDataPath(s, 0);
                    s = typedarray.getNonConfigurationString(5, 0);
                    if(s != null)
                        intentinfo.addDataPath(s, 1);
                    s = typedarray.getNonConfigurationString(6, 0);
                    if(s != null)
                    {
                        if(!flag)
                        {
                            as[0] = "pathPattern not allowed here; path must be literal";
                            return false;
                        }
                        intentinfo.addDataPath(s, 2);
                    }
                    s = typedarray.getNonConfigurationString(10, 0);
                    if(s != null)
                    {
                        if(!flag)
                        {
                            as[0] = "pathAdvancedPattern not allowed here; path must be literal";
                            return false;
                        }
                        intentinfo.addDataPath(s, 3);
                    }
                    typedarray.recycle();
                    XmlUtils.skipCurrentTag(xmlresourceparser);
                } else
                {
                    Slog.w("PackageParser", (new StringBuilder()).append("Unknown element under <intent-filter>: ").append(xmlresourceparser.getName()).append(" at ").append(mArchiveSourcePath).append(" ").append(xmlresourceparser.getPositionDescription()).toString());
                    XmlUtils.skipCurrentTag(xmlresourceparser);
                }
            }
        } while(true);
        intentinfo.hasDefault = intentinfo.hasCategory("android.intent.category.DEFAULT");
        return true;
    }

    private boolean parseKeySets(Package package1, Resources resources, XmlResourceParser xmlresourceparser, String as[])
        throws XmlPullParserException, IOException
    {
        int i = xmlresourceparser.getDepth();
        int j = -1;
        String s = null;
        ArrayMap arraymap = new ArrayMap();
        ArraySet arrayset = new ArraySet();
        ArrayMap arraymap1 = new ArrayMap();
        ArraySet arrayset1 = new ArraySet();
label0:
        do
        {
            Object obj;
label1:
            {
                TypedArray typedarray1;
label2:
                {
                    int k = xmlresourceparser.next();
                    if(k == 1 || k == 3 && xmlresourceparser.getDepth() <= i)
                        break label0;
                    if(k == 3)
                    {
                        if(xmlresourceparser.getDepth() == j)
                        {
                            s = null;
                            j = -1;
                        }
                        continue;
                    }
                    obj = xmlresourceparser.getName();
                    if(((String) (obj)).equals("key-set"))
                    {
                        if(s != null)
                        {
                            as[0] = (new StringBuilder()).append("Improperly nested 'key-set' tag at ").append(xmlresourceparser.getPositionDescription()).toString();
                            mParseError = -108;
                            return false;
                        }
                        obj = resources.obtainAttributes(xmlresourceparser, com.android.internal.R.styleable.AndroidManifestKeySet);
                        s = ((TypedArray) (obj)).getNonResourceString(0);
                        arraymap1.put(s, new ArraySet());
                        j = xmlresourceparser.getDepth();
                        ((TypedArray) (obj)).recycle();
                        continue;
                    }
                    if(!((String) (obj)).equals("public-key"))
                        break label1;
                    if(s == null)
                    {
                        as[0] = (new StringBuilder()).append("Improperly nested 'key-set' tag at ").append(xmlresourceparser.getPositionDescription()).toString();
                        mParseError = -108;
                        return false;
                    }
                    typedarray1 = resources.obtainAttributes(xmlresourceparser, com.android.internal.R.styleable.AndroidManifestPublicKey);
                    obj = typedarray1.getNonResourceString(0);
                    Object obj1 = typedarray1.getNonResourceString(1);
                    if(obj1 == null && arraymap.get(obj) == null)
                    {
                        as[0] = (new StringBuilder()).append("'public-key' ").append(((String) (obj))).append(" must define a public-key value").append(" on first use at ").append(xmlresourceparser.getPositionDescription()).toString();
                        mParseError = -108;
                        typedarray1.recycle();
                        return false;
                    }
                    if(obj1 != null)
                    {
                        obj1 = parsePublicKey(((String) (obj1)));
                        if(obj1 == null)
                        {
                            Slog.w("PackageParser", (new StringBuilder()).append("No recognized valid key in 'public-key' tag at ").append(xmlresourceparser.getPositionDescription()).append(" key-set ").append(s).append(" will not be added to the package's defined key-sets.").toString());
                            typedarray1.recycle();
                            arrayset1.add(s);
                            XmlUtils.skipCurrentTag(xmlresourceparser);
                            continue;
                        }
                        if(arraymap.get(obj) != null && !((PublicKey)arraymap.get(obj)).equals(obj1))
                            break label2;
                        arraymap.put(obj, obj1);
                    }
                    ((ArraySet)arraymap1.get(s)).add(obj);
                    typedarray1.recycle();
                    XmlUtils.skipCurrentTag(xmlresourceparser);
                    continue;
                }
                as[0] = (new StringBuilder()).append("Value of 'public-key' ").append(((String) (obj))).append(" conflicts with previously defined value at ").append(xmlresourceparser.getPositionDescription()).toString();
                mParseError = -108;
                typedarray1.recycle();
                return false;
            }
            if(((String) (obj)).equals("upgrade-key-set"))
            {
                TypedArray typedarray = resources.obtainAttributes(xmlresourceparser, com.android.internal.R.styleable.AndroidManifestUpgradeKeySet);
                arrayset.add(typedarray.getNonResourceString(0));
                typedarray.recycle();
                XmlUtils.skipCurrentTag(xmlresourceparser);
            } else
            {
                Slog.w("PackageParser", (new StringBuilder()).append("Unknown element under <key-sets>: ").append(xmlresourceparser.getName()).append(" at ").append(mArchiveSourcePath).append(" ").append(xmlresourceparser.getPositionDescription()).toString());
                XmlUtils.skipCurrentTag(xmlresourceparser);
            }
        } while(true);
        if(arraymap.keySet().removeAll(arraymap1.keySet()))
        {
            as[0] = (new StringBuilder()).append("Package").append(package1.packageName).append(" AndroidManifext.xml ").append("'key-set' and 'public-key' names must be distinct.").toString();
            mParseError = -108;
            return false;
        }
        package1.mKeySetMapping = new ArrayMap();
        for(xmlresourceparser = arraymap1.entrySet().iterator(); xmlresourceparser.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)xmlresourceparser.next();
            resources = (String)entry.getKey();
            if(((ArraySet)entry.getValue()).size() == 0)
                Slog.w("PackageParser", (new StringBuilder()).append("Package").append(package1.packageName).append(" AndroidManifext.xml ").append("'key-set' ").append(resources).append(" has no valid associated 'public-key'.").append(" Not including in package's defined key-sets.").toString());
            else
            if(arrayset1.contains(resources))
            {
                Slog.w("PackageParser", (new StringBuilder()).append("Package").append(package1.packageName).append(" AndroidManifext.xml ").append("'key-set' ").append(resources).append(" contained improper 'public-key'").append(" tags. Not including in package's defined key-sets.").toString());
            } else
            {
                package1.mKeySetMapping.put(resources, new ArraySet());
                Iterator iterator = ((ArraySet)entry.getValue()).iterator();
                while(iterator.hasNext()) 
                {
                    String s1 = (String)iterator.next();
                    ((ArraySet)package1.mKeySetMapping.get(resources)).add((PublicKey)arraymap.get(s1));
                }
            }
        }

        if(package1.mKeySetMapping.keySet().containsAll(arrayset))
        {
            package1.mUpgradeKeySets = arrayset;
            return true;
        } else
        {
            as[0] = (new StringBuilder()).append("Package").append(package1.packageName).append(" AndroidManifext.xml ").append("does not define all 'upgrade-key-set's .").toString();
            mParseError = -108;
            return false;
        }
    }

    private void parseLayout(Resources resources, AttributeSet attributeset, Activity activity)
    {
        resources = resources.obtainAttributes(attributeset, com.android.internal.R.styleable.AndroidManifestLayout);
        int i = -1;
        float f = -1F;
        int j = -1;
        float f1 = -1F;
        int k = resources.getType(3);
        float f2;
        int l;
        int i1;
        if(k == 6)
        {
            f2 = resources.getFraction(3, 1, 1, -1F);
        } else
        {
            f2 = f;
            if(k == 5)
            {
                i = resources.getDimensionPixelSize(3, -1);
                f2 = f;
            }
        }
        k = resources.getType(4);
        if(k == 6)
        {
            f = resources.getFraction(4, 1, 1, -1F);
        } else
        {
            f = f1;
            if(k == 5)
            {
                j = resources.getDimensionPixelSize(4, -1);
                f = f1;
            }
        }
        l = resources.getInt(0, 17);
        i1 = resources.getDimensionPixelSize(1, -1);
        k = resources.getDimensionPixelSize(2, -1);
        resources.recycle();
        activity.info.windowLayout = new ActivityInfo.WindowLayout(i, f2, j, f, l, i1, k);
    }

    private Bundle parseMetaData(Resources resources, XmlResourceParser xmlresourceparser, Bundle bundle, String as[])
        throws XmlPullParserException, IOException
    {
        Object obj = null;
        TypedArray typedarray = resources.obtainAttributes(xmlresourceparser, com.android.internal.R.styleable.AndroidManifestMetaData);
        resources = bundle;
        if(bundle == null)
            resources = new Bundle();
        bundle = typedarray.getNonConfigurationString(0, 0);
        if(bundle == null)
        {
            as[0] = "<meta-data> requires an android:name attribute";
            typedarray.recycle();
            return null;
        }
        String s = bundle.intern();
        bundle = typedarray.peekValue(2);
        if(bundle != null && ((TypedValue) (bundle)).resourceId != 0)
        {
            resources.putInt(s, ((TypedValue) (bundle)).resourceId);
        } else
        {
            bundle = typedarray.peekValue(1);
            if(bundle != null)
            {
                if(((TypedValue) (bundle)).type == 3)
                {
                    as = bundle.coerceToString();
                    bundle = obj;
                    if(as != null)
                        bundle = as.toString();
                    resources.putString(s, bundle);
                } else
                if(((TypedValue) (bundle)).type == 18)
                {
                    boolean flag;
                    if(((TypedValue) (bundle)).data != 0)
                        flag = true;
                    else
                        flag = false;
                    resources.putBoolean(s, flag);
                } else
                if(((TypedValue) (bundle)).type >= 16 && ((TypedValue) (bundle)).type <= 31)
                    resources.putInt(s, ((TypedValue) (bundle)).data);
                else
                if(((TypedValue) (bundle)).type == 4)
                    resources.putFloat(s, bundle.getFloat());
                else
                    Slog.w("PackageParser", (new StringBuilder()).append("<meta-data> only supports string, integer, float, color, boolean, and resource reference types: ").append(xmlresourceparser.getName()).append(" at ").append(mArchiveSourcePath).append(" ").append(xmlresourceparser.getPositionDescription()).toString());
            } else
            {
                as[0] = "<meta-data> requires an android:value or android:resource attribute";
                resources = null;
            }
        }
        typedarray.recycle();
        XmlUtils.skipCurrentTag(xmlresourceparser);
        return resources;
    }

    private static PackageLite parseMonolithicPackageLite(File file, int i)
        throws PackageParserException
    {
        Trace.traceBegin(0x40000L, "parseApkLite");
        ApkLite apklite = parseApkLite(file, i);
        file = file.getAbsolutePath();
        Trace.traceEnd(0x40000L);
        return new PackageLite(file, apklite, null, null, null, null, null, null);
    }

    private static boolean parsePackageItemInfo(Package package1, PackageItemInfo packageiteminfo, String as[], String s, TypedArray typedarray, boolean flag, int i, int j, 
            int k, int l, int i1, int j1)
    {
        if(typedarray == null)
        {
            as[0] = (new StringBuilder()).append(s).append(" does not contain any attributes").toString();
            return false;
        }
        String s1 = typedarray.getNonConfigurationString(i, 0);
        if(s1 == null)
        {
            if(flag)
            {
                as[0] = (new StringBuilder()).append(s).append(" does not specify android:name").toString();
                return false;
            }
        } else
        {
            packageiteminfo.name = buildClassName(package1.applicationInfo.packageName, s1, as);
            if(packageiteminfo.name == null)
                return false;
        }
        if(Resources.getSystem().getBoolean(0x11200c4))
            i = typedarray.getResourceId(l, 0);
        else
            i = 0;
        if(i == 0) goto _L2; else goto _L1
_L1:
        packageiteminfo.icon = i;
        packageiteminfo.nonLocalizedLabel = null;
_L4:
        i = typedarray.getResourceId(i1, 0);
        if(i != 0)
            packageiteminfo.logo = i;
        i = typedarray.getResourceId(j1, 0);
        if(i != 0)
            packageiteminfo.banner = i;
        as = typedarray.peekValue(j);
        if(as != null)
        {
            i = ((TypedValue) (as)).resourceId;
            packageiteminfo.labelRes = i;
            if(i == 0)
                packageiteminfo.nonLocalizedLabel = as.coerceToString();
        }
        packageiteminfo.packageName = package1.packageName;
        return true;
_L2:
        i = typedarray.getResourceId(k, 0);
        if(i != 0)
        {
            packageiteminfo.icon = i;
            packageiteminfo.nonLocalizedLabel = null;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static PackageLite parsePackageLite(File file, int i)
        throws PackageParserException
    {
        if(file.isDirectory())
            return parseClusterPackageLite(file, i);
        else
            return parseMonolithicPackageLite(file, i);
    }

    private static Pair parsePackageSplitNames(XmlPullParser xmlpullparser, AttributeSet attributeset)
        throws IOException, XmlPullParserException, PackageParserException
    {
        int i;
        do
            i = xmlpullparser.next();
        while(i != 2 && i != 1);
        if(i != 2)
            throw new PackageParserException(-108, "No start tag found");
        if(!xmlpullparser.getName().equals("manifest"))
            throw new PackageParserException(-108, "No <manifest> tag");
        String s = attributeset.getAttributeValue(null, "package");
        if(!"android".equals(s))
        {
            xmlpullparser = validateName(s, true, true);
            if(xmlpullparser != null)
                throw new PackageParserException(-106, (new StringBuilder()).append("Invalid manifest package: ").append(xmlpullparser).toString());
        }
        attributeset = attributeset.getAttributeValue(null, "split");
        xmlpullparser = attributeset;
        if(attributeset != null)
            if(attributeset.length() == 0)
            {
                xmlpullparser = null;
            } else
            {
                String s1 = validateName(attributeset, false, false);
                xmlpullparser = attributeset;
                if(s1 != null)
                    throw new PackageParserException(-106, (new StringBuilder()).append("Invalid manifest split: ").append(s1).toString());
            }
        s = s.intern();
        attributeset = xmlpullparser;
        if(xmlpullparser != null)
            attributeset = xmlpullparser.intern();
        return Pair.create(s, attributeset);
    }

    private boolean parsePermission(Package package1, Resources resources, XmlResourceParser xmlresourceparser, String as[])
        throws XmlPullParserException, IOException
    {
        TypedArray typedarray = resources.obtainAttributes(xmlresourceparser, com.android.internal.R.styleable.AndroidManifestPermission);
        Permission permission = new Permission(package1);
        if(!parsePackageItemInfo(package1, permission.info, as, "<permission>", typedarray, true, 2, 0, 1, 9, 6, 8))
        {
            typedarray.recycle();
            mParseError = -108;
            return false;
        }
        permission.info.group = typedarray.getNonResourceString(4);
        if(permission.info.group != null)
            permission.info.group = permission.info.group.intern();
        permission.info.descriptionRes = typedarray.getResourceId(5, 0);
        permission.info.protectionLevel = typedarray.getInt(3, 0);
        permission.info.flags = typedarray.getInt(7, 0);
        typedarray.recycle();
        if(permission.info.protectionLevel == -1)
        {
            as[0] = "<permission> does not specify protectionLevel";
            mParseError = -108;
            return false;
        }
        permission.info.protectionLevel = PermissionInfo.fixProtectionLevel(permission.info.protectionLevel);
        if((permission.info.protectionLevel & 0xfff0) != 0 && (permission.info.protectionLevel & 0x1000) == 0 && (permission.info.protectionLevel & 0x2000) == 0 && (permission.info.protectionLevel & 0xf) != 2)
        {
            as[0] = "<permission>  protectionLevel specifies a non-instnat flag but is not based on signature type";
            mParseError = -108;
            return false;
        }
        if(!parseAllMetaData(resources, xmlresourceparser, "<permission>", permission, as))
        {
            mParseError = -108;
            return false;
        } else
        {
            package1.permissions.add(permission);
            return true;
        }
    }

    private boolean parsePermissionGroup(Package package1, int i, Resources resources, XmlResourceParser xmlresourceparser, String as[])
        throws XmlPullParserException, IOException
    {
        PermissionGroup permissiongroup = new PermissionGroup(package1);
        TypedArray typedarray = resources.obtainAttributes(xmlresourceparser, com.android.internal.R.styleable.AndroidManifestPermissionGroup);
        if(!parsePackageItemInfo(package1, permissiongroup.info, as, "<permission-group>", typedarray, true, 2, 0, 1, 8, 5, 7))
        {
            typedarray.recycle();
            mParseError = -108;
            return false;
        }
        permissiongroup.info.descriptionRes = typedarray.getResourceId(4, 0);
        permissiongroup.info.flags = typedarray.getInt(6, 0);
        permissiongroup.info.priority = typedarray.getInt(3, 0);
        if(permissiongroup.info.priority > 0 && (i & 1) == 0)
            permissiongroup.info.priority = 0;
        typedarray.recycle();
        if(!parseAllMetaData(resources, xmlresourceparser, "<permission-group>", permissiongroup, as))
        {
            mParseError = -108;
            return false;
        } else
        {
            package1.permissionGroups.add(permissiongroup);
            return true;
        }
    }

    private boolean parsePermissionTree(Package package1, Resources resources, XmlResourceParser xmlresourceparser, String as[])
        throws XmlPullParserException, IOException
    {
        Permission permission = new Permission(package1);
        TypedArray typedarray = resources.obtainAttributes(xmlresourceparser, com.android.internal.R.styleable.AndroidManifestPermissionTree);
        if(!parsePackageItemInfo(package1, permission.info, as, "<permission-tree>", typedarray, true, 2, 0, 1, 5, 3, 4))
        {
            typedarray.recycle();
            mParseError = -108;
            return false;
        }
        typedarray.recycle();
        int i = permission.info.name.indexOf('.');
        int j = i;
        if(i > 0)
            j = permission.info.name.indexOf('.', i + 1);
        if(j < 0)
        {
            as[0] = (new StringBuilder()).append("<permission-tree> name has less than three segments: ").append(permission.info.name).toString();
            mParseError = -108;
            return false;
        }
        permission.info.descriptionRes = 0;
        permission.info.protectionLevel = 0;
        permission.tree = true;
        if(!parseAllMetaData(resources, xmlresourceparser, "<permission-tree>", permission, as))
        {
            mParseError = -108;
            return false;
        } else
        {
            package1.permissions.add(permission);
            return true;
        }
    }

    private Provider parseProvider(Package package1, Resources resources, XmlResourceParser xmlresourceparser, int i, String as[], CachedComponentArgs cachedcomponentargs)
        throws XmlPullParserException, IOException
    {
        TypedArray typedarray = resources.obtainAttributes(xmlresourceparser, com.android.internal.R.styleable.AndroidManifestProvider);
        if(cachedcomponentargs.mProviderArgs == null)
        {
            cachedcomponentargs.mProviderArgs = new ParseComponentArgs(package1, as, 2, 0, 1, 19, 15, 17, mSeparateProcesses, 8, 14, 6);
            cachedcomponentargs.mProviderArgs.tag = "<provider>";
        }
        cachedcomponentargs.mProviderArgs.sa = typedarray;
        cachedcomponentargs.mProviderArgs.flags = i;
        Provider provider = new Provider(cachedcomponentargs.mProviderArgs, new ProviderInfo());
        if(as[0] != null)
        {
            typedarray.recycle();
            return null;
        }
        boolean flag = false;
        if(package1.applicationInfo.targetSdkVersion < 17)
            flag = true;
        provider.info.exported = typedarray.getBoolean(7, flag);
        String s = typedarray.getNonConfigurationString(10, 0);
        provider.info.isSyncable = typedarray.getBoolean(11, false);
        cachedcomponentargs = typedarray.getNonConfigurationString(3, 0);
        String s1 = typedarray.getNonConfigurationString(4, 0);
        Object obj = s1;
        if(s1 == null)
            obj = cachedcomponentargs;
        if(obj == null)
        {
            provider.info.readPermission = package1.applicationInfo.permission;
        } else
        {
            ProviderInfo providerinfo = provider.info;
            if(((String) (obj)).length() > 0)
                obj = ((String) (obj)).toString().intern();
            else
                obj = null;
            providerinfo.readPermission = ((String) (obj));
        }
        s1 = typedarray.getNonConfigurationString(5, 0);
        obj = s1;
        if(s1 == null)
            obj = cachedcomponentargs;
        if(obj == null)
        {
            provider.info.writePermission = package1.applicationInfo.permission;
        } else
        {
            ProviderInfo providerinfo1 = provider.info;
            if(((String) (obj)).length() > 0)
                cachedcomponentargs = ((String) (obj)).toString().intern();
            else
                cachedcomponentargs = null;
            providerinfo1.writePermission = cachedcomponentargs;
        }
        provider.info.grantUriPermissions = typedarray.getBoolean(13, false);
        provider.info.multiprocess = typedarray.getBoolean(9, false);
        provider.info.initOrder = typedarray.getInt(12, 0);
        cachedcomponentargs = typedarray.getNonConfigurationString(21, 0);
        if(!TextUtils.isEmpty(cachedcomponentargs))
            if(validateName(cachedcomponentargs, false, false) != null)
                Slog.w("PackageParser", (new StringBuilder()).append("Provider ").append(provider.info.name).append(" specified invalid splitName ").append(cachedcomponentargs).append(" at ").append(mArchiveSourcePath).toString());
            else
                provider.info.splitName = cachedcomponentargs;
        provider.info.flags = 0;
        if(typedarray.getBoolean(16, false))
        {
            cachedcomponentargs = provider.info;
            cachedcomponentargs.flags = ((ProviderInfo) (cachedcomponentargs)).flags | 0x40000000;
            if(provider.info.exported && (i & 0x80) == 0)
            {
                Slog.w("PackageParser", (new StringBuilder()).append("Provider exported request ignored due to singleUser: ").append(provider.className).append(" at ").append(mArchiveSourcePath).append(" ").append(xmlresourceparser.getPositionDescription()).toString());
                provider.info.exported = false;
            }
        }
        cachedcomponentargs = provider.info;
        flag = typedarray.getBoolean(18, false);
        provider.info.directBootAware = flag;
        cachedcomponentargs.encryptionAware = flag;
        if(provider.info.directBootAware)
        {
            cachedcomponentargs = package1.applicationInfo;
            cachedcomponentargs.privateFlags = ((ApplicationInfo) (cachedcomponentargs)).privateFlags | 0x100;
        }
        flag = typedarray.getBoolean(20, false);
        if(flag)
        {
            cachedcomponentargs = provider.info;
            cachedcomponentargs.flags = ((ProviderInfo) (cachedcomponentargs)).flags | 0x100000;
            package1.visibleToInstantApps = true;
        }
        typedarray.recycle();
        if((package1.applicationInfo.privateFlags & 2) != 0 && provider.info.processName == package1.packageName)
        {
            as[0] = "Heavy-weight applications can not have providers in main process";
            return null;
        }
        if(s == null)
        {
            as[0] = "<provider> does not include authorities attribute";
            return null;
        }
        if(s.length() <= 0)
        {
            as[0] = "<provider> has empty authorities attribute";
            return null;
        }
        provider.info.authority = s.intern();
        if(!parseProviderTags(resources, xmlresourceparser, flag, package1, provider, as))
            return null;
        else
            return provider;
    }

    private boolean parseProviderTags(Resources resources, XmlResourceParser xmlresourceparser, boolean flag, Package package1, Provider provider, String as[])
        throws XmlPullParserException, IOException
    {
        int i = xmlresourceparser.getDepth();
label0:
        do
        {
            int j = xmlresourceparser.next();
            if(j == 1 || j == 3 && xmlresourceparser.getDepth() <= i)
                break;
            if(j == 3 || j == 4)
                continue;
            if(xmlresourceparser.getName().equals("intent-filter"))
            {
                ProviderIntentInfo providerintentinfo = new ProviderIntentInfo(provider);
                if(!parseIntent(resources, xmlresourceparser, true, false, providerintentinfo, as))
                    return false;
                if(flag)
                {
                    providerintentinfo.setVisibilityToInstantApp(1);
                    ProviderInfo providerinfo = provider.info;
                    providerinfo.flags = providerinfo.flags | 0x100000;
                }
                provider.intents.add(providerintentinfo);
                continue;
            }
            if(xmlresourceparser.getName().equals("meta-data"))
            {
                Object obj = parseMetaData(resources, xmlresourceparser, provider.metaData, as);
                provider.metaData = ((Bundle) (obj));
                if(obj == null)
                    return false;
                if(flag || !provider.metaData.getBoolean("instantapps.clients.allowed"))
                    continue;
                boolean flag2 = true;
                obj = provider.info;
                obj.flags = ((ProviderInfo) (obj)).flags | 0x100000;
                package1.visibleToInstantApps = true;
                int k = provider.intents.size() - 1;
                do
                {
                    flag = flag2;
                    if(k < 0)
                        continue label0;
                    ((ProviderIntentInfo)provider.intents.get(k)).setVisibilityToInstantApp(1);
                    k--;
                } while(true);
            }
            if(xmlresourceparser.getName().equals("grant-uri-permission"))
            {
                TypedArray typedarray = resources.obtainAttributes(xmlresourceparser, com.android.internal.R.styleable.AndroidManifestGrantUriPermission);
                PatternMatcher patternmatcher = null;
                String s2 = typedarray.getNonConfigurationString(0, 0);
                if(s2 != null)
                    patternmatcher = new PatternMatcher(s2, 0);
                s2 = typedarray.getNonConfigurationString(1, 0);
                if(s2 != null)
                    patternmatcher = new PatternMatcher(s2, 1);
                s2 = typedarray.getNonConfigurationString(2, 0);
                if(s2 != null)
                    patternmatcher = new PatternMatcher(s2, 2);
                typedarray.recycle();
                if(patternmatcher != null)
                {
                    if(provider.info.uriPermissionPatterns == null)
                    {
                        provider.info.uriPermissionPatterns = new PatternMatcher[1];
                        provider.info.uriPermissionPatterns[0] = patternmatcher;
                    } else
                    {
                        int l = provider.info.uriPermissionPatterns.length;
                        PatternMatcher apatternmatcher[] = new PatternMatcher[l + 1];
                        System.arraycopy(provider.info.uriPermissionPatterns, 0, apatternmatcher, 0, l);
                        apatternmatcher[l] = patternmatcher;
                        provider.info.uriPermissionPatterns = apatternmatcher;
                    }
                    provider.info.grantUriPermissions = true;
                    XmlUtils.skipCurrentTag(xmlresourceparser);
                } else
                {
                    Slog.w("PackageParser", (new StringBuilder()).append("Unknown element under <path-permission>: ").append(xmlresourceparser.getName()).append(" at ").append(mArchiveSourcePath).append(" ").append(xmlresourceparser.getPositionDescription()).toString());
                    XmlUtils.skipCurrentTag(xmlresourceparser);
                }
            } else
            if(xmlresourceparser.getName().equals("path-permission"))
            {
                TypedArray typedarray1 = resources.obtainAttributes(xmlresourceparser, com.android.internal.R.styleable.AndroidManifestPathPermission);
                String s4 = null;
                String s3 = typedarray1.getNonConfigurationString(0, 0);
                String s1 = typedarray1.getNonConfigurationString(1, 0);
                String s = s1;
                if(s1 == null)
                    s = s3;
                String s5 = typedarray1.getNonConfigurationString(2, 0);
                s1 = s5;
                if(s5 == null)
                    s1 = s3;
                boolean flag1 = false;
                s3 = s;
                if(s != null)
                {
                    s3 = s.intern();
                    flag1 = true;
                }
                s = s1;
                if(s1 != null)
                {
                    s = s1.intern();
                    flag1 = true;
                }
                if(!flag1)
                {
                    Slog.w("PackageParser", (new StringBuilder()).append("No readPermission or writePermssion for <path-permission>: ").append(xmlresourceparser.getName()).append(" at ").append(mArchiveSourcePath).append(" ").append(xmlresourceparser.getPositionDescription()).toString());
                    XmlUtils.skipCurrentTag(xmlresourceparser);
                } else
                {
                    String s6 = typedarray1.getNonConfigurationString(3, 0);
                    PathPermission pathpermission = s4;
                    if(s6 != null)
                        pathpermission = new PathPermission(s6, 0, s3, s);
                    s4 = typedarray1.getNonConfigurationString(4, 0);
                    if(s4 != null)
                        pathpermission = new PathPermission(s4, 1, s3, s);
                    s4 = typedarray1.getNonConfigurationString(5, 0);
                    if(s4 != null)
                        pathpermission = new PathPermission(s4, 2, s3, s);
                    s4 = typedarray1.getNonConfigurationString(6, 0);
                    if(s4 != null)
                        pathpermission = new PathPermission(s4, 3, s3, s);
                    typedarray1.recycle();
                    if(pathpermission != null)
                    {
                        if(provider.info.pathPermissions == null)
                        {
                            provider.info.pathPermissions = new PathPermission[1];
                            provider.info.pathPermissions[0] = pathpermission;
                        } else
                        {
                            int i1 = provider.info.pathPermissions.length;
                            PathPermission apathpermission[] = new PathPermission[i1 + 1];
                            System.arraycopy(provider.info.pathPermissions, 0, apathpermission, 0, i1);
                            apathpermission[i1] = pathpermission;
                            provider.info.pathPermissions = apathpermission;
                        }
                        XmlUtils.skipCurrentTag(xmlresourceparser);
                    } else
                    {
                        Slog.w("PackageParser", (new StringBuilder()).append("No path, pathPrefix, or pathPattern for <path-permission>: ").append(xmlresourceparser.getName()).append(" at ").append(mArchiveSourcePath).append(" ").append(xmlresourceparser.getPositionDescription()).toString());
                        XmlUtils.skipCurrentTag(xmlresourceparser);
                    }
                }
            } else
            {
                Slog.w("PackageParser", (new StringBuilder()).append("Unknown element under <provider>: ").append(xmlresourceparser.getName()).append(" at ").append(mArchiveSourcePath).append(" ").append(xmlresourceparser.getPositionDescription()).toString());
                XmlUtils.skipCurrentTag(xmlresourceparser);
            }
        } while(true);
        return true;
    }

    public static final PublicKey parsePublicKey(String s)
    {
        if(s == null)
        {
            Slog.w("PackageParser", "Could not parse null public key");
            return null;
        }
        PublicKey publickey;
        try
        {
            s = new X509EncodedKeySpec(Base64.decode(s, 0));
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Slog.w("PackageParser", "Could not parse verifier public key; invalid Base64");
            return null;
        }
        publickey = KeyFactory.getInstance("RSA").generatePublic(s);
        return publickey;
        Object obj;
        obj;
        Slog.wtf("PackageParser", "Could not parse public key: RSA KeyFactory not included in build");
_L6:
        PublicKey publickey1 = KeyFactory.getInstance("EC").generatePublic(s);
        return publickey1;
        publickey1;
        Slog.wtf("PackageParser", "Could not parse public key: EC KeyFactory not included in build");
_L4:
        s = KeyFactory.getInstance("DSA").generatePublic(s);
        return s;
        s;
        Slog.wtf("PackageParser", "Could not parse public key: DSA KeyFactory not included in build");
_L2:
        return null;
        s;
        if(true) goto _L2; else goto _L1
_L1:
        publickey1;
        if(true) goto _L4; else goto _L3
_L3:
        publickey1;
        if(true) goto _L6; else goto _L5
_L5:
    }

    private Service parseService(Package package1, Resources resources, XmlResourceParser xmlresourceparser, int i, String as[], CachedComponentArgs cachedcomponentargs)
        throws XmlPullParserException, IOException
    {
        TypedArray typedarray = resources.obtainAttributes(xmlresourceparser, com.android.internal.R.styleable.AndroidManifestService);
        if(cachedcomponentargs.mServiceArgs == null)
        {
            cachedcomponentargs.mServiceArgs = new ParseComponentArgs(package1, as, 2, 0, 1, 15, 8, 12, mSeparateProcesses, 6, 7, 4);
            cachedcomponentargs.mServiceArgs.tag = "<service>";
        }
        cachedcomponentargs.mServiceArgs.sa = typedarray;
        cachedcomponentargs.mServiceArgs.flags = i;
        Service service = new Service(cachedcomponentargs.mServiceArgs, new ServiceInfo());
        if(as[0] != null)
        {
            typedarray.recycle();
            return null;
        }
        boolean flag = typedarray.hasValue(5);
        if(flag)
            service.info.exported = typedarray.getBoolean(5, false);
        cachedcomponentargs = typedarray.getNonConfigurationString(3, 0);
        boolean flag1;
        if(cachedcomponentargs == null)
        {
            service.info.permission = package1.applicationInfo.permission;
        } else
        {
            ServiceInfo serviceinfo = service.info;
            if(cachedcomponentargs.length() > 0)
                cachedcomponentargs = cachedcomponentargs.toString().intern();
            else
                cachedcomponentargs = null;
            serviceinfo.permission = cachedcomponentargs;
        }
        cachedcomponentargs = typedarray.getNonConfigurationString(17, 0);
        if(!TextUtils.isEmpty(cachedcomponentargs))
            if(validateName(cachedcomponentargs, false, false) != null)
                Slog.w("PackageParser", (new StringBuilder()).append("Service ").append(service.info.name).append(" specified invalid splitName ").append(cachedcomponentargs).append(" at ").append(mArchiveSourcePath).toString());
            else
                service.info.splitName = cachedcomponentargs;
        service.info.flags = 0;
        if(typedarray.getBoolean(9, false))
        {
            cachedcomponentargs = service.info;
            cachedcomponentargs.flags = ((ServiceInfo) (cachedcomponentargs)).flags | 1;
        }
        if(typedarray.getBoolean(10, false))
        {
            cachedcomponentargs = service.info;
            cachedcomponentargs.flags = ((ServiceInfo) (cachedcomponentargs)).flags | 2;
        }
        if(typedarray.getBoolean(14, false))
        {
            cachedcomponentargs = service.info;
            cachedcomponentargs.flags = ((ServiceInfo) (cachedcomponentargs)).flags | 4;
        }
        flag1 = flag;
        if(typedarray.getBoolean(11, false))
        {
            cachedcomponentargs = service.info;
            cachedcomponentargs.flags = ((ServiceInfo) (cachedcomponentargs)).flags | 0x40000000;
            flag1 = flag;
            if(service.info.exported)
            {
                flag1 = flag;
                if((i & 0x80) == 0)
                {
                    Slog.w("PackageParser", (new StringBuilder()).append("Service exported request ignored due to singleUser: ").append(service.className).append(" at ").append(mArchiveSourcePath).append(" ").append(xmlresourceparser.getPositionDescription()).toString());
                    service.info.exported = false;
                    flag1 = true;
                }
            }
        }
        cachedcomponentargs = service.info;
        flag = typedarray.getBoolean(13, false);
        service.info.directBootAware = flag;
        cachedcomponentargs.encryptionAware = flag;
        if(service.info.directBootAware)
        {
            cachedcomponentargs = package1.applicationInfo;
            cachedcomponentargs.privateFlags = ((ApplicationInfo) (cachedcomponentargs)).privateFlags | 0x100;
        }
        flag = typedarray.getBoolean(16, false);
        if(flag)
        {
            cachedcomponentargs = service.info;
            cachedcomponentargs.flags = ((ServiceInfo) (cachedcomponentargs)).flags | 0x100000;
            package1.visibleToInstantApps = true;
        }
        typedarray.recycle();
        if((package1.applicationInfo.privateFlags & 2) != 0 && service.info.processName == package1.packageName)
        {
            as[0] = "Heavy-weight applications can not have services in main process";
            return null;
        }
        int j = xmlresourceparser.getDepth();
label0:
        do
        {
            i = xmlresourceparser.next();
            if(i == 1 || i == 3 && xmlresourceparser.getDepth() <= j)
                break;
            if(i == 3 || i == 4)
                continue;
            if(xmlresourceparser.getName().equals("intent-filter"))
            {
                ServiceIntentInfo serviceintentinfo = new ServiceIntentInfo(service);
                if(!parseIntent(resources, xmlresourceparser, true, false, serviceintentinfo, as))
                    return null;
                if(flag)
                {
                    serviceintentinfo.setVisibilityToInstantApp(1);
                    cachedcomponentargs = service.info;
                    cachedcomponentargs.flags = ((ServiceInfo) (cachedcomponentargs)).flags | 0x100000;
                }
                service.intents.add(serviceintentinfo);
                continue;
            }
            if(xmlresourceparser.getName().equals("meta-data"))
            {
                cachedcomponentargs = parseMetaData(resources, xmlresourceparser, service.metaData, as);
                service.metaData = cachedcomponentargs;
                if(cachedcomponentargs == null)
                    return null;
                if(flag || !service.metaData.getBoolean("instantapps.clients.allowed"))
                    continue;
                boolean flag3 = true;
                cachedcomponentargs = service.info;
                cachedcomponentargs.flags = ((ServiceInfo) (cachedcomponentargs)).flags | 0x100000;
                package1.visibleToInstantApps = true;
                i = service.intents.size() - 1;
                do
                {
                    flag = flag3;
                    if(i < 0)
                        continue label0;
                    ((ServiceIntentInfo)service.intents.get(i)).setVisibilityToInstantApp(1);
                    i--;
                } while(true);
            }
            Slog.w("PackageParser", (new StringBuilder()).append("Unknown element under <service>: ").append(xmlresourceparser.getName()).append(" at ").append(mArchiveSourcePath).append(" ").append(xmlresourceparser.getPositionDescription()).toString());
            XmlUtils.skipCurrentTag(xmlresourceparser);
        } while(true);
        if(!flag1)
        {
            package1 = service.info;
            boolean flag2;
            if(service.intents.size() > 0)
                flag2 = true;
            else
                flag2 = false;
            package1.exported = flag2;
        }
        return service;
    }

    private Package parseSplitApk(Package package1, Resources resources, XmlResourceParser xmlresourceparser, int i, int j, String as[])
        throws XmlPullParserException, IOException, PackageParserException
    {
        parsePackageSplitNames(xmlresourceparser, xmlresourceparser);
        mParseInstrumentationArgs = null;
        boolean flag = false;
        int k = xmlresourceparser.getDepth();
        do
        {
            int l = xmlresourceparser.next();
            if(l == 1 || l == 3 && xmlresourceparser.getDepth() <= k)
                break;
            if(l != 3 && l != 4)
                if(xmlresourceparser.getName().equals("application"))
                {
                    if(flag)
                    {
                        Slog.w("PackageParser", "<manifest> has more than one <application>");
                        XmlUtils.skipCurrentTag(xmlresourceparser);
                    } else
                    {
                        flag = true;
                        if(!parseSplitApplication(package1, resources, xmlresourceparser, i, j, as))
                            return null;
                    }
                } else
                {
                    Slog.w("PackageParser", (new StringBuilder()).append("Unknown element under <manifest>: ").append(xmlresourceparser.getName()).append(" at ").append(mArchiveSourcePath).append(" ").append(xmlresourceparser.getPositionDescription()).toString());
                    XmlUtils.skipCurrentTag(xmlresourceparser);
                }
        } while(true);
        if(!flag)
        {
            as[0] = "<manifest> does not contain an <application>";
            mParseError = -109;
        }
        return package1;
    }

    private void parseSplitApk(Package package1, int i, AssetManager assetmanager, int j)
        throws PackageParserException
    {
        String s;
        int k;
        s = package1.splitCodePaths[i];
        mParseError = 1;
        mArchiveSourcePath = s;
        k = loadApkIntoAssetManager(assetmanager, s, j);
        Object obj;
        XmlResourceParser xmlresourceparser;
        obj = JVM INSTR new #1451 <Class Resources>;
        ((Resources) (obj)).Resources(assetmanager, mMetrics, null);
        assetmanager.setConfiguration(0, 0, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, android.os.Build.VERSION.RESOURCES_SDK_INT);
        xmlresourceparser = assetmanager.openXmlResourceParser(k, "AndroidManifest.xml");
        assetmanager = xmlresourceparser;
        String as[] = new String[1];
        assetmanager = xmlresourceparser;
        if(parseSplitApk(package1, ((Resources) (obj)), xmlresourceparser, j, i, as) != null) goto _L2; else goto _L1
_L1:
        assetmanager = xmlresourceparser;
        obj = JVM INSTR new #53  <Class PackageParser$PackageParserException>;
        assetmanager = xmlresourceparser;
        i = mParseError;
        assetmanager = xmlresourceparser;
        package1 = JVM INSTR new #465 <Class StringBuilder>;
        assetmanager = xmlresourceparser;
        package1.StringBuilder();
        assetmanager = xmlresourceparser;
        ((PackageParserException) (obj)).PackageParserException(i, package1.append(s).append(" (at ").append(xmlresourceparser.getPositionDescription()).append("): ").append(as[0]).toString());
        assetmanager = xmlresourceparser;
        throw obj;
        package1;
        assetmanager = xmlresourceparser;
_L4:
        throw package1;
        package1;
_L3:
        IoUtils.closeQuietly(assetmanager);
        throw package1;
_L2:
        IoUtils.closeQuietly(xmlresourceparser);
        return;
        package1;
        xmlresourceparser = null;
_L5:
        assetmanager = xmlresourceparser;
        obj = JVM INSTR new #53  <Class PackageParser$PackageParserException>;
        assetmanager = xmlresourceparser;
        StringBuilder stringbuilder = JVM INSTR new #465 <Class StringBuilder>;
        assetmanager = xmlresourceparser;
        stringbuilder.StringBuilder();
        assetmanager = xmlresourceparser;
        ((PackageParserException) (obj)).PackageParserException(-102, stringbuilder.append("Failed to read manifest from ").append(s).toString(), package1);
        assetmanager = xmlresourceparser;
        throw obj;
        package1;
        assetmanager = null;
          goto _L3
        package1;
        assetmanager = null;
          goto _L4
        package1;
          goto _L5
    }

    private boolean parseSplitApplication(Package package1, Resources resources, XmlResourceParser xmlresourceparser, int i, int j, String as[])
        throws XmlPullParserException, IOException
    {
        Object obj;
        TypedArray typedarray = resources.obtainAttributes(xmlresourceparser, com.android.internal.R.styleable.AndroidManifestApplication);
        if(typedarray.getBoolean(7, true))
        {
            int ai[] = package1.splitFlags;
            ai[j] = ai[j] | 4;
        }
        obj = typedarray.getString(46);
        if(obj != null && !ClassLoaderFactory.isValidClassLoaderName(((String) (obj)))) goto _L2; else goto _L1
_L1:
        int k;
        package1.applicationInfo.splitClassLoaderNames[j] = ((String) (obj));
        k = xmlresourceparser.getDepth();
_L13:
        Object obj1;
        int l = xmlresourceparser.next();
        if(l == 1 || l == 3 && xmlresourceparser.getDepth() <= k)
            break; /* Loop/switch isn't completed */
        if(l == 3 || l == 4)
            continue; /* Loop/switch isn't completed */
        obj = null;
        CachedComponentArgs cachedcomponentargs = new CachedComponentArgs(null);
        obj1 = xmlresourceparser.getName();
        if(((String) (obj1)).equals("activity"))
            break MISSING_BLOCK_LABEL_156;
        if(((String) (obj1)).equals("receiver"))
        {
            obj = parseActivity(package1, resources, xmlresourceparser, i, as, cachedcomponentargs, true, false);
            if(obj == null)
            {
                mParseError = -108;
                return false;
            }
            package1.receivers.add(obj);
            obj = ((Activity) (obj)).info;
            continue; /* Loop/switch isn't completed */
        }
        if(((String) (obj1)).equals("service"))
        {
            obj = parseService(package1, resources, xmlresourceparser, i, as, cachedcomponentargs);
            if(obj == null)
            {
                mParseError = -108;
                return false;
            }
            package1.services.add(obj);
            obj = ((Service) (obj)).info;
            continue; /* Loop/switch isn't completed */
        }
        if(((String) (obj1)).equals("provider"))
        {
            obj = parseProvider(package1, resources, xmlresourceparser, i, as, cachedcomponentargs);
            if(obj == null)
            {
                mParseError = -108;
                return false;
            }
            package1.providers.add(obj);
            obj = ((Provider) (obj)).info;
            continue; /* Loop/switch isn't completed */
        }
        if(((String) (obj1)).equals("activity-alias"))
        {
            obj = parseActivityAlias(package1, resources, xmlresourceparser, i, as, cachedcomponentargs);
            if(obj == null)
            {
                mParseError = -108;
                return false;
            }
            package1.activities.add(obj);
            obj = ((Activity) (obj)).info;
            continue; /* Loop/switch isn't completed */
        }
        if(xmlresourceparser.getName().equals("meta-data"))
        {
            Bundle bundle = parseMetaData(resources, xmlresourceparser, package1.mAppMetaData, as);
            package1.mAppMetaData = bundle;
            if(bundle == null)
            {
                mParseError = -108;
                return false;
            }
            continue; /* Loop/switch isn't completed */
        }
        if(((String) (obj1)).equals("uses-static-library"))
        {
            if(!parseUsesStaticLibrary(package1, resources, xmlresourceparser, as))
                return false;
            continue; /* Loop/switch isn't completed */
        }
          goto _L3
_L2:
        as[0] = (new StringBuilder()).append("Invalid class loader name: ").append(((String) (obj))).toString();
        mParseError = -108;
        return false;
        obj = parseActivity(package1, resources, xmlresourceparser, i, as, cachedcomponentargs, false, package1.baseHardwareAccelerated);
        if(obj == null)
        {
            mParseError = -108;
            return false;
        }
        package1.activities.add(obj);
        obj = ((Activity) (obj)).info;
_L11:
        if(obj != null && ((ComponentInfo) (obj)).splitName == null)
            obj.splitName = package1.splitNames[j];
        continue; /* Loop/switch isn't completed */
_L3:
        if(!((String) (obj1)).equals("uses-library")) goto _L5; else goto _L4
_L4:
        String s;
        boolean flag;
        obj1 = resources.obtainAttributes(xmlresourceparser, com.android.internal.R.styleable.AndroidManifestUsesLibrary);
        s = ((TypedArray) (obj1)).getNonResourceString(0);
        flag = ((TypedArray) (obj1)).getBoolean(1, true);
        ((TypedArray) (obj1)).recycle();
        if(s == null) goto _L7; else goto _L6
_L6:
        s = s.intern();
        if(!flag) goto _L9; else goto _L8
_L8:
        package1.usesLibraries = ArrayUtils.add(package1.usesLibraries, s);
        package1.usesOptionalLibraries = ArrayUtils.remove(package1.usesOptionalLibraries, s);
_L7:
        XmlUtils.skipCurrentTag(xmlresourceparser);
        continue; /* Loop/switch isn't completed */
_L9:
        if(!ArrayUtils.contains(package1.usesLibraries, s))
            package1.usesOptionalLibraries = ArrayUtils.add(package1.usesOptionalLibraries, s);
        if(true) goto _L7; else goto _L5
_L5:
        if(!((String) (obj1)).equals("uses-package"))
            break; /* Loop/switch isn't completed */
        XmlUtils.skipCurrentTag(xmlresourceparser);
        if(true) goto _L11; else goto _L10
_L10:
        Slog.w("PackageParser", (new StringBuilder()).append("Unknown element under <application>: ").append(((String) (obj1))).append(" at ").append(mArchiveSourcePath).append(" ").append(xmlresourceparser.getPositionDescription()).toString());
        XmlUtils.skipCurrentTag(xmlresourceparser);
        if(true) goto _L13; else goto _L12
_L12:
        return true;
    }

    private FeatureInfo parseUsesFeature(Resources resources, AttributeSet attributeset)
    {
        FeatureInfo featureinfo = new FeatureInfo();
        resources = resources.obtainAttributes(attributeset, com.android.internal.R.styleable.AndroidManifestUsesFeature);
        featureinfo.name = resources.getNonResourceString(0);
        featureinfo.version = resources.getInt(3, 0);
        if(featureinfo.name == null)
            featureinfo.reqGlEsVersion = resources.getInt(1, 0);
        if(resources.getBoolean(2, true))
            featureinfo.flags = featureinfo.flags | 1;
        resources.recycle();
        return featureinfo;
    }

    private boolean parseUsesPermission(Package package1, Resources resources, XmlResourceParser xmlresourceparser)
        throws XmlPullParserException, IOException
    {
        TypedArray typedarray = resources.obtainAttributes(xmlresourceparser, com.android.internal.R.styleable.AndroidManifestUsesPermission);
        resources = typedarray.getNonResourceString(0);
        boolean flag = false;
        Object obj = typedarray.peekValue(1);
        int i = ((flag) ? 1 : 0);
        if(obj != null)
        {
            i = ((flag) ? 1 : 0);
            if(((TypedValue) (obj)).type >= 16)
            {
                i = ((flag) ? 1 : 0);
                if(((TypedValue) (obj)).type <= 31)
                    i = ((TypedValue) (obj)).data;
            }
        }
        obj = typedarray.getNonConfigurationString(2, 0);
        String s = typedarray.getNonConfigurationString(3, 0);
        typedarray.recycle();
        XmlUtils.skipCurrentTag(xmlresourceparser);
        if(resources == null)
            return true;
        if(i != 0 && i < android.os.Build.VERSION.RESOURCES_SDK_INT)
            return true;
        if(obj != null && mCallback != null && mCallback.hasFeature(((String) (obj))) ^ true)
            return true;
        if(s != null && mCallback != null && mCallback.hasFeature(s))
            return true;
        if(package1.requestedPermissions.indexOf(resources) == -1)
            package1.requestedPermissions.add(resources.intern());
        else
            Slog.w("PackageParser", (new StringBuilder()).append("Ignoring duplicate uses-permissions/uses-permissions-sdk-m: ").append(resources).append(" in package: ").append(package1.packageName).append(" at: ").append(xmlresourceparser.getPositionDescription()).toString());
        return true;
    }

    private boolean parseUsesStaticLibrary(Package package1, Resources resources, XmlResourceParser xmlresourceparser, String as[])
        throws XmlPullParserException, IOException
    {
        Object obj = resources.obtainAttributes(xmlresourceparser, com.android.internal.R.styleable.AndroidManifestUsesStaticLibrary);
        String s = ((TypedArray) (obj)).getNonResourceString(0);
        int i = ((TypedArray) (obj)).getInt(1, -1);
        String s1 = ((TypedArray) (obj)).getNonResourceString(2);
        ((TypedArray) (obj)).recycle();
        while(s == null || i < 0 || s1 == null) 
        {
            as[0] = (new StringBuilder()).append("Bad uses-static-library declaration name: ").append(s).append(" version: ").append(i).append(" certDigest").append(s1).toString();
            mParseError = -108;
            XmlUtils.skipCurrentTag(xmlresourceparser);
            return false;
        }
        if(package1.usesStaticLibraries != null && package1.usesStaticLibraries.contains(s))
        {
            as[0] = (new StringBuilder()).append("Depending on multiple versions of static library ").append(s).toString();
            mParseError = -108;
            XmlUtils.skipCurrentTag(xmlresourceparser);
            return false;
        }
        obj = s.intern();
        s = s1.replace(":", "").toLowerCase();
        String as1[] = EmptyArray.STRING;
        if(package1.applicationInfo.targetSdkVersion > 26)
        {
            xmlresourceparser = parseAdditionalCertificates(resources, xmlresourceparser, as);
            resources = xmlresourceparser;
            if(xmlresourceparser == null)
                return false;
        } else
        {
            XmlUtils.skipCurrentTag(xmlresourceparser);
            resources = as1;
        }
        xmlresourceparser = new String[resources.length + 1];
        xmlresourceparser[0] = s;
        System.arraycopy(resources, 0, xmlresourceparser, 1, resources.length);
        package1.usesStaticLibraries = ArrayUtils.add(package1.usesStaticLibraries, obj);
        package1.usesStaticLibrariesVersions = ArrayUtils.appendInt(package1.usesStaticLibrariesVersions, i, true);
        package1.usesStaticLibrariesCertDigests = (String[][])ArrayUtils.appendElement([Ljava/lang/String;, package1.usesStaticLibrariesCertDigests, xmlresourceparser, true);
        return true;
    }

    private static VerifierInfo parseVerifier(AttributeSet attributeset)
    {
        String s;
        String s1;
        int i;
        int j;
        s = null;
        s1 = null;
        i = attributeset.getAttributeCount();
        j = 0;
_L2:
        if(j >= i)
            break MISSING_BLOCK_LABEL_86;
        switch(attributeset.getAttributeNameResource(j))
        {
        default:
            break;

        case 16842755: 
            break; /* Loop/switch isn't completed */

        case 16843686: 
            break;
        }
        break MISSING_BLOCK_LABEL_74;
_L3:
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        s = attributeset.getAttributeValue(j);
          goto _L3
        s1 = attributeset.getAttributeValue(j);
          goto _L3
        if(s == null || s.length() == 0)
        {
            Slog.i("PackageParser", "verifier package name was null; skipping");
            return null;
        }
        attributeset = parsePublicKey(s1);
        if(attributeset == null)
        {
            Slog.i("PackageParser", (new StringBuilder()).append("Unable to parse verifier public key for ").append(s).toString());
            return null;
        } else
        {
            return new VerifierInfo(s, attributeset);
        }
    }

    public static void populateCertificates(Package package1, Certificate acertificate[][])
        throws PackageParserException
    {
        package1.mCertificates = null;
        package1.mSignatures = null;
        package1.mSigningKeys = null;
        package1.mCertificates = acertificate;
        try
        {
            package1.mSignatures = convertToSignatures(acertificate);
        }
        // Misplaced declaration of an exception variable
        catch(Certificate acertificate[][])
        {
            throw new PackageParserException(-103, (new StringBuilder()).append("Failed to collect certificates from ").append(package1.baseCodePath).toString(), acertificate);
        }
        package1.mSigningKeys = new ArraySet(acertificate.length);
        for(int i = 0; i < acertificate.length; i++)
        {
            Certificate certificate = acertificate[i][0];
            package1.mSigningKeys.add(certificate.getPublicKey());
        }

        int j;
        int k;
        if(package1.childPackages != null)
            j = package1.childPackages.size();
        else
            j = 0;
        for(k = 0; k < j; k++)
        {
            acertificate = (Package)package1.childPackages.get(k);
            acertificate.mCertificates = package1.mCertificates;
            acertificate.mSignatures = package1.mSignatures;
            acertificate.mSigningKeys = package1.mSigningKeys;
        }

    }

    public static long readFullyIgnoringContents(InputStream inputstream)
        throws IOException
    {
        byte abyte0[] = (byte[])sBuffer.getAndSet(null);
        byte abyte1[] = abyte0;
        if(abyte0 == null)
            abyte1 = new byte[4096];
        int i = 0;
        do
        {
            int j = inputstream.read(abyte1, 0, abyte1.length);
            if(j != -1)
            {
                i += j;
            } else
            {
                sBuffer.set(abyte1);
                return (long)i;
            }
        } while(true);
    }

    private void setActivityResizeMode(ActivityInfo activityinfo, TypedArray typedarray, Package package1)
    {
        boolean flag;
        if((package1.applicationInfo.privateFlags & 0xc00) != 0)
            flag = true;
        else
            flag = false;
        if(typedarray.hasValue(40) || flag)
        {
            boolean flag1;
            if((package1.applicationInfo.privateFlags & 0x400) != 0)
                flag1 = true;
            else
                flag1 = false;
            if(typedarray.getBoolean(40, flag1))
                activityinfo.resizeMode = 2;
            else
                activityinfo.resizeMode = 0;
            return;
        }
        if((package1.applicationInfo.privateFlags & 0x1000) != 0)
        {
            activityinfo.resizeMode = 1;
            return;
        }
        if(activityinfo.isFixedOrientationPortrait())
            activityinfo.resizeMode = 6;
        else
        if(activityinfo.isFixedOrientationLandscape())
            activityinfo.resizeMode = 5;
        else
        if(activityinfo.isFixedOrientation())
            activityinfo.resizeMode = 7;
        else
            activityinfo.resizeMode = 4;
    }

    public static void setCompatibilityModeEnabled(boolean flag)
    {
        sCompatibilityModeEnabled = flag;
    }

    private void setMaxAspectRatio(Package package1)
    {
        float f;
        float f1;
        if(package1.applicationInfo.targetSdkVersion < 26)
            f = 1.86F;
        else
            f = 0.0F;
        if(package1.applicationInfo.maxAspectRatio != 0.0F)
        {
            f1 = package1.applicationInfo.maxAspectRatio;
        } else
        {
            f1 = f;
            if(package1.mAppMetaData != null)
            {
                f1 = f;
                if(package1.mAppMetaData.containsKey("android.max_aspect"))
                    f1 = package1.mAppMetaData.getFloat("android.max_aspect", f);
            }
        }
        package1 = package1.activities.iterator();
        do
        {
            if(!package1.hasNext())
                break;
            Activity activity = (Activity)package1.next();
            if(!Activity._2D_wrap0(activity))
            {
                if(activity.metaData != null)
                    f = activity.metaData.getFloat("android.max_aspect", f1);
                else
                    f = f1;
                Activity._2D_wrap1(activity, f);
            }
        } while(true);
    }

    public static byte[] toCacheEntryStatic(Package package1)
    {
        Parcel parcel = Parcel.obtain();
        PackageParserCacheHelper.WriteHelper writehelper = new PackageParserCacheHelper.WriteHelper(parcel);
        package1.writeToParcel(parcel, 0);
        writehelper.finishAndUninstall();
        package1 = parcel.marshall();
        parcel.recycle();
        return package1;
    }

    private static void updateApplicationInfo(ApplicationInfo applicationinfo, int i, PackageUserState packageuserstate)
    {
        boolean flag;
        flag = true;
        if(!sCompatibilityModeEnabled)
            applicationinfo.disableCompatibilityMode();
        if(packageuserstate.installed)
            applicationinfo.flags = applicationinfo.flags | 0x800000;
        else
            applicationinfo.flags = applicationinfo.flags & 0xff7fffff;
        if(packageuserstate.suspended)
            applicationinfo.flags = applicationinfo.flags | 0x40000000;
        else
            applicationinfo.flags = applicationinfo.flags & 0xbfffffff;
        if(packageuserstate.instantApp)
            applicationinfo.privateFlags = applicationinfo.privateFlags | 0x80;
        else
            applicationinfo.privateFlags = applicationinfo.privateFlags & 0xffffff7f;
        if(packageuserstate.virtualPreload)
            applicationinfo.privateFlags = applicationinfo.privateFlags | 0x10000;
        else
            applicationinfo.privateFlags = applicationinfo.privateFlags & 0xfffeffff;
        if(packageuserstate.hidden)
            applicationinfo.privateFlags = applicationinfo.privateFlags | 1;
        else
            applicationinfo.privateFlags = applicationinfo.privateFlags & -2;
        if(packageuserstate.enabled != 1) goto _L2; else goto _L1
_L1:
        applicationinfo.enabled = true;
_L4:
        applicationinfo.enabledSetting = packageuserstate.enabled;
        if(applicationinfo.category == -1)
            applicationinfo.category = packageuserstate.categoryHint;
        if(applicationinfo.category == -1)
            applicationinfo.category = FallbackCategoryProvider.getFallbackCategory(applicationinfo.packageName);
        applicationinfo.seInfoUser = SELinuxUtil.assignSeinfoUser(packageuserstate);
        applicationinfo.resourceDirs = packageuserstate.overlayPaths;
        return;
_L2:
        if(packageuserstate.enabled == 4)
        {
            if((0x8000 & i) == 0)
                flag = false;
            applicationinfo.enabled = flag;
        } else
        if(packageuserstate.enabled == 2 || packageuserstate.enabled == 3)
            applicationinfo.enabled = false;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static String validateName(String s, boolean flag, boolean flag1)
    {
        int i;
        boolean flag2;
        boolean flag3;
        int j;
        i = s.length();
        flag2 = false;
        flag3 = true;
        j = 0;
_L10:
        char c;
        if(j >= i)
            break MISSING_BLOCK_LABEL_163;
        c = s.charAt(j);
          goto _L1
_L3:
        boolean flag4;
        boolean flag5;
        flag4 = false;
        flag5 = flag2;
_L8:
        j++;
        flag3 = flag4;
        flag2 = flag5;
        continue; /* Loop/switch isn't completed */
_L1:
        if(c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') goto _L3; else goto _L2
_L2:
        if(flag3) goto _L5; else goto _L4
_L4:
        if(c < '0') goto _L7; else goto _L6
_L6:
        flag4 = flag3;
        flag5 = flag2;
        if(c <= '9') goto _L8; else goto _L7
_L7:
        flag4 = flag3;
        flag5 = flag2;
        if(c == '_') goto _L8; else goto _L5
_L5:
        if(c == '.')
        {
            flag5 = true;
            flag4 = true;
        } else
        {
            return (new StringBuilder()).append("bad character '").append(c).append("'").toString();
        }
          goto _L8
        if(flag1 && FileUtils.isValidExtFilename(s) ^ true)
            return "Invalid filename";
        if(flag2 || flag ^ true)
            s = null;
        else
            s = "must have at least one '.' separator";
        return s;
        if(true) goto _L10; else goto _L9
_L9:
    }

    protected Package fromCacheEntry(byte abyte0[])
    {
        return fromCacheEntryStatic(abyte0);
    }

    public Package parseMonolithicPackage(File file, int i)
        throws PackageParserException
    {
        AssetManager assetmanager;
        PackageLite packagelite;
        assetmanager = newConfiguredAssetManager();
        packagelite = parseMonolithicPackageLite(file, i);
        if(mOnlyCoreApps && !packagelite.coreApp)
            throw new PackageParserException(-108, (new StringBuilder()).append("Not a coreApp: ").append(file).toString());
        Package package1;
        package1 = parseBaseApk(file, assetmanager, i);
        package1.setCodePath(file.getAbsolutePath());
        package1.setUse32bitAbi(packagelite.use32bitAbi);
        IoUtils.closeQuietly(assetmanager);
        return package1;
        file;
        IoUtils.closeQuietly(assetmanager);
        throw file;
    }

    public Package parsePackage(File file, int i)
        throws PackageParserException
    {
        return parsePackage(file, i, false);
    }

    public Package parsePackage(File file, int i, boolean flag)
        throws PackageParserException
    {
        Package package1;
        if(flag)
            package1 = getCachedResult(file, i);
        else
            package1 = null;
        if(package1 != null)
            return package1;
        long l;
        long l1;
        if(LOG_PARSE_TIMINGS)
            l = SystemClock.uptimeMillis();
        else
            l = 0L;
        if(file.isDirectory())
            package1 = parseClusterPackage(file, i);
        else
            package1 = parseMonolithicPackage(file, i);
        if(LOG_PARSE_TIMINGS)
            l1 = SystemClock.uptimeMillis();
        else
            l1 = 0L;
        cacheResult(file, i, package1);
        if(LOG_PARSE_TIMINGS)
        {
            l = l1 - l;
            l1 = SystemClock.uptimeMillis() - l1;
            if(l + l1 > 100L)
                Slog.i("PackageParser", (new StringBuilder()).append("Parse times for '").append(file).append("': parse=").append(l).append("ms, update_cache=").append(l1).append(" ms").toString());
        }
        return package1;
    }

    public void setCacheDir(File file)
    {
        mCacheDir = file;
    }

    public void setCallback(Callback callback)
    {
        mCallback = callback;
    }

    public void setDisplayMetrics(DisplayMetrics displaymetrics)
    {
        mMetrics = displaymetrics;
    }

    public void setOnlyCoreApps(boolean flag)
    {
        mOnlyCoreApps = flag;
    }

    public void setSeparateProcesses(String as[])
    {
        mSeparateProcesses = as;
    }

    protected byte[] toCacheEntry(Package package1)
    {
        return toCacheEntryStatic(package1);
    }

    private static final String ANDROID_MANIFEST_FILENAME = "AndroidManifest.xml";
    private static final String ANDROID_RESOURCES = "http://schemas.android.com/apk/res/android";
    public static final int APK_SIGNING_UNKNOWN = 0;
    public static final int APK_SIGNING_V1 = 1;
    public static final int APK_SIGNING_V2 = 2;
    private static final Set CHILD_PACKAGE_TAGS;
    private static final boolean DEBUG_BACKUP = false;
    private static final boolean DEBUG_JAR = false;
    private static final boolean DEBUG_PARSER = false;
    private static final float DEFAULT_PRE_O_MAX_ASPECT_RATIO = 1.86F;
    private static final boolean LOG_PARSE_TIMINGS;
    private static final int LOG_PARSE_TIMINGS_THRESHOLD_MS = 100;
    private static final boolean LOG_UNSAFE_BROADCASTS = false;
    private static final int MAX_PACKAGES_PER_APK = 5;
    private static final String METADATA_MAX_ASPECT_RATIO = "android.max_aspect";
    private static final String META_DATA_INSTANT_APPS = "instantapps.clients.allowed";
    private static final String MNT_EXPAND = "/mnt/expand/";
    private static final boolean MULTI_PACKAGE_APK_ENABLED;
    public static final NewPermissionInfo NEW_PERMISSIONS[] = {
        new NewPermissionInfo("android.permission.WRITE_EXTERNAL_STORAGE", 4, 0), new NewPermissionInfo("android.permission.READ_PHONE_STATE", 4, 0)
    };
    private static final int NUMBER_OF_CORES;
    public static final int PARSE_CHATTY = 2;
    public static final int PARSE_COLLECT_CERTIFICATES = 256;
    private static final int PARSE_DEFAULT_INSTALL_LOCATION = -1;
    private static final int PARSE_DEFAULT_TARGET_SANDBOX = 1;
    public static final int PARSE_ENFORCE_CODE = 1024;
    public static final int PARSE_EXTERNAL_STORAGE = 32;
    public static final int PARSE_FORCE_SDK = 4096;
    public static final int PARSE_FORWARD_LOCK = 16;
    public static final int PARSE_IGNORE_PROCESSES = 8;
    public static final int PARSE_IS_EPHEMERAL = 2048;
    public static final int PARSE_IS_PRIVILEGED = 128;
    public static final int PARSE_IS_SYSTEM = 1;
    public static final int PARSE_IS_SYSTEM_DIR = 64;
    public static final int PARSE_MUST_BE_APK = 4;
    public static final int PARSE_TRUSTED_OVERLAY = 512;
    private static final String PROPERTY_CHILD_PACKAGES_ENABLED = "persist.sys.child_packages_enabled";
    private static final int RECREATE_ON_CONFIG_CHANGES_MASK = 3;
    private static final boolean RIGID_PARSER = false;
    private static final Set SAFE_BROADCASTS;
    private static final String SDK_CODENAMES[];
    private static final int SDK_VERSION;
    public static final SplitPermissionInfo SPLIT_PERMISSIONS[] = {
        new SplitPermissionInfo("android.permission.WRITE_EXTERNAL_STORAGE", new String[] {
            "android.permission.READ_EXTERNAL_STORAGE"
        }, 10001), new SplitPermissionInfo("android.permission.READ_CONTACTS", new String[] {
            "android.permission.READ_CALL_LOG"
        }, 16), new SplitPermissionInfo("android.permission.WRITE_CONTACTS", new String[] {
            "android.permission.WRITE_CALL_LOG"
        }, 16)
    };
    private static final String TAG = "PackageParser";
    private static final String TAG_ADOPT_PERMISSIONS = "adopt-permissions";
    private static final String TAG_APPLICATION = "application";
    private static final String TAG_COMPATIBLE_SCREENS = "compatible-screens";
    private static final String TAG_EAT_COMMENT = "eat-comment";
    private static final String TAG_FEATURE_GROUP = "feature-group";
    private static final String TAG_INSTRUMENTATION = "instrumentation";
    private static final String TAG_KEY_SETS = "key-sets";
    private static final String TAG_MANIFEST = "manifest";
    private static final String TAG_ORIGINAL_PACKAGE = "original-package";
    private static final String TAG_OVERLAY = "overlay";
    private static final String TAG_PACKAGE = "package";
    private static final String TAG_PACKAGE_VERIFIER = "package-verifier";
    private static final String TAG_PERMISSION = "permission";
    private static final String TAG_PERMISSION_GROUP = "permission-group";
    private static final String TAG_PERMISSION_TREE = "permission-tree";
    private static final String TAG_PROTECTED_BROADCAST = "protected-broadcast";
    private static final String TAG_RESTRICT_UPDATE = "restrict-update";
    private static final String TAG_SUPPORTS_INPUT = "supports-input";
    private static final String TAG_SUPPORT_SCREENS = "supports-screens";
    private static final String TAG_USES_CONFIGURATION = "uses-configuration";
    private static final String TAG_USES_FEATURE = "uses-feature";
    private static final String TAG_USES_GL_TEXTURE = "uses-gl-texture";
    private static final String TAG_USES_PERMISSION = "uses-permission";
    private static final String TAG_USES_PERMISSION_SDK_23 = "uses-permission-sdk-23";
    private static final String TAG_USES_PERMISSION_SDK_M = "uses-permission-sdk-m";
    private static final String TAG_USES_SDK = "uses-sdk";
    private static final String TAG_USES_SPLIT = "uses-split";
    private static AtomicReference sBuffer = new AtomicReference();
    public static final AtomicInteger sCachedPackageReadCount = new AtomicInteger();
    private static boolean sCompatibilityModeEnabled = true;
    private static boolean sIsPerfLockAcquired = false;
    private static BoostFramework sPerfBoost = null;
    private static final Comparator sSplitNameComparator = new SplitNameComparator(null);
    private String mArchiveSourcePath;
    private File mCacheDir;
    private Callback mCallback;
    private DisplayMetrics mMetrics;
    private boolean mOnlyCoreApps;
    private int mParseError;
    private ParsePackageItemArgs mParseInstrumentationArgs;
    private String mSeparateProcesses[];

    static 
    {
        LOG_PARSE_TIMINGS = Build.IS_DEBUGGABLE;
        boolean flag;
        int i;
        if(Build.IS_DEBUGGABLE)
            flag = SystemProperties.getBoolean("persist.sys.child_packages_enabled", false);
        else
            flag = false;
        MULTI_PACKAGE_APK_ENABLED = flag;
        if(Runtime.getRuntime().availableProcessors() >= 4)
            i = 4;
        else
            i = Runtime.getRuntime().availableProcessors();
        NUMBER_OF_CORES = i;
        CHILD_PACKAGE_TAGS = new ArraySet();
        CHILD_PACKAGE_TAGS.add("application");
        CHILD_PACKAGE_TAGS.add("uses-permission");
        CHILD_PACKAGE_TAGS.add("uses-permission-sdk-m");
        CHILD_PACKAGE_TAGS.add("uses-permission-sdk-23");
        CHILD_PACKAGE_TAGS.add("uses-configuration");
        CHILD_PACKAGE_TAGS.add("uses-feature");
        CHILD_PACKAGE_TAGS.add("feature-group");
        CHILD_PACKAGE_TAGS.add("uses-sdk");
        CHILD_PACKAGE_TAGS.add("supports-screens");
        CHILD_PACKAGE_TAGS.add("instrumentation");
        CHILD_PACKAGE_TAGS.add("uses-gl-texture");
        CHILD_PACKAGE_TAGS.add("compatible-screens");
        CHILD_PACKAGE_TAGS.add("supports-input");
        CHILD_PACKAGE_TAGS.add("eat-comment");
        SAFE_BROADCASTS = new ArraySet();
        SAFE_BROADCASTS.add("android.intent.action.BOOT_COMPLETED");
        SDK_VERSION = android.os.Build.VERSION.SDK_INT;
        SDK_CODENAMES = android.os.Build.VERSION.ACTIVE_CODENAMES;
    }

    // Unreferenced inner class android/content/pm/PackageParser$1

/* anonymous class */
    static final class _cls1
        implements Runnable
    {

        public void run()
        {
            if(vData.exceptionFlag != 0)
            {
                StringBuilder stringbuilder = JVM INSTR new #54  <Class StringBuilder>;
                stringbuilder.StringBuilder();
                Slog.w("PackageParser", stringbuilder.append("verifyV1 exit with Exception ").append(vData.exceptionFlag).toString());
                return;
            }
            String s = Long.toString(Thread.currentThread().getId());
            ArrayMap arraymap = strictJarFiles;
            arraymap;
            JVM INSTR monitorenter ;
            Object obj1 = (StrictJarFile)strictJarFiles.get(s);
            Object obj;
            obj = obj1;
            if(obj1 != null)
                break MISSING_BLOCK_LABEL_145;
            int i;
            if(vData.index >= PackageParser._2D_get0())
                vData.index = 0;
            obj = sJarFiles;
            obj1 = vData;
            i = ((_cls1VerificationData) (obj1)).index;
            obj1.index = i + 1;
            obj = obj[i];
            strictJarFiles.put(s, obj);
            arraymap;
            JVM INSTR monitorexit ;
            obj1 = PackageParser._2D_wrap3(((StrictJarFile) (obj)), entry);
            if(ArrayUtils.isEmpty(((Object []) (obj1))))
            {
                obj = JVM INSTR new #46  <Class PackageParser$PackageParserException>;
                obj1 = JVM INSTR new #54  <Class StringBuilder>;
                ((StringBuilder) (obj1)).StringBuilder();
                ((PackageParserException) (obj)).PackageParserException(-103, ((StringBuilder) (obj1)).append("Package ").append(apkPath).append(" has no certificates at entry ").append(entry.getName()).toString());
                throw obj;
            }
              goto _L1
            obj1;
            obj = vData.objWaitAll;
            obj;
            JVM INSTR monitorenter ;
            vData.exceptionFlag = -105;
            vData.exception = ((Exception) (obj1));
_L2:
            obj;
            JVM INSTR monitorexit ;
_L7:
            return;
            obj;
            arraymap;
            JVM INSTR monitorexit ;
            throw obj;
            obj1;
            obj = vData.objWaitAll;
            obj;
            JVM INSTR monitorenter ;
            vData.exceptionFlag = -102;
            vData.exception = ((Exception) (obj1));
              goto _L2
            obj1;
            throw obj1;
_L1:
            Signature asignature[] = PackageParser._2D_wrap0(((Certificate [][]) (obj1)));
            obj = pkg;
            obj;
            JVM INSTR monitorenter ;
            if(pkg.mCertificates != null) goto _L4; else goto _L3
_L3:
            pkg.mCertificates = ((Certificate [][]) (obj1));
            pkg.mSignatures = asignature;
            asignature = pkg;
            ArraySet arrayset = JVM INSTR new #156 <Class ArraySet>;
            arrayset.ArraySet();
            asignature.mSigningKeys = arrayset;
            i = 0;
_L6:
            if(i >= obj1.length)
                break; /* Loop/switch isn't completed */
            pkg.mSigningKeys.add(obj1[i][0].getPublicKey());
            i++;
            if(true) goto _L6; else goto _L5
_L4:
            if(!Signature.areExactMatch(pkg.mSignatures, asignature))
            {
                PackageParserException packageparserexception = JVM INSTR new #46  <Class PackageParser$PackageParserException>;
                StringBuilder stringbuilder1 = JVM INSTR new #54  <Class StringBuilder>;
                stringbuilder1.StringBuilder();
                packageparserexception.PackageParserException(-104, stringbuilder1.append("Package ").append(apkPath).append(" has mismatched certificates at entry ").append(entry.getName()).toString());
                throw packageparserexception;
            }
              goto _L5
            Exception exception;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
_L5:
            obj;
            JVM INSTR monitorexit ;
              goto _L7
            exception;
            throw exception;
              goto _L2
        }

        final String val$apkPath;
        final ZipEntry val$entry;
        final Package val$pkg;
        final StrictJarFile val$sJarFiles[];
        final ArrayMap val$strictJarFiles;
        final _cls1VerificationData val$vData;

            
            {
                vData = _pcls1verificationdata;
                strictJarFiles = arraymap;
                sJarFiles = astrictjarfile;
                entry = zipentry;
                apkPath = s;
                pkg = package1;
                super();
            }
    }

}
