package manager;

import java.io.IOException;

/**
 * Interface for file operations (text and binary)
 */
public interface FileOperations {
    /**
     * Save data to a text file
     */
    void saveToTextFile(String filename) throws IOException;
    
    /**
     * Load data from a text file
     */
    void loadFromTextFile(String filename) throws IOException;
    
    /**
     * Save data to a binary file
     */
    void saveToBinaryFile(String filename) throws IOException;
    
    /**
     * Load data from a binary file
     */
    void loadFromBinaryFile(String filename) throws IOException, ClassNotFoundException;
}
