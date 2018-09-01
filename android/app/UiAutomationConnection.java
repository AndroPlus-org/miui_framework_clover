// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.accessibilityservice.IAccessibilityServiceClient;
import android.content.pm.IPackageManager;
import android.graphics.Bitmap;
import android.hardware.input.InputManager;
import android.os.*;
import android.util.Log;
import android.view.*;
import android.view.accessibility.IAccessibilityManager;
import java.io.*;
import libcore.io.IoUtils;

public final class UiAutomationConnection extends IUiAutomationConnection.Stub
{
    public class Repeater
        implements Runnable
    {

        public void run()
        {
            byte abyte0[] = new byte[8192];
_L1:
            int i = readFrom.read(abyte0);
            if(i < 0)
            {
                IoUtils.closeQuietly(readFrom);
                IoUtils.closeQuietly(writeTo);
                return;
            }
            writeTo.write(abyte0, 0, i);
            writeTo.flush();
              goto _L1
            Object obj;
            obj;
            RuntimeException runtimeexception = JVM INSTR new #53  <Class RuntimeException>;
            runtimeexception.RuntimeException("Error while reading/writing ", ((Throwable) (obj)));
            throw runtimeexception;
            obj;
            IoUtils.closeQuietly(readFrom);
            IoUtils.closeQuietly(writeTo);
            throw obj;
        }

        private final InputStream readFrom;
        final UiAutomationConnection this$0;
        private final OutputStream writeTo;

        public Repeater(InputStream inputstream, OutputStream outputstream)
        {
            this$0 = UiAutomationConnection.this;
            super();
            readFrom = inputstream;
            writeTo = outputstream;
        }
    }


    public UiAutomationConnection()
    {
        mInitialFrozenRotation = -1;
    }

