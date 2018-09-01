// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2;

import android.graphics.Rect;
import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.impl.CaptureResultExtras;
import android.hardware.camera2.params.ColorSpaceTransform;
import android.hardware.camera2.params.LensShadingMap;
import android.hardware.camera2.params.RggbChannelVector;
import android.hardware.camera2.params.TonemapCurve;
import android.hardware.camera2.utils.TypeReference;
import android.location.Location;
import android.util.Size;
import java.util.List;

// Referenced classes of package android.hardware.camera2:
//            CameraMetadata, CaptureRequest

public class CaptureResult extends CameraMetadata
{
    public static final class Key
    {

        public final boolean equals(Object obj)
        {
            boolean flag;
            if(obj instanceof Key)
                flag = ((Key)obj).mKey.equals(mKey);
            else
                flag = false;
            return flag;
        }

        public String getName()
        {
            return mKey.getName();
        }

        public android.hardware.camera2.impl.CameraMetadataNative.Key getNativeKey()
        {
            return mKey;
        }

        public long getVendorId()
        {
            return mKey.getVendorId();
        }

        public final int hashCode()
        {
            return mKey.hashCode();
        }

        public String toString()
        {
            return String.format("CaptureResult.Key(%s)", new Object[] {
                mKey.getName()
            });
        }

        private final android.hardware.camera2.impl.CameraMetadataNative.Key mKey;

        Key(android.hardware.camera2.impl.CameraMetadataNative.Key key)
        {
            mKey = key;
        }

        public Key(String s, TypeReference typereference)
        {
            mKey = new android.hardware.camera2.impl.CameraMetadataNative.Key(s, typereference);
        }

        public Key(String s, Class class1)
        {
            mKey = new android.hardware.camera2.impl.CameraMetadataNative.Key(s, class1);
        }

        public Key(String s, Class class1, long l)
        {
            mKey = new android.hardware.camera2.impl.CameraMetadataNative.Key(s, class1, l);
        }
    }


    public CaptureResult(CameraMetadataNative camerametadatanative, int i)
    {
        if(camerametadatanative == null)
            throw new IllegalArgumentException("results was null");
        mResults = CameraMetadataNative.move(camerametadatanative);
        if(mResults.isEmpty())
        {
            throw new AssertionError("Results must not be empty");
        } else
        {
            setNativeInstance(mResults);
            mRequest = null;
            mSequenceId = i;
            mFrameNumber = -1L;
            return;
        }
    }

    public CaptureResult(CameraMetadataNative camerametadatanative, CaptureRequest capturerequest, CaptureResultExtras captureresultextras)
    {
        if(camerametadatanative == null)
            throw new IllegalArgumentException("results was null");
        if(capturerequest == null)
            throw new IllegalArgumentException("parent was null");
        if(captureresultextras == null)
            throw new IllegalArgumentException("extras was null");
        mResults = CameraMetadataNative.move(camerametadatanative);
        if(mResults.isEmpty())
        {
            throw new AssertionError("Results must not be empty");
        } else
        {
            setNativeInstance(mResults);
            mRequest = capturerequest;
            mSequenceId = captureresultextras.getRequestId();
            mFrameNumber = captureresultextras.getFrameNumber();
            return;
        }
    }

    public void dumpToLog()
    {
        mResults.dumpToLog();
    }

    public Object get(Key key)
    {
        return mResults.get(key);
    }

    public long getFrameNumber()
    {
        return mFrameNumber;
    }

    protected Class getKeyClass()
    {
        return (Class)android/hardware/camera2/CaptureResult$Key;
    }

    public List getKeys()
    {
        return super.getKeys();
    }

    public CameraMetadataNative getNativeCopy()
    {
        return new CameraMetadataNative(mResults);
    }

    protected Object getProtected(Key key)
    {
        return mResults.get(key);
    }

    protected volatile Object getProtected(Object obj)
    {
        return getProtected((Key)obj);
    }

    public CaptureRequest getRequest()
    {
        return mRequest;
    }

    public int getSequenceId()
    {
        return mSequenceId;
    }

