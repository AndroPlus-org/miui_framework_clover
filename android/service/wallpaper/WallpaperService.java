// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.wallpaper;

import android.app.Service;
import android.app.WallpaperColors;
import android.content.Intent;
import android.content.res.*;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.hardware.display.DisplayManager;
import android.os.*;
import android.util.Log;
import android.util.MergedConfiguration;
import android.view.*;
import com.android.internal.os.HandlerCaller;
import com.android.internal.view.BaseIWindow;
import com.android.internal.view.BaseSurfaceHolder;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

// Referenced classes of package android.service.wallpaper:
//            IWallpaperConnection

public abstract class WallpaperService extends Service
{
    public class Engine
    {

        static Long _2D_android_service_wallpaper_WallpaperService$Engine_2D_mthref_2D_1()
        {
            return Long.valueOf(SystemClock.elapsedRealtime());
        }

        static int _2D_get0(Engine engine)
        {
            return engine.mDisplayState;
        }

        static void _2D_wrap0(Engine engine, MotionEvent motionevent)
        {
            engine.dispatchPointer(motionevent);
        }

        private void dispatchPointer(MotionEvent motionevent)
        {
            if(!motionevent.isTouchEvent())
                break MISSING_BLOCK_LABEL_63;
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            if(motionevent.getAction() != 2) goto _L2; else goto _L1
_L1:
            mPendingMove = motionevent;
_L3:
            obj;
            JVM INSTR monitorexit ;
            motionevent = mCaller.obtainMessageO(10040, motionevent);
            mCaller.sendMessage(motionevent);
_L4:
            return;
_L2:
            mPendingMove = null;
              goto _L3
            motionevent;
            throw motionevent;
            motionevent.recycle();
              goto _L4
        }

        void _2D_android_service_wallpaper_WallpaperService$Engine_2D_mthref_2D_0()
        {
            notifyColorsChanged();
        }

        void attach(IWallpaperEngineWrapper iwallpaperenginewrapper)
        {
            if(mDestroyed)
            {
                return;
            } else
            {
                mIWallpaperEngine = iwallpaperenginewrapper;
                mCaller = IWallpaperEngineWrapper._2D_get0(iwallpaperenginewrapper);
                mConnection = iwallpaperenginewrapper.mConnection;
                mWindowToken = iwallpaperenginewrapper.mWindowToken;
                mSurfaceHolder.setSizeFromLayout();
                mInitializing = true;
                mSession = WindowManagerGlobal.getWindowSession();
                mWindow.setSession(mSession);
                mLayout.packageName = getPackageName();
                mDisplayManager = (DisplayManager)getSystemService("display");
                mDisplayManager.registerDisplayListener(mDisplayListener, mCaller.getHandler());
                mDisplay = mDisplayManager.getDisplay(0);
                mDisplayState = mDisplay.getState();
                onCreate(mSurfaceHolder);
                mInitializing = false;
                mReportedVisible = false;
                updateSurface(false, false, false);
                return;
            }
        }

        void detach()
        {
            if(mDestroyed)
                return;
            mDestroyed = true;
            if(mDisplayManager != null)
                mDisplayManager.unregisterDisplayListener(mDisplayListener);
            if(mVisible)
            {
                mVisible = false;
                onVisibilityChanged(false);
            }
            reportSurfaceDestroyed();
            onDestroy();
            if(mCreated)
            {
                try
                {
                    if(mInputEventReceiver != null)
                    {
                        mInputEventReceiver.dispose();
                        mInputEventReceiver = null;
                    }
                    mSession.remove(mWindow);
                }
                catch(RemoteException remoteexception) { }
                mSurfaceHolder.mSurface.release();
                mCreated = false;
                if(mInputChannel != null)
                {
                    mInputChannel.dispose();
                    mInputChannel = null;
                }
            }
        }

        void doCommand(WallpaperCommand wallpapercommand)
        {
            Bundle bundle;
            if(!mDestroyed)
                bundle = onCommand(wallpapercommand.action, wallpapercommand.x, wallpapercommand.y, wallpapercommand.z, wallpapercommand.extras, wallpapercommand.sync);
            else
                bundle = null;
            if(!wallpapercommand.sync)
                break MISSING_BLOCK_LABEL_60;
            mSession.wallpaperCommandComplete(mWindow.asBinder(), bundle);
_L2:
            return;
            wallpapercommand;
            if(true) goto _L2; else goto _L1
_L1:
        }

        void doDesiredSizeChanged(int i, int j)
        {
            if(!mDestroyed)
            {
                mIWallpaperEngine.mReqWidth = i;
                mIWallpaperEngine.mReqHeight = j;
                onDesiredSizeChanged(i, j);
                doOffsetsChanged(true);
            }
        }

        void doDisplayPaddingChanged(Rect rect)
        {
            if(!mDestroyed && !mIWallpaperEngine.mDisplayPadding.equals(rect))
            {
                mIWallpaperEngine.mDisplayPadding.set(rect);
                updateSurface(true, false, false);
            }
        }

        void doOffsetsChanged(boolean flag)
        {
            if(mDestroyed)
                return;
            if(!flag && mOffsetsChanged ^ true)
                return;
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            float f;
            float f1;
            float f2;
            float f3;
            f = mPendingXOffset;
            f1 = mPendingYOffset;
            f2 = mPendingXOffsetStep;
            f3 = mPendingYOffsetStep;
            flag = mPendingSync;
            mPendingSync = false;
            mOffsetMessageEnqueued = false;
            obj;
            JVM INSTR monitorexit ;
            if(mSurfaceCreated)
                if(mReportedVisible)
                {
                    int i = mIWallpaperEngine.mReqWidth - mCurWidth;
                    int j;
                    Exception exception;
                    if(i > 0)
                        i = -(int)((float)i * f + 0.5F);
                    else
                        i = 0;
                    j = mIWallpaperEngine.mReqHeight - mCurHeight;
                    if(j > 0)
                        j = -(int)((float)j * f1 + 0.5F);
                    else
                        j = 0;
                    onOffsetsChanged(f, f1, f2, f3, i, j);
                } else
                {
                    mOffsetsChanged = true;
                }
            if(!flag)
                break MISSING_BLOCK_LABEL_183;
            mSession.wallpaperOffsetsComplete(mWindow.asBinder());
_L2:
            return;
            exception;
            throw exception;
            RemoteException remoteexception;
            remoteexception;
            if(true) goto _L2; else goto _L1
_L1:
        }

