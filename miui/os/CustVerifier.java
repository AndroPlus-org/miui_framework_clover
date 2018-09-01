// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.os;

import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.RSAPublicKeySpec;
import java.util.*;

// Referenced classes of package miui.os:
//            Build

public class CustVerifier
{

    private CustVerifier()
    {
    }

    private byte a2b(byte byte0)
    {
        if(byte0 >= 48 && byte0 <= 57)
            return (byte)(byte0 - 48);
        if(byte0 >= 97 && byte0 <= 102)
            return (byte)((byte0 - 97) + 10);
        if(byte0 >= 65 && byte0 <= 70)
            return (byte)((byte0 - 65) + 10);
        else
            return 0;
    }

    private byte b2a(int i)
    {
        if(i < 10)
            return (byte)(i + 48);
        else
            return (byte)((i - 10) + 97);
    }

    private String bytesToHexString(byte abyte0[])
    {
        byte abyte1[] = new byte[abyte0.length * 2];
        int i = 0;
        for(int j = 0; j < abyte0.length; j++)
        {
            int k = i + 1;
            abyte1[i] = b2a(abyte0[j] >> 4 & 0xf);
            i = k + 1;
            abyte1[k] = b2a(abyte0[j] & 0xf);
        }

        return new String(abyte1);
    }

    private void checkCustSignature()
    {
        LinkedList linkedlist;
        File file;
        loadVerifyKey("/verity_key");
        linkedlist = new LinkedList();
        file = new File(CUST_PATH);
        linkedlist.add(file);
_L2:
        String as[];
        if(linkedlist.isEmpty())
            break MISSING_BLOCK_LABEL_210;
        file = (File)linkedlist.remove();
        as = file.list();
        if(DEBUG)
            Log.d("CustVerifier", file.getAbsolutePath());
        int i = 0;
        int j = as.length;
_L5:
        if(i >= j) goto _L2; else goto _L1
_L1:
        String s = as[i];
        File file1;
        file1 = JVM INSTR new #95  <Class File>;
        file1.File(file, s);
        if(DEBUG)
        {
            StringBuilder stringbuilder = JVM INSTR new #42  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.d("CustVerifier", stringbuilder.append("\t").append(file1.getAbsolutePath()).toString());
        }
        if(!file1.isDirectory()) goto _L4; else goto _L3
_L3:
        linkedlist.add(file1);
_L7:
        i++;
          goto _L5
_L4:
        if(!file1.isFile()) goto _L7; else goto _L6
_L6:
        s.endsWith(".apk");
          goto _L7
        Exception exception;
        exception;
        Log.e("CustVerifier", (new StringBuilder()).append("checkCustSignature occurs: ").append(exception.toString()).toString());
    }

