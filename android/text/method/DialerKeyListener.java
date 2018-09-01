// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.method;

import android.text.Spannable;
import android.view.KeyEvent;

// Referenced classes of package android.text.method:
//            NumberKeyListener

public class DialerKeyListener extends NumberKeyListener
{

    public DialerKeyListener()
    {
    }

    public static DialerKeyListener getInstance()
    {
        if(sInstance != null)
        {
            return sInstance;
        } else
        {
            sInstance = new DialerKeyListener();
            return sInstance;
        }
    }

    protected char[] getAcceptedChars()
    {
        return CHARACTERS;
    }

    public int getInputType()
    {
        return 3;
    }

    protected int lookup(KeyEvent keyevent, Spannable spannable)
    {
        int i = getMetaState(spannable, keyevent);
        char c = keyevent.getNumber();
        if((i & 3) == 0 && c != 0)
            return c;
        int k = super.lookup(keyevent, spannable);
        if(k != 0)
            return k;
        if(i != 0)
        {
            android.view.KeyCharacterMap.KeyData keydata = new android.view.KeyCharacterMap.KeyData();
            spannable = getAcceptedChars();
            if(keyevent.getKeyData(keydata))
            {
                for(int j = 1; j < keydata.meta.length; j++)
                    if(ok(spannable, keydata.meta[j]))
                        return keydata.meta[j];

            }
        }
        return c;
    }

    public static final char CHARACTERS[] = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
        '#', '*', '+', '-', '(', ')', ',', '/', 'N', '.', 
        ' ', ';'
    };
    private static DialerKeyListener sInstance;

}
