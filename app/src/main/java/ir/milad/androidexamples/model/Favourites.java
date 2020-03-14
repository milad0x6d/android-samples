package ir.milad.androidexamples.model;

public class Favourites {

    public long mId;
    public String mUrl;
    public long mDate;

    public Favourites(long mId, String mUrl, long mDate) {
        this.mId = mId;
        this.mUrl = mUrl;
        this.mDate = mDate;
    }

    public Favourites(Favourites favourites){
        mId = favourites.mId;
        mUrl = favourites.mUrl;
        mDate = favourites.mDate;
    }
}
