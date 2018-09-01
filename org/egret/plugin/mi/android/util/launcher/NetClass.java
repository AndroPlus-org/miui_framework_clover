// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.egret.plugin.mi.android.util.launcher;

import android.util.Log;
import java.io.*;
import java.net.*;

// Referenced classes of package org.egret.plugin.mi.android.util.launcher:
//            ExecutorLab

public class NetClass
{
    public static interface OnNetListener
    {

        public abstract void onError(String s);

        public abstract void onProgress(int i, int j);

        public abstract void onSuccess(String s);
    }


    public NetClass()
    {
    }

    private void doRequest(String s, String s1, OutputStream outputstream, OnNetListener onnetlistener)
    {
        Object obj;
        HttpURLConnection httpurlconnection;
        HttpURLConnection httpurlconnection1;
        Object obj1;
        Object obj2;
        OutputStream outputstream1;
        Object obj3;
        Object obj4;
        String s2;
        OutputStream outputstream2;
        String s3;
        OutputStream outputstream3;
        if(s == null || outputstream == null)
        {
            Log.e("NetTool", "url, out may be null");
            return;
        }
        try
        {
            obj = new URL(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            s.printStackTrace();
            if(onnetlistener != null)
                onnetlistener.onError(s.toString());
            return;
        }
        httpurlconnection = null;
        httpurlconnection1 = null;
        obj1 = null;
        obj2 = null;
        outputstream1 = null;
        obj3 = null;
        obj4 = null;
        s2 = obj4;
        outputstream2 = obj1;
        s3 = obj3;
        outputstream3 = obj2;
        obj = (HttpURLConnection)((URL) (obj)).openConnection();
        if(obj != null)
            break MISSING_BLOCK_LABEL_239;
        if(onnetlistener == null)
            break MISSING_BLOCK_LABEL_184;
        httpurlconnection1 = ((HttpURLConnection) (obj));
        s2 = obj4;
        outputstream2 = obj1;
        httpurlconnection = ((HttpURLConnection) (obj));
        s3 = obj3;
        outputstream3 = obj2;
        s1 = JVM INSTR new #46  <Class StringBuilder>;
        httpurlconnection1 = ((HttpURLConnection) (obj));
        s2 = obj4;
        outputstream2 = obj1;
        httpurlconnection = ((HttpURLConnection) (obj));
        s3 = obj3;
        outputstream3 = obj2;
        s1.StringBuilder();
        httpurlconnection1 = ((HttpURLConnection) (obj));
        s2 = obj4;
        outputstream2 = obj1;
        httpurlconnection = ((HttpURLConnection) (obj));
        s3 = obj3;
        outputstream3 = obj2;
        onnetlistener.onError(s1.append("unable to open ").append(s).toString());
        if(obj == null)
            break MISSING_BLOCK_LABEL_194;
        ((HttpURLConnection) (obj)).disconnect();
_L1:
        return;
        s;
        s.printStackTrace();
        if(onnetlistener != null)
            onnetlistener.onError("fail to close");
          goto _L1
        httpurlconnection1 = ((HttpURLConnection) (obj));
        s2 = obj4;
        outputstream2 = obj1;
        httpurlconnection = ((HttpURLConnection) (obj));
        s3 = obj3;
        outputstream3 = obj2;
        ((HttpURLConnection) (obj)).setConnectTimeout(30000);
        if(s1 == null)
            break MISSING_BLOCK_LABEL_551;
        httpurlconnection1 = ((HttpURLConnection) (obj));
        s2 = obj4;
        outputstream2 = obj1;
        httpurlconnection = ((HttpURLConnection) (obj));
        s3 = obj3;
        outputstream3 = obj2;
        ((HttpURLConnection) (obj)).setDoOutput(true);
        httpurlconnection1 = ((HttpURLConnection) (obj));
        s2 = obj4;
        outputstream2 = obj1;
        httpurlconnection = ((HttpURLConnection) (obj));
        s3 = obj3;
        outputstream3 = obj2;
        outputstream1 = ((HttpURLConnection) (obj)).getOutputStream();
        if(outputstream1 != null)
            break MISSING_BLOCK_LABEL_489;
        if(onnetlistener == null)
            break MISSING_BLOCK_LABEL_446;
        httpurlconnection1 = ((HttpURLConnection) (obj));
        s2 = obj4;
        outputstream2 = outputstream1;
        httpurlconnection = ((HttpURLConnection) (obj));
        s3 = obj3;
        outputstream3 = outputstream1;
        s1 = JVM INSTR new #46  <Class StringBuilder>;
        httpurlconnection1 = ((HttpURLConnection) (obj));
        s2 = obj4;
        outputstream2 = outputstream1;
        httpurlconnection = ((HttpURLConnection) (obj));
        s3 = obj3;
        outputstream3 = outputstream1;
        s1.StringBuilder();
        httpurlconnection1 = ((HttpURLConnection) (obj));
        s2 = obj4;
        outputstream2 = outputstream1;
        httpurlconnection = ((HttpURLConnection) (obj));
        s3 = obj3;
        outputstream3 = outputstream1;
        onnetlistener.onError(s1.append("unable to open post: ").append(s).toString());
        if(obj == null)
            break MISSING_BLOCK_LABEL_456;
        ((HttpURLConnection) (obj)).disconnect();
        if(outputstream1 == null)
            break MISSING_BLOCK_LABEL_466;
        outputstream1.close();
_L2:
        return;
        s;
        s.printStackTrace();
        if(onnetlistener != null)
            onnetlistener.onError("fail to close");
          goto _L2
        httpurlconnection1 = ((HttpURLConnection) (obj));
        s2 = obj4;
        outputstream2 = outputstream1;
        httpurlconnection = ((HttpURLConnection) (obj));
        s3 = obj3;
        outputstream3 = outputstream1;
        outputstream1.write(s1.getBytes());
        httpurlconnection1 = ((HttpURLConnection) (obj));
        s2 = obj4;
        outputstream2 = outputstream1;
        httpurlconnection = ((HttpURLConnection) (obj));
        s3 = obj3;
        outputstream3 = outputstream1;
        outputstream1.close();
        httpurlconnection1 = ((HttpURLConnection) (obj));
        s2 = obj4;
        outputstream2 = outputstream1;
        httpurlconnection = ((HttpURLConnection) (obj));
        s3 = obj3;
        outputstream3 = outputstream1;
        if(((HttpURLConnection) (obj)).getResponseCode() == 200)
            break MISSING_BLOCK_LABEL_667;
        if(onnetlistener == null)
            break MISSING_BLOCK_LABEL_624;
        httpurlconnection1 = ((HttpURLConnection) (obj));
        s2 = obj4;
        outputstream2 = outputstream1;
        httpurlconnection = ((HttpURLConnection) (obj));
        s3 = obj3;
        outputstream3 = outputstream1;
        onnetlistener.onError("response code is HTTP_OK");
        if(obj == null)
            break MISSING_BLOCK_LABEL_634;
        ((HttpURLConnection) (obj)).disconnect();
        if(outputstream1 == null)
            break MISSING_BLOCK_LABEL_644;
        outputstream1.close();
_L3:
        return;
        s;
        s.printStackTrace();
        if(onnetlistener != null)
            onnetlistener.onError("fail to close");
          goto _L3
        httpurlconnection1 = ((HttpURLConnection) (obj));
        s2 = obj4;
        outputstream2 = outputstream1;
        httpurlconnection = ((HttpURLConnection) (obj));
        s3 = obj3;
        outputstream3 = outputstream1;
        int i = ((HttpURLConnection) (obj)).getContentLength();
        int j;
        j = 0;
        httpurlconnection1 = ((HttpURLConnection) (obj));
        s2 = obj4;
        outputstream2 = outputstream1;
        httpurlconnection = ((HttpURLConnection) (obj));
        s3 = obj3;
        outputstream3 = outputstream1;
        s = ((HttpURLConnection) (obj)).getInputStream();
        httpurlconnection1 = ((HttpURLConnection) (obj));
        s2 = s;
        outputstream2 = outputstream1;
        httpurlconnection = ((HttpURLConnection) (obj));
        s3 = s;
        outputstream3 = outputstream1;
        s1 = new byte[1024];
_L12:
        httpurlconnection1 = ((HttpURLConnection) (obj));
        s2 = s;
        outputstream2 = outputstream1;
        httpurlconnection = ((HttpURLConnection) (obj));
        s3 = s;
        outputstream3 = outputstream1;
        int k = s.read(s1);
        if(k <= 0) goto _L5; else goto _L4
_L4:
        if(onnetlistener == null) goto _L7; else goto _L6
_L6:
        httpurlconnection1 = ((HttpURLConnection) (obj));
        s2 = s;
        outputstream2 = outputstream1;
        httpurlconnection = ((HttpURLConnection) (obj));
        s3 = s;
        outputstream3 = outputstream1;
        boolean flag = ExecutorLab.getInstance().isRunning();
        if(!(flag ^ true)) goto _L7; else goto _L5
_L5:
        if(obj == null)
            break MISSING_BLOCK_LABEL_845;
        ((HttpURLConnection) (obj)).disconnect();
        if(outputstream1 == null)
            break MISSING_BLOCK_LABEL_855;
        outputstream1.close();
        if(s == null)
            break MISSING_BLOCK_LABEL_863;
        s.close();
_L8:
        return;
_L7:
        httpurlconnection1 = ((HttpURLConnection) (obj));
        s2 = s;
        outputstream2 = outputstream1;
        httpurlconnection = ((HttpURLConnection) (obj));
        s3 = s;
        outputstream3 = outputstream1;
        outputstream.write(s1, 0, k);
        k = j + k;
        j = k;
        if(onnetlistener == null)
            continue; /* Loop/switch isn't completed */
        httpurlconnection1 = ((HttpURLConnection) (obj));
        s2 = s;
        outputstream2 = outputstream1;
        httpurlconnection = ((HttpURLConnection) (obj));
        s3 = s;
        outputstream3 = outputstream1;
        onnetlistener.onProgress(k, i);
        j = k;
        continue; /* Loop/switch isn't completed */
        s;
        httpurlconnection = httpurlconnection1;
        s3 = s2;
        outputstream3 = outputstream2;
        s.printStackTrace();
        if(onnetlistener == null)
            break MISSING_BLOCK_LABEL_995;
        httpurlconnection = httpurlconnection1;
        s3 = s2;
        outputstream3 = outputstream2;
        onnetlistener.onError(s.toString());
        if(httpurlconnection1 == null)
            break MISSING_BLOCK_LABEL_1005;
        httpurlconnection1.disconnect();
        if(outputstream2 == null)
            break MISSING_BLOCK_LABEL_1015;
        outputstream2.close();
        if(s2 != null)
            try
            {
                s2.close();
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                s.printStackTrace();
                if(onnetlistener != null)
                    onnetlistener.onError("fail to close");
            }
          goto _L8
        s;
        s.printStackTrace();
        if(onnetlistener != null)
            onnetlistener.onError("fail to close");
          goto _L8
        s;
        if(httpurlconnection == null)
            break MISSING_BLOCK_LABEL_1083;
        httpurlconnection.disconnect();
        if(outputstream3 == null)
            break MISSING_BLOCK_LABEL_1093;
        outputstream3.close();
        if(s3 == null)
            break MISSING_BLOCK_LABEL_1103;
        s3.close();
_L10:
        throw s;
        s1;
        s1.printStackTrace();
        if(onnetlistener != null)
            onnetlistener.onError("fail to close");
        if(true) goto _L10; else goto _L9
_L9:
        if(true) goto _L12; else goto _L11
_L11:
    }

    private void request(String s, String s1, File file, OnNetListener onnetlistener)
    {
        if(file == null) goto _L2; else goto _L1
_L1:
        s1 = JVM INSTR new #141 <Class FileOutputStream>;
        s1.FileOutputStream(file);
        doRequest(s, null, s1, onnetlistener);
        s1.close();
        if(onnetlistener == null) goto _L4; else goto _L3
_L3:
        if(ExecutorLab.getInstance().isRunning()) goto _L6; else goto _L5
_L5:
        onnetlistener.onError("net thread is cancelled");
_L4:
        return;
_L6:
        try
        {
            onnetlistener.onSuccess(null);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            s.printStackTrace();
            if(onnetlistener != null)
                onnetlistener.onError(s.toString());
        }
          goto _L4
_L2:
        file = JVM INSTR new #154 <Class ByteArrayOutputStream>;
        file.ByteArrayOutputStream();
        doRequest(s, s1, file, onnetlistener);
        file.close();
        if(onnetlistener == null) goto _L4; else goto _L7
_L7:
label0:
        {
            if(ExecutorLab.getInstance().isRunning())
                break label0;
            onnetlistener.onError("net thread is cancelled");
        }
          goto _L4
        s = JVM INSTR new #91  <Class String>;
        s.String(file.toByteArray());
        onnetlistener.onSuccess(s);
          goto _L4
    }

    public String getRequest(String s)
    {
        return postRequest(s, null);
    }

    public void getRequest(String s, OnNetListener onnetlistener)
    {
        postRequest(s, null, onnetlistener);
    }

    public String postRequest(String s, String s1)
    {
        if(s == null)
            return null;
        try
        {
            ByteArrayOutputStream bytearrayoutputstream = JVM INSTR new #154 <Class ByteArrayOutputStream>;
            bytearrayoutputstream.ByteArrayOutputStream();
            doRequest(s, s1, bytearrayoutputstream, null);
            bytearrayoutputstream.close();
            s = new String(bytearrayoutputstream.toByteArray());
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            s.printStackTrace();
            return null;
        }
        return s;
    }

    public void postRequest(String s, String s1, OnNetListener onnetlistener)
    {
        if(s == null)
        {
            return;
        } else
        {
            request(s, s1, null, onnetlistener);
            return;
        }
    }

    public void writeResponseToFile(String s, File file, OnNetListener onnetlistener)
    {
        if(s == null || file == null)
        {
            return;
        } else
        {
            request(s, null, file, onnetlistener);
            return;
        }
    }

    private static final int BUFFER_SIZE = 1024;
    private static final int TIME_OUT = 30000;
}
