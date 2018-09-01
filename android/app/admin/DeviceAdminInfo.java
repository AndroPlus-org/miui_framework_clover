// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.admin;

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
import java.util.HashMap;
import org.xmlpull.v1.*;

public final class DeviceAdminInfo
    implements Parcelable
{
    public static class PolicyInfo
    {

        public final int description;
        public final int descriptionForSecondaryUsers;
        public final int ident;
        public final int label;
        public final int labelForSecondaryUsers;
        public final String tag;

        public PolicyInfo(int i, String s, int j, int k)
        {
            this(i, s, j, k, j, k);
        }

        public PolicyInfo(int i, String s, int j, int k, int l, int i1)
        {
            ident = i;
            tag = s;
            label = j;
            description = k;
            labelForSecondaryUsers = l;
            descriptionForSecondaryUsers = i1;
        }
    }


    public DeviceAdminInfo(Context context, ActivityInfo activityinfo)
        throws XmlPullParserException, IOException
    {
        Object obj;
        mActivityInfo = activityinfo;
        obj = context.getPackageManager();
        activityinfo = null;
        context = null;
        Object obj1 = mActivityInfo.loadXmlMetaData(((PackageManager) (obj)), "android.app.device_admin");
        if(obj1 != null)
            break MISSING_BLOCK_LABEL_129;
        context = ((Context) (obj1));
        activityinfo = ((ActivityInfo) (obj1));
        obj = JVM INSTR new #163 <Class XmlPullParserException>;
        context = ((Context) (obj1));
        activityinfo = ((ActivityInfo) (obj1));
        ((XmlPullParserException) (obj)).XmlPullParserException("No android.app.device_admin meta-data");
        context = ((Context) (obj1));
        activityinfo = ((ActivityInfo) (obj1));
        try
        {
            throw obj;
        }
        // Misplaced declaration of an exception variable
        catch(ActivityInfo activityinfo)
        {
            activityinfo = context;
        }
        obj1 = JVM INSTR new #163 <Class XmlPullParserException>;
        activityinfo = context;
        obj = JVM INSTR new #191 <Class StringBuilder>;
        activityinfo = context;
        ((StringBuilder) (obj)).StringBuilder();
        activityinfo = context;
        ((XmlPullParserException) (obj1)).XmlPullParserException(((StringBuilder) (obj)).append("Unable to create context for: ").append(mActivityInfo.packageName).toString());
        activityinfo = context;
        throw obj1;
        context;
        if(activityinfo != null)
            activityinfo.close();
        throw context;
        context = ((Context) (obj1));
        activityinfo = ((ActivityInfo) (obj1));
        Object obj2 = ((PackageManager) (obj)).getResourcesForApplication(mActivityInfo.applicationInfo);
        context = ((Context) (obj1));
        activityinfo = ((ActivityInfo) (obj1));
        obj = Xml.asAttributeSet(((XmlPullParser) (obj1)));
_L2:
        context = ((Context) (obj1));
        activityinfo = ((ActivityInfo) (obj1));
        int i = ((XmlResourceParser) (obj1)).next();
        if(i != 1 && i != 2) goto _L2; else goto _L1
_L1:
        context = ((Context) (obj1));
        activityinfo = ((ActivityInfo) (obj1));
        if("device-admin".equals(((XmlResourceParser) (obj1)).getName()))
            break MISSING_BLOCK_LABEL_238;
        context = ((Context) (obj1));
        activityinfo = ((ActivityInfo) (obj1));
        obj = JVM INSTR new #163 <Class XmlPullParserException>;
        context = ((Context) (obj1));
        activityinfo = ((ActivityInfo) (obj1));
        ((XmlPullParserException) (obj)).XmlPullParserException("Meta-data does not start with device-admin tag");
        context = ((Context) (obj1));
        activityinfo = ((ActivityInfo) (obj1));
        throw obj;
        context = ((Context) (obj1));
        activityinfo = ((ActivityInfo) (obj1));
        obj = ((Resources) (obj2)).obtainAttributes(((android.util.AttributeSet) (obj)), com.android.internal.R.styleable.DeviceAdmin);
        context = ((Context) (obj1));
        activityinfo = ((ActivityInfo) (obj1));
        mVisible = ((TypedArray) (obj)).getBoolean(0, true);
        context = ((Context) (obj1));
        activityinfo = ((ActivityInfo) (obj1));
        ((TypedArray) (obj)).recycle();
        context = ((Context) (obj1));
        activityinfo = ((ActivityInfo) (obj1));
        i = ((XmlResourceParser) (obj1)).getDepth();
_L4:
        context = ((Context) (obj1));
        activityinfo = ((ActivityInfo) (obj1));
        int j = ((XmlResourceParser) (obj1)).next();
        if(j == 1)
            break MISSING_BLOCK_LABEL_573;
        if(j != 3)
            continue; /* Loop/switch isn't completed */
        context = ((Context) (obj1));
        activityinfo = ((ActivityInfo) (obj1));
        if(((XmlResourceParser) (obj1)).getDepth() <= i)
            break MISSING_BLOCK_LABEL_573;
        if(j == 3 || j == 4) goto _L4; else goto _L3
_L3:
        context = ((Context) (obj1));
        activityinfo = ((ActivityInfo) (obj1));
        if(!((XmlResourceParser) (obj1)).getName().equals("uses-policies")) goto _L4; else goto _L5
_L5:
        context = ((Context) (obj1));
        activityinfo = ((ActivityInfo) (obj1));
        j = ((XmlResourceParser) (obj1)).getDepth();
_L9:
        context = ((Context) (obj1));
        activityinfo = ((ActivityInfo) (obj1));
        int k = ((XmlResourceParser) (obj1)).next();
        if(k == 1) goto _L4; else goto _L6
_L6:
        if(k != 3)
            break; /* Loop/switch isn't completed */
        context = ((Context) (obj1));
        activityinfo = ((ActivityInfo) (obj1));
        if(((XmlResourceParser) (obj1)).getDepth() <= j) goto _L4; else goto _L7
_L7:
        if(k == 3 || k == 4) goto _L9; else goto _L8
_L8:
        context = ((Context) (obj1));
        activityinfo = ((ActivityInfo) (obj1));
        obj = ((XmlResourceParser) (obj1)).getName();
        context = ((Context) (obj1));
        activityinfo = ((ActivityInfo) (obj1));
        obj2 = (Integer)sKnownPolicies.get(obj);
        if(obj2 == null)
            break MISSING_BLOCK_LABEL_508;
        context = ((Context) (obj1));
        activityinfo = ((ActivityInfo) (obj1));
        mUsesPolicies = mUsesPolicies | 1 << ((Integer) (obj2)).intValue();
          goto _L9
        context = ((Context) (obj1));
        activityinfo = ((ActivityInfo) (obj1));
        obj2 = JVM INSTR new #191 <Class StringBuilder>;
        context = ((Context) (obj1));
        activityinfo = ((ActivityInfo) (obj1));
        ((StringBuilder) (obj2)).StringBuilder();
        context = ((Context) (obj1));
        activityinfo = ((ActivityInfo) (obj1));
        Log.w("DeviceAdminInfo", ((StringBuilder) (obj2)).append("Unknown tag under uses-policies of ").append(getComponent()).append(": ").append(((String) (obj))).toString());
          goto _L9
        if(obj1 != null)
            ((XmlResourceParser) (obj1)).close();
        return;
    }

    public DeviceAdminInfo(Context context, ResolveInfo resolveinfo)
        throws XmlPullParserException, IOException
    {
        this(context, resolveinfo.activityInfo);
    }

    DeviceAdminInfo(Parcel parcel)
    {
        mActivityInfo = (ActivityInfo)ActivityInfo.CREATOR.createFromParcel(parcel);
        mUsesPolicies = parcel.readInt();
    }

    public int describeContents()
    {
        return 0;
    }

    public void dump(Printer printer, String s)
    {
        printer.println((new StringBuilder()).append(s).append("Receiver:").toString());
        mActivityInfo.dump(printer, (new StringBuilder()).append(s).append("  ").toString());
    }

    public ActivityInfo getActivityInfo()
    {
        return mActivityInfo;
    }

    public ComponentName getComponent()
    {
        return new ComponentName(mActivityInfo.packageName, mActivityInfo.name);
    }

    public String getPackageName()
    {
        return mActivityInfo.packageName;
    }

    public String getReceiverName()
    {
        return mActivityInfo.name;
    }

    public String getTagForPolicy(int i)
    {
        return ((PolicyInfo)sRevKnownPolicies.get(i)).tag;
    }

    public ArrayList getUsedPolicies()
    {
        ArrayList arraylist = new ArrayList();
        for(int i = 0; i < sPoliciesDisplayOrder.size(); i++)
        {
            PolicyInfo policyinfo = (PolicyInfo)sPoliciesDisplayOrder.get(i);
            if(usesPolicy(policyinfo.ident))
                arraylist.add(policyinfo);
        }

        return arraylist;
    }

    public boolean isVisible()
    {
        return mVisible;
    }

    public CharSequence loadDescription(PackageManager packagemanager)
        throws android.content.res.Resources.NotFoundException
    {
        if(mActivityInfo.descriptionRes != 0)
            return packagemanager.getText(mActivityInfo.packageName, mActivityInfo.descriptionRes, mActivityInfo.applicationInfo);
        else
            throw new android.content.res.Resources.NotFoundException();
    }

    public Drawable loadIcon(PackageManager packagemanager)
    {
        return mActivityInfo.loadIcon(packagemanager);
    }

    public CharSequence loadLabel(PackageManager packagemanager)
    {
        return mActivityInfo.loadLabel(packagemanager);
    }

    public void readPoliciesFromXml(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        mUsesPolicies = Integer.parseInt(xmlpullparser.getAttributeValue(null, "flags"));
    }

    public String toString()
    {
        return (new StringBuilder()).append("DeviceAdminInfo{").append(mActivityInfo.name).append("}").toString();
    }

    public boolean usesPolicy(int i)
    {
        boolean flag = true;
        if((mUsesPolicies & 1 << i) == 0)
            flag = false;
        return flag;
    }

    public void writePoliciesToXml(XmlSerializer xmlserializer)
        throws IllegalArgumentException, IllegalStateException, IOException
    {
        xmlserializer.attribute(null, "flags", Integer.toString(mUsesPolicies));
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        mActivityInfo.writeToParcel(parcel, i);
        parcel.writeInt(mUsesPolicies);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public DeviceAdminInfo createFromParcel(Parcel parcel)
        {
            return new DeviceAdminInfo(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public DeviceAdminInfo[] newArray(int j)
        {
            return new DeviceAdminInfo[j];
        }

        public volatile Object[] newArray(int j)
        {
            return newArray(j);
        }

    }
;
    static final String TAG = "DeviceAdminInfo";
    public static final int USES_ENCRYPTED_STORAGE = 7;
    public static final int USES_POLICY_DEVICE_OWNER = -2;
    public static final int USES_POLICY_DISABLE_CAMERA = 8;
    public static final int USES_POLICY_DISABLE_KEYGUARD_FEATURES = 9;
    public static final int USES_POLICY_EXPIRE_PASSWORD = 6;
    public static final int USES_POLICY_FORCE_LOCK = 3;
    public static final int USES_POLICY_LIMIT_PASSWORD = 0;
    public static final int USES_POLICY_PROFILE_OWNER = -1;
    public static final int USES_POLICY_RESET_PASSWORD = 2;
    public static final int USES_POLICY_SETS_GLOBAL_PROXY = 5;
    public static final int USES_POLICY_WATCH_LOGIN = 1;
    public static final int USES_POLICY_WIPE_DATA = 4;
    static HashMap sKnownPolicies;
    static ArrayList sPoliciesDisplayOrder;
    static SparseArray sRevKnownPolicies;
    final ActivityInfo mActivityInfo;
    int mUsesPolicies;
    boolean mVisible;

    static 
    {
        sPoliciesDisplayOrder = new ArrayList();
        sKnownPolicies = new HashMap();
        sRevKnownPolicies = new SparseArray();
        sPoliciesDisplayOrder.add(new PolicyInfo(4, "wipe-data", 0x104053d, 0x1040532, 0x104053e, 0x1040533));
        sPoliciesDisplayOrder.add(new PolicyInfo(2, "reset-password", 0x104053a, 0x104052e));
        sPoliciesDisplayOrder.add(new PolicyInfo(0, "limit-password", 0x1040539, 0x104052d));
        sPoliciesDisplayOrder.add(new PolicyInfo(1, "watch-login", 0x104053c, 0x1040530, 0x104053c, 0x1040531));
        sPoliciesDisplayOrder.add(new PolicyInfo(3, "force-lock", 0x1040538, 0x104052c));
        sPoliciesDisplayOrder.add(new PolicyInfo(5, "set-global-proxy", 0x104053b, 0x104052f));
        sPoliciesDisplayOrder.add(new PolicyInfo(6, "expire-password", 0x1040537, 0x104052b));
        sPoliciesDisplayOrder.add(new PolicyInfo(7, "encrypted-storage", 0x1040536, 0x104052a));
        sPoliciesDisplayOrder.add(new PolicyInfo(8, "disable-camera", 0x1040534, 0x1040528));
        sPoliciesDisplayOrder.add(new PolicyInfo(9, "disable-keyguard-features", 0x1040535, 0x1040529));
        for(int i = 0; i < sPoliciesDisplayOrder.size(); i++)
        {
            PolicyInfo policyinfo = (PolicyInfo)sPoliciesDisplayOrder.get(i);
            sRevKnownPolicies.put(policyinfo.ident, policyinfo);
            sKnownPolicies.put(policyinfo.tag, Integer.valueOf(policyinfo.ident));
        }

    }
}
