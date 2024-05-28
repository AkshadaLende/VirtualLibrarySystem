package org.example.VirtualLibraryMangement.bookManagement.factory;

public class FormatFactory {

    public static FileFormatParser getFileFormatObject(String fileType) {
       if(fileType.equalsIgnoreCase("json")){
           return new JsonFileFormat();
       }else if(fileType.equalsIgnoreCase("xml")){
           return new XmlFileFormat();
       }
       return null;
    }
}
