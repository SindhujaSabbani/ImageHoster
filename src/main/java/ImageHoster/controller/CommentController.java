
package ImageHoster.controller;

        import ImageHoster.model.Comment;
        import ImageHoster.model.Image;
        import ImageHoster.model.User;
        import ImageHoster.service.CommentService;
        import ImageHoster.service.ImageService;
        import ImageHoster.service.UserService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestMethod;
        import org.springframework.web.bind.annotation.RequestParam;

        import javax.servlet.http.HttpSession;
        import java.util.Date;


@Controller
public class CommentController {

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

   @Autowired
    private CommentService commentService;

    /**
     * Controller method is called when a user comments on an image.
     * @param imageId id of the image
     * @param imageTitle title of the image
     * @param comment comment the user made
     * @param session
     * @return
     */
    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String createComment(@PathVariable(name = "imageId") Integer imageId,
                                @PathVariable(name = "imageTitle") String imageTitle,
                                @RequestParam(name = "comment") String comment, HttpSession session) {


        Comment newComment = new Comment();
        Image image = imageService.getImage(imageId);
        newComment.setImage(image);
        User user = (User) session.getAttribute("loggeduser");
        newComment.setUser(user);
        newComment.setText(comment);
        newComment.setCreatedDate(new Date());
        commentService.createComment(newComment);

        return "redirect:/images/" + imageId + "/" + imageTitle;
    }



}
