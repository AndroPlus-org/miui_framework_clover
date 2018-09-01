// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.res.*;
import android.hardware.display.DisplayManagerGlobal;
import android.os.IBinder;
import android.os.Trace;
import android.util.*;
import android.view.Display;
import android.view.DisplayAdjustments;
import com.android.internal.util.ArrayUtils;
import java.lang.ref.WeakReference;
import java.util.*;
import java.util.function.Predicate;

// Referenced classes of package android.app:
//            MiuiThemeHelper, ApplicationPackageManager

public class ResourcesManager
{
    private static class ActivityResources
    {

        public final ArrayList activityResources;
        public final Configuration overrideConfig;

        private ActivityResources()
        {
            overrideConfig = new Configuration();
            activityResources = new ArrayList();
        }

        ActivityResources(ActivityResources activityresources)
        {
            this();
        }
    }


    public ResourcesManager()
    {
    }

    private static void applyNonDefaultDisplayMetricsToConfiguration(DisplayMetrics displaymetrics, Configuration configuration)
    {
        configuration.touchscreen = 1;
        configuration.densityDpi = displaymetrics.densityDpi;
        configuration.screenWidthDp = (int)((float)displaymetrics.widthPixels / displaymetrics.density);
        configuration.screenHeightDp = (int)((float)displaymetrics.heightPixels / displaymetrics.density);
        int i = Configuration.resetScreenLayout(configuration.screenLayout);
        if(displaymetrics.widthPixels > displaymetrics.heightPixels)
        {
            configuration.orientation = 2;
            configuration.screenLayout = Configuration.reduceScreenLayout(i, configuration.screenWidthDp, configuration.screenHeightDp);
        } else
        {
            configuration.orientation = 1;
            configuration.screenLayout = Configuration.reduceScreenLayout(i, configuration.screenHeightDp, configuration.screenWidthDp);
        }
        configuration.smallestScreenWidthDp = configuration.screenWidthDp;
        configuration.compatScreenWidthDp = configuration.screenWidthDp;
        configuration.compatScreenHeightDp = configuration.screenHeightDp;
        configuration.compatSmallestScreenWidthDp = configuration.smallestScreenWidthDp;
    }

    private void cleanupResourceImpl(ResourcesKey resourceskey)
    {
        resourceskey = (ResourcesImpl)((WeakReference)mResourceImpls.remove(resourceskey)).get();
        if(resourceskey != null)
            resourceskey.flushLayoutCache();
    }

    private ResourcesImpl createResourcesImpl(ResourcesKey resourceskey)
    {
        DisplayAdjustments displayadjustments = new DisplayAdjustments(resourceskey.mOverrideConfiguration);
        displayadjustments.setCompatibilityInfo(resourceskey.mCompatInfo);
        AssetManager assetmanager = createAssetManager(resourceskey);
        if(assetmanager == null)
        {
            return null;
        } else
        {
            DisplayMetrics displaymetrics = getDisplayMetrics(resourceskey.mDisplayId, displayadjustments);
            return new MiuiResourcesImpl(assetmanager, displaymetrics, generateConfig(resourceskey, displaymetrics), displayadjustments);
        }
    }

    private ResourcesKey findKeyForResourceImplLocked(ResourcesImpl resourcesimpl)
    {
        int i = mResourceImpls.size();
        Object obj;
        for(int j = 0; j < i; j++)
        {
            obj = (WeakReference)mResourceImpls.valueAt(j);
            if(obj != null)
                obj = (ResourcesImpl)((WeakReference) (obj)).get();
            else
                obj = null;
            if(obj != null && resourcesimpl == obj)
                return (ResourcesKey)mResourceImpls.keyAt(j);
        }

        return null;
    }

    private ResourcesImpl findOrCreateResourcesImplForKeyLocked(ResourcesKey resourceskey)
    {
        ResourcesImpl resourcesimpl = findResourcesImplForKeyLocked(resourceskey);
        ResourcesImpl resourcesimpl2 = resourcesimpl;
        if(resourcesimpl == null)
        {
            ResourcesImpl resourcesimpl1 = createResourcesImpl(resourceskey);
            resourcesimpl2 = resourcesimpl1;
            if(resourcesimpl1 != null)
            {
                mResourceImpls.put(resourceskey, new WeakReference(resourcesimpl1));
                resourcesimpl2 = resourcesimpl1;
            }
        }
        return resourcesimpl2;
    }

