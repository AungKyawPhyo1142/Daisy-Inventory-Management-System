package HelpFrame;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class HelpClass {

    public void Help( ) {
        
         if (Desktop.isDesktopSupported()) {
             try {
                    URL url = getClass().getResource("src\\helpPDF\\D160 FORM (FAQ).pdf");
                    File myFile = new File(url.toURI());
                    Desktop.getDesktop().open(myFile);
            } catch (IOException | URISyntaxException ex) {
                        // no application registered for PDFs
                }
            }
        
    }
    
}
