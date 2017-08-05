package com.jsonde.util.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import javax.swing.JOptionPane;

/**
 * Commenti Javadoc
 * @author gabriele
 *
 */
public class FileUtils {

	/**
	 * Line separator
	 */
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");
    public static final String FILE_SEPARATOR = System.getProperty("file.separator");
    public static final String PATH_SEPARATOR = System.getProperty("path.separator");
    public static final String USER_HOME = System.getProperty("user.home");

    public static boolean deleteDirectory(File path) {
        if (path.exists()) {
            File[] files = path.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    deleteDirectory(files[i]);
                } else {
                    files[i].delete();
                }
            }
        }
        return (path.delete());
    }
    
    /**
	 * Legge da file il nome utente di MySQL
	 * @return
	 */
	private static String getUser() {
		String s = null;
		try {
			PrintStream output = new PrintStream(new File("user.txt"));
			output.println("user.home");
			output.flush();
			output.close();
		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog (
				null , "Problemi di lettura da file user"
			);
		}
		try {
			@SuppressWarnings("resource")
			BufferedReader buffer = new BufferedReader(new FileReader("user.txt"));
			s = buffer.readLine();
		} catch (IOException e) {
			JOptionPane.showMessageDialog (
				null , "Problemi di lettura da file user"
			);
		}
		return s;
	}
    /**
	 * Legge da file il nome utente di MySQL
	 * @return
	 */
	public static String user() {
		String s = null;
		try {
			PrintStream output = new PrintStream(new File("user.txt"));
			output.println("root");
			output.flush();
			output.close();
		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog (
				null , "Problemi di lettura da file user"
			);
		}
		try {
			@SuppressWarnings("resource")
			BufferedReader buffer = new BufferedReader(new FileReader("user.txt"));
			s = buffer.readLine();
		} catch (IOException e) {
			JOptionPane.showMessageDialog (
				null , "Problemi di lettura da file user"
			);
		}
		return s;
	}
    public static boolean createFile(File file) {

        if (!file.exists()) {

            File directory = file.getParentFile();

            if (!directory.exists()) {
                return directory.mkdirs();
            } else {
                return true;
            }

        } else {
            return true;
        }

    }

    public static String canonizePath(String path) throws IOException {
        File file = new File(path);
        return file.getCanonicalPath();
    }
   
}
