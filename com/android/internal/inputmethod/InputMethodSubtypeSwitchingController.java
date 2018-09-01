// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.inputmethod;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Printer;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodSubtype;
import java.util.*;

// Referenced classes of package com.android.internal.inputmethod:
//            InputMethodUtils

public class InputMethodSubtypeSwitchingController
{
    public static class ControllerImpl
    {

        public static ControllerImpl createFrom(ControllerImpl controllerimpl, List list)
        {
            DynamicRotationList dynamicrotationlist = null;
            List list1 = filterImeSubtypeList(list, true);
            DynamicRotationList dynamicrotationlist1 = dynamicrotationlist;
            if(controllerimpl != null)
            {
                dynamicrotationlist1 = dynamicrotationlist;
                if(controllerimpl.mSwitchingAwareRotationList != null)
                {
                    dynamicrotationlist1 = dynamicrotationlist;
                    if(Objects.equals(DynamicRotationList._2D_get0(controllerimpl.mSwitchingAwareRotationList), list1))
                        dynamicrotationlist1 = controllerimpl.mSwitchingAwareRotationList;
                }
            }
            dynamicrotationlist = dynamicrotationlist1;
            if(dynamicrotationlist1 == null)
                dynamicrotationlist = new DynamicRotationList(list1, null);
            dynamicrotationlist1 = null;
            list1 = filterImeSubtypeList(list, false);
            list = dynamicrotationlist1;
            if(controllerimpl != null)
            {
                list = dynamicrotationlist1;
                if(controllerimpl.mSwitchingUnawareRotationList != null)
                {
                    list = dynamicrotationlist1;
                    if(Objects.equals(StaticRotationList._2D_get0(controllerimpl.mSwitchingUnawareRotationList), list1))
                        list = controllerimpl.mSwitchingUnawareRotationList;
                }
            }
            controllerimpl = list;
            if(list == null)
                controllerimpl = new StaticRotationList(list1);
            return new ControllerImpl(dynamicrotationlist, controllerimpl);
        }

        private static List filterImeSubtypeList(List list, boolean flag)
        {
            ArrayList arraylist = new ArrayList();
            int i = list.size();
            for(int j = 0; j < i; j++)
            {
                ImeSubtypeListItem imesubtypelistitem = (ImeSubtypeListItem)list.get(j);
                if(imesubtypelistitem.mImi.supportsSwitchingToNextInputMethod() == flag)
                    arraylist.add(imesubtypelistitem);
            }

            return arraylist;
        }

        protected void dump(Printer printer)
        {
            printer.println("    mSwitchingAwareRotationList:");
            mSwitchingAwareRotationList.dump(printer, "      ");
            printer.println("    mSwitchingUnawareRotationList:");
            mSwitchingUnawareRotationList.dump(printer, "      ");
        }

        public ImeSubtypeListItem getNextInputMethod(boolean flag, InputMethodInfo inputmethodinfo, InputMethodSubtype inputmethodsubtype, boolean flag1)
        {
            if(inputmethodinfo == null)
                return null;
            if(inputmethodinfo.supportsSwitchingToNextInputMethod())
                return mSwitchingAwareRotationList.getNextInputMethodLocked(flag, inputmethodinfo, inputmethodsubtype, flag1);
            else
                return mSwitchingUnawareRotationList.getNextInputMethodLocked(flag, inputmethodinfo, inputmethodsubtype, flag1);
        }

        public void onUserActionLocked(InputMethodInfo inputmethodinfo, InputMethodSubtype inputmethodsubtype)
        {
            if(inputmethodinfo == null)
                return;
            if(inputmethodinfo.supportsSwitchingToNextInputMethod())
                mSwitchingAwareRotationList.onUserAction(inputmethodinfo, inputmethodsubtype);
        }

        private final DynamicRotationList mSwitchingAwareRotationList;
        private final StaticRotationList mSwitchingUnawareRotationList;

        private ControllerImpl(DynamicRotationList dynamicrotationlist, StaticRotationList staticrotationlist)
        {
            mSwitchingAwareRotationList = dynamicrotationlist;
            mSwitchingUnawareRotationList = staticrotationlist;
        }
    }

    private static class DynamicRotationList
    {

        static List _2D_get0(DynamicRotationList dynamicrotationlist)
        {
            return dynamicrotationlist.mImeSubtypeList;
        }

