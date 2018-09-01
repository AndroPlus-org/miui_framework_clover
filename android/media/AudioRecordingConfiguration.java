// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;

// Referenced classes of package android.media:
//            AudioFormat, MediaRecorder, AudioManager, AudioPatch, 
//            AudioPortConfig, AudioPort, AudioDeviceInfo

public final class AudioRecordingConfiguration
    implements Parcelable
{

    public AudioRecordingConfiguration(int i, int j, int k, AudioFormat audioformat, AudioFormat audioformat1, int l, String s)
    {
        mClientUid = i;
        mSessionId = j;
        mClientSource = k;
        mClientFormat = audioformat;
        mDeviceFormat = audioformat1;
        mPatchHandle = l;
        mClientPackageName = s;
    }

    private AudioRecordingConfiguration(Parcel parcel)
    {
        mSessionId = parcel.readInt();
        mClientSource = parcel.readInt();
        mClientFormat = (AudioFormat)AudioFormat.CREATOR.createFromParcel(parcel);
        mDeviceFormat = (AudioFormat)AudioFormat.CREATOR.createFromParcel(parcel);
        mPatchHandle = parcel.readInt();
        mClientPackageName = parcel.readString();
        mClientUid = parcel.readInt();
    }

    AudioRecordingConfiguration(Parcel parcel, AudioRecordingConfiguration audiorecordingconfiguration)
    {
        this(parcel);
    }

    public static AudioRecordingConfiguration anonymizedCopy(AudioRecordingConfiguration audiorecordingconfiguration)
    {
        return new AudioRecordingConfiguration(-1, audiorecordingconfiguration.mSessionId, audiorecordingconfiguration.mClientSource, audiorecordingconfiguration.mClientFormat, audiorecordingconfiguration.mDeviceFormat, audiorecordingconfiguration.mPatchHandle, "");
    }

    public static String toLogFriendlyString(AudioRecordingConfiguration audiorecordingconfiguration)
    {
        return new String((new StringBuilder()).append("session:").append(audiorecordingconfiguration.mSessionId).append(" -- source:").append(MediaRecorder.toLogFriendlyAudioSource(audiorecordingconfiguration.mClientSource)).append(" -- uid:").append(audiorecordingconfiguration.mClientUid).append(" -- patch:").append(audiorecordingconfiguration.mPatchHandle).append(" -- pack:").append(audiorecordingconfiguration.mClientPackageName).append(" -- format client=").append(audiorecordingconfiguration.mClientFormat.toLogFriendlyString()).append(", dev=").append(audiorecordingconfiguration.mDeviceFormat.toLogFriendlyString()).toString());
    }

    public int describeContents()
    {
        return 0;
    }

    public void dump(PrintWriter printwriter)
    {
        printwriter.println((new StringBuilder()).append("  ").append(toLogFriendlyString(this)).toString());
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(this == obj)
            return true;
        if(obj == null || (obj instanceof AudioRecordingConfiguration) ^ true)
            return false;
        obj = (AudioRecordingConfiguration)obj;
        boolean flag1 = flag;
        if(mClientUid == ((AudioRecordingConfiguration) (obj)).mClientUid)
        {
            flag1 = flag;
            if(mSessionId == ((AudioRecordingConfiguration) (obj)).mSessionId)
            {
                flag1 = flag;
                if(mClientSource == ((AudioRecordingConfiguration) (obj)).mClientSource)
                {
                    flag1 = flag;
                    if(mPatchHandle == ((AudioRecordingConfiguration) (obj)).mPatchHandle)
                    {
                        flag1 = flag;
                        if(mClientFormat.equals(((AudioRecordingConfiguration) (obj)).mClientFormat))
                        {
                            flag1 = flag;
                            if(mDeviceFormat.equals(((AudioRecordingConfiguration) (obj)).mDeviceFormat))
                                flag1 = mClientPackageName.equals(((AudioRecordingConfiguration) (obj)).mClientPackageName);
                        }
                    }
                }
            }
        }
        return flag1;
    }

    public AudioDeviceInfo getAudioDevice()
    {
        ArrayList arraylist = new ArrayList();
        if(AudioManager.listAudioPatches(arraylist) != 0)
        {
            Log.e(TAG, "Error retrieving list of audio patches");
            return null;
        }
        int i = 0;
        do
        {
            if(i >= arraylist.size())
                break;
            AudioPatch audiopatch = (AudioPatch)arraylist.get(i);
            if(audiopatch.id() == mPatchHandle)
            {
                Object aobj[] = audiopatch.sources();
                if(aobj != null && aobj.length > 0)
                {
                    int j = aobj[0].port().id();
                    aobj = AudioManager.getDevicesStatic(1);
                    for(i = 0; i < aobj.length; i++)
                        if(aobj[i].getId() == j)
                            return aobj[i];

                }
                break;
            }
            i++;
        } while(true);
        Log.e(TAG, "Couldn't find device for recording, did recording end already?");
        return null;
    }

    public int getClientAudioSessionId()
    {
        return mSessionId;
    }

    public int getClientAudioSource()
    {
        return mClientSource;
    }

    public AudioFormat getClientFormat()
    {
        return mClientFormat;
    }

    public String getClientPackageName()
    {
        return mClientPackageName;
    }

    public int getClientUid()
    {
        return mClientUid;
    }

    public AudioFormat getFormat()
    {
        return mDeviceFormat;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            Integer.valueOf(mSessionId), Integer.valueOf(mClientSource)
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mSessionId);
        parcel.writeInt(mClientSource);
        mClientFormat.writeToParcel(parcel, 0);
        mDeviceFormat.writeToParcel(parcel, 0);
        parcel.writeInt(mPatchHandle);
        parcel.writeString(mClientPackageName);
        parcel.writeInt(mClientUid);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public AudioRecordingConfiguration createFromParcel(Parcel parcel)
        {
            return new AudioRecordingConfiguration(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public AudioRecordingConfiguration[] newArray(int i)
        {
            return new AudioRecordingConfiguration[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String TAG = new String("AudioRecordingConfiguration");
    private final AudioFormat mClientFormat;
    private final String mClientPackageName;
    private final int mClientSource;
    private final int mClientUid;
    private final AudioFormat mDeviceFormat;
    private final int mPatchHandle;
    private final int mSessionId;

}
