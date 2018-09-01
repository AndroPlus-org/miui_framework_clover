// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.res;

import android.graphics.Movie;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableInflater;
import android.miui.ResourcesManager;
import android.os.Bundle;
import android.os.LocaleList;
import android.util.*;
import android.view.DisplayAdjustments;
import android.view.ViewHierarchyEncoder;
import com.android.internal.util.GrowingArrayUtils;
import com.android.internal.util.XmlUtils;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.content.res:
//            Configuration, MiuiResourcesImpl, AssetManager, ResourcesImpl, 
//            MiuiResources, ColorStateList, TypedArray, XmlResourceParser, 
//            CompatibilityInfo, ConfigurationBoundResourceCache, ComplexColor, AssetFileDescriptor

public class Resources
{
    public static class NotFoundException extends RuntimeException
    {

        public NotFoundException()
        {
        }

        public NotFoundException(String s)
        {
            super(s);
        }

        public NotFoundException(String s, Exception exception)
        {
            super(s, exception);
        }
    }

    public final class Theme
    {

        private String getResourceNameFromHexString(String s)
        {
            return getResourceName(Integer.parseInt(s, 16));
        }

        public void applyStyle(int i, boolean flag)
        {
            mThemeImpl.applyStyle(i, flag);
        }

        public void dump(int i, String s, String s1)
        {
            mThemeImpl.dump(i, s, s1);
        }

        public void encode(ViewHierarchyEncoder viewhierarchyencoder)
        {
            viewhierarchyencoder.beginObject(this);
            String as[] = getTheme();
            for(int i = 0; i < as.length; i += 2)
                viewhierarchyencoder.addProperty(as[i], as[i + 1]);

            viewhierarchyencoder.endObject();
        }

        public int[] getAllAttributes()
        {
            return mThemeImpl.getAllAttributes();
        }

        int getAppliedStyleResId()
        {
            return mThemeImpl.getAppliedStyleResId();
        }

        public int getChangingConfigurations()
        {
            return mThemeImpl.getChangingConfigurations();
        }

        public Drawable getDrawable(int i)
            throws NotFoundException
        {
            return Resources.this.getDrawable(i, this);
        }

        public ThemeKey getKey()
        {
            return mThemeImpl.getKey();
        }

        long getNativeTheme()
        {
            return mThemeImpl.getNativeTheme();
        }

        public Resources getResources()
        {
            return Resources.this;
        }

        public String[] getTheme()
        {
            return mThemeImpl.getTheme();
        }

        public TypedArray obtainStyledAttributes(int i, int ai[])
            throws NotFoundException
        {
            return loadOverlayTypedArray(mThemeImpl.obtainStyledAttributes(this, null, ai, 0, i));
        }

        public TypedArray obtainStyledAttributes(AttributeSet attributeset, int ai[], int i, int j)
        {
            return loadOverlayTypedArray(mThemeImpl.obtainStyledAttributes(this, attributeset, ai, i, j));
        }

        public TypedArray obtainStyledAttributes(int ai[])
        {
            return loadOverlayTypedArray(mThemeImpl.obtainStyledAttributes(this, null, ai, 0, 0));
        }

        public void rebase()
        {
            mThemeImpl.rebase();
        }

        public boolean resolveAttribute(int i, TypedValue typedvalue, boolean flag)
        {
            flag = mThemeImpl.resolveAttribute(i, typedvalue, flag);
            if(flag)
                loadOverlayValue(typedvalue, i);
            return flag;
        }

        public TypedArray resolveAttributes(int ai[], int ai1[])
        {
            return mThemeImpl.resolveAttributes(this, ai, ai1);
        }

        void setImpl(ResourcesImpl.ThemeImpl themeimpl)
        {
            mThemeImpl = themeimpl;
        }

        public void setTo(Theme theme)
        {
            mThemeImpl.setTo(theme.mThemeImpl);
        }

        private ResourcesImpl.ThemeImpl mThemeImpl;
        final Resources this$0;

        private Theme()
        {
            this$0 = Resources.this;
            super();
        }

        Theme(Theme theme)
        {
            this();
        }
    }