    private boolean isConnectedLocked()
    {
        boolean flag;
        if(mClient != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private void registerUiTestAutomationServiceLocked(IAccessibilityServiceClient iaccessibilityserviceclient, int i)
    {
        IAccessibilityManager iaccessibilitymanager = android.view.accessibility.IAccessibilityManager.Stub.asInterface(ServiceManager.getService("accessibility"));
        AccessibilityServiceInfo accessibilityserviceinfo = new AccessibilityServiceInfo();
        accessibilityserviceinfo.eventTypes = -1;
        accessibilityserviceinfo.feedbackType = 16;
        accessibilityserviceinfo.flags = accessibilityserviceinfo.flags | 0x10012;
        accessibilityserviceinfo.setCapabilities(15);
        try
        {
            iaccessibilitymanager.registerUiTestAutomationService(mToken, iaccessibilityserviceclient, accessibilityserviceinfo, i);
            mClient = iaccessibilityserviceclient;
            return;
        }
        // Misplaced declaration of an exception variable
        catch(IAccessibilityServiceClient iaccessibilityserviceclient)
        {
            throw new IllegalStateException("Error while registering UiTestAutomationService.", iaccessibilityserviceclient);
        }
    }

    private void restoreRotationStateLocked()
    {
        if(mInitialFrozenRotation == -1)
            break MISSING_BLOCK_LABEL_22;
        mWindowManager.freezeRotation(mInitialFrozenRotation);
_L1:
        return;
        try
        {
            mWindowManager.thawRotation();
        }
        catch(RemoteException remoteexception) { }
          goto _L1
    }

    private void storeRotationStateLocked()
    {
        if(mWindowManager.isRotationFrozen())
            mInitialFrozenRotation = mWindowManager.getDefaultDisplayRotation();
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private void throwIfCalledByNotTrustedUidLocked()
    {
        int i = Binder.getCallingUid();
        if(i != mOwningUid && mOwningUid != 1000 && i != 0)
            throw new SecurityException("Calling from not trusted UID!");
        else
            return;
    }

    private void throwIfNotConnectedLocked()
    {
        if(!isConnectedLocked())
            throw new IllegalStateException("Not connected!");
        else
            return;
    }

    private void throwIfShutdownLocked()
    {
        if(mIsShutdown)
            throw new IllegalStateException("Connection shutdown!");
        else
            return;
    }

    private void unregisterUiTestAutomationServiceLocked()
    {
        IAccessibilityManager iaccessibilitymanager = android.view.accessibility.IAccessibilityManager.Stub.asInterface(ServiceManager.getService("accessibility"));
        try
        {
            iaccessibilitymanager.unregisterUiTestAutomationService(mClient);
            mClient = null;
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw new IllegalStateException("Error while unregistering UiTestAutomationService", remoteexception);
        }
    }

    public void clearWindowAnimationFrameStats()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        throwIfCalledByNotTrustedUidLocked();
        throwIfShutdownLocked();
        throwIfNotConnectedLocked();
        obj;
        JVM INSTR monitorexit ;
        long l = Binder.clearCallingIdentity();
        SurfaceControl.clearAnimationFrameStats();
        Binder.restoreCallingIdentity(l);
        return;
        Exception exception1;
        exception1;
        throw exception1;
        Exception exception;
        exception;
        Binder.restoreCallingIdentity(l);
        throw exception;
    }

    public boolean clearWindowContentFrameStats(int i)
        throws RemoteException
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        throwIfCalledByNotTrustedUidLocked();
        throwIfShutdownLocked();
        throwIfNotConnectedLocked();
        obj;
        JVM INSTR monitorexit ;
        int j;
        long l;
        j = UserHandle.getCallingUserId();
        l = Binder.clearCallingIdentity();
        obj = mAccessibilityManager.getWindowToken(i, j);
        if(obj == null)
        {
            Binder.restoreCallingIdentity(l);
            return false;
        }
        break MISSING_BLOCK_LABEL_60;
        Exception exception1;
        exception1;
        throw exception1;
        boolean flag = mWindowManager.clearWindowContentFrameStats(((android.os.IBinder) (obj)));
        Binder.restoreCallingIdentity(l);
        return flag;
        Exception exception;
        exception;
        Binder.restoreCallingIdentity(l);
        throw exception;
    }

    public void connect(IAccessibilityServiceClient iaccessibilityserviceclient, int i)
    {
label0:
        {
            if(iaccessibilityserviceclient == null)
                throw new IllegalArgumentException("Client cannot be null!");
            synchronized(mLock)
            {
                throwIfShutdownLocked();
                if(isConnectedLocked())
                {
                    iaccessibilityserviceclient = JVM INSTR new #116 <Class IllegalStateException>;
                    iaccessibilityserviceclient.IllegalStateException("Already connected.");
                    throw iaccessibilityserviceclient;
                }
                break label0;
            }
        }
        mOwningUid = Binder.getCallingUid();
        registerUiTestAutomationServiceLocked(iaccessibilityserviceclient, i);
        storeRotationStateLocked();
        obj;
        JVM INSTR monitorexit ;
    }

    public void disconnect()
    {
        synchronized(mLock)
        {
            throwIfCalledByNotTrustedUidLocked();
            throwIfShutdownLocked();
            if(!isConnectedLocked())
            {
                IllegalStateException illegalstateexception = JVM INSTR new #116 <Class IllegalStateException>;
                illegalstateexception.IllegalStateException("Already disconnected.");
                throw illegalstateexception;
            }
            break MISSING_BLOCK_LABEL_39;
        }
        mOwningUid = -1;
        unregisterUiTestAutomationServiceLocked();
        restoreRotationStateLocked();
        obj;
        JVM INSTR monitorexit ;
    }

    public void executeShellCommand(final String readFromProcess, final ParcelFileDescriptor sink, final ParcelFileDescriptor source)
        throws RemoteException
    {
        final Object writeToProcess = mLock;
        writeToProcess;
        JVM INSTR monitorenter ;
        throwIfCalledByNotTrustedUidLocked();
        throwIfShutdownLocked();
        throwIfNotConnectedLocked();
        writeToProcess;
        JVM INSTR monitorexit ;
        final Process process;
        try
        {
            process = Runtime.getRuntime().exec(readFromProcess);
        }
        // Misplaced declaration of an exception variable
        catch(final ParcelFileDescriptor sink)
        {
            throw new RuntimeException((new StringBuilder()).append("Error running shell command '").append(readFromProcess).append("'").toString(), sink);
        }
        if(sink != null)
        {
            readFromProcess = new Thread(new Repeater(process.getInputStream(), new FileOutputStream(sink.getFileDescriptor())));
            readFromProcess.start();
        } else
        {
            readFromProcess = null;
        }
        if(source != null)
        {
            writeToProcess = process.getOutputStream();
            writeToProcess = new Thread(new Repeater(new FileInputStream(source.getFileDescriptor()), ((OutputStream) (writeToProcess))));
            ((Thread) (writeToProcess)).start();
        } else
        {
            writeToProcess = null;
        }
        (new Thread(new Runnable() {

            public void run()
            {
                try
                {
                    if(writeToProcess != null)
                        writeToProcess.join();
                    if(readFromProcess != null)
                        readFromProcess.join();
                }
                catch(InterruptedException interruptedexception)
                {
                    Log.e("UiAutomationConnection", "At least one of the threads was interrupted");
                }
                IoUtils.closeQuietly(sink);
                IoUtils.closeQuietly(source);
                process.destroy();
            }

            final UiAutomationConnection this$0;
            final Process val$process;
            final Thread val$readFromProcess;
            final ParcelFileDescriptor val$sink;
            final ParcelFileDescriptor val$source;
            final Thread val$writeToProcess;

            
            {
                this$0 = UiAutomationConnection.this;
                writeToProcess = thread;
                readFromProcess = thread1;
                sink = parcelfiledescriptor;
                source = parcelfiledescriptor1;
                process = process1;
                super();
            }
        }
)).start();
        return;
        readFromProcess;
        throw readFromProcess;
    }

    public WindowAnimationFrameStats getWindowAnimationFrameStats()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        throwIfCalledByNotTrustedUidLocked();
        throwIfShutdownLocked();
        throwIfNotConnectedLocked();
        obj;
        JVM INSTR monitorexit ;
        long l = Binder.clearCallingIdentity();
        obj = JVM INSTR new #297 <Class WindowAnimationFrameStats>;
        ((WindowAnimationFrameStats) (obj)).WindowAnimationFrameStats();
        SurfaceControl.getAnimationFrameStats(((WindowAnimationFrameStats) (obj)));
        Binder.restoreCallingIdentity(l);
        return ((WindowAnimationFrameStats) (obj));
        Exception exception1;
        exception1;
        throw exception1;
        Exception exception;
        exception;
        Binder.restoreCallingIdentity(l);
        throw exception;
    }

