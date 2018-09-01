// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.content;

import android.content.*;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.graphics.Point;
import android.net.Uri;
import android.os.*;
import android.provider.*;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import android.webkit.MimeTypeMap;
import java.io.*;
import java.util.*;
import libcore.io.IoUtils;

public abstract class FileSystemProvider extends DocumentsProvider
{
    private class DirectoryCursor extends MatrixCursor
    {

        public void close()
        {
            super.close();
            FileSystemProvider._2D_wrap1(FileSystemProvider.this, mFile);
        }

        private final File mFile;
        final FileSystemProvider this$0;

        public DirectoryCursor(String as[], String s, File file)
        {
            this$0 = FileSystemProvider.this;
            super(as);
            as = buildNotificationUri(s);
            setNotificationUri(getContext().getContentResolver(), as);
            mFile = file;
            FileSystemProvider._2D_wrap0(FileSystemProvider.this, mFile, as);
        }
    }

    private static class DirectoryObserver extends FileObserver
    {

        static int _2D_get0(DirectoryObserver directoryobserver)
        {
            return directoryobserver.mRefCount;
        }

        static int _2D_set0(DirectoryObserver directoryobserver, int i)
        {
            directoryobserver.mRefCount = i;
            return i;
        }

        public void onEvent(int i, String s)
        {
            if((i & 0xfcc) != 0)
                mResolver.notifyChange(mNotifyUri, null, false);
        }

        public String toString()
        {
            return (new StringBuilder()).append("DirectoryObserver{file=").append(mFile.getAbsolutePath()).append(", ref=").append(mRefCount).append("}").toString();
        }

        private static final int NOTIFY_EVENTS = 4044;
        private final File mFile;
        private final Uri mNotifyUri;
        private int mRefCount;
        private final ContentResolver mResolver;

        public DirectoryObserver(File file, ContentResolver contentresolver, Uri uri)
        {
            super(file.getAbsolutePath(), 4044);
            mRefCount = 0;
            mFile = file;
            mResolver = contentresolver;
            mNotifyUri = uri;
        }
    }


    static void _2D_wrap0(FileSystemProvider filesystemprovider, File file, Uri uri)
    {
        filesystemprovider.startObserving(file, uri);
    }

    static void _2D_wrap1(FileSystemProvider filesystemprovider, File file)
    {
        filesystemprovider.stopObserving(file);
    }

    public FileSystemProvider()
    {
    }

    private void addFolderToMediaStore(File file)
    {
        long l;
        if(file == null)
            break MISSING_BLOCK_LABEL_80;
        if(!_2D_assertionsDisabled && !file.isDirectory())
            throw new AssertionError();
        l = Binder.clearCallingIdentity();
        ContentResolver contentresolver = getContext().getContentResolver();
        Uri uri = android.provider.MediaStore.Files.getDirectoryUri("external");
        ContentValues contentvalues = JVM INSTR new #99  <Class ContentValues>;
        contentvalues.ContentValues();
        contentvalues.put("_data", file.getAbsolutePath());
        contentresolver.insert(uri, contentvalues);
        Binder.restoreCallingIdentity(l);
        return;
        file;
        Binder.restoreCallingIdentity(l);
        throw file;
    }

    private static String getTypeForFile(File file)
    {
        if(file.isDirectory())
            return "vnd.android.document/directory";
        else
            return getTypeForName(file.getName());
    }

    private static String getTypeForName(String s)
    {
        int i = s.lastIndexOf('.');
        if(i >= 0)
        {
            s = s.substring(i + 1).toLowerCase();
            s = MimeTypeMap.getSingleton().getMimeTypeFromExtension(s);
            if(s != null)
                return s;
        }
        return "application/octet-stream";
    }

    private void moveInMediaStore(File file, File file1)
    {
        if(file == null || file1 == null) goto _L2; else goto _L1
_L1:
        long l = Binder.clearCallingIdentity();
        ContentResolver contentresolver;
        Uri uri;
        contentresolver = getContext().getContentResolver();
        if(!file1.isDirectory())
            break MISSING_BLOCK_LABEL_90;
        uri = android.provider.MediaStore.Files.getDirectoryUri("external");
_L3:
        ContentValues contentvalues = JVM INSTR new #99  <Class ContentValues>;
        contentvalues.ContentValues();
        contentvalues.put("_data", file1.getAbsolutePath());
        file = file.getAbsolutePath();
        contentresolver.update(uri, contentvalues, "_data LIKE ? AND lower(_data)=lower(?)", new String[] {
            file, file
        });
        Binder.restoreCallingIdentity(l);
_L2:
        return;
        uri = android.provider.MediaStore.Files.getContentUri("external");
          goto _L3
        file;
        Binder.restoreCallingIdentity(l);
        throw file;
    }

