// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.params;

import android.graphics.*;
import android.hardware.camera2.utils.HashCodeHelpers;
import android.hardware.camera2.utils.SurfaceUtils;
import android.media.*;
import android.renderscript.Allocation;
import android.util.*;
import android.view.Surface;
import android.view.SurfaceHolder;
import com.android.internal.util.Preconditions;
import java.util.*;

// Referenced classes of package android.hardware.camera2.params:
//            StreamConfiguration, StreamConfigurationDuration, HighSpeedVideoConfiguration, ReprocessFormatsMap

public final class StreamConfigurationMap
{

    public StreamConfigurationMap(StreamConfiguration astreamconfiguration[], StreamConfigurationDuration astreamconfigurationduration[], StreamConfigurationDuration astreamconfigurationduration1[], StreamConfiguration astreamconfiguration1[], StreamConfigurationDuration astreamconfigurationduration2[], StreamConfigurationDuration astreamconfigurationduration3[], HighSpeedVideoConfiguration ahighspeedvideoconfiguration[], 
            ReprocessFormatsMap reprocessformatsmap, boolean flag)
    {
        int i1;
        mOutputFormats = new SparseIntArray();
        mHighResOutputFormats = new SparseIntArray();
        mAllOutputFormats = new SparseIntArray();
        mInputFormats = new SparseIntArray();
        mDepthOutputFormats = new SparseIntArray();
        mHighSpeedVideoSizeMap = new HashMap();
        mHighSpeedVideoFpsRangeMap = new HashMap();
        int i;
        int j;
        int l;
        long l1;
        long l2;
        int i2;
        if(astreamconfiguration == null)
        {
            Preconditions.checkArrayElementsNotNull(astreamconfiguration1, "depthConfigurations");
            mConfigurations = new StreamConfiguration[0];
            mMinFrameDurations = new StreamConfigurationDuration[0];
            mStallDurations = new StreamConfigurationDuration[0];
        } else
        {
            mConfigurations = (StreamConfiguration[])Preconditions.checkArrayElementsNotNull(astreamconfiguration, "configurations");
            mMinFrameDurations = (StreamConfigurationDuration[])Preconditions.checkArrayElementsNotNull(astreamconfigurationduration, "minFrameDurations");
            mStallDurations = (StreamConfigurationDuration[])Preconditions.checkArrayElementsNotNull(astreamconfigurationduration1, "stallDurations");
        }
        mListHighResolution = flag;
        if(astreamconfiguration1 == null)
        {
            mDepthConfigurations = new StreamConfiguration[0];
            mDepthMinFrameDurations = new StreamConfigurationDuration[0];
            mDepthStallDurations = new StreamConfigurationDuration[0];
        } else
        {
            mDepthConfigurations = (StreamConfiguration[])Preconditions.checkArrayElementsNotNull(astreamconfiguration1, "depthConfigurations");
            mDepthMinFrameDurations = (StreamConfigurationDuration[])Preconditions.checkArrayElementsNotNull(astreamconfigurationduration2, "depthMinFrameDurations");
            mDepthStallDurations = (StreamConfigurationDuration[])Preconditions.checkArrayElementsNotNull(astreamconfigurationduration3, "depthStallDurations");
        }
        if(ahighspeedvideoconfiguration == null)
            mHighSpeedVideoConfigurations = new HighSpeedVideoConfiguration[0];
        else
            mHighSpeedVideoConfigurations = (HighSpeedVideoConfiguration[])Preconditions.checkArrayElementsNotNull(ahighspeedvideoconfiguration, "highSpeedVideoConfigurations");
        astreamconfigurationduration1 = mConfigurations;
        i = astreamconfigurationduration1.length;
        j = 0;
_L9:
        if(j >= i)
            break MISSING_BLOCK_LABEL_475;
        astreamconfigurationduration2 = astreamconfigurationduration1[j];
        l = astreamconfigurationduration2.getFormat();
        if(!astreamconfigurationduration2.isOutput())
            break MISSING_BLOCK_LABEL_467;
        mAllOutputFormats.put(l, mAllOutputFormats.get(l) + 1);
        l1 = 0L;
        l2 = l1;
        if(!mListHighResolution) goto _L2; else goto _L1
_L1:
        astreamconfiguration1 = mMinFrameDurations;
        i1 = 0;
        i2 = astreamconfiguration1.length;
_L6:
        l2 = l1;
        if(i1 >= i2) goto _L2; else goto _L3
_L3:
        astreamconfigurationduration = astreamconfiguration1[i1];
        if(astreamconfigurationduration.getFormat() != l || astreamconfigurationduration.getWidth() != astreamconfigurationduration2.getSize().getWidth() || astreamconfigurationduration.getHeight() != astreamconfigurationduration2.getSize().getHeight()) goto _L5; else goto _L4
_L4:
        l2 = astreamconfigurationduration.getDuration();
_L2:
        if(l2 <= 0x2faf080L)
            astreamconfigurationduration = mOutputFormats;
        else
            astreamconfigurationduration = mHighResOutputFormats;
_L7:
        astreamconfigurationduration.put(l, astreamconfigurationduration.get(l) + 1);
        j++;
        continue; /* Loop/switch isn't completed */
_L5:
        i1++;
          goto _L6
        astreamconfigurationduration = mInputFormats;
          goto _L7
        astreamconfigurationduration1 = mDepthConfigurations;
        int k = 0;
        int j1 = astreamconfigurationduration1.length;
        while(k < j1) 
        {
            astreamconfigurationduration = astreamconfigurationduration1[k];
            if(astreamconfigurationduration.isOutput())
                mDepthOutputFormats.put(astreamconfigurationduration.getFormat(), mDepthOutputFormats.get(astreamconfigurationduration.getFormat()) + 1);
            k++;
        }
        if(astreamconfiguration != null && mOutputFormats.indexOfKey(34) < 0)
            throw new AssertionError("At least one stream configuration for IMPLEMENTATION_DEFINED must exist");
        astreamconfigurationduration1 = mHighSpeedVideoConfigurations;
        k = 0;
        for(int k1 = astreamconfigurationduration1.length; k < k1; k++)
        {
            astreamconfiguration = astreamconfigurationduration1[k];
            astreamconfigurationduration2 = astreamconfiguration.getSize();
            astreamconfiguration1 = astreamconfiguration.getFpsRange();
            astreamconfigurationduration = (Integer)mHighSpeedVideoSizeMap.get(astreamconfigurationduration2);
            astreamconfiguration = astreamconfigurationduration;
            if(astreamconfigurationduration == null)
                astreamconfiguration = Integer.valueOf(0);
            mHighSpeedVideoSizeMap.put(astreamconfigurationduration2, Integer.valueOf(astreamconfiguration.intValue() + 1));
            astreamconfigurationduration = (Integer)mHighSpeedVideoFpsRangeMap.get(astreamconfiguration1);
            astreamconfiguration = astreamconfigurationduration;
            if(astreamconfigurationduration == null)
                astreamconfiguration = Integer.valueOf(0);
            mHighSpeedVideoFpsRangeMap.put(astreamconfiguration1, Integer.valueOf(astreamconfiguration.intValue() + 1));
        }

        mInputOutputFormatsMap = reprocessformatsmap;
        return;
        if(true) goto _L9; else goto _L8
_L8:
    }

