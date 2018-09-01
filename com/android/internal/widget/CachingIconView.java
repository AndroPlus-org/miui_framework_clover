// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import libcore.util.Objects;

public class CachingIconView extends ImageView
{

    public CachingIconView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    private String normalizeIconPackage(Icon icon)
    {
        if(icon == null)
            return null;
        icon = icon.getResPackage();
        if(TextUtils.isEmpty(icon))
            return null;
        if(icon.equals(mContext.getPackageName()))
            return null;
        else
            return icon;
    }

    private void resetCache()
    {
        this;
        JVM INSTR monitorenter ;
        mLastResId = 0;
        mLastPackage = null;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private boolean testAndSetCache(int i)
    {
        this;
        JVM INSTR monitorenter ;
        if(i == 0) goto _L2; else goto _L1
_L1:
        if(mLastResId != 0) goto _L3; else goto _L2
_L2:
        boolean flag = false;
_L5:
        mLastPackage = null;
        mLastResId = i;
        this;
        JVM INSTR monitorexit ;
        return flag;
_L3:
        String s;
        if(i != mLastResId)
            break MISSING_BLOCK_LABEL_51;
        s = mLastPackage;
        if(s == null)
        {
            flag = true;
            continue; /* Loop/switch isn't completed */
        }
        flag = false;
        if(true) goto _L5; else goto _L4
_L4:
        Exception exception;
        exception;
        throw exception;
    }

    private boolean testAndSetCache(Icon icon)
    {
        this;
        JVM INSTR monitorenter ;
        if(icon == null)
            break MISSING_BLOCK_LABEL_69;
        String s;
        boolean flag;
        if(icon.getType() != 2)
            break MISSING_BLOCK_LABEL_69;
        s = normalizeIconPackage(icon);
        if(mLastResId == 0 || icon.getResId() != mLastResId)
            break MISSING_BLOCK_LABEL_64;
        flag = Objects.equal(s, mLastPackage);
_L1:
        mLastPackage = s;
        mLastResId = icon.getResId();
        this;
        JVM INSTR monitorexit ;
        return flag;
        flag = false;
          goto _L1
        resetCache();
        this;
        JVM INSTR monitorexit ;
        return false;
        icon;
        throw icon;
    }

    private void updateVisibility()
    {
        int i;
        if(mDesiredVisibility == 0 && mForceHidden)
            i = 4;
        else
            i = mDesiredVisibility;
        super.setVisibility(i);
    }

    protected void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
        resetCache();
    }

    public void setForceHidden(boolean flag)
    {
        mForceHidden = flag;
        updateVisibility();
    }

    public void setImageBitmap(Bitmap bitmap)
    {
        resetCache();
        super.setImageBitmap(bitmap);
    }

    public void setImageDrawable(Drawable drawable)
    {
        if(!mInternalSetDrawable)
            resetCache();
        super.setImageDrawable(drawable);
    }

    public void setImageIcon(Icon icon)
    {
        if(!testAndSetCache(icon))
        {
            mInternalSetDrawable = true;
            super.setImageIcon(icon);
            mInternalSetDrawable = false;
        }
    }

    public Runnable setImageIconAsync(Icon icon)
    {
        resetCache();
        return super.setImageIconAsync(icon);
    }

    public void setImageResource(int i)
    {
        if(!testAndSetCache(i))
        {
            mInternalSetDrawable = true;
            super.setImageResource(i);
            mInternalSetDrawable = false;
        }
    }

    public Runnable setImageResourceAsync(int i)
    {
        resetCache();
        return super.setImageResourceAsync(i);
    }

    public void setImageURI(Uri uri)
    {
        resetCache();
        super.setImageURI(uri);
    }

    public Runnable setImageURIAsync(Uri uri)
    {
        resetCache();
        return super.setImageURIAsync(uri);
    }

    public void setVisibility(int i)
    {
        mDesiredVisibility = i;
        updateVisibility();
    }

    private int mDesiredVisibility;
    private boolean mForceHidden;
    private boolean mInternalSetDrawable;
    private String mLastPackage;
    private int mLastResId;
}