    private ResourcesImpl findResourcesImplForKeyLocked(ResourcesKey resourceskey)
    {
        resourceskey = (WeakReference)mResourceImpls.get(resourceskey);
        if(resourceskey != null)
            resourceskey = (ResourcesImpl)resourceskey.get();
        else
            resourceskey = null;
        if(resourceskey != null && resourceskey.getAssets().isUpToDate())
            return resourceskey;
        else
            return null;
    }

    private Configuration generateConfig(ResourcesKey resourceskey, DisplayMetrics displaymetrics)
    {
        boolean flag;
        boolean flag1;
        if(resourceskey.mDisplayId == 0)
            flag = true;
        else
            flag = false;
        flag1 = resourceskey.hasOverrideConfiguration();
        if(!flag || flag1)
        {
            Configuration configuration = new Configuration(getConfiguration());
            if(!flag)
                applyNonDefaultDisplayMetricsToConfiguration(displaymetrics, configuration);
            displaymetrics = configuration;
            if(flag1)
            {
                configuration.updateFrom(resourceskey.mOverrideConfiguration);
                displaymetrics = configuration;
            }
        } else
        {
            displaymetrics = getConfiguration();
        }
        return displaymetrics;
    }

    private Display getAdjustedDisplay(int i, DisplayAdjustments displayadjustments)
    {
        Object obj;
        if(displayadjustments != null)
            displayadjustments = new DisplayAdjustments(displayadjustments);
        else
            displayadjustments = new DisplayAdjustments();
        displayadjustments = Pair.create(Integer.valueOf(i), displayadjustments);
        this;
        JVM INSTR monitorenter ;
        obj = (WeakReference)mAdjustedDisplays.get(displayadjustments);
        if(obj == null)
            break MISSING_BLOCK_LABEL_67;
        obj = (Display)((WeakReference) (obj)).get();
        if(obj == null)
            break MISSING_BLOCK_LABEL_67;
        this;
        JVM INSTR monitorexit ;
        return ((Display) (obj));
        obj = DisplayManagerGlobal.getInstance();
        if(obj != null)
            break MISSING_BLOCK_LABEL_79;
        this;
        JVM INSTR monitorexit ;
        return null;
        Display display = ((DisplayManagerGlobal) (obj)).getCompatibleDisplay(i, (DisplayAdjustments)((Pair) (displayadjustments)).second);
        if(display == null)
            break MISSING_BLOCK_LABEL_123;
        ArrayMap arraymap = mAdjustedDisplays;
        WeakReference weakreference = JVM INSTR new #129 <Class WeakReference>;
        weakreference.WeakReference(display);
        arraymap.put(displayadjustments, weakreference);
        this;
        JVM INSTR monitorexit ;
        return display;
        displayadjustments;
        throw displayadjustments;
    }

    public static ResourcesManager getInstance()
    {
        android/app/ResourcesManager;
        JVM INSTR monitorenter ;
        ResourcesManager resourcesmanager1;
        if(sResourcesManager == null)
        {
            ResourcesManager resourcesmanager = JVM INSTR new #2   <Class ResourcesManager>;
            resourcesmanager.ResourcesManager();
            sResourcesManager = resourcesmanager;
        }
        resourcesmanager1 = sResourcesManager;
        android/app/ResourcesManager;
        JVM INSTR monitorexit ;
        return resourcesmanager1;
        Exception exception;
        exception;
        throw exception;
    }

    private ActivityResources getOrCreateActivityResourcesStructLocked(IBinder ibinder)
    {
        ActivityResources activityresources = (ActivityResources)mActivityResourceReferences.get(ibinder);
        ActivityResources activityresources1 = activityresources;
        if(activityresources == null)
        {
            activityresources1 = new ActivityResources(null);
            mActivityResourceReferences.put(ibinder, activityresources1);
        }
        return activityresources1;
    }

