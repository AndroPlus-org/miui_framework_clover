// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.provider;

import android.util.Base64;
import com.android.internal.util.Preconditions;
import java.util.Collections;
import java.util.List;

public final class FontRequest
{

    public FontRequest(String s, String s1, String s2)
    {
        mProviderAuthority = (String)Preconditions.checkNotNull(s);
        mQuery = (String)Preconditions.checkNotNull(s2);
        mProviderPackage = (String)Preconditions.checkNotNull(s1);
        mCertificates = Collections.emptyList();
        mIdentifier = (new StringBuilder(mProviderAuthority)).append("-").append(mProviderPackage).append("-").append(mQuery).toString();
    }

    public FontRequest(String s, String s1, String s2, List list)
    {
        mProviderAuthority = (String)Preconditions.checkNotNull(s);
        mProviderPackage = (String)Preconditions.checkNotNull(s1);
        mQuery = (String)Preconditions.checkNotNull(s2);
        mCertificates = (List)Preconditions.checkNotNull(list);
        mIdentifier = (new StringBuilder(mProviderAuthority)).append("-").append(mProviderPackage).append("-").append(mQuery).toString();
    }

    public List getCertificates()
    {
        return mCertificates;
    }

    public String getIdentifier()
    {
        return mIdentifier;
    }

    public String getProviderAuthority()
    {
        return mProviderAuthority;
    }

    public String getProviderPackage()
    {
        return mProviderPackage;
    }

    public String getQuery()
    {
        return mQuery;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("FontRequest {mProviderAuthority: ").append(mProviderAuthority).append(", mProviderPackage: ").append(mProviderPackage).append(", mQuery: ").append(mQuery).append(", mCertificates:");
        for(int i = 0; i < mCertificates.size(); i++)
        {
            stringbuilder.append(" [");
            List list = (List)mCertificates.get(i);
            for(int j = 0; j < list.size(); j++)
            {
                stringbuilder.append(" \"");
                stringbuilder.append(Base64.encodeToString((byte[])list.get(j), 0));
                stringbuilder.append("\"");
            }

            stringbuilder.append(" ]");
        }

        stringbuilder.append("}");
        return stringbuilder.toString();
    }

    private final List mCertificates;
    private final String mIdentifier;
    private final String mProviderAuthority;
    private final String mProviderPackage;
    private final String mQuery;
}
