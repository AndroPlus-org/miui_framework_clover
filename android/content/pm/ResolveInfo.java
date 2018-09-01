// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.content.ComponentName;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.*;
import android.text.TextUtils;
import android.util.Printer;
import android.util.Slog;
import java.text.Collator;
import java.util.Comparator;

// Referenced classes of package android.content.pm:
//            ActivityInfo, ServiceInfo, ProviderInfo, ComponentInfo, 
//            PackageManager, ApplicationInfo, AuxiliaryResolveInfo

public class ResolveInfo
    implements Parcelable
{
    public static class DisplayNameComparator
        implements Comparator
    {

        public final int compare(ResolveInfo resolveinfo, ResolveInfo resolveinfo1)
        {
            if(resolveinfo.targetUserId != -2)
                return 1;
            if(resolveinfo1.targetUserId != -2)
                return -1;
            CharSequence charsequence = resolveinfo.loadLabel(mPM);
            Object obj = charsequence;
            if(charsequence == null)
                obj = resolveinfo.activityInfo.name;
            charsequence = resolveinfo1.loadLabel(mPM);
            resolveinfo = charsequence;
            if(charsequence == null)
                resolveinfo = resolveinfo1.activityInfo.name;
            return mCollator.compare(((CharSequence) (obj)).toString(), resolveinfo.toString());
        }

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((ResolveInfo)obj, (ResolveInfo)obj1);
        }

        private final Collator mCollator = Collator.getInstance();
        private PackageManager mPM;

        public DisplayNameComparator(PackageManager packagemanager)
        {
            mPM = packagemanager;
            mCollator.setStrength(0);
        }
    }


    public ResolveInfo()
    {
        specificIndex = -1;
        targetUserId = -2;
    }

    public ResolveInfo(ResolveInfo resolveinfo)
    {
        specificIndex = -1;
        activityInfo = resolveinfo.activityInfo;
        serviceInfo = resolveinfo.serviceInfo;
        providerInfo = resolveinfo.providerInfo;
        filter = resolveinfo.filter;
        priority = resolveinfo.priority;
        preferredOrder = resolveinfo.preferredOrder;
        match = resolveinfo.match;
        specificIndex = resolveinfo.specificIndex;
        labelRes = resolveinfo.labelRes;
        nonLocalizedLabel = resolveinfo.nonLocalizedLabel;
        icon = resolveinfo.icon;
        resolvePackageName = resolveinfo.resolvePackageName;
        noResourceId = resolveinfo.noResourceId;
        iconResourceId = resolveinfo.iconResourceId;
        system = resolveinfo.system;
        targetUserId = resolveinfo.targetUserId;
        handleAllWebDataURI = resolveinfo.handleAllWebDataURI;
        isInstantAppAvailable = resolveinfo.isInstantAppAvailable;
        instantAppAvailable = isInstantAppAvailable;
    }

    private ResolveInfo(Parcel parcel)
    {
        boolean flag;
        flag = true;
        super();
        specificIndex = -1;
        activityInfo = null;
        serviceInfo = null;
        providerInfo = null;
        parcel.readInt();
        JVM INSTR tableswitch 1 3: default 56
    //                   1 237
    //                   2 256
    //                   3 275;
           goto _L1 _L2 _L3 _L4
_L4:
        break MISSING_BLOCK_LABEL_275;
_L1:
        Slog.w("ResolveInfo", "Missing ComponentInfo!");
_L5:
        if(parcel.readInt() != 0)
            filter = (IntentFilter)IntentFilter.CREATOR.createFromParcel(parcel);
        priority = parcel.readInt();
        preferredOrder = parcel.readInt();
        match = parcel.readInt();
        specificIndex = parcel.readInt();
        labelRes = parcel.readInt();
        nonLocalizedLabel = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        icon = parcel.readInt();
        resolvePackageName = parcel.readString();
        targetUserId = parcel.readInt();
        boolean flag1;
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        system = flag1;
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        noResourceId = flag1;
        iconResourceId = parcel.readInt();
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        handleAllWebDataURI = flag1;
        if(parcel.readInt() != 0)
            flag1 = flag;
        else
            flag1 = false;
        isInstantAppAvailable = flag1;
        instantAppAvailable = flag1;
        return;
_L2:
        activityInfo = (ActivityInfo)ActivityInfo.CREATOR.createFromParcel(parcel);
          goto _L5
_L3:
        serviceInfo = (ServiceInfo)ServiceInfo.CREATOR.createFromParcel(parcel);
          goto _L5
        providerInfo = (ProviderInfo)ProviderInfo.CREATOR.createFromParcel(parcel);
          goto _L5
    }

    ResolveInfo(Parcel parcel, ResolveInfo resolveinfo)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public void dump(Printer printer, String s)
    {
        dump(printer, s, 3);
    }

    public void dump(Printer printer, String s, int i)
    {
        if(filter != null)
        {
            printer.println((new StringBuilder()).append(s).append("Filter:").toString());
            filter.dump(printer, (new StringBuilder()).append(s).append("  ").toString());
        }
        printer.println((new StringBuilder()).append(s).append("priority=").append(priority).append(" preferredOrder=").append(preferredOrder).append(" match=0x").append(Integer.toHexString(match)).append(" specificIndex=").append(specificIndex).append(" isDefault=").append(isDefault).toString());
        if(resolvePackageName != null)
            printer.println((new StringBuilder()).append(s).append("resolvePackageName=").append(resolvePackageName).toString());
        break MISSING_BLOCK_LABEL_181;
        if(labelRes != 0 || nonLocalizedLabel != null || icon != 0)
            printer.println((new StringBuilder()).append(s).append("labelRes=0x").append(Integer.toHexString(labelRes)).append(" nonLocalizedLabel=").append(nonLocalizedLabel).append(" icon=0x").append(Integer.toHexString(icon)).toString());
        if(activityInfo != null)
        {
            printer.println((new StringBuilder()).append(s).append("ActivityInfo:").toString());
            activityInfo.dump(printer, (new StringBuilder()).append(s).append("  ").toString(), i);
        } else
        if(serviceInfo != null)
        {
            printer.println((new StringBuilder()).append(s).append("ServiceInfo:").toString());
            serviceInfo.dump(printer, (new StringBuilder()).append(s).append("  ").toString(), i);
        } else
        if(providerInfo != null)
        {
            printer.println((new StringBuilder()).append(s).append("ProviderInfo:").toString());
            providerInfo.dump(printer, (new StringBuilder()).append(s).append("  ").toString(), i);
        }
        return;
    }

    public ComponentInfo getComponentInfo()
    {
        if(activityInfo != null)
            return activityInfo;
        if(serviceInfo != null)
            return serviceInfo;
        if(providerInfo != null)
            return providerInfo;
        else
            throw new IllegalStateException("Missing ComponentInfo!");
    }

    public final int getIconResource()
    {
        if(noResourceId)
            return 0;
        else
            return getIconResourceInternal();
    }

    final int getIconResourceInternal()
    {
        if(iconResourceId != 0)
            return iconResourceId;
        ComponentInfo componentinfo = getComponentInfo();
        if(componentinfo != null)
            return componentinfo.getIconResource();
        else
            return 0;
    }

    public Drawable loadIcon(PackageManager packagemanager)
    {
        Drawable drawable = null;
        Drawable drawable1 = drawable;
        if(resolvePackageName != null)
        {
            drawable1 = drawable;
            if(iconResourceId != 0)
                drawable1 = packagemanager.getDrawable(resolvePackageName, iconResourceId, null);
        }
        ComponentInfo componentinfo = getComponentInfo();
        drawable = drawable1;
        if(drawable1 == null)
        {
            drawable = drawable1;
            if(iconResourceId != 0)
            {
                ApplicationInfo applicationinfo = componentinfo.applicationInfo;
                drawable = packagemanager.getDrawable(componentinfo.packageName, iconResourceId, applicationinfo);
            }
        }
        if(drawable != null)
            return packagemanager.getUserBadgedIcon(drawable, new UserHandle(UserHandle.myUserId()));
        else
            return componentinfo.loadIcon(packagemanager);
    }

    public CharSequence loadLabel(PackageManager packagemanager)
    {
        if(nonLocalizedLabel != null)
            return nonLocalizedLabel;
        if(resolvePackageName != null && labelRes != 0)
        {
            CharSequence charsequence = packagemanager.getText(resolvePackageName, labelRes, null);
            if(charsequence != null)
                return charsequence.toString().trim();
        }
        Object obj = getComponentInfo();
        Object obj1 = ((ComponentInfo) (obj)).applicationInfo;
        if(labelRes != 0)
        {
            obj1 = packagemanager.getText(((ComponentInfo) (obj)).packageName, labelRes, ((ApplicationInfo) (obj1)));
            if(obj1 != null)
                return ((CharSequence) (obj1)).toString().trim();
        }
        obj = ((ComponentInfo) (obj)).loadLabel(packagemanager);
        packagemanager = ((PackageManager) (obj));
        if(obj != null)
            packagemanager = ((CharSequence) (obj)).toString().trim();
        return packagemanager;
    }

    public int resolveIconResId()
    {
        if(icon != 0)
            return icon;
        ComponentInfo componentinfo = getComponentInfo();
        if(componentinfo.icon != 0)
            return componentinfo.icon;
        else
            return componentinfo.applicationInfo.icon;
    }

    public int resolveLabelResId()
    {
        if(labelRes != 0)
            return labelRes;
        ComponentInfo componentinfo = getComponentInfo();
        if(componentinfo.labelRes != 0)
            return componentinfo.labelRes;
        else
            return componentinfo.applicationInfo.labelRes;
    }

    public String toString()
    {
        ComponentInfo componentinfo = getComponentInfo();
        StringBuilder stringbuilder = new StringBuilder(128);
        stringbuilder.append("ResolveInfo{");
        stringbuilder.append(Integer.toHexString(System.identityHashCode(this)));
        stringbuilder.append(' ');
        ComponentName.appendShortString(stringbuilder, componentinfo.packageName, componentinfo.name);
        if(priority != 0)
        {
            stringbuilder.append(" p=");
            stringbuilder.append(priority);
        }
        if(preferredOrder != 0)
        {
            stringbuilder.append(" o=");
            stringbuilder.append(preferredOrder);
        }
        stringbuilder.append(" m=0x");
        stringbuilder.append(Integer.toHexString(match));
        if(targetUserId != -2)
        {
            stringbuilder.append(" targetUserId=");
            stringbuilder.append(targetUserId);
        }
        stringbuilder.append('}');
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        if(activityInfo != null)
        {
            parcel.writeInt(1);
            activityInfo.writeToParcel(parcel, i);
        } else
        if(serviceInfo != null)
        {
            parcel.writeInt(2);
            serviceInfo.writeToParcel(parcel, i);
        } else
        if(providerInfo != null)
        {
            parcel.writeInt(3);
            providerInfo.writeToParcel(parcel, i);
        } else
        {
            parcel.writeInt(0);
        }
        if(filter != null)
        {
            parcel.writeInt(1);
            filter.writeToParcel(parcel, i);
        } else
        {
            parcel.writeInt(0);
        }
        parcel.writeInt(priority);
        parcel.writeInt(preferredOrder);
        parcel.writeInt(match);
        parcel.writeInt(specificIndex);
        parcel.writeInt(labelRes);
        TextUtils.writeToParcel(nonLocalizedLabel, parcel, i);
        parcel.writeInt(icon);
        parcel.writeString(resolvePackageName);
        parcel.writeInt(targetUserId);
        if(system)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(noResourceId)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeInt(iconResourceId);
        if(handleAllWebDataURI)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(isInstantAppAvailable)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeInt(i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ResolveInfo createFromParcel(Parcel parcel)
        {
            return new ResolveInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ResolveInfo[] newArray(int i)
        {
            return new ResolveInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String TAG = "ResolveInfo";
    public ActivityInfo activityInfo;
    public AuxiliaryResolveInfo auxiliaryInfo;
    public IntentFilter filter;
    public boolean handleAllWebDataURI;
    public int icon;
    public int iconResourceId;
    public boolean instantAppAvailable;
    public boolean isDefault;
    public boolean isInstantAppAvailable;
    public int labelRes;
    public int match;
    public boolean noResourceId;
    public CharSequence nonLocalizedLabel;
    public int preferredOrder;
    public int priority;
    public ProviderInfo providerInfo;
    public String resolvePackageName;
    public ServiceInfo serviceInfo;
    public int specificIndex;
    public boolean system;
    public int targetUserId;

}
