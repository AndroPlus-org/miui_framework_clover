// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text;

import java.nio.CharBuffer;
import java.util.Locale;

// Referenced classes of package android.text:
//            TextDirectionHeuristic, TextUtils

public class TextDirectionHeuristics
{
    private static class AnyStrong
        implements TextDirectionAlgorithm
    {

        public int checkRtl(CharSequence charsequence, int i, int j)
        {
            boolean flag;
            boolean flag1;
            int k;
            int l;
            flag = true;
            flag1 = false;
            k = 0;
            l = i;
_L2:
            int i1;
            int j1;
            boolean flag2;
            if(l >= i + j)
                break; /* Loop/switch isn't completed */
            i1 = Character.codePointAt(charsequence, l);
            if(8294 <= i1 && i1 <= 8296)
            {
                j1 = k + 1;
                flag2 = flag1;
            } else
            {
label0:
                {
                    if(i1 != 8297)
                        break label0;
                    flag2 = flag1;
                    j1 = k;
                    if(k > 0)
                    {
                        j1 = k - 1;
                        flag2 = flag1;
                    }
                }
            }
_L3:
            l += Character.charCount(i1);
            flag1 = flag2;
            k = j1;
            if(true) goto _L2; else goto _L1
            flag2 = flag1;
            j1 = k;
            if(k == 0)
            {
                switch(TextDirectionHeuristics._2D_wrap0(i1))
                {
                default:
                    flag2 = flag1;
                    j1 = k;
                    break;

                case 0: // '\0'
                    if(mLookForRtl)
                        return 0;
                    flag2 = true;
                    j1 = k;
                    break;

                case 1: // '\001'
                    if(!mLookForRtl)
                        return 1;
                    flag2 = true;
                    j1 = k;
                    break;
                }
                continue; /* Loop/switch isn't completed */
            }
              goto _L3
_L1:
            if(flag1)
            {
                if(mLookForRtl)
                    i = ((flag) ? 1 : 0);
                else
                    i = 0;
                return i;
            }
            return 2;
            if(true) goto _L3; else goto _L4
_L4:
        }

        public static final AnyStrong INSTANCE_LTR = new AnyStrong(false);
        public static final AnyStrong INSTANCE_RTL = new AnyStrong(true);
        private final boolean mLookForRtl;


        private AnyStrong(boolean flag)
        {
            mLookForRtl = flag;
        }
    }

    private static class FirstStrong
        implements TextDirectionAlgorithm
    {

        public int checkRtl(CharSequence charsequence, int i, int j)
        {
            byte byte0 = 2;
            int k = 0;
            int l = i;
            while(l < i + j && byte0 == 2) 
            {
                int i1 = Character.codePointAt(charsequence, l);
                int j1;
                int k1;
                if(8294 <= i1 && i1 <= 8296)
                {
                    j1 = k + 1;
                    k1 = byte0;
                } else
                if(i1 == 8297)
                {
                    j1 = k;
                    k1 = byte0;
                    if(k > 0)
                    {
                        j1 = k - 1;
                        k1 = byte0;
                    }
                } else
                {
                    j1 = k;
                    k1 = byte0;
                    if(k == 0)
                    {
                        k1 = TextDirectionHeuristics._2D_wrap0(i1);
                        j1 = k;
                    }
                }
                l += Character.charCount(i1);
                k = j1;
                byte0 = k1;
            }
            return byte0;
        }

        public static final FirstStrong INSTANCE = new FirstStrong();


        private FirstStrong()
        {
        }
    }

    private static interface TextDirectionAlgorithm
    {

        public abstract int checkRtl(CharSequence charsequence, int i, int j);
    }

