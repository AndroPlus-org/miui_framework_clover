// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.tv;

import android.content.*;
import android.net.Uri;
import android.provider.BaseColumns;
import android.text.TextUtils;
import android.util.ArraySet;
import java.util.*;

// Referenced classes of package android.media.tv:
//            TvInputManager

public final class TvContract
{
    public static interface BaseTvColumns
        extends BaseColumns
    {

        public static final String COLUMN_PACKAGE_NAME = "package_name";
    }

    public static final class Channels
        implements BaseTvColumns
    {

        public static final String getVideoResolution(String s)
        {
            return (String)VIDEO_FORMAT_TO_RESOLUTION_MAP.get(s);
        }

        public static final String COLUMN_APP_LINK_COLOR = "app_link_color";
        public static final String COLUMN_APP_LINK_ICON_URI = "app_link_icon_uri";
        public static final String COLUMN_APP_LINK_INTENT_URI = "app_link_intent_uri";
        public static final String COLUMN_APP_LINK_POSTER_ART_URI = "app_link_poster_art_uri";
        public static final String COLUMN_APP_LINK_TEXT = "app_link_text";
        public static final String COLUMN_BROWSABLE = "browsable";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_DISPLAY_NAME = "display_name";
        public static final String COLUMN_DISPLAY_NUMBER = "display_number";
        public static final String COLUMN_INPUT_ID = "input_id";
        public static final String COLUMN_INTERNAL_PROVIDER_DATA = "internal_provider_data";
        public static final String COLUMN_INTERNAL_PROVIDER_FLAG1 = "internal_provider_flag1";
        public static final String COLUMN_INTERNAL_PROVIDER_FLAG2 = "internal_provider_flag2";
        public static final String COLUMN_INTERNAL_PROVIDER_FLAG3 = "internal_provider_flag3";
        public static final String COLUMN_INTERNAL_PROVIDER_FLAG4 = "internal_provider_flag4";
        public static final String COLUMN_INTERNAL_PROVIDER_ID = "internal_provider_id";
        public static final String COLUMN_LOCKED = "locked";
        public static final String COLUMN_NETWORK_AFFILIATION = "network_affiliation";
        public static final String COLUMN_ORIGINAL_NETWORK_ID = "original_network_id";
        public static final String COLUMN_SEARCHABLE = "searchable";
        public static final String COLUMN_SERVICE_ID = "service_id";
        public static final String COLUMN_SERVICE_TYPE = "service_type";
        public static final String COLUMN_TRANSIENT = "transient";
        public static final String COLUMN_TRANSPORT_STREAM_ID = "transport_stream_id";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_VERSION_NUMBER = "version_number";
        public static final String COLUMN_VIDEO_FORMAT = "video_format";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/channel";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/channel";
        public static final Uri CONTENT_URI = Uri.parse("content://android.media.tv/channel");
        public static final String SERVICE_TYPE_AUDIO = "SERVICE_TYPE_AUDIO";
        public static final String SERVICE_TYPE_AUDIO_VIDEO = "SERVICE_TYPE_AUDIO_VIDEO";
        public static final String SERVICE_TYPE_OTHER = "SERVICE_TYPE_OTHER";
        public static final String TYPE_1SEG = "TYPE_1SEG";
        public static final String TYPE_ATSC_C = "TYPE_ATSC_C";
        public static final String TYPE_ATSC_M_H = "TYPE_ATSC_M_H";
        public static final String TYPE_ATSC_T = "TYPE_ATSC_T";
        public static final String TYPE_CMMB = "TYPE_CMMB";
        public static final String TYPE_DTMB = "TYPE_DTMB";
        public static final String TYPE_DVB_C = "TYPE_DVB_C";
        public static final String TYPE_DVB_C2 = "TYPE_DVB_C2";
        public static final String TYPE_DVB_H = "TYPE_DVB_H";
        public static final String TYPE_DVB_S = "TYPE_DVB_S";
        public static final String TYPE_DVB_S2 = "TYPE_DVB_S2";
        public static final String TYPE_DVB_SH = "TYPE_DVB_SH";
        public static final String TYPE_DVB_T = "TYPE_DVB_T";
        public static final String TYPE_DVB_T2 = "TYPE_DVB_T2";
        public static final String TYPE_ISDB_C = "TYPE_ISDB_C";
        public static final String TYPE_ISDB_S = "TYPE_ISDB_S";
        public static final String TYPE_ISDB_T = "TYPE_ISDB_T";
        public static final String TYPE_ISDB_TB = "TYPE_ISDB_TB";
        public static final String TYPE_NTSC = "TYPE_NTSC";
        public static final String TYPE_OTHER = "TYPE_OTHER";
        public static final String TYPE_PAL = "TYPE_PAL";
        public static final String TYPE_PREVIEW = "TYPE_PREVIEW";
        public static final String TYPE_SECAM = "TYPE_SECAM";
        public static final String TYPE_S_DMB = "TYPE_S_DMB";
        public static final String TYPE_T_DMB = "TYPE_T_DMB";
        public static final String VIDEO_FORMAT_1080I = "VIDEO_FORMAT_1080I";
        public static final String VIDEO_FORMAT_1080P = "VIDEO_FORMAT_1080P";
        public static final String VIDEO_FORMAT_2160P = "VIDEO_FORMAT_2160P";
        public static final String VIDEO_FORMAT_240P = "VIDEO_FORMAT_240P";
        public static final String VIDEO_FORMAT_360P = "VIDEO_FORMAT_360P";
        public static final String VIDEO_FORMAT_4320P = "VIDEO_FORMAT_4320P";
        public static final String VIDEO_FORMAT_480I = "VIDEO_FORMAT_480I";
        public static final String VIDEO_FORMAT_480P = "VIDEO_FORMAT_480P";
        public static final String VIDEO_FORMAT_576I = "VIDEO_FORMAT_576I";
        public static final String VIDEO_FORMAT_576P = "VIDEO_FORMAT_576P";
        public static final String VIDEO_FORMAT_720P = "VIDEO_FORMAT_720P";
        private static final Map VIDEO_FORMAT_TO_RESOLUTION_MAP;
        public static final String VIDEO_RESOLUTION_ED = "VIDEO_RESOLUTION_ED";
        public static final String VIDEO_RESOLUTION_FHD = "VIDEO_RESOLUTION_FHD";
        public static final String VIDEO_RESOLUTION_HD = "VIDEO_RESOLUTION_HD";
        public static final String VIDEO_RESOLUTION_SD = "VIDEO_RESOLUTION_SD";
        public static final String VIDEO_RESOLUTION_UHD = "VIDEO_RESOLUTION_UHD";

        static 
        {
            VIDEO_FORMAT_TO_RESOLUTION_MAP = new HashMap();
            VIDEO_FORMAT_TO_RESOLUTION_MAP.put("VIDEO_FORMAT_480I", "VIDEO_RESOLUTION_SD");
            VIDEO_FORMAT_TO_RESOLUTION_MAP.put("VIDEO_FORMAT_480P", "VIDEO_RESOLUTION_ED");
            VIDEO_FORMAT_TO_RESOLUTION_MAP.put("VIDEO_FORMAT_576I", "VIDEO_RESOLUTION_SD");
            VIDEO_FORMAT_TO_RESOLUTION_MAP.put("VIDEO_FORMAT_576P", "VIDEO_RESOLUTION_ED");
            VIDEO_FORMAT_TO_RESOLUTION_MAP.put("VIDEO_FORMAT_720P", "VIDEO_RESOLUTION_HD");
            VIDEO_FORMAT_TO_RESOLUTION_MAP.put("VIDEO_FORMAT_1080I", "VIDEO_RESOLUTION_HD");
            VIDEO_FORMAT_TO_RESOLUTION_MAP.put("VIDEO_FORMAT_1080P", "VIDEO_RESOLUTION_FHD");
            VIDEO_FORMAT_TO_RESOLUTION_MAP.put("VIDEO_FORMAT_2160P", "VIDEO_RESOLUTION_UHD");
            VIDEO_FORMAT_TO_RESOLUTION_MAP.put("VIDEO_FORMAT_4320P", "VIDEO_RESOLUTION_UHD");
        }

        private Channels()
        {
        }
    }

