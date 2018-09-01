// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public class NetworkBadging
{

    private NetworkBadging()
    {
    }

    private static int getBadgedWifiSignalResource(int i)
    {
        switch(i)
        {
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid signal level: ").append(i).toString());

        case 0: // '\0'
            return 0x10804d2;

        case 1: // '\001'
            return 0x10804d3;

        case 2: // '\002'
            return 0x10804d4;

        case 3: // '\003'
            return 0x10804d5;

        case 4: // '\004'
            return 0x10804d6;
        }
    }

    public static Drawable getWifiIcon(int i, int j, android.content.res.Resources.Theme theme)
    {
        return Resources.getSystem().getDrawable(getWifiSignalResource(i), theme);
    }

    private static int getWifiSignalResource(int i)
    {
        switch(i)
        {
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid signal level: ").append(i).toString());

        case 0: // '\0'
            return 0x10804f7;

        case 1: // '\001'
            return 0x10804f8;

        case 2: // '\002'
            return 0x10804f9;

        case 3: // '\003'
            return 0x10804fa;

        case 4: // '\004'
            return 0x10804fb;
        }
    }

    public static final int BADGING_4K = 30;
    public static final int BADGING_HD = 20;
    public static final int BADGING_NONE = 0;
    public static final int BADGING_SD = 10;
}
