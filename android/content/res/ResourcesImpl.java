// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.res;

import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.*;
import android.icu.text.PluralRules;
import android.os.*;
import android.util.*;
import android.view.DisplayAdjustments;
import com.android.internal.util.GrowingArrayUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Locale;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.content.res:
//            Configuration, DrawableCache, ConfigurationBoundResourceCache, XmlBlock, 
//            AssetManager, Resources, ConstantState, ColorStateList, 
//            XmlResourceParser, GradientColor, ComplexColor, CompatibilityInfo, 
//            FontResourcesParser, AssetFileDescriptor, TypedArray

public class ResourcesImpl
{
    private static class LookupStack
    {

        public boolean contains(int i)
        {
            for(int j = 0; j < mSize; j++)
                if(mIds[j] == i)
                    return true;

            return false;
        }

        public void pop()
        {
            mSize = mSize - 1;
        }

        public void push(int i)
        {
            mIds = GrowingArrayUtils.append(mIds, mSize, i);
            mSize = mSize + 1;
        }

        private int mIds[];
        private int mSize;

        private LookupStack()
        {
            mIds = new int[4];
            mSize = 0;
        }

        LookupStack(LookupStack lookupstack)
        {
            this();
        }
    }

    public class ThemeImpl
    {

        static Resources.ThemeKey _2D_get0(ThemeImpl themeimpl)
        {
            return themeimpl.mKey;
        }

        void applyStyle(int i, boolean flag)
        {
            Resources.ThemeKey themekey = mKey;
            themekey;
            JVM INSTR monitorenter ;
            AssetManager.applyThemeStyle(mTheme, i, flag);
            mThemeResId = i;
            mKey.append(i, flag);
            themekey;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public void dump(int i, String s, String s1)
        {
            Resources.ThemeKey themekey = mKey;
            themekey;
            JVM INSTR monitorenter ;
            AssetManager.dumpTheme(mTheme, i, s, s1);
            themekey;
            JVM INSTR monitorexit ;
            return;
            s;
            throw s;
        }

        protected void finalize()
            throws Throwable
        {
            super.finalize();
            mAssets.releaseTheme(mTheme);
        }

        int[] getAllAttributes()
        {
            return mAssets.getStyleAttributes(getAppliedStyleResId());
        }

        int getAppliedStyleResId()
        {
            return mThemeResId;
        }

        int getChangingConfigurations()
        {
            Resources.ThemeKey themekey = mKey;
            themekey;
            JVM INSTR monitorenter ;
            int i = ActivityInfo.activityInfoConfigNativeToJava(AssetManager.getThemeChangingConfigurations(mTheme));
            themekey;
            JVM INSTR monitorexit ;
            return i;
            Exception exception;
            exception;
            throw exception;
        }

        Resources.ThemeKey getKey()
        {
            return mKey;
        }

        long getNativeTheme()
        {
            return mTheme;
        }

        String[] getTheme()
        {
            Resources.ThemeKey themekey = mKey;
            themekey;
            JVM INSTR monitorenter ;
            int i;
            String as[];
            i = mKey.mCount;
            as = new String[i * 2];
            int j;
            j = 0;
            i--;
_L1:
            int k;
            boolean flag;
            if(j >= as.length)
                break MISSING_BLOCK_LABEL_124;
            k = mKey.mResId[i];
            flag = mKey.mForce[i];
            as[j] = getResourceName(k);
_L2:
            Object obj;
            if(flag)
                obj = "forced";
            else
                obj = "not forced";
            as[j + 1] = ((String) (obj));
            j += 2;
            i--;
              goto _L1
            obj;
            as[j] = Integer.toHexString(j);
              goto _L2
            obj;
            throw obj;
            themekey;
            JVM INSTR monitorexit ;
            return as;
        }

        TypedArray obtainStyledAttributes(Resources.Theme theme, AttributeSet attributeset, int ai[], int i, int j)
        {
            Resources.ThemeKey themekey = mKey;
            themekey;
            JVM INSTR monitorenter ;
            TypedArray typedarray;
            long l;
            int k = ai.length;
            typedarray = TypedArray.obtain(theme.getResources(), k);
            attributeset = (XmlBlock.Parser)attributeset;
            l = mTheme;
            if(attributeset == null)
                break MISSING_BLOCK_LABEL_87;
            long l1 = ((XmlBlock.Parser) (attributeset)).mParseState;
_L1:
            AssetManager.applyStyle(l, i, j, l1, ai, ai.length, typedarray.mDataAddress, typedarray.mIndicesAddress);
            typedarray.mTheme = theme;
            typedarray.mXml = attributeset;
            themekey;
            JVM INSTR monitorexit ;
            return typedarray;
            l1 = 0L;
              goto _L1
            theme;
            throw theme;
        }

        void rebase()
        {
            Resources.ThemeKey themekey = mKey;
            themekey;
            JVM INSTR monitorenter ;
            AssetManager.clearTheme(mTheme);
            int i = 0;
_L2:
            if(i >= mKey.mCount)
                break; /* Loop/switch isn't completed */
            int j = mKey.mResId[i];
            boolean flag = mKey.mForce[i];
            AssetManager.applyThemeStyle(mTheme, j, flag);
            i++;
            if(true) goto _L2; else goto _L1
_L1:
            return;
            Exception exception;
            exception;
            throw exception;
        }

        boolean resolveAttribute(int i, TypedValue typedvalue, boolean flag)
        {
            Resources.ThemeKey themekey = mKey;
            themekey;
            JVM INSTR monitorenter ;
            flag = mAssets.getThemeValue(mTheme, i, typedvalue, flag);
            themekey;
            JVM INSTR monitorexit ;
            return flag;
            typedvalue;
            throw typedvalue;
        }

        TypedArray resolveAttributes(Resources.Theme theme, int ai[], int ai1[])
        {
            Resources.ThemeKey themekey = mKey;
            themekey;
            JVM INSTR monitorenter ;
            int i = ai1.length;
            if(ai == null)
                break MISSING_BLOCK_LABEL_24;
            if(i == ai.length)
                break MISSING_BLOCK_LABEL_42;
            theme = JVM INSTR new #173 <Class IllegalArgumentException>;
            theme.IllegalArgumentException("Base attribute values must the same length as attrs");
            throw theme;
            theme;
            themekey;
            JVM INSTR monitorexit ;
            throw theme;
            TypedArray typedarray;
            typedarray = TypedArray.obtain(theme.getResources(), i);
            AssetManager.resolveAttrs(mTheme, 0, 0, ai, ai1, typedarray.mData, typedarray.mIndices);
            typedarray.mTheme = theme;
            typedarray.mXml = null;
            themekey;
            JVM INSTR monitorexit ;
            return typedarray;
        }

        void setTo(ThemeImpl themeimpl)
        {
            Resources.ThemeKey themekey = mKey;
            themekey;
            JVM INSTR monitorenter ;
            Resources.ThemeKey themekey1 = themeimpl.mKey;
            themekey1;
            JVM INSTR monitorenter ;
            AssetManager.copyTheme(mTheme, themeimpl.mTheme);
            mThemeResId = themeimpl.mThemeResId;
            mKey.setTo(themeimpl.getKey());
            themekey1;
            JVM INSTR monitorexit ;
            themekey;
            JVM INSTR monitorexit ;
            return;
            themeimpl;
            themekey1;
            JVM INSTR monitorexit ;
            throw themeimpl;
            themeimpl;
            themekey;
            JVM INSTR monitorexit ;
            throw themeimpl;
        }

        private final AssetManager mAssets;
        private final Resources.ThemeKey mKey = new Resources.ThemeKey();
        private final long mTheme;
        private int mThemeResId;
        final ResourcesImpl this$0;

        ThemeImpl()
        {
            this$0 = ResourcesImpl.this;
            super();
            mThemeResId = 0;
            mAssets = ResourcesImpl.this.mAssets;
            mTheme = mAssets.createTheme();
        }
    }


