// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.os.*;
import android.util.Slog;
import java.io.IOException;

public class ProfilerInfo
    implements Parcelable
{

    public ProfilerInfo(ProfilerInfo profilerinfo)
    {
        profileFile = profilerinfo.profileFile;
        profileFd = profilerinfo.profileFd;
        samplingInterval = profilerinfo.samplingInterval;
        autoStopProfiler = profilerinfo.autoStopProfiler;
        streamingOutput = profilerinfo.streamingOutput;
        agent = profilerinfo.agent;
    }

    private ProfilerInfo(Parcel parcel)
    {
        boolean flag = true;
        super();
        profileFile = parcel.readString();
        ParcelFileDescriptor parcelfiledescriptor;
        boolean flag1;
        if(parcel.readInt() != 0)
            parcelfiledescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
        else
            parcelfiledescriptor = null;
        profileFd = parcelfiledescriptor;
        samplingInterval = parcel.readInt();
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        autoStopProfiler = flag1;
        if(parcel.readInt() != 0)
            flag1 = flag;
        else
            flag1 = false;
        streamingOutput = flag1;
        agent = parcel.readString();
    }

    ProfilerInfo(Parcel parcel, ProfilerInfo profilerinfo)
    {
        this(parcel);
    }

    public ProfilerInfo(String s, ParcelFileDescriptor parcelfiledescriptor, int i, boolean flag, boolean flag1, String s1)
    {
        profileFile = s;
        profileFd = parcelfiledescriptor;
        samplingInterval = i;
        autoStopProfiler = flag;
        streamingOutput = flag1;
        agent = s1;
    }

    public void closeFd()
    {
        if(profileFd != null)
        {
            try
            {
                profileFd.close();
            }
            catch(IOException ioexception)
            {
                Slog.w("ProfilerInfo", "Failure closing profile fd", ioexception);
            }
            profileFd = null;
        }
    }

    public int describeContents()
    {
        if(profileFd != null)
            return profileFd.describeContents();
        else
            return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeString(profileFile);
        if(profileFd != null)
        {
            parcel.writeInt(1);
            profileFd.writeToParcel(parcel, i);
        } else
        {
            parcel.writeInt(0);
        }
        parcel.writeInt(samplingInterval);
        if(autoStopProfiler)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(streamingOutput)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeString(agent);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ProfilerInfo createFromParcel(Parcel parcel)
        {
            return new ProfilerInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ProfilerInfo[] newArray(int i)
        {
            return new ProfilerInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String TAG = "ProfilerInfo";
    public final String agent;
    public final boolean autoStopProfiler;
    public ParcelFileDescriptor profileFd;
    public final String profileFile;
    public final int samplingInterval;
    public final boolean streamingOutput;

}
