// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.autofill;

import android.content.*;
import android.graphics.Rect;
import android.metrics.LogMaker;
import android.os.*;
import android.service.autofill.FillEventHistory;
import android.util.*;
import android.view.View;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.util.Preconditions;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.*;

// Referenced classes of package android.view.autofill:
//            AutofillId, AutofillValue, ParcelableMap, Helper, 
//            IAutoFillManager, IAutoFillManagerClient, IAutofillWindowPresenter

public final class AutofillManager
{
    public static abstract class AutofillCallback
    {

        public void onAutofillEvent(View view, int i)
        {
        }

        public void onAutofillEvent(View view, int i, int j)
        {
        }

        public static final int EVENT_INPUT_HIDDEN = 2;
        public static final int EVENT_INPUT_SHOWN = 1;
        public static final int EVENT_INPUT_UNAVAILABLE = 3;

        public AutofillCallback()
        {
        }
    }

    public static interface AutofillClient
    {

        public abstract void autofillCallbackAuthenticate(int i, IntentSender intentsender, Intent intent);

        public abstract boolean autofillCallbackRequestHideFillUi();

        public abstract boolean autofillCallbackRequestShowFillUi(View view, int i, int j, Rect rect, IAutofillWindowPresenter iautofillwindowpresenter);

        public abstract void autofillCallbackResetableStateAvailable();

        public abstract View findViewByAutofillIdTraversal(int i);

        public abstract View[] findViewsByAutofillIdTraversal(int ai[]);

        public abstract ComponentName getComponentNameForAutofill();

        public abstract boolean[] getViewVisibility(int ai[]);

        public abstract boolean isVisibleForAutofill();

        public abstract void runOnUiThread(Runnable runnable);
    }

    private static final class AutofillManagerClient extends IAutoFillManagerClient.Stub
    {

        static void lambda$_2D_android_view_autofill_AutofillManager$AutofillManagerClient_68405(AutofillManager autofillmanager, boolean flag, boolean flag1, boolean flag2)
        {
            AutofillManager._2D_wrap12(autofillmanager, flag, flag1, flag2);
        }

        static void lambda$_2D_android_view_autofill_AutofillManager$AutofillManagerClient_68709(AutofillManager autofillmanager, int i, List list, List list1)
        {
            AutofillManager._2D_wrap4(autofillmanager, i, list, list1);
        }

        static void lambda$_2D_android_view_autofill_AutofillManager$AutofillManagerClient_69035(AutofillManager autofillmanager, int i, int j, IntentSender intentsender, Intent intent)
        {
            AutofillManager._2D_wrap3(autofillmanager, i, j, intentsender, intent);
        }

        static void lambda$_2D_android_view_autofill_AutofillManager$AutofillManagerClient_69426(AutofillManager autofillmanager, int i, AutofillId autofillid, int j, int k, Rect rect, IAutofillWindowPresenter iautofillwindowpresenter)
        {
            AutofillManager._2D_wrap9(autofillmanager, i, autofillid, j, k, rect, iautofillwindowpresenter);
        }

        static void lambda$_2D_android_view_autofill_AutofillManager$AutofillManagerClient_69756(AutofillManager autofillmanager, AutofillId autofillid)
        {
            AutofillManager._2D_wrap8(autofillmanager, autofillid);
        }

        static void lambda$_2D_android_view_autofill_AutofillManager$AutofillManagerClient_70033(AutofillManager autofillmanager, int i, AutofillId autofillid, boolean flag)
        {
            AutofillManager._2D_wrap6(autofillmanager, i, autofillid, flag);
        }

        static void lambda$_2D_android_view_autofill_AutofillManager$AutofillManagerClient_70325(AutofillManager autofillmanager, IntentSender intentsender, Intent intent)
        {
            AutofillManager._2D_get0(autofillmanager).startIntentSender(intentsender, intent, 0, 0, 0);
_L1:
            return;
            autofillmanager;
            Log.e("AutofillManager", (new StringBuilder()).append("startIntentSender() failed for intent:").append(intentsender).toString(), autofillmanager);
              goto _L1
        }

        static void lambda$_2D_android_view_autofill_AutofillManager$AutofillManagerClient_70948(AutofillManager autofillmanager, int i, AutofillId aautofillid[], boolean flag, AutofillId aautofillid1[])
        {
            AutofillManager._2D_wrap13(autofillmanager, i, aautofillid, flag, aautofillid1);
        }

        static void lambda$_2D_android_view_autofill_AutofillManager$AutofillManagerClient_71289(AutofillManager autofillmanager, int i, boolean flag)
        {
            AutofillManager._2D_wrap10(autofillmanager, i, flag);
        }

        static void lambda$_2D_android_view_autofill_AutofillManager$AutofillManagerClient_71540(AutofillManager autofillmanager, int i)
        {
            AutofillManager._2D_wrap11(autofillmanager, i);
        }

        public void authenticate(int i, int j, IntentSender intentsender, Intent intent)
        {
            AutofillManager autofillmanager = (AutofillManager)mAfm.get();
            if(autofillmanager != null)
                AutofillManager._2D_wrap7(autofillmanager, new _.Lambda._cls6ub2tg3C_4hyczXTkY_CEW2ET8I._cls4(i, j, autofillmanager, intentsender, intent));
        }

        public void autofill(int i, List list, List list1)
        {
            AutofillManager autofillmanager = (AutofillManager)mAfm.get();
            if(autofillmanager != null)
                AutofillManager._2D_wrap7(autofillmanager, new _.Lambda._cls6ub2tg3C_4hyczXTkY_CEW2ET8I._cls3(i, autofillmanager, list, list1));
        }

        public void notifyNoFillUi(int i, AutofillId autofillid, boolean flag)
        {
            AutofillManager autofillmanager = (AutofillManager)mAfm.get();
            if(autofillmanager != null)
                AutofillManager._2D_wrap7(autofillmanager, new _.Lambda._cls6ub2tg3C_4hyczXTkY_CEW2ET8I._cls7(flag, i, autofillmanager, autofillid));
        }

        public void requestHideFillUi(int i, AutofillId autofillid)
        {
            AutofillManager autofillmanager = (AutofillManager)mAfm.get();
            if(autofillmanager != null)
                AutofillManager._2D_wrap7(autofillmanager, new _.Lambda._cls6ub2tg3C_4hyczXTkY_CEW2ET8I(autofillmanager, autofillid));
        }

        public void requestShowFillUi(int i, AutofillId autofillid, int j, int k, Rect rect, IAutofillWindowPresenter iautofillwindowpresenter)
        {
            AutofillManager autofillmanager = (AutofillManager)mAfm.get();
            if(autofillmanager != null)
                AutofillManager._2D_wrap7(autofillmanager, new _.Lambda._cls6ub2tg3C_4hyczXTkY_CEW2ET8I._cls5(i, j, k, autofillmanager, autofillid, rect, iautofillwindowpresenter));
        }

        public void setSaveUiState(int i, boolean flag)
        {
            AutofillManager autofillmanager = (AutofillManager)mAfm.get();
            if(autofillmanager != null)
                AutofillManager._2D_wrap7(autofillmanager, new _.Lambda._cls6ub2tg3C_4hyczXTkY_CEW2ET8I._cls6(flag, i, autofillmanager));
        }

