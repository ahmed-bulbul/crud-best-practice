package com.bulbul.bestpractice.user.dto.request;

import com.bulbul.bestpractice.common.generic.payload.marker.IDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AssignUserDto implements IDto {
    @NotNull
    private Long userId;
    @NotEmpty
    private Set<Long> roleIds;
    @NotNull
    private Long agencyId;
    @NotNull
    private Long fareInfoId;

}
