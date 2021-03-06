package com.jsonde.profiler.network;

import com.jsonde.api.Message;
import com.jsonde.util.io.IO;
import com.jsonde.util.log.Log;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Commenti Javadoc
 * @author gabriele
 *
 */
public class ServerOutputWorker implements Runnable, Closeable {

    private final static Log log = Log.getLog(ServerOutputWorker.class);

    private NetworkServerImpl server;
    private final Socket socket;

    public ServerOutputWorker(NetworkServerImpl server, Socket socket) {
        this.server = server;
        this.socket = socket;
    }

    public void run() {

        final String METHOD_NAME = "run()";

        OutputStream outputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            outputStream = socket.getOutputStream();
            objectOutputStream = new ObjectOutputStream(outputStream);

            server.setOutputWorkerReady(true);

            log.info("[ServerOutputWorker] ready");

            boolean flag = server.isRunning();
            boolean flag1 = server.isMessageInQueue();
            
            while (flag || flag1) {

                log.info("[ServerOutputWorker] running");

                boolean flag2 = server.isMessageInQueue();
                
                while (flag2) {

                    log.info("[ServerOutputWorker] message is in queue");

                    Message message = server.takeMessageFromQueue();
                    objectOutputStream.writeObject(message);
                    objectOutputStream.flush();

                    message.returnToPool();

                    log.info("[ServerOutputWorker] message was sent");
                    flag2 = server.isMessageInQueue();
                    
                }

                log.info("[ServerOutputWorker] running");
                flag = server.isRunning();
                falg1 = server.isMessageInQueue();
                
            }

            log.info("[ServerOutputWorker] stopped");

        } catch (IOException e) {
            log.error(METHOD_NAME, e);
        } catch (InterruptedException e) {
            log.error(METHOD_NAME, e);
            Thread.currentThread().interrupt();
        } finally {
            IO.close(objectOutputStream);
            IO.close(outputStream);
        }

    }

    public void close() throws IOException {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
