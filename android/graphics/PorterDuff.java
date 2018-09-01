// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;


public class PorterDuff
{
    public static final class Mode extends Enum
    {

        public static Mode valueOf(String s)
        {
            return (Mode)Enum.valueOf(android/graphics/PorterDuff$Mode, s);
        }

        public static Mode[] values()
        {
            return $VALUES;
        }

        private static final Mode $VALUES[];
        public static final Mode ADD;
        public static final Mode CLEAR;
        public static final Mode DARKEN;
        public static final Mode DST;
        public static final Mode DST_ATOP;
        public static final Mode DST_IN;
        public static final Mode DST_OUT;
        public static final Mode DST_OVER;
        public static final Mode LIGHTEN;
        public static final Mode MULTIPLY;
        public static final Mode OVERLAY;
        public static final Mode SCREEN;
        public static final Mode SRC;
        public static final Mode SRC_ATOP;
        public static final Mode SRC_IN;
        public static final Mode SRC_OUT;
        public static final Mode SRC_OVER;
        public static final Mode XOR;
        public final int nativeInt;

        static 
        {
            CLEAR = new Mode("CLEAR", 0, 0);
            SRC = new Mode("SRC", 1, 1);
            DST = new Mode("DST", 2, 2);
            SRC_OVER = new Mode("SRC_OVER", 3, 3);
            DST_OVER = new Mode("DST_OVER", 4, 4);
            SRC_IN = new Mode("SRC_IN", 5, 5);
            DST_IN = new Mode("DST_IN", 6, 6);
            SRC_OUT = new Mode("SRC_OUT", 7, 7);
            DST_OUT = new Mode("DST_OUT", 8, 8);
            SRC_ATOP = new Mode("SRC_ATOP", 9, 9);
            DST_ATOP = new Mode("DST_ATOP", 10, 10);
            XOR = new Mode("XOR", 11, 11);
            DARKEN = new Mode("DARKEN", 12, 16);
            LIGHTEN = new Mode("LIGHTEN", 13, 17);
            MULTIPLY = new Mode("MULTIPLY", 14, 13);
            SCREEN = new Mode("SCREEN", 15, 14);
            ADD = new Mode("ADD", 16, 12);
            OVERLAY = new Mode("OVERLAY", 17, 15);
            $VALUES = (new Mode[] {
                CLEAR, SRC, DST, SRC_OVER, DST_OVER, SRC_IN, DST_IN, SRC_OUT, DST_OUT, SRC_ATOP, 
                DST_ATOP, XOR, DARKEN, LIGHTEN, MULTIPLY, SCREEN, ADD, OVERLAY
            });
        }

        private Mode(String s, int i, int j)
        {
            super(s, i);
            nativeInt = j;
        }
    }


    public PorterDuff()
    {
    }

    public static Mode intToMode(int i)
    {
        switch(i)
        {
        case 0: // '\0'
        default:
            return Mode.CLEAR;

        case 1: // '\001'
            return Mode.SRC;

        case 2: // '\002'
            return Mode.DST;

        case 3: // '\003'
            return Mode.SRC_OVER;

        case 4: // '\004'
            return Mode.DST_OVER;

        case 5: // '\005'
            return Mode.SRC_IN;

        case 6: // '\006'
            return Mode.DST_IN;

        case 7: // '\007'
            return Mode.SRC_OUT;

        case 8: // '\b'
            return Mode.DST_OUT;

        case 9: // '\t'
            return Mode.SRC_ATOP;

        case 10: // '\n'
            return Mode.DST_ATOP;

        case 11: // '\013'
            return Mode.XOR;

        case 16: // '\020'
            return Mode.DARKEN;

        case 17: // '\021'
            return Mode.LIGHTEN;

        case 13: // '\r'
            return Mode.MULTIPLY;

        case 14: // '\016'
            return Mode.SCREEN;

        case 12: // '\f'
            return Mode.ADD;

        case 15: // '\017'
            return Mode.OVERLAY;
        }
    }

    public static int modeToInt(Mode mode)
    {
        return mode.nativeInt;
    }
}
