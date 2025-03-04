package me.josielcm.jcm.files;

import lombok.Getter;
import me.josielcm.jcm.JosielPL;
import me.josielcm.jcm.api.utils.config.FileConfig;

public class FileManager {

    final JosielPL plugin;

    public FileManager(JosielPL plugin) {
        this.plugin = plugin;
    }
    
    @Getter
    FileConfig config;

    
    public void loadFiles() {
        config = new FileConfig(plugin, "config.yml");
    }

}
