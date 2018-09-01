// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import android.graphics.drawable.*;

// Referenced classes of package android.util:
//            SparseArray

public final class LauncherIcons
{
    private static class ShadowDrawable extends DrawableWrapper
    {

        public void draw(Canvas canvas)
        {
            Rect rect = getBounds();
            canvas.drawBitmap(mState.mShadow, null, rect, mState.mPaint);
            canvas.save();
            canvas.translate((float)rect.width() * 0.9599999F * 0.02083333F, (float)rect.height() * 0.9599999F * 0.01041667F);
            canvas.scale(0.9599999F, 0.9599999F);
            super.draw(canvas);
            canvas.restore();
        }

        public android.graphics.drawable.Drawable.ConstantState getConstantState()
        {
            return mState;
        }

        final MyConstantState mState;

        public ShadowDrawable(Bitmap bitmap, Drawable drawable)
        {
            super(drawable);
            mState = new MyConstantState(bitmap, drawable.getConstantState());
        }

        ShadowDrawable(MyConstantState myconstantstate)
        {
            super(myconstantstate.mChildState.newDrawable());
            mState = myconstantstate;
        }
    }

    private static class ShadowDrawable.MyConstantState extends android.graphics.drawable.Drawable.ConstantState
    {

        public int getChangingConfigurations()
        {
            return mChildState.getChangingConfigurations();
        }

        public Drawable newDrawable()
        {
            return new ShadowDrawable(this);
        }

        final android.graphics.drawable.Drawable.ConstantState mChildState;
        final Paint mPaint = new Paint(2);
        final Bitmap mShadow;

        ShadowDrawable.MyConstantState(Bitmap bitmap, android.graphics.drawable.Drawable.ConstantState constantstate)
        {
            mShadow = bitmap;
            mChildState = constantstate;
        }
    }


    public LauncherIcons(Context context)
    {
        mRes = context.getResources();
        mIconSize = mRes.getDimensionPixelSize(0x1050000);
    }

    private Bitmap getShadowBitmap(AdaptiveIconDrawable adaptiveicondrawable)
    {
        int i = Math.max(mIconSize, adaptiveicondrawable.getIntrinsicHeight());
        Object obj = mShadowCache;
        obj;
        JVM INSTR monitorenter ;
        Bitmap bitmap = (Bitmap)mShadowCache.get(i);
        if(bitmap == null)
            break MISSING_BLOCK_LABEL_42;
        obj;
        JVM INSTR monitorexit ;
        return bitmap;
        obj;
        JVM INSTR monitorexit ;
        adaptiveicondrawable.setBounds(0, 0, i, i);
        float f = 0.01041667F * (float)i;
        float f1 = 0.02083333F * (float)i;
        int j = (int)((float)i + 2.0F * f + f1);
        obj = Bitmap.createBitmap(j, j, android.graphics.Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(((Bitmap) (obj)));
        canvas.translate(f1 / 2.0F + f, f);
        Paint paint = new Paint(1);
        paint.setColor(0);
        paint.setShadowLayer(f, 0.0F, 0.0F, 0x1e000000);
        canvas.drawPath(adaptiveicondrawable.getIconMask(), paint);
        canvas.translate(0.0F, f1);
        paint.setShadowLayer(f, 0.0F, 0.0F, 0x3d000000);
        canvas.drawPath(adaptiveicondrawable.getIconMask(), paint);
        canvas.setBitmap(null);
        adaptiveicondrawable = mShadowCache;
        adaptiveicondrawable;
        JVM INSTR monitorenter ;
        mShadowCache.put(i, obj);
        adaptiveicondrawable;
        JVM INSTR monitorexit ;
        return ((Bitmap) (obj));
        adaptiveicondrawable;
        throw adaptiveicondrawable;
        Exception exception;
        exception;
        throw exception;
    }

    public Drawable getBadgeDrawable(int i, int j)
    {
        return getBadgedDrawable(null, i, j);
    }

    public Drawable getBadgedDrawable(Drawable drawable, int i, int j)
    {
        Resources resources = Resources.getSystem();
        Drawable drawable1 = resources.getDrawable(0x108033e);
        Drawable drawable2 = resources.getDrawable(0x108033d).getConstantState().newDrawable().mutate();
        drawable2.setTint(j);
        Drawable drawable3 = resources.getDrawable(i);
        if(drawable == null)
        {
            drawable = new Drawable[3];
            drawable[0] = drawable1;
            drawable[1] = drawable2;
            drawable[2] = drawable3;
        } else
        {
            Drawable adrawable[] = new Drawable[4];
            adrawable[0] = drawable;
            adrawable[1] = drawable1;
            adrawable[2] = drawable2;
            adrawable[3] = drawable3;
            drawable = adrawable;
        }
        return new LayerDrawable(drawable);
    }

    public Drawable wrapIconDrawableWithShadow(Drawable drawable)
    {
        if(!(drawable instanceof AdaptiveIconDrawable))
            return drawable;
        else
            return new ShadowDrawable(getShadowBitmap((AdaptiveIconDrawable)drawable), drawable);
    }

    private static final int AMBIENT_SHADOW_ALPHA = 30;
    private static final float ICON_SIZE_BLUR_FACTOR = 0.01041667F;
    private static final float ICON_SIZE_KEY_SHADOW_DELTA_FACTOR = 0.02083333F;
    private static final int KEY_SHADOW_ALPHA = 61;
    private final int mIconSize;
    private final Resources mRes;
    private final SparseArray mShadowCache = new SparseArray();
}
