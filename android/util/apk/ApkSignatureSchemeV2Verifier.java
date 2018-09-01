// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util.apk;

import android.system.ErrnoException;
import android.system.OsConstants;
import android.util.ArrayMap;
import android.util.Pair;
import java.io.*;
import java.math.BigInteger;
import java.nio.*;
import java.security.*;
import java.security.cert.*;
import java.security.spec.*;
import java.util.*;
import libcore.io.Libcore;
import libcore.io.Os;

// Referenced classes of package android.util.apk:
//            ZipUtils

public class ApkSignatureSchemeV2Verifier
{
    private static final class ByteBufferDataSource
        implements DataSource
    {

        public void feedIntoMessageDigests(MessageDigest amessagedigest[], long l, int i)
            throws IOException
        {
            Object obj = mBuf;
            obj;
            JVM INSTR monitorenter ;
            ByteBuffer bytebuffer;
            mBuf.position((int)l);
            mBuf.limit((int)l + i);
            bytebuffer = mBuf.slice();
            obj;
            JVM INSTR monitorexit ;
            int j = amessagedigest.length;
            for(i = 0; i < j; i++)
            {
                obj = amessagedigest[i];
                bytebuffer.position(0);
                ((MessageDigest) (obj)).update(bytebuffer);
            }

            break MISSING_BLOCK_LABEL_90;
            amessagedigest;
            throw amessagedigest;
        }

        public long size()
        {
            return (long)mBuf.capacity();
        }

        private final ByteBuffer mBuf;

        public ByteBufferDataSource(ByteBuffer bytebuffer)
        {
            mBuf = bytebuffer.slice();
        }
    }

    private static interface DataSource
    {

        public abstract void feedIntoMessageDigests(MessageDigest amessagedigest[], long l, int i)
            throws IOException;

        public abstract long size();
    }

    private static final class MemoryMappedFileDataSource
        implements DataSource
    {

        public void feedIntoMessageDigests(MessageDigest amessagedigest[], long l, int i)
            throws IOException
        {
            long l1;
            int j;
            long l2;
            long l3;
            l = mFilePosition + l;
            l1 = (l / MEMORY_PAGE_SIZE_BYTES) * MEMORY_PAGE_SIZE_BYTES;
            j = (int)(l - l1);
            l2 = i + j;
            l3 = 0L;
            l = l3;
            l1 = OS.mmap(0L, l2, OsConstants.PROT_READ, OsConstants.MAP_SHARED | OsConstants.MAP_POPULATE, mFd, l1);
            l = l1;
            l3 = l1;
            DirectByteBuffer directbytebuffer = JVM INSTR new #72  <Class DirectByteBuffer>;
            l = l1;
            l3 = l1;
            directbytebuffer.DirectByteBuffer(i, l1 + (long)j, mFd, null, true);
            i = 0;
            l = l1;
            l3 = l1;
            j = amessagedigest.length;
_L2:
            Object obj1;
            if(i >= j)
                break; /* Loop/switch isn't completed */
            obj1 = amessagedigest[i];
            l = l1;
            l3 = l1;
            directbytebuffer.position(0);
            l = l1;
            l3 = l1;
            ((MessageDigest) (obj1)).update(directbytebuffer);
            i++;
            if(true) goto _L2; else goto _L1
_L1:
            if(l1 == 0L)
                break MISSING_BLOCK_LABEL_186;
            OS.munmap(l1, l2);
_L4:
            return;
            Object obj;
            obj;
            l3 = l;
            obj1 = JVM INSTR new #55  <Class IOException>;
            l3 = l;
            amessagedigest = JVM INSTR new #93  <Class StringBuilder>;
            l3 = l;
            amessagedigest.StringBuilder();
            l3 = l;
            ((IOException) (obj1)).IOException(amessagedigest.append("Failed to mmap ").append(l2).append(" bytes").toString(), ((Throwable) (obj)));
            l3 = l;
            throw obj1;
            obj;
            if(l3 != 0L)
                try
                {
                    OS.munmap(l3, l2);
                }
                // Misplaced declaration of an exception variable
                catch(MessageDigest amessagedigest[]) { }
            throw obj;
            amessagedigest;
            if(true) goto _L4; else goto _L3
_L3:
        }

        public long size()
        {
            return mSize;
        }

        private static final long MEMORY_PAGE_SIZE_BYTES;
        private static final Os OS;
        private final FileDescriptor mFd;
        private final long mFilePosition;
        private final long mSize;

        static 
        {
            OS = Libcore.os;
            MEMORY_PAGE_SIZE_BYTES = OS.sysconf(OsConstants._SC_PAGESIZE);
        }

        public MemoryMappedFileDataSource(FileDescriptor filedescriptor, long l, long l1)
        {
            mFd = filedescriptor;
            mFilePosition = l;
            mSize = l1;
        }
    }

    private static class SignatureInfo
    {

        static long _2D_get0(SignatureInfo signatureinfo)
        {
            return signatureinfo.apkSigningBlockOffset;
        }

        static long _2D_get1(SignatureInfo signatureinfo)
        {
            return signatureinfo.centralDirOffset;
        }

        static ByteBuffer _2D_get2(SignatureInfo signatureinfo)
        {
            return signatureinfo.eocd;
        }

        static long _2D_get3(SignatureInfo signatureinfo)
        {
            return signatureinfo.eocdOffset;
        }

