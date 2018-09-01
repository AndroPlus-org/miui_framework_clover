// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.webkit;

import android.content.Context;
import android.util.Log;

class LegacyErrorStrings
{

    private LegacyErrorStrings()
    {
    }

    private static int getResource(int i)
    {
        switch(i)
        {
        default:
            Log.w("Http", (new StringBuilder()).append("Using generic message for unknown error code: ").append(i).toString());
            return 0x104026b;

        case 0: // '\0'
            return 0x1040273;

        case -1: 
            return 0x104026b;

        case -2: 
            return 0x1040272;

        case -3: 
            return 0x1040278;

        case -4: 
            return 0x104026c;

        case -5: 
            return 0x1040274;

        case -6: 
            return 0x104026d;

        case -7: 
            return 0x1040271;

        case -8: 
            return 0x1040276;

        case -9: 
            return 0x1040275;

        case -10: 
            return 0x1040008;

        case -11: 
            return 0x104026e;

        case -12: 
            return 0x1040007;

        case -13: 
            return 0x104026f;

        case -14: 
            return 0x1040270;

        case -15: 
            return 0x1040277;
        }
    }

    static String getString(int i, Context context)
    {
        return context.getText(getResource(i)).toString();
    }

    private static final String LOGTAG = "Http";
}
