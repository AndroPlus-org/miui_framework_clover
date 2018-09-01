// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package android.graphics:
//            Point

public class PointF
    implements Parcelable
{

    public PointF()
    {
    }

    public PointF(float f, float f1)
    {
        x = f;
        y = f1;
    }

    public PointF(Point point)
    {
        x = point.x;
        y = point.y;
    }

    public static float length(float f, float f1)
    {
        return (float)Math.hypot(f, f1);
    }

    public int describeContents()
    {
        return 0;
    }

    public final boolean equals(float f, float f1)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(x == f)
        {
            flag1 = flag;
            if(y == f1)
                flag1 = true;
        }
        return flag1;
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        obj = (PointF)obj;
        if(Float.compare(((PointF) (obj)).x, x) != 0)
            return false;
        return Float.compare(((PointF) (obj)).y, y) == 0;
    }

    public int hashCode()
    {
        int i;
        int j;
        if(x != 0.0F)
            i = Float.floatToIntBits(x);
        else
            i = 0;
        if(y != 0.0F)
            j = Float.floatToIntBits(y);
        else
            j = 0;
        return i * 31 + j;
    }

    public final float length()
    {
        return length(x, y);
    }

    public final void negate()
    {
        x = -x;
        y = -y;
    }

    public final void offset(float f, float f1)
    {
        x = x + f;
        y = y + f1;
    }

    public void readFromParcel(Parcel parcel)
    {
        x = parcel.readFloat();
        y = parcel.readFloat();
    }

    public final void set(float f, float f1)
    {
        x = f;
        y = f1;
    }

    public final void set(PointF pointf)
    {
        x = pointf.x;
        y = pointf.y;
    }

    public String toString()
    {
        return (new StringBuilder()).append("PointF(").append(x).append(", ").append(y).append(")").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeFloat(x);
        parcel.writeFloat(y);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public PointF createFromParcel(Parcel parcel)
        {
            PointF pointf = new PointF();
            pointf.readFromParcel(parcel);
            return pointf;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PointF[] newArray(int i)
        {
            return new PointF[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public float x;
    public float y;

}
