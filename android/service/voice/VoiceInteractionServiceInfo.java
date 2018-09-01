// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.voice;

import android.app.AppGlobals;
import android.content.ComponentName;
import android.content.pm.*;
import android.content.res.*;
import android.os.RemoteException;
import android.util.Log;
import android.util.Xml;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

public class VoiceInteractionServiceInfo
{

    public VoiceInteractionServiceInfo(PackageManager packagemanager, ComponentName componentname)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        this(packagemanager, packagemanager.getServiceInfo(componentname, 128));
    }

    public VoiceInteractionServiceInfo(PackageManager packagemanager, ComponentName componentname, int i)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        this(packagemanager, getServiceInfoOrThrow(componentname, i));
    }

    public VoiceInteractionServiceInfo(PackageManager packagemanager, ServiceInfo serviceinfo)
    {
        XmlResourceParser xmlresourceparser;
        XmlResourceParser xmlresourceparser1;
        XmlResourceParser xmlresourceparser2;
        XmlResourceParser xmlresourceparser3;
        if(serviceinfo == null)
        {
            mParseError = "Service not available";
            return;
        }
        if(!"android.permission.BIND_VOICE_INTERACTION".equals(serviceinfo.permission))
        {
            mParseError = "Service does not require permission android.permission.BIND_VOICE_INTERACTION";
            return;
        }
        xmlresourceparser = null;
        xmlresourceparser1 = null;
        xmlresourceparser2 = null;
        xmlresourceparser3 = null;
        XmlResourceParser xmlresourceparser4 = serviceinfo.loadXmlMetaData(packagemanager, "android.voice_interaction");
        if(xmlresourceparser4 != null)
            break MISSING_BLOCK_LABEL_145;
        xmlresourceparser3 = xmlresourceparser4;
        xmlresourceparser = xmlresourceparser4;
        xmlresourceparser1 = xmlresourceparser4;
        xmlresourceparser2 = xmlresourceparser4;
        packagemanager = JVM INSTR new #71  <Class StringBuilder>;
        xmlresourceparser3 = xmlresourceparser4;
        xmlresourceparser = xmlresourceparser4;
        xmlresourceparser1 = xmlresourceparser4;
        xmlresourceparser2 = xmlresourceparser4;
        packagemanager.StringBuilder();
        xmlresourceparser3 = xmlresourceparser4;
        xmlresourceparser = xmlresourceparser4;
        xmlresourceparser1 = xmlresourceparser4;
        xmlresourceparser2 = xmlresourceparser4;
        mParseError = packagemanager.append("No android.voice_interaction meta-data for ").append(serviceinfo.packageName).toString();
        if(xmlresourceparser4 != null)
            xmlresourceparser4.close();
        return;
        xmlresourceparser3 = xmlresourceparser4;
        xmlresourceparser = xmlresourceparser4;
        xmlresourceparser1 = xmlresourceparser4;
        xmlresourceparser2 = xmlresourceparser4;
        packagemanager = packagemanager.getResourcesForApplication(serviceinfo.applicationInfo);
        xmlresourceparser3 = xmlresourceparser4;
        xmlresourceparser = xmlresourceparser4;
        xmlresourceparser1 = xmlresourceparser4;
        xmlresourceparser2 = xmlresourceparser4;
        android.util.AttributeSet attributeset = Xml.asAttributeSet(xmlresourceparser4);
_L2:
        xmlresourceparser3 = xmlresourceparser4;
        xmlresourceparser = xmlresourceparser4;
        xmlresourceparser1 = xmlresourceparser4;
        xmlresourceparser2 = xmlresourceparser4;
        int i = xmlresourceparser4.next();
        if(i != 1 && i != 2) goto _L2; else goto _L1
_L1:
        xmlresourceparser3 = xmlresourceparser4;
        xmlresourceparser = xmlresourceparser4;
        xmlresourceparser1 = xmlresourceparser4;
        xmlresourceparser2 = xmlresourceparser4;
        if("voice-interaction-service".equals(xmlresourceparser4.getName()))
            break MISSING_BLOCK_LABEL_291;
        xmlresourceparser3 = xmlresourceparser4;
        xmlresourceparser = xmlresourceparser4;
        xmlresourceparser1 = xmlresourceparser4;
        xmlresourceparser2 = xmlresourceparser4;
        mParseError = "Meta-data does not start with voice-interaction-service tag";
        if(xmlresourceparser4 != null)
            xmlresourceparser4.close();
        return;
        xmlresourceparser3 = xmlresourceparser4;
        xmlresourceparser = xmlresourceparser4;
        xmlresourceparser1 = xmlresourceparser4;
        xmlresourceparser2 = xmlresourceparser4;
        packagemanager = packagemanager.obtainAttributes(attributeset, com.android.internal.R.styleable.VoiceInteractionService);
        xmlresourceparser3 = xmlresourceparser4;
        xmlresourceparser = xmlresourceparser4;
        xmlresourceparser1 = xmlresourceparser4;
        xmlresourceparser2 = xmlresourceparser4;
        mSessionService = packagemanager.getString(1);
        xmlresourceparser3 = xmlresourceparser4;
        xmlresourceparser = xmlresourceparser4;
        xmlresourceparser1 = xmlresourceparser4;
        xmlresourceparser2 = xmlresourceparser4;
        mRecognitionService = packagemanager.getString(2);
        xmlresourceparser3 = xmlresourceparser4;
        xmlresourceparser = xmlresourceparser4;
        xmlresourceparser1 = xmlresourceparser4;
        xmlresourceparser2 = xmlresourceparser4;
        mSettingsActivity = packagemanager.getString(0);
        xmlresourceparser3 = xmlresourceparser4;
        xmlresourceparser = xmlresourceparser4;
        xmlresourceparser1 = xmlresourceparser4;
        xmlresourceparser2 = xmlresourceparser4;
        mSupportsAssist = packagemanager.getBoolean(3, false);
        xmlresourceparser3 = xmlresourceparser4;
        xmlresourceparser = xmlresourceparser4;
        xmlresourceparser1 = xmlresourceparser4;
        xmlresourceparser2 = xmlresourceparser4;
        mSupportsLaunchFromKeyguard = packagemanager.getBoolean(4, false);
        xmlresourceparser3 = xmlresourceparser4;
        xmlresourceparser = xmlresourceparser4;
        xmlresourceparser1 = xmlresourceparser4;
        xmlresourceparser2 = xmlresourceparser4;
        mSupportsLocalInteraction = packagemanager.getBoolean(5, false);
        xmlresourceparser3 = xmlresourceparser4;
        xmlresourceparser = xmlresourceparser4;
        xmlresourceparser1 = xmlresourceparser4;
        xmlresourceparser2 = xmlresourceparser4;
        packagemanager.recycle();
        xmlresourceparser3 = xmlresourceparser4;
        xmlresourceparser = xmlresourceparser4;
        xmlresourceparser1 = xmlresourceparser4;
        xmlresourceparser2 = xmlresourceparser4;
        if(mSessionService != null)
            break MISSING_BLOCK_LABEL_538;
        xmlresourceparser3 = xmlresourceparser4;
        xmlresourceparser = xmlresourceparser4;
        xmlresourceparser1 = xmlresourceparser4;
        xmlresourceparser2 = xmlresourceparser4;
        mParseError = "No sessionService specified";
        if(xmlresourceparser4 != null)
            xmlresourceparser4.close();
        return;
        xmlresourceparser3 = xmlresourceparser4;
        xmlresourceparser = xmlresourceparser4;
        xmlresourceparser1 = xmlresourceparser4;
        xmlresourceparser2 = xmlresourceparser4;
        if(mRecognitionService != null)
            break MISSING_BLOCK_LABEL_594;
        xmlresourceparser3 = xmlresourceparser4;
        xmlresourceparser = xmlresourceparser4;
        xmlresourceparser1 = xmlresourceparser4;
        xmlresourceparser2 = xmlresourceparser4;
        mParseError = "No recognitionService specified";
        if(xmlresourceparser4 != null)
            xmlresourceparser4.close();
        return;
        if(xmlresourceparser4 != null)
            xmlresourceparser4.close();
        mServiceInfo = serviceinfo;
        return;
        serviceinfo;
        xmlresourceparser2 = xmlresourceparser3;
        packagemanager = JVM INSTR new #71  <Class StringBuilder>;
        xmlresourceparser2 = xmlresourceparser3;
        packagemanager.StringBuilder();
        xmlresourceparser2 = xmlresourceparser3;
        mParseError = packagemanager.append("Error parsing voice interation service meta-data: ").append(serviceinfo).toString();
        xmlresourceparser2 = xmlresourceparser3;
        Log.w("VoiceInteractionServiceInfo", "error parsing voice interaction service meta-data", serviceinfo);
        if(xmlresourceparser3 != null)
            xmlresourceparser3.close();
        return;
        serviceinfo;
        xmlresourceparser2 = xmlresourceparser;
        packagemanager = JVM INSTR new #71  <Class StringBuilder>;
        xmlresourceparser2 = xmlresourceparser;
        packagemanager.StringBuilder();
        xmlresourceparser2 = xmlresourceparser;
        mParseError = packagemanager.append("Error parsing voice interation service meta-data: ").append(serviceinfo).toString();
        xmlresourceparser2 = xmlresourceparser;
        Log.w("VoiceInteractionServiceInfo", "error parsing voice interaction service meta-data", serviceinfo);
        if(xmlresourceparser != null)
            xmlresourceparser.close();
        return;
        packagemanager;
        xmlresourceparser2 = xmlresourceparser1;
        serviceinfo = JVM INSTR new #71  <Class StringBuilder>;
        xmlresourceparser2 = xmlresourceparser1;
        serviceinfo.StringBuilder();
        xmlresourceparser2 = xmlresourceparser1;
        mParseError = serviceinfo.append("Error parsing voice interation service meta-data: ").append(packagemanager).toString();
        xmlresourceparser2 = xmlresourceparser1;
        Log.w("VoiceInteractionServiceInfo", "error parsing voice interaction service meta-data", packagemanager);
        if(xmlresourceparser1 != null)
            xmlresourceparser1.close();
        return;
        packagemanager;
        if(xmlresourceparser2 != null)
            xmlresourceparser2.close();
        throw packagemanager;
    }

    static ServiceInfo getServiceInfoOrThrow(ComponentName componentname, int i)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        ServiceInfo serviceinfo;
        try
        {
            serviceinfo = AppGlobals.getPackageManager().getServiceInfo(componentname, 0x100c0080, i);
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

    public String getParseError()
    {
        return mParseError;
    }

    public String getRecognitionService()
    {
        return mRecognitionService;
    }

    public ServiceInfo getServiceInfo()
    {
        return mServiceInfo;
    }

    public String getSessionService()
    {
        return mSessionService;
    }

    public String getSettingsActivity()
    {
        return mSettingsActivity;
    }

    public boolean getSupportsAssist()
    {
        return mSupportsAssist;
    }

    public boolean getSupportsLaunchFromKeyguard()
    {
        return mSupportsLaunchFromKeyguard;
    }

    public boolean getSupportsLocalInteraction()
    {
        return mSupportsLocalInteraction;
    }

    static final String TAG = "VoiceInteractionServiceInfo";
    private String mParseError;
    private String mRecognitionService;
    private ServiceInfo mServiceInfo;
    private String mSessionService;
    private String mSettingsActivity;
    private boolean mSupportsAssist;
    private boolean mSupportsLaunchFromKeyguard;
    private boolean mSupportsLocalInteraction;
}
