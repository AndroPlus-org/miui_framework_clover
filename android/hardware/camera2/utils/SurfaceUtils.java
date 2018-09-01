// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.utils;

import android.hardware.camera2.legacy.LegacyCameraDevice;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.util.Range;
import android.util.Size;
import android.view.Surface;
import java.util.*;

public class SurfaceUtils
{

    public SurfaceUtils()
    {
    }

    public static void checkConstrainedHighSpeedSurfaces(Collection collection, Range range, StreamConfigurationMap streamconfigurationmap)
    {
        while(collection == null || collection.size() == 0 || collection.size() > 2) 
            throw new IllegalArgumentException("Output target surface list must not be null and the size must be 1 or 2");
        if(range == null)
        {
            range = Arrays.asList(streamconfigurationmap.getHighSpeedVideoSizes());
        } else
        {
            Range arange[] = streamconfigurationmap.getHighSpeedVideoFpsRanges();
            if(!Arrays.asList(arange).contains(range))
                throw new IllegalArgumentException((new StringBuilder()).append("Fps range ").append(range.toString()).append(" in the").append(" request is not a supported high speed fps range ").append(Arrays.toString(arange)).toString());
            range = Arrays.asList(streamconfigurationmap.getHighSpeedVideoSizesFor(range));
        }
        for(streamconfigurationmap = collection.iterator(); streamconfigurationmap.hasNext();)
        {
            Surface surface = (Surface)streamconfigurationmap.next();
            checkHighSpeedSurfaceFormat(surface);
            Size size = getSurfaceSize(surface);
            if(!range.contains(size))
                throw new IllegalArgumentException((new StringBuilder()).append("Surface size ").append(size.toString()).append(" is").append(" not part of the high speed supported size list ").append(Arrays.toString(range.toArray())).toString());
            if(!isSurfaceForPreview(surface) && isSurfaceForHwVideoEncoder(surface) ^ true)
                throw new IllegalArgumentException("This output surface is neither preview nor hardware video encoding surface");
            if(isSurfaceForPreview(surface) && isSurfaceForHwVideoEncoder(surface))
                throw new IllegalArgumentException("This output surface can not be both preview and hardware video encoding surface");
        }

        if(collection.size() == 2)
        {
            collection = collection.iterator();
            if(isSurfaceForPreview((Surface)collection.next()) == isSurfaceForPreview((Surface)collection.next()))
                throw new IllegalArgumentException("The 2 output surfaces must have different type");
        }
    }

    private static void checkHighSpeedSurfaceFormat(Surface surface)
    {
        int i = getSurfaceFormat(surface);
        if(i != 34)
            throw new IllegalArgumentException((new StringBuilder()).append("Surface format(").append(i).append(") is not").append(" for preview or hardware video encoding!").toString());
        else
            return;
    }

    public static int getSurfaceDataspace(Surface surface)
    {
        int i;
        try
        {
            i = LegacyCameraDevice.detectSurfaceDataspace(surface);
        }
        // Misplaced declaration of an exception variable
        catch(Surface surface)
        {
            throw new IllegalArgumentException("Surface was abandoned", surface);
        }
        return i;
    }

    public static int getSurfaceFormat(Surface surface)
    {
        int i;
        try
        {
            i = LegacyCameraDevice.detectSurfaceType(surface);
        }
        // Misplaced declaration of an exception variable
        catch(Surface surface)
        {
            throw new IllegalArgumentException("Surface was abandoned", surface);
        }
        return i;
    }

    public static Size getSurfaceSize(Surface surface)
    {
        try
        {
            surface = LegacyCameraDevice.getSurfaceSize(surface);
        }
        // Misplaced declaration of an exception variable
        catch(Surface surface)
        {
            throw new IllegalArgumentException("Surface was abandoned", surface);
        }
        return surface;
    }

    public static boolean isFlexibleConsumer(Surface surface)
    {
        return LegacyCameraDevice.isFlexibleConsumer(surface);
    }

    public static boolean isSurfaceForHwVideoEncoder(Surface surface)
    {
        return LegacyCameraDevice.isVideoEncoderConsumer(surface);
    }

    public static boolean isSurfaceForPreview(Surface surface)
    {
        return LegacyCameraDevice.isPreviewConsumer(surface);
    }
}
