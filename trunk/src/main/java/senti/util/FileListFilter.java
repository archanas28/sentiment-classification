package senti.util;

import java.io.File;
import java.io.FilenameFilter;

public class FileListFilter implements FilenameFilter {
    
    public boolean accept(File dir, String name) {
        return name.endsWith("." + new Props().getProperty("data_extension"));
    }

}
