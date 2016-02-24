package com.omertron.tvmazeapi.tools;

import java.util.EnumSet;
import org.apache.commons.lang3.StringUtils;

/**
 * Parameters for use in the URL
 *
 * @author Stuart
 */
public enum Param {

    COUNTRY("country="),
    DATE("date="),
    EMBED("embed="),
    NUMBER("number="),
    PAGE("page="),
    QUERY("q="),
    SEASON("season="),
    THETVDB("thetvdb="),
    TVRAGE("tvrage=");
    
    private final String value;

    private Param(String value) {
        this.value = value;
    }

    /**
     * Get the URL parameter to use
     *
     * @return
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Convert a string into an Enum type
     *
     * @param value
     * @return
     */
    public static Param fromString(String value) {
        if (StringUtils.isNotBlank(value)) {
            for (final Param param : EnumSet.allOf(Param.class)) {
                if (value.equalsIgnoreCase(param.value)) {
                    return param;
                }
            }
        }

        // We've not found the type!
        throw new IllegalArgumentException("Value '" + value + "' not recognised");
    }

}
