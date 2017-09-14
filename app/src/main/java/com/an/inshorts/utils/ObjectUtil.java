package com.an.inshorts.utils;


import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectUtil<T> {

    public void writeObjects(T object, String filename) {
        ObjectOutputStream out = null;

        try {
            out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(object);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {

                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public T readObjects(String fileName) {
        File f = new File(fileName);

        if (f.exists()) {
            ObjectInputStream in = null;

            try {
                in = new ObjectInputStream(new FileInputStream(fileName));
                return (T) in.readObject();
            } catch (Exception e) {
                return null;

            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                    }
                }
            }
        } else {
            return null;
        }
    }
}