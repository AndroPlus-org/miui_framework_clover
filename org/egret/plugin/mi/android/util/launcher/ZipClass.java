// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.egret.plugin.mi.android.util.launcher;

import android.util.Log;
import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

// Referenced classes of package org.egret.plugin.mi.android.util.launcher:
//            ExecutorLab

public class ZipClass
{
    public static interface OnZipListener
    {

        public abstract void onError(String s);

        public abstract void onFileProgress(int i, int j);

        public abstract void onProgress(int i, int j);

        public abstract void onSuccess();
    }


    public ZipClass()
    {
    }

    private boolean doUnzip(File file, File file1, OnZipListener onziplistener)
    {
        Object obj;
        Object obj1;
        Enumeration enumeration;
        Object obj2;
        Object obj3;
        byte abyte0[];
        Object obj4;
        Object obj5;
        Object obj6;
        Object obj7;
        Object obj8;
        Object obj9;
        Object obj10;
        Object obj11;
        if(file == null || file1 == null)
        {
            Log.e("ZipClass", "src or dstRoot may be null");
            return false;
        }
        if(!file1.exists() && file1.mkdirs() ^ true)
        {
            Log.e("ZipClass", (new StringBuilder()).append("fail to mkdir ").append(file1.getAbsolutePath()).toString());
            if(onziplistener != null)
                onziplistener.onError((new StringBuilder()).append("fail to mkdir ").append(file1.getAbsolutePath()).toString());
            return false;
        }
        obj = null;
        obj1 = null;
        enumeration = null;
        obj2 = null;
        obj3 = null;
        abyte0 = null;
        obj4 = null;
        obj5 = null;
        obj6 = null;
        obj7 = null;
        obj8 = null;
        obj9 = obj3;
        obj10 = obj6;
        obj11 = obj;
        Object obj12 = JVM INSTR new #62  <Class ZipFile>;
        obj9 = obj3;
        obj10 = obj6;
        obj11 = obj;
        ((ZipFile) (obj12)).ZipFile(file);
        obj9 = enumeration;
        obj10 = obj8;
        obj1 = abyte0;
        obj11 = obj7;
        int i = ((ZipFile) (obj12)).size();
        int j;
        j = 0;
        obj9 = enumeration;
        obj10 = obj8;
        obj1 = abyte0;
        obj11 = obj7;
        enumeration = ((ZipFile) (obj12)).entries();
        obj9 = null;
_L10:
        obj11 = obj4;
        obj10 = obj4;
        if(!enumeration.hasMoreElements()) goto _L2; else goto _L1
_L1:
        if(onziplistener == null) goto _L4; else goto _L3
_L3:
        obj11 = obj4;
        obj10 = obj4;
        if(!(ExecutorLab.getInstance().isRunning() ^ true)) goto _L4; else goto _L5
_L5:
        obj11 = obj4;
        obj10 = obj4;
        onziplistener.onError("zip thread is cancelled");
        if(obj12 == null)
            break MISSING_BLOCK_LABEL_283;
        ((ZipFile) (obj12)).close();
        if(obj4 == null)
            break MISSING_BLOCK_LABEL_293;
        ((InputStream) (obj4)).close();
        if(obj9 == null)
            break MISSING_BLOCK_LABEL_303;
        ((FileOutputStream) (obj9)).close();
_L6:
        return false;
        file;
        file.printStackTrace();
        if(onziplistener != null)
            onziplistener.onError(file.toString());
        if(true) goto _L6; else goto _L4
_L4:
        int k;
        k = j + 1;
        if(onziplistener == null)
            break MISSING_BLOCK_LABEL_355;
        obj11 = obj4;
        obj10 = obj4;
        onziplistener.onProgress(k, i);
        obj11 = obj4;
        obj10 = obj4;
        obj8 = (ZipEntry)enumeration.nextElement();
        obj11 = obj4;
        obj10 = obj4;
        obj1 = JVM INSTR new #33  <Class File>;
        obj11 = obj4;
        obj10 = obj4;
        ((File) (obj1)).File(file1, ((ZipEntry) (obj8)).getName());
        obj11 = obj4;
        obj10 = obj4;
        if(!((ZipEntry) (obj8)).isDirectory()) goto _L8; else goto _L7
_L7:
        j = k;
        obj11 = obj4;
        obj10 = obj4;
        if(((File) (obj1)).mkdirs()) goto _L10; else goto _L9
_L9:
        obj11 = obj4;
        obj10 = obj4;
        file = JVM INSTR new #42  <Class StringBuilder>;
        obj11 = obj4;
        obj10 = obj4;
        file.StringBuilder();
        obj11 = obj4;
        obj10 = obj4;
        Log.e("ZipClass", file.append("fail to mkdir ").append(((File) (obj1)).getAbsolutePath()).toString());
        if(onziplistener == null)
            break MISSING_BLOCK_LABEL_557;
        obj11 = obj4;
        obj10 = obj4;
        file = JVM INSTR new #42  <Class StringBuilder>;
        obj11 = obj4;
        obj10 = obj4;
        file.StringBuilder();
        obj11 = obj4;
        obj10 = obj4;
        onziplistener.onError(file.append("fail to mkdir ").append(((File) (obj1)).getAbsolutePath()).toString());
        if(obj12 == null)
            break MISSING_BLOCK_LABEL_567;
        ((ZipFile) (obj12)).close();
        if(obj4 == null)
            break MISSING_BLOCK_LABEL_577;
        ((InputStream) (obj4)).close();
        if(obj9 == null)
            break MISSING_BLOCK_LABEL_587;
        ((FileOutputStream) (obj9)).close();
_L11:
        return false;
        file;
        file.printStackTrace();
        if(onziplistener != null)
            onziplistener.onError(file.toString());
        if(true) goto _L11; else goto _L8
_L8:
        obj11 = obj4;
        obj10 = obj4;
        int l = (int)((ZipEntry) (obj8)).getSize();
        j = 0;
        obj11 = obj4;
        obj10 = obj4;
        obj4 = ((ZipFile) (obj12)).getInputStream(((ZipEntry) (obj8)));
        obj11 = obj4;
        obj10 = obj4;
        obj8 = new FileOutputStream(((File) (obj1)));
        obj9 = obj4;
        obj10 = obj8;
        obj1 = obj4;
        obj11 = obj8;
        abyte0 = new byte[1024];
_L19:
        obj9 = obj4;
        obj10 = obj8;
        obj1 = obj4;
        obj11 = obj8;
        int i1 = ((InputStream) (obj4)).read(abyte0, 0, 1024);
        if(i1 == -1) goto _L13; else goto _L12
_L12:
        if(onziplistener == null) goto _L15; else goto _L14
_L14:
        obj9 = obj4;
        obj10 = obj8;
        obj1 = obj4;
        obj11 = obj8;
        if(!(ExecutorLab.getInstance().isRunning() ^ true)) goto _L15; else goto _L16
_L16:
        obj9 = obj4;
        obj10 = obj8;
        obj1 = obj4;
        obj11 = obj8;
        onziplistener.onError("zip thread is cancelled");
        if(obj12 == null)
            break MISSING_BLOCK_LABEL_789;
        ((ZipFile) (obj12)).close();
        if(obj4 == null)
            break MISSING_BLOCK_LABEL_799;
        ((InputStream) (obj4)).close();
        if(obj8 == null)
            break MISSING_BLOCK_LABEL_809;
        ((FileOutputStream) (obj8)).close();
_L17:
        return false;
        file;
        file.printStackTrace();
        if(onziplistener != null)
            onziplistener.onError(file.toString());
        if(true) goto _L17; else goto _L15
_L15:
        obj9 = obj4;
        obj10 = obj8;
        obj1 = obj4;
        obj11 = obj8;
        ((FileOutputStream) (obj8)).write(abyte0, 0, i1);
        i1 = j + i1;
        j = i1;
        if(onziplistener == null) goto _L19; else goto _L18
_L18:
        obj9 = obj4;
        obj10 = obj8;
        obj1 = obj4;
        obj11 = obj8;
        onziplistener.onFileProgress(i1, l);
        j = i1;
          goto _L19
        obj11;
        obj4 = obj12;
        file1 = ((File) (obj10));
        file = ((File) (obj9));
        obj12 = obj11;
_L26:
        obj9 = file;
        obj10 = file1;
        obj11 = obj4;
        ((IOException) (obj12)).printStackTrace();
        if(onziplistener == null)
            break MISSING_BLOCK_LABEL_963;
        obj9 = file;
        obj10 = file1;
        obj11 = obj4;
        onziplistener.onError(((IOException) (obj12)).toString());
        if(obj4 == null)
            break MISSING_BLOCK_LABEL_973;
        ((ZipFile) (obj4)).close();
        if(file == null)
            break MISSING_BLOCK_LABEL_981;
        file.close();
        if(file1 != null)
            try
            {
                file1.close();
            }
            // Misplaced declaration of an exception variable
            catch(File file)
            {
                file.printStackTrace();
                if(onziplistener != null)
                    onziplistener.onError(file.toString());
            }
_L20:
        return false;
_L13:
        obj9 = obj4;
        obj10 = obj8;
        obj1 = obj4;
        obj11 = obj8;
        ((FileOutputStream) (obj8)).close();
        obj9 = obj4;
        obj10 = obj8;
        obj1 = obj4;
        obj11 = obj8;
        ((InputStream) (obj4)).close();
        obj9 = obj8;
        j = k;
          goto _L10
_L2:
        obj11 = obj4;
        obj10 = obj4;
        ((ZipFile) (obj12)).close();
        obj11 = obj4;
        obj10 = obj4;
        file1 = JVM INSTR new #42  <Class StringBuilder>;
        obj11 = obj4;
        obj10 = obj4;
        file1.StringBuilder();
        obj11 = obj4;
        obj10 = obj4;
        Log.i("ZipClass", file1.append("success to unzip ").append(file.getAbsolutePath()).toString());
        if(onziplistener == null)
            break MISSING_BLOCK_LABEL_1129;
        obj11 = obj4;
        obj10 = obj4;
        onziplistener.onSuccess();
        if(obj12 == null)
            break MISSING_BLOCK_LABEL_1139;
        ((ZipFile) (obj12)).close();
        if(obj4 == null)
            break MISSING_BLOCK_LABEL_1149;
        ((InputStream) (obj4)).close();
        if(obj9 == null)
            break MISSING_BLOCK_LABEL_1159;
        ((FileOutputStream) (obj9)).close();
_L21:
        return true;
        file;
        file.printStackTrace();
        if(onziplistener != null)
            onziplistener.onError(file.toString());
        if(true) goto _L21; else goto _L20
        file;
_L25:
        if(obj11 == null)
            break MISSING_BLOCK_LABEL_1216;
        ((ZipFile) (obj11)).close();
        if(obj9 == null)
            break MISSING_BLOCK_LABEL_1226;
        ((InputStream) (obj9)).close();
        if(obj10 == null)
            break MISSING_BLOCK_LABEL_1236;
        ((FileOutputStream) (obj10)).close();
_L23:
        throw file;
        file1;
        file1.printStackTrace();
        if(onziplistener != null)
            onziplistener.onError(file1.toString());
        if(true) goto _L23; else goto _L22
_L22:
        file;
        obj9 = obj1;
        obj10 = obj11;
        obj11 = obj12;
        continue; /* Loop/switch isn't completed */
        file;
        obj10 = obj9;
        obj9 = obj11;
        obj11 = obj12;
        if(true) goto _L25; else goto _L24
_L24:
        obj12;
        file = obj2;
        file1 = obj5;
        obj4 = obj1;
          goto _L26
        file;
        file1 = ((File) (obj9));
        obj4 = obj12;
        obj12 = file;
        file = ((File) (obj10));
          goto _L26
    }

    public void unzip(File file, File file1, OnZipListener onziplistener)
    {
        if(onziplistener == null)
            Log.e("ZipClass", "listener is null");
        doUnzip(file, file1, onziplistener);
    }

    public boolean unzip(File file, File file1)
    {
        return doUnzip(file, file1, null);
    }

    private static final int BUFFER_SIZE = 1024;
    private static final String TAG = "ZipClass";
}
