// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.provider;

import android.content.*;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

// Referenced classes of package android.provider:
//            BaseColumns

public class Contacts
{
    public static final class ContactMethods
        implements BaseColumns, ContactMethodsColumns, PeopleColumns
    {

        public static Object decodeImProtocol(String s)
        {
            if(s == null)
                return null;
            if(s.startsWith("pre:"))
                return Integer.valueOf(Integer.parseInt(s.substring(4)));
            if(s.startsWith("custom:"))
                return s.substring(7);
            else
                throw new IllegalArgumentException((new StringBuilder()).append("the value is not a valid encoded protocol, ").append(s).toString());
        }

        public static String encodeCustomImProtocol(String s)
        {
            return (new StringBuilder()).append("custom:").append(s).toString();
        }

        public static String encodePredefinedImProtocol(int i)
        {
            return (new StringBuilder()).append("pre:").append(i).toString();
        }

        public static final CharSequence getDisplayLabel(Context context, int i, int j, CharSequence charsequence)
        {
            String s = "";
            i;
            JVM INSTR tableswitch 1 2: default 28
        //                       1 37
        //                       2 75;
               goto _L1 _L2 _L3
_L1:
            context = context.getString(0x104000f);
_L5:
            return context;
_L2:
            if(j != 0)
            {
                context = context.getResources().getTextArray(0x1070000);
                context = context[j - 1];
            } else
            {
                context = s;
                if(!TextUtils.isEmpty(charsequence))
                    context = charsequence;
            }
            continue; /* Loop/switch isn't completed */
_L3:
            if(j != 0)
            {
                context = context.getResources().getTextArray(0x1070004);
                context = context[j - 1];
            } else
            {
                context = s;
                if(!TextUtils.isEmpty(charsequence))
                    context = charsequence;
            }
            if(true) goto _L5; else goto _L4
_L4:
        }

        public static String lookupProviderNameFromId(int i)
        {
            switch(i)
            {
            default:
                return null;

            case 5: // '\005'
                return "GTalk";

            case 0: // '\0'
                return "AIM";

            case 1: // '\001'
                return "MSN";

            case 2: // '\002'
                return "Yahoo";

            case 6: // '\006'
                return "ICQ";

            case 7: // '\007'
                return "JABBER";

            case 3: // '\003'
                return "SKYPE";

            case 4: // '\004'
                return "QQ";
            }
        }

        public void addPostalLocation(Context context, long l, double d, double d1)
        {
            context = context.getContentResolver();
            ContentValues contentvalues = new ContentValues(2);
            contentvalues.put("data", Double.valueOf(d));
            contentvalues.put("aux_data", Double.valueOf(d1));
            long l1 = ContentUris.parseId(context.insert(CONTENT_URI, contentvalues));
            contentvalues.clear();
            contentvalues.put("aux_data", Long.valueOf(l1));
            context.update(ContentUris.withAppendedId(CONTENT_URI, l), contentvalues, null, null);
        }

        public static final String CONTENT_EMAIL_ITEM_TYPE = "vnd.android.cursor.item/email";
        public static final String CONTENT_EMAIL_TYPE = "vnd.android.cursor.dir/email";
        public static final Uri CONTENT_EMAIL_URI = Uri.parse("content://contacts/contact_methods/email");
        public static final String CONTENT_IM_ITEM_TYPE = "vnd.android.cursor.item/jabber-im";
        public static final String CONTENT_POSTAL_ITEM_TYPE = "vnd.android.cursor.item/postal-address";
        public static final String CONTENT_POSTAL_TYPE = "vnd.android.cursor.dir/postal-address";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/contact-methods";
        public static final Uri CONTENT_URI = Uri.parse("content://contacts/contact_methods");
        public static final String DEFAULT_SORT_ORDER = "name ASC";
        public static final String PERSON_ID = "person";
        public static final String POSTAL_LOCATION_LATITUDE = "data";
        public static final String POSTAL_LOCATION_LONGITUDE = "aux_data";
        public static final int PROTOCOL_AIM = 0;
        public static final int PROTOCOL_GOOGLE_TALK = 5;
        public static final int PROTOCOL_ICQ = 6;
        public static final int PROTOCOL_JABBER = 7;
        public static final int PROTOCOL_MSN = 1;
        public static final int PROTOCOL_QQ = 4;
        public static final int PROTOCOL_SKYPE = 3;
        public static final int PROTOCOL_YAHOO = 2;


        private ContactMethods()
        {
        }
    }

