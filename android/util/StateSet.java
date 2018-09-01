// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;


public class StateSet
{

    public StateSet()
    {
    }

    public static boolean containsAttribute(int ai[][], int i)
    {
        if(ai == null) goto _L2; else goto _L1
_L1:
        int j;
        int k;
        j = ai.length;
        k = 0;
_L6:
        if(k >= j) goto _L2; else goto _L3
_L3:
        int ai1[] = ai[k];
        if(ai1 != null) goto _L4; else goto _L2
_L2:
        return false;
_L4:
        int l = ai1.length;
        for(int i1 = 0; i1 < l; i1++)
        {
            int j1 = ai1[i1];
            if(j1 == i || -j1 == i)
                return true;
        }

        k++;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public static String dump(int ai[])
    {
        StringBuilder stringbuilder;
        int i;
        int j;
        stringbuilder = new StringBuilder();
        i = ai.length;
        j = 0;
_L10:
        if(j >= i)
            break MISSING_BLOCK_LABEL_164;
        ai[j];
        JVM INSTR lookupswitch 7: default 88
    //                   16842908: 124
    //                   16842909: 94
    //                   16842910: 134
    //                   16842912: 144
    //                   16842913: 114
    //                   16842919: 104
    //                   16843518: 154;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L8:
        break MISSING_BLOCK_LABEL_154;
_L1:
        break; /* Loop/switch isn't completed */
_L3:
        break; /* Loop/switch isn't completed */
_L11:
        j++;
        if(true) goto _L10; else goto _L9
_L9:
        stringbuilder.append("W ");
          goto _L11
_L7:
        stringbuilder.append("P ");
          goto _L11
_L6:
        stringbuilder.append("S ");
          goto _L11
_L2:
        stringbuilder.append("F ");
          goto _L11
_L4:
        stringbuilder.append("E ");
          goto _L11
_L5:
        stringbuilder.append("C ");
          goto _L11
        stringbuilder.append("A ");
          goto _L11
        return stringbuilder.toString();
    }

    public static int[] get(int i)
    {
        if(i >= VIEW_STATE_SETS.length)
            throw new IllegalArgumentException("Invalid state set mask");
        else
            return VIEW_STATE_SETS[i];
    }

    public static boolean isWildCard(int ai[])
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(ai.length != 0)
            if(ai[0] == 0)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public static boolean stateSetMatches(int ai[], int i)
    {
        int j = ai.length;
        for(int k = 0; k < j; k++)
        {
            int l = ai[k];
            if(l == 0)
                return true;
            if(l > 0)
            {
                if(i != l)
                    return false;
                continue;
            }
            if(i == -l)
                return false;
        }

        return true;
    }

    public static boolean stateSetMatches(int ai[], int ai1[])
    {
        boolean flag = true;
        if(ai1 == null)
        {
            if(ai != null)
                flag = isWildCard(ai);
            return flag;
        }
        int i = ai.length;
        int j = ai1.length;
label0:
        for(int k = 0; k < i; k++)
        {
            int l = ai[k];
            if(l == 0)
                return true;
            boolean flag1;
            boolean flag2;
            int i1;
            if(l > 0)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
                l = -l;
            }
            flag2 = false;
            i1 = 0;
            do
            {
label1:
                {
label2:
                    {
                        int j1 = ((flag2) ? 1 : 0);
                        if(i1 < j)
                        {
                            j1 = ai1[i1];
                            if(j1 == 0)
                            {
                                j1 = ((flag2) ? 1 : 0);
                                if(flag1)
                                    return false;
                            } else
                            {
                                if(j1 != l)
                                    break label1;
                                if(!flag1)
                                    break label2;
                                j1 = 1;
                            }
                        }
                        if(flag1 && (j1 ^ 1) != 0)
                            return false;
                        continue label0;
                    }
                    return false;
                }
                i1++;
            } while(true);
        }

        return true;
    }

    public static int[] trimStateSet(int ai[], int i)
    {
        if(ai.length == i)
        {
            return ai;
        } else
        {
            int ai1[] = new int[i];
            System.arraycopy(ai, 0, ai1, 0, i);
            return ai1;
        }
    }

    public static final int NOTHING[] = {
        0
    };
    public static final int VIEW_STATE_ACCELERATED = 64;
    public static final int VIEW_STATE_ACTIVATED = 32;
    public static final int VIEW_STATE_DRAG_CAN_ACCEPT = 256;
    public static final int VIEW_STATE_DRAG_HOVERED = 512;
    public static final int VIEW_STATE_ENABLED = 8;
    public static final int VIEW_STATE_FOCUSED = 4;
    public static final int VIEW_STATE_HOVERED = 128;
    static final int VIEW_STATE_IDS[] = {
        0x101009d, 1, 0x10100a1, 2, 0x101009c, 4, 0x101009e, 8, 0x10100a7, 16, 
        0x10102fe, 32, 0x101031b, 64, 0x1010367, 128, 0x1010368, 256, 0x1010369, 512
    };
    public static final int VIEW_STATE_PRESSED = 16;
    public static final int VIEW_STATE_SELECTED = 2;
    private static final int VIEW_STATE_SETS[][];
    public static final int VIEW_STATE_WINDOW_FOCUSED = 1;
    public static final int WILD_CARD[] = new int[0];

    static 
    {
        if(VIEW_STATE_IDS.length / 2 != com.android.internal.R.styleable.ViewDrawableStates.length)
            throw new IllegalStateException("VIEW_STATE_IDs array length does not match ViewDrawableStates style array");
        int ai[] = new int[VIEW_STATE_IDS.length];
        for(int i = 0; i < com.android.internal.R.styleable.ViewDrawableStates.length; i++)
        {
            int k = com.android.internal.R.styleable.ViewDrawableStates[i];
            for(int i1 = 0; i1 < VIEW_STATE_IDS.length; i1 += 2)
                if(VIEW_STATE_IDS[i1] == k)
                {
                    ai[i * 2] = k;
                    ai[i * 2 + 1] = VIEW_STATE_IDS[i1 + 1];
                }

        }

        VIEW_STATE_SETS = new int[1 << VIEW_STATE_IDS.length / 2][];
        for(int j = 0; j < VIEW_STATE_SETS.length; j++)
        {
            int ai1[] = new int[Integer.bitCount(j)];
            int l = 0;
            for(int k1 = 0; k1 < ai.length;)
            {
                int j1 = l;
                if((ai[k1 + 1] & j) != 0)
                {
                    ai1[l] = ai[k1];
                    j1 = l + 1;
                }
                k1 += 2;
                l = j1;
            }

            VIEW_STATE_SETS[j] = ai1;
        }

    }
}
