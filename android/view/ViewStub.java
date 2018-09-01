// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import java.lang.ref.WeakReference;

// Referenced classes of package android.view:
//            View, LayoutInflater, ViewGroup

public final class ViewStub extends View
{
    public static interface OnInflateListener
    {

        public abstract void onInflate(ViewStub viewstub, View view);
    }

    public class ViewReplaceRunnable
        implements Runnable
    {

        public void run()
        {
            ViewStub._2D_wrap0(ViewStub.this, view, (ViewGroup)getParent());
        }

        final ViewStub this$0;
        public final View view;

        ViewReplaceRunnable(View view1)
        {
            this$0 = ViewStub.this;
            super();
            view = view1;
        }
    }


    static void _2D_wrap0(ViewStub viewstub, View view, ViewGroup viewgroup)
    {
        viewstub.replaceSelfWithView(view, viewgroup);
    }

    public ViewStub(Context context)
    {
        this(context, 0);
    }

    public ViewStub(Context context, int i)
    {
        this(context, ((AttributeSet) (null)));
        mLayoutResource = i;
    }

    public ViewStub(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public ViewStub(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public ViewStub(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context);
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.ViewStub, i, j);
        mInflatedId = context.getResourceId(2, -1);
        mLayoutResource = context.getResourceId(1, 0);
        mID = context.getResourceId(0, -1);
        context.recycle();
        setVisibility(8);
        setWillNotDraw(true);
    }

    private View inflateViewNoAdd(ViewGroup viewgroup)
    {
        LayoutInflater layoutinflater;
        if(mInflater != null)
            layoutinflater = mInflater;
        else
            layoutinflater = LayoutInflater.from(mContext);
        viewgroup = layoutinflater.inflate(mLayoutResource, viewgroup, false);
        if(mInflatedId != -1)
            viewgroup.setId(mInflatedId);
        return viewgroup;
    }

    private void replaceSelfWithView(View view, ViewGroup viewgroup)
    {
        int i = viewgroup.indexOfChild(this);
        viewgroup.removeViewInLayout(this);
        ViewGroup.LayoutParams layoutparams = getLayoutParams();
        if(layoutparams != null)
            viewgroup.addView(view, i, layoutparams);
        else
            viewgroup.addView(view, i);
    }

    protected void dispatchDraw(Canvas canvas)
    {
    }

    public void draw(Canvas canvas)
    {
    }

    public int getInflatedId()
    {
        return mInflatedId;
    }

    public LayoutInflater getLayoutInflater()
    {
        return mInflater;
    }

    public int getLayoutResource()
    {
        return mLayoutResource;
    }

    public View inflate()
    {
        Object obj = getParent();
        if(obj != null && (obj instanceof ViewGroup))
        {
            if(mLayoutResource != 0)
            {
                ViewGroup viewgroup = (ViewGroup)obj;
                obj = inflateViewNoAdd(viewgroup);
                replaceSelfWithView(((View) (obj)), viewgroup);
                mInflatedViewRef = new WeakReference(obj);
                if(mInflateListener != null)
                    mInflateListener.onInflate(this, ((View) (obj)));
                return ((View) (obj));
            } else
            {
                throw new IllegalArgumentException("ViewStub must have a valid layoutResource");
            }
        } else
        {
            throw new IllegalStateException("ViewStub must have a non-null ViewGroup viewParent");
        }
    }

    protected void onMeasure(int i, int j)
    {
        setMeasuredDimension(0, 0);
    }

    public void setInflatedId(int i)
    {
        mInflatedId = i;
    }

    public Runnable setInflatedIdAsync(int i)
    {
        mInflatedId = i;
        return null;
    }

    public void setLayoutInflater(LayoutInflater layoutinflater)
    {
        mInflater = layoutinflater;
    }

    public void setLayoutResource(int i)
    {
        mLayoutResource = i;
    }

    public Runnable setLayoutResourceAsync(int i)
    {
        mLayoutResource = i;
        return null;
    }

    public void setOnInflateListener(OnInflateListener oninflatelistener)
    {
        mInflateListener = oninflatelistener;
    }

    public void setVisibility(int i)
    {
        if(mInflatedViewRef == null) goto _L2; else goto _L1
_L1:
        View view = (View)mInflatedViewRef.get();
        if(view == null) goto _L4; else goto _L3
_L3:
        view.setVisibility(i);
_L6:
        return;
_L4:
        throw new IllegalStateException("setVisibility called on un-referenced view");
_L2:
        super.setVisibility(i);
        if(i == 0 || i == 4)
            inflate();
        if(true) goto _L6; else goto _L5
_L5:
    }

    public Runnable setVisibilityAsync(int i)
    {
        if(i == 0 || i == 4)
            return new ViewReplaceRunnable(inflateViewNoAdd((ViewGroup)getParent()));
        else
            return null;
    }

    private OnInflateListener mInflateListener;
    private int mInflatedId;
    private WeakReference mInflatedViewRef;
    private LayoutInflater mInflater;
    private int mLayoutResource;
}
