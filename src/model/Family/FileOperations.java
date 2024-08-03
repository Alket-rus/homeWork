package model.Family;

import java.io.Serializable;

public interface FileOperations {

    boolean save(Serializable obj);
    Object read();
    void setPath(String filePath);
}
