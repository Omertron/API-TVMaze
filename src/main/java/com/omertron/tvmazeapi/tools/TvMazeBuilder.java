/*
 *      Copyright (c) 2015-2016 Stuart Boston
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

import com.omertron.tvmazeapi.TvMazeException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yamj.api.common.exception.ApiExceptionType;

/**
 * Create a build object to build the request
 *
 * @author Stuart.Boston
 */
public final class TvMazeBuilder {

    private static final Logger LOG = LoggerFactory.getLogger(TvMazeBuilder.class);
    private final TvMazeParameters params = new TvMazeParameters();
    // Default values (if required)
    private static final boolean DEFAULT_TOMATOES = Boolean.FALSE;

    /**
     * Construct the object
     *
     */
    public TvMazeBuilder() {
    }

    /**
     * Set the search term
     *
     * @param searchTerm The text to build for
     * @return
     * @throws com.omertron.tvmazeapi.TvMazeException
     */
    public TvMazeBuilder setSearchTerm(final String searchTerm) throws TvMazeException {
        if (StringUtils.isBlank(searchTerm)) {
            throw new TvMazeException(ApiExceptionType.UNKNOWN_CAUSE, "Must provide a search term!");
        }
        params.add(Param.SEARCH, searchTerm);
        return this;
    }

    /**
     * A valid IMDb ID
     *
     * @param imdbId The IMDB ID
     * @return
     * @throws TvMazeException
     */
    public TvMazeBuilder setImdbId(final String imdbId) throws TvMazeException {
        if (StringUtils.isBlank(imdbId)) {
            throw new TvMazeException(ApiExceptionType.UNKNOWN_CAUSE, "Must provide an IMDB ID!");
        }
        params.add(Param.IMDB, imdbId);
        return this;
    }

    /**
     * The title to search for
     *
     * @param title
     * @return
     * @throws TvMazeException
     */
    public TvMazeBuilder setTitle(final String title) throws TvMazeException {
        if (StringUtils.isBlank(title)) {
            throw new TvMazeException(ApiExceptionType.UNKNOWN_CAUSE, "Must provide a title!");
        }
        params.add(Param.TITLE, title);
        return this;
    }

    /**
     * Set the year
     *
     * @param year [OPTIONAL] the year to limit the build to
     * @return
     */
    public TvMazeBuilder setYear(final int year) {
        if (year > 0) {
            params.add(Param.YEAR, year);
        }
        return this;
    }

    /**
     * Include or exclude Rotten Tomatoes ratings.
     *
     * @param tomatoes
     * @return
     */
    public TvMazeBuilder setTomatoes(boolean tomatoes) {
        if (DEFAULT_TOMATOES != tomatoes) {
            params.add(Param.TOMATOES, tomatoes);
        }
        return this;
    }

    /**
     * Include Rotten Tomatoes ratings.
     *
     * @return
     */
    public TvMazeBuilder setTomatoesOn() {
        return setTomatoes(true);
    }

    /**
     * Exclude Rotten Tomatoes ratings.
     *
     * @return
     */
    public TvMazeBuilder setTomatoesOff() {
        return setTomatoes(false);
    }

    /**
     * API version
     *
     * @param version [OPTIONAL] The API version to use
     * @return
     */
    public TvMazeBuilder setVersion(final int version) {
        params.add(Param.VERSION, version);
        return this;
    }

    /**
     * Generate the parameters
     *
     * @return
     */
    public TvMazeParameters build() {
        LOG.trace(toString());
        return params;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(params, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