    public ResourcesImpl(AssetManager assetmanager, DisplayMetrics displaymetrics, Configuration configuration, DisplayAdjustments displayadjustments)
    {
        mLookupStack = ThreadLocal.withInitial(_.Lambda.s0O_nf1GRGlu9U9Grxb4QL6yOfw.$INST$0);
        mLastCachedXmlBlockIndex = -1;
        mAssets = assetmanager;
        mMetrics.setToDefaults();
        mDisplayAdjustments = displayadjustments;
        mConfiguration.setToDefaults();
        updateConfiguration(configuration, displaymetrics, displayadjustments.getCompatibilityInfo());
        mAssets.ensureStringBlocks();
    }

    private static String adjustLanguageTag(String s)
    {
        int i = s.indexOf('-');
        String s1;
        if(i == -1)
        {
            s1 = s;
            s = "";
        } else
        {
            s1 = s.substring(0, i);
            s = s.substring(i);
        }
        return (new StringBuilder()).append(Locale.adjustLanguageCode(s1)).append(s).toString();
    }

    private static int attrForQuantityCode(String s)
    {
        if(s.equals("zero"))
            return 0x1000005;
        if(s.equals("one"))
            return 0x1000006;
        if(s.equals("two"))
            return 0x1000007;
        if(s.equals("few"))
            return 0x1000008;
        return !s.equals("many") ? 0x1000004 : 0x1000009;
    }

    private void cacheDrawable(TypedValue typedvalue, boolean flag, DrawableCache drawablecache, Resources.Theme theme, boolean flag1, long l, 
            Drawable drawable)
    {
        drawable = drawable.getConstantState();
        if(drawable == null)
            return;
        if(!mPreloading) goto _L2; else goto _L1
_L1:
        int i = drawable.getChangingConfigurations();
        if(!flag) goto _L4; else goto _L3
_L3:
        if(verifyPreloadConfig(i, 0, typedvalue.resourceId, "drawable"))
            sPreloadedColorDrawables.put(l, drawable);
_L6:
        return;
_L4:
        if(verifyPreloadConfig(i, 8192, typedvalue.resourceId, "drawable"))
            if((i & 0x2000) == 0)
            {
                sPreloadedDrawables[0].put(l, drawable);
                sPreloadedDrawables[1].put(l, drawable);
            } else
            {
                sPreloadedDrawables[mConfiguration.getLayoutDirection()].put(l, drawable);
            }
        continue; /* Loop/switch isn't completed */
_L2:
        typedvalue = ((TypedValue) (mAccessLock));
        typedvalue;
        JVM INSTR monitorenter ;
        drawablecache.put(l, theme, drawable, flag1);
        if(!mCaching || !(flag ^ true))
            break MISSING_BLOCK_LABEL_177;
        sCachedDrawables.put(l, drawable);
        typedvalue;
        JVM INSTR monitorexit ;
        if(true) goto _L6; else goto _L5
_L5:
        drawablecache;
        throw drawablecache;
    }

    static void clearPreloadedCache()
    {
        sPreloadedDrawables[0].clear();
        sPreloadedDrawables[1].clear();
        sPreloadedColorDrawables.clear();
    }

    protected static Drawable createFromXmlForDensity(Resources resources, XmlPullParser xmlpullparser, int i, Resources.Theme theme, TypedValue typedvalue, int j)
        throws XmlPullParserException, IOException
    {
        Drawable drawable = resources.loadOverlayDrawable(typedvalue, j);
        typedvalue = drawable;
        if(drawable == null)
            typedvalue = Drawable.createFromXmlForDensity(resources, xmlpullparser, i, theme);
        return typedvalue;
    }

    private ColorStateList getColorStateListFromInt(TypedValue typedvalue, long l)
    {
        Object obj = (ConstantState)sPreloadedComplexColors.get(l);
        if(obj != null)
            return (ColorStateList)((ConstantState) (obj)).newInstance();
        obj = ColorStateList.valueOf(typedvalue.data);
        if(mPreloading && verifyPreloadConfig(typedvalue.changingConfigurations, 0, typedvalue.resourceId, "color"))
            sPreloadedComplexColors.put(l, ((ColorStateList) (obj)).getConstantState());
        return ((ColorStateList) (obj));
    }

    private PluralRules getPluralRule()
    {
        Object obj = sSync;
        obj;
        JVM INSTR monitorenter ;
        PluralRules pluralrules;
        if(mPluralRule == null)
            mPluralRule = PluralRules.forLocale(mConfiguration.getLocales().get(0));
        pluralrules = mPluralRule;
        obj;
        JVM INSTR monitorexit ;
        return pluralrules;
        Exception exception;
        exception;
        throw exception;
    }

