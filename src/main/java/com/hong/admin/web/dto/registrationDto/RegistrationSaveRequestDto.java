package com.hong.admin.web.dto.registrationDto;


import com.hong.admin.domain.hashtag.Hashtag;
import com.hong.admin.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class RegistrationSaveRequestDto {
    private List<String> hashtags;
    private Posts posts;

    @Builder
    RegistrationSaveRequestDto(String hashtags, Posts posts){
        this.hashtags = hastagParsing(hashtags);
        this.posts = posts;
    }

    public List<String> hastagParsing(String title){
        List<String> hashtags = new ArrayList<>();
        Pattern p = Pattern.compile("\\#([0-9a-zA-Z가-힣]*)");
        Matcher m = p.matcher(title);

        String str = null;

        while (m.find()) {
            str = sepcialCharacter_replace(m.group());
            if(str != null){
                hashtags.add(str);
            }
        }

        return hashtags.stream().distinct().collect(Collectors.toList());

    }

    public String sepcialCharacter_replace(String str) {
        str = StringUtils.replaceChars(str, "-_+=!@#$%^&*()[]{}|\\;:'\"<>,.?/~`） ", "");

        if (str.length() < 1) {
            return null;
        }

        return str;
    }

}
