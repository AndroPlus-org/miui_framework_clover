// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics.pdf;

import android.graphics.*;
import android.os.ParcelFileDescriptor;
import android.system.*;
import dalvik.system.CloseGuard;
import java.io.IOException;
import libcore.io.*;

// Referenced classes of package android.graphics.pdf:
//            PdfRenderer

public final class PdfEditor
{

    public PdfEditor(ParcelFileDescriptor parcelfiledescriptor)
        throws IOException
    {
        Object obj;
        mCloseGuard = CloseGuard.get();
        if(parcelfiledescriptor == null)
            throw new NullPointerException("input cannot be null");
        long l;
        try
        {
            Libcore.os.lseek(parcelfiledescriptor.getFileDescriptor(), 0L, OsConstants.SEEK_SET);
            l = Libcore.os.fstat(parcelfiledescriptor.getFileDescriptor()).st_size;
        }
        // Misplaced declaration of an exception variable
        catch(ParcelFileDescriptor parcelfiledescriptor)
        {
            throw new IllegalArgumentException("file descriptor not seekable");
        }
        mInput = parcelfiledescriptor;
        obj = PdfRenderer.sPdfiumLock;
        obj;
        JVM INSTR monitorenter ;
        mNativeDocument = nativeOpen(mInput.getFd(), l);
        mPageCount = nativeGetPageCount(mNativeDocument);
        obj;
        JVM INSTR monitorexit ;
        mCloseGuard.open("close");
        return;
        parcelfiledescriptor;
        throw parcelfiledescriptor;
    }

