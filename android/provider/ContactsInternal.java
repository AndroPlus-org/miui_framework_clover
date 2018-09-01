// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.provider;

import android.app.admin.DevicePolicyManager;
import android.content.*;
import android.net.Uri;
import android.os.Process;
import android.os.UserHandle;
import android.text.TextUtils;
import android.widget.Toast;
import java.util.List;

public class ContactsInternal
{

    private ContactsInternal()
    {
    }

    private static boolean maybeStartManagedQuickContact(Context context, Intent intent)
    {
        Object obj = intent.getData();
        Object obj1 = ((Uri) (obj)).getPathSegments();
        boolean flag;
        long l;
        long l1;
        if(((List) (obj1)).size() < 4)
            flag = true;
        else
            flag = false;
        if(flag)
            l = ContactsContract.Contacts.ENTERPRISE_CONTACT_ID_BASE;
        else
            l = ContentUris.parseId(((Uri) (obj)));
        obj1 = (String)((List) (obj1)).get(2);
        obj = ((Uri) (obj)).getQueryParameter("directory");
        if(obj == null)
            l1 = 0x3b9aca00L;
        else
            l1 = Long.parseLong(((String) (obj)));
        if(TextUtils.isEmpty(((CharSequence) (obj1))) || ((String) (obj1)).startsWith(ContactsContract.Contacts.ENTERPRISE_CONTACT_LOOKUP_PREFIX) ^ true)
            return false;
        if(!ContactsContract.Contacts.isEnterpriseContactId(l))
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid enterprise contact id: ").append(l).toString());
        if(!ContactsContract.Directory.isEnterpriseDirectoryId(l1))
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid enterprise directory id: ").append(l1).toString());
        } else
        {
            ((DevicePolicyManager)context.getSystemService(android/app/admin/DevicePolicyManager)).startManagedQuickContact(((String) (obj1)).substring(ContactsContract.Contacts.ENTERPRISE_CONTACT_LOOKUP_PREFIX.length()), l - ContactsContract.Contacts.ENTERPRISE_CONTACT_ID_BASE, flag, l1 - 0x3b9aca00L, intent);
            return true;
        }
    }

    public static void startQuickContactWithErrorToast(Context context, Intent intent)
    {
        Uri uri = intent.getData();
        sContactsUriMatcher.match(uri);
        JVM INSTR tableswitch 1000 1001: default 36
    //                   1000 45
    //                   1001 45;
           goto _L1 _L2 _L2
_L1:
        startQuickContactWithErrorToastForUser(context, intent, Process.myUserHandle());
        return;
_L2:
        if(maybeStartManagedQuickContact(context, intent))
            return;
        if(true) goto _L1; else goto _L3
_L3:
    }

    public static void startQuickContactWithErrorToastForUser(Context context, Intent intent, UserHandle userhandle)
    {
        context.startActivityAsUser(intent, userhandle);
_L1:
        return;
        intent;
        Toast.makeText(context, 0x1040550, 0).show();
          goto _L1
    }

    private static final int CONTACTS_URI_LOOKUP = 1001;
    private static final int CONTACTS_URI_LOOKUP_ID = 1000;
    private static final UriMatcher sContactsUriMatcher;

    static 
    {
        sContactsUriMatcher = new UriMatcher(-1);
        UriMatcher urimatcher = sContactsUriMatcher;
        urimatcher.addURI("com.android.contacts", "contacts/lookup/*", 1001);
        urimatcher.addURI("com.android.contacts", "contacts/lookup/*/#", 1000);
    }
}
