// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.provider;

import android.content.*;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteException;
import android.graphics.*;
import android.media.MiniThumbFile;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.*;
import android.util.Log;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import libcore.io.IoUtils;

// Referenced classes of package android.provider:
//            BaseColumns

public final class MediaStore
{
    public static final class Audio
    {

        public static String keyFor(String s)
        {
label0:
            {
                boolean flag;
                String s1;
label1:
                {
                    if(s == null)
                        break label0;
                    flag = false;
                    if(s.equals("<unknown>"))
                        return "\001";
                    if(s.startsWith("\001"))
                        flag = true;
                    s1 = s.trim().toLowerCase();
                    s = s1;
                    if(s1.startsWith("the "))
                        s = s1.substring(4);
                    s1 = s;
                    if(s.startsWith("an "))
                        s1 = s.substring(3);
                    s = s1;
                    if(s1.startsWith("a "))
                        s = s1.substring(2);
                    if(!s.endsWith(", the") && !s.endsWith(",the") && !s.endsWith(", an") && !s.endsWith(",an") && !s.endsWith(", a"))
                    {
                        s1 = s;
                        if(!s.endsWith(",a"))
                            break label1;
                    }
                    s1 = s.substring(0, s.lastIndexOf(','));
                }
                s1 = s1.replaceAll("[\\[\\]\\(\\)\"'.,?!]", "").trim();
                if(s1.length() > 0)
                {
                    s = new StringBuilder();
                    s.append('.');
                    int i = s1.length();
                    for(int j = 0; j < i; j++)
                    {
                        s.append(s1.charAt(j));
                        s.append('.');
                    }

                    s1 = DatabaseUtils.getCollationKey(s.toString());
                    s = s1;
                    if(flag)
                        s = (new StringBuilder()).append("\001").append(s1).toString();
                    return s;
                } else
                {
                    return "";
                }
            }
            return null;
        }

        public Audio()
        {
        }
    }

    public static interface Audio.AlbumColumns
    {

        public static final String ALBUM = "album";
        public static final String ALBUM_ART = "album_art";
        public static final String ALBUM_ID = "album_id";
        public static final String ALBUM_KEY = "album_key";
        public static final String ARTIST = "artist";
        public static final String FIRST_YEAR = "minyear";
        public static final String LAST_YEAR = "maxyear";
        public static final String NUMBER_OF_SONGS = "numsongs";
        public static final String NUMBER_OF_SONGS_FOR_ARTIST = "numsongs_by_artist";
    }

    public static final class Audio.Albums
        implements BaseColumns, Audio.AlbumColumns
    {

        public static Uri getContentUri(String s)
        {
            return Uri.parse((new StringBuilder()).append("content://media/").append(s).append("/audio/albums").toString());
        }

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/albums";
        public static final String DEFAULT_SORT_ORDER = "album_key";
        public static final String ENTRY_CONTENT_TYPE = "vnd.android.cursor.item/album";
        public static final Uri EXTERNAL_CONTENT_URI = getContentUri("external");
        public static final Uri INTERNAL_CONTENT_URI = getContentUri("internal");


        public Audio.Albums()
        {
        }
    }

    public static interface Audio.ArtistColumns
    {

        public static final String ARTIST = "artist";
        public static final String ARTIST_KEY = "artist_key";
        public static final String NUMBER_OF_ALBUMS = "number_of_albums";
        public static final String NUMBER_OF_TRACKS = "number_of_tracks";
    }

    public static final class Audio.Artists
        implements BaseColumns, Audio.ArtistColumns
    {

        public static Uri getContentUri(String s)
        {
            return Uri.parse((new StringBuilder()).append("content://media/").append(s).append("/audio/artists").toString());
        }

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/artists";
        public static final String DEFAULT_SORT_ORDER = "artist_key";
        public static final String ENTRY_CONTENT_TYPE = "vnd.android.cursor.item/artist";
        public static final Uri EXTERNAL_CONTENT_URI = getContentUri("external");
        public static final Uri INTERNAL_CONTENT_URI = getContentUri("internal");


        public Audio.Artists()
        {
        }
    }

    public static final class Audio.Artists.Albums
        implements Audio.AlbumColumns
    {

        public static final Uri getContentUri(String s, long l)
        {
            return Uri.parse((new StringBuilder()).append("content://media/").append(s).append("/audio/artists/").append(l).append("/albums").toString());
        }

        public Audio.Artists.Albums()
        {
        }
    }

    public static interface Audio.AudioColumns
        extends MediaColumns
    {

        public static final String ALBUM = "album";
        public static final String ALBUM_ARTIST = "album_artist";
        public static final String ALBUM_ID = "album_id";
        public static final String ALBUM_KEY = "album_key";
        public static final String ARTIST = "artist";
        public static final String ARTIST_ID = "artist_id";
        public static final String ARTIST_KEY = "artist_key";
        public static final String BOOKMARK = "bookmark";
        public static final String COMPILATION = "compilation";
        public static final String COMPOSER = "composer";
        public static final String DURATION = "duration";
        public static final String GENRE = "genre";
        public static final String IS_ALARM = "is_alarm";
        public static final String IS_MUSIC = "is_music";
        public static final String IS_NOTIFICATION = "is_notification";
        public static final String IS_PODCAST = "is_podcast";
        public static final String IS_RINGTONE = "is_ringtone";
        public static final String TITLE_KEY = "title_key";
        public static final String TRACK = "track";
        public static final String YEAR = "year";
    }

    public static final class Audio.Genres
        implements BaseColumns, Audio.GenresColumns
    {

        public static Uri getContentUri(String s)
        {
            return Uri.parse((new StringBuilder()).append("content://media/").append(s).append("/audio/genres").toString());
        }

        public static Uri getContentUriForAudioId(String s, int i)
        {
            return Uri.parse((new StringBuilder()).append("content://media/").append(s).append("/audio/media/").append(i).append("/genres").toString());
        }

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/genre";
        public static final String DEFAULT_SORT_ORDER = "name";
        public static final String ENTRY_CONTENT_TYPE = "vnd.android.cursor.item/genre";
        public static final Uri EXTERNAL_CONTENT_URI = getContentUri("external");
        public static final Uri INTERNAL_CONTENT_URI = getContentUri("internal");


        public Audio.Genres()
        {
        }
    }

