import java.io.Serializable;

/**
 * Created by tylers on 10/7/14.
 */
public class Simple implements Serializable {

    private String test;
    private String test2;

    public Simple() {

    }

    public String getTest2() {
        return test2;
    }

    public void setTest2(String test2) {
        this.test2 = test2;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }


}
