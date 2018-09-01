// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.inputmethod;

import android.app.AppOpsManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.*;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.LocaleList;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.*;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodSubtype;
import android.view.textservice.SpellCheckerInfo;
import android.view.textservice.TextServicesManager;
import java.util.*;

// Referenced classes of package com.android.internal.inputmethod:
//            LocaleUtils

public class InputMethodUtils
{
    private static final class InputMethodListBuilder
    {

        public ArrayList build()
        {
            return new ArrayList(mInputMethodSet);
        }

        public InputMethodListBuilder fillAuxiliaryImes(ArrayList arraylist, Context context)
        {
            for(Iterator iterator = mInputMethodSet.iterator(); iterator.hasNext();)
                if(((InputMethodInfo)iterator.next()).isAuxiliaryIme())
                    return this;

            boolean flag = false;
            for(int i = 0; i < arraylist.size(); i++)
            {
                InputMethodInfo inputmethodinfo = (InputMethodInfo)arraylist.get(i);
                if(InputMethodUtils._2D_wrap0(inputmethodinfo, context, true))
                {
                    mInputMethodSet.add(inputmethodinfo);
                    flag = true;
                }
            }

            if(flag)
                return this;
            for(int j = 0; j < arraylist.size(); j++)
            {
                InputMethodInfo inputmethodinfo1 = (InputMethodInfo)arraylist.get(j);
                if(InputMethodUtils._2D_wrap0(inputmethodinfo1, context, false))
                    mInputMethodSet.add(inputmethodinfo1);
            }

            return this;
        }

        public InputMethodListBuilder fillImes(ArrayList arraylist, Context context, boolean flag, Locale locale, boolean flag1, String s)
        {
            for(int i = 0; i < arraylist.size(); i++)
            {
                InputMethodInfo inputmethodinfo = (InputMethodInfo)arraylist.get(i);
                if(InputMethodUtils.isSystemImeThatHasSubtypeOf(inputmethodinfo, context, flag, locale, flag1, s))
                    mInputMethodSet.add(inputmethodinfo);
            }

            return this;
        }

        public boolean isEmpty()
        {
            return mInputMethodSet.isEmpty();
        }

        private final LinkedHashSet mInputMethodSet;

        private InputMethodListBuilder()
        {
            mInputMethodSet = new LinkedHashSet();
        }

        InputMethodListBuilder(InputMethodListBuilder inputmethodlistbuilder)
        {
            this();
        }
    }

    public static class InputMethodSettings
    {

        private void addSubtypeToHistory(String s, String s1)
        {
            List list = loadInputMethodAndSubtypeHistoryLocked();
            Iterator iterator = list.iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                Pair pair = (Pair)iterator.next();
                if(!((String)pair.first).equals(s))
                    continue;
                list.remove(pair);
                break;
            } while(true);
            saveSubtypeHistory(list, s, s1);
        }

        private static void buildEnabledInputMethodsSettingString(StringBuilder stringbuilder, Pair pair)
        {
            stringbuilder.append((String)pair.first);
            String s;
            for(pair = ((ArrayList)pair.second).iterator(); pair.hasNext(); stringbuilder.append(';').append(s))
                s = (String)pair.next();

        }

        public static List buildInputMethodsAndSubtypeList(String s, android.text.TextUtils.SimpleStringSplitter simplestringsplitter, android.text.TextUtils.SimpleStringSplitter simplestringsplitter1)
        {
            ArrayList arraylist = new ArrayList();
            if(TextUtils.isEmpty(s))
                return arraylist;
            simplestringsplitter.setString(s);
            do
            {
                if(!simplestringsplitter.hasNext())
                    break;
                simplestringsplitter1.setString(simplestringsplitter.next());
                if(simplestringsplitter1.hasNext())
                {
                    ArrayList arraylist1 = new ArrayList();
                    s = simplestringsplitter1.next();
                    for(; simplestringsplitter1.hasNext(); arraylist1.add(simplestringsplitter1.next()));
                    arraylist.add(new Pair(s, arraylist1));
                }
            } while(true);
            return arraylist;
        }

        public static String buildInputMethodsSettingString(List list)
        {
            StringBuilder stringbuilder = new StringBuilder();
            boolean flag = false;
            for(Iterator iterator = list.iterator(); iterator.hasNext();)
            {
                list = (Pair)iterator.next();
                if(flag)
                    stringbuilder.append(':');
                buildEnabledInputMethodsSettingString(stringbuilder, list);
                flag = true;
            }

            return stringbuilder.toString();
        }

        private ArrayList createEnabledInputMethodListLocked(List list)
        {
            ArrayList arraylist = new ArrayList();
            list = list.iterator();
            do
            {
                if(!list.hasNext())
                    break;
                Object obj = (Pair)list.next();
                obj = (InputMethodInfo)mMethodMap.get(((Pair) (obj)).first);
                if(obj != null)
                    arraylist.add(obj);
            } while(true);
            return arraylist;
        }

        private boolean getBoolean(String s, boolean flag)
        {
            boolean flag1 = true;
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            if(getInt(s, i) == 1)
                flag = flag1;
            else
                flag = false;
            return flag;
        }

        private String getEnabledSubtypeHashCodeForInputMethodAndSubtypeLocked(List list, String s, String s1)
        {
            list = list.iterator();
            Object obj;
            do
            {
                if(!list.hasNext())
                    break MISSING_BLOCK_LABEL_212;
                obj = (Pair)list.next();
            } while(!((String)((Pair) (obj)).first).equals(s));
            obj = (ArrayList)((Pair) (obj)).second;
            list = (InputMethodInfo)mMethodMap.get(s);
            if(((ArrayList) (obj)).size() == 0)
            {
                if(list != null && list.getSubtypeCount() > 0)
                {
                    list = InputMethodUtils.getImplicitlyApplicableSubtypesLocked(mRes, list);
                    if(list != null)
                    {
                        int i = list.size();
                        for(int j = 0; j < i; j++)
                            if(String.valueOf(((InputMethodSubtype)list.get(j)).hashCode()).equals(s1))
                                return s1;

                    }
                }
                break MISSING_BLOCK_LABEL_208;
            }
            obj = ((Iterable) (obj)).iterator();
            do
            {
                if(!((Iterator) (obj)).hasNext())
                    break MISSING_BLOCK_LABEL_208;
                s = (String)((Iterator) (obj)).next();
            } while(!s.equals(s1));
            if(InputMethodUtils.isValidSubtypeId(list, Integer.parseInt(s1)))
                return s;
            try
            {
                list = InputMethodUtils._2D_get0();
            }
            // Misplaced declaration of an exception variable
            catch(List list)
            {
                return InputMethodUtils._2D_get0();
            }
            return list;
            return InputMethodUtils._2D_get0();
            return null;
        }

