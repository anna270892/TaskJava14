import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void requestSimpleTask1() {
        SimpleTask simpleTask = new SimpleTask(1, "Тестировщик");

        Todos todos = new Todos();

        todos.add(simpleTask);

        Task[] expected = {simpleTask};
        Task[] actual = todos.search("Тестировщик");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void requestSimpleTask2() {
        SimpleTask simpleTask = new SimpleTask(1, "Тестировщик");

        Todos todos = new Todos();

        todos.add(simpleTask);

        Task[] expected = {}; //ожидаем пустой массив
        Task[] actual = todos.search("Разработчик");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void requestSimpleMeeting1() {
        Meeting meeting = new Meeting(1, "работа", "VK", "27.09.2023 22:00:00");

        Todos todos = new Todos();

        todos.add(meeting);

        Task[] expected = {meeting};
        Task[] actual = todos.search("VK");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void requestSimpleMeeting2() {
        Meeting meeting = new Meeting(1, "работа", "VK", "27.09.2023 22:00:00");

        Todos todos = new Todos();

        todos.add(meeting);

        Task[] expected = {meeting};
        Task[] actual1 = todos.search("VK");
        Task[] actual2 = todos.search("27.09.2023 22:00:00");
        Assertions.assertArrayEquals(expected, actual1);
        Assertions.assertArrayEquals(expected, actual2);
    }

    @Test
    public void requestSimpleMeeting3() {
        Meeting meeting = new Meeting(1, "работа", "VK", "27.09.2023 22:00:00");

        Todos todos = new Todos();

        todos.add(meeting);

        Task[] expected = {}; //ожидаем пустой массив
        Task[] actual = todos.search("VKMusic");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void requestEpic1() {
        String[] subtasks = {"разработка клиентской части", "разработка серверной части", "покрытие автотестами"};
        Epic epic = new Epic(1, subtasks);

        Todos todos = new Todos();

        todos.add(epic);

        Task[] expected = {epic};
        Task[] actual = todos.search("покрытие автотестами");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void requestEpic2() {
        String[] subtasks = {"разработка клиентской части", "разработка серверной части", "покрытие автотестами"};
        Epic epic = new Epic(1, subtasks);

        Todos todos = new Todos();

        todos.add(epic);

        Task[] expected = {epic};
        Task[] actual1 = todos.search("покрытие автотестами");
        Task[] actual2 = todos.search("разработка клиентской части");

        Assertions.assertArrayEquals(expected, actual1);
        Assertions.assertArrayEquals(expected, actual2);
    }

    @Test
    public void requestEpic3() {
        String[] subtasks = {"разработка клиентской части", "разработка серверной части", "покрытие автотестами"};
        Epic epic = new Epic(1, subtasks);

        Todos todos = new Todos();

        todos.add(epic);

        Task[] expected = {}; //ожидаем пустой массив
        Task[] actual = todos.search("дизайн проекта");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void requestEpic4() {
        String[] subtasks = {"разработка клиентской части"};
        Epic epic = new Epic(1, subtasks);

        Todos todos = new Todos();

        todos.add(epic);

        Task[] expected = {epic};
        Task[] actual = todos.search("разработка клиентской части");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void requestEpic5() {
        String[] subtasks = {};
        Epic epic = new Epic(1, subtasks);

        Todos todos = new Todos();

        todos.add(epic);

        Task[] expected = {};
        Task[] actual = todos.search("разработка клиентской части");
        Assertions.assertArrayEquals(expected, actual);
    }

    //Тесты с менеджером
    //ищем несколько задач
    @Test
    public void requestEpic6() {
        Todos manager = new Todos();

        Epic taskOne = new Epic(1, new String[]{"разработка клиентской части"});
        Epic taskTwo = new Epic(2, new String[]{"разработка серверной части"});
        Epic taskThree = new Epic(3, new String[]{"покрытие автотестами"});

        manager.add(taskOne);
        manager.add(taskTwo);
        manager.add(taskThree);

        String query = "разработка";

        Task[] expected = {taskOne, taskTwo};
        Task[] actual = manager.search(query);

        Assertions.assertArrayEquals(expected, actual);
    }

    //ищем одну задачу
    @Test
    public void requestEpic7() {
        Todos manager = new Todos();

        Epic taskOne = new Epic(1, new String[]{"разработка клиентской части"});
        Epic taskTwo = new Epic(2, new String[]{"разработка серверной части"});
        Epic taskThree = new Epic(3, new String[]{"покрытие автотестами"});

        manager.add(taskOne);
        manager.add(taskTwo);
        manager.add(taskThree);

        String query = "разработка клиентской части";

        Task[] expected = {taskOne};
        Task[] actual = manager.search(query);

        Assertions.assertArrayEquals(expected, actual);
    }

    //находим 0 задач, т.е. ни одна задача не подходит
    @Test
    public void requestEpic8() {
        Todos manager = new Todos();

        Epic taskOne = new Epic(1, new String[]{"разработка клиентской части"});
        Epic taskTwo = new Epic(2, new String[]{"разработка серверной части"});
        Epic taskThree = new Epic(3, new String[]{"покрытие автотестами"});

        manager.add(taskOne);
        manager.add(taskTwo);
        manager.add(taskThree);

        String query = "тестирование";

        Task[] expected = {};
        Task[] actual = manager.search(query);

        Assertions.assertArrayEquals(expected, actual);
    }
}
