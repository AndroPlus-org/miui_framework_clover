// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.graphics.*;
import android.os.*;
import android.transition.*;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.view.*;
import android.widget.ImageView;
import com.android.internal.view.OneShotPreDrawListener;
import java.util.*;

// Referenced classes of package android.app:
//            SharedElementCallback

abstract class ActivityTransitionCoordinator extends ResultReceiver
{
    protected class ContinueTransitionListener extends TransitionListenerAdapter
    {

        public void onTransitionEnd(Transition transition)
        {
            transition.removeListener(this);
        }

        public void onTransitionStart(Transition transition)
        {
            ActivityTransitionCoordinator._2D_set0(ActivityTransitionCoordinator.this, false);
            transition = ActivityTransitionCoordinator._2D_get0(ActivityTransitionCoordinator.this);
            ActivityTransitionCoordinator._2D_set1(ActivityTransitionCoordinator.this, null);
            if(transition != null)
                startTransition(transition);
        }

        final ActivityTransitionCoordinator this$0;

        protected ContinueTransitionListener()
        {
            this$0 = ActivityTransitionCoordinator.this;
            super();
        }
    }

    private static class FixedEpicenterCallback extends android.transition.Transition.EpicenterCallback
    {

        public Rect onGetEpicenter(Transition transition)
        {
            return mEpicenter;
        }

        public void setEpicenter(Rect rect)
        {
            mEpicenter = rect;
        }

        private Rect mEpicenter;

        private FixedEpicenterCallback()
        {
        }

        FixedEpicenterCallback(FixedEpicenterCallback fixedepicentercallback)
        {
            this();
        }
    }

    private static class GhostViewListeners
        implements android.view.ViewTreeObserver.OnPreDrawListener, android.view.View.OnAttachStateChangeListener
    {

        public View getView()
        {
            return mView;
        }

        public boolean onPreDraw()
        {
            GhostView ghostview = GhostView.getGhost(mView);
            if(ghostview == null || mView.isAttachedToWindow() ^ true)
            {
                removeListener();
            } else
            {
                GhostView.calculateMatrix(mView, mDecor, mMatrix);
                ghostview.setMatrix(mMatrix);
            }
            return true;
        }

        public void onViewAttachedToWindow(View view)
        {
            mViewTreeObserver = view.getViewTreeObserver();
        }

        public void onViewDetachedFromWindow(View view)
        {
            removeListener();
        }

        public void removeListener()
        {
            if(mViewTreeObserver.isAlive())
                mViewTreeObserver.removeOnPreDrawListener(this);
            else
                mParent.getViewTreeObserver().removeOnPreDrawListener(this);
            mParent.removeOnAttachStateChangeListener(this);
        }

        private ViewGroup mDecor;
        private Matrix mMatrix;
        private View mParent;
        private View mView;
        private ViewTreeObserver mViewTreeObserver;

        public GhostViewListeners(View view, View view1, ViewGroup viewgroup)
        {
            mMatrix = new Matrix();
            mView = view;
            mParent = view1;
            mDecor = viewgroup;
            mViewTreeObserver = view1.getViewTreeObserver();
        }
    }

    static class SharedElementOriginalState
    {

        int mBottom;
        float mElevation;
        int mLeft;
        Matrix mMatrix;
        int mMeasuredHeight;
        int mMeasuredWidth;
        int mRight;
        android.widget.ImageView.ScaleType mScaleType;
        int mTop;
        float mTranslationZ;

        SharedElementOriginalState()
        {
        }
    }


    static Runnable _2D_get0(ActivityTransitionCoordinator activitytransitioncoordinator)
    {
        return activitytransitioncoordinator.mPendingTransition;
    }

    static boolean _2D_set0(ActivityTransitionCoordinator activitytransitioncoordinator, boolean flag)
    {
        activitytransitioncoordinator.mIsStartingTransition = flag;
        return flag;
    }

    static Runnable _2D_set1(ActivityTransitionCoordinator activitytransitioncoordinator, Runnable runnable)
    {
        activitytransitioncoordinator.mPendingTransition = runnable;
        return runnable;
    }

