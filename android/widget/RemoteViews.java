// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.app.*;
import android.appwidget.AppWidgetHostView;
import android.content.*;
import android.content.pm.ApplicationInfo;
import android.content.res.*;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.*;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import android.view.*;
import com.android.internal.util.NotificationColorUtil;
import com.android.internal.util.Preconditions;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.Executor;
import libcore.util.Objects;

// Referenced classes of package android.widget:
//            TextView, ImageView, AdapterView, AdapterViewAnimator, 
//            AbsListView, RemoteViewsListAdapter, Adapter

public class RemoteViews
    implements Parcelable, android.view.LayoutInflater.Filter
{
    private static abstract class Action
        implements Parcelable
    {

        public abstract void apply(View view, ViewGroup viewgroup, OnClickHandler onclickhandler)
            throws ActionException;

        public int describeContents()
        {
            return 0;
        }

        public abstract String getActionName();

        public String getUniqueKey()
        {
            return (new StringBuilder()).append(getActionName()).append(viewId).toString();
        }

        public boolean hasSameAppInfo(ApplicationInfo applicationinfo)
        {
            return true;
        }

        public Action initActionAsync(ViewTree viewtree, ViewGroup viewgroup, OnClickHandler onclickhandler)
        {
            return this;
        }

        public int mergeBehavior()
        {
            return 0;
        }

        public boolean prefersAsyncApply()
        {
            return false;
        }

        public void setBitmapCache(BitmapCache bitmapcache)
        {
        }

        public void updateMemoryUsageEstimate(MemoryUsageCounter memoryusagecounter)
        {
        }

        public static final int MERGE_APPEND = 1;
        public static final int MERGE_IGNORE = 2;
        public static final int MERGE_REPLACE = 0;
        int viewId;

        private Action()
        {
        }

        Action(Action action)
        {
            this();
        }
    }

    public static class ActionException extends RuntimeException
    {

        public ActionException(Exception exception)
        {
            super(exception);
        }

        public ActionException(String s)
        {
            super(s);
        }
    }

    private class AsyncApplyTask extends AsyncTask
        implements android.os.CancellationSignal.OnCancelListener
    {

        static Exception _2D_get0(AsyncApplyTask asyncapplytask)
        {
            return asyncapplytask.mError;
        }

        static View _2D_get1(AsyncApplyTask asyncapplytask)
        {
            return asyncapplytask.mResult;
        }

        protected transient ViewTree doInBackground(Void avoid[])
        {
            int i;
            if(mResult == null)
                mResult = RemoteViews._2D_wrap1(RemoteViews.this, mContext, mRV, mParent);
            avoid = JVM INSTR new #72  <Class RemoteViews$ViewTree>;
            avoid.ViewTree(mResult, null);
            mTree = avoid;
            if(RemoteViews._2D_get2(mRV) == null)
                break MISSING_BLOCK_LABEL_134;
            i = RemoteViews._2D_get2(mRV).size();
            mActions = new Action[i];
            int j = 0;
            do
            {
                if(j >= i)
                    break;
                try
                {
                    if(!(isCancelled() ^ true))
                        break;
                    mActions[j] = ((Action)RemoteViews._2D_get2(mRV).get(j)).initActionAsync(mTree, mParent, mHandler);
                }
                // Misplaced declaration of an exception variable
                catch(Void avoid[])
                {
                    mError = avoid;
                    return null;
                }
                j++;
            } while(true);
            break MISSING_BLOCK_LABEL_139;
            mActions = null;
            avoid = mTree;
            return avoid;
        }

        protected volatile Object doInBackground(Object aobj[])
        {
            return doInBackground((Void[])aobj);
        }

        public void onCancel()
        {
            cancel(true);
        }

        protected void onPostExecute(ViewTree viewtree)
        {
            if(mError != null) goto _L2; else goto _L1
_L1:
            if(mActions == null) goto _L2; else goto _L3
_L3:
            if(mHandler != null) goto _L5; else goto _L4
_L4:
            OnClickHandler onclickhandler = RemoteViews._2D_get1();
_L7:
            Action aaction[] = mActions;
            int i = 0;
            int j = aaction.length;
_L6:
            if(i >= j)
                break; /* Loop/switch isn't completed */
            aaction[i].apply(ViewTree._2D_get0(viewtree), mParent, onclickhandler);
            i++;
            if(true) goto _L6; else goto _L2
_L5:
            onclickhandler = mHandler;
              goto _L7
            Exception exception;
            exception;
            mError = exception;
_L2:
            if(mListener != null)
            {
                if(mError != null)
                    mListener.onError(mError);
                else
                    mListener.onViewApplied(ViewTree._2D_get0(viewtree));
            } else
            if(mError != null)
                if(mError instanceof ActionException)
                    throw (ActionException)mError;
                else
                    throw new ActionException(mError);
            return;
        }

        protected volatile void onPostExecute(Object obj)
        {
            onPostExecute((ViewTree)obj);
        }

        private Action mActions[];
        final Context mContext;
        private Exception mError;
        final OnClickHandler mHandler;
        final OnViewAppliedListener mListener;
        final ViewGroup mParent;
        final RemoteViews mRV;
        private View mResult;
        private ViewTree mTree;
        final RemoteViews this$0;

        private AsyncApplyTask(RemoteViews remoteviews1, ViewGroup viewgroup, Context context, OnViewAppliedListener onviewappliedlistener, OnClickHandler onclickhandler, View view)
        {
            this$0 = RemoteViews.this;
            super();
            mRV = remoteviews1;
            mParent = viewgroup;
            mContext = context;
            mListener = onviewappliedlistener;
            mHandler = onclickhandler;
            mResult = view;
            RemoteViews._2D_wrap7(context, onclickhandler);
        }

        AsyncApplyTask(RemoteViews remoteviews1, ViewGroup viewgroup, Context context, OnViewAppliedListener onviewappliedlistener, OnClickHandler onclickhandler, View view, 
                AsyncApplyTask asyncapplytask)
        {
            this(remoteviews1, viewgroup, context, onviewappliedlistener, onclickhandler, view);
        }
    }

    private static class BitmapCache
    {

        public void addBitmapMemory(MemoryUsageCounter memoryusagecounter)
        {
            for(int i = 0; i < mBitmaps.size(); i++)
                memoryusagecounter.addBitmapMemory((Bitmap)mBitmaps.get(i));

        }

        public void assimilate(BitmapCache bitmapcache)
        {
            bitmapcache = bitmapcache.mBitmaps;
            int i = bitmapcache.size();
            for(int j = 0; j < i; j++)
            {
                Bitmap bitmap = (Bitmap)bitmapcache.get(j);
                if(!mBitmaps.contains(bitmap))
                    mBitmaps.add(bitmap);
            }

        }

        protected BitmapCache clone()
        {
            BitmapCache bitmapcache = new BitmapCache();
            bitmapcache.mBitmaps.addAll(mBitmaps);
            return bitmapcache;
        }

        protected volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
        }

        public Bitmap getBitmapForId(int i)
        {
            if(i == -1 || i >= mBitmaps.size())
                return null;
            else
                return (Bitmap)mBitmaps.get(i);
        }

        public int getBitmapId(Bitmap bitmap)
        {
            if(bitmap == null)
                return -1;
            if(mBitmaps.contains(bitmap))
            {
                return mBitmaps.indexOf(bitmap);
            } else
            {
                mBitmaps.add(bitmap);
                return mBitmaps.size() - 1;
            }
        }

        public void writeBitmapsToParcel(Parcel parcel, int i)
        {
            int j = mBitmaps.size();
            parcel.writeInt(j);
            for(int k = 0; k < j; k++)
                ((Bitmap)mBitmaps.get(k)).writeToParcel(parcel, i);

        }

        ArrayList mBitmaps;

        public BitmapCache()
        {
            mBitmaps = new ArrayList();
        }

        public BitmapCache(Parcel parcel)
        {
            int i = parcel.readInt();
            mBitmaps = new ArrayList();
            for(int j = 0; j < i; j++)
            {
                Bitmap bitmap = (Bitmap)Bitmap.CREATOR.createFromParcel(parcel);
                mBitmaps.add(bitmap);
            }

        }
    }

    private class BitmapReflectionAction extends Action
    {

        public void apply(View view, ViewGroup viewgroup, OnClickHandler onclickhandler)
            throws ActionException
        {
            (new ReflectionAction(viewId, methodName, 12, bitmap)).apply(view, viewgroup, onclickhandler);
        }

        public String getActionName()
        {
            return "BitmapReflectionAction";
        }

        public void setBitmapCache(BitmapCache bitmapcache)
        {
            bitmapId = bitmapcache.getBitmapId(bitmap);
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(12);
            parcel.writeInt(viewId);
            parcel.writeString(methodName);
            parcel.writeInt(bitmapId);
        }

        Bitmap bitmap;
        int bitmapId;
        String methodName;
        final RemoteViews this$0;

        BitmapReflectionAction(int i, String s, Bitmap bitmap1)
        {
            this$0 = RemoteViews.this;
            super(null);
            bitmap = bitmap1;
            viewId = i;
            methodName = s;
            bitmapId = RemoteViews._2D_get4(RemoteViews.this).getBitmapId(bitmap1);
        }

        BitmapReflectionAction(Parcel parcel)
        {
            this$0 = RemoteViews.this;
            super(null);
            viewId = parcel.readInt();
            methodName = parcel.readString();
            bitmapId = parcel.readInt();
            bitmap = RemoteViews._2D_get4(RemoteViews.this).getBitmapForId(bitmapId);
        }
    }

    private static class LayoutParamAction extends Action
    {

        private static int resolveDimenPixelOffset(View view, int i)
        {
            if(i == 0)
                return 0;
            else
                return view.getContext().getResources().getDimensionPixelOffset(i);
        }

        public void apply(View view, ViewGroup viewgroup, OnClickHandler onclickhandler)
        {
            viewgroup = view.findViewById(viewId);
            if(viewgroup == null)
                return;
            view = viewgroup.getLayoutParams();
            if(view == null)
                return;
            property;
            JVM INSTR tableswitch 1 3: default 56
        //                       1 86
        //                       2 152
        //                       3 118;
               goto _L1 _L2 _L3 _L4
_L1:
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown property ").append(property).toString());
_L2:
            if(view instanceof android.view.ViewGroup.MarginLayoutParams)
            {
                int i = resolveDimenPixelOffset(viewgroup, value);
                ((android.view.ViewGroup.MarginLayoutParams)view).setMarginEnd(i);
                viewgroup.setLayoutParams(view);
            }
_L6:
            return;
_L4:
            if(view instanceof android.view.ViewGroup.MarginLayoutParams)
            {
                int j = resolveDimenPixelOffset(viewgroup, value);
                ((android.view.ViewGroup.MarginLayoutParams)view).bottomMargin = j;
                viewgroup.setLayoutParams(view);
            }
            continue; /* Loop/switch isn't completed */
_L3:
            view.width = value;
            viewgroup.setLayoutParams(view);
            if(true) goto _L6; else goto _L5
_L5:
        }

        public String getActionName()
        {
            return (new StringBuilder()).append("LayoutParamAction").append(property).append(".").toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(19);
            parcel.writeInt(viewId);
            parcel.writeInt(property);
            parcel.writeInt(value);
        }

        public static final int LAYOUT_MARGIN_BOTTOM_DIMEN = 3;
        public static final int LAYOUT_MARGIN_END_DIMEN = 1;
        public static final int LAYOUT_WIDTH = 2;
        int property;
        int value;

        public LayoutParamAction(int i, int j, int k)
        {
            super(null);
            viewId = i;
            property = j;
            value = k;
        }

        public LayoutParamAction(Parcel parcel)
        {
            super(null);
            viewId = parcel.readInt();
            property = parcel.readInt();
            value = parcel.readInt();
        }
    }

    private class MemoryUsageCounter
    {

        public void addBitmapMemory(Bitmap bitmap)
        {
            increment(bitmap.getAllocationByteCount());
        }

        public void clear()
        {
            mMemoryUsage = 0;
        }

        public int getMemoryUsage()
        {
            return mMemoryUsage;
        }

        public void increment(int i)
        {
            mMemoryUsage = mMemoryUsage + i;
        }

        int mMemoryUsage;
        final RemoteViews this$0;

        private MemoryUsageCounter()
        {
            this$0 = RemoteViews.this;
            super();
        }

        MemoryUsageCounter(MemoryUsageCounter memoryusagecounter)
        {
            this();
        }
    }

    static class MutablePair
    {

        public boolean equals(Object obj)
        {
            boolean flag = false;
            if(!(obj instanceof MutablePair))
                return false;
            obj = (MutablePair)obj;
            if(Objects.equal(((MutablePair) (obj)).first, first))
                flag = Objects.equal(((MutablePair) (obj)).second, second);
            return flag;
        }

        public int hashCode()
        {
            int i = 0;
            int j;
            if(first == null)
                j = 0;
            else
                j = first.hashCode();
            if(second != null)
                i = second.hashCode();
            return j ^ i;
        }

        Object first;
        Object second;

        MutablePair(Object obj, Object obj1)
        {
            first = obj;
            second = obj1;
        }
    }

    public static class OnClickHandler
    {

        public boolean onClickHandler(View view, PendingIntent pendingintent, Intent intent)
        {
            return onClickHandler(view, pendingintent, intent, -1);
        }

        public boolean onClickHandler(View view, PendingIntent pendingintent, Intent intent, int i)
        {
            Context context = view.getContext();
            if(mEnterAnimationId == 0) goto _L2; else goto _L1
_L1:
            view = ActivityOptions.makeCustomAnimation(context, mEnterAnimationId, 0);
_L4:
            if(i == -1)
                break MISSING_BLOCK_LABEL_36;
            view.setLaunchStackId(i);
            context.startIntentSender(pendingintent.getIntentSender(), intent, 0x10000000, 0x10000000, 0, view.toBundle());
            return true;
_L2:
            try
            {
                view = ActivityOptions.makeBasic();
            }
            // Misplaced declaration of an exception variable
            catch(View view)
            {
                Log.e("RemoteViews", "Cannot send pending intent: ", view);
                return false;
            }
            // Misplaced declaration of an exception variable
            catch(View view)
            {
                Log.e("RemoteViews", "Cannot send pending intent due to unknown exception: ", view);
                return false;
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        public void setEnterAnimationId(int i)
        {
            mEnterAnimationId = i;
        }

        private int mEnterAnimationId;

        public OnClickHandler()
        {
        }
    }

    public static interface OnViewAppliedListener
    {

        public abstract void onError(Exception exception);

        public abstract void onViewApplied(View view);
    }

    private class OverrideTextColorsAction extends Action
    {

        public void apply(View view, ViewGroup viewgroup, OnClickHandler onclickhandler)
        {
            viewgroup = new Stack();
            viewgroup.add(view);
            do
            {
                if(viewgroup.isEmpty())
                    break;
                onclickhandler = (View)viewgroup.pop();
                if(onclickhandler instanceof TextView)
                {
                    view = (TextView)onclickhandler;
                    view.setText(NotificationColorUtil.clearColorSpans(view.getText()));
                    view.setTextColor(textColor);
                }
                if(onclickhandler instanceof ViewGroup)
                {
                    view = (ViewGroup)onclickhandler;
                    int i = 0;
                    while(i < view.getChildCount()) 
                    {
                        viewgroup.push(view.getChildAt(i));
                        i++;
                    }
                }
            } while(true);
        }

        public String getActionName()
        {
            return "OverrideTextColorsAction";
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(20);
            parcel.writeInt(textColor);
        }

        private final int textColor;
        final RemoteViews this$0;

        public OverrideTextColorsAction(int i)
        {
            this$0 = RemoteViews.this;
            super(null);
            textColor = i;
        }

        public OverrideTextColorsAction(Parcel parcel)
        {
            this$0 = RemoteViews.this;
            super(null);
            textColor = parcel.readInt();
        }
    }

    private final class ReflectionAction extends Action
    {

        private Class getParameterType()
        {
            switch(type)
            {
            default:
                return null;

            case 1: // '\001'
                return Boolean.TYPE;

            case 2: // '\002'
                return Byte.TYPE;

            case 3: // '\003'
                return Short.TYPE;

            case 4: // '\004'
                return Integer.TYPE;

            case 5: // '\005'
                return Long.TYPE;

            case 6: // '\006'
                return Float.TYPE;

            case 7: // '\007'
                return Double.TYPE;

            case 8: // '\b'
                return Character.TYPE;

            case 9: // '\t'
                return java/lang/String;

            case 10: // '\n'
                return java/lang/CharSequence;

            case 11: // '\013'
                return android/net/Uri;

            case 12: // '\f'
                return android/graphics/Bitmap;

            case 13: // '\r'
                return android/os/Bundle;

            case 14: // '\016'
                return android/content/Intent;

            case 15: // '\017'
                return android/content/res/ColorStateList;

            case 16: // '\020'
                return android/graphics/drawable/Icon;
            }
        }

        public void apply(View view, ViewGroup viewgroup, OnClickHandler onclickhandler)
        {
            viewgroup = view.findViewById(viewId);
            if(viewgroup == null)
                return;
            view = getParameterType();
            if(view == null)
                throw new ActionException((new StringBuilder()).append("bad type: ").append(type).toString());
            try
            {
                RemoteViews._2D_wrap5(RemoteViews.this, viewgroup, methodName, view).invoke(viewgroup, RemoteViews._2D_wrap3(value));
                return;
            }
            // Misplaced declaration of an exception variable
            catch(View view)
            {
                throw view;
            }
            // Misplaced declaration of an exception variable
            catch(View view)
            {
                throw new ActionException(view);
            }
        }

        public String getActionName()
        {
            return (new StringBuilder()).append("ReflectionAction").append(methodName).append(type).toString();
        }

        public Action initActionAsync(ViewTree viewtree, ViewGroup viewgroup, OnClickHandler onclickhandler)
        {
            viewgroup = viewtree.findViewById(viewId);
            if(viewgroup == null)
                return RemoteViews._2D_get0();
            onclickhandler = getParameterType();
            if(onclickhandler == null)
                throw new ActionException((new StringBuilder()).append("bad type: ").append(type).toString());
            try
            {
                onclickhandler = RemoteViews._2D_wrap5(RemoteViews.this, viewgroup, methodName, onclickhandler);
                onclickhandler = RemoteViews._2D_wrap4(RemoteViews.this, onclickhandler);
            }
            // Misplaced declaration of an exception variable
            catch(ViewTree viewtree)
            {
                throw viewtree;
            }
            // Misplaced declaration of an exception variable
            catch(ViewTree viewtree)
            {
                throw new ActionException(viewtree);
            }
            if(onclickhandler == null)
                break MISSING_BLOCK_LABEL_160;
            viewgroup = (Runnable)onclickhandler.invoke(viewgroup, RemoteViews._2D_wrap3(value));
            if(viewgroup != null)
                break MISSING_BLOCK_LABEL_107;
            return RemoteViews._2D_get0();
            if(viewgroup instanceof android.view.ViewStub.ViewReplaceRunnable)
            {
                viewtree.createTree();
                viewtree.findViewTreeById(viewId).replaceView(((android.view.ViewStub.ViewReplaceRunnable)viewgroup).view);
            }
            viewtree = new RunnableAction(viewgroup);
            return viewtree;
            return this;
        }

        public int mergeBehavior()
        {
            return !methodName.equals("smoothScrollBy") ? 0 : 1;
        }

        public boolean prefersAsyncApply()
        {
            boolean flag = true;
            boolean flag1 = flag;
            if(type != 11)
                if(type == 16)
                    flag1 = flag;
                else
                    flag1 = false;
            return flag1;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            boolean flag;
            boolean flag1;
            boolean flag2;
            boolean flag3;
            int j;
            flag = true;
            flag1 = true;
            flag2 = true;
            flag3 = true;
            j = 1;
            parcel.writeInt(2);
            parcel.writeInt(viewId);
            parcel.writeString(methodName);
            parcel.writeInt(type);
            type;
            JVM INSTR tableswitch 1 16: default 124
        //                       1 125
        //                       2 153
        //                       3 170
        //                       4 187
        //                       5 204
        //                       6 221
        //                       7 238
        //                       8 255
        //                       9 272
        //                       10 286
        //                       11 301
        //                       12 342
        //                       13 386
        //                       14 400
        //                       15 445
        //                       16 490;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17
_L1:
            return;
_L2:
            if(((Boolean)value).booleanValue())
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            continue; /* Loop/switch isn't completed */
_L3:
            parcel.writeByte(((Byte)value).byteValue());
            continue; /* Loop/switch isn't completed */
_L4:
            parcel.writeInt(((Short)value).shortValue());
            continue; /* Loop/switch isn't completed */
_L5:
            parcel.writeInt(((Integer)value).intValue());
            continue; /* Loop/switch isn't completed */
_L6:
            parcel.writeLong(((Long)value).longValue());
            continue; /* Loop/switch isn't completed */
_L7:
            parcel.writeFloat(((Float)value).floatValue());
            continue; /* Loop/switch isn't completed */
_L8:
            parcel.writeDouble(((Double)value).doubleValue());
            continue; /* Loop/switch isn't completed */
_L9:
            parcel.writeInt(((Character)value).charValue());
            continue; /* Loop/switch isn't completed */
_L10:
            parcel.writeString((String)value);
            continue; /* Loop/switch isn't completed */
_L11:
            TextUtils.writeToParcel((CharSequence)value, parcel, i);
            continue; /* Loop/switch isn't completed */
_L12:
            if(value == null)
                j = 0;
            parcel.writeInt(j);
            if(value != null)
                ((Uri)value).writeToParcel(parcel, i);
            continue; /* Loop/switch isn't completed */
_L13:
            int k;
            if(value != null)
                k = ((flag) ? 1 : 0);
            else
                k = 0;
            parcel.writeInt(k);
            if(value != null)
                ((Bitmap)value).writeToParcel(parcel, i);
            continue; /* Loop/switch isn't completed */
_L14:
            parcel.writeBundle((Bundle)value);
            continue; /* Loop/switch isn't completed */
_L15:
            int l;
            if(value != null)
                l = ((flag1) ? 1 : 0);
            else
                l = 0;
            parcel.writeInt(l);
            if(value != null)
                ((Intent)value).writeToParcel(parcel, i);
            continue; /* Loop/switch isn't completed */
_L16:
            int i1;
            if(value != null)
                i1 = ((flag2) ? 1 : 0);
            else
                i1 = 0;
            parcel.writeInt(i1);
            if(value != null)
                ((ColorStateList)value).writeToParcel(parcel, i);
            continue; /* Loop/switch isn't completed */
_L17:
            int j1;
            if(value != null)
                j1 = ((flag3) ? 1 : 0);
            else
                j1 = 0;
            parcel.writeInt(j1);
            if(value != null)
                ((Icon)value).writeToParcel(parcel, i);
            if(true) goto _L1; else goto _L18
_L18:
        }

        static final int BITMAP = 12;
        static final int BOOLEAN = 1;
        static final int BUNDLE = 13;
        static final int BYTE = 2;
        static final int CHAR = 8;
        static final int CHAR_SEQUENCE = 10;
        static final int COLOR_STATE_LIST = 15;
        static final int DOUBLE = 7;
        static final int FLOAT = 6;
        static final int ICON = 16;
        static final int INT = 4;
        static final int INTENT = 14;
        static final int LONG = 5;
        static final int SHORT = 3;
        static final int STRING = 9;
        static final int URI = 11;
        String methodName;
        final RemoteViews this$0;
        int type;
        Object value;

        ReflectionAction(int i, String s, int j, Object obj)
        {
            this$0 = RemoteViews.this;
            super(null);
            viewId = i;
            methodName = s;
            type = j;
            value = obj;
        }

        ReflectionAction(Parcel parcel)
        {
            boolean flag;
            flag = false;
            this$0 = RemoteViews.this;
            super(null);
            viewId = parcel.readInt();
            methodName = parcel.readString();
            type = parcel.readInt();
            type;
            JVM INSTR tableswitch 1 16: default 120
        //                       1 121
        //                       2 141
        //                       3 155
        //                       4 170
        //                       5 184
        //                       6 198
        //                       7 212
        //                       8 226
        //                       9 241
        //                       10 252
        //                       11 268
        //                       12 291
        //                       13 314
        //                       14 325
        //                       15 348
        //                       16 371;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17
_L1:
            return;
_L2:
            if(parcel.readInt() != 0)
                flag = true;
            value = Boolean.valueOf(flag);
            continue; /* Loop/switch isn't completed */
_L3:
            value = Byte.valueOf(parcel.readByte());
            continue; /* Loop/switch isn't completed */
_L4:
            value = Short.valueOf((short)parcel.readInt());
            continue; /* Loop/switch isn't completed */
_L5:
            value = Integer.valueOf(parcel.readInt());
            continue; /* Loop/switch isn't completed */
_L6:
            value = Long.valueOf(parcel.readLong());
            continue; /* Loop/switch isn't completed */
_L7:
            value = Float.valueOf(parcel.readFloat());
            continue; /* Loop/switch isn't completed */
_L8:
            value = Double.valueOf(parcel.readDouble());
            continue; /* Loop/switch isn't completed */
_L9:
            value = Character.valueOf((char)parcel.readInt());
            continue; /* Loop/switch isn't completed */
_L10:
            value = parcel.readString();
            continue; /* Loop/switch isn't completed */
_L11:
            value = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            continue; /* Loop/switch isn't completed */
_L12:
            if(parcel.readInt() != 0)
                value = Uri.CREATOR.createFromParcel(parcel);
            continue; /* Loop/switch isn't completed */
_L13:
            if(parcel.readInt() != 0)
                value = Bitmap.CREATOR.createFromParcel(parcel);
            continue; /* Loop/switch isn't completed */
_L14:
            value = parcel.readBundle();
            continue; /* Loop/switch isn't completed */
_L15:
            if(parcel.readInt() != 0)
                value = Intent.CREATOR.createFromParcel(parcel);
            continue; /* Loop/switch isn't completed */
_L16:
            if(parcel.readInt() != 0)
                value = ColorStateList.CREATOR.createFromParcel(parcel);
            continue; /* Loop/switch isn't completed */
_L17:
            if(parcel.readInt() != 0)
                value = Icon.CREATOR.createFromParcel(parcel);
            if(true) goto _L1; else goto _L18
_L18:
        }
    }

    private final class ReflectionActionWithoutParams extends Action
    {

        public void apply(View view, ViewGroup viewgroup, OnClickHandler onclickhandler)
        {
            view = view.findViewById(viewId);
            if(view == null)
                return;
            try
            {
                RemoteViews._2D_wrap5(RemoteViews.this, view, methodName, null).invoke(view, new Object[0]);
                return;
            }
            // Misplaced declaration of an exception variable
            catch(View view)
            {
                throw view;
            }
            // Misplaced declaration of an exception variable
            catch(View view)
            {
                throw new ActionException(view);
            }
        }

        public String getActionName()
        {
            return "ReflectionActionWithoutParams";
        }

        public int mergeBehavior()
        {
            return !methodName.equals("showNext") && !methodName.equals("showPrevious") ? 0 : 2;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(5);
            parcel.writeInt(viewId);
            parcel.writeString(methodName);
        }

        final String methodName;
        final RemoteViews this$0;

        ReflectionActionWithoutParams(int i, String s)
        {
            this$0 = RemoteViews.this;
            super(null);
            viewId = i;
            methodName = s;
        }

        ReflectionActionWithoutParams(Parcel parcel)
        {
            this$0 = RemoteViews.this;
            super(null);
            viewId = parcel.readInt();
            methodName = parcel.readString();
        }
    }

    public static interface RemoteView
        extends Annotation
    {
    }

    private static class RemoteViewsContextWrapper extends ContextWrapper
    {

        public String getPackageName()
        {
            return mContextForResources.getPackageName();
        }

        public Resources getResources()
        {
            return mContextForResources.getResources();
        }

        public android.content.res.Resources.Theme getTheme()
        {
            return mContextForResources.getTheme();
        }

        private final Context mContextForResources;

        RemoteViewsContextWrapper(Context context, Context context1)
        {
            super(context);
            mContextForResources = context1;
        }
    }

    private static final class RunnableAction extends RuntimeAction
    {

        public void apply(View view, ViewGroup viewgroup, OnClickHandler onclickhandler)
        {
            mRunnable.run();
        }

        private final Runnable mRunnable;

        RunnableAction(Runnable runnable)
        {
            super(null);
            mRunnable = runnable;
        }
    }

    private static abstract class RuntimeAction extends Action
    {

        public final String getActionName()
        {
            return "RuntimeAction";
        }

        public final void writeToParcel(Parcel parcel, int i)
        {
            throw new UnsupportedOperationException();
        }

        private RuntimeAction()
        {
            super(null);
        }

        RuntimeAction(RuntimeAction runtimeaction)
        {
            this();
        }
    }

    private class SetDrawableParameters extends Action
    {

        public void apply(View view, ViewGroup viewgroup, OnClickHandler onclickhandler)
        {
            viewgroup = view.findViewById(viewId);
            if(viewgroup == null)
                return;
            view = null;
            if(!targetBackground) goto _L2; else goto _L1
_L1:
            view = viewgroup.getBackground();
_L4:
            if(view != null)
            {
                if(alpha != -1)
                    view.mutate().setAlpha(alpha);
                if(filterMode != null)
                    view.mutate().setColorFilter(colorFilter, filterMode);
                if(level != -1)
                    view.mutate().setLevel(level);
            }
            return;
_L2:
            if(viewgroup instanceof ImageView)
                view = ((ImageView)viewgroup).getDrawable();
            if(true) goto _L4; else goto _L3
_L3:
        }

        public String getActionName()
        {
            return "SetDrawableParameters";
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(3);
            parcel.writeInt(viewId);
            if(targetBackground)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeInt(alpha);
            parcel.writeInt(colorFilter);
            if(filterMode != null)
            {
                parcel.writeInt(1);
                parcel.writeString(filterMode.toString());
            } else
            {
                parcel.writeInt(0);
            }
            parcel.writeInt(level);
        }

        int alpha;
        int colorFilter;
        android.graphics.PorterDuff.Mode filterMode;
        int level;
        boolean targetBackground;
        final RemoteViews this$0;

        public SetDrawableParameters(int i, boolean flag, int j, int k, android.graphics.PorterDuff.Mode mode, int l)
        {
            this$0 = RemoteViews.this;
            super(null);
            viewId = i;
            targetBackground = flag;
            alpha = j;
            colorFilter = k;
            filterMode = mode;
            level = l;
        }

        public SetDrawableParameters(Parcel parcel)
        {
            boolean flag = false;
            this$0 = RemoteViews.this;
            super(null);
            viewId = parcel.readInt();
            if(parcel.readInt() != 0)
                flag = true;
            targetBackground = flag;
            alpha = parcel.readInt();
            colorFilter = parcel.readInt();
            boolean flag1;
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            if(flag1)
                filterMode = android.graphics.PorterDuff.Mode.valueOf(parcel.readString());
            else
                filterMode = null;
            level = parcel.readInt();
        }
    }

    private class SetEmptyView extends Action
    {

        public void apply(View view, ViewGroup viewgroup, OnClickHandler onclickhandler)
        {
            viewgroup = view.findViewById(viewId);
            if(!(viewgroup instanceof AdapterView))
                return;
            viewgroup = (AdapterView)viewgroup;
            view = view.findViewById(emptyViewId);
            if(view == null)
            {
                return;
            } else
            {
                viewgroup.setEmptyView(view);
                return;
            }
        }

        public String getActionName()
        {
            return "SetEmptyView";
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(6);
            parcel.writeInt(viewId);
            parcel.writeInt(emptyViewId);
        }

        int emptyViewId;
        final RemoteViews this$0;
        int viewId;

        SetEmptyView(int i, int j)
        {
            this$0 = RemoteViews.this;
            super(null);
            viewId = i;
            emptyViewId = j;
        }

        SetEmptyView(Parcel parcel)
        {
            this$0 = RemoteViews.this;
            super(null);
            viewId = parcel.readInt();
            emptyViewId = parcel.readInt();
        }
    }

    private class SetOnClickFillInIntent extends Action
    {

        public void apply(View view, ViewGroup viewgroup, OnClickHandler onclickhandler)
        {
            viewgroup = view.findViewById(viewId);
            if(viewgroup == null)
                return;
            if(!RemoteViews._2D_get5(RemoteViews.this))
            {
                Log.e("RemoteViews", "The method setOnClickFillInIntent is available only from RemoteViewsFactory (ie. on collection items).");
                return;
            }
            if(viewgroup != view) goto _L2; else goto _L1
_L1:
            viewgroup.setTagInternal(0x1020247, fillInIntent);
_L4:
            return;
_L2:
            if(fillInIntent != null)
                viewgroup.setOnClickListener(onclickhandler. new android.view.View.OnClickListener() {

                    public void onClick(View view)
                    {
                        Object obj;
                        for(obj = (View)view.getParent(); obj != null && (obj instanceof AdapterView) ^ true && (!(obj instanceof AppWidgetHostView) || (obj instanceof RemoteViewsAdapter.RemoteViewsFrameLayout)); obj = (View)((View) (obj)).getParent());
                        if(!(obj instanceof AdapterView))
                        {
                            Log.e("RemoteViews", "Collection item doesn't have AdapterView parent");
                            return;
                        }
                        if(!(((View) (obj)).getTag() instanceof PendingIntent))
                        {
                            Log.e("RemoteViews", "Attempting setOnClickFillInIntent without calling setPendingIntentTemplate on parent.");
                            return;
                        } else
                        {
                            obj = (PendingIntent)((View) (obj)).getTag();
                            Rect rect = RemoteViews._2D_wrap0(view);
                            fillInIntent.setSourceBounds(rect);
                            handler.onClickHandler(view, ((PendingIntent) (obj)), fillInIntent);
                            return;
                        }
                    }

                    final SetOnClickFillInIntent this$1;
                    final OnClickHandler val$handler;

            
            {
                this$1 = final_setonclickfillinintent;
                handler = OnClickHandler.this;
                super();
            }
                }
);
            if(true) goto _L4; else goto _L3
_L3:
        }

        public String getActionName()
        {
            return "SetOnClickFillInIntent";
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(9);
            parcel.writeInt(viewId);
            fillInIntent.writeToParcel(parcel, 0);
        }

        Intent fillInIntent;
        final RemoteViews this$0;

        public SetOnClickFillInIntent(int i, Intent intent)
        {
            this$0 = RemoteViews.this;
            super(null);
            viewId = i;
            fillInIntent = intent;
        }

        public SetOnClickFillInIntent(Parcel parcel)
        {
            this$0 = RemoteViews.this;
            super(null);
            viewId = parcel.readInt();
            fillInIntent = (Intent)Intent.CREATOR.createFromParcel(parcel);
        }
    }

    private class SetOnClickPendingIntent extends Action
    {

        public void apply(View view, ViewGroup viewgroup, OnClickHandler onclickhandler)
        {
            viewgroup = view.findViewById(viewId);
            if(viewgroup == null)
                return;
            if(RemoteViews._2D_get5(RemoteViews.this))
            {
                Log.w("RemoteViews", (new StringBuilder()).append("Cannot setOnClickPendingIntent for collection item (id: ").append(viewId).append(")").toString());
                view = view.getContext().getApplicationInfo();
                if(view != null && ((ApplicationInfo) (view)).targetSdkVersion >= 16)
                    return;
            }
            view = null;
            if(pendingIntent != null)
                view = onclickhandler. new android.view.View.OnClickListener() {

                    public void onClick(View view)
                    {
                        Rect rect = RemoteViews._2D_wrap0(view);
                        Intent intent = new Intent();
                        intent.setSourceBounds(rect);
                        handler.onClickHandler(view, pendingIntent, intent);
                    }

                    final SetOnClickPendingIntent this$1;
                    final OnClickHandler val$handler;

            
            {
                this$1 = final_setonclickpendingintent;
                handler = OnClickHandler.this;
                super();
            }
                }
;
            viewgroup.setOnClickListener(view);
        }

        public String getActionName()
        {
            return "SetOnClickPendingIntent";
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            i = 1;
            parcel.writeInt(1);
            parcel.writeInt(viewId);
            if(pendingIntent == null)
                i = 0;
            parcel.writeInt(i);
            if(pendingIntent != null)
                pendingIntent.writeToParcel(parcel, 0);
        }

        PendingIntent pendingIntent;
        final RemoteViews this$0;

        public SetOnClickPendingIntent(int i, PendingIntent pendingintent)
        {
            this$0 = RemoteViews.this;
            super(null);
            viewId = i;
            pendingIntent = pendingintent;
        }

        public SetOnClickPendingIntent(Parcel parcel)
        {
            this$0 = RemoteViews.this;
            super(null);
            viewId = parcel.readInt();
            if(parcel.readInt() != 0)
                pendingIntent = PendingIntent.readPendingIntentOrNullFromParcel(parcel);
        }
    }

    private class SetPendingIntentTemplate extends Action
    {

        public void apply(View view, ViewGroup viewgroup, OnClickHandler onclickhandler)
        {
            view = view.findViewById(viewId);
            if(view == null)
                return;
            if(view instanceof AdapterView)
            {
                view = (AdapterView)view;
                view.setOnItemClickListener(onclickhandler. new AdapterView.OnItemClickListener() {

                    public void onItemClick(AdapterView adapterview, View view, int i, long l)
                    {
label0:
                        {
                            if(!(view instanceof ViewGroup))
                                break label0;
                            ViewGroup viewgroup = (ViewGroup)view;
                            Object obj = viewgroup;
                            if(adapterview instanceof AdapterViewAnimator)
                                obj = (ViewGroup)viewgroup.getChildAt(0);
                            if(obj == null)
                                return;
                            viewgroup = null;
                            int j = ((ViewGroup) (obj)).getChildCount();
                            i = 0;
label1:
                            do
                            {
label2:
                                {
                                    adapterview = viewgroup;
                                    if(i < j)
                                    {
                                        adapterview = ((AdapterView) (((ViewGroup) (obj)).getChildAt(i).getTag(0x1020247)));
                                        if(!(adapterview instanceof Intent))
                                            break label2;
                                        adapterview = (Intent)adapterview;
                                    }
                                    if(adapterview == null)
                                        return;
                                    break label1;
                                }
                                i++;
                            } while(true);
                            obj = RemoteViews._2D_wrap0(view);
                            (new Intent()).setSourceBounds(((Rect) (obj)));
                            handler.onClickHandler(view, pendingIntentTemplate, adapterview);
                        }
                    }

                    final SetPendingIntentTemplate this$1;
                    final OnClickHandler val$handler;

            
            {
                this$1 = final_setpendingintenttemplate;
                handler = OnClickHandler.this;
                super();
            }
                }
);
                view.setTag(pendingIntentTemplate);
                return;
            } else
            {
                Log.e("RemoteViews", (new StringBuilder()).append("Cannot setPendingIntentTemplate on a view which is notan AdapterView (id: ").append(viewId).append(")").toString());
                return;
            }
        }

        public String getActionName()
        {
            return "SetPendingIntentTemplate";
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(8);
            parcel.writeInt(viewId);
            pendingIntentTemplate.writeToParcel(parcel, 0);
        }

        PendingIntent pendingIntentTemplate;
        final RemoteViews this$0;

        public SetPendingIntentTemplate(int i, PendingIntent pendingintent)
        {
            this$0 = RemoteViews.this;
            super(null);
            viewId = i;
            pendingIntentTemplate = pendingintent;
        }

        public SetPendingIntentTemplate(Parcel parcel)
        {
            this$0 = RemoteViews.this;
            super(null);
            viewId = parcel.readInt();
            pendingIntentTemplate = PendingIntent.readPendingIntentOrNullFromParcel(parcel);
        }
    }

    private class SetRemoteInputsAction extends Action
    {

        public void apply(View view, ViewGroup viewgroup, OnClickHandler onclickhandler)
        {
            view = view.findViewById(viewId);
            if(view == null)
            {
                return;
            } else
            {
                view.setTagInternal(0x10203a2, remoteInputs);
                return;
            }
        }

        public String getActionName()
        {
            return "SetRemoteInputsAction";
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(18);
            parcel.writeInt(viewId);
            parcel.writeTypedArray(remoteInputs, i);
        }

        final Parcelable remoteInputs[];
        final RemoteViews this$0;

        public SetRemoteInputsAction(int i, RemoteInput aremoteinput[])
        {
            this$0 = RemoteViews.this;
            super(null);
            viewId = i;
            remoteInputs = aremoteinput;
        }

        public SetRemoteInputsAction(Parcel parcel)
        {
            this$0 = RemoteViews.this;
            super(null);
            viewId = parcel.readInt();
            remoteInputs = (Parcelable[])parcel.createTypedArray(RemoteInput.CREATOR);
        }
    }

    private class SetRemoteViewsAdapterIntent extends Action
    {

        public void apply(View view, ViewGroup viewgroup, OnClickHandler onclickhandler)
        {
            view = view.findViewById(viewId);
            if(view == null)
                return;
            if(!(viewgroup instanceof AppWidgetHostView))
            {
                Log.e("RemoteViews", (new StringBuilder()).append("SetRemoteViewsAdapterIntent action can only be used for AppWidgets (root id: ").append(viewId).append(")").toString());
                return;
            }
            if(!(view instanceof AbsListView) && (view instanceof AdapterViewAnimator) ^ true)
            {
                Log.e("RemoteViews", (new StringBuilder()).append("Cannot setRemoteViewsAdapter on a view which is not an AbsListView or AdapterViewAnimator (id: ").append(viewId).append(")").toString());
                return;
            }
            viewgroup = (AppWidgetHostView)viewgroup;
            intent.putExtra("remoteAdapterAppWidgetId", viewgroup.getAppWidgetId());
            if(!(view instanceof AbsListView)) goto _L2; else goto _L1
_L1:
            view = (AbsListView)view;
            view.setRemoteViewsAdapter(intent, isAsync);
            view.setRemoteViewsOnClickHandler(onclickhandler);
_L4:
            return;
_L2:
            if(view instanceof AdapterViewAnimator)
            {
                view = (AdapterViewAnimator)view;
                view.setRemoteViewsAdapter(intent, isAsync);
                view.setRemoteViewsOnClickHandler(onclickhandler);
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        public String getActionName()
        {
            return "SetRemoteViewsAdapterIntent";
        }

        public Action initActionAsync(ViewTree viewtree, ViewGroup viewgroup, OnClickHandler onclickhandler)
        {
            viewtree = new SetRemoteViewsAdapterIntent(viewId, intent);
            viewtree.isAsync = true;
            return viewtree;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(10);
            parcel.writeInt(viewId);
            intent.writeToParcel(parcel, i);
        }

        Intent intent;
        boolean isAsync;
        final RemoteViews this$0;

        public SetRemoteViewsAdapterIntent(int i, Intent intent1)
        {
            this$0 = RemoteViews.this;
            super(null);
            isAsync = false;
            viewId = i;
            intent = intent1;
        }

        public SetRemoteViewsAdapterIntent(Parcel parcel)
        {
            this$0 = RemoteViews.this;
            super(null);
            isAsync = false;
            viewId = parcel.readInt();
            intent = (Intent)Intent.CREATOR.createFromParcel(parcel);
        }
    }

    private class SetRemoteViewsAdapterList extends Action
    {

        public void apply(View view, ViewGroup viewgroup, OnClickHandler onclickhandler)
        {
            view = view.findViewById(viewId);
            if(view == null)
                return;
            if(!(viewgroup instanceof AppWidgetHostView))
            {
                Log.e("RemoteViews", (new StringBuilder()).append("SetRemoteViewsAdapterIntent action can only be used for AppWidgets (root id: ").append(viewId).append(")").toString());
                return;
            }
            if(!(view instanceof AbsListView) && (view instanceof AdapterViewAnimator) ^ true)
            {
                Log.e("RemoteViews", (new StringBuilder()).append("Cannot setRemoteViewsAdapter on a view which is not an AbsListView or AdapterViewAnimator (id: ").append(viewId).append(")").toString());
                return;
            }
            if(!(view instanceof AbsListView)) goto _L2; else goto _L1
_L1:
            view = (AbsListView)view;
            viewgroup = view.getAdapter();
            if((viewgroup instanceof RemoteViewsListAdapter) && viewTypeCount <= viewgroup.getViewTypeCount())
                ((RemoteViewsListAdapter)viewgroup).setViewsList(list);
            else
                view.setAdapter(new RemoteViewsListAdapter(view.getContext(), list, viewTypeCount));
_L4:
            return;
_L2:
            if(view instanceof AdapterViewAnimator)
            {
                view = (AdapterViewAnimator)view;
                viewgroup = view.getAdapter();
                if((viewgroup instanceof RemoteViewsListAdapter) && viewTypeCount <= viewgroup.getViewTypeCount())
                    ((RemoteViewsListAdapter)viewgroup).setViewsList(list);
                else
                    view.setAdapter(new RemoteViewsListAdapter(view.getContext(), list, viewTypeCount));
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        public String getActionName()
        {
            return "SetRemoteViewsAdapterList";
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(15);
            parcel.writeInt(viewId);
            parcel.writeInt(viewTypeCount);
            if(list == null || list.size() == 0)
            {
                parcel.writeInt(0);
            } else
            {
                int j = list.size();
                parcel.writeInt(j);
                int k = 0;
                while(k < j) 
                {
                    ((RemoteViews)list.get(k)).writeToParcel(parcel, i);
                    k++;
                }
            }
        }

        ArrayList list;
        final RemoteViews this$0;
        int viewTypeCount;

        public SetRemoteViewsAdapterList(int i, ArrayList arraylist, int j)
        {
            this$0 = RemoteViews.this;
            super(null);
            viewId = i;
            list = arraylist;
            viewTypeCount = j;
        }

        public SetRemoteViewsAdapterList(Parcel parcel)
        {
            this$0 = RemoteViews.this;
            super(null);
            viewId = parcel.readInt();
            viewTypeCount = parcel.readInt();
            int i = parcel.readInt();
            list = new ArrayList();
            for(int j = 0; j < i; j++)
            {
                remoteviews = (RemoteViews)RemoteViews.CREATOR.createFromParcel(parcel);
                list.add(RemoteViews.this);
            }

        }
    }

    private class TextViewDrawableAction extends Action
    {

        public void apply(View view, ViewGroup viewgroup, OnClickHandler onclickhandler)
        {
            TextView textview = (TextView)view.findViewById(viewId);
            if(textview == null)
                return;
            if(drawablesLoaded)
            {
                if(isRelative)
                    textview.setCompoundDrawablesRelativeWithIntrinsicBounds(id1, id2, id3, id4);
                else
                    textview.setCompoundDrawablesWithIntrinsicBounds(id1, id2, id3, id4);
            } else
            if(useIcons)
            {
                Object obj = textview.getContext();
                if(i1 == null)
                    view = null;
                else
                    view = i1.loadDrawable(((Context) (obj)));
                if(i2 == null)
                    viewgroup = null;
                else
                    viewgroup = i2.loadDrawable(((Context) (obj)));
                if(i3 == null)
                    onclickhandler = null;
                else
                    onclickhandler = i3.loadDrawable(((Context) (obj)));
                if(i4 == null)
                    obj = null;
                else
                    obj = i4.loadDrawable(((Context) (obj)));
                if(isRelative)
                    textview.setCompoundDrawablesRelativeWithIntrinsicBounds(view, viewgroup, onclickhandler, ((Drawable) (obj)));
                else
                    textview.setCompoundDrawablesWithIntrinsicBounds(view, viewgroup, onclickhandler, ((Drawable) (obj)));
            } else
            if(isRelative)
                textview.setCompoundDrawablesRelativeWithIntrinsicBounds(d1, d2, d3, d4);
            else
                textview.setCompoundDrawablesWithIntrinsicBounds(d1, d2, d3, d4);
        }

        public String getActionName()
        {
            return "TextViewDrawableAction";
        }

        public Action initActionAsync(ViewTree viewtree, ViewGroup viewgroup, OnClickHandler onclickhandler)
        {
            Object obj = null;
            onclickhandler = null;
            viewgroup = (TextView)viewtree.findViewById(viewId);
            if(viewgroup == null)
                return RemoteViews._2D_get0();
            Context context;
            if(useIcons)
                viewtree = new TextViewDrawableAction(viewId, isRelative, i1, i2, i3, i4);
            else
                viewtree = new TextViewDrawableAction(viewId, isRelative, d1, d2, d3, d4);
            viewtree.drawablesLoaded = true;
            context = viewgroup.getContext();
            if(useIcons)
            {
                if(i1 == null)
                    viewgroup = null;
                else
                    viewgroup = i1.loadDrawable(context);
                viewtree.id1 = viewgroup;
                if(i2 == null)
                    viewgroup = null;
                else
                    viewgroup = i2.loadDrawable(context);
                viewtree.id2 = viewgroup;
                if(i3 == null)
                    viewgroup = null;
                else
                    viewgroup = i3.loadDrawable(context);
                viewtree.id3 = viewgroup;
                if(i4 == null)
                    viewgroup = onclickhandler;
                else
                    viewgroup = i4.loadDrawable(context);
                viewtree.id4 = viewgroup;
            } else
            {
                if(d1 == 0)
                    viewgroup = null;
                else
                    viewgroup = context.getDrawable(d1);
                viewtree.id1 = viewgroup;
                if(d2 == 0)
                    viewgroup = null;
                else
                    viewgroup = context.getDrawable(d2);
                viewtree.id2 = viewgroup;
                if(d3 == 0)
                    viewgroup = null;
                else
                    viewgroup = context.getDrawable(d3);
                viewtree.id3 = viewgroup;
                if(d4 == 0)
                    viewgroup = obj;
                else
                    viewgroup = context.getDrawable(d4);
                viewtree.id4 = viewgroup;
            }
            return viewtree;
        }

        public boolean prefersAsyncApply()
        {
            return useIcons;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(11);
            parcel.writeInt(viewId);
            if(isRelative)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(useIcons)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(useIcons)
            {
                if(i1 != null)
                {
                    parcel.writeInt(1);
                    i1.writeToParcel(parcel, 0);
                } else
                {
                    parcel.writeInt(0);
                }
                if(i2 != null)
                {
                    parcel.writeInt(1);
                    i2.writeToParcel(parcel, 0);
                } else
                {
                    parcel.writeInt(0);
                }
                if(i3 != null)
                {
                    parcel.writeInt(1);
                    i3.writeToParcel(parcel, 0);
                } else
                {
                    parcel.writeInt(0);
                }
                if(i4 != null)
                {
                    parcel.writeInt(1);
                    i4.writeToParcel(parcel, 0);
                } else
                {
                    parcel.writeInt(0);
                }
            } else
            {
                parcel.writeInt(d1);
                parcel.writeInt(d2);
                parcel.writeInt(d3);
                parcel.writeInt(d4);
            }
        }

        int d1;
        int d2;
        int d3;
        int d4;
        boolean drawablesLoaded;
        Icon i1;
        Icon i2;
        Icon i3;
        Icon i4;
        Drawable id1;
        Drawable id2;
        Drawable id3;
        Drawable id4;
        boolean isRelative;
        final RemoteViews this$0;
        boolean useIcons;

        public TextViewDrawableAction(int i, boolean flag, int j, int k, int l, int j1)
        {
            this$0 = RemoteViews.this;
            super(null);
            isRelative = false;
            useIcons = false;
            drawablesLoaded = false;
            viewId = i;
            isRelative = flag;
            useIcons = false;
            d1 = j;
            d2 = k;
            d3 = l;
            d4 = j1;
        }

        public TextViewDrawableAction(int i, boolean flag, Icon icon, Icon icon1, Icon icon2, Icon icon3)
        {
            this$0 = RemoteViews.this;
            super(null);
            isRelative = false;
            useIcons = false;
            drawablesLoaded = false;
            viewId = i;
            isRelative = flag;
            useIcons = true;
            i1 = icon;
            i2 = icon1;
            i3 = icon2;
            i4 = icon3;
        }

        public TextViewDrawableAction(Parcel parcel)
        {
            boolean flag = true;
            this$0 = RemoteViews.this;
            super(null);
            isRelative = false;
            useIcons = false;
            drawablesLoaded = false;
            viewId = parcel.readInt();
            boolean flag1;
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            isRelative = flag1;
            if(parcel.readInt() != 0)
                flag1 = flag;
            else
                flag1 = false;
            useIcons = flag1;
            if(useIcons)
            {
                if(parcel.readInt() != 0)
                    i1 = (Icon)Icon.CREATOR.createFromParcel(parcel);
                if(parcel.readInt() != 0)
                    i2 = (Icon)Icon.CREATOR.createFromParcel(parcel);
                if(parcel.readInt() != 0)
                    i3 = (Icon)Icon.CREATOR.createFromParcel(parcel);
                if(parcel.readInt() != 0)
                    i4 = (Icon)Icon.CREATOR.createFromParcel(parcel);
            } else
            {
                d1 = parcel.readInt();
                d2 = parcel.readInt();
                d3 = parcel.readInt();
                d4 = parcel.readInt();
            }
        }
    }

    private class TextViewDrawableColorFilterAction extends Action
    {

        private android.graphics.PorterDuff.Mode readPorterDuffMode(Parcel parcel)
        {
            int i = parcel.readInt();
            if(i >= 0 && i < android.graphics.PorterDuff.Mode.values().length)
                return android.graphics.PorterDuff.Mode.values()[i];
            else
                return android.graphics.PorterDuff.Mode.CLEAR;
        }

        public void apply(View view, ViewGroup viewgroup, OnClickHandler onclickhandler)
        {
            view = (TextView)view.findViewById(viewId);
            if(view == null)
                return;
            if(isRelative)
                view = view.getCompoundDrawablesRelative();
            else
                view = view.getCompoundDrawables();
            if(index < 0 || index >= 4)
                throw new IllegalStateException("index must be in range [0, 3].");
            view = view[index];
            if(view != null)
            {
                view.mutate();
                view.setColorFilter(color, mode);
            }
        }

        public String getActionName()
        {
            return "TextViewDrawableColorFilterAction";
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(17);
            parcel.writeInt(viewId);
            if(isRelative)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeInt(index);
            parcel.writeInt(color);
            parcel.writeInt(mode.ordinal());
        }

        final int color;
        final int index;
        final boolean isRelative;
        final android.graphics.PorterDuff.Mode mode;
        final RemoteViews this$0;

        public TextViewDrawableColorFilterAction(int i, boolean flag, int j, int k, android.graphics.PorterDuff.Mode mode1)
        {
            this$0 = RemoteViews.this;
            super(null);
            viewId = i;
            isRelative = flag;
            index = j;
            color = k;
            mode = mode1;
        }

        public TextViewDrawableColorFilterAction(Parcel parcel)
        {
            boolean flag = false;
            this$0 = RemoteViews.this;
            super(null);
            viewId = parcel.readInt();
            if(parcel.readInt() != 0)
                flag = true;
            isRelative = flag;
            index = parcel.readInt();
            color = parcel.readInt();
            mode = readPorterDuffMode(parcel);
        }
    }

    private class TextViewSizeAction extends Action
    {

        public void apply(View view, ViewGroup viewgroup, OnClickHandler onclickhandler)
        {
            view = (TextView)view.findViewById(viewId);
            if(view == null)
            {
                return;
            } else
            {
                view.setTextSize(units, size);
                return;
            }
        }

        public String getActionName()
        {
            return "TextViewSizeAction";
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(13);
            parcel.writeInt(viewId);
            parcel.writeInt(units);
            parcel.writeFloat(size);
        }

        float size;
        final RemoteViews this$0;
        int units;

        public TextViewSizeAction(int i, int j, float f)
        {
            this$0 = RemoteViews.this;
            super(null);
            viewId = i;
            units = j;
            size = f;
        }

        public TextViewSizeAction(Parcel parcel)
        {
            this$0 = RemoteViews.this;
            super(null);
            viewId = parcel.readInt();
            units = parcel.readInt();
            size = parcel.readFloat();
        }
    }

    private class ViewGroupActionAdd extends Action
    {

        static int _2D_get0(ViewGroupActionAdd viewgroupactionadd)
        {
            return viewgroupactionadd.mIndex;
        }

        public void apply(View view, ViewGroup viewgroup, OnClickHandler onclickhandler)
        {
            viewgroup = view.getContext();
            view = (ViewGroup)view.findViewById(viewId);
            if(view == null)
            {
                return;
            } else
            {
                view.addView(mNestedViews.apply(viewgroup, view, onclickhandler), mIndex);
                return;
            }
        }

        public String getActionName()
        {
            return "ViewGroupActionAdd";
        }

        public boolean hasSameAppInfo(ApplicationInfo applicationinfo)
        {
            boolean flag = false;
            boolean flag1 = flag;
            if(RemoteViews._2D_get3(mNestedViews).packageName.equals(applicationinfo.packageName))
            {
                flag1 = flag;
                if(RemoteViews._2D_get3(mNestedViews).uid == applicationinfo.uid)
                    flag1 = true;
            }
            return flag1;
        }

        public Action initActionAsync(final ViewTree task, ViewGroup viewgroup, final OnClickHandler tree)
        {
            task.createTree();
            ViewTree viewtree = task.findViewTreeById(viewId);
            if(viewtree == null || (ViewTree._2D_get0(viewtree) instanceof ViewGroup) ^ true)
                return RemoteViews._2D_get0();
            viewgroup = (ViewGroup)ViewTree._2D_get0(viewtree);
            task = ViewTree._2D_get0(task).getContext();
            task = RemoteViews._2D_wrap2(mNestedViews, task, viewgroup, null, tree);
            tree = task.doInBackground(new Void[0]);
            if(tree == null)
            {
                throw new ActionException(AsyncApplyTask._2D_get0(task));
            } else
            {
                viewtree.addChild(tree, mIndex);
                return viewgroup. new RuntimeAction() {

                    public void apply(View view, ViewGroup viewgroup, OnClickHandler onclickhandler)
                        throws ActionException
                    {
                        task.onPostExecute(tree);
                        targetVg.addView(AsyncApplyTask._2D_get1(task), ViewGroupActionAdd._2D_get0(ViewGroupActionAdd.this));
                    }

                    final ViewGroupActionAdd this$1;
                    final ViewGroup val$targetVg;
                    final AsyncApplyTask val$task;
                    final ViewTree val$tree;

            
            {
                this$1 = final_viewgroupactionadd;
                task = asyncapplytask;
                tree = viewtree;
                targetVg = ViewGroup.this;
                super(null);
            }
                }
;
            }
        }

        public int mergeBehavior()
        {
            return 1;
        }

        public boolean prefersAsyncApply()
        {
            return mNestedViews.prefersAsyncApply();
        }

        public void setBitmapCache(BitmapCache bitmapcache)
        {
            RemoteViews._2D_wrap8(mNestedViews, bitmapcache);
        }

        public void updateMemoryUsageEstimate(MemoryUsageCounter memoryusagecounter)
        {
            memoryusagecounter.increment(mNestedViews.estimateMemoryUsage());
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(4);
            parcel.writeInt(viewId);
            parcel.writeInt(mIndex);
            mNestedViews.writeToParcel(parcel, i);
        }

        private int mIndex;
        private RemoteViews mNestedViews;
        final RemoteViews this$0;

        ViewGroupActionAdd(int i, RemoteViews remoteviews1)
        {
            this(i, remoteviews1, -1);
        }

        ViewGroupActionAdd(int i, RemoteViews remoteviews1, int j)
        {
            this$0 = RemoteViews.this;
            super(null);
            viewId = i;
            mNestedViews = remoteviews1;
            mIndex = j;
            if(remoteviews1 != null)
                RemoteViews._2D_wrap6(RemoteViews.this, remoteviews1);
        }

        ViewGroupActionAdd(Parcel parcel, BitmapCache bitmapcache, ApplicationInfo applicationinfo, int i)
        {
            this$0 = RemoteViews.this;
            super(null);
            viewId = parcel.readInt();
            mIndex = parcel.readInt();
            mNestedViews = new RemoteViews(parcel, bitmapcache, applicationinfo, i, null);
        }
    }

    private class ViewGroupActionRemove extends Action
    {

        static int _2D_get0(ViewGroupActionRemove viewgroupactionremove)
        {
            return viewgroupactionremove.mViewIdToKeep;
        }

        static void _2D_wrap0(ViewGroupActionRemove viewgroupactionremove, ViewGroup viewgroup)
        {
            viewgroupactionremove.removeAllViewsExceptIdToKeep(viewgroup);
        }

        private void removeAllViewsExceptIdToKeep(ViewGroup viewgroup)
        {
            for(int i = viewgroup.getChildCount() - 1; i >= 0; i--)
                if(viewgroup.getChildAt(i).getId() != mViewIdToKeep)
                    viewgroup.removeViewAt(i);

        }

        public void apply(View view, ViewGroup viewgroup, OnClickHandler onclickhandler)
        {
            view = (ViewGroup)view.findViewById(viewId);
            if(view == null)
                return;
            if(mViewIdToKeep == -2)
            {
                view.removeAllViews();
                return;
            } else
            {
                removeAllViewsExceptIdToKeep(view);
                return;
            }
        }

        public String getActionName()
        {
            return "ViewGroupActionRemove";
        }

        public Action initActionAsync(ViewTree viewtree, ViewGroup viewgroup, OnClickHandler onclickhandler)
        {
            viewtree.createTree();
            viewtree = viewtree.findViewTreeById(viewId);
            if(viewtree == null || (ViewTree._2D_get0(viewtree) instanceof ViewGroup) ^ true)
            {
                return RemoteViews._2D_get0();
            } else
            {
                viewgroup = (ViewGroup)ViewTree._2D_get0(viewtree);
                ViewTree._2D_set0(viewtree, null);
                return viewgroup. new RuntimeAction() {

                    public void apply(View view, ViewGroup viewgroup, OnClickHandler onclickhandler)
                        throws ActionException
                    {
                        if(ViewGroupActionRemove._2D_get0(ViewGroupActionRemove.this) == -2)
                        {
                            targetVg.removeAllViews();
                            return;
                        } else
                        {
                            ViewGroupActionRemove._2D_wrap0(ViewGroupActionRemove.this, targetVg);
                            return;
                        }
                    }

                    final ViewGroupActionRemove this$1;
                    final ViewGroup val$targetVg;

            
            {
                this$1 = final_viewgroupactionremove;
                targetVg = ViewGroup.this;
                super(null);
            }
                }
;
            }
        }

        public int mergeBehavior()
        {
            return 1;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(7);
            parcel.writeInt(viewId);
            parcel.writeInt(mViewIdToKeep);
        }

        private static final int REMOVE_ALL_VIEWS_ID = -2;
        private int mViewIdToKeep;
        final RemoteViews this$0;

        ViewGroupActionRemove(int i)
        {
            this(i, -2);
        }

        ViewGroupActionRemove(int i, int j)
        {
            this$0 = RemoteViews.this;
            super(null);
            viewId = i;
            mViewIdToKeep = j;
        }

        ViewGroupActionRemove(Parcel parcel)
        {
            this$0 = RemoteViews.this;
            super(null);
            viewId = parcel.readInt();
            mViewIdToKeep = parcel.readInt();
        }
    }

    private class ViewPaddingAction extends Action
    {

        public void apply(View view, ViewGroup viewgroup, OnClickHandler onclickhandler)
        {
            view = view.findViewById(viewId);
            if(view == null)
            {
                return;
            } else
            {
                view.setPadding(left, top, right, bottom);
                return;
            }
        }

        public String getActionName()
        {
            return "ViewPaddingAction";
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(14);
            parcel.writeInt(viewId);
            parcel.writeInt(left);
            parcel.writeInt(top);
            parcel.writeInt(right);
            parcel.writeInt(bottom);
        }

        int bottom;
        int left;
        int right;
        final RemoteViews this$0;
        int top;

        public ViewPaddingAction(int i, int j, int k, int l, int i1)
        {
            this$0 = RemoteViews.this;
            super(null);
            viewId = i;
            left = j;
            top = k;
            right = l;
            bottom = i1;
        }

        public ViewPaddingAction(Parcel parcel)
        {
            this$0 = RemoteViews.this;
            super(null);
            viewId = parcel.readInt();
            left = parcel.readInt();
            top = parcel.readInt();
            right = parcel.readInt();
            bottom = parcel.readInt();
        }
    }

    private static class ViewTree
    {

        static View _2D_get0(ViewTree viewtree)
        {
            return viewtree.mRoot;
        }

        static ArrayList _2D_set0(ViewTree viewtree, ArrayList arraylist)
        {
            viewtree.mChildren = arraylist;
            return arraylist;
        }

        private void addViewChild(View view)
        {
            if(view.isRootNamespace())
                return;
            ViewTree viewtree;
            if(view.getId() != 0)
            {
                viewtree = new ViewTree(view);
                mChildren.add(viewtree);
            } else
            {
                viewtree = this;
            }
            if((view instanceof ViewGroup) && viewtree.mChildren == null)
            {
                viewtree.mChildren = new ArrayList();
                view = (ViewGroup)view;
                int i = view.getChildCount();
                for(int j = 0; j < i; j++)
                    viewtree.addViewChild(view.getChildAt(j));

            }
        }

        public void addChild(ViewTree viewtree)
        {
            addChild(viewtree, -1);
        }

        public void addChild(ViewTree viewtree, int i)
        {
            if(mChildren == null)
                mChildren = new ArrayList();
            viewtree.createTree();
            if(i == -1)
            {
                mChildren.add(viewtree);
                return;
            } else
            {
                mChildren.add(i, viewtree);
                return;
            }
        }

        public void createTree()
        {
            if(mChildren != null)
                return;
            mChildren = new ArrayList();
            if(mRoot instanceof ViewGroup)
            {
                ViewGroup viewgroup = (ViewGroup)mRoot;
                int i = viewgroup.getChildCount();
                for(int j = 0; j < i; j++)
                    addViewChild(viewgroup.getChildAt(j));

            }
        }

        public View findViewById(int i)
        {
            View view = null;
            if(mChildren == null)
                return mRoot.findViewById(i);
            ViewTree viewtree = findViewTreeById(i);
            if(viewtree != null)
                view = viewtree.mRoot;
            return view;
        }

        public ViewTree findViewTreeById(int i)
        {
            if(mRoot.getId() == i)
                return this;
            if(mChildren == null)
                return null;
            for(Iterator iterator = mChildren.iterator(); iterator.hasNext();)
            {
                ViewTree viewtree = ((ViewTree)iterator.next()).findViewTreeById(i);
                if(viewtree != null)
                    return viewtree;
            }

            return null;
        }

        public void replaceView(View view)
        {
            mRoot = view;
            mChildren = null;
            createTree();
        }

        private static final int INSERT_AT_END_INDEX = -1;
        private ArrayList mChildren;
        private View mRoot;

        private ViewTree(View view)
        {
            mRoot = view;
        }

        ViewTree(View view, ViewTree viewtree)
        {
            this(view);
        }
    }


    static Action _2D_get0()
    {
        return ACTION_NOOP;
    }

    static OnClickHandler _2D_get1()
    {
        return DEFAULT_ON_CLICK_HANDLER;
    }

    static ArrayList _2D_get2(RemoteViews remoteviews)
    {
        return remoteviews.mActions;
    }

    static ApplicationInfo _2D_get3(RemoteViews remoteviews)
    {
        return remoteviews.mApplication;
    }

    static BitmapCache _2D_get4(RemoteViews remoteviews)
    {
        return remoteviews.mBitmapCache;
    }

    static boolean _2D_get5(RemoteViews remoteviews)
    {
        return remoteviews.mIsWidgetCollectionChild;
    }

    static Rect _2D_wrap0(View view)
    {
        return getSourceBounds(view);
    }

    static View _2D_wrap1(RemoteViews remoteviews, Context context, RemoteViews remoteviews1, ViewGroup viewgroup)
    {
        return remoteviews.inflateView(context, remoteviews1, viewgroup);
    }

    static AsyncApplyTask _2D_wrap2(RemoteViews remoteviews, Context context, ViewGroup viewgroup, OnViewAppliedListener onviewappliedlistener, OnClickHandler onclickhandler)
    {
        return remoteviews.getAsyncApplyTask(context, viewgroup, onviewappliedlistener, onclickhandler);
    }

    static Object[] _2D_wrap3(Object obj)
    {
        return wrapArg(obj);
    }

    static Method _2D_wrap4(RemoteViews remoteviews, Method method)
    {
        return remoteviews.getAsyncMethod(method);
    }

    static Method _2D_wrap5(RemoteViews remoteviews, View view, String s, Class class1)
    {
        return remoteviews.getMethod(view, s, class1);
    }

    static void _2D_wrap6(RemoteViews remoteviews, RemoteViews remoteviews1)
    {
        remoteviews.configureRemoteViewsAsChild(remoteviews1);
    }

    static void _2D_wrap7(Context context, OnClickHandler onclickhandler)
    {
        loadTransitionOverride(context, onclickhandler);
    }

    static void _2D_wrap8(RemoteViews remoteviews, BitmapCache bitmapcache)
    {
        remoteviews.setBitmapCache(bitmapcache);
    }

    protected RemoteViews(ApplicationInfo applicationinfo, int i)
    {
        mIsRoot = true;
        mLandscape = null;
        mPortrait = null;
        mIsWidgetCollectionChild = false;
        mPair = new MutablePair(null, null);
        mApplication = applicationinfo;
        mLayoutId = i;
        mBitmapCache = new BitmapCache();
        mMemoryUsageCounter = new MemoryUsageCounter(null);
        recalculateMemoryUsage();
    }

    public RemoteViews(Parcel parcel)
    {
        this(parcel, null, null, 0);
    }

    private RemoteViews(Parcel parcel, BitmapCache bitmapcache, ApplicationInfo applicationinfo, int i)
    {
        int j;
        mIsRoot = true;
        mLandscape = null;
        mPortrait = null;
        mIsWidgetCollectionChild = false;
        mPair = new MutablePair(null, null);
        if(i > 10 && UserHandle.getAppId(Binder.getCallingUid()) != 1000)
            throw new IllegalArgumentException("Too many nested views.");
        j = i + 1;
        i = parcel.readInt();
        boolean flag;
        int k;
        int l;
        if(bitmapcache == null)
        {
            mBitmapCache = new BitmapCache(parcel);
        } else
        {
            setBitmapCache(bitmapcache);
            setNotRoot();
        }
        if(i != 0)
            break MISSING_BLOCK_LABEL_733;
        if(parcel.readInt() != 0)
            applicationinfo = (ApplicationInfo)ApplicationInfo.CREATOR.createFromParcel(parcel);
        mApplication = applicationinfo;
        mLayoutId = parcel.readInt();
        if(parcel.readInt() == 1)
            flag = true;
        else
            flag = false;
        mIsWidgetCollectionChild = flag;
        k = parcel.readInt();
        if(k <= 0)
            break MISSING_BLOCK_LABEL_799;
        mActions = new ArrayList(k);
        i = 0;
_L22:
        if(i >= k)
            break MISSING_BLOCK_LABEL_799;
        l = parcel.readInt();
        l;
        JVM INSTR tableswitch 1 20: default 272
    //                   1 341
    //                   2 384
    //                   3 364
    //                   4 404
    //                   5 454
    //                   6 474
    //                   7 434
    //                   8 494
    //                   9 514
    //                   10 534
    //                   11 554
    //                   12 614
    //                   13 574
    //                   14 594
    //                   15 634
    //                   16 272
    //                   17 654
    //                   18 674
    //                   19 694
    //                   20 713;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L1 _L17 _L18 _L19 _L20
_L20:
        break MISSING_BLOCK_LABEL_713;
_L4:
        break; /* Loop/switch isn't completed */
_L1:
        throw new ActionException((new StringBuilder()).append("Tag ").append(l).append(" not found").toString());
_L2:
        mActions.add(new SetOnClickPendingIntent(parcel));
_L23:
        i++;
        if(true) goto _L22; else goto _L21
_L21:
        mActions.add(new SetDrawableParameters(parcel));
          goto _L23
_L3:
        mActions.add(new ReflectionAction(parcel));
          goto _L23
_L5:
        mActions.add(new ViewGroupActionAdd(parcel, mBitmapCache, mApplication, j));
          goto _L23
_L8:
        mActions.add(new ViewGroupActionRemove(parcel));
          goto _L23
_L6:
        mActions.add(new ReflectionActionWithoutParams(parcel));
          goto _L23
_L7:
        mActions.add(new SetEmptyView(parcel));
          goto _L23
_L9:
        mActions.add(new SetPendingIntentTemplate(parcel));
          goto _L23
_L10:
        mActions.add(new SetOnClickFillInIntent(parcel));
          goto _L23
_L11:
        mActions.add(new SetRemoteViewsAdapterIntent(parcel));
          goto _L23
_L12:
        mActions.add(new TextViewDrawableAction(parcel));
          goto _L23
_L14:
        mActions.add(new TextViewSizeAction(parcel));
          goto _L23
_L15:
        mActions.add(new ViewPaddingAction(parcel));
          goto _L23
_L13:
        mActions.add(new BitmapReflectionAction(parcel));
          goto _L23
_L16:
        mActions.add(new SetRemoteViewsAdapterList(parcel));
          goto _L23
_L17:
        mActions.add(new TextViewDrawableColorFilterAction(parcel));
          goto _L23
_L18:
        mActions.add(new SetRemoteInputsAction(parcel));
          goto _L23
_L19:
        mActions.add(new LayoutParamAction(parcel));
          goto _L23
        mActions.add(new OverrideTextColorsAction(parcel));
          goto _L23
        mLandscape = new RemoteViews(parcel, mBitmapCache, applicationinfo, j);
        mPortrait = new RemoteViews(parcel, mBitmapCache, mLandscape.mApplication, j);
        mApplication = mPortrait.mApplication;
        mLayoutId = mPortrait.getLayoutId();
        boolean flag1;
        if(parcel.readInt() == 0)
            flag1 = true;
        else
            flag1 = false;
        mReapplyDisallowed = flag1;
        mMemoryUsageCounter = new MemoryUsageCounter(null);
        recalculateMemoryUsage();
        return;
    }

    RemoteViews(Parcel parcel, BitmapCache bitmapcache, ApplicationInfo applicationinfo, int i, RemoteViews remoteviews)
    {
        this(parcel, bitmapcache, applicationinfo, i);
    }

    public RemoteViews(RemoteViews remoteviews, RemoteViews remoteviews1)
    {
        mIsRoot = true;
        mLandscape = null;
        mPortrait = null;
        mIsWidgetCollectionChild = false;
        mPair = new MutablePair(null, null);
        if(remoteviews == null || remoteviews1 == null)
            throw new RuntimeException("Both RemoteViews must be non-null");
        if(remoteviews.mApplication.uid != remoteviews1.mApplication.uid || remoteviews.mApplication.packageName.equals(remoteviews1.mApplication.packageName) ^ true)
        {
            throw new RuntimeException("Both RemoteViews must share the same package and user");
        } else
        {
            mApplication = remoteviews1.mApplication;
            mLayoutId = remoteviews1.getLayoutId();
            mLandscape = remoteviews;
            mPortrait = remoteviews1;
            mMemoryUsageCounter = new MemoryUsageCounter(null);
            mBitmapCache = new BitmapCache();
            configureRemoteViewsAsChild(remoteviews);
            configureRemoteViewsAsChild(remoteviews1);
            recalculateMemoryUsage();
            return;
        }
    }

    public RemoteViews(String s, int i)
    {
        this(getApplicationInfo(s, UserHandle.myUserId()), i);
    }

    public RemoteViews(String s, int i, int j)
    {
        this(getApplicationInfo(s, i), j);
    }

    private void addAction(Action action)
    {
        if(hasLandscapeAndPortraitLayouts())
            throw new RuntimeException("RemoteViews specifying separate landscape and portrait layouts cannot be modified. Instead, fully configure the landscape and portrait layouts individually before constructing the combined layout.");
        if(mActions == null)
            mActions = new ArrayList();
        mActions.add(action);
        action.updateMemoryUsageEstimate(mMemoryUsageCounter);
    }

    private void configureRemoteViewsAsChild(RemoteViews remoteviews)
    {
        mBitmapCache.assimilate(remoteviews.mBitmapCache);
        remoteviews.setBitmapCache(mBitmapCache);
        remoteviews.setNotRoot();
    }

    private static ApplicationInfo getApplicationInfo(String s, int i)
    {
label0:
        {
            if(s == null)
                return null;
            Application application = ActivityThread.currentApplication();
            if(application == null)
                throw new IllegalStateException("Cannot create remote views out of an aplication.");
            ApplicationInfo applicationinfo = application.getApplicationInfo();
            Object obj;
            if(UserHandle.getUserId(applicationinfo.uid) == i)
            {
                obj = applicationinfo;
                if(!(applicationinfo.packageName.equals(s) ^ true))
                    break label0;
            }
            try
            {
                obj = application.getBaseContext();
                UserHandle userhandle = JVM INSTR new #340 <Class UserHandle>;
                userhandle.UserHandle(i);
                obj = ((Context) (obj)).createPackageContextAsUser(s, 0, userhandle).getApplicationInfo();
            }
            catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("No such package ").append(s).toString());
            }
        }
        return ((ApplicationInfo) (obj));
    }

    private AsyncApplyTask getAsyncApplyTask(Context context, ViewGroup viewgroup, OnViewAppliedListener onviewappliedlistener, OnClickHandler onclickhandler)
    {
        return new AsyncApplyTask(getRemoteViewsToApply(context), viewgroup, context, onviewappliedlistener, onclickhandler, null, null);
    }

    private Method getAsyncMethod(Method method)
    {
        ArrayMap arraymap = sAsyncMethods;
        arraymap;
        JVM INSTR monitorenter ;
        int i = sAsyncMethods.indexOfKey(method);
        if(i < 0)
            break MISSING_BLOCK_LABEL_33;
        method = (Method)sAsyncMethods.valueAt(i);
        arraymap;
        JVM INSTR monitorexit ;
        return method;
        Object obj = (RemotableViewMethod)method.getAnnotation(android/view/RemotableViewMethod);
        Object obj1 = null;
        boolean flag = ((RemotableViewMethod) (obj)).asyncImpl().isEmpty();
        if(flag)
            break MISSING_BLOCK_LABEL_198;
        obj = method.getDeclaringClass().getMethod(((RemotableViewMethod) (obj)).asyncImpl(), method.getParameterTypes());
        obj1 = obj;
        try
        {
            if(!((Method) (obj)).getReturnType().equals(java/lang/Runnable))
            {
                obj1 = JVM INSTR new #19  <Class RemoteViews$ActionException>;
                StringBuilder stringbuilder = JVM INSTR new #365 <Class StringBuilder>;
                stringbuilder.StringBuilder();
                ((ActionException) (obj1)).ActionException(stringbuilder.append("Async implementation for ").append(method.getName()).append(" does not return a Runnable").toString());
                throw obj1;
            }
            break MISSING_BLOCK_LABEL_198;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj1) { }
        obj1 = JVM INSTR new #19  <Class RemoteViews$ActionException>;
        StringBuilder stringbuilder1 = JVM INSTR new #365 <Class StringBuilder>;
        stringbuilder1.StringBuilder();
        ((ActionException) (obj1)).ActionException(stringbuilder1.append("Async implementation declared but not defined for ").append(method.getName()).toString());
        throw obj1;
        method;
        arraymap;
        JVM INSTR monitorexit ;
        throw method;
        sAsyncMethods.put(method, obj1);
        arraymap;
        JVM INSTR monitorexit ;
        return ((Method) (obj1));
    }

    private Context getContextForResources(Context context)
    {
        if(mApplication == null)
            break MISSING_BLOCK_LABEL_94;
        if(context.getUserId() == UserHandle.getUserId(mApplication.uid) && context.getPackageName().equals(mApplication.packageName))
            return context;
        Context context1 = context.createApplicationContext(mApplication, 4);
        return context1;
        android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
        namenotfoundexception;
        Log.e("RemoteViews", (new StringBuilder()).append("Package name ").append(mApplication.packageName).append(" not found").toString());
        return context;
    }

    private Method getMethod(View view, String s, Class class1)
    {
        Class class2 = view.getClass();
        Object aobj[] = sMethodsLock;
        aobj;
        JVM INSTR monitorenter ;
        view = (ArrayMap)sMethods.get(class2);
        Object obj;
        obj = view;
        if(view != null)
            break MISSING_BLOCK_LABEL_54;
        obj = JVM INSTR new #292 <Class ArrayMap>;
        ((ArrayMap) (obj)).ArrayMap();
        sMethods.put(class2, obj);
        Method method;
        mPair.first = s;
        mPair.second = class1;
        method = (Method)((ArrayMap) (obj)).get(mPair);
        view = method;
        if(method != null)
            break MISSING_BLOCK_LABEL_275;
        if(class1 != null)
            break MISSING_BLOCK_LABEL_179;
        view = class2.getMethod(s, new Class[0]);
_L1:
        if(!view.isAnnotationPresent(android/view/RemotableViewMethod))
        {
            view = JVM INSTR new #19  <Class RemoteViews$ActionException>;
            obj = JVM INSTR new #365 <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            view.ActionException(((StringBuilder) (obj)).append("view: ").append(class2.getName()).append(" can't use method with RemoteViews: ").append(s).append(getParameters(class1)).toString());
            throw view;
        }
        break MISSING_BLOCK_LABEL_254;
        view;
        aobj;
        JVM INSTR monitorexit ;
        throw view;
        view = class2.getMethod(s, new Class[] {
            class1
        });
          goto _L1
        view;
        view = JVM INSTR new #19  <Class RemoteViews$ActionException>;
        obj = JVM INSTR new #365 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        view.ActionException(((StringBuilder) (obj)).append("view: ").append(class2.getName()).append(" doesn't have method: ").append(s).append(getParameters(class1)).toString());
        throw view;
        MutablePair mutablepair = JVM INSTR new #37  <Class RemoteViews$MutablePair>;
        mutablepair.MutablePair(s, class1);
        ((ArrayMap) (obj)).put(mutablepair, view);
        aobj;
        JVM INSTR monitorexit ;
        return view;
    }

    private static String getParameters(Class class1)
    {
        if(class1 == null)
            return "()";
        else
            return (new StringBuilder()).append("(").append(class1).append(")").toString();
    }

    private RemoteViews getRemoteViewsToApply(Context context)
    {
        if(hasLandscapeAndPortraitLayouts())
        {
            if(context.getResources().getConfiguration().orientation == 2)
                return mLandscape;
            else
                return mPortrait;
        } else
        {
            return this;
        }
    }

    private static Rect getSourceBounds(View view)
    {
        float f = view.getContext().getResources().getCompatibilityInfo().applicationScale;
        int ai[] = new int[2];
        view.getLocationOnScreen(ai);
        Rect rect = new Rect();
        rect.left = (int)((float)ai[0] * f + 0.5F);
        rect.top = (int)((float)ai[1] * f + 0.5F);
        rect.right = (int)((float)(ai[0] + view.getWidth()) * f + 0.5F);
        rect.bottom = (int)((float)(ai[1] + view.getHeight()) * f + 0.5F);
        return rect;
    }

    private boolean hasLandscapeAndPortraitLayouts()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(mLandscape != null)
        {
            flag1 = flag;
            if(mPortrait != null)
                flag1 = true;
        }
        return flag1;
    }

    private View inflateView(Context context, RemoteViews remoteviews, ViewGroup viewgroup)
    {
        RemoteViewsContextWrapper remoteviewscontextwrapper = new RemoteViewsContextWrapper(context, getContextForResources(context));
        context = ((LayoutInflater)context.getSystemService("layout_inflater")).cloneInContext(remoteviewscontextwrapper);
        context.setFilter(this);
        context = context.inflate(remoteviews.getLayoutId(), viewgroup, false);
        context.setTagInternal(0x1020018, Integer.valueOf(remoteviews.getLayoutId()));
        return context;
    }

    private static void loadTransitionOverride(Context context, OnClickHandler onclickhandler)
    {
        if(onclickhandler != null && context.getResources().getBoolean(0x1120085))
        {
            TypedArray typedarray = context.getTheme().obtainStyledAttributes(com.android.internal.R.styleable.Window);
            context = context.obtainStyledAttributes(typedarray.getResourceId(8, 0), com.android.internal.R.styleable.WindowAnimation);
            onclickhandler.setEnterAnimationId(context.getResourceId(26, 0));
            typedarray.recycle();
            context.recycle();
        }
    }

    private void performApply(View view, ViewGroup viewgroup, OnClickHandler onclickhandler)
    {
        if(mActions != null)
        {
            OnClickHandler onclickhandler1 = onclickhandler;
            if(onclickhandler == null)
                onclickhandler1 = DEFAULT_ON_CLICK_HANDLER;
            int i = mActions.size();
            for(int j = 0; j < i; j++)
                ((Action)mActions.get(j)).apply(view, viewgroup, onclickhandler1);

        }
    }

    private void recalculateMemoryUsage()
    {
        mMemoryUsageCounter.clear();
        if(!hasLandscapeAndPortraitLayouts())
        {
            if(mActions != null)
            {
                int i = mActions.size();
                for(int j = 0; j < i; j++)
                    ((Action)mActions.get(j)).updateMemoryUsageEstimate(mMemoryUsageCounter);

            }
            if(mIsRoot)
                mBitmapCache.addBitmapMemory(mMemoryUsageCounter);
        } else
        {
            mMemoryUsageCounter.increment(mLandscape.estimateMemoryUsage());
            mMemoryUsageCounter.increment(mPortrait.estimateMemoryUsage());
            mBitmapCache.addBitmapMemory(mMemoryUsageCounter);
        }
    }

    private void setBitmapCache(BitmapCache bitmapcache)
    {
        mBitmapCache = bitmapcache;
        if(!hasLandscapeAndPortraitLayouts())
        {
            if(mActions != null)
            {
                int i = mActions.size();
                for(int j = 0; j < i; j++)
                    ((Action)mActions.get(j)).setBitmapCache(bitmapcache);

            }
        } else
        {
            mLandscape.setBitmapCache(bitmapcache);
            mPortrait.setBitmapCache(bitmapcache);
        }
    }

    private CancellationSignal startTaskOnExecutor(AsyncApplyTask asyncapplytask, Executor executor)
    {
        CancellationSignal cancellationsignal = new CancellationSignal();
        cancellationsignal.setOnCancelListener(asyncapplytask);
        Executor executor1 = executor;
        if(executor == null)
            executor1 = AsyncTask.THREAD_POOL_EXECUTOR;
        asyncapplytask.executeOnExecutor(executor1, new Void[0]);
        return cancellationsignal;
    }

    private static Object[] wrapArg(Object obj)
    {
        Object aobj[] = (Object[])sInvokeArgsTls.get();
        aobj[0] = obj;
        return aobj;
    }

    public void addView(int i, RemoteViews remoteviews)
    {
        if(remoteviews == null)
            remoteviews = new ViewGroupActionRemove(i);
        else
            remoteviews = new ViewGroupActionAdd(i, remoteviews);
        addAction(remoteviews);
    }

    public void addView(int i, RemoteViews remoteviews, int j)
    {
        addAction(new ViewGroupActionAdd(i, remoteviews, j));
    }

    public View apply(Context context, ViewGroup viewgroup)
    {
        return apply(context, viewgroup, null);
    }

    public View apply(Context context, ViewGroup viewgroup, OnClickHandler onclickhandler)
    {
        RemoteViews remoteviews = getRemoteViewsToApply(context);
        View view = inflateView(context, remoteviews, viewgroup);
        loadTransitionOverride(context, onclickhandler);
        remoteviews.performApply(view, viewgroup, onclickhandler);
        return view;
    }

    public CancellationSignal applyAsync(Context context, ViewGroup viewgroup, Executor executor, OnViewAppliedListener onviewappliedlistener)
    {
        return applyAsync(context, viewgroup, executor, onviewappliedlistener, null);
    }

    public CancellationSignal applyAsync(Context context, ViewGroup viewgroup, Executor executor, OnViewAppliedListener onviewappliedlistener, OnClickHandler onclickhandler)
    {
        return startTaskOnExecutor(getAsyncApplyTask(context, viewgroup, onviewappliedlistener, onclickhandler), executor);
    }

    public RemoteViews clone()
    {
        this;
        JVM INSTR monitorenter ;
        RemoteViews remoteviews;
        Preconditions.checkState(mIsRoot, "RemoteView has been attached to another RemoteView. May only clone the root of a RemoteView hierarchy.");
        Parcel parcel = Parcel.obtain();
        mIsRoot = false;
        writeToParcel(parcel, 2);
        parcel.setDataPosition(0);
        mIsRoot = true;
        remoteviews = JVM INSTR new #2   <Class RemoteViews>;
        remoteviews.RemoteViews(parcel, mBitmapCache.clone(), mApplication, 0);
        remoteviews.mIsRoot = true;
        parcel.recycle();
        this;
        JVM INSTR monitorexit ;
        return remoteviews;
        Exception exception;
        exception;
        throw exception;
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public int describeContents()
    {
        return 0;
    }

    public int estimateMemoryUsage()
    {
        return mMemoryUsageCounter.getMemoryUsage();
    }

    public int getLayoutId()
    {
        return mLayoutId;
    }

    public String getPackage()
    {
        String s = null;
        if(mApplication != null)
            s = mApplication.packageName;
        return s;
    }

    public int getSequenceNumber()
    {
        int i;
        if(mActions == null)
            i = 0;
        else
            i = mActions.size();
        return i;
    }

    public boolean isReapplyDisallowed()
    {
        return mReapplyDisallowed;
    }

    public void mergeRemoteViews(RemoteViews remoteviews)
    {
        if(remoteviews == null)
            return;
        RemoteViews remoteviews1 = remoteviews.clone();
        remoteviews = new HashMap();
        if(mActions == null)
            mActions = new ArrayList();
        int i = mActions.size();
        for(int j = 0; j < i; j++)
        {
            Action action1 = (Action)mActions.get(j);
            remoteviews.put(action1.getUniqueKey(), action1);
        }

        ArrayList arraylist = remoteviews1.mActions;
        if(arraylist == null)
            return;
        i = arraylist.size();
        for(int k = 0; k < i; k++)
        {
            Action action = (Action)arraylist.get(k);
            String s = ((Action)arraylist.get(k)).getUniqueKey();
            int l = ((Action)arraylist.get(k)).mergeBehavior();
            if(remoteviews.containsKey(s) && l == 0)
            {
                mActions.remove(remoteviews.get(s));
                remoteviews.remove(s);
            }
            if(l == 0 || l == 1)
                mActions.add(action);
        }

        mBitmapCache = new BitmapCache();
        setBitmapCache(mBitmapCache);
        recalculateMemoryUsage();
    }

    public boolean onLoadClass(Class class1)
    {
        return class1.isAnnotationPresent(android/widget/RemoteViews$RemoteView);
    }

    public void overrideTextColors(int i)
    {
        addAction(new OverrideTextColorsAction(i));
    }

    public boolean prefersAsyncApply()
    {
        if(mActions != null)
        {
            int i = mActions.size();
            for(int j = 0; j < i; j++)
                if(((Action)mActions.get(j)).prefersAsyncApply())
                    return true;

        }
        return false;
    }

    public void reapply(Context context, View view)
    {
        reapply(context, view, null);
    }

    public void reapply(Context context, View view, OnClickHandler onclickhandler)
    {
        context = getRemoteViewsToApply(context);
        if(hasLandscapeAndPortraitLayouts() && ((Integer)view.getTag(0x1020018)).intValue() != context.getLayoutId())
        {
            throw new RuntimeException("Attempting to re-apply RemoteViews to a view that that does not share the same root layout id.");
        } else
        {
            context.performApply(view, (ViewGroup)view.getParent(), onclickhandler);
            return;
        }
    }

    public CancellationSignal reapplyAsync(Context context, View view, Executor executor, OnViewAppliedListener onviewappliedlistener)
    {
        return reapplyAsync(context, view, executor, onviewappliedlistener, null);
    }

    public CancellationSignal reapplyAsync(Context context, View view, Executor executor, OnViewAppliedListener onviewappliedlistener, OnClickHandler onclickhandler)
    {
        RemoteViews remoteviews = getRemoteViewsToApply(context);
        if(hasLandscapeAndPortraitLayouts() && ((Integer)view.getTag(0x1020018)).intValue() != remoteviews.getLayoutId())
            throw new RuntimeException("Attempting to re-apply RemoteViews to a view that that does not share the same root layout id.");
        else
            return startTaskOnExecutor(new AsyncApplyTask(remoteviews, (ViewGroup)view.getParent(), context, onviewappliedlistener, onclickhandler, view, null), executor);
    }

    public void reduceImageSizes(int i, int j)
    {
        ArrayList arraylist = mBitmapCache.mBitmaps;
        for(int k = 0; k < arraylist.size(); k++)
            arraylist.set(k, Icon.scaleDownIfNecessary((Bitmap)arraylist.get(k), i, j));

    }

    public void removeAllViews(int i)
    {
        addAction(new ViewGroupActionRemove(i));
    }

    public void removeAllViewsExceptId(int i, int j)
    {
        addAction(new ViewGroupActionRemove(i, j));
    }

    public void setAccessibilityTraversalAfter(int i, int j)
    {
        setInt(i, "setAccessibilityTraversalAfter", j);
    }

    public void setAccessibilityTraversalBefore(int i, int j)
    {
        setInt(i, "setAccessibilityTraversalBefore", j);
    }

    public void setBitmap(int i, String s, Bitmap bitmap)
    {
        addAction(new BitmapReflectionAction(i, s, bitmap));
    }

    public void setBoolean(int i, String s, boolean flag)
    {
        addAction(new ReflectionAction(i, s, 1, Boolean.valueOf(flag)));
    }

    public void setBundle(int i, String s, Bundle bundle)
    {
        addAction(new ReflectionAction(i, s, 13, bundle));
    }

    public void setByte(int i, String s, byte byte0)
    {
        addAction(new ReflectionAction(i, s, 2, Byte.valueOf(byte0)));
    }

    public void setChar(int i, String s, char c)
    {
        addAction(new ReflectionAction(i, s, 8, Character.valueOf(c)));
    }

    public void setCharSequence(int i, String s, CharSequence charsequence)
    {
        addAction(new ReflectionAction(i, s, 10, charsequence));
    }

    public void setChronometer(int i, long l, String s, boolean flag)
    {
        setLong(i, "setBase", l);
        setString(i, "setFormat", s);
        setBoolean(i, "setStarted", flag);
    }

    public void setChronometerCountDown(int i, boolean flag)
    {
        setBoolean(i, "setCountDown", flag);
    }

    public void setContentDescription(int i, CharSequence charsequence)
    {
        setCharSequence(i, "setContentDescription", charsequence);
    }

    public void setDisplayedChild(int i, int j)
    {
        setInt(i, "setDisplayedChild", j);
    }

    public void setDouble(int i, String s, double d)
    {
        addAction(new ReflectionAction(i, s, 7, Double.valueOf(d)));
    }

    public void setDrawableParameters(int i, boolean flag, int j, int k, android.graphics.PorterDuff.Mode mode, int l)
    {
        addAction(new SetDrawableParameters(i, flag, j, k, mode, l));
    }

    public void setEmptyView(int i, int j)
    {
        addAction(new SetEmptyView(i, j));
    }

    public void setFloat(int i, String s, float f)
    {
        addAction(new ReflectionAction(i, s, 6, Float.valueOf(f)));
    }

    public void setIcon(int i, String s, Icon icon)
    {
        addAction(new ReflectionAction(i, s, 16, icon));
    }

    public void setImageViewBitmap(int i, Bitmap bitmap)
    {
        setBitmap(i, "setImageBitmap", bitmap);
    }

    public void setImageViewIcon(int i, Icon icon)
    {
        setIcon(i, "setImageIcon", icon);
    }

    public void setImageViewResource(int i, int j)
    {
        setInt(i, "setImageResource", j);
    }

    public void setImageViewUri(int i, Uri uri)
    {
        setUri(i, "setImageURI", uri);
    }

    public void setInt(int i, String s, int j)
    {
        addAction(new ReflectionAction(i, s, 4, Integer.valueOf(j)));
    }

    public void setIntent(int i, String s, Intent intent)
    {
        addAction(new ReflectionAction(i, s, 14, intent));
    }

    void setIsWidgetCollectionChild(boolean flag)
    {
        mIsWidgetCollectionChild = flag;
    }

    public void setLabelFor(int i, int j)
    {
        setInt(i, "setLabelFor", j);
    }

    public void setLong(int i, String s, long l)
    {
        addAction(new ReflectionAction(i, s, 5, Long.valueOf(l)));
    }

    void setNotRoot()
    {
        mIsRoot = false;
    }

    public void setOnClickFillInIntent(int i, Intent intent)
    {
        addAction(new SetOnClickFillInIntent(i, intent));
    }

    public void setOnClickPendingIntent(int i, PendingIntent pendingintent)
    {
        addAction(new SetOnClickPendingIntent(i, pendingintent));
    }

    public void setPendingIntentTemplate(int i, PendingIntent pendingintent)
    {
        addAction(new SetPendingIntentTemplate(i, pendingintent));
    }

    public void setProgressBackgroundTintList(int i, ColorStateList colorstatelist)
    {
        addAction(new ReflectionAction(i, "setProgressBackgroundTintList", 15, colorstatelist));
    }

    public void setProgressBar(int i, int j, int k, boolean flag)
    {
        setBoolean(i, "setIndeterminate", flag);
        if(!flag)
        {
            setInt(i, "setMax", j);
            setInt(i, "setProgress", k);
        }
    }

    public void setProgressIndeterminateTintList(int i, ColorStateList colorstatelist)
    {
        addAction(new ReflectionAction(i, "setIndeterminateTintList", 15, colorstatelist));
    }

    public void setProgressTintList(int i, ColorStateList colorstatelist)
    {
        addAction(new ReflectionAction(i, "setProgressTintList", 15, colorstatelist));
    }

    public void setReapplyDisallowed()
    {
        mReapplyDisallowed = true;
    }

    public void setRelativeScrollPosition(int i, int j)
    {
        setInt(i, "smoothScrollByOffset", j);
    }

    public void setRemoteAdapter(int i, int j, Intent intent)
    {
        setRemoteAdapter(j, intent);
    }

    public void setRemoteAdapter(int i, Intent intent)
    {
        addAction(new SetRemoteViewsAdapterIntent(i, intent));
    }

    public void setRemoteAdapter(int i, ArrayList arraylist, int j)
    {
        addAction(new SetRemoteViewsAdapterList(i, arraylist, j));
    }

    public void setRemoteInputs(int i, RemoteInput aremoteinput[])
    {
        mActions.add(new SetRemoteInputsAction(i, aremoteinput));
    }

    public void setScrollPosition(int i, int j)
    {
        setInt(i, "smoothScrollToPosition", j);
    }

    public void setShort(int i, String s, short word0)
    {
        addAction(new ReflectionAction(i, s, 3, Short.valueOf(word0)));
    }

    public void setString(int i, String s, String s1)
    {
        addAction(new ReflectionAction(i, s, 9, s1));
    }

    public void setTextColor(int i, int j)
    {
        setInt(i, "setTextColor", j);
    }

    public void setTextColor(int i, ColorStateList colorstatelist)
    {
        addAction(new ReflectionAction(i, "setTextColor", 15, colorstatelist));
    }

    public void setTextViewCompoundDrawables(int i, int j, int k, int l, int i1)
    {
        addAction(new TextViewDrawableAction(i, false, j, k, l, i1));
    }

    public void setTextViewCompoundDrawables(int i, Icon icon, Icon icon1, Icon icon2, Icon icon3)
    {
        addAction(new TextViewDrawableAction(i, false, icon, icon1, icon2, icon3));
    }

    public void setTextViewCompoundDrawablesRelative(int i, int j, int k, int l, int i1)
    {
        addAction(new TextViewDrawableAction(i, true, j, k, l, i1));
    }

    public void setTextViewCompoundDrawablesRelative(int i, Icon icon, Icon icon1, Icon icon2, Icon icon3)
    {
        addAction(new TextViewDrawableAction(i, true, icon, icon1, icon2, icon3));
    }

    public void setTextViewCompoundDrawablesRelativeColorFilter(int i, int j, int k, android.graphics.PorterDuff.Mode mode)
    {
        if(j < 0 || j >= 4)
        {
            throw new IllegalArgumentException("index must be in range [0, 3].");
        } else
        {
            addAction(new TextViewDrawableColorFilterAction(i, true, j, k, mode));
            return;
        }
    }

    public void setTextViewText(int i, CharSequence charsequence)
    {
        setCharSequence(i, "setText", charsequence);
    }

    public void setTextViewTextSize(int i, int j, float f)
    {
        addAction(new TextViewSizeAction(i, j, f));
    }

    public void setUri(int i, String s, Uri uri)
    {
        Uri uri1 = uri;
        if(uri != null)
        {
            uri = uri.getCanonicalUri();
            uri1 = uri;
            if(StrictMode.vmFileUriExposureEnabled())
            {
                uri.checkFileUriExposed("RemoteViews.setUri()");
                uri1 = uri;
            }
        }
        addAction(new ReflectionAction(i, s, 11, uri1));
    }

    public void setViewLayoutMarginBottomDimen(int i, int j)
    {
        addAction(new LayoutParamAction(i, 3, j));
    }

    public void setViewLayoutMarginEndDimen(int i, int j)
    {
        addAction(new LayoutParamAction(i, 1, j));
    }

    public void setViewLayoutWidth(int i, int j)
    {
        if(j != 0 && j != -1 && j != -2)
        {
            throw new IllegalArgumentException("Only supports 0, WRAP_CONTENT and MATCH_PARENT");
        } else
        {
            mActions.add(new LayoutParamAction(i, 2, j));
            return;
        }
    }

    public void setViewPadding(int i, int j, int k, int l, int i1)
    {
        addAction(new ViewPaddingAction(i, j, k, l, i1));
    }

    public void setViewVisibility(int i, int j)
    {
        setInt(i, "setVisibility", j);
    }

    public void showNext(int i)
    {
        addAction(new ReflectionActionWithoutParams(i, "showNext"));
    }

    public void showPrevious(int i)
    {
        addAction(new ReflectionActionWithoutParams(i, "showPrevious"));
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        if(!hasLandscapeAndPortraitLayouts())
        {
            parcel.writeInt(0);
            if(mIsRoot)
                mBitmapCache.writeBitmapsToParcel(parcel, i);
            int j;
            if(!mIsRoot && (i & 2) != 0)
            {
                parcel.writeInt(0);
            } else
            {
                parcel.writeInt(1);
                mApplication.writeToParcel(parcel, i);
            }
            parcel.writeInt(mLayoutId);
            if(mIsWidgetCollectionChild)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(mActions != null)
                i = mActions.size();
            else
                i = 0;
            parcel.writeInt(i);
            j = 0;
            while(j < i) 
            {
                Action action = (Action)mActions.get(j);
                byte byte0;
                if(action.hasSameAppInfo(mApplication))
                    byte0 = 2;
                else
                    byte0 = 0;
                action.writeToParcel(parcel, byte0);
                j++;
            }
        } else
        {
            parcel.writeInt(1);
            if(mIsRoot)
                mBitmapCache.writeBitmapsToParcel(parcel, i);
            mLandscape.writeToParcel(parcel, i);
            mPortrait.writeToParcel(parcel, i | 2);
        }
        if(mReapplyDisallowed)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeInt(i);
    }

    private static final Action ACTION_NOOP = new RuntimeAction() {

        public void apply(View view, ViewGroup viewgroup, OnClickHandler onclickhandler)
        {
        }

    }
;
    private static final int BITMAP_REFLECTION_ACTION_TAG = 12;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public RemoteViews createFromParcel(Parcel parcel)
        {
            return new RemoteViews(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public RemoteViews[] newArray(int i)
        {
            return new RemoteViews[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final OnClickHandler DEFAULT_ON_CLICK_HANDLER = new OnClickHandler();
    static final String EXTRA_REMOTEADAPTER_APPWIDGET_ID = "remoteAdapterAppWidgetId";
    private static final int LAYOUT_PARAM_ACTION_TAG = 19;
    private static final String LOG_TAG = "RemoteViews";
    private static final int MAX_NESTED_VIEWS = 10;
    private static final int MODE_HAS_LANDSCAPE_AND_PORTRAIT = 1;
    private static final int MODE_NORMAL = 0;
    private static final int OVERRIDE_TEXT_COLORS_TAG = 20;
    private static final int REFLECTION_ACTION_TAG = 2;
    private static final int SET_DRAWABLE_PARAMETERS_TAG = 3;
    private static final int SET_EMPTY_VIEW_ACTION_TAG = 6;
    private static final int SET_ON_CLICK_FILL_IN_INTENT_TAG = 9;
    private static final int SET_ON_CLICK_PENDING_INTENT_TAG = 1;
    private static final int SET_PENDING_INTENT_TEMPLATE_TAG = 8;
    private static final int SET_REFLECTION_ACTION_WITHOUT_PARAMS_TAG = 5;
    private static final int SET_REMOTE_INPUTS_ACTION_TAG = 18;
    private static final int SET_REMOTE_VIEW_ADAPTER_INTENT_TAG = 10;
    private static final int SET_REMOTE_VIEW_ADAPTER_LIST_TAG = 15;
    private static final int TEXT_VIEW_DRAWABLE_ACTION_TAG = 11;
    private static final int TEXT_VIEW_DRAWABLE_COLOR_FILTER_ACTION_TAG = 17;
    private static final int TEXT_VIEW_SIZE_ACTION_TAG = 13;
    private static final int VIEW_GROUP_ACTION_ADD_TAG = 4;
    private static final int VIEW_GROUP_ACTION_REMOVE_TAG = 7;
    private static final int VIEW_PADDING_ACTION_TAG = 14;
    private static final ArrayMap sAsyncMethods = new ArrayMap();
    private static final ThreadLocal sInvokeArgsTls = new ThreadLocal() {

        protected volatile Object initialValue()
        {
            return ((Object) (initialValue()));
        }

        protected Object[] initialValue()
        {
            return new Object[1];
        }

    }
;
    private static final ArrayMap sMethods = new ArrayMap();
    private static final Object sMethodsLock[] = new Object[0];
    private ArrayList mActions;
    private ApplicationInfo mApplication;
    private BitmapCache mBitmapCache;
    private boolean mIsRoot;
    private boolean mIsWidgetCollectionChild;
    private RemoteViews mLandscape;
    private final int mLayoutId;
    private MemoryUsageCounter mMemoryUsageCounter;
    private final MutablePair mPair;
    private RemoteViews mPortrait;
    private boolean mReapplyDisallowed;

}
