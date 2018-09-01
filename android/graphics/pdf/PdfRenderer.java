// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics.pdf;

import android.graphics.*;
import android.os.ParcelFileDescriptor;
import android.system.*;
import com.android.internal.util.Preconditions;
import dalvik.system.CloseGuard;
import java.io.IOException;
import libcore.io.Libcore;
import libcore.io.Os;

public final class PdfRenderer
    implements AutoCloseable
{
    public final class Page
        implements AutoCloseable
    {

        private void doClose()
        {
            Object obj = PdfRenderer.sPdfiumLock;
            obj;
            JVM INSTR monitorenter ;
            PdfRenderer._2D_wrap1(mNativePage);
            obj;
            JVM INSTR monitorexit ;
            mNativePage = 0L;
            mCloseGuard.close();
            PdfRenderer._2D_set0(PdfRenderer.this, null);
            return;
            Exception exception;
            exception;
            throw exception;
        }

        private void throwIfClosed()
        {
            if(mNativePage == 0L)
                throw new IllegalStateException("Already closed");
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
            if(mNativePage != 0L)
                doClose();
            super.finalize();
            return;
            Exception exception;
            exception;
            super.finalize();
            throw exception;
        }

        public int getHeight()
        {
            return mHeight;
        }

        public int getIndex()
        {
            return mIndex;
        }

        public int getWidth()
        {
            return mWidth;
        }

        public void render(Bitmap bitmap, Rect rect, Matrix matrix, int i)
        {
            if(mNativePage == 0L)
                throw new NullPointerException();
            Bitmap bitmap1 = (Bitmap)Preconditions.checkNotNull(bitmap, "bitmap null");
            if(bitmap1.getConfig() != android.graphics.Bitmap.Config.ARGB_8888)
                throw new IllegalArgumentException("Unsupported pixel format");
            while(rect != null && (rect.left < 0 || rect.top < 0 || rect.right > bitmap1.getWidth() || rect.bottom > bitmap1.getHeight())) 
                throw new IllegalArgumentException("destBounds not in destination");
            if(matrix != null && matrix.isAffine() ^ true)
                throw new IllegalArgumentException("transform not affine");
            if(i != 2 && i != 1)
                throw new IllegalArgumentException("Unsupported render mode");
            if(i == 2 && i == 1)
                throw new IllegalArgumentException("Only single render mode supported");
            int j;
            int k;
            int l;
            int i1;
            long l1;
            if(rect != null)
                j = rect.left;
            else
                j = 0;
            if(rect != null)
                k = rect.top;
            else
                k = 0;
            if(rect != null)
                l = rect.right;
            else
                l = bitmap1.getWidth();
            if(rect != null)
                i1 = rect.bottom;
            else
                i1 = bitmap1.getHeight();
            bitmap = matrix;
            if(matrix == null)
            {
                bitmap = new Matrix();
                bitmap.postScale((float)(l - j) / (float)getWidth(), (float)(i1 - k) / (float)getHeight());
                bitmap.postTranslate(j, k);
            }
            l1 = ((Matrix) (bitmap)).native_instance;
            bitmap = ((Bitmap) (PdfRenderer.sPdfiumLock));
            bitmap;
            JVM INSTR monitorenter ;
            PdfRenderer._2D_wrap2(PdfRenderer._2D_get0(PdfRenderer.this), mNativePage, bitmap1, j, k, l, i1, l1, i);
            bitmap;
            JVM INSTR monitorexit ;
            return;
            rect;
            throw rect;
        }

        public static final int RENDER_MODE_FOR_DISPLAY = 1;
        public static final int RENDER_MODE_FOR_PRINT = 2;
        private final CloseGuard mCloseGuard;
        private final int mHeight;
        private final int mIndex;
        private long mNativePage;
        private final int mWidth;
        final PdfRenderer this$0;

        private Page(int i)
        {
            Point point;
            this$0 = PdfRenderer.this;
            super();
            mCloseGuard = CloseGuard.get();
            point = PdfRenderer._2D_get1(PdfRenderer.this);
            Object obj = PdfRenderer.sPdfiumLock;
            obj;
            JVM INSTR monitorenter ;
            mNativePage = PdfRenderer._2D_wrap0(PdfRenderer._2D_get0(PdfRenderer.this), i, point);
            obj;
            JVM INSTR monitorexit ;
            mIndex = i;
            mWidth = point.x;
            mHeight = point.y;
            mCloseGuard.open("close");
            return;
            pdfrenderer;
            throw PdfRenderer.this;
        }

        Page(int i, Page page)
        {
            this(i);
        }
    }


    static long _2D_get0(PdfRenderer pdfrenderer)
    {
        return pdfrenderer.mNativeDocument;
    }

    static Point _2D_get1(PdfRenderer pdfrenderer)
    {
        return pdfrenderer.mTempPoint;
    }

    static Page _2D_set0(PdfRenderer pdfrenderer, Page page)
    {
        pdfrenderer.mCurrentPage = page;
        return page;
    }

    static long _2D_wrap0(long l, int i, Point point)
    {
        return nativeOpenPageAndGetSize(l, i, point);
    }

    static void _2D_wrap1(long l)
    {
        nativeClosePage(l);
    }

    static void _2D_wrap2(long l, long l1, Bitmap bitmap, int i, int j, int k, 
            int i1, long l2, int j1)
    {
        nativeRenderPage(l, l1, bitmap, i, j, k, i1, l2, j1);
    }

    public PdfRenderer(ParcelFileDescriptor parcelfiledescriptor)
        throws IOException
    {
        mCloseGuard = CloseGuard.get();
        mTempPoint = new Point();
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
        parcelfiledescriptor = ((ParcelFileDescriptor) (sPdfiumLock));
        parcelfiledescriptor;
        JVM INSTR monitorenter ;
        mNativeDocument = nativeCreate(mInput.getFd(), l);
        mPageCount = nativeGetPageCount(mNativeDocument);
        parcelfiledescriptor;
        JVM INSTR monitorexit ;
        mCloseGuard.open("close");
        return;
        Object obj;
        obj;
        nativeClose(mNativeDocument);
        throw obj;
        obj;
        parcelfiledescriptor;
        JVM INSTR monitorexit ;
        throw obj;
    }

    private void doClose()
    {
        if(mCurrentPage != null)
            mCurrentPage.close();
        Object obj = sPdfiumLock;
        obj;
        JVM INSTR monitorenter ;
        nativeClose(mNativeDocument);
        obj;
        JVM INSTR monitorexit ;
        Exception exception;
        try
        {
            mInput.close();
        }
        catch(IOException ioexception) { }
        mInput = null;
        mCloseGuard.close();
        return;
        exception;
        throw exception;
    }

    private static native void nativeClose(long l);

    private static native void nativeClosePage(long l);

    private static native long nativeCreate(int i, long l);

    private static native int nativeGetPageCount(long l);

    private static native long nativeOpenPageAndGetSize(long l, int i, Point point);

    private static native void nativeRenderPage(long l, long l1, Bitmap bitmap, int i, int j, int k, 
            int i1, long l2, int j1);

    private static native boolean nativeScaleForPrinting(long l);

    private void throwIfClosed()
    {
        if(mInput == null)
            throw new IllegalStateException("Already closed");
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

    private void throwIfPageOpened()
    {
        if(mCurrentPage != null)
            throw new IllegalStateException("Current page not closed");
        else
            return;
    }

    public void close()
    {
        throwIfClosed();
        throwIfPageOpened();
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

    public Page openPage(int i)
    {
        throwIfClosed();
        throwIfPageOpened();
        throwIfPageNotInDocument(i);
        mCurrentPage = new Page(i, null);
        return mCurrentPage;
    }

    public boolean shouldScaleForPrinting()
    {
        throwIfClosed();
        Object obj = sPdfiumLock;
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

    static final Object sPdfiumLock = new Object();
    private final CloseGuard mCloseGuard;
    private Page mCurrentPage;
    private ParcelFileDescriptor mInput;
    private final long mNativeDocument;
    private final int mPageCount;
    private final Point mTempPoint;

}
