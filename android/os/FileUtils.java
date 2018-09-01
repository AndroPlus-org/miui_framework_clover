// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.system.*;
import android.text.TextUtils;
import android.util.Log;
import android.util.Slog;
import android.webkit.MimeTypeMap;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import libcore.util.EmptyArray;

public class FileUtils
{
    private static class NoImagePreloadHolder
    {

        public static final Pattern SAFE_FILENAME_PATTERN = Pattern.compile("[\\w%+,./=_-]+");


        private NoImagePreloadHolder()
        {
        }
    }


    public FileUtils()
    {
    }

    private static File buildFile(File file, String s, String s1)
    {
        if(TextUtils.isEmpty(s1))
            return new File(file, s);
        else
            return new File(file, (new StringBuilder()).append(s).append(".").append(s1).toString());
    }

    public static File buildUniqueFile(File file, String s)
        throws FileNotFoundException
    {
        int i = s.lastIndexOf('.');
        String s2;
        if(i >= 0)
        {
            String s1 = s.substring(0, i);
            s2 = s.substring(i + 1);
            s = s1;
        } else
        {
            s2 = null;
        }
        return buildUniqueFileWithExtension(file, s, s2);
    }

    public static File buildUniqueFile(File file, String s, String s1)
        throws FileNotFoundException
    {
        s = splitFileName(s, s1);
        return buildUniqueFileWithExtension(file, s[0], s[1]);
    }

    private static File buildUniqueFileWithExtension(File file, String s, String s1)
        throws FileNotFoundException
    {
        File file1 = buildFile(file, s, s1);
        int j;
        for(int i = 0; file1.exists(); i = j)
        {
            j = i + 1;
            if(i >= 32)
                throw new FileNotFoundException("Failed to create unique file");
            file1 = buildFile(file, (new StringBuilder()).append(s).append(" (").append(j).append(")").toString(), s1);
        }

        return file1;
    }

    public static String buildValidExtFilename(String s)
    {
        if(TextUtils.isEmpty(s) || ".".equals(s) || "..".equals(s))
            return "(invalid)";
        StringBuilder stringbuilder = new StringBuilder(s.length());
        int i = 0;
        while(i < s.length()) 
        {
            char c = s.charAt(i);
            if(isValidExtFilenameChar(c))
                stringbuilder.append(c);
            else
                stringbuilder.append('_');
            i++;
        }
        trimFilename(stringbuilder, 255);
        return stringbuilder.toString();
    }

    public static String buildValidFatFilename(String s)
    {
        if(TextUtils.isEmpty(s) || ".".equals(s) || "..".equals(s))
            return "(invalid)";
        StringBuilder stringbuilder = new StringBuilder(s.length());
        int i = 0;
        while(i < s.length()) 
        {
            char c = s.charAt(i);
            if(isValidFatFilenameChar(c))
                stringbuilder.append(c);
            else
                stringbuilder.append('_');
            i++;
        }
        trimFilename(stringbuilder, 255);
        return stringbuilder.toString();
    }

    public static void bytesToFile(String s, byte abyte0[])
        throws IOException
    {
        Object obj;
        Object obj1;
        Object obj2;
        Object obj3;
        obj = null;
        obj1 = null;
        obj2 = null;
        obj3 = null;
        Object obj4;
        obj4 = JVM INSTR new #161 <Class FileOutputStream>;
        ((FileOutputStream) (obj4)).FileOutputStream(s);
        ((FileOutputStream) (obj4)).write(abyte0);
        s = obj1;
        if(obj4 == null)
            break MISSING_BLOCK_LABEL_41;
        ((FileOutputStream) (obj4)).close();
        s = obj1;
_L1:
        if(s != null)
            throw s;
        else
            return;
        s;
          goto _L1
        abyte0;
        s = obj3;
_L6:
        throw abyte0;
        obj;
        obj4 = abyte0;
        abyte0 = ((byte []) (obj));
_L4:
        obj = obj4;
        if(s == null)
            break MISSING_BLOCK_LABEL_77;
        s.close();
        obj = obj4;
_L2:
        if(obj != null)
            throw obj;
        else
            throw abyte0;
        s;
        if(obj4 == null)
        {
            obj = s;
        } else
        {
            obj = obj4;
            if(obj4 != s)
            {
                ((Throwable) (obj4)).addSuppressed(s);
                obj = obj4;
            }
        }
          goto _L2
        abyte0;
        s = obj2;
        obj4 = obj;
        continue; /* Loop/switch isn't completed */
        abyte0;
        s = ((String) (obj4));
        obj4 = obj;
        if(true) goto _L4; else goto _L3
_L3:
        abyte0;
        s = ((String) (obj4));
        if(true) goto _L6; else goto _L5
_L5:
    }

