// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;

import java.io.File;
import miui.util.FeatureParser;

// Referenced classes of package android.graphics:
//            Typeface

public class MiuiTypeface
{

    public MiuiTypeface()
    {
    }

    public static Typeface getChangedTypeface(Typeface typeface, int i, int j)
    {
        Typeface typeface1 = getTypefaceFlipFont(i, j);
        if(typeface1 != null)
            return typeface1;
        else
            return typeface;
    }

    public static Typeface getDefaultTypeface(Typeface typeface)
    {
        Typeface typeface1 = typeface;
        if(typeface == null)
            typeface1 = getTypefaceFlipFont(1, 0);
        return typeface1;
    }

    private static String getFlipFontPath(int i, int j)
    {
        boolean flag;
        String as[];
        flag = false;
        if(i != 1)
            return "";
        as = null;
        if(j != 0) goto _L2; else goto _L1
_L1:
        as = DROID_SANS_FONTS;
_L6:
        j = as.length;
        i = ((flag) ? 1 : 0);
_L4:
        if(i >= j)
            break; /* Loop/switch isn't completed */
        String s = as[i];
        if((new File(s)).exists())
            return s;
        i++;
        continue; /* Loop/switch isn't completed */
_L2:
        if(j == 1)
            as = DROID_SANS_FONTS_BOLD;
        continue; /* Loop/switch isn't completed */
        if(true) goto _L4; else goto _L3
_L3:
        return "";
        if(true) goto _L6; else goto _L5
_L5:
    }

    private static Typeface getTypefaceFlipFont(int i, int j)
    {
        while(!FeatureParser.getBoolean("is_patchrom", false) || i != 1 || j < 0 || j > 3) 
            return null;
        setTypefaceFlipFont();
        return Typeface.sDefaults[j];
    }

    private static void setTypefaceFlipFont()
    {
        String s;
        String s1;
        File file;
        s = getFlipFontPath(1, 0);
        s1 = getFlipFontPath(1, 1);
        file = new File(s);
        if(!s.isEmpty()) goto _L2; else goto _L1
_L1:
        FLIPFONT = Typeface.DEFAULT;
        FLIPFONT_BOLD = Typeface.DEFAULT_BOLD;
        FLIPFONT_ITALIC = Typeface.create((String)null, 2);
        FLIPFONT_BOLD_ITALIC = Typeface.create((String)null, 3);
_L4:
        Typeface.sDefaults[0] = FLIPFONT;
        Typeface.sDefaults[1] = FLIPFONT_BOLD;
        Typeface.sDefaults[2] = FLIPFONT_ITALIC;
        Typeface.sDefaults[3] = FLIPFONT_BOLD_ITALIC;
        return;
_L2:
        if(mLastModified != file.lastModified())
        {
            try
            {
                FLIPFONT = Typeface.createFromFile(s);
                FLIPFONT_BOLD = Typeface.createFromFile(s1);
            }
            catch(RuntimeException runtimeexception)
            {
                FLIPFONT_BOLD = Typeface.create(FLIPFONT, 1);
            }
            FLIPFONT_ITALIC = Typeface.create(FLIPFONT, 2);
            FLIPFONT_BOLD_ITALIC = Typeface.create(FLIPFONT_BOLD, 3);
            mLastModified = file.lastModified();
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static final String DROID_SANS = "/data/system/theme/fonts/Roboto-Regular.ttf";
    private static final String DROID_SANS_BOLD = "/data/system/theme/fonts/Roboto-Bold.ttf";
    private static final String DROID_SANS_FALLBACK = "/data/system/theme/fonts/DroidSansFallback.ttf";
    private static String DROID_SANS_FONTS[] = {
        "/data/system/theme/fonts/DroidSansFallback.ttf", "/data/system/theme/fonts/Roboto-Regular.ttf"
    };
    private static String DROID_SANS_FONTS_BOLD[] = {
        "/data/system/theme/fonts/Roboto-Bold.ttf"
    };
    public static Typeface FLIPFONT = null;
    public static Typeface FLIPFONT_BOLD = null;
    public static Typeface FLIPFONT_BOLD_ITALIC = null;
    public static Typeface FLIPFONT_ITALIC = null;
    private static final String FONTS_FOLDER = "/data/system/theme/fonts/";
    public static final int MONOSPACE_INDEX = 3;
    public static final int SANS_INDEX = 1;
    public static final int SERIF_INDEX = 2;
    private static long mLastModified;

}
