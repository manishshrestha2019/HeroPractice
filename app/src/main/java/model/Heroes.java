package model;

public class Heroes {    private String image;

    private String _id;
    private String name;
    private String desc;

    public Heroes( String name, String desc,String image) {

        this.name = name;
        this.desc = desc;
        this.image=image;
    }


    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
