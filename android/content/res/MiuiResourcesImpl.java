// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.res;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.*;
import android.view.DisplayAdjustments;
import java.io.InputStream;
import java.util.*;
import miui.content.res.*;

// Referenced classes of package android.content.res:
//            ResourcesImpl, Resources, TypedArray, Configuration, 
//            MiuiConfiguration, AssetManager, MiuiResources, CompatibilityInfo

public final class MiuiResourcesImpl extends ResourcesImpl
{
    private class PreloadDrawableSource
    {

        int mCookie;
        String mResourcePath;
        String mThemePath;
        final MiuiResourcesImpl this$0;

        PreloadDrawableSource(int i, String s, String s1)
        {
            this$0 = MiuiResourcesImpl.this;
            super();
            mCookie = i;
            mResourcePath = s;
            mThemePath = s1;
        }
    }


    public MiuiResourcesImpl(AssetManager assetmanager, DisplayMetrics displaymetrics, Configuration configuration, DisplayAdjustments displayadjustments)
    {
        super(assetmanager, displaymetrics, configuration, displayadjustments);
        mIsPreloadOverlayed = new SparseArray();
        mPreloadingIds = new Stack();
        mThemeResources = ThemeResourcesEmpty.sInstance;
        mUpdatedTime = -1L;
        mValueLoadedTime = -1L;
        mThemeValues = new ThemeValues();
        mSkipFiles = new SparseArray();
    }

    private static void delayGCAtlasPreloadedBitmaps()
    {
        LongSparseArray longsparsearray = Resources.getSystem().getPreloadedDrawables().clone();
        if(longsparsearray.size() > 0)
        {
            ThemeNativeUtils.terminateAtlas();
            (new Thread(longsparsearray) {

                public void run()
                {
                    try
                    {
                        Thread.sleep(30000L);
                    }
                    catch(Exception exception) { }
                    preload.clear();
                }

                final LongSparseArray val$preload;

            
            {
                preload = longsparsearray;
                super();
            }
            }
).start();
        }
    }

    private void loadValues()
    {
        if(mValueLoadedTime >= mUpdatedTime)
        {
            return;
        } else
        {
            ThemeValues themevalues = new ThemeValues();
            mThemeResources.mergeThemeValues(mPackageName, themevalues);
            mThemeValues = themevalues;
            mValueLoadedTime = System.currentTimeMillis();
            return;
        }
    }

    private void resolveOverlayValue(int i, TypedValue typedvalue)
    {
        if(!sMiuiThemeEnabled || typedvalue.resourceId <= 0)
            return;
        break MISSING_BLOCK_LABEL_14;
        if(typedvalue.type >= 16 && typedvalue.type <= 31 || typedvalue.type == 5)
        {
            loadValues();
            Integer integer = getThemeInt(i);
            Integer integer1 = integer;
            if(integer == null)
                integer1 = getThemeInt(typedvalue.resourceId);
            if(integer1 != null)
                typedvalue.data = integer1.intValue();
        }
        return;
    }

    public int[] getIntArray(int i)
    {
        if(sMiuiThemeEnabled)
        {
            int ai[] = getThemeIntArray(i);
            if(ai != null)
                return ai;
        }
        return null;
    }

    public String[] getStringArray(int i)
    {
        if(sMiuiThemeEnabled)
        {
            String as[] = getThemeStringArray(i);
            if(as != null)
                return as;
        }
        return null;
    }

    public CharSequence getText(int i)
    {
        if(sMiuiThemeEnabled)
        {
            CharSequence charsequence = getThemeString(i);
            if(charsequence != null)
                return charsequence;
        }
        return null;
    }

    public CharSequence getText(int i, CharSequence charsequence)
    {
        if(sMiuiThemeEnabled)
        {
            charsequence = getThemeString(i);
            if(charsequence != null)
                return charsequence;
        }
        return null;
    }

    public CharSequence[] getTextArray(int i)
    {
        if(sMiuiThemeEnabled)
        {
            String as[] = getThemeStringArray(i);
            if(as != null)
                return as;
        }
        return null;
    }

    Integer getThemeInt(int i)
    {
        loadValues();
        return (Integer)mThemeValues.mIntegers.get(Integer.valueOf(i));
    }

    int[] getThemeIntArray(int i)
    {
        loadValues();
        return (int[])mThemeValues.mIntegerArrays.get(Integer.valueOf(i));
    }

    CharSequence getThemeString(int i)
    {
        loadValues();
        return (CharSequence)mThemeValues.mStrings.get(Integer.valueOf(i));
    }

    String[] getThemeStringArray(int i)
    {
        loadValues();
        return (String[])mThemeValues.mStringArrays.get(Integer.valueOf(i));
    }

    public void getValue(int i, TypedValue typedvalue, boolean flag)
    {
        super.getValue(i, typedvalue, flag);
        resolveOverlayValue(i, typedvalue);
    }

