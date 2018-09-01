// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.inputmethod;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.icu.text.DisplayContext;
import android.icu.text.LocaleDisplayNames;
import android.os.*;
import android.text.TextUtils;
import android.util.Slog;
import com.android.internal.inputmethod.InputMethodUtils;
import java.util.*;

// Referenced classes of package android.view.inputmethod:
//            InputMethodInfo

public final class InputMethodSubtype
    implements Parcelable
{
    public static class InputMethodSubtypeBuilder
    {

        static boolean _2D_get0(InputMethodSubtypeBuilder inputmethodsubtypebuilder)
        {
            return inputmethodsubtypebuilder.mIsAsciiCapable;
        }

        static boolean _2D_get1(InputMethodSubtypeBuilder inputmethodsubtypebuilder)
        {
            return inputmethodsubtypebuilder.mIsAuxiliary;
        }

        static boolean _2D_get2(InputMethodSubtypeBuilder inputmethodsubtypebuilder)
        {
            return inputmethodsubtypebuilder.mOverridesImplicitlyEnabledSubtype;
        }

        static String _2D_get3(InputMethodSubtypeBuilder inputmethodsubtypebuilder)
        {
            return inputmethodsubtypebuilder.mSubtypeExtraValue;
        }

        static int _2D_get4(InputMethodSubtypeBuilder inputmethodsubtypebuilder)
        {
            return inputmethodsubtypebuilder.mSubtypeIconResId;
        }

        static int _2D_get5(InputMethodSubtypeBuilder inputmethodsubtypebuilder)
        {
            return inputmethodsubtypebuilder.mSubtypeId;
        }

        static String _2D_get6(InputMethodSubtypeBuilder inputmethodsubtypebuilder)
        {
            return inputmethodsubtypebuilder.mSubtypeLanguageTag;
        }

        static String _2D_get7(InputMethodSubtypeBuilder inputmethodsubtypebuilder)
        {
            return inputmethodsubtypebuilder.mSubtypeLocale;
        }

        static String _2D_get8(InputMethodSubtypeBuilder inputmethodsubtypebuilder)
        {
            return inputmethodsubtypebuilder.mSubtypeMode;
        }

        static int _2D_get9(InputMethodSubtypeBuilder inputmethodsubtypebuilder)
        {
            return inputmethodsubtypebuilder.mSubtypeNameResId;
        }

        static boolean _2D_set0(InputMethodSubtypeBuilder inputmethodsubtypebuilder, boolean flag)
        {
            inputmethodsubtypebuilder.mIsAsciiCapable = flag;
            return flag;
        }

        static boolean _2D_set1(InputMethodSubtypeBuilder inputmethodsubtypebuilder, boolean flag)
        {
            inputmethodsubtypebuilder.mIsAuxiliary = flag;
            return flag;
        }

        static boolean _2D_set2(InputMethodSubtypeBuilder inputmethodsubtypebuilder, boolean flag)
        {
            inputmethodsubtypebuilder.mOverridesImplicitlyEnabledSubtype = flag;
            return flag;
        }

        static String _2D_set3(InputMethodSubtypeBuilder inputmethodsubtypebuilder, String s)
        {
            inputmethodsubtypebuilder.mSubtypeExtraValue = s;
            return s;
        }

        static int _2D_set4(InputMethodSubtypeBuilder inputmethodsubtypebuilder, int i)
        {
            inputmethodsubtypebuilder.mSubtypeIconResId = i;
            return i;
        }

        static int _2D_set5(InputMethodSubtypeBuilder inputmethodsubtypebuilder, int i)
        {
            inputmethodsubtypebuilder.mSubtypeId = i;
            return i;
        }

        static String _2D_set6(InputMethodSubtypeBuilder inputmethodsubtypebuilder, String s)
        {
            inputmethodsubtypebuilder.mSubtypeLocale = s;
            return s;
        }

        static String _2D_set7(InputMethodSubtypeBuilder inputmethodsubtypebuilder, String s)
        {
            inputmethodsubtypebuilder.mSubtypeMode = s;
            return s;
        }

        static int _2D_set8(InputMethodSubtypeBuilder inputmethodsubtypebuilder, int i)
        {
            inputmethodsubtypebuilder.mSubtypeNameResId = i;
            return i;
        }

        public InputMethodSubtype build()
        {
            return new InputMethodSubtype(this, null);
        }

        public InputMethodSubtypeBuilder setIsAsciiCapable(boolean flag)
        {
            mIsAsciiCapable = flag;
            return this;
        }

        public InputMethodSubtypeBuilder setIsAuxiliary(boolean flag)
        {
            mIsAuxiliary = flag;
            return this;
        }

        public InputMethodSubtypeBuilder setLanguageTag(String s)
        {
            String s1 = s;
            if(s == null)
                s1 = "";
            mSubtypeLanguageTag = s1;
            return this;
        }

        public InputMethodSubtypeBuilder setOverridesImplicitlyEnabledSubtype(boolean flag)
        {
            mOverridesImplicitlyEnabledSubtype = flag;
            return this;
        }

        public InputMethodSubtypeBuilder setSubtypeExtraValue(String s)
        {
            String s1 = s;
            if(s == null)
                s1 = "";
            mSubtypeExtraValue = s1;
            return this;
        }

        public InputMethodSubtypeBuilder setSubtypeIconResId(int i)
        {
            mSubtypeIconResId = i;
            return this;
        }

        public InputMethodSubtypeBuilder setSubtypeId(int i)
        {
            mSubtypeId = i;
            return this;
        }

        public InputMethodSubtypeBuilder setSubtypeLocale(String s)
        {
            String s1 = s;
            if(s == null)
                s1 = "";
            mSubtypeLocale = s1;
            return this;
        }

        public InputMethodSubtypeBuilder setSubtypeMode(String s)
        {
            String s1 = s;
            if(s == null)
                s1 = "";
            mSubtypeMode = s1;
            return this;
        }

        public InputMethodSubtypeBuilder setSubtypeNameResId(int i)
        {
            mSubtypeNameResId = i;
            return this;
        }

        private boolean mIsAsciiCapable;
        private boolean mIsAuxiliary;
        private boolean mOverridesImplicitlyEnabledSubtype;
        private String mSubtypeExtraValue;
        private int mSubtypeIconResId;
        private int mSubtypeId;
        private String mSubtypeLanguageTag;
        private String mSubtypeLocale;
        private String mSubtypeMode;
        private int mSubtypeNameResId;

        public InputMethodSubtypeBuilder()
        {
            mIsAuxiliary = false;
            mOverridesImplicitlyEnabledSubtype = false;
            mIsAsciiCapable = false;
            mSubtypeIconResId = 0;
            mSubtypeNameResId = 0;
            mSubtypeId = 0;
            mSubtypeLocale = "";
            mSubtypeLanguageTag = "";
            mSubtypeMode = "";
            mSubtypeExtraValue = "";
        }
    }


    public InputMethodSubtype(int i, int j, String s, String s1, String s2, boolean flag, boolean flag1)
    {
        this(i, j, s, s1, s2, flag, flag1, 0);
    }

    public InputMethodSubtype(int i, int j, String s, String s1, String s2, boolean flag, boolean flag1, 
            int k)
    {
        this(getBuilder(i, j, s, s1, s2, flag, flag1, k, false));
    }

    InputMethodSubtype(Parcel parcel)
    {
        boolean flag = true;
        super();
        mLock = new Object();
        mSubtypeNameResId = parcel.readInt();
        mSubtypeIconResId = parcel.readInt();
        String s = parcel.readString();
        boolean flag1;
        if(s == null)
            s = "";
        mSubtypeLocale = s;
        s = parcel.readString();
        if(s == null)
            s = "";
        mSubtypeLanguageTag = s;
        s = parcel.readString();
        if(s == null)
            s = "";
        mSubtypeMode = s;
        s = parcel.readString();
        if(s == null)
            s = "";
        mSubtypeExtraValue = s;
        if(parcel.readInt() == 1)
            flag1 = true;
        else
            flag1 = false;
        mIsAuxiliary = flag1;
        if(parcel.readInt() == 1)
            flag1 = true;
        else
            flag1 = false;
        mOverridesImplicitlyEnabledSubtype = flag1;
        mSubtypeHashCode = parcel.readInt();
        mSubtypeId = parcel.readInt();
        if(parcel.readInt() == 1)
            flag1 = flag;
        else
            flag1 = false;
        mIsAsciiCapable = flag1;
    }

    private InputMethodSubtype(InputMethodSubtypeBuilder inputmethodsubtypebuilder)
    {
        mLock = new Object();
        mSubtypeNameResId = InputMethodSubtypeBuilder._2D_get9(inputmethodsubtypebuilder);
        mSubtypeIconResId = InputMethodSubtypeBuilder._2D_get4(inputmethodsubtypebuilder);
        mSubtypeLocale = InputMethodSubtypeBuilder._2D_get7(inputmethodsubtypebuilder);
        mSubtypeLanguageTag = InputMethodSubtypeBuilder._2D_get6(inputmethodsubtypebuilder);
        mSubtypeMode = InputMethodSubtypeBuilder._2D_get8(inputmethodsubtypebuilder);
        mSubtypeExtraValue = InputMethodSubtypeBuilder._2D_get3(inputmethodsubtypebuilder);
        mIsAuxiliary = InputMethodSubtypeBuilder._2D_get1(inputmethodsubtypebuilder);
        mOverridesImplicitlyEnabledSubtype = InputMethodSubtypeBuilder._2D_get2(inputmethodsubtypebuilder);
        mSubtypeId = InputMethodSubtypeBuilder._2D_get5(inputmethodsubtypebuilder);
        mIsAsciiCapable = InputMethodSubtypeBuilder._2D_get0(inputmethodsubtypebuilder);
        if(mSubtypeId != 0)
            mSubtypeHashCode = mSubtypeId;
        else
            mSubtypeHashCode = hashCodeInternal(mSubtypeLocale, mSubtypeMode, mSubtypeExtraValue, mIsAuxiliary, mOverridesImplicitlyEnabledSubtype, mIsAsciiCapable);
    }

    InputMethodSubtype(InputMethodSubtypeBuilder inputmethodsubtypebuilder, InputMethodSubtype inputmethodsubtype)
    {
        this(inputmethodsubtypebuilder);
    }

    private static InputMethodSubtypeBuilder getBuilder(int i, int j, String s, String s1, String s2, boolean flag, boolean flag1, int k, 
            boolean flag2)
    {
        InputMethodSubtypeBuilder inputmethodsubtypebuilder = new InputMethodSubtypeBuilder();
        InputMethodSubtypeBuilder._2D_set8(inputmethodsubtypebuilder, i);
        InputMethodSubtypeBuilder._2D_set4(inputmethodsubtypebuilder, j);
        InputMethodSubtypeBuilder._2D_set6(inputmethodsubtypebuilder, s);
        InputMethodSubtypeBuilder._2D_set7(inputmethodsubtypebuilder, s1);
        InputMethodSubtypeBuilder._2D_set3(inputmethodsubtypebuilder, s2);
        InputMethodSubtypeBuilder._2D_set1(inputmethodsubtypebuilder, flag);
        InputMethodSubtypeBuilder._2D_set2(inputmethodsubtypebuilder, flag1);
        InputMethodSubtypeBuilder._2D_set5(inputmethodsubtypebuilder, k);
        InputMethodSubtypeBuilder._2D_set0(inputmethodsubtypebuilder, flag2);
        return inputmethodsubtypebuilder;
    }

    private HashMap getExtraValueHashMap()
    {
        this;
        JVM INSTR monitorenter ;
        HashMap hashmap = mExtraValueHashMapCache;
        if(hashmap == null)
            break MISSING_BLOCK_LABEL_15;
        this;
        JVM INSTR monitorexit ;
        return hashmap;
        String as[];
        HashMap hashmap1;
        hashmap1 = JVM INSTR new #189 <Class HashMap>;
        hashmap1.HashMap();
        as = mSubtypeExtraValue.split(",");
        int i = 0;
_L2:
        String as1[];
        if(i >= as.length)
            break MISSING_BLOCK_LABEL_118;
        as1 = as[i].split("=");
        if(as1.length != 1)
            break; /* Loop/switch isn't completed */
        hashmap1.put(as1[0], null);
_L4:
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        if(as1.length <= 1) goto _L4; else goto _L3
_L3:
        if(as1.length > 2)
            Slog.w(TAG, "ExtraValue has two or more '='s");
        hashmap1.put(as1[0], as1[1]);
          goto _L4
        Exception exception;
        exception;
        throw exception;
        mExtraValueHashMapCache = hashmap1;
        this;
        JVM INSTR monitorexit ;
        return hashmap1;
    }

    private static String getLocaleDisplayName(Locale locale, Locale locale1, DisplayContext displaycontext)
    {
        if(locale1 == null)
            return "";
        if(locale == null)
            locale = Locale.getDefault();
        return LocaleDisplayNames.getInstance(locale, new DisplayContext[] {
            displaycontext
        }).localeDisplayName(locale1);
    }

    private static Locale getLocaleFromContext(Context context)
    {
        if(context == null)
            return null;
        if(context.getResources() == null)
            return null;
        context = context.getResources().getConfiguration();
        if(context == null)
            return null;
        else
            return context.getLocales().get(0);
    }

    private static int hashCodeInternal(String s, String s1, String s2, boolean flag, boolean flag1, boolean flag2)
    {
        if(flag2 ^ true)
            return Arrays.hashCode(new Object[] {
                s, s1, s2, Boolean.valueOf(flag), Boolean.valueOf(flag1)
            });
        else
            return Arrays.hashCode(new Object[] {
                s, s1, s2, Boolean.valueOf(flag), Boolean.valueOf(flag1), Boolean.valueOf(flag2)
            });
    }

    public static List sort(Context context, int i, InputMethodInfo inputmethodinfo, List list)
    {
        if(inputmethodinfo == null)
            return list;
        HashSet hashset = new HashSet(list);
        context = new ArrayList();
        int j = inputmethodinfo.getSubtypeCount();
        for(i = 0; i < j; i++)
        {
            list = inputmethodinfo.getSubtypeAt(i);
            if(hashset.contains(list))
            {
                context.add(list);
                hashset.remove(list);
            }
        }

        for(inputmethodinfo = hashset.iterator(); inputmethodinfo.hasNext(); context.add((InputMethodSubtype)inputmethodinfo.next()));
        return context;
    }

    public boolean containsExtraValueKey(String s)
    {
        return getExtraValueHashMap().containsKey(s);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        boolean flag1 = true;
        if(obj instanceof InputMethodSubtype)
        {
            obj = (InputMethodSubtype)obj;
            if(((InputMethodSubtype) (obj)).mSubtypeId != 0 || mSubtypeId != 0)
            {
                if(((InputMethodSubtype) (obj)).hashCode() != hashCode())
                    flag1 = false;
                return flag1;
            }
            if(((InputMethodSubtype) (obj)).hashCode() == hashCode() && ((InputMethodSubtype) (obj)).getLocale().equals(getLocale()) && ((InputMethodSubtype) (obj)).getLanguageTag().equals(getLanguageTag()) && ((InputMethodSubtype) (obj)).getMode().equals(getMode()) && ((InputMethodSubtype) (obj)).getExtraValue().equals(getExtraValue()) && ((InputMethodSubtype) (obj)).isAuxiliary() == isAuxiliary() && ((InputMethodSubtype) (obj)).overridesImplicitlyEnabledSubtype() == overridesImplicitlyEnabledSubtype())
            {
                if(((InputMethodSubtype) (obj)).isAsciiCapable() == isAsciiCapable())
                    flag1 = flag;
                else
                    flag1 = false;
            } else
            {
                flag1 = false;
            }
            return flag1;
        } else
        {
            return false;
        }
    }

    public CharSequence getDisplayName(Context context, String s, ApplicationInfo applicationinfo)
    {
        String s1;
        if(mSubtypeNameResId == 0)
            return getLocaleDisplayName(getLocaleFromContext(context), getLocaleObject(), DisplayContext.CAPITALIZATION_FOR_UI_LIST_OR_MENU);
        applicationinfo = context.getPackageManager().getText(s, mSubtypeNameResId, applicationinfo);
        if(TextUtils.isEmpty(applicationinfo))
            return "";
        s1 = applicationinfo.toString();
        if(!containsExtraValueKey("UntranslatableReplacementStringInSubtypeName")) goto _L2; else goto _L1
_L1:
        context = getExtraValueOf("UntranslatableReplacementStringInSubtypeName");
_L4:
        s = context;
        if(context == null)
            s = "";
        try
        {
            context = String.format(s1, new Object[] {
                s
            });
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            Slog.w(TAG, (new StringBuilder()).append("Found illegal format in subtype name(").append(applicationinfo).append("): ").append(context).toString());
            return "";
        }
        return context;
_L2:
        if(!TextUtils.equals(s1, "%s"))
            break; /* Loop/switch isn't completed */
        s = DisplayContext.CAPITALIZATION_FOR_UI_LIST_OR_MENU;
_L5:
        context = getLocaleDisplayName(getLocaleFromContext(context), getLocaleObject(), s);
        if(true) goto _L4; else goto _L3
_L3:
        if(s1.startsWith("%s"))
            s = DisplayContext.CAPITALIZATION_FOR_BEGINNING_OF_SENTENCE;
        else
            s = DisplayContext.CAPITALIZATION_FOR_MIDDLE_OF_SENTENCE;
          goto _L5
    }

    public String getExtraValue()
    {
        return mSubtypeExtraValue;
    }

    public String getExtraValueOf(String s)
    {
        return (String)getExtraValueHashMap().get(s);
    }

    public int getIconResId()
    {
        return mSubtypeIconResId;
    }

    public String getLanguageTag()
    {
        return mSubtypeLanguageTag;
    }

    public String getLocale()
    {
        return mSubtypeLocale;
    }

    public Locale getLocaleObject()
    {
        if(mCachedLocaleObj != null)
            return mCachedLocaleObj;
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        Locale locale;
        if(mCachedLocaleObj == null)
            break MISSING_BLOCK_LABEL_35;
        locale = mCachedLocaleObj;
        obj;
        JVM INSTR monitorexit ;
        return locale;
        if(TextUtils.isEmpty(mSubtypeLanguageTag))
            break MISSING_BLOCK_LABEL_65;
        mCachedLocaleObj = Locale.forLanguageTag(mSubtypeLanguageTag);
_L1:
        locale = mCachedLocaleObj;
        obj;
        JVM INSTR monitorexit ;
        return locale;
        mCachedLocaleObj = InputMethodUtils.constructLocaleFromString(mSubtypeLocale);
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    public String getMode()
    {
        return mSubtypeMode;
    }

    public int getNameResId()
    {
        return mSubtypeNameResId;
    }

    public final int getSubtypeId()
    {
        return mSubtypeId;
    }

    public final boolean hasSubtypeId()
    {
        boolean flag = false;
        if(mSubtypeId != 0)
            flag = true;
        return flag;
    }

    public int hashCode()
    {
        return mSubtypeHashCode;
    }

    public boolean isAsciiCapable()
    {
        return mIsAsciiCapable;
    }

    public boolean isAuxiliary()
    {
        return mIsAuxiliary;
    }

    public boolean overridesImplicitlyEnabledSubtype()
    {
        return mOverridesImplicitlyEnabledSubtype;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeInt(mSubtypeNameResId);
        parcel.writeInt(mSubtypeIconResId);
        parcel.writeString(mSubtypeLocale);
        parcel.writeString(mSubtypeLanguageTag);
        parcel.writeString(mSubtypeMode);
        parcel.writeString(mSubtypeExtraValue);
        if(mIsAuxiliary)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mOverridesImplicitlyEnabledSubtype)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeInt(mSubtypeHashCode);
        parcel.writeInt(mSubtypeId);
        if(mIsAsciiCapable)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeInt(i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public InputMethodSubtype createFromParcel(Parcel parcel)
        {
            return new InputMethodSubtype(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public InputMethodSubtype[] newArray(int i)
        {
            return new InputMethodSubtype[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String EXTRA_KEY_UNTRANSLATABLE_STRING_IN_SUBTYPE_NAME = "UntranslatableReplacementStringInSubtypeName";
    private static final String EXTRA_VALUE_KEY_VALUE_SEPARATOR = "=";
    private static final String EXTRA_VALUE_PAIR_SEPARATOR = ",";
    private static final String LANGUAGE_TAG_NONE = "";
    private static final int SUBTYPE_ID_NONE = 0;
    private static final String TAG = android/view/inputmethod/InputMethodSubtype.getSimpleName();
    private volatile Locale mCachedLocaleObj;
    private volatile HashMap mExtraValueHashMapCache;
    private final boolean mIsAsciiCapable;
    private final boolean mIsAuxiliary;
    private final Object mLock;
    private final boolean mOverridesImplicitlyEnabledSubtype;
    private final String mSubtypeExtraValue;
    private final int mSubtypeHashCode;
    private final int mSubtypeIconResId;
    private final int mSubtypeId;
    private final String mSubtypeLanguageTag;
    private final String mSubtypeLocale;
    private final String mSubtypeMode;
    private final int mSubtypeNameResId;

}
