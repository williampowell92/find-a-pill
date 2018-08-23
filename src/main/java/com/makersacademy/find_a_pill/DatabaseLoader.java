package com.makersacademy.find_a_pill;

import com.makersacademy.find_a_pill.model.Pill;
import com.makersacademy.find_a_pill.service.PillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {
  private final PillService pillService;
  private final ElasticsearchTemplate template;

  @Autowired
  public DatabaseLoader(PillService pillservice, ElasticsearchTemplate template) {
    this.pillService = pillservice;
    this.template = template;
  }

  @Override
  public void run(String... strings) throws Exception {
    try {
      template.deleteIndex(Pill.class);
    } catch(Exception e) {
    }
    template.createIndex(Pill.class);

    pillService.save(new Pill("Arrays", "https://github.com/makersacademy/course/blob/master/pills/arrays.md", "In Ruby, an array is denoted by square brackets. The elements are separated by commas. We mentioned above that an array is an indexed collection. This means two things. First, all elements are ordered: Paris comes after London but before Berlin in our array. Second, we can access any element by specifying its index."));
    pillService.save(new Pill("Capybara", "https://github.com/makersacademy/course/blob/master/pills/capybara.md", "Capybara is a ruby gem that allows you to easily test the user experience of a web-based application. It provides methods that can mimic the actions a user would make in the browser, and test the outcomes of those actions in terms of what the user should be able to see. These methods can be used in conjunction with RSpec or Cucumber (or other test frameworks such as MiniTest, which are not covered in this course)."));
    pillService.save(new Pill("Exceptions", "https://github.com/makersacademy/course/blob/master/pills/exceptions.md", "Exceptions are situations when something goes wrong in your Ruby code. You saw them several times by now. For example, if you try to call a method that doesn't exist, you'll get an exception."));
    pillService.save(new Pill("Git", "https://github.com/makersacademy/course/blob/master/pills/git.md", "Version control is the process and technology used to \"control\" various \"versions\" of the code that you write. If, for example, you were building a house, you'd likely start with the foundation and make sure it's solid before you started building the walls. You'd want to stop after the walls were built to make sure everything lined up properly before adding the roof. You wouldn't want to build the entire thing and realize after the roof is built that there is a crack in the foundation."));
    pillService.save(new Pill("Java", "https://github.com/makersacademy/course/blob/master/pills/java.md", "ava derives much of its syntax from the C family of programming languages - the closest you will have seen to the overall look of Java is its namesake JavaScript, but the similarity is at the surface level; the two languages are quite different."));
    pillService.save(new Pill("MVC", "https://github.com/makersacademy/course/blob/master/pills/mvc.md", "MVC stands for model, view, controller – the three key types of component that make up many web apps."));
    pillService.save(new Pill("Procs", "https://github.com/makersacademy/course/blob/master/pills/procs.md", "A proc is an object that is associated with a block of code. It's convenient to use procs when the block of code doesn't need to be invoked but needs to be passed to other methods."));
    pillService.save(new Pill("Strings", "https://github.com/makersacademy/course/blob/master/pills/strings.md", "Strings are the standard way to represent bodies of text of arbitrary content and length."));
  }
}
