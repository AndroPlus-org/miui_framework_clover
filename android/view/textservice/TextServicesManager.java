// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.textservice;

import android.os.*;
import com.android.internal.textservice.ITextServicesManager;
import java.util.Locale;

// Referenced classes of package android.view.textservice:
//            SpellCheckerSubtype, SpellCheckerInfo, SpellCheckerSession

public final class TextServicesManager
{

    private TextServicesManager()
        throws android.os.ServiceManager.ServiceNotFoundException
    {
    }

    public static TextServicesManager getInstance()
    {
        android/view/textservice/TextServicesManager;
        JVM INSTR monitorenter ;
        TextServicesManager textservicesmanager = sInstance;
        if(textservicesmanager != null)
            break MISSING_BLOCK_LABEL_23;
        textservicesmanager = JVM INSTR new #2   <Class TextServicesManager>;
        textservicesmanager.TextServicesManager();
        sInstance = textservicesmanager;
        textservicesmanager = sInstance;
        android/view/textservice/TextServicesManager;
        JVM INSTR monitorexit ;
        return textservicesmanager;
        Object obj;
        obj;
        IllegalStateException illegalstateexception = JVM INSTR new #53  <Class IllegalStateException>;
        illegalstateexception.IllegalStateException(((Throwable) (obj)));
        throw illegalstateexception;
        obj;
        android/view/textservice/TextServicesManager;
        JVM INSTR monitorexit ;
        throw obj;
    }

    private static String parseLanguageFromLocaleString(String s)
    {
        int i = s.indexOf('_');
        if(i < 0)
            return s;
        else
            return s.substring(0, i);
    }

    public SpellCheckerInfo getCurrentSpellChecker()
    {
        SpellCheckerInfo spellcheckerinfo;
        try
        {
            spellcheckerinfo = mService.getCurrentSpellChecker(null);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return spellcheckerinfo;
    }

    public SpellCheckerSubtype getCurrentSpellCheckerSubtype(boolean flag)
    {
        SpellCheckerSubtype spellcheckersubtype;
        try
        {
            spellcheckersubtype = mService.getCurrentSpellCheckerSubtype(null, flag);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return spellcheckersubtype;
    }

    public SpellCheckerInfo[] getEnabledSpellCheckers()
    {
        SpellCheckerInfo aspellcheckerinfo[];
        try
        {
            aspellcheckerinfo = mService.getEnabledSpellCheckers();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return aspellcheckerinfo;
    }

    public boolean isSpellCheckerEnabled()
    {
        boolean flag;
        try
        {
            flag = mService.isSpellCheckerEnabled();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public SpellCheckerSession newSpellCheckerSession(Bundle bundle, Locale locale, SpellCheckerSession.SpellCheckerSessionListener spellcheckersessionlistener, boolean flag)
    {
        SpellCheckerInfo spellcheckerinfo;
        SpellCheckerSubtype spellcheckersubtype;
        if(spellcheckersessionlistener == null)
            throw new NullPointerException();
        if(!flag && locale == null)
            throw new IllegalArgumentException("Locale should not be null if you don't refer settings.");
        if(flag && isSpellCheckerEnabled() ^ true)
            return null;
        try
        {
            spellcheckerinfo = mService.getCurrentSpellChecker(null);
        }
        // Misplaced declaration of an exception variable
        catch(Bundle bundle)
        {
            return null;
        }
        if(spellcheckerinfo == null)
            return null;
        spellcheckersubtype = null;
        if(!flag) goto _L2; else goto _L1
_L1:
        SpellCheckerSubtype spellcheckersubtype1;
        spellcheckersubtype = getCurrentSpellCheckerSubtype(true);
        if(spellcheckersubtype == null)
            return null;
        spellcheckersubtype1 = spellcheckersubtype;
        if(locale == null) goto _L4; else goto _L3
_L3:
        String s = parseLanguageFromLocaleString(spellcheckersubtype.getLocale());
        if(s.length() < 2) goto _L6; else goto _L5
_L5:
        spellcheckersubtype1 = spellcheckersubtype;
        if(!(locale.getLanguage().equals(s) ^ true)) goto _L4; else goto _L6
_L6:
        return null;
_L2:
        String s1;
        int i;
        s1 = locale.toString();
        i = 0;
_L10:
        spellcheckersubtype1 = spellcheckersubtype;
        if(i >= spellcheckerinfo.getSubtypeCount()) goto _L4; else goto _L7
_L7:
        String s2;
        spellcheckersubtype1 = spellcheckerinfo.getSubtypeAt(i);
        s = spellcheckersubtype1.getLocale();
        s2 = parseLanguageFromLocaleString(s);
        if(!s.equals(s1)) goto _L8; else goto _L4
_L4:
        if(spellcheckersubtype1 == null)
            return null;
        break; /* Loop/switch isn't completed */
_L8:
        SpellCheckerSubtype spellcheckersubtype2 = spellcheckersubtype;
        if(s2.length() >= 2)
        {
            spellcheckersubtype2 = spellcheckersubtype;
            if(locale.getLanguage().equals(s2))
                spellcheckersubtype2 = spellcheckersubtype1;
        }
        i++;
        spellcheckersubtype = spellcheckersubtype2;
        if(true) goto _L10; else goto _L9
_L9:
        locale = new SpellCheckerSession(spellcheckerinfo, mService, spellcheckersessionlistener);
        try
        {
            mService.getSpellCheckerService(spellcheckerinfo.getId(), spellcheckersubtype1.getLocale(), locale.getTextServicesSessionListener(), locale.getSpellCheckerSessionListener(), bundle);
        }
        // Misplaced declaration of an exception variable
        catch(Bundle bundle)
        {
            throw bundle.rethrowFromSystemServer();
        }
        return locale;
    }

    private static final boolean DBG = false;
    private static final String TAG = android/view/textservice/TextServicesManager.getSimpleName();
    private static TextServicesManager sInstance;
    private final ITextServicesManager mService = com.android.internal.textservice.ITextServicesManager.Stub.asInterface(ServiceManager.getServiceOrThrow("textservices"));

}
