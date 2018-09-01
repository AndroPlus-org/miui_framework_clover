// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.PrintWriter;

public class Point
    implements Parcelable
{

    public Point()
    {
    }

    public Point(int i, int j)
    {
        x = i;
        y = j;
    }

    public Point(Point point)
    {
        x = point.x;
        y = point.y;
    }

    public int describeContents()
    {
        return 0;
    }

    public final boolean equals(int i, int j)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(x == i)
        {
            flag1 = flag;
            if(y == j)
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
        obj = (Point)obj;
        if(x != ((Point) (obj)).x)
            return false;
        return y == ((Point) (obj)).y;
    }

    public int hashCode()
    {
        return x * 31 + y;
    }

    public final void negate()
    {
        x = -x;
        y = -y;
    }

    public final void offset(int i, int j)
    {
        x = x + i;
        y = y + j;
    }

    public void printShortString(PrintWriter printwriter)
    {
        printwriter.print("[");
        printwriter.print(x);
        printwriter.print(",");
        printwriter.print(y);
        printwriter.print("]");
    }

    public void readFromParcel(Parcel parcel)
    {
        x = parcel.readInt();
        y = parcel.readInt();
    }

    public void set(int i, int j)
    {
        x = i;
        y = j;
    }

    public String toString()
    {
        return (new StringBuilder()).append("Point(").append(x).append(", ").append(y).append(")").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(x);
        parcel.writeInt(y);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public Point createFromParcel(Parcel parcel)
        {
            Point point = new Point();
            point.readFromParcel(parcel);
            return point;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public Point[] newArray(int i)
        {
            return new Point[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public int x;
    public int y;

}
