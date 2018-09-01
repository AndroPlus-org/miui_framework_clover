// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;


// Referenced classes of package android.os:
//            Parcelable, Parcel

public class PowerSaveState
    implements Parcelable
{
    public static final class Builder
    {

        static boolean _2D_get0(Builder builder)
        {
            return builder.mBatterySaverEnabled;
        }

        static float _2D_get1(Builder builder)
        {
            return builder.mBrightnessFactor;
        }

        static boolean _2D_get2(Builder builder)
        {
            return builder.mGlobalBatterySaverEnabled;
        }

        static int _2D_get3(Builder builder)
        {
            return builder.mGpsMode;
        }

        public PowerSaveState build()
        {
            return new PowerSaveState(this);
        }

        public Builder setBatterySaverEnabled(boolean flag)
        {
            mBatterySaverEnabled = flag;
            return this;
        }

        public Builder setBrightnessFactor(float f)
        {
            mBrightnessFactor = f;
            return this;
        }

        public Builder setGlobalBatterySaverEnabled(boolean flag)
        {
            mGlobalBatterySaverEnabled = flag;
            return this;
        }

        public Builder setGpsMode(int i)
        {
            mGpsMode = i;
            return this;
        }

        private boolean mBatterySaverEnabled;
        private float mBrightnessFactor;
        private boolean mGlobalBatterySaverEnabled;
        private int mGpsMode;

        public Builder()
        {
            mBatterySaverEnabled = false;
            mGlobalBatterySaverEnabled = false;
            mGpsMode = 0;
            mBrightnessFactor = 0.5F;
        }
    }


    public PowerSaveState(Parcel parcel)
    {
        boolean flag = true;
        super();
        boolean flag1;
        if(parcel.readByte() != 0)
            flag1 = true;
        else
            flag1 = false;
        batterySaverEnabled = flag1;
        if(parcel.readByte() != 0)
            flag1 = flag;
        else
            flag1 = false;
        globalBatterySaverEnabled = flag1;
        gpsMode = parcel.readInt();
        brightnessFactor = parcel.readFloat();
    }

    public PowerSaveState(Builder builder)
    {
        batterySaverEnabled = Builder._2D_get0(builder);
        gpsMode = Builder._2D_get3(builder);
        brightnessFactor = Builder._2D_get1(builder);
        globalBatterySaverEnabled = Builder._2D_get2(builder);
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        if(batterySaverEnabled)
            i = 1;
        else
            i = 0;
        parcel.writeByte((byte)i);
        if(globalBatterySaverEnabled)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeByte((byte)i);
        parcel.writeInt(gpsMode);
        parcel.writeFloat(brightnessFactor);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public PowerSaveState createFromParcel(Parcel parcel)
        {
            return new PowerSaveState(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PowerSaveState[] newArray(int i)
        {
            return new PowerSaveState[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public final boolean batterySaverEnabled;
    public final float brightnessFactor;
    public final boolean globalBatterySaverEnabled;
    public final int gpsMode;

}