    private void appendHighResOutputsString(StringBuilder stringbuilder)
    {
        stringbuilder.append("HighResolutionOutputs(");
        int ai[] = getOutputFormats();
        int i = ai.length;
        int j = 0;
        while(j < i) 
        {
            int k = ai[j];
            Size asize[] = getHighResolutionOutputSizes(k);
            if(asize != null)
            {
                int l = 0;
                int i1 = asize.length;
                while(l < i1) 
                {
                    Size size = asize[l];
                    long l1 = getOutputMinFrameDuration(k, size);
                    long l2 = getOutputStallDuration(k, size);
                    stringbuilder.append(String.format("[w:%d, h:%d, format:%s(%d), min_duration:%d, stall:%d], ", new Object[] {
                        Integer.valueOf(size.getWidth()), Integer.valueOf(size.getHeight()), formatToString(k), Integer.valueOf(k), Long.valueOf(l1), Long.valueOf(l2)
                    }));
                    l++;
                }
            }
            j++;
        }
        if(stringbuilder.charAt(stringbuilder.length() - 1) == ' ')
            stringbuilder.delete(stringbuilder.length() - 2, stringbuilder.length());
        stringbuilder.append(")");
    }

    private void appendHighSpeedVideoConfigurationsString(StringBuilder stringbuilder)
    {
        stringbuilder.append("HighSpeedVideoConfigurations(");
        Size asize[] = getHighSpeedVideoSizes();
        int i = asize.length;
        for(int j = 0; j < i; j++)
        {
            Size size = asize[j];
            Range arange[] = getHighSpeedVideoFpsRangesFor(size);
            int k = arange.length;
            for(int l = 0; l < k; l++)
            {
                Range range = arange[l];
                stringbuilder.append(String.format("[w:%d, h:%d, min_fps:%d, max_fps:%d], ", new Object[] {
                    Integer.valueOf(size.getWidth()), Integer.valueOf(size.getHeight()), range.getLower(), range.getUpper()
                }));
            }

        }

        if(stringbuilder.charAt(stringbuilder.length() - 1) == ' ')
            stringbuilder.delete(stringbuilder.length() - 2, stringbuilder.length());
        stringbuilder.append(")");
    }