        static ByteBuffer _2D_get4(SignatureInfo signatureinfo)
        {
            return signatureinfo.signatureBlock;
        }

        private final long apkSigningBlockOffset;
        private final long centralDirOffset;
        private final ByteBuffer eocd;
        private final long eocdOffset;
        private final ByteBuffer signatureBlock;

        private SignatureInfo(ByteBuffer bytebuffer, long l, long l1, long l2, 
                ByteBuffer bytebuffer1)
        {
            signatureBlock = bytebuffer;
            apkSigningBlockOffset = l;
            centralDirOffset = l1;
            eocdOffset = l2;
            eocd = bytebuffer1;
        }

        SignatureInfo(ByteBuffer bytebuffer, long l, long l1, long l2, 
                ByteBuffer bytebuffer1, SignatureInfo signatureinfo)
        {
            this(bytebuffer, l, l1, l2, bytebuffer1);
        }
    }

    public static class SignatureNotFoundException extends Exception
    {

        private static final long serialVersionUID = 1L;

        public SignatureNotFoundException(String s)
        {
            super(s);
        }

        public SignatureNotFoundException(String s, Throwable throwable)
        {
            super(s, throwable);
        }
    }

    private static class VerbatimX509Certificate extends WrappedX509Certificate
    {

        public byte[] getEncoded()
            throws CertificateEncodingException
        {
            return encodedVerbatim;
        }

        private byte encodedVerbatim[];

        public VerbatimX509Certificate(X509Certificate x509certificate, byte abyte0[])
        {
            super(x509certificate);
            encodedVerbatim = abyte0;
        }
    }

    private static class WrappedX509Certificate extends X509Certificate
    {

        public void checkValidity()
            throws CertificateExpiredException, CertificateNotYetValidException
        {
            wrapped.checkValidity();
        }

        public void checkValidity(Date date)
            throws CertificateExpiredException, CertificateNotYetValidException
        {
            wrapped.checkValidity(date);
        }

        public int getBasicConstraints()
        {
            return wrapped.getBasicConstraints();
        }

        public Set getCriticalExtensionOIDs()
        {
            return wrapped.getCriticalExtensionOIDs();
        }

        public byte[] getEncoded()
            throws CertificateEncodingException
        {
            return wrapped.getEncoded();
        }

        public byte[] getExtensionValue(String s)
        {
            return wrapped.getExtensionValue(s);
        }

        public Principal getIssuerDN()
        {
            return wrapped.getIssuerDN();
        }

        public boolean[] getIssuerUniqueID()
        {
            return wrapped.getIssuerUniqueID();
        }

        public boolean[] getKeyUsage()
        {
            return wrapped.getKeyUsage();
        }

        public Set getNonCriticalExtensionOIDs()
        {
            return wrapped.getNonCriticalExtensionOIDs();
        }

        public Date getNotAfter()
        {
            return wrapped.getNotAfter();
        }

        public Date getNotBefore()
        {
            return wrapped.getNotBefore();
        }

        public PublicKey getPublicKey()
        {
            return wrapped.getPublicKey();
        }

        public BigInteger getSerialNumber()
        {
            return wrapped.getSerialNumber();
        }

        public String getSigAlgName()
        {
            return wrapped.getSigAlgName();
        }

        public String getSigAlgOID()
        {
            return wrapped.getSigAlgOID();
        }

        public byte[] getSigAlgParams()
        {
            return wrapped.getSigAlgParams();
        }

        public byte[] getSignature()
        {
            return wrapped.getSignature();
        }

        public Principal getSubjectDN()
        {
            return wrapped.getSubjectDN();
        }

        public boolean[] getSubjectUniqueID()
        {
            return wrapped.getSubjectUniqueID();
        }

        public byte[] getTBSCertificate()
            throws CertificateEncodingException
        {
            return wrapped.getTBSCertificate();
        }

        public int getVersion()
        {
            return wrapped.getVersion();
        }

        public boolean hasUnsupportedCriticalExtension()
        {
            return wrapped.hasUnsupportedCriticalExtension();
        }

        public String toString()
        {
            return wrapped.toString();
        }

        public void verify(PublicKey publickey)
            throws CertificateException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException
        {
            wrapped.verify(publickey);
        }

        public void verify(PublicKey publickey, String s)
            throws CertificateException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException
        {
            wrapped.verify(publickey, s);
        }

        private final X509Certificate wrapped;

        public WrappedX509Certificate(X509Certificate x509certificate)
        {
            wrapped = x509certificate;
        }
    }


    public ApkSignatureSchemeV2Verifier()
    {
    }

    private static void checkByteOrderLittleEndian(ByteBuffer bytebuffer)
    {
        if(bytebuffer.order() != ByteOrder.LITTLE_ENDIAN)
            throw new IllegalArgumentException("ByteBuffer byte order must be little endian");
        else
            return;
    }

    private static int compareContentDigestAlgorithm(int i, int j)
    {
        switch(i)
        {
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown digestAlgorithm1: ").append(i).toString());

        case 1: // '\001'
            switch(j)
            {
            default:
                throw new IllegalArgumentException((new StringBuilder()).append("Unknown digestAlgorithm2: ").append(j).toString());

            case 1: // '\001'
                return 0;

            case 2: // '\002'
                return -1;
            }

        case 2: // '\002'
            switch(j)
            {
            default:
                throw new IllegalArgumentException((new StringBuilder()).append("Unknown digestAlgorithm2: ").append(j).toString());

            case 1: // '\001'
                return 1;

            case 2: // '\002'
                return 0;
            }
        }
    }