    public static final class Channels.Logo
    {

        public static final String CONTENT_DIRECTORY = "logo";

        private Channels.Logo()
        {
        }
    }

    static interface PreviewProgramColumns
    {

        public static final int ASPECT_RATIO_16_9 = 0;
        public static final int ASPECT_RATIO_1_1 = 3;
        public static final int ASPECT_RATIO_2_3 = 4;
        public static final int ASPECT_RATIO_3_2 = 1;
        public static final int ASPECT_RATIO_4_3 = 2;
        public static final int AVAILABILITY_AVAILABLE = 0;
        public static final int AVAILABILITY_FREE_WITH_SUBSCRIPTION = 1;
        public static final int AVAILABILITY_PAID_CONTENT = 2;
        public static final String COLUMN_AUTHOR = "author";
        public static final String COLUMN_AVAILABILITY = "availability";
        public static final String COLUMN_BROWSABLE = "browsable";
        public static final String COLUMN_CONTENT_ID = "content_id";
        public static final String COLUMN_DURATION_MILLIS = "duration_millis";
        public static final String COLUMN_INTENT_URI = "intent_uri";
        public static final String COLUMN_INTERACTION_COUNT = "interaction_count";
        public static final String COLUMN_INTERACTION_TYPE = "interaction_type";
        public static final String COLUMN_INTERNAL_PROVIDER_ID = "internal_provider_id";
        public static final String COLUMN_ITEM_COUNT = "item_count";
        public static final String COLUMN_LAST_PLAYBACK_POSITION_MILLIS = "last_playback_position_millis";
        public static final String COLUMN_LIVE = "live";
        public static final String COLUMN_LOGO_URI = "logo_uri";
        public static final String COLUMN_OFFER_PRICE = "offer_price";
        public static final String COLUMN_POSTER_ART_ASPECT_RATIO = "poster_art_aspect_ratio";
        public static final String COLUMN_PREVIEW_VIDEO_URI = "preview_video_uri";
        public static final String COLUMN_RELEASE_DATE = "release_date";
        public static final String COLUMN_STARTING_PRICE = "starting_price";
        public static final String COLUMN_THUMBNAIL_ASPECT_RATIO = "poster_thumbnail_aspect_ratio";
        public static final String COLUMN_TRANSIENT = "transient";
        public static final String COLUMN_TYPE = "type";
        public static final int INTERACTION_TYPE_FANS = 3;
        public static final int INTERACTION_TYPE_FOLLOWERS = 2;
        public static final int INTERACTION_TYPE_LIKES = 4;
        public static final int INTERACTION_TYPE_LISTENS = 1;
        public static final int INTERACTION_TYPE_THUMBS = 5;
        public static final int INTERACTION_TYPE_VIEWERS = 6;
        public static final int INTERACTION_TYPE_VIEWS = 0;
        public static final int TYPE_ALBUM = 8;
        public static final int TYPE_ARTIST = 9;
        public static final int TYPE_CHANNEL = 6;
        public static final int TYPE_CLIP = 4;
        public static final int TYPE_EVENT = 5;
        public static final int TYPE_MOVIE = 0;
        public static final int TYPE_PLAYLIST = 10;
        public static final int TYPE_STATION = 11;
        public static final int TYPE_TRACK = 7;
        public static final int TYPE_TV_EPISODE = 3;
        public static final int TYPE_TV_SEASON = 2;
        public static final int TYPE_TV_SERIES = 1;
    }

