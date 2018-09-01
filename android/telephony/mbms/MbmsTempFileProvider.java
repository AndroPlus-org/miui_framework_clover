// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony.mbms;

import android.content.*;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import java.io.*;
import java.util.Objects;

// Referenced classes of package android.telephony.mbms:
//            MbmsUtils

public class MbmsTempFileProvider extends ContentProvider
{

    public MbmsTempFileProvider()
    {
    }

    public static File getEmbmsTempFileDir(Context context)
    {
        String s;
        s = context.getSharedPreferences("MbmsTempFileRootPrefs", 0).getString("mbms_temp_file_root", null);
        if(s == null)
            break MISSING_BLOCK_LABEL_34;
        context = JVM INSTR new #37  <Class File>;
        context.File(s);
        return context.getCanonicalFile();
        File file = JVM INSTR new #37  <Class File>;
        file.File(context.getFilesDir(), "androidMbmsTempFileRoot");
        context = file.getCanonicalFile();
        return context;
        context;
        throw new RuntimeException((new StringBuilder()).append("Unable to canonicalize temp file root path ").append(context).toString());
    }

    public static File getFileForUri(Context context, String s, Uri uri)
        throws FileNotFoundException
    {
        if(!"content".equals(uri.getScheme()))
            throw new IllegalArgumentException("Uri must have scheme content");
        if(!Objects.equals(s, uri.getAuthority()))
            throw new IllegalArgumentException((new StringBuilder()).append("Uri does not have a matching authority: ").append(s).append(", ").append(uri.getAuthority()).toString());
        s = Uri.decode(uri.getEncodedPath());
        try
        {
            context = getEmbmsTempFileDir(context).getCanonicalFile();
            uri = JVM INSTR new #37  <Class File>;
            uri.File(context, s);
            s = uri.getCanonicalFile();
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw new FileNotFoundException("Could not resolve paths");
        }
        if(!s.getPath().startsWith(context.getPath()))
            throw new SecurityException("Resolved path jumped beyond configured root");
        else
            return s;
    }

    public static Uri getUriForFile(Context context, String s, File file)
    {
        String s1;
        try
        {
            s1 = file.getCanonicalPath();
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Could not get canonical path for file ").append(file).toString());
        }
        context = getEmbmsTempFileDir(context);
        if(!MbmsUtils.isContainedIn(context, file))
            throw new IllegalArgumentException((new StringBuilder()).append("File ").append(file).append(" is not contained in the temp ").append("file directory, which is ").append(context).toString());
        try
        {
            file = context.getCanonicalPath();
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new RuntimeException((new StringBuilder()).append("Could not get canonical path for temp file root dir ").append(context).toString());
        }
        if(file.endsWith("/"))
            context = s1.substring(file.length());
        else
            context = s1.substring(file.length() + 1);
        context = Uri.encode(context);
        return (new android.net.Uri.Builder()).scheme("content").authority(s).encodedPath(context).build();
    }

    public void attachInfo(Context context, ProviderInfo providerinfo)
    {
        super.attachInfo(context, providerinfo);
        if(providerinfo.exported)
            throw new SecurityException("Provider must not be exported");
        if(!providerinfo.grantUriPermissions)
        {
            throw new SecurityException("Provider must grant uri permissions");
        } else
        {
            mAuthority = providerinfo.authority;
            mContext = context;
            return;
        }
    }

    public int delete(Uri uri, String s, String as[])
    {
        throw new UnsupportedOperationException("No deleting supported");
    }

    public String getType(Uri uri)
    {
        return "application/octet-stream";
    }

    public Uri insert(Uri uri, ContentValues contentvalues)
    {
        throw new UnsupportedOperationException("No inserting supported");
    }

    public boolean onCreate()
    {
        return true;
    }

    public ParcelFileDescriptor openFile(Uri uri, String s)
        throws FileNotFoundException
    {
        return ParcelFileDescriptor.open(getFileForUri(mContext, mAuthority, uri), ParcelFileDescriptor.parseMode(s));
    }

    public Cursor query(Uri uri, String as[], String s, String as1[], String s1)
    {
        throw new UnsupportedOperationException("No querying supported");
    }

    public int update(Uri uri, ContentValues contentvalues, String s, String as[])
    {
        throw new UnsupportedOperationException("No updating supported");
    }

    public static final String TEMP_FILE_ROOT_PREF_FILE_NAME = "MbmsTempFileRootPrefs";
    public static final String TEMP_FILE_ROOT_PREF_NAME = "mbms_temp_file_root";
    private String mAuthority;
    private Context mContext;
}
