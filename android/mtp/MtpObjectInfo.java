// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.mtp;

import com.android.internal.util.Preconditions;
import dalvik.system.VMRuntime;

public final class MtpObjectInfo
{
    public static class Builder
    {

        public MtpObjectInfo build()
        {
            MtpObjectInfo mtpobjectinfo = mObjectInfo;
            mObjectInfo = null;
            return mtpobjectinfo;
        }

        public Builder setAssociationDesc(int i)
        {
            MtpObjectInfo._2D_set0(mObjectInfo, i);
            return this;
        }

        public Builder setAssociationType(int i)
        {
            MtpObjectInfo._2D_set1(mObjectInfo, i);
            return this;
        }

        public Builder setCompressedSize(long l)
        {
            MtpObjectInfo._2D_set2(mObjectInfo, MtpObjectInfo._2D_wrap0(l, "value"));
            return this;
        }

        public Builder setDateCreated(long l)
        {
            MtpObjectInfo._2D_set3(mObjectInfo, l);
            return this;
        }

        public Builder setDateModified(long l)
        {
            MtpObjectInfo._2D_set4(mObjectInfo, l);
            return this;
        }

        public Builder setFormat(int i)
        {
            MtpObjectInfo._2D_set5(mObjectInfo, i);
            return this;
        }

        public Builder setImagePixDepth(long l)
        {
            MtpObjectInfo._2D_set7(mObjectInfo, MtpObjectInfo._2D_wrap0(l, "value"));
            return this;
        }

        public Builder setImagePixHeight(long l)
        {
            MtpObjectInfo._2D_set8(mObjectInfo, MtpObjectInfo._2D_wrap0(l, "value"));
            return this;
        }

        public Builder setImagePixWidth(long l)
        {
            MtpObjectInfo._2D_set9(mObjectInfo, MtpObjectInfo._2D_wrap0(l, "value"));
            return this;
        }

        public Builder setKeywords(String s)
        {
            if(VMRuntime.getRuntime().getTargetSdkVersion() <= 25) goto _L2; else goto _L1
_L1:
            String s1;
            Preconditions.checkNotNull(s);
            s1 = s;
_L4:
            MtpObjectInfo._2D_set10(mObjectInfo, s1);
            return this;
_L2:
            s1 = s;
            if(s == null)
                s1 = "";
            if(true) goto _L4; else goto _L3
_L3:
        }

        public Builder setName(String s)
        {
            Preconditions.checkNotNull(s);
            MtpObjectInfo._2D_set11(mObjectInfo, s);
            return this;
        }

        public Builder setObjectHandle(int i)
        {
            MtpObjectInfo._2D_set6(mObjectInfo, i);
            return this;
        }

        public Builder setParent(int i)
        {
            MtpObjectInfo._2D_set12(mObjectInfo, i);
            return this;
        }

        public Builder setProtectionStatus(int i)
        {
            MtpObjectInfo._2D_set13(mObjectInfo, i);
            return this;
        }

        public Builder setSequenceNumber(long l)
        {
            MtpObjectInfo._2D_set14(mObjectInfo, MtpObjectInfo._2D_wrap0(l, "value"));
            return this;
        }

        public Builder setStorageId(int i)
        {
            MtpObjectInfo._2D_set15(mObjectInfo, i);
            return this;
        }

        public Builder setThumbCompressedSize(long l)
        {
            MtpObjectInfo._2D_set16(mObjectInfo, MtpObjectInfo._2D_wrap0(l, "value"));
            return this;
        }

        public Builder setThumbFormat(int i)
        {
            MtpObjectInfo._2D_set17(mObjectInfo, i);
            return this;
        }

        public Builder setThumbPixHeight(long l)
        {
            MtpObjectInfo._2D_set18(mObjectInfo, MtpObjectInfo._2D_wrap0(l, "value"));
            return this;
        }

        public Builder setThumbPixWidth(long l)
        {
            MtpObjectInfo._2D_set19(mObjectInfo, MtpObjectInfo._2D_wrap0(l, "value"));
            return this;
        }

        private MtpObjectInfo mObjectInfo;

        public Builder()
        {
            mObjectInfo = new MtpObjectInfo(null);
            MtpObjectInfo._2D_set6(mObjectInfo, -1);
        }

