// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.provider;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.*;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.os.RemoteException;
import android.util.SeempLog;

// Referenced classes of package android.provider:
//            BaseColumns

public final class CalendarContract
{
    public static final class Attendees
        implements BaseColumns, AttendeesColumns, EventsColumns
    {

        public static final Cursor query(ContentResolver contentresolver, long l, String as[])
        {
            SeempLog.record(54);
            String s = Long.toString(l);
            return contentresolver.query(CONTENT_URI, as, "event_id=?", new String[] {
                s
            }, null);
        }

        private static final String ATTENDEES_WHERE = "event_id=?";
        public static final Uri CONTENT_URI = Uri.parse("content://com.android.calendar/attendees");


        private Attendees()
        {
        }
    }

    protected static interface AttendeesColumns
    {

        public static final String ATTENDEE_EMAIL = "attendeeEmail";
        public static final String ATTENDEE_IDENTITY = "attendeeIdentity";
        public static final String ATTENDEE_ID_NAMESPACE = "attendeeIdNamespace";
        public static final String ATTENDEE_NAME = "attendeeName";
        public static final String ATTENDEE_RELATIONSHIP = "attendeeRelationship";
        public static final String ATTENDEE_STATUS = "attendeeStatus";
        public static final int ATTENDEE_STATUS_ACCEPTED = 1;
        public static final int ATTENDEE_STATUS_DECLINED = 2;
        public static final int ATTENDEE_STATUS_INVITED = 3;
        public static final int ATTENDEE_STATUS_NONE = 0;
        public static final int ATTENDEE_STATUS_TENTATIVE = 4;
        public static final String ATTENDEE_TYPE = "attendeeType";
        public static final String EVENT_ID = "event_id";
        public static final int RELATIONSHIP_ATTENDEE = 1;
        public static final int RELATIONSHIP_NONE = 0;
        public static final int RELATIONSHIP_ORGANIZER = 2;
        public static final int RELATIONSHIP_PERFORMER = 3;
        public static final int RELATIONSHIP_SPEAKER = 4;
        public static final int TYPE_NONE = 0;
        public static final int TYPE_OPTIONAL = 2;
        public static final int TYPE_REQUIRED = 1;
        public static final int TYPE_RESOURCE = 3;
    }

