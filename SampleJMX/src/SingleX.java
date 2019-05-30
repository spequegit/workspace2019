public class SingleX {
    private static SingleX ourInstance = new SingleX();

    public static SingleX getInstance() {
        return ourInstance;
    }

    private SingleX() {
    }
}
