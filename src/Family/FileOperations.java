package Family;

import java.io.Serializable;

public interface FileOperations {
    boolean save(Serializable serializable);
    Object read();
    void setPath(String filePath);
}
