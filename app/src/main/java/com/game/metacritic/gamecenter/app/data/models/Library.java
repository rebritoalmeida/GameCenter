package com.game.metacritic.gamecenter.app.data.models;

import java.security.acl.Group;
import java.util.ArrayList;

public class Library {

    private ArrayList<Game> arrayGameItem;
    public static final int AUTHOR = 1;
    public static final int TITLE = 2;
    public static final int RATE = 3;
    public static final int DOWNLOAD_DATE = 4;

    public Library() {
        arrayGameItem = new ArrayList<Game>();
    }

    public void setColectionBookItem(ArrayList<Game> _array) {
        this.arrayGameItem = _array;
    }

    public void addGameItem(Game _bi) {
        this.arrayGameItem.add(_bi);
    }

    public ArrayList<ArrayList<Game>> groupbyArrayBookItem(int type) {

        //GameResponse[] books = GameResponse.ALL_BOOKS;
        ArrayList<ArrayList<Game>> groupList = new ArrayList<ArrayList<Game>>();
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

        /*
        Game game = new Game("MATIO","3");
        ArrayList<Game> games = new ArrayList<Game>();
        games.add(game);
        games.add(game);
        Game gameResponse = new Game(3,4,games);
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
*/
        /*
        for(int i = 0; i < arrayGameItem.size(); i++) {
            if(i%5 == 0) {
                groupList.add(arrayGameItem.get(i));
            }
        }*/

        groupList.add(arrayGameItem);

        /*groupList.add(gameResponses);
        groupList.add(gameResponses);
        groupList.add(gameResponses);
        groupList.add(gameResponses);
        groupList.add(gameResponses);
        groupList.add(gameResponses);
        groupList.add(gameResponses);
        groupList.add(gameResponses);
        groupList.add(gameResponses);*/

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