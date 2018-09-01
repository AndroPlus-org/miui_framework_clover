// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import android.os.*;
import android.text.style.AccessibilityClickableSpan;
import android.text.style.ClickableSpan;
import android.util.LongSparseArray;
import android.util.Slog;
import android.view.accessibility.AccessibilityInteractionClient;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import android.view.accessibility.AccessibilityRequestPreparer;
import android.view.accessibility.IAccessibilityInteractionConnectionCallback;
import com.android.internal.os.SomeArgs;
import java.util.*;
import java.util.function.Predicate;

// Referenced classes of package android.view:
//            ViewRootImpl, MagnificationSpec, Display, View, 
//            ViewGroup, ViewParent

final class AccessibilityInteractionController
{
    private class AccessibilityNodePrefetcher
    {

        private void enforceNodeTreeConsistent(List list)
        {
            LongSparseArray longsparsearray;
            HashSet hashset;
            longsparsearray = new LongSparseArray();
            int i = list.size();
            for(int k = 0; k < i; k++)
            {
                AccessibilityNodeInfo accessibilitynodeinfo = (AccessibilityNodeInfo)list.get(k);
                longsparsearray.put(accessibilitynodeinfo.getSourceNodeId(), accessibilitynodeinfo);
            }

            Object obj = (AccessibilityNodeInfo)longsparsearray.valueAt(0);
            for(list = ((List) (obj)); list != null; list = (AccessibilityNodeInfo)longsparsearray.get(list.getParentNodeId()))
                obj = list;

            List list2 = null;
            Object obj1 = null;
            hashset = new HashSet();
            LinkedList linkedlist = new LinkedList();
            linkedlist.add(obj);
            do
            {
label0:
                {
                    if(linkedlist.isEmpty())
                        break label0;
                    list = (AccessibilityNodeInfo)linkedlist.poll();
                    if(!hashset.add(list))
                        throw new IllegalStateException((new StringBuilder()).append("Duplicate node: ").append(list).append(" in window:").append(AccessibilityInteractionController._2D_get0(AccessibilityInteractionController.this).mAttachInfo.mAccessibilityWindowId).toString());
                    List list1 = list2;
                    if(list.isAccessibilityFocused())
                    {
                        if(list2 != null)
                            throw new IllegalStateException((new StringBuilder()).append("Duplicate accessibility focus:").append(list).append(" in window:").append(AccessibilityInteractionController._2D_get0(AccessibilityInteractionController.this).mAttachInfo.mAccessibilityWindowId).toString());
                        list1 = list;
                    }
                    List list3 = ((List) (obj1));
                    if(list.isFocused())
                    {
                        if(obj1 != null)
                            throw new IllegalStateException((new StringBuilder()).append("Duplicate input focus: ").append(list).append(" in window:").append(AccessibilityInteractionController._2D_get0(AccessibilityInteractionController.this).mAttachInfo.mAccessibilityWindowId).toString());
                        list3 = list;
                    }
                    int j = list.getChildCount();
                    int l = 0;
                    do
                    {
                        list2 = list1;
                        obj1 = list3;
                        if(l >= j)
                            break;
                        obj1 = (AccessibilityNodeInfo)longsparsearray.get(list.getChildId(l));
                        if(obj1 != null)
                            linkedlist.add(obj1);
                        l++;
                    } while(true);
                }
            } while(true);
            for(int i1 = longsparsearray.size() - 1; i1 >= 0; i1--)
            {
                list = (AccessibilityNodeInfo)longsparsearray.valueAt(i1);
                if(!hashset.contains(list))
                    throw new IllegalStateException((new StringBuilder()).append("Disconnected node: ").append(list).toString());
            }

            return;
        }

        private void prefetchDescendantsOfRealNode(View view, List list)
        {
            HashMap hashmap;
            ArrayList arraylist;
            if(!(view instanceof ViewGroup))
                return;
            hashmap = new HashMap();
            arraylist = mTempViewList;
            arraylist.clear();
            int i;
            view.addChildrenForAccessibility(arraylist);
            i = arraylist.size();
            int j = 0;
_L2:
            if(j >= i)
                break MISSING_BLOCK_LABEL_178;
            int k = list.size();
            if(k >= 50)
            {
                arraylist.clear();
                return;
            }
            Object obj1;
            view = (View)arraylist.get(j);
            if(!AccessibilityInteractionController._2D_wrap0(AccessibilityInteractionController.this, view))
                break MISSING_BLOCK_LABEL_131;
            obj1 = view.getAccessibilityNodeProvider();
            if(obj1 != null)
                break; /* Loop/switch isn't completed */
            obj1 = view.createAccessibilityNodeInfo();
            if(obj1 == null)
                break MISSING_BLOCK_LABEL_131;
            list.add(obj1);
            hashmap.put(view, null);
_L4:
            j++;
            if(true) goto _L2; else goto _L1
_L1:
            obj1 = ((AccessibilityNodeProvider) (obj1)).createAccessibilityNodeInfo(-1);
            if(obj1 == null) goto _L4; else goto _L3
_L3:
            list.add(obj1);
            hashmap.put(view, obj1);
              goto _L4
            view;
            arraylist.clear();
            throw view;
            arraylist.clear();
            if(list.size() < 50)
                for(view = hashmap.entrySet().iterator(); view.hasNext();)
                {
                    Object obj = (java.util.Map.Entry)view.next();
                    View view1 = (View)((java.util.Map.Entry) (obj)).getKey();
                    obj = (AccessibilityNodeInfo)((java.util.Map.Entry) (obj)).getValue();
                    if(obj == null)
                        prefetchDescendantsOfRealNode(view1, list);
                    else
                        prefetchDescendantsOfVirtualNode(((AccessibilityNodeInfo) (obj)), view1.getAccessibilityNodeProvider(), list);
                }

            return;
        }

        private void prefetchDescendantsOfVirtualNode(AccessibilityNodeInfo accessibilitynodeinfo, AccessibilityNodeProvider accessibilitynodeprovider, List list)
        {
            int i = list.size();
            int j = accessibilitynodeinfo.getChildCount();
            for(int l = 0; l < j; l++)
            {
                if(list.size() >= 50)
                    return;
                AccessibilityNodeInfo accessibilitynodeinfo1 = accessibilitynodeprovider.createAccessibilityNodeInfo(AccessibilityNodeInfo.getVirtualDescendantId(accessibilitynodeinfo.getChildId(l)));
                if(accessibilitynodeinfo1 != null)
                    list.add(accessibilitynodeinfo1);
            }

            if(list.size() < 50)
            {
                int k = list.size();
                for(int i1 = 0; i1 < k - i; i1++)
                    prefetchDescendantsOfVirtualNode((AccessibilityNodeInfo)list.get(i + i1), accessibilitynodeprovider, list);

            }
        }

        private void prefetchPredecessorsOfRealNode(View view, List list)
        {
            for(view = view.getParentForAccessibility(); (view instanceof View) && list.size() < 50; view = view.getParentForAccessibility())
            {
                AccessibilityNodeInfo accessibilitynodeinfo = ((View)view).createAccessibilityNodeInfo();
                if(accessibilitynodeinfo != null)
                    list.add(accessibilitynodeinfo);
            }

        }

