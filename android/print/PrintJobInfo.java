// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.print;

import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.*;
import com.android.internal.util.Preconditions;
import java.util.Arrays;

// Referenced classes of package android.print:
//            PrintJobId, PrinterId, PageRange, PrintAttributes, 
//            PrintDocumentInfo

public final class PrintJobInfo
    implements Parcelable
{
    public static final class Builder
    {

        public PrintJobInfo build()
        {
            return mPrototype;
        }

        public void putAdvancedOption(String s, int i)
        {
            if(PrintJobInfo._2D_get0(mPrototype) == null)
                PrintJobInfo._2D_set0(mPrototype, new Bundle());
            PrintJobInfo._2D_get0(mPrototype).putInt(s, i);
        }

        public void putAdvancedOption(String s, String s1)
        {
            Preconditions.checkNotNull(s, "key cannot be null");
            if(PrintJobInfo._2D_get0(mPrototype) == null)
                PrintJobInfo._2D_set0(mPrototype, new Bundle());
            PrintJobInfo._2D_get0(mPrototype).putString(s, s1);
        }

        public void setAttributes(PrintAttributes printattributes)
        {
            PrintJobInfo._2D_set1(mPrototype, printattributes);
        }

        public void setCopies(int i)
        {
            PrintJobInfo._2D_set2(mPrototype, i);
        }

        public void setPages(PageRange apagerange[])
        {
            PrintJobInfo._2D_set3(mPrototype, apagerange);
        }

        public void setProgress(float f)
        {
            Preconditions.checkArgumentInRange(f, 0.0F, 1.0F, "progress");
            PrintJobInfo._2D_set4(mPrototype, f);
        }

        public void setStatus(CharSequence charsequence)
        {
            PrintJobInfo._2D_set5(mPrototype, charsequence);
        }

        private final PrintJobInfo mPrototype;

        public Builder(PrintJobInfo printjobinfo)
        {
            if(printjobinfo != null)
                printjobinfo = new PrintJobInfo(printjobinfo);
            else
                printjobinfo = new PrintJobInfo();
            mPrototype = printjobinfo;
        }
    }


    static Bundle _2D_get0(PrintJobInfo printjobinfo)
    {
        return printjobinfo.mAdvancedOptions;
    }

    static Bundle _2D_set0(PrintJobInfo printjobinfo, Bundle bundle)
    {
        printjobinfo.mAdvancedOptions = bundle;
        return bundle;
    }

    static PrintAttributes _2D_set1(PrintJobInfo printjobinfo, PrintAttributes printattributes)
    {
        printjobinfo.mAttributes = printattributes;
        return printattributes;
    }

    static int _2D_set2(PrintJobInfo printjobinfo, int i)
    {
        printjobinfo.mCopies = i;
        return i;
    }

    static PageRange[] _2D_set3(PrintJobInfo printjobinfo, PageRange apagerange[])
    {
        printjobinfo.mPageRanges = apagerange;
        return apagerange;
    }

    static float _2D_set4(PrintJobInfo printjobinfo, float f)
    {
        printjobinfo.mProgress = f;
        return f;
    }

    static CharSequence _2D_set5(PrintJobInfo printjobinfo, CharSequence charsequence)
    {
        printjobinfo.mStatus = charsequence;
        return charsequence;
    }

    public PrintJobInfo()
    {
        mProgress = -1F;
    }

    private PrintJobInfo(Parcel parcel)
    {
        mId = (PrintJobId)parcel.readParcelable(null);
        mLabel = parcel.readString();
        mPrinterId = (PrinterId)parcel.readParcelable(null);
        mPrinterName = parcel.readString();
        mState = parcel.readInt();
        mAppId = parcel.readInt();
        mTag = parcel.readString();
        mCreationTime = parcel.readLong();
        mCopies = parcel.readInt();
        Parcelable aparcelable[] = parcel.readParcelableArray(null);
        if(aparcelable != null)
        {
            mPageRanges = new PageRange[aparcelable.length];
            for(int i = 0; i < aparcelable.length; i++)
                mPageRanges[i] = (PageRange)aparcelable[i];

        }
        mAttributes = (PrintAttributes)parcel.readParcelable(null);
        mDocumentInfo = (PrintDocumentInfo)parcel.readParcelable(null);
        mProgress = parcel.readFloat();
        mStatus = parcel.readCharSequence();
        mStatusRes = parcel.readInt();
        mStatusResAppPackageName = parcel.readCharSequence();
        boolean flag;
        if(parcel.readInt() == 1)
            flag = true;
        else
            flag = false;
        mCanceling = flag;
        mAdvancedOptions = parcel.readBundle();
        if(mAdvancedOptions != null)
            Preconditions.checkArgument(mAdvancedOptions.containsKey(null) ^ true);
    }

    PrintJobInfo(Parcel parcel, PrintJobInfo printjobinfo)
    {
        this(parcel);
    }

    public PrintJobInfo(PrintJobInfo printjobinfo)
    {
        mId = printjobinfo.mId;
        mLabel = printjobinfo.mLabel;
        mPrinterId = printjobinfo.mPrinterId;
        mPrinterName = printjobinfo.mPrinterName;
        mState = printjobinfo.mState;
        mAppId = printjobinfo.mAppId;
        mTag = printjobinfo.mTag;
        mCreationTime = printjobinfo.mCreationTime;
        mCopies = printjobinfo.mCopies;
        mPageRanges = printjobinfo.mPageRanges;
        mAttributes = printjobinfo.mAttributes;
        mDocumentInfo = printjobinfo.mDocumentInfo;
        mProgress = printjobinfo.mProgress;
        mStatus = printjobinfo.mStatus;
        mStatusRes = printjobinfo.mStatusRes;
        mStatusResAppPackageName = printjobinfo.mStatusResAppPackageName;
        mCanceling = printjobinfo.mCanceling;
        mAdvancedOptions = printjobinfo.mAdvancedOptions;
    }

    public static String stateToString(int i)
    {
        switch(i)
        {
        default:
            return "STATE_UNKNOWN";

        case 1: // '\001'
            return "STATE_CREATED";

        case 2: // '\002'
            return "STATE_QUEUED";

        case 3: // '\003'
            return "STATE_STARTED";

        case 4: // '\004'
            return "STATE_BLOCKED";

        case 6: // '\006'
            return "STATE_FAILED";

        case 5: // '\005'
            return "STATE_COMPLETED";

        case 7: // '\007'
            return "STATE_CANCELED";
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public int getAdvancedIntOption(String s)
    {
        if(mAdvancedOptions != null)
            return mAdvancedOptions.getInt(s);
        else
            return 0;
    }

    public Bundle getAdvancedOptions()
    {
        return mAdvancedOptions;
    }

    public String getAdvancedStringOption(String s)
    {
        if(mAdvancedOptions != null)
            return mAdvancedOptions.getString(s);
        else
            return null;
    }

    public int getAppId()
    {
        return mAppId;
    }

    public PrintAttributes getAttributes()
    {
        return mAttributes;
    }

    public int getCopies()
    {
        return mCopies;
    }

    public long getCreationTime()
    {
        return mCreationTime;
    }

    public PrintDocumentInfo getDocumentInfo()
    {
        return mDocumentInfo;
    }

    public PrintJobId getId()
    {
        return mId;
    }

    public String getLabel()
    {
        return mLabel;
    }

    public PageRange[] getPages()
    {
        return mPageRanges;
    }

    public PrinterId getPrinterId()
    {
        return mPrinterId;
    }

    public String getPrinterName()
    {
        return mPrinterName;
    }

    public float getProgress()
    {
        return mProgress;
    }

    public int getState()
    {
        return mState;
    }

    public CharSequence getStatus(PackageManager packagemanager)
    {
        if(mStatusRes == 0)
            return mStatus;
        try
        {
            packagemanager = packagemanager.getResourcesForApplication(mStatusResAppPackageName.toString()).getString(mStatusRes);
        }
        // Misplaced declaration of an exception variable
        catch(PackageManager packagemanager)
        {
            return null;
        }
        return packagemanager;
    }

    public String getTag()
    {
        return mTag;
    }

    public boolean hasAdvancedOption(String s)
    {
        boolean flag;
        if(mAdvancedOptions != null)
            flag = mAdvancedOptions.containsKey(s);
        else
            flag = false;
        return flag;
    }

    public boolean isCancelling()
    {
        return mCanceling;
    }

    public void setAdvancedOptions(Bundle bundle)
    {
        mAdvancedOptions = bundle;
    }

    public void setAppId(int i)
    {
        mAppId = i;
    }

    public void setAttributes(PrintAttributes printattributes)
    {
        mAttributes = printattributes;
    }

    public void setCancelling(boolean flag)
    {
        mCanceling = flag;
    }

    public void setCopies(int i)
    {
        if(i < 1)
        {
            throw new IllegalArgumentException("Copies must be more than one.");
        } else
        {
            mCopies = i;
            return;
        }
    }

    public void setCreationTime(long l)
    {
        if(l < 0L)
        {
            throw new IllegalArgumentException("creationTime must be non-negative.");
        } else
        {
            mCreationTime = l;
            return;
        }
    }

    public void setDocumentInfo(PrintDocumentInfo printdocumentinfo)
    {
        mDocumentInfo = printdocumentinfo;
    }

    public void setId(PrintJobId printjobid)
    {
        mId = printjobid;
    }

    public void setLabel(String s)
    {
        mLabel = s;
    }

    public void setPages(PageRange apagerange[])
    {
        mPageRanges = apagerange;
    }

    public void setPrinterId(PrinterId printerid)
    {
        mPrinterId = printerid;
    }

    public void setPrinterName(String s)
    {
        mPrinterName = s;
    }

    public void setProgress(float f)
    {
        Preconditions.checkArgumentInRange(f, 0.0F, 1.0F, "progress");
        mProgress = f;
    }

    public void setState(int i)
    {
        mState = i;
    }

    public void setStatus(int i, CharSequence charsequence)
    {
        mStatus = null;
        mStatusRes = i;
        mStatusResAppPackageName = charsequence;
    }

    public void setStatus(CharSequence charsequence)
    {
        mStatusRes = 0;
        mStatusResAppPackageName = null;
        mStatus = charsequence;
    }

    public void setTag(String s)
    {
        mTag = s;
    }

    public String toString()
    {
        Object obj = null;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("PrintJobInfo{");
        stringbuilder.append("label: ").append(mLabel);
        stringbuilder.append(", id: ").append(mId);
        stringbuilder.append(", state: ").append(stateToString(mState));
        stringbuilder.append(", printer: ").append(mPrinterId);
        stringbuilder.append(", tag: ").append(mTag);
        stringbuilder.append(", creationTime: ").append(mCreationTime);
        stringbuilder.append(", copies: ").append(mCopies);
        StringBuilder stringbuilder1 = stringbuilder.append(", attributes: ");
        Object obj1;
        boolean flag;
        if(mAttributes != null)
            obj1 = mAttributes.toString();
        else
            obj1 = null;
        stringbuilder1.append(((String) (obj1)));
        stringbuilder1 = stringbuilder.append(", documentInfo: ");
        if(mDocumentInfo != null)
            obj1 = mDocumentInfo.toString();
        else
            obj1 = null;
        stringbuilder1.append(((String) (obj1)));
        stringbuilder.append(", cancelling: ").append(mCanceling);
        stringbuilder1 = stringbuilder.append(", pages: ");
        if(mPageRanges != null)
            obj1 = Arrays.toString(mPageRanges);
        else
            obj1 = null;
        stringbuilder1.append(((String) (obj1)));
        obj1 = stringbuilder.append(", hasAdvancedOptions: ");
        if(mAdvancedOptions != null)
            flag = true;
        else
            flag = false;
        ((StringBuilder) (obj1)).append(flag);
        stringbuilder.append(", progress: ").append(mProgress);
        stringbuilder1 = stringbuilder.append(", status: ");
        if(mStatus != null)
            obj1 = mStatus.toString();
        else
            obj1 = null;
        stringbuilder1.append(((String) (obj1)));
        stringbuilder.append(", statusRes: ").append(mStatusRes);
        stringbuilder1 = stringbuilder.append(", statusResAppPackageName: ");
        obj1 = obj;
        if(mStatusResAppPackageName != null)
            obj1 = mStatusResAppPackageName.toString();
        stringbuilder1.append(((String) (obj1)));
        stringbuilder.append("}");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = false;
        parcel.writeParcelable(mId, i);
        parcel.writeString(mLabel);
        parcel.writeParcelable(mPrinterId, i);
        parcel.writeString(mPrinterName);
        parcel.writeInt(mState);
        parcel.writeInt(mAppId);
        parcel.writeString(mTag);
        parcel.writeLong(mCreationTime);
        parcel.writeInt(mCopies);
        parcel.writeParcelableArray(mPageRanges, i);
        parcel.writeParcelable(mAttributes, i);
        parcel.writeParcelable(mDocumentInfo, 0);
        parcel.writeFloat(mProgress);
        parcel.writeCharSequence(mStatus);
        parcel.writeInt(mStatusRes);
        parcel.writeCharSequence(mStatusResAppPackageName);
        i = ((flag) ? 1 : 0);
        if(mCanceling)
            i = 1;
        parcel.writeInt(i);
        parcel.writeBundle(mAdvancedOptions);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public PrintJobInfo createFromParcel(Parcel parcel)
        {
            return new PrintJobInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PrintJobInfo[] newArray(int i)
        {
            return new PrintJobInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int STATE_ANY = -1;
    public static final int STATE_ANY_ACTIVE = -3;
    public static final int STATE_ANY_SCHEDULED = -4;
    public static final int STATE_ANY_VISIBLE_TO_CLIENTS = -2;
    public static final int STATE_BLOCKED = 4;
    public static final int STATE_CANCELED = 7;
    public static final int STATE_COMPLETED = 5;
    public static final int STATE_CREATED = 1;
    public static final int STATE_FAILED = 6;
    public static final int STATE_QUEUED = 2;
    public static final int STATE_STARTED = 3;
    private Bundle mAdvancedOptions;
    private int mAppId;
    private PrintAttributes mAttributes;
    private boolean mCanceling;
    private int mCopies;
    private long mCreationTime;
    private PrintDocumentInfo mDocumentInfo;
    private PrintJobId mId;
    private String mLabel;
    private PageRange mPageRanges[];
    private PrinterId mPrinterId;
    private String mPrinterName;
    private float mProgress;
    private int mState;
    private CharSequence mStatus;
    private int mStatusRes;
    private CharSequence mStatusResAppPackageName;
    private String mTag;

}