        public void setSessionFinished(int i)
        {
            AutofillManager autofillmanager = (AutofillManager)mAfm.get();
            if(autofillmanager != null)
                AutofillManager._2D_wrap7(autofillmanager, new _.Lambda._cls6ub2tg3C_4hyczXTkY_CEW2ET8I._cls2(i, autofillmanager));
        }

        public void setState(boolean flag, boolean flag1, boolean flag2)
        {
            AutofillManager autofillmanager = (AutofillManager)mAfm.get();
            if(autofillmanager != null)
                AutofillManager._2D_wrap7(autofillmanager, new _.Lambda._cls6ub2tg3C_4hyczXTkY_CEW2ET8I._cls9(flag, flag1, flag2, autofillmanager));
        }

        public void setTrackedViews(int i, AutofillId aautofillid[], boolean flag, AutofillId aautofillid1[])
        {
            AutofillManager autofillmanager = (AutofillManager)mAfm.get();
            if(autofillmanager != null)
                AutofillManager._2D_wrap7(autofillmanager, new _.Lambda._cls6ub2tg3C_4hyczXTkY_CEW2ET8I._cls8(flag, i, autofillmanager, aautofillid, aautofillid1));
        }

        public void startIntentSender(IntentSender intentsender, Intent intent)
        {
            AutofillManager autofillmanager = (AutofillManager)mAfm.get();
            if(autofillmanager != null)
                AutofillManager._2D_wrap7(autofillmanager, new _.Lambda._cls6ub2tg3C_4hyczXTkY_CEW2ET8I._cls1(autofillmanager, intentsender, intent));
        }

        private final WeakReference mAfm;

        AutofillManagerClient(AutofillManager autofillmanager)
        {
            mAfm = new WeakReference(autofillmanager);
        }
    }

    private class TrackedViews
    {

        static ArraySet _2D_get0(TrackedViews trackedviews)
        {
            return trackedviews.mInvisibleTrackedIds;
        }

        static ArraySet _2D_get1(TrackedViews trackedviews)
        {
            return trackedviews.mVisibleTrackedIds;
        }

        private ArraySet addToSet(ArraySet arrayset, Object obj)
        {
            ArraySet arrayset1 = arrayset;
            if(arrayset == null)
                arrayset1 = new ArraySet(1);
            arrayset1.add(obj);
            return arrayset1;
        }

        private boolean isInSet(ArraySet arrayset, Object obj)
        {
            boolean flag;
            if(arrayset != null)
                flag = arrayset.contains(obj);
            else
                flag = false;
            return flag;
        }

        private ArraySet removeFromSet(ArraySet arrayset, Object obj)
        {
            if(arrayset == null)
                return null;
            arrayset.remove(obj);
            if(arrayset.isEmpty())
                return null;
            else
                return arrayset;
        }

        void notifyViewVisibilityChanged(AutofillId autofillid, boolean flag)
        {
            AutofillClient autofillclient;
            autofillclient = AutofillManager._2D_wrap0(AutofillManager.this);
            if(Helper.sDebug)
                Log.d("AutofillManager", (new StringBuilder()).append("notifyViewVisibilityChanged(): id=").append(autofillid).append(" isVisible=").append(flag).toString());
            if(autofillclient == null || !autofillclient.isVisibleForAutofill()) goto _L2; else goto _L1
_L1:
            if(!flag) goto _L4; else goto _L3
_L3:
            if(isInSet(mInvisibleTrackedIds, autofillid))
            {
                mInvisibleTrackedIds = removeFromSet(mInvisibleTrackedIds, autofillid);
                mVisibleTrackedIds = addToSet(mVisibleTrackedIds, autofillid);
            }
_L2:
            if(mVisibleTrackedIds == null)
            {
                if(Helper.sVerbose)
                    Log.v("AutofillManager", (new StringBuilder()).append("No more visible ids. Invisibile = ").append(mInvisibleTrackedIds).toString());
                AutofillManager._2D_wrap5(AutofillManager.this);
            }
            return;
_L4:
            if(isInSet(mVisibleTrackedIds, autofillid))
            {
                mVisibleTrackedIds = removeFromSet(mVisibleTrackedIds, autofillid);
                mInvisibleTrackedIds = addToSet(mInvisibleTrackedIds, autofillid);
            }
            if(true) goto _L2; else goto _L5
_L5:
        }

        void onVisibleForAutofillLocked()
        {
            AutofillClient autofillclient = AutofillManager._2D_wrap0(AutofillManager.this);
            ArraySet arrayset = null;
            ArraySet arrayset1 = null;
            ArraySet arrayset2 = null;
            ArraySet arrayset3 = null;
            if(autofillclient != null)
            {
                if(mInvisibleTrackedIds != null)
                {
                    ArrayList arraylist = new ArrayList(mInvisibleTrackedIds);
                    boolean aflag1[] = autofillclient.getViewVisibility(AutofillManager._2D_wrap2(AutofillManager.this, arraylist));
                    int i = arraylist.size();
                    int k = 0;
                    do
                    {
                        arrayset2 = arrayset3;
                        arrayset = arrayset1;
                        if(k >= i)
                            break;
                        AutofillId autofillid1 = (AutofillId)arraylist.get(k);
                        if(aflag1[k])
                        {
                            arrayset = addToSet(arrayset1, autofillid1);
                            arrayset2 = arrayset3;
                            arrayset1 = arrayset;
                            if(Helper.sDebug)
                            {
                                Log.d("AutofillManager", (new StringBuilder()).append("onVisibleForAutofill() ").append(autofillid1).append(" became visible").toString());
                                arrayset1 = arrayset;
                                arrayset2 = arrayset3;
                            }
                        } else
                        {
                            arrayset2 = addToSet(arrayset3, autofillid1);
                        }
                        k++;
                        arrayset3 = arrayset2;
                    } while(true);
                }
                arrayset3 = arrayset2;
                arrayset1 = arrayset;
                if(mVisibleTrackedIds != null)
                {
                    ArrayList arraylist1 = new ArrayList(mVisibleTrackedIds);
                    boolean aflag[] = autofillclient.getViewVisibility(AutofillManager._2D_wrap2(AutofillManager.this, arraylist1));
                    int j = arraylist1.size();
                    int l = 0;
                    do
                    {
                        arrayset3 = arrayset2;
                        arrayset1 = arrayset;
                        if(l >= j)
                            break;
                        AutofillId autofillid = (AutofillId)arraylist1.get(l);
                        if(aflag[l])
                        {
                            arrayset3 = addToSet(arrayset, autofillid);
                        } else
                        {
                            arrayset1 = addToSet(arrayset2, autofillid);
                            arrayset2 = arrayset1;
                            arrayset3 = arrayset;
                            if(Helper.sDebug)
                            {
                                Log.d("AutofillManager", (new StringBuilder()).append("onVisibleForAutofill() ").append(autofillid).append(" became invisible").toString());
                                arrayset2 = arrayset1;
                                arrayset3 = arrayset;
                            }
                        }
                        l++;
                        arrayset = arrayset3;
                    } while(true);
                }
                mInvisibleTrackedIds = arrayset3;
                mVisibleTrackedIds = arrayset1;
            }
            if(mVisibleTrackedIds == null)
                AutofillManager._2D_wrap5(AutofillManager.this);
        }

        private ArraySet mInvisibleTrackedIds;
        private ArraySet mVisibleTrackedIds;
        final AutofillManager this$0;

