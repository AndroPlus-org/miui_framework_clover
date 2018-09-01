// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.inputmethod;

import android.graphics.RectF;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

public final class SparseRectFArray
    implements Parcelable
{
    public static final class SparseRectFArrayBuilder
    {

        static float[] _2D_get0(SparseRectFArrayBuilder sparserectfarraybuilder)
        {
            return sparserectfarraybuilder.mCoordinates;
        }

        static int _2D_get1(SparseRectFArrayBuilder sparserectfarraybuilder)
        {
            return sparserectfarraybuilder.mCount;
        }

        static int[] _2D_get2(SparseRectFArrayBuilder sparserectfarraybuilder)
        {
            return sparserectfarraybuilder.mFlagsArray;
        }

        static int[] _2D_get3(SparseRectFArrayBuilder sparserectfarraybuilder)
        {
            return sparserectfarraybuilder.mKeys;
        }

        private void checkIndex(int i)
        {
            if(mCount == 0)
                return;
            if(mKeys[mCount - 1] >= i)
                throw new IllegalArgumentException("key must be greater than all existing keys.");
            else
                return;
        }

        private void ensureBufferSize()
        {
            if(mKeys == null)
                mKeys = new int[INITIAL_SIZE];
            if(mCoordinates == null)
                mCoordinates = new float[INITIAL_SIZE * 4];
            if(mFlagsArray == null)
                mFlagsArray = new int[INITIAL_SIZE];
            int i = mCount + 1;
            if(mKeys.length <= i)
            {
                int ai[] = new int[i * 2];
                System.arraycopy(mKeys, 0, ai, 0, mCount);
                mKeys = ai;
            }
            int j = (mCount + 1) * 4;
            if(mCoordinates.length <= j)
            {
                float af[] = new float[j * 2];
                System.arraycopy(mCoordinates, 0, af, 0, mCount * 4);
                mCoordinates = af;
            }
            if(mFlagsArray.length <= i)
            {
                int ai1[] = new int[i * 2];
                System.arraycopy(mFlagsArray, 0, ai1, 0, mCount);
                mFlagsArray = ai1;
            }
        }

        public SparseRectFArrayBuilder append(int i, float f, float f1, float f2, float f3, int j)
        {
            checkIndex(i);
            ensureBufferSize();
            int k = mCount * 4;
            mCoordinates[k + 0] = f;
            mCoordinates[k + 1] = f1;
            mCoordinates[k + 2] = f2;
            mCoordinates[k + 3] = f3;
            k = mCount;
            mFlagsArray[k] = j;
            mKeys[mCount] = i;
            mCount = mCount + 1;
            return this;
        }

        public SparseRectFArray build()
        {
            return new SparseRectFArray(this, null);
        }

        public boolean isEmpty()
        {
            boolean flag = false;
            if(mCount <= 0)
                flag = true;
            return flag;
        }

        public void reset()
        {
            if(mCount == 0)
            {
                mKeys = null;
                mCoordinates = null;
                mFlagsArray = null;
            }
            mCount = 0;
        }

        private static int INITIAL_SIZE = 16;
        private float mCoordinates[];
        private int mCount;
        private int mFlagsArray[];
        private int mKeys[];


        public SparseRectFArrayBuilder()
        {
            mCount = 0;
            mKeys = null;
            mCoordinates = null;
            mFlagsArray = null;
        }
    }


    public SparseRectFArray(Parcel parcel)
    {
        mKeys = parcel.createIntArray();
        mCoordinates = parcel.createFloatArray();
        mFlagsArray = parcel.createIntArray();
    }

    private SparseRectFArray(SparseRectFArrayBuilder sparserectfarraybuilder)
    {
        if(SparseRectFArrayBuilder._2D_get1(sparserectfarraybuilder) == 0)
        {
            mKeys = null;
            mCoordinates = null;
            mFlagsArray = null;
        } else
        {
            mKeys = new int[SparseRectFArrayBuilder._2D_get1(sparserectfarraybuilder)];
            mCoordinates = new float[SparseRectFArrayBuilder._2D_get1(sparserectfarraybuilder) * 4];
            mFlagsArray = new int[SparseRectFArrayBuilder._2D_get1(sparserectfarraybuilder)];
            System.arraycopy(SparseRectFArrayBuilder._2D_get3(sparserectfarraybuilder), 0, mKeys, 0, SparseRectFArrayBuilder._2D_get1(sparserectfarraybuilder));
            System.arraycopy(SparseRectFArrayBuilder._2D_get0(sparserectfarraybuilder), 0, mCoordinates, 0, SparseRectFArrayBuilder._2D_get1(sparserectfarraybuilder) * 4);
            System.arraycopy(SparseRectFArrayBuilder._2D_get2(sparserectfarraybuilder), 0, mFlagsArray, 0, SparseRectFArrayBuilder._2D_get1(sparserectfarraybuilder));
        }
    }

    SparseRectFArray(SparseRectFArrayBuilder sparserectfarraybuilder, SparseRectFArray sparserectfarray)
    {
        this(sparserectfarraybuilder);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj == null)
            return false;
        if(this == obj)
            return true;
        if(!(obj instanceof SparseRectFArray))
            return false;
        obj = (SparseRectFArray)obj;
        boolean flag1 = flag;
        if(Arrays.equals(mKeys, ((SparseRectFArray) (obj)).mKeys))
        {
            flag1 = flag;
            if(Arrays.equals(mCoordinates, ((SparseRectFArray) (obj)).mCoordinates))
                flag1 = Arrays.equals(mFlagsArray, ((SparseRectFArray) (obj)).mFlagsArray);
        }
        return flag1;
    }

    public RectF get(int i)
    {
        if(mKeys == null)
            return null;
        if(i < 0)
            return null;
        i = Arrays.binarySearch(mKeys, i);
        if(i < 0)
        {
            return null;
        } else
        {
            i *= 4;
            return new RectF(mCoordinates[i], mCoordinates[i + 1], mCoordinates[i + 2], mCoordinates[i + 3]);
        }
    }

    public int getFlags(int i, int j)
    {
        if(mKeys == null)
            return j;
        if(i < 0)
            return j;
        i = Arrays.binarySearch(mKeys, i);
        if(i < 0)
            return j;
        else
            return mFlagsArray[i];
    }

    public int hashCode()
    {
        if(mKeys == null || mKeys.length == 0)
            return 0;
        int i = mKeys.length;
        for(int j = 0; j < 4; j++)
            i = (int)((float)(i * 31) + mCoordinates[j]);

        return i * 31 + mFlagsArray[0];
    }

    public String toString()
    {
        while(mKeys == null || mCoordinates == null || mFlagsArray == null) 
            return "SparseRectFArray{}";
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("SparseRectFArray{");
        for(int i = 0; i < mKeys.length; i++)
        {
            if(i != 0)
                stringbuilder.append(", ");
            int j = i * 4;
            stringbuilder.append(mKeys[i]);
            stringbuilder.append(":[");
            stringbuilder.append(mCoordinates[j + 0]);
            stringbuilder.append(",");
            stringbuilder.append(mCoordinates[j + 1]);
            stringbuilder.append("],[");
            stringbuilder.append(mCoordinates[j + 2]);
            stringbuilder.append(",");
            stringbuilder.append(mCoordinates[j + 3]);
            stringbuilder.append("]:flagsArray=");
            stringbuilder.append(mFlagsArray[i]);
        }

        stringbuilder.append("}");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeIntArray(mKeys);
        parcel.writeFloatArray(mCoordinates);
        parcel.writeIntArray(mFlagsArray);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public SparseRectFArray createFromParcel(Parcel parcel)
        {
            return new SparseRectFArray(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public SparseRectFArray[] newArray(int i)
        {
            return new SparseRectFArray[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final float mCoordinates[];
    private final int mFlagsArray[];
    private final int mKeys[];

}
