// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.appwidget;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.*;
import android.content.res.ResourceId;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.*;
import android.util.DisplayMetrics;
import android.util.TypedValue;

public class AppWidgetProviderInfo
    implements Parcelable
{

    public AppWidgetProviderInfo()
    {
    }

    public AppWidgetProviderInfo(Parcel parcel)
    {
        if(parcel.readInt() != 0)
            provider = new ComponentName(parcel);
        minWidth = parcel.readInt();
        minHeight = parcel.readInt();
        minResizeWidth = parcel.readInt();
        minResizeHeight = parcel.readInt();
        updatePeriodMillis = parcel.readInt();
        initialLayout = parcel.readInt();
        initialKeyguardLayout = parcel.readInt();
        if(parcel.readInt() != 0)
            configure = new ComponentName(parcel);
        label = parcel.readString();
        icon = parcel.readInt();
        previewImage = parcel.readInt();
        autoAdvanceViewId = parcel.readInt();
        resizeMode = parcel.readInt();
        widgetCategory = parcel.readInt();
        providerInfo = (ActivityInfo)parcel.readParcelable(null);
    }

    private Drawable loadDrawable(Context context, int i, int j, boolean flag)
    {
        Drawable drawable = null;
        Object obj;
        obj = context.getPackageManager().getResourcesForApplication(providerInfo.applicationInfo);
        if(!ResourceId.isValid(j))
            break MISSING_BLOCK_LABEL_52;
        int k;
        k = i;
        if(i < 0)
            k = 0;
        obj = ((Resources) (obj)).getDrawableForDensity(j, k, null);
        return ((Drawable) (obj));
        Object obj1;
        obj1;
        if(flag)
            drawable = providerInfo.loadIcon(context.getPackageManager());
        return drawable;
    }

    public AppWidgetProviderInfo clone()
    {
        Object obj = null;
        AppWidgetProviderInfo appwidgetproviderinfo = new AppWidgetProviderInfo();
        Object obj1;
        if(provider == null)
            obj1 = null;
        else
            obj1 = provider.clone();
        appwidgetproviderinfo.provider = ((ComponentName) (obj1));
        appwidgetproviderinfo.minWidth = minWidth;
        appwidgetproviderinfo.minHeight = minHeight;
        appwidgetproviderinfo.minResizeWidth = minResizeHeight;
        appwidgetproviderinfo.minResizeHeight = minResizeHeight;
        appwidgetproviderinfo.updatePeriodMillis = updatePeriodMillis;
        appwidgetproviderinfo.initialLayout = initialLayout;
        appwidgetproviderinfo.initialKeyguardLayout = initialKeyguardLayout;
        if(configure == null)
            obj1 = null;
        else
            obj1 = configure.clone();
        appwidgetproviderinfo.configure = ((ComponentName) (obj1));
        if(label == null)
            obj1 = obj;
        else
            obj1 = label.substring(0);
        appwidgetproviderinfo.label = ((String) (obj1));
        appwidgetproviderinfo.icon = icon;
        appwidgetproviderinfo.previewImage = previewImage;
        appwidgetproviderinfo.autoAdvanceViewId = autoAdvanceViewId;
        appwidgetproviderinfo.resizeMode = resizeMode;
        appwidgetproviderinfo.widgetCategory = widgetCategory;
        appwidgetproviderinfo.providerInfo = providerInfo;
        return appwidgetproviderinfo;
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public int describeContents()
    {
        return 0;
    }

    public final UserHandle getProfile()
    {
        return new UserHandle(UserHandle.getUserId(providerInfo.applicationInfo.uid));
    }

    public final Drawable loadIcon(Context context, int i)
    {
        return loadDrawable(context, i, providerInfo.getIconResource(), true);
    }

    public final String loadLabel(PackageManager packagemanager)
    {
        packagemanager = providerInfo.loadLabel(packagemanager);
        if(packagemanager != null)
            return packagemanager.toString().trim();
        else
            return null;
    }

    public final Drawable loadPreviewImage(Context context, int i)
    {
        return loadDrawable(context, i, previewImage, false);
    }

    public String toString()
    {
        return (new StringBuilder()).append("AppWidgetProviderInfo(").append(getProfile()).append('/').append(provider).append(')').toString();
    }

    public void updateDimensions(DisplayMetrics displaymetrics)
    {
        minWidth = TypedValue.complexToDimensionPixelSize(minWidth, displaymetrics);
        minHeight = TypedValue.complexToDimensionPixelSize(minHeight, displaymetrics);
        minResizeWidth = TypedValue.complexToDimensionPixelSize(minResizeWidth, displaymetrics);
        minResizeHeight = TypedValue.complexToDimensionPixelSize(minResizeHeight, displaymetrics);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        if(provider != null)
        {
            parcel.writeInt(1);
            provider.writeToParcel(parcel, i);
        } else
        {
            parcel.writeInt(0);
        }
        parcel.writeInt(minWidth);
        parcel.writeInt(minHeight);
        parcel.writeInt(minResizeWidth);
        parcel.writeInt(minResizeHeight);
        parcel.writeInt(updatePeriodMillis);
        parcel.writeInt(initialLayout);
        parcel.writeInt(initialKeyguardLayout);
        if(configure != null)
        {
            parcel.writeInt(1);
            configure.writeToParcel(parcel, i);
        } else
        {
            parcel.writeInt(0);
        }
        parcel.writeString(label);
        parcel.writeInt(icon);
        parcel.writeInt(previewImage);
        parcel.writeInt(autoAdvanceViewId);
        parcel.writeInt(resizeMode);
        parcel.writeInt(widgetCategory);
        parcel.writeParcelable(providerInfo, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public AppWidgetProviderInfo createFromParcel(Parcel parcel)
        {
            return new AppWidgetProviderInfo(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public AppWidgetProviderInfo[] newArray(int i)
        {
            return new AppWidgetProviderInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int RESIZE_BOTH = 3;
    public static final int RESIZE_HORIZONTAL = 1;
    public static final int RESIZE_NONE = 0;
    public static final int RESIZE_VERTICAL = 2;
    public static final int WIDGET_CATEGORY_HOME_SCREEN = 1;
    public static final int WIDGET_CATEGORY_KEYGUARD = 2;
    public static final int WIDGET_CATEGORY_SEARCHBOX = 4;
    public int autoAdvanceViewId;
    public ComponentName configure;
    public int icon;
    public int initialKeyguardLayout;
    public int initialLayout;
    public String label;
    public int minHeight;
    public int minResizeHeight;
    public int minResizeWidth;
    public int minWidth;
    public int previewImage;
    public ComponentName provider;
    public ActivityInfo providerInfo;
    public int resizeMode;
    public int updatePeriodMillis;
    public int widgetCategory;

}
