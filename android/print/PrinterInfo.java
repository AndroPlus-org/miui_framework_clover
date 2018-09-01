// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.print;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.*;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.android.internal.util.Preconditions;

// Referenced classes of package android.print:
//            PrinterId, PrinterCapabilitiesInfo, PrintManager

public final class PrinterInfo
    implements Parcelable
{
    public static final class Builder
    {

        public PrinterInfo build()
        {
            return new PrinterInfo(mPrinterId, mName, mStatus, mIconResourceId, mHasCustomPrinterIcon, mDescription, mInfoIntent, mCapabilities, mCustomPrinterIconGen, null);
        }

        public Builder incCustomPrinterIconGen()
        {
            mCustomPrinterIconGen = mCustomPrinterIconGen + 1;
            return this;
        }

        public Builder setCapabilities(PrinterCapabilitiesInfo printercapabilitiesinfo)
        {
            mCapabilities = printercapabilitiesinfo;
            return this;
        }

        public Builder setDescription(String s)
        {
            mDescription = s;
            return this;
        }

        public Builder setHasCustomPrinterIcon(boolean flag)
        {
            mHasCustomPrinterIcon = flag;
            return this;
        }

        public Builder setIconResourceId(int i)
        {
            mIconResourceId = Preconditions.checkArgumentNonnegative(i, "iconResourceId can't be negative");
            return this;
        }

        public Builder setInfoIntent(PendingIntent pendingintent)
        {
            mInfoIntent = pendingintent;
            return this;
        }

        public Builder setName(String s)
        {
            mName = PrinterInfo._2D_wrap2(s);
            return this;
        }

        public Builder setStatus(int i)
        {
            mStatus = PrinterInfo._2D_wrap1(i);
            return this;
        }

        private PrinterCapabilitiesInfo mCapabilities;
        private int mCustomPrinterIconGen;
        private String mDescription;
        private boolean mHasCustomPrinterIcon;
        private int mIconResourceId;
        private PendingIntent mInfoIntent;
        private String mName;
        private PrinterId mPrinterId;
        private int mStatus;

        public Builder(PrinterId printerid, String s, int i)
        {
            mPrinterId = PrinterInfo._2D_wrap0(printerid);
            mName = PrinterInfo._2D_wrap2(s);
            mStatus = PrinterInfo._2D_wrap1(i);
        }

        public Builder(PrinterInfo printerinfo)
        {
            mPrinterId = PrinterInfo._2D_get5(printerinfo);
            mName = PrinterInfo._2D_get7(printerinfo);
            mStatus = PrinterInfo._2D_get8(printerinfo);
            mIconResourceId = PrinterInfo._2D_get4(printerinfo);
            mHasCustomPrinterIcon = PrinterInfo._2D_get3(printerinfo);
            mDescription = PrinterInfo._2D_get2(printerinfo);
            mInfoIntent = PrinterInfo._2D_get6(printerinfo);
            mCapabilities = PrinterInfo._2D_get0(printerinfo);
            mCustomPrinterIconGen = PrinterInfo._2D_get1(printerinfo);
        }
    }


    static PrinterCapabilitiesInfo _2D_get0(PrinterInfo printerinfo)
    {
        return printerinfo.mCapabilities;
    }

    static int _2D_get1(PrinterInfo printerinfo)
    {
        return printerinfo.mCustomPrinterIconGen;
    }

    static String _2D_get2(PrinterInfo printerinfo)
    {
        return printerinfo.mDescription;
    }

    static boolean _2D_get3(PrinterInfo printerinfo)
    {
        return printerinfo.mHasCustomPrinterIcon;
    }

    static int _2D_get4(PrinterInfo printerinfo)
    {
        return printerinfo.mIconResourceId;
    }

    static PrinterId _2D_get5(PrinterInfo printerinfo)
    {
        return printerinfo.mId;
    }

    static PendingIntent _2D_get6(PrinterInfo printerinfo)
    {
        return printerinfo.mInfoIntent;
    }

    static String _2D_get7(PrinterInfo printerinfo)
    {
        return printerinfo.mName;
    }

    static int _2D_get8(PrinterInfo printerinfo)
    {
        return printerinfo.mStatus;
    }

    static PrinterId _2D_wrap0(PrinterId printerid)
    {
        return checkPrinterId(printerid);
    }

    static int _2D_wrap1(int i)
    {
        return checkStatus(i);
    }

    static String _2D_wrap2(String s)
    {
        return checkName(s);
    }

    private PrinterInfo(Parcel parcel)
    {
        mId = checkPrinterId((PrinterId)parcel.readParcelable(null));
        mName = checkName(parcel.readString());
        mStatus = checkStatus(parcel.readInt());
        mDescription = parcel.readString();
        mCapabilities = (PrinterCapabilitiesInfo)parcel.readParcelable(null);
        mIconResourceId = parcel.readInt();
        boolean flag;
        if(parcel.readByte() != 0)
            flag = true;
        else
            flag = false;
        mHasCustomPrinterIcon = flag;
        mCustomPrinterIconGen = parcel.readInt();
        mInfoIntent = (PendingIntent)parcel.readParcelable(null);
    }

    PrinterInfo(Parcel parcel, PrinterInfo printerinfo)
    {
        this(parcel);
    }

    private PrinterInfo(PrinterId printerid, String s, int i, int j, boolean flag, String s1, PendingIntent pendingintent, 
            PrinterCapabilitiesInfo printercapabilitiesinfo, int k)
    {
        mId = printerid;
        mName = s;
        mStatus = i;
        mIconResourceId = j;
        mHasCustomPrinterIcon = flag;
        mDescription = s1;
        mInfoIntent = pendingintent;
        mCapabilities = printercapabilitiesinfo;
        mCustomPrinterIconGen = k;
    }

    PrinterInfo(PrinterId printerid, String s, int i, int j, boolean flag, String s1, PendingIntent pendingintent, 
            PrinterCapabilitiesInfo printercapabilitiesinfo, int k, PrinterInfo printerinfo)
    {
        this(printerid, s, i, j, flag, s1, pendingintent, printercapabilitiesinfo, k);
    }

    private static String checkName(String s)
    {
        return (String)Preconditions.checkStringNotEmpty(s, "name cannot be empty.");
    }

    private static PrinterId checkPrinterId(PrinterId printerid)
    {
        return (PrinterId)Preconditions.checkNotNull(printerid, "printerId cannot be null.");
    }

    private static int checkStatus(int i)
    {
        if(i != 1 && i != 2 && i != 3)
            throw new IllegalArgumentException("status is invalid.");
        else
            return i;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        obj = (PrinterInfo)obj;
        if(!equalsIgnoringStatus(((PrinterInfo) (obj))))
            return false;
        return mStatus == ((PrinterInfo) (obj)).mStatus;
    }

    public boolean equalsIgnoringStatus(PrinterInfo printerinfo)
    {
        if(!mId.equals(printerinfo.mId))
            return false;
        if(!mName.equals(printerinfo.mName))
            return false;
        if(!TextUtils.equals(mDescription, printerinfo.mDescription))
            return false;
        if(mCapabilities == null)
        {
            if(printerinfo.mCapabilities != null)
                return false;
        } else
        if(!mCapabilities.equals(printerinfo.mCapabilities))
            return false;
        if(mIconResourceId != printerinfo.mIconResourceId)
            return false;
        if(mHasCustomPrinterIcon != printerinfo.mHasCustomPrinterIcon)
            return false;
        if(mCustomPrinterIconGen != printerinfo.mCustomPrinterIconGen)
            return false;
        if(mInfoIntent == null)
        {
            if(printerinfo.mInfoIntent != null)
                return false;
        } else
        if(!mInfoIntent.equals(printerinfo.mInfoIntent))
            return false;
        return true;
    }

    public PrinterCapabilitiesInfo getCapabilities()
    {
        return mCapabilities;
    }

    public String getDescription()
    {
        return mDescription;
    }

    public boolean getHasCustomPrinterIcon()
    {
        return mHasCustomPrinterIcon;
    }

    public PrinterId getId()
    {
        return mId;
    }

    public PendingIntent getInfoIntent()
    {
        return mInfoIntent;
    }

    public String getName()
    {
        return mName;
    }

    public int getStatus()
    {
        return mStatus;
    }

    public int hashCode()
    {
        int i = 0;
        int j = mId.hashCode();
        int k = mName.hashCode();
        int l = mStatus;
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        if(mDescription != null)
            i1 = mDescription.hashCode();
        else
            i1 = 0;
        if(mCapabilities != null)
            j1 = mCapabilities.hashCode();
        else
            j1 = 0;
        k1 = mIconResourceId;
        if(mHasCustomPrinterIcon)
            l1 = 1;
        else
            l1 = 0;
        i2 = mCustomPrinterIconGen;
        if(mInfoIntent != null)
            i = mInfoIntent.hashCode();
        return ((((((((j + 31) * 31 + k) * 31 + l) * 31 + i1) * 31 + j1) * 31 + k1) * 31 + l1) * 31 + i2) * 31 + i;
    }

    public Drawable loadIcon(Context context)
    {
        Object obj;
        PackageManager packagemanager;
        Drawable drawable;
        obj = null;
        packagemanager = context.getPackageManager();
        drawable = ((Drawable) (obj));
        if(mHasCustomPrinterIcon)
        {
            Icon icon = ((PrintManager)context.getSystemService("print")).getCustomPrinterIcon(mId);
            drawable = ((Drawable) (obj));
            if(icon != null)
                drawable = icon.loadDrawable(context);
        }
        obj = drawable;
        if(drawable != null)
            break MISSING_BLOCK_LABEL_135;
        obj = drawable;
        String s = mId.getServiceName().getPackageName();
        obj = drawable;
        ApplicationInfo applicationinfo = packagemanager.getPackageInfo(s, 0).applicationInfo;
        context = drawable;
        obj = drawable;
        if(mIconResourceId == 0)
            break MISSING_BLOCK_LABEL_118;
        obj = drawable;
        context = packagemanager.getDrawable(s, mIconResourceId, applicationinfo);
        obj = context;
        if(context != null)
            break MISSING_BLOCK_LABEL_135;
        obj = context;
        context = applicationinfo.loadIcon(packagemanager);
        obj = context;
_L2:
        return ((Drawable) (obj));
        context;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("PrinterInfo{");
        stringbuilder.append("id=").append(mId);
        stringbuilder.append(", name=").append(mName);
        stringbuilder.append(", status=").append(mStatus);
        stringbuilder.append(", description=").append(mDescription);
        stringbuilder.append(", capabilities=").append(mCapabilities);
        stringbuilder.append(", iconResId=").append(mIconResourceId);
        stringbuilder.append(", hasCustomPrinterIcon=").append(mHasCustomPrinterIcon);
        stringbuilder.append(", customPrinterIconGen=").append(mCustomPrinterIconGen);
        stringbuilder.append(", infoIntent=").append(mInfoIntent);
        stringbuilder.append("\"}");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(mId, i);
        parcel.writeString(mName);
        parcel.writeInt(mStatus);
        parcel.writeString(mDescription);
        parcel.writeParcelable(mCapabilities, i);
        parcel.writeInt(mIconResourceId);
        int j;
        if(mHasCustomPrinterIcon)
            j = 1;
        else
            j = 0;
        parcel.writeByte((byte)j);
        parcel.writeInt(mCustomPrinterIconGen);
        parcel.writeParcelable(mInfoIntent, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public PrinterInfo createFromParcel(Parcel parcel)
        {
            return new PrinterInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PrinterInfo[] newArray(int i)
        {
            return new PrinterInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int STATUS_BUSY = 2;
    public static final int STATUS_IDLE = 1;
    public static final int STATUS_UNAVAILABLE = 3;
    private final PrinterCapabilitiesInfo mCapabilities;
    private final int mCustomPrinterIconGen;
    private final String mDescription;
    private final boolean mHasCustomPrinterIcon;
    private final int mIconResourceId;
    private final PrinterId mId;
    private final PendingIntent mInfoIntent;
    private final String mName;
    private final int mStatus;

}
