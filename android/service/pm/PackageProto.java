// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.pm;


public final class PackageProto
{
    public final class SplitProto
    {

        public static final long NAME = 0x10e00000001L;
        public static final long REVISION_CODE = 0x10300000002L;
        final PackageProto this$0;

        public SplitProto()
        {
            this$0 = PackageProto.this;
            super();
        }
    }

    public final class UserInfoProto
    {

        public static final int COMPONENT_ENABLED_STATE_DEFAULT = 0;
        public static final int COMPONENT_ENABLED_STATE_DISABLED = 2;
        public static final int COMPONENT_ENABLED_STATE_DISABLED_UNTIL_USED = 4;
        public static final int COMPONENT_ENABLED_STATE_DISABLED_USER = 3;
        public static final int COMPONENT_ENABLED_STATE_ENABLED = 1;
        public static final long ENABLED_STATE = 0x11000000007L;
        public static final int FULL_APP_INSTALL = 1;
        public static final long ID = 0x10300000001L;
        public static final long INSTALL_TYPE = 0x11000000002L;
        public static final int INSTANT_APP_INSTALL = 2;
        public static final long IS_HIDDEN = 0x10d00000003L;
        public static final long IS_LAUNCHED = 0x10d00000006L;
        public static final long IS_STOPPED = 0x10d00000005L;
        public static final long IS_SUSPENDED = 0x10d00000004L;
        public static final long LAST_DISABLED_APP_CALLER = 0x10e00000008L;
        public static final int NOT_INSTALLED_FOR_USER = 0;
        final PackageProto this$0;

        public UserInfoProto()
        {
            this$0 = PackageProto.this;
            super();
        }
    }


    public PackageProto()
    {
    }

    public static final long INSTALLER_NAME = 0x10e00000007L;
    public static final long INSTALL_TIME_MS = 0x10400000005L;
    public static final long NAME = 0x10e00000001L;
    public static final long SPLITS = 0x21100000008L;
    public static final long UID = 0x10300000002L;
    public static final long UPDATE_TIME_MS = 0x10400000006L;
    public static final long USERS = 0x21100000009L;
    public static final long VERSION_CODE = 0x10300000003L;
    public static final long VERSION_STRING = 0x10e00000004L;
}
