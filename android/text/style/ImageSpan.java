// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.style;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import java.io.InputStream;

// Referenced classes of package android.text.style:
//            DynamicDrawableSpan

public class ImageSpan extends DynamicDrawableSpan
{

    public ImageSpan(Context context, int i)
    {
        this(context, i, 0);
    }

    public ImageSpan(Context context, int i, int j)
    {
        super(j);
        mContext = context;
        mResourceId = i;
    }

    public ImageSpan(Context context, Bitmap bitmap)
    {
        this(context, bitmap, 0);
    }

    public ImageSpan(Context context, Bitmap bitmap, int i)
    {
        super(i);
        mContext = context;
        int j;
        if(context != null)
            context = new BitmapDrawable(context.getResources(), bitmap);
        else
            context = new BitmapDrawable(bitmap);
        mDrawable = context;
        i = mDrawable.getIntrinsicWidth();
        j = mDrawable.getIntrinsicHeight();
        context = mDrawable;
        if(i <= 0)
            i = 0;
        if(j <= 0)
            j = 0;
        context.setBounds(0, 0, i, j);
    }

    public ImageSpan(Context context, Uri uri)
    {
        this(context, uri, 0);
    }

    public ImageSpan(Context context, Uri uri, int i)
    {
        super(i);
        mContext = context;
        mContentUri = uri;
        mSource = uri.toString();
    }

    public ImageSpan(Bitmap bitmap)
    {
        this(((Context) (null)), bitmap, 0);
    }

    public ImageSpan(Bitmap bitmap, int i)
    {
        this(((Context) (null)), bitmap, i);
    }

    public ImageSpan(Drawable drawable)
    {
        this(drawable, 0);
    }

    public ImageSpan(Drawable drawable, int i)
    {
        super(i);
        mDrawable = drawable;
    }

    public ImageSpan(Drawable drawable, String s)
    {
        this(drawable, s, 0);
    }

    public ImageSpan(Drawable drawable, String s, int i)
    {
        super(i);
        mDrawable = drawable;
        mSource = s;
    }

    public Drawable getDrawable()
    {
        Object obj;
        Object obj1;
        obj = null;
        obj1 = null;
        if(mDrawable == null) goto _L2; else goto _L1
_L1:
        obj = mDrawable;
_L5:
        return ((Drawable) (obj));
_L2:
        if(mContentUri == null) goto _L4; else goto _L3
_L3:
        InputStream inputstream;
        inputstream = mContext.getContentResolver().openInputStream(mContentUri);
        Bitmap bitmap = BitmapFactory.decodeStream(inputstream);
        obj = JVM INSTR new #33  <Class BitmapDrawable>;
        ((BitmapDrawable) (obj)).BitmapDrawable(mContext.getResources(), bitmap);
        ((Drawable) (obj)).setBounds(0, 0, ((Drawable) (obj)).getIntrinsicWidth(), ((Drawable) (obj)).getIntrinsicHeight());
        inputstream.close();
          goto _L5
        Exception exception;
        exception;
        obj = obj1;
_L6:
        Log.e("sms", (new StringBuilder()).append("Failed to loaded content ").append(mContentUri).toString(), exception);
          goto _L5
_L4:
        Drawable drawable = mContext.getDrawable(mResourceId);
        obj = drawable;
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        obj = drawable;
          goto _L5
        drawable;
        Log.e("sms", (new StringBuilder()).append("Unable to find resource: ").append(mResourceId).toString());
          goto _L5
        drawable;
          goto _L6
    }

    public String getSource()
    {
        return mSource;
    }

    private Uri mContentUri;
    private Context mContext;
    private Drawable mDrawable;
    private int mResourceId;
    private String mSource;
}