        private int getInt(String s, int i)
        {
            if(mCopyOnWrite && mCopyOnWriteDataStore.containsKey(s))
            {
                s = (String)mCopyOnWriteDataStore.get(s);
                if(s != null)
                    i = Integer.parseInt(s);
                else
                    i = 0;
                return i;
            } else
            {
                return android.provider.Settings.Secure.getIntForUser(mResolver, s, i, mCurrentUserId);
            }
        }

        private Pair getLastSubtypeForInputMethodLockedInternal(String s)
        {
            List list = getEnabledInputMethodsAndSubtypeListLocked();
            for(Iterator iterator = loadInputMethodAndSubtypeHistoryLocked().iterator(); iterator.hasNext();)
            {
                Object obj = (Pair)iterator.next();
                String s1 = (String)((Pair) (obj)).first;
                if(TextUtils.isEmpty(s) || s1.equals(s))
                {
                    obj = getEnabledSubtypeHashCodeForInputMethodAndSubtypeLocked(list, s1, (String)((Pair) (obj)).second);
                    if(!TextUtils.isEmpty(((CharSequence) (obj))))
                        return new Pair(s1, obj);
                }
            }

            return null;
        }

        private int getSelectedInputMethodSubtypeHashCode()
        {
            return getInt("selected_input_method_subtype", -1);
        }

        private String getString(String s, String s1)
        {
            if(mCopyOnWrite && mCopyOnWriteDataStore.containsKey(s))
                s = (String)mCopyOnWriteDataStore.get(s);
            else
                s = android.provider.Settings.Secure.getStringForUser(mResolver, s, mCurrentUserId);
            if(s == null)
                s = s1;
            return s;
        }

        private String getSubtypeHistoryStr()
        {
            return getString("input_methods_subtype_history", "");
        }

        private List loadInputMethodAndSubtypeHistoryLocked()
        {
            ArrayList arraylist = new ArrayList();
            String s = getSubtypeHistoryStr();
            if(TextUtils.isEmpty(s))
                return arraylist;
            mInputMethodSplitter.setString(s);
            do
            {
                if(!mInputMethodSplitter.hasNext())
                    break;
                String s1 = mInputMethodSplitter.next();
                mSubtypeSplitter.setString(s1);
                if(mSubtypeSplitter.hasNext())
                {
                    String s2 = InputMethodUtils._2D_get0();
                    String s3 = mSubtypeSplitter.next();
                    if(mSubtypeSplitter.hasNext())
                        s2 = mSubtypeSplitter.next();
                    arraylist.add(new Pair(s3, s2));
                }
            } while(true);
            return arraylist;
        }

        private void putBoolean(String s, boolean flag)
        {
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            putInt(s, i);
        }

        private void putEnabledInputMethodsStr(String s)
        {
            if(TextUtils.isEmpty(s))
                putString("enabled_input_methods", null);
            else
                putString("enabled_input_methods", s);
            if(s == null)
                s = "";
            mEnabledInputMethodsStrCache = s;
        }

        private void putInt(String s, int i)
        {
            if(mCopyOnWrite)
                mCopyOnWriteDataStore.put(s, String.valueOf(i));
            else
                android.provider.Settings.Secure.putIntForUser(mResolver, s, i, mCurrentUserId);
        }

        private void putString(String s, String s1)
        {
            if(mCopyOnWrite)
                mCopyOnWriteDataStore.put(s, s1);
            else
                android.provider.Settings.Secure.putStringForUser(mResolver, s, s1, mCurrentUserId);
        }

        private void putSubtypeHistoryStr(String s)
        {
            if(TextUtils.isEmpty(s))
                putString("input_methods_subtype_history", null);
            else
                putString("input_methods_subtype_history", s);
        }

        private void saveSubtypeHistory(List list, String s, String s1)
        {
            StringBuilder stringbuilder = new StringBuilder();
            boolean flag = false;
            boolean flag1 = flag;
            if(!TextUtils.isEmpty(s))
            {
                flag1 = flag;
                if(TextUtils.isEmpty(s1) ^ true)
                {
                    stringbuilder.append(s).append(';').append(s1);
                    flag1 = true;
                }
            }
            Iterator iterator = list.iterator();
            while(iterator.hasNext()) 
            {
                list = (Pair)iterator.next();
                s1 = (String)((Pair) (list)).first;
                s = (String)((Pair) (list)).second;
                list = s;
                if(TextUtils.isEmpty(s))
                    list = InputMethodUtils._2D_get0();
                if(flag1)
                    stringbuilder.append(':');
                else
                    flag1 = true;
                stringbuilder.append(s1).append(';').append(list);
            }
            putSubtypeHistoryStr(stringbuilder.toString());
        }

        public void appendAndPutEnabledInputMethodLocked(String s, boolean flag)
        {
            if(flag)
                getEnabledInputMethodsStr();
            if(TextUtils.isEmpty(mEnabledInputMethodsStrCache))
                putEnabledInputMethodsStr(s);
            else
                putEnabledInputMethodsStr((new StringBuilder()).append(mEnabledInputMethodsStrCache).append(':').append(s).toString());
        }