        private int getUsageRank(InputMethodInfo inputmethodinfo, InputMethodSubtype inputmethodsubtype)
        {
            int i = InputMethodSubtypeSwitchingController._2D_wrap0(inputmethodinfo, inputmethodsubtype);
            int j = mUsageHistoryOfSubtypeListItemIndex.length;
            for(int k = 0; k < j; k++)
            {
                int l = mUsageHistoryOfSubtypeListItemIndex[k];
                inputmethodsubtype = (ImeSubtypeListItem)mImeSubtypeList.get(l);
                if(((ImeSubtypeListItem) (inputmethodsubtype)).mImi.equals(inputmethodinfo) && ((ImeSubtypeListItem) (inputmethodsubtype)).mSubtypeId == i)
                    return k;
            }

            return -1;
        }

        protected void dump(Printer printer, String s)
        {
            for(int i = 0; i < mUsageHistoryOfSubtypeListItemIndex.length; i++)
            {
                int j = mUsageHistoryOfSubtypeListItemIndex[i];
                ImeSubtypeListItem imesubtypelistitem = (ImeSubtypeListItem)mImeSubtypeList.get(i);
                printer.println((new StringBuilder()).append(s).append("rank=").append(j).append(" item=").append(imesubtypelistitem).toString());
            }

        }

        public ImeSubtypeListItem getNextInputMethodLocked(boolean flag, InputMethodInfo inputmethodinfo, InputMethodSubtype inputmethodsubtype, boolean flag1)
        {
            int i = getUsageRank(inputmethodinfo, inputmethodsubtype);
            if(i < 0)
                return null;
            int j = mUsageHistoryOfSubtypeListItemIndex.length;
            for(int k = 1; k < j;)
            {
                int l;
                if(flag1)
                    l = k;
                else
                    l = j - k;
                l = mUsageHistoryOfSubtypeListItemIndex[(i + l) % j];
                inputmethodsubtype = (ImeSubtypeListItem)mImeSubtypeList.get(l);
                if(flag && inputmethodinfo.equals(((ImeSubtypeListItem) (inputmethodsubtype)).mImi) ^ true)
                    k++;
                else
                    return inputmethodsubtype;
            }

            return null;
        }

        public void onUserAction(InputMethodInfo inputmethodinfo, InputMethodSubtype inputmethodsubtype)
        {
            int i = getUsageRank(inputmethodinfo, inputmethodsubtype);
            if(i <= 0)
            {
                return;
            } else
            {
                int j = mUsageHistoryOfSubtypeListItemIndex[i];
                System.arraycopy(mUsageHistoryOfSubtypeListItemIndex, 0, mUsageHistoryOfSubtypeListItemIndex, 1, i);
                mUsageHistoryOfSubtypeListItemIndex[0] = j;
                return;
            }
        }

        private static final String TAG = com/android/internal/inputmethod/InputMethodSubtypeSwitchingController$DynamicRotationList.getSimpleName();
        private final List mImeSubtypeList;
        private final int mUsageHistoryOfSubtypeListItemIndex[];


        private DynamicRotationList(List list)
        {
            mImeSubtypeList = list;
            mUsageHistoryOfSubtypeListItemIndex = new int[mImeSubtypeList.size()];
            int i = mImeSubtypeList.size();
            for(int j = 0; j < i; j++)
                mUsageHistoryOfSubtypeListItemIndex[j] = j;

        }

        DynamicRotationList(List list, DynamicRotationList dynamicrotationlist)
        {
            this(list);
        }
    }