        void doVisibilityChanged(boolean flag)
        {
            if(!mDestroyed)
            {
                mVisible = flag;
                reportVisibility();
            }
        }

        protected void dump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
        {
            printwriter.print(s);
            printwriter.print("mInitializing=");
            printwriter.print(mInitializing);
            printwriter.print(" mDestroyed=");
            printwriter.println(mDestroyed);
            printwriter.print(s);
            printwriter.print("mVisible=");
            printwriter.print(mVisible);
            printwriter.print(" mReportedVisible=");
            printwriter.println(mReportedVisible);
            printwriter.print(s);
            printwriter.print("mDisplay=");
            printwriter.println(mDisplay);
            printwriter.print(s);
            printwriter.print("mCreated=");
            printwriter.print(mCreated);
            printwriter.print(" mSurfaceCreated=");
            printwriter.print(mSurfaceCreated);
            printwriter.print(" mIsCreating=");
            printwriter.print(mIsCreating);
            printwriter.print(" mDrawingAllowed=");
            printwriter.println(mDrawingAllowed);
            printwriter.print(s);
            printwriter.print("mWidth=");
            printwriter.print(mWidth);
            printwriter.print(" mCurWidth=");
            printwriter.print(mCurWidth);
            printwriter.print(" mHeight=");
            printwriter.print(mHeight);
            printwriter.print(" mCurHeight=");
            printwriter.println(mCurHeight);
            printwriter.print(s);
            printwriter.print("mType=");
            printwriter.print(mType);
            printwriter.print(" mWindowFlags=");
            printwriter.print(mWindowFlags);
            printwriter.print(" mCurWindowFlags=");
            printwriter.println(mCurWindowFlags);
            printwriter.print(s);
            printwriter.print("mWindowPrivateFlags=");
            printwriter.print(mWindowPrivateFlags);
            printwriter.print(" mCurWindowPrivateFlags=");
            printwriter.println(mCurWindowPrivateFlags);
            printwriter.print(s);
            printwriter.print("mVisibleInsets=");
            printwriter.print(mVisibleInsets.toShortString());
            printwriter.print(" mWinFrame=");
            printwriter.print(mWinFrame.toShortString());
            printwriter.print(" mContentInsets=");
            printwriter.println(mContentInsets.toShortString());
            printwriter.print(s);
            printwriter.print("mConfiguration=");
            printwriter.println(mMergedConfiguration.getMergedConfiguration());
            printwriter.print(s);
            printwriter.print("mLayout=");
            printwriter.println(mLayout);
            filedescriptor = ((FileDescriptor) (mLock));
            filedescriptor;
            JVM INSTR monitorenter ;
            printwriter.print(s);
            printwriter.print("mPendingXOffset=");
            printwriter.print(mPendingXOffset);
            printwriter.print(" mPendingXOffset=");
            printwriter.println(mPendingXOffset);
            printwriter.print(s);
            printwriter.print("mPendingXOffsetStep=");
            printwriter.print(mPendingXOffsetStep);
            printwriter.print(" mPendingXOffsetStep=");
            printwriter.println(mPendingXOffsetStep);
            printwriter.print(s);
            printwriter.print("mOffsetMessageEnqueued=");
            printwriter.print(mOffsetMessageEnqueued);
            printwriter.print(" mPendingSync=");
            printwriter.println(mPendingSync);
            if(mPendingMove != null)
            {
                printwriter.print(s);
                printwriter.print("mPendingMove=");
                printwriter.println(mPendingMove);
            }
            filedescriptor;
            JVM INSTR monitorexit ;
            return;
            s;
            throw s;
        }

        public int getDesiredMinimumHeight()
        {
            return mIWallpaperEngine.mReqHeight;
        }

        public int getDesiredMinimumWidth()
        {
            return mIWallpaperEngine.mReqWidth;
        }

        public SurfaceHolder getSurfaceHolder()
        {
            return mSurfaceHolder;
        }

        public boolean isPreview()
        {
            return mIWallpaperEngine.mIsPreview;
        }

        public boolean isVisible()
        {
            return mReportedVisible;
        }

        public void notifyColorsChanged()
        {
            long l = ((Long)mClockFunction.get()).longValue();
            if(l - mLastColorInvalidation < 1000L)
            {
                Log.w("WallpaperService", "This call has been deferred. You should only call notifyColorsChanged() once every 1.0 seconds.");
                if(!mHandler.hasCallbacks(mNotifyColorsChanged))
                    mHandler.postDelayed(mNotifyColorsChanged, 1000L);
                return;
            }
            mLastColorInvalidation = l;
            mHandler.removeCallbacks(mNotifyColorsChanged);
            WallpaperColors wallpapercolors = onComputeColors();
            if(mConnection == null)
                break MISSING_BLOCK_LABEL_108;
            mConnection.onWallpaperColorsChanged(wallpapercolors);
_L1:
            return;
            try
            {
                Log.w("WallpaperService", "Can't notify system because wallpaper connection was not established.");
            }
            catch(RemoteException remoteexception)
            {
                Log.w("WallpaperService", "Can't notify system because wallpaper connection was lost.", remoteexception);
            }
              goto _L1
        }

