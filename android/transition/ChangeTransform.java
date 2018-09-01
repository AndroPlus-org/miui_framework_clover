// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.transition;

import android.animation.*;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Property;
import android.view.*;
import java.util.Map;

// Referenced classes of package android.transition:
//            Transition, TransitionValues, PathMotion, TransitionListenerAdapter

public class ChangeTransform extends Transition
{
    private static class GhostListener extends TransitionListenerAdapter
    {

        public void onTransitionEnd(Transition transition)
        {
            transition.removeListener(this);
            GhostView.removeGhost(mView);
            mView.setTagInternal(0x102046c, null);
            mView.setTagInternal(0x1020362, null);
            mStartView.setTransitionAlpha(1.0F);
        }

        public void onTransitionPause(Transition transition)
        {
            mGhostView.setVisibility(4);
        }

        public void onTransitionResume(Transition transition)
        {
            mGhostView.setVisibility(0);
        }

        private GhostView mGhostView;
        private View mStartView;
        private View mView;

        public GhostListener(View view, View view1, GhostView ghostview)
        {
            mView = view;
            mStartView = view1;
            mGhostView = ghostview;
        }
    }

    private static class PathAnimatorMatrix
    {

        private void setAnimationMatrix()
        {
            mValues[2] = mTranslationX;
            mValues[5] = mTranslationY;
            mMatrix.setValues(mValues);
            mView.setAnimationMatrix(mMatrix);
        }

        public Matrix getMatrix()
        {
            return mMatrix;
        }

        public void setTranslation(PointF pointf)
        {
            mTranslationX = pointf.x;
            mTranslationY = pointf.y;
            setAnimationMatrix();
        }

        public void setValues(float af[])
        {
            System.arraycopy(af, 0, mValues, 0, af.length);
            setAnimationMatrix();
        }

        private final Matrix mMatrix = new Matrix();
        private float mTranslationX;
        private float mTranslationY;
        private final float mValues[];
        private final View mView;

        public PathAnimatorMatrix(View view, float af[])
        {
            mView = view;
            mValues = (float[])af.clone();
            mTranslationX = mValues[2];
            mTranslationY = mValues[5];
            setAnimationMatrix();
        }
    }

    private static class Transforms
    {