        public Builder(MtpObjectInfo mtpobjectinfo)
        {
            mObjectInfo = new MtpObjectInfo(null);
            MtpObjectInfo._2D_set6(mObjectInfo, -1);
            MtpObjectInfo._2D_set0(mObjectInfo, MtpObjectInfo._2D_get0(mtpobjectinfo));
            MtpObjectInfo._2D_set1(mObjectInfo, MtpObjectInfo._2D_get1(mtpobjectinfo));
            MtpObjectInfo._2D_set2(mObjectInfo, MtpObjectInfo._2D_get2(mtpobjectinfo));
            MtpObjectInfo._2D_set3(mObjectInfo, MtpObjectInfo._2D_get3(mtpobjectinfo));
            MtpObjectInfo._2D_set4(mObjectInfo, MtpObjectInfo._2D_get4(mtpobjectinfo));
            MtpObjectInfo._2D_set5(mObjectInfo, MtpObjectInfo._2D_get5(mtpobjectinfo));
            MtpObjectInfo._2D_set7(mObjectInfo, MtpObjectInfo._2D_get6(mtpobjectinfo));
            MtpObjectInfo._2D_set8(mObjectInfo, MtpObjectInfo._2D_get7(mtpobjectinfo));
            MtpObjectInfo._2D_set9(mObjectInfo, MtpObjectInfo._2D_get8(mtpobjectinfo));
            MtpObjectInfo._2D_set10(mObjectInfo, MtpObjectInfo._2D_get9(mtpobjectinfo));
            MtpObjectInfo._2D_set11(mObjectInfo, MtpObjectInfo._2D_get10(mtpobjectinfo));
            MtpObjectInfo._2D_set12(mObjectInfo, MtpObjectInfo._2D_get11(mtpobjectinfo));
            MtpObjectInfo._2D_set13(mObjectInfo, MtpObjectInfo._2D_get12(mtpobjectinfo));
            MtpObjectInfo._2D_set14(mObjectInfo, MtpObjectInfo._2D_get13(mtpobjectinfo));
            MtpObjectInfo._2D_set15(mObjectInfo, MtpObjectInfo._2D_get14(mtpobjectinfo));
            MtpObjectInfo._2D_set16(mObjectInfo, MtpObjectInfo._2D_get15(mtpobjectinfo));
            MtpObjectInfo._2D_set17(mObjectInfo, MtpObjectInfo._2D_get16(mtpobjectinfo));
            MtpObjectInfo._2D_set18(mObjectInfo, MtpObjectInfo._2D_get17(mtpobjectinfo));
            MtpObjectInfo._2D_set19(mObjectInfo, MtpObjectInfo._2D_get18(mtpobjectinfo));
        }
    }


    static int _2D_get0(MtpObjectInfo mtpobjectinfo)
    {
        return mtpobjectinfo.mAssociationDesc;
    }

    static int _2D_get1(MtpObjectInfo mtpobjectinfo)
    {
        return mtpobjectinfo.mAssociationType;
    }

    static String _2D_get10(MtpObjectInfo mtpobjectinfo)
    {
        return mtpobjectinfo.mName;
    }

    static int _2D_get11(MtpObjectInfo mtpobjectinfo)
    {
        return mtpobjectinfo.mParent;
    }

    static int _2D_get12(MtpObjectInfo mtpobjectinfo)
    {
        return mtpobjectinfo.mProtectionStatus;
    }

    static int _2D_get13(MtpObjectInfo mtpobjectinfo)
    {
        return mtpobjectinfo.mSequenceNumber;
    }

    static int _2D_get14(MtpObjectInfo mtpobjectinfo)
    {
        return mtpobjectinfo.mStorageId;
    }

    static int _2D_get15(MtpObjectInfo mtpobjectinfo)
    {
        return mtpobjectinfo.mThumbCompressedSize;
    }

    static int _2D_get16(MtpObjectInfo mtpobjectinfo)
    {
        return mtpobjectinfo.mThumbFormat;
    }

    static int _2D_get17(MtpObjectInfo mtpobjectinfo)
    {
        return mtpobjectinfo.mThumbPixHeight;
    }

    static int _2D_get18(MtpObjectInfo mtpobjectinfo)
    {
        return mtpobjectinfo.mThumbPixWidth;
    }

    static int _2D_get2(MtpObjectInfo mtpobjectinfo)
    {
        return mtpobjectinfo.mCompressedSize;
    }

    static long _2D_get3(MtpObjectInfo mtpobjectinfo)
    {
        return mtpobjectinfo.mDateCreated;
    }