        public boolean buildAndPutEnabledInputMethodsStrRemovingIdLocked(StringBuilder stringbuilder, List list, String s)
        {
            boolean flag = false;
            boolean flag1 = false;
            for(list = list.iterator(); list.hasNext();)
            {
                Pair pair = (Pair)list.next();
                if(((String)pair.first).equals(s))
                {
                    flag = true;
                } else
                {
                    if(flag1)
                        stringbuilder.append(':');
                    else
                        flag1 = true;
                    buildEnabledInputMethodsSettingString(stringbuilder, pair);
                }
            }

            if(flag)
                putEnabledInputMethodsStr(stringbuilder.toString());
            return flag;
        }

        public void dumpLocked(Printer printer, String s)
        {
            printer.println((new StringBuilder()).append(s).append("mCurrentUserId=").append(mCurrentUserId).toString());
            printer.println((new StringBuilder()).append(s).append("mCurrentProfileIds=").append(Arrays.toString(mCurrentProfileIds)).toString());
            printer.println((new StringBuilder()).append(s).append("mCopyOnWrite=").append(mCopyOnWrite).toString());
            printer.println((new StringBuilder()).append(s).append("mEnabledInputMethodsStrCache=").append(mEnabledInputMethodsStrCache).toString());
        }

        public int getCurrentUserId()
        {
            return mCurrentUserId;
        }

        public ArrayList getEnabledInputMethodListLocked()
        {
            return createEnabledInputMethodListLocked(getEnabledInputMethodsAndSubtypeListLocked());
        }

        public List getEnabledInputMethodSubtypeListLocked(Context context, InputMethodInfo inputmethodinfo, boolean flag)
        {
            List list = getEnabledInputMethodSubtypeListLocked(inputmethodinfo);
            Object obj = list;
            if(flag)
            {
                obj = list;
                if(list.isEmpty())
                    obj = InputMethodUtils.getImplicitlyApplicableSubtypesLocked(context.getResources(), inputmethodinfo);
            }
            return InputMethodSubtype.sort(context, 0, inputmethodinfo, ((List) (obj)));
        }

        public List getEnabledInputMethodSubtypeListLocked(InputMethodInfo inputmethodinfo)
        {
            ArrayList arraylist;
label0:
            {
                Object obj = getEnabledInputMethodsAndSubtypeListLocked();
                arraylist = new ArrayList();
                if(inputmethodinfo == null)
                    break label0;
                Iterator iterator = ((Iterable) (obj)).iterator();
                InputMethodInfo inputmethodinfo1;
                do
                {
                    if(!iterator.hasNext())
                        break label0;
                    obj = (Pair)iterator.next();
                    inputmethodinfo1 = (InputMethodInfo)mMethodMap.get(((Pair) (obj)).first);
                } while(inputmethodinfo1 == null || !inputmethodinfo1.getId().equals(inputmethodinfo.getId()));
                int i = inputmethodinfo1.getSubtypeCount();
label1:
                for(int j = 0; j < i; j++)
                {
                    InputMethodSubtype inputmethodsubtype = inputmethodinfo1.getSubtypeAt(j);
                    inputmethodinfo = ((ArrayList)((Pair) (obj)).second).iterator();
                    do
                    {
                        if(!inputmethodinfo.hasNext())
                            continue label1;
                        String s = (String)inputmethodinfo.next();
                        if(String.valueOf(inputmethodsubtype.hashCode()).equals(s))
                            arraylist.add(inputmethodsubtype);
                    } while(true);
                }

            }
            return arraylist;
        }

        public List getEnabledInputMethodsAndSubtypeListLocked()
        {
            return buildInputMethodsAndSubtypeList(getEnabledInputMethodsStr(), mInputMethodSplitter, mSubtypeSplitter);
        }

        public String getEnabledInputMethodsStr()
        {
            mEnabledInputMethodsStrCache = getString("enabled_input_methods", "");
            String s;
            if(mEnabledInputMethodsStrCache != null)
                s = mEnabledInputMethodsStrCache;
            else
                s = "";
            return s;
        }

        public HashMap getExplicitlyOrImplicitlyEnabledInputMethodsAndSubtypeListLocked(Context context)
        {
            HashMap hashmap = new HashMap();
            InputMethodInfo inputmethodinfo;
            for(Iterator iterator = getEnabledInputMethodListLocked().iterator(); iterator.hasNext(); hashmap.put(inputmethodinfo, getEnabledInputMethodSubtypeListLocked(context, inputmethodinfo, true)))
                inputmethodinfo = (InputMethodInfo)iterator.next();

            return hashmap;
        }

        public Pair getLastInputMethodAndSubtypeLocked()
        {
            return getLastSubtypeForInputMethodLockedInternal(null);
        }

        public String getLastSubtypeForInputMethodLocked(String s)
        {
            s = getLastSubtypeForInputMethodLockedInternal(s);
            if(s != null)
                return (String)((Pair) (s)).second;
            else
                return null;
        }

        public String getSelectedInputMethod()
        {
            return getString("default_input_method", null);
        }

        public int getSelectedInputMethodSubtypeId(String s)
        {
            s = (InputMethodInfo)mMethodMap.get(s);
            if(s == null)
                return -1;
            else
                return InputMethodUtils.getSubtypeIdFromHashCode(s, getSelectedInputMethodSubtypeHashCode());
        }

        public boolean isCurrentProfile(int i)
        {
            this;
            JVM INSTR monitorenter ;
            int j = mCurrentUserId;
            if(i != j)
                break MISSING_BLOCK_LABEL_16;
            this;
            JVM INSTR monitorexit ;
            return true;
            j = 0;
_L2:
            int k;
            if(j >= mCurrentProfileIds.length)
                break; /* Loop/switch isn't completed */
            k = mCurrentProfileIds[j];
            if(i != k)
                break MISSING_BLOCK_LABEL_43;
            this;
            JVM INSTR monitorexit ;
            return true;
            j++;
            if(true) goto _L2; else goto _L1
_L1:
            return false;
            Exception exception;
            exception;
            throw exception;
        }

