// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.media;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import java.io.File;
import miui.os.Environment;

public class MiuiMediaScannerUtil
{

    public MiuiMediaScannerUtil()
    {
    }

    public static void scanFolder(Context context, String s)
    {
        if(s == null || (new File(s)).isDirectory() ^ true)
        {
            Log.e("MiuiMediaScannerUtil", (new StringBuilder()).append("The folder path to scan is invalid: ").append(s).toString());
            return;
        } else
        {
            Intent intent = new Intent("miui.intent.action.MEDIA_SCANNER_SCAN_FOLDER");
            intent.setClassName("com.android.providers.media", "com.android.providers.media.MediaScannerReceiver");
            intent.setData(Uri.fromFile(new File(s)));
            context.sendBroadcast(intent);
            return;
        }
    }

    public static void scanSingleFile(Context context, String s)
    {
        if(s == null || (new File(s)).isDirectory())
        {
            Log.e("MiuiMediaScannerUtil", (new StringBuilder()).append("The path must be a file path: ").append(s).toString());
            return;
        } else
        {
            Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
            intent.setClassName("com.android.providers.media", "com.android.providers.media.MediaScannerReceiver");
            intent.setData(Uri.fromFile(new File(s)));
            context.sendBroadcast(intent);
            return;
        }
    }

    public static void scanWholeExternalStorage(Context context)
    {
        Intent intent = new Intent("android.intent.action.MEDIA_MOUNTED");
        intent.setClassName("com.android.providers.media", "com.android.providers.media.MediaScannerReceiver");
        intent.setData(Uri.fromFile(Environment.getExternalStorageDirectory()));
        context.sendBroadcast(intent);
    }

    private static final String MEDIA_SCANNER_CLASS = "com.android.providers.media.MediaScannerReceiver";
    private static final String MEDIA_SCANNER_PACKAGE = "com.android.providers.media";
    private static final String TAG = "MiuiMediaScannerUtil";
}
