// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.autofill;

import android.app.AppGlobals;
import android.content.ComponentName;
import android.content.pm.*;
import android.content.res.*;
import android.os.RemoteException;
import android.util.Log;
import android.util.Xml;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

public final class AutofillServiceInfo
{

    public AutofillServiceInfo(PackageManager packagemanager, ComponentName componentname, int i)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        this(packagemanager, getServiceInfoOrThrow(componentname, i));
    }

    public AutofillServiceInfo(PackageManager packagemanager, ServiceInfo serviceinfo)
    {
        mServiceInfo = serviceinfo;
        packagemanager = getMetaDataArray(packagemanager, serviceinfo);
        if(packagemanager != null)
        {
            mSettingsActivity = packagemanager.getString(0);
            packagemanager.recycle();
        } else
        {
            mSettingsActivity = null;
        }
    }

    private static TypedArray getMetaDataArray(PackageManager packagemanager, ServiceInfo serviceinfo)
    {
        XmlResourceParser xmlresourceparser;
        if(!"android.permission.BIND_AUTOFILL_SERVICE".equals(serviceinfo.permission) && "android.permission.BIND_AUTOFILL".equals(serviceinfo.permission) ^ true)
        {
            Log.w("AutofillServiceInfo", (new StringBuilder()).append("AutofillService from '").append(serviceinfo.packageName).append("' does not require permission ").append("android.permission.BIND_AUTOFILL_SERVICE").toString());
            throw new SecurityException("Service does not require permission android.permission.BIND_AUTOFILL_SERVICE");
        }
        xmlresourceparser = serviceinfo.loadXmlMetaData(packagemanager, "android.autofill");
        if(xmlresourceparser == null)
            return null;
        int i;
        do
            i = xmlresourceparser.next();
        while(i != 1 && i != 2);
        if("autofill-service".equals(xmlresourceparser.getName()))
            break MISSING_BLOCK_LABEL_153;
        Log.e("AutofillServiceInfo", "Meta-data does not start with autofill-service tag");
        xmlresourceparser.close();
        return null;
        packagemanager;
        Log.e("AutofillServiceInfo", "Error parsing auto fill service meta-data", packagemanager);
        xmlresourceparser.close();
        return null;
        android.util.AttributeSet attributeset = Xml.asAttributeSet(xmlresourceparser);
        packagemanager = packagemanager.getResourcesForApplication(serviceinfo.applicationInfo);
        packagemanager = packagemanager.obtainAttributes(attributeset, com.android.internal.R.styleable.AutofillService);
        xmlresourceparser.close();
        return packagemanager;
        packagemanager;
        Log.e("AutofillServiceInfo", "Error getting application resources", packagemanager);
        xmlresourceparser.close();
        return null;
        packagemanager;
        xmlresourceparser.close();
        throw packagemanager;
    }

    private static ServiceInfo getServiceInfoOrThrow(ComponentName componentname, int i)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        ServiceInfo serviceinfo;
        try
        {
            serviceinfo = AppGlobals.getPackageManager().getServiceInfo(componentname, 128, i);
        }
        catch(RemoteException remoteexception)
        {
            throw new android.content.pm.PackageManager.NameNotFoundException(componentname.toString());
        }
label0:
        {
            if(serviceinfo != null)
                return serviceinfo;
            break label0;
        }
    }

    public ServiceInfo getServiceInfo()
    {
        return mServiceInfo;
    }

    public String getSettingsActivity()
    {
        return mSettingsActivity;
    }

    public String toString()
    {
        String s;
        if(mServiceInfo == null)
            s = "null";
        else
            s = mServiceInfo.toString();
        return s;
    }

    private static final String TAG = "AutofillServiceInfo";
    private final ServiceInfo mServiceInfo;
    private final String mSettingsActivity;
}
