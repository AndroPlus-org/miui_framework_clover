// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi.hotspot2;

import android.net.wifi.hotspot2.omadm.PpsMoParser;
import android.net.wifi.hotspot2.pps.Credential;
import android.text.TextUtils;
import android.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.*;
import java.util.*;

// Referenced classes of package android.net.wifi.hotspot2:
//            PasspointConfiguration

public final class ConfigParser
{
    private static class MimeHeader
    {

        public String boundary;
        public String contentType;
        public String encodingType;

        private MimeHeader()
        {
            contentType = null;
            boundary = null;
            encodingType = null;
        }

        MimeHeader(MimeHeader mimeheader)
        {
            this();
        }
    }

    private static class MimePart
    {

        public byte data[];
        public boolean isLast;
        public String type;

        private MimePart()
        {
            type = null;
            data = null;
            isLast = false;
        }

        MimePart(MimePart mimepart)
        {
            this();
        }
    }


    public ConfigParser()
    {
    }

    private static PasspointConfiguration createPasspointConfig(Map map)
        throws IOException
    {
        byte abyte0[] = (byte[])map.get("application/x-passpoint-profile");
        if(abyte0 == null)
            throw new IOException("Missing Passpoint Profile");
        PasspointConfiguration passpointconfiguration = PpsMoParser.parseMoText(new String(abyte0));
        if(passpointconfiguration == null)
            throw new IOException("Failed to parse Passpoint profile");
        if(passpointconfiguration.getCredential() == null)
            throw new IOException("Passpoint profile missing credential");
        byte abyte1[] = (byte[])map.get("application/x-x509-ca-cert");
        if(abyte1 != null)
            try
            {
                passpointconfiguration.getCredential().setCaCertificate(parseCACert(abyte1));
            }
            // Misplaced declaration of an exception variable
            catch(Map map)
            {
                throw new IOException("Failed to parse CA Certificate");
            }
        map = (byte[])map.get("application/x-pkcs12");
        if(map == null)
            break MISSING_BLOCK_LABEL_168;
        try
        {
            map = parsePkcs12(map);
            passpointconfiguration.getCredential().setClientPrivateKey((PrivateKey)((Pair) (map)).first);
            passpointconfiguration.getCredential().setClientCertificateChain((X509Certificate[])((List)((Pair) (map)).second).toArray(new X509Certificate[((List)((Pair) (map)).second).size()]));
        }
        // Misplaced declaration of an exception variable
        catch(Map map)
        {
            throw new IOException("Failed to parse PCKS12 string");
        }
        return passpointconfiguration;
    }

