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

        String questId = req.getParameter("questId");
        if (questId == null) {
            questId = (String) session.getAttribute("currentQuestId");
        }

        if (questId == null) {
            System.out.println("Quest ID is missing. Redirecting to welcome.jsp.");
            resp.sendRedirect("welcome.jsp");
            return;
        }

        List<Quest> selectedQuest = allQuests.get(questId);
        if (selectedQuest == null) {
            System.out.println("Quest not found for questId: " + questId);
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Quest not found");
            return;
        }

        session.setAttribute("currentQuestId", questId);

        if (session.getAttribute("currentQuestIndex") == null) {
            session.setAttribute("currentQuestIndex", 0);
            session.setAttribute("selectedQuest", selectedQuest);
        }

        int currentQuestIndex = (int) session.getAttribute("currentQuestIndex");
        if (currentQuestIndex >= selectedQuest.size()) {
            // Квест завершено — очищаємо сесійні змінні
            session.removeAttribute("currentQuestIndex");
            session.removeAttribute("selectedQuest");
            session.removeAttribute("currentQuestId");

            req.getRequestDispatcher("/result.jsp").forward(req, resp);
            return;
        }

        Quest currentQuest = selectedQuest.get(currentQuestIndex);
        req.setAttribute("currentQuest", currentQuest);
        req.getRequestDispatcher("/quest.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();

        List<Quest> selectedQuest = (List<Quest>) session.getAttribute("selectedQuest");
        if (selectedQuest == null) {
            resp.sendRedirect("welcome.jsp");
            return;
        }

        int currentQuestIndex = (int) session.getAttribute("currentQuestIndex");
        if (currentQuestIndex >= selectedQuest.size()) {
            // Якщо всі питання пройдено, перенаправляємо на сторінку вибору
            session.removeAttribute("currentQuestIndex");
            session.removeAttribute("selectedQuest");
            session.removeAttribute("currentQuestId");
            resp.sendRedirect("welcome.jsp");
            return;
        }

        Quest currentQuest = selectedQuest.get(currentQuestIndex);
        String selectedOption = req.getParameter("option");
        if (selectedOption == null) {
            req.setAttribute("message", "Please select an option before submitting!");
            req.setAttribute("currentQuest", currentQuest);
            req.getRequestDispatcher("/quest.jsp").forward(req, resp);
            return;
        }

        int selectedOptionIndex = Integer.parseInt(selectedOption);

        // Заміна getCorrectOptionIndex() на getCorrectAnswer()
        if (selectedOptionIndex == currentQuest.getCorrectAnswer()) {
            // Якщо відповідь правильна, переходимо до наступного питання
            session.setAttribute("currentQuestIndex", currentQuestIndex + 1);
            String currentQuestId = (String) session.getAttribute("currentQuestId");
            resp.sendRedirect("quest?questId=" + currentQuestId);
        } else {
            // Якщо відповідь неправильна, завершуємо квест
            session.removeAttribute("currentQuestIndex");
            session.removeAttribute("selectedQuest");
            session.removeAttribute("currentQuestId");
            req.setAttribute("message", "You failed the quest! Try again from the beginning.");
            req.getRequestDispatcher("/welcome.jsp").forward(req, resp);
        }
    }


}