        public boolean isShowImeWithHardKeyboardEnabled()
        {
            return getBoolean("show_ime_with_hard_keyboard", false);
        }

        public boolean isSubtypeSelected()
        {
            boolean flag;
            if(getSelectedInputMethodSubtypeHashCode() != -1)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public void putSelectedInputMethod(String s)
        {
            putString("default_input_method", s);
        }

        public void putSelectedSubtype(int i)
        {
            putInt("selected_input_method_subtype", i);
        }

        public void saveCurrentInputMethodAndSubtypeToHistory(String s, InputMethodSubtype inputmethodsubtype)
        {
            String s1 = InputMethodUtils._2D_get0();
            if(inputmethodsubtype != null)
                s1 = String.valueOf(inputmethodsubtype.hashCode());
            if(InputMethodUtils.canAddToLastInputMethod(inputmethodsubtype))
                addSubtypeToHistory(s, s1);
        }

        public void setCurrentProfileIds(int ai[])
        {
            this;
            JVM INSTR monitorenter ;
            mCurrentProfileIds = ai;
            this;
            JVM INSTR monitorexit ;
            return;
            ai;
            throw ai;
        }

        public void setShowImeWithHardKeyboard(boolean flag)
        {
            putBoolean("show_ime_with_hard_keyboard", flag);
        }

        public void switchCurrentUser(int i, boolean flag)
        {
            if(mCurrentUserId != i || mCopyOnWrite != flag)
            {
                mCopyOnWriteDataStore.clear();
                mEnabledInputMethodsStrCache = "";
            }
            mCurrentUserId = i;
            mCopyOnWrite = flag;
        }

        private boolean mCopyOnWrite;
        private final HashMap mCopyOnWriteDataStore = new HashMap();
        private int mCurrentProfileIds[];
        private int mCurrentUserId;
        private String mEnabledInputMethodsStrCache;
        private final android.text.TextUtils.SimpleStringSplitter mInputMethodSplitter = new android.text.TextUtils.SimpleStringSplitter(':');
        private final ArrayList mMethodList;
        private final HashMap mMethodMap;
        private final Resources mRes;
        private final ContentResolver mResolver;
        private final android.text.TextUtils.SimpleStringSplitter mSubtypeSplitter = new android.text.TextUtils.SimpleStringSplitter(';');

        public InputMethodSettings(Resources resources, ContentResolver contentresolver, HashMap hashmap, ArrayList arraylist, int i, boolean flag)
        {
            mCopyOnWrite = false;
            mEnabledInputMethodsStrCache = "";
            mCurrentProfileIds = new int[0];
            mRes = resources;
            mResolver = contentresolver;
            mMethodMap = hashmap;
            mMethodList = arraylist;
            switchCurrentUser(i, flag);
        }
    }


    static String _2D_get0()
    {
        return NOT_A_SUBTYPE_ID_STR;
    }

    static boolean _2D_wrap0(InputMethodInfo inputmethodinfo, Context context, boolean flag)
    {
        return isSystemAuxilialyImeThatHasAutomaticSubtype(inputmethodinfo, context, flag);
    }

    private InputMethodUtils()
    {
    }

    public static String buildInputMethodsAndSubtypesString(ArrayMap arraymap)
    {
        ArrayList arraylist = new ArrayList(4);
        Object obj;
        for(Iterator iterator = arraymap.entrySet().iterator(); iterator.hasNext(); arraylist.add(new Pair(arraymap, obj)))
        {
            obj = (java.util.Map.Entry)iterator.next();
            arraymap = (String)((java.util.Map.Entry) (obj)).getKey();
            ArraySet arrayset = (ArraySet)((java.util.Map.Entry) (obj)).getValue();
            obj = new ArrayList(2);
            if(arrayset != null)
                ((ArrayList) (obj)).addAll(arrayset);
        }

        return InputMethodSettings.buildInputMethodsSettingString(arraylist);
    }

    public static boolean canAddToLastInputMethod(InputMethodSubtype inputmethodsubtype)
    {
        if(inputmethodsubtype == null)
            return true;
        else
            return inputmethodsubtype.isAuxiliary() ^ true;
    }

    public static boolean checkIfPackageBelongsToUid(AppOpsManager appopsmanager, int i, String s)
    {
        try
        {
            appopsmanager.checkPackage(i, s);
        }
        // Misplaced declaration of an exception variable
        catch(AppOpsManager appopsmanager)
        {
            return false;
        }
        return true;
    }

    public static Locale constructLocaleFromString(String s)
    {
        if(TextUtils.isEmpty(s))
            return null;
        s = s.split("_", 3);
        if(s.length >= 1 && "tl".equals(s[0]))
            s[0] = "fil";
        if(s.length == 1)
            return new Locale(s[0]);
        if(s.length == 2)
            return new Locale(s[0], s[1]);
        if(s.length == 3)
            return new Locale(s[0], s[1], s[2]);
        else
            return null;
    }

    public static boolean containsSubtypeOf(InputMethodInfo inputmethodinfo, Locale locale, boolean flag, String s)
    {
        int i;
        int j;
        if(locale == null)
            return false;
        i = inputmethodinfo.getSubtypeCount();
        j = 0;
_L3:
        InputMethodSubtype inputmethodsubtype;
        Locale locale1;
        if(j >= i)
            break; /* Loop/switch isn't completed */
        inputmethodsubtype = inputmethodinfo.getSubtypeAt(j);
          goto _L1
_L5:
        j++;
        if(true) goto _L3; else goto _L2
_L1:
        if((flag ? (locale1 = inputmethodsubtype.getLocaleObject()) == null || TextUtils.equals(locale1.getLanguage(), locale.getLanguage()) ^ true || TextUtils.equals(locale1.getCountry(), locale.getCountry()) ^ true : !TextUtils.equals((new Locale(getLanguageFromLocaleString(inputmethodsubtype.getLocale()))).getLanguage(), locale.getLanguage())) || s != SUBTYPE_MODE_ANY && !TextUtils.isEmpty(s) && !s.equalsIgnoreCase(inputmethodsubtype.getMode())) goto _L5; else goto _L4
_L4:
        return true;
_L2:
        return false;
    }

