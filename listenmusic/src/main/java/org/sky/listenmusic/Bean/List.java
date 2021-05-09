package org.sky.listenmusic.Bean;

public class List {


    private int ListId;
    private String ListName;
    private String ListCreator;
    private String Share;
    private String Tag1;
    private String Tag2;

    public int getListId() {
        return ListId;
    }

    public void setListId(int listId) {
        ListId = listId;
    }

    public String getListName() {
        return ListName;
    }

    public void setListName(String listName) {
        ListName = listName;
    }

    public String getListCreator() {
        return ListCreator;
    }

    public void setListCreator(String listCreator) {
        ListCreator = listCreator;
    }

    public String getShare() {
        return Share;
    }

    public void setShare(String share) {
        Share = share;
    }

    public String getTag1() {
        return Tag1;
    }

    public void setTag1(String tag1) {
        Tag1 = tag1;
    }

    public String getTag2() {
        return Tag2;
    }

    public void setTag2(String tag2) {
        Tag2 = tag2;
    }

    public List(int listId, String listName, String listCreator, String share, String tag1, String tag2) {
        ListId = listId;
        ListName = listName;
        ListCreator = listCreator;
        Share = share;
        Tag1 = tag1;
        Tag2 = tag2;
    }


}
