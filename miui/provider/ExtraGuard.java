// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.provider;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import miui.util.IOUtils;

// Referenced classes of package miui.provider:
//            ExtraGuardVirusInfoEntity

public final class ExtraGuard
{

    public ExtraGuard()
    {
    }

    public static ExtraGuardVirusInfoEntity checkApkForVirusInfo(Context context, Uri uri, boolean flag)
    {
        ContentResolver contentresolver;
        Object obj;
        Object obj1;
        Uri uri1;
        if(uri == null)
            throw new NullPointerException("uri is null");
        contentresolver = context.getContentResolver();
        obj = null;
        obj1 = null;
        uri1 = obj1;
        context = obj;
        Uri uri2 = Uri.parse("content://guard/sync_scan");
        if(uri2 == null)
            break MISSING_BLOCK_LABEL_224;
        uri1 = obj1;
        context = obj;
        ExtraGuardVirusInfoEntity extraguardvirusinfoentity = JVM INSTR new #47  <Class ExtraGuardVirusInfoEntity>;
        uri1 = obj1;
        context = obj;
        extraguardvirusinfoentity.ExtraGuardVirusInfoEntity();
        uri1 = obj1;
        context = obj;
        uri = contentresolver.query(uri2, null, null, new String[] {
            uri.toString(), String.valueOf(flag)
        }, null);
        if(uri == null)
            break MISSING_BLOCK_LABEL_217;
        uri1 = uri;
        context = uri;
        if(!uri.moveToNext())
            break MISSING_BLOCK_LABEL_217;
        uri1 = uri;
        context = uri;
        extraguardvirusinfoentity.setType(uri.getInt(uri.getColumnIndex("Type")));
        uri1 = uri;
        context = uri;
        extraguardvirusinfoentity.setDescription(uri.getString(uri.getColumnIndex("Description")));
        uri1 = uri;
        context = uri;
        extraguardvirusinfoentity.setVirusName(uri.getString(uri.getColumnIndex("VirusName")));
        uri1 = uri;
        context = uri;
        extraguardvirusinfoentity.setEngineName(uri.getString(uri.getColumnIndex("EngineName")));
        IOUtils.closeQuietly(uri);
        return extraguardvirusinfoentity;
        IOUtils.closeQuietly(null);
_L2:
        return null;
        uri;
        context = uri1;
        uri.printStackTrace();
        IOUtils.closeQuietly(uri1);
        if(true) goto _L2; else goto _L1
_L1:
        uri;
        IOUtils.closeQuietly(context);
        throw uri;
    }

    public static final String DEFAULT_PACKAGE_NAME = "com.miui.guardprovider";
    public static final int VIRUS_BLACK = 2;
    public static final int VIRUS_GRAY = 3;
    public static final int VIRUS_WHITE = 1;
}
