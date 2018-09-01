// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.accounts;

import android.os.*;
import android.util.Log;

// Referenced classes of package android.accounts:
//            IAccountAuthenticatorResponse, AccountManager

public class AccountAuthenticatorResponse
    implements Parcelable
{

    public AccountAuthenticatorResponse(IAccountAuthenticatorResponse iaccountauthenticatorresponse)
    {
        mAccountAuthenticatorResponse = iaccountauthenticatorresponse;
    }

    public AccountAuthenticatorResponse(Parcel parcel)
    {
        mAccountAuthenticatorResponse = IAccountAuthenticatorResponse.Stub.asInterface(parcel.readStrongBinder());
    }

    public int describeContents()
    {
        return 0;
    }

    public void onError(int i, String s)
    {
        if(Log.isLoggable("AccountAuthenticator", 2))
            Log.v("AccountAuthenticator", (new StringBuilder()).append("AccountAuthenticatorResponse.onError: ").append(i).append(", ").append(s).toString());
        mAccountAuthenticatorResponse.onError(i, s);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void onRequestContinued()
    {
        if(Log.isLoggable("AccountAuthenticator", 2))
            Log.v("AccountAuthenticator", "AccountAuthenticatorResponse.onRequestContinued");
        mAccountAuthenticatorResponse.onRequestContinued();
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void onResult(Bundle bundle)
    {
        if(Log.isLoggable("AccountAuthenticator", 2))
        {
            bundle.keySet();
            Log.v("AccountAuthenticator", (new StringBuilder()).append("AccountAuthenticatorResponse.onResult: ").append(AccountManager.sanitizeResult(bundle)).toString());
        }
        mAccountAuthenticatorResponse.onResult(bundle);
_L2:
        return;
        bundle;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeStrongBinder(mAccountAuthenticatorResponse.asBinder());
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public AccountAuthenticatorResponse createFromParcel(Parcel parcel)
        {
            return new AccountAuthenticatorResponse(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public AccountAuthenticatorResponse[] newArray(int i)
        {
            return new AccountAuthenticatorResponse[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String TAG = "AccountAuthenticator";
    private IAccountAuthenticatorResponse mAccountAuthenticatorResponse;

}
