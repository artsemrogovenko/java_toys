import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FilesOp extends Machine{
    // построчное считывание файла
    public static void getBucket(String pathname) {
        try {
            File file = new File(pathname);
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            String line = reader.readLine();
            while (line != null) {
                add_toy(line.replaceAll(";"," ").split(" "));  
                line = reader.readLine();
            }
            Sorting.start();
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Не удается найти указанный файл, был автоматически создан");
            exportFile(pathname, "example 5 20");  
            getBucket(pathname);        
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


public static void exportFile(String fileName, String data){    
    try(FileWriter writer = new FileWriter(fileName,true))
    {
       // запись всей строки
        writer.write(data+"\n");
        writer.flush();
    }
    catch(IOException ex){
        System.out.println(ex.getMessage());
    } 
}


}
