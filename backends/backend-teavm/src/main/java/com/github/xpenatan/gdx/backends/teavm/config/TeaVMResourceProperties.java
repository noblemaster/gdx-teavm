package com.github.xpenatan.gdx.backends.teavm.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class TeaVMResourceProperties {
    private static final String OPTION_ADDITIONAL_RESOURCES = "resources";

    public String path;

    public ArrayList<String> additionalPath = new ArrayList<>();

    public TeaVMResourceProperties(String path, String content) {
        this.path = path;

        Scanner scanner = new Scanner(content);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            String[] split = line.split("=");
            if(split.length == 2) {
                String option = split[0].trim();
                String value = split[1].trim();
                if(!option.isEmpty() && !value.isEmpty()) {
                    setupOption(option, value);
                }
            }
        }
        scanner.close();
    }

    private void setupOption(String option, String value) {
        if(option.equals(OPTION_ADDITIONAL_RESOURCES)) {
            additionalPath.add(value);
        }
    }
    public static List<String> getResources(ArrayList<URL> acceptedURL) {
        // Get all resources
        ArrayList<TeaVMResourceProperties> propertiesList = getAllProperties(acceptedURL);
        ArrayList<URI> filteredUrl = new ArrayList<>();
        for(URL url : acceptedURL) {
            String path = url.getPath();
            boolean accept = false;
            for(TeaVMResourceProperties properties : propertiesList) {
                if(path.contains(properties.path)) {
                    accept = true;
                    break;
                }
                if(!accept) {
                    for(String additionalPath : properties.additionalPath) {
                        if(path.contains(additionalPath)) {
                            accept = true;
                            break;
                        }
                    }
                    if(accept) {
                        break;
                    }
                }
            }
            if(accept) {
                URI uri = URI.create("jar:file:" + path);
                filteredUrl.add(uri);
            }
        }

        ArrayList<String> result = new ArrayList<>();
        for(URI uri : filteredUrl) {
            ArrayList<String> pathsFromResource = getPathsFromResource(uri, ".");
            result.addAll(pathsFromResource);
        }
        return result;
    }

    private static ArrayList<TeaVMResourceProperties> getAllProperties(ArrayList<URL> acceptedURL) {
        ArrayList<String> filteredUrl = new ArrayList<>();
        for(URL url : acceptedURL) {
            String path = url.getPath();
            boolean accept = !(
                    !(path.endsWith(".jar")) ||
                            path.contains("org.teavm"
                            ));
            if(accept) {
                filteredUrl.add(path);
            }
        }
        ArrayList<TeaVMResourceProperties> result = new ArrayList<>();
        for(String path : filteredUrl) {
            TeaVMResourceProperties properties = getProperties(path);
            if(properties != null) {
                result.add(properties);
            }
        }
        return result;
    }

    private static TeaVMResourceProperties getProperties(String path) {
        try {
            try(ZipFile zipFile = new ZipFile(path)) {
                ZipEntry propertyEntry = zipFile.getEntry("META-INF/gdx-teavm.properties");
                if(propertyEntry != null) {
                    InputStream inputStream = zipFile.getInputStream(propertyEntry);
                    String content = readString(inputStream, null);
                    inputStream.close();
                    return new TeaVMResourceProperties(path, content);
                }
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static String readString(InputStream in, String charset) {
        StringBuilder output = new StringBuilder(512);
        InputStreamReader reader = null;
        try {
            if(charset == null)
                reader = new InputStreamReader(in);
            else
                reader = new InputStreamReader(in, charset);
            char[] buffer = new char[256];
            while(true) {
                int length = reader.read(buffer);
                if(length == -1) break;
                output.append(buffer, 0, length);
            }
        } catch(IOException ex) {
            throw new RuntimeException("Error reading resource zip file", ex);
        } finally {
            try {
                if(reader != null) reader.close();
            } catch(IOException ignored) {
            }
        }
        return output.toString();
    }

    private static ArrayList<String> getPathsFromResource(URI uri, String folder) {
        ArrayList<String> result = new ArrayList<>();
        List<Path> resultPath = null;

        try(FileSystem fs = FileSystems.newFileSystem(uri, Collections.emptyMap())) {
            resultPath = Files.walk(fs.getPath(folder))
                    .filter(Files::isRegularFile)
                    .filter(new Predicate<Path>() {
                        @Override
                        public boolean test(Path path) {
                            String pathStr = path.toString();
                            boolean isValid = !(
                                    pathStr.endsWith(".java") ||
                                            pathStr.endsWith(".class") ||
                                            pathStr.contains("META-INF") ||
                                            pathStr.contains("WEB-INF") ||
                                            pathStr.endsWith(".html") ||
                                            pathStr.endsWith(".gwt.xml") ||
                                            pathStr.endsWith(".rl")
                            );
                            return isValid;
                        }
                    })
                    .collect(Collectors.toList());
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        if(resultPath != null) {
            for(Path path : resultPath) {
                result.add(path.toString());
            }
        }
        return result;
    }
}