package org.georgiev.tourwhiz.mappers;

import org.georgiev.tourwhiz.data.models.SocialMedia;
import org.georgiev.tourwhiz.presentation.dtos.SocialMediaDto;
import org.georgiev.tourwhiz.presentation.dtos.UpdateSocialMediaDto;
import org.springframework.stereotype.Component;

@Component
public class SocialMediaMapper {

    public SocialMediaDto toDto(SocialMedia socialMedia) {
        return new SocialMediaDto(socialMedia.getType(), socialMedia.getWebpage());
    }

    public UpdateSocialMediaDto toUpdateDto(SocialMedia socialMedia) {
        return new UpdateSocialMediaDto(socialMedia.getWebpage());
    }

    public SocialMedia dtoToEntity(SocialMediaDto socialMediaDto) {
        SocialMedia socialMedia = new SocialMedia();
        socialMedia.setType(socialMediaDto.getType());
        socialMedia.setWebpage(socialMediaDto.getWebpage());

        return socialMedia;
    }

    public SocialMedia updateDtoToEntity(UpdateSocialMediaDto updateSocialMediaDto) {
        SocialMedia socialMedia = new SocialMedia();
        socialMedia.setWebpage(updateSocialMediaDto.getWebpage());

        return socialMedia;
    }

}
