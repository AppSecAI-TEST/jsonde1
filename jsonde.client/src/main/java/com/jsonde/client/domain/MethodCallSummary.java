package com.jsonde.client.domain;

/**
 * Commenti Javadoc
 * @author gabriele
 *
 */
public class MethodCallSummary extends AbstractMethodCall {

	/**
	 * Variabile executionTime
	 */
    public Long executionTime;
    
    /**
     * throwExceptionCounter;
     */
    public Long throwExceptionCounter;
    
    /**
     * invocationCount
     */
    public Long invocationCount;

    public Long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Long executionTime) {
        this.executionTime = executionTime;
    }

    public Long getThrowExceptionCounter() {
        return throwExceptionCounter;
    }

    public void setThrowExceptionCounter(Long throwExceptionCounter) {
        this.throwExceptionCounter = throwExceptionCounter;
    }

    public Long getInvocationCount() {
        return invocationCount;
    }

    public void setInvocationCount(Long invocationCount) {
        this.invocationCount = invocationCount;
    }

    @Override
    public String toString() {
        return "MethodCallSummary{" +
                "executionTime=" + executionTime +
                ", throwExceptionCounter=" + throwExceptionCounter +
                ", invocationCount=" + invocationCount +
                "} " + super.toString();
    }
}