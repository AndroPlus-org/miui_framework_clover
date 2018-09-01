// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.pm;


public final class PackageServiceDumpProto
{
    public final class FeatureProto
    {

        public static final long NAME = 0x10e00000001L;
        public static final long VERSION = 0x10300000002L;
        final PackageServiceDumpProto this$0;

        public FeatureProto()
        {
            this$0 = PackageServiceDumpProto.this;
            super();
        }
    }

    public final class PackageShortProto
    {

        public static final long NAME = 0x10e00000001L;
        public static final long UID = 0x10300000002L;
        final PackageServiceDumpProto this$0;

        public PackageShortProto()
        {
            this$0 = PackageServiceDumpProto.this;
            super();
        }
    }

    public final class SharedLibraryProto
    {

        public static final long APK = 0x10e00000004L;
        public static final long IS_JAR = 0x10d00000002L;
        public static final long NAME = 0x10e00000001L;
        public static final long PATH = 0x10e00000003L;
        final PackageServiceDumpProto this$0;

        public SharedLibraryProto()
        {
            this$0 = PackageServiceDumpProto.this;
            super();
        }
    }

    public final class SharedUserProto
    {

        public static final long NAME = 0x10e00000002L;
        public static final long USER_ID = 0x10300000001L;
        final PackageServiceDumpProto this$0;

        public SharedUserProto()
        {
            this$0 = PackageServiceDumpProto.this;
            super();
        }
    }


    public PackageServiceDumpProto()
    {
    }

    public static final long FEATURES = 0x21100000004L;
    public static final long MESSAGES = 0x20e00000007L;
    public static final long PACKAGES = 0x21100000005L;
    public static final long REQUIRED_VERIFIER_PACKAGE = 0x11100000001L;
    public static final long SHARED_LIBRARIES = 0x21100000003L;
    public static final long SHARED_USERS = 0x21100000006L;
    public static final long VERIFIER_PACKAGE = 0x11100000002L;
}
