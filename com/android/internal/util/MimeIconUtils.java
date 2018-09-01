// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import java.util.HashMap;

public class MimeIconUtils
{

    public MimeIconUtils()
    {
    }

    private static void add(String s, int i)
    {
        if(sMimeIcons.put(s, Integer.valueOf(i)) != null)
            throw new RuntimeException((new StringBuilder()).append(s).append(" already registered!").toString());
        else
            return;
    }

    public static Drawable loadMimeIcon(Context context, String s)
    {
        if("vnd.android.document/directory".equals(s))
            return context.getDrawable(0x1080352);
        Integer integer = (Integer)sMimeIcons.get(s);
        if(integer != null)
            return context.getDrawable(integer.intValue());
        if(s == null)
            return null;
        s = s.split("/")[0];
        if("audio".equals(s))
            return context.getDrawable(0x108034a);
        if("image".equals(s))
            return context.getDrawable(0x1080355);
        if("text".equals(s))
            return context.getDrawable(0x108035a);
        if("video".equals(s))
            return context.getDrawable(0x108035b);
        else
            return context.getDrawable(0x1080354);
    }

    private static HashMap sMimeIcons = new HashMap();

    static 
    {
        add("application/vnd.android.package-archive", 0x1080349);
        add("application/ogg", 0x108034a);
        add("application/x-flac", 0x108034a);
        add("application/pgp-keys", 0x108034b);
        add("application/pgp-signature", 0x108034b);
        add("application/x-pkcs12", 0x108034b);
        add("application/x-pkcs7-certreqresp", 0x108034b);
        add("application/x-pkcs7-crl", 0x108034b);
        add("application/x-x509-ca-cert", 0x108034b);
        add("application/x-x509-user-cert", 0x108034b);
        add("application/x-pkcs7-certificates", 0x108034b);
        add("application/x-pkcs7-mime", 0x108034b);
        add("application/x-pkcs7-signature", 0x108034b);
        add("application/rdf+xml", 0x108034c);
        add("application/rss+xml", 0x108034c);
        add("application/x-object", 0x108034c);
        add("application/xhtml+xml", 0x108034c);
        add("text/css", 0x108034c);
        add("text/html", 0x108034c);
        add("text/xml", 0x108034c);
        add("text/x-c++hdr", 0x108034c);
        add("text/x-c++src", 0x108034c);
        add("text/x-chdr", 0x108034c);
        add("text/x-csrc", 0x108034c);
        add("text/x-dsrc", 0x108034c);
        add("text/x-csh", 0x108034c);
        add("text/x-haskell", 0x108034c);
        add("text/x-java", 0x108034c);
        add("text/x-literate-haskell", 0x108034c);
        add("text/x-pascal", 0x108034c);
        add("text/x-tcl", 0x108034c);
        add("text/x-tex", 0x108034c);
        add("application/x-latex", 0x108034c);
        add("application/x-texinfo", 0x108034c);
        add("application/atom+xml", 0x108034c);
        add("application/ecmascript", 0x108034c);
        add("application/json", 0x108034c);
        add("application/javascript", 0x108034c);
        add("application/xml", 0x108034c);
        add("text/javascript", 0x108034c);
        add("application/x-javascript", 0x108034c);
        add("application/mac-binhex40", 0x108034d);
        add("application/rar", 0x108034d);
        add("application/zip", 0x108034d);
        add("application/x-apple-diskimage", 0x108034d);
        add("application/x-debian-package", 0x108034d);
        add("application/x-gtar", 0x108034d);
        add("application/x-iso9660-image", 0x108034d);
        add("application/x-lha", 0x108034d);
        add("application/x-lzh", 0x108034d);
        add("application/x-lzx", 0x108034d);
        add("application/x-stuffit", 0x108034d);
        add("application/x-tar", 0x108034d);
        add("application/x-webarchive", 0x108034d);
        add("application/x-webarchive-xml", 0x108034d);
        add("application/gzip", 0x108034d);
        add("application/x-7z-compressed", 0x108034d);
        add("application/x-deb", 0x108034d);
        add("application/x-rar-compressed", 0x108034d);
        add("text/x-vcard", 0x108034e);
        add("text/vcard", 0x108034e);
        add("text/calendar", 0x1080350);
        add("text/x-vcalendar", 0x1080350);
        add("application/x-font", 0x1080353);
        add("application/font-woff", 0x1080353);
        add("application/x-font-woff", 0x1080353);
        add("application/x-font-ttf", 0x1080353);
        add("application/vnd.oasis.opendocument.graphics", 0x1080355);
        add("application/vnd.oasis.opendocument.graphics-template", 0x1080355);
        add("application/vnd.oasis.opendocument.image", 0x1080355);
        add("application/vnd.stardivision.draw", 0x1080355);
        add("application/vnd.sun.xml.draw", 0x1080355);
        add("application/vnd.sun.xml.draw.template", 0x1080355);
        add("application/pdf", 0x1080356);
        add("application/vnd.stardivision.impress", 0x1080358);
        add("application/vnd.sun.xml.impress", 0x1080358);
        add("application/vnd.sun.xml.impress.template", 0x1080358);
        add("application/x-kpresenter", 0x1080358);
        add("application/vnd.oasis.opendocument.presentation", 0x1080358);
        add("application/vnd.oasis.opendocument.spreadsheet", 0x1080359);
        add("application/vnd.oasis.opendocument.spreadsheet-template", 0x1080359);
        add("application/vnd.stardivision.calc", 0x1080359);
        add("application/vnd.sun.xml.calc", 0x1080359);
        add("application/vnd.sun.xml.calc.template", 0x1080359);
        add("application/x-kspread", 0x1080359);
        add("application/vnd.oasis.opendocument.text", 0x108034f);
        add("application/vnd.oasis.opendocument.text-master", 0x108034f);
        add("application/vnd.oasis.opendocument.text-template", 0x108034f);
        add("application/vnd.oasis.opendocument.text-web", 0x108034f);
        add("application/vnd.stardivision.writer", 0x108034f);
        add("application/vnd.stardivision.writer-global", 0x108034f);
        add("application/vnd.sun.xml.writer", 0x108034f);
        add("application/vnd.sun.xml.writer.global", 0x108034f);
        add("application/vnd.sun.xml.writer.template", 0x108034f);
        add("application/x-abiword", 0x108034f);
        add("application/x-kword", 0x108034f);
        add("application/x-quicktimeplayer", 0x108035b);
        add("application/x-shockwave-flash", 0x108035b);
        add("application/msword", 0x108035c);
        add("application/vnd.openxmlformats-officedocument.wordprocessingml.document", 0x108035c);
        add("application/vnd.openxmlformats-officedocument.wordprocessingml.template", 0x108035c);
        add("application/vnd.ms-excel", 0x1080351);
        add("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", 0x1080351);
        add("application/vnd.openxmlformats-officedocument.spreadsheetml.template", 0x1080351);
        add("application/vnd.ms-powerpoint", 0x1080357);
        add("application/vnd.openxmlformats-officedocument.presentationml.presentation", 0x1080357);
        add("application/vnd.openxmlformats-officedocument.presentationml.template", 0x1080357);
        add("application/vnd.openxmlformats-officedocument.presentationml.slideshow", 0x1080357);
    }
}