    public static final class PreviewPrograms
        implements BaseTvColumns, ProgramColumns, PreviewProgramColumns
    {

        public static final String COLUMN_CHANNEL_ID = "channel_id";
        public static final String COLUMN_WEIGHT = "weight";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/preview_program";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/preview_program";
        public static final Uri CONTENT_URI = Uri.parse("content://android.media.tv/preview_program");


        private PreviewPrograms()
        {
        }
    }

    static interface ProgramColumns
    {

        public static final String COLUMN_AUDIO_LANGUAGE = "audio_language";
        public static final String COLUMN_CANONICAL_GENRE = "canonical_genre";
        public static final String COLUMN_CONTENT_RATING = "content_rating";
        public static final String COLUMN_EPISODE_DISPLAY_NUMBER = "episode_display_number";
        public static final String COLUMN_EPISODE_TITLE = "episode_title";
        public static final String COLUMN_INTERNAL_PROVIDER_DATA = "internal_provider_data";
        public static final String COLUMN_INTERNAL_PROVIDER_FLAG1 = "internal_provider_flag1";
        public static final String COLUMN_INTERNAL_PROVIDER_FLAG2 = "internal_provider_flag2";
        public static final String COLUMN_INTERNAL_PROVIDER_FLAG3 = "internal_provider_flag3";
        public static final String COLUMN_INTERNAL_PROVIDER_FLAG4 = "internal_provider_flag4";
        public static final String COLUMN_LONG_DESCRIPTION = "long_description";
        public static final String COLUMN_POSTER_ART_URI = "poster_art_uri";
        public static final String COLUMN_REVIEW_RATING = "review_rating";
        public static final String COLUMN_REVIEW_RATING_STYLE = "review_rating_style";
        public static final String COLUMN_SEARCHABLE = "searchable";
        public static final String COLUMN_SEASON_DISPLAY_NUMBER = "season_display_number";
        public static final String COLUMN_SEASON_TITLE = "season_title";
        public static final String COLUMN_SHORT_DESCRIPTION = "short_description";
        public static final String COLUMN_THUMBNAIL_URI = "thumbnail_uri";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_VERSION_NUMBER = "version_number";
        public static final String COLUMN_VIDEO_HEIGHT = "video_height";
        public static final String COLUMN_VIDEO_WIDTH = "video_width";
        public static final int REVIEW_RATING_STYLE_PERCENTAGE = 2;
        public static final int REVIEW_RATING_STYLE_STARS = 0;
        public static final int REVIEW_RATING_STYLE_THUMBS_UP_DOWN = 1;
    }

