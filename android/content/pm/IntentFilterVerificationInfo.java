// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.ArraySet;
import android.util.Log;
import com.android.internal.util.XmlUtils;
import java.io.IOException;
import java.util.*;
import org.xmlpull.v1.*;

public final class IntentFilterVerificationInfo
    implements Parcelable
{

    public IntentFilterVerificationInfo()
    {
        mDomains = new ArraySet();
        mPackageName = null;
        mMainStatus = 0;
    }

    public IntentFilterVerificationInfo(Parcel parcel)
    {
        mDomains = new ArraySet();
        readFromParcel(parcel);
    }

    public IntentFilterVerificationInfo(String s, ArraySet arrayset)
    {
        mDomains = new ArraySet();
        mPackageName = s;
        mDomains = arrayset;
        mMainStatus = 0;
    }

    public IntentFilterVerificationInfo(XmlPullParser xmlpullparser)
        throws IOException, XmlPullParserException
    {
        mDomains = new ArraySet();
        readFromXml(xmlpullparser);
    }

    public static String getStatusStringFromValue(long l)
    {
        StringBuilder stringbuilder = new StringBuilder();
        (int)(l >> 32);
        JVM INSTR tableswitch 1 4: default 44
    //                   1 79
    //                   2 56
    //                   3 89
    //                   4 99;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        stringbuilder.append("undefined");
_L7:
        return stringbuilder.toString();
_L3:
        stringbuilder.append("always : ");
        stringbuilder.append(Long.toHexString(-1L & l));
        continue; /* Loop/switch isn't completed */
_L2:
        stringbuilder.append("ask");
        continue; /* Loop/switch isn't completed */
_L4:
        stringbuilder.append("never");
        continue; /* Loop/switch isn't completed */
_L5:
        stringbuilder.append("always-ask");
        if(true) goto _L7; else goto _L6
_L6:
    }

    private void readFromParcel(Parcel parcel)
    {
        mPackageName = parcel.readString();
        mMainStatus = parcel.readInt();
        ArrayList arraylist = new ArrayList();
        parcel.readStringList(arraylist);
        mDomains.addAll(arraylist);
    }

    public int describeContents()
    {
        return 0;
    }

    public Set getDomains()
    {
        return mDomains;
    }

    public String getDomainsString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        String s;
        for(Iterator iterator = mDomains.iterator(); iterator.hasNext(); stringbuilder.append(s))
        {
            s = (String)iterator.next();
            if(stringbuilder.length() > 0)
                stringbuilder.append(" ");
        }

        return stringbuilder.toString();
    }

    int getIntFromXml(XmlPullParser xmlpullparser, String s, int i)
    {
        String s1 = xmlpullparser.getAttributeValue(null, s);
        if(TextUtils.isEmpty(s1))
        {
            xmlpullparser = (new StringBuilder()).append("Missing element under ").append(TAG).append(": ").append(s).append(" at ").append(xmlpullparser.getPositionDescription()).toString();
            Log.w(TAG, xmlpullparser);
            return i;
        } else
        {
            return Integer.parseInt(s1);
        }
    }

    public String getPackageName()
    {
        return mPackageName;
    }

    public int getStatus()
    {
        return mMainStatus;
    }

    public String getStatusString()
    {
        return getStatusStringFromValue((long)mMainStatus << 32);
    }

    String getStringFromXml(XmlPullParser xmlpullparser, String s, String s1)
    {
        String s2 = xmlpullparser.getAttributeValue(null, s);
        if(s2 == null)
        {
            xmlpullparser = (new StringBuilder()).append("Missing element under ").append(TAG).append(": ").append(s).append(" at ").append(xmlpullparser.getPositionDescription()).toString();
            Log.w(TAG, xmlpullparser);
            return s1;
        } else
        {
            return s2;
        }
    }

    public void readFromXml(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        mPackageName = getStringFromXml(xmlpullparser, "packageName", null);
        if(mPackageName == null)
            Log.e(TAG, "Package name cannot be null!");
        int i = getIntFromXml(xmlpullparser, "status", -1);
        if(i == -1)
            Log.e(TAG, (new StringBuilder()).append("Unknown status value: ").append(i).toString());
        mMainStatus = i;
        i = xmlpullparser.getDepth();
        do
        {
            int j = xmlpullparser.next();
            if(j == 1 || j == 3 && xmlpullparser.getDepth() <= i)
                break;
            if(j != 3 && j != 4)
            {
                String s = xmlpullparser.getName();
                if(s.equals("domain"))
                {
                    s = getStringFromXml(xmlpullparser, "name", null);
                    if(!TextUtils.isEmpty(s))
                        mDomains.add(s);
                } else
                {
                    Log.w(TAG, (new StringBuilder()).append("Unknown tag parsing IntentFilter: ").append(s).toString());
                }
                XmlUtils.skipCurrentTag(xmlpullparser);
            }
        } while(true);
    }

    public void setDomains(ArraySet arrayset)
    {
        mDomains = arrayset;
    }

    public void setStatus(int i)
    {
        if(i >= 0 && i <= 3)
            mMainStatus = i;
        else
            Log.w(TAG, (new StringBuilder()).append("Trying to set a non supported status: ").append(i).toString());
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mPackageName);
        parcel.writeInt(mMainStatus);
        parcel.writeStringList(new ArrayList(mDomains));
    }

    public void writeToXml(XmlSerializer xmlserializer)
        throws IOException
    {
        xmlserializer.attribute(null, "packageName", mPackageName);
        xmlserializer.attribute(null, "status", String.valueOf(mMainStatus));
        for(Iterator iterator = mDomains.iterator(); iterator.hasNext(); xmlserializer.endTag(null, "domain"))
        {
            String s = (String)iterator.next();
            xmlserializer.startTag(null, "domain");
            xmlserializer.attribute(null, "name", s);
        }

    }

    private static final String ATTR_DOMAIN_NAME = "name";
    private static final String ATTR_PACKAGE_NAME = "packageName";
    private static final String ATTR_STATUS = "status";
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public IntentFilterVerificationInfo createFromParcel(Parcel parcel)
        {
            return new IntentFilterVerificationInfo(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public IntentFilterVerificationInfo[] newArray(int i)
        {
            return new IntentFilterVerificationInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String TAG = android/content/pm/IntentFilterVerificationInfo.getName();
    private static final String TAG_DOMAIN = "domain";
    private ArraySet mDomains;
    private int mMainStatus;
    private String mPackageName;

}