    private void appendInputsString(StringBuilder stringbuilder)
    {
        stringbuilder.append("Inputs(");
        int ai[] = getInputFormats();
        int i = ai.length;
        for(int j = 0; j < i; j++)
        {
            int k = ai[j];
            Size asize[] = getInputSizes(k);
            int l = asize.length;
            for(int i1 = 0; i1 < l; i1++)
            {
                Size size = asize[i1];
                stringbuilder.append(String.format("[w:%d, h:%d, format:%s(%d)], ", new Object[] {
                    Integer.valueOf(size.getWidth()), Integer.valueOf(size.getHeight()), formatToString(k), Integer.valueOf(k)
                }));
            }

        }

        if(stringbuilder.charAt(stringbuilder.length() - 1) == ' ')
            stringbuilder.delete(stringbuilder.length() - 2, stringbuilder.length());
        stringbuilder.append(")");
    }

    private void appendOutputsString(StringBuilder stringbuilder)
    {
        stringbuilder.append("Outputs(");
        int ai[] = getOutputFormats();
        int i = ai.length;
        for(int j = 0; j < i; j++)
        {
            int k = ai[j];
            Size asize[] = getOutputSizes(k);
            int l = 0;
            for(int i1 = asize.length; l < i1; l++)
            {
                Size size = asize[l];
                long l1 = getOutputMinFrameDuration(k, size);
                long l2 = getOutputStallDuration(k, size);
                stringbuilder.append(String.format("[w:%d, h:%d, format:%s(%d), min_duration:%d, stall:%d], ", new Object[] {
                    Integer.valueOf(size.getWidth()), Integer.valueOf(size.getHeight()), formatToString(k), Integer.valueOf(k), Long.valueOf(l1), Long.valueOf(l2)
                }));
            }

        }

        if(stringbuilder.charAt(stringbuilder.length() - 1) == ' ')
            stringbuilder.delete(stringbuilder.length() - 2, stringbuilder.length());
        stringbuilder.append(")");
    }

    private void appendValidOutputFormatsForInputString(StringBuilder stringbuilder)
    {
        stringbuilder.append("ValidOutputFormatsForInput(");
        int ai[] = getInputFormats();
        int i = ai.length;
        for(int j = 0; j < i; j++)
        {
            int k = ai[j];
            stringbuilder.append(String.format("[in:%s(%d), out:", new Object[] {
                formatToString(k), Integer.valueOf(k)
            }));
            int ai1[] = getValidOutputFormatsForInput(k);
            for(int l = 0; l < ai1.length; l++)
            {
                stringbuilder.append(String.format("%s(%d)", new Object[] {
                    formatToString(ai1[l]), Integer.valueOf(ai1[l])
                }));
                if(l < ai1.length - 1)
                    stringbuilder.append(", ");
            }

            stringbuilder.append("], ");
        }

        if(stringbuilder.charAt(stringbuilder.length() - 1) == ' ')
            stringbuilder.delete(stringbuilder.length() - 2, stringbuilder.length());
        stringbuilder.append(")");
    }

    private static boolean arrayContains(Object aobj[], Object obj)
    {
        if(aobj == null)
            return false;
        int i = aobj.length;
        for(int j = 0; j < i; j++)
            if(Objects.equals(aobj[j], obj))
                return true;

        return false;
    }

