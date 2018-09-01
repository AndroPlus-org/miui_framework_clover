// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;


// Referenced classes of package android.graphics:
//            Xfermode

public class AvoidXfermode extends Xfermode
{
    public static final class Mode extends Enum
    {

        public static Mode valueOf(String s)
        {
            return (Mode)Enum.valueOf(android/graphics/AvoidXfermode$Mode, s);
        }

        public static Mode[] values()
        {
            return $VALUES;
        }

        private static final Mode $VALUES[];
        public static final Mode AVOID;
        public static final Mode TARGET;
        final int nativeInt;

        static 
        {
            AVOID = new Mode("AVOID", 0, 0);
            TARGET = new Mode("TARGET", 1, 1);
            $VALUES = (new Mode[] {
                AVOID, TARGET
            });
        }

        private Mode(String s, int i, int j)
        {
            super(s, i);
            nativeInt = j;
        }
    }


    public AvoidXfermode(int i, int j, Mode mode)
    {
        if(j < 0 || j > 255)
            throw new IllegalArgumentException("tolerance must be 0..255");
        else
            return;
    }
}
