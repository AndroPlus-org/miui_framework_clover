// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.textservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.*;
import android.content.res.*;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.view.textservice:
//            SpellCheckerSubtype

public final class SpellCheckerInfo
    implements Parcelable
{

    public SpellCheckerInfo(Context context, ResolveInfo resolveinfo)
        throws XmlPullParserException, IOException
    {
        ServiceInfo serviceinfo;
        Object obj;
        mSubtypes = new ArrayList();
        mService = resolveinfo;
        serviceinfo = resolveinfo.serviceInfo;
        mId = (new ComponentName(serviceinfo.packageName, serviceinfo.name)).flattenToShortString();
        obj = context.getPackageManager();
        resolveinfo = null;
        context = null;
        Object obj1 = serviceinfo.loadXmlMetaData(((PackageManager) (obj)), "android.view.textservice.scs");
        if(obj1 != null)
            break MISSING_BLOCK_LABEL_214;
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        obj = JVM INSTR new #41  <Class XmlPullParserException>;
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        ((XmlPullParserException) (obj)).XmlPullParserException("No android.view.textservice.scs meta-data");
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        throw obj;
        Exception exception;
        exception;
        resolveinfo = context;
        obj1 = TAG;
        resolveinfo = context;
        obj = JVM INSTR new #96  <Class StringBuilder>;
        resolveinfo = context;
        ((StringBuilder) (obj)).StringBuilder();
        resolveinfo = context;
        Slog.e(((String) (obj1)), ((StringBuilder) (obj)).append("Caught exception: ").append(exception).toString());
        resolveinfo = context;
        obj = JVM INSTR new #41  <Class XmlPullParserException>;
        resolveinfo = context;
        obj1 = JVM INSTR new #96  <Class StringBuilder>;
        resolveinfo = context;
        ((StringBuilder) (obj1)).StringBuilder();
        resolveinfo = context;
        ((XmlPullParserException) (obj)).XmlPullParserException(((StringBuilder) (obj1)).append("Unable to create context for: ").append(serviceinfo.packageName).toString());
        resolveinfo = context;
        throw obj;
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
        if("spell-checker".equals(((XmlResourceParser) (obj1)).getName()))
            break MISSING_BLOCK_LABEL_325;
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        obj = JVM INSTR new #41  <Class XmlPullParserException>;
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        ((XmlPullParserException) (obj)).XmlPullParserException("Meta-data does not start with spell-checker tag");
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        throw obj;
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        TypedArray typedarray = resources.obtainAttributes(attributeset, com.android.internal.R.styleable.SpellChecker);
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        int j = typedarray.getResourceId(0, 0);
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        obj = typedarray.getString(1);
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        typedarray.recycle();
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        int k = ((XmlResourceParser) (obj1)).getDepth();
_L4:
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        i = ((XmlResourceParser) (obj1)).next();
        if(i != 3)
            break MISSING_BLOCK_LABEL_437;
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        if(((XmlResourceParser) (obj1)).getDepth() <= k)
            break; /* Loop/switch isn't completed */
        if(i == 1)
            break; /* Loop/switch isn't completed */
        if(i != 2)
            continue; /* Loop/switch isn't completed */
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        if("subtype".equals(((XmlResourceParser) (obj1)).getName()))
            break MISSING_BLOCK_LABEL_503;
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        obj = JVM INSTR new #41  <Class XmlPullParserException>;
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        ((XmlPullParserException) (obj)).XmlPullParserException("Meta-data in spell-checker does not start with subtype tag");
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        throw obj;
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        typedarray = resources.obtainAttributes(attributeset, com.android.internal.R.styleable.SpellChecker_Subtype);
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        SpellCheckerSubtype spellcheckersubtype = JVM INSTR new #192 <Class SpellCheckerSubtype>;
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        spellcheckersubtype.SpellCheckerSubtype(typedarray.getResourceId(0, 0), typedarray.getString(1), typedarray.getString(4), typedarray.getString(2), typedarray.getInt(3, 0));
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        mSubtypes.add(spellcheckersubtype);
        if(true) goto _L4; else goto _L3
_L3:
        if(obj1 != null)
            ((XmlResourceParser) (obj1)).close();
        mLabel = j;
        mSettingsActivityName = ((String) (obj));
        return;
    }

    public SpellCheckerInfo(Parcel parcel)
    {
        mSubtypes = new ArrayList();
        mLabel = parcel.readInt();
        mId = parcel.readString();
        mSettingsActivityName = parcel.readString();
        mService = (ResolveInfo)ResolveInfo.CREATOR.createFromParcel(parcel);
        parcel.readTypedList(mSubtypes, SpellCheckerSubtype.CREATOR);
    }

    public int describeContents()
    {
        return 0;
    }

    public void dump(PrintWriter printwriter, String s)
    {
        printwriter.println((new StringBuilder()).append(s).append("mId=").append(mId).toString());
        printwriter.println((new StringBuilder()).append(s).append("mSettingsActivityName=").append(mSettingsActivityName).toString());
        printwriter.println((new StringBuilder()).append(s).append("Service:").toString());
        mService.dump(new PrintWriterPrinter(printwriter), (new StringBuilder()).append(s).append("  ").toString());
        int i = getSubtypeCount();
        for(int j = 0; j < i; j++)
        {
            SpellCheckerSubtype spellcheckersubtype = getSubtypeAt(j);
            printwriter.println((new StringBuilder()).append(s).append("  ").append("Subtype #").append(j).append(":").toString());
            printwriter.println((new StringBuilder()).append(s).append("    ").append("locale=").append(spellcheckersubtype.getLocale()).append(" languageTag=").append(spellcheckersubtype.getLanguageTag()).toString());
            printwriter.println((new StringBuilder()).append(s).append("    ").append("extraValue=").append(spellcheckersubtype.getExtraValue()).toString());
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

    public String getPackageName()
    {
        return mService.serviceInfo.packageName;
    }

    public ServiceInfo getServiceInfo()
    {
        return mService.serviceInfo;
    }

    public String getSettingsActivity()
    {
        return mSettingsActivityName;
    }

    public SpellCheckerSubtype getSubtypeAt(int i)
    {
        return (SpellCheckerSubtype)mSubtypes.get(i);
    }

    public int getSubtypeCount()
    {
        return mSubtypes.size();
    }

    public Drawable loadIcon(PackageManager packagemanager)
    {
        return mService.loadIcon(packagemanager);
    }

    public CharSequence loadLabel(PackageManager packagemanager)
    {
        if(mLabel == 0 || packagemanager == null)
            return "";
        else
            return packagemanager.getText(getPackageName(), mLabel, mService.serviceInfo.applicationInfo);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mLabel);
        parcel.writeString(mId);
        parcel.writeString(mSettingsActivityName);
        mService.writeToParcel(parcel, i);
        parcel.writeTypedList(mSubtypes);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public SpellCheckerInfo createFromParcel(Parcel parcel)
        {
            return new SpellCheckerInfo(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public SpellCheckerInfo[] newArray(int i)
        {
            return new SpellCheckerInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String TAG = android/view/textservice/SpellCheckerInfo.getSimpleName();
    private final String mId;
    private final int mLabel;
    private final ResolveInfo mService;
    private final String mSettingsActivityName;
    private final ArrayList mSubtypes;

}
