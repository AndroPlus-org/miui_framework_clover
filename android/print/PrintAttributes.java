// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.print;

import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.*;
import com.android.internal.util.Preconditions;
import java.util.Map;

public final class PrintAttributes
    implements Parcelable
{
    public static final class Builder
    {

        public PrintAttributes build()
        {
            return mAttributes;
        }

        public Builder setColorMode(int i)
        {
            mAttributes.setColorMode(i);
            return this;
        }

        public Builder setDuplexMode(int i)
        {
            mAttributes.setDuplexMode(i);
            return this;
        }

        public Builder setMediaSize(MediaSize mediasize)
        {
            mAttributes.setMediaSize(mediasize);
            return this;
        }

        public Builder setMinMargins(Margins margins)
        {
            mAttributes.setMinMargins(margins);
            return this;
        }

        public Builder setResolution(Resolution resolution)
        {
            mAttributes.setResolution(resolution);
            return this;
        }

        private final PrintAttributes mAttributes = new PrintAttributes();

        public Builder()
        {
        }
    }

    public static final class Margins
    {

        static Margins createFromParcel(Parcel parcel)
        {
            return new Margins(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
        }

        public boolean equals(Object obj)
        {
            if(this == obj)
                return true;
            if(obj == null)
                return false;
            if(getClass() != obj.getClass())
                return false;
            obj = (Margins)obj;
            if(mBottomMils != ((Margins) (obj)).mBottomMils)
                return false;
            if(mLeftMils != ((Margins) (obj)).mLeftMils)
                return false;
            if(mRightMils != ((Margins) (obj)).mRightMils)
                return false;
            return mTopMils == ((Margins) (obj)).mTopMils;
        }

        public int getBottomMils()
        {
            return mBottomMils;
        }

        public int getLeftMils()
        {
            return mLeftMils;
        }

        public int getRightMils()
        {
            return mRightMils;
        }

        public int getTopMils()
        {
            return mTopMils;
        }

        public int hashCode()
        {
            return (((mBottomMils + 31) * 31 + mLeftMils) * 31 + mRightMils) * 31 + mTopMils;
        }

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("Margins{");
            stringbuilder.append("leftMils: ").append(mLeftMils);
            stringbuilder.append(", topMils: ").append(mTopMils);
            stringbuilder.append(", rightMils: ").append(mRightMils);
            stringbuilder.append(", bottomMils: ").append(mBottomMils);
            stringbuilder.append("}");
            return stringbuilder.toString();
        }

        void writeToParcel(Parcel parcel)
        {
            parcel.writeInt(mLeftMils);
            parcel.writeInt(mTopMils);
            parcel.writeInt(mRightMils);
            parcel.writeInt(mBottomMils);
        }

        public static final Margins NO_MARGINS = new Margins(0, 0, 0, 0);
        private final int mBottomMils;
        private final int mLeftMils;
        private final int mRightMils;
        private final int mTopMils;


        public Margins(int i, int j, int k, int l)
        {
            mTopMils = j;
            mLeftMils = i;
            mRightMils = k;
            mBottomMils = l;
        }
    }

    public static final class MediaSize
    {

        static MediaSize createFromParcel(Parcel parcel)
        {
            return new MediaSize(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
        }

        public static ArraySet getAllPredefinedSizes()
        {
            ArraySet arrayset = new ArraySet(sIdToMediaSizeMap.values());
            arrayset.remove(UNKNOWN_PORTRAIT);
            arrayset.remove(UNKNOWN_LANDSCAPE);
            return arrayset;
        }

        public static MediaSize getStandardMediaSizeById(String s)
        {
            return (MediaSize)sIdToMediaSizeMap.get(s);
        }

        public MediaSize asLandscape()
        {
            if(!isPortrait())
                return this;
            else
                return new MediaSize(mId, mLabel, mPackageName, Math.max(mWidthMils, mHeightMils), Math.min(mWidthMils, mHeightMils), mLabelResId);
        }

        public MediaSize asPortrait()
        {
            if(isPortrait())
                return this;
            else
                return new MediaSize(mId, mLabel, mPackageName, Math.min(mWidthMils, mHeightMils), Math.max(mWidthMils, mHeightMils), mLabelResId);
        }

        public boolean equals(Object obj)
        {
            if(this == obj)
                return true;
            if(obj == null)
                return false;
            if(getClass() != obj.getClass())
                return false;
            obj = (MediaSize)obj;
            if(mWidthMils != ((MediaSize) (obj)).mWidthMils)
                return false;
            return mHeightMils == ((MediaSize) (obj)).mHeightMils;
        }

        public int getHeightMils()
        {
            return mHeightMils;
        }

        public String getId()
        {
            return mId;
        }

        public String getLabel(PackageManager packagemanager)
        {
            if(TextUtils.isEmpty(mPackageName) || mLabelResId <= 0)
                break MISSING_BLOCK_LABEL_78;
            packagemanager = packagemanager.getResourcesForApplication(mPackageName).getString(mLabelResId);
            return packagemanager;
            packagemanager;
            Log.w("MediaSize", (new StringBuilder()).append("Could not load resouce").append(mLabelResId).append(" from package ").append(mPackageName).toString());
            return mLabel;
        }

        public int getWidthMils()
        {
            return mWidthMils;
        }

        public int hashCode()
        {
            return (mWidthMils + 31) * 31 + mHeightMils;
        }

        public boolean isPortrait()
        {
            boolean flag;
            if(mHeightMils >= mWidthMils)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("MediaSize{");
            stringbuilder.append("id: ").append(mId);
            stringbuilder.append(", label: ").append(mLabel);
            stringbuilder.append(", packageName: ").append(mPackageName);
            stringbuilder.append(", heightMils: ").append(mHeightMils);
            stringbuilder.append(", widthMils: ").append(mWidthMils);
            stringbuilder.append(", labelResId: ").append(mLabelResId);
            stringbuilder.append("}");
            return stringbuilder.toString();
        }

        void writeToParcel(Parcel parcel)
        {
            parcel.writeString(mId);
            parcel.writeString(mLabel);
            parcel.writeString(mPackageName);
            parcel.writeInt(mWidthMils);
            parcel.writeInt(mHeightMils);
            parcel.writeInt(mLabelResId);
        }

        public static final MediaSize ISO_A0 = new MediaSize("ISO_A0", "android", 0x1040361, 33110, 46810);
        public static final MediaSize ISO_A1 = new MediaSize("ISO_A1", "android", 0x1040362, 23390, 33110);
        public static final MediaSize ISO_A10 = new MediaSize("ISO_A10", "android", 0x1040363, 1020, 1460);
        public static final MediaSize ISO_A2 = new MediaSize("ISO_A2", "android", 0x1040364, 16540, 23390);
        public static final MediaSize ISO_A3 = new MediaSize("ISO_A3", "android", 0x1040365, 11690, 16540);
        public static final MediaSize ISO_A4 = new MediaSize("ISO_A4", "android", 0x1040366, 8270, 11690);
        public static final MediaSize ISO_A5 = new MediaSize("ISO_A5", "android", 0x1040367, 5830, 8270);
        public static final MediaSize ISO_A6 = new MediaSize("ISO_A6", "android", 0x1040368, 4130, 5830);
        public static final MediaSize ISO_A7 = new MediaSize("ISO_A7", "android", 0x1040369, 2910, 4130);
        public static final MediaSize ISO_A8 = new MediaSize("ISO_A8", "android", 0x104036a, 2050, 2910);
        public static final MediaSize ISO_A9 = new MediaSize("ISO_A9", "android", 0x104036b, 1460, 2050);
        public static final MediaSize ISO_B0 = new MediaSize("ISO_B0", "android", 0x104036c, 39370, 55670);
        public static final MediaSize ISO_B1 = new MediaSize("ISO_B1", "android", 0x104036d, 27830, 39370);
        public static final MediaSize ISO_B10 = new MediaSize("ISO_B10", "android", 0x104036e, 1220, 1730);
        public static final MediaSize ISO_B2 = new MediaSize("ISO_B2", "android", 0x104036f, 19690, 27830);
        public static final MediaSize ISO_B3 = new MediaSize("ISO_B3", "android", 0x1040370, 13900, 19690);
        public static final MediaSize ISO_B4 = new MediaSize("ISO_B4", "android", 0x1040371, 9840, 13900);
        public static final MediaSize ISO_B5 = new MediaSize("ISO_B5", "android", 0x1040372, 6930, 9840);
        public static final MediaSize ISO_B6 = new MediaSize("ISO_B6", "android", 0x1040373, 4920, 6930);
        public static final MediaSize ISO_B7 = new MediaSize("ISO_B7", "android", 0x1040374, 3460, 4920);
        public static final MediaSize ISO_B8 = new MediaSize("ISO_B8", "android", 0x1040375, 2440, 3460);
        public static final MediaSize ISO_B9 = new MediaSize("ISO_B9", "android", 0x1040376, 1730, 2440);
        public static final MediaSize ISO_C0 = new MediaSize("ISO_C0", "android", 0x1040377, 36100, 51060);
        public static final MediaSize ISO_C1 = new MediaSize("ISO_C1", "android", 0x1040378, 25510, 36100);
        public static final MediaSize ISO_C10 = new MediaSize("ISO_C10", "android", 0x1040379, 1100, 1570);
        public static final MediaSize ISO_C2 = new MediaSize("ISO_C2", "android", 0x104037a, 18030, 25510);
        public static final MediaSize ISO_C3 = new MediaSize("ISO_C3", "android", 0x104037b, 12760, 18030);
        public static final MediaSize ISO_C4 = new MediaSize("ISO_C4", "android", 0x104037c, 9020, 12760);
        public static final MediaSize ISO_C5 = new MediaSize("ISO_C5", "android", 0x104037d, 6380, 9020);
        public static final MediaSize ISO_C6 = new MediaSize("ISO_C6", "android", 0x104037e, 4490, 6380);
        public static final MediaSize ISO_C7 = new MediaSize("ISO_C7", "android", 0x104037f, 3190, 4490);
        public static final MediaSize ISO_C8 = new MediaSize("ISO_C8", "android", 0x1040380, 2240, 3190);
        public static final MediaSize ISO_C9 = new MediaSize("ISO_C9", "android", 0x1040381, 1570, 2240);
        public static final MediaSize JIS_B0 = new MediaSize("JIS_B0", "android", 0x1040386, 40551, 57323);
        public static final MediaSize JIS_B1 = new MediaSize("JIS_B1", "android", 0x1040387, 28661, 40551);
        public static final MediaSize JIS_B10 = new MediaSize("JIS_B10", "android", 0x1040388, 1259, 1772);
        public static final MediaSize JIS_B2 = new MediaSize("JIS_B2", "android", 0x1040389, 20276, 28661);
        public static final MediaSize JIS_B3 = new MediaSize("JIS_B3", "android", 0x104038a, 14331, 20276);
        public static final MediaSize JIS_B4 = new MediaSize("JIS_B4", "android", 0x104038b, 10118, 14331);
        public static final MediaSize JIS_B5 = new MediaSize("JIS_B5", "android", 0x104038c, 7165, 10118);
        public static final MediaSize JIS_B6 = new MediaSize("JIS_B6", "android", 0x104038d, 5049, 7165);
        public static final MediaSize JIS_B7 = new MediaSize("JIS_B7", "android", 0x104038e, 3583, 5049);
        public static final MediaSize JIS_B8 = new MediaSize("JIS_B8", "android", 0x104038f, 2520, 3583);
        public static final MediaSize JIS_B9 = new MediaSize("JIS_B9", "android", 0x1040390, 1772, 2520);
        public static final MediaSize JIS_EXEC = new MediaSize("JIS_EXEC", "android", 0x1040391, 8504, 12992);
        public static final MediaSize JPN_CHOU2 = new MediaSize("JPN_CHOU2", "android", 0x1040382, 4374, 5748);
        public static final MediaSize JPN_CHOU3 = new MediaSize("JPN_CHOU3", "android", 0x1040383, 4724, 9252);
        public static final MediaSize JPN_CHOU4 = new MediaSize("JPN_CHOU4", "android", 0x1040384, 3543, 8071);
        public static final MediaSize JPN_HAGAKI = new MediaSize("JPN_HAGAKI", "android", 0x1040385, 3937, 5827);
        public static final MediaSize JPN_KAHU = new MediaSize("JPN_KAHU", "android", 0x1040392, 9449, 12681);
        public static final MediaSize JPN_KAKU2 = new MediaSize("JPN_KAKU2", "android", 0x1040393, 9449, 13071);
        public static final MediaSize JPN_OUFUKU = new MediaSize("JPN_OUFUKU", "android", 0x1040394, 5827, 7874);
        public static final MediaSize JPN_YOU4 = new MediaSize("JPN_YOU4", "android", 0x1040395, 4134, 9252);
        private static final String LOG_TAG = "MediaSize";
        public static final MediaSize NA_FOOLSCAP = new MediaSize("NA_FOOLSCAP", "android", 0x1040396, 8000, 13000);
        public static final MediaSize NA_GOVT_LETTER = new MediaSize("NA_GOVT_LETTER", "android", 0x1040397, 8000, 10500);
        public static final MediaSize NA_INDEX_3X5 = new MediaSize("NA_INDEX_3X5", "android", 0x1040398, 3000, 5000);
        public static final MediaSize NA_INDEX_4X6 = new MediaSize("NA_INDEX_4X6", "android", 0x1040399, 4000, 6000);
        public static final MediaSize NA_INDEX_5X8 = new MediaSize("NA_INDEX_5X8", "android", 0x104039a, 5000, 8000);
        public static final MediaSize NA_JUNIOR_LEGAL = new MediaSize("NA_JUNIOR_LEGAL", "android", 0x104039b, 8000, 5000);
        public static final MediaSize NA_LEDGER = new MediaSize("NA_LEDGER", "android", 0x104039c, 17000, 11000);
        public static final MediaSize NA_LEGAL = new MediaSize("NA_LEGAL", "android", 0x104039d, 8500, 14000);
        public static final MediaSize NA_LETTER = new MediaSize("NA_LETTER", "android", 0x104039e, 8500, 11000);
        public static final MediaSize NA_MONARCH = new MediaSize("NA_MONARCH", "android", 0x104039f, 7250, 10500);
        public static final MediaSize NA_QUARTO = new MediaSize("NA_QUARTO", "android", 0x10403a0, 8000, 10000);
        public static final MediaSize NA_TABLOID = new MediaSize("NA_TABLOID", "android", 0x10403a1, 11000, 17000);
        public static final MediaSize OM_DAI_PA_KAI = new MediaSize("OM_DAI_PA_KAI", "android", 0x1040351, 10827, 15551);
        public static final MediaSize OM_JUURO_KU_KAI = new MediaSize("OM_JUURO_KU_KAI", "android", 0x1040352, 7796, 10827);
        public static final MediaSize OM_PA_KAI = new MediaSize("OM_PA_KAI", "android", 0x1040353, 10512, 15315);
        public static final MediaSize PRC_1 = new MediaSize("PRC_1", "android", 0x1040354, 4015, 6496);
        public static final MediaSize PRC_10 = new MediaSize("PRC_10", "android", 0x1040355, 12756, 18032);
        public static final MediaSize PRC_16K = new MediaSize("PRC_16K", "android", 0x1040356, 5749, 8465);
        public static final MediaSize PRC_2 = new MediaSize("PRC_2", "android", 0x1040357, 4015, 6929);
        public static final MediaSize PRC_3 = new MediaSize("PRC_3", "android", 0x1040358, 4921, 6929);
        public static final MediaSize PRC_4 = new MediaSize("PRC_4", "android", 0x1040359, 4330, 8189);
        public static final MediaSize PRC_5 = new MediaSize("PRC_5", "android", 0x104035a, 4330, 8661);
        public static final MediaSize PRC_6 = new MediaSize("PRC_6", "android", 0x104035b, 4724, 12599);
        public static final MediaSize PRC_7 = new MediaSize("PRC_7", "android", 0x104035c, 6299, 9055);
        public static final MediaSize PRC_8 = new MediaSize("PRC_8", "android", 0x104035d, 4724, 12165);
        public static final MediaSize PRC_9 = new MediaSize("PRC_9", "android", 0x104035e, 9016, 12756);
        public static final MediaSize ROC_16K = new MediaSize("ROC_16K", "android", 0x104035f, 7677, 10629);
        public static final MediaSize ROC_8K = new MediaSize("ROC_8K", "android", 0x1040360, 10629, 15354);
        public static final MediaSize UNKNOWN_LANDSCAPE = new MediaSize("UNKNOWN_LANDSCAPE", "android", 0x10403a2, 0x7fffffff, 1);
        public static final MediaSize UNKNOWN_PORTRAIT = new MediaSize("UNKNOWN_PORTRAIT", "android", 0x10403a3, 1, 0x7fffffff);
        private static final Map sIdToMediaSizeMap = new ArrayMap();
        private final int mHeightMils;
        private final String mId;
        public final String mLabel;
        public final int mLabelResId;
        public final String mPackageName;
        private final int mWidthMils;


        public MediaSize(String s, String s1, int i, int j)
        {
            this(s, s1, null, i, j, 0);
        }

        public MediaSize(String s, String s1, int i, int j, int k)
        {
            this(s, null, s1, j, k, i);
            sIdToMediaSizeMap.put(mId, this);
        }

        public MediaSize(String s, String s1, String s2, int i, int j, int k)
        {
            mPackageName = s2;
            mId = (String)Preconditions.checkStringNotEmpty(s, "id cannot be empty.");
            mLabelResId = k;
            mWidthMils = Preconditions.checkArgumentPositive(i, "widthMils cannot be less than or equal to zero.");
            mHeightMils = Preconditions.checkArgumentPositive(j, "heightMils cannot be less than or equal to zero.");
            mLabel = s1;
            boolean flag = TextUtils.isEmpty(s1);
            if(!TextUtils.isEmpty(s2) && k != 0)
                i = 1;
            else
                i = 0;
            if((flag ^ true) != i)
                flag = true;
            else
                flag = false;
            Preconditions.checkArgument(flag, "label cannot be empty.");
        }
    }

    public static final class Resolution
    {

        static Resolution createFromParcel(Parcel parcel)
        {
            return new Resolution(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt());
        }

        public boolean equals(Object obj)
        {
            if(this == obj)
                return true;
            if(obj == null)
                return false;
            if(getClass() != obj.getClass())
                return false;
            obj = (Resolution)obj;
            if(mHorizontalDpi != ((Resolution) (obj)).mHorizontalDpi)
                return false;
            return mVerticalDpi == ((Resolution) (obj)).mVerticalDpi;
        }

        public int getHorizontalDpi()
        {
            return mHorizontalDpi;
        }

        public String getId()
        {
            return mId;
        }

        public String getLabel()
        {
            return mLabel;
        }

        public int getVerticalDpi()
        {
            return mVerticalDpi;
        }

        public int hashCode()
        {
            return (mHorizontalDpi + 31) * 31 + mVerticalDpi;
        }

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("Resolution{");
            stringbuilder.append("id: ").append(mId);
            stringbuilder.append(", label: ").append(mLabel);
            stringbuilder.append(", horizontalDpi: ").append(mHorizontalDpi);
            stringbuilder.append(", verticalDpi: ").append(mVerticalDpi);
            stringbuilder.append("}");
            return stringbuilder.toString();
        }

        void writeToParcel(Parcel parcel)
        {
            parcel.writeString(mId);
            parcel.writeString(mLabel);
            parcel.writeInt(mHorizontalDpi);
            parcel.writeInt(mVerticalDpi);
        }

        private final int mHorizontalDpi;
        private final String mId;
        private final String mLabel;
        private final int mVerticalDpi;

        public Resolution(String s, String s1, int i, int j)
        {
            if(TextUtils.isEmpty(s))
                throw new IllegalArgumentException("id cannot be empty.");
            if(TextUtils.isEmpty(s1))
                throw new IllegalArgumentException("label cannot be empty.");
            if(i <= 0)
                throw new IllegalArgumentException("horizontalDpi cannot be less than or equal to zero.");
            if(j <= 0)
            {
                throw new IllegalArgumentException("verticalDpi cannot be less than or equal to zero.");
            } else
            {
                mId = s;
                mLabel = s1;
                mHorizontalDpi = i;
                mVerticalDpi = j;
                return;
            }
        }
    }


    PrintAttributes()
    {
    }

    private PrintAttributes(Parcel parcel)
    {
        Object obj = null;
        super();
        Object obj1;
        if(parcel.readInt() == 1)
            obj1 = MediaSize.createFromParcel(parcel);
        else
            obj1 = null;
        mMediaSize = ((MediaSize) (obj1));
        if(parcel.readInt() == 1)
            obj1 = Resolution.createFromParcel(parcel);
        else
            obj1 = null;
        mResolution = ((Resolution) (obj1));
        obj1 = obj;
        if(parcel.readInt() == 1)
            obj1 = Margins.createFromParcel(parcel);
        mMinMargins = ((Margins) (obj1));
        mColorMode = parcel.readInt();
        if(mColorMode != 0)
            enforceValidColorMode(mColorMode);
        mDuplexMode = parcel.readInt();
        if(mDuplexMode != 0)
            enforceValidDuplexMode(mDuplexMode);
    }

    PrintAttributes(Parcel parcel, PrintAttributes printattributes)
    {
        this(parcel);
    }

    static String colorModeToString(int i)
    {
        switch(i)
        {
        default:
            return "COLOR_MODE_UNKNOWN";

        case 1: // '\001'
            return "COLOR_MODE_MONOCHROME";

        case 2: // '\002'
            return "COLOR_MODE_COLOR";
        }
    }

    static String duplexModeToString(int i)
    {
        switch(i)
        {
        case 3: // '\003'
        default:
            return "DUPLEX_MODE_UNKNOWN";

        case 1: // '\001'
            return "DUPLEX_MODE_NONE";

        case 2: // '\002'
            return "DUPLEX_MODE_LONG_EDGE";

        case 4: // '\004'
            return "DUPLEX_MODE_SHORT_EDGE";
        }
    }

    static void enforceValidColorMode(int i)
    {
        if((i & 3) == 0 || Integer.bitCount(i) != 1)
            throw new IllegalArgumentException((new StringBuilder()).append("invalid color mode: ").append(i).toString());
        else
            return;
    }

    static void enforceValidDuplexMode(int i)
    {
        if((i & 7) == 0 || Integer.bitCount(i) != 1)
            throw new IllegalArgumentException((new StringBuilder()).append("invalid duplex mode: ").append(i).toString());
        else
            return;
    }

    public PrintAttributes asLandscape()
    {
        if(!isPortrait())
        {
            return this;
        } else
        {
            PrintAttributes printattributes = new PrintAttributes();
            printattributes.setMediaSize(getMediaSize().asLandscape());
            Resolution resolution = getResolution();
            printattributes.setResolution(new Resolution(resolution.getId(), resolution.getLabel(), resolution.getVerticalDpi(), resolution.getHorizontalDpi()));
            printattributes.setMinMargins(getMinMargins());
            printattributes.setColorMode(getColorMode());
            printattributes.setDuplexMode(getDuplexMode());
            return printattributes;
        }
    }

    public PrintAttributes asPortrait()
    {
        if(isPortrait())
        {
            return this;
        } else
        {
            PrintAttributes printattributes = new PrintAttributes();
            printattributes.setMediaSize(getMediaSize().asPortrait());
            Resolution resolution = getResolution();
            printattributes.setResolution(new Resolution(resolution.getId(), resolution.getLabel(), resolution.getVerticalDpi(), resolution.getHorizontalDpi()));
            printattributes.setMinMargins(getMinMargins());
            printattributes.setColorMode(getColorMode());
            printattributes.setDuplexMode(getDuplexMode());
            return printattributes;
        }
    }

    public void clear()
    {
        mMediaSize = null;
        mResolution = null;
        mMinMargins = null;
        mColorMode = 0;
        mDuplexMode = 0;
    }

    public void copyFrom(PrintAttributes printattributes)
    {
        mMediaSize = printattributes.mMediaSize;
        mResolution = printattributes.mResolution;
        mMinMargins = printattributes.mMinMargins;
        mColorMode = printattributes.mColorMode;
        mDuplexMode = printattributes.mDuplexMode;
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
        obj = (PrintAttributes)obj;
        if(mColorMode != ((PrintAttributes) (obj)).mColorMode)
            return false;
        if(mDuplexMode != ((PrintAttributes) (obj)).mDuplexMode)
            return false;
        if(mMinMargins == null)
        {
            if(((PrintAttributes) (obj)).mMinMargins != null)
                return false;
        } else
        if(!mMinMargins.equals(((PrintAttributes) (obj)).mMinMargins))
            return false;
        if(mMediaSize == null)
        {
            if(((PrintAttributes) (obj)).mMediaSize != null)
                return false;
        } else
        if(!mMediaSize.equals(((PrintAttributes) (obj)).mMediaSize))
            return false;
        if(mResolution == null)
        {
            if(((PrintAttributes) (obj)).mResolution != null)
                return false;
        } else
        if(!mResolution.equals(((PrintAttributes) (obj)).mResolution))
            return false;
        return true;
    }

    public int getColorMode()
    {
        return mColorMode;
    }

    public int getDuplexMode()
    {
        return mDuplexMode;
    }

    public MediaSize getMediaSize()
    {
        return mMediaSize;
    }

    public Margins getMinMargins()
    {
        return mMinMargins;
    }

    public Resolution getResolution()
    {
        return mResolution;
    }

    public int hashCode()
    {
        int i = 0;
        int j = mColorMode;
        int k = mDuplexMode;
        int l;
        int i1;
        if(mMinMargins == null)
            l = 0;
        else
            l = mMinMargins.hashCode();
        if(mMediaSize == null)
            i1 = 0;
        else
            i1 = mMediaSize.hashCode();
        if(mResolution != null)
            i = mResolution.hashCode();
        return ((((j + 31) * 31 + k) * 31 + l) * 31 + i1) * 31 + i;
    }

    public boolean isPortrait()
    {
        return mMediaSize.isPortrait();
    }

    public void setColorMode(int i)
    {
        enforceValidColorMode(i);
        mColorMode = i;
    }

    public void setDuplexMode(int i)
    {
        enforceValidDuplexMode(i);
        mDuplexMode = i;
    }

    public void setMediaSize(MediaSize mediasize)
    {
        mMediaSize = mediasize;
    }

    public void setMinMargins(Margins margins)
    {
        mMinMargins = margins;
    }

    public void setResolution(Resolution resolution)
    {
        mResolution = resolution;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("PrintAttributes{");
        stringbuilder.append("mediaSize: ").append(mMediaSize);
        if(mMediaSize != null)
        {
            StringBuilder stringbuilder1 = stringbuilder.append(", orientation: ");
            String s;
            if(mMediaSize.isPortrait())
                s = "portrait";
            else
                s = "landscape";
            stringbuilder1.append(s);
        } else
        {
            stringbuilder.append(", orientation: ").append("null");
        }
        stringbuilder.append(", resolution: ").append(mResolution);
        stringbuilder.append(", minMargins: ").append(mMinMargins);
        stringbuilder.append(", colorMode: ").append(colorModeToString(mColorMode));
        stringbuilder.append(", duplexMode: ").append(duplexModeToString(mDuplexMode));
        stringbuilder.append("}");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        if(mMediaSize != null)
        {
            parcel.writeInt(1);
            mMediaSize.writeToParcel(parcel);
        } else
        {
            parcel.writeInt(0);
        }
        if(mResolution != null)
        {
            parcel.writeInt(1);
            mResolution.writeToParcel(parcel);
        } else
        {
            parcel.writeInt(0);
        }
        if(mMinMargins != null)
        {
            parcel.writeInt(1);
            mMinMargins.writeToParcel(parcel);
        } else
        {
            parcel.writeInt(0);
        }
        parcel.writeInt(mColorMode);
        parcel.writeInt(mDuplexMode);
    }

    public static final int COLOR_MODE_COLOR = 2;
    public static final int COLOR_MODE_MONOCHROME = 1;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public PrintAttributes createFromParcel(Parcel parcel)
        {
            return new PrintAttributes(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PrintAttributes[] newArray(int i)
        {
            return new PrintAttributes[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int DUPLEX_MODE_LONG_EDGE = 2;
    public static final int DUPLEX_MODE_NONE = 1;
    public static final int DUPLEX_MODE_SHORT_EDGE = 4;
    private static final int VALID_COLOR_MODES = 3;
    private static final int VALID_DUPLEX_MODES = 7;
    private int mColorMode;
    private int mDuplexMode;
    private MediaSize mMediaSize;
    private Margins mMinMargins;
    private Resolution mResolution;

}
