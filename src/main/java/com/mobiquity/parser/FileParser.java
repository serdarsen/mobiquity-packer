package com.mobiquity.parser;

import com.mobiquity.exception.APIException;
import com.mobiquity.exception.Messages;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * Reads given file.
 *
 * @author Serdar ŞEN
 */
public class FileParser {
    private String filePath;

    public FileParser() {
    }

    /**
     * Read given file
     * @param filePath the file path to parse
     * @return the line strings.
     * @throws APIException an exception occurred during parsing
     */
    public Stream<String> lines(String filePath) throws  APIException {
        this.filePath = filePath;
        Stream<String> fileLines;
        try {
            fileLines = Files.lines(Path.of(filePath));
        }catch (IOException e){
            throw new APIException(Messages.fileIsInvalid, e);
        }

        return fileLines;
    }
}
