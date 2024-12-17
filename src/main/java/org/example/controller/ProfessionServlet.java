package org.example.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.Profession;
import org.example.service.ProfessionService;
import org.springframework.security.core.parameters.P;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/")
public class ProfessionServlet extends HttpServlet {

//    final static Logger logger = LogManager.getLogger(ProfessionServlet.class);

    private ProfessionService service = new ProfessionService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {

            String page = request.getParameter("page");
            String profession = request.getParameter("name");
            request.setAttribute("profession", profession);

            if (page == null || page.isEmpty()) page = "1";
            if (profession == null) profession = "";
            int pageSize = 25;
            int offset = (Integer.parseInt(page) - 1) * pageSize;

            List<Profession> professions = new ArrayList<>();
            int totalPages = 0;
            if (profession != "") {
                professions = service.findByName(profession, pageSize, offset);
                totalPages = (int) Math.ceil((double) service.countResults(profession) / pageSize);

            } else {
                professions = service.findAll(pageSize, offset);
                totalPages = (int) Math.ceil((double) service.countResults("") / pageSize);

            }
            if (totalPages == 0) {
                totalPages = 1;
            }
            request.setAttribute("professions",
                    professions);


            request.setAttribute("countPages", getPaginatedPageNumbers(Integer.parseInt(page), totalPages));

            request.getRequestDispatcher("/profession.ftl").forward(request, response);
        } catch (IOException e) {
//            logger.error(e);
            e.printStackTrace();
        } catch (ServletException e) {
//            logger.error(e);
            e.printStackTrace();
        }
    }


    private List<String> getPaginatedPageNumbers(int currentPage, int totalPages) {
        int numPagesToDisplay = 10;
        List<String> pageNumbers = new ArrayList<>();

        // Определяем границы для отображения страниц
        int startPage = Math.max(1, currentPage - numPagesToDisplay / 2);
        int endPage = Math.min(totalPages, startPage + numPagesToDisplay - 1);

        // Корректировка границ, чтобы всегда отображать numPagesToDisplay страниц
        if (endPage - startPage + 1 < numPagesToDisplay) {
            if (endPage == totalPages) {
                startPage = Math.max(1, totalPages - numPagesToDisplay + 1);
            } else {
                endPage = totalPages;
            }
        }

        // Добавляем первую страницу с троеточием, если она не входит в диапазон
        if (startPage > 1) {
            pageNumbers.add("1");
            if (startPage > 2) {
                pageNumbers.add("...");
            }
        }
        // Добавляем номера страниц в диапазоне
        for (int i = startPage; i <= endPage; i++) {
            pageNumbers.add(String.valueOf(i));
        }

        // Добавляем последнюю страницу с троеточием, если она не входит в диапазон
        if (endPage < totalPages) {
            if (endPage < totalPages - 1) {
                pageNumbers.add("...");
            }
            pageNumbers.add(String.valueOf(totalPages));
        }

        return pageNumbers;
    }

}