    static class ThemeKey
        implements Cloneable
    {

        public void append(int i, boolean flag)
        {
            if(mResId == null)
                mResId = new int[4];
            if(mForce == null)
                mForce = new boolean[4];
            mResId = GrowingArrayUtils.append(mResId, mCount, i);
            mForce = GrowingArrayUtils.append(mForce, mCount, flag);
            mCount = mCount + 1;
            int j = mHashCode;
            int k;
            if(flag)
                k = 1;
            else
                k = 0;
            mHashCode = k + (j * 31 + i) * 31;
        }

        public ThemeKey clone()
        {
            ThemeKey themekey = new ThemeKey();
            themekey.mResId = mResId;
            themekey.mForce = mForce;
            themekey.mCount = mCount;
            themekey.mHashCode = mHashCode;
            return themekey;
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
        }

        public boolean equals(Object obj)
        {
            if(this == obj)
                return true;
            while(obj == null || getClass() != obj.getClass() || hashCode() != obj.hashCode()) 
                return false;
            obj = (ThemeKey)obj;
            if(mCount != ((ThemeKey) (obj)).mCount)
                return false;
            int i = mCount;
            for(int j = 0; j < i; j++)
                if(mResId[j] != ((ThemeKey) (obj)).mResId[j] || mForce[j] != ((ThemeKey) (obj)).mForce[j])
                    return false;

            return true;
        }

        public int hashCode()
        {
            return mHashCode;
        }

        public void setTo(ThemeKey themekey)
        {
            Object obj = null;
            boolean aflag[];
            if(themekey.mResId == null)
                aflag = null;
            else
                aflag = (int[])themekey.mResId.clone();
            mResId = aflag;
            if(themekey.mForce == null)
                aflag = obj;
            else
                aflag = (boolean[])themekey.mForce.clone();
            mForce = aflag;
            mCount = themekey.mCount;
        }

        int mCount;
        boolean mForce[];
        private int mHashCode;
        int mResId[];

        ThemeKey()
        {
            mHashCode = 0;
        }
    }


    Resources()
    {
        this(null);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        displaymetrics.setToDefaults();
        Configuration configuration = new Configuration();
        configuration.setToDefaults();
        mResourcesImpl = new MiuiResourcesImpl(AssetManager.getSystem(), displaymetrics, configuration, new DisplayAdjustments());
    }

    public Resources(AssetManager assetmanager, DisplayMetrics displaymetrics, Configuration configuration)
    {
        this(null);
        mResourcesImpl = new MiuiResourcesImpl(assetmanager, displaymetrics, configuration, new DisplayAdjustments());
    }

    public Resources(ClassLoader classloader)
    {
        mTypedArrayPool = new android.util.Pools.SynchronizedPool(5);
        mTmpValueLock = new Object();
        mTmpValue = new TypedValue();
        mThemeRefs = new ArrayList();
        ClassLoader classloader1 = classloader;
        if(classloader == null)
            classloader1 = ClassLoader.getSystemClassLoader();
        mClassLoader = classloader1;
    }

    static void clearPreloadedCache()
    {
        ResourcesImpl.clearPreloadedCache();
    }

    public static Resources getSystem()
    {
        Object obj = sSync;
        obj;
        JVM INSTR monitorenter ;
        Resources resources = mSystem;
        Object obj1;
        obj1 = resources;
        if(resources != null)
            break MISSING_BLOCK_LABEL_33;
        obj1 = JVM INSTR new #113 <Class MiuiResources>;
        ((MiuiResources) (obj1)).MiuiResources();
        mSystem = ((Resources) (obj1));
        ResourcesManager.initMiuiResource(((Resources) (obj1)), null);
        obj;
        JVM INSTR monitorexit ;
        return ((Resources) (obj1));
        Exception exception;
        exception;
        throw exception;
    }

    public static TypedArray obtainAttributes(Resources resources, Theme theme, AttributeSet attributeset, int ai[])
    {
        if(theme == null)
            return resources.obtainAttributes(attributeset, ai);
        else
            return theme.obtainStyledAttributes(attributeset, ai, 0, 0);
    }

    private TypedValue obtainTempTypedValue()
    {
        Object obj = null;
        Object obj1 = mTmpValueLock;
        obj1;
        JVM INSTR monitorenter ;
        if(mTmpValue != null)
        {
            obj = mTmpValue;
            mTmpValue = null;
        }
        obj1;
        JVM INSTR monitorexit ;
        if(obj == null)
            return new TypedValue();
        else
            return ((TypedValue) (obj));
        obj;
        throw obj;
    }

    private void releaseTempTypedValue(TypedValue typedvalue)
    {
        Object obj = mTmpValueLock;
        obj;
        JVM INSTR monitorenter ;
        if(mTmpValue == null)
            mTmpValue = typedvalue;
        obj;
        JVM INSTR monitorexit ;
        return;
        typedvalue;
        throw typedvalue;
    }

    public static boolean resourceHasPackage(int i)
    {
        boolean flag = false;
        if(i >>> 24 != 0)
            flag = true;
        return flag;
    }

    public static int selectDefaultTheme(int i, int j)
    {
        return selectSystemTheme(i, j, 0x1030005, 0x103006b, 0x1030128, 0x103013f);
    }

    public static int selectSystemTheme(int i, int j, int k, int l, int i1, int j1)
    {
        if(i != 0)
            return i;
        if(j < 11)
            return k;
        if(j < 14)
            return l;
        if(j < 24)
            return i1;
        else
            return j1;
    }

