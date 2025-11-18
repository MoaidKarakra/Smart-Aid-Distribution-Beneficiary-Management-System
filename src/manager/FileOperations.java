package manager;

import java.io.IOException;

/**
 * Interface defining file operations for saving and loading data.
 */
public interface FileOperations {
    
    /**
     * Saves data to a text file.
     * 
     * @param filename The name of the file to save to
     * @throws IOException If an I/O error occurs
     */
    void saveToTextFile(String filename) throws IOException;
    
    /**
     * Loads data from a text file.
     * 
     * @param filename The name of the file to load from
     * @throws IOException If an I/O error occurs
     */
    void loadFromTextFile(String filename) throws IOException;
    
    /**
     * Saves data to a binary file.
     * 
     * @param filename The name of the file to save to
     * @throws IOException If an I/O error occurs
     */
    void saveToBinaryFile(String filename) throws IOException;
    
    /**
     * Loads data from a binary file.
     * 
     * @param filename The name of the file to load from
     * @throws IOException If an I/O error occurs
     * @throws ClassNotFoundException If the class of a serialized object cannot be found
     */
    void loadFromBinaryFile(String filename) throws IOException, ClassNotFoundException;
}
