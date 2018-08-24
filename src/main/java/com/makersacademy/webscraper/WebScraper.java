package com.makersacademy.webscraper;

import java.util.ArrayList;
import java.util.HashMap;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebScraper {

  public static void main(String[] args) throws Exception {
    final String USER_AGENT = "\"Mozilla/5.0 (Windows NT\" +\n" +
        "          \" 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2\"";
    final String loginFormUrl = "https://github.com/login";
    final String loginActionUrl = "https://github.com/session";
    final String username = System.getenv("GH_USER");
    final String password = System.getenv("GH_PASS");
    HashMap<String, String> cookies = new HashMap<>();
    HashMap<String, String> formData = new HashMap<>();
    HashMap<String, String> pillsCookies = new HashMap<>();

    // Goes to the login page for Github and then gets the login form which is required as it
    // contains a hidden field with an authentication tken used to prevent CSRF. It also contains
    // other cookies which need to be submitted on logging in
    Connection.Response loginForm = Jsoup.connect(loginFormUrl).method(Connection.Method.GET)
        .userAgent(USER_AGENT).execute();
    Document loginDoc = loginForm.parse();

    // collects the cookies from the form
    cookies.putAll(loginForm.cookies());
    // Gets the token using a css query
    String authToken = loginDoc.select("#login > form > input[type=\"hidden\"]:nth-child(2)")
        .first()
        .attr("value");

    // Creates the correct form data - note you need to set your own Github login credentials in the
    // environment (You can do this in the Run/debug configuration if using Intellij
    formData.put("commit", "Sign in");
    formData.put("utf8", "e2 9c 93");
    formData.put("login", username);
    formData.put("password", password);
    formData.put("authenticity_token", authToken);

    // Submit the form
    Connection.Response homePage = Jsoup.connect(loginActionUrl)
        .cookies(cookies)
        .data(formData)
        .method(Method.POST)
        .userAgent(USER_AGENT)
        .execute();

    // Collects the cookies which are now for the logged in user
    pillsCookies.putAll(homePage.cookies());
    pillsCookies.put("Connection", "keep-alive");

    // Navigate to the pills page. In this case the file-list is a file version not the full webpage. You will need to
    // be subscribed to repository to allow access
    Connection.Response pillsPage = Jsoup.connect("https://github.com/makersacademy/course/file-list/master/pills")
        .cookies(pillsCookies)
        .method(Connection.Method.GET)
        .userAgent(USER_AGENT).execute();
    Document pillsDoc = pillsPage.parse();

    // Collect the html elements which represent actual pills
    Elements pillElements = pillsDoc.select("tr:nth-child(n+3):nth-child(-n)");
    ArrayList<HashMap> pills = new ArrayList<>();

    // Iterate over html elements and extract necessary information
    for (Element pill : pillElements) {

      String title = pill.
    }



  }

}