    public static final class Programs
        implements BaseTvColumns, ProgramColumns
    {

        public static final String COLUMN_BROADCAST_GENRE = "broadcast_genre";
        public static final String COLUMN_CHANNEL_ID = "channel_id";
        public static final String COLUMN_END_TIME_UTC_MILLIS = "end_time_utc_millis";
        public static final String COLUMN_EPISODE_NUMBER = "episode_number";
        public static final String COLUMN_RECORDING_PROHIBITED = "recording_prohibited";
        public static final String COLUMN_SEASON_NUMBER = "season_number";
        public static final String COLUMN_START_TIME_UTC_MILLIS = "start_time_utc_millis";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/program";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/program";
        public static final Uri CONTENT_URI = Uri.parse("content://android.media.tv/program");


        private Programs()
        {
        }
    }

    public static final class Programs.Genres
    {

        public static String[] decode(String s)
        {
            Object obj;
            ArrayList arraylist;
            int i;
            boolean flag;
            int j;
            if(TextUtils.isEmpty(s))
                return EMPTY_STRING_ARRAY;
            if(s.indexOf(',') == -1 && s.indexOf('"') == -1)
                return (new String[] {
                    s.trim()
                });
            obj = new StringBuilder();
            arraylist = new ArrayList();
            i = s.length();
            flag = false;
            j = 0;
_L10:
            if(j >= i) goto _L2; else goto _L1
_L1:
            char c = s.charAt(j);
            c;
            JVM INSTR lookupswitch 2: default 112
        //                       34: 128
        //                       44: 139;
               goto _L3 _L4 _L5
_L3:
            ((StringBuilder) (obj)).append(c);
            flag = false;
_L7:
            j++;
            continue; /* Loop/switch isn't completed */
_L4:
            if(flag) goto _L3; else goto _L6
_L6:
            flag = true;
              goto _L7
_L5:
            if(flag) goto _L3; else goto _L8
_L8:
            obj = ((StringBuilder) (obj)).toString().trim();
            if(((String) (obj)).length() > 0)
                arraylist.add(obj);
            obj = new StringBuilder();
              goto _L7
_L2:
            s = ((StringBuilder) (obj)).toString().trim();
            if(s.length() > 0)
                arraylist.add(s);
            return (String[])arraylist.toArray(new String[arraylist.size()]);
            if(true) goto _L10; else goto _L9
_L9:
        }

