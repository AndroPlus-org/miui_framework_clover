// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.res;

import android.content.pm.ApplicationInfo;
import android.graphics.*;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;

// Referenced classes of package android.content.res:
//            Configuration

public class CompatibilityInfo
    implements Parcelable
{
    public class Translator
    {

        public Rect getTranslatedContentInsets(Rect rect)
        {
            if(mContentInsetsBuffer == null)
                mContentInsetsBuffer = new Rect();
            mContentInsetsBuffer.set(rect);
            translateRectInAppWindowToScreen(mContentInsetsBuffer);
            return mContentInsetsBuffer;
        }

        public Region getTranslatedTouchableArea(Region region)
        {
            if(mTouchableAreaBuffer == null)
                mTouchableAreaBuffer = new Region();
            mTouchableAreaBuffer.set(region);
            mTouchableAreaBuffer.scale(applicationScale);
            return mTouchableAreaBuffer;
        }

        public Rect getTranslatedVisibleInsets(Rect rect)
        {
            if(mVisibleInsetsBuffer == null)
                mVisibleInsetsBuffer = new Rect();
            mVisibleInsetsBuffer.set(rect);
            translateRectInAppWindowToScreen(mVisibleInsetsBuffer);
            return mVisibleInsetsBuffer;
        }

        public void translateCanvas(Canvas canvas)
        {
            if(applicationScale == 1.5F)
                canvas.translate(0.002614379F, 0.002614379F);
            canvas.scale(applicationScale, applicationScale);
        }

        public void translateEventInScreenToAppWindow(MotionEvent motionevent)
        {
            motionevent.scale(applicationInvertedScale);
        }

        public void translateLayoutParamsInAppWindowToScreen(android.view.WindowManager.LayoutParams layoutparams)
        {
            layoutparams.scale(applicationScale);
        }

        public void translatePointInScreenToAppWindow(PointF pointf)
        {
            float f = applicationInvertedScale;
            if(f != 1.0F)
            {
                pointf.x = pointf.x * f;
                pointf.y = pointf.y * f;
            }
        }

        public void translateRectInAppWindowToScreen(Rect rect)
        {
            rect.scale(applicationScale);
        }

        public void translateRectInScreenToAppWinFrame(Rect rect)
        {
            rect.scale(applicationInvertedScale);
        }

        public void translateRectInScreenToAppWindow(Rect rect)
        {
            rect.scale(applicationInvertedScale);
        }

        public void translateRegionInWindowToScreen(Region region)
        {
            region.scale(applicationScale);
        }

        public void translateWindowLayout(android.view.WindowManager.LayoutParams layoutparams)
        {
            layoutparams.scale(applicationScale);
        }

        public final float applicationInvertedScale;
        public final float applicationScale;
        private Rect mContentInsetsBuffer;
        private Region mTouchableAreaBuffer;
        private Rect mVisibleInsetsBuffer;
        final CompatibilityInfo this$0;

        Translator()
        {
            this(CompatibilityInfo.this.applicationScale, CompatibilityInfo.this.applicationInvertedScale);
        }

        Translator(float f, float f1)
        {
            this$0 = CompatibilityInfo.this;
            super();
            mContentInsetsBuffer = null;
            mVisibleInsetsBuffer = null;
            mTouchableAreaBuffer = null;
            applicationScale = f;
            applicationInvertedScale = f1;
        }
    }


    private CompatibilityInfo()
    {
        this(4, DisplayMetrics.DENSITY_DEVICE, 1.0F, 1.0F);
    }

    private CompatibilityInfo(int i, int j, float f, float f1)
    {
        mCompatibilityFlags = i;
        applicationDensity = j;
        applicationScale = f;
        applicationInvertedScale = f1;
    }

    public CompatibilityInfo(ApplicationInfo applicationinfo, int i, int j, boolean flag)
    {
        int k;
        k = 0;
        if(applicationinfo.targetSdkVersion < 26)
            k = 16;
          goto _L1
_L3:
        int l;
        int i1;
        boolean flag1;
        if(applicationinfo.requiresSmallestWidthDp != 0)
            l = applicationinfo.requiresSmallestWidthDp;
        else
            l = applicationinfo.compatibleWidthLimitDp;
        i = l;
        if(l == 0)
            i = applicationinfo.largestWidthLimitDp;
        if(applicationinfo.compatibleWidthLimitDp != 0)
            l = applicationinfo.compatibleWidthLimitDp;
        else
            l = i;
        i1 = l;
        if(l < i)
            i1 = i;
        l = applicationinfo.largestWidthLimitDp;
        if(i > 320)
            i = k | 4;
        else
        if(l != 0 && j > l)
            i = k | 0xa;
        else
        if(i1 >= j)
        {
            i = k | 4;
        } else
        {
            i = k;
            if(flag)
                i = k | 8;
        }
        j = applicationinfo.getOverrideDensity();
        if(j != 0)
        {
            applicationDensity = j;
            applicationScale = (float)DisplayMetrics.DENSITY_DEVICE / (float)applicationDensity;
            applicationInvertedScale = 1.0F / applicationScale;
            i |= 1;
        } else
        {
            applicationDensity = DisplayMetrics.DENSITY_DEVICE;
            applicationScale = 1.0F;
            applicationInvertedScale = 1.0F;
        }
_L7:
        mCompatibilityFlags = i;
        Log.d("CompatibilityInfo", (new StringBuilder()).append("mCompatibilityFlags - ").append(Integer.toHexString(mCompatibilityFlags)).toString());
        Log.d("CompatibilityInfo", (new StringBuilder()).append("applicationDensity - ").append(applicationDensity).toString());
        Log.d("CompatibilityInfo", (new StringBuilder()).append("applicationScale - ").append(applicationScale).toString());
        return;
_L1:
        if(applicationinfo.requiresSmallestWidthDp != 0 || applicationinfo.compatibleWidthLimitDp != 0 || applicationinfo.largestWidthLimitDp != 0) goto _L3; else goto _L2
_L2:
        j = 0;
        l = 0;
        if((applicationinfo.flags & 0x800) != 0)
        {
            j = 8;
            i1 = 1;
            l = i1;
            if(!flag)
            {
                j = 8 | 0x22;
                l = i1;
            }
        }
        i1 = l;
        l = j;
        if((applicationinfo.flags & 0x80000) != 0)
        {
            flag1 = true;
            i1 = ((flag1) ? 1 : 0);
            l = j;
            if(!flag)
            {
                l = j | 0x22;
                i1 = ((flag1) ? 1 : 0);
            }
        }
        j = l;
        if((applicationinfo.flags & 0x1000) != 0)
        {
            i1 = 1;
            j = l | 2;
        }
        l = j;
        if(flag)
            l = j & -3;
        j = k | 8;
        i & 0xf;
        JVM INSTR tableswitch 3 4: default 396
    //                   3 590
    //                   4 552;
           goto _L4 _L5 _L6
_L4:
        break; /* Loop/switch isn't completed */
_L5:
        break MISSING_BLOCK_LABEL_590;
_L8:
        if((0x10000000 & i) != 0)
        {
            if((l & 2) != 0)
            {
                i = j & -9;
            } else
            {
                i = j;
                if(i1 == 0)
                    i = j | 2;
            }
        } else
        {
            i = j & -9 | 4;
        }
        j = applicationinfo.getOverrideDensity();
        if((applicationinfo.flags & 0x2000) == 0)
        {
            applicationDensity = 160;
            applicationScale = (float)DisplayMetrics.DENSITY_DEVICE / 160F;
            applicationInvertedScale = 1.0F / applicationScale;
            i |= 1;
        } else
        if(j != 0)
        {
            applicationDensity = j;
            applicationScale = (float)DisplayMetrics.DENSITY_DEVICE / (float)applicationDensity;
            applicationInvertedScale = 1.0F / applicationScale;
            i |= 1;
        } else
        {
            applicationDensity = DisplayMetrics.DENSITY_DEVICE;
            applicationScale = 1.0F;
            applicationInvertedScale = 1.0F;
        }
          goto _L7
_L6:
        k = j;
        if((l & 0x20) != 0)
            k = j & -9;
        j = k;
        if((applicationinfo.flags & 0x80000) != 0)
            j = k | 4;
          goto _L8
        k = j;
        if((l & 8) != 0)
            k = j & -9;
        j = k;
        if((applicationinfo.flags & 0x800) != 0)
            j = k | 4;
          goto _L8
    }

    CompatibilityInfo(CompatibilityInfo compatibilityinfo)
    {
        this();
    }

    private CompatibilityInfo(Parcel parcel)
    {
        mCompatibilityFlags = parcel.readInt();
        applicationDensity = parcel.readInt();
        applicationScale = parcel.readFloat();
        applicationInvertedScale = parcel.readFloat();
    }

    CompatibilityInfo(Parcel parcel, CompatibilityInfo compatibilityinfo)
    {
        this(parcel);
    }

    public static float computeCompatibleScaling(DisplayMetrics displaymetrics, DisplayMetrics displaymetrics1)
    {
        int i = displaymetrics.noncompatWidthPixels;
        int j = displaymetrics.noncompatHeightPixels;
        int k;
        int l;
        int i1;
        float f;
        float f1;
        if(i < j)
        {
            k = i;
            l = j;
        } else
        {
            k = j;
            l = i;
        }
        i1 = (int)(displaymetrics.density * 320F + 0.5F);
        f = (float)l / (float)k;
        f1 = f;
        if(f > 1.779167F)
            f1 = 1.779167F;
        k = (int)((float)i1 * f1 + 0.5F);
        if(i < j)
        {
            l = k;
            k = i1;
            i1 = l;
        }
        f = (float)i / (float)k;
        f1 = (float)j / (float)i1;
        if(f < f1)
            f1 = f;
        f = f1;
        if(f1 < 1.0F)
            f = 1.0F;
        if(displaymetrics1 != null)
        {
            displaymetrics1.widthPixels = k;
            displaymetrics1.heightPixels = i1;
        }
        return f;
    }

    public boolean alwaysSupportsScreen()
    {
        boolean flag = false;
        if((mCompatibilityFlags & 4) != 0)
            flag = true;
        return flag;
    }

    public void applyToConfiguration(int i, Configuration configuration)
    {
        if(!supportsScreen())
        {
            configuration.screenLayout = configuration.screenLayout & 0xfffffff0 | 2;
            configuration.screenWidthDp = configuration.compatScreenWidthDp;
            configuration.screenHeightDp = configuration.compatScreenHeightDp;
            configuration.smallestScreenWidthDp = configuration.compatSmallestScreenWidthDp;
        }
        configuration.densityDpi = i;
        if(isScalingRequired())
        {
            float f = applicationInvertedScale;
            configuration.densityDpi = (int)((float)configuration.densityDpi * f + 0.5F);
        }
    }

    public void applyToDisplayMetrics(DisplayMetrics displaymetrics)
    {
        if(!supportsScreen())
        {
            computeCompatibleScaling(displaymetrics, displaymetrics);
        } else
        {
            displaymetrics.widthPixels = displaymetrics.noncompatWidthPixels;
            displaymetrics.heightPixels = displaymetrics.noncompatHeightPixels;
        }
        if(isScalingRequired())
        {
            float f = applicationInvertedScale;
            displaymetrics.density = displaymetrics.noncompatDensity * f;
            displaymetrics.densityDpi = (int)((float)displaymetrics.noncompatDensityDpi * f + 0.5F);
            displaymetrics.scaledDensity = displaymetrics.noncompatScaledDensity * f;
            displaymetrics.xdpi = displaymetrics.noncompatXdpi * f;
            displaymetrics.ydpi = displaymetrics.noncompatYdpi * f;
            displaymetrics.widthPixels = (int)((float)displaymetrics.widthPixels * f + 0.5F);
            displaymetrics.heightPixels = (int)((float)displaymetrics.heightPixels * f + 0.5F);
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        obj = (CompatibilityInfo)obj;
        if(mCompatibilityFlags != ((CompatibilityInfo) (obj)).mCompatibilityFlags)
            return false;
        if(applicationDensity != ((CompatibilityInfo) (obj)).applicationDensity)
            return false;
        if(applicationScale != ((CompatibilityInfo) (obj)).applicationScale)
            return false;
        float f;
        float f1;
        try
        {
            f = applicationInvertedScale;
            f1 = ((CompatibilityInfo) (obj)).applicationInvertedScale;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            return false;
        }
        return f == f1;
    }

    public Translator getTranslator()
    {
        Translator translator;
        if(isScalingRequired())
            translator = new Translator();
        else
            translator = null;
        return translator;
    }

    public int hashCode()
    {
        return (((mCompatibilityFlags + 527) * 31 + applicationDensity) * 31 + Float.floatToIntBits(applicationScale)) * 31 + Float.floatToIntBits(applicationInvertedScale);
    }

    public boolean isScalingRequired()
    {
        boolean flag = false;
        if((mCompatibilityFlags & 1) != 0)
            flag = true;
        return flag;
    }

    public boolean needsCompatResources()
    {
        boolean flag = false;
        if((mCompatibilityFlags & 0x10) != 0)
            flag = true;
        return flag;
    }

    public boolean neverSupportsScreen()
    {
        boolean flag = false;
        if((mCompatibilityFlags & 2) != 0)
            flag = true;
        return flag;
    }

    public boolean supportsScreen()
    {
        boolean flag = false;
        if((mCompatibilityFlags & 8) == 0)
            flag = true;
        return flag;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder(128);
        stringbuilder.append("{");
        stringbuilder.append(applicationDensity);
        stringbuilder.append("dpi");
        if(isScalingRequired())
        {
            stringbuilder.append(" ");
            stringbuilder.append(applicationScale);
            stringbuilder.append("x");
        }
        if(!supportsScreen())
            stringbuilder.append(" resizing");
        if(neverSupportsScreen())
            stringbuilder.append(" never-compat");
        if(alwaysSupportsScreen())
            stringbuilder.append(" always-compat");
        stringbuilder.append("}");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mCompatibilityFlags);
        parcel.writeInt(applicationDensity);
        parcel.writeFloat(applicationScale);
        parcel.writeFloat(applicationInvertedScale);
    }

    private static final int ALWAYS_NEEDS_COMPAT = 2;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public CompatibilityInfo createFromParcel(Parcel parcel)
        {
            return new CompatibilityInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public CompatibilityInfo[] newArray(int i)
        {
            return new CompatibilityInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final CompatibilityInfo DEFAULT_COMPATIBILITY_INFO = new CompatibilityInfo() {

    }
;
    public static final int DEFAULT_NORMAL_SHORT_DIMENSION = 320;
    public static final float MAXIMUM_ASPECT_RATIO = 1.779167F;
    private static final int NEEDS_COMPAT_RES = 16;
    private static final int NEEDS_SCREEN_COMPAT = 8;
    private static final int NEVER_NEEDS_COMPAT = 4;
    private static final int SCALING_REQUIRED = 1;
    static final String TAG = "CompatibilityInfo";
    public final int applicationDensity;
    public final float applicationInvertedScale;
    public final float applicationScale;
    private final int mCompatibilityFlags;

}
