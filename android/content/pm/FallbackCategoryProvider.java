// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.SystemProperties;
import android.util.ArrayMap;
import android.util.Log;
import java.io.*;

public class FallbackCategoryProvider
{

    public FallbackCategoryProvider()
    {
    }

    public static int getFallbackCategory(String s)
    {
        return ((Integer)sFallbacks.getOrDefault(s, Integer.valueOf(-1))).intValue();
    }

    public static void loadFallbacks()
    {
        Object obj;
        Object obj1;
        Resources resources;
        Object obj3;
        Object obj4;
        obj = null;
        obj1 = null;
        sFallbacks.clear();
        if(SystemProperties.getBoolean("fw.ignore_fb_categories", false))
        {
            Log.d("FallbackCategoryProvider", "Ignoring fallback categories");
            return;
        }
        AssetManager assetmanager = new AssetManager();
        assetmanager.addAssetPath("/system/framework/framework-res.apk");
        resources = new Resources(assetmanager, null, null);
        obj3 = null;
        obj4 = null;
        Object obj2;
        obj2 = JVM INSTR new #79  <Class BufferedReader>;
        InputStreamReader inputstreamreader = JVM INSTR new #81  <Class InputStreamReader>;
        inputstreamreader.InputStreamReader(resources.openRawResource(0x1100004));
        ((BufferedReader) (obj2)).BufferedReader(inputstreamreader);
_L4:
        obj4 = ((BufferedReader) (obj2)).readLine();
        if(obj4 == null) goto _L2; else goto _L1
_L1:
        if(((String) (obj4)).charAt(0) == '#') goto _L4; else goto _L3
_L3:
        obj4 = ((String) (obj4)).split(",");
        if(obj4.length == 2)
            sFallbacks.put(obj4[0], Integer.valueOf(Integer.parseInt(obj4[1])));
          goto _L4
        obj4;
        obj = obj2;
        obj2 = obj4;
        obj4 = obj;
_L15:
        throw obj2;
        obj1;
        obj = obj2;
        obj2 = obj1;
_L13:
        obj1 = obj;
        if(obj4 == null)
            break MISSING_BLOCK_LABEL_179;
        ((BufferedReader) (obj4)).close();
        obj1 = obj;
_L11:
        if(obj1 == null)
            break; /* Loop/switch isn't completed */
        try
        {
            throw obj1;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj2) { }
_L9:
        Log.w("FallbackCategoryProvider", "Failed to read fallback categories", ((Throwable) (obj2)));
_L8:
        return;
_L2:
        obj4 = JVM INSTR new #125 <Class StringBuilder>;
        ((StringBuilder) (obj4)).StringBuilder();
        Log.d("FallbackCategoryProvider", ((StringBuilder) (obj4)).append("Found ").append(sFallbacks.size()).append(" fallback categories").toString());
        obj4 = obj;
        if(obj2 == null) goto _L6; else goto _L5
_L5:
        ((BufferedReader) (obj2)).close();
        obj4 = obj;
_L6:
        if(obj4 == null) goto _L8; else goto _L7
_L7:
        try
        {
            throw obj4;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj2) { }
          goto _L9
        obj4;
          goto _L6
        obj4;
        if(obj == null)
        {
            obj1 = obj4;
            continue; /* Loop/switch isn't completed */
        }
        obj1 = obj;
        if(obj == obj4)
            continue; /* Loop/switch isn't completed */
        ((Throwable) (obj)).addSuppressed(((Throwable) (obj4)));
        obj1 = obj;
        if(true) goto _L11; else goto _L10
_L10:
        throw obj2;
        obj2;
        obj4 = obj3;
        obj = obj1;
        continue; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        obj4 = obj2;
        obj2 = exception;
        exception = ((Exception) (obj1));
        if(true) goto _L13; else goto _L12
_L12:
        obj2;
        if(true) goto _L15; else goto _L14
_L14:
    }

    private static final String TAG = "FallbackCategoryProvider";
    private static final ArrayMap sFallbacks = new ArrayMap();

}
