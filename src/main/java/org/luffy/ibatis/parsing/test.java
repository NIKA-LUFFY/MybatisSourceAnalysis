package org.luffy.ibatis.parsing;

import java.io.IOException;
import java.io.Reader;

public class test {
    public static void main(String[] args) throws IOException {

        try (Reader reader = Resources.getResourceAsReader("org/luffy/ibatis/parsing/mybatis-config.xml")) {
            XPathParser parser = new XPathParser(reader, true, null,new XMLMapperEntityResolver());
        }
    }
}
