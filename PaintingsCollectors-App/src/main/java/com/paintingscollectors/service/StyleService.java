package com.paintingscollectors.service;

import com.paintingscollectors.model.entity.Style;
import com.paintingscollectors.model.enums.StyleType;

public interface StyleService {

    void initStyles();

    Style getStyleByStyleName(StyleType styleName);
}
