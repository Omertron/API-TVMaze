/*
 *      Copyright (c) 2015-2015 Stuart Boston
 *
 *      This file is part of the TV Maze API.
 *
 *      The TV Maze API is free software: you can redistribute it and/or modify
 *      it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation, either version 3 of the License, or
 *      any later version.
 *
 *      The TV Maze API is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU General Public License for more details.
 *
 *      You should have received a copy of the GNU General Public License
 *      along with The TV Maze API.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.omertron.tvmazeapi.tools;

import java.util.EnumMap;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Parameters for the OMDB API
 *
 * @author Stuart
 */
public class TvMazeParameters {

    private final Map<Param, Object> parameters = new EnumMap<>(Param.class);
    // Defaults for parameters
    private final Map<Param, Object> defaults = new EnumMap<>(Param.class);

    /**
     * Construct an empty set of parameters
     */
    public TvMazeParameters() {
    }

    /**
     * Add a parameter to the collection
     *
     * @param key Parameter to add
     * @param value The value to use
     */
    public void add(Param key, Object value) {
        // check to see if we have a default value and skip it
        if (defaults.containsKey(key) && defaults.get(key).equals(value)) {
            // We already have this value, so skip it
            return;
        }
        parameters.put(key, value);
    }

    /**
     * Check to see if the collection has a certain parameter
     *
     * @param key The Parameter to check
     * @return
     */
    public boolean has(Param key) {
        return parameters.containsKey(key);
    }

    /**
     * Get a parameter from the collection
     *
     * @param key The parameter to get
     * @return
     */
    public Object get(Param key) {
        return parameters.get(key);
    }

    /**
     * Remove a parameter from the collection
     *
     * @param key
     */
    public void remove(Param key) {
        parameters.remove(key);
    }

    /**
     * Check to see if the collection has no items
     *
     * @return
     */
    public boolean isEmpty() {
        return parameters.isEmpty();
    }

    /**
     * Check to see if the collection has items
     *
     * @return
     */
    public boolean isNotEmpty() {
        return !isEmpty();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(parameters, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