    public static InputMethodSubtype findLastResortApplicableSubtypeLocked(Resources resources, List list, String s, String s1, boolean flag)
    {
        if(list == null || list.size() == 0)
            return null;
        String s2 = s1;
        if(TextUtils.isEmpty(s1))
            s2 = resources.getConfiguration().locale.toString();
        String s3 = getLanguageFromLocaleString(s2);
        boolean flag1 = false;
        InputMethodSubtype inputmethodsubtype = null;
        resources = null;
        int i = list.size();
        int j = 0;
        do
        {
label0:
            {
label1:
                {
                    InputMethodSubtype inputmethodsubtype1 = inputmethodsubtype;
                    s1 = resources;
                    String s5;
                    InputMethodSubtype inputmethodsubtype2;
                    Object obj;
                    boolean flag2;
                    if(j < i)
                    {
                        inputmethodsubtype1 = (InputMethodSubtype)list.get(j);
                        String s4 = inputmethodsubtype1.getLocale();
                        s5 = getLanguageFromLocaleString(s4);
                        if(s != null)
                        {
                            inputmethodsubtype2 = inputmethodsubtype;
                            obj = resources;
                            flag2 = flag1;
                            if(!((InputMethodSubtype)list.get(j)).getMode().equalsIgnoreCase(s))
                                break label0;
                        }
                        s1 = resources;
                        if(resources == null)
                            s1 = inputmethodsubtype1;
                        if(!s2.equals(s4))
                            break label1;
                    }
                    if(inputmethodsubtype1 == null && flag)
                        return s1;
                    else
                        return inputmethodsubtype1;
                }
                inputmethodsubtype2 = inputmethodsubtype;
                obj = s1;
                flag2 = flag1;
                if(!flag1)
                {
                    inputmethodsubtype2 = inputmethodsubtype;
                    obj = s1;
                    flag2 = flag1;
                    if(s3.equals(s5))
                    {
                        flag2 = true;
                        obj = s1;
                        inputmethodsubtype2 = inputmethodsubtype1;
                    }
                }
            }
            j++;
            inputmethodsubtype = inputmethodsubtype2;
            resources = ((Resources) (obj));
            flag1 = flag2;
        } while(true);
    }

    public static String getApiCallStack()
    {
        String s;
        StackTraceElement astacktraceelement[];
        int i;
        s = "";
        try
        {
            RuntimeException runtimeexception = JVM INSTR new #286 <Class RuntimeException>;
            runtimeexception.RuntimeException();
            throw runtimeexception;
        }
        catch(RuntimeException runtimeexception1)
        {
            astacktraceelement = runtimeexception1.getStackTrace();
            i = 1;
        }
_L3:
        String s1;
        if(i >= astacktraceelement.length)
            break; /* Loop/switch isn't completed */
        s1 = astacktraceelement[i].toString();
          goto _L1
_L4:
        s = s1;
        i++;
        if(true) goto _L3; else goto _L2
_L1:
        if(!TextUtils.isEmpty(s) && s1.indexOf("Transact(") >= 0) goto _L2; else goto _L4
_L2:
        return s;
    }

    public static ArrayList getDefaultEnabledImes(Context context, ArrayList arraylist)
    {
        Locale locale = getFallbackLocaleForDefaultIme(arraylist, context);
        Locale locale1 = getSystemLocaleFromContext(context);
        return getMinimumKeyboardSetWithSystemLocale(arraylist, context, locale1, locale).fillImes(arraylist, context, true, locale1, true, SUBTYPE_MODE_ANY).fillAuxiliaryImes(arraylist, context).build();
    }

    public static Locale getFallbackLocaleForDefaultIme(ArrayList arraylist, Context context)
    {
        Locale alocale[] = SEARCH_ORDER_OF_FALLBACK_LOCALES;
        int i = alocale.length;
        for(int j = 0; j < i; j++)
        {
            Locale locale = alocale[j];
            for(int l = 0; l < arraylist.size(); l++)
                if(isSystemImeThatHasSubtypeOf((InputMethodInfo)arraylist.get(l), context, true, locale, true, "keyboard"))
                    return locale;

        }

        alocale = SEARCH_ORDER_OF_FALLBACK_LOCALES;
        i = alocale.length;
        for(int k = 0; k < i; k++)
        {
            Locale locale1 = alocale[k];
            for(int i1 = 0; i1 < arraylist.size(); i1++)
                if(isSystemImeThatHasSubtypeOf((InputMethodInfo)arraylist.get(i1), context, false, locale1, true, "keyboard"))
                    return locale1;

        }

        Slog.w("InputMethodUtils", (new StringBuilder()).append("Found no fallback locale. imis=").append(Arrays.toString(arraylist.toArray())).toString());
        return null;
    }

    public static CharSequence getImeAndSubtypeDisplayName(Context context, InputMethodInfo inputmethodinfo, InputMethodSubtype inputmethodsubtype)
    {
        CharSequence charsequence = inputmethodinfo.loadLabel(context.getPackageManager());
        CharSequence charsequence1 = charsequence;
        if(inputmethodsubtype != null)
        {
            inputmethodinfo = inputmethodsubtype.getDisplayName(context, inputmethodinfo.getPackageName(), inputmethodinfo.getServiceInfo().applicationInfo);
            if(TextUtils.isEmpty(charsequence))
                context = "";
            else
                context = (new StringBuilder()).append(" - ").append(charsequence).toString();
            charsequence1 = TextUtils.concat(new CharSequence[] {
                inputmethodinfo, context
            });
        }
        return charsequence1;
    }

