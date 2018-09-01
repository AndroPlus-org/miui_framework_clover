// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.appwidget;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.RemoteViews;

// Referenced classes of package android.appwidget:
//            AppWidgetProviderInfo

public class PendingHostUpdate
    implements Parcelable
{

    private PendingHostUpdate(int i, int j)
    {
        appWidgetId = i;
        type = j;
    }

    private PendingHostUpdate(Parcel parcel)
    {
        appWidgetId = parcel.readInt();
        type = parcel.readInt();
        type;
        JVM INSTR tableswitch 0 2: default 52
    //                   0 53
    //                   1 75
    //                   2 97;
           goto _L1 _L2 _L3 _L4
_L1:
        return;
_L2:
        if(parcel.readInt() != 0)
            views = new RemoteViews(parcel);
        continue; /* Loop/switch isn't completed */
_L3:
        if(parcel.readInt() != 0)
            widgetInfo = new AppWidgetProviderInfo(parcel);
        continue; /* Loop/switch isn't completed */
_L4:
        viewId = parcel.readInt();
        if(true) goto _L1; else goto _L5
_L5:
    }

    PendingHostUpdate(Parcel parcel, PendingHostUpdate pendinghostupdate)
    {
        this(parcel);
    }

    public static PendingHostUpdate providerChanged(int i, AppWidgetProviderInfo appwidgetproviderinfo)
    {
        PendingHostUpdate pendinghostupdate = new PendingHostUpdate(i, 1);
        pendinghostupdate.widgetInfo = appwidgetproviderinfo;
        return pendinghostupdate;
    }

    public static PendingHostUpdate updateAppWidget(int i, RemoteViews remoteviews)
    {
        PendingHostUpdate pendinghostupdate = new PendingHostUpdate(i, 0);
        pendinghostupdate.views = remoteviews;
        return pendinghostupdate;
    }

    public static PendingHostUpdate viewDataChanged(int i, int j)
    {
        PendingHostUpdate pendinghostupdate = new PendingHostUpdate(i, 2);
        pendinghostupdate.viewId = j;
        return pendinghostupdate;
    }

    private void writeNullParcelable(Parcelable parcelable, Parcel parcel, int i)
    {
        if(parcelable != null)
        {
            parcel.writeInt(1);
            parcelable.writeToParcel(parcel, i);
        } else
        {
            parcel.writeInt(0);
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(appWidgetId);
        parcel.writeInt(type);
        type;
        JVM INSTR tableswitch 0 2: default 48
    //                   0 49
    //                   1 62
    //                   2 75;
           goto _L1 _L2 _L3 _L4
_L1:
        return;
_L2:
        writeNullParcelable(views, parcel, i);
        continue; /* Loop/switch isn't completed */
_L3:
        writeNullParcelable(widgetInfo, parcel, i);
        continue; /* Loop/switch isn't completed */
_L4:
        parcel.writeInt(viewId);
        if(true) goto _L1; else goto _L5
_L5:
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public PendingHostUpdate createFromParcel(Parcel parcel)
        {
            return new PendingHostUpdate(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PendingHostUpdate[] newArray(int i)
        {
            return new PendingHostUpdate[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    static final int TYPE_PROVIDER_CHANGED = 1;
    static final int TYPE_VIEWS_UPDATE = 0;
    static final int TYPE_VIEW_DATA_CHANGED = 2;
    final int appWidgetId;
    final int type;
    int viewId;
    RemoteViews views;
    AppWidgetProviderInfo widgetInfo;

}
