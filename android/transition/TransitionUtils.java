// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.transition;

import android.animation.*;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.*;
import android.widget.ImageView;

// Referenced classes of package android.transition:
//            TransitionSet, Transition

public class TransitionUtils
{
    public static class MatrixEvaluator
        implements TypeEvaluator
    {

        public Matrix evaluate(float f, Matrix matrix, Matrix matrix1)
        {
            matrix.getValues(mTempStartValues);
            matrix1.getValues(mTempEndValues);
            for(int i = 0; i < 9; i++)
            {
                float f1 = mTempEndValues[i];
                float f2 = mTempStartValues[i];
                mTempEndValues[i] = mTempStartValues[i] + f * (f1 - f2);
            }

            mTempMatrix.setValues(mTempEndValues);
            return mTempMatrix;
        }

        public volatile Object evaluate(float f, Object obj, Object obj1)
        {
            return evaluate(f, (Matrix)obj, (Matrix)obj1);
        }

        float mTempEndValues[];
        Matrix mTempMatrix;
        float mTempStartValues[];

        public MatrixEvaluator()
        {
            mTempStartValues = new float[9];
            mTempEndValues = new float[9];
            mTempMatrix = new Matrix();
        }
    }


    public TransitionUtils()
    {
    }

    public static View copyViewImage(ViewGroup viewgroup, View view, View view1)
    {
        Matrix matrix = new Matrix();
        matrix.setTranslate(-view1.getScrollX(), -view1.getScrollY());
        view.transformMatrixToGlobal(matrix);
        viewgroup.transformMatrixToLocal(matrix);
        RectF rectf = new RectF(0.0F, 0.0F, view.getWidth(), view.getHeight());
        matrix.mapRect(rectf);
        int i = Math.round(rectf.left);
        int j = Math.round(rectf.top);
        int k = Math.round(rectf.right);
        int l = Math.round(rectf.bottom);
        view1 = new ImageView(view.getContext());
        view1.setScaleType(android.widget.ImageView.ScaleType.CENTER_CROP);
        viewgroup = createViewBitmap(view, matrix, rectf, viewgroup);
        if(viewgroup != null)
            view1.setImageBitmap(viewgroup);
        view1.measure(android.view.View.MeasureSpec.makeMeasureSpec(k - i, 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(l - j, 0x40000000));
        view1.layout(i, j, k, l);
        return view1;
    }

    public static Bitmap createDrawableBitmap(Drawable drawable, View view)
    {
        int i = drawable.getIntrinsicWidth();
        int j = drawable.getIntrinsicHeight();
        if(i <= 0 || j <= 0)
            return null;
        float f = Math.min(1.0F, (float)MAX_IMAGE_SIZE / (float)(i * j));
        if((drawable instanceof BitmapDrawable) && f == 1.0F)
        {
            return ((BitmapDrawable)drawable).getBitmap();
        } else
        {
            int k = (int)((float)i * f);
            int l = (int)((float)j * f);
            RenderNode rendernode = RenderNode.create("TransitionUtils", view);
            rendernode.setLeftTopRightBottom(0, 0, i, j);
            rendernode.setClipToBounds(false);
            DisplayListCanvas displaylistcanvas = rendernode.start(i, j);
            view = drawable.getBounds();
            int i1 = ((Rect) (view)).left;
            int j1 = ((Rect) (view)).top;
            int k1 = ((Rect) (view)).right;
            int l1 = ((Rect) (view)).bottom;
            drawable.setBounds(0, 0, k, l);
            drawable.draw(displaylistcanvas);
            drawable.setBounds(i1, j1, k1, l1);
            rendernode.end(displaylistcanvas);
            return ThreadedRenderer.createHardwareBitmap(rendernode, i, j);
        }
    }

    public static Bitmap createViewBitmap(View view, Matrix matrix, RectF rectf, ViewGroup viewgroup)
    {
        boolean flag = view.isAttachedToWindow() ^ true;
        if(flag)
        {
            if(viewgroup == null || viewgroup.isAttachedToWindow() ^ true)
                return null;
            viewgroup.getOverlay().add(view);
        }
        Object obj = null;
        int i = Math.round(rectf.width());
        int j = Math.round(rectf.height());
        Object obj1 = obj;
        if(i > 0)
        {
            obj1 = obj;
            if(j > 0)
            {
                float f = Math.min(1.0F, (float)MAX_IMAGE_SIZE / (float)(i * j));
                i = (int)((float)i * f);
                j = (int)((float)j * f);
                matrix.postTranslate(-rectf.left, -rectf.top);
                matrix.postScale(f, f);
                obj1 = RenderNode.create("TransitionUtils", view);
                ((RenderNode) (obj1)).setLeftTopRightBottom(0, 0, i, j);
                ((RenderNode) (obj1)).setClipToBounds(false);
                rectf = ((RenderNode) (obj1)).start(i, j);
                rectf.concat(matrix);
                view.draw(rectf);
                ((RenderNode) (obj1)).end(rectf);
                obj1 = ThreadedRenderer.createHardwareBitmap(((RenderNode) (obj1)), i, j);
            }
        }
        if(flag)
            viewgroup.getOverlay().remove(view);
        return ((Bitmap) (obj1));
    }

    static Animator mergeAnimators(Animator animator, Animator animator1)
    {
        if(animator == null)
            return animator1;
        if(animator1 == null)
        {
            return animator;
        } else
        {
            AnimatorSet animatorset = new AnimatorSet();
            animatorset.playTogether(new Animator[] {
                animator, animator1
            });
            return animatorset;
        }
    }

    public static transient Transition mergeTransitions(Transition atransition[])
    {
        int i = 0;
        int j = -1;
        for(int k = 0; k < atransition.length;)
        {
            int i1 = i;
            if(atransition[k] != null)
            {
                i1 = i + 1;
                j = k;
            }
            k++;
            i = i1;
        }

        if(i == 0)
            return null;
        if(i == 1)
            return atransition[j];
        TransitionSet transitionset = new TransitionSet();
        for(int l = 0; l < atransition.length; l++)
            if(atransition[l] != null)
                transitionset.addTransition(atransition[l]);

        return transitionset;
    }

    private static int MAX_IMAGE_SIZE = 0x100000;

}
