// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.res;

import android.graphics.drawable.Drawable;
import android.util.*;
import java.io.InputStream;

// Referenced classes of package android.content.res:
//            Resources, MiuiResourcesImpl, AssetManager, Configuration, 
//            TypedArray, ResourcesImpl

public final class MiuiResources extends Resources
{
    public static class ThemeFileInfoOption
    {

        public int inCookie;
        public int inDensity;
        public boolean inRequestStream;
        public String inResourcePath;
        public int outDensity;
        public String outFilterPath;
        public InputStream outInputStream;
        public long outSize;

        public ThemeFileInfoOption(int i, String s, boolean flag)
        {
            inCookie = i;
            inResourcePath = s;
            inRequestStream = flag;
        }

        public ThemeFileInfoOption(TypedValue typedvalue, boolean flag)
        {
            inCookie = typedvalue.assetCookie;
            inDensity = typedvalue.density;
            inResourcePath = typedvalue.string.toString();
            inRequestStream = flag;
        }

        public ThemeFileInfoOption(boolean flag)
        {
            inRequestStream = flag;
        }
    }


    MiuiResources()
    {
        updateMiuiImpl();
    }

    public MiuiResources(AssetManager assetmanager, DisplayMetrics displaymetrics, Configuration configuration)
    {
        super(assetmanager, displaymetrics, configuration);
        updateMiuiImpl();
    }

    public MiuiResources(ClassLoader classloader)
    {
        super(classloader);
        updateMiuiImpl();
    }

    public static boolean isPreloadedCacheEmpty()
    {
        boolean flag = false;
        if(getSystem().getPreloadedDrawables().size() == 0)
            flag = true;
        return flag;
    }

    private void updateMiuiImpl()
    {
        ResourcesImpl resourcesimpl = getImpl();
        if(resourcesimpl != null && (resourcesimpl instanceof MiuiResourcesImpl))
            mMiuiImpl = (MiuiResourcesImpl)resourcesimpl;
        else
            mMiuiImpl = null;
    }

    public int[] getIntArray(int i)
        throws Resources.NotFoundException
    {
        if(mMiuiImpl != null)
        {
            int ai[] = mMiuiImpl.getIntArray(i);
            if(ai != null)
                return ai;
        }
        return super.getIntArray(i);
    }

    public String[] getStringArray(int i)
        throws Resources.NotFoundException
    {
        if(mMiuiImpl != null)
        {
            String as[] = mMiuiImpl.getStringArray(i);
            if(as != null)
                return as;
        }
        return super.getStringArray(i);
    }

    public CharSequence getText(int i)
        throws Resources.NotFoundException
    {
        if(mMiuiImpl != null)
        {
            CharSequence charsequence = mMiuiImpl.getText(i);
            if(charsequence != null)
                return charsequence;
        }
        return super.getText(i);
    }

    public CharSequence getText(int i, CharSequence charsequence)
    {
        if(mMiuiImpl != null)
        {
            CharSequence charsequence1 = mMiuiImpl.getText(i, charsequence);
            if(charsequence1 != null)
                return charsequence1;
        }
        return super.getText(i, charsequence);
    }

    public CharSequence[] getTextArray(int i)
        throws Resources.NotFoundException
    {
        if(mMiuiImpl != null)
        {
            CharSequence acharsequence[] = mMiuiImpl.getTextArray(i);
            if(acharsequence != null)
                return acharsequence;
        }
        return super.getTextArray(i);
    }

    CharSequence getThemeString(int i)
    {
        if(mMiuiImpl != null)
            return mMiuiImpl.getThemeString(i);
        else
            return null;
    }

    public void init(String s)
    {
        if(mMiuiImpl != null)
            mMiuiImpl.init(this, s);
    }

    boolean isPreloadOverlayed(int i)
    {
        if(mMiuiImpl != null)
        {
            Boolean boolean1 = mMiuiImpl.isPreloadOverlayed(i);
            if(boolean1 != null)
                return boolean1.booleanValue();
        }
        return super.isPreloadOverlayed(i);
    }

    Drawable loadOverlayDrawable(TypedValue typedvalue, int i)
    {
        if(mMiuiImpl != null)
        {
            Drawable drawable = mMiuiImpl.loadOverlayDrawable(this, typedvalue, i);
            if(drawable != null)
                return drawable;
        }
        return super.loadOverlayDrawable(typedvalue, i);
    }

    TypedArray loadOverlayTypedArray(TypedArray typedarray)
    {
        if(mMiuiImpl != null)
        {
            TypedArray typedarray1 = mMiuiImpl.loadOverlayTypedArray(typedarray);
            if(typedarray1 != null)
                return typedarray1;
        }
        return super.loadOverlayTypedArray(typedarray);
    }

    void loadOverlayValue(TypedValue typedvalue, int i)
    {
        if(mMiuiImpl != null)
            mMiuiImpl.loadOverlayValue(typedvalue, i);
    }

    public void setImpl(ResourcesImpl resourcesimpl)
    {
        super.setImpl(resourcesimpl);
        updateMiuiImpl();
    }

    private MiuiResourcesImpl mMiuiImpl;
}
