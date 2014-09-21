package com.game.metacritic.gamecenter.app.data.models;

import com.game.metacritic.gamecenter.app.data.models.GameResponse;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.Iterator;

public class Library {

    private ArrayList<GameResponse> arrayBookItem;
    public static final int AUTHOR = 1;
    public static final int TITLE = 2;
    public static final int RATE = 3;
    public static final int DOWNLOAD_DATE = 4;

    public Library() {
        arrayBookItem = new ArrayList<GameResponse>();
    }

    public void setColectionBookItem(ArrayList<GameResponse> _array) {
        this.arrayBookItem = _array;
    }

    public void addBookItem(GameResponse _bi) {
        this.arrayBookItem.add(_bi);
    }

    public ArrayList<ArrayList<GameResponse>> groupbyArrayBookItem(int type) {

        //GameResponse[] books = GameResponse.ALL_BOOKS;
        ArrayList<ArrayList<GameResponse>> groupList =
                new ArrayList<ArrayList<GameResponse>>();
        String getType = "";

        switch (type) {
            case AUTHOR:
                getType = "bookitem.getAuthor()";
                break;
            case TITLE:
                getType = "bookitem.getTitle()";
                break;
            case DOWNLOAD_DATE:
                getType = "bookitem.getDownloadDate()";
                break;
            case RATE:
                getType = "bookitem.getRate()";
                break;
            default:
                return groupList;
        }
 
		/*
		 * books is a object of BookItem
		 * bookitem is item for point to list
		 * getType is a string value for set type of grouping
		 * groupbyArrayBookItem return back array of array of items
		 */
        Game game = new Game("MATIO","3");
        ArrayList<Game> games = new ArrayList<Game>();
        games.add(game);
        games.add(game);
        GameResponse gameResponse = new GameResponse(3,4,games);
        ArrayList<GameResponse> gameResponses = new ArrayList<GameResponse>();

        gameResponses.add(gameResponse);
        gameResponses.add(gameResponse);
        gameResponses.add(gameResponse);
        gameResponses.add(gameResponse);
        gameResponses.add(gameResponse);
        gameResponses.add(gameResponse);
        gameResponses.add(gameResponse);
        gameResponses.add(gameResponse);
        gameResponses.add(gameResponse);
        gameResponses.add(gameResponse);
        gameResponses.add(gameResponse);


        groupList.add(gameResponses);
        groupList.add(gameResponses);
        groupList.add(gameResponses);
        groupList.add(gameResponses);groupList.add(gameResponses);
        groupList.add(gameResponses);
        groupList.add(gameResponses);
        groupList.add(gameResponses);
        groupList.add(gameResponses);
        groupList.add(gameResponses);

       //Iterable<Group> groups =
         //       from("bookitem").in(books).group("bookitem")
          //              .by(getType).into("g").select("g");
        ArrayList<Group> groups = new ArrayList<Group>();
        for (Group group : groups) {
            ArrayList<Game> obj = new ArrayList<Game>();
            //for (Object Item : group.getGroup()) {
            //    obj.add((BookItem) Item);
            //}
            //groupList.add(obj);
        }
        return groupList;
    }
}