// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.legacy;

import android.hardware.Camera;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.utils.ParamsUtils;
import android.util.Log;
import com.android.internal.util.Preconditions;
import java.util.Objects;

public class LegacyFocusStateMapper
{

    static String _2D_get0()
    {
        return TAG;
    }

    static int _2D_get1(LegacyFocusStateMapper legacyfocusstatemapper)
    {
        return legacyfocusstatemapper.mAfRun;
    }

    static Object _2D_get2(LegacyFocusStateMapper legacyfocusstatemapper)
    {
        return legacyfocusstatemapper.mLock;
    }

    static int _2D_set0(LegacyFocusStateMapper legacyfocusstatemapper, int i)
    {
        legacyfocusstatemapper.mAfState = i;
        return i;
    }

    public LegacyFocusStateMapper(Camera camera)
    {
        mAfStatePrevious = 0;
        mAfModePrevious = null;
        mAfRun = 0;
        mAfState = 0;
        mCamera = (Camera)Preconditions.checkNotNull(camera, "camera must not be null");
    }

    private static String afStateToString(int i)
    {
        switch(i)
        {
        default:
            return (new StringBuilder()).append("UNKNOWN(").append(i).append(")").toString();

        case 3: // '\003'
            return "ACTIVE_SCAN";

        case 4: // '\004'
            return "FOCUSED_LOCKED";

        case 0: // '\0'
            return "INACTIVE";

        case 5: // '\005'
            return "NOT_FOCUSED_LOCKED";

        case 2: // '\002'
            return "PASSIVE_FOCUSED";

        case 1: // '\001'
            return "PASSIVE_SCAN";

        case 6: // '\006'
            return "PASSIVE_UNFOCUSED";
        }
    }

    public void mapResultTriggers(CameraMetadataNative camerametadatanative)
    {
        Preconditions.checkNotNull(camerametadatanative, "result must not be null");
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        int i = mAfState;
        obj;
        JVM INSTR monitorexit ;
        camerametadatanative.set(CaptureResult.CONTROL_AF_STATE, Integer.valueOf(i));
        mAfStatePrevious = i;
        return;
        camerametadatanative;
        throw camerametadatanative;
    }

