// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;


// Referenced classes of package android.graphics:
//            MaskFilter

public class BlurMaskFilter extends MaskFilter
{
    public static final class Blur extends Enum
    {

        public static Blur valueOf(String s)
        {
            return (Blur)Enum.valueOf(android/graphics/BlurMaskFilter$Blur, s);
        }

        public static Blur[] values()
        {
            return $VALUES;
        }

        private static final Blur $VALUES[];
        public static final Blur INNER;
        public static final Blur NORMAL;
        public static final Blur OUTER;
        public static final Blur SOLID;
        final int native_int;

        static 
        {
            NORMAL = new Blur("NORMAL", 0, 0);
            SOLID = new Blur("SOLID", 1, 1);
            OUTER = new Blur("OUTER", 2, 2);
            INNER = new Blur("INNER", 3, 3);
            $VALUES = (new Blur[] {
                NORMAL, SOLID, OUTER, INNER
            });
        }

        private Blur(String s, int i, int j)
        {
            super(s, i);
            native_int = j;
        }
    }


    public BlurMaskFilter(float f, Blur blur)
    {
        native_instance = nativeConstructor(f, blur.native_int);
    }

    private static native long nativeConstructor(float f, int i);
}
