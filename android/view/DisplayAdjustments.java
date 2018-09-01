// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.content.res.CompatibilityInfo;
import android.content.res.Configuration;
import java.util.Objects;

public class DisplayAdjustments
{

    public DisplayAdjustments()
    {
        mCompatInfo = CompatibilityInfo.DEFAULT_COMPATIBILITY_INFO;
    }

    public DisplayAdjustments(Configuration configuration)
    {
        mCompatInfo = CompatibilityInfo.DEFAULT_COMPATIBILITY_INFO;
        if(configuration == null)
            configuration = Configuration.EMPTY;
        mConfiguration = new Configuration(configuration);
    }

    public DisplayAdjustments(DisplayAdjustments displayadjustments)
    {
        mCompatInfo = CompatibilityInfo.DEFAULT_COMPATIBILITY_INFO;
        setCompatibilityInfo(displayadjustments.mCompatInfo);
        if(displayadjustments.mConfiguration != null)
            displayadjustments = displayadjustments.mConfiguration;
        else
            displayadjustments = Configuration.EMPTY;
        mConfiguration = new Configuration(displayadjustments);
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(!(obj instanceof DisplayAdjustments))
            return false;
        obj = (DisplayAdjustments)obj;
        if(Objects.equals(((DisplayAdjustments) (obj)).mCompatInfo, mCompatInfo))
            flag = Objects.equals(((DisplayAdjustments) (obj)).mConfiguration, mConfiguration);
        return flag;
    }

    public CompatibilityInfo getCompatibilityInfo()
    {
        return mCompatInfo;
    }

    public Configuration getConfiguration()
    {
        return mConfiguration;
    }

    public int hashCode()
    {
        return (Objects.hashCode(mCompatInfo) + 527) * 31 + Objects.hashCode(mConfiguration);
    }

    public void setCompatibilityInfo(CompatibilityInfo compatibilityinfo)
    {
        if(this == DEFAULT_DISPLAY_ADJUSTMENTS)
            throw new IllegalArgumentException("setCompatbilityInfo: Cannot modify DEFAULT_DISPLAY_ADJUSTMENTS");
        if(compatibilityinfo != null && (compatibilityinfo.isScalingRequired() || compatibilityinfo.supportsScreen() ^ true))
            mCompatInfo = compatibilityinfo;
        else
            mCompatInfo = CompatibilityInfo.DEFAULT_COMPATIBILITY_INFO;
    }

    public void setConfiguration(Configuration configuration)
    {
        if(this == DEFAULT_DISPLAY_ADJUSTMENTS)
            throw new IllegalArgumentException("setConfiguration: Cannot modify DEFAULT_DISPLAY_ADJUSTMENTS");
        Configuration configuration1 = mConfiguration;
        if(configuration == null)
            configuration = Configuration.EMPTY;
        configuration1.setTo(configuration);
    }

    public static final DisplayAdjustments DEFAULT_DISPLAY_ADJUSTMENTS = new DisplayAdjustments();
    private volatile CompatibilityInfo mCompatInfo;
    private Configuration mConfiguration;

}