        public void onApplyWindowInsets(WindowInsets windowinsets)
        {
        }

        public Bundle onCommand(String s, int i, int j, int k, Bundle bundle, boolean flag)
        {
            return null;
        }

        public WallpaperColors onComputeColors()
        {
            return null;
        }

        public void onCreate(SurfaceHolder surfaceholder)
        {
        }

        public void onDesiredSizeChanged(int i, int j)
        {
        }

        public void onDestroy()
        {
        }

        public void onOffsetsChanged(float f, float f1, float f2, float f3, int i, int j)
        {
        }

        public void onSurfaceChanged(SurfaceHolder surfaceholder, int i, int j, int k)
        {
        }

        public void onSurfaceCreated(SurfaceHolder surfaceholder)
        {
        }

        public void onSurfaceDestroyed(SurfaceHolder surfaceholder)
        {
        }

        public void onSurfaceRedrawNeeded(SurfaceHolder surfaceholder)
        {
        }

        public void onTouchEvent(MotionEvent motionevent)
        {
        }

        public void onVisibilityChanged(boolean flag)
        {
        }

        void reportSurfaceDestroyed()
        {
            int i = 0;
            if(mSurfaceCreated)
            {
                mSurfaceCreated = false;
                mSurfaceHolder.ungetCallbacks();
                android.view.SurfaceHolder.Callback acallback[] = mSurfaceHolder.getCallbacks();
                if(acallback != null)
                {
                    for(int j = acallback.length; i < j; i++)
                        acallback[i].surfaceDestroyed(mSurfaceHolder);

                }
                onSurfaceDestroyed(mSurfaceHolder);
            }
        }

        void reportVisibility()
        {
            if(!mDestroyed)
            {
                int i;
                boolean flag;
                if(mDisplay == null)
                    i = 0;
                else
                    i = mDisplay.getState();
                mDisplayState = i;
                if(mVisible && mDisplayState != 1)
                    flag = true;
                else
                    flag = false;
                if(mReportedVisible != flag)
                {
                    mReportedVisible = flag;
                    if(flag)
                    {
                        doOffsetsChanged(false);
                        updateSurface(false, false, false);
                    }
                    onVisibilityChanged(flag);
                }
            }
        }

        public void setFixedSizeAllowed(boolean flag)
        {
            mFixedSizeAllowed = flag;
        }

        public void setOffsetNotificationsEnabled(boolean flag)
        {
            int i;
            if(flag)
                i = mWindowPrivateFlags | 4;
            else
                i = mWindowPrivateFlags & -5;
            mWindowPrivateFlags = i;
            if(mCreated)
                updateSurface(false, false, false);
        }

        public void setTouchEventsEnabled(boolean flag)
        {
            int i;
            if(flag)
                i = mWindowFlags & 0xffffffef;
            else
                i = mWindowFlags | 0x10;
            mWindowFlags = i;
            if(mCreated)
                updateSurface(false, false, false);
        }