    static int checkArgumentFormat(int i)
    {
        if(!ImageFormat.isPublicFormat(i) && PixelFormat.isPublicFormat(i) ^ true)
            throw new IllegalArgumentException(String.format("format 0x%x was not defined in either ImageFormat or PixelFormat", new Object[] {
                Integer.valueOf(i)
            }));
        else
            return i;
    }

    static int checkArgumentFormatInternal(int i)
    {
        switch(i)
        {
        default:
            return checkArgumentFormat(i);

        case 33: // '!'
        case 34: // '"'
        case 36: // '$'
        case 540422489: 
            return i;

        case 256: 
            throw new IllegalArgumentException("ImageFormat.JPEG is an unknown internal format");
        }
    }

    private int checkArgumentFormatSupported(int i, boolean flag)
    {
        checkArgumentFormat(i);
        int j = imageFormatToInternal(i);
        int k = imageFormatToDataspace(i);
        if(flag)
        {
            if(k == 4096)
            {
                if(mDepthOutputFormats.indexOfKey(j) >= 0)
                    return i;
            } else
            if(mAllOutputFormats.indexOfKey(j) >= 0)
                return i;
        } else
        if(mInputFormats.indexOfKey(j) >= 0)
            return i;
        throw new IllegalArgumentException(String.format("format %x is not supported by this stream configuration map", new Object[] {
            Integer.valueOf(i)
        }));
    }

    static int depthFormatToPublic(int i)
    {
        switch(i)
        {
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown DATASPACE_DEPTH format ").append(i).toString());

        case 33: // '!'
            return 257;

        case 540422489: 
            return 0x44363159;

        case 32: // ' '
            return 4098;

        case 256: 
            throw new IllegalArgumentException("ImageFormat.JPEG is an unknown internal format");

        case 34: // '"'
            throw new IllegalArgumentException("IMPLEMENTATION_DEFINED must not leak to public API");
        }
    }

    private String formatToString(int i)
    {
        switch(i)
        {
        default:
            return "UNKNOWN";

        case 842094169: 
            return "YV12";

        case 35: // '#'
            return "YUV_420_888";

        case 17: // '\021'
            return "NV21";

        case 16: // '\020'
            return "NV16";

        case 4: // '\004'
            return "RGB_565";

        case 1: // '\001'
            return "RGBA_8888";

        case 2: // '\002'
            return "RGBX_8888";

        case 3: // '\003'
            return "RGB_888";

        case 256: 
            return "JPEG";

        case 20: // '\024'
            return "YUY2";

        case 538982489: 
            return "Y8";

        case 540422489: 
            return "Y16";

        case 32: // ' '
            return "RAW_SENSOR";

        case 36: // '$'
            return "RAW_PRIVATE";

        case 37: // '%'
            return "RAW10";

        case 1144402265: 
            return "DEPTH16";

        case 257: 
            return "DEPTH_POINT_CLOUD";

        case 4098: 
            return "RAW_DEPTH";

        case 34: // '"'
            return "PRIVATE";
        }
    }

    private StreamConfigurationDuration[] getDurations(int i, int j)
    {
        switch(i)
        {
        default:
            throw new IllegalArgumentException("duration was invalid");

        case 0: // '\0'
            StreamConfigurationDuration astreamconfigurationduration[];
            if(j == 4096)
                astreamconfigurationduration = mDepthMinFrameDurations;
            else
                astreamconfigurationduration = mMinFrameDurations;
            return astreamconfigurationduration;

        case 1: // '\001'
            break;
        }
        StreamConfigurationDuration astreamconfigurationduration1[];
        if(j == 4096)
            astreamconfigurationduration1 = mDepthStallDurations;
        else
            astreamconfigurationduration1 = mStallDurations;
        return astreamconfigurationduration1;
    }

    private SparseIntArray getFormatsMap(boolean flag)
    {
        SparseIntArray sparseintarray;
        if(flag)
            sparseintarray = mAllOutputFormats;
        else
            sparseintarray = mInputFormats;
        return sparseintarray;
    }