    private HashMap getCustApkSignatures(String s)
    {
        HashMap hashmap;
        Object obj;
        Object obj1;
        Object obj2;
        Object obj3;
        Object obj4;
        String s1;
        Object obj5;
        Object obj6;
        Object obj7;
        hashmap = new HashMap();
        obj = null;
        obj1 = null;
        obj2 = null;
        obj3 = null;
        obj4 = null;
        s1 = obj4;
        obj5 = obj1;
        obj6 = obj2;
        obj7 = obj3;
        File file = JVM INSTR new #95  <Class File>;
        s1 = obj4;
        obj5 = obj1;
        obj6 = obj2;
        obj7 = obj3;
        file.File(s);
        s1 = obj4;
        obj5 = obj1;
        obj6 = obj2;
        obj7 = obj3;
        Scanner scanner = JVM INSTR new #157 <Class Scanner>;
        s1 = obj4;
        obj5 = obj1;
        obj6 = obj2;
        obj7 = obj3;
        scanner.Scanner(file);
_L5:
        s = obj;
        if(!scanner.hasNextLine()) goto _L2; else goto _L1
_L1:
        s = scanner.nextLine().split(" ");
        if(s.length != 2) goto _L4; else goto _L3
_L3:
        hashmap.put(s[0], s[1]);
          goto _L5
        s;
        s1 = obj4;
        obj5 = obj1;
        obj6 = obj2;
        obj7 = obj3;
        scanner.close();
        s1 = obj4;
        obj5 = obj1;
        obj6 = obj2;
        obj7 = obj3;
        throw s;
        s;
        obj7 = s1;
        Log.e("CustVerifier", s.toString());
        s = "cust signature file not found";
        if("cust signature file not found" == null)
            s = "Cust signature read finish.";
        Log.e("CustVerifier", s);
_L8:
        return null;
_L4:
        int i = s[0].length();
        if(verifyFileBlockSignature(file, (int)file.length() - i, hexStringToBytes(s[0]))) goto _L7; else goto _L6
_L6:
        s = "cust signature file is cracked";
_L2:
        s1 = s;
        obj5 = s;
        obj6 = s;
        obj7 = s;
        scanner.close();
        obj7 = s;
        if(s == null)
            obj7 = "cust signature file is not signed";
        s = ((String) (obj7));
        if(obj7 == null)
            s = "Cust signature read finish.";
        Log.e("CustVerifier", s);
          goto _L8
_L7:
        if(DEBUG)
        {
            Log.d("CustVerifier", "cust apk and signatures:");
            for(obj7 = hashmap.entrySet().iterator(); ((Iterator) (obj7)).hasNext(); Log.d("CustVerifier", ((StringBuilder) (obj5)).append("\t").append((String)s.getKey()).append(":").append(((String)s.getValue()).substring(0, 20)).toString()))
            {
                s = (java.util.Map.Entry)((Iterator) (obj7)).next();
                obj5 = JVM INSTR new #42  <Class StringBuilder>;
                ((StringBuilder) (obj5)).StringBuilder();
            }

        }
        s1 = obj4;
        obj5 = obj1;
        obj6 = obj2;
        obj7 = obj3;
        scanner.close();
        Log.e("CustVerifier", "Cust signature read finish.");
        return hashmap;
        s;
        obj7 = obj5;
        Log.e("CustVerifier", s.toString());
        obj7 = obj5;
        obj6 = JVM INSTR new #42  <Class StringBuilder>;
        obj7 = obj5;
        ((StringBuilder) (obj6)).StringBuilder();
        obj7 = obj5;
        s = ((StringBuilder) (obj6)).append("failed to read cust signature file: ").append(s.toString()).toString();
        obj7 = s;
        if(s == null)
            obj7 = "Cust signature read finish.";
        Log.e("CustVerifier", ((String) (obj7)));
          goto _L8
        s;
        obj7 = obj6;
        Log.e("CustVerifier", s.toString());
        s = "failed to read cust signature file: IOException";
        if("failed to read cust signature file: IOException" == null)
            s = "Cust signature read finish.";
        Log.e("CustVerifier", s);
          goto _L8
        Exception exception;
        exception;
        s = ((String) (obj7));
        if(obj7 == null)
            s = "Cust signature read finish.";
        Log.e("CustVerifier", s);
        throw exception;
    }

    public static CustVerifier getInstance()
    {
        miui/os/CustVerifier;
        JVM INSTR monitorenter ;
        CustVerifier custverifier1;
        if(sInstance == null)
        {
            CustVerifier custverifier = JVM INSTR new #2   <Class CustVerifier>;
            custverifier.CustVerifier();
            if(custverifier.init())
                sInstance = custverifier;
        }
        custverifier1 = sInstance;
        miui/os/CustVerifier;
        JVM INSTR monitorexit ;
        return custverifier1;
        Exception exception;
        exception;
        throw exception;
    }

    private String getSignPath(String s)
    {
        String as[] = s.split("\\/");
        s = new StringBuffer(CUST_PATH);
        s.append(as[as.length - 1]);
        return s.toString().replace(".apk", ".sig");
    }

    private byte[] hexStringToBytes(String s)
    {
        if(s.length() % 2 == 1)
            return null;
        byte abyte0[] = new byte[s.length() / 2];
        s = s.getBytes();
        int i = 0;
        for(int j = 0; j < s.length - 1;)
        {
            byte byte0 = s[j];
            byte byte1 = s[j + 1];
            abyte0[i] = (byte)(a2b(byte0) << 4 | a2b(byte1));
            j += 2;
            i++;
        }

        return abyte0;
    }

    private boolean init()
    {
        boolean flag = false;
        mPubKey = loadVerifyKey("/verity_key");
        if(mPubKey == null)
        {
            Log.e("CustVerifier", "failed to load verify key");
            return false;
        }
        mSignatures = getCustApkSignatures(CUST_SIGNATURE_FILE);
        if(mSignatures != null)
            flag = true;
        return flag;
    }

