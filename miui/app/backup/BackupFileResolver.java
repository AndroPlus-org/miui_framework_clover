// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.app.backup;

import android.content.Context;
import android.content.pm.*;
import android.text.TextUtils;
import android.util.Log;
import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class BackupFileResolver
{
    public static class BackupFileMiuiHeader
    {

        public String apkName;
        public String encryptedPwd;
        public int featureId;
        public boolean isEncrypted;
        public String packageName;
        public int version;

        public BackupFileMiuiHeader()
        {
        }
    }


    public BackupFileResolver()
    {
    }

    public static BackupFileMiuiHeader getMiuiHeader(File file)
    {
        Object obj;
        Object obj1;
        Object obj2;
        Object obj3;
        obj = null;
        obj1 = null;
        obj2 = null;
        obj3 = obj1;
        Object obj5 = JVM INSTR new #24  <Class FileInputStream>;
        obj3 = obj1;
        ((FileInputStream) (obj5)).FileInputStream(file);
        file = readMiuiHeader(((InputStream) (obj5)));
        if(obj5 != null)
            try
            {
                ((InputStream) (obj5)).close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj3)
            {
                Log.e("Backup:BackupFileResolver", "IOException", ((Throwable) (obj3)));
            }
        obj3 = file;
_L2:
        return ((BackupFileMiuiHeader) (obj3));
        obj5;
        file = obj2;
_L5:
        obj3 = file;
        Log.e("Backup:BackupFileResolver", "IOException", ((Throwable) (obj5)));
        obj3 = obj;
        if(file == null) goto _L2; else goto _L1
_L1:
        file.close();
        obj3 = obj;
          goto _L2
        file;
        Log.e("Backup:BackupFileResolver", "IOException", file);
        obj3 = obj;
          goto _L2
        file;
_L4:
        if(obj3 != null)
            try
            {
                ((InputStream) (obj3)).close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj3)
            {
                Log.e("Backup:BackupFileResolver", "IOException", ((Throwable) (obj3)));
            }
        throw file;
        file;
        obj3 = obj5;
        if(true) goto _L4; else goto _L3
_L3:
        file;
        Object obj4 = obj5;
        obj5 = file;
        file = ((File) (obj4));
          goto _L5
    }

    private static String readLine(InputStream inputstream)
        throws IOException
    {
        ArrayList arraylist = new ArrayList();
        do
        {
            int i = inputstream.read();
            if(i < 0 || i == 10)
            {
                inputstream = new byte[arraylist.size()];
                for(i = 0; i < arraylist.size(); i++)
                    inputstream[i] = ((Byte)arraylist.get(i)).byteValue();

                break;
            }
            arraylist.add(Byte.valueOf((byte)i));
        } while(true);
        return new String(inputstream, Charset.forName("UTF-8"));
    }

    public static BackupFileMiuiHeader readMiuiHeader(InputStream inputstream)
    {
        Object obj = null;
        StringBuilder stringbuilder;
        stringbuilder = JVM INSTR new #92  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        BackupFileMiuiHeader backupfilemiuiheader = obj;
        if(!"MIUI BACKUP\n".equals(stringbuilder.append(readLine(inputstream)).append("\n").toString())) goto _L2; else goto _L1
_L1:
        backupfilemiuiheader = JVM INSTR new #6   <Class BackupFileResolver$BackupFileMiuiHeader>;
        backupfilemiuiheader.BackupFileMiuiHeader();
        backupfilemiuiheader.version = Integer.parseInt(readLine(inputstream));
        backupfilemiuiheader.version;
        JVM INSTR tableswitch 2 2: default 76
    //                   2 80;
           goto _L3 _L4
_L3:
        backupfilemiuiheader = obj;
_L2:
        return backupfilemiuiheader;
_L4:
        try
        {
            String as[] = readLine(inputstream).split(" ", 2);
            backupfilemiuiheader.packageName = as[0];
            if(as.length > 1)
                backupfilemiuiheader.apkName = as[1];
            backupfilemiuiheader.featureId = Integer.valueOf(readLine(inputstream)).intValue();
            backupfilemiuiheader.isEncrypted = "1".equals(readLine(inputstream));
            if(backupfilemiuiheader.isEncrypted)
                backupfilemiuiheader.encryptedPwd = readLine(inputstream);
        }
        // Misplaced declaration of an exception variable
        catch(InputStream inputstream)
        {
            Log.e("Backup:BackupFileResolver", "Exception", inputstream);
            backupfilemiuiheader = obj;
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    public static void writeMiuiHeader(OutputStream outputstream, Context context, String s, int i, String s1)
        throws IOException
    {
        boolean flag;
        StringBuilder stringbuilder;
        Object obj;
        flag = TextUtils.isEmpty(s1);
        stringbuilder = new StringBuilder();
        obj = context.getPackageManager();
        context = null;
        obj = ((PackageManager) (obj)).getPackageInfo(s, 0).applicationInfo.loadLabel(((PackageManager) (obj))).toString().replace('\n', ' ');
        context = ((Context) (obj));
_L1:
        stringbuilder.append("MIUI BACKUP\n");
        stringbuilder.append(String.valueOf(2)).append("\n");
        android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
        if(TextUtils.isEmpty(context))
            stringbuilder.append(s).append("\n");
        else
            stringbuilder.append(s).append(" ").append(context).append("\n");
        stringbuilder.append(String.valueOf(i)).append("\n");
        if(flag ^ true)
        {
            stringbuilder.append(String.valueOf(1)).append("\n");
            stringbuilder.append(s1).append("\n");
        } else
        {
            stringbuilder.append(String.valueOf(0)).append("\n");
        }
        outputstream.write(stringbuilder.toString().getBytes());
        return;
        namenotfoundexception;
        namenotfoundexception.printStackTrace();
          goto _L1
    }

    private static final String TAG = "Backup:BackupFileResolver";
}