    private long getInternalFormatDuration(int i, int j, Size size, int k)
    {
        if(!isSupportedInternalConfiguration(i, j, size))
            throw new IllegalArgumentException("size was not supported");
        StreamConfigurationDuration astreamconfigurationduration[] = getDurations(k, j);
        j = 0;
        for(k = astreamconfigurationduration.length; j < k; j++)
        {
            StreamConfigurationDuration streamconfigurationduration = astreamconfigurationduration[j];
            if(streamconfigurationduration.getFormat() == i && streamconfigurationduration.getWidth() == size.getWidth() && streamconfigurationduration.getHeight() == size.getHeight())
                return streamconfigurationduration.getDuration();
        }

        return 0L;
    }

    private Size[] getInternalFormatSizes(int i, int j, boolean flag, boolean flag1)
    {
        int k;
        if(j == 4096 && flag1)
            return new Size[0];
        SparseIntArray sparseintarray;
        if(!flag)
            sparseintarray = mInputFormats;
        else
        if(j == 4096)
            sparseintarray = mDepthOutputFormats;
        else
        if(flag1)
            sparseintarray = mHighResOutputFormats;
        else
            sparseintarray = mOutputFormats;
_L10:
        Size asize[];
        int j1;
        StreamConfiguration streamconfiguration;
        int i2;
        for(k = sparseintarray.get(i); (!flag || j == 4096) && k == 0 || flag && j != 4096 && mAllOutputFormats.get(i) == 0;)
            throw new IllegalArgumentException("format not available");

        asize = new Size[k];
        StreamConfiguration astreamconfiguration[];
        StreamConfigurationDuration astreamconfigurationduration[];
        int l;
        int i1;
        int k1;
        long l1;
        long l2;
        StreamConfigurationDuration streamconfigurationduration;
        if(j == 4096)
            astreamconfiguration = mDepthConfigurations;
        else
            astreamconfiguration = mConfigurations;
        if(j == 4096)
            astreamconfigurationduration = mDepthMinFrameDurations;
        else
            astreamconfigurationduration = mMinFrameDurations;
        l = astreamconfiguration.length;
        i1 = 0;
        j1 = 0;
_L7:
        if(i1 >= l)
            break MISSING_BLOCK_LABEL_376;
        streamconfiguration = astreamconfiguration[i1];
        k1 = streamconfiguration.getFormat();
        if(k1 != i || streamconfiguration.isOutput() != flag) goto _L2; else goto _L1
_L1:
        if(!flag || !mListHighResolution)
            break MISSING_BLOCK_LABEL_353;
        l1 = 0L;
        i2 = 0;
_L8:
        l2 = l1;
        if(i2 >= astreamconfigurationduration.length) goto _L4; else goto _L3
_L3:
        streamconfigurationduration = astreamconfigurationduration[i2];
        if(streamconfigurationduration.getFormat() != k1 || streamconfigurationduration.getWidth() != streamconfiguration.getSize().getWidth() || streamconfigurationduration.getHeight() != streamconfiguration.getSize().getHeight()) goto _L6; else goto _L5
_L5:
        l2 = streamconfigurationduration.getDuration();
_L4:
        if(j == 4096)
            break MISSING_BLOCK_LABEL_353;
        boolean flag2;
        if(l2 > 0x2faf080L)
            flag2 = true;
        else
            flag2 = false;
        if(flag1 == flag2)
            break MISSING_BLOCK_LABEL_353;
_L2:
        i1++;
          goto _L7
_L6:
        i2++;
          goto _L8
        int j2 = j1 + 1;
        asize[j1] = streamconfiguration.getSize();
        j1 = j2;
          goto _L2
        if(j1 != k)
            throw new AssertionError((new StringBuilder()).append("Too few sizes (expected ").append(k).append(", actual ").append(j1).append(")").toString());
        return asize;
        if(true) goto _L10; else goto _L9
_L9:
    }

    private int getPublicFormatCount(boolean flag)
    {
        int i = getFormatsMap(flag).size();
        int j = i;
        if(flag)
            j = i + mDepthOutputFormats.size();
        return j;
    }

    private Size[] getPublicFormatSizes(int i, boolean flag, boolean flag1)
    {
        try
        {
            checkArgumentFormatSupported(i, flag);
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            return null;
        }
        return getInternalFormatSizes(imageFormatToInternal(i), imageFormatToDataspace(i), flag, flag1);
    }

