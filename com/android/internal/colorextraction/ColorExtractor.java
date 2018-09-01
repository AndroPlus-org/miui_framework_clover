// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.colorextraction;

import android.app.WallpaperColors;
import android.app.WallpaperManager;
import android.content.Context;
import android.os.Trace;
import android.util.Log;
import android.util.SparseArray;
import com.android.internal.colorextraction.types.ExtractionType;
import com.android.internal.colorextraction.types.Tonal;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class ColorExtractor
    implements android.app.WallpaperManager.OnColorsChangedListener
{
    public static class GradientColors
    {

        public boolean equals(Object obj)
        {
            boolean flag = false;
            if(obj == null || obj.getClass() != getClass())
                return false;
            obj = (GradientColors)obj;
            boolean flag1 = flag;
            if(((GradientColors) (obj)).mMainColor == mMainColor)
            {
                flag1 = flag;
                if(((GradientColors) (obj)).mSecondaryColor == mSecondaryColor)
                {
                    flag1 = flag;
                    if(((GradientColors) (obj)).mSupportsDarkText == mSupportsDarkText)
                        flag1 = true;
                }
            }
            return flag1;
        }

        public int getMainColor()
        {
            return mMainColor;
        }

        public int getSecondaryColor()
        {
            return mSecondaryColor;
        }

        public int hashCode()
        {
            int i = mMainColor;
            int j = mSecondaryColor;
            int k;
            if(mSupportsDarkText)
                k = 0;
            else
                k = 1;
            return (i * 31 + j) * 31 + k;
        }

        public void set(GradientColors gradientcolors)
        {
            mMainColor = gradientcolors.mMainColor;
            mSecondaryColor = gradientcolors.mSecondaryColor;
            mSupportsDarkText = gradientcolors.mSupportsDarkText;
        }

        public void setMainColor(int i)
        {
            mMainColor = i;
        }

        public void setSecondaryColor(int i)
        {
            mSecondaryColor = i;
        }

        public void setSupportsDarkText(boolean flag)
        {
            mSupportsDarkText = flag;
        }

        public boolean supportsDarkText()
        {
            return mSupportsDarkText;
        }

        public String toString()
        {
            return (new StringBuilder()).append("GradientColors(").append(Integer.toHexString(mMainColor)).append(", ").append(Integer.toHexString(mSecondaryColor)).append(")").toString();
        }

        private int mMainColor;
        private int mSecondaryColor;
        private boolean mSupportsDarkText;

        public GradientColors()
        {
        }
    }

    public static interface OnColorsChangedListener
    {

        public abstract void onColorsChanged(ColorExtractor colorextractor, int i);
    }


    public ColorExtractor(Context context)
    {
        this(context, ((ExtractionType) (new Tonal(context))));
    }

    public ColorExtractor(Context context, ExtractionType extractiontype)
    {
        mContext = context;
        mExtractionType = extractiontype;
        mGradientColors = new SparseArray();
        context = new int[2];
        context[0] = 2;
        context[1] = 1;
        int i = context.length;
        for(int j = 0; j < i; j++)
        {
            int k = context[j];
            GradientColors agradientcolors[] = new GradientColors[sGradientTypes.length];
            mGradientColors.append(k, agradientcolors);
            extractiontype = sGradientTypes;
            k = 0;
            for(int l = extractiontype.length; k < l; k++)
                agradientcolors[extractiontype[k]] = new GradientColors();

        }

        mOnColorsChangedListeners = new ArrayList();
        extractiontype = (GradientColors[])mGradientColors.get(1);
        context = (GradientColors[])mGradientColors.get(2);
        WallpaperManager wallpapermanager = (WallpaperManager)mContext.getSystemService(android/app/WallpaperManager);
        if(wallpapermanager == null)
        {
            Log.w("ColorExtractor", "Can't listen to color changes!");
        } else
        {
            wallpapermanager.addOnColorsChangedListener(this, null);
            Trace.beginSection("ColorExtractor#getWallpaperColors");
            mSystemColors = wallpapermanager.getWallpaperColors(1);
            mLockColors = wallpapermanager.getWallpaperColors(2);
            Trace.endSection();
        }
        extractInto(mSystemColors, extractiontype[0], extractiontype[1], extractiontype[2]);
        extractInto(mLockColors, context[0], context[1], context[2]);
    }

    private void extractInto(WallpaperColors wallpapercolors, GradientColors gradientcolors, GradientColors gradientcolors1, GradientColors gradientcolors2)
    {
        mExtractionType.extractInto(wallpapercolors, gradientcolors, gradientcolors1, gradientcolors2);
    }

    public void addOnColorsChangedListener(OnColorsChangedListener oncolorschangedlistener)
    {
        mOnColorsChangedListeners.add(new WeakReference(oncolorschangedlistener));
    }

    public void destroy()
    {
        WallpaperManager wallpapermanager = (WallpaperManager)mContext.getSystemService(android/app/WallpaperManager);
        if(wallpapermanager != null)
            wallpapermanager.removeOnColorsChangedListener(this);
    }

    public GradientColors getColors(int i)
    {
        return getColors(i, 1);
    }

    public GradientColors getColors(int i, int j)
    {
        if(j != 0 && j != 1 && j != 2)
            throw new IllegalArgumentException("type should be TYPE_NORMAL, TYPE_DARK or TYPE_EXTRA_DARK");
        if(i != 2 && i != 1)
            throw new IllegalArgumentException("which should be FLAG_SYSTEM or FLAG_NORMAL");
        else
            return ((GradientColors[])mGradientColors.get(i))[j];
    }

    public WallpaperColors getWallpaperColors(int i)
    {
        if(i == 2)
            return mLockColors;
        if(i == 1)
            return mSystemColors;
        else
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid value for which: ").append(i).toString());
    }

    public void onColorsChanged(WallpaperColors wallpapercolors, int i)
    {
        boolean flag = false;
        if((i & 2) != 0)
        {
            mLockColors = wallpapercolors;
            GradientColors agradientcolors[] = (GradientColors[])mGradientColors.get(2);
            extractInto(wallpapercolors, agradientcolors[0], agradientcolors[1], agradientcolors[2]);
            flag = true;
        }
        if((i & 1) != 0)
        {
            mSystemColors = wallpapercolors;
            GradientColors agradientcolors1[] = (GradientColors[])mGradientColors.get(1);
            extractInto(wallpapercolors, agradientcolors1[0], agradientcolors1[1], agradientcolors1[2]);
            flag = true;
        }
        if(flag)
            triggerColorsChanged(i);
    }

    public void removeOnColorsChangedListener(OnColorsChangedListener oncolorschangedlistener)
    {
        ArrayList arraylist = new ArrayList(mOnColorsChangedListeners);
        int i = arraylist.size();
        int j = 0;
        do
        {
label0:
            {
                if(j < i)
                {
                    WeakReference weakreference = (WeakReference)arraylist.get(j);
                    if(weakreference.get() != oncolorschangedlistener)
                        break label0;
                    mOnColorsChangedListeners.remove(weakreference);
                }
                return;
            }
            j++;
        } while(true);
    }

    protected void triggerColorsChanged(int i)
    {
        ArrayList arraylist = new ArrayList(mOnColorsChangedListeners);
        int j = arraylist.size();
        int k = 0;
        while(k < j) 
        {
            WeakReference weakreference = (WeakReference)arraylist.get(k);
            OnColorsChangedListener oncolorschangedlistener = (OnColorsChangedListener)weakreference.get();
            if(oncolorschangedlistener == null)
                mOnColorsChangedListeners.remove(weakreference);
            else
                oncolorschangedlistener.onColorsChanged(this, i);
            k++;
        }
    }

    private static final boolean DEBUG = false;
    private static final String TAG = "ColorExtractor";
    public static final int TYPE_DARK = 1;
    public static final int TYPE_EXTRA_DARK = 2;
    public static final int TYPE_NORMAL = 0;
    private static final int sGradientTypes[] = {
        0, 1, 2
    };
    private final Context mContext;
    private final ExtractionType mExtractionType;
    protected final SparseArray mGradientColors;
    protected WallpaperColors mLockColors;
    private final ArrayList mOnColorsChangedListeners;
    protected WallpaperColors mSystemColors;

}