    public static final class CalendarAlerts
        implements BaseColumns, CalendarAlertsColumns, EventsColumns, CalendarColumns
    {

        public static final boolean alarmExists(ContentResolver contentresolver, long l, long l1, long l2)
        {
            boolean flag;
            boolean flag1;
            SeempLog.record(52);
            Uri uri = CONTENT_URI;
            String s = Long.toString(l);
            String s1 = Long.toString(l1);
            String s2 = Long.toString(l2);
            contentresolver = contentresolver.query(uri, new String[] {
                "alarmTime"
            }, "event_id=? AND begin=? AND alarmTime=?", new String[] {
                s, s1, s2
            }, null);
            flag = false;
            flag1 = flag;
            if(contentresolver == null)
                break MISSING_BLOCK_LABEL_99;
            int i = contentresolver.getCount();
            flag1 = flag;
            if(i > 0)
                flag1 = true;
            if(contentresolver != null)
                contentresolver.close();
            return flag1;
            Exception exception;
            exception;
            if(contentresolver != null)
                contentresolver.close();
            throw exception;
        }

        public static final long findNextAlarmTime(ContentResolver contentresolver, long l)
        {
            SeempLog.record(53);
            (new StringBuilder()).append("alarmTime>=").append(l).toString();
            Uri uri = CONTENT_URI;
            String s = Long.toString(l);
            contentresolver = contentresolver.query(uri, new String[] {
                "alarmTime"
            }, "alarmTime>=?", new String[] {
                s
            }, "alarmTime ASC");
            long l1 = -1L;
            l = l1;
            if(contentresolver == null)
                break MISSING_BLOCK_LABEL_96;
            l = l1;
            if(contentresolver.moveToFirst())
                l = contentresolver.getLong(0);
            if(contentresolver != null)
                contentresolver.close();
            return l;
            Exception exception;
            exception;
            if(contentresolver != null)
                contentresolver.close();
            throw exception;
        }

        public static final Uri insert(ContentResolver contentresolver, long l, long l1, long l2, long l3, int i)
        {
            SeempLog.record(51);
            ContentValues contentvalues = new ContentValues();
            contentvalues.put("event_id", Long.valueOf(l));
            contentvalues.put("begin", Long.valueOf(l1));
            contentvalues.put("end", Long.valueOf(l2));
            contentvalues.put("alarmTime", Long.valueOf(l3));
            contentvalues.put("creationTime", Long.valueOf(System.currentTimeMillis()));
            contentvalues.put("receivedTime", Integer.valueOf(0));
            contentvalues.put("notifyTime", Integer.valueOf(0));
            contentvalues.put("state", Integer.valueOf(0));
            contentvalues.put("minutes", Integer.valueOf(i));
            return contentresolver.insert(CONTENT_URI, contentvalues);
        }

        public static final void rescheduleMissedAlarms(ContentResolver contentresolver, Context context, AlarmManager alarmmanager)
        {
            long l;
            l = System.currentTimeMillis();
            Uri uri = CONTENT_URI;
            String s = Long.toString(l);
            String s1 = Long.toString(l - 0x5265c00L);
            String s2 = Long.toString(l);
            contentresolver = contentresolver.query(uri, new String[] {
                "alarmTime"
            }, "state=0 AND alarmTime<? AND alarmTime>? AND end>=?", new String[] {
                s, s1, s2
            }, "alarmTime ASC");
            if(contentresolver == null)
                return;
            l = -1L;
_L2:
            long l1;
            if(!contentresolver.moveToNext())
                break; /* Loop/switch isn't completed */
            l1 = contentresolver.getLong(0);
            if(l == l1)
                continue; /* Loop/switch isn't completed */
            scheduleAlarm(context, alarmmanager, l1);
            l = l1;
            if(true) goto _L2; else goto _L1
_L1:
            contentresolver.close();
            return;
            context;
            contentresolver.close();
            throw context;
        }

        public static void scheduleAlarm(Context context, AlarmManager alarmmanager, long l)
        {
            AlarmManager alarmmanager1 = alarmmanager;
            if(alarmmanager == null)
                alarmmanager1 = (AlarmManager)context.getSystemService("alarm");
            alarmmanager = new Intent("android.intent.action.EVENT_REMINDER");
            alarmmanager.setData(ContentUris.withAppendedId(CalendarContract.CONTENT_URI, l));
            alarmmanager.putExtra("alarmTime", l);
            alarmmanager.setFlags(0x1000000);
            alarmmanager1.setExactAndAllowWhileIdle(0, l, PendingIntent.getBroadcast(context, 0, alarmmanager, 0));
        }

        public static final Uri CONTENT_URI = Uri.parse("content://com.android.calendar/calendar_alerts");
        public static final Uri CONTENT_URI_BY_INSTANCE = Uri.parse("content://com.android.calendar/calendar_alerts/by_instance");
        private static final boolean DEBUG = false;
        private static final String SORT_ORDER_ALARMTIME_ASC = "alarmTime ASC";
        public static final String TABLE_NAME = "CalendarAlerts";
        private static final String WHERE_ALARM_EXISTS = "event_id=? AND begin=? AND alarmTime=?";
        private static final String WHERE_FINDNEXTALARMTIME = "alarmTime>=?";
        private static final String WHERE_RESCHEDULE_MISSED_ALARMS = "state=0 AND alarmTime<? AND alarmTime>? AND end>=?";


        private CalendarAlerts()
        {
        }
    }

    protected static interface CalendarAlertsColumns
    {

        public static final String ALARM_TIME = "alarmTime";
        public static final String BEGIN = "begin";
        public static final String CREATION_TIME = "creationTime";
        public static final String DEFAULT_SORT_ORDER = "begin ASC,title ASC";
        public static final String END = "end";
        public static final String EVENT_ID = "event_id";
        public static final String MINUTES = "minutes";
        public static final String NOTIFY_TIME = "notifyTime";
        public static final String RECEIVED_TIME = "receivedTime";
        public static final String STATE = "state";
        public static final int STATE_DISMISSED = 2;
        public static final int STATE_FIRED = 1;
        public static final int STATE_SCHEDULED = 0;
    }

    public static final class CalendarCache
        implements CalendarCacheColumns
    {

        public static final String KEY_TIMEZONE_INSTANCES = "timezoneInstances";
        public static final String KEY_TIMEZONE_INSTANCES_PREVIOUS = "timezoneInstancesPrevious";
        public static final String KEY_TIMEZONE_TYPE = "timezoneType";
        public static final String TIMEZONE_TYPE_AUTO = "auto";
        public static final String TIMEZONE_TYPE_HOME = "home";
        public static final Uri URI = Uri.parse("content://com.android.calendar/properties");


        private CalendarCache()
        {
        }
    }

