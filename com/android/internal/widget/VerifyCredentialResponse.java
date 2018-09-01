// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.service.gatekeeper.GateKeeperResponse;
import android.util.Slog;

public final class VerifyCredentialResponse
    implements Parcelable
{

    static void _2D_wrap0(VerifyCredentialResponse verifycredentialresponse, byte abyte0[])
    {
        verifycredentialresponse.setPayload(abyte0);
    }

    static void _2D_wrap1(VerifyCredentialResponse verifycredentialresponse, int i)
    {
        verifycredentialresponse.setTimeout(i);
    }

    public VerifyCredentialResponse()
    {
        mResponseCode = 0;
        mPayload = null;
    }

    public VerifyCredentialResponse(int i)
    {
        mTimeout = i;
        mResponseCode = 1;
        mPayload = null;
    }

    private VerifyCredentialResponse(int i, int j, byte abyte0[])
    {
        mResponseCode = i;
        mTimeout = j;
        mPayload = abyte0;
    }

    VerifyCredentialResponse(int i, int j, byte abyte0[], VerifyCredentialResponse verifycredentialresponse)
    {
        this(i, j, abyte0);
    }

    public VerifyCredentialResponse(byte abyte0[])
    {
        mPayload = abyte0;
        mResponseCode = 0;
    }

    public static VerifyCredentialResponse fromGateKeeperResponse(GateKeeperResponse gatekeeperresponse)
    {
        int i = gatekeeperresponse.getResponseCode();
        if(i == 1)
            gatekeeperresponse = new VerifyCredentialResponse(gatekeeperresponse.getTimeout());
        else
        if(i == 0)
        {
            gatekeeperresponse = gatekeeperresponse.getPayload();
            if(gatekeeperresponse == null)
            {
                Slog.e("VerifyCredentialResponse", "verifyChallenge response had no associated payload");
                gatekeeperresponse = ERROR;
            } else
            {
                gatekeeperresponse = new VerifyCredentialResponse(gatekeeperresponse);
            }
        } else
        {
            gatekeeperresponse = ERROR;
        }
        return gatekeeperresponse;
    }

    private void setPayload(byte abyte0[])
    {
        mPayload = abyte0;
    }

    private void setTimeout(int i)
    {
        mTimeout = i;
    }

    public int describeContents()
    {
        return 0;
    }

    public byte[] getPayload()
    {
        return mPayload;
    }

    public int getResponseCode()
    {
        return mResponseCode;
    }

    public int getTimeout()
    {
        return mTimeout;
    }

    public VerifyCredentialResponse stripPayload()
    {
        return new VerifyCredentialResponse(mResponseCode, mTimeout, new byte[0]);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mResponseCode);
        if(mResponseCode != 1) goto _L2; else goto _L1
_L1:
        parcel.writeInt(mTimeout);
_L4:
        return;
_L2:
        if(mResponseCode == 0)
            if(mPayload != null)
            {
                parcel.writeInt(mPayload.length);
                parcel.writeByteArray(mPayload);
            } else
            {
                parcel.writeInt(0);
            }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public VerifyCredentialResponse createFromParcel(Parcel parcel)
        {
            int i;
            VerifyCredentialResponse verifycredentialresponse;
            i = parcel.readInt();
            verifycredentialresponse = new VerifyCredentialResponse(i, 0, null, null);
            if(i != 1) goto _L2; else goto _L1
_L1:
            VerifyCredentialResponse._2D_wrap1(verifycredentialresponse, parcel.readInt());
_L4:
            return verifycredentialresponse;
_L2:
            if(i == 0)
            {
                int j = parcel.readInt();
                if(j > 0)
                {
                    byte abyte0[] = new byte[j];
                    parcel.readByteArray(abyte0);
                    VerifyCredentialResponse._2D_wrap0(verifycredentialresponse, abyte0);
                }
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public VerifyCredentialResponse[] newArray(int i)
        {
            return new VerifyCredentialResponse[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final VerifyCredentialResponse ERROR = new VerifyCredentialResponse(-1, 0, null);
    public static final VerifyCredentialResponse OK = new VerifyCredentialResponse();
    public static final int RESPONSE_ERROR = -1;
    public static final int RESPONSE_OK = 0;
    public static final int RESPONSE_RETRY = 1;
    private static final String TAG = "VerifyCredentialResponse";
    private byte mPayload[];
    private int mResponseCode;
    private int mTimeout;

}
