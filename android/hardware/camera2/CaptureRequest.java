// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2;

import android.graphics.Rect;
import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.params.ColorSpaceTransform;
import android.hardware.camera2.params.RggbChannelVector;
import android.hardware.camera2.params.TonemapCurve;
import android.hardware.camera2.utils.HashCodeHelpers;
import android.hardware.camera2.utils.TypeReference;
import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Size;
import android.view.Surface;
import java.util.*;

// Referenced classes of package android.hardware.camera2:
//            CameraMetadata

public final class CaptureRequest extends CameraMetadata
    implements Parcelable
{
    public static final class Builder
    {

        public void addTarget(Surface surface)
        {
            CaptureRequest._2D_get1(mRequest).add(surface);
        }

        public CaptureRequest build()
        {
            return new CaptureRequest(mRequest, null, null);
        }

        public Object get(Key key)
        {
            return CaptureRequest._2D_get0(mRequest).get(key);
        }

        public boolean isEmpty()
        {
            return CaptureRequest._2D_get0(mRequest).isEmpty();
        }

        public void removeTarget(Surface surface)
        {
            CaptureRequest._2D_get1(mRequest).remove(surface);
        }

        public void set(Key key, Object obj)
        {
            CaptureRequest._2D_get0(mRequest).set(key, obj);
        }

        public void setPartOfCHSRequestList(boolean flag)
        {
            CaptureRequest._2D_set0(mRequest, flag);
        }

        public void setTag(Object obj)
        {
            CaptureRequest._2D_set1(mRequest, obj);
        }

        private final CaptureRequest mRequest;

        public Builder(CameraMetadataNative camerametadatanative, boolean flag, int i)
        {
            mRequest = new CaptureRequest(camerametadatanative, flag, i, null);
        }
    }

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
            return String.format("CaptureRequest.Key(%s)", new Object[] {
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


    static CameraMetadataNative _2D_get0(CaptureRequest capturerequest)
    {
        return capturerequest.mSettings;
    }

    static HashSet _2D_get1(CaptureRequest capturerequest)
    {
        return capturerequest.mSurfaceSet;
    }

    static boolean _2D_set0(CaptureRequest capturerequest, boolean flag)
    {
        capturerequest.mIsPartOfCHSRequestList = flag;
        return flag;
    }

    static Object _2D_set1(CaptureRequest capturerequest, Object obj)
    {
        capturerequest.mUserTag = obj;
        return obj;
    }

    static void _2D_wrap0(CaptureRequest capturerequest, Parcel parcel)
    {
        capturerequest.readFromParcel(parcel);
    }

    private CaptureRequest()
    {
        mIsPartOfCHSRequestList = false;
        mSettings = new CameraMetadataNative();
        setNativeInstance(mSettings);
        mSurfaceSet = new HashSet();
        mIsReprocess = false;
        mReprocessableSessionId = -1;
    }

    private CaptureRequest(CaptureRequest capturerequest)
    {
        mIsPartOfCHSRequestList = false;
        mSettings = new CameraMetadataNative(capturerequest.mSettings);
        setNativeInstance(mSettings);
        mSurfaceSet = (HashSet)capturerequest.mSurfaceSet.clone();
        mIsReprocess = capturerequest.mIsReprocess;
        mIsPartOfCHSRequestList = capturerequest.mIsPartOfCHSRequestList;
        mReprocessableSessionId = capturerequest.mReprocessableSessionId;
        mUserTag = capturerequest.mUserTag;
    }

    CaptureRequest(CaptureRequest capturerequest, CaptureRequest capturerequest1)
    {
        this();
    }

    CaptureRequest(CaptureRequest capturerequest, CaptureRequest capturerequest1, CaptureRequest capturerequest2)
    {
        this(capturerequest);
    }

    private CaptureRequest(CameraMetadataNative camerametadatanative, boolean flag, int i)
    {
        mIsPartOfCHSRequestList = false;
        mSettings = CameraMetadataNative.move(camerametadatanative);
        setNativeInstance(mSettings);
        mSurfaceSet = new HashSet();
        mIsReprocess = flag;
        if(flag)
        {
            if(i == -1)
                throw new IllegalArgumentException((new StringBuilder()).append("Create a reprocess capture request with an invalid session ID: ").append(i).toString());
            mReprocessableSessionId = i;
        } else
        {
            mReprocessableSessionId = -1;
        }
    }

    CaptureRequest(CameraMetadataNative camerametadatanative, boolean flag, int i, CaptureRequest capturerequest)
    {
        this(camerametadatanative, flag, i);
    }

    private boolean equals(CaptureRequest capturerequest)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(capturerequest != null)
        {
            flag1 = flag;
            if(Objects.equals(mUserTag, capturerequest.mUserTag))
            {
                flag1 = flag;
                if(mSurfaceSet.equals(capturerequest.mSurfaceSet))
                {
                    flag1 = flag;
                    if(mSettings.equals(capturerequest.mSettings))
                    {
                        flag1 = flag;
                        if(mIsReprocess == capturerequest.mIsReprocess)
                        {
                            flag1 = flag;
                            if(mReprocessableSessionId == capturerequest.mReprocessableSessionId)
                                flag1 = true;
                        }
                    }
                }
            }
        }
        return flag1;
    }

    private void readFromParcel(Parcel parcel)
    {
        boolean flag = false;
        mSettings.readFromParcel(parcel);
        setNativeInstance(mSettings);
        mSurfaceSet.clear();
        Parcelable aparcelable[] = parcel.readParcelableArray(android/view/Surface.getClassLoader());
        if(aparcelable == null)
            return;
        int i = aparcelable.length;
        for(int j = 0; j < i; j++)
        {
            Surface surface = (Surface)aparcelable[j];
            mSurfaceSet.add(surface);
        }

        if(parcel.readInt() != 0)
            flag = true;
        mIsReprocess = flag;
        mReprocessableSessionId = -1;
    }

    public boolean containsTarget(Surface surface)
    {
        return mSurfaceSet.contains(surface);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag;
        if(obj instanceof CaptureRequest)
            flag = equals((CaptureRequest)obj);
        else
            flag = false;
        return flag;
    }

    public Object get(Key key)
    {
        return mSettings.get(key);
    }

    protected Class getKeyClass()
    {
        return (Class)android/hardware/camera2/CaptureRequest$Key;
    }

    public List getKeys()
    {
        return super.getKeys();
    }

    public CameraMetadataNative getNativeCopy()
    {
        return new CameraMetadataNative(mSettings);
    }

    protected Object getProtected(Key key)
    {
        return mSettings.get(key);
    }

    protected volatile Object getProtected(Object obj)
    {
        return getProtected((Key)obj);
    }

    public int getReprocessableSessionId()
    {
        if(!mIsReprocess || mReprocessableSessionId == -1)
            throw new IllegalStateException("Getting the reprocessable session ID for a non-reprocess capture request is illegal.");
        else
            return mReprocessableSessionId;
    }

    public Object getTag()
    {
        return mUserTag;
    }

    public Collection getTargets()
    {
        return Collections.unmodifiableCollection(mSurfaceSet);
    }

    public int hashCode()
    {
        return HashCodeHelpers.hashCodeGeneric(new Object[] {
            mSettings, mSurfaceSet, mUserTag
        });
    }

    public boolean isPartOfCRequestList()
    {
        return mIsPartOfCHSRequestList;
    }

    public boolean isReprocess()
    {
        return mIsReprocess;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        mSettings.writeToParcel(parcel, i);
        parcel.writeParcelableArray((Surface[])mSurfaceSet.toArray(new Surface[mSurfaceSet.size()]), i);
        if(mIsReprocess)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
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
    public static final Key CONTROL_AE_TARGET_FPS_RANGE = new Key("android.control.aeTargetFpsRange", new TypeReference() {

    }
);
    public static final Key CONTROL_AF_MODE;
    public static final Key CONTROL_AF_REGIONS = new Key("android.control.afRegions", [Landroid/hardware/camera2/params/MeteringRectangle;);
    public static final Key CONTROL_AF_TRIGGER;
    public static final Key CONTROL_AWB_LOCK;
    public static final Key CONTROL_AWB_MODE;
    public static final Key CONTROL_AWB_REGIONS = new Key("android.control.awbRegions", [Landroid/hardware/camera2/params/MeteringRectangle;);
    public static final Key CONTROL_CAPTURE_INTENT;
    public static final Key CONTROL_EFFECT_MODE;
    public static final Key CONTROL_ENABLE_ZSL;
    public static final Key CONTROL_MODE;
    public static final Key CONTROL_POST_RAW_SENSITIVITY_BOOST;
    public static final Key CONTROL_SCENE_MODE;
    public static final Key CONTROL_VIDEO_STABILIZATION_MODE;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public CaptureRequest createFromParcel(Parcel parcel)
        {
            CaptureRequest capturerequest = new CaptureRequest(null, null);
            CaptureRequest._2D_wrap0(capturerequest, parcel);
            return capturerequest;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public CaptureRequest[] newArray(int i)
        {
            return new CaptureRequest[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final Key EDGE_MODE;
    public static final Key FLASH_MODE;
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
    public static final Key LENS_OPTICAL_STABILIZATION_MODE;
    public static final Key NOISE_REDUCTION_MODE;
    public static final Key REPROCESS_EFFECTIVE_EXPOSURE_FACTOR;
    public static final Key REQUEST_ID;
    public static final Key SCALER_CROP_REGION = new Key("android.scaler.cropRegion", android/graphics/Rect);
    public static final Key SENSOR_EXPOSURE_TIME;
    public static final Key SENSOR_FRAME_DURATION;
    public static final Key SENSOR_SENSITIVITY;
    public static final Key SENSOR_TEST_PATTERN_DATA = new Key("android.sensor.testPatternData", [I);
    public static final Key SENSOR_TEST_PATTERN_MODE;
    public static final Key SHADING_MODE;
    public static final Key STATISTICS_FACE_DETECT_MODE;
    public static final Key STATISTICS_HOT_PIXEL_MAP_MODE;
    public static final Key STATISTICS_LENS_SHADING_MAP_MODE;
    public static final Key TONEMAP_CURVE = new Key("android.tonemap.curve", android/hardware/camera2/params/TonemapCurve);
    public static final Key TONEMAP_CURVE_BLUE = new Key("android.tonemap.curveBlue", [F);
    public static final Key TONEMAP_CURVE_GREEN = new Key("android.tonemap.curveGreen", [F);
    public static final Key TONEMAP_CURVE_RED = new Key("android.tonemap.curveRed", [F);
    public static final Key TONEMAP_GAMMA;
    public static final Key TONEMAP_MODE;
    public static final Key TONEMAP_PRESET_CURVE;
    private boolean mIsPartOfCHSRequestList;
    private boolean mIsReprocess;
    private int mReprocessableSessionId;
    private final CameraMetadataNative mSettings;
    private final HashSet mSurfaceSet;
    private Object mUserTag;

    static 
    {
        COLOR_CORRECTION_MODE = new Key("android.colorCorrection.mode", Integer.TYPE);
        COLOR_CORRECTION_ABERRATION_MODE = new Key("android.colorCorrection.aberrationMode", Integer.TYPE);
        CONTROL_AE_ANTIBANDING_MODE = new Key("android.control.aeAntibandingMode", Integer.TYPE);
        CONTROL_AE_EXPOSURE_COMPENSATION = new Key("android.control.aeExposureCompensation", Integer.TYPE);
        CONTROL_AE_LOCK = new Key("android.control.aeLock", Boolean.TYPE);
        CONTROL_AE_MODE = new Key("android.control.aeMode", Integer.TYPE);
        CONTROL_AE_PRECAPTURE_TRIGGER = new Key("android.control.aePrecaptureTrigger", Integer.TYPE);
        CONTROL_AF_MODE = new Key("android.control.afMode", Integer.TYPE);
        CONTROL_AF_TRIGGER = new Key("android.control.afTrigger", Integer.TYPE);
        CONTROL_AWB_LOCK = new Key("android.control.awbLock", Boolean.TYPE);
        CONTROL_AWB_MODE = new Key("android.control.awbMode", Integer.TYPE);
        CONTROL_CAPTURE_INTENT = new Key("android.control.captureIntent", Integer.TYPE);
        CONTROL_EFFECT_MODE = new Key("android.control.effectMode", Integer.TYPE);
        CONTROL_MODE = new Key("android.control.mode", Integer.TYPE);
        CONTROL_SCENE_MODE = new Key("android.control.sceneMode", Integer.TYPE);
        CONTROL_VIDEO_STABILIZATION_MODE = new Key("android.control.videoStabilizationMode", Integer.TYPE);
        CONTROL_POST_RAW_SENSITIVITY_BOOST = new Key("android.control.postRawSensitivityBoost", Integer.TYPE);
        CONTROL_ENABLE_ZSL = new Key("android.control.enableZsl", Boolean.TYPE);
        EDGE_MODE = new Key("android.edge.mode", Integer.TYPE);
        FLASH_MODE = new Key("android.flash.mode", Integer.TYPE);
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
        NOISE_REDUCTION_MODE = new Key("android.noiseReduction.mode", Integer.TYPE);
        REQUEST_ID = new Key("android.request.id", Integer.TYPE);
        SENSOR_EXPOSURE_TIME = new Key("android.sensor.exposureTime", Long.TYPE);
        SENSOR_FRAME_DURATION = new Key("android.sensor.frameDuration", Long.TYPE);
        SENSOR_SENSITIVITY = new Key("android.sensor.sensitivity", Integer.TYPE);
        SENSOR_TEST_PATTERN_MODE = new Key("android.sensor.testPatternMode", Integer.TYPE);
        SHADING_MODE = new Key("android.shading.mode", Integer.TYPE);
        STATISTICS_FACE_DETECT_MODE = new Key("android.statistics.faceDetectMode", Integer.TYPE);
        STATISTICS_HOT_PIXEL_MAP_MODE = new Key("android.statistics.hotPixelMapMode", Boolean.TYPE);
        STATISTICS_LENS_SHADING_MAP_MODE = new Key("android.statistics.lensShadingMapMode", Integer.TYPE);
        TONEMAP_MODE = new Key("android.tonemap.mode", Integer.TYPE);
        TONEMAP_GAMMA = new Key("android.tonemap.gamma", Float.TYPE);
        TONEMAP_PRESET_CURVE = new Key("android.tonemap.presetCurve", Integer.TYPE);
        LED_TRANSMIT = new Key("android.led.transmit", Boolean.TYPE);
        BLACK_LEVEL_LOCK = new Key("android.blackLevel.lock", Boolean.TYPE);
        REPROCESS_EFFECTIVE_EXPOSURE_FACTOR = new Key("android.reprocess.effectiveExposureFactor", Float.TYPE);
    }
}