    protected static interface CalendarCacheColumns
    {

        public static final String KEY = "key";
        public static final String VALUE = "value";
    }

    protected static interface CalendarColumns
    {

        public static final String ALLOWED_ATTENDEE_TYPES = "allowedAttendeeTypes";
        public static final String ALLOWED_AVAILABILITY = "allowedAvailability";
        public static final String ALLOWED_REMINDERS = "allowedReminders";
        public static final String CALENDAR_ACCESS_LEVEL = "calendar_access_level";
        public static final String CALENDAR_COLOR = "calendar_color";
        public static final String CALENDAR_COLOR_KEY = "calendar_color_index";
        public static final String CALENDAR_DISPLAY_NAME = "calendar_displayName";
        public static final String CALENDAR_TIME_ZONE = "calendar_timezone";
        public static final int CAL_ACCESS_CONTRIBUTOR = 500;
        public static final int CAL_ACCESS_EDITOR = 600;
        public static final int CAL_ACCESS_FREEBUSY = 100;
        public static final int CAL_ACCESS_NONE = 0;
        public static final int CAL_ACCESS_OVERRIDE = 400;
        public static final int CAL_ACCESS_OWNER = 700;
        public static final int CAL_ACCESS_READ = 200;
        public static final int CAL_ACCESS_RESPOND = 300;
        public static final int CAL_ACCESS_ROOT = 800;
        public static final String CAN_MODIFY_TIME_ZONE = "canModifyTimeZone";
        public static final String CAN_ORGANIZER_RESPOND = "canOrganizerRespond";
        public static final String IS_PRIMARY = "isPrimary";
        public static final String MAX_REMINDERS = "maxReminders";
        public static final String OWNER_ACCOUNT = "ownerAccount";
        public static final String SYNC_EVENTS = "sync_events";
        public static final String VISIBLE = "visible";
    }

    public static final class CalendarEntity
        implements BaseColumns, SyncColumns, CalendarColumns
    {

        public static EntityIterator newEntityIterator(Cursor cursor)
        {
            return new EntityIteratorImpl(cursor);
        }

        public static final Uri CONTENT_URI = Uri.parse("content://com.android.calendar/calendar_entities");


        private CalendarEntity()
        {
        }
    }

    private static class CalendarEntity.EntityIteratorImpl extends CursorEntityIterator
    {