    public static void updateSystemConfiguration(Configuration configuration, DisplayMetrics displaymetrics, CompatibilityInfo compatibilityinfo)
    {
        if(mSystem != null)
            mSystem.updateConfiguration(configuration, displaymetrics, compatibilityinfo);
    }

    public int calcConfigChanges(Configuration configuration)
    {
        return mResourcesImpl.calcConfigChanges(configuration);
    }

    public final void finishPreloading()
    {
        mResourcesImpl.finishPreloading();
    }

    public final void flushLayoutCache()
    {
        mResourcesImpl.flushLayoutCache();
    }

    public XmlResourceParser getAnimation(int i)
        throws NotFoundException
    {
        return loadXmlResourceParser(i, "anim");
    }

    public ConfigurationBoundResourceCache getAnimatorCache()
    {
        return mResourcesImpl.getAnimatorCache();
    }

    public final AssetManager getAssets()
    {
        return mResourcesImpl.getAssets();
    }

    public boolean getBoolean(int i)
        throws NotFoundException
    {
        boolean flag;
        TypedValue typedvalue;
        flag = true;
        typedvalue = obtainTempTypedValue();
        mResourcesImpl.getValue(i, typedvalue, true);
        if(typedvalue.type < 16 || typedvalue.type > 31)
            break MISSING_BLOCK_LABEL_56;
        i = typedvalue.data;
        if(i == 0)
            flag = false;
        releaseTempTypedValue(typedvalue);
        return flag;
        NotFoundException notfoundexception = JVM INSTR new #6   <Class Resources$NotFoundException>;
        StringBuilder stringbuilder = JVM INSTR new #196 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        notfoundexception.NotFoundException(stringbuilder.append("Resource ID #0x").append(Integer.toHexString(i)).append(" type #0x").append(Integer.toHexString(typedvalue.type)).append(" is not valid").toString());
        throw notfoundexception;
        Exception exception;
        exception;
        releaseTempTypedValue(typedvalue);
        throw exception;
    }

    public ClassLoader getClassLoader()
    {
        return mClassLoader;
    }

    public int getColor(int i)
        throws NotFoundException
    {
        return getColor(i, null);
    }

    public int getColor(int i, Theme theme)
        throws NotFoundException
    {
        TypedValue typedvalue = obtainTempTypedValue();
        Object obj;
        obj = mResourcesImpl;
        ((ResourcesImpl) (obj)).getValue(i, typedvalue, true);
        if(typedvalue.type < 16 || typedvalue.type > 31)
            break MISSING_BLOCK_LABEL_49;
        i = typedvalue.data;
        releaseTempTypedValue(typedvalue);
        return i;
        if(typedvalue.type != 3)
        {
            obj = JVM INSTR new #6   <Class Resources$NotFoundException>;
            theme = JVM INSTR new #196 <Class StringBuilder>;
            theme.StringBuilder();
            ((NotFoundException) (obj)).NotFoundException(theme.append("Resource ID #0x").append(Integer.toHexString(i)).append(" type #0x").append(Integer.toHexString(typedvalue.type)).append(" is not valid").toString());
            throw obj;
        }
        break MISSING_BLOCK_LABEL_122;
        theme;
        releaseTempTypedValue(typedvalue);
        throw theme;
        i = ((ResourcesImpl) (obj)).loadColorStateList(this, typedvalue, i, theme).getDefaultColor();
        releaseTempTypedValue(typedvalue);
        return i;
    }

    public ColorStateList getColorStateList(int i)
        throws NotFoundException
    {
        ColorStateList colorstatelist = getColorStateList(i, null);
        if(colorstatelist != null && colorstatelist.canApplyTheme())
            Log.w("Resources", (new StringBuilder()).append("ColorStateList ").append(getResourceName(i)).append(" has ").append("unresolved theme attributes! Consider using ").append("Resources.getColorStateList(int, Theme) or ").append("Context.getColorStateList(int).").toString(), new RuntimeException());
        return colorstatelist;
    }

    public ColorStateList getColorStateList(int i, Theme theme)
        throws NotFoundException
    {
        TypedValue typedvalue = obtainTempTypedValue();
        ResourcesImpl resourcesimpl = mResourcesImpl;
        resourcesimpl.getValue(i, typedvalue, true);
        theme = resourcesimpl.loadColorStateList(this, typedvalue, i, theme);
        releaseTempTypedValue(typedvalue);
        return theme;
        theme;
        releaseTempTypedValue(typedvalue);
        throw theme;
    }

    public CompatibilityInfo getCompatibilityInfo()
    {
        return mResourcesImpl.getCompatibilityInfo();
    }

