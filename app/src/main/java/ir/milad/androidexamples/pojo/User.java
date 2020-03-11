package ir.milad.androidexamples.pojo;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("name")
    public String name;
    @SerializedName("job")
    public String job;
    @SerializedName("id")
    public Integer id;
    @SerializedName("createdAt")
    public String createdAt;

    public User(String name,String job){
        this.name = name;
        this.job = job;
    }

}
