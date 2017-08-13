package com.hvt.hbapplication.util.font;


import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.hvt.hbapplication.util.font.Font.FIRASANS;
import static com.hvt.hbapplication.util.font.Font.FIRASANS_BOLD;
import static com.hvt.hbapplication.util.font.Font.FIRASANS_BOLD_ITALIC;
import static com.hvt.hbapplication.util.font.Font.FIRASANS_ITALIC;
import static com.hvt.hbapplication.util.font.Font.JOSEFINSANS_LIGHT;

@Retention(RetentionPolicy.SOURCE)
@StringDef({
        FIRASANS,
        FIRASANS_BOLD,
        FIRASANS_BOLD_ITALIC,
        FIRASANS_ITALIC,
        JOSEFINSANS_LIGHT
})

public @interface Font {
    String FIRASANS = "proximaNovaRegular.otf";
    String FIRASANS_BOLD = "proximaNovaSemibold.otf";
    String FIRASANS_ITALIC = "JosefinSans-Light.ttf";
    String FIRASANS_BOLD_ITALIC = "FiraSans-BoldItalic.ttf";
    String JOSEFINSANS_LIGHT = "Roboto-Light.ttf";
}
