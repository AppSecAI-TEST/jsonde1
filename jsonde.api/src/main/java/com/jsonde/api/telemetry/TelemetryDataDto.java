package com.jsonde.api.telemetry;

import java.io.Serializable;

/**
 * Commenti Javadoc
 * @author gabriele
 *
 */
public class TelemetryDataDto implements Serializable {

	/**
     * Variabile time
     */
    public long time;
    
    /**
     * freeMemory
     */
    public long freeMemory;

    /**
     * maxMemory
     */
    public long maxMemory;
    
    /**
    * totalMemory
    */
    public long totalMemory;

    /**
     * loadedClassCount
     */
    public int loadedClassCount;
    
    /**
     * classCount
     */
    public long classCount;
    
    /**
     * unloadedClassCount;
     */
    public long unloadedClassCount;

    /**
     * long totalCompilationTime
     */
    public long totalCompilationTime;

}