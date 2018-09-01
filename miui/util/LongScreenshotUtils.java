// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.util;

import android.app.Activity;
import android.app.ActivityThread;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.os.*;
import android.util.*;
import android.view.*;
import android.webkit.WebView;
import android.widget.*;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class LongScreenshotUtils
{
    public static class ContentPort
    {

        static View _2D_get0(ContentPort contentport)
        {
            return contentport.mMainScrollView;
        }

        static void _2D_wrap0(ContentPort contentport)
        {
            contentport.broadcastCallback();
        }

        static void _2D_wrap1(ContentPort contentport)
        {
            contentport.finish();
        }

        static void _2D_wrap2(ContentPort contentport)
        {
            contentport.scrollView();
        }

        static void _2D_wrap3(ContentPort contentport)
        {
            contentport.start();
        }

        private void broadcastCallback()
        {
            boolean flag;
            int i;
            Intent intent;
            if(mMainScrollView.canScrollVertically(1))
            {
                if(mTotalScrollDistance >= mScreenRect.height() * 8)
                    flag = true;
                else
                    flag = false;
            } else
            {
                flag = true;
            }
            if(mPrevScrolledYChildView != null)
            {
                i = mPrevScrolledY - mPrevScrolledYChildView.getTop();
            } else
            {
                mMainScrollView.getLocationOnScreen(mTempLoc);
                i = mMainScrollView.getScrollY() - mTempLoc[1] - mPrevScrolledY;
            }
            mTotalScrollDistance = mTotalScrollDistance + i;
            intent = new Intent("com.miui.util.LongScreenshotUtils.LongScreenshot");
            intent.putExtra("IsEnd", flag);
            intent.putExtra("TopLoc", (mMainScrollViewTop + getScrollViewVisibleHeight()) - mVerticalEdge - i);
            intent.putExtra("BottomLoc", (mMainScrollViewTop + getScrollViewVisibleHeight()) - mVerticalEdge);
            intent.putExtra("ViewTop", mMainScrollViewTop);
            intent.putExtra("ViewBottom", mMainScrollViewTop + getScrollViewVisibleHeight());
            mMainScrollView.getContext().sendBroadcast(intent);
            if(flag)
                mHandler.sendEmptyMessageDelayed(4, 500L);
        }

        private boolean checkIsMainScrollView(View view)
        {
label0:
            {
                boolean flag;
                try
                {
                    if(isKnownScrollableView(view))
                        break label0;
                    flag = view.canScrollVertically(1);
                }
                // Misplaced declaration of an exception variable
                catch(View view)
                {
                    Log.w("LongScreenshotUtils", "", view);
                    return false;
                }
                if(flag ^ true)
                    return false;
            }
            int i = mScreenRect.width() / 3;
            int j = mScreenRect.height() / 2;
            if(view.getWidth() < i || view.getHeight() < j)
                return false;
            view.getLocationOnScreen(mTempLoc);
            for(Rect rect = new Rect(mScreenRect); !rect.intersect(mTempLoc[0], mTempLoc[1], mTempLoc[0] + view.getWidth(), mTempLoc[1] + view.getHeight()) || rect.width() < i || rect.height() < j;)
                return false;

            mMainScrollViewTop = mTempLoc[1];
            mMainScrollViewTop = findVisibleTop(view, mMainScrollViewTop);
            return true;
        }

        private boolean checkIsMayHasBg()
        {
            String s = mMainScrollView.getContext().getPackageName();
            String s1 = mMainScrollView.getClass().getName();
            boolean flag;
            if((!"com.miui.notes".equalsIgnoreCase(s) || !s1.equals("com.miui.notes.editor.RichEditView$RichEditScrollView")) && (!"com.tencent.mobileqq".equalsIgnoreCase(s) || !s1.equals("com.tencent.mobileqq.bubble.ChatXListView")))
            {
                if("com.tencent.mm".equalsIgnoreCase(s))
                    flag = mMainScrollView instanceof ListView;
                else
                    flag = false;
            } else
            {
                flag = true;
            }
            return flag;
        }

        private boolean checkNeedFakeTouchForScroll()
        {
            if((mMainScrollView instanceof AbsListView) || (mMainScrollView instanceof ScrollView) || isRecyclerView(mMainScrollView.getClass()) || isNestedScrollView(mMainScrollView.getClass()))
                return false;
            return !(mMainScrollView instanceof AbsoluteLayout) || android.os.Build.VERSION.SDK_INT > 19 && !"com.ucmobile".equalsIgnoreCase(mMainScrollView.getContext().getPackageName()) && !"com.eg.android.AlipayGphone".equalsIgnoreCase(mMainScrollView.getContext().getPackageName());
        }

        private void dispatchFakeTouchEvent(int i)
        {
            mTmpPointerProperties[0].id = 0;
            mTmpPointerCoords[0].x = mMainScrollView.getWidth() / 2;
            mTmpPointerCoords[0].y = mTouchY;
            MotionEvent motionevent = MotionEvent.obtain(System.currentTimeMillis(), System.currentTimeMillis(), i, 1, mTmpPointerProperties, mTmpPointerCoords, 0, 0, 1.0F, 1.0F, 0, 0, 0, 0);
            mMainScrollView.dispatchTouchEvent(motionevent);
            motionevent.recycle();
        }

        private void dispatchMoveAndReset(int i)
        {
            if(mIsFirstMove)
            {
                mTouchY = mTouchY - i;
                dispatchFakeTouchEvent(2);
                mIsFirstMove = false;
            } else
            {
                dispatchFakeTouchEvent(1);
                mTouchY = getScrollViewVisibleHeight() - mVerticalEdge;
                dispatchFakeTouchEvent(0);
                mTouchY = mTouchY - i;
                dispatchFakeTouchEvent(2);
            }
        }

        private View findMainScrollView()
        {
            Object obj = getTopActivity();
            if(obj != null)
            {
                if(android.provider.MiuiSettings.Global.getBoolean(((Activity) (obj)).getContentResolver(), "force_fsg_nav_bar"))
                {
                    WindowManager windowmanager = (WindowManager)((Activity) (obj)).getSystemService("window");
                    DisplayMetrics displaymetrics = new DisplayMetrics();
                    windowmanager.getDefaultDisplay().getRealMetrics(displaymetrics);
                    mScreenRect.set(0, 0, displaymetrics.widthPixels, displaymetrics.heightPixels);
                } else
                {
                    mScreenRect.set(0, 0, ((Activity) (obj)).getResources().getDisplayMetrics().widthPixels, ((Activity) (obj)).getResources().getDisplayMetrics().heightPixels);
                }
                obj = findScrollView(((Activity) (obj)).getWindow().getDecorView());
                if(obj == null)
                    return null;
                if(!((View) (obj)).canScrollVertically(1))
                    return null;
                else
                    return ((View) (obj));
            } else
            {
                Log.w("LongScreenshotUtils", (new StringBuilder()).append("Get top activity in ").append(Process.myPid()).append(" failed.").toString());
                return null;
            }
        }

        private View findScrollView(View view)
        {
            if(view == null || view.getVisibility() != 0)
                return null;
            if(view instanceof ViewGroup)
            {
                ViewGroup viewgroup = (ViewGroup)view;
                for(int i = viewgroup.getChildCount() - 1; i >= 0; i--)
                {
                    View view1 = findScrollView(viewgroup.getChildAt(i));
                    if(view1 != null)
                        return view1;
                }

            }
            if(checkIsMainScrollView(view))
                return view;
            else
                return null;
        }

        private static int findVisibleTop(View view, int i)
        {
            int j = 0;
            int k;
            do
            {
                k = j + view.getTop();
                if(!(view.getParent() instanceof View))
                    break;
                view = (View)view.getParent();
                j = k;
            } while(true);
            j = i;
            if(k < 0)
                j = i - k;
            return Math.max(j, 0);
        }

        private void finish()
        {
            if(mMainScrollView == null)
                return;
            mHandler.removeMessages(2);
            mMainScrollView.setVerticalScrollBarEnabled(mVerticalScrollBarEnabled);
            if(mIsFakeTouchForScroll)
                dispatchFakeTouchEvent(1);
            mMainScrollView = null;
            mPrevScrolledYChildView = null;
        }

        private int getExpectScrollDistance()
        {
            int i = getScrollViewVisibleHeight() - mVerticalEdge * 2;
            int j = i;
            if(isTencentApp())
            {
                j = i;
                if(mMainScrollView instanceof AbsoluteLayout)
                    j = i / 2;
            }
            return j;
        }

        private int getScrollViewVisibleHeight()
        {
            int i = mMainScrollView.getHeight();
            if(mMainScrollViewTop + i <= mScreenRect.height())
                return i;
            else
                return mScreenRect.height() - mMainScrollViewTop;
        }

        private static Activity getTopActivity()
        {
            Object obj = ActivityThread.currentActivityThread();
            Field field = android/app/ActivityThread.getDeclaredField("mActivities");
            field.setAccessible(true);
            obj = (ArrayMap)field.get(obj);
            int i = 0;
_L2:
            Object obj1;
            boolean flag;
            if(i >= ((ArrayMap) (obj)).size())
                break; /* Loop/switch isn't completed */
            obj1 = ((ArrayMap) (obj)).valueAt(i);
            Field field1 = obj1.getClass().getDeclaredField("activity");
            field1.setAccessible(true);
            obj1 = (Activity)field1.get(obj1);
            flag = ((Activity) (obj1)).isResumed();
            if(flag)
                return ((Activity) (obj1));
            i++;
            if(true) goto _L2; else goto _L1
            Exception exception;
            exception;
            exception.printStackTrace();
_L1:
            return null;
        }

        private boolean isKnownScrollableView(View view)
        {
            return (view instanceof AbsListView) || (view instanceof ListView) || (view instanceof ScrollView) || isRecyclerView(view.getClass()) || isNestedScrollView(view.getClass());
        }

        private boolean isNestedScrollView(Class class1)
        {
            if("android.support.v4.widget.NestedScrollView".equals(class1.getName()))
                return true;
            if(class1.equals(java/lang/Object))
                return false;
            else
                return isNestedScrollView(class1.getSuperclass());
        }

        private boolean isRecyclerView(Class class1)
        {
            if("android.support.v7.widget.RecyclerView".equals(class1.getName()))
                return true;
            if(class1.equals(java/lang/Object))
                return false;
            else
                return isRecyclerView(class1.getSuperclass());
        }

        private boolean isTencentApp()
        {
            return mMainScrollView.getContext().getPackageName().startsWith("com.tencent.");
        }

        private void scrollView()
        {
            if(mMainScrollView.canScrollVertically(1))
                scrollY(mMainScrollView, getExpectScrollDistance());
        }

        private void scrollY(View view, int i)
        {
            if((view instanceof ViewGroup) && (view instanceof ScrollView) ^ true && isNestedScrollView(view.getClass()) ^ true && (view instanceof WebView) ^ true && (view instanceof AbsoluteLayout) ^ true && ((ViewGroup)view).getChildCount() > 0)
            {
                ViewGroup viewgroup = (ViewGroup)view;
                mPrevScrolledYChildView = viewgroup.getChildAt(viewgroup.getChildCount() - 1);
                mPrevScrolledY = mPrevScrolledYChildView.getTop();
            } else
            {
                mPrevScrolledYChildView = null;
                view.getLocationOnScreen(mTempLoc);
                mPrevScrolledY = view.getScrollY() - mTempLoc[1];
            }
            if(mIsFakeTouchForScroll)
            {
                if(mNeedUseMultiTouch)
                {
                    dispatchMoveAndReset(i);
                } else
                {
                    mTouchY = mTouchY - i;
                    dispatchFakeTouchEvent(2);
                }
            } else
            if(view instanceof AbsListView)
                ((AbsListView)view).scrollListBy(i);
            else
                view.scrollBy(0, i);
        }

        private void start()
        {
            mTotalScrollDistance = 0;
            mVerticalEdge = getScrollViewVisibleHeight() / 5;
            mVerticalScrollBarEnabled = mMainScrollView.isVerticalScrollBarEnabled();
            mIsFakeTouchForScroll = checkNeedFakeTouchForScroll();
            if(mIsFakeTouchForScroll)
            {
                mNeedUseMultiTouch = isTencentApp();
                mTouchY = getScrollViewVisibleHeight() - mVerticalEdge;
                dispatchFakeTouchEvent(0);
                mIsFirstMove = true;
            }
            mMainScrollView.setVerticalScrollBarEnabled(false);
            mHandler.sendEmptyMessage(2);
        }

        public boolean longScreenshot(int i)
        {
            i;
            JVM INSTR tableswitch 1 4: default 32
        //                       1 34
        //                       2 34
        //                       3 69
        //                       4 81;
               goto _L1 _L2 _L2 _L3 _L4
_L1:
            return true;
_L2:
            View view = findMainScrollView();
            if(view == null)
                return false;
            if(i == 1)
                return true;
            mMainScrollView = view;
            mHandler.sendEmptyMessage(1);
            continue; /* Loop/switch isn't completed */
_L3:
            mHandler.sendEmptyMessage(2);
            continue; /* Loop/switch isn't completed */
_L4:
            mHandler.sendEmptyMessage(4);
            if(true) goto _L1; else goto _L5
_L5:
        }

        private H mHandler;
        private boolean mIsFakeTouchForScroll;
        private boolean mIsFirstMove;
        private View mMainScrollView;
        private int mMainScrollViewTop;
        private boolean mNeedUseMultiTouch;
        private int mPrevScrolledY;
        private View mPrevScrolledYChildView;
        private Rect mScreenRect;
        private int mTempLoc[];
        private android.view.MotionEvent.PointerCoords mTmpPointerCoords[];
        private android.view.MotionEvent.PointerProperties mTmpPointerProperties[];
        private int mTotalScrollDistance;
        private int mTouchY;
        private int mVerticalEdge;
        private boolean mVerticalScrollBarEnabled;

        public ContentPort()
        {
            mScreenRect = new Rect();
            mHandler = new H();
            mTempLoc = new int[2];
            mTmpPointerProperties = new android.view.MotionEvent.PointerProperties[2];
            mTmpPointerCoords = new android.view.MotionEvent.PointerCoords[2];
            for(int i = 0; i < 2; i++)
            {
                mTmpPointerProperties[i] = new android.view.MotionEvent.PointerProperties();
                mTmpPointerProperties[i].id = i;
                mTmpPointerCoords[i] = new android.view.MotionEvent.PointerCoords();
                mTmpPointerCoords[i].pressure = 1.0F;
                mTmpPointerCoords[i].size = 1.0F;
            }

        }
    }

    public class ContentPort.H extends Handler
    {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 4: default 36
        //                       1 37
        //                       2 47
        //                       3 76
        //                       4 96;
               goto _L1 _L2 _L3 _L4 _L5
_L1:
            return;
_L2:
            ContentPort._2D_wrap3(ContentPort.this);
            continue; /* Loop/switch isn't completed */
_L3:
            if(ContentPort._2D_get0(ContentPort.this) != null)
            {
                ContentPort._2D_wrap2(ContentPort.this);
                sendEmptyMessageDelayed(3, 200L);
            }
            continue; /* Loop/switch isn't completed */
_L4:
            if(ContentPort._2D_get0(ContentPort.this) != null)
                ContentPort._2D_wrap0(ContentPort.this);
            continue; /* Loop/switch isn't completed */
_L5:
            ContentPort._2D_wrap1(ContentPort.this);
            if(true) goto _L1; else goto _L6
_L6:
        }

        public static final int MSG_BROADCAST_CALLBACK = 3;
        public static final int MSG_FINISH = 4;
        public static final int MSG_SCROLL = 2;
        public static final int MSG_START = 1;
        final ContentPort this$1;

        public ContentPort.H()
        {
            this$1 = ContentPort.this;
            super(Looper.getMainLooper());
        }
    }

    public static class LongBitmapDrawable extends Drawable
    {

        public void draw(Canvas canvas)
        {
            canvas.save();
            Rect rect = getBounds();
            if(rect != null)
                canvas.translate(rect.left, rect.top);
            for(int i = 0; i < mBitmaps.length; i++)
            {
                Bitmap bitmap = mBitmaps[i];
                if(!canvas.quickReject(0.0F, 0.0F, bitmap.getWidth(), bitmap.getHeight(), android.graphics.Canvas.EdgeType.BW))
                    canvas.drawBitmap(bitmap, 0.0F, 0.0F, mPaint);
                canvas.translate(0.0F, bitmap.getHeight());
            }

            canvas.restore();
        }

        public Bitmap[] getBitmaps()
        {
            return mBitmaps;
        }

        public int getIntrinsicHeight()
        {
            if(mBitmaps == null || mBitmaps.length == 0)
                return 0;
            int i = 0;
            for(int j = 0; j < mBitmaps.length; j++)
                i += mBitmaps[j].getHeight();

            return i;
        }

        public int getIntrinsicWidth()
        {
            if(mBitmaps == null || mBitmaps.length == 0)
                return 0;
            else
                return mBitmaps[0].getWidth();
        }

        public int getOpacity()
        {
            return 0;
        }

        public void setAlpha(int i)
        {
            mPaint.setAlpha(i);
        }

        public void setColorFilter(ColorFilter colorfilter)
        {
        }

        static final int MAX_PART_HEIGHT = 1024;
        private Bitmap mBitmaps[];
        private Paint mPaint;

        public LongBitmapDrawable(Bitmap bitmap)
        {
            mBitmaps = new Bitmap[0];
            mPaint = new Paint(3);
            if(bitmap == null)
                return;
            ArrayList arraylist = new ArrayList();
            int i = bitmap.getWidth();
            int j = bitmap.getHeight();
            Paint paint = new Paint(4);
            int l;
            for(; j > 0; j -= l)
            {
                int k = bitmap.getHeight();
                l = Math.min(j, 1024);
                Bitmap bitmap1 = Bitmap.createBitmap(i, l, bitmap.getConfig());
                (new Canvas(bitmap1)).drawBitmap(bitmap, 0.0F, -(k - j), paint);
                arraylist.add(bitmap1);
            }

            mBitmaps = (Bitmap[])arraylist.toArray(new Bitmap[arraylist.size()]);
        }

        public LongBitmapDrawable(String s)
        {
            mBitmaps = new Bitmap[0];
            mPaint = new Paint(3);
            ArrayList arraylist = new ArrayList();
            Rect rect;
            int i;
            try
            {
                s = BitmapRegionDecoder.newInstance(s, false);
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                s.printStackTrace();
                return;
            }
            rect = new Rect(0, 0, s.getWidth(), Math.min(s.getHeight(), 1024));
            i = s.getHeight();
            do
            {
                if(i <= 0)
                    break;
                arraylist.add(s.decodeRegion(rect, null));
                rect.offset(0, rect.height());
                int j = i - rect.height();
                i = j;
                if(j < 0)
                {
                    rect.set(rect.left, rect.top, rect.right, rect.bottom + j);
                    i = j;
                }
            } while(true);
            s.recycle();
            mBitmaps = (Bitmap[])arraylist.toArray(new Bitmap[arraylist.size()]);
        }

        public LongBitmapDrawable(Bitmap abitmap[])
        {
            mBitmaps = new Bitmap[0];
            mPaint = new Paint(3);
            mBitmaps = abitmap;
        }
    }


    public LongScreenshotUtils()
    {
    }

    public static final String ACTION_LONG_SCREENSHOT = "com.miui.util.LongScreenshotUtils.LongScreenshot";
    static final int DELAY_FOR_BROADCAST_CALLBACK = 200;
    static final int DELAY_FOR_FINISH = 500;
    public static final String EXTRA_BOTTOM_LOC = "BottomLoc";
    public static final String EXTRA_IS_END = "IsEnd";
    public static final String EXTRA_IS_LONG_SCREENSHOT = "IsLongScreenshot";
    public static final String EXTRA_IS_SCREENSHOT = "IsScreenshot";
    public static final String EXTRA_TOP_LOC = "TopLoc";
    public static final String EXTRA_VIEW_BOTTOM = "ViewBottom";
    public static final String EXTRA_VIEW_TOP = "ViewTop";
    public static final int LONG_SCREENSHOT_CMD_DETECT = 1;
    public static final int LONG_SCREENSHOT_CMD_FINISH = 4;
    public static final int LONG_SCREENSHOT_CMD_START = 2;
    public static final int LONG_SCREENSHOT_CMD_TAKED = 3;
    static final int MAX_HEIGHT_FOR_SCREEN_COUNT = 8;
    private static final String TAG = "LongScreenshotUtils";
}
