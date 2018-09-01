// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi.hotspot2.pps;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;

public final class UpdateParameter
    implements Parcelable
{

    public UpdateParameter()
    {
        mUpdateIntervalInMinutes = 0x8000000000000000L;
        mUpdateMethod = null;
        mRestriction = null;
        mServerUri = null;
        mUsername = null;
        mBase64EncodedPassword = null;
        mTrustRootCertUrl = null;
        mTrustRootCertSha256Fingerprint = null;
    }

    public UpdateParameter(UpdateParameter updateparameter)
    {
        mUpdateIntervalInMinutes = 0x8000000000000000L;
        mUpdateMethod = null;
        mRestriction = null;
        mServerUri = null;
        mUsername = null;
        mBase64EncodedPassword = null;
        mTrustRootCertUrl = null;
        mTrustRootCertSha256Fingerprint = null;
        if(updateparameter == null)
            return;
        mUpdateIntervalInMinutes = updateparameter.mUpdateIntervalInMinutes;
        mUpdateMethod = updateparameter.mUpdateMethod;
        mRestriction = updateparameter.mRestriction;
        mServerUri = updateparameter.mServerUri;
        mUsername = updateparameter.mUsername;
        mBase64EncodedPassword = updateparameter.mBase64EncodedPassword;
        mTrustRootCertUrl = updateparameter.mTrustRootCertUrl;
        if(updateparameter.mTrustRootCertSha256Fingerprint != null)
            mTrustRootCertSha256Fingerprint = Arrays.copyOf(updateparameter.mTrustRootCertSha256Fingerprint, updateparameter.mTrustRootCertSha256Fingerprint.length);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(this == obj)
            return true;
        if(!(obj instanceof UpdateParameter))
            return false;
        obj = (UpdateParameter)obj;
        boolean flag1 = flag;
        if(mUpdateIntervalInMinutes == ((UpdateParameter) (obj)).mUpdateIntervalInMinutes)
        {
            flag1 = flag;
            if(TextUtils.equals(mUpdateMethod, ((UpdateParameter) (obj)).mUpdateMethod))
            {
                flag1 = flag;
                if(TextUtils.equals(mRestriction, ((UpdateParameter) (obj)).mRestriction))
                {
                    flag1 = flag;
                    if(TextUtils.equals(mServerUri, ((UpdateParameter) (obj)).mServerUri))
                    {
                        flag1 = flag;
                        if(TextUtils.equals(mUsername, ((UpdateParameter) (obj)).mUsername))
                        {
                            flag1 = flag;
                            if(TextUtils.equals(mBase64EncodedPassword, ((UpdateParameter) (obj)).mBase64EncodedPassword))
                            {
                                flag1 = flag;
                                if(TextUtils.equals(mTrustRootCertUrl, ((UpdateParameter) (obj)).mTrustRootCertUrl))
                                    flag1 = Arrays.equals(mTrustRootCertSha256Fingerprint, ((UpdateParameter) (obj)).mTrustRootCertSha256Fingerprint);
                            }
                        }
                    }
                }
            }
        }
        return flag1;
    }

    public String getBase64EncodedPassword()
    {
        return mBase64EncodedPassword;
    }

    public String getRestriction()
    {
        return mRestriction;
    }

    public String getServerUri()
    {
        return mServerUri;
    }

    public byte[] getTrustRootCertSha256Fingerprint()
    {
        return mTrustRootCertSha256Fingerprint;
    }

    public String getTrustRootCertUrl()
    {
        return mTrustRootCertUrl;
    }

    public long getUpdateIntervalInMinutes()
    {
        return mUpdateIntervalInMinutes;
    }

    public String getUpdateMethod()
    {
        return mUpdateMethod;
    }

    public String getUsername()
    {
        return mUsername;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            Long.valueOf(mUpdateIntervalInMinutes), mUpdateMethod, mRestriction, mServerUri, mUsername, mBase64EncodedPassword, mTrustRootCertUrl, mTrustRootCertSha256Fingerprint
        });
    }

    public void setBase64EncodedPassword(String s)
    {
        mBase64EncodedPassword = s;
    }

    public void setRestriction(String s)
    {
        mRestriction = s;
    }

    public void setServerUri(String s)
    {
        mServerUri = s;
    }

    public void setTrustRootCertSha256Fingerprint(byte abyte0[])
    {
        mTrustRootCertSha256Fingerprint = abyte0;
    }

    public void setTrustRootCertUrl(String s)
    {
        mTrustRootCertUrl = s;
    }

    public void setUpdateIntervalInMinutes(long l)
    {
        mUpdateIntervalInMinutes = l;
    }

    public void setUpdateMethod(String s)
    {
        mUpdateMethod = s;
    }

    public void setUsername(String s)
    {
        mUsername = s;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("UpdateInterval: ").append(mUpdateIntervalInMinutes).append("\n");
        stringbuilder.append("UpdateMethod: ").append(mUpdateMethod).append("\n");
        stringbuilder.append("Restriction: ").append(mRestriction).append("\n");
        stringbuilder.append("ServerURI: ").append(mServerUri).append("\n");
        stringbuilder.append("Username: ").append(mUsername).append("\n");
        stringbuilder.append("TrustRootCertURL: ").append(mTrustRootCertUrl).append("\n");
        return stringbuilder.toString();
    }

    public boolean validate()
    {
        if(mUpdateIntervalInMinutes == 0x8000000000000000L)
        {
            Log.d("UpdateParameter", "Update interval not specified");
            return false;
        }
        if(mUpdateIntervalInMinutes == 0xffffffffL)
            return true;
        if(!TextUtils.equals(mUpdateMethod, "OMA-DM-ClientInitiated") && TextUtils.equals(mUpdateMethod, "SSP-ClientInitiated") ^ true)
        {
            Log.d("UpdateParameter", (new StringBuilder()).append("Unknown update method: ").append(mUpdateMethod).toString());
            return false;
        }
        if(!TextUtils.equals(mRestriction, "HomeSP") && TextUtils.equals(mRestriction, "RoamingPartner") ^ true && TextUtils.equals(mRestriction, "Unrestricted") ^ true)
        {
            Log.d("UpdateParameter", (new StringBuilder()).append("Unknown restriction: ").append(mRestriction).toString());
            return false;
        }
        if(TextUtils.isEmpty(mServerUri))
        {
            Log.d("UpdateParameter", "Missing update server URI");
            return false;
        }
        if(mServerUri.getBytes(StandardCharsets.UTF_8).length > 1023)
        {
            Log.d("UpdateParameter", (new StringBuilder()).append("URI bytes exceeded the max: ").append(mServerUri.getBytes(StandardCharsets.UTF_8).length).toString());
            return false;
        }
        if(TextUtils.isEmpty(mUsername))
        {
            Log.d("UpdateParameter", "Missing username");
            return false;
        }
        if(mUsername.getBytes(StandardCharsets.UTF_8).length > 63)
        {
            Log.d("UpdateParameter", (new StringBuilder()).append("Username bytes exceeded the max: ").append(mUsername.getBytes(StandardCharsets.UTF_8).length).toString());
            return false;
        }
        if(TextUtils.isEmpty(mBase64EncodedPassword))
        {
            Log.d("UpdateParameter", "Missing username");
            return false;
        }
        if(mBase64EncodedPassword.getBytes(StandardCharsets.UTF_8).length > 255)
        {
            Log.d("UpdateParameter", (new StringBuilder()).append("Password bytes exceeded the max: ").append(mBase64EncodedPassword.getBytes(StandardCharsets.UTF_8).length).toString());
            return false;
        }
        try
        {
            Base64.decode(mBase64EncodedPassword, 0);
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            Log.d("UpdateParameter", (new StringBuilder()).append("Invalid encoding for password: ").append(mBase64EncodedPassword).toString());
            return false;
        }
        if(TextUtils.isEmpty(mTrustRootCertUrl))
        {
            Log.d("UpdateParameter", "Missing trust root certificate URL");
            return false;
        }
        if(mTrustRootCertUrl.getBytes(StandardCharsets.UTF_8).length > 1023)
        {
            Log.d("UpdateParameter", (new StringBuilder()).append("Trust root cert URL bytes exceeded the max: ").append(mTrustRootCertUrl.getBytes(StandardCharsets.UTF_8).length).toString());
            return false;
        }
        if(mTrustRootCertSha256Fingerprint == null)
        {
            Log.d("UpdateParameter", "Missing trust root certificate SHA-256 fingerprint");
            return false;
        }
        if(mTrustRootCertSha256Fingerprint.length != 32)
        {
            Log.d("UpdateParameter", (new StringBuilder()).append("Incorrect size of trust root certificate SHA-256 fingerprint: ").append(mTrustRootCertSha256Fingerprint.length).toString());
            return false;
        } else
        {
            return true;
        }
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeLong(mUpdateIntervalInMinutes);
        parcel.writeString(mUpdateMethod);
        parcel.writeString(mRestriction);
        parcel.writeString(mServerUri);
        parcel.writeString(mUsername);
        parcel.writeString(mBase64EncodedPassword);
        parcel.writeString(mTrustRootCertUrl);
        parcel.writeByteArray(mTrustRootCertSha256Fingerprint);
    }

    private static final int CERTIFICATE_SHA256_BYTES = 32;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public UpdateParameter createFromParcel(Parcel parcel)
        {
            UpdateParameter updateparameter = new UpdateParameter();
            updateparameter.setUpdateIntervalInMinutes(parcel.readLong());
            updateparameter.setUpdateMethod(parcel.readString());
            updateparameter.setRestriction(parcel.readString());
            updateparameter.setServerUri(parcel.readString());
            updateparameter.setUsername(parcel.readString());
            updateparameter.setBase64EncodedPassword(parcel.readString());
            updateparameter.setTrustRootCertUrl(parcel.readString());
            updateparameter.setTrustRootCertSha256Fingerprint(parcel.createByteArray());
            return updateparameter;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public UpdateParameter[] newArray(int i)
        {
            return new UpdateParameter[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final int MAX_PASSWORD_BYTES = 255;
    private static final int MAX_URI_BYTES = 1023;
    private static final int MAX_URL_BYTES = 1023;
    private static final int MAX_USERNAME_BYTES = 63;
    private static final String TAG = "UpdateParameter";
    public static final long UPDATE_CHECK_INTERVAL_NEVER = 0xffffffffL;
    public static final String UPDATE_METHOD_OMADM = "OMA-DM-ClientInitiated";
    public static final String UPDATE_METHOD_SSP = "SSP-ClientInitiated";
    public static final String UPDATE_RESTRICTION_HOMESP = "HomeSP";
    public static final String UPDATE_RESTRICTION_ROAMING_PARTNER = "RoamingPartner";
    public static final String UPDATE_RESTRICTION_UNRESTRICTED = "Unrestricted";
    private String mBase64EncodedPassword;
    private String mRestriction;
    private String mServerUri;
    private byte mTrustRootCertSha256Fingerprint[];
    private String mTrustRootCertUrl;
    private long mUpdateIntervalInMinutes;
    private String mUpdateMethod;
    private String mUsername;

}
