// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.provider;

import android.content.*;
import android.content.pm.UserInfo;
import android.database.Cursor;
import android.location.Country;
import android.location.CountryDetector;
import android.net.Uri;
import android.os.UserHandle;
import android.os.UserManager;
import android.telecom.*;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.Log;
import com.android.internal.telephony.CallerInfo;
import java.util.List;

// Referenced classes of package android.provider:
//            BaseColumns

public class CallLog
{
    public static class Calls
        implements BaseColumns
    {

        public static Uri addCall(CallerInfo callerinfo, Context context, String s, int i, int j, int k, PhoneAccountHandle phoneaccounthandle, long l, int i1, Long long1)
        {
            return addCall(callerinfo, context, s, "", "", i, j, k, phoneaccounthandle, l, i1, long1, false, null, false);
        }

        public static Uri addCall(CallerInfo callerinfo, Context context, String s, String s1, String s2, int i, int j, int k, 
                PhoneAccountHandle phoneaccounthandle, long l, int i1, Long long1, boolean flag, UserHandle userhandle)
        {
            return addCall(callerinfo, context, s, s1, s2, i, j, k, phoneaccounthandle, l, i1, long1, flag, userhandle, false);
        }

        public static Uri addCall(CallerInfo callerinfo, Context context, String s, String s1, String s2, int i, int j, int k, 
                PhoneAccountHandle phoneaccounthandle, long l, int i1, Long long1, boolean flag, UserHandle userhandle, 
                boolean flag1)
        {
            ContentResolver contentresolver;
            byte byte0;
            Object obj;
            contentresolver = context.getContentResolver();
            byte0 = 1;
            obj = null;
            TelecomManager telecommanager = TelecomManager.from(context);
            obj = telecommanager;
_L1:
            String s4 = null;
            String s3 = s4;
            if(obj != null)
            {
                s3 = s4;
                if(phoneaccounthandle != null)
                {
                    obj = ((TelecomManager) (obj)).getPhoneAccount(phoneaccounthandle);
                    s3 = s4;
                    if(obj != null)
                    {
                        obj = ((PhoneAccount) (obj)).getSubscriptionAddress();
                        s3 = s4;
                        if(obj != null)
                            s3 = ((Uri) (obj)).getSchemeSpecificPart();
                    }
                }
            }
            if(i == 2)
                byte0 = 2;
            else
            if(i == 4)
                byte0 = 4;
            else
            if(TextUtils.isEmpty(s) || i == 3)
                byte0 = 3;
            if(byte0 != 1)
            {
                obj = "";
                s = ((String) (obj));
                if(callerinfo != null)
                {
                    callerinfo.name = "";
                    s = ((String) (obj));
                }
            }
            obj = null;
            s4 = null;
            if(phoneaccounthandle != null)
            {
                obj = phoneaccounthandle.getComponentName().flattenToString();
                s4 = phoneaccounthandle.getId();
            }
            phoneaccounthandle = new ContentValues(6);
            phoneaccounthandle.put("number", s);
            phoneaccounthandle.put("post_dial_digits", s1);
            phoneaccounthandle.put("via_number", s2);
            phoneaccounthandle.put("presentation", Integer.valueOf(byte0));
            phoneaccounthandle.put("type", Integer.valueOf(j));
            phoneaccounthandle.put("features", Integer.valueOf(k));
            phoneaccounthandle.put("date", Long.valueOf(l));
            phoneaccounthandle.put("duration", Long.valueOf(i1));
            if(long1 != null)
                phoneaccounthandle.put("data_usage", long1);
            phoneaccounthandle.put("subscription_component_name", ((String) (obj)));
            phoneaccounthandle.put("subscription_id", s4);
            phoneaccounthandle.put("phone_account_address", s3);
            phoneaccounthandle.put("new", Integer.valueOf(1));
            if(flag)
                i = 1;
            else
                i = 0;
            phoneaccounthandle.put("add_for_all_users", Integer.valueOf(i));
            if(j == 3 || j == 1002)
            {
                UnsupportedOperationException unsupportedoperationexception;
                if(flag1)
                    i = 1;
                else
                    i = 0;
                phoneaccounthandle.put("is_read", Integer.valueOf(i));
            }
            if(callerinfo == null || callerinfo.contactIdOrZero <= 0L)
                break MISSING_BLOCK_LABEL_509;
            if(callerinfo.normalizedNumber != null)
            {
                s1 = callerinfo.normalizedNumber;
                s2 = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                l = callerinfo.contactIdOrZero;
                s1 = contentresolver.query(s2, new String[] {
                    "_id"
                }, "contact_id =? AND data4 =?", new String[] {
                    String.valueOf(l), s1
                }, null);
            } else
            {
                if(callerinfo.phoneNumber != null)
                    s1 = callerinfo.phoneNumber;
                else
                    s1 = s;
                s1 = Uri.withAppendedPath(ContactsContract.CommonDataKinds.Callable.CONTENT_FILTER_URI, Uri.encode(s1));
                l = callerinfo.contactIdOrZero;
                s1 = contentresolver.query(s1, new String[] {
                    "_id"
                }, "contact_id =?", new String[] {
                    String.valueOf(l)
                }, null);
            }
            if(s1 == null)
                break MISSING_BLOCK_LABEL_509;
            if(s1.getCount() <= 0 || !s1.moveToFirst())
                break MISSING_BLOCK_LABEL_503;
            s2 = s1.getString(0);
            updateDataUsageStatForData(contentresolver, s2);
            if(i1 < 10000 || j != 2 && j != 1001)
                break MISSING_BLOCK_LABEL_503;
            if(TextUtils.isEmpty(callerinfo.normalizedNumber))
                updateNormalizedNumber(context, contentresolver, s2, s);
            s1.close();
            callerinfo = null;
            s2 = (UserManager)context.getSystemService(android/os/UserManager);
            j = s2.getUserHandle();
            if(!flag)
                break MISSING_BLOCK_LABEL_827;
            s = addEntryAndRemoveExpiredEntries(context, s2, UserHandle.SYSTEM, phoneaccounthandle);
            if(s == null || "call_log_shadow".equals(s.getAuthority()))
                return null;
            break MISSING_BLOCK_LABEL_693;
            unsupportedoperationexception;
              goto _L1
            callerinfo;
            s1.close();
            throw callerinfo;
            if(j == 0)
                callerinfo = s;
            long1 = s2.getUsers(true);
            k = long1.size();
            i = 0;
            do
            {
                s = callerinfo;
                if(i >= k)
                    break;
                s1 = ((UserInfo)long1.get(i)).getUserHandle();
                i1 = s1.getIdentifier();
                if(s1.isSystem())
                {
                    s = callerinfo;
                } else
                {
                    s = callerinfo;
                    if(shouldHaveSharedCallLogEntries(context, s2, i1))
                    {
                        s = callerinfo;
                        if(s2.isUserRunning(s1))
                        {
                            s = callerinfo;
                            if(s2.isUserUnlocked(s1))
                            {
                                s1 = addEntryAndRemoveExpiredEntries(context, s2, s1, phoneaccounthandle);
                                s = callerinfo;
                                if(i1 == j)
                                    s = s1;
                            }
                        }
                    }
                }
                i++;
                callerinfo = s;
            } while(true);
            break MISSING_BLOCK_LABEL_845;
            if(userhandle != null)
                callerinfo = userhandle;
            else
                callerinfo = UserHandle.of(j);
            s = addEntryAndRemoveExpiredEntries(context, s2, callerinfo, phoneaccounthandle);
            return s;
        }

        private static Uri addEntryAndRemoveExpiredEntries(Context context, UserManager usermanager, UserHandle userhandle, ContentValues contentvalues)
        {
            ContentResolver contentresolver;
            contentresolver = context.getContentResolver();
            if(usermanager.isUserUnlocked(userhandle))
                context = CONTENT_URI;
            else
                context = SHADOW_CONTENT_URI;
            usermanager = ContentProvider.maybeAddUserId(context, userhandle.getIdentifier());
            context = contentresolver.insert(usermanager, contentvalues);
            if(!contentvalues.containsKey("subscription_id") || !(TextUtils.isEmpty(contentvalues.getAsString("subscription_id")) ^ true) || !contentvalues.containsKey("subscription_component_name") || !(TextUtils.isEmpty(contentvalues.getAsString("subscription_component_name")) ^ true)) goto _L2; else goto _L1
_L1:
            contentresolver.delete(usermanager, "_id IN (SELECT _id FROM calls WHERE subscription_component_name = ? AND subscription_id = ? ORDER BY date DESC LIMIT -1 OFFSET 500)", new String[] {
                contentvalues.getAsString("subscription_component_name"), contentvalues.getAsString("subscription_id")
            });
_L4:
            return context;
_L2:
            try
            {
                contentresolver.delete(usermanager, "_id IN (SELECT _id FROM calls ORDER BY date DESC LIMIT -1 OFFSET 500)", null);
            }
            // Misplaced declaration of an exception variable
            catch(Context context)
            {
                Log.w("CallLog", "Failed to insert calllog", context);
                return null;
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        private static String getCurrentCountryIso(Context context)
        {
            Object obj = null;
            Object obj1 = (CountryDetector)context.getSystemService("country_detector");
            context = obj;
            if(obj1 != null)
            {
                obj1 = ((CountryDetector) (obj1)).detectCountry();
                context = obj;
                if(obj1 != null)
                    context = ((Country) (obj1)).getCountryIso();
            }
            return context;
        }

        public static String getLastOutgoingCall(Context context)
        {
            Object obj;
            obj = context.getContentResolver();
            context = null;
            obj = ((ContentResolver) (obj)).query(CONTENT_URI, new String[] {
                "number"
            }, "type = 2 OR type = 1001", null, "date DESC LIMIT 1");
            if(obj == null)
                break MISSING_BLOCK_LABEL_48;
            context = ((Context) (obj));
            if(!(((Cursor) (obj)).moveToFirst() ^ true))
                break MISSING_BLOCK_LABEL_61;
            if(obj != null)
                ((Cursor) (obj)).close();
            return "";
            context = ((Context) (obj));
            String s = ((Cursor) (obj)).getString(0);
            if(obj != null)
                ((Cursor) (obj)).close();
            return s;
            Exception exception;
            exception;
            if(context != null)
                context.close();
            throw exception;
        }

        public static boolean shouldHaveSharedCallLogEntries(Context context, UserManager usermanager, int i)
        {
            boolean flag = false;
            if(usermanager.hasUserRestriction("no_outgoing_calls", UserHandle.of(i)))
                return false;
            context = usermanager.getUserInfo(i);
            if(context != null)
                flag = context.isManagedProfile() ^ true;
            return flag;
        }

        private static void updateDataUsageStatForData(ContentResolver contentresolver, String s)
        {
            contentresolver.update(ContactsContract.DataUsageFeedback.FEEDBACK_URI.buildUpon().appendPath(s).appendQueryParameter("type", "call").build(), new ContentValues(), null, null);
        }

        private static void updateNormalizedNumber(Context context, ContentResolver contentresolver, String s, String s1)
        {
            if(TextUtils.isEmpty(s1) || TextUtils.isEmpty(s))
                return;
            if(TextUtils.isEmpty(getCurrentCountryIso(context)))
                return;
            context = PhoneNumberUtils.formatNumberToE164(s1, getCurrentCountryIso(context));
            if(TextUtils.isEmpty(context))
            {
                return;
            } else
            {
                s1 = new ContentValues();
                s1.put("data4", context);
                contentresolver.update(ContactsContract.Data.CONTENT_URI, s1, "_id=?", new String[] {
                    s
                });
                return;
            }
        }

        public static final String ADD_FOR_ALL_USERS = "add_for_all_users";
        public static final String ALLOW_VOICEMAILS_PARAM_KEY = "allow_voicemails";
        public static final int ANSWERED_EXTERNALLY_TYPE = 7;
        public static final int BLOCKED_TYPE = 6;
        public static final String CACHED_FORMATTED_NUMBER = "formatted_number";
        public static final String CACHED_LOOKUP_URI = "lookup_uri";
        public static final String CACHED_MATCHED_NUMBER = "matched_number";
        public static final String CACHED_NAME = "name";
        public static final String CACHED_NORMALIZED_NUMBER = "normalized_number";
        public static final String CACHED_NUMBER_LABEL = "numberlabel";
        public static final String CACHED_NUMBER_TYPE = "numbertype";
        public static final String CACHED_PHOTO_ID = "photo_id";
        public static final String CACHED_PHOTO_URI = "photo_uri";
        public static final Uri CONTENT_FILTER_URI = Uri.parse("content://call_log/calls/filter");
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/calls";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/calls";
        public static final Uri CONTENT_URI;
        public static final Uri CONTENT_URI_WITH_VOICEMAIL;
        public static final String COUNTRY_ISO = "countryiso";
        public static final String DATA_USAGE = "data_usage";
        public static final String DATE = "date";
        public static final String DEFAULT_SORT_ORDER = "date DESC";
        public static final String DURATION = "duration";
        public static final String EXTRA_CALL_TYPE_FILTER = "android.provider.extra.CALL_TYPE_FILTER";
        public static final String FEATURES = "features";
        public static final int FEATURES_HD_CALL = 4;
        public static final int FEATURES_PULLED_EXTERNALLY = 2;
        public static final int FEATURES_VIDEO = 1;
        public static final int FEATURES_WIFI = 8;
        public static final String GEOCODED_LOCATION = "geocoded_location";
        public static final int INCOMING_IMS_TYPE = 1000;
        public static final int INCOMING_TYPE = 1;
        public static final String IS_READ = "is_read";
        public static final String LAST_MODIFIED = "last_modified";
        public static final String LIMIT_PARAM_KEY = "limit";
        private static final int MIN_DURATION_FOR_NORMALIZED_NUMBER_UPDATE_MS = 10000;
        public static final int MISSED_IMS_TYPE = 1002;
        public static final int MISSED_TYPE = 3;
        public static final String NEW = "new";
        public static final String NUMBER = "number";
        public static final String NUMBER_PRESENTATION = "presentation";
        public static final String OFFSET_PARAM_KEY = "offset";
        public static final int OUTGOING_IMS_TYPE = 1001;
        public static final int OUTGOING_TYPE = 2;
        public static final String PHONE_ACCOUNT_ADDRESS = "phone_account_address";
        public static final String PHONE_ACCOUNT_COMPONENT_NAME = "subscription_component_name";
        public static final String PHONE_ACCOUNT_HIDDEN = "phone_account_hidden";
        public static final String PHONE_ACCOUNT_ID = "subscription_id";
        public static final String POST_DIAL_DIGITS = "post_dial_digits";
        public static final int PRESENTATION_ALLOWED = 1;
        public static final int PRESENTATION_PAYPHONE = 4;
        public static final int PRESENTATION_RESTRICTED = 2;
        public static final int PRESENTATION_UNKNOWN = 3;
        public static final int REJECTED_TYPE = 5;
        public static final Uri SHADOW_CONTENT_URI = Uri.parse("content://call_log_shadow/calls");
        public static final String SUB_ID = "sub_id";
        public static final String TRANSCRIPTION = "transcription";
        public static final String TRANSCRIPTION_STATE = "transcription_state";
        public static final String TYPE = "type";
        public static final String VIA_NUMBER = "via_number";
        public static final int VOICEMAIL_TYPE = 4;
        public static final String VOICEMAIL_URI = "voicemail_uri";

        static 
        {
            CONTENT_URI = Uri.parse("content://call_log/calls");
            CONTENT_URI_WITH_VOICEMAIL = CONTENT_URI.buildUpon().appendQueryParameter("allow_voicemails", "true").build();
        }

        public Calls()
        {
        }
    }


    public CallLog()
    {
    }

    public static final String AUTHORITY = "call_log";
    public static final Uri CONTENT_URI = Uri.parse("content://call_log");
    private static final String LOG_TAG = "CallLog";
    public static final String SHADOW_AUTHORITY = "call_log_shadow";
    private static final boolean VERBOSE_LOG = false;

}
