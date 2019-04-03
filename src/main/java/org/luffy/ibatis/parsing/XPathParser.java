package org.luffy.ibatis.parsing;

import org.w3c.dom.Document;

import org.xml.sax.*;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;

import javax.xml.xpath.XPathFactory;

import java.io.Reader;
import java.io.StringReader;

import java.util.Properties;

public class XPathParser {

    /**
     * Document 对象
     */
    private final Document document;
    /**
     * 是否校验
     */
    private boolean validation;
    /**
     * XML 实体解析器
     */
    private EntityResolver entityResolver;
    /**
     * Properties 对象
     */
    private Properties variables;
    /**
     * Java XPath 对象
     */
    private XPath xpath;

    public XPathParser(String xml, boolean validation, Properties variables, EntityResolver entityResolver) {
        commonConstructor(validation, variables, entityResolver);
        this.document = createDocument(new InputSource(new StringReader(xml)));
    }

    public XPathParser(Reader reader, boolean validation, Properties variables, EntityResolver entityResolver) {
        commonConstructor(validation, variables, entityResolver);
        this.document = createDocument(new InputSource(reader));
    }


    public void setVariables(Properties variables) {
        this.variables = variables;
    }


    private Document createDocument(InputSource inputSource) {
        // important: this must only be called AFTER common constructor
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(validation);

            factory.setNamespaceAware(false);
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(false);
            factory.setCoalescing(false);
            factory.setExpandEntityReferences(true);

            DocumentBuilder builder = factory.newDocumentBuilder();
            builder.setEntityResolver(entityResolver);
            builder.setErrorHandler(new ErrorHandler() {

                public void error(SAXParseException exception) throws SAXException {
                    throw exception;
                }


                public void fatalError(SAXParseException exception) throws SAXException {
                    throw exception;
                }


                public void warning(SAXParseException exception) throws SAXException {
                }
            });
            return builder.parse(inputSource);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }

    private void commonConstructor(boolean validation, Properties variables, EntityResolver entityResolver) {
        this.validation = validation;
        this.entityResolver = entityResolver;
        this.variables = variables;
        XPathFactory factory = XPathFactory.newInstance();
        this.xpath = factory.newXPath();
    }

}
