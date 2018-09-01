// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.autofill;

import android.os.RemoteException;

// Referenced classes of package android.service.autofill:
//            ISaveCallback

public final class SaveCallback
{

    SaveCallback(ISaveCallback isavecallback)
    {
        mCallback = isavecallback;
    }

    private void assertNotCalled()
    {
        if(mCalled)
            throw new IllegalStateException("Already called");
        else
            return;
    }

    public void onFailure(CharSequence charsequence)
    {
        assertNotCalled();
        mCalled = true;
        mCallback.onFailure(charsequence);
_L1:
        return;
        charsequence;
        charsequence.rethrowAsRuntimeException();
          goto _L1
    }

    public void onSuccess()
    {
        assertNotCalled();
        mCalled = true;
        mCallback.onSuccess();
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        remoteexception.rethrowAsRuntimeException();
          goto _L1
    }

    private final ISaveCallback mCallback;
    private boolean mCalled;
}