    static LookupStack lambda$_2D_android_content_res_ResourcesImpl_5418()
    {
        return new LookupStack(null);
    }

    private ComplexColor loadComplexColorForCookie(Resources resources, TypedValue typedvalue, int i, Resources.Theme theme)
    {
        String s;
        Object obj;
        if(typedvalue.string == null)
            throw new UnsupportedOperationException((new StringBuilder()).append("Can't convert to ComplexColor: type=0x").append(typedvalue.type).toString());
        s = typedvalue.string.toString();
        obj = null;
        Trace.traceBegin(8192L, s);
        if(!s.endsWith(".xml"))
            break MISSING_BLOCK_LABEL_265;
        XmlResourceParser xmlresourceparser;
        AttributeSet attributeset;
        xmlresourceparser = loadXmlResourceParser(s, i, typedvalue.assetCookie, "ComplexColor");
        attributeset = Xml.asAttributeSet(xmlresourceparser);
        int j;
        do
            j = xmlresourceparser.next();
        while(j != 2 && j != 1);
        if(j != 2)
        {
            try
            {
                resources = JVM INSTR new #289 <Class XmlPullParserException>;
                resources.XmlPullParserException("No start tag found");
                throw resources;
            }
            // Misplaced declaration of an exception variable
            catch(Resources resources)
            {
                Trace.traceEnd(8192L);
            }
            typedvalue = new Resources.NotFoundException((new StringBuilder()).append("File ").append(s).append(" from ComplexColor resource ID #0x").append(Integer.toHexString(i)).toString());
            typedvalue.initCause(resources);
            throw typedvalue;
        }
        String s1 = xmlresourceparser.getName();
        if(!s1.equals("gradient")) goto _L2; else goto _L1
_L1:
        typedvalue = GradientColor.createFromXmlInner(resources, xmlresourceparser, attributeset, theme);
_L4:
        xmlresourceparser.close();
        Trace.traceEnd(8192L);
        return typedvalue;
_L2:
        typedvalue = obj;
        if(!s1.equals("selector")) goto _L4; else goto _L3
_L3:
        typedvalue = ColorStateList.createFromXmlInner(resources, xmlresourceparser, attributeset, theme);
          goto _L4
        Trace.traceEnd(8192L);
        throw new Resources.NotFoundException((new StringBuilder()).append("File ").append(s).append(" from drawable resource ID #0x").append(Integer.toHexString(i)).append(": .xml extension required").toString());
    }

    private ComplexColor loadComplexColorFromName(Resources resources, Resources.Theme theme, TypedValue typedvalue, int i)
    {
        long l = (long)typedvalue.assetCookie << 32 | (long)typedvalue.data;
        ConfigurationBoundResourceCache configurationboundresourcecache = mComplexColorCache;
        ComplexColor complexcolor = (ComplexColor)configurationboundresourcecache.getInstance(l, resources, theme);
        if(complexcolor != null)
            return complexcolor;
        Object obj = (ConstantState)sPreloadedComplexColors.get(l);
        if(obj != null)
            complexcolor = (ComplexColor)((ConstantState) (obj)).newInstance(resources, theme);
        obj = complexcolor;
        if(complexcolor == null)
            obj = loadComplexColorForCookie(resources, typedvalue, i, theme);
        if(obj != null)
        {
            ((ComplexColor) (obj)).setBaseChangingConfigurations(typedvalue.changingConfigurations);
            if(mPreloading)
            {
                if(verifyPreloadConfig(((ComplexColor) (obj)).getChangingConfigurations(), 0, typedvalue.resourceId, "color"))
                    sPreloadedComplexColors.put(l, ((ComplexColor) (obj)).getConstantState());
            } else
            {
                configurationboundresourcecache.put(l, theme, ((ComplexColor) (obj)).getConstantState());
            }
        }
        return ((ComplexColor) (obj));
    }

    private Drawable loadDrawableForCookie(Resources resources, TypedValue typedvalue, int i, int j, Resources.Theme theme)
    {
        String s;
        long l;
        int k;
        long l1;
        int i1;
        Object obj;
        if(typedvalue.string == null)
            throw new Resources.NotFoundException((new StringBuilder()).append("Resource \"").append(getResourceName(i)).append("\" (").append(Integer.toHexString(i)).append(") is not a Drawable (color or path): ").append(typedvalue).toString());
        s = typedvalue.string.toString();
        l = 0L;
        k = 0;
        l1 = 0L;
        i1 = 0;
        if(TRACE_FOR_DETAILED_PRELOAD)
        {
            l = System.nanoTime();
            k = Bitmap.sPreloadTracingNumInstantiatedBitmaps;
            l1 = Bitmap.sPreloadTracingTotalBitmapsSize;
            i1 = sPreloadTracingNumLoadedDrawables;
        }
        Trace.traceBegin(8192L, s);
        obj = (LookupStack)mLookupStack.get();
        try
        {
            if(((LookupStack) (obj)).contains(i))
            {
                resources = JVM INSTR new #358 <Class Exception>;
                resources.Exception("Recursive reference in drawable");
                throw resources;
            }
        }
        // Misplaced declaration of an exception variable
        catch(Resources resources)
        {
            Trace.traceEnd(8192L);
            typedvalue = new Resources.NotFoundException((new StringBuilder()).append("File ").append(s).append(" from drawable resource ID #0x").append(Integer.toHexString(i)).toString());
            typedvalue.initCause(resources);
            throw typedvalue;
        }
        ((LookupStack) (obj)).push(i);
        if(!s.endsWith(".xml"))
            break MISSING_BLOCK_LABEL_477;
        XmlResourceParser xmlresourceparser = loadXmlResourceParser(s, i, typedvalue.assetCookie, "drawable");
        resources = createFromXmlForDensity(resources, xmlresourceparser, j, theme, typedvalue, i);
        xmlresourceparser.close();
_L1:
        ((LookupStack) (obj)).pop();
        Trace.traceEnd(8192L);
        if(TRACE_FOR_DETAILED_PRELOAD && i >>> 24 == 1)
        {
            obj = getResourceName(i);
            if(obj != null)
            {
                long l2 = System.nanoTime();
                int j1 = Bitmap.sPreloadTracingNumInstantiatedBitmaps;
                long l3 = Bitmap.sPreloadTracingTotalBitmapsSize;
                int k1 = sPreloadTracingNumLoadedDrawables;
                sPreloadTracingNumLoadedDrawables++;
                if(Process.myUid() == 0)
                    j = 1;
                else
                    j = 0;
                theme = new StringBuilder();
                if(j != 0)
                    typedvalue = "Preloaded FW drawable #";
                else
                    typedvalue = "Loaded non-preloaded FW drawable #";
                Log.d("Resources.preload", theme.append(typedvalue).append(Integer.toHexString(i)).append(" ").append(((String) (obj))).append(" ").append(s).append(" ").append(resources.getClass().getCanonicalName()).append(" #nested_drawables= ").append(k1 - i1).append(" #bitmaps= ").append(j1 - k).append(" total_bitmap_size= ").append(l3 - l1).append(" in[us] ").append((l2 - l) / 1000L).toString());
            }
        }
        return resources;
        theme = mAssets.openNonAsset(typedvalue.assetCookie, s, 2);
        resources = createFromResourceStream(resources, typedvalue, theme, s, i);
        theme.close();
          goto _L1
        resources;
        ((LookupStack) (obj)).pop();
        throw resources;
    }

