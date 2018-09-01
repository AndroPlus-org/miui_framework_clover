// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.speech.tts;

import java.util.Iterator;
import java.util.Locale;

// Referenced classes of package android.speech.tts:
//            TtsEngines

class TtsEnginesInjector
{

    TtsEnginesInjector()
    {
    }

    static String findFirstEngineSupportLocale(TtsEngines ttsengines, Locale locale)
    {
        for(Iterator iterator = ttsengines.getEngines().iterator(); iterator.hasNext();)
        {
            TextToSpeech.EngineInfo engineinfo = (TextToSpeech.EngineInfo)iterator.next();
            if(ttsengines.getLocalePrefForEngine(engineinfo.name).equals(locale))
                return engineinfo.name;
        }

        return null;
    }

    static String getRecommendEngineForLocale(TtsEngines ttsengines, Locale locale)
    {
        if(locale.getLanguage().equals(Locale.CHINESE.getLanguage()))
        {
            if(ttsengines.isEngineInstalled("com.xiaomi.mibrain.speech"))
                return "com.xiaomi.mibrain.speech";
            if(ttsengines.isEngineInstalled("com.baidu.duersdk.opensdk"))
                return "com.baidu.duersdk.opensdk";
        }
        return null;
    }
}
