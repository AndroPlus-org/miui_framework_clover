// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.view;

import android.graphics.Rect;
import android.hardware.input.InputManager;
import android.os.*;
import android.util.MergedConfiguration;
import android.view.DragEvent;
import android.view.IWindowSession;
import com.android.internal.os.IResultReceiver;

public class BaseIWindow extends android.view.IWindow.Stub
{

    public BaseIWindow()
    {
    }

    public void closeSystemDialogs(String s)
    {
    }

    public void dispatchAppVisibility(boolean flag)
    {
    }

    public void dispatchDragEvent(DragEvent dragevent)
    {
        if(dragevent.getAction() != 3)
            break MISSING_BLOCK_LABEL_19;
        mSession.reportDropResult(this, false);
_L2:
        return;
        dragevent;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void dispatchGetNewSurface()
    {
    }

    public void dispatchPointerCaptureChanged(boolean flag)
    {
    }

    public void dispatchSystemUiVisibilityChanged(int i, int j, int k, int l)
    {
        mSeq = i;
    }

    public void dispatchWallpaperCommand(String s, int i, int j, int k, Bundle bundle, boolean flag)
    {
        if(!flag)
            break MISSING_BLOCK_LABEL_19;
        mSession.wallpaperCommandComplete(asBinder(), null);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void dispatchWallpaperOffsets(float f, float f1, float f2, float f3, boolean flag)
    {
        if(!flag)
            break MISSING_BLOCK_LABEL_18;
        mSession.wallpaperOffsetsComplete(asBinder());
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void dispatchWindowShown()
    {
    }

    public void executeCommand(String s, String s1, ParcelFileDescriptor parcelfiledescriptor)
    {
    }

    public void moved(int i, int j)
    {
    }

    public void requestAppKeyboardShortcuts(IResultReceiver iresultreceiver, int i)
    {
    }

    public void resized(Rect rect, Rect rect1, Rect rect2, Rect rect3, Rect rect4, Rect rect5, boolean flag, 
            MergedConfiguration mergedconfiguration, Rect rect6, boolean flag1, boolean flag2, int i)
    {
        if(!flag)
            break MISSING_BLOCK_LABEL_15;
        mSession.finishDrawing(this);
_L2:
        return;
        rect;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void setSession(IWindowSession iwindowsession)
    {
        mSession = iwindowsession;
    }

    public void updatePointerIcon(float f, float f1)
    {
        InputManager.getInstance().setPointerIconType(1);
    }

    public void windowFocusChanged(boolean flag, boolean flag1)
    {
    }

    public int mSeq;
    private IWindowSession mSession;
}
