// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.securityspace;

import android.app.Fragment;
import android.content.*;
import android.os.Bundle;
import android.os.UserHandle;
import android.util.Log;
import miui.app.Activity;

// Referenced classes of package miui.securityspace:
//            CrossUserUtils

public class CrossUserPickerActivity extends Activity
{
    class CrossUserContextWrapper extends ContextWrapper
    {

        public ContentResolver getContentResolver()
        {
            return mBase.getContentResolverForUser(mCrossUser);
        }

        Context mBase;
        UserHandle mCrossUser;
        final CrossUserPickerActivity this$0;

        public CrossUserContextWrapper(Context context, UserHandle userhandle)
        {
            this$0 = CrossUserPickerActivity.this;
            super(context);
            mBase = context;
            mCrossUser = userhandle;
        }
    }


    public CrossUserPickerActivity()
    {
    }

    private boolean validateCallingPackage()
    {
        return getPackageName().equals(getCallingPackage()) || CrossUserUtils.checkUidPermission(this, getCallingPackage());
    }

    private int validateCrossUser()
    {
        if(getIntent() == null)
            return -1;
        int i = getIntent().getIntExtra("android.intent.extra.picked_user_id", -1);
        if(validateCallingPackage())
            return i;
        else
            return -1;
    }

    public Context getApplicationContext()
    {
        if(!isCrossUserPick())
            break MISSING_BLOCK_LABEL_84;
        if(mCrossUserContextWrapper != null) goto _L2; else goto _L1
_L1:
        Object obj = mLockObject;
        obj;
        JVM INSTR monitorenter ;
        if(mCrossUserContextWrapper == null)
        {
            CrossUserContextWrapper crossusercontextwrapper = JVM INSTR new #6   <Class CrossUserPickerActivity$CrossUserContextWrapper>;
            Context context = super.getApplicationContext();
            UserHandle userhandle = JVM INSTR new #78  <Class UserHandle>;
            userhandle.UserHandle(validateCrossUser());
            crossusercontextwrapper.this. CrossUserContextWrapper(context, userhandle);
            mCrossUserContextWrapper = crossusercontextwrapper;
        }
        obj;
        JVM INSTR monitorexit ;
_L2:
        Log.d("CrossUserPickerActivity", "getApplicationContext: WrapperedApplication");
        return mCrossUserContextWrapper;
        Exception exception;
        exception;
        throw exception;
        Log.d("CrossUserPickerActivity", "getApplicationContext: NormalApplication");
        return super.getApplicationContext();
    }

    public ContentResolver getContentResolver()
    {
        if(!isCrossUserPick())
            break MISSING_BLOCK_LABEL_69;
        if(mCrossUserContentResolver != null) goto _L2; else goto _L1
_L1:
        Object obj = mLockObject;
        obj;
        JVM INSTR monitorenter ;
        if(mCrossUserContentResolver == null)
        {
            UserHandle userhandle = JVM INSTR new #78  <Class UserHandle>;
            userhandle.UserHandle(validateCrossUser());
            mCrossUserContentResolver = getContentResolverForUser(userhandle);
        }
        obj;
        JVM INSTR monitorexit ;
_L2:
        Log.d("CrossUserPickerActivity", "getContentResolver: CrossUserContentResolver");
        return mCrossUserContentResolver;
        Exception exception;
        exception;
        throw exception;
        Log.d("CrossUserPickerActivity", "getContentResolver: NormalContentResolver");
        return super.getContentResolver();
    }

    public boolean isCrossUserPick()
    {
        boolean flag;
        if(validateCrossUser() != -1)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void startActivity(Intent intent)
    {
        if(isCrossUserPick())
            intent.putExtra("android.intent.extra.picked_user_id", validateCrossUser());
        super.startActivity(intent);
    }

    public void startActivity(Intent intent, Bundle bundle)
    {
        if(isCrossUserPick())
            intent.putExtra("android.intent.extra.picked_user_id", validateCrossUser());
        super.startActivity(intent, bundle);
    }

    public void startActivityForResult(Intent intent, int i)
    {
        if(isCrossUserPick())
            intent.putExtra("android.intent.extra.picked_user_id", validateCrossUser());
        super.startActivityForResult(intent, i);
    }

    public void startActivityForResult(Intent intent, int i, Bundle bundle)
    {
        if(isCrossUserPick())
            intent.putExtra("android.intent.extra.picked_user_id", validateCrossUser());
        super.startActivityForResult(intent, i, bundle);
    }

    public void startActivityFromFragment(Fragment fragment, Intent intent, int i, Bundle bundle)
    {
        if(isCrossUserPick())
            intent.putExtra("android.intent.extra.picked_user_id", validateCrossUser());
        super.startActivityFromFragment(fragment, intent, i, bundle);
    }

    private static final String TAG = "CrossUserPickerActivity";
    public static final int USER_ID_INVALID = -1;
    private volatile ContentResolver mCrossUserContentResolver;
    private volatile ContextWrapper mCrossUserContextWrapper;
    private final Object mLockObject = new Object();
}
