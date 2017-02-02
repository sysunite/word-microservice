package com.sysunite.microservice.word.model;

/**
 * Created by char on 01/02/17.
 */
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.UUID;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.imageio.ImageIO;

public class Image {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("data")
    @Expose
    private List<Integer> data = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }

    public int saveTofile() throws IOException {

        File temp = File.createTempFile(UUID.randomUUID().toString(),".png");





        Object[] arrImg =  this.data.toArray();

//        ByteBuffer byteBuffer = ByteBuffer


//        BufferedImage bi = new BufferedImage(50, 50, BufferedImage.TYPE_USHORT_555_RGB);

//        bi.setData();

//        ImageIO.write(arrImg, "png", temp);

//
//        ImageIO.write((RenderedImage) this.data, "png", temp);

//        Integer[] da = (Integer[]) this.data.toArray();
//
//        FileOutputStream fos = new FileOutputStream("joder.png");
//        try {
//            fos.write((Array) this.data.toArray());
//        } finally {
//            fos.close();
//        }

        return 0;
    }

    @Override
    public String toString() {
        return "Image{" +
                "type='" + type + '\'' +
                ", data=" + data +
                '}';
    }
}
