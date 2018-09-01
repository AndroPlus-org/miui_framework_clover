// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.app.MiuiThemeHelper;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.text.*;
import android.util.Printer;
import java.text.Collator;
import java.util.Comparator;

// Referenced classes of package android.content.pm:
//            PackageManager, ApplicationInfo

public class PackageItemInfo
{
    public static class DisplayNameComparator
        implements Comparator
    {

        public final int compare(PackageItemInfo packageiteminfo, PackageItemInfo packageiteminfo1)
        {
            CharSequence charsequence = packageiteminfo.loadLabel(mPM);
            Object obj = charsequence;
            if(charsequence == null)
                obj = packageiteminfo.name;
            charsequence = packageiteminfo1.loadLabel(mPM);
            packageiteminfo = charsequence;
            if(charsequence == null)
                packageiteminfo = packageiteminfo1.name;
            return sCollator.compare(((CharSequence) (obj)).toString(), packageiteminfo.toString());
        }

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((PackageItemInfo)obj, (PackageItemInfo)obj1);
        }

        private PackageManager mPM;
        private final Collator sCollator = Collator.getInstance();

        public DisplayNameComparator(PackageManager packagemanager)
        {
            mPM = packagemanager;
        }
    }


    public PackageItemInfo()
    {
        showUserIcon = -10000;
    }

    public PackageItemInfo(PackageItemInfo packageiteminfo)
    {
        name = packageiteminfo.name;
        if(name != null)
            name = name.trim();
        packageName = packageiteminfo.packageName;
        labelRes = packageiteminfo.labelRes;
        nonLocalizedLabel = packageiteminfo.nonLocalizedLabel;
        if(nonLocalizedLabel != null)
            nonLocalizedLabel = nonLocalizedLabel.toString().trim();
        icon = packageiteminfo.icon;
        banner = packageiteminfo.banner;
        logo = packageiteminfo.logo;
        metaData = packageiteminfo.metaData;
        showUserIcon = packageiteminfo.showUserIcon;
    }

    protected PackageItemInfo(Parcel parcel)
    {
        name = parcel.readString();
        packageName = parcel.readString();
        labelRes = parcel.readInt();
        nonLocalizedLabel = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        icon = parcel.readInt();
        logo = parcel.readInt();
        metaData = parcel.readBundle();
        banner = parcel.readInt();
        showUserIcon = parcel.readInt();
    }

    protected void dumpBack(Printer printer, String s)
    {
    }

    protected void dumpFront(Printer printer, String s)
    {
        if(name != null)
            printer.println((new StringBuilder()).append(s).append("name=").append(name).toString());
        printer.println((new StringBuilder()).append(s).append("packageName=").append(packageName).toString());
        break MISSING_BLOCK_LABEL_71;
        if(labelRes != 0 || nonLocalizedLabel != null || icon != 0 || banner != 0)
            printer.println((new StringBuilder()).append(s).append("labelRes=0x").append(Integer.toHexString(labelRes)).append(" nonLocalizedLabel=").append(nonLocalizedLabel).append(" icon=0x").append(Integer.toHexString(icon)).append(" banner=0x").append(Integer.toHexString(banner)).toString());
        return;
    }

    protected ApplicationInfo getApplicationInfo()
    {
        return null;
    }

    public Drawable loadBanner(PackageManager packagemanager)
    {
        if(banner != 0)
        {
            Drawable drawable = packagemanager.getDrawable(packageName, banner, getApplicationInfo());
            if(drawable != null)
                return drawable;
        }
        return loadDefaultBanner(packagemanager);
    }

    protected Drawable loadDefaultBanner(PackageManager packagemanager)
    {
        return null;
    }

    public Drawable loadDefaultIcon(PackageManager packagemanager)
    {
        return packagemanager.getDefaultActivityIcon();
    }

    protected Drawable loadDefaultLogo(PackageManager packagemanager)
    {
        return null;
    }

    public Drawable loadIcon(PackageManager packagemanager)
    {
        Drawable drawable = MiuiThemeHelper.getDrawable(packagemanager, packageName, name, icon, getApplicationInfo());
        Drawable drawable1 = drawable;
        if(drawable == null)
        {
            drawable1 = drawable;
            if(name != null)
                drawable1 = loadDefaultIcon(packagemanager);
        }
        if(drawable1 != null)
            return drawable1;
        else
            return packagemanager.loadItemIcon(this, getApplicationInfo());
    }

    public CharSequence loadLabel(PackageManager packagemanager)
    {
        if(nonLocalizedLabel != null)
            return nonLocalizedLabel;
        if(labelRes != 0)
        {
            packagemanager = packagemanager.getText(packageName, labelRes, getApplicationInfo());
            if(packagemanager != null)
                return packagemanager.toString().trim();
        }
        if(name != null)
            return name;
        else
            return packageName;
    }

    public Drawable loadLogo(PackageManager packagemanager)
    {
        if(logo != 0)
        {
            Drawable drawable = packagemanager.getDrawable(packageName, logo, getApplicationInfo());
            if(drawable != null)
                return drawable;
        }
        return loadDefaultLogo(packagemanager);
    }

    public CharSequence loadSafeLabel(PackageManager packagemanager)
    {
        int i;
        int j;
        packagemanager = Html.fromHtml(loadLabel(packagemanager).toString()).toString();
        i = packagemanager.length();
        j = 0;
_L7:
        Object obj = packagemanager;
        if(j >= i) goto _L2; else goto _L1
_L1:
        int k;
        int l;
        k = packagemanager.codePointAt(j);
        l = Character.getType(k);
          goto _L3
_L5:
        obj = packagemanager.substring(0, j);
_L2:
        packagemanager = ((String) (obj)).trim();
        if(packagemanager.isEmpty())
        {
            return packageName;
        } else
        {
            TextPaint textpaint = new TextPaint();
            textpaint.setTextSize(42F);
            return TextUtils.ellipsize(packagemanager, textpaint, 500F, android.text.TextUtils.TruncateAt.END);
        }
_L3:
        if(l == 13 || l == 15 || l == 14) goto _L5; else goto _L4
_L4:
        obj = packagemanager;
        if(l == 12)
            obj = (new StringBuilder()).append(packagemanager.substring(0, j)).append(" ").append(packagemanager.substring(Character.charCount(k) + j)).toString();
        j += Character.charCount(k);
        packagemanager = ((PackageManager) (obj));
        if(true) goto _L7; else goto _L6
_L6:
    }

    public Drawable loadUnbadgedIcon(PackageManager packagemanager)
    {
        return packagemanager.loadUnbadgedItemIcon(this, getApplicationInfo());
    }

    public XmlResourceParser loadXmlMetaData(PackageManager packagemanager, String s)
    {
        if(metaData != null)
        {
            int i = metaData.getInt(s);
            if(i != 0)
                return packagemanager.getXml(packageName, i, getApplicationInfo());
        }
        return null;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(name);
        parcel.writeString(packageName);
        parcel.writeInt(labelRes);
        TextUtils.writeToParcel(nonLocalizedLabel, parcel, i);
        parcel.writeInt(icon);
        parcel.writeInt(logo);
        parcel.writeBundle(metaData);
        parcel.writeInt(banner);
        parcel.writeInt(showUserIcon);
    }

    public static final int DUMP_FLAG_ALL = 3;
    public static final int DUMP_FLAG_APPLICATION = 2;
    public static final int DUMP_FLAG_DETAILS = 1;
    private static final float MAX_LABEL_SIZE_PX = 500F;
    public int banner;
    public int icon;
    public int labelRes;
    public int logo;
    public Bundle metaData;
    public String name;
    public CharSequence nonLocalizedLabel;
    public String packageName;
    public int showUserIcon;
}
