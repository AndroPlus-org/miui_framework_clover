// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telecom;

import android.accounts.*;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

public class AuthenticatorService extends Service
{
    public class Authenticator extends AbstractAccountAuthenticator
    {

        public Bundle addAccount(AccountAuthenticatorResponse accountauthenticatorresponse, String s, String s1, String as[], Bundle bundle)
            throws NetworkErrorException
        {
            return null;
        }

        public Bundle confirmCredentials(AccountAuthenticatorResponse accountauthenticatorresponse, Account account, Bundle bundle)
            throws NetworkErrorException
        {
            return null;
        }

        public Bundle editProperties(AccountAuthenticatorResponse accountauthenticatorresponse, String s)
        {
            throw new UnsupportedOperationException();
        }

        public Bundle getAuthToken(AccountAuthenticatorResponse accountauthenticatorresponse, Account account, String s, Bundle bundle)
            throws NetworkErrorException
        {
            throw new UnsupportedOperationException();
        }

        public String getAuthTokenLabel(String s)
        {
            throw new UnsupportedOperationException();
        }

        public Bundle hasFeatures(AccountAuthenticatorResponse accountauthenticatorresponse, Account account, String as[])
            throws NetworkErrorException
        {
            throw new UnsupportedOperationException();
        }

        public Bundle updateCredentials(AccountAuthenticatorResponse accountauthenticatorresponse, Account account, String s, Bundle bundle)
            throws NetworkErrorException
        {
            throw new UnsupportedOperationException();
        }

        final AuthenticatorService this$0;

        public Authenticator(Context context)
        {
            this$0 = AuthenticatorService.this;
            super(context);
        }
    }


    public AuthenticatorService()
    {
    }

    public IBinder onBind(Intent intent)
    {
        return mAuthenticator.getIBinder();
    }

    public void onCreate()
    {
        mAuthenticator = new Authenticator(this);
    }

    private static Authenticator mAuthenticator;
}
