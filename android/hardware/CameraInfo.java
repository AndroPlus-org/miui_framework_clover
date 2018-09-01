// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware;

import android.os.Parcel;
import android.os.Parcelable;

public class CameraInfo
    implements Parcelable
{

    public CameraInfo()
    {
        info = new Camera.CameraInfo();
    }

    public int describeContents()
    {
        return 0;
    }

    public void readFromParcel(Parcel parcel)
    {
        info.facing = parcel.readInt();
        info.orientation = parcel.readInt();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(info.facing);
        parcel.writeInt(info.orientation);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public CameraInfo createFromParcel(Parcel parcel)
        {
            CameraInfo camerainfo = new CameraInfo();
            camerainfo.readFromParcel(parcel);
            return camerainfo;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public CameraInfo[] newArray(int i)
        {
            return new CameraInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public Camera.CameraInfo info;

}
