// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.print;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.android.internal.util.Preconditions;

public final class PrintDocumentInfo
    implements Parcelable
{
    public static final class Builder
    {

        public PrintDocumentInfo build()
        {
            if(PrintDocumentInfo._2D_get0(mPrototype) == 0)
                PrintDocumentInfo._2D_set2(mPrototype, -1);
            return new PrintDocumentInfo(mPrototype, null, null);
        }

        public Builder setContentType(int i)
        {
            PrintDocumentInfo._2D_set0(mPrototype, i);
            return this;
        }

        public Builder setPageCount(int i)
        {
            if(i < 0 && i != -1)
            {
                throw new IllegalArgumentException("pageCount must be greater than or equal to zero or DocumentInfo#PAGE_COUNT_UNKNOWN");
            } else
            {
                PrintDocumentInfo._2D_set2(mPrototype, i);
                return this;
            }
        }

        private final PrintDocumentInfo mPrototype;

        public Builder(String s)
        {
            if(TextUtils.isEmpty(s))
            {
                throw new IllegalArgumentException("name cannot be empty");
            } else
            {
                mPrototype = new PrintDocumentInfo(null, null);
                PrintDocumentInfo._2D_set1(mPrototype, s);
                return;
            }
        }
    }


    static int _2D_get0(PrintDocumentInfo printdocumentinfo)
    {
        return printdocumentinfo.mPageCount;
    }

    static int _2D_set0(PrintDocumentInfo printdocumentinfo, int i)
    {
        printdocumentinfo.mContentType = i;
        return i;
    }

    static String _2D_set1(PrintDocumentInfo printdocumentinfo, String s)
    {
        printdocumentinfo.mName = s;
        return s;
    }

    static int _2D_set2(PrintDocumentInfo printdocumentinfo, int i)
    {
        printdocumentinfo.mPageCount = i;
        return i;
    }

    private PrintDocumentInfo()
    {
    }

    private PrintDocumentInfo(Parcel parcel)
    {
        mName = (String)Preconditions.checkStringNotEmpty(parcel.readString());
        mPageCount = parcel.readInt();
        boolean flag;
        if(mPageCount == -1 || mPageCount > 0)
            flag = true;
        else
            flag = false;
        Preconditions.checkArgument(flag);
        mContentType = parcel.readInt();
        mDataSize = Preconditions.checkArgumentNonnegative(parcel.readLong());
    }

    PrintDocumentInfo(Parcel parcel, PrintDocumentInfo printdocumentinfo)
    {
        this(parcel);
    }

    private PrintDocumentInfo(PrintDocumentInfo printdocumentinfo)
    {
        mName = printdocumentinfo.mName;
        mPageCount = printdocumentinfo.mPageCount;
        mContentType = printdocumentinfo.mContentType;
        mDataSize = printdocumentinfo.mDataSize;
    }

    PrintDocumentInfo(PrintDocumentInfo printdocumentinfo, PrintDocumentInfo printdocumentinfo1)
    {
        this();
    }

    PrintDocumentInfo(PrintDocumentInfo printdocumentinfo, PrintDocumentInfo printdocumentinfo1, PrintDocumentInfo printdocumentinfo2)
    {
        this(printdocumentinfo);
    }

    private String contentTypeToString(int i)
    {
        switch(i)
        {
        default:
            return "CONTENT_TYPE_UNKNOWN";

        case 0: // '\0'
            return "CONTENT_TYPE_DOCUMENT";

        case 1: // '\001'
            return "CONTENT_TYPE_PHOTO";
        }
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
        obj = (PrintDocumentInfo)obj;
        if(!TextUtils.equals(mName, ((PrintDocumentInfo) (obj)).mName))
            return false;
        if(mContentType != ((PrintDocumentInfo) (obj)).mContentType)
            return false;
        if(mPageCount != ((PrintDocumentInfo) (obj)).mPageCount)
            return false;
        return mDataSize == ((PrintDocumentInfo) (obj)).mDataSize;
    }

    public int getContentType()
    {
        return mContentType;
    }

    public long getDataSize()
    {
        return mDataSize;
    }

    public String getName()
    {
        return mName;
    }

    public int getPageCount()
    {
        return mPageCount;
    }

    public int hashCode()
    {
        int i;
        if(mName != null)
            i = mName.hashCode();
        else
            i = 0;
        return ((((i + 31) * 31 + mContentType) * 31 + mPageCount) * 31 + (int)mDataSize) * 31 + (int)(mDataSize >> 32);
    }

    public void setDataSize(long l)
    {
        mDataSize = l;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("PrintDocumentInfo{");
        stringbuilder.append("name=").append(mName);
        stringbuilder.append(", pageCount=").append(mPageCount);
        stringbuilder.append(", contentType=").append(contentTypeToString(mContentType));
        stringbuilder.append(", dataSize=").append(mDataSize);
        stringbuilder.append("}");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mName);
        parcel.writeInt(mPageCount);
        parcel.writeInt(mContentType);
        parcel.writeLong(mDataSize);
    }

    public static final int CONTENT_TYPE_DOCUMENT = 0;
    public static final int CONTENT_TYPE_PHOTO = 1;
    public static final int CONTENT_TYPE_UNKNOWN = -1;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public PrintDocumentInfo createFromParcel(Parcel parcel)
        {
            return new PrintDocumentInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PrintDocumentInfo[] newArray(int i)
        {
            return new PrintDocumentInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int PAGE_COUNT_UNKNOWN = -1;
    private int mContentType;
    private long mDataSize;
    private String mName;
    private int mPageCount;

}