    private int[] getPublicFormats(boolean flag)
    {
        int ai[] = new int[getPublicFormatCount(flag)];
        int i = 0;
        SparseIntArray sparseintarray = getFormatsMap(flag);
        for(int j = 0; j < sparseintarray.size();)
        {
            ai[i] = imageFormatToPublic(sparseintarray.keyAt(j));
            j++;
            i++;
        }

        int k = i;
        if(flag)
        {
            int l = 0;
            do
            {
                k = i;
                if(l >= mDepthOutputFormats.size())
                    break;
                ai[i] = depthFormatToPublic(mDepthOutputFormats.keyAt(l));
                l++;
                i++;
            } while(true);
        }
        if(ai.length != k)
            throw new AssertionError((new StringBuilder()).append("Too few formats ").append(k).append(", expected ").append(ai.length).toString());
        else
            return ai;
    }

    static int imageFormatToDataspace(int i)
    {
        switch(i)
        {
        default:
            return 0;

        case 256: 
            return 0x8c20000;

        case 257: 
        case 4098: 
        case 1144402265: 
            return 4096;
        }
    }

    static int imageFormatToInternal(int i)
    {
        switch(i)
        {
        default:
            return i;

        case 256: 
        case 257: 
            return 33;

        case 1144402265: 
            return 0x20363159;

        case 4098: 
            return 32;
        }
    }

    public static int[] imageFormatToInternal(int ai[])
    {
        if(ai == null)
            return null;
        for(int i = 0; i < ai.length; i++)
            ai[i] = imageFormatToInternal(ai[i]);

        return ai;
    }

    static int imageFormatToPublic(int i)
    {
        switch(i)
        {
        default:
            return i;

        case 33: // '!'
            return 256;

        case 256: 
            throw new IllegalArgumentException("ImageFormat.JPEG is an unknown internal format");
        }
    }

    static int[] imageFormatToPublic(int ai[])
    {
        if(ai == null)
            return null;
        for(int i = 0; i < ai.length; i++)
            ai[i] = imageFormatToPublic(ai[i]);

        return ai;
    }

    public static boolean isOutputSupportedFor(Class class1)
    {
        Preconditions.checkNotNull(class1, "klass must not be null");
        if(class1 == android/media/ImageReader)
            return true;
        if(class1 == android/media/MediaRecorder)
            return true;
        if(class1 == android/media/MediaCodec)
            return true;
        if(class1 == android/renderscript/Allocation)
            return true;
        if(class1 == android/view/SurfaceHolder)
            return true;
        return class1 == android/graphics/SurfaceTexture;
    }