        void updateSurface(boolean flag, boolean flag1, boolean flag2)
        {
            boolean flag3;
            boolean flag4;
            boolean flag5;
            boolean flag6;
            boolean flag7;
            boolean flag8;
            if(mDestroyed)
                Log.w("WallpaperService", "Ignoring updateSurface: destroyed");
            flag3 = false;
            int i = mSurfaceHolder.getRequestedWidth();
            int k;
            boolean flag9;
            boolean flag10;
            InputChannel inputchannel;
            if(i <= 0)
                i = -1;
            else
                flag3 = true;
            k = mSurfaceHolder.getRequestedHeight();
            if(k <= 0)
            {
                k = -1;
                flag4 = flag3;
            } else
            {
                flag4 = true;
            }
            flag5 = mCreated ^ true;
            flag6 = mSurfaceCreated ^ true;
            if(mFormat != mSurfaceHolder.getRequestedFormat())
                flag7 = true;
            else
                flag7 = false;
            if(mWidth != i || mHeight != k)
                flag3 = true;
            else
                flag3 = false;
            flag8 = mCreated;
            if(mType != mSurfaceHolder.getRequestedType())
                flag9 = true;
            else
                flag9 = false;
            if(mCurWindowFlags == mWindowFlags)
            {
                if(mCurWindowPrivateFlags != mWindowPrivateFlags)
                    flag10 = true;
                else
                    flag10 = false;
            } else
            {
                flag10 = true;
            }
            if(!flag && !flag5 && !flag6 && !flag7 && !flag3 && !flag9 && !flag10 && !flag2 && !(mIWallpaperEngine.mShownReported ^ true)) goto _L2; else goto _L1
_L1:
            mWidth = i;
            mHeight = k;
            mFormat = mSurfaceHolder.getRequestedFormat();
            mType = mSurfaceHolder.getRequestedType();
            mLayout.x = 0;
            mLayout.y = 0;
            mLayout.width = i;
            mLayout.height = k;
            mLayout.format = mFormat;
            mCurWindowFlags = mWindowFlags;
            mLayout.flags = mWindowFlags | 0x200 | 0x10000 | 0x100 | 8;
            mCurWindowPrivateFlags = mWindowPrivateFlags;
            mLayout.privateFlags = mWindowPrivateFlags;
            mLayout.memoryType = mType;
            mLayout.token = mWindowToken;
            if(mCreated)
                break MISSING_BLOCK_LABEL_578;
            obtainStyledAttributes(com.android.internal.R.styleable.Window).recycle();
            mLayout.type = mIWallpaperEngine.mWindowType;
            mLayout.gravity = 0x800033;
            mLayout.setTitle(getClass().getName());
            mLayout.windowAnimations = 0x1030306;
            inputchannel = JVM INSTR new #383 <Class InputChannel>;
            inputchannel.InputChannel();
            mInputChannel = inputchannel;
            if(mSession.addToDisplay(mWindow, mWindow.mSeq, mLayout, 0, 0, mContentInsets, mStableInsets, mOutsets, mInputChannel) < 0)
            {
                Log.w("WallpaperService", "Failed to add window while updating wallpaper surface.");
                return;
            }
            mCreated = true;
            WallpaperInputEventReceiver wallpaperinputeventreceiver = JVM INSTR new #15  <Class WallpaperService$Engine$WallpaperInputEventReceiver>;
            wallpaperinputeventreceiver.this. WallpaperInputEventReceiver(mInputChannel, Looper.myLooper());
            mInputEventReceiver = wallpaperinputeventreceiver;
            mSurfaceHolder.mSurfaceLock.lock();
            mDrawingAllowed = true;
            if(flag4) goto _L4; else goto _L3
_L3:
            mLayout.surfaceInsets.set(mIWallpaperEngine.mDisplayPadding);
            Rect rect = mLayout.surfaceInsets;
            rect.left = rect.left + mOutsets.left;
            rect = mLayout.surfaceInsets;
            rect.top = rect.top + mOutsets.top;
            rect = mLayout.surfaceInsets;
            rect.right = rect.right + mOutsets.right;
            rect = mLayout.surfaceInsets;
            rect.bottom = rect.bottom + mOutsets.bottom;
_L6:
            int l1;
            int i2;
            int j2;
            j2 = mSession.relayout(mWindow, mWindow.mSeq, mLayout, mWidth, mHeight, 0, 0, mWinFrame, mOverscanInsets, mContentInsets, mVisibleInsets, mStableInsets, mOutsets, mBackdropFrame, mMergedConfiguration, mSurfaceHolder.mSurface);
            l1 = mWinFrame.width();
            i2 = mWinFrame.height();
            int j;
            int l;
            l = i2;
            j = l1;
            if(flag4)
                break MISSING_BLOCK_LABEL_1166;
            Rect rect1 = mIWallpaperEngine.mDisplayPadding;
            j = l1 + (rect1.left + rect1.right + mOutsets.left + mOutsets.right);
            l = i2 + (rect1.top + rect1.bottom + mOutsets.top + mOutsets.bottom);
            Rect rect2 = mOverscanInsets;
            rect2.left = rect2.left + rect1.left;
            rect2 = mOverscanInsets;
            rect2.top = rect2.top + rect1.top;
            rect2 = mOverscanInsets;
            rect2.right = rect2.right + rect1.right;
            rect2 = mOverscanInsets;
            rect2.bottom = rect2.bottom + rect1.bottom;
            rect2 = mContentInsets;
            rect2.left = rect2.left + rect1.left;
            rect2 = mContentInsets;
            rect2.top = rect2.top + rect1.top;
            rect2 = mContentInsets;
            rect2.right = rect2.right + rect1.right;
            rect2 = mContentInsets;
            rect2.bottom = rect2.bottom + rect1.bottom;
            rect2 = mStableInsets;
            rect2.left = rect2.left + rect1.left;
            rect2 = mStableInsets;
            rect2.top = rect2.top + rect1.top;
            rect2 = mStableInsets;
            rect2.right = rect2.right + rect1.right;
            rect2 = mStableInsets;
            rect2.bottom = rect2.bottom + rect1.bottom;
            if(mCurWidth == j)
                break MISSING_BLOCK_LABEL_1184;
            flag3 = true;
            mCurWidth = j;
            i2 = ((flag3) ? 1 : 0);
            if(mCurHeight == l)
                break MISSING_BLOCK_LABEL_1206;
            i2 = 1;
            mCurHeight = l;
            boolean flag11;
            boolean flag12;
            boolean flag13;
            boolean flag14;
            flag11 = mDispatchedOverscanInsets.equals(mOverscanInsets);
            flag12 = mDispatchedContentInsets.equals(mContentInsets);
            flag13 = mDispatchedStableInsets.equals(mStableInsets);
            flag14 = mDispatchedOutsets.equals(mOutsets);
            mSurfaceHolder.setSurfaceFrameSize(j, l);
            mSurfaceHolder.mSurfaceLock.unlock();
            if(!mSurfaceHolder.mSurface.isValid())
            {
                reportSurfaceDestroyed();
                return;
            }
              goto _L5
_L4:
            mLayout.surfaceInsets.set(0, 0, 0, 0);
              goto _L6
            Object obj;
            obj;
_L2:
            return;
_L5:
            flag3 = false;
            flag = flag2;
            mSurfaceHolder.ungetCallbacks();
            if(!flag6) goto _L8; else goto _L7
_L7:
            flag = flag2;
            mIsCreating = true;
            j = 1;
            flag = flag2;
            onSurfaceCreated(mSurfaceHolder);
            flag = flag2;
            android.view.SurfaceHolder.Callback acallback[] = mSurfaceHolder.getCallbacks();
            flag3 = j;
            if(acallback == null) goto _L8; else goto _L9
_L9:
            int i1;
            i1 = 0;
            flag = flag2;
            l = acallback.length;
_L10:
            flag3 = j;
            if(i1 >= l)
                break; /* Loop/switch isn't completed */
            flag = flag2;
            acallback[i1].surfaceCreated(mSurfaceHolder);
            i1++;
            if(true) goto _L10; else goto _L8
_L8:
            int j1;
            android.view.SurfaceHolder.Callback acallback1[];
            if(flag5 || (j2 & 2) != 0)
                j1 = 1;
            else
                j1 = 0;
            flag2 |= j1;
            if(!flag1 && !flag5 && !flag6 && !flag7 && !i2) goto _L12; else goto _L11
_L11:
            flag7 = true;
            flag = flag2;
            onSurfaceChanged(mSurfaceHolder, mFormat, mCurWidth, mCurHeight);
            flag = flag2;
            acallback1 = mSurfaceHolder.getCallbacks();
            flag3 = flag7;
            if(acallback1 == null) goto _L12; else goto _L13
_L13:
            i2 = 0;
            flag = flag2;
            j1 = acallback1.length;
_L14:
            flag3 = flag7;
            if(i2 >= j1)
                break; /* Loop/switch isn't completed */
            flag = flag2;
            acallback1[i2].surfaceChanged(mSurfaceHolder, mFormat, mCurWidth, mCurHeight);
            i2++;
            if(true) goto _L14; else goto _L12
_L12:
            if(!(flag8 ^ true | flag11 ^ true | flag12 ^ true | flag13 ^ true | flag14 ^ true))
                break MISSING_BLOCK_LABEL_1829;
            flag = flag2;
            mDispatchedOverscanInsets.set(mOverscanInsets);
            flag = flag2;
            Object obj1 = mDispatchedOverscanInsets;
            flag = flag2;
            obj1.left = ((Rect) (obj1)).left + mOutsets.left;
            flag = flag2;
            obj1 = mDispatchedOverscanInsets;
            flag = flag2;
            obj1.top = ((Rect) (obj1)).top + mOutsets.top;
            flag = flag2;
            obj1 = mDispatchedOverscanInsets;
            flag = flag2;
            obj1.right = ((Rect) (obj1)).right + mOutsets.right;
            flag = flag2;
            obj1 = mDispatchedOverscanInsets;
            flag = flag2;
            obj1.bottom = ((Rect) (obj1)).bottom + mOutsets.bottom;
            flag = flag2;
            mDispatchedContentInsets.set(mContentInsets);
            flag = flag2;
            mDispatchedStableInsets.set(mStableInsets);
            flag = flag2;
            mDispatchedOutsets.set(mOutsets);
            flag = flag2;
            mFinalSystemInsets.set(mDispatchedOverscanInsets);
            flag = flag2;
            mFinalStableInsets.set(mDispatchedStableInsets);
            flag = flag2;
            obj1 = JVM INSTR new #828 <Class WindowInsets>;
            flag = flag2;
            ((WindowInsets) (obj1)).WindowInsets(mFinalSystemInsets, null, mFinalStableInsets, getResources().getConfiguration().isScreenRound(), false);
            flag = flag2;
            onApplyWindowInsets(((WindowInsets) (obj1)));
            if(!flag2)
                break MISSING_BLOCK_LABEL_1915;
            flag = flag2;
            onSurfaceRedrawNeeded(mSurfaceHolder);
            flag = flag2;
            android.view.SurfaceHolder.Callback acallback2[] = mSurfaceHolder.getCallbacks();
            if(acallback2 == null)
                break MISSING_BLOCK_LABEL_1915;
            i2 = 0;
            flag = flag2;
            int k1 = acallback2.length;
_L17:
            if(i2 >= k1) goto _L16; else goto _L15
_L15:
            obj1 = acallback2[i2];
            flag = flag2;
            if(!(obj1 instanceof android.view.SurfaceHolder.Callback2))
                continue; /* Loop/switch isn't completed */
            flag = flag2;
            ((android.view.SurfaceHolder.Callback2)obj1).surfaceRedrawNeeded(mSurfaceHolder);
            i2++;
              goto _L17
_L16:
            if(!flag3)
                break MISSING_BLOCK_LABEL_1954;
            flag = flag2;
            if(!(mReportedVisible ^ true))
                break MISSING_BLOCK_LABEL_1954;
            flag = flag2;
            if(!mIsCreating)
                break MISSING_BLOCK_LABEL_1947;
            flag = flag2;
            onVisibilityChanged(true);
            flag = flag2;
            onVisibilityChanged(false);
            mIsCreating = false;
            mSurfaceCreated = true;
            if(!flag2)
                break MISSING_BLOCK_LABEL_1981;
            mSession.finishDrawing(mWindow);
            mIWallpaperEngine.reportShown();
            if(true) goto _L2; else goto _L18
_L18:
            obj1;
            mIsCreating = false;
            mSurfaceCreated = true;
            if(!flag)
                break MISSING_BLOCK_LABEL_2020;
            mSession.finishDrawing(mWindow);
            mIWallpaperEngine.reportShown();
            throw obj1;
        }