    public Configuration getConfiguration()
    {
        return mResourcesImpl.getConfiguration();
    }

    public float getDimension(int i)
        throws NotFoundException
    {
        TypedValue typedvalue = obtainTempTypedValue();
        float f;
        ResourcesImpl resourcesimpl = mResourcesImpl;
        resourcesimpl.getValue(i, typedvalue, true);
        if(typedvalue.type != 5)
            break MISSING_BLOCK_LABEL_46;
        f = TypedValue.complexToDimension(typedvalue.data, resourcesimpl.getDisplayMetrics());
        releaseTempTypedValue(typedvalue);
        return f;
        NotFoundException notfoundexception = JVM INSTR new #6   <Class Resources$NotFoundException>;
        StringBuilder stringbuilder = JVM INSTR new #196 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        notfoundexception.NotFoundException(stringbuilder.append("Resource ID #0x").append(Integer.toHexString(i)).append(" type #0x").append(Integer.toHexString(typedvalue.type)).append(" is not valid").toString());
        throw notfoundexception;
        Exception exception;
        exception;
        releaseTempTypedValue(typedvalue);
        throw exception;
    }

    public int getDimensionPixelOffset(int i)
        throws NotFoundException
    {
        TypedValue typedvalue = obtainTempTypedValue();
        ResourcesImpl resourcesimpl = mResourcesImpl;
        resourcesimpl.getValue(i, typedvalue, true);
        if(typedvalue.type != 5)
            break MISSING_BLOCK_LABEL_44;
        i = TypedValue.complexToDimensionPixelOffset(typedvalue.data, resourcesimpl.getDisplayMetrics());
        releaseTempTypedValue(typedvalue);
        return i;
        NotFoundException notfoundexception = JVM INSTR new #6   <Class Resources$NotFoundException>;
        StringBuilder stringbuilder = JVM INSTR new #196 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        notfoundexception.NotFoundException(stringbuilder.append("Resource ID #0x").append(Integer.toHexString(i)).append(" type #0x").append(Integer.toHexString(typedvalue.type)).append(" is not valid").toString());
        throw notfoundexception;
        Exception exception;
        exception;
        releaseTempTypedValue(typedvalue);
        throw exception;
    }

    public int getDimensionPixelSize(int i)
        throws NotFoundException
    {
        TypedValue typedvalue = obtainTempTypedValue();
        ResourcesImpl resourcesimpl = mResourcesImpl;
        resourcesimpl.getValue(i, typedvalue, true);
        if(typedvalue.type != 5)
            break MISSING_BLOCK_LABEL_44;
        i = TypedValue.complexToDimensionPixelSize(typedvalue.data, resourcesimpl.getDisplayMetrics());
        releaseTempTypedValue(typedvalue);
        return i;
        NotFoundException notfoundexception = JVM INSTR new #6   <Class Resources$NotFoundException>;
        StringBuilder stringbuilder = JVM INSTR new #196 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        notfoundexception.NotFoundException(stringbuilder.append("Resource ID #0x").append(Integer.toHexString(i)).append(" type #0x").append(Integer.toHexString(typedvalue.type)).append(" is not valid").toString());
        throw notfoundexception;
        Exception exception;
        exception;
        releaseTempTypedValue(typedvalue);
        throw exception;
    }

    public DisplayAdjustments getDisplayAdjustments()
    {
        return mResourcesImpl.getDisplayAdjustments();
    }

    public DisplayMetrics getDisplayMetrics()
    {
        return mResourcesImpl.getDisplayMetrics();
    }

    public Drawable getDrawable(int i)
        throws NotFoundException
    {
        Drawable drawable = getDrawable(i, null);
        if(drawable != null && drawable.canApplyTheme())
            Log.w("Resources", (new StringBuilder()).append("Drawable ").append(getResourceName(i)).append(" has unresolved theme ").append("attributes! Consider using Resources.getDrawable(int, Theme) or ").append("Context.getDrawable(int).").toString(), new RuntimeException());
        return drawable;
    }

    public Drawable getDrawable(int i, Theme theme)
        throws NotFoundException
    {
        return getDrawableForDensity(i, 0, theme);
    }

    public Drawable getDrawableForDensity(int i, int j)
        throws NotFoundException
    {
        return getDrawableForDensity(i, j, null);
    }

    public Drawable getDrawableForDensity(int i, int j, Theme theme)
    {
        TypedValue typedvalue = obtainTempTypedValue();
        ResourcesImpl resourcesimpl = mResourcesImpl;
        resourcesimpl.getValueForDensity(i, j, typedvalue, true);
        theme = resourcesimpl.loadDrawable(this, typedvalue, i, j, theme);
        releaseTempTypedValue(typedvalue);
        return theme;
        theme;
        releaseTempTypedValue(typedvalue);
        throw theme;
    }