    private static int compareSignatureAlgorithm(int i, int j)
    {
        return compareContentDigestAlgorithm(getSignatureAlgorithmContentDigestAlgorithm(i), getSignatureAlgorithmContentDigestAlgorithm(j));
    }

    private static byte[][] computeContentDigests(int ai[], DataSource adatasource[])
        throws DigestException
    {
        long l = 0L;
        int i = 0;
        for(int j = adatasource.length; i < j; i++)
            l += getChunkCount(adatasource[i].size());

        if(l >= 0x1fffffL)
            throw new DigestException((new StringBuilder()).append("Too many chunks: ").append(l).toString());
        int k = (int)l;
        byte abyte0[][] = new byte[ai.length][];
        for(i = 0; i < ai.length; i++)
        {
            byte abyte1[] = new byte[k * getContentDigestAlgorithmOutputSizeBytes(ai[i]) + 5];
            abyte1[0] = (byte)90;
            setUnsignedInt32LittleEndian(k, abyte1, 1);
            abyte0[i] = abyte1;
        }

        byte abyte2[] = new byte[5];
        abyte2[0] = (byte)-91;
        int j1 = 0;
        MessageDigest amessagedigest[] = new MessageDigest[ai.length];
        i = 0;
        while(i < ai.length) 
        {
            String s = getContentDigestAlgorithmJcaDigestAlgorithm(ai[i]);
            try
            {
                amessagedigest[i] = MessageDigest.getInstance(s);
            }
            // Misplaced declaration of an exception variable
            catch(int ai[])
            {
                throw new RuntimeException((new StringBuilder()).append(s).append(" digest not supported").toString(), ai);
            }
            i++;
        }
        k = 0;
        i = 0;
        for(int k1 = adatasource.length; i < k1; i++)
        {
            DataSource datasource = adatasource[i];
            long l2 = 0L;
            for(long l1 = datasource.size(); l1 > 0L;)
            {
                int i2 = (int)Math.min(l1, 0x100000L);
                setUnsignedInt32LittleEndian(i2, abyte2, 1);
                for(int j2 = 0; j2 < amessagedigest.length; j2++)
                    amessagedigest[j2].update(abyte2);

                int k2;
                try
                {
                    datasource.feedIntoMessageDigests(amessagedigest, l2, i2);
                }
                // Misplaced declaration of an exception variable
                catch(int ai[])
                {
                    throw new DigestException((new StringBuilder()).append("Failed to digest chunk #").append(j1).append(" of section #").append(k).toString(), ai);
                }
                for(k2 = 0; k2 < ai.length; k2++)
                {
                    int i3 = ai[k2];
                    byte abyte5[] = abyte0[k2];
                    int j3 = getContentDigestAlgorithmOutputSizeBytes(i3);
                    MessageDigest messagedigest1 = amessagedigest[k2];
                    i3 = messagedigest1.digest(abyte5, j1 * j3 + 5, j3);
                    if(i3 != j3)
                        throw new RuntimeException((new StringBuilder()).append("Unexpected output size of ").append(messagedigest1.getAlgorithm()).append(" digest: ").append(i3).toString());
                }

                l2 += i2;
                l1 -= i2;
                j1++;
            }

            k++;
        }

        byte abyte4[][] = new byte[ai.length][];
        i = 0;
        while(i < ai.length) 
        {
            int i1 = ai[i];
            byte abyte3[] = abyte0[i];
            adatasource = getContentDigestAlgorithmJcaDigestAlgorithm(i1);
            MessageDigest messagedigest;
            try
            {
                messagedigest = MessageDigest.getInstance(adatasource);
            }
            // Misplaced declaration of an exception variable
            catch(int ai[])
            {
                throw new RuntimeException((new StringBuilder()).append(adatasource).append(" digest not supported").toString(), ai);
            }
            abyte4[i] = messagedigest.digest(abyte3);
            i++;
        }
        return abyte4;
    }

    private static ByteBuffer findApkSignatureSchemeV2Block(ByteBuffer bytebuffer)
        throws SignatureNotFoundException
    {
        checkByteOrderLittleEndian(bytebuffer);
        bytebuffer = sliceFromTo(bytebuffer, 8, bytebuffer.capacity() - 24);
        int i = 0;
        int j;
        int k;
        for(; bytebuffer.hasRemaining(); bytebuffer.position(k + j))
        {
            i++;
            if(bytebuffer.remaining() < 8)
                throw new SignatureNotFoundException((new StringBuilder()).append("Insufficient data to read size of APK Signing Block entry #").append(i).toString());
            long l = bytebuffer.getLong();
            if(l < 4L || l > 0x7fffffffL)
                throw new SignatureNotFoundException((new StringBuilder()).append("APK Signing Block entry #").append(i).append(" size out of range: ").append(l).toString());
            j = (int)l;
            k = bytebuffer.position();
            if(j > bytebuffer.remaining())
                throw new SignatureNotFoundException((new StringBuilder()).append("APK Signing Block entry #").append(i).append(" size out of range: ").append(j).append(", available: ").append(bytebuffer.remaining()).toString());
            if(bytebuffer.getInt() == 0x7109871a)
                return getByteBuffer(bytebuffer, j - 4);
        }

        throw new SignatureNotFoundException("No APK Signature Scheme v2 block in APK Signing Block");
    }

