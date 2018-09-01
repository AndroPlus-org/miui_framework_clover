// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.content;

import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.pdf.PdfRenderer;
import android.os.AsyncTask;
import android.os.ParcelFileDescriptor;
import java.io.*;
import libcore.io.IoUtils;
import libcore.io.Streams;

public final class PdfUtils
{

    private PdfUtils()
    {
    }

    public static AssetFileDescriptor openPdfThumbnail(File file, Point point)
        throws IOException
    {
        file = ParcelFileDescriptor.open(file, 0x10000000);
        Object obj = (new PdfRenderer(file)).openPage(0);
        point = Bitmap.createBitmap(point.x, point.y, android.graphics.Bitmap.Config.ARGB_8888);
        ((android.graphics.pdf.PdfRenderer.Page) (obj)).render(point, null, null, 1);
        obj = new ByteArrayOutputStream();
        point.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, ((java.io.OutputStream) (obj)));
        obj = new ByteArrayInputStream(((ByteArrayOutputStream) (obj)).toByteArray());
        point = ParcelFileDescriptor.createReliablePipe();
        (new AsyncTask(point, ((ByteArrayInputStream) (obj)), file) {

            protected transient Object doInBackground(Object aobj[])
            {
                aobj = new FileOutputStream(fds[1].getFileDescriptor());
                try
                {
                    Streams.copy(in, ((java.io.OutputStream) (aobj)));
                }
                // Misplaced declaration of an exception variable
                catch(Object aobj[])
                {
                    throw new RuntimeException(((Throwable) (aobj)));
                }
                IoUtils.closeQuietly(fds[1]);
                try
                {
                    pdfDescriptor.close();
                }
                // Misplaced declaration of an exception variable
                catch(Object aobj[])
                {
                    throw new RuntimeException(((Throwable) (aobj)));
                }
                return null;
            }

            final ParcelFileDescriptor val$fds[];
            final ByteArrayInputStream val$in;
            final ParcelFileDescriptor val$pdfDescriptor;

            
            {
                fds = aparcelfiledescriptor;
                in = bytearrayinputstream;
                pdfDescriptor = parcelfiledescriptor;
                super();
            }
        }
).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
        file.close();
        return new AssetFileDescriptor(point[0], 0L, -1L);
    }
}
