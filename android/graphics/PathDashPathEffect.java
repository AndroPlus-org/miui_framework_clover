// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;


// Referenced classes of package android.graphics:
//            PathEffect, Path

public class PathDashPathEffect extends PathEffect
{
    public static final class Style extends Enum
    {

        public static Style valueOf(String s)
        {
            return (Style)Enum.valueOf(android/graphics/PathDashPathEffect$Style, s);
        }

        public static Style[] values()
        {
            return $VALUES;
        }

        private static final Style $VALUES[];
        public static final Style MORPH;
        public static final Style ROTATE;
        public static final Style TRANSLATE;
        int native_style;

        static 
        {
            TRANSLATE = new Style("TRANSLATE", 0, 0);
            ROTATE = new Style("ROTATE", 1, 1);
            MORPH = new Style("MORPH", 2, 2);
            $VALUES = (new Style[] {
                TRANSLATE, ROTATE, MORPH
            });
        }

        private Style(String s, int i, int j)
        {
            super(s, i);
            native_style = j;
        }
    }


    public PathDashPathEffect(Path path, float f, float f1, Style style)
    {
        native_instance = nativeCreate(path.readOnlyNI(), f, f1, style.native_style);
    }

    private static native long nativeCreate(long l, float f, float f1, int i);
}
