// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.renderscript;

import android.content.res.Resources;
import android.os.Environment;
import android.util.DisplayMetrics;
import dalvik.system.CloseGuard;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package android.renderscript:
//            BaseObj, RenderScript, RSRuntimeException

public class Font extends BaseObj
{
    private static class FontFamily
    {

        String mBoldFileName;
        String mBoldItalicFileName;
        String mItalicFileName;
        String mNames[];
        String mNormalFileName;

        private FontFamily()
        {
        }

        FontFamily(FontFamily fontfamily)
        {
            this();
        }
    }

    public static final class Style extends Enum
    {

        public static Style valueOf(String s)
        {
            return (Style)Enum.valueOf(android/renderscript/Font$Style, s);
        }

        public static Style[] values()
        {
            return $VALUES;
        }

        private static final Style $VALUES[];
        public static final Style BOLD;
        public static final Style BOLD_ITALIC;
        public static final Style ITALIC;
        public static final Style NORMAL;

        static 
        {
            NORMAL = new Style("NORMAL", 0);
            BOLD = new Style("BOLD", 1);
            ITALIC = new Style("ITALIC", 2);
            BOLD_ITALIC = new Style("BOLD_ITALIC", 3);
            $VALUES = (new Style[] {
                NORMAL, BOLD, ITALIC, BOLD_ITALIC
            });
        }

        private Style(String s, int i)
        {
            super(s, i);
        }
    }


    private static int[] _2D_getandroid_2D_renderscript_2D_Font$StyleSwitchesValues()
    {
        if(_2D_android_2D_renderscript_2D_Font$StyleSwitchesValues != null)
            return _2D_android_2D_renderscript_2D_Font$StyleSwitchesValues;
        int ai[] = new int[Style.values().length];
        try
        {
            ai[Style.BOLD.ordinal()] = 1;
        }
        catch(NoSuchFieldError nosuchfielderror3) { }
        try
        {
            ai[Style.BOLD_ITALIC.ordinal()] = 2;
        }
        catch(NoSuchFieldError nosuchfielderror2) { }
        try
        {
            ai[Style.ITALIC.ordinal()] = 3;
        }
        catch(NoSuchFieldError nosuchfielderror1) { }
        try
        {
            ai[Style.NORMAL.ordinal()] = 4;
        }
        catch(NoSuchFieldError nosuchfielderror) { }
        _2D_android_2D_renderscript_2D_Font$StyleSwitchesValues = ai;
        return ai;
    }

    Font(long l, RenderScript renderscript)
    {
        super(l, renderscript);
        guard.open("destroy");
    }

    private static void addFamilyToMap(FontFamily fontfamily)
    {
        for(int i = 0; i < fontfamily.mNames.length; i++)
            sFontFamilyMap.put(fontfamily.mNames[i], fontfamily);

    }

    public static Font create(RenderScript renderscript, Resources resources, String s, Style style, float f)
    {
        s = getFontFileName(s, style);
        style = Environment.getRootDirectory().getAbsolutePath();
        return createFromFile(renderscript, resources, (new StringBuilder()).append(style).append("/fonts/").append(s).toString(), f);
    }

    public static Font createFromAsset(RenderScript renderscript, Resources resources, String s, float f)
    {
        renderscript.validate();
        long l = renderscript.nFontCreateFromAsset(resources.getAssets(), s, f, resources.getDisplayMetrics().densityDpi);
        if(l == 0L)
            throw new RSRuntimeException((new StringBuilder()).append("Unable to create font from asset ").append(s).toString());
        else
            return new Font(l, renderscript);
    }

    public static Font createFromFile(RenderScript renderscript, Resources resources, File file, float f)
    {
        return createFromFile(renderscript, resources, file.getAbsolutePath(), f);
    }

    public static Font createFromFile(RenderScript renderscript, Resources resources, String s, float f)
    {
        renderscript.validate();
        long l = renderscript.nFontCreateFromFile(s, f, resources.getDisplayMetrics().densityDpi);
        if(l == 0L)
            throw new RSRuntimeException((new StringBuilder()).append("Unable to create font from file ").append(s).toString());
        else
            return new Font(l, renderscript);
    }