    private String loadSignatureFromFile(File file)
    {
        byte abyte0[];
        FileInputStream fileinputstream;
        abyte0 = new byte[512];
        fileinputstream = JVM INSTR new #287 <Class FileInputStream>;
        fileinputstream.FileInputStream(file);
        int i = 0;
_L2:
        int j = fileinputstream.read(abyte0, i, abyte0.length - i);
        if(j == -1)
            break; /* Loop/switch isn't completed */
        j = i + j;
        int k = abyte0.length;
        i = j;
        if(j < k) goto _L2; else goto _L1
_L1:
        try
        {
            fileinputstream.close();
            return new String(abyte0);
        }
        // Misplaced declaration of an exception variable
        catch(File file)
        {
            Log.e("CustVerifier", file.toString());
        }
        break MISSING_BLOCK_LABEL_91;
        file;
        fileinputstream.close();
        throw file;
        return null;
    }

    private PublicKey loadVerifyKey(String s)
    {
        RandomAccessFile randomaccessfile;
        randomaccessfile = JVM INSTR new #295 <Class RandomAccessFile>;
        randomaccessfile.RandomAccessFile(s, "r");
        byte abyte0[];
        abyte0 = new byte[4];
        if(randomaccessfile.read(abyte0) == abyte0.length)
            break MISSING_BLOCK_LABEL_41;
        Log.e("CustVerifier", "EOF when read len bytes");
        randomaccessfile.close();
        return null;
        int i;
        i = (abyte0[0] | abyte0[1] << 8 | abyte0[2] << 16 | abyte0[3] << 24) * 4;
        if(i > 0 && i <= 256)
            break MISSING_BLOCK_LABEL_97;
        Log.e("CustVerifier", "invalid len bytes");
        randomaccessfile.close();
        return null;
        if(randomaccessfile.skipBytes(4) == 4)
            break MISSING_BLOCK_LABEL_121;
        Log.e("CustVerifier", "can't skip n0inv bytes");
        randomaccessfile.close();
        return null;
        byte abyte1[];
        abyte1 = new byte[i];
        if(randomaccessfile.read(abyte1) == abyte1.length)
            break MISSING_BLOCK_LABEL_154;
        Log.e("CustVerifier", "EOF when read mod bytes");
        randomaccessfile.close();
        return null;
        int j = 0;
        for(int k = i - 1; j < k; k--)
        {
            int i1 = abyte1[j];
            abyte1[j] = abyte1[k];
            abyte1[k] = (byte)i1;
            j++;
        }

        s = JVM INSTR new #318 <Class BigInteger>;
        s.BigInteger(1, abyte1);
        int l = (256 - i) + 256;
        if(randomaccessfile.skipBytes(l) == l)
            break MISSING_BLOCK_LABEL_254;
        Log.e("CustVerifier", "can't skip rr bytes");
        randomaccessfile.close();
        return null;
        if(randomaccessfile.read(abyte0) == abyte0.length)
            break MISSING_BLOCK_LABEL_279;
        Log.e("CustVerifier", "EOF when read exp bytes");
        randomaccessfile.close();
        return null;
        l = abyte0[0] | abyte0[1] << 8 | abyte0[2] << 16 | abyte0[3] << 24;
        Object obj;
        BigInteger biginteger = BigInteger.valueOf(l);
        obj = JVM INSTR new #331 <Class RSAPublicKeySpec>;
        ((RSAPublicKeySpec) (obj)).RSAPublicKeySpec(s, biginteger);
        obj = KeyFactory.getInstance("RSA").generatePublic(((java.security.spec.KeySpec) (obj)));
        if(DEBUG)
        {
            StringBuilder stringbuilder = JVM INSTR new #42  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.d("CustVerifier", stringbuilder.append("loadVerifyKey: \n\tlen: ").append(i).append("\n").append("\tmodulus: ").append(s.toString(16)).append("\n").append("\tpublic exponent: ").append(l).toString());
        }
        randomaccessfile.close();
        return ((PublicKey) (obj));
        s;
        try
        {
            randomaccessfile.close();
            throw s;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.e("CustVerifier", (new StringBuilder()).append("Exception occur when load verify key: ").append(s.toString()).toString());
        }
        return null;
    }

