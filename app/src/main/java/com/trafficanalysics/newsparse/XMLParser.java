/*
 *   Copyright 2016 Marco Gomiero
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package com.trafficanalysics.newsparse;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by DucTien on 14/10/2018.
 */

public class XMLParser extends Observable {

    private ArrayList<News> news;
    private News currentNews;

    public XMLParser() {
        news = new ArrayList<>();
        currentNews = new News();
    }

    public void parseXML(String xml) throws XmlPullParserException, IOException {

        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

        factory.setNamespaceAware(false);
        XmlPullParser xmlPullParser = factory.newPullParser();

        xmlPullParser.setInput(new StringReader(xml));
        boolean insideItem = false;
        int eventType = xmlPullParser.getEventType();

        while (eventType != XmlPullParser.END_DOCUMENT) {

            if (eventType == XmlPullParser.START_TAG) {
                if (xmlPullParser.getName().equalsIgnoreCase("item")) {
                    insideItem = true;

                } else if (xmlPullParser.getName().equalsIgnoreCase("title")) {
                    if (insideItem) {
                        String title = xmlPullParser.nextText();
                        currentNews.setTitle(title);
                    }

                } else if (xmlPullParser.getName().equalsIgnoreCase("link")) {
                    if (insideItem) {
                        String link = xmlPullParser.nextText();
                        currentNews.setLink(link);
                    }

                } else if (xmlPullParser.getName().equalsIgnoreCase("dc:creator")) {
                    if (insideItem) {
                        String author = xmlPullParser.nextText();
                        currentNews.setAuthor(author);
                    }

                } else if (xmlPullParser.getName().equalsIgnoreCase("category")) {
                    if (insideItem) {
                        String category = xmlPullParser.nextText();
                        currentNews.addCategory(category);
                    }

                } else if (xmlPullParser.getName().equalsIgnoreCase("media:thumbnail")) {
                    if (insideItem) {
                        String img = xmlPullParser.getAttributeValue(null, "url");
                        currentNews.setImage(img);
                    }

                } else if (xmlPullParser.getName().equalsIgnoreCase("description")) {
                    if (insideItem) {
                        String description = xmlPullParser.nextText();
                        if (currentNews.getImage() == null) {
                            currentNews.setImage(getImageUrl(description));
                        }
                        currentNews.setDescription(description);

                    }

                } else if (xmlPullParser.getName().equalsIgnoreCase("content:encoded")) {
                    if (insideItem) {
                        String content = xmlPullParser.nextText();
                        if (currentNews.getImage() == null) {
                            currentNews.setImage(getImageUrl(content));
                        }
                        currentNews.setContent(content);
                    }

                } else if (xmlPullParser.getName().equalsIgnoreCase("pubDate")) {
                    Date pubDate = new Date(xmlPullParser.nextText());
                    currentNews.setPubDate(pubDate);
                }

            } else if (eventType == XmlPullParser.END_TAG && xmlPullParser.getName().equalsIgnoreCase("item")) {
                insideItem = false;
                news.add(currentNews);
                currentNews = new News();
            }
            eventType = xmlPullParser.next();
        }
        triggerObserver();
    }


    private void triggerObserver() {
        setChanged();
        notifyObservers(news);
    }

    /**
     * Finds the first img tag and get the src as featured image
     *
     * @param input The content in which to search for the tag
     * @return The url, if there is one
     */
    private String getImageUrl(String input) {

        String url = null;
        Pattern patternImg = Pattern.compile("(<img .*?>)");
        Matcher matcherImg = patternImg.matcher(input);
        if (matcherImg.find()) {
            String imgTag = matcherImg.group(1);
            Pattern patternLink = Pattern.compile("src\\s*=\\s*\"(.+?)\"");
            Matcher matcherLink = patternLink.matcher(imgTag);
            if (matcherLink.find()) {
                url = matcherLink.group(1);
            }
        }
        return url;
    }
}