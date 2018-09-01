// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.annotations;

import java.lang.annotation.Annotation;

public interface VisibleForTesting
    extends Annotation
{
    public static final class Visibility extends Enum
    {

        public static Visibility valueOf(String s)
        {
            return (Visibility)Enum.valueOf(com/android/internal/annotations/VisibleForTesting$Visibility, s);
        }

        public static Visibility[] values()
        {
            return $VALUES;
        }

        private static final Visibility $VALUES[];
        public static final Visibility PACKAGE;
        public static final Visibility PRIVATE;
        public static final Visibility PROTECTED;

        static 
        {
            PROTECTED = new Visibility("PROTECTED", 0);
            PACKAGE = new Visibility("PACKAGE", 1);
            PRIVATE = new Visibility("PRIVATE", 2);
            $VALUES = (new Visibility[] {
                PROTECTED, PACKAGE, PRIVATE
            });
        }

        private Visibility(String s, int i)
        {
            super(s, i);
        }
    }


    public abstract Visibility visibility();
}
