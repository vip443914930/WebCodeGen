package com.codegen.util;

/**
 * @author chenxiaojun
 */
public class PrimitiveTypeWrapper extends FullyQualifiedJavaType {
    private static PrimitiveTypeWrapper booleanInstance;
    private static PrimitiveTypeWrapper byteInstance;
    private static PrimitiveTypeWrapper characterInstance;
    private static PrimitiveTypeWrapper doubleInstance;
    private static PrimitiveTypeWrapper floatInstance;
    private static PrimitiveTypeWrapper integerInstance;
    private static PrimitiveTypeWrapper longInstance;
    private static PrimitiveTypeWrapper shortInstance;

    private String toPrimitiveMethod;

    private PrimitiveTypeWrapper(String fullyQualifiedName,
                                 String toPrimitiveMethod) {
        super(fullyQualifiedName);
        this.toPrimitiveMethod = toPrimitiveMethod;
    }

    public static PrimitiveTypeWrapper getBooleanInstance() {
        if (booleanInstance == null) {
            booleanInstance = new PrimitiveTypeWrapper("java.lang.Boolean", //$NON-NLS-1$
                    "booleanValue()"); //$NON-NLS-1$
        }

        return booleanInstance;
    }

    public static PrimitiveTypeWrapper getByteInstance() {
        if (byteInstance == null) {
            byteInstance = new PrimitiveTypeWrapper("java.lang.Byte", //$NON-NLS-1$
                    "byteValue()"); //$NON-NLS-1$
        }

        return byteInstance;
    }

    public static PrimitiveTypeWrapper getCharacterInstance() {
        if (characterInstance == null) {
            characterInstance = new PrimitiveTypeWrapper("java.lang.Character", //$NON-NLS-1$
                    "charValue()"); //$NON-NLS-1$
        }

        return characterInstance;
    }

    public static PrimitiveTypeWrapper getDoubleInstance() {
        if (doubleInstance == null) {
            doubleInstance = new PrimitiveTypeWrapper("java.lang.Double", //$NON-NLS-1$
                    "doubleValue()"); //$NON-NLS-1$
        }

        return doubleInstance;
    }

    public static PrimitiveTypeWrapper getFloatInstance() {
        if (floatInstance == null) {
            floatInstance = new PrimitiveTypeWrapper("java.lang.Float", //$NON-NLS-1$
                    "floatValue()"); //$NON-NLS-1$
        }

        return floatInstance;
    }

    public static PrimitiveTypeWrapper getIntegerInstance() {
        if (integerInstance == null) {
            integerInstance = new PrimitiveTypeWrapper("java.lang.Integer", //$NON-NLS-1$
                    "intValue()"); //$NON-NLS-1$
        }

        return integerInstance;
    }

    public static PrimitiveTypeWrapper getLongInstance() {
        if (longInstance == null) {
            longInstance = new PrimitiveTypeWrapper("java.lang.Long", //$NON-NLS-1$
                    "longValue()"); //$NON-NLS-1$
        }

        return longInstance;
    }

    public static PrimitiveTypeWrapper getShortInstance() {
        if (shortInstance == null) {
            shortInstance = new PrimitiveTypeWrapper("java.lang.Short", //$NON-NLS-1$
                    "shortValue()"); //$NON-NLS-1$
        }

        return shortInstance;
    }
}