        final Rect mBackdropFrame;
        HandlerCaller mCaller;
        private final Supplier mClockFunction;
        IWallpaperConnection mConnection;
        final Rect mContentInsets;
        boolean mCreated;
        int mCurHeight;
        int mCurWidth;
        int mCurWindowFlags;
        int mCurWindowPrivateFlags;
        boolean mDestroyed;
        final Rect mDispatchedContentInsets;
        final Rect mDispatchedOutsets;
        final Rect mDispatchedOverscanInsets;
        final Rect mDispatchedStableInsets;
        Display mDisplay;
        private final android.hardware.display.DisplayManager.DisplayListener mDisplayListener;
        DisplayManager mDisplayManager;
        private int mDisplayState;
        boolean mDrawingAllowed;
        final Rect mFinalStableInsets;
        final Rect mFinalSystemInsets;
        boolean mFixedSizeAllowed;
        int mFormat;
        private final Handler mHandler;
        int mHeight;
        IWallpaperEngineWrapper mIWallpaperEngine;
        boolean mInitializing;
        InputChannel mInputChannel;
        WallpaperInputEventReceiver mInputEventReceiver;
        boolean mIsCreating;
        private long mLastColorInvalidation;
        final android.view.WindowManager.LayoutParams mLayout;
        final Object mLock;
        final MergedConfiguration mMergedConfiguration;
        private final Runnable mNotifyColorsChanged;
        boolean mOffsetMessageEnqueued;
        boolean mOffsetsChanged;
        final Rect mOutsets;
        final Rect mOverscanInsets;
        MotionEvent mPendingMove;
        boolean mPendingSync;
        float mPendingXOffset;
        float mPendingXOffsetStep;
        float mPendingYOffset;
        float mPendingYOffsetStep;
        boolean mReportedVisible;
        IWindowSession mSession;
        final Rect mStableInsets;
        boolean mSurfaceCreated;
        final BaseSurfaceHolder mSurfaceHolder;
        int mType;
        boolean mVisible;
        final Rect mVisibleInsets;
        int mWidth;
        final Rect mWinFrame;
        final BaseIWindow mWindow;
        int mWindowFlags;
        int mWindowPrivateFlags;
        IBinder mWindowToken;
        final WallpaperService this$0;

