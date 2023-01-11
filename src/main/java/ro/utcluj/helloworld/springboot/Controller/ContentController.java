package ro.utcluj.helloworld.springboot.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ro.utcluj.helloworld.springboot.Logic.ContentService;
import ro.utcluj.helloworld.springboot.Model.Content;

import java.util.List;

@Controller
@RequestMapping(value="/content")
public class ContentController {
    @Autowired
    ContentService contentService;


    @RequestMapping(value="/list", method= RequestMethod.GET)
    public ModelAndView list() {

        ModelAndView model = new ModelAndView("content_list");
        List<Content> contentList = contentService.getAllContent();
        model.addObject("contentList", contentList);

        return model;
    }



    @RequestMapping(value="/addContent/", method=RequestMethod.GET)
    public ModelAndView addContent() {

        ModelAndView model = new ModelAndView();
        Content content = new Content();
        model.addObject("contentForm", content);
        model.setViewName("content_form");

        return model;
    }



    @RequestMapping(value="/editContent/{id}", method=RequestMethod.GET)
    public ModelAndView editStudent(@PathVariable int id) {
        ModelAndView model = new ModelAndView();

        Content content = contentService.getContentById(id);
        model.addObject("contentForm", content);
        model.setViewName("content_form");

        return model;
    }


    @RequestMapping(value="/addContent", method=RequestMethod.POST)
    public ModelAndView add(@ModelAttribute("contentForm") Content content) {

        contentService.addContent(content);
        return new ModelAndView("redirect:/content/list");

    }

    @RequestMapping(value="/deleteContent/{id}", method=RequestMethod.GET)
    public ModelAndView delete(@PathVariable("id") int id) {

        contentService.deleteContent(id);
        return new ModelAndView("redirect:/content/list");

    }

}
