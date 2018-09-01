// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.database.Cursor;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import java.util.*;

// Referenced classes of package android.content:
//            ContentProvider, ContentValues, ContentProviderResult, ContentUris, 
//            OperationApplicationException

public class ContentProviderOperation
    implements Parcelable
{
    public static class Builder
    {

        static Integer _2D_get0(Builder builder)
        {
            return builder.mExpectedCount;
        }

        static String _2D_get1(Builder builder)
        {
            return builder.mSelection;
        }

        static String[] _2D_get2(Builder builder)
        {
            return builder.mSelectionArgs;
        }

        static Map _2D_get3(Builder builder)
        {
            return builder.mSelectionArgsBackReferences;
        }

        static int _2D_get4(Builder builder)
        {
            return builder.mType;
        }

        static Uri _2D_get5(Builder builder)
        {
            return builder.mUri;
        }

        static ContentValues _2D_get6(Builder builder)
        {
            return builder.mValues;
        }

        static ContentValues _2D_get7(Builder builder)
        {
            return builder.mValuesBackReferences;
        }

        static boolean _2D_get8(Builder builder)
        {
            return builder.mYieldAllowed;
        }

        public ContentProviderOperation build()
        {
            if(mType == 2 && (mValues == null || mValues.isEmpty()) && (mValuesBackReferences == null || mValuesBackReferences.isEmpty()))
                throw new IllegalArgumentException("Empty values");
            if(mType == 4 && (mValues == null || mValues.isEmpty()) && (mValuesBackReferences == null || mValuesBackReferences.isEmpty()) && mExpectedCount == null)
                throw new IllegalArgumentException("Empty values");
            else
                return new ContentProviderOperation(this, null);
        }

        public Builder withExpectedCount(int i)
        {
            if(mType != 2 && mType != 3 && mType != 4)
            {
                throw new IllegalArgumentException("only updates, deletes, and asserts can have expected counts");
            } else
            {
                mExpectedCount = Integer.valueOf(i);
                return this;
            }
        }

        public Builder withSelection(String s, String as[])
        {
            if(mType != 2 && mType != 3 && mType != 4)
                throw new IllegalArgumentException("only updates, deletes, and asserts can have selections");
            mSelection = s;
            if(as == null)
            {
                mSelectionArgs = null;
            } else
            {
                mSelectionArgs = new String[as.length];
                System.arraycopy(as, 0, mSelectionArgs, 0, as.length);
            }
            return this;
        }

        public Builder withSelectionBackReference(int i, int j)
        {
            if(mType != 2 && mType != 3 && mType != 4)
                throw new IllegalArgumentException("only updates, deletes, and asserts can have selection back-references");
            if(mSelectionArgsBackReferences == null)
                mSelectionArgsBackReferences = new HashMap();
            mSelectionArgsBackReferences.put(Integer.valueOf(i), Integer.valueOf(j));
            return this;
        }

        public Builder withValue(String s, Object obj)
        {
            if(mType != 1 && mType != 2 && mType != 4)
                throw new IllegalArgumentException("only inserts and updates can have values");
            if(mValues == null)
                mValues = new ContentValues();
            if(obj == null)
                mValues.putNull(s);
            else
            if(obj instanceof String)
                mValues.put(s, (String)obj);
            else
            if(obj instanceof Byte)
                mValues.put(s, (Byte)obj);
            else
            if(obj instanceof Short)
                mValues.put(s, (Short)obj);
            else
            if(obj instanceof Integer)
                mValues.put(s, (Integer)obj);
            else
            if(obj instanceof Long)
                mValues.put(s, (Long)obj);
            else
            if(obj instanceof Float)
                mValues.put(s, (Float)obj);
            else
            if(obj instanceof Double)
                mValues.put(s, (Double)obj);
            else
            if(obj instanceof Boolean)
                mValues.put(s, (Boolean)obj);
            else
            if(obj instanceof byte[])
                mValues.put(s, (byte[])obj);
            else
                throw new IllegalArgumentException((new StringBuilder()).append("bad value type: ").append(obj.getClass().getName()).toString());
            return this;
        }

        public Builder withValueBackReference(String s, int i)
        {
            if(mType != 1 && mType != 2 && mType != 4)
                throw new IllegalArgumentException("only inserts, updates, and asserts can have value back-references");
            if(mValuesBackReferences == null)
                mValuesBackReferences = new ContentValues();
            mValuesBackReferences.put(s, Integer.valueOf(i));
            return this;
        }

        public Builder withValueBackReferences(ContentValues contentvalues)
        {
            if(mType != 1 && mType != 2 && mType != 4)
            {
                throw new IllegalArgumentException("only inserts, updates, and asserts can have value back-references");
            } else
            {
                mValuesBackReferences = contentvalues;
                return this;
            }
        }

        public Builder withValues(ContentValues contentvalues)
        {
            if(mType != 1 && mType != 2 && mType != 4)
                throw new IllegalArgumentException("only inserts, updates, and asserts can have values");
            if(mValues == null)
                mValues = new ContentValues();
            mValues.putAll(contentvalues);
            return this;
        }

        public Builder withYieldAllowed(boolean flag)
        {
            mYieldAllowed = flag;
            return this;
        }

        private Integer mExpectedCount;
        private String mSelection;
        private String mSelectionArgs[];
        private Map mSelectionArgsBackReferences;
        private final int mType;
        private final Uri mUri;
        private ContentValues mValues;
        private ContentValues mValuesBackReferences;
        private boolean mYieldAllowed;

        private Builder(int i, Uri uri)
        {
            if(uri == null)
            {
                throw new IllegalArgumentException("uri must not be null");
            } else
            {
                mType = i;
                mUri = uri;
                return;
            }
        }

        Builder(int i, Uri uri, Builder builder)
        {
            this(i, uri);
        }
    }


    private ContentProviderOperation(Builder builder)
    {
        mType = Builder._2D_get4(builder);
        mUri = Builder._2D_get5(builder);
        mValues = Builder._2D_get6(builder);
        mSelection = Builder._2D_get1(builder);
        mSelectionArgs = Builder._2D_get2(builder);
        mExpectedCount = Builder._2D_get0(builder);
        mSelectionArgsBackReferences = Builder._2D_get3(builder);
        mValuesBackReferences = Builder._2D_get7(builder);
        mYieldAllowed = Builder._2D_get8(builder);
    }

    ContentProviderOperation(Builder builder, ContentProviderOperation contentprovideroperation)
    {
        this(builder);
    }

    public ContentProviderOperation(ContentProviderOperation contentprovideroperation, boolean flag)
    {
        mType = contentprovideroperation.mType;
        if(flag)
            mUri = ContentProvider.getUriWithoutUserId(contentprovideroperation.mUri);
        else
            mUri = contentprovideroperation.mUri;
        mValues = contentprovideroperation.mValues;
        mSelection = contentprovideroperation.mSelection;
        mSelectionArgs = contentprovideroperation.mSelectionArgs;
        mExpectedCount = contentprovideroperation.mExpectedCount;
        mSelectionArgsBackReferences = contentprovideroperation.mSelectionArgsBackReferences;
        mValuesBackReferences = contentprovideroperation.mValuesBackReferences;
        mYieldAllowed = contentprovideroperation.mYieldAllowed;
    }

    private ContentProviderOperation(Parcel parcel)
    {
        Object obj = null;
        super();
        mType = parcel.readInt();
        mUri = (Uri)Uri.CREATOR.createFromParcel(parcel);
        Object obj1;
        if(parcel.readInt() != 0)
            obj1 = (ContentValues)ContentValues.CREATOR.createFromParcel(parcel);
        else
            obj1 = null;
        mValues = ((ContentValues) (obj1));
        if(parcel.readInt() != 0)
            obj1 = parcel.readString();
        else
            obj1 = null;
        mSelection = ((String) (obj1));
        if(parcel.readInt() != 0)
            obj1 = parcel.readStringArray();
        else
            obj1 = null;
        mSelectionArgs = ((String []) (obj1));
        if(parcel.readInt() != 0)
            obj1 = Integer.valueOf(parcel.readInt());
        else
            obj1 = null;
        mExpectedCount = ((Integer) (obj1));
        if(parcel.readInt() != 0)
            obj1 = (ContentValues)ContentValues.CREATOR.createFromParcel(parcel);
        else
            obj1 = null;
        mValuesBackReferences = ((ContentValues) (obj1));
        obj1 = obj;
        if(parcel.readInt() != 0)
            obj1 = new HashMap();
        mSelectionArgsBackReferences = ((Map) (obj1));
        if(mSelectionArgsBackReferences != null)
        {
            int i = parcel.readInt();
            for(int j = 0; j < i; j++)
                mSelectionArgsBackReferences.put(Integer.valueOf(parcel.readInt()), Integer.valueOf(parcel.readInt()));

        }
        boolean flag;
        if(parcel.readInt() != 0)
            flag = true;
        else
            flag = false;
        mYieldAllowed = flag;
    }

    ContentProviderOperation(Parcel parcel, ContentProviderOperation contentprovideroperation)
    {
        this(parcel);
    }

    private long backRefToValue(ContentProviderResult acontentproviderresult[], int i, Integer integer)
    {
        if(integer.intValue() >= i)
        {
            Log.e("ContentProviderOperation", toString());
            throw new ArrayIndexOutOfBoundsException((new StringBuilder()).append("asked for back ref ").append(integer).append(" but there are only ").append(i).append(" back refs").toString());
        }
        acontentproviderresult = acontentproviderresult[integer.intValue()];
        long l;
        if(((ContentProviderResult) (acontentproviderresult)).uri != null)
            l = ContentUris.parseId(((ContentProviderResult) (acontentproviderresult)).uri);
        else
            l = ((ContentProviderResult) (acontentproviderresult)).count.intValue();
        return l;
    }

    public static Builder newAssertQuery(Uri uri)
    {
        return new Builder(4, uri, null);
    }

    public static Builder newDelete(Uri uri)
    {
        return new Builder(3, uri, null);
    }

    public static Builder newInsert(Uri uri)
    {
        return new Builder(1, uri, null);
    }

    public static Builder newUpdate(Uri uri)
    {
        return new Builder(2, uri, null);
    }

    public ContentProviderResult apply(ContentProvider contentprovider, ContentProviderResult acontentproviderresult[], int i)
        throws OperationApplicationException
    {
        ContentValues contentvalues;
        String as[];
        contentvalues = resolveValueBackReferences(acontentproviderresult, i);
        as = resolveSelectionArgsBackReferences(acontentproviderresult, i);
        if(mType == 1)
        {
            contentprovider = contentprovider.insert(mUri, contentvalues);
            if(contentprovider == null)
                throw new OperationApplicationException("insert failed");
            else
                return new ContentProviderResult(contentprovider);
        }
        if(mType == 3)
        {
            i = contentprovider.delete(mUri, mSelection, as);
        } else
        {
label0:
            {
                if(mType != 2)
                    break label0;
                i = contentprovider.update(mUri, contentvalues, mSelection, as);
            }
        }
_L5:
        int j;
        if(mExpectedCount != null && mExpectedCount.intValue() != i)
        {
            Log.e("ContentProviderOperation", toString());
            throw new OperationApplicationException((new StringBuilder()).append("wrong number of rows: ").append(i).toString());
        } else
        {
            return new ContentProviderResult(i);
        }
        if(mType != 4)
            break MISSING_BLOCK_LABEL_424;
        acontentproviderresult = null;
        if(contentvalues != null)
        {
            acontentproviderresult = new ArrayList();
            for(Iterator iterator = contentvalues.valueSet().iterator(); iterator.hasNext(); acontentproviderresult.add((String)((java.util.Map.Entry)iterator.next()).getKey()));
            acontentproviderresult = (String[])acontentproviderresult.toArray(new String[acontentproviderresult.size()]);
        }
        contentprovider = contentprovider.query(mUri, acontentproviderresult, mSelection, as, null);
        j = contentprovider.getCount();
        if(acontentproviderresult == null) goto _L2; else goto _L1
_L1:
        if(!contentprovider.moveToNext())
            break; /* Loop/switch isn't completed */
        i = 0;
_L4:
        if(i >= acontentproviderresult.length)
            break; /* Loop/switch isn't completed */
        String s1 = contentprovider.getString(i);
        String s = contentvalues.getAsString(acontentproviderresult[i]);
        if(!TextUtils.equals(s1, s))
        {
            Log.e("ContentProviderOperation", toString());
            OperationApplicationException operationapplicationexception = JVM INSTR new #226 <Class OperationApplicationException>;
            StringBuilder stringbuilder = JVM INSTR new #179 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            operationapplicationexception.OperationApplicationException(stringbuilder.append("Found value ").append(s1).append(" when expected ").append(s).append(" for column ").append(acontentproviderresult[i]).toString());
            throw operationapplicationexception;
        }
        break MISSING_BLOCK_LABEL_406;
        acontentproviderresult;
        contentprovider.close();
        throw acontentproviderresult;
        i++;
        if(true) goto _L4; else goto _L3
_L3:
        if(true) goto _L1; else goto _L2
_L2:
        contentprovider.close();
        i = j;
          goto _L5
        Log.e("ContentProviderOperation", toString());
        throw new IllegalStateException((new StringBuilder()).append("bad type, ").append(mType).toString());
    }

    public int describeContents()
    {
        return 0;
    }

    public int getType()
    {
        return mType;
    }

    public Uri getUri()
    {
        return mUri;
    }

    public ContentProviderOperation getWithoutUserIdInUri()
    {
        if(ContentProvider.uriHasUserId(mUri))
            return new ContentProviderOperation(this, true);
        else
            return this;
    }

    public boolean isAssertQuery()
    {
        boolean flag;
        if(mType == 4)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isDelete()
    {
        boolean flag;
        if(mType == 3)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isInsert()
    {
        boolean flag = true;
        if(mType != 1)
            flag = false;
        return flag;
    }

    public boolean isReadOperation()
    {
        boolean flag;
        if(mType == 4)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isUpdate()
    {
        boolean flag;
        if(mType == 2)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isWriteOperation()
    {
        boolean flag;
        boolean flag1;
        flag = true;
        flag1 = flag;
        if(mType == 3) goto _L2; else goto _L1
_L1:
        if(mType != 1) goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        flag1 = flag;
        if(mType != 2)
            flag1 = false;
        if(true) goto _L2; else goto _L5
_L5:
    }

    public boolean isYieldAllowed()
    {
        return mYieldAllowed;
    }

    public String[] resolveSelectionArgsBackReferences(ContentProviderResult acontentproviderresult[], int i)
    {
        if(mSelectionArgsBackReferences == null)
            return mSelectionArgs;
        String as[] = new String[mSelectionArgs.length];
        System.arraycopy(mSelectionArgs, 0, as, 0, mSelectionArgs.length);
        for(Iterator iterator = mSelectionArgsBackReferences.entrySet().iterator(); iterator.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            Integer integer = (Integer)entry.getKey();
            int j = ((Integer)entry.getValue()).intValue();
            as[integer.intValue()] = String.valueOf(backRefToValue(acontentproviderresult, i, Integer.valueOf(j)));
        }

        return as;
    }

    public ContentValues resolveValueBackReferences(ContentProviderResult acontentproviderresult[], int i)
    {
        if(mValuesBackReferences == null)
            return mValues;
        ContentValues contentvalues;
        Iterator iterator;
        String s;
        Integer integer;
        if(mValues == null)
            contentvalues = new ContentValues();
        else
            contentvalues = new ContentValues(mValues);
        for(iterator = mValuesBackReferences.valueSet().iterator(); iterator.hasNext(); contentvalues.put(s, Long.valueOf(backRefToValue(acontentproviderresult, i, integer))))
        {
            s = (String)((java.util.Map.Entry)iterator.next()).getKey();
            integer = mValuesBackReferences.getAsInteger(s);
            if(integer == null)
            {
                Log.e("ContentProviderOperation", toString());
                throw new IllegalArgumentException((new StringBuilder()).append("values backref ").append(s).append(" is not an integer").toString());
            }
        }

        return contentvalues;
    }

    public String toString()
    {
        return (new StringBuilder()).append("mType: ").append(mType).append(", mUri: ").append(mUri).append(", mSelection: ").append(mSelection).append(", mExpectedCount: ").append(mExpectedCount).append(", mYieldAllowed: ").append(mYieldAllowed).append(", mValues: ").append(mValues).append(", mValuesBackReferences: ").append(mValuesBackReferences).append(", mSelectionArgsBackReferences: ").append(mSelectionArgsBackReferences).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mType);
        Uri.writeToParcel(parcel, mUri);
        if(mValues != null)
        {
            parcel.writeInt(1);
            mValues.writeToParcel(parcel, 0);
        } else
        {
            parcel.writeInt(0);
        }
        if(mSelection != null)
        {
            parcel.writeInt(1);
            parcel.writeString(mSelection);
        } else
        {
            parcel.writeInt(0);
        }
        if(mSelectionArgs != null)
        {
            parcel.writeInt(1);
            parcel.writeStringArray(mSelectionArgs);
        } else
        {
            parcel.writeInt(0);
        }
        if(mExpectedCount != null)
        {
            parcel.writeInt(1);
            parcel.writeInt(mExpectedCount.intValue());
        } else
        {
            parcel.writeInt(0);
        }
        if(mValuesBackReferences != null)
        {
            parcel.writeInt(1);
            mValuesBackReferences.writeToParcel(parcel, 0);
        } else
        {
            parcel.writeInt(0);
        }
        if(mSelectionArgsBackReferences != null)
        {
            parcel.writeInt(1);
            parcel.writeInt(mSelectionArgsBackReferences.size());
            java.util.Map.Entry entry;
            for(Iterator iterator = mSelectionArgsBackReferences.entrySet().iterator(); iterator.hasNext(); parcel.writeInt(((Integer)entry.getValue()).intValue()))
            {
                entry = (java.util.Map.Entry)iterator.next();
                parcel.writeInt(((Integer)entry.getKey()).intValue());
            }

        } else
        {
            parcel.writeInt(0);
        }
        if(mYieldAllowed)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ContentProviderOperation createFromParcel(Parcel parcel)
        {
            return new ContentProviderOperation(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ContentProviderOperation[] newArray(int i)
        {
            return new ContentProviderOperation[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String TAG = "ContentProviderOperation";
    public static final int TYPE_ASSERT = 4;
    public static final int TYPE_DELETE = 3;
    public static final int TYPE_INSERT = 1;
    public static final int TYPE_UPDATE = 2;
    private final Integer mExpectedCount;
    private final String mSelection;
    private final String mSelectionArgs[];
    private final Map mSelectionArgsBackReferences;
    private final int mType;
    private final Uri mUri;
    private final ContentValues mValues;
    private final ContentValues mValuesBackReferences;
    private final boolean mYieldAllowed;

}
