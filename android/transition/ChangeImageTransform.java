// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.transition;

import android.animation.*;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.Map;

// Referenced classes of package android.transition:
//            Transition, TransitionValues

public class ChangeImageTransform extends Transition
{

    public ChangeImageTransform()
    {
    }

    public ChangeImageTransform(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    private void captureValues(TransitionValues transitionvalues)
    {
        View view = transitionvalues.view;
        if(!(view instanceof ImageView) || view.getVisibility() != 0)
            return;
        Object obj = (ImageView)view;
        Drawable drawable = ((ImageView) (obj)).getDrawable();
        if(drawable == null)
            return;
        Map map = transitionvalues.values;
        transitionvalues = new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        map.put("android:changeImageTransform:bounds", transitionvalues);
        if(((ImageView) (obj)).getScaleType() == android.widget.ImageView.ScaleType.FIT_XY)
        {
            obj = ((ImageView) (obj)).getImageMatrix();
            if(!((Matrix) (obj)).isIdentity())
            {
                transitionvalues = new Matrix(((Matrix) (obj)));
            } else
            {
                int i = drawable.getIntrinsicWidth();
                int j = drawable.getIntrinsicHeight();
                if(i > 0 && j > 0)
                {
                    float f = (float)transitionvalues.width() / (float)i;
                    float f1 = (float)transitionvalues.height() / (float)j;
                    transitionvalues = new Matrix();
                    transitionvalues.setScale(f, f1);
                } else
                {
                    transitionvalues = null;
                }
            }
        } else
        {
            transitionvalues = new Matrix(((ImageView) (obj)).getImageMatrix());
        }
        map.put("android:changeImageTransform:matrix", transitionvalues);
    }

    private ObjectAnimator createMatrixAnimator(ImageView imageview, Matrix matrix, Matrix matrix1)
    {
        return ObjectAnimator.ofObject(imageview, ANIMATED_TRANSFORM_PROPERTY, new TransitionUtils.MatrixEvaluator(), new Matrix[] {
            matrix, matrix1
        });
    }

    private ObjectAnimator createNullAnimator(ImageView imageview)
    {
        return ObjectAnimator.ofObject(imageview, ANIMATED_TRANSFORM_PROPERTY, NULL_MATRIX_EVALUATOR, new Matrix[] {
            null, null
        });
    }

    public void captureEndValues(TransitionValues transitionvalues)
    {
        captureValues(transitionvalues);
    }

    public void captureStartValues(TransitionValues transitionvalues)
    {
        captureValues(transitionvalues);
    }

    public Animator createAnimator(ViewGroup viewgroup, TransitionValues transitionvalues, TransitionValues transitionvalues1)
    {
        if(transitionvalues == null || transitionvalues1 == null)
            return null;
        viewgroup = (Rect)transitionvalues.values.get("android:changeImageTransform:bounds");
        Rect rect = (Rect)transitionvalues1.values.get("android:changeImageTransform:bounds");
        if(viewgroup == null || rect == null)
            return null;
        transitionvalues = (Matrix)transitionvalues.values.get("android:changeImageTransform:matrix");
        Matrix matrix = (Matrix)transitionvalues1.values.get("android:changeImageTransform:matrix");
        boolean flag;
        if(transitionvalues == null && matrix == null)
            flag = true;
        else
        if(transitionvalues != null)
            flag = transitionvalues.equals(matrix);
        else
            flag = false;
        if(viewgroup.equals(rect) && flag)
            return null;
        transitionvalues1 = (ImageView)transitionvalues1.view;
        viewgroup = transitionvalues1.getDrawable();
        int i = viewgroup.getIntrinsicWidth();
        int j = viewgroup.getIntrinsicHeight();
        if(i == 0 || j == 0)
        {
            viewgroup = createNullAnimator(transitionvalues1);
        } else
        {
            viewgroup = transitionvalues;
            if(transitionvalues == null)
                viewgroup = Matrix.IDENTITY_MATRIX;
            transitionvalues = matrix;
            if(matrix == null)
                transitionvalues = Matrix.IDENTITY_MATRIX;
            ANIMATED_TRANSFORM_PROPERTY.set(transitionvalues1, viewgroup);
            viewgroup = createMatrixAnimator(transitionvalues1, viewgroup, transitionvalues);
        }
        return viewgroup;
    }

    public String[] getTransitionProperties()
    {
        return sTransitionProperties;
    }

    private static Property ANIMATED_TRANSFORM_PROPERTY = new Property(android/graphics/Matrix, "animatedTransform") {

        public Matrix get(ImageView imageview)
        {
            return null;
        }

        public volatile Object get(Object obj)
        {
            return get((ImageView)obj);
        }

        public void set(ImageView imageview, Matrix matrix)
        {
            imageview.animateTransform(matrix);
        }

        public volatile void set(Object obj, Object obj1)
        {
            set((ImageView)obj, (Matrix)obj1);
        }

    }
;
    private static TypeEvaluator NULL_MATRIX_EVALUATOR = new TypeEvaluator() {

        public Matrix evaluate(float f, Matrix matrix, Matrix matrix1)
        {
            return null;
        }

        public volatile Object evaluate(float f, Object obj, Object obj1)
        {
            return evaluate(f, (Matrix)obj, (Matrix)obj1);
        }

    }
;
    private static final String PROPNAME_BOUNDS = "android:changeImageTransform:bounds";
    private static final String PROPNAME_MATRIX = "android:changeImageTransform:matrix";
    private static final String TAG = "ChangeImageTransform";
    private static final String sTransitionProperties[] = {
        "android:changeImageTransform:matrix", "android:changeImageTransform:bounds"
    };

}