    static interface ContactMethods.ProviderNames
    {

        public static final String AIM = "AIM";
        public static final String GTALK = "GTalk";
        public static final String ICQ = "ICQ";
        public static final String JABBER = "JABBER";
        public static final String MSN = "MSN";
        public static final String QQ = "QQ";
        public static final String SKYPE = "SKYPE";
        public static final String XMPP = "XMPP";
        public static final String YAHOO = "Yahoo";
    }

    public static interface ContactMethodsColumns
    {

        public static final String AUX_DATA = "aux_data";
        public static final String DATA = "data";
        public static final String ISPRIMARY = "isprimary";
        public static final String KIND = "kind";
        public static final String LABEL = "label";
        public static final int MOBILE_EMAIL_TYPE_INDEX = 2;
        public static final String MOBILE_EMAIL_TYPE_NAME = "_AUTO_CELL";
        public static final String TYPE = "type";
        public static final int TYPE_CUSTOM = 0;
        public static final int TYPE_HOME = 1;
        public static final int TYPE_OTHER = 3;
        public static final int TYPE_WORK = 2;
    }

    public static final class Extensions
        implements BaseColumns, ExtensionsColumns
    {

        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/contact_extensions";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/contact_extensions";
        public static final Uri CONTENT_URI = Uri.parse("content://contacts/extensions");
        public static final String DEFAULT_SORT_ORDER = "person, name ASC";
        public static final String PERSON_ID = "person";


        private Extensions()
        {
        }
    }

    public static interface ExtensionsColumns
    {

        public static final String NAME = "name";
        public static final String VALUE = "value";
    }

    public static final class GroupMembership
        implements BaseColumns, GroupsColumns
    {

        public static final String CONTENT_DIRECTORY = "groupmembership";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/contactsgroupmembership";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/contactsgroupmembership";
        public static final Uri CONTENT_URI = Uri.parse("content://contacts/groupmembership");
        public static final String DEFAULT_SORT_ORDER = "group_id ASC";
        public static final String GROUP_ID = "group_id";
        public static final String GROUP_SYNC_ACCOUNT = "group_sync_account";
        public static final String GROUP_SYNC_ACCOUNT_TYPE = "group_sync_account_type";
        public static final String GROUP_SYNC_ID = "group_sync_id";
        public static final String PERSON_ID = "person";
        public static final Uri RAW_CONTENT_URI = Uri.parse("content://contacts/groupmembershipraw");


        private GroupMembership()
        {
        }
    }

    public static final class Groups
        implements BaseColumns, GroupsColumns
    {

        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/contactsgroup";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/contactsgroup";
        public static final Uri CONTENT_URI = Uri.parse("content://contacts/groups");
        public static final String DEFAULT_SORT_ORDER = "name ASC";
        public static final Uri DELETED_CONTENT_URI = Uri.parse("content://contacts/deleted_groups");
        public static final String GROUP_ANDROID_STARRED = "Starred in Android";
        public static final String GROUP_MY_CONTACTS = "Contacts";


        private Groups()
        {
        }
    }

    public static interface GroupsColumns
    {

        public static final String NAME = "name";
        public static final String NOTES = "notes";
        public static final String SHOULD_SYNC = "should_sync";
        public static final String SYSTEM_ID = "system_id";
    }

    public static final class Intents
    {

        public static final String ATTACH_IMAGE = "com.android.contacts.action.ATTACH_IMAGE";
        public static final String EXTRA_CREATE_DESCRIPTION = "com.android.contacts.action.CREATE_DESCRIPTION";
        public static final String EXTRA_FORCE_CREATE = "com.android.contacts.action.FORCE_CREATE";
        public static final String EXTRA_TARGET_RECT = "target_rect";
        public static final String SEARCH_SUGGESTION_CLICKED = "android.provider.Contacts.SEARCH_SUGGESTION_CLICKED";
        public static final String SEARCH_SUGGESTION_CREATE_CONTACT_CLICKED = "android.provider.Contacts.SEARCH_SUGGESTION_CREATE_CONTACT_CLICKED";
        public static final String SEARCH_SUGGESTION_DIAL_NUMBER_CLICKED = "android.provider.Contacts.SEARCH_SUGGESTION_DIAL_NUMBER_CLICKED";
        public static final String SHOW_OR_CREATE_CONTACT = "com.android.contacts.action.SHOW_OR_CREATE_CONTACT";