    private static Pair findApkSigningBlock(RandomAccessFile randomaccessfile, long l)
        throws IOException, SignatureNotFoundException
    {
        if(l < 32L)
            throw new SignatureNotFoundException((new StringBuilder()).append("APK too small for APK Signing Block. ZIP Central Directory offset: ").append(l).toString());
        ByteBuffer bytebuffer = ByteBuffer.allocate(24);
        bytebuffer.order(ByteOrder.LITTLE_ENDIAN);
        randomaccessfile.seek(l - (long)bytebuffer.capacity());
        randomaccessfile.readFully(bytebuffer.array(), bytebuffer.arrayOffset(), bytebuffer.capacity());
        if(bytebuffer.getLong(8) != 0x20676953204b5041L || bytebuffer.getLong(16) != 0x3234206b636f6c42L)
            throw new SignatureNotFoundException("No APK Signing Block before ZIP Central Directory");
        long l1 = bytebuffer.getLong(0);
        if(l1 < (long)bytebuffer.capacity() || l1 > 0x7ffffff7L)
            throw new SignatureNotFoundException((new StringBuilder()).append("APK Signing Block size out of range: ").append(l1).toString());
        int i = (int)(8L + l1);
        l -= i;
        if(l < 0L)
            throw new SignatureNotFoundException((new StringBuilder()).append("APK Signing Block offset out of range: ").append(l).toString());
        bytebuffer = ByteBuffer.allocate(i);
        bytebuffer.order(ByteOrder.LITTLE_ENDIAN);
        randomaccessfile.seek(l);
        randomaccessfile.readFully(bytebuffer.array(), bytebuffer.arrayOffset(), bytebuffer.capacity());
        long l2 = bytebuffer.getLong(0);
        if(l2 != l1)
            throw new SignatureNotFoundException((new StringBuilder()).append("APK Signing Block sizes in header and footer do not match: ").append(l2).append(" vs ").append(l1).toString());
        else
            return Pair.create(bytebuffer, Long.valueOf(l));
    }

    private static SignatureInfo findSignature(RandomAccessFile randomaccessfile)
        throws IOException, SignatureNotFoundException
    {
        Pair pair = getEocd(randomaccessfile);
        ByteBuffer bytebuffer = (ByteBuffer)pair.first;
        long l = ((Long)pair.second).longValue();
        if(ZipUtils.isZip64EndOfCentralDirectoryLocatorPresent(randomaccessfile, l))
        {
            throw new SignatureNotFoundException("ZIP64 APK not supported");
        } else
        {
            long l1 = getCentralDirOffset(bytebuffer, l);
            Pair pair1 = findApkSigningBlock(randomaccessfile, l1);
            randomaccessfile = (ByteBuffer)pair1.first;
            long l2 = ((Long)pair1.second).longValue();
            return new SignatureInfo(findApkSignatureSchemeV2Block(randomaccessfile), l2, l1, l, bytebuffer, null);
        }
    }

    private static ByteBuffer getByteBuffer(ByteBuffer bytebuffer, int i)
        throws BufferUnderflowException
    {
        int j;
        if(i < 0)
            throw new IllegalArgumentException((new StringBuilder()).append("size: ").append(i).toString());
        j = bytebuffer.limit();
        int k = bytebuffer.position();
        i = k + i;
        if(i < k || i > j)
            throw new BufferUnderflowException();
        bytebuffer.limit(i);
        ByteBuffer bytebuffer1;
        bytebuffer1 = bytebuffer.slice();
        bytebuffer1.order(bytebuffer.order());
        bytebuffer.position(i);
        bytebuffer.limit(j);
        return bytebuffer1;
        Exception exception;
        exception;
        bytebuffer.limit(j);
        throw exception;
    }

    private static long getCentralDirOffset(ByteBuffer bytebuffer, long l)
        throws SignatureNotFoundException
    {
        long l1 = ZipUtils.getZipEocdCentralDirectoryOffset(bytebuffer);
        if(l1 > l)
            throw new SignatureNotFoundException((new StringBuilder()).append("ZIP Central Directory offset out of range: ").append(l1).append(". ZIP End of Central Directory offset: ").append(l).toString());
        if(l1 + ZipUtils.getZipEocdCentralDirectorySizeBytes(bytebuffer) != l)
            throw new SignatureNotFoundException("ZIP Central Directory is not immediately followed by End of Central Directory");
        else
            return l1;
    }

    private static final long getChunkCount(long l)
    {
        return ((l + 0x100000L) - 1L) / 0x100000L;
    }

    private static String getContentDigestAlgorithmJcaDigestAlgorithm(int i)
    {
        switch(i)
        {
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown content digest algorthm: ").append(i).toString());

        case 1: // '\001'
            return "SHA-256";

        case 2: // '\002'
            return "SHA-512";
        }
    }

    private static int getContentDigestAlgorithmOutputSizeBytes(int i)
    {
        switch(i)
        {
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown content digest algorthm: ").append(i).toString());

        case 1: // '\001'
            return 32;

        case 2: // '\002'
            return 64;
        }
    }

