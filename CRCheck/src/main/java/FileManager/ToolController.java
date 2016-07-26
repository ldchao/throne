package FileManager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by mm on 2016/7/25.
 */
@Controller
public class ToolController {
    @RequestMapping("/topage")
    public String topage(@RequestParam String pagename){
        return pagename;
    }
}
