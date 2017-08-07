package com.jsonde.gui;

import com.jsonde.gui.dialog.license.LicenseDialog;
import com.jsonde.gui.license.LicenseManager;
import com.jsonde.gui.sdedit.SdEditUIAdapter;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Date;

/**
 * Commenti Javadoc
 * @author gabriele
 *
 */
public class Main {

	/**
	 * Main instance
	 */
    private static Main instance = new Main();

    public static Main getInstance() {
        if (null == instance) {
            instance = new Main();
        }
        return instance;
    }

    private String[] arguments;
    private ApplicationUserInterface sdEditUIAdapter;

    public void processException(Throwable e) {
        try {
            sdEditUIAdapter.processException(e);
        } catch (Exception ex) {
        	JOptionPane.showMessageDialog (
					null , "Eccezione ex lanciata"
			);
        }JOptionPane.showMessageDialog (
				null , "Eccezione lanciata"
		);
    }

    public Main() {
    }

    protected void createGui() {
        sdEditUIAdapter = new SdEditUIAdapter();

        final MyEventQueue myEventQueue = new MyEventQueue();

        Toolkit.getDefaultToolkit().getSystemEventQueue().push(myEventQueue);

        try {
            EventQueue.invokeAndWait(new Runnable() {
               public void run() {
                 Thread.currentThread().setUncaughtExceptionHandler(myEventQueue);
              }
            });
        } catch (InterruptedException e) {
            instance.processException(e);
        } catch (InvocationTargetException e) {
            instance.processException(e);
        }

    }

    private static class MyEventQueue extends EventQueue implements Thread.UncaughtExceptionHandler {

        public void uncaughtException(Thread t, Throwable e) {
            instance.processException(e);
        }

        @Override
        protected void dispatchEvent(AWTEvent event) {
            try {
                super.dispatchEvent(event);
            } catch (Throwable e) {
                instance.processException(e);
            }
        }

    }

    protected void checkLicense() {

        LicenseManager licenseManager = new LicenseManager();

        if (!licenseManager.checkLicense()) {

            LicenseDialog licenseDialog = new LicenseDialog();
            licenseDialog.setVisible(true);

            if (licenseDialog.isEvaluate()) {

                Date firstExecutionDate = licenseManager.getFirstExecutionDate();
                Date currentDate = Calendar.getInstance().getTime();

                long period = currentDate.getTime() - firstExecutionDate.getTime();

                if (period > (1000L * 60L * 60L * 24L * 30L)) {
                    JOptionPane.showMessageDialog(null, "Your evaluation period has expired");
                    System.exit(1);
                }

            } else if (licenseDialog.isEnterLicenseCode() && licenseDialog.isLicenseValid()) {

                String license = licenseDialog.getLicense();
                licenseManager.saveLicense(license);

            } else {
                System.exit(1);
            }

        }

    }

    public static void main(String... argms) {

        Main main = Main.getInstance();

        main.arguments = argms;

        //main.checkLicense();

        main.createGui();

    }

}