    public static class ImeSubtypeListItem
        implements Comparable
    {

        private static int compareNullableCharSequences(CharSequence charsequence, CharSequence charsequence1)
        {
            int i = 1;
            boolean flag = TextUtils.isEmpty(charsequence);
            boolean flag1 = TextUtils.isEmpty(charsequence1);
            if(flag || flag1)
            {
                int j;
                if(flag)
                    j = 1;
                else
                    j = 0;
                if(!flag1)
                    i = 0;
                return j - i;
            } else
            {
                return charsequence.toString().compareTo(charsequence1.toString());
            }
        }

        private static String parseLanguageFromLocaleString(String s)
        {
            int i = s.indexOf('_');
            if(i < 0)
                return s;
            else
                return s.substring(0, i);
        }

        public int compareTo(ImeSubtypeListItem imesubtypelistitem)
        {
            byte byte0 = -1;
            int i = compareNullableCharSequences(mImeName, imesubtypelistitem.mImeName);
            if(i != 0)
                return i;
            byte byte1;
            if(mIsSystemLocale)
                i = -1;
            else
                i = 0;
            if(imesubtypelistitem.mIsSystemLocale)
                byte1 = -1;
            else
                byte1 = 0;
            i -= byte1;
            if(i != 0)
                return i;
            if(mIsSystemLanguage)
                i = -1;
            else
                i = 0;
            if(imesubtypelistitem.mIsSystemLanguage)
                byte1 = byte0;
            else
                byte1 = 0;
            i -= byte1;
            if(i != 0)
                return i;
            else
                return compareNullableCharSequences(mSubtypeName, imesubtypelistitem.mSubtypeName);
        }

        public volatile int compareTo(Object obj)
        {
            return compareTo((ImeSubtypeListItem)obj);
        }

        public boolean equals(Object obj)
        {
            boolean flag = true;
            if(obj == this)
                return true;
            if(obj instanceof ImeSubtypeListItem)
            {
                obj = (ImeSubtypeListItem)obj;
                if(!Objects.equals(mImi, ((ImeSubtypeListItem) (obj)).mImi) || mSubtypeId != ((ImeSubtypeListItem) (obj)).mSubtypeId)
                    flag = false;
                return flag;
            } else
            {
                return false;
            }
        }

        public String toString()
        {
            return (new StringBuilder()).append("ImeSubtypeListItem{mImeName=").append(mImeName).append(" mSubtypeName=").append(mSubtypeName).append(" mSubtypeId=").append(mSubtypeId).append(" mIsSystemLocale=").append(mIsSystemLocale).append(" mIsSystemLanguage=").append(mIsSystemLanguage).append("}").toString();
        }

        public final CharSequence mImeName;
        public final InputMethodInfo mImi;
        public final boolean mIsSystemLanguage;
        public final boolean mIsSystemLocale;
        public final int mSubtypeId;
        public final CharSequence mSubtypeName;

        public ImeSubtypeListItem(CharSequence charsequence, CharSequence charsequence1, InputMethodInfo inputmethodinfo, int i, String s, String s1)
        {
            boolean flag = false;
            super();
            mImeName = charsequence;
            mSubtypeName = charsequence1;
            mImi = inputmethodinfo;
            mSubtypeId = i;
            if(TextUtils.isEmpty(s))
            {
                mIsSystemLocale = false;
                mIsSystemLanguage = false;
            } else
            {
                mIsSystemLocale = s.equals(s1);
                if(mIsSystemLocale)
                {
                    mIsSystemLanguage = true;
                } else
                {
                    charsequence = parseLanguageFromLocaleString(s1);
                    charsequence1 = parseLanguageFromLocaleString(s);
                    if(charsequence.length() >= 2)
                        flag = charsequence.equals(charsequence1);
                    mIsSystemLanguage = flag;
                }
            }
        }
    }

    private static class InputMethodAndSubtypeList
    {

        static PackageManager _2D_get0(InputMethodAndSubtypeList inputmethodandsubtypelist)
        {
            return inputmethodandsubtypelist.mPm;
        }

