// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.provider;

import android.net.Uri;
import android.provider.ContactsContract;
import miui.os.Build;
import miui.util.FeatureParser;

public class MiProfile
{
    public static class Video
    {

        public static final String CONTACT_COLUMN = "video";
        public static final String CONTENT_ITEM_TYPE = "vnd.com.miui.cursor.item/profileVideo";
        public static final String DIGEST = "data2";
        public static final String FILEPATH = "data1";
        public static final String SYNC = "data3";

        private Video()
        {
        }
    }


    public MiProfile()
    {
    }

    public static boolean isEnabled()
    {
        if(Build.IS_CTA_BUILD || isPad())
            return false;
        return !Build.IS_INTERNATIONAL_BUILD || !(Build.checkRegion("IN") ^ true);
    }

    private static boolean isPad()
    {
        return FeatureParser.getBoolean("is_pad", false);
    }

    public static final String ACTION_DELETE = "com.miui.cloudservice.miprofile.action.DELETE";
    public static final String ACTION_MESSENGER = "com.miui.cloudservice.miprofile.action.MESSENGER";
    public static final String ACTION_NEW_REQUEST = "com.android.contacts.miprofile.action.NEWREQUEST";
    public static final String ACTION_REQUEST_AGREED = "com.android.contacts.miprofile.action.REQUEST_AGREED";
    public static final int ERROR_CODE_MIPROFILE_ACCEPT_SELF = 32104;
    public static final int ERROR_CODE_MIPROFILE_NOT_FOUND = 10008;
    public static final int ERROR_CODE_MIPROFILE_NOT_OPENED = 32100;
    public static final int ERROR_CODE_MIPROFILE_NOT_SET = 32101;
    public static final int ERROR_CODE_NONE = 0;
    public static final int ERROR_CODE_NO_MIACCOUNT = -2;
    public static final int ERROR_CODE_PDC_INVALID_SIZE = 10008;
    public static final int ERROR_CODE_UNKNOWN = -1;
    public static final String EXTRA_BOOLEAN_MIPROFILE = "EXTRA_BOOLEAN_MIPROFILE";
    public static final String EXTRA_INT_MIPROFILE = "EXTRA_INT_MIPROFILE";
    public static final String EXTRA_MIPROFILE_ID = "id";
    public static final String EXTRA_MIPROFILE_NICKNAME = "nickName";
    public static final String EXTRA_NEW_REQUEST_COUNT = "EXTRA_NEW_REQUEST_COUNT";
    public static final String EXTRA_NEW_REQUEST_NAME = "EXTRA_NEW_REQUEST_NAME";
    public static final String EXTRA_PHONE_NUMBER = "phoneNumber";
    public static final String EXTRA_RELATED_CONTACTS = "relatedContacts";
    public static final String EXTRA_REQUEST_AGREED_CONTACT_NAME = "EXTRA_REQUEST_AGREED_CONTACT_NAME";
    public static final String EXTRA_REQUEST_AGREED_CONTACT_URI = "EXTRA_REQUEST_AGREED_CONTACT_URI";
    public static final String EXTRA_SKIP_COMPRESS = "EXTRA_SKIP_COMPRESS";
    public static final String EXTRA_STRING_MIPROFILE = "EXTRA_STRING_MIPROFILE";
    public static final String EXTRA_URI_MIPROFILE = "EXTRA_URI_MIPROFILE";
    public static final String MIPROFILE_ACCEPTTIME = "acceptTime";
    public static final String MIPROFILE_ACCOUNT_TYPE = "com.xiaomi.miprofile";
    public static final String MIPROFILE_CREATETIME = "createTime";
    public static final String MIPROFILE_EXPIRETIME = "expireTime";
    public static final String MIPROFILE_HASUPDATE = "hasUpdate";
    public static final String MIPROFILE_ID = "_id";
    public static final String MIPROFILE_IGNORE = "ignore";
    public static final String MIPROFILE_INITIAL_SYNC = "miprofile_initial_sync";
    public static final String MIPROFILE_JSON = "json";
    public static final String MIPROFILE_NAME = "name";
    public static final String MIPROFILE_NUMBER = "number";
    public static final String MIPROFILE_NUMBERS_TABLE = "miprofile_numbers";
    public static final Uri MIPROFILE_NUMBERS_URI;
    public static final String MIPROFILE_READ = "read";
    public static final String MIPROFILE_RECOMMEND_STATUS_VERIFIED = "verified";
    public static final String MIPROFILE_RECOMMEND_STATUS_VERIFYING = "verifying";
    public static final String MIPROFILE_SID = "sid";
    public static final String MIPROFILE_SOURCEID = "default";
    public static final String MIPROFILE_STATUS = "status";
    public static final String MIPROFILE_TABLE = "miprofile";
    public static final String MIPROFILE_TYPE = "type";
    public static final String MIPROFILE_TYPE_RECOMMEND = "Recommend";
    public static final String MIPROFILE_TYPE_REQUEST = "Request";
    public static final String MIPROFILE_TYPE_SEND = "SendCard";
    public static final String MIPROFILE_TYPE_SNAPSHOT = "Snapshot";
    public static final Uri MIPROFILE_URI;
    public static final int MSG_REQUEST_ACCEPT_MIPROFILE = 9;
    public static final int MSG_REQUEST_BATCH_REQUEST = 20;
    public static final int MSG_REQUEST_BINDED_NUMBER = 16;
    public static final int MSG_REQUEST_CHECK_MIPROFILE = 5;
    public static final int MSG_REQUEST_DELETE_MIPROFILE = 11;
    public static final int MSG_REQUEST_INIT_RECOMMEND = 19;
    public static final int MSG_REQUEST_READ_MIPROFILE = 15;
    public static final int MSG_REQUEST_REQUEST_MIPROFILE = 7;
    public static final int MSG_REQUEST_SAVE_MIPROFILE = 1;
    public static final int MSG_REQUEST_SHARE_MIPROFILE = 13;
    public static final int MSG_REQUEST_SWITCH_MIPROFILE = 3;
    public static final int MSG_REQUEST_UPDATE_NICKNAME = 18;
    public static final int MSG_RESPONSE_ACCEPT_MIPROFILE = 10;
    public static final int MSG_RESPONSE_BATCH_REQUEST = 21;
    public static final int MSG_RESPONSE_BINDED_NUMBER = 17;
    public static final int MSG_RESPONSE_CHECK_MIPROFILE = 6;
    public static final int MSG_RESPONSE_DELETE_MIPROFILE = 12;
    public static final int MSG_RESPONSE_REQUEST_MIPROFILE = 8;
    public static final int MSG_RESPONSE_SAVE_MIPROFILE = 2;
    public static final int MSG_RESPONSE_SHARE_MIPROFILE = 14;
    public static final int MSG_RESPONSE_SWITCH_MIPROFILE = 4;
    public static final String PACKAGE_SCOPE_CLOUDSERVICE = "com.miui.cloudservice";
    public static final String PHOTO_TYPE_BIG = "photo";
    public static final String PHOTO_TYPE_SMALL = "thumbnail";
    public static final String SETTINGS_MIPROFILE_BADGE_NOTICE = "miprofile.settings.miprofile_badge_notice";
    public static final String SETTINGS_MIPROFILE_ON = "miprofile.settings.miprofile_on";
    public static final String SETTINGS_MIPROFILE_RECOMMEND_INITED = "miprofile.settings.miprofile_recommend_inited";
    public static final String SETTINGS_MIPROFILE_SET = "miprofile.settings.miprofile_set";
    public static final String SETTINGS_MIPROFILE_USER_NOTICE = "miprofile.settings.miprofile_user_notice";
    public static final String SETTINGS_MIPROFILE_VISIBLE = "miprofile.settings.miprofile_visible";
    public static final String TAG = "MiProfile";

    static 
    {
        MIPROFILE_URI = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI, "miprofile");
        MIPROFILE_NUMBERS_URI = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI, "miprofile_numbers");
    }
}
