// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.transition;

import android.util.ArrayMap;
import android.view.*;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package android.transition:
//            AutoTransition, Transition, Scene, TransitionListenerAdapter

public class TransitionManager
{
    private static class MultiListener
        implements android.view.ViewTreeObserver.OnPreDrawListener, android.view.View.OnAttachStateChangeListener
    {

        private void removeListeners()
        {
            if(mViewTreeObserver.isAlive())
                mViewTreeObserver.removeOnPreDrawListener(this);
            else
                mSceneRoot.getViewTreeObserver().removeOnPreDrawListener(this);
            mSceneRoot.removeOnAttachStateChangeListener(this);
        }

        public boolean onPreDraw()
        {
            ArrayMap arraymap;
            ArrayList arraylist;
            ArrayList arraylist1;
            removeListeners();
            if(!TransitionManager._2D_get0().remove(mSceneRoot))
                return true;
            arraymap = TransitionManager._2D_wrap0();
            arraylist = (ArrayList)arraymap.get(mSceneRoot);
            arraylist1 = null;
            if(arraylist != null) goto _L2; else goto _L1
_L1:
            Object obj;
            obj = new ArrayList();
            arraymap.put(mSceneRoot, obj);
_L4:
            ((ArrayList) (obj)).add(mTransition);
            mTransition.addListener(arraymap. new TransitionListenerAdapter() {

                public void onTransitionEnd(Transition transition)
                {
                    ((ArrayList)runningTransitions.get(mSceneRoot)).remove(transition);
                }

                final MultiListener this$1;
                final ArrayMap val$runningTransitions;

            
            {
                this$1 = final_multilistener;
                runningTransitions = ArrayMap.this;
                super();
            }
            }
);
            mTransition.captureValues(mSceneRoot, false);
            if(arraylist1 != null)
                for(obj = arraylist1.iterator(); ((Iterator) (obj)).hasNext(); ((Transition)((Iterator) (obj)).next()).resume(mSceneRoot));
            break; /* Loop/switch isn't completed */
_L2:
            obj = arraylist;
            if(arraylist.size() > 0)
            {
                arraylist1 = new ArrayList(arraylist);
                obj = arraylist;
            }
            if(true) goto _L4; else goto _L3
_L3:
            mTransition.playTransition(mSceneRoot);
            return true;
        }

        public void onViewAttachedToWindow(View view)
        {
        }

        public void onViewDetachedFromWindow(View view)
        {
            removeListeners();
            TransitionManager._2D_get0().remove(mSceneRoot);
            view = (ArrayList)TransitionManager._2D_wrap0().get(mSceneRoot);
            if(view != null && view.size() > 0)
                for(view = view.iterator(); view.hasNext(); ((Transition)view.next()).resume(mSceneRoot));
            mTransition.clearValues(true);
        }

        ViewGroup mSceneRoot;
        Transition mTransition;
        final ViewTreeObserver mViewTreeObserver;

        MultiListener(Transition transition, ViewGroup viewgroup)
        {
            mTransition = transition;
            mSceneRoot = viewgroup;
            mViewTreeObserver = mSceneRoot.getViewTreeObserver();
        }
    }


    static ArrayList _2D_get0()
    {
        return sPendingTransitions;
    }

    static ArrayMap _2D_wrap0()
    {
        return getRunningTransitions();
    }

    public TransitionManager()
    {
        mSceneTransitions = new ArrayMap();
        mScenePairTransitions = new ArrayMap();
    }

    public static void beginDelayedTransition(ViewGroup viewgroup)
    {
        beginDelayedTransition(viewgroup, null);
    }

    public static void beginDelayedTransition(ViewGroup viewgroup, Transition transition)
    {
        if(!sPendingTransitions.contains(viewgroup) && viewgroup.isLaidOut())
        {
            sPendingTransitions.add(viewgroup);
            Transition transition1 = transition;
            if(transition == null)
                transition1 = sDefaultTransition;
            transition = transition1.clone();
            sceneChangeSetup(viewgroup, transition);
            Scene.setCurrentScene(viewgroup, null);
            sceneChangeRunTransition(viewgroup, transition);
        }
    }

