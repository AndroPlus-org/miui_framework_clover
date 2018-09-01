// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keymaster;

import java.util.HashMap;
import java.util.Map;

public final class KeymasterDefs
{

    private KeymasterDefs()
    {
    }

    public static String getErrorMessage(int i)
    {
        String s = (String)sErrorCodeToString.get(Integer.valueOf(i));
        if(s != null)
            return s;
        else
            return String.valueOf(i);
    }

    public static int getTagType(int i)
    {
        return 0xf0000000 & i;
    }

    public static final int HW_AUTH_FINGERPRINT = 2;
    public static final int HW_AUTH_PASSWORD = 1;
    public static final int KM_ALGORITHM_AES = 32;
    public static final int KM_ALGORITHM_EC = 3;
    public static final int KM_ALGORITHM_HMAC = 128;
    public static final int KM_ALGORITHM_RSA = 1;
    public static final int KM_BIGNUM = 0x80000000;
    public static final int KM_BLOB_REQUIRES_FILE_SYSTEM = 1;
    public static final int KM_BLOB_STANDALONE = 0;
    public static final int KM_BOOL = 0x70000000;
    public static final int KM_BYTES = 0x90000000;
    public static final int KM_DATE = 0x60000000;
    public static final int KM_DIGEST_MD5 = 1;
    public static final int KM_DIGEST_NONE = 0;
    public static final int KM_DIGEST_SHA1 = 2;
    public static final int KM_DIGEST_SHA_2_224 = 3;
    public static final int KM_DIGEST_SHA_2_256 = 4;
    public static final int KM_DIGEST_SHA_2_384 = 5;
    public static final int KM_DIGEST_SHA_2_512 = 6;
    public static final int KM_ENUM = 0x10000000;
    public static final int KM_ENUM_REP = 0x20000000;
    public static final int KM_ERROR_CALLER_NONCE_PROHIBITED = -55;
    public static final int KM_ERROR_CANNOT_ATTEST_IDS = -66;
    public static final int KM_ERROR_CONCURRENT_ACCESS_CONFLICT = -47;
    public static final int KM_ERROR_DELEGATION_NOT_ALLOWED = -23;
    public static final int KM_ERROR_IMPORTED_KEY_DECRYPTION_FAILED = -35;
    public static final int KM_ERROR_IMPORTED_KEY_NOT_ENCRYPTED = -34;
    public static final int KM_ERROR_IMPORTED_KEY_NOT_SIGNED = -36;
    public static final int KM_ERROR_IMPORTED_KEY_VERIFICATION_FAILED = -37;
    public static final int KM_ERROR_IMPORT_PARAMETER_MISMATCH = -44;
    public static final int KM_ERROR_INCOMPATIBLE_ALGORITHM = -5;
    public static final int KM_ERROR_INCOMPATIBLE_BLOCK_MODE = -8;
    public static final int KM_ERROR_INCOMPATIBLE_DIGEST = -13;
    public static final int KM_ERROR_INCOMPATIBLE_KEY_FORMAT = -18;
    public static final int KM_ERROR_INCOMPATIBLE_PADDING_MODE = -11;
    public static final int KM_ERROR_INCOMPATIBLE_PURPOSE = -3;
    public static final int KM_ERROR_INSUFFICIENT_BUFFER_SPACE = -29;
    public static final int KM_ERROR_INVALID_ARGUMENT = -38;
    public static final int KM_ERROR_INVALID_AUTHORIZATION_TIMEOUT = -16;
    public static final int KM_ERROR_INVALID_EXPIRATION_TIME = -14;
    public static final int KM_ERROR_INVALID_INPUT_LENGTH = -21;
    public static final int KM_ERROR_INVALID_KEY_BLOB = -33;
    public static final int KM_ERROR_INVALID_MAC_LENGTH = -57;
    public static final int KM_ERROR_INVALID_NONCE = -52;
    public static final int KM_ERROR_INVALID_OPERATION_HANDLE = -28;
    public static final int KM_ERROR_INVALID_RESCOPING = -42;
    public static final int KM_ERROR_INVALID_TAG = -40;
    public static final int KM_ERROR_INVALID_USER_ID = -15;
    public static final int KM_ERROR_KEY_EXPIRED = -25;
    public static final int KM_ERROR_KEY_EXPORT_OPTIONS_INVALID = -22;
    public static final int KM_ERROR_KEY_MAX_OPS_EXCEEDED = -56;
    public static final int KM_ERROR_KEY_NOT_YET_VALID = -24;
    public static final int KM_ERROR_KEY_RATE_LIMIT_EXCEEDED = -54;
    public static final int KM_ERROR_KEY_USER_NOT_AUTHENTICATED = -26;
    public static final int KM_ERROR_MEMORY_ALLOCATION_FAILED = -41;
    public static final int KM_ERROR_MISSING_MAC_LENGTH = -53;
    public static final int KM_ERROR_MISSING_MIN_MAC_LENGTH = -58;
    public static final int KM_ERROR_MISSING_NONCE = -51;
    public static final int KM_ERROR_OK = 0;
    public static final int KM_ERROR_OPERATION_CANCELLED = -46;
    public static final int KM_ERROR_OUTPUT_PARAMETER_NULL = -27;
    public static final int KM_ERROR_ROOT_OF_TRUST_ALREADY_SET = -1;
    public static final int KM_ERROR_SECURE_HW_ACCESS_DENIED = -45;
    public static final int KM_ERROR_SECURE_HW_BUSY = -48;
    public static final int KM_ERROR_SECURE_HW_COMMUNICATION_FAILED = -49;
    public static final int KM_ERROR_TOO_MANY_OPERATIONS = -31;
    public static final int KM_ERROR_UNEXPECTED_NULL_POINTER = -32;
    public static final int KM_ERROR_UNIMPLEMENTED = -100;
    public static final int KM_ERROR_UNKNOWN_ERROR = -1000;
    public static final int KM_ERROR_UNSUPPORTED_ALGORITHM = -4;
    public static final int KM_ERROR_UNSUPPORTED_BLOCK_MODE = -7;
    public static final int KM_ERROR_UNSUPPORTED_DIGEST = -12;
    public static final int KM_ERROR_UNSUPPORTED_EC_FIELD = -50;
    public static final int KM_ERROR_UNSUPPORTED_KEY_ENCRYPTION_ALGORITHM = -19;
    public static final int KM_ERROR_UNSUPPORTED_KEY_FORMAT = -17;
    public static final int KM_ERROR_UNSUPPORTED_KEY_SIZE = -6;
    public static final int KM_ERROR_UNSUPPORTED_KEY_VERIFICATION_ALGORITHM = -20;
    public static final int KM_ERROR_UNSUPPORTED_MAC_LENGTH = -9;
    public static final int KM_ERROR_UNSUPPORTED_MIN_MAC_LENGTH = -59;
    public static final int KM_ERROR_UNSUPPORTED_PADDING_MODE = -10;
    public static final int KM_ERROR_UNSUPPORTED_PURPOSE = -2;
    public static final int KM_ERROR_UNSUPPORTED_TAG = -39;
    public static final int KM_ERROR_VERIFICATION_FAILED = -30;
    public static final int KM_ERROR_VERSION_MISMATCH = -101;
    public static final int KM_INVALID = 0;
    public static final int KM_KEY_FORMAT_PKCS8 = 1;
    public static final int KM_KEY_FORMAT_RAW = 3;
    public static final int KM_KEY_FORMAT_X509 = 0;
    public static final int KM_MODE_CBC = 2;
    public static final int KM_MODE_CTR = 3;
    public static final int KM_MODE_ECB = 1;
    public static final int KM_MODE_GCM = 32;
    public static final int KM_ORIGIN_GENERATED = 0;
    public static final int KM_ORIGIN_IMPORTED = 2;
    public static final int KM_ORIGIN_UNKNOWN = 3;
    public static final int KM_PAD_NONE = 1;
    public static final int KM_PAD_PKCS7 = 64;
    public static final int KM_PAD_RSA_OAEP = 2;
    public static final int KM_PAD_RSA_PKCS1_1_5_ENCRYPT = 4;
    public static final int KM_PAD_RSA_PKCS1_1_5_SIGN = 5;
    public static final int KM_PAD_RSA_PSS = 3;
    public static final int KM_PURPOSE_DECRYPT = 1;
    public static final int KM_PURPOSE_ENCRYPT = 0;
    public static final int KM_PURPOSE_SIGN = 2;
    public static final int KM_PURPOSE_VERIFY = 3;
    public static final int KM_TAG_ACTIVE_DATETIME = 0x60000190;
    public static final int KM_TAG_ALGORITHM = 0x10000002;
    public static final int KM_TAG_ALLOW_WHILE_ON_BODY = 0x700001fa;
    public static final int KM_TAG_ALL_APPLICATIONS = 0x70000258;
    public static final int KM_TAG_ALL_USERS = 0x700001f4;
    public static final int KM_TAG_APPLICATION_ID = 0x90000259;
    public static final int KM_TAG_ASSOCIATED_DATA = 0x900003e8;
    public static final int KM_TAG_ATTESTATION_CHALLENGE = 0x900002c4;
    public static final int KM_TAG_ATTESTATION_ID_BRAND = 0x900002c6;
    public static final int KM_TAG_ATTESTATION_ID_DEVICE = 0x900002c7;
    public static final int KM_TAG_ATTESTATION_ID_IMEI = 0x900002ca;
    public static final int KM_TAG_ATTESTATION_ID_MANUFACTURER = 0x900002cc;
    public static final int KM_TAG_ATTESTATION_ID_MEID = 0x900002cb;
    public static final int KM_TAG_ATTESTATION_ID_MODEL = 0x900002cd;
    public static final int KM_TAG_ATTESTATION_ID_PRODUCT = 0x900002c8;
    public static final int KM_TAG_ATTESTATION_ID_SERIAL = 0x900002c9;
    public static final int KM_TAG_AUTH_TIMEOUT = 0x300001f9;
    public static final int KM_TAG_AUTH_TOKEN = 0x900003ea;
    public static final int KM_TAG_BLOB_USAGE_REQUIREMENTS = 0x100002c1;
    public static final int KM_TAG_BLOCK_MODE = 0x20000004;
    public static final int KM_TAG_CALLER_NONCE = 0x70000007;
    public static final int KM_TAG_CREATION_DATETIME = 0x600002bd;
    public static final int KM_TAG_DIGEST = 0x20000005;
    public static final int KM_TAG_INCLUDE_UNIQUE_ID = 0x700000ca;
    public static final int KM_TAG_INVALID = 0;
    public static final int KM_TAG_KEY_SIZE = 0x30000003;
    public static final int KM_TAG_MAC_LENGTH = 0x300003eb;
    public static final int KM_TAG_MAX_USES_PER_BOOT = 0x30000194;
    public static final int KM_TAG_MIN_MAC_LENGTH = 0x30000008;
    public static final int KM_TAG_MIN_SECONDS_BETWEEN_OPS = 0x30000193;
    public static final int KM_TAG_NONCE = 0x900003e9;
    public static final int KM_TAG_NO_AUTH_REQUIRED = 0x700001f7;
    public static final int KM_TAG_ORIGIN = 0x100002be;
    public static final int KM_TAG_ORIGINATION_EXPIRE_DATETIME = 0x60000191;
    public static final int KM_TAG_PADDING = 0x20000006;
    public static final int KM_TAG_PURPOSE = 0x20000001;
    public static final int KM_TAG_RESCOPING_ADD = 0x20000065;
    public static final int KM_TAG_RESCOPING_DEL = 0x20000066;
    public static final int KM_TAG_ROLLBACK_RESISTANT = 0x700002bf;
    public static final int KM_TAG_ROOT_OF_TRUST = 0x900002c0;
    public static final int KM_TAG_RSA_PUBLIC_EXPONENT = 0x500000c8;
    public static final int KM_TAG_UNIQUE_ID = 0x900002c3;
    public static final int KM_TAG_USAGE_EXPIRE_DATETIME = 0x60000192;
    public static final int KM_TAG_USER_AUTH_TYPE = 0x100001f8;
    public static final int KM_TAG_USER_ID = 0x300001f5;
    public static final int KM_TAG_USER_SECURE_ID = 0xa00001f6;
    public static final int KM_UINT = 0x30000000;
    public static final int KM_UINT_REP = 0x40000000;
    public static final int KM_ULONG = 0x50000000;
    public static final int KM_ULONG_REP = 0xa0000000;
    public static final Map sErrorCodeToString;