    private boolean isSupportedInternalConfiguration(int i, int j, Size size)
    {
        StreamConfiguration astreamconfiguration[];
        if(j == 4096)
            astreamconfiguration = mDepthConfigurations;
        else
            astreamconfiguration = mConfigurations;
        for(j = 0; j < astreamconfiguration.length; j++)
            if(astreamconfiguration[j].getFormat() == i && astreamconfiguration[j].getSize().equals(size))
                return true;

        return false;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj == null)
            return false;
        if(this == obj)
            return true;
        if(obj instanceof StreamConfigurationMap)
        {
            obj = (StreamConfigurationMap)obj;
            boolean flag1 = flag;
            if(Arrays.equals(mConfigurations, ((StreamConfigurationMap) (obj)).mConfigurations))
            {
                flag1 = flag;
                if(Arrays.equals(mMinFrameDurations, ((StreamConfigurationMap) (obj)).mMinFrameDurations))
                {
                    flag1 = flag;
                    if(Arrays.equals(mStallDurations, ((StreamConfigurationMap) (obj)).mStallDurations))
                    {
                        flag1 = flag;
                        if(Arrays.equals(mDepthConfigurations, ((StreamConfigurationMap) (obj)).mDepthConfigurations))
                            flag1 = Arrays.equals(mHighSpeedVideoConfigurations, ((StreamConfigurationMap) (obj)).mHighSpeedVideoConfigurations);
                    }
                }
            }
            return flag1;
        } else
        {
            return false;
        }
    }

    public Size[] getHighResolutionOutputSizes(int i)
    {
        if(!mListHighResolution)
            return null;
        else
            return getPublicFormatSizes(i, true, true);
    }

    public Range[] getHighSpeedVideoFpsRanges()
    {
        Set set = mHighSpeedVideoFpsRangeMap.keySet();
        return (Range[])set.toArray(new Range[set.size()]);
    }

    public Range[] getHighSpeedVideoFpsRangesFor(Size size)
    {
        int i = 0;
        Integer integer = (Integer)mHighSpeedVideoSizeMap.get(size);
        if(integer == null || integer.intValue() == 0)
            throw new IllegalArgumentException(String.format("Size %s does not support high speed video recording", new Object[] {
                size
            }));
        Range arange[] = new Range[integer.intValue()];
        HighSpeedVideoConfiguration ahighspeedvideoconfiguration[] = mHighSpeedVideoConfigurations;
        int j = ahighspeedvideoconfiguration.length;
        int k = 0;
        for(; i < j; i++)
        {
            HighSpeedVideoConfiguration highspeedvideoconfiguration = ahighspeedvideoconfiguration[i];
            if(size.equals(highspeedvideoconfiguration.getSize()))
            {
                int l = k + 1;
                arange[k] = highspeedvideoconfiguration.getFpsRange();
                k = l;
            }
        }

        return arange;
    }

    public Size[] getHighSpeedVideoSizes()
    {
        Set set = mHighSpeedVideoSizeMap.keySet();
        return (Size[])set.toArray(new Size[set.size()]);
    }

    public Size[] getHighSpeedVideoSizesFor(Range range)
    {
        int i = 0;
        Integer integer = (Integer)mHighSpeedVideoFpsRangeMap.get(range);
        if(integer == null || integer.intValue() == 0)
            throw new IllegalArgumentException(String.format("FpsRange %s does not support high speed video recording", new Object[] {
                range
            }));
        Size asize[] = new Size[integer.intValue()];
        HighSpeedVideoConfiguration ahighspeedvideoconfiguration[] = mHighSpeedVideoConfigurations;
        int j = ahighspeedvideoconfiguration.length;
        int k = 0;
        for(; i < j; i++)
        {
            HighSpeedVideoConfiguration highspeedvideoconfiguration = ahighspeedvideoconfiguration[i];
            if(range.equals(highspeedvideoconfiguration.getFpsRange()))
            {
                int l = k + 1;
                asize[k] = highspeedvideoconfiguration.getSize();
                k = l;
            }
        }

        return asize;
    }

    public final int[] getInputFormats()
    {
        return getPublicFormats(false);
    }

    public Size[] getInputSizes(int i)
    {
        return getPublicFormatSizes(i, false, false);
    }

    public final int[] getOutputFormats()
    {
        return getPublicFormats(true);
    }

    public long getOutputMinFrameDuration(int i, Size size)
    {
        Preconditions.checkNotNull(size, "size must not be null");
        checkArgumentFormatSupported(i, true);
        return getInternalFormatDuration(imageFormatToInternal(i), imageFormatToDataspace(i), size, 0);
    }

    public long getOutputMinFrameDuration(Class class1, Size size)
    {
        if(!isOutputSupportedFor(class1))
            throw new IllegalArgumentException("klass was not supported");
        else
            return getInternalFormatDuration(34, 0, size, 0);
    }

    public Size[] getOutputSizes(int i)
    {
        return getPublicFormatSizes(i, true, false);
    }

    public Size[] getOutputSizes(Class class1)
    {
        if(!isOutputSupportedFor(class1))
            return null;
        else
            return getInternalFormatSizes(34, 0, true, false);
    }

    public long getOutputStallDuration(int i, Size size)
    {
        checkArgumentFormatSupported(i, true);
        return getInternalFormatDuration(imageFormatToInternal(i), imageFormatToDataspace(i), size, 1);
    }

    public long getOutputStallDuration(Class class1, Size size)
    {
        if(!isOutputSupportedFor(class1))
            throw new IllegalArgumentException("klass was not supported");
        else
            return getInternalFormatDuration(34, 0, size, 1);
    }

    public final int[] getValidOutputFormatsForInput(int i)
    {
        if(mInputOutputFormatsMap == null)
            return new int[0];
        else
            return mInputOutputFormatsMap.getOutputs(i);
    }

    public int hashCode()
    {
        return HashCodeHelpers.hashCodeGeneric(((Object []) (new Object[][] {
            mConfigurations, mMinFrameDurations, mStallDurations, mDepthConfigurations, mHighSpeedVideoConfigurations
        })));
    }

    public boolean isOutputSupportedFor(int i)
    {
        boolean flag = true;
        boolean flag1 = true;
        checkArgumentFormat(i);
        int j = imageFormatToInternal(i);
        if(imageFormatToDataspace(i) == 4096)
        {
            if(mDepthOutputFormats.indexOfKey(j) < 0)
                flag1 = false;
            return flag1;
        }
        if(getFormatsMap(true).indexOfKey(j) >= 0)
            flag1 = flag;
        else
            flag1 = false;
        return flag1;
    }

    public boolean isOutputSupportedFor(Surface surface)
    {
        Preconditions.checkNotNull(surface, "surface must not be null");
        Size size = SurfaceUtils.getSurfaceSize(surface);
        int i = SurfaceUtils.getSurfaceFormat(surface);
        int j = SurfaceUtils.getSurfaceDataspace(surface);
        boolean flag = SurfaceUtils.isFlexibleConsumer(surface);
        int l;
        if(j != 4096)
            surface = mConfigurations;
        else
            surface = mDepthConfigurations;
        l = surface.length;
        for(int k = 0; k < l; k++)
        {
            StreamConfiguration streamconfiguration = surface[k];
            if(streamconfiguration.getFormat() != i || !streamconfiguration.isOutput())
                continue;
            if(streamconfiguration.getSize().equals(size))
                return true;
            if(flag && streamconfiguration.getSize().getWidth() <= 1920)
                return true;
        }

        return false;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder("StreamConfiguration(");
        appendOutputsString(stringbuilder);
        stringbuilder.append(", ");
        appendHighResOutputsString(stringbuilder);
        stringbuilder.append(", ");
        appendInputsString(stringbuilder);
        stringbuilder.append(", ");
        appendValidOutputFormatsForInputString(stringbuilder);
        stringbuilder.append(", ");
        appendHighSpeedVideoConfigurationsString(stringbuilder);
        stringbuilder.append(")");
        return stringbuilder.toString();
    }

    private static final long DURATION_20FPS_NS = 0x2faf080L;
    private static final int DURATION_MIN_FRAME = 0;
    private static final int DURATION_STALL = 1;
    private static final int HAL_DATASPACE_DEPTH = 4096;
    private static final int HAL_DATASPACE_RANGE_SHIFT = 27;
    private static final int HAL_DATASPACE_STANDARD_SHIFT = 16;
    private static final int HAL_DATASPACE_TRANSFER_SHIFT = 22;
    private static final int HAL_DATASPACE_UNKNOWN = 0;
    private static final int HAL_DATASPACE_V0_JFIF = 0x8c20000;
    private static final int HAL_PIXEL_FORMAT_BLOB = 33;
    private static final int HAL_PIXEL_FORMAT_IMPLEMENTATION_DEFINED = 34;
    private static final int HAL_PIXEL_FORMAT_RAW10 = 37;
    private static final int HAL_PIXEL_FORMAT_RAW12 = 38;
    private static final int HAL_PIXEL_FORMAT_RAW16 = 32;
    private static final int HAL_PIXEL_FORMAT_RAW_OPAQUE = 36;
    private static final int HAL_PIXEL_FORMAT_Y16 = 0x20363159;
    private static final int HAL_PIXEL_FORMAT_YCbCr_420_888 = 35;
    private static final String TAG = "StreamConfigurationMap";
    private final SparseIntArray mAllOutputFormats;
    private final StreamConfiguration mConfigurations[];
    private final StreamConfiguration mDepthConfigurations[];
    private final StreamConfigurationDuration mDepthMinFrameDurations[];
    private final SparseIntArray mDepthOutputFormats;
    private final StreamConfigurationDuration mDepthStallDurations[];
    private final SparseIntArray mHighResOutputFormats;
    private final HighSpeedVideoConfiguration mHighSpeedVideoConfigurations[];
    private final HashMap mHighSpeedVideoFpsRangeMap;
    private final HashMap mHighSpeedVideoSizeMap;
    private final SparseIntArray mInputFormats;
    private final ReprocessFormatsMap mInputOutputFormatsMap;
    private final boolean mListHighResolution;
    private final StreamConfigurationDuration mMinFrameDurations[];
    private final SparseIntArray mOutputFormats;
    private final StreamConfigurationDuration mStallDurations[];
}