    private Resources getOrCreateResources(IBinder ibinder, ResourcesKey resourceskey, ClassLoader classloader)
    {
        this;
        JVM INSTR monitorenter ;
        if(ibinder == null)
            break MISSING_BLOCK_LABEL_111;
        ResourcesImpl resourcesimpl1;
        ActivityResources activityresources = getOrCreateActivityResourcesStructLocked(ibinder);
        ArrayUtils.unstableRemoveIf(activityresources.activityResources, sEmptyReferencePredicate);
        if(resourceskey.hasOverrideConfiguration() && activityresources.overrideConfig.equals(Configuration.EMPTY) ^ true)
        {
            Configuration configuration = JVM INSTR new #47  <Class Configuration>;
            configuration.Configuration(activityresources.overrideConfig);
            configuration.updateFrom(resourceskey.mOverrideConfiguration);
            resourceskey.mOverrideConfiguration.setTo(configuration);
        }
        resourcesimpl1 = findResourcesImplForKeyLocked(resourceskey);
        if(resourcesimpl1 == null)
            break MISSING_BLOCK_LABEL_150;
        ibinder = getOrCreateResourcesForActivityLocked(ibinder, classloader, resourcesimpl1, resourceskey.mCompatInfo);
        this;
        JVM INSTR monitorexit ;
        return ibinder;
        ArrayUtils.unstableRemoveIf(mResourceReferences, sEmptyReferencePredicate);
        resourcesimpl1 = findResourcesImplForKeyLocked(resourceskey);
        if(resourcesimpl1 == null)
            break MISSING_BLOCK_LABEL_150;
        ibinder = getOrCreateResourcesLocked(classloader, resourcesimpl1, resourceskey.mCompatInfo);
        this;
        JVM INSTR monitorexit ;
        return ibinder;
        this;
        JVM INSTR monitorexit ;
        resourcesimpl1 = createResourcesImpl(resourceskey);
        if(resourcesimpl1 == null)
            return null;
        break MISSING_BLOCK_LABEL_171;
        ibinder;
        throw ibinder;
        this;
        JVM INSTR monitorenter ;
        ResourcesImpl resourcesimpl = findResourcesImplForKeyLocked(resourceskey);
        if(resourcesimpl == null) goto _L2; else goto _L1
_L1:
        resourcesimpl1.getAssets().close();
        resourcesimpl1 = resourcesimpl;
_L3:
        if(ibinder == null)
            break MISSING_BLOCK_LABEL_253;
        ibinder = getOrCreateResourcesForActivityLocked(ibinder, classloader, resourcesimpl1, resourceskey.mCompatInfo);
_L4:
        this;
        JVM INSTR monitorexit ;
        return ibinder;
_L2:
        ArrayMap arraymap = mResourceImpls;
        WeakReference weakreference = JVM INSTR new #129 <Class WeakReference>;
        weakreference.WeakReference(resourcesimpl1);
        arraymap.put(resourceskey, weakreference);
          goto _L3
        ibinder;
        throw ibinder;
        ibinder = getOrCreateResourcesLocked(classloader, resourcesimpl1, resourceskey.mCompatInfo);
          goto _L4
    }

    private Resources getOrCreateResourcesForActivityLocked(IBinder ibinder, ClassLoader classloader, ResourcesImpl resourcesimpl, CompatibilityInfo compatibilityinfo)
    {
        ibinder = getOrCreateActivityResourcesStructLocked(ibinder);
        int i = ((ActivityResources) (ibinder)).activityResources.size();
        for(int j = 0; j < i; j++)
        {
            compatibilityinfo = (Resources)((WeakReference)((ActivityResources) (ibinder)).activityResources.get(j)).get();
            if(compatibilityinfo != null && Objects.equals(compatibilityinfo.getClassLoader(), classloader) && compatibilityinfo.getImpl() == resourcesimpl)
                return compatibilityinfo;
        }

        classloader = new MiuiResources(classloader);
        classloader.setImpl(resourcesimpl);
        ((ActivityResources) (ibinder)).activityResources.add(new WeakReference(classloader));
        return classloader;
    }

    private Resources getOrCreateResourcesLocked(ClassLoader classloader, ResourcesImpl resourcesimpl, CompatibilityInfo compatibilityinfo)
    {
        int i = mResourceReferences.size();
        for(int j = 0; j < i; j++)
        {
            compatibilityinfo = (Resources)((WeakReference)mResourceReferences.get(j)).get();
            if(compatibilityinfo != null && Objects.equals(compatibilityinfo.getClassLoader(), classloader) && compatibilityinfo.getImpl() == resourcesimpl)
                return compatibilityinfo;
        }

        classloader = new MiuiResources(classloader);
        classloader.setImpl(resourcesimpl);
        mResourceReferences.add(new WeakReference(classloader));
        return classloader;
    }

