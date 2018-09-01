// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.legacy;

import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.hardware.Camera;
import android.hardware.CameraInfo;
import android.hardware.camera2.*;
import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.params.*;
import android.hardware.camera2.utils.*;
import android.util.*;
import com.android.internal.util.Preconditions;
import java.util.*;

// Referenced classes of package android.hardware.camera2.legacy:
//            LegacyRequestMapper, ParameterUtils, SizeAreaComparator, LegacyRequest

public class LegacyMetadataMapper
{

    public LegacyMetadataMapper()
    {
    }

    private static void appendStreamConfig(ArrayList arraylist, int i, List list)
    {
        for(Iterator iterator = list.iterator(); iterator.hasNext(); arraylist.add(new StreamConfiguration(i, ((android.hardware.Camera.Size) (list)).width, ((android.hardware.Camera.Size) (list)).height, false)))
            list = (android.hardware.Camera.Size)iterator.next();

    }

    private static long calculateJpegStallDuration(android.hardware.Camera.Size size)
    {
        return 71L * ((long)size.width * (long)size.height) + 0xbebc200L;
    }

    private static int[] convertAeFpsRangeToLegacy(Range range)
    {
        return (new int[] {
            ((Integer)range.getLower()).intValue(), ((Integer)range.getUpper()).intValue()
        });
    }

    static String convertAfModeToLegacy(int i, List list)
    {
        String s;
        if(list == null || list.isEmpty())
        {
            Log.w("LegacyMetadataMapper", "No focus modes supported; API1 bug");
            return null;
        }
        s = null;
        i;
        JVM INSTR tableswitch 0 5: default 64
    //                   0 145
    //                   1 115
    //                   2 139
    //                   3 127
    //                   4 121
    //                   5 133;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7
_L1:
        String s1 = s;
        if(!list.contains(s))
        {
            s1 = (String)list.get(0);
            Log.w("LegacyMetadataMapper", String.format("convertAfModeToLegacy - ignoring unsupported mode %d, defaulting to %s", new Object[] {
                Integer.valueOf(i), s1
            }));
        }
        return s1;
_L3:
        s = "auto";
        continue; /* Loop/switch isn't completed */
_L6:
        s = "continuous-picture";
        continue; /* Loop/switch isn't completed */
_L5:
        s = "continuous-video";
        continue; /* Loop/switch isn't completed */
_L7:
        s = "edof";
        continue; /* Loop/switch isn't completed */
_L4:
        s = "macro";
        continue; /* Loop/switch isn't completed */
_L2:
        if(list.contains("fixed"))
            s = "fixed";
        else
            s = "infinity";
        if(true) goto _L1; else goto _L8
_L8:
    }

    private static int convertAntiBandingMode(String s)
    {
        if(s == null)
            return -1;
        if(s.equals("off"))
            return 0;
        if(s.equals("50hz"))
            return 1;
        if(s.equals("60hz"))
            return 2;
        if(s.equals("auto"))
        {
            return 3;
        } else
        {
            Log.w("LegacyMetadataMapper", (new StringBuilder()).append("convertAntiBandingMode - Unknown antibanding mode ").append(s).toString());
            return -1;
        }
    }

    static int convertAntiBandingModeOrDefault(String s)
    {
        int i = convertAntiBandingMode(s);
        if(i == -1)
            return 0;
        else
            return i;
    }

    static int convertEffectModeFromLegacy(String s)
    {
        if(s == null)
            return 0;
        int i = ArrayUtils.getArrayIndex(sLegacyEffectMode, s);
        if(i < 0)
            return -1;
        else
            return sEffectModes[i];
    }

    static String convertEffectModeToLegacy(int i)
    {
        i = ArrayUtils.getArrayIndex(sEffectModes, i);
        if(i < 0)
            return null;
        else
            return sLegacyEffectMode[i];
    }

    public static void convertRequestMetadata(LegacyRequest legacyrequest)
    {
        LegacyRequestMapper.convertRequestMetadata(legacyrequest);
    }

    static int convertSceneModeFromLegacy(String s)
    {
        if(s == null)
            return 0;
        int i = ArrayUtils.getArrayIndex(sLegacySceneModes, s);
        if(i < 0)
            return -1;
        else
            return sSceneModes[i];
    }

    static String convertSceneModeToLegacy(int i)
    {
        if(i == 1)
            return "auto";
        i = ArrayUtils.getArrayIndex(sSceneModes, i);
        if(i < 0)
            return null;
        else
            return sLegacySceneModes[i];
    }

    public static CameraCharacteristics createCharacteristics(android.hardware.Camera.Parameters parameters, android.hardware.Camera.CameraInfo camerainfo)
    {
        Preconditions.checkNotNull(parameters, "parameters must not be null");
        Preconditions.checkNotNull(camerainfo, "info must not be null");
        String s = parameters.flatten();
        parameters = new CameraInfo();
        parameters.info = camerainfo;
        return createCharacteristics(s, ((CameraInfo) (parameters)));
    }

