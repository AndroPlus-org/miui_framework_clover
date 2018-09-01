// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.input;

import android.os.Parcel;
import android.os.Parcelable;

public class TouchCalibration
    implements Parcelable
{

    public TouchCalibration()
    {
        this(1.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F);
    }

    public TouchCalibration(float f, float f1, float f2, float f3, float f4, float f5)
    {
        mXScale = f;
        mXYMix = f1;
        mXOffset = f2;
        mYXMix = f3;
        mYScale = f4;
        mYOffset = f5;
    }

    public TouchCalibration(Parcel parcel)
    {
        mXScale = parcel.readFloat();
        mXYMix = parcel.readFloat();
        mXOffset = parcel.readFloat();
        mYXMix = parcel.readFloat();
        mYScale = parcel.readFloat();
        mYOffset = parcel.readFloat();
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(obj == this)
            return true;
        if(obj instanceof TouchCalibration)
        {
            obj = (TouchCalibration)obj;
            if(((TouchCalibration) (obj)).mXScale == mXScale && ((TouchCalibration) (obj)).mXYMix == mXYMix && ((TouchCalibration) (obj)).mXOffset == mXOffset && ((TouchCalibration) (obj)).mYXMix == mYXMix && ((TouchCalibration) (obj)).mYScale == mYScale)
            {
                if(((TouchCalibration) (obj)).mYOffset != mYOffset)
                    flag = false;
            } else
            {
                flag = false;
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public float[] getAffineTransform()
    {
        return (new float[] {
            mXScale, mXYMix, mXOffset, mYXMix, mYScale, mYOffset
        });
    }

    public int hashCode()
    {
        return Float.floatToIntBits(mXScale) ^ Float.floatToIntBits(mXYMix) ^ Float.floatToIntBits(mXOffset) ^ Float.floatToIntBits(mYXMix) ^ Float.floatToIntBits(mYScale) ^ Float.floatToIntBits(mYOffset);
    }

    public String toString()
    {
        return String.format("[%f, %f, %f, %f, %f, %f]", new Object[] {
            Float.valueOf(mXScale), Float.valueOf(mXYMix), Float.valueOf(mXOffset), Float.valueOf(mYXMix), Float.valueOf(mYScale), Float.valueOf(mYOffset)
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeFloat(mXScale);
        parcel.writeFloat(mXYMix);
        parcel.writeFloat(mXOffset);
        parcel.writeFloat(mYXMix);
        parcel.writeFloat(mYScale);
        parcel.writeFloat(mYOffset);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public TouchCalibration createFromParcel(Parcel parcel)
        {
            return new TouchCalibration(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public TouchCalibration[] newArray(int i)
        {
            return new TouchCalibration[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final TouchCalibration IDENTITY = new TouchCalibration();
    private final float mXOffset;
    private final float mXScale;
    private final float mXYMix;
    private final float mYOffset;
    private final float mYScale;
    private final float mYXMix;

}
