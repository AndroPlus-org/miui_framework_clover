// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.legacy;

import android.graphics.Rect;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.MeteringRectangle;
import android.hardware.camera2.utils.ListUtils;
import android.hardware.camera2.utils.ParamsUtils;
import android.location.Location;
import android.util.*;
import java.util.*;

// Referenced classes of package android.hardware.camera2.legacy:
//            ParameterUtils, LegacyRequest, LegacyMetadataMapper

public class LegacyRequestMapper
{

    public LegacyRequestMapper()
    {
    }

    private static boolean checkForCompleteGpsData(Location location)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(location != null)
        {
            flag1 = flag;
            if(location.getProvider() != null)
            {
                flag1 = flag;
                if(location.getTime() != 0L)
                    flag1 = true;
            }
        }
        return flag1;
    }

    private static String convertAeAntiBandingModeToLegacy(int i)
    {
        switch(i)
        {
        default:
            return null;

        case 0: // '\0'
            return "off";

        case 1: // '\001'
            return "50hz";

        case 2: // '\002'
            return "60hz";

        case 3: // '\003'
            return "auto";
        }
    }

    private static int[] convertAeFpsRangeToLegacy(Range range)
    {
        return (new int[] {
            ((Integer)range.getLower()).intValue() * 1000, ((Integer)range.getUpper()).intValue() * 1000
        });
    }

    private static String convertAwbModeToLegacy(int i)
    {
        switch(i)
        {
        default:
            Log.w("LegacyRequestMapper", (new StringBuilder()).append("convertAwbModeToLegacy - unrecognized control.awbMode").append(i).toString());
            return "auto";

        case 1: // '\001'
            return "auto";

        case 2: // '\002'
            return "incandescent";

        case 3: // '\003'
            return "fluorescent";

        case 4: // '\004'
            return "warm-fluorescent";

        case 5: // '\005'
            return "daylight";

        case 6: // '\006'
            return "cloudy-daylight";

        case 7: // '\007'
            return "twilight";

        case 8: // '\b'
            return "shade";
        }
    }

    private static List convertMeteringRegionsToLegacy(Rect rect, ParameterUtils.ZoomData zoomdata, MeteringRectangle ameteringrectangle[], int i, String s)
    {
        if(ameteringrectangle == null || i <= 0)
            if(i > 0)
                return Arrays.asList(new android.hardware.Camera.Area[] {
                    ParameterUtils.CAMERA_AREA_DEFAULT
                });
            else
                return null;
        ArrayList arraylist = new ArrayList();
        int j = ameteringrectangle.length;
        for(int k = 0; k < j; k++)
        {
            MeteringRectangle meteringrectangle = ameteringrectangle[k];
            if(meteringrectangle.getMeteringWeight() != 0)
                arraylist.add(meteringrectangle);
        }

        if(arraylist.size() == 0)
        {
            Log.w("LegacyRequestMapper", "Only received metering rectangles with weight 0.");
            return Arrays.asList(new android.hardware.Camera.Area[] {
                ParameterUtils.CAMERA_AREA_DEFAULT
            });
        }
        j = Math.min(i, arraylist.size());
        ameteringrectangle = new ArrayList(j);
        for(int l = 0; l < j; l++)
            ameteringrectangle.add(ParameterUtils.convertMeteringRectangleToLegacy(rect, (MeteringRectangle)arraylist.get(l), zoomdata).meteringArea);

        if(i < arraylist.size())
            Log.w("LegacyRequestMapper", (new StringBuilder()).append("convertMeteringRegionsToLegacy - Too many requested ").append(s).append(" regions, ignoring all beyond the first ").append(i).toString());
        return ameteringrectangle;
    }

    public static void convertRequestMetadata(LegacyRequest legacyrequest)
    {
        CaptureRequest capturerequest;
        android.hardware.Camera.Parameters parameters;
        CameraCharacteristics cameracharacteristics = legacyrequest.characteristics;
        capturerequest = legacyrequest.captureRequest;
        Object obj = legacyrequest.previewSize;
        parameters = legacyrequest.parameters;
        Rect rect = (Rect)cameracharacteristics.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);
        obj = ParameterUtils.convertScalerCropRegion(rect, (Rect)capturerequest.get(CaptureRequest.SCALER_CROP_REGION), ((Size) (obj)), parameters);
        if(parameters.isZoomSupported())
            parameters.setZoom(((ParameterUtils.ZoomData) (obj)).zoomIndex);
        int i = ((Integer)ParamsUtils.getOrDefault(capturerequest, CaptureRequest.COLOR_CORRECTION_ABERRATION_MODE, Integer.valueOf(1))).intValue();
        if(i != 1 && i != 2)
            Log.w("LegacyRequestMapper", (new StringBuilder()).append("convertRequestToMetadata - Ignoring unsupported colorCorrection.aberrationMode = ").append(i).toString());
        legacyrequest = (Integer)capturerequest.get(CaptureRequest.CONTROL_AE_ANTIBANDING_MODE);
        boolean flag;
        if(legacyrequest != null)
            legacyrequest = convertAeAntiBandingModeToLegacy(legacyrequest.intValue());
        else
            legacyrequest = (String)ListUtils.listSelectFirstFrom(parameters.getSupportedAntibanding(), new String[] {
                "auto", "off", "50hz", "60hz"
            });
        if(legacyrequest != null)
            parameters.setAntibanding(legacyrequest);
        legacyrequest = (MeteringRectangle[])capturerequest.get(CaptureRequest.CONTROL_AE_REGIONS);
        if(capturerequest.get(CaptureRequest.CONTROL_AWB_REGIONS) != null)
            Log.w("LegacyRequestMapper", "convertRequestMetadata - control.awbRegions setting is not supported, ignoring value");
        i = parameters.getMaxNumMeteringAreas();
        legacyrequest = convertMeteringRegionsToLegacy(rect, ((ParameterUtils.ZoomData) (obj)), legacyrequest, i, "AE");
        if(i > 0)
            parameters.setMeteringAreas(legacyrequest);
        legacyrequest = (MeteringRectangle[])capturerequest.get(CaptureRequest.CONTROL_AF_REGIONS);
        i = parameters.getMaxNumFocusAreas();
        legacyrequest = convertMeteringRegionsToLegacy(rect, ((ParameterUtils.ZoomData) (obj)), legacyrequest, i, "AF");
        if(i > 0)
            parameters.setFocusAreas(legacyrequest);
        legacyrequest = (Range)capturerequest.get(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE);
        if(legacyrequest != null)
        {
            int ai[] = convertAeFpsRangeToLegacy(legacyrequest);
            android.hardware.camera2.CaptureRequest.Key key = null;
            Iterator iterator = parameters.getSupportedPreviewFpsRange().iterator();
            int j;
            int l;
            do
            {
                legacyrequest = key;
                if(!iterator.hasNext())
                    break;
                legacyrequest = (int[])iterator.next();
                l = (int)Math.floor((double)legacyrequest[0] / 1000D);
                j = (int)Math.ceil((double)legacyrequest[1] / 1000D);
            } while(ai[0] != l * 1000 || ai[1] != j * 1000);
            if(legacyrequest != null)
                parameters.setPreviewFpsRange(legacyrequest[0], legacyrequest[1]);
            else
                Log.w("LegacyRequestMapper", (new StringBuilder()).append("Unsupported FPS range set [").append(ai[0]).append(",").append(ai[1]).append("]").toString());
        }
        legacyrequest = (Range)cameracharacteristics.get(CameraCharacteristics.CONTROL_AE_COMPENSATION_RANGE);
        l = ((Integer)ParamsUtils.getOrDefault(capturerequest, CaptureRequest.CONTROL_AE_EXPOSURE_COMPENSATION, Integer.valueOf(0))).intValue();
        j = l;
        if(!legacyrequest.contains(Integer.valueOf(l)))
        {
            Log.w("LegacyRequestMapper", "convertRequestMetadata - control.aeExposureCompensation is out of range, ignoring value");
            j = 0;
        }
        parameters.setExposureCompensation(j);
        legacyrequest = (Boolean)getIfSupported(capturerequest, CaptureRequest.CONTROL_AE_LOCK, Boolean.valueOf(false), parameters.isAutoExposureLockSupported(), Boolean.valueOf(false));
        if(legacyrequest != null)
            parameters.setAutoExposureLock(legacyrequest.booleanValue());
        mapAeAndFlashMode(capturerequest, parameters);
        legacyrequest = LegacyMetadataMapper.convertAfModeToLegacy(((Integer)ParamsUtils.getOrDefault(capturerequest, CaptureRequest.CONTROL_AF_MODE, Integer.valueOf(0))).intValue(), parameters.getSupportedFocusModes());
        if(legacyrequest != null)
            parameters.setFocusMode(legacyrequest);
        legacyrequest = CaptureRequest.CONTROL_AWB_MODE;
        if(parameters.getSupportedWhiteBalance() != null)
            flag = true;
        else
            flag = false;
        legacyrequest = (Integer)getIfSupported(capturerequest, legacyrequest, Integer.valueOf(1), flag, Integer.valueOf(1));
        if(legacyrequest != null)
            parameters.setWhiteBalance(convertAwbModeToLegacy(legacyrequest.intValue()));
        legacyrequest = (Boolean)getIfSupported(capturerequest, CaptureRequest.CONTROL_AWB_LOCK, Boolean.valueOf(false), parameters.isAutoWhiteBalanceLockSupported(), Boolean.valueOf(false));
        if(legacyrequest != null)
            parameters.setAutoWhiteBalanceLock(legacyrequest.booleanValue());
        j = filterSupportedCaptureIntent(((Integer)ParamsUtils.getOrDefault(capturerequest, CaptureRequest.CONTROL_CAPTURE_INTENT, Integer.valueOf(1))).intValue());
        if(j != 3)
        {
            if(j == 4)
                flag = true;
            else
                flag = false;
        } else
        {
            flag = true;
        }
        parameters.setRecordingHint(flag);
        legacyrequest = (Integer)getIfSupported(capturerequest, CaptureRequest.CONTROL_VIDEO_STABILIZATION_MODE, Integer.valueOf(0), parameters.isVideoStabilizationSupported(), Integer.valueOf(0));
        if(legacyrequest != null)
        {
            if(legacyrequest.intValue() == 1)
                flag = true;
            else
                flag = false;
            parameters.setVideoStabilization(flag);
        }
        flag = ListUtils.listContains(parameters.getSupportedFocusModes(), "infinity");
        legacyrequest = (Float)getIfSupported(capturerequest, CaptureRequest.LENS_FOCUS_DISTANCE, Float.valueOf(0.0F), flag, Float.valueOf(0.0F));
        if(legacyrequest == null || legacyrequest.floatValue() != 0.0F)
            Log.w("LegacyRequestMapper", (new StringBuilder()).append("convertRequestToMetadata - Ignoring android.lens.focusDistance ").append(flag).append(", only 0.0f is supported").toString());
        if(parameters.getSupportedSceneModes() == null) goto _L2; else goto _L1
