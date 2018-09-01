// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.autofill;

import android.os.RemoteException;

// Referenced classes of package android.service.autofill:
//            IFillCallback, FillResponse

public final class FillCallback
{

    public FillCallback(IFillCallback ifillcallback, int i)
    {
        mCallback = ifillcallback;
        mRequestId = i;
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

    public void onSuccess(FillResponse fillresponse)
    {
        assertNotCalled();
        mCalled = true;
        if(fillresponse != null)
            fillresponse.setRequestId(mRequestId);
        mCallback.onSuccess(fillresponse);
_L1:
        return;
        fillresponse;
        fillresponse.rethrowAsRuntimeException();
          goto _L1
    }

    private final IFillCallback mCallback;
    private boolean mCalled;
    private final int mRequestId;
}