    public void init(MiuiResources miuiresources, String s)
    {
        if(mPackageName != null)
            return;
        mPackageName = s;
        if(s == null)
        {
            mPackageName = "android";
            mThemeResources = ThemeResources.getSystem(miuiresources);
        } else
        {
            mThemeResources = ThemeResourcesPackage.getThemeResources(miuiresources, s);
        }
        reset();
    }

    Boolean isPreloadOverlayed(int i)
    {
        Object obj;
label0:
        {
            if(!sMiuiThemeEnabled)
                return null;
            obj = (Boolean)mIsPreloadOverlayed.get(i);
            if(obj != null)
                return ((Boolean) (obj));
            Boolean boolean1 = Boolean.valueOf(false);
            Object obj1 = (Set)sPreloadDrawableSources.get(i);
            obj = boolean1;
            if(obj1 == null)
                break label0;
            obj1 = ((Iterable) (obj1)).iterator();
            PreloadDrawableSource preloaddrawablesource;
            do
            {
                obj = boolean1;
                if(!((Iterator) (obj1)).hasNext())
                    break label0;
                preloaddrawablesource = (PreloadDrawableSource)((Iterator) (obj1)).next();
                obj = new MiuiResources.ThemeFileInfoOption(preloaddrawablesource.mCookie, preloaddrawablesource.mResourcePath, false);
                mThemeResources.getThemeFile(((MiuiResources.ThemeFileInfoOption) (obj)));
            } while(TextUtils.equals(preloaddrawablesource.mThemePath, ((MiuiResources.ThemeFileInfoOption) (obj)).outFilterPath));
            obj = Boolean.valueOf(true);
        }
        mIsPreloadOverlayed.put(i, obj);
        return ((Boolean) (obj));
    }

    Drawable loadDrawable(Resources resources, TypedValue typedvalue, int i, int j, Resources.Theme theme)
        throws Resources.NotFoundException
    {
        if(!sMiuiThemeEnabled)
            return super.loadDrawable(resources, typedvalue, i, j, theme);
        if(isPreloading())
            mPreloadingIds.push(Integer.valueOf(i));
        theme = super.loadDrawable(resources, typedvalue, i, j, theme);
        if(isPreloading())
        {
            if(typedvalue.type < 28 || typedvalue.type > 31)
            {
                resources = typedvalue.string.toString();
                if(!resources.endsWith(".xml"))
                {
                    PreloadDrawableSource preloaddrawablesource = new PreloadDrawableSource(typedvalue.assetCookie, resources, null);
                    if(mPreloadingInfo != null)
                    {
                        preloaddrawablesource.mThemePath = mPreloadingInfo.outFilterPath;
                        mPreloadingInfo = null;
                    }
                    for(Iterator iterator = mPreloadingIds.iterator(); iterator.hasNext(); resources.add(preloaddrawablesource))
                    {
                        Integer integer = (Integer)iterator.next();
                        typedvalue = (Set)sPreloadDrawableSources.get(integer.intValue());
                        resources = typedvalue;
                        if(typedvalue == null)
                        {
                            resources = new HashSet();
                            sPreloadDrawableSources.put(integer.intValue(), resources);
                        }
                    }

                }
            }
            mPreloadingIds.pop();
        }
        return theme;
    }

    Drawable loadOverlayDrawable(MiuiResources miuiresources, TypedValue typedvalue, int i)
    {
        MiuiResources.ThemeFileInfoOption themefileinfooption;
        if(!sMiuiThemeEnabled || mSkipFiles.get(i) != null)
            return null;
        themefileinfooption = new MiuiResources.ThemeFileInfoOption(typedvalue, true);
        if(isPreloading())
            mPreloadingInfo = themefileinfooption;
        InputStream inputstream;
        boolean flag;
        InputStream inputstream1;
        if(themefileinfooption.inResourcePath.endsWith(".xml"))
        {
            FixedSizeStringBuffer fixedsizestringbuffer = FixedSizeStringBuffer.getBuffer();
            int j = themefileinfooption.inResourcePath.length() - ".xml".length();
            fixedsizestringbuffer.assign(themefileinfooption.inResourcePath, j);
            fixedsizestringbuffer.append(".9.png");
            themefileinfooption.inResourcePath = fixedsizestringbuffer.toString();
            flag = mThemeResources.getThemeFile(themefileinfooption);
            if(flag)
            {
                fixedsizestringbuffer.setLength(j);
                fixedsizestringbuffer.append(".png");
                themefileinfooption.inResourcePath = fixedsizestringbuffer.toString();
            }
            FixedSizeStringBuffer.freeBuffer(fixedsizestringbuffer);
        } else
        {
            flag = mThemeResources.getThemeFile(themefileinfooption);
        }
        if(!flag)
            break MISSING_BLOCK_LABEL_260;
        typedvalue.density = themefileinfooption.outDensity;
        inputstream1 = themefileinfooption.outInputStream;
        inputstream = inputstream1;
        if(themefileinfooption.inResourcePath.endsWith(".9.png"))
            inputstream = SimulateNinePngUtil.convertIntoNinePngStream(inputstream1);
        miuiresources = Drawable.createFromResourceStream(miuiresources, typedvalue, inputstream, themefileinfooption.inResourcePath);
        try
        {
            themefileinfooption.outInputStream.close();
        }
        // Misplaced declaration of an exception variable
        catch(TypedValue typedvalue) { }
        return miuiresources;
        miuiresources;
        try
        {
            themefileinfooption.outInputStream.close();
        }
        // Misplaced declaration of an exception variable
        catch(MiuiResources miuiresources) { }
        return null;
        miuiresources;
        try
        {
            themefileinfooption.outInputStream.close();
        }
        // Misplaced declaration of an exception variable
        catch(TypedValue typedvalue) { }
        throw miuiresources;
        mSkipFiles.put(i, Boolean.valueOf(true));
        if(false)
            ;
        else
            break MISSING_BLOCK_LABEL_247;
    }