    public ActivityTransitionCoordinator(Window window, ArrayList arraylist, SharedElementCallback sharedelementcallback, boolean flag)
    {
        super(new Handler());
        mTransitioningViews = new ArrayList();
        mGhostViewListeners = new ArrayList();
        mOriginalAlphas = new ArrayMap();
        mStrippedTransitioningViews = new ArrayList();
        mWindow = window;
        mListener = sharedelementcallback;
        mAllSharedElementNames = arraylist;
        mIsReturning = flag;
    }

    private static void findIncludedViews(Transition transition, ArrayList arraylist, ArraySet arrayset)
    {
        if(transition instanceof TransitionSet)
        {
            TransitionSet transitionset = (TransitionSet)transition;
            ArrayList arraylist1 = new ArrayList();
            int i = arraylist.size();
            for(int k = 0; k < i; k++)
            {
                View view = (View)arraylist.get(k);
                if(transition.isValidTarget(view))
                    arraylist1.add(view);
            }

            i = transitionset.getTransitionCount();
            for(int l = 0; l < i; l++)
                findIncludedViews(transitionset.getTransitionAt(l), arraylist1, arrayset);

        } else
        {
            int j = arraylist.size();
            for(int i1 = 0; i1 < j; i1++)
            {
                View view1 = (View)arraylist.get(i1);
                if(transition.isValidTarget(view1))
                    arrayset.add(view1);
            }

        }
    }

    private static SharedElementOriginalState getOldSharedElementState(View view, String s, Bundle bundle)
    {
        SharedElementOriginalState sharedelementoriginalstate = new SharedElementOriginalState();
        sharedelementoriginalstate.mLeft = view.getLeft();
        sharedelementoriginalstate.mTop = view.getTop();
        sharedelementoriginalstate.mRight = view.getRight();
        sharedelementoriginalstate.mBottom = view.getBottom();
        sharedelementoriginalstate.mMeasuredWidth = view.getMeasuredWidth();
        sharedelementoriginalstate.mMeasuredHeight = view.getMeasuredHeight();
        sharedelementoriginalstate.mTranslationZ = view.getTranslationZ();
        sharedelementoriginalstate.mElevation = view.getElevation();
        if(!(view instanceof ImageView))
            return sharedelementoriginalstate;
        s = bundle.getBundle(s);
        if(s == null)
            return sharedelementoriginalstate;
        if(s.getInt("shared_element:scaleType", -1) < 0)
            return sharedelementoriginalstate;
        view = (ImageView)view;
        sharedelementoriginalstate.mScaleType = view.getScaleType();
        if(sharedelementoriginalstate.mScaleType == android.widget.ImageView.ScaleType.MATRIX)
            sharedelementoriginalstate.mMatrix = new Matrix(view.getImageMatrix());
        return sharedelementoriginalstate;
    }

    private void getSharedElementParentMatrix(View view, Matrix matrix)
    {
        int i;
        if(mSharedElementParentMatrices == null)
            i = -1;
        else
            i = mSharedElements.indexOf(view);
        if(i < 0)
        {
            matrix.reset();
            view = view.getParent();
            if(view instanceof ViewGroup)
            {
                view = (ViewGroup)view;
                view.transformMatrixToLocal(matrix);
                matrix.postTranslate(view.getScrollX(), view.getScrollY());
            }
        } else
        {
            matrix.set((Matrix)mSharedElementParentMatrices.get(i));
        }
    }

    public static boolean isInTransitionGroup(ViewParent viewparent, ViewGroup viewgroup)
    {
        if(viewparent == viewgroup || (viewparent instanceof ViewGroup) ^ true)
            return false;
        viewparent = (ViewGroup)viewparent;
        if(viewparent.isTransitionGroup())
            return true;
        else
            return isInTransitionGroup(viewparent.getParent(), viewgroup);
    }

    private static boolean isNested(View view, ArrayMap arraymap)
    {
        view = view.getParent();
        boolean flag = false;
        do
        {
label0:
            {
                boolean flag1 = flag;
                if(view instanceof View)
                {
                    view = (View)view;
                    if(!arraymap.containsValue(view))
                        break label0;
                    flag1 = true;
                }
                return flag1;
            }
            view = view.getParent();
        } while(true);
    }

