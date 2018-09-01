// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2;

import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.impl.PublicKey;
import android.hardware.camera2.impl.SyntheticKey;
import java.lang.reflect.Field;
import java.util.*;

// Referenced classes of package android.hardware.camera2:
//            TotalCaptureResult, CaptureResult

public abstract class CameraMetadata
{

    protected CameraMetadata()
    {
        mNativeInstance = null;
    }

    private static boolean shouldKeyBeAdded(Object obj, Field field, int ai[])
    {
        boolean flag = true;
        if(obj == null)
            throw new NullPointerException("key must not be null");
        if(obj instanceof CameraCharacteristics.Key)
            obj = ((CameraCharacteristics.Key)obj).getNativeKey();
        else
        if(obj instanceof CaptureResult.Key)
            obj = ((CaptureResult.Key)obj).getNativeKey();
        else
        if(obj instanceof CaptureRequest.Key)
            obj = ((CaptureRequest.Key)obj).getNativeKey();
        else
            throw new IllegalArgumentException("key type must be that of a metadata key");
        if(field.getAnnotation(android/hardware/camera2/impl/PublicKey) == null)
            return false;
        if(ai == null)
            return true;
        if(field.getAnnotation(android/hardware/camera2/impl/SyntheticKey) != null)
            return true;
        if(Arrays.binarySearch(ai, ((android.hardware.camera2.impl.CameraMetadataNative.Key) (obj)).getTag()) < 0)
            flag = false;
        return flag;
    }

    protected abstract Class getKeyClass();

