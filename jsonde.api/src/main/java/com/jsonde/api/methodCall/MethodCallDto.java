package com.jsonde.api.methodCall;

import java.io.Serializable;

/**
 * Commenti Javadoc
 * @author gabriele
 *
 */
public class MethodCallDto implements Serializable {

	/**
	 * Variabile RETURN_VALUE_FLAG
	 */
    public static final byte RETURN_VALUE_FLAG = 0;
    
    /**
     * THROW_EXCEPTION_FLAG
     */
    public static final byte THROW_EXCEPTION_FLAG = 1;
    

    /**
     * CALLER_ID_SET_FLAG
     */
    public static final byte CALLER_ID_SET_FLAG = 2;
    
    /**
     * ACTUAL_CLASS_ID_SET_FLAG 
     */
    public static final byte ACTUAL_CLASS_ID_SET_FLAG = 3;

    /**
     * Flags
     */
    public byte flags;

    /**
     * methodCallId
     */
    public long methodCallId;
    
    /**
     * callerId
     */
    public long callerId;

    /**
     * long methodId
     */
    public long methodId;
    
    /**
     * actualClassId
     */
    public long actualClassId;

    public long executionTime;

    public void returnToPool() {
        MethodCallDtoFactory.returnMethodCallDtoToPool(this);
    }

    @Override
    public String toString() {
        return "MethodCallDto{" +
                "flags=" + flags +
                ", methodCallId=" + methodCallId +
                ", callerId=" + callerId +
                ", methodId=" + methodId +
                ", actualClassId=" + actualClassId +
                ", executionTime=" + executionTime +
                '}';
    }
}