    private boolean verifyPreloadConfig(int i, int j, int k, String s)
    {
        if((0xbfffefff & i & j) != 0)
        {
            String s1;
            try
            {
                s1 = getResourceName(k);
            }
            catch(Resources.NotFoundException notfoundexception)
            {
                notfoundexception = "?";
            }
            Log.w("Resources", (new StringBuilder()).append("Preloaded ").append(s).append(" resource #0x").append(Integer.toHexString(k)).append(" (").append(s1).append(") that varies with configuration!!").toString());
            return false;
        } else
        {
            return true;
        }
    }

    public int calcConfigChanges(Configuration configuration)
    {
        if(configuration == null)
            return -1;
        mTmpConfig.setTo(configuration);
        int i = configuration.densityDpi;
        int j = i;
        if(i == 0)
            j = mMetrics.noncompatDensityDpi;
        mDisplayAdjustments.getCompatibilityInfo().applyToConfiguration(j, mTmpConfig);
        if(mTmpConfig.getLocales().isEmpty())
            mTmpConfig.setLocales(LocaleList.getDefault());
        return mConfiguration.updateFrom(mTmpConfig);
    }

    Drawable createFromResourceStream(Resources resources, TypedValue typedvalue, InputStream inputstream, String s, int i)
    {
        Drawable drawable = resources.loadOverlayDrawable(typedvalue, i);
        Drawable drawable1 = drawable;
        if(drawable == null)
            drawable1 = Drawable.createFromResourceStream(resources, typedvalue, inputstream, s, null);
        return drawable1;
    }

    void finishPreloading()
    {
        if(mPreloading)
        {
            if(TRACE_FOR_DETAILED_PRELOAD)
            {
                long l = SystemClock.uptimeMillis();
                long l1 = mPreloadTracingPreloadStartTime;
                long l2 = Bitmap.sPreloadTracingTotalBitmapsSize;
                long l3 = mPreloadTracingStartBitmapSize;
                long l4 = Bitmap.sPreloadTracingNumInstantiatedBitmaps;
                long l5 = mPreloadTracingStartBitmapCount;
                Log.d("Resources.preload", (new StringBuilder()).append("Preload finished, ").append(l4 - l5).append(" bitmaps of ").append(l2 - l3).append(" bytes in ").append(l - l1).append(" ms").toString());
            }
            mPreloading = false;
            flushLayoutCache();
        }
    }