        public Engine()
        {
            this(((Supplier) (_.Lambda.htiXs5zQinBXs3seMVLgh3fgmis.$INST$0)), Handler.getMain());
        }

        public Engine(Supplier supplier, Handler handler)
        {
            this$0 = WallpaperService.this;
            super();
            mInitializing = true;
            mWindowFlags = 16;
            mWindowPrivateFlags = 4;
            mCurWindowFlags = mWindowFlags;
            mCurWindowPrivateFlags = mWindowPrivateFlags;
            mVisibleInsets = new Rect();
            mWinFrame = new Rect();
            mOverscanInsets = new Rect();
            mContentInsets = new Rect();
            mStableInsets = new Rect();
            mOutsets = new Rect();
            mDispatchedOverscanInsets = new Rect();
            mDispatchedContentInsets = new Rect();
            mDispatchedStableInsets = new Rect();
            mDispatchedOutsets = new Rect();
            mFinalSystemInsets = new Rect();
            mFinalStableInsets = new Rect();
            mBackdropFrame = new Rect();
            mMergedConfiguration = new MergedConfiguration();
            mLayout = new android.view.WindowManager.LayoutParams();
            mLock = new Object();
            mNotifyColorsChanged = new _.Lambda.htiXs5zQinBXs3seMVLgh3fgmis._cls1(this);
            mSurfaceHolder = new _cls1();
            mWindow = new _cls2();
            mDisplayListener = new _cls3();
            mClockFunction = supplier;
            mHandler = handler;
        }
    }

    final class Engine.WallpaperInputEventReceiver extends InputEventReceiver
    {

        public void onInputEvent(InputEvent inputevent, int i)
        {
            boolean flag;
            boolean flag1;
            flag = false;
            flag1 = flag;
            if(!(inputevent instanceof MotionEvent))
                break MISSING_BLOCK_LABEL_45;
            flag1 = flag;
            if((inputevent.getSource() & 2) == 0)
                break MISSING_BLOCK_LABEL_45;
            MotionEvent motionevent = MotionEvent.obtainNoHistory((MotionEvent)inputevent);
            Engine._2D_wrap0(Engine.this, motionevent);
            flag1 = true;
            finishInputEvent(inputevent, flag1);
            return;
            Exception exception;
            exception;
            finishInputEvent(inputevent, false);
            throw exception;
        }

        final Engine this$1;

        public Engine.WallpaperInputEventReceiver(InputChannel inputchannel, Looper looper)
        {
            this$1 = Engine.this;
            super(inputchannel, looper);
        }
    }