        TrackedViews(AutofillId aautofillid[])
        {
            this$0 = AutofillManager.this;
            super();
            AutofillClient autofillclient = AutofillManager._2D_wrap0(AutofillManager.this);
            if(aautofillid != null && autofillclient != null)
            {
                boolean aflag[];
                int i;
                int j;
                if(autofillclient.isVisibleForAutofill())
                    aflag = autofillclient.getViewVisibility(AutofillManager._2D_wrap1(AutofillManager.this, aautofillid));
                else
                    aflag = new boolean[aautofillid.length];
                i = aautofillid.length;
                j = 0;
                while(j < i) 
                {
                    AutofillId autofillid = aautofillid[j];
                    if(aflag[j])
                        mVisibleTrackedIds = addToSet(mVisibleTrackedIds, autofillid);
                    else
                        mInvisibleTrackedIds = addToSet(mInvisibleTrackedIds, autofillid);
                    j++;
                }
            }
            if(Helper.sVerbose)
                Log.v("AutofillManager", (new StringBuilder()).append("TrackedViews(trackedIds=").append(aautofillid).append("): ").append(" mVisibleTrackedIds=").append(mVisibleTrackedIds).append(" mInvisibleTrackedIds=").append(mInvisibleTrackedIds).toString());
            if(mVisibleTrackedIds == null)
                AutofillManager._2D_wrap5(AutofillManager.this);
        }
    }


    static Context _2D_get0(AutofillManager autofillmanager)
    {
        return autofillmanager.mContext;
    }

    static AutofillClient _2D_wrap0(AutofillManager autofillmanager)
    {
        return autofillmanager.getClientLocked();
    }

    static int[] _2D_wrap1(AutofillManager autofillmanager, AutofillId aautofillid[])
    {
        return autofillmanager.getViewIds(aautofillid);
    }

    static void _2D_wrap10(AutofillManager autofillmanager, int i, boolean flag)
    {
        autofillmanager.setSaveUiState(i, flag);
    }

    static void _2D_wrap11(AutofillManager autofillmanager, int i)
    {
        autofillmanager.setSessionFinished(i);
    }

    static void _2D_wrap12(AutofillManager autofillmanager, boolean flag, boolean flag1, boolean flag2)
    {
        autofillmanager.setState(flag, flag1, flag2);
    }

    static void _2D_wrap13(AutofillManager autofillmanager, int i, AutofillId aautofillid[], boolean flag, AutofillId aautofillid1[])
    {
        autofillmanager.setTrackedViews(i, aautofillid, flag, aautofillid1);
    }

    static int[] _2D_wrap2(AutofillManager autofillmanager, List list)
    {
        return autofillmanager.getViewIds(list);
    }

    static void _2D_wrap3(AutofillManager autofillmanager, int i, int j, IntentSender intentsender, Intent intent)
    {
        autofillmanager.authenticate(i, j, intentsender, intent);
    }

    static void _2D_wrap4(AutofillManager autofillmanager, int i, List list, List list1)
    {
        autofillmanager.autofill(i, list, list1);
    }

    static void _2D_wrap5(AutofillManager autofillmanager)
    {
        autofillmanager.finishSessionLocked();
    }

    static void _2D_wrap6(AutofillManager autofillmanager, int i, AutofillId autofillid, boolean flag)
    {
        autofillmanager.notifyNoFillUi(i, autofillid, flag);
    }

    static void _2D_wrap7(AutofillManager autofillmanager, Runnable runnable)
    {
        autofillmanager.post(runnable);
    }

    static void _2D_wrap8(AutofillManager autofillmanager, AutofillId autofillid)
    {
        autofillmanager.requestHideFillUi(autofillid);
    }

    static void _2D_wrap9(AutofillManager autofillmanager, int i, AutofillId autofillid, int j, int k, Rect rect, IAutofillWindowPresenter iautofillwindowpresenter)
    {
        autofillmanager.requestShowFillUi(i, autofillid, j, k, rect, iautofillwindowpresenter);
    }

    public AutofillManager(Context context, IAutoFillManager iautofillmanager)
    {
        mSessionId = 0x80000000;
        mState = 0;
        mContext = (Context)Preconditions.checkNotNull(context, "context cannot be null");
        mService = iautofillmanager;
    }

    private void authenticate(int i, int j, IntentSender intentsender, Intent intent)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        AutofillClient autofillclient;
        if(i != mSessionId)
            break MISSING_BLOCK_LABEL_39;
        autofillclient = getClientLocked();
        if(autofillclient == null)
            break MISSING_BLOCK_LABEL_39;
        autofillclient.autofillCallbackAuthenticate(j, intentsender, intent);
        obj;
        JVM INSTR monitorexit ;
        return;
        intentsender;
        throw intentsender;
    }

    private void autofill(int i, List list, List list1)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        int j = mSessionId;
        if(i == j)
            break MISSING_BLOCK_LABEL_25;
        obj;
        JVM INSTR monitorexit ;
        return;
        Object obj1 = getClientLocked();
        if(obj1 != null)
            break MISSING_BLOCK_LABEL_40;
        obj;
        JVM INSTR monitorexit ;
        return;
        int k = list.size();
        Object obj2;
        i = 0;
        obj2 = null;
        View aview[] = ((AutofillClient) (obj1)).findViewsByAutofillIdTraversal(getViewIds(list));
        j = 0;
_L2:
        if(j >= k)
            break MISSING_BLOCK_LABEL_315;
        AutofillId autofillid;
        AutofillValue autofillvalue;
        int l;
        autofillid = (AutofillId)list.get(j);
        autofillvalue = (AutofillValue)list1.get(j);
        l = autofillid.getViewId();
        View view;
        view = aview[j];
        if(view != null)
            break; /* Loop/switch isn't completed */
        obj1 = JVM INSTR new #234 <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        Log.w("AutofillManager", ((StringBuilder) (obj1)).append("autofill(): no View with id ").append(l).toString());
_L3:
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        if(!autofillid.isVirtual())
            break MISSING_BLOCK_LABEL_254;
        obj1 = obj2;
        if(obj2 != null)
            break MISSING_BLOCK_LABEL_187;
        obj1 = JVM INSTR new #260 <Class ArrayMap>;
        ((ArrayMap) (obj1)).ArrayMap(1);
        SparseArray sparsearray = (SparseArray)((ArrayMap) (obj1)).get(view);
        obj2 = sparsearray;
        if(sparsearray != null)
            break MISSING_BLOCK_LABEL_229;
        obj2 = JVM INSTR new #267 <Class SparseArray>;
        ((SparseArray) (obj2)).SparseArray(5);
        ((ArrayMap) (obj1)).put(view, obj2);
        ((SparseArray) (obj2)).put(autofillid.getVirtualChildId(), autofillvalue);
        obj2 = obj1;
          goto _L3
        list;
        throw list;
        if(mLastAutofilledData == null)
        {
            ParcelableMap parcelablemap = JVM INSTR new #281 <Class ParcelableMap>;
            parcelablemap.ParcelableMap(k - j);
            mLastAutofilledData = parcelablemap;
        }
        mLastAutofilledData.put(autofillid, autofillvalue);
        view.autofill(autofillvalue);
        setAutofilledIfValuesIs(view, autofillvalue);
        i++;
          goto _L3
        j = i;
        if(obj2 == null) goto _L5; else goto _L4
_L4:
        l = 0;
