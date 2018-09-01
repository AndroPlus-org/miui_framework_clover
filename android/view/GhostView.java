// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.widget.FrameLayout;
import java.util.ArrayList;

// Referenced classes of package android.view:
//            View, ViewGroup, ViewGroupOverlay, DisplayListCanvas, 
//            RenderNode

public class GhostView extends View
{

    private GhostView(View view)
    {
        super(view.getContext());
        mView = view;
        mView.mGhostView = this;
        view = (ViewGroup)mView.getParent();
        mView.setTransitionVisibility(4);
        view.invalidate();
    }

    public static GhostView addGhost(View view, ViewGroup viewgroup)
    {
        return addGhost(view, viewgroup, null);
    }

    public static GhostView addGhost(View view, ViewGroup viewgroup, Matrix matrix)
    {
        ViewGroupOverlay viewgroupoverlay;
        Object obj;
        int j;
        if(!(view.getParent() instanceof ViewGroup))
            throw new IllegalArgumentException("Ghosted views must be parented by a ViewGroup");
        viewgroupoverlay = viewgroup.getOverlay();
        ViewOverlay.OverlayViewGroup overlayviewgroup = viewgroupoverlay.mOverlayViewGroup;
        GhostView ghostview = view.mGhostView;
        boolean flag = false;
        obj = ghostview;
        j = ((flag) ? 1 : 0);
        if(ghostview != null)
        {
            View view1 = (View)ghostview.getParent();
            ViewGroup viewgroup1 = (ViewGroup)view1.getParent();
            obj = ghostview;
            j = ((flag) ? 1 : 0);
            if(viewgroup1 != overlayviewgroup)
            {
                j = ghostview.mReferences;
                viewgroup1.removeView(view1);
                obj = null;
            }
        }
        if(obj != null) goto _L2; else goto _L1
_L1:
        obj = matrix;
        if(matrix == null)
        {
            obj = new Matrix();
            calculateMatrix(view, viewgroup, ((Matrix) (obj)));
        }
        matrix = new GhostView(view);
        matrix.setMatrix(((Matrix) (obj)));
        view = new FrameLayout(view.getContext());
        view.setClipChildren(false);
        copySize(viewgroup, view);
        copySize(viewgroup, matrix);
        view.addView(matrix);
        viewgroup = new ArrayList();
        int i = moveGhostViewsToTop(viewgroupoverlay.mOverlayViewGroup, viewgroup);
        insertIntoOverlay(viewgroupoverlay.mOverlayViewGroup, view, matrix, viewgroup, i);
        matrix.mReferences = j;
        view = matrix;
_L4:
        view.mReferences = ((GhostView) (view)).mReferences + 1;
        return view;
_L2:
        view = ((View) (obj));
        if(matrix != null)
        {
            ((GhostView) (obj)).setMatrix(matrix);
            view = ((View) (obj));
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static void calculateMatrix(View view, ViewGroup viewgroup, Matrix matrix)
    {
        view = (ViewGroup)view.getParent();
        matrix.reset();
        view.transformMatrixToGlobal(matrix);
        matrix.preTranslate(-view.getScrollX(), -view.getScrollY());
        viewgroup.transformMatrixToLocal(matrix);
    }

    private static void copySize(View view, View view1)
    {
        view1.setLeft(0);
        view1.setTop(0);
        view1.setRight(view.getWidth());
        view1.setBottom(view.getHeight());
    }

    public static GhostView getGhost(View view)
    {
        return view.mGhostView;
    }

    private static int getInsertIndex(ViewGroup viewgroup, ArrayList arraylist, ArrayList arraylist1, int i)
    {
        int j = viewgroup.getChildCount() - 1;
        while(i <= j) 
        {
            int k = (i + j) / 2;
            getParents(((GhostView)((ViewGroup)viewgroup.getChildAt(k)).getChildAt(0)).mView, arraylist1);
            if(isOnTop(arraylist, arraylist1))
                i = k + 1;
            else
                j = k - 1;
            arraylist1.clear();
        }
        return i;
    }

    private static void getParents(View view, ArrayList arraylist)
    {
        ViewParent viewparent = view.getParent();
        if(viewparent != null && (viewparent instanceof ViewGroup))
            getParents((View)viewparent, arraylist);
        arraylist.add(view);
    }

    private static void insertIntoOverlay(ViewGroup viewgroup, ViewGroup viewgroup1, GhostView ghostview, ArrayList arraylist, int i)
    {
        if(i == -1)
        {
            viewgroup.addView(viewgroup1);
        } else
        {
            ArrayList arraylist1 = new ArrayList();
            getParents(ghostview.mView, arraylist1);
            i = getInsertIndex(viewgroup, arraylist1, arraylist, i);
            if(i < 0 || i >= viewgroup.getChildCount())
                viewgroup.addView(viewgroup1);
            else
                viewgroup.addView(viewgroup1, i);
        }
    }

    private static boolean isGhostWrapper(View view)
    {
        if(view instanceof FrameLayout)
        {
            view = (FrameLayout)view;
            if(view.getChildCount() == 1)
                return view.getChildAt(0) instanceof GhostView;
        }
        return false;
    }

    private static boolean isOnTop(View view, View view1)
    {
        ArrayList arraylist;
        int j;
        boolean flag2;
        View view2;
        ViewGroup viewgroup = (ViewGroup)view.getParent();
        int i = viewgroup.getChildCount();
        arraylist = viewgroup.buildOrderedChildList();
        boolean flag;
        boolean flag1;
        if(arraylist == null)
            flag = viewgroup.isChildrenDrawingOrderEnabled();
        else
            flag = false;
        flag1 = true;
        j = 0;
_L7:
        flag2 = flag1;
        if(j >= i) goto _L2; else goto _L1
_L1:
        int k;
        if(flag)
            k = viewgroup.getChildDrawingOrder(i, j);
        else
            k = j;
        if(arraylist == null)
            view2 = viewgroup.getChildAt(k);
        else
            view2 = (View)arraylist.get(k);
        if(view2 != view) goto _L4; else goto _L3
_L3:
        flag2 = false;
_L2:
        if(arraylist != null)
            arraylist.clear();
        return flag2;
_L4:
        if(view2 != view1)
            break; /* Loop/switch isn't completed */
        flag2 = true;
        if(true) goto _L2; else goto _L5
_L5:
        j++;
        if(true) goto _L7; else goto _L6
_L6:
    }

    private static boolean isOnTop(ArrayList arraylist, ArrayList arraylist1)
    {
        if(arraylist.isEmpty() || arraylist1.isEmpty() || arraylist.get(0) != arraylist1.get(0))
            return true;
        int i = Math.min(arraylist.size(), arraylist1.size());
        for(int j = 1; j < i; j++)
        {
            View view = (View)arraylist.get(j);
            View view1 = (View)arraylist1.get(j);
            if(view != view1)
                return isOnTop(view, view1);
        }

        boolean flag;
        if(arraylist1.size() == i)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static int moveGhostViewsToTop(ViewGroup viewgroup, ArrayList arraylist)
    {
        int i = viewgroup.getChildCount();
        if(i == 0)
            return -1;
        if(isGhostWrapper(viewgroup.getChildAt(i - 1)))
        {
            int j = i - 1;
            i -= 2;
            do
            {
                if(i < 0 || !isGhostWrapper(viewgroup.getChildAt(i)))
                    return j;
                j = i;
                i--;
            } while(true);
        }
        for(i -= 2; i >= 0; i--)
        {
            Object obj = viewgroup.getChildAt(i);
            if(isGhostWrapper(((View) (obj))))
            {
                arraylist.add(obj);
                obj = (GhostView)((ViewGroup)obj).getChildAt(0);
                obj.mBeingMoved = true;
                viewgroup.removeViewAt(i);
                obj.mBeingMoved = false;
            }
        }

        if(arraylist.isEmpty())
        {
            i = -1;
        } else
        {
            int k = viewgroup.getChildCount();
            for(i = arraylist.size() - 1; i >= 0; i--)
                viewgroup.addView((View)arraylist.get(i));

            arraylist.clear();
            i = k;
        }
        return i;
    }

    public static void removeGhost(View view)
    {
        view = view.mGhostView;
        if(view != null)
        {
            view.mReferences = ((GhostView) (view)).mReferences - 1;
            if(((GhostView) (view)).mReferences == 0)
            {
                view = (ViewGroup)view.getParent();
                ((ViewGroup)view.getParent()).removeView(view);
            }
        }
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        if(!mBeingMoved)
        {
            mView.setTransitionVisibility(0);
            mView.mGhostView = null;
            ViewGroup viewgroup = (ViewGroup)mView.getParent();
            if(viewgroup != null)
                viewgroup.invalidate();
        }
    }

    protected void onDraw(Canvas canvas)
    {
        if(canvas instanceof DisplayListCanvas)
        {
            DisplayListCanvas displaylistcanvas = (DisplayListCanvas)canvas;
            mView.mRecreateDisplayList = true;
            canvas = mView.updateDisplayListIfDirty();
            if(canvas.isValid())
            {
                displaylistcanvas.insertReorderBarrier();
                displaylistcanvas.drawRenderNode(canvas);
                displaylistcanvas.insertInorderBarrier();
            }
        }
    }

    public void setMatrix(Matrix matrix)
    {
        mRenderNode.setAnimationMatrix(matrix);
    }

    public void setVisibility(int i)
    {
        super.setVisibility(i);
        if(mView.mGhostView == this)
        {
            if(i == 0)
                i = 4;
            else
                i = 0;
            mView.setTransitionVisibility(i);
        }
    }

    private boolean mBeingMoved;
    private int mReferences;
    private final View mView;
}
