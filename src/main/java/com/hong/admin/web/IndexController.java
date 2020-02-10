package com.hong.admin.web;

import com.hong.admin.config.auth.LoginUser;
import com.hong.admin.config.auth.dto.SessionUser;
import com.hong.admin.domain.user.User;
import com.hong.admin.domain.user.UserRepository;
import com.hong.admin.service.follow.FollowService;
import com.hong.admin.service.posts.PostsService;
import com.hong.admin.service.user.UserService;
import com.hong.admin.web.dto.postsDto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;

    private final FollowService followService;

    private final UserService userService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user)  {

        model.addAttribute("posts", postsService.findAllDesc());


        if(user != null){
            model.addAttribute("loginUserEmail", user.getEmail());
        }

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(Model model, @LoginUser SessionUser user){

        model.addAttribute("user", user);

        return "posts-save";
    }


    @GetMapping("posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){

        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }

    @GetMapping("user/info/{email}")
    public String userInfo(@PathVariable String email, Model model, @LoginUser SessionUser user) {
        User targetUser = userService.findByEmail(email);
        Long targetId = userService.findByEmail(email).getId();

        model.addAttribute("user", targetUser);
        model.addAttribute("follower", followService.findAllFollowers(targetId));
        model.addAttribute("following", followService.findAllFollowings(targetId));
        //User Info requirement
        if(user != null) {
            model.addAttribute("loginUser", user.getEmail());
        }

        return "user-info";
    }


}