    private static X509Certificate parseCACert(byte abyte0[])
        throws CertificateException
    {
        return (X509Certificate)CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(abyte0));
    }

    private static Pair parseContentType(String s)
        throws IOException
    {
        String as[] = s.split(";");
        Object obj = null;
        if(as.length < 1)
            throw new IOException((new StringBuilder()).append("Invalid Content-Type: ").append(s).toString());
        String s2 = as[0].trim();
        int i = 1;
        s = obj;
        while(i < as.length) 
        {
            String s1 = as[i].trim();
            if(!s1.startsWith("boundary="))
            {
                Log.d("ConfigParser", (new StringBuilder()).append("Ignore Content-Type attribute: ").append(as[i]).toString());
            } else
            {
                s1 = s1.substring("boundary=".length());
                s = s1;
                if(s1.length() > 1)
                {
                    s = s1;
                    if(s1.startsWith("\""))
                    {
                        s = s1;
                        if(s1.endsWith("\""))
                            s = s1.substring(1, s1.length() - 1);
                    }
                }
            }
            i++;
        }
        return new Pair(s2, s);
    }

    private static MimeHeader parseHeaders(LineNumberReader linenumberreader)
        throws IOException
    {
        MimeHeader mimeheader = new MimeHeader(null);
        for(linenumberreader = readHeaders(linenumberreader).entrySet().iterator(); linenumberreader.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)linenumberreader.next();
            Object obj = (String)entry.getKey();
            if(((String) (obj)).equals("Content-Type"))
            {
                obj = parseContentType((String)entry.getValue());
                mimeheader.contentType = (String)((Pair) (obj)).first;
                mimeheader.boundary = (String)((Pair) (obj)).second;
            } else
            if(((String) (obj)).equals("Content-Transfer-Encoding"))
                mimeheader.encodingType = (String)entry.getValue();
            else
                Log.d("ConfigParser", (new StringBuilder()).append("Ignore header: ").append((String)entry.getKey()).toString());
        }

        return mimeheader;
    }

    private static Map parseMimeMultipartMessage(LineNumberReader linenumberreader)
        throws IOException
    {
        MimeHeader mimeheader = parseHeaders(linenumberreader);
        if(!TextUtils.equals(mimeheader.contentType, "multipart/mixed"))
            throw new IOException((new StringBuilder()).append("Invalid content type: ").append(mimeheader.contentType).toString());
        if(TextUtils.isEmpty(mimeheader.boundary))
            throw new IOException("Missing boundary string");
        if(!TextUtils.equals(mimeheader.encodingType, "base64"))
            throw new IOException((new StringBuilder()).append("Unexpected encoding: ").append(mimeheader.encodingType).toString());
        Object obj;
        do
        {
            obj = linenumberreader.readLine();
            if(obj == null)
                throw new IOException((new StringBuilder()).append("Unexpected EOF before first boundary @ ").append(linenumberreader.getLineNumber()).toString());
        } while(!((String) (obj)).equals((new StringBuilder()).append("--").append(mimeheader.boundary).toString()));
        obj = new HashMap();
        MimePart mimepart;
        do
        {
            mimepart = parseMimePart(linenumberreader, mimeheader.boundary);
            ((Map) (obj)).put(mimepart.type, mimepart.data);
        } while(!mimepart.isLast);
        return ((Map) (obj));
    }

    private static MimePart parseMimePart(LineNumberReader linenumberreader, String s)
        throws IOException
    {
        MimeHeader mimeheader = parseHeaders(linenumberreader);
        if(!TextUtils.equals(mimeheader.encodingType, "base64"))
            throw new IOException((new StringBuilder()).append("Unexpected encoding type: ").append(mimeheader.encodingType).toString());
        if(!TextUtils.equals(mimeheader.contentType, "application/x-passpoint-profile") && TextUtils.equals(mimeheader.contentType, "application/x-x509-ca-cert") ^ true && TextUtils.equals(mimeheader.contentType, "application/x-pkcs12") ^ true)
            throw new IOException((new StringBuilder()).append("Unexpected content type: ").append(mimeheader.contentType).toString());
        StringBuilder stringbuilder = new StringBuilder();
        boolean flag = false;
        String s1 = (new StringBuilder()).append("--").append(s).toString();
        String s2 = (new StringBuilder()).append(s1).append("--").toString();
        do
        {
            s = linenumberreader.readLine();
            if(s == null)
                throw new IOException((new StringBuilder()).append("Unexpected EOF file in body @ ").append(linenumberreader.getLineNumber()).toString());
            if(s.startsWith(s1))
            {
                if(s.equals(s2))
                    flag = true;
                linenumberreader = new MimePart(null);
                linenumberreader.type = mimeheader.contentType;
                linenumberreader.data = Base64.decode(stringbuilder.toString(), 0);
                linenumberreader.isLast = flag;
                return linenumberreader;
            }
            stringbuilder.append(s);
        } while(true);
    }

    public static PasspointConfiguration parsePasspointConfig(String s, byte abyte0[])
    {
        if(!TextUtils.equals(s, "application/x-wifi-config"))
        {
            Log.e("ConfigParser", (new StringBuilder()).append("Unexpected MIME type: ").append(s).toString());
            return null;
        }
        try
        {
            s = JVM INSTR new #69  <Class String>;
            s.String(abyte0, StandardCharsets.ISO_8859_1);
            byte abyte1[] = Base64.decode(s, 0);
            s = JVM INSTR new #286 <Class LineNumberReader>;
            InputStreamReader inputstreamreader = JVM INSTR new #356 <Class InputStreamReader>;
            abyte0 = JVM INSTR new #152 <Class ByteArrayInputStream>;
            abyte0.ByteArrayInputStream(abyte1);
            inputstreamreader.InputStreamReader(abyte0, StandardCharsets.ISO_8859_1);
            s.LineNumberReader(inputstreamreader);
            s = createPasspointConfig(parseMimeMultipartMessage(s));
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.e("ConfigParser", (new StringBuilder()).append("Failed to parse installation file: ").append(s.getMessage()).toString());
            return null;
        }
        return s;
    }

    private static Pair parsePkcs12(byte abyte0[])
        throws GeneralSecurityException, IOException
    {
        int i = 0;
        KeyStore keystore = KeyStore.getInstance("PKCS12");
        abyte0 = new ByteArrayInputStream(abyte0);
        keystore.load(abyte0, new char[0]);
        abyte0.close();
        if(keystore.size() != 1)
            throw new IOException((new StringBuilder()).append("Unexpected key size: ").append(keystore.size()).toString());
        String s = (String)keystore.aliases().nextElement();
        if(s == null)
            throw new IOException("No alias found");
        PrivateKey privatekey = (PrivateKey)keystore.getKey(s, null);
        abyte0 = null;
        Certificate acertificate[] = keystore.getCertificateChain(s);
        if(acertificate != null)
        {
            ArrayList arraylist = new ArrayList();
            int j = acertificate.length;
            do
            {
                abyte0 = arraylist;
                if(i >= j)
                    break;
                abyte0 = acertificate[i];
                if(!(abyte0 instanceof X509Certificate))
                    throw new IOException((new StringBuilder()).append("Unexpceted certificate type: ").append(abyte0.getClass()).toString());
                arraylist.add((X509Certificate)abyte0);
                i++;
            } while(true);
        }
        return new Pair(privatekey, abyte0);
    }

    private static Map readHeaders(LineNumberReader linenumberreader)
        throws IOException
    {
        HashMap hashmap = new HashMap();
        String s = null;
        StringBuilder stringbuilder = null;
        do
        {
            String s1 = linenumberreader.readLine();
            if(s1 == null)
                throw new IOException((new StringBuilder()).append("Missing line @ ").append(linenumberreader.getLineNumber()).toString());
            if(s1.length() == 0 || s1.trim().length() == 0)
            {
                if(s != null)
                    hashmap.put(s, stringbuilder.toString());
                return hashmap;
            }
            int i = s1.indexOf(':');
            if(i < 0)
            {
                if(stringbuilder != null)
                    stringbuilder.append(' ').append(s1.trim());
                else
                    throw new IOException((new StringBuilder()).append("Bad header line: '").append(s1).append("' @ ").append(linenumberreader.getLineNumber()).toString());
            } else
            {
                if(Character.isWhitespace(s1.charAt(0)))
                    throw new IOException((new StringBuilder()).append("Illegal blank prefix in header line '").append(s1).append("' @ ").append(linenumberreader.getLineNumber()).toString());
                if(s != null)
                    hashmap.put(s, stringbuilder.toString());
                s = s1.substring(0, i).trim();
                stringbuilder = new StringBuilder();
                stringbuilder.append(s1.substring(i + 1).trim());
            }
        } while(true);
    }

    private static final String BOUNDARY = "boundary=";
    private static final String CONTENT_TRANSFER_ENCODING = "Content-Transfer-Encoding";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String ENCODING_BASE64 = "base64";
    private static final String TAG = "ConfigParser";
    private static final String TYPE_CA_CERT = "application/x-x509-ca-cert";
    private static final String TYPE_MULTIPART_MIXED = "multipart/mixed";
    private static final String TYPE_PASSPOINT_PROFILE = "application/x-passpoint-profile";
    private static final String TYPE_PKCS12 = "application/x-pkcs12";
    private static final String TYPE_WIFI_CONFIG = "application/x-wifi-config";
}