        public List getSortedInputMethodAndSubtypeList(boolean flag, boolean flag1)
        {
            ArrayList arraylist = new ArrayList();
            HashMap hashmap = mSettings.getExplicitlyOrImplicitlyEnabledInputMethodsAndSubtypeListLocked(mContext);
            if(hashmap == null || hashmap.size() == 0)
                return Collections.emptyList();
            boolean flag2 = flag;
            if(flag1)
            {
                flag2 = flag;
                if(flag)
                    flag2 = false;
            }
            mSortedImmis.clear();
            mSortedImmis.putAll(hashmap);
            Iterator iterator = mSortedImmis.keySet().iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                InputMethodInfo inputmethodinfo = (InputMethodInfo)iterator.next();
                if(inputmethodinfo != null)
                {
                    Object obj = (List)hashmap.get(inputmethodinfo);
                    HashSet hashset = new HashSet();
                    for(obj = ((Iterable) (obj)).iterator(); ((Iterator) (obj)).hasNext(); hashset.add(String.valueOf(((InputMethodSubtype)((Iterator) (obj)).next()).hashCode())));
                    CharSequence charsequence1 = inputmethodinfo.loadLabel(mPm);
                    if(hashset.size() > 0)
                    {
                        int i = inputmethodinfo.getSubtypeCount();
                        int j = 0;
                        while(j < i) 
                        {
                            InputMethodSubtype inputmethodsubtype = inputmethodinfo.getSubtypeAt(j);
                            String s = String.valueOf(inputmethodsubtype.hashCode());
                            if(hashset.contains(s) && (flag2 || inputmethodsubtype.isAuxiliary() ^ true))
                            {
                                CharSequence charsequence;
                                if(inputmethodsubtype.overridesImplicitlyEnabledSubtype())
                                    charsequence = null;
                                else
                                    charsequence = inputmethodsubtype.getDisplayName(mContext, inputmethodinfo.getPackageName(), inputmethodinfo.getServiceInfo().applicationInfo);
                                arraylist.add(new ImeSubtypeListItem(charsequence1, charsequence, inputmethodinfo, j, inputmethodsubtype.getLocale(), mSystemLocaleStr));
                                hashset.remove(s);
                            }
                            j++;
                        }
                    } else
                    {
                        arraylist.add(new ImeSubtypeListItem(charsequence1, null, inputmethodinfo, -1, null, mSystemLocaleStr));
                    }
                }
            } while(true);
            Collections.sort(arraylist);
            return arraylist;
        }

        private final Context mContext;
        private final PackageManager mPm;
        private final InputMethodUtils.InputMethodSettings mSettings;
        private final TreeMap mSortedImmis = new TreeMap(new _cls1());
        private final String mSystemLocaleStr;

