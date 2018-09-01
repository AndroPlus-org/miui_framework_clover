// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics.pdf;

import android.graphics.*;
import dalvik.system.CloseGuard;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

public class PdfDocument
{
    public static final class Page
    {

        static void _2D_wrap0(Page page)
        {
            page.finish();
        }

        private void finish()
        {
            if(mCanvas != null)
            {
                mCanvas.release();
                mCanvas = null;
            }
        }

        public Canvas getCanvas()
        {
            return mCanvas;
        }

        public PageInfo getInfo()
        {
            return mPageInfo;
        }

        boolean isFinished()
        {
            boolean flag;
            if(mCanvas == null)
                flag = true;
            else
                flag = false;
            return flag;
        }

        private Canvas mCanvas;
        private final PageInfo mPageInfo;

        private Page(Canvas canvas, PageInfo pageinfo)
        {
            mCanvas = canvas;
            mPageInfo = pageinfo;
        }

        Page(Canvas canvas, PageInfo pageinfo, Page page)
        {
            this(canvas, pageinfo);
        }
    }

    public static final class PageInfo
    {

        static Rect _2D_get0(PageInfo pageinfo)
        {
            return pageinfo.mContentRect;
        }

        static int _2D_get1(PageInfo pageinfo)
        {
            return pageinfo.mPageHeight;
        }

        static int _2D_get2(PageInfo pageinfo)
        {
            return pageinfo.mPageWidth;
        }

        static Rect _2D_set0(PageInfo pageinfo, Rect rect)
        {
            pageinfo.mContentRect = rect;
            return rect;
        }

        static int _2D_set1(PageInfo pageinfo, int i)
        {
            pageinfo.mPageHeight = i;
            return i;
        }

        static int _2D_set2(PageInfo pageinfo, int i)
        {
            pageinfo.mPageNumber = i;
            return i;
        }

        static int _2D_set3(PageInfo pageinfo, int i)
        {
            pageinfo.mPageWidth = i;
            return i;
        }

        public Rect getContentRect()
        {
            return mContentRect;
        }

        public int getPageHeight()
        {
            return mPageHeight;
        }

        public int getPageNumber()
        {
            return mPageNumber;
        }

        public int getPageWidth()
        {
            return mPageWidth;
        }

        private Rect mContentRect;
        private int mPageHeight;
        private int mPageNumber;
        private int mPageWidth;

        private PageInfo()
        {
        }

        PageInfo(PageInfo pageinfo)
        {
            this();
        }
    }

    public static final class PageInfo.Builder
    {

        public PageInfo create()
        {
            if(PageInfo._2D_get0(mPageInfo) == null)
                PageInfo._2D_set0(mPageInfo, new Rect(0, 0, PageInfo._2D_get2(mPageInfo), PageInfo._2D_get1(mPageInfo)));
            return mPageInfo;
        }

        public PageInfo.Builder setContentRect(Rect rect)
        {
            while(rect != null && (rect.left < 0 || rect.top < 0 || rect.right > PageInfo._2D_get2(mPageInfo) || rect.bottom > PageInfo._2D_get1(mPageInfo))) 
                throw new IllegalArgumentException("contentRect does not fit the page");
            PageInfo._2D_set0(mPageInfo, rect);
            return this;
        }

        private final PageInfo mPageInfo = new PageInfo(null);

        public PageInfo.Builder(int i, int j, int k)
        {
            if(i <= 0)
                throw new IllegalArgumentException("page width must be positive");
            if(j <= 0)
                throw new IllegalArgumentException("page width must be positive");
            if(k < 0)
            {
                throw new IllegalArgumentException("pageNumber must be non negative");
            } else
            {
                PageInfo._2D_set3(mPageInfo, i);
                PageInfo._2D_set1(mPageInfo, j);
                PageInfo._2D_set2(mPageInfo, k);
                return;
            }
        }
    }

    private final class PdfCanvas extends Canvas
    {

        public void setBitmap(Bitmap bitmap)
        {
            throw new UnsupportedOperationException();
        }

        final PdfDocument this$0;

        public PdfCanvas(long l)
        {
            this$0 = PdfDocument.this;
            super(l);
        }
    }


    public PdfDocument()
    {
        mNativeDocument = nativeCreateDocument();
        mCloseGuard.open("close");
    }

    private void dispose()
    {
        if(mNativeDocument != 0L)
        {
            nativeClose(mNativeDocument);
            mCloseGuard.close();
            mNativeDocument = 0L;
        }
    }

    private native void nativeClose(long l);

    private native long nativeCreateDocument();

    private native void nativeFinishPage(long l);

    private static native long nativeStartPage(long l, int i, int j, int k, int i1, int j1, int k1);

    private native void nativeWriteTo(long l, OutputStream outputstream, byte abyte0[]);

    private void throwIfClosed()
    {
        if(mNativeDocument == 0L)
            throw new IllegalStateException("document is closed!");
        else
            return;
    }

    private void throwIfCurrentPageNotFinished()
    {
        if(mCurrentPage != null)
            throw new IllegalStateException("Current page not finished!");
        else
            return;
    }

    public void close()
    {
        throwIfCurrentPageNotFinished();
        dispose();
    }

    protected void finalize()
        throws Throwable
    {
        if(mCloseGuard != null)
            mCloseGuard.warnIfOpen();
        dispose();
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public void finishPage(Page page)
    {
        throwIfClosed();
        if(page == null)
            throw new IllegalArgumentException("page cannot be null");
        if(page != mCurrentPage)
            throw new IllegalStateException("invalid page");
        if(page.isFinished())
        {
            throw new IllegalStateException("page already finished");
        } else
        {
            mPages.add(page.getInfo());
            mCurrentPage = null;
            nativeFinishPage(mNativeDocument);
            Page._2D_wrap0(page);
            return;
        }
    }

    public List getPages()
    {
        return Collections.unmodifiableList(mPages);
    }

    public Page startPage(PageInfo pageinfo)
    {
        throwIfClosed();
        throwIfCurrentPageNotFinished();
        if(pageinfo == null)
        {
            throw new IllegalArgumentException("page cannot be null");
        } else
        {
            mCurrentPage = new Page(new PdfCanvas(nativeStartPage(mNativeDocument, PageInfo._2D_get2(pageinfo), PageInfo._2D_get1(pageinfo), PageInfo._2D_get0(pageinfo).left, PageInfo._2D_get0(pageinfo).top, PageInfo._2D_get0(pageinfo).right, PageInfo._2D_get0(pageinfo).bottom)), pageinfo, null);
            return mCurrentPage;
        }
    }

    public void writeTo(OutputStream outputstream)
        throws IOException
    {
        throwIfClosed();
        throwIfCurrentPageNotFinished();
        if(outputstream == null)
        {
            throw new IllegalArgumentException("out cannot be null!");
        } else
        {
            nativeWriteTo(mNativeDocument, outputstream, mChunk);
            return;
        }
    }

    private final byte mChunk[] = new byte[4096];
    private final CloseGuard mCloseGuard = CloseGuard.get();
    private Page mCurrentPage;
    private long mNativeDocument;
    private final List mPages = new ArrayList();
}