    private void doClose()
    {
        Object obj = PdfRenderer.sPdfiumLock;
        obj;
        JVM INSTR monitorenter ;
        nativeClose(mNativeDocument);
        obj;
        JVM INSTR monitorexit ;
        IoUtils.closeQuietly(mInput);
        mInput = null;
        mCloseGuard.close();
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private static native void nativeClose(long l);

    private static native int nativeGetPageCount(long l);

    private static native boolean nativeGetPageCropBox(long l, int i, Rect rect);

    private static native boolean nativeGetPageMediaBox(long l, int i, Rect rect);

    private static native void nativeGetPageSize(long l, int i, Point point);

    private static native long nativeOpen(int i, long l);

    private static native int nativeRemovePage(long l, int i);

    private static native boolean nativeScaleForPrinting(long l);

    private static native void nativeSetPageCropBox(long l, int i, Rect rect);

    private static native void nativeSetPageMediaBox(long l, int i, Rect rect);

    private static native void nativeSetTransformAndClip(long l, int i, long l1, int j, int k, int i1, 
            int j1);

    private static native void nativeWrite(long l, int i);

    private void throwIfClosed()
    {
        if(mInput == null)
            throw new IllegalStateException("Already closed");
        else
            return;
    }

    private void throwIfCropBoxNull(Rect rect)
    {
        if(rect == null)
            throw new NullPointerException("cropBox cannot be null");
        else
            return;
    }

    private void throwIfMediaBoxNull(Rect rect)
    {
        if(rect == null)
            throw new NullPointerException("mediaBox cannot be null");
        else
            return;
    }

    private void throwIfNotNullAndNotAfine(Matrix matrix)
    {
        if(matrix != null && matrix.isAffine() ^ true)
            throw new IllegalStateException("Matrix must be afine");
        else
            return;
    }

    private void throwIfOutCropBoxNull(Rect rect)
    {
        if(rect == null)
            throw new NullPointerException("outCropBox cannot be null");
        else
            return;
    }

    private void throwIfOutMediaBoxNull(Rect rect)
    {
        if(rect == null)
            throw new NullPointerException("outMediaBox cannot be null");
        else
            return;
    }

    private void throwIfOutSizeNull(Point point)
    {
        if(point == null)
            throw new NullPointerException("outSize cannot be null");
        else
            return;
    }

    private void throwIfPageNotInDocument(int i)
    {
        if(i < 0 || i >= mPageCount)
            throw new IllegalArgumentException("Invalid page index");
        else
            return;
    }

    public void close()
    {
        throwIfClosed();
        doClose();
    }

    protected void finalize()
        throws Throwable
    {
        if(mCloseGuard != null)
            mCloseGuard.warnIfOpen();
        if(mInput != null)
            doClose();
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public int getPageCount()
    {
        throwIfClosed();
        return mPageCount;
    }

    public boolean getPageCropBox(int i, Rect rect)
    {
        throwIfClosed();
        throwIfOutCropBoxNull(rect);
        throwIfPageNotInDocument(i);
        Object obj = PdfRenderer.sPdfiumLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag = nativeGetPageCropBox(mNativeDocument, i, rect);
        obj;
        JVM INSTR monitorexit ;
        return flag;
        rect;
        throw rect;
    }

    public boolean getPageMediaBox(int i, Rect rect)
    {
        throwIfClosed();
        throwIfOutMediaBoxNull(rect);
        throwIfPageNotInDocument(i);
        Object obj = PdfRenderer.sPdfiumLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag = nativeGetPageMediaBox(mNativeDocument, i, rect);
        obj;
        JVM INSTR monitorexit ;
        return flag;
        rect;
        throw rect;
    }

    public void getPageSize(int i, Point point)
    {
        throwIfClosed();
        throwIfOutSizeNull(point);
        throwIfPageNotInDocument(i);
        Object obj = PdfRenderer.sPdfiumLock;
        obj;
        JVM INSTR monitorenter ;
        nativeGetPageSize(mNativeDocument, i, point);
        obj;
        JVM INSTR monitorexit ;
        return;
        point;
        throw point;
    }

    public void removePage(int i)
    {
        throwIfClosed();
        throwIfPageNotInDocument(i);
        Object obj = PdfRenderer.sPdfiumLock;
        obj;
        JVM INSTR monitorenter ;
        mPageCount = nativeRemovePage(mNativeDocument, i);
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setPageCropBox(int i, Rect rect)
    {
        throwIfClosed();
        throwIfCropBoxNull(rect);
        throwIfPageNotInDocument(i);
        Object obj = PdfRenderer.sPdfiumLock;
        obj;
        JVM INSTR monitorenter ;
        nativeSetPageCropBox(mNativeDocument, i, rect);
        obj;
        JVM INSTR monitorexit ;
        return;
        rect;
        throw rect;
    }

    public void setPageMediaBox(int i, Rect rect)
    {
        throwIfClosed();
        throwIfMediaBoxNull(rect);
        throwIfPageNotInDocument(i);
        Object obj = PdfRenderer.sPdfiumLock;
        obj;
        JVM INSTR monitorenter ;
        nativeSetPageMediaBox(mNativeDocument, i, rect);
        obj;
        JVM INSTR monitorexit ;
        return;
        rect;
        throw rect;
    }

    public void setTransformAndClip(int i, Matrix matrix, Rect rect)
    {
        Matrix matrix1;
        throwIfClosed();
        throwIfPageNotInDocument(i);
        throwIfNotNullAndNotAfine(matrix);
        matrix1 = matrix;
        if(matrix == null)
            matrix1 = Matrix.IDENTITY_MATRIX;
        if(rect != null) goto _L2; else goto _L1
_L1:
        rect = new Point();
        getPageSize(i, rect);
        matrix = ((Matrix) (PdfRenderer.sPdfiumLock));
        matrix;
        JVM INSTR monitorenter ;
        nativeSetTransformAndClip(mNativeDocument, i, matrix1.native_instance, 0, 0, ((Point) (rect)).x, ((Point) (rect)).y);
_L3:
        matrix;
        JVM INSTR monitorexit ;
        return;
        rect;
        throw rect;
_L2:
        matrix = ((Matrix) (PdfRenderer.sPdfiumLock));
        matrix;
        JVM INSTR monitorenter ;
        nativeSetTransformAndClip(mNativeDocument, i, matrix1.native_instance, rect.left, rect.top, rect.right, rect.bottom);
          goto _L3
        rect;
        throw rect;
    }

    public boolean shouldScaleForPrinting()
    {
        throwIfClosed();
        Object obj = PdfRenderer.sPdfiumLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag = nativeScaleForPrinting(mNativeDocument);
        obj;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public void write(ParcelFileDescriptor parcelfiledescriptor)
        throws IOException
    {
        throwIfClosed();
        Object obj = PdfRenderer.sPdfiumLock;
        obj;
        JVM INSTR monitorenter ;
        nativeWrite(mNativeDocument, parcelfiledescriptor.getFd());
        obj;
        JVM INSTR monitorexit ;
        IoUtils.closeQuietly(parcelfiledescriptor);
        return;
        Exception exception1;
        exception1;
        obj;
        JVM INSTR monitorexit ;
        throw exception1;
        Exception exception;
        exception;
        IoUtils.closeQuietly(parcelfiledescriptor);
        throw exception;
    }

    private final CloseGuard mCloseGuard;
    private ParcelFileDescriptor mInput;
    private final long mNativeDocument;
    private int mPageCount;
}