    TypedArray loadOverlayTypedArray(TypedArray typedarray)
    {
        int ai[];
        int i;
        if(!sMiuiThemeEnabled)
            return null;
        loadValues();
        if(mThemeValues == null || mThemeValues.mIntegers.size() == 0)
            return typedarray;
        ai = typedarray.mData;
        i = 0;
_L2:
        int j;
        int k;
        if(i >= ai.length)
            break MISSING_BLOCK_LABEL_113;
        j = ai[i + 0];
        k = ai[i + 3];
        break MISSING_BLOCK_LABEL_61;
        if(j >= 16 && j <= 31 || j == 5)
        {
            Integer integer = getThemeInt(k);
            if(integer != null)
                ai[i + 1] = integer.intValue();
        }
        i += 6;
        continue; /* Loop/switch isn't completed */
        return typedarray;
        if(true) goto _L2; else goto _L1
_L1:
    }

    void loadOverlayValue(TypedValue typedvalue, int i)
    {
        resolveOverlayValue(i, typedvalue);
    }

    public InputStream openRawResource(int i, TypedValue typedvalue)
    {
        if(sMiuiThemeEnabled && mSkipFiles.get(i) == null)
        {
            super.getValue(i, typedvalue, true);
            MiuiResources.ThemeFileInfoOption themefileinfooption = new MiuiResources.ThemeFileInfoOption(typedvalue, true);
            if(mThemeResources.getThemeFile(themefileinfooption))
            {
                typedvalue.density = themefileinfooption.outDensity;
                return themefileinfooption.outInputStream;
            }
            mSkipFiles.put(i, Boolean.valueOf(true));
        }
        return super.openRawResource(i, typedvalue);
    }

    protected void reset()
    {
        mUpdatedTime = System.currentTimeMillis();
        mSkipFiles = new SparseArray();
        mIsPreloadOverlayed = new SparseArray();
    }

    public void updateConfiguration(Configuration configuration, DisplayMetrics displaymetrics, CompatibilityInfo compatibilityinfo)
    {
        int i;
        long l;
        if(configuration != null)
            i = getConfiguration().diff(configuration);
        else
            i = 0;
        super.updateConfiguration(configuration, displaymetrics, compatibilityinfo);
        if(!sMiuiThemeEnabled)
            return;
        if(mThemeResources == null)
            break MISSING_BLOCK_LABEL_134;
        l = mUpdatedTime;
        if(!MiuiConfiguration.needNewResources(i)) goto _L2; else goto _L1
_L1:
        configuration = sUpdatedTimeSystem;
        configuration;
        JVM INSTR monitorenter ;
        l = ThemeResources.getSystem().checkUpdate();
        if(sUpdatedTimeSystem.longValue() < l)
        {
            sUpdatedTimeSystem = Long.valueOf(l);
            delayGCAtlasPreloadedBitmaps();
            Resources.clearPreloadedCache();
        }
        configuration;
        JVM INSTR monitorexit ;
        l = Math.max(l, mThemeResources.checkUpdate());
          goto _L2
_L3:
        return;
        displaymetrics;
        throw displaymetrics;
_L2:
        if(mUpdatedTime < l || (i & 0x80) != 0 || (i & 0x200) != 0)
            reset();
          goto _L3
    }

    private static final boolean sMiuiThemeEnabled = ThemeCompatibility.isThemeEnabled();
    private static final SparseArray sPreloadDrawableSources = new SparseArray();
    public static Long sUpdatedTimeSystem = Long.valueOf(0L);
    private SparseArray mIsPreloadOverlayed;
    private String mPackageName;
    private Stack mPreloadingIds;
    private MiuiResources.ThemeFileInfoOption mPreloadingInfo;
    private SparseArray mSkipFiles;
    private ThemeResources mThemeResources;
    private ThemeValues mThemeValues;
    private long mUpdatedTime;
    private long mValueLoadedTime;

}
