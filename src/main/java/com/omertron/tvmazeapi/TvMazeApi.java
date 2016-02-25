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
package com.omertron.tvmazeapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yamj.api.common.exception.ApiExceptionType;
import org.yamj.api.common.http.DigestedResponse;
import org.yamj.api.common.http.DigestedResponseReader;
import org.yamj.api.common.http.SimpleHttpClientBuilder;

public class TvMazeApi {

    private static final Logger LOG = LoggerFactory.getLogger(TvMazeApi.class);
    private final HttpClient httpClient;
    // Jackson JSON configuration
    private static ObjectMapper mapper = new ObjectMapper();
    // HTTP Status codes
    private static final int HTTP_STATUS_300 = 300;
    private static final int HTTP_STATUS_500 = 500;
    private static final String DEFAULT_CHARSET = "UTF-8";
    private final Charset charset;

    /* Search
     /search/shows?q=:query
     /singlesearch/shows?q=:query
     /lookup/shows?tvrage=:id or /lookup/shows?thetvdb=:id
     /search/people?q=:query
     */
    /* Schedule
     /schedule?country=:countrycode&date=:date
     /schedule/full
     */
    /* Shows
     /shows/:id
     /shows/:id/episodes
     /shows/:id/episodebynumber?season=:season&number=:number
     /shows/:id/episodesbydate?date=:date
     /shows/:id/cast
     /shows/:id/akas
     /shows?page=:num
     */
    /* People
     /people/:id
     /people/:id/castcredits
     /people/:id/crewcredits
     */
    /* Updates
     /updates/shows
     */
    /**
     * Create an instance of the API with a default HTTP Client
     *
     * @throws com.omertron.tvmazeapi.TvMazeException
     */
    public TvMazeApi() throws TvMazeException {
        this(new SimpleHttpClientBuilder().build());
    }

    /**
     * Create an instance of the API with the provided HTTP Client
     *
     * @param httpClient
     * @throws com.omertron.tvmazeapi.TvMazeException
     */
    public TvMazeApi(HttpClient httpClient) throws TvMazeException {
        this.httpClient = httpClient;
        this.charset = Charset.forName(DEFAULT_CHARSET);
    }

    public void searchShows(String query) {
//     /search/shows?q=:query
    }

    public void searchSingle(String query, String embed) {
//     /singlesearch/shows?q=:query
    }

    public void lookup(String id, boolean isTvdb) {
//     /lookup/shows?tvrage=:id or /lookup/shows?thetvdb=:id
    }

    public void searchPeople(String query) {
//     /search/people?q=:query
    }

    /**
     * Get the API information
     *
     * @param url
     * @return
     * @throws TvMazeException
     */
    private String requestWebPage(URL url) throws TvMazeException {
        LOG.trace("Requesting: {}", url.toString());
        try {
            final HttpGet httpGet = new HttpGet(url.toURI());
            httpGet.addHeader("accept", "application/json");

            final DigestedResponse response = DigestedResponseReader.requestContent(httpClient, httpGet, charset);

            if (response.getStatusCode() >= HTTP_STATUS_500) {
                throw new TvMazeException(ApiExceptionType.HTTP_503_ERROR, response.getContent(), response.getStatusCode(), url);
            } else if (response.getStatusCode() >= HTTP_STATUS_300) {
                throw new TvMazeException(ApiExceptionType.HTTP_404_ERROR, response.getContent(), response.getStatusCode(), url);
            }

            return response.getContent();
        } catch (URISyntaxException ex) {
            throw new TvMazeException(ApiExceptionType.INVALID_URL, "Invalid URL", url, ex);
        } catch (IOException ex) {
            throw new TvMazeException(ApiExceptionType.CONNECTION_ERROR, "Error retrieving URL", url, ex);
        }
    }
}
