package xml;

import jdk.internal.org.xml.sax.SAXException;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.DOMBuilder;
import org.jdom2.input.SAXBuilder;
import org.w3c.dom.Document;
import pojo.POJO;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlParseClass {

        public static void xmlParser() throws Exception {
        String fileName = "src/2.xml";
        org.jdom2.Document jDoc = createJDOMusingSAXParser(fileName);
        org.jdom2.Element root = jDoc.getRootElement();
        List<Element> elements = root.getChildren("entries");
        List<POJO> entrieses = new ArrayList<>();
        for (org.jdom2.Element entriesList : elements) {
            POJO pojo = new POJO();
            pojo.setId(Integer.parseInt(entriesList.getAttributeValue("id")));
            pojo.setField(Integer.parseInt(entriesList.getChildText("field")));
            entrieses.add(pojo);
        }
        for (POJO pojo : entrieses) {
            // TODO: 09.03.2021 арифметическое сложение значений
            System.out.println(pojo.toString());
        }

    }

//    private static org.jdom2.Document createJDOMusingDOMParser(String fileName) throws ParserConfigurationException, IOException, SAXException, org.xml.sax.SAXException {
//     DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//     DocumentBuilder documentBuilder;
//     documentBuilder = dbf.newDocumentBuilder();
//     Document doc = documentBuilder.parse(new File(fileName));
//        DOMBuilder domBuilder = new DOMBuilder();
//
//        return domBuilder.build(doc);
//    }

    private static org.jdom2.Document createJDOMusingSAXParser(String fileName)
            throws JDOMException, IOException {
        SAXBuilder saxBuilder = new SAXBuilder();
        System.out.println(saxBuilder);
        return saxBuilder.build(new File(fileName));
    }
}
