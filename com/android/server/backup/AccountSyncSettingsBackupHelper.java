// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.server.backup;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.backup.*;
import android.content.*;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import org.json.*;

public class AccountSyncSettingsBackupHelper
    implements BackupHelper
{

    public AccountSyncSettingsBackupHelper(Context context)
    {
        mContext = context;
        mAccountManager = AccountManager.get(mContext);
    }

    public static void accountAdded(Context context)
    {
        (new AccountSyncSettingsBackupHelper(context)).accountAddedInternal();
    }

    private void accountAddedInternal()
    {
        Object obj;
        Object obj1;
        String s;
        Object obj3;
        obj = null;
        obj1 = null;
        s = null;
        obj3 = null;
        Object obj4;
        obj4 = JVM INSTR new #113 <Class FileInputStream>;
        File file = JVM INSTR new #115 <Class File>;
        file.File(STASH_FILE);
        ((FileInputStream) (obj4)).FileInputStream(file);
        obj3 = JVM INSTR new #123 <Class DataInputStream>;
        ((DataInputStream) (obj3)).DataInputStream(((java.io.InputStream) (obj4)));
        s = ((DataInputStream) (obj3)).readUTF();
        obj3 = obj1;
        if(obj4 == null)
            break MISSING_BLOCK_LABEL_68;
        ((FileInputStream) (obj4)).close();
        obj3 = obj1;
_L3:
        if(obj3 == null) goto _L2; else goto _L1
_L1:
        throw obj3;
        obj4;
_L6:
        return;
        obj3;
          goto _L3
        obj4;
_L13:
        throw obj4;
        obj;
        obj1 = obj4;
        obj4 = obj;
_L12:
        obj = obj1;
        if(obj3 == null)
            break MISSING_BLOCK_LABEL_110;
        ((FileInputStream) (obj3)).close();
        obj = obj1;
_L7:
        if(obj == null) goto _L5; else goto _L4
_L4:
        throw obj;
        obj4;
          goto _L6
        obj3;
label0:
        {
            if(obj1 != null)
                break label0;
            obj = obj3;
        }
          goto _L7
        obj = obj1;
        if(obj1 == obj3) goto _L7; else goto _L8
_L8:
        ((Throwable) (obj1)).addSuppressed(((Throwable) (obj3)));
        obj = obj1;
          goto _L7
        obj4;
_L11:
        return;
_L5:
        throw obj4;
_L2:
        obj4 = JVM INSTR new #138 <Class JSONArray>;
        ((JSONArray) (obj4)).JSONArray(s);
        restoreFromJsonArray(((JSONArray) (obj4)));
_L9:
        return;
        obj4;
        Log.e("AccountSyncSettingsBackupHelper", "there was an error with the stashed sync settings", ((Throwable) (obj4)));
          goto _L9
        obj4;
        if(true) goto _L11; else goto _L10
_L10:
        obj4;
        obj3 = s;
        obj1 = obj;
          goto _L12
        Object obj2;
        obj2;
        obj3 = obj4;
        obj4 = obj2;
        obj2 = obj;
          goto _L12
        obj2;
        obj3 = obj4;
        obj4 = obj2;
          goto _L13
    }

    private byte[] generateMd5Checksum(byte abyte0[])
        throws NoSuchAlgorithmException
    {
        if(abyte0 == null)
            return null;
        else
            return MessageDigest.getInstance("MD5").digest(abyte0);
    }

    private HashSet getAccounts()
    {
        Account aaccount[] = mAccountManager.getAccounts();
        HashSet hashset = new HashSet();
        int i = 0;
        for(int j = aaccount.length; i < j; i++)
            hashset.add(aaccount[i]);

        return hashset;
    }

    private byte[] readOldMd5Checksum(ParcelFileDescriptor parcelfiledescriptor)
        throws IOException
    {
        DataInputStream datainputstream;
        datainputstream = new DataInputStream(new FileInputStream(parcelfiledescriptor.getFileDescriptor()));
        parcelfiledescriptor = new byte[16];
        int i = datainputstream.readInt();
        if(i > 1) goto _L2; else goto _L1
_L1:
        i = 0;
_L4:
        if(i >= 16)
            break; /* Loop/switch isn't completed */
        parcelfiledescriptor[i] = datainputstream.readByte();
        i++;
        if(true) goto _L4; else goto _L3
_L2:
        try
        {
            StringBuilder stringbuilder = JVM INSTR new #60  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.i("AccountSyncSettingsBackupHelper", stringbuilder.append("Backup state version is: ").append(i).append(" (support only up to version ").append(1).append(")").toString());
        }
        catch(EOFException eofexception) { }
_L3:
        return parcelfiledescriptor;
    }

    private void restoreExistingAccountSyncSettingsFromJSON(JSONObject jsonobject)
        throws JSONException
    {
        JSONArray jsonarray = jsonobject.getJSONArray("authorities");
        Account account = new Account(jsonobject.getString("name"), jsonobject.getString("type"));
        int i = 0;
        while(i < jsonarray.length()) 
        {
            jsonobject = (JSONObject)jsonarray.get(i);
            String s = jsonobject.getString("name");
            boolean flag = jsonobject.getBoolean("syncEnabled");
            int j = jsonobject.getInt("syncState");
            ContentResolver.setSyncAutomaticallyAsUser(account, s, flag, 0);
            if(!flag)
            {
                if(j == 0)
                    j = 0;
                else
                    j = 2;
                ContentResolver.setIsSyncable(account, s, j);
            }
            i++;
        }
    }

    private void restoreFromJsonArray(JSONArray jsonarray)
        throws JSONException
    {
        Object obj;
        JSONArray jsonarray1;
        int i;
        obj = getAccounts();
        jsonarray1 = new JSONArray();
        i = 0;
_L2:
        Object obj1;
        String s;
        String s1;
        if(i >= jsonarray.length())
            break; /* Loop/switch isn't completed */
        obj1 = (JSONObject)jsonarray.get(i);
        s = ((JSONObject) (obj1)).getString("name");
        s1 = ((JSONObject) (obj1)).getString("type");
        Object obj3;
        obj3 = JVM INSTR new #225 <Class Account>;
        ((Account) (obj3)).Account(s, s1);
        if(((HashSet) (obj)).contains(obj3))
            restoreExistingAccountSyncSettingsFromJSON(((JSONObject) (obj1)));
        else
            jsonarray1.put(obj1);
_L13:
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        if(jsonarray1.length() <= 0) goto _L4; else goto _L3
_L3:
        obj3 = null;
        obj1 = null;
        s1 = null;
        obj = null;
        jsonarray = JVM INSTR new #272 <Class FileOutputStream>;
        jsonarray.FileOutputStream(STASH_FILE);
        s1 = jsonarray1.toString();
        obj = JVM INSTR new #276 <Class DataOutputStream>;
        ((DataOutputStream) (obj)).DataOutputStream(jsonarray);
        ((DataOutputStream) (obj)).writeUTF(s1);
        obj = obj1;
        if(jsonarray == null)
            break MISSING_BLOCK_LABEL_163;
        jsonarray.close();
        obj = obj1;
_L7:
        if(obj == null) goto _L6; else goto _L5
_L5:
        try
        {
            throw obj;
        }
        // Misplaced declaration of an exception variable
        catch(JSONArray jsonarray) { }
_L8:
        Log.e("AccountSyncSettingsBackupHelper", "unable to write the sync settings to the stash file", jsonarray);
_L6:
        return;
        obj;
          goto _L7
        jsonarray;
_L12:
        throw jsonarray;
        obj3;
        obj1 = jsonarray;
        jsonarray = ((JSONArray) (obj3));
_L11:
        obj3 = obj1;
        if(obj == null)
            break MISSING_BLOCK_LABEL_212;
        ((FileOutputStream) (obj)).close();
        obj3 = obj1;
_L9:
        if(obj3 == null)
            break MISSING_BLOCK_LABEL_259;
        try
        {
            throw obj3;
        }
        // Misplaced declaration of an exception variable
        catch(JSONArray jsonarray) { }
          goto _L8
        obj;
label0:
        {
            if(obj1 != null)
                break label0;
            obj3 = obj;
        }
          goto _L9
        obj3 = obj1;
        if(obj1 == obj) goto _L9; else goto _L10
_L10:
        ((Throwable) (obj1)).addSuppressed(((Throwable) (obj)));
        obj3 = obj1;
          goto _L9
        throw jsonarray;
_L4:
        jsonarray = new File(STASH_FILE);
        if(jsonarray.exists())
            jsonarray.delete();
          goto _L6
        jsonarray;
        obj = s1;
        obj1 = obj3;
          goto _L11
        Object obj2;
        obj2;
        obj = jsonarray;
        jsonarray = ((JSONArray) (obj2));
        obj2 = obj3;
          goto _L11
        obj2;
        obj = jsonarray;
        jsonarray = ((JSONArray) (obj2));
          goto _L12
        obj2;
          goto _L13
    }

    private JSONObject serializeAccountSyncSettingsToJSON()
        throws JSONException
    {
        Account aaccount[] = mAccountManager.getAccounts();
        SyncAdapterType asyncadaptertype[] = ContentResolver.getSyncAdapterTypesAsUser(mContext.getUserId());
        HashMap hashmap = new HashMap();
        int i = asyncadaptertype.length;
        int j = 0;
        while(j < i) 
        {
            SyncAdapterType syncadaptertype = asyncadaptertype[j];
            if(syncadaptertype.isUserVisible())
            {
                if(!hashmap.containsKey(syncadaptertype.accountType))
                    hashmap.put(syncadaptertype.accountType, new ArrayList());
                ((List)hashmap.get(syncadaptertype.accountType)).add(syncadaptertype.authority);
            }
            j++;
        }
        JSONObject jsonobject = new JSONObject();
        jsonobject.put("version", 1);
        jsonobject.put("masterSyncEnabled", ContentResolver.getMasterSyncAutomatically());
        JSONArray jsonarray1 = new JSONArray();
        j = 0;
        i = aaccount.length;
        while(j < i) 
        {
            Account account = aaccount[j];
            Object obj = (List)hashmap.get(account.type);
            if(obj != null && !((List) (obj)).isEmpty())
            {
                JSONObject jsonobject1 = new JSONObject();
                jsonobject1.put("name", account.name);
                jsonobject1.put("type", account.type);
                JSONArray jsonarray = new JSONArray();
                JSONObject jsonobject2;
                for(obj = ((Iterable) (obj)).iterator(); ((Iterator) (obj)).hasNext(); jsonarray.put(jsonobject2))
                {
                    String s = (String)((Iterator) (obj)).next();
                    int k = ContentResolver.getIsSyncable(account, s);
                    boolean flag = ContentResolver.getSyncAutomatically(account, s);
                    jsonobject2 = new JSONObject();
                    jsonobject2.put("name", s);
                    jsonobject2.put("syncState", k);
                    jsonobject2.put("syncEnabled", flag);
                }

                jsonobject1.put("authorities", jsonarray);
                jsonarray1.put(jsonobject1);
            }
            j++;
        }
        jsonobject.put("accounts", jsonarray1);
        return jsonobject;
    }

    private void writeNewMd5Checksum(ParcelFileDescriptor parcelfiledescriptor, byte abyte0[])
        throws IOException
    {
        parcelfiledescriptor = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(parcelfiledescriptor.getFileDescriptor())));
        parcelfiledescriptor.writeInt(1);
        parcelfiledescriptor.write(abyte0);
    }

    public void performBackup(ParcelFileDescriptor parcelfiledescriptor, BackupDataOutput backupdataoutput, ParcelFileDescriptor parcelfiledescriptor1)
    {
        byte abyte0[];
        byte abyte1[];
        abyte0 = serializeAccountSyncSettingsToJSON().toString().getBytes("UTF-8");
        parcelfiledescriptor = readOldMd5Checksum(parcelfiledescriptor);
        abyte1 = generateMd5Checksum(abyte0);
        if(Arrays.equals(parcelfiledescriptor, abyte1)) goto _L2; else goto _L1
_L1:
        int i = abyte0.length;
        backupdataoutput.writeEntityHeader("account_data", i);
        backupdataoutput.writeEntityData(abyte0, i);
        Log.i("AccountSyncSettingsBackupHelper", "Backup successful.");
_L3:
        writeNewMd5Checksum(parcelfiledescriptor1, abyte1);
_L4:
        return;
_L2:
        Log.i("AccountSyncSettingsBackupHelper", "Old and new MD5 checksums match. Skipping backup.");
          goto _L3
        parcelfiledescriptor;
        Log.e("AccountSyncSettingsBackupHelper", (new StringBuilder()).append("Couldn't backup account sync settings\n").append(parcelfiledescriptor).toString());
          goto _L4
    }

    public void restoreEntity(BackupDataInputStream backupdatainputstream)
    {
        byte abyte0[] = new byte[backupdatainputstream.size()];
        boolean flag;
        backupdatainputstream.read(abyte0);
        backupdatainputstream = JVM INSTR new #369 <Class String>;
        backupdatainputstream.String(abyte0, "UTF-8");
        JSONObject jsonobject = JVM INSTR new #219 <Class JSONObject>;
        jsonobject.JSONObject(backupdatainputstream);
        flag = jsonobject.getBoolean("masterSyncEnabled");
        backupdatainputstream = jsonobject.getJSONArray("accounts");
        if(ContentResolver.getMasterSyncAutomatically())
            ContentResolver.setMasterSyncAutomatically(false);
        restoreFromJsonArray(backupdatainputstream);
        ContentResolver.setMasterSyncAutomatically(flag);
        Log.i("AccountSyncSettingsBackupHelper", "Restore successful.");
_L1:
        return;
        backupdatainputstream;
        try
        {
            ContentResolver.setMasterSyncAutomatically(flag);
            throw backupdatainputstream;
        }
        // Misplaced declaration of an exception variable
        catch(BackupDataInputStream backupdatainputstream)
        {
            Log.e("AccountSyncSettingsBackupHelper", (new StringBuilder()).append("Couldn't restore account sync settings\n").append(backupdatainputstream).toString());
        }
          goto _L1
    }

    public void writeNewStateDescription(ParcelFileDescriptor parcelfiledescriptor)
    {
    }

    private static final boolean DEBUG = false;
    private static final String JSON_FORMAT_ENCODING = "UTF-8";
    private static final String JSON_FORMAT_HEADER_KEY = "account_data";
    private static final int JSON_FORMAT_VERSION = 1;
    private static final String KEY_ACCOUNTS = "accounts";
    private static final String KEY_ACCOUNT_AUTHORITIES = "authorities";
    private static final String KEY_ACCOUNT_NAME = "name";
    private static final String KEY_ACCOUNT_TYPE = "type";
    private static final String KEY_AUTHORITY_NAME = "name";
    private static final String KEY_AUTHORITY_SYNC_ENABLED = "syncEnabled";
    private static final String KEY_AUTHORITY_SYNC_STATE = "syncState";
    private static final String KEY_MASTER_SYNC_ENABLED = "masterSyncEnabled";
    private static final String KEY_VERSION = "version";
    private static final int MD5_BYTE_SIZE = 16;
    private static final String STASH_FILE = (new StringBuilder()).append(Environment.getDataDirectory()).append("/backup/unadded_account_syncsettings.json").toString();
    private static final int STATE_VERSION = 1;
    private static final int SYNC_REQUEST_LATCH_TIMEOUT_SECONDS = 1;
    private static final String TAG = "AccountSyncSettingsBackupHelper";
    private AccountManager mAccountManager;
    private Context mContext;

}
