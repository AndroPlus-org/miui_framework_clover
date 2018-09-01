// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.policy;

import android.graphics.Rect;

public class DockedDividerUtils
{

    public DockedDividerUtils()
    {
    }

    public static void calculateBoundsForPosition(int i, int j, Rect rect, int k, int l, int i1)
    {
        boolean flag;
        flag = true;
        rect.set(0, 0, k, l);
        j;
        JVM INSTR tableswitch 1 4: default 44
    //                   1 69
    //                   2 77
    //                   3 85
    //                   4 96;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        break; /* Loop/switch isn't completed */
_L5:
        break MISSING_BLOCK_LABEL_96;
_L6:
        boolean flag1 = flag;
        if(j != 1)
            if(j == 2)
                flag1 = flag;
            else
                flag1 = false;
        sanitizeStackBounds(rect, flag1);
        return;
_L2:
        rect.right = i;
          goto _L6
_L3:
        rect.bottom = i;
          goto _L6
_L4:
        rect.left = i + i1;
          goto _L6
        rect.top = i + i1;
          goto _L6
    }

    public static int calculateMiddlePosition(boolean flag, Rect rect, int i, int j, int k)
    {
        int l;
        if(flag)
            l = rect.top;
        else
            l = rect.left;
        if(flag)
            i = j - rect.bottom;
        else
            i -= rect.right;
        return ((i - l) / 2 + l) - k / 2;
    }

    public static int calculatePositionForBounds(Rect rect, int i, int j)
    {
        switch(i)
        {
        default:
            return 0;

        case 1: // '\001'
            return rect.right;

        case 2: // '\002'
            return rect.bottom;

        case 3: // '\003'
            return rect.left - j;

        case 4: // '\004'
            return rect.top - j;
        }
    }

    public static int getDockSideFromCreatedMode(boolean flag, boolean flag1)
    {
        if(flag)
            return !flag1 ? 1 : 2;
        return !flag1 ? 3 : 4;
    }

    public static int invertDockSide(int i)
    {
        switch(i)
        {
        default:
            return -1;

        case 1: // '\001'
            return 3;

        case 2: // '\002'
            return 4;

        case 3: // '\003'
            return 1;

        case 4: // '\004'
            return 2;
        }
    }

    public static void sanitizeStackBounds(Rect rect, boolean flag)
    {
        if(!flag) goto _L2; else goto _L1
_L1:
        if(rect.left >= rect.right)
            rect.left = rect.right - 1;
        if(rect.top >= rect.bottom)
            rect.top = rect.bottom - 1;
_L4:
        return;
_L2:
        if(rect.right <= rect.left)
            rect.right = rect.left + 1;
        if(rect.bottom <= rect.top)
            rect.bottom = rect.top + 1;
        if(true) goto _L4; else goto _L3
_L3:
    }
}
