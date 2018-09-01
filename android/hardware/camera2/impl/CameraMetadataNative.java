// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.impl;

import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.camera2.*;
import android.hardware.camera2.marshal.*;
import android.hardware.camera2.marshal.impl.*;
import android.hardware.camera2.params.*;
import android.hardware.camera2.utils.TypeReference;
import android.location.Location;
import android.os.*;
import android.util.Log;
import android.util.Size;
import com.android.internal.util.Preconditions;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;

// Referenced classes of package android.hardware.camera2.impl:
//            GetCommand, SetCommand

public class CameraMetadataNative
    implements Parcelable
{
    public static class Key
    {

        public final boolean equals(Object obj)
        {
            boolean flag = false;
            if(this == obj)
                return true;
            if(obj == null || hashCode() != obj.hashCode())
                return false;
            if(obj instanceof android.hardware.camera2.CaptureResult.Key)
                obj = ((android.hardware.camera2.CaptureResult.Key)obj).getNativeKey();
            else
            if(obj instanceof android.hardware.camera2.CaptureRequest.Key)
                obj = ((android.hardware.camera2.CaptureRequest.Key)obj).getNativeKey();
            else
            if(obj instanceof android.hardware.camera2.CameraCharacteristics.Key)
                obj = ((android.hardware.camera2.CameraCharacteristics.Key)obj).getNativeKey();
            else
            if(obj instanceof Key)
                obj = (Key)obj;
            else
                return false;
            if(mName.equals(((Key) (obj)).mName))
                flag = mTypeReference.equals(((Key) (obj)).mTypeReference);
            return flag;
        }

        public final String getName()
        {
            return mName;
        }

        public final int getTag()
        {
            if(!mHasTag)
            {
                mTag = CameraMetadataNative.getTag(mName, mVendorId);
                mHasTag = true;
            }
            return mTag;
        }

        public final Class getType()
        {
            return mType;
        }

        public final TypeReference getTypeReference()
        {
            return mTypeReference;
        }

        public final long getVendorId()
        {
            return mVendorId;
        }

        public final int hashCode()
        {
            return mHash;
        }

        private boolean mHasTag;
        private final int mHash;
        private final String mName;
        private int mTag;
        private final Class mType;
        private final TypeReference mTypeReference;
        private long mVendorId;

        public Key(String s, TypeReference typereference)
        {
            mVendorId = 0x7fffffffffffffffL;
            if(s == null)
                throw new NullPointerException("Key needs a valid name");
            if(typereference == null)
            {
                throw new NullPointerException("TypeReference needs to be non-null");
            } else
            {
                mName = s;
                mType = typereference.getRawType();
                mTypeReference = typereference;
                mHash = mName.hashCode() ^ mTypeReference.hashCode();
                return;
            }
        }

        public Key(String s, Class class1)
        {
            mVendorId = 0x7fffffffffffffffL;
            if(s == null)
                throw new NullPointerException("Key needs a valid name");
            if(class1 == null)
            {
                throw new NullPointerException("Type needs to be non-null");
            } else
            {
                mName = s;
                mType = class1;
                mTypeReference = TypeReference.createSpecializedTypeReference(class1);
                mHash = mName.hashCode() ^ mTypeReference.hashCode();
                return;
            }
        }

        public Key(String s, Class class1, long l)
        {
            mVendorId = 0x7fffffffffffffffL;
            if(s == null)
                throw new NullPointerException("Key needs a valid name");
            if(class1 == null)
            {
                throw new NullPointerException("Type needs to be non-null");
            } else
            {
                mName = s;
                mType = class1;
                mVendorId = l;
                mTypeReference = TypeReference.createSpecializedTypeReference(class1);
                mHash = mName.hashCode() ^ mTypeReference.hashCode();
                return;
            }
        }
    }


    static Rect[] _2D_wrap0(CameraMetadataNative camerametadatanative)
    {
        return camerametadatanative.getFaceRectangles();
    }

    static Face[] _2D_wrap1(CameraMetadataNative camerametadatanative)
    {
        return camerametadatanative.getFaces();
    }

    static boolean _2D_wrap10(CameraMetadataNative camerametadatanative, TonemapCurve tonemapcurve)
    {
        return camerametadatanative.setTonemapCurve(tonemapcurve);
    }

    static int[] _2D_wrap11(CameraMetadataNative camerametadatanative)
    {
        return camerametadatanative.getAvailableFormats();
    }

    static Integer _2D_wrap12(CameraMetadataNative camerametadatanative, Key key)
    {
        return camerametadatanative.getMaxNumOutputs(key);
    }

    static Integer _2D_wrap13(CameraMetadataNative camerametadatanative, Key key)
    {
        return camerametadatanative.getMaxRegions(key);
    }

    static LensShadingMap _2D_wrap2(CameraMetadataNative camerametadatanative)
    {
        return camerametadatanative.getLensShadingMap();
    }

    static StreamConfigurationMap _2D_wrap3(CameraMetadataNative camerametadatanative)
    {
        return camerametadatanative.getStreamConfigurationMap();
    }

    static TonemapCurve _2D_wrap4(CameraMetadataNative camerametadatanative)
    {
        return camerametadatanative.getTonemapCurve();
    }

    static Location _2D_wrap5(CameraMetadataNative camerametadatanative)
    {
        return camerametadatanative.getGpsLocation();
    }

    static boolean _2D_wrap6(CameraMetadataNative camerametadatanative, int ai[])
    {
        return camerametadatanative.setAvailableFormats(ai);
    }

    static boolean _2D_wrap7(CameraMetadataNative camerametadatanative, Rect arect[])
    {
        return camerametadatanative.setFaceRectangles(arect);
    }

    static boolean _2D_wrap8(CameraMetadataNative camerametadatanative, Face aface[])
    {
        return camerametadatanative.setFaces(aface);
    }

    static boolean _2D_wrap9(CameraMetadataNative camerametadatanative, Location location)
    {
        return camerametadatanative.setGpsLocation(location);
    }

    public CameraMetadataNative()
    {
        mMetadataPtr = nativeAllocate();
        if(mMetadataPtr == 0L)
            throw new OutOfMemoryError("Failed to allocate native CameraMetadata");
        else
            return;
    }

    public CameraMetadataNative(CameraMetadataNative camerametadatanative)
    {
        mMetadataPtr = nativeAllocateCopy(camerametadatanative);
        if(mMetadataPtr == 0L)
            throw new OutOfMemoryError("Failed to allocate native CameraMetadata");
        else
            return;
    }

    private static transient boolean areValuesAllNull(Object aobj[])
    {
        int i = aobj.length;
        for(int j = 0; j < i; j++)
            if(aobj[j] != null)
                return false;

        return true;
    }

    private void close()
    {
        nativeClose();
        mMetadataPtr = 0L;
    }

    private int[] getAvailableFormats()
    {
        int ai[] = (int[])getBase(CameraCharacteristics.SCALER_AVAILABLE_FORMATS);
        if(ai != null)
        {
            for(int i = 0; i < ai.length; i++)
                if(ai[i] == 33)
                    ai[i] = 256;

        }
        return ai;
    }

    private Object getBase(android.hardware.camera2.CameraCharacteristics.Key key)
    {
        return getBase(key.getNativeKey());
    }

    private Object getBase(android.hardware.camera2.CaptureRequest.Key key)
    {
        return getBase(key.getNativeKey());
    }

    private Object getBase(android.hardware.camera2.CaptureResult.Key key)
    {
        return getBase(key.getNativeKey());
    }

    private Object getBase(Key key)
    {
        int i = nativeGetTagFromKeyLocal(key.getName());
        byte abyte0[] = readValues(i);
        if(abyte0 == null)
            return null;
        else
            return getMarshalerForKey(key, nativeGetTypeFromTagLocal(i)).unmarshal(ByteBuffer.wrap(abyte0).order(ByteOrder.nativeOrder()));
    }

    private Rect[] getFaceRectangles()
    {
        Rect arect[] = (Rect[])getBase(CaptureResult.STATISTICS_FACE_RECTANGLES);
        if(arect == null)
            return null;
        Rect arect1[] = new Rect[arect.length];
        for(int i = 0; i < arect.length; i++)
            arect1[i] = new Rect(arect[i].left, arect[i].top, arect[i].right - arect[i].left, arect[i].bottom - arect[i].top);

        return arect1;
    }

    private Face[] getFaces()
    {
        Object obj = (Integer)get(CaptureResult.STATISTICS_FACE_DETECT_MODE);
        byte abyte0[] = (byte[])get(CaptureResult.STATISTICS_FACE_SCORES);
        Rect arect[] = (Rect[])get(CaptureResult.STATISTICS_FACE_RECTANGLES);
        int ai[] = (int[])get(CaptureResult.STATISTICS_FACE_IDS);
        int ai1[] = (int[])get(CaptureResult.STATISTICS_FACE_LANDMARKS);
        if(areValuesAllNull(new Object[] {
    obj, abyte0, arect, ai, ai1
}))
            return null;
        Object obj1;
        if(obj == null)
        {
            Log.w("CameraMetadataJV", "Face detect mode metadata is null, assuming the mode is SIMPLE");
            obj1 = Integer.valueOf(1);
        } else
        {
            if(((Integer) (obj)).intValue() == 0)
                return new Face[0];
            obj1 = obj;
            if(((Integer) (obj)).intValue() != 1)
            {
                obj1 = obj;
                if(((Integer) (obj)).intValue() != 2)
                {
                    Log.w("CameraMetadataJV", (new StringBuilder()).append("Unknown face detect mode: ").append(obj).toString());
                    return new Face[0];
                }
            }
        }
        if(abyte0 == null || arect == null)
        {
            Log.w("CameraMetadataJV", "Expect face scores and rectangles to be non-null");
            return new Face[0];
        }
        if(abyte0.length != arect.length)
            Log.w("CameraMetadataJV", String.format("Face score size(%d) doesn match face rectangle size(%d)!", new Object[] {
                Integer.valueOf(abyte0.length), Integer.valueOf(arect.length)
            }));
        int i = Math.min(abyte0.length, arect.length);
        obj = obj1;
        int k = i;
        if(((Integer) (obj1)).intValue() == 2)
            if(ai == null || ai1 == null)
            {
                Log.w("CameraMetadataJV", "Expect face ids and landmarks to be non-null for FULL mode,fallback to SIMPLE mode");
                obj = Integer.valueOf(1);
                k = i;
            } else
            {
                if(ai.length != i || ai1.length != i * 6)
                    Log.w("CameraMetadataJV", String.format("Face id size(%d), or face landmark size(%d) don'tmatch face number(%d)!", new Object[] {
                        Integer.valueOf(ai.length), Integer.valueOf(ai1.length * 6), Integer.valueOf(i)
                    }));
                k = Math.min(Math.min(i, ai.length), ai1.length / 6);
                obj = obj1;
            }
        obj1 = new ArrayList();
        if(((Integer) (obj)).intValue() == 1)
        {
            for(i = 0; i < k; i++)
                if(abyte0[i] <= 100 && abyte0[i] >= 1)
                    ((ArrayList) (obj1)).add(new Face(arect[i], abyte0[i]));

        } else
        {
            for(int j = 0; j < k; j++)
                if(abyte0[j] <= 100 && abyte0[j] >= 1 && ai[j] >= 0)
                {
                    Point point = new Point(ai1[j * 6], ai1[j * 6 + 1]);
                    Point point1 = new Point(ai1[j * 6 + 2], ai1[j * 6 + 3]);
                    Point point2 = new Point(ai1[j * 6 + 4], ai1[j * 6 + 5]);
                    ((ArrayList) (obj1)).add(new Face(arect[j], abyte0[j], ai[j], point, point1, point2));
                }

        }
        Face aface[] = new Face[((ArrayList) (obj1)).size()];
        ((ArrayList) (obj1)).toArray(aface);
        return aface;
    }

    private Location getGpsLocation()
    {
        Object obj = (String)get(CaptureResult.JPEG_GPS_PROCESSING_METHOD);
        double ad[] = (double[])get(CaptureResult.JPEG_GPS_COORDINATES);
        Long long1 = (Long)get(CaptureResult.JPEG_GPS_TIMESTAMP);
        if(areValuesAllNull(new Object[] {
    obj, ad, long1
}))
            return null;
        obj = new Location(translateProcessToLocationProvider(((String) (obj))));
        if(long1 != null)
            ((Location) (obj)).setTime(long1.longValue() * 1000L);
        else
            Log.w("CameraMetadataJV", "getGpsLocation - No timestamp for GPS location.");
        if(ad != null)
        {
            ((Location) (obj)).setLatitude(ad[0]);
            ((Location) (obj)).setLongitude(ad[1]);
            ((Location) (obj)).setAltitude(ad[2]);
        } else
        {
            Log.w("CameraMetadataJV", "getGpsLocation - No coordinates for GPS location");
        }
        return ((Location) (obj));
    }

    private LensShadingMap getLensShadingMap()
    {
        float af[] = (float[])getBase(CaptureResult.STATISTICS_LENS_SHADING_MAP);
        Size size = (Size)get(CameraCharacteristics.LENS_INFO_SHADING_MAP_SIZE);
        if(af == null)
            return null;
        if(size == null)
        {
            Log.w("CameraMetadataJV", "getLensShadingMap - Lens shading map size was null.");
            return null;
        } else
        {
            return new LensShadingMap(af, size.getHeight(), size.getWidth());
        }
    }

    private static Marshaler getMarshalerForKey(Key key, int i)
    {
        return MarshalRegistry.getMarshaler(key.getTypeReference(), i);
    }

    private Integer getMaxNumOutputs(Key key)
    {
        int ai[] = (int[])getBase(CameraCharacteristics.REQUEST_MAX_NUM_OUTPUT_STREAMS);
        if(ai == null)
            return null;
        if(key.equals(CameraCharacteristics.REQUEST_MAX_NUM_OUTPUT_RAW))
            return Integer.valueOf(ai[0]);
        if(key.equals(CameraCharacteristics.REQUEST_MAX_NUM_OUTPUT_PROC))
            return Integer.valueOf(ai[1]);
        if(key.equals(CameraCharacteristics.REQUEST_MAX_NUM_OUTPUT_PROC_STALLING))
            return Integer.valueOf(ai[2]);
        else
            throw new AssertionError((new StringBuilder()).append("Invalid key ").append(key).toString());
    }

    private Integer getMaxRegions(Key key)
    {
        int ai[] = (int[])getBase(CameraCharacteristics.CONTROL_MAX_REGIONS);
        if(ai == null)
            return null;
        if(key.equals(CameraCharacteristics.CONTROL_MAX_REGIONS_AE))
            return Integer.valueOf(ai[0]);
        if(key.equals(CameraCharacteristics.CONTROL_MAX_REGIONS_AWB))
            return Integer.valueOf(ai[1]);
        if(key.equals(CameraCharacteristics.CONTROL_MAX_REGIONS_AF))
            return Integer.valueOf(ai[2]);
        else
            throw new AssertionError((new StringBuilder()).append("Invalid key ").append(key).toString());
    }

    public static int getNativeType(int i, long l)
    {
        return nativeGetTypeFromTag(i, l);
    }

    private StreamConfigurationMap getStreamConfigurationMap()
    {
        android.hardware.camera2.params.StreamConfiguration astreamconfiguration[] = (android.hardware.camera2.params.StreamConfiguration[])getBase(CameraCharacteristics.SCALER_AVAILABLE_STREAM_CONFIGURATIONS);
        android.hardware.camera2.params.StreamConfigurationDuration astreamconfigurationduration[] = (android.hardware.camera2.params.StreamConfigurationDuration[])getBase(CameraCharacteristics.SCALER_AVAILABLE_MIN_FRAME_DURATIONS);
        android.hardware.camera2.params.StreamConfigurationDuration astreamconfigurationduration1[] = (android.hardware.camera2.params.StreamConfigurationDuration[])getBase(CameraCharacteristics.SCALER_AVAILABLE_STALL_DURATIONS);
        android.hardware.camera2.params.StreamConfiguration astreamconfiguration1[] = (android.hardware.camera2.params.StreamConfiguration[])getBase(CameraCharacteristics.DEPTH_AVAILABLE_DEPTH_STREAM_CONFIGURATIONS);
        android.hardware.camera2.params.StreamConfigurationDuration astreamconfigurationduration2[] = (android.hardware.camera2.params.StreamConfigurationDuration[])getBase(CameraCharacteristics.DEPTH_AVAILABLE_DEPTH_MIN_FRAME_DURATIONS);
        android.hardware.camera2.params.StreamConfigurationDuration astreamconfigurationduration3[] = (android.hardware.camera2.params.StreamConfigurationDuration[])getBase(CameraCharacteristics.DEPTH_AVAILABLE_DEPTH_STALL_DURATIONS);
        android.hardware.camera2.params.HighSpeedVideoConfiguration ahighspeedvideoconfiguration[] = (android.hardware.camera2.params.HighSpeedVideoConfiguration[])getBase(CameraCharacteristics.CONTROL_AVAILABLE_HIGH_SPEED_VIDEO_CONFIGURATIONS);
        ReprocessFormatsMap reprocessformatsmap = (ReprocessFormatsMap)getBase(CameraCharacteristics.SCALER_AVAILABLE_INPUT_OUTPUT_FORMATS_MAP);
        int ai[] = (int[])getBase(CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES);
        boolean flag = false;
        int i = 0;
        int j = ai.length;
        do
        {
label0:
            {
                boolean flag1 = flag;
                if(i < j)
                {
                    if(ai[i] != 6)
                        break label0;
                    flag1 = true;
                }
                return new StreamConfigurationMap(astreamconfiguration, astreamconfigurationduration, astreamconfigurationduration1, astreamconfiguration1, astreamconfigurationduration2, astreamconfigurationduration3, ahighspeedvideoconfiguration, reprocessformatsmap, flag1);
            }
            i++;
        } while(true);
    }

    public static int getTag(String s)
    {
        return nativeGetTagFromKey(s, 0x7fffffffffffffffL);
    }

    public static int getTag(String s, long l)
    {
        return nativeGetTagFromKey(s, l);
    }

    private TonemapCurve getTonemapCurve()
    {
        float af[] = (float[])getBase(CaptureRequest.TONEMAP_CURVE_RED);
        float af1[] = (float[])getBase(CaptureRequest.TONEMAP_CURVE_GREEN);
        float af2[] = (float[])getBase(CaptureRequest.TONEMAP_CURVE_BLUE);
        if(areValuesAllNull(new Object[] {
    af, af1, af2
}))
            return null;
        while(af == null || af1 == null || af2 == null) 
        {
            Log.w("CameraMetadataJV", "getTonemapCurve - missing tone curve components");
            return null;
        }
        return new TonemapCurve(af, af1, af2);
    }

    public static CameraMetadataNative move(CameraMetadataNative camerametadatanative)
    {
        CameraMetadataNative camerametadatanative1 = new CameraMetadataNative();
        camerametadatanative1.swap(camerametadatanative);
        return camerametadatanative1;
    }

    private native long nativeAllocate();

    private native long nativeAllocateCopy(CameraMetadataNative camerametadatanative)
        throws NullPointerException;

    private synchronized native void nativeClose();

    private synchronized native void nativeDump()
        throws IOException;

    private synchronized native ArrayList nativeGetAllVendorKeys(Class class1);

    private synchronized native int nativeGetEntryCount();

    private static native int nativeGetTagFromKey(String s, long l)
        throws IllegalArgumentException;

    private synchronized native int nativeGetTagFromKeyLocal(String s)
        throws IllegalArgumentException;

    private static native int nativeGetTypeFromTag(int i, long l)
        throws IllegalArgumentException;

    private synchronized native int nativeGetTypeFromTagLocal(int i)
        throws IllegalArgumentException;

    private synchronized native boolean nativeIsEmpty();

    private synchronized native void nativeReadFromParcel(Parcel parcel);

    private synchronized native byte[] nativeReadValues(int i);

    private static native int nativeSetupGlobalVendorTagDescriptor();

    private synchronized native void nativeSwap(CameraMetadataNative camerametadatanative)
        throws NullPointerException;

    private synchronized native void nativeWriteToParcel(Parcel parcel);

    private synchronized native void nativeWriteValues(int i, byte abyte0[]);

    private static void registerAllMarshalers()
    {
        int i = 0;
        MarshalQueryable amarshalqueryable[] = new MarshalQueryable[20];
        amarshalqueryable[0] = new MarshalQueryablePrimitive();
        amarshalqueryable[1] = new MarshalQueryableEnum();
        amarshalqueryable[2] = new MarshalQueryableArray();
        amarshalqueryable[3] = new MarshalQueryableBoolean();
        amarshalqueryable[4] = new MarshalQueryableNativeByteToInteger();
        amarshalqueryable[5] = new MarshalQueryableRect();
        amarshalqueryable[6] = new MarshalQueryableSize();
        amarshalqueryable[7] = new MarshalQueryableSizeF();
        amarshalqueryable[8] = new MarshalQueryableString();
        amarshalqueryable[9] = new MarshalQueryableReprocessFormatsMap();
        amarshalqueryable[10] = new MarshalQueryableRange();
        amarshalqueryable[11] = new MarshalQueryablePair();
        amarshalqueryable[12] = new MarshalQueryableMeteringRectangle();
        amarshalqueryable[13] = new MarshalQueryableColorSpaceTransform();
        amarshalqueryable[14] = new MarshalQueryableStreamConfiguration();
        amarshalqueryable[15] = new MarshalQueryableStreamConfigurationDuration();
        amarshalqueryable[16] = new MarshalQueryableRggbChannelVector();
        amarshalqueryable[17] = new MarshalQueryableBlackLevelPattern();
        amarshalqueryable[18] = new MarshalQueryableHighSpeedVideoConfiguration();
        amarshalqueryable[19] = new MarshalQueryableParcelable();
        for(int j = amarshalqueryable.length; i < j; i++)
            MarshalRegistry.registerMarshalQueryable(amarshalqueryable[i]);

    }

    private boolean setAvailableFormats(int ai[])
    {
        if(ai == null)
            return false;
        int ai1[] = new int[ai.length];
        for(int i = 0; i < ai.length; i++)
        {
            ai1[i] = ai[i];
            if(ai[i] == 256)
                ai1[i] = 33;
        }

        setBase(CameraCharacteristics.SCALER_AVAILABLE_FORMATS, ai1);
        return true;
    }

    private void setBase(android.hardware.camera2.CameraCharacteristics.Key key, Object obj)
    {
        setBase(key.getNativeKey(), obj);
    }

    private void setBase(android.hardware.camera2.CaptureRequest.Key key, Object obj)
    {
        setBase(key.getNativeKey(), obj);
    }

    private void setBase(android.hardware.camera2.CaptureResult.Key key, Object obj)
    {
        setBase(key.getNativeKey(), obj);
    }

    private void setBase(Key key, Object obj)
    {
        int i = nativeGetTagFromKeyLocal(key.getName());
        if(obj == null)
        {
            writeValues(i, null);
            return;
        } else
        {
            Marshaler marshaler = getMarshalerForKey(key, nativeGetTypeFromTagLocal(i));
            key = new byte[marshaler.calculateMarshalSize(obj)];
            marshaler.marshal(obj, ByteBuffer.wrap(key).order(ByteOrder.nativeOrder()));
            writeValues(i, key);
            return;
        }
    }

    private boolean setFaceRectangles(Rect arect[])
    {
        if(arect == null)
            return false;
        Rect arect1[] = new Rect[arect.length];
        for(int i = 0; i < arect1.length; i++)
            arect1[i] = new Rect(arect[i].left, arect[i].top, arect[i].right + arect[i].left, arect[i].bottom + arect[i].top);

        setBase(CaptureResult.STATISTICS_FACE_RECTANGLES, arect1);
        return true;
    }

    private boolean setFaces(Face aface[])
    {
        boolean flag = false;
        if(aface == null)
            return false;
        int i = aface.length;
        boolean flag1 = true;
        int j = aface.length;
        int k = 0;
        while(k < j) 
        {
            Face face = aface[k];
            int l;
            if(face == null)
            {
                l = i - 1;
                Log.w("CameraMetadataJV", "setFaces - null face detected, skipping");
            } else
            {
                l = i;
                if(face.getId() == -1)
                {
                    flag1 = false;
                    l = i;
                }
            }
            k++;
            i = l;
        }
        Rect arect[] = new Rect[i];
        byte abyte0[] = new byte[i];
        int ai[] = null;
        int ai1[] = null;
        if(flag1)
        {
            ai = new int[i];
            ai1 = new int[i * 6];
        }
        i = 0;
        int i1 = aface.length;
        k = ((flag) ? 1 : 0);
        while(k < i1) 
        {
            Face face1 = aface[k];
            if(face1 != null)
            {
                arect[i] = face1.getBounds();
                abyte0[i] = (byte)face1.getScore();
                if(flag1)
                {
                    ai[i] = face1.getId();
                    ai1[i * 6 + 0] = face1.getLeftEyePosition().x;
                    ai1[i * 6 + 1] = face1.getLeftEyePosition().y;
                    ai1[i * 6 + 2] = face1.getRightEyePosition().x;
                    ai1[i * 6 + 3] = face1.getRightEyePosition().y;
                    ai1[i * 6 + 4] = face1.getMouthPosition().x;
                    ai1[i * 6 + 5] = face1.getMouthPosition().y;
                }
                i++;
            }
            k++;
        }
        set(CaptureResult.STATISTICS_FACE_RECTANGLES, arect);
        set(CaptureResult.STATISTICS_FACE_IDS, ai);
        set(CaptureResult.STATISTICS_FACE_LANDMARKS, ai1);
        set(CaptureResult.STATISTICS_FACE_SCORES, abyte0);
        return true;
    }

    private boolean setGpsLocation(Location location)
    {
        if(location == null)
            return false;
        double d = location.getLatitude();
        double d1 = location.getLongitude();
        double d2 = location.getAltitude();
        String s = translateLocationProviderToProcess(location.getProvider());
        long l = location.getTime() / 1000L;
        set(CaptureRequest.JPEG_GPS_TIMESTAMP, Long.valueOf(l));
        set(CaptureRequest.JPEG_GPS_COORDINATES, new double[] {
            d, d1, d2
        });
        if(s == null)
            Log.w("CameraMetadataJV", "setGpsLocation - No process method, Location is not from a GPS or NETWORKprovider");
        else
            setBase(CaptureRequest.JPEG_GPS_PROCESSING_METHOD, s);
        return true;
    }

    private boolean setTonemapCurve(TonemapCurve tonemapcurve)
    {
        if(tonemapcurve == null)
            return false;
        float af[][] = new float[3][];
        for(int i = 0; i <= 2; i++)
        {
            af[i] = new float[tonemapcurve.getPointCount(i) * 2];
            tonemapcurve.copyColorCurve(i, af[i], 0);
        }

        setBase(CaptureRequest.TONEMAP_CURVE_RED, af[0]);
        setBase(CaptureRequest.TONEMAP_CURVE_GREEN, af[1]);
        setBase(CaptureRequest.TONEMAP_CURVE_BLUE, af[2]);
        return true;
    }

    public static void setupGlobalVendorTagDescriptor()
        throws ServiceSpecificException
    {
        int i = nativeSetupGlobalVendorTagDescriptor();
        if(i != 0)
            throw new ServiceSpecificException(i, "Failure to set up global vendor tags");
        else
            return;
    }

    private static String translateLocationProviderToProcess(String s)
    {
        if(s == null)
            return null;
        if(s.equals("gps"))
            return "GPS";
        if(s.equals("network"))
            return "CELLID";
        else
            return null;
    }

    private static String translateProcessToLocationProvider(String s)
    {
        if(s == null)
            return null;
        if(s.equals("GPS"))
            return "gps";
        if(s.equals("CELLID"))
            return "network";
        else
            return null;
    }

    public int describeContents()
    {
        return 0;
    }

    public void dumpToLog()
    {
        nativeDump();
_L1:
        return;
        IOException ioexception;
        ioexception;
        Log.wtf("CameraMetadataJV", "Dump logging failed", ioexception);
          goto _L1
    }

    protected void finalize()
        throws Throwable
    {
        close();
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public Object get(android.hardware.camera2.CameraCharacteristics.Key key)
    {
        return get(key.getNativeKey());
    }

    public Object get(android.hardware.camera2.CaptureRequest.Key key)
    {
        return get(key.getNativeKey());
    }

    public Object get(android.hardware.camera2.CaptureResult.Key key)
    {
        return get(key.getNativeKey());
    }

    public Object get(Key key)
    {
        Preconditions.checkNotNull(key, "key must not be null");
        GetCommand getcommand = (GetCommand)sGetCommandMap.get(key);
        if(getcommand != null)
            return getcommand.getValue(this, key);
        else
            return getBase(key);
    }

    public ArrayList getAllVendorKeys(Class class1)
    {
        if(class1 == null)
            throw new NullPointerException();
        else
            return nativeGetAllVendorKeys(class1);
    }

    public int getEntryCount()
    {
        return nativeGetEntryCount();
    }

    public boolean isEmpty()
    {
        return nativeIsEmpty();
    }

    public void readFromParcel(Parcel parcel)
    {
        nativeReadFromParcel(parcel);
    }

    public byte[] readValues(int i)
    {
        return nativeReadValues(i);
    }

    public void set(android.hardware.camera2.CameraCharacteristics.Key key, Object obj)
    {
        set(key.getNativeKey(), obj);
    }

    public void set(android.hardware.camera2.CaptureRequest.Key key, Object obj)
    {
        set(key.getNativeKey(), obj);
    }

    public void set(android.hardware.camera2.CaptureResult.Key key, Object obj)
    {
        set(key.getNativeKey(), obj);
    }

    public void set(Key key, Object obj)
    {
        SetCommand setcommand = (SetCommand)sSetCommandMap.get(key);
        if(setcommand != null)
        {
            setcommand.setValue(this, obj);
            return;
        } else
        {
            setBase(key, obj);
            return;
        }
    }

    public void swap(CameraMetadataNative camerametadatanative)
    {
        nativeSwap(camerametadatanative);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        nativeWriteToParcel(parcel);
    }

    public void writeValues(int i, byte abyte0[])
    {
        nativeWriteValues(i, abyte0);
    }

    private static final String CELLID_PROCESS = "CELLID";
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public CameraMetadataNative createFromParcel(Parcel parcel)
        {
            CameraMetadataNative camerametadatanative = new CameraMetadataNative();
            camerametadatanative.readFromParcel(parcel);
            return camerametadatanative;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public CameraMetadataNative[] newArray(int i)
        {
            return new CameraMetadataNative[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final boolean DEBUG = false;
    private static final int FACE_LANDMARK_SIZE = 6;
    private static final String GPS_PROCESS = "GPS";
    public static final int NATIVE_JPEG_FORMAT = 33;
    public static final int NUM_TYPES = 6;
    private static final String TAG = "CameraMetadataJV";
    public static final int TYPE_BYTE = 0;
    public static final int TYPE_DOUBLE = 4;
    public static final int TYPE_FLOAT = 2;
    public static final int TYPE_INT32 = 1;
    public static final int TYPE_INT64 = 3;
    public static final int TYPE_RATIONAL = 5;
    private static final HashMap sGetCommandMap;
    private static final HashMap sSetCommandMap;
    private long mMetadataPtr;

    static 
    {
        sGetCommandMap = new HashMap();
        sGetCommandMap.put(CameraCharacteristics.SCALER_AVAILABLE_FORMATS.getNativeKey(), new GetCommand() {

            public Object getValue(CameraMetadataNative camerametadatanative, Key key)
            {
                return CameraMetadataNative._2D_wrap11(camerametadatanative);
            }

        }
);
        sGetCommandMap.put(CaptureResult.STATISTICS_FACES.getNativeKey(), new GetCommand() {

            public Object getValue(CameraMetadataNative camerametadatanative, Key key)
            {
                return CameraMetadataNative._2D_wrap1(camerametadatanative);
            }

        }
);
        sGetCommandMap.put(CaptureResult.STATISTICS_FACE_RECTANGLES.getNativeKey(), new GetCommand() {

            public Object getValue(CameraMetadataNative camerametadatanative, Key key)
            {
                return CameraMetadataNative._2D_wrap0(camerametadatanative);
            }

        }
);
        sGetCommandMap.put(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP.getNativeKey(), new GetCommand() {

            public Object getValue(CameraMetadataNative camerametadatanative, Key key)
            {
                return CameraMetadataNative._2D_wrap3(camerametadatanative);
            }

        }
);
        sGetCommandMap.put(CameraCharacteristics.CONTROL_MAX_REGIONS_AE.getNativeKey(), new GetCommand() {

            public Object getValue(CameraMetadataNative camerametadatanative, Key key)
            {
                return CameraMetadataNative._2D_wrap13(camerametadatanative, key);
            }

        }
);
        sGetCommandMap.put(CameraCharacteristics.CONTROL_MAX_REGIONS_AWB.getNativeKey(), new GetCommand() {

            public Object getValue(CameraMetadataNative camerametadatanative, Key key)
            {
                return CameraMetadataNative._2D_wrap13(camerametadatanative, key);
            }

        }
);
        sGetCommandMap.put(CameraCharacteristics.CONTROL_MAX_REGIONS_AF.getNativeKey(), new GetCommand() {

            public Object getValue(CameraMetadataNative camerametadatanative, Key key)
            {
                return CameraMetadataNative._2D_wrap13(camerametadatanative, key);
            }

        }
);
        sGetCommandMap.put(CameraCharacteristics.REQUEST_MAX_NUM_OUTPUT_RAW.getNativeKey(), new GetCommand() {

            public Object getValue(CameraMetadataNative camerametadatanative, Key key)
            {
                return CameraMetadataNative._2D_wrap12(camerametadatanative, key);
            }

        }
);
        sGetCommandMap.put(CameraCharacteristics.REQUEST_MAX_NUM_OUTPUT_PROC.getNativeKey(), new GetCommand() {

            public Object getValue(CameraMetadataNative camerametadatanative, Key key)
            {
                return CameraMetadataNative._2D_wrap12(camerametadatanative, key);
            }

        }
);
        sGetCommandMap.put(CameraCharacteristics.REQUEST_MAX_NUM_OUTPUT_PROC_STALLING.getNativeKey(), new GetCommand() {

            public Object getValue(CameraMetadataNative camerametadatanative, Key key)
            {
                return CameraMetadataNative._2D_wrap12(camerametadatanative, key);
            }

        }
);
        sGetCommandMap.put(CaptureRequest.TONEMAP_CURVE.getNativeKey(), new GetCommand() {

            public Object getValue(CameraMetadataNative camerametadatanative, Key key)
            {
                return CameraMetadataNative._2D_wrap4(camerametadatanative);
            }

        }
);
        sGetCommandMap.put(CaptureResult.JPEG_GPS_LOCATION.getNativeKey(), new GetCommand() {

            public Object getValue(CameraMetadataNative camerametadatanative, Key key)
            {
                return CameraMetadataNative._2D_wrap5(camerametadatanative);
            }

        }
);
        sGetCommandMap.put(CaptureResult.STATISTICS_LENS_SHADING_CORRECTION_MAP.getNativeKey(), new GetCommand() {

            public Object getValue(CameraMetadataNative camerametadatanative, Key key)
            {
                return CameraMetadataNative._2D_wrap2(camerametadatanative);
            }

        }
);
        sSetCommandMap = new HashMap();
        sSetCommandMap.put(CameraCharacteristics.SCALER_AVAILABLE_FORMATS.getNativeKey(), new SetCommand() {

            public void setValue(CameraMetadataNative camerametadatanative, Object obj)
            {
                CameraMetadataNative._2D_wrap6(camerametadatanative, (int[])obj);
            }

        }
);
        sSetCommandMap.put(CaptureResult.STATISTICS_FACE_RECTANGLES.getNativeKey(), new SetCommand() {

            public void setValue(CameraMetadataNative camerametadatanative, Object obj)
            {
                CameraMetadataNative._2D_wrap7(camerametadatanative, (Rect[])obj);
            }

        }
);
        sSetCommandMap.put(CaptureResult.STATISTICS_FACES.getNativeKey(), new SetCommand() {

            public void setValue(CameraMetadataNative camerametadatanative, Object obj)
            {
                CameraMetadataNative._2D_wrap8(camerametadatanative, (Face[])obj);
            }

        }
);
        sSetCommandMap.put(CaptureRequest.TONEMAP_CURVE.getNativeKey(), new SetCommand() {

            public void setValue(CameraMetadataNative camerametadatanative, Object obj)
            {
                CameraMetadataNative._2D_wrap10(camerametadatanative, (TonemapCurve)obj);
            }

        }
);
        sSetCommandMap.put(CaptureResult.JPEG_GPS_LOCATION.getNativeKey(), new SetCommand() {

            public void setValue(CameraMetadataNative camerametadatanative, Object obj)
            {
                CameraMetadataNative._2D_wrap9(camerametadatanative, (Location)obj);
            }

        }
);
        registerAllMarshalers();
    }
}
