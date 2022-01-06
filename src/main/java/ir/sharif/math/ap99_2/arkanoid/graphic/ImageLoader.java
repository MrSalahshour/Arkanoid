package ir.sharif.math.ap99_2.arkanoid.graphic;

import ir.sharif.math.ap99_2.arkanoid.util.Config;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ImageLoader {
    final static ImageLoader instance = new ImageLoader();
    private final Map<String, BufferedImage> imageMap;

    public static BufferedImage getImage(String name) {
        return instance.imageMap.get(name);
    }

    private ImageLoader() {
        imageMap = new HashMap<>();
        load();
    }

    private void load() {
        Config config = Config.getConfig("images");
        for (Map.Entry<Object, Object> k : config.entrySet()) {
            String key = (String) k.getKey();
            File file = new File((String) k.getValue());
            try {
                BufferedImage image = ImageIO.read(file);
                String name = key.replace('-', ' ');
                imageMap.put(name, image);
            } catch (IOException e) {
                System.err.println(file.toString());
                throw new RuntimeException("image file not exist", e);
            }
        }
    }

}

