<%-- 
    Document   : quiz
    Author     : Alexander Lundqvist & Ramin Shojaei

    This is an active quiz page. The user has to perform the quiz then submit it
    to return to the home page.
--%>

<%@page import="java.util.ArrayList"%>
<%
    String subject = (String) request.getSession().getAttribute("Quiz");
    if (subject == null) {
        subject = "Error";
    }
    
    String result = (String) request.getSession().getAttribute("Result");
    
    String[] allQuestions = {"", "", ""};
    String[] allOptions = {"", "", ""};
    
    String[] astronomyQuestions = {"Which planets are larger than earth?", "Which planet is nearest to the Sun?", "Which planets have rings?"};
    String[] astronomyOptions = {"Mercury/Mars/Saturn", "Mercury/Mars/Saturn", "Mercury/Mars/Saturn"};
    
    String[] natureQuestions = {"A caracal is what type of animal?", "What is the collective name for a group of crows?", "Canis is the latin word for which animal?"};
    String[] natureOptions = {"Bird/Fish/Cat", "Flight/Murder/Shoal", "Rabbit/Dog/Snake"};
    
    String[] historyQuestions = {"Which of these cities was NOT founded by the Romans?", "What was the capital of the Byzantine Empire?", "Which era came first?"};
    String[] historyOptions = {"Alexandria/Cologne/London", "Jerusalem/Constantinople/Alexandria", "Paleolithic/Neolithic/Chalcolithic"};
    
    if (subject.equals("Astronomy")) {
        allQuestions = astronomyQuestions;
        allOptions = astronomyOptions;
    }
    if (subject.equals("Nature")) {
        allQuestions = natureQuestions;
        allOptions = natureOptions;
    }
    if (subject.equals("History")) {
        allQuestions = historyQuestions;
        allOptions = historyOptions;
    }  
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quiz</title>
        <!-- Bootstrap latest compiled and minified core CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" 
        integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <!-- Optional theme -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap-theme.min.css" 
        integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
        <!-- Project styling -->
        <style>
        body {padding: 10px;}
        input {border-right: 10px;}
        </style>
    </head>
    <body>
        <% out.print("<h2>" + subject + "</h2>"); %>
        <% 
            out.print("<form id=\"quiz\" action=\"GameController\" method=\"POST\">");
            for (int i = 0; i < allQuestions.length; i++) {
                out.print("<h4>Question " + (i+1) + "</h4>");
                out.print("<p>" + allQuestions[i] + "</p>");
                String[] options = allOptions[i].split("/");
                for (int j = 0; j < options.length; j++) {
                    out.print("<input name=\"question" + (i+1) + "\" type=\"radio\" value=\"" + options[j] + "\"/>" + options[j] + "<br>");
                } 
            }
            out.print("<br><input name=\"submitQuiz\" type=\"submit\"  value=\"Submit\"/>");
            out.print("</form>");
        %>
    </body>
</html>
