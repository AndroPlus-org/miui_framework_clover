// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.inputmethod;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.*;
import android.content.res.*;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.view.inputmethod:
//            InputMethodSubtype, InputMethodSubtypeArray

public final class InputMethodInfo
    implements Parcelable
{

    public InputMethodInfo(Context context, ResolveInfo resolveinfo)
        throws XmlPullParserException, IOException
    {
        this(context, resolveinfo, null);
    }

    public InputMethodInfo(Context context, ResolveInfo resolveinfo, List list)
        throws XmlPullParserException, IOException
    {
        ServiceInfo serviceinfo;
        boolean flag;
        Object obj;
        ArrayList arraylist;
        mService = resolveinfo;
        serviceinfo = resolveinfo.serviceInfo;
        mId = computeId(resolveinfo);
        flag = true;
        mForceDefault = false;
        obj = context.getPackageManager();
        resolveinfo = null;
        context = null;
        arraylist = new ArrayList();
        Object obj1 = serviceinfo.loadXmlMetaData(((PackageManager) (obj)), "android.view.im");
        if(obj1 != null)
            break MISSING_BLOCK_LABEL_158;
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        list = JVM INSTR new #38  <Class XmlPullParserException>;
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        list.XmlPullParserException("No android.view.im meta-data");
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        try
        {
            throw list;
        }
        // Misplaced declaration of an exception variable
        catch(ResolveInfo resolveinfo)
        {
            resolveinfo = context;
        }
        list = JVM INSTR new #38  <Class XmlPullParserException>;
        resolveinfo = context;
        obj1 = JVM INSTR new #91  <Class StringBuilder>;
        resolveinfo = context;
        ((StringBuilder) (obj1)).StringBuilder();
        resolveinfo = context;
        list.XmlPullParserException(((StringBuilder) (obj1)).append("Unable to create context for: ").append(serviceinfo.packageName).toString());
        resolveinfo = context;
        throw list;
        context;
        if(resolveinfo != null)
            resolveinfo.close();
        throw context;
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        Resources resources = ((PackageManager) (obj)).getResourcesForApplication(serviceinfo.applicationInfo);
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        android.util.AttributeSet attributeset = Xml.asAttributeSet(((org.xmlpull.v1.XmlPullParser) (obj1)));
_L2:
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        int i = ((XmlResourceParser) (obj1)).next();
        if(i != 1 && i != 2) goto _L2; else goto _L1
_L1:
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        if("input-method".equals(((XmlResourceParser) (obj1)).getName()))
            break MISSING_BLOCK_LABEL_267;
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        list = JVM INSTR new #38  <Class XmlPullParserException>;
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        list.XmlPullParserException("Meta-data does not start with input-method tag");
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        throw list;
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        Object obj2 = resources.obtainAttributes(attributeset, com.android.internal.R.styleable.InputMethod);
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        obj = ((TypedArray) (obj2)).getString(1);
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        int k = ((TypedArray) (obj2)).getResourceId(0, 0);
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        boolean flag1 = ((TypedArray) (obj2)).getBoolean(2, false);
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        ((TypedArray) (obj2)).recycle();
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        i = ((XmlResourceParser) (obj1)).getDepth();
_L4:
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        int l = ((XmlResourceParser) (obj1)).next();
        if(l != 3)
            break MISSING_BLOCK_LABEL_394;
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        if(((XmlResourceParser) (obj1)).getDepth() <= i)
            break; /* Loop/switch isn't completed */
        if(l == 1)
            break; /* Loop/switch isn't completed */
        if(l != 2)
            continue; /* Loop/switch isn't completed */
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        if("subtype".equals(((XmlResourceParser) (obj1)).getName()))
            break MISSING_BLOCK_LABEL_457;
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        list = JVM INSTR new #38  <Class XmlPullParserException>;
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        list.XmlPullParserException("Meta-data in input-method does not start with subtype tag");
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        throw list;
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        obj2 = resources.obtainAttributes(attributeset, com.android.internal.R.styleable.InputMethod_Subtype);
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        InputMethodSubtype.InputMethodSubtypeBuilder inputmethodsubtypebuilder = JVM INSTR new #184 <Class InputMethodSubtype$InputMethodSubtypeBuilder>;
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        inputmethodsubtypebuilder.InputMethodSubtype.InputMethodSubtypeBuilder();
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        obj2 = inputmethodsubtypebuilder.setSubtypeNameResId(((TypedArray) (obj2)).getResourceId(0, 0)).setSubtypeIconResId(((TypedArray) (obj2)).getResourceId(1, 0)).setLanguageTag(((TypedArray) (obj2)).getString(9)).setSubtypeLocale(((TypedArray) (obj2)).getString(2)).setSubtypeMode(((TypedArray) (obj2)).getString(3)).setSubtypeExtraValue(((TypedArray) (obj2)).getString(4)).setIsAuxiliary(((TypedArray) (obj2)).getBoolean(5, false)).setOverridesImplicitlyEnabledSubtype(((TypedArray) (obj2)).getBoolean(6, false)).setSubtypeId(((TypedArray) (obj2)).getInt(7, 0)).setIsAsciiCapable(((TypedArray) (obj2)).getBoolean(8, false)).build();
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        if(!((InputMethodSubtype) (obj2)).isAuxiliary())
            flag = false;
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        arraylist.add(obj2);
        if(true) goto _L4; else goto _L3
_L3:
        if(obj1 != null)
            ((XmlResourceParser) (obj1)).close();
        if(arraylist.size() == 0)
            flag = false;
        if(list != null)
        {
            int i1 = list.size();
            int j = 0;
            while(j < i1) 
            {
                context = (InputMethodSubtype)list.get(j);
                if(!arraylist.contains(context))
                    arraylist.add(context);
                else
                    Slog.w("InputMethodInfo", (new StringBuilder()).append("Duplicated subtype definition found: ").append(context.getLocale()).append(", ").append(context.getMode()).toString());
                j++;
            }
        }
        mSubtypes = new InputMethodSubtypeArray(arraylist);
        mSettingsActivityName = ((String) (obj));
        mIsDefaultResId = k;
        mIsAuxIme = flag;
        mSupportsSwitchingToNextInputMethod = flag1;
        return;
    }

    public InputMethodInfo(ResolveInfo resolveinfo, boolean flag, String s, List list, int i, boolean flag1)
    {
        this(resolveinfo, flag, s, list, i, flag1, true);
    }

    public InputMethodInfo(ResolveInfo resolveinfo, boolean flag, String s, List list, int i, boolean flag1, boolean flag2)
    {
        ServiceInfo serviceinfo = resolveinfo.serviceInfo;
        mService = resolveinfo;
        mId = (new ComponentName(serviceinfo.packageName, serviceinfo.name)).flattenToShortString();
        mSettingsActivityName = s;
        mIsDefaultResId = i;
        mIsAuxIme = flag;
        mSubtypes = new InputMethodSubtypeArray(list);
        mForceDefault = flag1;
        mSupportsSwitchingToNextInputMethod = flag2;
    }

    InputMethodInfo(Parcel parcel)
    {
        boolean flag = true;
        super();
        mId = parcel.readString();
        mSettingsActivityName = parcel.readString();
        mIsDefaultResId = parcel.readInt();
        boolean flag1;
        if(parcel.readInt() == 1)
            flag1 = true;
        else
            flag1 = false;
        mIsAuxIme = flag1;
        if(parcel.readInt() == 1)
            flag1 = flag;
        else
            flag1 = false;
        mSupportsSwitchingToNextInputMethod = flag1;
        mService = (ResolveInfo)ResolveInfo.CREATOR.createFromParcel(parcel);
        mSubtypes = new InputMethodSubtypeArray(parcel);
        mForceDefault = false;
    }

    public InputMethodInfo(String s, String s1, CharSequence charsequence, String s2)
    {
        this(buildDummyResolveInfo(s, s1, charsequence), false, s2, null, 0, false, true);
    }

    private static ResolveInfo buildDummyResolveInfo(String s, String s1, CharSequence charsequence)
    {
        ResolveInfo resolveinfo = new ResolveInfo();
        ServiceInfo serviceinfo = new ServiceInfo();
        ApplicationInfo applicationinfo = new ApplicationInfo();
        applicationinfo.packageName = s;
        applicationinfo.enabled = true;
        serviceinfo.applicationInfo = applicationinfo;
        serviceinfo.enabled = true;
        serviceinfo.packageName = s;
        serviceinfo.name = s1;
        serviceinfo.exported = true;
        serviceinfo.nonLocalizedLabel = charsequence;
        resolveinfo.serviceInfo = serviceinfo;
        return resolveinfo;
    }

    public static String computeId(ResolveInfo resolveinfo)
    {
        resolveinfo = resolveinfo.serviceInfo;
        return (new ComponentName(((ServiceInfo) (resolveinfo)).packageName, ((ServiceInfo) (resolveinfo)).name)).flattenToShortString();
    }

    public int describeContents()
    {
        return 0;
    }

    public void dump(Printer printer, String s)
    {
        printer.println((new StringBuilder()).append(s).append("mId=").append(mId).append(" mSettingsActivityName=").append(mSettingsActivityName).append(" mSupportsSwitchingToNextInputMethod=").append(mSupportsSwitchingToNextInputMethod).toString());
        printer.println((new StringBuilder()).append(s).append("mIsDefaultResId=0x").append(Integer.toHexString(mIsDefaultResId)).toString());
        printer.println((new StringBuilder()).append(s).append("Service:").toString());
        mService.dump(printer, (new StringBuilder()).append(s).append("  ").toString());
    }

    public boolean equals(Object obj)
    {
        if(obj == this)
            return true;
        if(obj == null)
            return false;
        if(!(obj instanceof InputMethodInfo))
        {
            return false;
        } else
        {
            obj = (InputMethodInfo)obj;
            return mId.equals(((InputMethodInfo) (obj)).mId);
        }
    }

    public ComponentName getComponent()
    {
        return new ComponentName(mService.serviceInfo.packageName, mService.serviceInfo.name);
    }

    public String getId()
    {
        return mId;
    }

    public int getIsDefaultResourceId()
    {
        return mIsDefaultResId;
    }

    public String getPackageName()
    {
        return mService.serviceInfo.packageName;
    }

    public ServiceInfo getServiceInfo()
    {
        return mService.serviceInfo;
    }

    public String getServiceName()
    {
        return mService.serviceInfo.name;
    }

    public String getSettingsActivity()
    {
        return mSettingsActivityName;
    }

    public InputMethodSubtype getSubtypeAt(int i)
    {
        return mSubtypes.get(i);
    }

    public int getSubtypeCount()
    {
        return mSubtypes.getCount();
    }

    public int hashCode()
    {
        return mId.hashCode();
    }

    public boolean isAuxiliaryIme()
    {
        return mIsAuxIme;
    }

    public boolean isDefault(Context context)
    {
        if(mForceDefault)
            return true;
        if(getIsDefaultResourceId() == 0)
            return false;
        boolean flag;
        try
        {
            flag = context.createPackageContext(getPackageName(), 0).getResources().getBoolean(getIsDefaultResourceId());
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            return false;
        }
        return flag;
    }

    public Drawable loadIcon(PackageManager packagemanager)
    {
        return mService.loadIcon(packagemanager);
    }

    public CharSequence loadLabel(PackageManager packagemanager)
    {
        return mService.loadLabel(packagemanager);
    }

    public boolean supportsSwitchingToNextInputMethod()
    {
        return mSupportsSwitchingToNextInputMethod;
    }

    public String toString()
    {
        return (new StringBuilder()).append("InputMethodInfo{").append(mId).append(", settings: ").append(mSettingsActivityName).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeString(mId);
        parcel.writeString(mSettingsActivityName);
        parcel.writeInt(mIsDefaultResId);
        int j;
        if(mIsAuxIme)
            j = 1;
        else
            j = 0;
        parcel.writeInt(j);
        if(mSupportsSwitchingToNextInputMethod)
            j = ((flag) ? 1 : 0);
        else
            j = 0;
        parcel.writeInt(j);
        mService.writeToParcel(parcel, i);
        mSubtypes.writeToParcel(parcel);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public InputMethodInfo createFromParcel(Parcel parcel)
        {
            return new InputMethodInfo(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public InputMethodInfo[] newArray(int i)
        {
            return new InputMethodInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    static final String TAG = "InputMethodInfo";
    private final boolean mForceDefault;
    final String mId;
    private final boolean mIsAuxIme;
    final int mIsDefaultResId;
    final ResolveInfo mService;
    final String mSettingsActivityName;
    private final InputMethodSubtypeArray mSubtypes;
    private final boolean mSupportsSwitchingToNextInputMethod;

}
