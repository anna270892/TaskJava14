public class SimpleTask extends Task {
    protected String title;//название

    public SimpleTask(int id, String title) {
        super(id); // вызов родительского конструктора
        this.title = title; // заполнение своих полей
    }

    @Override
    public boolean matches(String query) {
        if (title.contains(query)) {
            return true;
        } else {
            return false;
        }
    }

    public String getTitle() {
        return title;
    }
}
