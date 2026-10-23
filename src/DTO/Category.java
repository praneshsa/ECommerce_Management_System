package DTO;

public class Category {

    private int catogory_id;
    private String catogory_name;


    public Category() {
        super();
    }


    public Category(int catogory_id, String catogory_name) {
        super();
        this.catogory_id = catogory_id;
        this.catogory_name = catogory_name;
    }


    public int getCatogory_id() {
        return catogory_id;
    }


    public void setCatogory_id(int catogory_id) {
        this.catogory_id = catogory_id;
    }


    public String getCatogory_name() {
        return catogory_name;
    }


    public void setCatogory_name(String catogory_name) {
        this.catogory_name = catogory_name;
    }



}
