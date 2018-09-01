// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.content.res.CompatibilityInfo;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArraySet;
import android.util.DisplayMetrics;
import java.util.Arrays;
import java.util.Iterator;
import libcore.util.Objects;

// Referenced classes of package android.view:
//            DisplayAdjustments, Display

public final class DisplayInfo
    implements Parcelable
{

    public DisplayInfo()
    {
        supportedModes = Display.Mode.EMPTY_ARRAY;
        removeMode = 0;
    }

    private DisplayInfo(Parcel parcel)
    {
        supportedModes = Display.Mode.EMPTY_ARRAY;
        removeMode = 0;
        readFromParcel(parcel);
    }

    DisplayInfo(Parcel parcel, DisplayInfo displayinfo)
    {
        this(parcel);
    }

    public DisplayInfo(DisplayInfo displayinfo)
    {
        supportedModes = Display.Mode.EMPTY_ARRAY;
        removeMode = 0;
        copyFrom(displayinfo);
    }

    private Display.Mode findMode(int i)
    {
        for(int j = 0; j < supportedModes.length; j++)
            if(supportedModes[j].getModeId() == i)
                return supportedModes[j];

        throw new IllegalStateException((new StringBuilder()).append("Unable to locate mode ").append(i).toString());
    }

    private static String flagsToString(int i)
    {
        StringBuilder stringbuilder = new StringBuilder();
        if((i & 2) != 0)
            stringbuilder.append(", FLAG_SECURE");
        if((i & 1) != 0)
            stringbuilder.append(", FLAG_SUPPORTS_PROTECTED_BUFFERS");
        if((i & 4) != 0)
            stringbuilder.append(", FLAG_PRIVATE");
        if((i & 8) != 0)
            stringbuilder.append(", FLAG_PRESENTATION");
        if((0x40000000 & i) != 0)
            stringbuilder.append(", FLAG_SCALING_DISABLED");
        if((i & 0x10) != 0)
            stringbuilder.append(", FLAG_ROUND");
        return stringbuilder.toString();
    }

    private void getMetricsWithSize(DisplayMetrics displaymetrics, CompatibilityInfo compatibilityinfo, Configuration configuration, int i, int j)
    {
        int k = logicalDensityDpi;
        displaymetrics.noncompatDensityDpi = k;
        displaymetrics.densityDpi = k;
        float f = (float)logicalDensityDpi * 0.00625F;
        displaymetrics.noncompatDensity = f;
        displaymetrics.density = f;
        f = displaymetrics.density;
        displaymetrics.noncompatScaledDensity = f;
        displaymetrics.scaledDensity = f;
        f = physicalXDpi;
        displaymetrics.noncompatXdpi = f;
        displaymetrics.xdpi = f;
        f = physicalYDpi;
        displaymetrics.noncompatYdpi = f;
        displaymetrics.ydpi = f;
        k = i;
        if(configuration != null)
        {
            k = i;
            if(configuration.appBounds != null)
                k = configuration.appBounds.width();
        }
        i = j;
        if(configuration != null)
        {
            i = j;
            if(configuration.appBounds != null)
                i = configuration.appBounds.height();
        }
        displaymetrics.widthPixels = k;
        displaymetrics.noncompatWidthPixels = k;
        displaymetrics.heightPixels = i;
        displaymetrics.noncompatHeightPixels = i;
        if(!compatibilityinfo.equals(CompatibilityInfo.DEFAULT_COMPATIBILITY_INFO))
            compatibilityinfo.applyToDisplayMetrics(displaymetrics);
    }

    public void copyFrom(DisplayInfo displayinfo)
    {
        layerStack = displayinfo.layerStack;
        flags = displayinfo.flags;
        type = displayinfo.type;
        address = displayinfo.address;
        name = displayinfo.name;
        uniqueId = displayinfo.uniqueId;
        appWidth = displayinfo.appWidth;
        appHeight = displayinfo.appHeight;
        smallestNominalAppWidth = displayinfo.smallestNominalAppWidth;
        smallestNominalAppHeight = displayinfo.smallestNominalAppHeight;
        largestNominalAppWidth = displayinfo.largestNominalAppWidth;
        largestNominalAppHeight = displayinfo.largestNominalAppHeight;
        logicalWidth = displayinfo.logicalWidth;
        logicalHeight = displayinfo.logicalHeight;
        overscanLeft = displayinfo.overscanLeft;
        overscanTop = displayinfo.overscanTop;
        overscanRight = displayinfo.overscanRight;
        overscanBottom = displayinfo.overscanBottom;
        rotation = displayinfo.rotation;
        modeId = displayinfo.modeId;
        defaultModeId = displayinfo.defaultModeId;
        supportedModes = (Display.Mode[])Arrays.copyOf(displayinfo.supportedModes, displayinfo.supportedModes.length);
        colorMode = displayinfo.colorMode;
        supportedColorModes = Arrays.copyOf(displayinfo.supportedColorModes, displayinfo.supportedColorModes.length);
        hdrCapabilities = displayinfo.hdrCapabilities;
        logicalDensityDpi = displayinfo.logicalDensityDpi;
        physicalXDpi = displayinfo.physicalXDpi;
        physicalYDpi = displayinfo.physicalYDpi;
        appVsyncOffsetNanos = displayinfo.appVsyncOffsetNanos;
        presentationDeadlineNanos = displayinfo.presentationDeadlineNanos;
        state = displayinfo.state;
        ownerUid = displayinfo.ownerUid;
        ownerPackageName = displayinfo.ownerPackageName;
        removeMode = displayinfo.removeMode;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(DisplayInfo displayinfo)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(displayinfo != null)
        {
            flag1 = flag;
            if(layerStack == displayinfo.layerStack)
            {
                flag1 = flag;
                if(flags == displayinfo.flags)
                {
                    flag1 = flag;
                    if(type == displayinfo.type)
                    {
                        flag1 = flag;
                        if(Objects.equal(address, displayinfo.address))
                        {
                            flag1 = flag;
                            if(Objects.equal(uniqueId, displayinfo.uniqueId))
                            {
                                flag1 = flag;
                                if(appWidth == displayinfo.appWidth)
                                {
                                    flag1 = flag;
                                    if(appHeight == displayinfo.appHeight)
                                    {
                                        flag1 = flag;
                                        if(smallestNominalAppWidth == displayinfo.smallestNominalAppWidth)
                                        {
                                            flag1 = flag;
                                            if(smallestNominalAppHeight == displayinfo.smallestNominalAppHeight)
                                            {
                                                flag1 = flag;
                                                if(largestNominalAppWidth == displayinfo.largestNominalAppWidth)
                                                {
                                                    flag1 = flag;
                                                    if(largestNominalAppHeight == displayinfo.largestNominalAppHeight)
                                                    {
                                                        flag1 = flag;
                                                        if(logicalWidth == displayinfo.logicalWidth)
                                                        {
                                                            flag1 = flag;
                                                            if(logicalHeight == displayinfo.logicalHeight)
                                                            {
                                                                flag1 = flag;
                                                                if(overscanLeft == displayinfo.overscanLeft)
                                                                {
                                                                    flag1 = flag;
                                                                    if(overscanTop == displayinfo.overscanTop)
                                                                    {
                                                                        flag1 = flag;
                                                                        if(overscanRight == displayinfo.overscanRight)
                                                                        {
                                                                            flag1 = flag;
                                                                            if(overscanBottom == displayinfo.overscanBottom)
                                                                            {
                                                                                flag1 = flag;
                                                                                if(rotation == displayinfo.rotation)
                                                                                {
                                                                                    flag1 = flag;
                                                                                    if(modeId == displayinfo.modeId)
                                                                                    {
                                                                                        flag1 = flag;
                                                                                        if(defaultModeId == displayinfo.defaultModeId)
                                                                                        {
                                                                                            flag1 = flag;
                                                                                            if(colorMode == displayinfo.colorMode)
                                                                                            {
                                                                                                flag1 = flag;
                                                                                                if(Arrays.equals(supportedColorModes, displayinfo.supportedColorModes))
                                                                                                {
                                                                                                    flag1 = flag;
                                                                                                    if(Objects.equal(hdrCapabilities, displayinfo.hdrCapabilities))
                                                                                                    {
                                                                                                        flag1 = flag;
                                                                                                        if(logicalDensityDpi == displayinfo.logicalDensityDpi)
                                                                                                        {
                                                                                                            flag1 = flag;
                                                                                                            if(physicalXDpi == displayinfo.physicalXDpi)
                                                                                                            {
                                                                                                                flag1 = flag;
                                                                                                                if(physicalYDpi == displayinfo.physicalYDpi)
                                                                                                                {
                                                                                                                    flag1 = flag;
                                                                                                                    if(appVsyncOffsetNanos == displayinfo.appVsyncOffsetNanos)
                                                                                                                    {
                                                                                                                        flag1 = flag;
                                                                                                                        if(presentationDeadlineNanos == displayinfo.presentationDeadlineNanos)
                                                                                                                        {
                                                                                                                            flag1 = flag;
                                                                                                                            if(state == displayinfo.state)
                                                                                                                            {
                                                                                                                                flag1 = flag;
                                                                                                                                if(ownerUid == displayinfo.ownerUid)
                                                                                                                                {
                                                                                                                                    flag1 = flag;
                                                                                                                                    if(Objects.equal(ownerPackageName, displayinfo.ownerPackageName))
                                                                                                                                    {
                                                                                                                                        flag1 = flag;
                                                                                                                                        if(removeMode == displayinfo.removeMode)
                                                                                                                                            flag1 = true;
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return flag1;
    }

    public boolean equals(Object obj)
    {
        boolean flag;
        if(obj instanceof DisplayInfo)
            flag = equals((DisplayInfo)obj);
        else
            flag = false;
        return flag;
    }

    public int findDefaultModeByRefreshRate(float f)
    {
        Display.Mode amode[] = supportedModes;
        Display.Mode mode = getDefaultMode();
        for(int i = 0; i < amode.length; i++)
            if(amode[i].matches(mode.getPhysicalWidth(), mode.getPhysicalHeight(), f))
                return amode[i].getModeId();

        return 0;
    }

    public void getAppMetrics(DisplayMetrics displaymetrics)
    {
        getAppMetrics(displaymetrics, CompatibilityInfo.DEFAULT_COMPATIBILITY_INFO, null);
    }

    public void getAppMetrics(DisplayMetrics displaymetrics, CompatibilityInfo compatibilityinfo, Configuration configuration)
    {
        getMetricsWithSize(displaymetrics, compatibilityinfo, configuration, appWidth, appHeight);
    }

    public void getAppMetrics(DisplayMetrics displaymetrics, DisplayAdjustments displayadjustments)
    {
        getMetricsWithSize(displaymetrics, displayadjustments.getCompatibilityInfo(), displayadjustments.getConfiguration(), appWidth, appHeight);
    }

    public Display.Mode getDefaultMode()
    {
        return findMode(defaultModeId);
    }

    public float[] getDefaultRefreshRates()
    {
        Object aobj[] = supportedModes;
        Object obj = new ArraySet();
        Display.Mode mode = getDefaultMode();
        for(int i = 0; i < aobj.length; i++)
        {
            Display.Mode mode1 = aobj[i];
            if(mode1.getPhysicalWidth() == mode.getPhysicalWidth() && mode1.getPhysicalHeight() == mode.getPhysicalHeight())
                ((ArraySet) (obj)).add(Float.valueOf(mode1.getRefreshRate()));
        }

        aobj = new float[((ArraySet) (obj)).size()];
        int j = 0;
        for(obj = ((Iterable) (obj)).iterator(); ((Iterator) (obj)).hasNext();)
        {
            aobj[j] = ((Float)((Iterator) (obj)).next()).floatValue();
            j++;
        }

        return ((float []) (aobj));
    }

    public void getLogicalMetrics(DisplayMetrics displaymetrics, CompatibilityInfo compatibilityinfo, Configuration configuration)
    {
        getMetricsWithSize(displaymetrics, compatibilityinfo, configuration, logicalWidth, logicalHeight);
    }

    public Display.Mode getMode()
    {
        return findMode(modeId);
    }

    public int getNaturalHeight()
    {
        int i;
        if(rotation == 0 || rotation == 2)
            i = logicalHeight;
        else
            i = logicalWidth;
        return i;
    }

    public int getNaturalWidth()
    {
        int i;
        if(rotation == 0 || rotation == 2)
            i = logicalWidth;
        else
            i = logicalHeight;
        return i;
    }

    public boolean hasAccess(int i)
    {
        return Display.hasAccess(i, flags, ownerUid);
    }

    public int hashCode()
    {
        return 0;
    }

    public boolean isHdr()
    {
        boolean flag = false;
        int ai[];
        boolean flag1;
        if(hdrCapabilities != null)
            ai = hdrCapabilities.getSupportedHdrTypes();
        else
            ai = null;
        flag1 = flag;
        if(ai != null)
        {
            flag1 = flag;
            if(ai.length > 0)
                flag1 = true;
        }
        return flag1;
    }

    public boolean isWideColorGamut()
    {
        int ai[] = supportedColorModes;
        int i = ai.length;
        for(int j = 0; j < i; j++)
        {
            int k = ai[j];
            if(k == 6 || k > 7)
                return true;
        }

        return false;
    }

    public void readFromParcel(Parcel parcel)
    {
        layerStack = parcel.readInt();
        flags = parcel.readInt();
        type = parcel.readInt();
        address = parcel.readString();
        name = parcel.readString();
        appWidth = parcel.readInt();
        appHeight = parcel.readInt();
        smallestNominalAppWidth = parcel.readInt();
        smallestNominalAppHeight = parcel.readInt();
        largestNominalAppWidth = parcel.readInt();
        largestNominalAppHeight = parcel.readInt();
        logicalWidth = parcel.readInt();
        logicalHeight = parcel.readInt();
        overscanLeft = parcel.readInt();
        overscanTop = parcel.readInt();
        overscanRight = parcel.readInt();
        overscanBottom = parcel.readInt();
        rotation = parcel.readInt();
        modeId = parcel.readInt();
        defaultModeId = parcel.readInt();
        int i = parcel.readInt();
        supportedModes = new Display.Mode[i];
        for(int j = 0; j < i; j++)
            supportedModes[j] = (Display.Mode)Display.Mode.CREATOR.createFromParcel(parcel);

        colorMode = parcel.readInt();
        i = parcel.readInt();
        supportedColorModes = new int[i];
        for(int k = 0; k < i; k++)
            supportedColorModes[k] = parcel.readInt();

        hdrCapabilities = (Display.HdrCapabilities)parcel.readParcelable(null);
        logicalDensityDpi = parcel.readInt();
        physicalXDpi = parcel.readFloat();
        physicalYDpi = parcel.readFloat();
        appVsyncOffsetNanos = parcel.readLong();
        presentationDeadlineNanos = parcel.readLong();
        state = parcel.readInt();
        ownerUid = parcel.readInt();
        ownerPackageName = parcel.readString();
        uniqueId = parcel.readString();
        removeMode = parcel.readInt();
    }

    public String toString()
    {
        StringBuilder stringbuilder;
        stringbuilder = new StringBuilder();
        stringbuilder.append("DisplayInfo{\"");
        stringbuilder.append(name);
        stringbuilder.append("\", uniqueId \"");
        stringbuilder.append(uniqueId);
        stringbuilder.append("\", app ");
        stringbuilder.append(appWidth);
        stringbuilder.append(" x ");
        stringbuilder.append(appHeight);
        stringbuilder.append(", real ");
        stringbuilder.append(logicalWidth);
        stringbuilder.append(" x ");
        stringbuilder.append(logicalHeight);
        break MISSING_BLOCK_LABEL_110;
        if(overscanLeft != 0 || overscanTop != 0 || overscanRight != 0 || overscanBottom != 0)
        {
            stringbuilder.append(", overscan (");
            stringbuilder.append(overscanLeft);
            stringbuilder.append(",");
            stringbuilder.append(overscanTop);
            stringbuilder.append(",");
            stringbuilder.append(overscanRight);
            stringbuilder.append(",");
            stringbuilder.append(overscanBottom);
            stringbuilder.append(")");
        }
        stringbuilder.append(", largest app ");
        stringbuilder.append(largestNominalAppWidth);
        stringbuilder.append(" x ");
        stringbuilder.append(largestNominalAppHeight);
        stringbuilder.append(", smallest app ");
        stringbuilder.append(smallestNominalAppWidth);
        stringbuilder.append(" x ");
        stringbuilder.append(smallestNominalAppHeight);
        stringbuilder.append(", mode ");
        stringbuilder.append(modeId);
        stringbuilder.append(", defaultMode ");
        stringbuilder.append(defaultModeId);
        stringbuilder.append(", modes ");
        stringbuilder.append(Arrays.toString(supportedModes));
        stringbuilder.append(", colorMode ");
        stringbuilder.append(colorMode);
        stringbuilder.append(", supportedColorModes ");
        stringbuilder.append(Arrays.toString(supportedColorModes));
        stringbuilder.append(", hdrCapabilities ");
        stringbuilder.append(hdrCapabilities);
        stringbuilder.append(", rotation ");
        stringbuilder.append(rotation);
        stringbuilder.append(", density ");
        stringbuilder.append(logicalDensityDpi);
        stringbuilder.append(" (");
        stringbuilder.append(physicalXDpi);
        stringbuilder.append(" x ");
        stringbuilder.append(physicalYDpi);
        stringbuilder.append(") dpi, layerStack ");
        stringbuilder.append(layerStack);
        stringbuilder.append(", appVsyncOff ");
        stringbuilder.append(appVsyncOffsetNanos);
        stringbuilder.append(", presDeadline ");
        stringbuilder.append(presentationDeadlineNanos);
        stringbuilder.append(", type ");
        stringbuilder.append(Display.typeToString(type));
        if(address != null)
            stringbuilder.append(", address ").append(address);
        stringbuilder.append(", state ");
        stringbuilder.append(Display.stateToString(state));
        if(ownerUid != 0 || ownerPackageName != null)
        {
            stringbuilder.append(", owner ").append(ownerPackageName);
            stringbuilder.append(" (uid ").append(ownerUid).append(")");
        }
        stringbuilder.append(flagsToString(flags));
        stringbuilder.append(", removeMode ");
        stringbuilder.append(removeMode);
        stringbuilder.append("}");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(layerStack);
        parcel.writeInt(flags);
        parcel.writeInt(type);
        parcel.writeString(address);
        parcel.writeString(name);
        parcel.writeInt(appWidth);
        parcel.writeInt(appHeight);
        parcel.writeInt(smallestNominalAppWidth);
        parcel.writeInt(smallestNominalAppHeight);
        parcel.writeInt(largestNominalAppWidth);
        parcel.writeInt(largestNominalAppHeight);
        parcel.writeInt(logicalWidth);
        parcel.writeInt(logicalHeight);
        parcel.writeInt(overscanLeft);
        parcel.writeInt(overscanTop);
        parcel.writeInt(overscanRight);
        parcel.writeInt(overscanBottom);
        parcel.writeInt(rotation);
        parcel.writeInt(modeId);
        parcel.writeInt(defaultModeId);
        parcel.writeInt(supportedModes.length);
        for(int j = 0; j < supportedModes.length; j++)
            supportedModes[j].writeToParcel(parcel, i);

        parcel.writeInt(colorMode);
        parcel.writeInt(supportedColorModes.length);
        for(int k = 0; k < supportedColorModes.length; k++)
            parcel.writeInt(supportedColorModes[k]);

        parcel.writeParcelable(hdrCapabilities, i);
        parcel.writeInt(logicalDensityDpi);
        parcel.writeFloat(physicalXDpi);
        parcel.writeFloat(physicalYDpi);
        parcel.writeLong(appVsyncOffsetNanos);
        parcel.writeLong(presentationDeadlineNanos);
        parcel.writeInt(state);
        parcel.writeInt(ownerUid);
        parcel.writeString(ownerPackageName);
        parcel.writeString(uniqueId);
        parcel.writeInt(removeMode);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public DisplayInfo createFromParcel(Parcel parcel)
        {
            return new DisplayInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public DisplayInfo[] newArray(int i)
        {
            return new DisplayInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public String address;
    public int appHeight;
    public long appVsyncOffsetNanos;
    public int appWidth;
    public int colorMode;
    public int defaultModeId;
    public int flags;
    public Display.HdrCapabilities hdrCapabilities;
    public int largestNominalAppHeight;
    public int largestNominalAppWidth;
    public int layerStack;
    public int logicalDensityDpi;
    public int logicalHeight;
    public int logicalWidth;
    public int modeId;
    public String name;
    public int overscanBottom;
    public int overscanLeft;
    public int overscanRight;
    public int overscanTop;
    public String ownerPackageName;
    public int ownerUid;
    public float physicalXDpi;
    public float physicalYDpi;
    public long presentationDeadlineNanos;
    public int removeMode;
    public int rotation;
    public int smallestNominalAppHeight;
    public int smallestNominalAppWidth;
    public int state;
    public int supportedColorModes[] = {
        0
    };
    public Display.Mode supportedModes[];
    public int type;
    public String uniqueId;

}
