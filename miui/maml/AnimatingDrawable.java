// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml;

import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import miui.maml.util.AppIconsHelper;

// Referenced classes of package miui.maml:
//            MamlDrawable, ResourceManager, FancyDrawable, ScreenElementRoot

public class AnimatingDrawable extends MamlDrawable
{
    static final class AnimatingDrawableState extends MamlDrawable.MamlDrawableState
    {

        protected MamlDrawable createDrawable()
        {
            return new AnimatingDrawable(mContext, mPackageName, mClassName, mResourceManager, mUser);
        }

        private String mClassName;
        private Context mContext;
        private String mPackageName;
        private ResourceManager mResourceManager;
        private UserHandle mUser;

        public AnimatingDrawableState(Context context, String s, String s1, ResourceManager resourcemanager, UserHandle userhandle)
        {
            mContext = context;
            mResourceManager = resourcemanager;
            mPackageName = s;
            mClassName = s1;
            mUser = userhandle;
        }
    }


    public AnimatingDrawable(Context context, String s, String s1, ResourceManager resourcemanager, UserHandle userhandle)
    {
        mContext = context;
        mResourceManager = resourcemanager;
        mPackageName = s;
        mClassName = s1;
        mUser = userhandle;
        init();
    }

    private void init()
    {
        mState = new AnimatingDrawableState(mContext, mPackageName, mClassName, mResourceManager, mUser);
        Display display = ((WindowManager)mContext.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        display.getMetrics(displaymetrics);
        mResourceManager.setExtraResource((new StringBuilder()).append("den").append(displaymetrics.densityDpi).toString(), displaymetrics.densityDpi);
        mQuietDrawable = mResourceManager.getDrawable(mContext.getResources(), "quietImage.png");
        if(mQuietDrawable != null)
        {
            setIntrinsicSize(mQuietDrawable.getIntrinsicWidth(), mQuietDrawable.getIntrinsicHeight());
            mQuietDrawable = mQuietDrawable.mutate();
            mQuietDrawable.setBounds(0, 0, mQuietDrawable.getIntrinsicWidth(), mQuietDrawable.getIntrinsicHeight());
        } else
        {
            Log.e("Maml.AnimatingDrawable", (new StringBuilder()).append("mQuietDrwable is null! package/class=").append(mPackageName).append("/").append(mClassName).toString());
        }
    }

    public void clear()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        mFancyDrawable = null;
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    protected void drawIcon(Canvas canvas)
    {
        int i = canvas.save();
        canvas.translate(getBounds().left, getBounds().top);
        canvas.scale((float)mWidth / (float)mIntrinsicWidth, (float)mHeight / (float)mIntrinsicHeight, 0.0F, 0.0F);
        mQuietDrawable.draw(canvas);
        canvas.restoreToCount(i);
_L1:
        return;
        canvas;
        canvas.printStackTrace();
        Log.e("Maml.AnimatingDrawable", canvas.toString());
          goto _L1
        canvas;
        canvas.printStackTrace();
        Log.e("Maml.AnimatingDrawable", canvas.toString());
          goto _L1
    }

    public Drawable getFancyDrawable()
    {
        return mFancyDrawable;
    }

    public int getOpacity()
    {
        return -3;
    }

    public Drawable getStartDrawable()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        Drawable drawable;
        prepareFancyDrawable();
        if(mFancyDrawable == null)
            break MISSING_BLOCK_LABEL_30;
        drawable = mFancyDrawable.getStartDrawable();
        return drawable;
        obj;
        JVM INSTR monitorexit ;
        return null;
        Exception exception;
        exception;
        throw exception;
    }

    public void prepareFancyDrawable()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        FancyDrawable fancydrawable = mFancyDrawable;
        if(fancydrawable == null)
            break MISSING_BLOCK_LABEL_19;
        obj;
        JVM INSTR monitorexit ;
        return;
        Drawable drawable = AppIconsHelper.getFancyIconDrawable(mContext, mPackageName, mClassName, 0L, mUser);
        if(drawable instanceof FancyDrawable)
        {
            mFancyDrawable = (FancyDrawable)drawable;
            if(mQuietDrawable != null)
                mFancyDrawable.setColorFilter(mQuietDrawable.getColorFilter());
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void sendCommand(String s)
    {
        if(mFancyDrawable != null)
            mFancyDrawable.getRoot().onCommand(s);
    }

    public void setAlpha(int i)
    {
        if(mQuietDrawable != null)
            mQuietDrawable.setAlpha(i);
    }

    public void setBounds(int i, int j, int k, int l)
    {
        super.setBounds(i, j, k, l);
    }

    public void setColorFilter(ColorFilter colorfilter)
    {
        if(mQuietDrawable != null)
            mQuietDrawable.setColorFilter(colorfilter);
        if(mBadgeDrawable != null)
            mBadgeDrawable.setColorFilter(colorfilter);
    }

    private static final String QUIET_IMAGE_NAME = "quietImage.png";
    private static final String TAG = "Maml.AnimatingDrawable";
    public static final int TIME_FANCY_CACHE = 0;
    private String mClassName;
    private Context mContext;
    private FancyDrawable mFancyDrawable;
    private final Object mLock = new Object();
    private String mPackageName;
    private Drawable mQuietDrawable;
    private ResourceManager mResourceManager;
    private UserHandle mUser;
}
