// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.util;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import java.util.ArrayList;

// Referenced classes of package com.google.android.util:
//            AbstractMessageParser, SmileyResources

public class SmileyParser extends AbstractMessageParser
{

    public SmileyParser(String s, SmileyResources smileyresources)
    {
        super(s, true, false, false, false, false, false);
        mRes = smileyresources;
    }

    protected AbstractMessageParser.Resources getResources()
    {
        return mRes;
    }

    public CharSequence getSpannableString(Context context)
    {
        SpannableStringBuilder spannablestringbuilder = new SpannableStringBuilder();
        if(getPartCount() == 0)
            return "";
        ArrayList arraylist = getPart(0).getTokens();
        int i = arraylist.size();
        for(int j = 0; j < i; j++)
        {
            AbstractMessageParser.Token token = (AbstractMessageParser.Token)arraylist.get(j);
            int k = spannablestringbuilder.length();
            spannablestringbuilder.append(token.getRawText());
            if(token.getType() != AbstractMessageParser.Token.Type.SMILEY)
                continue;
            int l = mRes.getSmileyRes(token.getRawText());
            if(l != -1)
                spannablestringbuilder.setSpan(new ImageSpan(context, l), k, spannablestringbuilder.length(), 33);
        }

        return spannablestringbuilder;
    }

    private SmileyResources mRes;
}
