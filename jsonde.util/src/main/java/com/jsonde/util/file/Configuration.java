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
public class Configuration {

	/**
	 * Legge da file il nome utente di MySQL
	 * @return
	 */
	public static String user() {
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
	
}