    public static final Key BLACK_LEVEL_LOCK;
    public static final Key COLOR_CORRECTION_ABERRATION_MODE;
    public static final Key COLOR_CORRECTION_GAINS = new Key("android.colorCorrection.gains", android/hardware/camera2/params/RggbChannelVector);
    public static final Key COLOR_CORRECTION_MODE;
    public static final Key COLOR_CORRECTION_TRANSFORM = new Key("android.colorCorrection.transform", android/hardware/camera2/params/ColorSpaceTransform);
    public static final Key CONTROL_AE_ANTIBANDING_MODE;
    public static final Key CONTROL_AE_EXPOSURE_COMPENSATION;
    public static final Key CONTROL_AE_LOCK;
    public static final Key CONTROL_AE_MODE;
    public static final Key CONTROL_AE_PRECAPTURE_TRIGGER;
    public static final Key CONTROL_AE_REGIONS = new Key("android.control.aeRegions", [Landroid/hardware/camera2/params/MeteringRectangle;);
    public static final Key CONTROL_AE_STATE;
    public static final Key CONTROL_AE_TARGET_FPS_RANGE = new Key("android.control.aeTargetFpsRange", new TypeReference() {

    }
);
    public static final Key CONTROL_AF_MODE;
    public static final Key CONTROL_AF_REGIONS = new Key("android.control.afRegions", [Landroid/hardware/camera2/params/MeteringRectangle;);
    public static final Key CONTROL_AF_STATE;
    public static final Key CONTROL_AF_TRIGGER;
    public static final Key CONTROL_AWB_LOCK;
    public static final Key CONTROL_AWB_MODE;
    public static final Key CONTROL_AWB_REGIONS = new Key("android.control.awbRegions", [Landroid/hardware/camera2/params/MeteringRectangle;);
    public static final Key CONTROL_AWB_STATE;
    public static final Key CONTROL_CAPTURE_INTENT;
    public static final Key CONTROL_EFFECT_MODE;
    public static final Key CONTROL_ENABLE_ZSL;
    public static final Key CONTROL_MODE;
    public static final Key CONTROL_POST_RAW_SENSITIVITY_BOOST;
    public static final Key CONTROL_SCENE_MODE;
    public static final Key CONTROL_VIDEO_STABILIZATION_MODE;
    public static final Key EDGE_MODE;
    public static final Key FLASH_MODE;
    public static final Key FLASH_STATE;
    public static final Key HOT_PIXEL_MODE;
    public static final Key JPEG_GPS_COORDINATES = new Key("android.jpeg.gpsCoordinates", [D);
    public static final Key JPEG_GPS_LOCATION = new Key("android.jpeg.gpsLocation", android/location/Location);
    public static final Key JPEG_GPS_PROCESSING_METHOD = new Key("android.jpeg.gpsProcessingMethod", java/lang/String);
    public static final Key JPEG_GPS_TIMESTAMP;
    public static final Key JPEG_ORIENTATION;
    public static final Key JPEG_QUALITY;
    public static final Key JPEG_THUMBNAIL_QUALITY;
    public static final Key JPEG_THUMBNAIL_SIZE = new Key("android.jpeg.thumbnailSize", android/util/Size);
    public static final Key LED_TRANSMIT;
    public static final Key LENS_APERTURE;
    public static final Key LENS_FILTER_DENSITY;
    public static final Key LENS_FOCAL_LENGTH;
    public static final Key LENS_FOCUS_DISTANCE;
    public static final Key LENS_FOCUS_RANGE = new Key("android.lens.focusRange", new TypeReference() {

    }
);
    public static final Key LENS_INTRINSIC_CALIBRATION = new Key("android.lens.intrinsicCalibration", [F);
    public static final Key LENS_OPTICAL_STABILIZATION_MODE;
    public static final Key LENS_POSE_ROTATION = new Key("android.lens.poseRotation", [F);
    public static final Key LENS_POSE_TRANSLATION = new Key("android.lens.poseTranslation", [F);
    public static final Key LENS_RADIAL_DISTORTION = new Key("android.lens.radialDistortion", [F);
    public static final Key LENS_STATE;
    public static final Key NOISE_REDUCTION_MODE;
    public static final Key QUIRKS_PARTIAL_RESULT;
    public static final Key REPROCESS_EFFECTIVE_EXPOSURE_FACTOR;
    public static final Key REQUEST_FRAME_COUNT;
    public static final Key REQUEST_ID;
    public static final Key REQUEST_PIPELINE_DEPTH;
    public static final Key SCALER_CROP_REGION = new Key("android.scaler.cropRegion", android/graphics/Rect);
    public static final Key SENSOR_DYNAMIC_BLACK_LEVEL = new Key("android.sensor.dynamicBlackLevel", [F);
    public static final Key SENSOR_DYNAMIC_WHITE_LEVEL;
    public static final Key SENSOR_EXPOSURE_TIME;
    public static final Key SENSOR_FRAME_DURATION;
    public static final Key SENSOR_GREEN_SPLIT;
    public static final Key SENSOR_NEUTRAL_COLOR_POINT = new Key("android.sensor.neutralColorPoint", [Landroid/util/Rational;);
    public static final Key SENSOR_NOISE_PROFILE = new Key("android.sensor.noiseProfile", new TypeReference() {

    }
);
    public static final Key SENSOR_ROLLING_SHUTTER_SKEW;
    public static final Key SENSOR_SENSITIVITY;
    public static final Key SENSOR_TEST_PATTERN_DATA = new Key("android.sensor.testPatternData", [I);
    public static final Key SENSOR_TEST_PATTERN_MODE;
    public static final Key SENSOR_TIMESTAMP;
    public static final Key SHADING_MODE;
    public static final Key STATISTICS_FACES = new Key("android.statistics.faces", [Landroid/hardware/camera2/params/Face;);
    public static final Key STATISTICS_FACE_DETECT_MODE;
    public static final Key STATISTICS_FACE_IDS = new Key("android.statistics.faceIds", [I);
    public static final Key STATISTICS_FACE_LANDMARKS = new Key("android.statistics.faceLandmarks", [I);
    public static final Key STATISTICS_FACE_RECTANGLES = new Key("android.statistics.faceRectangles", [Landroid/graphics/Rect;);
    public static final Key STATISTICS_FACE_SCORES = new Key("android.statistics.faceScores", [B);
    public static final Key STATISTICS_HOT_PIXEL_MAP = new Key("android.statistics.hotPixelMap", [Landroid/graphics/Point;);
    public static final Key STATISTICS_HOT_PIXEL_MAP_MODE;
    public static final Key STATISTICS_LENS_SHADING_CORRECTION_MAP = new Key("android.statistics.lensShadingCorrectionMap", android/hardware/camera2/params/LensShadingMap);
    public static final Key STATISTICS_LENS_SHADING_MAP = new Key("android.statistics.lensShadingMap", [F);
    public static final Key STATISTICS_LENS_SHADING_MAP_MODE;
    public static final Key STATISTICS_PREDICTED_COLOR_GAINS = new Key("android.statistics.predictedColorGains", [F);
    public static final Key STATISTICS_PREDICTED_COLOR_TRANSFORM = new Key("android.statistics.predictedColorTransform", [Landroid/util/Rational;);
    public static final Key STATISTICS_SCENE_FLICKER;
    public static final Key SYNC_FRAME_NUMBER;
    private static final String TAG = "CaptureResult";
    public static final Key TONEMAP_CURVE = new Key("android.tonemap.curve", android/hardware/camera2/params/TonemapCurve);
    public static final Key TONEMAP_CURVE_BLUE = new Key("android.tonemap.curveBlue", [F);
    public static final Key TONEMAP_CURVE_GREEN = new Key("android.tonemap.curveGreen", [F);
    public static final Key TONEMAP_CURVE_RED = new Key("android.tonemap.curveRed", [F);
    public static final Key TONEMAP_GAMMA;
    public static final Key TONEMAP_MODE;
    public static final Key TONEMAP_PRESET_CURVE;
    private static final boolean VERBOSE = false;
    private final long mFrameNumber;
    private final CaptureRequest mRequest;
    private final CameraMetadataNative mResults;
    private final int mSequenceId;

    static 
    {
        COLOR_CORRECTION_MODE = new Key("android.colorCorrection.mode", Integer.TYPE);
        COLOR_CORRECTION_ABERRATION_MODE = new Key("android.colorCorrection.aberrationMode", Integer.TYPE);
        CONTROL_AE_ANTIBANDING_MODE = new Key("android.control.aeAntibandingMode", Integer.TYPE);
        CONTROL_AE_EXPOSURE_COMPENSATION = new Key("android.control.aeExposureCompensation", Integer.TYPE);
        CONTROL_AE_LOCK = new Key("android.control.aeLock", Boolean.TYPE);
        CONTROL_AE_MODE = new Key("android.control.aeMode", Integer.TYPE);
        CONTROL_AE_PRECAPTURE_TRIGGER = new Key("android.control.aePrecaptureTrigger", Integer.TYPE);
        CONTROL_AE_STATE = new Key("android.control.aeState", Integer.TYPE);
        CONTROL_AF_MODE = new Key("android.control.afMode", Integer.TYPE);
        CONTROL_AF_TRIGGER = new Key("android.control.afTrigger", Integer.TYPE);
        CONTROL_AF_STATE = new Key("android.control.afState", Integer.TYPE);
        CONTROL_AWB_LOCK = new Key("android.control.awbLock", Boolean.TYPE);
        CONTROL_AWB_MODE = new Key("android.control.awbMode", Integer.TYPE);
        CONTROL_CAPTURE_INTENT = new Key("android.control.captureIntent", Integer.TYPE);
        CONTROL_AWB_STATE = new Key("android.control.awbState", Integer.TYPE);
        CONTROL_EFFECT_MODE = new Key("android.control.effectMode", Integer.TYPE);
        CONTROL_MODE = new Key("android.control.mode", Integer.TYPE);
        CONTROL_SCENE_MODE = new Key("android.control.sceneMode", Integer.TYPE);
        CONTROL_VIDEO_STABILIZATION_MODE = new Key("android.control.videoStabilizationMode", Integer.TYPE);
        CONTROL_POST_RAW_SENSITIVITY_BOOST = new Key("android.control.postRawSensitivityBoost", Integer.TYPE);
        CONTROL_ENABLE_ZSL = new Key("android.control.enableZsl", Boolean.TYPE);
        EDGE_MODE = new Key("android.edge.mode", Integer.TYPE);
        FLASH_MODE = new Key("android.flash.mode", Integer.TYPE);
        FLASH_STATE = new Key("android.flash.state", Integer.TYPE);
        HOT_PIXEL_MODE = new Key("android.hotPixel.mode", Integer.TYPE);
        JPEG_GPS_TIMESTAMP = new Key("android.jpeg.gpsTimestamp", Long.TYPE);
        JPEG_ORIENTATION = new Key("android.jpeg.orientation", Integer.TYPE);
        JPEG_QUALITY = new Key("android.jpeg.quality", Byte.TYPE);
        JPEG_THUMBNAIL_QUALITY = new Key("android.jpeg.thumbnailQuality", Byte.TYPE);
        LENS_APERTURE = new Key("android.lens.aperture", Float.TYPE);
        LENS_FILTER_DENSITY = new Key("android.lens.filterDensity", Float.TYPE);
        LENS_FOCAL_LENGTH = new Key("android.lens.focalLength", Float.TYPE);
        LENS_FOCUS_DISTANCE = new Key("android.lens.focusDistance", Float.TYPE);
        LENS_OPTICAL_STABILIZATION_MODE = new Key("android.lens.opticalStabilizationMode", Integer.TYPE);
        LENS_STATE = new Key("android.lens.state", Integer.TYPE);
        NOISE_REDUCTION_MODE = new Key("android.noiseReduction.mode", Integer.TYPE);
        QUIRKS_PARTIAL_RESULT = new Key("android.quirks.partialResult", Boolean.TYPE);
        REQUEST_FRAME_COUNT = new Key("android.request.frameCount", Integer.TYPE);
        REQUEST_ID = new Key("android.request.id", Integer.TYPE);
        REQUEST_PIPELINE_DEPTH = new Key("android.request.pipelineDepth", Byte.TYPE);
        SENSOR_EXPOSURE_TIME = new Key("android.sensor.exposureTime", Long.TYPE);
        SENSOR_FRAME_DURATION = new Key("android.sensor.frameDuration", Long.TYPE);
        SENSOR_SENSITIVITY = new Key("android.sensor.sensitivity", Integer.TYPE);
        SENSOR_TIMESTAMP = new Key("android.sensor.timestamp", Long.TYPE);
        SENSOR_GREEN_SPLIT = new Key("android.sensor.greenSplit", Float.TYPE);
        SENSOR_TEST_PATTERN_MODE = new Key("android.sensor.testPatternMode", Integer.TYPE);
        SENSOR_ROLLING_SHUTTER_SKEW = new Key("android.sensor.rollingShutterSkew", Long.TYPE);
        SENSOR_DYNAMIC_WHITE_LEVEL = new Key("android.sensor.dynamicWhiteLevel", Integer.TYPE);
        SHADING_MODE = new Key("android.shading.mode", Integer.TYPE);
        STATISTICS_FACE_DETECT_MODE = new Key("android.statistics.faceDetectMode", Integer.TYPE);
        STATISTICS_SCENE_FLICKER = new Key("android.statistics.sceneFlicker", Integer.TYPE);
        STATISTICS_HOT_PIXEL_MAP_MODE = new Key("android.statistics.hotPixelMapMode", Boolean.TYPE);
        STATISTICS_LENS_SHADING_MAP_MODE = new Key("android.statistics.lensShadingMapMode", Integer.TYPE);
        TONEMAP_MODE = new Key("android.tonemap.mode", Integer.TYPE);
        TONEMAP_GAMMA = new Key("android.tonemap.gamma", Float.TYPE);
        TONEMAP_PRESET_CURVE = new Key("android.tonemap.presetCurve", Integer.TYPE);
        LED_TRANSMIT = new Key("android.led.transmit", Boolean.TYPE);
        BLACK_LEVEL_LOCK = new Key("android.blackLevel.lock", Boolean.TYPE);
        SYNC_FRAME_NUMBER = new Key("android.sync.frameNumber", Long.TYPE);
        REPROCESS_EFFECTIVE_EXPOSURE_FACTOR = new Key("android.reprocess.effectiveExposureFactor", Float.TYPE);
    }
}
