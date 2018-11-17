package com.anriku.cherryplayback.model;


import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;


/**
 * Created by anriku on 2018/11/17.
 */

@Root(name = "lyric")
public class Lyric {

    @Text
    private String lyric;


    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    @Override
    public String toString() {
        return "Lyric{" +
                "lyric='" + lyric + '\'' +
                '}';
    }
}
