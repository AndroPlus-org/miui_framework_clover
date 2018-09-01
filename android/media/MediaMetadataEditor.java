// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.util.SparseIntArray;

public abstract class MediaMetadataEditor
{

    protected MediaMetadataEditor()
    {
        mMetadataChanged = false;
        mApplied = false;
        mArtworkChanged = false;
    }

    public void addEditableKey(int i)
    {
        this;
        JVM INSTR monitorenter ;
        if(!mApplied)
            break MISSING_BLOCK_LABEL_20;
        Log.e("MediaMetadataEditor", "Can't change editable keys of a previously applied MetadataEditor");
        this;
        JVM INSTR monitorexit ;
        return;
        if(i != 0x10000001)
            break MISSING_BLOCK_LABEL_48;
        mEditableKeys = mEditableKeys | (long)(0x1fffffff & i);
        mMetadataChanged = true;
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
        StringBuilder stringbuilder = JVM INSTR new #78  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.e("MediaMetadataEditor", stringbuilder.append("Metadata key ").append(i).append(" cannot be edited").toString());
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    public abstract void apply();

    public void clear()
    {
        this;
        JVM INSTR monitorenter ;
        if(!mApplied)
            break MISSING_BLOCK_LABEL_20;
        Log.e("MediaMetadataEditor", "Can't clear a previously applied MediaMetadataEditor");
        this;
        JVM INSTR monitorexit ;
        return;
        mEditorMetadata.clear();
        mEditorArtwork = null;
        MediaMetadata.Builder builder = JVM INSTR new #108 <Class MediaMetadata$Builder>;
        builder.MediaMetadata.Builder();
        mMetadataBuilder = builder;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public Bitmap getBitmap(int i, Bitmap bitmap)
        throws IllegalArgumentException
    {
        this;
        JVM INSTR monitorenter ;
        if(i == 100)
            break MISSING_BLOCK_LABEL_44;
        IllegalArgumentException illegalargumentexception = JVM INSTR new #115 <Class IllegalArgumentException>;
        bitmap = JVM INSTR new #78  <Class StringBuilder>;
        bitmap.StringBuilder();
        illegalargumentexception.IllegalArgumentException(bitmap.append("Invalid type 'Bitmap' for key ").append(i).toString());
        throw illegalargumentexception;
        bitmap;
        this;
        JVM INSTR monitorexit ;
        throw bitmap;
        if(mEditorArtwork != null)
            bitmap = mEditorArtwork;
        this;
        JVM INSTR monitorexit ;
        return bitmap;
    }

    public int[] getEditableKeys()
    {
        this;
        JVM INSTR monitorenter ;
        if(mEditableKeys == 0x10000001L)
            return (new int[] {
                0x10000001
            });
        this;
        JVM INSTR monitorexit ;
        return null;
        Exception exception;
        exception;
        throw exception;
    }

    public long getLong(int i, long l)
        throws IllegalArgumentException
    {
        this;
        JVM INSTR monitorenter ;
        if(METADATA_KEYS_TYPE.get(i, -1) != 0)
        {
            IllegalArgumentException illegalargumentexception = JVM INSTR new #115 <Class IllegalArgumentException>;
            StringBuilder stringbuilder = JVM INSTR new #78  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            illegalargumentexception.IllegalArgumentException(stringbuilder.append("Invalid type 'long' for key ").append(i).toString());
            throw illegalargumentexception;
        }
        break MISSING_BLOCK_LABEL_57;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        l = mEditorMetadata.getLong(String.valueOf(i), l);
        this;
        JVM INSTR monitorexit ;
        return l;
    }

    public Object getObject(int i, Object obj)
        throws IllegalArgumentException
    {
        this;
        JVM INSTR monitorenter ;
        METADATA_KEYS_TYPE.get(i, -1);
        JVM INSTR tableswitch 0 3: default 40
    //                   0 76
    //                   1 115
    //                   2 183
    //                   3 149;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        IllegalArgumentException illegalargumentexception = JVM INSTR new #115 <Class IllegalArgumentException>;
        obj = JVM INSTR new #78  <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        illegalargumentexception.IllegalArgumentException(((StringBuilder) (obj)).append("Invalid key ").append(i).toString());
        throw illegalargumentexception;
        obj;
        this;
        JVM INSTR monitorexit ;
        throw obj;
_L2:
        if(!mEditorMetadata.containsKey(String.valueOf(i))) goto _L7; else goto _L6
_L6:
        long l = mEditorMetadata.getLong(String.valueOf(i));
        return Long.valueOf(l);
_L7:
        this;
        JVM INSTR monitorexit ;
        return obj;
_L3:
        if(!mEditorMetadata.containsKey(String.valueOf(i))) goto _L9; else goto _L8
_L8:
        obj = mEditorMetadata.getString(String.valueOf(i));
        return obj;
_L9:
        this;
        JVM INSTR monitorexit ;
        return obj;
_L5:
        if(!mEditorMetadata.containsKey(String.valueOf(i))) goto _L11; else goto _L10
_L10:
        obj = mEditorMetadata.getParcelable(String.valueOf(i));
        return obj;
_L11:
        this;
        JVM INSTR monitorexit ;
        return obj;
_L4:
        if(i != 100) goto _L1; else goto _L12
_L12:
        if(mEditorArtwork != null)
            obj = mEditorArtwork;
        this;
        JVM INSTR monitorexit ;
        return obj;
    }

    public String getString(int i, String s)
        throws IllegalArgumentException
    {
        this;
        JVM INSTR monitorenter ;
        if(METADATA_KEYS_TYPE.get(i, -1) != 1)
        {
            IllegalArgumentException illegalargumentexception = JVM INSTR new #115 <Class IllegalArgumentException>;
            s = JVM INSTR new #78  <Class StringBuilder>;
            s.StringBuilder();
            illegalargumentexception.IllegalArgumentException(s.append("Invalid type 'String' for key ").append(i).toString());
            throw illegalargumentexception;
        }
        break MISSING_BLOCK_LABEL_50;
        s;
        this;
        JVM INSTR monitorexit ;
        throw s;
        s = mEditorMetadata.getString(String.valueOf(i), s);
        this;
        JVM INSTR monitorexit ;
        return s;
    }

    public MediaMetadataEditor putBitmap(int i, Bitmap bitmap)
        throws IllegalArgumentException
    {
        this;
        JVM INSTR monitorenter ;
        if(!mApplied)
            break MISSING_BLOCK_LABEL_21;
        Log.e("MediaMetadataEditor", "Can't edit a previously applied MediaMetadataEditor");
        this;
        JVM INSTR monitorexit ;
        return this;
        if(i == 100)
            break MISSING_BLOCK_LABEL_63;
        bitmap = JVM INSTR new #115 <Class IllegalArgumentException>;
        StringBuilder stringbuilder = JVM INSTR new #78  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        bitmap.IllegalArgumentException(stringbuilder.append("Invalid type 'Bitmap' for key ").append(i).toString());
        throw bitmap;
        bitmap;
        this;
        JVM INSTR monitorexit ;
        throw bitmap;
        mEditorArtwork = bitmap;
        mArtworkChanged = true;
        this;
        JVM INSTR monitorexit ;
        return this;
    }

    public MediaMetadataEditor putLong(int i, long l)
        throws IllegalArgumentException
    {
        this;
        JVM INSTR monitorenter ;
        if(!mApplied)
            break MISSING_BLOCK_LABEL_21;
        Log.e("MediaMetadataEditor", "Can't edit a previously applied MediaMetadataEditor");
        this;
        JVM INSTR monitorexit ;
        return this;
        if(METADATA_KEYS_TYPE.get(i, -1) != 0)
        {
            IllegalArgumentException illegalargumentexception = JVM INSTR new #115 <Class IllegalArgumentException>;
            StringBuilder stringbuilder = JVM INSTR new #78  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            illegalargumentexception.IllegalArgumentException(stringbuilder.append("Invalid type 'long' for key ").append(i).toString());
            throw illegalargumentexception;
        }
        break MISSING_BLOCK_LABEL_76;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        mEditorMetadata.putLong(String.valueOf(i), l);
        mMetadataChanged = true;
        this;
        JVM INSTR monitorexit ;
        return this;
    }

    public MediaMetadataEditor putObject(int i, Object obj)
        throws IllegalArgumentException
    {
        this;
        JVM INSTR monitorenter ;
        if(!mApplied)
            break MISSING_BLOCK_LABEL_21;
        Log.e("MediaMetadataEditor", "Can't edit a previously applied MediaMetadataEditor");
        this;
        JVM INSTR monitorexit ;
        return this;
        METADATA_KEYS_TYPE.get(i, -1);
        JVM INSTR tableswitch 0 3: default 60
    //                   0 96
    //                   1 151
    //                   2 231
    //                   3 207;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        obj = JVM INSTR new #115 <Class IllegalArgumentException>;
        StringBuilder stringbuilder = JVM INSTR new #78  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        ((IllegalArgumentException) (obj)).IllegalArgumentException(stringbuilder.append("Invalid key ").append(i).toString());
        throw obj;
        obj;
        this;
        JVM INSTR monitorexit ;
        throw obj;
_L2:
        if(!(obj instanceof Long)) goto _L7; else goto _L6
_L6:
        obj = putLong(i, ((Long)obj).longValue());
        this;
        JVM INSTR monitorexit ;
        return ((MediaMetadataEditor) (obj));
_L7:
        obj = JVM INSTR new #115 <Class IllegalArgumentException>;
        StringBuilder stringbuilder1 = JVM INSTR new #78  <Class StringBuilder>;
        stringbuilder1.StringBuilder();
        ((IllegalArgumentException) (obj)).IllegalArgumentException(stringbuilder1.append("Not a non-null Long for key ").append(i).toString());
        throw obj;
_L3:
        if(obj == null) goto _L9; else goto _L8
_L8:
        if(!(obj instanceof String)) goto _L10; else goto _L9
_L9:
        obj = putString(i, (String)obj);
        this;
        JVM INSTR monitorexit ;
        return ((MediaMetadataEditor) (obj));
_L10:
        obj = JVM INSTR new #115 <Class IllegalArgumentException>;
        StringBuilder stringbuilder2 = JVM INSTR new #78  <Class StringBuilder>;
        stringbuilder2.StringBuilder();
        ((IllegalArgumentException) (obj)).IllegalArgumentException(stringbuilder2.append("Not a String for key ").append(i).toString());
        throw obj;
_L5:
        mEditorMetadata.putParcelable(String.valueOf(i), (Parcelable)obj);
        mMetadataChanged = true;
        this;
        JVM INSTR monitorexit ;
        return this;
_L4:
        if(obj == null) goto _L12; else goto _L11
_L11:
        if(!(obj instanceof Bitmap)) goto _L13; else goto _L12
_L12:
        obj = putBitmap(i, (Bitmap)obj);
        this;
        JVM INSTR monitorexit ;
        return ((MediaMetadataEditor) (obj));
_L13:
        obj = JVM INSTR new #115 <Class IllegalArgumentException>;
        StringBuilder stringbuilder3 = JVM INSTR new #78  <Class StringBuilder>;
        stringbuilder3.StringBuilder();
        ((IllegalArgumentException) (obj)).IllegalArgumentException(stringbuilder3.append("Not a Bitmap for key ").append(i).toString());
        throw obj;
    }

    public MediaMetadataEditor putString(int i, String s)
        throws IllegalArgumentException
    {
        this;
        JVM INSTR monitorenter ;
        if(!mApplied)
            break MISSING_BLOCK_LABEL_21;
        Log.e("MediaMetadataEditor", "Can't edit a previously applied MediaMetadataEditor");
        this;
        JVM INSTR monitorexit ;
        return this;
        if(METADATA_KEYS_TYPE.get(i, -1) != 1)
        {
            s = JVM INSTR new #115 <Class IllegalArgumentException>;
            StringBuilder stringbuilder = JVM INSTR new #78  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            s.IllegalArgumentException(stringbuilder.append("Invalid type 'String' for key ").append(i).toString());
            throw s;
        }
        break MISSING_BLOCK_LABEL_69;
        s;
        this;
        JVM INSTR monitorexit ;
        throw s;
        mEditorMetadata.putString(String.valueOf(i), s);
        mMetadataChanged = true;
        this;
        JVM INSTR monitorexit ;
        return this;
    }

    public void removeEditableKeys()
    {
        this;
        JVM INSTR monitorenter ;
        if(!mApplied)
            break MISSING_BLOCK_LABEL_20;
        Log.e("MediaMetadataEditor", "Can't remove all editable keys of a previously applied MetadataEditor");
        this;
        JVM INSTR monitorexit ;
        return;
        if(mEditableKeys != 0L)
        {
            mEditableKeys = 0L;
            mMetadataChanged = true;
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public static final int BITMAP_KEY_ARTWORK = 100;
    public static final int KEY_EDITABLE_MASK = 0x1fffffff;
    protected static final SparseIntArray METADATA_KEYS_TYPE;
    protected static final int METADATA_TYPE_BITMAP = 2;
    protected static final int METADATA_TYPE_INVALID = -1;
    protected static final int METADATA_TYPE_LONG = 0;
    protected static final int METADATA_TYPE_RATING = 3;
    protected static final int METADATA_TYPE_STRING = 1;
    public static final int RATING_KEY_BY_OTHERS = 101;
    public static final int RATING_KEY_BY_USER = 0x10000001;
    private static final String TAG = "MediaMetadataEditor";
    protected boolean mApplied;
    protected boolean mArtworkChanged;
    protected long mEditableKeys;
    protected Bitmap mEditorArtwork;
    protected Bundle mEditorMetadata;
    protected MediaMetadata.Builder mMetadataBuilder;
    protected boolean mMetadataChanged;

    static 
    {
        METADATA_KEYS_TYPE = new SparseIntArray(17);
        METADATA_KEYS_TYPE.put(0, 0);
        METADATA_KEYS_TYPE.put(14, 0);
        METADATA_KEYS_TYPE.put(9, 0);
        METADATA_KEYS_TYPE.put(8, 0);
        METADATA_KEYS_TYPE.put(1, 1);
        METADATA_KEYS_TYPE.put(13, 1);
        METADATA_KEYS_TYPE.put(7, 1);
        METADATA_KEYS_TYPE.put(2, 1);
        METADATA_KEYS_TYPE.put(3, 1);
        METADATA_KEYS_TYPE.put(15, 1);
        METADATA_KEYS_TYPE.put(4, 1);
        METADATA_KEYS_TYPE.put(5, 1);
        METADATA_KEYS_TYPE.put(6, 1);
        METADATA_KEYS_TYPE.put(11, 1);
        METADATA_KEYS_TYPE.put(1000, 1);
        METADATA_KEYS_TYPE.put(100, 2);
        METADATA_KEYS_TYPE.put(101, 3);
        METADATA_KEYS_TYPE.put(0x10000001, 3);
    }
}