    static long _2D_get4(MtpObjectInfo mtpobjectinfo)
    {
        return mtpobjectinfo.mDateModified;
    }

    static int _2D_get5(MtpObjectInfo mtpobjectinfo)
    {
        return mtpobjectinfo.mFormat;
    }

    static int _2D_get6(MtpObjectInfo mtpobjectinfo)
    {
        return mtpobjectinfo.mImagePixDepth;
    }

    static int _2D_get7(MtpObjectInfo mtpobjectinfo)
    {
        return mtpobjectinfo.mImagePixHeight;
    }

    static int _2D_get8(MtpObjectInfo mtpobjectinfo)
    {
        return mtpobjectinfo.mImagePixWidth;
    }

    static String _2D_get9(MtpObjectInfo mtpobjectinfo)
    {
        return mtpobjectinfo.mKeywords;
    }

    static int _2D_set0(MtpObjectInfo mtpobjectinfo, int i)
    {
        mtpobjectinfo.mAssociationDesc = i;
        return i;
    }

    static int _2D_set1(MtpObjectInfo mtpobjectinfo, int i)
    {
        mtpobjectinfo.mAssociationType = i;
        return i;
    }

    static String _2D_set10(MtpObjectInfo mtpobjectinfo, String s)
    {
        mtpobjectinfo.mKeywords = s;
        return s;
    }

    static String _2D_set11(MtpObjectInfo mtpobjectinfo, String s)
    {
        mtpobjectinfo.mName = s;
        return s;
    }

    static int _2D_set12(MtpObjectInfo mtpobjectinfo, int i)
    {
        mtpobjectinfo.mParent = i;
        return i;
    }

    static int _2D_set13(MtpObjectInfo mtpobjectinfo, int i)
    {
        mtpobjectinfo.mProtectionStatus = i;
        return i;
    }

    static int _2D_set14(MtpObjectInfo mtpobjectinfo, int i)
    {
        mtpobjectinfo.mSequenceNumber = i;
        return i;
    }

    static int _2D_set15(MtpObjectInfo mtpobjectinfo, int i)
    {
        mtpobjectinfo.mStorageId = i;
        return i;
    }

    static int _2D_set16(MtpObjectInfo mtpobjectinfo, int i)
    {
        mtpobjectinfo.mThumbCompressedSize = i;
        return i;
    }

    static int _2D_set17(MtpObjectInfo mtpobjectinfo, int i)
    {
        mtpobjectinfo.mThumbFormat = i;
        return i;
    }

    static int _2D_set18(MtpObjectInfo mtpobjectinfo, int i)
    {
        mtpobjectinfo.mThumbPixHeight = i;
        return i;
    }

    static int _2D_set19(MtpObjectInfo mtpobjectinfo, int i)
    {
        mtpobjectinfo.mThumbPixWidth = i;
        return i;
    }

    static int _2D_set2(MtpObjectInfo mtpobjectinfo, int i)
    {
        mtpobjectinfo.mCompressedSize = i;
        return i;
    }

    static long _2D_set3(MtpObjectInfo mtpobjectinfo, long l)
    {
        mtpobjectinfo.mDateCreated = l;
        return l;
    }

    static long _2D_set4(MtpObjectInfo mtpobjectinfo, long l)
    {
        mtpobjectinfo.mDateModified = l;
        return l;
    }

    static int _2D_set5(MtpObjectInfo mtpobjectinfo, int i)
    {
        mtpobjectinfo.mFormat = i;
        return i;
    }

    static int _2D_set6(MtpObjectInfo mtpobjectinfo, int i)
    {
        mtpobjectinfo.mHandle = i;
        return i;
    }

    static int _2D_set7(MtpObjectInfo mtpobjectinfo, int i)
    {
        mtpobjectinfo.mImagePixDepth = i;
        return i;
    }

    static int _2D_set8(MtpObjectInfo mtpobjectinfo, int i)
    {
        mtpobjectinfo.mImagePixHeight = i;
        return i;
    }

    static int _2D_set9(MtpObjectInfo mtpobjectinfo, int i)
    {
        mtpobjectinfo.mImagePixWidth = i;
        return i;
    }

    static int _2D_wrap0(long l, String s)
    {
        return longToUint32(l, s);
    }

    private MtpObjectInfo()
    {
        mName = "";
        mKeywords = "";
    }