    ArrayList getKeys(Class class1, Class class2, CameraMetadata camerametadata, int ai[])
    {
        ArrayList arraylist;
        Object obj = class1;
        if(class1.equals(android/hardware/camera2/TotalCaptureResult))
            obj = android/hardware/camera2/CaptureResult;
        if(ai != null)
            Arrays.sort(ai);
        arraylist = new ArrayList();
        Field afield[] = ((Class) (obj)).getDeclaredFields();
        int i = 0;
        int j = afield.length;
        while(i < j) 
        {
            Field field = afield[i];
            if(!field.getType().isAssignableFrom(class2) || (field.getModifiers() & 1) == 0)
                continue;
            try
            {
                class1 = ((Class) (field.get(camerametadata)));
            }
            // Misplaced declaration of an exception variable
            catch(Class class1)
            {
                throw new AssertionError("Can't get IllegalAccessException", class1);
            }
            // Misplaced declaration of an exception variable
            catch(Class class1)
            {
                throw new AssertionError("Can't get IllegalArgumentException", class1);
            }
            if((camerametadata == null || camerametadata.getProtected(class1) != null) && shouldKeyBeAdded(class1, field, ai))
                arraylist.add(class1);
            i++;
        }
        if(mNativeInstance == null)
            return arraylist;
        class1 = mNativeInstance.getAllVendorKeys(class2);
        if(class1 == null) goto _L2; else goto _L1
_L1:
        camerametadata = class1.iterator();
_L4:
        if(camerametadata.hasNext())
        {
            class2 = ((Class) (camerametadata.next()));
            long l;
            if(class2 instanceof CaptureRequest.Key)
            {
                class1 = ((CaptureRequest.Key)class2).getName();
                l = ((CaptureRequest.Key)class2).getVendorId();
            } else
            if(class2 instanceof CaptureResult.Key)
            {
                class1 = ((CaptureResult.Key)class2).getName();
                l = ((CaptureResult.Key)class2).getVendorId();
            } else
            {
                if(!(class2 instanceof CameraCharacteristics.Key))
                    continue; /* Loop/switch isn't completed */
                class1 = ((CameraCharacteristics.Key)class2).getName();
                l = ((CameraCharacteristics.Key)class2).getVendorId();
            }
            if(ai == null || Arrays.binarySearch(ai, CameraMetadataNative.getTag(class1, l)) >= 0)
                arraylist.add(class2);
            continue; /* Loop/switch isn't completed */
        }
_L2:
        return arraylist;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public List getKeys()
    {
        return Collections.unmodifiableList(getKeys(getClass(), getKeyClass(), this, null));
    }

    protected abstract Object getProtected(Object obj);

    protected void setNativeInstance(CameraMetadataNative camerametadatanative)
    {
        mNativeInstance = camerametadatanative;
    }

    public static final int COLOR_CORRECTION_ABERRATION_MODE_FAST = 1;
    public static final int COLOR_CORRECTION_ABERRATION_MODE_HIGH_QUALITY = 2;
    public static final int COLOR_CORRECTION_ABERRATION_MODE_OFF = 0;
    public static final int COLOR_CORRECTION_MODE_FAST = 1;
    public static final int COLOR_CORRECTION_MODE_HIGH_QUALITY = 2;
    public static final int COLOR_CORRECTION_MODE_TRANSFORM_MATRIX = 0;
    public static final int CONTROL_AE_ANTIBANDING_MODE_50HZ = 1;
    public static final int CONTROL_AE_ANTIBANDING_MODE_60HZ = 2;
    public static final int CONTROL_AE_ANTIBANDING_MODE_AUTO = 3;
    public static final int CONTROL_AE_ANTIBANDING_MODE_OFF = 0;
    public static final int CONTROL_AE_MODE_OFF = 0;
    public static final int CONTROL_AE_MODE_ON = 1;
    public static final int CONTROL_AE_MODE_ON_ALWAYS_FLASH = 3;
    public static final int CONTROL_AE_MODE_ON_AUTO_FLASH = 2;
    public static final int CONTROL_AE_MODE_ON_AUTO_FLASH_REDEYE = 4;
    public static final int CONTROL_AE_PRECAPTURE_TRIGGER_CANCEL = 2;
    public static final int CONTROL_AE_PRECAPTURE_TRIGGER_IDLE = 0;
    public static final int CONTROL_AE_PRECAPTURE_TRIGGER_START = 1;
    public static final int CONTROL_AE_STATE_CONVERGED = 2;
    public static final int CONTROL_AE_STATE_FLASH_REQUIRED = 4;
    public static final int CONTROL_AE_STATE_INACTIVE = 0;
    public static final int CONTROL_AE_STATE_LOCKED = 3;
    public static final int CONTROL_AE_STATE_PRECAPTURE = 5;
    public static final int CONTROL_AE_STATE_SEARCHING = 1;
    public static final int CONTROL_AF_MODE_AUTO = 1;
    public static final int CONTROL_AF_MODE_CONTINUOUS_PICTURE = 4;
    public static final int CONTROL_AF_MODE_CONTINUOUS_VIDEO = 3;
    public static final int CONTROL_AF_MODE_EDOF = 5;
    public static final int CONTROL_AF_MODE_MACRO = 2;
    public static final int CONTROL_AF_MODE_OFF = 0;
    public static final int CONTROL_AF_STATE_ACTIVE_SCAN = 3;
    public static final int CONTROL_AF_STATE_FOCUSED_LOCKED = 4;
    public static final int CONTROL_AF_STATE_INACTIVE = 0;
    public static final int CONTROL_AF_STATE_NOT_FOCUSED_LOCKED = 5;
    public static final int CONTROL_AF_STATE_PASSIVE_FOCUSED = 2;
    public static final int CONTROL_AF_STATE_PASSIVE_SCAN = 1;
    public static final int CONTROL_AF_STATE_PASSIVE_UNFOCUSED = 6;
    public static final int CONTROL_AF_TRIGGER_CANCEL = 2;
    public static final int CONTROL_AF_TRIGGER_IDLE = 0;
    public static final int CONTROL_AF_TRIGGER_START = 1;
    public static final int CONTROL_AWB_MODE_AUTO = 1;
    public static final int CONTROL_AWB_MODE_CLOUDY_DAYLIGHT = 6;
    public static final int CONTROL_AWB_MODE_DAYLIGHT = 5;
    public static final int CONTROL_AWB_MODE_FLUORESCENT = 3;
    public static final int CONTROL_AWB_MODE_INCANDESCENT = 2;
    public static final int CONTROL_AWB_MODE_OFF = 0;
    public static final int CONTROL_AWB_MODE_SHADE = 8;
    public static final int CONTROL_AWB_MODE_TWILIGHT = 7;
    public static final int CONTROL_AWB_MODE_WARM_FLUORESCENT = 4;
    public static final int CONTROL_AWB_STATE_CONVERGED = 2;
    public static final int CONTROL_AWB_STATE_INACTIVE = 0;
    public static final int CONTROL_AWB_STATE_LOCKED = 3;
    public static final int CONTROL_AWB_STATE_SEARCHING = 1;
    public static final int CONTROL_CAPTURE_INTENT_CUSTOM = 0;
    public static final int CONTROL_CAPTURE_INTENT_MANUAL = 6;
    public static final int CONTROL_CAPTURE_INTENT_PREVIEW = 1;
    public static final int CONTROL_CAPTURE_INTENT_STILL_CAPTURE = 2;
    public static final int CONTROL_CAPTURE_INTENT_VIDEO_RECORD = 3;
    public static final int CONTROL_CAPTURE_INTENT_VIDEO_SNAPSHOT = 4;
    public static final int CONTROL_CAPTURE_INTENT_ZERO_SHUTTER_LAG = 5;
    public static final int CONTROL_EFFECT_MODE_AQUA = 8;
    public static final int CONTROL_EFFECT_MODE_BLACKBOARD = 7;
    public static final int CONTROL_EFFECT_MODE_MONO = 1;
    public static final int CONTROL_EFFECT_MODE_NEGATIVE = 2;
    public static final int CONTROL_EFFECT_MODE_OFF = 0;
    public static final int CONTROL_EFFECT_MODE_POSTERIZE = 5;
    public static final int CONTROL_EFFECT_MODE_SEPIA = 4;
    public static final int CONTROL_EFFECT_MODE_SOLARIZE = 3;
    public static final int CONTROL_EFFECT_MODE_WHITEBOARD = 6;
    public static final int CONTROL_MODE_AUTO = 1;
    public static final int CONTROL_MODE_OFF = 0;
    public static final int CONTROL_MODE_OFF_KEEP_STATE = 3;
    public static final int CONTROL_MODE_USE_SCENE_MODE = 2;
    public static final int CONTROL_SCENE_MODE_ACTION = 2;
    public static final int CONTROL_SCENE_MODE_BARCODE = 16;
    public static final int CONTROL_SCENE_MODE_BEACH = 8;
    public static final int CONTROL_SCENE_MODE_CANDLELIGHT = 15;
    public static final int CONTROL_SCENE_MODE_DEVICE_CUSTOM_END = 127;
    public static final int CONTROL_SCENE_MODE_DEVICE_CUSTOM_START = 100;
    public static final int CONTROL_SCENE_MODE_DISABLED = 0;
    public static final int CONTROL_SCENE_MODE_FACE_PRIORITY = 1;
    public static final int CONTROL_SCENE_MODE_FACE_PRIORITY_LOW_LIGHT = 19;
    public static final int CONTROL_SCENE_MODE_FIREWORKS = 12;
    public static final int CONTROL_SCENE_MODE_HDR = 18;
    public static final int CONTROL_SCENE_MODE_HIGH_SPEED_VIDEO = 17;
    public static final int CONTROL_SCENE_MODE_LANDSCAPE = 4;
    public static final int CONTROL_SCENE_MODE_NIGHT = 5;
    public static final int CONTROL_SCENE_MODE_NIGHT_PORTRAIT = 6;
    public static final int CONTROL_SCENE_MODE_PARTY = 14;
    public static final int CONTROL_SCENE_MODE_PORTRAIT = 3;
    public static final int CONTROL_SCENE_MODE_SNOW = 9;
    public static final int CONTROL_SCENE_MODE_SPORTS = 13;
    public static final int CONTROL_SCENE_MODE_STEADYPHOTO = 11;
    public static final int CONTROL_SCENE_MODE_SUNSET = 10;
    public static final int CONTROL_SCENE_MODE_THEATRE = 7;
    public static final int CONTROL_VIDEO_STABILIZATION_MODE_OFF = 0;
    public static final int CONTROL_VIDEO_STABILIZATION_MODE_ON = 1;
    private static final boolean DEBUG = false;
    public static final int EDGE_MODE_FAST = 1;
    public static final int EDGE_MODE_HIGH_QUALITY = 2;
    public static final int EDGE_MODE_OFF = 0;
    public static final int EDGE_MODE_ZERO_SHUTTER_LAG = 3;
    public static final int FLASH_MODE_OFF = 0;
    public static final int FLASH_MODE_SINGLE = 1;
    public static final int FLASH_MODE_TORCH = 2;
    public static final int FLASH_STATE_CHARGING = 1;
    public static final int FLASH_STATE_FIRED = 3;
    public static final int FLASH_STATE_PARTIAL = 4;
    public static final int FLASH_STATE_READY = 2;
    public static final int FLASH_STATE_UNAVAILABLE = 0;
    public static final int HOT_PIXEL_MODE_FAST = 1;
    public static final int HOT_PIXEL_MODE_HIGH_QUALITY = 2;
    public static final int HOT_PIXEL_MODE_OFF = 0;
    public static final int INFO_SUPPORTED_HARDWARE_LEVEL_3 = 3;
    public static final int INFO_SUPPORTED_HARDWARE_LEVEL_FULL = 1;
    public static final int INFO_SUPPORTED_HARDWARE_LEVEL_LEGACY = 2;
    public static final int INFO_SUPPORTED_HARDWARE_LEVEL_LIMITED = 0;
    public static final int LED_AVAILABLE_LEDS_TRANSMIT = 0;
    public static final int LENS_FACING_BACK = 1;
    public static final int LENS_FACING_EXTERNAL = 2;
    public static final int LENS_FACING_FRONT = 0;
    public static final int LENS_INFO_FOCUS_DISTANCE_CALIBRATION_APPROXIMATE = 1;
    public static final int LENS_INFO_FOCUS_DISTANCE_CALIBRATION_CALIBRATED = 2;
    public static final int LENS_INFO_FOCUS_DISTANCE_CALIBRATION_UNCALIBRATED = 0;
    public static final int LENS_OPTICAL_STABILIZATION_MODE_OFF = 0;
    public static final int LENS_OPTICAL_STABILIZATION_MODE_ON = 1;
    public static final int LENS_STATE_MOVING = 1;
    public static final int LENS_STATE_STATIONARY = 0;
    public static final int NOISE_REDUCTION_MODE_FAST = 1;
    public static final int NOISE_REDUCTION_MODE_HIGH_QUALITY = 2;
    public static final int NOISE_REDUCTION_MODE_MINIMAL = 3;
    public static final int NOISE_REDUCTION_MODE_OFF = 0;
    public static final int NOISE_REDUCTION_MODE_ZERO_SHUTTER_LAG = 4;
    public static final int REQUEST_AVAILABLE_CAPABILITIES_BACKWARD_COMPATIBLE = 0;
    public static final int REQUEST_AVAILABLE_CAPABILITIES_BURST_CAPTURE = 6;
    public static final int REQUEST_AVAILABLE_CAPABILITIES_CONSTRAINED_HIGH_SPEED_VIDEO = 9;
    public static final int REQUEST_AVAILABLE_CAPABILITIES_DEPTH_OUTPUT = 8;
    public static final int REQUEST_AVAILABLE_CAPABILITIES_MANUAL_POST_PROCESSING = 2;
    public static final int REQUEST_AVAILABLE_CAPABILITIES_MANUAL_SENSOR = 1;
    public static final int REQUEST_AVAILABLE_CAPABILITIES_PRIVATE_REPROCESSING = 4;
    public static final int REQUEST_AVAILABLE_CAPABILITIES_RAW = 3;
    public static final int REQUEST_AVAILABLE_CAPABILITIES_READ_SENSOR_SETTINGS = 5;
    public static final int REQUEST_AVAILABLE_CAPABILITIES_YUV_REPROCESSING = 7;
    public static final int SCALER_CROPPING_TYPE_CENTER_ONLY = 0;
    public static final int SCALER_CROPPING_TYPE_FREEFORM = 1;
    public static final int SENSOR_INFO_COLOR_FILTER_ARRANGEMENT_BGGR = 3;
    public static final int SENSOR_INFO_COLOR_FILTER_ARRANGEMENT_GBRG = 2;
    public static final int SENSOR_INFO_COLOR_FILTER_ARRANGEMENT_GRBG = 1;
    public static final int SENSOR_INFO_COLOR_FILTER_ARRANGEMENT_RGB = 4;
    public static final int SENSOR_INFO_COLOR_FILTER_ARRANGEMENT_RGGB = 0;
    public static final int SENSOR_INFO_TIMESTAMP_SOURCE_REALTIME = 1;
    public static final int SENSOR_INFO_TIMESTAMP_SOURCE_UNKNOWN = 0;
    public static final int SENSOR_REFERENCE_ILLUMINANT1_CLOUDY_WEATHER = 10;
    public static final int SENSOR_REFERENCE_ILLUMINANT1_COOL_WHITE_FLUORESCENT = 14;
    public static final int SENSOR_REFERENCE_ILLUMINANT1_D50 = 23;
    public static final int SENSOR_REFERENCE_ILLUMINANT1_D55 = 20;
    public static final int SENSOR_REFERENCE_ILLUMINANT1_D65 = 21;
    public static final int SENSOR_REFERENCE_ILLUMINANT1_D75 = 22;
    public static final int SENSOR_REFERENCE_ILLUMINANT1_DAYLIGHT = 1;
    public static final int SENSOR_REFERENCE_ILLUMINANT1_DAYLIGHT_FLUORESCENT = 12;
    public static final int SENSOR_REFERENCE_ILLUMINANT1_DAY_WHITE_FLUORESCENT = 13;
    public static final int SENSOR_REFERENCE_ILLUMINANT1_FINE_WEATHER = 9;
    public static final int SENSOR_REFERENCE_ILLUMINANT1_FLASH = 4;
    public static final int SENSOR_REFERENCE_ILLUMINANT1_FLUORESCENT = 2;
    public static final int SENSOR_REFERENCE_ILLUMINANT1_ISO_STUDIO_TUNGSTEN = 24;
    public static final int SENSOR_REFERENCE_ILLUMINANT1_SHADE = 11;
    public static final int SENSOR_REFERENCE_ILLUMINANT1_STANDARD_A = 17;
    public static final int SENSOR_REFERENCE_ILLUMINANT1_STANDARD_B = 18;
    public static final int SENSOR_REFERENCE_ILLUMINANT1_STANDARD_C = 19;
    public static final int SENSOR_REFERENCE_ILLUMINANT1_TUNGSTEN = 3;
    public static final int SENSOR_REFERENCE_ILLUMINANT1_WHITE_FLUORESCENT = 15;
    public static final int SENSOR_TEST_PATTERN_MODE_COLOR_BARS = 2;
    public static final int SENSOR_TEST_PATTERN_MODE_COLOR_BARS_FADE_TO_GRAY = 3;
    public static final int SENSOR_TEST_PATTERN_MODE_CUSTOM1 = 256;
    public static final int SENSOR_TEST_PATTERN_MODE_OFF = 0;
    public static final int SENSOR_TEST_PATTERN_MODE_PN9 = 4;
    public static final int SENSOR_TEST_PATTERN_MODE_SOLID_COLOR = 1;
    public static final int SHADING_MODE_FAST = 1;
    public static final int SHADING_MODE_HIGH_QUALITY = 2;
    public static final int SHADING_MODE_OFF = 0;
    public static final int STATISTICS_FACE_DETECT_MODE_FULL = 2;
    public static final int STATISTICS_FACE_DETECT_MODE_OFF = 0;
    public static final int STATISTICS_FACE_DETECT_MODE_SIMPLE = 1;
    public static final int STATISTICS_LENS_SHADING_MAP_MODE_OFF = 0;
    public static final int STATISTICS_LENS_SHADING_MAP_MODE_ON = 1;
    public static final int STATISTICS_SCENE_FLICKER_50HZ = 1;
    public static final int STATISTICS_SCENE_FLICKER_60HZ = 2;
    public static final int STATISTICS_SCENE_FLICKER_NONE = 0;
    public static final int SYNC_FRAME_NUMBER_CONVERGING = -1;
    public static final int SYNC_FRAME_NUMBER_UNKNOWN = -2;
    public static final int SYNC_MAX_LATENCY_PER_FRAME_CONTROL = 0;
    public static final int SYNC_MAX_LATENCY_UNKNOWN = -1;
    private static final String TAG = "CameraMetadataAb";
    public static final int TONEMAP_MODE_CONTRAST_CURVE = 0;
    public static final int TONEMAP_MODE_FAST = 1;
    public static final int TONEMAP_MODE_GAMMA_VALUE = 3;
    public static final int TONEMAP_MODE_HIGH_QUALITY = 2;
    public static final int TONEMAP_MODE_PRESET_CURVE = 4;
    public static final int TONEMAP_PRESET_CURVE_REC709 = 1;
    public static final int TONEMAP_PRESET_CURVE_SRGB = 0;
    private CameraMetadataNative mNativeInstance;
}