        public static transient String encode(String as[])
        {
            if(as == null)
                return null;
            StringBuilder stringbuilder = new StringBuilder();
            String s = "";
            int i = 0;
            for(int j = as.length; i < j; i++)
            {
                String s1 = as[i];
                stringbuilder.append(s).append(encodeToCsv(s1));
                s = ",";
            }

            return stringbuilder.toString();
        }

        private static String encodeToCsv(String s)
        {
            StringBuilder stringbuilder;
            int i;
            int j;
            stringbuilder = new StringBuilder();
            i = s.length();
            j = 0;
_L2:
            char c;
            if(j >= i)
                break MISSING_BLOCK_LABEL_89;
            c = s.charAt(j);
            switch(c)
            {
            default:
                break;

            case 34: // '"'
                break; /* Loop/switch isn't completed */

            case 44: // ','
                break;
            }
            break MISSING_BLOCK_LABEL_79;
_L3:
            stringbuilder.append(c);
            j++;
            if(true) goto _L2; else goto _L1
_L1:
            stringbuilder.append('"');
              goto _L3
            stringbuilder.append('"');
              goto _L3
            return stringbuilder.toString();
        }

        public static boolean isCanonical(String s)
        {
            return CANONICAL_GENRES.contains(s);
        }

        public static final String ANIMAL_WILDLIFE = "ANIMAL_WILDLIFE";
        public static final String ARTS = "ARTS";
        private static final ArraySet CANONICAL_GENRES;
        public static final String COMEDY = "COMEDY";
        private static final char COMMA = 44;
        private static final String DELIMITER = ",";
        private static final char DOUBLE_QUOTE = 34;
        public static final String DRAMA = "DRAMA";
        public static final String EDUCATION = "EDUCATION";
        private static final String EMPTY_STRING_ARRAY[] = new String[0];
        public static final String ENTERTAINMENT = "ENTERTAINMENT";
        public static final String FAMILY_KIDS = "FAMILY_KIDS";
        public static final String GAMING = "GAMING";
        public static final String LIFE_STYLE = "LIFE_STYLE";
        public static final String MOVIES = "MOVIES";
        public static final String MUSIC = "MUSIC";
        public static final String NEWS = "NEWS";
        public static final String PREMIER = "PREMIER";
        public static final String SHOPPING = "SHOPPING";
        public static final String SPORTS = "SPORTS";
        public static final String TECH_SCIENCE = "TECH_SCIENCE";
        public static final String TRAVEL = "TRAVEL";

        static 
        {
            CANONICAL_GENRES = new ArraySet();
            CANONICAL_GENRES.add("FAMILY_KIDS");
            CANONICAL_GENRES.add("SPORTS");
            CANONICAL_GENRES.add("SHOPPING");
            CANONICAL_GENRES.add("MOVIES");
            CANONICAL_GENRES.add("COMEDY");
            CANONICAL_GENRES.add("TRAVEL");
            CANONICAL_GENRES.add("DRAMA");
            CANONICAL_GENRES.add("EDUCATION");
            CANONICAL_GENRES.add("ANIMAL_WILDLIFE");
            CANONICAL_GENRES.add("NEWS");
            CANONICAL_GENRES.add("GAMING");
            CANONICAL_GENRES.add("ARTS");
            CANONICAL_GENRES.add("ENTERTAINMENT");
            CANONICAL_GENRES.add("LIFE_STYLE");
            CANONICAL_GENRES.add("MUSIC");
            CANONICAL_GENRES.add("PREMIER");
            CANONICAL_GENRES.add("TECH_SCIENCE");
        }

        private Programs.Genres()
        {
        }
    }

