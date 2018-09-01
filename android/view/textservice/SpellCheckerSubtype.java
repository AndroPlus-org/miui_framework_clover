// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.textservice;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Slog;
import com.android.internal.inputmethod.InputMethodUtils;
import java.util.*;

// Referenced classes of package android.view.textservice:
//            SpellCheckerInfo

public final class SpellCheckerSubtype
    implements Parcelable
{

    public SpellCheckerSubtype(int i, String s, String s1)
    {
        this(i, s, "", s1, 0);
    }

    public SpellCheckerSubtype(int i, String s, String s1, String s2, int j)
    {
        mSubtypeNameResId = i;
        if(s == null)
            s = "";
        mSubtypeLocale = s;
        if(s1 == null)
            s1 = "";
        mSubtypeLanguageTag = s1;
        if(s2 == null)
            s2 = "";
        mSubtypeExtraValue = s2;
        mSubtypeId = j;
        if(mSubtypeId != 0)
            i = mSubtypeId;
        else
            i = hashCodeInternal(mSubtypeLocale, mSubtypeExtraValue);
        mSubtypeHashCode = i;
    }

    SpellCheckerSubtype(Parcel parcel)
    {
        mSubtypeNameResId = parcel.readInt();
        String s = parcel.readString();
        int i;
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
        mSubtypeExtraValue = s;
        mSubtypeId = parcel.readInt();
        if(mSubtypeId != 0)
            i = mSubtypeId;
        else
            i = hashCodeInternal(mSubtypeLocale, mSubtypeExtraValue);
        mSubtypeHashCode = i;
    }

    private HashMap getExtraValueHashMap()
    {
        if(mExtraValueHashMapCache == null)
        {
            mExtraValueHashMapCache = new HashMap();
            String as[] = mSubtypeExtraValue.split(",");
            int i = as.length;
            int j = 0;
            while(j < i) 
            {
                String as1[] = as[j].split("=");
                if(as1.length == 1)
                    mExtraValueHashMapCache.put(as1[0], null);
                else
                if(as1.length > 1)
                {
                    if(as1.length > 2)
                        Slog.w(TAG, "ExtraValue has two or more '='s");
                    mExtraValueHashMapCache.put(as1[0], as1[1]);
                }
                j++;
            }
        }
        return mExtraValueHashMapCache;
    }

    private static int hashCodeInternal(String s, String s1)
    {
        return Arrays.hashCode(new Object[] {
            s, s1
        });
    }

    public static List sort(Context context, int i, SpellCheckerInfo spellcheckerinfo, List list)
    {
        if(spellcheckerinfo == null)
            return list;
        list = new HashSet(list);
        context = new ArrayList();
        int j = spellcheckerinfo.getSubtypeCount();
        for(i = 0; i < j; i++)
        {
            SpellCheckerSubtype spellcheckersubtype = spellcheckerinfo.getSubtypeAt(i);
            if(list.contains(spellcheckersubtype))
            {
                context.add(spellcheckersubtype);
                list.remove(spellcheckersubtype);
            }
        }

        for(spellcheckerinfo = list.iterator(); spellcheckerinfo.hasNext(); context.add((SpellCheckerSubtype)spellcheckerinfo.next()));
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
        boolean flag = false;
        boolean flag1 = false;
        if(obj instanceof SpellCheckerSubtype)
        {
            obj = (SpellCheckerSubtype)obj;
            if(((SpellCheckerSubtype) (obj)).mSubtypeId != 0 || mSubtypeId != 0)
            {
                if(((SpellCheckerSubtype) (obj)).hashCode() == hashCode())
                    flag1 = true;
                return flag1;
            }
            flag1 = flag;
            if(((SpellCheckerSubtype) (obj)).hashCode() == hashCode())
            {
                flag1 = flag;
                if(((SpellCheckerSubtype) (obj)).getNameResId() == getNameResId())
                {
                    flag1 = flag;
                    if(((SpellCheckerSubtype) (obj)).getLocale().equals(getLocale()))
                    {
                        flag1 = flag;
                        if(((SpellCheckerSubtype) (obj)).getLanguageTag().equals(getLanguageTag()))
                            flag1 = ((SpellCheckerSubtype) (obj)).getExtraValue().equals(getExtraValue());
                    }
                }
            }
            return flag1;
        } else
        {
            return false;
        }
    }

    public CharSequence getDisplayName(Context context, String s, ApplicationInfo applicationinfo)
    {
        Object obj = getLocaleObject();
        if(obj != null)
            obj = ((Locale) (obj)).getDisplayName();
        else
            obj = mSubtypeLocale;
        if(mSubtypeNameResId == 0)
            return ((CharSequence) (obj));
        context = context.getPackageManager().getText(s, mSubtypeNameResId, applicationinfo);
        if(!TextUtils.isEmpty(context))
            return String.format(context.toString(), new Object[] {
                obj
            });
        else
            return ((CharSequence) (obj));
    }

    public String getExtraValue()
    {
        return mSubtypeExtraValue;
    }

    public String getExtraValueOf(String s)
    {
        return (String)getExtraValueHashMap().get(s);
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
        if(!TextUtils.isEmpty(mSubtypeLanguageTag))
            return Locale.forLanguageTag(mSubtypeLanguageTag);
        else
            return InputMethodUtils.constructLocaleFromString(mSubtypeLocale);
    }

    public int getNameResId()
    {
        return mSubtypeNameResId;
    }

    public int hashCode()
    {
        return mSubtypeHashCode;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mSubtypeNameResId);
        parcel.writeString(mSubtypeLocale);
        parcel.writeString(mSubtypeLanguageTag);
        parcel.writeString(mSubtypeExtraValue);
        parcel.writeInt(mSubtypeId);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public SpellCheckerSubtype createFromParcel(Parcel parcel)
        {
            return new SpellCheckerSubtype(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public SpellCheckerSubtype[] newArray(int i)
        {
            return new SpellCheckerSubtype[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String EXTRA_VALUE_KEY_VALUE_SEPARATOR = "=";
    private static final String EXTRA_VALUE_PAIR_SEPARATOR = ",";
    public static final int SUBTYPE_ID_NONE = 0;
    private static final String SUBTYPE_LANGUAGE_TAG_NONE = "";
    private static final String TAG = android/view/textservice/SpellCheckerSubtype.getSimpleName();
    private HashMap mExtraValueHashMapCache;
    private final String mSubtypeExtraValue;
    private final int mSubtypeHashCode;
    private final int mSubtypeId;
    private final String mSubtypeLanguageTag;
    private final String mSubtypeLocale;
    private final int mSubtypeNameResId;

}
