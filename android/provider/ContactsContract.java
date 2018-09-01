// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.provider;

import android.accounts.Account;
import android.app.Activity;
import android.content.ContentProviderClient;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.CursorEntityIterator;
import android.content.EntityIterator;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.CompatibilityInfo;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.graphics.Rect;
import android.net.Uri;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Pair;
import android.util.SeempLog;
import android.view.View;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

// Referenced classes of package android.provider:
//            BaseColumns, ContactsInternal

public final class ContactsContract
{
    public static final class AggregationExceptions
        implements BaseColumns
    {

        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/aggregation_exception";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/aggregation_exception";
        public static final Uri CONTENT_URI;
        public static final String RAW_CONTACT_ID1 = "raw_contact_id1";
        public static final String RAW_CONTACT_ID2 = "raw_contact_id2";
        public static final String TYPE = "type";
        public static final int TYPE_AUTOMATIC = 0;
        public static final int TYPE_KEEP_SEPARATE = 2;
        public static final int TYPE_KEEP_TOGETHER = 1;

        static 
        {
            CONTENT_URI = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI, "aggregation_exceptions");
        }

        private AggregationExceptions()
        {
        }
    }

    public static final class Authorization
    {

        public static final String AUTHORIZATION_METHOD = "authorize";
        public static final String KEY_AUTHORIZED_URI = "authorized_uri";
        public static final String KEY_URI_TO_AUTHORIZE = "uri_to_authorize";

        public Authorization()
        {
        }
    }

    protected static interface BaseSyncColumns
    {

        public static final String SYNC1 = "sync1";
        public static final String SYNC2 = "sync2";
        public static final String SYNC3 = "sync3";
        public static final String SYNC4 = "sync4";
    }

    public static final class CommonDataKinds
    {

        public static final String PACKAGE_COMMON = "common";

        private CommonDataKinds()
        {
        }
    }

    public static interface CommonDataKinds.BaseTypes
    {

        public static final int TYPE_CUSTOM = 0;
    }

    public static final class CommonDataKinds.Callable
        implements DataColumnsWithJoins, CommonDataKinds.CommonColumns, ContactCounts
    {

        public static final Uri CONTENT_FILTER_URI;
        public static final Uri CONTENT_URI;
        public static final Uri ENTERPRISE_CONTENT_FILTER_URI;

        static 
        {
            CONTENT_URI = Uri.withAppendedPath(Data.CONTENT_URI, "callables");
            CONTENT_FILTER_URI = Uri.withAppendedPath(CONTENT_URI, "filter");
            ENTERPRISE_CONTENT_FILTER_URI = Uri.withAppendedPath(CONTENT_URI, "filter_enterprise");
        }

        public CommonDataKinds.Callable()
        {
        }
    }

    protected static interface CommonDataKinds.CommonColumns
        extends CommonDataKinds.BaseTypes
    {

        public static final String DATA = "data1";
        public static final String LABEL = "data3";
        public static final String TYPE = "data2";
    }

    public static final class CommonDataKinds.Contactables
        implements DataColumnsWithJoins, CommonDataKinds.CommonColumns, ContactCounts
    {

        public static final Uri CONTENT_FILTER_URI;
        public static final Uri CONTENT_URI;
        public static final String VISIBLE_CONTACTS_ONLY = "visible_contacts_only";

        static 
        {
            CONTENT_URI = Uri.withAppendedPath(Data.CONTENT_URI, "contactables");
            CONTENT_FILTER_URI = Uri.withAppendedPath(CONTENT_URI, "filter");
        }

        public CommonDataKinds.Contactables()
        {
        }
    }

    public static final class CommonDataKinds.Email
        implements DataColumnsWithJoins, CommonDataKinds.CommonColumns, ContactCounts
    {

        public static final CharSequence getTypeLabel(Resources resources, int i, CharSequence charsequence)
        {
            if(i == 0 && TextUtils.isEmpty(charsequence) ^ true)
                return charsequence;
            else
                return resources.getText(getTypeLabelResource(i));
        }

        public static final int getTypeLabelResource(int i)
        {
            switch(i)
            {
            default:
                return 0x10401d0;

            case 1: // '\001'
                return 0x10401d1;

            case 2: // '\002'
                return 0x10401d4;

            case 3: // '\003'
                return 0x10401d3;

            case 4: // '\004'
                return 0x10401d2;
            }
        }

        public static final String ADDRESS = "data1";
        public static final Uri CONTENT_FILTER_URI;
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/email_v2";
        public static final Uri CONTENT_LOOKUP_URI;
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/email_v2";
        public static final Uri CONTENT_URI;
        public static final String DISPLAY_NAME = "data4";
        public static final Uri ENTERPRISE_CONTENT_FILTER_URI;
        public static final Uri ENTERPRISE_CONTENT_LOOKUP_URI;
        public static final int TYPE_HOME = 1;
        public static final int TYPE_MOBILE = 4;
        public static final int TYPE_OTHER = 3;
        public static final int TYPE_WORK = 2;

        static 
        {
            CONTENT_URI = Uri.withAppendedPath(Data.CONTENT_URI, "emails");
            CONTENT_LOOKUP_URI = Uri.withAppendedPath(CONTENT_URI, "lookup");
            ENTERPRISE_CONTENT_LOOKUP_URI = Uri.withAppendedPath(CONTENT_URI, "lookup_enterprise");
            CONTENT_FILTER_URI = Uri.withAppendedPath(CONTENT_URI, "filter");
            ENTERPRISE_CONTENT_FILTER_URI = Uri.withAppendedPath(CONTENT_URI, "filter_enterprise");
        }

        private CommonDataKinds.Email()
        {
        }
    }

    public static final class CommonDataKinds.Event
        implements DataColumnsWithJoins, CommonDataKinds.CommonColumns, ContactCounts
    {

        public static final CharSequence getTypeLabel(Resources resources, int i, CharSequence charsequence)
        {
            if(i == 0 && TextUtils.isEmpty(charsequence) ^ true)
                return charsequence;
            else
                return resources.getText(getTypeResource(Integer.valueOf(i)));
        }

        public static int getTypeResource(Integer integer)
        {
            if(integer == null)
                return 0x10401e5;
            switch(integer.intValue())
            {
            default:
                return 0x10401e4;

            case 1: // '\001'
                return 0x10401e2;

            case 3: // '\003'
                return 0x10401e3;

            case 2: // '\002'
                return 0x10401e5;
            }
        }

        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/contact_event";
        public static final String START_DATE = "data1";
        public static final int TYPE_ANNIVERSARY = 1;
        public static final int TYPE_BIRTHDAY = 3;
        public static final int TYPE_OTHER = 2;

        private CommonDataKinds.Event()
        {
        }
    }

    public static final class CommonDataKinds.GroupMembership
        implements DataColumnsWithJoins, ContactCounts
    {

        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/group_membership";
        public static final String GROUP_ROW_ID = "data1";
        public static final String GROUP_SOURCE_ID = "group_sourceid";

        private CommonDataKinds.GroupMembership()
        {
        }
    }

    public static final class CommonDataKinds.Identity
        implements DataColumnsWithJoins, ContactCounts
    {

        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/identity";
        public static final String IDENTITY = "data1";
        public static final String NAMESPACE = "data2";

        private CommonDataKinds.Identity()
        {
        }
    }

    public static final class CommonDataKinds.Im
        implements DataColumnsWithJoins, CommonDataKinds.CommonColumns, ContactCounts
    {

        public static final CharSequence getProtocolLabel(Resources resources, int i, CharSequence charsequence)
        {
            if(i == -1 && TextUtils.isEmpty(charsequence) ^ true)
                return charsequence;
            else
                return resources.getText(getProtocolLabelResource(i));
        }

        public static final int getProtocolLabelResource(int i)
        {
            switch(i)
            {
            default:
                return 0x104027b;

            case 0: // '\0'
                return 0x104027a;

            case 1: // '\001'
                return 0x104027f;

            case 2: // '\002'
                return 0x1040283;

            case 3: // '\003'
                return 0x1040282;

            case 4: // '\004'
                return 0x1040281;

            case 5: // '\005'
                return 0x104027c;

            case 6: // '\006'
                return 0x104027d;

            case 7: // '\007'
                return 0x104027e;

            case 8: // '\b'
                return 0x1040280;
            }
        }

        public static final CharSequence getTypeLabel(Resources resources, int i, CharSequence charsequence)
        {
            if(i == 0 && TextUtils.isEmpty(charsequence) ^ true)
                return charsequence;
            else
                return resources.getText(getTypeLabelResource(i));
        }

        public static final int getTypeLabelResource(int i)
        {
            switch(i)
            {
            default:
                return 0x1040284;

            case 1: // '\001'
                return 0x1040285;

            case 2: // '\002'
                return 0x1040287;

            case 3: // '\003'
                return 0x1040286;
            }
        }

        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/im";
        public static final String CUSTOM_PROTOCOL = "data6";
        public static final String PROTOCOL = "data5";
        public static final int PROTOCOL_AIM = 0;
        public static final int PROTOCOL_CUSTOM = -1;
        public static final int PROTOCOL_GOOGLE_TALK = 5;
        public static final int PROTOCOL_ICQ = 6;
        public static final int PROTOCOL_JABBER = 7;
        public static final int PROTOCOL_MSN = 1;
        public static final int PROTOCOL_NETMEETING = 8;
        public static final int PROTOCOL_QQ = 4;
        public static final int PROTOCOL_SKYPE = 3;
        public static final int PROTOCOL_YAHOO = 2;
        public static final int TYPE_HOME = 1;
        public static final int TYPE_OTHER = 3;
        public static final int TYPE_WORK = 2;

        private CommonDataKinds.Im()
        {
        }
    }

    public static final class CommonDataKinds.Nickname
        implements DataColumnsWithJoins, CommonDataKinds.CommonColumns, ContactCounts
    {

        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/nickname";
        public static final String NAME = "data1";
        public static final int TYPE_DEFAULT = 1;
        public static final int TYPE_INITIALS = 5;
        public static final int TYPE_MAIDEN_NAME = 3;
        public static final int TYPE_MAINDEN_NAME = 3;
        public static final int TYPE_OTHER_NAME = 2;
        public static final int TYPE_SHORT_NAME = 4;

        private CommonDataKinds.Nickname()
        {
        }
    }

    public static final class CommonDataKinds.Note
        implements DataColumnsWithJoins, ContactCounts
    {

        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/note";
        public static final String NOTE = "data1";

        private CommonDataKinds.Note()
        {
        }
    }

    public static final class CommonDataKinds.Organization
        implements DataColumnsWithJoins, CommonDataKinds.CommonColumns, ContactCounts
    {

        public static final CharSequence getTypeLabel(Resources resources, int i, CharSequence charsequence)
        {
            if(i == 0 && TextUtils.isEmpty(charsequence) ^ true)
                return charsequence;
            else
                return resources.getText(getTypeLabelResource(i));
        }

        public static final int getTypeLabelResource(int i)
        {
            switch(i)
            {
            default:
                return 0x1040400;

            case 1: // '\001'
                return 0x1040402;

            case 2: // '\002'
                return 0x1040401;
            }
        }

        public static final String COMPANY = "data1";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/organization";
        public static final String DEPARTMENT = "data5";
        public static final String JOB_DESCRIPTION = "data6";
        public static final String OFFICE_LOCATION = "data9";
        public static final String PHONETIC_NAME = "data8";
        public static final String PHONETIC_NAME_STYLE = "data10";
        public static final String SYMBOL = "data7";
        public static final String TITLE = "data4";
        public static final int TYPE_OTHER = 2;
        public static final int TYPE_WORK = 1;

        private CommonDataKinds.Organization()
        {
        }
    }

    public static final class CommonDataKinds.Phone
        implements DataColumnsWithJoins, CommonDataKinds.CommonColumns, ContactCounts
    {

        public static final CharSequence getDisplayLabel(Context context, int i, CharSequence charsequence)
        {
            return getTypeLabel(context.getResources(), i, charsequence);
        }

        public static final CharSequence getDisplayLabel(Context context, int i, CharSequence charsequence, CharSequence acharsequence[])
        {
            return getTypeLabel(context.getResources(), i, charsequence);
        }

        public static final CharSequence getTypeLabel(Resources resources, int i, CharSequence charsequence)
        {
            if((i == 0 || i == 19) && TextUtils.isEmpty(charsequence) ^ true)
                return charsequence;
            else
                return resources.getText(getTypeLabelResource(i));
        }

        public static final int getTypeLabelResource(int i)
        {
            switch(i)
            {
            default:
                return 0x1040516;

            case 1: // '\001'
                return 0x1040519;

            case 2: // '\002'
                return 0x104051d;

            case 3: // '\003'
                return 0x1040524;

            case 4: // '\004'
                return 0x1040518;

            case 5: // '\005'
                return 0x1040517;

            case 6: // '\006'
                return 0x1040520;

            case 7: // '\007'
                return 0x104051e;

            case 8: // '\b'
                return 0x1040513;

            case 9: // '\t'
                return 0x1040514;

            case 10: // '\n'
                return 0x1040515;

            case 11: // '\013'
                return 0x104051a;

            case 12: // '\f'
                return 0x104051b;

            case 13: // '\r'
                return 0x104051f;

            case 14: // '\016'
                return 0x1040521;

            case 15: // '\017'
                return 0x1040522;

            case 16: // '\020'
                return 0x1040523;

            case 17: // '\021'
                return 0x1040525;

            case 18: // '\022'
                return 0x1040526;

            case 19: // '\023'
                return 0x1040512;

            case 20: // '\024'
                return 0x104051c;
            }
        }

        public static final Uri CONTENT_FILTER_URI;
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/phone_v2";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/phone_v2";
        public static final Uri CONTENT_URI;
        public static final Uri ENTERPRISE_CONTENT_FILTER_URI;
        public static final Uri ENTERPRISE_CONTENT_URI;
        public static final String NORMALIZED_NUMBER = "data4";
        public static final String NUMBER = "data1";
        public static final String SEARCH_DISPLAY_NAME_KEY = "search_display_name";
        public static final String SEARCH_PHONE_NUMBER_KEY = "search_phone_number";
        public static final int TYPE_ASSISTANT = 19;
        public static final int TYPE_CALLBACK = 8;
        public static final int TYPE_CAR = 9;
        public static final int TYPE_COMPANY_MAIN = 10;
        public static final int TYPE_FAX_HOME = 5;
        public static final int TYPE_FAX_WORK = 4;
        public static final int TYPE_HOME = 1;
        public static final int TYPE_ISDN = 11;
        public static final int TYPE_MAIN = 12;
        public static final int TYPE_MMS = 20;
        public static final int TYPE_MOBILE = 2;
        public static final int TYPE_OTHER = 7;
        public static final int TYPE_OTHER_FAX = 13;
        public static final int TYPE_PAGER = 6;
        public static final int TYPE_RADIO = 14;
        public static final int TYPE_TELEX = 15;
        public static final int TYPE_TTY_TDD = 16;
        public static final int TYPE_WORK = 3;
        public static final int TYPE_WORK_MOBILE = 17;
        public static final int TYPE_WORK_PAGER = 18;

        static 
        {
            CONTENT_URI = Uri.withAppendedPath(Data.CONTENT_URI, "phones");
            ENTERPRISE_CONTENT_URI = Uri.withAppendedPath(Data.ENTERPRISE_CONTENT_URI, "phones");
            CONTENT_FILTER_URI = Uri.withAppendedPath(CONTENT_URI, "filter");
            ENTERPRISE_CONTENT_FILTER_URI = Uri.withAppendedPath(CONTENT_URI, "filter_enterprise");
        }

        private CommonDataKinds.Phone()
        {
        }
    }

    public static final class CommonDataKinds.Photo
        implements DataColumnsWithJoins, ContactCounts
    {

        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/photo";
        public static final String PHOTO = "data15";
        public static final String PHOTO_FILE_ID = "data14";

        private CommonDataKinds.Photo()
        {
        }
    }

    public static final class CommonDataKinds.Relation
        implements DataColumnsWithJoins, CommonDataKinds.CommonColumns, ContactCounts
    {

        public static final CharSequence getTypeLabel(Resources resources, int i, CharSequence charsequence)
        {
            if(i == 0 && TextUtils.isEmpty(charsequence) ^ true)
                return charsequence;
            else
                return resources.getText(getTypeLabelResource(i));
        }

        public static final int getTypeLabelResource(int i)
        {
            switch(i)
            {
            default:
                return 0x1040400;

            case 1: // '\001'
                return 0x1040561;

            case 2: // '\002'
                return 0x1040562;

            case 3: // '\003'
                return 0x1040563;

            case 4: // '\004'
                return 0x1040565;

            case 5: // '\005'
                return 0x1040566;

            case 6: // '\006'
                return 0x1040567;

            case 7: // '\007'
                return 0x1040568;

            case 8: // '\b'
                return 0x1040569;

            case 9: // '\t'
                return 0x104056a;

            case 10: // '\n'
                return 0x104056b;

            case 11: // '\013'
                return 0x104056c;

            case 12: // '\f'
                return 0x104056d;

            case 13: // '\r'
                return 0x104056e;

            case 14: // '\016'
                return 0x104056f;
            }
        }

        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/relation";
        public static final String NAME = "data1";
        public static final int TYPE_ASSISTANT = 1;
        public static final int TYPE_BROTHER = 2;
        public static final int TYPE_CHILD = 3;
        public static final int TYPE_DOMESTIC_PARTNER = 4;
        public static final int TYPE_FATHER = 5;
        public static final int TYPE_FRIEND = 6;
        public static final int TYPE_MANAGER = 7;
        public static final int TYPE_MOTHER = 8;
        public static final int TYPE_PARENT = 9;
        public static final int TYPE_PARTNER = 10;
        public static final int TYPE_REFERRED_BY = 11;
        public static final int TYPE_RELATIVE = 12;
        public static final int TYPE_SISTER = 13;
        public static final int TYPE_SPOUSE = 14;

        private CommonDataKinds.Relation()
        {
        }
    }

    public static final class CommonDataKinds.SipAddress
        implements DataColumnsWithJoins, CommonDataKinds.CommonColumns, ContactCounts
    {

        public static final CharSequence getTypeLabel(Resources resources, int i, CharSequence charsequence)
        {
            if(i == 0 && TextUtils.isEmpty(charsequence) ^ true)
                return charsequence;
            else
                return resources.getText(getTypeLabelResource(i));
        }

        public static final int getTypeLabelResource(int i)
        {
            switch(i)
            {
            default:
                return 0x10405dd;

            case 1: // '\001'
                return 0x10405de;

            case 2: // '\002'
                return 0x10405e0;

            case 3: // '\003'
                return 0x10405df;
            }
        }

        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/sip_address";
        public static final String SIP_ADDRESS = "data1";
        public static final int TYPE_HOME = 1;
        public static final int TYPE_OTHER = 3;
        public static final int TYPE_WORK = 2;

        private CommonDataKinds.SipAddress()
        {
        }
    }

    public static final class CommonDataKinds.StructuredName
        implements DataColumnsWithJoins, ContactCounts
    {

        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/name";
        public static final String DISPLAY_NAME = "data1";
        public static final String FAMILY_NAME = "data3";
        public static final String FULL_NAME_STYLE = "data10";
        public static final String GIVEN_NAME = "data2";
        public static final String MIDDLE_NAME = "data5";
        public static final String PHONETIC_FAMILY_NAME = "data9";
        public static final String PHONETIC_GIVEN_NAME = "data7";
        public static final String PHONETIC_MIDDLE_NAME = "data8";
        public static final String PHONETIC_NAME_STYLE = "data11";
        public static final String PREFIX = "data4";
        public static final String SUFFIX = "data6";

        private CommonDataKinds.StructuredName()
        {
        }
    }

    public static final class CommonDataKinds.StructuredPostal
        implements DataColumnsWithJoins, CommonDataKinds.CommonColumns, ContactCounts
    {

        public static final CharSequence getTypeLabel(Resources resources, int i, CharSequence charsequence)
        {
            if(i == 0 && TextUtils.isEmpty(charsequence) ^ true)
                return charsequence;
            else
                return resources.getText(getTypeLabelResource(i));
        }

        public static final int getTypeLabelResource(int i)
        {
            switch(i)
            {
            default:
                return 0x1040540;

            case 1: // '\001'
                return 0x1040541;

            case 2: // '\002'
                return 0x1040543;

            case 3: // '\003'
                return 0x1040542;
            }
        }

        public static final String CITY = "data7";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/postal-address_v2";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/postal-address_v2";
        public static final Uri CONTENT_URI;
        public static final String COUNTRY = "data10";
        public static final String FORMATTED_ADDRESS = "data1";
        public static final String NEIGHBORHOOD = "data6";
        public static final String POBOX = "data5";
        public static final String POSTCODE = "data9";
        public static final String REGION = "data8";
        public static final String STREET = "data4";
        public static final int TYPE_HOME = 1;
        public static final int TYPE_OTHER = 3;
        public static final int TYPE_WORK = 2;

        static 
        {
            CONTENT_URI = Uri.withAppendedPath(Data.CONTENT_URI, "postals");
        }

        private CommonDataKinds.StructuredPostal()
        {
        }
    }

    public static final class CommonDataKinds.Website
        implements DataColumnsWithJoins, CommonDataKinds.CommonColumns, ContactCounts
    {

        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/website";
        public static final int TYPE_BLOG = 2;
        public static final int TYPE_FTP = 6;
        public static final int TYPE_HOME = 4;
        public static final int TYPE_HOMEPAGE = 1;
        public static final int TYPE_OTHER = 7;
        public static final int TYPE_PROFILE = 3;
        public static final int TYPE_WORK = 5;
        public static final String URL = "data1";

        private CommonDataKinds.Website()
        {
        }
    }

    static interface ContactCounts
    {

        public static final String EXTRA_ADDRESS_BOOK_INDEX = "android.provider.extra.ADDRESS_BOOK_INDEX";
        public static final String EXTRA_ADDRESS_BOOK_INDEX_COUNTS = "android.provider.extra.ADDRESS_BOOK_INDEX_COUNTS";
        public static final String EXTRA_ADDRESS_BOOK_INDEX_TITLES = "android.provider.extra.ADDRESS_BOOK_INDEX_TITLES";
    }

    protected static interface ContactNameColumns
    {

        public static final String DISPLAY_NAME_ALTERNATIVE = "display_name_alt";
        public static final String DISPLAY_NAME_PRIMARY = "display_name";
        public static final String DISPLAY_NAME_SOURCE = "display_name_source";
        public static final String PHONETIC_NAME = "phonetic_name";
        public static final String PHONETIC_NAME_STYLE = "phonetic_name_style";
        public static final String SORT_KEY_ALTERNATIVE = "sort_key_alt";
        public static final String SORT_KEY_PRIMARY = "sort_key";
    }

    protected static interface ContactOptionsColumns
    {

        public static final String CUSTOM_RINGTONE = "custom_ringtone";
        public static final String LAST_TIME_CONTACTED = "last_time_contacted";
        public static final String LR_LAST_TIME_CONTACTED = "last_time_contacted";
        public static final String LR_TIMES_CONTACTED = "times_contacted";
        public static final String PINNED = "pinned";
        public static final String RAW_LAST_TIME_CONTACTED = "x_last_time_contacted";
        public static final String RAW_TIMES_CONTACTED = "x_times_contacted";
        public static final String SEND_TO_VOICEMAIL = "send_to_voicemail";
        public static final String STARRED = "starred";
        public static final String TIMES_CONTACTED = "times_contacted";
    }

    protected static interface ContactStatusColumns
    {

        public static final String CONTACT_CHAT_CAPABILITY = "contact_chat_capability";
        public static final String CONTACT_PRESENCE = "contact_presence";
        public static final String CONTACT_STATUS = "contact_status";
        public static final String CONTACT_STATUS_ICON = "contact_status_icon";
        public static final String CONTACT_STATUS_LABEL = "contact_status_label";
        public static final String CONTACT_STATUS_RES_PACKAGE = "contact_status_res_package";
        public static final String CONTACT_STATUS_TIMESTAMP = "contact_status_ts";
    }

    public static class Contacts
        implements BaseColumns, ContactsColumns, ContactOptionsColumns, ContactNameColumns, ContactStatusColumns, ContactCounts
    {

        public static Uri getLookupUri(long l, String s)
        {
            SeempLog.record(86);
            if(TextUtils.isEmpty(s))
                return null;
            else
                return ContentUris.withAppendedId(Uri.withAppendedPath(CONTENT_LOOKUP_URI, s), l);
        }

        public static Uri getLookupUri(ContentResolver contentresolver, Uri uri)
        {
            SeempLog.record(86);
            contentresolver = contentresolver.query(uri, new String[] {
                "lookup", "_id"
            }, null, null, null);
            if(contentresolver == null)
                return null;
            if(!contentresolver.moveToFirst())
                break MISSING_BLOCK_LABEL_72;
            uri = contentresolver.getString(0);
            uri = getLookupUri(contentresolver.getLong(1), ((String) (uri)));
            contentresolver.close();
            return uri;
            contentresolver.close();
            return null;
            uri;
            contentresolver.close();
            throw uri;
        }

        public static boolean isEnterpriseContactId(long l)
        {
            boolean flag = false;
            boolean flag1 = flag;
            if(l >= ENTERPRISE_CONTACT_ID_BASE)
            {
                flag1 = flag;
                if(l < 0x7fffffff80000000L)
                    flag1 = true;
            }
            return flag1;
        }

        public static Uri lookupContact(ContentResolver contentresolver, Uri uri)
        {
            SeempLog.record(87);
            if(uri == null)
                return null;
            contentresolver = contentresolver.query(uri, new String[] {
                "_id"
            }, null, null, null);
            if(contentresolver == null)
                return null;
            if(!contentresolver.moveToFirst())
                break MISSING_BLOCK_LABEL_69;
            long l = contentresolver.getLong(0);
            uri = ContentUris.withAppendedId(CONTENT_URI, l);
            contentresolver.close();
            return uri;
            contentresolver.close();
            return null;
            uri;
            contentresolver.close();
            throw uri;
        }

        public static void markAsContacted(ContentResolver contentresolver, long l)
        {
            Uri uri = ContentUris.withAppendedId(CONTENT_URI, l);
            ContentValues contentvalues = new ContentValues();
            contentvalues.put("last_time_contacted", Long.valueOf(System.currentTimeMillis()));
            contentresolver.update(uri, contentvalues, null, null);
        }

        public static InputStream openContactPhotoInputStream(ContentResolver contentresolver, Uri uri)
        {
            SeempLog.record(88);
            return openContactPhotoInputStream(contentresolver, uri, false);
        }

        public static InputStream openContactPhotoInputStream(ContentResolver contentresolver, Uri uri, boolean flag)
        {
            Object obj;
            SeempLog.record(88);
            if(!flag)
                break MISSING_BLOCK_LABEL_37;
            obj = Uri.withAppendedPath(uri, "display_photo");
            obj = contentresolver.openAssetFileDescriptor(((Uri) (obj)), "r");
            if(obj == null)
                break MISSING_BLOCK_LABEL_37;
            obj = ((AssetFileDescriptor) (obj)).createInputStream();
            return ((InputStream) (obj));
            IOException ioexception;
            ioexception;
            uri = Uri.withAppendedPath(uri, "photo");
            if(uri == null)
                return null;
            contentresolver = contentresolver.query(uri, new String[] {
                "data15"
            }, null, null, null);
            if(contentresolver == null)
                break MISSING_BLOCK_LABEL_85;
            flag = contentresolver.moveToNext();
            if(!(flag ^ true))
                break MISSING_BLOCK_LABEL_97;
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

        public static final Uri CONTENT_FILTER_URI;
        public static final Uri CONTENT_FREQUENT_URI;
        public static final Uri CONTENT_GROUP_URI;
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/contact";
        public static final Uri CONTENT_LOOKUP_URI;
        public static final Uri CONTENT_MULTI_VCARD_URI;
        public static final Uri CONTENT_STREQUENT_FILTER_URI;
        public static final Uri CONTENT_STREQUENT_URI;
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/contact";
        public static final Uri CONTENT_URI;
        public static final String CONTENT_VCARD_TYPE = "text/x-vcard";
        public static final Uri CONTENT_VCARD_URI;
        public static final Uri CORP_CONTENT_URI;
        public static long ENTERPRISE_CONTACT_ID_BASE = 0L;
        public static String ENTERPRISE_CONTACT_LOOKUP_PREFIX = "c-";
        public static final Uri ENTERPRISE_CONTENT_FILTER_URI;
        public static final String QUERY_PARAMETER_VCARD_NO_PHOTO = "no_photo";

        static 
        {
            CONTENT_URI = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI, "contacts");
            CORP_CONTENT_URI = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI, "contacts_corp");
            CONTENT_LOOKUP_URI = Uri.withAppendedPath(CONTENT_URI, "lookup");
            CONTENT_VCARD_URI = Uri.withAppendedPath(CONTENT_URI, "as_vcard");
            CONTENT_MULTI_VCARD_URI = Uri.withAppendedPath(CONTENT_URI, "as_multi_vcard");
            CONTENT_FILTER_URI = Uri.withAppendedPath(CONTENT_URI, "filter");
            ENTERPRISE_CONTENT_FILTER_URI = Uri.withAppendedPath(CONTENT_URI, "filter_enterprise");
            CONTENT_STREQUENT_URI = Uri.withAppendedPath(CONTENT_URI, "strequent");
            CONTENT_FREQUENT_URI = Uri.withAppendedPath(CONTENT_URI, "frequent");
            CONTENT_STREQUENT_FILTER_URI = Uri.withAppendedPath(CONTENT_STREQUENT_URI, "filter");
            CONTENT_GROUP_URI = Uri.withAppendedPath(CONTENT_URI, "group");
            ENTERPRISE_CONTACT_ID_BASE = 0x3b9aca00L;
        }

        private Contacts()
        {
        }
    }

    public static final class Contacts.AggregationSuggestions
        implements BaseColumns, ContactsColumns, ContactOptionsColumns, ContactStatusColumns
    {

        public static final Builder builder()
        {
            return new Builder();
        }

        public static final String CONTENT_DIRECTORY = "suggestions";
        public static final String PARAMETER_MATCH_NAME = "name";

        private Contacts.AggregationSuggestions()
        {
        }
    }

    public static final class Contacts.AggregationSuggestions.Builder
    {

        public Contacts.AggregationSuggestions.Builder addNameParameter(String s)
        {
            mValues.add(s);
            return this;
        }

        public Uri build()
        {
            android.net.Uri.Builder builder = Contacts.CONTENT_URI.buildUpon();
            builder.appendEncodedPath(String.valueOf(mContactId));
            builder.appendPath("suggestions");
            if(mLimit != 0)
                builder.appendQueryParameter("limit", String.valueOf(mLimit));
            int i = mValues.size();
            for(int j = 0; j < i; j++)
                builder.appendQueryParameter("query", (new StringBuilder()).append("name:").append((String)mValues.get(j)).toString());

            return builder.build();
        }

        public Contacts.AggregationSuggestions.Builder setContactId(long l)
        {
            mContactId = l;
            return this;
        }

        public Contacts.AggregationSuggestions.Builder setLimit(int i)
        {
            mLimit = i;
            return this;
        }

        private long mContactId;
        private int mLimit;
        private final ArrayList mValues = new ArrayList();

        public Contacts.AggregationSuggestions.Builder()
        {
        }
    }

    public static final class Contacts.Data
        implements BaseColumns, DataColumns
    {

        public static final String CONTENT_DIRECTORY = "data";

        private Contacts.Data()
        {
        }
    }

    public static final class Contacts.Entity
        implements BaseColumns, ContactsColumns, ContactNameColumns, RawContactsColumns, BaseSyncColumns, SyncColumns, DataColumns, StatusColumns, ContactOptionsColumns, ContactStatusColumns, DataUsageStatColumns
    {

        public static final String CONTENT_DIRECTORY = "entities";
        public static final String DATA_ID = "data_id";
        public static final String RAW_CONTACT_ID = "raw_contact_id";

        private Contacts.Entity()
        {
        }
    }

    public static final class Contacts.Photo
        implements BaseColumns, DataColumnsWithJoins
    {

        public static final String CONTENT_DIRECTORY = "photo";
        public static final String DISPLAY_PHOTO = "display_photo";
        public static final String PHOTO = "data15";
        public static final String PHOTO_FILE_ID = "data14";

        private Contacts.Photo()
        {
        }
    }

    public static final class Contacts.StreamItems
        implements StreamItemsColumns
    {

        public static final String CONTENT_DIRECTORY = "stream_items";

        private Contacts.StreamItems()
        {
        }
    }

    protected static interface ContactsColumns
    {

        public static final String CONTACT_LAST_UPDATED_TIMESTAMP = "contact_last_updated_timestamp";
        public static final String DISPLAY_NAME = "display_name";
        public static final String HAS_PHONE_NUMBER = "has_phone_number";
        public static final String IN_DEFAULT_DIRECTORY = "in_default_directory";
        public static final String IN_VISIBLE_GROUP = "in_visible_group";
        public static final String IS_USER_PROFILE = "is_user_profile";
        public static final String LOOKUP_KEY = "lookup";
        public static final String NAME_RAW_CONTACT_ID = "name_raw_contact_id";
        public static final String PHOTO_FILE_ID = "photo_file_id";
        public static final String PHOTO_ID = "photo_id";
        public static final String PHOTO_THUMBNAIL_URI = "photo_thumb_uri";
        public static final String PHOTO_URI = "photo_uri";
    }

    public static final class Data
        implements DataColumnsWithJoins, ContactCounts
    {

        public static Uri getContactLookupUri(ContentResolver contentresolver, Uri uri)
        {
            SeempLog.record(89);
            contentresolver = contentresolver.query(uri, new String[] {
                "contact_id", "lookup"
            }, null, null, null);
            if(contentresolver == null)
                break MISSING_BLOCK_LABEL_72;
            if(!contentresolver.moveToFirst())
                break MISSING_BLOCK_LABEL_72;
            uri = Contacts.getLookupUri(contentresolver.getLong(0), contentresolver.getString(1));
            if(contentresolver != null)
                contentresolver.close();
            return uri;
            if(contentresolver != null)
                contentresolver.close();
            return null;
            uri;
            if(contentresolver != null)
                contentresolver.close();
            throw uri;
        }

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/data";
        public static final Uri CONTENT_URI;
        static final Uri ENTERPRISE_CONTENT_URI;
        public static final String VISIBLE_CONTACTS_ONLY = "visible_contacts_only";

        static 
        {
            CONTENT_URI = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI, "data");
            ENTERPRISE_CONTENT_URI = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI, "data_enterprise");
        }

        private Data()
        {
        }
    }

    protected static interface DataColumns
    {

        public static final String CARRIER_PRESENCE = "carrier_presence";
        public static final int CARRIER_PRESENCE_VT_CAPABLE = 1;
        public static final String DATA1 = "data1";
        public static final String DATA10 = "data10";
        public static final String DATA11 = "data11";
        public static final String DATA12 = "data12";
        public static final String DATA13 = "data13";
        public static final String DATA14 = "data14";
        public static final String DATA15 = "data15";
        public static final String DATA2 = "data2";
        public static final String DATA3 = "data3";
        public static final String DATA4 = "data4";
        public static final String DATA5 = "data5";
        public static final String DATA6 = "data6";
        public static final String DATA7 = "data7";
        public static final String DATA8 = "data8";
        public static final String DATA9 = "data9";
        public static final String DATA_VERSION = "data_version";
        public static final String HASH_ID = "hash_id";
        public static final String IS_PRIMARY = "is_primary";
        public static final String IS_READ_ONLY = "is_read_only";
        public static final String IS_SUPER_PRIMARY = "is_super_primary";
        public static final String MIMETYPE = "mimetype";
        public static final String RAW_CONTACT_ID = "raw_contact_id";
        public static final String RES_PACKAGE = "res_package";
        public static final String SYNC1 = "data_sync1";
        public static final String SYNC2 = "data_sync2";
        public static final String SYNC3 = "data_sync3";
        public static final String SYNC4 = "data_sync4";
    }

    protected static interface DataColumnsWithJoins
        extends BaseColumns, DataColumns, StatusColumns, RawContactsColumns, ContactsColumns, ContactNameColumns, ContactOptionsColumns, ContactStatusColumns, DataUsageStatColumns
    {
    }

    public static final class DataUsageFeedback
    {

        public static final Uri DELETE_USAGE_URI;
        public static final Uri FEEDBACK_URI;
        public static final String USAGE_TYPE = "type";
        public static final String USAGE_TYPE_CALL = "call";
        public static final String USAGE_TYPE_LONG_TEXT = "long_text";
        public static final String USAGE_TYPE_SHORT_TEXT = "short_text";

        static 
        {
            FEEDBACK_URI = Uri.withAppendedPath(Data.CONTENT_URI, "usagefeedback");
            DELETE_USAGE_URI = Uri.withAppendedPath(Contacts.CONTENT_URI, "delete_usage");
        }

        public DataUsageFeedback()
        {
        }
    }

    protected static interface DataUsageStatColumns
    {

        public static final String LAST_TIME_USED = "last_time_used";
        public static final String LR_LAST_TIME_USED = "last_time_used";
        public static final String LR_TIMES_USED = "times_used";
        public static final String RAW_LAST_TIME_USED = "x_last_time_used";
        public static final String RAW_TIMES_USED = "x_times_used";
        public static final String TIMES_USED = "times_used";
    }

    public static final class DeletedContacts
        implements DeletedContactsColumns
    {

        public static final Uri CONTENT_URI;
        private static final int DAYS_KEPT = 30;
        public static final long DAYS_KEPT_MILLISECONDS = 0x9a7ec800L;

        static 
        {
            CONTENT_URI = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI, "deleted_contacts");
        }

        private DeletedContacts()
        {
        }
    }

    protected static interface DeletedContactsColumns
    {

        public static final String CONTACT_DELETED_TIMESTAMP = "contact_deleted_timestamp";
        public static final String CONTACT_ID = "contact_id";
    }

    public static final class Directory
        implements BaseColumns
    {

        public static boolean isEnterpriseDirectoryId(long l)
        {
            boolean flag;
            if(l >= 0x3b9aca00L)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public static boolean isRemoteDirectory(long l)
        {
            return isRemoteDirectoryId(l);
        }

        public static boolean isRemoteDirectoryId(long l)
        {
            boolean flag = false;
            boolean flag1 = flag;
            if(l != 0L)
            {
                flag1 = flag;
                if(l != 1L)
                {
                    flag1 = flag;
                    if(l != 0x3b9aca00L)
                    {
                        flag1 = flag;
                        if(l != 0x3b9aca01L)
                            flag1 = true;
                    }
                }
            }
            return flag1;
        }

        public static void notifyDirectoryChange(ContentResolver contentresolver)
        {
            ContentValues contentvalues = new ContentValues();
            contentresolver.update(CONTENT_URI, contentvalues, null, null);
        }

        public static final String ACCOUNT_NAME = "accountName";
        public static final String ACCOUNT_TYPE = "accountType";
        public static final String CALLER_PACKAGE_PARAM_KEY = "callerPackage";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/contact_directory";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/contact_directories";
        public static final Uri CONTENT_URI;
        public static final long DEFAULT = 0L;
        public static final String DIRECTORY_AUTHORITY = "authority";
        public static final String DISPLAY_NAME = "displayName";
        public static final Uri ENTERPRISE_CONTENT_URI;
        public static final long ENTERPRISE_DEFAULT = 0x3b9aca00L;
        public static final long ENTERPRISE_DIRECTORY_ID_BASE = 0x3b9aca00L;
        public static final Uri ENTERPRISE_FILE_URI;
        public static final long ENTERPRISE_LOCAL_INVISIBLE = 0x3b9aca01L;
        public static final String EXPORT_SUPPORT = "exportSupport";
        public static final int EXPORT_SUPPORT_ANY_ACCOUNT = 2;
        public static final int EXPORT_SUPPORT_NONE = 0;
        public static final int EXPORT_SUPPORT_SAME_ACCOUNT_ONLY = 1;
        public static final long LOCAL_INVISIBLE = 1L;
        public static final String PACKAGE_NAME = "packageName";
        public static final String PHOTO_SUPPORT = "photoSupport";
        public static final int PHOTO_SUPPORT_FULL = 3;
        public static final int PHOTO_SUPPORT_FULL_SIZE_ONLY = 2;
        public static final int PHOTO_SUPPORT_NONE = 0;
        public static final int PHOTO_SUPPORT_THUMBNAIL_ONLY = 1;
        public static final String SHORTCUT_SUPPORT = "shortcutSupport";
        public static final int SHORTCUT_SUPPORT_DATA_ITEMS_ONLY = 1;
        public static final int SHORTCUT_SUPPORT_FULL = 2;
        public static final int SHORTCUT_SUPPORT_NONE = 0;
        public static final String TYPE_RESOURCE_ID = "typeResourceId";

        static 
        {
            CONTENT_URI = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI, "directories");
            ENTERPRISE_CONTENT_URI = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI, "directories_enterprise");
            ENTERPRISE_FILE_URI = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI, "directory_file_enterprise");
        }

        private Directory()
        {
        }
    }

    public static interface DisplayNameSources
    {

        public static final int EMAIL = 10;
        public static final int NICKNAME = 35;
        public static final int ORGANIZATION = 30;
        public static final int PHONE = 20;
        public static final int STRUCTURED_NAME = 40;
        public static final int STRUCTURED_PHONETIC_NAME = 37;
        public static final int UNDEFINED = 0;
    }

    public static final class DisplayPhoto
    {

        public static final Uri CONTENT_MAX_DIMENSIONS_URI;
        public static final Uri CONTENT_URI;
        public static final String DISPLAY_MAX_DIM = "display_max_dim";
        public static final String THUMBNAIL_MAX_DIM = "thumbnail_max_dim";

        static 
        {
            CONTENT_URI = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI, "display_photo");
            CONTENT_MAX_DIMENSIONS_URI = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI, "photo_dimensions");
        }

        private DisplayPhoto()
        {
        }
    }

    public static interface FullNameStyle
    {

        public static final int CHINESE = 3;
        public static final int CJK = 2;
        public static final int JAPANESE = 4;
        public static final int KOREAN = 5;
        public static final int UNDEFINED = 0;
        public static final int WESTERN = 1;
    }

    public static final class Groups
        implements BaseColumns, GroupsColumns, SyncColumns
    {

        public static EntityIterator newEntityIterator(Cursor cursor)
        {
            return new EntityIteratorImpl(cursor);
        }

        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/group";
        public static final Uri CONTENT_SUMMARY_URI;
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/group";
        public static final Uri CONTENT_URI;

        static 
        {
            CONTENT_URI = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI, "groups");
            CONTENT_SUMMARY_URI = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI, "groups_summary");
        }

        private Groups()
        {
        }
    }

    private static class Groups.EntityIteratorImpl extends CursorEntityIterator
    {

        public android.content.Entity getEntityAndIncrementCursor(Cursor cursor)
            throws RemoteException
        {
            ContentValues contentvalues = new ContentValues();
            DatabaseUtils.cursorLongToContentValuesIfPresent(cursor, contentvalues, "_id");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, contentvalues, "account_name");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, contentvalues, "account_type");
            DatabaseUtils.cursorLongToContentValuesIfPresent(cursor, contentvalues, "dirty");
            DatabaseUtils.cursorLongToContentValuesIfPresent(cursor, contentvalues, "version");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, contentvalues, "sourceid");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, contentvalues, "res_package");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, contentvalues, "title");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, contentvalues, "title_res");
            DatabaseUtils.cursorLongToContentValuesIfPresent(cursor, contentvalues, "group_visible");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, contentvalues, "sync1");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, contentvalues, "sync2");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, contentvalues, "sync3");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, contentvalues, "sync4");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, contentvalues, "system_id");
            DatabaseUtils.cursorLongToContentValuesIfPresent(cursor, contentvalues, "deleted");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, contentvalues, "notes");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, contentvalues, "should_sync");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, contentvalues, "favorites");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, contentvalues, "auto_add");
            cursor.moveToNext();
            return new android.content.Entity(contentvalues);
        }

        public Groups.EntityIteratorImpl(Cursor cursor)
        {
            super(cursor);
        }
    }

    protected static interface GroupsColumns
    {

        public static final String ACCOUNT_TYPE_AND_DATA_SET = "account_type_and_data_set";
        public static final String AUTO_ADD = "auto_add";
        public static final String DATA_SET = "data_set";
        public static final String DELETED = "deleted";
        public static final String FAVORITES = "favorites";
        public static final String GROUP_IS_READ_ONLY = "group_is_read_only";
        public static final String GROUP_VISIBLE = "group_visible";
        public static final String NOTES = "notes";
        public static final String PARAM_RETURN_GROUP_COUNT_PER_ACCOUNT = "return_group_count_per_account";
        public static final String RES_PACKAGE = "res_package";
        public static final String SHOULD_SYNC = "should_sync";
        public static final String SUMMARY_COUNT = "summ_count";
        public static final String SUMMARY_GROUP_COUNT_PER_ACCOUNT = "group_count_per_account";
        public static final String SUMMARY_WITH_PHONES = "summ_phones";
        public static final String SYSTEM_ID = "system_id";
        public static final String TITLE = "title";
        public static final String TITLE_RES = "title_res";
    }

    public static final class Intents
    {

        public static final String ACTION_GET_MULTIPLE_PHONES = "com.android.contacts.action.GET_MULTIPLE_PHONES";
        public static final String ACTION_PROFILE_CHANGED = "android.provider.Contacts.PROFILE_CHANGED";
        public static final String ACTION_VOICE_SEND_MESSAGE_TO_CONTACTS = "android.provider.action.VOICE_SEND_MESSAGE_TO_CONTACTS";
        public static final String ATTACH_IMAGE = "com.android.contacts.action.ATTACH_IMAGE";
        public static final String CONTACTS_DATABASE_CREATED = "android.provider.Contacts.DATABASE_CREATED";
        public static final String EXTRA_CREATE_DESCRIPTION = "com.android.contacts.action.CREATE_DESCRIPTION";
        public static final String EXTRA_EXCLUDE_MIMES = "exclude_mimes";
        public static final String EXTRA_FORCE_CREATE = "com.android.contacts.action.FORCE_CREATE";
        public static final String EXTRA_MODE = "mode";
        public static final String EXTRA_PHONE_URIS = "com.android.contacts.extra.PHONE_URIS";
        public static final String EXTRA_RECIPIENT_CONTACT_CHAT_ID = "android.provider.extra.RECIPIENT_CONTACT_CHAT_ID";
        public static final String EXTRA_RECIPIENT_CONTACT_NAME = "android.provider.extra.RECIPIENT_CONTACT_NAME";
        public static final String EXTRA_RECIPIENT_CONTACT_URI = "android.provider.extra.RECIPIENT_CONTACT_URI";
        public static final String EXTRA_TARGET_RECT = "target_rect";
        public static final String INVITE_CONTACT = "com.android.contacts.action.INVITE_CONTACT";
        public static final String METADATA_ACCOUNT_TYPE = "android.provider.account_type";
        public static final String METADATA_MIMETYPE = "android.provider.mimetype";
        public static final int MODE_LARGE = 3;
        public static final int MODE_MEDIUM = 2;
        public static final int MODE_SMALL = 1;
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
        public static final String DATA = "data";
        public static final String EMAIL = "email";
        public static final String EMAIL_ISPRIMARY = "email_isprimary";
        public static final String EMAIL_TYPE = "email_type";
        public static final String EXTRA_ACCOUNT = "android.provider.extra.ACCOUNT";
        public static final String EXTRA_DATA_SET = "android.provider.extra.DATA_SET";
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

    public static final class MetadataSync
        implements BaseColumns, MetadataSyncColumns
    {

        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/contact_metadata";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/contact_metadata";
        public static final Uri CONTENT_URI;
        public static final String METADATA_AUTHORITY = "com.android.contacts.metadata";
        public static final Uri METADATA_AUTHORITY_URI;

        static 
        {
            METADATA_AUTHORITY_URI = Uri.parse("content://com.android.contacts.metadata");
            CONTENT_URI = Uri.withAppendedPath(METADATA_AUTHORITY_URI, "metadata_sync");
        }

        private MetadataSync()
        {
        }
    }

    protected static interface MetadataSyncColumns
    {

        public static final String ACCOUNT_NAME = "account_name";
        public static final String ACCOUNT_TYPE = "account_type";
        public static final String DATA = "data";
        public static final String DATA_SET = "data_set";
        public static final String DELETED = "deleted";
        public static final String RAW_CONTACT_BACKUP_ID = "raw_contact_backup_id";
    }

    public static final class MetadataSyncState
        implements BaseColumns, MetadataSyncStateColumns
    {

        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/contact_metadata_sync_state";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/contact_metadata_sync_state";
        public static final Uri CONTENT_URI;

        static 
        {
            CONTENT_URI = Uri.withAppendedPath(MetadataSync.METADATA_AUTHORITY_URI, "metadata_sync_state");
        }

        private MetadataSyncState()
        {
        }
    }

    protected static interface MetadataSyncStateColumns
    {

        public static final String ACCOUNT_NAME = "account_name";
        public static final String ACCOUNT_TYPE = "account_type";
        public static final String DATA_SET = "data_set";
        public static final String STATE = "state";
    }

    public static final class PhoneLookup
        implements BaseColumns, PhoneLookupColumns, ContactsColumns, ContactOptionsColumns, ContactNameColumns
    {

        public static final Uri CONTENT_FILTER_URI;
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/phone_lookup";
        public static final Uri ENTERPRISE_CONTENT_FILTER_URI;
        public static final String QUERY_PARAMETER_SIP_ADDRESS = "sip";

        static 
        {
            CONTENT_FILTER_URI = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI, "phone_lookup");
            ENTERPRISE_CONTENT_FILTER_URI = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI, "phone_lookup_enterprise");
        }

        private PhoneLookup()
        {
        }
    }

    protected static interface PhoneLookupColumns
    {

        public static final String CONTACT_ID = "contact_id";
        public static final String DATA_ID = "data_id";
        public static final String LABEL = "label";
        public static final String NORMALIZED_NUMBER = "normalized_number";
        public static final String NUMBER = "number";
        public static final String TYPE = "type";
    }

    public static interface PhoneticNameStyle
    {

        public static final int JAPANESE = 4;
        public static final int KOREAN = 5;
        public static final int PINYIN = 3;
        public static final int UNDEFINED = 0;
    }

    public static final class PhotoFiles
        implements BaseColumns, PhotoFilesColumns
    {

        private PhotoFiles()
        {
        }
    }

    protected static interface PhotoFilesColumns
    {

        public static final String FILESIZE = "filesize";
        public static final String HEIGHT = "height";
        public static final String WIDTH = "width";
    }

    public static final class PinnedPositions
    {

        public static void pin(ContentResolver contentresolver, long l, int i)
        {
            Uri uri = Uri.withAppendedPath(Contacts.CONTENT_URI, String.valueOf(l));
            ContentValues contentvalues = new ContentValues();
            contentvalues.put("pinned", Integer.valueOf(i));
            contentresolver.update(uri, contentvalues, null, null);
        }

        public static void undemote(ContentResolver contentresolver, long l)
        {
            contentresolver.call(ContactsContract.AUTHORITY_URI, "undemote", String.valueOf(l), null);
        }

        public static final int DEMOTED = -1;
        public static final String UNDEMOTE_METHOD = "undemote";
        public static final int UNPINNED = 0;

        public PinnedPositions()
        {
        }
    }

    public static final class Presence extends StatusUpdates
    {

        public Presence()
        {
            super(null);
        }
    }

    protected static interface PresenceColumns
    {

        public static final String CUSTOM_PROTOCOL = "custom_protocol";
        public static final String DATA_ID = "presence_data_id";
        public static final String IM_ACCOUNT = "im_account";
        public static final String IM_HANDLE = "im_handle";
        public static final String PROTOCOL = "protocol";
    }

    public static final class Profile
        implements BaseColumns, ContactsColumns, ContactOptionsColumns, ContactNameColumns, ContactStatusColumns
    {

        public static final Uri CONTENT_RAW_CONTACTS_URI;
        public static final Uri CONTENT_URI;
        public static final Uri CONTENT_VCARD_URI;
        public static final long MIN_ID = 0x7fffffff80000000L;

        static 
        {
            CONTENT_URI = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI, "profile");
            CONTENT_VCARD_URI = Uri.withAppendedPath(CONTENT_URI, "as_vcard");
            CONTENT_RAW_CONTACTS_URI = Uri.withAppendedPath(CONTENT_URI, "raw_contacts");
        }

        private Profile()
        {
        }
    }

    public static final class ProfileSyncState
        implements SyncStateContract.Columns
    {

        public static byte[] get(ContentProviderClient contentproviderclient, Account account)
            throws RemoteException
        {
            return SyncStateContract.Helpers.get(contentproviderclient, CONTENT_URI, account);
        }

        public static Pair getWithUri(ContentProviderClient contentproviderclient, Account account)
            throws RemoteException
        {
            return SyncStateContract.Helpers.getWithUri(contentproviderclient, CONTENT_URI, account);
        }

        public static ContentProviderOperation newSetOperation(Account account, byte abyte0[])
        {
            return SyncStateContract.Helpers.newSetOperation(CONTENT_URI, account, abyte0);
        }

        public static void set(ContentProviderClient contentproviderclient, Account account, byte abyte0[])
            throws RemoteException
        {
            SyncStateContract.Helpers.set(contentproviderclient, CONTENT_URI, account, abyte0);
        }

        public static final String CONTENT_DIRECTORY = "syncstate";
        public static final Uri CONTENT_URI;

        static 
        {
            CONTENT_URI = Uri.withAppendedPath(Profile.CONTENT_URI, "syncstate");
        }

        private ProfileSyncState()
        {
        }
    }

    public static final class ProviderStatus
    {

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/provider_status";
        public static final Uri CONTENT_URI;
        public static final String DATABASE_CREATION_TIMESTAMP = "database_creation_timestamp";
        public static final String STATUS = "status";
        public static final int STATUS_BUSY = 1;
        public static final int STATUS_EMPTY = 2;
        public static final int STATUS_NORMAL = 0;

        static 
        {
            CONTENT_URI = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI, "provider_status");
        }

        private ProviderStatus()
        {
        }
    }

    public static final class QuickContact
    {

        public static Intent composeQuickContactsIntent(Context context, Rect rect, Uri uri, int i, String as[])
        {
            for(; (context instanceof ContextWrapper) && (context instanceof Activity) ^ true; context = ((ContextWrapper)context).getBaseContext());
            int j;
            if(context instanceof Activity)
                j = 0;
            else
                j = 0x10008000;
            context = (new Intent("android.provider.action.QUICK_CONTACT")).addFlags(j | 0x20000000);
            context.setData(uri);
            context.setSourceBounds(rect);
            context.putExtra("android.provider.extra.MODE", i);
            context.putExtra("android.provider.extra.EXCLUDE_MIMES", as);
            return context;
        }

        public static Intent composeQuickContactsIntent(Context context, View view, Uri uri, int i, String as[])
        {
            float f = context.getResources().getCompatibilityInfo().applicationScale;
            int ai[] = new int[2];
            view.getLocationOnScreen(ai);
            Rect rect = new Rect();
            rect.left = (int)((float)ai[0] * f + 0.5F);
            rect.top = (int)((float)ai[1] * f + 0.5F);
            rect.right = (int)((float)(ai[0] + view.getWidth()) * f + 0.5F);
            rect.bottom = (int)((float)(ai[1] + view.getHeight()) * f + 0.5F);
            return composeQuickContactsIntent(context, rect, uri, i, as);
        }

        public static Intent rebuildManagedQuickContactsIntent(String s, long l, boolean flag, long l1, Intent intent)
        {
            Intent intent1 = new Intent("android.provider.action.QUICK_CONTACT");
            Uri uri = null;
            if(!TextUtils.isEmpty(s))
                if(flag)
                    uri = Uri.withAppendedPath(Contacts.CONTENT_LOOKUP_URI, s);
                else
                    uri = Contacts.getLookupUri(l, s);
            s = uri;
            if(uri != null)
            {
                s = uri;
                if(l1 != 0L)
                    s = uri.buildUpon().appendQueryParameter("directory", String.valueOf(l1)).build();
            }
            intent1.setData(s);
            intent1.setFlags(intent.getFlags() | 0x10000000);
            intent1.setSourceBounds(intent.getSourceBounds());
            intent1.putExtra("android.provider.extra.MODE", intent.getIntExtra("android.provider.extra.MODE", 3));
            intent1.putExtra("android.provider.extra.EXCLUDE_MIMES", intent.getStringArrayExtra("android.provider.extra.EXCLUDE_MIMES"));
            return intent1;
        }

        public static void showQuickContact(Context context, Rect rect, Uri uri, int i, String as[])
        {
            ContactsInternal.startQuickContactWithErrorToast(context, composeQuickContactsIntent(context, rect, uri, i, as));
        }

        public static void showQuickContact(Context context, Rect rect, Uri uri, String as[], String s)
        {
            rect = composeQuickContactsIntent(context, rect, uri, 3, as);
            rect.putExtra("android.provider.extra.PRIORITIZED_MIMETYPE", s);
            ContactsInternal.startQuickContactWithErrorToast(context, rect);
        }

        public static void showQuickContact(Context context, View view, Uri uri, int i, String as[])
        {
            ContactsInternal.startQuickContactWithErrorToast(context, composeQuickContactsIntent(context, view, uri, i, as));
        }

        public static void showQuickContact(Context context, View view, Uri uri, String as[], String s)
        {
            view = composeQuickContactsIntent(context, view, uri, 3, as);
            view.putExtra("android.provider.extra.PRIORITIZED_MIMETYPE", s);
            ContactsInternal.startQuickContactWithErrorToast(context, view);
        }

        public static final String ACTION_QUICK_CONTACT = "android.provider.action.QUICK_CONTACT";
        public static final String EXTRA_EXCLUDE_MIMES = "android.provider.extra.EXCLUDE_MIMES";
        public static final String EXTRA_MODE = "android.provider.extra.MODE";
        public static final String EXTRA_PRIORITIZED_MIMETYPE = "android.provider.extra.PRIORITIZED_MIMETYPE";
        public static final String EXTRA_TARGET_RECT = "android.provider.extra.TARGET_RECT";
        public static final int MODE_DEFAULT = 3;
        public static final int MODE_LARGE = 3;
        public static final int MODE_MEDIUM = 2;
        public static final int MODE_SMALL = 1;

        public QuickContact()
        {
        }
    }

    public static final class RawContacts
        implements BaseColumns, RawContactsColumns, ContactOptionsColumns, ContactNameColumns, SyncColumns
    {

        public static Uri getContactLookupUri(ContentResolver contentresolver, Uri uri)
        {
            SeempLog.record(89);
            contentresolver = contentresolver.query(Uri.withAppendedPath(uri, "data"), new String[] {
                "contact_id", "lookup"
            }, null, null, null);
            if(contentresolver == null)
                break MISSING_BLOCK_LABEL_77;
            if(!contentresolver.moveToFirst())
                break MISSING_BLOCK_LABEL_77;
            uri = Contacts.getLookupUri(contentresolver.getLong(0), contentresolver.getString(1));
            if(contentresolver != null)
                contentresolver.close();
            return uri;
            if(contentresolver != null)
                contentresolver.close();
            return null;
            uri;
            if(contentresolver != null)
                contentresolver.close();
            throw uri;
        }

        public static EntityIterator newEntityIterator(Cursor cursor)
        {
            return new EntityIteratorImpl(cursor);
        }

        public static final int AGGREGATION_MODE_DEFAULT = 0;
        public static final int AGGREGATION_MODE_DISABLED = 3;
        public static final int AGGREGATION_MODE_IMMEDIATE = 1;
        public static final int AGGREGATION_MODE_SUSPENDED = 2;
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/raw_contact";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/raw_contact";
        public static final Uri CONTENT_URI;

        static 
        {
            CONTENT_URI = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI, "raw_contacts");
        }

        private RawContacts()
        {
        }
    }

    public static final class RawContacts.Data
        implements BaseColumns, DataColumns
    {

        public static final String CONTENT_DIRECTORY = "data";

        private RawContacts.Data()
        {
        }
    }

    public static final class RawContacts.DisplayPhoto
    {

        public static final String CONTENT_DIRECTORY = "display_photo";

        private RawContacts.DisplayPhoto()
        {
        }
    }

    public static final class RawContacts.Entity
        implements BaseColumns, DataColumns
    {

        public static final String CONTENT_DIRECTORY = "entity";
        public static final String DATA_ID = "data_id";

        private RawContacts.Entity()
        {
        }
    }

    private static class RawContacts.EntityIteratorImpl extends CursorEntityIterator
    {

        public android.content.Entity getEntityAndIncrementCursor(Cursor cursor)
            throws RemoteException
        {
            int i;
            long l;
            Object obj;
            i = cursor.getColumnIndexOrThrow("_id");
            l = cursor.getLong(i);
            obj = new ContentValues();
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "account_name");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "account_type");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "data_set");
            DatabaseUtils.cursorLongToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "_id");
            DatabaseUtils.cursorLongToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "dirty");
            DatabaseUtils.cursorLongToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "version");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "sourceid");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "sync1");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "sync2");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "sync3");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "sync4");
            DatabaseUtils.cursorLongToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "deleted");
            DatabaseUtils.cursorLongToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "contact_id");
            DatabaseUtils.cursorLongToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "starred");
            obj = new android.content.Entity(((ContentValues) (obj)));
