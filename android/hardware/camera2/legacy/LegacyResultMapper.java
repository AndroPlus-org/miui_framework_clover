// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.legacy;

import android.graphics.Rect;
import android.hardware.camera2.*;
import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.params.MeteringRectangle;
import android.hardware.camera2.utils.ParamsUtils;
import android.location.Location;
import android.util.Log;
import java.util.*;

// Referenced classes of package android.hardware.camera2.legacy:
//            LegacyRequest, ParameterUtils, LegacyRequestMapper, LegacyMetadataMapper

public class LegacyResultMapper
{

    public LegacyResultMapper()
    {
        mCachedRequest = null;
        mCachedResult = null;
    }

    private static int convertLegacyAfMode(String s)
    {
        if(s == null)
        {
            Log.w("LegacyResultMapper", "convertLegacyAfMode - no AF mode, default to OFF");
            return 0;
        }
        if(s.equals("auto"))
            return 1;
        if(s.equals("continuous-picture"))
            return 4;
        if(s.equals("continuous-video"))
            return 3;
        if(s.equals("edof"))
            return 5;
        if(s.equals("macro"))
            return 2;
        if(s.equals("fixed"))
            return 0;
        if(s.equals("infinity"))
        {
            return 0;
        } else
        {
            Log.w("LegacyResultMapper", (new StringBuilder()).append("convertLegacyAfMode - unknown mode ").append(s).append(" , ignoring").toString());
            return 0;
        }
    }

    private static int convertLegacyAwbMode(String s)
    {
        if(s == null)
            return 1;
        if(s.equals("auto"))
            return 1;
        if(s.equals("incandescent"))
            return 2;
        if(s.equals("fluorescent"))
            return 3;
        if(s.equals("warm-fluorescent"))
            return 4;
        if(s.equals("daylight"))
            return 5;
        if(s.equals("cloudy-daylight"))
            return 6;
        if(s.equals("twilight"))
            return 7;
        if(s.equals("shade"))
        {
            return 8;
        } else
        {
            Log.w("LegacyResultMapper", (new StringBuilder()).append("convertAwbMode - unrecognized WB mode ").append(s).toString());
            return 1;
        }
    }