    public WindowContentFrameStats getWindowContentFrameStats(int i)
        throws RemoteException
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        throwIfCalledByNotTrustedUidLocked();
        throwIfShutdownLocked();
        throwIfNotConnectedLocked();
        obj;
        JVM INSTR monitorexit ;
        int j;
        long l;
        j = UserHandle.getCallingUserId();
        l = Binder.clearCallingIdentity();
        Object obj1 = mAccessibilityManager.getWindowToken(i, j);
        if(obj1 == null)
        {
            Binder.restoreCallingIdentity(l);
            return null;
        }
        break MISSING_BLOCK_LABEL_62;
        obj1;
        throw obj1;
        obj1 = mWindowManager.getWindowContentFrameStats(((android.os.IBinder) (obj1)));
        Binder.restoreCallingIdentity(l);
        return ((WindowContentFrameStats) (obj1));
        Exception exception;
        exception;
        Binder.restoreCallingIdentity(l);
        throw exception;
    }

    public void grantRuntimePermission(String s, String s1, int i)
        throws RemoteException
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        throwIfCalledByNotTrustedUidLocked();
        throwIfShutdownLocked();
        throwIfNotConnectedLocked();
        obj;
        JVM INSTR monitorexit ;
        long l = Binder.clearCallingIdentity();
        mPackageManager.grantRuntimePermission(s, s1, i);
        Binder.restoreCallingIdentity(l);
        return;
        s;
        throw s;
        s;
        Binder.restoreCallingIdentity(l);
        throw s;
    }

    public boolean injectInputEvent(InputEvent inputevent, boolean flag)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        throwIfCalledByNotTrustedUidLocked();
        throwIfShutdownLocked();
        throwIfNotConnectedLocked();
        obj;
        JVM INSTR monitorexit ;
        long l;
        byte byte0;
        if(flag)
            byte0 = 2;
        else
            byte0 = 0;
        l = Binder.clearCallingIdentity();
        flag = InputManager.getInstance().injectInputEvent(inputevent, byte0);
        Binder.restoreCallingIdentity(l);
        return flag;
        inputevent;
        throw inputevent;
        inputevent;
        Binder.restoreCallingIdentity(l);
        throw inputevent;
    }

    public void revokeRuntimePermission(String s, String s1, int i)
        throws RemoteException
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        throwIfCalledByNotTrustedUidLocked();
        throwIfShutdownLocked();
        throwIfNotConnectedLocked();
        obj;
        JVM INSTR monitorexit ;
        long l = Binder.clearCallingIdentity();
        mPackageManager.revokeRuntimePermission(s, s1, i);
        Binder.restoreCallingIdentity(l);
        return;
        s;
        throw s;
        s;
        Binder.restoreCallingIdentity(l);
        throw s;
    }

    public boolean setRotation(int i)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        throwIfCalledByNotTrustedUidLocked();
        throwIfShutdownLocked();
        throwIfNotConnectedLocked();
        obj;
        JVM INSTR monitorexit ;
        long l;
        l = Binder.clearCallingIdentity();
        if(i != -2)
            break MISSING_BLOCK_LABEL_53;
        Object obj1;
        try
        {
            mWindowManager.thawRotation();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj1)
        {
            Binder.restoreCallingIdentity(l);
            return false;
        }
        Binder.restoreCallingIdentity(l);
        return true;
        obj1;
        throw obj1;
        mWindowManager.freezeRotation(i);
        if(false)
            ;
        else
            break MISSING_BLOCK_LABEL_40;
        obj1;
        Binder.restoreCallingIdentity(l);
        throw obj1;
    }

    public void shutdown()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(isConnectedLocked())
            throwIfCalledByNotTrustedUidLocked();
        throwIfShutdownLocked();
        mIsShutdown = true;
        if(isConnectedLocked())
            disconnect();
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public Bitmap takeScreenshot(int i, int j)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        throwIfCalledByNotTrustedUidLocked();
        throwIfShutdownLocked();
        throwIfNotConnectedLocked();
        obj;
        JVM INSTR monitorexit ;
        long l = Binder.clearCallingIdentity();
        Bitmap bitmap = SurfaceControl.screenshot(i, j);
        Binder.restoreCallingIdentity(l);
        return bitmap;
        Exception exception;
        exception;
        throw exception;
        exception;
        Binder.restoreCallingIdentity(l);
        throw exception;
    }

    private static final int INITIAL_FROZEN_ROTATION_UNSPECIFIED = -1;
    private static final String TAG = "UiAutomationConnection";
    private final IAccessibilityManager mAccessibilityManager = android.view.accessibility.IAccessibilityManager.Stub.asInterface(ServiceManager.getService("accessibility"));
    private IAccessibilityServiceClient mClient;
    private int mInitialFrozenRotation;
    private boolean mIsShutdown;
    private final Object mLock = new Object();
    private int mOwningUid;
    private final IPackageManager mPackageManager = android.content.pm.IPackageManager.Stub.asInterface(ServiceManager.getService("package"));
    private final Binder mToken = new Binder();
    private final IWindowManager mWindowManager = android.view.IWindowManager.Stub.asInterface(ServiceManager.getService("window"));
}