    public static CameraCharacteristics createCharacteristics(String s, CameraInfo camerainfo)
    {
        Preconditions.checkNotNull(s, "parameters must not be null");
        Preconditions.checkNotNull(camerainfo, "info must not be null");
        Preconditions.checkNotNull(camerainfo.info, "info.info must not be null");
        CameraMetadataNative camerametadatanative = new CameraMetadataNative();
        mapCharacteristicsFromInfo(camerametadatanative, camerainfo.info);
        camerainfo = Camera.getEmptyParameters();
        camerainfo.unflatten(s);
        mapCharacteristicsFromParameters(camerametadatanative, camerainfo);
        return new CameraCharacteristics(camerametadatanative);
    }

    public static CameraMetadataNative createRequestTemplate(CameraCharacteristics cameracharacteristics, int i)
    {
        CameraMetadataNative camerametadatanative;
        if(!ArrayUtils.contains(sAllowedTemplates, i))
            throw new IllegalArgumentException("templateId out of range");
        camerametadatanative = new CameraMetadataNative();
        camerametadatanative.set(CaptureRequest.CONTROL_AWB_MODE, Integer.valueOf(1));
        camerametadatanative.set(CaptureRequest.CONTROL_AE_ANTIBANDING_MODE, Integer.valueOf(3));
        camerametadatanative.set(CaptureRequest.CONTROL_AE_EXPOSURE_COMPENSATION, Integer.valueOf(0));
        camerametadatanative.set(CaptureRequest.CONTROL_AE_LOCK, Boolean.valueOf(false));
        camerametadatanative.set(CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER, Integer.valueOf(0));
        camerametadatanative.set(CaptureRequest.CONTROL_AF_TRIGGER, Integer.valueOf(0));
        camerametadatanative.set(CaptureRequest.CONTROL_AWB_MODE, Integer.valueOf(1));
        camerametadatanative.set(CaptureRequest.CONTROL_AWB_LOCK, Boolean.valueOf(false));
        Rect rect = (Rect)cameracharacteristics.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);
        MeteringRectangle ameteringrectangle[] = new MeteringRectangle[1];
        ameteringrectangle[0] = new MeteringRectangle(0, 0, rect.width() - 1, rect.height() - 1, 0);
        camerametadatanative.set(CaptureRequest.CONTROL_AE_REGIONS, ameteringrectangle);
        camerametadatanative.set(CaptureRequest.CONTROL_AWB_REGIONS, ameteringrectangle);
        camerametadatanative.set(CaptureRequest.CONTROL_AF_REGIONS, ameteringrectangle);
        i;
        JVM INSTR tableswitch 1 3: default 216
    //                   1 227
    //                   2 382
    //                   3 388;
           goto _L1 _L2 _L3 _L4
_L1:
        throw new AssertionError("Impossible; keep in sync with sAllowedTemplates");
_L2:
        int j = 1;
_L9:
        camerametadatanative.set(CaptureRequest.CONTROL_CAPTURE_INTENT, Integer.valueOf(j));
        camerametadatanative.set(CaptureRequest.CONTROL_AE_MODE, Integer.valueOf(1));
        camerametadatanative.set(CaptureRequest.CONTROL_MODE, Integer.valueOf(1));
        Float float1 = (Float)cameracharacteristics.get(CameraCharacteristics.LENS_INFO_MINIMUM_FOCUS_DISTANCE);
        Range arange[];
        int k;
        if(float1 != null && float1.floatValue() == 0.0F)
        {
            j = 0;
        } else
        {
label0:
            {
                flag = true;
                if(i != 3 && i != 4)
                    break label0;
                j = ((flag) ? 1 : 0);
                if(ArrayUtils.contains((int[])cameracharacteristics.get(CameraCharacteristics.CONTROL_AF_AVAILABLE_MODES), 3))
                    j = 3;
            }
        }
          goto _L5
_L3:
        j = 2;
        continue; /* Loop/switch isn't completed */
_L4:
        j = 3;
        continue; /* Loop/switch isn't completed */
        if(i == 1) goto _L7; else goto _L6
_L6:
        j = ((flag) ? 1 : 0);
        if(i != 2) goto _L5; else goto _L7
_L7:
        j = ((flag) ? 1 : 0);
        if(ArrayUtils.contains((int[])cameracharacteristics.get(CameraCharacteristics.CONTROL_AF_AVAILABLE_MODES), 4))
            j = 4;
_L5:
        camerametadatanative.set(CaptureRequest.CONTROL_AF_MODE, Integer.valueOf(j));
        arange = (Range[])cameracharacteristics.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES);
        Range range = arange[0];
        k = arange.length;
        j = 0;
        while(j < k) 
        {
            Range range2 = arange[j];
            Range range1;
            boolean flag;
            if(((Integer)range.getUpper()).intValue() < ((Integer)range2.getUpper()).intValue())
            {
                range1 = range2;
            } else
            {
                range1 = range;
                if(range.getUpper() == range2.getUpper())
                {
                    range1 = range;
                    if(((Integer)range.getLower()).intValue() < ((Integer)range2.getLower()).intValue())
                        range1 = range2;
                }
            }
            j++;
            range = range1;
        }
        camerametadatanative.set(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE, range);
        camerametadatanative.set(CaptureRequest.CONTROL_SCENE_MODE, Integer.valueOf(0));
        camerametadatanative.set(CaptureRequest.STATISTICS_FACE_DETECT_MODE, Integer.valueOf(0));
        camerametadatanative.set(CaptureRequest.FLASH_MODE, Integer.valueOf(0));
        android.hardware.camera2.CaptureRequest.Key key;
        if(i == 2)
            camerametadatanative.set(CaptureRequest.NOISE_REDUCTION_MODE, Integer.valueOf(2));
        else
            camerametadatanative.set(CaptureRequest.NOISE_REDUCTION_MODE, Integer.valueOf(1));
        if(i == 2)
            camerametadatanative.set(CaptureRequest.COLOR_CORRECTION_ABERRATION_MODE, Integer.valueOf(2));
        else
            camerametadatanative.set(CaptureRequest.COLOR_CORRECTION_ABERRATION_MODE, Integer.valueOf(1));
        camerametadatanative.set(CaptureRequest.LENS_FOCAL_LENGTH, Float.valueOf(((float[])cameracharacteristics.get(CameraCharacteristics.LENS_INFO_AVAILABLE_FOCAL_LENGTHS))[0]));
        cameracharacteristics = (android.util.Size[])cameracharacteristics.get(CameraCharacteristics.JPEG_AVAILABLE_THUMBNAIL_SIZES);
        key = CaptureRequest.JPEG_THUMBNAIL_SIZE;
        if(cameracharacteristics.length > 1)
            cameracharacteristics = cameracharacteristics[1];
        else
            cameracharacteristics = cameracharacteristics[0];
        camerametadatanative.set(key, cameracharacteristics);
        return camerametadatanative;
        if(true) goto _L9; else goto _L8
