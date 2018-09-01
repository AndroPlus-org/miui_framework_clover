// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2;

import android.graphics.Rect;
import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.params.BlackLevelPattern;
import android.hardware.camera2.params.ColorSpaceTransform;
import android.hardware.camera2.params.ReprocessFormatsMap;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.hardware.camera2.utils.TypeReference;
import android.util.*;
import java.util.Collections;
import java.util.List;

// Referenced classes of package android.hardware.camera2:
//            CameraMetadata, CaptureRequest, CaptureResult

public final class CameraCharacteristics extends CameraMetadata
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
            return String.format("CameraCharacteristics.Key(%s)", new Object[] {
                mKey.getName()
            });
        }

        private final android.hardware.camera2.impl.CameraMetadataNative.Key mKey;

        private Key(android.hardware.camera2.impl.CameraMetadataNative.Key key)
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


    public CameraCharacteristics(CameraMetadataNative camerametadatanative)
    {
        mProperties = CameraMetadataNative.move(camerametadatanative);
        setNativeInstance(mProperties);
    }

    private List getAvailableKeyList(Class class1, Class class2, int ai[])
    {
        if(class1.equals(android/hardware/camera2/CameraMetadata))
            throw new AssertionError("metadataClass must be a strict subclass of CameraMetadata");
        if(!android/hardware/camera2/CameraMetadata.isAssignableFrom(class1))
            throw new AssertionError("metadataClass must be a subclass of CameraMetadata");
        else
            return Collections.unmodifiableList(getKeys(class1, class2, null, ai));
    }

    public Object get(Key key)
    {
        return mProperties.get(key);
    }

    public List getAvailableCaptureRequestKeys()
    {
        if(mAvailableRequestKeys == null)
        {
            Class class1 = (Class)android/hardware/camera2/CaptureRequest$Key;
            int ai[] = (int[])get(REQUEST_AVAILABLE_REQUEST_KEYS);
            if(ai == null)
                throw new AssertionError("android.request.availableRequestKeys must be non-null in the characteristics");
            mAvailableRequestKeys = getAvailableKeyList(android/hardware/camera2/CaptureRequest, class1, ai);
        }
        return mAvailableRequestKeys;
    }

    public List getAvailableCaptureResultKeys()
    {
        if(mAvailableResultKeys == null)
        {
            Class class1 = (Class)android/hardware/camera2/CaptureResult$Key;
            int ai[] = (int[])get(REQUEST_AVAILABLE_RESULT_KEYS);
            if(ai == null)
                throw new AssertionError("android.request.availableResultKeys must be non-null in the characteristics");
            mAvailableResultKeys = getAvailableKeyList(android/hardware/camera2/CaptureResult, class1, ai);
        }
        return mAvailableResultKeys;
    }

    protected Class getKeyClass()
    {
        return (Class)android/hardware/camera2/CameraCharacteristics$Key;
    }

    public List getKeys()
    {
        if(mKeys != null)
            return mKeys;
        int ai[] = (int[])get(REQUEST_AVAILABLE_CHARACTERISTICS_KEYS);
        if(ai == null)
        {
            throw new AssertionError("android.request.availableCharacteristicsKeys must be non-null in the characteristics");
        } else
        {
            mKeys = Collections.unmodifiableList(getKeys(getClass(), getKeyClass(), ((CameraMetadata) (this)), ai));
            return mKeys;
        }
    }

    public CameraMetadataNative getNativeCopy()
    {
        return new CameraMetadataNative(mProperties);
    }

    protected Object getProtected(Key key)
    {
        return mProperties.get(key);
    }

    protected volatile Object getProtected(Object obj)
    {
        return getProtected((Key)obj);
    }

    public static final Key COLOR_CORRECTION_AVAILABLE_ABERRATION_MODES = new Key("android.colorCorrection.availableAberrationModes", [I);
    public static final Key CONTROL_AE_AVAILABLE_ANTIBANDING_MODES = new Key("android.control.aeAvailableAntibandingModes", [I);
    public static final Key CONTROL_AE_AVAILABLE_MODES = new Key("android.control.aeAvailableModes", [I);
    public static final Key CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES = new Key("android.control.aeAvailableTargetFpsRanges", new TypeReference() {

    }
);
    public static final Key CONTROL_AE_COMPENSATION_RANGE = new Key("android.control.aeCompensationRange", new TypeReference() {

    }
);
    public static final Key CONTROL_AE_COMPENSATION_STEP = new Key("android.control.aeCompensationStep", android/util/Rational);
    public static final Key CONTROL_AE_LOCK_AVAILABLE;
    public static final Key CONTROL_AF_AVAILABLE_MODES = new Key("android.control.afAvailableModes", [I);
    public static final Key CONTROL_AVAILABLE_EFFECTS = new Key("android.control.availableEffects", [I);
    public static final Key CONTROL_AVAILABLE_HIGH_SPEED_VIDEO_CONFIGURATIONS = new Key("android.control.availableHighSpeedVideoConfigurations", [Landroid/hardware/camera2/params/HighSpeedVideoConfiguration;);
    public static final Key CONTROL_AVAILABLE_MODES = new Key("android.control.availableModes", [I);
    public static final Key CONTROL_AVAILABLE_SCENE_MODES = new Key("android.control.availableSceneModes", [I);
    public static final Key CONTROL_AVAILABLE_VIDEO_STABILIZATION_MODES = new Key("android.control.availableVideoStabilizationModes", [I);
    public static final Key CONTROL_AWB_AVAILABLE_MODES = new Key("android.control.awbAvailableModes", [I);
    public static final Key CONTROL_AWB_LOCK_AVAILABLE;
    public static final Key CONTROL_MAX_REGIONS = new Key("android.control.maxRegions", [I);
    public static final Key CONTROL_MAX_REGIONS_AE;
    public static final Key CONTROL_MAX_REGIONS_AF;
    public static final Key CONTROL_MAX_REGIONS_AWB;
    public static final Key CONTROL_POST_RAW_SENSITIVITY_BOOST_RANGE = new Key("android.control.postRawSensitivityBoostRange", new TypeReference() {

    }
);
    public static final Key DEPTH_AVAILABLE_DEPTH_MIN_FRAME_DURATIONS = new Key("android.depth.availableDepthMinFrameDurations", [Landroid/hardware/camera2/params/StreamConfigurationDuration;);
    public static final Key DEPTH_AVAILABLE_DEPTH_STALL_DURATIONS = new Key("android.depth.availableDepthStallDurations", [Landroid/hardware/camera2/params/StreamConfigurationDuration;);
    public static final Key DEPTH_AVAILABLE_DEPTH_STREAM_CONFIGURATIONS = new Key("android.depth.availableDepthStreamConfigurations", [Landroid/hardware/camera2/params/StreamConfiguration;);
    public static final Key DEPTH_DEPTH_IS_EXCLUSIVE;
    public static final Key EDGE_AVAILABLE_EDGE_MODES = new Key("android.edge.availableEdgeModes", [I);
    public static final Key FLASH_INFO_AVAILABLE;
    public static final Key HOT_PIXEL_AVAILABLE_HOT_PIXEL_MODES = new Key("android.hotPixel.availableHotPixelModes", [I);
    public static final Key INFO_SUPPORTED_HARDWARE_LEVEL;
    public static final Key JPEG_AVAILABLE_THUMBNAIL_SIZES = new Key("android.jpeg.availableThumbnailSizes", [Landroid/util/Size;);
    public static final Key LED_AVAILABLE_LEDS = new Key("android.led.availableLeds", [I);
    public static final Key LENS_FACING;
    public static final Key LENS_INFO_AVAILABLE_APERTURES = new Key("android.lens.info.availableApertures", [F);
    public static final Key LENS_INFO_AVAILABLE_FILTER_DENSITIES = new Key("android.lens.info.availableFilterDensities", [F);
    public static final Key LENS_INFO_AVAILABLE_FOCAL_LENGTHS = new Key("android.lens.info.availableFocalLengths", [F);
    public static final Key LENS_INFO_AVAILABLE_OPTICAL_STABILIZATION = new Key("android.lens.info.availableOpticalStabilization", [I);
    public static final Key LENS_INFO_FOCUS_DISTANCE_CALIBRATION;
    public static final Key LENS_INFO_HYPERFOCAL_DISTANCE;
    public static final Key LENS_INFO_MINIMUM_FOCUS_DISTANCE;
    public static final Key LENS_INFO_SHADING_MAP_SIZE = new Key("android.lens.info.shadingMapSize", android/util/Size);
    public static final Key LENS_INTRINSIC_CALIBRATION = new Key("android.lens.intrinsicCalibration", [F);
    public static final Key LENS_POSE_ROTATION = new Key("android.lens.poseRotation", [F);
    public static final Key LENS_POSE_TRANSLATION = new Key("android.lens.poseTranslation", [F);
    public static final Key LENS_RADIAL_DISTORTION = new Key("android.lens.radialDistortion", [F);
    public static final Key NOISE_REDUCTION_AVAILABLE_NOISE_REDUCTION_MODES = new Key("android.noiseReduction.availableNoiseReductionModes", [I);
    public static final Key QUIRKS_USE_PARTIAL_RESULT;
    public static final Key REPROCESS_MAX_CAPTURE_STALL;
    public static final Key REQUEST_AVAILABLE_CAPABILITIES = new Key("android.request.availableCapabilities", [I);
    public static final Key REQUEST_AVAILABLE_CHARACTERISTICS_KEYS = new Key("android.request.availableCharacteristicsKeys", [I);
    public static final Key REQUEST_AVAILABLE_REQUEST_KEYS = new Key("android.request.availableRequestKeys", [I);
    public static final Key REQUEST_AVAILABLE_RESULT_KEYS = new Key("android.request.availableResultKeys", [I);
    public static final Key REQUEST_MAX_NUM_INPUT_STREAMS;
    public static final Key REQUEST_MAX_NUM_OUTPUT_PROC;
    public static final Key REQUEST_MAX_NUM_OUTPUT_PROC_STALLING;
    public static final Key REQUEST_MAX_NUM_OUTPUT_RAW;
    public static final Key REQUEST_MAX_NUM_OUTPUT_STREAMS = new Key("android.request.maxNumOutputStreams", [I);
    public static final Key REQUEST_PARTIAL_RESULT_COUNT;
    public static final Key REQUEST_PIPELINE_MAX_DEPTH;
    public static final Key SCALER_AVAILABLE_FORMATS = new Key("android.scaler.availableFormats", [I);
    public static final Key SCALER_AVAILABLE_INPUT_OUTPUT_FORMATS_MAP = new Key("android.scaler.availableInputOutputFormatsMap", android/hardware/camera2/params/ReprocessFormatsMap);
    public static final Key SCALER_AVAILABLE_JPEG_MIN_DURATIONS = new Key("android.scaler.availableJpegMinDurations", [J);
    public static final Key SCALER_AVAILABLE_JPEG_SIZES = new Key("android.scaler.availableJpegSizes", [Landroid/util/Size;);
    public static final Key SCALER_AVAILABLE_MAX_DIGITAL_ZOOM;
    public static final Key SCALER_AVAILABLE_MIN_FRAME_DURATIONS = new Key("android.scaler.availableMinFrameDurations", [Landroid/hardware/camera2/params/StreamConfigurationDuration;);
    public static final Key SCALER_AVAILABLE_PROCESSED_MIN_DURATIONS = new Key("android.scaler.availableProcessedMinDurations", [J);
    public static final Key SCALER_AVAILABLE_PROCESSED_SIZES = new Key("android.scaler.availableProcessedSizes", [Landroid/util/Size;);
    public static final Key SCALER_AVAILABLE_STALL_DURATIONS = new Key("android.scaler.availableStallDurations", [Landroid/hardware/camera2/params/StreamConfigurationDuration;);
    public static final Key SCALER_AVAILABLE_STREAM_CONFIGURATIONS = new Key("android.scaler.availableStreamConfigurations", [Landroid/hardware/camera2/params/StreamConfiguration;);
    public static final Key SCALER_CROPPING_TYPE;
    public static final Key SCALER_STREAM_CONFIGURATION_MAP = new Key("android.scaler.streamConfigurationMap", android/hardware/camera2/params/StreamConfigurationMap);
    public static final Key SENSOR_AVAILABLE_TEST_PATTERN_MODES = new Key("android.sensor.availableTestPatternModes", [I);
    public static final Key SENSOR_BLACK_LEVEL_PATTERN = new Key("android.sensor.blackLevelPattern", android/hardware/camera2/params/BlackLevelPattern);
    public static final Key SENSOR_CALIBRATION_TRANSFORM1 = new Key("android.sensor.calibrationTransform1", android/hardware/camera2/params/ColorSpaceTransform);
    public static final Key SENSOR_CALIBRATION_TRANSFORM2 = new Key("android.sensor.calibrationTransform2", android/hardware/camera2/params/ColorSpaceTransform);
    public static final Key SENSOR_COLOR_TRANSFORM1 = new Key("android.sensor.colorTransform1", android/hardware/camera2/params/ColorSpaceTransform);
    public static final Key SENSOR_COLOR_TRANSFORM2 = new Key("android.sensor.colorTransform2", android/hardware/camera2/params/ColorSpaceTransform);
    public static final Key SENSOR_FORWARD_MATRIX1 = new Key("android.sensor.forwardMatrix1", android/hardware/camera2/params/ColorSpaceTransform);
    public static final Key SENSOR_FORWARD_MATRIX2 = new Key("android.sensor.forwardMatrix2", android/hardware/camera2/params/ColorSpaceTransform);
    public static final Key SENSOR_INFO_ACTIVE_ARRAY_SIZE = new Key("android.sensor.info.activeArraySize", android/graphics/Rect);
    public static final Key SENSOR_INFO_COLOR_FILTER_ARRANGEMENT;
    public static final Key SENSOR_INFO_EXPOSURE_TIME_RANGE = new Key("android.sensor.info.exposureTimeRange", new TypeReference() {

    }
);
    public static final Key SENSOR_INFO_LENS_SHADING_APPLIED;
    public static final Key SENSOR_INFO_MAX_FRAME_DURATION;
    public static final Key SENSOR_INFO_PHYSICAL_SIZE = new Key("android.sensor.info.physicalSize", android/util/SizeF);
    public static final Key SENSOR_INFO_PIXEL_ARRAY_SIZE = new Key("android.sensor.info.pixelArraySize", android/util/Size);
    public static final Key SENSOR_INFO_PRE_CORRECTION_ACTIVE_ARRAY_SIZE = new Key("android.sensor.info.preCorrectionActiveArraySize", android/graphics/Rect);
    public static final Key SENSOR_INFO_SENSITIVITY_RANGE = new Key("android.sensor.info.sensitivityRange", new TypeReference() {

    }
);
    public static final Key SENSOR_INFO_TIMESTAMP_SOURCE;
    public static final Key SENSOR_INFO_WHITE_LEVEL;
    public static final Key SENSOR_MAX_ANALOG_SENSITIVITY;
    public static final Key SENSOR_OPTICAL_BLACK_REGIONS = new Key("android.sensor.opticalBlackRegions", [Landroid/graphics/Rect;);
    public static final Key SENSOR_ORIENTATION;
    public static final Key SENSOR_REFERENCE_ILLUMINANT1;
    public static final Key SENSOR_REFERENCE_ILLUMINANT2;
    public static final Key SHADING_AVAILABLE_MODES = new Key("android.shading.availableModes", [I);
    public static final Key STATISTICS_INFO_AVAILABLE_FACE_DETECT_MODES = new Key("android.statistics.info.availableFaceDetectModes", [I);
    public static final Key STATISTICS_INFO_AVAILABLE_HOT_PIXEL_MAP_MODES = new Key("android.statistics.info.availableHotPixelMapModes", [Z);
    public static final Key STATISTICS_INFO_AVAILABLE_LENS_SHADING_MAP_MODES = new Key("android.statistics.info.availableLensShadingMapModes", [I);
    public static final Key STATISTICS_INFO_MAX_FACE_COUNT;
    public static final Key SYNC_MAX_LATENCY;
    public static final Key TONEMAP_AVAILABLE_TONE_MAP_MODES = new Key("android.tonemap.availableToneMapModes", [I);
    public static final Key TONEMAP_MAX_CURVE_POINTS;
    private List mAvailableRequestKeys;
    private List mAvailableResultKeys;
    private List mKeys;
    private final CameraMetadataNative mProperties;

    static 
    {
        CONTROL_MAX_REGIONS_AE = new Key("android.control.maxRegionsAe", Integer.TYPE);
        CONTROL_MAX_REGIONS_AWB = new Key("android.control.maxRegionsAwb", Integer.TYPE);
        CONTROL_MAX_REGIONS_AF = new Key("android.control.maxRegionsAf", Integer.TYPE);
        CONTROL_AE_LOCK_AVAILABLE = new Key("android.control.aeLockAvailable", Boolean.TYPE);
        CONTROL_AWB_LOCK_AVAILABLE = new Key("android.control.awbLockAvailable", Boolean.TYPE);
        FLASH_INFO_AVAILABLE = new Key("android.flash.info.available", Boolean.TYPE);
        LENS_INFO_HYPERFOCAL_DISTANCE = new Key("android.lens.info.hyperfocalDistance", Float.TYPE);
        LENS_INFO_MINIMUM_FOCUS_DISTANCE = new Key("android.lens.info.minimumFocusDistance", Float.TYPE);
        LENS_INFO_FOCUS_DISTANCE_CALIBRATION = new Key("android.lens.info.focusDistanceCalibration", Integer.TYPE);
        LENS_FACING = new Key("android.lens.facing", Integer.TYPE);
        QUIRKS_USE_PARTIAL_RESULT = new Key("android.quirks.usePartialResult", Byte.TYPE);
        REQUEST_MAX_NUM_OUTPUT_RAW = new Key("android.request.maxNumOutputRaw", Integer.TYPE);
        REQUEST_MAX_NUM_OUTPUT_PROC = new Key("android.request.maxNumOutputProc", Integer.TYPE);
        REQUEST_MAX_NUM_OUTPUT_PROC_STALLING = new Key("android.request.maxNumOutputProcStalling", Integer.TYPE);
        REQUEST_MAX_NUM_INPUT_STREAMS = new Key("android.request.maxNumInputStreams", Integer.TYPE);
        REQUEST_PIPELINE_MAX_DEPTH = new Key("android.request.pipelineMaxDepth", Byte.TYPE);
        REQUEST_PARTIAL_RESULT_COUNT = new Key("android.request.partialResultCount", Integer.TYPE);
        SCALER_AVAILABLE_MAX_DIGITAL_ZOOM = new Key("android.scaler.availableMaxDigitalZoom", Float.TYPE);
        SCALER_CROPPING_TYPE = new Key("android.scaler.croppingType", Integer.TYPE);
        SENSOR_INFO_COLOR_FILTER_ARRANGEMENT = new Key("android.sensor.info.colorFilterArrangement", Integer.TYPE);
        SENSOR_INFO_MAX_FRAME_DURATION = new Key("android.sensor.info.maxFrameDuration", Long.TYPE);
        SENSOR_INFO_WHITE_LEVEL = new Key("android.sensor.info.whiteLevel", Integer.TYPE);
        SENSOR_INFO_TIMESTAMP_SOURCE = new Key("android.sensor.info.timestampSource", Integer.TYPE);
        SENSOR_INFO_LENS_SHADING_APPLIED = new Key("android.sensor.info.lensShadingApplied", Boolean.TYPE);
        SENSOR_REFERENCE_ILLUMINANT1 = new Key("android.sensor.referenceIlluminant1", Integer.TYPE);
        SENSOR_REFERENCE_ILLUMINANT2 = new Key("android.sensor.referenceIlluminant2", Byte.TYPE);
        SENSOR_MAX_ANALOG_SENSITIVITY = new Key("android.sensor.maxAnalogSensitivity", Integer.TYPE);
        SENSOR_ORIENTATION = new Key("android.sensor.orientation", Integer.TYPE);
        STATISTICS_INFO_MAX_FACE_COUNT = new Key("android.statistics.info.maxFaceCount", Integer.TYPE);
        TONEMAP_MAX_CURVE_POINTS = new Key("android.tonemap.maxCurvePoints", Integer.TYPE);
        INFO_SUPPORTED_HARDWARE_LEVEL = new Key("android.info.supportedHardwareLevel", Integer.TYPE);
        SYNC_MAX_LATENCY = new Key("android.sync.maxLatency", Integer.TYPE);
        REPROCESS_MAX_CAPTURE_STALL = new Key("android.reprocess.maxCaptureStall", Integer.TYPE);
        DEPTH_DEPTH_IS_EXCLUSIVE = new Key("android.depth.depthIsExclusive", Boolean.TYPE);
    }
}