        public Intents()
        {
        }
    }

    public static final class Intents.Insert
    {

        public static final String ACTION = "android.intent.action.INSERT";
        public static final String COMPANY = "company";
        public static final String EMAIL = "email";
        public static final String EMAIL_ISPRIMARY = "email_isprimary";
        public static final String EMAIL_TYPE = "email_type";
        public static final String FULL_MODE = "full_mode";
        public static final String IM_HANDLE = "im_handle";
        public static final String IM_ISPRIMARY = "im_isprimary";
        public static final String IM_PROTOCOL = "im_protocol";
        public static final String JOB_TITLE = "job_title";
        public static final String NAME = "name";
        public static final String NOTES = "notes";
        public static final String PHONE = "phone";
        public static final String PHONETIC_NAME = "phonetic_name";
        public static final String PHONE_ISPRIMARY = "phone_isprimary";
        public static final String PHONE_TYPE = "phone_type";
        public static final String POSTAL = "postal";
        public static final String POSTAL_ISPRIMARY = "postal_isprimary";
        public static final String POSTAL_TYPE = "postal_type";
        public static final String SECONDARY_EMAIL = "secondary_email";
        public static final String SECONDARY_EMAIL_TYPE = "secondary_email_type";
        public static final String SECONDARY_PHONE = "secondary_phone";
        public static final String SECONDARY_PHONE_TYPE = "secondary_phone_type";
        public static final String TERTIARY_EMAIL = "tertiary_email";
        public static final String TERTIARY_EMAIL_TYPE = "tertiary_email_type";
        public static final String TERTIARY_PHONE = "tertiary_phone";
        public static final String TERTIARY_PHONE_TYPE = "tertiary_phone_type";

        public Intents.Insert()
        {
        }
    }

    public static final class Intents.UI
    {

        public static final String FILTER_CONTACTS_ACTION = "com.android.contacts.action.FILTER_CONTACTS";
        public static final String FILTER_TEXT_EXTRA_KEY = "com.android.contacts.extra.FILTER_TEXT";
        public static final String GROUP_NAME_EXTRA_KEY = "com.android.contacts.extra.GROUP";
        public static final String LIST_ALL_CONTACTS_ACTION = "com.android.contacts.action.LIST_ALL_CONTACTS";
        public static final String LIST_CONTACTS_WITH_PHONES_ACTION = "com.android.contacts.action.LIST_CONTACTS_WITH_PHONES";
        public static final String LIST_DEFAULT = "com.android.contacts.action.LIST_DEFAULT";
        public static final String LIST_FREQUENT_ACTION = "com.android.contacts.action.LIST_FREQUENT";
        public static final String LIST_GROUP_ACTION = "com.android.contacts.action.LIST_GROUP";
        public static final String LIST_STARRED_ACTION = "com.android.contacts.action.LIST_STARRED";
        public static final String LIST_STREQUENT_ACTION = "com.android.contacts.action.LIST_STREQUENT";
        public static final String TITLE_EXTRA_KEY = "com.android.contacts.extra.TITLE_EXTRA";

        public Intents.UI()
        {
        }
    }

    public static interface OrganizationColumns
    {

        public static final String COMPANY = "company";
        public static final String ISPRIMARY = "isprimary";
        public static final String LABEL = "label";
        public static final String PERSON_ID = "person";
        public static final String TITLE = "title";
        public static final String TYPE = "type";
        public static final int TYPE_CUSTOM = 0;
        public static final int TYPE_OTHER = 2;
        public static final int TYPE_WORK = 1;
    }