    public void flushLayoutCache()
    {
        XmlBlock axmlblock[] = mCachedXmlBlocks;
        axmlblock;
        JVM INSTR monitorenter ;
        XmlBlock axmlblock1[];
        Arrays.fill(mCachedXmlBlockCookies, 0);
        Arrays.fill(mCachedXmlBlockFiles, null);
        axmlblock1 = mCachedXmlBlocks;
        int i = 0;
_L3:
        if(i >= 4) goto _L2; else goto _L1
_L1:
        XmlBlock xmlblock;
        xmlblock = axmlblock1[i];
        if(xmlblock == null)
            continue; /* Loop/switch isn't completed */
        xmlblock.close();
        i++;
          goto _L3
_L2:
        Arrays.fill(axmlblock1, null);
        axmlblock;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    ConfigurationBoundResourceCache getAnimatorCache()
    {
        return mAnimatorCache;
    }

    public AssetManager getAssets()
    {
        return mAssets;
    }

    CompatibilityInfo getCompatibilityInfo()
    {
        return mDisplayAdjustments.getCompatibilityInfo();
    }

    Configuration getConfiguration()
    {
        return mConfiguration;
    }

    public DisplayAdjustments getDisplayAdjustments()
    {
        return mDisplayAdjustments;
    }

    DisplayMetrics getDisplayMetrics()
    {
        return mMetrics;
    }

    int getIdentifier(String s, String s1, String s2)
    {
        if(s == null)
            throw new NullPointerException("name is null");
        int i;
        try
        {
            i = Integer.parseInt(s);
        }
        catch(Exception exception)
        {
            return mAssets.getResourceIdentifier(s, s1, s2);
        }
        return i;
    }

    android.graphics.drawable.Drawable.ConstantState getPreloadedDrawable(Resources resources, long l, int i)
    {
        android.graphics.drawable.Drawable.ConstantState constantstate = (android.graphics.drawable.Drawable.ConstantState)sPreloadedDrawables[mConfiguration.getLayoutDirection()].get(l);
        android.graphics.drawable.Drawable.ConstantState constantstate1 = constantstate;
        if(constantstate == null)
        {
            constantstate = (android.graphics.drawable.Drawable.ConstantState)sCachedDrawables.get(l);
            constantstate1 = constantstate;
            if(constantstate != null)
            {
                sCachedDrawables.remove(l);
                constantstate1 = constantstate;
            }
        }
        constantstate = constantstate1;
        if(constantstate1 != null)
        {
            constantstate = constantstate1;
            if(resources.isPreloadOverlayed(i))
                constantstate = null;
        }
        return constantstate;
    }

    LongSparseArray getPreloadedDrawables()
    {
        return sPreloadedDrawables[0];
    }

    CharSequence getQuantityText(int i, int j)
        throws Resources.NotFoundException
    {
        PluralRules pluralrules = getPluralRule();
        CharSequence charsequence = mAssets.getResourceBagText(i, attrForQuantityCode(pluralrules.select(j)));
        if(charsequence != null)
            return charsequence;
        charsequence = mAssets.getResourceBagText(i, 0x1000004);
        if(charsequence != null)
            return charsequence;
        else
            throw new Resources.NotFoundException((new StringBuilder()).append("Plural resource ID #0x").append(Integer.toHexString(i)).append(" quantity=").append(j).append(" item=").append(pluralrules.select(j)).toString());
    }

    String getResourceEntryName(int i)
        throws Resources.NotFoundException
    {
        String s = mAssets.getResourceEntryName(i);
        if(s != null)
            return s;
        else
            throw new Resources.NotFoundException((new StringBuilder()).append("Unable to find resource ID #0x").append(Integer.toHexString(i)).toString());
    }

    String getResourceName(int i)
        throws Resources.NotFoundException
    {
        String s = mAssets.getResourceName(i);
        if(s != null)
            return s;
        else
            throw new Resources.NotFoundException((new StringBuilder()).append("Unable to find resource ID #0x").append(Integer.toHexString(i)).toString());
    }

    String getResourcePackageName(int i)
        throws Resources.NotFoundException
    {
        String s = mAssets.getResourcePackageName(i);
        if(s != null)
            return s;
        else
            throw new Resources.NotFoundException((new StringBuilder()).append("Unable to find resource ID #0x").append(Integer.toHexString(i)).toString());
    }

    String getResourceTypeName(int i)
        throws Resources.NotFoundException
    {
        String s = mAssets.getResourceTypeName(i);
        if(s != null)
            return s;
        else
            throw new Resources.NotFoundException((new StringBuilder()).append("Unable to find resource ID #0x").append(Integer.toHexString(i)).toString());
    }

    Configuration[] getSizeConfigurations()
    {
        return mAssets.getSizeConfigurations();
    }

    ConfigurationBoundResourceCache getStateListAnimatorCache()
    {
        return mStateListAnimatorCache;
    }

    void getValue(int i, TypedValue typedvalue, boolean flag)
        throws Resources.NotFoundException
    {
        if(mAssets.getResourceValue(i, 0, typedvalue, flag))
            return;
        else
            throw new Resources.NotFoundException((new StringBuilder()).append("Resource ID #0x").append(Integer.toHexString(i)).toString());
    }

    void getValue(String s, TypedValue typedvalue, boolean flag)
        throws Resources.NotFoundException
    {
        int i = getIdentifier(s, "string", null);
        if(i != 0)
        {
            getValue(i, typedvalue, flag);
            return;
        } else
        {
            throw new Resources.NotFoundException((new StringBuilder()).append("String resource name ").append(s).toString());
        }
    }

    void getValueForDensity(int i, int j, TypedValue typedvalue, boolean flag)
        throws Resources.NotFoundException
    {
        if(mAssets.getResourceValue(i, j, typedvalue, flag))
            return;
        else
            throw new Resources.NotFoundException((new StringBuilder()).append("Resource ID #0x").append(Integer.toHexString(i)).toString());
    }

    boolean isPreloading()
    {
        return mPreloading;
    }

    ColorStateList loadColorStateList(Resources resources, TypedValue typedvalue, int i, Resources.Theme theme)
        throws Resources.NotFoundException
    {
        long l = typedvalue.assetCookie;
        long l1 = typedvalue.data;
        if(typedvalue.type >= 28 && typedvalue.type <= 31)
            return getColorStateListFromInt(typedvalue, l << 32 | l1);
        resources = loadComplexColorFromName(resources, theme, typedvalue, i);
        if(resources != null && (resources instanceof ColorStateList))
            return (ColorStateList)resources;
        else
            throw new Resources.NotFoundException((new StringBuilder()).append("Can't find ColorStateList from drawable resource ID #0x").append(Integer.toHexString(i)).toString());
    }

    ComplexColor loadComplexColor(Resources resources, TypedValue typedvalue, int i, Resources.Theme theme)
    {
        long l = typedvalue.assetCookie;
        long l1 = typedvalue.data;
        if(typedvalue.type >= 28 && typedvalue.type <= 31)
            return getColorStateListFromInt(typedvalue, l << 32 | l1);
        String s = typedvalue.string.toString();
        if(s.endsWith(".xml"))
        {
            try
            {
                resources = loadComplexColorFromName(resources, theme, typedvalue, i);
            }
            // Misplaced declaration of an exception variable
            catch(Resources resources)
            {
                typedvalue = new Resources.NotFoundException((new StringBuilder()).append("File ").append(s).append(" from complex color resource ID #0x").append(Integer.toHexString(i)).toString());
                typedvalue.initCause(resources);
                throw typedvalue;
            }
            return resources;
        } else
        {
            throw new Resources.NotFoundException((new StringBuilder()).append("File ").append(s).append(" from drawable resource ID #0x").append(Integer.toHexString(i)).append(": .xml extension required").toString());
        }
    }

    Drawable loadDrawable(Resources resources, TypedValue typedvalue, int i, int j, Resources.Theme theme)
        throws Resources.NotFoundException
    {
        boolean flag;
        boolean flag1;
        DrawableCache drawablecache;
        long l;
        Drawable drawable;
        if(j == 0 || typedvalue.density == mMetrics.densityDpi)
            flag = true;
        else
            flag = false;
        if(j > 0 && typedvalue.density > 0 && typedvalue.density != 65535)
            if(typedvalue.density == j)
                typedvalue.density = mMetrics.densityDpi;
            else
                typedvalue.density = (typedvalue.density * mMetrics.densityDpi) / j;
        if(typedvalue.type < 28 || typedvalue.type > 31) goto _L2; else goto _L1
_L1:
        flag1 = true;
        drawablecache = mColorDrawableCache;
        l = typedvalue.data;
_L4:
        if(mPreloading || !flag)
            break; /* Loop/switch isn't completed */
        drawable = drawablecache.getInstance(l, resources, theme);
        if(drawable == null)
            break; /* Loop/switch isn't completed */
        drawable.setChangingConfigurations(typedvalue.changingConfigurations);
        return drawable;
_L2:
        flag1 = false;
        drawablecache = mDrawableCache;
        l = (long)typedvalue.assetCookie << 32 | (long)typedvalue.data;
        if(true) goto _L4; else goto _L3
_L3:
        if(!flag1) goto _L6; else goto _L5
_L5:
        Object obj;
        boolean flag2;
        String s;
        Drawable drawable1;
        boolean flag3;
        try
        {
            obj = (android.graphics.drawable.Drawable.ConstantState)sPreloadedColorDrawables.get(l);
        }
        // Misplaced declaration of an exception variable
        catch(TypedValue typedvalue)
        {
            try
            {
                resources = getResourceName(i);
            }
            // Misplaced declaration of an exception variable
            catch(Resources resources)
            {
                resources = "(missing name)";
            }
            resources = new Resources.NotFoundException((new StringBuilder()).append("Drawable ").append(resources).append(" with resource ID #0x").append(Integer.toHexString(i)).toString(), typedvalue);
            resources.setStackTrace(new StackTraceElement[0]);
            throw resources;
        }
        flag2 = false;
        if(obj == null) goto _L8; else goto _L7
_L7:
        if(!TRACE_FOR_DETAILED_PRELOAD || i >>> 24 != 1)
            break MISSING_BLOCK_LABEL_298;
        if(Process.myUid() == 0)
            break MISSING_BLOCK_LABEL_298;
        s = getResourceName(i);
        if(s == null)
            break MISSING_BLOCK_LABEL_298;
        StringBuilder stringbuilder = JVM INSTR new #200 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.d("Resources.preload", stringbuilder.append("Hit preloaded FW drawable #").append(Integer.toHexString(i)).append(" ").append(s).toString());
        obj = ((android.graphics.drawable.Drawable.ConstantState) (obj)).newDrawable(resources);
_L9:
        j = ((flag2) ? 1 : 0);
        if(obj instanceof DrawableContainer)
            j = 1;
        if(obj == null)
            break MISSING_BLOCK_LABEL_496;
        flag3 = ((Drawable) (obj)).canApplyTheme();
_L10:
        drawable1 = ((Drawable) (obj));
        if(!flag3)
            break MISSING_BLOCK_LABEL_370;
        drawable1 = ((Drawable) (obj));
        if(theme == null)
            break MISSING_BLOCK_LABEL_370;
        drawable1 = ((Drawable) (obj)).mutate();
        drawable1.applyTheme(theme);
        drawable1.clearMutated();
        obj = drawable1;
        if(drawable1 == null)
            break MISSING_BLOCK_LABEL_444;
        drawable1.setChangingConfigurations(typedvalue.changingConfigurations);
        obj = drawable1;
        if(!flag)
            break MISSING_BLOCK_LABEL_444;
        cacheDrawable(typedvalue, flag1, drawablecache, theme, flag3, l, drawable1);
        obj = drawable1;
        if(j == 0)
            break MISSING_BLOCK_LABEL_444;
        typedvalue = drawable1.getConstantState();
        obj = drawable1;
        if(typedvalue == null)
            break MISSING_BLOCK_LABEL_444;
        obj = typedvalue.newDrawable(resources);
        return ((Drawable) (obj));
_L6:
        obj = getPreloadedDrawable(resources, l, i);
        break MISSING_BLOCK_LABEL_213;
_L8:
        if(!flag1)
            break MISSING_BLOCK_LABEL_481;
        obj = new ColorDrawable(typedvalue.data);
          goto _L9
        obj = loadDrawableForCookie(resources, typedvalue, i, j, null);
          goto _L9
        flag3 = false;
          goto _L10
    }

    public Typeface loadFont(Resources resources, TypedValue typedvalue, int i)
    {
        String s;
        if(typedvalue.string == null)
            throw new Resources.NotFoundException((new StringBuilder()).append("Resource \"").append(getResourceName(i)).append("\" (").append(Integer.toHexString(i)).append(") is not a Font: ").append(typedvalue).toString());
        s = typedvalue.string.toString();
        if(!s.startsWith("res/"))
            return null;
        Typeface typeface = Typeface.findFromCache(mAssets, s);
        if(typeface != null)
            return typeface;
        Trace.traceBegin(8192L, s);
        if(!s.endsWith("xml"))
            break MISSING_BLOCK_LABEL_174;
        resources = FontResourcesParser.parse(loadXmlResourceParser(s, i, typedvalue.assetCookie, "font"), resources);
        if(resources == null)
        {
            Trace.traceEnd(8192L);
            return null;
        }
        resources = Typeface.createFromResources(resources, mAssets, s);
        Trace.traceEnd(8192L);
        return resources;
        resources = Typeface.createFromResources(mAssets, s, typedvalue.assetCookie);
        Trace.traceEnd(8192L);
        return resources;
        typedvalue;
        resources = JVM INSTR new #200 <Class StringBuilder>;
        resources.StringBuilder();
        Log.e("Resources", resources.append("Failed to read xml resource ").append(s).toString(), typedvalue);
        Trace.traceEnd(8192L);
_L2:
        return null;
        resources;
        typedvalue = JVM INSTR new #200 <Class StringBuilder>;
        typedvalue.StringBuilder();
        Log.e("Resources", typedvalue.append("Failed to parse xml resource ").append(s).toString(), resources);
        Trace.traceEnd(8192L);
        if(true) goto _L2; else goto _L1
_L1:
        resources;
        Trace.traceEnd(8192L);
        throw resources;
    }

    XmlResourceParser loadXmlResourceParser(String s, int i, int j, String s1)
        throws Resources.NotFoundException
    {
        if(i == 0) goto _L2; else goto _L1
_L1:
        XmlBlock axmlblock[] = mCachedXmlBlocks;
        axmlblock;
        JVM INSTR monitorenter ;
        int ai[];
        Object obj;
        XmlBlock axmlblock1[];
        int k;
        ai = mCachedXmlBlockCookies;
        obj = mCachedXmlBlockFiles;
        axmlblock1 = mCachedXmlBlocks;
        k = obj.length;
        int l = 0;
_L5:
        if(l >= k) goto _L4; else goto _L3
_L3:
        if(ai[l] != j || obj[l] == null)
            continue; /* Loop/switch isn't completed */
        if(!obj[l].equals(s))
            continue; /* Loop/switch isn't completed */
        obj = axmlblock1[l].newParser();
        axmlblock;
        JVM INSTR monitorexit ;
        return ((XmlResourceParser) (obj));
          goto _L5
_L4:
        XmlBlock xmlblock = mAssets.openXmlBlockAsset(j, s);
        if(xmlblock == null)
            break MISSING_BLOCK_LABEL_179;
        l = (mLastCachedXmlBlockIndex + 1) % k;
        mLastCachedXmlBlockIndex = l;
        XmlBlock xmlblock1;
        xmlblock1 = axmlblock1[l];
        if(xmlblock1 == null)
            break MISSING_BLOCK_LABEL_147;
        xmlblock1.close();
        ai[l] = j;
        obj[l] = s;
        axmlblock1[l] = xmlblock;
        obj = xmlblock.newParser();
        axmlblock;
        JVM INSTR monitorexit ;
        return ((XmlResourceParser) (obj));
        axmlblock;
        JVM INSTR monitorexit ;
_L2:
        throw new Resources.NotFoundException((new StringBuilder()).append("File ").append(s).append(" from xml type ").append(s1).append(" resource ID #0x").append(Integer.toHexString(i)).toString());
        Exception exception1;
        exception1;
        axmlblock;
        JVM INSTR monitorexit ;
        throw exception1;
        Exception exception;
        exception;
        s = new Resources.NotFoundException((new StringBuilder()).append("File ").append(s).append(" from xml type ").append(s1).append(" resource ID #0x").append(Integer.toHexString(i)).toString());
        s.initCause(exception);
        throw s;
    }

    ThemeImpl newThemeImpl()
    {
        return new ThemeImpl();
    }

    ThemeImpl newThemeImpl(Resources.ThemeKey themekey)
    {
        ThemeImpl themeimpl = new ThemeImpl();
        ThemeImpl._2D_get0(themeimpl).setTo(themekey);
        themeimpl.rebase();
        return themeimpl;
    }

    InputStream openRawResource(int i, TypedValue typedvalue)
        throws Resources.NotFoundException
    {
        getValue(i, typedvalue, true);
        InputStream inputstream;
        try
        {
            inputstream = mAssets.openNonAsset(typedvalue.assetCookie, typedvalue.string.toString(), 2);
        }
        catch(Exception exception)
        {
            StringBuilder stringbuilder = (new StringBuilder()).append("File ");
            if(typedvalue.string == null)
                typedvalue = "(null)";
            else
                typedvalue = typedvalue.string.toString();
            typedvalue = new Resources.NotFoundException(stringbuilder.append(typedvalue).append(" from drawable resource ID #0x").append(Integer.toHexString(i)).toString());
            typedvalue.initCause(exception);
            throw typedvalue;
        }
        return inputstream;
    }

    AssetFileDescriptor openRawResourceFd(int i, TypedValue typedvalue)
        throws Resources.NotFoundException
    {
        getValue(i, typedvalue, true);
        AssetFileDescriptor assetfiledescriptor;
        try
        {
            assetfiledescriptor = mAssets.openNonAssetFd(typedvalue.assetCookie, typedvalue.string.toString());
        }
        catch(Exception exception)
        {
            throw new Resources.NotFoundException((new StringBuilder()).append("File ").append(typedvalue.string.toString()).append(" from drawable ").append("resource ID #0x").append(Integer.toHexString(i)).toString(), exception);
        }
        return assetfiledescriptor;
    }

    public final void startPreloading()
    {
        synchronized(sSync)
        {
            if(sPreloaded)
            {
                IllegalStateException illegalstateexception = JVM INSTR new #910 <Class IllegalStateException>;
                illegalstateexception.IllegalStateException("Resources already preloaded");
                throw illegalstateexception;
            }
            break MISSING_BLOCK_LABEL_30;
        }
        sPreloaded = true;
        mPreloading = true;
        mConfiguration.densityDpi = DisplayMetrics.DENSITY_DEVICE;
        updateConfiguration(null, null, null);
        if(TRACE_FOR_DETAILED_PRELOAD)
        {
            mPreloadTracingPreloadStartTime = SystemClock.uptimeMillis();
            mPreloadTracingStartBitmapSize = Bitmap.sPreloadTracingTotalBitmapsSize;
            mPreloadTracingStartBitmapCount = Bitmap.sPreloadTracingNumInstantiatedBitmaps;
            Log.d("Resources.preload", "Preload starting");
        }
        obj;
        JVM INSTR monitorexit ;
    }

    public void updateConfiguration(Configuration configuration, DisplayMetrics displaymetrics, CompatibilityInfo compatibilityinfo)
    {
        Trace.traceBegin(8192L, "ResourcesImpl#updateConfiguration");
        Object obj = mAccessLock;
        obj;
        JVM INSTR monitorenter ;
        if(compatibilityinfo == null)
            break MISSING_BLOCK_LABEL_30;
        mDisplayAdjustments.setCompatibilityInfo(compatibilityinfo);
        if(displaymetrics == null)
            break MISSING_BLOCK_LABEL_42;
        mMetrics.setTo(displaymetrics);
        int i;
        mDisplayAdjustments.getCompatibilityInfo().applyToDisplayMetrics(mMetrics);
        i = calcConfigChanges(configuration);
        configuration = mConfiguration.getLocales();
        displaymetrics = configuration;
        if(configuration.isEmpty())
        {
            displaymetrics = LocaleList.getDefault();
            mConfiguration.setLocales(displaymetrics);
        }
        if((i & 4) == 0)
            break MISSING_BLOCK_LABEL_188;
        if(displaymetrics.size() <= 1)
            break MISSING_BLOCK_LABEL_188;
        compatibilityinfo = mAssets.getNonSystemLocales();
        configuration = compatibilityinfo;
        if(!LocaleList.isPseudoLocalesOnly(compatibilityinfo))
            break MISSING_BLOCK_LABEL_143;
        compatibilityinfo = mAssets.getLocales();
        configuration = compatibilityinfo;
        if(LocaleList.isPseudoLocalesOnly(compatibilityinfo))
            configuration = null;
        if(configuration == null)
            break MISSING_BLOCK_LABEL_188;
        configuration = displaymetrics.getFirstMatchWithEnglishSupported(configuration);
        if(configuration == null)
            break MISSING_BLOCK_LABEL_188;
        if(configuration != displaymetrics.get(0))
        {
            Configuration configuration1 = mConfiguration;
            compatibilityinfo = JVM INSTR new #340 <Class LocaleList>;
            compatibilityinfo.LocaleList(configuration, displaymetrics);
            configuration1.setLocales(compatibilityinfo);
        }
        float f;
        if(mConfiguration.densityDpi != 0)
        {
            mMetrics.densityDpi = mConfiguration.densityDpi;
            mMetrics.density = (float)mConfiguration.densityDpi * 0.00625F;
        }
        configuration = mMetrics;
        f = mMetrics.density;
        if(mConfiguration.fontScale == 0.0F) goto _L2; else goto _L1
_L1:
        float f1 = mConfiguration.fontScale;
_L7:
        configuration.scaledDensity = f1 * f;
        if(mMetrics.widthPixels < mMetrics.heightPixels) goto _L4; else goto _L3
_L3:
        int j;
        int k;
        j = mMetrics.widthPixels;
        k = mMetrics.heightPixels;
_L8:
        if(mConfiguration.keyboardHidden != 1 || mConfiguration.hardKeyboardHidden != 2) goto _L6; else goto _L5
_L5:
        int l = 3;
_L9:
        mAssets.setConfiguration(mConfiguration.mcc, mConfiguration.mnc, adjustLanguageTag(mConfiguration.getLocales().get(0).toLanguageTag()), mConfiguration.orientation, mConfiguration.touchscreen, mConfiguration.densityDpi, mConfiguration.keyboard, l, mConfiguration.navigation, j, k, mConfiguration.smallestScreenWidthDp, mConfiguration.screenWidthDp, mConfiguration.screenHeightDp, mConfiguration.screenLayout, mConfiguration.uiMode, mConfiguration.colorMode, android.os.Build.VERSION.RESOURCES_SDK_INT);
        mDrawableCache.onConfigurationChange(i);
        mColorDrawableCache.onConfigurationChange(i);
        mComplexColorCache.onConfigurationChange(i);
        mAnimatorCache.onConfigurationChange(i);
        mStateListAnimatorCache.onConfigurationChange(i);
        flushLayoutCache();
        obj;
        JVM INSTR monitorexit ;
        displaymetrics = ((DisplayMetrics) (sSync));
        displaymetrics;
        JVM INSTR monitorenter ;
        if(mPluralRule != null)
            mPluralRule = PluralRules.forLocale(mConfiguration.getLocales().get(0));
        displaymetrics;
        JVM INSTR monitorexit ;
        Trace.traceEnd(8192L);
        return;
_L2:
        f1 = 1.0F;
          goto _L7
_L4:
        j = mMetrics.heightPixels;
        k = mMetrics.widthPixels;
          goto _L8
_L6:
        l = mConfiguration.keyboardHidden;
          goto _L9
        configuration;
        obj;
        JVM INSTR monitorexit ;
        throw configuration;
        configuration;
        Trace.traceEnd(8192L);
        throw configuration;
        configuration;
        displaymetrics;
        JVM INSTR monitorexit ;
        throw configuration;
          goto _L7
    }

    private static final boolean DEBUG_CONFIG = false;
    private static final boolean DEBUG_LOAD = false;
    private static final int ID_OTHER = 0x1000004;
    static final String TAG = "Resources";
    static final String TAG_PRELOAD = "Resources.preload";
    public static final boolean TRACE_FOR_DETAILED_PRELOAD = SystemProperties.getBoolean("debug.trace_resource_preload", false);
    private static final boolean TRACE_FOR_MISS_PRELOAD = false;
    private static final boolean TRACE_FOR_PRELOAD = false;
    private static final int XML_BLOCK_CACHE_SIZE = 4;
    private static final LongSparseArray sCachedDrawables = new LongSparseArray();
    private static int sPreloadTracingNumLoadedDrawables;
    private static boolean sPreloaded;
    private static final LongSparseArray sPreloadedColorDrawables = new LongSparseArray();
    private static final LongSparseArray sPreloadedComplexColors = new LongSparseArray();
    private static final LongSparseArray sPreloadedDrawables[];
    private static final Object sSync = new Object();
    private final Object mAccessLock = new Object();
    private final ConfigurationBoundResourceCache mAnimatorCache = new ConfigurationBoundResourceCache();
    final AssetManager mAssets;
    private final int mCachedXmlBlockCookies[] = new int[4];
    private final String mCachedXmlBlockFiles[] = new String[4];
    private final XmlBlock mCachedXmlBlocks[] = new XmlBlock[4];
    private boolean mCaching;
    private final DrawableCache mColorDrawableCache = new DrawableCache();
    private final ConfigurationBoundResourceCache mComplexColorCache = new ConfigurationBoundResourceCache();
    private final Configuration mConfiguration = new Configuration();
    private final DisplayAdjustments mDisplayAdjustments;
    private final DrawableCache mDrawableCache = new DrawableCache();
    private int mLastCachedXmlBlockIndex;
    private final ThreadLocal mLookupStack;
    private final DisplayMetrics mMetrics = new DisplayMetrics();
    private PluralRules mPluralRule;
    private long mPreloadTracingPreloadStartTime;
    private long mPreloadTracingStartBitmapCount;
    private long mPreloadTracingStartBitmapSize;
    private boolean mPreloading;
    private final ConfigurationBoundResourceCache mStateListAnimatorCache = new ConfigurationBoundResourceCache();
    private final Configuration mTmpConfig = new Configuration();

    static 
    {
        sPreloadedDrawables = new LongSparseArray[2];
        sPreloadedDrawables[0] = new LongSparseArray();
        sPreloadedDrawables[1] = new LongSparseArray();
    }
}