_L1:
        j = ((Integer)ParamsUtils.getOrDefault(capturerequest, CaptureRequest.CONTROL_MODE, Integer.valueOf(1))).intValue();
        j;
        JVM INSTR tableswitch 1 2: default 840
    //                   1 1480
    //                   2 1415;
           goto _L3 _L4 _L5
_L3:
        Log.w("LegacyRequestMapper", (new StringBuilder()).append("Control mode ").append(j).append(" is unsupported, defaulting to AUTO").toString());
        legacyrequest = "auto";
_L7:
        parameters.setSceneMode(legacyrequest);
_L2:
        List list;
        int k;
        if(parameters.getSupportedColorEffects() != null)
        {
            k = ((Integer)ParamsUtils.getOrDefault(capturerequest, CaptureRequest.CONTROL_EFFECT_MODE, Integer.valueOf(0))).intValue();
            legacyrequest = LegacyMetadataMapper.convertEffectModeToLegacy(k);
            if(legacyrequest != null)
            {
                parameters.setColorEffect(legacyrequest);
            } else
            {
                parameters.setColorEffect("none");
                Log.w("LegacyRequestMapper", (new StringBuilder()).append("Skipping unknown requested effect mode: ").append(k).toString());
            }
        }
        k = ((Integer)ParamsUtils.getOrDefault(capturerequest, CaptureRequest.SENSOR_TEST_PATTERN_MODE, Integer.valueOf(0))).intValue();
        if(k != 0)
            Log.w("LegacyRequestMapper", (new StringBuilder()).append("convertRequestToMetadata - ignoring sensor.testPatternMode ").append(k).append("; only OFF is supported").toString());
        legacyrequest = (Location)capturerequest.get(CaptureRequest.JPEG_GPS_LOCATION);
        if(legacyrequest != null)
        {
            if(checkForCompleteGpsData(legacyrequest))
            {
                parameters.setGpsAltitude(legacyrequest.getAltitude());
                parameters.setGpsLatitude(legacyrequest.getLatitude());
                parameters.setGpsLongitude(legacyrequest.getLongitude());
                parameters.setGpsProcessingMethod(legacyrequest.getProvider().toUpperCase());
                parameters.setGpsTimestamp(legacyrequest.getTime());
            } else
            {
                Log.w("LegacyRequestMapper", (new StringBuilder()).append("Incomplete GPS parameters provided in location ").append(legacyrequest).toString());
            }
        } else
        {
            parameters.removeGpsData();
        }
        legacyrequest = (Integer)capturerequest.get(CaptureRequest.JPEG_ORIENTATION);
        key = CaptureRequest.JPEG_ORIENTATION;
        if(legacyrequest == null)
            k = 0;
        else
            k = legacyrequest.intValue();
        parameters.setRotation(((Integer)ParamsUtils.getOrDefault(capturerequest, key, Integer.valueOf(k))).intValue());
        parameters.setJpegQuality(((Byte)ParamsUtils.getOrDefault(capturerequest, CaptureRequest.JPEG_QUALITY, Byte.valueOf((byte)85))).byteValue() & 0xff);
        parameters.setJpegThumbnailQuality(((Byte)ParamsUtils.getOrDefault(capturerequest, CaptureRequest.JPEG_THUMBNAIL_QUALITY, Byte.valueOf((byte)85))).byteValue() & 0xff);
        list = parameters.getSupportedJpegThumbnailSizes();
        if(list != null && list.size() > 0)
        {
            legacyrequest = (Size)capturerequest.get(CaptureRequest.JPEG_THUMBNAIL_SIZE);
            if(legacyrequest == null)
                k = 0;
            else
                k = ParameterUtils.containsSize(list, legacyrequest.getWidth(), legacyrequest.getHeight()) ^ true;
            if(k != 0)
                Log.w("LegacyRequestMapper", (new StringBuilder()).append("Invalid JPEG thumbnail size set ").append(legacyrequest).append(", skipping thumbnail...").toString());
            if(legacyrequest == null || k != 0)
                parameters.setJpegThumbnailSize(0, 0);
            else
                parameters.setJpegThumbnailSize(legacyrequest.getWidth(), legacyrequest.getHeight());
        }
        k = ((Integer)ParamsUtils.getOrDefault(capturerequest, CaptureRequest.NOISE_REDUCTION_MODE, Integer.valueOf(1))).intValue();
        if(k != 1 && k != 2)
            Log.w("LegacyRequestMapper", (new StringBuilder()).append("convertRequestToMetadata - Ignoring unsupported noiseReduction.mode = ").append(k).toString());
        return;
