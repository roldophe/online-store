package com.onlinestore.api.user.web;

import java.util.List;
import java.util.Set;

public record UserDto(String uuid,
                      String username,
                      String email,
                      String nickName,
                      List<String> roles) {
}
