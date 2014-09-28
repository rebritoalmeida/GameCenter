package com.game.metacritic.gamecenter.app.data.models;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by ruialmeida on 9/29/14.
 */
public class InternalStorage {

    String FILENAME = "hello_file";
    String string = "hello world!";

    public InternalStorage() {
    }

    public static void save(String fileName, ArrayList<Game> games, Context ctx){
        FileOutputStream fos;
        try{
            fos = ctx.openFileOutput(fileName, Context.MODE_PRIVATE);

            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(games);
            oos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
