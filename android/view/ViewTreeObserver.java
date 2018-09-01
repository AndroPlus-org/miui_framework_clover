// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.Rect;
import android.graphics.Region;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

// Referenced classes of package android.view:
//            View

public final class ViewTreeObserver
{
    static class CopyOnWriteArray
    {

        private ArrayList getArray()
        {
            if(mStart)
            {
                if(mDataCopy == null)
                    mDataCopy = new ArrayList(mData);
                return mDataCopy;
            } else
            {
                return mData;
            }
        }

        void add(Object obj)
        {
            getArray().add(obj);
        }

        void addAll(CopyOnWriteArray copyonwritearray)
        {
            getArray().addAll(copyonwritearray.mData);
        }

        void clear()
        {
            getArray().clear();
        }

        void end()
        {
            if(!mStart)
                throw new IllegalStateException("Iteration not started");
            mStart = false;
            if(mDataCopy != null)
            {
                mData = mDataCopy;
                Access._2D_get0(mAccess).clear();
                Access._2D_set1(mAccess, 0);
            }
            mDataCopy = null;
        }

        void remove(Object obj)
        {
            getArray().remove(obj);
        }

        int size()
        {
            return getArray().size();
        }

        Access start()
        {
            if(mStart)
            {
                throw new IllegalStateException("Iteration already started");
            } else
            {
                mStart = true;
                mDataCopy = null;
                Access._2D_set0(mAccess, mData);
                Access._2D_set1(mAccess, mData.size());
                return mAccess;
            }
        }

        private final Access mAccess = new Access();
        private ArrayList mData;
        private ArrayList mDataCopy;
        private boolean mStart;

        CopyOnWriteArray()
        {
            mData = new ArrayList();
        }
    }

    static class CopyOnWriteArray.Access
    {

        static ArrayList _2D_get0(CopyOnWriteArray.Access access)
        {
            return access.mData;
        }

        static ArrayList _2D_set0(CopyOnWriteArray.Access access, ArrayList arraylist)
        {
            access.mData = arraylist;
            return arraylist;
        }

        static int _2D_set1(CopyOnWriteArray.Access access, int i)
        {
            access.mSize = i;
            return i;
        }

        Object get(int i)
        {
            return mData.get(i);
        }

        int size()
        {
            return mSize;
        }

        private ArrayList mData;
        private int mSize;

        CopyOnWriteArray.Access()
        {
        }
    }

    public static final class InternalInsetsInfo
    {

        public boolean equals(Object obj)
        {
            boolean flag = false;
            if(this == obj)
                return true;
            if(obj == null || getClass() != obj.getClass())
                return false;
            obj = (InternalInsetsInfo)obj;
            boolean flag1 = flag;
            if(mTouchableInsets == ((InternalInsetsInfo) (obj)).mTouchableInsets)
            {
                flag1 = flag;
                if(contentInsets.equals(((InternalInsetsInfo) (obj)).contentInsets))
                {
                    flag1 = flag;
                    if(visibleInsets.equals(((InternalInsetsInfo) (obj)).visibleInsets))
                        flag1 = touchableRegion.equals(((InternalInsetsInfo) (obj)).touchableRegion);
                }
            }
            return flag1;
        }

        public int hashCode()
        {
            return ((contentInsets.hashCode() * 31 + visibleInsets.hashCode()) * 31 + touchableRegion.hashCode()) * 31 + mTouchableInsets;
        }

        boolean isEmpty()
        {
            boolean flag = false;
            boolean flag1 = flag;
            if(contentInsets.isEmpty())
            {
                flag1 = flag;
                if(visibleInsets.isEmpty())
                {
                    flag1 = flag;
                    if(touchableRegion.isEmpty())
                    {
                        flag1 = flag;
                        if(mTouchableInsets == 0)
                            flag1 = true;
                    }
                }
            }
            return flag1;
        }

        void reset()
        {
            contentInsets.setEmpty();
            visibleInsets.setEmpty();
            touchableRegion.setEmpty();
            mTouchableInsets = 0;
        }

        void set(InternalInsetsInfo internalinsetsinfo)
        {
            contentInsets.set(internalinsetsinfo.contentInsets);
            visibleInsets.set(internalinsetsinfo.visibleInsets);
            touchableRegion.set(internalinsetsinfo.touchableRegion);
            mTouchableInsets = internalinsetsinfo.mTouchableInsets;
        }

