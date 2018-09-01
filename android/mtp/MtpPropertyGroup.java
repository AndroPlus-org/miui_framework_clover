// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.mtp;

import android.content.ContentProviderClient;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.util.Log;
import java.util.ArrayList;

// Referenced classes of package android.mtp:
//            MtpPropertyList, MtpDatabase

class MtpPropertyGroup
{
    private class Property
    {

        int code;
        int column;
        final MtpPropertyGroup this$0;
        int type;

        Property(int i, int j, int k)
        {
            this$0 = MtpPropertyGroup.this;
            super();
            code = i;
            type = j;
            column = k;
        }
    }


    public MtpPropertyGroup(MtpDatabase mtpdatabase, ContentProviderClient contentproviderclient, String s, int ai[])
    {
        mDatabase = mtpdatabase;
        mProvider = contentproviderclient;
        mVolumeName = s;
        mUri = android.provider.MediaStore.Files.getMtpObjectsUri(s);
        int i = ai.length;
        mtpdatabase = new ArrayList(i);
        mtpdatabase.add("_id");
        mProperties = new Property[i];
        for(int j = 0; j < i; j++)
            mProperties[j] = createProperty(ai[j], mtpdatabase);

        i = mtpdatabase.size();
        mColumns = new String[i];
        for(int k = 0; k < i; k++)
            mColumns[k] = (String)mtpdatabase.get(k);

    }

    private Property createProperty(int i, ArrayList arraylist)
    {
        String s = null;
        i;
        JVM INSTR lookupswitch 25: default 212
    //                   56321: 268
    //                   56322: 278
    //                   56323: 287
    //                   56324: 293
    //                   56327: 303
    //                   56329: 323
    //                   56331: 353
    //                   56385: 363
    //                   56388: 313
    //                   56390: 402
    //                   56392: 443
    //                   56398: 333
    //                   56457: 373
    //                   56459: 383
    //                   56460: 426
    //                   56470: 433
    //                   56473: 343
    //                   56474: 409
    //                   56475: 416
    //                   56544: 392
    //                   56978: 460
    //                   56979: 453
    //                   56980: 460
    //                   56985: 453
    //                   56986: 453;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L22 _L23 _L23
_L22:
        break MISSING_BLOCK_LABEL_460;
_L1:
        int j;
        j = 0;
        Log.e("MtpPropertyGroup", (new StringBuilder()).append("unsupported property ").append(i).toString());
_L24:
        if(s != null)
        {
            arraylist.add(s);
            return new Property(i, j, arraylist.size() - 1);
        } else
        {
            return new Property(i, j, -1);
        }
_L2:
        s = "storage_id";
        j = 6;
          goto _L24
_L3:
        s = "format";
        j = 4;
          goto _L24
_L4:
        j = 4;
          goto _L24
_L5:
        s = "_size";
        j = 8;
          goto _L24
_L6:
        s = "_data";
        j = 65535;
          goto _L24
_L10:
        s = "title";
        j = 65535;
          goto _L24
_L7:
        s = "date_modified";
        j = 65535;
          goto _L24
_L13:
        s = "date_added";
        j = 65535;
          goto _L24
_L18:
        s = "year";
        j = 65535;
          goto _L24
_L8:
        s = "parent";
        j = 6;
          goto _L24
_L9:
        s = "storage_id";
        j = 10;
          goto _L24
_L14:
        s = "duration";
        j = 6;
          goto _L24
_L15:
        s = "track";
        j = 4;
          goto _L24
_L21:
        s = "_display_name";
        j = 65535;
          goto _L24
_L11:
        j = 65535;
          goto _L24
_L19:
        j = 65535;
          goto _L24
_L20:
        s = "album_artist";
        j = 65535;
          goto _L24
_L16:
        j = 65535;
          goto _L24
_L17:
        s = "composer";
        j = 65535;
          goto _L24
_L12:
        s = "description";
        j = 65535;
          goto _L24
_L23:
        j = 6;
          goto _L24
        j = 4;
          goto _L24
    }

    private native String format_date_time(long l);

    private static String nameFromPath(String s)
    {
        int i = 0;
        int j = s.lastIndexOf('/');
        if(j >= 0)
            i = j + 1;
        int k = s.length();
        j = k;
        if(k - i > 255)
            j = i + 255;
        return s.substring(i, j);
    }

