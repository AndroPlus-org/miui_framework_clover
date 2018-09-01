// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.content.ComponentName;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.util.Printer;

// Referenced classes of package android.content.pm:
//            PackageItemInfo, ApplicationInfo, PackageManager

public class ComponentInfo extends PackageItemInfo
{

    public ComponentInfo()
    {
        enabled = true;
        exported = false;
        directBootAware = false;
        encryptionAware = false;
    }

    public ComponentInfo(ComponentInfo componentinfo)
    {
        super(componentinfo);
        enabled = true;
        exported = false;
        directBootAware = false;
        encryptionAware = false;
        applicationInfo = componentinfo.applicationInfo;
        processName = componentinfo.processName;
        splitName = componentinfo.splitName;
        descriptionRes = componentinfo.descriptionRes;
        enabled = componentinfo.enabled;
        exported = componentinfo.exported;
        boolean flag = componentinfo.directBootAware;
        directBootAware = flag;
        encryptionAware = flag;
    }

    protected ComponentInfo(Parcel parcel)
    {
        boolean flag = true;
        super(parcel);
        enabled = true;
        exported = false;
        directBootAware = false;
        encryptionAware = false;
        boolean flag1;
        boolean flag2;
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        if(flag1)
            applicationInfo = (ApplicationInfo)ApplicationInfo.CREATOR.createFromParcel(parcel);
        processName = parcel.readString();
        splitName = parcel.readString();
        descriptionRes = parcel.readInt();
        if(parcel.readInt() != 0)
            flag2 = true;
        else
            flag2 = false;
        enabled = flag2;
        if(parcel.readInt() != 0)
            flag2 = true;
        else
            flag2 = false;
        exported = flag2;
        if(parcel.readInt() != 0)
            flag2 = flag;
        else
            flag2 = false;
        directBootAware = flag2;
        encryptionAware = flag2;
    }

    protected void dumpBack(Printer printer, String s)
    {
        dumpBack(printer, s, 3);
    }

    void dumpBack(Printer printer, String s, int i)
    {
        if((i & 2) != 0)
            if(applicationInfo != null)
            {
                printer.println((new StringBuilder()).append(s).append("ApplicationInfo:").toString());
                applicationInfo.dump(printer, (new StringBuilder()).append(s).append("  ").toString(), i);
            } else
            {
                printer.println((new StringBuilder()).append(s).append("ApplicationInfo: null").toString());
            }
        super.dumpBack(printer, s);
    }

    protected void dumpFront(Printer printer, String s)
    {
        super.dumpFront(printer, s);
        if(processName != null && packageName.equals(processName) ^ true)
            printer.println((new StringBuilder()).append(s).append("processName=").append(processName).toString());
        if(splitName != null)
            printer.println((new StringBuilder()).append(s).append("splitName=").append(splitName).toString());
        printer.println((new StringBuilder()).append(s).append("enabled=").append(enabled).append(" exported=").append(exported).append(" directBootAware=").append(directBootAware).toString());
        if(descriptionRes != 0)
            printer.println((new StringBuilder()).append(s).append("description=").append(descriptionRes).toString());
    }

    protected ApplicationInfo getApplicationInfo()
    {
        return applicationInfo;
    }

    public final int getBannerResource()
    {
        int i;
        if(banner != 0)
            i = banner;
        else
            i = applicationInfo.banner;
        return i;
    }

    public ComponentName getComponentName()
    {
        return new ComponentName(packageName, name);
    }

    public final int getIconResource()
    {
        int i;
        if(icon != 0)
            i = icon;
        else
            i = applicationInfo.icon;
        return i;
    }

    public final int getLogoResource()
    {
        int i;
        if(logo != 0)
            i = logo;
        else
            i = applicationInfo.logo;
        return i;
    }

    public boolean isEnabled()
    {
        boolean flag;
        if(enabled)
            flag = applicationInfo.enabled;
        else
            flag = false;
        return flag;
    }

    protected Drawable loadDefaultBanner(PackageManager packagemanager)
    {
        return applicationInfo.loadBanner(packagemanager);
    }

    public Drawable loadDefaultIcon(PackageManager packagemanager)
    {
        return applicationInfo.loadIcon(packagemanager);
    }

    protected Drawable loadDefaultLogo(PackageManager packagemanager)
    {
        return applicationInfo.loadLogo(packagemanager);
    }

    public CharSequence loadLabel(PackageManager packagemanager)
    {
        if(nonLocalizedLabel != null)
            return nonLocalizedLabel;
        ApplicationInfo applicationinfo = applicationInfo;
        if(labelRes != 0)
        {
            CharSequence charsequence = packagemanager.getText(packageName, labelRes, applicationinfo);
            if(charsequence != null)
                return charsequence;
        }
        if(applicationinfo.nonLocalizedLabel != null)
            return applicationinfo.nonLocalizedLabel;
        if(applicationinfo.labelRes != 0)
        {
            packagemanager = packagemanager.getText(packageName, applicationinfo.labelRes, applicationinfo);
            if(packagemanager != null)
                return packagemanager;
        }
        return name;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        super.writeToParcel(parcel, i);
        if((i & 2) != 0)
        {
            parcel.writeInt(0);
        } else
        {
            parcel.writeInt(1);
            applicationInfo.writeToParcel(parcel, i);
        }
        parcel.writeString(processName);
        parcel.writeString(splitName);
        parcel.writeInt(descriptionRes);
        if(enabled)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(exported)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(directBootAware)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeInt(i);
    }

    public ApplicationInfo applicationInfo;
    public int descriptionRes;
    public boolean directBootAware;
    public boolean enabled;
    public boolean encryptionAware;
    public boolean exported;
    public String processName;
    public String splitName;
}
