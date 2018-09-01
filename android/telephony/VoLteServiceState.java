// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;

import android.os.*;

// Referenced classes of package android.telephony:
//            Rlog

public final class VoLteServiceState
    implements Parcelable
{

    public VoLteServiceState()
    {
        initialize();
    }

    public VoLteServiceState(int i)
    {
        initialize();
        mSrvccState = i;
    }

    public VoLteServiceState(Parcel parcel)
    {
        mSrvccState = parcel.readInt();
    }

    public VoLteServiceState(VoLteServiceState volteservicestate)
    {
        copyFrom(volteservicestate);
    }

    private void initialize()
    {
        mSrvccState = 0x7fffffff;
    }

    private static void log(String s)
    {
        Rlog.w("VoLteServiceState", s);
    }

    public static VoLteServiceState newFromBundle(Bundle bundle)
    {
        VoLteServiceState volteservicestate = new VoLteServiceState();
        volteservicestate.setFromNotifierBundle(bundle);
        return volteservicestate;
    }

    private void setFromNotifierBundle(Bundle bundle)
    {
        mSrvccState = bundle.getInt("mSrvccState");
    }

    protected void copyFrom(VoLteServiceState volteservicestate)
    {
        mSrvccState = volteservicestate.mSrvccState;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        VoLteServiceState volteservicestate;
        try
        {
            volteservicestate = (VoLteServiceState)obj;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            return false;
        }
        if(obj == null)
            return false;
        if(mSrvccState == volteservicestate.mSrvccState)
            flag = true;
        return flag;
    }

    public void fillInNotifierBundle(Bundle bundle)
    {
        bundle.putInt("mSrvccState", mSrvccState);
    }

    public int getSrvccState()
    {
        return mSrvccState;
    }

    public int hashCode()
    {
        return mSrvccState * 31;
    }

    public String toString()
    {
        return (new StringBuilder()).append("VoLteServiceState: ").append(mSrvccState).toString();
    }

    public void validateInput()
    {
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mSrvccState);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public VoLteServiceState createFromParcel(Parcel parcel)
        {
            return new VoLteServiceState(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public VoLteServiceState[] newArray(int i)
        {
            return new VoLteServiceState[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final boolean DBG = false;
    public static final int HANDOVER_CANCELED = 3;
    public static final int HANDOVER_COMPLETED = 1;
    public static final int HANDOVER_FAILED = 2;
    public static final int HANDOVER_STARTED = 0;
    public static final int INVALID = 0x7fffffff;
    private static final String LOG_TAG = "VoLteServiceState";
    public static final int NOT_SUPPORTED = 0;
    public static final int SUPPORTED = 1;
    private int mSrvccState;

}