    private void removeFromMediaStore(File file, boolean flag)
        throws FileNotFoundException
    {
        long l;
        if(file == null)
            break MISSING_BLOCK_LABEL_146;
        l = Binder.clearCallingIdentity();
        ContentResolver contentresolver;
        Uri uri;
        contentresolver = getContext().getContentResolver();
        uri = android.provider.MediaStore.Files.getContentUri("external");
        if(!flag)
            break MISSING_BLOCK_LABEL_115;
        Object obj = JVM INSTR new #172 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        obj = ((StringBuilder) (obj)).append(file.getAbsolutePath()).append("/").toString();
        StringBuilder stringbuilder = JVM INSTR new #172 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        contentresolver.delete(uri, "_data LIKE ?1 AND lower(substr(_data,1,?2))=lower(?3)", new String[] {
            stringbuilder.append(((String) (obj))).append("%").toString(), Integer.toString(((String) (obj)).length()), obj
        });
        file = file.getAbsolutePath();
        contentresolver.delete(uri, "_data LIKE ?1 AND lower(_data)=lower(?2)", new String[] {
            file, file
        });
        Binder.restoreCallingIdentity(l);
        return;
        file;
        Binder.restoreCallingIdentity(l);
        throw file;
    }

    private String[] resolveProjection(String as[])
    {
        String as1[] = as;
        if(as == null)
            as1 = mDefaultProjection;
        return as1;
    }

    private void scanFile(File file)
    {
        Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        intent.setData(Uri.fromFile(file));
        getContext().sendBroadcast(intent);
    }

    private void startObserving(File file, Uri uri)
    {
        ArrayMap arraymap = mObservers;
        arraymap;
        JVM INSTR monitorenter ;
        DirectoryObserver directoryobserver = (DirectoryObserver)mObservers.get(file);
        DirectoryObserver directoryobserver1;
        directoryobserver1 = directoryobserver;
        if(directoryobserver != null)
            break MISSING_BLOCK_LABEL_64;
        directoryobserver1 = JVM INSTR new #9   <Class FileSystemProvider$DirectoryObserver>;
        directoryobserver1.DirectoryObserver(file, getContext().getContentResolver(), uri);
        directoryobserver1.startWatching();
        mObservers.put(file, directoryobserver1);
        DirectoryObserver._2D_set0(directoryobserver1, DirectoryObserver._2D_get0(directoryobserver1) + 1);
        arraymap;
        JVM INSTR monitorexit ;
        return;
        file;
        throw file;
    }

    private void stopObserving(File file)
    {
        ArrayMap arraymap = mObservers;
        arraymap;
        JVM INSTR monitorenter ;
        DirectoryObserver directoryobserver = (DirectoryObserver)mObservers.get(file);
        if(directoryobserver != null)
            break MISSING_BLOCK_LABEL_26;
        arraymap;
        JVM INSTR monitorexit ;
        return;
        DirectoryObserver._2D_set0(directoryobserver, DirectoryObserver._2D_get0(directoryobserver) - 1);
        if(DirectoryObserver._2D_get0(directoryobserver) == 0)
        {
            mObservers.remove(file);
            directoryobserver.stopWatching();
        }
        arraymap;
        JVM INSTR monitorexit ;
        return;
        file;
        throw file;
    }

    protected abstract Uri buildNotificationUri(String s);

    public String createDocument(String s, String s1, String s2)
        throws FileNotFoundException
    {
        s2 = FileUtils.buildValidFatFilename(s2);
        s = getFileForDocId(s);
        if(!s.isDirectory())
            throw new IllegalArgumentException("Parent document isn't a directory");
        s2 = FileUtils.buildUniqueFile(s, s1, s2);
        if(!"vnd.android.document/directory".equals(s1)) goto _L2; else goto _L1
_L1:
        if(!s2.mkdir())
            throw new IllegalStateException((new StringBuilder()).append("Failed to mkdir ").append(s2).toString());
        s = getDocIdForFile(s2);
        addFolderToMediaStore(getFileForDocId(s, true));
_L3:
        return s;
_L2:
        try
        {
            if(!s2.createNewFile())
            {
                s = JVM INSTR new #286 <Class IllegalStateException>;
                s1 = JVM INSTR new #172 <Class StringBuilder>;
                s1.StringBuilder();
                s.IllegalStateException(s1.append("Failed to touch ").append(s2).toString());
                throw s;
            }
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new IllegalStateException((new StringBuilder()).append("Failed to touch ").append(s2).append(": ").append(s).toString());
        }
        s = getDocIdForFile(s2);
          goto _L3
    }

    public void deleteDocument(String s)
        throws FileNotFoundException
    {
        File file = getFileForDocId(s);
        s = getFileForDocId(s, true);
        boolean flag = file.isDirectory();
        if(flag)
            FileUtils.deleteContents(file);
        if(!file.delete())
        {
            throw new IllegalStateException((new StringBuilder()).append("Failed to delete ").append(file).toString());
        } else
        {
            removeFromMediaStore(s, flag);
            return;
        }
    }

    protected final List findDocumentPath(File file, File file1)
        throws FileNotFoundException
    {
        if(!file1.exists())
            throw new FileNotFoundException((new StringBuilder()).append(file1).append(" is not found.").toString());
        if(!FileUtils.contains(file, file1))
            throw new FileNotFoundException((new StringBuilder()).append(file1).append(" is not found under ").append(file).toString());
        LinkedList linkedlist = new LinkedList();
        for(; file1 != null && FileUtils.contains(file, file1); file1 = file1.getParentFile())
            linkedlist.addFirst(getDocIdForFile(file1));

        return linkedlist;
    }

    protected abstract String getDocIdForFile(File file)
        throws FileNotFoundException;

    public Bundle getDocumentMetadata(String s, String as[])
        throws FileNotFoundException
    {
        File file;
        Bundle bundle;
        file = getFileForDocId(s);
        boolean flag;
        if(file.exists() && file.isFile())
            flag = file.canRead();
        else
            flag = false;
        if(!flag)
            return Bundle.EMPTY;
        s = file.getAbsolutePath();
        bundle = new Bundle();
        if(!getTypeForFile(file).equals("image/jpeg") && !getTypeForFile(file).equals("image/jpg"))
            break MISSING_BLOCK_LABEL_121;
        s = new FileInputStream(s);
        MetadataReader.getMetadata(bundle, s, getTypeForFile(file), as);
        IoUtils.closeQuietly(s);
        return bundle;
        as;
        Log.e("FileSystemProvider", "An error occurred retrieving the metadata", as);
        IoUtils.closeQuietly(s);
        return null;
        as;
        IoUtils.closeQuietly(s);
        throw as;
    }

    public String getDocumentType(String s)
        throws FileNotFoundException
    {
        return getTypeForFile(getFileForDocId(s));
    }

    protected final File getFileForDocId(String s)
        throws FileNotFoundException
    {
        return getFileForDocId(s, false);
    }

    protected abstract File getFileForDocId(String s, boolean flag)
        throws FileNotFoundException;

    protected android.database.MatrixCursor.RowBuilder includeFile(MatrixCursor matrixcursor, String s, File file)
        throws FileNotFoundException
    {
        int i;
        String s1;
        String s2;
        int j;
        long l;
        if(s == null)
            s = getDocIdForFile(file);
        else
            file = getFileForDocId(s);
        i = 0;
        if(file.canWrite())
            if(file.isDirectory())
                i = 8 | 4 | 0x40 | 0x100;
            else
                i = 2 | 4 | 0x40 | 0x100;
        s1 = getTypeForFile(file);
        s2 = file.getName();
        j = i;
        if(s1.startsWith("image/"))
            j = i | 1;
        matrixcursor = matrixcursor.newRow();
        matrixcursor.add("document_id", s);
        matrixcursor.add("_display_name", s2);
        matrixcursor.add("_size", Long.valueOf(file.length()));
        matrixcursor.add("mime_type", s1);
        matrixcursor.add("flags", Integer.valueOf(j));
        l = file.lastModified();
        if(l > 0x757b12c00L)
            matrixcursor.add("last_modified", Long.valueOf(l));
        return matrixcursor;
    }

    public boolean isChildDocument(String s, String s1)
    {
        boolean flag;
        try
        {
            flag = FileUtils.contains(getFileForDocId(s).getCanonicalFile(), getFileForDocId(s1).getCanonicalFile());
        }
        catch(IOException ioexception)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Failed to determine if ").append(s1).append(" is child of ").append(s).append(": ").append(ioexception).toString());
        }
        return flag;
    }

    void lambda$_2D_com_android_internal_content_FileSystemProvider_15674(File file, IOException ioexception)
    {
        scanFile(file);
    }

    public String moveDocument(String s, String s1, String s2)
        throws FileNotFoundException
    {
        s1 = getFileForDocId(s);
        s2 = new File(getFileForDocId(s2), s1.getName());
        s = getFileForDocId(s, true);
        if(s2.exists())
            throw new IllegalStateException((new StringBuilder()).append("Already exists ").append(s2).toString());
        if(!s1.renameTo(s2))
        {
            throw new IllegalStateException((new StringBuilder()).append("Failed to move to ").append(s2).toString());
        } else
        {
            s1 = getDocIdForFile(s2);
            moveInMediaStore(s, getFileForDocId(s1, true));
            return s1;
        }
    }

    protected void onCreate(String as[])
    {
        mHandler = new Handler();
        mDefaultProjection = as;
    }

    public boolean onCreate()
    {
        throw new UnsupportedOperationException("Subclass should override this and call onCreate(defaultDocumentProjection)");
    }

    public ParcelFileDescriptor openDocument(String s, String s1, CancellationSignal cancellationsignal)
        throws FileNotFoundException
    {
        cancellationsignal = getFileForDocId(s);
        s = getFileForDocId(s, true);
        int i = ParcelFileDescriptor.parseMode(s1);
        if(i == 0x10000000 || s == null)
            return ParcelFileDescriptor.open(cancellationsignal, i);
        try
        {
            s1 = mHandler;
            _.Lambda.qCDQZ4U5of2rgsneNEo3bc5KTII qcdqz4u5of2rgsneneo3bc5ktii = JVM INSTR new #490 <Class _$Lambda$qCDQZ4U5of2rgsneNEo3bc5KTII>;
            qcdqz4u5of2rgsneneo3bc5ktii._.Lambda.qCDQZ4U5of2rgsneNEo3bc5KTII(this, s);
            s = ParcelFileDescriptor.open(cancellationsignal, i, s1, qcdqz4u5of2rgsneneo3bc5ktii);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new FileNotFoundException((new StringBuilder()).append("Failed to open for writing: ").append(s).toString());
        }
        return s;
    }

    public AssetFileDescriptor openDocumentThumbnail(String s, Point point, CancellationSignal cancellationsignal)
        throws FileNotFoundException
    {
        return DocumentsContract.openImageThumbnail(getFileForDocId(s));
    }

    public Cursor queryChildDocuments(String s, String as[], String s1)
        throws FileNotFoundException
    {
        s1 = getFileForDocId(s);
        s = new DirectoryCursor(resolveProjection(as), s, s1);
        as = s1.listFiles();
        int i = 0;
        for(int j = as.length; i < j; i++)
            includeFile(s, null, as[i]);

        return s;
    }

    public Cursor queryDocument(String s, String as[])
        throws FileNotFoundException
    {
        as = new MatrixCursor(resolveProjection(as));
        includeFile(as, s, null);
        return as;
    }

    protected final Cursor querySearchDocuments(File file, String s, String as[], Set set)
        throws FileNotFoundException
    {
        s = s.toLowerCase();
        as = new MatrixCursor(resolveProjection(as));
        LinkedList linkedlist = new LinkedList();
        linkedlist.add(file);
        do
        {
            if(linkedlist.isEmpty() || as.getCount() >= 24)
                break;
            file = (File)linkedlist.removeFirst();
            if(file.isDirectory())
            {
                File afile[] = file.listFiles();
                int i = 0;
                for(int j = afile.length; i < j; i++)
                    linkedlist.add(afile[i]);

            }
            if(file.getName().toLowerCase().contains(s) && set.contains(file.getAbsolutePath()) ^ true)
                includeFile(as, null, file);
        } while(true);
        return as;
    }

    public String renameDocument(String s, String s1)
        throws FileNotFoundException
    {
        Object obj = FileUtils.buildValidFatFilename(s1);
        s1 = getFileForDocId(s);
        File file = FileUtils.buildUniqueFile(s1.getParentFile(), ((String) (obj)));
        obj = getFileForDocId(s, true);
        if(!s1.renameTo(file))
            throw new IllegalStateException((new StringBuilder()).append("Failed to rename to ").append(file).toString());
        s1 = getDocIdForFile(file);
        moveInMediaStore(((File) (obj)), getFileForDocId(s1, true));
        if(!TextUtils.equals(s, s1))
            return s1;
        else
            return null;
    }

    static final boolean _2D_assertionsDisabled = com/android/internal/content/FileSystemProvider.desiredAssertionStatus() ^ true;
    private static final boolean LOG_INOTIFY = false;
    private static final String MIMETYPE_JPEG = "image/jpeg";
    private static final String MIMETYPE_JPG = "image/jpg";
    private static final String TAG = "FileSystemProvider";
    private String mDefaultProjection[];
    private Handler mHandler;
    private final ArrayMap mObservers = new ArrayMap();

}
