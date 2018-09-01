// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.http;

import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.android.internal.util.HexDump;
import com.android.org.bouncycastle.asn1.x509.X509Name;
import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class SslCertificate
{
    public class DName
    {

        public String getCName()
        {
            String s;
            if(mCName != null)
                s = mCName;
            else
                s = "";
            return s;
        }

        public String getDName()
        {
            String s;
            if(mDName != null)
                s = mDName;
            else
                s = "";
            return s;
        }

        public String getOName()
        {
            String s;
            if(mOName != null)
                s = mOName;
            else
                s = "";
            return s;
        }

        public String getUName()
        {
            String s;
            if(mUName != null)
                s = mUName;
            else
                s = "";
            return s;
        }

        private String mCName;
        private String mDName;
        private String mOName;
        private String mUName;
        final SslCertificate this$0;

        public DName(String s)
        {
            this$0 = SslCertificate.this;
            super();
            if(s == null) goto _L2; else goto _L1
_L1:
            mDName = s;
            sslcertificate = JVM INSTR new #27  <Class X509Name>;
            super(s);
            s = getValues();
            sslcertificate = getOIDs();
            int i = 0;
_L6:
            if(i >= size()) goto _L2; else goto _L3
_L3:
            if(!elementAt(i).equals(X509Name.CN)) goto _L5; else goto _L4
_L4:
            if(mCName == null)
                mCName = (String)s.elementAt(i);
_L9:
            i++;
              goto _L6
_L5:
            if(!elementAt(i).equals(X509Name.O) || mOName != null) goto _L8; else goto _L7
_L7:
            mOName = (String)s.elementAt(i);
              goto _L9
            sslcertificate;
_L2:
            return;
_L8:
            if(!elementAt(i).equals(X509Name.OU) || mUName != null) goto _L9; else goto _L10
_L10:
            mUName = (String)s.elementAt(i);
              goto _L9
        }
    }


    public SslCertificate(String s, String s1, String s2, String s3)
    {
        this(s, s1, parseDate(s2), parseDate(s3), null);
    }

    public SslCertificate(String s, String s1, Date date, Date date1)
    {
        this(s, s1, date, date1, null);
    }

    private SslCertificate(String s, String s1, Date date, Date date1, X509Certificate x509certificate)
    {
        mIssuedTo = new DName(s);
        mIssuedBy = new DName(s1);
        mValidNotBefore = cloneDate(date);
        mValidNotAfter = cloneDate(date1);
        mX509Certificate = x509certificate;
    }

    public SslCertificate(X509Certificate x509certificate)
    {
        this(x509certificate.getSubjectDN().getName(), x509certificate.getIssuerDN().getName(), x509certificate.getNotBefore(), x509certificate.getNotAfter(), x509certificate);
    }

    private static Date cloneDate(Date date)
    {
        if(date == null)
            return null;
        else
            return (Date)date.clone();
    }

    private static final String fingerprint(byte abyte0[])
    {
        if(abyte0 == null)
            return "";
        StringBuilder stringbuilder = new StringBuilder();
        for(int i = 0; i < abyte0.length; i++)
        {
            HexDump.appendByteAsHex(stringbuilder, abyte0[i], true);
            if(i + 1 != abyte0.length)
                stringbuilder.append(':');
        }

        return stringbuilder.toString();
    }

    private String formatCertificateDate(Context context, Date date)
    {
        if(date == null)
            return "";
        else
            return DateFormat.getMediumDateFormat(context).format(date);
    }

    private static String formatDate(Date date)
    {
        if(date == null)
            return "";
        else
            return (new SimpleDateFormat(ISO_8601_DATE_FORMAT)).format(date);
    }

    private static String getDigest(X509Certificate x509certificate, String s)
    {
        if(x509certificate == null)
            return "";
        try
        {
            x509certificate = x509certificate.getEncoded();
            x509certificate = fingerprint(MessageDigest.getInstance(s).digest(x509certificate));
        }
        // Misplaced declaration of an exception variable
        catch(X509Certificate x509certificate)
        {
            return "";
        }
        // Misplaced declaration of an exception variable
        catch(X509Certificate x509certificate)
        {
            return "";
        }
        return x509certificate;
    }

    private static String getSerialNumber(X509Certificate x509certificate)
    {
        if(x509certificate == null)
            return "";
        x509certificate = x509certificate.getSerialNumber();
        if(x509certificate == null)
            return "";
        else
            return fingerprint(x509certificate.toByteArray());
    }

    private static Date parseDate(String s)
    {
        try
        {
            SimpleDateFormat simpledateformat = JVM INSTR new #136 <Class SimpleDateFormat>;
            simpledateformat.SimpleDateFormat(ISO_8601_DATE_FORMAT);
            s = simpledateformat.parse(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return null;
        }
        return s;
    }

    public static SslCertificate restoreState(Bundle bundle)
    {
        if(bundle == null)
            return null;
        Object obj = bundle.getByteArray("x509-certificate");
        if(obj == null)
            obj = null;
        else
            try
            {
                CertificateFactory certificatefactory = CertificateFactory.getInstance("X.509");
                ByteArrayInputStream bytearrayinputstream = JVM INSTR new #200 <Class ByteArrayInputStream>;
                bytearrayinputstream.ByteArrayInputStream(((byte []) (obj)));
                obj = (X509Certificate)certificatefactory.generateCertificate(bytearrayinputstream);
            }
            catch(CertificateException certificateexception)
            {
                certificateexception = null;
            }
        return new SslCertificate(bundle.getString("issued-to"), bundle.getString("issued-by"), parseDate(bundle.getString("valid-not-before")), parseDate(bundle.getString("valid-not-after")), ((X509Certificate) (obj)));
    }

    public static Bundle saveState(SslCertificate sslcertificate)
    {
        if(sslcertificate == null)
            return null;
        Bundle bundle = new Bundle();
        bundle.putString("issued-to", sslcertificate.getIssuedTo().getDName());
        bundle.putString("issued-by", sslcertificate.getIssuedBy().getDName());
        bundle.putString("valid-not-before", sslcertificate.getValidNotBefore());
        bundle.putString("valid-not-after", sslcertificate.getValidNotAfter());
        sslcertificate = sslcertificate.mX509Certificate;
        if(sslcertificate != null)
            try
            {
                bundle.putByteArray("x509-certificate", sslcertificate.getEncoded());
            }
            // Misplaced declaration of an exception variable
            catch(SslCertificate sslcertificate) { }
        return bundle;
    }

    public DName getIssuedBy()
    {
        return mIssuedBy;
    }

    public DName getIssuedTo()
    {
        return mIssuedTo;
    }

    public String getValidNotAfter()
    {
        return formatDate(mValidNotAfter);
    }

    public Date getValidNotAfterDate()
    {
        return cloneDate(mValidNotAfter);
    }

    public String getValidNotBefore()
    {
        return formatDate(mValidNotBefore);
    }

    public Date getValidNotBeforeDate()
    {
        return cloneDate(mValidNotBefore);
    }

    public View inflateCertificateView(Context context)
    {
        View view = LayoutInflater.from(context).inflate(0x10900fc, null);
        Object obj = getIssuedTo();
        if(obj != null)
        {
            ((TextView)view.findViewById(0x102045b)).setText(((DName) (obj)).getCName());
            ((TextView)view.findViewById(0x102045d)).setText(((DName) (obj)).getOName());
            ((TextView)view.findViewById(0x102045f)).setText(((DName) (obj)).getUName());
        }
        ((TextView)view.findViewById(0x10203df)).setText(getSerialNumber(mX509Certificate));
        obj = getIssuedBy();
        if(obj != null)
        {
            ((TextView)view.findViewById(0x10201e0)).setText(((DName) (obj)).getCName());
            ((TextView)view.findViewById(0x10201e2)).setText(((DName) (obj)).getOName());
            ((TextView)view.findViewById(0x10201e4)).setText(((DName) (obj)).getUName());
        }
        obj = formatCertificateDate(context, getValidNotBeforeDate());
        ((TextView)view.findViewById(0x10202c1)).setText(((CharSequence) (obj)));
        context = formatCertificateDate(context, getValidNotAfterDate());
        ((TextView)view.findViewById(0x102023a)).setText(context);
        ((TextView)view.findViewById(0x10203e6)).setText(getDigest(mX509Certificate, "SHA256"));
        ((TextView)view.findViewById(0x10203e4)).setText(getDigest(mX509Certificate, "SHA1"));
        return view;
    }

    public String toString()
    {
        return (new StringBuilder()).append("Issued to: ").append(mIssuedTo.getDName()).append(";\n").append("Issued by: ").append(mIssuedBy.getDName()).append(";\n").toString();
    }

    private static String ISO_8601_DATE_FORMAT = "yyyy-MM-dd HH:mm:ssZ";
    private static final String ISSUED_BY = "issued-by";
    private static final String ISSUED_TO = "issued-to";
    private static final String VALID_NOT_AFTER = "valid-not-after";
    private static final String VALID_NOT_BEFORE = "valid-not-before";
    private static final String X509_CERTIFICATE = "x509-certificate";
    private final DName mIssuedBy;
    private final DName mIssuedTo;
    private final Date mValidNotAfter;
    private final Date mValidNotBefore;
    private final X509Certificate mX509Certificate;

}
