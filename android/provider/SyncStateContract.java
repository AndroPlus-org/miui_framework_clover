// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.provider;

import android.accounts.Account;
import android.content.*;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.util.Pair;

// Referenced classes of package android.provider:
//            BaseColumns

public class SyncStateContract
{
    public static interface Columns
        extends BaseColumns
    {

        public static final String ACCOUNT_NAME = "account_name";
        public static final String ACCOUNT_TYPE = "account_type";
        public static final String DATA = "data";
    }

    public static class Constants
        implements Columns
    {

        public static final String CONTENT_DIRECTORY = "syncstate";

        public Constants()
        {
        }
    }

    public static final class Helpers
    {

        public static byte[] get(ContentProviderClient contentproviderclient, Uri uri, Account account)
            throws RemoteException
        {
            contentproviderclient = contentproviderclient.query(uri, DATA_PROJECTION, "account_name=? AND account_type=?", new String[] {
                account.name, account.type
            }, null);
            if(contentproviderclient == null)
                throw new RemoteException();
            if(!contentproviderclient.moveToNext())
                break MISSING_BLOCK_LABEL_74;
            uri = contentproviderclient.getBlob(contentproviderclient.getColumnIndexOrThrow("data"));
            contentproviderclient.close();
            return uri;
            contentproviderclient.close();
            return null;
            uri;
            contentproviderclient.close();
            throw uri;
        }

        public static Pair getWithUri(ContentProviderClient contentproviderclient, Uri uri, Account account)
            throws RemoteException
        {
            contentproviderclient = contentproviderclient.query(uri, DATA_PROJECTION, "account_name=? AND account_type=?", new String[] {
                account.name, account.type
            }, null);
            if(contentproviderclient == null)
                throw new RemoteException();
            if(!contentproviderclient.moveToNext())
                break MISSING_BLOCK_LABEL_92;
            long l = contentproviderclient.getLong(1);
            account = contentproviderclient.getBlob(contentproviderclient.getColumnIndexOrThrow("data"));
            uri = Pair.create(ContentUris.withAppendedId(uri, l), account);
            contentproviderclient.close();
            return uri;
            contentproviderclient.close();
            return null;
            uri;
            contentproviderclient.close();
            throw uri;
        }

        public static Uri insert(ContentProviderClient contentproviderclient, Uri uri, Account account, byte abyte0[])
            throws RemoteException
        {
            ContentValues contentvalues = new ContentValues();
            contentvalues.put("data", abyte0);
            contentvalues.put("account_name", account.name);
            contentvalues.put("account_type", account.type);
            return contentproviderclient.insert(uri, contentvalues);
        }

        public static ContentProviderOperation newSetOperation(Uri uri, Account account, byte abyte0[])
        {
            ContentValues contentvalues = new ContentValues();
            contentvalues.put("data", abyte0);
            return ContentProviderOperation.newInsert(uri).withValue("account_name", account.name).withValue("account_type", account.type).withValues(contentvalues).build();
        }

        public static ContentProviderOperation newUpdateOperation(Uri uri, byte abyte0[])
        {
            ContentValues contentvalues = new ContentValues();
            contentvalues.put("data", abyte0);
            return ContentProviderOperation.newUpdate(uri).withValues(contentvalues).build();
        }

        public static void set(ContentProviderClient contentproviderclient, Uri uri, Account account, byte abyte0[])
            throws RemoteException
        {
            ContentValues contentvalues = new ContentValues();
            contentvalues.put("data", abyte0);
            contentvalues.put("account_name", account.name);
            contentvalues.put("account_type", account.type);
            contentproviderclient.insert(uri, contentvalues);
        }

        public static void update(ContentProviderClient contentproviderclient, Uri uri, byte abyte0[])
            throws RemoteException
        {
            ContentValues contentvalues = new ContentValues();
            contentvalues.put("data", abyte0);
            contentproviderclient.update(uri, contentvalues, null, null);
        }

        private static final String DATA_PROJECTION[] = {
            "data", "_id"
        };
        private static final String SELECT_BY_ACCOUNT = "account_name=? AND account_type=?";


        public Helpers()
        {
        }
    }


    public SyncStateContract()
    {
    }
}