        private void prefetchPredecessorsOfVirtualNode(AccessibilityNodeInfo accessibilitynodeinfo, View view, AccessibilityNodeProvider accessibilitynodeprovider, List list)
        {
            int i = list.size();
            long l = accessibilitynodeinfo.getParentNodeId();
            for(int j = AccessibilityNodeInfo.getAccessibilityViewId(l); j != 0x7fffffff;)
            {
                if(list.size() >= 50)
                    return;
                int k = AccessibilityNodeInfo.getVirtualDescendantId(l);
                if(k != -1 || j == view.getAccessibilityViewId())
                {
                    accessibilitynodeinfo = accessibilitynodeprovider.createAccessibilityNodeInfo(k);
                    if(accessibilitynodeinfo == null)
                    {
                        for(j = list.size() - 1; j >= i; j--)
                            list.remove(j);

                        return;
                    }
                    list.add(accessibilitynodeinfo);
                    l = accessibilitynodeinfo.getParentNodeId();
                    j = AccessibilityNodeInfo.getAccessibilityViewId(l);
                } else
                {
                    prefetchPredecessorsOfRealNode(view, list);
                    return;
                }
            }

        }

        private void prefetchSiblingsOfRealNode(View view, List list)
        {
            Object obj = view.getParentForAccessibility();
            if(!(obj instanceof ViewGroup)) goto _L2; else goto _L1
_L1:
            ArrayList arraylist;
            obj = (ViewGroup)obj;
            arraylist = mTempViewList;
            arraylist.clear();
            int i;
            ((ViewGroup) (obj)).addChildrenForAccessibility(arraylist);
            i = arraylist.size();
            int j = 0;
_L12:
            if(j >= i) goto _L4; else goto _L3
_L3:
            int k = list.size();
            if(k >= 50)
            {
                arraylist.clear();
                return;
            }
            View view1 = (View)arraylist.get(j);
            if(view1.getAccessibilityViewId() == view.getAccessibilityViewId() || !AccessibilityInteractionController._2D_wrap0(AccessibilityInteractionController.this, view1)) goto _L6; else goto _L5
_L5:
            obj = view1.getAccessibilityNodeProvider();
            if(obj != null) goto _L8; else goto _L7
_L7:
            obj = view1.createAccessibilityNodeInfo();
_L10:
            if(obj == null) goto _L6; else goto _L9
_L9:
            list.add(obj);
_L6:
            j++;
            continue; /* Loop/switch isn't completed */
_L8:
            obj = ((AccessibilityNodeProvider) (obj)).createAccessibilityNodeInfo(-1);
            if(true) goto _L10; else goto _L4
_L4:
            arraylist.clear();
_L2:
            return;
            view;
            arraylist.clear();
            throw view;
            if(true) goto _L12; else goto _L11
_L11:
        }

        private void prefetchSiblingsOfVirtualNode(AccessibilityNodeInfo accessibilitynodeinfo, View view, AccessibilityNodeProvider accessibilitynodeprovider, List list)
        {
            long l = accessibilitynodeinfo.getParentNodeId();
            int i = AccessibilityNodeInfo.getAccessibilityViewId(l);
            int k = AccessibilityNodeInfo.getVirtualDescendantId(l);
            if(k != -1 || i == view.getAccessibilityViewId())
            {
                view = accessibilitynodeprovider.createAccessibilityNodeInfo(k);
                if(view != null)
                {
                    int i1 = view.getChildCount();
                    for(int j = 0; j < i1; j++)
                    {
                        if(list.size() >= 50)
                            return;
                        long l1 = view.getChildId(j);
                        if(l1 != accessibilitynodeinfo.getSourceNodeId())
                        {
                            AccessibilityNodeInfo accessibilitynodeinfo1 = accessibilitynodeprovider.createAccessibilityNodeInfo(AccessibilityNodeInfo.getVirtualDescendantId(l1));
                            if(accessibilitynodeinfo1 != null)
                                list.add(accessibilitynodeinfo1);
                        }
                    }

                }
            } else
            {
                prefetchSiblingsOfRealNode(view, list);
            }
        }

