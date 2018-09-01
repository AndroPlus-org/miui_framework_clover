// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.os.*;
import android.util.AttributeSet;
import android.view.*;
import android.view.inputmethod.InputMethodManager;
import dalvik.system.BaseDexClassLoader;
import java.io.File;

// Referenced classes of package android.app:
//            Activity

public class NativeActivity extends Activity
    implements android.view.SurfaceHolder.Callback2, android.view.InputQueue.Callback, android.view.ViewTreeObserver.OnGlobalLayoutListener
{
    static class NativeContentView extends View
    {

        NativeActivity mActivity;

        public NativeContentView(Context context)
        {
            super(context);
        }

        public NativeContentView(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
        }
    }


    public NativeActivity()
    {
    }

    private static String getAbsolutePath(File file)
    {
        String s = null;
        if(file != null)
            s = file.getAbsolutePath();
        return s;
    }

    private native String getDlError();

    private native long loadNativeCode(String s, String s1, MessageQueue messagequeue, String s2, String s3, String s4, int i, 
            AssetManager assetmanager, byte abyte0[], ClassLoader classloader, String s5);

    private native void onConfigurationChangedNative(long l);

    private native void onContentRectChangedNative(long l, int i, int j, int k, int i1);

    private native void onInputQueueCreatedNative(long l, long l1);

    private native void onInputQueueDestroyedNative(long l, long l1);

    private native void onLowMemoryNative(long l);

    private native void onPauseNative(long l);

    private native void onResumeNative(long l);

    private native byte[] onSaveInstanceStateNative(long l);

    private native void onStartNative(long l);

    private native void onStopNative(long l);

    private native void onSurfaceChangedNative(long l, Surface surface, int i, int j, int k);

    private native void onSurfaceCreatedNative(long l, Surface surface);

    private native void onSurfaceDestroyedNative(long l);

    private native void onSurfaceRedrawNeededNative(long l, Surface surface);

    private native void onWindowFocusChangedNative(long l, boolean flag);

    private native void unloadNativeCode(long l);

    void hideIme(int i)
    {
        mIMM.hideSoftInputFromWindow(mNativeContentView.getWindowToken(), i);
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
        if(!mDestroyed)
            onConfigurationChangedNative(mNativeHandle);
    }

    protected void onCreate(Bundle bundle)
    {
        String s3;
        String s = "main";
        String s1 = "ANativeActivity_onCreate";
        mIMM = (InputMethodManager)getSystemService(android/view/inputmethod/InputMethodManager);
        getWindow().takeSurface(this);
        getWindow().takeInputQueue(this);
        getWindow().setFormat(4);
        getWindow().setSoftInputMode(16);
        mNativeContentView = new NativeContentView(this);
        mNativeContentView.mActivity = this;
        setContentView(mNativeContentView);
        mNativeContentView.requestFocus();
        mNativeContentView.getViewTreeObserver().addOnGlobalLayoutListener(this);
        Object obj;
        String s4;
        try
        {
            obj = getPackageManager().getActivityInfo(getIntent().getComponent(), 128);
        }
        // Misplaced declaration of an exception variable
        catch(Bundle bundle)
        {
            throw new RuntimeException("Error getting activity info", bundle);
        }
        s3 = s1;
        s4 = s;
        if(((ActivityInfo) (obj)).metaData == null)
            break MISSING_BLOCK_LABEL_182;
        s3 = ((ActivityInfo) (obj)).metaData.getString("android.app.lib_name");
        if(s3 != null)
            s = s3;
        obj = ((ActivityInfo) (obj)).metaData.getString("android.app.func_name");
        s3 = s1;
        s4 = s;
        if(obj != null)
        {
            s3 = ((String) (obj));
            s4 = s;
        }
        BaseDexClassLoader basedexclassloader = (BaseDexClassLoader)getClassLoader();
        String s2 = basedexclassloader.findLibrary(s4);
        if(s2 == null)
            throw new IllegalArgumentException((new StringBuilder()).append("Unable to find native library ").append(s4).append(" using classloader: ").append(basedexclassloader.toString()).toString());
        byte abyte0[];
        if(bundle != null)
            abyte0 = bundle.getByteArray("android:native_state");
        else
            abyte0 = null;
        mNativeHandle = loadNativeCode(s2, s3, Looper.myQueue(), getAbsolutePath(getFilesDir()), getAbsolutePath(getObbDir()), getAbsolutePath(getExternalFilesDir(null)), android.os.Build.VERSION.SDK_INT, getAssets(), abyte0, basedexclassloader, basedexclassloader.getLdLibraryPath());
        if(mNativeHandle == 0L)
        {
            throw new UnsatisfiedLinkError((new StringBuilder()).append("Unable to load native library \"").append(s2).append("\": ").append(getDlError()).toString());
        } else
        {
            super.onCreate(bundle);
            return;
        }
    }

    protected void onDestroy()
    {
        mDestroyed = true;
        if(mCurSurfaceHolder != null)
        {
            onSurfaceDestroyedNative(mNativeHandle);
            mCurSurfaceHolder = null;
        }
        if(mCurInputQueue != null)
        {
            onInputQueueDestroyedNative(mNativeHandle, mCurInputQueue.getNativePtr());
            mCurInputQueue = null;
        }
        unloadNativeCode(mNativeHandle);
        super.onDestroy();
    }

    public void onGlobalLayout()
    {
        int i;
        int j;
        mNativeContentView.getLocationInWindow(mLocation);
        i = mNativeContentView.getWidth();
        j = mNativeContentView.getHeight();
        break MISSING_BLOCK_LABEL_27;
        if(mLocation[0] != mLastContentX || mLocation[1] != mLastContentY || i != mLastContentWidth || j != mLastContentHeight)
        {
            mLastContentX = mLocation[0];
            mLastContentY = mLocation[1];
            mLastContentWidth = i;
            mLastContentHeight = j;
            if(!mDestroyed)
                onContentRectChangedNative(mNativeHandle, mLastContentX, mLastContentY, mLastContentWidth, mLastContentHeight);
        }
        return;
    }

    public void onInputQueueCreated(InputQueue inputqueue)
    {
        if(!mDestroyed)
        {
            mCurInputQueue = inputqueue;
            onInputQueueCreatedNative(mNativeHandle, inputqueue.getNativePtr());
        }
    }

    public void onInputQueueDestroyed(InputQueue inputqueue)
    {
        if(!mDestroyed)
        {
            onInputQueueDestroyedNative(mNativeHandle, inputqueue.getNativePtr());
            mCurInputQueue = null;
        }
    }

    public void onLowMemory()
    {
        super.onLowMemory();
        if(!mDestroyed)
            onLowMemoryNative(mNativeHandle);
    }

    protected void onPause()
    {
        super.onPause();
        onPauseNative(mNativeHandle);
    }

    protected void onResume()
    {
        super.onResume();
        onResumeNative(mNativeHandle);
    }

    protected void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        byte abyte0[] = onSaveInstanceStateNative(mNativeHandle);
        if(abyte0 != null)
            bundle.putByteArray("android:native_state", abyte0);
    }

    protected void onStart()
    {
        super.onStart();
        onStartNative(mNativeHandle);
    }

    protected void onStop()
    {
        super.onStop();
        onStopNative(mNativeHandle);
    }

    public void onWindowFocusChanged(boolean flag)
    {
        super.onWindowFocusChanged(flag);
        if(!mDestroyed)
            onWindowFocusChangedNative(mNativeHandle, flag);
    }

    void setWindowFlags(int i, int j)
    {
        getWindow().setFlags(i, j);
    }

    void setWindowFormat(int i)
    {
        getWindow().setFormat(i);
    }

    void showIme(int i)
    {
        mIMM.showSoftInput(mNativeContentView, i);
    }

    public void surfaceChanged(SurfaceHolder surfaceholder, int i, int j, int k)
    {
        if(!mDestroyed)
        {
            mCurSurfaceHolder = surfaceholder;
            onSurfaceChangedNative(mNativeHandle, surfaceholder.getSurface(), i, j, k);
        }
    }

    public void surfaceCreated(SurfaceHolder surfaceholder)
    {
        if(!mDestroyed)
        {
            mCurSurfaceHolder = surfaceholder;
            onSurfaceCreatedNative(mNativeHandle, surfaceholder.getSurface());
        }
    }

    public void surfaceDestroyed(SurfaceHolder surfaceholder)
    {
        mCurSurfaceHolder = null;
        if(!mDestroyed)
            onSurfaceDestroyedNative(mNativeHandle);
    }

    public void surfaceRedrawNeeded(SurfaceHolder surfaceholder)
    {
        if(!mDestroyed)
        {
            mCurSurfaceHolder = surfaceholder;
            onSurfaceRedrawNeededNative(mNativeHandle, surfaceholder.getSurface());
        }
    }

    private static final String KEY_NATIVE_SAVED_STATE = "android:native_state";
    public static final String META_DATA_FUNC_NAME = "android.app.func_name";
    public static final String META_DATA_LIB_NAME = "android.app.lib_name";
    private InputQueue mCurInputQueue;
    private SurfaceHolder mCurSurfaceHolder;
    private boolean mDestroyed;
    private boolean mDispatchingUnhandledKey;
    private InputMethodManager mIMM;
    int mLastContentHeight;
    int mLastContentWidth;
    int mLastContentX;
    int mLastContentY;
    final int mLocation[] = new int[2];
    private NativeContentView mNativeContentView;
    private long mNativeHandle;
}
