// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import java.util.Arrays;

// Referenced classes of package android.os:
//            Parcelable, Parcel

public abstract class VibrationEffect
    implements Parcelable
{
    public static class OneShot extends VibrationEffect
        implements Parcelable
    {

        public boolean equals(Object obj)
        {
            boolean flag = false;
            if(!(obj instanceof OneShot))
                return false;
            obj = (OneShot)obj;
            boolean flag1 = flag;
            if(((OneShot) (obj)).mTiming == mTiming)
            {
                flag1 = flag;
                if(((OneShot) (obj)).mAmplitude == mAmplitude)
                    flag1 = true;
            }
            return flag1;
        }

        public int getAmplitude()
        {
            return mAmplitude;
        }

        public long getTiming()
        {
            return mTiming;
        }

        public int hashCode()
        {
            int i = (int)mTiming;
            return mAmplitude * 37;
        }

        public String toString()
        {
            return (new StringBuilder()).append("OneShot{mTiming=").append(mTiming).append(", mAmplitude=").append(mAmplitude).append("}").toString();
        }

        public void validate()
        {
            while(mAmplitude < -1 || mAmplitude == 0 || mAmplitude > 255) 
                throw new IllegalArgumentException((new StringBuilder()).append("amplitude must either be DEFAULT_AMPLITUDE, or between 1 and 255 inclusive (amplitude=").append(mAmplitude).append(")").toString());
            if(mTiming <= 0L)
                throw new IllegalArgumentException((new StringBuilder()).append("timing must be positive (timing=").append(mTiming).append(")").toString());
            else
                return;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(1);
            parcel.writeLong(mTiming);
            parcel.writeInt(mAmplitude);
        }

        public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

            public OneShot createFromParcel(Parcel parcel)
            {
                parcel.readInt();
                return new OneShot(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public OneShot[] newArray(int i)
            {
                return new OneShot[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private int mAmplitude;
        private long mTiming;


        public OneShot(long l, int i)
        {
            mTiming = l;
            mAmplitude = i;
        }

        public OneShot(Parcel parcel)
        {
            this(parcel.readLong(), parcel.readInt());
        }
    }

    public static class Prebaked extends VibrationEffect
        implements Parcelable
    {

        public boolean equals(Object obj)
        {
            boolean flag = false;
            if(!(obj instanceof Prebaked))
                return false;
            obj = (Prebaked)obj;
            boolean flag1 = flag;
            if(mEffectId == ((Prebaked) (obj)).mEffectId)
            {
                flag1 = flag;
                if(mFallback == ((Prebaked) (obj)).mFallback)
                    flag1 = true;
            }
            return flag1;
        }

        public int getId()
        {
            return mEffectId;
        }

        public int hashCode()
        {
            return mEffectId;
        }

        public boolean shouldFallback()
        {
            return mFallback;
        }

        public String toString()
        {
            return (new StringBuilder()).append("Prebaked{mEffectId=").append(mEffectId).append(", mFallback=").append(mFallback).append("}").toString();
        }

        public void validate()
        {
            switch(mEffectId)
            {
            default:
                throw new IllegalArgumentException((new StringBuilder()).append("Unknown prebaked effect type (value=").append(mEffectId).append(")").toString());

            case 0: // '\0'
            case 1: // '\001'
            case 2: // '\002'
                return;
            }
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(3);
            parcel.writeInt(mEffectId);
            if(mFallback)
                i = 1;
            else
                i = 0;
            parcel.writeByte((byte)i);
        }

        public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

            public Prebaked createFromParcel(Parcel parcel)
            {
                parcel.readInt();
                return new Prebaked(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public Prebaked[] newArray(int i)
            {
                return new Prebaked[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private int mEffectId;
        private boolean mFallback;


        public Prebaked(int i, boolean flag)
        {
            mEffectId = i;
            mFallback = flag;
        }

        public Prebaked(Parcel parcel)
        {
            boolean flag = false;
            int i = parcel.readInt();
            if(parcel.readByte() != 0)
                flag = true;
            this(i, flag);
        }
    }

    public static class Waveform extends VibrationEffect
        implements Parcelable
    {

        private static boolean hasNonZeroEntry(long al[])
        {
            int i = al.length;
            for(int j = 0; j < i; j++)
                if(al[j] != 0L)
                    return true;

            return false;
        }

        public boolean equals(Object obj)
        {
            boolean flag = false;
            if(!(obj instanceof Waveform))
                return false;
            obj = (Waveform)obj;
            boolean flag1 = flag;
            if(Arrays.equals(mTimings, ((Waveform) (obj)).mTimings))
            {
                flag1 = flag;
                if(Arrays.equals(mAmplitudes, ((Waveform) (obj)).mAmplitudes))
                {
                    flag1 = flag;
                    if(mRepeat == ((Waveform) (obj)).mRepeat)
                        flag1 = true;
                }
            }
            return flag1;
        }

        public int[] getAmplitudes()
        {
            return mAmplitudes;
        }

        public int getRepeatIndex()
        {
            return mRepeat;
        }

        public long[] getTimings()
        {
            return mTimings;
        }

        public int hashCode()
        {
            Arrays.hashCode(mTimings);
            Arrays.hashCode(mAmplitudes);
            return mRepeat * 37;
        }

        public String toString()
        {
            return (new StringBuilder()).append("Waveform{mTimings=").append(Arrays.toString(mTimings)).append(", mAmplitudes=").append(Arrays.toString(mAmplitudes)).append(", mRepeat=").append(mRepeat).append("}").toString();
        }

        public void validate()
        {
            boolean flag = false;
            if(mTimings.length != mAmplitudes.length)
                throw new IllegalArgumentException((new StringBuilder()).append("timing and amplitude arrays must be of equal length (timings.length=").append(mTimings.length).append(", amplitudes.length=").append(mAmplitudes.length).append(")").toString());
            if(!hasNonZeroEntry(mTimings))
                throw new IllegalArgumentException((new StringBuilder()).append("at least one timing must be non-zero (timings=").append(Arrays.toString(mTimings)).append(")").toString());
            long al[] = mTimings;
            int j = al.length;
            for(int k = 0; k < j; k++)
                if(al[k] < 0L)
                    throw new IllegalArgumentException((new StringBuilder()).append("timings must all be >= 0 (timings=").append(Arrays.toString(mTimings)).append(")").toString());

            al = mAmplitudes;
            j = al.length;
            for(int l = ((flag) ? 1 : 0); l < j; l++)
            {
                int i = al[l];
                if(i < -1 || i > 255)
                    throw new IllegalArgumentException((new StringBuilder()).append("amplitudes must all be DEFAULT_AMPLITUDE or between 0 and 255 (amplitudes=").append(Arrays.toString(mAmplitudes)).append(")").toString());
            }

            if(mRepeat < -1 || mRepeat >= mTimings.length)
                throw new IllegalArgumentException((new StringBuilder()).append("repeat index must be within the bounds of the timings array (timings.length=").append(mTimings.length).append(", index=").append(mRepeat).append(")").toString());
            else
                return;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(2);
            parcel.writeLongArray(mTimings);
            parcel.writeIntArray(mAmplitudes);
            parcel.writeInt(mRepeat);
        }

        public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

            public Waveform createFromParcel(Parcel parcel)
            {
                parcel.readInt();
                return new Waveform(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public Waveform[] newArray(int i)
            {
                return new Waveform[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private int mAmplitudes[];
        private int mRepeat;
        private long mTimings[];


        public Waveform(Parcel parcel)
        {
            this(parcel.createLongArray(), parcel.createIntArray(), parcel.readInt());
        }

        public Waveform(long al[], int ai[], int i)
        {
            mTimings = new long[al.length];
            System.arraycopy(al, 0, mTimings, 0, al.length);
            mAmplitudes = new int[ai.length];
            System.arraycopy(ai, 0, mAmplitudes, 0, ai.length);
            mRepeat = i;
        }
    }


    public VibrationEffect()
    {
    }

    public static VibrationEffect createOneShot(long l, int i)
    {
        OneShot oneshot = new OneShot(l, i);
        oneshot.validate();
        return oneshot;
    }

    public static VibrationEffect createWaveform(long al[], int i)
    {
        int ai[] = new int[al.length];
        for(int j = 0; j < al.length / 2; j++)
            ai[j * 2 + 1] = -1;

        return createWaveform(al, ai, i);
    }

    public static VibrationEffect createWaveform(long al[], int ai[], int i)
    {
        al = new Waveform(al, ai, i);
        al.validate();
        return al;
    }

    public static VibrationEffect get(int i)
    {
        return get(i, true);
    }

    public static VibrationEffect get(int i, boolean flag)
    {
        Prebaked prebaked = new Prebaked(i, flag);
        prebaked.validate();
        return prebaked;
    }

    public int describeContents()
    {
        return 0;
    }

    public abstract void validate();

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public VibrationEffect createFromParcel(Parcel parcel)
        {
            int i = parcel.readInt();
            if(i == 1)
                return new OneShot(parcel);
            if(i == 2)
                return new Waveform(parcel);
            if(i == 3)
                return new Prebaked(parcel);
            else
                throw new IllegalStateException("Unexpected vibration event type token in parcel.");
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public VibrationEffect[] newArray(int i)
        {
            return new VibrationEffect[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int DEFAULT_AMPLITUDE = -1;
    public static final int EFFECT_CLICK = 0;
    public static final int EFFECT_DOUBLE_CLICK = 1;
    public static final int EFFECT_TICK = 2;
    private static final int PARCEL_TOKEN_EFFECT = 3;
    private static final int PARCEL_TOKEN_ONE_SHOT = 1;
    private static final int PARCEL_TOKEN_WAVEFORM = 2;

}