        public void prefetchAccessibilityNodeInfos(View view, int i, int j, List list, Bundle bundle)
        {
            Object obj;
            String s;
            obj = view.getAccessibilityNodeProvider();
            if(bundle == null)
                s = null;
            else
                s = bundle.getString("android.view.accessibility.AccessibilityNodeInfo.extra_data_requested");
            if(obj != null) goto _L2; else goto _L1
_L1:
            obj = view.createAccessibilityNodeInfo();
            if(obj != null)
            {
                if(s != null)
                    view.addExtraDataToAccessibilityNodeInfo(((AccessibilityNodeInfo) (obj)), s, bundle);
                list.add(obj);
                if((j & 1) != 0)
                    prefetchPredecessorsOfRealNode(view, list);
                if((j & 2) != 0)
                    prefetchSiblingsOfRealNode(view, list);
                if((j & 4) != 0)
                    prefetchDescendantsOfRealNode(view, list);
            }
_L4:
            return;
_L2:
            AccessibilityNodeInfo accessibilitynodeinfo = ((AccessibilityNodeProvider) (obj)).createAccessibilityNodeInfo(i);
            if(accessibilitynodeinfo != null)
            {
                if(s != null)
                    ((AccessibilityNodeProvider) (obj)).addExtraDataToAccessibilityNodeInfo(i, accessibilitynodeinfo, s, bundle);
                list.add(accessibilitynodeinfo);
                if((j & 1) != 0)
                    prefetchPredecessorsOfVirtualNode(accessibilitynodeinfo, view, ((AccessibilityNodeProvider) (obj)), list);
                if((j & 2) != 0)
                    prefetchSiblingsOfVirtualNode(accessibilitynodeinfo, view, ((AccessibilityNodeProvider) (obj)), list);
                if((j & 4) != 0)
                    prefetchDescendantsOfVirtualNode(accessibilitynodeinfo, ((AccessibilityNodeProvider) (obj)), list);
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        private static final int MAX_ACCESSIBILITY_NODE_INFO_BATCH_SIZE = 50;
        private final ArrayList mTempViewList;
        final AccessibilityInteractionController this$0;

        private AccessibilityNodePrefetcher()
        {
            this$0 = AccessibilityInteractionController.this;
            super();
            mTempViewList = new ArrayList();
        }

        AccessibilityNodePrefetcher(AccessibilityNodePrefetcher accessibilitynodeprefetcher)
        {
            this();
        }
    }

    private final class AddNodeInfosForViewId
        implements Predicate
    {

        public void init(int i, List list)
        {
            mViewId = i;
            mInfos = list;
        }

        public void reset()
        {
            mViewId = -1;
            mInfos = null;
        }

        public boolean test(View view)
        {
            if(view.getId() == mViewId && AccessibilityInteractionController._2D_wrap0(AccessibilityInteractionController.this, view))
                mInfos.add(view.createAccessibilityNodeInfo());
            return false;
        }

        public volatile boolean test(Object obj)
        {
            return test((View)obj);
        }

        private List mInfos;
        private int mViewId;
        final AccessibilityInteractionController this$0;

        private AddNodeInfosForViewId()
        {
            this$0 = AccessibilityInteractionController.this;
            super();
            mViewId = -1;
        }

        AddNodeInfosForViewId(AddNodeInfosForViewId addnodeinfosforviewid)
        {
            this();
        }
    }

    private static final class MessageHolder
    {

        final int mInterrogatingPid;
        final long mInterrogatingTid;
        final Message mMessage;

        MessageHolder(Message message, int i, long l)
        {
            mMessage = message;
            mInterrogatingPid = i;
            mInterrogatingTid = l;
        }
    }

    private class PrivateHandler extends Handler
    {

        public String getMessageName(Message message)
        {
            int i = message.what;
            switch(i)
            {
            default:
                throw new IllegalArgumentException((new StringBuilder()).append("Unknown message type: ").append(i).toString());

            case 1: // '\001'
                return "MSG_PERFORM_ACCESSIBILITY_ACTION";

            case 2: // '\002'
                return "MSG_FIND_ACCESSIBILITY_NODE_INFO_BY_ACCESSIBILITY_ID";

            case 3: // '\003'
                return "MSG_FIND_ACCESSIBILITY_NODE_INFOS_BY_VIEW_ID";

            case 4: // '\004'
                return "MSG_FIND_ACCESSIBILITY_NODE_INFO_BY_TEXT";

            case 5: // '\005'
                return "MSG_FIND_FOCUS";

            case 6: // '\006'
                return "MSG_FOCUS_SEARCH";

            case 7: // '\007'
                return "MSG_PREPARE_FOR_EXTRA_DATA_REQUEST";

            case 8: // '\b'
                return "MSG_APP_PREPARATION_FINISHED";

            case 9: // '\t'
                return "MSG_APP_PREPARATION_TIMEOUT";
            }
        }

        public void handleMessage(Message message)
        {
            int i = message.what;
            i;
            JVM INSTR tableswitch 1 9: default 56
        //                       1 92
        //                       2 83
        //                       3 103
        //                       4 114
        //                       5 125
        //                       6 136
        //                       7 147
        //                       8 158
        //                       9 169;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10
_L1:
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown message type: ").append(i).toString());
_L3:
            AccessibilityInteractionController._2D_wrap1(AccessibilityInteractionController.this, message);
_L12:
            return;
_L2:
            AccessibilityInteractionController._2D_wrap6(AccessibilityInteractionController.this, message);
            continue; /* Loop/switch isn't completed */
_L4:
            AccessibilityInteractionController._2D_wrap3(AccessibilityInteractionController.this, message);
            continue; /* Loop/switch isn't completed */
_L5:
            AccessibilityInteractionController._2D_wrap2(AccessibilityInteractionController.this, message);
            continue; /* Loop/switch isn't completed */
_L6:
            AccessibilityInteractionController._2D_wrap4(AccessibilityInteractionController.this, message);
            continue; /* Loop/switch isn't completed */
_L7:
            AccessibilityInteractionController._2D_wrap5(AccessibilityInteractionController.this, message);
            continue; /* Loop/switch isn't completed */
_L8:
            AccessibilityInteractionController._2D_wrap7(AccessibilityInteractionController.this, message);
            continue; /* Loop/switch isn't completed */
_L9:
            AccessibilityInteractionController._2D_wrap8(AccessibilityInteractionController.this, message);
            continue; /* Loop/switch isn't completed */
_L10:
            AccessibilityInteractionController._2D_wrap9(AccessibilityInteractionController.this);
            if(true) goto _L12; else goto _L11
_L11:
        }

        private static final int MSG_APP_PREPARATION_FINISHED = 8;
        private static final int MSG_APP_PREPARATION_TIMEOUT = 9;
        private static final int MSG_FIND_ACCESSIBILITY_NODE_INFOS_BY_VIEW_ID = 3;
        private static final int MSG_FIND_ACCESSIBILITY_NODE_INFO_BY_ACCESSIBILITY_ID = 2;
        private static final int MSG_FIND_ACCESSIBILITY_NODE_INFO_BY_TEXT = 4;
        private static final int MSG_FIND_FOCUS = 5;
        private static final int MSG_FOCUS_SEARCH = 6;
        private static final int MSG_PERFORM_ACCESSIBILITY_ACTION = 1;
        private static final int MSG_PREPARE_FOR_EXTRA_DATA_REQUEST = 7;
        final AccessibilityInteractionController this$0;

        public PrivateHandler(Looper looper)
        {
            this$0 = AccessibilityInteractionController.this;
            super(looper);
        }
    }


    static ViewRootImpl _2D_get0(AccessibilityInteractionController accessibilityinteractioncontroller)
    {
        return accessibilityinteractioncontroller.mViewRootImpl;
    }

    static boolean _2D_wrap0(AccessibilityInteractionController accessibilityinteractioncontroller, View view)
    {
        return accessibilityinteractioncontroller.isShown(view);
    }

    static void _2D_wrap1(AccessibilityInteractionController accessibilityinteractioncontroller, Message message)
    {
        accessibilityinteractioncontroller.findAccessibilityNodeInfoByAccessibilityIdUiThread(message);
    }

    static void _2D_wrap2(AccessibilityInteractionController accessibilityinteractioncontroller, Message message)
    {
        accessibilityinteractioncontroller.findAccessibilityNodeInfosByTextUiThread(message);
    }

    static void _2D_wrap3(AccessibilityInteractionController accessibilityinteractioncontroller, Message message)
    {
        accessibilityinteractioncontroller.findAccessibilityNodeInfosByViewIdUiThread(message);
    }

    static void _2D_wrap4(AccessibilityInteractionController accessibilityinteractioncontroller, Message message)
    {
        accessibilityinteractioncontroller.findFocusUiThread(message);
    }

    static void _2D_wrap5(AccessibilityInteractionController accessibilityinteractioncontroller, Message message)
    {
        accessibilityinteractioncontroller.focusSearchUiThread(message);
    }

    static void _2D_wrap6(AccessibilityInteractionController accessibilityinteractioncontroller, Message message)
    {
        accessibilityinteractioncontroller.performAccessibilityActionUiThread(message);
    }

    static void _2D_wrap7(AccessibilityInteractionController accessibilityinteractioncontroller, Message message)
    {
        accessibilityinteractioncontroller.prepareForExtraDataRequestUiThread(message);
    }

    static void _2D_wrap8(AccessibilityInteractionController accessibilityinteractioncontroller, Message message)
    {
        accessibilityinteractioncontroller.requestPreparerDoneUiThread(message);
    }

    static void _2D_wrap9(AccessibilityInteractionController accessibilityinteractioncontroller)
    {
        accessibilityinteractioncontroller.requestPreparerTimeoutUiThread();
    }

    public AccessibilityInteractionController(ViewRootImpl viewrootimpl)
    {
        Looper looper = viewrootimpl.mHandler.getLooper();
        mMyLooperThreadId = looper.getThread().getId();
        mHandler = new PrivateHandler(looper);
        mViewRootImpl = viewrootimpl;
        mA11yManager = (AccessibilityManager)mViewRootImpl.mContext.getSystemService(android/view/accessibility/AccessibilityManager);
    }

    private void adjustIsVisibleToUserIfNeeded(AccessibilityNodeInfo accessibilitynodeinfo, Region region)
    {
        if(region == null || accessibilitynodeinfo == null)
            return;
        Rect rect = mTempRect;
        accessibilitynodeinfo.getBoundsInScreen(rect);
        if(region.quickReject(rect))
            accessibilitynodeinfo.setVisibleToUser(false);
    }

    private void adjustIsVisibleToUserIfNeeded(List list, Region region)
    {
        if(region == null || list == null)
            return;
        int i = list.size();
        for(int j = 0; j < i; j++)
            adjustIsVisibleToUserIfNeeded((AccessibilityNodeInfo)list.get(j), region);

    }

    private void applyAppScaleAndMagnificationSpecIfNeeded(AccessibilityNodeInfo accessibilitynodeinfo, MagnificationSpec magnificationspec)
    {
        if(accessibilitynodeinfo == null)
            return;
        float f = mViewRootImpl.mAttachInfo.mApplicationScale;
        if(!shouldApplyAppScaleAndMagnificationSpec(f, magnificationspec))
            return;
        Rect rect = mTempRect;
        Rect rect2 = mTempRect1;
        accessibilitynodeinfo.getBoundsInParent(rect);
        accessibilitynodeinfo.getBoundsInScreen(rect2);
        if(f != 1.0F)
        {
            rect.scale(f);
            rect2.scale(f);
        }
        if(magnificationspec != null)
        {
            rect.scale(magnificationspec.scale);
            rect2.scale(magnificationspec.scale);
            rect2.offset((int)magnificationspec.offsetX, (int)magnificationspec.offsetY);
        }
        accessibilitynodeinfo.setBoundsInParent(rect);
        accessibilitynodeinfo.setBoundsInScreen(rect2);
        if(accessibilitynodeinfo.hasExtras())
        {
            android.os.Parcelable aparcelable[] = accessibilitynodeinfo.getExtras().getParcelableArray("android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_KEY");
            if(aparcelable != null)
            {
                for(int i = 0; i < aparcelable.length; i++)
                {
                    RectF rectf = (RectF)aparcelable[i];
                    rectf.scale(f);
                    if(magnificationspec != null)
                    {
                        rectf.scale(magnificationspec.scale);
                        rectf.offset(magnificationspec.offsetX, magnificationspec.offsetY);
                    }
                }

            }
        }
        if(magnificationspec != null)
        {
            View.AttachInfo attachinfo = mViewRootImpl.mAttachInfo;
            if(attachinfo.mDisplay == null)
                return;
            float f1 = attachinfo.mApplicationScale * magnificationspec.scale;
            Rect rect1 = mTempRect1;
            rect1.left = (int)((float)attachinfo.mWindowLeft * f1 + magnificationspec.offsetX);
            rect1.top = (int)((float)attachinfo.mWindowTop * f1 + magnificationspec.offsetY);
            rect1.right = (int)((float)rect1.left + (float)mViewRootImpl.mWidth * f1);
            rect1.bottom = (int)((float)rect1.top + (float)mViewRootImpl.mHeight * f1);
            attachinfo.mDisplay.getRealSize(mTempPoint);
            int j = mTempPoint.x;
            int k = mTempPoint.y;
            magnificationspec = mTempRect2;
            magnificationspec.set(0, 0, j, k);
            if(!rect1.intersect(magnificationspec))
                magnificationspec.setEmpty();
            if(!rect1.intersects(rect2.left, rect2.top, rect2.right, rect2.bottom))
                accessibilitynodeinfo.setVisibleToUser(false);
        }
    }

    private void applyAppScaleAndMagnificationSpecIfNeeded(List list, MagnificationSpec magnificationspec)
    {
        if(list == null)
            return;
        if(shouldApplyAppScaleAndMagnificationSpec(mViewRootImpl.mAttachInfo.mApplicationScale, magnificationspec))
        {
            int i = list.size();
            for(int j = 0; j < i; j++)
                applyAppScaleAndMagnificationSpecIfNeeded((AccessibilityNodeInfo)list.get(j), magnificationspec);

        }
    }

    private void findAccessibilityNodeInfoByAccessibilityIdUiThread(Message message)
    {
        int i;
        int j;
        int k;
        int l;
        IAccessibilityInteractionConnectionCallback iaccessibilityinteractionconnectioncallback;
        MagnificationSpec magnificationspec;
        Region region;
        Bundle bundle;
        ArrayList arraylist;
        i = message.arg1;
        message = (SomeArgs)message.obj;
        j = ((SomeArgs) (message)).argi1;
        k = ((SomeArgs) (message)).argi2;
        l = ((SomeArgs) (message)).argi3;
        iaccessibilityinteractionconnectioncallback = (IAccessibilityInteractionConnectionCallback)((SomeArgs) (message)).arg1;
        magnificationspec = (MagnificationSpec)((SomeArgs) (message)).arg2;
        region = (Region)((SomeArgs) (message)).arg3;
        bundle = (Bundle)((SomeArgs) (message)).arg4;
        message.recycle();
        arraylist = mTempAccessibilityNodeInfoList;
        arraylist.clear();
        if(mViewRootImpl.mView == null)
            break MISSING_BLOCK_LABEL_105;
        message = mViewRootImpl.mAttachInfo;
        if(message != null)
            break MISSING_BLOCK_LABEL_120;
        updateInfosForViewportAndReturnFindNodeResult(arraylist, iaccessibilityinteractionconnectioncallback, l, magnificationspec, region);
        return;
        mViewRootImpl.mAttachInfo.mAccessibilityFetchFlags = i;
        if(j != 0x7ffffffe) goto _L2; else goto _L1
_L1:
        message = mViewRootImpl.mView;
_L4:
        if(message == null)
            break MISSING_BLOCK_LABEL_173;
        if(isShown(message))
            mPrefetcher.prefetchAccessibilityNodeInfos(message, k, i, arraylist, bundle);
        updateInfosForViewportAndReturnFindNodeResult(arraylist, iaccessibilityinteractionconnectioncallback, l, magnificationspec, region);
        return;
_L2:
        message = findViewByAccessibilityId(j);
        if(true) goto _L4; else goto _L3
_L3:
        message;
        updateInfosForViewportAndReturnFindNodeResult(arraylist, iaccessibilityinteractionconnectioncallback, l, magnificationspec, region);
        throw message;
    }

    private void findAccessibilityNodeInfosByTextUiThread(Message message)
    {
        int i;
        String s;
        IAccessibilityInteractionConnectionCallback iaccessibilityinteractionconnectioncallback;
        MagnificationSpec magnificationspec;
        int j;
        int k;
        int l;
        Region region;
        Object obj;
        AccessibilityNodeProvider accessibilitynodeprovider;
        ArrayList arraylist;
        i = message.arg1;
        message = (SomeArgs)message.obj;
        s = (String)((SomeArgs) (message)).arg1;
        iaccessibilityinteractionconnectioncallback = (IAccessibilityInteractionConnectionCallback)((SomeArgs) (message)).arg2;
        magnificationspec = (MagnificationSpec)((SomeArgs) (message)).arg3;
        j = ((SomeArgs) (message)).argi1;
        k = ((SomeArgs) (message)).argi2;
        l = ((SomeArgs) (message)).argi3;
        region = (Region)((SomeArgs) (message)).arg4;
        message.recycle();
        obj = null;
        accessibilitynodeprovider = null;
        arraylist = obj;
        if(mViewRootImpl.mView == null)
            break MISSING_BLOCK_LABEL_106;
        arraylist = obj;
        message = mViewRootImpl.mAttachInfo;
        if(message != null)
            break MISSING_BLOCK_LABEL_120;
        updateInfosForViewportAndReturnFindNodeResult(null, iaccessibilityinteractionconnectioncallback, l, magnificationspec, region);
        return;
        arraylist = obj;
        mViewRootImpl.mAttachInfo.mAccessibilityFetchFlags = i;
        if(j == 0x7ffffffe) goto _L2; else goto _L1
_L1:
        arraylist = obj;
        Object obj1 = findViewByAccessibilityId(j);
_L18:
        message = accessibilitynodeprovider;
        if(obj1 == null) goto _L4; else goto _L3
_L3:
        message = accessibilitynodeprovider;
        arraylist = obj;
        if(!isShown(((View) (obj1)))) goto _L4; else goto _L5
_L5:
        arraylist = obj;
        message = ((View) (obj1)).getAccessibilityNodeProvider();
        if(message == null) goto _L7; else goto _L6
_L6:
        arraylist = obj;
        message = message.findAccessibilityNodeInfosByText(s, k);
_L4:
        updateInfosForViewportAndReturnFindNodeResult(message, iaccessibilityinteractionconnectioncallback, l, magnificationspec, region);
        return;
_L2:
        arraylist = obj;
        obj1 = mViewRootImpl.mView;
        continue; /* Loop/switch isn't completed */
_L7:
        message = accessibilitynodeprovider;
        if(k != -1) goto _L4; else goto _L8
_L8:
        arraylist = obj;
        ArrayList arraylist1 = mTempArrayList;
        arraylist = obj;
        arraylist1.clear();
        arraylist = obj;
        ((View) (obj1)).findViewsWithText(arraylist1, s, 7);
        message = accessibilitynodeprovider;
        arraylist = obj;
        if(arraylist1.isEmpty()) goto _L4; else goto _L9
_L9:
        arraylist = obj;
        obj1 = mTempAccessibilityNodeInfoList;
        arraylist = ((ArrayList) (obj1));
        ((List) (obj1)).clear();
        arraylist = ((ArrayList) (obj1));
        k = arraylist1.size();
        i = 0;
_L16:
        message = ((Message) (obj1));
        if(i >= k) goto _L4; else goto _L10
_L10:
        arraylist = ((ArrayList) (obj1));
        message = (View)arraylist1.get(i);
        arraylist = ((ArrayList) (obj1));
        if(!isShown(message)) goto _L12; else goto _L11
_L11:
        arraylist = ((ArrayList) (obj1));
        accessibilitynodeprovider = message.getAccessibilityNodeProvider();
        if(accessibilitynodeprovider == null) goto _L14; else goto _L13
_L13:
        arraylist = ((ArrayList) (obj1));
        message = accessibilitynodeprovider.findAccessibilityNodeInfosByText(s, -1);
        if(message == null) goto _L12; else goto _L15
_L15:
        arraylist = ((ArrayList) (obj1));
        ((List) (obj1)).addAll(message);
_L12:
        i++;
          goto _L16
_L14:
        arraylist = ((ArrayList) (obj1));
        ((List) (obj1)).add(message.createAccessibilityNodeInfo());
          goto _L12
        message;
        updateInfosForViewportAndReturnFindNodeResult(arraylist, iaccessibilityinteractionconnectioncallback, l, magnificationspec, region);
        throw message;
        if(true) goto _L18; else goto _L17
_L17:
    }

    private void findAccessibilityNodeInfosByViewIdUiThread(Message message)
    {
        int i;
        int j;
        int k;
        IAccessibilityInteractionConnectionCallback iaccessibilityinteractionconnectioncallback;
        MagnificationSpec magnificationspec;
        String s;
        Region region;
        ArrayList arraylist;
        i = message.arg1;
        j = message.arg2;
        message = (SomeArgs)message.obj;
        k = ((SomeArgs) (message)).argi1;
        iaccessibilityinteractionconnectioncallback = (IAccessibilityInteractionConnectionCallback)((SomeArgs) (message)).arg1;
        magnificationspec = (MagnificationSpec)((SomeArgs) (message)).arg2;
        s = (String)((SomeArgs) (message)).arg3;
        region = (Region)((SomeArgs) (message)).arg4;
        message.recycle();
        arraylist = mTempAccessibilityNodeInfoList;
        arraylist.clear();
        if(mViewRootImpl.mView == null)
            break MISSING_BLOCK_LABEL_99;
        message = mViewRootImpl.mAttachInfo;
        if(message != null)
            break MISSING_BLOCK_LABEL_114;
        updateInfosForViewportAndReturnFindNodeResult(arraylist, iaccessibilityinteractionconnectioncallback, k, magnificationspec, region);
        return;
        mViewRootImpl.mAttachInfo.mAccessibilityFetchFlags = i;
        if(j == 0x7ffffffe)
            break MISSING_BLOCK_LABEL_176;
        message = findViewByAccessibilityId(j);
_L1:
        if(message == null)
            break MISSING_BLOCK_LABEL_238;
        i = message.getContext().getResources().getIdentifier(s, null, null);
        if(i <= 0)
        {
            updateInfosForViewportAndReturnFindNodeResult(arraylist, iaccessibilityinteractionconnectioncallback, k, magnificationspec, region);
            return;
        }
        break MISSING_BLOCK_LABEL_187;
        message = mViewRootImpl.mView;
          goto _L1
        if(mAddNodeInfosForViewId == null)
        {
            AddNodeInfosForViewId addnodeinfosforviewid = JVM INSTR new #9   <Class AccessibilityInteractionController$AddNodeInfosForViewId>;
            addnodeinfosforviewid.this. AddNodeInfosForViewId(null);
            mAddNodeInfosForViewId = addnodeinfosforviewid;
        }
        mAddNodeInfosForViewId.init(i, arraylist);
        message.findViewByPredicate(mAddNodeInfosForViewId);
        mAddNodeInfosForViewId.reset();
        updateInfosForViewportAndReturnFindNodeResult(arraylist, iaccessibilityinteractionconnectioncallback, k, magnificationspec, region);
        return;
        message;
        updateInfosForViewportAndReturnFindNodeResult(arraylist, iaccessibilityinteractionconnectioncallback, k, magnificationspec, region);
        throw message;
    }

    private void findFocusUiThread(Message message)
    {
        int i;
        int j;
        int k;
        int l;
        int i1;
        IAccessibilityInteractionConnectionCallback iaccessibilityinteractionconnectioncallback;
        MagnificationSpec magnificationspec;
        Region region;
        Object obj;
        View view;
        Object obj1;
        Object obj2;
        i = message.arg1;
        j = message.arg2;
        message = (SomeArgs)message.obj;
        k = ((SomeArgs) (message)).argi1;
        l = ((SomeArgs) (message)).argi2;
        i1 = ((SomeArgs) (message)).argi3;
        iaccessibilityinteractionconnectioncallback = (IAccessibilityInteractionConnectionCallback)((SomeArgs) (message)).arg1;
        magnificationspec = (MagnificationSpec)((SomeArgs) (message)).arg2;
        region = (Region)((SomeArgs) (message)).arg3;
        message.recycle();
        obj = null;
        view = null;
        obj1 = null;
        obj2 = obj1;
        if(mViewRootImpl.mView == null)
            break MISSING_BLOCK_LABEL_106;
        obj2 = obj1;
        message = mViewRootImpl.mAttachInfo;
        if(message != null)
            break MISSING_BLOCK_LABEL_120;
        updateInfoForViewportAndReturnFindNodeResult(null, iaccessibilityinteractionconnectioncallback, k, magnificationspec, region);
        return;
        obj2 = obj1;
        mViewRootImpl.mAttachInfo.mAccessibilityFetchFlags = i;
        if(l == 0x7ffffffe) goto _L2; else goto _L1
_L1:
        obj2 = obj1;
        Object obj3 = findViewByAccessibilityId(l);
_L9:
        message = obj;
        if(obj3 == null) goto _L4; else goto _L3
_L3:
        obj2 = obj1;
        message = obj;
        if(!isShown(((View) (obj3)))) goto _L4; else goto _L5
_L5:
        j;
        JVM INSTR tableswitch 1 2: default 204
    //                   1 435
    //                   2 292;
           goto _L6 _L7 _L8
_L6:
        obj2 = obj1;
        obj3 = JVM INSTR new #485 <Class IllegalArgumentException>;
        obj2 = obj1;
        message = JVM INSTR new #487 <Class StringBuilder>;
        obj2 = obj1;
        message.StringBuilder();
        obj2 = obj1;
        ((IllegalArgumentException) (obj3)).IllegalArgumentException(message.append("Unknown focus type: ").append(j).toString());
        obj2 = obj1;
        throw obj3;
        message;
        updateInfoForViewportAndReturnFindNodeResult(((AccessibilityNodeInfo) (obj2)), iaccessibilityinteractionconnectioncallback, k, magnificationspec, region);
        throw message;
_L2:
        obj2 = obj1;
        obj3 = mViewRootImpl.mView;
          goto _L9
_L8:
        obj2 = obj1;
        view = mViewRootImpl.mAccessibilityFocusedHost;
        message = obj;
        if(view == null) goto _L4; else goto _L10
_L10:
        obj2 = obj1;
        boolean flag = ViewRootImpl.isViewDescendantOf(view, ((View) (obj3)));
        if(!(flag ^ true)) goto _L12; else goto _L11
_L11:
        message = obj;
_L4:
        updateInfoForViewportAndReturnFindNodeResult(message, iaccessibilityinteractionconnectioncallback, k, magnificationspec, region);
        return;
_L12:
        obj2 = obj1;
        message = obj;
        if(!isShown(view)) goto _L4; else goto _L13
_L13:
        obj2 = obj1;
        if(view.getAccessibilityNodeProvider() == null) goto _L15; else goto _L14
_L14:
        obj2 = obj1;
        message = obj;
        if(mViewRootImpl.mAccessibilityFocusedVirtualView == null) goto _L4; else goto _L16
_L16:
        obj2 = obj1;
        message = AccessibilityNodeInfo.obtain(mViewRootImpl.mAccessibilityFocusedVirtualView);
          goto _L4
_L15:
        message = obj;
        if(i1 != -1) goto _L4; else goto _L17
_L17:
        obj2 = obj1;
        message = view.createAccessibilityNodeInfo();
          goto _L4
_L7:
        obj2 = obj1;
        obj3 = ((View) (obj3)).findFocus();
        message = obj;
        if(obj3 == null) goto _L4; else goto _L18
_L18:
        obj2 = obj1;
        message = obj;
        if(isShown(((View) (obj3))) ^ true) goto _L4; else goto _L19
_L19:
        obj2 = obj1;
        message = ((View) (obj3)).getAccessibilityNodeProvider();
        obj2 = view;
        if(message == null) goto _L21; else goto _L20
_L20:
        obj2 = obj1;
        message = message.findFocus(j);
        obj2 = message;
_L21:
        message = ((Message) (obj2));
        if(obj2 != null) goto _L4; else goto _L22
_L22:
        message = ((View) (obj3)).createAccessibilityNodeInfo();
          goto _L4
    }

    private View findViewByAccessibilityId(int i)
    {
        View view = mViewRootImpl.mView;
        if(view == null)
            return null;
        view = view.findViewByAccessibilityId(i);
        if(view != null && isShown(view) ^ true)
            return null;
        else
            return view;
    }

    private void focusSearchUiThread(Message message)
    {
        int i;
        int j;
        int k;
        int l;
        IAccessibilityInteractionConnectionCallback iaccessibilityinteractionconnectioncallback;
        MagnificationSpec magnificationspec;
        Region region;
        Object obj;
        i = message.arg1;
        j = message.arg2;
        message = (SomeArgs)message.obj;
        k = ((SomeArgs) (message)).argi2;
        l = ((SomeArgs) (message)).argi3;
        iaccessibilityinteractionconnectioncallback = (IAccessibilityInteractionConnectionCallback)((SomeArgs) (message)).arg1;
        magnificationspec = (MagnificationSpec)((SomeArgs) (message)).arg2;
        region = (Region)((SomeArgs) (message)).arg3;
        message.recycle();
        obj = null;
        if(mViewRootImpl.mView == null)
            break MISSING_BLOCK_LABEL_86;
        message = mViewRootImpl.mAttachInfo;
        if(message != null)
            break MISSING_BLOCK_LABEL_100;
        updateInfoForViewportAndReturnFindNodeResult(null, iaccessibilityinteractionconnectioncallback, l, magnificationspec, region);
        return;
        mViewRootImpl.mAttachInfo.mAccessibilityFetchFlags = i;
        if(j == 0x7ffffffe) goto _L2; else goto _L1
_L1:
        View view = findViewByAccessibilityId(j);
_L4:
        message = obj;
        if(view == null)
            break MISSING_BLOCK_LABEL_168;
        message = obj;
        if(!isShown(view))
            break MISSING_BLOCK_LABEL_168;
        view = view.focusSearch(k);
        message = obj;
        if(view == null)
            break MISSING_BLOCK_LABEL_168;
        message = view.createAccessibilityNodeInfo();
        updateInfoForViewportAndReturnFindNodeResult(message, iaccessibilityinteractionconnectioncallback, l, magnificationspec, region);
        return;
_L2:
        view = mViewRootImpl.mView;
        if(true) goto _L4; else goto _L3
_L3:
        message;
        updateInfoForViewportAndReturnFindNodeResult(null, iaccessibilityinteractionconnectioncallback, l, magnificationspec, region);
        throw message;
    }

    private boolean handleClickableSpanActionUiThread(View view, int i, Bundle bundle)
    {
        android.os.Parcelable parcelable;
        AccessibilityNodeProvider accessibilitynodeprovider;
        parcelable = bundle.getParcelable("android.view.accessibility.action.ACTION_ARGUMENT_ACCESSIBLE_CLICKABLE_SPAN");
        if(!(parcelable instanceof AccessibilityClickableSpan))
            return false;
        bundle = null;
        accessibilitynodeprovider = view.getAccessibilityNodeProvider();
        if(accessibilitynodeprovider == null) goto _L2; else goto _L1
_L1:
        bundle = accessibilitynodeprovider.createAccessibilityNodeInfo(i);
_L4:
        if(bundle == null)
            return false;
        break; /* Loop/switch isn't completed */
_L2:
        if(i == -1)
            bundle = view.createAccessibilityNodeInfo();
        if(true) goto _L4; else goto _L3
_L3:
        bundle = ((AccessibilityClickableSpan)parcelable).findClickableSpan(bundle.getOriginalText());
        if(bundle != null)
        {
            bundle.onClick(view);
            return true;
        } else
        {
            return false;
        }
    }

    private boolean holdOffMessageIfNeeded(Message message, int i, long l)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(mNumActiveRequestPreparers == 0)
            break MISSING_BLOCK_LABEL_28;
        queueMessageToHandleOncePrepared(message, i, l);
        obj;
        JVM INSTR monitorexit ;
        return true;
        int j = message.what;
        if(j == 2)
            break MISSING_BLOCK_LABEL_45;
        obj;
        JVM INSTR monitorexit ;
        return false;
        SomeArgs someargs;
        Bundle bundle;
        someargs = (SomeArgs)message.obj;
        bundle = (Bundle)someargs.arg4;
        if(bundle != null)
            break MISSING_BLOCK_LABEL_74;
        obj;
        JVM INSTR monitorexit ;
        return false;
        List list;
        j = someargs.argi1;
        list = mA11yManager.getRequestPreparersForAccessibilityId(j);
        if(list != null)
            break MISSING_BLOCK_LABEL_102;
        obj;
        JVM INSTR monitorexit ;
        return false;
        String s = bundle.getString("android.view.accessibility.AccessibilityNodeInfo.extra_data_requested");
        if(s != null)
            break MISSING_BLOCK_LABEL_122;
        obj;
        JVM INSTR monitorexit ;
        return false;
        mNumActiveRequestPreparers = list.size();
        j = 0;
_L3:
        Message message1;
        SomeArgs someargs1;
        if(j >= list.size())
            break MISSING_BLOCK_LABEL_307;
        message1 = mHandler.obtainMessage(7);
        someargs1 = SomeArgs.obtain();
        if(someargs.argi2 != 0x7fffffff)
            break MISSING_BLOCK_LABEL_297;
        int k = -1;
_L1:
        someargs1.argi1 = k;
        someargs1.arg1 = list.get(j);
        someargs1.arg2 = s;
        someargs1.arg3 = bundle;
        Message message2 = mHandler.obtainMessage(8);
        k = mActiveRequestPreparerId + 1;
        mActiveRequestPreparerId = k;
        message2.arg1 = k;
        someargs1.arg4 = message2;
        message1.obj = someargs1;
        scheduleMessage(message1, i, l, true);
        mHandler.obtainMessage(9);
        mHandler.sendEmptyMessageDelayed(9, 500L);
        j++;
        continue; /* Loop/switch isn't completed */
        k = someargs.argi2;
          goto _L1
        queueMessageToHandleOncePrepared(message, i, l);
        obj;
        JVM INSTR monitorexit ;
        return true;
        message;
        throw message;
        if(true) goto _L3; else goto _L2
_L2:
    }

