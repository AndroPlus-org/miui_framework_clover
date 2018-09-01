// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware;

import android.os.Parcel;
import android.os.Parcelable;

public class CameraStatus
    implements Parcelable
{

    public CameraStatus()
    {
    }

    public int describeContents()
    {
        return 0;
    }

    public void readFromParcel(Parcel parcel)
    {
        cameraId = parcel.readString();
        status = parcel.readInt();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(cameraId);
        parcel.writeInt(status);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public CameraStatus createFromParcel(Parcel parcel)
        {
            CameraStatus camerastatus = new CameraStatus();
            camerastatus.readFromParcel(parcel);
            return camerastatus;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public CameraStatus[] newArray(int i)
        {
            return new CameraStatus[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public String cameraId;
    public int status;

}