    private void redirectResourcesToNewImplLocked(ArrayMap arraymap)
    {
        if(arraymap.isEmpty())
            return;
        int i = mResourceReferences.size();
        for(int k = 0; k < i; k++)
        {
            Object obj = (WeakReference)mResourceReferences.get(k);
            Object obj2;
            if(obj != null)
                obj = (Resources)((WeakReference) (obj)).get();
            else
                obj = null;
            if(obj == null)
                continue;
            obj2 = (ResourcesKey)arraymap.get(((Resources) (obj)).getImpl());
            if(obj2 == null)
                continue;
            obj2 = findOrCreateResourcesImplForKeyLocked(((ResourcesKey) (obj2)));
            if(obj2 == null)
                throw new android.content.res.Resources.NotFoundException("failed to redirect ResourcesImpl");
            ((Resources) (obj)).setImpl(((ResourcesImpl) (obj2)));
        }

        for(Iterator iterator = mActivityResourceReferences.values().iterator(); iterator.hasNext();)
        {
            ActivityResources activityresources = (ActivityResources)iterator.next();
            int j = activityresources.activityResources.size();
            int l = 0;
            while(l < j) 
            {
                Object obj1 = (WeakReference)activityresources.activityResources.get(l);
                if(obj1 != null)
                    obj1 = (Resources)((WeakReference) (obj1)).get();
                else
                    obj1 = null;
                if(obj1 != null)
                {
                    Object obj3 = (ResourcesKey)arraymap.get(((Resources) (obj1)).getImpl());
                    if(obj3 != null)
                    {
                        obj3 = findOrCreateResourcesImplForKeyLocked(((ResourcesKey) (obj3)));
                        if(obj3 == null)
                            throw new android.content.res.Resources.NotFoundException("failed to redirect ResourcesImpl");
                        ((Resources) (obj1)).setImpl(((ResourcesImpl) (obj3)));
                    }
                }
                l++;
            }
        }

    }

    public void appendLibAssetForMainAssetPath(String s, String s1)
    {
        this;
        JVM INSTR monitorenter ;
        ArrayMap arraymap;
        int i;
        arraymap = JVM INSTR new #52  <Class ArrayMap>;
        arraymap.ArrayMap();
        i = mResourceImpls.size();
        int j = 0;
_L9:
        if(j >= i)
            break MISSING_BLOCK_LABEL_230;
        ResourcesKey resourceskey;
        Object obj;
        resourceskey = (ResourcesKey)mResourceImpls.keyAt(j);
        obj = (WeakReference)mResourceImpls.valueAt(j);
        if(obj == null) goto _L2; else goto _L1
_L1:
        obj = (ResourcesImpl)((WeakReference) (obj)).get();
_L6:
        if(obj == null) goto _L4; else goto _L3
_L3:
        if(!Objects.equals(resourceskey.mResDir, s) || ArrayUtils.contains(resourceskey.mLibDirs, s1)) goto _L4; else goto _L5
_L5:
        int k;
        if(resourceskey.mLibDirs == null)
            break MISSING_BLOCK_LABEL_224;
        k = resourceskey.mLibDirs.length;
_L7:
        k++;
        String as[];
        as = new String[k];
        if(resourceskey.mLibDirs != null)
            System.arraycopy(resourceskey.mLibDirs, 0, as, 0, resourceskey.mLibDirs.length);
        as[k - 1] = s1;
        ResourcesKey resourceskey1 = JVM INSTR new #144 <Class ResourcesKey>;
        resourceskey1.ResourcesKey(resourceskey.mResDir, resourceskey.mSplitResDirs, resourceskey.mOverlayDirs, as, resourceskey.mDisplayId, resourceskey.mOverrideConfiguration, resourceskey.mCompatInfo);
        arraymap.put(obj, resourceskey1);
_L4:
        j++;
        continue; /* Loop/switch isn't completed */
_L2:
        obj = null;
          goto _L6
        k = 0;
          goto _L7
        redirectResourcesToNewImplLocked(arraymap);
        this;
        JVM INSTR monitorexit ;
        return;
        s;
        throw s;
        if(true) goto _L9; else goto _L8
_L8:
    }

    public boolean applyCompatConfigurationLocked(int i, Configuration configuration)
    {
        if(mResCompatibilityInfo != null && mResCompatibilityInfo.supportsScreen() ^ true)
        {
            mResCompatibilityInfo.applyToConfiguration(i, configuration);
            return true;
        } else
        {
            return false;
        }
    }