    public static final class Audio.Genres.Members
        implements Audio.AudioColumns
    {

        public static final Uri getContentUri(String s, long l)
        {
            return Uri.parse((new StringBuilder()).append("content://media/").append(s).append("/audio/genres/").append(l).append("/members").toString());
        }

        public static final String AUDIO_ID = "audio_id";
        public static final String CONTENT_DIRECTORY = "members";
        public static final String DEFAULT_SORT_ORDER = "title_key";
        public static final String GENRE_ID = "genre_id";

        public Audio.Genres.Members()
        {
        }
    }

    public static interface Audio.GenresColumns
    {

        public static final String NAME = "name";
    }

    public static final class Audio.Media
        implements Audio.AudioColumns
    {

        public static Uri getContentUri(String s)
        {
            return Uri.parse((new StringBuilder()).append("content://media/").append(s).append("/audio/media").toString());
        }

        public static Uri getContentUriForPath(String s)
        {
            String as[] = EXTERNAL_PATHS;
            int i = 0;
            for(int j = as.length; i < j; i++)
                if(s.startsWith(as[i]))
                    return EXTERNAL_CONTENT_URI;

            if(s.startsWith(Environment.getExternalStorageDirectory().getPath()))
                s = EXTERNAL_CONTENT_URI;
            else
                s = INTERNAL_CONTENT_URI;
            return s;
        }

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/audio";
        public static final String DEFAULT_SORT_ORDER = "title_key";
        public static final String ENTRY_CONTENT_TYPE = "vnd.android.cursor.item/audio";
        public static final Uri EXTERNAL_CONTENT_URI = getContentUri("external");
        private static final String EXTERNAL_PATHS[];
        public static final String EXTRA_MAX_BYTES = "android.provider.MediaStore.extra.MAX_BYTES";
        public static final Uri INTERNAL_CONTENT_URI = getContentUri("internal");
        public static final String RECORD_SOUND_ACTION = "android.provider.MediaStore.RECORD_SOUND";

        static 
        {
            String s = System.getenv("SECONDARY_STORAGE");
            if(s != null)
                EXTERNAL_PATHS = s.split(":");
            else
                EXTERNAL_PATHS = new String[0];
        }

        public Audio.Media()
        {
        }
    }

    public static final class Audio.Playlists
        implements BaseColumns, Audio.PlaylistsColumns
    {

        public static Uri getContentUri(String s)
        {
            return Uri.parse((new StringBuilder()).append("content://media/").append(s).append("/audio/playlists").toString());
        }

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/playlist";
        public static final String DEFAULT_SORT_ORDER = "name";
        public static final String ENTRY_CONTENT_TYPE = "vnd.android.cursor.item/playlist";
        public static final Uri EXTERNAL_CONTENT_URI = getContentUri("external");
        public static final Uri INTERNAL_CONTENT_URI = getContentUri("internal");


        public Audio.Playlists()
        {
        }
    }

    public static final class Audio.Playlists.Members
        implements Audio.AudioColumns
    {

        public static final Uri getContentUri(String s, long l)
        {
            return Uri.parse((new StringBuilder()).append("content://media/").append(s).append("/audio/playlists/").append(l).append("/members").toString());
        }

        public static final boolean moveItem(ContentResolver contentresolver, long l, int i, int j)
        {
            boolean flag = false;
            Uri uri = getContentUri("external", l).buildUpon().appendEncodedPath(String.valueOf(i)).appendQueryParameter("move", "true").build();
            ContentValues contentvalues = new ContentValues();
            contentvalues.put("play_order", Integer.valueOf(j));
            if(contentresolver.update(uri, contentvalues, null, null) != 0)
                flag = true;
            return flag;
        }

        public static final String AUDIO_ID = "audio_id";
        public static final String CONTENT_DIRECTORY = "members";
        public static final String DEFAULT_SORT_ORDER = "play_order";
        public static final String PLAYLIST_ID = "playlist_id";
        public static final String PLAY_ORDER = "play_order";
        public static final String _ID = "_id";

        public Audio.Playlists.Members()
        {
        }
    }

    public static interface Audio.PlaylistsColumns
    {

        public static final String DATA = "_data";
        public static final String DATE_ADDED = "date_added";
        public static final String DATE_MODIFIED = "date_modified";
        public static final String NAME = "name";
    }

    public static final class Audio.Radio
    {

        public static final String ENTRY_CONTENT_TYPE = "vnd.android.cursor.item/radio";

        private Audio.Radio()
        {
        }
    }

    public static final class Files
    {

        public static Uri getContentUri(String s)
        {
            return Uri.parse((new StringBuilder()).append("content://media/").append(s).append("/file").toString());
        }

        public static final Uri getContentUri(String s, long l)
        {
            return Uri.parse((new StringBuilder()).append("content://media/").append(s).append("/file/").append(l).toString());
        }

        public static final Uri getDirectoryUri(String s)
        {
            return Uri.parse((new StringBuilder()).append("content://media/").append(s).append("/dir").toString());
        }

        public static Uri getMtpObjectsUri(String s)
        {
            return Uri.parse((new StringBuilder()).append("content://media/").append(s).append("/object").toString());
        }

        public static final Uri getMtpObjectsUri(String s, long l)
        {
            return Uri.parse((new StringBuilder()).append("content://media/").append(s).append("/object/").append(l).toString());
        }

        public static final Uri getMtpReferencesUri(String s, long l)
        {
            return Uri.parse((new StringBuilder()).append("content://media/").append(s).append("/object/").append(l).append("/references").toString());
        }

        public Files()
        {
        }
    }

    public static interface Files.FileColumns
        extends MediaColumns
    {

        public static final String FORMAT = "format";
        public static final String MEDIA_TYPE = "media_type";
        public static final int MEDIA_TYPE_AUDIO = 2;
        public static final int MEDIA_TYPE_IMAGE = 1;
        public static final int MEDIA_TYPE_NONE = 0;
        public static final int MEDIA_TYPE_PLAYLIST = 4;
        public static final int MEDIA_TYPE_VIDEO = 3;
        public static final String MIME_TYPE = "mime_type";
        public static final String PARENT = "parent";
        public static final String STORAGE_ID = "storage_id";
        public static final String TITLE = "title";
    }

