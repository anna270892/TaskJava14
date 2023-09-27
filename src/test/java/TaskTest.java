import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void requestSimpleTask() {
        SimpleTask simpleTask = new SimpleTask(1, "Тестировщик");

        Todos todos = new Todos();

        todos.add(simpleTask);

        Task[] expected = {simpleTask};
        Task[] actual = todos.search("Тестировщик");
        Assertions.assertArrayEquals(expected, actual);
    }


    @Test
    public void requestSimpleMeeting() {
        Meeting meeting = new Meeting(1, "работа", "VK", "27.09.2023 22:00:00");

        Todos todos = new Todos();

        todos.add(meeting);

        Task[] expected = {meeting};
        Task[] actual = todos.search("VK");
        Assertions.assertArrayEquals(expected, actual);
    }


    @Test
    public void requestEpic() {
        String[] subtasks = {"разработка клиентской части", "разработка серверной части", "покрытие автотестами"};
        Epic epic = new Epic(1, subtasks);

        Todos todos = new Todos();

        todos.add(epic);

        Task[] expected = {epic};
        Task[] actual = todos.search("покрытие автотестами");
        Assertions.assertArrayEquals(expected, actual);
    }
}