    class IWallpaperEngineWrapper extends IWallpaperEngine.Stub
        implements com.android.internal.os.HandlerCaller.Callback
    {

        static HandlerCaller _2D_get0(IWallpaperEngineWrapper iwallpaperenginewrapper)
        {
            return iwallpaperenginewrapper.mCaller;
        }

        public void destroy()
        {
            Message message = mCaller.obtainMessage(20);
            mCaller.sendMessage(message);
        }

        public void dispatchPointer(MotionEvent motionevent)
        {
            if(mEngine != null)
                Engine._2D_wrap0(mEngine, motionevent);
            else
                motionevent.recycle();
        }

        public void dispatchWallpaperCommand(String s, int i, int j, int k, Bundle bundle)
        {
            if(mEngine != null)
                mEngine.mWindow.dispatchWallpaperCommand(s, i, j, k, bundle, false);
        }

        public void executeMessage(Message message)
        {
            message.what;
            JVM INSTR lookupswitch 12: default 112
        //                       10: 141
        //                       20: 193
        //                       30: 216
        //                       40: 232
        //                       10000: 246
        //                       10010: 259
        //                       10020: 286
        //                       10025: 297
        //                       10030: 316
        //                       10035: 140
        //                       10040: 368
        //                       10050: 456;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13
_L11:
            break; /* Loop/switch isn't completed */
_L1:
            Log.w("WallpaperService", (new StringBuilder()).append("Unknown message type ").append(message.what).toString());
_L21:
            return;
_L2:
            try
            {
                mConnection.attachEngine(this);
            }
            // Misplaced declaration of an exception variable
            catch(Message message)
            {
                Log.w("WallpaperService", "Wallpaper host disappeared", message);
                return;
            }
            message = onCreateEngine();
            mEngine = message;
            WallpaperService._2D_get0(WallpaperService.this).add(message);
            message.attach(this);
            return;
_L3:
            WallpaperService._2D_get0(WallpaperService.this).remove(mEngine);
            mEngine.detach();
            return;
_L4:
            mEngine.doDesiredSizeChanged(message.arg1, message.arg2);
            return;
_L5:
            mEngine.doDisplayPaddingChanged((Rect)message.obj);
_L6:
            mEngine.updateSurface(true, false, false);
            continue; /* Loop/switch isn't completed */
_L7:
            Engine engine = mEngine;
            boolean flag;
            if(message.arg1 != 0)
                flag = true;
            else
                flag = false;
            engine.doVisibilityChanged(flag);
            continue; /* Loop/switch isn't completed */
_L8:
            mEngine.doOffsetsChanged(true);
            continue; /* Loop/switch isn't completed */
_L9:
            message = (WallpaperCommand)message.obj;
            mEngine.doCommand(message);
            continue; /* Loop/switch isn't completed */
_L10:
            boolean flag1;
            if(message.arg1 != 0)
                flag1 = true;
            else
                flag1 = false;
            mEngine.mOutsets.set((Rect)message.obj);
            mEngine.updateSurface(true, false, flag1);
            mEngine.doOffsetsChanged(true);
            continue; /* Loop/switch isn't completed */
_L12:
            MotionEvent motionevent;
            boolean flag2;
            boolean flag3;
            flag2 = false;
            flag3 = false;
            motionevent = (MotionEvent)message.obj;
            if(motionevent.getAction() != 2) goto _L15; else goto _L14
_L14:
            message = ((Message) (mEngine.mLock));
            message;
            JVM INSTR monitorenter ;
            if(mEngine.mPendingMove != motionevent) goto _L17; else goto _L16
_L16:
            mEngine.mPendingMove = null;
            flag2 = flag3;
_L19:
            message;
            JVM INSTR monitorexit ;
_L15:
            if(!flag2)
                mEngine.onTouchEvent(motionevent);
            motionevent.recycle();
            continue; /* Loop/switch isn't completed */
_L17:
            flag2 = true;
            if(true) goto _L19; else goto _L18
_L18:
            Exception exception;
            exception;
            throw exception;
_L13:
            if(mConnection != null)
                try
                {
                    mConnection.onWallpaperColorsChanged(mEngine.onComputeColors());
                }
                // Misplaced declaration of an exception variable
                catch(Message message) { }
            if(true) goto _L21; else goto _L20
_L20:
        }

        public void reportShown()
        {
            if(mShownReported)
                break MISSING_BLOCK_LABEL_22;
            mShownReported = true;
            mConnection.engineShown(this);
            return;
            RemoteException remoteexception;
            remoteexception;
            Log.w("WallpaperService", "Wallpaper host disappeared", remoteexception);
            return;
        }

        public void requestWallpaperColors()
        {
            Message message = mCaller.obtainMessage(10050);
            mCaller.sendMessage(message);
        }

        public void setDesiredSize(int i, int j)
        {
            Message message = mCaller.obtainMessageII(30, i, j);
            mCaller.sendMessage(message);
        }

        public void setDisplayPadding(Rect rect)
        {
            rect = mCaller.obtainMessageO(40, rect);
            mCaller.sendMessage(rect);
        }

        public void setVisibility(boolean flag)
        {
            Object obj = mCaller;
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            obj = ((HandlerCaller) (obj)).obtainMessageI(10010, i);
            mCaller.sendMessage(((Message) (obj)));
        }

        private final HandlerCaller mCaller;
        final IWallpaperConnection mConnection;
        final Rect mDisplayPadding = new Rect();
        Engine mEngine;
        final boolean mIsPreview;
        int mReqHeight;
        int mReqWidth;
        boolean mShownReported;
        final IBinder mWindowToken;
        final int mWindowType;
        final WallpaperService this$0;

        IWallpaperEngineWrapper(WallpaperService wallpaperservice1, IWallpaperConnection iwallpaperconnection, IBinder ibinder, int i, boolean flag, int j, 
                int k, Rect rect)
        {
            this$0 = WallpaperService.this;
            super();
            mCaller = new HandlerCaller(wallpaperservice1, wallpaperservice1.getMainLooper(), this, true);
            mConnection = iwallpaperconnection;
            mWindowToken = ibinder;
            mWindowType = i;
            mIsPreview = flag;
            mReqWidth = j;
            mReqHeight = k;
            mDisplayPadding.set(rect);
            wallpaperservice = mCaller.obtainMessage(10);
            mCaller.sendMessage(WallpaperService.this);
        }
    }

    class IWallpaperServiceWrapper extends IWallpaperService.Stub
    {

        public void attach(IWallpaperConnection iwallpaperconnection, IBinder ibinder, int i, boolean flag, int j, int k, Rect rect)
        {
            new IWallpaperEngineWrapper(mTarget, iwallpaperconnection, ibinder, i, flag, j, k, rect);
        }

        private final WallpaperService mTarget;
        final WallpaperService this$0;

        public IWallpaperServiceWrapper(WallpaperService wallpaperservice1)
        {
            this$0 = WallpaperService.this;
            super();
            mTarget = wallpaperservice1;
        }
    }

    static final class WallpaperCommand
    {

        String action;
        Bundle extras;
        boolean sync;
        int x;
        int y;
        int z;

        WallpaperCommand()
        {
        }
    }


    static ArrayList _2D_get0(WallpaperService wallpaperservice)
    {
        return wallpaperservice.mActiveEngines;
    }

    public WallpaperService()
    {
    }

    protected void dump(FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        printwriter.print("State of wallpaper ");
        printwriter.print(this);
        printwriter.println(":");
        for(int i = 0; i < mActiveEngines.size(); i++)
        {
            Engine engine = (Engine)mActiveEngines.get(i);
            printwriter.print("  Engine ");
            printwriter.print(engine);
            printwriter.println(":");
            engine.dump("    ", filedescriptor, printwriter, as);
        }

    }

    public final IBinder onBind(Intent intent)
    {
        return new IWallpaperServiceWrapper(this);
    }

    public void onCreate()
    {
        super.onCreate();
    }

    public abstract Engine onCreateEngine();

    public void onDestroy()
    {
        super.onDestroy();
        for(int i = 0; i < mActiveEngines.size(); i++)
            ((Engine)mActiveEngines.get(i)).detach();

        mActiveEngines.clear();
    }