_L7:
            if(l == cursor.getLong(i)) goto _L2; else goto _L1
_L1:
            return ((android.content.Entity) (obj));
_L2:
            ContentValues contentvalues;
            String as[];
            int j;
            int k;
            contentvalues = new ContentValues();
            contentvalues.put("_id", Long.valueOf(cursor.getLong(cursor.getColumnIndexOrThrow("data_id"))));
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, contentvalues, "res_package");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, contentvalues, "mimetype");
            DatabaseUtils.cursorLongToContentValuesIfPresent(cursor, contentvalues, "is_primary");
            DatabaseUtils.cursorLongToContentValuesIfPresent(cursor, contentvalues, "is_super_primary");
            DatabaseUtils.cursorLongToContentValuesIfPresent(cursor, contentvalues, "data_version");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, contentvalues, "group_sourceid");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, contentvalues, "data_version");
            as = DATA_KEYS;
            j = 0;
            k = as.length;
_L4:
            String s;
            int i1;
            if(j >= k)
                break MISSING_BLOCK_LABEL_381;
            s = as[j];
            i1 = cursor.getColumnIndexOrThrow(s);
            switch(cursor.getType(i1))
            {
            default:
                throw new IllegalStateException("Invalid or unhandled data type");

            case 0: // '\0'
                break;

            case 4: // '\004'
                break; /* Loop/switch isn't completed */

            case 1: // '\001'
            case 2: // '\002'
            case 3: // '\003'
                contentvalues.put(s, cursor.getString(i1));
                break;
            }
