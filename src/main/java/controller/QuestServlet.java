package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Quest;
import utils.QuestLoader;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/quest")
public class QuestServlet extends HttpServlet {

    private Map<String, List<Quest>> allQuests;

    @Override
    public void init() throws ServletException {
        // Завантаження всіх квестів під час ініціалізації сервлета
        allQuests = QuestLoader.loadAllQuests();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();

        // Отримання questId з параметрів запиту
        String questId = req.getParameter("questId");
        if (questId == null) {
            // Якщо questId відсутній, пробуємо взяти його з сесії
            questId = (String) session.getAttribute("currentQuestId");
        }

        if (questId == null) {
            // Якщо questId все ще відсутній, перенаправляємо на сторінку вибору квестів
            System.out.println("Quest ID is missing. Redirecting to welcome.jsp.");
            resp.sendRedirect("welcome.jsp");
            return;
        }

        // Отримання списку питань для заданого квесту
        List<Quest> selectedQuest = allQuests.get(questId);
        if (selectedQuest == null) {
            // Якщо квест не знайдено, повертаємо помилку 404
            System.out.println("Quest not found for questId: " + questId);
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Quest not found");
            return;
        }

        // Зберігаємо questId у сесії
        session.setAttribute("currentQuestId", questId);

        // Ініціалізація сесійних даних, якщо це перший запит для квесту
        if (session.getAttribute("currentQuestIndex") == null) {
            session.setAttribute("currentQuestIndex", 0);
            session.setAttribute("selectedQuest", selectedQuest);
        }

        int currentQuestIndex = (int) session.getAttribute("currentQuestIndex");
        if (currentQuestIndex >= selectedQuest.size()) {
            // Якщо всі питання пройдено, перенаправляємо на сторінку результатів
            req.getRequestDispatcher("/result.jsp").forward(req, resp);
            return;
        }

        // Отримуємо поточне питання
        Quest currentQuest = selectedQuest.get(currentQuestIndex);
        req.setAttribute("currentQuest", currentQuest);
        req.getRequestDispatcher("/quest.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();

        // Отримання квесту з сесії
        List<Quest> selectedQuest = (List<Quest>) session.getAttribute("selectedQuest");
        if (selectedQuest == null) {
            // Якщо квест не знайдено в сесії, перенаправляємо на сторінку вибору квестів
            resp.sendRedirect("welcome.jsp");
            return;
        }

        // Отримання індексу поточного питання
        int currentQuestIndex = (int) session.getAttribute("currentQuestIndex");
        if (currentQuestIndex >= selectedQuest.size()) {
            // Якщо всі питання пройдено, перенаправляємо на сторінку вибору
            resp.sendRedirect("welcome.jsp");
            return;
        }

        Quest currentQuest = selectedQuest.get(currentQuestIndex);

        // Обробка відповіді
        String selectedOption = req.getParameter("option");
        if (selectedOption == null) {
            // Якщо відповідь не вибрана, показуємо повідомлення про помилку
            req.setAttribute("message", "Please select an option before submitting!");
            req.setAttribute("currentQuest", currentQuest);
            req.getRequestDispatcher("/quest.jsp").forward(req, resp);
            return;
        }

        int selectedOptionIndex = Integer.parseInt(selectedOption);

        if (selectedOptionIndex == currentQuest.getCorrectOptionIndex()) {
            // Якщо відповідь правильна, оновлюємо індекс і переходимо до наступного питання
            session.setAttribute("currentQuestIndex", currentQuestIndex + 1);
            String currentQuestId = (String) session.getAttribute("currentQuestId");
            resp.sendRedirect("quest?questId=" + currentQuestId);
        } else {
            // Якщо відповідь неправильна, показуємо повідомлення про помилку
            req.setAttribute("message", "Wrong answer! Try again.");
            req.setAttribute("currentQuest", currentQuest);
            req.getRequestDispatcher("/quest.jsp").forward(req, resp);
        }
    }
}
