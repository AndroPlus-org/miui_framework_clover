// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.mtp;

import android.content.*;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaScanner;
import android.net.Uri;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import dalvik.system.CloseGuard;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

// Referenced classes of package android.mtp:
//            MtpPropertyList, MtpPropertyGroup, MtpStorage, MtpServer

public class MtpDatabase
    implements AutoCloseable
{

    static int _2D_get0(MtpDatabase mtpdatabase)
    {
        return mtpdatabase.mBatteryLevel;
    }

    static MtpServer _2D_get1(MtpDatabase mtpdatabase)
    {
        return mtpdatabase.mServer;
    }

    static int _2D_set0(MtpDatabase mtpdatabase, int i)
    {
        mtpdatabase.mBatteryLevel = i;
        return i;
    }

    static int _2D_set1(MtpDatabase mtpdatabase, int i)
    {
        mtpdatabase.mBatteryScale = i;
        return i;
    }

    public MtpDatabase(Context context, Context context1, String s, String s1, String as[])
    {
        mBatteryReceiver = new BroadcastReceiver() {

            public void onReceive(Context context2, Intent intent)
            {
                if(intent.getAction().equals("android.intent.action.BATTERY_CHANGED"))
                {
                    MtpDatabase._2D_set1(MtpDatabase.this, intent.getIntExtra("scale", 0));
                    int j1 = intent.getIntExtra("level", 0);
                    if(j1 != MtpDatabase._2D_get0(MtpDatabase.this))
                    {
                        MtpDatabase._2D_set0(MtpDatabase.this, j1);
                        if(MtpDatabase._2D_get1(MtpDatabase.this) != null)
                            MtpDatabase._2D_get1(MtpDatabase.this).sendDevicePropertyChanged(20481);
                    }
                }
            }

            final MtpDatabase this$0;

            
            {
                this$0 = MtpDatabase.this;
                super();
            }
        }
;
        native_setup();
        mContext = context;
        mUserContext = context1;
        mPackageName = context.getPackageName();
        mMediaProvider = context1.getContentResolver().acquireContentProviderClient("media");
        mVolumeName = s;
        mMediaStoragePath = s1;
        mObjectsUri = android.provider.MediaStore.Files.getMtpObjectsUri(s);
        mMediaScanner = new MediaScanner(context, mVolumeName);
        mSubDirectories = as;
        if(as != null)
        {
            context1 = new StringBuilder();
            context1.append("(");
            int i = as.length;
            for(int j = 0; j < i; j++)
            {
                context1.append("_data=? OR _data LIKE ?");
                if(j != i - 1)
                    context1.append(" OR ");
            }

            context1.append(")");
            mSubDirectoriesWhere = context1.toString();
            mSubDirectoriesWhereArgs = new String[i * 2];
            int l = 0;
            int k = 0;
            for(; l < i; l++)
            {
                context1 = as[l];
                s = mSubDirectoriesWhereArgs;
                int i1 = k + 1;
                s[k] = context1;
                s = mSubDirectoriesWhereArgs;
                k = i1 + 1;
                s[i1] = (new StringBuilder()).append(context1).append("/%").toString();
            }

        }
        initDeviceProperties(context);
        mDeviceType = SystemProperties.getInt("sys.usb.mtp.device_type", 0);
        mCloseGuard.open("close");
    }

    private int beginSendObject(String s, int i, int j, int k, long l, long l1)
    {
        Object obj;
        Cursor cursor;
        if(!inStorageRoot(s))
        {
            Log.e("MtpDatabase", (new StringBuilder()).append("attempt to put file outside of storage area: ").append(s).toString());
            return -1;
        }
        if(!inStorageSubDirectory(s))
            return -1;
        if(s == null)
            break MISSING_BLOCK_LABEL_188;
        obj = null;
        cursor = null;
        Cursor cursor1 = mMediaProvider.query(mObjectsUri, ID_PROJECTION, "_data=?", new String[] {
            s
        }, null, null);
        if(cursor1 == null)
            break MISSING_BLOCK_LABEL_176;
        cursor = cursor1;
        obj = cursor1;
        if(cursor1.getCount() <= 0)
            break MISSING_BLOCK_LABEL_176;
        cursor = cursor1;
        obj = cursor1;
        StringBuilder stringbuilder = JVM INSTR new #257 <Class StringBuilder>;
        cursor = cursor1;
        obj = cursor1;
        stringbuilder.StringBuilder();
        cursor = cursor1;
        obj = cursor1;
        Log.w("MtpDatabase", stringbuilder.append("file already exists in beginSendObject: ").append(s).toString());
        if(cursor1 != null)
            cursor1.close();
        return -1;
        if(cursor1 != null)
            cursor1.close();
_L2:
        mDatabaseModified = true;
        obj = new ContentValues();
        ((ContentValues) (obj)).put("_data", s);
        ((ContentValues) (obj)).put("format", Integer.valueOf(i));
        ((ContentValues) (obj)).put("parent", Integer.valueOf(j));
        ((ContentValues) (obj)).put("storage_id", Integer.valueOf(k));
        ((ContentValues) (obj)).put("_size", Long.valueOf(l));
        ((ContentValues) (obj)).put("date_modified", Long.valueOf(l1));
        RemoteException remoteexception;
        try
        {
            s = mMediaProvider.insert(mObjectsUri, ((ContentValues) (obj)));
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.e("MtpDatabase", "RemoteException in beginSendObject", s);
            return -1;
        }
        if(s == null)
            break; /* Loop/switch isn't completed */
        i = Integer.parseInt((String)s.getPathSegments().get(2));
        return i;
        remoteexception;
        obj = cursor;
        Log.e("MtpDatabase", "RemoteException in beginSendObject", remoteexception);
        if(cursor != null)
            cursor.close();
        if(true) goto _L2; else goto _L1
        s;
        if(obj != null)
            ((Cursor) (obj)).close();
        throw s;
_L1:
        return -1;
    }

    private Cursor createObjectQuery(int i, int j, int k)
        throws RemoteException
    {
        String s;
        String as[];
        String s1;
        String as1[];
        if(i == -1)
        {
            if(j == 0)
            {
                if(k == 0)
                {
                    s = null;
                    as = null;
                } else
                {
                    i = k;
                    if(k == -1)
                        i = 0;
                    s = "parent=?";
                    as = new String[1];
                    as[0] = Integer.toString(i);
                }
            } else
            if(k == 0)
            {
                s = "format=?";
                as = new String[1];
                as[0] = Integer.toString(j);
            } else
            {
                i = k;
                if(k == -1)
                    i = 0;
                s = "format=? AND parent=?";
                as = new String[2];
                as[0] = Integer.toString(j);
                as[1] = Integer.toString(i);
            }
        } else
        if(j == 0)
        {
            if(k == 0)
            {
                s = "storage_id=?";
                as = new String[1];
                as[0] = Integer.toString(i);
            } else
            {
                j = k;
                if(k == -1)
                    j = 0;
                s = "storage_id=? AND parent=?";
                as = new String[2];
                as[0] = Integer.toString(i);
                as[1] = Integer.toString(j);
            }
        } else
        if(k == 0)
        {
            s = "storage_id=? AND format=?";
            as = new String[2];
            as[0] = Integer.toString(i);
            as[1] = Integer.toString(j);
        } else
        {
            int l = k;
            if(k == -1)
                l = 0;
            s = "storage_id=? AND format=? AND parent=?";
            as = new String[3];
            as[0] = Integer.toString(i);
            as[1] = Integer.toString(j);
            as[2] = Integer.toString(l);
        }
        s1 = s;
        as1 = as;
        if(mSubDirectoriesWhere != null)
            if(s == null)
            {
                s1 = mSubDirectoriesWhere;
                as1 = mSubDirectoriesWhereArgs;
            } else
            {
                s1 = (new StringBuilder()).append(s).append(" AND ").append(mSubDirectoriesWhere).toString();
                as1 = new String[as.length + mSubDirectoriesWhereArgs.length];
                for(i = 0; i < as.length; i++)
                    as1[i] = as[i];

                j = 0;
                while(j < mSubDirectoriesWhereArgs.length) 
                {
                    as1[i] = mSubDirectoriesWhereArgs[j];
                    i++;
                    j++;
                }
            }
        return mMediaProvider.query(mObjectsUri, ID_PROJECTION, s1, as1, null, null);
    }

    private int deleteFile(int i)
    {
        Object obj;
        Object obj1;
        mDatabaseModified = true;
        obj = null;
        obj1 = null;
        Object obj2 = mMediaProvider.query(mObjectsUri, PATH_FORMAT_PROJECTION, "_id=?", new String[] {
            Integer.toString(i)
        }, null, null);
        if(obj2 == null)
            break MISSING_BLOCK_LABEL_119;
        obj1 = obj2;
        obj = obj2;
        if(!((Cursor) (obj2)).moveToNext())
            break MISSING_BLOCK_LABEL_119;
        obj1 = obj2;
        obj = obj2;
        String s = ((Cursor) (obj2)).getString(1);
        obj1 = obj2;
        obj = obj2;
        int j = ((Cursor) (obj2)).getInt(2);
        if(s == null || j == 0)
        {
            if(obj2 != null)
                ((Cursor) (obj2)).close();
            return 8194;
        }
        break MISSING_BLOCK_LABEL_135;
        if(obj2 != null)
            ((Cursor) (obj2)).close();
        return 8201;
        obj1 = obj2;
        obj = obj2;
        boolean flag = isStorageSubDirectory(s);
        if(flag)
        {
            if(obj2 != null)
                ((Cursor) (obj2)).close();
            return 8205;
        }
        if(j != 12289)
            break MISSING_BLOCK_LABEL_341;
        obj1 = obj2;
        obj = obj2;
        Uri uri = android.provider.MediaStore.Files.getMtpObjectsUri(mVolumeName);
        obj1 = obj2;
        obj = obj2;
        Object obj3 = mMediaProvider;
        obj1 = obj2;
        obj = obj2;
        Object obj4 = JVM INSTR new #257 <Class StringBuilder>;
        obj1 = obj2;
        obj = obj2;
        ((StringBuilder) (obj4)).StringBuilder();
        obj1 = obj2;
        obj = obj2;
        String s1 = ((StringBuilder) (obj4)).append(s).append("/%").toString();
        obj1 = obj2;
        obj = obj2;
        obj4 = Integer.toString(s.length() + 1);
        obj1 = obj2;
        obj = obj2;
        StringBuilder stringbuilder = JVM INSTR new #257 <Class StringBuilder>;
        obj1 = obj2;
        obj = obj2;
        stringbuilder.StringBuilder();
        obj1 = obj2;
        obj = obj2;
        ((ContentProviderClient) (obj3)).delete(uri, "_data LIKE ?1 AND lower(substr(_data,1,?2))=lower(?3)", new String[] {
            s1, obj4, stringbuilder.append(s).append("/").toString()
        });
        obj1 = obj2;
        obj = obj2;
        obj3 = android.provider.MediaStore.Files.getMtpObjectsUri(mVolumeName, i);
        obj1 = obj2;
        obj = obj2;
        if(mMediaProvider.delete(((Uri) (obj3)), null, null) <= 0)
            break MISSING_BLOCK_LABEL_546;
        if(j == 12289)
            break MISSING_BLOCK_LABEL_447;
        obj1 = obj2;
        obj = obj2;
        flag = s.toLowerCase(Locale.US).endsWith("/.nomedia");
        if(!flag)
            break MISSING_BLOCK_LABEL_447;
        obj = obj2;
        obj1 = s.substring(0, s.lastIndexOf("/"));
        obj = obj2;
        mMediaProvider.call("unhide", ((String) (obj1)), null);
_L2:
        if(obj2 != null)
            ((Cursor) (obj2)).close();
        return 8193;
        obj;
        obj1 = obj2;
        obj = obj2;
        obj3 = JVM INSTR new #257 <Class StringBuilder>;
        obj1 = obj2;
        obj = obj2;
        ((StringBuilder) (obj3)).StringBuilder();
        obj1 = obj2;
        obj = obj2;
        Log.e("MtpDatabase", ((StringBuilder) (obj3)).append("failed to unhide/rescan for ").append(s).toString());
        if(true) goto _L2; else goto _L1
_L1:
        obj2;
        obj = obj1;
        Log.e("MtpDatabase", "RemoteException in deleteFile", ((Throwable) (obj2)));
        if(obj1 != null)
            ((Cursor) (obj1)).close();
        return 8194;
        if(obj2 != null)
            ((Cursor) (obj2)).close();
        return 8201;
        Exception exception;
        exception;
        if(obj != null)
            ((Cursor) (obj)).close();
        throw exception;
    }

    private void endSendObject(String s, int i, int j, boolean flag)
    {
        if(!flag)
            break MISSING_BLOCK_LABEL_177;
        if(j != 47621) goto _L2; else goto _L1
_L1:
        Object obj;
        obj = s;
        int k = s.lastIndexOf('/');
        if(k >= 0)
            obj = s.substring(k + 1);
        String s1 = ((String) (obj));
        if(((String) (obj)).endsWith(".pla"))
            s1 = ((String) (obj)).substring(0, ((String) (obj)).length() - 4);
        obj = new ContentValues(1);
        ((ContentValues) (obj)).put("_data", s);
        ((ContentValues) (obj)).put("name", s1);
        ((ContentValues) (obj)).put("format", Integer.valueOf(j));
        ((ContentValues) (obj)).put("date_modified", Long.valueOf(System.currentTimeMillis() / 1000L));
        ((ContentValues) (obj)).put("media_scanner_new_object_id", Integer.valueOf(i));
        mMediaProvider.insert(android.provider.MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI, ((ContentValues) (obj)));
_L3:
        return;
        s;
        Log.e("MtpDatabase", "RemoteException in endSendObject", s);
          goto _L3
_L2:
        mMediaScanner.scanMtpFile(s, i, j);
          goto _L3
        deleteFile(i);
          goto _L3
    }

    private int getDeviceProperty(int i, long al[], char ac[])
    {
        switch(i)
        {
        default:
            return 8202;

        case 54273: 
        case 54274: 
            al = mDeviceProperties.getString(Integer.toString(i), "");
            int j = al.length();
            i = j;
            if(j > 255)
                i = 255;
            al.getChars(0, i, ac, 0);
            ac[i] = (char)0;
            return 8193;

        case 20483: 
            al = ((WindowManager)mContext.getSystemService("window")).getDefaultDisplay();
            i = al.getMaximumSizeDimension();
            int k = al.getMaximumSizeDimension();
            al = (new StringBuilder()).append(Integer.toString(i)).append("x").append(Integer.toString(k)).toString();
            al.getChars(0, al.length(), ac, 0);
            ac[al.length()] = (char)0;
            return 8193;

        case 54279: 
            al[0] = mDeviceType;
            return 8193;
        }
    }

    private int getNumObjects(int i, int j, int k)
    {
        Cursor cursor;
        Cursor cursor1;
        cursor = null;
        cursor1 = null;
        Cursor cursor2 = createObjectQuery(i, j, k);
        if(cursor2 == null)
            break MISSING_BLOCK_LABEL_50;
        cursor1 = cursor2;
        cursor = cursor2;
        i = cursor2.getCount();
        if(cursor2 != null)
            cursor2.close();
        return i;
        if(cursor2 != null)
            cursor2.close();
_L2:
        return -1;
        RemoteException remoteexception;
        remoteexception;
        cursor = cursor1;
        Log.e("MtpDatabase", "RemoteException in getNumObjects", remoteexception);
        if(cursor1 != null)
            cursor1.close();
        if(true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        if(cursor != null)
            cursor.close();
        throw exception;
    }

    private int getObjectFilePath(int i, char ac[], long al[])
    {
        Cursor cursor;
        Cursor cursor1;
        if(i == 0)
        {
            mMediaStoragePath.getChars(0, mMediaStoragePath.length(), ac, 0);
            ac[mMediaStoragePath.length()] = (char)0;
            al[0] = 0L;
            al[1] = 12289L;
            return 8193;
        }
        cursor = null;
        cursor1 = null;
        Cursor cursor2 = mMediaProvider.query(mObjectsUri, PATH_FORMAT_PROJECTION, "_id=?", new String[] {
            Integer.toString(i)
        }, null, null);
        if(cursor2 == null)
            break MISSING_BLOCK_LABEL_238;
        cursor1 = cursor2;
        cursor = cursor2;
        if(!cursor2.moveToNext())
            break MISSING_BLOCK_LABEL_238;
        cursor1 = cursor2;
        cursor = cursor2;
        String s = cursor2.getString(1);
        cursor1 = cursor2;
        cursor = cursor2;
        s.getChars(0, s.length(), ac, 0);
        cursor1 = cursor2;
        cursor = cursor2;
        ac[s.length()] = (char)0;
        cursor1 = cursor2;
        cursor = cursor2;
        ac = JVM INSTR new #537 <Class File>;
        cursor1 = cursor2;
        cursor = cursor2;
        ac.File(s);
        cursor1 = cursor2;
        cursor = cursor2;
        al[0] = ac.length();
        cursor1 = cursor2;
        cursor = cursor2;
        al[1] = cursor2.getLong(2);
        if(cursor2 != null)
            cursor2.close();
        return 8193;
        if(cursor2 != null)
            cursor2.close();
        return 8201;
        ac;
        cursor = cursor1;
        Log.e("MtpDatabase", "RemoteException in getObjectFilePath", ac);
        if(cursor1 != null)
            cursor1.close();
        return 8194;
        ac;
        if(cursor != null)
            cursor.close();
        throw ac;
    }

    private int getObjectFormat(int i)
    {
        Cursor cursor;
        Cursor cursor1;
        cursor = null;
        cursor1 = null;
        Cursor cursor2 = mMediaProvider.query(mObjectsUri, FORMAT_PROJECTION, "_id=?", new String[] {
            Integer.toString(i)
        }, null, null);
        if(cursor2 == null)
            break MISSING_BLOCK_LABEL_85;
        cursor1 = cursor2;
        cursor = cursor2;
        if(!cursor2.moveToNext())
            break MISSING_BLOCK_LABEL_85;
        cursor1 = cursor2;
        cursor = cursor2;
        i = cursor2.getInt(1);
        if(cursor2 != null)
            cursor2.close();
        return i;
        if(cursor2 != null)
            cursor2.close();
        return -1;
        RemoteException remoteexception;
        remoteexception;
        cursor = cursor1;
        Log.e("MtpDatabase", "RemoteException in getObjectFilePath", remoteexception);
        if(cursor1 != null)
            cursor1.close();
        return -1;
        Exception exception;
        exception;
        if(cursor != null)
            cursor.close();
        throw exception;
    }

    private boolean getObjectInfo(int i, int ai[], char ac[], long al[])
    {
        Cursor cursor;
        Cursor cursor1;
        cursor = null;
        cursor1 = null;
        Cursor cursor2 = mMediaProvider.query(mObjectsUri, OBJECT_INFO_PROJECTION, "_id=?", new String[] {
            Integer.toString(i)
        }, null, null);
        if(cursor2 == null)
            break MISSING_BLOCK_LABEL_293;
        cursor1 = cursor2;
        cursor = cursor2;
        if(!cursor2.moveToNext())
            break MISSING_BLOCK_LABEL_293;
        cursor1 = cursor2;
        cursor = cursor2;
        ai[0] = cursor2.getInt(1);
        cursor1 = cursor2;
        cursor = cursor2;
        ai[1] = cursor2.getInt(2);
        cursor1 = cursor2;
        cursor = cursor2;
        ai[2] = cursor2.getInt(3);
        cursor1 = cursor2;
        cursor = cursor2;
        ai = cursor2.getString(4);
        cursor1 = cursor2;
        cursor = cursor2;
        i = ai.lastIndexOf('/');
        int j;
        int k;
        if(i >= 0)
            i++;
        else
            i = 0;
        cursor1 = cursor2;
        cursor = cursor2;
        j = ai.length();
        k = j;
        if(j - i > 255)
            k = i + 255;
        cursor1 = cursor2;
        cursor = cursor2;
        ai.getChars(i, k, ac, 0);
        ac[k - i] = (char)0;
        cursor1 = cursor2;
        cursor = cursor2;
        al[0] = cursor2.getLong(5);
        cursor1 = cursor2;
        cursor = cursor2;
        al[1] = cursor2.getLong(6);
        if(al[0] == 0L)
            al[0] = al[1];
        if(cursor2 != null)
            cursor2.close();
        return true;
        if(cursor2 != null)
            cursor2.close();
_L2:
        return false;
        ai;
        cursor = cursor1;
        Log.e("MtpDatabase", "RemoteException in getObjectInfo", ai);
        if(cursor1 != null)
            cursor1.close();
        if(true) goto _L2; else goto _L1
_L1:
        ai;
        if(cursor != null)
            cursor.close();
        throw ai;
    }

    private int[] getObjectList(int i, int j, int k)
    {
        Cursor cursor;
        Cursor cursor1;
        cursor = null;
        cursor1 = null;
        Cursor cursor2 = createObjectQuery(i, j, k);
        if(cursor2 == null)
        {
            if(cursor2 != null)
                cursor2.close();
            return null;
        }
        cursor1 = cursor2;
        cursor = cursor2;
        j = cursor2.getCount();
        if(j <= 0)
            break MISSING_BLOCK_LABEL_131;
        cursor1 = cursor2;
        cursor = cursor2;
        int ai[] = new int[j];
        i = 0;
_L2:
        if(i >= j)
            break; /* Loop/switch isn't completed */
        cursor1 = cursor2;
        cursor = cursor2;
        cursor2.moveToNext();
        cursor1 = cursor2;
        cursor = cursor2;
        ai[i] = cursor2.getInt(0);
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        if(cursor2 != null)
            cursor2.close();
        return ai;
        if(cursor2 != null)
            cursor2.close();
_L4:
        return null;
        RemoteException remoteexception;
        remoteexception;
        cursor = cursor1;
        Log.e("MtpDatabase", "RemoteException in getObjectList", remoteexception);
        if(cursor1 != null)
            cursor1.close();
        if(true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        if(cursor != null)
            cursor.close();
        throw exception;
    }

    private MtpPropertyList getObjectPropertyList(int i, int j, int k, int l, int i1)
    {
        if(l != 0)
            return new MtpPropertyList(0, 43015);
        if(k != -1) goto _L2; else goto _L1
_L1:
        Object obj;
        k = j;
        if(j == 0)
        {
            k = j;
            if(i != 0)
            {
                k = j;
                if(i != -1)
                    k = getObjectFormat(i);
            }
        }
        MtpPropertyGroup mtppropertygroup = (MtpPropertyGroup)mPropertyGroupsByFormat.get(Integer.valueOf(k));
        obj = mtppropertygroup;
        l = k;
        if(mtppropertygroup == null)
        {
            obj = getSupportedObjectProperties(k);
            obj = new MtpPropertyGroup(this, mMediaProvider, mVolumeName, ((int []) (obj)));
            mPropertyGroupsByFormat.put(Integer.valueOf(k), obj);
            l = k;
        }
_L4:
        return ((MtpPropertyGroup) (obj)).getPropertyList(i, l, i1);
_L2:
        MtpPropertyGroup mtppropertygroup1 = (MtpPropertyGroup)mPropertyGroupsByProperty.get(Integer.valueOf(k));
        obj = mtppropertygroup1;
        l = j;
        if(mtppropertygroup1 == null)
        {
            obj = new MtpPropertyGroup(this, mMediaProvider, mVolumeName, new int[] {
                k
            });
            mPropertyGroupsByProperty.put(Integer.valueOf(k), obj);
            l = j;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private int[] getObjectReferences(int i)
    {
        Object obj;
        Cursor cursor;
        Cursor cursor1;
        obj = android.provider.MediaStore.Files.getMtpReferencesUri(mVolumeName, i);
        cursor = null;
        cursor1 = null;
        obj = mMediaProvider.query(((Uri) (obj)), ID_PROJECTION, null, null, null, null);
        if(obj == null)
        {
            if(obj != null)
                ((Cursor) (obj)).close();
            return null;
        }
        cursor1 = ((Cursor) (obj));
        cursor = ((Cursor) (obj));
        int j = ((Cursor) (obj)).getCount();
        if(j <= 0)
            break MISSING_BLOCK_LABEL_131;
        cursor1 = ((Cursor) (obj));
        cursor = ((Cursor) (obj));
        int ai[] = new int[j];
        i = 0;
_L2:
        if(i >= j)
            break; /* Loop/switch isn't completed */
        cursor1 = ((Cursor) (obj));
        cursor = ((Cursor) (obj));
        ((Cursor) (obj)).moveToNext();
        cursor1 = ((Cursor) (obj));
        cursor = ((Cursor) (obj));
        ai[i] = ((Cursor) (obj)).getInt(0);
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        if(obj != null)
            ((Cursor) (obj)).close();
        return ai;
        if(obj != null)
            ((Cursor) (obj)).close();
_L4:
        return null;
        RemoteException remoteexception;
        remoteexception;
        cursor = cursor1;
        Log.e("MtpDatabase", "RemoteException in getObjectList", remoteexception);
        if(cursor1 != null)
            cursor1.close();
        if(true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        if(cursor != null)
            cursor.close();
        throw exception;
    }

    private int[] getSupportedCaptureFormats()
    {
        return null;
    }

    private int[] getSupportedDeviceProperties()
    {
        return (new int[] {
            54273, 54274, 20483, 20481, 54279
        });
    }

    private int[] getSupportedObjectProperties(int i)
    {
        switch(i)
        {
        default:
            return FILE_PROPERTIES;

        case 12296: 
        case 12297: 
        case 47361: 
        case 47362: 
        case 47363: 
            return AUDIO_PROPERTIES;

        case 12299: 
        case 47489: 
        case 47492: 
            return VIDEO_PROPERTIES;

        case 14337: 
        case 14340: 
        case 14343: 
        case 14347: 
        case 14353: 
        case 14354: 
            return IMAGE_PROPERTIES;
        }
    }

    private int[] getSupportedPlaybackFormats()
    {
        return (new int[] {
            12288, 12289, 12292, 12293, 12296, 12297, 12299, 14337, 14338, 14340, 
            14343, 14344, 14347, 14349, 47361, 47362, 47363, 47490, 47491, 47492, 
            47621, 47632, 47633, 47636, 47746, 47366, 14353, 14354
        });
    }

    private boolean inStorageRoot(String s)
    {
        Object obj;
        obj = JVM INSTR new #537 <Class File>;
        ((File) (obj)).File(s);
        obj = ((File) (obj)).getCanonicalPath();
        s = mStorageMap.keySet().iterator();
        boolean flag;
        do
        {
            if(!s.hasNext())
                break MISSING_BLOCK_LABEL_57;
            flag = ((String) (obj)).startsWith((String)s.next());
        } while(!flag);
        return true;
        s;
        return false;
    }

    private boolean inStorageSubDirectory(String s)
    {
        if(mSubDirectories == null)
            return true;
        if(s == null)
            return false;
        boolean flag = false;
        int i = s.length();
        boolean flag1;
        for(int j = 0; j < mSubDirectories.length && flag ^ true; flag = flag1)
        {
            String s1 = mSubDirectories[j];
            int k = s1.length();
            flag1 = flag;
            if(k < i)
            {
                flag1 = flag;
                if(s.charAt(k) == '/')
                {
                    flag1 = flag;
                    if(s.startsWith(s1))
                        flag1 = true;
                }
            }
            j++;
        }

        return flag;
    }

    private void initDeviceProperties(Context context)
    {
        mDeviceProperties = context.getSharedPreferences("device-properties", 0);
        if(!context.getDatabasePath("device-properties").exists()) goto _L2; else goto _L1
_L1:
        SQLiteDatabase sqlitedatabase;
        SQLiteDatabase sqlitedatabase1;
        Object obj;
        android.content.SharedPreferences.Editor editor;
        Object obj1;
        Object obj2;
        Object obj3;
        sqlitedatabase = null;
        sqlitedatabase1 = null;
        obj = null;
        editor = null;
        obj1 = null;
        obj2 = obj1;
        obj3 = editor;
        SQLiteDatabase sqlitedatabase2 = context.openOrCreateDatabase("device-properties", 0, null);
        obj3 = obj;
        if(sqlitedatabase2 == null) goto _L4; else goto _L3
_L3:
        sqlitedatabase1 = sqlitedatabase2;
        obj2 = obj1;
        sqlitedatabase = sqlitedatabase2;
        obj3 = editor;
        obj1 = sqlitedatabase2.query("properties", new String[] {
            "_id", "code", "value"
        }, null, null, null, null, null);
        obj3 = obj1;
        if(obj1 == null) goto _L4; else goto _L5
_L5:
        sqlitedatabase1 = sqlitedatabase2;
        obj2 = obj1;
        sqlitedatabase = sqlitedatabase2;
        obj3 = obj1;
        editor = mDeviceProperties.edit();
_L7:
        sqlitedatabase1 = sqlitedatabase2;
        obj2 = obj1;
        sqlitedatabase = sqlitedatabase2;
        obj3 = obj1;
        if(!((Cursor) (obj1)).moveToNext())
            break; /* Loop/switch isn't completed */
        sqlitedatabase1 = sqlitedatabase2;
        obj2 = obj1;
        sqlitedatabase = sqlitedatabase2;
        obj3 = obj1;
        editor.putString(((Cursor) (obj1)).getString(1), ((Cursor) (obj1)).getString(2));
        if(true) goto _L7; else goto _L6
        obj1;
        sqlitedatabase = sqlitedatabase1;
        obj3 = obj2;
        Log.e("MtpDatabase", "failed to migrate device properties", ((Throwable) (obj1)));
        if(obj2 != null)
            ((Cursor) (obj2)).close();
        if(sqlitedatabase1 != null)
            sqlitedatabase1.close();
_L9:
        context.deleteDatabase("device-properties");
_L2:
        return;
_L6:
        sqlitedatabase1 = sqlitedatabase2;
        obj2 = obj1;
        sqlitedatabase = sqlitedatabase2;
        obj3 = obj1;
        editor.commit();
        obj3 = obj1;
_L4:
        if(obj3 != null)
            ((Cursor) (obj3)).close();
        if(sqlitedatabase2 != null)
            sqlitedatabase2.close();
        if(true) goto _L9; else goto _L8
_L8:
        context;
        if(obj3 != null)
            ((Cursor) (obj3)).close();
        if(sqlitedatabase != null)
            sqlitedatabase.close();
        throw context;
    }

    private boolean isStorageSubDirectory(String s)
    {
        if(mSubDirectories == null)
            return false;
        for(int i = 0; i < mSubDirectories.length; i++)
            if(s.equals(mSubDirectories[i]))
                return true;

        return false;
    }

    private final native void native_finalize();

    private final native void native_setup();

    private int renameFile(int i, String s)
    {
        Object obj;
        Object obj1;
        Object obj2;
        String as[];
        obj = null;
        obj1 = null;
        obj2 = null;
        as = new String[1];
        as[0] = Integer.toString(i);
        Cursor cursor = mMediaProvider.query(mObjectsUri, PATH_PROJECTION, "_id=?", as, null, null);
        String s1;
        s1 = obj2;
        if(cursor == null)
            break MISSING_BLOCK_LABEL_91;
        s1 = obj2;
        obj1 = cursor;
        obj = cursor;
        if(!cursor.moveToNext())
            break MISSING_BLOCK_LABEL_91;
        obj1 = cursor;
        obj = cursor;
        s1 = cursor.getString(1);
        if(cursor != null)
            cursor.close();
        if(s1 == null)
            return 8201;
        break MISSING_BLOCK_LABEL_155;
        s;
        obj = obj1;
        Log.e("MtpDatabase", "RemoteException in getObjectFilePath", s);
        if(obj1 != null)
            ((Cursor) (obj1)).close();
        return 8194;
        s;
        if(obj != null)
            ((Cursor) (obj)).close();
        throw s;
        if(isStorageSubDirectory(s1))
            return 8205;
        obj1 = new File(s1);
        i = s1.lastIndexOf('/');
        if(i <= 1)
            return 8194;
        s = (new StringBuilder()).append(s1.substring(0, i + 1)).append(s).toString();
        obj = new File(s);
        if(!((File) (obj1)).renameTo(((File) (obj))))
        {
            Log.w("MtpDatabase", (new StringBuilder()).append("renaming ").append(s1).append(" to ").append(s).append(" failed").toString());
            return 8194;
        }
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("_data", s);
        boolean flag = false;
        try
        {
            i = mMediaProvider.update(mObjectsUri, contentvalues, "_id=?", as);
        }
        catch(RemoteException remoteexception1)
        {
            Log.e("MtpDatabase", "RemoteException in mMediaProvider.update", remoteexception1);
            i = ((flag) ? 1 : 0);
        }
        if(i == 0)
        {
            Log.e("MtpDatabase", (new StringBuilder()).append("Unable to update path for ").append(s1).append(" to ").append(s).toString());
            ((File) (obj)).renameTo(((File) (obj1)));
            return 8194;
        }
        if(!((File) (obj)).isDirectory()) goto _L2; else goto _L1
_L1:
        if(((File) (obj1)).getName().startsWith(".") && s.startsWith(".") ^ true)
            try
            {
                mMediaProvider.call("unhide", s, null);
            }
            // Misplaced declaration of an exception variable
            catch(Object obj1)
            {
                Log.e("MtpDatabase", (new StringBuilder()).append("failed to unhide/rescan for ").append(s).toString());
            }
_L4:
        return 8193;
_L2:
        if(((File) (obj1)).getName().toLowerCase(Locale.US).equals(".nomedia") && s.toLowerCase(Locale.US).equals(".nomedia") ^ true)
            try
            {
                mMediaProvider.call("unhide", ((File) (obj1)).getParent(), null);
            }
            catch(RemoteException remoteexception)
            {
                Log.e("MtpDatabase", (new StringBuilder()).append("failed to unhide/rescan for ").append(s).toString());
            }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void sessionEnded()
    {
        if(mDatabaseModified)
        {
            mUserContext.sendBroadcast(new Intent("android.provider.action.MTP_SESSION_END"));
            mDatabaseModified = false;
        }
    }

    private void sessionStarted()
    {
        mDatabaseModified = false;
    }

    private int setDeviceProperty(int i, long l, String s)
    {
        android.content.SharedPreferences.Editor editor;
        switch(i)
        {
        default:
            return 8202;

        case 54273: 
        case 54274: 
            editor = mDeviceProperties.edit();
            break;
        }
        editor.putString(Integer.toString(i), s);
        if(editor.commit())
            i = 8193;
        else
            i = 8194;
        return i;
    }

    private int setObjectProperty(int i, int j, long l, String s)
    {
        switch(j)
        {
        default:
            return 43018;

        case 56327: 
            return renameFile(i, s);
        }
    }

    private int setObjectReferences(int i, int ai[])
    {
        Uri uri;
        ContentValues acontentvalues[];
        mDatabaseModified = true;
        uri = android.provider.MediaStore.Files.getMtpReferencesUri(mVolumeName, i);
        int j = ai.length;
        acontentvalues = new ContentValues[j];
        for(i = 0; i < j; i++)
        {
            ContentValues contentvalues = new ContentValues();
            contentvalues.put("_id", Integer.valueOf(ai[i]));
            acontentvalues[i] = contentvalues;
        }

        i = mMediaProvider.bulkInsert(uri, acontentvalues);
        if(i > 0)
            return 8193;
        break MISSING_BLOCK_LABEL_98;
        ai;
        Log.e("MtpDatabase", "RemoteException in setObjectReferences", ai);
        return 8194;
    }

    public void addStorage(MtpStorage mtpstorage)
    {
        mStorageMap.put(mtpstorage.getPath(), mtpstorage);
    }

    public void close()
    {
        mCloseGuard.close();
        if(mClosed.compareAndSet(false, true))
        {
            mMediaScanner.close();
            mMediaProvider.close();
            native_finalize();
        }
    }

    protected void finalize()
        throws Throwable
    {
        if(mCloseGuard != null)
            mCloseGuard.warnIfOpen();
        close();
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public void removeStorage(MtpStorage mtpstorage)
    {
        mStorageMap.remove(mtpstorage.getPath());
    }

    public void setServer(MtpServer mtpserver)
    {
        mServer = mtpserver;
        try
        {
            mContext.unregisterReceiver(mBatteryReceiver);
        }
        catch(IllegalArgumentException illegalargumentexception) { }
        if(mtpserver != null)
            mContext.registerReceiver(mBatteryReceiver, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
    }

    static final int AUDIO_PROPERTIES[] = {
        56321, 56322, 56323, 56324, 56327, 56329, 56331, 56385, 56388, 56544, 
        56398, 56390, 56474, 56475, 56459, 56473, 56457, 56460, 56470, 56985, 
        56978, 56986, 56980, 56979
    };
    private static final int DEVICE_PROPERTIES_DATABASE_VERSION = 1;
    static final int FILE_PROPERTIES[] = {
        56321, 56322, 56323, 56324, 56327, 56329, 56331, 56385, 56388, 56544, 
        56398
    };
    private static final String FORMAT_PARENT_WHERE = "format=? AND parent=?";
    private static final String FORMAT_PROJECTION[] = {
        "_id", "format"
    };
    private static final String FORMAT_WHERE = "format=?";
    private static final String ID_PROJECTION[] = {
        "_id"
    };
    private static final String ID_WHERE = "_id=?";
    static final int IMAGE_PROPERTIES[] = {
        56321, 56322, 56323, 56324, 56327, 56329, 56331, 56385, 56388, 56544, 
        56398, 56392
    };
    private static final String OBJECT_INFO_PROJECTION[] = {
        "_id", "storage_id", "format", "parent", "_data", "date_added", "date_modified"
    };
    private static final String PARENT_WHERE = "parent=?";
    private static final String PATH_FORMAT_PROJECTION[] = {
        "_id", "_data", "format"
    };
    private static final String PATH_PROJECTION[] = {
        "_id", "_data"
    };
    private static final String PATH_WHERE = "_data=?";
    private static final String STORAGE_FORMAT_PARENT_WHERE = "storage_id=? AND format=? AND parent=?";
    private static final String STORAGE_FORMAT_WHERE = "storage_id=? AND format=?";
    private static final String STORAGE_PARENT_WHERE = "storage_id=? AND parent=?";
    private static final String STORAGE_WHERE = "storage_id=?";
    private static final String TAG = "MtpDatabase";
    static final int VIDEO_PROPERTIES[] = {
        56321, 56322, 56323, 56324, 56327, 56329, 56331, 56385, 56388, 56544, 
        56398, 56390, 56474, 56457, 56392
    };
    private int mBatteryLevel;
    private BroadcastReceiver mBatteryReceiver;
    private int mBatteryScale;
    private final CloseGuard mCloseGuard = CloseGuard.get();
    private final AtomicBoolean mClosed = new AtomicBoolean();
    private final Context mContext;
    private boolean mDatabaseModified;
    private SharedPreferences mDeviceProperties;
    private int mDeviceType;
    private final ContentProviderClient mMediaProvider;
    private final MediaScanner mMediaScanner;
    private final String mMediaStoragePath;
    private long mNativeContext;
    private final Uri mObjectsUri;
    private final String mPackageName;
    private final HashMap mPropertyGroupsByFormat = new HashMap();
    private final HashMap mPropertyGroupsByProperty = new HashMap();
    private MtpServer mServer;
    private final HashMap mStorageMap = new HashMap();
    private final String mSubDirectories[];
    private String mSubDirectoriesWhere;
    private String mSubDirectoriesWhereArgs[];
    private final Context mUserContext;
    private final String mVolumeName;

    static 
    {
        System.loadLibrary("media_jni");
    }
}
