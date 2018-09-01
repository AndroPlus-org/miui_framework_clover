// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware;


// Referenced classes of package android.hardware:
//            Sensor

public class SensorAdditionalInfo
{

    SensorAdditionalInfo(Sensor sensor1, int i, int j, int ai[], float af[])
    {
        sensor = sensor1;
        type = i;
        serial = j;
        intValues = ai;
        floatValues = af;
    }

    public static SensorAdditionalInfo createCustomInfo(Sensor sensor1, int i, float af[])
    {
        while(i < 0x10000000 || i >= 0x40000000 || sensor1 == null) 
            throw new IllegalArgumentException((new StringBuilder()).append("invalid parameter(s): type: ").append(i).append("; sensor: ").append(sensor1).toString());
        return new SensorAdditionalInfo(sensor1, i, 0, null, af);
    }

    public static SensorAdditionalInfo createLocalGeomagneticField(float f, float f1, float f2)
    {
        while(f < 10F || f > 100F || f1 < 0.0F || (double)f1 > 3.1415926535897931D || (double)f2 < -1.5707963267948966D || (double)f2 > 1.5707963267948966D) 
            throw new IllegalArgumentException("Geomagnetic field info out of range");
        return new SensorAdditionalInfo(null, 0x30000, 0, null, new float[] {
            f, f1, f2
        });
    }

    public static final int TYPE_CUSTOM_INFO = 0x10000000;
    public static final int TYPE_DEBUG_INFO = 0x40000000;
    public static final int TYPE_DOCK_STATE = 0x30002;
    public static final int TYPE_FRAME_BEGIN = 0;
    public static final int TYPE_FRAME_END = 1;
    public static final int TYPE_HIGH_PERFORMANCE_MODE = 0x30003;
    public static final int TYPE_INTERNAL_TEMPERATURE = 0x10001;
    public static final int TYPE_LOCAL_GEOMAGNETIC_FIELD = 0x30000;
    public static final int TYPE_LOCAL_GRAVITY = 0x30001;
    public static final int TYPE_MAGNETIC_FIELD_CALIBRATION = 0x30004;
    public static final int TYPE_SAMPLING = 0x10004;
    public static final int TYPE_SENSOR_PLACEMENT = 0x10003;
    public static final int TYPE_UNTRACKED_DELAY = 0x10000;
    public static final int TYPE_VEC3_CALIBRATION = 0x10002;
    public final float floatValues[];
    public final int intValues[];
    public final Sensor sensor;
    public final int serial;
    public final int type;
}
