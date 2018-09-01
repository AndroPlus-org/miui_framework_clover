// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.soundtrigger;

import android.util.ArraySet;
import java.util.Locale;

public class KeyphraseMetadata
{

    public KeyphraseMetadata(int i, String s, ArraySet arrayset, int j)
    {
        id = i;
        keyphrase = s;
        supportedLocales = arrayset;
        recognitionModeFlags = j;
    }

    public boolean supportsLocale(Locale locale)
    {
        boolean flag;
        if(!supportedLocales.isEmpty())
            flag = supportedLocales.contains(locale);
        else
            flag = true;
        return flag;
    }

    public boolean supportsPhrase(String s)
    {
        boolean flag;
        if(!keyphrase.isEmpty())
            flag = keyphrase.equalsIgnoreCase(s);
        else
            flag = true;
        return flag;
    }

    public String toString()
    {
        return (new StringBuilder()).append("id=").append(id).append(", keyphrase=").append(keyphrase).append(", supported-locales=").append(supportedLocales).append(", recognition-modes=").append(recognitionModeFlags).toString();
    }

    public final int id;
    public final String keyphrase;
    public final int recognitionModeFlags;
    public final ArraySet supportedLocales;
}
