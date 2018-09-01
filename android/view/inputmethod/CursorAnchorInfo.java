// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.inputmethod;

import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.SpannedString;
import android.text.TextUtils;
import java.util.Arrays;
import java.util.Objects;

// Referenced classes of package android.view.inputmethod:
//            SparseRectFArray

public final class CursorAnchorInfo
    implements Parcelable
{
    public static final class Builder
    {

        static SparseRectFArray.SparseRectFArrayBuilder _2D_get0(Builder builder)
        {
            return builder.mCharacterBoundsArrayBuilder;
        }

        static CharSequence _2D_get1(Builder builder)
        {
            return builder.mComposingText;
        }

        static int _2D_get10(Builder builder)
        {
            return builder.mSelectionEnd;
        }

        static int _2D_get11(Builder builder)
        {
            return builder.mSelectionStart;
        }

        static int _2D_get2(Builder builder)
        {
            return builder.mComposingTextStart;
        }

        static float _2D_get3(Builder builder)
        {
            return builder.mInsertionMarkerBaseline;
        }

        static float _2D_get4(Builder builder)
        {
            return builder.mInsertionMarkerBottom;
        }

        static int _2D_get5(Builder builder)
        {
            return builder.mInsertionMarkerFlags;
        }

        static float _2D_get6(Builder builder)
        {
            return builder.mInsertionMarkerHorizontal;
        }

        static float _2D_get7(Builder builder)
        {
            return builder.mInsertionMarkerTop;
        }

        static boolean _2D_get8(Builder builder)
        {
            return builder.mMatrixInitialized;
        }

        static float[] _2D_get9(Builder builder)
        {
            return builder.mMatrixValues;
        }

        public Builder addCharacterBounds(int i, float f, float f1, float f2, float f3, int j)
        {
            if(i < 0)
                throw new IllegalArgumentException("index must not be a negative integer.");
            if(mCharacterBoundsArrayBuilder == null)
                mCharacterBoundsArrayBuilder = new SparseRectFArray.SparseRectFArrayBuilder();
            mCharacterBoundsArrayBuilder.append(i, f, f1, f2, f3, j);
            return this;
        }

        public CursorAnchorInfo build()
        {
            if(!mMatrixInitialized)
            {
                boolean flag;
                if(mCharacterBoundsArrayBuilder != null)
                    flag = mCharacterBoundsArrayBuilder.isEmpty() ^ true;
                else
                    flag = false;
                if(flag || Float.isNaN(mInsertionMarkerHorizontal) ^ true || Float.isNaN(mInsertionMarkerTop) ^ true || Float.isNaN(mInsertionMarkerBaseline) ^ true || Float.isNaN(mInsertionMarkerBottom) ^ true)
                    throw new IllegalArgumentException("Coordinate transformation matrix is required when positional parameters are specified.");
            }
            return new CursorAnchorInfo(this, null);
        }

        public void reset()
        {
            mSelectionStart = -1;
            mSelectionEnd = -1;
            mComposingTextStart = -1;
            mComposingText = null;
            mInsertionMarkerFlags = 0;
            mInsertionMarkerHorizontal = (0.0F / 0.0F);
            mInsertionMarkerTop = (0.0F / 0.0F);
            mInsertionMarkerBaseline = (0.0F / 0.0F);
            mInsertionMarkerBottom = (0.0F / 0.0F);
            mMatrixInitialized = false;
            if(mCharacterBoundsArrayBuilder != null)
                mCharacterBoundsArrayBuilder.reset();
        }

        public Builder setComposingText(int i, CharSequence charsequence)
        {
            mComposingTextStart = i;
            if(charsequence == null)
                mComposingText = null;
            else
                mComposingText = new SpannedString(charsequence);
            return this;
        }

        public Builder setInsertionMarkerLocation(float f, float f1, float f2, float f3, int i)
        {
            mInsertionMarkerHorizontal = f;
            mInsertionMarkerTop = f1;
            mInsertionMarkerBaseline = f2;
            mInsertionMarkerBottom = f3;
            mInsertionMarkerFlags = i;
            return this;
        }

        public Builder setMatrix(Matrix matrix)
        {
            if(mMatrixValues == null)
                mMatrixValues = new float[9];
            if(matrix == null)
                matrix = Matrix.IDENTITY_MATRIX;
            matrix.getValues(mMatrixValues);
            mMatrixInitialized = true;
            return this;
        }

        public Builder setSelectionRange(int i, int j)
        {
            mSelectionStart = i;
            mSelectionEnd = j;
            return this;
        }

        private SparseRectFArray.SparseRectFArrayBuilder mCharacterBoundsArrayBuilder;
        private CharSequence mComposingText;
        private int mComposingTextStart;
        private float mInsertionMarkerBaseline;
        private float mInsertionMarkerBottom;
        private int mInsertionMarkerFlags;
        private float mInsertionMarkerHorizontal;
        private float mInsertionMarkerTop;
        private boolean mMatrixInitialized;
        private float mMatrixValues[];
        private int mSelectionEnd;
        private int mSelectionStart;

        public Builder()
        {
            mSelectionStart = -1;
            mSelectionEnd = -1;
            mComposingTextStart = -1;
            mComposingText = null;
            mInsertionMarkerHorizontal = (0.0F / 0.0F);
            mInsertionMarkerTop = (0.0F / 0.0F);
            mInsertionMarkerBaseline = (0.0F / 0.0F);
            mInsertionMarkerBottom = (0.0F / 0.0F);
            mInsertionMarkerFlags = 0;
            mCharacterBoundsArrayBuilder = null;
            mMatrixValues = null;
            mMatrixInitialized = false;
        }
    }


    public CursorAnchorInfo(Parcel parcel)
    {
        mHashCode = parcel.readInt();
        mSelectionStart = parcel.readInt();
        mSelectionEnd = parcel.readInt();
        mComposingTextStart = parcel.readInt();
        mComposingText = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        mInsertionMarkerFlags = parcel.readInt();
        mInsertionMarkerHorizontal = parcel.readFloat();
        mInsertionMarkerTop = parcel.readFloat();
        mInsertionMarkerBaseline = parcel.readFloat();
        mInsertionMarkerBottom = parcel.readFloat();
        mCharacterBoundsArray = (SparseRectFArray)parcel.readParcelable(android/view/inputmethod/SparseRectFArray.getClassLoader());
        mMatrixValues = parcel.createFloatArray();
    }

    private CursorAnchorInfo(Builder builder)
    {
        SparseRectFArray sparserectfarray = null;
        super();
        mSelectionStart = Builder._2D_get11(builder);
        mSelectionEnd = Builder._2D_get10(builder);
        mComposingTextStart = Builder._2D_get2(builder);
        mComposingText = Builder._2D_get1(builder);
        mInsertionMarkerFlags = Builder._2D_get5(builder);
        mInsertionMarkerHorizontal = Builder._2D_get6(builder);
        mInsertionMarkerTop = Builder._2D_get7(builder);
        mInsertionMarkerBaseline = Builder._2D_get3(builder);
        mInsertionMarkerBottom = Builder._2D_get4(builder);
        if(Builder._2D_get0(builder) != null)
            sparserectfarray = Builder._2D_get0(builder).build();
        mCharacterBoundsArray = sparserectfarray;
        mMatrixValues = new float[9];
        if(Builder._2D_get8(builder))
            System.arraycopy(Builder._2D_get9(builder), 0, mMatrixValues, 0, 9);
        else
            Matrix.IDENTITY_MATRIX.getValues(mMatrixValues);
        mHashCode = Objects.hashCode(mComposingText) * 31 + Arrays.hashCode(mMatrixValues);
    }

    CursorAnchorInfo(Builder builder, CursorAnchorInfo cursoranchorinfo)
    {
        this(builder);
    }

    private static boolean areSameFloatImpl(float f, float f1)
    {
        boolean flag = true;
        if(Float.isNaN(f) && Float.isNaN(f1))
            return true;
        if(f != f1)
            flag = false;
        return flag;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if(obj == null)
            return false;
        if(this == obj)
            return true;
        if(!(obj instanceof CursorAnchorInfo))
            return false;
        obj = (CursorAnchorInfo)obj;
        if(hashCode() != ((CursorAnchorInfo) (obj)).hashCode())
            return false;
        if(mSelectionStart != ((CursorAnchorInfo) (obj)).mSelectionStart || mSelectionEnd != ((CursorAnchorInfo) (obj)).mSelectionEnd)
            return false;
        if(mInsertionMarkerFlags != ((CursorAnchorInfo) (obj)).mInsertionMarkerFlags || areSameFloatImpl(mInsertionMarkerHorizontal, ((CursorAnchorInfo) (obj)).mInsertionMarkerHorizontal) ^ true || areSameFloatImpl(mInsertionMarkerTop, ((CursorAnchorInfo) (obj)).mInsertionMarkerTop) ^ true || areSameFloatImpl(mInsertionMarkerBaseline, ((CursorAnchorInfo) (obj)).mInsertionMarkerBaseline) ^ true || areSameFloatImpl(mInsertionMarkerBottom, ((CursorAnchorInfo) (obj)).mInsertionMarkerBottom) ^ true)
            return false;
        if(!Objects.equals(mCharacterBoundsArray, ((CursorAnchorInfo) (obj)).mCharacterBoundsArray))
            return false;
        if(mComposingTextStart != ((CursorAnchorInfo) (obj)).mComposingTextStart || Objects.equals(mComposingText, ((CursorAnchorInfo) (obj)).mComposingText) ^ true)
            return false;
        if(mMatrixValues.length != ((CursorAnchorInfo) (obj)).mMatrixValues.length)
            return false;
        for(int i = 0; i < mMatrixValues.length; i++)
            if(mMatrixValues[i] != ((CursorAnchorInfo) (obj)).mMatrixValues[i])
                return false;

        return true;
    }

    public RectF getCharacterBounds(int i)
    {
        if(mCharacterBoundsArray == null)
            return null;
        else
            return mCharacterBoundsArray.get(i);
    }

    public int getCharacterBoundsFlags(int i)
    {
        if(mCharacterBoundsArray == null)
            return 0;
        else
            return mCharacterBoundsArray.getFlags(i, 0);
    }

    public CharSequence getComposingText()
    {
        return mComposingText;
    }

    public int getComposingTextStart()
    {
        return mComposingTextStart;
    }

    public float getInsertionMarkerBaseline()
    {
        return mInsertionMarkerBaseline;
    }

    public float getInsertionMarkerBottom()
    {
        return mInsertionMarkerBottom;
    }

    public int getInsertionMarkerFlags()
    {
        return mInsertionMarkerFlags;
    }

    public float getInsertionMarkerHorizontal()
    {
        return mInsertionMarkerHorizontal;
    }

    public float getInsertionMarkerTop()
    {
        return mInsertionMarkerTop;
    }

    public Matrix getMatrix()
    {
        Matrix matrix = new Matrix();
        matrix.setValues(mMatrixValues);
        return matrix;
    }

    public int getSelectionEnd()
    {
        return mSelectionEnd;
    }

    public int getSelectionStart()
    {
        return mSelectionStart;
    }

    public int hashCode()
    {
        return mHashCode;
    }

    public String toString()
    {
        return (new StringBuilder()).append("CursorAnchorInfo{mHashCode=").append(mHashCode).append(" mSelection=").append(mSelectionStart).append(",").append(mSelectionEnd).append(" mComposingTextStart=").append(mComposingTextStart).append(" mComposingText=").append(Objects.toString(mComposingText)).append(" mInsertionMarkerFlags=").append(mInsertionMarkerFlags).append(" mInsertionMarkerHorizontal=").append(mInsertionMarkerHorizontal).append(" mInsertionMarkerTop=").append(mInsertionMarkerTop).append(" mInsertionMarkerBaseline=").append(mInsertionMarkerBaseline).append(" mInsertionMarkerBottom=").append(mInsertionMarkerBottom).append(" mCharacterBoundsArray=").append(Objects.toString(mCharacterBoundsArray)).append(" mMatrix=").append(Arrays.toString(mMatrixValues)).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mHashCode);
        parcel.writeInt(mSelectionStart);
        parcel.writeInt(mSelectionEnd);
        parcel.writeInt(mComposingTextStart);
        TextUtils.writeToParcel(mComposingText, parcel, i);
        parcel.writeInt(mInsertionMarkerFlags);
        parcel.writeFloat(mInsertionMarkerHorizontal);
        parcel.writeFloat(mInsertionMarkerTop);
        parcel.writeFloat(mInsertionMarkerBaseline);
        parcel.writeFloat(mInsertionMarkerBottom);
        parcel.writeParcelable(mCharacterBoundsArray, i);
        parcel.writeFloatArray(mMatrixValues);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public CursorAnchorInfo createFromParcel(Parcel parcel)
        {
            return new CursorAnchorInfo(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public CursorAnchorInfo[] newArray(int i)
        {
            return new CursorAnchorInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int FLAG_HAS_INVISIBLE_REGION = 2;
    public static final int FLAG_HAS_VISIBLE_REGION = 1;
    public static final int FLAG_IS_RTL = 4;
    private final SparseRectFArray mCharacterBoundsArray;
    private final CharSequence mComposingText;
    private final int mComposingTextStart;
    private final int mHashCode;
    private final float mInsertionMarkerBaseline;
    private final float mInsertionMarkerBottom;
    private final int mInsertionMarkerFlags;
    private final float mInsertionMarkerHorizontal;
    private final float mInsertionMarkerTop;
    private final float mMatrixValues[];
    private final int mSelectionEnd;
    private final int mSelectionStart;

}
