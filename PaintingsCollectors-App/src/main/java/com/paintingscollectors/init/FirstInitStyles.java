package com.paintingscollectors.init;

import com.paintingscollectors.service.StyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FirstInitStyles implements CommandLineRunner {

    private final StyleService styleService;

    @Autowired
    public FirstInitStyles(StyleService styleService) {
        this.styleService = styleService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.styleService.initStyles();
    }
}
