// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.content.res.CompatibilityInfo;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.display.DisplayManagerGlobal;
import android.os.*;
import android.util.DisplayMetrics;
import java.util.Arrays;

// Referenced classes of package android.view:
//            DisplayAdjustments, DisplayInfo

public final class Display
{
    public static final class HdrCapabilities
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public boolean equals(Object obj)
        {
            boolean flag = true;
            if(this == obj)
                return true;
            if(!(obj instanceof HdrCapabilities))
                return false;
            obj = (HdrCapabilities)obj;
            if(Arrays.equals(mSupportedHdrTypes, ((HdrCapabilities) (obj)).mSupportedHdrTypes) && mMaxLuminance == ((HdrCapabilities) (obj)).mMaxLuminance && mMaxAverageLuminance == ((HdrCapabilities) (obj)).mMaxAverageLuminance)
            {
                if(mMinLuminance != ((HdrCapabilities) (obj)).mMinLuminance)
                    flag = false;
            } else
            {
                flag = false;
            }
            return flag;
        }

        public float getDesiredMaxAverageLuminance()
        {
            return mMaxAverageLuminance;
        }

        public float getDesiredMaxLuminance()
        {
            return mMaxLuminance;
        }

        public float getDesiredMinLuminance()
        {
            return mMinLuminance;
        }

        public int[] getSupportedHdrTypes()
        {
            return mSupportedHdrTypes;
        }

        public int hashCode()
        {
            return (((Arrays.hashCode(mSupportedHdrTypes) + 391) * 17 + Float.floatToIntBits(mMaxLuminance)) * 17 + Float.floatToIntBits(mMaxAverageLuminance)) * 17 + Float.floatToIntBits(mMinLuminance);
        }