    private String queryAudio(int i, String s)
    {
        Object obj;
        String s1;
        String s2;
        String s3;
        obj = null;
        s1 = null;
        s2 = s1;
        s3 = obj;
        ContentProviderClient contentproviderclient;
        Uri uri;
        String s4;
        try
        {
            contentproviderclient = mProvider;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            if(s2 != null)
                s2.close();
            return null;
        }
        s2 = s1;
        s3 = obj;
        uri = android.provider.MediaStore.Audio.Media.getContentUri(mVolumeName);
        s2 = s1;
        s3 = obj;
        s4 = Integer.toString(i);
        s2 = s1;
        s3 = obj;
        s = contentproviderclient.query(uri, new String[] {
            "_id", s
        }, "_id=?", new String[] {
            s4
        }, null, null);
        if(s == null)
            break MISSING_BLOCK_LABEL_135;
        s2 = s;
        s3 = s;
        if(!s.moveToNext())
            break MISSING_BLOCK_LABEL_135;
        s2 = s;
        s3 = s;
        s1 = s.getString(1);
        if(s != null)
            s.close();
        return s1;
        if(s != null)
            s.close();
        return "";
        s;
        if(s3 != null)
            s3.close();
        throw s;
    }

    private String queryGenre(int i)
    {
        String s;
        Cursor cursor;
        Cursor cursor1;
        Cursor cursor2;
        s = null;
        cursor = null;
        cursor1 = cursor;
        cursor2 = s;
        Uri uri = android.provider.MediaStore.Audio.Genres.getContentUriForAudioId(mVolumeName, i);
        cursor1 = cursor;
        cursor2 = s;
        cursor = mProvider.query(uri, new String[] {
            "_id", "name"
        }, null, null, null, null);
        if(cursor == null)
            break MISSING_BLOCK_LABEL_99;
        cursor1 = cursor;
        cursor2 = cursor;
        if(!cursor.moveToNext())
            break MISSING_BLOCK_LABEL_99;
        cursor1 = cursor;
        cursor2 = cursor;
        s = cursor.getString(1);
        if(cursor != null)
            cursor.close();
        return s;
        if(cursor != null)
            cursor.close();
        return "";
        Exception exception;
        exception;
        cursor2 = cursor1;
        Log.e("MtpPropertyGroup", "queryGenre exception", exception);
        if(cursor1 != null)
            cursor1.close();
        return null;
        Exception exception1;
        exception1;
        if(cursor2 != null)
            cursor2.close();
        throw exception1;
    }

    private Long queryLong(int i, String s)
    {
        Object obj;
        Long long1;
        String s1;
        String s2;
        obj = null;
        long1 = null;
        s1 = long1;
        s2 = obj;
        ContentProviderClient contentproviderclient = mProvider;
        s1 = long1;
        s2 = obj;
        Uri uri = mUri;
        s1 = long1;
        s2 = obj;
        String s3 = Integer.toString(i);
        s1 = long1;
        s2 = obj;
        s = contentproviderclient.query(uri, new String[] {
            "_id", s
        }, "_id=?", new String[] {
            s3
        }, null, null);
        if(s == null)
            break MISSING_BLOCK_LABEL_139;
        s1 = s;
        s2 = s;
        if(!s.moveToNext())
            break MISSING_BLOCK_LABEL_139;
        s1 = s;
        s2 = s;
        long1 = new Long(s.getLong(1));
        if(s != null)
            s.close();
        return long1;
        if(s != null)
            s.close();
_L2:
        return null;
        s;
        if(s1 != null)
            s1.close();
        if(true) goto _L2; else goto _L1
_L1:
        s;
        if(s2 != null)
            s2.close();
        throw s;
    }

    private String queryString(int i, String s)
    {
        Object obj;
        String s1;
        String s2;
        String s3;
        obj = null;
        s1 = null;
        s2 = s1;
        s3 = obj;
        ContentProviderClient contentproviderclient;
        Uri uri;
        String s4;
        try
        {
            contentproviderclient = mProvider;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            if(s2 != null)
                s2.close();
            return null;
        }
        s2 = s1;
        s3 = obj;
        uri = mUri;
        s2 = s1;
        s3 = obj;
        s4 = Integer.toString(i);
        s2 = s1;
        s3 = obj;
        s = contentproviderclient.query(uri, new String[] {
            "_id", s
        }, "_id=?", new String[] {
            s4
        }, null, null);
        if(s == null)
            break MISSING_BLOCK_LABEL_132;
        s2 = s;
        s3 = s;
        if(!s.moveToNext())
            break MISSING_BLOCK_LABEL_132;
        s2 = s;
        s3 = s;
        s1 = s.getString(1);
        if(s != null)
            s.close();
        return s1;
        if(s != null)
            s.close();
        return "";
        s;
        if(s3 != null)
            s3.close();
        throw s;
    }

