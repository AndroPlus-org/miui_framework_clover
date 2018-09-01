// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.autofill;

import com.android.internal.util.Preconditions;

// Referenced classes of package android.service.autofill:
//            RequiredValidators, InternalValidator, OptionalValidators, Validator

public final class Validators
{

    private Validators()
    {
        throw new UnsupportedOperationException("contains static methods only");
    }

    public static transient Validator and(Validator avalidator[])
    {
        return new RequiredValidators(getInternalValidators(avalidator));
    }

    private static InternalValidator[] getInternalValidators(Validator avalidator[])
    {
        Preconditions.checkArrayElementsNotNull(avalidator, "validators");
        InternalValidator ainternalvalidator[] = new InternalValidator[avalidator.length];
        for(int i = 0; i < avalidator.length; i++)
        {
            Preconditions.checkArgument(avalidator[i] instanceof InternalValidator, (new StringBuilder()).append("element ").append(i).append(" not provided by Android System: ").append(avalidator[i]).toString());
            ainternalvalidator[i] = (InternalValidator)avalidator[i];
        }

        return ainternalvalidator;
    }

    public static transient Validator or(Validator avalidator[])
    {
        return new OptionalValidators(getInternalValidators(avalidator));
    }
}
