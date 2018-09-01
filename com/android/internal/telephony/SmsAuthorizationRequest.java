// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telephony;

import android.os.*;

// Referenced classes of package com.android.internal.telephony:
//            ISmsSecurityService

public class SmsAuthorizationRequest
    implements Parcelable
{

    public SmsAuthorizationRequest(Parcel parcel)
    {
        service = ISmsSecurityService.Stub.asInterface(parcel.readStrongBinder());
        token = parcel.readStrongBinder();
        packageName = parcel.readString();
        destinationAddress = parcel.readString();
        message = parcel.readString();
    }

    public SmsAuthorizationRequest(ISmsSecurityService ismssecurityservice, IBinder ibinder, String s, String s1, String s2)
    {
        service = ismssecurityservice;
        token = ibinder;
        packageName = s;
        destinationAddress = s1;
        message = s2;
    }

    public void accept()
        throws RemoteException
    {
        service.sendResponse(this, true);
    }

    public int describeContents()
    {
        return 0;
    }

    public IBinder getToken()
    {
        return token;
    }

    public void reject()
        throws RemoteException
    {
        service.sendResponse(this, false);
    }

    public String toString()
    {
        return String.format("[%s] (%s) # %s", new Object[] {
            packageName, destinationAddress, message
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeStrongBinder(service.asBinder());
        parcel.writeStrongBinder(token);
        parcel.writeString(packageName);
        parcel.writeString(destinationAddress);
        parcel.writeString(message);
    }

    public static android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public SmsAuthorizationRequest createFromParcel(Parcel parcel)
        {
            return new SmsAuthorizationRequest(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public SmsAuthorizationRequest[] newArray(int i)
        {
            return new SmsAuthorizationRequest[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public final String destinationAddress;
    public final String message;
    public final String packageName;
    private final ISmsSecurityService service;
    private final IBinder token;

}