    public final DrawableInflater getDrawableInflater()
    {
        if(mDrawableInflater == null)
            mDrawableInflater = new DrawableInflater(this, mClassLoader);
        return mDrawableInflater;
    }

    public float getFloat(int i)
    {
        TypedValue typedvalue = obtainTempTypedValue();
        float f;
        mResourcesImpl.getValue(i, typedvalue, true);
        if(typedvalue.type != 4)
            break MISSING_BLOCK_LABEL_35;
        f = typedvalue.getFloat();
        releaseTempTypedValue(typedvalue);
        return f;
        NotFoundException notfoundexception = JVM INSTR new #6   <Class Resources$NotFoundException>;
        StringBuilder stringbuilder = JVM INSTR new #196 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        notfoundexception.NotFoundException(stringbuilder.append("Resource ID #0x").append(Integer.toHexString(i)).append(" type #0x").append(Integer.toHexString(typedvalue.type)).append(" is not valid").toString());
        throw notfoundexception;
        Exception exception;
        exception;
        releaseTempTypedValue(typedvalue);
        throw exception;
    }

    public Typeface getFont(int i)
        throws NotFoundException
    {
        TypedValue typedvalue = obtainTempTypedValue();
        Object obj;
        obj = mResourcesImpl;
        ((ResourcesImpl) (obj)).getValue(i, typedvalue, true);
        obj = ((ResourcesImpl) (obj)).loadFont(this, typedvalue, i);
        if(obj != null)
        {
            releaseTempTypedValue(typedvalue);
            return ((Typeface) (obj));
        } else
        {
            releaseTempTypedValue(typedvalue);
            throw new NotFoundException((new StringBuilder()).append("Font resource ID #0x").append(Integer.toHexString(i)).toString());
        }
        Exception exception;
        exception;
        releaseTempTypedValue(typedvalue);
        throw exception;
    }

    Typeface getFont(TypedValue typedvalue, int i)
        throws NotFoundException
    {
        return mResourcesImpl.loadFont(this, typedvalue, i);
    }

    public float getFraction(int i, int j, int k)
    {
        TypedValue typedvalue = obtainTempTypedValue();
        float f;
        mResourcesImpl.getValue(i, typedvalue, true);
        if(typedvalue.type != 6)
            break MISSING_BLOCK_LABEL_50;
        f = TypedValue.complexToFraction(typedvalue.data, j, k);
        releaseTempTypedValue(typedvalue);
        return f;
        NotFoundException notfoundexception = JVM INSTR new #6   <Class Resources$NotFoundException>;
        StringBuilder stringbuilder = JVM INSTR new #196 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        notfoundexception.NotFoundException(stringbuilder.append("Resource ID #0x").append(Integer.toHexString(i)).append(" type #0x").append(Integer.toHexString(typedvalue.type)).append(" is not valid").toString());
        throw notfoundexception;
        Exception exception;
        exception;
        releaseTempTypedValue(typedvalue);
        throw exception;
    }

    public int getIdentifier(String s, String s1, String s2)
    {
        return mResourcesImpl.getIdentifier(s, s1, s2);
    }

    public ResourcesImpl getImpl()
    {
        return mResourcesImpl;
    }

    public int[] getIntArray(int i)
        throws NotFoundException
    {
        int ai[] = mResourcesImpl.getAssets().getArrayIntResource(i);
        if(ai != null)
            return ai;
        else
            throw new NotFoundException((new StringBuilder()).append("Int array resource ID #0x").append(Integer.toHexString(i)).toString());
    }

    public int getInteger(int i)
        throws NotFoundException
    {
        TypedValue typedvalue = obtainTempTypedValue();
        mResourcesImpl.getValue(i, typedvalue, true);
        if(typedvalue.type < 16 || typedvalue.type > 31)
            break MISSING_BLOCK_LABEL_45;
        i = typedvalue.data;
        releaseTempTypedValue(typedvalue);
        return i;
        NotFoundException notfoundexception = JVM INSTR new #6   <Class Resources$NotFoundException>;
        StringBuilder stringbuilder = JVM INSTR new #196 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        notfoundexception.NotFoundException(stringbuilder.append("Resource ID #0x").append(Integer.toHexString(i)).append(" type #0x").append(Integer.toHexString(typedvalue.type)).append(" is not valid").toString());
        throw notfoundexception;
        Exception exception;
        exception;
        releaseTempTypedValue(typedvalue);
        throw exception;
    }

    public XmlResourceParser getLayout(int i)
        throws NotFoundException
    {
        return loadXmlResourceParser(i, "layout");
    }

