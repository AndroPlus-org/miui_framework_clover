// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.provider;

import android.content.*;
import android.content.pm.UserInfo;
import android.net.Uri;
import android.os.UserHandle;
import android.os.UserManager;
import android.os.storage.StorageManager;
import android.telecom.*;
import android.util.Log;
import com.android.internal.telephony.CallerInfo;
import java.util.List;

public class CallLog
{

    public CallLog()
    {
    }

    public static Uri addCall(Context context, Uri uri, ContentValues contentvalues, boolean flag)
    {
        return addCall(context, uri, contentvalues, flag, null);
    }

    public static Uri addCall(Context context, Uri uri, ContentValues contentvalues, boolean flag, UserHandle userhandle)
    {
        Log.i("CallLog", String.format("addCall(): addForAllUsers=%s", new Object[] {
            Boolean.valueOf(flag)
        }));
        Object obj = null;
        UserManager usermanager = (UserManager)context.getSystemService("user");
        Uri uri1 = uri;
        if(StorageManager.isFileEncryptedNativeOrEmulated())
        {
            uri1 = uri;
            if(usermanager.isUserUnlocked() ^ true)
                uri1 = ExtraContacts.Calls.SHADOW_CONTENT_URI;
        }
        int i = usermanager.getUserHandle();
        if(flag)
        {
            userhandle = insertCall(context, UserHandle.SYSTEM, uri1, contentvalues);
            if(userhandle == null || "call_log_shadow".equals(userhandle.getAuthority()))
                return null;
            uri = obj;
            if(i == 0)
                uri = userhandle;
            List list = usermanager.getUsers(true);
            int j = list.size();
            int k = 0;
            do
            {
                userhandle = uri;
                if(k >= j)
                    break;
                UserInfo userinfo = (UserInfo)list.get(k);
                Object obj1 = userinfo.getUserHandle();
                int l = ((UserHandle) (obj1)).getIdentifier();
                if(((UserHandle) (obj1)).isSystem())
                {
                    userhandle = uri;
                } else
                {
                    userhandle = uri;
                    if(canInsertCalllog(usermanager, userinfo))
                    {
                        obj1 = insertCall(context, ((UserHandle) (obj1)), uri1, contentvalues);
                        userhandle = uri;
                        if(l == i)
                            userhandle = ((UserHandle) (obj1));
                    }
                }
                k++;
                uri = userhandle;
            } while(true);
        } else
        {
            if(userhandle != null)
                uri = userhandle;
            else
                uri = UserHandle.of(i);
            userhandle = insertCall(context, uri, uri1, contentvalues);
        }
        Log.i("CallLog", String.format("addCall(): result=%s", new Object[] {
            userhandle
        }));
        return userhandle;
    }

    public static Uri addCall(CallerInfo callerinfo, Context context, String s, int i, int j, long l, int k, 
            int i1, int j1, long l1, long l2, long l3, boolean flag, UserHandle userhandle, PhoneAccountHandle phoneaccounthandle)
    {
        Object obj = null;
        TelecomManager telecommanager = TelecomManager.from(context);
        obj = telecommanager;
_L2:
        String s2 = null;
        String s1 = s2;
        if(obj != null)
        {
            s1 = s2;
            if(phoneaccounthandle != null)
            {
                obj = ((TelecomManager) (obj)).getPhoneAccount(phoneaccounthandle);
                s1 = s2;
                if(obj != null)
                {
                    obj = ((PhoneAccount) (obj)).getSubscriptionAddress();
                    s1 = s2;
                    if(obj != null)
                        s1 = ((Uri) (obj)).getSchemeSpecificPart();
                }
            }
        }
        s2 = null;
        obj = null;
        if(phoneaccounthandle != null)
        {
            s2 = phoneaccounthandle.getComponentName().flattenToString();
            obj = phoneaccounthandle.getId();
        }
        return ExtraContacts.Calls.addCall(callerinfo, context, s, i, j, l, k, i1, j1, l1, l2, l3, flag, userhandle, s2, ((String) (obj)), s1);
        UnsupportedOperationException unsupportedoperationexception;
        unsupportedoperationexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static Uri addCall(CallerInfo callerinfo, Context context, String s, int i, int j, long l, int k, 
            int i1, int j1, long l1, long l2, long l3, boolean flag, PhoneAccountHandle phoneaccounthandle)
    {
        return addCall(callerinfo, context, s, i, j, l, k, i1, j1, l1, l2, l3, flag, null, phoneaccounthandle);
    }

    private static boolean canInsertCalllog(UserManager usermanager, UserInfo userinfo)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(usermanager != null)
        {
            flag1 = flag;
            if(userinfo != null)
            {
                UserHandle userhandle = userinfo.getUserHandle();
                if(999 != userinfo.id && usermanager.isUserRunning(userhandle) && usermanager.hasUserRestriction("no_outgoing_calls", userhandle) ^ true)
                    flag1 = userinfo.isManagedProfile() ^ true;
                else
                    flag1 = false;
            }
        }
        Log.i("CallLog", String.format("canInsertCallLog(): user=%s, canInsert=%s", new Object[] {
            userinfo, Boolean.valueOf(flag1)
        }));
        return flag1;
    }

    private static Uri insertCall(Context context, UserHandle userhandle, Uri uri, ContentValues contentvalues)
    {
        userhandle = ContentProvider.maybeAddUserId(uri, userhandle.getIdentifier());
        try
        {
            context = context.getContentResolver().insert(userhandle, contentvalues);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            Log.w("CallLog", "Failed to insert calllog", context);
            return null;
        }
        return context;
    }

    private static final String TAG = "CallLog";
}
