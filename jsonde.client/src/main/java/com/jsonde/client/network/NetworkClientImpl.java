package com.jsonde.client.network;

import com.jsonde.api.Message;
import com.jsonde.api.MessageListener;
import com.jsonde.api.function.FunctionRequest;
import com.jsonde.api.function.FunctionResponse;
import com.jsonde.util.log.Log;
import java.io.IOException;
import java.net.Socket;
import java.util.Collection;
import java.util.Vector;

/**
 * Commenti Javadoc
 * @author gabriele
 *
 */
public class NetworkClientImpl implements NetworkClient {

    private static final Log log = Log.getLog(NetworkClientImpl.class);

    private String address;
    private int port;

    private boolean running;
    private boolean outputWorkerReady;
    private boolean inputWorkerReady;

    private BlockingQueue<Message> messageQueue = new ArrayBlockingQueue<Message>(5);

    private Socket socket;

    private Thread outputThread;
    private Thread inputThread;

    private Collection<MessageListener> messageListeners = new Vector<MessageListener>();

    public NetworkClientImpl(String address, int port) {
        this.address = address;
        this.port = port;
    }

    public void start() throws NetworkClientException {

        try {
            socket = new Socket(address, port);
        } catch (IOException e) {
            throw new NetworkClientException(e);
        }

        setRunning(true);

        inputThread = new Thread(new ClientInputWorker(this, socket));
        inputThread.setDaemon(true);
        inputThread.start();

        outputThread = new Thread(new ClientOutputWorker(this, socket));
        outputThread.setDaemon(true);
        outputThread.start();

        waitForInitialization();

    }

    public void stop() throws NetworkClientException {

        final String METHOD_NAME = "stop()";

        setRunning(false);

        try {
            outputThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error(METHOD_NAME, e);
        }

        try {
            inputThread.join(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error(METHOD_NAME, e);
        }

        if (inputThread.isAlive()) {

            closeSockets();

            try {
                inputThread.join(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error(METHOD_NAME, e);
            }

            if (inputThread.isAlive()) {
                inputThread.interrupt();
            }

        } else {
            closeSockets();
        }

        processMessageExecutorService.shutdown();

        try {
            processMessageExecutorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error(METHOD_NAME, e);
        }

    }

    private void closeSockets() throws NetworkClientException {
    	synchronized (this){
    		try {
                socket.close();
            } catch (IOException e) {
                throw new NetworkClientException(e);
            }
            notifyAll();
    	}
    }

    private void waitForInitialization() {
    	synchronized (this){
            final String METHOD_NAME = "waitForInitialization()";

            boolean flag = areWorkersReady();
            try {
            	while (!flag) {
            		wait();
                flag = areWorkersReady();
            	}
            } catch (InterruptedException e) {
                log.error(METHOD_NAME, e);
                Thread.currentThread().interrupt();
            }
            notifyAll();
    	}
    }

    public void sendMessage(Message message) {

    	synchronized (this){
    		final String METHOD_NAME = "sendMessage(Message)";

            try {
                boolean inserted = false;
                while (!inserted) {
                    inserted = messageQueue.offer(message);
                    if (inserted) {
                        notifyAll();
                    } else {
                        wait();
                    }
                }
            } catch (InterruptedException e) {
                log.error(METHOD_NAME, e);
                Thread.currentThread().interrupt();
            }

    	}
    }

    public <T extends FunctionResponse> T invokeFunction(final FunctionRequest<T> functionRequest)
            throws InterruptedException {

        final SynchronousQueue<T> functionResponseExchanger =
                new SynchronousQueue<T>();

        addMessageListener(new MessageListener() {

            public void onMessage(Message message) {

                if (message instanceof FunctionResponse) {

                    T functionResponse = (T) message;

                    if (functionResponse.getRequestId() == functionRequest.getRequestId()) {
                        functionResponseExchanger.offer(functionResponse);
                    }

                }

            }

        });

        sendMessage(functionRequest);

        return functionResponseExchanger.take();

    }


    protected boolean isMessageInQueue() throws InterruptedException {
    	synchronized (this){
    		boolean flag = isRunning();
        	int a = messageQueue.size();
            while (flag && 0 == a) {
                wait();
                flag = isRunning();
                a = messageQueue.size();
            }
            return 0 != messageQueue.size();
    	}
    }

    protected Message takeMessageFromQueue() {
    	synchronized (this){
    		Message message = messageQueue.poll();
            notifyAll();
            return message;
    	}
    }

    private ExecutorService processMessageExecutorService = Executors.newSingleThreadExecutor();

    protected void processMessage(final Message message) {

        processMessageExecutorService.submit(new Runnable() {

            public void run() {

                for (MessageListener messageListener : messageListeners) {
                    messageListener.onMessage(message);
                }

            }

        });

    }

    public void addMessageListener(MessageListener messageListener) {
        messageListeners.add(messageListener);
    }

    public void removeMessageListener(MessageListener messageListener) {
        messageListeners.remove(messageListener);
    }


    public boolean isRunning() {
    	synchronized (this){
    		return running;
    	}
    }

    public void setRunning(boolean running) {
    	synchronized (this){
    		this.running = running;
            notifyAll();
    	}
    }

    private boolean areWorkersReady() {
    	synchronized (this){
    		return inputWorkerReady && outputWorkerReady;
    	}
    }

    public void setOutputWorkerReady(boolean outputWorkerReady) {
    	synchronized (this){
    		this.outputWorkerReady = outputWorkerReady;
            notifyAll();
    	}
    }

    public void setInputWorkerReady(boolean inputWorkerReady) {
    	synchronized (this){
    		this.inputWorkerReady = inputWorkerReady;
            notifyAll();
    	}
    }

}