    public static final class RecordedPrograms
        implements BaseTvColumns, ProgramColumns
    {

        public static final String COLUMN_BROADCAST_GENRE = "broadcast_genre";
        public static final String COLUMN_CHANNEL_ID = "channel_id";
        public static final String COLUMN_END_TIME_UTC_MILLIS = "end_time_utc_millis";
        public static final String COLUMN_INPUT_ID = "input_id";
        public static final String COLUMN_RECORDING_DATA_BYTES = "recording_data_bytes";
        public static final String COLUMN_RECORDING_DATA_URI = "recording_data_uri";
        public static final String COLUMN_RECORDING_DURATION_MILLIS = "recording_duration_millis";
        public static final String COLUMN_RECORDING_EXPIRE_TIME_UTC_MILLIS = "recording_expire_time_utc_millis";
        public static final String COLUMN_START_TIME_UTC_MILLIS = "start_time_utc_millis";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/recorded_program";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/recorded_program";
        public static final Uri CONTENT_URI = Uri.parse("content://android.media.tv/recorded_program");


        private RecordedPrograms()
        {
        }
    }

    public static final class WatchNextPrograms
        implements BaseTvColumns, ProgramColumns, PreviewProgramColumns
    {

        public static final String COLUMN_LAST_ENGAGEMENT_TIME_UTC_MILLIS = "last_engagement_time_utc_millis";
        public static final String COLUMN_WATCH_NEXT_TYPE = "watch_next_type";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/watch_next_program";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/watch_next_program";
        public static final Uri CONTENT_URI = Uri.parse("content://android.media.tv/watch_next_program");
        public static final int WATCH_NEXT_TYPE_CONTINUE = 0;
        public static final int WATCH_NEXT_TYPE_NEW = 2;
        public static final int WATCH_NEXT_TYPE_NEXT = 1;
        public static final int WATCH_NEXT_TYPE_WATCHLIST = 3;


        private WatchNextPrograms()
        {
        }
    }

    public static final class WatchedPrograms
        implements BaseTvColumns
    {

        public static final String COLUMN_CHANNEL_ID = "channel_id";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_END_TIME_UTC_MILLIS = "end_time_utc_millis";
        public static final String COLUMN_INTERNAL_SESSION_TOKEN = "session_token";
        public static final String COLUMN_INTERNAL_TUNE_PARAMS = "tune_params";
        public static final String COLUMN_START_TIME_UTC_MILLIS = "start_time_utc_millis";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_WATCH_END_TIME_UTC_MILLIS = "watch_end_time_utc_millis";
        public static final String COLUMN_WATCH_START_TIME_UTC_MILLIS = "watch_start_time_utc_millis";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/watched_program";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/watched_program";
        public static final Uri CONTENT_URI = Uri.parse("content://android.media.tv/watched_program");


        private WatchedPrograms()
        {
        }
    }


    private TvContract()
    {
    }

    public static Uri buildChannelLogoUri(long l)
    {
        return buildChannelLogoUri(buildChannelUri(l));
    }

    public static Uri buildChannelLogoUri(Uri uri)
    {
        if(!isChannelUriForTunerInput(uri))
            throw new IllegalArgumentException((new StringBuilder()).append("Not a channel: ").append(uri).toString());
        else
            return Uri.withAppendedPath(uri, "logo");
    }

    public static Uri buildChannelUri(long l)
    {
        return ContentUris.withAppendedId(Channels.CONTENT_URI, l);
    }

    public static Uri buildChannelUriForPassthroughInput(String s)
    {
        return (new android.net.Uri.Builder()).scheme("content").authority("android.media.tv").appendPath("passthrough").appendPath(s).build();
    }

    public static Uri buildChannelsUriForInput(String s)
    {
        return buildChannelsUriForInput(s, false);
    }

    public static Uri buildChannelsUriForInput(String s, String s1, boolean flag)
    {
        if(s1 == null)
            return buildChannelsUriForInput(s, flag);
        if(!Programs.Genres.isCanonical(s1))
            throw new IllegalArgumentException((new StringBuilder()).append("Not a canonical genre: '").append(s1).append("'").toString());
        else
            return buildChannelsUriForInput(s, flag).buildUpon().appendQueryParameter("canonical_genre", s1).build();
    }

    public static Uri buildChannelsUriForInput(String s, boolean flag)
    {
        android.net.Uri.Builder builder = Channels.CONTENT_URI.buildUpon();
        if(s != null)
            builder.appendQueryParameter("input", s);
        return builder.appendQueryParameter("browsable_only", String.valueOf(flag)).build();
    }

    public static String buildInputId(ComponentName componentname)
    {
        return componentname.flattenToShortString();
    }

