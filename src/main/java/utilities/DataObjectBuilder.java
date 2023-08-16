package utilities;

import com.google.gson.Gson;

import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

public class DataObjectBuilder {
    public static <T> T buildDataObject(String absoluteFilePath, Class<T> datatype) {
        T returnData = null;
        try (
                Reader reader = Files.newBufferedReader(Paths.get(absoluteFilePath));
        ) {
            Gson gson = new Gson();
            returnData = gson.fromJson(reader, datatype);
        } catch (NoSuchFileException nsfe) {
            throw new RuntimeException("[ERR] Could not locate the file " + absoluteFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (returnData == null) {
            throw new RuntimeException("[ERR] returned data is null");
        }
        return returnData;
    }

    public static <T> void buildJsonFile(String absoluteFilePath, Object dataType) {

        try (
                Writer writer = Files.newBufferedWriter(Paths.get(absoluteFilePath));
        ) {
            Gson gson = new Gson();
            String jsonStr = gson.toJson(dataType);
            writer.append(jsonStr);
            writer.flush();
            System.out.println(jsonStr);
        } catch (NoSuchFileException nsfe) {
            throw new RuntimeException("[ERR] Could not locate the file " + absoluteFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