    public static ArrayList getImplicitlyApplicableSubtypesLocked(Resources resources, InputMethodInfo inputmethodinfo)
    {
        LocaleList localelist = resources.getConfiguration().getLocales();
        Object obj = sCacheLock;
        obj;
        JVM INSTR monitorenter ;
        if(!localelist.equals(sCachedSystemLocales) || sCachedInputMethodInfo != inputmethodinfo)
            break MISSING_BLOCK_LABEL_46;
        resources = new ArrayList(sCachedResult);
        obj;
        JVM INSTR monitorexit ;
        return resources;
        obj;
        JVM INSTR monitorexit ;
        obj = getImplicitlyApplicableSubtypesLockedImpl(resources, inputmethodinfo);
        resources = ((Resources) (sCacheLock));
        resources;
        JVM INSTR monitorenter ;
        sCachedSystemLocales = localelist;
        sCachedInputMethodInfo = inputmethodinfo;
        inputmethodinfo = JVM INSTR new #121 <Class ArrayList>;
        inputmethodinfo.ArrayList(((Collection) (obj)));
        sCachedResult = inputmethodinfo;
        resources;
        JVM INSTR monitorexit ;
        return ((ArrayList) (obj));
        resources;
        throw resources;
        inputmethodinfo;
        throw inputmethodinfo;
    }

    private static ArrayList getImplicitlyApplicableSubtypesLockedImpl(Resources resources, InputMethodInfo inputmethodinfo)
    {
        ArrayList arraylist = getSubtypes(inputmethodinfo);
        inputmethodinfo = resources.getConfiguration().getLocales();
        String s = inputmethodinfo.get(0).toString();
        if(TextUtils.isEmpty(s))
            return new ArrayList();
        int i = arraylist.size();
        Object obj = new HashMap();
        for(int k = 0; k < i; k++)
        {
            InputMethodSubtype inputmethodsubtype = (InputMethodSubtype)arraylist.get(k);
            if(!inputmethodsubtype.overridesImplicitlyEnabledSubtype())
                continue;
            String s1 = inputmethodsubtype.getMode();
            if(!((HashMap) (obj)).containsKey(s1))
                ((HashMap) (obj)).put(s1, inputmethodsubtype);
        }

        if(((HashMap) (obj)).size() > 0)
            return new ArrayList(((HashMap) (obj)).values());
        HashMap hashmap = new HashMap();
        ArrayList arraylist1 = new ArrayList();
        int l = 0;
        while(l < i) 
        {
            InputMethodSubtype inputmethodsubtype1 = (InputMethodSubtype)arraylist.get(l);
            obj = inputmethodsubtype1.getMode();
            if("keyboard".equals(obj))
            {
                arraylist1.add(inputmethodsubtype1);
            } else
            {
                if(!hashmap.containsKey(obj))
                    hashmap.put(obj, new ArrayList());
                ((ArrayList)hashmap.get(obj)).add(inputmethodsubtype1);
            }
            l++;
        }
        obj = new ArrayList();
        LocaleUtils.filterByLanguage(arraylist1, sSubtypeToLocale, inputmethodinfo, ((ArrayList) (obj)));
        if(!((ArrayList) (obj)).isEmpty())
        {
            boolean flag1 = false;
            int j1 = ((ArrayList) (obj)).size();
            int j = 0;
label0:
            do
            {
label1:
                {
                    boolean flag = flag1;
                    if(j < j1)
                    {
                        if(!((InputMethodSubtype)((ArrayList) (obj)).get(j)).containsExtraValueKey("AsciiCapable"))
                            break label1;
                        flag = true;
                    }
                    if(!flag)
                    {
                        j = arraylist1.size();
                        for(int i1 = 0; i1 < j; i1++)
                        {
                            InputMethodSubtype inputmethodsubtype2 = (InputMethodSubtype)arraylist1.get(i1);
                            if("keyboard".equals(inputmethodsubtype2.getMode()) && inputmethodsubtype2.containsExtraValueKey("EnabledWhenDefaultIsNotAsciiCapable"))
                                ((ArrayList) (obj)).add(inputmethodsubtype2);
                        }

                    }
                    break label0;
                }
                j++;
            } while(true);
        }
        if(((ArrayList) (obj)).isEmpty())
        {
            resources = findLastResortApplicableSubtypeLocked(resources, arraylist, "keyboard", s, true);
            if(resources != null)
                ((ArrayList) (obj)).add(resources);
        }
        for(resources = hashmap.values().iterator(); resources.hasNext(); LocaleUtils.filterByLanguage((ArrayList)resources.next(), sSubtypeToLocale, inputmethodinfo, ((ArrayList) (obj))));
        return ((ArrayList) (obj));
    }

    public static String getLanguageFromLocaleString(String s)
    {
        int i = s.indexOf('_');
        if(i < 0)
            return s;
        else
            return s.substring(0, i);
    }

    private static InputMethodListBuilder getMinimumKeyboardSetWithSystemLocale(ArrayList arraylist, Context context, Locale locale, Locale locale1)
    {
        InputMethodListBuilder inputmethodlistbuilder = new InputMethodListBuilder(null);
        inputmethodlistbuilder.fillImes(arraylist, context, true, locale, true, "keyboard");
        if(!inputmethodlistbuilder.isEmpty())
            return inputmethodlistbuilder;
        inputmethodlistbuilder.fillImes(arraylist, context, true, locale, false, "keyboard");
        if(!inputmethodlistbuilder.isEmpty())
            return inputmethodlistbuilder;
        inputmethodlistbuilder.fillImes(arraylist, context, true, locale1, true, "keyboard");
        if(!inputmethodlistbuilder.isEmpty())
            return inputmethodlistbuilder;
        inputmethodlistbuilder.fillImes(arraylist, context, true, locale1, false, "keyboard");
        if(!inputmethodlistbuilder.isEmpty())
            return inputmethodlistbuilder;
        inputmethodlistbuilder.fillImes(arraylist, context, false, locale1, true, "keyboard");
        if(!inputmethodlistbuilder.isEmpty())
            return inputmethodlistbuilder;
        inputmethodlistbuilder.fillImes(arraylist, context, false, locale1, false, "keyboard");
        if(!inputmethodlistbuilder.isEmpty())
        {
            return inputmethodlistbuilder;
        } else
        {
            Slog.w("InputMethodUtils", (new StringBuilder()).append("No software keyboard is found. imis=").append(Arrays.toString(arraylist.toArray())).append(" systemLocale=").append(locale).append(" fallbackLocale=").append(locale1).toString());
            return inputmethodlistbuilder;
        }
    }