    public static Uri buildPreviewProgramUri(long l)
    {
        return ContentUris.withAppendedId(PreviewPrograms.CONTENT_URI, l);
    }

    public static Uri buildPreviewProgramsUriForChannel(long l)
    {
        return PreviewPrograms.CONTENT_URI.buildUpon().appendQueryParameter("channel", String.valueOf(l)).build();
    }

    public static Uri buildPreviewProgramsUriForChannel(Uri uri)
    {
        if(!isChannelUriForTunerInput(uri))
            throw new IllegalArgumentException((new StringBuilder()).append("Not a channel: ").append(uri).toString());
        else
            return buildPreviewProgramsUriForChannel(ContentUris.parseId(uri));
    }

    public static Uri buildProgramUri(long l)
    {
        return ContentUris.withAppendedId(Programs.CONTENT_URI, l);
    }

    public static Uri buildProgramsUriForChannel(long l)
    {
        return Programs.CONTENT_URI.buildUpon().appendQueryParameter("channel", String.valueOf(l)).build();
    }

    public static Uri buildProgramsUriForChannel(long l, long l1, long l2)
    {
        return buildProgramsUriForChannel(l).buildUpon().appendQueryParameter("start_time", String.valueOf(l1)).appendQueryParameter("end_time", String.valueOf(l2)).build();
    }

    public static Uri buildProgramsUriForChannel(Uri uri)
    {
        if(!isChannelUriForTunerInput(uri))
            throw new IllegalArgumentException((new StringBuilder()).append("Not a channel: ").append(uri).toString());
        else
            return buildProgramsUriForChannel(ContentUris.parseId(uri));
    }

    public static Uri buildProgramsUriForChannel(Uri uri, long l, long l1)
    {
        if(!isChannelUriForTunerInput(uri))
            throw new IllegalArgumentException((new StringBuilder()).append("Not a channel: ").append(uri).toString());
        else
            return buildProgramsUriForChannel(ContentUris.parseId(uri), l, l1);
    }

    public static Uri buildRecordedProgramUri(long l)
    {
        return ContentUris.withAppendedId(RecordedPrograms.CONTENT_URI, l);
    }

    public static Uri buildWatchNextProgramUri(long l)
    {
        return ContentUris.withAppendedId(WatchNextPrograms.CONTENT_URI, l);
    }

    public static Uri buildWatchedProgramUri(long l)
    {
        return ContentUris.withAppendedId(WatchedPrograms.CONTENT_URI, l);
    }

    public static boolean isChannelUri(Uri uri)
    {
        boolean flag;
        if(!isChannelUriForTunerInput(uri))
            flag = isChannelUriForPassthroughInput(uri);
        else
            flag = true;
        return flag;
    }

    public static boolean isChannelUriForPassthroughInput(Uri uri)
    {
        boolean flag;
        if(isTvUri(uri))
            flag = isTwoSegmentUriStartingWith(uri, "passthrough");
        else
            flag = false;
        return flag;
    }

    public static boolean isChannelUriForTunerInput(Uri uri)
    {
        boolean flag;
        if(isTvUri(uri))
            flag = isTwoSegmentUriStartingWith(uri, "channel");
        else
            flag = false;
        return flag;
    }

    public static boolean isProgramUri(Uri uri)
    {
        boolean flag;
        if(isTvUri(uri))
            flag = isTwoSegmentUriStartingWith(uri, "program");
        else
            flag = false;
        return flag;
    }

    public static boolean isRecordedProgramUri(Uri uri)
    {
        boolean flag;
        if(isTvUri(uri))
            flag = isTwoSegmentUriStartingWith(uri, "recorded_program");
        else
            flag = false;
        return flag;
    }

    private static boolean isTvUri(Uri uri)
    {
        boolean flag;
        if(uri != null && "content".equals(uri.getScheme()))
            flag = "android.media.tv".equals(uri.getAuthority());
        else
            flag = false;
        return flag;
    }

    private static boolean isTwoSegmentUriStartingWith(Uri uri, String s)
    {
        boolean flag = false;
        uri = uri.getPathSegments();
        if(uri.size() == 2)
            flag = s.equals(uri.get(0));
        return flag;
    }