    static 
    {
        sErrorCodeToString = new HashMap();
        sErrorCodeToString.put(Integer.valueOf(0), "OK");
        sErrorCodeToString.put(Integer.valueOf(-2), "Unsupported purpose");
        sErrorCodeToString.put(Integer.valueOf(-3), "Incompatible purpose");
        sErrorCodeToString.put(Integer.valueOf(-4), "Unsupported algorithm");
        sErrorCodeToString.put(Integer.valueOf(-5), "Incompatible algorithm");
        sErrorCodeToString.put(Integer.valueOf(-6), "Unsupported key size");
        sErrorCodeToString.put(Integer.valueOf(-7), "Unsupported block mode");
        sErrorCodeToString.put(Integer.valueOf(-8), "Incompatible block mode");
        sErrorCodeToString.put(Integer.valueOf(-9), "Unsupported MAC or authentication tag length");
        sErrorCodeToString.put(Integer.valueOf(-10), "Unsupported padding mode");
        sErrorCodeToString.put(Integer.valueOf(-11), "Incompatible padding mode");
        sErrorCodeToString.put(Integer.valueOf(-12), "Unsupported digest");
        sErrorCodeToString.put(Integer.valueOf(-13), "Incompatible digest");
        sErrorCodeToString.put(Integer.valueOf(-14), "Invalid expiration time");
        sErrorCodeToString.put(Integer.valueOf(-15), "Invalid user ID");
        sErrorCodeToString.put(Integer.valueOf(-16), "Invalid user authorization timeout");
        sErrorCodeToString.put(Integer.valueOf(-17), "Unsupported key format");
        sErrorCodeToString.put(Integer.valueOf(-18), "Incompatible key format");
        sErrorCodeToString.put(Integer.valueOf(-21), "Invalid input length");
        sErrorCodeToString.put(Integer.valueOf(-24), "Key not yet valid");
        sErrorCodeToString.put(Integer.valueOf(-25), "Key expired");
        sErrorCodeToString.put(Integer.valueOf(-26), "Key user not authenticated");
        sErrorCodeToString.put(Integer.valueOf(-28), "Invalid operation handle");
        sErrorCodeToString.put(Integer.valueOf(-30), "Signature/MAC verification failed");
        sErrorCodeToString.put(Integer.valueOf(-31), "Too many operations");
        sErrorCodeToString.put(Integer.valueOf(-33), "Invalid key blob");
        sErrorCodeToString.put(Integer.valueOf(-38), "Invalid argument");
        sErrorCodeToString.put(Integer.valueOf(-39), "Unsupported tag");
        sErrorCodeToString.put(Integer.valueOf(-40), "Invalid tag");
        sErrorCodeToString.put(Integer.valueOf(-41), "Memory allocation failed");
        sErrorCodeToString.put(Integer.valueOf(-50), "Unsupported EC field");
        sErrorCodeToString.put(Integer.valueOf(-51), "Required IV missing");
        sErrorCodeToString.put(Integer.valueOf(-52), "Invalid IV");
        sErrorCodeToString.put(Integer.valueOf(-55), "Caller-provided IV not permitted");
        sErrorCodeToString.put(Integer.valueOf(-57), "Invalid MAC or authentication tag length");
        sErrorCodeToString.put(Integer.valueOf(-66), "Unable to attest device ids");
        sErrorCodeToString.put(Integer.valueOf(-100), "Not implemented");
        sErrorCodeToString.put(Integer.valueOf(-1000), "Unknown error");
    }
}