    static final boolean DEBUG = false;
    private static final int DO_ATTACH = 10;
    private static final int DO_DETACH = 20;
    private static final int DO_SET_DESIRED_SIZE = 30;
    private static final int DO_SET_DISPLAY_PADDING = 40;
    private static final int MSG_REQUEST_WALLPAPER_COLORS = 10050;
    private static final int MSG_TOUCH_EVENT = 10040;
    private static final int MSG_UPDATE_SURFACE = 10000;
    private static final int MSG_VISIBILITY_CHANGED = 10010;
    private static final int MSG_WALLPAPER_COMMAND = 10025;
    private static final int MSG_WALLPAPER_OFFSETS = 10020;
    private static final int MSG_WINDOW_MOVED = 10035;
    private static final int MSG_WINDOW_RESIZED = 10030;
    private static final int NOTIFY_COLORS_RATE_LIMIT_MS = 1000;
    public static final String SERVICE_INTERFACE = "android.service.wallpaper.WallpaperService";
    public static final String SERVICE_META_DATA = "android.service.wallpaper";
    static final String TAG = "WallpaperService";
    private final ArrayList mActiveEngines = new ArrayList();

    // Unreferenced inner class android/service/wallpaper/WallpaperService$Engine$1

/* anonymous class */
    class Engine._cls1 extends BaseSurfaceHolder
    {

        private void prepareToDraw()
        {
            if(Engine._2D_get0(Engine.this) != 3 && Engine._2D_get0(Engine.this) != 4)
                break MISSING_BLOCK_LABEL_41;
            mSession.pokeDrawLock(mWindow);
_L2:
            return;
            RemoteException remoteexception;
            remoteexception;
            if(true) goto _L2; else goto _L1
_L1:
        }

        public boolean isCreating()
        {
            return mIsCreating;
        }

        public Canvas lockCanvas()
        {
            prepareToDraw();
            return super.lockCanvas();
        }

        public Canvas lockCanvas(Rect rect)
        {
            prepareToDraw();
            return super.lockCanvas(rect);
        }

        public Canvas lockHardwareCanvas()
        {
            prepareToDraw();
            return super.lockHardwareCanvas();
        }

        public boolean onAllowLockCanvas()
        {
            return mDrawingAllowed;
        }

        public void onRelayoutContainer()
        {
            Message message = mCaller.obtainMessage(10000);
            mCaller.sendMessage(message);
        }

        public void onUpdateSurface()
        {
            Message message = mCaller.obtainMessage(10000);
            mCaller.sendMessage(message);
        }

        public void setFixedSize(int i, int j)
        {
            if(!mFixedSizeAllowed)
            {
                throw new UnsupportedOperationException("Wallpapers currently only support sizing from layout");
            } else
            {
                super.setFixedSize(i, j);
                return;
            }
        }

        public void setKeepScreenOn(boolean flag)
        {
            throw new UnsupportedOperationException("Wallpapers do not support keep screen on");
        }

        final Engine this$1;

            
            {
                this$1 = Engine.this;
                super();
                mRequestedFormat = 2;
            }
    }


    // Unreferenced inner class android/service/wallpaper/WallpaperService$Engine$2

/* anonymous class */
    class Engine._cls2 extends BaseIWindow
    {

        public void dispatchAppVisibility(boolean flag)
        {
            if(!mIWallpaperEngine.mIsPreview)
            {
                Object obj = mCaller;
                int i;
                if(flag)
                    i = 1;
                else
                    i = 0;
                obj = ((HandlerCaller) (obj)).obtainMessageI(10010, i);
                mCaller.sendMessage(((Message) (obj)));
            }
        }

        public void dispatchWallpaperCommand(String s, int i, int j, int k, Bundle bundle, boolean flag)
        {
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            WallpaperCommand wallpapercommand = JVM INSTR new #53  <Class WallpaperService$WallpaperCommand>;
            wallpapercommand.WallpaperCommand();
            wallpapercommand.action = s;
            wallpapercommand.x = i;
            wallpapercommand.y = j;
            wallpapercommand.z = k;
            wallpapercommand.extras = bundle;
            wallpapercommand.sync = flag;
            s = mCaller.obtainMessage(10025);
            s.obj = wallpapercommand;
            mCaller.sendMessage(s);
            obj;
            JVM INSTR monitorexit ;
            return;
            s;
            throw s;
        }

        public void dispatchWallpaperOffsets(float f, float f1, float f2, float f3, boolean flag)
        {
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            mPendingXOffset = f;
            mPendingYOffset = f1;
            mPendingXOffsetStep = f2;
            mPendingYOffsetStep = f3;
            if(!flag)
                break MISSING_BLOCK_LABEL_58;
            mPendingSync = true;
            if(!mOffsetMessageEnqueued)
            {
                mOffsetMessageEnqueued = true;
                Message message = mCaller.obtainMessage(10020);
                mCaller.sendMessage(message);
            }
            obj;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public void moved(int i, int j)
        {
            Message message = mCaller.obtainMessageII(10035, i, j);
            mCaller.sendMessage(message);
        }

        public void resized(Rect rect, Rect rect1, Rect rect2, Rect rect3, Rect rect4, Rect rect5, boolean flag, 
                MergedConfiguration mergedconfiguration, Rect rect6, boolean flag1, boolean flag2, int i)
        {
            rect = mCaller;
            if(flag)
                i = 1;
            else
                i = 0;
            rect = rect.obtainMessageIO(10030, i, rect5);
            mCaller.sendMessage(rect);
        }

        final Engine this$1;

            
            {
                this$1 = Engine.this;
                super();
            }
    }


    // Unreferenced inner class android/service/wallpaper/WallpaperService$Engine$3

/* anonymous class */
    class Engine._cls3
        implements android.hardware.display.DisplayManager.DisplayListener
    {

        public void onDisplayAdded(int i)
        {
        }

        public void onDisplayChanged(int i)
        {
            if(mDisplay.getDisplayId() == i)
                reportVisibility();
        }

        public void onDisplayRemoved(int i)
        {
        }

        final Engine this$1;

            
            {
                this$1 = Engine.this;
                super();
            }
    }

}
