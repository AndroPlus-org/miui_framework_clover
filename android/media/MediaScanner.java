// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.content.*;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.drm.DrmManagerClient;
import android.graphics.BitmapFactory;
import android.mtp.MtpConstants;
import android.net.Uri;
import android.os.*;
import android.sax.*;
import android.system.*;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import dalvik.system.CloseGuard;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import org.xml.sax.*;

// Referenced classes of package android.media:
//            MediaScannerInjector, MediaFile, MediaInserter, MediaScannerClient, 
//            ExifInterface, RingtoneManager

public class MediaScanner
    implements AutoCloseable
{
    private static class FileEntry
    {

        public String toString()
        {
            return (new StringBuilder()).append(mPath).append(" mRowId: ").append(mRowId).toString();
        }

        int mFormat;
        long mLastModified;
        boolean mLastModifiedChanged;
        String mPath;
        long mRowId;

        FileEntry(long l, String s, long l1, int i)
        {
            mRowId = l;
            mPath = s;
            mLastModified = l1;
            mFormat = i;
            mLastModifiedChanged = false;
        }
    }

    static class MediaBulkDeleter
    {

        public void delete(long l)
            throws RemoteException
        {
            if(whereClause.length() != 0)
                whereClause.append(",");
            whereClause.append("?");
            whereArgs.add((new StringBuilder()).append("").append(l).toString());
            if(whereArgs.size() > 100)
                flush();
        }

        public void flush()
            throws RemoteException
        {
            int i = whereArgs.size();
            if(i > 0)
            {
                String as[] = new String[i];
                as = (String[])whereArgs.toArray(as);
                mProvider.delete(mBaseUri, (new StringBuilder()).append("_id IN (").append(whereClause.toString()).append(")").toString(), as);
                whereClause.setLength(0);
                whereArgs.clear();
            }
        }

        final Uri mBaseUri;
        final ContentProviderClient mProvider;
        ArrayList whereArgs;
        StringBuilder whereClause;

        public MediaBulkDeleter(ContentProviderClient contentproviderclient, Uri uri)
        {
            whereClause = new StringBuilder();
            whereArgs = new ArrayList(100);
            mProvider = contentproviderclient;
            mBaseUri = uri;
        }
    }

    private class MyMediaScannerClient
        implements MediaScannerClient
    {

        private boolean convertGenreCode(String s, String s1)
        {
            String s2 = getGenreName(s);
            if(s2.equals(s1))
            {
                return true;
            } else
            {
                Log.d("MediaScanner", (new StringBuilder()).append("'").append(s).append("' -> '").append(s2).append("', expected '").append(s1).append("'").toString());
                return false;
            }
        }

        private boolean doesPathHaveFilename(String s, String s1)
        {
            boolean flag = false;
            int i = s.lastIndexOf(File.separatorChar) + 1;
            int j = s1.length();
            boolean flag1 = flag;
            if(s.regionMatches(i, s1, 0, j))
            {
                flag1 = flag;
                if(i + j == s.length())
                    flag1 = true;
            }
            return flag1;
        }

        private Uri endFile(FileEntry fileentry, boolean flag, boolean flag1, boolean flag2, boolean flag3, boolean flag4)
            throws RemoteException
        {
            ContentValues contentvalues;
            if(mArtist == null || mArtist.length() == 0)
                mArtist = mAlbumArtist;
            contentvalues = toValues();
            String s = contentvalues.getAsString("title");
            if(s == null || TextUtils.isEmpty(s.trim()))
                contentvalues.put("title", MediaFile.getFileTitle(contentvalues.getAsString("_data")));
            if(!"<unknown>".equals(contentvalues.getAsString("album"))) goto _L2; else goto _L1
_L1:
            Object obj;
            int i;
            obj = contentvalues.getAsString("_data");
            i = ((String) (obj)).lastIndexOf('/');
            if(i < 0) goto _L2; else goto _L3
_L3:
            int l = 0;
_L10:
            int i1 = ((String) (obj)).indexOf('/', l + 1);
            if(i1 >= 0 && i1 < i) goto _L5; else goto _L4
_L4:
            if(l != 0)
                contentvalues.put("album", ((String) (obj)).substring(l + 1, i));
_L2:
            long l1 = fileentry.mRowId;
            if(!MediaFile.isAudioFileType(mFileType) || l1 != 0L && MediaScanner._2D_get15(MediaScanner.this) == 0) goto _L7; else goto _L6
_L6:
            contentvalues.put("is_ringtone", Boolean.valueOf(flag));
            contentvalues.put("is_notification", Boolean.valueOf(flag1));
            contentvalues.put("is_alarm", Boolean.valueOf(flag2));
            contentvalues.put("is_music", Boolean.valueOf(flag3));
            contentvalues.put("is_podcast", Boolean.valueOf(flag4));
_L12:
            Object obj1;
            float af[] = MediaScanner._2D_get11(MediaScanner.this);
            obj1 = MediaScanner._2D_get13(MediaScanner.this);
            obj = af;
            int j;
            long l2;
            if(!mNoMedia)
                if(MediaFile.isVideoFileType(mFileType))
                    obj = MediaScanner._2D_get19(MediaScanner.this);
                else
                if(MediaFile.isImageFileType(mFileType))
                {
                    obj = MediaScanner._2D_get12(MediaScanner.this);
                } else
                {
                    obj = af;
                    if(MediaFile.isAudioFileType(mFileType))
                        obj = MediaScanner._2D_get1(MediaScanner.this);
                }
            af = null;
            l = 0;
            if(!flag1 || !(MediaScanner._2D_get7(MediaScanner.this) ^ true)) goto _L9; else goto _L8
_L8:
label0:
            {
                if(!TextUtils.isEmpty(MediaScanner._2D_get6(MediaScanner.this)))
                {
                    i1 = l;
                    if(!doesPathHaveFilename(fileentry.mPath, MediaScanner._2D_get6(MediaScanner.this)))
                        break label0;
                }
                i1 = 1;
            }
_L22:
            if(l1 == 0L)
            {
                if(MediaScanner._2D_get15(MediaScanner.this) != 0)
                    contentvalues.put("media_scanner_new_object_id", Integer.valueOf(MediaScanner._2D_get15(MediaScanner.this)));
                if(obj == MediaScanner._2D_get11(MediaScanner.this))
                {
                    j = fileentry.mFormat;
                    l = j;
                    if(j == 0)
                        l = MediaFile.getFormatCode(fileentry.mPath, mMimeType);
                    contentvalues.put("format", Integer.valueOf(l));
                }
                if(obj1 == null || i1 != 0)
                {
                    if(obj1 != null)
                        ((MediaInserter) (obj1)).flushAll();
                    af = MediaScanner._2D_get14(MediaScanner.this).insert(((Uri) (obj)), contentvalues);
                } else
                if(fileentry.mFormat == 12289)
                    ((MediaInserter) (obj1)).insertwithPriority(((Uri) (obj)), contentvalues);
                else
                    ((MediaInserter) (obj1)).insert(((Uri) (obj)), contentvalues);
                obj1 = af;
                if(af != null)
                {
                    l1 = ContentUris.parseId(af);
                    fileentry.mRowId = l1;
                    obj1 = af;
                }
            } else
            {
                obj1 = ContentUris.withAppendedId(((Uri) (obj)), l1);
                contentvalues.remove("_data");
                byte byte0 = 0;
                if(!MediaScanner.isNoMediaPath(fileentry.mPath))
                {
                    int k = MediaFile.getFileTypeForMimeType(mMimeType);
                    if(MediaFile.isAudioFileType(k))
                        byte0 = 2;
                    else
                    if(MediaFile.isVideoFileType(k))
                        byte0 = 3;
                    else
                    if(MediaFile.isImageFileType(k))
                        byte0 = 1;
                    else
                    if(MediaFile.isPlayListFileType(k))
                        byte0 = 4;
                    contentvalues.put("media_type", Integer.valueOf(byte0));
                }
                MediaScanner._2D_get14(MediaScanner.this).update(((Uri) (obj1)), contentvalues, null, null);
            }
            if(i1 != 0)
                if(flag1)
                {
                    setRingtoneIfNotSet("notification_sound", ((Uri) (obj)), l1);
                    MediaScanner._2D_set1(MediaScanner.this, true);
                } else
                if(flag)
                {
                    setRingtoneIfNotSet("ringtone", ((Uri) (obj)), l1);
                    MediaScanner._2D_set2(MediaScanner.this, true);
                } else
                if(flag2)
                {
                    setRingtoneIfNotSet("alarm_alert", ((Uri) (obj)), l1);
                    MediaScanner._2D_set0(MediaScanner.this, true);
                }
            return ((Uri) (obj1));
_L5:
            l = i1;
              goto _L10
_L7:
            if(mFileType != 31 && !MediaFile.isRawImageFileType(mFileType) || !(mNoMedia ^ true)) goto _L12; else goto _L11
_L11:
            obj = null;
            af = JVM INSTR new #294 <Class ExifInterface>;
            af.ExifInterface(fileentry.mPath);
            obj = af;
_L27:
            if(obj == null) goto _L12; else goto _L13
_L13:
            af = new float[2];
            if(((ExifInterface) (obj)).getLatLong(af))
            {
                contentvalues.put("latitude", Float.valueOf(af[0]));
                contentvalues.put("longitude", Float.valueOf(af[1]));
            }
            l2 = ((ExifInterface) (obj)).getGpsDateTime();
            if(l2 != -1L)
            {
                contentvalues.put("datetaken", Long.valueOf(l2));
            } else
            {
                l2 = ((ExifInterface) (obj)).getDateTime();
                if(l2 != -1L && Math.abs(mLastModified * 1000L - l2) >= 0x5265c00L)
                    contentvalues.put("datetaken", Long.valueOf(l2));
            }
            l = ((ExifInterface) (obj)).getAttributeInt("Orientation", -1);
            if(l == -1) goto _L12; else goto _L14
_L14:
            l;
            JVM INSTR tableswitch 3 8: default 736
        //                       3 815
        //                       4 736
        //                       5 736
        //                       6 808
        //                       7 736
        //                       8 823;
               goto _L15 _L16 _L15 _L15 _L17 _L15 _L18
_L18:
            break MISSING_BLOCK_LABEL_823;
_L15:
            l = 0;
_L19:
            contentvalues.put("orientation", Integer.valueOf(l));
              goto _L12
_L17:
            l = 90;
              goto _L19
_L16:
            l = 180;
              goto _L19
            l = 270;
              goto _L19
_L9:
            if(!flag || !(MediaScanner._2D_get9(MediaScanner.this) ^ true))
                break MISSING_BLOCK_LABEL_936;
            if(TextUtils.isEmpty(MediaScanner._2D_get8(MediaScanner.this))) goto _L21; else goto _L20
_L20:
            i1 = l;
            if(!doesPathHaveFilename(fileentry.mPath, MediaScanner._2D_get8(MediaScanner.this))) goto _L22; else goto _L21
_L21:
            i1 = 1;
              goto _L22
            i1 = l;
            if(!flag2) goto _L22; else goto _L23
_L23:
            i1 = l;
            if(!(MediaScanner._2D_get5(MediaScanner.this) ^ true)) goto _L22; else goto _L24
_L24:
            if(TextUtils.isEmpty(MediaScanner._2D_get4(MediaScanner.this))) goto _L26; else goto _L25
_L25:
            i1 = l;
            if(!doesPathHaveFilename(fileentry.mPath, MediaScanner._2D_get4(MediaScanner.this))) goto _L22; else goto _L26
_L26:
            i1 = 1;
              goto _L22
            IOException ioexception;
            ioexception;
              goto _L27
        }

        private int getFileTypeFromDrm(String s)
        {
            if(!MediaScanner._2D_wrap0(MediaScanner.this))
                return 0;
            boolean flag = false;
            if(MediaScanner._2D_get10(MediaScanner.this) == null)
                MediaScanner._2D_set3(MediaScanner.this, new DrmManagerClient(MediaScanner._2D_get3(MediaScanner.this)));
            int i = ((flag) ? 1 : 0);
            if(MediaScanner._2D_get10(MediaScanner.this).canHandle(s, null))
            {
                mIsDrm = true;
                s = MediaScanner._2D_get10(MediaScanner.this).getOriginalMimeType(s);
                i = ((flag) ? 1 : 0);
                if(s != null)
                {
                    mMimeType = s;
                    i = MediaFile.getFileTypeForMimeType(s);
                }
            }
            return i;
        }

        private long parseDate(String s)
        {
            long l;
            try
            {
                l = mDateFormatter.parse(s).getTime();
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                return 0L;
            }
            return l;
        }

        private int parseSubstring(String s, int i, int j)
        {
            int k = s.length();
            if(i == k)
                return j;
            int l = i + 1;
            i = s.charAt(i);
            if(i < 48 || i > 57)
                return j;
            j = i - 48;
            for(i = l; i < k; i++)
            {
                char c = s.charAt(i);
                if(c < '0' || c > '9')
                    return j;
                j = j * 10 + (c - 48);
            }

            return j;
        }

        private void processImageFile(String s)
        {
            MediaScanner._2D_get2(MediaScanner.this).outWidth = 0;
            MediaScanner._2D_get2(MediaScanner.this).outHeight = 0;
            BitmapFactory.decodeFile(s, MediaScanner._2D_get2(MediaScanner.this));
            mWidth = MediaScanner._2D_get2(MediaScanner.this).outWidth;
            mHeight = MediaScanner._2D_get2(MediaScanner.this).outHeight;
_L2:
            return;
            s;
            if(true) goto _L2; else goto _L1
_L1:
        }

        private void setRingtoneIfNotSet(String s, Uri uri, long l)
        {
            if(MediaScanner._2D_wrap3(MediaScanner.this, s))
                return;
            ContentResolver contentresolver = MediaScanner._2D_get3(MediaScanner.this).getContentResolver();
            if(TextUtils.isEmpty(android.provider.Settings.System.getString(contentresolver, s)))
            {
                Uri uri1 = android.provider.Settings.System.getUriFor(s);
                uri = ContentUris.withAppendedId(uri, l);
                RingtoneManager.setActualDefaultRingtoneUri(MediaScanner._2D_get3(MediaScanner.this), RingtoneManager.getDefaultType(uri1), uri);
            }
            android.provider.Settings.System.putInt(contentresolver, MediaScanner._2D_wrap4(MediaScanner.this, s), 1);
        }

        private void testGenreNameConverter()
        {
            convertGenreCode("2", "Country");
            convertGenreCode("(2)", "Country");
            convertGenreCode("(2", "(2");
            convertGenreCode("2 Foo", "Country");
            convertGenreCode("(2) Foo", "Country");
            convertGenreCode("(2 Foo", "(2 Foo");
            convertGenreCode("2Foo", "2Foo");
            convertGenreCode("(2)Foo", "Country");
            convertGenreCode("200 Foo", "Foo");
            convertGenreCode("(200) Foo", "Foo");
            convertGenreCode("200Foo", "200Foo");
            convertGenreCode("(200)Foo", "Foo");
            convertGenreCode("200)Foo", "200)Foo");
            convertGenreCode("200) Foo", "200) Foo");
        }

        private ContentValues toValues()
        {
            ContentValues contentvalues;
            String s1;
            contentvalues = new ContentValues();
            contentvalues.put("_data", mPath);
            contentvalues.put("title", mTitle);
            contentvalues.put("date_modified", Long.valueOf(mLastModified));
            contentvalues.put("_size", Long.valueOf(mFileSize));
            contentvalues.put("mime_type", mMimeType);
            contentvalues.put("is_drm", Boolean.valueOf(mIsDrm));
            Object obj = null;
            s1 = obj;
            if(mWidth > 0)
            {
                s1 = obj;
                if(mHeight > 0)
                {
                    contentvalues.put("width", Integer.valueOf(mWidth));
                    contentvalues.put("height", Integer.valueOf(mHeight));
                    s1 = (new StringBuilder()).append(mWidth).append("x").append(mHeight).toString();
                }
            }
            if(mNoMedia) goto _L2; else goto _L1
_L1:
            if(!MediaFile.isVideoFileType(mFileType)) goto _L4; else goto _L3
_L3:
            String s;
            if(mArtist != null && mArtist.length() > 0)
                s = mArtist;
            else
                s = "<unknown>";
            contentvalues.put("artist", s);
            if(mAlbum != null && mAlbum.length() > 0)
                s = mAlbum;
            else
                s = "<unknown>";
            contentvalues.put("album", s);
            contentvalues.put("duration", Integer.valueOf(mDuration));
            if(s1 != null)
                contentvalues.put("resolution", s1);
            if(mDate > 0L)
                contentvalues.put("datetaken", Long.valueOf(mDate));
_L2:
            return contentvalues;
_L4:
            if(!MediaFile.isImageFileType(mFileType) && MediaFile.isAudioFileType(mFileType))
            {
                String s2;
                if(mArtist != null && mArtist.length() > 0)
                    s2 = mArtist;
                else
                    s2 = "<unknown>";
                contentvalues.put("artist", s2);
                if(mAlbumArtist != null && mAlbumArtist.length() > 0)
                    s2 = mAlbumArtist;
                else
                    s2 = null;
                contentvalues.put("album_artist", s2);
                if(mAlbum != null && mAlbum.length() > 0)
                    s2 = mAlbum;
                else
                    s2 = "<unknown>";
                contentvalues.put("album", s2);
                contentvalues.put("composer", mComposer);
                contentvalues.put("genre", mGenre);
                if(mYear != 0)
                    contentvalues.put("year", Integer.valueOf(mYear));
                contentvalues.put("track", Integer.valueOf(mTrack));
                contentvalues.put("duration", Integer.valueOf(mDuration));
                contentvalues.put("compilation", Integer.valueOf(mCompilation));
            }
            if(true) goto _L2; else goto _L5
_L5:
        }

        public FileEntry beginFile(String s, String s1, long l, long l1, boolean flag, 
                boolean flag1)
        {
label0:
            {
                mMimeType = s1;
                mFileType = 0;
                mFileSize = l1;
                mIsDrm = false;
                if(!flag)
                {
                    boolean flag2 = flag1;
                    if(!flag1)
                    {
                        flag2 = flag1;
                        if(MediaScanner._2D_wrap1(s))
                            flag2 = true;
                    }
                    mNoMedia = flag2;
                    if(s1 != null)
                        mFileType = MediaFile.getFileTypeForMimeType(s1);
                    if(mFileType == 0)
                    {
                        s1 = MediaFile.getFileType(s);
                        if(s1 != null)
                        {
                            mFileType = ((MediaFile.MediaFileType) (s1)).fileType;
                            if(mMimeType == null)
                                mMimeType = ((MediaFile.MediaFileType) (s1)).mimeType;
                        }
                    }
                    if(MediaScanner._2D_wrap0(MediaScanner.this) && MediaFile.isDrmFileType(mFileType))
                        mFileType = getFileTypeFromDrm(s);
                }
                FileEntry fileentry = makeEntryFor(s);
                boolean flag3;
                if(fileentry != null)
                    l1 = l - fileentry.mLastModified;
                else
                    l1 = 0L;
                if(l1 > 1L || l1 < -1L)
                    flag3 = true;
                else
                    flag3 = false;
                if(fileentry != null)
                {
                    s1 = fileentry;
                    if(!flag3)
                        break label0;
                }
                if(flag3)
                {
                    fileentry.mLastModified = l;
                    s1 = fileentry;
                } else
                {
                    char c;
                    if(flag)
                        c = '\u3001';
                    else
                        c = '\0';
                    s1 = new FileEntry(0L, s, l, c);
                }
                s1.mLastModifiedChanged = true;
            }
            if(MediaScanner._2D_get18(MediaScanner.this) && MediaFile.isPlayListFileType(mFileType))
            {
                MediaScanner._2D_get16(MediaScanner.this).add(s1);
                return null;
            } else
            {
                mArtist = null;
                mAlbumArtist = null;
                mAlbum = null;
                mTitle = null;
                mComposer = null;
                mGenre = null;
                mTrack = 0;
                mYear = 0;
                mDuration = 0;
                mPath = s;
                mDate = 0L;
                mLastModified = l;
                mWriter = null;
                mCompilation = 0;
                mWidth = 0;
                mHeight = 0;
                return s1;
            }
        }

        public Uri doScanFile(String s, String s1, long l, long l1, boolean flag, 
                boolean flag1, boolean flag2)
        {
            Object obj = null;
            FileEntry fileentry = beginFile(s, s1, l, l1, flag, flag2);
            if(fileentry == null)
                return null;
            if(MediaScanner._2D_get15(MediaScanner.this) != 0)
                fileentry.mRowId = 0L;
            flag = flag1;
            if(fileentry.mPath == null) goto _L2; else goto _L1
_L1:
            if((MediaScanner._2D_get7(MediaScanner.this) || !doesPathHaveFilename(fileentry.mPath, MediaScanner._2D_get6(MediaScanner.this))) && (MediaScanner._2D_get9(MediaScanner.this) || !doesPathHaveFilename(fileentry.mPath, MediaScanner._2D_get8(MediaScanner.this))) && (MediaScanner._2D_get5(MediaScanner.this) || !doesPathHaveFilename(fileentry.mPath, MediaScanner._2D_get4(MediaScanner.this)))) goto _L4; else goto _L3
_L3:
            StringBuilder stringbuilder = JVM INSTR new #83  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.w("MediaScanner", stringbuilder.append("forcing rescan of ").append(fileentry.mPath).append("since ringtone setting didn't finish").toString());
            flag = true;
_L2:
            Object obj1 = obj;
            if(fileentry == null) goto _L6; else goto _L5
_L5:
            if(fileentry.mLastModifiedChanged) goto _L8; else goto _L7
_L7:
            obj1 = obj;
            if(!flag) goto _L6; else goto _L8
_L8:
            if(!flag2) goto _L10; else goto _L9
_L9:
            boolean flag3;
            boolean flag4;
            boolean flag5;
            boolean flag6;
            boolean flag7;
            try
            {
                obj1 = endFile(fileentry, false, false, false, false, false);
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                Log.e("MediaScanner", "RemoteException in MediaScanner.scanFile()", s);
                obj1 = obj;
            }
_L6:
            return ((Uri) (obj1));
_L4:
            flag = flag1;
            if(!MediaScanner._2D_wrap2(fileentry.mPath)) goto _L2; else goto _L11
_L11:
            flag = flag1;
            if(!(Build.FINGERPRINT.equals(MediaScanner._2D_get20()) ^ true)) goto _L2; else goto _L12
_L12:
            obj1 = JVM INSTR new #83  <Class StringBuilder>;
            ((StringBuilder) (obj1)).StringBuilder();
            Log.i("MediaScanner", ((StringBuilder) (obj1)).append("forcing rescan of ").append(fileentry.mPath).append(" since build fingerprint changed").toString());
            flag = true;
              goto _L2
_L10:
            obj1 = s.toLowerCase(Locale.ROOT);
            if(((String) (obj1)).indexOf("/ringtones/") > 0)
                flag1 = true;
            else
                flag1 = false;
            if(((String) (obj1)).indexOf("/notifications/") > 0)
                flag2 = true;
            else
                flag2 = false;
            if(((String) (obj1)).indexOf("/alarms/") > 0)
                flag3 = true;
            else
                flag3 = false;
            if(((String) (obj1)).indexOf("/podcasts/") > 0)
                flag4 = true;
            else
                flag4 = false;
            if(((String) (obj1)).indexOf("/music/") <= 0)
            {
                if(!flag1 && flag2 ^ true && flag3 ^ true)
                    flag = flag4 ^ true;
                else
                    flag = false;
            } else
            {
                flag = true;
            }
            flag5 = MediaFile.isAudioFileType(mFileType);
            flag6 = MediaFile.isVideoFileType(mFileType);
            flag7 = MediaFile.isImageFileType(mFileType);
            if(!flag5 && !flag6)
            {
                obj1 = s;
                if(!flag7)
                    break MISSING_BLOCK_LABEL_478;
            }
            obj1 = JVM INSTR new #107 <Class File>;
            ((File) (obj1)).File(s);
            obj1 = Environment.maybeTranslateEmulatedPathToInternal(((File) (obj1))).getAbsolutePath();
            if(!flag5 && !flag6)
                break MISSING_BLOCK_LABEL_514;
            MediaScannerInjector.processFileBegin(((String) (obj1)), MediaScanner._2D_get3(MediaScanner.this));
            MediaScanner._2D_wrap6(MediaScanner.this, ((String) (obj1)), s1, this);
            MediaScannerInjector.processFileEnd();
            if(!flag7)
                break MISSING_BLOCK_LABEL_525;
            processImageFile(((String) (obj1)));
            obj1 = endFile(fileentry, flag1, flag2, flag3, flag, flag4);
              goto _L6
        }

        public String getGenreName(String s)
        {
            int i;
            if(s == null)
                return null;
            i = s.length();
            if(i <= 0) goto _L2; else goto _L1
_L1:
            boolean flag;
            Object obj;
            int j;
            flag = false;
            obj = new StringBuffer();
            j = 0;
_L4:
            char c;
            if(j >= i)
                break MISSING_BLOCK_LABEL_82;
            c = s.charAt(j);
            if(j != 0 || c != '(')
                break; /* Loop/switch isn't completed */
            flag = true;
_L5:
            j++;
            if(true) goto _L4; else goto _L3
_L3:
            if(!Character.isDigit(c))
                break MISSING_BLOCK_LABEL_82;
            ((StringBuffer) (obj)).append(c);
              goto _L5
            if(true) goto _L4; else goto _L6
            short word0 = Short.parseShort(((StringBuffer) (obj)).toString());
            if(word0 < 0) goto _L2; else goto _L7
_L7:
            if(word0 >= MediaScanner._2D_get0().length || MediaScanner._2D_get0()[word0] == null) goto _L9; else goto _L8
_L8:
            obj = MediaScanner._2D_get0()[word0];
            return ((String) (obj));
_L6:
            if(j < i)
            {
                char c1 = s.charAt(j);
                c = c1;
            } else
            {
                word0 = 32;
                c = word0;
            }
            if(flag && c == ')' || !flag && Character.isWhitespace(c))
                break MISSING_BLOCK_LABEL_111;
_L2:
            return s;
_L9:
            if(word0 == 255)
                return null;
            if(word0 >= 255 || j + 1 >= i)
                break MISSING_BLOCK_LABEL_249;
            i = j;
            if(flag)
            {
                i = j;
                if(c == ')')
                    i = j + 1;
            }
            obj = s.substring(i).trim();
            if(((String) (obj)).length() != 0)
                return ((String) (obj));
            continue; /* Loop/switch isn't completed */
            obj = ((StringBuffer) (obj)).toString();
            return ((String) (obj));
            NumberFormatException numberformatexception;
            numberformatexception;
            if(true) goto _L2; else goto _L10
_L10:
        }

        public void handleStringTag(String s, String s1)
        {
            boolean flag = true;
            if(!s.equalsIgnoreCase("title") && !s.startsWith("title;")) goto _L2; else goto _L1
_L1:
            mTitle = s1;
_L4:
            return;
_L2:
            if(s.equalsIgnoreCase("artist") || s.startsWith("artist;"))
                mArtist = s1.trim();
            else
            if(s.equalsIgnoreCase("albumartist") || s.startsWith("albumartist;") || s.equalsIgnoreCase("band") || s.startsWith("band;"))
                mAlbumArtist = s1.trim();
            else
            if(s.equalsIgnoreCase("album") || s.startsWith("album;"))
                mAlbum = s1.trim();
            else
            if(s.equalsIgnoreCase("composer") || s.startsWith("composer;"))
                mComposer = s1.trim();
            else
            if(MediaScanner._2D_get17(MediaScanner.this) && (s.equalsIgnoreCase("genre") || s.startsWith("genre;")))
                mGenre = getGenreName(s1);
            else
            if(s.equalsIgnoreCase("year") || s.startsWith("year;"))
                mYear = parseSubstring(s1, 0, 0);
            else
            if(s.equalsIgnoreCase("tracknumber") || s.startsWith("tracknumber;"))
            {
                int i = parseSubstring(s1, 0, 0);
                mTrack = (mTrack / 1000) * 1000 + i;
            } else
            if(s.equalsIgnoreCase("discnumber") || s.equals("set") || s.startsWith("set;"))
                mTrack = parseSubstring(s1, 0, 0) * 1000 + mTrack % 1000;
            else
            if(s.equalsIgnoreCase("duration"))
                mDuration = parseSubstring(s1, 0, 0);
            else
            if(s.equalsIgnoreCase("writer") || s.startsWith("writer;"))
                mWriter = s1.trim();
            else
            if(s.equalsIgnoreCase("compilation"))
                mCompilation = parseSubstring(s1, 0, 0);
            else
            if(s.equalsIgnoreCase("isdrm"))
            {
                if(parseSubstring(s1, 0, 0) != 1)
                    flag = false;
                mIsDrm = flag;
            } else
            if(s.equalsIgnoreCase("date"))
                mDate = parseDate(s1);
            else
            if(s.equalsIgnoreCase("width"))
                mWidth = parseSubstring(s1, 0, 0);
            else
            if(s.equalsIgnoreCase("height"))
                mHeight = parseSubstring(s1, 0, 0);
            if(true) goto _L4; else goto _L3
_L3:
        }

        public void scanFile(String s, long l, long l1, boolean flag, boolean flag1)
        {
            doScanFile(s, null, l, l1, flag, false, flag1);
        }

        public void setMimeType(String s)
        {
            if("audio/mp4".equals(mMimeType) && s.startsWith("video"))
                return;
            if(MediaScannerInjector.keepMimeType(mMimeType, s))
            {
                return;
            } else
            {
                mMimeType = s;
                mFileType = MediaFile.getFileTypeForMimeType(s);
                return;
            }
        }

        private String mAlbum;
        private String mAlbumArtist;
        private String mArtist;
        private int mCompilation;
        private String mComposer;
        private long mDate;
        private final SimpleDateFormat mDateFormatter = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
        private int mDuration;
        private long mFileSize;
        private int mFileType;
        private String mGenre;
        private int mHeight;
        private boolean mIsDrm;
        private long mLastModified;
        private String mMimeType;
        private boolean mNoMedia;
        private String mPath;
        private String mTitle;
        private int mTrack;
        private int mWidth;
        private String mWriter;
        private int mYear;
        final MediaScanner this$0;

        public MyMediaScannerClient()
        {
            this$0 = MediaScanner.this;
            super();
            mDateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        }
    }

    private static class PlaylistEntry
    {

        long bestmatchid;
        int bestmatchlevel;
        String path;

        private PlaylistEntry()
        {
        }

        PlaylistEntry(PlaylistEntry playlistentry)
        {
            this();
        }
    }

    class WplHandler
        implements ElementListener
    {

        public void end()
        {
        }

        ContentHandler getContentHandler()
        {
            return handler;
        }

        public void start(Attributes attributes)
        {
            attributes = attributes.getValue("", "src");
            if(attributes != null)
                MediaScanner._2D_wrap5(MediaScanner.this, attributes, playListDirectory);
        }

        final ContentHandler handler;
        String playListDirectory;
        final MediaScanner this$0;

        public WplHandler(String s, Uri uri, Cursor cursor)
        {
            this$0 = MediaScanner.this;
            super();
            playListDirectory = s;
            mediascanner = new RootElement("smil");
            getChild("body").getChild("seq").getChild("media").setElementListener(this);
            handler = RootElement.this.getContentHandler();
        }
    }


    static String[] _2D_get0()
    {
        return ID3_GENRES;
    }

    static Uri _2D_get1(MediaScanner mediascanner)
    {
        return mediascanner.mAudioUri;
    }

    static DrmManagerClient _2D_get10(MediaScanner mediascanner)
    {
        return mediascanner.mDrmManagerClient;
    }

    static Uri _2D_get11(MediaScanner mediascanner)
    {
        return mediascanner.mFilesUri;
    }

    static Uri _2D_get12(MediaScanner mediascanner)
    {
        return mediascanner.mImagesUri;
    }

    static MediaInserter _2D_get13(MediaScanner mediascanner)
    {
        return mediascanner.mMediaInserter;
    }

    static ContentProviderClient _2D_get14(MediaScanner mediascanner)
    {
        return mediascanner.mMediaProvider;
    }

    static int _2D_get15(MediaScanner mediascanner)
    {
        return mediascanner.mMtpObjectHandle;
    }

    static ArrayList _2D_get16(MediaScanner mediascanner)
    {
        return mediascanner.mPlayLists;
    }

    static boolean _2D_get17(MediaScanner mediascanner)
    {
        return mediascanner.mProcessGenres;
    }

    static boolean _2D_get18(MediaScanner mediascanner)
    {
        return mediascanner.mProcessPlaylists;
    }

    static Uri _2D_get19(MediaScanner mediascanner)
    {
        return mediascanner.mVideoUri;
    }

    static android.graphics.BitmapFactory.Options _2D_get2(MediaScanner mediascanner)
    {
        return mediascanner.mBitmapOptions;
    }

    static String _2D_get20()
    {
        return sLastInternalScanFingerprint;
    }

    static Context _2D_get3(MediaScanner mediascanner)
    {
        return mediascanner.mContext;
    }

    static String _2D_get4(MediaScanner mediascanner)
    {
        return mediascanner.mDefaultAlarmAlertFilename;
    }

    static boolean _2D_get5(MediaScanner mediascanner)
    {
        return mediascanner.mDefaultAlarmSet;
    }

    static String _2D_get6(MediaScanner mediascanner)
    {
        return mediascanner.mDefaultNotificationFilename;
    }

    static boolean _2D_get7(MediaScanner mediascanner)
    {
        return mediascanner.mDefaultNotificationSet;
    }

    static String _2D_get8(MediaScanner mediascanner)
    {
        return mediascanner.mDefaultRingtoneFilename;
    }

    static boolean _2D_get9(MediaScanner mediascanner)
    {
        return mediascanner.mDefaultRingtoneSet;
    }

    static boolean _2D_set0(MediaScanner mediascanner, boolean flag)
    {
        mediascanner.mDefaultAlarmSet = flag;
        return flag;
    }

    static boolean _2D_set1(MediaScanner mediascanner, boolean flag)
    {
        mediascanner.mDefaultNotificationSet = flag;
        return flag;
    }

    static boolean _2D_set2(MediaScanner mediascanner, boolean flag)
    {
        mediascanner.mDefaultRingtoneSet = flag;
        return flag;
    }

    static DrmManagerClient _2D_set3(MediaScanner mediascanner, DrmManagerClient drmmanagerclient)
    {
        mediascanner.mDrmManagerClient = drmmanagerclient;
        return drmmanagerclient;
    }

    static boolean _2D_wrap0(MediaScanner mediascanner)
    {
        return mediascanner.isDrmEnabled();
    }

    static boolean _2D_wrap1(String s)
    {
        return isNoMediaFile(s);
    }

    static boolean _2D_wrap2(String s)
    {
        return isSystemSoundWithMetadata(s);
    }

    static boolean _2D_wrap3(MediaScanner mediascanner, String s)
    {
        return mediascanner.wasRingtoneAlreadySet(s);
    }

    static String _2D_wrap4(MediaScanner mediascanner, String s)
    {
        return mediascanner.settingSetIndicatorName(s);
    }

    static void _2D_wrap5(MediaScanner mediascanner, String s, String s1)
    {
        mediascanner.cachePlaylistEntry(s, s1);
    }

    static void _2D_wrap6(MediaScanner mediascanner, String s, String s1, MediaScannerClient mediascannerclient)
    {
        mediascanner.processFile(s, s1, mediascannerclient);
    }

    public MediaScanner(Context context, String s)
    {
        mDrmManagerClient = null;
        native_setup();
        mContext = context;
        mPackageName = context.getPackageName();
        mVolumeName = s;
        mBitmapOptions.inSampleSize = 1;
        mBitmapOptions.inJustDecodeBounds = true;
        setDefaultRingtoneFileNames();
        mMediaProvider = mContext.getContentResolver().acquireContentProviderClient("media");
        if(sLastInternalScanFingerprint == null)
            sLastInternalScanFingerprint = mContext.getSharedPreferences("MediaScanBuild", 0).getString("lastScanFingerprint", new String());
        mAudioUri = android.provider.MediaStore.Audio.Media.getContentUri(s);
        mVideoUri = android.provider.MediaStore.Video.Media.getContentUri(s);
        mImagesUri = android.provider.MediaStore.Images.Media.getContentUri(s);
        mThumbsUri = android.provider.MediaStore.Images.Thumbnails.getContentUri(s);
        mFilesUri = android.provider.MediaStore.Files.getContentUri(s);
        mFilesUriNoNotify = mFilesUri.buildUpon().appendQueryParameter("nonotify", "1").build();
        if(!s.equals("internal"))
        {
            mProcessPlaylists = true;
            mProcessGenres = true;
            mPlaylistsUri = android.provider.MediaStore.Audio.Playlists.getContentUri(s);
        } else
        {
            mProcessPlaylists = false;
            mProcessGenres = false;
            mPlaylistsUri = null;
        }
        s = mContext.getResources().getConfiguration().locale;
        if(s != null)
        {
            context = s.getLanguage();
            s = s.getCountry();
            if(context != null)
                if(s != null)
                    setLocale((new StringBuilder()).append(context).append("_").append(s).toString());
                else
                    setLocale(context);
        }
        mCloseGuard.open("close");
        MediaScannerInjector.initMediaFileCapture("media.extractor");
    }

    private void cachePlaylistEntry(String s, String s1)
    {
        PlaylistEntry playlistentry = new PlaylistEntry(null);
        int i;
        for(i = s.length(); i > 0 && Character.isWhitespace(s.charAt(i - 1)); i--);
        if(i < 3)
            return;
        String s2 = s;
        if(i < s.length())
            s2 = s.substring(0, i);
        char c = s2.charAt(0);
        boolean flag;
        if(c != '/')
        {
            if(Character.isLetter(c) && s2.charAt(1) == ':' && s2.charAt(2) == '\\')
                flag = true;
            else
                flag = false;
        } else
        {
            flag = true;
        }
        s = s2;
        if(!flag)
            s = (new StringBuilder()).append(s1).append(s2).toString();
        playlistentry.path = s;
        mPlaylistEntries.add(playlistentry);
    }

    public static void clearMediaPathCache(boolean flag, boolean flag1)
    {
        android/media/MediaScanner;
        JVM INSTR monitorenter ;
        if(!flag)
            break MISSING_BLOCK_LABEL_13;
        mMediaPaths.clear();
        if(!flag1)
            break MISSING_BLOCK_LABEL_23;
        mNoMediaPaths.clear();
        android/media/MediaScanner;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private boolean isDrmEnabled()
    {
        String s = SystemProperties.get("drm.service.enabled");
        boolean flag;
        if(s != null)
            flag = s.equals("true");
        else
            flag = false;
        return flag;
    }

    private static boolean isNoMediaFile(String s)
    {
        if((new File(s)).isDirectory())
            return false;
        int i = s.lastIndexOf('/');
        if(i >= 0 && i + 2 < s.length())
        {
            if(s.regionMatches(i + 1, "._", 0, 2))
                return true;
            if(s.regionMatches(true, s.length() - 4, ".jpg", 0, 4))
            {
                if(s.regionMatches(true, i + 1, "AlbumArt_{", 0, 10) || s.regionMatches(true, i + 1, "AlbumArt.", 0, 9))
                    return true;
                int j = s.length() - i - 1;
                if(j == 17 && s.regionMatches(true, i + 1, "AlbumArtSmall", 0, 13) || j == 10 && s.regionMatches(true, i + 1, "Folder", 0, 6))
                    return true;
            }
        }
        return false;
    }

    public static boolean isNoMediaPath(String s)
    {
        int i;
        if(s == null)
            return false;
        if(s.indexOf("/.") >= 0)
            return true;
        i = s.lastIndexOf('/');
        if(i <= 0)
            return false;
        String s1 = s.substring(0, i);
        android/media/MediaScanner;
        JVM INSTR monitorenter ;
        boolean flag = mNoMediaPaths.containsKey(s1);
        if(!flag)
            break MISSING_BLOCK_LABEL_58;
        android/media/MediaScanner;
        JVM INSTR monitorexit ;
        return true;
        if(mMediaPaths.containsKey(s1))
            break MISSING_BLOCK_LABEL_182;
        int j = 1;
_L3:
        if(j < 0) goto _L2; else goto _L1
_L1:
        int k = s.indexOf('/', j);
        i = k;
        if(k <= j)
            continue; /* Loop/switch isn't completed */
        i = k + 1;
        File file = JVM INSTR new #793 <Class File>;
        StringBuilder stringbuilder = JVM INSTR new #723 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        file.File(stringbuilder.append(s.substring(0, i)).append(".nomedia").toString());
        if(!file.exists())
            continue; /* Loop/switch isn't completed */
        mNoMediaPaths.put(s1, "");
        android/media/MediaScanner;
        JVM INSTR monitorexit ;
        return true;
          goto _L3
_L2:
        mMediaPaths.put(s1, "");
        android/media/MediaScanner;
        JVM INSTR monitorexit ;
        return isNoMediaFile(s);
        s;
        throw s;
    }

    private static boolean isSystemSoundWithMetadata(String s)
    {
        return s.startsWith("/system/media/audio/alarms/") || s.startsWith("/system/media/audio/ringtones/") || s.startsWith("/system/media/audio/notifications/");
    }

    private boolean matchEntries(long l, String s)
    {
        int i = mPlaylistEntries.size();
        boolean flag = true;
        int j = 0;
        while(j < i) 
        {
            PlaylistEntry playlistentry = (PlaylistEntry)mPlaylistEntries.get(j);
            if(playlistentry.bestmatchlevel != 0x7fffffff)
            {
                boolean flag1 = false;
                if(s.equalsIgnoreCase(playlistentry.path))
                {
                    playlistentry.bestmatchid = l;
                    playlistentry.bestmatchlevel = 0x7fffffff;
                    flag = flag1;
                } else
                {
                    int k = matchPaths(s, playlistentry.path);
                    flag = flag1;
                    if(k > playlistentry.bestmatchlevel)
                    {
                        playlistentry.bestmatchid = l;
                        playlistentry.bestmatchlevel = k;
                        flag = flag1;
                    }
                }
            }
            j++;
        }
        return flag;
    }

    private int matchPaths(String s, String s1)
    {
        int i = 0;
        int j = s.length();
        int k = s1.length();
        do
        {
            int i1;
            int j1;
            if(j > 0 && k > 0)
            {
                int l = s.lastIndexOf('/', j - 1);
                i1 = s1.lastIndexOf('/', k - 1);
                j1 = s.lastIndexOf('\\', j - 1);
                int k1 = s1.lastIndexOf('\\', k - 1);
                if(l > j1)
                    j1 = l;
                if(i1 <= k1)
                    i1 = k1;
                if(j1 < 0)
                    j1 = 0;
                else
                    j1++;
                if(i1 < 0)
                    i1 = 0;
                else
                    i1++;
                j -= j1;
                break MISSING_BLOCK_LABEL_113;
            }
            do
                return i;
            while(k - i1 != j || !s.regionMatches(true, j1, s1, i1, j));
            i++;
            j = j1 - 1;
            k = i1 - 1;
        } while(true);
    }

    private final native void native_finalize();

    private static final native void native_init();

    private final native void native_setup();

    private void postscan(String as[])
        throws RemoteException
    {
        if(mProcessPlaylists)
            processPlayLists();
        if(mOriginalCount == 0 && mImagesUri.equals(android.provider.MediaStore.Images.Media.getContentUri("external")))
            pruneDeadThumbnailFiles();
        mPlayLists.clear();
    }

    private void prescan(String s, boolean flag)
        throws RemoteException
    {
        Object obj;
        Object obj1;
        MediaBulkDeleter mediabulkdeleter;
        long l;
        obj = null;
        Uri uri = null;
        obj1 = null;
        mPlayLists.clear();
        String s1;
        String as[];
        if(s != null)
        {
            s1 = "_id>? AND _data=?";
            as = new String[2];
            as[0] = "";
            as[1] = s;
        } else
        {
            s1 = "_id>?";
            as = new String[1];
            as[0] = "";
        }
        mDefaultRingtoneSet = wasRingtoneAlreadySet("ringtone");
        mDefaultNotificationSet = wasRingtoneAlreadySet("notification_sound");
        mDefaultAlarmSet = wasRingtoneAlreadySet("alarm_alert");
        s = mFilesUri.buildUpon();
        s.appendQueryParameter("deletedata", "false");
        mediabulkdeleter = new MediaBulkDeleter(mMediaProvider, s.build());
        if(!flag) goto _L2; else goto _L1
_L1:
        l = 0x8000000000000000L;
        s = uri;
        uri = mFilesUri.buildUpon().appendQueryParameter("limit", "1000").build();
        obj = obj1;
_L7:
        s = ((String) (obj));
        obj1 = JVM INSTR new #723 <Class StringBuilder>;
        s = ((String) (obj));
        ((StringBuilder) (obj1)).StringBuilder();
        s = ((String) (obj));
        as[0] = ((StringBuilder) (obj1)).append("").append(l).toString();
        s = ((String) (obj));
        if(obj == null)
            break MISSING_BLOCK_LABEL_199;
        s = ((String) (obj));
        ((Cursor) (obj)).close();
        s = null;
        obj1 = mMediaProvider.query(uri, FILES_PRESCAN_PROJECTION, s1, as, "_id", null);
        if(obj1 != null) goto _L4; else goto _L3
_L3:
        obj = obj1;
_L2:
        if(obj != null)
            ((Cursor) (obj)).close();
        mediabulkdeleter.flush();
        mOriginalCount = 0;
        s = mMediaProvider.query(mImagesUri, ID_PROJECTION, null, null, null, null);
        if(s != null)
        {
            mOriginalCount = s.getCount();
            s.close();
        }
        return;
_L4:
        obj = obj1;
        s = ((String) (obj1));
        if(((Cursor) (obj1)).getCount() == 0) goto _L2; else goto _L5
_L5:
        obj = obj1;
        s = ((String) (obj1));
        if(!((Cursor) (obj1)).moveToNext()) goto _L7; else goto _L6
_L6:
        s = ((String) (obj1));
        long l1 = ((Cursor) (obj1)).getLong(0);
        s = ((String) (obj1));
        obj = ((Cursor) (obj1)).getString(1);
        s = ((String) (obj1));
        int i = ((Cursor) (obj1)).getInt(2);
        s = ((String) (obj1));
        ((Cursor) (obj1)).getLong(3);
        long l2;
        l2 = l1;
        l = l2;
        if(obj == null) goto _L5; else goto _L8
_L8:
        s = ((String) (obj1));
        flag = ((String) (obj)).startsWith("/");
        l = l2;
        if(!flag) goto _L5; else goto _L9
_L9:
        flag = false;
        s = ((String) (obj1));
        boolean flag1 = Os.access(((String) (obj)), OsConstants.F_OK);
        flag = flag1;
_L17:
        l = l2;
        if(flag) goto _L5; else goto _L10
_L10:
        l = l2;
        s = ((String) (obj1));
        if(!(MtpConstants.isAbstractObject(i) ^ true)) goto _L5; else goto _L11
_L11:
        s = ((String) (obj1));
        Object obj2 = MediaFile.getFileType(((String) (obj)));
        if(obj2 != null) goto _L13; else goto _L12
_L12:
        i = 0;
_L16:
        l = l2;
        s = ((String) (obj1));
        if(MediaFile.isPlayListFileType(i)) goto _L5; else goto _L14
_L14:
        s = ((String) (obj1));
        mediabulkdeleter.delete(l1);
        l = l2;
        s = ((String) (obj1));
        if(!((String) (obj)).toLowerCase(Locale.US).endsWith("/.nomedia")) goto _L5; else goto _L15
_L15:
        s = ((String) (obj1));
        mediabulkdeleter.flush();
        s = ((String) (obj1));
        obj2 = JVM INSTR new #793 <Class File>;
        s = ((String) (obj1));
        ((File) (obj2)).File(((String) (obj)));
        s = ((String) (obj1));
        obj = ((File) (obj2)).getParent();
        s = ((String) (obj1));
        mMediaProvider.call("unhide", ((String) (obj)), null);
        l = l2;
          goto _L5
        obj1;
        if(s != null)
            s.close();
        mediabulkdeleter.flush();
        throw obj1;
_L13:
        s = ((String) (obj1));
        i = ((MediaFile.MediaFileType) (obj2)).fileType;
          goto _L16
        s;
          goto _L17
    }

    private void processCachedPlaylist(Cursor cursor, ContentValues contentvalues, Uri uri)
    {
        cursor.moveToPosition(-1);
        while(cursor.moveToNext() && !matchEntries(cursor.getLong(0), cursor.getString(1))) ;
        int i = mPlaylistEntries.size();
        int j = 0;
        int k = 0;
        do
        {
            if(k >= i)
                break;
            cursor = (PlaylistEntry)mPlaylistEntries.get(k);
            int l = j;
            if(((PlaylistEntry) (cursor)).bestmatchlevel > 0)
            {
                try
                {
                    contentvalues.clear();
                    contentvalues.put("play_order", Integer.valueOf(j));
                    contentvalues.put("audio_id", Long.valueOf(((PlaylistEntry) (cursor)).bestmatchid));
                    mMediaProvider.insert(uri, contentvalues);
                }
                // Misplaced declaration of an exception variable
                catch(Cursor cursor)
                {
                    Log.e("MediaScanner", "RemoteException in MediaScanner.processCachedPlaylist()", cursor);
                    return;
                }
                l = j + 1;
            }
            k++;
            j = l;
        } while(true);
        mPlaylistEntries.clear();
    }

    private native void processDirectory(String s, MediaScannerClient mediascannerclient);

    private native void processFile(String s, String s1, MediaScannerClient mediascannerclient);

    private void processM3uPlayList(String s, String s1, Uri uri, ContentValues contentvalues, Cursor cursor)
    {
        Object obj;
        Object obj1;
        FileInputStream fileinputstream;
        String s2;
        obj = null;
        obj1 = null;
        fileinputstream = null;
        s2 = obj1;
        File file = JVM INSTR new #793 <Class File>;
        s2 = obj1;
        file.File(s);
        s = fileinputstream;
        s2 = obj1;
        if(!file.exists())
            break MISSING_BLOCK_LABEL_166;
        s2 = obj1;
        s = JVM INSTR new #1064 <Class BufferedReader>;
        s2 = obj1;
        InputStreamReader inputstreamreader = JVM INSTR new #1066 <Class InputStreamReader>;
        s2 = obj1;
        fileinputstream = JVM INSTR new #1068 <Class FileInputStream>;
        s2 = obj1;
        fileinputstream.FileInputStream(file);
        s2 = obj1;
        inputstreamreader.InputStreamReader(fileinputstream);
        s2 = obj1;
        s.BufferedReader(inputstreamreader, 8192);
        s2 = s.readLine();
        mPlaylistEntries.clear();
_L1:
        if(s2 == null)
            break MISSING_BLOCK_LABEL_157;
        if(s2.length() > 0 && s2.charAt(0) != '#')
            cachePlaylistEntry(s2, s1);
        s2 = s.readLine();
          goto _L1
        processCachedPlaylist(cursor, contentvalues, uri);
        if(s == null)
            break MISSING_BLOCK_LABEL_174;
        s.close();
_L2:
        return;
        s;
        Log.e("MediaScanner", "IOException in MediaScanner.processM3uPlayList()", s);
          goto _L2
        s1;
        s = obj;
_L5:
        s2 = s;
        Log.e("MediaScanner", "IOException in MediaScanner.processM3uPlayList()", s1);
        if(s != null)
            try
            {
                s.close();
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                Log.e("MediaScanner", "IOException in MediaScanner.processM3uPlayList()", s);
            }
          goto _L2
        s;
_L4:
        if(s2 != null)
            try
            {
                s2.close();
            }
            // Misplaced declaration of an exception variable
            catch(String s1)
            {
                Log.e("MediaScanner", "IOException in MediaScanner.processM3uPlayList()", s1);
            }
        throw s;
        s1;
        s2 = s;
        s = s1;
        if(true) goto _L4; else goto _L3
_L3:
        s1;
          goto _L5
    }

    private void processPlayList(FileEntry fileentry, Cursor cursor)
        throws RemoteException
    {
        String s;
        ContentValues contentvalues;
        int i;
        String s1;
        s = fileentry.mPath;
        contentvalues = new ContentValues();
        i = s.lastIndexOf('/');
        if(i < 0)
            throw new IllegalArgumentException((new StringBuilder()).append("bad path ").append(s).toString());
        long l = fileentry.mRowId;
        s1 = contentvalues.getAsString("name");
        Object obj = s1;
        if(s1 == null)
        {
            s1 = contentvalues.getAsString("title");
            obj = s1;
            if(s1 == null)
            {
                int j = s.lastIndexOf('.');
                if(j < 0)
                    obj = s.substring(i + 1);
                else
                    obj = s.substring(i + 1, j);
            }
        }
        contentvalues.put("name", ((String) (obj)));
        contentvalues.put("date_modified", Long.valueOf(fileentry.mLastModified));
        if(l == 0L)
        {
            contentvalues.put("_data", s);
            fileentry = mMediaProvider.insert(mPlaylistsUri, contentvalues);
            ContentUris.parseId(fileentry);
            fileentry = Uri.withAppendedPath(fileentry, "members");
        } else
        {
            fileentry = ContentUris.withAppendedId(mPlaylistsUri, l);
            mMediaProvider.update(fileentry, contentvalues, null, null);
            fileentry = Uri.withAppendedPath(fileentry, "members");
            mMediaProvider.delete(fileentry, null, null);
        }
        s1 = s.substring(0, i + 1);
        obj = MediaFile.getFileType(s);
        if(obj == null)
            i = 0;
        else
            i = ((MediaFile.MediaFileType) (obj)).fileType;
        if(i != 41) goto _L2; else goto _L1
_L1:
        processM3uPlayList(s, s1, fileentry, contentvalues, cursor);
_L4:
        return;
_L2:
        if(i == 42)
            processPlsPlayList(s, s1, fileentry, contentvalues, cursor);
        else
        if(i == 43)
            processWplPlayList(s, s1, fileentry, contentvalues, cursor);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void processPlayLists()
        throws RemoteException
    {
        Iterator iterator;
        Object obj;
        Cursor cursor;
        iterator = mPlayLists.iterator();
        obj = null;
        cursor = null;
        Cursor cursor1 = mMediaProvider.query(mFilesUri, FILES_PRESCAN_PROJECTION, "media_type=2", null, null, null);
_L4:
        cursor = cursor1;
        obj = cursor1;
        if(!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        cursor = cursor1;
        obj = cursor1;
        FileEntry fileentry = (FileEntry)iterator.next();
        cursor = cursor1;
        obj = cursor1;
        if(!fileentry.mLastModifiedChanged) goto _L4; else goto _L3
_L3:
        cursor = cursor1;
        obj = cursor1;
        processPlayList(fileentry, cursor1);
          goto _L4
        obj;
        if(cursor != null)
            cursor.close();
_L6:
        return;
_L2:
        if(cursor1 != null)
            cursor1.close();
        if(true) goto _L6; else goto _L5
_L5:
        Exception exception;
        exception;
        if(obj != null)
            ((Cursor) (obj)).close();
        throw exception;
    }

    private void processPlsPlayList(String s, String s1, Uri uri, ContentValues contentvalues, Cursor cursor)
    {
        Object obj;
        Object obj1;
        FileInputStream fileinputstream;
        String s2;
        obj = null;
        obj1 = null;
        fileinputstream = null;
        s2 = obj1;
        File file = JVM INSTR new #793 <Class File>;
        s2 = obj1;
        file.File(s);
        s = fileinputstream;
        s2 = obj1;
        if(!file.exists())
            break MISSING_BLOCK_LABEL_179;
        s2 = obj1;
        s = JVM INSTR new #1064 <Class BufferedReader>;
        s2 = obj1;
        InputStreamReader inputstreamreader = JVM INSTR new #1066 <Class InputStreamReader>;
        s2 = obj1;
        fileinputstream = JVM INSTR new #1068 <Class FileInputStream>;
        s2 = obj1;
        fileinputstream.FileInputStream(file);
        s2 = obj1;
        inputstreamreader.InputStreamReader(fileinputstream);
        s2 = obj1;
        s.BufferedReader(inputstreamreader, 8192);
        s2 = s.readLine();
        mPlaylistEntries.clear();
_L1:
        if(s2 == null)
            break MISSING_BLOCK_LABEL_170;
        int i;
        if(!s2.startsWith("File"))
            break MISSING_BLOCK_LABEL_161;
        i = s2.indexOf('=');
        if(i <= 0)
            break MISSING_BLOCK_LABEL_161;
        cachePlaylistEntry(s2.substring(i + 1), s1);
        s2 = s.readLine();
          goto _L1
        processCachedPlaylist(cursor, contentvalues, uri);
        if(s == null)
            break MISSING_BLOCK_LABEL_187;
        s.close();
_L2:
        return;
        s;
        Log.e("MediaScanner", "IOException in MediaScanner.processPlsPlayList()", s);
          goto _L2
        s1;
        s = obj;
_L5:
        s2 = s;
        Log.e("MediaScanner", "IOException in MediaScanner.processPlsPlayList()", s1);
        if(s != null)
            try
            {
                s.close();
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                Log.e("MediaScanner", "IOException in MediaScanner.processPlsPlayList()", s);
            }
          goto _L2
        s1;
_L4:
        if(s2 != null)
            try
            {
                s2.close();
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                Log.e("MediaScanner", "IOException in MediaScanner.processPlsPlayList()", s);
            }
        throw s1;
        s1;
        s2 = s;
        if(true) goto _L4; else goto _L3
_L3:
        s1;
          goto _L5
    }

    private void processWplPlayList(String s, String s1, Uri uri, ContentValues contentvalues, Cursor cursor)
    {
        WplHandler wplhandler;
        Object obj;
        Object obj1;
        Object obj2;
        Object obj3;
        wplhandler = null;
        obj = null;
        obj1 = null;
        obj2 = null;
        obj3 = obj1;
        File file = JVM INSTR new #793 <Class File>;
        obj3 = obj1;
        file.File(s);
        s = obj2;
        obj3 = obj1;
        if(!file.exists())
            break MISSING_BLOCK_LABEL_114;
        obj3 = obj1;
        s = JVM INSTR new #1068 <Class FileInputStream>;
        obj3 = obj1;
        s.FileInputStream(file);
        mPlaylistEntries.clear();
        obj3 = Xml.findEncodingByName("UTF-8");
        wplhandler = JVM INSTR new #20  <Class MediaScanner$WplHandler>;
        wplhandler.this. WplHandler(s1, uri, cursor);
        Xml.parse(s, ((android.util.Xml.Encoding) (obj3)), wplhandler.getContentHandler());
        processCachedPlaylist(cursor, contentvalues, uri);
        if(s == null)
            break MISSING_BLOCK_LABEL_122;
        s.close();
_L1:
        return;
        s;
        Log.e("MediaScanner", "IOException in MediaScanner.processWplPlayList()", s);
          goto _L1
        s1;
        s = wplhandler;
_L5:
        obj3 = s;
        s1.printStackTrace();
        if(s != null)
            try
            {
                s.close();
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                Log.e("MediaScanner", "IOException in MediaScanner.processWplPlayList()", s);
            }
          goto _L1
        s1;
        s = obj;
_L4:
        obj3 = s;
        s1.printStackTrace();
        if(s != null)
            try
            {
                s.close();
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                Log.e("MediaScanner", "IOException in MediaScanner.processWplPlayList()", s);
            }
          goto _L1
        s1;
_L3:
        if(obj3 != null)
            try
            {
                ((FileInputStream) (obj3)).close();
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                Log.e("MediaScanner", "IOException in MediaScanner.processWplPlayList()", s);
            }
        throw s1;
        s1;
        obj3 = s;
        if(true) goto _L3; else goto _L2
_L2:
        s1;
          goto _L4
        s1;
          goto _L5
    }

    private void pruneDeadThumbnailFiles()
    {
        Object obj;
        Object obj2;
        Object obj4;
        obj = new HashSet();
        String as[] = (new File("/sdcard/DCIM/.thumbnails")).list();
        obj2 = null;
        Object obj3 = null;
        String as1[] = as;
        if(as == null)
            as1 = new String[0];
        for(int i = 0; i < as1.length; i++)
            ((HashSet) (obj)).add((new StringBuilder()).append("/sdcard/DCIM/.thumbnails").append("/").append(as1[i]).toString());

        obj4 = obj3;
        Cursor cursor = mMediaProvider.query(mThumbsUri, new String[] {
            "_data"
        }, null, null, null, null);
        obj4 = cursor;
        obj2 = cursor;
        Object obj1 = JVM INSTR new #723 <Class StringBuilder>;
        obj4 = cursor;
        obj2 = cursor;
        ((StringBuilder) (obj1)).StringBuilder();
        obj4 = cursor;
        obj2 = cursor;
        Log.v("MediaScanner", ((StringBuilder) (obj1)).append("pruneDeadThumbnailFiles... ").append(cursor).toString());
        if(cursor == null) goto _L2; else goto _L1
_L1:
        obj4 = cursor;
        obj2 = cursor;
        if(!cursor.moveToFirst()) goto _L2; else goto _L3
_L3:
        obj4 = cursor;
        obj2 = cursor;
        ((HashSet) (obj)).remove(cursor.getString(0));
        obj4 = cursor;
        obj2 = cursor;
        if(cursor.moveToNext()) goto _L3; else goto _L2
_L2:
        obj4 = cursor;
        obj2 = cursor;
        obj1 = ((Iterable) (obj)).iterator();
_L5:
        obj4 = cursor;
        obj2 = cursor;
        if(!((Iterator) (obj1)).hasNext())
            break; /* Loop/switch isn't completed */
        obj4 = cursor;
        obj2 = cursor;
        obj = (String)((Iterator) (obj1)).next();
        obj4 = cursor;
        obj2 = cursor;
        File file = JVM INSTR new #793 <Class File>;
        obj4 = cursor;
        obj2 = cursor;
        file.File(((String) (obj)));
        obj4 = cursor;
        obj2 = cursor;
        file.delete();
        continue; /* Loop/switch isn't completed */
        obj4;
        if(true) goto _L5; else goto _L4
_L4:
        obj4 = cursor;
        obj2 = cursor;
        obj1 = JVM INSTR new #723 <Class StringBuilder>;
        obj4 = cursor;
        obj2 = cursor;
        ((StringBuilder) (obj1)).StringBuilder();
        obj4 = cursor;
        obj2 = cursor;
        Log.v("MediaScanner", ((StringBuilder) (obj1)).append("/pruneDeadThumbnailFiles... ").append(cursor).toString());
        if(cursor != null)
            cursor.close();
_L7:
        return;
        obj2;
        if(obj4 != null)
            ((Cursor) (obj4)).close();
        if(true) goto _L7; else goto _L6
_L6:
        Exception exception;
        exception;
        if(obj2 != null)
            ((Cursor) (obj2)).close();
        throw exception;
    }

    private void releaseResources()
    {
        if(mDrmManagerClient != null)
        {
            mDrmManagerClient.close();
            mDrmManagerClient = null;
        }
    }

    private void setDefaultRingtoneFileNames()
    {
        mDefaultRingtoneFilename = SystemProperties.get("ro.config.ringtone");
        mDefaultNotificationFilename = SystemProperties.get("ro.config.notification_sound");
        mDefaultAlarmAlertFilename = SystemProperties.get("ro.config.alarm_alert");
    }

    private native void setLocale(String s);

    private String settingSetIndicatorName(String s)
    {
        return (new StringBuilder()).append(s).append("_set").toString();
    }

    private boolean wasRingtoneAlreadySet(String s)
    {
        boolean flag = false;
        ContentResolver contentresolver = mContext.getContentResolver();
        s = settingSetIndicatorName(s);
        int i;
        try
        {
            i = android.provider.Settings.System.getInt(contentresolver, s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return false;
        }
        if(i != 0)
            flag = true;
        return flag;
    }

    public void close()
    {
        mCloseGuard.close();
        if(mClosed.compareAndSet(false, true))
        {
            mMediaProvider.close();
            native_finalize();
        }
    }

    public native byte[] extractAlbumArt(FileDescriptor filedescriptor);

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

    FileEntry makeEntryFor(String s)
    {
        Cursor cursor;
        Cursor cursor1;
        cursor = null;
        cursor1 = null;
        Cursor cursor2 = mMediaProvider.query(mFilesUriNoNotify, FILES_PRESCAN_PROJECTION, "_data=?", new String[] {
            s
        }, null, null);
        cursor1 = cursor2;
        cursor = cursor2;
        if(!cursor2.moveToFirst())
            break MISSING_BLOCK_LABEL_122;
        cursor1 = cursor2;
        cursor = cursor2;
        long l = cursor2.getLong(0);
        cursor1 = cursor2;
        cursor = cursor2;
        int i = cursor2.getInt(2);
        cursor1 = cursor2;
        cursor = cursor2;
        s = new FileEntry(l, s, cursor2.getLong(3), i);
        if(cursor2 != null)
            cursor2.close();
        return s;
        if(cursor2 != null)
            cursor2.close();
_L2:
        return null;
        s;
        if(cursor1 != null)
            cursor1.close();
        if(true) goto _L2; else goto _L1
_L1:
        s;
        if(cursor != null)
            cursor.close();
        throw s;
    }

    public void scanDirectories(String as[])
    {
        System.currentTimeMillis();
        prescan(null, true);
        System.currentTimeMillis();
        MediaInserter mediainserter = JVM INSTR new #1288 <Class MediaInserter>;
        mediainserter.MediaInserter(mMediaProvider, 500);
        mMediaInserter = mediainserter;
        int i = 0;
_L2:
        if(i >= as.length)
            break; /* Loop/switch isn't completed */
        processDirectory(as[i], mClient);
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        mMediaInserter.flushAll();
        mMediaInserter = null;
        System.currentTimeMillis();
        postscan(as);
        System.currentTimeMillis();
        releaseResources();
_L3:
        return;
        as;
        Log.e("MediaScanner", "RemoteException in MediaScanner.scan()", as);
        releaseResources();
          goto _L3
        as;
        Log.e("MediaScanner", "UnsupportedOperationException in MediaScanner.scan()", as);
        releaseResources();
          goto _L3
        as;
        Log.e("MediaScanner", "SQLException in MediaScanner.scan()", as);
        releaseResources();
          goto _L3
        as;
        releaseResources();
        throw as;
    }

    public void scanMtpFile(String s, int i, int j)
    {
        Object obj;
        int k;
        Object obj1;
        long l;
        obj = MediaFile.getFileType(s);
        String s1;
        if(obj == null)
            k = 0;
        else
            k = ((MediaFile.MediaFileType) (obj)).fileType;
        obj1 = new File(s);
        l = ((File) (obj1)).lastModified() / 1000L;
        if(MediaFile.isAudioFileType(k) || !(MediaFile.isVideoFileType(k) ^ true) || !(MediaFile.isImageFileType(k) ^ true) || !(MediaFile.isPlayListFileType(k) ^ true) || !(MediaFile.isDrmFileType(k) ^ true))
            break MISSING_BLOCK_LABEL_177;
        s = new ContentValues();
        s.put("_size", Long.valueOf(((File) (obj1)).length()));
        s.put("date_modified", Long.valueOf(l));
        s1 = Integer.toString(i);
        mMediaProvider.update(android.provider.MediaStore.Files.getMtpObjectsUri(mVolumeName), s, "_id=?", new String[] {
            s1
        });
_L1:
        return;
        s;
        Log.e("MediaScanner", "RemoteException in scanMtpFile", s);
          goto _L1
        String s2;
        Object obj2;
        Object obj3;
        Object obj4;
        String s3;
        mMtpObjectHandle = i;
        obj2 = null;
        obj3 = null;
        obj4 = null;
        s3 = obj2;
        s2 = obj3;
        if(!MediaFile.isPlayListFileType(k)) goto _L3; else goto _L2
_L2:
        s3 = obj2;
        s2 = obj3;
        prescan(null, true);
        s3 = obj2;
        s2 = obj3;
        obj1 = makeEntryFor(s);
        s = obj4;
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_284;
        s3 = obj2;
        s2 = obj3;
        s = mMediaProvider.query(mFilesUri, FILES_PRESCAN_PROJECTION, null, null, null, null);
        s3 = s;
        s2 = s;
        processPlayList(((FileEntry) (obj1)), s);
_L7:
        mMtpObjectHandle = 0;
        if(s != null)
            s.close();
        releaseResources();
_L5:
        return;
_L3:
        s3 = obj2;
        s2 = obj3;
        prescan(s, false);
        s3 = obj2;
        s2 = obj3;
        MyMediaScannerClient mymediascannerclient = mClient;
        s3 = obj2;
        s2 = obj3;
        obj = ((MediaFile.MediaFileType) (obj)).mimeType;
        s3 = obj2;
        s2 = obj3;
        long l1 = ((File) (obj1)).length();
        boolean flag;
        if(j == 12289)
            flag = true;
        else
            flag = false;
        s3 = obj2;
        s2 = obj3;
        mymediascannerclient.doScanFile(s, ((String) (obj)), l, l1, flag, true, isNoMediaPath(s));
        s = obj4;
        continue; /* Loop/switch isn't completed */
        s;
        s2 = s3;
        Log.e("MediaScanner", "RemoteException in MediaScanner.scanFile()", s);
        mMtpObjectHandle = 0;
        if(s3 != null)
            s3.close();
        releaseResources();
        if(true) goto _L5; else goto _L4
_L4:
        s;
        mMtpObjectHandle = 0;
        if(s2 != null)
            s2.close();
        releaseResources();
        throw s;
        if(true) goto _L7; else goto _L6
_L6:
    }

    public Uri scanSingleFile(String s, String s1)
    {
        File file;
        boolean flag;
        prescan(s, true);
        file = JVM INSTR new #793 <Class File>;
        file.File(s);
        if(!file.exists())
            break MISSING_BLOCK_LABEL_35;
        flag = file.canRead();
        if(!(flag ^ true))
            break MISSING_BLOCK_LABEL_41;
        releaseResources();
        return null;
        long l = file.lastModified() / 1000L;
        s = mClient.doScanFile(s, s1, l, file.length(), false, true, isNoMediaPath(s));
        releaseResources();
        return s;
        s;
        Log.e("MediaScanner", "RemoteException in MediaScanner.scanFile()", s);
        releaseResources();
        return null;
        s;
        releaseResources();
        throw s;
    }

    private static final String ALARMS_DIR = "/alarms/";
    private static final int DATE_MODIFIED_PLAYLISTS_COLUMN_INDEX = 2;
    private static final String DEFAULT_RINGTONE_PROPERTY_PREFIX = "ro.config.";
    private static final boolean ENABLE_BULK_INSERTS = true;
    private static final int FILES_PRESCAN_DATE_MODIFIED_COLUMN_INDEX = 3;
    private static final int FILES_PRESCAN_FORMAT_COLUMN_INDEX = 2;
    private static final int FILES_PRESCAN_ID_COLUMN_INDEX = 0;
    private static final int FILES_PRESCAN_PATH_COLUMN_INDEX = 1;
    private static final String FILES_PRESCAN_PROJECTION[] = {
        "_id", "_data", "format", "date_modified"
    };
    private static final String ID3_GENRES[] = {
        "Blues", "Classic Rock", "Country", "Dance", "Disco", "Funk", "Grunge", "Hip-Hop", "Jazz", "Metal", 
        "New Age", "Oldies", "Other", "Pop", "R&B", "Rap", "Reggae", "Rock", "Techno", "Industrial", 
        "Alternative", "Ska", "Death Metal", "Pranks", "Soundtrack", "Euro-Techno", "Ambient", "Trip-Hop", "Vocal", "Jazz+Funk", 
        "Fusion", "Trance", "Classical", "Instrumental", "Acid", "House", "Game", "Sound Clip", "Gospel", "Noise", 
        "AlternRock", "Bass", "Soul", "Punk", "Space", "Meditative", "Instrumental Pop", "Instrumental Rock", "Ethnic", "Gothic", 
        "Darkwave", "Techno-Industrial", "Electronic", "Pop-Folk", "Eurodance", "Dream", "Southern Rock", "Comedy", "Cult", "Gangsta", 
        "Top 40", "Christian Rap", "Pop/Funk", "Jungle", "Native American", "Cabaret", "New Wave", "Psychadelic", "Rave", "Showtunes", 
        "Trailer", "Lo-Fi", "Tribal", "Acid Punk", "Acid Jazz", "Polka", "Retro", "Musical", "Rock & Roll", "Hard Rock", 
        "Folk", "Folk-Rock", "National Folk", "Swing", "Fast Fusion", "Bebob", "Latin", "Revival", "Celtic", "Bluegrass", 
        "Avantgarde", "Gothic Rock", "Progressive Rock", "Psychedelic Rock", "Symphonic Rock", "Slow Rock", "Big Band", "Chorus", "Easy Listening", "Acoustic", 
        "Humour", "Speech", "Chanson", "Opera", "Chamber Music", "Sonata", "Symphony", "Booty Bass", "Primus", "Porn Groove", 
        "Satire", "Slow Jam", "Club", "Tango", "Samba", "Folklore", "Ballad", "Power Ballad", "Rhythmic Soul", "Freestyle", 
        "Duet", "Punk Rock", "Drum Solo", "A capella", "Euro-House", "Dance Hall", "Goa", "Drum & Bass", "Club-House", "Hardcore", 
        "Terror", "Indie", "Britpop", null, "Polsk Punk", "Beat", "Christian Gangsta", "Heavy Metal", "Black Metal", "Crossover", 
        "Contemporary Christian", "Christian Rock", "Merengue", "Salsa", "Thrash Metal", "Anime", "JPop", "Synthpop"
    };
    private static final int ID_PLAYLISTS_COLUMN_INDEX = 0;
    private static final String ID_PROJECTION[] = {
        "_id"
    };
    public static final String LAST_INTERNAL_SCAN_FINGERPRINT = "lastScanFingerprint";
    private static final String MUSIC_DIR = "/music/";
    private static final String NOTIFICATIONS_DIR = "/notifications/";
    private static final int PATH_PLAYLISTS_COLUMN_INDEX = 1;
    private static final String PLAYLIST_MEMBERS_PROJECTION[] = {
        "playlist_id"
    };
    private static final String PODCAST_DIR = "/podcasts/";
    private static final String RINGTONES_DIR = "/ringtones/";
    public static final String SCANNED_BUILD_PREFS_NAME = "MediaScanBuild";
    private static final String SYSTEM_SOUNDS_DIR = "/system/media/audio";
    private static final String TAG = "MediaScanner";
    private static HashMap mMediaPaths = new HashMap();
    private static HashMap mNoMediaPaths = new HashMap();
    private static String sLastInternalScanFingerprint;
    private final Uri mAudioUri;
    private final android.graphics.BitmapFactory.Options mBitmapOptions = new android.graphics.BitmapFactory.Options();
    private final MyMediaScannerClient mClient = new MyMediaScannerClient();
    private final CloseGuard mCloseGuard = CloseGuard.get();
    private final AtomicBoolean mClosed = new AtomicBoolean();
    private final Context mContext;
    private String mDefaultAlarmAlertFilename;
    private boolean mDefaultAlarmSet;
    private String mDefaultNotificationFilename;
    private boolean mDefaultNotificationSet;
    private String mDefaultRingtoneFilename;
    private boolean mDefaultRingtoneSet;
    private DrmManagerClient mDrmManagerClient;
    private final Uri mFilesUri;
    private final Uri mFilesUriNoNotify;
    private final Uri mImagesUri;
    private MediaInserter mMediaInserter;
    private final ContentProviderClient mMediaProvider;
    private int mMtpObjectHandle;
    private long mNativeContext;
    private int mOriginalCount;
    private final String mPackageName;
    private final ArrayList mPlayLists = new ArrayList();
    private final ArrayList mPlaylistEntries = new ArrayList();
    private final Uri mPlaylistsUri;
    private final boolean mProcessGenres;
    private final boolean mProcessPlaylists;
    private final Uri mThumbsUri;
    private final Uri mVideoUri;
    private final String mVolumeName;

    static 
    {
        System.loadLibrary("media_jni");
        native_init();
    }
}
