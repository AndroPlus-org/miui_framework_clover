// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.os.Parcel;
import android.os.Parcelable;

public class MagnificationSpec
    implements Parcelable
{

    static void _2D_wrap0(MagnificationSpec magnificationspec, Parcel parcel)
    {
        magnificationspec.initFromParcel(parcel);
    }

    private MagnificationSpec()
    {
        scale = 1.0F;
    }

    private void initFromParcel(Parcel parcel)
    {
        scale = parcel.readFloat();
        offsetX = parcel.readFloat();
        offsetY = parcel.readFloat();
    }

    public static MagnificationSpec obtain()
    {
        MagnificationSpec magnificationspec = (MagnificationSpec)sPool.acquire();
        if(magnificationspec == null)
            magnificationspec = new MagnificationSpec();
        return magnificationspec;
    }

    public static MagnificationSpec obtain(MagnificationSpec magnificationspec)
    {
        MagnificationSpec magnificationspec1 = obtain();
        magnificationspec1.scale = magnificationspec.scale;
        magnificationspec1.offsetX = magnificationspec.offsetX;
        magnificationspec1.offsetY = magnificationspec.offsetY;
        return magnificationspec1;
    }

    public void clear()
    {
        scale = 1.0F;
        offsetX = 0.0F;
        offsetY = 0.0F;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        obj = (MagnificationSpec)obj;
        if(scale != ((MagnificationSpec) (obj)).scale || offsetX != ((MagnificationSpec) (obj)).offsetX || offsetY != ((MagnificationSpec) (obj)).offsetY)
            flag = false;
        return flag;
    }

    public int hashCode()
    {
        int i = 0;
        int j;
        int k;
        if(scale != 0.0F)
            j = Float.floatToIntBits(scale);
        else
            j = 0;
        if(offsetX != 0.0F)
            k = Float.floatToIntBits(offsetX);
        else
            k = 0;
        if(offsetY != 0.0F)
            i = Float.floatToIntBits(offsetY);
        return (j * 31 + k) * 31 + i;
    }

    public void initialize(float f, float f1, float f2)
    {
        if(f < 1.0F)
        {
            throw new IllegalArgumentException("Scale must be greater than or equal to one!");
        } else
        {
            scale = f;
            offsetX = f1;
            offsetY = f2;
            return;
        }
    }

    public boolean isNop()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(scale == 1.0F)
        {
            flag1 = flag;
            if(offsetX == 0.0F)
            {
                flag1 = flag;
                if(offsetY == 0.0F)
                    flag1 = true;
            }
        }
        return flag1;
    }

    public void recycle()
    {
        clear();
        sPool.release(this);
    }

    public void setTo(MagnificationSpec magnificationspec)
    {
        scale = magnificationspec.scale;
        offsetX = magnificationspec.offsetX;
        offsetY = magnificationspec.offsetY;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("<scale:");
        stringbuilder.append(Float.toString(scale));
        stringbuilder.append(",offsetX:");
        stringbuilder.append(Float.toString(offsetX));
        stringbuilder.append(",offsetY:");
        stringbuilder.append(Float.toString(offsetY));
        stringbuilder.append(">");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeFloat(scale);
        parcel.writeFloat(offsetX);
        parcel.writeFloat(offsetY);
        recycle();
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public MagnificationSpec createFromParcel(Parcel parcel)
        {
            MagnificationSpec magnificationspec = MagnificationSpec.obtain();
            MagnificationSpec._2D_wrap0(magnificationspec, parcel);
            return magnificationspec;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public MagnificationSpec[] newArray(int i)
        {
            return new MagnificationSpec[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final int MAX_POOL_SIZE = 20;
    private static final android.util.Pools.SynchronizedPool sPool = new android.util.Pools.SynchronizedPool(20);
    public float offsetX;
    public float offsetY;
    public float scale;

}