        public Entity getEntityAndIncrementCursor(Cursor cursor)
            throws RemoteException
        {
            long l = cursor.getLong(cursor.getColumnIndexOrThrow("_id"));
            Object obj = new ContentValues();
            ((ContentValues) (obj)).put("_id", Long.valueOf(l));
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "account_name");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "account_type");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "_sync_id");
            DatabaseUtils.cursorLongToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "dirty");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "mutators");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "cal_sync1");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "cal_sync2");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "cal_sync3");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "cal_sync4");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "cal_sync5");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "cal_sync6");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "cal_sync7");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "cal_sync8");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "cal_sync9");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "cal_sync10");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "name");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "calendar_displayName");
            DatabaseUtils.cursorIntToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "calendar_color");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "calendar_color_index");
            DatabaseUtils.cursorIntToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "calendar_access_level");
            DatabaseUtils.cursorIntToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "visible");
            DatabaseUtils.cursorIntToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "sync_events");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "calendar_location");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "calendar_timezone");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "ownerAccount");
            DatabaseUtils.cursorIntToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "canOrganizerRespond");
            DatabaseUtils.cursorIntToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "canModifyTimeZone");
            DatabaseUtils.cursorIntToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "maxReminders");
            DatabaseUtils.cursorIntToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "canPartiallyUpdate");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "allowedReminders");
            DatabaseUtils.cursorIntToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "deleted");
            obj = new Entity(((ContentValues) (obj)));
            cursor.moveToNext();
            return ((Entity) (obj));
        }

        public CalendarEntity.EntityIteratorImpl(Cursor cursor)
        {
            super(cursor);
        }
    }

    public static final class CalendarMetaData
        implements CalendarMetaDataColumns, BaseColumns
    {

        private CalendarMetaData()
        {
        }
    }

    protected static interface CalendarMetaDataColumns
    {

        public static final String LOCAL_TIMEZONE = "localTimezone";
        public static final String MAX_EVENTDAYS = "maxEventDays";
        public static final String MAX_INSTANCE = "maxInstance";
        public static final String MIN_EVENTDAYS = "minEventDays";
        public static final String MIN_INSTANCE = "minInstance";
    }

    protected static interface CalendarSyncColumns
    {

        public static final String CAL_SYNC1 = "cal_sync1";
        public static final String CAL_SYNC10 = "cal_sync10";
        public static final String CAL_SYNC2 = "cal_sync2";
        public static final String CAL_SYNC3 = "cal_sync3";
        public static final String CAL_SYNC4 = "cal_sync4";
        public static final String CAL_SYNC5 = "cal_sync5";
        public static final String CAL_SYNC6 = "cal_sync6";
        public static final String CAL_SYNC7 = "cal_sync7";
        public static final String CAL_SYNC8 = "cal_sync8";
        public static final String CAL_SYNC9 = "cal_sync9";
    }

    public static final class Calendars
        implements BaseColumns, SyncColumns, CalendarColumns
    {

        public static final String CALENDAR_LOCATION = "calendar_location";
        public static final Uri CONTENT_URI = Uri.parse("content://com.android.calendar/calendars");
        public static final String DEFAULT_SORT_ORDER = "calendar_displayName";
        public static final String NAME = "name";
        public static final String SYNC_WRITABLE_COLUMNS[] = {
            "account_name", "account_type", "_sync_id", "dirty", "mutators", "ownerAccount", "maxReminders", "allowedReminders", "canModifyTimeZone", "canOrganizerRespond", 
            "canPartiallyUpdate", "calendar_location", "calendar_timezone", "calendar_access_level", "deleted", "cal_sync1", "cal_sync2", "cal_sync3", "cal_sync4", "cal_sync5", 
            "cal_sync6", "cal_sync7", "cal_sync8", "cal_sync9", "cal_sync10"
        };


        private Calendars()
        {
        }
    }

    public static final class Colors
        implements ColorsColumns
    {

        public static final Uri CONTENT_URI = Uri.parse("content://com.android.calendar/colors");
        public static final String TABLE_NAME = "Colors";


        private Colors()
        {
        }
    }

    protected static interface ColorsColumns
        extends SyncStateContract.Columns
    {

        public static final String COLOR = "color";
        public static final String COLOR_KEY = "color_index";
        public static final String COLOR_TYPE = "color_type";
        public static final int TYPE_CALENDAR = 0;
        public static final int TYPE_EVENT = 1;
    }

    public static final class EventDays
        implements EventDaysColumns
    {

        public static final Cursor query(ContentResolver contentresolver, int i, int j, String as[])
        {
            SeempLog.record(54);
            if(j < 1)
            {
                return null;
            } else
            {
                android.net.Uri.Builder builder = CONTENT_URI.buildUpon();
                ContentUris.appendId(builder, i);
                ContentUris.appendId(builder, (i + j) - 1);
                return contentresolver.query(builder.build(), as, "selected=1", null, "startDay");
            }
        }

        public static final Uri CONTENT_URI = Uri.parse("content://com.android.calendar/instances/groupbyday");
        private static final String SELECTION = "selected=1";


        private EventDays()
        {
        }
    }

    protected static interface EventDaysColumns
    {

        public static final String ENDDAY = "endDay";
        public static final String STARTDAY = "startDay";
    }

    public static final class Events
        implements BaseColumns, SyncColumns, EventsColumns, CalendarColumns
    {

        public static final Uri CONTENT_EXCEPTION_URI = Uri.parse("content://com.android.calendar/exception");
        public static final Uri CONTENT_URI = Uri.parse("content://com.android.calendar/events");
        private static final String DEFAULT_SORT_ORDER = "";
        public static String PROVIDER_WRITABLE_COLUMNS[] = {
            "account_name", "account_type", "cal_sync1", "cal_sync2", "cal_sync3", "cal_sync4", "cal_sync5", "cal_sync6", "cal_sync7", "cal_sync8", 
            "cal_sync9", "cal_sync10", "allowedReminders", "allowedAttendeeTypes", "allowedAvailability", "calendar_access_level", "calendar_color", "calendar_timezone", "canModifyTimeZone", "canOrganizerRespond", 
            "calendar_displayName", "canPartiallyUpdate", "sync_events", "visible"
        };
        public static final String SYNC_WRITABLE_COLUMNS[] = {
            "_sync_id", "dirty", "mutators", "sync_data1", "sync_data2", "sync_data3", "sync_data4", "sync_data5", "sync_data6", "sync_data7", 
            "sync_data8", "sync_data9", "sync_data10"
        };


        private Events()
        {
        }
    }

    protected static interface EventsColumns
    {

        public static final int ACCESS_CONFIDENTIAL = 1;
        public static final int ACCESS_DEFAULT = 0;
        public static final String ACCESS_LEVEL = "accessLevel";
        public static final int ACCESS_PRIVATE = 2;
        public static final int ACCESS_PUBLIC = 3;
        public static final String ALL_DAY = "allDay";
        public static final String AVAILABILITY = "availability";
        public static final int AVAILABILITY_BUSY = 0;
        public static final int AVAILABILITY_FREE = 1;
        public static final int AVAILABILITY_TENTATIVE = 2;
        public static final String CALENDAR_ID = "calendar_id";
        public static final String CAN_INVITE_OTHERS = "canInviteOthers";
        public static final String CUSTOM_APP_PACKAGE = "customAppPackage";
        public static final String CUSTOM_APP_URI = "customAppUri";
        public static final String DESCRIPTION = "description";
        public static final String DISPLAY_COLOR = "displayColor";
        public static final String DTEND = "dtend";
        public static final String DTSTART = "dtstart";
        public static final String DURATION = "duration";
        public static final String EVENT_COLOR = "eventColor";
        public static final String EVENT_COLOR_KEY = "eventColor_index";
        public static final String EVENT_END_TIMEZONE = "eventEndTimezone";
        public static final String EVENT_LOCATION = "eventLocation";
        public static final String EVENT_TIMEZONE = "eventTimezone";
        public static final String EXDATE = "exdate";
        public static final String EXRULE = "exrule";
        public static final String GUESTS_CAN_INVITE_OTHERS = "guestsCanInviteOthers";
        public static final String GUESTS_CAN_MODIFY = "guestsCanModify";
        public static final String GUESTS_CAN_SEE_GUESTS = "guestsCanSeeGuests";
        public static final String HAS_ALARM = "hasAlarm";
        public static final String HAS_ATTENDEE_DATA = "hasAttendeeData";
        public static final String HAS_EXTENDED_PROPERTIES = "hasExtendedProperties";
        public static final String IS_ORGANIZER = "isOrganizer";
        public static final String LAST_DATE = "lastDate";
        public static final String LAST_SYNCED = "lastSynced";
        public static final String ORGANIZER = "organizer";
        public static final String ORIGINAL_ALL_DAY = "originalAllDay";
        public static final String ORIGINAL_ID = "original_id";
        public static final String ORIGINAL_INSTANCE_TIME = "originalInstanceTime";
        public static final String ORIGINAL_SYNC_ID = "original_sync_id";
        public static final String RDATE = "rdate";
        public static final String RRULE = "rrule";
        public static final String SELF_ATTENDEE_STATUS = "selfAttendeeStatus";
        public static final String STATUS = "eventStatus";
        public static final int STATUS_CANCELED = 2;
        public static final int STATUS_CONFIRMED = 1;
        public static final int STATUS_TENTATIVE = 0;
        public static final String SYNC_DATA1 = "sync_data1";
        public static final String SYNC_DATA10 = "sync_data10";
        public static final String SYNC_DATA2 = "sync_data2";
        public static final String SYNC_DATA3 = "sync_data3";
        public static final String SYNC_DATA4 = "sync_data4";
        public static final String SYNC_DATA5 = "sync_data5";
        public static final String SYNC_DATA6 = "sync_data6";
        public static final String SYNC_DATA7 = "sync_data7";
        public static final String SYNC_DATA8 = "sync_data8";
        public static final String SYNC_DATA9 = "sync_data9";
        public static final String TITLE = "title";
        public static final String UID_2445 = "uid2445";
    }

    public static final class EventsEntity
        implements BaseColumns, SyncColumns, EventsColumns
    {

        public static EntityIterator newEntityIterator(Cursor cursor, ContentProviderClient contentproviderclient)
        {
            return new EntityIteratorImpl(cursor, contentproviderclient);
        }

        public static EntityIterator newEntityIterator(Cursor cursor, ContentResolver contentresolver)
        {
            return new EntityIteratorImpl(cursor, contentresolver);
        }

        public static final Uri CONTENT_URI = Uri.parse("content://com.android.calendar/event_entities");


        private EventsEntity()
        {
        }
    }

    private static class EventsEntity.EntityIteratorImpl extends CursorEntityIterator
    {

        public Entity getEntityAndIncrementCursor(Cursor cursor)
            throws RemoteException
        {
            long l;
            Object obj;
            Entity entity;
            l = cursor.getLong(cursor.getColumnIndexOrThrow("_id"));
            obj = new ContentValues();
            ((ContentValues) (obj)).put("_id", Long.valueOf(l));
            DatabaseUtils.cursorIntToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "calendar_id");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "title");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "description");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "eventLocation");
            DatabaseUtils.cursorIntToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "eventStatus");
            DatabaseUtils.cursorIntToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "selfAttendeeStatus");
            DatabaseUtils.cursorLongToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "dtstart");
            DatabaseUtils.cursorLongToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "dtend");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "duration");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "eventTimezone");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "eventEndTimezone");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "allDay");
            DatabaseUtils.cursorIntToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "accessLevel");
            DatabaseUtils.cursorIntToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "availability");
            DatabaseUtils.cursorIntToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "eventColor");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "eventColor_index");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "hasAlarm");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "hasExtendedProperties");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "rrule");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "rdate");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "exrule");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "exdate");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "original_sync_id");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "original_id");
            DatabaseUtils.cursorLongToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "originalInstanceTime");
            DatabaseUtils.cursorIntToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "originalAllDay");
            DatabaseUtils.cursorLongToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "lastDate");
            DatabaseUtils.cursorIntToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "hasAttendeeData");
            DatabaseUtils.cursorIntToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "guestsCanInviteOthers");
            DatabaseUtils.cursorIntToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "guestsCanModify");
            DatabaseUtils.cursorIntToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "guestsCanSeeGuests");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "customAppPackage");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "customAppUri");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "uid2445");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "organizer");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "isOrganizer");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "_sync_id");
            DatabaseUtils.cursorLongToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "dirty");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "mutators");
            DatabaseUtils.cursorLongToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "lastSynced");
            DatabaseUtils.cursorIntToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "deleted");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "sync_data1");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "sync_data2");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "sync_data3");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "sync_data4");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "sync_data5");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "sync_data6");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "sync_data7");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "sync_data8");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "sync_data9");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "sync_data10");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "cal_sync1");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "cal_sync2");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "cal_sync3");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "cal_sync4");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "cal_sync5");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "cal_sync6");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "cal_sync7");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "cal_sync8");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "cal_sync9");
            DatabaseUtils.cursorStringToContentValuesIfPresent(cursor, ((ContentValues) (obj)), "cal_sync10");
            entity = new Entity(((ContentValues) (obj)));
            ContentValues contentvalues;
            if(mResolver != null)
                obj = mResolver.query(Reminders.CONTENT_URI, REMINDERS_PROJECTION, "event_id=?", new String[] {
                    Long.toString(l)
                }, null);
            else
                obj = mProvider.query(Reminders.CONTENT_URI, REMINDERS_PROJECTION, "event_id=?", new String[] {
                    Long.toString(l)
                }, null);
            for(; ((Cursor) (obj)).moveToNext(); entity.addSubValue(Reminders.CONTENT_URI, contentvalues))
            {
                contentvalues = JVM INSTR new #103 <Class ContentValues>;
                contentvalues.ContentValues();
                contentvalues.put("minutes", Integer.valueOf(((Cursor) (obj)).getInt(0)));
                contentvalues.put("method", Integer.valueOf(((Cursor) (obj)).getInt(1)));
            }

            break MISSING_BLOCK_LABEL_681;
            cursor;
            ((Cursor) (obj)).close();
            throw cursor;
            ((Cursor) (obj)).close();
            ContentValues contentvalues1;
            if(mResolver != null)
                obj = mResolver.query(Attendees.CONTENT_URI, ATTENDEES_PROJECTION, "event_id=?", new String[] {
                    Long.toString(l)
                }, null);
            else
                obj = mProvider.query(Attendees.CONTENT_URI, ATTENDEES_PROJECTION, "event_id=?", new String[] {
                    Long.toString(l)
                }, null);
            for(; ((Cursor) (obj)).moveToNext(); entity.addSubValue(Attendees.CONTENT_URI, contentvalues1))
            {
                contentvalues1 = JVM INSTR new #103 <Class ContentValues>;
                contentvalues1.ContentValues();
                contentvalues1.put("attendeeName", ((Cursor) (obj)).getString(0));
                contentvalues1.put("attendeeEmail", ((Cursor) (obj)).getString(1));
                contentvalues1.put("attendeeRelationship", Integer.valueOf(((Cursor) (obj)).getInt(2)));
                contentvalues1.put("attendeeType", Integer.valueOf(((Cursor) (obj)).getInt(3)));
                contentvalues1.put("attendeeStatus", Integer.valueOf(((Cursor) (obj)).getInt(4)));
                contentvalues1.put("attendeeIdentity", ((Cursor) (obj)).getString(5));
                contentvalues1.put("attendeeIdNamespace", ((Cursor) (obj)).getString(6));
            }

            break MISSING_BLOCK_LABEL_914;
            cursor;
            ((Cursor) (obj)).close();
            throw cursor;
            ((Cursor) (obj)).close();
            ContentValues contentvalues2;
            if(mResolver != null)
                obj = mResolver.query(ExtendedProperties.CONTENT_URI, EXTENDED_PROJECTION, "event_id=?", new String[] {
                    Long.toString(l)
                }, null);
            else
                obj = mProvider.query(ExtendedProperties.CONTENT_URI, EXTENDED_PROJECTION, "event_id=?", new String[] {
                    Long.toString(l)
                }, null);
            for(; ((Cursor) (obj)).moveToNext(); entity.addSubValue(ExtendedProperties.CONTENT_URI, contentvalues2))
            {
                contentvalues2 = JVM INSTR new #103 <Class ContentValues>;
                contentvalues2.ContentValues();
                contentvalues2.put("_id", ((Cursor) (obj)).getString(0));
                contentvalues2.put("name", ((Cursor) (obj)).getString(1));
                contentvalues2.put("value", ((Cursor) (obj)).getString(2));
            }

            break MISSING_BLOCK_LABEL_1077;
            cursor;
            ((Cursor) (obj)).close();
            throw cursor;
            ((Cursor) (obj)).close();
            cursor.moveToNext();
            return entity;
        }

        private static final String ATTENDEES_PROJECTION[] = {
            "attendeeName", "attendeeEmail", "attendeeRelationship", "attendeeType", "attendeeStatus", "attendeeIdentity", "attendeeIdNamespace"
        };
        private static final int COLUMN_ATTENDEE_EMAIL = 1;
        private static final int COLUMN_ATTENDEE_IDENTITY = 5;
        private static final int COLUMN_ATTENDEE_ID_NAMESPACE = 6;
        private static final int COLUMN_ATTENDEE_NAME = 0;
        private static final int COLUMN_ATTENDEE_RELATIONSHIP = 2;
        private static final int COLUMN_ATTENDEE_STATUS = 4;
        private static final int COLUMN_ATTENDEE_TYPE = 3;
        private static final int COLUMN_ID = 0;
        private static final int COLUMN_METHOD = 1;
        private static final int COLUMN_MINUTES = 0;
        private static final int COLUMN_NAME = 1;
        private static final int COLUMN_VALUE = 2;
        private static final String EXTENDED_PROJECTION[] = {
            "_id", "name", "value"
        };
        private static final String REMINDERS_PROJECTION[] = {
            "minutes", "method"
        };
        private static final String WHERE_EVENT_ID = "event_id=?";
        private final ContentProviderClient mProvider;
        private final ContentResolver mResolver;


        public EventsEntity.EntityIteratorImpl(Cursor cursor, ContentProviderClient contentproviderclient)
        {
            super(cursor);
            mResolver = null;
            mProvider = contentproviderclient;
        }

        public EventsEntity.EntityIteratorImpl(Cursor cursor, ContentResolver contentresolver)
        {
            super(cursor);
            mResolver = contentresolver;
            mProvider = null;
        }
    }

    public static final class EventsRawTimes
        implements BaseColumns, EventsRawTimesColumns
    {

        private EventsRawTimes()
        {
        }
    }

    protected static interface EventsRawTimesColumns
    {

        public static final String DTEND_2445 = "dtend2445";
        public static final String DTSTART_2445 = "dtstart2445";
        public static final String EVENT_ID = "event_id";
        public static final String LAST_DATE_2445 = "lastDate2445";
        public static final String ORIGINAL_INSTANCE_TIME_2445 = "originalInstanceTime2445";
    }

    public static final class ExtendedProperties
        implements BaseColumns, ExtendedPropertiesColumns, EventsColumns
    {

        public static final Uri CONTENT_URI = Uri.parse("content://com.android.calendar/extendedproperties");


        private ExtendedProperties()
        {
        }
    }

    protected static interface ExtendedPropertiesColumns
    {

        public static final String EVENT_ID = "event_id";
        public static final String NAME = "name";
        public static final String VALUE = "value";
    }

    public static final class Instances
        implements BaseColumns, EventsColumns, CalendarColumns
    {

        public static final Cursor query(ContentResolver contentresolver, String as[], long l, long l1)
        {
            SeempLog.record(54);
            android.net.Uri.Builder builder = CONTENT_URI.buildUpon();
            ContentUris.appendId(builder, l);
            ContentUris.appendId(builder, l1);
            return contentresolver.query(builder.build(), as, "visible=?", WHERE_CALENDARS_ARGS, "begin ASC");
        }

        public static final Cursor query(ContentResolver contentresolver, String as[], long l, long l1, String s)
        {
            SeempLog.record(54);
            android.net.Uri.Builder builder = CONTENT_SEARCH_URI.buildUpon();
            ContentUris.appendId(builder, l);
            ContentUris.appendId(builder, l1);
            return contentresolver.query(builder.appendPath(s).build(), as, "visible=?", WHERE_CALENDARS_ARGS, "begin ASC");
        }

        public static final String BEGIN = "begin";
        public static final Uri CONTENT_BY_DAY_URI = Uri.parse("content://com.android.calendar/instances/whenbyday");
        public static final Uri CONTENT_SEARCH_BY_DAY_URI = Uri.parse("content://com.android.calendar/instances/searchbyday");
        public static final Uri CONTENT_SEARCH_URI = Uri.parse("content://com.android.calendar/instances/search");
        public static final Uri CONTENT_URI = Uri.parse("content://com.android.calendar/instances/when");
        private static final String DEFAULT_SORT_ORDER = "begin ASC";
        public static final String END = "end";
        public static final String END_DAY = "endDay";
        public static final String END_MINUTE = "endMinute";
        public static final String EVENT_ID = "event_id";
        public static final String START_DAY = "startDay";
        public static final String START_MINUTE = "startMinute";
        private static final String WHERE_CALENDARS_ARGS[] = {
            "1"
        };
        private static final String WHERE_CALENDARS_SELECTED = "visible=?";


        private Instances()
        {
        }
    }

    public static final class Reminders
        implements BaseColumns, RemindersColumns, EventsColumns
    {

        public static final Cursor query(ContentResolver contentresolver, long l, String as[])
        {
            SeempLog.record(54);
            String s = Long.toString(l);
            return contentresolver.query(CONTENT_URI, as, "event_id=?", new String[] {
                s
            }, null);
        }

        public static final Uri CONTENT_URI = Uri.parse("content://com.android.calendar/reminders");
        private static final String REMINDERS_WHERE = "event_id=?";


        private Reminders()
        {
        }
    }

    protected static interface RemindersColumns
    {

        public static final String EVENT_ID = "event_id";
        public static final String METHOD = "method";
        public static final int METHOD_ALARM = 4;
        public static final int METHOD_ALERT = 1;
        public static final int METHOD_DEFAULT = 0;
        public static final int METHOD_EMAIL = 2;
        public static final int METHOD_SMS = 3;
        public static final String MINUTES = "minutes";
        public static final int MINUTES_DEFAULT = -1;
    }

    protected static interface SyncColumns
        extends CalendarSyncColumns
    {

        public static final String ACCOUNT_NAME = "account_name";
        public static final String ACCOUNT_TYPE = "account_type";
        public static final String CAN_PARTIALLY_UPDATE = "canPartiallyUpdate";
        public static final String DELETED = "deleted";
        public static final String DIRTY = "dirty";
        public static final String MUTATORS = "mutators";
        public static final String _SYNC_ID = "_sync_id";
    }

    public static final class SyncState
        implements SyncStateContract.Columns
    {

        private static final String CONTENT_DIRECTORY = "syncstate";
        public static final Uri CONTENT_URI;

        static 
        {
            CONTENT_URI = Uri.withAppendedPath(CalendarContract.CONTENT_URI, "syncstate");
        }

        private SyncState()
        {
        }
    }


    private CalendarContract()
    {
    }

    public static final String ACCOUNT_TYPE_LOCAL = "LOCAL";
    public static final String ACTION_EVENT_REMINDER = "android.intent.action.EVENT_REMINDER";
    public static final String ACTION_HANDLE_CUSTOM_EVENT = "android.provider.calendar.action.HANDLE_CUSTOM_EVENT";
    public static final String AUTHORITY = "com.android.calendar";
    public static final String CALLER_IS_SYNCADAPTER = "caller_is_syncadapter";
    public static final Uri CONTENT_URI = Uri.parse("content://com.android.calendar");
    public static final String EXTRA_CUSTOM_APP_URI = "customAppUri";
    public static final String EXTRA_EVENT_ALL_DAY = "allDay";
    public static final String EXTRA_EVENT_BEGIN_TIME = "beginTime";
    public static final String EXTRA_EVENT_END_TIME = "endTime";
    private static final String TAG = "Calendar";

}
