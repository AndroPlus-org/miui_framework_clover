// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.os.Parcel;
import android.os.Parcelable;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Objects;

// Referenced classes of package android.media:
//            PlayerBase

public final class VolumeShaper
    implements AutoCloseable
{
    public static final class Configuration
        implements Parcelable
    {

        static void _2D_wrap0(float af[], float af1[], boolean flag, boolean flag1)
        {
            checkCurveForErrorsAndThrowException(af, af1, flag, flag1);
        }

        static void _2D_wrap1(float f, boolean flag)
        {
            checkValidVolumeAndThrowException(f, flag);
        }

        static void _2D_wrap2(float af[], boolean flag)
        {
            clampVolume(af, flag);
        }

        private static String checkCurveForErrors(float af[], float af1[], boolean flag)
        {
            if(af == null)
                return "times array must be non-null";
            if(af1 == null)
                return "volumes array must be non-null";
            if(af.length != af1.length)
                return "array length must match";
            if(af.length < 2)
                return "array length must be at least 2";
            if(af.length > 16)
                return "array length must be no larger than 16";
            if(af[0] != 0.0F)
                return "times must start at 0.f";
            if(af[af.length - 1] != 1.0F)
                return "times must end at 1.f";
            boolean flag1;
            for(int i = 1; i < af.length; i++)
            {
                if(af[i] > af[i - 1])
                    flag1 = true;
                else
                    flag1 = false;
                if(!flag1)
                    return (new StringBuilder()).append("times not monotonic increasing, check index ").append(i).toString();
            }

            if(flag)
            {
                boolean flag2;
                for(int j = 0; j < af1.length; j++)
                {
                    if(af1[j] <= 0.0F)
                        flag2 = true;
                    else
                        flag2 = false;
                    if(!flag2)
                        return (new StringBuilder()).append("volumes for log scale cannot be positive, check index ").append(j).toString();
                }

            } else
            {
                int k = 0;
label0:
                do
                {
label1:
                    {
                        if(k >= af1.length)
                            break label0;
                        boolean flag3;
                        if(af1[k] >= 0.0F)
                            flag3 = true;
                        else
                            flag3 = false;
                        if(flag3)
                        {
                            if(af1[k] <= 1.0F)
                                flag3 = true;
                            else
                                flag3 = false;
                            if(!(flag3 ^ true))
                                break label1;
                        }
                        return (new StringBuilder()).append("volumes for linear scale must be between 0.f and 1.f, check index ").append(k).toString();
                    }
                    k++;
                } while(true);
            }
            return null;
        }

        private static void checkCurveForErrorsAndThrowException(float af[], float af1[], boolean flag, boolean flag1)
        {
            af = checkCurveForErrors(af, af1, flag);
            if(af != null)
            {
                if(flag1)
                    throw new IllegalStateException(af);
                else
                    throw new IllegalArgumentException(af);
            } else
            {
                return;
            }
        }

        private static void checkValidVolumeAndThrowException(float f, boolean flag)
        {
label0:
            {
                boolean flag1 = true;
                boolean flag2 = true;
                if(flag)
                {
                    if(f > 0.0F)
                        flag2 = false;
                    if(!flag2)
                        throw new IllegalArgumentException("dbfs volume must be 0.f or less");
                    break label0;
                }
                if(f >= 0.0F)
                    flag2 = true;
                else
                    flag2 = false;
                if(flag2)
                {
                    if(f <= 1.0F)
                        flag2 = flag1;
                    else
                        flag2 = false;
                    if(!(flag2 ^ true))
                        break label0;
                }
                throw new IllegalArgumentException("volume must be >= 0.f and <= 1.f");
            }
        }

        private static void clampVolume(float af[], boolean flag)
        {
            if(flag)
            {
                int i = 0;
                while(i < af.length) 
                {
                    boolean flag1;
                    if(af[i] <= 0.0F)
                        flag1 = true;
                    else
                        flag1 = false;
                    if(!flag1)
                        af[i] = 0.0F;
                    i++;
                }
            } else
            {
                int j = 0;
                while(j < af.length) 
                {
                    boolean flag2;
                    if(af[j] >= 0.0F)
                        flag2 = true;
                    else
                        flag2 = false;
                    if(!flag2)
                    {
                        af[j] = 0.0F;
                    } else
                    {
                        boolean flag3;
                        if(af[j] <= 1.0F)
                            flag3 = true;
                        else
                            flag3 = false;
                        if(!flag3)
                            af[j] = 1.0F;
                    }
                    j++;
                }
            }
        }

        public static int getMaximumCurvePoints()
        {
            return 16;
        }

        public int describeContents()
        {
            return 0;
        }

        public boolean equals(Object obj)
        {
            boolean flag = true;
            if(!(obj instanceof Configuration))
                return false;
            if(obj == this)
                return true;
            obj = (Configuration)obj;
            if(mType == ((Configuration) (obj)).mType && mId == ((Configuration) (obj)).mId)
            {
                if(mType != 0)
                    if(mOptionFlags == ((Configuration) (obj)).mOptionFlags && mDurationMs == ((Configuration) (obj)).mDurationMs && mInterpolatorType == ((Configuration) (obj)).mInterpolatorType && Arrays.equals(mTimes, ((Configuration) (obj)).mTimes))
                        flag = Arrays.equals(mVolumes, ((Configuration) (obj)).mVolumes);
                    else
                        flag = false;
            } else
            {
                flag = false;
            }
            return flag;
        }

        int getAllOptionFlags()
        {
            return mOptionFlags;
        }

        public long getDuration()
        {
            return (long)mDurationMs;
        }

        public int getId()
        {
            return mId;
        }

        public int getInterpolatorType()
        {
            return mInterpolatorType;
        }

        public int getOptionFlags()
        {
            return mOptionFlags & 3;
        }

        public float[] getTimes()
        {
            return mTimes;
        }

        public int getType()
        {
            return mType;
        }

        public float[] getVolumes()
        {
            return mVolumes;
        }

        public int hashCode()
        {
            int i;
            if(mType == 0)
                i = Objects.hash(new Object[] {
                    Integer.valueOf(mType), Integer.valueOf(mId)
                });
            else
                i = Objects.hash(new Object[] {
                    Integer.valueOf(mType), Integer.valueOf(mId), Integer.valueOf(mOptionFlags), Double.valueOf(mDurationMs), Integer.valueOf(mInterpolatorType), Integer.valueOf(Arrays.hashCode(mTimes)), Integer.valueOf(Arrays.hashCode(mVolumes))
                });
            return i;
        }

        public String toString()
        {
            StringBuilder stringbuilder = (new StringBuilder()).append("VolumeShaper.Configuration{mType = ").append(mType).append(", mId = ").append(mId);
            String s;
            if(mType == 0)
                s = "}";
            else
                s = (new StringBuilder()).append(", mOptionFlags = 0x").append(Integer.toHexString(mOptionFlags).toUpperCase()).append(", mDurationMs = ").append(mDurationMs).append(", mInterpolatorType = ").append(mInterpolatorType).append(", mTimes[] = ").append(Arrays.toString(mTimes)).append(", mVolumes[] = ").append(Arrays.toString(mVolumes)).append("}").toString();
            return stringbuilder.append(s).toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(mType);
            parcel.writeInt(mId);
            if(mType != 0)
            {
                parcel.writeInt(mOptionFlags);
                parcel.writeDouble(mDurationMs);
                parcel.writeInt(mInterpolatorType);
                parcel.writeFloat(0.0F);
                parcel.writeFloat(0.0F);
                parcel.writeInt(mTimes.length);
                for(i = 0; i < mTimes.length; i++)
                {
                    parcel.writeFloat(mTimes[i]);
                    parcel.writeFloat(mVolumes[i]);
                }

            }
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public Configuration createFromParcel(Parcel parcel)
            {
                int i = parcel.readInt();
                int j = parcel.readInt();
                if(i == 0)
                    return new Configuration(j);
                int k = parcel.readInt();
                double d = parcel.readDouble();
                int l = parcel.readInt();
                parcel.readFloat();
                parcel.readFloat();
                int i1 = parcel.readInt();
                float af[] = new float[i1];
                float af1[] = new float[i1];
                for(int j1 = 0; j1 < i1; j1++)
                {
                    af[j1] = parcel.readFloat();
                    af1[j1] = parcel.readFloat();
                }

                return new Configuration(i, j, k, d, l, af, af1, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public Configuration[] newArray(int i)
            {
                return new Configuration[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public static final Configuration CUBIC_RAMP = (new Builder()).setInterpolatorType(2).setCurve(new float[] {
            0.0F, 1.0F
        }, new float[] {
            0.0F, 1.0F
        }).setDuration(1000L).build();
        public static final int INTERPOLATOR_TYPE_CUBIC = 2;
        public static final int INTERPOLATOR_TYPE_CUBIC_MONOTONIC = 3;
        public static final int INTERPOLATOR_TYPE_LINEAR = 1;
        public static final int INTERPOLATOR_TYPE_STEP = 0;
        public static final Configuration LINEAR_RAMP = (new Builder()).setInterpolatorType(1).setCurve(new float[] {
            0.0F, 1.0F
        }, new float[] {
            0.0F, 1.0F
        }).setDuration(1000L).build();
        private static final int MAXIMUM_CURVE_POINTS = 16;
        public static final int OPTION_FLAG_CLOCK_TIME = 2;
        private static final int OPTION_FLAG_PUBLIC_ALL = 3;
        public static final int OPTION_FLAG_VOLUME_IN_DBFS = 1;
        public static final Configuration SCURVE_RAMP;
        public static final Configuration SINE_RAMP;
        static final int TYPE_ID = 0;
        static final int TYPE_SCALE = 1;
        private final double mDurationMs;
        private final int mId;
        private final int mInterpolatorType;
        private final int mOptionFlags;
        private final float mTimes[];
        private final int mType;
        private final float mVolumes[];

        static 
        {
            float af[] = new float[16];
            float af1[] = new float[16];
            float af2[] = new float[16];
            for(int i = 0; i < 16; i++)
            {
                af[i] = (float)i / 15F;
                float f = (float)Math.sin(((double)af[i] * 3.1415926535897931D) / 2D);
                af1[i] = f;
                af2[i] = f * f;
            }

            SINE_RAMP = (new Builder()).setInterpolatorType(2).setCurve(af, af1).setDuration(1000L).build();
            SCURVE_RAMP = (new Builder()).setInterpolatorType(2).setCurve(af, af2).setDuration(1000L).build();
        }

        public Configuration(int i)
        {
            if(i < 0)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("negative id ").append(i).toString());
            } else
            {
                mType = 0;
                mId = i;
                mInterpolatorType = 0;
                mOptionFlags = 0;
                mDurationMs = 0.0D;
                mTimes = null;
                mVolumes = null;
                return;
            }
        }

        private Configuration(int i, int j, int k, double d, int l, float af[], 
                float af1[])
        {
            mType = i;
            mId = j;
            mOptionFlags = k;
            mDurationMs = d;
            mInterpolatorType = l;
            mTimes = af;
            mVolumes = af1;
        }

        Configuration(int i, int j, int k, double d, int l, float af[], 
                float af1[], Configuration configuration)
        {
            this(i, j, k, d, l, af, af1);
        }
    }

    public static final class Configuration.Builder
    {

        public Configuration build()
        {
            boolean flag;
            if((mOptionFlags & 1) != 0)
                flag = true;
            else
                flag = false;
            Configuration._2D_wrap0(mTimes, mVolumes, flag, true);
            return new Configuration(mType, mId, mOptionFlags, mDurationMs, mInterpolatorType, mTimes, mVolumes, null);
        }

        public Configuration.Builder invertVolumes()
        {
            boolean flag;
            float f;
            float f1;
            int i;
            if((mOptionFlags & 1) != 0)
                flag = true;
            else
                flag = false;
            Configuration._2D_wrap0(mTimes, mVolumes, flag, true);
            f = mVolumes[0];
            f1 = mVolumes[0];
            i = 1;
            while(i < mVolumes.length) 
            {
                float f2;
                float f3;
                if(mVolumes[i] < f)
                {
                    f2 = mVolumes[i];
                    f3 = f1;
                } else
                {
                    f3 = f1;
                    f2 = f;
                    if(mVolumes[i] > f1)
                    {
                        f3 = mVolumes[i];
                        f2 = f;
                    }
                }
                i++;
                f1 = f3;
                f = f2;
            }
            for(int j = 0; j < mVolumes.length; j++)
                mVolumes[j] = (f1 + f) - mVolumes[j];

            return this;
        }

        public Configuration.Builder reflectTimes()
        {
            boolean flag;
            int i;
            if((mOptionFlags & 1) != 0)
                flag = true;
            else
                flag = false;
            Configuration._2D_wrap0(mTimes, mVolumes, flag, true);
            for(i = 0; i < mTimes.length / 2; i++)
            {
                float f = mTimes[i];
                mTimes[i] = 1.0F - mTimes[mTimes.length - 1 - i];
                mTimes[mTimes.length - 1 - i] = 1.0F - f;
                f = mVolumes[i];
                mVolumes[i] = mVolumes[mVolumes.length - 1 - i];
                mVolumes[mVolumes.length - 1 - i] = f;
            }

            if((mTimes.length & 1) != 0)
                mTimes[i] = 1.0F - mTimes[i];
            return this;
        }

        public Configuration.Builder scaleToEndVolume(float f)
        {
            boolean flag;
            float f1;
            float f2;
            if((mOptionFlags & 1) != 0)
                flag = true;
            else
                flag = false;
            Configuration._2D_wrap0(mTimes, mVolumes, flag, true);
            Configuration._2D_wrap1(f, flag);
            f1 = mVolumes[0];
            f2 = mVolumes[mVolumes.length - 1];
            if(f2 == f1)
            {
                for(int i = 0; i < mVolumes.length; i++)
                    mVolumes[i] = mVolumes[i] + mTimes[i] * (f - f1);

            } else
            {
                f = (f - f1) / (f2 - f1);
                for(int j = 0; j < mVolumes.length; j++)
                    mVolumes[j] = (mVolumes[j] - f1) * f + f1;

            }
            Configuration._2D_wrap2(mVolumes, flag);
            return this;
        }

        public Configuration.Builder scaleToStartVolume(float f)
        {
            boolean flag;
            float f1;
            float f2;
            if((mOptionFlags & 1) != 0)
                flag = true;
            else
                flag = false;
            Configuration._2D_wrap0(mTimes, mVolumes, flag, true);
            Configuration._2D_wrap1(f, flag);
            f1 = mVolumes[0];
            f2 = mVolumes[mVolumes.length - 1];
            if(f2 == f1)
            {
                for(int i = 0; i < mVolumes.length; i++)
                    mVolumes[i] = mVolumes[i] + (1.0F - mTimes[i]) * (f - f1);

            } else
            {
                f = (f - f2) / (f1 - f2);
                for(int j = 0; j < mVolumes.length; j++)
                    mVolumes[j] = (mVolumes[j] - f2) * f + f2;

            }
            Configuration._2D_wrap2(mVolumes, flag);
            return this;
        }

        public Configuration.Builder setCurve(float af[], float af1[])
        {
            boolean flag;
            if((mOptionFlags & 1) != 0)
                flag = true;
            else
                flag = false;
            Configuration._2D_wrap0(af, af1, flag, false);
            mTimes = (float[])af.clone();
            mVolumes = (float[])af1.clone();
            return this;
        }

        public Configuration.Builder setDuration(long l)
        {
            if(l <= 0L)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("duration: ").append(l).append(" not positive").toString());
            } else
            {
                mDurationMs = l;
                return this;
            }
        }

        public Configuration.Builder setId(int i)
        {
            if(i < -1)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("invalid id: ").append(i).toString());
            } else
            {
                mId = i;
                return this;
            }
        }

        public Configuration.Builder setInterpolatorType(int i)
        {
            switch(i)
            {
            default:
                throw new IllegalArgumentException((new StringBuilder()).append("invalid interpolatorType: ").append(i).toString());

            case 0: // '\0'
            case 1: // '\001'
            case 2: // '\002'
            case 3: // '\003'
                mInterpolatorType = i;
                break;
            }
            return this;
        }

        public Configuration.Builder setOptionFlags(int i)
        {
            if((i & -4) != 0)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("invalid bits in flag: ").append(i).toString());
            } else
            {
                mOptionFlags = mOptionFlags & -4 | i;
                return this;
            }
        }

        private double mDurationMs;
        private int mId;
        private int mInterpolatorType;
        private int mOptionFlags;
        private float mTimes[];
        private int mType;
        private float mVolumes[];

        public Configuration.Builder()
        {
            mType = 1;
            mId = -1;
            mInterpolatorType = 2;
            mOptionFlags = 2;
            mDurationMs = 1000D;
            mTimes = null;
            mVolumes = null;
        }

        public Configuration.Builder(Configuration configuration)
        {
            mType = 1;
            mId = -1;
            mInterpolatorType = 2;
            mOptionFlags = 2;
            mDurationMs = 1000D;
            mTimes = null;
            mVolumes = null;
            mType = configuration.getType();
            mId = configuration.getId();
            mOptionFlags = configuration.getAllOptionFlags();
            mInterpolatorType = configuration.getInterpolatorType();
            mDurationMs = configuration.getDuration();
            mTimes = (float[])configuration.getTimes().clone();
            mVolumes = (float[])configuration.getVolumes().clone();
        }
    }

    public static final class Operation
        implements Parcelable
    {

        static int _2D_get0(Operation operation)
        {
            return operation.mFlags;
        }

        static int _2D_get1(Operation operation)
        {
            return operation.mReplaceId;
        }

        static float _2D_get2(Operation operation)
        {
            return operation.mXOffset;
        }

        public int describeContents()
        {
            return 0;
        }

        public boolean equals(Object obj)
        {
            boolean flag = true;
            if(!(obj instanceof Operation))
                return false;
            if(obj == this)
                return true;
            obj = (Operation)obj;
            if(mFlags == ((Operation) (obj)).mFlags && mReplaceId == ((Operation) (obj)).mReplaceId)
            {
                if(Float.compare(mXOffset, ((Operation) (obj)).mXOffset) != 0)
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
                Integer.valueOf(mFlags), Integer.valueOf(mReplaceId), Float.valueOf(mXOffset)
            });
        }

        public String toString()
        {
            return (new StringBuilder()).append("VolumeShaper.Operation{mFlags = 0x").append(Integer.toHexString(mFlags).toUpperCase()).append(", mReplaceId = ").append(mReplaceId).append(", mXOffset = ").append(mXOffset).append("}").toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(mFlags);
            parcel.writeInt(mReplaceId);
            parcel.writeFloat(mXOffset);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public Operation createFromParcel(Parcel parcel)
            {
                return new Operation(parcel.readInt(), parcel.readInt(), parcel.readFloat(), null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public Operation[] newArray(int i)
            {
                return new Operation[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private static final int FLAG_CREATE_IF_NEEDED = 16;
        private static final int FLAG_DEFER = 8;
        private static final int FLAG_JOIN = 4;
        private static final int FLAG_NONE = 0;
        private static final int FLAG_PUBLIC_ALL = 3;
        private static final int FLAG_REVERSE = 1;
        private static final int FLAG_TERMINATE = 2;
        public static final Operation PLAY = (new Builder()).build();
        public static final Operation REVERSE = (new Builder()).reverse().build();
        private final int mFlags;
        private final int mReplaceId;
        private final float mXOffset;


        private Operation(int i, int j, float f)
        {
            mFlags = i;
            mReplaceId = j;
            mXOffset = f;
        }

        Operation(int i, int j, float f, Operation operation)
        {
            this(i, j, f);
        }
    }

    public static final class Operation.Builder
    {

        private Operation.Builder setFlags(int i)
        {
            if((i & -4) != 0)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("flag has unknown bits set: ").append(i).toString());
            } else
            {
                mFlags = mFlags & -4 | i;
                return this;
            }
        }

        public Operation build()
        {
            return new Operation(mFlags, mReplaceId, mXOffset, null);
        }

        public Operation.Builder createIfNeeded()
        {
            mFlags = mFlags | 0x10;
            return this;
        }

        public Operation.Builder defer()
        {
            mFlags = mFlags | 8;
            return this;
        }

        public Operation.Builder replace(int i, boolean flag)
        {
            mReplaceId = i;
            if(flag)
                mFlags = mFlags | 4;
            else
                mFlags = mFlags & -5;
            return this;
        }

        public Operation.Builder reverse()
        {
            mFlags = mFlags ^ 1;
            return this;
        }

        public Operation.Builder setXOffset(float f)
        {
            if(f < 0.0F)
                throw new IllegalArgumentException("Negative xOffset not allowed");
            if(f > 1.0F)
            {
                throw new IllegalArgumentException("xOffset > 1.f not allowed");
            } else
            {
                mXOffset = f;
                return this;
            }
        }

        public Operation.Builder terminate()
        {
            mFlags = mFlags | 2;
            return this;
        }

        int mFlags;
        int mReplaceId;
        float mXOffset;

        public Operation.Builder()
        {
            mFlags = 0;
            mReplaceId = -1;
            mXOffset = (0.0F / 0.0F);
        }

        public Operation.Builder(Operation operation)
        {
            mReplaceId = Operation._2D_get1(operation);
            mFlags = Operation._2D_get0(operation);
            mXOffset = Operation._2D_get2(operation);
        }
    }

    public static final class State
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public boolean equals(Object obj)
        {
            boolean flag = true;
            if(!(obj instanceof State))
                return false;
            if(obj == this)
                return true;
            obj = (State)obj;
            if(mVolume == ((State) (obj)).mVolume)
            {
                if(mXOffset != ((State) (obj)).mXOffset)
                    flag = false;
            } else
            {
                flag = false;
            }
            return flag;
        }

        public float getVolume()
        {
            return mVolume;
        }

        public float getXOffset()
        {
            return mXOffset;
        }

        public int hashCode()
        {
            return Objects.hash(new Object[] {
                Float.valueOf(mVolume), Float.valueOf(mXOffset)
            });
        }

        public String toString()
        {
            return (new StringBuilder()).append("VolumeShaper.State{mVolume = ").append(mVolume).append(", mXOffset = ").append(mXOffset).append("}").toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeFloat(mVolume);
            parcel.writeFloat(mXOffset);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public State createFromParcel(Parcel parcel)
            {
                return new State(parcel.readFloat(), parcel.readFloat());
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public State[] newArray(int i)
            {
                return new State[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private float mVolume;
        private float mXOffset;


        State(float f, float f1)
        {
            mVolume = f;
            mXOffset = f1;
        }
    }


    VolumeShaper(Configuration configuration, PlayerBase playerbase)
    {
        mWeakPlayerBase = new WeakReference(playerbase);
        mId = applyPlayer(configuration, (new Operation.Builder()).defer().build());
    }

    private int applyPlayer(Configuration configuration, Operation operation)
    {
        if(mWeakPlayerBase != null)
        {
            PlayerBase playerbase = (PlayerBase)mWeakPlayerBase.get();
            if(playerbase == null)
                throw new IllegalStateException("player deallocated");
            int i = playerbase.playerApplyVolumeShaper(configuration, operation);
            if(i < 0)
            {
                if(i == -38)
                    throw new IllegalStateException("player or VolumeShaper deallocated");
                else
                    throw new IllegalArgumentException((new StringBuilder()).append("invalid configuration or operation: ").append(i).toString());
            } else
            {
                return i;
            }
        } else
        {
            throw new IllegalStateException("uninitialized shaper");
        }
    }

    private State getStatePlayer(int i)
    {
        if(mWeakPlayerBase != null)
        {
            Object obj = (PlayerBase)mWeakPlayerBase.get();
            if(obj == null)
                throw new IllegalStateException("player deallocated");
            obj = ((PlayerBase) (obj)).playerGetVolumeShaperState(i);
            if(obj == null)
                throw new IllegalStateException("shaper cannot be found");
            else
                return ((State) (obj));
        } else
        {
            throw new IllegalStateException("uninitialized shaper");
        }
    }

    public void apply(Operation operation)
    {
        applyPlayer(new Configuration(mId), operation);
    }

    public void close()
    {
        try
        {
            Configuration configuration = JVM INSTR new #8   <Class VolumeShaper$Configuration>;
            configuration.Configuration(mId);
            Operation.Builder builder = JVM INSTR new #21  <Class VolumeShaper$Operation$Builder>;
            builder.Operation.Builder();
            applyPlayer(configuration, builder.terminate().build());
        }
        catch(IllegalStateException illegalstateexception) { }
        if(mWeakPlayerBase != null)
            mWeakPlayerBase.clear();
    }

    protected void finalize()
    {
        close();
    }

    int getId()
    {
        return mId;
    }

    public float getVolume()
    {
        return getStatePlayer(mId).getVolume();
    }

    public void replace(Configuration configuration, Operation operation, boolean flag)
    {
        mId = applyPlayer(configuration, (new Operation.Builder(operation)).replace(mId, flag).build());
    }

    private int mId;
    private final WeakReference mWeakPlayerBase;
}