        public void readFromParcel(Parcel parcel)
        {
            int i = parcel.readInt();
            mSupportedHdrTypes = new int[i];
            for(int j = 0; j < i; j++)
                mSupportedHdrTypes[j] = parcel.readInt();

            mMaxLuminance = parcel.readFloat();
            mMaxAverageLuminance = parcel.readFloat();
            mMinLuminance = parcel.readFloat();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(mSupportedHdrTypes.length);
            for(i = 0; i < mSupportedHdrTypes.length; i++)
                parcel.writeInt(mSupportedHdrTypes[i]);

            parcel.writeFloat(mMaxLuminance);
            parcel.writeFloat(mMaxAverageLuminance);
            parcel.writeFloat(mMinLuminance);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public HdrCapabilities createFromParcel(Parcel parcel)
            {
                return new HdrCapabilities(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public HdrCapabilities[] newArray(int i)
            {
                return new HdrCapabilities[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public static final int HDR_TYPE_DOLBY_VISION = 1;
        public static final int HDR_TYPE_HDR10 = 2;
        public static final int HDR_TYPE_HLG = 3;
        public static final float INVALID_LUMINANCE = -1F;
        private float mMaxAverageLuminance;
        private float mMaxLuminance;
        private float mMinLuminance;
        private int mSupportedHdrTypes[];


        public HdrCapabilities()
        {
            mSupportedHdrTypes = new int[0];
            mMaxLuminance = -1F;
            mMaxAverageLuminance = -1F;
            mMinLuminance = -1F;
        }

        private HdrCapabilities(Parcel parcel)
        {
            mSupportedHdrTypes = new int[0];
            mMaxLuminance = -1F;
            mMaxAverageLuminance = -1F;
            mMinLuminance = -1F;
            readFromParcel(parcel);
        }

        HdrCapabilities(Parcel parcel, HdrCapabilities hdrcapabilities)
        {
            this(parcel);
        }

        public HdrCapabilities(int ai[], float f, float f1, float f2)
        {
            mSupportedHdrTypes = new int[0];
            mMaxLuminance = -1F;
            mMaxAverageLuminance = -1F;
            mMinLuminance = -1F;
            mSupportedHdrTypes = ai;
            mMaxLuminance = f;
            mMaxAverageLuminance = f1;
            mMinLuminance = f2;
        }
    }

    public static final class Mode
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public boolean equals(Object obj)
        {
            boolean flag = false;
            if(this == obj)
                return true;
            if(!(obj instanceof Mode))
                return false;
            obj = (Mode)obj;
            if(mModeId == ((Mode) (obj)).mModeId)
                flag = matches(((Mode) (obj)).mWidth, ((Mode) (obj)).mHeight, ((Mode) (obj)).mRefreshRate);
            return flag;
        }

        public int getModeId()
        {
            return mModeId;
        }

        public int getPhysicalHeight()
        {
            return mHeight;
        }

        public int getPhysicalWidth()
        {
            return mWidth;
        }

        public float getRefreshRate()
        {
            return mRefreshRate;
        }

        public int hashCode()
        {
            return (((mModeId + 17) * 17 + mWidth) * 17 + mHeight) * 17 + Float.floatToIntBits(mRefreshRate);
        }

        public boolean matches(int i, int j, float f)
        {
            boolean flag = false;
            boolean flag1 = flag;
            if(mWidth == i)
            {
                flag1 = flag;
                if(mHeight == j)
                {
                    flag1 = flag;
                    if(Float.floatToIntBits(mRefreshRate) == Float.floatToIntBits(f))
                        flag1 = true;
                }
            }
            return flag1;
        }

        public String toString()
        {
            return (new StringBuilder("{")).append("id=").append(mModeId).append(", width=").append(mWidth).append(", height=").append(mHeight).append(", fps=").append(mRefreshRate).append("}").toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(mModeId);
            parcel.writeInt(mWidth);
            parcel.writeInt(mHeight);
            parcel.writeFloat(mRefreshRate);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public Mode createFromParcel(Parcel parcel)
            {
                return new Mode(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public Mode[] newArray(int i)
            {
                return new Mode[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public static final Mode EMPTY_ARRAY[] = new Mode[0];
        private final int mHeight;
        private final int mModeId;
        private final float mRefreshRate;
        private final int mWidth;


        public Mode(int i, int j, int k, float f)
        {
            mModeId = i;
            mWidth = j;
            mHeight = k;
            mRefreshRate = f;
        }

        private Mode(Parcel parcel)
        {
            this(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readFloat());
        }

        Mode(Parcel parcel, Mode mode)
        {
            this(parcel);
        }
    }


    public Display(DisplayManagerGlobal displaymanagerglobal, int i, DisplayInfo displayinfo, Resources resources)
    {
        this(displaymanagerglobal, i, displayinfo, null, resources);
    }

    public Display(DisplayManagerGlobal displaymanagerglobal, int i, DisplayInfo displayinfo, DisplayAdjustments displayadjustments)
    {
        this(displaymanagerglobal, i, displayinfo, displayadjustments, null);
    }

    private Display(DisplayManagerGlobal displaymanagerglobal, int i, DisplayInfo displayinfo, DisplayAdjustments displayadjustments, Resources resources)
    {
        Object obj;
        obj = null;
        super();
        mTempMetrics = new DisplayMetrics();
        mGlobal = displaymanagerglobal;
        mDisplayId = i;
        mDisplayInfo = displayinfo;
        mResources = resources;
        if(mResources == null) goto _L2; else goto _L1
_L1:
        displaymanagerglobal = new DisplayAdjustments(mResources.getConfiguration());
_L4:
        mDisplayAdjustments = displaymanagerglobal;
        mIsValid = true;
        mLayerStack = displayinfo.layerStack;
        mFlags = displayinfo.flags;
        mType = displayinfo.type;
        mAddress = displayinfo.address;
        mOwnerUid = displayinfo.ownerUid;
        mOwnerPackageName = displayinfo.ownerPackageName;
        return;
_L2:
        displaymanagerglobal = obj;
        if(displayadjustments != null)
            displaymanagerglobal = new DisplayAdjustments(displayadjustments);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static boolean hasAccess(int i, int j, int k)
    {
        boolean flag;
        boolean flag1;
        flag = true;
        flag1 = flag;
        if((j & 4) == 0) goto _L2; else goto _L1
_L1:
        if(i != k) goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        flag1 = flag;
        if(i != 1000)
        {
            flag1 = flag;
            if(i != 0)
                flag1 = false;
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    public static boolean isDozeState(int i)
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(i != 3)
            if(i == 4)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public static boolean isSuspendedState(int i)
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(i != 1)
            if(i == 4)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public static String stateToString(int i)
    {
        switch(i)
        {
        default:
            return Integer.toString(i);

        case 0: // '\0'
            return "UNKNOWN";

        case 1: // '\001'
            return "OFF";

        case 2: // '\002'
            return "ON";

        case 3: // '\003'
            return "DOZE";

        case 4: // '\004'
            return "DOZE_SUSPEND";

        case 5: // '\005'
            return "VR";
        }
    }

    public static String typeToString(int i)
    {
        switch(i)
        {
        default:
            return Integer.toString(i);

        case 0: // '\0'
            return "UNKNOWN";

        case 1: // '\001'
            return "BUILT_IN";

        case 2: // '\002'
            return "HDMI";

        case 3: // '\003'
            return "WIFI";

        case 4: // '\004'
            return "OVERLAY";

        case 5: // '\005'
            return "VIRTUAL";
        }
    }

    private void updateCachedAppSizeIfNeededLocked()
    {
        long l = SystemClock.uptimeMillis();
        if(l > mLastCachedAppSizeUpdate + 20L)
        {
            updateDisplayInfoLocked();
            mDisplayInfo.getAppMetrics(mTempMetrics, getDisplayAdjustments());
            mCachedAppWidthCompat = mTempMetrics.widthPixels;
            mCachedAppHeightCompat = mTempMetrics.heightPixels;
            mLastCachedAppSizeUpdate = l;
        }
    }

    private void updateDisplayInfoLocked()
    {
        DisplayInfo displayinfo = mGlobal.getDisplayInfo(mDisplayId);
        if(displayinfo != null) goto _L2; else goto _L1
_L1:
        if(mIsValid)
            mIsValid = false;
_L4:
        return;
_L2:
        mDisplayInfo = displayinfo;
        if(!mIsValid)
            mIsValid = true;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getAddress()
    {
        return mAddress;
    }

    public long getAppVsyncOffsetNanos()
    {
        this;
        JVM INSTR monitorenter ;
        long l;
        updateDisplayInfoLocked();
        l = mDisplayInfo.appVsyncOffsetNanos;
        this;
        JVM INSTR monitorexit ;
        return l;
        Exception exception;
        exception;
        throw exception;
    }

    public int getColorMode()
    {
        this;
        JVM INSTR monitorenter ;
        int i;
        updateDisplayInfoLocked();
        i = mDisplayInfo.colorMode;
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public void getCurrentSizeRange(Point point, Point point1)
    {
        this;
        JVM INSTR monitorenter ;
        updateDisplayInfoLocked();
        point.x = mDisplayInfo.smallestNominalAppWidth;
        point.y = mDisplayInfo.smallestNominalAppHeight;
        point1.x = mDisplayInfo.largestNominalAppWidth;
        point1.y = mDisplayInfo.largestNominalAppHeight;
        this;
        JVM INSTR monitorexit ;
        return;
        point;
        throw point;
    }

    public DisplayAdjustments getDisplayAdjustments()
    {
        if(mResources != null)
        {
            DisplayAdjustments displayadjustments = mResources.getDisplayAdjustments();
            if(!mDisplayAdjustments.equals(displayadjustments))
                mDisplayAdjustments = new DisplayAdjustments(displayadjustments);
        }
        return mDisplayAdjustments;
    }

    public int getDisplayId()
    {
        return mDisplayId;
    }

    public boolean getDisplayInfo(DisplayInfo displayinfo)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag;
        updateDisplayInfoLocked();
        displayinfo.copyFrom(mDisplayInfo);
        flag = mIsValid;
        this;
        JVM INSTR monitorexit ;
        return flag;
        displayinfo;
        throw displayinfo;
    }

    public int getFlags()
    {
        return mFlags;
    }

    public HdrCapabilities getHdrCapabilities()
    {
        this;
        JVM INSTR monitorenter ;
        HdrCapabilities hdrcapabilities;
        updateDisplayInfoLocked();
        hdrcapabilities = mDisplayInfo.hdrCapabilities;
        this;
        JVM INSTR monitorexit ;
        return hdrcapabilities;
        Exception exception;
        exception;
        throw exception;
    }

    public int getHeight()
    {
        this;
        JVM INSTR monitorenter ;
        int i;
        updateCachedAppSizeIfNeededLocked();
        i = mCachedAppHeightCompat;
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public int getLayerStack()
    {
        return mLayerStack;
    }

    public int getMaximumSizeDimension()
    {
        this;
        JVM INSTR monitorenter ;
        int i;
        updateDisplayInfoLocked();
        i = Math.max(mDisplayInfo.logicalWidth, mDisplayInfo.logicalHeight);
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public void getMetrics(DisplayMetrics displaymetrics)
    {
        this;
        JVM INSTR monitorenter ;
        updateDisplayInfoLocked();
        mDisplayInfo.getAppMetrics(displaymetrics, getDisplayAdjustments());
        this;
        JVM INSTR monitorexit ;
        return;
        displaymetrics;
        throw displaymetrics;
    }

    public Mode getMode()
    {
        this;
        JVM INSTR monitorenter ;
        Mode mode;
        updateDisplayInfoLocked();
        mode = mDisplayInfo.getMode();
        this;
        JVM INSTR monitorexit ;
        return mode;
        Exception exception;
        exception;
        throw exception;
    }

    public String getName()
    {
        this;
        JVM INSTR monitorenter ;
        String s;
        updateDisplayInfoLocked();
        s = mDisplayInfo.name;
        this;
        JVM INSTR monitorexit ;
        return s;
        Exception exception;
        exception;
        throw exception;
    }

    public int getOrientation()
    {
        return getRotation();
    }

    public void getOverscanInsets(Rect rect)
    {
        this;
        JVM INSTR monitorenter ;
        updateDisplayInfoLocked();
        rect.set(mDisplayInfo.overscanLeft, mDisplayInfo.overscanTop, mDisplayInfo.overscanRight, mDisplayInfo.overscanBottom);
        this;
        JVM INSTR monitorexit ;
        return;
        rect;
        throw rect;
    }

    public String getOwnerPackageName()
    {
        return mOwnerPackageName;
    }

    public int getOwnerUid()
    {
        return mOwnerUid;
    }

    public int getPixelFormat()
    {
        return 1;
    }

    public long getPresentationDeadlineNanos()
    {
        this;
        JVM INSTR monitorenter ;
        long l;
        updateDisplayInfoLocked();
        l = mDisplayInfo.presentationDeadlineNanos;
        this;
        JVM INSTR monitorexit ;
        return l;
        Exception exception;
        exception;
        throw exception;
    }

    public void getRealMetrics(DisplayMetrics displaymetrics)
    {
        this;
        JVM INSTR monitorenter ;
        updateDisplayInfoLocked();
        mDisplayInfo.getLogicalMetrics(displaymetrics, CompatibilityInfo.DEFAULT_COMPATIBILITY_INFO, null);
        this;
        JVM INSTR monitorexit ;
        return;
        displaymetrics;
        throw displaymetrics;
    }

    public void getRealSize(Point point)
    {
        this;
        JVM INSTR monitorenter ;
        updateDisplayInfoLocked();
        point.x = mDisplayInfo.logicalWidth;
        point.y = mDisplayInfo.logicalHeight;
        this;
        JVM INSTR monitorexit ;
        return;
        point;
        throw point;
    }

    public void getRectSize(Rect rect)
    {
        this;
        JVM INSTR monitorenter ;
        updateDisplayInfoLocked();
        mDisplayInfo.getAppMetrics(mTempMetrics, getDisplayAdjustments());
        rect.set(0, 0, mTempMetrics.widthPixels, mTempMetrics.heightPixels);
        this;
        JVM INSTR monitorexit ;
        return;
        rect;
        throw rect;
    }

    public float getRefreshRate()
    {
        this;
        JVM INSTR monitorenter ;
        float f;
        updateDisplayInfoLocked();
        f = mDisplayInfo.getMode().getRefreshRate();
        this;
        JVM INSTR monitorexit ;
        return f;
        Exception exception;
        exception;
        throw exception;
    }

    public int getRemoveMode()
    {
        return mDisplayInfo.removeMode;
    }

    public int getRotation()
    {
        this;
        JVM INSTR monitorenter ;
        int i;
        updateDisplayInfoLocked();
        i = mDisplayInfo.rotation;
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public void getSize(Point point)
    {
        this;
        JVM INSTR monitorenter ;
        updateDisplayInfoLocked();
        mDisplayInfo.getAppMetrics(mTempMetrics, getDisplayAdjustments());
        point.x = mTempMetrics.widthPixels;
        point.y = mTempMetrics.heightPixels;
        this;
        JVM INSTR monitorexit ;
        return;
        point;
        throw point;
    }

    public int getState()
    {
        this;
        JVM INSTR monitorenter ;
        int i;
        updateDisplayInfoLocked();
        if(!mIsValid)
            break MISSING_BLOCK_LABEL_25;
        i = mDisplayInfo.state;
_L1:
        this;
        JVM INSTR monitorexit ;
        return i;
        i = 0;
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    public int[] getSupportedColorModes()
    {
        this;
        JVM INSTR monitorenter ;
        int ai[];
        updateDisplayInfoLocked();
        ai = mDisplayInfo.supportedColorModes;
        ai = Arrays.copyOf(ai, ai.length);
        this;
        JVM INSTR monitorexit ;
        return ai;
        Exception exception;
        exception;
        throw exception;
    }

    public Mode[] getSupportedModes()
    {
        this;
        JVM INSTR monitorenter ;
        Mode amode[];
        updateDisplayInfoLocked();
        amode = mDisplayInfo.supportedModes;
        amode = (Mode[])Arrays.copyOf(amode, amode.length);
        this;
        JVM INSTR monitorexit ;
        return amode;
        Exception exception;
        exception;
        throw exception;
    }

    public float[] getSupportedRefreshRates()
    {
        this;
        JVM INSTR monitorenter ;
        float af[];
        updateDisplayInfoLocked();
        af = mDisplayInfo.getDefaultRefreshRates();
        this;
        JVM INSTR monitorexit ;
        return af;
        Exception exception;
        exception;
        throw exception;
    }

    public int getType()
    {
        return mType;
    }

    public int getWidth()
    {
        this;
        JVM INSTR monitorenter ;
        int i;
        updateCachedAppSizeIfNeededLocked();
        i = mCachedAppWidthCompat;
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean hasAccess(int i)
    {
        return hasAccess(i, mFlags, mOwnerUid);
    }

    public boolean isHdr()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag;
        updateDisplayInfoLocked();
        flag = mDisplayInfo.isHdr();
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean isPublicPresentation()
    {
        boolean flag;
        if((mFlags & 0xc) == 8)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isValid()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag;
        updateDisplayInfoLocked();
        flag = mIsValid;
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean isWideColorGamut()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag;
        updateDisplayInfoLocked();
        flag = mDisplayInfo.isWideColorGamut();
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public void requestColorMode(int i)
    {
        mGlobal.requestColorMode(mDisplayId, i);
    }

    public String toString()
    {
        this;
        JVM INSTR monitorenter ;
        Object obj;
        updateDisplayInfoLocked();
        mDisplayInfo.getAppMetrics(mTempMetrics, getDisplayAdjustments());
        obj = JVM INSTR new #427 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        obj = ((StringBuilder) (obj)).append("Display id ").append(mDisplayId).append(": ").append(mDisplayInfo).append(", ").append(mTempMetrics).append(", isValid=").append(mIsValid).toString();
        this;
        JVM INSTR monitorexit ;
        return ((String) (obj));
        Exception exception;
        exception;
        throw exception;
    }

    private static final int CACHED_APP_SIZE_DURATION_MILLIS = 20;
    public static final int COLOR_MODE_ADOBE_RGB = 8;
    public static final int COLOR_MODE_BT601_525 = 3;
    public static final int COLOR_MODE_BT601_525_UNADJUSTED = 4;
    public static final int COLOR_MODE_BT601_625 = 1;
    public static final int COLOR_MODE_BT601_625_UNADJUSTED = 2;
    public static final int COLOR_MODE_BT709 = 5;
    public static final int COLOR_MODE_DCI_P3 = 6;
    public static final int COLOR_MODE_DEFAULT = 0;
    public static final int COLOR_MODE_DISPLAY_P3 = 9;
    public static final int COLOR_MODE_INVALID = -1;
    public static final int COLOR_MODE_SRGB = 7;
    private static final boolean DEBUG = false;
    public static final int DEFAULT_DISPLAY = 0;
    public static final int FLAG_CAN_SHOW_WITH_INSECURE_KEYGUARD = 32;
    public static final int FLAG_PRESENTATION = 8;
    public static final int FLAG_PRIVATE = 4;
    public static final int FLAG_ROUND = 16;
    public static final int FLAG_SCALING_DISABLED = 0x40000000;
    public static final int FLAG_SECURE = 2;
    public static final int FLAG_SUPPORTS_PROTECTED_BUFFERS = 1;
    public static final int INVALID_DISPLAY = -1;
    public static final int REMOVE_MODE_DESTROY_CONTENT = 1;
    public static final int REMOVE_MODE_MOVE_CONTENT_TO_PRIMARY = 0;
    public static final int STATE_DOZE = 3;
    public static final int STATE_DOZE_SUSPEND = 4;
    public static final int STATE_OFF = 1;
    public static final int STATE_ON = 2;
    public static final int STATE_UNKNOWN = 0;
    public static final int STATE_VR = 5;
    private static final String TAG = "Display";
    public static final int TYPE_BUILT_IN = 1;
    public static final int TYPE_HDMI = 2;
    public static final int TYPE_OVERLAY = 4;
    public static final int TYPE_UNKNOWN = 0;
    public static final int TYPE_VIRTUAL = 5;
    public static final int TYPE_WIFI = 3;
    private final String mAddress;
    private int mCachedAppHeightCompat;
    private int mCachedAppWidthCompat;
    private DisplayAdjustments mDisplayAdjustments;
    private final int mDisplayId;
    private DisplayInfo mDisplayInfo;
    private final int mFlags;
    private final DisplayManagerGlobal mGlobal;
    private boolean mIsValid;
    private long mLastCachedAppSizeUpdate;
    private final int mLayerStack;
    private final String mOwnerPackageName;
    private final int mOwnerUid;
    private final Resources mResources;
    private final DisplayMetrics mTempMetrics;
    private final int mType;
}
