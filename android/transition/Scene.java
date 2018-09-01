// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.transition;

import android.content.Context;
import android.util.SparseArray;
import android.view.*;

public final class Scene
{

    public Scene(ViewGroup viewgroup)
    {
        mLayoutId = -1;
        mSceneRoot = viewgroup;
    }

    private Scene(ViewGroup viewgroup, int i, Context context)
    {
        mLayoutId = -1;
        mContext = context;
        mSceneRoot = viewgroup;
        mLayoutId = i;
    }

    public Scene(ViewGroup viewgroup, View view)
    {
        mLayoutId = -1;
        mSceneRoot = viewgroup;
        mLayout = view;
    }

    public Scene(ViewGroup viewgroup, ViewGroup viewgroup1)
    {
        mLayoutId = -1;
        mSceneRoot = viewgroup;
        mLayout = viewgroup1;
    }

    static Scene getCurrentScene(View view)
    {
        return (Scene)view.getTag(0x102020d);
    }

    public static Scene getSceneForLayout(ViewGroup viewgroup, int i, Context context)
    {
        Object obj = (SparseArray)viewgroup.getTag(0x10203bd);
        SparseArray sparsearray = ((SparseArray) (obj));
        if(obj == null)
        {
            sparsearray = new SparseArray();
            viewgroup.setTagInternal(0x10203bd, sparsearray);
        }
        obj = (Scene)sparsearray.get(i);
        if(obj != null)
        {
            return ((Scene) (obj));
        } else
        {
            viewgroup = new Scene(viewgroup, i, context);
            sparsearray.put(i, viewgroup);
            return viewgroup;
        }
    }

    static void setCurrentScene(View view, Scene scene)
    {
        view.setTagInternal(0x102020d, scene);
    }

    public void enter()
    {
        if(mLayoutId > 0 || mLayout != null)
        {
            getSceneRoot().removeAllViews();
            if(mLayoutId > 0)
                LayoutInflater.from(mContext).inflate(mLayoutId, mSceneRoot);
            else
                mSceneRoot.addView(mLayout);
        }
        if(mEnterAction != null)
            mEnterAction.run();
        setCurrentScene(mSceneRoot, this);
    }

    public void exit()
    {
        if(getCurrentScene(mSceneRoot) == this && mExitAction != null)
            mExitAction.run();
    }

    public ViewGroup getSceneRoot()
    {
        return mSceneRoot;
    }

    boolean isCreatedFromLayoutResource()
    {
        boolean flag = false;
        if(mLayoutId > 0)
            flag = true;
        return flag;
    }

    public void setEnterAction(Runnable runnable)
    {
        mEnterAction = runnable;
    }

    public void setExitAction(Runnable runnable)
    {
        mExitAction = runnable;
    }

    private Context mContext;
    Runnable mEnterAction;
    Runnable mExitAction;
    private View mLayout;
    private int mLayoutId;
    private ViewGroup mSceneRoot;
}