    public static Font createFromResource(RenderScript renderscript, Resources resources, int i, float f)
    {
        String s = (new StringBuilder()).append("R.").append(Integer.toString(i)).toString();
        renderscript.validate();
        java.io.InputStream inputstream;
        int j;
        try
        {
            inputstream = resources.openRawResource(i);
        }
        // Misplaced declaration of an exception variable
        catch(RenderScript renderscript)
        {
            throw new RSRuntimeException((new StringBuilder()).append("Unable to open resource ").append(i).toString());
        }
        j = resources.getDisplayMetrics().densityDpi;
        if(inputstream instanceof android.content.res.AssetManager.AssetInputStream)
        {
            long l = renderscript.nFontCreateFromAssetStream(s, f, j, ((android.content.res.AssetManager.AssetInputStream)inputstream).getNativeAsset());
            if(l == 0L)
                throw new RSRuntimeException((new StringBuilder()).append("Unable to create font from resource ").append(i).toString());
            else
                return new Font(l, renderscript);
        } else
        {
            throw new RSRuntimeException("Unsupported asset stream created");
        }
    }

    static String getFontFileName(String s, Style style)
    {
        s = (FontFamily)sFontFamilyMap.get(s);
        if(s == null) goto _L2; else goto _L1
_L1:
        _2D_getandroid_2D_renderscript_2D_Font$StyleSwitchesValues()[style.ordinal()];
        JVM INSTR tableswitch 1 4: default 56
    //                   1 64
    //                   2 74
    //                   3 69
    //                   4 59;
           goto _L2 _L3 _L4 _L5 _L6
_L2:
        return "DroidSans.ttf";
_L6:
        return ((FontFamily) (s)).mNormalFileName;
_L3:
        return ((FontFamily) (s)).mBoldFileName;
_L5:
        return ((FontFamily) (s)).mItalicFileName;
_L4:
        return ((FontFamily) (s)).mBoldItalicFileName;
    }

    private static void initFontFamilyMap()
    {
        sFontFamilyMap = new HashMap();
        FontFamily fontfamily = new FontFamily(null);
        fontfamily.mNames = sSansNames;
        fontfamily.mNormalFileName = "Roboto-Regular.ttf";
        fontfamily.mBoldFileName = "Roboto-Bold.ttf";
        fontfamily.mItalicFileName = "Roboto-Italic.ttf";
        fontfamily.mBoldItalicFileName = "Roboto-BoldItalic.ttf";
        addFamilyToMap(fontfamily);
        fontfamily = new FontFamily(null);
        fontfamily.mNames = sSerifNames;
        fontfamily.mNormalFileName = "NotoSerif-Regular.ttf";
        fontfamily.mBoldFileName = "NotoSerif-Bold.ttf";
        fontfamily.mItalicFileName = "NotoSerif-Italic.ttf";
        fontfamily.mBoldItalicFileName = "NotoSerif-BoldItalic.ttf";
        addFamilyToMap(fontfamily);
        fontfamily = new FontFamily(null);
        fontfamily.mNames = sMonoNames;
        fontfamily.mNormalFileName = "DroidSansMono.ttf";
        fontfamily.mBoldFileName = "DroidSansMono.ttf";
        fontfamily.mItalicFileName = "DroidSansMono.ttf";
        fontfamily.mBoldItalicFileName = "DroidSansMono.ttf";
        addFamilyToMap(fontfamily);
    }

    private static final int _2D_android_2D_renderscript_2D_Font$StyleSwitchesValues[];
    private static Map sFontFamilyMap;
    private static final String sMonoNames[] = {
        "monospace", "courier", "courier new", "monaco"
    };
    private static final String sSansNames[] = {
        "sans-serif", "arial", "helvetica", "tahoma", "verdana"
    };
    private static final String sSerifNames[] = {
        "serif", "times", "times new roman", "palatino", "georgia", "baskerville", "goudy", "fantasy", "cursive", "ITC Stone Serif"
    };

    static 
    {
        initFontFamilyMap();
    }
}
