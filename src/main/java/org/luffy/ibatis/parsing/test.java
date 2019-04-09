package org.luffy.ibatis.parsing;

import java.io.IOException;
import java.io.Reader;

public class test {
    public static void main(String[] args) throws IOException {

        try (Reader reader = Resources.getResourceAsReader("org/luffy/ibatis/parsing/mybatis-config.xml")) {
            XPathParser parser = new XPathParser(reader, true, null,new XMLMapperEntityResolver());
            XNode xNode = parser.evalNode("/configuration");
            XNode environments = xNode.evalNode("environments");

            if (environments != null) {

                String environment = environments.getStringAttribute("default");

                for (XNode child : environments.getChildren()) {
                    String id = child.getStringAttribute("id");
                    XNode transactionManager = child.evalNode("transactionManager");
                    XNode dataSource = child.evalNode("dataSource");
                    System.out.println("id : " + id);
                    System.out.println("transactionManager : " + transactionManager);
                    System.out.println("dataSource : " + dataSource);
                }
            }
        }
    }
}