        public boolean equals(Object obj)
        {
            boolean flag = false;
            if(!(obj instanceof Transforms))
                return false;
            obj = (Transforms)obj;
            boolean flag1 = flag;
            if(((Transforms) (obj)).translationX == translationX)
            {
                flag1 = flag;
                if(((Transforms) (obj)).translationY == translationY)
                {
                    flag1 = flag;
                    if(((Transforms) (obj)).translationZ == translationZ)
                    {
                        flag1 = flag;
                        if(((Transforms) (obj)).scaleX == scaleX)
                        {
                            flag1 = flag;
                            if(((Transforms) (obj)).scaleY == scaleY)
                            {
                                flag1 = flag;
                                if(((Transforms) (obj)).rotationX == rotationX)
                                {
                                    flag1 = flag;
                                    if(((Transforms) (obj)).rotationY == rotationY)
                                    {
                                        flag1 = flag;
                                        if(((Transforms) (obj)).rotationZ == rotationZ)
                                            flag1 = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return flag1;
        }

        public void restore(View view)
        {
            ChangeTransform._2D_wrap1(view, translationX, translationY, translationZ, scaleX, scaleY, rotationX, rotationY, rotationZ);
        }

        public final float rotationX;
        public final float rotationY;
        public final float rotationZ;
        public final float scaleX;
        public final float scaleY;
        public final float translationX;
        public final float translationY;
        public final float translationZ;

        public Transforms(View view)
        {
            translationX = view.getTranslationX();
            translationY = view.getTranslationY();
            translationZ = view.getTranslationZ();
            scaleX = view.getScaleX();
            scaleY = view.getScaleY();
            rotationX = view.getRotationX();
            rotationY = view.getRotationY();
            rotationZ = view.getRotation();
        }
    }


    static boolean _2D_get0(ChangeTransform changetransform)
    {
        return changetransform.mUseOverlay;
    }

    static void _2D_wrap0(View view)
    {
        setIdentityTransforms(view);
    }

    static void _2D_wrap1(View view, float f, float f1, float f2, float f3, float f4, float f5, float f6, 
            float f7)
    {
        setTransforms(view, f, f1, f2, f3, f4, f5, f6, f7);
    }

    public ChangeTransform()
    {
        mUseOverlay = true;
        mReparent = true;
        mTempMatrix = new Matrix();
    }

    public ChangeTransform(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mUseOverlay = true;
        mReparent = true;
        mTempMatrix = new Matrix();
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.ChangeTransform);
        mUseOverlay = context.getBoolean(1, true);
        mReparent = context.getBoolean(0, true);
        context.recycle();
    }

    private void captureValues(TransitionValues transitionvalues)
    {
        View view = transitionvalues.view;
        if(view.getVisibility() == 8)
            return;
        transitionvalues.values.put("android:changeTransform:parent", view.getParent());
        Object obj = new Transforms(view);
        transitionvalues.values.put("android:changeTransform:transforms", obj);
        obj = view.getMatrix();
        if(obj == null || ((Matrix) (obj)).isIdentity())
            obj = null;
        else
            obj = new Matrix(((Matrix) (obj)));
        transitionvalues.values.put("android:changeTransform:matrix", obj);
        if(mReparent)
        {
            obj = new Matrix();
            ViewGroup viewgroup = (ViewGroup)view.getParent();
            viewgroup.transformMatrixToGlobal(((Matrix) (obj)));
            ((Matrix) (obj)).preTranslate(-viewgroup.getScrollX(), -viewgroup.getScrollY());
            transitionvalues.values.put("android:changeTransform:parentMatrix", obj);
            transitionvalues.values.put("android:changeTransform:intermediateMatrix", view.getTag(0x102046c));
            transitionvalues.values.put("android:changeTransform:intermediateParentMatrix", view.getTag(0x1020362));
        }
    }

    private void createGhostView(ViewGroup viewgroup, TransitionValues transitionvalues, TransitionValues transitionvalues1)
    {
        View view = transitionvalues1.view;
        Object obj = new Matrix((Matrix)transitionvalues1.values.get("android:changeTransform:parentMatrix"));
        viewgroup.transformMatrixToLocal(((Matrix) (obj)));
        obj = GhostView.addGhost(view, viewgroup, ((Matrix) (obj)));
        for(viewgroup = this; ((Transition) (viewgroup)).mParent != null; viewgroup = ((Transition) (viewgroup)).mParent);
        viewgroup.addListener(new GhostListener(view, transitionvalues.view, ((GhostView) (obj))));
        if(transitionvalues.view != transitionvalues1.view)
            transitionvalues.view.setTransitionAlpha(0.0F);
        view.setTransitionAlpha(1.0F);
    }

    private ObjectAnimator createTransformAnimator(final TransitionValues finalEndMatrix, final TransitionValues view, final boolean handleParentChange)
    {
        finalEndMatrix = (Matrix)finalEndMatrix.values.get("android:changeTransform:matrix");
        Matrix matrix = (Matrix)view.values.get("android:changeTransform:matrix");
        final PathAnimatorMatrix pathAnimatorMatrix = finalEndMatrix;
        if(finalEndMatrix == null)
            pathAnimatorMatrix = Matrix.IDENTITY_MATRIX;
        finalEndMatrix = matrix;
        if(matrix == null)
            finalEndMatrix = Matrix.IDENTITY_MATRIX;
        if(pathAnimatorMatrix.equals(finalEndMatrix))
        {
            return null;
        } else
        {
            final Transforms transforms = (Transforms)view.values.get("android:changeTransform:transforms");
            view = view.view;
            setIdentityTransforms(view);
            float af[] = new float[9];
            pathAnimatorMatrix.getValues(af);
            float af1[] = new float[9];
            finalEndMatrix.getValues(af1);
            pathAnimatorMatrix = new PathAnimatorMatrix(view, af);
            PropertyValuesHolder propertyvaluesholder = PropertyValuesHolder.ofObject(NON_TRANSLATIONS_PROPERTY, new FloatArrayEvaluator(new float[9]), new float[][] {
                af, af1
            });
            Object obj = getPathMotion().getPath(af[2], af[5], af1[2], af1[5]);
            obj = ObjectAnimator.ofPropertyValuesHolder(pathAnimatorMatrix, new PropertyValuesHolder[] {
                propertyvaluesholder, PropertyValuesHolder.ofObject(TRANSLATIONS_PROPERTY, null, ((android.graphics.Path) (obj)))
            });
            finalEndMatrix = new AnimatorListenerAdapter() {

                private void setCurrentMatrix(Matrix matrix1)
                {
                    mTempMatrix.set(matrix1);
                    view.setTagInternal(0x102046c, mTempMatrix);
                    transforms.restore(view);
                }

                public void onAnimationCancel(Animator animator)
                {
                    mIsCanceled = true;
                }

                public void onAnimationEnd(Animator animator)
                {
                    if(!mIsCanceled)
                        if(handleParentChange && ChangeTransform._2D_get0(ChangeTransform.this))
                        {
                            setCurrentMatrix(finalEndMatrix);
                        } else
                        {
                            view.setTagInternal(0x102046c, null);
                            view.setTagInternal(0x1020362, null);
                        }
                    view.setAnimationMatrix(null);
                    transforms.restore(view);
                }

                public void onAnimationPause(Animator animator)
                {
                    setCurrentMatrix(pathAnimatorMatrix.getMatrix());
                }

                public void onAnimationResume(Animator animator)
                {
                    ChangeTransform._2D_wrap0(view);
                }

                private boolean mIsCanceled;
                private Matrix mTempMatrix;
                final ChangeTransform this$0;
                final Matrix val$finalEndMatrix;
                final boolean val$handleParentChange;
                final PathAnimatorMatrix val$pathAnimatorMatrix;
                final Transforms val$transforms;
                final View val$view;

            
            {
                this$0 = ChangeTransform.this;
                handleParentChange = flag;
                finalEndMatrix = matrix;
                view = view1;
                transforms = transforms1;
                pathAnimatorMatrix = pathanimatormatrix;
                super();
                mTempMatrix = new Matrix();
            }
            }
;
            ((ObjectAnimator) (obj)).addListener(finalEndMatrix);
            ((ObjectAnimator) (obj)).addPauseListener(finalEndMatrix);
            return ((ObjectAnimator) (obj));
        }
    }

    private boolean parentsMatch(ViewGroup viewgroup, ViewGroup viewgroup1)
    {
        boolean flag = false;
        if(isValidTarget(viewgroup) && !(isValidTarget(viewgroup1) ^ true)) goto _L2; else goto _L1
_L1:
        if(viewgroup == viewgroup1)
            flag = true;
        else
            flag = false;
_L4:
        return flag;
_L2:
        viewgroup = getMatchedTransitionValues(viewgroup, true);
        if(viewgroup != null)
            if(viewgroup1 == ((TransitionValues) (viewgroup)).view)
                flag = true;
            else
                flag = false;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static void setIdentityTransforms(View view)
    {
        setTransforms(view, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
    }

    private void setMatricesForParent(TransitionValues transitionvalues, TransitionValues transitionvalues1)
    {
        Matrix matrix = (Matrix)transitionvalues1.values.get("android:changeTransform:parentMatrix");
        transitionvalues1.view.setTagInternal(0x1020362, matrix);
        Matrix matrix1 = mTempMatrix;
        matrix1.reset();
        matrix.invert(matrix1);
        matrix = (Matrix)transitionvalues.values.get("android:changeTransform:matrix");
        transitionvalues1 = matrix;
        if(matrix == null)
        {
            transitionvalues1 = new Matrix();
            transitionvalues.values.put("android:changeTransform:matrix", transitionvalues1);
        }
        transitionvalues1.postConcat((Matrix)transitionvalues.values.get("android:changeTransform:parentMatrix"));
        transitionvalues1.postConcat(matrix1);
    }

    private static void setTransforms(View view, float f, float f1, float f2, float f3, float f4, float f5, float f6, 
            float f7)
    {
        view.setTranslationX(f);
        view.setTranslationY(f1);
        view.setTranslationZ(f2);
        view.setScaleX(f3);
        view.setScaleY(f4);
        view.setRotationX(f5);
        view.setRotationY(f6);
        view.setRotation(f7);
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
        while(transitionvalues == null || transitionvalues1 == null || transitionvalues.values.containsKey("android:changeTransform:parent") ^ true || transitionvalues1.values.containsKey("android:changeTransform:parent") ^ true) 
            return null;
        ViewGroup viewgroup1 = (ViewGroup)transitionvalues.values.get("android:changeTransform:parent");
        Object obj = (ViewGroup)transitionvalues1.values.get("android:changeTransform:parent");
        boolean flag;
        if(mReparent)
            flag = parentsMatch(viewgroup1, ((ViewGroup) (obj))) ^ true;
        else
            flag = false;
        obj = (Matrix)transitionvalues.values.get("android:changeTransform:intermediateMatrix");
        if(obj != null)
            transitionvalues.values.put("android:changeTransform:matrix", obj);
        obj = (Matrix)transitionvalues.values.get("android:changeTransform:intermediateParentMatrix");
        if(obj != null)
            transitionvalues.values.put("android:changeTransform:parentMatrix", obj);
        if(flag)
            setMatricesForParent(transitionvalues, transitionvalues1);
        obj = createTransformAnimator(transitionvalues, transitionvalues1, flag);
        if(flag && obj != null && mUseOverlay)
            createGhostView(viewgroup, transitionvalues, transitionvalues1);
        return ((Animator) (obj));
    }

    public boolean getReparent()
    {
        return mReparent;
    }

    public boolean getReparentWithOverlay()
    {
        return mUseOverlay;
    }

    public String[] getTransitionProperties()
    {
        return sTransitionProperties;
    }

    public void setReparent(boolean flag)
    {
        mReparent = flag;
    }

    public void setReparentWithOverlay(boolean flag)
    {
        mUseOverlay = flag;
    }

    private static final Property NON_TRANSLATIONS_PROPERTY = new Property([F, "nonTranslations") {

        public volatile Object get(Object obj)
        {
            return get((PathAnimatorMatrix)obj);
        }

        public float[] get(PathAnimatorMatrix pathanimatormatrix)
        {
            return null;
        }

        public void set(PathAnimatorMatrix pathanimatormatrix, float af[])
        {
            pathanimatormatrix.setValues(af);
        }

        public volatile void set(Object obj, Object obj1)
        {
            set((PathAnimatorMatrix)obj, (float[])obj1);
        }

    }
;
    private static final String PROPNAME_INTERMEDIATE_MATRIX = "android:changeTransform:intermediateMatrix";
    private static final String PROPNAME_INTERMEDIATE_PARENT_MATRIX = "android:changeTransform:intermediateParentMatrix";
    private static final String PROPNAME_MATRIX = "android:changeTransform:matrix";
    private static final String PROPNAME_PARENT = "android:changeTransform:parent";
    private static final String PROPNAME_PARENT_MATRIX = "android:changeTransform:parentMatrix";
    private static final String PROPNAME_TRANSFORMS = "android:changeTransform:transforms";
    private static final String TAG = "ChangeTransform";
    private static final Property TRANSLATIONS_PROPERTY = new Property(android/graphics/PointF, "translations") {

        public PointF get(PathAnimatorMatrix pathanimatormatrix)
        {
            return null;
        }

        public volatile Object get(Object obj)
        {
            return get((PathAnimatorMatrix)obj);
        }

        public void set(PathAnimatorMatrix pathanimatormatrix, PointF pointf)
        {
            pathanimatormatrix.setTranslation(pointf);
        }

        public volatile void set(Object obj, Object obj1)
        {
            set((PathAnimatorMatrix)obj, (PointF)obj1);
        }

    }
;
    private static final String sTransitionProperties[] = {
        "android:changeTransform:matrix", "android:changeTransform:transforms", "android:changeTransform:parentMatrix"
    };
    private boolean mReparent;
    private Matrix mTempMatrix;
    private boolean mUseOverlay;

}