_L5:
        k = ((Integer)ParamsUtils.getOrDefault(capturerequest, CaptureRequest.CONTROL_SCENE_MODE, Integer.valueOf(0))).intValue();
        legacyrequest = LegacyMetadataMapper.convertSceneModeToLegacy(k);
        if(legacyrequest == null)
        {
            legacyrequest = "auto";
            Log.w("LegacyRequestMapper", (new StringBuilder()).append("Skipping unknown requested scene mode: ").append(k).toString());
        }
        continue; /* Loop/switch isn't completed */
_L4:
        legacyrequest = "auto";
        if(true) goto _L7; else goto _L6
_L6:
    }

    static int filterSupportedCaptureIntent(int i)
    {
        int j = i;
        i;
        JVM INSTR tableswitch 0 6: default 44
    //                   0 78
    //                   1 78
    //                   2 78
    //                   3 78
    //                   4 78
    //                   5 80
    //                   6 80;
           goto _L1 _L2 _L2 _L2 _L2 _L2 _L3 _L3
_L1:
        j = 1;
        Log.w("LegacyRequestMapper", (new StringBuilder()).append("Unknown control.captureIntent value ").append(1).append("; default to PREVIEW").toString());
_L2:
        return j;
_L3:
        Log.w("LegacyRequestMapper", (new StringBuilder()).append("Unsupported control.captureIntent value ").append(1).append("; default to PREVIEW").toString());
        if(true) goto _L1; else goto _L4
_L4:
    }

    private static Object getIfSupported(CaptureRequest capturerequest, android.hardware.camera2.CaptureRequest.Key key, Object obj, boolean flag, Object obj1)
    {
        capturerequest = ((CaptureRequest) (ParamsUtils.getOrDefault(capturerequest, key, obj)));
        if(!flag)
        {
            if(!Objects.equals(capturerequest, obj1))
                Log.w("LegacyRequestMapper", (new StringBuilder()).append(key.getName()).append(" is not supported; ignoring requested value ").append(capturerequest).toString());
            return null;
        } else
        {
            return capturerequest;
        }
    }

    private static void mapAeAndFlashMode(CaptureRequest capturerequest, android.hardware.Camera.Parameters parameters)
    {
        int i;
        int j;
        List list;
        String s;
        i = ((Integer)ParamsUtils.getOrDefault(capturerequest, CaptureRequest.FLASH_MODE, Integer.valueOf(0))).intValue();
        j = ((Integer)ParamsUtils.getOrDefault(capturerequest, CaptureRequest.CONTROL_AE_MODE, Integer.valueOf(1))).intValue();
        list = parameters.getSupportedFlashModes();
        s = null;
        if(ListUtils.listContains(list, "off"))
            s = "off";
        if(j != 1) goto _L2; else goto _L1
_L1:
        if(i != 2) goto _L4; else goto _L3
_L3:
        if(ListUtils.listContains(list, "torch"))
        {
            capturerequest = "torch";
        } else
        {
            Log.w("LegacyRequestMapper", "mapAeAndFlashMode - Ignore flash.mode == TORCH;camera does not support it");
            capturerequest = s;
        }
_L6:
        if(capturerequest != null)
            parameters.setFlashMode(capturerequest);
        return;
_L4:
        capturerequest = s;
        if(i == 1)
            if(ListUtils.listContains(list, "on"))
            {
                capturerequest = "on";
            } else
            {
                Log.w("LegacyRequestMapper", "mapAeAndFlashMode - Ignore flash.mode == SINGLE;camera does not support it");
                capturerequest = s;
            }
        continue; /* Loop/switch isn't completed */
_L2:
        if(j == 3)
        {
            if(ListUtils.listContains(list, "on"))
            {
                capturerequest = "on";
            } else
            {
                Log.w("LegacyRequestMapper", "mapAeAndFlashMode - Ignore control.aeMode == ON_ALWAYS_FLASH;camera does not support it");
                capturerequest = s;
            }
        } else
        if(j == 2)
        {
            if(ListUtils.listContains(list, "auto"))
            {
                capturerequest = "auto";
            } else
            {
                Log.w("LegacyRequestMapper", "mapAeAndFlashMode - Ignore control.aeMode == ON_AUTO_FLASH;camera does not support it");
                capturerequest = s;
            }
        } else
        {
            capturerequest = s;
            if(j == 4)
                if(ListUtils.listContains(list, "red-eye"))
                {
                    capturerequest = "red-eye";
                } else
                {
                    Log.w("LegacyRequestMapper", "mapAeAndFlashMode - Ignore control.aeMode == ON_AUTO_FLASH_REDEYE;camera does not support it");
                    capturerequest = s;
                }
        }
        if(true) goto _L6; else goto _L5
_L5:
    }

    private static final boolean DEBUG = false;
    private static final byte DEFAULT_JPEG_QUALITY = 85;
    private static final String TAG = "LegacyRequestMapper";
}
