// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.*;
import android.content.res.*;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Printer;
import android.util.Xml;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

public final class WallpaperInfo
    implements Parcelable
{

    public WallpaperInfo(Context context, ResolveInfo resolveinfo)
        throws XmlPullParserException, IOException
    {
        ServiceInfo serviceinfo;
        Object obj;
        mService = resolveinfo;
        serviceinfo = resolveinfo.serviceInfo;
        obj = context.getPackageManager();
        resolveinfo = null;
        context = null;
        Object obj1 = serviceinfo.loadXmlMetaData(((PackageManager) (obj)), "android.service.wallpaper");
        if(obj1 != null)
            break MISSING_BLOCK_LABEL_136;
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        obj = JVM INSTR new #37  <Class XmlPullParserException>;
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        ((XmlPullParserException) (obj)).XmlPullParserException("No android.service.wallpaper meta-data");
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        try
        {
            throw obj;
        }
        // Misplaced declaration of an exception variable
        catch(ResolveInfo resolveinfo)
        {
            resolveinfo = context;
        }
        obj1 = JVM INSTR new #37  <Class XmlPullParserException>;
        resolveinfo = context;
        obj = JVM INSTR new #71  <Class StringBuilder>;
        resolveinfo = context;
        ((StringBuilder) (obj)).StringBuilder();
        resolveinfo = context;
        ((XmlPullParserException) (obj1)).XmlPullParserException(((StringBuilder) (obj)).append("Unable to create context for: ").append(serviceinfo.packageName).toString());
        resolveinfo = context;
        throw obj1;
        context;
        if(resolveinfo != null)
            resolveinfo.close();
        throw context;
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        obj = ((PackageManager) (obj)).getResourcesForApplication(serviceinfo.applicationInfo);
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        Object obj2 = Xml.asAttributeSet(((org.xmlpull.v1.XmlPullParser) (obj1)));
_L2:
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        int i = ((XmlResourceParser) (obj1)).next();
        if(i != 1 && i != 2) goto _L2; else goto _L1
_L1:
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        if("wallpaper".equals(((XmlResourceParser) (obj1)).getName()))
            break MISSING_BLOCK_LABEL_247;
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        obj = JVM INSTR new #37  <Class XmlPullParserException>;
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        ((XmlPullParserException) (obj)).XmlPullParserException("Meta-data does not start with wallpaper tag");
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        throw obj;
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        obj = ((Resources) (obj)).obtainAttributes(((android.util.AttributeSet) (obj2)), com.android.internal.R.styleable.Wallpaper);
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        obj2 = ((TypedArray) (obj)).getString(1);
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        i = ((TypedArray) (obj)).getResourceId(2, -1);
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        int j = ((TypedArray) (obj)).getResourceId(3, -1);
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        int k = ((TypedArray) (obj)).getResourceId(0, -1);
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        int l = ((TypedArray) (obj)).getResourceId(4, -1);
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        int i1 = ((TypedArray) (obj)).getResourceId(5, -1);
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        boolean flag = ((TypedArray) (obj)).getBoolean(6, false);
        context = ((Context) (obj1));
        resolveinfo = ((ResolveInfo) (obj1));
        ((TypedArray) (obj)).recycle();
        if(obj1 != null)
            ((XmlResourceParser) (obj1)).close();
        mSettingsActivityName = ((String) (obj2));
        mThumbnailResource = i;
        mAuthorResource = j;
        mDescriptionResource = k;
        mContextUriResource = l;
        mContextDescriptionResource = i1;
        mShowMetadataInPreview = flag;
        return;
    }

    WallpaperInfo(Parcel parcel)
    {
        boolean flag = false;
        super();
        mSettingsActivityName = parcel.readString();
        mThumbnailResource = parcel.readInt();
        mAuthorResource = parcel.readInt();
        mDescriptionResource = parcel.readInt();
        mContextUriResource = parcel.readInt();
        mContextDescriptionResource = parcel.readInt();
        if(parcel.readInt() != 0)
            flag = true;
        mShowMetadataInPreview = flag;
        mService = (ResolveInfo)ResolveInfo.CREATOR.createFromParcel(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public void dump(Printer printer, String s)
    {
        printer.println((new StringBuilder()).append(s).append("Service:").toString());
        mService.dump(printer, (new StringBuilder()).append(s).append("  ").toString());
        printer.println((new StringBuilder()).append(s).append("mSettingsActivityName=").append(mSettingsActivityName).toString());
    }

    public ComponentName getComponent()
    {
        return new ComponentName(mService.serviceInfo.packageName, mService.serviceInfo.name);
    }

    public String getPackageName()
    {
        return mService.serviceInfo.packageName;
    }

    public ServiceInfo getServiceInfo()
    {
        return mService.serviceInfo;
    }

    public String getServiceName()
    {
        return mService.serviceInfo.name;
    }

    public String getSettingsActivity()
    {
        return mSettingsActivityName;
    }

    public boolean getShowMetadataInPreview()
    {
        return mShowMetadataInPreview;
    }

    public CharSequence loadAuthor(PackageManager packagemanager)
        throws android.content.res.Resources.NotFoundException
    {
        if(mAuthorResource <= 0)
            throw new android.content.res.Resources.NotFoundException();
        String s = mService.resolvePackageName;
        android.content.pm.ApplicationInfo applicationinfo = null;
        String s1 = s;
        if(s == null)
        {
            s1 = mService.serviceInfo.packageName;
            applicationinfo = mService.serviceInfo.applicationInfo;
        }
        return packagemanager.getText(s1, mAuthorResource, applicationinfo);
    }

    public CharSequence loadContextDescription(PackageManager packagemanager)
        throws android.content.res.Resources.NotFoundException
    {
        if(mContextDescriptionResource <= 0)
            throw new android.content.res.Resources.NotFoundException();
        String s = mService.resolvePackageName;
        android.content.pm.ApplicationInfo applicationinfo = null;
        String s1 = s;
        if(s == null)
        {
            s1 = mService.serviceInfo.packageName;
            applicationinfo = mService.serviceInfo.applicationInfo;
        }
        return packagemanager.getText(s1, mContextDescriptionResource, applicationinfo).toString();
    }

    public Uri loadContextUri(PackageManager packagemanager)
        throws android.content.res.Resources.NotFoundException
    {
        if(mContextUriResource <= 0)
            throw new android.content.res.Resources.NotFoundException();
        String s = mService.resolvePackageName;
        android.content.pm.ApplicationInfo applicationinfo = null;
        String s1 = s;
        if(s == null)
        {
            s1 = mService.serviceInfo.packageName;
            applicationinfo = mService.serviceInfo.applicationInfo;
        }
        packagemanager = packagemanager.getText(s1, mContextUriResource, applicationinfo).toString();
        if(packagemanager == null)
            return null;
        else
            return Uri.parse(packagemanager);
    }

    public CharSequence loadDescription(PackageManager packagemanager)
        throws android.content.res.Resources.NotFoundException
    {
        String s = mService.resolvePackageName;
        android.content.pm.ApplicationInfo applicationinfo = null;
        String s1 = s;
        if(s == null)
        {
            s1 = mService.serviceInfo.packageName;
            applicationinfo = mService.serviceInfo.applicationInfo;
        }
        if(mService.serviceInfo.descriptionRes != 0)
            return packagemanager.getText(s1, mService.serviceInfo.descriptionRes, applicationinfo);
        if(mDescriptionResource <= 0)
            throw new android.content.res.Resources.NotFoundException();
        else
            return packagemanager.getText(s1, mDescriptionResource, mService.serviceInfo.applicationInfo);
    }

    public Drawable loadIcon(PackageManager packagemanager)
    {
        return mService.loadIcon(packagemanager);
    }

    public CharSequence loadLabel(PackageManager packagemanager)
    {
        return mService.loadLabel(packagemanager);
    }

    public Drawable loadThumbnail(PackageManager packagemanager)
    {
        if(mThumbnailResource < 0)
            return null;
        else
            return packagemanager.getDrawable(mService.serviceInfo.packageName, mThumbnailResource, mService.serviceInfo.applicationInfo);
    }

    public String toString()
    {
        return (new StringBuilder()).append("WallpaperInfo{").append(mService.serviceInfo.name).append(", settings: ").append(mSettingsActivityName).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mSettingsActivityName);
        parcel.writeInt(mThumbnailResource);
        parcel.writeInt(mAuthorResource);
        parcel.writeInt(mDescriptionResource);
        parcel.writeInt(mContextUriResource);
        parcel.writeInt(mContextDescriptionResource);
        int j;
        if(mShowMetadataInPreview)
            j = 1;
        else
            j = 0;
        parcel.writeInt(j);
        mService.writeToParcel(parcel, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public WallpaperInfo createFromParcel(Parcel parcel)
        {
            return new WallpaperInfo(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public WallpaperInfo[] newArray(int i)
        {
            return new WallpaperInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    static final String TAG = "WallpaperInfo";
    final int mAuthorResource;
    final int mContextDescriptionResource;
    final int mContextUriResource;
    final int mDescriptionResource;
    final ResolveInfo mService;
    final String mSettingsActivityName;
    final boolean mShowMetadataInPreview;
    final int mThumbnailResource;

}