        public void setTouchableInsets(int i)
        {
            mTouchableInsets = i;
        }

        public static final int TOUCHABLE_INSETS_CONTENT = 1;
        public static final int TOUCHABLE_INSETS_FRAME = 0;
        public static final int TOUCHABLE_INSETS_REGION = 3;
        public static final int TOUCHABLE_INSETS_VISIBLE = 2;
        public final Rect contentInsets = new Rect();
        int mTouchableInsets;
        public final Region touchableRegion = new Region();
        public final Rect visibleInsets = new Rect();

        public InternalInsetsInfo()
        {
        }
    }

    public static interface OnComputeInternalInsetsListener
    {

        public abstract void onComputeInternalInsets(InternalInsetsInfo internalinsetsinfo);
    }

    public static interface OnDrawListener
    {

        public abstract void onDraw();
    }

    public static interface OnEnterAnimationCompleteListener
    {

        public abstract void onEnterAnimationComplete();
    }

    public static interface OnGlobalFocusChangeListener
    {

        public abstract void onGlobalFocusChanged(View view, View view1);
    }

    public static interface OnGlobalLayoutListener
    {

        public abstract void onGlobalLayout();
    }

    public static interface OnPreDrawListener
    {

        public abstract boolean onPreDraw();
    }

    public static interface OnScrollChangedListener
    {

        public abstract void onScrollChanged();
    }

    public static interface OnTouchModeChangeListener
    {

        public abstract void onTouchModeChanged(boolean flag);
    }

    public static interface OnWindowAttachListener
    {

        public abstract void onWindowAttached();

        public abstract void onWindowDetached();
    }

    public static interface OnWindowFocusChangeListener
    {

        public abstract void onWindowFocusChanged(boolean flag);
    }

    public static interface OnWindowShownListener
    {

        public abstract void onWindowShown();
    }


    ViewTreeObserver(Context context)
    {
        boolean flag = true;
        super();
        mAlive = true;
        if(context.getApplicationInfo().targetSdkVersion < 26)
            flag = false;
        sIllegalOnDrawModificationIsFatal = flag;
    }

    private void checkIsAlive()
    {
        if(!mAlive)
            throw new IllegalStateException("This ViewTreeObserver is not alive, call getViewTreeObserver() again");
        else
            return;
    }

    private void kill()
    {
        mAlive = false;
    }

    public void addOnComputeInternalInsetsListener(OnComputeInternalInsetsListener oncomputeinternalinsetslistener)
    {
        checkIsAlive();
        if(mOnComputeInternalInsetsListeners == null)
            mOnComputeInternalInsetsListeners = new CopyOnWriteArray();
        mOnComputeInternalInsetsListeners.add(oncomputeinternalinsetslistener);
    }

    public void addOnDrawListener(OnDrawListener ondrawlistener)
    {
        checkIsAlive();
        if(mOnDrawListeners == null)
            mOnDrawListeners = new ArrayList();
        if(mInDispatchOnDraw)
        {
            IllegalStateException illegalstateexception = new IllegalStateException("Cannot call addOnDrawListener inside of onDraw");
            if(sIllegalOnDrawModificationIsFatal)
                throw illegalstateexception;
            Log.e("ViewTreeObserver", illegalstateexception.getMessage(), illegalstateexception);
        }
        mOnDrawListeners.add(ondrawlistener);
    }

    public void addOnEnterAnimationCompleteListener(OnEnterAnimationCompleteListener onenteranimationcompletelistener)
    {
        checkIsAlive();
        if(mOnEnterAnimationCompleteListeners == null)
            mOnEnterAnimationCompleteListeners = new CopyOnWriteArrayList();
        mOnEnterAnimationCompleteListeners.add(onenteranimationcompletelistener);
    }

    public void addOnGlobalFocusChangeListener(OnGlobalFocusChangeListener onglobalfocuschangelistener)
    {
        checkIsAlive();
        if(mOnGlobalFocusListeners == null)
            mOnGlobalFocusListeners = new CopyOnWriteArrayList();
        mOnGlobalFocusListeners.add(onglobalfocuschangelistener);
    }

    public void addOnGlobalLayoutListener(OnGlobalLayoutListener ongloballayoutlistener)
    {
        checkIsAlive();
        if(mOnGlobalLayoutListeners == null)
            mOnGlobalLayoutListeners = new CopyOnWriteArray();
        mOnGlobalLayoutListeners.add(ongloballayoutlistener);
    }