_L8:
    }

    private static int[] getTagsForKeys(android.hardware.camera2.CameraCharacteristics.Key akey[])
    {
        int ai[] = new int[akey.length];
        for(int i = 0; i < akey.length; i++)
            ai[i] = akey[i].getNativeKey().getTag();

        return ai;
    }

    private static int[] getTagsForKeys(android.hardware.camera2.CaptureRequest.Key akey[])
    {
        int ai[] = new int[akey.length];
        for(int i = 0; i < akey.length; i++)
            ai[i] = akey[i].getNativeKey().getTag();

        return ai;
    }

    private static int[] getTagsForKeys(android.hardware.camera2.CaptureResult.Key akey[])
    {
        int ai[] = new int[akey.length];
        for(int i = 0; i < akey.length; i++)
            ai[i] = akey[i].getNativeKey().getTag();

        return ai;
    }

    private static void mapCharacteristicsFromInfo(CameraMetadataNative camerametadatanative, android.hardware.Camera.CameraInfo camerainfo)
    {
        int i = 0;
        android.hardware.camera2.CameraCharacteristics.Key key = CameraCharacteristics.LENS_FACING;
        if(camerainfo.facing == 0)
            i = 1;
        camerametadatanative.set(key, Integer.valueOf(i));
        camerametadatanative.set(CameraCharacteristics.SENSOR_ORIENTATION, Integer.valueOf(camerainfo.orientation));
    }

    private static void mapCharacteristicsFromParameters(CameraMetadataNative camerametadatanative, android.hardware.Camera.Parameters parameters)
    {
        camerametadatanative.set(CameraCharacteristics.COLOR_CORRECTION_AVAILABLE_ABERRATION_MODES, new int[] {
            1, 2
        });
        mapControlAe(camerametadatanative, parameters);
        mapControlAf(camerametadatanative, parameters);
        mapControlAwb(camerametadatanative, parameters);
        mapControlOther(camerametadatanative, parameters);
        mapLens(camerametadatanative, parameters);
        mapFlash(camerametadatanative, parameters);
        mapJpeg(camerametadatanative, parameters);
        camerametadatanative.set(CameraCharacteristics.NOISE_REDUCTION_AVAILABLE_NOISE_REDUCTION_MODES, new int[] {
            1, 2
        });
        mapScaler(camerametadatanative, parameters);
        mapSensor(camerametadatanative, parameters);
        mapStatistics(camerametadatanative, parameters);
        mapSync(camerametadatanative, parameters);
        camerametadatanative.set(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL, Integer.valueOf(2));
        mapScalerStreamConfigs(camerametadatanative, parameters);
        mapRequest(camerametadatanative, parameters);
    }

    private static void mapControlAe(CameraMetadataNative camerametadatanative, android.hardware.Camera.Parameters parameters)
    {
        Object aobj[];
label0:
        {
            Object obj = parameters.getSupportedAntibanding();
            if(obj != null && ((List) (obj)).size() > 0)
            {
                int ai2[] = new int[((List) (obj)).size()];
                int i = 0;
                for(obj = ((Iterable) (obj)).iterator(); ((Iterator) (obj)).hasNext();)
                {
                    ai2[i] = convertAntiBandingMode((String)((Iterator) (obj)).next());
                    i++;
                }

                camerametadatanative.set(CameraCharacteristics.CONTROL_AE_AVAILABLE_ANTIBANDING_MODES, Arrays.copyOf(ai2, i));
            } else
            {
                camerametadatanative.set(CameraCharacteristics.CONTROL_AE_AVAILABLE_ANTIBANDING_MODES, new int[0]);
            }
            obj = parameters.getSupportedPreviewFpsRange();
            if(obj == null)
                throw new AssertionError("Supported FPS ranges cannot be null.");
            int j = ((List) (obj)).size();
            if(j <= 0)
                throw new AssertionError("At least one FPS range must be supported.");
            aobj = new Range[j];
            j = 0;
            for(Iterator iterator = ((Iterable) (obj)).iterator(); iterator.hasNext();)
            {
                int ai[] = (int[])iterator.next();
                aobj[j] = Range.create(Integer.valueOf((int)Math.floor((double)ai[0] / 1000D)), Integer.valueOf((int)Math.ceil((double)ai[1] / 1000D)));
                j++;
            }

            camerametadatanative.set(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES, ((Object) (aobj)));
            int ai1[] = ArrayUtils.convertStringListToIntArray(parameters.getSupportedFlashModes(), new String[] {
                "off", "auto", "on", "red-eye", "torch"
            }, new int[] {
                1, 2, 3, 4
            });
            if(ai1 != null)
            {
                aobj = ai1;
                if(ai1.length != 0)
                    break label0;
            }
            aobj = new int[1];
            aobj[0] = 1;
        }
        camerametadatanative.set(CameraCharacteristics.CONTROL_AE_AVAILABLE_MODES, ((Object) (aobj)));
        int k = parameters.getMinExposureCompensation();
        int l = parameters.getMaxExposureCompensation();
        camerametadatanative.set(CameraCharacteristics.CONTROL_AE_COMPENSATION_RANGE, Range.create(Integer.valueOf(k), Integer.valueOf(l)));
        float f = parameters.getExposureCompensationStep();
        camerametadatanative.set(CameraCharacteristics.CONTROL_AE_COMPENSATION_STEP, ParamsUtils.createRational(f));
        boolean flag = parameters.isAutoExposureLockSupported();
        camerametadatanative.set(CameraCharacteristics.CONTROL_AE_LOCK_AVAILABLE, Boolean.valueOf(flag));
    }

    private static void mapControlAf(CameraMetadataNative camerametadatanative, android.hardware.Camera.Parameters parameters)
    {
label0:
        {
            List list = ArrayUtils.convertStringListToIntList(parameters.getSupportedFocusModes(), new String[] {
                "auto", "continuous-picture", "continuous-video", "edof", "infinity", "macro", "fixed"
            }, new int[] {
                1, 4, 3, 5, 0, 2, 0
            });
            if(list != null)
            {
                parameters = list;
                if(list.size() != 0)
                    break label0;
            }
            Log.w("LegacyMetadataMapper", "No AF modes supported (HAL bug); defaulting to AF_MODE_OFF only");
            parameters = new ArrayList(1);
            parameters.add(Integer.valueOf(0));
        }
        camerametadatanative.set(CameraCharacteristics.CONTROL_AF_AVAILABLE_MODES, ArrayUtils.toIntArray(parameters));
    }

    private static void mapControlAwb(CameraMetadataNative camerametadatanative, android.hardware.Camera.Parameters parameters)
    {
        Object obj;
label0:
        {
            List list = ArrayUtils.convertStringListToIntList(parameters.getSupportedWhiteBalance(), new String[] {
                "auto", "incandescent", "fluorescent", "warm-fluorescent", "daylight", "cloudy-daylight", "twilight", "shade"
            }, new int[] {
                1, 2, 3, 4, 5, 6, 7, 8
            });
            if(list != null)
            {
                obj = list;
                if(list.size() != 0)
                    break label0;
            }
            Log.w("LegacyMetadataMapper", "No AWB modes supported (HAL bug); defaulting to AWB_MODE_AUTO only");
            obj = new ArrayList(1);
            ((List) (obj)).add(Integer.valueOf(1));
        }
        camerametadatanative.set(CameraCharacteristics.CONTROL_AWB_AVAILABLE_MODES, ArrayUtils.toIntArray(((List) (obj))));
        boolean flag = parameters.isAutoWhiteBalanceLockSupported();
        camerametadatanative.set(CameraCharacteristics.CONTROL_AWB_LOCK_AVAILABLE, Boolean.valueOf(flag));
    }

    private static void mapControlOther(CameraMetadataNative camerametadatanative, android.hardware.Camera.Parameters parameters)
    {
        Object obj;
        int i;
        int j;
        boolean flag;
        int k;
        List list;
        if(parameters.isVideoStabilizationSupported())
        {
            obj = (new int[] {
                0, 1
            });
        } else
        {
            obj = new int[1];
            obj[0] = 0;
        }
        camerametadatanative.set(CameraCharacteristics.CONTROL_AVAILABLE_VIDEO_STABILIZATION_MODES, obj);
        i = parameters.getMaxNumMeteringAreas();
        j = parameters.getMaxNumFocusAreas();
        camerametadatanative.set(CameraCharacteristics.CONTROL_MAX_REGIONS, new int[] {
            i, 0, j
        });
        obj = parameters.getSupportedColorEffects();
        if(obj == null)
            obj = new int[0];
        else
            obj = ArrayUtils.convertStringListToIntArray(((List) (obj)), sLegacyEffectMode, sEffectModes);
        camerametadatanative.set(CameraCharacteristics.CONTROL_AVAILABLE_EFFECTS, obj);
        k = parameters.getMaxNumDetectedFaces();
        list = parameters.getSupportedSceneModes();
        obj = ArrayUtils.convertStringListToIntList(list, sLegacySceneModes, sSceneModes);
        parameters = ((android.hardware.Camera.Parameters) (obj));
        if(list != null)
        {
            parameters = ((android.hardware.Camera.Parameters) (obj));
            if(list.size() == 1)
            {
                parameters = ((android.hardware.Camera.Parameters) (obj));
                if(((String)list.get(0)).equals("auto"))
                    parameters = null;
            }
        }
        i = 1;
        flag = i;
        if(parameters == null)
        {
            flag = i;
            if(k == 0)
                flag = false;
        }
        if(flag)
        {
            obj = parameters;
            if(parameters == null)
                obj = new ArrayList();
            if(k > 0)
                ((List) (obj)).add(Integer.valueOf(1));
            if(((List) (obj)).contains(Integer.valueOf(0)))
                while(((List) (obj)).remove(new Integer(0))) ;
            camerametadatanative.set(CameraCharacteristics.CONTROL_AVAILABLE_SCENE_MODES, ArrayUtils.toIntArray(((List) (obj))));
        } else
        {
            camerametadatanative.set(CameraCharacteristics.CONTROL_AVAILABLE_SCENE_MODES, new int[] {
                0
            });
        }
        obj = CameraCharacteristics.CONTROL_AVAILABLE_MODES;
        if(flag)
        {
            parameters = (new int[] {
                1, 2
            });
        } else
        {
            parameters = new int[1];
            parameters[0] = 1;
        }
        camerametadatanative.set(((android.hardware.camera2.CameraCharacteristics.Key) (obj)), parameters);
    }

    private static void mapFlash(CameraMetadataNative camerametadatanative, android.hardware.Camera.Parameters parameters)
    {
        boolean flag = false;
        parameters = parameters.getSupportedFlashModes();
        if(parameters != null)
            flag = ListUtils.listElementsEqualTo(parameters, "off") ^ true;
        camerametadatanative.set(CameraCharacteristics.FLASH_INFO_AVAILABLE, Boolean.valueOf(flag));
    }

    private static void mapJpeg(CameraMetadataNative camerametadatanative, android.hardware.Camera.Parameters parameters)
    {
        parameters = parameters.getSupportedJpegThumbnailSizes();
        if(parameters != null)
        {
            parameters = ParameterUtils.convertSizeListToArray(parameters);
            Arrays.sort(parameters, new SizeAreaComparator());
            camerametadatanative.set(CameraCharacteristics.JPEG_AVAILABLE_THUMBNAIL_SIZES, parameters);
        }
    }

    private static void mapLens(CameraMetadataNative camerametadatanative, android.hardware.Camera.Parameters parameters)
    {
        if("fixed".equals(parameters.getFocusMode()))
            camerametadatanative.set(CameraCharacteristics.LENS_INFO_MINIMUM_FOCUS_DISTANCE, Float.valueOf(0.0F));
        float f = parameters.getFocalLength();
        camerametadatanative.set(CameraCharacteristics.LENS_INFO_AVAILABLE_FOCAL_LENGTHS, new float[] {
            f
        });
    }

    private static void mapRequest(CameraMetadataNative camerametadatanative, android.hardware.Camera.Parameters parameters)
    {
        camerametadatanative.set(CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES, new int[] {
            0
        });
        ArrayList arraylist = new ArrayList(Arrays.asList(new android.hardware.camera2.CameraCharacteristics.Key[] {
            CameraCharacteristics.COLOR_CORRECTION_AVAILABLE_ABERRATION_MODES, CameraCharacteristics.CONTROL_AE_AVAILABLE_ANTIBANDING_MODES, CameraCharacteristics.CONTROL_AE_AVAILABLE_MODES, CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES, CameraCharacteristics.CONTROL_AE_COMPENSATION_RANGE, CameraCharacteristics.CONTROL_AE_COMPENSATION_STEP, CameraCharacteristics.CONTROL_AE_LOCK_AVAILABLE, CameraCharacteristics.CONTROL_AF_AVAILABLE_MODES, CameraCharacteristics.CONTROL_AVAILABLE_EFFECTS, CameraCharacteristics.CONTROL_AVAILABLE_MODES, 
            CameraCharacteristics.CONTROL_AVAILABLE_SCENE_MODES, CameraCharacteristics.CONTROL_AVAILABLE_VIDEO_STABILIZATION_MODES, CameraCharacteristics.CONTROL_AWB_AVAILABLE_MODES, CameraCharacteristics.CONTROL_AWB_LOCK_AVAILABLE, CameraCharacteristics.CONTROL_MAX_REGIONS, CameraCharacteristics.FLASH_INFO_AVAILABLE, CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL, CameraCharacteristics.JPEG_AVAILABLE_THUMBNAIL_SIZES, CameraCharacteristics.LENS_FACING, CameraCharacteristics.LENS_INFO_AVAILABLE_FOCAL_LENGTHS, 
            CameraCharacteristics.NOISE_REDUCTION_AVAILABLE_NOISE_REDUCTION_MODES, CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES, CameraCharacteristics.REQUEST_MAX_NUM_OUTPUT_STREAMS, CameraCharacteristics.REQUEST_PARTIAL_RESULT_COUNT, CameraCharacteristics.REQUEST_PIPELINE_MAX_DEPTH, CameraCharacteristics.SCALER_AVAILABLE_MAX_DIGITAL_ZOOM, CameraCharacteristics.SCALER_CROPPING_TYPE, CameraCharacteristics.SENSOR_AVAILABLE_TEST_PATTERN_MODES, CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE, CameraCharacteristics.SENSOR_INFO_PHYSICAL_SIZE, 
            CameraCharacteristics.SENSOR_INFO_PIXEL_ARRAY_SIZE, CameraCharacteristics.SENSOR_INFO_TIMESTAMP_SOURCE, CameraCharacteristics.SENSOR_ORIENTATION, CameraCharacteristics.STATISTICS_INFO_AVAILABLE_FACE_DETECT_MODES, CameraCharacteristics.STATISTICS_INFO_MAX_FACE_COUNT, CameraCharacteristics.SYNC_MAX_LATENCY
        }));
        if(camerametadatanative.get(CameraCharacteristics.LENS_INFO_MINIMUM_FOCUS_DISTANCE) != null)
            arraylist.add(CameraCharacteristics.LENS_INFO_MINIMUM_FOCUS_DISTANCE);
        camerametadatanative.set(CameraCharacteristics.REQUEST_AVAILABLE_CHARACTERISTICS_KEYS, getTagsForKeys((android.hardware.camera2.CameraCharacteristics.Key[])arraylist.toArray(new android.hardware.camera2.CameraCharacteristics.Key[0])));
        ArrayList arraylist1 = new ArrayList(Arrays.asList(new android.hardware.camera2.CaptureRequest.Key[] {
            CaptureRequest.COLOR_CORRECTION_ABERRATION_MODE, CaptureRequest.CONTROL_AE_ANTIBANDING_MODE, CaptureRequest.CONTROL_AE_EXPOSURE_COMPENSATION, CaptureRequest.CONTROL_AE_LOCK, CaptureRequest.CONTROL_AE_MODE, CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE, CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_TRIGGER, CaptureRequest.CONTROL_AWB_LOCK, CaptureRequest.CONTROL_AWB_MODE, 
            CaptureRequest.CONTROL_CAPTURE_INTENT, CaptureRequest.CONTROL_EFFECT_MODE, CaptureRequest.CONTROL_MODE, CaptureRequest.CONTROL_SCENE_MODE, CaptureRequest.CONTROL_VIDEO_STABILIZATION_MODE, CaptureRequest.FLASH_MODE, CaptureRequest.JPEG_GPS_COORDINATES, CaptureRequest.JPEG_GPS_PROCESSING_METHOD, CaptureRequest.JPEG_GPS_TIMESTAMP, CaptureRequest.JPEG_ORIENTATION, 
            CaptureRequest.JPEG_QUALITY, CaptureRequest.JPEG_THUMBNAIL_QUALITY, CaptureRequest.JPEG_THUMBNAIL_SIZE, CaptureRequest.LENS_FOCAL_LENGTH, CaptureRequest.NOISE_REDUCTION_MODE, CaptureRequest.SCALER_CROP_REGION, CaptureRequest.STATISTICS_FACE_DETECT_MODE
        }));
        if(parameters.getMaxNumMeteringAreas() > 0)
            arraylist1.add(CaptureRequest.CONTROL_AE_REGIONS);
        if(parameters.getMaxNumFocusAreas() > 0)
            arraylist1.add(CaptureRequest.CONTROL_AF_REGIONS);
        android.hardware.camera2.CaptureRequest.Key akey[] = new android.hardware.camera2.CaptureRequest.Key[arraylist1.size()];
        arraylist1.toArray(akey);
        camerametadatanative.set(CameraCharacteristics.REQUEST_AVAILABLE_REQUEST_KEYS, getTagsForKeys(akey));
        akey = new ArrayList(Arrays.asList(new android.hardware.camera2.CaptureResult.Key[] {
            CaptureResult.COLOR_CORRECTION_ABERRATION_MODE, CaptureResult.CONTROL_AE_ANTIBANDING_MODE, CaptureResult.CONTROL_AE_EXPOSURE_COMPENSATION, CaptureResult.CONTROL_AE_LOCK, CaptureResult.CONTROL_AE_MODE, CaptureResult.CONTROL_AF_MODE, CaptureResult.CONTROL_AF_STATE, CaptureResult.CONTROL_AWB_MODE, CaptureResult.CONTROL_AWB_LOCK, CaptureResult.CONTROL_MODE, 
            CaptureResult.FLASH_MODE, CaptureResult.JPEG_GPS_COORDINATES, CaptureResult.JPEG_GPS_PROCESSING_METHOD, CaptureResult.JPEG_GPS_TIMESTAMP, CaptureResult.JPEG_ORIENTATION, CaptureResult.JPEG_QUALITY, CaptureResult.JPEG_THUMBNAIL_QUALITY, CaptureResult.LENS_FOCAL_LENGTH, CaptureResult.NOISE_REDUCTION_MODE, CaptureResult.REQUEST_PIPELINE_DEPTH, 
            CaptureResult.SCALER_CROP_REGION, CaptureResult.SENSOR_TIMESTAMP, CaptureResult.STATISTICS_FACE_DETECT_MODE
        }));
        if(parameters.getMaxNumMeteringAreas() > 0)
            akey.add(CaptureResult.CONTROL_AE_REGIONS);
        if(parameters.getMaxNumFocusAreas() > 0)
            akey.add(CaptureResult.CONTROL_AF_REGIONS);
        parameters = new android.hardware.camera2.CaptureResult.Key[akey.size()];
        akey.toArray(parameters);
        camerametadatanative.set(CameraCharacteristics.REQUEST_AVAILABLE_RESULT_KEYS, getTagsForKeys(parameters));
        camerametadatanative.set(CameraCharacteristics.REQUEST_MAX_NUM_OUTPUT_STREAMS, new int[] {
            0, 3, 1
        });
        camerametadatanative.set(CameraCharacteristics.REQUEST_MAX_NUM_INPUT_STREAMS, Integer.valueOf(0));
        camerametadatanative.set(CameraCharacteristics.REQUEST_PARTIAL_RESULT_COUNT, Integer.valueOf(1));
        camerametadatanative.set(CameraCharacteristics.REQUEST_PIPELINE_MAX_DEPTH, Byte.valueOf((byte)6));
    }

    private static void mapScaler(CameraMetadataNative camerametadatanative, android.hardware.Camera.Parameters parameters)
    {
        camerametadatanative.set(CameraCharacteristics.SCALER_AVAILABLE_MAX_DIGITAL_ZOOM, Float.valueOf(ParameterUtils.getMaxZoomRatio(parameters)));
        camerametadatanative.set(CameraCharacteristics.SCALER_CROPPING_TYPE, Integer.valueOf(0));
    }

    private static void mapScalerStreamConfigs(CameraMetadataNative camerametadatanative, android.hardware.Camera.Parameters parameters)
    {
        ArrayList arraylist = new ArrayList();
        Object obj = parameters.getSupportedPreviewSizes();
        List list = parameters.getSupportedPictureSizes();
        android.hardware.camera2.legacy.SizeAreaComparator sizeareacomparator = new android.hardware.camera2.legacy.SizeAreaComparator();
        Collections.sort(((List) (obj)), sizeareacomparator);
        Object obj1 = SizeAreaComparator.findLargestByArea(list);
        float f = ((float)((android.hardware.Camera.Size) (obj1)).width * 1.0F) / (float)((android.hardware.Camera.Size) (obj1)).height;
        do
        {
            if(((List) (obj)).isEmpty())
                break;
            int i = ((List) (obj)).size() - 1;
            obj1 = (android.hardware.Camera.Size)((List) (obj)).get(i);
            if(Math.abs(f - ((float)((android.hardware.Camera.Size) (obj1)).width * 1.0F) / (float)((android.hardware.Camera.Size) (obj1)).height) < 0.01F)
                break;
            ((List) (obj)).remove(i);
        } while(true);
        obj1 = obj;
        if(((List) (obj)).isEmpty())
        {
            Log.w("LegacyMetadataMapper", (new StringBuilder()).append("mapScalerStreamConfigs - failed to find any preview size matching JPEG aspect ratio ").append(f).toString());
            obj1 = parameters.getSupportedPreviewSizes();
        }
        Collections.sort(((List) (obj1)), Collections.reverseOrder(sizeareacomparator));
        appendStreamConfig(arraylist, 34, ((List) (obj1)));
        appendStreamConfig(arraylist, 35, ((List) (obj1)));
        obj = parameters.getSupportedPreviewFormats().iterator();
        do
        {
            if(!((Iterator) (obj)).hasNext())
                break;
            int j = ((Integer)((Iterator) (obj)).next()).intValue();
            if(ImageFormat.isPublicFormat(j) && j != 17)
                appendStreamConfig(arraylist, j, ((List) (obj1)));
        } while(true);
        appendStreamConfig(arraylist, 33, parameters.getSupportedPictureSizes());
        camerametadatanative.set(CameraCharacteristics.SCALER_AVAILABLE_STREAM_CONFIGURATIONS, (StreamConfiguration[])arraylist.toArray(new StreamConfiguration[0]));
        camerametadatanative.set(CameraCharacteristics.SCALER_AVAILABLE_MIN_FRAME_DURATIONS, new StreamConfigurationDuration[0]);
        parameters = new StreamConfigurationDuration[list.size()];
        int k = 0;
        long l = -1L;
        for(Iterator iterator = list.iterator(); iterator.hasNext();)
        {
            android.hardware.Camera.Size size = (android.hardware.Camera.Size)iterator.next();
            long l1 = calculateJpegStallDuration(size);
            parameters[k] = new StreamConfigurationDuration(33, size.width, size.height, l1);
            long l2 = l;
            if(l < l1)
                l2 = l1;
            k++;
            l = l2;
        }

        camerametadatanative.set(CameraCharacteristics.SCALER_AVAILABLE_STALL_DURATIONS, parameters);
        camerametadatanative.set(CameraCharacteristics.SENSOR_INFO_MAX_FRAME_DURATION, Long.valueOf(l));
    }

    private static void mapSensor(CameraMetadataNative camerametadatanative, android.hardware.Camera.Parameters parameters)
    {
        android.util.Size size = ParameterUtils.getLargestSupportedJpegSizeByArea(parameters);
        Rect rect = ParamsUtils.createRect(size);
        camerametadatanative.set(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE, rect);
        camerametadatanative.set(CameraCharacteristics.SENSOR_AVAILABLE_TEST_PATTERN_MODES, new int[] {
            0
        });
        camerametadatanative.set(CameraCharacteristics.SENSOR_INFO_PIXEL_ARRAY_SIZE, size);
        float f = parameters.getFocalLength();
        double d = ((double)parameters.getHorizontalViewAngle() * 3.1415926535897931D) / 180D;
        double d1 = ((double)parameters.getVerticalViewAngle() * 3.1415926535897931D) / 180D;
        float f1 = (float)Math.abs((double)(2.0F * f) * Math.tan(d1 / 2D));
        f = (float)Math.abs((double)(2.0F * f) * Math.tan(d / 2D));
        camerametadatanative.set(CameraCharacteristics.SENSOR_INFO_PHYSICAL_SIZE, new SizeF(f, f1));
        camerametadatanative.set(CameraCharacteristics.SENSOR_INFO_TIMESTAMP_SOURCE, Integer.valueOf(0));
    }

    private static void mapStatistics(CameraMetadataNative camerametadatanative, android.hardware.Camera.Parameters parameters)
    {
        int ai[];
        if(parameters.getMaxNumDetectedFaces() > 0)
        {
            ai = (new int[] {
                0, 1
            });
        } else
        {
            ai = new int[1];
            ai[0] = 0;
        }
        camerametadatanative.set(CameraCharacteristics.STATISTICS_INFO_AVAILABLE_FACE_DETECT_MODES, ai);
        camerametadatanative.set(CameraCharacteristics.STATISTICS_INFO_MAX_FACE_COUNT, Integer.valueOf(parameters.getMaxNumDetectedFaces()));
    }

    private static void mapSync(CameraMetadataNative camerametadatanative, android.hardware.Camera.Parameters parameters)
    {
        camerametadatanative.set(CameraCharacteristics.SYNC_MAX_LATENCY, Integer.valueOf(-1));
    }

    private static final long APPROXIMATE_CAPTURE_DELAY_MS = 200L;
    private static final long APPROXIMATE_JPEG_ENCODE_TIME_MS = 600L;
    private static final long APPROXIMATE_SENSOR_AREA_PX = 0x800000L;
    private static final boolean DEBUG = false;
    public static final int HAL_PIXEL_FORMAT_BGRA_8888 = 5;
    public static final int HAL_PIXEL_FORMAT_BLOB = 33;
    public static final int HAL_PIXEL_FORMAT_IMPLEMENTATION_DEFINED = 34;
    public static final int HAL_PIXEL_FORMAT_RGBA_8888 = 1;
    private static final float LENS_INFO_MINIMUM_FOCUS_DISTANCE_FIXED_FOCUS = 0F;
    static final boolean LIE_ABOUT_AE_MAX_REGIONS = false;
    static final boolean LIE_ABOUT_AE_STATE = false;
    static final boolean LIE_ABOUT_AF = false;
    static final boolean LIE_ABOUT_AF_MAX_REGIONS = false;
    static final boolean LIE_ABOUT_AWB = false;
    static final boolean LIE_ABOUT_AWB_STATE = false;
    private static final long NS_PER_MS = 0xf4240L;
    private static final float PREVIEW_ASPECT_RATIO_TOLERANCE = 0.01F;
    private static final int REQUEST_MAX_NUM_INPUT_STREAMS_COUNT = 0;
    private static final int REQUEST_MAX_NUM_OUTPUT_STREAMS_COUNT_PROC = 3;
    private static final int REQUEST_MAX_NUM_OUTPUT_STREAMS_COUNT_PROC_STALL = 1;
    private static final int REQUEST_MAX_NUM_OUTPUT_STREAMS_COUNT_RAW = 0;
    private static final int REQUEST_PIPELINE_MAX_DEPTH_HAL1 = 3;
    private static final int REQUEST_PIPELINE_MAX_DEPTH_OURS = 3;
    private static final String TAG = "LegacyMetadataMapper";
    static final int UNKNOWN_MODE = -1;
    private static final int sAllowedTemplates[] = {
        1, 2, 3
    };
    private static final int sEffectModes[] = {
        0, 1, 2, 3, 4, 5, 6, 7, 8
    };
    private static final String sLegacyEffectMode[] = {
        "none", "mono", "negative", "solarize", "sepia", "posterize", "whiteboard", "blackboard", "aqua"
    };
    private static final String sLegacySceneModes[] = {
        "auto", "action", "portrait", "landscape", "night", "night-portrait", "theatre", "beach", "snow", "sunset", 
        "steadyphoto", "fireworks", "sports", "party", "candlelight", "barcode", "hdr"
    };
    private static final int sSceneModes[] = {
        0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 
        11, 12, 13, 14, 15, 16, 18
    };

}
