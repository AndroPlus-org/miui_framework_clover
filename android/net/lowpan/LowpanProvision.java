// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.lowpan;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

// Referenced classes of package android.net.lowpan:
//            LowpanIdentity, LowpanCredential

public class LowpanProvision
    implements Parcelable
{
    public static class Builder
    {

        public LowpanProvision build()
        {
            return provision;
        }

        public Builder setLowpanCredential(LowpanCredential lowpancredential)
        {
            LowpanProvision._2D_set0(provision, lowpancredential);
            return this;
        }

        public Builder setLowpanIdentity(LowpanIdentity lowpanidentity)
        {
            LowpanProvision._2D_set1(provision, lowpanidentity);
            return this;
        }

        private final LowpanProvision provision = new LowpanProvision(null);

        public Builder()
        {
        }
    }


    static LowpanCredential _2D_set0(LowpanProvision lowpanprovision, LowpanCredential lowpancredential)
    {
        lowpanprovision.mCredential = lowpancredential;
        return lowpancredential;
    }

    static LowpanIdentity _2D_set1(LowpanProvision lowpanprovision, LowpanIdentity lowpanidentity)
    {
        lowpanprovision.mIdentity = lowpanidentity;
        return lowpanidentity;
    }

    private LowpanProvision()
    {
        mIdentity = new LowpanIdentity();
        mCredential = null;
    }

    LowpanProvision(LowpanProvision lowpanprovision)
    {
        this();
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if(!(obj instanceof LowpanProvision))
            return false;
        obj = (LowpanProvision)obj;
        if(!mIdentity.equals(((LowpanProvision) (obj)).mIdentity))
            return false;
        return Objects.equals(mCredential, ((LowpanProvision) (obj)).mCredential);
    }

    public LowpanCredential getLowpanCredential()
    {
        return mCredential;
    }

    public LowpanIdentity getLowpanIdentity()
    {
        return mIdentity;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            mIdentity, mCredential
        });
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("LowpanProvision { identity => ").append(mIdentity.toString());
        if(mCredential != null)
            stringbuffer.append(", credential => ").append(mCredential.toString());
        stringbuffer.append("}");
        return stringbuffer.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        mIdentity.writeToParcel(parcel, i);
        if(mCredential == null)
        {
            parcel.writeBoolean(false);
        } else
        {
            parcel.writeBoolean(true);
            mCredential.writeToParcel(parcel, i);
        }
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public LowpanProvision createFromParcel(Parcel parcel)
        {
            Builder builder = new Builder();
            builder.setLowpanIdentity((LowpanIdentity)LowpanIdentity.CREATOR.createFromParcel(parcel));
            if(parcel.readBoolean())
                builder.setLowpanCredential((LowpanCredential)LowpanCredential.CREATOR.createFromParcel(parcel));
            return builder.build();
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public LowpanProvision[] newArray(int i)
        {
            return new LowpanProvision[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private LowpanCredential mCredential;
    private LowpanIdentity mIdentity;

}