    public void addOnPreDrawListener(OnPreDrawListener onpredrawlistener)
    {
        checkIsAlive();
        if(mOnPreDrawListeners == null)
            mOnPreDrawListeners = new CopyOnWriteArray();
        mOnPreDrawListeners.add(onpredrawlistener);
    }

    public void addOnScrollChangedListener(OnScrollChangedListener onscrollchangedlistener)
    {
        checkIsAlive();
        if(mOnScrollChangedListeners == null)
            mOnScrollChangedListeners = new CopyOnWriteArray();
        mOnScrollChangedListeners.add(onscrollchangedlistener);
    }

    public void addOnTouchModeChangeListener(OnTouchModeChangeListener ontouchmodechangelistener)
    {
        checkIsAlive();
        if(mOnTouchModeChangeListeners == null)
            mOnTouchModeChangeListeners = new CopyOnWriteArrayList();
        mOnTouchModeChangeListeners.add(ontouchmodechangelistener);
    }

    public void addOnWindowAttachListener(OnWindowAttachListener onwindowattachlistener)
    {
        checkIsAlive();
        if(mOnWindowAttachListeners == null)
            mOnWindowAttachListeners = new CopyOnWriteArrayList();
        mOnWindowAttachListeners.add(onwindowattachlistener);
    }

    public void addOnWindowFocusChangeListener(OnWindowFocusChangeListener onwindowfocuschangelistener)
    {
        checkIsAlive();
        if(mOnWindowFocusListeners == null)
            mOnWindowFocusListeners = new CopyOnWriteArrayList();
        mOnWindowFocusListeners.add(onwindowfocuschangelistener);
    }

    public void addOnWindowShownListener(OnWindowShownListener onwindowshownlistener)
    {
        checkIsAlive();
        if(mOnWindowShownListeners == null)
            mOnWindowShownListeners = new CopyOnWriteArray();
        mOnWindowShownListeners.add(onwindowshownlistener);
        if(mWindowShown)
            onwindowshownlistener.onWindowShown();
    }

    final void dispatchOnComputeInternalInsets(InternalInsetsInfo internalinsetsinfo)
    {
        CopyOnWriteArray copyonwritearray;
        CopyOnWriteArray.Access access;
        copyonwritearray = mOnComputeInternalInsetsListeners;
        if(copyonwritearray == null || copyonwritearray.size() <= 0)
            break MISSING_BLOCK_LABEL_62;
        access = copyonwritearray.start();
        int i = access.size();
        int j = 0;
_L2:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        ((OnComputeInternalInsetsListener)access.get(j)).onComputeInternalInsets(internalinsetsinfo);
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        copyonwritearray.end();
        return;
        internalinsetsinfo;
        copyonwritearray.end();
        throw internalinsetsinfo;
    }

    public final void dispatchOnDraw()
    {
        if(mOnDrawListeners != null)
        {
            mInDispatchOnDraw = true;
            ArrayList arraylist = mOnDrawListeners;
            int i = arraylist.size();
            for(int j = 0; j < i; j++)
                ((OnDrawListener)arraylist.get(j)).onDraw();

            mInDispatchOnDraw = false;
        }
    }

    public final void dispatchOnEnterAnimationComplete()
    {
        Object obj = mOnEnterAnimationCompleteListeners;
        if(obj != null && ((CopyOnWriteArrayList) (obj)).isEmpty() ^ true)
            for(obj = ((Iterable) (obj)).iterator(); ((Iterator) (obj)).hasNext(); ((OnEnterAnimationCompleteListener)((Iterator) (obj)).next()).onEnterAnimationComplete());
    }

    final void dispatchOnGlobalFocusChange(View view, View view1)
    {
        Object obj = mOnGlobalFocusListeners;
        if(obj != null && ((CopyOnWriteArrayList) (obj)).size() > 0)
            for(obj = ((Iterable) (obj)).iterator(); ((Iterator) (obj)).hasNext(); ((OnGlobalFocusChangeListener)((Iterator) (obj)).next()).onGlobalFocusChanged(view, view1));
    }

