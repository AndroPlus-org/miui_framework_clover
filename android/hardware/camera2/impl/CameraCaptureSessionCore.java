// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.impl;


public interface CameraCaptureSessionCore
{

    public abstract CameraDeviceImpl.StateCallbackKK getDeviceStateCallback();

    public abstract boolean isAborting();

    public abstract void replaceSessionClose();
}
