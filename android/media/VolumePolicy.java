// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

public final class VolumePolicy
    implements Parcelable
{

    public VolumePolicy(boolean flag, boolean flag1, boolean flag2, int i)
    {
        volumeDownToEnterSilent = flag;
        volumeUpToExitSilent = flag1;
        doNotDisturbWhenSilent = flag2;
        vibrateToSilentDebounce = i;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(!(obj instanceof VolumePolicy))
            return false;
        if(obj == this)
            return true;
        obj = (VolumePolicy)obj;
        if(((VolumePolicy) (obj)).volumeDownToEnterSilent == volumeDownToEnterSilent && ((VolumePolicy) (obj)).volumeUpToExitSilent == volumeUpToExitSilent && ((VolumePolicy) (obj)).doNotDisturbWhenSilent == doNotDisturbWhenSilent)
        {
            if(((VolumePolicy) (obj)).vibrateToSilentDebounce != vibrateToSilentDebounce)
                flag = false;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            Boolean.valueOf(volumeDownToEnterSilent), Boolean.valueOf(volumeUpToExitSilent), Boolean.valueOf(doNotDisturbWhenSilent), Integer.valueOf(vibrateToSilentDebounce)
        });
    }

    public String toString()
    {
        return (new StringBuilder()).append("VolumePolicy[volumeDownToEnterSilent=").append(volumeDownToEnterSilent).append(",volumeUpToExitSilent=").append(volumeUpToExitSilent).append(",doNotDisturbWhenSilent=").append(doNotDisturbWhenSilent).append(",vibrateToSilentDebounce=").append(vibrateToSilentDebounce).append("]").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        if(volumeDownToEnterSilent)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(volumeUpToExitSilent)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(doNotDisturbWhenSilent)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeInt(vibrateToSilentDebounce);
    }

    public static final int A11Y_MODE_INDEPENDENT_A11Y_VOLUME = 1;
    public static final int A11Y_MODE_MEDIA_A11Y_VOLUME = 0;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public VolumePolicy createFromParcel(Parcel parcel)
        {
            boolean flag = true;
            boolean flag1;
            boolean flag2;
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            if(parcel.readInt() != 0)
                flag2 = true;
            else
                flag2 = false;
            if(parcel.readInt() == 0)
                flag = false;
            return new VolumePolicy(flag1, flag2, flag, parcel.readInt());
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public VolumePolicy[] newArray(int i)
        {
            return new VolumePolicy[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final VolumePolicy DEFAULT = new VolumePolicy(true, true, true, 400);
    public final boolean doNotDisturbWhenSilent;
    public final int vibrateToSilentDebounce;
    public final boolean volumeDownToEnterSilent;
    public final boolean volumeUpToExitSilent;

}
