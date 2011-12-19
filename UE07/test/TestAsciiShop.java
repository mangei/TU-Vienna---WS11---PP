import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Test;

/**
 * Test um alle Testf�lle zu testen.
 *
 * ------
 *
 * JUnit Library in den Build Path hinzuf�gen:
 *  Rechte Maustaste auf das Projekt - Properties
 *  unter Java Build Path
 *  unter Libraries
 *  Add Library...
 *  JUnit
 *  ...
 * 
 * ------
 * 
 * Test konfigurieren:
 *  Die drei Membervariablen von TestAsciiShop.java m�ssen entsprechend den Beispielen
 *  abge�ndert werden.
 *  
 *  Die mitgelieferten Testdateien *.i# und *.o# m�ssen in den Testordner kopiert werden.
 *  Der Test erstellt automatisch *.out# Dateien und vergleicht diese mit den entsprechenden
 *  *.o# Dateien und ermittelt so das Ergebnis.
 * 
 * ------
 * 
 * Tests starten:
 *  Rechte Maustaste auf TestAsciiShop.java - Run as - JUnit Test
 *  
 * Der Testoutput ist in der "Console"-View zu finden. Bei Fehlern sind diese in der 
 * "Console"-View und in der "JUnit"-View zu finden.
 * 
 * ------
 * 
 * Changelog:
 * 
 * v3
 *  AsciiShop-Hack entfernt. System.in wird �berschrieben.
 * v2
 *  Anleitung hinzugef�gt
 * v1
 *  Test erstellt
 *  
 * @author Manuel Geier (1126137)
 * @version 2
 *
 */
public class TestAsciiShop {

	/**
	 * 
	 */
	private String testfileFolder = "test/";			// Testordner
	private String testfileName = "asciishop-A07-PP";	// Name der Testdateien (ohne Endung)
	private int testfileCount = 4;						// Anzahl der Testdateien
	
	/**
	 * Test
	 * @throws IOException
	 */
	@Test
	public void testMain() throws IOException {
		
		PrintStream console = System.out;
		console.write(("Test werden durchgef�hrt:\n").getBytes());
		
		// Testf�lle testen
		for(int i = 1; i <= testfileCount; i++) {
			
			// Eingabedatei
			File f = new File(testfileFolder + testfileName + ".i" + i);
			FileInputStream fis = new FileInputStream(f);
			
			// Ausgabedatei
			File o = new File(testfileFolder + testfileName + ".out" + i);
			FileOutputStream fos = new FileOutputStream(o);
			System.setOut(new PrintStream(fos));
			
			// Eingabestream �berschreiben
			System.setIn(fis);
			
			console.write(("Test " + i + "...").getBytes());
			
			// Test starten
			AsciiShop.main(null);
			
			console.write(("abgeschlossen\n").getBytes());
			
			fos.close();
			fis.close();
		}
		
		console.write(("\nTest werden verglichen:\n").getBytes());
		
		int testOKCount = 0;
		
		// Testf�lle vergleichen
		for(int i = 1; i <= testfileCount; i++) {
			
			console.write(("Vergleiche Test " + i + "...").getBytes());
			
			// Eingabedatei 1
			File f1 = new File(testfileFolder + testfileName + ".o" + i);
			FileReader fr1 = new FileReader(f1);
			BufferedReader br1 = new BufferedReader(fr1);
			
			// Eingabedatei 1
			File f2 = new File(testfileFolder + testfileName + ".out" + i);
			FileReader fr2 = new FileReader(f2);
			BufferedReader br2 = new BufferedReader(fr2);
			
			StringBuffer b1 = new StringBuffer();
			StringBuffer b2 = new StringBuffer();
			
			String fileLine;
			while((fileLine = br1.readLine()) != null) {
				b1.append(fileLine);
			}
			while((fileLine = br2.readLine()) != null) {
				b2.append(fileLine);
			}
			
			br1.close();
			br2.close();
			
			// Zeilenumbr�che am Ende werden dadurch ignoriert
			String s1 = b1.toString().trim();
			String s2 = b2.toString().trim();
			
			
			/**
			 * Folgendes, exakteres compare kann nicht verwendet werden, da
			 * Zeilenumbr�che am Ende der Datei nicht gewertet werden.
			 */
//			String r1 = null, r2 = null;
//			int line = 0;
//			boolean check = true;
//			do {
//				line++;
//				
//				r1 = br1.readLine();
//				r2 = br2.readLine();
//				
//				if((r1 == null && r2 != null) || 
//				   (r1 != null && r2 == null) ||
//				   //(((r1 != null || r2 != null) && ("".equals(r1) || "".equals(r2)))) ||
//				   (r1 != null && r2 != null && !r1.equals(r2))) {
//					
//					console.write(("\nZeile " + line + ": " + r1).getBytes());
//					console.write(("\nZeile " + line + ": " + r2 + "\n").getBytes());
//					
//					check = false;
//				}
//			}
//			while(r1 != null && r2 != null && check);
			
			if(s1.equals(s2)) {
				console.write(("RICHTIG\n").getBytes());
				testOKCount++;
			} else {
				console.write(("FALSCH\n").getBytes());
			}
		}
		
		console.write(("\n" +  testOKCount+ " von " + testfileCount + " Tests erfolgreich!\n").getBytes());
		
		assertTrue((testfileCount-testOKCount) + " von " + testfileCount + " Tests fehlgeschlagen!", testOKCount == testfileCount);
	}

}
