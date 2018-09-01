// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.nfc.cardemulation;

import android.content.ComponentName;
import android.content.pm.*;
import android.content.res.*;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.util.Xml;
import java.io.*;
import java.util.*;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.nfc.cardemulation:
//            AidGroup, CardEmulation

public class ApduServiceInfo
    implements Parcelable
{

    public ApduServiceInfo(PackageManager packagemanager, ResolveInfo resolveinfo, boolean flag)
        throws XmlPullParserException, IOException
    {
        ServiceInfo serviceinfo;
        XmlResourceParser xmlresourceparser;
        XmlResourceParser xmlresourceparser1;
        serviceinfo = resolveinfo.serviceInfo;
        xmlresourceparser = null;
        xmlresourceparser1 = null;
        if(!flag)
            break MISSING_BLOCK_LABEL_146;
        Object obj = serviceinfo.loadXmlMetaData(packagemanager, "android.nfc.cardemulation.host_apdu_service");
        XmlResourceParser xmlresourceparser2;
        xmlresourceparser2 = ((XmlResourceParser) (obj));
        if(obj != null)
            break MISSING_BLOCK_LABEL_201;
        xmlresourceparser1 = ((XmlResourceParser) (obj));
        xmlresourceparser = ((XmlResourceParser) (obj));
        packagemanager = JVM INSTR new #40  <Class XmlPullParserException>;
        xmlresourceparser1 = ((XmlResourceParser) (obj));
        xmlresourceparser = ((XmlResourceParser) (obj));
        packagemanager.XmlPullParserException("No android.nfc.cardemulation.host_apdu_service meta-data");
        xmlresourceparser1 = ((XmlResourceParser) (obj));
        xmlresourceparser = ((XmlResourceParser) (obj));
        try
        {
            throw packagemanager;
        }
        // Misplaced declaration of an exception variable
        catch(PackageManager packagemanager)
        {
            xmlresourceparser = xmlresourceparser1;
        }
        packagemanager = JVM INSTR new #40  <Class XmlPullParserException>;
        xmlresourceparser = xmlresourceparser1;
        resolveinfo = JVM INSTR new #66  <Class StringBuilder>;
        xmlresourceparser = xmlresourceparser1;
        resolveinfo.StringBuilder();
        xmlresourceparser = xmlresourceparser1;
        packagemanager.XmlPullParserException(resolveinfo.append("Unable to create context for: ").append(serviceinfo.packageName).toString());
        xmlresourceparser = xmlresourceparser1;
        throw packagemanager;
        packagemanager;
        if(xmlresourceparser != null)
            xmlresourceparser.close();
        throw packagemanager;
        obj = serviceinfo.loadXmlMetaData(packagemanager, "android.nfc.cardemulation.off_host_apdu_service");
        xmlresourceparser2 = ((XmlResourceParser) (obj));
        if(obj != null)
            break MISSING_BLOCK_LABEL_201;
        xmlresourceparser1 = ((XmlResourceParser) (obj));
        xmlresourceparser = ((XmlResourceParser) (obj));
        packagemanager = JVM INSTR new #40  <Class XmlPullParserException>;
        xmlresourceparser1 = ((XmlResourceParser) (obj));
        xmlresourceparser = ((XmlResourceParser) (obj));
        packagemanager.XmlPullParserException("No android.nfc.cardemulation.off_host_apdu_service meta-data");
        xmlresourceparser1 = ((XmlResourceParser) (obj));
        xmlresourceparser = ((XmlResourceParser) (obj));
        throw packagemanager;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        int i = xmlresourceparser2.getEventType();
_L2:
        if(i == 2 || i == 1)
            break; /* Loop/switch isn't completed */
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        i = xmlresourceparser2.next();
        if(true) goto _L2; else goto _L1
_L1:
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        obj = xmlresourceparser2.getName();
        if(!flag)
            break MISSING_BLOCK_LABEL_327;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        if(!("host-apdu-service".equals(obj) ^ true))
            break MISSING_BLOCK_LABEL_327;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        packagemanager = JVM INSTR new #40  <Class XmlPullParserException>;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        packagemanager.XmlPullParserException("Meta-data does not start with <host-apdu-service> tag");
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        throw packagemanager;
        if(flag)
            break MISSING_BLOCK_LABEL_387;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        if(!("offhost-apdu-service".equals(obj) ^ true))
            break MISSING_BLOCK_LABEL_387;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        packagemanager = JVM INSTR new #40  <Class XmlPullParserException>;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        packagemanager.XmlPullParserException("Meta-data does not start with <offhost-apdu-service> tag");
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        throw packagemanager;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        Resources resources = packagemanager.getResourcesForApplication(serviceinfo.applicationInfo);
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        obj = Xml.asAttributeSet(xmlresourceparser2);
        if(!flag) goto _L4; else goto _L3
_L3:
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        packagemanager = resources.obtainAttributes(((android.util.AttributeSet) (obj)), com.android.internal.R.styleable.HostApduService);
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        mService = resolveinfo;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        mDescription = packagemanager.getString(0);
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        mRequiresDeviceUnlock = packagemanager.getBoolean(2, false);
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        mBannerResourceId = packagemanager.getResourceId(3, -1);
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        mSettingsActivityName = packagemanager.getString(1);
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        packagemanager.recycle();
_L11:
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        packagemanager = JVM INSTR new #170 <Class HashMap>;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        packagemanager.HashMap();
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        mStaticAidGroups = packagemanager;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        packagemanager = JVM INSTR new #170 <Class HashMap>;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        packagemanager.HashMap();
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        mDynamicAidGroups = packagemanager;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        mOnHost = flag;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        i = xmlresourceparser2.getDepth();
        packagemanager = null;
_L10:
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        int j = xmlresourceparser2.next();
        if(j != 3)
            break MISSING_BLOCK_LABEL_688;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        if(xmlresourceparser2.getDepth() <= i)
            break MISSING_BLOCK_LABEL_1772;
        if(j == 1)
            break MISSING_BLOCK_LABEL_1772;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        resolveinfo = xmlresourceparser2.getName();
        if(j != 2) goto _L6; else goto _L5
_L5:
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        if(!"aid-group".equals(resolveinfo) || packagemanager != null) goto _L6; else goto _L7
_L7:
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        Object obj1 = resources.obtainAttributes(((android.util.AttributeSet) (obj)), com.android.internal.R.styleable.AidGroup);
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        packagemanager = ((TypedArray) (obj1)).getString(1);
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        Object obj2 = ((TypedArray) (obj1)).getString(0);
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        resolveinfo = packagemanager;
        if(!"payment".equals(packagemanager))
            resolveinfo = "other";
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        packagemanager = (AidGroup)mStaticAidGroups.get(resolveinfo);
        if(packagemanager == null) goto _L9; else goto _L8
_L8:
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        if("other".equals(resolveinfo))
            break MISSING_BLOCK_LABEL_909;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        packagemanager = JVM INSTR new #66  <Class StringBuilder>;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        packagemanager.StringBuilder();
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        Log.e("ApduServiceInfo", packagemanager.append("Not allowing multiple aid-groups in the ").append(resolveinfo).append(" category").toString());
        packagemanager = null;
_L12:
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        ((TypedArray) (obj1)).recycle();
          goto _L10
_L4:
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        packagemanager = resources.obtainAttributes(((android.util.AttributeSet) (obj)), com.android.internal.R.styleable.OffHostApduService);
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        mService = resolveinfo;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        mDescription = packagemanager.getString(0);
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        mRequiresDeviceUnlock = false;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        mBannerResourceId = packagemanager.getResourceId(2, -1);
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        mSettingsActivityName = packagemanager.getString(1);
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        packagemanager.recycle();
          goto _L11
_L9:
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        packagemanager = new AidGroup(resolveinfo, ((String) (obj2)));
          goto _L12
_L6:
        if(j != 3)
            break MISSING_BLOCK_LABEL_1173;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        if(!"aid-group".equals(resolveinfo) || packagemanager == null)
            break MISSING_BLOCK_LABEL_1173;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        if(((AidGroup) (packagemanager)).aids.size() <= 0) goto _L14; else goto _L13
_L13:
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        if(mStaticAidGroups.containsKey(((AidGroup) (packagemanager)).category))
            break MISSING_BLOCK_LABEL_1149;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        mStaticAidGroups.put(((AidGroup) (packagemanager)).category, packagemanager);
_L15:
        packagemanager = null;
          goto _L10
_L14:
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        Log.e("ApduServiceInfo", "Not adding <aid-group> with empty or invalid AIDs");
          goto _L15
        if(j != 2)
            break MISSING_BLOCK_LABEL_1368;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        if(!"aid-filter".equals(resolveinfo) || packagemanager == null)
            break MISSING_BLOCK_LABEL_1368;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        obj1 = resources.obtainAttributes(((android.util.AttributeSet) (obj)), com.android.internal.R.styleable.AidFilter);
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        resolveinfo = ((TypedArray) (obj1)).getString(0).toUpperCase();
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        if(!CardEmulation.isValidAid(resolveinfo)) goto _L17; else goto _L16
_L16:
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        if(!(((AidGroup) (packagemanager)).aids.contains(resolveinfo) ^ true)) goto _L17; else goto _L18
_L18:
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        ((AidGroup) (packagemanager)).aids.add(resolveinfo);
_L19:
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        ((TypedArray) (obj1)).recycle();
          goto _L10
_L17:
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        obj2 = JVM INSTR new #66  <Class StringBuilder>;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        ((StringBuilder) (obj2)).StringBuilder();
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        Log.e("ApduServiceInfo", ((StringBuilder) (obj2)).append("Ignoring invalid or duplicate aid: ").append(resolveinfo).toString());
          goto _L19
        if(j != 2) goto _L21; else goto _L20
_L20:
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        if(!"aid-prefix-filter".equals(resolveinfo) || packagemanager == null) goto _L21; else goto _L22
_L22:
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        obj2 = resources.obtainAttributes(((android.util.AttributeSet) (obj)), com.android.internal.R.styleable.AidFilter);
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        resolveinfo = ((TypedArray) (obj2)).getString(0).toUpperCase().concat("*");
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        if(!CardEmulation.isValidAid(resolveinfo)) goto _L24; else goto _L23
_L23:
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        if(!(((AidGroup) (packagemanager)).aids.contains(resolveinfo) ^ true)) goto _L24; else goto _L25
_L25:
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        ((AidGroup) (packagemanager)).aids.add(resolveinfo);
_L26:
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        ((TypedArray) (obj2)).recycle();
          goto _L10
_L24:
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        obj1 = JVM INSTR new #66  <Class StringBuilder>;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        ((StringBuilder) (obj1)).StringBuilder();
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        Log.e("ApduServiceInfo", ((StringBuilder) (obj1)).append("Ignoring invalid or duplicate aid: ").append(resolveinfo).toString());
          goto _L26
_L21:
        if(j != 2) goto _L10; else goto _L27
_L27:
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        if(!resolveinfo.equals("aid-suffix-filter") || packagemanager == null) goto _L10; else goto _L28
_L28:
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        obj1 = resources.obtainAttributes(((android.util.AttributeSet) (obj)), com.android.internal.R.styleable.AidFilter);
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        resolveinfo = ((TypedArray) (obj1)).getString(0).toUpperCase().concat("#");
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        if(!CardEmulation.isValidAid(resolveinfo)) goto _L30; else goto _L29
_L29:
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        if(!(((AidGroup) (packagemanager)).aids.contains(resolveinfo) ^ true)) goto _L30; else goto _L31
_L31:
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        ((AidGroup) (packagemanager)).aids.add(resolveinfo);
_L32:
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        ((TypedArray) (obj1)).recycle();
          goto _L10
_L30:
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        obj2 = JVM INSTR new #66  <Class StringBuilder>;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        ((StringBuilder) (obj2)).StringBuilder();
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        Log.e("ApduServiceInfo", ((StringBuilder) (obj2)).append("Ignoring invalid or duplicate aid: ").append(resolveinfo).toString());
          goto _L32
        if(xmlresourceparser2 != null)
            xmlresourceparser2.close();
        mUid = serviceinfo.applicationInfo.uid;
        return;
          goto _L10
    }

    public ApduServiceInfo(ResolveInfo resolveinfo, boolean flag, String s, ArrayList arraylist, ArrayList arraylist1, boolean flag1, int i, 
            int j, String s1)
    {
        mService = resolveinfo;
        mDescription = s;
        mStaticAidGroups = new HashMap();
        mDynamicAidGroups = new HashMap();
        mOnHost = flag;
        mRequiresDeviceUnlock = flag1;
        for(s = arraylist.iterator(); s.hasNext(); mStaticAidGroups.put(((AidGroup) (resolveinfo)).category, resolveinfo))
            resolveinfo = (AidGroup)s.next();

        for(resolveinfo = arraylist1.iterator(); resolveinfo.hasNext(); mDynamicAidGroups.put(((AidGroup) (s)).category, s))
            s = (AidGroup)resolveinfo.next();

        mBannerResourceId = i;
        mUid = j;
        mSettingsActivityName = s1;
    }

    public int describeContents()
    {
        return 0;
    }

    public void dump(FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        printwriter.println((new StringBuilder()).append("    ").append(getComponent()).append(" (Description: ").append(getDescription()).append(")").toString());
        printwriter.println("    Static AID groups:");
        for(filedescriptor = mStaticAidGroups.values().iterator(); filedescriptor.hasNext();)
        {
            as = (AidGroup)filedescriptor.next();
            printwriter.println((new StringBuilder()).append("        Category: ").append(((AidGroup) (as)).category).toString());
            as = ((AidGroup) (as)).aids.iterator();
            while(as.hasNext()) 
            {
                String s = (String)as.next();
                printwriter.println((new StringBuilder()).append("            AID: ").append(s).toString());
            }
        }

        printwriter.println("    Dynamic AID groups:");
        for(filedescriptor = mDynamicAidGroups.values().iterator(); filedescriptor.hasNext();)
        {
            as = (AidGroup)filedescriptor.next();
            printwriter.println((new StringBuilder()).append("        Category: ").append(((AidGroup) (as)).category).toString());
            as = ((AidGroup) (as)).aids.iterator();
            while(as.hasNext()) 
            {
                String s1 = (String)as.next();
                printwriter.println((new StringBuilder()).append("            AID: ").append(s1).toString());
            }
        }

        printwriter.println((new StringBuilder()).append("    Settings Activity: ").append(mSettingsActivityName).toString());
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(!(obj instanceof ApduServiceInfo))
            return false;
        else
            return ((ApduServiceInfo)obj).getComponent().equals(getComponent());
    }

    public ArrayList getAidGroups()
    {
        ArrayList arraylist = new ArrayList();
        for(Iterator iterator = mDynamicAidGroups.entrySet().iterator(); iterator.hasNext(); arraylist.add((AidGroup)((java.util.Map.Entry)iterator.next()).getValue()));
        Iterator iterator1 = mStaticAidGroups.entrySet().iterator();
        do
        {
            if(!iterator1.hasNext())
                break;
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator1.next();
            if(!mDynamicAidGroups.containsKey(entry.getKey()))
                arraylist.add((AidGroup)entry.getValue());
        } while(true);
        return arraylist;
    }

    public List getAids()
    {
        ArrayList arraylist = new ArrayList();
        for(Iterator iterator = getAidGroups().iterator(); iterator.hasNext(); arraylist.addAll(((AidGroup)iterator.next()).aids));
        return arraylist;
    }

    public String getCategoryForAid(String s)
    {
        for(Iterator iterator = getAidGroups().iterator(); iterator.hasNext();)
        {
            AidGroup aidgroup = (AidGroup)iterator.next();
            if(aidgroup.aids.contains(s.toUpperCase()))
                return aidgroup.category;
        }

        return null;
    }

    public ComponentName getComponent()
    {
        return new ComponentName(mService.serviceInfo.packageName, mService.serviceInfo.name);
    }

    public String getDescription()
    {
        return mDescription;
    }

    public AidGroup getDynamicAidGroupForCategory(String s)
    {
        return (AidGroup)mDynamicAidGroups.get(s);
    }

    public List getPrefixAids()
    {
        ArrayList arraylist = new ArrayList();
        for(Iterator iterator = getAidGroups().iterator(); iterator.hasNext();)
        {
            Iterator iterator1 = ((AidGroup)iterator.next()).aids.iterator();
            while(iterator1.hasNext()) 
            {
                String s = (String)iterator1.next();
                if(s.endsWith("*"))
                    arraylist.add(s);
            }
        }

        return arraylist;
    }

    public String getSettingsActivityName()
    {
        return mSettingsActivityName;
    }

    public List getSubsetAids()
    {
        ArrayList arraylist = new ArrayList();
        for(Iterator iterator = getAidGroups().iterator(); iterator.hasNext();)
        {
            Iterator iterator1 = ((AidGroup)iterator.next()).aids.iterator();
            while(iterator1.hasNext()) 
            {
                String s = (String)iterator1.next();
                if(s.endsWith("#"))
                    arraylist.add(s);
            }
        }

        return arraylist;
    }

    public int getUid()
    {
        return mUid;
    }

    public boolean hasCategory(String s)
    {
        boolean flag;
        if(!mStaticAidGroups.containsKey(s))
            flag = mDynamicAidGroups.containsKey(s);
        else
            flag = true;
        return flag;
    }

    public int hashCode()
    {
        return getComponent().hashCode();
    }

    public boolean isOnHost()
    {
        return mOnHost;
    }

    public CharSequence loadAppLabel(PackageManager packagemanager)
    {
        try
        {
            packagemanager = packagemanager.getApplicationLabel(packagemanager.getApplicationInfo(mService.resolvePackageName, 128));
        }
        // Misplaced declaration of an exception variable
        catch(PackageManager packagemanager)
        {
            return null;
        }
        return packagemanager;
    }

    public Drawable loadBanner(PackageManager packagemanager)
    {
        try
        {
            packagemanager = packagemanager.getResourcesForApplication(mService.serviceInfo.packageName).getDrawable(mBannerResourceId);
        }
        // Misplaced declaration of an exception variable
        catch(PackageManager packagemanager)
        {
            Log.e("ApduServiceInfo", "Could not load banner.");
            return null;
        }
        // Misplaced declaration of an exception variable
        catch(PackageManager packagemanager)
        {
            Log.e("ApduServiceInfo", "Could not load banner.");
            return null;
        }
        return packagemanager;
    }

    public Drawable loadIcon(PackageManager packagemanager)
    {
        return mService.loadIcon(packagemanager);
    }

    public CharSequence loadLabel(PackageManager packagemanager)
    {
        return mService.loadLabel(packagemanager);
    }

    public boolean removeDynamicAidGroupForCategory(String s)
    {
        boolean flag;
        if(mDynamicAidGroups.remove(s) != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean requiresUnlock()
    {
        return mRequiresDeviceUnlock;
    }

    public void setOrReplaceDynamicAidGroup(AidGroup aidgroup)
    {
        mDynamicAidGroups.put(aidgroup.getCategory(), aidgroup);
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder("ApduService: ");
        stringbuilder.append(getComponent());
        stringbuilder.append(", description: ").append(mDescription);
        stringbuilder.append(", Static AID Groups: ");
        for(Iterator iterator = mStaticAidGroups.values().iterator(); iterator.hasNext(); stringbuilder.append(((AidGroup)iterator.next()).toString()));
        stringbuilder.append(", Dynamic AID Groups: ");
        for(Iterator iterator1 = mDynamicAidGroups.values().iterator(); iterator1.hasNext(); stringbuilder.append(((AidGroup)iterator1.next()).toString()));
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        mService.writeToParcel(parcel, i);
        parcel.writeString(mDescription);
        if(mOnHost)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeInt(mStaticAidGroups.size());
        if(mStaticAidGroups.size() > 0)
            parcel.writeTypedList(new ArrayList(mStaticAidGroups.values()));
        parcel.writeInt(mDynamicAidGroups.size());
        if(mDynamicAidGroups.size() > 0)
            parcel.writeTypedList(new ArrayList(mDynamicAidGroups.values()));
        if(mRequiresDeviceUnlock)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeInt(mBannerResourceId);
        parcel.writeInt(mUid);
        parcel.writeString(mSettingsActivityName);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ApduServiceInfo createFromParcel(Parcel parcel)
        {
            ResolveInfo resolveinfo = (ResolveInfo)ResolveInfo.CREATOR.createFromParcel(parcel);
            String s = parcel.readString();
            boolean flag;
            ArrayList arraylist;
            ArrayList arraylist1;
            boolean flag1;
            if(parcel.readInt() != 0)
                flag = true;
            else
                flag = false;
            arraylist = new ArrayList();
            if(parcel.readInt() > 0)
                parcel.readTypedList(arraylist, AidGroup.CREATOR);
            arraylist1 = new ArrayList();
            if(parcel.readInt() > 0)
                parcel.readTypedList(arraylist1, AidGroup.CREATOR);
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            return new ApduServiceInfo(resolveinfo, flag, s, arraylist, arraylist1, flag1, parcel.readInt(), parcel.readInt(), parcel.readString());
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ApduServiceInfo[] newArray(int i)
        {
            return new ApduServiceInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    static final String TAG = "ApduServiceInfo";
    protected int mBannerResourceId;
    protected String mDescription;
    protected HashMap mDynamicAidGroups;
    protected boolean mOnHost;
    protected boolean mRequiresDeviceUnlock;
    protected ResolveInfo mService;
    protected String mSettingsActivityName;
    protected HashMap mStaticAidGroups;
    protected int mUid;

}
