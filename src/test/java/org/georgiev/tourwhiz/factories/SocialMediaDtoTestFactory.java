package org.georgiev.tourwhiz.factories;

import org.georgiev.tourwhiz.data.models.SocialMedia;
import org.georgiev.tourwhiz.presentation.dtos.SocialMediaDto;

public class SocialMediaDtoTestFactory {

    public static SocialMediaDto createWithSetParameters(SocialMedia socialMedia) {
        SocialMediaDto socialMediaDto = new SocialMediaDto();
        socialMediaDto.setType(socialMedia.getType());
        socialMediaDto.setWebpage(socialMedia.getWebpage());

        return socialMediaDto;
    }
}