    public Movie getMovie(int i)
        throws NotFoundException
    {
        InputStream inputstream = openRawResource(i);
        Movie movie = Movie.decodeStream(inputstream);
        try
        {
            inputstream.close();
        }
        catch(IOException ioexception) { }
        return movie;
    }

    public LongSparseArray getPreloadedDrawables()
    {
        return mResourcesImpl.getPreloadedDrawables();
    }

    public String getQuantityString(int i, int j)
        throws NotFoundException
    {
        return getQuantityText(i, j).toString();
    }

    public transient String getQuantityString(int i, int j, Object aobj[])
        throws NotFoundException
    {
        String s = getQuantityText(i, j).toString();
        return String.format(mResourcesImpl.getConfiguration().getLocales().get(0), s, aobj);
    }

    public CharSequence getQuantityText(int i, int j)
        throws NotFoundException
    {
        return mResourcesImpl.getQuantityText(i, j);
    }

    public String getResourceEntryName(int i)
        throws NotFoundException
    {
        return mResourcesImpl.getResourceEntryName(i);
    }

    public String getResourceName(int i)
        throws NotFoundException
    {
        return mResourcesImpl.getResourceName(i);
    }

    public String getResourcePackageName(int i)
        throws NotFoundException
    {
        return mResourcesImpl.getResourcePackageName(i);
    }

    public String getResourceTypeName(int i)
        throws NotFoundException
    {
        return mResourcesImpl.getResourceTypeName(i);
    }

    public Configuration[] getSizeConfigurations()
    {
        return mResourcesImpl.getSizeConfigurations();
    }

    public ConfigurationBoundResourceCache getStateListAnimatorCache()
    {
        return mResourcesImpl.getStateListAnimatorCache();
    }

    public String getString(int i)
        throws NotFoundException
    {
        return getText(i).toString();
    }

    public transient String getString(int i, Object aobj[])
        throws NotFoundException
    {
        String s = getString(i);
        return String.format(mResourcesImpl.getConfiguration().getLocales().get(0), s, aobj);
    }

    public String[] getStringArray(int i)
        throws NotFoundException
    {
        String as[] = mResourcesImpl.getAssets().getResourceStringArray(i);
        if(as != null)
            return as;
        else
            throw new NotFoundException((new StringBuilder()).append("String array resource ID #0x").append(Integer.toHexString(i)).toString());
    }

    public CharSequence getText(int i)
        throws NotFoundException
    {
        CharSequence charsequence = mResourcesImpl.getAssets().getResourceText(i);
        if(charsequence != null)
            return charsequence;
        else
            throw new NotFoundException((new StringBuilder()).append("String resource ID #0x").append(Integer.toHexString(i)).toString());
    }

    public CharSequence getText(int i, CharSequence charsequence)
    {
        CharSequence charsequence1;
        if(i != 0)
            charsequence1 = mResourcesImpl.getAssets().getResourceText(i);
        else
            charsequence1 = null;
        if(charsequence1 == null)
            charsequence1 = charsequence;
        return charsequence1;
    }

    public CharSequence[] getTextArray(int i)
        throws NotFoundException
    {
        CharSequence acharsequence[] = mResourcesImpl.getAssets().getResourceTextArray(i);
        if(acharsequence != null)
            return acharsequence;
        else
            throw new NotFoundException((new StringBuilder()).append("Text array resource ID #0x").append(Integer.toHexString(i)).toString());
    }

    public void getValue(int i, TypedValue typedvalue, boolean flag)
        throws NotFoundException
    {
        mResourcesImpl.getValue(i, typedvalue, flag);
    }

    public void getValue(String s, TypedValue typedvalue, boolean flag)
        throws NotFoundException
    {
        mResourcesImpl.getValue(s, typedvalue, flag);
    }

    public void getValueForDensity(int i, int j, TypedValue typedvalue, boolean flag)
        throws NotFoundException
    {
        mResourcesImpl.getValueForDensity(i, j, typedvalue, flag);
    }

    public XmlResourceParser getXml(int i)
        throws NotFoundException
    {
        return loadXmlResourceParser(i, "xml");
    }

    boolean isPreloadOverlayed(int i)
    {
        return false;
    }

    boolean isPreloading()
    {
        return mResourcesImpl.isPreloading();
    }

    ColorStateList loadColorStateList(TypedValue typedvalue, int i, Theme theme)
        throws NotFoundException
    {
        return mResourcesImpl.loadColorStateList(this, typedvalue, i, theme);
    }

    public ComplexColor loadComplexColor(TypedValue typedvalue, int i, Theme theme)
    {
        return mResourcesImpl.loadComplexColor(this, typedvalue, i, theme);
    }

    Drawable loadDrawable(TypedValue typedvalue, int i, int j, Theme theme)
        throws NotFoundException
    {
        return mResourcesImpl.loadDrawable(this, typedvalue, i, j, theme);
    }

