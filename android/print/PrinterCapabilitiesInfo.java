// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.print;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.Preconditions;
import java.util.*;
import java.util.function.IntConsumer;

// Referenced classes of package android.print:
//            PrintAttributes, PrinterId

public final class PrinterCapabilitiesInfo
    implements Parcelable
{
    public static final class Builder
    {

        static void lambda$_2D_android_print_PrinterCapabilitiesInfo$Builder_18342(int i)
        {
            PrintAttributes.enforceValidColorMode(i);
        }

        static void lambda$_2D_android_print_PrinterCapabilitiesInfo$Builder_19452(int i)
        {
            PrintAttributes.enforceValidDuplexMode(i);
        }

        private void throwIfDefaultAlreadySpecified(int i)
        {
            if(PrinterCapabilitiesInfo._2D_get1(mPrototype)[i] != -1)
                throw new IllegalArgumentException("Default already specified.");
            else
                return;
        }

        public Builder addMediaSize(PrintAttributes.MediaSize mediasize, boolean flag)
        {
            if(PrinterCapabilitiesInfo._2D_get3(mPrototype) == null)
                PrinterCapabilitiesInfo._2D_set2(mPrototype, new ArrayList());
            int i = PrinterCapabilitiesInfo._2D_get3(mPrototype).size();
            PrinterCapabilitiesInfo._2D_get3(mPrototype).add(mediasize);
            if(flag)
            {
                throwIfDefaultAlreadySpecified(0);
                PrinterCapabilitiesInfo._2D_get1(mPrototype)[0] = i;
            }
            return this;
        }

        public Builder addResolution(PrintAttributes.Resolution resolution, boolean flag)
        {
            if(PrinterCapabilitiesInfo._2D_get5(mPrototype) == null)
                PrinterCapabilitiesInfo._2D_set4(mPrototype, new ArrayList());
            int i = PrinterCapabilitiesInfo._2D_get5(mPrototype).size();
            PrinterCapabilitiesInfo._2D_get5(mPrototype).add(resolution);
            if(flag)
            {
                throwIfDefaultAlreadySpecified(1);
                PrinterCapabilitiesInfo._2D_get1(mPrototype)[1] = i;
            }
            return this;
        }

        public PrinterCapabilitiesInfo build()
        {
            if(PrinterCapabilitiesInfo._2D_get3(mPrototype) == null || PrinterCapabilitiesInfo._2D_get3(mPrototype).isEmpty())
                throw new IllegalStateException("No media size specified.");
            if(PrinterCapabilitiesInfo._2D_get1(mPrototype)[0] == -1)
                throw new IllegalStateException("No default media size specified.");
            if(PrinterCapabilitiesInfo._2D_get5(mPrototype) == null || PrinterCapabilitiesInfo._2D_get5(mPrototype).isEmpty())
                throw new IllegalStateException("No resolution specified.");
            if(PrinterCapabilitiesInfo._2D_get1(mPrototype)[1] == -1)
                throw new IllegalStateException("No default resolution specified.");
            if(PrinterCapabilitiesInfo._2D_get0(mPrototype) == 0)
                throw new IllegalStateException("No color mode specified.");
            if(PrinterCapabilitiesInfo._2D_get1(mPrototype)[2] == -1)
                throw new IllegalStateException("No default color mode specified.");
            if(PrinterCapabilitiesInfo._2D_get2(mPrototype) == 0)
                setDuplexModes(1, 1);
            if(PrinterCapabilitiesInfo._2D_get4(mPrototype) == null)
                throw new IllegalArgumentException("margins cannot be null");
            else
                return mPrototype;
        }

        public Builder setColorModes(int i, int j)
        {
            PrinterCapabilitiesInfo._2D_wrap0(i, _.Lambda.nZCUMFnU8HXNMZ1DQrWBqUtcQbo.$INST$0);
            PrintAttributes.enforceValidColorMode(j);
            PrinterCapabilitiesInfo._2D_set0(mPrototype, i);
            PrinterCapabilitiesInfo._2D_get1(mPrototype)[2] = j;
            return this;
        }

        public Builder setDuplexModes(int i, int j)
        {
            PrinterCapabilitiesInfo._2D_wrap0(i, _.Lambda.nZCUMFnU8HXNMZ1DQrWBqUtcQbo.$INST$1);
            PrintAttributes.enforceValidDuplexMode(j);
            PrinterCapabilitiesInfo._2D_set1(mPrototype, i);
            PrinterCapabilitiesInfo._2D_get1(mPrototype)[3] = j;
            return this;
        }

        public Builder setMinMargins(PrintAttributes.Margins margins)
        {
            if(margins == null)
            {
                throw new IllegalArgumentException("margins cannot be null");
            } else
            {
                PrinterCapabilitiesInfo._2D_set3(mPrototype, margins);
                return this;
            }
        }

        private final PrinterCapabilitiesInfo mPrototype;

        public Builder(PrinterId printerid)
        {
            if(printerid == null)
            {
                throw new IllegalArgumentException("printerId cannot be null.");
            } else
            {
                mPrototype = new PrinterCapabilitiesInfo();
                return;
            }
        }
    }


    static int _2D_get0(PrinterCapabilitiesInfo printercapabilitiesinfo)
    {
        return printercapabilitiesinfo.mColorModes;
    }

    static int[] _2D_get1(PrinterCapabilitiesInfo printercapabilitiesinfo)
    {
        return printercapabilitiesinfo.mDefaults;
    }

    static int _2D_get2(PrinterCapabilitiesInfo printercapabilitiesinfo)
    {
        return printercapabilitiesinfo.mDuplexModes;
    }

    static List _2D_get3(PrinterCapabilitiesInfo printercapabilitiesinfo)
    {
        return printercapabilitiesinfo.mMediaSizes;
    }

    static PrintAttributes.Margins _2D_get4(PrinterCapabilitiesInfo printercapabilitiesinfo)
    {
        return printercapabilitiesinfo.mMinMargins;
    }

    static List _2D_get5(PrinterCapabilitiesInfo printercapabilitiesinfo)
    {
        return printercapabilitiesinfo.mResolutions;
    }

    static int _2D_set0(PrinterCapabilitiesInfo printercapabilitiesinfo, int i)
    {
        printercapabilitiesinfo.mColorModes = i;
        return i;
    }

    static int _2D_set1(PrinterCapabilitiesInfo printercapabilitiesinfo, int i)
    {
        printercapabilitiesinfo.mDuplexModes = i;
        return i;
    }

    static List _2D_set2(PrinterCapabilitiesInfo printercapabilitiesinfo, List list)
    {
        printercapabilitiesinfo.mMediaSizes = list;
        return list;
    }

    static PrintAttributes.Margins _2D_set3(PrinterCapabilitiesInfo printercapabilitiesinfo, PrintAttributes.Margins margins)
    {
        printercapabilitiesinfo.mMinMargins = margins;
        return margins;
    }

    static List _2D_set4(PrinterCapabilitiesInfo printercapabilitiesinfo, List list)
    {
        printercapabilitiesinfo.mResolutions = list;
        return list;
    }

    static void _2D_wrap0(int i, IntConsumer intconsumer)
    {
        enforceValidMask(i, intconsumer);
    }

    public PrinterCapabilitiesInfo()
    {
        mMinMargins = DEFAULT_MARGINS;
        mDefaults = new int[4];
        Arrays.fill(mDefaults, -1);
    }

    private PrinterCapabilitiesInfo(Parcel parcel)
    {
        boolean flag = true;
        super();
        mMinMargins = DEFAULT_MARGINS;
        mDefaults = new int[4];
        mMinMargins = (PrintAttributes.Margins)Preconditions.checkNotNull(readMargins(parcel));
        readMediaSizes(parcel);
        readResolutions(parcel);
        mColorModes = parcel.readInt();
        enforceValidMask(mColorModes, _.Lambda.nZCUMFnU8HXNMZ1DQrWBqUtcQbo.$INST$2);
        mDuplexModes = parcel.readInt();
        enforceValidMask(mDuplexModes, _.Lambda.nZCUMFnU8HXNMZ1DQrWBqUtcQbo.$INST$3);
        readDefaults(parcel);
        boolean flag1;
        if(mMediaSizes.size() > mDefaults[0])
            flag1 = true;
        else
            flag1 = false;
        Preconditions.checkArgument(flag1);
        if(mResolutions.size() > mDefaults[1])
            flag1 = flag;
        else
            flag1 = false;
        Preconditions.checkArgument(flag1);
    }

    PrinterCapabilitiesInfo(Parcel parcel, PrinterCapabilitiesInfo printercapabilitiesinfo)
    {
        this(parcel);
    }

    public PrinterCapabilitiesInfo(PrinterCapabilitiesInfo printercapabilitiesinfo)
    {
        mMinMargins = DEFAULT_MARGINS;
        mDefaults = new int[4];
        copyFrom(printercapabilitiesinfo);
    }

    private String colorModesToString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append('[');
        for(int i = mColorModes; i != 0;)
        {
            int j = 1 << Integer.numberOfTrailingZeros(i);
            i &= j;
            if(stringbuilder.length() > 1)
                stringbuilder.append(", ");
            stringbuilder.append(PrintAttributes.colorModeToString(j));
        }

        stringbuilder.append(']');
        return stringbuilder.toString();
    }

    private String duplexModesToString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append('[');
        for(int i = mDuplexModes; i != 0;)
        {
            int j = 1 << Integer.numberOfTrailingZeros(i);
            i &= j;
            if(stringbuilder.length() > 1)
                stringbuilder.append(", ");
            stringbuilder.append(PrintAttributes.duplexModeToString(j));
        }

        stringbuilder.append(']');
        return stringbuilder.toString();
    }

    private static void enforceValidMask(int i, IntConsumer intconsumer)
    {
        while(i > 0) 
        {
            int j = 1 << Integer.numberOfTrailingZeros(i);
            i &= j;
            intconsumer.accept(j);
        }
    }

    static void lambda$_2D_android_print_PrinterCapabilitiesInfo_6870(int i)
    {
        PrintAttributes.enforceValidColorMode(i);
    }

    static void lambda$_2D_android_print_PrinterCapabilitiesInfo_7037(int i)
    {
        PrintAttributes.enforceValidDuplexMode(i);
    }

    private void readDefaults(Parcel parcel)
    {
        int i = parcel.readInt();
        for(int j = 0; j < i; j++)
            mDefaults[j] = parcel.readInt();

    }

    private PrintAttributes.Margins readMargins(Parcel parcel)
    {
        if(parcel.readInt() == 1)
            parcel = PrintAttributes.Margins.createFromParcel(parcel);
        else
            parcel = null;
        return parcel;
    }

    private void readMediaSizes(Parcel parcel)
    {
        int i = parcel.readInt();
        if(i > 0 && mMediaSizes == null)
            mMediaSizes = new ArrayList();
        for(int j = 0; j < i; j++)
            mMediaSizes.add(PrintAttributes.MediaSize.createFromParcel(parcel));

    }

    private void readResolutions(Parcel parcel)
    {
        int i = parcel.readInt();
        if(i > 0 && mResolutions == null)
            mResolutions = new ArrayList();
        for(int j = 0; j < i; j++)
            mResolutions.add(PrintAttributes.Resolution.createFromParcel(parcel));

    }

    private void writeDefaults(Parcel parcel)
    {
        int i = mDefaults.length;
        parcel.writeInt(i);
        for(int j = 0; j < i; j++)
            parcel.writeInt(mDefaults[j]);

    }

    private void writeMargins(PrintAttributes.Margins margins, Parcel parcel)
    {
        if(margins == null)
        {
            parcel.writeInt(0);
        } else
        {
            parcel.writeInt(1);
            margins.writeToParcel(parcel);
        }
    }

    private void writeMediaSizes(Parcel parcel)
    {
        if(mMediaSizes == null)
        {
            parcel.writeInt(0);
            return;
        }
        int i = mMediaSizes.size();
        parcel.writeInt(i);
        for(int j = 0; j < i; j++)
            ((PrintAttributes.MediaSize)mMediaSizes.get(j)).writeToParcel(parcel);

    }

    private void writeResolutions(Parcel parcel)
    {
        if(mResolutions == null)
        {
            parcel.writeInt(0);
            return;
        }
        int i = mResolutions.size();
        parcel.writeInt(i);
        for(int j = 0; j < i; j++)
            ((PrintAttributes.Resolution)mResolutions.get(j)).writeToParcel(parcel);

    }

    public void copyFrom(PrinterCapabilitiesInfo printercapabilitiesinfo)
    {
        if(this == printercapabilitiesinfo)
            return;
        mMinMargins = printercapabilitiesinfo.mMinMargins;
        int i;
        if(printercapabilitiesinfo.mMediaSizes != null)
        {
            if(mMediaSizes != null)
            {
                mMediaSizes.clear();
                mMediaSizes.addAll(printercapabilitiesinfo.mMediaSizes);
            } else
            {
                mMediaSizes = new ArrayList(printercapabilitiesinfo.mMediaSizes);
            }
        } else
        {
            mMediaSizes = null;
        }
        if(printercapabilitiesinfo.mResolutions != null)
        {
            if(mResolutions != null)
            {
                mResolutions.clear();
                mResolutions.addAll(printercapabilitiesinfo.mResolutions);
            } else
            {
                mResolutions = new ArrayList(printercapabilitiesinfo.mResolutions);
            }
        } else
        {
            mResolutions = null;
        }
        mColorModes = printercapabilitiesinfo.mColorModes;
        mDuplexModes = printercapabilitiesinfo.mDuplexModes;
        i = printercapabilitiesinfo.mDefaults.length;
        for(int j = 0; j < i; j++)
            mDefaults[j] = printercapabilitiesinfo.mDefaults[j];

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
        obj = (PrinterCapabilitiesInfo)obj;
        if(mMinMargins == null)
        {
            if(((PrinterCapabilitiesInfo) (obj)).mMinMargins != null)
                return false;
        } else
        if(!mMinMargins.equals(((PrinterCapabilitiesInfo) (obj)).mMinMargins))
            return false;
        if(mMediaSizes == null)
        {
            if(((PrinterCapabilitiesInfo) (obj)).mMediaSizes != null)
                return false;
        } else
        if(!mMediaSizes.equals(((PrinterCapabilitiesInfo) (obj)).mMediaSizes))
            return false;
        if(mResolutions == null)
        {
            if(((PrinterCapabilitiesInfo) (obj)).mResolutions != null)
                return false;
        } else
        if(!mResolutions.equals(((PrinterCapabilitiesInfo) (obj)).mResolutions))
            return false;
        if(mColorModes != ((PrinterCapabilitiesInfo) (obj)).mColorModes)
            return false;
        if(mDuplexModes != ((PrinterCapabilitiesInfo) (obj)).mDuplexModes)
            return false;
        return Arrays.equals(mDefaults, ((PrinterCapabilitiesInfo) (obj)).mDefaults);
    }

    public int getColorModes()
    {
        return mColorModes;
    }

    public PrintAttributes getDefaults()
    {
        PrintAttributes.Builder builder = new PrintAttributes.Builder();
        builder.setMinMargins(mMinMargins);
        int i = mDefaults[0];
        if(i >= 0)
            builder.setMediaSize((PrintAttributes.MediaSize)mMediaSizes.get(i));
        i = mDefaults[1];
        if(i >= 0)
            builder.setResolution((PrintAttributes.Resolution)mResolutions.get(i));
        i = mDefaults[2];
        if(i > 0)
            builder.setColorMode(i);
        i = mDefaults[3];
        if(i > 0)
            builder.setDuplexMode(i);
        return builder.build();
    }

    public int getDuplexModes()
    {
        return mDuplexModes;
    }

    public List getMediaSizes()
    {
        return Collections.unmodifiableList(mMediaSizes);
    }

    public PrintAttributes.Margins getMinMargins()
    {
        return mMinMargins;
    }

    public List getResolutions()
    {
        return Collections.unmodifiableList(mResolutions);
    }

    public int hashCode()
    {
        int i = 0;
        int j;
        int k;
        if(mMinMargins == null)
            j = 0;
        else
            j = mMinMargins.hashCode();
        if(mMediaSizes == null)
            k = 0;
        else
            k = mMediaSizes.hashCode();
        if(mResolutions != null)
            i = mResolutions.hashCode();
        return (((((j + 31) * 31 + k) * 31 + i) * 31 + mColorModes) * 31 + mDuplexModes) * 31 + Arrays.hashCode(mDefaults);
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("PrinterInfo{");
        stringbuilder.append("minMargins=").append(mMinMargins);
        stringbuilder.append(", mediaSizes=").append(mMediaSizes);
        stringbuilder.append(", resolutions=").append(mResolutions);
        stringbuilder.append(", colorModes=").append(colorModesToString());
        stringbuilder.append(", duplexModes=").append(duplexModesToString());
        stringbuilder.append("\"}");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        writeMargins(mMinMargins, parcel);
        writeMediaSizes(parcel);
        writeResolutions(parcel);
        parcel.writeInt(mColorModes);
        parcel.writeInt(mDuplexModes);
        writeDefaults(parcel);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public PrinterCapabilitiesInfo createFromParcel(Parcel parcel)
        {
            return new PrinterCapabilitiesInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PrinterCapabilitiesInfo[] newArray(int i)
        {
            return new PrinterCapabilitiesInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final PrintAttributes.Margins DEFAULT_MARGINS = new PrintAttributes.Margins(0, 0, 0, 0);
    public static final int DEFAULT_UNDEFINED = -1;
    private static final int PROPERTY_COLOR_MODE = 2;
    private static final int PROPERTY_COUNT = 4;
    private static final int PROPERTY_DUPLEX_MODE = 3;
    private static final int PROPERTY_MEDIA_SIZE = 0;
    private static final int PROPERTY_RESOLUTION = 1;
    private int mColorModes;
    private final int mDefaults[];
    private int mDuplexModes;
    private List mMediaSizes;
    private PrintAttributes.Margins mMinMargins;
    private List mResolutions;

}