    public static void requestChannelBrowsable(Context context, long l)
    {
        context = (TvInputManager)context.getSystemService("tv_input");
        if(context != null)
            context.requestChannelBrowsable(buildChannelUri(l));
    }

    public static final String ACTION_CHANNEL_BROWSABLE_REQUESTED = "android.media.tv.action.CHANNEL_BROWSABLE_REQUESTED";
    public static final String ACTION_INITIALIZE_PROGRAMS = "android.media.tv.action.INITIALIZE_PROGRAMS";
    public static final String ACTION_PREVIEW_PROGRAM_ADDED_TO_WATCH_NEXT = "android.media.tv.action.PREVIEW_PROGRAM_ADDED_TO_WATCH_NEXT";
    public static final String ACTION_PREVIEW_PROGRAM_BROWSABLE_DISABLED = "android.media.tv.action.PREVIEW_PROGRAM_BROWSABLE_DISABLED";
    public static final String ACTION_REQUEST_CHANNEL_BROWSABLE = "android.media.tv.action.REQUEST_CHANNEL_BROWSABLE";
    public static final String ACTION_WATCH_NEXT_PROGRAM_BROWSABLE_DISABLED = "android.media.tv.action.WATCH_NEXT_PROGRAM_BROWSABLE_DISABLED";
    public static final String AUTHORITY = "android.media.tv";
    public static final String EXTRA_BLOCKED_PACKAGES = "android.media.tv.extra.BLOCKED_PACKAGES";
    public static final String EXTRA_CHANNEL_ID = "android.media.tv.extra.CHANNEL_ID";
    public static final String EXTRA_COLUMN_NAME = "android.media.tv.extra.COLUMN_NAME";
    public static final String EXTRA_DATA_TYPE = "android.media.tv.extra.DATA_TYPE";
    public static final String EXTRA_DEFAULT_VALUE = "android.media.tv.extra.DEFAULT_VALUE";
    public static final String EXTRA_EXISTING_COLUMN_NAMES = "android.media.tv.extra.EXISTING_COLUMN_NAMES";
    public static final String EXTRA_PACKAGE_NAME = "android.media.tv.extra.PACKAGE_NAME";
    public static final String EXTRA_PREVIEW_PROGRAM_ID = "android.media.tv.extra.PREVIEW_PROGRAM_ID";
    public static final String EXTRA_RESULT_CODE = "android.media.tv.extra.RESULT_CODE";
    public static final String EXTRA_WATCH_NEXT_PROGRAM_ID = "android.media.tv.extra.WATCH_NEXT_PROGRAM_ID";
    public static final String METHOD_ADD_COLUMN = "add_column";
    public static final String METHOD_BLOCK_PACKAGE = "block_package";
    public static final String METHOD_GET_BLOCKED_PACKAGES = "get_blocked_packages";
    public static final String METHOD_GET_COLUMNS = "get_columns";
    public static final String METHOD_UNBLOCK_PACKAGE = "unblock_package";
    public static final String PARAM_BROWSABLE_ONLY = "browsable_only";
    public static final String PARAM_CANONICAL_GENRE = "canonical_genre";
    public static final String PARAM_CHANNEL = "channel";
    public static final String PARAM_END_TIME = "end_time";
    public static final String PARAM_INPUT = "input";
    public static final String PARAM_PACKAGE = "package";
    public static final String PARAM_PREVIEW = "preview";
    public static final String PARAM_START_TIME = "start_time";
    private static final String PATH_CHANNEL = "channel";
    private static final String PATH_PASSTHROUGH = "passthrough";
    private static final String PATH_PREVIEW_PROGRAM = "preview_program";
    private static final String PATH_PROGRAM = "program";
    private static final String PATH_RECORDED_PROGRAM = "recorded_program";
    private static final String PATH_WATCH_NEXT_PROGRAM = "watch_next_program";
    public static final String PERMISSION_READ_TV_LISTINGS = "android.permission.READ_TV_LISTINGS";
    public static final int RESULT_ERROR_INVALID_ARGUMENT = 2;
    public static final int RESULT_ERROR_IO = 1;
    public static final int RESULT_OK = 0;
}
