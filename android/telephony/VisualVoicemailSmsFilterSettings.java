// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Collections;
import java.util.List;

public final class VisualVoicemailSmsFilterSettings
    implements Parcelable
{
    public static class Builder
    {

        static String _2D_get0(Builder builder)
        {
            return builder.mClientPrefix;
        }

        static int _2D_get1(Builder builder)
        {
            return builder.mDestinationPort;
        }

        static List _2D_get2(Builder builder)
        {
            return builder.mOriginatingNumbers;
        }

        public VisualVoicemailSmsFilterSettings build()
        {
            return new VisualVoicemailSmsFilterSettings(this, null);
        }

        public Builder setClientPrefix(String s)
        {
            if(s == null)
            {
                throw new IllegalArgumentException("Client prefix cannot be null");
            } else
            {
                mClientPrefix = s;
                return this;
            }
        }

        public Builder setDestinationPort(int i)
        {
            mDestinationPort = i;
            return this;
        }

        public Builder setOriginatingNumbers(List list)
        {
            if(list == null)
            {
                throw new IllegalArgumentException("Originating numbers cannot be null");
            } else
            {
                mOriginatingNumbers = list;
                return this;
            }
        }

        private String mClientPrefix;
        private int mDestinationPort;
        private List mOriginatingNumbers;

        public Builder()
        {
            mClientPrefix = "//VVM";
            mOriginatingNumbers = VisualVoicemailSmsFilterSettings.DEFAULT_ORIGINATING_NUMBERS;
            mDestinationPort = -1;
        }
    }


    private VisualVoicemailSmsFilterSettings(Builder builder)
    {
        clientPrefix = Builder._2D_get0(builder);
        originatingNumbers = Builder._2D_get2(builder);
        destinationPort = Builder._2D_get1(builder);
    }

    VisualVoicemailSmsFilterSettings(Builder builder, VisualVoicemailSmsFilterSettings visualvoicemailsmsfiltersettings)
    {
        this(builder);
    }

    public int describeContents()
    {
        return 0;
    }

    public String toString()
    {
        return (new StringBuilder()).append("[VisualVoicemailSmsFilterSettings clientPrefix=").append(clientPrefix).append(", originatingNumbers=").append(originatingNumbers).append(", destinationPort=").append(destinationPort).append("]").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(clientPrefix);
        parcel.writeStringList(originatingNumbers);
        parcel.writeInt(destinationPort);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public VisualVoicemailSmsFilterSettings createFromParcel(Parcel parcel)
        {
            Builder builder = new Builder();
            builder.setClientPrefix(parcel.readString());
            builder.setOriginatingNumbers(parcel.createStringArrayList());
            builder.setDestinationPort(parcel.readInt());
            return builder.build();
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public VisualVoicemailSmsFilterSettings[] newArray(int i)
        {
            return new VisualVoicemailSmsFilterSettings[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final String DEFAULT_CLIENT_PREFIX = "//VVM";
    public static final int DEFAULT_DESTINATION_PORT = -1;
    public static final List DEFAULT_ORIGINATING_NUMBERS = Collections.emptyList();
    public static final int DESTINATION_PORT_ANY = -1;
    public static final int DESTINATION_PORT_DATA_SMS = -2;
    public final String clientPrefix;
    public final int destinationPort;
    public final List originatingNumbers;

}
