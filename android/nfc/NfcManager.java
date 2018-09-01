// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.nfc;

import android.content.Context;

// Referenced classes of package android.nfc:
//            NfcAdapter

public final class NfcManager
{

    public NfcManager(Context context)
    {
        context = context.getApplicationContext();
        if(context == null)
            throw new IllegalArgumentException("context not associated with any application (using a mock context?)");
        try
        {
            context = NfcAdapter.getNfcAdapter(context);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            context = null;
        }
        mAdapter = context;
    }

    public NfcAdapter getDefaultAdapter()
    {
        return mAdapter;
    }

    private final NfcAdapter mAdapter;
}