    public final boolean applyConfigurationToResourcesLocked(Configuration configuration, CompatibilityInfo compatibilityinfo)
    {
        boolean flag;
        Trace.traceBegin(8192L, "ResourcesManager#applyConfigurationToResourcesLocked");
        flag = mResConfiguration.isOtherSeqNewer(configuration);
        if(!flag && compatibilityinfo == null)
        {
            Trace.traceEnd(8192L);
            return false;
        }
        int i;
        DisplayMetrics displaymetrics;
        i = mResConfiguration.updateFrom(configuration);
        mAdjustedDisplays.clear();
        displaymetrics = getDisplayMetrics();
        int j;
        j = i;
        if(compatibilityinfo == null)
            break MISSING_BLOCK_LABEL_102;
        if(mResCompatibilityInfo == null)
            break MISSING_BLOCK_LABEL_89;
        j = i;
        if(!(mResCompatibilityInfo.equals(compatibilityinfo) ^ true))
            break MISSING_BLOCK_LABEL_102;
        mResCompatibilityInfo = compatibilityinfo;
        j = i | 0xd00;
        MiuiThemeHelper.handleExtraConfigurationChanges(j, configuration);
        Resources.updateSystemConfiguration(configuration, displaymetrics, compatibilityinfo);
        ApplicationPackageManager.configurationChanged();
        Object obj = null;
        i = mResourceImpls.size() - 1;
_L10:
        if(i < 0)
            break MISSING_BLOCK_LABEL_384;
        ResourcesKey resourceskey;
        Object obj1;
        resourceskey = (ResourcesKey)mResourceImpls.keyAt(i);
        obj1 = (WeakReference)mResourceImpls.valueAt(i);
        if(obj1 == null) goto _L2; else goto _L1
_L1:
        ResourcesImpl resourcesimpl = (ResourcesImpl)((WeakReference) (obj1)).get();
_L7:
        if(resourcesimpl == null) goto _L4; else goto _L3
_L3:
        int k = resourceskey.mDisplayId;
        boolean flag2;
        DisplayAdjustments displayadjustments;
        if(k == 0)
            flag2 = true;
        else
            flag2 = false;
        flag = resourceskey.hasOverrideConfiguration();
        if(flag2 && !flag) goto _L6; else goto _L5
_L5:
        obj1 = obj;
        if(obj != null)
            break MISSING_BLOCK_LABEL_234;
        obj1 = JVM INSTR new #47  <Class Configuration>;
        ((Configuration) (obj1)).Configuration();
        ((Configuration) (obj1)).setTo(configuration);
        displayadjustments = resourcesimpl.getDisplayAdjustments();
        obj = displayadjustments;
        if(compatibilityinfo == null)
            break MISSING_BLOCK_LABEL_273;
        obj = JVM INSTR new #142 <Class DisplayAdjustments>;
        ((DisplayAdjustments) (obj)).DisplayAdjustments(displayadjustments);
        ((DisplayAdjustments) (obj)).setCompatibilityInfo(compatibilityinfo);
        obj = getDisplayMetrics(k, ((DisplayAdjustments) (obj)));
        if(flag2)
            break MISSING_BLOCK_LABEL_295;
        applyNonDefaultDisplayMetricsToConfiguration(((DisplayMetrics) (obj)), ((Configuration) (obj1)));
        if(!flag)
            break MISSING_BLOCK_LABEL_310;
        ((Configuration) (obj1)).updateFrom(resourceskey.mOverrideConfiguration);
        resourcesimpl.updateConfiguration(((Configuration) (obj1)), ((DisplayMetrics) (obj)), compatibilityinfo);
_L8:
        i--;
        obj = obj1;
        continue; /* Loop/switch isn't completed */
_L2:
        resourcesimpl = null;
          goto _L7
_L6:
        resourcesimpl.updateConfiguration(configuration, displaymetrics, compatibilityinfo);
        obj1 = obj;
          goto _L8
        configuration;
        Trace.traceEnd(8192L);
        throw configuration;
_L4:
        mResourceImpls.removeAt(i);
        obj1 = obj;
          goto _L8
        boolean flag1;
        if(j != 0)
            flag1 = true;
        else
            flag1 = false;
        Trace.traceEnd(8192L);
        return flag1;
        if(true) goto _L10; else goto _L9
_L9:
    }

    final void applyNewResourceDirsLocked(String s, String as[])
    {
        ArrayMap arraymap;
        int i;
        Trace.traceBegin(8192L, "ResourcesManager#applyNewResourceDirsLocked");
        arraymap = JVM INSTR new #52  <Class ArrayMap>;
        arraymap.ArrayMap();
        i = mResourceImpls.size();
        int j = 0;
_L7:
        if(j >= i) goto _L2; else goto _L1
_L1:
        ResourcesKey resourceskey;
        Object obj;
        resourceskey = (ResourcesKey)mResourceImpls.keyAt(j);
        obj = (WeakReference)mResourceImpls.valueAt(j);
        if(obj == null) goto _L4; else goto _L3
_L3:
        obj = (ResourcesImpl)((WeakReference) (obj)).get();
_L5:
        if(obj == null)
            break MISSING_BLOCK_LABEL_154;
        if(resourceskey.mResDir == null || resourceskey.mResDir.equals(s))
        {
            ResourcesKey resourceskey1 = JVM INSTR new #144 <Class ResourcesKey>;
            resourceskey1.ResourcesKey(resourceskey.mResDir, resourceskey.mSplitResDirs, as, resourceskey.mLibDirs, resourceskey.mDisplayId, resourceskey.mOverrideConfiguration, resourceskey.mCompatInfo);
            arraymap.put(obj, resourceskey1);
        }
        j++;
        continue; /* Loop/switch isn't completed */
_L4:
        obj = null;
        if(true) goto _L5; else goto _L2
_L2:
        redirectResourcesToNewImplLocked(arraymap);
        Trace.traceEnd(8192L);
        return;
        s;
        Trace.traceEnd(8192L);
        throw s;
        if(true) goto _L7; else goto _L6
_L6:
    }

