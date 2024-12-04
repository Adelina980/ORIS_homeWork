package org.example.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Utils {

    final static Logger logger = LogManager.getLogger(Utils.class);

    public static String upper(String src) {
        logger.info(src);
        return src != null ? src.toUpperCase() : null;
    }

}
