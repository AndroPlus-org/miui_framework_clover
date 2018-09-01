// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.app;

import android.accounts.Account;
import android.content.*;
import android.database.Cursor;
import android.net.Uri;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.Log;
import java.util.*;
import miui.provider.BatchOperation;

public class RemoveDuplicateContacts
{
    public static class ContactsInfo
    {

        public int getCount()
        {
            return mCount;
        }

        public List getEmails()
        {
            return mEmails;
        }

        public String getName()
        {
            return mName;
        }

        public List getPhones()
        {
            return mPhones;
        }

        public long getPhotoId()
        {
            return mPhotoId;
        }

        public long getRawContactId()
        {
            return mRawContactId;
        }

        private int mCount;
        private List mEmails;
        private String mName;
        private List mPhones;
        private long mPhotoId;
        private long mRawContactId;

        public ContactsInfo(long l, String s, List list, List list1, long l1)
        {
            mPhotoId = l;
            String s1 = s;
            if(TextUtils.isEmpty(s))
                s1 = "";
            mName = s1;
            mPhones = list;
            mEmails = list1;
            mRawContactId = l1;
        }

        public ContactsInfo(RawContactData rawcontactdata, int i)
        {
            mPhotoId = rawcontactdata.mPhotoId;
            String s;
            if(TextUtils.isEmpty(rawcontactdata.mName))
                s = "";
            else
                s = rawcontactdata.mName;
            mName = s;
            mPhones = (List)rawcontactdata.getDatas().get("vnd.android.cursor.item/phone_v2");
            mCount = i;
            mRawContactId = rawcontactdata.getRawContactId();
            mEmails = (List)rawcontactdata.getDatas().get("vnd.android.cursor.item/email_v2");
        }
    }

    public static class GroupsData
    {

        public long id;
        public String sourceid;
        public String title;

        public GroupsData()
        {
        }
    }

    public static class MergeContacts
    {

        public ArrayList getContacts()
        {
            return mContacts;
        }

        public String getName()
        {
            return mName;
        }

        public boolean isChecked()
        {
            return mChecked;
        }

        public void setChecked(boolean flag)
        {
            mChecked = flag;
        }

        private boolean mChecked;
        private ArrayList mContacts;
        private String mName;

        public MergeContacts(ArrayList arraylist, String s)
        {
            mContacts = arraylist;
            mName = s;
            mChecked = true;
        }
    }

    public static class RawContactData
    {

        public void addData(String s, String s1)
        {
            Object obj;
            if(s1 == null)
                return;
            obj = (List)mDatas.get(s);
            if(obj != null) goto _L2; else goto _L1
_L1:
            obj = new ArrayList();
            ((List) (obj)).add(s1);
            mDatas.put(s, obj);
_L4:
            return;
_L2:
            if(!((List) (obj)).contains(s1))
                ((List) (obj)).add(s1);
            if(true) goto _L4; else goto _L3
_L3:
        }

        public boolean compare(RawContactData rawcontactdata)
        {
label0:
            {
                if(rawcontactdata == null)
                    return false;
                if(rawcontactdata.mDatas.size() != mDatas.size())
                    return false;
                Iterator iterator = mDatas.keySet().iterator();
                List list;
                ArrayList arraylist;
label1:
                do
                    do
                    {
                        if(!iterator.hasNext())
                            break label0;
                        String s = (String)iterator.next();
                        if(!rawcontactdata.mDatas.containsKey(s))
                            return false;
                        list = (List)mDatas.get(s);
                        arraylist = new ArrayList();
                        arraylist.addAll((Collection)rawcontactdata.mDatas.get(s));
                        if(list.size() != arraylist.size())
                            return false;
                        if(!"vnd.android.cursor.item/phone_v2".equals(s))
                            continue label1;
                        Iterator iterator1 = list.iterator();
                        while(iterator1.hasNext()) 
                        {
                            String s1 = (String)iterator1.next();
                            list = null;
                            Iterator iterator2 = arraylist.iterator();
                            Object obj;
                            do
                            {
label2:
                                {
                                    obj = list;
                                    if(!iterator2.hasNext())
                                        break label2;
                                    obj = (String)iterator2.next();
                                }
                            } while(!s1.equals(arraylist) && !PhoneNumberUtils.compare(s1, ((String) (obj))));
                            if(obj == null)
                                return false;
                            arraylist.remove(obj);
                        }
                    } while(true);
                while(list.equals(arraylist));
                return false;
            }
            return true;
        }