    public static final class Organizations
        implements BaseColumns, OrganizationColumns
    {

        public static final CharSequence getDisplayLabel(Context context, int i, CharSequence charsequence)
        {
            String s = "";
            if(i == 0) goto _L2; else goto _L1
_L1:
            context = context.getResources().getTextArray(0x1070002);
            context = context[i - 1];
_L4:
            return context;
_L2:
            context = s;
            if(!TextUtils.isEmpty(charsequence))
                context = charsequence;
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static final String CONTENT_DIRECTORY = "organizations";
        public static final Uri CONTENT_URI = Uri.parse("content://contacts/organizations");
        public static final String DEFAULT_SORT_ORDER = "company, title, isprimary ASC";


        private Organizations()
        {
        }
    }

    public static final class People
        implements BaseColumns, PeopleColumns, PhonesColumns, PresenceColumns
    {

        public static Uri addToGroup(ContentResolver contentresolver, long l, long l1)
        {
            ContentValues contentvalues = new ContentValues();
            contentvalues.put("person", Long.valueOf(l));
            contentvalues.put("group_id", Long.valueOf(l1));
            return contentresolver.insert(GroupMembership.CONTENT_URI, contentvalues);
        }

        public static Uri addToGroup(ContentResolver contentresolver, long l, String s)
        {
            long l1;
            long l2;
            l1 = 0L;
            s = contentresolver.query(Groups.CONTENT_URI, GROUPS_PROJECTION, "name=?", new String[] {
                s
            }, null);
            l2 = l1;
            if(s == null)
                break MISSING_BLOCK_LABEL_61;
            if(s.moveToFirst())
                l1 = s.getLong(0);
            s.close();
            l2 = l1;
            if(l2 == 0L)
                throw new IllegalStateException("Failed to find the My Contacts group");
            else
                return addToGroup(contentresolver, l, l2);
            contentresolver;
            s.close();
            throw contentresolver;
        }

        public static Uri addToMyContactsGroup(ContentResolver contentresolver, long l)
        {
            long l1 = tryGetMyContactsGroupId(contentresolver);
            if(l1 == 0L)
                throw new IllegalStateException("Failed to find the My Contacts group");
            else
                return addToGroup(contentresolver, l, l1);
        }

        public static Uri createPersonInMyContactsGroup(ContentResolver contentresolver, ContentValues contentvalues)
        {
            contentvalues = contentresolver.insert(CONTENT_URI, contentvalues);
            if(contentvalues == null)
            {
                Log.e("Contacts", "Failed to create the contact");
                return null;
            }
            if(addToMyContactsGroup(contentresolver, ContentUris.parseId(contentvalues)) == null)
            {
                contentresolver.delete(contentvalues, null, null);
                return null;
            } else
            {
                return contentvalues;
            }
        }

        public static Bitmap loadContactPhoto(Context context, Uri uri, int i, android.graphics.BitmapFactory.Options options)
        {
            if(uri == null)
                return loadPlaceholderPhoto(i, context, options);
            uri = openContactPhotoInputStream(context.getContentResolver(), uri);
            Object obj;
            if(uri != null)
                uri = BitmapFactory.decodeStream(uri, null, options);
            else
                uri = null;
            obj = uri;
            if(uri == null)
                obj = loadPlaceholderPhoto(i, context, options);
            return ((Bitmap) (obj));
        }

        private static Bitmap loadPlaceholderPhoto(int i, Context context, android.graphics.BitmapFactory.Options options)
        {
            if(i == 0)
                return null;
            else
                return BitmapFactory.decodeResource(context.getResources(), i, options);
        }

        public static void markAsContacted(ContentResolver contentresolver, long l)
        {
        }

        public static InputStream openContactPhotoInputStream(ContentResolver contentresolver, Uri uri)
        {
            contentresolver = contentresolver.query(Uri.withAppendedPath(uri, "photo"), new String[] {
                "data"
            }, null, null, null);
            if(contentresolver == null)
                break MISSING_BLOCK_LABEL_40;
            boolean flag = contentresolver.moveToNext();
            if(!(flag ^ true))
                break MISSING_BLOCK_LABEL_52;
            if(contentresolver != null)
                contentresolver.close();
            return null;
            uri = contentresolver.getBlob(0);
            if(uri == null)
            {
                if(contentresolver != null)
                    contentresolver.close();
                return null;
            }
            uri = new ByteArrayInputStream(uri);
            if(contentresolver != null)
                contentresolver.close();
            return uri;
            uri;
            if(contentresolver != null)
                contentresolver.close();
            throw uri;
        }

        public static Cursor queryGroups(ContentResolver contentresolver, long l)
        {
            return contentresolver.query(GroupMembership.CONTENT_URI, null, "person=?", new String[] {
                String.valueOf(l)
            }, "name ASC");
        }

        public static void setPhotoData(ContentResolver contentresolver, Uri uri, byte abyte0[])
        {
            uri = Uri.withAppendedPath(uri, "photo");
            ContentValues contentvalues = new ContentValues();
            contentvalues.put("data", abyte0);
            contentresolver.update(uri, contentvalues, null, null);
        }

        public static long tryGetMyContactsGroupId(ContentResolver contentresolver)
        {
            contentresolver = contentresolver.query(Groups.CONTENT_URI, GROUPS_PROJECTION, "system_id='Contacts'", null, null);
            if(contentresolver == null)
                break MISSING_BLOCK_LABEL_50;
            long l;
            if(!contentresolver.moveToFirst())
                break MISSING_BLOCK_LABEL_44;
            l = contentresolver.getLong(0);
            contentresolver.close();
            return l;
            contentresolver.close();
            return 0L;
            Exception exception;
            exception;
            contentresolver.close();
            throw exception;
        }

        public static final Uri CONTENT_FILTER_URI = Uri.parse("content://contacts/people/filter");
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/person";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/person";
        public static final Uri CONTENT_URI = Uri.parse("content://contacts/people");
        public static final String DEFAULT_SORT_ORDER = "name ASC";
        public static final Uri DELETED_CONTENT_URI = Uri.parse("content://contacts/deleted_people");
        private static final String GROUPS_PROJECTION[] = {
            "_id"
        };
        public static final String PRIMARY_EMAIL_ID = "primary_email";
        public static final String PRIMARY_ORGANIZATION_ID = "primary_organization";
        public static final String PRIMARY_PHONE_ID = "primary_phone";
        public static final Uri WITH_EMAIL_OR_IM_FILTER_URI = Uri.parse("content://contacts/people/with_email_or_im_filter");


        private People()
        {
        }
    }

    public static final class People.ContactMethods
        implements BaseColumns, ContactMethodsColumns, PeopleColumns
    {

        public static final String CONTENT_DIRECTORY = "contact_methods";
        public static final String DEFAULT_SORT_ORDER = "data ASC";

        private People.ContactMethods()
        {
        }
    }

    public static class People.Extensions
        implements BaseColumns, ExtensionsColumns
    {

        public static final String CONTENT_DIRECTORY = "extensions";
        public static final String DEFAULT_SORT_ORDER = "name ASC";
        public static final String PERSON_ID = "person";

        private People.Extensions()
        {
        }
    }

    public static final class People.Phones
        implements BaseColumns, PhonesColumns, PeopleColumns
    {

        public static final String CONTENT_DIRECTORY = "phones";
        public static final String DEFAULT_SORT_ORDER = "number ASC";

        private People.Phones()
        {
        }
    }

    public static interface PeopleColumns
    {

        public static final String CUSTOM_RINGTONE = "custom_ringtone";
        public static final String DISPLAY_NAME = "display_name";
        public static final String LAST_TIME_CONTACTED = "last_time_contacted";
        public static final String NAME = "name";
        public static final String NOTES = "notes";
        public static final String PHONETIC_NAME = "phonetic_name";
        public static final String PHOTO_VERSION = "photo_version";
        public static final String SEND_TO_VOICEMAIL = "send_to_voicemail";
        public static final String SORT_STRING = "sort_string";
        public static final String STARRED = "starred";
        public static final String TIMES_CONTACTED = "times_contacted";
    }

    public static final class Phones
        implements BaseColumns, PhonesColumns, PeopleColumns
    {

        public static final CharSequence getDisplayLabel(Context context, int i, CharSequence charsequence)
        {
            return getDisplayLabel(context, i, charsequence, null);
        }

        public static final CharSequence getDisplayLabel(Context context, int i, CharSequence charsequence, CharSequence acharsequence[])
        {
            String s = "";
            if(i == 0) goto _L2; else goto _L1
_L1:
            if(acharsequence != null)
                context = acharsequence;
            else
                context = context.getResources().getTextArray(0x1070003);
            context = context[i - 1];
_L4:
            return context;
_L2:
            context = s;
            if(!TextUtils.isEmpty(charsequence))
                context = charsequence;
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static final Uri CONTENT_FILTER_URL = Uri.parse("content://contacts/phones/filter");
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/phone";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/phone";
        public static final Uri CONTENT_URI = Uri.parse("content://contacts/phones");
        public static final String DEFAULT_SORT_ORDER = "name ASC";
        public static final String PERSON_ID = "person";


        private Phones()
        {
        }
    }

    public static interface PhonesColumns
    {

        public static final String ISPRIMARY = "isprimary";
        public static final String LABEL = "label";
        public static final String NUMBER = "number";
        public static final String NUMBER_KEY = "number_key";
        public static final String TYPE = "type";
        public static final int TYPE_CUSTOM = 0;
        public static final int TYPE_FAX_HOME = 5;
        public static final int TYPE_FAX_WORK = 4;
        public static final int TYPE_HOME = 1;
        public static final int TYPE_MOBILE = 2;
        public static final int TYPE_OTHER = 7;
        public static final int TYPE_PAGER = 6;
        public static final int TYPE_WORK = 3;
    }

    public static final class Photos
        implements BaseColumns, PhotosColumns
    {

        public static final String CONTENT_DIRECTORY = "photo";
        public static final Uri CONTENT_URI = Uri.parse("content://contacts/photos");
        public static final String DEFAULT_SORT_ORDER = "person ASC";


        private Photos()
        {
        }
    }

    public static interface PhotosColumns
    {

        public static final String DATA = "data";
        public static final String DOWNLOAD_REQUIRED = "download_required";
        public static final String EXISTS_ON_SERVER = "exists_on_server";
        public static final String LOCAL_VERSION = "local_version";
        public static final String PERSON_ID = "person";
        public static final String SYNC_ERROR = "sync_error";
    }

    public static final class Presence
        implements BaseColumns, PresenceColumns, PeopleColumns
    {

        public static final int getPresenceIconResourceId(int i)
        {
            switch(i)
            {
            default:
                return 0x108006a;

            case 5: // '\005'
                return 0x108006b;

            case 2: // '\002'
            case 3: // '\003'
                return 0x1080067;

            case 4: // '\004'
                return 0x1080068;

            case 1: // '\001'
                return 0x1080069;
            }
        }

        public static final void setPresenceIcon(ImageView imageview, int i)
        {
            imageview.setImageResource(getPresenceIconResourceId(i));
        }

        public static final Uri CONTENT_URI = Uri.parse("content://contacts/presence");
        public static final String PERSON_ID = "person";


        public Presence()
        {
        }
    }

    public static interface PresenceColumns
    {

        public static final int AVAILABLE = 5;
        public static final int AWAY = 2;
        public static final int DO_NOT_DISTURB = 4;
        public static final int IDLE = 3;
        public static final String IM_ACCOUNT = "im_account";
        public static final String IM_HANDLE = "im_handle";
        public static final String IM_PROTOCOL = "im_protocol";
        public static final int INVISIBLE = 1;
        public static final int OFFLINE = 0;
        public static final String PRESENCE_CUSTOM_STATUS = "status";
        public static final String PRESENCE_STATUS = "mode";
        public static final String PRIORITY = "priority";
    }

    public static final class Settings
        implements BaseColumns, SettingsColumns
    {

        public static String getSetting(ContentResolver contentresolver, String s, String s1)
        {
            contentresolver = contentresolver.query(CONTENT_URI, new String[] {
                "value"
            }, "key=?", new String[] {
                s1
            }, null);
            boolean flag = contentresolver.moveToNext();
            if(!flag)
            {
                contentresolver.close();
                return null;
            }
            s = contentresolver.getString(0);
            contentresolver.close();
            return s;
            s;
            contentresolver.close();
            throw s;
        }

        public static void setSetting(ContentResolver contentresolver, String s, String s1, String s2)
        {
            s = new ContentValues();
            s.put("key", s1);
            s.put("value", s2);
            contentresolver.update(CONTENT_URI, s, null, null);
        }

        public static final String CONTENT_DIRECTORY = "settings";
        public static final Uri CONTENT_URI = Uri.parse("content://contacts/settings");
        public static final String DEFAULT_SORT_ORDER = "key ASC";
        public static final String SYNC_EVERYTHING = "syncEverything";


        private Settings()
        {
        }
    }

    public static interface SettingsColumns
    {

        public static final String KEY = "key";
        public static final String VALUE = "value";
        public static final String _SYNC_ACCOUNT = "_sync_account";
        public static final String _SYNC_ACCOUNT_TYPE = "_sync_account_type";
    }


    private Contacts()
    {
    }

    public static final String AUTHORITY = "contacts";
    public static final Uri CONTENT_URI = Uri.parse("content://contacts");
    public static final int KIND_EMAIL = 1;
    public static final int KIND_IM = 3;
    public static final int KIND_ORGANIZATION = 4;
    public static final int KIND_PHONE = 5;
    public static final int KIND_POSTAL = 2;
    private static final String TAG = "Contacts";

}