    MtpObjectInfo(MtpObjectInfo mtpobjectinfo)
    {
        this();
    }

    private static int longToUint32(long l, String s)
    {
        Preconditions.checkArgumentInRange(l, 0L, 0xffffffffL, s);
        return (int)l;
    }

    private static long uint32ToLong(int i)
    {
        long l;
        if(i < 0)
            l = (long)i + 0x100000000L;
        else
            l = i;
        return l;
    }

    public final int getAssociationDesc()
    {
        return mAssociationDesc;
    }

    public final int getAssociationType()
    {
        return mAssociationType;
    }

    public final int getCompressedSize()
    {
        boolean flag = false;
        if(mCompressedSize >= 0)
            flag = true;
        Preconditions.checkState(flag);
        return mCompressedSize;
    }

    public final long getCompressedSizeLong()
    {
        return uint32ToLong(mCompressedSize);
    }

    public final long getDateCreated()
    {
        return mDateCreated;
    }

    public final long getDateModified()
    {
        return mDateModified;
    }

    public final int getFormat()
    {
        return mFormat;
    }

    public final int getImagePixDepth()
    {
        boolean flag = false;
        if(mImagePixDepth >= 0)
            flag = true;
        Preconditions.checkState(flag);
        return mImagePixDepth;
    }

    public final long getImagePixDepthLong()
    {
        return uint32ToLong(mImagePixDepth);
    }

    public final int getImagePixHeight()
    {
        boolean flag = false;
        if(mImagePixHeight >= 0)
            flag = true;
        Preconditions.checkState(flag);
        return mImagePixHeight;
    }

    public final long getImagePixHeightLong()
    {
        return uint32ToLong(mImagePixHeight);
    }

    public final int getImagePixWidth()
    {
        boolean flag = false;
        if(mImagePixWidth >= 0)
            flag = true;
        Preconditions.checkState(flag);
        return mImagePixWidth;
    }

    public final long getImagePixWidthLong()
    {
        return uint32ToLong(mImagePixWidth);
    }

    public final String getKeywords()
    {
        return mKeywords;
    }

    public final String getName()
    {
        return mName;
    }

    public final int getObjectHandle()
    {
        return mHandle;
    }

    public final int getParent()
    {
        return mParent;
    }

    public final int getProtectionStatus()
    {
        return mProtectionStatus;
    }

    public final int getSequenceNumber()
    {
        boolean flag = false;
        if(mSequenceNumber >= 0)
            flag = true;
        Preconditions.checkState(flag);
        return mSequenceNumber;
    }

    public final long getSequenceNumberLong()
    {
        return uint32ToLong(mSequenceNumber);
    }

    public final int getStorageId()
    {
        return mStorageId;
    }

    public final int getThumbCompressedSize()
    {
        boolean flag = false;
        if(mThumbCompressedSize >= 0)
            flag = true;
        Preconditions.checkState(flag);
        return mThumbCompressedSize;
    }

    public final long getThumbCompressedSizeLong()
    {
        return uint32ToLong(mThumbCompressedSize);
    }

    public final int getThumbFormat()
    {
        return mThumbFormat;
    }

    public final int getThumbPixHeight()
    {
        boolean flag = false;
        if(mThumbPixHeight >= 0)
            flag = true;
        Preconditions.checkState(flag);
        return mThumbPixHeight;
    }

    public final long getThumbPixHeightLong()
    {
        return uint32ToLong(mThumbPixHeight);
    }

    public final int getThumbPixWidth()
    {
        boolean flag = false;
        if(mThumbPixWidth >= 0)
            flag = true;
        Preconditions.checkState(flag);
        return mThumbPixWidth;
    }

    public final long getThumbPixWidthLong()
    {
        return uint32ToLong(mThumbPixWidth);
    }

    private int mAssociationDesc;
    private int mAssociationType;
    private int mCompressedSize;
    private long mDateCreated;
    private long mDateModified;
    private int mFormat;
    private int mHandle;
    private int mImagePixDepth;
    private int mImagePixHeight;
    private int mImagePixWidth;
    private String mKeywords;
    private String mName;
    private int mParent;
    private int mProtectionStatus;
    private int mSequenceNumber;
    private int mStorageId;
    private int mThumbCompressedSize;
    private int mThumbFormat;
    private int mThumbPixHeight;
    private int mThumbPixWidth;
}
