// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony.mbms;

import android.content.*;
import android.content.pm.*;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.FileAttribute;
import java.util.*;

// Referenced classes of package android.telephony.mbms:
//            DownloadRequest, MbmsUtils, MbmsTempFileProvider, UriPathPair, 
//            FileInfo

public class MbmsDownloadReceiver extends BroadcastReceiver
{

    public MbmsDownloadReceiver()
    {
        mFileProviderAuthorityCache = null;
        mMiddlewarePackageNameCache = null;
    }

    private void cleanupPostMove(Context context, Intent intent)
    {
        DownloadRequest downloadrequest = (DownloadRequest)intent.getParcelableExtra("android.telephony.extra.MBMS_DOWNLOAD_REQUEST");
        if(downloadrequest == null)
        {
            Log.w("MbmsDownloadReceiver", "Intent does not include a DownloadRequest. Ignoring.");
            return;
        }
        intent = intent.getParcelableArrayListExtra("android.telephony.mbms.extra.TEMP_LIST");
        if(intent == null)
            return;
        Iterator iterator = intent.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            intent = (Uri)iterator.next();
            if(verifyTempFilePath(context, downloadrequest.getFileServiceId(), intent))
                (new File(intent.getSchemeSpecificPart())).delete();
        } while(true);
    }

    private void cleanupTempFiles(Context context, Intent intent)
    {
        context = MbmsUtils.getEmbmsTempFileDirForService(context, intent.getStringExtra("android.telephony.mbms.extra.SERVICE_ID")).listFiles(new FileFilter() {

            public boolean accept(File file)
            {
                Object obj;
                try
                {
                    obj = file.getCanonicalFile();
                }
                // Misplaced declaration of an exception variable
                catch(Object obj)
                {
                    Log.w("MbmsDownloadReceiver", (new StringBuilder()).append("Got IOException canonicalizing ").append(file).append(", not deleting.").toString());
                    return false;
                }
                if(!((File) (obj)).getName().endsWith(".embms.temp"))
                {
                    return false;
                } else
                {
                    file = Uri.fromFile(((File) (obj)));
                    return filesInUse.contains(file) ^ true;
                }
            }

            final MbmsDownloadReceiver this$0;
            final List val$filesInUse;

            
            {
                this$0 = MbmsDownloadReceiver.this;
                filesInUse = list;
                super();
            }
        }
);
        int i = 0;
        for(int j = context.length; i < j; i++)
            context[i].delete();

    }

    private ArrayList generateFreshTempFiles(Context context, String s, int i)
    {
        File file = MbmsUtils.getEmbmsTempFileDirForService(context, s);
        if(!file.exists())
            file.mkdirs();
        s = new ArrayList(i);
        int j = 0;
        while(j < i) 
        {
            Object obj = generateSingleTempFile(file);
            if(obj == null)
            {
                setResultCode(5);
                Log.w("MbmsDownloadReceiver", "Failed to generate a temp file. Moving on.");
            } else
            {
                Uri uri = Uri.fromFile(((File) (obj)));
                obj = MbmsTempFileProvider.getUriForFile(context, getFileProviderAuthorityCached(context), ((File) (obj)));
                context.grantUriPermission(getMiddlewarePackageCached(context), ((Uri) (obj)), 3);
                s.add(new UriPathPair(uri, ((Uri) (obj))));
            }
            j++;
        }
        return s;
    }

    private static File generateSingleTempFile(File file)
    {
        int i = 0;
_L2:
        int j;
        File file1;
        if(i >= 5)
            break MISSING_BLOCK_LABEL_57;
        j = i + 1;
        file1 = new File(file, (new StringBuilder()).append(UUID.randomUUID()).append(".embms.temp").toString());
        i = j;
        if(!file1.createNewFile())
            continue; /* Loop/switch isn't completed */
        file1 = file1.getCanonicalFile();
        return file1;
        return null;
        IOException ioexception;
        ioexception;
        i = j;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private void generateTempFiles(Context context, Intent intent)
    {
        Object obj = intent.getStringExtra("android.telephony.mbms.extra.SERVICE_ID");
        if(obj == null)
        {
            Log.w("MbmsDownloadReceiver", "Temp file request did not include the associated service id. Ignoring.");
            setResultCode(2);
            return;
        }
        int i = intent.getIntExtra("android.telephony.mbms.extra.FD_COUNT", 0);
        ArrayList arraylist = intent.getParcelableArrayListExtra("android.telephony.mbms.extra.PAUSED_LIST");
        if(i == 0 && (arraylist == null || arraylist.size() == 0))
        {
            Log.i("MbmsDownloadReceiver", "No temp files actually requested. Ending.");
            setResultCode(0);
            setResultExtras(Bundle.EMPTY);
            return;
        } else
        {
            intent = generateFreshTempFiles(context, ((String) (obj)), i);
            obj = generateUrisForPausedFiles(context, ((String) (obj)), arraylist);
            context = new Bundle();
            context.putParcelableArrayList("android.telephony.mbms.extra.FREE_URI_LIST", intent);
            context.putParcelableArrayList("android.telephony.mbms.extra.PAUSED_URI_LIST", ((ArrayList) (obj)));
            setResultCode(0);
            setResultExtras(context);
            return;
        }
    }

    private ArrayList generateUrisForPausedFiles(Context context, String s, List list)
    {
        if(list == null)
            return new ArrayList(0);
        ArrayList arraylist = new ArrayList(list.size());
        for(list = list.iterator(); list.hasNext();)
        {
            Uri uri = (Uri)list.next();
            if(!verifyTempFilePath(context, s, uri))
            {
                Log.w("MbmsDownloadReceiver", (new StringBuilder()).append("Supplied file ").append(uri).append(" is not a valid temp file to resume").toString());
                setResultCode(5);
            } else
            {
                Object obj = new File(uri.getSchemeSpecificPart());
                if(!((File) (obj)).exists())
                {
                    Log.w("MbmsDownloadReceiver", (new StringBuilder()).append("Supplied file ").append(uri).append(" does not exist.").toString());
                    setResultCode(5);
                } else
                {
                    obj = MbmsTempFileProvider.getUriForFile(context, getFileProviderAuthorityCached(context), ((File) (obj)));
                    context.grantUriPermission(getMiddlewarePackageCached(context), ((Uri) (obj)), 3);
                    arraylist.add(new UriPathPair(uri, ((Uri) (obj))));
                }
            }
        }

        return arraylist;
    }

    private static String getFileProviderAuthority(Context context)
    {
        Object obj;
        try
        {
            obj = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            throw new RuntimeException((new StringBuilder()).append("Package manager couldn't find ").append(context.getPackageName()).toString());
        }
        if(((ApplicationInfo) (obj)).metaData == null)
            throw new RuntimeException("App must declare the file provider authority as metadata in the manifest.");
        context = ((ApplicationInfo) (obj)).metaData.getString("mbms-file-provider-authority");
        if(context == null)
            throw new RuntimeException("App must declare the file provider authority as metadata in the manifest.");
        else
            return context;
    }

    private String getFileProviderAuthorityCached(Context context)
    {
        if(mFileProviderAuthorityCache != null)
        {
            return mFileProviderAuthorityCache;
        } else
        {
            mFileProviderAuthorityCache = getFileProviderAuthority(context);
            return mFileProviderAuthorityCache;
        }
    }

    private String getMiddlewarePackageCached(Context context)
    {
        if(mMiddlewarePackageNameCache == null)
            mMiddlewarePackageNameCache = MbmsUtils.getMiddlewareServiceInfo(context, "android.telephony.action.EmbmsDownload").packageName;
        return mMiddlewarePackageNameCache;
    }

    private static boolean manualMove(File file, File file1)
    {
        Object obj;
        Object obj1;
        Object obj2;
        StringBuilder stringbuilder;
        File file2;
        byte abyte0[];
        Object obj4;
        obj = null;
        obj1 = null;
        obj2 = null;
        stringbuilder = null;
        file2 = null;
        abyte0 = obj;
        obj4 = obj2;
        if(file1.exists())
            break MISSING_BLOCK_LABEL_39;
        abyte0 = obj;
        obj4 = obj2;
        file1.createNewFile();
        abyte0 = obj;
        obj4 = obj2;
        Object obj5 = JVM INSTR new #323 <Class FileInputStream>;
        abyte0 = obj;
        obj4 = obj2;
        ((FileInputStream) (obj5)).FileInputStream(file);
        try
        {
            file = JVM INSTR new #328 <Class FileOutputStream>;
            file.FileOutputStream(file1);
        }
        // Misplaced declaration of an exception variable
        catch(File file)
        {
            Object obj3 = obj5;
            obj5 = file;
            file = ((File) (obj3));
            continue; /* Loop/switch isn't completed */
        }
        abyte0 = new byte[2048];
        int i;
        do
        {
            i = ((InputStream) (obj5)).read(abyte0);
            file.write(abyte0, 0, i);
        } while(i > 0);
        if(obj5 == null)
            break MISSING_BLOCK_LABEL_113;
        ((InputStream) (obj5)).close();
        if(file != null)
            try
            {
                file.close();
            }
            // Misplaced declaration of an exception variable
            catch(File file)
            {
                Log.w("MbmsDownloadReceiver", (new StringBuilder()).append("Error closing streams: ").append(file).toString());
            }
        return true;
        obj5;
        file = obj1;
_L4:
        abyte0 = file;
        obj4 = file2;
        stringbuilder = JVM INSTR new #194 <Class StringBuilder>;
        abyte0 = file;
        obj4 = file2;
        stringbuilder.StringBuilder();
        abyte0 = file;
        obj4 = file2;
        Log.w("MbmsDownloadReceiver", stringbuilder.append("Manual file move failed due to exception ").append(obj5).toString());
        abyte0 = file;
        obj4 = file2;
        if(!file1.exists())
            break MISSING_BLOCK_LABEL_236;
        abyte0 = file;
        obj4 = file2;
        file1.delete();
        if(file == null)
            break MISSING_BLOCK_LABEL_244;
        file.close();
        if(file2 != null)
            try
            {
                file2.close();
            }
            // Misplaced declaration of an exception variable
            catch(File file)
            {
                Log.w("MbmsDownloadReceiver", (new StringBuilder()).append("Error closing streams: ").append(file).toString());
            }
        return false;
        file;
_L2:
        if(abyte0 == null)
            break MISSING_BLOCK_LABEL_297;
        abyte0.close();
        if(obj4 != null)
            try
            {
                ((OutputStream) (obj4)).close();
            }
            // Misplaced declaration of an exception variable
            catch(File file1)
            {
                Log.w("MbmsDownloadReceiver", (new StringBuilder()).append("Error closing streams: ").append(file1).toString());
            }
        throw file;
        file;
        abyte0 = ((byte []) (obj5));
        obj4 = stringbuilder;
        continue; /* Loop/switch isn't completed */
        file1;
        abyte0 = ((byte []) (obj5));
        obj4 = file;
        file = file1;
        if(true) goto _L2; else goto _L1
_L1:
        break MISSING_BLOCK_LABEL_73;
        IOException ioexception;
        ioexception;
        file2 = file;
        file = ((File) (obj5));
        obj5 = ioexception;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void moveDownloadedFile(Context context, Intent intent)
    {
        DownloadRequest downloadrequest = (DownloadRequest)intent.getParcelableExtra("android.telephony.extra.MBMS_DOWNLOAD_REQUEST");
        Intent intent1 = downloadrequest.getIntentForApp();
        if(intent1 == null)
        {
            Log.i("MbmsDownloadReceiver", "Malformed app notification intent");
            setResultCode(6);
            return;
        }
        int i = intent.getIntExtra("android.telephony.extra.MBMS_DOWNLOAD_RESULT", 2);
        intent1.putExtra("android.telephony.extra.MBMS_DOWNLOAD_RESULT", i);
        if(i != 1)
        {
            Log.i("MbmsDownloadReceiver", "Download request indicated a failed download. Aborting.");
            context.sendBroadcast(intent1);
            return;
        }
        Uri uri = (Uri)intent.getParcelableExtra("android.telephony.mbms.extra.FINAL_URI");
        if(!verifyTempFilePath(context, downloadrequest.getFileServiceId(), uri))
        {
            Log.w("MbmsDownloadReceiver", (new StringBuilder()).append("Download result specified an invalid temp file ").append(uri).toString());
            setResultCode(4);
            return;
        }
        intent = (FileInfo)intent.getParcelableExtra("android.telephony.extra.MBMS_FILE_INFO");
        Path path = FileSystems.getDefault().getPath(MbmsTempFileProvider.getEmbmsTempFileDir(context).getPath(), new String[] {
            "staged_completed_files"
        });
        try
        {
            uri = stageTempFile(uri, path);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            Log.w("MbmsDownloadReceiver", "Failed to move temp file to final destination");
            setResultCode(4);
            return;
        }
        intent1.putExtra("android.telephony.extra.MBMS_COMPLETED_FILE_URI", uri);
        intent1.putExtra("android.telephony.extra.MBMS_FILE_INFO", intent);
        intent1.putExtra("android.telephony.extra.MBMS_DOWNLOAD_REQUEST", downloadrequest);
        context.sendBroadcast(intent1);
        setResultCode(0);
    }

    private static Uri stageTempFile(Uri uri, Path path)
        throws IOException
    {
        if(!"file".equals(uri.getScheme()))
        {
            Log.w("MbmsDownloadReceiver", (new StringBuilder()).append("Moving source uri ").append(uri).append(" does not have a file scheme").toString());
            return null;
        }
        uri = FileSystems.getDefault().getPath(uri.getPath(), new String[0]);
        if(!Files.isDirectory(path, new LinkOption[0]))
            Files.createDirectory(path, new FileAttribute[0]);
        return Uri.fromFile(Files.move(uri, path.resolve(uri.getFileName()), new CopyOption[0]).toFile());
    }

    private boolean verifyIntentContents(Context context, Intent intent)
    {
        if("android.telephony.mbms.action.DOWNLOAD_RESULT_INTERNAL".equals(intent.getAction()))
        {
            if(!intent.hasExtra("android.telephony.extra.MBMS_DOWNLOAD_RESULT"))
            {
                Log.w("MbmsDownloadReceiver", "Download result did not include a result code. Ignoring.");
                return false;
            }
            if(1 != intent.getIntExtra("android.telephony.extra.MBMS_DOWNLOAD_RESULT", 2))
                return true;
            if(!intent.hasExtra("android.telephony.extra.MBMS_DOWNLOAD_REQUEST"))
            {
                Log.w("MbmsDownloadReceiver", "Download result did not include the associated request. Ignoring.");
                return false;
            }
            if(!intent.hasExtra("android.telephony.mbms.extra.TEMP_FILE_ROOT"))
            {
                Log.w("MbmsDownloadReceiver", "Download result did not include the temp file root. Ignoring.");
                return false;
            }
            if(!intent.hasExtra("android.telephony.extra.MBMS_FILE_INFO"))
            {
                Log.w("MbmsDownloadReceiver", "Download result did not include the associated file info. Ignoring.");
                return false;
            }
            if(!intent.hasExtra("android.telephony.mbms.extra.FINAL_URI"))
            {
                Log.w("MbmsDownloadReceiver", "Download result did not include the path to the final temp file. Ignoring.");
                return false;
            }
            intent = (DownloadRequest)intent.getParcelableExtra("android.telephony.extra.MBMS_DOWNLOAD_REQUEST");
            String s = (new StringBuilder()).append(intent.getHash()).append(".download_token").toString();
            context = new File(MbmsUtils.getEmbmsTempFileDirForService(context, intent.getFileServiceId()), s);
            if(!context.exists())
            {
                Log.w("MbmsDownloadReceiver", (new StringBuilder()).append("Supplied download request does not match a token that we have. Expected ").append(context).toString());
                return false;
            }
        } else
        if("android.telephony.mbms.action.FILE_DESCRIPTOR_REQUEST".equals(intent.getAction()))
        {
            if(!intent.hasExtra("android.telephony.mbms.extra.SERVICE_ID"))
            {
                Log.w("MbmsDownloadReceiver", "Temp file request did not include the associated service id. Ignoring.");
                return false;
            }
            if(!intent.hasExtra("android.telephony.mbms.extra.TEMP_FILE_ROOT"))
            {
                Log.w("MbmsDownloadReceiver", "Download result did not include the temp file root. Ignoring.");
                return false;
            }
        } else
        if("android.telephony.mbms.action.CLEANUP".equals(intent.getAction()))
        {
            if(!intent.hasExtra("android.telephony.mbms.extra.SERVICE_ID"))
            {
                Log.w("MbmsDownloadReceiver", "Cleanup request did not include the associated service id. Ignoring.");
                return false;
            }
            if(!intent.hasExtra("android.telephony.mbms.extra.TEMP_FILE_ROOT"))
            {
                Log.w("MbmsDownloadReceiver", "Cleanup request did not include the temp file root. Ignoring.");
                return false;
            }
            if(!intent.hasExtra("android.telephony.mbms.extra.TEMP_FILES_IN_USE"))
            {
                Log.w("MbmsDownloadReceiver", "Cleanup request did not include the list of temp files in use. Ignoring.");
                return false;
            }
        }
        return true;
    }

    private static boolean verifyTempFilePath(Context context, String s, Uri uri)
    {
        if(!"file".equals(uri.getScheme()))
        {
            Log.w("MbmsDownloadReceiver", (new StringBuilder()).append("Uri ").append(uri).append(" does not have a file scheme").toString());
            return false;
        }
        String s1 = uri.getSchemeSpecificPart();
        uri = new File(s1);
        if(!uri.exists())
        {
            Log.w("MbmsDownloadReceiver", (new StringBuilder()).append("File at ").append(s1).append(" does not exist.").toString());
            return false;
        }
        return MbmsUtils.isContainedIn(MbmsUtils.getEmbmsTempFileDirForService(context, s), uri);
    }

    public void onReceive(Context context, Intent intent)
    {
        if(!verifyIntentContents(context, intent))
        {
            setResultCode(2);
            return;
        }
        if(!Objects.equals(intent.getStringExtra("android.telephony.mbms.extra.TEMP_FILE_ROOT"), MbmsTempFileProvider.getEmbmsTempFileDir(context).getPath()))
        {
            setResultCode(3);
            return;
        }
        if("android.telephony.mbms.action.DOWNLOAD_RESULT_INTERNAL".equals(intent.getAction()))
        {
            moveDownloadedFile(context, intent);
            cleanupPostMove(context, intent);
        } else
        if("android.telephony.mbms.action.FILE_DESCRIPTOR_REQUEST".equals(intent.getAction()))
            generateTempFiles(context, intent);
        else
        if("android.telephony.mbms.action.CLEANUP".equals(intent.getAction()))
            cleanupTempFiles(context, intent);
        else
            setResultCode(1);
    }

    public static final String DOWNLOAD_TOKEN_SUFFIX = ".download_token";
    private static final String LOG_TAG = "MbmsDownloadReceiver";
    private static final int MAX_TEMP_FILE_RETRIES = 5;
    public static final String MBMS_FILE_PROVIDER_META_DATA_KEY = "mbms-file-provider-authority";
    public static final int RESULT_APP_NOTIFICATION_ERROR = 6;
    public static final int RESULT_BAD_TEMP_FILE_ROOT = 3;
    public static final int RESULT_DOWNLOAD_FINALIZATION_ERROR = 4;
    public static final int RESULT_INVALID_ACTION = 1;
    public static final int RESULT_MALFORMED_INTENT = 2;
    public static final int RESULT_OK = 0;
    public static final int RESULT_TEMP_FILE_GENERATION_ERROR = 5;
    private static final String TEMP_FILE_STAGING_LOCATION = "staged_completed_files";
    private static final String TEMP_FILE_SUFFIX = ".embms.temp";
    private String mFileProviderAuthorityCache;
    private String mMiddlewarePackageNameCache;
}
