// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.printservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.*;
import android.content.res.*;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.util.Xml;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

public final class PrintServiceInfo
    implements Parcelable
{

    public PrintServiceInfo(ResolveInfo resolveinfo, String s, String s1, String s2)
    {
        mId = (new ComponentName(resolveinfo.serviceInfo.packageName, resolveinfo.serviceInfo.name)).flattenToString();
        mResolveInfo = resolveinfo;
        mSettingsActivityName = s;
        mAddPrintersActivityName = s1;
        mAdvancedPrintOptionsActivityName = s2;
    }

    public PrintServiceInfo(Parcel parcel)
    {
        boolean flag = false;
        super();
        mId = parcel.readString();
        if(parcel.readByte() != 0)
            flag = true;
        mIsEnabled = flag;
        mResolveInfo = (ResolveInfo)parcel.readParcelable(null);
        mSettingsActivityName = parcel.readString();
        mAddPrintersActivityName = parcel.readString();
        mAdvancedPrintOptionsActivityName = parcel.readString();
    }

    public static PrintServiceInfo create(Context context, ResolveInfo resolveinfo)
    {
        Object obj;
        Object obj1;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj7;
        Object obj8;
        Object obj9;
        TypedArray typedarray;
        Object obj10;
        Object obj11;
        Object obj12;
        Object obj13;
        Object obj14;
        PackageManager packagemanager;
        XmlResourceParser xmlresourceparser;
        obj = null;
        obj1 = null;
        obj2 = null;
        obj3 = null;
        obj4 = null;
        Object obj5 = null;
        obj7 = null;
        obj8 = null;
        obj9 = null;
        typedarray = null;
        obj10 = null;
        obj11 = null;
        obj12 = null;
        obj13 = null;
        obj14 = null;
        packagemanager = context.getPackageManager();
        xmlresourceparser = resolveinfo.serviceInfo.loadXmlMetaData(packagemanager, "android.printservice");
        context = obj5;
        if(xmlresourceparser == null) goto _L2; else goto _L1
_L1:
        int i = 0;
_L4:
        Object obj6;
        String s;
        String s1;
        Object obj15;
        Object obj16;
        String s2;
        String s3;
        String s4;
        Context context1;
        if(i == 1 || i == 2)
            break; /* Loop/switch isn't completed */
        s = obj7;
        s1 = obj11;
        obj15 = obj1;
        obj16 = obj8;
        s2 = obj12;
        obj6 = obj2;
        s3 = obj9;
        s4 = obj13;
        context1 = obj3;
        i = xmlresourceparser.next();
        if(true) goto _L4; else goto _L3
_L3:
        s = obj7;
        s1 = obj11;
        obj15 = obj1;
        obj16 = obj8;
        s2 = obj12;
        obj6 = obj2;
        s3 = obj9;
        s4 = obj13;
        context1 = obj3;
        if("print-service".equals(xmlresourceparser.getName())) goto _L6; else goto _L5
_L5:
        s = obj7;
        s1 = obj11;
        obj15 = obj1;
        obj16 = obj8;
        s2 = obj12;
        obj6 = obj2;
        s3 = obj9;
        s4 = obj13;
        context1 = obj3;
        Log.e(LOG_TAG, "Ignoring meta-data that does not start with print-service tag");
        obj16 = obj4;
        obj6 = obj14;
        obj15 = typedarray;
_L9:
        context = ((Context) (obj15));
        obj10 = obj6;
        obj = obj16;
        if(xmlresourceparser != null)
        {
            xmlresourceparser.close();
            obj = obj16;
            obj10 = obj6;
            context = ((Context) (obj15));
        }
_L2:
        return new PrintServiceInfo(resolveinfo, ((String) (obj)), context, ((String) (obj10)));
_L6:
        s = obj7;
        s1 = obj11;
        obj15 = obj1;
        obj16 = obj8;
        s2 = obj12;
        obj6 = obj2;
        s3 = obj9;
        s4 = obj13;
        context1 = obj3;
        typedarray = packagemanager.getResourcesForApplication(resolveinfo.serviceInfo.applicationInfo).obtainAttributes(Xml.asAttributeSet(xmlresourceparser), com.android.internal.R.styleable.PrintService);
        s = obj7;
        s1 = obj11;
        obj15 = obj1;
        obj16 = obj8;
        s2 = obj12;
        obj6 = obj2;
        s3 = obj9;
        s4 = obj13;
        context1 = obj3;
        context = typedarray.getString(0);
        s = obj7;
        s1 = obj11;
        obj15 = context;
        obj16 = obj8;
        s2 = obj12;
        obj6 = context;
        s3 = obj9;
        s4 = obj13;
        context1 = context;
        obj10 = typedarray.getString(1);
        s = ((String) (obj10));
        s1 = obj11;
        obj15 = context;
        obj16 = obj10;
        s2 = obj12;
        obj6 = context;
        s3 = ((String) (obj10));
        s4 = obj13;
        context1 = context;
        obj = typedarray.getString(3);
        s = ((String) (obj10));
        s1 = ((String) (obj));
        obj15 = context;
        obj16 = obj10;
        s2 = ((String) (obj));
        obj6 = context;
        s3 = ((String) (obj10));
        s4 = ((String) (obj));
        context1 = context;
        typedarray.recycle();
        obj15 = obj10;
        obj6 = obj;
        obj16 = context;
        continue; /* Loop/switch isn't completed */
        IOException ioexception;
        ioexception;
        obj10 = LOG_TAG;
        context = JVM INSTR new #177 <Class StringBuilder>;
        context.StringBuilder();
        Log.w(((String) (obj10)), context.append("Error reading meta-data:").append(ioexception).toString());
        context = s;
        obj10 = s1;
        ioexception = ((IOException) (obj15));
        if(xmlresourceparser != null)
        {
            xmlresourceparser.close();
            context = s;
            obj10 = s1;
            ioexception = ((IOException) (obj15));
        }
        continue; /* Loop/switch isn't completed */
        context;
        context = LOG_TAG;
        obj10 = JVM INSTR new #177 <Class StringBuilder>;
        ((StringBuilder) (obj10)).StringBuilder();
        Log.e(context, ((StringBuilder) (obj10)).append("Unable to load resources for: ").append(resolveinfo.serviceInfo.packageName).toString());
        context = ((Context) (obj16));
        obj10 = s2;
        ioexception = ((IOException) (obj6));
        if(xmlresourceparser != null)
        {
            xmlresourceparser.close();
            context = ((Context) (obj16));
            obj10 = s2;
            ioexception = ((IOException) (obj6));
        }
        continue; /* Loop/switch isn't completed */
        context;
        ioexception = LOG_TAG;
        obj10 = JVM INSTR new #177 <Class StringBuilder>;
        ((StringBuilder) (obj10)).StringBuilder();
        Log.w(ioexception, ((StringBuilder) (obj10)).append("Error reading meta-data:").append(context).toString());
        context = s3;
        obj10 = s4;
        ioexception = context1;
        if(xmlresourceparser != null)
        {
            xmlresourceparser.close();
            context = s3;
            obj10 = s4;
            ioexception = context1;
        }
        if(true) goto _L2; else goto _L7
_L7:
        context;
        if(xmlresourceparser != null)
            xmlresourceparser.close();
        throw context;
        if(true) goto _L9; else goto _L8
_L8:
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        obj = (PrintServiceInfo)obj;
        if(mId == null)
        {
            if(((PrintServiceInfo) (obj)).mId != null)
                return false;
        } else
        if(!mId.equals(((PrintServiceInfo) (obj)).mId))
            return false;
        return true;
    }

    public String getAddPrintersActivityName()
    {
        return mAddPrintersActivityName;
    }

    public String getAdvancedOptionsActivityName()
    {
        return mAdvancedPrintOptionsActivityName;
    }

    public ComponentName getComponentName()
    {
        return new ComponentName(mResolveInfo.serviceInfo.packageName, mResolveInfo.serviceInfo.name);
    }

    public String getId()
    {
        return mId;
    }

    public ResolveInfo getResolveInfo()
    {
        return mResolveInfo;
    }

    public String getSettingsActivityName()
    {
        return mSettingsActivityName;
    }

    public int hashCode()
    {
        int i;
        if(mId == null)
            i = 0;
        else
            i = mId.hashCode();
        return i + 31;
    }

    public boolean isEnabled()
    {
        return mIsEnabled;
    }

    public void setIsEnabled(boolean flag)
    {
        mIsEnabled = flag;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("PrintServiceInfo{");
        stringbuilder.append("id=").append(mId);
        stringbuilder.append("isEnabled=").append(mIsEnabled);
        stringbuilder.append(", resolveInfo=").append(mResolveInfo);
        stringbuilder.append(", settingsActivityName=").append(mSettingsActivityName);
        stringbuilder.append(", addPrintersActivityName=").append(mAddPrintersActivityName);
        stringbuilder.append(", advancedPrintOptionsActivityName=").append(mAdvancedPrintOptionsActivityName);
        stringbuilder.append("}");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mId);
        if(mIsEnabled)
            i = 1;
        else
            i = 0;
        parcel.writeByte((byte)i);
        parcel.writeParcelable(mResolveInfo, 0);
        parcel.writeString(mSettingsActivityName);
        parcel.writeString(mAddPrintersActivityName);
        parcel.writeString(mAdvancedPrintOptionsActivityName);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public PrintServiceInfo createFromParcel(Parcel parcel)
        {
            return new PrintServiceInfo(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PrintServiceInfo[] newArray(int i)
        {
            return new PrintServiceInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String LOG_TAG = android/printservice/PrintServiceInfo.getSimpleName();
    private static final String TAG_PRINT_SERVICE = "print-service";
    private final String mAddPrintersActivityName;
    private final String mAdvancedPrintOptionsActivityName;
    private final String mId;
    private boolean mIsEnabled;
    private final ResolveInfo mResolveInfo;
    private final String mSettingsActivityName;

}