    private boolean verifyFileBlockSignature(File file, int i, byte abyte0[])
    {
        if(i == 0)
        {
            Log.e("CustVerifier", "verifyFileBlockSignature get 0-sized block");
            return false;
        }
        Signature signature;
        byte abyte1[];
        FileInputStream fileinputstream;
        signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(mPubKey);
        abyte1 = new byte[4096];
        fileinputstream = JVM INSTR new #287 <Class FileInputStream>;
        fileinputstream.FileInputStream(file);
        int j = 0;
_L2:
        int k = fileinputstream.read(abyte1);
        int l;
        if(k == -1)
            break; /* Loop/switch isn't completed */
        l = k;
        if(k + j > i)
            l = i - j;
        signature.update(abyte1, 0, l);
        l = j + l;
        j = l;
        if(l != i)
            continue; /* Loop/switch isn't completed */
        StringBuilder stringbuilder = JVM INSTR new #42  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.e("CustVerifier", stringbuilder.append("total: ").append(l).toString());
        break; /* Loop/switch isn't completed */
        if(true) goto _L2; else goto _L1
_L1:
        boolean flag;
        flag = signature.verify(abyte0);
        if(DEBUG)
        {
            abyte0 = JVM INSTR new #42  <Class StringBuilder>;
            abyte0.StringBuilder();
            Log.d("CustVerifier", abyte0.append("verify ").append(file.getAbsolutePath()).append(" ").append(i).append(" ").append(flag).toString());
        }
        fileinputstream.close();
        return flag;
        file;
        try
        {
            fileinputstream.close();
            throw file;
        }
        // Misplaced declaration of an exception variable
        catch(File file)
        {
            Log.e("CustVerifier", (new StringBuilder()).append("Exception occurs when verify file block: ").append(file.toString()).toString());
        }
        return false;
    }

    private boolean verifyFileSignature(String s, byte abyte0[])
    {
        if(TextUtils.isEmpty(s))
        {
            Log.e("CustVerifier", "verifyFileSignature get null file");
            return false;
        } else
        {
            s = new File(s);
            return verifyFileBlockSignature(s, (int)s.length(), abyte0);
        }
    }

    private boolean verifyPathSignature(String s, String s1)
    {
        boolean flag;
        try
        {
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initVerify(mPubKey);
            signature.update(s.getBytes());
            flag = signature.verify(hexStringToBytes(s1));
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.e("CustVerifier", s.toString());
            return false;
        }
        return flag;
    }

    public boolean verifyApkSignatue(String s, int i)
    {
        Object obj = getSignPath(s);
        Log.e("CustVerifier", (new StringBuilder()).append(" Sig path ").append(((String) (obj))).toString());
        obj = new File(((String) (obj)));
        boolean flag = ((File) (obj)).exists();
        if(i == 0)
        {
            if(flag)
            {
                obj = loadSignatureFromFile(((File) (obj)));
                Log.e("CustVerifier", (new StringBuilder()).append("has sig File1 : ").append(((String) (obj))).toString());
            } else
            {
                obj = (String)mSignatures.get(s);
                Log.e("CustVerifier", (new StringBuilder()).append("has sig File2 : ").append(((String) (obj))).toString());
            }
            if(obj == null)
            {
                Log.e("CustVerifier", (new StringBuilder()).append("no signature found for ").append(s).toString());
                return false;
            } else
            {
                return verifyFileSignature(s, hexStringToBytes(((String) (obj))));
            }
        }
        if(i == 1)
        {
            if(flag)
                return verifyPathSignature(s, loadSignatureFromFile(((File) (obj))));
            else
                return false;
        } else
        {
            throw new RuntimeException("not supported mode");
        }
    }

    private static final String CUST_PATH = (new StringBuilder()).append("/cust/cust/").append(Build.getCustVariant()).append("/").toString();
    private static final String CUST_SIGNATURE_FILE = (new StringBuilder()).append(CUST_PATH).append("sign_customized_applist").toString();
    private static final boolean DEBUG;
    private static final int INT_SIZE = 4;
    public static final int MODE_DELETE = 1;
    public static final int MODE_NORMAL = 0;
    private static final int RSANUMBYTES = 256;
    private static final String TAG = "CustVerifier";
    private static final String VERIFY_KEY_FILE = "/verity_key";
    private static CustVerifier sInstance;
    private PublicKey mPubKey;
    private HashMap mSignatures;

    static 
    {
        DEBUG = Build.IS_DEBUGGABLE;
    }
}
