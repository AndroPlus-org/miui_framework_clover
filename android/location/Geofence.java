// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.location;

import android.os.Parcel;
import android.os.Parcelable;

public final class Geofence
    implements Parcelable
{

    static void _2D_wrap0(int i)
    {
        checkType(i);
    }

    private Geofence(double d, double d1, float f)
    {
        checkRadius(f);
        checkLatLong(d, d1);
        mLatitude = d;
        mLongitude = d1;
        mRadius = f;
    }

    private static void checkLatLong(double d, double d1)
    {
        if(d > 90D || d < -90D)
            throw new IllegalArgumentException((new StringBuilder()).append("invalid latitude: ").append(d).toString());
        if(d1 > 180D || d1 < -180D)
            throw new IllegalArgumentException((new StringBuilder()).append("invalid longitude: ").append(d1).toString());
        else
            return;
    }

    private static void checkRadius(float f)
    {
        if(f <= 0.0F)
            throw new IllegalArgumentException((new StringBuilder()).append("invalid radius: ").append(f).toString());
        else
            return;
    }

    private static void checkType(int i)
    {
        if(i != 1)
            throw new IllegalArgumentException((new StringBuilder()).append("invalid type: ").append(i).toString());
        else
            return;
    }

    public static Geofence createCircle(double d, double d1, float f)
    {
        return new Geofence(d, d1, f);
    }

    private static String typeToString(int i)
    {
        switch(i)
        {
        default:
            checkType(i);
            return null;

        case 1: // '\001'
            return "CIRCLE";
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(!(obj instanceof Geofence))
            return false;
        obj = (Geofence)obj;
        if(mRadius != ((Geofence) (obj)).mRadius)
            return false;
        if(mLatitude != ((Geofence) (obj)).mLatitude)
            return false;
        if(mLongitude != ((Geofence) (obj)).mLongitude)
            return false;
        return mType == ((Geofence) (obj)).mType;
    }

    public double getLatitude()
    {
        return mLatitude;
    }

    public double getLongitude()
    {
        return mLongitude;
    }

    public float getRadius()
    {
        return mRadius;
    }

    public int getType()
    {
        return mType;
    }

    public int hashCode()
    {
        long l = Double.doubleToLongBits(mLatitude);
        int i = (int)(l >>> 32 ^ l);
        l = Double.doubleToLongBits(mLongitude);
        return (((i + 31) * 31 + (int)(l >>> 32 ^ l)) * 31 + Float.floatToIntBits(mRadius)) * 31 + mType;
    }

    public String toString()
    {
        return String.format("Geofence[%s %.6f, %.6f %.0fm]", new Object[] {
            typeToString(mType), Double.valueOf(mLatitude), Double.valueOf(mLongitude), Float.valueOf(mRadius)
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mType);
        parcel.writeDouble(mLatitude);
        parcel.writeDouble(mLongitude);
        parcel.writeFloat(mRadius);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public Geofence createFromParcel(Parcel parcel)
        {
            int i = parcel.readInt();
            double d = parcel.readDouble();
            double d1 = parcel.readDouble();
            float f = parcel.readFloat();
            Geofence._2D_wrap0(i);
            return Geofence.createCircle(d, d1, f);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public Geofence[] newArray(int i)
        {
            return new Geofence[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int TYPE_HORIZONTAL_CIRCLE = 1;
    private final double mLatitude;
    private final double mLongitude;
    private final float mRadius;
    private final int mType = 1;

}