    private boolean isShown(View view)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(view.mAttachInfo != null)
        {
            flag1 = flag;
            if(view.mAttachInfo.mWindowVisibility == 0)
                flag1 = view.isShown();
        }
        return flag1;
    }

    private void performAccessibilityActionUiThread(Message message)
    {
        int i;
        int j;
        int k;
        int l;
        int i1;
        IAccessibilityInteractionConnectionCallback iaccessibilityinteractionconnectioncallback;
        Bundle bundle;
        boolean flag;
        i = message.arg1;
        j = message.arg2;
        message = (SomeArgs)message.obj;
        k = ((SomeArgs) (message)).argi1;
        l = ((SomeArgs) (message)).argi2;
        i1 = ((SomeArgs) (message)).argi3;
        iaccessibilityinteractionconnectioncallback = (IAccessibilityInteractionConnectionCallback)((SomeArgs) (message)).arg1;
        bundle = (Bundle)((SomeArgs) (message)).arg2;
        message.recycle();
        flag = false;
        if(mViewRootImpl.mView == null) goto _L2; else goto _L1
_L1:
        message = mViewRootImpl.mAttachInfo;
        if(message != null) goto _L3; else goto _L2
_L2:
        mViewRootImpl.mAttachInfo.mAccessibilityFetchFlags = 0;
        iaccessibilityinteractionconnectioncallback.setPerformAccessibilityActionResult(false, i1);
_L15:
        return;
_L3:
        if(mViewRootImpl.mStopped || mViewRootImpl.mPausedForTransition) goto _L2; else goto _L4
_L4:
        mViewRootImpl.mAttachInfo.mAccessibilityFetchFlags = i;
        if(j == 0x7ffffffe) goto _L6; else goto _L5
_L5:
        message = findViewByAccessibilityId(j);
_L12:
        boolean flag1 = flag;
        if(message == null) goto _L8; else goto _L7
_L7:
        flag1 = flag;
        if(!isShown(message)) goto _L8; else goto _L9
_L9:
        if(l != 0x1020168) goto _L11; else goto _L10
_L10:
        flag1 = handleClickableSpanActionUiThread(message, k, bundle);
_L8:
        mViewRootImpl.mAttachInfo.mAccessibilityFetchFlags = 0;
        iaccessibilityinteractionconnectioncallback.setPerformAccessibilityActionResult(flag1, i1);
_L14:
        return;
_L6:
        message = mViewRootImpl.mView;
          goto _L12
_L11:
        AccessibilityNodeProvider accessibilitynodeprovider = message.getAccessibilityNodeProvider();
        if(accessibilitynodeprovider == null)
            break MISSING_BLOCK_LABEL_249;
        flag1 = accessibilitynodeprovider.performAction(k, l, bundle);
          goto _L8
        flag1 = flag;
        if(k != -1) goto _L8; else goto _L13
_L13:
        flag1 = message.performAccessibilityAction(l, bundle);
          goto _L8
        message;
        try
        {
            mViewRootImpl.mAttachInfo.mAccessibilityFetchFlags = 0;
            iaccessibilityinteractionconnectioncallback.setPerformAccessibilityActionResult(false, i1);
        }
        catch(RemoteException remoteexception) { }
        throw message;
        message;
          goto _L14
        message;
          goto _L15
    }

