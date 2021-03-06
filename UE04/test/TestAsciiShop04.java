import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Test;

/**
 * Test um alle Testfälle zu testen.
 *
 * ------
 *
 * JUnit Library in den Build Path hinzufügen:
 *  Rechte Maustaste auf das Projekt - Properties
 *  unter Java Build Path
 *  unter Libraries
 *  Add Library...
 *  JUnit
 *  ...
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
 * v4
 *  Test-Dateien werden automatisch aus dem Verzeichnis gelesen. Dadurch können auch 
 *  gleich direkt die Bonus-Test-Dateien mitgetestet werden.
 * v3
 *  AsciiShop-Hack entfernt. System.in wird überschrieben.
 * v2
 *  Anleitung hinzugefügt
 * v1
 *  Test erstellt
 *  
 * @author Manuel Geier (1126137)
 * @version 4
 *
 */
public class TestAsciiShop04 {
	
	private final String testfolderName = "test/";
	
	/**
	 * Test
	 * @throws IOException
	 */
	@Test
	public void testMain() throws IOException {
		
		final PrintStream console = System.out;
		console.write(("Test werden durchgeführt:\n").getBytes());
		
		File testfolder = new File(testfolderName);
		File[] testfiles = testfolder.listFiles(new FilenameFilter() {
			
			public boolean accept(File dir, String name) {
				return name.matches(".*[.][i][0-9]+$");
			}
		});
		
		int testfileCount = testfiles.length;
		
		// Testfälle testen
		for(int i = 0; i < testfileCount; i++) {
			
			// Eingabedatei
			FileInputStream fis = new FileInputStream(testfiles[i]);
			
			// Ausgabedatei
			File o = new File(testfolderName + testfiles[i].getName().replaceAll("(.*[.])([i])([0-9]+)$", "$1out$3"));
			FileOutputStream fos = new FileOutputStream(o);
			System.setOut(new PrintStream(fos));
			
			// Eingabestream überschreiben
			System.setIn(fis);
			
			console.write(("Test " + (i+1) + ": " + testfiles[i].getName() + " ... ").getBytes());
			
			// Test starten
			AsciiShop.main(null);
			
			console.write(("abgeschlossen\n").getBytes());
			
			fos.close();
			fis.close();
		}
		
		console.write(("\nTest werden verglichen:\n").getBytes());
		
		int testOKCount = 0;
		
		// Testfälle vergleichen
		for(int i = 0; i < testfileCount; i++) {
			
			console.write(("Vergleiche Test " + (i+1) + "...").getBytes());
			
			// Eingabedatei 1
			File f1 = new File(testfolderName + testfiles[i].getName().replaceAll("(.*[.])([i])([0-9]+)$", "$1o$3"));
			
			
			FileReader fr1 = new FileReader(f1);
			BufferedReader br1 = new BufferedReader(fr1);
			
			// Eingabedatei 1
			File f2 = new File(testfolderName + testfiles[i].getName().replaceAll("(.*[.])([i])([0-9]+)$", "$1out$3"));
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
			
			// Zeilenumbrüche am Ende werden dadurch ignoriert
			String s1 = b1.toString().trim();
			String s2 = b2.toString().trim();
			
			
			/**
			 * Folgendes, exakteres compare kann nicht verwendet werden, da
			 * Zeilenumbrüche am Ende der Datei nicht gewertet werden.
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