    public static long checksumCrc32(File file)
        throws FileNotFoundException, IOException
    {
        CRC32 crc32;
        IOException ioexception;
        crc32 = new CRC32();
        ioexception = null;
        CheckedInputStream checkedinputstream;
        checkedinputstream = JVM INSTR new #180 <Class CheckedInputStream>;
        FileInputStream fileinputstream = JVM INSTR new #182 <Class FileInputStream>;
        fileinputstream.FileInputStream(file);
        checkedinputstream.CheckedInputStream(fileinputstream, crc32);
        long l;
        for(file = new byte[128]; checkedinputstream.read(file) >= 0;);
        l = crc32.getValue();
        if(checkedinputstream != null)
            try
            {
                checkedinputstream.close();
            }
            // Misplaced declaration of an exception variable
            catch(File file) { }
        return l;
        file;
_L2:
        if(ioexception != null)
            try
            {
                ioexception.close();
            }
            // Misplaced declaration of an exception variable
            catch(IOException ioexception) { }
        throw file;
        file;
        ioexception = checkedinputstream;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static boolean contains(File file, File file1)
    {
        if(file == null || file1 == null)
            return false;
        else
            return contains(file.getAbsolutePath(), file1.getAbsolutePath());
    }

    public static boolean contains(String s, String s1)
    {
        if(s.equals(s1))
            return true;
        String s2 = s;
        if(!s.endsWith("/"))
            s2 = (new StringBuilder()).append(s).append("/").toString();
        return s1.startsWith(s2);
    }

    public static boolean contains(File afile[], File file)
    {
        int i = afile.length;
        for(int j = 0; j < i; j++)
            if(contains(afile[j], file))
                return true;

        return false;
    }

    public static boolean copyFile(File file, File file1)
    {
        try
        {
            copyFileOrThrow(file, file1);
        }
        // Misplaced declaration of an exception variable
        catch(File file)
        {
            return false;
        }
        return true;
    }

    public static void copyFileOrThrow(File file, File file1)
        throws IOException
    {
        Object obj;
        Object obj1;
        Object obj2;
        Object obj3;
        obj = null;
        obj1 = null;
        obj2 = null;
        obj3 = null;
        Object obj4;
        obj4 = JVM INSTR new #182 <Class FileInputStream>;
        ((FileInputStream) (obj4)).FileInputStream(file);
        copyToFileOrThrow(((InputStream) (obj4)), file1);
        file = obj1;
        if(obj4 == null)
            break MISSING_BLOCK_LABEL_41;
        ((InputStream) (obj4)).close();
        file = obj1;
_L1:
        if(file != null)
            throw file;
        else
            return;
        file;
          goto _L1
        file;
        file1 = obj3;
_L6:
        throw file;
        obj;
        obj4 = file;
        file = ((File) (obj));
_L4:
        obj = obj4;
        if(file1 == null)
            break MISSING_BLOCK_LABEL_77;
        file1.close();
        obj = obj4;
_L2:
        if(obj != null)
            throw obj;
        else
            throw file;
        file1;
        if(obj4 == null)
        {
            obj = file1;
        } else
        {
            obj = obj4;
            if(obj4 != file1)
            {
                ((Throwable) (obj4)).addSuppressed(file1);
                obj = obj4;
            }
        }
          goto _L2
        file;
        file1 = obj2;
        obj4 = obj;
        continue; /* Loop/switch isn't completed */
        file;
        file1 = ((File) (obj4));
        obj4 = obj;
        if(true) goto _L4; else goto _L3
_L3:
        file;
        file1 = ((File) (obj4));
        if(true) goto _L6; else goto _L5
_L5:
    }

    public static void copyPermissions(File file, File file1)
        throws IOException
    {
        try
        {
            file = Os.stat(file.getAbsolutePath());
            Os.chmod(file1.getAbsolutePath(), ((StructStat) (file)).st_mode);
            Os.chown(file1.getAbsolutePath(), ((StructStat) (file)).st_uid, ((StructStat) (file)).st_gid);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(File file)
        {
            throw file.rethrowAsIOException();
        }
    }

    public static boolean copyToFile(InputStream inputstream, File file)
    {
        try
        {
            copyToFileOrThrow(inputstream, file);
        }
        // Misplaced declaration of an exception variable
        catch(InputStream inputstream)
        {
            return false;
        }
        return true;
    }

    public static void copyToFileOrThrow(InputStream inputstream, File file)
        throws IOException
    {
        if(file.exists())
            file.delete();
        file = new FileOutputStream(file);
        byte abyte0[] = new byte[4096];
_L1:
        int i = inputstream.read(abyte0);
        if(i < 0)
            break MISSING_BLOCK_LABEL_65;
        file.write(abyte0, 0, i);
          goto _L1
        inputstream;
        file.flush();
        try
        {
            file.getFD().sync();
        }
        catch(IOException ioexception) { }
        file.close();
        throw inputstream;
        file.flush();
        try
        {
            file.getFD().sync();
        }
        // Misplaced declaration of an exception variable
        catch(InputStream inputstream) { }
        file.close();
        return;
    }

    public static File createDir(File file, String s)
    {
        file = new File(file, s);
        if(file.exists())
        {
            if(!file.isDirectory())
                file = null;
            return file;
        }
        if(!file.mkdir())
            file = null;
        return file;
    }

    public static boolean deleteContents(File file)
    {
        file = file.listFiles();
        boolean flag = true;
        boolean flag1 = true;
        if(file != null)
        {
            int i = 0;
            int j = file.length;
            do
            {
                flag = flag1;
                if(i >= j)
                    break;
                File file1 = file[i];
                flag = flag1;
                if(file1.isDirectory())
                    flag = flag1 & deleteContents(file1);
                flag1 = flag;
                if(!file1.delete())
                {
                    Log.w("FileUtils", (new StringBuilder()).append("Failed to delete ").append(file1).toString());
                    flag1 = false;
                }
                i++;
            } while(true);
        }
        return flag;
    }

    public static boolean deleteContentsAndDir(File file)
    {
        if(deleteContents(file))
            return file.delete();
        else
            return false;
    }

    public static boolean deleteOlderFiles(File file, int i, long l)
    {
        if(i < 0 || l < 0L)
            throw new IllegalArgumentException("Constraints must be positive or 0");
        File afile[] = file.listFiles();
        if(afile == null)
            return false;
        Arrays.sort(afile, new Comparator() {

            public int compare(File file1, File file2)
            {
                return Long.compare(file2.lastModified(), file1.lastModified());
            }

            public volatile int compare(Object obj, Object obj1)
            {
                return compare((File)obj, (File)obj1);
            }

        }
);
        boolean flag;
        boolean flag1;
        for(flag = false; i < afile.length; flag = flag1)
        {
            file = afile[i];
            flag1 = flag;
            if(System.currentTimeMillis() - file.lastModified() > l)
            {
                flag1 = flag;
                if(file.delete())
                {
                    Log.d("FileUtils", (new StringBuilder()).append("Deleted old file ").append(file).toString());
                    flag1 = true;
                }
            }
            i++;
        }

        return flag;
    }

    public static int getUid(String s)
    {
        int i;
        try
        {
            i = Os.stat(s).st_uid;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return -1;
        }
        return i;
    }

    public static boolean isFilenameSafe(File file)
    {
        return NoImagePreloadHolder.SAFE_FILENAME_PATTERN.matcher(file.getPath()).matches();
    }

    public static boolean isValidExtFilename(String s)
    {
        boolean flag;
        if(s != null)
            flag = s.equals(buildValidExtFilename(s));
        else
            flag = false;
        return flag;
    }

    private static boolean isValidExtFilenameChar(char c)
    {
        switch(c)
        {
        default:
            return true;

        case 0: // '\0'
        case 47: // '/'
            return false;
        }
    }

    public static boolean isValidFatFilename(String s)
    {
        boolean flag;
        if(s != null)
            flag = s.equals(buildValidFatFilename(s));
        else
            flag = false;
        return flag;
    }

    private static boolean isValidFatFilenameChar(char c)
    {
        if(c >= 0 && c <= '\037')
            return false;
        switch(c)
        {
        default:
            return true;

        case 34: // '"'
        case 42: // '*'
        case 47: // '/'
        case 58: // ':'
        case 60: // '<'
        case 62: // '>'
        case 63: // '?'
        case 92: // '\\'
        case 124: // '|'
        case 127: // '\177'
            return false;
        }
    }

    public static File[] listFilesOrEmpty(File file)
    {
        if(file == null)
            return EMPTY;
        file = file.listFiles();
        if(file != null)
            return file;
        else
            return EMPTY;
    }

    public static File[] listFilesOrEmpty(File file, FilenameFilter filenamefilter)
    {
        if(file == null)
            return EMPTY;
        file = file.listFiles(filenamefilter);
        if(file != null)
            return file;
        else
            return EMPTY;
    }

    public static String[] listOrEmpty(File file)
    {
        if(file == null)
            return EmptyArray.STRING;
        file = file.list();
        if(file != null)
            return file;
        else
            return EmptyArray.STRING;
    }

    public static File newFileOrNull(String s)
    {
        File file = null;
        if(s != null)
            file = new File(s);
        return file;
    }

    public static String readTextFile(File file, int i, String s)
        throws IOException
    {
        FileInputStream fileinputstream;
        BufferedInputStream bufferedinputstream;
        fileinputstream = new FileInputStream(file);
        bufferedinputstream = new BufferedInputStream(fileinputstream);
        long l = file.length();
        int j;
label0:
        {
            if(i <= 0 && (l <= 0L || i != 0))
                break MISSING_BLOCK_LABEL_208;
            j = i;
            if(l <= 0L)
                break label0;
            if(i != 0)
            {
                j = i;
                if(l >= (long)i)
                    break label0;
            }
            j = (int)l;
        }
        byte abyte0[];
        abyte0 = new byte[j + 1];
        i = bufferedinputstream.read(abyte0);
        if(i <= 0)
        {
            bufferedinputstream.close();
            fileinputstream.close();
            return "";
        }
        if(i > j)
            break MISSING_BLOCK_LABEL_132;
        file = new String(abyte0, 0, i);
        bufferedinputstream.close();
        fileinputstream.close();
        return file;
        if(s != null)
            break MISSING_BLOCK_LABEL_160;
        file = new String(abyte0, 0, j);
        bufferedinputstream.close();
        fileinputstream.close();
        return file;
        StringBuilder stringbuilder = JVM INSTR new #63  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        file = JVM INSTR new #80  <Class String>;
        file.String(abyte0, 0, j);
        file = stringbuilder.append(file).append(s).toString();
        bufferedinputstream.close();
        fileinputstream.close();
        return file;
        File file1;
        int k;
        if(i >= 0)
            break MISSING_BLOCK_LABEL_446;
        k = 0;
        abyte0 = null;
        file1 = null;
_L2:
        byte abyte1[];
        j = k;
        if(abyte0 != null)
            j = 1;
        abyte1 = file1;
        file = abyte0;
        if(abyte0 != null)
            break MISSING_BLOCK_LABEL_254;
        k = -i;
        file = new byte[k];
        int i1 = bufferedinputstream.read(file);
        file1 = file;
        abyte0 = abyte1;
        k = j;
        if(i1 == file.length) goto _L2; else goto _L1
_L1:
        if(abyte1 == null && i1 <= 0)
        {
            bufferedinputstream.close();
            fileinputstream.close();
            return "";
        }
        if(abyte1 != null)
            break MISSING_BLOCK_LABEL_331;
        file = new String(file, 0, i1);
        bufferedinputstream.close();
        fileinputstream.close();
        return file;
        if(i1 <= 0)
            break MISSING_BLOCK_LABEL_370;
        j = 1;
        System.arraycopy(abyte1, i1, abyte1, 0, abyte1.length - i1);
        System.arraycopy(file, 0, abyte1, abyte1.length - i1, i1);
        if(s != null && !(j ^ true))
            break MISSING_BLOCK_LABEL_402;
        file = new String(abyte1);
        bufferedinputstream.close();
        fileinputstream.close();
        return file;
        file = JVM INSTR new #63  <Class StringBuilder>;
        file.StringBuilder();
        file = file.append(s);
        s = JVM INSTR new #80  <Class String>;
        s.String(abyte1);
        file = file.append(s).toString();
        bufferedinputstream.close();
        fileinputstream.close();
        return file;
        s = JVM INSTR new #410 <Class ByteArrayOutputStream>;
        s.ByteArrayOutputStream();
        file = new byte[1024];
_L4:
        i = bufferedinputstream.read(file);
        if(i <= 0)
            continue; /* Loop/switch isn't completed */
        s.write(file, 0, i);
        if(i == file.length) goto _L4; else goto _L3
_L3:
        file = s.toString();
        bufferedinputstream.close();
        fileinputstream.close();
        return file;
        file;
        bufferedinputstream.close();
        fileinputstream.close();
        throw file;
    }

    public static File rewriteAfterRename(File file, File file1, File file2)
    {
        while(file2 == null || file == null || file1 == null) 
            return null;
        if(contains(file, file2))
            return new File(file1, file2.getAbsolutePath().substring(file.getAbsolutePath().length()));
        else
            return null;
    }

    public static String rewriteAfterRename(File file, File file1, String s)
    {
        Object obj = null;
        if(s == null)
            return null;
        file1 = rewriteAfterRename(file, file1, new File(s));
        file = obj;
        if(file1 != null)
            file = file1.getAbsolutePath();
        return file;
    }

    public static String[] rewriteAfterRename(File file, File file1, String as[])
    {
        if(as == null)
            return null;
        String as1[] = new String[as.length];
        for(int i = 0; i < as.length; i++)
            as1[i] = rewriteAfterRename(file, file1, as[i]);

        return as1;
    }

    public static long roundStorageSize(long l)
    {
        long l1 = 1L;
        long l2 = 1L;
        do
        {
            if(l1 * l2 >= l)
                break;
            long l3 = l1 << 1;
            l1 = l3;
            if(l3 > 512L)
            {
                l1 = 1L;
                l2 *= 1000L;
            }
        } while(true);
        return l1 * l2;
    }

    public static int setPermissions(File file, int i, int j, int k)
    {
        return setPermissions(file.getAbsolutePath(), i, j, k);
    }

    public static int setPermissions(FileDescriptor filedescriptor, int i, int j, int k)
    {
        try
        {
            Os.fchmod(filedescriptor, i);
        }
        // Misplaced declaration of an exception variable
        catch(FileDescriptor filedescriptor)
        {
            Slog.w("FileUtils", (new StringBuilder()).append("Failed to fchmod(): ").append(filedescriptor).toString());
            return ((ErrnoException) (filedescriptor)).errno;
        }
        if(j >= 0 || k >= 0)
            try
            {
                Os.fchown(filedescriptor, j, k);
            }
            // Misplaced declaration of an exception variable
            catch(FileDescriptor filedescriptor)
            {
                Slog.w("FileUtils", (new StringBuilder()).append("Failed to fchown(): ").append(filedescriptor).toString());
                return ((ErrnoException) (filedescriptor)).errno;
            }
        return 0;
    }

    public static int setPermissions(String s, int i, int j, int k)
    {
        try
        {
            Os.chmod(s, i);
        }
        catch(ErrnoException errnoexception)
        {
            Slog.w("FileUtils", (new StringBuilder()).append("Failed to chmod(").append(s).append("): ").append(errnoexception).toString());
            return errnoexception.errno;
        }
        if(j >= 0 || k >= 0)
            try
            {
                Os.chown(s, j, k);
            }
            catch(ErrnoException errnoexception1)
            {
                Slog.w("FileUtils", (new StringBuilder()).append("Failed to chown(").append(s).append("): ").append(errnoexception1).toString());
                return errnoexception1.errno;
            }
        return 0;
    }

    public static String[] splitFileName(String s, String s1)
    {
        if(!"vnd.android.document/directory".equals(s)) goto _L2; else goto _L1
_L1:
        String s2;
        String s3;
        s2 = null;
        s3 = s1;
_L4:
        s = s2;
        if(s2 == null)
            s = "";
        return (new String[] {
            s3, s
        });
_L2:
        int i = s1.lastIndexOf('.');
        String s4;
        String s5;
        String s6;
        String s7;
        if(i >= 0)
        {
            s4 = s1.substring(0, i);
            s5 = s1.substring(i + 1);
            s2 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(s5.toLowerCase());
        } else
        {
            s4 = s1;
            s5 = null;
            s2 = null;
        }
        s6 = s2;
        if(s2 == null)
            s6 = "application/octet-stream";
        s7 = MimeTypeMap.getSingleton().getExtensionFromMimeType(s);
        s2 = s5;
        s3 = s4;
        if(!Objects.equals(s, s6))
        {
            s2 = s5;
            s3 = s4;
            if(!Objects.equals(s5, s7))
            {
                s2 = s7;
                s3 = s1;
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static void stringToFile(File file, String s)
        throws IOException
    {
        stringToFile(file.getAbsolutePath(), s);
    }

    public static void stringToFile(String s, String s1)
        throws IOException
    {
        bytesToFile(s, s1.getBytes(StandardCharsets.UTF_8));
    }

    public static boolean sync(FileOutputStream fileoutputstream)
    {
        if(fileoutputstream != null)
            try
            {
                fileoutputstream.getFD().sync();
            }
            // Misplaced declaration of an exception variable
            catch(FileOutputStream fileoutputstream)
            {
                return false;
            }
        return true;
    }

    public static String trimFilename(String s, int i)
    {
        s = new StringBuilder(s);
        trimFilename(((StringBuilder) (s)), i);
        return s.toString();
    }

    private static void trimFilename(StringBuilder stringbuilder, int i)
    {
        byte abyte0[] = stringbuilder.toString().getBytes(StandardCharsets.UTF_8);
        if(abyte0.length > i)
        {
            for(; abyte0.length > i - 3; abyte0 = stringbuilder.toString().getBytes(StandardCharsets.UTF_8))
                stringbuilder.deleteCharAt(stringbuilder.length() / 2);

            stringbuilder.insert(stringbuilder.length() / 2, "...");
        }
    }

    private static final File EMPTY[] = new File[0];
    public static final int S_IRGRP = 32;
    public static final int S_IROTH = 4;
    public static final int S_IRUSR = 256;
    public static final int S_IRWXG = 56;
    public static final int S_IRWXO = 7;
    public static final int S_IRWXU = 448;
    public static final int S_IWGRP = 16;
    public static final int S_IWOTH = 2;
    public static final int S_IWUSR = 128;
    public static final int S_IXGRP = 8;
    public static final int S_IXOTH = 1;
    public static final int S_IXUSR = 64;
    private static final String TAG = "FileUtils";

}