        public InputMethodAndSubtypeList(Context context, InputMethodUtils.InputMethodSettings inputmethodsettings)
        {
            mContext = context;
            mSettings = inputmethodsettings;
            mPm = context.getPackageManager();
            context = context.getResources().getConfiguration().locale;
            if(context != null)
                context = context.toString();
            else
                context = "";
            mSystemLocaleStr = context;
        }
    }

    private static class StaticRotationList
    {

        static List _2D_get0(StaticRotationList staticrotationlist)
        {
            return staticrotationlist.mImeSubtypeList;
        }

        private int getIndex(InputMethodInfo inputmethodinfo, InputMethodSubtype inputmethodsubtype)
        {
            int i = InputMethodSubtypeSwitchingController._2D_wrap0(inputmethodinfo, inputmethodsubtype);
            int j = mImeSubtypeList.size();
            for(int k = 0; k < j; k++)
            {
                inputmethodsubtype = (ImeSubtypeListItem)mImeSubtypeList.get(k);
                if(inputmethodinfo.equals(((ImeSubtypeListItem) (inputmethodsubtype)).mImi) && ((ImeSubtypeListItem) (inputmethodsubtype)).mSubtypeId == i)
                    return k;
            }

            return -1;
        }

        protected void dump(Printer printer, String s)
        {
            int i = mImeSubtypeList.size();
            for(int j = 0; j < i; j++)
            {
                ImeSubtypeListItem imesubtypelistitem = (ImeSubtypeListItem)mImeSubtypeList.get(j);
                printer.println((new StringBuilder()).append(s).append("rank=").append(j).append(" item=").append(imesubtypelistitem).toString());
            }

        }

        public ImeSubtypeListItem getNextInputMethodLocked(boolean flag, InputMethodInfo inputmethodinfo, InputMethodSubtype inputmethodsubtype, boolean flag1)
        {
            if(inputmethodinfo == null)
                return null;
            if(mImeSubtypeList.size() <= 1)
                return null;
            int i = getIndex(inputmethodinfo, inputmethodsubtype);
            if(i < 0)
                return null;
            int j = mImeSubtypeList.size();
            for(int k = 1; k < j;)
            {
                int l;
                if(flag1)
                    l = k;
                else
                    l = j - k;
                inputmethodsubtype = (ImeSubtypeListItem)mImeSubtypeList.get((i + l) % j);
                if(flag && inputmethodinfo.equals(((ImeSubtypeListItem) (inputmethodsubtype)).mImi) ^ true)
                    k++;
                else
                    return inputmethodsubtype;
            }

            return null;
        }

        private final List mImeSubtypeList;

        public StaticRotationList(List list)
        {
            mImeSubtypeList = list;
        }
    }


    static int _2D_wrap0(InputMethodInfo inputmethodinfo, InputMethodSubtype inputmethodsubtype)
    {
        return calculateSubtypeId(inputmethodinfo, inputmethodsubtype);
    }

    private InputMethodSubtypeSwitchingController(InputMethodUtils.InputMethodSettings inputmethodsettings, Context context)
    {
        mSettings = inputmethodsettings;
        resetCircularListLocked(context);
    }

    private static int calculateSubtypeId(InputMethodInfo inputmethodinfo, InputMethodSubtype inputmethodsubtype)
    {
        int i;
        if(inputmethodsubtype != null)
            i = InputMethodUtils.getSubtypeIdFromHashCode(inputmethodinfo, inputmethodsubtype.hashCode());
        else
            i = -1;
        return i;
    }

    public static InputMethodSubtypeSwitchingController createInstanceLocked(InputMethodUtils.InputMethodSettings inputmethodsettings, Context context)
    {
        return new InputMethodSubtypeSwitchingController(inputmethodsettings, context);
    }

    public void dump(Printer printer)
    {
        if(mController != null)
            mController.dump(printer);
        else
            printer.println("    mController=null");
    }

    public ImeSubtypeListItem getNextInputMethodLocked(boolean flag, InputMethodInfo inputmethodinfo, InputMethodSubtype inputmethodsubtype, boolean flag1)
    {
        if(mController == null)
            return null;
        else
            return mController.getNextInputMethod(flag, inputmethodinfo, inputmethodsubtype, flag1);
    }

    public List getSortedInputMethodAndSubtypeListLocked(boolean flag, boolean flag1)
    {
        return mSubtypeList.getSortedInputMethodAndSubtypeList(flag, flag1);
    }

    public void onUserActionLocked(InputMethodInfo inputmethodinfo, InputMethodSubtype inputmethodsubtype)
    {
        if(mController == null)
        {
            return;
        } else
        {
            mController.onUserActionLocked(inputmethodinfo, inputmethodsubtype);
            return;
        }
    }

    public void resetCircularListLocked(Context context)
    {
        mSubtypeList = new InputMethodAndSubtypeList(context, mSettings);
        mController = ControllerImpl.createFrom(mController, mSubtypeList.getSortedInputMethodAndSubtypeList(false, false));
    }

    private static final boolean DEBUG = false;
    private static final int NOT_A_SUBTYPE_ID = -1;
    private static final String TAG = com/android/internal/inputmethod/InputMethodSubtypeSwitchingController.getSimpleName();
    private ControllerImpl mController;
    private final InputMethodUtils.InputMethodSettings mSettings;
    private InputMethodAndSubtypeList mSubtypeList;


    // Unreferenced inner class com/android/internal/inputmethod/InputMethodSubtypeSwitchingController$InputMethodAndSubtypeList$1

/* anonymous class */
    class InputMethodAndSubtypeList._cls1
        implements Comparator
    {

        public int compare(InputMethodInfo inputmethodinfo, InputMethodInfo inputmethodinfo1)
        {
            if(inputmethodinfo1 == null)
                return 0;
            if(inputmethodinfo == null)
                return 1;
            if(InputMethodAndSubtypeList._2D_get0(InputMethodAndSubtypeList.this) == null)
            {
                return inputmethodinfo.getId().compareTo(inputmethodinfo1.getId());
            } else
            {
                inputmethodinfo = (new StringBuilder()).append(inputmethodinfo.loadLabel(InputMethodAndSubtypeList._2D_get0(InputMethodAndSubtypeList.this))).append("/").append(inputmethodinfo.getId()).toString();
                inputmethodinfo1 = (new StringBuilder()).append(inputmethodinfo1.loadLabel(InputMethodAndSubtypeList._2D_get0(InputMethodAndSubtypeList.this))).append("/").append(inputmethodinfo1.getId()).toString();
                return inputmethodinfo.toString().compareTo(inputmethodinfo1.toString());
            }
        }

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((InputMethodInfo)obj, (InputMethodInfo)obj1);
        }

        final InputMethodAndSubtypeList this$1;

            
            {
                this$1 = InputMethodAndSubtypeList.this;
                super();
            }
    }

}