    private static Pair getEocd(RandomAccessFile randomaccessfile)
        throws IOException, SignatureNotFoundException
    {
        randomaccessfile = ZipUtils.findZipEndOfCentralDirectoryRecord(randomaccessfile);
        if(randomaccessfile == null)
            throw new SignatureNotFoundException("Not an APK file: ZIP End of Central Directory record not found");
        else
            return randomaccessfile;
    }

    private static ByteBuffer getLengthPrefixedSlice(ByteBuffer bytebuffer)
        throws IOException
    {
        if(bytebuffer.remaining() < 4)
            throw new IOException((new StringBuilder()).append("Remaining buffer too short to contain length of length-prefixed field. Remaining: ").append(bytebuffer.remaining()).toString());
        int i = bytebuffer.getInt();
        if(i < 0)
            throw new IllegalArgumentException("Negative length");
        if(i > bytebuffer.remaining())
            throw new IOException((new StringBuilder()).append("Length-prefixed field longer than remaining buffer. Field length: ").append(i).append(", remaining: ").append(bytebuffer.remaining()).toString());
        else
            return getByteBuffer(bytebuffer, i);
    }

    private static int getSignatureAlgorithmContentDigestAlgorithm(int i)
    {
        switch(i)
        {
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown signature algorithm: 0x").append(Long.toHexString(i & -1)).toString());

        case 257: 
        case 259: 
        case 513: 
        case 769: 
            return 1;

        case 258: 
        case 260: 
        case 514: 
            return 2;
        }
    }

    private static String getSignatureAlgorithmJcaKeyAlgorithm(int i)
    {
        switch(i)
        {
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown signature algorithm: 0x").append(Long.toHexString(i & -1)).toString());

        case 257: 
        case 258: 
        case 259: 
        case 260: 
            return "RSA";

        case 513: 
        case 514: 
            return "EC";

        case 769: 
            return "DSA";
        }
    }

