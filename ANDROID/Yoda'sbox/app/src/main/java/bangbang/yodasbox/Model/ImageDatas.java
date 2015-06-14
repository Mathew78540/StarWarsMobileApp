package bangbang.yodasbox.Model;


import java.io.Serializable;
import java.util.ArrayList;

public class ImageDatas implements Serializable
{

    private String unescapedUrl;

    public String getUnescapedUrl() {
        return unescapedUrl;
    }

    public void setUnescapedUrl(String unescapedUrl) {
        this.unescapedUrl = unescapedUrl;
    }

    @Override
    public String toString() {
        return "ImageDatas{" +
                "unescapedUrl='" + unescapedUrl + '\'' +
                '}';
    }
}