    private static abstract class TextDirectionHeuristicImpl
        implements TextDirectionHeuristic
    {

        private boolean doCheck(CharSequence charsequence, int i, int j)
        {
            switch(mAlgorithm.checkRtl(charsequence, i, j))
            {
            default:
                return defaultIsRtl();

            case 0: // '\0'
                return true;

            case 1: // '\001'
                return false;
            }
        }

        protected abstract boolean defaultIsRtl();

        public boolean isRtl(CharSequence charsequence, int i, int j)
        {
            while(charsequence == null || i < 0 || j < 0 || charsequence.length() - j < i) 
                throw new IllegalArgumentException();
            if(mAlgorithm == null)
                return defaultIsRtl();
            else
                return doCheck(charsequence, i, j);
        }

        public boolean isRtl(char ac[], int i, int j)
        {
            return isRtl(((CharSequence) (CharBuffer.wrap(ac))), i, j);
        }

        private final TextDirectionAlgorithm mAlgorithm;

        public TextDirectionHeuristicImpl(TextDirectionAlgorithm textdirectionalgorithm)
        {
            mAlgorithm = textdirectionalgorithm;
        }
    }

    private static class TextDirectionHeuristicInternal extends TextDirectionHeuristicImpl
    {

        protected boolean defaultIsRtl()
        {
            return mDefaultIsRtl;
        }

        private final boolean mDefaultIsRtl;

        private TextDirectionHeuristicInternal(TextDirectionAlgorithm textdirectionalgorithm, boolean flag)
        {
            super(textdirectionalgorithm);
            mDefaultIsRtl = flag;
        }

        TextDirectionHeuristicInternal(TextDirectionAlgorithm textdirectionalgorithm, boolean flag, TextDirectionHeuristicInternal textdirectionheuristicinternal)
        {
            this(textdirectionalgorithm, flag);
        }
    }

    private static class TextDirectionHeuristicLocale extends TextDirectionHeuristicImpl
    {

        protected boolean defaultIsRtl()
        {
            boolean flag = true;
            if(TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) != 1)
                flag = false;
            return flag;
        }

        public static final TextDirectionHeuristicLocale INSTANCE = new TextDirectionHeuristicLocale();


        public TextDirectionHeuristicLocale()
        {
            super(null);
        }
    }


    static int _2D_wrap0(int i)
    {
        return isRtlCodePoint(i);
    }

    public TextDirectionHeuristics()
    {
    }

    private static int isRtlCodePoint(int i)
    {
        switch(Character.getDirectionality(i))
        {
        default:
            return 2;

        case 0: // '\0'
            return 1;

        case 1: // '\001'
        case 2: // '\002'
            return 0;

        case -1: 
            break;
        }
        while(1424 <= i && i <= 2303 || 64285 <= i && i <= 64975 || 65008 <= i && i <= 65023 || 65136 <= i && i <= 65279 || 0x10800 <= i && i <= 0x10fff || 0x1e800 <= i && i <= 0x1efff) 
            return 0;
        while(8293 <= i && i <= 8297 || 65520 <= i && i <= 65528 || 0xe0000 <= i && i <= 0xe0fff || 64976 <= i && i <= 65007 || (i & 0xfffe) == 65534 || 8352 <= i && i <= 8399 || 55296 <= i && i <= 57343) 
            return 2;
        return 1;
    }

    public static final TextDirectionHeuristic ANYRTL_LTR;
    public static final TextDirectionHeuristic FIRSTSTRONG_LTR;
    public static final TextDirectionHeuristic FIRSTSTRONG_RTL;
    public static final TextDirectionHeuristic LOCALE;
    public static final TextDirectionHeuristic LTR = new TextDirectionHeuristicInternal(null, false, null);
    public static final TextDirectionHeuristic RTL = new TextDirectionHeuristicInternal(null, true, null);
    private static final int STATE_FALSE = 1;
    private static final int STATE_TRUE = 0;
    private static final int STATE_UNKNOWN = 2;

    static 
    {
        FIRSTSTRONG_LTR = new TextDirectionHeuristicInternal(FirstStrong.INSTANCE, false, null);
        FIRSTSTRONG_RTL = new TextDirectionHeuristicInternal(FirstStrong.INSTANCE, true, null);
        ANYRTL_LTR = new TextDirectionHeuristicInternal(AnyStrong.INSTANCE_RTL, false, null);
        LOCALE = TextDirectionHeuristicLocale.INSTANCE;
    }
}
