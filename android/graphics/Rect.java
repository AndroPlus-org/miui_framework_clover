// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Rect
    implements Parcelable
{
    private static final class UnflattenHelper
    {

        static Matcher getMatcher(String s)
        {
            return FLATTENED_PATTERN.matcher(s);
        }

        private static final Pattern FLATTENED_PATTERN = Pattern.compile("(-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+)");


        private UnflattenHelper()
        {
        }
    }


    public Rect()
    {
    }

    public Rect(int i, int j, int k, int l)
    {
        left = i;
        top = j;
        right = k;
        bottom = l;
    }

    public Rect(Rect rect)
    {
        if(rect == null)
        {
            bottom = 0;
            right = 0;
            top = 0;
            left = 0;
        } else
        {
            left = rect.left;
            top = rect.top;
            right = rect.right;
            bottom = rect.bottom;
        }
    }

    public static boolean intersects(Rect rect, Rect rect1)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(rect.left < rect1.right)
        {
            flag1 = flag;
            if(rect1.left < rect.right)
            {
                flag1 = flag;
                if(rect.top < rect1.bottom)
                {
                    flag1 = flag;
                    if(rect1.top < rect.bottom)
                        flag1 = true;
                }
            }
        }
        return flag1;
    }

    public static Rect unflattenFromString(String s)
    {
        if(TextUtils.isEmpty(s))
            return null;
        s = UnflattenHelper.getMatcher(s);
        if(!s.matches())
            return null;
        else
            return new Rect(Integer.parseInt(s.group(1)), Integer.parseInt(s.group(2)), Integer.parseInt(s.group(3)), Integer.parseInt(s.group(4)));
    }

    public final int centerX()
    {
        return left + right >> 1;
    }

    public final int centerY()
    {
        return top + bottom >> 1;
    }

    public boolean contains(int i, int j)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(left < right)
        {
            flag1 = flag;
            if(top < bottom)
            {
                flag1 = flag;
                if(i >= left)
                {
                    flag1 = flag;
                    if(i < right)
                    {
                        flag1 = flag;
                        if(j >= top)
                        {
                            flag1 = flag;
                            if(j < bottom)
                                flag1 = true;
                        }
                    }
                }
            }
        }
        return flag1;
    }

    public boolean contains(int i, int j, int k, int l)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(left < right)
        {
            flag1 = flag;
            if(top < bottom)
            {
                flag1 = flag;
                if(left <= i)
                {
                    flag1 = flag;
                    if(top <= j)
                    {
                        flag1 = flag;
                        if(right >= k)
                        {
                            flag1 = flag;
                            if(bottom >= l)
                                flag1 = true;
                        }
                    }
                }
            }
        }
        return flag1;
    }

    public boolean contains(Rect rect)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(left < right)
        {
            flag1 = flag;
            if(top < bottom)
            {
                flag1 = flag;
                if(left <= rect.left)
                {
                    flag1 = flag;
                    if(top <= rect.top)
                    {
                        flag1 = flag;
                        if(right >= rect.right)
                        {
                            flag1 = flag;
                            if(bottom >= rect.bottom)
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
        obj = (Rect)obj;
        if(left != ((Rect) (obj)).left || top != ((Rect) (obj)).top || right != ((Rect) (obj)).right || bottom != ((Rect) (obj)).bottom)
            flag = false;
        return flag;
    }

    public final float exactCenterX()
    {
        return (float)(left + right) * 0.5F;
    }

    public final float exactCenterY()
    {
        return (float)(top + bottom) * 0.5F;
    }

    public String flattenToString()
    {
        StringBuilder stringbuilder = new StringBuilder(32);
        stringbuilder.append(left);
        stringbuilder.append(' ');
        stringbuilder.append(top);
        stringbuilder.append(' ');
        stringbuilder.append(right);
        stringbuilder.append(' ');
        stringbuilder.append(bottom);
        return stringbuilder.toString();
    }

    public int hashCode()
    {
        return ((left * 31 + top) * 31 + right) * 31 + bottom;
    }

    public final int height()
    {
        return bottom - top;
    }

    public void inset(int i, int j)
    {
        left = left + i;
        top = top + j;
        right = right - i;
        bottom = bottom - j;
    }

    public void inset(int i, int j, int k, int l)
    {
        left = left + i;
        top = top + j;
        right = right - k;
        bottom = bottom - l;
    }

    public void inset(Rect rect)
    {
        left = left + rect.left;
        top = top + rect.top;
        right = right - rect.right;
        bottom = bottom - rect.bottom;
    }

    public boolean intersect(int i, int j, int k, int l)
    {
        if(left < k && i < right && top < l && j < bottom)
        {
            if(left < i)
                left = i;
            if(top < j)
                top = j;
            if(right > k)
                right = k;
            if(bottom > l)
                bottom = l;
            return true;
        } else
        {
            return false;
        }
    }

    public boolean intersect(Rect rect)
    {
        return intersect(rect.left, rect.top, rect.right, rect.bottom);
    }

    public boolean intersects(int i, int j, int k, int l)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(left < k)
        {
            flag1 = flag;
            if(i < right)
            {
                flag1 = flag;
                if(top < l)
                {
                    flag1 = flag;
                    if(j < bottom)
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

    public void offset(int i, int j)
    {
        left = left + i;
        top = top + j;
        right = right + i;
        bottom = bottom + j;
    }

    public void offsetTo(int i, int j)
    {
        right = right + (i - left);
        bottom = bottom + (j - top);
        left = i;
        top = j;
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
        left = parcel.readInt();
        top = parcel.readInt();
        right = parcel.readInt();
        bottom = parcel.readInt();
    }

    public void scale(float f)
    {
        if(f != 1.0F)
        {
            left = (int)((float)left * f + 0.5F);
            top = (int)((float)top * f + 0.5F);
            right = (int)((float)right * f + 0.5F);
            bottom = (int)((float)bottom * f + 0.5F);
        }
    }

    public void set(int i, int j, int k, int l)
    {
        left = i;
        top = j;
        right = k;
        bottom = l;
    }

    public void set(Rect rect)
    {
        left = rect.left;
        top = rect.top;
        right = rect.right;
        bottom = rect.bottom;
    }

    public void setEmpty()
    {
        bottom = 0;
        top = 0;
        right = 0;
        left = 0;
    }

    public boolean setIntersect(Rect rect, Rect rect1)
    {
        if(rect.left < rect1.right && rect1.left < rect.right && rect.top < rect1.bottom && rect1.top < rect.bottom)
        {
            left = Math.max(rect.left, rect1.left);
            top = Math.max(rect.top, rect1.top);
            right = Math.min(rect.right, rect1.right);
            bottom = Math.min(rect.bottom, rect1.bottom);
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
            int i = left;
            left = right;
            right = i;
        }
        if(top > bottom)
        {
            int j = top;
            top = bottom;
            bottom = j;
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
        StringBuilder stringbuilder = new StringBuilder(32);
        stringbuilder.append("Rect(");
        stringbuilder.append(left);
        stringbuilder.append(", ");
        stringbuilder.append(top);
        stringbuilder.append(" - ");
        stringbuilder.append(right);
        stringbuilder.append(", ");
        stringbuilder.append(bottom);
        stringbuilder.append(")");
        return stringbuilder.toString();
    }

    public void union(int i, int j)
    {
        if(i < left)
            left = i;
        else
        if(i > right)
            right = i;
        if(j < top)
            top = j;
        else
        if(j > bottom)
            bottom = j;
    }

    public void union(int i, int j, int k, int l)
    {
        if(i < k && j < l)
            if(left < right && top < bottom)
            {
                if(left > i)
                    left = i;
                if(top > j)
                    top = j;
                if(right < k)
                    right = k;
                if(bottom < l)
                    bottom = l;
            } else
            {
                left = i;
                top = j;
                right = k;
                bottom = l;
            }
    }

    public void union(Rect rect)
    {
        union(rect.left, rect.top, rect.right, rect.bottom);
    }

    public final int width()
    {
        return right - left;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(left);
        parcel.writeInt(top);
        parcel.writeInt(right);
        parcel.writeInt(bottom);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public Rect createFromParcel(Parcel parcel)
        {
            Rect rect = new Rect();
            rect.readFromParcel(parcel);
            return rect;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public Rect[] newArray(int i)
        {
            return new Rect[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public int bottom;
    public int left;
    public int right;
    public int top;

}