        public HashMap getDatas()
        {
            return mDatas;
        }

        public long getRawContactId()
        {
            return mRawContactId;
        }

        public boolean isDeleted()
        {
            return mDeleted;
        }

        public void setDeleted(boolean flag)
        {
            mDeleted = flag;
        }

        public void setRawContactId(long l)
        {
            mRawContactId = l;
        }

        public static final int HAS_DISPLAY_PHOTO = 100;
        public static final int HAS_PHOTO = 10;
        private HashMap mDatas;
        private boolean mDeleted;
        public int mN;
        public String mName;
        public long mPhotoId;
        public long mRawContactId;
        public String mSourceId;

        public RawContactData()
        {
            mDatas = new HashMap();
        }
    }

    public static interface RemoveDuplicateContactsListener
    {

        public abstract void onBegin(int i);

        public abstract void onEnd(boolean flag);

        public abstract void onProgress(int i);
    }


    public RemoveDuplicateContacts()
    {
    }

    public static List getDeletedRawContacts(Account account, ContentResolver contentresolver)
    {
        Object obj = account.name;
        String s = account.type;
        account = new ArrayList();
        obj = getNameWithRawContactIds(contentresolver, new String[] {
            obj, s
        });
        if(((HashMap) (obj)).size() > 0)
        {
            Iterator iterator = ((HashMap) (obj)).keySet().iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                Object obj1 = (String)iterator.next();
                List list = (List)((HashMap) (obj)).get(obj1);
                if(list.size() >= 2)
                {
                    obj1 = getNeedDeletedRawContacts(contentresolver, list, ((String) (obj1)));
                    if(obj1 != null)
                        account.addAll(((Collection) (obj1)));
                }
            } while(true);
        }
        return account;
    }

    private static EntityIterator getEntityByIds(ContentResolver contentresolver, List list)
    {
        contentresolver = contentresolver.query(CONTENT_URI, null, (new StringBuilder()).append("_id IN (").append(TextUtils.join(",", list)).append(")").toString(), null, null);
        if(contentresolver == null)
            return null;
        else
            return android.provider.ContactsContract.RawContacts.newEntityIterator(contentresolver);
    }

    private static List getGroups(Account account, ContentResolver contentresolver)
    {
        Object obj;
        obj = account.name;
        account = account.type;
        account = contentresolver.query(android.provider.ContactsContract.Groups.CONTENT_URI, new String[] {
            "_id", "title", "sourceid"
        }, "account_name = ? AND account_type = ? ", new String[] {
            obj, account
        }, "title,sourceid DESC");
        obj = new ArrayList();
        if(account == null)
            break MISSING_BLOCK_LABEL_141;
        for(; account.moveToNext(); ((List) (obj)).add(contentresolver))
        {
            contentresolver = JVM INSTR new #9   <Class RemoveDuplicateContacts$GroupsData>;
            contentresolver.GroupsData();
            contentresolver.id = account.getLong(0);
            contentresolver.title = account.getString(1);
            contentresolver.sourceid = account.getString(2);
        }

        break MISSING_BLOCK_LABEL_135;
        contentresolver;
        account.close();
        throw contentresolver;
        account.close();
        return ((List) (obj));
    }

    public static ArrayList getMergeRawContacts(Account aaccount[], ContentResolver contentresolver)
    {
        ArrayList arraylist;
        int i;
        int j;
        arraylist = new ArrayList();
        i = aaccount.length;
        j = 0;
_L2:
        Object obj;
        if(j >= i)
            break MISSING_BLOCK_LABEL_444;
        obj = aaccount[j];
        String s = ((Account) (obj)).name;
        String s2 = ((Account) (obj)).type;
        Log.d("RemoveDuplicateContacts", (new StringBuilder()).append("start scan raw_contact of account ").append(((Account) (obj)).name).toString());
        obj = getNameWithRawContactIds(contentresolver, new String[] {
            s, s2
        });
        if(((HashMap) (obj)).size() != 0)
            break; /* Loop/switch isn't completed */
_L4:
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        Iterator iterator = ((HashMap) (obj)).keySet().iterator();
_L6:
        if(!iterator.hasNext()) goto _L4; else goto _L3
_L3:
        String s1;
        Object obj1;
        s1 = (String)iterator.next();
        obj1 = (List)((HashMap) (obj)).get(s1);
        if(((List) (obj1)).size() < 2) goto _L6; else goto _L5
_L5:
        ArrayList arraylist1;
        obj1 = getEntityByIds(contentresolver, ((List) (obj1)));
        arraylist1 = new ArrayList();
_L14:
        Object obj2;
        long l;
        if(!((EntityIterator) (obj1)).hasNext())
            break; /* Loop/switch isn't completed */
        obj2 = (Entity)((EntityIterator) (obj1)).next();
        l = ((Entity) (obj2)).getEntityValues().getAsLong("_id").longValue();
        long l1 = 0L;
        ArrayList arraylist2;
        ArrayList arraylist3;
        Iterator iterator1;
        arraylist2 = JVM INSTR new #121 <Class ArrayList>;
        arraylist2.ArrayList();
        arraylist3 = JVM INSTR new #121 <Class ArrayList>;
        arraylist3.ArrayList();
        iterator1 = ((Entity) (obj2)).getSubValues().iterator();
_L9:
        ContentValues contentvalues;
        if(!iterator1.hasNext())
            break MISSING_BLOCK_LABEL_378;
        contentvalues = ((android.content.Entity.NamedContentValues)iterator1.next()).values;
        obj2 = contentvalues.getAsString("mimetype");
        if(!"vnd.android.cursor.item/photo".equals(obj2)) goto _L8; else goto _L7
_L7:
        l1 = contentvalues.getAsLong("_id").longValue();
          goto _L9
_L8:
        if(!"vnd.android.cursor.item/phone_v2".equals(obj2)) goto _L11; else goto _L10
_L10:
        arraylist2.add(contentvalues.getAsString("data1"));
          goto _L9
        aaccount;
        ((EntityIterator) (obj1)).close();
        throw aaccount;
_L11:
        if(!"vnd.android.cursor.item/email_v2".equals(obj2)) goto _L9; else goto _L12
_L12:
        arraylist3.add(contentvalues.getAsString("data1"));
          goto _L9
        ContactsInfo contactsinfo = JVM INSTR new #6   <Class RemoveDuplicateContacts$ContactsInfo>;
        contactsinfo.ContactsInfo(l1, s1, arraylist2, arraylist3, l);
        arraylist1.add(contactsinfo);
        if(true) goto _L14; else goto _L13
_L13:
        ((EntityIterator) (obj1)).close();
        if(arraylist1.size() > 1)
            arraylist.add(new MergeContacts(arraylist1, s1));
          goto _L6
          goto _L4
        return arraylist;
    }

    private static HashMap getNameWithRawContactIds(ContentResolver contentresolver, String as[])
    {
        HashMap hashmap;
        hashmap = new HashMap();
        contentresolver = contentresolver.query(android.provider.ContactsContract.RawContacts.CONTENT_URI, new String[] {
            "display_name", "_id"
        }, "deleted=0 AND account_name=? AND account_type=? AND data_set IS NULL ", as, null);
        if(contentresolver == null)
            return null;
_L2:
        long l;
        Object obj;
        if(!contentresolver.moveToNext())
            break; /* Loop/switch isn't completed */
        as = contentresolver.getString(0);
        l = contentresolver.getLong(1);
        obj = (List)hashmap.get(as);
        if(obj != null)
            break MISSING_BLOCK_LABEL_123;
        obj = JVM INSTR new #121 <Class ArrayList>;
        ((ArrayList) (obj)).ArrayList();
        ((List) (obj)).add(Long.valueOf(l));
        hashmap.put(as, obj);
        continue; /* Loop/switch isn't completed */
        as;
        contentresolver.close();
        throw as;
        ((List) (obj)).add(Long.valueOf(l));
        if(true) goto _L2; else goto _L1
_L1:
        contentresolver.close();
        return hashmap;
    }

    private static ArrayList getNeedDeletedRawContacts(ContentResolver contentresolver, List list, String s)
    {
        EntityIterator entityiterator;
        ArrayList arraylist;
        if(list == null || list.size() == 0)
            return null;
        System.currentTimeMillis();
        entityiterator = getEntityByIds(contentresolver, list);
        if(entityiterator == null)
            return null;
        arraylist = new ArrayList();
_L11:
        if(!entityiterator.hasNext()) goto _L2; else goto _L1
_L1:
        RawContactData rawcontactdata;
        Iterator iterator;
        contentresolver = (Entity)entityiterator.next();
        rawcontactdata = JVM INSTR new #15  <Class RemoveDuplicateContacts$RawContactData>;
        rawcontactdata.RawContactData();
        list = contentresolver.getEntityValues();
        rawcontactdata.setRawContactId(list.getAsLong("_id").longValue());
        rawcontactdata.mName = s;
        rawcontactdata.mSourceId = list.getAsString("sourceid");
        iterator = contentresolver.getSubValues().iterator();
_L7:
        if(!iterator.hasNext()) goto _L4; else goto _L3
_L3:
        String s1;
        contentresolver = ((android.content.Entity.NamedContentValues)iterator.next()).values;
        s1 = contentresolver.getAsString("mimetype");
        if(!"vnd.android.cursor.item/photo".equals(s1)) goto _L6; else goto _L5
_L5:
        rawcontactdata.mPhotoId = contentresolver.getAsLong("_id").longValue();
        int i;
        int j;
        if(contentresolver.containsKey("data14"))
            i = 100;
        else
            i = 10;
        rawcontactdata.mN = i;
          goto _L7
        list;
        contentresolver = JVM INSTR new #173 <Class StringBuilder>;
        contentresolver.StringBuilder();
        Log.w("RemoveDuplicateContacts", contentresolver.append("getNeedDeletedRawContacts(): ").append(list.getMessage()).toString());
        list.printStackTrace();
        entityiterator.close();
        break MISSING_BLOCK_LABEL_240;
_L6:
        if(!"vnd.android.cursor.item/group_membership".equals(s1))
            break MISSING_BLOCK_LABEL_334;
        long l = contentresolver.getAsLong("data1").longValue();
        list = (String)sGroups.get(Long.valueOf(l));
        contentresolver = list;
        if(list == null)
            contentresolver = "";
        rawcontactdata.addData(s1, contentresolver);
          goto _L7
        contentresolver;
        entityiterator.close();
        throw contentresolver;
        if(!"vnd.android.cursor.item/im".equals(s1)) goto _L9; else goto _L8
_L8:
        rawcontactdata.addData(s1, contentresolver.getAsString("data1"));
          goto _L7
_L9:
        if("vnd.android.cursor.item/name".equals(s1)) goto _L7; else goto _L10
_L10:
        if(!sOtherMimeTypes.contains(s1))
            break MISSING_BLOCK_LABEL_410;
        contentresolver = contentresolver.getAsString("data1");
        if(!TextUtils.isEmpty(contentresolver))
            rawcontactdata.addData(s1, contentresolver);
          goto _L7
        contentresolver = JVM INSTR new #173 <Class StringBuilder>;
        contentresolver.StringBuilder();
        Log.d("RemoveDuplicateContacts", contentresolver.append("ignore unknown mimetype ").append(s1).toString());
          goto _L7
_L4:
        arraylist.add(rawcontactdata);
          goto _L11
_L2:
        entityiterator.close();
        System.currentTimeMillis();
        j = arraylist.size();
        if(j < 2)
            return null;
        s = new ArrayList();
        i = 0;
_L13:
        if(i >= j - 1)
            break MISSING_BLOCK_LABEL_636;
        contentresolver = (RawContactData)arraylist.get(i);
        if(!contentresolver.isDeleted())
            break; /* Loop/switch isn't completed */
_L14:
        i++;
        if(true) goto _L13; else goto _L12
_L12:
        int k = i + 1;
        while(k < j) 
        {
            list = (RawContactData)arraylist.get(k);
            if(!list.isDeleted() && contentresolver.compare(list))
                if(((RawContactData) (list)).mSourceId == null)
                {
                    list.setDeleted(true);
                    s.add(list);
                } else
                {
label0:
                    {
                        if(((RawContactData) (contentresolver)).mN <= ((RawContactData) (list)).mN)
                            break label0;
                        list.setDeleted(true);
                        s.add(list);
                    }
                }
            k++;
        }
          goto _L14
        if(((RawContactData) (contentresolver)).mN < ((RawContactData) (list)).mN)
        {
            contentresolver.setDeleted(true);
            s.add(contentresolver);
        } else
        {
            contentresolver.setDeleted(true);
            s.add(contentresolver);
        }
          goto _L14
        System.currentTimeMillis();
        return s;
          goto _L7
    }

    public static int remove(Account aaccount[], ContentResolver contentresolver, RemoveDuplicateContactsListener removeduplicatecontactslistener, boolean flag)
    {
        miui/app/RemoveDuplicateContacts;
        JVM INSTR monitorenter ;
        if(aaccount == null || contentresolver == null)
            return 0;
        System.currentTimeMillis();
        boolean flag1;
        boolean flag2;
        flag1 = false;
        flag2 = false;
        Object obj;
        obj = JVM INSTR new #121 <Class ArrayList>;
        ((ArrayList) (obj)).ArrayList();
        if(removeduplicatecontactslistener == null)
            break MISSING_BLOCK_LABEL_47;
        removeduplicatecontactslistener.onBegin(0);
        int i = 0;
        int j = aaccount.length;
_L2:
        Object obj1;
        if(i >= j)
            break; /* Loop/switch isn't completed */
        obj1 = aaccount[i];
        sGroups.clear();
        GroupsData groupsdata;
        for(Iterator iterator = getGroups(((Account) (obj1)), contentresolver).iterator(); iterator.hasNext(); sGroups.put(Long.valueOf(groupsdata.id), groupsdata.title))
            groupsdata = (GroupsData)iterator.next();

        break MISSING_BLOCK_LABEL_137;
        aaccount;
        throw aaccount;
        ((List) (obj)).addAll(getDeletedRawContacts(((Account) (obj1)), contentresolver));
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        j = ((flag1) ? 1 : 0);
        if(obj == null) goto _L4; else goto _L3
_L3:
        j = ((flag1) ? 1 : 0);
        if(((List) (obj)).size() <= 0) goto _L4; else goto _L5
_L5:
        obj1 = JVM INSTR new #421 <Class BatchOperation>;
        ((BatchOperation) (obj1)).BatchOperation(contentresolver, "com.android.contacts");
        if(removeduplicatecontactslistener == null)
            break MISSING_BLOCK_LABEL_211;
        removeduplicatecontactslistener.onBegin(((List) (obj)).size());
        contentresolver = ((Iterable) (obj)).iterator();
        i = ((flag2) ? 1 : 0);
_L13:
        if(!contentresolver.hasNext()) goto _L7; else goto _L6
_L6:
        RawContactData rawcontactdata;
        rawcontactdata = (RawContactData)contentresolver.next();
        obj = ContentUris.withAppendedId(android.provider.ContactsContract.RawContacts.CONTENT_URI, rawcontactdata.mRawContactId).buildUpon().appendQueryParameter("caller_is_remove_duplicate", "true").build();
        aaccount = null;
        if(rawcontactdata.mSourceId != null) goto _L9; else goto _L8
_L8:
        aaccount = ContentProviderOperation.newDelete(((Uri) (obj)).buildUpon().appendQueryParameter("caller_is_syncadapter", "true").build()).build();
_L10:
        if(aaccount == null)
            break MISSING_BLOCK_LABEL_314;
        ((BatchOperation) (obj1)).add(aaccount);
        if(((BatchOperation) (obj1)).size() > 100)
            ((BatchOperation) (obj1)).execute();
        if(removeduplicatecontactslistener == null)
            break MISSING_BLOCK_LABEL_342;
        removeduplicatecontactslistener.onProgress(i);
        i++;
        continue; /* Loop/switch isn't completed */
_L9:
        if(flag)
            continue; /* Loop/switch isn't completed */
        aaccount = ContentProviderOperation.newDelete(((Uri) (obj))).build();
        if(true) goto _L10; else goto _L7
_L7:
        j = i;
        if(((BatchOperation) (obj1)).size() <= 0) goto _L4; else goto _L11
_L11:
        ((BatchOperation) (obj1)).execute();
        j = i;
_L4:
        if(removeduplicatecontactslistener == null)
            break MISSING_BLOCK_LABEL_397;
        removeduplicatecontactslistener.onEnd(true);
        System.currentTimeMillis();
        miui/app/RemoveDuplicateContacts;
        JVM INSTR monitorexit ;
        return j;
        if(true) goto _L13; else goto _L12
_L12:
    }

    public static void removeGroups(Account account, ContentResolver contentresolver)
    {
        Iterator iterator;
        HashSet hashset;
        List list = getGroups(account, contentresolver);
        if(list.size() <= 1)
            return;
        long l = 0L;
        int i = 0;
        while(i < list.size()) 
        {
            if(i == 0)
            {
                l = ((GroupsData)list.get(i)).id;
            } else
            {
                String s = ((GroupsData)list.get(i - 1)).title;
                String s1 = ((GroupsData)list.get(i)).title;
                long l3 = ((GroupsData)list.get(i)).id;
                Object obj1 = ((GroupsData)list.get(i)).sourceid;
                if(TextUtils.equals(s, s1))
                {
                    ContentValues contentvalues = new ContentValues();
                    contentvalues.put("data1", Long.valueOf(l));
                    contentresolver.update(android.provider.ContactsContract.Data.CONTENT_URI, contentvalues, "mimetype=? AND data1=?", new String[] {
                        "vnd.android.cursor.item/group_membership", String.valueOf(l3)
                    });
                    Log.d("RemoveDuplicateContacts", (new StringBuilder()).append("update contacts group from ").append(l3).append(" to ").append(l).toString());
                    if(obj1 == null)
                        obj1 = ContentUris.withAppendedId(android.provider.ContactsContract.Groups.CONTENT_URI, l3).buildUpon().appendQueryParameter("caller_is_syncadapter", "true").build();
                    else
                        obj1 = ContentUris.withAppendedId(android.provider.ContactsContract.Groups.CONTENT_URI, l3);
                    contentresolver.delete(((Uri) (obj1)), null, null);
                    Log.d("RemoveDuplicateContacts", (new StringBuilder()).append("delete group ").append(l3).toString());
                } else
                {
                    l = ((GroupsData)list.get(i)).id;
                }
            }
            i++;
        }
        list = getGroups(account, contentresolver);
        hashset = new HashSet();
        account = new HashSet();
        iterator = list.iterator();
_L4:
        Object obj;
        if(!iterator.hasNext())
            break; /* Loop/switch isn't completed */
        obj = (GroupsData)iterator.next();
        hashset.clear();
        long l1 = ((GroupsData) (obj)).id;
        obj = contentresolver.query(android.provider.ContactsContract.Data.CONTENT_URI, new String[] {
            "_id", "raw_contact_id"
        }, "mimetype=? AND data1=?", new String[] {
            "vnd.android.cursor.item/group_membership", String.valueOf(l1)
        }, null);
        if(obj == null)
            continue; /* Loop/switch isn't completed */
_L2:
        long l2;
        if(!((Cursor) (obj)).moveToNext())
            break; /* Loop/switch isn't completed */
        long l4 = ((Cursor) (obj)).getLong(0);
        l2 = ((Cursor) (obj)).getLong(1);
        if(hashset.contains(Long.valueOf(l2)))
        {
            account.add(Long.valueOf(l4));
            continue; /* Loop/switch isn't completed */
        }
        break MISSING_BLOCK_LABEL_494;
        account;
        ((Cursor) (obj)).close();
        throw account;
        hashset.add(Long.valueOf(l2));
        if(true) goto _L2; else goto _L1
_L1:
        ((Cursor) (obj)).close();
        if(true) goto _L4; else goto _L3
_L3:
        contentresolver = new BatchOperation(contentresolver, "com.android.contacts");
        account = account.iterator();
        do
        {
            if(!account.hasNext())
                break;
            Long long1 = (Long)account.next();
            contentresolver.add(ContentProviderOperation.newDelete(ContentUris.withAppendedId(android.provider.ContactsContract.Data.CONTENT_URI, long1.longValue())).build());
            if(contentresolver.size() > 100)
                contentresolver.execute();
        } while(true);
        if(contentresolver.size() > 0)
            contentresolver.execute();
        return;
    }

    public static final String CALLER_IS_REMOVE_DUPLICATE = "caller_is_remove_duplicate";
    private static final Uri CONTENT_URI;
    private static final boolean DBG = false;
    private static final String NAME_SELECTION = "deleted=0 AND account_name=? AND account_type=? AND data_set IS NULL ";
    public static final String TAG = "RemoveDuplicateContacts";
    private static final HashMap sGroups = new HashMap();
    private static final HashSet sOtherMimeTypes;

    static 
    {
        CONTENT_URI = android.provider.ContactsContract.RawContactsEntity.CONTENT_URI;
        sOtherMimeTypes = new HashSet();
        sOtherMimeTypes.add("vnd.android.cursor.item/phone_v2");
        sOtherMimeTypes.add("vnd.android.cursor.item/email_v2");
        sOtherMimeTypes.add("vnd.android.cursor.item/postal-address_v2");
        sOtherMimeTypes.add("vnd.android.cursor.item/organization");
        sOtherMimeTypes.add("vnd.android.cursor.item/website");
        sOtherMimeTypes.add("vnd.android.cursor.item/contact_event");
        sOtherMimeTypes.add("vnd.android.cursor.item/sip_address");
        sOtherMimeTypes.add("vnd.android.cursor.item/relation");
        sOtherMimeTypes.add("vnd.android.cursor.item/note");
        sOtherMimeTypes.add("vnd.android.cursor.item/nickname");
        sOtherMimeTypes.add("vnd.com.miui.cursor.item/gender");
        sOtherMimeTypes.add("vnd.com.miui.cursor.item/bloodType");
        sOtherMimeTypes.add("vnd.com.miui.cursor.item/constellation");
        sOtherMimeTypes.add("vnd.com.miui.cursor.item/animalSign");
        sOtherMimeTypes.add("vnd.com.miui.cursor.item/emotionStatus");
        sOtherMimeTypes.add("vnd.com.miui.cursor.item/interest");
        sOtherMimeTypes.add("vnd.com.miui.cursor.item/hobby");
        sOtherMimeTypes.add("vnd.com.miui.cursor.item/degree");
        sOtherMimeTypes.add("vnd.com.miui.cursor.item/schools");
        sOtherMimeTypes.add("vnd.com.miui.cursor.item/characteristic");
        sOtherMimeTypes.add("vnd.com.miui.cursor.item/xiaomiId");
        sOtherMimeTypes.add("vnd.com.miui.cursor.item/lunarBirthday");
    }
}