    MtpPropertyList getPropertyList(int i, int j, int k)
    {
        Object obj;
        MtpPropertyList mtppropertylist;
        Object obj2;
        String s;
        Object obj3;
        Object obj4;
        if(k > 1)
            return new MtpPropertyList(0, 43016);
        String as[];
        if(j == 0)
        {
            if(i == -1)
            {
                obj = null;
                as = null;
            } else
            {
                as = new String[1];
                as[0] = Integer.toString(i);
                if(k == 1)
                    obj = "parent=?";
                else
                    obj = "_id=?";
            }
        } else
        if(i == -1)
        {
            obj = "format=?";
            as = new String[1];
            as[0] = Integer.toString(j);
        } else
        {
            as = new String[2];
            as[0] = Integer.toString(i);
            as[1] = Integer.toString(j);
            if(k == 1)
                obj = "parent=? AND format=?";
            else
                obj = "_id=? AND format=?";
        }
_L40:
        mtppropertylist = null;
        obj2 = null;
        s = null;
        if(k <= 0 && i != -1) goto _L2; else goto _L1
_L1:
        obj3 = mtppropertylist;
        obj4 = obj2;
        as = mProvider.query(mUri, mColumns, ((String) (obj)), as, null, null);
        obj = as;
        if(as != null)
            break MISSING_BLOCK_LABEL_246;
        obj3 = as;
        obj4 = as;
        obj = new MtpPropertyList(0, 8201);
        if(as != null)
            as.close();
        return ((MtpPropertyList) (obj));
_L2:
        obj3 = mtppropertylist;
        obj4 = obj2;
        if(mColumns.length > 1) goto _L1; else goto _L3
_L3:
        obj = s;
        if(obj != null) goto _L5; else goto _L4
_L4:
        j = 1;
_L26:
        obj3 = obj;
        obj4 = obj;
        mtppropertylist = JVM INSTR new #225 <Class MtpPropertyList>;
        obj3 = obj;
        obj4 = obj;
        mtppropertylist.MtpPropertyList(mProperties.length * j, 8193);
        boolean flag = false;
        k = i;
        i = ((flag) ? 1 : 0);
_L38:
        if(i >= j) goto _L7; else goto _L6
_L6:
        if(obj == null)
            break MISSING_BLOCK_LABEL_341;
        obj3 = obj;
        obj4 = obj;
        ((Cursor) (obj)).moveToNext();
        obj3 = obj;
        obj4 = obj;
        k = (int)((Cursor) (obj)).getLong(0);
        int l = 0;
_L25:
        obj3 = obj;
        obj4 = obj;
        if(l >= mProperties.length) goto _L9; else goto _L8
_L8:
        obj3 = obj;
        obj4 = obj;
        Object obj1 = mProperties[l];
        obj3 = obj;
        obj4 = obj;
        int i1 = ((Property) (obj1)).code;
        obj3 = obj;
        obj4 = obj;
        int j1 = ((Property) (obj1)).column;
        i1;
        JVM INSTR lookupswitch 16: default 548
    //                   56323: 616
    //                   56327: 671
    //                   56329: 899
    //                   56385: 1015
    //                   56388: 755
    //                   56390: 1085
    //                   56398: 899
    //                   56459: 1051
    //                   56460: 1139
    //                   56473: 932
    //                   56474: 1112
    //                   56978: 1221
    //                   56979: 1199
    //                   56980: 1221
    //                   56985: 1199
    //                   56986: 1199;
           goto _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L13 _L17 _L18 _L19 _L20 _L21 _L22 _L21 _L22 _L22
_L10:
        obj3 = obj;
        obj4 = obj;
        if(((Property) (obj1)).type != 65535) goto _L24; else goto _L23
_L23:
        obj3 = obj;
        obj4 = obj;
        mtppropertylist.append(k, i1, ((Cursor) (obj)).getString(j1));
_L27:
        l++;
          goto _L25
_L5:
        obj3 = obj;
        obj4 = obj;
        j = ((Cursor) (obj)).getCount();
          goto _L26
_L11:
        obj3 = obj;
        obj4 = obj;
        mtppropertylist.append(k, i1, 4, 0L);
          goto _L27
        obj;
        obj4 = obj3;
        obj = new MtpPropertyList(0, 8194);
        if(obj3 != null)
            ((Cursor) (obj3)).close();
        return ((MtpPropertyList) (obj));
_L12:
        obj3 = obj;
        obj4 = obj;
        obj1 = ((Cursor) (obj)).getString(j1);
        if(obj1 == null) goto _L29; else goto _L28
_L28:
        obj3 = obj;
        obj4 = obj;
        mtppropertylist.append(k, i1, nameFromPath(((String) (obj1))));
          goto _L27
        obj;
        if(obj4 != null)
            ((Cursor) (obj4)).close();
        throw obj;
_L29:
        obj3 = obj;
        obj4 = obj;
        mtppropertylist.setResult(8201);
          goto _L27
_L15:
        obj3 = obj;
        obj4 = obj;
        obj1 = ((Cursor) (obj)).getString(j1);
        obj3 = obj1;
        if(obj1 != null) goto _L31; else goto _L30
_L30:
        obj3 = obj;
        obj4 = obj;
        obj1 = queryString(k, "name");
        obj3 = obj1;
_L31:
        obj1 = obj3;
        if(obj3 != null)
            break MISSING_BLOCK_LABEL_854;
        obj3 = obj;
        obj4 = obj;
        s = queryString(k, "_data");
        obj1 = s;
        if(s == null)
            break MISSING_BLOCK_LABEL_854;
        obj3 = obj;
        obj4 = obj;
        obj1 = nameFromPath(s);
        if(obj1 == null) goto _L33; else goto _L32
_L32:
        obj3 = obj;
        obj4 = obj;
        mtppropertylist.append(k, i1, ((String) (obj1)));
          goto _L27
_L33:
        obj3 = obj;
        obj4 = obj;
        mtppropertylist.setResult(8201);
          goto _L27
_L13:
        obj3 = obj;
        obj4 = obj;
        mtppropertylist.append(k, i1, format_date_time(((Cursor) (obj)).getInt(j1)));
          goto _L27
_L19:
        obj3 = obj;
        obj4 = obj;
        j1 = ((Cursor) (obj)).getInt(j1);
        obj3 = obj;
        obj4 = obj;
        obj1 = JVM INSTR new #88  <Class StringBuilder>;
        obj3 = obj;
        obj4 = obj;
        ((StringBuilder) (obj1)).StringBuilder();
        obj3 = obj;
        obj4 = obj;
        mtppropertylist.append(k, i1, ((StringBuilder) (obj1)).append(Integer.toString(j1)).append("0101T000000").toString());
          goto _L27
_L14:
        obj3 = obj;
        obj4 = obj;
        mtppropertylist.append(k, i1, 10, (((Cursor) (obj)).getLong(j1) << 32) + (long)k);
          goto _L27
_L17:
        obj3 = obj;
        obj4 = obj;
        mtppropertylist.append(k, i1, 4, ((Cursor) (obj)).getInt(j1) % 1000);
          goto _L27
_L16:
        obj3 = obj;
        obj4 = obj;
        mtppropertylist.append(k, i1, queryAudio(k, "artist"));
          goto _L27
_L20:
        obj3 = obj;
        obj4 = obj;
        mtppropertylist.append(k, i1, queryAudio(k, "album"));
          goto _L27
_L18:
        obj3 = obj;
        obj4 = obj;
        obj1 = queryGenre(k);
        if(obj1 == null) goto _L35; else goto _L34
_L34:
        obj3 = obj;
        obj4 = obj;
        mtppropertylist.append(k, i1, ((String) (obj1)));
          goto _L27
_L35:
        obj3 = obj;
        obj4 = obj;
        mtppropertylist.setResult(8201);
          goto _L27
_L22:
        obj3 = obj;
        obj4 = obj;
        mtppropertylist.append(k, i1, 6, 0L);
          goto _L27
_L21:
        obj3 = obj;
        obj4 = obj;
        mtppropertylist.append(k, i1, 4, 0L);
          goto _L27
_L24:
        obj3 = obj;
        obj4 = obj;
        if(((Property) (obj1)).type != 0) goto _L37; else goto _L36
_L36:
        obj3 = obj;
        obj4 = obj;
        mtppropertylist.append(k, i1, ((Property) (obj1)).type, 0L);
          goto _L27
_L37:
        obj3 = obj;
        obj4 = obj;
        mtppropertylist.append(k, i1, ((Property) (obj1)).type, ((Cursor) (obj)).getLong(j1));
          goto _L27
_L9:
        i++;
          goto _L38
_L7:
        if(obj != null)
            ((Cursor) (obj)).close();
        return mtppropertylist;
        if(true) goto _L40; else goto _L39
_L39:
    }

    private static final String FORMAT_WHERE = "format=?";
    private static final String ID_FORMAT_WHERE = "_id=? AND format=?";
    private static final String ID_WHERE = "_id=?";
    private static final String PARENT_FORMAT_WHERE = "parent=? AND format=?";
    private static final String PARENT_WHERE = "parent=?";
    private static final String TAG = "MtpPropertyGroup";
    private String mColumns[];
    private final MtpDatabase mDatabase;
    private final Property mProperties[];
    private final ContentProviderClient mProvider;
    private final Uri mUri;
    private final String mVolumeName;
}
