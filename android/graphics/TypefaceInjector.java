// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;

import android.util.Log;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Map;

// Referenced classes of package android.graphics:
//            FontFamily

public class TypefaceInjector
{

    public TypefaceInjector()
    {
    }

    public static boolean addFontToFamily(FontFamily fontfamily, android.text.FontConfig.Font font, Map map)
    {
        String s;
        Object obj;
        Object obj1;
        s = (new StringBuilder()).append("/data/system/theme/fonts/").append(font.getFontName()).toString();
        if(!(new File(s)).exists())
            return false;
        obj = (ByteBuffer)map.get(s);
        obj1 = obj;
        if(obj != null) goto _L2; else goto _L1
_L1:
        Object obj2;
        Object obj3;
        obj2 = null;
        obj = null;
        obj3 = null;
        obj1 = JVM INSTR new #56  <Class FileInputStream>;
        ((FileInputStream) (obj1)).FileInputStream(s);
        obj = ((FileInputStream) (obj1)).getChannel();
        long l = ((FileChannel) (obj)).size();
        obj = ((FileChannel) (obj)).map(java.nio.channels.FileChannel.MapMode.READ_ONLY, 0L, l);
        map.put(s, obj);
        map = obj2;
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_133;
        ((FileInputStream) (obj1)).close();
        map = obj2;
_L4:
        obj1 = obj;
        if(map == null) goto _L2; else goto _L3
_L3:
        try
        {
            throw map;
        }
        // Misplaced declaration of an exception variable
        catch(FontFamily fontfamily) { }
_L5:
        Log.e("TypefaceInjetor", (new StringBuilder()).append("Error mapping font file ").append(s).toString());
        return false;
        map;
          goto _L4
        font;
        fontfamily = obj3;
_L9:
        throw font;
        obj1;
        map = fontfamily;
        fontfamily = font;
        font = ((android.text.FontConfig.Font) (obj1));
_L8:
        obj1 = fontfamily;
        if(map == null)
            break MISSING_BLOCK_LABEL_204;
        map.close();
        obj1 = fontfamily;
_L6:
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_246;
        try
        {
            throw obj1;
        }
        // Misplaced declaration of an exception variable
        catch(FontFamily fontfamily) { }
          goto _L5
        map;
label0:
        {
            if(fontfamily != null)
                break label0;
            obj1 = map;
        }
          goto _L6
        obj1 = fontfamily;
        if(fontfamily == map) goto _L6; else goto _L7
_L7:
        fontfamily.addSuppressed(map);
        obj1 = fontfamily;
          goto _L6
        throw font;
_L2:
        int i = font.getTtcIndex();
        map = font.getAxes();
        int j = font.getWeight();
        int k;
        if(font.isItalic())
            k = 1;
        else
            k = 0;
        if(!fontfamily.addFontFromBuffer(((ByteBuffer) (obj1)), i, map, j, k))
        {
            Log.e("TypefaceInjetor", (new StringBuilder()).append("Error creating font ").append(s).append("#").append(font.getTtcIndex()).toString());
            return false;
        } else
        {
            return true;
        }
        font;
        fontfamily = null;
        map = ((Map) (obj));
          goto _L8
        font;
        map = ((Map) (obj1));
        fontfamily = null;
          goto _L8
        font;
        fontfamily = ((FontFamily) (obj1));
          goto _L9
    }

    private static final String TAG = "TypefaceInjetor";
}
