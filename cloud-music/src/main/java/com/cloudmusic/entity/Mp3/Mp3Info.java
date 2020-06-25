package com.cloudmusic.entity.mp3;

import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.id3.ID3v23Frame;

import javax.persistence.MappedSuperclass;
import java.io.File;
import java.io.IOException;
import java.sql.Time;

@MappedSuperclass
public class Mp3Info {

    protected String title;//标题
    protected String artist;//艺术家
    protected String album;//专辑
    protected Time duration;//时长

    public Mp3Info(){};

    public Mp3Info (String title, String artist, String album, Time duration){
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
    }

    public Mp3Info (Mp3Info mp3Info){
        this.title = mp3Info.getTitle();
        this.artist = mp3Info.getArtist();
        this.album = mp3Info.getAlbum();
        this.duration = mp3Info.getDuration();
    }

    public Mp3Info getMusicInfo(File file){
        Mp3Info mp3Info = null;

        try {
            //读取文件信息
            MP3File mp3File = (MP3File) AudioFileIO.read(file);
            //获取头
            MP3AudioHeader header = mp3File.getMP3AudioHeader();

            //歌名
            String title;
            try{
                ID3v23Frame titleFrame = (ID3v23Frame) mp3File.getID3v2Tag().frameMap.get("TIT2");
                title = titleFrame.getContent();
            } catch (NullPointerException e){
                //filePath=D:\CloudMusic\song.mp3
                String filePath = file.getPath();
                String[] str1 = filePath.split("\\\\");
                String str2 = str1[str1.length-1];
                String[] str3 = str2.split("\\.");
                title = str3[0];
            }
            //歌手
            String artist;
            try {
                ID3v23Frame artistFrame = (ID3v23Frame) mp3File.getID3v2Tag().frameMap.get("TPE1");
                artist = artistFrame.getContent();
            } catch (NullPointerException e){
                artist = "无";
            }
            //专辑
            String album;
            try {
                ID3v23Frame albumFrame = (ID3v23Frame) mp3File.getID3v2Tag().frameMap.get("TALB");
                album = albumFrame.getContent();
            } catch (NullPointerException e){
                album = "无";
            }
            //时长
            int duration = header.getTrackLength();

            mp3Info = new Mp3Info(title, artist, album, secondToDate(duration));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CannotReadException e) {
            e.printStackTrace();
        } catch (ReadOnlyFileException e) {
            e.printStackTrace();
        } catch (TagException e) {
            e.printStackTrace();
        } catch (InvalidAudioFrameException e) {
            e.printStackTrace();
        } catch (ClassCastException e){
            e.printStackTrace();
        }
        return mp3Info;
    }

    private Time secondToDate(int second){
        //转换为毫秒,但需要减去最基础的8小时
        Time time = new Time(second * 1000 - 8 * 60 * 60 * 1000);
        return time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Mp3Info{" +
                "title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", album='" + album + '\'' +
                ", duration=" + duration +
                '}';
    }
}
