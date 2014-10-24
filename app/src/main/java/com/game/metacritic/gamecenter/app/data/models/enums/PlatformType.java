package com.game.metacritic.gamecenter.app.data.models.enums;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by ruialmeida on 10/5/14.
 */

    public class PlatformType {



    private static final HashMap<String, Integer> PLATFORM_TYPE = createMap();

    private static HashMap<String, Integer> createMap() {
        HashMap<String, Integer> result = new HashMap<String, Integer>();
        result.put("PS3", 1);
        result.put("X360", 2);
        result.put("PC", 3);
        result.put("DS",4);
        result.put("PlayStation 2",6);
        result.put("PSP",7);
        result.put("WII",8);
        result.put("iPhone/iPad",9);
        result.put("PlayStation",10);
        result.put("GBA",11);
        result.put("Xbox",12);
        result.put("GC",13);
        result.put("N64",14);
        result.put("Dreamcast",15);
        result.put("3DS",16);
        result.put("PlayStation Vita",67365);
        result.put("WIIU", 68410);


        return result;
    }

    public static Integer getPlatformTypeByName(String name) {
       return PLATFORM_TYPE.get(name);
    }

    }
/*
"platforms": {
        "1": "PlayStation 3",
        "2": "Xbox 360",
        "3": "PC",
        "4": "DS",
        "6": "PlayStation 2",
        "7": "PSP",
        "8":" "Wii",
        "9": iPhone/iPad",
        "10": "PlayStation",
        "11": "Game Boy Advance",
        "12": "Xbox",
        "13": "GameCube",
        "14": "Nintendo 64",
        "15": "Dreamcast",
        "16": "3DS",
        "67365": "PlayStation Vita",
        "68410": "Wii U"
        */