_L6:
        j = i;
        if(l >= ((ArrayMap) (obj2)).size())
            break; /* Loop/switch isn't completed */
        list = (View)((ArrayMap) (obj2)).keyAt(l);
        list1 = (SparseArray)((ArrayMap) (obj2)).valueAt(l);
        list.autofill(list1);
        i += list1.size();
        l++;
        if(true) goto _L6; else goto _L5
_L5:
        list = JVM INSTR new #305 <Class LogMaker>;
        list.LogMaker(913);
        list = list.setPackageName(mContext.getPackageName()).addTaggedData(914, Integer.valueOf(k)).addTaggedData(915, Integer.valueOf(j));
        mMetricsLogger.write(list);
        obj;
        JVM INSTR monitorexit ;
    }

    private void cancelSessionLocked()
    {
        if(Helper.sVerbose)
            Log.v("AutofillManager", (new StringBuilder()).append("cancelSessionLocked(): ").append(getStateAsStringLocked()).toString());
        if(!isActiveLocked())
            return;
        try
        {
            mService.cancelSession(mSessionId, mContext.getUserId());
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        resetSessionLocked();
    }

    private void ensureServiceClientAddedIfNeededLocked()
    {
        boolean flag = true;
        if(getClientLocked() == null)
            return;
        if(mServiceClient != null)
            break MISSING_BLOCK_LABEL_87;
        mServiceClient = new AutofillManagerClient(this);
        int i;
        boolean flag1;
        try
        {
            i = mService.addClient(mServiceClient, mContext.getUserId());
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        if((i & 1) != 0)
            flag1 = true;
        else
            flag1 = false;
        mEnabled = flag1;
        if((i & 2) != 0)
            flag1 = true;
        else
            flag1 = false;
        Helper.sDebug = flag1;
        if((i & 4) != 0)
            flag1 = flag;
        else
            flag1 = false;
        Helper.sVerbose = flag1;
    }

    private View findView(AutofillId autofillid)
    {
        AutofillClient autofillclient = getClientLocked();
        if(autofillclient == null)
            return null;
        else
            return autofillclient.findViewByAutofillIdTraversal(autofillid.getViewId());
    }

    private void finishSessionLocked()
    {
        if(Helper.sVerbose)
            Log.v("AutofillManager", (new StringBuilder()).append("finishSessionLocked(): ").append(getStateAsStringLocked()).toString());
        if(!isActiveLocked())
            return;
        try
        {
            mService.finishSession(mSessionId, mContext.getUserId());
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        resetSessionLocked();
    }

    private static AutofillId getAutofillId(View view)
    {
        return new AutofillId(view.getAutofillViewId());
    }

    private static AutofillId getAutofillId(View view, int i)
    {
        return new AutofillId(view.getAutofillViewId(), i);
    }

    private AutofillClient getClientLocked()
    {
        return mContext.getAutofillClient();
    }

    private ComponentName getComponentNameFromContext(AutofillClient autofillclient)
    {
        Object obj = null;
        if(autofillclient == null)
            autofillclient = obj;
        else
            autofillclient = autofillclient.getComponentNameForAutofill();
        return autofillclient;
    }

    public static int getDatasetIdFromAuthenticationId(int i)
    {
        return 0xffff & i;
    }

    public static int getRequestIdFromAuthenticationId(int i)
    {
        return i >> 16;
    }

    private String getStateAsStringLocked()
    {
        switch(mState)
        {
        default:
            return (new StringBuilder()).append("INVALID:").append(mState).toString();

        case 0: // '\0'
            return "STATE_UNKNOWN";

        case 1: // '\001'
            return "STATE_ACTIVE";

        case 2: // '\002'
            return "STATE_FINISHED";

        case 3: // '\003'
            return "STATE_SHOWING_SAVE_UI";
        }
    }

    private int[] getViewIds(List list)
    {
        int i = list.size();
        int ai[] = new int[i];
        for(int j = 0; j < i; j++)
            ai[j] = ((AutofillId)list.get(j)).getViewId();

        return ai;
    }

    private int[] getViewIds(AutofillId aautofillid[])
    {
        int i = aautofillid.length;
        int ai[] = new int[i];
        for(int j = 0; j < i; j++)
            ai[j] = aautofillid[j].getViewId();

        return ai;
    }

    private boolean isActiveLocked()
    {
        boolean flag = true;
        if(mState != 1)
            flag = false;
        return flag;
    }

    private boolean isFinishedLocked()
    {
        boolean flag;
        if(mState == 2)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static int makeAuthenticationId(int i, int j)
    {
        return i << 16 | 0xffff & j;
    }

    private void notifyNoFillUi(int i, AutofillId autofillid, boolean flag)
    {
        View view;
        Object obj;
        if(Helper.sVerbose)
            Log.v("AutofillManager", (new StringBuilder()).append("notifyNoFillUi(): sessionId=").append(i).append(", autofillId=").append(autofillid).append(", finished=").append(flag).toString());
        view = findView(autofillid);
        if(view == null)
            return;
        obj = null;
        Object obj1 = mLock;
        obj1;
        JVM INSTR monitorenter ;
        AutofillCallback autofillcallback = obj;
        if(mSessionId != i)
            break MISSING_BLOCK_LABEL_106;
        autofillcallback = obj;
        if(getClientLocked() != null)
            autofillcallback = mCallback;
        obj1;
        JVM INSTR monitorexit ;
        if(autofillcallback != null)
            if(autofillid.isVirtual())
                autofillcallback.onAutofillEvent(view, autofillid.getVirtualChildId(), 3);
            else
                autofillcallback.onAutofillEvent(view, 3);
        if(flag)
            setSessionFinished(2);
        return;
        autofillid;
        throw autofillid;
    }

    private void notifyViewEntered(View view, int i)
    {
        Object obj;
        if(!hasAutofillFeature())
            return;
        obj = null;
        Object obj1 = mLock;
        obj1;
        JVM INSTR monitorenter ;
        if(!isFinishedLocked() || (i & 1) != 0)
            break MISSING_BLOCK_LABEL_93;
        if(Helper.sVerbose)
        {
            obj = JVM INSTR new #234 <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            Log.v("AutofillManager", ((StringBuilder) (obj)).append("notifyViewEntered(flags=").append(i).append(", view=").append(view).append("): ignored on state ").append(getStateAsStringLocked()).toString());
        }
        obj1;
        JVM INSTR monitorexit ;
        return;
        ensureServiceClientAddedIfNeededLocked();
        if(mEnabled) goto _L2; else goto _L1
_L1:
        if(mCallback != null)
            obj = mCallback;
_L3:
        obj1;
        JVM INSTR monitorexit ;
        if(obj != null)
            mCallback.onAutofillEvent(view, 3);
        return;
_L2:
        AutofillId autofillid;
        AutofillValue autofillvalue;
        autofillid = getAutofillId(view);
        autofillvalue = view.getAutofillValue();
        if(isActiveLocked())
            break MISSING_BLOCK_LABEL_171;
        startSessionLocked(autofillid, null, autofillvalue, i);
          goto _L3
        view;
        throw view;
        updateSessionLocked(autofillid, null, autofillvalue, 2, i);
          goto _L3
    }

    private void notifyViewEntered(View view, int i, Rect rect, int j)
    {
        Object obj;
        if(!hasAutofillFeature())
            return;
        obj = null;
        Object obj1 = mLock;
        obj1;
        JVM INSTR monitorenter ;
        if(!isFinishedLocked() || (j & 1) != 0)
            break MISSING_BLOCK_LABEL_106;
        if(Helper.sVerbose)
        {
            rect = JVM INSTR new #234 <Class StringBuilder>;
            rect.StringBuilder();
            Log.v("AutofillManager", rect.append("notifyViewEntered(flags=").append(j).append(", view=").append(view).append(", virtualId=").append(i).append("): ignored on state ").append(getStateAsStringLocked()).toString());
        }
        obj1;
        JVM INSTR monitorexit ;
        return;
        ensureServiceClientAddedIfNeededLocked();
        if(mEnabled) goto _L2; else goto _L1
_L1:
        rect = obj;
        if(mCallback != null)
            rect = mCallback;
_L4:
        obj1;
        JVM INSTR monitorexit ;
        if(rect != null)
            rect.onAutofillEvent(view, i, 3);
        return;
_L2:
        AutofillId autofillid;
        autofillid = getAutofillId(view, i);
        if(isActiveLocked())
            break MISSING_BLOCK_LABEL_183;
        startSessionLocked(autofillid, rect, null, j);
        rect = obj;
        continue; /* Loop/switch isn't completed */
        view;
        throw view;
        updateSessionLocked(autofillid, rect, null, 2, j);
        rect = obj;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void notifyViewVisibilityChangedInternal(View view, int i, boolean flag, boolean flag1)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(!mEnabled || !isActiveLocked()) goto _L2; else goto _L1
_L1:
        if(!flag1) goto _L4; else goto _L3
_L3:
        AutofillId autofillid = getAutofillId(view, i);
_L6:
        if(flag)
            break MISSING_BLOCK_LABEL_109;
        if(mFillableIds != null && mFillableIds.contains(autofillid))
        {
            if(Helper.sDebug)
            {
                StringBuilder stringbuilder = JVM INSTR new #234 <Class StringBuilder>;
                stringbuilder.StringBuilder();
                Log.d("AutofillManager", stringbuilder.append("Hidding UI when view ").append(autofillid).append(" became invisible").toString());
            }
            requestHideFillUi(autofillid, view);
        }
        if(mTrackedViews != null)
            mTrackedViews.notifyViewVisibilityChanged(autofillid, flag);
_L2:
        obj;
        JVM INSTR monitorexit ;
        return;
_L4:
        autofillid = view.getAutofillId();
        if(true) goto _L6; else goto _L5
_L5:
        view;
        throw view;
    }

    private void post(Runnable runnable)
    {
        AutofillClient autofillclient = getClientLocked();
        if(autofillclient == null)
        {
            if(Helper.sVerbose)
                Log.v("AutofillManager", "ignoring post() because client is null");
            return;
        } else
        {
            autofillclient.runOnUiThread(runnable);
            return;
        }
    }

    private void requestHideFillUi(AutofillId autofillid)
    {
        View view = findView(autofillid);
        if(Helper.sVerbose)
            Log.v("AutofillManager", (new StringBuilder()).append("requestHideFillUi(").append(autofillid).append("): anchor = ").append(view).toString());
        if(view == null)
        {
            return;
        } else
        {
            requestHideFillUi(autofillid, view);
            return;
        }
    }

    private void requestHideFillUi(AutofillId autofillid, View view)
    {
        Object obj = null;
        Object obj1 = mLock;
        obj1;
        JVM INSTR monitorenter ;
        AutofillClient autofillclient = getClientLocked();
        AutofillCallback autofillcallback;
        autofillcallback = obj;
        if(autofillclient == null)
            break MISSING_BLOCK_LABEL_54;
        autofillcallback = obj;
        if(!autofillclient.autofillCallbackRequestHideFillUi())
            break MISSING_BLOCK_LABEL_54;
        autofillcallback = obj;
        if(mCallback != null)
            autofillcallback = mCallback;
        obj1;
        JVM INSTR monitorexit ;
        if(autofillcallback != null)
            if(autofillid.isVirtual())
                autofillcallback.onAutofillEvent(view, autofillid.getVirtualChildId(), 2);
            else
                autofillcallback.onAutofillEvent(view, 2);
        return;
        autofillid;
        throw autofillid;
    }

    private void requestShowFillUi(int i, AutofillId autofillid, int j, int k, Rect rect, IAutofillWindowPresenter iautofillwindowpresenter)
    {
        View view;
        Object obj;
        view = findView(autofillid);
        if(view == null)
            return;
        obj = null;
        Object obj1 = mLock;
        obj1;
        JVM INSTR monitorenter ;
        AutofillCallback autofillcallback = obj;
        AutofillClient autofillclient;
        if(mSessionId != i)
            break MISSING_BLOCK_LABEL_92;
        autofillclient = getClientLocked();
        autofillcallback = obj;
        if(autofillclient == null)
            break MISSING_BLOCK_LABEL_92;
        autofillcallback = obj;
        if(!autofillclient.autofillCallbackRequestShowFillUi(view, j, k, rect, iautofillwindowpresenter))
            break MISSING_BLOCK_LABEL_92;
        autofillcallback = obj;
        if(mCallback != null)
            autofillcallback = mCallback;
        obj1;
        JVM INSTR monitorexit ;
        if(autofillcallback != null)
            if(autofillid.isVirtual())
                autofillcallback.onAutofillEvent(view, autofillid.getVirtualChildId(), 1);
            else
                autofillcallback.onAutofillEvent(view, 1);
        return;
        autofillid;
        throw autofillid;
    }

    private void resetSessionLocked()
    {
        mSessionId = 0x80000000;
        mState = 0;
        mTrackedViews = null;
        mFillableIds = null;
    }

    private void setAutofilledIfValuesIs(View view, AutofillValue autofillvalue)
    {
        if(!Objects.equals(view.getAutofillValue(), autofillvalue))
            break MISSING_BLOCK_LABEL_62;
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(mLastAutofilledData == null)
        {
            ParcelableMap parcelablemap = JVM INSTR new #281 <Class ParcelableMap>;
            parcelablemap.ParcelableMap(1);
            mLastAutofilledData = parcelablemap;
        }
        mLastAutofilledData.put(getAutofillId(view), autofillvalue);
        obj;
        JVM INSTR monitorexit ;
        view.setAutofilled(true);
        return;
        view;
        throw view;
    }

    private void setSaveUiState(int i, boolean flag)
    {
        if(Helper.sDebug)
            Log.d("AutofillManager", (new StringBuilder()).append("setSaveUiState(").append(i).append("): ").append(flag).toString());
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(mSessionId != 0x80000000)
        {
            StringBuilder stringbuilder = JVM INSTR new #234 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.w("AutofillManager", stringbuilder.append("setSaveUiState(").append(i).append(", ").append(flag).append(") called on existing session ").append(mSessionId).append("; cancelling it").toString());
            cancelSessionLocked();
        }
        if(!flag)
            break MISSING_BLOCK_LABEL_139;
        mSessionId = i;
        mState = 3;
_L1:
        obj;
        JVM INSTR monitorexit ;
        return;
        mSessionId = 0x80000000;
        mState = 0;
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    private void setSessionFinished(int i)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(Helper.sVerbose)
        {
            StringBuilder stringbuilder = JVM INSTR new #234 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.v("AutofillManager", stringbuilder.append("setSessionFinished(): from ").append(mState).append(" to ").append(i).toString());
        }
        resetSessionLocked();
        mState = i;
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private void setState(boolean flag, boolean flag1, boolean flag2)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        mEnabled = flag;
        if(mEnabled && !flag1)
            break MISSING_BLOCK_LABEL_29;
        resetSessionLocked();
        if(!flag2)
            break MISSING_BLOCK_LABEL_38;
        mServiceClient = null;
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private void setTrackedViews(int i, AutofillId aautofillid[], boolean flag, AutofillId aautofillid1[])
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(!mEnabled || mSessionId != i)
            break MISSING_BLOCK_LABEL_169;
        if(!flag) goto _L2; else goto _L1
_L1:
        TrackedViews trackedviews = JVM INSTR new #15  <Class AutofillManager$TrackedViews>;
        trackedviews.this. TrackedViews(aautofillid);
        mTrackedViews = trackedviews;
_L6:
        if(aautofillid1 == null)
            break MISSING_BLOCK_LABEL_169;
        if(mFillableIds == null)
        {
            aautofillid = JVM INSTR new #482 <Class ArraySet>;
            aautofillid.ArraySet(aautofillid1.length);
            mFillableIds = aautofillid;
        }
        i = 0;
        int j = aautofillid1.length;
_L4:
        if(i >= j)
            break; /* Loop/switch isn't completed */
        aautofillid = aautofillid1[i];
        mFillableIds.add(aautofillid);
        i++;
        if(true) goto _L4; else goto _L3
_L3:
        break; /* Loop/switch isn't completed */
_L2:
        mTrackedViews = null;
        if(true) goto _L6; else goto _L5
        aautofillid;
        throw aautofillid;
_L5:
        if(Helper.sVerbose)
        {
            aautofillid = JVM INSTR new #234 <Class StringBuilder>;
            aautofillid.StringBuilder();
            Log.v("AutofillManager", aautofillid.append("setTrackedViews(): fillableIds=").append(aautofillid1).append(", mFillableIds").append(mFillableIds).toString());
        }
        obj;
        JVM INSTR monitorexit ;
    }

    private void startSessionLocked(AutofillId autofillid, Rect rect, AutofillValue autofillvalue, int i)
    {
        if(Helper.sVerbose)
            Log.v("AutofillManager", (new StringBuilder()).append("startSessionLocked(): id=").append(autofillid).append(", bounds=").append(rect).append(", value=").append(autofillvalue).append(", flags=").append(i).append(", state=").append(getStateAsStringLocked()).toString());
        if(mState != 0 && (i & 1) == 0)
        {
            if(Helper.sVerbose)
                Log.v("AutofillManager", (new StringBuilder()).append("not automatically starting session for ").append(autofillid).append(" on state ").append(getStateAsStringLocked()).toString());
            return;
        }
        AutofillClient autofillclient;
        ComponentName componentname;
        IAutoFillManager iautofillmanager;
        IBinder ibinder;
        IBinder ibinder1;
        int j;
        boolean flag;
        try
        {
            autofillclient = getClientLocked();
            componentname = getComponentNameFromContext(autofillclient);
        }
        // Misplaced declaration of an exception variable
        catch(AutofillId autofillid)
        {
            throw autofillid.rethrowFromSystemServer();
        }
        if(componentname != null)
            break MISSING_BLOCK_LABEL_187;
        autofillid = JVM INSTR new #234 <Class StringBuilder>;
        autofillid.StringBuilder();
        Log.w("AutofillManager", autofillid.append("startSessionLocked(): context is not activity: ").append(mContext).toString());
        return;
        iautofillmanager = mService;
        ibinder = mContext.getActivityToken();
        ibinder1 = mServiceClient.asBinder();
        j = mContext.getUserId();
        if(mCallback != null)
            flag = true;
        else
            flag = false;
        mSessionId = iautofillmanager.startSession(ibinder, ibinder1, autofillid, rect, autofillvalue, j, flag, i, componentname);
        if(mSessionId != 0x80000000)
            mState = 1;
        if(autofillclient == null)
            break MISSING_BLOCK_LABEL_284;
        autofillclient.autofillCallbackResetableStateAvailable();
    }

    private void updateSessionLocked(AutofillId autofillid, Rect rect, AutofillValue autofillvalue, int i, int j)
    {
        if(Helper.sVerbose && i != 3)
            Log.v("AutofillManager", (new StringBuilder()).append("updateSessionLocked(): id=").append(autofillid).append(", bounds=").append(rect).append(", value=").append(autofillvalue).append(", action=").append(i).append(", flags=").append(j).toString());
        int k;
        AutofillClient autofillclient;
        ComponentName componentname;
        if((j & 1) != 0)
            k = 1;
        else
            k = 0;
        if(!k)
            break MISSING_BLOCK_LABEL_333;
        IAutoFillManager iautofillmanager;
        IBinder ibinder;
        IBinder ibinder1;
        boolean flag;
        try
        {
            autofillclient = getClientLocked();
            componentname = getComponentNameFromContext(autofillclient);
        }
        // Misplaced declaration of an exception variable
        catch(AutofillId autofillid)
        {
            throw autofillid.rethrowFromSystemServer();
        }
        if(componentname != null)
            break MISSING_BLOCK_LABEL_152;
        autofillid = JVM INSTR new #234 <Class StringBuilder>;
        autofillid.StringBuilder();
        Log.w("AutofillManager", autofillid.append("startSessionLocked(): context is not activity: ").append(mContext).toString());
        return;
        iautofillmanager = mService;
        ibinder = mContext.getActivityToken();
        ibinder1 = mServiceClient.asBinder();
        k = mContext.getUserId();
        if(mCallback != null)
            flag = true;
        else
            flag = false;
        i = iautofillmanager.updateOrRestartSession(ibinder, ibinder1, autofillid, rect, autofillvalue, k, flag, j, componentname, mSessionId, i);
        if(i == mSessionId)
            break MISSING_BLOCK_LABEL_320;
        if(Helper.sDebug)
        {
            autofillid = JVM INSTR new #234 <Class StringBuilder>;
            autofillid.StringBuilder();
            Log.d("AutofillManager", autofillid.append("Session restarted: ").append(mSessionId).append("=>").append(i).toString());
        }
        mSessionId = i;
        if(mSessionId == 0x80000000)
            i = 0;
        else
            i = 1;
        mState = i;
        if(autofillclient == null)
            break MISSING_BLOCK_LABEL_320;
        autofillclient.autofillCallbackResetableStateAvailable();
_L1:
        return;
        mService.updateSession(mSessionId, autofillid, rect, autofillvalue, i, j, mContext.getUserId());
          goto _L1
    }

    public void cancel()
    {
        if(!hasAutofillFeature())
            return;
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag;
        if(mEnabled)
            break MISSING_BLOCK_LABEL_36;
        flag = isActiveLocked();
        if(!(flag ^ true))
            break MISSING_BLOCK_LABEL_36;
        obj;
        JVM INSTR monitorexit ;
        return;
        cancelSessionLocked();
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void commit()
    {
        if(!hasAutofillFeature())
            return;
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag;
        if(mEnabled)
            break MISSING_BLOCK_LABEL_36;
        flag = isActiveLocked();
        if(!(flag ^ true))
            break MISSING_BLOCK_LABEL_36;
        obj;
        JVM INSTR monitorexit ;
        return;
        finishSessionLocked();
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void disableAutofillServices()
    {
        if(!hasAutofillFeature())
            return;
        try
        {
            mService.disableOwnedAutofillServices(mContext.getUserId());
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void disableOwnedAutofillServices()
    {
        disableAutofillServices();
    }

    public void dump(String s, PrintWriter printwriter)
    {
        boolean flag = true;
        printwriter.print(s);
        printwriter.println("AutofillManager:");
        String s1 = (new StringBuilder()).append(s).append("  ").toString();
        printwriter.print(s1);
        printwriter.print("sessionId: ");
        printwriter.println(mSessionId);
        printwriter.print(s1);
        printwriter.print("state: ");
        printwriter.println(getStateAsStringLocked());
        printwriter.print(s1);
        printwriter.print("enabled: ");
        printwriter.println(mEnabled);
        printwriter.print(s1);
        printwriter.print("hasService: ");
        boolean flag1;
        if(mService != null)
            flag1 = true;
        else
            flag1 = false;
        printwriter.println(flag1);
        printwriter.print(s1);
        printwriter.print("hasCallback: ");
        if(mCallback != null)
            flag1 = flag;
        else
            flag1 = false;
        printwriter.println(flag1);
        printwriter.print(s1);
        printwriter.print("last autofilled data: ");
        printwriter.println(mLastAutofilledData);
        printwriter.print(s1);
        printwriter.print("tracked views: ");
        if(mTrackedViews == null)
        {
            printwriter.println("null");
        } else
        {
            s = (new StringBuilder()).append(s1).append("  ").toString();
            printwriter.println();
            printwriter.print(s);
            printwriter.print("visible:");
            printwriter.println(TrackedViews._2D_get1(mTrackedViews));
            printwriter.print(s);
            printwriter.print("invisible:");
            printwriter.println(TrackedViews._2D_get0(mTrackedViews));
        }
        printwriter.print(s1);
        printwriter.print("fillable ids: ");
        printwriter.println(mFillableIds);
    }

    public FillEventHistory getFillEventHistory()
    {
        FillEventHistory filleventhistory;
        try
        {
            filleventhistory = mService.getFillEventHistory();
        }
        catch(RemoteException remoteexception)
        {
            remoteexception.rethrowFromSystemServer();
            return null;
        }
        return filleventhistory;
    }

    public boolean hasAutofillFeature()
    {
        boolean flag;
        if(mService != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean hasEnabledAutofillServices()
    {
        if(mService == null)
            return false;
        boolean flag;
        try
        {
            flag = mService.isServiceEnabled(mContext.getUserId(), mContext.getPackageName());
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isAutofillSupported()
    {
        if(mService == null)
            return false;
        boolean flag;
        try
        {
            flag = mService.isServiceSupported(mContext.getUserId());
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isEnabled()
    {
        if(!hasAutofillFeature())
            return false;
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag;
        ensureServiceClientAddedIfNeededLocked();
        flag = mEnabled;
        obj;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public void notifyValueChanged(View view)
    {
        Object obj;
        boolean flag;
        AutofillValue autofillvalue;
        if(!hasAutofillFeature())
            return;
        obj = null;
        flag = false;
        autofillvalue = null;
        Object obj1 = mLock;
        obj1;
        JVM INSTR monitorenter ;
        if(mLastAutofilledData != null) goto _L2; else goto _L1
_L1:
        view.setAutofilled(false);
_L3:
        if(mEnabled && !(isActiveLocked() ^ true))
            break MISSING_BLOCK_LABEL_189;
        if(Helper.sVerbose && mEnabled)
        {
            obj = JVM INSTR new #234 <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            Log.v("AutofillManager", ((StringBuilder) (obj)).append("notifyValueChanged(").append(view).append("): ignoring on state ").append(getStateAsStringLocked()).toString());
        }
        obj1;
        JVM INSTR monitorexit ;
        return;
_L2:
        obj = getAutofillId(view);
        if(!mLastAutofilledData.containsKey(obj))
            break MISSING_BLOCK_LABEL_181;
        autofillvalue = view.getAutofillValue();
        flag = true;
        if(!Objects.equals(mLastAutofilledData.get(obj), autofillvalue))
            break MISSING_BLOCK_LABEL_164;
        view.setAutofilled(true);
          goto _L3
        view;
        throw view;
        view.setAutofilled(false);
        mLastAutofilledData.remove(obj);
          goto _L3
        view.setAutofilled(false);
          goto _L3
        Object obj2;
        obj2 = obj;
        if(obj != null)
            break MISSING_BLOCK_LABEL_202;
        obj2 = getAutofillId(view);
        if(flag)
            break MISSING_BLOCK_LABEL_212;
        autofillvalue = view.getAutofillValue();
        updateSessionLocked(((AutofillId) (obj2)), null, autofillvalue, 4, 0);
        obj1;
        JVM INSTR monitorexit ;
    }

    public void notifyValueChanged(View view, int i, AutofillValue autofillvalue)
    {
        if(!hasAutofillFeature())
            return;
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag;
        if(!mEnabled)
            break MISSING_BLOCK_LABEL_37;
        flag = isActiveLocked();
        if(!(flag ^ true))
            break MISSING_BLOCK_LABEL_41;
        obj;
        JVM INSTR monitorexit ;
        return;
        updateSessionLocked(getAutofillId(view, i), null, autofillvalue, 4, 0);
        obj;
        JVM INSTR monitorexit ;
        return;
        view;
        throw view;
    }

    public void notifyViewEntered(View view)
    {
        notifyViewEntered(view, 0);
    }

    public void notifyViewEntered(View view, int i, Rect rect)
    {
        notifyViewEntered(view, i, rect, 0);
    }

    public void notifyViewExited(View view)
    {
        if(!hasAutofillFeature())
            return;
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        ensureServiceClientAddedIfNeededLocked();
        if(mEnabled && isActiveLocked())
            updateSessionLocked(getAutofillId(view), null, null, 3, 0);
        obj;
        JVM INSTR monitorexit ;
        return;
        view;
        throw view;
    }

    public void notifyViewExited(View view, int i)
    {
        if(!hasAutofillFeature())
            return;
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        ensureServiceClientAddedIfNeededLocked();
        if(mEnabled && isActiveLocked())
            updateSessionLocked(getAutofillId(view, i), null, null, 3, 0);
        obj;
        JVM INSTR monitorexit ;
        return;
        view;
        throw view;
    }

    public void notifyViewVisibilityChanged(View view, int i, boolean flag)
    {
        notifyViewVisibilityChangedInternal(view, i, flag, true);
    }

    public void notifyViewVisibilityChanged(View view, boolean flag)
    {
        notifyViewVisibilityChangedInternal(view, 0, flag, false);
    }

    public void onAuthenticationResult(int i, Intent intent)
    {
        if(!hasAutofillFeature())
            return;
        if(Helper.sDebug)
            Log.d("AutofillManager", (new StringBuilder()).append("onAuthenticationResult(): d=").append(intent).toString());
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag = isActiveLocked();
        if(flag && intent != null)
            break MISSING_BLOCK_LABEL_65;
        obj;
        JVM INSTR monitorexit ;
        return;
        Bundle bundle;
        intent = intent.getParcelableExtra("android.view.autofill.extra.AUTHENTICATION_RESULT");
        bundle = JVM INSTR new #719 <Class Bundle>;
        bundle.Bundle();
        bundle.putParcelable("android.view.autofill.extra.AUTHENTICATION_RESULT", intent);
        mService.setAuthenticationResult(bundle, mSessionId, i, mContext.getUserId());
_L1:
        obj;
        JVM INSTR monitorexit ;
        return;
        intent;
        Log.e("AutofillManager", "Error delivering authentication result", intent);
          goto _L1
        intent;
        throw intent;
    }

    public void onCreate(Bundle bundle)
    {
        if(!hasAutofillFeature())
            return;
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        mLastAutofilledData = (ParcelableMap)bundle.getParcelable("android:lastAutoFilledData");
        if(!isActiveLocked())
            break MISSING_BLOCK_LABEL_47;
        Log.w("AutofillManager", "New session was started before onCreate()");
        obj;
        JVM INSTR monitorexit ;
        return;
        mSessionId = bundle.getInt("android:sessionId", 0x80000000);
        mState = bundle.getInt("android:state", 0);
        if(mSessionId == 0x80000000) goto _L2; else goto _L1
_L1:
        ensureServiceClientAddedIfNeededLocked();
        bundle = getClientLocked();
        if(bundle == null) goto _L2; else goto _L3
_L3:
        if(mService.restoreSession(mSessionId, mContext.getActivityToken(), mServiceClient.asBinder())) goto _L5; else goto _L4
_L4:
        bundle = JVM INSTR new #234 <Class StringBuilder>;
        bundle.StringBuilder();
        Log.w("AutofillManager", bundle.append("Session ").append(mSessionId).append(" could not be restored").toString());
        mSessionId = 0x80000000;
        mState = 0;
_L2:
        obj;
        JVM INSTR monitorexit ;
        return;
_L5:
        if(Helper.sDebug)
        {
            StringBuilder stringbuilder = JVM INSTR new #234 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.d("AutofillManager", stringbuilder.append("session ").append(mSessionId).append(" was restored").toString());
        }
        bundle.autofillCallbackResetableStateAvailable();
          goto _L2
        bundle;
        Log.e("AutofillManager", "Could not figure out if there was an autofill session", bundle);
          goto _L2
        bundle;
        throw bundle;
    }

    public void onPendingSaveUi(int i, IBinder ibinder)
    {
        if(Helper.sVerbose)
            Log.v("AutofillManager", (new StringBuilder()).append("onPendingSaveUi(").append(i).append("): ").append(ibinder).toString());
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        mService.onPendingSaveUi(i, ibinder);
_L1:
        obj;
        JVM INSTR monitorexit ;
        return;
        ibinder;
        ibinder.rethrowFromSystemServer();
          goto _L1
        ibinder;
        throw ibinder;
    }

    public void onSaveInstanceState(Bundle bundle)
    {
        if(!hasAutofillFeature())
            return;
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(mSessionId != 0x80000000)
            bundle.putInt("android:sessionId", mSessionId);
        if(mState != 0)
            bundle.putInt("android:state", mState);
        if(mLastAutofilledData != null)
            bundle.putParcelable("android:lastAutoFilledData", mLastAutofilledData);
        obj;
        JVM INSTR monitorexit ;
        return;
        bundle;
        throw bundle;
    }

    public void onVisibleForAutofill()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(mEnabled && isActiveLocked() && mTrackedViews != null)
            mTrackedViews.onVisibleForAutofillLocked();
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void registerCallback(AutofillCallback autofillcallback)
    {
        if(!hasAutofillFeature())
            return;
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(autofillcallback == null)
            return;
        boolean flag;
        if(mCallback != null)
            flag = true;
        else
            flag = false;
        mCallback = autofillcallback;
        if(flag)
            break MISSING_BLOCK_LABEL_61;
        mService.setHasCallback(mSessionId, mContext.getUserId(), true);
        obj;
        JVM INSTR monitorexit ;
        return;
        autofillcallback;
        throw autofillcallback.rethrowFromSystemServer();
        autofillcallback;
        obj;
        JVM INSTR monitorexit ;
        throw autofillcallback;
    }

    public void requestAutofill(View view)
    {
        notifyViewEntered(view, 1);
    }

    public void requestAutofill(View view, int i, Rect rect)
    {
        notifyViewEntered(view, i, rect, 1);
    }

    public void unregisterCallback(AutofillCallback autofillcallback)
    {
        if(!hasAutofillFeature())
            return;
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(autofillcallback == null) goto _L2; else goto _L1
_L1:
        AutofillCallback autofillcallback1 = mCallback;
        if(autofillcallback1 != null) goto _L3; else goto _L2
_L2:
        obj;
        JVM INSTR monitorexit ;
        return;
_L3:
        if(autofillcallback != mCallback) goto _L2; else goto _L4
_L4:
        mCallback = null;
        mService.setHasCallback(mSessionId, mContext.getUserId(), false);
        obj;
        JVM INSTR monitorexit ;
        return;
        autofillcallback;
        throw autofillcallback.rethrowFromSystemServer();
        autofillcallback;
        obj;
        JVM INSTR monitorexit ;
        throw autofillcallback;
    }

    public static final int ACTION_START_SESSION = 1;
    public static final int ACTION_VALUE_CHANGED = 4;
    public static final int ACTION_VIEW_ENTERED = 2;
    public static final int ACTION_VIEW_EXITED = 3;
    private static final int AUTHENTICATION_ID_DATASET_ID_MASK = 65535;
    private static final int AUTHENTICATION_ID_DATASET_ID_SHIFT = 16;
    public static final int AUTHENTICATION_ID_DATASET_ID_UNDEFINED = 65535;
    public static final String EXTRA_ASSIST_STRUCTURE = "android.view.autofill.extra.ASSIST_STRUCTURE";
    public static final String EXTRA_AUTHENTICATION_RESULT = "android.view.autofill.extra.AUTHENTICATION_RESULT";
    public static final String EXTRA_CLIENT_STATE = "android.view.autofill.extra.CLIENT_STATE";
    public static final String EXTRA_RESTORE_SESSION_TOKEN = "android.view.autofill.extra.RESTORE_SESSION_TOKEN";
    public static final int FLAG_ADD_CLIENT_DEBUG = 2;
    public static final int FLAG_ADD_CLIENT_ENABLED = 1;
    public static final int FLAG_ADD_CLIENT_VERBOSE = 4;
    private static final String LAST_AUTOFILLED_DATA_TAG = "android:lastAutoFilledData";
    public static final int NO_SESSION = 0x80000000;
    public static final int PENDING_UI_OPERATION_CANCEL = 1;
    public static final int PENDING_UI_OPERATION_RESTORE = 2;
    private static final String SESSION_ID_TAG = "android:sessionId";
    public static final int STATE_ACTIVE = 1;
    public static final int STATE_FINISHED = 2;
    public static final int STATE_SHOWING_SAVE_UI = 3;
    private static final String STATE_TAG = "android:state";
    public static final int STATE_UNKNOWN = 0;
    private static final String TAG = "AutofillManager";
    private AutofillCallback mCallback;
    private final Context mContext;
    private boolean mEnabled;
    private ArraySet mFillableIds;
    private ParcelableMap mLastAutofilledData;
    private final Object mLock = new Object();
    private final MetricsLogger mMetricsLogger = new MetricsLogger();
    private final IAutoFillManager mService;
    private IAutoFillManagerClient mServiceClient;
    private int mSessionId;
    private int mState;
    private TrackedViews mTrackedViews;
}