    public final void dispatchOnGlobalLayout()
    {
        CopyOnWriteArray copyonwritearray;
        CopyOnWriteArray.Access access;
        copyonwritearray = mOnGlobalLayoutListeners;
        if(copyonwritearray == null || copyonwritearray.size() <= 0)
            break MISSING_BLOCK_LABEL_59;
        access = copyonwritearray.start();
        int i = access.size();
        int j = 0;
_L2:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        ((OnGlobalLayoutListener)access.get(j)).onGlobalLayout();
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        copyonwritearray.end();
        return;
        Exception exception;
        exception;
        copyonwritearray.end();
        throw exception;
    }

    public final boolean dispatchOnPreDraw()
    {
        boolean flag1;
        CopyOnWriteArray copyonwritearray;
        boolean flag2;
        CopyOnWriteArray.Access access;
        boolean flag = false;
        flag1 = false;
        copyonwritearray = mOnPreDrawListeners;
        flag2 = flag;
        if(copyonwritearray == null)
            break MISSING_BLOCK_LABEL_86;
        flag2 = flag;
        if(copyonwritearray.size() <= 0)
            break MISSING_BLOCK_LABEL_86;
        access = copyonwritearray.start();
        int i = access.size();
        int j;
        j = 0;
        flag2 = flag1;
_L2:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        flag1 = ((OnPreDrawListener)access.get(j)).onPreDraw();
        flag2 |= flag1 ^ true;
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        copyonwritearray.end();
        return flag2;
        Exception exception;
        exception;
        copyonwritearray.end();
        throw exception;
    }

    final void dispatchOnScrollChanged()
    {
        CopyOnWriteArray copyonwritearray;
        CopyOnWriteArray.Access access;
        copyonwritearray = mOnScrollChangedListeners;
        if(copyonwritearray == null || copyonwritearray.size() <= 0)
            break MISSING_BLOCK_LABEL_59;
        access = copyonwritearray.start();
        int i = access.size();
        int j = 0;
_L2:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        ((OnScrollChangedListener)access.get(j)).onScrollChanged();
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        copyonwritearray.end();
        return;
        Exception exception;
        exception;
        copyonwritearray.end();
        throw exception;
    }

    final void dispatchOnTouchModeChanged(boolean flag)
    {
        Object obj = mOnTouchModeChangeListeners;
        if(obj != null && ((CopyOnWriteArrayList) (obj)).size() > 0)
            for(obj = ((Iterable) (obj)).iterator(); ((Iterator) (obj)).hasNext(); ((OnTouchModeChangeListener)((Iterator) (obj)).next()).onTouchModeChanged(flag));
    }

    final void dispatchOnWindowAttachedChange(boolean flag)
    {
        Object obj = mOnWindowAttachListeners;
        if(obj != null && ((CopyOnWriteArrayList) (obj)).size() > 0)
            for(obj = ((Iterable) (obj)).iterator(); ((Iterator) (obj)).hasNext();)
            {
                OnWindowAttachListener onwindowattachlistener = (OnWindowAttachListener)((Iterator) (obj)).next();
                if(flag)
                    onwindowattachlistener.onWindowAttached();
                else
                    onwindowattachlistener.onWindowDetached();
            }

    }

    final void dispatchOnWindowFocusChange(boolean flag)
    {
        Object obj = mOnWindowFocusListeners;
        if(obj != null && ((CopyOnWriteArrayList) (obj)).size() > 0)
            for(obj = ((Iterable) (obj)).iterator(); ((Iterator) (obj)).hasNext(); ((OnWindowFocusChangeListener)((Iterator) (obj)).next()).onWindowFocusChanged(flag));
    }

    public final void dispatchOnWindowShown()
    {
        CopyOnWriteArray copyonwritearray;
        CopyOnWriteArray.Access access;
        mWindowShown = true;
        copyonwritearray = mOnWindowShownListeners;
        if(copyonwritearray == null || copyonwritearray.size() <= 0)
            break MISSING_BLOCK_LABEL_64;
        access = copyonwritearray.start();
        int i = access.size();
        int j = 0;
_L2:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        ((OnWindowShownListener)access.get(j)).onWindowShown();
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        copyonwritearray.end();
        return;
        Exception exception;
        exception;
        copyonwritearray.end();
        throw exception;
    }

    final boolean hasComputeInternalInsetsListeners()
    {
        boolean flag = false;
        CopyOnWriteArray copyonwritearray = mOnComputeInternalInsetsListeners;
        boolean flag1 = flag;
        if(copyonwritearray != null)
        {
            flag1 = flag;
            if(copyonwritearray.size() > 0)
                flag1 = true;
        }
        return flag1;
    }