    public static InputMethodInfo getMostApplicableDefaultIME(List list)
    {
        if(list == null || list.isEmpty())
            return null;
        int i = list.size();
        int j = -1;
        do
        {
            if(i <= 0)
                break;
            int k = i - 1;
            InputMethodInfo inputmethodinfo = (InputMethodInfo)list.get(k);
            i = k;
            if(!inputmethodinfo.isAuxiliaryIme())
            {
                if(isSystemIme(inputmethodinfo) && containsSubtypeOf(inputmethodinfo, ENGLISH_LOCALE, false, "keyboard"))
                    return inputmethodinfo;
                i = k;
                if(j < 0)
                {
                    i = k;
                    if(isSystemIme(inputmethodinfo))
                    {
                        j = k;
                        i = k;
                    }
                }
            }
        } while(true);
        return (InputMethodInfo)list.get(Math.max(j, 0));
    }

    public static ArrayList getOverridingImplicitlyEnabledSubtypes(InputMethodInfo inputmethodinfo, String s)
    {
        ArrayList arraylist = new ArrayList();
        int i = inputmethodinfo.getSubtypeCount();
        for(int j = 0; j < i; j++)
        {
            InputMethodSubtype inputmethodsubtype = inputmethodinfo.getSubtypeAt(j);
            if(inputmethodsubtype.overridesImplicitlyEnabledSubtype() && inputmethodsubtype.getMode().equals(s))
                arraylist.add(inputmethodsubtype);
        }

        return arraylist;
    }

    public static int getSubtypeIdFromHashCode(InputMethodInfo inputmethodinfo, int i)
    {
        if(inputmethodinfo != null)
        {
            int j = inputmethodinfo.getSubtypeCount();
            for(int k = 0; k < j; k++)
                if(i == inputmethodinfo.getSubtypeAt(k).hashCode())
                    return k;

        }
        return -1;
    }

    public static ArrayList getSubtypes(InputMethodInfo inputmethodinfo)
    {
        ArrayList arraylist = new ArrayList();
        int i = inputmethodinfo.getSubtypeCount();
        for(int j = 0; j < i; j++)
            arraylist.add(inputmethodinfo.getSubtypeAt(j));

        return arraylist;
    }

    public static ArrayList getSuitableLocalesForSpellChecker(Locale locale)
    {
        Object obj;
        Object obj1;
        ArrayList arraylist;
        if(locale != null)
        {
            obj = locale.getLanguage();
            boolean flag = TextUtils.isEmpty(((CharSequence) (obj))) ^ true;
            obj1 = locale.getCountry();
            boolean flag1 = TextUtils.isEmpty(((CharSequence) (obj1))) ^ true;
            locale = locale.getVariant();
            boolean flag2 = TextUtils.isEmpty(locale);
            if(flag && flag1 && flag2 ^ true)
                locale = new Locale(((String) (obj)), ((String) (obj1)), locale);
            else
                locale = null;
            if(flag && flag1)
                obj1 = new Locale(((String) (obj)), ((String) (obj1)));
            else
                obj1 = null;
            if(flag)
                obj = new Locale(((String) (obj)));
            else
                obj = null;
        } else
        {
            locale = null;
            obj1 = null;
            obj = null;
        }
        arraylist = new ArrayList();
        if(locale != null)
            arraylist.add(locale);
        if(Locale.ENGLISH.equals(obj))
        {
            if(obj1 != null)
            {
                if(obj1 != null)
                    arraylist.add(obj1);
                if(!LOCALE_EN_US.equals(obj1))
                    arraylist.add(LOCALE_EN_US);
                if(!LOCALE_EN_GB.equals(obj1))
                    arraylist.add(LOCALE_EN_GB);
                arraylist.add(Locale.ENGLISH);
            } else
            {
                arraylist.add(Locale.ENGLISH);
                arraylist.add(LOCALE_EN_US);
                arraylist.add(LOCALE_EN_GB);
            }
        } else
        {
            if(obj1 != null)
                arraylist.add(obj1);
            if(obj != null)
                arraylist.add(obj);
            arraylist.add(LOCALE_EN_US);
            arraylist.add(LOCALE_EN_GB);
            arraylist.add(Locale.ENGLISH);
        }
        return arraylist;
    }

    public static Locale getSystemLocaleFromContext(Context context)
    {
        try
        {
            context = context.getResources().getConfiguration().locale;
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            return null;
        }
        return context;
    }

    private static boolean isSystemAuxilialyImeThatHasAutomaticSubtype(InputMethodInfo inputmethodinfo, Context context, boolean flag)
    {
        if(!isSystemIme(inputmethodinfo))
            return false;
        if(flag && inputmethodinfo.isDefault(context) ^ true)
            return false;
        if(!inputmethodinfo.isAuxiliaryIme())
            return false;
        int i = inputmethodinfo.getSubtypeCount();
        for(int j = 0; j < i; j++)
            if(inputmethodinfo.getSubtypeAt(j).overridesImplicitlyEnabledSubtype())
                return true;

        return false;
    }

    public static boolean isSystemIme(InputMethodInfo inputmethodinfo)
    {
        boolean flag = false;
        if((inputmethodinfo.getServiceInfo().applicationInfo.flags & 1) != 0)
            flag = true;
        return flag;
    }

    public static boolean isSystemImeThatHasSubtypeOf(InputMethodInfo inputmethodinfo, Context context, boolean flag, Locale locale, boolean flag1, String s)
    {
        if(!isSystemIme(inputmethodinfo))
            return false;
        if(flag && inputmethodinfo.isDefault(context) ^ true)
            return false;
        return containsSubtypeOf(inputmethodinfo, locale, flag1, s);
    }

