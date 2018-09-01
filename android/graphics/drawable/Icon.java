// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics.drawable;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.*;
import android.net.Uri;
import android.os.*;
import android.text.TextUtils;
import android.util.Log;
import java.io.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.Executor;

// Referenced classes of package android.graphics.drawable:
//            Drawable, BitmapDrawable, AdaptiveIconDrawable

public final class Icon
    implements Parcelable
{
    private class LoadDrawableTask
        implements Runnable
    {

        public void run()
        {
            mMessage.obj = loadDrawable(mContext);
            mMessage.sendToTarget();
        }

        public void runAsync()
        {
            AsyncTask.THREAD_POOL_EXECUTOR.execute(this);
        }

        final Context mContext;
        final Message mMessage;
        final Icon this$0;

        public LoadDrawableTask(Context context, Handler handler, OnDrawableLoadedListener ondrawableloadedlistener)
        {
            this$0 = Icon.this;
            super();
            mContext = context;
            mMessage = Message.obtain(handler, ondrawableloadedlistener. new _cls1());
        }

        public LoadDrawableTask(Context context, Message message)
        {
            this$0 = Icon.this;
            super();
            mContext = context;
            mMessage = message;
        }
    }

    public static interface OnDrawableLoadedListener
    {

        public abstract void onDrawableLoaded(Drawable drawable);
    }


    private Icon(int i)
    {
        mTintMode = DEFAULT_TINT_MODE;
        mType = i;
    }

    private Icon(Parcel parcel)
    {
        this(parcel.readInt());
        mType;
        JVM INSTR tableswitch 1 5: default 48
    //                   1 93
    //                   2 145
    //                   3 168
    //                   4 239
    //                   5 93;
           goto _L1 _L2 _L3 _L4 _L5 _L2
_L1:
        throw new RuntimeException((new StringBuilder()).append("invalid ").append(getClass().getSimpleName()).append(" type in parcel: ").append(mType).toString());
_L2:
        mObj1 = (Bitmap)Bitmap.CREATOR.createFromParcel(parcel);
_L7:
        if(parcel.readInt() == 1)
            mTintList = (ColorStateList)ColorStateList.CREATOR.createFromParcel(parcel);
        mTintMode = PorterDuff.intToMode(parcel.readInt());
        return;
_L3:
        String s = parcel.readString();
        int i = parcel.readInt();
        mString1 = s;
        mInt1 = i;
        continue; /* Loop/switch isn't completed */
_L4:
        int j = parcel.readInt();
        byte abyte0[] = parcel.readBlob();
        if(j != abyte0.length)
            throw new RuntimeException((new StringBuilder()).append("internal unparceling error: blob length (").append(abyte0.length).append(") != expected length (").append(j).append(")").toString());
        mInt1 = j;
        mObj1 = abyte0;
        continue; /* Loop/switch isn't completed */
_L5:
        mString1 = parcel.readString();
        if(true) goto _L7; else goto _L6
_L6:
    }

    Icon(Parcel parcel, Icon icon)
    {
        this(parcel);
    }

    public static Icon createFromStream(InputStream inputstream)
        throws IOException
    {
        DataInputStream datainputstream = new DataInputStream(inputstream);
        if(datainputstream.readInt() < 1) goto _L2; else goto _L1
_L1:
        datainputstream.readByte();
        JVM INSTR tableswitch 1 5: default 56
    //                   1 58
    //                   2 98
    //                   3 74
    //                   4 110
    //                   5 66;
           goto _L2 _L3 _L4 _L5 _L6 _L7
_L2:
        return null;
_L3:
        return createWithBitmap(BitmapFactory.decodeStream(datainputstream));
_L7:
        return createWithAdaptiveBitmap(BitmapFactory.decodeStream(datainputstream));
_L5:
        int i = datainputstream.readInt();
        inputstream = new byte[i];
        datainputstream.read(inputstream, 0, i);
        return createWithData(inputstream, 0, i);
_L4:
        return createWithResource(datainputstream.readUTF(), datainputstream.readInt());
_L6:
        return createWithContentUri(datainputstream.readUTF());
    }

    public static Icon createWithAdaptiveBitmap(Bitmap bitmap)
    {
        if(bitmap == null)
        {
            throw new IllegalArgumentException("Bitmap must not be null.");
        } else
        {
            Icon icon = new Icon(5);
            icon.setBitmap(bitmap);
            return icon;
        }
    }

    public static Icon createWithBitmap(Bitmap bitmap)
    {
        if(bitmap == null)
        {
            throw new IllegalArgumentException("Bitmap must not be null.");
        } else
        {
            Icon icon = new Icon(1);
            icon.setBitmap(bitmap);
            return icon;
        }
    }

    public static Icon createWithContentUri(Uri uri)
    {
        if(uri == null)
        {
            throw new IllegalArgumentException("Uri must not be null.");
        } else
        {
            Icon icon = new Icon(4);
            icon.mString1 = uri.toString();
            return icon;
        }
    }

    public static Icon createWithContentUri(String s)
    {
        if(s == null)
        {
            throw new IllegalArgumentException("Uri must not be null.");
        } else
        {
            Icon icon = new Icon(4);
            icon.mString1 = s;
            return icon;
        }
    }

    public static Icon createWithData(byte abyte0[], int i, int j)
    {
        if(abyte0 == null)
        {
            throw new IllegalArgumentException("Data must not be null.");
        } else
        {
            Icon icon = new Icon(3);
            icon.mObj1 = abyte0;
            icon.mInt1 = j;
            icon.mInt2 = i;
            return icon;
        }
    }

    public static Icon createWithFilePath(String s)
    {
        if(s == null)
        {
            throw new IllegalArgumentException("Path must not be null.");
        } else
        {
            Icon icon = new Icon(4);
            icon.mString1 = s;
            return icon;
        }
    }

    public static Icon createWithResource(Context context, int i)
    {
        if(context == null)
        {
            throw new IllegalArgumentException("Context must not be null.");
        } else
        {
            Icon icon = new Icon(2);
            icon.mInt1 = i;
            icon.mString1 = context.getPackageName();
            return icon;
        }
    }

    public static Icon createWithResource(Resources resources, int i)
    {
        if(resources == null)
        {
            throw new IllegalArgumentException("Resource must not be null.");
        } else
        {
            Icon icon = new Icon(2);
            icon.mInt1 = i;
            icon.mString1 = resources.getResourcePackageName(i);
            return icon;
        }
    }

    public static Icon createWithResource(String s, int i)
    {
        if(s == null)
        {
            throw new IllegalArgumentException("Resource package name must not be null.");
        } else
        {
            Icon icon = new Icon(2);
            icon.mInt1 = i;
            icon.mString1 = s;
            return icon;
        }
    }

    private Drawable loadDrawableInner(Context context)
    {
        mType;
        JVM INSTR tableswitch 1 5: default 40
    //                   1 42
    //                   2 82
    //                   3 243
    //                   4 270
    //                   5 58;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        return null;
_L2:
        return new BitmapDrawable(context.getResources(), getBitmap());
_L6:
        return new AdaptiveIconDrawable(null, new BitmapDrawable(context.getResources(), getBitmap()));
_L3:
        if(getResources() != null) goto _L8; else goto _L7
_L7:
        Object obj2;
        String s = getResPackage();
        obj2 = s;
        if(TextUtils.isEmpty(s))
            obj2 = context.getPackageName();
        if(!"android".equals(obj2)) goto _L10; else goto _L9
_L9:
        mObj1 = Resources.getSystem();
_L8:
        PackageManager packagemanager;
        android.content.pm.ApplicationInfo applicationinfo;
        try
        {
            context = getResources().getDrawable(getResId(), context.getTheme());
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            Log.e("Icon", String.format("Unable to load resource 0x%08x from pkg=%s", new Object[] {
                Integer.valueOf(getResId()), getResPackage()
            }), context);
            continue; /* Loop/switch isn't completed */
        }
        return context;
_L10:
        packagemanager = context.getPackageManager();
        try
        {
            applicationinfo = packagemanager.getApplicationInfo(((String) (obj2)), 8192);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            Log.e("Icon", String.format("Unable to find pkg=%s for icon %s", new Object[] {
                obj2, this
            }), context);
            continue; /* Loop/switch isn't completed */
        }
        if(applicationinfo == null)
            continue; /* Loop/switch isn't completed */
        mObj1 = packagemanager.getResourcesForApplication(applicationinfo);
        if(true) goto _L8; else goto _L11
_L11:
_L4:
        return new BitmapDrawable(context.getResources(), BitmapFactory.decodeByteArray(getDataBytes(), getDataOffset(), getDataLength()));
_L5:
        Uri uri;
        uri = getUri();
        String s1 = uri.getScheme();
        obj2 = null;
        if(!"content".equals(s1) && !"file".equals(s1))
            break; /* Loop/switch isn't completed */
        Object obj = context.getContentResolver().openInputStream(uri);
        obj2 = obj;
_L13:
        if(obj2 != null)
            return new BitmapDrawable(context.getResources(), BitmapFactory.decodeStream(((InputStream) (obj2))));
        if(true) goto _L1; else goto _L12
        Object obj1;
        obj1;
        Log.w("Icon", (new StringBuilder()).append("Unable to load image from URI: ").append(uri).toString(), ((Throwable) (obj1)));
          goto _L13
_L12:
        obj1 = JVM INSTR new #374 <Class FileInputStream>;
        File file = JVM INSTR new #376 <Class File>;
        file.File(mString1);
        ((FileInputStream) (obj1)).FileInputStream(file);
        obj2 = obj1;
          goto _L13
        obj1;
        Log.w("Icon", (new StringBuilder()).append("Unable to load image from path: ").append(uri).toString(), ((Throwable) (obj1)));
          goto _L13
    }

    public static Bitmap scaleDownIfNecessary(Bitmap bitmap, int i, int j)
    {
        Bitmap bitmap1;
label0:
        {
            int k = bitmap.getWidth();
            int l = bitmap.getHeight();
            if(k <= i)
            {
                bitmap1 = bitmap;
                if(l <= j)
                    break label0;
            }
            float f = Math.min((float)i / (float)k, (float)j / (float)l);
            bitmap1 = Bitmap.createScaledBitmap(bitmap, (int)((float)k * f), (int)((float)l * f), true);
        }
        return bitmap1;
    }

    private void setBitmap(Bitmap bitmap)
    {
        mObj1 = bitmap;
    }

    private static final String typeToString(int i)
    {
        switch(i)
        {
        default:
            return "UNKNOWN";

        case 1: // '\001'
            return "BITMAP";

        case 5: // '\005'
            return "BITMAP_MASKABLE";

        case 3: // '\003'
            return "DATA";

        case 2: // '\002'
            return "RESOURCE";

        case 4: // '\004'
            return "URI";
        }
    }

    public void convertToAshmem()
    {
        if((mType == 1 || mType == 5) && getBitmap().isMutable() && getBitmap().getAllocationByteCount() >= 0x20000)
            setBitmap(getBitmap().createAshmemBitmap());
    }

    public int describeContents()
    {
        boolean flag;
        int i;
        flag = true;
        i = ((flag) ? 1 : 0);
        if(mType == 1) goto _L2; else goto _L1
_L1:
        if(mType != 5) goto _L4; else goto _L3
_L3:
        i = ((flag) ? 1 : 0);
_L2:
        return i;
_L4:
        i = ((flag) ? 1 : 0);
        if(mType != 3)
            i = 0;
        if(true) goto _L2; else goto _L5
_L5:
    }

    public Bitmap getBitmap()
    {
        if(mType != 1 && mType != 5)
            throw new IllegalStateException((new StringBuilder()).append("called getBitmap() on ").append(this).toString());
        else
            return (Bitmap)mObj1;
    }

    public byte[] getDataBytes()
    {
        if(mType != 3)
            throw new IllegalStateException((new StringBuilder()).append("called getDataBytes() on ").append(this).toString());
        this;
        JVM INSTR monitorenter ;
        byte abyte0[] = (byte[])mObj1;
        this;
        JVM INSTR monitorexit ;
        return abyte0;
        Exception exception;
        exception;
        throw exception;
    }

    public int getDataLength()
    {
        if(mType != 3)
            throw new IllegalStateException((new StringBuilder()).append("called getDataLength() on ").append(this).toString());
        this;
        JVM INSTR monitorenter ;
        int i = mInt1;
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public int getDataOffset()
    {
        if(mType != 3)
            throw new IllegalStateException((new StringBuilder()).append("called getDataOffset() on ").append(this).toString());
        this;
        JVM INSTR monitorenter ;
        int i = mInt2;
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public int getResId()
    {
        if(mType != 2)
            throw new IllegalStateException((new StringBuilder()).append("called getResId() on ").append(this).toString());
        else
            return mInt1;
    }

    public String getResPackage()
    {
        if(mType != 2)
            throw new IllegalStateException((new StringBuilder()).append("called getResPackage() on ").append(this).toString());
        else
            return mString1;
    }

    public Resources getResources()
    {
        if(mType != 2)
            throw new IllegalStateException((new StringBuilder()).append("called getResources() on ").append(this).toString());
        else
            return (Resources)mObj1;
    }

    public int getType()
    {
        return mType;
    }

    public Uri getUri()
    {
        return Uri.parse(getUriString());
    }

    public String getUriString()
    {
        if(mType != 4)
            throw new IllegalStateException((new StringBuilder()).append("called getUriString() on ").append(this).toString());
        else
            return mString1;
    }

    public boolean hasTint()
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(mTintList == null)
            if(mTintMode != DEFAULT_TINT_MODE)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public Drawable loadDrawable(Context context)
    {
        context = loadDrawableInner(context);
        if(context != null && (mTintList != null || mTintMode != DEFAULT_TINT_MODE))
        {
            context.mutate();
            context.setTintList(mTintList);
            context.setTintMode(mTintMode);
        }
        return context;
    }

    public Drawable loadDrawableAsUser(Context context, int i)
    {
        if(mType == 2)
        {
            String s = getResPackage();
            String s1 = s;
            if(TextUtils.isEmpty(s))
                s1 = context.getPackageName();
            if(getResources() == null && getResPackage().equals("android") ^ true)
            {
                PackageManager packagemanager = context.getPackageManager();
                try
                {
                    mObj1 = packagemanager.getResourcesForApplicationAsUser(s1, i);
                }
                catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
                {
                    Log.e("Icon", String.format("Unable to find pkg=%s user=%d", new Object[] {
                        getResPackage(), Integer.valueOf(i)
                    }), namenotfoundexception);
                }
            }
        }
        return loadDrawable(context);
    }

    public void loadDrawableAsync(Context context, OnDrawableLoadedListener ondrawableloadedlistener, Handler handler)
    {
        (new LoadDrawableTask(context, handler, ondrawableloadedlistener)).runAsync();
    }

    public void loadDrawableAsync(Context context, Message message)
    {
        if(message.getTarget() == null)
        {
            throw new IllegalArgumentException("callback message must have a target handler");
        } else
        {
            (new LoadDrawableTask(context, message)).runAsync();
            return;
        }
    }

    public boolean sameAs(Icon icon)
    {
        boolean flag = true;
        boolean flag3 = false;
        boolean flag4 = false;
        if(icon == this)
            return true;
        if(mType != icon.getType())
            return false;
        switch(mType)
        {
        default:
            return false;

        case 1: // '\001'
        case 5: // '\005'
            if(getBitmap() != icon.getBitmap())
                flag = false;
            return flag;

        case 3: // '\003'
            boolean flag1 = flag4;
            if(getDataLength() == icon.getDataLength())
            {
                flag1 = flag4;
                if(getDataOffset() == icon.getDataOffset())
                    flag1 = Arrays.equals(getDataBytes(), icon.getDataBytes());
            }
            return flag1;

        case 2: // '\002'
            boolean flag2 = flag3;
            if(getResId() == icon.getResId())
                flag2 = Objects.equals(getResPackage(), icon.getResPackage());
            return flag2;

        case 4: // '\004'
            return Objects.equals(getUriString(), icon.getUriString());
        }
    }

    public void scaleDownIfNecessary(int i, int j)
    {
        if(mType != 1 && mType != 5)
        {
            return;
        } else
        {
            setBitmap(scaleDownIfNecessary(getBitmap(), i, j));
            return;
        }
    }

    public Icon setTint(int i)
    {
        return setTintList(ColorStateList.valueOf(i));
    }

    public Icon setTintList(ColorStateList colorstatelist)
    {
        mTintList = colorstatelist;
        return this;
    }

    public Icon setTintMode(android.graphics.PorterDuff.Mode mode)
    {
        mTintMode = mode;
        return this;
    }

    public String toString()
    {
        StringBuilder stringbuilder = (new StringBuilder("Icon(typ=")).append(typeToString(mType));
        mType;
        JVM INSTR tableswitch 1 5: default 60
    //                   1 140
    //                   2 177
    //                   3 224
    //                   4 264
    //                   5 140;
           goto _L1 _L2 _L3 _L4 _L5 _L2
_L1:
        if(mTintList != null)
        {
            stringbuilder.append(" tint=");
            String s = "";
            int ai[] = mTintList.getColors();
            int i = ai.length;
            for(int j = 0; j < i; j++)
            {
                stringbuilder.append(String.format("%s0x%08x", new Object[] {
                    s, Integer.valueOf(ai[j])
                }));
                s = "|";
            }

        }
        break; /* Loop/switch isn't completed */
_L2:
        stringbuilder.append(" size=").append(getBitmap().getWidth()).append("x").append(getBitmap().getHeight());
        continue; /* Loop/switch isn't completed */
_L3:
        stringbuilder.append(" pkg=").append(getResPackage()).append(" id=").append(String.format("0x%08x", new Object[] {
            Integer.valueOf(getResId())
        }));
        continue; /* Loop/switch isn't completed */
_L4:
        stringbuilder.append(" len=").append(getDataLength());
        if(getDataOffset() != 0)
            stringbuilder.append(" off=").append(getDataOffset());
        continue; /* Loop/switch isn't completed */
_L5:
        stringbuilder.append(" uri=").append(getUriString());
        if(true) goto _L1; else goto _L6
_L6:
        if(mTintMode != DEFAULT_TINT_MODE)
            stringbuilder.append(" mode=").append(mTintMode);
        stringbuilder.append(")");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mType);
        mType;
        JVM INSTR tableswitch 1 5: default 48
    //                   1 72
    //                   2 89
    //                   3 108
    //                   4 135
    //                   5 72;
           goto _L1 _L2 _L3 _L4 _L5 _L2
_L1:
        break; /* Loop/switch isn't completed */
_L5:
        break MISSING_BLOCK_LABEL_135;
_L6:
        if(mTintList == null)
        {
            parcel.writeInt(0);
        } else
        {
            parcel.writeInt(1);
            mTintList.writeToParcel(parcel, i);
        }
        parcel.writeInt(PorterDuff.modeToInt(mTintMode));
        return;
_L2:
        getBitmap();
        getBitmap().writeToParcel(parcel, i);
          goto _L6
_L3:
        parcel.writeString(getResPackage());
        parcel.writeInt(getResId());
          goto _L6
_L4:
        parcel.writeInt(getDataLength());
        parcel.writeBlob(getDataBytes(), getDataOffset(), getDataLength());
          goto _L6
        parcel.writeString(getUriString());
          goto _L6
    }

    public void writeToStream(OutputStream outputstream)
        throws IOException
    {
        outputstream = new DataOutputStream(outputstream);
        outputstream.writeInt(1);
        outputstream.writeByte(mType);
        mType;
        JVM INSTR tableswitch 1 5: default 60
    //                   1 61
    //                   2 105
    //                   3 78
    //                   4 124
    //                   5 61;
           goto _L1 _L2 _L3 _L4 _L5 _L2
_L1:
        return;
_L2:
        getBitmap().compress(android.graphics.Bitmap.CompressFormat.PNG, 100, outputstream);
        continue; /* Loop/switch isn't completed */
_L4:
        outputstream.writeInt(getDataLength());
        outputstream.write(getDataBytes(), getDataOffset(), getDataLength());
        continue; /* Loop/switch isn't completed */
_L3:
        outputstream.writeUTF(getResPackage());
        outputstream.writeInt(getResId());
        continue; /* Loop/switch isn't completed */
_L5:
        outputstream.writeUTF(getUriString());
        if(true) goto _L1; else goto _L6
_L6:
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public Icon createFromParcel(Parcel parcel)
        {
            return new Icon(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public Icon[] newArray(int i)
        {
            return new Icon[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    static final android.graphics.PorterDuff.Mode DEFAULT_TINT_MODE;
    public static final int MIN_ASHMEM_ICON_SIZE = 0x20000;
    private static final String TAG = "Icon";
    public static final int TYPE_ADAPTIVE_BITMAP = 5;
    public static final int TYPE_BITMAP = 1;
    public static final int TYPE_DATA = 3;
    public static final int TYPE_RESOURCE = 2;
    public static final int TYPE_URI = 4;
    private static final int VERSION_STREAM_SERIALIZER = 1;
    private int mInt1;
    private int mInt2;
    private Object mObj1;
    private String mString1;
    private ColorStateList mTintList;
    private android.graphics.PorterDuff.Mode mTintMode;
    private final int mType;

    static 
    {
        DEFAULT_TINT_MODE = Drawable.DEFAULT_TINT_MODE;
    }

    // Unreferenced inner class android/graphics/drawable/Icon$LoadDrawableTask$1

/* anonymous class */
    class LoadDrawableTask._cls1
        implements Runnable
    {

        public void run()
        {
            listener.onDrawableLoaded((Drawable)mMessage.obj);
        }

        final LoadDrawableTask this$1;
        final OnDrawableLoadedListener val$listener;

            
            {
                this$1 = final_loaddrawabletask;
                listener = OnDrawableLoadedListener.this;
                super();
            }
    }

}
