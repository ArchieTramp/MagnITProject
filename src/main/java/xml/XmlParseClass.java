package xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import pojo.POJO;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Класс для описания парсинга 2.xml и нахождения суммы всех элементов.
 * SAX был выбран в связи с простотой xml документа и с тем, что DOM выдавал некоторые ошибки,
 * связанные вероятнее всего с моим малым опытом.
 */

public class XmlParseClass {

    private static ArrayList<POJO> fields = new ArrayList<>();

    public static void xmlParser() throws ParserConfigurationException, SAXException, IOException {

        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser parser = spf.newSAXParser();

        XMLHandler handler = new XMLHandler();
        parser.parse(new File("src/2.xml"), handler);
        int sum = 0;
        for (POJO pojo : fields) {
            sum += pojo.getField();
        }

        System.out.println("Арифметическая сумма всех элементов = " + sum);

    }

    private static class XMLHandler extends DefaultHandler {

        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            if (qName.equals("entry")) {
                int field = Integer.parseInt(attributes.getValue("field"));
                fields.add(new POJO(field));
            }
        }
    }
}