    private static Pair getSignatureAlgorithmJcaSignatureAlgorithm(int i)
    {
        switch(i)
        {
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown signature algorithm: 0x").append(Long.toHexString(i & -1)).toString());

        case 257: 
            return Pair.create("SHA256withRSA/PSS", new PSSParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, 32, 1));

        case 258: 
            return Pair.create("SHA512withRSA/PSS", new PSSParameterSpec("SHA-512", "MGF1", MGF1ParameterSpec.SHA512, 64, 1));

        case 259: 
            return Pair.create("SHA256withRSA", null);

        case 260: 
            return Pair.create("SHA512withRSA", null);

        case 513: 
            return Pair.create("SHA256withECDSA", null);

        case 514: 
            return Pair.create("SHA512withECDSA", null);

        case 769: 
            return Pair.create("SHA256withDSA", null);
        }
    }

    public static boolean hasSignature(String s)
        throws IOException
    {
        String s1;
        Object obj;
        Object obj1;
        Object obj2;
        s1 = null;
        obj = null;
        obj1 = null;
        obj2 = null;
        Object obj3;
        obj3 = JVM INSTR new #267 <Class RandomAccessFile>;
        ((RandomAccessFile) (obj3)).RandomAccessFile(s, "r");
        findSignature(((RandomAccessFile) (obj3)));
        s = obj;
        if(obj3 == null)
            break MISSING_BLOCK_LABEL_43;
        ((RandomAccessFile) (obj3)).close();
        s = obj;
_L3:
        if(s == null) goto _L2; else goto _L1
_L1:
        try
        {
            throw s;
        }
        // Misplaced declaration of an exception variable
        catch(String s) { }
_L4:
        return false;
        s;
          goto _L3
_L2:
        return true;
        s;
        obj3 = obj2;
_L8:
        throw s;
        Exception exception;
        exception;
        s1 = s;
        s = exception;
_L7:
        exception = s1;
        if(obj3 == null)
            break MISSING_BLOCK_LABEL_88;
        ((RandomAccessFile) (obj3)).close();
        exception = s1;
_L5:
        if(exception == null)
            break MISSING_BLOCK_LABEL_134;
        try
        {
            throw exception;
        }
        // Misplaced declaration of an exception variable
        catch(String s) { }
          goto _L4
        obj3;
label0:
        {
            if(s1 != null)
                break label0;
            exception = ((Exception) (obj3));
        }
          goto _L5
        exception = s1;
        if(s1 == obj3) goto _L5; else goto _L6
_L6:
        s1.addSuppressed(((Throwable) (obj3)));
        exception = s1;
          goto _L5
        throw s;
        s;
        obj3 = obj1;
          goto _L7
        s;
          goto _L7
        s;
          goto _L8
    }

    private static boolean isSupportedSignatureAlgorithm(int i)
    {
        switch(i)
        {
        default:
            return false;

        case 257: 
        case 258: 
        case 259: 
        case 260: 
        case 513: 
        case 514: 
        case 769: 
            return true;
        }
    }

    private static byte[] readLengthPrefixedByteArray(ByteBuffer bytebuffer)
        throws IOException
    {
        int i = bytebuffer.getInt();
        if(i < 0)
            throw new IOException("Negative length");
        if(i > bytebuffer.remaining())
        {
            throw new IOException((new StringBuilder()).append("Underflow while reading length-prefixed value. Length: ").append(i).append(", available: ").append(bytebuffer.remaining()).toString());
        } else
        {
            byte abyte0[] = new byte[i];
            bytebuffer.get(abyte0);
            return abyte0;
        }
    }

    private static void setUnsignedInt32LittleEndian(int i, byte abyte0[], int j)
    {
        abyte0[j] = (byte)(i & 0xff);
        abyte0[j + 1] = (byte)(i >>> 8 & 0xff);
        abyte0[j + 2] = (byte)(i >>> 16 & 0xff);
        abyte0[j + 3] = (byte)(i >>> 24 & 0xff);
    }

    private static ByteBuffer sliceFromTo(ByteBuffer bytebuffer, int i, int j)
    {
        int k;
        int l;
        if(i < 0)
            throw new IllegalArgumentException((new StringBuilder()).append("start: ").append(i).toString());
        if(j < i)
            throw new IllegalArgumentException((new StringBuilder()).append("end < start: ").append(j).append(" < ").append(i).toString());
        k = bytebuffer.capacity();
        if(j > bytebuffer.capacity())
            throw new IllegalArgumentException((new StringBuilder()).append("end > capacity: ").append(j).append(" > ").append(k).toString());
        k = bytebuffer.limit();
        l = bytebuffer.position();
        ByteBuffer bytebuffer1;
        bytebuffer.position(0);
        bytebuffer.limit(j);
        bytebuffer.position(i);
        bytebuffer1 = bytebuffer.slice();
        bytebuffer1.order(bytebuffer.order());
        bytebuffer.position(0);
        bytebuffer.limit(k);
        bytebuffer.position(l);
        return bytebuffer1;
        Exception exception;
        exception;
        bytebuffer.position(0);
        bytebuffer.limit(k);
        bytebuffer.position(l);
        throw exception;
    }

    private static X509Certificate[][] verify(FileDescriptor filedescriptor, SignatureInfo signatureinfo)
        throws SecurityException
    {
        int i;
        ArrayMap arraymap;
        ArrayList arraylist;
        i = 0;
        arraymap = new ArrayMap();
        arraylist = new ArrayList();
        CertificateFactory certificatefactory;
        ByteBuffer bytebuffer;
        try
        {
            certificatefactory = CertificateFactory.getInstance("X.509");
        }
        // Misplaced declaration of an exception variable
        catch(FileDescriptor filedescriptor)
        {
            throw new RuntimeException("Failed to obtain X.509 CertificateFactory", filedescriptor);
        }
        try
        {
            bytebuffer = getLengthPrefixedSlice(SignatureInfo._2D_get4(signatureinfo));
        }
        // Misplaced declaration of an exception variable
        catch(FileDescriptor filedescriptor)
        {
            throw new SecurityException("Failed to read list of signers", filedescriptor);
        }
        if(!bytebuffer.hasRemaining())
            break; /* Loop/switch isn't completed */
        i++;
        try
        {
            arraylist.add(verifySigner(getLengthPrefixedSlice(bytebuffer), arraymap, certificatefactory));
        }
        // Misplaced declaration of an exception variable
        catch(FileDescriptor filedescriptor)
        {
            throw new SecurityException((new StringBuilder()).append("Failed to parse/verify signer #").append(i).append(" block").toString(), filedescriptor);
        }
        if(true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_36;
_L1:
        if(i < 1)
            throw new SecurityException("No signers found");
        if(arraymap.isEmpty())
        {
            throw new SecurityException("No content digests found");
        } else
        {
            verifyIntegrity(arraymap, filedescriptor, SignatureInfo._2D_get0(signatureinfo), SignatureInfo._2D_get1(signatureinfo), SignatureInfo._2D_get3(signatureinfo), SignatureInfo._2D_get2(signatureinfo));
            return (X509Certificate[][])arraylist.toArray(new X509Certificate[arraylist.size()][]);
        }
    }

    private static X509Certificate[][] verify(RandomAccessFile randomaccessfile)
        throws SignatureNotFoundException, SecurityException, IOException
    {
        SignatureInfo signatureinfo = findSignature(randomaccessfile);
        return verify(randomaccessfile.getFD(), signatureinfo);
    }

    public static X509Certificate[][] verify(String s)
        throws SignatureNotFoundException, SecurityException, IOException
    {
        String s1;
        Object obj;
        Object obj1;
        X509Certificate ax509certificate[][];
        s1 = null;
        obj = null;
        obj1 = null;
        ax509certificate = null;
        Object obj3;
        obj3 = JVM INSTR new #267 <Class RandomAccessFile>;
        ((RandomAccessFile) (obj3)).RandomAccessFile(s, "r");
        ax509certificate = verify(((RandomAccessFile) (obj3)));
        s = obj;
        if(obj3 == null)
            break MISSING_BLOCK_LABEL_44;
        ((RandomAccessFile) (obj3)).close();
        s = obj;
_L1:
        if(s != null)
            throw s;
        else
            return ax509certificate;
        s;
          goto _L1
        s;
        obj3 = ax509certificate;
_L6:
        throw s;
        Exception exception;
        exception;
        s1 = s;
        s = exception;
_L4:
        Object obj2;
        obj2 = s1;
        if(obj3 == null)
            break MISSING_BLOCK_LABEL_87;
        ((RandomAccessFile) (obj3)).close();
        obj2 = s1;
_L2:
        if(obj2 != null)
            throw obj2;
        else
            throw s;
        obj3;
        if(s1 == null)
        {
            obj2 = obj3;
        } else
        {
            obj2 = s1;
            if(s1 != obj3)
            {
                s1.addSuppressed(((Throwable) (obj3)));
                obj2 = s1;
            }
        }
          goto _L2
        s;
        obj3 = obj1;
        continue; /* Loop/switch isn't completed */
        s;
        if(true) goto _L4; else goto _L3
_L3:
        s;
        if(true) goto _L6; else goto _L5
_L5:
    }

    private static void verifyIntegrity(Map map, FileDescriptor filedescriptor, long l, long l1, long l2, 
            ByteBuffer bytebuffer)
        throws SecurityException
    {
        if(map.isEmpty())
            throw new SecurityException("No digests provided");
        MemoryMappedFileDataSource memorymappedfiledatasource = new MemoryMappedFileDataSource(filedescriptor, 0L, l);
        filedescriptor = new MemoryMappedFileDataSource(filedescriptor, l1, l2 - l1);
        bytebuffer = bytebuffer.duplicate();
        bytebuffer.order(ByteOrder.LITTLE_ENDIAN);
        ZipUtils.setZipEocdCentralDirectoryOffset(bytebuffer, l);
        ByteBufferDataSource bytebufferdatasource = new ByteBufferDataSource(bytebuffer);
        bytebuffer = new int[map.size()];
        int i = 0;
        for(Iterator iterator = map.keySet().iterator(); iterator.hasNext();)
        {
            bytebuffer[i] = ((Integer)iterator.next()).intValue();
            i++;
        }

        try
        {
            filedescriptor = computeContentDigests(bytebuffer, new DataSource[] {
                memorymappedfiledatasource, filedescriptor, bytebufferdatasource
            });
        }
        // Misplaced declaration of an exception variable
        catch(Map map)
        {
            throw new SecurityException("Failed to compute digest(s) of contents", map);
        }
        for(i = 0; i < bytebuffer.length; i++)
        {
            int j = bytebuffer[i];
            if(!MessageDigest.isEqual((byte[])map.get(Integer.valueOf(j)), filedescriptor[i]))
                throw new SecurityException((new StringBuilder()).append(getContentDigestAlgorithmJcaDigestAlgorithm(j)).append(" digest of contents did not verify").toString());
        }

    }

    private static X509Certificate[] verifySigner(ByteBuffer bytebuffer, Map map, CertificateFactory certificatefactory)
        throws SecurityException, IOException
    {
        ByteBuffer bytebuffer1;
        Object obj;
        byte abyte0[];
        int i;
        int j;
        ArrayList arraylist;
        bytebuffer1 = getLengthPrefixedSlice(bytebuffer);
        obj = getLengthPrefixedSlice(bytebuffer);
        abyte0 = readLengthPrefixedByteArray(bytebuffer);
        i = 0;
        j = -1;
        bytebuffer = null;
        arraylist = new ArrayList();
_L2:
        int k;
        if(!((ByteBuffer) (obj)).hasRemaining())
            break; /* Loop/switch isn't completed */
        k = i + 1;
        Object obj2;
        try
        {
            obj2 = getLengthPrefixedSlice(((ByteBuffer) (obj)));
            if(((ByteBuffer) (obj2)).remaining() < 8)
            {
                bytebuffer = JVM INSTR new #485 <Class SecurityException>;
                bytebuffer.SecurityException("Signature record too short");
                throw bytebuffer;
            }
        }
        // Misplaced declaration of an exception variable
        catch(ByteBuffer bytebuffer)
        {
            throw new SecurityException((new StringBuilder()).append("Failed to parse signature record #").append(k).toString(), bytebuffer);
        }
        int l;
        l = ((ByteBuffer) (obj2)).getInt();
        arraylist.add(Integer.valueOf(l));
        i = k;
        if(!isSupportedSignatureAlgorithm(l))
            continue; /* Loop/switch isn't completed */
        if(j == -1)
            break MISSING_BLOCK_LABEL_161;
        i = k;
        if(compareSignatureAlgorithm(l, j) <= 0)
            continue; /* Loop/switch isn't completed */
        j = l;
        bytebuffer = readLengthPrefixedByteArray(((ByteBuffer) (obj2)));
        i = k;
        if(true) goto _L2; else goto _L1
_L1:
        Object obj3;
        if(j == -1)
            if(i == 0)
                throw new SecurityException("No signatures found");
            else
                throw new SecurityException("No supported signatures found");
        obj3 = getSignatureAlgorithmJcaKeyAlgorithm(j);
        obj2 = getSignatureAlgorithmJcaSignatureAlgorithm(j);
        obj = (String)((Pair) (obj2)).first;
        obj2 = (AlgorithmParameterSpec)((Pair) (obj2)).second;
        boolean flag;
        try
        {
            Object obj4 = KeyFactory.getInstance(((String) (obj3)));
            obj3 = JVM INSTR new #665 <Class X509EncodedKeySpec>;
            ((X509EncodedKeySpec) (obj3)).X509EncodedKeySpec(abyte0);
            obj4 = ((KeyFactory) (obj4)).generatePublic(((java.security.spec.KeySpec) (obj3)));
            obj3 = Signature.getInstance(((String) (obj)));
            ((Signature) (obj3)).initVerify(((PublicKey) (obj4)));
        }
        // Misplaced declaration of an exception variable
        catch(ByteBuffer bytebuffer)
        {
            throw new SecurityException((new StringBuilder()).append("Failed to verify ").append(((String) (obj))).append(" signature").toString(), bytebuffer);
        }
        if(obj2 == null)
            break MISSING_BLOCK_LABEL_299;
        ((Signature) (obj3)).setParameter(((AlgorithmParameterSpec) (obj2)));
        ((Signature) (obj3)).update(bytebuffer1);
        flag = ((Signature) (obj3)).verify(bytebuffer);
        if(!flag)
            throw new SecurityException((new StringBuilder()).append(((String) (obj))).append(" signature did not verify").toString());
        bytebuffer = null;
        bytebuffer1.clear();
        obj2 = getLengthPrefixedSlice(bytebuffer1);
        obj = new ArrayList();
        i = 0;
_L4:
        if(!((ByteBuffer) (obj2)).hasRemaining())
            break; /* Loop/switch isn't completed */
        k = i + 1;
        try
        {
            obj3 = getLengthPrefixedSlice(((ByteBuffer) (obj2)));
            if(((ByteBuffer) (obj3)).remaining() < 8)
            {
                bytebuffer = JVM INSTR new #123 <Class IOException>;
                bytebuffer.IOException("Record too short");
                throw bytebuffer;
            }
        }
        // Misplaced declaration of an exception variable
        catch(ByteBuffer bytebuffer)
        {
            throw new IOException((new StringBuilder()).append("Failed to parse digest record #").append(k).toString(), bytebuffer);
        }
        l = ((ByteBuffer) (obj3)).getInt();
        ((List) (obj)).add(Integer.valueOf(l));
        i = k;
        if(l != j)
            continue; /* Loop/switch isn't completed */
        bytebuffer = readLengthPrefixedByteArray(((ByteBuffer) (obj3)));
        i = k;
        if(true) goto _L4; else goto _L3
_L3:
        if(!arraylist.equals(obj))
            throw new SecurityException("Signature algorithms don't match between digests and signatures records");
        j = getSignatureAlgorithmContentDigestAlgorithm(j);
        map = (byte[])map.put(Integer.valueOf(j), bytebuffer);
        if(map != null && MessageDigest.isEqual(map, bytebuffer) ^ true)
            throw new SecurityException((new StringBuilder()).append(getContentDigestAlgorithmJcaDigestAlgorithm(j)).append(" contents digest does not match the digest specified by a preceding signer").toString());
        bytebuffer1 = getLengthPrefixedSlice(bytebuffer1);
        map = new ArrayList();
        j = 0;
        while(bytebuffer1.hasRemaining()) 
        {
            j++;
            bytebuffer = readLengthPrefixedByteArray(bytebuffer1);
            Object obj1;
            try
            {
                obj1 = JVM INSTR new #717 <Class ByteArrayInputStream>;
                ((ByteArrayInputStream) (obj1)).ByteArrayInputStream(bytebuffer);
                obj1 = (X509Certificate)certificatefactory.generateCertificate(((java.io.InputStream) (obj1)));
            }
            // Misplaced declaration of an exception variable
            catch(ByteBuffer bytebuffer)
            {
                throw new SecurityException((new StringBuilder()).append("Failed to decode certificate #").append(j).toString(), bytebuffer);
            }
            map.add(new VerbatimX509Certificate(((X509Certificate) (obj1)), bytebuffer));
        }
        if(map.isEmpty())
            throw new SecurityException("No certificates listed");
        if(!Arrays.equals(abyte0, ((X509Certificate)map.get(0)).getPublicKey().getEncoded()))
            throw new SecurityException("Public key mismatch between certificate and signature record");
        else
            return (X509Certificate[])map.toArray(new X509Certificate[map.size()]);
    }

    private static final int APK_SIGNATURE_SCHEME_V2_BLOCK_ID = 0x7109871a;
    private static final long APK_SIG_BLOCK_MAGIC_HI = 0x3234206b636f6c42L;
    private static final long APK_SIG_BLOCK_MAGIC_LO = 0x20676953204b5041L;
    private static final int APK_SIG_BLOCK_MIN_SIZE = 32;
    private static final int CHUNK_SIZE_BYTES = 0x100000;
    private static final int CONTENT_DIGEST_CHUNKED_SHA256 = 1;
    private static final int CONTENT_DIGEST_CHUNKED_SHA512 = 2;
    public static final int SF_ATTRIBUTE_ANDROID_APK_SIGNED_ID = 2;
    public static final String SF_ATTRIBUTE_ANDROID_APK_SIGNED_NAME = "X-Android-APK-Signed";
    private static final int SIGNATURE_DSA_WITH_SHA256 = 769;
    private static final int SIGNATURE_ECDSA_WITH_SHA256 = 513;
    private static final int SIGNATURE_ECDSA_WITH_SHA512 = 514;
    private static final int SIGNATURE_RSA_PKCS1_V1_5_WITH_SHA256 = 259;
    private static final int SIGNATURE_RSA_PKCS1_V1_5_WITH_SHA512 = 260;
    private static final int SIGNATURE_RSA_PSS_WITH_SHA256 = 257;
    private static final int SIGNATURE_RSA_PSS_WITH_SHA512 = 258;
}