    final boolean hasOnPreDrawListeners()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(mOnPreDrawListeners != null)
        {
            flag1 = flag;
            if(mOnPreDrawListeners.size() > 0)
                flag1 = true;
        }
        return flag1;
    }

    public boolean isAlive()
    {
        return mAlive;
    }

    void merge(ViewTreeObserver viewtreeobserver)
    {
        if(viewtreeobserver.mOnWindowAttachListeners != null)
            if(mOnWindowAttachListeners != null)
                mOnWindowAttachListeners.addAll(viewtreeobserver.mOnWindowAttachListeners);
            else
                mOnWindowAttachListeners = viewtreeobserver.mOnWindowAttachListeners;
        if(viewtreeobserver.mOnWindowFocusListeners != null)
            if(mOnWindowFocusListeners != null)
                mOnWindowFocusListeners.addAll(viewtreeobserver.mOnWindowFocusListeners);
            else
                mOnWindowFocusListeners = viewtreeobserver.mOnWindowFocusListeners;
        if(viewtreeobserver.mOnGlobalFocusListeners != null)
            if(mOnGlobalFocusListeners != null)
                mOnGlobalFocusListeners.addAll(viewtreeobserver.mOnGlobalFocusListeners);
            else
                mOnGlobalFocusListeners = viewtreeobserver.mOnGlobalFocusListeners;
        if(viewtreeobserver.mOnGlobalLayoutListeners != null)
            if(mOnGlobalLayoutListeners != null)
                mOnGlobalLayoutListeners.addAll(viewtreeobserver.mOnGlobalLayoutListeners);
            else
                mOnGlobalLayoutListeners = viewtreeobserver.mOnGlobalLayoutListeners;
        if(viewtreeobserver.mOnPreDrawListeners != null)
            if(mOnPreDrawListeners != null)
                mOnPreDrawListeners.addAll(viewtreeobserver.mOnPreDrawListeners);
            else
                mOnPreDrawListeners = viewtreeobserver.mOnPreDrawListeners;
        if(viewtreeobserver.mOnDrawListeners != null)
            if(mOnDrawListeners != null)
                mOnDrawListeners.addAll(viewtreeobserver.mOnDrawListeners);
            else
                mOnDrawListeners = viewtreeobserver.mOnDrawListeners;
        if(viewtreeobserver.mOnTouchModeChangeListeners != null)
            if(mOnTouchModeChangeListeners != null)
                mOnTouchModeChangeListeners.addAll(viewtreeobserver.mOnTouchModeChangeListeners);
            else
                mOnTouchModeChangeListeners = viewtreeobserver.mOnTouchModeChangeListeners;
        if(viewtreeobserver.mOnComputeInternalInsetsListeners != null)
            if(mOnComputeInternalInsetsListeners != null)
                mOnComputeInternalInsetsListeners.addAll(viewtreeobserver.mOnComputeInternalInsetsListeners);
            else
                mOnComputeInternalInsetsListeners = viewtreeobserver.mOnComputeInternalInsetsListeners;
        if(viewtreeobserver.mOnScrollChangedListeners != null)
            if(mOnScrollChangedListeners != null)
                mOnScrollChangedListeners.addAll(viewtreeobserver.mOnScrollChangedListeners);
            else
                mOnScrollChangedListeners = viewtreeobserver.mOnScrollChangedListeners;
        if(viewtreeobserver.mOnWindowShownListeners != null)
            if(mOnWindowShownListeners != null)
                mOnWindowShownListeners.addAll(viewtreeobserver.mOnWindowShownListeners);
            else
                mOnWindowShownListeners = viewtreeobserver.mOnWindowShownListeners;
        viewtreeobserver.kill();
    }

    public void removeGlobalOnLayoutListener(OnGlobalLayoutListener ongloballayoutlistener)
    {
        removeOnGlobalLayoutListener(ongloballayoutlistener);
    }

    public void removeOnComputeInternalInsetsListener(OnComputeInternalInsetsListener oncomputeinternalinsetslistener)
    {
        checkIsAlive();
        if(mOnComputeInternalInsetsListeners == null)
        {
            return;
        } else
        {
            mOnComputeInternalInsetsListeners.remove(oncomputeinternalinsetslistener);
            return;
        }
    }

    public void removeOnDrawListener(OnDrawListener ondrawlistener)
    {
        checkIsAlive();
        if(mOnDrawListeners == null)
            return;
        if(mInDispatchOnDraw)
        {
            IllegalStateException illegalstateexception = new IllegalStateException("Cannot call removeOnDrawListener inside of onDraw");
            if(sIllegalOnDrawModificationIsFatal)
                throw illegalstateexception;
            Log.e("ViewTreeObserver", illegalstateexception.getMessage(), illegalstateexception);
        }
        mOnDrawListeners.remove(ondrawlistener);
    }

    public void removeOnEnterAnimationCompleteListener(OnEnterAnimationCompleteListener onenteranimationcompletelistener)
    {
        checkIsAlive();
        if(mOnEnterAnimationCompleteListeners == null)
        {
            return;
        } else
        {
            mOnEnterAnimationCompleteListeners.remove(onenteranimationcompletelistener);
            return;
        }
    }

    public void removeOnGlobalFocusChangeListener(OnGlobalFocusChangeListener onglobalfocuschangelistener)
    {
        checkIsAlive();
        if(mOnGlobalFocusListeners == null)
        {
            return;
        } else
        {
            mOnGlobalFocusListeners.remove(onglobalfocuschangelistener);
            return;
        }
    }

    public void removeOnGlobalLayoutListener(OnGlobalLayoutListener ongloballayoutlistener)
    {
        checkIsAlive();
        if(mOnGlobalLayoutListeners == null)
        {
            return;
        } else
        {
            mOnGlobalLayoutListeners.remove(ongloballayoutlistener);
            return;
        }
    }

    public void removeOnPreDrawListener(OnPreDrawListener onpredrawlistener)
    {
        checkIsAlive();
        if(mOnPreDrawListeners == null)
        {
            return;
        } else
        {
            mOnPreDrawListeners.remove(onpredrawlistener);
            return;
        }
    }

    public void removeOnScrollChangedListener(OnScrollChangedListener onscrollchangedlistener)
    {
        checkIsAlive();
        if(mOnScrollChangedListeners == null)
        {
            return;
        } else
        {
            mOnScrollChangedListeners.remove(onscrollchangedlistener);
            return;
        }
    }

    public void removeOnTouchModeChangeListener(OnTouchModeChangeListener ontouchmodechangelistener)
    {
        checkIsAlive();
        if(mOnTouchModeChangeListeners == null)
        {
            return;
        } else
        {
            mOnTouchModeChangeListeners.remove(ontouchmodechangelistener);
            return;
        }
    }

    public void removeOnWindowAttachListener(OnWindowAttachListener onwindowattachlistener)
    {
        checkIsAlive();
        if(mOnWindowAttachListeners == null)
        {
            return;
        } else
        {
            mOnWindowAttachListeners.remove(onwindowattachlistener);
            return;
        }
    }

    public void removeOnWindowFocusChangeListener(OnWindowFocusChangeListener onwindowfocuschangelistener)
    {
        checkIsAlive();
        if(mOnWindowFocusListeners == null)
        {
            return;
        } else
        {
            mOnWindowFocusListeners.remove(onwindowfocuschangelistener);
            return;
        }
    }

    public void removeOnWindowShownListener(OnWindowShownListener onwindowshownlistener)
    {
        checkIsAlive();
        if(mOnWindowShownListeners == null)
        {
            return;
        } else
        {
            mOnWindowShownListeners.remove(onwindowshownlistener);
            return;
        }
    }

    private static boolean sIllegalOnDrawModificationIsFatal;
    private boolean mAlive;
    private boolean mInDispatchOnDraw;
    private CopyOnWriteArray mOnComputeInternalInsetsListeners;
    private ArrayList mOnDrawListeners;
    private CopyOnWriteArrayList mOnEnterAnimationCompleteListeners;
    private CopyOnWriteArrayList mOnGlobalFocusListeners;
    private CopyOnWriteArray mOnGlobalLayoutListeners;
    private CopyOnWriteArray mOnPreDrawListeners;
    private CopyOnWriteArray mOnScrollChangedListeners;
    private CopyOnWriteArrayList mOnTouchModeChangeListeners;
    private CopyOnWriteArrayList mOnWindowAttachListeners;
    private CopyOnWriteArrayList mOnWindowFocusListeners;
    private CopyOnWriteArray mOnWindowShownListeners;
    private boolean mWindowShown;
}
