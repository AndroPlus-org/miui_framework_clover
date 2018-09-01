// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi.hotspot2.pps;

import android.net.wifi.ParcelUtil;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.*;

public final class Credential
    implements Parcelable
{
    public static final class CertificateCredential
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public boolean equals(Object obj)
        {
            boolean flag = false;
            if(this == obj)
                return true;
            if(!(obj instanceof CertificateCredential))
                return false;
            obj = (CertificateCredential)obj;
            if(TextUtils.equals(mCertType, ((CertificateCredential) (obj)).mCertType))
                flag = Arrays.equals(mCertSha256Fingerprint, ((CertificateCredential) (obj)).mCertSha256Fingerprint);
            return flag;
        }

        public byte[] getCertSha256Fingerprint()
        {
            return mCertSha256Fingerprint;
        }

        public String getCertType()
        {
            return mCertType;
        }

        public int hashCode()
        {
            return Objects.hash(new Object[] {
                mCertType, mCertSha256Fingerprint
            });
        }

        public void setCertSha256Fingerprint(byte abyte0[])
        {
            mCertSha256Fingerprint = abyte0;
        }

        public void setCertType(String s)
        {
            mCertType = s;
        }

        public String toString()
        {
            return (new StringBuilder()).append("CertificateType: ").append(mCertType).append("\n").toString();
        }

        public boolean validate()
        {
            if(!TextUtils.equals("x509v3", mCertType))
            {
                Log.d("Credential", (new StringBuilder()).append("Unsupported certificate type: ").append(mCertType).toString());
                return false;
            }
            if(mCertSha256Fingerprint == null || mCertSha256Fingerprint.length != 32)
            {
                Log.d("Credential", "Invalid SHA-256 fingerprint");
                return false;
            } else
            {
                return true;
            }
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeString(mCertType);
            parcel.writeByteArray(mCertSha256Fingerprint);
        }

        private static final int CERT_SHA256_FINGER_PRINT_LENGTH = 32;
        public static final String CERT_TYPE_X509V3 = "x509v3";
        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public CertificateCredential createFromParcel(Parcel parcel)
            {
                CertificateCredential certificatecredential = new CertificateCredential();
                certificatecredential.setCertType(parcel.readString());
                certificatecredential.setCertSha256Fingerprint(parcel.createByteArray());
                return certificatecredential;
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public CertificateCredential[] newArray(int i)
            {
                return new CertificateCredential[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private byte mCertSha256Fingerprint[];
        private String mCertType;


        public CertificateCredential()
        {
            mCertType = null;
            mCertSha256Fingerprint = null;
        }

        public CertificateCredential(CertificateCredential certificatecredential)
        {
            mCertType = null;
            mCertSha256Fingerprint = null;
            if(certificatecredential != null)
            {
                mCertType = certificatecredential.mCertType;
                if(certificatecredential.mCertSha256Fingerprint != null)
                    mCertSha256Fingerprint = Arrays.copyOf(certificatecredential.mCertSha256Fingerprint, certificatecredential.mCertSha256Fingerprint.length);
            }
        }
    }

    public static final class SimCredential
        implements Parcelable
    {

        private boolean verifyImsi()
        {
            if(TextUtils.isEmpty(mImsi))
            {
                Log.d("Credential", "Missing IMSI");
                return false;
            }
            if(mImsi.length() > 15)
            {
                Log.d("Credential", (new StringBuilder()).append("IMSI exceeding maximum length: ").append(mImsi.length()).toString());
                return false;
            }
            char c = '\0';
            int i = 0;
            char c1;
label0:
            do
            {
label1:
                {
                    c1 = c;
                    if(i < mImsi.length())
                    {
                        c = mImsi.charAt(i);
                        c1 = c;
                        if(c >= '0')
                        {
                            if(c <= '9')
                                break label1;
                            c1 = c;
                        }
                    }
                    if(i == mImsi.length())
                        return true;
                    break label0;
                }
                i++;
            } while(true);
            return i == mImsi.length() - 1 && c1 == '*';
        }

        public int describeContents()
        {
            return 0;
        }

        public boolean equals(Object obj)
        {
            boolean flag = true;
            if(this == obj)
                return true;
            if(!(obj instanceof SimCredential))
                return false;
            obj = (SimCredential)obj;
            if(TextUtils.equals(mImsi, ((SimCredential) (obj)).mImsi))
            {
                if(mEapType != ((SimCredential) (obj)).mEapType)
                    flag = false;
            } else
            {
                flag = false;
            }
            return flag;
        }

        public int getEapType()
        {
            return mEapType;
        }

        public String getImsi()
        {
            return mImsi;
        }

        public int hashCode()
        {
            return Objects.hash(new Object[] {
                mImsi, Integer.valueOf(mEapType)
            });
        }

        public void setEapType(int i)
        {
            mEapType = i;
        }

        public void setImsi(String s)
        {
            mImsi = s;
        }

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("IMSI: ").append(mImsi).append("\n");
            stringbuilder.append("EAPType: ").append(mEapType).append("\n");
            return stringbuilder.toString();
        }

        public boolean validate()
        {
            if(!verifyImsi())
                return false;
            if(mEapType != 18 && mEapType != 23 && mEapType != 50)
            {
                Log.d("Credential", (new StringBuilder()).append("Invalid EAP Type for SIM credential: ").append(mEapType).toString());
                return false;
            } else
            {
                return true;
            }
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeString(mImsi);
            parcel.writeInt(mEapType);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public SimCredential createFromParcel(Parcel parcel)
            {
                SimCredential simcredential = new SimCredential();
                simcredential.setImsi(parcel.readString());
                simcredential.setEapType(parcel.readInt());
                return simcredential;
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public SimCredential[] newArray(int i)
            {
                return new SimCredential[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private static final int MAX_IMSI_LENGTH = 15;
        private int mEapType;
        private String mImsi;


        public SimCredential()
        {
            mImsi = null;
            mEapType = 0x80000000;
        }

        public SimCredential(SimCredential simcredential)
        {
            mImsi = null;
            mEapType = 0x80000000;
            if(simcredential != null)
            {
                mImsi = simcredential.mImsi;
                mEapType = simcredential.mEapType;
            }
        }
    }

    public static final class UserCredential
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public boolean equals(Object obj)
        {
            boolean flag = false;
            if(this == obj)
                return true;
            if(!(obj instanceof UserCredential))
                return false;
            obj = (UserCredential)obj;
            boolean flag1 = flag;
            if(TextUtils.equals(mUsername, ((UserCredential) (obj)).mUsername))
            {
                flag1 = flag;
                if(TextUtils.equals(mPassword, ((UserCredential) (obj)).mPassword))
                {
                    flag1 = flag;
                    if(mMachineManaged == ((UserCredential) (obj)).mMachineManaged)
                    {
                        flag1 = flag;
                        if(TextUtils.equals(mSoftTokenApp, ((UserCredential) (obj)).mSoftTokenApp))
                        {
                            flag1 = flag;
                            if(mAbleToShare == ((UserCredential) (obj)).mAbleToShare)
                            {
                                flag1 = flag;
                                if(mEapType == ((UserCredential) (obj)).mEapType)
                                    flag1 = TextUtils.equals(mNonEapInnerMethod, ((UserCredential) (obj)).mNonEapInnerMethod);
                            }
                        }
                    }
                }
            }
            return flag1;
        }

        public boolean getAbleToShare()
        {
            return mAbleToShare;
        }

        public int getEapType()
        {
            return mEapType;
        }

        public boolean getMachineManaged()
        {
            return mMachineManaged;
        }

        public String getNonEapInnerMethod()
        {
            return mNonEapInnerMethod;
        }

        public String getPassword()
        {
            return mPassword;
        }

        public String getSoftTokenApp()
        {
            return mSoftTokenApp;
        }

        public String getUsername()
        {
            return mUsername;
        }

        public int hashCode()
        {
            return Objects.hash(new Object[] {
                mUsername, mPassword, Boolean.valueOf(mMachineManaged), mSoftTokenApp, Boolean.valueOf(mAbleToShare), Integer.valueOf(mEapType), mNonEapInnerMethod
            });
        }

        public void setAbleToShare(boolean flag)
        {
            mAbleToShare = flag;
        }

        public void setEapType(int i)
        {
            mEapType = i;
        }

        public void setMachineManaged(boolean flag)
        {
            mMachineManaged = flag;
        }

        public void setNonEapInnerMethod(String s)
        {
            mNonEapInnerMethod = s;
        }

        public void setPassword(String s)
        {
            mPassword = s;
        }

        public void setSoftTokenApp(String s)
        {
            mSoftTokenApp = s;
        }

        public void setUsername(String s)
        {
            mUsername = s;
        }

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("Username: ").append(mUsername).append("\n");
            stringbuilder.append("MachineManaged: ").append(mMachineManaged).append("\n");
            stringbuilder.append("SoftTokenApp: ").append(mSoftTokenApp).append("\n");
            stringbuilder.append("AbleToShare: ").append(mAbleToShare).append("\n");
            stringbuilder.append("EAPType: ").append(mEapType).append("\n");
            stringbuilder.append("AuthMethod: ").append(mNonEapInnerMethod).append("\n");
            return stringbuilder.toString();
        }

        public boolean validate()
        {
            if(TextUtils.isEmpty(mUsername))
            {
                Log.d("Credential", "Missing username");
                return false;
            }
            if(mUsername.getBytes(StandardCharsets.UTF_8).length > 63)
            {
                Log.d("Credential", (new StringBuilder()).append("username exceeding maximum length: ").append(mUsername.getBytes(StandardCharsets.UTF_8).length).toString());
                return false;
            }
            if(TextUtils.isEmpty(mPassword))
            {
                Log.d("Credential", "Missing password");
                return false;
            }
            if(mPassword.getBytes(StandardCharsets.UTF_8).length > 255)
            {
                Log.d("Credential", (new StringBuilder()).append("password exceeding maximum length: ").append(mPassword.getBytes(StandardCharsets.UTF_8).length).toString());
                return false;
            }
            if(mEapType != 21)
            {
                Log.d("Credential", (new StringBuilder()).append("Invalid EAP Type for user credential: ").append(mEapType).toString());
                return false;
            }
            if(!SUPPORTED_AUTH.contains(mNonEapInnerMethod))
            {
                Log.d("Credential", (new StringBuilder()).append("Invalid non-EAP inner method for EAP-TTLS: ").append(mNonEapInnerMethod).toString());
                return false;
            } else
            {
                return true;
            }
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            boolean flag = true;
            parcel.writeString(mUsername);
            parcel.writeString(mPassword);
            if(mMachineManaged)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeString(mSoftTokenApp);
            if(mAbleToShare)
                i = ((flag) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeInt(mEapType);
            parcel.writeString(mNonEapInnerMethod);
        }

        public static final String AUTH_METHOD_MSCHAP = "MS-CHAP";
        public static final String AUTH_METHOD_MSCHAPV2 = "MS-CHAP-V2";
        public static final String AUTH_METHOD_PAP = "PAP";
        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public UserCredential createFromParcel(Parcel parcel)
            {
                boolean flag = true;
                UserCredential usercredential = new UserCredential();
                usercredential.setUsername(parcel.readString());
                usercredential.setPassword(parcel.readString());
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                usercredential.setMachineManaged(flag1);
                usercredential.setSoftTokenApp(parcel.readString());
                if(parcel.readInt() != 0)
                    flag1 = flag;
                else
                    flag1 = false;
                usercredential.setAbleToShare(flag1);
                usercredential.setEapType(parcel.readInt());
                usercredential.setNonEapInnerMethod(parcel.readString());
                return usercredential;
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public UserCredential[] newArray(int i)
            {
                return new UserCredential[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private static final int MAX_PASSWORD_BYTES = 255;
        private static final int MAX_USERNAME_BYTES = 63;
        private static final Set SUPPORTED_AUTH = new HashSet(Arrays.asList(new String[] {
            "PAP", "MS-CHAP", "MS-CHAP-V2"
        }));
        private boolean mAbleToShare;
        private int mEapType;
        private boolean mMachineManaged;
        private String mNonEapInnerMethod;
        private String mPassword;
        private String mSoftTokenApp;
        private String mUsername;


        public UserCredential()
        {
            mUsername = null;
            mPassword = null;
            mMachineManaged = false;
            mSoftTokenApp = null;
            mAbleToShare = false;
            mEapType = 0x80000000;
            mNonEapInnerMethod = null;
        }

        public UserCredential(UserCredential usercredential)
        {
            mUsername = null;
            mPassword = null;
            mMachineManaged = false;
            mSoftTokenApp = null;
            mAbleToShare = false;
            mEapType = 0x80000000;
            mNonEapInnerMethod = null;
            if(usercredential != null)
            {
                mUsername = usercredential.mUsername;
                mPassword = usercredential.mPassword;
                mMachineManaged = usercredential.mMachineManaged;
                mSoftTokenApp = usercredential.mSoftTokenApp;
                mAbleToShare = usercredential.mAbleToShare;
                mEapType = usercredential.mEapType;
                mNonEapInnerMethod = usercredential.mNonEapInnerMethod;
            }
        }
    }


    public Credential()
    {
        mCreationTimeInMillis = 0x8000000000000000L;
        mExpirationTimeInMillis = 0x8000000000000000L;
        mRealm = null;
        mCheckAaaServerCertStatus = false;
        mUserCredential = null;
        mCertCredential = null;
        mSimCredential = null;
        mCaCertificate = null;
        mClientCertificateChain = null;
        mClientPrivateKey = null;
    }

    public Credential(Credential credential)
    {
        mCreationTimeInMillis = 0x8000000000000000L;
        mExpirationTimeInMillis = 0x8000000000000000L;
        mRealm = null;
        mCheckAaaServerCertStatus = false;
        mUserCredential = null;
        mCertCredential = null;
        mSimCredential = null;
        mCaCertificate = null;
        mClientCertificateChain = null;
        mClientPrivateKey = null;
        if(credential != null)
        {
            mCreationTimeInMillis = credential.mCreationTimeInMillis;
            mExpirationTimeInMillis = credential.mExpirationTimeInMillis;
            mRealm = credential.mRealm;
            mCheckAaaServerCertStatus = credential.mCheckAaaServerCertStatus;
            if(credential.mUserCredential != null)
                mUserCredential = new UserCredential(credential.mUserCredential);
            if(credential.mCertCredential != null)
                mCertCredential = new CertificateCredential(credential.mCertCredential);
            if(credential.mSimCredential != null)
                mSimCredential = new SimCredential(credential.mSimCredential);
            if(credential.mClientCertificateChain != null)
                mClientCertificateChain = (X509Certificate[])Arrays.copyOf(credential.mClientCertificateChain, credential.mClientCertificateChain.length);
            mCaCertificate = credential.mCaCertificate;
            mClientPrivateKey = credential.mClientPrivateKey;
        }
    }

    private static boolean isPrivateKeyEquals(PrivateKey privatekey, PrivateKey privatekey1)
    {
        boolean flag = false;
        if(privatekey == null && privatekey1 == null)
            return true;
        if(privatekey == null || privatekey1 == null)
            return false;
        if(TextUtils.equals(privatekey.getAlgorithm(), privatekey1.getAlgorithm()))
            flag = Arrays.equals(privatekey.getEncoded(), privatekey1.getEncoded());
        return flag;
    }

    private static boolean isX509CertificateEquals(X509Certificate x509certificate, X509Certificate x509certificate1)
    {
        boolean flag;
        if(x509certificate == null && x509certificate1 == null)
            return true;
        if(x509certificate == null || x509certificate1 == null)
            return false;
        flag = false;
        boolean flag1 = Arrays.equals(x509certificate.getEncoded(), x509certificate1.getEncoded());
        flag = flag1;
_L2:
        return flag;
        x509certificate;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static boolean isX509CertificatesEquals(X509Certificate ax509certificate[], X509Certificate ax509certificate1[])
    {
        if(ax509certificate == null && ax509certificate1 == null)
            return true;
        if(ax509certificate == null || ax509certificate1 == null)
            return false;
        if(ax509certificate.length != ax509certificate1.length)
            return false;
        for(int i = 0; i < ax509certificate.length; i++)
            if(!isX509CertificateEquals(ax509certificate[i], ax509certificate1[i]))
                return false;

        return true;
    }

    private boolean verifyCertCredential()
    {
        if(mCertCredential == null)
        {
            Log.d("Credential", "Missing certificate credential");
            return false;
        }
        if(mUserCredential != null || mSimCredential != null)
        {
            Log.d("Credential", "Contained more than one type of credential");
            return false;
        }
        if(!mCertCredential.validate())
            return false;
        if(mCaCertificate == null)
        {
            Log.d("Credential", "Missing CA Certificate for certificate credential");
            return false;
        }
        if(mClientPrivateKey == null)
        {
            Log.d("Credential", "Missing client private key for certificate credential");
            return false;
        }
label0:
        {
            try
            {
                if(verifySha256Fingerprint(mClientCertificateChain, mCertCredential.getCertSha256Fingerprint()))
                    break label0;
                Log.d("Credential", "SHA-256 fingerprint mismatch");
            }
            catch(Object obj)
            {
                Log.d("Credential", (new StringBuilder()).append("Failed to verify SHA-256 fingerprint: ").append(((GeneralSecurityException) (obj)).getMessage()).toString());
                return false;
            }
            return false;
        }
        return true;
    }

    private static boolean verifySha256Fingerprint(X509Certificate ax509certificate[], byte abyte0[])
        throws NoSuchAlgorithmException, CertificateEncodingException
    {
        if(ax509certificate == null)
            return false;
        MessageDigest messagedigest = MessageDigest.getInstance("SHA-256");
        int i = ax509certificate.length;
        for(int j = 0; j < i; j++)
        {
            X509Certificate x509certificate = ax509certificate[j];
            messagedigest.reset();
            if(Arrays.equals(abyte0, messagedigest.digest(x509certificate.getEncoded())))
                return true;
        }

        return false;
    }

    private boolean verifySimCredential()
    {
        if(mSimCredential == null)
        {
            Log.d("Credential", "Missing SIM credential");
            return false;
        }
        if(mUserCredential != null || mCertCredential != null)
        {
            Log.d("Credential", "Contained more than one type of credential");
            return false;
        } else
        {
            return mSimCredential.validate();
        }
    }

    private boolean verifyUserCredential()
    {
        if(mUserCredential == null)
        {
            Log.d("Credential", "Missing user credential");
            return false;
        }
        if(mCertCredential != null || mSimCredential != null)
        {
            Log.d("Credential", "Contained more than one type of credential");
            return false;
        }
        if(!mUserCredential.validate())
            return false;
        if(mCaCertificate == null)
        {
            Log.d("Credential", "Missing CA Certificate for user credential");
            return false;
        } else
        {
            return true;
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag;
        boolean flag1;
        flag = false;
        if(this == obj)
            return true;
        if(!(obj instanceof Credential))
            return false;
        obj = (Credential)obj;
        flag1 = flag;
        if(!TextUtils.equals(mRealm, ((Credential) (obj)).mRealm)) goto _L2; else goto _L1
_L1:
        flag1 = flag;
        if(mCreationTimeInMillis != ((Credential) (obj)).mCreationTimeInMillis) goto _L2; else goto _L3
_L3:
        flag1 = flag;
        if(mExpirationTimeInMillis != ((Credential) (obj)).mExpirationTimeInMillis) goto _L2; else goto _L4
_L4:
        flag1 = flag;
        if(mCheckAaaServerCertStatus != ((Credential) (obj)).mCheckAaaServerCertStatus) goto _L2; else goto _L5
_L5:
        if(mUserCredential != null) goto _L7; else goto _L6
_L6:
        flag1 = flag;
        if(((Credential) (obj)).mUserCredential != null) goto _L2; else goto _L8
_L8:
        if(mCertCredential != null) goto _L10; else goto _L9
_L9:
        flag1 = flag;
        if(((Credential) (obj)).mCertCredential != null) goto _L2; else goto _L11
_L11:
        if(mSimCredential != null) goto _L13; else goto _L12
_L12:
        flag1 = flag;
        if(((Credential) (obj)).mSimCredential != null) goto _L2; else goto _L14
_L14:
        flag1 = flag;
        if(isX509CertificateEquals(mCaCertificate, ((Credential) (obj)).mCaCertificate))
        {
            flag1 = flag;
            if(isX509CertificatesEquals(mClientCertificateChain, ((Credential) (obj)).mClientCertificateChain))
                flag1 = isPrivateKeyEquals(mClientPrivateKey, ((Credential) (obj)).mClientPrivateKey);
        }
_L2:
        return flag1;
_L7:
        flag1 = flag;
        if(!mUserCredential.equals(((Credential) (obj)).mUserCredential)) goto _L2; else goto _L8
_L10:
        flag1 = flag;
        if(!mCertCredential.equals(((Credential) (obj)).mCertCredential)) goto _L2; else goto _L11
_L13:
        flag1 = flag;
        if(!mSimCredential.equals(((Credential) (obj)).mSimCredential)) goto _L2; else goto _L14
    }

    public X509Certificate getCaCertificate()
    {
        return mCaCertificate;
    }

    public CertificateCredential getCertCredential()
    {
        return mCertCredential;
    }

    public boolean getCheckAaaServerCertStatus()
    {
        return mCheckAaaServerCertStatus;
    }

    public X509Certificate[] getClientCertificateChain()
    {
        return mClientCertificateChain;
    }

    public PrivateKey getClientPrivateKey()
    {
        return mClientPrivateKey;
    }

    public long getCreationTimeInMillis()
    {
        return mCreationTimeInMillis;
    }

    public long getExpirationTimeInMillis()
    {
        return mExpirationTimeInMillis;
    }

    public String getRealm()
    {
        return mRealm;
    }

    public SimCredential getSimCredential()
    {
        return mSimCredential;
    }

    public UserCredential getUserCredential()
    {
        return mUserCredential;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            mRealm, Long.valueOf(mCreationTimeInMillis), Long.valueOf(mExpirationTimeInMillis), Boolean.valueOf(mCheckAaaServerCertStatus), mUserCredential, mCertCredential, mSimCredential, mCaCertificate, mClientCertificateChain, mClientPrivateKey
        });
    }

    public void setCaCertificate(X509Certificate x509certificate)
    {
        mCaCertificate = x509certificate;
    }

    public void setCertCredential(CertificateCredential certificatecredential)
    {
        mCertCredential = certificatecredential;
    }

    public void setCheckAaaServerCertStatus(boolean flag)
    {
        mCheckAaaServerCertStatus = flag;
    }

    public void setClientCertificateChain(X509Certificate ax509certificate[])
    {
        mClientCertificateChain = ax509certificate;
    }

    public void setClientPrivateKey(PrivateKey privatekey)
    {
        mClientPrivateKey = privatekey;
    }

    public void setCreationTimeInMillis(long l)
    {
        mCreationTimeInMillis = l;
    }

    public void setExpirationTimeInMillis(long l)
    {
        mExpirationTimeInMillis = l;
    }

    public void setRealm(String s)
    {
        mRealm = s;
    }

    public void setSimCredential(SimCredential simcredential)
    {
        mSimCredential = simcredential;
    }

    public void setUserCredential(UserCredential usercredential)
    {
        mUserCredential = usercredential;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("Realm: ").append(mRealm).append("\n");
        StringBuilder stringbuilder1 = stringbuilder.append("CreationTime: ");
        Object obj;
        if(mCreationTimeInMillis != 0x8000000000000000L)
            obj = new Date(mCreationTimeInMillis);
        else
            obj = "Not specified";
        stringbuilder1.append(obj).append("\n");
        stringbuilder1 = stringbuilder.append("ExpirationTime: ");
        if(mExpirationTimeInMillis != 0x8000000000000000L)
            obj = new Date(mExpirationTimeInMillis);
        else
            obj = "Not specified";
        stringbuilder1.append(obj).append("\n");
        stringbuilder.append("CheckAAAServerStatus: ").append(mCheckAaaServerCertStatus).append("\n");
        if(mUserCredential != null)
        {
            stringbuilder.append("UserCredential Begin ---\n");
            stringbuilder.append(mUserCredential);
            stringbuilder.append("UserCredential End ---\n");
        }
        if(mCertCredential != null)
        {
            stringbuilder.append("CertificateCredential Begin ---\n");
            stringbuilder.append(mCertCredential);
            stringbuilder.append("CertificateCredential End ---\n");
        }
        if(mSimCredential != null)
        {
            stringbuilder.append("SIMCredential Begin ---\n");
            stringbuilder.append(mSimCredential);
            stringbuilder.append("SIMCredential End ---\n");
        }
        return stringbuilder.toString();
    }

    public boolean validate()
    {
        if(TextUtils.isEmpty(mRealm))
        {
            Log.d("Credential", "Missing realm");
            return false;
        }
        if(mRealm.getBytes(StandardCharsets.UTF_8).length > 253)
        {
            Log.d("Credential", (new StringBuilder()).append("realm exceeding maximum length: ").append(mRealm.getBytes(StandardCharsets.UTF_8).length).toString());
            return false;
        }
        if(mUserCredential != null)
        {
            if(!verifyUserCredential())
                return false;
        } else
        if(mCertCredential != null)
        {
            if(!verifyCertCredential())
                return false;
        } else
        if(mSimCredential != null)
        {
            if(!verifySimCredential())
                return false;
        } else
        {
            Log.d("Credential", "Missing required credential");
            return false;
        }
        return true;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeLong(mCreationTimeInMillis);
        parcel.writeLong(mExpirationTimeInMillis);
        parcel.writeString(mRealm);
        int j;
        if(mCheckAaaServerCertStatus)
            j = 1;
        else
            j = 0;
        parcel.writeInt(j);
        parcel.writeParcelable(mUserCredential, i);
        parcel.writeParcelable(mCertCredential, i);
        parcel.writeParcelable(mSimCredential, i);
        ParcelUtil.writeCertificate(parcel, mCaCertificate);
        ParcelUtil.writeCertificates(parcel, mClientCertificateChain);
        ParcelUtil.writePrivateKey(parcel, mClientPrivateKey);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public Credential createFromParcel(Parcel parcel)
        {
            boolean flag = false;
            Credential credential = new Credential();
            credential.setCreationTimeInMillis(parcel.readLong());
            credential.setExpirationTimeInMillis(parcel.readLong());
            credential.setRealm(parcel.readString());
            if(parcel.readInt() != 0)
                flag = true;
            credential.setCheckAaaServerCertStatus(flag);
            credential.setUserCredential((UserCredential)parcel.readParcelable(null));
            credential.setCertCredential((CertificateCredential)parcel.readParcelable(null));
            credential.setSimCredential((SimCredential)parcel.readParcelable(null));
            credential.setCaCertificate(ParcelUtil.readCertificate(parcel));
            credential.setClientCertificateChain(ParcelUtil.readCertificates(parcel));
            credential.setClientPrivateKey(ParcelUtil.readPrivateKey(parcel));
            return credential;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public Credential[] newArray(int i)
        {
            return new Credential[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final int MAX_REALM_BYTES = 253;
    private static final String TAG = "Credential";
    private X509Certificate mCaCertificate;
    private CertificateCredential mCertCredential;
    private boolean mCheckAaaServerCertStatus;
    private X509Certificate mClientCertificateChain[];
    private PrivateKey mClientPrivateKey;
    private long mCreationTimeInMillis;
    private long mExpirationTimeInMillis;
    private String mRealm;
    private SimCredential mSimCredential;
    private UserCredential mUserCredential;

}