    Drawable loadOverlayDrawable(TypedValue typedvalue, int i)
    {
        return null;
    }

    TypedArray loadOverlayTypedArray(TypedArray typedarray)
    {
        return typedarray;
    }

    void loadOverlayValue(TypedValue typedvalue, int i)
    {
    }

    XmlResourceParser loadXmlResourceParser(int i, String s)
        throws NotFoundException
    {
        TypedValue typedvalue = obtainTempTypedValue();
        ResourcesImpl resourcesimpl = mResourcesImpl;
        resourcesimpl.getValue(i, typedvalue, true);
        if(typedvalue.type != 3)
            break MISSING_BLOCK_LABEL_55;
        s = resourcesimpl.loadXmlResourceParser(typedvalue.string.toString(), i, typedvalue.assetCookie, s);
        releaseTempTypedValue(typedvalue);
        return s;
        NotFoundException notfoundexception = JVM INSTR new #6   <Class Resources$NotFoundException>;
        s = JVM INSTR new #196 <Class StringBuilder>;
        s.StringBuilder();
        notfoundexception.NotFoundException(s.append("Resource ID #0x").append(Integer.toHexString(i)).append(" type #0x").append(Integer.toHexString(typedvalue.type)).append(" is not valid").toString());
        throw notfoundexception;
        s;
        releaseTempTypedValue(typedvalue);
        throw s;
    }

    XmlResourceParser loadXmlResourceParser(String s, int i, int j, String s1)
        throws NotFoundException
    {
        return mResourcesImpl.loadXmlResourceParser(s, i, j, s1);
    }

    public final Theme newTheme()
    {
        Theme theme;
        theme = new Theme(null);
        theme.setImpl(mResourcesImpl.newThemeImpl());
        ArrayList arraylist = mThemeRefs;
        arraylist;
        JVM INSTR monitorenter ;
        ArrayList arraylist1 = mThemeRefs;
        WeakReference weakreference = JVM INSTR new #517 <Class WeakReference>;
        weakreference.WeakReference(theme);
        arraylist1.add(weakreference);
        arraylist;
        JVM INSTR monitorexit ;
        return theme;
        Exception exception;
        exception;
        throw exception;
    }

    public TypedArray obtainAttributes(AttributeSet attributeset, int ai[])
    {
        TypedArray typedarray = TypedArray.obtain(this, ai.length);
        attributeset = (XmlBlock.Parser)attributeset;
        mResourcesImpl.getAssets().retrieveAttributes(((XmlBlock.Parser) (attributeset)).mParseState, ai, typedarray.mData, typedarray.mIndices);
        typedarray.mXml = attributeset;
        return loadOverlayTypedArray(typedarray);
    }

    public TypedArray obtainTypedArray(int i)
        throws NotFoundException
    {
        ResourcesImpl resourcesimpl = mResourcesImpl;
        int j = resourcesimpl.getAssets().getArraySize(i);
        if(j < 0)
        {
            throw new NotFoundException((new StringBuilder()).append("Array resource ID #0x").append(Integer.toHexString(i)).toString());
        } else
        {
            TypedArray typedarray = TypedArray.obtain(this, j);
            typedarray.mLength = resourcesimpl.getAssets().retrieveArray(i, typedarray.mData);
            typedarray.mIndices[0] = 0;
            return loadOverlayTypedArray(typedarray);
        }
    }

    public InputStream openRawResource(int i)
        throws NotFoundException
    {
        TypedValue typedvalue = obtainTempTypedValue();
        InputStream inputstream = openRawResource(i, typedvalue);
        releaseTempTypedValue(typedvalue);
        return inputstream;
        Exception exception;
        exception;
        releaseTempTypedValue(typedvalue);
        throw exception;
    }

    public InputStream openRawResource(int i, TypedValue typedvalue)
        throws NotFoundException
    {
        return mResourcesImpl.openRawResource(i, typedvalue);
    }

    public AssetFileDescriptor openRawResourceFd(int i)
        throws NotFoundException
    {
        TypedValue typedvalue = obtainTempTypedValue();
        AssetFileDescriptor assetfiledescriptor = mResourcesImpl.openRawResourceFd(i, typedvalue);
        releaseTempTypedValue(typedvalue);
        return assetfiledescriptor;
        Exception exception;
        exception;
        releaseTempTypedValue(typedvalue);
        throw exception;
    }

