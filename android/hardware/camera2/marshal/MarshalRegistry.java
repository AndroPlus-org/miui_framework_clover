// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.marshal;

import android.hardware.camera2.utils.TypeReference;
import java.util.*;

// Referenced classes of package android.hardware.camera2.marshal:
//            Marshaler, MarshalQueryable, MarshalHelpers

public class MarshalRegistry
{
    private static class MarshalToken
    {

        public boolean equals(Object obj)
        {
            boolean flag = false;
            if(obj instanceof MarshalToken)
            {
                obj = (MarshalToken)obj;
                boolean flag1 = flag;
                if(typeReference.equals(((MarshalToken) (obj)).typeReference))
                {
                    flag1 = flag;
                    if(nativeType == ((MarshalToken) (obj)).nativeType)
                        flag1 = true;
                }
                return flag1;
            } else
            {
                return false;
            }
        }

        public int hashCode()
        {
            return hash;
        }

        private final int hash;
        final int nativeType;
        final TypeReference typeReference;

        public MarshalToken(TypeReference typereference, int i)
        {
            typeReference = typereference;
            nativeType = i;
            hash = typereference.hashCode() ^ i;
        }
    }


    private MarshalRegistry()
    {
        throw new AssertionError();
    }

    public static Marshaler getMarshaler(TypeReference typereference, int i)
    {
        Object obj = sMarshalLock;
        obj;
        JVM INSTR monitorenter ;
        MarshalToken marshaltoken;
        Marshaler marshaler;
        marshaltoken = JVM INSTR new #6   <Class MarshalRegistry$MarshalToken>;
        marshaltoken.MarshalToken(typereference, i);
        marshaler = (Marshaler)sMarshalerMap.get(marshaltoken);
        Object obj1;
        obj1 = marshaler;
        if(marshaler != null)
            break MISSING_BLOCK_LABEL_188;
        if(sRegisteredMarshalQueryables.size() == 0)
        {
            typereference = JVM INSTR new #35  <Class AssertionError>;
            typereference.AssertionError("No available query marshalers registered");
            throw typereference;
        }
        break MISSING_BLOCK_LABEL_65;
        typereference;
        obj;
        JVM INSTR monitorexit ;
        throw typereference;
        Iterator iterator = sRegisteredMarshalQueryables.iterator();
_L2:
        obj1 = marshaler;
        if(!iterator.hasNext())
            break; /* Loop/switch isn't completed */
        obj1 = (MarshalQueryable)iterator.next();
        if(!((MarshalQueryable) (obj1)).isTypeMappingSupported(typereference, i))
            continue; /* Loop/switch isn't completed */
        obj1 = ((MarshalQueryable) (obj1)).createMarshaler(typereference, i);
        break; /* Loop/switch isn't completed */
        if(true) goto _L2; else goto _L1
_L1:
        if(obj1 != null)
            break MISSING_BLOCK_LABEL_178;
        UnsupportedOperationException unsupportedoperationexception = JVM INSTR new #85  <Class UnsupportedOperationException>;
        obj1 = JVM INSTR new #87  <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        unsupportedoperationexception.UnsupportedOperationException(((StringBuilder) (obj1)).append("Could not find marshaler that matches the requested combination of type reference ").append(typereference).append(" and native type ").append(MarshalHelpers.toStringNativeType(i)).toString());
        throw unsupportedoperationexception;
        sMarshalerMap.put(marshaltoken, obj1);
        obj;
        JVM INSTR monitorexit ;
        return ((Marshaler) (obj1));
    }

    public static void registerMarshalQueryable(MarshalQueryable marshalqueryable)
    {
        Object obj = sMarshalLock;
        obj;
        JVM INSTR monitorenter ;
        sRegisteredMarshalQueryables.add(marshalqueryable);
        obj;
        JVM INSTR monitorexit ;
        return;
        marshalqueryable;
        throw marshalqueryable;
    }

    private static final Object sMarshalLock = new Object();
    private static final HashMap sMarshalerMap = new HashMap();
    private static final List sRegisteredMarshalQueryables = new ArrayList();

}