    protected AssetManager createAssetManager(ResourcesKey resourceskey)
    {
        boolean flag = false;
        AssetManager assetmanager = new AssetManager();
        if(resourceskey.mResDir != null && assetmanager.addAssetPath(resourceskey.mResDir) == 0)
        {
            Log.e("ResourcesManager", (new StringBuilder()).append("failed to add asset path ").append(resourceskey.mResDir).toString());
            return null;
        }
        if(resourceskey.mSplitResDirs != null)
        {
            String as[] = resourceskey.mSplitResDirs;
            int i = as.length;
            for(int l = 0; l < i; l++)
            {
                String s = as[l];
                if(assetmanager.addAssetPath(s) == 0)
                {
                    Log.e("ResourcesManager", (new StringBuilder()).append("failed to add split asset path ").append(s).toString());
                    return null;
                }
            }

        }
        if(resourceskey.mOverlayDirs != null)
        {
            String as1[] = resourceskey.mOverlayDirs;
            int j = as1.length;
            for(int i1 = 0; i1 < j; i1++)
                assetmanager.addOverlayPath(as1[i1]);

        }
        if(resourceskey.mLibDirs != null)
        {
            resourceskey = resourceskey.mLibDirs;
            int k = resourceskey.length;
            for(int j1 = ((flag) ? 1 : 0); j1 < k; j1++)
            {
                String s1 = resourceskey[j1];
                if(s1.endsWith(".apk") && assetmanager.addAssetPathAsSharedLibrary(s1) == 0)
                    Log.w("ResourcesManager", (new StringBuilder()).append("Asset path '").append(s1).append("' does not exist or contains no resources.").toString());
            }

        }
        return assetmanager;
    }

    public Resources createBaseActivityResources(IBinder ibinder, String s, String as[], String as1[], String as2[], int i, Configuration configuration, 
            CompatibilityInfo compatibilityinfo, ClassLoader classloader)
    {
        ResourcesKey resourceskey;
        Trace.traceBegin(8192L, "ResourcesManager#createBaseActivityResources");
        resourceskey = JVM INSTR new #144 <Class ResourcesKey>;
        if(configuration == null) goto _L2; else goto _L1
_L1:
        Configuration configuration1;
        configuration1 = JVM INSTR new #47  <Class Configuration>;
        configuration1.Configuration(configuration);
_L5:
        resourceskey.ResourcesKey(s, as, as1, as2, i, configuration1, compatibilityinfo);
        if(classloader == null) goto _L4; else goto _L3
_L3:
        this;
        JVM INSTR monitorenter ;
        getOrCreateActivityResourcesStructLocked(ibinder);
        this;
        JVM INSTR monitorexit ;
        updateResourcesForActivity(ibinder, configuration, i, false);
        ibinder = getOrCreateResources(ibinder, resourceskey, classloader);
        Trace.traceEnd(8192L);
        return ibinder;
_L2:
        configuration1 = null;
          goto _L5
_L4:
        classloader = ClassLoader.getSystemClassLoader();
          goto _L3
        ibinder;
        this;
        JVM INSTR monitorexit ;
        throw ibinder;
        ibinder;
        Trace.traceEnd(8192L);
        throw ibinder;
          goto _L5
    }

    public Display getAdjustedDisplay(int i, Resources resources)
    {
        this;
        JVM INSTR monitorenter ;
        DisplayManagerGlobal displaymanagerglobal = DisplayManagerGlobal.getInstance();
        if(displaymanagerglobal != null)
            break MISSING_BLOCK_LABEL_14;
        this;
        JVM INSTR monitorexit ;
        return null;
        resources = displaymanagerglobal.getCompatibleDisplay(i, resources);
        this;
        JVM INSTR monitorexit ;
        return resources;
        resources;
        throw resources;
    }

