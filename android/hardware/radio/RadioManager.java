// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.radio;

import android.content.Context;
import android.os.*;
import android.text.TextUtils;
import android.util.Log;
import java.util.*;
import java.util.stream.*;

// Referenced classes of package android.hardware.radio:
//            IRadioService, TunerCallbackAdapter, TunerAdapter, RadioTuner, 
//            ProgramSelector, RadioMetadata

public class RadioManager
{
    public static class AmBandConfig extends BandConfig
    {

        public int describeContents()
        {
            return 0;
        }

        public boolean equals(Object obj)
        {
            if(this == obj)
                return true;
            if(!super.equals(obj))
                return false;
            if(!(obj instanceof AmBandConfig))
                return false;
            obj = (AmBandConfig)obj;
            return mStereo == ((AmBandConfig) (obj)).getStereo();
        }

        public boolean getStereo()
        {
            return mStereo;
        }

        public int hashCode()
        {
            int i = super.hashCode();
            int j;
            if(mStereo)
                j = 1;
            else
                j = 0;
            return i * 31 + j;
        }

        public String toString()
        {
            return (new StringBuilder()).append("AmBandConfig [").append(super.toString()).append(", mStereo=").append(mStereo).append("]").toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            if(mStereo)
                i = 1;
            else
                i = 0;
            parcel.writeByte((byte)i);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public AmBandConfig createFromParcel(Parcel parcel)
            {
                return new AmBandConfig(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public AmBandConfig[] newArray(int i)
            {
                return new AmBandConfig[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private final boolean mStereo;


        AmBandConfig(int i, int j, int k, int l, int i1, boolean flag)
        {
            super(i, j, k, l, i1);
            mStereo = flag;
        }

        AmBandConfig(AmBandDescriptor ambanddescriptor)
        {
            super(ambanddescriptor);
            mStereo = ambanddescriptor.isStereoSupported();
        }

        private AmBandConfig(Parcel parcel)
        {
            boolean flag = true;
            super(parcel, null);
            if(parcel.readByte() != 1)
                flag = false;
            mStereo = flag;
        }

        AmBandConfig(Parcel parcel, AmBandConfig ambandconfig)
        {
            this(parcel);
        }
    }

    public static class AmBandConfig.Builder
    {

        public AmBandConfig build()
        {
            return new AmBandConfig(mDescriptor.getRegion(), mDescriptor.getType(), mDescriptor.getLowerLimit(), mDescriptor.getUpperLimit(), mDescriptor.getSpacing(), mStereo);
        }

        public AmBandConfig.Builder setStereo(boolean flag)
        {
            mStereo = flag;
            return this;
        }

        private final BandDescriptor mDescriptor;
        private boolean mStereo;

        public AmBandConfig.Builder(AmBandConfig ambandconfig)
        {
            mDescriptor = new BandDescriptor(ambandconfig.getRegion(), ambandconfig.getType(), ambandconfig.getLowerLimit(), ambandconfig.getUpperLimit(), ambandconfig.getSpacing());
            mStereo = ambandconfig.getStereo();
        }

        public AmBandConfig.Builder(AmBandDescriptor ambanddescriptor)
        {
            mDescriptor = new BandDescriptor(ambanddescriptor.getRegion(), ambanddescriptor.getType(), ambanddescriptor.getLowerLimit(), ambanddescriptor.getUpperLimit(), ambanddescriptor.getSpacing());
            mStereo = ambanddescriptor.isStereoSupported();
        }
    }

    public static class AmBandDescriptor extends BandDescriptor
    {

        public int describeContents()
        {
            return 0;
        }

        public boolean equals(Object obj)
        {
            if(this == obj)
                return true;
            if(!super.equals(obj))
                return false;
            if(!(obj instanceof AmBandDescriptor))
                return false;
            obj = (AmBandDescriptor)obj;
            return mStereo == ((AmBandDescriptor) (obj)).isStereoSupported();
        }

        public int hashCode()
        {
            int i = super.hashCode();
            int j;
            if(mStereo)
                j = 1;
            else
                j = 0;
            return i * 31 + j;
        }

        public boolean isStereoSupported()
        {
            return mStereo;
        }

        public String toString()
        {
            return (new StringBuilder()).append("AmBandDescriptor [ ").append(super.toString()).append(" mStereo=").append(mStereo).append("]").toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            if(mStereo)
                i = 1;
            else
                i = 0;
            parcel.writeByte((byte)i);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public AmBandDescriptor createFromParcel(Parcel parcel)
            {
                return new AmBandDescriptor(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public AmBandDescriptor[] newArray(int i)
            {
                return new AmBandDescriptor[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private final boolean mStereo;


        AmBandDescriptor(int i, int j, int k, int l, int i1, boolean flag)
        {
            super(i, j, k, l, i1);
            mStereo = flag;
        }

        private AmBandDescriptor(Parcel parcel)
        {
            boolean flag = true;
            super(parcel, null);
            if(parcel.readByte() != 1)
                flag = false;
            mStereo = flag;
        }

        AmBandDescriptor(Parcel parcel, AmBandDescriptor ambanddescriptor)
        {
            this(parcel);
        }
    }

    public static class BandConfig
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public boolean equals(Object obj)
        {
            if(this == obj)
                return true;
            if(!(obj instanceof BandConfig))
                return false;
            obj = ((BandConfig)obj).getDescriptor();
            boolean flag;
            boolean flag1;
            if(mDescriptor == null)
                flag = true;
            else
                flag = false;
            if(obj == null)
                flag1 = true;
            else
                flag1 = false;
            if(flag != flag1)
                return false;
            return mDescriptor == null || !(mDescriptor.equals(obj) ^ true);
        }

        BandDescriptor getDescriptor()
        {
            return mDescriptor;
        }

        public int getLowerLimit()
        {
            return mDescriptor.getLowerLimit();
        }

        public int getRegion()
        {
            return mDescriptor.getRegion();
        }

        public int getSpacing()
        {
            return mDescriptor.getSpacing();
        }

        public int getType()
        {
            return mDescriptor.getType();
        }

        public int getUpperLimit()
        {
            return mDescriptor.getUpperLimit();
        }

        public int hashCode()
        {
            return mDescriptor.hashCode() + 31;
        }

        public String toString()
        {
            return (new StringBuilder()).append("BandConfig [ ").append(mDescriptor.toString()).append("]").toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            mDescriptor.writeToParcel(parcel, i);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public BandConfig createFromParcel(Parcel parcel)
            {
                int i = BandDescriptor._2D_wrap0(parcel);
                switch(i)
                {
                default:
                    throw new IllegalArgumentException((new StringBuilder()).append("Unsupported band: ").append(i).toString());

                case 1: // '\001'
                case 2: // '\002'
                    return new FmBandConfig(parcel, null);

                case 0: // '\0'
                case 3: // '\003'
                    return new AmBandConfig(parcel, null);
                }
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public BandConfig[] newArray(int i)
            {
                return new BandConfig[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        final BandDescriptor mDescriptor;


        BandConfig(int i, int j, int k, int l, int i1)
        {
            mDescriptor = new BandDescriptor(i, j, k, l, i1);
        }

        BandConfig(BandDescriptor banddescriptor)
        {
            mDescriptor = banddescriptor;
        }

        private BandConfig(Parcel parcel)
        {
            mDescriptor = new BandDescriptor(parcel, null);
        }

        BandConfig(Parcel parcel, BandConfig bandconfig)
        {
            this(parcel);
        }
    }

    public static class BandDescriptor
        implements Parcelable
    {

        static int _2D_wrap0(Parcel parcel)
        {
            return lookupTypeFromParcel(parcel);
        }

        private static int lookupTypeFromParcel(Parcel parcel)
        {
            int i = parcel.dataPosition();
            parcel.readInt();
            int j = parcel.readInt();
            parcel.setDataPosition(i);
            return j;
        }

        public int describeContents()
        {
            return 0;
        }

        public boolean equals(Object obj)
        {
            if(this == obj)
                return true;
            if(!(obj instanceof BandDescriptor))
                return false;
            obj = (BandDescriptor)obj;
            if(mRegion != ((BandDescriptor) (obj)).getRegion())
                return false;
            if(mType != ((BandDescriptor) (obj)).getType())
                return false;
            if(mLowerLimit != ((BandDescriptor) (obj)).getLowerLimit())
                return false;
            if(mUpperLimit != ((BandDescriptor) (obj)).getUpperLimit())
                return false;
            return mSpacing == ((BandDescriptor) (obj)).getSpacing();
        }

        public int getLowerLimit()
        {
            return mLowerLimit;
        }

        public int getRegion()
        {
            return mRegion;
        }

        public int getSpacing()
        {
            return mSpacing;
        }

        public int getType()
        {
            return mType;
        }

        public int getUpperLimit()
        {
            return mUpperLimit;
        }

        public int hashCode()
        {
            return ((((mRegion + 31) * 31 + mType) * 31 + mLowerLimit) * 31 + mUpperLimit) * 31 + mSpacing;
        }

        public boolean isAmBand()
        {
            boolean flag = true;
            boolean flag1 = flag;
            if(mType != 0)
                if(mType == 3)
                    flag1 = flag;
                else
                    flag1 = false;
            return flag1;
        }

        public boolean isFmBand()
        {
            boolean flag = true;
            boolean flag1 = flag;
            if(mType != 1)
                if(mType == 2)
                    flag1 = flag;
                else
                    flag1 = false;
            return flag1;
        }

        public String toString()
        {
            return (new StringBuilder()).append("BandDescriptor [mRegion=").append(mRegion).append(", mType=").append(mType).append(", mLowerLimit=").append(mLowerLimit).append(", mUpperLimit=").append(mUpperLimit).append(", mSpacing=").append(mSpacing).append("]").toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(mRegion);
            parcel.writeInt(mType);
            parcel.writeInt(mLowerLimit);
            parcel.writeInt(mUpperLimit);
            parcel.writeInt(mSpacing);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public BandDescriptor createFromParcel(Parcel parcel)
            {
                int i = BandDescriptor._2D_wrap0(parcel);
                switch(i)
                {
                default:
                    throw new IllegalArgumentException((new StringBuilder()).append("Unsupported band: ").append(i).toString());

                case 1: // '\001'
                case 2: // '\002'
                    return new FmBandDescriptor(parcel, null);

                case 0: // '\0'
                case 3: // '\003'
                    return new AmBandDescriptor(parcel, null);
                }
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public BandDescriptor[] newArray(int i)
            {
                return new BandDescriptor[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private final int mLowerLimit;
        private final int mRegion;
        private final int mSpacing;
        private final int mType;
        private final int mUpperLimit;


        BandDescriptor(int i, int j, int k, int l, int i1)
        {
            if(j != 0 && j != 1 && j != 2 && j != 3)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Unsupported band: ").append(j).toString());
            } else
            {
                mRegion = i;
                mType = j;
                mLowerLimit = k;
                mUpperLimit = l;
                mSpacing = i1;
                return;
            }
        }

        private BandDescriptor(Parcel parcel)
        {
            mRegion = parcel.readInt();
            mType = parcel.readInt();
            mLowerLimit = parcel.readInt();
            mUpperLimit = parcel.readInt();
            mSpacing = parcel.readInt();
        }

        BandDescriptor(Parcel parcel, BandDescriptor banddescriptor)
        {
            this(parcel);
        }
    }

    public static class FmBandConfig extends BandConfig
    {

        public int describeContents()
        {
            return 0;
        }

        public boolean equals(Object obj)
        {
            if(this == obj)
                return true;
            if(!super.equals(obj))
                return false;
            if(!(obj instanceof FmBandConfig))
                return false;
            obj = (FmBandConfig)obj;
            if(mStereo != ((FmBandConfig) (obj)).mStereo)
                return false;
            if(mRds != ((FmBandConfig) (obj)).mRds)
                return false;
            if(mTa != ((FmBandConfig) (obj)).mTa)
                return false;
            if(mAf != ((FmBandConfig) (obj)).mAf)
                return false;
            return mEa == ((FmBandConfig) (obj)).mEa;
        }

        public boolean getAf()
        {
            return mAf;
        }

        public boolean getEa()
        {
            return mEa;
        }

        public boolean getRds()
        {
            return mRds;
        }

        public boolean getStereo()
        {
            return mStereo;
        }

        public boolean getTa()
        {
            return mTa;
        }

        public int hashCode()
        {
            int i = 1;
            int j = super.hashCode();
            int k;
            int l;
            int i1;
            int j1;
            if(mStereo)
                k = 1;
            else
                k = 0;
            if(mRds)
                l = 1;
            else
                l = 0;
            if(mTa)
                i1 = 1;
            else
                i1 = 0;
            if(mAf)
                j1 = 1;
            else
                j1 = 0;
            if(!mEa)
                i = 0;
            return ((((j * 31 + k) * 31 + l) * 31 + i1) * 31 + j1) * 31 + i;
        }

        public String toString()
        {
            return (new StringBuilder()).append("FmBandConfig [").append(super.toString()).append(", mStereo=").append(mStereo).append(", mRds=").append(mRds).append(", mTa=").append(mTa).append(", mAf=").append(mAf).append(", mEa =").append(mEa).append("]").toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            boolean flag = true;
            super.writeToParcel(parcel, i);
            if(mStereo)
                i = 1;
            else
                i = 0;
            parcel.writeByte((byte)i);
            if(mRds)
                i = 1;
            else
                i = 0;
            parcel.writeByte((byte)i);
            if(mTa)
                i = 1;
            else
                i = 0;
            parcel.writeByte((byte)i);
            if(mAf)
                i = 1;
            else
                i = 0;
            parcel.writeByte((byte)i);
            if(mEa)
                i = ((flag) ? 1 : 0);
            else
                i = 0;
            parcel.writeByte((byte)i);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public FmBandConfig createFromParcel(Parcel parcel)
            {
                return new FmBandConfig(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public FmBandConfig[] newArray(int i)
            {
                return new FmBandConfig[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private final boolean mAf;
        private final boolean mEa;
        private final boolean mRds;
        private final boolean mStereo;
        private final boolean mTa;


        FmBandConfig(int i, int j, int k, int l, int i1, boolean flag, boolean flag1, 
                boolean flag2, boolean flag3, boolean flag4)
        {
            super(i, j, k, l, i1);
            mStereo = flag;
            mRds = flag1;
            mTa = flag2;
            mAf = flag3;
            mEa = flag4;
        }

        FmBandConfig(FmBandDescriptor fmbanddescriptor)
        {
            super(fmbanddescriptor);
            mStereo = fmbanddescriptor.isStereoSupported();
            mRds = fmbanddescriptor.isRdsSupported();
            mTa = fmbanddescriptor.isTaSupported();
            mAf = fmbanddescriptor.isAfSupported();
            mEa = fmbanddescriptor.isEaSupported();
        }

        private FmBandConfig(Parcel parcel)
        {
            boolean flag = true;
            super(parcel, null);
            boolean flag1;
            if(parcel.readByte() == 1)
                flag1 = true;
            else
                flag1 = false;
            mStereo = flag1;
            if(parcel.readByte() == 1)
                flag1 = true;
            else
                flag1 = false;
            mRds = flag1;
            if(parcel.readByte() == 1)
                flag1 = true;
            else
                flag1 = false;
            mTa = flag1;
            if(parcel.readByte() == 1)
                flag1 = true;
            else
                flag1 = false;
            mAf = flag1;
            if(parcel.readByte() == 1)
                flag1 = flag;
            else
                flag1 = false;
            mEa = flag1;
        }

        FmBandConfig(Parcel parcel, FmBandConfig fmbandconfig)
        {
            this(parcel);
        }
    }

    public static class FmBandConfig.Builder
    {

        public FmBandConfig build()
        {
            return new FmBandConfig(mDescriptor.getRegion(), mDescriptor.getType(), mDescriptor.getLowerLimit(), mDescriptor.getUpperLimit(), mDescriptor.getSpacing(), mStereo, mRds, mTa, mAf, mEa);
        }

        public FmBandConfig.Builder setAf(boolean flag)
        {
            mAf = flag;
            return this;
        }

        public FmBandConfig.Builder setEa(boolean flag)
        {
            mEa = flag;
            return this;
        }

        public FmBandConfig.Builder setRds(boolean flag)
        {
            mRds = flag;
            return this;
        }

        public FmBandConfig.Builder setStereo(boolean flag)
        {
            mStereo = flag;
            return this;
        }

        public FmBandConfig.Builder setTa(boolean flag)
        {
            mTa = flag;
            return this;
        }

        private boolean mAf;
        private final BandDescriptor mDescriptor;
        private boolean mEa;
        private boolean mRds;
        private boolean mStereo;
        private boolean mTa;

        public FmBandConfig.Builder(FmBandConfig fmbandconfig)
        {
            mDescriptor = new BandDescriptor(fmbandconfig.getRegion(), fmbandconfig.getType(), fmbandconfig.getLowerLimit(), fmbandconfig.getUpperLimit(), fmbandconfig.getSpacing());
            mStereo = fmbandconfig.getStereo();
            mRds = fmbandconfig.getRds();
            mTa = fmbandconfig.getTa();
            mAf = fmbandconfig.getAf();
            mEa = fmbandconfig.getEa();
        }

        public FmBandConfig.Builder(FmBandDescriptor fmbanddescriptor)
        {
            mDescriptor = new BandDescriptor(fmbanddescriptor.getRegion(), fmbanddescriptor.getType(), fmbanddescriptor.getLowerLimit(), fmbanddescriptor.getUpperLimit(), fmbanddescriptor.getSpacing());
            mStereo = fmbanddescriptor.isStereoSupported();
            mRds = fmbanddescriptor.isRdsSupported();
            mTa = fmbanddescriptor.isTaSupported();
            mAf = fmbanddescriptor.isAfSupported();
            mEa = fmbanddescriptor.isEaSupported();
        }
    }

    public static class FmBandDescriptor extends BandDescriptor
    {

        public int describeContents()
        {
            return 0;
        }

        public boolean equals(Object obj)
        {
            if(this == obj)
                return true;
            if(!super.equals(obj))
                return false;
            if(!(obj instanceof FmBandDescriptor))
                return false;
            obj = (FmBandDescriptor)obj;
            if(mStereo != ((FmBandDescriptor) (obj)).isStereoSupported())
                return false;
            if(mRds != ((FmBandDescriptor) (obj)).isRdsSupported())
                return false;
            if(mTa != ((FmBandDescriptor) (obj)).isTaSupported())
                return false;
            if(mAf != ((FmBandDescriptor) (obj)).isAfSupported())
                return false;
            return mEa == ((FmBandDescriptor) (obj)).isEaSupported();
        }

        public int hashCode()
        {
            int i = 1;
            int j = super.hashCode();
            int k;
            int l;
            int i1;
            int j1;
            if(mStereo)
                k = 1;
            else
                k = 0;
            if(mRds)
                l = 1;
            else
                l = 0;
            if(mTa)
                i1 = 1;
            else
                i1 = 0;
            if(mAf)
                j1 = 1;
            else
                j1 = 0;
            if(!mEa)
                i = 0;
            return ((((j * 31 + k) * 31 + l) * 31 + i1) * 31 + j1) * 31 + i;
        }

        public boolean isAfSupported()
        {
            return mAf;
        }

        public boolean isEaSupported()
        {
            return mEa;
        }

        public boolean isRdsSupported()
        {
            return mRds;
        }

        public boolean isStereoSupported()
        {
            return mStereo;
        }

        public boolean isTaSupported()
        {
            return mTa;
        }

        public String toString()
        {
            return (new StringBuilder()).append("FmBandDescriptor [ ").append(super.toString()).append(" mStereo=").append(mStereo).append(", mRds=").append(mRds).append(", mTa=").append(mTa).append(", mAf=").append(mAf).append(", mEa =").append(mEa).append("]").toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            boolean flag = true;
            super.writeToParcel(parcel, i);
            if(mStereo)
                i = 1;
            else
                i = 0;
            parcel.writeByte((byte)i);
            if(mRds)
                i = 1;
            else
                i = 0;
            parcel.writeByte((byte)i);
            if(mTa)
                i = 1;
            else
                i = 0;
            parcel.writeByte((byte)i);
            if(mAf)
                i = 1;
            else
                i = 0;
            parcel.writeByte((byte)i);
            if(mEa)
                i = ((flag) ? 1 : 0);
            else
                i = 0;
            parcel.writeByte((byte)i);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public FmBandDescriptor createFromParcel(Parcel parcel)
            {
                return new FmBandDescriptor(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public FmBandDescriptor[] newArray(int i)
            {
                return new FmBandDescriptor[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private final boolean mAf;
        private final boolean mEa;
        private final boolean mRds;
        private final boolean mStereo;
        private final boolean mTa;


        FmBandDescriptor(int i, int j, int k, int l, int i1, boolean flag, boolean flag1, 
                boolean flag2, boolean flag3, boolean flag4)
        {
            super(i, j, k, l, i1);
            mStereo = flag;
            mRds = flag1;
            mTa = flag2;
            mAf = flag3;
            mEa = flag4;
        }

        private FmBandDescriptor(Parcel parcel)
        {
            boolean flag = true;
            super(parcel, null);
            boolean flag1;
            if(parcel.readByte() == 1)
                flag1 = true;
            else
                flag1 = false;
            mStereo = flag1;
            if(parcel.readByte() == 1)
                flag1 = true;
            else
                flag1 = false;
            mRds = flag1;
            if(parcel.readByte() == 1)
                flag1 = true;
            else
                flag1 = false;
            mTa = flag1;
            if(parcel.readByte() == 1)
                flag1 = true;
            else
                flag1 = false;
            mAf = flag1;
            if(parcel.readByte() == 1)
                flag1 = flag;
            else
                flag1 = false;
            mEa = flag1;
        }

        FmBandDescriptor(Parcel parcel, FmBandDescriptor fmbanddescriptor)
        {
            this(parcel);
        }
    }

    public static class ModuleProperties
        implements Parcelable
    {

        static int _2D_android_hardware_radio_RadioManager$ModuleProperties_2D_mthref_2D_0(Integer integer)
        {
            return integer.intValue();
        }

        private static Set arrayToSet(int ai[])
        {
            return (Set)Arrays.stream(ai).boxed().collect(Collectors.toSet());
        }

        private static int[] setToArray(Set set)
        {
            return set.stream().mapToInt(_.Lambda.NfISO_O3QdEm5B1InYI8yDsxQBY.$INST$0).toArray();
        }

        public int describeContents()
        {
            return 0;
        }

        public boolean equals(Object obj)
        {
            if(this == obj)
                return true;
            if(!(obj instanceof ModuleProperties))
                return false;
            obj = (ModuleProperties)obj;
            if(mId != ((ModuleProperties) (obj)).getId())
                return false;
            if(!TextUtils.equals(mServiceName, ((ModuleProperties) (obj)).mServiceName))
                return false;
            if(mClassId != ((ModuleProperties) (obj)).getClassId())
                return false;
            if(mImplementor == null)
            {
                if(((ModuleProperties) (obj)).getImplementor() != null)
                    return false;
            } else
            if(!mImplementor.equals(((ModuleProperties) (obj)).getImplementor()))
                return false;
            if(mProduct == null)
            {
                if(((ModuleProperties) (obj)).getProduct() != null)
                    return false;
            } else
            if(!mProduct.equals(((ModuleProperties) (obj)).getProduct()))
                return false;
            if(mVersion == null)
            {
                if(((ModuleProperties) (obj)).getVersion() != null)
                    return false;
            } else
            if(!mVersion.equals(((ModuleProperties) (obj)).getVersion()))
                return false;
            if(mSerial == null)
            {
                if(((ModuleProperties) (obj)).getSerial() != null)
                    return false;
            } else
            if(!mSerial.equals(((ModuleProperties) (obj)).getSerial()))
                return false;
            if(mNumTuners != ((ModuleProperties) (obj)).getNumTuners())
                return false;
            if(mNumAudioSources != ((ModuleProperties) (obj)).getNumAudioSources())
                return false;
            if(mIsCaptureSupported != ((ModuleProperties) (obj)).isCaptureSupported())
                return false;
            if(!Arrays.equals(mBands, ((ModuleProperties) (obj)).getBands()))
                return false;
            if(mIsBgScanSupported != ((ModuleProperties) (obj)).isBackgroundScanningSupported())
                return false;
            return mVendorInfo.equals(((ModuleProperties) (obj)).mVendorInfo);
        }

        public BandDescriptor[] getBands()
        {
            return mBands;
        }

        public int getClassId()
        {
            return mClassId;
        }

        public int getId()
        {
            return mId;
        }

        public String getImplementor()
        {
            return mImplementor;
        }

        public int getNumAudioSources()
        {
            return mNumAudioSources;
        }

        public int getNumTuners()
        {
            return mNumTuners;
        }

        public String getProduct()
        {
            return mProduct;
        }

        public String getSerial()
        {
            return mSerial;
        }

        public String getServiceName()
        {
            return mServiceName;
        }

        public Map getVendorInfo()
        {
            return mVendorInfo;
        }

        public String getVersion()
        {
            return mVersion;
        }

        public int hashCode()
        {
            int i = 1;
            int j = mId;
            int k = mServiceName.hashCode();
            int l = mClassId;
            int i1;
            int j1;
            int k1;
            int l1;
            int i2;
            int j2;
            int k2;
            int l2;
            if(mImplementor == null)
                i1 = 0;
            else
                i1 = mImplementor.hashCode();
            if(mProduct == null)
                j1 = 0;
            else
                j1 = mProduct.hashCode();
            if(mVersion == null)
                k1 = 0;
            else
                k1 = mVersion.hashCode();
            if(mSerial == null)
                l1 = 0;
            else
                l1 = mSerial.hashCode();
            i2 = mNumTuners;
            j2 = mNumAudioSources;
            if(mIsCaptureSupported)
                k2 = 1;
            else
                k2 = 0;
            l2 = Arrays.hashCode(mBands);
            if(!mIsBgScanSupported)
                i = 0;
            return ((((((((((((j + 31) * 31 + k) * 31 + l) * 31 + i1) * 31 + j1) * 31 + k1) * 31 + l1) * 31 + i2) * 31 + j2) * 31 + k2) * 31 + l2) * 31 + i) * 31 + mVendorInfo.hashCode();
        }

        public boolean isBackgroundScanningSupported()
        {
            return mIsBgScanSupported;
        }

        public boolean isCaptureSupported()
        {
            return mIsCaptureSupported;
        }

        public boolean isProgramIdentifierSupported(int i)
        {
            return mSupportedIdentifierTypes.contains(Integer.valueOf(i));
        }

        public boolean isProgramTypeSupported(int i)
        {
            return mSupportedProgramTypes.contains(Integer.valueOf(i));
        }

        public String toString()
        {
            return (new StringBuilder()).append("ModuleProperties [mId=").append(mId).append(", mServiceName=").append(mServiceName).append(", mClassId=").append(mClassId).append(", mImplementor=").append(mImplementor).append(", mProduct=").append(mProduct).append(", mVersion=").append(mVersion).append(", mSerial=").append(mSerial).append(", mNumTuners=").append(mNumTuners).append(", mNumAudioSources=").append(mNumAudioSources).append(", mIsCaptureSupported=").append(mIsCaptureSupported).append(", mIsBgScanSupported=").append(mIsBgScanSupported).append(", mBands=").append(Arrays.toString(mBands)).append("]").toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            boolean flag = true;
            parcel.writeInt(mId);
            parcel.writeString(mServiceName);
            parcel.writeInt(mClassId);
            parcel.writeString(mImplementor);
            parcel.writeString(mProduct);
            parcel.writeString(mVersion);
            parcel.writeString(mSerial);
            parcel.writeInt(mNumTuners);
            parcel.writeInt(mNumAudioSources);
            int j;
            if(mIsCaptureSupported)
                j = 1;
            else
                j = 0;
            parcel.writeInt(j);
            parcel.writeParcelableArray(mBands, i);
            if(mIsBgScanSupported)
                i = ((flag) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeIntArray(setToArray(mSupportedProgramTypes));
            parcel.writeIntArray(setToArray(mSupportedIdentifierTypes));
            RadioManager._2D_wrap1(parcel, mVendorInfo);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public ModuleProperties createFromParcel(Parcel parcel)
            {
                return new ModuleProperties(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public ModuleProperties[] newArray(int i)
            {
                return new ModuleProperties[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private final BandDescriptor mBands[];
        private final int mClassId;
        private final int mId;
        private final String mImplementor;
        private final boolean mIsBgScanSupported;
        private final boolean mIsCaptureSupported;
        private final int mNumAudioSources;
        private final int mNumTuners;
        private final String mProduct;
        private final String mSerial;
        private final String mServiceName;
        private final Set mSupportedIdentifierTypes;
        private final Set mSupportedProgramTypes;
        private final Map mVendorInfo;
        private final String mVersion;


        ModuleProperties(int i, String s, int j, String s1, String s2, String s3, String s4, 
                int k, int l, boolean flag, BandDescriptor abanddescriptor[], boolean flag1, int ai[], int ai1[], 
                Map map)
        {
            mId = i;
            String s5 = s;
            if(TextUtils.isEmpty(s))
                s5 = "default";
            mServiceName = s5;
            mClassId = j;
            mImplementor = s1;
            mProduct = s2;
            mVersion = s3;
            mSerial = s4;
            mNumTuners = k;
            mNumAudioSources = l;
            mIsCaptureSupported = flag;
            mBands = abanddescriptor;
            mIsBgScanSupported = flag1;
            mSupportedProgramTypes = arrayToSet(ai);
            mSupportedIdentifierTypes = arrayToSet(ai1);
            s = map;
            if(map == null)
                s = new HashMap();
            mVendorInfo = s;
        }

        private ModuleProperties(Parcel parcel)
        {
            boolean flag = true;
            super();
            mId = parcel.readInt();
            String s = parcel.readString();
            String s1 = s;
            if(TextUtils.isEmpty(s))
                s1 = "default";
            mServiceName = s1;
            mClassId = parcel.readInt();
            mImplementor = parcel.readString();
            mProduct = parcel.readString();
            mVersion = parcel.readString();
            mSerial = parcel.readString();
            mNumTuners = parcel.readInt();
            mNumAudioSources = parcel.readInt();
            Parcelable aparcelable[];
            boolean flag1;
            if(parcel.readInt() == 1)
                flag1 = true;
            else
                flag1 = false;
            mIsCaptureSupported = flag1;
            aparcelable = parcel.readParcelableArray(android/hardware/radio/RadioManager$BandDescriptor.getClassLoader());
            mBands = new BandDescriptor[aparcelable.length];
            for(int i = 0; i < aparcelable.length; i++)
                mBands[i] = (BandDescriptor)aparcelable[i];

            if(parcel.readInt() == 1)
                flag1 = flag;
            else
                flag1 = false;
            mIsBgScanSupported = flag1;
            mSupportedProgramTypes = arrayToSet(parcel.createIntArray());
            mSupportedIdentifierTypes = arrayToSet(parcel.createIntArray());
            mVendorInfo = RadioManager._2D_wrap0(parcel);
        }

        ModuleProperties(Parcel parcel, ModuleProperties moduleproperties)
        {
            this(parcel);
        }
    }

    public static class ProgramInfo
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public boolean equals(Object obj)
        {
            if(this == obj)
                return true;
            if(!(obj instanceof ProgramInfo))
                return false;
            obj = (ProgramInfo)obj;
            if(!mSelector.equals(((ProgramInfo) (obj)).getSelector()))
                return false;
            if(mTuned != ((ProgramInfo) (obj)).isTuned())
                return false;
            if(mStereo != ((ProgramInfo) (obj)).isStereo())
                return false;
            if(mDigital != ((ProgramInfo) (obj)).isDigital())
                return false;
            if(mFlags != ((ProgramInfo) (obj)).mFlags)
                return false;
            if(mSignalStrength != ((ProgramInfo) (obj)).getSignalStrength())
                return false;
            if(mMetadata == null)
            {
                if(((ProgramInfo) (obj)).getMetadata() != null)
                    return false;
            } else
            if(!mMetadata.equals(((ProgramInfo) (obj)).getMetadata()))
                return false;
            return mVendorInfo.equals(((ProgramInfo) (obj)).mVendorInfo);
        }

        public int getChannel()
        {
            long l;
            try
            {
                l = mSelector.getFirstId(1);
            }
            catch(IllegalArgumentException illegalargumentexception)
            {
                Log.w("BroadcastRadio.manager", "Not an AM/FM program");
                return 0;
            }
            return (int)l;
        }

        public RadioMetadata getMetadata()
        {
            return mMetadata;
        }

        public ProgramSelector getSelector()
        {
            return mSelector;
        }

        public int getSignalStrength()
        {
            return mSignalStrength;
        }

        public int getSubChannel()
        {
            long l;
            try
            {
                l = mSelector.getFirstId(4);
            }
            catch(IllegalArgumentException illegalargumentexception)
            {
                return 0;
            }
            return (int)l + 1;
        }

        public Map getVendorInfo()
        {
            return mVendorInfo;
        }

        public int hashCode()
        {
            int i = 1;
            int j = 0;
            int k = mSelector.hashCode();
            int l;
            int i1;
            int j1;
            int k1;
            if(mTuned)
                l = 1;
            else
                l = 0;
            if(mStereo)
                i1 = 1;
            else
                i1 = 0;
            if(!mDigital)
                i = 0;
            j1 = mFlags;
            k1 = mSignalStrength;
            if(mMetadata != null)
                j = mMetadata.hashCode();
            return (((((((k + 31) * 31 + l) * 31 + i1) * 31 + i) * 31 + j1) * 31 + k1) * 31 + j) * 31 + mVendorInfo.hashCode();
        }

        public boolean isDigital()
        {
            return mDigital;
        }

        public boolean isLive()
        {
            boolean flag = false;
            if((mFlags & 1) != 0)
                flag = true;
            return flag;
        }

        public boolean isMuted()
        {
            boolean flag = false;
            if((mFlags & 2) != 0)
                flag = true;
            return flag;
        }

        public boolean isStereo()
        {
            return mStereo;
        }

        public boolean isTrafficAnnouncementActive()
        {
            boolean flag = false;
            if((mFlags & 8) != 0)
                flag = true;
            return flag;
        }

        public boolean isTrafficProgram()
        {
            boolean flag = false;
            if((mFlags & 4) != 0)
                flag = true;
            return flag;
        }

        public boolean isTuned()
        {
            return mTuned;
        }

        public String toString()
        {
            StringBuilder stringbuilder = (new StringBuilder()).append("ProgramInfo [mSelector=").append(mSelector).append(", mTuned=").append(mTuned).append(", mStereo=").append(mStereo).append(", mDigital=").append(mDigital).append(", mFlags=").append(mFlags).append(", mSignalStrength=").append(mSignalStrength);
            String s;
            if(mMetadata == null)
                s = "";
            else
                s = (new StringBuilder()).append(", mMetadata=").append(mMetadata.toString()).toString();
            return stringbuilder.append(s).append("]").toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeParcelable(mSelector, 0);
            int j;
            if(mTuned)
                j = 1;
            else
                j = 0;
            parcel.writeByte((byte)j);
            if(mStereo)
                j = 1;
            else
                j = 0;
            parcel.writeByte((byte)j);
            if(mDigital)
                j = 1;
            else
                j = 0;
            parcel.writeByte((byte)j);
            parcel.writeInt(mSignalStrength);
            if(mMetadata == null)
            {
                parcel.writeByte((byte)0);
            } else
            {
                parcel.writeByte((byte)1);
                mMetadata.writeToParcel(parcel, i);
            }
            parcel.writeInt(mFlags);
            RadioManager._2D_wrap1(parcel, mVendorInfo);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public ProgramInfo createFromParcel(Parcel parcel)
            {
                return new ProgramInfo(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public ProgramInfo[] newArray(int i)
            {
                return new ProgramInfo[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private static final int FLAG_LIVE = 1;
        private static final int FLAG_MUTED = 2;
        private static final int FLAG_TRAFFIC_ANNOUNCEMENT = 8;
        private static final int FLAG_TRAFFIC_PROGRAM = 4;
        private final boolean mDigital;
        private final int mFlags;
        private final RadioMetadata mMetadata;
        private final ProgramSelector mSelector;
        private final int mSignalStrength;
        private final boolean mStereo;
        private final boolean mTuned;
        private final Map mVendorInfo;


        ProgramInfo(ProgramSelector programselector, boolean flag, boolean flag1, boolean flag2, int i, RadioMetadata radiometadata, int j, 
                Map map)
        {
            mSelector = programselector;
            mTuned = flag;
            mStereo = flag1;
            mDigital = flag2;
            mFlags = j;
            mSignalStrength = i;
            mMetadata = radiometadata;
            programselector = map;
            if(map == null)
                programselector = new HashMap();
            mVendorInfo = programselector;
        }

        private ProgramInfo(Parcel parcel)
        {
            boolean flag = false;
            super();
            mSelector = (ProgramSelector)parcel.readParcelable(null);
            boolean flag1;
            if(parcel.readByte() == 1)
                flag1 = true;
            else
                flag1 = false;
            mTuned = flag1;
            if(parcel.readByte() == 1)
                flag1 = true;
            else
                flag1 = false;
            mStereo = flag1;
            flag1 = flag;
            if(parcel.readByte() == 1)
                flag1 = true;
            mDigital = flag1;
            mSignalStrength = parcel.readInt();
            if(parcel.readByte() == 1)
                mMetadata = (RadioMetadata)RadioMetadata.CREATOR.createFromParcel(parcel);
            else
                mMetadata = null;
            mFlags = parcel.readInt();
            mVendorInfo = RadioManager._2D_wrap0(parcel);
        }

        ProgramInfo(Parcel parcel, ProgramInfo programinfo)
        {
            this(parcel);
        }
    }


    static Map _2D_wrap0(Parcel parcel)
    {
        return readStringMap(parcel);
    }

    static void _2D_wrap1(Parcel parcel, Map map)
    {
        writeStringMap(parcel, map);
    }

    public RadioManager(Context context)
        throws android.os.ServiceManager.ServiceNotFoundException
    {
        mContext = context;
    }

    private native int nativeListModules(List list);

    private static Map readStringMap(Parcel parcel)
    {
        int i = parcel.readInt();
        HashMap hashmap = new HashMap();
        for(; i > 0; i--)
            hashmap.put(parcel.readString(), parcel.readString());

        return hashmap;
    }

    private static void writeStringMap(Parcel parcel, Map map)
    {
        parcel.writeInt(map.size());
        java.util.Map.Entry entry;
        for(map = map.entrySet().iterator(); map.hasNext(); parcel.writeString((String)entry.getValue()))
        {
            entry = (java.util.Map.Entry)map.next();
            parcel.writeString((String)entry.getKey());
        }

    }

    public int listModules(List list)
    {
        if(list == null)
        {
            Log.e("BroadcastRadio.manager", "the output list must not be empty");
            return -22;
        }
        Log.d("BroadcastRadio.manager", "Listing available tuners...");
        List list1;
        try
        {
            list1 = mService.listModules();
        }
        // Misplaced declaration of an exception variable
        catch(List list)
        {
            Log.e("BroadcastRadio.manager", "Failed listing available tuners", list);
            return -32;
        }
        if(list1 == null)
        {
            Log.e("BroadcastRadio.manager", "Returned list was a null");
            return 0x80000000;
        } else
        {
            list.addAll(list1);
            return 0;
        }
    }

    public RadioTuner openTuner(int i, BandConfig bandconfig, boolean flag, RadioTuner.Callback callback, Handler handler)
    {
        if(callback == null)
            throw new IllegalArgumentException("callback must not be empty");
        Log.d("BroadcastRadio.manager", (new StringBuilder()).append("Opening tuner ").append(i).append("...").toString());
        callback = new TunerCallbackAdapter(callback, handler);
        try
        {
            callback = mService.openTuner(i, bandconfig, flag, callback);
        }
        // Misplaced declaration of an exception variable
        catch(BandConfig bandconfig)
        {
            Log.e("BroadcastRadio.manager", "Failed to open tuner", bandconfig);
            return null;
        }
        if(callback == null)
        {
            Log.e("BroadcastRadio.manager", "Failed to open tuner");
            return null;
        }
        if(bandconfig != null)
            i = bandconfig.getType();
        else
            i = -1;
        return new TunerAdapter(callback, i);
    }

    public static final int BAND_AM = 0;
    public static final int BAND_AM_HD = 3;
    public static final int BAND_FM = 1;
    public static final int BAND_FM_HD = 2;
    public static final int BAND_INVALID = -1;
    public static final int CLASS_AM_FM = 0;
    public static final int CLASS_DT = 2;
    public static final int CLASS_SAT = 1;
    public static final int REGION_ITU_1 = 0;
    public static final int REGION_ITU_2 = 1;
    public static final int REGION_JAPAN = 3;
    public static final int REGION_KOREA = 4;
    public static final int REGION_OIRT = 2;
    public static final int STATUS_BAD_VALUE = -22;
    public static final int STATUS_DEAD_OBJECT = -32;
    public static final int STATUS_ERROR = 0x80000000;
    public static final int STATUS_INVALID_OPERATION = -38;
    public static final int STATUS_NO_INIT = -19;
    public static final int STATUS_OK = 0;
    public static final int STATUS_PERMISSION_DENIED = -1;
    public static final int STATUS_TIMED_OUT = -110;
    private static final String TAG = "BroadcastRadio.manager";
    private final Context mContext;
    private final IRadioService mService = IRadioService.Stub.asInterface(ServiceManager.getServiceOrThrow("broadcastradio"));
}