    protected static Transition mergeTransitions(Transition transition, Transition transition1)
    {
        if(transition == null)
            return transition1;
        if(transition1 == null)
        {
            return transition;
        } else
        {
            TransitionSet transitionset = new TransitionSet();
            transitionset.addTransition(transition);
            transitionset.addTransition(transition1);
            return transitionset;
        }
    }

    private static void noLayoutSuppressionForVisibilityTransitions(Transition transition)
    {
        if(!(transition instanceof Visibility)) goto _L2; else goto _L1
_L1:
        ((Visibility)transition).setSuppressLayout(false);
_L4:
        return;
_L2:
        if(transition instanceof TransitionSet)
        {
            transition = (TransitionSet)transition;
            int i = transition.getTransitionCount();
            int j = 0;
            while(j < i) 
            {
                noLayoutSuppressionForVisibilityTransitions(transition.getTransitionAt(j));
                j++;
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    protected static void removeExcludedViews(Transition transition, ArrayList arraylist)
    {
        ArraySet arrayset = new ArraySet();
        findIncludedViews(transition, arraylist, arrayset);
        arraylist.clear();
        arraylist.addAll(arrayset);
    }

    private static int scaleTypeToInt(android.widget.ImageView.ScaleType scaletype)
    {
        for(int i = 0; i < SCALE_TYPE_VALUES.length; i++)
            if(scaletype == SCALE_TYPE_VALUES[i])
                return i;

        return -1;
    }

    private void setEpicenter(View view)
    {
        if(view == null)
        {
            mEpicenterCallback.setEpicenter(null);
        } else
        {
            Rect rect = new Rect();
            view.getBoundsOnScreen(rect);
            mEpicenterCallback.setEpicenter(rect);
        }
    }

    protected static void setOriginalSharedElementState(ArrayList arraylist, ArrayList arraylist1)
    {
        for(int i = 0; i < arraylist1.size(); i++)
        {
            View view = (View)arraylist.get(i);
            SharedElementOriginalState sharedelementoriginalstate = (SharedElementOriginalState)arraylist1.get(i);
            if((view instanceof ImageView) && sharedelementoriginalstate.mScaleType != null)
            {
                ImageView imageview = (ImageView)view;
                imageview.setScaleType(sharedelementoriginalstate.mScaleType);
                if(sharedelementoriginalstate.mScaleType == android.widget.ImageView.ScaleType.MATRIX)
                    imageview.setImageMatrix(sharedelementoriginalstate.mMatrix);
            }
            view.setElevation(sharedelementoriginalstate.mElevation);
            view.setTranslationZ(sharedelementoriginalstate.mTranslationZ);
            view.measure(android.view.View.MeasureSpec.makeMeasureSpec(sharedelementoriginalstate.mMeasuredWidth, 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(sharedelementoriginalstate.mMeasuredHeight, 0x40000000));
            view.layout(sharedelementoriginalstate.mLeft, sharedelementoriginalstate.mTop, sharedelementoriginalstate.mRight, sharedelementoriginalstate.mBottom);
        }

    }

    private void setSharedElementMatrices()
    {
        int i = mSharedElements.size();
        if(i > 0)
            mSharedElementParentMatrices = new ArrayList(i);
        for(int j = 0; j < i; j++)
        {
            ViewGroup viewgroup = (ViewGroup)((View)mSharedElements.get(j)).getParent();
            Matrix matrix = new Matrix();
            if(viewgroup != null)
            {
                viewgroup.transformMatrixToLocal(matrix);
                matrix.postTranslate(viewgroup.getScrollX(), viewgroup.getScrollY());
            }
            mSharedElementParentMatrices.add(matrix);
        }

    }

    private void setSharedElementState(View view, String s, Bundle bundle, Matrix matrix, RectF rectf, int ai[])
    {
        s = bundle.getBundle(s);
        if(s == null)
            return;
        if(view instanceof ImageView)
        {
            int i = s.getInt("shared_element:scaleType", -1);
            if(i >= 0)
            {
                ImageView imageview = (ImageView)view;
                bundle = SCALE_TYPE_VALUES[i];
                imageview.setScaleType(bundle);
                if(bundle == android.widget.ImageView.ScaleType.MATRIX)
                {
                    matrix.setValues(s.getFloatArray("shared_element:imageMatrix"));
                    imageview.setImageMatrix(matrix);
                }
            }
        }
        view.setTranslationZ(s.getFloat("shared_element:translationZ"));
        view.setElevation(s.getFloat("shared_element:elevation"));
        float f = s.getFloat("shared_element:screenLeft");
        float f1 = s.getFloat("shared_element:screenTop");
        float f2 = s.getFloat("shared_element:screenRight");
        float f3 = s.getFloat("shared_element:screenBottom");
        int j;
        int k;
        int l;
        int i1;
        if(ai != null)
        {
            f -= ai[0];
            f1 -= ai[1];
            f2 -= ai[0];
            f3 -= ai[1];
        } else
        {
            getSharedElementParentMatrix(view, matrix);
            rectf.set(f, f1, f2, f3);
            matrix.mapRect(rectf);
            f = rectf.left;
            f1 = rectf.top;
            view.getInverseMatrix().mapRect(rectf);
            f2 = rectf.width();
            f3 = rectf.height();
            view.setLeft(0);
            view.setTop(0);
            view.setRight(Math.round(f2));
            view.setBottom(Math.round(f3));
            rectf.set(0.0F, 0.0F, f2, f3);
            view.getMatrix().mapRect(rectf);
            f -= rectf.left;
            f1 -= rectf.top;
            f2 = f + f2;
            f3 = f1 + f3;
        }
        k = Math.round(f);
        j = Math.round(f1);
        l = Math.round(f2) - k;
        i1 = Math.round(f3) - j;
        view.measure(android.view.View.MeasureSpec.makeMeasureSpec(l, 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(i1, 0x40000000));
        view.layout(k, j, k + l, j + i1);
    }

    private void setSharedElements(ArrayMap arraymap)
    {
        boolean flag = true;
        do
        {
            if(arraymap.isEmpty())
                break;
            int i = arraymap.size() - 1;
            while(i >= 0) 
            {
                View view = (View)arraymap.valueAt(i);
                String s = (String)arraymap.keyAt(i);
                if(flag && (view == null || view.isAttachedToWindow() ^ true || s == null))
                    arraymap.removeAt(i);
                else
                if(!isNested(view, arraymap))
                {
                    mSharedElementNames.add(s);
                    mSharedElements.add(view);
                    arraymap.removeAt(i);
                }
                i--;
            }
            flag = false;
        } while(true);
    }

    private void showView(View view, boolean flag)
    {
        Float float1 = (Float)mOriginalAlphas.remove(view);
        if(float1 != null)
            view.setAlpha(float1.floatValue());
        if(flag)
            view.setTransitionAlpha(1.0F);
    }

    private void startInputWhenTransitionsComplete()
    {
        if(mViewsTransitionComplete && mSharedElementTransitionComplete)
        {
            Object obj = getDecor();
            if(obj != null)
            {
                obj = ((View) (obj)).getViewRootImpl();
                if(obj != null)
                    ((ViewRootImpl) (obj)).setPausedForTransition(false);
            }
            onTransitionsComplete();
        }
    }

    protected void backgroundAnimatorComplete()
    {
        mBackgroundAnimatorComplete = true;
    }

    protected boolean cancelPendingTransitions()
    {
        mPendingTransition = null;
        return mIsStartingTransition;
    }

    protected Bundle captureSharedElementState()
    {
        Bundle bundle = new Bundle();
        RectF rectf = new RectF();
        Matrix matrix = new Matrix();
        for(int i = 0; i < mSharedElements.size(); i++)
            captureSharedElementState((View)mSharedElements.get(i), (String)mSharedElementNames.get(i), bundle, matrix, rectf);

        return bundle;
    }

    protected void captureSharedElementState(View view, String s, Bundle bundle, Matrix matrix, RectF rectf)
    {
        Bundle bundle1 = new Bundle();
        matrix.reset();
        view.transformMatrixToGlobal(matrix);
        rectf.set(0.0F, 0.0F, view.getWidth(), view.getHeight());
        matrix.mapRect(rectf);
        bundle1.putFloat("shared_element:screenLeft", rectf.left);
        bundle1.putFloat("shared_element:screenRight", rectf.right);
        bundle1.putFloat("shared_element:screenTop", rectf.top);
        bundle1.putFloat("shared_element:screenBottom", rectf.bottom);
        bundle1.putFloat("shared_element:translationZ", view.getTranslationZ());
        bundle1.putFloat("shared_element:elevation", view.getElevation());
        android.os.Parcelable parcelable = null;
        if(mListener != null)
            parcelable = mListener.onCaptureSharedElementSnapshot(view, matrix, rectf);
        if(parcelable != null)
            bundle1.putParcelable("shared_element:bitmap", parcelable);
        if(view instanceof ImageView)
        {
            view = (ImageView)view;
            bundle1.putInt("shared_element:scaleType", scaleTypeToInt(view.getScaleType()));
            if(view.getScaleType() == android.widget.ImageView.ScaleType.MATRIX)
            {
                matrix = new float[9];
                view.getImageMatrix().getValues(matrix);
                bundle1.putFloatArray("shared_element:imageMatrix", matrix);
            }
        }
        bundle.putBundle(s, bundle1);
    }

    protected void clearState()
    {
        mWindow = null;
        mSharedElements.clear();
        mTransitioningViews = null;
        mStrippedTransitioningViews = null;
        mOriginalAlphas.clear();
        mResultReceiver = null;
        mPendingTransition = null;
        mListener = null;
        mSharedElementParentMatrices = null;
    }

    protected Transition configureTransition(Transition transition, boolean flag)
    {
        Transition transition1 = transition;
        if(transition != null)
        {
            transition = transition.clone();
            transition.setEpicenterCallback(mEpicenterCallback);
            transition1 = setTargets(transition, flag);
        }
        noLayoutSuppressionForVisibilityTransitions(transition1);
        return transition1;
    }

    public ArrayList copyMappedViews()
    {
        return new ArrayList(mSharedElements);
    }

    protected ArrayList createSnapshots(Bundle bundle, Collection collection)
    {
        int i = collection.size();
        ArrayList arraylist = new ArrayList(i);
        if(i == 0)
            return arraylist;
        android.content.Context context = getWindow().getContext();
        int ai[] = new int[2];
        ViewGroup viewgroup = getDecor();
        if(viewgroup != null)
            viewgroup.getLocationOnScreen(ai);
        Matrix matrix = new Matrix();
        Object obj;
        for(Iterator iterator = collection.iterator(); iterator.hasNext(); arraylist.add(obj))
        {
            String s = (String)iterator.next();
            collection = bundle.getBundle(s);
            obj = null;
            Object obj1 = null;
            if(collection == null)
                continue;
            obj = collection.getParcelable("shared_element:bitmap");
            collection = obj1;
            if(obj != null)
            {
                collection = obj1;
                if(mListener != null)
                    collection = mListener.onCreateSnapshotView(context, ((android.os.Parcelable) (obj)));
            }
            obj = collection;
            if(collection != null)
            {
                setSharedElementState(collection, s, bundle, matrix, null, ai);
                obj = collection;
            }
        }

        return arraylist;
    }

    public ArrayList getAcceptedNames()
    {
        return mSharedElementNames;
    }

    public ArrayList getAllSharedElementNames()
    {
        return mAllSharedElementNames;
    }

    public ViewGroup getDecor()
    {
        ViewGroup viewgroup = null;
        if(mWindow != null)
            viewgroup = (ViewGroup)mWindow.getDecorView();
        return viewgroup;
    }

    protected long getFadeDuration()
    {
        return getWindow().getTransitionBackgroundFadeDuration();
    }

    public ArrayList getMappedNames()
    {
        ArrayList arraylist = new ArrayList(mSharedElements.size());
        for(int i = 0; i < mSharedElements.size(); i++)
            arraylist.add(((View)mSharedElements.get(i)).getTransitionName());

        return arraylist;
    }

    protected abstract Transition getViewsTransition();

    protected Window getWindow()
    {
        return mWindow;
    }

    protected void hideViews(ArrayList arraylist)
    {
        int i = arraylist.size();
        for(int j = 0; j < i; j++)
        {
            View view = (View)arraylist.get(j);
            if(!mOriginalAlphas.containsKey(view))
                mOriginalAlphas.put(view, Float.valueOf(view.getAlpha()));
            view.setAlpha(0.0F);
        }

    }

    public boolean isTransitionRunning()
    {
        boolean flag;
        if(mViewsTransitionComplete && mSharedElementTransitionComplete)
            flag = mBackgroundAnimatorComplete;
        else
            flag = false;
        return flag ^ true;
    }

    protected boolean isViewsTransitionComplete()
    {
        return mViewsTransitionComplete;
    }

    void lambda$_2D_android_app_ActivityTransitionCoordinator_27512(ArrayList arraylist)
    {
        notifySharedElementEnd(arraylist);
    }

    void lambda$_2D_android_app_ActivityTransitionCoordinator_39166(int i)
    {
        setGhostVisibility(i);
    }

    protected ArrayMap mapSharedElements(ArrayList arraylist, ArrayList arraylist1)
    {
        ArrayMap arraymap = new ArrayMap();
        if(arraylist != null)
        {
            for(int i = 0; i < arraylist.size(); i++)
                arraymap.put((String)arraylist.get(i), (View)arraylist1.get(i));

        } else
        {
            arraylist = getDecor();
            if(arraylist != null)
                arraylist.findNamedViews(arraymap);
        }
        return arraymap;
    }

    protected boolean moveSharedElementWithParent()
    {
        return true;
    }

    protected void moveSharedElementsFromOverlay()
    {
        int i = mGhostViewListeners.size();
        for(int k = 0; k < i; k++)
            ((GhostViewListeners)mGhostViewListeners.get(k)).removeListener();

        mGhostViewListeners.clear();
        if(mWindow == null || mWindow.getSharedElementsUseOverlay() ^ true)
            return;
        ViewGroup viewgroup = getDecor();
        if(viewgroup != null)
        {
            viewgroup.getOverlay();
            int j = mSharedElements.size();
            for(int l = 0; l < j; l++)
                GhostView.removeGhost((View)mSharedElements.get(l));

        }
    }

    protected void moveSharedElementsToOverlay()
    {
        if(mWindow == null || mWindow.getSharedElementsUseOverlay() ^ true)
            return;
        setSharedElementMatrices();
        int i = mSharedElements.size();
        ViewGroup viewgroup = getDecor();
        if(viewgroup != null)
        {
            boolean flag = moveSharedElementWithParent();
            Matrix matrix = new Matrix();
            for(int j = 0; j < i; j++)
            {
                Object obj = (View)mSharedElements.get(j);
                if(!((View) (obj)).isAttachedToWindow())
                    continue;
                matrix.reset();
                ((Matrix)mSharedElementParentMatrices.get(j)).invert(matrix);
                GhostView.addGhost(((View) (obj)), viewgroup, matrix);
                ViewGroup viewgroup1 = (ViewGroup)((View) (obj)).getParent();
                if(flag && isInTransitionGroup(viewgroup1, viewgroup) ^ true)
                {
                    obj = new GhostViewListeners(((View) (obj)), viewgroup1, viewgroup);
                    viewgroup1.getViewTreeObserver().addOnPreDrawListener(((android.view.ViewTreeObserver.OnPreDrawListener) (obj)));
                    viewgroup1.addOnAttachStateChangeListener(((android.view.View.OnAttachStateChangeListener) (obj)));
                    mGhostViewListeners.add(obj);
                }
            }

        }
    }

    protected void notifySharedElementEnd(ArrayList arraylist)
    {
        if(mListener != null)
            mListener.onSharedElementEnd(mSharedElementNames, mSharedElements, arraylist);
    }

    protected void onTransitionsComplete()
    {
    }

    protected void pauseInput()
    {
        Object obj = getDecor();
        if(obj == null)
            obj = null;
        else
            obj = ((View) (obj)).getViewRootImpl();
        if(obj != null)
            ((ViewRootImpl) (obj)).setPausedForTransition(true);
    }

    protected void scheduleGhostVisibilityChange(int i)
    {
        ViewGroup viewgroup = getDecor();
        if(viewgroup != null)
            OneShotPreDrawListener.add(viewgroup, new _.Lambda.w9bG0NLfK6B6UpQKzQS6S1ayAh0(i, this));
    }

    protected void scheduleSetSharedElementEnd(ArrayList arraylist)
    {
        ViewGroup viewgroup = getDecor();
        if(viewgroup != null)
            OneShotPreDrawListener.add(viewgroup, new _.Lambda.Pcw_0289sroTvc5U7X_pS90OouM((byte)0, this, arraylist));
    }

    protected void setEpicenter()
    {
        Object obj = null;
        View view = obj;
        if(!mAllSharedElementNames.isEmpty())
        {
            view = obj;
            if(mSharedElementNames.isEmpty() ^ true)
            {
                int i = mSharedElementNames.indexOf(mAllSharedElementNames.get(0));
                view = obj;
                if(i >= 0)
                    view = (View)mSharedElements.get(i);
            }
        }
        setEpicenter(view);
    }

    protected void setGhostVisibility(int i)
    {
        int j = mSharedElements.size();
        for(int k = 0; k < j; k++)
        {
            GhostView ghostview = GhostView.getGhost((View)mSharedElements.get(k));
            if(ghostview != null)
                ghostview.setVisibility(i);
        }

    }

    protected void setResultReceiver(ResultReceiver resultreceiver)
    {
        mResultReceiver = resultreceiver;
    }

    protected ArrayList setSharedElementState(Bundle bundle, ArrayList arraylist)
    {
        ArrayList arraylist1 = new ArrayList();
        if(bundle != null)
        {
            Matrix matrix = new Matrix();
            RectF rectf = new RectF();
            int i = mSharedElements.size();
            for(int j = 0; j < i; j++)
            {
                View view = (View)mSharedElements.get(j);
                String s = (String)mSharedElementNames.get(j);
                arraylist1.add(getOldSharedElementState(view, s, bundle));
                setSharedElementState(view, s, bundle, matrix, rectf, null);
            }

        }
        if(mListener != null)
            mListener.onSharedElementStart(mSharedElementNames, mSharedElements, arraylist);
        return arraylist1;
    }

    protected Transition setTargets(Transition transition, boolean flag)
    {
        if(transition == null || flag && (mTransitioningViews == null || mTransitioningViews.isEmpty()))
            return null;
        TransitionSet transitionset = new TransitionSet();
        if(mTransitioningViews != null)
        {
            int i = mTransitioningViews.size() - 1;
            while(i >= 0) 
            {
                View view = (View)mTransitioningViews.get(i);
                if(flag)
                    transitionset.addTarget(view);
                else
                    transitionset.excludeTarget(view, true);
                i--;
            }
        }
        if(mStrippedTransitioningViews != null)
        {
            for(int j = mStrippedTransitioningViews.size() - 1; j >= 0; j--)
                transitionset.excludeTarget((View)mStrippedTransitioningViews.get(j), true);

        }
        transitionset.addTransition(transition);
        transition = transitionset;
        if(!flag)
        {
            transition = transitionset;
            if(mTransitioningViews != null)
            {
                transition = transitionset;
                if(mTransitioningViews.isEmpty() ^ true)
                    transition = (new TransitionSet()).addTransition(transitionset);
            }
        }
        return transition;
    }

    protected void setTransitioningViewsVisiblity(int i, boolean flag)
    {
        int j;
        int k;
        if(mTransitioningViews == null)
            j = 0;
        else
            j = mTransitioningViews.size();
        k = 0;
        while(k < j) 
        {
            View view = (View)mTransitioningViews.get(k);
            if(flag)
                view.setVisibility(i);
            else
                view.setTransitionVisibility(i);
            k++;
        }
    }

    protected void sharedElementTransitionComplete()
    {
        mSharedElementTransitionComplete = true;
        startInputWhenTransitionsComplete();
    }

    protected void showViews(ArrayList arraylist, boolean flag)
    {
        int i = arraylist.size();
        for(int j = 0; j < i; j++)
            showView((View)arraylist.get(j), flag);

    }

    protected void startTransition(Runnable runnable)
    {
        if(mIsStartingTransition)
        {
            mPendingTransition = runnable;
        } else
        {
            mIsStartingTransition = true;
            runnable.run();
        }
    }

    protected void stripOffscreenViews()
    {
        if(mTransitioningViews == null)
            return;
        Rect rect = new Rect();
        for(int i = mTransitioningViews.size() - 1; i >= 0; i--)
        {
            View view = (View)mTransitioningViews.get(i);
            if(!view.getGlobalVisibleRect(rect))
            {
                mTransitioningViews.remove(i);
                mStrippedTransitioningViews.add(view);
            }
        }

    }

    protected void transitionStarted()
    {
        mIsStartingTransition = false;
    }

    protected void viewsReady(ArrayMap arraymap)
    {
        arraymap.retainAll(mAllSharedElementNames);
        if(mListener != null)
            mListener.onMapSharedElements(mAllSharedElementNames, arraymap);
        setSharedElements(arraymap);
        if(getViewsTransition() != null && mTransitioningViews != null)
        {
            arraymap = getDecor();
            if(arraymap != null)
                arraymap.captureTransitioningViews(mTransitioningViews);
            mTransitioningViews.removeAll(mSharedElements);
        }
        setEpicenter();
    }

    protected void viewsTransitionComplete()
    {
        mViewsTransitionComplete = true;
        startInputWhenTransitionsComplete();
    }

    protected static final String KEY_ELEVATION = "shared_element:elevation";
    protected static final String KEY_IMAGE_MATRIX = "shared_element:imageMatrix";
    static final String KEY_REMOTE_RECEIVER = "android:remoteReceiver";
    protected static final String KEY_SCALE_TYPE = "shared_element:scaleType";
    protected static final String KEY_SCREEN_BOTTOM = "shared_element:screenBottom";
    protected static final String KEY_SCREEN_LEFT = "shared_element:screenLeft";
    protected static final String KEY_SCREEN_RIGHT = "shared_element:screenRight";
    protected static final String KEY_SCREEN_TOP = "shared_element:screenTop";
    protected static final String KEY_SNAPSHOT = "shared_element:bitmap";
    protected static final String KEY_TRANSLATION_Z = "shared_element:translationZ";
    public static final int MSG_CANCEL = 106;
    public static final int MSG_EXIT_TRANSITION_COMPLETE = 104;
    public static final int MSG_HIDE_SHARED_ELEMENTS = 101;
    public static final int MSG_SET_REMOTE_RECEIVER = 100;
    public static final int MSG_SHARED_ELEMENT_DESTINATION = 107;
    public static final int MSG_START_EXIT_TRANSITION = 105;
    public static final int MSG_TAKE_SHARED_ELEMENTS = 103;
    protected static final android.widget.ImageView.ScaleType SCALE_TYPE_VALUES[] = android.widget.ImageView.ScaleType.values();
    private static final String TAG = "ActivityTransitionCoordinator";
    protected final ArrayList mAllSharedElementNames;
    private boolean mBackgroundAnimatorComplete;
    private final FixedEpicenterCallback mEpicenterCallback = new FixedEpicenterCallback(null);
    private ArrayList mGhostViewListeners;
    protected final boolean mIsReturning;
    private boolean mIsStartingTransition;
    protected SharedElementCallback mListener;
    private ArrayMap mOriginalAlphas;
    private Runnable mPendingTransition;
    protected ResultReceiver mResultReceiver;
    protected final ArrayList mSharedElementNames = new ArrayList();
    private ArrayList mSharedElementParentMatrices;
    private boolean mSharedElementTransitionComplete;
    protected final ArrayList mSharedElements = new ArrayList();
    private ArrayList mStrippedTransitioningViews;
    protected ArrayList mTransitioningViews;
    private boolean mViewsTransitionComplete;
    private Window mWindow;

}