    public void processRequestTriggers(final CaptureRequest afMode, android.hardware.Camera.Parameters parameters)
    {
        final int currentAfRun;
        Preconditions.checkNotNull(afMode, "captureRequest must not be null");
        currentAfRun = ((Integer)ParamsUtils.getOrDefault(afMode, CaptureRequest.CONTROL_AF_TRIGGER, Integer.valueOf(0))).intValue();
        afMode = parameters.getFocusMode();
        if(Objects.equals(mAfModePrevious, afMode))
            break MISSING_BLOCK_LABEL_72;
        parameters = ((android.hardware.Camera.Parameters) (mLock));
        parameters;
        JVM INSTR monitorenter ;
        mAfRun = mAfRun + 1;
        mAfState = 0;
        parameters;
        JVM INSTR monitorexit ;
        mCamera.cancelAutoFocus();
        mAfModePrevious = afMode;
        parameters = ((android.hardware.Camera.Parameters) (mLock));
        parameters;
        JVM INSTR monitorenter ;
        final int currentAfRun = mAfRun;
        parameters;
        JVM INSTR monitorexit ;
          goto _L1
        currentAfRun;
        JVM INSTR tableswitch 0 2: default 148
    //                   0 174
    //                   1 215
    //                   2 323;
           goto _L2 _L3 _L4 _L5
_L3:
        break MISSING_BLOCK_LABEL_174;
_L2:
        Log.w(TAG, (new StringBuilder()).append("processRequestTriggers - ignoring unknown control.afTrigger = ").append(currentAfRun).toString());
_L8:
        return;
        afMode;
        throw afMode;
        afMode;
        throw afMode;
_L1:
        parameters = new android.hardware.Camera.AutoFocusMoveCallback() {

            public void onAutoFocusMoving(boolean flag, Camera camera)
            {
                camera = ((Camera) (LegacyFocusStateMapper._2D_get2(LegacyFocusStateMapper.this)));
                camera;
                JVM INSTR monitorenter ;
                int i = LegacyFocusStateMapper._2D_get1(LegacyFocusStateMapper.this);
                if(currentAfRun == i)
                    break MISSING_BLOCK_LABEL_67;
                String s = LegacyFocusStateMapper._2D_get0();
                StringBuilder stringbuilder = JVM INSTR new #45  <Class StringBuilder>;
                stringbuilder.StringBuilder();
                Log.d(s, stringbuilder.append("onAutoFocusMoving - ignoring move callbacks from old af run").append(currentAfRun).toString());
                camera;
                JVM INSTR monitorexit ;
                return;
                int j;
                String s1;
                if(flag)
                    j = 1;
                else
                    j = 2;
                s1 = afMode;
                if(!s1.equals("continuous-picture")) goto _L2; else goto _L1
_L1:
                LegacyFocusStateMapper._2D_set0(LegacyFocusStateMapper.this, j);
                camera;
                JVM INSTR monitorexit ;
                return;
_L2:
                if(s1.equals("continuous-video")) goto _L1; else goto _L3
_L3:
                String s2 = LegacyFocusStateMapper._2D_get0();
                StringBuilder stringbuilder1 = JVM INSTR new #45  <Class StringBuilder>;
                stringbuilder1.StringBuilder();
                Log.w(s2, stringbuilder1.append("onAutoFocus - got unexpected onAutoFocus in mode ").append(afMode).toString());
                  goto _L1
                Exception exception1;
                exception1;
                throw exception1;
            }

            final LegacyFocusStateMapper this$0;
            final String val$afMode;
            final int val$currentAfRun;

            
            {
                this$0 = LegacyFocusStateMapper.this;
                currentAfRun = i;
                afMode = s;
                super();
            }
        }
;
        if(afMode.equals("auto") || afMode.equals("macro") || afMode.equals("continuous-picture") || afMode.equals("continuous-video"))
            mCamera.setAutoFocusMoveCallback(parameters);
        break MISSING_BLOCK_LABEL_121;
_L6:
        parameters = ((android.hardware.Camera.Parameters) (mLock));
        parameters;
        JVM INSTR monitorenter ;
        currentAfRun = mAfRun + 1;
        mAfRun = currentAfRun;
        mAfState = currentAfRun;
        parameters;
        JVM INSTR monitorexit ;
        if(currentAfRun != 0)
            mCamera.autoFocus(new android.hardware.Camera.AutoFocusCallback() {

                public void onAutoFocus(boolean flag, Camera camera)
                {
                    camera = ((Camera) (LegacyFocusStateMapper._2D_get2(LegacyFocusStateMapper.this)));
                    camera;
                    JVM INSTR monitorenter ;
                    int i = LegacyFocusStateMapper._2D_get1(LegacyFocusStateMapper.this);
                    if(i == currentAfRun)
                        break MISSING_BLOCK_LABEL_62;
                    Log.d(LegacyFocusStateMapper._2D_get0(), String.format("onAutoFocus - ignoring AF callback (old run %d, new run %d)", new Object[] {
                        Integer.valueOf(currentAfRun), Integer.valueOf(i)
                    }));
                    camera;
                    JVM INSTR monitorexit ;
                    return;
                    byte byte0;
                    String s;
                    if(flag)
                        byte0 = 4;
                    else
                        byte0 = 5;
                    s = afMode;
                    if(!s.equals("auto")) goto _L2; else goto _L1
_L1:
                    LegacyFocusStateMapper._2D_set0(LegacyFocusStateMapper.this, byte0);
                    camera;
                    JVM INSTR monitorexit ;
                    return;
_L2:
                    if(s.equals("continuous-picture") || s.equals("continuous-video") || s.equals("macro")) goto _L1; else goto _L3
_L3:
                    String s1 = LegacyFocusStateMapper._2D_get0();
                    StringBuilder stringbuilder = JVM INSTR new #81  <Class StringBuilder>;
                    stringbuilder.StringBuilder();
                    Log.w(s1, stringbuilder.append("onAutoFocus - got unexpected onAutoFocus in mode ").append(afMode).toString());
                      goto _L1
                    Exception exception1;
                    exception1;
                    throw exception1;
                }

                final LegacyFocusStateMapper this$0;
                final String val$afMode;
                final int val$currentAfRun;

            
            {
                this$0 = LegacyFocusStateMapper.this;
                currentAfRun = i;
                afMode = s;
                super();
            }
            }
);
        continue; /* Loop/switch isn't completed */
_L4:
        if(afMode.equals("auto") || afMode.equals("macro"))
            currentAfRun = 3;
        else
        if(afMode.equals("continuous-picture") || afMode.equals("continuous-video"))
            currentAfRun = 1;
        else
            currentAfRun = 0;
          goto _L6
        afMode;
        throw afMode;
_L5:
        afMode = ((CaptureRequest) (mLock));
        afMode;
        JVM INSTR monitorenter ;
        parameters = ((android.hardware.Camera.Parameters) (mLock));
        parameters;
        JVM INSTR monitorenter ;
        mAfRun = mAfRun + 1;
        mAfState = 0;
        parameters;
        JVM INSTR monitorexit ;
        mCamera.cancelAutoFocus();
        afMode;
        JVM INSTR monitorexit ;
        if(true) goto _L8; else goto _L7
_L7:
        Exception exception;
        exception;
        parameters;
        JVM INSTR monitorexit ;
        throw exception;
        parameters;
        afMode;
        JVM INSTR monitorexit ;
        throw parameters;
    }

    private static final boolean DEBUG = false;
    private static String TAG = "LegacyFocusStateMapper";
    private String mAfModePrevious;
    private int mAfRun;
    private int mAfState;
    private int mAfStatePrevious;
    private final Camera mCamera;
    private final Object mLock = new Object();

}
