// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.provider;

import android.content.*;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;

public abstract class SearchIndexablesProvider extends ContentProvider
{

    public SearchIndexablesProvider()
    {
    }

    public void attachInfo(Context context, ProviderInfo providerinfo)
    {
        mAuthority = providerinfo.authority;
        mMatcher = new UriMatcher(-1);
        mMatcher.addURI(mAuthority, "settings/indexables_xml_res", 1);
        mMatcher.addURI(mAuthority, "settings/indexables_raw", 2);
        mMatcher.addURI(mAuthority, "settings/non_indexables_key", 3);
        if(!providerinfo.exported)
            throw new SecurityException("Provider must be exported");
        if(!providerinfo.grantUriPermissions)
            throw new SecurityException("Provider must grantUriPermissions");
        if(!"android.permission.READ_SEARCH_INDEXABLES".equals(providerinfo.readPermission))
        {
            throw new SecurityException("Provider must be protected by READ_SEARCH_INDEXABLES");
        } else
        {
            super.attachInfo(context, providerinfo);
            return;
        }
    }

    public final int delete(Uri uri, String s, String as[])
    {
        throw new UnsupportedOperationException("Delete not supported");
    }

    public String getType(Uri uri)
    {
        switch(mMatcher.match(uri))
        {
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown URI ").append(uri).toString());

        case 1: // '\001'
            return "vnd.android.cursor.dir/indexables_xml_res";

        case 2: // '\002'
            return "vnd.android.cursor.dir/indexables_raw";

        case 3: // '\003'
            return "vnd.android.cursor.dir/non_indexables_key";
        }
    }

    public final Uri insert(Uri uri, ContentValues contentvalues)
    {
        throw new UnsupportedOperationException("Insert not supported");
    }

    public Cursor query(Uri uri, String as[], String s, String as1[], String s1)
    {
        switch(mMatcher.match(uri))
        {
        default:
            throw new UnsupportedOperationException((new StringBuilder()).append("Unknown Uri ").append(uri).toString());

        case 1: // '\001'
            return queryXmlResources(null);

        case 2: // '\002'
            return queryRawData(null);

        case 3: // '\003'
            return queryNonIndexableKeys(null);
        }
    }

    public abstract Cursor queryNonIndexableKeys(String as[]);

    public abstract Cursor queryRawData(String as[]);

    public abstract Cursor queryXmlResources(String as[]);

    public final int update(Uri uri, ContentValues contentvalues, String s, String as[])
    {
        throw new UnsupportedOperationException("Update not supported");
    }

    private static final int MATCH_NON_INDEXABLE_KEYS_CODE = 3;
    private static final int MATCH_RAW_CODE = 2;
    private static final int MATCH_RES_CODE = 1;
    private static final String TAG = "IndexablesProvider";
    private String mAuthority;
    private UriMatcher mMatcher;
}
