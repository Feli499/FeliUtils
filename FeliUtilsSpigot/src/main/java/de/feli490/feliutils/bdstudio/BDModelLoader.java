package de.feli490.feliutils.bdstudio;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import de.feli490.feliutils.bdstudio.components.BDBlockDisplay;
import de.feli490.feliutils.bdstudio.components.BDCollection;
import de.feli490.feliutils.bdstudio.components.BDItemDisplay;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;
import org.joml.Matrix4f;

public class BDModelLoader {

    private interface BDComponentConstructor<T extends BDComponent> {
        T construct(String name, String nbt, Matrix4f transform);
    }

    private final JavaPlugin plugin;

    private final NamespacedKey modelEntitiesKey;
    private final NamespacedKey modelRotationKey;
    private final NamespacedKey modelNameKey;

    public BDModelLoader(JavaPlugin plugin) {
        this.plugin = plugin;

        this.modelEntitiesKey = new NamespacedKey(plugin, "model_entities");
        this.modelRotationKey = new NamespacedKey(plugin, "model_rotation");
        this.modelNameKey = new NamespacedKey(plugin, "model_name");
    }

    public Logger getLogger() {
        return this.plugin.getLogger();
    }

    public NamespacedKey getModelEntitiesKey() {
        return this.modelEntitiesKey;
    }

    public NamespacedKey getModelNameKey() {
        return this.modelNameKey;
    }

    public NamespacedKey getModelRotationKey() {
        return this.modelRotationKey;
    }

    public static String decompress(String compressedString) throws IOException {
        byte[] compressedBytes = Base64.getDecoder().decode(compressedString);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(compressedBytes);
        InputStream gzipInputStream = new GZIPInputStream(byteArrayInputStream);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int len;
        while ((len = gzipInputStream.read(buffer)) > 0) {
            byteArrayOutputStream.write(buffer, 0, len);
        }

        return byteArrayOutputStream.toString(StandardCharsets.UTF_8);
    }

    public static List<BDComponent> loadFromGZIP(String encodedGzippedData) throws IOException {
        String jsonData = decompress(encodedGzippedData);
        Type listType = new TypeToken<List<Map<String, Object>>>() {}.getType();
        Gson gson = new Gson();

        List<Map<String, Object>> data = gson.fromJson(jsonData, listType);

        List<BDComponent> components = new ArrayList<>();
        for (Map<String, Object> item : data) {
            components.add(loadComponent(item));
        }

        return components;
    }

    public static BDModel loadFromReader(Reader tmpReader, String modelName) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(tmpReader)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line);
                }
            }

        List<BDComponent> components = loadFromGZIP(content.toString());
        if (components.size() == 1) {
            modelName = components.get(0).name();
        }
        return new BDModel(modelName, components);
    }

    public static BDModel loadFromFile(File file) throws IOException {
        try (Reader fReader = new FileReader(file)) {
            return loadFromReader(fReader, file.getName());
        }
    }

    public static BDModel loadFromInputStream(InputStream inputStream) throws IOException {
        return loadFromInputStream(inputStream, "");
    }

    public static BDModel loadFromInputStream(InputStream inputStream, String modelName) throws IOException {
        try (Reader streamReader = new InputStreamReader(inputStream)) {
            return loadFromReader(streamReader, modelName);
        }
    }

    private static BDComponent loadComponent(Map<String, Object> item) {
        if (isCollection(item)) {
            return loadCollection(item);
        }
        if (isBlockDisplay(item)) {
            return loadBlockDisplay(item);
        }
        if (isItemDisplay(item)) {
            return loadItemDisplay(item);
        }
        return null;
    }

    private static <T extends BDComponent> T loadBase(Map<String, Object> item, BDComponentConstructor<T> constructor) {
        String name = (String) item.get("name");
        String nbt = (String) item.get("nbt");

        List<Double> transformValues = (List<Double>) item.get("transforms");
        Matrix4f transformMatrix = new Matrix4f(transformValues.get(0).floatValue(), transformValues.get(1).floatValue(),
                transformValues.get(2).floatValue(), transformValues.get(3).floatValue(), transformValues.get(4).floatValue(),
                transformValues.get(5).floatValue(), transformValues.get(6).floatValue(), transformValues.get(7).floatValue(),
                transformValues.get(8).floatValue(), transformValues.get(9).floatValue(), transformValues.get(10).floatValue(),
                transformValues.get(11).floatValue(), transformValues.get(12).floatValue(), transformValues.get(13).floatValue(),
                transformValues.get(14).floatValue(), transformValues.get(15).floatValue()).transpose(); // row major to column major

        return constructor.construct(name, nbt, transformMatrix);
    }

    private static boolean isCollection(Map<String, Object> item) {
        return item.containsKey("isCollection") && (boolean) item.get("isCollection");
    }

    private static BDCollection loadCollection(Map<String, Object> item) {
        BDCollection collection = loadBase(item, BDCollection::new);
        List<Map<String, Object>> children = (List<Map<String, Object>>) item.get("children");
        for (Map<String, Object> child : children) {
            collection.addChild(loadComponent(child));
        }
        return collection;
    }

    private static boolean isBlockDisplay(Map<String, Object> item) {
        return item.containsKey("isBlockDisplay") && (boolean) item.get("isBlockDisplay");
    }

    private static BDBlockDisplay loadBlockDisplay(Map<String, Object> item) {
        return loadBase(item, BDBlockDisplay::new);
    }

    private static boolean isItemDisplay(Map<String, Object> item) {
        return item.containsKey("isItemDisplay") && (boolean) item.get("isItemDisplay");
    }

    private static BDItemDisplay loadItemDisplay(Map<String, Object> item) {
        return loadBase(item, BDItemDisplay::new);
    }

}