    public Configuration getConfiguration()
    {
        this;
        JVM INSTR monitorenter ;
        Configuration configuration = mResConfiguration;
        this;
        JVM INSTR monitorexit ;
        return configuration;
        Exception exception;
        exception;
        throw exception;
    }

    DisplayMetrics getDisplayMetrics()
    {
        return getDisplayMetrics(0, DisplayAdjustments.DEFAULT_DISPLAY_ADJUSTMENTS);
    }

    protected DisplayMetrics getDisplayMetrics(int i, DisplayAdjustments displayadjustments)
    {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        displayadjustments = getAdjustedDisplay(i, displayadjustments);
        if(displayadjustments != null)
            displayadjustments.getMetrics(displaymetrics);
        else
            displaymetrics.setToDefaults();
        return displaymetrics;
    }

    public Resources getResources(IBinder ibinder, String s, String as[], String as1[], String as2[], int i, Configuration configuration, 
            CompatibilityInfo compatibilityinfo, ClassLoader classloader)
    {
        ResourcesKey resourceskey;
        Trace.traceBegin(8192L, "ResourcesManager#getResources");
        resourceskey = JVM INSTR new #144 <Class ResourcesKey>;
        if(configuration == null) goto _L2; else goto _L1
_L1:
        Configuration configuration1;
        configuration1 = JVM INSTR new #47  <Class Configuration>;
        configuration1.Configuration(configuration);
        configuration = configuration1;
_L6:
        resourceskey.ResourcesKey(s, as, as1, as2, i, configuration, compatibilityinfo);
        if(classloader == null) goto _L4; else goto _L3
_L3:
        ibinder = getOrCreateResources(ibinder, resourceskey, classloader);
        Trace.traceEnd(8192L);
        return ibinder;
_L2:
        configuration = null;
        continue; /* Loop/switch isn't completed */
_L4:
        classloader = ClassLoader.getSystemClassLoader();
          goto _L3
        ibinder;
        Trace.traceEnd(8192L);
        throw ibinder;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public void invalidatePath(String s)
    {
        this;
        JVM INSTR monitorenter ;
        int i;
        int j;
        i = 0;
        j = 0;
_L2:
        if(j >= mResourceImpls.size())
            break; /* Loop/switch isn't completed */
        ResourcesKey resourceskey = (ResourcesKey)mResourceImpls.keyAt(j);
        if(!resourceskey.isPathReferenced(s))
            break MISSING_BLOCK_LABEL_51;
        cleanupResourceImpl(resourceskey);
        i++;
        continue; /* Loop/switch isn't completed */
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        StringBuilder stringbuilder = JVM INSTR new #483 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.i("ResourcesManager", stringbuilder.append("Invalidated ").append(i).append(" asset managers that referenced ").append(s).toString());
        this;
        JVM INSTR monitorexit ;
        return;
        s;
        throw s;
    }

    boolean isSameResourcesOverrideConfig(IBinder ibinder, Configuration configuration)
    {
        boolean flag = true;
        boolean flag1 = true;
        this;
        JVM INSTR monitorenter ;
        if(ibinder == null) goto _L2; else goto _L1
_L1:
        ibinder = (ActivityResources)mActivityResourceReferences.get(ibinder);
_L4:
        if(ibinder != null)
            break; /* Loop/switch isn't completed */
        if(configuration != null)
            flag1 = false;
        this;
        JVM INSTR monitorexit ;
        return flag1;
_L2:
        ibinder = null;
        if(true) goto _L4; else goto _L3
_L3:
        flag1 = flag;
        if(Objects.equals(((ActivityResources) (ibinder)).overrideConfig, configuration)) goto _L6; else goto _L5
_L5:
        if(configuration == null) goto _L8; else goto _L7
_L7:
        if(((ActivityResources) (ibinder)).overrideConfig == null) goto _L8; else goto _L9
_L9:
        int i = configuration.diffPublicOnly(((ActivityResources) (ibinder)).overrideConfig);
        if(i == 0)
            flag1 = flag;
        else
            flag1 = false;
_L6:
        this;
        JVM INSTR monitorexit ;
        return flag1;
_L8:
        flag1 = false;
        if(true) goto _L6; else goto _L10
_L10:
        ibinder;
        throw ibinder;
    }

    public void updateResourcesForActivity(IBinder ibinder, Configuration configuration, int i, boolean flag)
    {
        Trace.traceBegin(8192L, "ResourcesManager#updateResourcesForActivity");
        this;
        JVM INSTR monitorenter ;
        ActivityResources activityresources;
        boolean flag1;
        activityresources = getOrCreateActivityResourcesStructLocked(ibinder);
        flag1 = Objects.equals(activityresources.overrideConfig, configuration);
        if(!flag1 || !(flag ^ true))
            break MISSING_BLOCK_LABEL_50;
        this;
        JVM INSTR monitorexit ;
        Trace.traceEnd(8192L);
        return;
        Configuration configuration1;
        configuration1 = JVM INSTR new #47  <Class Configuration>;
        configuration1.Configuration(activityresources.overrideConfig);
        if(configuration == null) goto _L2; else goto _L1
_L1:
        activityresources.overrideConfig.setTo(configuration);
_L8:
        int j;
        flag = activityresources.overrideConfig.equals(Configuration.EMPTY);
        j = activityresources.activityResources.size();
        int k = 0;
_L7:
        if(k >= j) goto _L4; else goto _L3
_L3:
        Resources resources = (Resources)((WeakReference)activityresources.activityResources.get(k)).get();
        if(resources != null) goto _L6; else goto _L5
_L5:
        k++;
          goto _L7
_L2:
        activityresources.overrideConfig.unset();
          goto _L8
        ibinder;
        this;
        JVM INSTR monitorexit ;
        throw ibinder;
        ibinder;
        Trace.traceEnd(8192L);
        throw ibinder;
_L6:
        Object obj = findKeyForResourceImplLocked(resources.getImpl());
        if(obj != null)
            break MISSING_BLOCK_LABEL_219;
        ibinder = JVM INSTR new #483 <Class StringBuilder>;
        ibinder.StringBuilder();
        Slog.e("ResourcesManager", ibinder.append("can't find ResourcesKey for resources impl=").append(resources.getImpl()).toString());
          goto _L5
        ibinder = JVM INSTR new #47  <Class Configuration>;
        ibinder.Configuration();
        if(configuration == null)
            break MISSING_BLOCK_LABEL_236;
        ibinder.setTo(configuration);
        if(!(flag ^ true))
            break MISSING_BLOCK_LABEL_266;
        if(((ResourcesKey) (obj)).hasOverrideConfiguration())
            ibinder.updateFrom(Configuration.generateDelta(configuration1, ((ResourcesKey) (obj)).mOverrideConfiguration));
        ResourcesKey resourceskey;
        resourceskey = JVM INSTR new #144 <Class ResourcesKey>;
        resourceskey.ResourcesKey(((ResourcesKey) (obj)).mResDir, ((ResourcesKey) (obj)).mSplitResDirs, ((ResourcesKey) (obj)).mOverlayDirs, ((ResourcesKey) (obj)).mLibDirs, i, ibinder, ((ResourcesKey) (obj)).mCompatInfo);
        obj = findResourcesImplForKeyLocked(resourceskey);
        ibinder = ((IBinder) (obj));
        if(obj != null) goto _L10; else goto _L9
_L9:
        obj = createResourcesImpl(resourceskey);
        ibinder = ((IBinder) (obj));
        if(obj == null) goto _L10; else goto _L11
_L11:
        ArrayMap arraymap = mResourceImpls;
        ibinder = JVM INSTR new #129 <Class WeakReference>;
        ibinder.WeakReference(obj);
        arraymap.put(resourceskey, ibinder);
        ibinder = ((IBinder) (obj));
_L10:
        if(ibinder == null) goto _L5; else goto _L12
_L12:
        if(ibinder == resources.getImpl()) goto _L5; else goto _L13
_L13:
        resources.setImpl(ibinder);
          goto _L5
_L4:
        this;
        JVM INSTR monitorexit ;
        Trace.traceEnd(8192L);
        return;
          goto _L8
    }

    private static final boolean DEBUG = false;
    static final String TAG = "ResourcesManager";
    private static final Predicate sEmptyReferencePredicate = new Predicate() {

        public volatile boolean test(Object obj)
        {
            return test((WeakReference)obj);
        }

        public boolean test(WeakReference weakreference)
        {
            boolean flag = true;
            boolean flag1 = flag;
            if(weakreference != null)
                if(weakreference.get() == null)
                    flag1 = flag;
                else
                    flag1 = false;
            return flag1;
        }

    }
;
    private static ResourcesManager sResourcesManager;
    private final WeakHashMap mActivityResourceReferences = new WeakHashMap();
    private final ArrayMap mAdjustedDisplays = new ArrayMap();
    private CompatibilityInfo mResCompatibilityInfo;
    private final Configuration mResConfiguration = new Configuration();
    private final ArrayMap mResourceImpls = new ArrayMap();
    private final ArrayList mResourceReferences = new ArrayList();

}
