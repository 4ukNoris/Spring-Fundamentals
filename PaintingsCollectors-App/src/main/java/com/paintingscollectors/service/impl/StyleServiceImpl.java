package com.paintingscollectors.service.impl;

import com.paintingscollectors.model.entity.Style;
import com.paintingscollectors.model.enums.StyleType;
import com.paintingscollectors.repository.StyleRepository;
import com.paintingscollectors.service.StyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class StyleServiceImpl implements StyleService {
    private final StyleRepository styleRepository;

    @Autowired
    public StyleServiceImpl(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    @Override
    public void initStyles() {
        if (this.styleRepository.count() == 0) {
            List<Style> styleList = Arrays.stream(StyleType.values())
                    .map(Style::new)
                    .toList();
            this.styleRepository.saveAll(styleList);
        }
    }

    @Override
    public Style getStyleByStyleName(StyleType styleName) {
        return this.styleRepository.findByName(styleName);
    }

}