    private static void changeScene(Scene scene, Transition transition)
    {
        ViewGroup viewgroup = scene.getSceneRoot();
        if(!sPendingTransitions.contains(viewgroup))
            if(transition == null)
            {
                scene.enter();
            } else
            {
                sPendingTransitions.add(viewgroup);
                Transition transition1 = transition.clone();
                transition1.setSceneRoot(viewgroup);
                transition = Scene.getCurrentScene(viewgroup);
                if(transition != null && transition.isCreatedFromLayoutResource())
                    transition1.setCanRemoveViews(true);
                sceneChangeSetup(viewgroup, transition1);
                scene.enter();
                sceneChangeRunTransition(viewgroup, transition1);
            }
    }

    public static void endTransitions(ViewGroup viewgroup)
    {
        sPendingTransitions.remove(viewgroup);
        ArrayList arraylist = (ArrayList)getRunningTransitions().get(viewgroup);
        if(arraylist != null && arraylist.isEmpty() ^ true)
        {
            arraylist = new ArrayList(arraylist);
            for(int i = arraylist.size() - 1; i >= 0; i--)
                ((Transition)arraylist.get(i)).forceToEnd(viewgroup);

        }
    }

    public static Transition getDefaultTransition()
    {
        return sDefaultTransition;
    }

    private static ArrayMap getRunningTransitions()
    {
        Object obj;
label0:
        {
            obj = (WeakReference)sRunningTransitions.get();
            if(obj != null)
            {
                ArrayMap arraymap = (ArrayMap)((WeakReference) (obj)).get();
                obj = arraymap;
                if(arraymap != null)
                    break label0;
            }
            obj = new ArrayMap();
            WeakReference weakreference = new WeakReference(obj);
            sRunningTransitions.set(weakreference);
        }
        return ((ArrayMap) (obj));
    }

    public static void go(Scene scene)
    {
        changeScene(scene, sDefaultTransition);
    }

    public static void go(Scene scene, Transition transition)
    {
        changeScene(scene, transition);
    }

    private static void sceneChangeRunTransition(ViewGroup viewgroup, Transition transition)
    {
        if(transition != null && viewgroup != null)
        {
            transition = new MultiListener(transition, viewgroup);
            viewgroup.addOnAttachStateChangeListener(transition);
            viewgroup.getViewTreeObserver().addOnPreDrawListener(transition);
        }
    }

    private static void sceneChangeSetup(ViewGroup viewgroup, Transition transition)
    {
        Object obj = (ArrayList)getRunningTransitions().get(viewgroup);
        if(obj != null && ((ArrayList) (obj)).size() > 0)
            for(obj = ((Iterable) (obj)).iterator(); ((Iterator) (obj)).hasNext(); ((Transition)((Iterator) (obj)).next()).pause(viewgroup));
        if(transition != null)
            transition.captureValues(viewgroup, true);
        viewgroup = Scene.getCurrentScene(viewgroup);
        if(viewgroup != null)
            viewgroup.exit();
    }

    public Transition getTransition(Scene scene)
    {
        ViewGroup viewgroup = scene.getSceneRoot();
        if(viewgroup != null)
        {
            Scene scene1 = Scene.getCurrentScene(viewgroup);
            if(scene1 != null)
            {
                Object obj = (ArrayMap)mScenePairTransitions.get(scene);
                if(obj != null)
                {
                    obj = (Transition)((ArrayMap) (obj)).get(scene1);
                    if(obj != null)
                        return ((Transition) (obj));
                }
            }
        }
        scene = (Transition)mSceneTransitions.get(scene);
        if(scene == null)
            scene = sDefaultTransition;
        return scene;
    }

    public void setDefaultTransition(Transition transition)
    {
        sDefaultTransition = transition;
    }

    public void setTransition(Scene scene, Scene scene1, Transition transition)
    {
        ArrayMap arraymap = (ArrayMap)mScenePairTransitions.get(scene1);
        ArrayMap arraymap1 = arraymap;
        if(arraymap == null)
        {
            arraymap1 = new ArrayMap();
            mScenePairTransitions.put(scene1, arraymap1);
        }
        arraymap1.put(scene, transition);
    }

    public void setTransition(Scene scene, Transition transition)
    {
        mSceneTransitions.put(scene, transition);
    }

    public void transitionTo(Scene scene)
    {
        changeScene(scene, getTransition(scene));
    }

    private static final String EMPTY_STRINGS[] = new String[0];
    private static String LOG_TAG = "TransitionManager";
    private static Transition sDefaultTransition = new AutoTransition();
    private static ArrayList sPendingTransitions = new ArrayList();
    private static ThreadLocal sRunningTransitions = new ThreadLocal();
    ArrayMap mScenePairTransitions;
    ArrayMap mSceneTransitions;

}