    public static final class Images
    {

        public Images()
        {
        }
    }

    public static interface Images.ImageColumns
        extends MediaColumns
    {

        public static final String BUCKET_DISPLAY_NAME = "bucket_display_name";
        public static final String BUCKET_ID = "bucket_id";
        public static final String DATE_TAKEN = "datetaken";
        public static final String DESCRIPTION = "description";
        public static final String IS_PRIVATE = "isprivate";
        public static final String LATITUDE = "latitude";
        public static final String LONGITUDE = "longitude";
        public static final String MINI_THUMB_MAGIC = "mini_thumb_magic";
        public static final String ORIENTATION = "orientation";
        public static final String PICASA_ID = "picasa_id";
    }

    public static final class Images.Media
        implements Images.ImageColumns
    {

        private static final Bitmap StoreThumbnail(ContentResolver contentresolver, Bitmap bitmap, long l, float f, float f1, int i)
        {
            Object obj = new Matrix();
            ((Matrix) (obj)).setScale(f / (float)bitmap.getWidth(), f1 / (float)bitmap.getHeight());
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), ((Matrix) (obj)), true);
            obj = new ContentValues(4);
            ((ContentValues) (obj)).put("kind", Integer.valueOf(i));
            ((ContentValues) (obj)).put("image_id", Integer.valueOf((int)l));
            ((ContentValues) (obj)).put("height", Integer.valueOf(bitmap.getHeight()));
            ((ContentValues) (obj)).put("width", Integer.valueOf(bitmap.getWidth()));
            obj = contentresolver.insert(Images.Thumbnails.EXTERNAL_CONTENT_URI, ((ContentValues) (obj)));
            try
            {
                contentresolver = contentresolver.openOutputStream(((Uri) (obj)));
                bitmap.compress(android.graphics.Bitmap.CompressFormat.JPEG, 100, contentresolver);
                contentresolver.close();
            }
            // Misplaced declaration of an exception variable
            catch(ContentResolver contentresolver)
            {
                return null;
            }
            // Misplaced declaration of an exception variable
            catch(ContentResolver contentresolver)
            {
                return null;
            }
            return bitmap;
        }

        public static final Bitmap getBitmap(ContentResolver contentresolver, Uri uri)
            throws FileNotFoundException, IOException
        {
            contentresolver = contentresolver.openInputStream(uri);
            uri = BitmapFactory.decodeStream(contentresolver);
            contentresolver.close();
            return uri;
        }

        public static Uri getContentUri(String s)
        {
            return Uri.parse((new StringBuilder()).append("content://media/").append(s).append("/images/media").toString());
        }

        public static final String insertImage(ContentResolver contentresolver, Bitmap bitmap, String s, String s1)
        {
            Object obj;
            Object obj1;
            obj = new ContentValues();
            ((ContentValues) (obj)).put("title", s);
            ((ContentValues) (obj)).put("description", s1);
            ((ContentValues) (obj)).put("mime_type", "image/jpeg");
            s = null;
            obj1 = null;
            s1 = contentresolver.insert(EXTERNAL_CONTENT_URI, ((ContentValues) (obj)));
            if(bitmap == null) goto _L2; else goto _L1
_L1:
            s = s1;
            obj = contentresolver.openOutputStream(s1);
            bitmap.compress(android.graphics.Bitmap.CompressFormat.JPEG, 50, ((OutputStream) (obj)));
            s = s1;
            ((OutputStream) (obj)).close();
            s = s1;
            long l = ContentUris.parseId(s1);
            s = s1;
            StoreThumbnail(contentresolver, Images.Thumbnails.getThumbnail(contentresolver, l, 1, null), l, 50F, 50F, 3);
            bitmap = s1;
_L4:
            contentresolver = obj1;
            if(bitmap != null)
                contentresolver = bitmap.toString();
            return contentresolver;
            bitmap;
            s = s1;
            ((OutputStream) (obj)).close();
            s = s1;
            try
            {
                throw bitmap;
            }
            // Misplaced declaration of an exception variable
            catch(Bitmap bitmap)
            {
                Log.e("MediaStore", "Failed to insert image", bitmap);
            }
            bitmap = s;
            if(s != null)
            {
                contentresolver.delete(s, null, null);
                bitmap = null;
            }
            continue; /* Loop/switch isn't completed */
_L2:
            s = s1;
            Log.e("MediaStore", "Failed to create thumbnail, removing original");
            s = s1;
            contentresolver.delete(s1, null, null);
            bitmap = null;
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static final String insertImage(ContentResolver contentresolver, String s, String s1, String s2)
            throws FileNotFoundException
        {
            FileInputStream fileinputstream = new FileInputStream(s);
            s = BitmapFactory.decodeFile(s);
            contentresolver = insertImage(contentresolver, ((Bitmap) (s)), s1, s2);
            s.recycle();
            try
            {
                fileinputstream.close();
            }
            // Misplaced declaration of an exception variable
            catch(String s) { }
            return contentresolver;
            contentresolver;
            try
            {
                fileinputstream.close();
            }
            // Misplaced declaration of an exception variable
            catch(String s) { }
            throw contentresolver;
        }

        public static final Cursor query(ContentResolver contentresolver, Uri uri, String as[])
        {
            return contentresolver.query(uri, as, null, null, "bucket_display_name");
        }

        public static final Cursor query(ContentResolver contentresolver, Uri uri, String as[], String s, String s1)
        {
            if(s1 == null)
                s1 = "bucket_display_name";
            return contentresolver.query(uri, as, s, null, s1);
        }

        public static final Cursor query(ContentResolver contentresolver, Uri uri, String as[], String s, String as1[], String s1)
        {
            if(s1 == null)
                s1 = "bucket_display_name";
            return contentresolver.query(uri, as, s, as1, s1);
        }

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/image";
        public static final String DEFAULT_SORT_ORDER = "bucket_display_name";
        public static final Uri EXTERNAL_CONTENT_URI = getContentUri("external");
        public static final Uri INTERNAL_CONTENT_URI = getContentUri("internal");


        public Images.Media()
        {
        }
    }

    public static class Images.Thumbnails
        implements BaseColumns
    {

        public static void cancelThumbnailRequest(ContentResolver contentresolver, long l)
        {
            InternalThumbnails.cancelThumbnailRequest(contentresolver, l, EXTERNAL_CONTENT_URI, 0L);
        }

        public static void cancelThumbnailRequest(ContentResolver contentresolver, long l, long l1)
        {
            InternalThumbnails.cancelThumbnailRequest(contentresolver, l, EXTERNAL_CONTENT_URI, l1);
        }

        public static Uri getContentUri(String s)
        {
            return Uri.parse((new StringBuilder()).append("content://media/").append(s).append("/images/thumbnails").toString());
        }

        public static Bitmap getThumbnail(ContentResolver contentresolver, long l, int i, android.graphics.BitmapFactory.Options options)
        {
            return InternalThumbnails.getThumbnail(contentresolver, l, 0L, i, options, EXTERNAL_CONTENT_URI, false);
        }

        public static Bitmap getThumbnail(ContentResolver contentresolver, long l, long l1, int i, android.graphics.BitmapFactory.Options options)
        {
            return InternalThumbnails.getThumbnail(contentresolver, l, l1, i, options, EXTERNAL_CONTENT_URI, false);
        }

        public static final Cursor query(ContentResolver contentresolver, Uri uri, String as[])
        {
            return contentresolver.query(uri, as, null, null, "image_id ASC");
        }

        public static final Cursor queryMiniThumbnail(ContentResolver contentresolver, long l, int i, String as[])
        {
            return contentresolver.query(EXTERNAL_CONTENT_URI, as, (new StringBuilder()).append("image_id = ").append(l).append(" AND ").append("kind").append(" = ").append(i).toString(), null, null);
        }

        public static final Cursor queryMiniThumbnails(ContentResolver contentresolver, Uri uri, int i, String as[])
        {
            return contentresolver.query(uri, as, (new StringBuilder()).append("kind = ").append(i).toString(), null, "image_id ASC");
        }

        public static final String DATA = "_data";
        public static final String DEFAULT_SORT_ORDER = "image_id ASC";
        public static final Uri EXTERNAL_CONTENT_URI = getContentUri("external");
        public static final int FULL_SCREEN_KIND = 2;
        public static final String HEIGHT = "height";
        public static final String IMAGE_ID = "image_id";
        public static final Uri INTERNAL_CONTENT_URI = getContentUri("internal");
        public static final String KIND = "kind";
        public static final int MICRO_KIND = 3;
        public static final int MINI_KIND = 1;
        public static final String THUMB_DATA = "thumb_data";
        public static final String WIDTH = "width";


        public Images.Thumbnails()
        {
        }
    }

    private static class InternalThumbnails
        implements BaseColumns
    {

        static void cancelThumbnailRequest(ContentResolver contentresolver, long l, Uri uri, long l1)
        {
            uri = uri.buildUpon().appendQueryParameter("cancel", "1").appendQueryParameter("orig_id", String.valueOf(l)).appendQueryParameter("group_id", String.valueOf(l1)).build();
            contentresolver = contentresolver.query(uri, PROJECTION, null, null, null);
            if(contentresolver != null)
                contentresolver.close();
            return;
            contentresolver;
            throw contentresolver;
        }

        private static Bitmap getMiniThumbFromFile(Cursor cursor, Uri uri, ContentResolver contentresolver, android.graphics.BitmapFactory.Options options)
        {
            Object obj;
            Object obj1;
            Object obj2;
            Object obj3;
            Object obj4;
            Object obj5;
            Cursor cursor1;
            Uri uri1;
            Cursor cursor2;
            Uri uri2;
            Cursor cursor3;
            Uri uri3;
            obj = null;
            obj1 = null;
            obj2 = null;
            obj3 = null;
            obj4 = null;
            obj5 = null;
            cursor1 = obj2;
            uri1 = obj5;
            cursor2 = obj;
            uri2 = obj3;
            cursor3 = obj1;
            uri3 = obj4;
            long l = cursor.getLong(0);
            cursor1 = obj2;
            uri1 = obj5;
            cursor2 = obj;
            uri2 = obj3;
            cursor3 = obj1;
            uri3 = obj4;
            cursor.getString(1);
            cursor1 = obj2;
            uri1 = obj5;
            cursor2 = obj;
            uri2 = obj3;
            cursor3 = obj1;
            uri3 = obj4;
            uri = ContentUris.withAppendedId(uri, l);
            cursor1 = obj2;
            uri1 = uri;
            cursor2 = obj;
            uri2 = uri;
            cursor3 = obj1;
            uri3 = uri;
            contentresolver = contentresolver.openFileDescriptor(uri, "r");
            cursor1 = obj2;
            uri1 = uri;
            cursor2 = obj;
            uri2 = uri;
            cursor3 = obj1;
            uri3 = uri;
            cursor = BitmapFactory.decodeFileDescriptor(contentresolver.getFileDescriptor(), null, options);
            cursor1 = cursor;
            uri1 = uri;
            cursor2 = cursor;
            uri2 = uri;
            cursor3 = cursor;
            uri3 = uri;
            try
            {
                contentresolver.close();
            }
            // Misplaced declaration of an exception variable
            catch(Cursor cursor)
            {
                Log.e("MediaStore", (new StringBuilder()).append("couldn't open thumbnail ").append(uri3).append("; ").append(cursor).toString());
                cursor = cursor3;
            }
            // Misplaced declaration of an exception variable
            catch(Cursor cursor)
            {
                Log.e("MediaStore", (new StringBuilder()).append("couldn't open thumbnail ").append(uri2).append("; ").append(cursor).toString());
                cursor = cursor2;
            }
            // Misplaced declaration of an exception variable
            catch(Cursor cursor)
            {
                Log.e("MediaStore", (new StringBuilder()).append("failed to allocate memory for thumbnail ").append(uri1).append("; ").append(cursor).toString());
                cursor = cursor1;
            }
            return cursor;
        }

        static Bitmap getThumbnail(ContentResolver contentresolver, long l, long l1, int i, android.graphics.BitmapFactory.Options options, Uri uri, 
                boolean flag)
        {
            Object obj1;
            Object obj2;
            Object obj4;
            MiniThumbFile minithumbfile;
            Object obj5;
            Object obj6;
            Object obj7;
            Object obj8;
            Object obj9;
            Object obj10;
            Object obj11;
            Object obj = null;
            obj1 = null;
            obj2 = null;
            Object obj3 = null;
            if(flag)
                obj4 = Video.Media.EXTERNAL_CONTENT_URI;
            else
                obj4 = Images.Media.EXTERNAL_CONTENT_URI;
            minithumbfile = new MiniThumbFile(((Uri) (obj4)));
            obj5 = null;
            obj6 = null;
            obj7 = null;
            obj4 = obj1;
            obj8 = obj7;
            obj9 = obj2;
            obj10 = obj5;
            obj11 = obj6;
            if(minithumbfile.getMagic(l) == 0L) goto _L2; else goto _L1
_L1:
            if(i != 3) goto _L4; else goto _L3
_L3:
            obj4 = obj1;
            obj8 = obj7;
            obj11 = obj6;
            obj9 = sThumbBufLock;
            obj4 = obj1;
            obj8 = obj7;
            obj11 = obj6;
            obj9;
            JVM INSTR monitorenter ;
            contentresolver = obj;
            if(sThumbBuf != null)
                break MISSING_BLOCK_LABEL_131;
            contentresolver = obj;
            sThumbBuf = new byte[10000];
            options = obj3;
            contentresolver = obj;
            if(minithumbfile.getMiniThumbFromFile(l, sThumbBuf) == null)
                break MISSING_BLOCK_LABEL_190;
            contentresolver = obj;
            uri = BitmapFactory.decodeByteArray(sThumbBuf, 0, sThumbBuf.length);
            options = uri;
            if(uri != null)
                break MISSING_BLOCK_LABEL_190;
            contentresolver = uri;
            Log.w("MediaStore", "couldn't decode byte array.");
            options = uri;
            obj4 = options;
            obj8 = obj7;
            obj11 = obj6;
            obj9;
            JVM INSTR monitorexit ;
            minithumbfile.deactivate();
            return options;
            options;
            obj4 = contentresolver;
            obj8 = obj7;
            obj11 = obj6;
            obj9;
            JVM INSTR monitorexit ;
            obj4 = contentresolver;
            obj8 = obj7;
            obj11 = obj6;
            try
            {
                throw options;
            }
            // Misplaced declaration of an exception variable
            catch(ContentResolver contentresolver)
            {
                obj11 = obj8;
            }
            Log.w("MediaStore", contentresolver);
            if(obj8 != null)
                ((Cursor) (obj8)).close();
            minithumbfile.deactivate();
            options = ((android.graphics.BitmapFactory.Options) (obj4));
_L13:
            return options;
_L4:
            obj9 = obj2;
            obj10 = obj5;
            if(i != 1) goto _L2; else goto _L5
_L5:
            String as[];
            if(flag)
                obj9 = "video_id=";
            else
                obj9 = "image_id=";
            obj4 = obj1;
            obj8 = obj7;
            obj11 = obj6;
            as = PROJECTION;
            obj4 = obj1;
            obj8 = obj7;
            obj11 = obj6;
            obj10 = JVM INSTR new #126 <Class StringBuilder>;
            obj4 = obj1;
            obj8 = obj7;
            obj11 = obj6;
            ((StringBuilder) (obj10)).StringBuilder();
            obj4 = obj1;
            obj8 = obj7;
            obj11 = obj6;
            obj7 = contentresolver.query(uri, as, ((StringBuilder) (obj10)).append(((String) (obj9))).append(l).toString(), null, null);
            obj9 = obj2;
            obj10 = obj7;
            if(obj7 == null) goto _L2; else goto _L6
_L6:
            obj4 = obj1;
            obj8 = obj7;
            obj9 = obj2;
            obj10 = obj7;
            obj11 = obj7;
            if(!((Cursor) (obj7)).moveToFirst()) goto _L2; else goto _L7
_L7:
            obj4 = obj1;
            obj8 = obj7;
            obj11 = obj7;
            obj9 = getMiniThumbFromFile(((Cursor) (obj7)), uri, contentresolver, options);
            obj4 = obj9;
            obj9 = obj4;
            obj10 = obj7;
            if(obj4 != null)
            {
                if(obj7 != null)
                    ((Cursor) (obj7)).close();
                minithumbfile.deactivate();
                return ((Bitmap) (obj4));
            }
_L2:
            obj4 = obj9;
            obj8 = obj10;
            obj11 = obj10;
            obj7 = uri.buildUpon().appendQueryParameter("blocking", "1").appendQueryParameter("orig_id", String.valueOf(l)).appendQueryParameter("group_id", String.valueOf(l1)).build();
            if(obj10 == null)
                break MISSING_BLOCK_LABEL_581;
            obj4 = obj9;
            obj8 = obj10;
            obj11 = obj10;
            ((Cursor) (obj10)).close();
            obj4 = obj9;
            obj8 = obj10;
            obj11 = obj10;
            obj7 = contentresolver.query(((Uri) (obj7)), PROJECTION, null, null, null);
            if(obj7 == null)
            {
                if(obj7 != null)
                    ((Cursor) (obj7)).close();
                minithumbfile.deactivate();
                return null;
            }
            if(i != 3) goto _L9; else goto _L8
_L8:
            obj4 = obj9;
            obj8 = obj7;
            obj11 = obj7;
            obj1 = sThumbBufLock;
            obj4 = obj9;
            obj8 = obj7;
            obj11 = obj7;
            obj1;
            JVM INSTR monitorenter ;
            options = ((android.graphics.BitmapFactory.Options) (obj9));
            if(sThumbBuf != null)
                break MISSING_BLOCK_LABEL_691;
            options = ((android.graphics.BitmapFactory.Options) (obj9));
            sThumbBuf = new byte[10000];
            options = ((android.graphics.BitmapFactory.Options) (obj9));
            Arrays.fill(sThumbBuf, (byte)0);
            obj10 = obj9;
            options = ((android.graphics.BitmapFactory.Options) (obj9));
            if(minithumbfile.getMiniThumbFromFile(l, sThumbBuf) == null)
                break MISSING_BLOCK_LABEL_764;
            options = ((android.graphics.BitmapFactory.Options) (obj9));
            obj4 = BitmapFactory.decodeByteArray(sThumbBuf, 0, sThumbBuf.length);
            obj10 = obj4;
            if(obj4 != null)
                break MISSING_BLOCK_LABEL_764;
            options = ((android.graphics.BitmapFactory.Options) (obj4));
            Log.w("MediaStore", "couldn't decode byte array.");
            obj10 = obj4;
            obj4 = obj10;
            obj8 = obj7;
            obj11 = obj7;
            obj1;
            JVM INSTR monitorexit ;
_L11:
            options = ((android.graphics.BitmapFactory.Options) (obj10));
            obj4 = obj7;
            if(obj10 != null)
                break MISSING_BLOCK_LABEL_1274;
            obj4 = obj10;
            obj8 = obj7;
            obj11 = obj7;
            options = JVM INSTR new #126 <Class StringBuilder>;
            obj4 = obj10;
            obj8 = obj7;
            obj11 = obj7;
            options.StringBuilder();
            obj4 = obj10;
            obj8 = obj7;
            obj11 = obj7;
            Log.v("MediaStore", options.append("Create the thumbnail in memory: origId=").append(l).append(", kind=").append(i).append(", isVideo=").append(flag).toString());
            obj4 = obj10;
            obj8 = obj7;
            obj11 = obj7;
            options = Uri.parse(uri.buildUpon().appendPath(String.valueOf(l)).toString().replaceFirst("thumbnails", "media"));
            if(obj7 == null)
                break MISSING_BLOCK_LABEL_941;
            obj4 = obj10;
            obj8 = obj7;
            obj11 = obj7;
            ((Cursor) (obj7)).close();
            obj4 = obj10;
            obj8 = obj7;
            obj11 = obj7;
            contentresolver = contentresolver.query(options, PROJECTION, null, null, null);
            if(contentresolver == null)
                break MISSING_BLOCK_LABEL_995;
            obj4 = obj10;
            obj8 = contentresolver;
            obj11 = contentresolver;
            boolean flag1 = contentresolver.moveToFirst();
            if(!(flag1 ^ true))
                break MISSING_BLOCK_LABEL_1216;
            if(contentresolver != null)
                contentresolver.close();
            minithumbfile.deactivate();
            return null;
            contentresolver;
            obj4 = options;
            obj8 = obj7;
            obj11 = obj7;
            obj1;
            JVM INSTR monitorexit ;
            obj4 = options;
            obj8 = obj7;
            obj11 = obj7;
            throw contentresolver;
            contentresolver;
            if(obj11 != null)
                ((Cursor) (obj11)).close();
            minithumbfile.deactivate();
            throw contentresolver;
_L9:
            if(i != 1)
                break; /* Loop/switch isn't completed */
            obj4 = obj9;
            obj8 = obj7;
            obj10 = obj9;
            obj11 = obj7;
            if(!((Cursor) (obj7)).moveToFirst())
                continue; /* Loop/switch isn't completed */
            obj4 = obj9;
            obj8 = obj7;
            obj11 = obj7;
            obj10 = getMiniThumbFromFile(((Cursor) (obj7)), uri, contentresolver, options);
            if(true) goto _L11; else goto _L10
_L10:
            obj4 = obj9;
            obj8 = obj7;
            obj11 = obj7;
            contentresolver = JVM INSTR new #247 <Class IllegalArgumentException>;
            obj4 = obj9;
            obj8 = obj7;
            obj11 = obj7;
            options = JVM INSTR new #126 <Class StringBuilder>;
            obj4 = obj9;
            obj8 = obj7;
            obj11 = obj7;
            options.StringBuilder();
            obj4 = obj9;
            obj8 = obj7;
            obj11 = obj7;
            contentresolver.IllegalArgumentException(options.append("Unsupported kind: ").append(i).toString());
            obj4 = obj9;
            obj8 = obj7;
            obj11 = obj7;
            throw contentresolver;
            obj4 = obj10;
            obj8 = contentresolver;
            obj11 = contentresolver;
            uri = contentresolver.getString(1);
            options = ((android.graphics.BitmapFactory.Options) (obj10));
            obj4 = contentresolver;
            if(uri == null)
                break MISSING_BLOCK_LABEL_1274;
            if(!flag)
                break; /* Loop/switch isn't completed */
            obj4 = obj10;
            obj8 = contentresolver;
            obj11 = contentresolver;
            options = ThumbnailUtils.createVideoThumbnail(uri, i);
            obj4 = contentresolver;
_L14:
            if(obj4 != null)
                ((Cursor) (obj4)).close();
            minithumbfile.deactivate();
            if(true) goto _L13; else goto _L12
_L12:
            obj4 = obj10;
            obj8 = contentresolver;
            obj11 = contentresolver;
            options = ThumbnailUtils.createImageThumbnail(uri, i);
            obj4 = contentresolver;
              goto _L14
        }

        static final int DEFAULT_GROUP_ID = 0;
        private static final int FULL_SCREEN_KIND = 2;
        private static final int MICRO_KIND = 3;
        private static final int MINI_KIND = 1;
        private static final String PROJECTION[] = {
            "_id", "_data"
        };
        private static byte sThumbBuf[];
        private static final Object sThumbBufLock = new Object();


        private InternalThumbnails()
        {
        }
    }

    public static interface MediaColumns
        extends BaseColumns
    {

        public static final String DATA = "_data";
        public static final String DATE_ADDED = "date_added";
        public static final String DATE_MODIFIED = "date_modified";
        public static final String DISPLAY_NAME = "_display_name";
        public static final String HEIGHT = "height";
        public static final String IS_DRM = "is_drm";
        public static final String MEDIA_SCANNER_NEW_OBJECT_ID = "media_scanner_new_object_id";
        public static final String MIME_TYPE = "mime_type";
        public static final String SIZE = "_size";
        public static final String TITLE = "title";
        public static final String WIDTH = "width";
    }

    public static final class Video
    {

        public static final Cursor query(ContentResolver contentresolver, Uri uri, String as[])
        {
            return contentresolver.query(uri, as, null, null, "_display_name");
        }

        public static final String DEFAULT_SORT_ORDER = "_display_name";

        public Video()
        {
        }
    }

    public static final class Video.Media
        implements Video.VideoColumns
    {

        public static Uri getContentUri(String s)
        {
            return Uri.parse((new StringBuilder()).append("content://media/").append(s).append("/video/media").toString());
        }

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/video";
        public static final String DEFAULT_SORT_ORDER = "title";
        public static final Uri EXTERNAL_CONTENT_URI = getContentUri("external");
        public static final Uri INTERNAL_CONTENT_URI = getContentUri("internal");


        public Video.Media()
        {
        }
    }

    public static class Video.Thumbnails
        implements BaseColumns
    {

        public static void cancelThumbnailRequest(ContentResolver contentresolver, long l)
        {
            InternalThumbnails.cancelThumbnailRequest(contentresolver, l, EXTERNAL_CONTENT_URI, 0L);
        }

        public static void cancelThumbnailRequest(ContentResolver contentresolver, long l, long l1)
        {
            InternalThumbnails.cancelThumbnailRequest(contentresolver, l, EXTERNAL_CONTENT_URI, l1);
        }

        public static Uri getContentUri(String s)
        {
            return Uri.parse((new StringBuilder()).append("content://media/").append(s).append("/video/thumbnails").toString());
        }

        public static Bitmap getThumbnail(ContentResolver contentresolver, long l, int i, android.graphics.BitmapFactory.Options options)
        {
            return InternalThumbnails.getThumbnail(contentresolver, l, 0L, i, options, EXTERNAL_CONTENT_URI, true);
        }

        public static Bitmap getThumbnail(ContentResolver contentresolver, long l, long l1, int i, android.graphics.BitmapFactory.Options options)
        {
            return InternalThumbnails.getThumbnail(contentresolver, l, l1, i, options, EXTERNAL_CONTENT_URI, true);
        }

        public static final String DATA = "_data";
        public static final String DEFAULT_SORT_ORDER = "video_id ASC";
        public static final Uri EXTERNAL_CONTENT_URI = getContentUri("external");
        public static final int FULL_SCREEN_KIND = 2;
        public static final String HEIGHT = "height";
        public static final Uri INTERNAL_CONTENT_URI = getContentUri("internal");
        public static final String KIND = "kind";
        public static final int MICRO_KIND = 3;
        public static final int MINI_KIND = 1;
        public static final String VIDEO_ID = "video_id";
        public static final String WIDTH = "width";


        public Video.Thumbnails()
        {
        }
    }

    public static interface Video.VideoColumns
        extends MediaColumns
    {

        public static final String ALBUM = "album";
        public static final String ARTIST = "artist";
        public static final String BOOKMARK = "bookmark";
        public static final String BUCKET_DISPLAY_NAME = "bucket_display_name";
        public static final String BUCKET_ID = "bucket_id";
        public static final String CATEGORY = "category";
        public static final String DATE_TAKEN = "datetaken";
        public static final String DESCRIPTION = "description";
        public static final String DURATION = "duration";
        public static final String IS_PRIVATE = "isprivate";
        public static final String LANGUAGE = "language";
        public static final String LATITUDE = "latitude";
        public static final String LONGITUDE = "longitude";
        public static final String MINI_THUMB_MAGIC = "mini_thumb_magic";
        public static final String RESOLUTION = "resolution";
        public static final String TAGS = "tags";
    }


    public MediaStore()
    {
    }

    private static Uri getDocumentUri(ContentResolver contentresolver, String s, List list)
        throws RemoteException
    {
        Object obj;
        Object obj1;
        ContentResolver contentresolver1;
        Object obj2;
        obj = null;
        obj1 = null;
        contentresolver1 = null;
        obj2 = null;
        contentresolver = contentresolver.acquireUnstableContentProviderClient("com.android.externalstorage.documents");
        obj2 = contentresolver;
        contentresolver1 = contentresolver;
        Bundle bundle = JVM INSTR new #211 <Class Bundle>;
        obj2 = contentresolver;
        contentresolver1 = contentresolver;
        bundle.Bundle();
        obj2 = contentresolver;
        contentresolver1 = contentresolver;
        bundle.putParcelableList("com.android.externalstorage.documents.extra.uriPermissions", list);
        obj2 = contentresolver;
        contentresolver1 = contentresolver;
        list = (Uri)contentresolver.call("getDocumentId", s, bundle).getParcelable("uri");
        s = obj1;
        if(contentresolver == null)
            break MISSING_BLOCK_LABEL_92;
        contentresolver.close();
        s = obj1;
_L1:
        if(s != null)
            throw s;
        else
            return list;
        s;
          goto _L1
        contentresolver;
        throw contentresolver;
        s;
_L4:
        list = contentresolver;
        if(obj2 == null)
            break MISSING_BLOCK_LABEL_122;
        ((ContentProviderClient) (obj2)).close();
        list = contentresolver;
_L2:
        if(list != null)
            throw list;
        else
            throw s;
        obj2;
        if(contentresolver == null)
        {
            list = ((List) (obj2));
        } else
        {
            list = contentresolver;
            if(contentresolver != obj2)
            {
                contentresolver.addSuppressed(((Throwable) (obj2)));
                list = contentresolver;
            }
        }
          goto _L2
        s;
        obj2 = contentresolver1;
        contentresolver = obj;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static Uri getDocumentUri(Context context, Uri uri)
    {
        try
        {
            context = context.getContentResolver();
            context = getDocumentUri(((ContentResolver) (context)), getFilePath(context, uri), context.getPersistedUriPermissions());
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw context.rethrowAsRuntimeException();
        }
        return context;
    }

    private static String getFilePath(ContentResolver contentresolver, Uri uri)
        throws RemoteException
    {
        Object obj;
        Object obj1;
        Object obj2;
        obj = null;
        obj1 = null;
        obj2 = null;
        contentresolver = contentresolver.acquireUnstableContentProviderClient("media");
        obj2 = contentresolver;
        obj1 = contentresolver;
        Cursor cursor = contentresolver.query(uri, new String[] {
            "_data"
        }, null, null, null);
        if(cursor.getCount() == 0)
        {
            obj1 = JVM INSTR new #281 <Class IllegalStateException>;
            obj2 = JVM INSTR new #283 <Class StringBuilder>;
            ((StringBuilder) (obj2)).StringBuilder();
            ((IllegalStateException) (obj1)).IllegalStateException(((StringBuilder) (obj2)).append("Not found media file under URI: ").append(uri).toString());
            throw obj1;
        }
          goto _L1
        uri;
        obj2 = contentresolver;
        obj1 = contentresolver;
        IoUtils.closeQuietly(cursor);
        obj2 = contentresolver;
        obj1 = contentresolver;
        try
        {
            throw uri;
        }
        // Misplaced declaration of an exception variable
        catch(ContentResolver contentresolver) { }
        throw contentresolver;
        uri;
_L5:
        obj1 = contentresolver;
        if(obj2 == null)
            break MISSING_BLOCK_LABEL_120;
        ((ContentProviderClient) (obj2)).close();
        obj1 = contentresolver;
_L3:
        String s;
        if(obj1 != null)
            throw obj1;
        else
            throw uri;
_L1:
        if(!cursor.moveToFirst())
        {
            uri = JVM INSTR new #281 <Class IllegalStateException>;
            uri.IllegalStateException("Failed to move cursor to the first item.");
            throw uri;
        }
        s = cursor.getString(0);
        obj2 = contentresolver;
        obj1 = contentresolver;
        IoUtils.closeQuietly(cursor);
        uri = obj;
        if(contentresolver == null)
            break MISSING_BLOCK_LABEL_181;
        contentresolver.close();
        uri = obj;
_L2:
        if(uri != null)
            throw uri;
        else
            return s;
        uri;
          goto _L2
        obj2;
        if(contentresolver == null)
        {
            obj1 = obj2;
        } else
        {
            obj1 = contentresolver;
            if(contentresolver != obj2)
            {
                contentresolver.addSuppressed(((Throwable) (obj2)));
                obj1 = contentresolver;
            }
        }
          goto _L3
        uri;
        contentresolver = null;
        obj2 = obj1;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public static Uri getMediaScannerUri()
    {
        return Uri.parse("content://media/none/media_scanner");
    }

    public static String getVersion(Context context)
    {
        context = context.getContentResolver().query(Uri.parse("content://media/none/version"), null, null, null, null);
        if(context == null)
            break MISSING_BLOCK_LABEL_53;
        String s;
        if(!context.moveToFirst())
            break MISSING_BLOCK_LABEL_47;
        s = context.getString(0);
        context.close();
        return s;
        context.close();
        return null;
        Exception exception;
        exception;
        context.close();
        throw exception;
    }

    public static final String ACTION_IMAGE_CAPTURE = "android.media.action.IMAGE_CAPTURE";
    public static final String ACTION_IMAGE_CAPTURE_SECURE = "android.media.action.IMAGE_CAPTURE_SECURE";
    public static final String ACTION_MTP_SESSION_END = "android.provider.action.MTP_SESSION_END";
    public static final String ACTION_VIDEO_CAPTURE = "android.media.action.VIDEO_CAPTURE";
    public static final String AUTHORITY = "media";
    private static final String CONTENT_AUTHORITY_SLASH = "content://media/";
    public static final String EXTRA_DURATION_LIMIT = "android.intent.extra.durationLimit";
    public static final String EXTRA_FINISH_ON_COMPLETION = "android.intent.extra.finishOnCompletion";
    public static final String EXTRA_FULL_SCREEN = "android.intent.extra.fullScreen";
    public static final String EXTRA_MEDIA_ALBUM = "android.intent.extra.album";
    public static final String EXTRA_MEDIA_ARTIST = "android.intent.extra.artist";
    public static final String EXTRA_MEDIA_FOCUS = "android.intent.extra.focus";
    public static final String EXTRA_MEDIA_GENRE = "android.intent.extra.genre";
    public static final String EXTRA_MEDIA_PLAYLIST = "android.intent.extra.playlist";
    public static final String EXTRA_MEDIA_RADIO_CHANNEL = "android.intent.extra.radio_channel";
    public static final String EXTRA_MEDIA_TITLE = "android.intent.extra.title";
    public static final String EXTRA_OUTPUT = "output";
    public static final String EXTRA_SCREEN_ORIENTATION = "android.intent.extra.screenOrientation";
    public static final String EXTRA_SHOW_ACTION_ICONS = "android.intent.extra.showActionIcons";
    public static final String EXTRA_SIZE_LIMIT = "android.intent.extra.sizeLimit";
    public static final String EXTRA_VIDEO_QUALITY = "android.intent.extra.videoQuality";
    public static final String INTENT_ACTION_MEDIA_PLAY_FROM_SEARCH = "android.media.action.MEDIA_PLAY_FROM_SEARCH";
    public static final String INTENT_ACTION_MEDIA_SEARCH = "android.intent.action.MEDIA_SEARCH";
    public static final String INTENT_ACTION_MUSIC_PLAYER = "android.intent.action.MUSIC_PLAYER";
    public static final String INTENT_ACTION_STILL_IMAGE_CAMERA = "android.media.action.STILL_IMAGE_CAMERA";
    public static final String INTENT_ACTION_STILL_IMAGE_CAMERA_SECURE = "android.media.action.STILL_IMAGE_CAMERA_SECURE";
    public static final String INTENT_ACTION_TEXT_OPEN_FROM_SEARCH = "android.media.action.TEXT_OPEN_FROM_SEARCH";
    public static final String INTENT_ACTION_VIDEO_CAMERA = "android.media.action.VIDEO_CAMERA";
    public static final String INTENT_ACTION_VIDEO_PLAY_FROM_SEARCH = "android.media.action.VIDEO_PLAY_FROM_SEARCH";
    public static final String MEDIA_IGNORE_FILENAME = ".nomedia";
    public static final String MEDIA_SCANNER_VOLUME = "volume";
    public static final String META_DATA_STILL_IMAGE_CAMERA_PREWARM_SERVICE = "android.media.still_image_camera_preview_service";
    public static final String PARAM_DELETE_DATA = "deletedata";
    private static final String TAG = "MediaStore";
    public static final String UNHIDE_CALL = "unhide";
    public static final String UNKNOWN_STRING = "<unknown>";
}
