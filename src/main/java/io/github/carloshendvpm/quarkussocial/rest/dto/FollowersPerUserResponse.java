package io.github.carloshendvpm.quarkussocial.rest.dto;

import java.util.List;

import lombok.Data;

@Data
public class FollowersPerUserResponse {
  private Integer followersCount;
  private List<FollowerResponse> content;
}
