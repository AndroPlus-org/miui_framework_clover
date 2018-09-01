// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.FastMath;
import java.io.PrintWriter;

// Referenced classes of package android.graphics:
//            Rect

public class RectF
    implements Parcelable
{

    public RectF()
    {
    }

    public RectF(float f, float f1, float f2, float f3)
    {
        left = f;
        top = f1;
        right = f2;
        bottom = f3;
    }

    public RectF(Rect rect)
    {
        if(rect == null)
        {
            bottom = 0.0F;
            right = 0.0F;
            top = 0.0F;
            left = 0.0F;
        } else
        {
            left = rect.left;
            top = rect.top;
            right = rect.right;
            bottom = rect.bottom;
        }
    }

    public RectF(RectF rectf)
    {
        if(rectf == null)
        {
            bottom = 0.0F;
            right = 0.0F;
            top = 0.0F;
            left = 0.0F;
        } else
        {
            left = rectf.left;
            top = rectf.top;
            right = rectf.right;
            bottom = rectf.bottom;
        }
    }

    public static boolean intersects(RectF rectf, RectF rectf1)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(rectf.left < rectf1.right)
        {
            flag1 = flag;
            if(rectf1.left < rectf.right)
            {
                flag1 = flag;
                if(rectf.top < rectf1.bottom)
                {
                    flag1 = flag;
                    if(rectf1.top < rectf.bottom)
                        flag1 = true;
                }
            }
        }
        return flag1;
    }

    public final float centerX()
    {
        return (left + right) * 0.5F;
    }

    public final float centerY()
    {
        return (top + bottom) * 0.5F;
    }

    public boolean contains(float f, float f1)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(left < right)
        {
            flag1 = flag;
            if(top < bottom)
            {
                flag1 = flag;
                if(f >= left)
                {
                    flag1 = flag;
                    if(f < right)
                    {
                        flag1 = flag;
                        if(f1 >= top)
                        {
                            flag1 = flag;
                            if(f1 < bottom)
                                flag1 = true;
                        }
                    }
                }
            }
        }
        return flag1;
    }

    public boolean contains(float f, float f1, float f2, float f3)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(left < right)
        {
            flag1 = flag;
            if(top < bottom)
            {
                flag1 = flag;
                if(left <= f)
                {
                    flag1 = flag;
                    if(top <= f1)
                    {
                        flag1 = flag;
                        if(right >= f2)
                        {
                            flag1 = flag;
                            if(bottom >= f3)
                                flag1 = true;
                        }
                    }
                }
            }
        }
        return flag1;
    }

    public boolean contains(RectF rectf)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(left < right)
        {
            flag1 = flag;
            if(top < bottom)
            {
                flag1 = flag;
                if(left <= rectf.left)
                {
                    flag1 = flag;
                    if(top <= rectf.top)
                    {
                        flag1 = flag;
                        if(right >= rectf.right)
                        {
                            flag1 = flag;
                            if(bottom >= rectf.bottom)
                                flag1 = true;
                        }
                    }
                }
            }
        }
        return flag1;
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
        obj = (RectF)obj;
        if(left != ((RectF) (obj)).left || top != ((RectF) (obj)).top || right != ((RectF) (obj)).right || bottom != ((RectF) (obj)).bottom)
            flag = false;
        return flag;
    }

    public int hashCode()
    {
        int i = 0;
        int j;
        int k;
        int l;
        if(left != 0.0F)
            j = Float.floatToIntBits(left);
        else
            j = 0;
        if(top != 0.0F)
            k = Float.floatToIntBits(top);
        else
            k = 0;
        if(right != 0.0F)
            l = Float.floatToIntBits(right);
        else
            l = 0;
        if(bottom != 0.0F)
            i = Float.floatToIntBits(bottom);
        return ((j * 31 + k) * 31 + l) * 31 + i;
    }

    public final float height()
    {
        return bottom - top;
    }

    public void inset(float f, float f1)
    {
        left = left + f;
        top = top + f1;
        right = right - f;
        bottom = bottom - f1;
    }

    public boolean intersect(float f, float f1, float f2, float f3)
    {
        if(left < f2 && f < right && top < f3 && f1 < bottom)
        {
            if(left < f)
                left = f;
            if(top < f1)
                top = f1;
            if(right > f2)
                right = f2;
            if(bottom > f3)
                bottom = f3;
            return true;
        } else
        {
            return false;
        }
    }

    public boolean intersect(RectF rectf)
    {
        return intersect(rectf.left, rectf.top, rectf.right, rectf.bottom);
    }

    public boolean intersects(float f, float f1, float f2, float f3)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(left < f2)
        {
            flag1 = flag;
            if(f < right)
            {
                flag1 = flag;
                if(top < f3)
                {
                    flag1 = flag;
                    if(f1 < bottom)
                        flag1 = true;
                }
            }
        }
        return flag1;
    }

    public final boolean isEmpty()
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(left < right)
            if(top >= bottom)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public void offset(float f, float f1)
    {
        left = left + f;
        top = top + f1;
        right = right + f;
        bottom = bottom + f1;
    }

    public void offsetTo(float f, float f1)
    {
        right = right + (f - left);
        bottom = bottom + (f1 - top);
        left = f;
        top = f1;
    }

    public void printShortString(PrintWriter printwriter)
    {
        printwriter.print('[');
        printwriter.print(left);
        printwriter.print(',');
        printwriter.print(top);
        printwriter.print("][");
        printwriter.print(right);
        printwriter.print(',');
        printwriter.print(bottom);
        printwriter.print(']');
    }

    public void readFromParcel(Parcel parcel)
    {
        left = parcel.readFloat();
        top = parcel.readFloat();
        right = parcel.readFloat();
        bottom = parcel.readFloat();
    }

    public void round(Rect rect)
    {
        rect.set(FastMath.round(left), FastMath.round(top), FastMath.round(right), FastMath.round(bottom));
    }

    public void roundOut(Rect rect)
    {
        rect.set((int)Math.floor(left), (int)Math.floor(top), (int)Math.ceil(right), (int)Math.ceil(bottom));
    }

    public void scale(float f)
    {
        if(f != 1.0F)
        {
            left = left * f;
            top = top * f;
            right = right * f;
            bottom = bottom * f;
        }
    }

    public void set(float f, float f1, float f2, float f3)
    {
        left = f;
        top = f1;
        right = f2;
        bottom = f3;
    }

    public void set(Rect rect)
    {
        left = rect.left;
        top = rect.top;
        right = rect.right;
        bottom = rect.bottom;
    }

    public void set(RectF rectf)
    {
        left = rectf.left;
        top = rectf.top;
        right = rectf.right;
        bottom = rectf.bottom;
    }

    public void setEmpty()
    {
        bottom = 0.0F;
        top = 0.0F;
        right = 0.0F;
        left = 0.0F;
    }

    public boolean setIntersect(RectF rectf, RectF rectf1)
    {
        if(rectf.left < rectf1.right && rectf1.left < rectf.right && rectf.top < rectf1.bottom && rectf1.top < rectf.bottom)
        {
            left = Math.max(rectf.left, rectf1.left);
            top = Math.max(rectf.top, rectf1.top);
            right = Math.min(rectf.right, rectf1.right);
            bottom = Math.min(rectf.bottom, rectf1.bottom);
            return true;
        } else
        {
            return false;
        }
    }

    public void sort()
    {
        if(left > right)
        {
            float f = left;
            left = right;
            right = f;
        }
        if(top > bottom)
        {
            float f1 = top;
            top = bottom;
            bottom = f1;
        }
    }

    public String toShortString()
    {
        return toShortString(new StringBuilder(32));
    }

    public String toShortString(StringBuilder stringbuilder)
    {
        stringbuilder.setLength(0);
        stringbuilder.append('[');
        stringbuilder.append(left);
        stringbuilder.append(',');
        stringbuilder.append(top);
        stringbuilder.append("][");
        stringbuilder.append(right);
        stringbuilder.append(',');
        stringbuilder.append(bottom);
        stringbuilder.append(']');
        return stringbuilder.toString();
    }

    public String toString()
    {
        return (new StringBuilder()).append("RectF(").append(left).append(", ").append(top).append(", ").append(right).append(", ").append(bottom).append(")").toString();
    }

    public void union(float f, float f1)
    {
        if(f < left)
            left = f;
        else
        if(f > right)
            right = f;
        if(f1 < top)
            top = f1;
        else
        if(f1 > bottom)
            bottom = f1;
    }

    public void union(float f, float f1, float f2, float f3)
    {
        if(f < f2 && f1 < f3)
            if(left < right && top < bottom)
            {
                if(left > f)
                    left = f;
                if(top > f1)
                    top = f1;
                if(right < f2)
                    right = f2;
                if(bottom < f3)
                    bottom = f3;
            } else
            {
                left = f;
                top = f1;
                right = f2;
                bottom = f3;
            }
    }

    public void union(RectF rectf)
    {
        union(rectf.left, rectf.top, rectf.right, rectf.bottom);
    }

    public final float width()
    {
        return right - left;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeFloat(left);
        parcel.writeFloat(top);
        parcel.writeFloat(right);
        parcel.writeFloat(bottom);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public RectF createFromParcel(Parcel parcel)
        {
            RectF rectf = new RectF();
            rectf.readFromParcel(parcel);
            return rectf;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public RectF[] newArray(int i)
        {
            return new RectF[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public float bottom;
    public float left;
    public float right;
    public float top;

}
