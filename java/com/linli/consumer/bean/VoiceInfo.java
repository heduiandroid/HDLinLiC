package com.linli.consumer.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hasee on 2017/1/5.
 */

public class VoiceInfo {

    /**
     * streams : [{"index":0,"codec_name":"amrnb","codec_long_name":"AMR-NB (Adaptive Multi-Rate NarrowBand)","codec_type":"audio","codec_time_base":"1/8000","codec_tag_string":"samr","codec_tag":"0x726d6173","sample_fmt":"flt","sample_rate":"8000","channels":1,"channel_layout":"mono","bits_per_sample":0,"r_frame_rate":"0/0","avg_frame_rate":"0/0","time_base":"1/8000","duration_ts":39360,"duration":"4.920000","bit_rate":"12800","disposition":{"default":0,"dub":0,"original":0,"comment":0,"lyrics":0,"karaoke":0,"forced":0,"hearing_impaired":0,"visual_impaired":0,"clean_effects":0,"attached_pic":0}}]
     * format : {"nb_streams":1,"nb_programs":0,"format_name":"amr","format_long_name":"3GPP AMR","duration":"4.920000","size":"7878","bit_rate":"12809","probe_score":100}
     */

    private FormatBean format;
    private List<StreamsBean> streams;

    public FormatBean getFormat() {
        return format;
    }

    public void setFormat(FormatBean format) {
        this.format = format;
    }

    public List<StreamsBean> getStreams() {
        return streams;
    }

    public void setStreams(List<StreamsBean> streams) {
        this.streams = streams;
    }

    public static class FormatBean {
        /**
         * nb_streams : 1
         * nb_programs : 0
         * format_name : amr
         * format_long_name : 3GPP AMR
         * duration : 4.920000
         * size : 7878
         * bit_rate : 12809
         * probe_score : 100
         */

        private int nb_streams;
        private int nb_programs;
        private String format_name;
        private String format_long_name;
        private String duration;
        private String size;
        private String bit_rate;
        private int probe_score;

        public int getNb_streams() {
            return nb_streams;
        }

        public void setNb_streams(int nb_streams) {
            this.nb_streams = nb_streams;
        }

        public int getNb_programs() {
            return nb_programs;
        }

        public void setNb_programs(int nb_programs) {
            this.nb_programs = nb_programs;
        }

        public String getFormat_name() {
            return format_name;
        }

        public void setFormat_name(String format_name) {
            this.format_name = format_name;
        }

        public String getFormat_long_name() {
            return format_long_name;
        }

        public void setFormat_long_name(String format_long_name) {
            this.format_long_name = format_long_name;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getBit_rate() {
            return bit_rate;
        }

        public void setBit_rate(String bit_rate) {
            this.bit_rate = bit_rate;
        }

        public int getProbe_score() {
            return probe_score;
        }

        public void setProbe_score(int probe_score) {
            this.probe_score = probe_score;
        }
    }

    public static class StreamsBean {
        /**
         * index : 0
         * codec_name : amrnb
         * codec_long_name : AMR-NB (Adaptive Multi-Rate NarrowBand)
         * codec_type : audio
         * codec_time_base : 1/8000
         * codec_tag_string : samr
         * codec_tag : 0x726d6173
         * sample_fmt : flt
         * sample_rate : 8000
         * channels : 1
         * channel_layout : mono
         * bits_per_sample : 0
         * r_frame_rate : 0/0
         * avg_frame_rate : 0/0
         * time_base : 1/8000
         * duration_ts : 39360
         * duration : 4.920000
         * bit_rate : 12800
         * disposition : {"default":0,"dub":0,"original":0,"comment":0,"lyrics":0,"karaoke":0,"forced":0,"hearing_impaired":0,"visual_impaired":0,"clean_effects":0,"attached_pic":0}
         */

        private int index;
        private String codec_name;
        private String codec_long_name;
        private String codec_type;
        private String codec_time_base;
        private String codec_tag_string;
        private String codec_tag;
        private String sample_fmt;
        private String sample_rate;
        private int channels;
        private String channel_layout;
        private int bits_per_sample;
        private String r_frame_rate;
        private String avg_frame_rate;
        private String time_base;
        private int duration_ts;
        private String duration;
        private String bit_rate;
        private DispositionBean disposition;

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getCodec_name() {
            return codec_name;
        }

        public void setCodec_name(String codec_name) {
            this.codec_name = codec_name;
        }

        public String getCodec_long_name() {
            return codec_long_name;
        }

        public void setCodec_long_name(String codec_long_name) {
            this.codec_long_name = codec_long_name;
        }