    public void parseBundleExtra(String s, AttributeSet attributeset, Bundle bundle)
        throws XmlPullParserException
    {
        boolean flag = true;
        TypedArray typedarray = obtainAttributes(attributeset, com.android.internal.R.styleable.Extra);
        String s1 = typedarray.getString(0);
        if(s1 == null)
        {
            typedarray.recycle();
            throw new XmlPullParserException((new StringBuilder()).append("<").append(s).append("> requires an android:name attribute at ").append(attributeset.getPositionDescription()).toString());
        }
        TypedValue typedvalue = typedarray.peekValue(1);
        if(typedvalue != null)
        {
            if(typedvalue.type == 3)
                bundle.putCharSequence(s1, typedvalue.coerceToString());
            else
            if(typedvalue.type == 18)
            {
                if(typedvalue.data == 0)
                    flag = false;
                bundle.putBoolean(s1, flag);
            } else
            if(typedvalue.type >= 16 && typedvalue.type <= 31)
                bundle.putInt(s1, typedvalue.data);
            else
            if(typedvalue.type == 4)
            {
                bundle.putFloat(s1, typedvalue.getFloat());
            } else
            {
                typedarray.recycle();
                throw new XmlPullParserException((new StringBuilder()).append("<").append(s).append("> only supports string, integer, float, color, and boolean at ").append(attributeset.getPositionDescription()).toString());
            }
            typedarray.recycle();
            return;
        } else
        {
            typedarray.recycle();
            throw new XmlPullParserException((new StringBuilder()).append("<").append(s).append("> requires an android:value or android:resource attribute at ").append(attributeset.getPositionDescription()).toString());
        }
    }

    public void parseBundleExtras(XmlResourceParser xmlresourceparser, Bundle bundle)
        throws XmlPullParserException, IOException
    {
        int i = xmlresourceparser.getDepth();
        do
        {
            int j = xmlresourceparser.next();
            if(j == 1 || j == 3 && xmlresourceparser.getDepth() <= i)
                break;
            if(j != 3 && j != 4)
                if(xmlresourceparser.getName().equals("extra"))
                {
                    parseBundleExtra("extra", xmlresourceparser, bundle);
                    XmlUtils.skipCurrentTag(xmlresourceparser);
                } else
                {
                    XmlUtils.skipCurrentTag(xmlresourceparser);
                }
        } while(true);
    }

    public void preloadFonts(int i)
    {
        TypedArray typedarray = obtainTypedArray(i);
        int j = typedarray.length();
        i = 0;
_L2:
        if(i >= j)
            break; /* Loop/switch isn't completed */
        typedarray.getFont(i);
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        typedarray.recycle();
        return;
        Exception exception;
        exception;
        typedarray.recycle();
        throw exception;
    }

    public void setCompatibilityInfo(CompatibilityInfo compatibilityinfo)
    {
        if(compatibilityinfo != null)
            mResourcesImpl.updateConfiguration(null, null, compatibilityinfo);
    }

    public void setImpl(ResourcesImpl resourcesimpl)
    {
        if(resourcesimpl == mResourcesImpl)
            return;
        mResourcesImpl = resourcesimpl;
        ArrayList arraylist = mThemeRefs;
        arraylist;
        JVM INSTR monitorenter ;
        int i = mThemeRefs.size();
        int j = 0;
_L7:
        if(j >= i) goto _L2; else goto _L1
_L1:
        resourcesimpl = (WeakReference)mThemeRefs.get(j);
        if(resourcesimpl == null) goto _L4; else goto _L3
_L3:
        resourcesimpl = (Theme)resourcesimpl.get();
_L5:
        if(resourcesimpl == null)
            break MISSING_BLOCK_LABEL_82;
        resourcesimpl.setImpl(mResourcesImpl.newThemeImpl(resourcesimpl.getKey()));
        j++;
        continue; /* Loop/switch isn't completed */
_L4:
        resourcesimpl = null;
        if(true) goto _L5; else goto _L2
_L2:
        arraylist;
        JVM INSTR monitorexit ;
        return;
        resourcesimpl;
        throw resourcesimpl;
        if(true) goto _L7; else goto _L6
_L6:
    }

    public final void startPreloading()
    {
        mResourcesImpl.startPreloading();
    }

    public void updateConfiguration(Configuration configuration, DisplayMetrics displaymetrics)
    {
        updateConfiguration(configuration, displaymetrics, null);
    }

    public void updateConfiguration(Configuration configuration, DisplayMetrics displaymetrics, CompatibilityInfo compatibilityinfo)
    {
        mResourcesImpl.updateConfiguration(configuration, displaymetrics, compatibilityinfo);
    }

    static final String TAG = "Resources";
    static Resources mSystem = null;
    private static final Object sSync = new Object();
    final ClassLoader mClassLoader;
    private DrawableInflater mDrawableInflater;
    private ResourcesImpl mResourcesImpl;
    private final ArrayList mThemeRefs;
    private TypedValue mTmpValue;
    private final Object mTmpValueLock;
    final android.util.Pools.SynchronizedPool mTypedArrayPool;

}