    private static CameraMetadataNative convertResultMetadata(LegacyRequest legacyrequest)
    {
        Object obj = legacyrequest.characteristics;
        CaptureRequest capturerequest = legacyrequest.captureRequest;
        Object obj1 = legacyrequest.previewSize;
        android.hardware.Camera.Parameters parameters = legacyrequest.parameters;
        legacyrequest = new CameraMetadataNative();
        Object obj2 = (Rect)((CameraCharacteristics) (obj)).get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);
        obj1 = ParameterUtils.convertScalerCropRegion(((Rect) (obj2)), (Rect)capturerequest.get(CaptureRequest.SCALER_CROP_REGION), ((android.util.Size) (obj1)), parameters);
        legacyrequest.set(CaptureResult.COLOR_CORRECTION_ABERRATION_MODE, (Integer)capturerequest.get(CaptureRequest.COLOR_CORRECTION_ABERRATION_MODE));
        mapAe(legacyrequest, ((CameraCharacteristics) (obj)), capturerequest, ((Rect) (obj2)), ((ParameterUtils.ZoomData) (obj1)), parameters);
        mapAf(legacyrequest, ((Rect) (obj2)), ((ParameterUtils.ZoomData) (obj1)), parameters);
        mapAwb(legacyrequest, parameters);
        int i = LegacyRequestMapper.filterSupportedCaptureIntent(((Integer)ParamsUtils.getOrDefault(capturerequest, CaptureRequest.CONTROL_CAPTURE_INTENT, Integer.valueOf(1))).intValue());
        legacyrequest.set(CaptureResult.CONTROL_CAPTURE_INTENT, Integer.valueOf(i));
        if(((Integer)ParamsUtils.getOrDefault(capturerequest, CaptureRequest.CONTROL_MODE, Integer.valueOf(1))).intValue() == 2)
            legacyrequest.set(CaptureResult.CONTROL_MODE, Integer.valueOf(2));
        else
            legacyrequest.set(CaptureResult.CONTROL_MODE, Integer.valueOf(1));
        obj2 = parameters.getSceneMode();
        i = LegacyMetadataMapper.convertSceneModeFromLegacy(((String) (obj2)));
        if(i != -1)
        {
            legacyrequest.set(CaptureResult.CONTROL_SCENE_MODE, Integer.valueOf(i));
        } else
        {
            Log.w("LegacyResultMapper", (new StringBuilder()).append("Unknown scene mode ").append(((String) (obj2))).append(" returned by camera HAL, setting to disabled.").toString());
            legacyrequest.set(CaptureResult.CONTROL_SCENE_MODE, Integer.valueOf(0));
        }
        obj2 = parameters.getColorEffect();
        i = LegacyMetadataMapper.convertEffectModeFromLegacy(((String) (obj2)));
        if(i != -1)
        {
            legacyrequest.set(CaptureResult.CONTROL_EFFECT_MODE, Integer.valueOf(i));
        } else
        {
            Log.w("LegacyResultMapper", (new StringBuilder()).append("Unknown effect mode ").append(((String) (obj2))).append(" returned by camera HAL, setting to off.").toString());
            legacyrequest.set(CaptureResult.CONTROL_EFFECT_MODE, Integer.valueOf(0));
        }
        if(parameters.isVideoStabilizationSupported() && parameters.getVideoStabilization())
            i = 1;
        else
            i = 0;
        legacyrequest.set(CaptureResult.CONTROL_VIDEO_STABILIZATION_MODE, Integer.valueOf(i));
        if("infinity".equals(parameters.getFocusMode()))
            legacyrequest.set(CaptureResult.LENS_FOCUS_DISTANCE, Float.valueOf(0.0F));
        legacyrequest.set(CaptureResult.LENS_FOCAL_LENGTH, Float.valueOf(parameters.getFocalLength()));
        legacyrequest.set(CaptureResult.REQUEST_PIPELINE_DEPTH, (Byte)((CameraCharacteristics) (obj)).get(CameraCharacteristics.REQUEST_PIPELINE_MAX_DEPTH));
        mapScaler(legacyrequest, ((ParameterUtils.ZoomData) (obj1)), parameters);
        legacyrequest.set(CaptureResult.SENSOR_TEST_PATTERN_MODE, Integer.valueOf(0));
        legacyrequest.set(CaptureResult.JPEG_GPS_LOCATION, (Location)capturerequest.get(CaptureRequest.JPEG_GPS_LOCATION));
        legacyrequest.set(CaptureResult.JPEG_ORIENTATION, (Integer)capturerequest.get(CaptureRequest.JPEG_ORIENTATION));
        legacyrequest.set(CaptureResult.JPEG_QUALITY, Byte.valueOf((byte)parameters.getJpegQuality()));
        legacyrequest.set(CaptureResult.JPEG_THUMBNAIL_QUALITY, Byte.valueOf((byte)parameters.getJpegThumbnailQuality()));
        obj = parameters.getJpegThumbnailSize();
        if(obj != null)
            legacyrequest.set(CaptureResult.JPEG_THUMBNAIL_SIZE, ParameterUtils.convertSize(((android.hardware.Camera.Size) (obj))));
        else
            Log.w("LegacyResultMapper", "Null thumbnail size received from parameters.");
        legacyrequest.set(CaptureResult.NOISE_REDUCTION_MODE, (Integer)capturerequest.get(CaptureRequest.NOISE_REDUCTION_MODE));
        return legacyrequest;
    }

    private static MeteringRectangle[] getMeteringRectangles(Rect rect, ParameterUtils.ZoomData zoomdata, List list, String s)
    {
        s = new ArrayList();
        if(list != null)
            for(list = list.iterator(); list.hasNext(); s.add(ParameterUtils.convertCameraAreaToActiveArrayRectangle(rect, zoomdata, (android.hardware.Camera.Area)list.next()).toMetering()));
        return (MeteringRectangle[])s.toArray(new MeteringRectangle[0]);
    }

    private static void mapAe(CameraMetadataNative camerametadatanative, CameraCharacteristics cameracharacteristics, CaptureRequest capturerequest, Rect rect, ParameterUtils.ZoomData zoomdata, android.hardware.Camera.Parameters parameters)
    {
        int i = LegacyMetadataMapper.convertAntiBandingModeOrDefault(parameters.getAntibanding());
        camerametadatanative.set(CaptureResult.CONTROL_AE_ANTIBANDING_MODE, Integer.valueOf(i));
        camerametadatanative.set(CaptureResult.CONTROL_AE_EXPOSURE_COMPENSATION, Integer.valueOf(parameters.getExposureCompensation()));
        boolean flag;
        if(parameters.isAutoExposureLockSupported())
            flag = parameters.getAutoExposureLock();
        else
            flag = false;
        camerametadatanative.set(CaptureResult.CONTROL_AE_LOCK, Boolean.valueOf(flag));
        capturerequest = (Boolean)capturerequest.get(CaptureRequest.CONTROL_AE_LOCK);
        if(capturerequest != null && capturerequest.booleanValue() != flag)
            Log.w("LegacyResultMapper", (new StringBuilder()).append("mapAe - android.control.aeLock was requested to ").append(capturerequest).append(" but resulted in ").append(flag).toString());
        mapAeAndFlashMode(camerametadatanative, cameracharacteristics, parameters);
        if(parameters.getMaxNumMeteringAreas() > 0)
        {
            cameracharacteristics = getMeteringRectangles(rect, zoomdata, parameters.getMeteringAreas(), "AE");
            camerametadatanative.set(CaptureResult.CONTROL_AE_REGIONS, cameracharacteristics);
        }
    }

    private static void mapAeAndFlashMode(CameraMetadataNative camerametadatanative, CameraCharacteristics cameracharacteristics, android.hardware.Camera.Parameters parameters)
    {
        boolean flag = false;
        Integer integer;
        boolean flag1;
        String s;
        int i;
        int j;
        if(((Boolean)cameracharacteristics.get(CameraCharacteristics.FLASH_INFO_AVAILABLE)).booleanValue())
            integer = null;
        else
            integer = Integer.valueOf(0);
        flag1 = true;
        s = parameters.getFlashMode();
        i = ((flag1) ? 1 : 0);
        j = ((flag) ? 1 : 0);
        cameracharacteristics = integer;
        if(s != null)
            if(s.equals("off"))
            {
                cameracharacteristics = integer;
                j = ((flag) ? 1 : 0);
                i = ((flag1) ? 1 : 0);
            } else
            if(s.equals("auto"))
            {
                i = 2;
                j = ((flag) ? 1 : 0);
                cameracharacteristics = integer;
            } else
            if(s.equals("on"))
            {
                j = 1;
                i = 3;
                cameracharacteristics = Integer.valueOf(3);
            } else
            if(s.equals("red-eye"))
            {
                i = 4;
                j = ((flag) ? 1 : 0);
                cameracharacteristics = integer;
            } else
            if(s.equals("torch"))
            {
                j = 2;
                cameracharacteristics = Integer.valueOf(3);
                i = ((flag1) ? 1 : 0);
            } else
            {
                Log.w("LegacyResultMapper", (new StringBuilder()).append("mapAeAndFlashMode - Ignoring unknown flash mode ").append(parameters.getFlashMode()).toString());
                i = ((flag1) ? 1 : 0);
                j = ((flag) ? 1 : 0);
                cameracharacteristics = integer;
            }
        camerametadatanative.set(CaptureResult.FLASH_STATE, cameracharacteristics);
        camerametadatanative.set(CaptureResult.FLASH_MODE, Integer.valueOf(j));
        camerametadatanative.set(CaptureResult.CONTROL_AE_MODE, Integer.valueOf(i));
    }

    private static void mapAf(CameraMetadataNative camerametadatanative, Rect rect, ParameterUtils.ZoomData zoomdata, android.hardware.Camera.Parameters parameters)
    {
        camerametadatanative.set(CaptureResult.CONTROL_AF_MODE, Integer.valueOf(convertLegacyAfMode(parameters.getFocusMode())));
        if(parameters.getMaxNumFocusAreas() > 0)
        {
            rect = getMeteringRectangles(rect, zoomdata, parameters.getFocusAreas(), "AF");
            camerametadatanative.set(CaptureResult.CONTROL_AF_REGIONS, rect);
        }
    }

    private static void mapAwb(CameraMetadataNative camerametadatanative, android.hardware.Camera.Parameters parameters)
    {
        boolean flag;
        int i;
        if(parameters.isAutoWhiteBalanceLockSupported())
            flag = parameters.getAutoWhiteBalanceLock();
        else
            flag = false;
        camerametadatanative.set(CaptureResult.CONTROL_AWB_LOCK, Boolean.valueOf(flag));
        i = convertLegacyAwbMode(parameters.getWhiteBalance());
        camerametadatanative.set(CaptureResult.CONTROL_AWB_MODE, Integer.valueOf(i));
    }

    private static void mapScaler(CameraMetadataNative camerametadatanative, ParameterUtils.ZoomData zoomdata, android.hardware.Camera.Parameters parameters)
    {
        camerametadatanative.set(CaptureResult.SCALER_CROP_REGION, zoomdata.reportedCrop);
    }

    public CameraMetadataNative cachedConvertResultMetadata(LegacyRequest legacyrequest, long l)
    {
        if(mCachedRequest != null && legacyrequest.parameters.same(mCachedRequest.parameters) && legacyrequest.captureRequest.equals(mCachedRequest.captureRequest))
        {
            legacyrequest = new CameraMetadataNative(mCachedResult);
        } else
        {
            CameraMetadataNative camerametadatanative = convertResultMetadata(legacyrequest);
            mCachedRequest = legacyrequest;
            mCachedResult = new CameraMetadataNative(camerametadatanative);
            legacyrequest = camerametadatanative;
        }
        legacyrequest.set(CaptureResult.SENSOR_TIMESTAMP, Long.valueOf(l));
        return legacyrequest;
    }

    private static final boolean DEBUG = false;
    private static final String TAG = "LegacyResultMapper";
    private LegacyRequest mCachedRequest;
    private CameraMetadataNative mCachedResult;
}