        public String getCodec_type() {
            return codec_type;
        }

        public void setCodec_type(String codec_type) {
            this.codec_type = codec_type;
        }

        public String getCodec_time_base() {
            return codec_time_base;
        }

        public void setCodec_time_base(String codec_time_base) {
            this.codec_time_base = codec_time_base;
        }

        public String getCodec_tag_string() {
            return codec_tag_string;
        }

        public void setCodec_tag_string(String codec_tag_string) {
            this.codec_tag_string = codec_tag_string;
        }

        public String getCodec_tag() {
            return codec_tag;
        }

        public void setCodec_tag(String codec_tag) {
            this.codec_tag = codec_tag;
        }

        public String getSample_fmt() {
            return sample_fmt;
        }

        public void setSample_fmt(String sample_fmt) {
            this.sample_fmt = sample_fmt;
        }

        public String getSample_rate() {
            return sample_rate;
        }

        public void setSample_rate(String sample_rate) {
            this.sample_rate = sample_rate;
        }

        public int getChannels() {
            return channels;
        }

        public void setChannels(int channels) {
            this.channels = channels;
        }

        public String getChannel_layout() {
            return channel_layout;
        }

        public void setChannel_layout(String channel_layout) {
            this.channel_layout = channel_layout;
        }

        public int getBits_per_sample() {
            return bits_per_sample;
        }

        public void setBits_per_sample(int bits_per_sample) {
            this.bits_per_sample = bits_per_sample;
        }

        public String getR_frame_rate() {
            return r_frame_rate;
        }

        public void setR_frame_rate(String r_frame_rate) {
            this.r_frame_rate = r_frame_rate;
        }

        public String getAvg_frame_rate() {
            return avg_frame_rate;
        }

        public void setAvg_frame_rate(String avg_frame_rate) {
            this.avg_frame_rate = avg_frame_rate;
        }

        public String getTime_base() {
            return time_base;
        }

        public void setTime_base(String time_base) {
            this.time_base = time_base;
        }

        public int getDuration_ts() {
            return duration_ts;
        }

        public void setDuration_ts(int duration_ts) {
            this.duration_ts = duration_ts;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getBit_rate() {
            return bit_rate;
        }

        public void setBit_rate(String bit_rate) {
            this.bit_rate = bit_rate;
        }

        public DispositionBean getDisposition() {
            return disposition;
        }

        public void setDisposition(DispositionBean disposition) {
            this.disposition = disposition;
        }

        public static class DispositionBean {
            /**
             * default : 0
             * dub : 0
             * original : 0
             * comment : 0
             * lyrics : 0
             * karaoke : 0
             * forced : 0
             * hearing_impaired : 0
             * visual_impaired : 0
             * clean_effects : 0
             * attached_pic : 0
             */

            @SerializedName("default")
            private int defaultX;
            private int dub;
            private int original;
            private int comment;
            private int lyrics;
            private int karaoke;
            private int forced;
            private int hearing_impaired;
            private int visual_impaired;
            private int clean_effects;
            private int attached_pic;

            public int getDefaultX() {
                return defaultX;
            }

            public void setDefaultX(int defaultX) {
                this.defaultX = defaultX;
            }

            public int getDub() {
                return dub;
            }

            public void setDub(int dub) {
                this.dub = dub;
            }

            public int getOriginal() {
                return original;
            }

            public void setOriginal(int original) {
                this.original = original;
            }

            public int getComment() {
                return comment;
            }

            public void setComment(int comment) {
                this.comment = comment;
            }

            public int getLyrics() {
                return lyrics;
            }

            public void setLyrics(int lyrics) {
                this.lyrics = lyrics;
            }

            public int getKaraoke() {
                return karaoke;
            }

            public void setKaraoke(int karaoke) {
                this.karaoke = karaoke;
            }

            public int getForced() {
                return forced;
            }

            public void setForced(int forced) {
                this.forced = forced;
            }

            public int getHearing_impaired() {
                return hearing_impaired;
            }

            public void setHearing_impaired(int hearing_impaired) {
                this.hearing_impaired = hearing_impaired;
            }

            public int getVisual_impaired() {
                return visual_impaired;
            }

            public void setVisual_impaired(int visual_impaired) {
                this.visual_impaired = visual_impaired;
            }

            public int getClean_effects() {
                return clean_effects;
            }

            public void setClean_effects(int clean_effects) {
                this.clean_effects = clean_effects;
            }

            public int getAttached_pic() {
                return attached_pic;
            }

            public void setAttached_pic(int attached_pic) {
                this.attached_pic = attached_pic;
            }
        }
    }
}
