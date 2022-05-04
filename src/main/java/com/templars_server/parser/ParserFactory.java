package com.templars_server.parser;

import com.templars_server.parser.MBParser.MBParser;

public class ParserFactory {

    public static Parser getParser(String key) throws NoParserFoundException {
        switch (key) {
            case "mb2":
                return new MBParser();
        }

        throw new NoParserFoundException(String.format("No input found for %s", key));
    }

}