    private void prepareForExtraDataRequestUiThread(Message message)
    {
        message = (SomeArgs)message.obj;
        int i = ((SomeArgs) (message)).argi1;
        ((AccessibilityRequestPreparer)((SomeArgs) (message)).arg1).onPrepareExtraData(i, (String)((SomeArgs) (message)).arg2, (Bundle)((SomeArgs) (message)).arg3, (Message)((SomeArgs) (message)).arg4);
    }

    private void queueMessageToHandleOncePrepared(Message message, int i, long l)
    {
        if(mMessagesWaitingForRequestPreparer == null)
            mMessagesWaitingForRequestPreparer = new ArrayList(1);
        message = new MessageHolder(message, i, l);
        mMessagesWaitingForRequestPreparer.add(message);
    }

    private void recycleMagnificationSpecAndRegionIfNeeded(MagnificationSpec magnificationspec, Region region)
    {
        if(Process.myPid() == Binder.getCallingPid()) goto _L2; else goto _L1
_L1:
        if(magnificationspec != null)
            magnificationspec.recycle();
_L4:
        return;
_L2:
        if(region != null)
            region.recycle();
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void requestPreparerDoneUiThread(Message message)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(message.arg1 == mActiveRequestPreparerId)
            break MISSING_BLOCK_LABEL_30;
        Slog.e("AccessibilityInteractionController", "Surprising AccessibilityRequestPreparer callback (likely late)");
        obj;
        JVM INSTR monitorexit ;
        return;
        mNumActiveRequestPreparers = mNumActiveRequestPreparers - 1;
        if(mNumActiveRequestPreparers <= 0)
        {
            mHandler.removeMessages(9);
            scheduleAllMessagesWaitingForRequestPreparerLocked();
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        message;
        throw message;
    }

    private void requestPreparerTimeoutUiThread()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        Slog.e("AccessibilityInteractionController", "AccessibilityRequestPreparer timed out");
        scheduleAllMessagesWaitingForRequestPreparerLocked();
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private void scheduleAllMessagesWaitingForRequestPreparerLocked()
    {
        int i = mMessagesWaitingForRequestPreparer.size();
        int j = 0;
        while(j < i) 
        {
            MessageHolder messageholder = (MessageHolder)mMessagesWaitingForRequestPreparer.get(j);
            Message message = messageholder.mMessage;
            int k = messageholder.mInterrogatingPid;
            long l = messageholder.mInterrogatingTid;
            boolean flag;
            if(j == 0)
                flag = true;
            else
                flag = false;
            scheduleMessage(message, k, l, flag);
            j++;
        }
        mMessagesWaitingForRequestPreparer.clear();
        mNumActiveRequestPreparers = 0;
        mActiveRequestPreparerId = -1;
    }

    private void scheduleMessage(Message message, int i, long l, boolean flag)
    {
        if(flag || holdOffMessageIfNeeded(message, i, l) ^ true)
            if(i == mMyProcessId && l == mMyLooperThreadId)
                AccessibilityInteractionClient.getInstanceForThread(l).setSameThreadMessage(message);
            else
                mHandler.sendMessage(message);
    }

    private boolean shouldApplyAppScaleAndMagnificationSpec(float f, MagnificationSpec magnificationspec)
    {
        boolean flag;
        if(f == 1.0F)
        {
            if(magnificationspec != null)
                flag = magnificationspec.isNop() ^ true;
            else
                flag = false;
        } else
        {
            flag = true;
        }
        return flag;
    }

    private void updateInfoForViewportAndReturnFindNodeResult(AccessibilityNodeInfo accessibilitynodeinfo, IAccessibilityInteractionConnectionCallback iaccessibilityinteractionconnectioncallback, int i, MagnificationSpec magnificationspec, Region region)
    {
        mViewRootImpl.mAttachInfo.mAccessibilityFetchFlags = 0;
        applyAppScaleAndMagnificationSpecIfNeeded(accessibilitynodeinfo, magnificationspec);
        adjustIsVisibleToUserIfNeeded(accessibilitynodeinfo, region);
        iaccessibilityinteractionconnectioncallback.setFindAccessibilityNodeInfoResult(accessibilitynodeinfo, i);
        recycleMagnificationSpecAndRegionIfNeeded(magnificationspec, region);
_L2:
        return;
        accessibilitynodeinfo;
        recycleMagnificationSpecAndRegionIfNeeded(magnificationspec, region);
        if(true) goto _L2; else goto _L1
_L1:
        accessibilitynodeinfo;
        recycleMagnificationSpecAndRegionIfNeeded(magnificationspec, region);
        throw accessibilitynodeinfo;
    }

    private void updateInfosForViewportAndReturnFindNodeResult(List list, IAccessibilityInteractionConnectionCallback iaccessibilityinteractionconnectioncallback, int i, MagnificationSpec magnificationspec, Region region)
    {
        mViewRootImpl.mAttachInfo.mAccessibilityFetchFlags = 0;
        applyAppScaleAndMagnificationSpecIfNeeded(list, magnificationspec);
        adjustIsVisibleToUserIfNeeded(list, region);
        iaccessibilityinteractionconnectioncallback.setFindAccessibilityNodeInfosResult(list, i);
        if(list == null)
            break MISSING_BLOCK_LABEL_43;
        list.clear();
        recycleMagnificationSpecAndRegionIfNeeded(magnificationspec, region);
_L2:
        return;
        list;
        recycleMagnificationSpecAndRegionIfNeeded(magnificationspec, region);
        if(true) goto _L2; else goto _L1
_L1:
        list;
        recycleMagnificationSpecAndRegionIfNeeded(magnificationspec, region);
        throw list;
    }

    public void findAccessibilityNodeInfoByAccessibilityIdClientThread(long l, Region region, int i, IAccessibilityInteractionConnectionCallback iaccessibilityinteractionconnectioncallback, int j, int k, 
            long l1, MagnificationSpec magnificationspec, Bundle bundle)
    {
        Message message = mHandler.obtainMessage();
        message.what = 2;
        message.arg1 = j;
        SomeArgs someargs = SomeArgs.obtain();
        someargs.argi1 = AccessibilityNodeInfo.getAccessibilityViewId(l);
        someargs.argi2 = AccessibilityNodeInfo.getVirtualDescendantId(l);
        someargs.argi3 = i;
        someargs.arg1 = iaccessibilityinteractionconnectioncallback;
        someargs.arg2 = magnificationspec;
        someargs.arg3 = region;
        someargs.arg4 = bundle;
        message.obj = someargs;
        scheduleMessage(message, k, l1, false);
    }

    public void findAccessibilityNodeInfosByTextClientThread(long l, String s, Region region, int i, IAccessibilityInteractionConnectionCallback iaccessibilityinteractionconnectioncallback, int j, 
            int k, long l1, MagnificationSpec magnificationspec)
    {
        Message message = mHandler.obtainMessage();
        message.what = 4;
        message.arg1 = j;
        SomeArgs someargs = SomeArgs.obtain();
        someargs.arg1 = s;
        someargs.arg2 = iaccessibilityinteractionconnectioncallback;
        someargs.arg3 = magnificationspec;
        someargs.argi1 = AccessibilityNodeInfo.getAccessibilityViewId(l);
        someargs.argi2 = AccessibilityNodeInfo.getVirtualDescendantId(l);
        someargs.argi3 = i;
        someargs.arg4 = region;
        message.obj = someargs;
        scheduleMessage(message, k, l1, false);
    }

    public void findAccessibilityNodeInfosByViewIdClientThread(long l, String s, Region region, int i, IAccessibilityInteractionConnectionCallback iaccessibilityinteractionconnectioncallback, int j, 
            int k, long l1, MagnificationSpec magnificationspec)
    {
        Message message = mHandler.obtainMessage();
        message.what = 3;
        message.arg1 = j;
        message.arg2 = AccessibilityNodeInfo.getAccessibilityViewId(l);
        SomeArgs someargs = SomeArgs.obtain();
        someargs.argi1 = i;
        someargs.arg1 = iaccessibilityinteractionconnectioncallback;
        someargs.arg2 = magnificationspec;
        someargs.arg3 = s;
        someargs.arg4 = region;
        message.obj = someargs;
        scheduleMessage(message, k, l1, false);
    }

    public void findFocusClientThread(long l, int i, Region region, int j, IAccessibilityInteractionConnectionCallback iaccessibilityinteractionconnectioncallback, int k, 
            int i1, long l1, MagnificationSpec magnificationspec)
    {
        Message message = mHandler.obtainMessage();
        message.what = 5;
        message.arg1 = k;
        message.arg2 = i;
        SomeArgs someargs = SomeArgs.obtain();
        someargs.argi1 = j;
        someargs.argi2 = AccessibilityNodeInfo.getAccessibilityViewId(l);
        someargs.argi3 = AccessibilityNodeInfo.getVirtualDescendantId(l);
        someargs.arg1 = iaccessibilityinteractionconnectioncallback;
        someargs.arg2 = magnificationspec;
        someargs.arg3 = region;
        message.obj = someargs;
        scheduleMessage(message, i1, l1, false);
    }

    public void focusSearchClientThread(long l, int i, Region region, int j, IAccessibilityInteractionConnectionCallback iaccessibilityinteractionconnectioncallback, int k, 
            int i1, long l1, MagnificationSpec magnificationspec)
    {
        Message message = mHandler.obtainMessage();
        message.what = 6;
        message.arg1 = k;
        message.arg2 = AccessibilityNodeInfo.getAccessibilityViewId(l);
        SomeArgs someargs = SomeArgs.obtain();
        someargs.argi2 = i;
        someargs.argi3 = j;
        someargs.arg1 = iaccessibilityinteractionconnectioncallback;
        someargs.arg2 = magnificationspec;
        someargs.arg3 = region;
        message.obj = someargs;
        scheduleMessage(message, i1, l1, false);
    }

    public void performAccessibilityActionClientThread(long l, int i, Bundle bundle, int j, IAccessibilityInteractionConnectionCallback iaccessibilityinteractionconnectioncallback, int k, 
            int i1, long l1)
    {
        Message message = mHandler.obtainMessage();
        message.what = 1;
        message.arg1 = k;
        message.arg2 = AccessibilityNodeInfo.getAccessibilityViewId(l);
        SomeArgs someargs = SomeArgs.obtain();
        someargs.argi1 = AccessibilityNodeInfo.getVirtualDescendantId(l);
        someargs.argi2 = i;
        someargs.argi3 = j;
        someargs.arg1 = iaccessibilityinteractionconnectioncallback;
        someargs.arg2 = bundle;
        message.obj = someargs;
        scheduleMessage(message, i1, l1, false);
    }

    private static final boolean CONSIDER_REQUEST_PREPARERS = false;
    private static final boolean ENFORCE_NODE_TREE_CONSISTENT = false;
    private static final boolean IGNORE_REQUEST_PREPARERS = true;
    private static final String LOG_TAG = "AccessibilityInteractionController";
    private static final long REQUEST_PREPARER_TIMEOUT_MS = 500L;
    private final AccessibilityManager mA11yManager;
    private int mActiveRequestPreparerId;
    private AddNodeInfosForViewId mAddNodeInfosForViewId;
    private final Handler mHandler;
    private final Object mLock = new Object();
    private List mMessagesWaitingForRequestPreparer;
    private final long mMyLooperThreadId;
    private final int mMyProcessId = Process.myPid();
    private int mNumActiveRequestPreparers;
    private final AccessibilityNodePrefetcher mPrefetcher = new AccessibilityNodePrefetcher(null);
    private final ArrayList mTempAccessibilityNodeInfoList = new ArrayList();
    private final ArrayList mTempArrayList = new ArrayList();
    private final Point mTempPoint = new Point();
    private final Rect mTempRect = new Rect();
    private final Rect mTempRect1 = new Rect();
    private final Rect mTempRect2 = new Rect();
    private final ViewRootImpl mViewRootImpl;
}
