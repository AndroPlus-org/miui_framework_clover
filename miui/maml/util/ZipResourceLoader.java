// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.util;

import android.text.TextUtils;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import miui.maml.ResourceLoader;

public class ZipResourceLoader extends ResourceLoader
{

    public ZipResourceLoader(String s)
    {
        this(s, null, null);
    }

    public ZipResourceLoader(String s, String s1)
    {
        this(s, s1, null);
    }

    public ZipResourceLoader(String s, String s1, String s2)
    {
        mLock = new Object();
        if(TextUtils.isEmpty(s))
            throw new IllegalArgumentException("empty zip path");
        mResourcePath = s;
        s = s1;
        if(s1 == null)
            s = "";
        mInnerPath = s;
        if(s2 != null)
            mManifestName = s2;
        init();
    }

    private void close()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        ZipFile zipfile = mZipFile;
        if(zipfile == null)
            break MISSING_BLOCK_LABEL_28;
        Exception exception;
        try
        {
            mZipFile.close();
        }
        catch(IOException ioexception) { }
        mZipFile = null;
        obj;
        JVM INSTR monitorexit ;
        return;
        exception;
        throw exception;
    }

    protected void finalize()
        throws Throwable
    {
        close();
        super.finalize();
    }

    public void finish()
    {
        close();
        super.finish();
    }

    public InputStream getInputStream(String s, long al[])
    {
        if(mZipFile == null || s == null)
            return null;
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(mZipFile == null)
            break MISSING_BLOCK_LABEL_108;
        ZipFile zipfile = mZipFile;
        StringBuilder stringbuilder = JVM INSTR new #76  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        s = zipfile.getEntry(stringbuilder.append(mInnerPath).append(s).toString());
        if(s != null)
            break MISSING_BLOCK_LABEL_73;
        obj;
        JVM INSTR monitorexit ;
        return null;
        if(al == null)
            break MISSING_BLOCK_LABEL_84;
        al[0] = s.getSize();
        s = mZipFile.getInputStream(s);
        obj;
        JVM INSTR monitorexit ;
        return s;
        s;
        Log.d("ZipResourceLoader", s.toString());
        obj;
        JVM INSTR monitorexit ;
        return null;
        s;
        throw s;
    }

    public void init()
    {
        super.init();
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        ZipFile zipfile = mZipFile;
        if(zipfile != null)
            break MISSING_BLOCK_LABEL_37;
        ZipFile zipfile1 = JVM INSTR new #60  <Class ZipFile>;
        zipfile1.ZipFile(mResourcePath);
        mZipFile = zipfile1;
_L1:
        obj;
        JVM INSTR monitorexit ;
        return;
        Object obj1;
        obj1;
        ((IOException) (obj1)).printStackTrace();
        StringBuilder stringbuilder = JVM INSTR new #76  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.e("ZipResourceLoader", stringbuilder.append("fail to init zip file: ").append(mResourcePath).toString());
          goto _L1
        stringbuilder;
        throw stringbuilder;
    }

    public boolean resourceExists(String s)
    {
        boolean flag;
        flag = false;
        if(mZipFile == null || s == null)
            return false;
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        ZipFile zipfile = mZipFile;
        boolean flag1 = flag;
        if(zipfile == null) goto _L2; else goto _L1
_L1:
        if(s != null) goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        obj;
        JVM INSTR monitorexit ;
        return flag1;
_L4:
        ZipFile zipfile1 = mZipFile;
        StringBuilder stringbuilder = JVM INSTR new #76  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        s = zipfile1.getEntry(stringbuilder.append(mInnerPath).append(s).toString());
        flag1 = flag;
        if(s != null)
            flag1 = true;
        if(true) goto _L2; else goto _L5
_L5:
        s;
        throw s;
    }

    private static final String LOG_TAG = "ZipResourceLoader";
    private String mInnerPath;
    private Object mLock;
    private String mResourcePath;
    private ZipFile mZipFile;
}
