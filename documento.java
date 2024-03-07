import org.odftoolkit.simple.TextDocument;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class LeerArchivos {
    public static void main(String[] args) {
        String archivoODT = "documento.odt";
        String archivoDOCX = "documento.docx";
        String archivoPDF = "documento.pdf";

        leerODT(archivoODT);
        leerDOCX(archivoDOCX);
        leerPDF(archivoPDF);
    }

    public static void leerODT(String ruta) {
        try {
            TextDocument document = TextDocument.loadDocument(new File(ruta));
            String contenido = document.getContentRoot().getTextContent();
            System.out.println(contenido);
        } catch (Exception e) {
            System.out.println("Error al leer el archivo ODT: " + e.getMessage());
        }
    }

    public static void leerDOCX(String ruta) {
        try {
            WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(new FileInputStream(ruta));
            MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();
            String contenido = documentPart.getContent().toString();
            System.out.println(contenido);
        } catch (Exception e) {
            System.out.println("Error al leer el archivo DOCX: " + e.getMessage());
        }
    }

    public static void leerPDF(String ruta) {
        try {
            PDDocument document = PDDocument.load(new File(ruta));
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String contenido = pdfStripper.getText(document);
            System.out.println(contenido);
            document.close();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo PDF: " + e.getMessage());
        }
    }
}
