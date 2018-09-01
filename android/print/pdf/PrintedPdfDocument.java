// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.print.pdf;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.pdf.PdfDocument;
import android.print.PrintAttributes;

public class PrintedPdfDocument extends PdfDocument
{

    public PrintedPdfDocument(Context context, PrintAttributes printattributes)
    {
        context = printattributes.getMediaSize();
        mPageWidth = (int)(((float)context.getWidthMils() / 1000F) * 72F);
        mPageHeight = (int)(((float)context.getHeightMils() / 1000F) * 72F);
        context = printattributes.getMinMargins();
        int i = (int)(((float)context.getLeftMils() / 1000F) * 72F);
        int j = (int)(((float)context.getTopMils() / 1000F) * 72F);
        int k = (int)(((float)context.getRightMils() / 1000F) * 72F);
        int l = (int)(((float)context.getBottomMils() / 1000F) * 72F);
        mContentRect = new Rect(i, j, mPageWidth - k, mPageHeight - l);
    }

    public Rect getPageContentRect()
    {
        return mContentRect;
    }

    public int getPageHeight()
    {
        return mPageHeight;
    }

    public int getPageWidth()
    {
        return mPageWidth;
    }

    public android.graphics.pdf.PdfDocument.Page startPage(int i)
    {
        return startPage((new android.graphics.pdf.PdfDocument.PageInfo.Builder(mPageWidth, mPageHeight, i)).setContentRect(mContentRect).create());
    }

    private static final int MILS_PER_INCH = 1000;
    private static final int POINTS_IN_INCH = 72;
    private final Rect mContentRect;
    private final int mPageHeight;
    private final int mPageWidth;
}