_L5:
            j++;
            if(true) goto _L4; else goto _L3
_L3:
            contentvalues.put(s, cursor.getBlob(i1));
              goto _L5
            if(true) goto _L4; else goto _L6
_L6:
            ((android.content.Entity) (obj)).addSubValue(Data.CONTENT_URI, contentvalues);
            if(!cursor.moveToNext()) goto _L1; else goto _L7
        }

        private static final String DATA_KEYS[] = {
            "data1", "data2", "data3", "data4", "data5", "data6", "data7", "data8", "data9", "data10", 
            "data11", "data12", "data13", "data14", "data15", "data_sync1", "data_sync2", "data_sync3", "data_sync4"
        };


        public RawContacts.EntityIteratorImpl(Cursor cursor)
        {
            super(cursor);
        }
    }

    public static final class RawContacts.StreamItems
        implements BaseColumns, StreamItemsColumns
    {

        public static final String CONTENT_DIRECTORY = "stream_items";

        private RawContacts.StreamItems()
        {
        }
    }

    protected static interface RawContactsColumns
    {

        public static final String ACCOUNT_TYPE_AND_DATA_SET = "account_type_and_data_set";
        public static final String AGGREGATION_MODE = "aggregation_mode";
        public static final String BACKUP_ID = "backup_id";
        public static final String CONTACT_ID = "contact_id";
        public static final String DATA_SET = "data_set";
        public static final String DELETED = "deleted";
        public static final String METADATA_DIRTY = "metadata_dirty";
        public static final String RAW_CONTACT_IS_READ_ONLY = "raw_contact_is_read_only";
        public static final String RAW_CONTACT_IS_USER_PROFILE = "raw_contact_is_user_profile";
    }

    public static final class RawContactsEntity
        implements BaseColumns, DataColumns, RawContactsColumns
    {

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/raw_contact_entity";
        public static final Uri CONTENT_URI;
        public static final Uri CORP_CONTENT_URI;
        public static final String DATA_ID = "data_id";
        public static final String FOR_EXPORT_ONLY = "for_export_only";
        public static final Uri PROFILE_CONTENT_URI;

        static 
        {
            CONTENT_URI = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI, "raw_contact_entities");
            CORP_CONTENT_URI = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI, "raw_contact_entities_corp");
            PROFILE_CONTENT_URI = Uri.withAppendedPath(Profile.CONTENT_URI, "raw_contact_entities");
        }

        private RawContactsEntity()
        {
        }
    }

    public static class SearchSnippets
    {

        public static final String DEFERRED_SNIPPETING_KEY = "deferred_snippeting";
        public static final String SNIPPET = "snippet";
        public static final String SNIPPET_ARGS_PARAM_KEY = "snippet_args";

        public SearchSnippets()
        {
        }
    }

    public static final class Settings
        implements SettingsColumns
    {

        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/setting";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/setting";
        public static final Uri CONTENT_URI;

        static 
        {
            CONTENT_URI = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI, "settings");
        }

        private Settings()
        {
        }
    }

    protected static interface SettingsColumns
    {

        public static final String ACCOUNT_NAME = "account_name";
        public static final String ACCOUNT_TYPE = "account_type";
        public static final String ANY_UNSYNCED = "any_unsynced";
        public static final String DATA_SET = "data_set";
        public static final String SHOULD_SYNC = "should_sync";
        public static final String UNGROUPED_COUNT = "summ_count";
        public static final String UNGROUPED_VISIBLE = "ungrouped_visible";
        public static final String UNGROUPED_WITH_PHONES = "summ_phones";
    }

    protected static interface StatusColumns
    {

        public static final int AVAILABLE = 5;
        public static final int AWAY = 2;
        public static final int CAPABILITY_HAS_CAMERA = 4;
        public static final int CAPABILITY_HAS_VIDEO = 2;
        public static final int CAPABILITY_HAS_VOICE = 1;
        public static final String CHAT_CAPABILITY = "chat_capability";
        public static final int DO_NOT_DISTURB = 4;
        public static final int IDLE = 3;
        public static final int INVISIBLE = 1;
        public static final int OFFLINE = 0;
        public static final String PRESENCE = "mode";
        public static final String PRESENCE_CUSTOM_STATUS = "status";
        public static final String PRESENCE_STATUS = "mode";
        public static final String STATUS = "status";
        public static final String STATUS_ICON = "status_icon";
        public static final String STATUS_LABEL = "status_label";
        public static final String STATUS_RES_PACKAGE = "status_res_package";
        public static final String STATUS_TIMESTAMP = "status_ts";
    }

    public static class StatusUpdates
        implements StatusColumns, PresenceColumns
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

        public static final int getPresencePrecedence(int i)
        {
            return i;
        }

        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/status-update";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/status-update";
        public static final Uri CONTENT_URI;
        public static final Uri PROFILE_CONTENT_URI;

        static 
        {
            CONTENT_URI = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI, "status_updates");
            PROFILE_CONTENT_URI = Uri.withAppendedPath(Profile.CONTENT_URI, "status_updates");
        }

        private StatusUpdates()
        {
        }

        StatusUpdates(StatusUpdates statusupdates)
        {
            this();
        }
    }

    public static final class StreamItemPhotos
        implements BaseColumns, StreamItemPhotosColumns
    {

        public static final String PHOTO = "photo";

        private StreamItemPhotos()
        {
        }
    }

    protected static interface StreamItemPhotosColumns
    {

        public static final String PHOTO_FILE_ID = "photo_file_id";
        public static final String PHOTO_URI = "photo_uri";
        public static final String SORT_INDEX = "sort_index";
        public static final String STREAM_ITEM_ID = "stream_item_id";
        public static final String SYNC1 = "stream_item_photo_sync1";
        public static final String SYNC2 = "stream_item_photo_sync2";
        public static final String SYNC3 = "stream_item_photo_sync3";
        public static final String SYNC4 = "stream_item_photo_sync4";
    }

    public static final class StreamItems
        implements BaseColumns, StreamItemsColumns
    {

        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/stream_item";
        public static final Uri CONTENT_LIMIT_URI;
        public static final Uri CONTENT_PHOTO_URI;
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/stream_item";
        public static final Uri CONTENT_URI;
        public static final String MAX_ITEMS = "max_items";

        static 
        {
            CONTENT_URI = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI, "stream_items");
            CONTENT_PHOTO_URI = Uri.withAppendedPath(CONTENT_URI, "photo");
            CONTENT_LIMIT_URI = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI, "stream_items_limit");
        }

        private StreamItems()
        {
        }
    }

    public static final class StreamItems.StreamItemPhotos
        implements BaseColumns, StreamItemPhotosColumns
    {

        public static final String CONTENT_DIRECTORY = "photo";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/stream_item_photo";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/stream_item_photo";

        private StreamItems.StreamItemPhotos()
        {
        }
    }

    protected static interface StreamItemsColumns
    {

        public static final String ACCOUNT_NAME = "account_name";
        public static final String ACCOUNT_TYPE = "account_type";
        public static final String COMMENTS = "comments";
        public static final String CONTACT_ID = "contact_id";
        public static final String CONTACT_LOOKUP_KEY = "contact_lookup";
        public static final String DATA_SET = "data_set";
        public static final String RAW_CONTACT_ID = "raw_contact_id";
        public static final String RAW_CONTACT_SOURCE_ID = "raw_contact_source_id";
        public static final String RES_ICON = "icon";
        public static final String RES_LABEL = "label";
        public static final String RES_PACKAGE = "res_package";
        public static final String SYNC1 = "stream_item_sync1";
        public static final String SYNC2 = "stream_item_sync2";
        public static final String SYNC3 = "stream_item_sync3";
        public static final String SYNC4 = "stream_item_sync4";
        public static final String TEXT = "text";
        public static final String TIMESTAMP = "timestamp";
    }

    protected static interface SyncColumns
        extends BaseSyncColumns
    {

        public static final String ACCOUNT_NAME = "account_name";
        public static final String ACCOUNT_TYPE = "account_type";
        public static final String DIRTY = "dirty";
        public static final String SOURCE_ID = "sourceid";
        public static final String VERSION = "version";
    }

    public static final class SyncState
        implements SyncStateContract.Columns
    {

        public static byte[] get(ContentProviderClient contentproviderclient, Account account)
            throws RemoteException
        {
            return SyncStateContract.Helpers.get(contentproviderclient, CONTENT_URI, account);
        }

        public static Pair getWithUri(ContentProviderClient contentproviderclient, Account account)
            throws RemoteException
        {
            return SyncStateContract.Helpers.getWithUri(contentproviderclient, CONTENT_URI, account);
        }

        public static ContentProviderOperation newSetOperation(Account account, byte abyte0[])
        {
            return SyncStateContract.Helpers.newSetOperation(CONTENT_URI, account, abyte0);
        }

        public static void set(ContentProviderClient contentproviderclient, Account account, byte abyte0[])
            throws RemoteException
        {
            SyncStateContract.Helpers.set(contentproviderclient, CONTENT_URI, account, abyte0);
        }

        public static final String CONTENT_DIRECTORY = "syncstate";
        public static final Uri CONTENT_URI;

        static 
        {
            CONTENT_URI = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI, "syncstate");
        }

        private SyncState()
        {
        }
    }

    public static interface SyncStateColumns
        extends SyncStateContract.Columns
    {
    }


    public ContactsContract()
    {
    }

    public static boolean isProfileId(long l)
    {
        boolean flag;
        if(l >= 0x7fffffff80000000L)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static final String AUTHORITY = "com.android.contacts";
    public static final Uri AUTHORITY_URI = Uri.parse("content://com.android.contacts");
    public static final String CALLER_IS_SYNCADAPTER = "caller_is_syncadapter";
    public static final String DEFERRED_SNIPPETING = "deferred_snippeting";
    public static final String DEFERRED_SNIPPETING_QUERY = "deferred_snippeting_query";
    public static final String DIRECTORY_PARAM_KEY = "directory";
    public static final String HIDDEN_COLUMN_PREFIX = "x_";
    public static final String LIMIT_PARAM_KEY = "limit";
    public static final String PRIMARY_ACCOUNT_NAME = "name_for_primary_account";
    public static final String PRIMARY_ACCOUNT_TYPE = "type_for_primary_account";
    public static final String REMOVE_DUPLICATE_ENTRIES = "remove_duplicate_entries";
    public static final String STREQUENT_PHONE_ONLY = "strequent_phone_only";

}
