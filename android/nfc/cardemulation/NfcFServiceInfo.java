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
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.nfc.cardemulation:
//            NfcFCardEmulation

public final class NfcFServiceInfo
    implements Parcelable
{

    public NfcFServiceInfo(PackageManager packagemanager, ResolveInfo resolveinfo)
        throws XmlPullParserException, IOException
    {
        ServiceInfo serviceinfo;
        XmlResourceParser xmlresourceparser;
        XmlResourceParser xmlresourceparser1;
        serviceinfo = resolveinfo.serviceInfo;
        xmlresourceparser = null;
        xmlresourceparser1 = null;
        XmlResourceParser xmlresourceparser2 = serviceinfo.loadXmlMetaData(packagemanager, "android.nfc.cardemulation.host_nfcf_service");
        if(xmlresourceparser2 != null)
            break MISSING_BLOCK_LABEL_135;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        packagemanager = JVM INSTR new #39  <Class XmlPullParserException>;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        packagemanager.XmlPullParserException("No android.nfc.cardemulation.host_nfcf_service meta-data");
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        try
        {
            throw packagemanager;
        }
        // Misplaced declaration of an exception variable
        catch(PackageManager packagemanager)
        {
            xmlresourceparser = xmlresourceparser1;
        }
        resolveinfo = JVM INSTR new #39  <Class XmlPullParserException>;
        xmlresourceparser = xmlresourceparser1;
        packagemanager = JVM INSTR new #65  <Class StringBuilder>;
        xmlresourceparser = xmlresourceparser1;
        packagemanager.StringBuilder();
        xmlresourceparser = xmlresourceparser1;
        resolveinfo.XmlPullParserException(packagemanager.append("Unable to create context for: ").append(serviceinfo.packageName).toString());
        xmlresourceparser = xmlresourceparser1;
        throw resolveinfo;
        packagemanager;
        if(xmlresourceparser != null)
            xmlresourceparser.close();
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
        if("host-nfcf-service".equals(xmlresourceparser2.getName()))
            break MISSING_BLOCK_LABEL_243;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        packagemanager = JVM INSTR new #39  <Class XmlPullParserException>;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        packagemanager.XmlPullParserException("Meta-data does not start with <host-nfcf-service> tag");
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        throw packagemanager;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        Resources resources = packagemanager.getResourcesForApplication(serviceinfo.applicationInfo);
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        android.util.AttributeSet attributeset = Xml.asAttributeSet(xmlresourceparser2);
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        packagemanager = resources.obtainAttributes(attributeset, com.android.internal.R.styleable.HostNfcFService);
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        mService = resolveinfo;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        mDescription = packagemanager.getString(0);
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        mDynamicSystemCode = null;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        mDynamicNfcid2 = null;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        packagemanager.recycle();
        Object obj;
        obj = null;
        resolveinfo = null;
        packagemanager = null;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        i = xmlresourceparser2.getDepth();
_L4:
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        int j = xmlresourceparser2.next();
        if(j != 3)
            break MISSING_BLOCK_LABEL_430;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        if(xmlresourceparser2.getDepth() <= i)
            break; /* Loop/switch isn't completed */
        if(j == 1)
            break; /* Loop/switch isn't completed */
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        Object obj1 = xmlresourceparser2.getName();
        if(j != 2)
            break MISSING_BLOCK_LABEL_639;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        if(!"system-code-filter".equals(obj1) || obj != null)
            break MISSING_BLOCK_LABEL_639;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        TypedArray typedarray = resources.obtainAttributes(attributeset, com.android.internal.R.styleable.SystemCodeFilter);
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        obj1 = typedarray.getString(0).toUpperCase();
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        obj = obj1;
        if(NfcFCardEmulation.isValidSystemCode(((String) (obj1))))
            break MISSING_BLOCK_LABEL_623;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        obj = obj1;
        if(!(((String) (obj1)).equalsIgnoreCase("NULL") ^ true))
            break MISSING_BLOCK_LABEL_623;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        obj = JVM INSTR new #65  <Class StringBuilder>;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        ((StringBuilder) (obj)).StringBuilder();
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        Log.e("NfcFServiceInfo", ((StringBuilder) (obj)).append("Invalid System Code: ").append(((String) (obj1))).toString());
        obj = null;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        typedarray.recycle();
        continue; /* Loop/switch isn't completed */
        if(j != 2)
            break MISSING_BLOCK_LABEL_841;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        if(!"nfcid2-filter".equals(obj1) || resolveinfo != null)
            break MISSING_BLOCK_LABEL_841;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        typedarray = resources.obtainAttributes(attributeset, com.android.internal.R.styleable.Nfcid2Filter);
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        obj1 = typedarray.getString(0).toUpperCase();
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        resolveinfo = ((ResolveInfo) (obj1));
        if(((String) (obj1)).equalsIgnoreCase("RANDOM"))
            break MISSING_BLOCK_LABEL_825;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        resolveinfo = ((ResolveInfo) (obj1));
        if(!(((String) (obj1)).equalsIgnoreCase("NULL") ^ true))
            break MISSING_BLOCK_LABEL_825;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        resolveinfo = ((ResolveInfo) (obj1));
        if(!(NfcFCardEmulation.isValidNfcid2(((String) (obj1))) ^ true))
            break MISSING_BLOCK_LABEL_825;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        resolveinfo = JVM INSTR new #65  <Class StringBuilder>;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        resolveinfo.StringBuilder();
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        Log.e("NfcFServiceInfo", resolveinfo.append("Invalid NFCID2: ").append(((String) (obj1))).toString());
        resolveinfo = null;
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        typedarray.recycle();
        continue; /* Loop/switch isn't completed */
        if(j != 2)
            continue; /* Loop/switch isn't completed */
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        if(!((String) (obj1)).equals("t3tPmm-filter") || packagemanager != null)
            continue; /* Loop/switch isn't completed */
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        obj1 = resources.obtainAttributes(attributeset, com.android.internal.R.styleable.T3tPmmFilter);
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        packagemanager = ((TypedArray) (obj1)).getString(0).toUpperCase();
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        ((TypedArray) (obj1)).recycle();
        if(true) goto _L4; else goto _L3
_L3:
        obj1 = obj;
        if(obj == null)
            obj1 = "NULL";
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        mSystemCode = ((String) (obj1));
        obj = resolveinfo;
        if(resolveinfo == null)
            obj = "NULL";
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        mNfcid2 = ((String) (obj));
        resolveinfo = packagemanager;
        if(packagemanager == null)
            resolveinfo = "FFFFFFFFFFFFFFFF";
        xmlresourceparser1 = xmlresourceparser2;
        xmlresourceparser = xmlresourceparser2;
        mT3tPmm = resolveinfo;
        if(xmlresourceparser2 != null)
            xmlresourceparser2.close();
        mUid = serviceinfo.applicationInfo.uid;
        return;
    }

    public NfcFServiceInfo(ResolveInfo resolveinfo, String s, String s1, String s2, String s3, String s4, int i, 
            String s5)
    {
        mService = resolveinfo;
        mDescription = s;
        mSystemCode = s1;
        mDynamicSystemCode = s2;
        mNfcid2 = s3;
        mDynamicNfcid2 = s4;
        mUid = i;
        mT3tPmm = s5;
    }

    public int describeContents()
    {
        return 0;
    }

    public void dump(FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        printwriter.println((new StringBuilder()).append("    ").append(getComponent()).append(" (Description: ").append(getDescription()).append(")").toString());
        printwriter.println((new StringBuilder()).append("    System Code: ").append(getSystemCode()).toString());
        printwriter.println((new StringBuilder()).append("    NFCID2: ").append(getNfcid2()).toString());
        printwriter.println((new StringBuilder()).append("    T3tPmm: ").append(getT3tPmm()).toString());
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(!(obj instanceof NfcFServiceInfo))
            return false;
        obj = (NfcFServiceInfo)obj;
        if(!((NfcFServiceInfo) (obj)).getComponent().equals(getComponent()))
            return false;
        if(!((NfcFServiceInfo) (obj)).mSystemCode.equalsIgnoreCase(mSystemCode))
            return false;
        if(!((NfcFServiceInfo) (obj)).mNfcid2.equalsIgnoreCase(mNfcid2))
            return false;
        return ((NfcFServiceInfo) (obj)).mT3tPmm.equalsIgnoreCase(mT3tPmm);
    }

    public ComponentName getComponent()
    {
        return new ComponentName(mService.serviceInfo.packageName, mService.serviceInfo.name);
    }

    public String getDescription()
    {
        return mDescription;
    }

    public String getNfcid2()
    {
        String s;
        if(mDynamicNfcid2 == null)
            s = mNfcid2;
        else
            s = mDynamicNfcid2;
        return s;
    }

    public String getSystemCode()
    {
        String s;
        if(mDynamicSystemCode == null)
            s = mSystemCode;
        else
            s = mDynamicSystemCode;
        return s;
    }

    public String getT3tPmm()
    {
        return mT3tPmm;
    }

    public int getUid()
    {
        return mUid;
    }

    public int hashCode()
    {
        return getComponent().hashCode();
    }

    public Drawable loadIcon(PackageManager packagemanager)
    {
        return mService.loadIcon(packagemanager);
    }

    public CharSequence loadLabel(PackageManager packagemanager)
    {
        return mService.loadLabel(packagemanager);
    }

    public void setOrReplaceDynamicNfcid2(String s)
    {
        mDynamicNfcid2 = s;
    }

    public void setOrReplaceDynamicSystemCode(String s)
    {
        mDynamicSystemCode = s;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder("NfcFService: ");
        stringbuilder.append(getComponent());
        stringbuilder.append(", description: ").append(mDescription);
        stringbuilder.append(", System Code: ").append(mSystemCode);
        if(mDynamicSystemCode != null)
            stringbuilder.append(", dynamic System Code: ").append(mDynamicSystemCode);
        stringbuilder.append(", NFCID2: ").append(mNfcid2);
        if(mDynamicNfcid2 != null)
            stringbuilder.append(", dynamic NFCID2: ").append(mDynamicNfcid2);
        stringbuilder.append(", T3T PMM:").append(mT3tPmm);
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        mService.writeToParcel(parcel, i);
        parcel.writeString(mDescription);
        parcel.writeString(mSystemCode);
        if(mDynamicSystemCode != null)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mDynamicSystemCode != null)
            parcel.writeString(mDynamicSystemCode);
        parcel.writeString(mNfcid2);
        if(mDynamicNfcid2 != null)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeInt(i);
        if(mDynamicNfcid2 != null)
            parcel.writeString(mDynamicNfcid2);
        parcel.writeInt(mUid);
        parcel.writeString(mT3tPmm);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public NfcFServiceInfo createFromParcel(Parcel parcel)
        {
            ResolveInfo resolveinfo = (ResolveInfo)ResolveInfo.CREATOR.createFromParcel(parcel);
            String s = parcel.readString();
            String s1 = parcel.readString();
            String s2 = null;
            if(parcel.readInt() != 0)
                s2 = parcel.readString();
            String s3 = parcel.readString();
            String s4 = null;
            if(parcel.readInt() != 0)
                s4 = parcel.readString();
            return new NfcFServiceInfo(resolveinfo, s, s1, s2, s3, s4, parcel.readInt(), parcel.readString());
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public NfcFServiceInfo[] newArray(int i)
        {
            return new NfcFServiceInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String DEFAULT_T3T_PMM = "FFFFFFFFFFFFFFFF";
    static final String TAG = "NfcFServiceInfo";
    final String mDescription;
    String mDynamicNfcid2;
    String mDynamicSystemCode;
    final String mNfcid2;
    final ResolveInfo mService;
    final String mSystemCode;
    final String mT3tPmm;
    final int mUid;

}
