// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.*;
import java.io.FileDescriptor;
import java.io.IOException;

public final class IpSecUdpEncapResponse
    implements Parcelable
{

    public IpSecUdpEncapResponse(int i)
    {
        if(i == 0)
        {
            throw new IllegalArgumentException("Valid status implies other args must be provided");
        } else
        {
            status = i;
            resourceId = 0;
            port = -1;
            fileDescriptor = null;
            return;
        }
    }

    public IpSecUdpEncapResponse(int i, int j, int k, FileDescriptor filedescriptor)
        throws IOException
    {
        ParcelFileDescriptor parcelfiledescriptor = null;
        super();
        if(i == 0 && filedescriptor == null)
            throw new IllegalArgumentException("Valid status implies FD must be non-null");
        status = i;
        resourceId = j;
        port = k;
        if(status == 0)
            parcelfiledescriptor = ParcelFileDescriptor.dup(filedescriptor);
        fileDescriptor = parcelfiledescriptor;
    }

    private IpSecUdpEncapResponse(Parcel parcel)
    {
        status = parcel.readInt();
        resourceId = parcel.readInt();
        port = parcel.readInt();
        fileDescriptor = (ParcelFileDescriptor)parcel.readParcelable(android/os/ParcelFileDescriptor.getClassLoader());
    }

    IpSecUdpEncapResponse(Parcel parcel, IpSecUdpEncapResponse ipsecudpencapresponse)
    {
        this(parcel);
    }

    public int describeContents()
    {
        int i;
        if(fileDescriptor != null)
            i = 1;
        else
            i = 0;
        return i;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(status);
        parcel.writeInt(resourceId);
        parcel.writeInt(port);
        parcel.writeParcelable(fileDescriptor, 1);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public IpSecUdpEncapResponse createFromParcel(Parcel parcel)
        {
            return new IpSecUdpEncapResponse(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public IpSecUdpEncapResponse[] newArray(int i)
        {
            return new IpSecUdpEncapResponse[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String TAG = "IpSecUdpEncapResponse";
    public final ParcelFileDescriptor fileDescriptor;
    public final int port;
    public final int resourceId;
    public final int status;

}