    public static boolean isValidSubtypeId(InputMethodInfo inputmethodinfo, int i)
    {
        boolean flag;
        if(getSubtypeIdFromHashCode(inputmethodinfo, i) != -1)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static ArrayMap parseInputMethodsAndSubtypesString(String s)
    {
        ArrayMap arraymap = new ArrayMap();
        if(TextUtils.isEmpty(s))
            return arraymap;
        Pair pair;
        for(Iterator iterator = InputMethodSettings.buildInputMethodsAndSubtypeList(s, new android.text.TextUtils.SimpleStringSplitter(':'), new android.text.TextUtils.SimpleStringSplitter(';')).iterator(); iterator.hasNext(); arraymap.put((String)pair.first, s))
        {
            pair = (Pair)iterator.next();
            s = new ArraySet();
            if(pair.second != null)
                s.addAll((Collection)pair.second);
        }

        return arraymap;
    }

    private static void setDisabledUntilUsed(IPackageManager ipackagemanager, String s, int i, String s1)
    {
        int j;
        try
        {
            j = ipackagemanager.getApplicationEnabledSetting(s, i);
        }
        // Misplaced declaration of an exception variable
        catch(IPackageManager ipackagemanager)
        {
            Slog.w("InputMethodUtils", (new StringBuilder()).append("getApplicationEnabledSetting failed. packageName=").append(s).append(" userId=").append(i).toString(), ipackagemanager);
            return;
        }
        if(j != 0 && j != 1)
            break MISSING_BLOCK_LABEL_32;
        ipackagemanager.setApplicationEnabledSetting(s, 4, 0, i, s1);
        return;
        ipackagemanager;
        Slog.w("InputMethodUtils", (new StringBuilder()).append("setApplicationEnabledSetting failed. packageName=").append(s).append(" userId=").append(i).append(" callingPackage=").append(s1).toString(), ipackagemanager);
        return;
    }

    public static void setNonSelectedSystemImesDisabledUntilUsed(IPackageManager ipackagemanager, List list, int i, String s)
    {
        String as[];
        SpellCheckerInfo spellcheckerinfo;
        int j;
        int k;
        as = Resources.getSystem().getStringArray(0x1070022);
        if(as == null || as.length == 0)
            return;
        spellcheckerinfo = TextServicesManager.getInstance().getCurrentSpellChecker();
        j = 0;
        k = as.length;
_L9:
        String s1;
        boolean flag;
        int l;
        if(j >= k)
            break MISSING_BLOCK_LABEL_232;
        s1 = as[j];
        flag = false;
        l = 0;
_L5:
        boolean flag1 = flag;
        if(l >= list.size()) goto _L2; else goto _L1
_L1:
        if(!s1.equals(((InputMethodInfo)list.get(l)).getPackageName())) goto _L4; else goto _L3
_L3:
        flag1 = true;
          goto _L2
_L7:
        j++;
        continue; /* Loop/switch isn't completed */
_L4:
        l++;
          goto _L5
_L2:
        if(flag1 || spellcheckerinfo != null && s1.equals(spellcheckerinfo.getPackageName())) goto _L7; else goto _L6
_L6:
        ApplicationInfo applicationinfo = ipackagemanager.getApplicationInfo(s1, 32768, i);
        if(applicationinfo != null)
        {
            boolean flag2;
            RemoteException remoteexception;
            if((applicationinfo.flags & 1) != 0)
                flag2 = true;
            else
                flag2 = false;
            if(flag2)
                setDisabledUntilUsed(ipackagemanager, s1, i, s);
        }
          goto _L7
        remoteexception;
        Slog.w("InputMethodUtils", (new StringBuilder()).append("getApplicationInfo failed. packageName=").append(s1).append(" userId=").append(i).toString(), remoteexception);
          goto _L7
        return;
        if(true) goto _L9; else goto _L8
_L8:
    }

    public static final boolean DEBUG = false;
    private static final Locale ENGLISH_LOCALE = new Locale("en");
    private static final char INPUT_METHOD_SEPARATOR = 58;
    private static final char INPUT_METHOD_SUBTYPE_SEPARATOR = 59;
    private static final Locale LOCALE_EN_GB = new Locale("en", "GB");
    private static final Locale LOCALE_EN_US = new Locale("en", "US");
    public static final int NOT_A_SUBTYPE_ID = -1;
    private static final String NOT_A_SUBTYPE_ID_STR = String.valueOf(-1);
    private static final Locale SEARCH_ORDER_OF_FALLBACK_LOCALES[];
    public static final String SUBTYPE_MODE_ANY;
    public static final String SUBTYPE_MODE_KEYBOARD = "keyboard";
    public static final String SUBTYPE_MODE_VOICE = "voice";
    private static final String TAG = "InputMethodUtils";
    private static final String TAG_ASCII_CAPABLE = "AsciiCapable";
    private static final String TAG_ENABLED_WHEN_DEFAULT_IS_NOT_ASCII_CAPABLE = "EnabledWhenDefaultIsNotAsciiCapable";
    private static final Object sCacheLock = new Object();
    private static InputMethodInfo sCachedInputMethodInfo;
    private static ArrayList sCachedResult;
    private static LocaleList sCachedSystemLocales;
    private static final LocaleUtils.LocaleExtractor sSubtypeToLocale = new LocaleUtils.LocaleExtractor() {

        public Locale get(InputMethodSubtype inputmethodsubtype)
        {
            Locale locale = null;
            if(inputmethodsubtype != null)
                locale = inputmethodsubtype.getLocaleObject();
            return locale;
        }

        public volatile Locale get(Object obj)
        {
            return get((InputMethodSubtype)obj);
        }

    }
;

    static 
    {
        SEARCH_ORDER_OF_FALLBACK_LOCALES = (new Locale[] {
            Locale.ENGLISH, Locale.US, Locale.UK
        });
    }
}
